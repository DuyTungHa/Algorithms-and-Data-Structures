#Uses python3

import sys
import queue

class Node:
    def __init__(self, index, distance):
        self.index = index
        self.distance = distance

    def __eq__(self, other):
        return self.distance == other.distance

    def __ne__(self, other):
        return self.distance != other.distance

    def __lt__(self, other):
        return self.distance < other.distance

    def __gt__(self, other):
        return self.distance > other.distance

    def __ge__(self, other):
        return self.distance >= other.distance

    def __le__(self, other):
        return self.distance <= other.distance


def distance(adj, cost, s, t):
    dist = [1000000 for u in adj]
    dist[s] = 0
    h = queue.PriorityQueue()
    h.put(Node(s, dist[s]))

    while not h.empty():
        u = h.get()
        for v in adj[u.index]:
            if dist[v] > dist[u.index] + cost[u.index][adj[u.index].index(v)]:
                dist[v] = dist[u.index] + cost[u.index][adj[u.index].index(v)]
                h.put(Node(v, dist[v]))
    
    return dist[t] if dist[t] != 1000000 else -1

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
    s, t = data[0] - 1, data[1] - 1
    print(distance(adj, cost, s, t))
