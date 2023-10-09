import sys

N, M, K = map(int, input().split())
fireballs = []
for _ in range(M):
    r, c, m, s, d = list(map(int, sys.stdin.readline().split()))
    fireballs.append([r-1, c-1, m, s, d])

board = [[[] for _ in range(N)] for _ in range(N)]
dx = [-1, -1, 0, 1, 1, 1, 0, -1]
dy = [0, 1, 1, 1, 0, -1, -1, -1]

while K > 0: 
    while fireballs: # 파이어볼 이동
        r, c, m, s, d = fireballs.pop(0)
        nr = (r + dx[d]*s) % N # 1번과 N번이 연결되어 있으므로
        nc = (c + dy[d]*s) % N
        board[nr][nc].append([m, s, d]) 
    
    # 이동 후 2개 이상의 파이어 볼이 있는 칸 확인
    for i in range(N):
        for j in range(N):
            if len(board[i][j]) >= 2: # 2개 이상이 있으면 4개의 파이어 볼로 쪼갬
                sum_m, sum_s, num = 0, 0, len(board[i][j])
                cnt_odd = 0 # 방향이 홀수인 파이어 볼 카운트
                while board[i][j]:
                    m, s, d = board[i][j].pop(0) # 현재 위치의 파이어 볼을 하나씩 제거하여
                    sum_m += m # 질량 합
                    sum_s += s # 속력 합
                    if d % 2 != 0: 
                        cnt_odd += 1
                cnt_even = num - cnt_odd # 방향이 짝수인 파이어 볼 개수
                if cnt_odd == num or cnt_even == num: # 방향이 모우 홀수이거나 짝수이면 0, 2, 4, 6 방향
                    nd = [0,2,4,6]
                else:
                    nd = [1,3,5,7]
                
                if sum_m//5: # 질량이 0이 아니면
                    for d in nd:
                        fireballs.append([i, j, sum_m//5, sum_s//num, d])

            if len(board[i][j]) == 1:
                fireballs.append([i,j] + board[i][j].pop())
    K -= 1

print(sum([ball[2] for ball in fireballs]))