# python3
import sys

class SuffixTreeNode:
    def __init__(self, parent, children, stringDepth, edgeStart, edgeEnd):
        self.parent = parent
        self.children = children
        self.stringDepth = stringDepth
        self.edgeStart = edgeStart
        self.edgeEnd = edgeEnd

def create_new_leaf(node, S, suffix):
    leaf = SuffixTreeNode(children={},
                          parent=node,
                          stringDepth=len(S)-suffix,
                          edgeStart = suffix + node.stringDepth,
                          edgeEnd = len(S) -1)
    node.children[S[leaf.edgeStart]] = leaf
    return leaf

def break_edge(node, S, start, offset):
    startChar = S[start]
    midChar = S[start + offset]
    midNode = SuffixTreeNode(children = {},
                             parent = node,
                             stringDepth = node.stringDepth + offset,
                             edgeStart = start,
                             edgeEnd = start + offset -1)
    midNode.children[midChar] = node.children[startChar]
    node.children[startChar].parent = midNode
    node.children[startChar].edgeStart += offset
    node.children[startChar] = midNode
    return midNode

def ST_from_SA(S, order, lcpArray):
    root = SuffixTreeNode(children={},
                          parent=None,
                          stringDepth=0,
                          edgeStart=-1,
                          edgeEnd=-1)
    lcpPrev = 0
    curNode = root
    for i in range(len(S)):
        suffix = order[i]
        while curNode.stringDepth > lcpPrev:
            curNode = curNode.parent
        if curNode.stringDepth == lcpPrev:
            curNode = create_new_leaf(curNode, S, suffix)
        else:
            edgeStart = order[i-1] + curNode.stringDepth
            offset = lcpPrev - curNode.stringDepth
            midNode = break_edge(curNode, S, edgeStart, offset)
            curNode = create_new_leaf(midNode, S, suffix)
        if i < len(S)-1:
            lcpPrev = lcpArray[i]
    return root

if __name__ == '__main__':
    text = sys.stdin.readline().strip()
    sa = list(map(int, sys.stdin.readline().strip().split()))
    lcp = list(map(int, sys.stdin.readline().strip().split()))
    print(text)
    root = ST_from_SA(text, sa, lcp)
    stack = [(root, 0)]
    while len(stack) > 0:
        (node, edge_index) = stack[-1]
        stack.pop()
        if 0 == len(node.children):
            continue
        edges = sorted(node.children.keys())
        if edge_index + 1 < len(edges):
            stack.append((node, edge_index + 1))
        print("%d %d" % (node.children[edges[edge_index]].edgeStart, node.children[edges[edge_index]].edgeEnd + 1))
        stack.append((node.children[edges[edge_index]], 0))
