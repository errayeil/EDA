package io.edbm.Input.Keyboard;

import org.simplenativehooks.events.NativeKeyEvent;

public interface NativeKeyListener {
    
    void nativeKeyPressed( NativeKeyEvent event);
    
    void nativeKeyReleased ( NativeKeyEvent event);
}
