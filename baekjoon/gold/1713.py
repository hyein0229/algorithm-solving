import sys

N = int(input())
M = int(input())
lst = list(map(int, input().split()))

pics = [] # 사진 틀
voting = [0] * 101
for i in range(len(lst)):
    if lst[i] not in pics: 
        if len(pics) == N: # 비어있는 사진틀이 없는 경우 사진 제거
            selected = 0 
            min_vote = M+1
            for j in range(len(pics)): # 사진틀에 게시된 후보 중 추천 수가 가장 적은 후보 자리 찾기
                if voting[pics[j]] < min_vote:
                    min_vote = voting[pics[j]]
                    selected = j

            voting[pics[selected]] = 0 # 삭제된 경우 추천 수 0으로 초기화
            pics.pop(selected) # 그 자리에 새로운 후보 게시

        pics.append(lst[i]) # 사진 추가
    voting[lst[i]] += 1 # 추천 수 증가

pics.sort()
for number in pics:
    print(number, end=' ')