#!/bin/bash
./saveJavaFileNames.sh
javac -d bin @classnames.txt -Xlint:deprecation
# echo "compile done."
