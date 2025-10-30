package com.github.sashi0034.angelintellij.lsp;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.util.messages.Topic;

import java.util.concurrent.atomic.AtomicReference;

public class ConnectionStateService {
    public enum State { DISCONNECTED, CONNECTING, CONNECTED }

    public interface Listener {
        void onStateChanged(State state);
    }

    public static final Topic<Listener> TOPIC = Topic.create("AngelScriptConnectionState", Listener.class);

    private final AtomicReference<State> state = new AtomicReference<>(State.DISCONNECTED);

    public static ConnectionStateService getInstance() {
        return ApplicationManager.getApplication().getService(ConnectionStateService.class);
    }

    public State getState() {
        return state.get();
    }

    public void setState(State newState) {
        State old = state.getAndSet(newState);
        if (old != newState) {
            ApplicationManager.getApplication().getMessageBus().syncPublisher(TOPIC).onStateChanged(newState);
        }
    }

    public void subscribe(Listener listener, Disposable parent) {
        ApplicationManager.getApplication().getMessageBus().connect(parent).subscribe(TOPIC, listener);
    }
}
