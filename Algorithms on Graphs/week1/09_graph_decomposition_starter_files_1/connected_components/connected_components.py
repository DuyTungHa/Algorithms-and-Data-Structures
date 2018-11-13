#Uses python3

import sys


def number_of_components(adj):
    return dfs(adj)

def dfs(adj: list) -> int:
    visited = [0 for v in adj]
    cc = 0
    for v in range(len(adj)):
        if visited[v] == 0:
            explore(v, visited, adj)
            cc += 1
    return cc

def explore(v: int, visited: list, adj: list):
    visited[v] = 1
    for w in adj[v]:
        if visited[w] != 1:
            explore(w, visited, adj)

if __name__ == '__main__':
    input = sys.stdin.read()
    data = list(map(int, input.split()))
    n, m = data[0:2]
    data = data[2:]
    edges = list(zip(data[0:(2 * m):2], data[1:(2 * m):2]))
    adj = [[] for _ in range(n)]
    for (a, b) in edges:
        adj[a - 1].append(b - 1)
        adj[b - 1].append(a - 1)
    print(number_of_components(adj))
