import urllib.request
import numpy as np
import matplotlib.pyplot as plt

# Getting 3 most recent months in Southern California!

earthquake_rows = [[]]
date_list = ["Oct", "Sep", "Aug", "Jul"]
magnitude_dict = {}

page = urllib.request.urlopen("http://earthquake.usgs.gov/earthquakes/shakemap/list.php?n=sc&y=2016")
decoded_page = page.read().decode()

tr_odd_tag = '<tr class="alt">'
tr_even_tag = '<tr>'
temp_list = []
for item in decoded_page.split("</tr>"):
    if tr_odd_tag or tr_even_tag in item:
        temp_list.append(item)
        earthquake_rows.append(temp_list)
        temp_list = []

earthquake_rows = earthquake_rows[1:]
info = [[]]
count = 0
td_tag = '<td class='
for row in earthquake_rows:
    chunk_list = []
    for item in row[0].split("</td>"):
        if td_tag in item:
            chunk_list.append(item)
    info.append(chunk_list)
    chunk_list = []

info = info[2:len(info)-1]
for item in info:
    for date in date_list:
        if date in item[2]:
            magnitude = item[0][-3:]
            if magnitude in magnitude_dict:
                magnitude_dict[magnitude] = magnitude_dict.get(magnitude) + 1
            else:
                magnitude_dict[magnitude] = 1


print(magnitude_dict.keys())
print(magnitude_dict.values())

pos = np.arange(len(list(magnitude_dict.keys())))
width = 1

axis = plt.axes()
axis.set_xticks(pos + (width / 2))
axis.set_xticklabels(list(magnitude_dict.keys()))

plt.bar(pos, list(magnitude_dict.values()), width)
plt.show()
