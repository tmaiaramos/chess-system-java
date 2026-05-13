@echo off
mkdir bin 2>nul
echo Gerando lista de arquivos...
dir /s /B src\*.java > sources.txt
echo Compilando...
javac -d bin @sources.txt
del sources.txt
if %errorlevel% equ 0 (
    echo Compilado com sucesso!
) else (
    echo Erro na compilacao!
)
pause