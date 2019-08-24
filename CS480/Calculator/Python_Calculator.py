# Michael Muinos
# CS 480.01

from tkinter import *

'''
The following class constructs and builds a graphical user interface of a calculator
in which has the functionality to add, subtract, multiply, divide, take the modulus, add an exponent,
and enter a decimal. The calculator has error checking; for
example, if the user divides by '0', it will return an error instead of the program crashing. Additionally,
if the user divides and the result is longer than 3 decimal places or is a
negative number, it will round to the 3rd decimal place. Lastly,
if the user adds any preceding zeros to a number (other than zero itself)
or if they only enter the multiplication, division, etc. symbols and
click equal, it will return an error.
'''


class NewCalculator:

    # This is the constructor method in which creates the GUI for the calculator. It includes a 2 Frames, 3 Labels,
    # and multiple Buttons. It is split top and bottom between 2 frames in which both use a grid to place the widgets.
    # The top frame holds the 3 labels, while the bottom frame holds a grid of all the buttons.

    def __init__(self,window):
        # Initialize top frame
        frame = Frame(window)
        frame.grid()

        # Initialize bottom frame
        frame_grid = Frame(window)
        frame_grid.grid()

        # Added to top frame. This is where the equation is displayed to the user.
        self.equation_label = Label(frame,text="",width=17,relief=RIDGE,bd=10)
        self.equation_label.grid(row=0,column=0)

        # Added to the top frame, to the right. This is where the answer to the equation is displayed.
        self.answer_label = Label(frame,text="0",width=11,relief=RIDGE,bd=10)
        self.answer_label.grid(row=0,column=1)

        # Added to the top frame, below the other labels. This is where I give myself credit :)
        self.credit_label = Label(frame,text="Developed by Michael Muinos",fg="red")
        self.credit_label.grid(row=1,columnspan=2)

        # All Buttons below are initialized in the bottom frame in a grid like fashion. Each button represents
        # an individual symbol that can be added to the equation label to be solved. They each call there
        # proper button click function.

        # Creates button in row 0, column 0.
        self.button_number7 = Button(frame_grid,text="7",height=2,width=7,fg="black",overrelief=RIDGE,command=self.button_number7_click)
        self.button_number7.grid(row=0,column=0)

        # Creates button in row 0, column 1.
        self.button_number8 = Button(frame_grid,text="8",height=2,width=7,fg="black",overrelief=RIDGE,command=self.button_number8_click)
        self.button_number8.grid(row=0,column=1)

        # Creates button in row 0, column 2.
        self.button_number9 = Button(frame_grid,text="9",height=2,width=7,fg="black",overrelief=RIDGE,command=self.button_number9_click)
        self.button_number9.grid(row=0,column=2)

        # Creates button in row 0, column 3.
        self.button_divide = Button(frame_grid,text="/",height=2,width=7,fg="blue",overrelief=RIDGE,command=self.button_divide_click)
        self.button_divide.grid(row=0,column=3)

        # Creates button in row 1, column 0.
        self.button_number6 = Button(frame_grid,text="6",height=2,width=7,fg="black",overrelief=RIDGE,command=self.button_number6_click)
        self.button_number6.grid(row=1,column=0)

        # Creates button in row 1, column 1.
        self.button_number5 = Button(frame_grid,text="5",height=2,width=7,fg="black",overrelief=RIDGE,command=self.button_number5_click)
        self.button_number5.grid(row=1,column=1)

        # Creates button in row 1, column 2.
        self.button_number4 = Button(frame_grid,text="4",height=2,width=7,fg="black",overrelief=RIDGE,command=self.button_number4_click)
        self.button_number4.grid(row=1,column=2)

        # Creates button in row 1, column 3.
        self.button_multiply = Button(frame_grid,text="*",height=2,width=7,fg="blue",overrelief=RIDGE,command=self.button_multiply_click)
        self.button_multiply.grid(row=1,column=3)

        # Creates button in row 2, column 0.
        self.button_number3 = Button(frame_grid,text="3",height=2,width=7,fg="black",overrelief=RIDGE,command=self.button_number3_click)
        self.button_number3.grid(row=2,column=0)

        # Creates button in row 2, column 1.
        self.button_number2 = Button(frame_grid,text="2",height=2,width=7,fg="black",overrelief=RIDGE,command=self.button_number2_click)
        self.button_number2.grid(row=2,column=1)

        # Creates button in row 2, column 2.
        self.button_number1 = Button(frame_grid,text="1",height=2,width=7,fg="black",overrelief=RIDGE,command=self.button_number1_click)
        self.button_number1.grid(row=2,column=2)

        # Creates button in row 2, column 3.
        self.button_subtract = Button(frame_grid,text="-",height=2,width=7,fg="blue",overrelief=RIDGE,command=self.button_subtract_click)
        self.button_subtract.grid(row=2,column=3)

        # Creates button in row 3, column 0.
        self.button_number0 = Button(frame_grid,text="0",height=2,width=16,fg="black",overrelief=RIDGE,command=self.button_number0_click)
        self.button_number0.grid(row=3,column=0,columnspan=2)

        # Creates button in row 3, column 2.
        self.button_decimal = Button(frame_grid,text=".",height=2,width=7,fg="blue",overrelief=RIDGE,command=self.button_decimal_click)
        self.button_decimal.grid(row=3,column=2)

        # Creates button in row 3, column 3.
        self.button_plus = Button(frame_grid,text="+",height=2,width=7,fg="blue",overrelief=RIDGE,command=self.button_plus_click)
        self.button_plus.grid(row=3,column=3)

        # Creates button in row 4, column 0.
        self.button_redo = Button(frame_grid,text="C",height=2,width=7,fg="red",overrelief=RIDGE,command=self.button_redo_click)
        self.button_redo.grid(row=4,column=0)

        # Creates button in row 4, column 1.
        self.button_equals = Button(frame_grid,text="=",height=2,width=7,fg="red",overrelief=RIDGE,command=self.button_equals_click)
        self.button_equals.grid(row=4,column=1)

        # Creates button in row 4, column 2.
        self.button_exponent = Button(frame_grid,text="Exp",height=2,width=7,fg="blue",overrelief=RIDGE,command=self.button_exponent_click)
        self.button_exponent.grid(row=4,column=2)

        # Creates button in row 4, column 3.
        self.button_modulus = Button(frame_grid,text="%",height=2,width=7,fg="blue",overrelief=RIDGE,command=self.button_modulus_click)
        self.button_modulus.grid(row=4,column=3)

    # The following method calculates the equation that is typed in the equation label space. The main function of
    # the method is used through the eval() method that is built in to python 3. It is able to take a string representation
    # of the equation and convert it to its integer/double equivalent. This is also where any error checking will occur.
    def calculate_equation(self,equation):
        try:
            if(str(equation).endswith("*") or str(equation).endswith("/")
               or str(equation).endswith("-") or str(equation).endswith("+")
               or str(equation).endswith("%")):
                return "ERROR"
            if(str(eval(equation)).isdecimal() == False):
                temp = format(eval(equation),'.3f')
                return temp
            number_value = eval(equation)
        except ZeroDivisionError:
            number_value = "ERROR"
        except SyntaxError:
            return "ERROR"
        return number_value

    # This method displays the appended (new) version of the equation label. It will add to whatever has been recently
    # displayed.
    def append_equation_label(self,string):
        append_text = self.equation_label.cget("text") + string
        self.equation_label.config(text=append_text)

    # Appends '7' to the equation label text.
    def button_number7_click(self):
        self.append_equation_label("7")

    # Appends '8' to the equation label text.
    def button_number8_click(self):
        self.append_equation_label("8")

    # Appends '9' to the equation label text.
    def button_number9_click(self):
        self.append_equation_label("9")

    # Appends '/' to the equation label text.
    def button_divide_click(self):
        self.append_equation_label("/")

    # Appends '6' to the equation label text.
    def button_number6_click(self):
        self.append_equation_label("6")

    # Appends '5' to the equation label text.
    def button_number5_click(self):
        self.append_equation_label("5")

    # Appends '4' to the equation label text.
    def button_number4_click(self):
        self.append_equation_label("4")

    # Appends '*' to the equation label text.
    def button_multiply_click(self):
        self.append_equation_label("*")

    # Appends '3' to the equation label text.
    def button_number3_click(self):
        self.append_equation_label("3")

    # Appends '2' to the equation label text.
    def button_number2_click(self):
        self.append_equation_label("2")

    # Appends '1' to the equation label text.
    def button_number1_click(self):
        self.append_equation_label("1")

    # Appends '-' to the equation label text.
    def button_subtract_click(self):
        self.append_equation_label("-")

    # Appends '0' to the equation label text.
    def button_number0_click(self):
        self.append_equation_label("0")

    # Appends '+' to the equation label text.
    def button_plus_click(self):
        self.append_equation_label("+")

    # Appends '**' to the equation label text.
    def button_exponent_click(self):
        self.append_equation_label("**")

    # Appends '%' to the equation label text.
    def button_modulus_click(self):
        self.append_equation_label("%")

    # Appends '.' to the equation label text.
    def button_decimal_click(self):
        self.append_equation_label(".")

    # Resets the equation label text to be empty, or "". The last answer label is also set to '0'.
    def button_redo_click(self):
        self.equation_label.config(text="")
        self.answer_label.config(text="0")

    # This method takes the current text in the equation label and updates the answer label text to be the answer.
    def button_equals_click(self):
        text = self.equation_label.cget("text")
        answer = self.calculate_equation(text)
        str(self.append_answer_label(answer))

    # Changes the answer label text of the equation that was entered by the user.
    def append_answer_label(self,number):
        self.answer_label.config(text=number)

# Creates the main window and sets the title and passes it to the class NewCalculator. It disallows the option to
# resize the window and the mainloop() function displays the window forever, until the user exits.
root = Tk()
root.title("Basic Calculator")
root.resizable(width=FALSE,height=FALSE)
calculator = NewCalculator(root)
root.mainloop()
