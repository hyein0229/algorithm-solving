import copy

n = int(input())
arr = list(map(int, input().split()))
s = int(input())
# 사전순 뒤 -> 앞의 숫자가 커야함
if n == 1:
    print(arr[0])
    exit(0)

# s가 남는 만큼 가장 큰 값을 앞으로 빼오기
if s != 0:
    tmp = copy.deepcopy(arr)
    tmp.sort(reverse=True) # 내림차순 정렬

    l = 0 # 정렬이 된 위치
    while s > 0:
        i = 0 # tmp 에서의 인덱스
        while i < n:
            pos = arr.index(tmp[i]) # 현재 큰 수의 arr에서의 위치
            if pos == l: # 알맞는 위치에 숫자가 있다면
                l += 1
                break

            if 0 < pos - l <= s: # 필요한 교환 횟수가 s보다 작거나 같은 경우만
                s -= (pos - l)
                arr.pop(pos)
                arr.insert(l, tmp[i])
                l += 1
                break    
            i += 1 # 그 다음 큰 수
        if arr == tmp: # 내림차순 정렬이 다 된 경우
            break

for i in range(len(arr)):
    if i != len(arr)-1:
        print(arr[i], end=' ')
    else:
        print(arr[i])