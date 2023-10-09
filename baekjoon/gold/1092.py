import sys

N = int(input())
cranes = list(map(int, sys.stdin.readline().split()))
M = int(input())
boxes = list(map(int, sys.stdin.readline().split()))
# 내림차순 정렬
cranes.sort(reverse=True) 
boxes.sort(reverse=True)

if cranes[0] < boxes[0]: # 가장 큰 박스 무게가 가장 큰 무게 제한보다 크면 모든 박스를 옮길 수 없음
    print(-1)
else:
    t = 0 # 소요된 시간
    while boxes: # 박스를 다 옮길 때까지
        for crane in cranes:
            for box in boxes:
                if crane >= box: # 옮길 수 있는 가장 큰 박스
                    boxes.remove(box)
                    break
        t += 1
    print(t)