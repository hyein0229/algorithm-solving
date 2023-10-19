import sys
input = sys.stdin.readline
'''
문제: 11509 풍선 맞추기
난이도: Gold 5
알고리즘: 그리디
풀이 방법: 어떻게 풀지 감이 안잡혀 찾아본 문제이다. 이런 풀이의 그리디는 처음 접해보는 것 같다.
        높이별 화살의 떠 있는 개수를 일차원 배열을 만들어 관리하고 h 배열을 돌면서
        해당 높이의 화살이 없으면 발사하고 발사한 높이-1의 화살을 증가한다.
        해당 높이의 화살이 있는 경우엔 해당 높이의 화살의 개수는 줄이고 높이-1 위치의 화살은 증가한다.
'''

n = int(input())
H = list(map(int, input().split()))

if n == 1: # 풍선이 하나면 화살 1개 필요
    print(1)
else:
    arrows = [0] * 1000001 # 높이별 떠 있는 화살 개수 기록
    for height in H:
        # 해당 높이에 떠 있는 화살이 있으면
        if arrows[height] > 0:
            # 풍선을 터뜨리고 화살의 높이는 -1
            arrows[height] -= 1
            arrows[height-1] += 1
        # 해당 높이의 화살이 없다면 화살을 날려 풍선을 터뜨리고 높이는 -1
        else:
            arrows[height-1] += 1
    print(sum(arrows))