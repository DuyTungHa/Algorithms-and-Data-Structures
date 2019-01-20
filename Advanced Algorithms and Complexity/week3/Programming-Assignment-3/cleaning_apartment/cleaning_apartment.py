# python3
import itertools
n, m = map(int, input().split())
edges = [ list(map(int, input().split())) for i in range(m) ]

def printEquisatisfiableSatFormula():
    clauses = []
    belongs_to_path(clauses)
    appear_one(clauses)
    occupied_by_vertex(clauses)
    occupy_one(clauses)
    successive_vertices(clauses)
    print(len(clauses), n*n)
    for c in clauses:
        c.append(0)
        print(' '.join(map(str, c)))
    

def varnum(i,j):
    return n*(i-1) + j

def belongs_to_path(clauses):
    for i in range(1, n+1):
        clauses.append([varnum(i,j) for j in range(1, n+1)])

def appear_one(clauses):
    for i in range(1, n +1):
        for j, j_prime in itertools.combinations( [k for k in range(1, n +1)] , 2):
            clauses.append([-varnum(i, j_prime), -varnum(i, j)])

def occupied_by_vertex(clauses):
    for j in range(1, n +1):
        clauses.append([varnum(i, j) for i in range(1, n+1)])

def occupy_one(clauses):
    for j in range(1, n +1):
        for i, i_prime in itertools.combinations( [k for k in range(1, n +1)] , 2):
            clauses.append([-varnum(i_prime, j), -varnum(i, j)])

def successive_vertices(clauses):
    adj = [[] for _ in range(n+1)]
    for i, j in edges:
        adj[i].append(j)
        adj[j].append(i)
    for j in range(1, n):
        for i, nodes in enumerate(adj):
            if i != 0:
                clauses.append([-varnum(i, j)] + [varnum(n, j+1) for n in nodes])
            

printEquisatisfiableSatFormula()
