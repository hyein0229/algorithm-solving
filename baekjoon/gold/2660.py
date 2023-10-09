import sys
from collections import deque

n = int(input())
friends = dict()
for i in range(1, n+1):
    friends[i] = []
while True:
    a, b = map(int, sys.stdin.readline().split())
    if a == -1:
        break
    friends[a].append(b)
    friends[b].append(a)    

candidates = []
for num in range(1, n+1): # 모든 회원에 대해 점수 구하기
    visited = [0]*(n+1)
    q = deque([(num, 0)])
    visited[num] = 1
    total = 0

    while q:
        cur, score = q.popleft()
        if score > total:
            total = score

        for friend in friends[cur]: # 친구 회원의 친구 탐색
            if visited[friend] == 0:
                visited[friend] = 1
                q.append((friend, score+1))
    
    if not candidates or candidates[0][1] == total: # 아직 후보가 없거나 같은 점수이면 후보 추가
        candidates.append((num, total))
    elif candidates[0][1] > total: # 더 작은 점수의 회원이면 새로 갱신 
        candidates = [(num, total)]

if candidates:
    print(candidates[0][1], len(candidates))
    for c in candidates:
        print(c[0], end=' ')