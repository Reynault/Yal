# Projet Compilation

## Description du projet

Ce projet est un compilateur permettant de passer du langage __YAL__ 
au langage __MIPS__.

## Description du langage YAL
Le langage __YAL__ est un langage de programmation type rudimentaire 
incluant les variables entières, les tableaux, les structures 
de contrôles élémentaires et les fonctions.

## Commandes de compilation de jflex / cup
* java -jar /opt/depot/projetToucan/java-cup-11a.jar -parser AnalyseurSyntaxique -symbols CodesLexicaux Grammaire.cup
* java -jar /opt/depot/projetToucan/jflex-1.6.1.jar AnalyseurLexical.jflex

## Version du projet
### [Yal0](Yal0)
#### description 
Le compilateur ne traite que des programmes avec des instructions d'écriture sur la sortie standard.

#### Constructions reconnues par le compilateur
* Commentaires
* Instructions d'écriture
* Le compilateur reconnaît également un programme sans instructions

### [Yal1](Yal1)
#### description
Le compilateur peut désormais traiter  
des programmes incluant des instructions de déclaration de variables entières, des affectations,
 des expressions résuites à une constante entière et des instructions de lecture.

#### Constructions reconnues par le compilateur
* Déclaration de variables entières
* Affectation
* Instructions de lecture
* Expressions réduites à une constante entière

### [Yal2](Yal2)
#### description
Le compilateur peut désormais traiter des programmes incluant
des expressions générales (logiques, arithmetique), des conditions et des boucles.

#### Constructions reconnues par le compilateur
* Expressions quelconques
* Instruction conditionnelle
* Instruction itérative

### [Yal3](Yal3)
#### description
Cette version permet à l'utilisateur de créer des fonctions sans paramètres ni variables locales.
Les fonctions sont gérées avec le chaînage statique et dynamique.
La table des symboles possède un arbre qui possède les tables locales des
fonctions du programme.

#### Constructions reconnues par le compilateur
* Fonctions sans paramètres.

### [Yal4](Yal4)
#### description
Cette version ajoute les fonctions paramétrées ainsi que les déclarations de variables dans
les fonctions.

### Constructions reconnues par le compilateur
* Fonctions avec paramètres.
* Variables dans une fonction.

## Participants
* Sies Reynault
* Cesaro Alexis
* Romary Julien
