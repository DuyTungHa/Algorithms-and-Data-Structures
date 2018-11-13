#Uses python3

import sys

def dfs(adj: list) -> int:
    visited = [0 for v in adj]
    record = [0 for v in adj]
    for v in range(len(adj)):
        if visited[v] == 0:
            if not explore(v, adj, visited, record):
                return 1
    return 0

def explore(v: int, adj: list, visited: list, record: list):
    visited[v] = 1
    record[v] = 1
    for w in adj[v]:
        if visited[w] != 1 and not explore(w, adj, visited, record):
            return False
        elif record[w] == 1:
            return False
    record[v] = 0
    return True

if __name__ == '__main__':
    input = sys.stdin.read()
    data = list(map(int, input.split()))
    n, m = data[0:2]
    data = data[2:]
    edges = list(zip(data[0:(2 * m):2], data[1:(2 * m):2]))
    adj = [[] for _ in range(n)]
    for (a, b) in edges:
        adj[a - 1].append(b - 1)
    print(dfs(adj))
