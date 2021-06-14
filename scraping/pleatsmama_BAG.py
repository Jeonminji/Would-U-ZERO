import csv
import time
from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.webdriver.common.keys import Keys

# csv파일저장
filename = "pleatsmama_bag.csv"
f = open(filename, "w",encoding="utf-8-sig",newline="")
writer = csv.writer(f)

title = "main_category,sub_category,siteName,name,price,img,link".split(",")
writer.writerow(title)


# selenium 접속
options = webdriver.ChromeOptions()
options.add_experimental_option("excludeSwitches", ["enable-logging"])
browser = webdriver.Chrome(options=options)
browser.maximize_window()

url = "https://pleatsmama.com/shop-knitpleatsbag-all"
browser.implicitly_wait(30)
browser.get(url)

# 소분류 카테고리 분류 및 상품정보 csv 삽입
def filecsv(items,sub_category):
    
    siteName = "플리츠마마"
    main_category= "가방"

    for item in items:
        name = item.find("h2").get_text() #제품 이름
        pri = item.find("p", attrs = {"class":"pay no-margin"}).get_text() #가격
        price = pri.replace(",","")
        img = item.find("img",attrs ={"class":"_org_img org_img _lazy_img"})['src'] #이미지
        link_href = item.find("a", attrs={"class":"_fade_link"})["href"] #판매 링크
        link = "https://pleatsmama.com"+link_href
        data = [main_category, sub_category, siteName, name, price, img, link]
        writer.writerow(data)

# 카테고리 별 클릭
def cate_click(cate_name):
    # 암묵적 대기
    browser.implicitly_wait(10)
    
    # 클릭이 안먹혀서 해당 링크/명령어에 엔터를 실행하도록 함 ==> 됨
    browser.find_element_by_link_text(cate_name).send_keys(Keys.ENTER)
    
    # 화면이 전환되는 도중에 while문으로 들어가서 button을 계속 클릭하는 버그가 생김 ==> sleep으로 화면이 완전히 전환된 후 button탐색
    time.sleep(2)

    # 소분류 카테고리별 페이지를 끝까지 읽기 위한 반복문
    while True:
        # 더보기 버튼이 있는 경우
        try:
            button = browser.find_element_by_css_selector("a.btn._more_btn.more_btn")
            print(button.text)

            # 버튼이 있을 경우 끝까지 클릭 후 탈출
            if button.text == "":
                break
            else:
                # 갑자기 element 클릭이 안됨 --> send_key 사용하니까 됨
                button.send_keys(Keys.ENTER)

        # 더보기 버튼이 없는 경우
        except:
            print("버튼없음")
            break
    
    # 현재 페이지 소스 가져오기
    soup1 = BeautifulSoup(browser.page_source,"lxml")

    #상품 정보 가져오기
    items = soup1.find_all("div", attrs = {"class":"item-wrap"})

    # 현재 소카테고리 가져오기
    sub_category = soup1.find("ul",attrs={"class":"clearfix"}).find("li", attrs={"class":"depth-01 active active-real"}).find("span", attrs={"class":"plain_name"}).get_text()
        
    if "숄더백" in sub_category or "크로스백" in sub_category:
        sub_category = "크로스/숄더백"
        filecsv(items,sub_category)

    elif "토트백" in sub_category:
        sub_category = "토트백"
        filecsv(items,sub_category)

    elif "백팩" in sub_category or "빅백" in sub_category:
        sub_category = "백팩"  
        filecsv(items, sub_category)
    
    else:
        sub_category = "기타"
        filecsv(items,sub_category)

# 현재 페이지 소스 가져오기
soup = BeautifulSoup(browser.page_source,"lxml")

# 소분류 카테고리 
sub_cate_name = soup.find("ul",attrs={"class":"clearfix"}).find_all("li")
category_name = []

for sub_cate in sub_cate_name:
    s_category = sub_cate.find("span", attrs = {"class":"plain_name"}).get_text() #소분류 카테고리 이름

    # 전체상품보여주기, 중복되는 글리터백, 없는 카테고리(클러치) 삭제
    if s_category != "니트플리츠백 ALL" and s_category != "글리터백" and s_category != "클러치":
        category_name.append(s_category)

# 카테고리 별로 클릭
for cate_name in category_name:

    # 빅백의 경우 사이트 클릭시 소카테고리가 클러치 하나만 보여지는 오류가 있음
    if cate_name == "빅백":
        cate_click(cate_name)
        browser.find_element_by_link_text("니트플리츠백").send_keys(Keys.ENTER)

    else:
        cate_click(cate_name)
        
    

