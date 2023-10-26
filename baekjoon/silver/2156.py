import sys
input = sys.stdin.readline
'''
문제: 2156 포도주 시식
난이도: Silver 1
알고리즘: dp
풀이 방법: 순서대로 와인을 순회하면서 i번째 와인까지 왔을 때 마실 수 있는 최대 양을 dp 값으로 기록한다.
        i번째가 있을 때 최대 값을 구할 수 있는 선택지는 세 가지이다.
        i번째를 마시지 않았을 때 -> 이 경우, i-1번째까지 마신 총 양이 그대로 전달된다.
        i, i-1번째를 마실 때 -> i-2번째는 연속 마실 수 없으므로 i-3번째까지 마신 총 양에 i, i-1번째를 더한 값이 된다.
        i, i-2번째를 마실 때 -> i-2번째까지 마신 총 양에 i번째 양을 더한다.
'''
n = int(input())
wines = [int(input()) for _ in range(n)]

# 포도주가 두개 이하면 포도주 양의 총 합이 최대 마실 수 있는 양
if n <= 2:
    print(sum(wines))
    exit(0)

# 포도주가 세개 이상인 경우
dp = [0] * n
dp[0] = wines[0]
dp[1] = wines[0] + wines[1]

# 세번째 포도주부터 선택이 주어짐, 연속된 3개를 먹을 수 없으므로 하나를 마시지 않았을 때 가장 큰 값이 dp 값
# dp[i-1] : i번째, 현재 와인을 마시지 않는 경우 i-1까지 마신 총 양
# dp[i-2] + wines[i] : i-2번째까지 마신 총 양 + i번째 양 (i-1번째를 마시지 않음)
# dp[i-3] + wines[i-1] + wines[i] : i-3번째까지 마신 총 양 + i, i-1 번째를 마심
dp[2] = max(dp[1], wines[0] + wines[2], wines[1] + wines[2])
for i in range(3, n):
    dp[i] = max(dp[i-1], dp[i-2] + wines[i], dp[i-3] + wines[i-1] + wines[i])
print(max(dp))