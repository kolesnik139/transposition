# Transposition Java Console Application

This is a simple Java console application to transpose notes in a musical piece.
It takes musical piece from JSON file, handles it and saves to another JSON file.

## How to Build the Project with Maven

1. Make sure Apache Maven 3+ is installed on your machine.
2. Make sure Java 17+ is installed on your machine.
3. Open console.
4. Navigate to the root directory of the project where the `pom.xml` file is located:
   ```bash
   cd <project root folder>
   ```
5. Execute the Maven build command:
   ```bash
   mvn clean package
   ```
   This command will build the project, create a JAR file, and place it in the `target` directory.

## How to Run the Application

1. After building the project, navigate to the `target` directory:
   ```bash
   cd target
   ```
2. Run the JAR file with the following command:
   ```bash
   java -jar transposition-1.0-SNAPSHOT-jar-with-dependencies.jar <input file path> <offset> <output file path>
   ```
   For example:
      ```bash
   java -jar transposition-1.0-SNAPSHOT-jar-with-dependencies.jar ../input.json -3 ../output.json
   ```


