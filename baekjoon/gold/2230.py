import sys

n, m = map(int, input().split())
arr = [int(sys.stdin.readline()) for _ in range(n)]
arr.sort()

answer = float('inf')
left, right = 0, 0 # 투 포인터

while left < n and right < n:
    diff = arr[right] - arr[left] # 두 수의 차이
    if diff < m:
        right += 1
    else:
        answer = min(diff, answer)
        left += 1
print(answer)