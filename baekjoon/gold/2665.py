import sys
import heapq
input = sys.stdin.readline

# 최소비용 -> 다익스트라
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]
def dijkstra():
    heap = []
    heapq.heappush(heap, [0, 0, 0])
    visited[0][0] = 1

    while heap:
        c, x, y = heapq.heappop(heap) # 흰 방으로 바꾼 개수, 현재 좌표
        if x == n-1 and y == n-1: # 끝방에 도착하면
            print(c)
            return
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if nx < 0 or nx >= n or ny < 0 or ny >= n:
                continue

            if not visited[nx][ny]:
                visited[nx][ny] = 1
                if graph[nx][ny] == 1: # 흰 방일 때
                    heapq.heappush(heap, [c, nx, ny])
                else: # 검은방일 때
                    heapq.heappush(heap, [c+1, nx, ny]) # 카운트 증가하여 힙에 삽입

n = int(input())
graph = []
for _ in range(n):
    graph.append(list(map(int, input().rstrip())))
visited = [[0] * n for _ in range(n)]
dijkstra()