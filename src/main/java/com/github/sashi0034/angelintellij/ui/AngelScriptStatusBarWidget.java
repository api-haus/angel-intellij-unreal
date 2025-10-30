package com.github.sashi0034.angelintellij.ui;

import com.github.sashi0034.angelintellij.lsp.ConnectionStateService;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.openapi.wm.impl.status.EditorBasedWidget;
import com.intellij.util.Consumer;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class AngelScriptStatusBarWidget extends EditorBasedWidget implements StatusBarWidget.IconPresentation, Disposable {
    private volatile ConnectionStateService.State currentState = ConnectionStateService.State.DISCONNECTED;
    private StatusBar myBar;

    public AngelScriptStatusBarWidget(@NotNull Project project) {
        super(project);
        ConnectionStateService.getInstance().subscribe(state -> {
            currentState = state;
            if (myBar != null) myBar.updateWidget(ID());
        }, this);
    }

    @Override
    public @NonNls @NotNull String ID() {
        return "AngelScriptConnectionStatusWidget";
    }

    @Override
    public @Nullable WidgetPresentation getPresentation() {
        return this;
    }

    @Override
    public void install(@NotNull StatusBar statusBar) {
        super.install(statusBar);
        myBar = statusBar;
    }

    @Override
    public void dispose() {
        Disposer.dispose(this);
    }

    @Override
    public @Nullable Icon getIcon() {
        return switch (currentState) {
            case CONNECTED -> AllIcons.General.InspectionsOK;
            case CONNECTING -> AllIcons.General.BalloonInformation;
            case DISCONNECTED -> AllIcons.General.BalloonError;
        };
    }

    @Override
    public @Nullable @NonNls String getTooltipText() {
        return "AngelScript: " + currentState.name();
    }

    @Override
    public @Nullable Consumer<MouseEvent> getClickConsumer() {
        return mouseEvent -> {
            // TODO: optionally open LSP console or settings
        };
    }
}
