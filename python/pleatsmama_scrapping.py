import csv
import requests
import re
from bs4 import BeautifulSoup

url = "https://pleatsmama.com/shop-knitpleatsbag-all?page="

#csv파일저장
filename = "apparel2.csv"
f = open(filename, "w",encoding="utf-8-sig",newline="")
writer = csv.writer(f)

title = "name,price,img,link".split(",")
writer.writerow(title)

#페이지 스크래핑
for page in range(1,8):
    res = requests.get(url+str(page))
    soup = BeautifulSoup(res.text,"lxml")

    items = soup.find_all("div", attrs = {"class":"item-wrap"})

    for item in items:
        name = item.find("h2").get_text() #제품 이름
        price = item.find("p", attrs = {"class":"pay no-margin"}).get_text() #가격
        img = item.find("img",attrs ={"class":"_org_img org_img _lazy_img"})['src'] #이미지
        link_href = item.find("a", attrs={"class":"_fade_link"})["href"] #판매 링크
        link = "https://pleatsmama.com"+link_href
        data = [name, price, img, link]
        #print(data)
        writer.writerow(data)