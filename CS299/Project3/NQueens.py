def create_board(n):
    board = []
    for row in range(n):
        board.append([])
        for column in range(n):
            board[row].append('0')
    return board


def print_board(board):
    for row in board:
        # print space in between numbers
        print(" ".join(row))


# recursively call put_queen and change queen position if queen_present
# returns false
def put_queen(board, queen, size):
    if queen == size:
        return True
    for place in range(size):
        if queen_present(board, place, queen):
            board[place][queen] = '1'
            # shift queen position
            if put_queen(board, queen+1, size):
                return True
            board[place][queen] = '0'
    return False


# based on backtracking algorithm, check if
# [row][column] is valid spot for queen
def queen_present(board, row, column):
    # check if queen present
    for i in range(column):
        if board[row][i] == '1':
            return False
    # check diagonal
    for i, j in zip(range(row, -1, -1), range(column, -1, -1)):
        if board[i][j] == '1':
            return False
    # check diagonal
    for i, j in zip(range(row, len(board), 1), range(column, -1, -1)):
        if board[i][j] == '1':
            return False
    # current position is a valid spot for queen
    return True


def test_board(n):
    board = create_board(n)
    if put_queen(board, 0, n):
        print("N = " + str(n))
        print_board(board)
        print("\n")


for i in range(3, 11):
    test_board(i)