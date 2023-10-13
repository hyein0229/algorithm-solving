import sys
from itertools import permutations
import copy
input = sys.stdin.readline

'''
문제: 17406 배열 돌리기 4
난이도: Gold 4
알고리즘: 구현, 브루트포스
풀이 방법: 연산 수행 순서에 따른 결과를 모두 계산해야 하므로 완전 탐색이다.
            주어진 조건에 맞게 배열을 회전 시키는 코드 구현만 잘하면 쉬운 문제인 것 같다. 
            배열 자체를 90도 회전인 줄 알았는데 그게 아니어서 이 부분이 젤 까다로웠던 점..?
'''

def rotate(A, op):

    tmp = copy.deepcopy(A)
    r, c, s = op # 현재 수행할 회전 연산
    x, y = r-s-1, c-s-1 # 가장 왼쪽 상단 좌표
    for w in range(2*s+1, 1, -2): # 정사각형 변 길이
        for i in range(y, y+w-1): 
            tmp[x][i+1] = A[x][i] # 오른쪽으로
        for i in range(x, x+w-1):
            tmp[i+1][y+w-1] = A[i][y+w-1] # 아래로
        for i in range(y+w-1, y, -1):
            tmp[x+w-1][i-1] = A[x+w-1][i] # 왼쪽으로
        for i in range(x+w-1, x, -1):
            tmp[i-1][y] = A[i][y] # 위로

        x += 1
        y += 1
    return tmp

n, m, k = map(int, input().split())
arr = [] # 배열
for _ in range(n):
    arr.append(list(map(int, input().split())))
op_arr = [] # 회전 연산
for _ in range(k):
    op_arr.append(list(map(int, input().split())))

ans = float("inf")
perm_list = list(permutations(range(k), k)) # 회전 연산 인덱스 순열 구하기
for seq in perm_list:
    after = copy.deepcopy(arr)
    for cur in seq: # 회전 연산 순서대로 수행 
        after = rotate(after, op_arr[cur]) 
    
    # 배열의 값 구하기
    min_row = float("inf")
    for r in range(n):
        min_row = min(min_row, sum(after[r]))
    ans = min(ans, min_row)
print(ans)