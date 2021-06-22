import csv
import requests
from bs4 import BeautifulSoup

# csv생성
filename = "Earthling.csv"

f = open(filename, "w",encoding="utf-8-sig",newline="")
writer = csv.writer(f)

title = "main_category,sub_category,siteName,name,price,img,link".split(",")
writer.writerow(title)

for i in range(1,3):
    url = "https://smartstore.naver.com/horanmalko/category/97b8cfa4060d4eec980533f5324e468e?cp={}".format(i)
    response = requests.get(url)
    bs = BeautifulSoup(response.text, 'html.parser')
    items = bs.find_all("li", attrs = {"class":"-qHwcFXhj0"})

    for item in items:
        main_category = ''
        sub_category = ''
        name = item.find("strong", attrs = {"class":"QNNliuiAk3"}).get_text() #상품
        pri = item.find("span", attrs = {"class":"nIAdxeTzhx"}).get_text() #가격
        price = pri.replace(",","")
        img = item.find("img", attrs = {"class":"_25CKxIKjAk"})['src']
        siteName = "EARTHLING"
        link_href = item.find("a")["href"]
        link = "https://smartstore.naver.com"+link_href #판매링크
        data = [main_category,sub_category,siteName,name, price, img, link]
        writer.writerow(data)
        

f.close()
