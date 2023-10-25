import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**7)
'''
문제: 2636 치즈
난이도: Gold 4
알고리즘: 구현, 그래프 탐색, dfs/bfs
풀이 방법: <그림1> 을 보면 중간에 치즈가 빈 칸과 닿아있더라도 겉에 감싸는 치즈가 있다면 녹지 않는다.
        따라서, 치즈의 가장자리를 구하려면 치즈가 없다고 가정된 (0, 0) 에서 이어진 공기가 있는 칸들을 dfs 로 모두 탐색하여
        공기와 닿아있는 치즈 칸을 찾아 치즈를 없애면 된다. 치즈로 둘러싸여져 있는 가운데 공기 칸들은 치즈가 있는 칸에 막혀 갈 수 없게 된다. 
'''
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]
def dfs(x, y):
    global total
    visited[x][y] = 1

    for k in range(4):
        nx = x + dx[k]
        ny = y + dy[k]
        if nx < 0 or nx >= n or ny < 0 or ny >= m:
            continue

        if not visited[nx][ny]:
            if board[nx][ny] == 1: # 치즈가 있는 칸일 때
                board[nx][ny] = 0 # 치즈 사라짐
                visited[nx][ny] = 1 # 방문 체크
                total -= 1 # 총 치즈 개수 감소
            else: # 공기일 때
                dfs(nx, ny)


n, m = map(int, input().split())
board = []
total = 0
for _ in range(n):
    row = list(map(int, input().split()))
    total += row.count(1) # 치즈 개수 카운트
    board.append(row)

time = 0 # 모든 치즈가 사라질 때까지 걸린 시간
while total > 0:
    before = total # 치즈가 사라지기 전 치즈가 있는 칸의 개수
    visited = [[0] * m for _ in range(n)] # 방문 체크
    dfs(0, 0) # 공기와 닿아있는 가장자리 치즈 탐색
    time += 1 # 1초 지남

    # 치즈가 모두 녹아서 업어졌으면 종료
    if total == 0: 
        print(time)
        print(before)
        break