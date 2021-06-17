from urllib.request import urlopen
from bs4 import BeautifulSoup
import openpyxl
import requests

wb = openpyxl.Workbook()
ws1 = wb.active
ws1.title = "continue scrapping"
ws1.append(["name", "img", "link"])

for p in range(1, 5):
    html = urlopen("http://wecontinew.co.kr/product/list.html?cate_no=80&page={p}".format(p = p))
    

    bsObject = BeautifulSoup(html, "html.parser")
    li_code = bsObject.select('#prd-right > div.xans-element-.xans-product.xans-product-listnormal.infinite > ul > li')
    for a in li_code:
        a_tag = a.select_one('.name')
        name = a_tag.text
        img = a.select_one('div > a > img')['data-original']
        link = a.select_one('div > a')['href']
        
        ws1.append([name, img, link])

wb.save(filename='continue scrapping.csv')
