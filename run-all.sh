#!/usr/bin/env bash

# Directory setup
src="src"
build="build_temp"
assertion="$src/com/algo/innov8r/common/Assertion.java"
categoryFilter="$1"

if [[ -z "$categoryFilter" ]]; then
    categoryFilter="all"
fi

# Colors
GREEN="\033[32m"
RED="\033[31m"
YELLOW="\033[33m"
CYAN="\033[36m"
RESET="\033[0m"

# Millisecond timestamp safe for macOS + Linux
get_time_ms() {
    if command -v gdate &> /dev/null; then
        gdate +%s%3N
    elif date +%s%3N &> /dev/null; then
        date +%s%3N
    else
        python3 - <<'EOF'
import time; print(int(time.time() * 1000))
EOF
    fi
}

echo -e "${CYAN}Preparing build...${RESET}"

# Clean old build
rm -rf "$build"
mkdir -p "$build"

echo -e "${YELLOW}Compiling Assertion.java...${RESET}"
javac -d "$build" "$assertion"

echo -e "${YELLOW}Compiling other Java files...${RESET}"
mapfile -t others < <(find "$src" -name "*.java" ! -path "$assertion")
javac -d "$build" -cp "$build:$src" "${others[@]}"

echo -e "\n${CYAN}Running tests...${RESET}"
total=0
passed=0
failed=0

# Get all compiled classes
mapfile -t classes < <(find "$build" -name "*.class")

for classFile in "${classes[@]}"; do
    # Derive fully qualified class name
    className="${classFile#$build/}"
    className="${className%.class}"
    className="${className//\//.}"

    # Match .java source to filter by package/category
    javaSource="$src/${className//.//}.java"
    [[ ! -f "$javaSource" ]] && continue
    [[ "$categoryFilter" != "all" && "$javaSource" != *"$categoryFilter"* ]] && continue

    # Run only classes with main()
    if ! grep -q "public static void main" "$javaSource"; then
        continue
    fi

    total=$((total + 1))
    echo -e "Running: $className"

    start=$(get_time_ms)
    output=$(java -cp "$build:$src" "$className" 2>&1)
    exitCode=$?
    end=$(get_time_ms)
    duration=$((end - start))

    if [[ $exitCode -eq 0 ]]; then
        echo -e "  ${GREEN}✔ PASS${RESET} (${duration}ms)"
        passed=$((passed + 1))
    else
        echo -e "  ${RED}✘ FAIL${RESET} (${duration}ms)"
        failed=$((failed + 1))
        echo -e "${YELLOW}---- Output Below ----${RESET}"
        echo -e "${RED}${output}${RESET}"
    fi
done

echo ""
echo -e "${CYAN}================================${RESET}"
echo -e "   TEST SUMMARY (${total})"
echo -e "${CYAN}================================${RESET}"
echo -e "${RED}✘ FAILED:${RESET}  $failed"
echo -e "${GREEN}✔ PASSED:${RESET}  $passed"
echo -e "${CYAN}--------------------------------${RESET}"
echo -e "${CYAN}================================${RESET}"

echo ""
echo "Cleaning build folder..."
rm -rf "$build"

echo -e "${GREEN}Done!${RESET}"