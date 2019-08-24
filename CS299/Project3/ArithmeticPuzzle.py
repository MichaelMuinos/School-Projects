import itertools

character_list = []
digit_list = []


def add_chars_from_word(word):
    for character in word:
        if character not in character_list:
            character_list.append(character)


def solve_equation(word_one, word_two, sum_word):
    add_chars_from_word(word_one)
    add_chars_from_word(word_two)
    add_chars_from_word(sum_word)


def get_word_number(word):
    number = ''
    for character in word:
        number += digit_list[character_list.index(character)]
    return int(number)

first_word = input("Enter 1st word in equation: \n")
second_word = input("Enter 2nd word in equation: \n")
sum_word = input("Enter the sum word (the result): \n")

print("Processing solutions...\n")

solve_equation(first_word, second_word, sum_word)
count = 0
found_solution = False
for permutation in itertools.permutations(range(10), len(character_list)):
    for index, item in enumerate(list(permutation)):
        digit_list.append(str(item))
    if (get_word_number(first_word) + get_word_number(second_word)) == get_word_number(sum_word):
        count += 1
        print("///////// Solution " + str(count) + " //////////")
        print(character_list)
        print(digit_list)
        print("\n")
        found_solution = True
    digit_list.clear()

if not found_solution:
    print("No solution was found for the equation.")




