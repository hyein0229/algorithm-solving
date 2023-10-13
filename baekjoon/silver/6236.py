import sys
import math
input = sys.stdin.readline

'''
문제: 6236 용돈 관리
난이도: Silver 2
알고리즘: 이분 탐색
풀이 방법: 인출 금액 k를 변수로 두어 이분 탐색을 하면 되는데, 문제 이해를 제대로 해야 한다.
        '모자라게 되면 남은 금액은 통장에 집어넣고 다시 K원을 인출한다' 라는 조건을 잘 이해해야 한다.
        처음에 구현한 방법은 k원이 하루 이용할 금액보다 작으면 그만큼 여러 번 인출하여 쓰도록 하였는데 이 방식은
        '모자란 남은 금액은 통장에 넣고' <- 이 부분에서 무한루프가 되고 결국 풀 수 없다는 것으므로 k원 자체를 높여서 풀어야 한다. 
        따라서, k원은 모든 하루 이용금액보다 무조건 커야한다. 이것을 문제 속에서 이해해야 하는 것이 핵심인 것 같다.
        또한, right 를 max(days) 가 아닌 sum(days) 로 두어야 하는 것에 주의하자.
        남은 금액이 이용할 금액보다 크면 인출 없이 계속 쓸 수 있으므로 인출할 수 있는 최대값이 max(days) 가 되어선 안된다.
'''

n, m = map(int, input().split())
days = [int(input()) for _ in range(n)]

left, right = min(days), sum(days)
k = sum(days)
while left <= right:
    mid = (left + right) // 2 # 인출 금액

    if max(days) > mid: # 인출 금액 자체가 모자라면 금액을 높임
        left = mid + 1
        continue
    
    cnt = 0 # 총 인출 횟수 카운트
    pocket = 0 # 현재 가진 돈
    for money in days:
        if money > pocket: # 현재 남은 돈이 모자라다면 인출
            pocket = mid 
            cnt += 1
        pocket -= money # 쓰고 남은 돈
    
    if cnt > m:
        left = mid + 1
    else:
        k = min(k, mid)
        right = mid - 1
print(k)