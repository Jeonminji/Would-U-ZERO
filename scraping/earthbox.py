from bs4 import BeautifulSoup
import requests
import re
import csv

csv_filename = "earthbox.csv" 

csv_open = open(csv_filename, "w+", encoding='utf-8')
csv_writer = csv.writer(csv_open)
csv_writer.writerow( ('main_category', 'sub_category', 'siteName', 'name', 'price', 'img', 'link') )

crawling_url = "https://www.earthbox.co.kr/shop"
response = requests.get(crawling_url)

bs = BeautifulSoup(response.text, 'html.parser' )

article_list = bs.find_all('div', {'class': re.compile('shop-item _shop_item')})

#csv 파일로 저장
for article in article_list:
    
    main_category = ""
    sub_category = ""
    
    siteName = "Earthbox"
    name = article.find("h2").get_text()
    price = article.find("p", attrs = {"class":"sale_pay no-margin"}).get_text().replace(",", "")
    img = article.find('img')['src']
    link =  'https://www.earthbox.co.kr' + article.find("a")["href"]
    csv_writer.writerow( (main_category, sub_category, siteName, name, price, img, link) )

csv_open.close()