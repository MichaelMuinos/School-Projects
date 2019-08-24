class Rectangle:

    def __init__(self, length, width):
        self.length = length
        self.width = width

    def get_length(self):
        return self.length

    def set_length(self, length):
        self.length = length

    def get_width(self):
        return self.width

    def set_width(self, width):
        self.width = width

    def get_area(self):
        return self.width * self.length


class Cube(Rectangle):

    def __init__(self, length, width, height):
        Rectangle.__init__(self, length, width)
        self.height = height

    def get_height(self):
        return self.height

    def set_height(self, height):
        self.height = height

    def get_volume(self):
        return self.height * Rectangle.get_area(self)


# Test cases
rectangle = Rectangle(4, 9)
print("Area of Rectangle: " + str(rectangle.get_area()))

cube = Cube(4, 9, 5)
print("Volume of Cube: " + str(cube.get_volume()))

cube2 = Cube(4, 3, 5)
print("Volume of Cube2: " + str(cube2.get_volume()))

