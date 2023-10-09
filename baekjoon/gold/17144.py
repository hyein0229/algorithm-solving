import sys
import copy

r, c, t = map(int, sys.stdin.readline().split())
board = []
air_bot = []
for i in range(r):
    row = list(map(int, sys.stdin.readline().split()))
    if -1 in row:
        air_bot.append(i) # 공기청정기 위치
    board.append(row)

direction = [[-1, 0], [0, 1], [1, 0], [0, -1]] # 북동남서 방향
def finedust_spread(board):
    temp = copy.deepcopy(board)
    for i in range(r):
        for j in range(c):
            if board[i][j] >= 0:
                sum_dust = 0
                cnt = 0
                for k in range(4): # 인접한 네 방향
                    nx = i + direction[k][0]
                    ny = j + direction[k][1]
                    if nx < 0 or nx >= r or ny < 0 or ny >= c: # 칸이 있는지 확인
                        continue

                    if board[nx][ny] >= 0: # 확산 가능한 인접한 칸
                        sum_dust += temp[nx][ny] // 5
                        cnt += 1

                board[i][j] = board[i][j] - (board[i][j] // 5) * cnt + sum_dust # 미세먼지 업데이트

def start_air_bot(board):
    x1, x2 = air_bot[0], air_bot[1]
    temp = copy.deepcopy(board)
    # 위쪽 반시계방향 순환
    for i in range(x1-2, -1, -1): # 첫번째 열 아래로 이동
        board[i+1][0] = temp[i][0]
        board[i][0] = 0
    for j in range(1, c): # 첫번째 행 왼쪽으로 이동
        board[0][j-1] = temp[0][j]
        board[0][j] = 0
    for i in range(1, x2): # 마지막 열은 위로 이동
        board[i-1][c-1] = temp[i][c-1]
        board[i][c-1] = 0
    for j in range(c-2, 0, -1): # 공기청정기가 있는 행은 오른쪽으로 이동
        board[x1][j+1] = temp[x1][j]
        board[x1][j] = 0

    # 아래쪽 시계방향 순환
    for i in range(x2+2, r): # 첫번째 열 위로 이동
        board[i-1][0] = temp[i][0]
        board[i][0] = 0
    for j in range(1, c): # 마지막 행 왼쪽 이동
        board[r-1][j-1] = temp[r-1][j]
        board[r-1][j] = 0
    for i in range(r-2, x1, -1): # 마지막 열 아래로 이동
        board[i+1][c-1] = temp[i][c-1]
        board[i][c-1] = 0
    for j in range(c-2, 0, -1): # 공기청정기가 있는 행 오른쪽으로 이동
        board[x2][j+1] = temp[x2][j]
        board[x2][j] = 0


while t:
    finedust_spread(board) # 확산
    start_air_bot(board) # 공기청정기 작동
    t -= 1 # 1초 후 

total = 0
for i in range(r):
    for j in range(c):
        if board[i][j] >= 1:
            total += board[i][j]
print(total)




