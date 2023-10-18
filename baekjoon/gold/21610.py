import sys
input = sys.stdin.readline

'''
문제: 21610 마법사 상어와 비바라기
난이도: Gold 5
알고리즘: 구현
풀이 방법: 문제에서 제시한대로 차근차근 구현만 해주면 되는데 주의할 것은 배열의 끝과 끝이 이어져 있다는 것이다.
        따라서, 구름 좌표 이동 시 모듈러 연산을 이용해주어야 한다.
        새로운 구름 생성 시에 이전에 구름이 사라졌던 칸은 제외해줘야 하는데 이때, in 연산으로 직접 리스트에
        해당 좌표가 존재하는지를 찾으면 배열 l 길이만큼 시간복잡도가 O(n**2*l) 이 되기 때문에 pypy3만 통과한다.
        따라서, 차라리 메모리 제한이 크므로 visited 배열을 만들어 이전에 구름이 사라졌던 칸을
        표시해주는 것으로 바꾸어서 이 부분을 O(1) 로 만들어 python 통과가 가능하도록 수정하였다.
'''
dx = [0, -1, -1, -1, 0, 1, 1, 1]
dy = [-1, -1, 0, 1, 1, 1, 0, -1]

def move(d, s):
    # 모든 구름 이동
    for i in range(len(cloud)):
        x, y = cloud[i]
        # 배열의 끝과 끝이 이어져 있으므로 모듈러 연산을 이용
        nx = (x + dx[d-1] * s) % n
        ny = (y + dy[d-1] * s) % n
        board[nx][ny] += 1 # 이동한 칸의 물의 양이 1 증가
        cloud[i] = (nx, ny) # 이동한 후의 구름 위치
        visited[nx][ny] = 1 # 구름이 사라진 자리 표시

def magic():
    for x, y in cloud:
        # 대각선 칸 중 물이 있는 칸 개수 구하기
        cnt = 0
        for i in range(1, 8, 2): # 대각선 좌표 보기
            nx = x + dx[i]
            ny = y + dy[i]
            # 경계를 넘어가는 칸은 예외처리
            if nx < 0 or nx >= n or ny < 0 or ny >= n: 
                continue

            if board[nx][ny] >= 1: 
                cnt += 1
        # 칸의 수만큼 물의 양 증가
        board[x][y] += cnt

def make_cloud():
    global cloud
    new_cloud = []
    for i in range(n):
        for j in range(n):
            # 물의 양이 2 이상인 칸이고 이전에 구름이 사라졌던 칸이 아니면 새로운 구름 생성
            if board[i][j] >= 2 and not visited[i][j]:
                new_cloud.append((i, j))
                board[i][j] -= 2
    cloud = new_cloud

n, m = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]
orders = [list(map(int, input().split())) for _ in range(m)]
cloud = [(n-1,0), (n-1,1), (n-2,0), (n-2,1)] # 구름 위치

# m번의 명령 수행
for order in orders:
    d, s = order # 방향, 이동거리
    visited = [[0] * n for _ in range(n)]
    move(d, s) 
    magic() 
    make_cloud() 

# 모든 바구니의 물의 양 합 구하기
total = 0
for i in range(n):
    total += sum(board[i])
print(total)