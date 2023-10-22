import sys
input = sys.stdin.readline
'''
문제: 2529 부등호
난이도: Silver 1
알고리즘: 브루트포스, 백트래킹
풀이 방법: dfs + 백트래킹을 이용하여 완전탐색을 하였다. 
        현재 부등호를 만족시키는 다음 숫자에 대해 dfs 탐색을 한다.
'''
def dfs(idx, n, number):
    global min_number, max_number
    if idx == k: # 모든 부등호를 다 본 경우
        if int(min_number) > int(number):
            min_number = number
        if int(max_number) < int(number):
            max_number = number
        return 
    
    visited[n] = 1
    
    # 현재 부등호 대상에 0~9가 올 때 만족하는 경우를 모두 탐색
    for i in range(10):
        if arr[idx] == '<' and n < i and not visited[i]:
            dfs(idx+1, i, number+str(i))
        if arr[idx] == '>' and n > i and not visited[i]:
            dfs(idx+1, i, number+str(i))

    visited[n] = 0 # 백트래킹을 위해 방문 여부 다시 0 으로

k = int(input())
arr = list(input().rstrip().split())
min_number = sys.maxsize # 최솟값
max_number = 0 # 최댓값

visited = [0] * 10
for num in range(10):
    dfs(0, num, str(num))

print(max_number)
print(min_number)