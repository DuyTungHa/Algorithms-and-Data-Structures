# python3
import sys

def BWT(text):
    texttext = text *2
    matrix = sorted([texttext[i:i + len(text)] for i in range(len(text))])
    return ''.join([matrix[i][-1] for i in range(len(text))])


if __name__ == '__main__':
    text = sys.stdin.readline().strip()
    print(BWT(text))
