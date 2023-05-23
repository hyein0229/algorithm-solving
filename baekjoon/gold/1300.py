import sys

N = int(input())
k = int(input())

# 이분 탐색 문제
answer = 0
left, right = 1, N*N
while left <= right:
    mid = (left + right) // 2
    cnt = 0

    for i in range(1, N+1):
        cnt += min(mid // i, N)
            
    if cnt >= k:
        right = mid - 1
        answer = mid
    else:
        left = mid + 1
    
print(answer)