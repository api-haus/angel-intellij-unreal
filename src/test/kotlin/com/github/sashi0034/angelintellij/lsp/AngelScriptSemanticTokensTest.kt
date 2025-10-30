package com.github.sashi0034.angelintellij.lsp

import com.github.sashi0034.angelintellij.language.AngelScriptFileType
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.redhat.devtools.lsp4ij.features.semanticTokens.SemanticTokensHighlightingColors
import org.eclipse.lsp4j.SemanticTokenModifiers
import org.eclipse.lsp4j.SemanticTokenTypes
import org.junit.Assert.*

class AngelScriptSemanticTokensTest : BasePlatformTestCase() {

    /**
     * Test that semantic token color provider handles namespace tokens
     */
    fun testNamespaceSemanticTokens() {
        val provider = AngelScriptSemanticTokensColorProvider()
        val file = myFixture.configureByText(
            "test.as",
            "namespace Test { }"
        )

        // Test namespace declaration
        val namespaceDecl = provider.getTextAttributesKey(
            SemanticTokenTypes.Namespace,
            listOf(SemanticTokenModifiers.Declaration),
            file
        )
        assertEquals(
            "Namespace declaration should have correct color",
            SemanticTokensHighlightingColors.NAMESPACE_DECLARATION,
            namespaceDecl
        )

        // Test namespace reference
        val namespaceRef = provider.getTextAttributesKey(
            SemanticTokenTypes.Namespace,
            emptyList(),
            file
        )
        assertEquals(
            "Namespace reference should have correct color",
            SemanticTokensHighlightingColors.NAMESPACE,
            namespaceRef
        )
    }

    /**
     * Test that semantic token color provider handles class tokens
     */
    fun testClassSemanticTokens() {
        val provider = AngelScriptSemanticTokensColorProvider()
        val file = myFixture.configureByText(
            "test.as",
            "class MyClass { }"
        )

        // Test class declaration
        val classDecl = provider.getTextAttributesKey(
            SemanticTokenTypes.Class,
            listOf(SemanticTokenModifiers.Declaration),
            file
        )
        assertEquals(
            "Class declaration should have correct color",
            SemanticTokensHighlightingColors.CLASS_DECLARATION,
            classDecl
        )

        // Test class reference
        val classRef = provider.getTextAttributesKey(
            SemanticTokenTypes.Class,
            emptyList(),
            file
        )
        assertEquals(
            "Class reference should have correct color",
            SemanticTokensHighlightingColors.CLASS,
            classRef
        )
    }

    /**
     * Test that semantic token color provider handles method tokens
     */
    fun testMethodSemanticTokens() {
        val provider = AngelScriptSemanticTokensColorProvider()
        val file = myFixture.configureByText(
            "test.as",
            "class C { void Method() { } }"
        )

        // Test method declaration
        val methodDecl = provider.getTextAttributesKey(
            SemanticTokenTypes.Method,
            listOf(SemanticTokenModifiers.Declaration),
            file
        )
        assertEquals(
            "Method declaration should have correct color",
            SemanticTokensHighlightingColors.METHOD_DECLARATION,
            methodDecl
        )

        // Test static method
        val staticMethod = provider.getTextAttributesKey(
            SemanticTokenTypes.Method,
            listOf(SemanticTokenModifiers.Static),
            file
        )
        assertEquals(
            "Static method should have correct color",
            SemanticTokensHighlightingColors.STATIC_METHOD,
            staticMethod
        )

        // Test regular method
        val method = provider.getTextAttributesKey(
            SemanticTokenTypes.Method,
            emptyList(),
            file
        )
        assertEquals(
            "Method should have correct color",
            SemanticTokensHighlightingColors.METHOD,
            method
        )
    }

