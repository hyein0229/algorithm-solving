import sys
from itertools import permutations
input = sys.stdin.readline
'''
문제: 12869 뮤탈리스크
난이도: Gold 4
알고리즘: DP, dfs/bfs
풀이 방법: 세 개의 scv 를 공격할 수 있는 순서는 총 3! = 6 가지의 경우이다.
        dfs 탐색을 이용해 for 문을 돌면서 6가지의 경우에 따라 모두 탐색해주면 되지만 이 방식대로면 모든 경우의 수를
        탐색하게 되므로 시간이 오래걸린다. 시간복잡도를 줄이기 위해선 dp 를 사용하여 (x, y, z) 체력에 도착할 수 있는
        최소 공격 횟수를 저장하고, 최소 횟수가 갱신되는 경우에만 탐색을 하도록 제한할 수 있다.
'''

def dfs(x, y, z, cnt):
    global ans
    if x == 0 and y == 0 and z == 0: # 모든 scv 가 파괴된 경우
        ans = min(ans, cnt) 
        return
    
    # 처음 도착한 경우 또는 더 적은 횟수로 도착하는 경우 공격 횟수 갱신
    if dp[x][y][z] == 0 or dp[x][y][z] > cnt:
        dp[x][y][z] = cnt
        
        for p in permutations([9, 3, 1], 3):
            # 순서대로 공격 후의 체력 상태
            x_ = 0 if x-p[0] <= 0 else x-p[0]
            y_ = 0 if y-p[1] <= 0 else y-p[1]
            z_ = 0 if z-p[2] <= 0 else z-p[2]
            dfs(x_, y_, z_, cnt+1)

n = int(input())
scv = list(map(int, input().split()))
# 주어진 csv 가 3개 미만이면 나머지는 0으로 채움 
while len(scv) < 3:
    scv.append(0)

dp = [[[0] * 61 for _ in range(61)] for _  in range(61)] # 각 체력을 인덱스로 하고, 최소 공격 횟수를 값으로 저장하는 dp 배열
ans = float("inf")
dfs(scv[0], scv[1], scv[2], 0)
print(ans)