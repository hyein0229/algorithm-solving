import sys
from collections import deque
from copy import deepcopy

# pypy3 통과

N, M = map(int, input().split())
iceberg = []
for _ in range(N):
    iceberg.append(list(map(int, sys.stdin.readline().split())))
    
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

def bfs(x, y):
    Q = deque([(x, y)])
    visited[x][y] = True

    while Q:
        cx, cy = Q.popleft()
        for k in range(4):
            nx = cx + dx[k]
            ny = cy + dy[k]

            if 0 <= nx < N and 0 <= ny < M:
                if not visited[nx][ny] and iceberg[nx][ny] > 0 :
                    Q.append((nx, ny))
                    visited[nx][ny] = True

years = 0
while True:
    visited = [[False for _ in range(M)] for _ in range(N)]
    cnt = 0
    isEnd = True
    
    # 빙산 덩어리 개수 세기
    for i in range(1, N-1):
        for j in range(1, M-1):
            if not visited[i][j] and iceberg[i][j] > 0:
                bfs(i, j)
                cnt += 1
                isEnd = False

    if isEnd:
        print(0)
        break
                
    if cnt >= 2:
        print(years)
        break

    # 녹은 빙산 업데이트
    tmp_iceberg = deepcopy(iceberg)
    for i in range(1, N-1):
        for j in range(1, M-1):
            if iceberg[i][j] > 0:
                sea = 0
                for k in range(4):
                    nx = i + dx[k]
                    ny = j + dy[k]
                    if 0 <= nx < N and 0 <= ny < M:
                        if tmp_iceberg[nx][ny] == 0:
                            sea += 1
                            
                iceberg[i][j] = max(0, iceberg[i][j] - sea)

    years += 1