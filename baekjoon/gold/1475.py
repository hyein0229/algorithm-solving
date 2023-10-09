N = list(map(int, input()))
numbers = [0] * 9
for i in range(len(N)):
    idx = N[i]
    if idx == 9: # 9는 6을 뒤집어서 사용할 수 있으므로 6을 카운트
        idx = 6
    numbers[idx] += 1 # 카운트
numbers[6] = (numbers[6] + 1) // 2 # 개수가 홀수이면 한 세트 더 필요하므로
print(max(numbers))