    /**
     * Test that semantic token color provider handles function tokens
     */
    fun testFunctionSemanticTokens() {
        val provider = AngelScriptSemanticTokensColorProvider()
        val file = myFixture.configureByText(
            "test.as",
            "void Function() { }"
        )

        // Test function declaration
        val funcDecl = provider.getTextAttributesKey(
            SemanticTokenTypes.Function,
            listOf(SemanticTokenModifiers.Declaration),
            file
        )
        assertEquals(
            "Function declaration should have correct color",
            SemanticTokensHighlightingColors.FUNCTION_DECLARATION,
            funcDecl
        )

        // Test default library function
        val libFunc = provider.getTextAttributesKey(
            SemanticTokenTypes.Function,
            listOf(SemanticTokenModifiers.DefaultLibrary),
            file
        )
        assertEquals(
            "Default library function should have correct color",
            SemanticTokensHighlightingColors.DEFAULT_LIBRARY_FUNCTION,
            libFunc
        )

        // Test regular function
        val func = provider.getTextAttributesKey(
            SemanticTokenTypes.Function,
            emptyList(),
            file
        )
        assertEquals(
            "Function should have correct color",
            SemanticTokensHighlightingColors.FUNCTION,
            func
        )
    }

    /**
     * Test that semantic token color provider handles variable tokens with modifiers
     */
    fun testVariableSemanticTokens() {
        val provider = AngelScriptSemanticTokensColorProvider()
        val file = myFixture.configureByText(
            "test.as",
            "int x = 5;"
        )

        // Test static readonly variable
        val staticReadonly = provider.getTextAttributesKey(
            SemanticTokenTypes.Variable,
            listOf(SemanticTokenModifiers.Static, SemanticTokenModifiers.Readonly),
            file
        )
        assertEquals(
            "Static readonly variable should have correct color",
            SemanticTokensHighlightingColors.STATIC_READONLY_VARIABLE,
            staticReadonly
        )

        // Test static variable
        val staticVar = provider.getTextAttributesKey(
            SemanticTokenTypes.Variable,
            listOf(SemanticTokenModifiers.Static),
            file
        )
        assertEquals(
            "Static variable should have correct color",
            SemanticTokensHighlightingColors.STATIC_VARIABLE,
            staticVar
        )

        // Test readonly variable
        val readonlyVar = provider.getTextAttributesKey(
            SemanticTokenTypes.Variable,
            listOf(SemanticTokenModifiers.Readonly),
            file
        )
        assertEquals(
            "Readonly variable should have correct color",
            SemanticTokensHighlightingColors.READONLY_VARIABLE,
            readonlyVar
        )

        // Test regular variable
        val variable = provider.getTextAttributesKey(
            SemanticTokenTypes.Variable,
            emptyList(),
            file
        )
        assertEquals(
            "Variable should have correct color",
            SemanticTokensHighlightingColors.VARIABLE,
            variable
        )
    }

    /**
     * Test that semantic token color provider handles property tokens with modifiers
     */
    fun testPropertySemanticTokens() {
        val provider = AngelScriptSemanticTokensColorProvider()
        val file = myFixture.configureByText(
            "test.as",
            "class C { int prop; }"
        )

        // Test static readonly property
        val staticReadonly = provider.getTextAttributesKey(
            SemanticTokenTypes.Property,
            listOf(SemanticTokenModifiers.Static, SemanticTokenModifiers.Readonly),
            file
        )
        assertEquals(
            "Static readonly property should have correct color",
            SemanticTokensHighlightingColors.STATIC_READONLY_PROPERTY,
            staticReadonly
        )

        // Test static property
        val staticProp = provider.getTextAttributesKey(
            SemanticTokenTypes.Property,
            listOf(SemanticTokenModifiers.Static),
            file
        )
        assertEquals(
            "Static property should have correct color",
            SemanticTokensHighlightingColors.STATIC_PROPERTY,
            staticProp
        )

        // Test readonly property
        val readonlyProp = provider.getTextAttributesKey(
            SemanticTokenTypes.Property,
            listOf(SemanticTokenModifiers.Readonly),
            file
        )
        assertEquals(
            "Readonly property should have correct color",
            SemanticTokensHighlightingColors.READONLY_PROPERTY,
            readonlyProp
        )

        // Test regular property
        val property = provider.getTextAttributesKey(
            SemanticTokenTypes.Property,
            emptyList(),
            file
        )
        assertEquals(
            "Property should have correct color",
            SemanticTokensHighlightingColors.PROPERTY,
            property
        )
    }

