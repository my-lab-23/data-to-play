#!/bin/bash
spark-submit --jars ./target/scala-2.12/spark-nlp-assembly-3.3.2.jar \
             --class "$1" ./target/scala-2.12/nlplus_2.12-1.0.jar \
             /
