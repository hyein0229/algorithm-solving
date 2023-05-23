import sys

N = int(input())
lst = list(map(int, sys.stdin.readline().split()))
answer = [-1] * N
stack = []

stack.append(0)
for i in range(1, N):
    while stack and lst[stack[-1]] < lst[i]:
        answer[stack.pop()] = lst[i]
    stack.append(i)
    
for n in answer:
    print(n, end=' ')