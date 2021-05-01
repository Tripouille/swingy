all:
	mvn clean package 
	java -jar target/swingy-1.0.jar console

clean fclean:
	mvn clean