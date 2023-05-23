import sys
from itertools import combinations

N, M = map(int, input().split())
city = []
for _ in range(N):
    city.append(list(map(int, sys.stdin.readline().split())))

chicken_pos = []
for i in range(N):
    for j in range(N):
        if city[i][j] == 2:
            chicken_pos.append((i, j))

def chicken_distance(x, y):
    min_d = 1e9
    for cur in left_chicken:
        distance = abs(x - cur[0]) + abs(y - cur[1])
        min_d = min(distance, min_d)
    return min_d

min_city_d = 1e9
for i in range(1, M+1):
    selected = combinations(chicken_pos, i)
    for comb in selected:
        left_chicken = []
        for r, c in comb:
            left_chicken.append((r, c))

        city_d = 0 # 도시의 치킨거리
        for r in range(N):
            for c in range(N):
                if city[r][c] == 1:
                    city_d += chicken_distance(r, c)
                    
        min_city_d = min(min_city_d, city_d)
        
print(min_city_d)