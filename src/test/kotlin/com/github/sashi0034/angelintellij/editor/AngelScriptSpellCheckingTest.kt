package com.github.sashi0034.angelintellij.editor

import com.github.sashi0034.angelintellij.psi.AngelScriptTokenTypes
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.intellij.spellchecker.tokenizer.Tokenizer
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.junit.Assert.*

class AngelScriptSpellCheckingTest : BasePlatformTestCase() {

    /**
     * Test that UPROPERTY macro is excluded from spell checking
     */
    fun testUPropertyNotSpellChecked() {
        val strategy = AngelScriptSpellCheckingStrategy()
        val file = myFixture.configureByText(
            "test.as",
            """
            UPROPERTY()
            int MyVariable;
            """.trimIndent()
        )

        // Find the UPROPERTY identifier in the PSI tree
        var foundUproperty = false
        file.accept(object : com.intellij.psi.PsiRecursiveElementVisitor() {
            override fun visitElement(element: com.intellij.psi.PsiElement) {
                super.visitElement(element)
                if (element is LeafPsiElement && 
                    element.elementType == AngelScriptTokenTypes.IDENTIFIER &&
                    element.text == "UPROPERTY") {
                    foundUproperty = true
                    val tokenizer = strategy.getTokenizer(element)
                    assertEquals(
                        "UPROPERTY should be excluded from spell checking",
                        com.intellij.spellchecker.tokenizer.SpellcheckingStrategy.EMPTY_TOKENIZER,
                        tokenizer
                    )
                }
            }
        })

        assertTrue("Should find UPROPERTY in the file", foundUproperty)
    }

    /**
     * Test that UFUNCTION macro is excluded from spell checking
     */
    fun testUFunctionNotSpellChecked() {
        val strategy = AngelScriptSpellCheckingStrategy()
        val file = myFixture.configureByText(
            "test.as",
            """
            UFUNCTION(BlueprintCallable)
            void MyFunction() {}
            """.trimIndent()
        )

        var foundUfunction = false
        file.accept(object : com.intellij.psi.PsiRecursiveElementVisitor() {
            override fun visitElement(element: com.intellij.psi.PsiElement) {
                super.visitElement(element)
                if (element is LeafPsiElement && 
                    element.elementType == AngelScriptTokenTypes.IDENTIFIER &&
                    element.text == "UFUNCTION") {
                    foundUfunction = true
                    val tokenizer = strategy.getTokenizer(element)
                    assertEquals(
                        "UFUNCTION should be excluded from spell checking",
                        com.intellij.spellchecker.tokenizer.SpellcheckingStrategy.EMPTY_TOKENIZER,
                        tokenizer
                    )
                }
            }
        })

        assertTrue("Should find UFUNCTION in the file", foundUfunction)
    }

    /**
     * Test that BlueprintCallable specifier is excluded from spell checking
     */
    fun testBlueprintCallableNotSpellChecked() {
        val strategy = AngelScriptSpellCheckingStrategy()
        val file = myFixture.configureByText(
            "test.as",
            """
            UFUNCTION(BlueprintCallable)
            void MyFunction() {}
            """.trimIndent()
        )

        var foundBlueprintCallable = false
        file.accept(object : com.intellij.psi.PsiRecursiveElementVisitor() {
            override fun visitElement(element: com.intellij.psi.PsiElement) {
                super.visitElement(element)
                if (element is LeafPsiElement && 
                    element.elementType == AngelScriptTokenTypes.IDENTIFIER &&
                    element.text == "BlueprintCallable") {
                    foundBlueprintCallable = true
                    val tokenizer = strategy.getTokenizer(element)
                    assertEquals(
                        "BlueprintCallable should be excluded from spell checking",
                        com.intellij.spellchecker.tokenizer.SpellcheckingStrategy.EMPTY_TOKENIZER,
                        tokenizer
                    )
                }
            }
        })

        assertTrue("Should find BlueprintCallable in the file", foundBlueprintCallable)
    }

