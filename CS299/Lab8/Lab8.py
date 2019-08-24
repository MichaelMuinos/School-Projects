

def reverse_string(str):
    if str == "":
        return "";
    else:
        return reverse_string(str[1:]) + str[0]


def pattern(num):
    if num == 0:
        print(num, "\t", end='')
        return;
    else:
        pattern(num-1)
        print(num, "\t", end='')
        pattern(num-1)


def double_values(integer_list):
    return [i*2 for i in integer_list]


def square_odd(integer_list):
    return [i**2 for i in integer_list if i % 2 == 1]


def take_prime(integer_list):
    return [i for i in integer_list if is_prime(i)]


def is_prime(num):
    for i in range(2,num):
        if num % i == 0:
            return False
    return True


def generic_sort(function, args):
    return function(args)


def ascending(item_list):
    for i in range(len(item_list)):
        for j in range(0, len(item_list)-i-1):
            if item_list[j] > item_list[j+1]:
                temp = item_list[j]
                item_list[j] = item_list[j+1]
                item_list[j+1] = temp
    return item_list


def descending(item_list):
    for i in range(len(item_list)):
        for j in range(0, len(item_list)-i-1):
            if item_list[j] < item_list[j+1]:
                temp = item_list[j]
                item_list[j] = item_list[j+1]
                item_list[j+1] = temp
    return item_list

print("----Part One----")
print("abc --> " + reverse_string("abc"))
print("hello, world! --> " + reverse_string("hello, world!"))

print("\n\n----Part Two----")
for pat in range(0, 5):
    print("\nPattern: ", pat)
    pattern(pat)
    print("\n")

print("\n\n----Part Three----")
int_list = [5, 2, 12, 4, 9, 13, 22, 1, 6, 17]
print("Sort by descending...")
print(generic_sort(descending, int_list))
print("\n")

name_list = ["Kate", "Sam", "Kate", "Jolly", "Alp", "Beta",
             "Alpine", "Samuel", "Bob"]
print("Sort in alphabetical...")
print(generic_sort(ascending, name_list))
print("\n")

tuple_list = [("Kate", 3), ("Sam", 2), ("Kate", 5), ("Jolly", 1), ("Alp", 2),
              ("Beta", 3), ("Alp", 1), ("Alpine", 2), ("Sam", 4), ("Bob", 2),
              ("Sam", 3)]
print("Sort in alphabetical...")
print(generic_sort(ascending, tuple_list))
print("\n")

print("\n\n----Part Four----")
integer_list = []
for i in range(1, 21):
    integer_list.append(i)
print("Double values...")
print(double_values(integer_list))
print("Square odd...")
print(square_odd(integer_list))
print("Get prime...")
print(take_prime(integer_list))
