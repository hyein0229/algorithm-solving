from collections import deque

N = int(input())
arr = list(map(int, input().split()))

if N == 1: # 숫자가 한개면 다음 수는 모든 수 가능
    print('A')
elif N == 2:
    if arr[0] == arr[1]: # 두 개의 숫자가 같은 경우
        print(arr[0])
    else: #  다른 경우
        print('A')
else: # N이 3 이상일 때
    # f(x) = a * x + b 수식을 이용하여 풀기
    if arr[0] == arr[1]:
        a = 0
    else:
        a = (arr[2] - arr[1]) // (arr[1] - arr[0]) # 기울기  = f(1) - f(0) / 1 - 0 
    b = arr[1] - arr[0] * a # b = y - a*x

    for i in range(N-1):
        expect = arr[i] * a + b # 예측값
        if expect != arr[i+1]: # 예측값과 다음값이 다르면 답이 없음
            print('B')
            exit(0)

    print(arr[-1] * a + b)