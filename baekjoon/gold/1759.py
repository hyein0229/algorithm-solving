import sys
input = sys.stdin.readline
'''
문제: 1759 암호 만들기
난이도 Gold 5
알고리즘: 브루트포스
풀이 방법: dfs를 이용하여 풀었다. 암호 체크 시 또 for 문을 돌려서 모음, 자음의 개수를 판단하는 것보단
        모음의 개수도 매개변수를 통해 같이 전달하도록 하였다. set 에서의 요소 찾기는 리스트와 다르게 O(1) 이므로 모음 집합으로 선언하였다.
'''
def dfs(cur, s, cnt):
    if len(s) == l: # 암호의 길이가 l이고 
        if cnt >= 1 and l-cnt >= 2: # 최소 한개의 모음, 최소 두 개의 자음으로 구성되어 있으면 가능성 있는 암호
            ans.append(s)
        return
    
    # 사전순 뒤에 있는 알파벳을 하나씩 이어서 탐색
    for k in range(cur+1, c):
        if arr[k] in m: 
            dfs(k, s+str(arr[k]), cnt+1)
        else:
            dfs(k, s+str(arr[k]), cnt)

l, c = map(int, input().split())
arr = list(input().rstrip().split())
arr.sort() # 사전식 정렬
m = {'a', 'e', 'i', 'o', 'u'} # 모음 집합

ans = []
for i in range(c-l+1):
    if arr[i] in m:
        dfs(i, arr[i], 1) 
    else:
        dfs(i, arr[i], 0)

for code in ans:
    print(code)