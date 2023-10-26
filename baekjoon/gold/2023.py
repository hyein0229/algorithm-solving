import sys
import math
input = sys.stdin.readline
'''
문제: 2023 신기한 소수
난이도: Gold 5
알고리즘: 백트래킹
풀이 방법: dfs + 백트래킹을 사용하였다. 1~9 숫자를 차례대로 붙여가면서 n 자리 숫자를 만드는데
        다음 숫자가 소수인지 아닌지 판별하여 소수가 아니면 더 이상 n 자리까지 탐색하지 않도록 가지치기를 한다.
        
'''
def is_prime_number(num):
    # 소수인지 판별
    for i in range(2, int(math.sqrt(num)) + 1):
        if num % i == 0:
            return False
    return True

def dfs(num):
    # n 자리 소수를 만들었으면 답에 추가
    if len(num) == n:
        print(num)
        return

    # 숫자 문자열 만들기
    for i in range(1, 10):
        # 다음 숫자를 붙였을 때 소수인것만 탐색
        next_num = int(num + str(i))
        if next_num != 1 and is_prime_number(next_num):
            dfs(num + str(i))

n = int(input())
ans = []
dfs('')