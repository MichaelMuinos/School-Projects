purchase_cases = [
    [80, 125, 45.5],
    [95],
    [12, 5.5, 6.5, 7.5, 9.0]
]
tax_cases = [8, 10.5, 7]


# default parameter of 8% tax
def sales_receipt_print(purchases = None, tax = 8):
    print("Purchase: ", end = '')
    sum = 0

    if purchases is None:
        print(str(sum) + ", ", end = '')
    else:
        sum = 0
        for item in purchases:
            sum += item
            print(str(item) + ", ", end = '')
        print("tax rate: " + str(tax) + "%")
        tax /= 100
        tax += 1
        sum *= tax

    return sum


def round_and_print_sum(sum):
    print("Total: " + str(float("{0:.2f}".format(sum))) + " dollars\n")

sum = sales_receipt_print(purchase_cases[0])
round_and_print_sum(sum)

sum = sales_receipt_print(purchase_cases[1], tax_cases[1])
round_and_print_sum(sum)

sum = sales_receipt_print(purchase_cases[2], tax_cases[2])
round_and_print_sum(sum)
