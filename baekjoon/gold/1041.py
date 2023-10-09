N = int(input())
numbers = list(map(int, input().split()))

if N == 1: # 주사위 한개인 경우
    print(sum(numbers) - max(numbers)) # 주사위의 가장 작은 5개의 숫자 합
else:
    answer = 0
    min_arr = []
    # 서로 마주보는 숫자 두개 중 작은값을 추가
    min_arr.append(min(numbers[0], numbers[5]))
    min_arr.append(min(numbers[1], numbers[4]))
    min_arr.append(min(numbers[2], numbers[3]))
    min_arr.sort() # 오름차순 정렬 

    # 면이 1개 보이는 주사위, 2개 보이는 주사위, 3개 보이는 각 주사위의 면 최소합
    sum1 = min_arr[0]
    sum2 = min_arr[0] + min_arr[1]
    sum3 = sum(min_arr)
    # (K개 면이 보이는 주사위 총 개수) x (k개 면의 합 최소값)
    answer += (4*(N-1)*(N-2) + (N-2)**2) * sum1 
    answer += (4*(N-1) + 4*(N-2)) * sum2
    answer += 4 * sum3
    print(answer)