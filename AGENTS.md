# AGENTS.md

## Build/Lint/Test Commands

### AngelScript LSP (VSCode extension)
- Build: `npm run compile` (from angel-lsp/)
- Lint: `npm run lint` (from angel-lsp/)
- Test all: `npm run test` (from angel-lsp/)
- Test single file: `cd angel-lsp/server && npx mocha test/compiler/analyzer/classConstructor.spec.ts`
- Watch mode: `npm run watch` (from angel-lsp/)

### IntelliJ Plugin
- Build: `./gradlew build`
- Run IDE: `./gradlew runIde`
- Test: `./gradlew test`

## Code Style Guidelines

### TypeScript (LSP)
- Strict TypeScript with ES2020 target
- Semicolons required
- No unused vars enforcement disabled
- No explicit any enforcement disabled
- CommonJS modules
- 4-space indentation

### Kotlin (IntelliJ Plugin)
- Kotlin JVM 17
- Standard IntelliJ Platform conventions
- Null safety practices

### General Conventions
- camelCase for variables/functions
- PascalCase for classes/types
- Descriptive naming
- Error handling with try/catch where appropriate
- Async/await for asynchronous operations
- DO NOT UPDATE CHANGELOG.md! It is managed by CI pipeline.
- 