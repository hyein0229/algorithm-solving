import sys
from heapq import heappush, heappop
input = sys.stdin.readline
'''
문제: 1107 리모컨
난이도: Gold 5
알고리즘: 브루트포스
풀이 방법: '최소' 버튼을 누르는 횟수를 구하라고 했을 때 최소 heap 을 사용해서 풀어야겠다고 생각했다.
        heap 에서 n번과 거리가 가까운 번호부터, 즉 거리 차이가 최소인 것부터 꺼내어 해당 번호로 이동할 수 있는지를 확인한다.
        이때, heap 에 넣어주는 채널 번호의 범위를 생각해야 하는데,
        n이 100보다 크고 만약 고장난 숫자 버튼때문에 다른 채널번호로 한번에 이동할 수 없을 때 n-100 번 + 를 눌러 이동해야 한다.
        적어도 정답은 n-100 번보다 작거나 같으므로 범위를 100번 부터 n + (n-100) 까지 잡았다.
        n 이 100 보다 작은 경우엔 굳이 현재 100번에서 100보다 큰 채널로 이동할 필요가 없으므로
        0~100 번 채널까지 범위를 잡았다.
        정답 비율이 낮은 데 풀어내서 더욱 뿌듯..하다.
'''
n = int(input())
m = int(input())
buttons = set()
# 고장난 버튼이 있다면 입력받기
if m > 0:
    buttons.update(list(map(int, input().split())))

# 고장난 버튼이 없어 n 채널을 입력하여 이동할 수 있는 경우
if m == 0:
    print(min(abs(n-100), len(str(n))))
# 숫자 버튼이 모두 고장난 경우 100번부터 거리만큼 + 또는 - 로만 이동
elif m == 10:
    print(abs(n - 100))
# 그외 고장난 버튼이 있는 경우
else:
    heap = []
    if n >= 100: # 100번보다 크거나 같은 채널일 때
        for i in range(100, n+n-100):
            heappush(heap, (abs(n - i), i))
    else: # 100번보다 작은 채널일 때
        for i in range(101):
            heappush(heap, (abs(n - i), i))
        
    cnt = 0 # 버튼 누른 횟수
    flag = True # 이동 가능 여부
    while heap:
        distance, num = heappop(heap) # 거리 차가 작은 순서대로 pop
        cnt = len(str(num)) + distance # num번으로 이동 후 +/- 버튼으로 이동 할 때 누르는 버튼 횟수 
        flag = True

        # 채널이 100번이면 현재 채널이므로 종료
        if num == 100:
            break
        
        # 100번이 아닌 다른 채널이면 해당 채널을 버튼을 눌러 이동이 가능한지 확인
        for c in list(set(list(str(num)))): # 중복된 숫자는 제거하여 탐색
            if int(c) in buttons: # 고장난 버튼의 숫자가 있다면 이동 불가능
                flag = False
                break
        # 해당 채널로 이동이 가능하다면 종료
        if flag:
            break
    # 이동이 가능
    if flag:
        print(min(abs(n-100), cnt))

    # 다른 채널로 한번에 이동이 불가능한 경우 +/-로만 이동
    else:
        print(abs(n - 100))