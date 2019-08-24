def compare_lists(list1, list2):
    if len(list1) != len(list2):
        print("Error: Lengths are not the same.")
    else:
        maximum_with_one = None
        tuple_list = []
        for a, b in zip(list1, list2):
            tuple_list.append((a, b))
            if b == 1:
                if maximum_with_one == None:
                    maximum_with_one = a
                elif a > maximum_with_one:
                    maximum_with_one = a
        print("Maximum value with tag 1: " + str(maximum_with_one))
        tuple_list.sort(key=lambda x: x[0])
        print("Sorted tuples: ", end='')
        print(tuple_list)
        print("\n")

l1_1 = [13, 3, 25, 7, 21, 2, 50, 2, 13, 40, 34, 14, 6, 16]
l1_2 = [1, -1, 1, 1, -1, 1, -1, 1, 1, -1, 1, -1, 1, -1]
compare_lists(l1_1, l1_2)

l2_1 = [45, 21, 4, 31, 2]
l2_2 = [1, 1, 1, 1, 1]
compare_lists(l2_1, l2_2)

l3_1 = [13, 3, 45]
l3_2 = [-1, -1, -1]
compare_lists(l3_1, l3_2)