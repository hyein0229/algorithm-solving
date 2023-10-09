S = input().rstrip()
T = input().rstrip()

# 백트래킹을 이용한 완전탐색은 시간초과, 그리디 알고리즘 이용
while len(S) != len(T):
    if T[-1] == 'A': # T의 마지막 문자가 A라면 제거
        T = T[:-1]
    else: # T의 마지막 문자가 B라면 제거하고 뒤집기
        T = T[:-1]
        T = T[::-1]

if S == T:
    print(1)
else:
    print(0)