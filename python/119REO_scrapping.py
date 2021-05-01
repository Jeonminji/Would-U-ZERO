import csv
import requests
import re
from bs4 import BeautifulSoup

 #csv파일저장
filename = "test.csv"
f = open(filename, "w",encoding="utf-8-sig",newline="")
writer = csv.writer(f)

title = "name,price,img,link".split(",")
writer.writerow(title)


for i in range(1,3):
    
    #페이지 스크래핑
    url = "http://www.119reo.com/25/?&page={}&sort=recent".format(i)
    res = requests.get(url)
    soup = BeautifulSoup(res.text,"lxml")

    items = soup.find_all("div", attrs = {"class":"item-wrap"})

    for item in items:
        name = item.find("h2").get_text() #제품 이름
        price = item.find("p", attrs = {"class":"pay no-margin"}).get_text() #가격
        img = item.find("img",attrs ={"class":"_org_img org_img _lazy_img"})['src'] #이미지
        link_href = item.find("a", attrs={"class":"_fade_link"})["href"] #판매 링크
        link = "http://www.119reo.com"+link_href
        data = [name, price, img, link]
        print(data)
        writer.writerow(data)
