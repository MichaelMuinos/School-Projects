import urllib.request

# Date, Open, High, Low, Close, Volume, Adj Close
#  0  ,  1  ,  2  ,  3 ,  4   ,   5   ,    6


def retrieve_data(url):
    page = urllib.request.urlopen(url)
    days = page.readlines()

    for index, day in enumerate(days):
        days[index] = day.decode("utf-8").split(",")
    return days[1:]


def get_highest_trading_volume(days):
    max = '0.0'
    day_max = ''
    for day in days:
        if float(day[5]) > float(max):
            max = day[5]
            day_max = day[0]
    return day_max, max


print("--------Target Stock---------")
target_stock = retrieve_data("http://chart.yahoo.com/table.csv?s=TGT")
ts_day_highest_volume, ts_highest_volume = get_highest_trading_volume(target_stock)
print(ts_day_highest_volume + ", trading volume is " + ts_highest_volume + " shares, the highest in the past 20 years.")

print("\n---------Microsoft Stock------------")
microsoft_stock = retrieve_data("http://chart.yahoo.com/table.csv?s=MSFT")
ms_day_highest_volume, ms_highest_volume = get_highest_trading_volume(microsoft_stock)
print(ms_day_highest_volume + ", trading volume is " + ms_highest_volume + " shares, the highest in the past 20 years.")