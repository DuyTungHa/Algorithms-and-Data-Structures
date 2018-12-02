# python3
import sys


def PreprocessBWT(bwt):
  compact = {'A':0, 'G':0, 'C':0, 'T':0, '$':0}
  starts = dict()
  occ_counts_before = dict()
  for c in 'ACTG$':
    occ_counts_before[c] = {0:0}
  for i in range(len(bwt)):
    compact[bwt[i]] += 1
    for k in occ_counts_before:
      occ_counts_before[k][i+1] = compact[k]
  count = 0
  for c,v in sorted(compact.items()):
    if v != 0:
      starts[c] = count
      count += v

  return starts, occ_counts_before


def CountOccurrences(pattern, bwt, starts, occ_counts_before):
  top = 0
  bottom = len(bwt) -1
  while top <= bottom:
    if pattern:
      symbol = pattern[-1]
      pattern = pattern[:len(pattern)-1:]
      if symbol in bwt[top:bottom+1:1]:
        top = starts[symbol] + occ_counts_before[symbol][top]
        bottom = starts[symbol] + occ_counts_before[symbol][bottom + 1] -1
      else:
        return 0
    else:
      return bottom - top +1
      
  return 0
     

if __name__ == '__main__':
  bwt = sys.stdin.readline().strip()
  pattern_count = int(sys.stdin.readline().strip())
  patterns = sys.stdin.readline().strip().split() 
  starts, occ_counts_before = PreprocessBWT(bwt)
  occurrence_counts = []
  for pattern in patterns:
    occurrence_counts.append(CountOccurrences(pattern, bwt, starts, occ_counts_before))
  print(' '.join(map(str, occurrence_counts)))
