There are 5 Python programs which are "levenshtein.py", "ngram_test.py", "soundex.py", "soundex_levenshtein.py", "levenshtein_soundex.py" and they computed the distance of word pairs by using Levenshtein algorithm, N-gram algorithm, Soundex algorithm, respectively.
In "levenshtein.py", "soundex.py", "soundex_levenshtein.py", "levenshtein_soundex.py", I used "jellyfish" which is a python library for doing approximate and phonetic matching of strings. It can be install by command "pip install jellyfish".
jellyfish: https://pypi.org/project/jellyfish/

1. "levenshtein.py": For each misspelled word in "wiki_misspell.txt", computes the Levenshtein distance with each word in "dict.txt" and filters the words (or a single word) with the minimum distance, then compares with the correct word in "wiki_correct.txt" using these words (this word). The program will output a "result" file which contains the misspelled word, predictions (prediction), Boolean value (successful prediction or not), the correct word. For example:
['lattitude', 'attitude', 'latitude', True, 'latitude']
The number of correct predictions and total predictions are stated at the end of the "result" file.

2. "ngram_test.py": For each misspelled word in "wiki_misspell.txt", computes the 2-gram distance with each word in "dict.txt" and filters the words (or a single word) with the minimum distance, then compares with the correct word in "wiki_correct.txt" using these words (this word). The program will output a "result" file which contains the misspelled word, predictions (prediction), Boolean value (successful prediction or not), the correct word. For example:
['wroking', 'broking', 'rooking', 'troking', 'wring', 'wrong', False, 'working']
The number of correct predictions and total predictions are stated at the end of the "result" file.

3. "soundex.py": For each misspelled word in "wiki_misspell.txt", computes the soundex abbreviation and compares with each soundex abbreviation of word in "dict.txt" and filters the words (or a single word) have same abbreviation, then compares with the correct word in "wiki_correct.txt" using these words (this word). The program will output a "result" file which contains the misspelled word, predictions (prediction), Boolean value (successful prediction or not), the correct word. For example:
['yeilding', 'yieldance', 'yielden', 'yielding', 'yieldingly', 'yieldingness', 'yildun', 'yolden', True, 'yielding']
The number of correct predictions and total predictions are stated at the end of the "result" file.

4. "soundex_levenshtein.py": For each misspelled word in "wiki_misspell.txt", computes the soundex abbreviation and compares with each soundex abbreviation of word in "dict.txt" and filters the words (or a single word) have same abbreviation, then computes the Levenshtein distance between the misspelled word and predictions and filters the words with minimum Levenshtein distance. Finally, compares with the correct word in "wiki_correct.txt" using these words (this word). The program will output a "result" file which contains the misspelled word, predictions (prediction), Boolean value (successful prediction or not), the correct word. For example:
['exagerate', 'exaggerate', True, 'exaggerate']
The number of correct predictions and total predictions are stated at the end of the "result" file.

5. "levenshtein_soundex.py": For each misspelled word in "wiki_misspell.txt", computes the Levenshtein distance with each word in "dict.txt" and filters the words (or a single word) with the minimum distance, then computes the soundex abbreviation of the misspelled word, compares with each soundex abbreviation of predictions (prediction) and filters the words (or a single word) have same abbreviation. Finally, compares with the correct word in "wiki_correct.txt" using these words (this word). The program will output a "result" file which contains the misspelled word, predictions (prediction), Boolean value (successful prediction or not), the correct word. For example:
['emmision', 'emission', True, 'emission']
The number of correct predictions and total predictions are stated at the end of the "result" file.
