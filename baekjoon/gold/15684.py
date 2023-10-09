import sys

N, M, H = map(int, sys.stdin.readline().split())
graph = [[0] * (N+1) for _ in range(H+1)]
for _ in range(M):
    a, b = map(int, sys.stdin.readline().split())
    graph[a][b] = 1

def check():
    # i번 세로선의 결과가 i번이 나오는지 순차적으로 확인
    for i in range(1, N+1):
        cur = i
        for j in range(1, H+1):
            if graph[j][cur]: # 오른쪽 가로선이 있는 경우
                cur += 1 # 옆 세로선으로 이동
            elif cur > 1 and graph[j][cur - 1]: # 왼쪽 가로선이 있는 경우
                cur -= 1
        if cur != i: # 최종 결과가 i번 도착이 아니면
            return False 
    return True

def dfs(cnt, row, col):

    global answer
    if check(): # 조건이 만족된다면 최솟값 갱신
        answer = min(answer, cnt)
        return
    elif cnt == 3 or answer <= cnt:  # 도착점이 조건을 만족하지 않으면
        # 현재 cnt가 3이라면 이 다음 가로선 추가 시 4가 되므로 조건 위배
        # 현재 cnt가 현재 정답보다 크거나 같으면 더 이상 볼 필요 없으므로 리턴
        return

    for i in range(row, H+1): # 행
        k = col if i == row else 1 # 현재 탐색 중인 행인 경우 탐색하던 열부터 마저 탐색, 아닌 경우 시작열부터 탐색
        for j in range(k, N): # 열
            if not graph[i][j]:
                graph[i][j] = 1 # 가로선 추가하기
                dfs(cnt + 1, i, j + 2) # 가로선이 연속될 수 없으므로 j+2부터
                graph[i][j] = 0

answer = 4
dfs(0, 1, 1)
answer = answer if answer <= 3 else -1
print(answer)