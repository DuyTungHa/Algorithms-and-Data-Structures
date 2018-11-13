#Uses python3

import sys
def bipartite(adj):
    #write your code here
    color_arr = [-1] * len(adj)
    color_arr[0] = 1
    queue = []
    queue.append(0)
    while queue:
        u = queue.pop(0)
        for v in adj[u]:
            if color_arr[v] == -1:
                color_arr[v] = 1 - color_arr[u]
                queue.append(v)
            elif color_arr[v] == color_arr[u]:
                return 0
    return 1

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
    print(bipartite(adj))
