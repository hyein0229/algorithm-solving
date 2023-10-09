import sys
input = sys.stdin.readline

T = int(input().rstrip())
testcase = []
for _ in range(T):
    N = int(input()) # 동전 개수
    coins = list(map(int, input().split())) # N개의 동전
    M = int(input()) # 만들어야할 금액
    testcase.append([N] + coins + [M])

answer = []
for i in range(T):
    case = testcase[i] # 현재 테스트 케이스
    m = case[-1] # 목표 금액

    dp = [0] * (m+1)
    dp[0] = 1

    # C원으로 X원을 만들 수 있는 가지수 -> X-C원을 만들 수 있는 가지 수를 살펴봐야함.
    # (X-C) + C = X 이기 때문
    for coin in case[1:len(case)-1]:
        for j in range(m+1):
            if j >= coin:
                dp[j] += dp[j - coin]
    answer.append(dp[m])

for n in answer:
    print(n)