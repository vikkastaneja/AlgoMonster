#!/usr/bin/env bash
export TERM=xterm-256color
set -e
SRC="src"
BUILD="build_temp"
ASSERTION="$SRC/com/algo/innov8r/common/Assertion.java"

echo -e "Preparing build..."

rm -rf "$BUILD"
mkdir -p "$BUILD"

echo "Compiling Assertion.java..."
javac -d "$BUILD" "$ASSERTION"

echo "Compiling other Java files..."
OTHERS=$(find "$SRC" -name '*.java' ! -path "*common/Assertion.java")

# Pick correct classpath separator for OS
if [[ "$(uname -s)" == "Darwin" || "$(uname -s)" == "Linux" ]]; then
  CP_SEP=":"
else
  CP_SEP=";"
fi

javac -d "$BUILD" -cp "$BUILD$CP_SEP$SRC" $OTHERS

echo
echo "Running tests..."

TOTAL=0
PASSED=0
FAILED=0

CLASS_FILES=$(find "$BUILD" -name '*.class')

for classfile in $CLASS_FILES; do
    # Skip inner classes ($)
    if [[ "$classfile" == *'$'* ]]; then
        continue
    fi

    REL_PATH="${classfile#$BUILD/}"
    CLASS_NAME="${REL_PATH%.class}"
    CLASS_NAME="${CLASS_NAME//\//.}"

    JAVA_SRC="${classfile/$BUILD/$SRC}"
    JAVA_SRC="${JAVA_SRC%.class}.java"

    [[ ! -f "$JAVA_SRC" ]] && continue

    # Only run classes with a real main()
    if ! grep -q "public static void main" "$JAVA_SRC"; then
        continue
    fi

    TOTAL=$((TOTAL + 1))
    echo "Running: $CLASS_NAME"

    start=$(date +%s%N)
    start=${start//[!0-9]/}

    OUTPUT=$(java -cp "$BUILD$CP_SEP$SRC" "$CLASS_NAME" 2>&1)
    EXIT=$?

    end=$(date +%s%N)
    end=${end//[!0-9]/}

    runtime=$(( (end - start) / 1000000 ))

    if [[ $EXIT -eq 0 ]]; then
        echo -e "  ✔ PASS (${runtime}ms)"
        PASSED=$((PASSED + 1))
    else
        echo -e "  ✘ FAIL (${runtime}ms)"
        FAILED=$((FAILED + 1))
        echo "------ Output ------"
        echo "$OUTPUT"
    fi
done

echo
echo "Summary:"
echo "  Total:  $TOTAL"
echo "  Passed: $PASSED"
echo "  Failed: $FAILED"

echo
echo "Cleaning build folder..."
rm -rf "$BUILD"

echo "Done!"