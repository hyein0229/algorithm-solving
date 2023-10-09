N, K = map(int, input().split())
number = input()
stack = []
for num in number:
    while stack and stack[-1] < num and K > 0: # stack의 마지막 숫자보다 크다면
        stack.pop() # num보다 작은 앞 숫자들은 모두 꺼내어 제거
        K -= 1
    stack.append(num) # 큰 숫자 삽입
    
if K > 0: # k개 미만으로 지운 경우 --> number 이 내림차순 정렬을 가진 경우 K개미만으로 제거될 수 있다. 중요!!
    print(''.join(stack[:-K])) # 뒤에 있는 숫자를 남은 k개만큼 제거하고 출력
else: # 모두 제거되었다면 그대로 합
    print(''.join(stack))