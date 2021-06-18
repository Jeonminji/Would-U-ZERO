import csv
from bs4 import BeautifulSoup
import requests
#from urllib.request import urlopen
import re

filename = "Touch4good.csv"
f = open(filename, "w", encoding="utf-8-sig", newline="")
writer = csv.writer(f)

title = "main_category,sub_category,siteName,name,price,img,link".split(",")
writer.writerow(title)

#html = urlopen(url)
#soup = BeautifulSoup(html, "html.parser")
#siteName = "터치포굿"
#items = soup.select('#section603319 > div.item-wrapper productList > div.item-element filter-use > div.productListWrapper c-3 ratio-1to1 > div > div')

for i in range(1, 3):
    url = "https://touch4good.com/shop?productListPage={}".format(i)
    res = requests.get(url)
    res.raise_for_status()
    soup = BeautifulSoup(res.text, "lxml")
    siteName = "터치포굿"
    items = soup.find_all("div", attrs={"class": re.compile("^shopProductWrapper")})
    for item in items:
        price = item.find("div", attrs={"class":"shopProduct price"}).get_text()
        name = item.find("div", attrs={"class":"shopProduct productName"}).get_text()
        #link = item.find("a", attrs={"class":""})["href"]
print(name, link)

    #data = [main, sub, siteName, name, price, img, "http://www.touch4good.com" + link]
    #writer.writerow(data)
