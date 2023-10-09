import sys

N, M = map(int, input().split())
relation = {i : [] for i in range(N)} # 친구관계 딕셔너리 사람 : 친구
for _ in range(M):
    a, b = map(int, sys.stdin.readline().split())
    relation[a].append(b)
    relation[b].append(a)

def dfs(cnt, person):
    global answer, visited
    if cnt == 4: # 4개의 관계 조건 성립
        answer = 1
        return

    visited[person] = 1

    for friend in relation[person]: # 친구인 관계
        if answer != 1 and visited[friend] == 0: # 아직 조건이 만족되지 않았고 방문하지 않은 사람이면
            dfs(cnt + 1, friend)
            visited[friend] = 0 # 백트래킹

answer = 0
for i in range(N): # i부터 dfs 시작
    if answer == 0:
        visited = [0] * N
        dfs(0, i)
print(answer)