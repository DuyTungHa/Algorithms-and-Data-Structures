#include <cassert>
#include <iostream>
#include <map>
#include <string>
#include <vector>

using namespace std;

const int LETTERS_NUM = 5;

struct Node {
	Node() :
		pos(0),
		len(0)
	{
		for (int i = 0; i < LETTERS_NUM; i++) {
			next[i] = NULL;
		}
	}

	Node *next[LETTERS_NUM];
	int pos;
	int len;
};

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
		case '$':
			return 4;
		default:
			assert(0);
			return -1;
	}
}

static void add_str_to_result(Node *v, const string &text, vector<string> *result)
{
	if (!v) return;

	string s = text.substr(v->pos, v->len);

	if (!s.empty()) {
		result->push_back(s);
	}

	for (int i = 0; i < LETTERS_NUM; i++) {
		add_str_to_result(v->next[i], text, result);
	}
}

static void print_node(const char *s, Node *v, const string &text)
{
	cout << s << ": " << text.substr(v->pos, v->len) << endl;
}

static Node *create_suffix_tree(const string &text)
{
	Node *root = new Node();

	for (int i = 0; i < (int)text.size(); i++) {
		Node *v = root;
		Node *w = v->next[letter_to_index(text[i])];
		int cur_pos = w ? w->pos : -1;

		for (int j = i; j < (int)text.size(); j++) {
			if (!w) {
				Node *z = new Node();

				z->pos = j;
				z->len = (int)text.size() - j;
				v->next[letter_to_index(text[z->pos])] = z;
				break;
			}

			if (text[j] != text[cur_pos]) {
				Node *z = new Node();

				z->pos = w->pos;
				z->len = cur_pos - w->pos;
				v->next[letter_to_index(text[z->pos])] = z;
				w->pos = cur_pos;
				w->len -= z->len;
				z->next[letter_to_index(text[w->pos])] = w;
				v = z;
				w = NULL;
				j--;
				continue;
			}

			if (j + 1 < (int)text.size()) {
				cur_pos++;
				if (cur_pos >= w->pos + w->len) {
					v = w;
					w = w->next[letter_to_index(text[j + 1])];
					cur_pos = w ? w->pos : -1;
				}
			}
		}
	}

	return root;
}

static void free_suffix_tree(Node *v)
{
	if (!v) return;

	for (int i = 0; i < LETTERS_NUM; i++) {
		free_suffix_tree(v->next[i]);
	}

	delete v;
}

static vector<string> ComputeSuffixTreeEdges(const string &text)
{
	vector<string> result;
	Node *root;
	int pos = 0, len = 0;

	root = create_suffix_tree(text);
	add_str_to_result(root, text, &result);
	free_suffix_tree(root);

	return result;
}

int main()
{
	string text;
	cin >> text;
	vector<string> edges = ComputeSuffixTreeEdges(text);

	for (int i = 0; i < edges.size(); ++i) {
		cout << edges[i] << endl;
	}

	return 0;
}