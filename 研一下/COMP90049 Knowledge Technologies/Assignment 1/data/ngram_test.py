def ngram(string1, string2):
	list1 = [string1[0]]
	list2 = [string2[0]]
	length1 = len(string1)
	length2 = len(string2)
	same = 0
	if length1 != 1:
		i = 1
		while i < length1:
			list1.append(string1[i - 1] + string1[i])
			i += 1
		list1.append(string1[length1 - 1])
	if length2 != 1:
		i = 1
		while i < length2:
			list2.append(string2[i - 1] + string2[i])
			i += 1
		list2.append(string2[length2 - 1])
	traversal = []
	for pair1 in list1:
		if not pair1 in traversal:
			for pair2 in list2:
				if pair2 == pair1:
					same += 1
					break
			traversal.append(pair1)
	distance = len(list1) + len(list2) - 2 * same
	return distance


def readFile(file):
	with open(file, 'r') as f:
		file = f.readlines()
		f.close()
		return file

def normalization(listfile):
	for i in range(len(listfile)):
		listfile[i] = listfile[i].strip()
	return listfile

misspell = readFile('wiki_misspell.txt')
correct = readFile('wiki_correct.txt')
dictionary = readFile('dict.txt')

misspell = normalization(misspell)
correct = normalization(correct)
dictionary = normalization(dictionary)

f = open('result.txt', 'w')

correctnumber = 0
total = 0

i = 0
while(i < len(misspell)):
	mis = misspell[i]
	resultlist = []
	for word in dictionary:
		distance = ngram(mis, word)
		resultlist.append(distance)

	minimum = min(resultlist)
	j = 0
	result = [mis]
	while(j < len(resultlist)):
		if resultlist[j] == minimum:
			result.append(dictionary[j])
			total += 1
		j += 1
	if correct[i] in result:
		result.append(True)
		correctnumber += 1
	else:
		result.append(False)
	
	result.append(correct[i])
	print(result)
	f.write(str(result) + '\n')
	i += 1

f.write(str(correctnumber) + ' ')
f.write(str(total))
f.close()
