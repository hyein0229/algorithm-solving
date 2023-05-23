import sys
import heapq

N = int(input())
mid = int(input())
print(mid)

# [left -> maxheap] mid [right -> minheap]
# 입력된 숫자와 mid 를 비교하여 왼쪽 또는 오른쪽 heap에 원소 삽입
max_heap = []
min_heap = []
for i in range(1, N):
    num = int(sys.stdin.readline())
    if num > mid:
        heapq.heappush(min_heap, num)
        if len(max_heap) + 2 <= len(min_heap):
            heapq.heappush(max_heap, -mid)
            mid = heapq.heappop(min_heap)
    else:
        heapq.heappush(max_heap, -num)
        if len(max_heap) > len(min_heap):
            heapq.heappush(min_heap, mid)
            mid = -heapq.heappop(max_heap)
    print(mid)