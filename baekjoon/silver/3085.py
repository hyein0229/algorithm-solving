import sys
input = sys.stdin.readline      

'''
문제: 3085 사탕 게임
난이도: Silver 2
알고리즘: 구현, 브루트포스
풀이 방법: 단순하게 생각. 인접한 두 칸이 서로 다르면 교환하고 가장 긴 연속부분을 구하는 식으로 완전탐색을 한다.
        for을 돌 때 오른쪽 -> 아래로 돌면 인접한 칸 탐색 시 상하좌우말고 오른쪽, 아래 두 칸만 고려하면 된다.
'''

def eat(board):
    max_len = 1 # 가장 긴 연속부분의 길이
    for i in range(n):
        length = 1
        for j in range(1, n): # 행의 연속부분
            if board[i][j] == board[i][j-1]:
                length += 1
            else:
                length = 1
                continue
            max_len = max(length, max_len)

        length = 1
        for j in range(1, n): # 열의 연속부분
            if board[j][i] == board[j-1][i]:
                length += 1
            else:
                length = 1
                continue
            max_len = max(length, max_len)
    return max_len
        
n = int(input())
board = [list(input().rstrip()) for _ in range(n)]

ans = 1
for i in range(n):
    for j in range(n):
        if j+1 < n and board[i][j] != board[i][j+1]: # 오른쪽 칸과 다르면 교환
            board[i][j], board[i][j+1] = board[i][j+1], board[i][j]
            ans = max(eat(board), ans) # 가장 긴 연속부분 구하기
            board[i][j], board[i][j+1] = board[i][j+1], board[i][j] # 되돌리기

        if i+1 < n and board[i][j] != board[i+1][j]: # 아래칸과 다르면 교환
            board[i][j], board[i+1][j] = board[i+1][j], board[i][j]
            ans = max(eat(board), ans)
            board[i][j], board[i+1][j] = board[i+1][j], board[i][j]
            
print(ans)