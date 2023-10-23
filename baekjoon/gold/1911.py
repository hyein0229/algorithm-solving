import sys
import math
input = sys.stdin.readline
'''
문제: 1911 흙길 보수하기
난이도: Gold 5
알고리즘: 그리디
풀이 방법: 먼저, 웅덩이는 겹치지 않으므로 시작점 기준 오름차순으로 하여 정렬을 한다.
        그 다음 차례대로 배열을 탐색하면서 널빤지를 놔주면 되는데 이때 주의할 것은 널빤지의 길이는 l로 고정이므로
        웅덩이의 길이가 l의 배수가 아닌 경우 웅덩이의 마지막 위치를 넘어서서 널빤지가 놓여질 수 있다는 것이다. 
        이럴 경우 다음 웅덩이를 덮기 위한 널빤지의 시작점은 다음 웅덩이 시작점과 널빤지의 마지막 위치+1 중 큰 값이 된다.
        따라서, last 변수를 사용하여 마지막 널빤지 위치를 저장하고 start 값을 구해주어야 한다.
        또한, 이미 이전에 놓은 널빤지가 현재 웅덩이까지 덮은 경우를 위해 length <= 0 인 경우 예외처리해주어야 한다.
'''
n, l = map(int, input().split())
arr = []
for _ in range(n):
    arr.append(list(map(int, input().split())))
arr.sort(key=lambda x : x[0]) # 시작 위치 오름차순 정렬

last = -1 # 마지막 널빤지가 있는 위치
cnt = 0 # 총 필요한 널빤지 카운트
for i in range(n):
    start = max(arr[i][0], last+1) # 널빤지가 필요한 시작점
    length = arr[i][1] - start # 필요한 길이
    if length <= 0:
        continue

    need = math.ceil(length / l) # 현재 필요한 널빤지 개수
    last = (start + need * l) - 1 # 마지막 널빤지의 위치 갱신
    cnt += need
print(cnt)