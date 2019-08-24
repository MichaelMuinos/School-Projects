names = ['joe', 'tom', 'barb', 'sue', 'sally']
scores = [10, 17, 20, 18, 15]
ages = [20, 17, 19, 18, 22]


def make_dict(name_list, score_list):
    return dict(zip(name_list, score_list))


def make_set(name_list, score_list):
    return set(zip(name_list, score_list))


def make_frozen_set(name_list, age_list):
    return frozenset(zip(name_list, age_list))


def find_intersection(name_set, name_frozenset):
    return name_set.intersection(name_frozenset)


def get_median_score(score_dict):
    score_list = list(dict(score_dict).values())
    score_list.sort()
    if len(score_list) % 2 == 0:
        return (score_list[len(score_list) // 2] + score_list[(len(score_list) // 2) - 1]) / 2
    else:
        return score_list[len(score_list) // 2]


def get_score(dictionary, name):
    if name not in dict(dictionary):
        print(name + " is not in the dictionary!")
        return -1
    else:
        return dict(dictionary)[name]


# part 1
print("PART 1")
print("Dictionary: ", end='')
score_dict = make_dict(names, scores)
print(score_dict)
print("\n")

# part 2
print("PART 2")
print("Set: ", end='')
name_set = make_set(names, scores)
print(name_set)

print("Frozen Set: ", end='')
name_frozenset = make_frozen_set(names, ages)
print(name_frozenset)

print("Intersection: ", end='')
print(find_intersection(name_set, name_frozenset))
print("\n")

# part 3
print("PART 3")
score_dict['john'] = 19  # add new entry 'john'
# adding a new entry sally will update key 'sue' to be 20
score_dict['sally'] = 19 # update 'sally' to be 19
del score_dict['tom']    # delete 'tom'

print("Names\tScores")
score_dict = sorted(score_dict.items())
for item in score_dict:
    print(item[0] + "\t\t" + str(item[1]))
print("\n")

# part 4
print("PART 4")
print("Median Score: " + str(get_median_score(score_dict)))
print("\n")

# part 5
print("PART 5")
print("Testing for 'ana': ")
print(get_score(score_dict, 'ana'))
print("Testing for 'barb: ")
print(get_score(score_dict, 'barb'))