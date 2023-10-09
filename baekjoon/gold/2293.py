import sys

n, k = map(int, input().split())
coins = [int(input()) for _ in range(n)]
dp = [0] * (k+1) # k원까지의 가치 경우의 수
dp[0] = 1 # i == coin 일 때 coin원을 만드는 경우의 수를 1개 추가시키기 위함

for coin in coins:
    # 핵심 -> coin원 동전으로 i원 만드는 것은 i-coin원을 만든 후 coin원을 추가하는 것과 같다.
    # 즉, coin원으로 i원을 만든느 경우의 수 = i-coin 원을 만드는 경우의 수
    for i in range(coin, k+1):
        dp[i] += dp[i-coin]
print(dp[k])