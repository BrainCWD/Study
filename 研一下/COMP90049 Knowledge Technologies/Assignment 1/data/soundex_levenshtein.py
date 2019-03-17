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

dictionary_copy = dictionary.copy()
mylist = list(dictionary_copy)

for index in range(0, len(mylist)):
	mylist[index] = jellyfish.soundex(mylist[index])

f = open('result.txt', 'w')

correctnumber = 0
total = 0

i = 0
while(i < len(misspell)):
	mis = misspell[i]
	resultlist = []
	abbreviation = jellyfish.soundex(mis)
	for index in range(0, len(mylist)):
		if abbreviation == mylist[index]:
			resultlist.append(index)

	distancelist = []
	for index in resultlist:
		distance = jellyfish.levenshtein_distance(mis, dictionary[index])
		distancelist.append(distance)


	minimum = min(distancelist, default = 100)
	result = [mis]
	for index in range(0, len(distancelist)):
		if distancelist[index] == minimum:
			result.append(dictionary[resultlist[index]])
			total += 1

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
