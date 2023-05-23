import sys

R, C = map(int, input().split())
board = []
for _ in range(R):
    board.append(list(sys.stdin.readline().rstrip()))

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
cnt = 1

def BFS(start_x, start_y):
    
    global cnt
    q = set([(start_x, start_y, board[start_x][start_y])])

    while q:
        x, y, road = q.pop()
        cnt = max(cnt, len(road))
        
        for i in range(4):
            next_x, next_y = x + dx[i], y + dy[i]
            
            if next_x < 0 or next_x >= R or next_y < 0 or next_y >= C:
                continue
            
            if board[next_x][next_y] not in road:
                q.add((next_x, next_y, board[next_x][next_y] + road))
                

BFS(0, 0)
print(cnt)