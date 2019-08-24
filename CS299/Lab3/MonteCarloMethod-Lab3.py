import random as rand
import time
import math

test_cases = [10000, 100000, 1000000]


def approximate_pi(iterations):
    start_time = time.time()
    inside_circle = 0.0
    for _ in range(iterations):
        x_value = float((rand.uniform(0,1)) * 2 - 1.0);
        y_value = float((rand.uniform(0,1)) * 2 - 1.0);

        if is_inside_boundaries(x_value, y_value):
            inside_circle += 1

    pi = float((4.0 * (inside_circle / iterations)))
    execution_time = time.time() - start_time
    print("Value of N: " + str(iterations))
    print("PI: " + str(pi))
    print("Time to execute (seconds): " + str(execution_time) + "\n")


def is_inside_boundaries(x, y):
    distance = math.sqrt((x ** 2) + (y ** 2))
    if distance < 1.0:
        return True
    else:
        return False


# test each case 3 times
for case in test_cases:
    for _ in range(3):
        approximate_pi(case)