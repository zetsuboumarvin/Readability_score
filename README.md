# Readability score
JetBrains Academy's project

Program implements various algorithms of determing texts difficulty and for which age they are most suitable:  
Automated readability index - https://en.wikipedia.org/wiki/Automated_readability_index  
Flesch-Kincaid readability tests - https://en.wikipedia.org/wiki/Flesch%E2%80%93Kincaid_readability_tests  
SMOG(Simple Measure of Gobbledygook) - https://en.wikipedia.org/wiki/SMOG  
Coleman–Liau index - https://en.wikipedia.org/wiki/Coleman%E2%80%93Liau_index

Program will show a score of a text's difficulty with a chosen algorithm (or all of them) and corresponding age.

## Usage
Compilation:
```
javac -d out srcs/*.java
```
Launch:
```
java -cp "./lib/*:./out" Main [-s] [filename]
```
**-s** flag means, that the table score-age-grade_level will be created as a SQL table in the root folder of the program, and each corresponding age for a score will be found in the table with SELECT query. Without flag corresponidng age will be found in hashmap, and therefore without -s flag program works much faster.  

Example file "in.txt" is present in the root.  

## Example output
```
java -cp "./lib/*:./out" Main in.txt
The text is:
This is the front page of the Simple English Wikipedia. Wikipedias are places where people
work together to write encyclopedias in different languages. We use Simple English words
and grammar here. The Simple English Wikipedia is for everyone! That includes children and
adults who are learning English. There are 142,262 articles on the Simple English
Wikipedia. All of the pages are free to use. They have all been published under both the
Creative Commons License and the GNU Free Documentation License. You can help here! You
may change these pages and make new pages. Read the help pages and other good pages to
learn how to write pages here. If you need help, you may ask questions at Simple talk. Use
Basic English vocabulary and shorter sentences. This allows people to understand normally
complex terms or phrases.


Words: 137
Sentences: 14
Characters: 687
Syllables: 210
Polysyllables: 18
Enter the score you want to calculate (ARI, FK, SMOG, CL, all): all

Automated Readability Index: 7.08 (about 13 year olds).
Flesch–Kincaid readability tests: 6.31 (about 12 year olds).
Simple Measure of Gobbledygook: 9.61 (about 16 year olds).
Coleman–Liau index:: 10.66 (about 17 year olds).

This text should be understood in average by 14.50 year olds.
```

## Used technologies
- Regex
- SQL table and JDBC driver (H2 Database)
