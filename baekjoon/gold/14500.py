import sys
input = sys.stdin.readline

'''
문제: 14500 테트로미노
난이도: Gold 4
알고리즘: 원래는 브루트포스지만 dfs로 풀이
풀이 방법: dfs로 한 칸씩 모양을 만들면서 탐색하며 합을 구한다.
        핵심!!은 두번째 블록 연결 후 세번째 연결 시 ㅏ, ㅓ, ㅗ, ㅜ 모양을 만들기 위해선
        새로운 좌표가 아닌 기존 좌표에서 탐색을 해야 한다는 것이다.
        처음에 테트로미노의 5가지 모양이 주어지고 회전, 대칭이라는 말에 꽂혀 하나씩 함수를 구현하고 완전 탐색으로 풀어야 하나 했는데,
        (실제로 브루트포스로 해결한 사람의 코드를 보니까 코드의 길이가 매우 길었다..)
        dfs + 백트래킹으로 상하좌우 연결을 하나씩 해주면 모든 모양을 탐색할 수 있다는것을 알았다.
'''
dx = [-1,0,1,0]
dy = [0,-1,0,1]
def DFS(x,y,L,total):
    global ans
    if ans >= total + max_val*(4-L): # 최댓값을 넘을 수 없다면
        return 

    if L == 4: # 테트로미노를 다 놓은 경우 합 비교 
        ans = max(ans,total)
        return 

    # 모양 탐색
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if 0<=nx<N and 0<=ny<M and visit[nx][ny] == 0:
            # 두번째 블록 연결 후 'ㅏ','ㅓ','ㅗ','ㅜ' 만들기
            if L == 2: 
                visit[nx][ny] = 1
                # 새로운 좌표에서 탐색하지 않고 기존 좌표로 돌아와 탐색재개
                DFS(x,y,L+1,total+board[nx][ny])
                visit[nx][ny] = 0
            visit[nx][ny] = 1
            DFS(nx,ny,L+1,total+board[nx][ny])
            visit[nx][ny] = 0
            
N,M = map(int,input().split())
board = [list(map(int,input().split())) for _ in range(N)]
visit = [[0]*M for _ in range(N)]

max_val = max(map(max,board)) # 보드에서 최댓값 
ans = 0
for i in range(N):
    for j in range(M):
        visit[i][j] = 1
        DFS(i,j,1,board[i][j])
        visit[i][j] = 0
print(ans)