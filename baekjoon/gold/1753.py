import sys
import heapq

INF = sys.maxsize
V, E = map(int, sys.stdin.readline().split())
K = int(sys.stdin.readline().rstrip())
edges = [[] for _ in range(V+1)]
distance = [INF] * (V+1)
heap = []

def Dijkstra(start):
    
    distance[start] = 0
    heapq.heappush(heap, (0, start))

    while heap:
        cur_d, cur = heapq.heappop(heap)

        if distance[cur] < cur_d:
            continue

        for n, w in edges[cur]:
            cost = cur_d + w
            if cost < distance[n]:
                distance[n] = cost
                heapq.heappush(heap, (cost, n))
        

for _ in range(E):
    u, v, w = map(int, sys.stdin.readline().split())
    edges[u].append((v, w))

Dijkstra(K)
for i in range(1, V+1):
    if distance[i] == INF:
        print('INF')
    else:
        print(distance[i])