# python3
import sys


def find_pattern(pattern, text):
  result = []
  merge_text = pattern + '$' + text
  s = compute_prefix_function(merge_text)
  for i in range(len(pattern) + 1,len(merge_text)):
    if s[i] == len(pattern):
      result.append(i-2*len(pattern))
  return result

def compute_prefix_function(text):
  s = [0]
  border = 0
  for i in range(1,len(text)):
    while(border > 0) and (text[i] != text[border]):
      border = s[border-1]
    if text[i] == text[border]:
      border = border + 1
    else:
      border = 0
    s.append(border)
  return s

if __name__ == '__main__':
  pattern = sys.stdin.readline().strip()
  text = sys.stdin.readline().strip()
  result = find_pattern(pattern, text)
  print(" ".join(map(str, result)))

