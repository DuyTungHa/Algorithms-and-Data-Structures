# python3
from sys import stdin
EPS = 1e-6
PRECISION = 20

def solve_diet_problem(n, m, A, b, c):
  for i in range(m):
    A.append([0] * m)
    A[-1][i] = -1
    b.append(0)
  A.append([1] * m)
  b.append(1000000000)
  
  maximum = float("-inf")
  final_vertex = None
  for elem in sub_set([i for i in range(len(A))]):
    if len(elem) == m:
      A_temp = [A[elem[i]].copy() for i in range(m)]
      b_temp = [b[elem[i]] for i in range(m)]
      vertex, result = SolveEquation(A_temp, b_temp)
      if result:
        maximum, final_vertex = compare_vertex(A, b, vertex, c, maximum, final_vertex)
  if not final_vertex:
    return [-1, [0] * m]
  elif int(sum(final_vertex)) + 1 >= 1000000000:
    return [1, [0] * m]
  else:
    return [0, final_vertex]

# Compare the vertex to other inequalities
def compare_vertex(A, b, vertex, c, maximum, prevVertex):
  for i in range(len(A)):
    temp = 0
    for j in range(len(A[i])):
      temp += A[i][j] * vertex[j]
    if temp > b[i] + 0.001:
      return maximum, prevVertex
  delicious = 0
  for dish in range(len(vertex)):
    delicious += c[dish]* vertex[dish]
  if delicious >= maximum:
    return delicious, vertex
  else:
    return maximum, prevVertex

# Find the sub set
def sub_set(l):
  if l == []:
    return [[]]
  x = sub_set(l[1:])
  return x + [[l[0]] + y for y in x]

#Compute a vertex
class Equation:
  def __init__(self, a, b):
    self.a = a
    self.b = b

class Position:
  def __init__(self, column, row):
    self.column = column
    self.row = row

def SelectPivotElement(a, used_rows, used_columns):
  # This algorithm selects the first free element.
  # You'll need to improve it to pass the problem.
  pivot_element = Position(0, 0)
  while used_rows[pivot_element.row]:
    pivot_element.row += 1
  while used_columns[pivot_element.column]:
    pivot_element.column += 1
  while 0 == a[pivot_element.row][pivot_element.column] or used_rows[pivot_element.row]:
    pivot_element.row += 1
    if pivot_element.row == len(a):
      return pivot_element, False
  return pivot_element, True

def SwapLines(a, b, used_rows, pivot_element):
  a[pivot_element.column], a[pivot_element.row] = a[pivot_element.row], a[pivot_element.column]
  b[pivot_element.column], b[pivot_element.row] = b[pivot_element.row], b[pivot_element.column]
  used_rows[pivot_element.column], used_rows[pivot_element.row] = used_rows[pivot_element.row], used_rows[pivot_element.column]
  pivot_element.row = pivot_element.column;

def ProcessPivotElement(a, b, pivot_element):
  # Rescale to make pivot 1
  row = pivot_element.row
  col = pivot_element.column
  denominator = a[row][col] 
  for coe_index in range(len(a[row])):
    a[row][coe_index] = a[row][coe_index]/denominator
  b[row] = b[row]/denominator

  # Substract row from others to make other entries in column 0
  for i in range(len(a)):
    if i != row:
      multi = a[i][col]/a[row][col]
      for coe_index in range(len(a[i])):
        a[i][coe_index] -= a[row][coe_index]*multi
      b[i] -= b[row]*multi

def MarkPivotElementUsed(pivot_element, used_rows, used_columns):
  used_rows[pivot_element.row] = True
  used_columns[pivot_element.column] = True

def SolveEquation(A, b):
  size = len(A)
  used_columns = [False] * size
  used_rows = [False] * size
  for step in range(size):
    pivot_element, can_select = SelectPivotElement(A, used_rows, used_columns)
    if not can_select:
      return None, False
    SwapLines(A, b, used_rows, pivot_element)
    ProcessPivotElement(A, b, pivot_element)
    MarkPivotElementUsed(pivot_element, used_rows, used_columns)
  return b, True

n, m = list(map(int, stdin.readline().split()))
A = []
for i in range(n):
  A += [list(map(int, stdin.readline().split()))]
b = list(map(int, stdin.readline().split()))
c = list(map(int, stdin.readline().split()))

anst, ansx = solve_diet_problem(n, m, A, b, c)

if anst == -1:
  print("No solution")
if anst == 0:  
  print("Bounded solution")
  print(' '.join(list(map(lambda x : '%.18f' % x, ansx))))
if anst == 1:
  print("Infinity")
    
