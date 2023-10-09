from itertools import combinations

N = int(input())
if N <= 10:
    print(N)
else:
    numbers = []
    for i in range(1, 11): # 0~9 이므로 최대 10개까지 가능
        for comb in combinations(range(0, 10), i): # 0~9 숫자 중 i개의 조합
            decreased = sorted(list(comb), reverse=True) # 감소하는 수로 만들기
            numbers.append(int(''.join(map(str, decreased))))
    numbers.sort() # 감소하는 수를 오름차순으로 정렬
    
    if N < len(numbers):
        print(numbers[N])
    else:
        print(-1)