import sys
input = sys.stdin.readline

N = int(input())
balls = []
for i in range(N):
    c, s = map(int, input().split())
    balls.append((i, c, s)) # 플레이어 번호, 색번호, 크기

balls.sort(key=lambda x : (x[2], x[1])) # 크기, 색 번호 순 오름차순 정렬

colors = [0] * (N+1)
players = [0] * N
sum_ = 0
i, j = 0, 0
while i < N:

    a = balls[i] # 결과를 구할 플레이어 공
    b = balls[j] 

    while b[2] < a[2]: # 크기가 작을 때까지
        sum_ += b[2] # 총 사이즈 누적
        colors[b[1]] += b[2] # 색별 사이즈 누적

        j += 1
        b = balls[j] # 다음 공

    players[a[0]] = sum_ - colors[a[1]] # 총 사이즈 누적에서 자신의 컬러의 누적을 뺀 것이 정답
    i += 1

for i in range(N):
    print(players[i])