import sys
import heapq

N = int(input())
schedules = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
schedules.sort(key=lambda x : x[0])

heap = []
heapq.heappush(heap, schedules[0][1]) # 최소힙
for i in range(1, N):
    if heap[0] > schedules[i][0]: # 가장 빨리 끝나는 시간보다 시작하는 시간이 더 앞이면
        heapq.heappush(heap, schedules[i][1]) # 새 강의실 추가

    else: # 새로운 수업으로 배정
        heapq.heappop(heap)
        heapq.heappush(heap, schedules[i][1])

print(len(heap))