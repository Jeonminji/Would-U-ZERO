import csv
from bs4 import BeautifulSoup
import re
import time
from selenium import webdriver

filename = "Touch4good.csv"
f = open(filename, "w", encoding="utf-8-sig", newline="")
writer = csv.writer(f)

title = "main_category,sub_category,siteName,name,price,img,link".split(",")
writer.writerow(title)

options = webdriver.ChromeOptions()
options.add_argument("headless")

for i in range(1, 6):
    url = "https://touch4good.com/shop?productListPage={}".format(i)

    driver = webdriver.Chrome(executable_path="/Users/yyyy1/PycharmProjects/chromedriver", options=options)
    driver.get(url)
    driver.implicitly_wait(10)
    time.sleep(3)
    html = driver.page_source

    driver.implicitly_wait(10)
    soup = BeautifulSoup(html, "lxml")
    siteName = "터치포굿"
    items = soup.find_all("div", attrs={"class": re.compile("^shopProductWrapper")})

    for item in items:
        price = item.find("span", attrs={"productPriceSpan"})
        name = item.find("div", attrs={"class":"shopProduct productName"}).get_text()

        if price:
            price = price.get_text().replace(",", "")
        else:
            price = item.find("span", attrs={"class":"productPriceWithDiscountSpan"}).get_text().replace(",", "")

        if "지갑" in name:
            main = "잡화"
            sub = "지갑"
        elif "자켓" in name:
            main = "의류"
            sub = "상의"
        elif "목도리" in name or "Animal" in name or "스카프" in name:
            main = "의류"
            sub = "기타"
        elif "가방" in name or "에코백" in name:
            main = "가방"
            sub = "크로스/솔더백"
        elif "백팩" in name:
            main = "가방"
            sub = "백팩"
        elif "파우치" in name:
            main = "가방"
            sub = "기타"
        elif "마스크" in name:
            main = "잡화"
            sub = "악세사리"
        else:
            main = "잡화"
            sub = "기타"

        img = "https://contents.sixshop.com/thumbnails" + item.find("div", attrs={"class":"thumb img"})['imgsrc'].replace(".jpg", "_1000.jpg").replace(".png", "_1000.png")
        link = "https://touch4good.com" + item.select_one('div > a')['href']

        data = [main, sub, siteName, name, price, img, link]

        if "_default" in img:
            data.clear()
        else:
            writer.writerow(data)

