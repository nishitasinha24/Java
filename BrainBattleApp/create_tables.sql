create table login(ID INT, firstName VARCHAR(25), lastName VARCHAR(25), emailID VARCHAR(25), password VARCHAR(25));
create table category(categoryId varchar(25), categoryName varchar(25));
create table questions(Id int, answerOption varchar(40), answer varchar(40), option1 varchar(40),option2 varchar(40),option3 varchar(40),option4 varchar(40),categoryId varchar(25), description varchar(432));
create table results(userID varchar(25), categoryId varchar(25), score varchar(25));