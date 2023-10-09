import sys
input = sys.stdin.readline
D, N = map(int,input().split())
oven = list(map(int,input().split()))
pizza = list(map(int,input().split()))

for i in range(D-1):
    if oven[i] < oven[i+1]:
        oven[i+1] = oven[i]

answer = -1
cur = 0 # 현재 피자 인덱스
for i in range(D-1, -1, -1):
    if pizza[cur] > oven[i]: # 현재 피자의 지름이 오븐의 지름보다 크다면 다음 오븐으로 넘어감
        continue

    # 현재 피자 지름이 오븐의 지름보다 작거나 같다면 피자가 들어감. 다음 피자 탐색
    cur += 1
    if cur == N: # 모든 피자를 오븐에 다 넣은 것이므로
        answer = i+1
        break

if answer != -1: 
    print(answer)
else:
    print(0)