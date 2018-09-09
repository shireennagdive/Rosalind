class KMerComposition:
    k = 0
    symbol_to_number = {'A': 0, 'C': 1, 'G': 2, 'T': 3}
    number_to_symbol = {0: 'A', 1: 'C', 2: 'G', 3: 'T'}
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

    def number_to_pattern(self, index, len_pattern):
        if len_pattern == 1:
            return self.number_to_symbol[index]
        prefix_index = index//4
        symbol = self.number_to_symbol[index % 4]
        return self.number_to_pattern(prefix_index, len_pattern-1) + symbol


test_data = open("frequentWords.txt", "r")
data = test_data.read()
test_data.close()
list_of_string = data.split("\n")
text = "".join(list_of_string).replace(" ", "")
print(text)
kMer = KMerComposition(11, text)
frequency_array = kMer.computing_frequencies()
max_occurrences = max(frequency_array)
max_values = []
patterns = []
len_freq_array = len(frequency_array)
for i in range(0, len_freq_array):
    if frequency_array[i] == max_occurrences:
        max_values.append(i)
for i in max_values:
    patterns.append(kMer.number_to_pattern(i, kMer.k))
print(' '.join(pattern for pattern in patterns))
