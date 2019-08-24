def calculate_fee(name, base_fee, list_of_checks):
    print("Customer: " + name)
    print("Base fee: $" + str(base_fee))
    print("Check Deposits: ", end='')
    print(list_of_checks)

    sum = 0.0
    if list_of_checks is not None:
        for check in list_of_checks:
            if check <= 20:
                sum += check * .10
            elif check > 20 and check <= 50:
                sum += check * .07
            else:
                sum += check * .05
    sum += base_fee
    print("Total fee: $" + str(sum) + "\n")

calculate_fee("Jack", 10, [15, 29, 18, 7])
calculate_fee("Joan", 10, [36])
calculate_fee("David", 20, [3, 5, 2, 6])
calculate_fee("Diana", 20, None)