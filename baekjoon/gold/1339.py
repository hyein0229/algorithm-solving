import sys

N = int(input())
words = []
wordnum = dict()
for _ in range(N):
    cur = sys.stdin.readline().rstrip() # current word
    for i in range(len(cur)):
        if cur[i] not in wordnum:
            wordnum[cur[i]] = 10 ** (len(cur) - i - 1)
        else:
            wordnum[cur[i]] += 10 ** (len(cur) - i - 1)
    words.append(cur)
    
d = dict(sorted(wordnum.items(), key=lambda x : x[1], reverse = True))
matched = dict()
num = 9
for alpha in d:
    matched[alpha] = num
    num -= 1

answer = 0
for word in words:
    to_number = ""
    for w in word:
        to_number += str(matched[w])

    answer += int(to_number)
print(answer)