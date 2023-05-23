import sys

n = int(input())
forest = []
for _ in range(n):
    forest.append(list(map(int, sys.stdin.readline().split())))

dp = [[0] * n for _ in range(n)]

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

sys.setrecursionlimit(10**7)
def dfs(x, y):

    if dp[x][y] != 0:
        return dp[x][y]

    dp[x][y] = 1

    for k in range(4):
        nx, ny = x + dx[k], y + dy[k]
        if 0 <= nx < n and 0 <= ny < n:
            if forest[nx][ny] > forest[x][y]:
                dp[x][y] = max(dp[x][y], dfs(nx, ny) + 1)

                
    return dp[x][y]

answer = 0
for i in range(n):
    for j in range(n):
        answer = max(answer, dfs(i, j))

print(answer)