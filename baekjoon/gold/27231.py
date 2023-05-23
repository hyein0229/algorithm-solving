import sys
from itertools import combinations

T = int(input())
for _ in range(T):
    n = sys.stdin.readline().rstrip()

    if n.count('1') + n.count('0') == len(n):
        print('Hello, BOJ 2023!')
        continue
    
    plus_idx = [i for i in range(len(n)-1)]
    sum_lst = [int(n)]
    for j in range(1, len(n)):
        for cur in combinations(plus_idx, j):
            sum = 0
            for k in range(len(cur)):
                idx = cur[k]
                if k == 0:
                    sum += int(n[:idx+1])
                else:
                    sum += int(n[cur[k-1]+1:idx+1])
    
            sum += int(n[cur[-1]+1:])    
            if sum not in sum_lst:
                sum_lst.append(sum)
    
    cnt = 0
    m = 1
    sum = 0
    while sum <= int(n):
        sum = 0
        for num in n:
            sum += int(num) ** m
        if sum in sum_lst:
            cnt += 1
        m += 1
        
    print(cnt)