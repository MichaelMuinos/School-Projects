import turtle

zipcodes = ["55555-1237", "91768-1234", "20500-0000"]
barcodes = {0 : "11000", 1: "00011", 2 : "00101",
            3 : "00110", 4: "01001", 5: "01010",
            6 : "01100", 7: "10001", 8: "10010",
            9 : "10100"}
pen_x_position = 0
pen_y_position = 0

figure = turtle.Turtle()
figure.pensize(1.5)
figure.left(90)


def create_short_line():
    global pen_x_position
    global figure
    figure.forward(15)
    figure.penup()
    figure.setposition(pen_x_position + 4, pen_y_position)
    figure.pendown()
    pen_x_position = figure.position()[0]


def create_long_line():
    global pen_x_position
    global figure
    figure.forward(30)
    figure.penup()
    figure.setposition(pen_x_position + 4, pen_y_position)
    figure.pendown()
    pen_x_position = figure.position()[0]


def move_turtle_down():
    global pen_x_position
    global pen_y_position
    global figure
    figure.penup()
    figure.setposition(0, pen_y_position - 100)
    figure.pendown
    pen_x_position = figure.position()[0]
    pen_y_position = figure.position()[1]


def calculate_check_digit(zipcode):
    sum = 0
    for i in zipcode:
        sum += int(i)
    return str((10 - sum) % 10)


def draw_barcode(zipcode):
    create_long_line()
    modified_zip = str(zipcode).replace("-","")
    modified_zip += calculate_check_digit(modified_zip)
    print(", Check digit: " + modified_zip[len(modified_zip) - 1])
    for num in modified_zip:
        for character in barcodes[int(num)]:
            if character == "0":
                create_short_line()
            else:
                create_long_line()
    create_long_line()
    move_turtle_down()

for zipcode in zipcodes:
    print("Drawing barcode for " + zipcode, end='')
    draw_barcode(zipcode)
print("Done.")
turtle.done()

