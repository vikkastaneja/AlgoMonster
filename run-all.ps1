[Console]::OutputEncoding = [System.Text.Encoding]::UTF8
$ErrorActionPreference = "Stop"
$OutputEncoding = [Console]::OutputEncoding

Set-Location -Path (Split-Path $MyInvocation.MyCommand.Path)

$src = "src"
$build = "build_temp"
$assertion = "$src/com/algo/innov8r/common/Assertion.java"
$categoryFilter = $args[0]
if ([string]::IsNullOrWhiteSpace($categoryFilter)) { $categoryFilter = "all" }

Write-Host "Preparing build..."

if (Test-Path $build) { Remove-Item $build -Recurse -Force }
New-Item $build -ItemType Directory | Out-Null

Write-Host "Compiling Assertion.java..."
javac -d $build $assertion
if ($LASTEXITCODE -ne 0) {
    Write-Host "Compilation failed for Assertion.java" -ForegroundColor Red
    exit 1
}

Write-Host "Compiling remaining Java files..."
$others = Get-ChildItem $src -Recurse -Filter *.java |
        Where-Object { $_.FullName -ne (Resolve-Path $assertion).Path }

javac -d $build -cp "$build;$src" @($others.FullName)
if ($LASTEXITCODE -ne 0) {
    Write-Host "Compilation failed" -ForegroundColor Red
    exit 1
}

$ErrorActionPreference = "Continue"
Write-Host ""
Write-Host "Running tests..."

$total = 0
$passed = 0
$failed = 0

$classes = Get-ChildItem $build -Recurse -Filter *.class

foreach ($class in $classes) {
    $classPath = $class.FullName
    $className =$classPath.Substring($classPath.IndexOf($build) +$build.Length + 1)
    $className = [System.IO.Path]::ChangeExtension($className, $null)  -replace '\\','.' -replace '/','.'
    $className = $className.TrimEnd('.')

    $javaSource = $classPath -replace "$build", "$src" -replace "\.class$", ".java"
    if (-not (Test-Path $javaSource)) { continue }

    if ($categoryFilter -ne "all" -and $javaSource -notmatch $categoryFilter) { continue }
    if (Select-String $javaSource -Pattern "@skip" -Quiet) { continue }
    if (-not (Select-String $javaSource -Pattern "public static void main" -Quiet)) { continue }

    $total++
    Write-Host "Running: $className"

    $sw = [System.Diagnostics.Stopwatch]::StartNew()

    # Run java and capture exit code
    $output = & java -cp "$build;$src" $className 2>&1
    $exitCode = $LASTEXITCODE
    $Error.Clear()

    $sw.Stop()

    if ($exitCode -eq 0) {
        Write-Host "PASS ($($sw.ElapsedMilliseconds) ms)" -ForegroundColor Green
        $passed++
    } else {
        Write-Host "FAIL ($($sw.ElapsedMilliseconds) ms)" -ForegroundColor Red
        $failed++

        Write-Host "---- Output Below ----" -ForegroundColor DarkYellow
        Write-Host $output -BackgroundColor White -ForegroundColor DarkRed
    }
}

Write-Host ""
Write-Host "Summary:"
Write-Host "  Total:  $total" -ForegroundColor Cyan
Write-Host "  Passed: $passed" -ForegroundColor Green
Write-Host "  Failed: $failed" -ForegroundColor Red

Write-Host ""
Write-Host "Cleaning build folder..."
Remove-Item $build -Recurse -Force

Write-Host "Done!"
