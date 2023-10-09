G = int(input())
cur = [w for w in range(1, 100001)]
before = [w for w in range(1, 100001)]
limit = 100000
l, r = 0, 0 # 투 포인터
answer = []
while l < limit and r < limit:
    result = cur[l]**2 - before[r]**2
    if result < G: # 차이가 G보다 작으면
        l += 1 # 현재 몸무게를 증가시킴
        continue

    if result == G:
        answer.append(cur[l])
    r += 1 # 이전 몸무게 증가
    
if not answer:
    print(-1)
else:
    for weight in answer:
        print(weight)