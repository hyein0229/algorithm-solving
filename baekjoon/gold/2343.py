N, M = map(int, input().split())
lectures = list(map(int, input().split()))

answer = 0
left, right = max(lectures), sum(lectures) # 녹화 길이의 최솟값, 최댓값
while left <= right:
    mid = (left+right)//2

    count, total = 0, 0
    for i in range(N):
        if total + lectures[i] > mid: # 녹화길이를 초과하면
            count += 1 # 블루레이 카운트
            total = 0
        total += lectures[i]

    if total: # 마지막 녹화
        count += 1

    if count > M: # m개를 초과한 경우
        left = mid + 1  # 녹화길이 늘리기
    else:
        right = mid - 1 # 녹화길이 줄이기
        answer = mid
print(answer)