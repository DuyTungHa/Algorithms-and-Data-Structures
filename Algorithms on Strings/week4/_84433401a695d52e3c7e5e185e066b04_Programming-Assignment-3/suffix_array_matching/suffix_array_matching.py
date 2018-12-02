# python3
import sys

def find_occurrences(text, patterns):
    occs = set()
    # text += '$'
    text = text_to_num(text)
    patterns = [text_to_num(pattern) for pattern in patterns]
    
    suffix_array = build_suffix_array(text)
    for pattern in patterns:
        pattern_matching_with_suffix_array(text, pattern, suffix_array, occs)
    return occs

def pattern_matching_with_suffix_array(text, pattern, suffix_array, occs):
    minIndex = 0
    maxIndex = len(text) 
    while minIndex < maxIndex:
        midIndex = int((minIndex + maxIndex)/2)
        if pattern > text[suffix_array[midIndex]:suffix_array[midIndex] + len(pattern):]:
            minIndex = midIndex + 1
        else:
            maxIndex = midIndex
    start = minIndex
    maxIndex = len(text) 
    while minIndex < maxIndex:
        midIndex = int((minIndex + maxIndex)/2)
        if pattern < text[suffix_array[midIndex]:suffix_array[midIndex] + len(pattern):]:
            maxIndex = midIndex
        else:
            minIndex = midIndex + 1
    end = maxIndex
    if start <= end:
        for i in range(start, end):
            occs.add(suffix_array[i])

def compare_suffix(pattern, text):
    if len(text) < len(pattern):
        return True
    else:
        for i in range(len(pattern)):
            if text[i] > pattern[i]:
                return True
    return False
            
    

def build_suffix_array(text):
  order = sort_characters(text)
  cl = compute_char_classes(text, order)
  L = 1
  while L < len(text):
    order = sort_doubled(text, L, order, cl)
    cl = update_classes(order, cl, L)
    L = 2*L
  return order

def sort_characters(text):
  order = [-1 for c in text]
  count = [0,0,0,0,0]
  for i in range(len(text)):
    count[text[i]] += 1
  for j in range(1, len(count)):
    count[j] += count[j-1]
  for i in range(len(text)-1, -1, -1):
    c = text[i]
    count[c] -= 1
    order[count[c]] = i
  return order

def compute_char_classes(text, order):
  cl = [-1 for c in text]
  cl[order[0]] = 0
  for i in range(1, len(text)):
    if text[order[i]] != text[order[i-1]]:
      cl[order[i]] = cl[order[i-1]] + 1
    else:
      cl[order[i]] = cl[order[i-1]]
  return cl

def sort_doubled(text, L, order, cl):
  count = [0 for c in text]
  new_order = [-1 for c in text]
  for i in range(len(text)):
    count[cl[i]] +=1
  for j in range(1, len(text)):
    count[j] += count[j-1]
  for i in range(len(text)-1, -1, -1):
    start = (order[i] - L + len(text)) % len(text)
    cl_temp = cl[start]
    count[cl_temp] -= 1
    new_order[count[cl_temp]] = start
  return new_order

def update_classes(new_order, cl, L):
  n = len(new_order)
  new_class = [-1]*n
  new_class[new_order[0]] = 0
  for i in range(1, n):
    cur = new_order[i]
    prev = new_order[i-1]
    mid = (cur + L) % n
    midPrev = (prev + L) % n
    if cl[cur] != cl[prev] or cl[mid] != cl[midPrev]:
      new_class[cur] = new_class[prev] + 1
    else:
      new_class[cur] = new_class[prev]
  return new_class

def text_to_num(text):
  result = []
  for c in text:
    if c == '$':
      result.append(0)
    elif c == 'A':
      result.append(1)
    elif c == 'C':
      result.append(2)
    elif c == 'G':
      result.append(3)
    else:
      result.append(4)
  return result


if __name__ == '__main__':
    text = sys.stdin.readline().strip()
    pattern_count = int(sys.stdin.readline().strip())
    patterns = sys.stdin.readline().strip().split()
    occs = find_occurrences(text, patterns)
    print(" ".join(map(str, occs)))
