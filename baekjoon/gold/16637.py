import sys
N = int(sys.stdin.readline())
expr = list(sys.stdin.readline().rstrip())
res = float('-inf')

def calculate(x,y,opr):
    if opr == '+':
        return int(x) + int(y)
    elif opr == '-':
        return int(x) - int(y)
    else:
        return int(x) * int(y)

def dfs(i, value):

    global res
    if N == i:
        res = max(res, int(value))
        return
    
    if i+4 <= N: # 괄호를 만들 때
        dfs(i+4, calculate(value, calculate(expr[i+1], expr[i+3], expr[i+2]), expr[i]))
    
    if i+2 <= N: # 괄호를 만들지 않고 연산
        dfs(i+2, calculate(value, expr[i+1], expr[i]))

if N == 1:
    res = expr[0]
else:
    dfs(1, expr[0]) # 첫번째 연산자 위치, 현재까지 계산 결과 값
print(res)