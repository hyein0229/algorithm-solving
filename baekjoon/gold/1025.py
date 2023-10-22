import sys
import math
input = sys.stdin.readline

'''
문제: 1025 제곱수 찾기
난이도: Gold 5
알고리즘: 브루트포스
풀이 방법: 가능한 첫째 항, 가능한 행과 열 공차의 조합에 따라 모두 완전 탐색하면 된다.
        구현은 쉬웠는데.. 질문 게시판의 모든 반례에 대해서도 답이 잘 나와서 삽질을 오래했다.
        처음에 check() 의 while 문에서 인덱스가 경계를 넘어설 때까지 끝까지 문자열을 이어붙인 후
        하나의 만들어진 문자열에 대해 완전제곱수임을 확인했던 것이 잘못되었다...
        칸은 1개 이상 선택이 가능하므로 이어붙일 때마다 완전제곱수를 확인해야한다..바보같은 삽질..
'''
def check(a, b, rd, cd):
    i, j = a, b
    if rd == 0 and cd == 0: # 공차가 모두 0인 경우엔 한 칸만 선택하는 경우이므로
        if is_square(arr[i][j]): # 현재 칸의 숫자가 완전제곱수인지 확인
            ans.add(arr[i][j])
        return
    
    # 등차수열에 따라 칸을 선택
    number = str(arr[i][j])
    while True:
        i_ = i + rd
        j_ = j + cd
        if i_ < 0 or i_ >= n or j_ < 0 or j_ >= m:
            break
        number += str(arr[i_][j_])
        i += rd
        j += cd
        # 삽질한 구간.. 숫자를 이어붙일 때마다 완전제곱수 확인하기!
        if is_square(number):
            ans.add(int(number))

def is_square(tmp):
    # 완전제곱수인지 확인
    tmp = int(tmp)
    if int(math.sqrt(tmp)) ** 2 == tmp:
        return True
    return False

n, m = map(int, input().split())
arr = []
for _ in range(n):
    arr.append(list(map(int, input().rstrip())))

ans = set()
for a in range(n): # 행 등차수열의 첫째항
    for b in range(m): # 열 등차수열의 첫째항
        for rd in range(-n, n): # 행 등차수열의 공차
            for cd in range(-m, m): # 열 등차수열의 공차
                check(a, b, rd, cd)

if not ans:
    print(-1)
else:
    print(max(ans))