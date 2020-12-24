package io.edbm.app;

import io.edbm.Utilities.Utils;
import io.edbm.modules.NDM.NotificationManager;
import io.sentry.Sentry;
import io.sentry.protocol.SentryId;
import io.sentry.protocol.User;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.Preferences;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDASetup {
    
    /**
     * Preferences.
     */
    private final Preferences prefs = Preferences.userNodeForPackage(EDASetup.class);
    
    /**
     * Download link to the version file for EDA to determine available updates.
     */
    private final String updateURLStr = "https://github.com/errayeil/EDA/blob/master/src/Version.txt";
    
    /**
     *Download link to the jar file containing the binaries for JInput functionality on Windows.
     */
    private final String windowsNativeJARURLStr = "https://search.maven.org/remotecontent?filepath=net/java/jinput/windows-plugin/2.0" +
                                                  ".9/windows-plugin-2.0.9-natives-windows.jar";
    
    /**
     * Download link to the jar file containing the binaries for JInput functionality on OSX.
     */
    private final String osxNativeJARURLStr = "https://search.maven.org/remotecontent?filepath=net/java/jinput/osx-plugin/2.0.9/osx-plugin-2.0.9" +
                                              "-natives-osx.jar";
    
    /**
     * Download link to the jar file containing the binaries for JInput functionality on Linux.
     */
    private final String linuxNativeJARURLStr = "https://search.maven.org/remotecontent?filepath=net/java/jinput/linux-plugin/2.0.9/linux-plugin-2" +
                                                ".0.9-natives-linux.jar";
    
    /**
     * Preferences key for if the user has granted permission to download application
     * assets.
     */
    private final String assetsKey = "permissionToDownloadAssets";
    
    /**
     * Preference key for if the user has granted permission to download application
     * updates.
     */
    private final String downloadUpdatesKey = "permissionToDownloadUpdates";
    
    /**
     * Preferences key for if the user has granted permission to install application
     * updates.
     */
    private final String updatesKey = "permissionToInstallUpdates";
    
    /**
     * Preferences key for if the user has granted permission to install the application
     * folder.
     */
    private final String installLocPermissionKey = "permissionToCreateInstallFolder";
    
    /**
     * Preferences key for if the application install location has been set.
     */
    private final String installLocKey = "installLocationSet";
    
    /**
     * The "AppData" folder found in the current users home folder on Windows.
     * File.separator is already added to improve readability.
     */
    private final String appDataFolder = File.separator + "AppData";
    
    /**
     * The "Local" folder found in the users AppData folder on Windows.
     * File.separator is already added to improve readability.
     */
    private final String localFolder = File.separator + "Local";
    
    /**
     * Eda install folder.
     * File.separator is already added to improve readability.
     */
    private final String edaFolder = File.separator + "EDA";
    
    /**
     * EDA log folder, contained within the EDA install folder.
     * File.separator is already added to improve readability.
     */
    private final String logFolder = File.separator + "Logs";
    
    /**
     * EDA assets folder, contained within the EDA install folder.
     * File.separator is already added to improve readability.
     */
    private final String assetsFolder = File.separator + "Assets";
    
    /**
     * EDA update folder, contained with the EDA install folder.
     * File.separator is already added to improve readability.
     */
    private final String updateFolder = File.separator + "Updates";
    
    /**
     * Bin folder for the java install path directory.
     */
    private final String javaPathBinFolder = File.separator + "bin";
    
    /**
     * The current system user home directory.
     */
    private final String userHomeFolder = Utils.getUserHome();
    
    /**
     * The default installation directory, system independent.
     */
    private String defaultInstallLocation;
    
    /**
     * A list of error messages.
     */
    private List<String> errorMessages;
    
    /**
     * A list of missing assets.
     */
    private List<String> missingAssets;
    
    /**
     * Constructor.
     * Automatically determines the underlying OS to assign default values, such as the defaultInstallLocation.
     * Automatically registers the Eurostile font with the GraphicsEnvironment.
     * Initiates Sentry.
     */
    public EDASetup() {
    
        Sentry.init( options -> {
            options.setDsn("https://f2fa9b61a4de48e680342299516550bb@o291527.ingest.sentry.io/5545580");
            options.setRelease( "EDA-vHIP2" );
            //TODO attachments?
        });
        
        
        Sentry.configureScope( scope -> {
            User user = new User();
            String userEmail = prefs.get( "userEmail", "N/A" );
            
            if (!userEmail.equals( "N/A" )) {
                user.setEmail( userEmail );
            }
            
            user.setUsername(  userEmail.substring( 0, userEmail.indexOf( "@" ) - 1 ));
        } );
        
        if (Utils.isWindows()) {
            defaultInstallLocation = userHomeFolder + appDataFolder + localFolder + edaFolder; //File.Separator included in strings above
            Sentry.addBreadcrumb( "DefaultInstallLocation for Windows Determined." );
        } else if (Utils.isMac()) {
            //TODO
            Sentry.addBreadcrumb( "DefaultInstallLocation for OSX Determined." );
        } else if (Utils.isLinux()) {
            //TODO
            Sentry.addBreadcrumb( "DefaultInstallLocation for Linux Determined." );
        }
        
        errorMessages = new ArrayList<>();
        missingAssets = new ArrayList<>();
        
        //Setup font
        try {
            boolean success = registerEurostileFont();
            
            if (success)
                Sentry.addBreadcrumb( "Eurostile font successfully registered." );
            else
                Sentry.addBreadcrumb( "Eurostile font was not successfully registered." );
            
        } catch ( URISyntaxException | IOException | FontFormatException e ) {
            e.printStackTrace();
            SentryId id = Sentry.captureException( e );
            NotificationManager.captureUserFeedback( id );
        }
    }
    
    /**
     * @version HIP 2
     * @since HIP 2
     */
    public static class ErrorMessages {
    
        /**
         * Message used when required assets are missing.
         */
        public static String missingAssetMessage = "Required Application assets are missing.";
    
        /**
         * Message used when the EDA installation location preferences value has not been set.
         */
        public static String installLocationNotSet = "Application install location has not been set.";
    
        /**
         * Message used when the java installation path is null or unavailable.
         */
        public static String javaInstallPathNull = "The Java installation path is null or unavailable.";
    
        /**
         * Message used when the bin folder within the java installation path could not be found.
         */
        public static String javaBinFolderNotFound = "The Java bin folder in the installation directory could not be found.";
    
        /**
         * Message used when the files contained in the java/bin directory could not be obtained.
         */
        public static String javaBinFilesNotObtained = "The files within the java bin folder could not be obtained.";
    
        /**
         * Message used when the user has not granted permission to download assets.
         */
        public static String noPermissionForAssetDownload = "Permissions to download assets has not been granted.";
    
        /**
         * Message used when the user has not granted permission to download the update.
         */
        public static String noPermissionForUpdateDownload = "Permissions to download updates has not been granted.";
    
        /**
         * Message used when the EDA app directory was not created.
         */
        public static String installDirectoryNotCreated = "EDA install directory could not be created.";
    
        /**
         * Message used when the underlying OS could not be determined, thus making us unable to generate
         * the required assets list.
         */
        public static String requiredAssetsNotDetermined = "The required assets could not be determined.";
    
        /**
         * Private Constructor.
         */
        private ErrorMessages () {
        
        }
    }
    
    /**
     * Clears all preferences and deletes directories for testing purposes.
     */
    public void clearEverythingForTesting() {
    
    }
    
    /**
     * Returns a list of error messages that have occured.
     * @return
     */
    public List<String> getErrorMessages() {
        return errorMessages;
    }
    
    /**
     * Downloads required assets to the install directory.
     *
     * @return True if assets downloaded successfully. False if it was not successful.
     * Check errorMessage for any issues that occurred if this returned false.
     */
    public boolean downloadAssets() {
        if (!hasPermissionToDownloadAssets()) {
            errorMessages.add( ErrorMessages.noPermissionForAssetDownload );
            Sentry.addBreadcrumb( "Permission for downloading assets not granted." );
            return false;
        }
        
        if (hasInstallLocationSet()) {
            String installLocation = prefs.get( installLocKey, "N/A" );
            if (installLocation.equals( "N/A" )) {
                errorMessages.add( ErrorMessages.installLocationNotSet );
                Sentry.addBreadcrumb( "Application installation folder not set." );
                return false;
            }
            
        }
        
        return false;
    }
    
    /**
     * Downloads available updates. The user must consent to the download.
     *
     * @return True if updates successfully download. False if it was not successful.
     * Check errorMessages for any issues that occurred if this returned false.
     */
    public boolean downloadUpdates() {
        if (!hasPermissionToDownloadUpdate()) {
            errorMessages.add( ErrorMessages.noPermissionForUpdateDownload );
            Sentry.addBreadcrumb( "Permission for downloading updates not granted." );
            return false;
        }
    
        if (hasInstallLocationSet()) {
            String installLocation = prefs.get( installLocKey, "N/A" );
            if (installLocation.equals( "N/A" )) {
                errorMessages.add( ErrorMessages.installLocationNotSet );
                Sentry.addBreadcrumb( "Application installation folder not set." );
                return false;
            }
            //TODO
        }
        
        
        return false;
    }
    
    /**
     * Registers the specified installation directory into the preferences tree.
     * If a directory has been entered previously, it will be overridden.
     * @param installLocation The directory EDA should be installed too.
     * @return True if successfully set, false otherwise.
     */
    public boolean setInstallLocation(String installLocation) {
        prefs.put( installLocKey, installLocation );
        
        return hasInstallLocationSet();
    }
    
    /**
     * Creates EDA's installation directories.
     * @return Returns true if successful, false if not. Check errorMessages to see what
     * occurred if false.
     */
    public boolean createInstallLocationDirectories() {
        if (!hasInstallLocationSet()) {
            errorMessages.add( ErrorMessages.installLocationNotSet );
            Sentry.addBreadcrumb( "Application installation folder not set." );
            return false;
        }
        
        String installLocation = prefs.get( installLocKey, defaultInstallLocation );
        File installDir = new File(installLocation);
        
        if (!installDir.exists()) {
            boolean success = installDir.mkdir();
            
            if (success) {
                File logDir = new File(installLocation + logFolder);
                File updateDir = new File(installLocation + updateFolder);
                File assetDir = new File(installLocation + assetsFolder);
                
                if (!logDir.exists()) {
                    boolean logSuccess = logDir.mkdir();
                }
                
                if (!updateDir.exists()) {
                    boolean updateSuccess = updateDir.mkdir();
                }
                
                if (!assetDir.exists()) {
                    boolean assetSuccess = assetDir.mkdir();
                }
                
                return true;
            } else {
                errorMessages.add( ErrorMessages.installDirectoryNotCreated );
                Sentry.addBreadcrumb( "Could not create installation directory." );
            }
        }
        
        return false;
    }
    
    /**
     *
     * @return True if the user granted permission to download application assets.
     */
    public boolean hasPermissionToDownloadAssets() {
        return prefs.getBoolean( assetsKey, false );
    }
    
    /**
     *
     * @return
     */
    public boolean hasPermissionToDownloadUpdate() {
        return prefs.getBoolean( downloadUpdatesKey, false);
    }
    
    /**
     *
     * @return True if the user has granted permission to update.
     */
    public boolean hasPermissionToUpdate() {
        return prefs.getBoolean( updatesKey, false );
    }
    
    /**
     *
     * @return True if the user has granted permission to create the application install folder.
     */
    public boolean hasPermissionToCreateInstallLoc() {
       return prefs.getBoolean( installLocPermissionKey, false );
    }
    
    /**
     *
     * @return True if the application install location has been set.
     */
    public boolean hasInstallLocationSet() {
        String installLocation = prefs.get( installLocKey, "N/A" );
    
        return ! installLocation.equals( "N/A" );
    }
    
    /**
     * Determines if the EDA application install directory has been created.
     * @return True if the applications install location has been created.
     */
    public boolean hasInstallLocationCreated() {
        String installLocation = prefs.get( "installLocKey", "N/A" );
        
        if (installLocation.equals( "N/A" )) {
            errorMessages.add( ErrorMessages.installLocationNotSet );
            Sentry.addBreadcrumb( "Application installation folder not set." );
            return false;
        }
        
        File edaFolder = new File(installLocation);
    
        return edaFolder.exists() && edaFolder.isDirectory();
    }
    
    /**
     * Checks if assets have been downloaded.
     * @return True if assets have been downloaded, false if something during the check failed.
     * Calling getErrorMessage() will return a String for what failed.
     */
    public boolean hasAssetsDownloaded() {
        String installLocation = prefs.get( installLocKey, "N/A" );
        String javaInstallPath = Utils.getJavaPath();
        
        if (installLocation.equals( "N/A" )) {
            errorMessages.add( ErrorMessages.installLocationNotSet );
            Sentry.addBreadcrumb( "Application installation folder not set." );
            return false;
        }
        
        if (javaInstallPath == null) {
            errorMessages.add( ErrorMessages.javaInstallPathNull );
            Sentry.addBreadcrumb( "Java installation path is null or unavailable." );
            return false;
        }
        
        File binDirectory = new File(javaInstallPath + javaPathBinFolder);
        
        if (!binDirectory.exists() || !binDirectory.isDirectory()) {
            errorMessages.add( ErrorMessages.javaBinFolderNotFound);
            Sentry.addBreadcrumb( "Java bin folder could not be found." );
            return false;
        }
        
        List<String> requiredAssets = getAssetNamesForPlatform();
        
        if (requiredAssets.size() == 0) {
            errorMessages.add( ErrorMessages.requiredAssetsNotDetermined );
            Sentry.addBreadcrumb( "Required assets for application functionality could not be determined. OS not detected." );
            return false;
        }
        
        List<String> foundAssets = new ArrayList<>();
        File[] binFiles = binDirectory.listFiles();
        
        if (binFiles == null) {
            errorMessages.add( ErrorMessages.javaBinFilesNotObtained );
            Sentry.addBreadcrumb( "Bin files array could not be obtained. Null value." );
            return false;
        }
        
        for (String s : requiredAssets) {
            for (File f : binFiles) {
                if (f.getName().equals( s )) {
                    foundAssets.add( s );
                }
            }
        }
        
        if (foundAssets.size() != requiredAssets.size()) {
            for (String s : requiredAssets) {
                if (!foundAssets.contains( s )) {
                    missingAssets.add( s );
                }
            }
            Sentry.addBreadcrumb( "Missing assets determined." );
            errorMessages.add( ErrorMessages.missingAssetMessage );
        } else {
            return true;
        }
        
        return false;
    }
    
    /**
     *
     * @return Returns true of updates are available, false otherwise.
     */
    public boolean checkForUpdate() {
        URL updateURL = null;
        Sentry.addBreadcrumb( "Getting update url." );
        try {
            updateURL = new URL( updateURLStr);
        } catch ( MalformedURLException e ) {
            e.printStackTrace();
            Sentry.captureException( e );
            return false;
        }
    
        BufferedReader urlReader = null;
        Sentry.addBreadcrumb( "Creating BufferedReader for url." );
        try {
            urlReader = new BufferedReader( new InputStreamReader( updateURL.openStream(), StandardCharsets.UTF_8 ) );
        } catch ( IOException e ) {
            e.printStackTrace();
            SentryId id = Sentry.captureException( e );
            NotificationManager.captureUserFeedback( id );
            return false;
        }
        
        Sentry.addBreadcrumb( "Creating BufferedReader for file." );
        BufferedReader fileReader = new BufferedReader( new InputStreamReader( EDASetup.class.getResourceAsStream( "/Version" ) ));
    
        String urlRead = null;
        String fileRead = null;
        
        Sentry.addBreadcrumb( "Reading lines from URL file and local file." );
        try {
            urlRead = urlReader.readLine();
            fileRead = fileReader.readLine();
        } catch ( IOException e ) {
            e.printStackTrace();
            SentryId id = Sentry.captureException( e );
            NotificationManager.captureUserFeedback( id );
            return false;
        }
    
        Sentry.addBreadcrumb( "Attempting to close streams." );
        try {
            urlReader.close();
            fileReader.close();
        } catch ( IOException e ) {
            e.printStackTrace();
            SentryId id = Sentry.captureException( e );
            NotificationManager.captureUserFeedback( id );
            return false;
        }
        
        Sentry.addBreadcrumb( "Determining if updates are available." );
        if (!urlRead.equals( fileRead )) {
            Sentry.addBreadcrumb( "Updates available." );
            return true;
        }
        
        Sentry.addBreadcrumb( "No updates available." );
        return false;
    }
    
    /**
     * Registers Eurostile font for use with the application.
     * @return True of the font was successfully registered.
     */
    public boolean registerEurostileFont() throws URISyntaxException, IOException, FontFormatException {
        Sentry.addBreadcrumb( "Registering Eurostile font." );
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        return ge.registerFont( Font.createFont( Font.TRUETYPE_FONT,
                                                new File( Utils.class.getResource( "/io/edbm/resources/eurostile.TTF" ).toURI()) ));
    }
    
    /**
     *
     * @return
     */
    private List<String> getAssetNamesForPlatform () {
        Sentry.addBreadcrumb( "Retrieving required asset names for platform." );
        if (Utils.isWindows()) {
            return Arrays.asList( "eurostile.TTF", "jinput-dx8_64.dll", "jinput-raw_64.dll", "jinput-wintab.dll" );
        } else if (Utils.isMac()) {
            return Arrays.asList( "eurostile.TTF", "libjinput-osx.jnilib" );
        } else if ( Utils.isLinux() ) {
            return Arrays.asList("eurostile.TTF", "libjinput-linux64.so");
        } else {
            Sentry.captureMessage( "The underlying OS could not be determined for the required assets." );
            errorMessages.add( ErrorMessages.requiredAssetsNotDetermined );
           return new ArrayList<>(0);
        }
    }
}