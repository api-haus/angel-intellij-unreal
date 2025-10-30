package com.github.sashi0034.angelintellij.editor;

import com.github.sashi0034.angelintellij.language.AngelScriptLexerAdapter;
import com.github.sashi0034.angelintellij.psi.AngelScriptTokenTypes;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class AngelScriptSyntaxHighlighter implements SyntaxHighlighter {

    public static final TextAttributesKey KEYWORD =
            createTextAttributesKey("ANGELSCRIPT_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey STRING =
            createTextAttributesKey("ANGELSCRIPT_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey NUMBER =
            createTextAttributesKey("ANGELSCRIPT_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey COMMENT =
            createTextAttributesKey("ANGELSCRIPT_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey OPERATOR =
            createTextAttributesKey("ANGELSCRIPT_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey IDENTIFIER =
            createTextAttributesKey("ANGELSCRIPT_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static final TextAttributesKey PARENTHESES =
            createTextAttributesKey("ANGELSCRIPT_PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey BRACKETS =
            createTextAttributesKey("ANGELSCRIPT_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey BRACES =
            createTextAttributesKey("ANGELSCRIPT_BRACES", DefaultLanguageHighlighterColors.BRACES);
    public static final TextAttributesKey COMMA =
            createTextAttributesKey("ANGELSCRIPT_COMMA", DefaultLanguageHighlighterColors.COMMA);
    public static final TextAttributesKey SEMICOLON =
            createTextAttributesKey("ANGELSCRIPT_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey DOT =
            createTextAttributesKey("ANGELSCRIPT_DOT", DefaultLanguageHighlighterColors.DOT);

    private static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[]{KEYWORD};
    private static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[]{STRING};
    private static final TextAttributesKey[] NUMBER_KEYS = new TextAttributesKey[]{NUMBER};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] OPERATOR_KEYS = new TextAttributesKey[]{OPERATOR};
    private static final TextAttributesKey[] IDENTIFIER_KEYS = new TextAttributesKey[]{IDENTIFIER};
    private static final TextAttributesKey[] PARENTHESES_KEYS = new TextAttributesKey[]{PARENTHESES};
    private static final TextAttributesKey[] BRACKETS_KEYS = new TextAttributesKey[]{BRACKETS};
    private static final TextAttributesKey[] BRACES_KEYS = new TextAttributesKey[]{BRACES};
    private static final TextAttributesKey[] COMMA_KEYS = new TextAttributesKey[]{COMMA};
    private static final TextAttributesKey[] SEMICOLON_KEYS = new TextAttributesKey[]{SEMICOLON};
    private static final TextAttributesKey[] DOT_KEYS = new TextAttributesKey[]{DOT};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new AngelScriptLexerAdapter();
    }

    @Override
    public @NotNull TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(AngelScriptTokenTypes.KEYWORD)) {
            return KEYWORD_KEYS;
        } else if (tokenType.equals(AngelScriptTokenTypes.STRING)) {
            return STRING_KEYS;
        } else if (tokenType.equals(AngelScriptTokenTypes.NUMBER)) {
            return NUMBER_KEYS;
        } else if (tokenType.equals(AngelScriptTokenTypes.COMMENT)) {
            return COMMENT_KEYS;
        } else if (tokenType.equals(AngelScriptTokenTypes.IDENTIFIER)) {
            return IDENTIFIER_KEYS;
        } else if (tokenType.equals(AngelScriptTokenTypes.OPEN_PARENTHESIS) ||
                   tokenType.equals(AngelScriptTokenTypes.CLOSE_PARENTHESIS)) {
            return PARENTHESES_KEYS;
        } else if (tokenType.equals(AngelScriptTokenTypes.OPEN_BRACKET) ||
                   tokenType.equals(AngelScriptTokenTypes.CLOSE_BRACKET)) {
            return BRACKETS_KEYS;
        } else if (tokenType.equals(AngelScriptTokenTypes.START_STATEMENT_BLOCK) ||
                   tokenType.equals(AngelScriptTokenTypes.END_STATEMENT_BLOCK)) {
            return BRACES_KEYS;
        } else if (tokenType.equals(AngelScriptTokenTypes.LIST_SEPARATOR)) {
            return COMMA_KEYS;
        } else if (tokenType.equals(AngelScriptTokenTypes.END_STATEMENT)) {
            return SEMICOLON_KEYS;
        } else if (tokenType.equals(AngelScriptTokenTypes.DOT)) {
            return DOT_KEYS;
        } else if (isOperator(tokenType)) {
            return OPERATOR_KEYS;
        } else if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return EMPTY_KEYS;
        }
        return EMPTY_KEYS;
    }

    private boolean isOperator(IElementType tokenType) {
        return tokenType.equals(AngelScriptTokenTypes.PLUS) ||
               tokenType.equals(AngelScriptTokenTypes.MINUS) ||
               tokenType.equals(AngelScriptTokenTypes.STAR) ||
               tokenType.equals(AngelScriptTokenTypes.SLASH) ||
               tokenType.equals(AngelScriptTokenTypes.PERCENT) ||
               tokenType.equals(AngelScriptTokenTypes.ASSIGNMENT) ||
               tokenType.equals(AngelScriptTokenTypes.EQUAL) ||
               tokenType.equals(AngelScriptTokenTypes.NOT_EQUAL) ||
               tokenType.equals(AngelScriptTokenTypes.LESS_THAN) ||
               tokenType.equals(AngelScriptTokenTypes.GREATER_THAN) ||
               tokenType.equals(AngelScriptTokenTypes.LESS_THAN_OR_EQUAL) ||
               tokenType.equals(AngelScriptTokenTypes.GREATER_THAN_OR_EQUAL) ||
               tokenType.equals(AngelScriptTokenTypes.AND) ||
               tokenType.equals(AngelScriptTokenTypes.OR) ||
               tokenType.equals(AngelScriptTokenTypes.NOT) ||
               tokenType.equals(AngelScriptTokenTypes.AMP) ||
               tokenType.equals(AngelScriptTokenTypes.BIT_OR) ||
               tokenType.equals(AngelScriptTokenTypes.BIT_XOR) ||
               tokenType.equals(AngelScriptTokenTypes.BIT_NOT) ||
               tokenType.equals(AngelScriptTokenTypes.BIT_SHIFT_LEFT) ||
               tokenType.equals(AngelScriptTokenTypes.BIT_SHIFT_RIGHT) ||
               tokenType.equals(AngelScriptTokenTypes.BIT_SHIFT_RIGHT_ARITH) ||
               tokenType.equals(AngelScriptTokenTypes.ADD_ASSIGN) ||
               tokenType.equals(AngelScriptTokenTypes.SUB_ASSIGN) ||
               tokenType.equals(AngelScriptTokenTypes.MUL_ASSIGN) ||
               tokenType.equals(AngelScriptTokenTypes.DIV_ASSIGN) ||
               tokenType.equals(AngelScriptTokenTypes.MOD_ASSIGN) ||
               tokenType.equals(AngelScriptTokenTypes.INC) ||
               tokenType.equals(AngelScriptTokenTypes.DEC) ||
               tokenType.equals(AngelScriptTokenTypes.QUESTION) ||
               tokenType.equals(AngelScriptTokenTypes.COLON) ||
               tokenType.equals(AngelScriptTokenTypes.SCOPE);
    }
}
