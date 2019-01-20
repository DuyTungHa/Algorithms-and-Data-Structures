# python3
from sys import stdin
n, m = list(map(int, stdin.readline().split()))
A = []
for i in range(n):
  A += [list(map(int, stdin.readline().split()))]
b = list(map(int, stdin.readline().split()))

one_var = [[0], [1]]
two_var = [[0,0], [0,1], [1,0], [1,1]]
three_var = [[0,0,0], [0,0,1], [0,1,0], [1,0,0], [0,1,1], [1,1,0], [1,0,1], [1,1,1]]

def printEquisatisfiableSatFormula():
  clauses = []
  for i in range(n):
    check_line(A[i], b[i], clauses)

  var_list = count_var(clauses)
  if len(clauses):
    print(str(len(clauses)) + ' ' + str(len(var_list)))
    for cl in clauses:
      text=''
      for num in cl:
        if num<0:
          text += '-' + str(var_list.index(abs(num)) + 1) + ' '
        else:
          text += str(var_list.index(num) + 1) + ' '
      print(text + '0')
  else:
    print('1 1')
    print('1 -1 0')
  

def check_line(line, value, clauses):
  coe_index= []
  for i in range(m):
    if line[i] != 0:
      coe_index.append(i)
  if len(coe_index) == 1:
    compare_result(line, coe_index, one_var, value, clauses)
  elif len(coe_index) == 2:
    compare_result(line, coe_index, two_var, value, clauses)
  elif len(coe_index) == 3:
    compare_result(line, coe_index, three_var, value, clauses)

def compare_result(line, coe_index, test_case, value, clauses):
  for test in test_case:
      result=0
      for i in range(len(coe_index)):
        result += line[coe_index[i]]*test[i]
      if result > value:
        add_boolean(coe_index, test, clauses)

def add_boolean(coe_index, test, clauses):
  cl = []
  for i in range(len(test)):
    if test[i] == 0:
      cl.append(coe_index[i] + 1)
    else:
      cl.append(-coe_index[i] - 1)
  clauses.append(cl)

def count_var(clauses):
  var_list = []
  for cl in clauses:
    for v in cl:
      if abs(v) not in var_list:
        var_list.append(abs(v))
  return var_list

printEquisatisfiableSatFormula()
