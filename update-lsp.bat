@echo off
REM Save the current directory
set "ROOT_DIR=%cd%"

echo Installing root dependencies for bundling...
call npm install

echo.
echo Installing language server dependencies...
cd unreal-angelscript-lsp\language-server
call npm install

echo.
echo Compiling TypeScript...
call npx tsc

REM Go back to the original directory
cd /d "%ROOT_DIR%"

echo.
echo Bundling language server...
call npm run bundle

echo.
echo Done! Language server bundled to src\main\resources\js\angelscript-language-server.js
