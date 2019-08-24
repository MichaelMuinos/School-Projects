import urllib.request

# Date, Open, High, Low, Close, Volume, Adj Close
#  0  ,  1  ,  2  ,  3 ,  4   ,   5   ,    6


def retrieve_data(url):
    page = urllib.request.urlopen(url)
    week = page.readlines()[4:9]

    for index, day_of_week in enumerate(week):
        week[index] = day_of_week.decode("utf-8").split(",")
    return week


def get_weeks_highest_share_price(week):
    max = '0.0'
    day_max = ''
    for day in week:
        if float(day[2]) > float(max):
            max = day[2]
            day_max = day[0]
    return day_max, format_dollar_amount(max)


def get_weeks_lowest_share_price(week):
    low = week[0][3]
    day_low = ''
    for day in week:
        if float(day[3]) < float(low):
            low = day[3]
            day_low = day[0]
    return day_low, format_dollar_amount(low)


def get_weeks_largest_trading_volume(week):
    max = '0'
    day_max = ''
    for day in week:
        if int(day[5]) > int(max):
            max = day[5]
            day_max = day[0]
    return day_max, max


def get_weeks_average_closing_price(week):
    sum = 0.0
    for day in week:
        sum += float(day[4])
    return format_dollar_amount(sum / len(week))


def format_dollar_amount(amount):
    return str("%.2f" % float(amount))


print("------Target Stock-------")
target_stock = retrieve_data("http://chart.yahoo.com/table.csv?s=TGT")

ts_day_max_share_price, ts_max_share_price = get_weeks_highest_share_price(target_stock)
print(ts_day_max_share_price + ", share price is $" + ts_max_share_price + ", the highest of the week.")

ts_day_low_share_price, ts_low_share_price = get_weeks_lowest_share_price(target_stock)
print(ts_day_low_share_price + ", share price is $" + ts_low_share_price + ", the lowest of the week.")

ts_day_trading_vol, ts_max_trading_vol = get_weeks_largest_trading_volume(target_stock)
print(ts_day_trading_vol + ", trading volume is " + ts_max_trading_vol + " shares, the highest of the week.")

ts_average_closing_price = get_weeks_average_closing_price(target_stock)
print("$" + ts_average_closing_price + ", the average closing price of the week.")

print("\n------Microsoft Stock-------")
microsoft_stock = retrieve_data("http://chart.yahoo.com/table.csv?s=MSFT")

ms_day_max_share_price, ms_max_share_price = get_weeks_highest_share_price(microsoft_stock)
print(ms_day_max_share_price + ", share price is $" + ms_max_share_price + ", the highest of the week.")

ms_day_low_share_price, ms_low_share_price = get_weeks_lowest_share_price(microsoft_stock)
print(ms_day_low_share_price + ", share price is $" + ms_low_share_price + ", the lowest of the week.")

ms_day_trading_vol, ms_max_trading_vol = get_weeks_largest_trading_volume(microsoft_stock)
print(ms_day_trading_vol + ", trading volume is " + ms_max_trading_vol + " shares, the highest of the week.")

ms_average_closing_price = get_weeks_average_closing_price(microsoft_stock)
print("$" + ms_average_closing_price + ", the average closing price of the week.")
