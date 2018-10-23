# Uses python3
import sys
import random

def fast_count_segments(starts, ends, points):
    cnt = [0] * len(points)
    LEFT = 1
    POINT = 2
    RIGHT = 3

    starts_l = [LEFT] * len(starts)
    ends_r = [RIGHT] * len(ends)
    points_p = [POINT] * len(points)

    pairs_number = starts + ends + points
    pairs_letter = starts_l + ends_r + points_p

    randomized_quick_sort(pairs_number, pairs_letter, 0, len(pairs_number) - 1)

    count_left = 0

    point_counts = {}
    for p in points:
        point_counts[p] = 0

    for i in range(len(pairs_number)):
        if pairs_letter[i] == LEFT:
            count_left += 1
        elif pairs_letter[i] == RIGHT:
            count_left -= 1
        elif pairs_letter[i] == POINT:
            if point_counts[pairs_number[i]] == 0:
                point_counts[pairs_number[i]] += count_left

    for i in range(len(points)):
        cnt[i] = point_counts[points[i]]

    return cnt

def partition3(a, b, l, r):
    x = a[l]
    letx = b[l]
    begin = l+1
    end = l

    for i in range(l + 1, r + 1):
        if less_than_or_equal(a[i], x, b[i], letx):
            end += 1
            a[i], a[end] = a[end], a[i]
            b[i], b[end] = b[end], b[i]
            if less_than(a[end], x, b[end], letx):
                a[begin], a[end] = a[end], a[begin]
                b[begin], b[end] = b[end], b[begin]
                begin += 1
                
    a[l], a[begin-1] = a[begin-1], a[l]
    b[l], b[begin-1] = b[begin-1], b[l]

    return [begin, end]

def less_than_or_equal(num1, num2, let1, let2):
    return less_than(num1, num2, let1, let2) or \
    equal(num1, num2, let1, let2)

def less_than(num1, num2, let1, let2):
    return num1 < num2 or \
            (num1 == num2 and let1 < let2)

def equal(num1, num2, let1, let2):
    return num1 == num2 and let1 == let2


def randomized_quick_sort(a, b, l, r):
    if l >= r:
        return
    k = random.randint(l, r)
    a[l], a[k] = a[k], a[l]
    b[l], b[k] = b[k], b[l]
    #use partition3
    [m1, m2] = partition3(a, b, l, r)
    randomized_quick_sort(a, b, l, m1 - 1);
    randomized_quick_sort(a, b, m2 + 1, r);

if __name__ == '__main__':
    input = sys.stdin.read()
    data = list(map(int, input.split()))
    n = data[0]
    m = data[1]
    starts = data[2:2 * n + 2:2]
    ends   = data[3:2 * n + 2:2]
    points = data[2 * n + 2:]
    # use fast_count_segments
    cnt = fast_count_segments(starts, ends, points)
    for x in cnt:
        print(x, end=' ')