    /**
     * Test that Unreal prefixed types are excluded (UObject, AActor, FVector, etc.)
     */
    fun testUnrealPrefixedTypesNotSpellChecked() {
        val strategy = AngelScriptSpellCheckingStrategy()
        val file = myFixture.configureByText(
            "test.as",
            """
            UObject obj;
            AActor actor;
            FVector position;
            TArray<int> numbers;
            """.trimIndent()
        )

        val unrealTypes = listOf("UObject", "AActor", "FVector", "TArray")
        val foundTypes = mutableSetOf<String>()

        file.accept(object : com.intellij.psi.PsiRecursiveElementVisitor() {
            override fun visitElement(element: com.intellij.psi.PsiElement) {
                super.visitElement(element)
                if (element is LeafPsiElement && 
                    element.elementType == AngelScriptTokenTypes.IDENTIFIER &&
                    element.text in unrealTypes) {
                    foundTypes.add(element.text)
                    val tokenizer = strategy.getTokenizer(element)
                    assertEquals(
                        "${element.text} should be excluded from spell checking",
                        com.intellij.spellchecker.tokenizer.SpellcheckingStrategy.EMPTY_TOKENIZER,
                        tokenizer
                    )
                }
            }
        })

        for (type in unrealTypes) {
            assertTrue("Should find $type in the file", foundTypes.contains(type))
        }
    }

    /**
     * Test that regular identifiers are still spell checked (not excluded)
     */
    fun testRegularIdentifiersAreSpellChecked() {
        val strategy = AngelScriptSpellCheckingStrategy()
        val file = myFixture.configureByText(
            "test.as",
            """
            void MyFunction()
            {
                int myVariable = 5;
            }
            """.trimIndent()
        )

        var foundRegularIdentifier = false
        file.accept(object : com.intellij.psi.PsiRecursiveElementVisitor() {
            override fun visitElement(element: com.intellij.psi.PsiElement) {
                super.visitElement(element)
                if (element is LeafPsiElement && 
                    element.elementType == AngelScriptTokenTypes.IDENTIFIER &&
                    element.text == "myVariable") {
                    foundRegularIdentifier = true
                    val tokenizer = strategy.getTokenizer(element)
                    assertNotEquals(
                        "Regular identifiers should still be spell checked",
                        com.intellij.spellchecker.tokenizer.SpellcheckingStrategy.EMPTY_TOKENIZER,
                        tokenizer
                    )
                }
            }
        })

        assertTrue("Should find myVariable in the file", foundRegularIdentifier)
    }

    /**
     * Test that keywords are excluded from spell checking
     */
    fun testKeywordsNotSpellChecked() {
        val strategy = AngelScriptSpellCheckingStrategy()
        val file = myFixture.configureByText(
            "test.as",
            """
            void MyFunction()
            {
                int x = 5;
            }
            """.trimIndent()
        )

        var foundKeyword = false
        file.accept(object : com.intellij.psi.PsiRecursiveElementVisitor() {
            override fun visitElement(element: com.intellij.psi.PsiElement) {
                super.visitElement(element)
                if (element is LeafPsiElement && 
                    element.elementType == AngelScriptTokenTypes.KEYWORD) {
                    foundKeyword = true
                    val tokenizer = strategy.getTokenizer(element)
                    assertEquals(
                        "Keywords should be excluded from spell checking",
                        com.intellij.spellchecker.tokenizer.SpellcheckingStrategy.EMPTY_TOKENIZER,
                        tokenizer
                    )
                }
            }
        })

        assertTrue("Should find at least one keyword in the file", foundKeyword)
    }

    /**
     * Test multiple Unreal macros in one file
     */
    fun testMultipleUnrealMacros() {
        val strategy = AngelScriptSpellCheckingStrategy()
        val file = myFixture.configureByText(
            "test.as",
            """
            UCLASS()
            class AMyActor
            {
                UPROPERTY(BlueprintReadWrite, EditAnywhere)
                int Health;
                
                UFUNCTION(BlueprintCallable)
                void TakeDamage(int Amount)
                {
                    Health -= Amount;
                }
            }
            """.trimIndent()
        )

        val unrealMacros = listOf(
            "UCLASS", 
            "UPROPERTY", 
            "UFUNCTION", 
            "BlueprintReadWrite", 
            "EditAnywhere",
            "BlueprintCallable"
        )
        val foundMacros = mutableSetOf<String>()

        file.accept(object : com.intellij.psi.PsiRecursiveElementVisitor() {
            override fun visitElement(element: com.intellij.psi.PsiElement) {
                super.visitElement(element)
                if (element is LeafPsiElement && 
                    element.elementType == AngelScriptTokenTypes.IDENTIFIER &&
                    element.text in unrealMacros) {
                    foundMacros.add(element.text)
                    val tokenizer = strategy.getTokenizer(element)
                    assertEquals(
                        "${element.text} should be excluded from spell checking",
                        com.intellij.spellchecker.tokenizer.SpellcheckingStrategy.EMPTY_TOKENIZER,
                        tokenizer
                    )
                }
            }
        })

        for (macro in unrealMacros) {
            assertTrue("Should find $macro in the file", foundMacros.contains(macro))
        }
    }
}
