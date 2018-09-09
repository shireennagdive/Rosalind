def reverse_complement(str):
    reverse_comp = ''
    i = len(str) - 1
    while i > -1:
        if str[i] == 'A':
            reverse_comp = reverse_comp + 'T'
        elif str[i] == 'T':
            reverse_comp = reverse_comp + 'A'
        elif str[i] == 'C':
            reverse_comp = reverse_comp + 'G'
        elif str[i] == 'G':
            reverse_comp = reverse_comp + 'C'
        i -= 1
    return reverse_comp


test_data = open("shared_kmer.txt", "r")
data = test_data.read()
list1 = data.split("\n")
test_data.close()

k = int(list1[0])
str1 = list1[1]
str2 = list1[2]
solution = ''
len_str1, len_str2 = len(str1) - k + 1, len(str2) - k + 1

for i in range(0, len_str1):
    substring1 = str1[i:i + k]
    val1 = str2.count(substring1)

    reverse_comp_str1 = reverse_complement(substring1)
    val2 = str2.count(reverse_comp_str1)

    if val1 > 1:
        for j in range(0, len_str2):
            substring2 = str2[j:j + k]
            if substring1 == substring2:
                solution = solution + ("(" + str(i) + ", " + str(j) + ")\n")
    if val2 > 1:
        for j in range(0, len_str2):
            substring2 = str2[j:j + k]
            if reverse_comp_str1 == substring2:
                solution = solution + ("(" + str(i) + ", " + str(j) + ")\n")

    if val1 == 1:
        solution = solution + ("(" + str(i) + ", " + str(str2.find(substring1)) + ")\n")
    if val2 == 1:
        solution = solution + ("(" + str(i) + ", " + str(str2.find(reverse_comp_str1)) + ")\n")

file = open("final_solution.txt", "w")
file.write(solution)
file.close()
