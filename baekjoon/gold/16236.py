import sys
from collections import deque

N = int(input())
area = []
shark_x, shark_y = 0, 0
shark_size = 2
for i in range(N):
    lst = list(map(int, sys.stdin.readline().split()))
    area.append(lst)
    for j in range(N):
        if lst[j] == 9:
            shark_x = i
            shark_y = j
            area[shark_x][shark_y] = 0
answer = 0
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

def bfs(x, y, w):
    Q = deque([(x, y)])
    dist = [[-1] * N for _ in range(N)]
    dist[x][y] = 0
    
    while Q:
        cx, cy = Q.popleft()
        for i in range(4):
            nx = cx + dx[i]
            ny = cy + dy[i]
            if 0 <= nx < N and 0 <= ny < N:
                if dist[nx][ny] == -1 and area[nx][ny] <= w:
                    dist[nx][ny] = dist[cx][cy] + 1
                    Q.append((nx, ny))
            
    return dist

def find_fish(dist):
    x, y, min_d = 0, 0, 1e9

    for i in range(N):
        for j in range(N):
            if dist[i][j] != -1 and 1 <= area[i][j] < shark_size:
                if min_d > dist[i][j]:
                    min_d = dist[i][j]
                    x, y = i, j
    if min_d == 1e9:
        return None
    
    return x, y, min_d


eat_cnt = 0
while True:
    target = find_fish(bfs(shark_x, shark_y, shark_size))

    if target == None:
        print(answer)
        break

    shark_x, shark_y = target[0], target[1]
    area[shark_x][shark_y] = 0
    answer += target[2]
    eat_cnt += 1
    if eat_cnt == shark_size:
        shark_size += 1
        eat_cnt = 0