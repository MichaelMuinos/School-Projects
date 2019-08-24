import random as rand

choices = ["rock", "paper", "scissors"]


def get_user_choice():
    message = "Choose the number for your choice.\n1. Rock\n2. Paper\n3. Scissors\n"
    user_choice = int(input(message))

    if user_choice < 1 or user_choice > 3:
        print("Incorrect option, please try again.\n")
        get_user_choice()
    else:
        return choices[user_choice - 1]


def get_computer_choice():
    return choices[rand.randint(0,2)]


def evaluateChoices(choice_one, choice_two):
    if choice_one == choice_two:
        return "tie"
    elif(choice_one == "rock" and choice_two == "paper"
       or choice_one == "paper" and choice_two == "rock"):
        return "paper"
    elif(choice_one == "rock" and choice_two == "scissors"
         or choice_one == "scissors" and choice_two == "rock"):
        return "rock"
    else:
        return "scissors"


def declare_winner(computer_choice, user_choice):
    print("Computer choice: " + computer_choice)
    print("Player choice: " + user_choice)
    winning_choice = evaluateChoices(computer_choice, user_choice)
    if winning_choice == "tie":
        print("Tie!\n\n")
    elif computer_choice == winning_choice:
        print("Computer won!\n\n")
    else:
        print("Player won!\n\n")

# Perform 7 tests
for i in range(7):
    declare_winner(get_computer_choice(), get_user_choice())

