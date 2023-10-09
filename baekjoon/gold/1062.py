import sys
from itertools import combinations

N, K = map(int, input().split())
words = [sys.stdin.readline().rstrip()[4:-4:] for _ in range(N)] # 입력받은 단어에서 공통된 'anta' 'tica'는 제거하고 저장

def check(word, comb):
    for a in word:
        if a not in comb and a not in 'antic':
            return False
    return True

if K < 5: # anta tica 에서 이미 5개의 글자가 포함되므로
    print(0)
else:
    alphas = set() # 존재하는 나머지 글자 집합
    for word in words: # 단어 순회
        for a in word:
            if a not in 'antic': 
                alphas.add(a)

    # 존재하는 나머지 글자를 모두 배울 수 있는 경우 N개의 모든 단어를 다 읽을 수 있음, 중요!!
    # combination 구할 때 남은 글자보다 K-5 가 더 큰 경우 구한 combi의 길이가 0이 되므로 for문이 동작하지 않음 -> 오류
    if len(alphas) <= K - 5: 
        print(N)
        exit(0)

    combi = list(combinations(list(alphas), K - 5)) # 모든 글자 조합 구하기
    # 모든 글자 조합에 대해 읽을 수 있는 최대의 단어 개수 구하기
    answer = 0
    for i in range(len(combi)): # 글자 조합 순회
        cnt = 0 # 읽을 수 있는 단어 개수 카운트
        for word in words: 
            if check(word, combi[i]): # 현재 글자 조합을 가르쳤을 때 단어를 읽을 수 있는지 확인
                cnt += 1
        answer = max(answer, cnt)
    print(answer)