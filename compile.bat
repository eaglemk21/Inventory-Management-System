@echo off
echo Compiling Inventory Management System...

mkdir bin 2>nul
mkdir data 2>nul

rem Compile all java files recursively
for /R src %%f in (*.java) do (
    javac -d bin "%%f"
)

if %errorlevel% equ 0 (
    echo.
    echo ? Compilation successful!
    echo.
    echo To run the application:
    echo java -cp bin main.InventoryManagementSystem
) else (
    echo.
    echo ? Compilation failed!
    pause
)
