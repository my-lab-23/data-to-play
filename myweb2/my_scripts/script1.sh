#!/bin/bash
echo "Bumble!"
curl "$MY_BEES_IP_PORT"/text > /home/ema/data/twitter.txt
spark-submit --class "Main" ./my_scripts/jar/bumble_2.12-1.0.jar
