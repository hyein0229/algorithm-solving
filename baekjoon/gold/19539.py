import sys

N = int(input())
heights = list(map(int, input().split()))

# 1, 2 물뿌리개를 동시에 사용하므로 한번 물을 뿌릴 때마다 3씩 증가함
# --> 모든 높이 합이 3의 배수가 되어야 함!!
# 나무가 자라는 데 걸리는 총 일수 = 전체 나무 높이 합 / 3
# 2만큼 성장시키는 물뿌리개를 사용할 수 잇는 횟수 >= 총 일수 여야 함!!
# 위가 성립되지 않는다면 1 물뿌리개를 더 많이 사용해야 하므로 동시에 3씩 증가해야하는 조건이 성립되지 않음

if sum(heights) % 3 == 0:
    cnt = 0 # 2 물뿌리개를 사용할 수 있는 횟수
    for h in heights:
        cnt += h // 2
    if cnt >= (sum(heights) / 3): # 총 일수보다 커야 함
        print("YES")
    else:
        print("NO")

else: # 모든 나무 높이 합을 3으로 나눈 나머지가 0이 아니면 만들 수 없다.
    print("NO")