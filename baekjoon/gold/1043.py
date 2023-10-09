N, M = map(int, input().split())
group = set(map(int, input().split()[1:])) # 진실을 아는 사람들
parties = []
for _ in range(M):
    parties.append(set(map(int, input().split()[1:])))

for _ in range(M):
    for party in parties:
        if party & group: # 진실이 아는 사람들이 참여한 파티인지 확인
            group = group.union(party) # 진실을 아는 사람들이 새로 생겨남

answer = 0 # 과장된 사실을 말할 수 있는 파티 개수
for party in parties:
    if party & group: 
        continue
    answer += 1
print(answer)