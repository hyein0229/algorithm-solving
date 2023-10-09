H, W = map(int, input().split())
blocks = list(map(int, input().split()))

total = 0 # 고이는 빗물의 총량
max_h = blocks[0] # 현재까지 최대의 높이
s = 0 # 탐색한 위치
for i in range(1, len(blocks)):
    h = blocks[i] # 현재 칸의 높이
    if h > max_h: # 최대 높이가 갱신될 때
        if max_h != 0 and s+1 < i: # 왼쪽 블록이 있었으며 그 사이 칸이 있는 경우 -> 빗물이 쌓일 수 있음
            for j in range(s+1, i):
                total += max_h - blocks[j] 
        max_h = h
        s = i
    else:
        if i == len(blocks)-1: # 마지막 칸까지 탐색을 완료하였을 때
            p = blocks[i]
            for j in range(i-1, s, -1): # 끝에서부터 역순으로 (오른쪽 블록을 고려하여 빗물 계산)
                if p < blocks[j]: # 오른쪽 최대 높이 갱신
                    p = blocks[j]
                else: # 오른쪽의 최대 높이보다 블록이 낮을때만 빗물이 고이므로 총량 추가
                    total += p - blocks[j] 
                    
print(total)