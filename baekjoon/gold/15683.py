import sys
import copy

# dfs + bruteforce
N, M = map(int, sys.stdin.readline().split())
room = [] # 사무실
cctv = [] # cctv 정보
for i in range(N):
    row = list(map(int, sys.stdin.readline().split()))
    for j in range(M):
        if row[j] in [1,2,3,4,5]: # cctv 위치와 번호 기록
            cctv.append((row[j], i, j)) # cctv번호, 행, 열
    room.append(row)

# 북동남서
dx = [0, 1, 0, -1]
dy = [-1, 0, 1, 0]
cctv_direct = [
    [],
    [[0], [1], [2], [3]], # 1번 cctc
    [[0, 2], [1, 3]], # 2번 cctv
    [[0, 1], [1, 2], [2, 3],[3, 0]], # 3번 cctv
    [[0, 1, 3], [0, 1, 2], [1, 2, 3], [2, 3, 0]], # 4번 cctv
    [[0, 1, 2, 3]] # 5번 cctv
]

def check(cur_direct, room, y, x):
    for i in cur_direct: # 감시할 수 있는 모든 방향 탐색
        nx, ny = x, y # 현재 위치
        while True:
            nx += dx[i] # 그 다음 위치 
            ny += dy[i]
            if nx < 0 or nx >= M or ny < 0 or ny >= N: # 범위 체크
                break
            if room[ny][nx] == 6: # 벽인 경우
                break
            if room[ny][nx] == 0: # 감시 가능한 위치
                room[ny][nx] = -1

def dfs(room, depth):
    global answer
    if depth == len(cctv): # 모든 cctv 탐색 완료
        cnt = 0
        for i in range(N):
            cnt += room[i].count(0) # 각 행의 사각 지대 개수 카운트
        answer = min(answer, cnt) # 가장 최소의 사각지대 개수
        return
    
    temp = copy.deepcopy(room)
    cctv_num, y, x = cctv[depth] # 현재 탐색할 cctv
    for cur_direct in cctv_direct[cctv_num]: # cctc 회전하면서 모든 방향 탐색
        check(cur_direct, temp, y, x) # 감시 가능 위치 체크
        dfs(temp, depth+1) # 그 다음 cctv 호출
        temp = copy.deepcopy(room) # 이전 상태로 복구

answer = int(1e9)
dfs(room, 0) # dfs 탐색 시작
print(answer)







