import sys

x = ' ' + sys.stdin.readline().rstrip()
y = ' ' + sys.stdin.readline().rstrip()
dp = [[0 for _ in range(len(y))] for _ in range(len(x))]

for i in range(1, len(x)):
    for j in range(1, len(y)):
        if x[i] == y[j]:
            dp[i][j] = dp[i-1][j-1] + 1
        else:
            dp[i][j] = max(dp[i][j-1], dp[i-1][j])

print(dp[-1][-1])