    /**
     * Test that semantic token color provider handles basic token types
     */
    fun testBasicSemanticTokenTypes() {
        val provider = AngelScriptSemanticTokensColorProvider()
        val file = myFixture.configureByText(
            "test.as",
            "int x = 5;"
        )

        // Test all basic token types
        val tests = mapOf(
            SemanticTokenTypes.Enum to SemanticTokensHighlightingColors.ENUM,
            SemanticTokenTypes.Interface to SemanticTokensHighlightingColors.INTERFACE,
            SemanticTokenTypes.Struct to SemanticTokensHighlightingColors.STRUCT,
            SemanticTokenTypes.TypeParameter to SemanticTokensHighlightingColors.TYPE_PARAMETER,
            SemanticTokenTypes.Type to SemanticTokensHighlightingColors.TYPE,
            SemanticTokenTypes.Parameter to SemanticTokensHighlightingColors.PARAMETER,
            SemanticTokenTypes.EnumMember to SemanticTokensHighlightingColors.ENUM_MEMBER,
            SemanticTokenTypes.Decorator to SemanticTokensHighlightingColors.DECORATOR,
            SemanticTokenTypes.Event to SemanticTokensHighlightingColors.EVENT,
            SemanticTokenTypes.Macro to SemanticTokensHighlightingColors.MACRO,
            SemanticTokenTypes.Comment to SemanticTokensHighlightingColors.COMMENT,
            SemanticTokenTypes.String to SemanticTokensHighlightingColors.STRING,
            SemanticTokenTypes.Keyword to SemanticTokensHighlightingColors.KEYWORD,
            SemanticTokenTypes.Number to SemanticTokensHighlightingColors.NUMBER,
            SemanticTokenTypes.Regexp to SemanticTokensHighlightingColors.REGEXP,
            SemanticTokenTypes.Modifier to SemanticTokensHighlightingColors.MODIFIER,
            SemanticTokenTypes.Operator to SemanticTokensHighlightingColors.OPERATOR
        )

        for ((tokenType, expectedColor) in tests) {
            val result = provider.getTextAttributesKey(tokenType, emptyList(), file)
            assertEquals(
                "Token type $tokenType should have correct color",
                expectedColor,
                result
            )
        }
    }

    /**
     * Test that semantic token color provider handles custom AngelScript-specific tokens
     */
    fun testAngelScriptSpecificTokens() {
        val provider = AngelScriptSemanticTokensColorProvider()
        val file = myFixture.configureByText(
            "test.as",
            "int x = 5;"
        )

        // Test "directive" token (unique to angel-lsp)
        val directive = provider.getTextAttributesKey(
            "directive",
            emptyList(),
            file
        )
        assertEquals(
            "Directive should be treated as macro",
            SemanticTokensHighlightingColors.MACRO,
            directive
        )

        // Test "builtin" token (unique to angel-lsp)
        val builtin = provider.getTextAttributesKey(
            "builtin",
            emptyList(),
            file
        )
        assertEquals(
            "Builtin should be treated as keyword",
            SemanticTokensHighlightingColors.KEYWORD,
            builtin
        )

        // Test "label" token (VSCode extension)
        val label = provider.getTextAttributesKey(
            "label",
            emptyList(),
            file
        )
        assertEquals(
            "Label should have correct color",
            SemanticTokensHighlightingColors.LABEL,
            label
        )
    }

    /**
     * Test that semantic token color provider returns null for unknown token types
     */
    fun testUnknownSemanticTokenTypes() {
        val provider = AngelScriptSemanticTokensColorProvider()
        val file = myFixture.configureByText(
            "test.as",
            "int x = 5;"
        )

        val result = provider.getTextAttributesKey(
            "unknown_token_type",
            emptyList(),
            file
        )
        assertNull("Unknown token types should return null", result)
    }
}
