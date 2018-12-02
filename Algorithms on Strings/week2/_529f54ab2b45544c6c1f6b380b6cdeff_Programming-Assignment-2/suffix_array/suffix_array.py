# python3
import sys


def build_suffix_array(text):
  suffixes = {}
  for i in range(len(text)):
    suffixes[text[i::]] = i
  result = [suffixes[k] for k in sorted(suffixes)]
  return result


if __name__ == '__main__':
  text = sys.stdin.readline().strip()
  print(" ".join(map(str, build_suffix_array(text))))
