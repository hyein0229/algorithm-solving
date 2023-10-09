n, k = map(int, input().split())
buycnt = 0
while bin(n).count('1') > k:
    idx = bin(n)[::-1].index('1') # 1이 이진수의 오른쪽에서 몇 번째에 처음 나오는지 찾기
    n += 2 ** idx
    buycnt += 2 ** idx
print(buycnt)