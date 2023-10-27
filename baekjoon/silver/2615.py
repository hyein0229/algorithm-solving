import sys
input = sys.stdin.readline
'''
문제: 2615 오목
난이도: Silver 1
알고리즘: 구현, 브루트포스
풀이 방법: 문제 자체는 이해가 어렵지 않지만 생각보다 구현하는 데 착각할 부분들이 있고 조금씩 계속 오류를 내서 시간이 오래 걸렸던 문제이다.
        처음에 착각한 부분은 가로, 세로, 대각선 방향을 상/하/좌/우/북동/북서 .. 이렇게 8가지 방향으로 나누어 각각 따로 탐색했던 것이다.
        가로인 경우엔 좌우, 세로는 상하, 대각선인 경우엔 (북동,남서), (북서, 남동) 이렇게 같이 탐색을 해주어야 한다. 즉, 4가지 경우로 나누어야 한다.
        그리고 가장 왼쪽에 있는 바둑알을 구할 때, for문을 왼쪽부터 탐색하므로 단순하게 기준이 되는 x, y 가 답이 되겠다 생각했는데,
        위에서 남서쪽으로 가는 대각선의 경우엔 답이 되지 않는다. 그래서 연속된 바둑알을 배열에 넣고 열을 기준으로 정렬하여 구하였다. 
'''
# 가로, 세로, 두 방향의 대각선
direction = [[[0, 1], [0, -1]], [[-1, 0], [1, 0]], [[-1, 1], [1, -1]], [[-1, -1], [1, 1]]]
def check(x, y, d):
    # 해당 방향의 바둑알 탐색
    for dx, dy in direction[d]:
        step = 1
        # 주의, 5개일 때도 탐색하여 다음 바둑알이 연속되는지 아닌지 확인해야함
        while len(arr) <= 5:
            nx = x + dx * step
            ny = y + dy * step
            if nx < 0 or nx >= 19 or ny < 0 or ny >= 19:
                break

            # 같은 바둑알이 연속되면 삽입
            if board[nx][ny] == board[x][y]:
                arr.append((nx, ny))
            # 연속되지 않은 경우 더 이상 탐색하지 않음
            else:
                break

            # 연속된 개수가 5개를 넘는다면 이긴 것이 아님
            if len(arr) > 5:
                return False
            step += 1 # 다음 스텝
    # 탐색 결과 5개의 바둑알이 연속되었다면 이김
    if len(arr) == 5:
        return True

board = [list(map(int, input().split())) for _ in range(19)]
arr = [] # 연속된 바둑알의 위치 배열
ans = 0 # 승자의 번호
for i in range(19):
    for j in range(19):
        # 바둑알이 없으면 넘어감
        if board[i][j] == 0:
            continue
        # 가로, 세로, 대각선 방향을 탐색하여 이겼는지 확인
        for k in range(4):
            arr.append((i, j))
            # 해당 방향으로 바둑알이 5개 연속되는지 체크
            if check(i, j, k):
                ans = board[i][j]
                break

            arr.clear()

        if ans != 0:
            break
    # 승부가 났다면 탐색 종료
    if ans != 0:
        break

if ans != 0:
    arr.sort(key=lambda x: x[1]) # 열 오름차순
    print(ans)
    print(arr[0][0]+1, arr[0][1]+1)
else:
    print(ans)