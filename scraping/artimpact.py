from urllib.request import urlopen
from bs4 import BeautifulSoup
import csv

#csv파일저장
filename = "artimpact.csv"
f = open(filename, "w",encoding="utf-8-sig",newline="")
writer = csv.writer(f)
title = "main_category,sub_category,siteName,name,price,img,link".split(",")
writer.writerow(title)

url = "https://smartstore.naver.com/blueorb"
html = urlopen(url)
soup = BeautifulSoup(html,"html.parser")
siteName = "아트임팩트 블루오브"
items = soup.select('.whole_products > ul > li')

for item in items:
    price = item.find("div", attrs = {"class":"_23DThs7PLJ"}).find("span").get_text().replace(",","") + "원" #상품 가격
    try:
        price = item.find("span", attrs = {"class":"_45HSXeff1y"}).find_all("span")[1].get_text().replace(",","") + "원" #상품 가격
    except:
        print("할인가격 없음")

    name = item.find("strong").get_text() #상품 이름

    if "지갑" in name or "카드" in name:
        main_category = "잡화"
        sub_category = "지갑"
    elif "브리프케이스" in name or "토트" in name or "쇼퍼" in name or "사첼백" in name:
        main_category = "가방"
        sub_category = "토트백"
    elif "백팩" in name:
        main_category = "가방"
        sub_category = "백팩"
    elif "메신저백" in name or "슬링백" in name or "새들백" in name or "숄더" in name or "크로스" in name or "포트폴리오" in name or "에코" in name or "데이백" in name or "탬버린 백" in name or "원마일 백" in name:
        main_category = "가방"
        sub_category = "크로스/숄더백"
    elif "클러치" in name:
        main_category = "가방"
        sub_category = "클러치"
    elif "파우치" in name or "슬링" in name or "버킷백" in name:
        main_category = "가방"
        sub_category = "기타"
    elif "스니커즈" in name:
        main_category = "의류"
        sub_category = "신발"
    elif "케이스" in name:
        main_category = "잡화"
        sub_category = "케이스"
    elif "네임택" in name or "목걸이" in name or "키 홀더" in name or "키링" in name or "레터" in name:
        main_category = "잡화"
        sub_category = "악세사리"
    else:
        main_category = "잡화"
        sub_category = "기타"

    img = item.find("img")['src']
    link = "https://smartstore.naver.com/" + item.select_one('a')['href']
    data = [main_category,sub_category,siteName,name, price, img, link]
    writer.writerow(data)