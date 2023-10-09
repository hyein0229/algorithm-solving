import copy

n = int(input())
state = list(map(int, input()))
goal = list(map(int, input()))
press = 0 # 스위치 누른 개수 카운트

def switch(cur):
    global press
    for i in range(1, n):
        if cur[i-1] != goal[i-1]:
            press += 1
            for j in range(i-1, i+2):
                if j >= n:
                    break
                cur[j] = int(not cur[j])

        if cur == goal:
            print(press)
            exit(0)

# 첫번째 스위치를 누르지 않고
tmp = copy.deepcopy(state)
switch(tmp)
# 첫번재 스위치를 눌렀을 경우
press = 1
state[0] = int(not state[0])
state[1] = int(not state[1])
switch(state)
# 정답이 없을 경우
print(-1)
