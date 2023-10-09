import sys
sys.setrecursionlimit(10**7)

# 치즈를 중심으로 생각하지 말고 공기를 초점으로 생각해보자
# 공기일 때 dfs 탐색 진행 -> 자동으로 내부에 치즈 내부에 빈 공간은 무시
N, M = map(int, input().split())
board = []
cnt = 0 # 치즈 총 개수
for i in range(N):
    row = list(map(int, sys.stdin.readline().split()))
    cnt += row.count(1) # 치즈 개수 카운트
    board.append(row)

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
def dfs(board, x, y):

    visited[x][y] = 1 # 방문 여부 체크

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if nx < 0 or nx >= N or ny < 0 or ny >= M: # 범위체크
            continue

        if board[nx][ny] == 1: # 치즈인 경우 
            visited[nx][ny] += 1 # 공기와 접촉하는 수 카운트
        elif visited[nx][ny] == 0: # 공기면서 방문하지 않은 칸이면
            dfs(board, nx, ny)

t = 0 # 시간
removed = 0 # 제거 여부
while cnt > 0: # 모든 치즈가 없어질 떄까지 진행
    visited = [[0]*M for _ in range(N)] # 초기화
    dfs(board, 0, 0) # dfs 탐색으로 각 치즈의 공기와 닿는 면 카운트
    for i in range(N):
        for j in range(M):
            if board[i][j] == 1 and visited[i][j] >= 2: # 공기와 닿는 부분이 2이상인 치즈일 때
                board[i][j] = 0 # 치즈 제거
                cnt -= 1
                removed = 1 # 제거 여부 1로 갱신
    if removed:
        t += 1
print(t)