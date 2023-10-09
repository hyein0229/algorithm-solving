N = int(input())
K = int(input())
sensors = list(map(int, input().split()))
sensors.sort() # 센서들의 위치를 오름차순 정렬

if K >= N: # 센서 수보다 K가 더 크면 각 센서 위치에 집중국을 모두 세우면 되므로 답은 0
    print(0)
    exit(0)

distances = []
for i in range(N-1): # 각 센서간의 거리 구하기
    distances.append(sensors[i+1] - sensors[i])
# distances.sort(reverse=True) # 거리를 내림차순 정렬

# for i in range(K-1): # K-1 개 
#     distances.pop(0) # 가장 큰 원소부터 차례대로 제거
# print(sum(distances))
distances.sort()
print(sum(distances[:n-k])) # 가장 거리가 작은 거부터 n-k개의 합