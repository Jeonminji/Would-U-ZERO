from bs4 import BeautifulSoup
import requests
import re
import csv

csv_filename = "toun28.csv" 

csv_open = open(csv_filename, "w+", encoding='utf-8')
csv_writer = csv.writer(csv_open)
csv_writer.writerow( ('main_category', 'siteName', 'name', 'price', 'img', 'link') )

crawling_url = "https://toun28.com/product/list"
response = requests.get(crawling_url)

bs = BeautifulSoup(response.text, 'html.parser' )

article_list = bs.find_all('div', {'class': re.compile('prod-card*')})

#csv 파일로 저장
for article in article_list:
    main_category = ""
    # main_category = article.find("div")
    # main_category.attrs['data-filter-val']
    sub_category = ""
    siteName = "톤28"
    name = article.find("p", attrs = {"class":"prod-name mb-1"}).get_text()
    price = article.select_one("p.prod-price > span").get_text()
    img = article.find('img')['src']
    link = article.find("a")["href"]
    csv_writer.writerow( (main_category, siteName, name, price, img, link) )

csv_open.close()

#view-source:https://toun28.com/product/list