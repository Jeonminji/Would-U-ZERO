import csv
import time
from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.webdriver.common.keys import Keys

# csv파일저장
filename = "pleatsmama_clothes.csv"
f = open(filename, "w",encoding="utf-8-sig",newline="")
writer = csv.writer(f)

title = "main_category,sub_category,siteName,name,price,img,link".split(",")
writer.writerow(title)


# selenium 접속
options = webdriver.ChromeOptions()
options.add_experimental_option("excludeSwitches", ["enable-logging"])
browser = webdriver.Chrome(options=options)

# 꽉찬 화면을 사용했을 때 site내에 있는 버그로 다음 화면 이동이 안돼서 작은 화면으로 사용함
# browser.set_window_size(900,1000)
browser.maximize_window()


url = "https://pleatsmama.com/apparel"
browser.implicitly_wait(30)
browser.get(url)

# 소분류 카테고리 분류 및 상품정보 csv 삽입
def filecsv(items,sub_category):
    
    siteName = "플리츠마마"
    main_category= "의류"

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
                print("탈출")
                break
            else:
                # 갑자기 element 클릭이 안됨 --> send_key 사용하니까 됨
                # button.click()
                button.send_keys(Keys.ENTER)
                # time.sleep(2)

        # 더보기 버튼이 없는 경우
        except:
            print("버튼없음")
            break
    
    # 현재 페이지 소스 가져오기
    soup1 = BeautifulSoup(browser.page_source,"lxml")

    #상품 정보 가져오기
    items = soup1.find_all("div", attrs = {"class":"item-wrap"})
    # print(items)

    sub_category = soup1.find("ul",attrs={"class":"clearfix"}).find("li", attrs={"class":"depth-01 active active-real"}).find("span", attrs={"class":"plain_name"}).get_text()
        
    if sub_category == "상의":
        sub_category = "상의"
        print(sub_category)
        filecsv(items,sub_category)

    elif sub_category == "하의":
        sub_category = "하의"
        filecsv(items,sub_category)
    
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
    if s_category == "상의" or s_category == "하의" or s_category == "머플러" or s_category == "담요":
        category_name.append(s_category)


# 카테고리 별로 클릭
for cate_name in category_name:
    cate_click(cate_name)
    
   
        
