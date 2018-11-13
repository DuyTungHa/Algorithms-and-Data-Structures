#Uses python3

import sys


def shortet_paths(adj, cost, s, distance, reachable, shortest):
    A = set()
    distance[s] = 0
    reachable[s] = 1
    shortest[s] = 1
    
    for i in range(len(adj)):
        for u in range(len(adj)):
            for v in adj[u]:
                relax(u, v, distance, adj, cost, reachable, shortest, i, A)

    if A:
        bfs([u for u in A], A, adj)
        for u in A:
            shortest[u] = 0
        
def relax(u, v, distance, adj, cost, reachable, shortest, i, A):
    if distance[v] > distance[u] + cost[u][adj[u].index(v)]:
        distance[v] = distance[u] + cost[u][adj[u].index(v)]
        reachable[v] = 1
        shortest[v] = 1
        if i == len(adj)-1:
            A.add(v)
            
def bfs(q, A, adj):
    visited = [-1 for v in adj]
    while q:
        u = q.pop(0)
        for v in adj[u]:
            if visited[v] == -1:
                q.append(v)
                A.add(v)
                visited[v] = 1
            
if __name__ == '__main__':
    input = sys.stdin.read()
    data = list(map(int, input.split()))
    n, m = data[0:2]
    data = data[2:]
    edges = list(zip(zip(data[0:(3 * m):3], data[1:(3 * m):3]), data[2:(3 * m):3]))
    data = data[3 * m:]
    adj = [[] for _ in range(n)]
    cost = [[] for _ in range(n)]
    for ((a, b), w) in edges:
        adj[a - 1].append(b - 1)
        cost[a - 1].append(w)
    s = data[0]
    s -= 1
    
    distance = [float('inf')] * n
    reachable = [0] * n
    shortest = [1] * n
    shortet_paths(adj, cost, s, distance, reachable, shortest)
    for x in range(n):
        if reachable[x] == 0:
            print('*')
        elif shortest[x] == 0:
            print('-')
        else:
            print(distance[x])

