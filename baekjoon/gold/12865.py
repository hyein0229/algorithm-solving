import sys

n, k = map(int, input().split())
items = [[0,0]]
dp = [[0]*(k+1) for _ in range(n+1)]
# dp[n][k] -> n번째 물건까지 살펴보았을 때 무게가 k인 배낭의 최대가치
for i in range(n):
    items.append(list(map(int, sys.stdin.readline().split())))
    
for i in range(1, n+1):
    for j in range(1, k+1):
        w, v = items[i][0], items[i][1]

        if j < w:
            dp[i][j] = dp[i-1][j]
        else:
            dp[i][j] = max(dp[i-1][j], dp[i-1][j-w]+v)

print(dp[n][k])