import sys

def obstacle(cnt):
    global answer

    if cnt == 3: # 3개의 장애물을 모두 설치했다면
        if check(): # 감시
            answer = True
            return
    else:
        for x in range(N): 
            for y in range(N):
                if board[x][y] == 'X':
                    board[x][y] = 'O' # 장애물 설치
                    obstacle(cnt + 1)
                    board[x][y] = 'X' # 백트래킹

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
def check():
    for x, y in teachers:
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]

            while 0 <= nx < N and 0 <= ny < N:
                if board[nx][ny] == 'S': # 학생이 보이면 실패
                    return False
                elif board[nx][ny] == 'O': # 장애물이 있다면 엄춤
                    break
                nx += dx[k]
                ny += dy[k]
    return True # 감시 피하기 성공

N = int(input())
answer = 0
teachers = []
board = []
for i in range(N):
    board.append(list(sys.stdin.readline().split()))
    for j in range(N):
        if board[i][j] == 'T': # 선생님 좌표 저장ㄴ
            teachers.append((i, j)) 

obstacle(0)
if answer:
    print('YES')
else:
    print('NO')