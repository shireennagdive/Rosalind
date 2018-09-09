from collections import defaultdict


class PatternMatching:

    def pre_process_pattern(self, pattern, longest_pp):
            pat, i = 0, 1
            pattern_len = len(pattern)
            while i in range(1, pattern_len):
                if pattern[pat] == pattern[i]:
                    i += 1
                    pat += 1
                    longest_pp.append(pat)
                else:
                    if pat != 0:
                        pat = longest_pp.__getitem__(pat-1)
                    else:
                        longest_pp.append(pat)
                        i += 1
            return longest_pp

    def search_pattern_in_text(self, text, pattern, longest_pp):
        i, j = 0, 0
        matching_indices = []
        len_text, len_pattern = len(text), len(pattern)
        while i in range(0, len_text):
            if j < len_pattern and text[i] == pattern[j]:
                i += 1
                j += 1
            else:
                if j != 0:
                    if j == len_pattern:
                        matching_indices.append(i - len_pattern)
                    j = longest_pp[j - 1]
                else:
                    i += 1
        if j == len_pattern:
            matching_indices.append(i - len_pattern)
        return matching_indices

str="CTTCGAAAGTTTGGGCCGAGTCTTACAGTCGGTCTTGAAGCAAAGTAACGAACTCCACGGCCCTGACTACCGAACCAGTTGTGAGTACTCAACTGGGTGAGAGTGCAGTCCCTATTGAGTTTCCGAGACTCACCGGGATTTTCGATCCAGCCTCAGTCCAGTCTTGTGGCCAACTCACCAAATGACGTTGGAATATCCCTGTCTAGCTCACGCAGTACTTAGTAAGAGGTCGCTGCAGCGGGGCAAGGAGATCGGAAAATGTGCTCTATATGCGACTAAAGCTCCTAACTTACACGTAGACTTGCCCGTGTTAAAAACTCGGCTCACATGCTGTCTGCGGCTGGCTGTATACAGTATCTACCTAATACCCTTCAGTTCGCCGCACAAAAGCTGGGAGTTACCGCGGAAATCACAG"

i, k = 0, 13
text = "GCTGACTCCATCACGCTCGTACGACGTACGAGCTGACTCCCGTACGAATCACGCTGATGTAAATATCACGCTGATGTAAATGCTGACTCCGCTGACTCCGATGTAAATGATGTAAATGCTGACTCCGCTGACTCCTATCGTCGCGTACGATATCGTCGGATGTAAATTATCGTCGTATCGTCGATCACGCTGATGTAAATTATCGTCGGCTGACTCCCGTACGACGTACGAGCTGACTCCTATCGTCGATCACGCTGATGTAAATGCTGACTCCGATGTAAATATCACGCTTATCGTCGGATGTAAATATCACGCTGCTGACTCCCGTACGATATCGTCGATCACGCTATCACGCTTATCGTCGGCTGACTCCTATCGTCGGCTGACTCCCGTACGATATCGTCGCGTACGAGATGTAAATGCTGACTCCGATGTAAATGATGTAAATGCTGACTCCTATCGTCGGCTGACTCCTATCGTCGGATGTAAATATCACGCTTATCGTCGTATCGTCGGCTGACTCCTATCGTCGATCACGCTGATGTAAATGCTGACTCCTATCGTCGGATGTAAATATCACGCTGCTGACTCCCGTACGATATCGTCGGCTGACTCCCGTACGAATCACGCTGCTGACTCCCGTACGAATCACGCTATCACGCTGCTGACTCCCGTACGAATCACGCTTATCGTCGGCTGACTCCGATGTAAATATCACGCTGATGTAAATGATGTAAATGATGTAAATATCACGCTTATCGTCGCGTACGAGCTGACTCCTATCGTCGCGTACGAGCTGACTCCGCTGACTCCGCTGACTCCGCTGACTCCATCACGCTGCTGACTCCATCACGCTGCTGACTCCATCACGCTATCACGCT"
length = len(text)

list_of_list = defaultdict(set)
list1 = []
obj = PatternMatching()

while i in range(0, length-k+1):
    pattern = text[i:i + k]
    if not list1.__contains__(pattern):
        longest_pp = [0]
        longest_pp = obj.pre_process_pattern(pattern, longest_pp)
        starting_indices = obj.search_pattern_in_text(text, pattern, longest_pp)
        list_of_list[len(starting_indices)].add(pattern)
        list1.append(pattern)
    i += 1

print(" ".join(list_of_list.get(max(list_of_list.keys()))))
