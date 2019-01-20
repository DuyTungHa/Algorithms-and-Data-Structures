#include <iostream>
#include <math.h>
#include <utility>
#include <stdio.h>
#include <time.h>
#include <map>
#include <cassert>
using namespace std;


int main() {
    int n, t;
    scanf("%d %d", &n, &t);

    map<int, int> M;
    int id, value;

    for (int i = 0; i < n; ++i) {
        scanf("%d %d", &id, &value);
        assert(M.find(id) == M.end());
        M.insert(pair<int, int>(id, value));
    }

    for (int i = 0; i < n; ++i) {
        scanf("%d %d", &id, &value);
        map<int,int>::iterator it = M.find(id);
        assert(it != M.end());
        it->second -= value;
    }

    int num_queries = 0;
    scanf("%d", &num_queries);
    for (int q = 0; q < num_queries; ++q) {
        int query = 0;
        scanf("%d", &query);

        map<int,int>::iterator it = M.find(query);
        assert(it != M.end());
        if (it->second >= t)
            cout << "1 ";
        else
            cout << "0 ";
    }

    return 0;
}



