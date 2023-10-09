import sys
input = sys.stdin.readline

N = int(input())
u = [int(input()) for _ in range(N)]
u.sort() # 숫자 오름차순 정렬
answer = 0

u_sum = set()
for x in u:
    for y in u:
        u_sum.add(x+y)
# x + y = k - z 임을 이용 -> 만족하는 최대 k 찾기
for i in range(N-1, -1, -1): # 역순으로 가장 큰 수부터 k가 될 수 있는지 확인
    for j in range(i+1):
        if (u[i]-u[j]) in u_sum:
            answer = u[i]
            break
    if answer: 
        break
print(answer)