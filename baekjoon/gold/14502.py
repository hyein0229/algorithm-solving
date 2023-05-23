import sys
from itertools import combinations
from copy import deepcopy

# 완전 탐색 + dfs
N, M = map(int, input().split())
arr = []
for _ in range(N):
    arr.append(list(map(int, sys.stdin.readline().split())))

zero_area = []
for i in range(N):
    for j in range(M):
        if arr[i][j] == 0:
            zero_area.append((i, j))
            
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]
def virus(x, y):
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if 0 <= nx < N and 0 <= ny < M:
            if arr[nx][ny] == 0:
                arr[nx][ny] = 2
                virus(nx, ny)
                
def area_cnt(cur_arr):
    cnt = 0
    for i in range(N):
        for j in range(M):
            if arr[i][j] == 0:
                cnt += 1
    return cnt
    

max_area_size = 0
positions = combinations(zero_area, 3)
copied_arr = deepcopy(arr)

for cur in positions:
    arr = deepcopy(copied_arr)
    for r, c in cur:
        arr[r][c] = 1

    for i in range(N):
        for j in range(M):
            if arr[i][j] == 2:
                virus(i, j)
    max_area_size = max(max_area_size, area_cnt(arr))
    
print(max_area_size)  