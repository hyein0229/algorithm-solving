import sys

N, S = map(int, sys.stdin.readline().split())
num = list(map(int , sys.stdin.readline().split()))

left, right = 0, 0
sum = num[0]
min_len = 100001
while left <= right:
    
    if S <= sum:
        if right - left + 1 < min_len:
            min_len = right - left + 1
        sum -= num[left]
        left += 1
    elif right == N - 1:
        break
    else:
        sum += num[right+1]
        right += 1
        
if min_len != 100001:
    print(min_len)
else:
    print(0)