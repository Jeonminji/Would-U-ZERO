from bs4 import BeautifulSoup
import requests
import re
import csv

csv_filename = "toun28.csv" 

csv_open = open(csv_filename, "w+", encoding='utf-8')
csv_writer = csv.writer(csv_open)
csv_writer.writerow( ('main_category', 'sub_category', 'siteName', 'name', 'price', 'img', 'link') )

crawling_url = "https://toun28.com/product/list"
response = requests.get(crawling_url)

bs = BeautifulSoup(response.text, 'html.parser' )

article_list = bs.find_all('div', {'class': re.compile('prod-card*')})

#csv 파일로 저장
for article in article_list:
        
    # 톤28의 카테고리
    category = article['data-filter-val']
    
    if '씻을거리' in category:
        if '고체세제' in category:
            main_category = "주방"
            sub_category = "etc"
        else:
            main_category = "욕실"
            sub_category = "etc"
        
    else:
        main_category = "화장품"
        if '손/몸' in category:
            sub_category = "etc"
        else:
            sub_category = "기초"

    
    siteName = "톤28"
    name = article.find("p", attrs = {"class":"prod-name mb-1"}).get_text()
    price = article.select_one("p.prod-price > span").get_text()
    img = article.find('img')['src']
    link = article.find("a")["href"]
    csv_writer.writerow( (main_category, sub_category , siteName, name, price, img, link) )

csv_open.close()