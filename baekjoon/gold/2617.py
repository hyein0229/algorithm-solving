import sys

def dfs(x, y, c):
    visited[y] = 1 # 방문 여부
    result[x][y] = c

    for k in range(1, n+1):
        if y == k:
            continue
        if result[y][k] == c and not visited[k]:
            dfs(x, k, c)

n, m = map(int, input().split())
result = [[0] * (n+1) for _ in range(n+1)]
for _ in range(m):
    a, b = map(int, sys.stdin.readline().split())
    result[a][b] = 1 # 무겁다
    result[b][a] = -1 # 가볍다

for i in range(1, n+1): # 첫 번째 구슬부터 탐색
    visited = [0] * (n+1)
    for j in range(1, n+1):
        if i == j: # 자기 자신
            continue
        if result[i][j] != 0 and not visited[j]: 
            dfs(i, j, result[i][j])

answer = 0
for i in range(1, n+1):
    heavy = 0
    light = 0
    for j in range(1, n+1):
        if result[i][j] == 1:
            heavy += 1
        elif result[i][j] == -1:
            light += 1
    if heavy >= n//2+1 or light >= n//2+1:
        answer += 1
print(answer)