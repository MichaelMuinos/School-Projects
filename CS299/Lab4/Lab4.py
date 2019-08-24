import math

print("////////PART 1/////////\n")
# Create list
items = ["Hello", 7, 7, 9, 'a', 'cat', False]
print("a) ", end='')
print(items)

# Append pi and 7
items.append(math.pi)
items.append(7)
print("b) ", end='')
print(items)

# Insert dog at position 3
items.insert(3, 'dog')
print("c) ", end='')
print(items)

# Find index of 'cat'
index_of_cat = items.index('cat')
print("d) ", end='')
print("Index of \'cat\': " + str(index_of_cat))
print("\t", end='')
print(items)

# Count number of 7's
number_of_sevens = items.count(7)
print("e) ", end='')
print("Number of 7\'s: " + str(number_of_sevens))
print("\t", end='')
print(items)

# Remove the first 7
items.remove(7)
print("f) ", end='')
print(items)

# Remove 'dog' from the list using pop and index
index_of_dog = items.index('dog')
items.pop(index_of_dog)
print("g) ", end='')
print(items)


# Part 2
print("\n\n/////////Part 2/////////\n")


def reverse(item_list):
    temp_list = []
    for i in range(len(item_list) - 1, -1, -1):
        temp_list.append(item_list[i])
    return temp_list


def index(item, item_list):
    for index_item, item_in_list in enumerate(item_list):
        if item == item_in_list:
            return index_item
    return -1


def median(list_of_numbers):
    length = len(list_of_numbers)
    pos = length // 2
    if length % 2 == 0:
        return float((list_of_numbers[pos - 1] + list_of_numbers[pos]) / 2)
    return list_of_numbers[pos]


reverse_test_list = [3, 5, 'Hello', True]
print("a) ", end='')
print(reverse_test_list)
print("Reversed: ", end='')
print(reverse(reverse_test_list))

index_test_list = [5, 2, 10, "dog", False]
print("b) ", end='')
print(index_test_list)
print("Testing \"dog\": index is " + str(index("dog", index_test_list)))
print("Testing 2: index is " + str(index(2, index_test_list)))
print("Testing 5: index is " + str(index(5, index_test_list)))
print("Testing \"cat\": index is " + str(index("cat", index_test_list)))

median_test_list_one = [24, 5, 8, 2, 9, 15, 10]
median_test_list_two = [24, 5, 8, 2, 9, 15, 10, 54]
print("c) ", end='')
median_test_list_one.sort()
median_test_list_two.sort()
print(median_test_list_one)
print("Median 1st List: " + str(median(median_test_list_one)))
print(median_test_list_two)
print("Median 2nd List: " + str(median(median_test_list_two)))

