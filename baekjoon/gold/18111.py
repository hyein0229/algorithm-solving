import sys

n, m, b = map(int, input().split())
heights = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
min_time = float('inf')
max_height = 0

for h in range(257): # 땅 높이
    remove_blocks = 0 # 제거한 블록 수
    add_blocks = 0 # 추가한 블록 수
    for i in range(n):
        for j in range(m):
            if heights[i][j] > h:
                remove_blocks += heights[i][j] - h
            else:
                add_blocks += h - heights[i][j] 
    
    if add_blocks <= remove_blocks + b:
        totaltime = remove_blocks * 2 + add_blocks # 걸린 총 시간
        if totaltime <= min_time:
            min_time = totaltime
            max_height = max(max_height, h)

print(min_time, max_height)