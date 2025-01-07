# Dream of the Rabbit

### The game is in development stage

In case of curiousity, you can type these commands in terminal for test

Make sure that you are in the **root folder** of the game:

## For MacOS and Linux
```zsh
export CLASSPATH=<YOUR-PATH-TO-GAME>/src

javac -d out src/main/*.java
javac -d out src/entities/*.java
javac -d out src/mathtools/*.java

cp -r src/res out

java -cp out main.Main
```

## For Windows
```cmd
SET CLASSPATH=<YOUR-PATH-TO-GAME>\src

javac -d out src/main/*.java
javac -d out src/entities/*.java
javac -d out src/mathtools/*.java

XCOPY /E src\res out\res\

java -cp out main.Main

```

## IMPORTANT NOTE: It is highly recommended to install Java 23.0.1 (this version exactly) for the proper image display

