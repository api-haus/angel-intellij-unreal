package com.github.sashi0034.angelintellij.editor;

import com.github.sashi0034.angelintellij.psi.AngelScriptTokenTypes;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.spellchecker.tokenizer.SpellcheckingStrategy;
import com.intellij.spellchecker.tokenizer.Tokenizer;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * Spell checking strategy for AngelScript files.
 * Excludes Unreal Engine specific macros and identifiers from spell checking.
 */
public class AngelScriptSpellCheckingStrategy extends SpellcheckingStrategy {
    
    /**
     * Unreal Engine macros that should be excluded from spell checking.
     * These are common Unreal-specific identifiers that would be flagged as typos.
     */
    private static final Set<String> UNREAL_MACROS = Set.of(
            // Unreal property macros
            "UPROPERTY",
            "UFUNCTION",
            "UCLASS",
            "USTRUCT",
            "UENUM",
            "UINTERFACE",
            "UDELEGATE",
            
            // Unreal property specifiers (commonly used)
            "BlueprintReadWrite",
            "BlueprintReadOnly",
            "EditAnywhere",
            "EditDefaultsOnly",
            "EditInstanceOnly",
            "VisibleAnywhere",
            "VisibleDefaultsOnly",
            "VisibleInstanceOnly",
            "Category",
            "BlueprintCallable",
            "BlueprintPure",
            "BlueprintImplementableEvent",
            "BlueprintNativeEvent",
            "CallInEditor",
            "Exec",
            "Server",
            "Client",
            "NetMulticast",
            "Reliable",
            "Unreliable",
            "WithValidation",
            "BlueprintAuthorityOnly",
            "BlueprintCosmetic",
            "Transient",
            "DuplicateTransient",
            "TextExportTransient",
            "NonPIEDuplicateTransient",
            "SaveGame",
            "AssetRegistrySearchable",
            "SimpleDisplay",
            "AdvancedDisplay",
            "Config",
            "GlobalConfig",
            "Localized",
            "Instanced",
            "BlueprintAssignable",
            "Replicated",
            "ReplicatedUsing",
            "NotReplicated",
            "RepSkip",
            "Interp",
            "NonTransactional",
            "NoClear",
            "EditFixedSize",
            "NoDestructor",
            "AutoWeak",
            
            // Common Unreal prefixes that might appear in identifiers
            "UObject",
            "AActor",
            "UActorComponent",
            "USceneComponent",
            "APawn",
            "ACharacter",
            "APlayerController",
            "UWorld",
            "FVector",
            "FRotator",
            "FTransform",
            "FString",
            "FName",
            "TArray",
            "TSubclassOf",
            "TWeakObjectPtr",
            "TSoftObjectPtr",
            "FSoftObjectPath"
    );
    
    @NotNull
    @Override
    public Tokenizer<?> getTokenizer(PsiElement element) {
        // Skip spell checking for identifiers that match Unreal macros
        if (element instanceof LeafPsiElement) {
            LeafPsiElement leaf = (LeafPsiElement) element;
            
            // Check if this is an identifier token
            if (leaf.getElementType() == AngelScriptTokenTypes.IDENTIFIER) {
                String text = leaf.getText();
                
                // Skip spell checking if this matches a known Unreal macro
                if (UNREAL_MACROS.contains(text)) {
                    return EMPTY_TOKENIZER;
                }
                
                // Skip spell checking for identifiers starting with common Unreal prefixes
                if (startsWithUnrealPrefix(text)) {
                    return EMPTY_TOKENIZER;
                }
                
                // For regular identifiers, enable spell checking
                return TEXT_TOKENIZER;
            }
            
            // Skip spell checking for keywords
            if (leaf.getElementType() == AngelScriptTokenTypes.KEYWORD) {
                return EMPTY_TOKENIZER;
            }
        }
        
        // Use default tokenizer for other elements (like comments and strings)
        return super.getTokenizer(element);
    }
    
    /**
     * Check if an identifier starts with a common Unreal Engine prefix.
     * These prefixes indicate Unreal-specific types that shouldn't be spell-checked.
     */
    private boolean startsWithUnrealPrefix(String text) {
        // Unreal uses specific prefixes for different types:
        // U - UObject-derived classes
        // A - AActor-derived classes
        // F - Plain structs/data structures
        // T - Template classes
        // I - Interface classes
        // E - Enum types
        if (text.length() < 2) {
            return false;
        }
        
        char firstChar = text.charAt(0);
        char secondChar = text.charAt(1);
        
        // Check for Unreal prefixes followed by uppercase letter
        return (firstChar == 'U' || firstChar == 'A' || firstChar == 'F' || 
                firstChar == 'T' || firstChar == 'I' || firstChar == 'E') &&
               Character.isUpperCase(secondChar);
    }
}
