import re

word_count = dict()
regex = re.compile('[^a-zA-Z \n]')      # regex, look for only letters and spaces
new_file = open("table.txt", "w")     # new file for filtered text

with open("book.txt", "r") as file:
    for line in file:
        line = regex.sub('', line.lower())  # make line all lowercase and cut all non-alphabetic chars
        split_line = line.split()      # split line into seperate words
        for word in split_line:         # cycle through each word in the line
            if word in word_count:      # if word has been added to the dict already
                count = word_count.get(word)    # get the count for the word
                word_count[word] = count + 1    # update our count by 1 and update the word count
            else:                           # word not in our dict yet
                word_count[word] = 1           # set count to be 1

word_count = sorted(word_count.items(), key=lambda x: -x[1])    # sort based on value in descending order
new_file.write('{:^{}} {:^{}}'.format("WORD", 20, "COUNT", 20) + "\n")
num = 1
for item in word_count:
    str = '{}. {:^{}} {:<{}}'.format(num, item[0], 20, item[1], 20)     # print in table format with even spacing
    new_file.write(str + "\n")
    num += 1



