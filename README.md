# COMIX1
Connor Witkiewicz, Drew Pawluckie, Jack Vander Linden, Jaime Diaz

# Build Instructions

- To build the code, run `mvn compile` from the project's root folder.
- To run the code in the terminal, run `mvn -q exec:java`.
- Before running the program, make sure you have a db.yml file under 
`src/main/resources/yml/` with credentials to access a 
PostgreSQL database. Follow the template in 
[template.yml](src/main/resources/yml/template.yml) to format this file.