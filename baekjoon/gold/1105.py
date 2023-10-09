L, R = input().split()
if L == R:
    print(L.count('8'))
elif len(L) != len(R): # 자릿수가 다른 숫자면 무조건 8이 없는 수가 있음
    print(0)
else:
    cnt = 0
    for i in range(len(L)): # 가장 큰 자릿수부터 비교
        if L[i] == R[i]:
            if L[i] == '8': 
                cnt += 1
        else:
            break
    print(cnt)