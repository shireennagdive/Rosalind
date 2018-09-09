class KMerComposition:
    k = 0
    symbol_to_number = {'A': 0, 'C': 1, 'G': 2, 'T': 3}
    text = ''

    def __init__(self, k, text):
        self.k = k
        self.text = text

    def computing_frequencies(self):
        size = 4**self.k
        len_text = len(self.text) - self.k+1
        frequency_array = []
        for i in range(0, size):
            frequency_array.insert(i, 0)
        for i in range(0, len_text):
            pattern = self.text[i: i + self.k]
            j = self.pattern_to_number(pattern)
            frequency_array[j] += 1
        return frequency_array

    def pattern_to_number(self, pattern):
        if pattern is '':
            return 0
        last_symbol = pattern[-1]
        prefix = pattern[:len(pattern)-1]
        return 4*self.pattern_to_number(prefix) + self.symbol_to_number[last_symbol]


test_data = open("rosalind_kmer.txt", "r")
data = test_data.read()
list_of_string = data.split("\n")
del list_of_string[0]
text = "".join(list_of_string)
kMer = KMerComposition(4, text)
solution = " ".join(str(x) for x in kMer.computing_frequencies())
print(solution)


