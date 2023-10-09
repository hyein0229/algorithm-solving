n,m = map(int,input().split())
books = list(map(int, input().split()))

# 음수, 양수 나누기
negative = []
positive = []
for book in books:
    if book < 0:
        negative.append(book)
    else:
        positive.append(book)

distance = []
negative.sort() # 오름차순 정렬 -> 절댓값은 내림차순(거리가 먼 것부터)
for i in range(len(negative)//m): # m개씩 분류
    distance.append(abs(negative[m*i]))
if len(negative) % m != 0:
    distance.append(abs(negative[(len(negative)//m)*m]))
    
positive.sort(reverse = True) # 내림차순 정렬
for i in range(len(positive)//m):
    distance.append(positive[m*i]) 
if len(positive) % m != 0:
    distance.append(positive[(len(positive)//m)*m])    
    
distance.sort() # 절댓값 오름차순
last = distance.pop() # 가장 위치가 먼 책을 마지막에
answer = last + 2 * sum(distance)
print(answer)    