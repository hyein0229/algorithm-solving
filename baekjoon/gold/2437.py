import sys

N = int(input())
lst = list(map(int, input().split()))
lst.sort()

target = 1
for num in lst:
    if target < num:
        break
    target += num
print(target)