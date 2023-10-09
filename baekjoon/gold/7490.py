from collections import deque

n = int(input())
numbers = [int(input()) for _ in range(n)]
all_case = dict()
for i in range(n):
    all_case[numbers[i]] = []

for i in range(n):
    number = numbers[i]
    q = deque([str(1)])
    cnt = len(q)
    cur = 2
    while cnt > 0 and cur <= number:
        expr = q.popleft() # 수식
        cnt -= 1
        for opr in ['+', '-', ' ']:
            new_expr = expr + opr + str(cur)
            if cur == number:
                if eval(new_expr.replace(' ', '')) == 0:
                    all_case[number].append(new_expr)
                continue
            q.append(new_expr)
        if cnt == 0:
            cur += 1 # 다음 숫자
            cnt = len(q) # 존재하는 수식 개수

for i in range(n):
    result = sorted(all_case[numbers[i]])
    for expr in result:
        print(expr)
    if i != n-1:
        print()

