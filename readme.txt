cd C:\Users\games\Desktop\MyProjects\StudyProjectsSS\src\

javac -encoding utf8 -d ./build *.java

cd C:\Users\games\Desktop\MyProjects\StudyProjectsSS\src\build

jar cvf YourJar.jar *

java -cp YourJar.jar Main C:\Users\games\Desktop\MyProjects\StudyProjectsSS\src\resources\clients_list.txt C:\Users\games\Desktop\MyProjects\StudyProjectsSS\src\resources\result.txt
