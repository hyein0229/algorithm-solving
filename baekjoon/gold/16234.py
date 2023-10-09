import sys
import copy
from collections import deque

N, L, R = map(int, input().split())
countries = [list(map(int, sys.stdin.readline().split())) for _ in range(N)] # NxN 나라
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def bfs(r, c):
    
    q = deque([(r, c, countries[r][c])])
    unite = []
    visited[r][c] = 1

    while q:
        x, y, people = q.popleft() # 현재 방문 나라
        unite.append((x, y, people)) # 연합에 추가

        for i in range(4): # 인접한 국가
            nx = x + dx[i]
            ny = y + dy[i]

            if nx < 0 or nx >= N or ny < 0 or ny >= N: # 범위체크 
                continue

            if visited[nx][ny] == 0 and L <= abs(people - countries[nx][ny]) <= R: # 방문을 하지 않았고, 인구 차이가 L이상 R이하면
                q.append((nx, ny, countries[nx][ny])) # 큐에 추가
                visited[nx][ny] = 1 # 방문 기록
    return unite


t = 0 # 인구이동에 필요한 일 수
while True:
    visited = [[0]*N for _ in range(N)]
    move = False # 인구이동 여부
    temp = copy.deepcopy(countries) # 인구 갱신을 기록하기 위한 임시 나라 정보
    
    # 모든 나라에 대해 bfs 진행하여 연합 탐색
    for i in range(N):
        for j in range(N):
            if visited[i][j] == 0: # 방문하지 않았다면 해당 나라를 기점으로 bfs 진행
                unite = bfs(i, j) # 연합
                # 국경선이 열린 연합이 있다면
                if len(unite) >= 2: 
                    move = True # 인구이동 진행
                    move_people = sum(map(lambda x : x[2], unite)) // len(unite) # 연합의 인구수 / 연합을 이루는 칸의 개수
                    for x, y, p in unite:
                        temp[x][y] = move_people # 인구 갱신
    
    # 인구이동이 있다면
    if move:
        countries = temp # 나라 인구 갱신
        t += 1
    else: # 없다면 반복문 종료
        break

print(t)