import sys
input = sys.stdin.readline

n, m = map(int, input().split()) # 덩어리 개수, 필요한 고기 양
meat = []
find = False
for _ in range(n):
    meat.append(list(map(int, input().split()))) # 덩어리 무게, 가격
meat.sort(key=lambda x : (x[1], -x[0])) # 가격 오름차순, 무게 내림차순

answer = sys.maxsize
weight, same = 0, 0
find = False
for i in range(n):
    weight += meat[i][0] # 무게 합
    if i >= 1 and meat[i][1] == meat[i-1][1]: # 이전 고기와 가격이 일치한 경우
        same += meat[i-1][1] # 같은 가격 추가, 더 싼 고기가 아니므로 추가 지불 필요
    else:
        same = 0
    if weight >= m: # 필요한 고기의 양을 충족한 경우
        answer = min(answer, meat[i][1] + same) # 최소 비용 갱신
        find = True

if find:
    print(answer)
else:
    print(-1)

