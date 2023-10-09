import sys
from collections import deque

n, k = map(int, input().split())
test = [] # 시험관
viruses = []
for i in range(n):
    row = list(map(int, sys.stdin.readline().split()))
    for j in range(n):
        if row[j] != 0: # 바이러스가 존재하면
            viruses.append((row[j], i, j, 0)) # 바이러스, 행, 열, 초
    test.append(row)
s, x, y = map(int, input().split())
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

if s == 0:
    print(test[x-1][y-1])
    exit(0)

q = deque(sorted(viruses, key = lambda x : x[0])) # 번호가 작은 순으로 오름차순 정렬
while q: 
    v = q.popleft()
    for i in range(4):
        nx = v[1] + dx[i]
        ny = v[2] + dy[i]
        if 0 <= nx < n and 0 <= ny < n:
            if test[nx][ny] == 0: # 바이러스가 없으면
                test[nx][ny] = v[0] # 증식
                if v[3]+1 < s:
                    q.append((v[0], nx, ny, v[3]+1))

print(test[x-1][y-1])