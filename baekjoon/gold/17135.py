import sys
import copy
from itertools import combinations

N, M, D = map(int, input().split())
enemies = [] # 적의 위치 저장
for i in range(N):
    row = list(map(int, sys.stdin.readline().split()))
    for j in range(M): 
        if row[j] == 1: # 적이 위치한 칸이면
            enemies.append((i, j))
enemies.sort(key=lambda x : (x[1], -x[0])) # 열은 오름차순, 행은 내림차순으로 정렬 (가장 왼쪽에 있는 가까운 적부터 탐색하기 위해)
combi = list(combinations([i for i in range(M)], 3)) # 궁수가 위치할 3개의 위치 조합

if len(enemies) == 0: # 적이 존재하지 않으면 0 출력
    print(0)
    exit(0)

answer = 0
for i in range(len(combi)): # 모든 조합에 대해 완전 탐색

    temp = copy.deepcopy(enemies)
    archers = combi[i] # 3명의 궁수 위치

    attack = 0 # 공격하여 제거한 적의 수
    alive = len(enemies) # 살아있는 적의 수
    while alive > 0: # 적이 모두 게임에서 제외될 때 까지

        targets = set() 
        for col in archers: # 각 3명의 궁수가 공격할 적 결정

            target = -1 # 공격 대상
            min_d = N*M # 가장 가까운 적과의 거리
            for j in range(len(temp)): # 가장 가까운 적 탐색
                dist = abs(temp[j][0] - N) + abs(temp[j][1] - col) # 적과 궁수의 거리
                if dist <= D and dist < min_d: # 거리가 D 이하이고 현재 최소 거리보다 더 작은 거리이면
                    target = temp[j] # 더 가까운 공격 대상으로 갱신
                    min_d = dist

            if target != -1: # 공격이 가능한 대상이 있다면
                targets.add(target)
                if alive == 1: # 남은 적이 하나라면 더 이상 탐색이 의미없으므로 탈출
                    break

        attack += len(targets) # 공격으로 제거된 적의 수 추가
        alive -= len(targets) 
        for target in targets:
            temp.remove(target) # 공격 대상 제거
        
        # 살아남은 적은 아래로 한 칸 이동
        idx = 0
        while idx < len(temp):
            if temp[idx][0] + 1 == N: # 이래로 이동한 칸이 성이 있는 칸이면
                temp.remove(temp[idx])
                alive -= 1  # 게임에서 제외
            else:
                temp[idx] = (temp[idx][0] + 1, temp[idx][1]) # 칸 이동 갱신
                idx += 1

    answer = max(answer, attack)    

print(answer)