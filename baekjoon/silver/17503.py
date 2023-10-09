import sys
import heapq
input = sys.stdin.readline

N, M, K = map(int, input().split())
beer = [list(map(int, input().split())) for _ in range(K)]
beer.sort(key=lambda x : (x[1], x[0])) # 도수 레벨, 선호도 오름차순 정렬

heap = []
answer = -1
sum_v = 0
# 도수가 낮은 순으로 맥주 탐색
for b in beer:
    sum_v += b[0] # 선호도 합
    heapq.heappush(heap, b[0])
    if len(heap) == N:
        if sum_v >= M:
            answer = b[1]
            break
        else:
            sum_v -= heapq.heappop(heap) # 선호도가 가장 작은 것 배기
print(answer)