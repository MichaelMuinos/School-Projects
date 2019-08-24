##################### PART FOUR #####################

weight = int(input("Enter your weight(lbs): "))
height = int(input("Enter your height(inches): "))
BMI = 703 * weight / (height * height)
print("BMI: " + str(BMI))
# my weight = 142, my height = 64
# result: 24

##################### END OF PART FOUR ##############

print("\n")

##################### PART FIVE #####################

print("**************")
print("   ******	")
print("Hello! How's everything?")
print("There are 35 students in 1 session of CS 299 class.")
print("Have a great quarter!")

##################### END OF PART FIVE ##############

print("\n")

##################### PART SIX (OPTIONAL) ###########

f = open('lab1-part6-newfile', 'w')
f.write('**************\n'
        + '   ******	\n'
        + 'Hello! How\'s everything?\n'
        + 'There are 35 students in 1 session of CS 299 class.\n'
        + 'Have a great quarter!')
f.close()

##################### END OF PART SIX ################

##################### PART SEVEN #####################

year = int(input("Enter a year: "))
result = ((year % 4 == 0) and (year % 100 != 0)) or (year % 400 == 0)
print(str(result) + '\n')

# used to test if a year is a leap year
def is_leap_year(new_year):
    result = ((new_year % 4 == 0) and (new_year % 100 != 0)) or (new_year % 400 == 0)
    print("Year " + str(new_year) + ": " + str(result))

##################### END OF PART SEVEN ##############







