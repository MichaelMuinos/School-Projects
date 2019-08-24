import random
import string

alphabet = string.ascii_lowercase


def substitution_encrypt(plain_text, key):
    encrypted_string = ""
    for i in plain_text:
        index_of_letter = alphabet.index(i)
        encrypted_string += key[index_of_letter]
    return encrypted_string


def substitution_decrypt(cypher_text, key):
    decrypted_string = ""
    for i in cypher_text:
        index_of_letter = str(key).index(i)
        decrypted_string += alphabet[index_of_letter]
    return decrypted_string


def key_gen():
    key = list(string.ascii_lowercase)
    random.shuffle(key)
    return ''.join(key)


def test_drive(strings, key="bpzhgocvjdqswkimlutneryaxf", tag='E'):
    altered_strings = []
    for item in strings:
        if tag == 'E':
            altered_strings.append(substitution_encrypt(item, key))
        else:
            altered_strings.append(substitution_decrypt(item, key))
    return altered_strings


def print_test(original_strings, altered_strings, test_number, key="bpzhgocvjdqswkimlutneryaxf", tag="E"):
    print("/////Test " + str(test_number) + "/////")
    print("Type: Encrypt") if tag == "E" else print("Type: Decrypt")
    print("Key: " + key)

    print("Original strings: ", end='')
    for original_string in original_strings:
        print(original_string + "   ", end='')

    print("\nModified strings: ", end='')
    for altered_string in altered_strings:
        print(altered_string + "   ", end='')

    print("\n")

# Test 1
test1_strings = ["flow", "substitutioncipher"]
test1_result = test_drive(test1_strings)
print_test(test1_strings, test1_result, 1)

# Test 2
test2_key = key_gen()
test2_result = test_drive(test1_strings, test2_key)
print_test(test1_strings, test2_result, 2, test2_key)

# Test 3
test3_strings = ["osiy", "obzy", "doedlugvusu"]
test3_result = test_drive(test3_strings, "bpzhgocvjdqswkimlutneryaxf", 'D')
print_test(test3_strings, test3_result, 3, "bpzhgocvjdqswkimlutneryaxf", 'D')

# Test 4
test4_key = key_gen()
test4_result = test_drive(test3_strings, test4_key, 'D')
print_test(test3_strings, test4_result, 4, test4_key, 'D')

# /////Test 1/////
# Type: Encrypt
# Key: bpzhgocvjdqswkimlutneryaxf
# Original strings: flow   substitutioncipher
# Modified strings: osiy   teptnjnenjikzjmvgu
#
# /////Test 2/////
# Type: Encrypt
# Key: pqywnezctadrbojxklumghifvs
# Original strings: flow   substitutioncipher
# Modified strings: erji   ugqumtmgmtjoytxcnl
#
# /////Test 3/////
# Type: Decrypt
# Key: bpzhgocvjdqswkimlutneryaxf
# Original strings: osiy   obzy   doedlugvusu
# Modified strings: flow   facw   jfujqrehrlr
#
# /////Test 4/////
# Type: Decrypt
# Key: meidhpurlwkcaxnvytfbzsqojg
# Original strings: osiy   obzy   doedlugvusu
# Modified strings: xvcq   xtuq   dxbdigzpgvg