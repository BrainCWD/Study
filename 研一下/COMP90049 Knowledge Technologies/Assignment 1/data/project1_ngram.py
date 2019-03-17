from ngram import NGram
import time

start = time.clock()

def read(instagram_file):
    with open(instagram_file, 'r', encoding='utf-8') as f:

        file_list = f.read().splitlines()
        return file_list

misspell = read('data/misspell.txt')
dictionary = read('data/dictionary.txt')
correct = read('data/correct.txt')
predict_count = 0

crt_spell_count = 0
crt_index = 0

result_file=open('data/result.txt','w')  

for mis_item in misspell:
    gram_list = []
    predict_list = []
    for i in range(0, len(dictionary)):
        gram_list.append(NGram.compare(mis_item, dictionary[i],N=3))
    max_NGram = max(gram_list)

    for j in range(0, len(gram_list)):
        if gram_list[j] == max_NGram:
            predict_list.append(dictionary[j])
            result_file.write(str(predict_list));  
            print(predict_list)

    predict_count += len(predict_list)

    if correct[crt_index] in predict_list:
        crt_spell_count += 1

    crt_index += 1
result_file.close() 
precision = crt_spell_count / predict_count
recall = crt_spell_count / len(correct)

print("correct word count:"+str(crt_spell_count))
print("The number of predicted words:"+str(predict_count))
print("Precision:"+str(precision))
print("Recall:"+str(recall))
print(time.clock()-start)