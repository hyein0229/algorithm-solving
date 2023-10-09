import sys
from collections import deque
input = sys.stdin.readline

N = int(input())
task = []
for _ in range(N):
    t, s = map(int, input().split())
    task.append((t, s))
task.sort(key=lambda x : (-x[1], -x[0])) # 마감 시간이 멀수록, 걸리는 시간이 클수록

time = task[0][1] - task[0][0]
task.pop(0)
while task:
    t, s = task.pop(0) # 소요 시간, 마감 시간
    if time > s: # 현재 시간이 마감 시간보다 크면
        time = s - t
    else:
        time -= t

if time < 0:
    print(-1)
else:
    print(time)