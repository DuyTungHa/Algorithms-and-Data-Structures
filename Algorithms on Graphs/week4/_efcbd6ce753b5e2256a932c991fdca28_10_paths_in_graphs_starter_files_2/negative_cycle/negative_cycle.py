#Uses python3

import sys


def negative_cycle(adj, cost):
    dist = [10000000 for u in adj]
    prev = [-1 for u in adj]
    dist[0] = 0
    for i in range(len(adj)):
        for u in range(len(adj)):
            for v in adj[u]:
                if not relax(u, v, dist, adj, cost, prev, i):
                    return 1
    return 0

def relax(u, v, dist, adj, cost, prev, i):
    if dist[v] > dist[u] + cost[u][adj[u].index(v)]:
        if i == len(adj) - 1:
            return False
        dist[v] = dist[u] + cost[u][adj[u].index(v)]
        prev[v] = u
    return True


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
    print(negative_cycle(adj, cost))
