import sys
from collections import deque
input = sys.stdin.readline

'''
문제: 17836 공주님을 구해라!
난이도: Gold 5
알고리즘: 그래프 탐색, BFS
풀이 방법: 최단 시간을 구해야 하므로 BFS 를 이용
        그람을 구해서 가는 경우와 그냥 가는 경우 최소 비교,
        주의! 꼭 그람을 구해야만 가는 경우가 있다.
'''
N, M, T = map(int, input().split())
arr = []
for _ in range(N):
    arr.append(list(map(int, input().split())))

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]
q = deque([(0, 0, 0)])
visited = [[0] * M for _ in range(N)]
visited[0][0] = 1
gram_dist = float("inf") # 그람을 구하는 경로
dist = float("inf") # 그람 없이 움직이는 경로
while q:
    x, y, t = q.popleft() # 현재 좌표, 시간, 그람 소지 여부
    if x == N-1 and y == M-1: # 도착
        dist = t 
        break

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if nx < 0 or nx >= N or ny < 0 or ny >= M:
            continue

        if not visited[nx][ny]: 
            if arr[nx][ny] == 0: # 빈공간
                visited[nx][ny] = 1
                q.append((nx, ny, t+1))
            elif arr[nx][ny] == 2: # 그람이 있는 공간이면
                gram_dist = ((N-1) - nx) + ((M-1) - ny) + t+1
                visited[nx][ny] = 1

ans = min(dist, gram_dist)
if ans > T:
    print("Fail")
else:
    print(ans)