# Lab 2
# Michael Muinos

def main():
    weight = int(input("Enter your weight(lbs): "))
    height = int(input("Enter your height(inches): "))
    BMI = 703 * weight / (height ** 2)
    
    print("Your BMI is " + str(BMI) + "!")
    
    if BMI <= 24:
        print("You are considered NORMAL! Awesome!\n")
    elif BMI >= 25 and BMI <= 29:
        print("You are considered OVERWEIGHT! \n")
    elif BMI >= 30 and BMI <= 39:
        print("You are considered OBESE! Oh no!\n")
    else:
        print("You are considered EXTREMELY OBESE! Go exercise!\n")

if __name__ == '__main__':
    main()