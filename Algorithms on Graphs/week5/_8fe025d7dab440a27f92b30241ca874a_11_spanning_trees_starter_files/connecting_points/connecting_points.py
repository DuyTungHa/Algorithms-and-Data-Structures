#Uses python3
import sys
import math

class Node:
    def __init__(self, index, distance):
        self.index = index
        self.distance = distance

def take_distance(elem):
    return elem.distance

def minimum_distance(x, y):
    cost = [float('inf')] * len(x)
    cost[0] = 0
    q = [Node(v, cost[v]) for v in range(len(x))]

    while q:
        q.sort(key=take_distance)
        v = q.pop(0)
        for z in range(len(x)):
            if z != v.index and z in [elem.index for elem in q]:
                temp_length =  math.sqrt((x[v.index] - x[z])**2
                               + (y[v.index] - y[z])**2)
                if cost[z] > temp_length:
                    cost[z] = temp_length
                    q = changePriority(q, z, cost[z])
    return sum(cost)

def changePriority(q, z, distance):
    for elem in q:
        if elem.index == z:
            elem.distance = distance
            return q
    return q

if __name__ == '__main__':
    input = sys.stdin.read()
    data = list(map(int, input.split()))
    n = data[0]
    x = data[1::2]
    y = data[2::2]
    print("{0:.9f}".format(minimum_distance(x, y)))
