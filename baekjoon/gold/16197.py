import sys
import copy
sys.setrecursionlimit(10**7)

n, m = map(int, input().split())
board = []
coins = []
for i in range(n):
    board.append(list(sys.stdin.readline().rstrip()))
    for j in range(m):
        if board[i][j] == 'o':
            coins.append([i, j]) # 동전 위치 저장

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def backtracking(cnt):
    global answer, coins
    if len(coins) == 1: # 동전이 한 개이면
        answer = min(answer, cnt)
        return
    elif cnt == 10 or len(coins) == 0:
        return

    tmp = copy.deepcopy(coins) # 이전 동전 상태 복사
    for i in range(4): # 4가지 버튼 동작
        k = 0
        while k < len(coins): # 동전 이동
            nx = coins[k][0] + dx[i]
            ny = coins[k][1] + dy[i]
            if nx < 0 or nx >= n or ny < 0 or ny >= m: # 이동 방향에 칸이 없으면 동전 떨어짐
                coins.pop(k) # 제거
                continue
            
            if board[nx][ny] != '#': # 벽이 아니면 현재 동전 이동
                coins[k][0] = nx
                coins[k][1] = ny
            k += 1

        backtracking(cnt + 1)
        coins = copy.deepcopy(tmp) # 이전 상태로
        
answer = 11
backtracking(0)
if answer == 11:
    print(-1)
else:
    print(answer)