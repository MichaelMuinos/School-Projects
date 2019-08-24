import os
import queue


class BinaryNumber:
    def __init__(self, str):
        is_valid = True
        for character in str:
            if character is not '0' and character is not '1':
                is_valid = False
        if not is_valid:
            new_str = ''
            print("Uh oh, wrong input. Translating string...")
            for character in str:
                if character is '0' or character is '1':
                    new_str += character
            self.str = new_str
        else:
            self.str = str

    def value(self):
        if self.str == '':
            return None
        return int(self.str, 2)


test_one = BinaryNumber('11101')
print(test_one.value(), "\n")

test_two = BinaryNumber('')
print(test_two.value(), "\n")

test_three = BinaryNumber('01E1310x1')
print(test_three.value(), "\n")

test_four = BinaryNumber('E3x5k')
print(test_four.value(), "\n\n\n\n")


def read_file(file_name, list):
    if os.path.isfile(file_name):
        with open(file_name) as file:
            for line in file:
                list.append(str(line))
    else:
        print("File does not exist.")
    return list


username_file_path = "names.txt"
password_file_path = "passwords.txt"

usernames = read_file(username_file_path, list())
passwords = read_file(password_file_path, list())
database = dict(zip(usernames,passwords))

# transform passwords to be only digits or letters
keys = list(database.keys())
for key in keys:
    val = str(database.get(key))
    new_val = ''
    for character in val:
        if character.isdigit() or character.isalpha():
            new_val += character
    database[key] = new_val
    new_val = ''

q = queue.Queue()
q.put("Jim")
q.put("Terry")
q.put("Carter")

user = input("Enter a username: ")
pw = input("Enter a password: ")
if user in database:
    if database.get(user) == pw:
        q.put("Tim")
        q.get("Jim")
        print("Placing inside the queue.")
    else:
        print("Incorrect password.")
else:
    print("Incorrect username.")
