import sys
import heapq

N = int(input())
cards = []
for _ in range(N):
    heapq.heappush(cards, int(sys.stdin.readline().rstrip()))

total_cnt = 0
while len(cards) > 1:
    c1 = heapq.heappop(cards)
    c2 = heapq.heappop(cards)
    total_cnt += (c1 + c2)
    heapq.heappush(cards, c1 + c2)
print(total_cnt)