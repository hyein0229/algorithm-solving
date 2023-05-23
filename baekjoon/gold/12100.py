import sys
from copy import deepcopy

N = int(input())
board = []
for _ in range(N):
    board.append(list(map(int, sys.stdin.readline().split())))

def move_blocks(board, dir):
    if dir == 0: # 상
        for j in range(N): # 첫열부터 순서대로
            p = 0
            for i in range(1, N): # 위 -> 아래 순서로
                if board[i][j] > 0: # 블록이 있으면
                    cur_block = board[i][j]
                    board[i][j] = 0
                    if board[p][j] == 0: # 빈칸이면 블록 이동
                        board[p][j] = cur_block
                    elif board[p][j] == cur_block: # 같은 숫자의 블록이면 합치기
                        board[p][j] *= 2
                        p += 1
                    else: # 서로 다른 숫자의 블록이면
                        p += 1
                        board[p][j] = cur_block
                        
    elif dir == 1: # 하
        for j in range(N): 
            p = N-1
            for i in range(N-2, -1, -1): # 아래열부터 차례대로 탐색
                if board[i][j] > 0: 
                    cur_block = board[i][j]
                    board[i][j] = 0
                    if board[p][j] == 0: 
                        board[p][j] = cur_block
                    elif board[p][j] == cur_block: 
                        board[p][j] *= 2
                        p -= 1
                    else: 
                        p -= 1
                        board[p][j] = cur_block
    elif dir == 2: # 좌
        for i in range(N): # 첫행부터 순서대로
            p = 0
            for j in range(1, N): # 왼쪽 -> 오른쪽열 순서로 탐색
                if board[i][j] > 0:
                    cur_block = board[i][j]
                    board[i][j] = 0
                    if board[i][p] == 0:
                        board[i][p] = cur_block
                    elif board[i][p] == cur_block: 
                        board[i][p] *= 2
                        p += 1
                    else: 
                        p += 1
                        board[i][p] = cur_block
    else: # 우
        for i in range(N): 
            p = N-1
            for j in range(N-2, -1, -1): # 오른쪽 -> 왼쪽열 순서로 탐
                if board[i][j] > 0: 
                    cur_block = board[i][j]
                    board[i][j] = 0
                    if board[i][p] == 0: 
                        board[i][p] = cur_block
                    elif board[i][p] == cur_block: 
                        board[i][p] *= 2
                        p -= 1
                    else: 
                        p -= 1
                        board[i][p] = cur_block
        
    return board

def dfs(board, cnt):
    global answer
    if cnt == 5:
        for i in range(N):
            for j in range(N):
                answer = max(answer, board[i][j])
        return
    for i in range(4): # 상하좌우 한 번씩 모두 탐색
        after_board = move_blocks(deepcopy(board), i)
        dfs(after_board, cnt+1)

answer = 0
dfs(board, 0)
print(answer)