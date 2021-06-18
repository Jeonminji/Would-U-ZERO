import csv
from bs4 import BeautifulSoup
import requests
import re

filename = "119REO.csv"
f = open(filename, "w", encoding="utf-8-sig", newline="")
writer = csv.writer(f)

title = "main_category,sub_category,siteName,name,price,img,link".split(",")
writer.writerow(title)

for i in range(1, 3):
    url = "http://www.119reo.com/25/?&page={}".format(i)
    res = requests.get(url)
    res.raise_for_status()
    soup = BeautifulSoup(res.text, "lxml")
    siteName = "119REO"
    items = soup.find_all("div", attrs={"class": re.compile("^shop-item")})

    for item in items:
        price = item.select_one('div > p').get_text().replace(",", "")
        name = item.select_one('h2 > a').get_text()

        if "지갑" in name:
            main = "잡화"
            sub = "지갑"
        elif "다이어리" in name or "필통" in name:
            main = "잡화"
            sub = "기타"
        elif "백팩" in name or "챌린저백" in name:
            main = "가방"
            sub = "백팩"
        elif "레토백" in name:
            main = "가방"
            sub = "토트백"
        elif "샤코슈백" in name or "슬링백" in name or "히어로" in name or "레코백" in name or "캔버스백" in name:
            main = "가방"
            sub = "크로스/숄더백"
        elif "노트북파우치" in name:
            main = "가방"
            sub = "클러치"
        elif "미니파우치" in name or "쓰리웨이백" in name:
            main = "가방"
            sub = "기타"
        else:
            main = "잡화"
            sub = "악세사리"

        img = item.select_one('div > a > img')['src']
        link = "https://www.119reo.com" + item.select_one('div > h2 > a')['href']

        data = [main, sub, siteName, name, price, img, link]
        writer.writerow(data)
