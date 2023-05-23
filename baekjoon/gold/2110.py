import sys

N, C = map(int, input().split())
pos = []
for _ in range(N):
    pos.append(int(sys.stdin.readline()))
pos.sort()

if C == 2:
    print(pos[-1] - pos[0])
else:
    answer = 0
    left , right = 1, pos[-1] - pos[0]  # 최소거리, 최대거리
    while left <= right:
        mid = (left + right) // 2
        cur = pos[0]
        cnt = 1
        
        for i in range(1, N):
            if pos[i] >= cur + mid:
                cnt += 1
                cur = pos[i] # 공유기 설치한 현재 위치로 업데이트

        if cnt >= C:
            left = mid + 1
            answer = mid
        else:
            right = mid - 1
    print(answer)