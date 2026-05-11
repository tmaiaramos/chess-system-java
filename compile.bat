@echo off
mkdir bin 2>nul
javac -d bin src\*.java src\chess\*.java src\chess\pieces\*.java
echo Compilado com sucesso!