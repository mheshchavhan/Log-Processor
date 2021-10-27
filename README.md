# Log-Processor
Simple code for processing logs in json format
# How to run
1. Clone the repository from https://github.com/mheshchavhan/Log-Processor/
2. (Optional)Import the cloned project into your IDE of preference.
3. Install maven on your system and set maven home in your path.
4. Create db with name 'testdb' in HSQLDB
5. Open bash/cmd and go to the project base folder.
6. run command 'mvn clean' to clean the maven project.
7. run command 'mvn exec:java -Dexec.args="<your log file path>"' to run the project(or build the pom.xml from IDE using goal 'exec:java -Dexec.args="<your log file path>"')
