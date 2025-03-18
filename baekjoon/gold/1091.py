import sys
input = sys.stdin.readline
'''
문제: 1091 카드 섞기
난이도: Gold 4
알고리즘: 구현, 시뮬레이션
풀이 방법: 문제 이해하기가 좀 헷갈리지만 구현에는 어려움이 없었던 문제이다. 문제 규칙대로 카드가 주어진 플레이어에게 분배되는지
        확인하고, 안되는 경우 카드를 처음 상태로 돌아올 때까지 섞기를 반복한다.
        (지금도 이해안되는 것은 처음 카드의 순서가 0,1,2 ~ N-1 순서로 정해져 있다는 것인가..? 지금 풀이로 풀려면 정해져있다는 것인데
        문제에는 그런 말이 없어 그 부분은 아직 의문이다)
'''
n = int(input())
p = list(map(int, input().split()))
s = list(map(int, input().split()))

start = [i for i in range(n)]
cards = [i for i in range(n)] 
cnt = 0
while True:
    # 카드가 주어진 P에 맞게 분배되는지 확인
    flag = True
    for index, number in enumerate(cards):
        if p[number] != index % 3:
            flag = False
            break
    if flag:
        print(cnt)
        break

    # 카드 섞기
    after = [0] * n
    for i in range(n):
        after[s[i]] = cards[i]
    cards = after[:]
    # 처음 카드의 순서로 돌아왔다면 종료
    if cards == start:
        print(-1)
        break

    cnt += 1