import sys

N = int(input())
A = list(map(int, input().split()))
A.sort() # 오름차순 정렬

answer = 0
for i in range(N):
    tmp = A[:i] + A[i+1:] # 중복되는 숫자가 있으므로 i를 제외한 리스트에서 모두 탐색해야 함!!
    l, r = 0, len(tmp) - 1 # 투포인터
    while l < r:
        result = tmp[l] + tmp[r]
        if result < A[i]:
            l += 1
        elif result > A[i]:
            r -= 1
        elif result == A[i]:
            answer += 1
            break
print(answer)