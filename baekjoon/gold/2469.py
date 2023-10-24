import sys
input = sys.stdin.readline
'''
문제: 2469 사다리 타기
난이도: Gold 5
알고리즘: 구현
풀이 방법: 처음엔 백트래킹으로 풀었는데 계속 시간초과가 나와서 처음부터 다른 방법을 생각해야 했다.
        먼저, 위에서 아래로 감추어진 가로줄이 있는 위치까지 내려가고, 아래에서 위로 올라가 도착한 위치를 기록한다.
        감추어진 가로줄 위 아래로 서로 도착한 참가자를 비교하여 다르면 사다리를 놓고, 같으면 놓지 않는다.
        예외없이 다르다면 무조건 놓는다. 선택지는 놓고, 안놓고 두가지 이므로 다른데 안놓는다면 100% 목표 도착 순서를 만들 수 없다.
        이렇게 가로줄을 완성한 다음 확인한다. 사다리를 놓았다면 옆의 열과 참가자와 서로 스위칭되는 것이므로 대각선에 있는 것끼리 같은지 확인한다.
'''
def play_ladder(down, number):
    r, c = 0, number
    if down == -1:
        r = n-1
    while r != pos:
        # 가로 막대가 있으면 옆으로 이동
        if c != 0 and ladder[r][c-1] == '-': # 왼쪽
            c -= 1
        elif c != k-1 and ladder[r][c] == '-': # 오른쪽
            c += 1
        r += down
    return c

k = int(input())
n = int(input())
goal = input().rstrip() # 도착할 최종 순서
pos = 0
ladder = []
for i in range(n):
    row = list(input().rstrip())
    if row[0] == '?': # 감추어진 가로 줄 위치
        pos = i
    ladder.append(row) 

# 감추어진 가로 줄이 있는 곳까지 위에서 아래로, 아래서 위로 사다리 타기
down_lst = [0] * k
up_lst = [0] * k
for i in range(k):
    dc = play_ladder(1, i)
    uc = play_ladder(-1, i)
    down_lst[dc] = i
    # 주의, 아래에서 출발하는 참가자는 목표 도착 순서에 해당하는 참가자의 출발임
    up_lst[uc] = ord(goal[i]) - ord('A') 

# 감추어진 가로 줄의 사다리 놓기
ans = ''
i = 0
while i < k:
    # 위 아래가 서로 다른 경우엔 사다리 놓기
    if down_lst[i] != up_lst[i]:
        ans += '-*'
        i += 1
    else:
        ans += '*'
    i += 1
ans = ans[:k-1]

# 사다리를 놓은 것이 맞는지 확인
# 사다리를 놓은 부분에서 서로 스위칭 했을 때 같은지 확인
check = True
for i in range(k-1):
    if ans[i] == '-' and up_lst[i] != down_lst[i+1]:
        check = False
        break
    elif i > 0 and ans[i-1] == '-' and up_lst[i] != down_lst[i-1]:
        check = False
        break

if check:
    print(ans)
else:
    print('x'*(k-1))