import sys
input = sys.stdin.readline
'''
문제: 1790 수 이어 쓰기2
난이도: Gold 5
알고리즘: 구현, 수학
풀이 방법: 문제를 보자마자 이건 수학적인 규칙을 이용해서 풀어야 겟다는 것은 알았지만 구현이 쉽지 않았다.
'''
n, k = map(int, input().split())

last_num = 0 # 마지막으로 사용된 숫자
num_len = 1 # 현재 자릿수
num_count = 9 # 현재 자릿수의 총 숫자 개수

# k 가 현재 자릿수의 모든 숫자 개수보다 클 때까지 k 갱신
while k > num_count * num_len:
    k -= (num_count * num_len) # 남은 자릿수 갱신
    last_num += num_count # 마지막으로 사용된 숫자
    num_len += 1 # 현재 자릿수 증가
    num_count = num_count * 10 # 그 다음 자릿수에서의 숫자 총 개수

# k번째 해당하는 숫자 구하기
# (마지막 숫자+1) + (남은 k-1번째를 현재 자릿수로 나눈 몫)
# 남은 k-1번째를 현재 자릿수로 나눈 나머지는 해당 숫자, 예를 들어 103 에서의 각 1, 0, 3 의 위치를 구할 수 있다.
last_num = (last_num+1) + ((k-1) // num_len)
if last_num > n:
    print(-1)
else:
    print(str(last_num)[(k-1) % num_len])