import sys

def find(a):
    if parents[a] != a:
        parents[a] = find(parents[a])
    return parents[a]

def union(a, b):
    a = find(a)
    b = find(b)
    if a > b:
        parents[a] = b
    else:
        parents[b] = a

N = int(input())
M = int(input())
parents = [ i for i in range(N)]
for i in range(N):
    r = list(map(int, sys.stdin.readline().split()))
    for j in range(N):
        if r[j] == 1:
            union(i, j)
plan = list(map(int, sys.stdin.readline().split()))

answer = 'YES'
for i in range(1, M):
    if parents[plan[i] - 1] != parents[plan[0] - 1]:
        answer = 'NO'
        break
print(answer)