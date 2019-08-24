class Fraction:

    def __init__(self, numerator, denominator):
        self.numerator = numerator
        self.denominator = denominator

    def simplify(self):
        multiple = -1
        higher_num = self.numerator if self.numerator < self.denominator else self.denominator
        for i in range(1, higher_num + 1):
            if i != 1 and self.denominator % i == 0 and self.numerator % i == 0:
                multiple = i

        if multiple > 0:
            self.numerator //= multiple
            self.denominator //= multiple

    def __add__(self, other):
        self.print_fraction()
        print(" + ", end='')
        self.print_other_fraction(other)

        num = self.denominator
        self.numerator *= other.denominator
        other.numerator *= num
        self.numerator += other.numerator
        self.denominator *= other.denominator
        self.simplify()

        self.print_result()
        return Fraction(self.numerator, self.denominator)

    def __eq__(self, other):
        self.print_fraction()
        print(" == ", end='')
        self.print_other_fraction(other)
        print(" = ", (self.numerator / self.denominator) == (other.numerator / other.denominator))
        return (self.numerator / self.denominator) == (other.numerator / other.denominator)

    def __mul__(self, other):
        self.print_fraction()
        print(" * ", end='')
        self.print_other_fraction(other)

        self.numerator *= other.numerator
        self.denominator *= other.denominator
        self.simplify()

        self.print_result()
        return Fraction(self.numerator, self.denominator)

    def __lt__(self, other):
        self.print_fraction()
        print(" < ", end='')
        self.print_other_fraction(other)
        print(" = ", (self.numerator / self.denominator) < (other.numerator / other.denominator))
        return (self.numerator / self.denominator) < (other.numerator / other.denominator)

    def print_other_fraction(self, other):
        print(other.numerator, "/", other.denominator, end='')

    def print_fraction(self):
        print(self.numerator, "/", self.denominator, end='')

    def print_result(self):
        print(" = ", self.numerator, "/", self.denominator)

# Test Cases
Fraction(2, 15) + Fraction(1, 5)
Fraction(3, 14) + Fraction(4, 7)
Fraction(2, 15) * Fraction(3, 4)
Fraction(5, 7) * Fraction(4, 35)
Fraction(2, 15) == Fraction(4, 25)
Fraction(2, 15) == Fraction(4, 30)
Fraction(2, 15) < Fraction(4, 25)
Fraction(9, 15) < Fraction(7, 12)