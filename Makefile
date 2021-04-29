all:
	mvn clean package 
	java -jar target/swingy-1.0.jar

clean fclean:
	mvn clean