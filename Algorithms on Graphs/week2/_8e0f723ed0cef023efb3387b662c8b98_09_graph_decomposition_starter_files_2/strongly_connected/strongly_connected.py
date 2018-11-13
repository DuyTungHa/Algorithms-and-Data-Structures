#Uses python3

import sys

sys.setrecursionlimit(200000)

pre = []
post = []
clock = 1

def number_of_strongly_connected_components(adj):
    result = 0
    r_adj = [ [] for v in adj]
    for v in range(len(adj)):
        for i in adj[v]:
            r_adj[i].append(v)
    dfs(r_adj)

    global post
    temp_dict = {v : post[v] for v in range(len(post))}

    visited = [False for v in adj]
    for v in sorted(temp_dict, key=temp_dict.get, reverse=True):
        if not visited[v]:
            explore(v, adj, visited)
            result += 1
    
    return result

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


if __name__ == '__main__':
    input = sys.stdin.read()
    data = list(map(int, input.split()))
    n, m = data[0:2]
    data = data[2:]
    edges = list(zip(data[0:(2 * m):2], data[1:(2 * m):2]))
    adj = [[] for _ in range(n)]
    for (a, b) in edges:
        adj[a - 1].append(b - 1)
    print(number_of_strongly_connected_components(adj))
