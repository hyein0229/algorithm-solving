import sys
input = sys.stdin.readline

'''
문제: 16562 친구비
난이도: Gold 4
알고리즘: 자료구조, 분리 집합
풀이 방법: 친구의 친구는 친구 -> 분리 집합 또는 dfs/bfs 탐색으로 연결된 친구 관계의 그룹을 알아 내야 함
        아래 풀이는 union, find 알고리즘으로 비용이 적은 것(최소비용)을 루트로 하는 분리 집합을 사용
        만약, dfs 로 구하는 경우 연결된 요소들을 따라 탐색하여 각 그룹의 연결 요소 리스트를 만들고
        각각의 리스트에서 최소 비용을 구하여 합하는 방식으로 구현
'''
def find_parent(x):
    if parent[x] == x:
        return x
    parent[x] = find_parent(parent[x])
    return parent[x]

def union(x, y):
    x = find_parent(x)
    y = find_parent(y)

    if money[x] > money[y]: # 더 작은 비용의 원소를 부모 노드로 지정
        money[x] = 0
        parent[x] = y
    else:
        money[y] = 0
        parent[y] = x

n, m, k = map(int, input().split())
money = [0] + list(map(int, input().split())) # 친구비
parent =[i for i in range(n+1)]
for _ in range(m):
    v, w = map(int, input().split())
    union(v, w)

total = sum(money)
if total <= k:
    print(total)
else:
    print("Oh no")