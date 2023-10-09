import sys
from collections import deque
input = sys.stdin.readline

k = int(input())

n = 1 # 자리 수
while True:
    total = 2 ** n # 4와 7로 만들어 질 수 있는 총 n 자리 수 개수
    if k > total:
        k -= total
    else:
        answer = []
        for i in range(n, 0, -1):
            c = 2 ** (i-1) # n-1 자리 개수
            step = (k-1) // c
            if step == 0:
                answer.append(4)
            else:
                answer.append(7)
            k -= step * c
        
        for i in answer:
            print(i, end='')
        break
    n += 1 # 그 다음 자리 수