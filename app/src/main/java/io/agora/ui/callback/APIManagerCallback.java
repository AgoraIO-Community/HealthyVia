package io.agora.ui.callback;

public interface APIManagerCallback {
    void onSuccess(Object object);

    void onFailure(String status);
}
