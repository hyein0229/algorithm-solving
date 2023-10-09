import sys
input = sys.stdin.readline

n, m = map(int, input().split())
maze = [list(map(int, input().split())) for _ in range(n)]
dx = [-1, 0, -1]
dy = [0, -1, -1]
# 올 수 있는 세 방향의 칸에서 최대 누적된 사탕 수 구하기
for i in range(n):
    for j in range(m): 
        max_c = 0
        for k in range(3):
            nx = i + dx[k]
            ny = j + dy[k]
            if 0 <= nx < n and 0 <= ny < m:
                if max_c < maze[nx][ny]:
                    max_c = maze[nx][ny]

        maze[i][j] += max_c
print(maze[n-1][m-1])