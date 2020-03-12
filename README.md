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

## Used technologies
- Regex
- SQL table and JDBC driver (H2 Database)
