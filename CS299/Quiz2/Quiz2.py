test_cases_avg = [[3,2.5,2,3.4,4], [], [5,'5',6,'abc',5.0]]


def myAve(list_of_nums):
    if len(list_of_nums) == 0:
        return 0.0
    else:
        sum = 0.0
        count = 0
        for i in list_of_nums:
            if isinstance(i, int):
                sum += i
                count += 1
        return sum / count

for case in test_cases_avg:
    print(case)
    print("Avg: " + str(myAve(case)) + "\n")

test_cases_encrypt = [1009, 2003, 3251, 4324]


def encrypt_number(num):
    str_num = str(num)
    str_encrypt = ''
    for character in str_num:
        str_encrypt += str((int(character) + 7) % 10)
    char_one = str_encrypt[0]
    char_two = str_encrypt[1]
    char_three = str_encrypt[2]
    char_four = str_encrypt[3]
    return int(char_three + char_four + char_one + char_two)

for case in test_cases_encrypt:
    print("Original Number: " + str(case))
    print("Encrypted Number: " + str(encrypt_number(case)) + "\n")