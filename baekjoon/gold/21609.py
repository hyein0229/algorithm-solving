import sys
import copy
from collections import deque

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
size = 0
rainbow = []

def dfs(x, y, num, visited):
    global size, rainbow
    visited[x][y] = 1
    size += 1
    if blocks[x][y] == 0:
        rainbow.append((x, y))

    for k in range(4):
        nx = x + dx[k]
        ny = y + dy[k]
        if 0 <= nx < N and 0 <= ny < N:
            if not visited[nx][ny]:
                if blocks[nx][ny] == num or blocks[nx][ny] == 0:
                    dfs(nx, ny, num, visited)

def check_group(blocks):
    global size, rainbow
    groups = [] # 찾은 블록 그룹
    visited = [[0]*N for _ in range(N)]
    for i in range(N):
        for j in range(N):
            if blocks[i][j] > 0 and not visited[i][j]:
                dfs(i, j, blocks[i][j], visited)
                if size >= 2:
                    groups.append((size, len(rainbow), i, j)) # 블록의 크기, 무지개블록 수, 기준블록의 행, 열
                size = 0
                for x, y in rainbow:
                    visited[x][y] = 0
                rainbow = []

    if groups:
        groups.sort(key=lambda x : (-x[0], -x[1], -x[2], -x[3]))
    return groups

def gravity(blocks):
    # 마지막 행부터 위로 올라가면서 중력 적용
    for i in range(N-1, -1, -1):
        for j in range(N-1, -1, -1):
            if blocks[i][j] >= 0:
                nx, ny = i, j # 이동할 위치
                for k in range(i+1, N):
                    if blocks[k][j] == -2:
                        nx, ny = k, j
                    else:
                        break
                blocks[i][j], blocks[nx][ny] = blocks[nx][ny], blocks[i][j]

def rotate(blocks):
    after = [[0]*N for _ in range(N)]
    for i in range(N):
        for j in range(N):
            after[N-1-j][i] = blocks[i][j]
    return after

N, M = map(int, input().split())
blocks = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
score = 0

while True:
    # 블록 그룹 탐색
    result = check_group(blocks) 
    if not result:
        break

    target = result[0] # 타켓 그룹
    tx, ty = target[2], target[3]
    num = blocks[tx][ty]
    B = target[0]
    q = deque([(tx, ty)])
    # 블록 그룹 제거
    while q:
        x, y = q.popleft()
        for k in range(4):
            nx = x + dx[k]
            ny = y + dy[k]
            if 0 <= nx < N and 0 <= ny < N:
                if blocks[nx][ny] == num or blocks[nx][ny] == 0:
                    blocks[nx][ny] = -2
                    q.append((nx, ny))

    score += B**2 # 점수 추가
    gravity(blocks) # 중력 작용
    blocks = copy.deepcopy(rotate(blocks)) # 격자 90도 반시계 방향 회전
    gravity(blocks) # gravity(blocks)

print(score)