package io.edbm.modules.NDM;

import io.sentry.Sentry;
import io.sentry.UserFeedback;
import io.sentry.protocol.SentryId;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class NotificationManager {
    
    /**
     * @version HIP 2
     */
    public static class DefaultMessages {
        
        /**
         *
         */
        public static String goalMetMessage = "You have met a shopping list goal.";
    
        /**
         *
         */
        public static String bookmarkAddedMessage = "A bookmark was added";
    
        /**
         *
         */
        public static String updatesAvailableMessage = "A new version of E.D.A is available. Download now?";
    
        /**
         *
         */
        public static String updatesDownloadedMessage = "A new update has been downloaded. Update now?";
    };
    
    
    /**
     *
     */
    private NotificationManager() {
    
    }
    
    /**
     *
     * @param primaryMessage
     * @param secondaryMessage
     */
    public static void addNotification(String primaryMessage, String secondaryMessage) {
    
    }
    
    public static void captureUserFeedback( SentryId eventId) {
        UserFeedback feedback = new UserFeedback( eventId );
        
        //TODO prompt user for email
        //TODO prompt user for error/issue
        //TODO prompt user for name
        
        //Sentry.captureUserFeedback(feedback);
    }
}
