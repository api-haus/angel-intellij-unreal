# AngelScript for Unreal — IntelliJ Plugin

![Build](../../actions/workflows/build.yml/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/26645-angelscript-language-server.svg)](https://plugins.jetbrains.com/plugin/26645-angelscript-language-server)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/26645-angelscript-language-server.svg)](https://plugins.jetbrains.com/plugin/26645-angelscript-language-server)

## Plugin description

<!-- Plugin description -->
This fork provides AngelScript language support tailored for Unreal Engine projects, powered by [lsp4ij](https://github.com/redhat-developer/lsp4ij).

It leverages the Unreal AngelScript Language Server to provide completion, go‑to definition, diagnostics, and more.

This is a port of the Unreal AngelScript VS Code tooling to IntelliJ‑based IDEs.

- Upstream language server: https://github.com/Hazelight/vscode-unreal-angelscript/tree/master/language-server
- Vendored source in this repo: `unreal-angelscript-lsp/language-server`

**This plugin uses Node.js, so it must be installed.**

Note: This plugin is still in preview and under development.
<!-- Plugin description end -->

## Installation

- Using the IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "angel-intellij"</kbd> >
  <kbd>Install</kbd>
  
- Using JetBrains Marketplace:

  Go to [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID) and install it by clicking the <kbd>Install to ...</kbd> button in case your IDE is running.

  You can also download the [latest release](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID/versions) from JetBrains Marketplace and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

- Manually:

  Download the [latest release](../../releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
