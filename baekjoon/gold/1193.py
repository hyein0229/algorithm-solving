import sys

x = int(input())
i = 0
total = 0
while total + i <= x:
    total += i
    i += 1

if total == x:
    if i % 2 != 0:
        n, k = i-1, 1
    else:
        n, k = 1, i-1

elif total + 1 == x:
    if i % 2 != 0:
        n, k = i, 1
    else:
        n, k = 1, i
else:
    diff =  x - (total + 1)
    if i % 2 != 0:
        n, k = i, 1
        n -= diff
        k += diff
    else:
        n, k = 1, i
        n += diff
        k -= diff
    
print('{}/{}'.format(n, k))