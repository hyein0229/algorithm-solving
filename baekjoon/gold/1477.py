N, M, L = map(int, input().split())
positions = [0] + list(map(int, input().split())) + [L];
positions.sort()

left, right = 1, L-1 
answer = 0
while left <= right:
    mid = (left + right) // 2 # 가장 멀리 떨어진 휴게소 사이의 거리
    cnt = 0 # 설치할 휴게소 개수
    for i in range(1, len(positions)):
        if positions[i] - positions[i-1] > mid:
            cnt += (positions[i] - positions[i-1] - 1) // mid  # 이미 설치한 곳에 설치하면 안되므로 -1
    if cnt > M: # M개 이상 설치할 수 있다면
        left = mid + 1 # 휴게소 간격을 넓힘
    else: # M개 보다 적게 설치된다면
        right = mid - 1 # 휴게소 간격을 좁힘
        answer = mid

print(answer)
