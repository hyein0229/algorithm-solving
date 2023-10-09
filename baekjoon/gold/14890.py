import sys

N, L = map(int, input().split())
board = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

def check_road(board):
    cnt = 0 # 길 카운트
    for i in range(N): # 모든 행의 길 가능 여부 탐색
        is_road = False # 가능한 길인지 여부
        flag = 0 # 경사로로 내려가야할 때에 대한 flag
        floor_len = 1 # 바닥길이
        for j in range(1, N): # 왼쪽에서 오른쪽으로 탐색
            cur_h, before_h = board[i][j], board[i][j-1]

            if cur_h == before_h: # 같은 층이면
                floor_len += 1
            elif cur_h == before_h + 1: # 1층 높아지면
                if flag == 1: # 이전에 내려왔다가 다시 올라갈 때를 의미
                    if floor_len >= 2*L: # 두개의 경사로를 놓을 수 있는지 확인
                        floor_len = 1
                        flag = 0
                    else:
                        break
                elif floor_len >= L: # 올라가는 경사로를 놓을 수 있는 지 확인
                    floor_len = 1
                else:
                    break
            elif cur_h == before_h - 1: # 1층 낮아지면
                if flag == 1: # 이미 이전에 한번 내려와야할 때를 의미
                    if floor_len >= L: # 내려오는 경사로를 놓을 수 있는지
                        floor_len = 1
                    else:
                        break
                flag = 1 # 내려와야함을 표시
                floor_len = 1
            else: # 층이 2이상 차이나면 길이 될 수 없음
                break
        
            if j == N-1: # 마지막 칸까지 탐색하였다면
                if flag == 1 and floor_len >= L:
                    is_road = True
                elif flag == 0:
                    is_road = True
        if is_road: # 길이면
            cnt += 1

    return cnt
    
answer = check_road(board) # 각 행 탐색
answer += check_road(list(zip(*board))) # 보드를 전치시켜 각 열을 탐색하도록 함
print(answer)