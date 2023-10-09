import sys

n, m = map(int, input().split())
board = [list(map(int, input())) for _ in range(n)] # 붙어있는 숫자 입력을 숫자 배열로 만들기

max_len = 0 # 최대 변 길이
direction = [[-1, -1], [-1, 0], [0, -1]] # 북서, 북, 서
for i in range(n):
    for j in range(m):
        if board[i][j] == 1: # 1인 칸이면
            if max_len == 0:
                max_len = 1
            if i > 0 and j > 0:
                board[i][j] += min(board[i-1][j-1], board[i-1][j], board[i][j-1])
                max_len = max(max_len, board[i][j]) # 현재까지의 최대 변 길이 갱신

print(max_len**2) 