import sys
import math
input = sys.stdin.readline

N = int(input())
arr = list(map(int, input().split()))

# n 자리 수 중 k번째 수 구하기
# 주어진 수가 몇 번째인지 구하기
if arr[0] == 1:
    k = arr[1]
    nums = [i for i in range(1, N+1)]
    tmp = []

    for i in range(N, 0, -1):
        fac = math.factorial(i-1)
        step = (k-1) // fac # 인덱스 0부터 시작이므로 k-1 을 나누어줌
        tmp.append(nums[step]) 
        nums.remove(nums[step])
        k -= step * fac
    print(*tmp)

else:
    target = arr[1:] # 임의의 순열
    nums = [i for i in range(1, N+1)]
    k = 1
    for i in range(N, 0, -1):
        fac = math.factorial(i-1)
        step = nums.index(target[N-i]) # target 숫자의 nums 에서의 위치
        nums.remove(nums[step])
        k += step * fac
    print(k) 