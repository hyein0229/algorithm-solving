import sys
import heapq

n = int(input())
arr = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
arr.sort(key=lambda x : (x[0], x[1])) # 빨리 시작하는 순, 끝나는 순

heap = [] # 최소힙
for i in range(n):
    if not heap:
        heapq.heappush(heap, arr[i][1]) # 힙에 끝나는 시간 삽입
        continue

    if heap[0] <= arr[i][0]: # 가장 빨리 끝나는 회의 시간보다 시작 시간이 크면
        heapq.heappop(heap)
    heapq.heappush(heap, arr[i][1])
    
print(len(heap))