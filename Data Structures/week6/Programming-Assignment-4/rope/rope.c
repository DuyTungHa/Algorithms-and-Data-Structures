#include <stdio.h>
#include <stdlib.h>
#include <strings.h>

#define MAX_LEN 300001

typedef struct _rope {
	int offset, len, size;
	char *s;
	struct _rope *left, *right, *parent;
} rope;

void printr(rope *r);
void report(rope *r);
void reportln(rope *r);

rope* new_rope(char *s, int len);
rope* cut(rope *r, int len);

void update(rope *r);
rope* concat(rope *left, rope *right);
void rotate(rope *r);
rope* find(rope **root, int i);
void splay(rope **root, rope *r);
void split(rope *r, int i, rope **left, rope **right);
rope* move(rope *r, int i, int j, int k);

int main() {
	char *s = (char*)calloc(MAX_LEN, sizeof(char));
	scanf("%s", s);

	rope *r = new_rope(s, strlen(s));

	int q, i, j, k;
	register int qi;
	scanf("%d", &q);
	for (qi = 0; qi < q; qi++) {
		scanf("%d %d %d", &i, &j, &k);
		r = move(r, i, j, k);
	}

	reportln(r);

	return 0;
}

void printr(rope *r) {
	register int i;
	for (i = 0; i < r->len; i++) printf("%c", *(r->s+i));
}

void report(rope *r) {
	if (r == NULL) return;
	report(r->left);
	printr(r);
	report(r->right);
}

void reportln(rope *r) {
	report(r);
	printf("\n");
}

rope* new_rope(char *s, int len) {
	rope *r = (rope*)malloc(sizeof(rope));
	r->s = s;
	r->offset = 0;
	r->len = r->size = len;
	r->left = r->right = r->parent = NULL;
	return r;
}

rope* cut(rope *r, int len) {
	r->len = len;
	return r;
}

void update(rope *r) {
	if (r == NULL) return;
	r->offset = 0;
	r->size = r->len;
	if (r->left != NULL) {
		r->left->parent = r;
		r->offset = r->left->size;
		r->size += r->left->size;
	}
	if (r->right != NULL) {
		r->size += r->right->size;
		r->right->parent = r;
	}
}

void rotate(rope *r) {
	rope *parent = r->parent;
	if (parent == NULL) return;
	rope *grandparent = parent->parent;
	if (parent->left == r) {
		rope *m = r->right;
		r->right = parent;
		parent->left = m;
	} else {
		rope *m = r->left;
		r->left = parent;
		parent->right = m;
	}
	update(parent);
	update(r);
	r->parent = grandparent;
	if (grandparent != NULL) {
		if (grandparent->left == parent) grandparent->left = r;
		else grandparent->right = r;
	}
}

void splay(rope **root, rope *r) {
	if (r == NULL) return;
	while (r->parent != NULL) {
		if (r->parent->parent == NULL) {
			// zig
			rotate(r);
		} else if (r->parent->left == r && r->parent->parent->left == r->parent) {
			// zig-zig (left)
			rotate(r->parent);
			rotate(r);
		} else if (r->parent->right == r && r->parent->parent->right == r->parent) {
			// zig-zig (right)
			rotate(r->parent);
			rotate(r);
		} else {
			// zig-zag
			rotate(r);
			rotate(r);
		}
	}
	*root = r;
}

rope* find(rope **root, int i) {
	rope *v = *root;
	while (v != NULL) {
		if (i >= v->offset && i <= v->offset+v->len) break;
		if (i > v->offset) {
			i -= v->offset + v->len;
			v = v->right;
		} else {
			v = v->left;
		}
	}
	splay(root, v);
	return v;
}

void split(rope *r, int i, rope **left, rope **right) {
	rope *found = find(&r, i);
	if (i == found->offset) {
		// a whole string should be split to the right
		rope *new_left = found->left;
		found->left = NULL;
		if (new_left != NULL) new_left->parent = NULL;
		*left = new_left;
		*right = found;
	} else if (i == found->offset + found->len) {
		// a whole string should be split to the left
		rope *new_right = found->right;
		found->right = NULL;
		if (new_right != NULL) new_right->parent = NULL;
		*right = new_right;
		*left = found;
	} else {
		// a string should be split in the middle
		rope *new_right = new_rope(found->s + (i - found->offset), found->len - (i - found->offset));
		new_right->right = found->right;
		found->right = NULL;
		*right = new_right;
		*left = cut(found, i - found->offset);
	}
	update(*left);
	update(*right);
}

rope* concat(rope *left, rope *right) {
	if (left == NULL) return right;
	if (right == NULL) return left;
	rope* min_right = right;
	while (min_right->left != NULL) min_right = min_right->left;
	splay(&right, min_right);
	right->left = left;
	update(right);
	return right;
}

rope* move(rope *r, int i, int j, int k) {
	if (i == k) return r;
	rope *left = NULL, *right = NULL, *out = NULL;
	split(r, i, &left, &out);
	int offset = j+1;
	if (left != NULL) offset -= left->size;
	split(out, offset, &out, &right);
	r = concat(left, right);
	left = right = NULL;
	split(r, k, &left, &right);
	return concat(concat(left, out), right);
}
