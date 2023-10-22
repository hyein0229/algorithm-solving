import sys
input = sys.stdin.readline

'''
문제: 2504 괄호의 값
난이도: Gold 5
알고리즘: 구현, 스택
풀이 방법: 쌍이 맞는지 확인을 위해 스택이 필요하다는 것은 알았지만 값 계산을 구현하는 데 어려웠다.
        정답은 열린 괄호일 떄 값을 곱하여 비용을 누적시킨다.
        그러다가 닫힌 괄호가 나오면 직전 문자와 쌍이 맞는지를 확인하고(가장 안쪽 괄호열임을 의미) 맞는 경우엔 이전까지 누적된 값을 더하는 것이다.
        직전 문자와 쌍이 일치하지 않으면 누적된 비용에서 되돌리기 위해 나눗셈만 해준다.
        따라서, 계속 곱하다가 가장 안쪽 괄호열이 닫히는 순간 한 번에 총합에 더하는 것이다.
'''
arr = list(input().rstrip())
stack = []
cost = 1
ans = 0

for i in range(len(arr)):
    a = arr[i]
    if a == '(':
        cost *= 2
        stack.append(a)

    elif a == '[':
        cost *= 3
        stack.append(a)
    
    elif a == ')':
        # 쌍이 맞는지 확인
        if not stack or stack[-1] == '[':
            ans = 0
            break

        stack.pop()
        # (2+11) x 2 -> 2x2 + 11x2 로 계산됨
        # 가장 안쪽 괄호 쌍인 경우 현재까지 곱하여 누적된 값을 합함
        if arr[i-1] == '(':
            ans += cost
        cost //= 2

    else:
        if not stack or stack[-1] == '(':
            ans = 0
            break
        
        stack.pop()
        if arr[i-1] == '[':
            ans += cost
        cost //= 3

if stack: # 아직 괄호가 남아있다면 올바르지 못한 괄호열
    ans = 0
print(ans)