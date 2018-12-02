#include <algorithm>
#include <iostream>
#include <string>
#include <vector>
#include <utility>
#include <cassert>

using namespace std;

#define ALPHABET_SIZE	5

static void print_vec(const char *s, const vector<int> &vec)
{
	cout << s << ": ";
	for (size_t i = 0; i < vec.size(); i++) {
		cout << vec[i] << " ";
	}
	cout << endl;
}

static int letter_to_index(char c)
{
	switch (c) {
		case '$':
			return 0;
		case 'A':
			return 1;
		case 'C':
			return 2;
		case 'G':
			return 3;
		case 'T':
			return 4;
		default:
			assert(0);
			return 0;
	}
}

static vector<int> SortCharacters(const string &s)
{
	int n = s.size();
	vector<int> order(n);
	vector<int> cnt(ALPHABET_SIZE, 0);

	for (int i = 0; i < n; i++) {
		int c_i = letter_to_index(s[i]);

		cnt[c_i]++;
	}
	
	for (size_t j = 1; j < cnt.size(); j++) {
		cnt[j] += cnt[j-1];
	}

	for (int i = n - 1; i >= 0; i--) {
		char c = letter_to_index(s[i]);

		cnt[c]--;
		order[cnt[c]] = i;
	}

	return order;
}

static vector<int> ComputeCharClasses(const string &s, const vector<int> &order)
{
	int n = s.size();
	vector<int> my_class(n);

	my_class[order[0]] = 0;

	for (size_t i = 1; i < n; i++) {
		if (s[order[i]] != s[order[i-1]]) {
			my_class[order[i]] = my_class[order[i-1]] + 1;
		} else {
			my_class[order[i]] = my_class[order[i-1]];
		}
	}

	return my_class;
}

static vector<int> SortDoubled(const string &s, int L, const vector<int> &order, const vector<int> &my_class)
{
	int n = s.size();
	vector<int> cnt(n, 0);
	vector<int> new_order(n);

	for (int i = 0; i < n; i++) {
		cnt[my_class[i]]++;
	}

	for (int j = 1; j < n; j++) {
		cnt[j] += cnt[j-1];
	}

	for (int i = n - 1; i >= 0; i--) {
		int start = (order[i] - L + n) % n;
		int cl = my_class[start];

		cnt[cl]--;
		new_order[cnt[cl]] = start;
	}

	return new_order;
}

static vector<int> UpdateClasses(const vector<int> &new_order, const vector<int> &my_class, int L)
{
	int n = new_order.size();
	vector<int> new_class(n);

	new_class[new_order[0]] = 0;

	for (int i = 1; i < n; i++) {
		int cur = new_order[i];
		int prev = new_order[i-1];
		int mid = (cur + L) % n;
		int mid_prev = (prev + L) % n;

		if (my_class[cur] != my_class[prev] || my_class[mid] != my_class[mid_prev]) {
			new_class[cur] = new_class[prev] + 1;
		} else {
			new_class[cur] = new_class[prev];
		}
	}

	return new_class;
}

static vector<int> BuildSuffixArray(const string &s)
{
	vector<int> order = SortCharacters(s);
	vector<int> my_class = ComputeCharClasses(s, order);
	int n = s.size();
	int L = 1;

	while (L < n) {
		order = SortDoubled(s, L, order, my_class);
		my_class = UpdateClasses(order, my_class, L);
		L *= 2;
	}

	return order;
}

static vector<int> FindOccurrences(const string &pattern, const string &text, const vector<int> &suffix_array)
{
	vector<int> result;
	int n = (int)text.size();
	int pn = (int)pattern.size();
	int min_index = 0;
	int max_index = n;
	int start, end;

	while (min_index < max_index) {
		int mid_index = (min_index + max_index)/2;
		int pos = suffix_array[mid_index];

		if (pattern > text.substr(pos, pn)) {
			min_index = mid_index + 1;
		} else {
			max_index = mid_index;
		}
	}

	start = min_index;
	max_index = n;

	while (min_index < max_index) {
		int mid_index = (min_index + max_index)/2;
		int pos = suffix_array[mid_index];

		if (pattern < text.substr(pos, pn)) {
			max_index = mid_index;
		} else {
			min_index = mid_index + 1;
		}
	}

	end = max_index;

	for (int i = start; i < end; i++) {
		result.push_back(suffix_array[i]);
	}

	return result;
}

int main()
{
	char buffer[100001];
	scanf("%s", buffer);
	string text = buffer;
	text += '$';
	vector<int> suffix_array = BuildSuffixArray(text);
	int pattern_count;
	scanf("%d", &pattern_count);

	vector<bool> occurs(text.length(), false);

	for (int pattern_index = 0; pattern_index < pattern_count; ++pattern_index) {
		scanf("%s", buffer);
		string pattern = buffer;
		vector<int> occurrences = FindOccurrences(pattern, text, suffix_array);
		for (int j = 0; j < occurrences.size(); ++j) {
			occurs[occurrences[j]] = true;
		}
	}

	for (int i = 0; i < occurs.size(); ++i) {
		if (occurs[i]) {
			printf("%d ", i);
		}
	}
	printf("\n");

	return 0;
}