import sys

n = int(input())
lines = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
lines.sort(key=lambda x : x[0]) # A전봇대 기준으로 오름차순 정렬

# 가장 긴 증가하는 부분수열의 길이 구하기!!!
dp = [1] * n
for i in range(1, n):
    for j in range(i): # 이전 번호의 전깃줄
        # 앞 전깃줄의 b번호가 현재 b번호보다 작은 경우 전깃줄 교차 x
        if lines[j][1] < lines[i][1]: 
            dp[i] = max(dp[i], dp[j] + 1)

print(n - max(dp))