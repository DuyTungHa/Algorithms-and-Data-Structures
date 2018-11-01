# python3

import sys

class Rope:
        
	def __init__(self, s):
		self.s = list(s)
		
	def result(self):
		return ''.join(self.s)
	
	def process(self, i, j, k):
                sub = self[i:j]
                

rope = Rope(sys.stdin.readline().strip())
q = int(sys.stdin.readline())
for _ in range(q):
	i, j, k = map(int, sys.stdin.readline().strip().split())
	rope.process(i, j, k)
print(rope.result())
