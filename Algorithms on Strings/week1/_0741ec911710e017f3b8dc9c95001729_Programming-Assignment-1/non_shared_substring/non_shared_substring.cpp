#include <algorithm>
#include <cassert>
#include <iostream>
#include <queue>
#include <string>
#include <unordered_set>
#include <vector>

using namespace std;

#define LETTERS_NUM	6

struct Node {
	Node () :
		letter(' '),
		is_text1(false),
		is_text2(false),
		prev(-1)
	{
		for (int i = 0; i < LETTERS_NUM; i++) {
			next[i] = -1;
		}
	}

	char letter;
	bool is_text1;
	bool is_text2;
	int prev;
	int next[LETTERS_NUM];
};

typedef vector<Node> Trie;

static int letter_to_index(char letter)
{
	switch (letter) {
		case 'A':
			return 0;
		case 'C':
			return 1;
		case 'T':
			return 2;
		case 'G':
			return 3;
		case '#':
			return 4;
		case '$':
			return 5;
		default:
			assert(0);
			return -1;
	}
}

static Trie create_suffix_trie(const string &text)
{
	Trie trie(1);
	bool is_text1 = true;
	bool is_text2 = false;

	for (size_t i = 0; i < text.size(); i++) {
		int v = 0;

		for (size_t j = i; j < text.size(); j++) {
			int index = letter_to_index(text[j]);
			int w = trie[v].next[index];

			if (w == -1) {
				w = trie.size();
				trie.push_back(Node());
				trie[v].next[letter_to_index(text[j])] = w;

				trie[w].letter = text[j];
				trie[w].prev = v;
			}
			if (is_text1) trie[w].is_text1 = true;
			if (is_text2) trie[w].is_text2 = true;
			v = w;
		}

		if (text[i] == '#') {
			is_text1 = false;
			is_text2 = true;
		}
	}

	return trie;
}

static string solve(const string &text1, const string &text2)
{
	string text = text1 + "#" +  text2 + "$";
	Trie trie = create_suffix_trie(text);
	queue<int> q;
	vector<bool> visited(trie.size(), false);
	int w = -1;

	q.push(0);
	visited[0] = true;

	while (!q.empty()) {
		int v = q.front();

		q.pop();

		if (trie[v].letter == '#') continue;
		if (trie[v].letter == '$') continue;

		if (trie[v].is_text1 && !trie[v].is_text2) {
			w = v;
			break;
		}

		for (int i = 0; i < LETTERS_NUM; i++) {
			int z = trie[v].next[i];

			if (z != -1 && !visited[z]) {
				q.push(z);
				visited[z] = true;
			}
		}
	}

	if (w == -1) return text1;

	string result;
	while (w != 0) {
		result += trie[w].letter;
		w = trie[w].prev;
	}
	reverse(result.begin(), result.end());

	return result;
}

int main(void)
{
	string text1, text2;
	cin >> text1;
	cin >> text2;

	cout << solve(text1, text2) << endl;

	return 0;
}