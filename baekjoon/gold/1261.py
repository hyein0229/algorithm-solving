import sys
from heapq import heappush, heappop
input = sys.stdin.readline

m, n = map(int, input().split())
miro = []
for _ in range(n):
    miro.append(list(map(int, input().rstrip())))
visited = [[0]*m for _ in range(n)] # 방문 여부

heap = []
heappush(heap, [0, 0, 0])
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]
while heap:

    cnt, x, y = heappop(heap) # 부순 벽 개수의 최소값을 가져옴
    if x == n-1 and y == m-1: # 도착점이면 탐색 종료
        print(cnt)
        break
    
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        
        if 0 <= nx < n and 0 <= ny < m and not visited[nx][ny]:
            visited[nx][ny] = 1
            if miro[nx][ny] == 1: # 벽일 때
                heappush(heap, [cnt+1, nx, ny])
            else: #  빈 방일 때
                heappush(heap, [cnt, nx, ny])