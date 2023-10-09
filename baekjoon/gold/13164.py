n, k = map(int, input().split())
heights = list(map(int, input().split()))

diff = []
for i in range(n-1):
    diff.append(heights[i+1] - heights[i])

diff.sort(reverse=True)
print(sum(diff[k-1:])) # 그룹 k개 -> 경계 k-1개