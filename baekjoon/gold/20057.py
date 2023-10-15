import sys
input = sys.stdin.readline

'''
문제: 20057 마법사 상어와 토네이도
난이도: Gold 3
알고리즘: 구현
풀이 방법: 토네이도 회전을 구현하는 것까진 쉽게 구현했지만 모래 이동 구현을 어떻게 해야 코드를 간결하게 짤 수 있을 지를 생각해야 했다.
        이동 방향마다 비율이 있는 칸으로 가는 방향도 달라지니까..
        방법은 생각보다 매우 간단했다. 왼쪽일 때의 비율이 적혀있는 칸의 방향이 정해져 있다. 나머지 방향의 경우도 회전한 경우와 같으므로 좌표를 쉽게 구할 수 있다.
        따라서, 왼쪽, 오른쪽, 아래, 위쪽 각 경우의 비율 칸 방향을 하나씩 모두 기록해두고 딕셔너리에서 방향에 맞는 것을 가져오면 된다.
        너무 어렵게 생각하려 하지 말자..
'''
left = [(1, 1, 0.01), (-1, 1, 0.01), (1, 0, 0.07), (-1, 0, 0.07), (1, -1, 0.1),
        (-1, -1, 0.1), (2, 0, 0.02), (-2, 0, 0.02), (0, -2, 0.05), (0, -1, 0)]
right = [(x, -y, z) for x,y,z in left]
down = [(-y, x, z) for x,y,z in left]
up = [(y, x, z) for x,y,z in left]
ratio = {0: left, 1: down, 2: right, 3: up}

def move_sand(x, y, d):
    global total

    rest = sand[x][y] # 이동하고 남은 모래의 양
    for dx, dy, r in ratio[d]:
        nx, ny = x + dx, y + dy
        if r == 0: # a 칸
            new_sand = rest 
        else:
            new_sand = int(sand[x][y] * r) # 비율만큼의 모래 이동
            rest -= new_sand

        if nx < 0 or nx >= n or ny < 0 or ny >= n: # 범위가 격자의 밖이면
            total += new_sand # 격자 밖으로 나간 모래의 합 
        else:
            sand[nx][ny] += new_sand # 다른 칸으로 모래 이동


n = int(input())
sand = []
for _ in range(n):
    sand.append(list(map(int, input().split())))

# 순서대로 왼쪽, 아래, 오른족, 위 방향
dx = [0, 1, 0, -1]
dy = [-1, 0, 1, 0]

x, y = n//2, n//2
total = 0
step = 0
for i in range(2*n-1): # 회전 횟수 -> 2*N -1
    direct = i % 4 # 이동 방향
    if direct == 0 or direct == 2: # 왼쪽과 오른쪽으로 전환될 때 길이가 +1 되므로
        step += 1
    for _ in range(step):
        nx = x + dx[direct]
        ny = y + dy[direct]
        if ny < 0:
            break
        move_sand(nx, ny, direct)
        x, y = nx, ny

print(total)
    
