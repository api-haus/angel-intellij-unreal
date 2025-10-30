package com.github.sashi0034.angelintellij.ui;

import com.github.sashi0034.angelintellij.lsp.ConnectionStateService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.openapi.wm.StatusBarWidgetFactory;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

public class AngelScriptStatusBarWidgetFactory implements StatusBarWidgetFactory {
    @Override
    public @NotNull String getId() {
        return "AngelScriptConnectionStatus";
    }

    @Override
    public @Nls @NotNull String getDisplayName() {
        return "AngelScript LSP/Unreal Status";
    }

    @Override
    public boolean isAvailable(@NotNull Project project) {
        return true;
    }

    @Override
    public @NotNull StatusBarWidget createWidget(@NotNull Project project) {
        return new AngelScriptStatusBarWidget(project);
    }

    @Override
    public boolean isConfigurable() {
        return false;
    }
}
