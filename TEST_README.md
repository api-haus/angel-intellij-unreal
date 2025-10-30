# AngelScript LSP Integration Tests

## Overview

This document describes the LSP integration tests for the AngelScript IntelliJ plugin.

## Test Suite

The test suite is located in `src/test/kotlin/com/github/sashi0034/angelintellij/lsp/AngelScriptLspIntegrationTest.kt`.

### Test Cases

1. **testLspServerStarts** - Verifies that the LSP server can be started successfully
2. **testSimpleAngelScriptFile** - Tests creation and processing of a simple AngelScript file
3. **testSyntaxErrorFile** - Tests that files with syntax errors can be opened without crashing
4. **testLoadSimpleFile** - Tests loading files from test data directory
5. **testBasicStructure** - Tests AngelScript files with namespace and class structures
6. **testMultipleFiles** - Tests opening multiple AngelScript files simultaneously
7. **testLanguageServerManager** - Tests accessing the Language Server Manager

## Test Data

Test data files are located in `src/test/testData/lsp/`:

- `simple.as` - A valid AngelScript file
- `syntax_error.as` - A file with intentional syntax errors
- `completion_test.as` - A file for testing code completion

## Known Issues

### JUnit ClassNotFoundException Issue

There is a known compatibility issue between IntelliJ Platform Gradle Plugin 2.5.0 and JUnit 4 that causes the following error:

```
java.lang.NoClassDefFoundError: org/opentest4j/AssertionFailedError
```

This is a classpath issue where the test framework expects `opentest4j` classes that are part of JUnit 5.

### Workaround Options

1. **Run tests in IntelliJ IDEA directly**:
   - Open the project in IntelliJ IDEA
   - Right-click on the test class
   - Select "Run 'AngelScriptLspIntegrationTest'"
   - The IDE test runner handles the classpath correctly

2. **Use runIde for manual testing**:
   ```bash
   ./gradlew runIde
   ```
   Then manually test the LSP integration by:
   - Creating a new .as file
   - Observing the LSP console (View > Tool Windows > LSP Consoles)
   - Verifying that the AngelScript Language Server starts

3. **Check the LSP Console**:
   - The LSP console shows all LSP communication
   - Server status (starting/started/stopped)
   - Request/response logging
   - Error messages from the language server

## Manual Testing Checklist

To manually verify the LSP integration is working:

- [ ] Create a new `.as` file in a project
- [ ] Open the LSP console (View > Tool Windows > LSP Consoles)
- [ ] Verify "AngelScript Language Server" appears in the list
- [ ] Check that the server status transitions to "started"
- [ ] Type some AngelScript code and verify no errors in the console
- [ ] Test basic features:
  - [ ] Syntax highlighting works
  - [ ] Code completion triggers (Ctrl+Space)
  - [ ] Error diagnostics appear for invalid code
  - [ ] Hover documentation works

## Test File Examples

### Simple Valid File
```angelscript
class SimpleClass
{
    void TestMethod()
    {
        Print("Hello, World!");
    }
}
```

### File with Namespace
```angelscript
namespace TestNamespace
{
    class MyClass
    {
        int myField;
        
        void MyMethod(int param)
        {
            myField = param;
        }
    }
}
```

### File with Syntax Error
```angelscript
class BrokenClass
{
    void Method(
    // Missing closing parenthesis and brace
```

## Future Improvements

- Add integration tests that can run in CI/CD
- Test specific LSP features (completion, diagnostics, hover, etc.)
- Add performance tests for large files
- Test LSP server restart scenarios
- Test configuration changes

## References

- [LSP4IJ Documentation](https://github.com/redhat-developer/lsp4ij)
- [Language Server Protocol Specification](https://microsoft.github.io/language-server-protocol/)
- [IntelliJ Platform Plugin SDK](https://plugins.jetbrains.com/docs/intellij/welcome.html)
