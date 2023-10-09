import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**7)

N, M = map(int, input().split())
board = []
for i in range(N):
    board.append(list(map(int, input().split())))
visited = [[0] * M for _ in range(N)]

dx = [0, 0, -1, 1, -1, 1, -1, 1]
dy = [-1, 1, 0, 0, -1, 1, 1, -1]
def dfs(x, y):
    global is_peak
    visited[y][x] = 1

    for i in range(8): # 인접한 격자
        nx = x + dx[i]
        ny = y + dy[i]
        if ny < 0 or ny >= N or nx < 0 or nx >= M:
            continue
        if board[ny][nx] > board[y][x]: # 인접한 격자보다 높이가 작으면 산봉우리가 아님
            is_peak = False
            continue

        if not visited[ny][nx] and board[ny][nx] == board[y][x]: # 같은 높이의 산봉우리 탐색
            dfs(nx, ny)

is_peak = False # 산봉우리 여부
answer = 0
for j in range(N):
    for k in range(M):
        if board[j][k] > 0 and not visited[j][k]:
            is_peak = True
            dfs(k, j)

            if is_peak: # 산봉우리가 맞으면 카운트
                answer += 1
            
print(answer) 