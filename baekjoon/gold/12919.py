import sys
from collections import deque
input = sys.stdin.readline
'''
문제: 12919 A와 B 2
난이도: Gold 5
알고리즘: 브루트포스
풀이 방법: S 에서 T를 맞추는 것이 아닌 T에서 하나씩 제거하면서 S를 맞춘다. bfs 를 통해 풀었다. dfs로 재귀로 풀어도 된다.
        S에서 T를 맞추면 앞에서 뒤로 탐색하므로 탐색할 경우의 수가 많아지지만 T에서 S로 맞출 경우 최종 문자열을 알고 있으므로 경우의 수가 좁혀진다.
        마지막 문자가 A인 경우엔 1번 A를 추가한다에 해당할 수 있으므로 A를 제거한 문자열을 큐에 삽입한다.
        첫 문자가 B인 경우엔 2번 뒤에 B를 추가하고 문자열을 뒤집는다에 해당할 수 있으므로 첫 문자 B를 제외하고 뒤집은 문자열을 큐에 삽입한다.
        주의할 것은 B__A 인 경우 두 개에 모두 해당하므로 elif 가 아닌 if문으로 하여 둘 다 삽입되도록 해야 한다.
        12904 A와 B 문제와 차이는 12904 문제는 뒤집고 B 추가이므로 T를 뒤에서부터 탐색하면서 마지막 문자가 A, B 인지에 따라 그리디 탐색해주면 되는데,
        이번 문제는 B 추가 후 뒤집으므로 경우의 수가 다양해져 브루트포스로 풀어야 한다는 점이다.
'''
S = input().rstrip()
T = input().rstrip()

q = deque([T])
ans = 0
while q:
    cur = q.popleft()
    if cur == S:
        ans = 1
        break
    
    # 문자열이 더 작아질 경우
    if len(cur) <= len(S):
        continue
    
    # 마지막 문자가 A인 경우 뒤에 A를 제거하고 삽입
    if cur[-1] == 'A':
        q.append(cur[:-1]) 
    # 첫 문자가 B인 경우 B를 제거하고 뒤집기
    if cur[0] == 'B':
        q.append(cur[-1:0:-1])
print(ans)
        


