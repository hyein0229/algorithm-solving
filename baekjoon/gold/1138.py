N = int(input())
lst = list(map(int, input().split()))
sequence = []
for i in range(N-1, -1, -1): # 큰 수부터
    sequence.insert(lst[i], i+1)
for number in sequence:
    print(number, end=' ')