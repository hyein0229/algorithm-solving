import sys

N = int(input())
cols = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
cols.sort(key=lambda x: x[0]) # 위치 순서대로 정렬

if N == 1: # 기둥이 한개면
    print(cols[0][1])
else:
    max_h = cols[0][1] # 첫번재 기둥 높이
    pos = cols[0][0] # 첫번째 기둥 위치
    area = 0
    for i in range(1, N):
        if cols[i][1] >= max_h: # 이전보다 높이가 최대가 될 때
            area += max_h * (cols[i][0] - pos)
            max_h = cols[i][1] 
            pos = cols[i][0] 
            if cols[i][1] > max_h:
                max_h = cols[i][1]
                
    if cols[N-1][1] < max_h: # 마지막 기둥 높이가 낮아졌을 경우
        next_h = cols[N-1][1]
        pos = cols[N-1][0]
        for i in range(N-2, -1, -1):
            if cols[i][1] >= next_h: 
                area += next_h * (pos - cols[i][0])
                next_h = cols[i][1] 
                pos = cols[i][0] 
                if cols[i][1] > next_h:
                    next_h = cols[i][1]

            if max_h == next_h:
                break

    area += max_h
    print(area)