import sys

N = int(input())			
arr = [0]
for _ in range(N):
    arr.append(int(sys.stdin.readline()))
answer = set()		

def dfs(first, second, num):
    first.add(num)			# 첫번째 줄 집합
    second.add(arr[num])		# 두번째 줄 집합
    if arr[num] in first: # 사이클이 형성됨
        if first == second:		# 첫번째 줄 집합과 두번째 줄 집합이 같다면
            answer.update(first)
            return True
        return False
    return dfs(first, second, arr[num])	

# dfs 실행
for i in range(1, N+1):
    if i not in answer:			# i가 결과 집합 안에 없는 경우만 실행 
        dfs(set(), set(), i)

print(len(answer))
for i in sorted(list(answer)):
    print(i)