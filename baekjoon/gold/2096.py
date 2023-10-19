import sys
input = sys.stdin.readline
'''
문제: 2096 내려가기
난이도: Gold 5
알고리즘: dp, 슬라이딩 윈도우
풀이 방법: 메모리 제한이 없다면 2차원 배열을 만들고 각 위치에서의 min, max 값을 갱신하면서 내려가면 된다.
        전형적인 dp 문제이나 메모리가 4mb 로 제한되어 있다. 메모리 사용을 낮추기 위해선 슬라이딩 위도우 기법을 이용할 수 있다.
        슬라이딩 윈도우란 메모이제이션 시 더 이상 사용하지 않는 값은 저장하지 않고 배열을 계속 갱신하여 메모리를 최소한으로 유지시키는 것이다.
        만약, 현재 행까지의 min 점수 값을 구할 경우 바로 윗 행의 각 열에서의 min 값과 현재 행 값만 필요하다. 
        따라서, 굳이 2차원 배열로 모든 값을 저장하지 않고 일차원 배열만 사용하여 구현할 수 있다.
        한 번에 모든 입력을 받지 않고 행 입력(현재 행)을 받을 때마다 이전 행까지 기록된 min, max 값 + 현재 값 비교하여
        DP 배열을 갱신해주는 것이다. 메모리 제한이 있을 경우를 대비해 이러한 방법을 기억해두는 것이 좋을 것 같다.
'''
n = int(input())
# 첫번째 행 숫자 입력
arr = list(map(int, input().split()))
minDP = arr
maxDP = arr

# 숫자 3개를 입력받을 때마다 dp 를 갱신
for _ in range(n-1):
    arr = list(map(int, input().split())) # 행 입력
    # 현재 위치에 올 수 있는 max, min 값을 계산하여 dp 갱신
    maxDP = [arr[0] + max(maxDP[0], maxDP[1]), arr[1] + max(maxDP), arr[2] + max(maxDP[1], maxDP[2])]
    minDP = [arr[0] + min(minDP[0], minDP[1]), arr[1] + min(minDP), arr[2] + min(minDP[1], minDP[2])]

print(max(maxDP), min(minDP))
