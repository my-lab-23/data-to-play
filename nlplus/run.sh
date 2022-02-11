#!/bin/bash
spark-submit --driver-memory "$2" --jars "$3" \
             --class "$1" ./target/scala-2.12/nlplus_2.12-1.0.jar \
             /
