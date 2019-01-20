# python3
import itertools
n, m = map(int, input().split())
edges = [ list(map(int, input().split())) for i in range(m) ]

# This solution prints a simple satisfiable formula
# and passes about half of the tests.
# Change this function to solve the problem.
def printEquisatisfiableSatFormula():
    clauses = []
    equal_some_color(clauses)
    exactly_one_color(clauses)
    not_same_color(clauses)
    var_list = count_var(clauses)
    print(str(len(clauses)) + ' ' + str(len(var_list)))
    for cl in clauses:
        text=''
        for num in cl:
            if num<0:
                text += '-' + str(var_list.index(abs(num)) + 1) + ' '
            else:
                text += str(var_list.index(num) + 1) + ' '
        print(text + '0')
        

def varnum(i, j):
    return 3*(i-1) + j

def equal_some_color(clauses):
    for i in range(1, n+1):
        clauses.append([varnum(i,j) for j in range(1, 4)])

def exactly_one_color(clauses):
    for i in range(1, n +1):
        for j, j_prime in itertools.combinations([1,2,3], 2):
            clauses.append([-varnum(i,j_prime), -varnum(i, j)])

def not_same_color(clauses):
    for j in range(1, 4):
        for edge in edges:
            clauses.append([-varnum(i, j) for i in edge])

def count_var(clauses):
    var_list = []
    for cl in clauses:
        for v in cl:
            if abs(v) not in var_list:
                var_list.append(abs(v))
    return var_list


printEquisatisfiableSatFormula()
