import sys
sys.setrecursionlimit(10**7)

N, M = map(int, input().split())
r, c, d = map(int, input().split()) # 로봇청소기 좌표와 바라보는 방향
room = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

direction = [[-1, 0], [0, 1], [1, 0], [0, -1]] # 북동남서
def move(room, r, c, d):
    flag = 0
    cnt = 0
    while True:

        if flag == 4: # 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
            # 현 방향을 유지한 채 후진한 다음 위치
            nr = r - direction[d][0]
            nc = c - direction[d][1]
            if nr < 0 or nr >= N or nc < 0 or nc >= M: # 범위 확인
                break
            
            if room[nr][nc] != 1:# 벽이 아니면 후진 가능
                r, c = nr, nc # 한 칸 후진
                flag = 0
                continue

            else: # 벽이여서 후진할 수 없다면 작동 멈춤
                break
        
        if room[r][c] == 0: # 현재 칸이 청소안한 칸이면
            room[r][c] = -1 # 청소된 상태로 갱신
            cnt += 1

        # 반시계방향 회전
        if d == 0: 
            d = 3 
        else:
            d -= 1 

        # 방향 전환 후 다음 칸의 위치
        nr = r + direction[d][0]
        nc = c + direction[d][1]
        
        if nr < 0 or nr >= N or nc < 0 or nc >= M: # 범위체크
            flag += 1
            continue
        
        if room[nr][nc] == 0: # 청소하지 않은 칸이면
            r, c = nr, nc # 칸 이동
            flag = 0
        else: # 청소된 칸이거나 벽인 경우
            flag += 1

    return cnt

answer = move(room, r, c, d)
print(answer)

