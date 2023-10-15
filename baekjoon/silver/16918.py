import sys
input = sys.stdin.readline

'''
문제: 16918 봄버맨
난이도: Silver 1
알고리즘: 구현
풀이 방법: 3초 동안의 각 행동에 따라 차근차근 구현만 해주면 풀 수 있다.
        2초 상태에서 폭탄이 설치되고 그 다음 1초 후 폭탄이 터지면 남아있는 폭탄의 상태는 다시 2초 상태가 된다는 것만 생각하면 된다.
'''
def make_bomb():
    board.clear()
    for _ in range(r):
        board.append(['O'] * c)

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]
def bomb(targets):
    for bomb in targets:
        x, y = bomb
        board[x][y] = '.'
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if nx < 0 or nx >= r or ny < 0 or ny >= c:
                continue

            if board[nx][ny] == 'O':
                board[nx][ny] = '.'

def next_targets():
    next_targets = []
    for i in range(r):
        for j in range(c):
            if board[i][j] == 'O':
                next_targets.append((i, j))
    return next_targets

        
r, c, n = map(int, input().split())
board = []
targets = [] # 폭탄이 있는 위치
for i in range(r):
    row = list(input().rstrip())
    for j in range(c):
        if row[j] == 'O':
            targets.append((i, j))
    board.append(row)

time = 1
status = 1
while time <= n:
    if status == 1:
        status += 1
    elif status == 2:
        make_bomb()
        status += 1
    elif status == 3:
        bomb(targets)
        targets = next_targets()
        status = 2

    time += 1

for i in range(r):
    for j in range(c):
        print(board[i][j], end='')
    print()