test_cases = [29,47,51,127,189]


def is_prime(num):
    for i in range(2,num):
        if num % i == 0:
            return False
    return True

for case in test_cases:
    print(str(case) + ": " + str(is_prime(case)))