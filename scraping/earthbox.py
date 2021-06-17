from bs4 import BeautifulSoup
import re
import csv
import time
from selenium import webdriver

csv_filename = "earthbox.csv" 

csv_open = open(csv_filename, "w+", encoding='utf-8')
csv_writer = csv.writer(csv_open)
csv_writer.writerow( ('main_category', 'sub_category', 'siteName', 'name', 'price', 'img', 'link') )

# selenium 접속
options = webdriver.ChromeOptions()
options.add_experimental_option("excludeSwitches", ["enable-logging"])
browser = webdriver.Chrome(options=options)
browser.maximize_window()

url = "https://www.earthbox.co.kr/shop"
browser.implicitly_wait(30)
browser.get(url)

time.sleep(4)
bs = BeautifulSoup(browser.page_source, 'html.parser' )

category_lists = bs.find("div",attrs = {"id":"w20210113e61bf1deee3fc"}).find_all("li")
category_list =[]
for category in category_lists:
    category_list.append(category.find("span",attrs={"class":"plain_name"}).get_text())

for category in category_list:
    if category != "키트":
        browser.find_element_by_link_text(category).click()

        soup = BeautifulSoup(browser.page_source,"lxml")
        article_list = soup.find_all('div', {'class': re.compile('shop-item _shop_item')})

        for article in article_list:
    
            main_category = category
            sub_category = ""
            
            siteName = "Earthbox"
            name = article.find("h2").get_text()
            price = article.find("p", attrs = {"class":"sale_pay no-margin"}).get_text().replace(",", "")
            img = article.find('img')['src']
            link =  'https://www.earthbox.co.kr' + article.find("a")["href"]
            csv_writer.writerow( (main_category, sub_category, siteName, name, price, img, link) )

csv_open.close()
