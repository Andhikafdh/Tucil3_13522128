# Penyelesaian Permainan Word Ladder Menggunakan Algoritma UCS, Greedy Best First Search, dan A*
Tugas Kecil 3 Mata Kuliah IF2211 Strategi Algoritma 2023

## **Table of Contents**
* [General Information](#general-information)
* [Requirements](#requirements)
* [How to Run and Compile (Windows)](#how-to-run-and-compile-windows)
* [Screenshot](#screenshot)
* [Program Structure](#program-structure)
* [Authors](#authors)

## **General Information**
>Word ladders (also known as Doublets, word-links, change-the-word puzzles,
paragraphs, laddergrams, or word golf) is one of the famous word games
for all groups. The word ladder was invented by Lewis Carroll, a writer and
mathematician, in 1877. In this game, players are given two words
referred to as start words and end words. To win the game, players must
Find a chain of words that can connect the start word and end word.
The number of letters at the start word and end word is always the same. Each word is close together
In a chain of words, only one letter can differ. In this game,
The optimal solution is expected, namely the solution that minimizes the number of words entered
on a chain of words. The following is an illustration and game rules.

## **Requirements**
To use this program, you will need to install **Java 22** (https://www.oracle.com/id/java/technologies/downloads/) on the device you are using. 

## **How to Run and Compile (Windows)**
### **Setup**
1. Clone this repository <br>
```sh 
$ git clone git@github.com:Andhikafdh/Tucil3_13522128.git
```
2. Open this repository in terminal

### **Run**
1. Change the directory to the 'src' folder <br>
```sh 
$ cd src
```

2. Compile program <br>
```sh 
$ javac *.java
```

3. Run program <br>
```sh 
$ java Main.java
```

## **Screenshot**

<img src="doc/gui.jpg"> 

## **Program Structure**
```
.
 ┣ doc
 ┃ ┗ Tucil 3_K3_13522128.pdf
 ┣ src
 ┃ ┣ AStar.java
 ┃ ┣ GBFS.java
 ┃ ┣ UCS.java
 ┃ ┣ Main.java
 ┃ ┣ WordLadderSolverGUI.java
 ┃ 
 ┣ test
 ┃ ┣ astar_testcase
 ┃ ┣ gbfs_testcase
 ┃ ┣ ucs_testcase
 ┃ 
 ┗ README.md
```

## **Authors**

| **NIM**  |       **Name**        | **Class**  |       
| :------: | :-------------------: | :------:   | 
| 13522128 |    Mohammad Andhika Fadillah   | K03
