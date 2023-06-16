from bs4 import BeautifulSoup
import requests
import pandas as pd


def site():
    url = 'http://127.0.0.1:5500/menu.html'
    response = requests.get(url)
    html_content = response.content
    soup = BeautifulSoup(html_content, "html.parser")
    names = soup.find_all('strong', class_='product-title')
    prices = soup.find_all('span', class_='product-price')

    names = [n.text for n in names]
    prices = [total.text for total in prices]
    df = pd.DataFrame({'Product': names, 'Price':prices})
    df.to_excel("products.xlsx", index=False)
    print("File 'products.xlsx' successfully created!:)")


if __name__ == "__main__":
    site()
