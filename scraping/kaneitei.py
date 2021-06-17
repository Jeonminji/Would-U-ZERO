import csv
import time
from bs4 import BeautifulSoup
from selenium import webdriver

# csv파일저장
filename = "카네이테이/kaneitei.csv"
f = open(filename, "w",encoding="utf-8-sig",newline="")
writer = csv.writer(f)

title = "main_category,sub_category,siteName,name,price,img,link,hash_tag".split(",")
writer.writerow(title)

# selenium 접속
options = webdriver.ChromeOptions()
options.add_experimental_option("excludeSwitches", ["enable-logging"])
browser = webdriver.Chrome(options=options)

# 전체 화면
browser.maximize_window()

url = "https://www.kaneitei.com/shop"
browser.implicitly_wait(30)
browser.get(url)

time.sleep(10) #price 등 동적 페이지로 가져오는 부분이 있어서 time.sleep으로 페이지 전체 로드를 기다림

# 소분류 카테고리 분류 및 상품정보 csv 삽입
def filecsv(items, main_category, sub_category):
    for item in items:
        name = item.find("div", attrs = {"class":"shopProduct productName"}).get_text() #상품명
        
        # 6월 4일 12시 --> 만약 할인되는 상품이 있을 경우
        try:
            pri = item.find("span", attrs = {"class":"productPriceSpan"}).get_text() #가격
        except:
            pri = item.find("span",attrs = {"class":"productPriceWithDiscountSpan"}).get_text()
        price = pri.replace(",","")
        img_link = item.find("div", attrs = {"class":"thumb img"})['style']
        siteName = "카네이테이"
        # 이미지 url이 style의 background-image 뒤에 붙어있어서 문자열을 잘라냄
        img = img_link.split('background-image:url(')[1][:-1]
        print(img)
        link_href = item.find("a")["href"]
        link = "https://www.kaneitei.com"+link_href #판매링크
        hash_tag = "null"
        data = [main_category,sub_category,siteName,name, price, img, link, hash_tag]
        writer.writerow(data)    


for i in range(2,8,1):
    # i = 5 -> 다른 카테고리와 중복됨 
    if i != 5:
        c_selector = '#itemElement19014871 > div.productListFilterCollection > div.productListFilterCollectionNavi > div:nth-child({})'.format(i)
        browser.find_element_by_css_selector(c_selector).click()
        time.sleep(5)

        soup = BeautifulSoup(browser.page_source,'lxml')
        category = soup.find("div", attrs = {"class":"productListFilter-navi now"}).get_text()

        items = soup.find_all("div", attrs = {"class":"shopProductWrapper"})
        
        if category == "지갑":
            main_category = "잡화"
            sub_category = "지갑"
            filecsv(items, main_category, sub_category)

        elif category == "케이스":
            main_category = "가방"
            sub_category = "클러치"
            filecsv(items, main_category, sub_category)

        elif category == "가방":
            main_category = "가방"
            sub_category = "백팩" #임의로 sub를 백팩으로 분류
            filecsv(items, main_category, sub_category)

        elif category == "악세서리": #상품 확인 결과 신발, 모자만 업사이클링 제품
            main_category = "의류"
            sub_category = "신발"
            filecsv(items, main_category, sub_category)

        else: 
            main_category = "의류"
            sub_category = "상의"
            filecsv(items, main_category, sub_category)