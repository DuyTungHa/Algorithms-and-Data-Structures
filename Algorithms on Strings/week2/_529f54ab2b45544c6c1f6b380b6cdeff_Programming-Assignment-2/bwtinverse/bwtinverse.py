# python3
import sys

def rankBWT(bwt):
    tots = dict()
    ranks = []
    for c in bwt:
        if c not in tots:
            tots[c] = 0
        ranks.append(tots[c])
        tots[c] += 1
    return ranks, tots

def firstCol(tots):
    first = {}
    totc = 0
    for c, count in sorted(tots.items()):
        first[c] = totc
        totc += count
    return first
    
def InverseBWT(bwt):
    ranks, tots = rankBWT(bwt)
    first = firstCol(tots)
    rowi = 0
    t = '$'
    while bwt[rowi] != '$':
        c = bwt[rowi]
        t = t + c
        rowi = first[c] + ranks[rowi]
    return t[::-1]


if __name__ == '__main__':
    bwt = sys.stdin.readline().strip()
    print(InverseBWT(bwt))
