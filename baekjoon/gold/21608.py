import sys

N = int(input())
students = [list(map(int, sys.stdin.readline().split())) for _ in range(N**2)]
positions = [[0]*N for _ in range(N)] # NxN 자리 배치
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

# 순서대로 학생 자리 정하기
for student in students:
    arr = []
    for i in range(N):
        for j in range(N):
            if positions[i][j] == 0: # 빈자리면
                like = 0 # 인접한 좋아하는 학생 수
                empty = 0 # 인접한 비어있는 자리 수
                for k in range(4):
                    nx, ny = i + dx[k], j + dy[k]
                    if 0 <= nx < N and 0 <= ny < N:
                        if positions[nx][ny] == 0: # 비어있는 자리면
                            empty += 1
                        elif positions[nx][ny][0] in student[1:]: # 좋아하는 학생이면 (인덱스 0에 학생 번호가 있으므로 주의)
                            like += 1
                arr.append([like, empty, i, j])
    # 좋아하는 학생 수가 많을 수록, 비어있는 자리가 많을수록, 행, 열 번호가 작을수록 답이므로 그에 맞게 정렬
    arr.sort(key = lambda x : (-x[0], -x[1], x[2], [3]))
    x, y = arr[0][2], arr[0][3]
    positions[x][y] = student # 학생 배치

# 학생의 만족도의 총 합 구하기
satisfied = 0
for x in range(N):
    for y in range(N):
        student = positions[x][y]
        cnt = 0
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < N and positions[nx][ny][0] in student[1:]:
                cnt += 1
        if cnt != 0:        
            satisfied += 10**(cnt-1)
print(satisfied)