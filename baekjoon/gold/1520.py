import sys

M, N = map(int, input().split())
map_height = []
for _ in range(M):
    map_height.append(list(map(int, sys.stdin.readline().split())))

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]
def dfs(x, y):
    if x == M-1 and y == N-1:
        return 1
  
    if dp[x][y] != -1:
        return dp[x][y]

    dp[x][y] = 0
    
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]

        if 0 <= nx < M and 0 <= ny < N:
            if map_height[nx][ny] < map_height[x][y]:
                dp[x][y] += dfs(nx, ny)
                
    return dp[x][y]

dp = [[-1 for _ in range(N)] for _ in range(M)]
print(dfs(0, 0))