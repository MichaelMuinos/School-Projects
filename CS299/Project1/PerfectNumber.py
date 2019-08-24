test_cases = [6, 28, 325, 496]


def get_factors(number):
    factors = []
    for i in range(1, number):
        if number % i == 0:
            factors.append(i)
    return factors


def is_perfect_number(factors, number):
    sum = 0
    for factor in factors:
        sum += factor

    if sum == number:
        print(str(number) + " is a perfect number, " + str(number) + " = "
              + ' + '.join(map(str, factors)) + ".\n")
    else:
        print(str(number) + " is not a perfect number.\n")


for case in test_cases:
    is_perfect_number(get_factors(case), case)
