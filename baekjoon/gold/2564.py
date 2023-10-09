import sys

def get_dist(loc, d) :
    # 북쪽 좌측 모서리를 0으로 잡아 직사각형을 펼쳤을 때 가게와 동근이까지의 절대 거리 계산
    if loc==1 : #북쪽
        return d
    elif loc==4 : #동쪽
        return w+d
    elif loc==2 : #남쪽
        return w+h+w-d
    elif loc==3 : #서쪽
        return w+h+w+h-d

w, h = map(int,input().split())
num=int(input())
dist=[]
for i in range(num+1) :
    loc, d =map(int, sys.stdin.readline().split())
    dist.append(get_dist(loc, d))
dong_dist=dist[-1]

answer = 0
total_dist = 2 * (w+h)
for i in range(num):
    cal_dist = abs(dist[i]-dong_dist) # 동근이와 가게 사이의 거리 계산
    answer += min(cal_dist, total_dist-cal_dist)
print(answer)