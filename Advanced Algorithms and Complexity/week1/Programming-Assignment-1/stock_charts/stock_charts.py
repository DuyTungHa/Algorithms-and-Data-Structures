# python3
class StockCharts:
    def read_data(self):
        n, k = map(int, input().split())
        stock_data = [list(map(int, input().split())) for i in range(n)]
        return stock_data

    def write_response(self, result):
        print(result)

    def min_charts(self, stock_data):
        n = len(stock_data)
        k = len(stock_data[0])
        adj_matrix = [ [ 1 if self.smaller(stock_data[i], stock_data[j]) else 0
                         for j in range(n)]
                       for i in range(n)]

        matching = [-1] * n
        for i in range(n):
            seen = [False] * n
            self.bpm(adj_matrix, i, matching, seen, n)

        groups = [{i} for i in range(n)]
        for i in range(n):
            if matching[i] != -1:
                self.merge_set(groups, i, matching[i])
                    
        
        return len(groups) 

    def merge_set(self, groups, i, j):
        grp_a = set()
        grp_b = set()
        for elem in groups:
            if i in elem:
                grp_a = elem
            if j in elem:
                grp_b = elem
        groups.remove(grp_a)
        groups.remove(grp_b)
        groups.append(grp_a.union(grp_b))
                

    def bpm(self, adj_matrix, u, matching, seen, n):
        for v in range(n):
            if adj_matrix[u][v] and seen[v] == False:
                seen[v] = True
                if matching[v] == -1 or self.bpm(adj_matrix, matching[v], matching, seen, n):
                    matching[v] = u
                    return True
        return False
    
    def smaller(self, first_graph, second_graph):
        for i in range(len(first_graph)):
            if first_graph[i] >= second_graph[i]:
                return False
        return True

    def solve(self):
        stock_data = self.read_data()
        result = self.min_charts(stock_data)
        self.write_response(result)

if __name__ == '__main__':
    stock_charts = StockCharts()
    stock_charts.solve()
