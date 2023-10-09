N = int(input())
buildings = list(map(int, input().split()))

answer = 0
for i in range(N):
    cnt = 0 # 보이는 건물 카운트
    max_gradient = -float('inf')
    for j in range(i+1, N): # 현재 빌딩의 오른쪽에 있는 빌딩 탐색
        gradient = (buildings[j] - buildings[i]) / (j-i)
        if gradient > max_gradient: # 기울기가 더 커질 때
            max_gradient = gradient
            cnt += 1
    min_gradient = float('inf')
    for j in range(i-1, -1, -1): # 현재 빌딩의 왼쪽에 있는 빌딩 탐색
        gradient = (buildings[j] - buildings[i]) / (j-i) # j-i 에서 음수가 되므로 오른쪽과 반대로 생각
        if gradient < min_gradient: # 왼쪽이면 오른쪽과 반대로 기울기가 최소가 될 때
            min_gradient = gradient
            cnt += 1
    answer = max(answer, cnt)
print(answer)