import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**7)

'''
문제: 20058 마법사 상어와 파이어스톰
난이도: Gold 3
알고리즘: 구현, 그래프 탐색, dfs/bfs
풀이 방법: 순서대로 구현하면 풀리는 문제이다. 시계방향 회전에서 생각이 필요했고, 배열 회전의 경우 자주 나오니까 아예 공식을 외워두면 좋을 듯
        시간 초과 문제가 있었는데 처음 구현 시 tmp 2차원 배열을 board 를 복사하여 선언을 해주었는 데 복사하지 않고 0 초기화하는 것으로 고치니까 해결되었다.
'''
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

def rotate(l, board):
    size = 2**l # 격자 사이즈
    tmp = [[0] * (2**N) for _ in range(2**N)] 

    # 시계방향 90도 회전
    for i in range(0, 2**N, size): # 행
        for j in range(0, 2**N, size): # 열
            for k in range(size):
                for t in range(size):
                    tmp[i + t][j + size-1-k] = board[i + k][j + t] 

    return tmp

def melt(board):
    tmp = [[0] * (2**N) for _ in range(2**N)] 
    for i in range(2**N):
        for j in range(2**N):
            if board[i][j] == 0:
                continue

            cnt = 0 # 얼음이 있는 칸 카운트
            for k in range(4): # 상하좌우 인접한 칸
                nx = i + dx[k]
                ny = j + dy[k]
                if nx < 0 or nx >= 2**N or ny < 0 or ny >= 2**N:
                    continue

                if board[nx][ny] >= 1: # 얼음이 있는 칸이면
                    cnt += 1

            if cnt < 3: # 얼음 3개 이상과 인접하지 않으면 감소
                tmp[i][j] = board[i][j] - 1
            else:
                tmp[i][j] = board[i][j]
    return tmp

def dfs(x, y):
    global cnt
    visited[x][y] = 1
    cnt += 1
    
    for k in range(4):
        nx = x + dx[k]
        ny = y + dy[k]
        if nx < 0 or nx >= 2**N or ny < 0 or ny >= 2**N:
            continue

        if not visited[nx][ny] and board[nx][ny] >= 1:
            dfs(nx, ny)

N, Q = map(int, input().split())
board = []
for _ in range(2**N):
    board.append(list(map(int, input().split())))
arr = list(map(int, input().split()))

# Q번 시전하기
for l in arr:
    board = rotate(l, board) # 격자 시계방향 회전
    board = melt(board) # 얼음 감소

# 얼음 덩어리 구하기
max_cnt = 0 # 가장 큰 덩어리가 차지하는 칸의 개수
cnt = 0
total = 0 # 남아있는 얼음의 총 합
visited = [[0] * (2**N) for _ in range(2**N)]
for i in range(2**N):
    total += sum(board[i])
    for j in range(2**N):
        if board[i][j] >= 1: # 얼음이 있는 칸이면 얼음 덩어리 탐색
            dfs(i, j)
            max_cnt = max(max_cnt, cnt)
            cnt = 0

print(total)
print(max_cnt)