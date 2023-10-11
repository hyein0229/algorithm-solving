import sys
input = sys.stdin.readline

"""
문제: 20202번 달력
난이도: gold 5
알고리즘: 구현, 그리디
풀이 방법: 문제 조건에 맞게 정렬 후 직접 2차원 배열 일정표를 생성,
          각 열(날짜)을 순서대로 탐색하여 일정이 연속되지 않을 때까지 최대 row를 구해주고,
          일정이 끊기면 이전까지 구한 row * col 계산하여 직사각형 면적을 구한다.
문제 조건에 맞게 그대로 구현만 하면 풀 수 있는 문제!
"""

n = int(input())
schedule = []
for _ in range(n):
    s, e = map(int, input().split())
    schedule.append((s, e))
board = [[0] * 366 for _ in range(n)] # 365일 달력
schedule.sort(key=lambda x : (x[0], -x[1])) # 시작일 오름차순, 종료 날짜 내림차순 정렬

# 2차원 배열 일정표 작성
for i in range(n):
    s, e = schedule[i] # 시작 날짜, 종료 날짜
    row = 0
    for j in range(n):
        if board[j][s] == 0:
            row = j
            break
    for k in range(s, e+1): # 시작일부터 종료일까지 일정 표시
        board[row][k] = 1

w, h = 0, 0 # 직사각형 너비, 높이
total = 0
for i in range(1, 366): 
    flag = False # 해당 날짜에 일정이 존재하는지 여부
    for j in range(n): 
        if board[j][i] == 1: # 1 이면 일정이 존재
            flag = True
            h = max(h, j+1)
    if flag: # 존재한다면 다음 날짜로 넘어감
        w += 1
    else:
        total += w * h
        w, h = 0, 0
if flag:
    total += w * h
print(total)

