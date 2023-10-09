n, k = map(int, input().split())
belt = list(map(int, input().split()))
robot = [0]*n

step = 0
while True: # 내구도가 0인 칸의 개수가 k개 이상이면 과정 종료

    belt = [belt[-1]] + belt[:-1] # 벨트 한 칸씩 회전
    robot = [0] + robot[:-1] # 로봇도 벨트와 함꼐 회전
    # 가장 먼저 올라간 로봇부터 한 칸씩 이동, 이동할 수 없다면 가만히
    for i in range(n-1, -1, -1):
        if i == n-1: # 내리는 위치에 로봇이 있다면 로봇을 내린다
            if robot[i] == 1:
                robot[i] = 0
        elif i == 0: # 올리는 위치의 칸의 내구도가 0이 아니면 로봇을 올린다
            if belt[i] != 0:
                robot[i] = 1
                belt[i] -= 1 # 내구도 감소
        elif robot[i] == 1 and robot[i+1] != 1 and belt[i+1] >= 1: # 다음 칸에 로봇이 없으며 그 칸의 내구도가 1이상일 때 이동
            robot[i+1] = 1
            belt[i+1] -= 1
            robot[i] = 0
    
    step += 1 # 한 단계 진행
    if belt.count(0) >= k: # 내구도가 0인 칸의 개수가 k개 이상일 때 과정 종료
        break

print(step)