import sys
input = sys.stdin.readline

village = []
people = 0
n = int(input())
for i in range(n):
    v, p = map(int,input().split())
    village.append((v, p))
    people += p
village.sort(key = lambda x : x[0])

# 우체국을 기점으로 좌우의 인구 합의 차이가 최소가 되는 지점을 찾기
# -> 차이가 최소라는 것 = 좌측의 인구 합이 총 인구 합의 절반 가까이가 되었을 때 이다.
# 우체국으로부터 거리의 손해, 이득을 보는 사람들의 차이를 최소화..?
count = 0
pos = 0
for i in range(n):
    count += village[i][1]
    if count >= people/2: # 마을당 사람의 누적 합이 총 인구 수의 절반 이상을 넘어가면
        pos = village[i][0]
        break 
print(pos)