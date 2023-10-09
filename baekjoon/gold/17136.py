import sys

paper = [list(map(int, sys.stdin.readline().split())) for _ in range(10)]
remain = [5 for _ in range(6)] # 각 사이즈의 색종이가 남아있는 개수
answer = 26 

def check(x, y, size):
    for i in range(x, x+size):
        for j in range(y, y+size):
            if paper[i][j] != 1:
                return False
    return True

def dfs(x, y, cnt):
    global remain, answer
    if x >= 10: 
        answer = min(answer, cnt)
        return
    
    if y >= 10:
        dfs(x+1, 0, cnt)
        return 

    if paper[x][y] == 1: 
        for size in range(1, 6): # 완전 탐색, 5가지 색종이를 모두 덮어봄
            if remain[size] == 0: 
                continue

            if x+size-1 >= 10 or y+size-1 >= 10: # 범위 체크
                continue

            if not check(x, y, size): # ixi 사이즈의 색종이로 덮을 수 잇는지 확인
                break
            # 색종이로 덮음
            for j in range(x, x+size):
                for k in range(y, y+size):
                    paper[j][k] = 0
            
            remain[size] -= 1
            dfs(x, y+size, cnt+1) # 그 다음 칸부터 다시 탐색

            # 백트래킹 
            remain[size] += 1 
            for j in range(x, x+size):
                for k in range(y, y+size):
                    paper[j][k] = 1
    else:
        dfs(x, y+1, cnt)

dfs(0, 0, 0)
if answer == 26:
    print(-1)
else:
    print(answer)