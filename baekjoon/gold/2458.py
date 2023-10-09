import sys

def dfs(a, b):
    result[a][b] = 1 # a < b
    result[b][a] = -1 # b > a
    for c in comparison[b]: # b 보다 큰 학생을 이어 탐색
        if not result[a][c]:
            dfs(a, c)

N, M = map(int, input().split())
comparison = [[] for _ in range(N+1)] # 입력된 비교 결과를 딕셔너리에 저장
for _ in range(M):
    a, b = list(map(int, sys.stdin.readline().split()))
    comparison[a].append(b)
result = [[0]*(N+1) for _ in range(N+1)]

for i in range(1, N+1): # 모든 학생에 대해 비교 결과를 기록
    result[i][i] = 1
    for j in comparison[i]: # j보다 큰 학생들
        if not result[i][j]: # 아직 비교 결과가 없으면 탐색 진행
            dfs(i, j)
answer = 0
for i in range(1, N+1):
    if not result[i][1:].count(0): # 모든 학생과 비교를 할 수 있으면
        answer += 1
print(answer)