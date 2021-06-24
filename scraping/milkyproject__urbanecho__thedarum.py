#!/usr/bin/python
# coding=utf8

import requests
from bs4 import BeautifulSoup
import pandas as pd

f = open("thedarum_unrbanecho_milkyproject.csv", "a", encoding = "utf-8-sig")

# 더 다룸

web_list = ["https://smartstore.naver.com/thedarum1/products/5581303962",
           "https://smartstore.naver.com/thedarum1/products/5565953345",
           "https://smartstore.naver.com/thedarum1/products/5563077648",
           "https://smartstore.naver.com/thedarum1/products/5582536490",
           "https://smartstore.naver.com/thedarum1/products/5581310497",
           "https://smartstore.naver.com/thedarum1/products/5572863241",
           "https://smartstore.naver.com/thedarum1/products/5566149690",
           "https://smartstore.naver.com/thedarum1/products/5563149694",
           "https://smartstore.naver.com/thedarum1/products/5563010105"]

product_list = []

print(web_list)
for web_site in web_list:
    web = requests.get(web_site)
    soup = BeautifulSoup(web.content, "html.parser")

    print(web)
    #원가
    price = soup.select("._1LY7DqCnwR")[0].get_text().replace(",","")

    #제품명
    content = soup.h3.get_text()
    
    #판매처명
    shop = soup.select("._3QBCSk9AvE")[0].get_text()
    
    #상품 이미지
    img = soup.select('._23RpOU6xpc img')[0].get('src')

    #판매 링크
    link = web_site
    
    #카테고리
    if content.find("칫솔") != -1:
        b_category = "욕실"
        s_category = "구강"
    elif content.find("행주") != -1:
        b_category = "주방"
        s_category = "행주"
    else:
        b_category = "잡화"
        s_category = "기타"
    
    small_list=[b_category, s_category, shop, content, (price + '원'), img, link]
    print(small_list)
    product_list.append(small_list)

#밀키 프로젝트, urban.echo

web_list = ["https://smartstore.naver.com/urbanecho/products/4828982423",
           "https://smartstore.naver.com/urbanecho/products/4694845812",
           "https://smartstore.naver.com/urbanecho/products/4963443559",
           "https://smartstore.naver.com/urbanecho/products/5454039116",
           "https://smartstore.naver.com/urbanecho/products/5447454028",
           "https://smartstore.naver.com/urbanecho/products/5376261084",
           "https://smartstore.naver.com/urbanecho/products/5367530060",
           "https://smartstore.naver.com/urbanecho/products/5237719016",
           "https://smartstore.naver.com/urbanecho/products/5123395916",
           "https://smartstore.naver.com/urbanecho/products/5119555986",
           "https://smartstore.naver.com/urbanecho/products/4998807712",
           "https://smartstore.naver.com/urbanecho/products/4998694317",
           "https://smartstore.naver.com/urbanecho/products/4962734544",
           "https://smartstore.naver.com/urbanecho/products/4933380022",
           "https://smartstore.naver.com/urbanecho/products/4844447421",
            "https://smartstore.naver.com/urbanecho/products/4768993220",
            "https://smartstore.naver.com/urbanecho/products/4745067532",
            "https://smartstore.naver.com/urbanecho/products/4731761753",
            "https://smartstore.naver.com/urbanecho/products/4731738124",
            "https://smartstore.naver.com/urbanecho/products/4731736020",
            "https://smartstore.naver.com/urbanecho/products/4731729624",
            "https://smartstore.naver.com/milkyproject/products/5345569833",
           "https://smartstore.naver.com/milkyproject/products/5068837226",
           "https://smartstore.naver.com/milkyproject/products/5068836641",
           "https://smartstore.naver.com/milkyproject/products/5068832575",
           "https://smartstore.naver.com/milkyproject/products/4876312971"
           ]

for web_site in web_list:
    web = requests.get(web_site)
    soup = BeautifulSoup(web.content, "html.parser")
    
    print(web)
    
    #가격
    price = soup.select("._1LY7DqCnwR")[0].get_text().replace(",","")

    #제품명
    content = soup.h3.get_text()
    
    #판매처명
    shop = soup.select(".KasFrJs3SA")[0].get_text()

    #상품 이미지
    img = soup.select("._23RpOU6xpc img")[0].get("src")
    
    #판매 링크
    link = web_site
    
     #카테고리
    if (content.find("수세미") != -1) or (content.find("행주") != -1) or (content.find("스트로우") != -1):
        b_category = "주방"
        if (content.find("수세미") != -1):
            s_category = "수세미"
        elif (content.find("행주") != -1):
            s_category = "행주"
        else:
            s_category = "기타"
    elif (content.find("칫솔") != -1) or (content.find("샤워") != -1):
        b_category = "욕실"
        if (content.find("칫솔") != -1):
            s_category = "구강"
        else:
            s_category = "기타"
    elif (content.find("화장") != -1):
        b_category = "화장품"
        if (content.find("세안") != -1):
            s_category = "기초"
        else:
            s_category = "기타"
    else:
        b_category = "잡화"
        s_category = "기타"

    small_list=[b_category, s_category, shop, content, (price + '원'), img, link]
    
    print(small_list)
    
    product_list.append(small_list)
    
data = pd.DataFrame(product_list, columns=['main_category','sub_category','siteName', 'name','price', 'img','link'])
data.to_csv("wuz_web_scraping.csv", index=False)
f.close()
