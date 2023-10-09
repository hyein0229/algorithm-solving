import sys
from collections import deque

n, k = map(int, input().split())
if n >= k: # 동생이 더 앞에 있을 때 
    print(n-k)
else:
    answer, time = float('inf'), 0
    visited = [0]*100001
    q = deque([(n, 0)])
    visited[n] = 1
    
    while q:
        pos, t = q.popleft()
        if pos == k:
            answer = t
            break
        
        if pos != 0 and 2*pos <= k+1 and visited[2*pos] == 0:
            visited[2*pos] = 1
            q.appendleft((2*pos, t)) # 중요!!

        if pos-1 >= 0 and visited[pos-1] == 0:
            visited[pos-1] = 1
            q.append((pos-1, t+1))
        
        if pos+1 <= k and visited[pos+1] == 0:
            visited[pos+1] = 1
            q.append((pos+1, t+1))
    print(answer)