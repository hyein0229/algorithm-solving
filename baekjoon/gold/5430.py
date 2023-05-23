import sys
from collections import deque

T = int(input())
for _ in range(T):
    p = list(sys.stdin.readline().rstrip())
    n = int(sys.stdin.readline())
    str_lst = sys.stdin.readline().rstrip()
    if n == 0:
        dq = deque([])
    else:
        dq = deque(str_lst[1:len(str_lst)-1].split(','))
    
    error = 0
    reverse = 0
    for func in p:
        if func == 'R':
            reverse += 1
        elif func == 'D':
            if not dq:
                print('error')
                error = 1
                break
            else: 
                if reverse % 2 == 0:
                    dq.popleft()
                else:
                    dq.pop()
                
    if not error:
        if reverse % 2 != 0:
            dq.reverse()
        print('[' + ','.join(dq) + ']')  