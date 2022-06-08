#!/bin/bash
./compile.sh
java -classpath bin Main listen --username AmirHBahrami -lp 2020
echo "[FIN]"
