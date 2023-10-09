from itertools import combinations

n, l, r, x = map(int, input().split())
A = list(map(int, input().split())) # 문제 난이도

if n <= 1: # 문제가 한 개만 있을 때
    print(0)
else:
    cnt = 0
    for k in range(2, n+1):
        lst = [i for i in range(n)] # 문제 인덱스 리스트
        comb = list(combinations(lst, k)) # k개의 인덱스 조합

        for i in range(len(comb)): # 각 조합에 대해 완전 탐색
            a = [A[idx] for idx in comb[i]]
            if max(a) - min(a) < x: # 가장 어려운 문제와 가장 쉬운 문제 난이도 차이가 x보다 작은지 확인
                continue

            if l <= sum(a) <= r: # 문제 난이도 합 비교
                cnt += 1
    print(cnt)