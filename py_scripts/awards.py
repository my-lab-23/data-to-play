#!/usr/bin/env python3

from pyvirtualdisplay import Display
from selenium import webdriver
import time
import os

data_path = os.environ["MY_DATA_PATH"]

display = Display(visible=0, size=(800, 600))
display.start()

path = f"{data_path}/data_awards.txt"

f = open(path, "w")

#

chrome_options = webdriver.ChromeOptions()
chrome_options.add_argument('--no-sandbox')

chrome_options.add_experimental_option('prefs', {
    'download.default_directory': os.getcwd(),
    'download.prompt_for_download': False,
})

#

driver=webdriver.Chrome(options=chrome_options)

def run(composer):
    driver.get("https://www.prestomusic.com/classical/composers")
    search = driver.find_element_by_xpath(f'//a[contains(text(), "{composer}")]')
    driver.get(search.get_attribute("href"))
    search = driver.find_element_by_xpath('//a[contains(text(), "Hi-Res")]')
    driver.get(search.get_attribute("href"))
    search = driver.find_element_by_xpath('//a[contains(text(), "Award Winners")]')
    print(f"{composer} Hi-Res {search.text}")
    f.write(f"{composer} Hi-Res {search.text}\n")

composers = ["Haydn", "Mozart", "Bach", "Beethoven", "Ravel"]

for c in composers:
    run(c)

driver.quit()
display.stop()
f.close()
