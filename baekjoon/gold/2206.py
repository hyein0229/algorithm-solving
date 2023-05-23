import sys
from collections import deque

N, M = map(int, input().split())
graph = []
dp = [[[0] * 2 for _ in range(M)] for _ in range(N)] # 벽을 파괴했는지의 여부 추가 -> 3차원 배열로 선언
# dp[r][c][0] -> 벽 파괴 x, dp[r][c][1] -> 벽 파괴 o

for _ in range(N):
    line = list(map(int, sys.stdin.readline().rstrip()))
    graph.append(line)

def BFS(r, c, crashed):
    
    Q = deque([(r, c, crashed)])
    dp[r][c][crashed] = 1
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    while Q:
        cur_r, cur_c, crashed = Q.popleft()
        if cur_r == N-1 and cur_c == M-1:
            return dp[cur_r][cur_c][crashed]

        for i in range(4):
            next_r, next_c = cur_r + dx[i], cur_c + dy[i]
        
            if next_r < 0 or next_r >= N or next_c < 0 or next_c >= M: # map 좌표 범위 검사
                continue
        
            if graph[next_r][next_c] == 0 and dp[next_r][next_c][crashed] == 0: # 갈 수 있는 길이고 방문하지 않았다면
                Q.append((next_r, next_c, crashed))
                dp[next_r][next_c][crashed] = dp[cur_r][cur_c][crashed] + 1
            
            if graph[next_r][next_c] == 1 and crashed == 0: # 벽을 파괴하고 감
                Q.append((next_r, next_c, 1))
                dp[next_r][next_c][1] = dp[cur_r][cur_c][crashed] + 1
    return -1
    
    
print(BFS(0, 0, 0))