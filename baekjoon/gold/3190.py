import sys
from collections import deque

N = int(input())
K = int(input())
board = [[0] * N for _ in range(N)]

for _ in range(K):
    i,j = map(int, sys.stdin.readline().split())
    board[i-1][j-1] = 1

L = int(input())
dic = dict()
for _ in range(L):
    t, d = input().split()
    dic[int(t)] = d

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
def change_direction(cur_d, next_d):
    global direction
    if (cur_d == 3 and next_d == 'L') or (cur_d == 2 and next_d == 'D'):
        direction = 0
    elif (cur_d == 3 and next_d == 'D') or (cur_d == 2 and next_d == 'L'):
        direction = 1
    elif (cur_d == 0 and next_d == 'L') or (cur_d == 1 and next_d == 'D'):
        direction = 2
    else:
        direction = 3
        
    
x, y = 0, 0
board[0][0] = 2 # snake position
direction = 3
time = 0
Q = deque([(0, 0)])

while True:
    time += 1
    x += dx[direction]
    y += dy[direction]

    if x < 0 or x >= N or y < 0 or y >= N:
        print(time)
        break

    if board[x][y] == 1: # apple
        board[x][y] = 2
        Q.append((x, y))
        if time in dic:
            change_direction(direction, dic[time])
            
    elif board[x][y] == 0: # empty
        board[x][y] = 2
        Q.append((x, y))
        tail_x, tail_y = Q.popleft()
        board[tail_x][tail_y] = 0
        if time in dic:
            change_direction(direction, dic[time])
    else: # snake body
        print(time)
        break