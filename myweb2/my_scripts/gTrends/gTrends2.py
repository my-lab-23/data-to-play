#!/usr/bin/env python3

import pandas as pd
import os

data_path = os.environ["MY_DATA_PATH"]

csv1 = f"{data_path}/searches_italy.csv"
csv2 = f"{data_path}/searches_france.csv"
text1 = f"{data_path}/searches_italy.txt"
text2 = f"{data_path}/searches_france.txt"

def run(csv, text):
    df = pd.read_csv(csv)
    s = df['0'].tolist()
    f = open(text, "w")
    for l in s:
        f.write(f"{l}\n")
    f.close()

run(csv1, text1)
run(csv2, text2)
