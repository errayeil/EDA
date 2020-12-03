package io.edbm.Input.Keyboard;

import org.simplenativehooks.NativeHookInitializer;
import org.simplenativehooks.NativeHookKeyEventSubscriber;
import org.simplenativehooks.NativeKeyHook;
import org.simplenativehooks.events.NativeKeyEvent;
import org.simplenativehooks.utilities.Function;

/**
 *
 */
public class NativeHook {
    
    private NativeKeyHook keyHook;
    
    /**
     *
     */
    private NativeKeyListener listener;
    
    /**
     *
     */
    private boolean initialized;
    
    /**
     *
     */
    public NativeHook() {
        NativeHookInitializer.of().start();
        keyHook = NativeKeyHook.of();
        
        initialized = true;
    }
   
    public void initialize() {
        NativeHookInitializer.of().start();
        keyHook = NativeKeyHook.of();
        
        initialized = true;
    }
    
    /**
     *
     */
    public void start() {
        keyHook.startListening();
    }
    
    /**
     *
     */
    public void stop() {
        keyHook.stopListening();
    }
    
    /**
     *
     */
    public void cleanup() {
        NativeHookInitializer.of().stop();
        initialized = false;
    }
    
    /**
     *
     * @param listener
     */
    public void setEventListener(NativeKeyListener listener) {
            this.listener = listener;
    }
    /**
     *
     */
    public void registerHookForKeyPressed() {
        keyHook.setKeyPressed(new Function<NativeKeyEvent, Boolean>() {
        
            @Override
            public Boolean apply(NativeKeyEvent d) {
                assert listener != null;
                listener.nativeKeyPressed( d );
                return true;
            }
        });
    }
    
    /**
     *
     */
    public void registerHookForKeyReleased() {
        keyHook.setKeyReleased( new Function<NativeKeyEvent , Boolean>() {
        
            @Override
            public Boolean apply(NativeKeyEvent event) {
                assert listener != null;
                listener.nativeKeyReleased( event );
                return true;
            }
        } );
    }
}
