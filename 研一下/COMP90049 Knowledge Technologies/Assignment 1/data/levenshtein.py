import jellyfish

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
		distance = jellyfish.levenshtein_distance(mis, word)
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
