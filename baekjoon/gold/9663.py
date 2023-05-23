import sys

# pypy3 통과
N = int(sys.stdin.readline().rstrip())

ans = 0
row = [0] * N

def promising(x):
    for i in range(x):
        if row[x] == row[i] or abs(row[x] - row[i]) == abs(x - i):
            return False
    return True

def dfs(x):
    global ans
    if x == N:
        ans += 1
    else:
        for i in range(N):
            row[x] = i
            if promising(x): 
                dfs(x+1) 

dfs(0)
print(ans)