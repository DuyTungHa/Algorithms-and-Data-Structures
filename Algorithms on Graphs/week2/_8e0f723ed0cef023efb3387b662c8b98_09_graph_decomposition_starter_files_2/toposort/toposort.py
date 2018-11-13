#Uses python3

import sys

pre = []
post = []
clock = 1

def dfs(adj):
    global pre, post
    pre = [0 for v in adj]
    post = [0 for v in adj]
    visited = [False for v in adj]
    for v in range(len(adj)):
        if not visited[v]:
            explore(v, adj, visited)

def explore(v, adj, visited):
    visited[v] = True
    previsit(v)
    for w in adj[v]:
        if not visited[w]:
            explore(w, adj, visited)
    postvisit(v)

def previsit(v):
    global pre, clock
    pre[v] = clock
    clock += 1

def postvisit(v):
    global post, clock
    post[v] = clock
    clock += 1
    
def toposort(adj):
    dfs(adj)
    temp_dict = {post[v] : v for v in range(len(adj))}
    order = sorted(temp_dict, reverse = True)
    return [temp_dict[k] for k in order]

if __name__ == '__main__':
    input = sys.stdin.read()
    data = list(map(int, input.split()))
    n, m = data[0:2]
    data = data[2:]
    edges = list(zip(data[0:(2 * m):2], data[1:(2 * m):2]))
    adj = [[] for _ in range(n)]
    for (a, b) in edges:
        adj[a - 1].append(b - 1)
    order = toposort(adj)
    for x in order:
        print(x + 1, end=' ')

