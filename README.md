# bank-account

# ########################################################################################
# Solution 
# ########################################################################################
Prérecuis techniques:
  springboot
  java17/+
  base de données H2
  mapstruct
  jackson
  lombok
  
# ########################################################################################
# Description
# ########################################################################################

La solution proposée consiste à mettre en place un API REST (en utilisant springboot et une BDD H2 pour stocker \nles données), qui met à disposition de l'utlisateur un ensemble de services qui lui permettent de : \n
	- Effectuer des transactions sur son compte bancaire. Une transaction peut être : \n
		- Depot (Deposit) de l'argent ===> endpoint : /account/deposit?amount=<value> \n
		- Retrait (Withdraw) de l'argent ===> endpoint : /account/withdraw?amount=<value> \n
	- Afficher son relevé de compte ===> endpoint : /account/statment\n
 
L'utilisateur peut mettre sur son compte bancaire autant d'argent qu'il veut \n
L'utilisateur peut retirer de l'argent dans le cas où il a suffisemment d'argent sur son compte bancaire

# ########################################################################################
# Lancement de la solution
# ########################################################################################
Pour tester la solution :

	- Il faut recupérer le projet sur un poste local : git clone <repoName>
	- Il faut Lancer le projet: lancer une application springboot (build and run)
	
Une fois l'application est lancée, ouvrez une console gitbatch et déroulez ce scénarion de test:

# ########################################################################################
# Test
# ########################################################################################

--> Commande 1:
$ curl -X POST "http://localhost:8080/account/withdraw?amount=300"

 --> Resultat 1:
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   130    0   130    0     0   4216      0 --:--:-- --:--:-- --:--:--  4333{"error":"Insufficient Funds","timestamp":"2025-06-17T18:38:52.6237985","message":"Cannot withdraw: balance is zero or negative."}


-->  Commande 2
$ curl -X POST "http://localhost:8080/account/deposit?amount=1000"

-->  Resultat 2
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    47    0    47    0     0    534      0 --:--:-- --:--:-- --:--:--   540{"message":"Deposit successful","balance":1000}


-->  Commande 3
$ curl -X POST "http://localhost:8080/account/withdraw?amount=300"

--> Resultat 3
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    47    0    47    0     0   3119      0 --:--:-- --:--:-- --:--:--  3133{"message":"Withdraw successful","balance":700}


--> Commande 4
$ curl -X POST "http://localhost:8080/account/withdraw?amount=300"

--> Resultat 4
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    47    0    47    0     0   2761      0 --:--:-- --:--:-- --:--:--  2937{"message":"Withdraw successful","balance":400}


--> Commande 5
$ curl -X POST "http://localhost:8080/account/withdraw?amount=300"

--> Resultat 5
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    47    0    47    0     0   3023      0 --:--:-- --:--:-- --:--:--  3133{"message":"Withdraw successful","balance":100}


--> Commande 6
$ curl -X POST "http://localhost:8080/account/withdraw?amount=300"

--> Resultat 6
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   130    0   130    0     0  10208      0 --:--:-- --:--:-- --:--:-- 10833{"error":"Insufficient Funds","timestamp":"2025-06-17T18:39:16.8397636","message":"Cannot withdraw: balance is zero or negative."}


--> Commande 7
$ curl -X GET "http://localhost:8080/account/statment"

--> Resultat 7
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   324    0   324    0     0  20968      0 --:--:-- --:--:-- --:--:-- 21600{"message":"Statement retrieved successfully","generatedAt":"2025-06-17T18:39:22.0057568","finalBalance":100,"statement":[{"date":"2025-06-17","amount":1000,"balance":1000},{"date":"2025-06-17","amount":-300,"balance":700},{"date":"2025-06-17","amount":-300,"balance":400},{"date":"2025-06-17","amount":-300,"balance":100}]}

