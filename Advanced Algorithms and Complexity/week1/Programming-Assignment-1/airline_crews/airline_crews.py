# python3
class MaxMatching:
    
    def read_data(self):
        n, m = map(int, input().split())
        adj_matrix = [list(map(int, input().split())) for i in range(n)]
        return adj_matrix

    def write_response(self, matching):
        line = [str(-1 if x == -1 else x + 1) for x in matching]
        print(' '.join(line))

    def find_matching(self, adj_matrix):
        n = len(adj_matrix)
        m = len(adj_matrix[0])
        matching = [-1] * m
        matchR = [-1] * n
        for i in range(n):
            busy_right = [False] * m
            self.bpm(adj_matrix, i, matching, busy_right, m, matchR)     
        return matchR

    def solve(self):
        adj_matrix = self.read_data()
        matching = self.find_matching(adj_matrix)
        self.write_response(matching)

    def bpm(self, adj_matrix, u, matching, busy_right, m, matchR):
        for v in range(m):
            if adj_matrix[u][v] and busy_right[v] == False:
                busy_right[v] = True
                if matching[v] == -1 or self.bpm(adj_matrix, matching[v], matching, busy_right, m, matchR):
                    matching[v] = u
                    matchR[u] = v
                    return True
        return False

if __name__ == '__main__':
    max_matching = MaxMatching()
    max_matching.solve()
