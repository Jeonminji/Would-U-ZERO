import csv
import time
from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import googlemaps

def getLoc(addr):
    maps = googlemaps.Client(key = "AIzaSyDT7WYN8UOAErezYb36nI2D2p55afpPD1I")
    geocode_result = maps.geocode(addr)
    lat = geocode_result[0]['geometry']['location']['lat'] #위도
    lng = geocode_result[0]['geometry']['location']['lng'] #경도
    return lat, lng

def offline_shop(browser):
    soup = BeautifulSoup(browser.page_source,'lxml')

    stores = soup.find_all("li", attrs = {"class":"FavoriteDetailItem"})

    for store in stores:

        try:
            address = store.find("span", attrs = {"class":"desc_region"}).get_text() #매장 주소
            if "서울" in address:
                store_name = store.find("a", attrs = {"class":"link_txt"}).get_text() #매장 이름
                type = store.find("p", attrs = {"class":"desc_detail"}).get_text() #매장 유형

                if '제로'in type:
                    store_type = "제로웨이스트 샵 & 리필"
                elif '제로' in type and '리필':
                    store_type = "제로웨이스트 & 리필"
                elif '리필' in type:
                    store_type = "리필 단일샵"
                    if store_name == "레디투웰니스":
                        store_type = "제로웨이스트 샵 & 리필"
                elif '카페' in type or '베이커리' in type or '빵' in type or '테이크아웃' in type or '디저트' in type:
                    store_type = "카페"
                else:
                    store_type = type

                lat, lng = getLoc(address) #위도, 경도
                other_info = "기타정보 추가 예정(인스타 DM)"
                if store_name != "보틀라운지":
                    
                    #매장 상세정보 페이지로 넘김
                    browser.find_element_by_link_text(store_name).send_keys(Keys.ENTER)
                    browser.implicitly_wait(5)
                    browser.find_element_by_css_selector("a.detail").send_keys(Keys.ENTER)
                    browser.implicitly_wait(5)
                    browser.switch_to_window(window_name = browser.window_handles[-1])
                    time.sleep(3)

                    # 매장영업시간의 더보기 버튼 유무
                    try:
                        browser.find_element_by_css_selector("span.ico_comm.ico_more").click()
                        soup1 = BeautifulSoup(browser.page_source, 'lxml')
                        browser.implicitly_wait(10)
                        store_hours_info = soup1.find("div", attrs = {"class":"inner_floor"}).find_all("ul", attrs = {"class":"list_operation"})
                        store_hours= store_hours_info[0].find_all("li")
                        print(store_hours)
                        opening_hours = ""
                        for open_time in store_hours:
                            t_open = open_time.get_text()
                            opening_hours = opening_hours + t_open
                        
                    except:
                        soup1 = BeautifulSoup(browser.page_source, 'lxml')
                        try:
                            opening_hours = soup1.find("span",attrs = {"class":"txt_operation"}).get_text()
                            
                        except:
                            opening_hours = "  "

                    # 매장 인스타주소 or 사이트 링크
                    try:        
                        link = soup1.find("div",attrs = {"class":"placeinfo_default placeinfo_homepage"}).find("a",attrs = {"class":"link_homepage"})['href']
                    except:
                        link = "  "
                    
                    # 매장 전화번호 유무
                    try:
                        store_phone_num = soup1.find("div", attrs = {"class":"placeinfo_default placeinfo_contact"}).find("span", attrs = {"class":"txt_contact"}).get_text()
                    
                    except:
                        store_phone_num = " "
                    

                    browser.close()
                    browser.implicitly_wait(5)
                    browser.switch_to_window(window_name = browser.window_handles[0])

                    data = [lat, lng, store_name, address, store_phone_num, store_type, opening_hours, link, other_info]
                    writer.writerow(data)
                    print(address)
      
        except:
            print("\n 주소 없음 \n")

        
# csv파일저장
filename = "offline.csv"
f = open(filename, "w",encoding="utf-8-sig",newline="")
writer = csv.writer(f)

title = "lat,lng,store_name,address,store_phone_number,store_type,opening_hours,link,other_info".split(",")
writer.writerow(title)

       
# selenium 접속
options = webdriver.ChromeOptions()
options.add_experimental_option("excludeSwitches", ["enable-logging"])
browser = webdriver.Chrome(options=options)

browser.maximize_window()

url = "https://map.kakao.com/?map_type=TYPE_MAP&folderid=4049789&target=other&page=bookmark"
browser.implicitly_wait(30)
browser.get(url)

time.sleep(10)
offline_shop(browser)
browser.find_element_by_css_selector("#info\.other\.favorite\.page\.no2").send_keys(Keys.ENTER)
offline_shop(browser)

    