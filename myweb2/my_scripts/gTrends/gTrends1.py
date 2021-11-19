#!/usr/bin/env python3

from pytrends.request import TrendReq
import os

data_path = os.environ["MY_DATA_PATH"]

path1 = f"{data_path}/searches_italy.csv"
path2 = f"{data_path}/searches_france.csv"

pytrend = TrendReq()

trending_searches_df = pytrend.trending_searches(pn='italy')
trending_searches_df.to_csv(path1)

trending_searches_df = pytrend.trending_searches(pn='france')
trending_searches_df.to_csv(path2)
