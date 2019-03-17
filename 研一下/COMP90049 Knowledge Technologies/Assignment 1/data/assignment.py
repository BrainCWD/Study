import jellyfish

# Let's load the entire dictionary into a list
f = open("dict.txt",'r')
my_dict = f.readlines().strip()
f.close()

file = open("wiki_misspell.txt",'r')
for line in file:
	string = line.strip()
	bestv = 10000000 
	bests = ""
	for entry in my_dict:
    	thisv = jellyfish.levenshtein_distance(string,entry)
    	if (thisv < bestv):
        	
        	bests = entry.strip()
        	bestv = thisv
        	
	print(string,bests,bestv)

