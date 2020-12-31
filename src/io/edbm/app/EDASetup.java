package io.edbm.app;

import io.edbm.UI.EDAOptionPane;
import io.edbm.Utilities.Utils;
import io.edbm.modules.NDM.NotificationManager;
import io.sentry.Sentry;
import io.sentry.protocol.SentryId;
import io.sentry.protocol.User;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDASetup {
    
    /**
     * Preferences.
     */
    private final Preferences prefs = Preferences.userNodeForPackage( EDASetup.class );
    
    /**
     * Download link to the version file for EDA to determine available updates.
     */
    private final String updateURLStr = "https://github.com/errayeil/EDA/blob/master/src/Version.txt";
    
    /**
     * Download link to the jar file containing the binaries for JInput functionality on Windows.
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
    private final String downloadAssetsKey = "permissionToDownloadAssets";
    
    /**
     * Preference key for if the user has granted permission to download application
     * updates.
     */
    private final String downloadUpdatesKey = "permissionToDownloadUpdates";
    
    /**
     * Preferences key for if the user has granted permission to install application
     * updates.
     */
    private final String installUpdatesKey = "permissionToInstallUpdates";
    
    /**
     * Preferences key for if the user has granted permission to create the application
     * folder.
     */
    private final String createAppFolderKey = "permissionToCreateInstallFolder";
    
    /**
     * Preferences key to retrieve the EDA installation directory.
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
    private final String logFolder = File.separator + "Logs" + File.separator;
    
    /**
     * EDA assets folder, contained within the EDA install folder.
     * File.separator is already added to improve readability.
     */
    private final String assetsFolder = File.separator + "Assets" + File.separator;
    
    /**
     * EDA update folder, contained with the EDA install folder.
     * File.separator is already added to improve readability.
     */
    private final String updateFolder = File.separator + "Updates" + File.separator;
    
    /**
     * EDA plugins folder, contained in the EDA installation folder.
     * File.separator is already added to improve readability.
     */
    private final String pluginsFolder = File.separator + "Plugins" + File.separator;
    
    /**
     * Bin folder for the java install path directory.
     */
    private final String javaPathBinFolder = File.separator + "bin" + File.separator;
    
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
    public EDASetup () {
        
        Sentry.init( options->{
            options.setDsn( "https://f2fa9b61a4de48e680342299516550bb@o291527.ingest.sentry.io/5545580" );
            options.setRelease( "EDA-vHIP2" );
            //TODO attachments?
        } );
        
        if ( Utils.isWindows() ) {
            defaultInstallLocation = userHomeFolder + appDataFolder + localFolder + edaFolder; //File.Separator included in strings above
            Sentry.addBreadcrumb( "DefaultInstallLocation for Windows Determined." );
        } else if ( Utils.isMac() ) {
            //TODO
            Sentry.addBreadcrumb( "DefaultInstallLocation for OSX Determined." );
        } else if ( Utils.isLinux() ) {
            //TODO
            Sentry.addBreadcrumb( "DefaultInstallLocation for Linux Determined." );
        }
        
        errorMessages = new ArrayList<>();
        missingAssets = new ArrayList<>();
        
        //Setup font
        Sentry.addBreadcrumb( "Attempting to Register Eurostile font." );
        boolean success = registerEurostileFont();
    
        if ( success ) {
            Sentry.addBreadcrumb( "Eurostile font successfully registered." );
        } else {
            Sentry.addBreadcrumb( "Eurostile font was not successfully registered." );
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
         *
         */
        public static String nativeJarDoesNotExist = "The native jar file could not be found.";
        
        /**
         *
         */
        public static String couldNotOpenJarFile = "The native jar file could not be opened.";
        
        /**
         *
         */
        public static String installationDirectoryAlreadyExists = "The EDA installation directory already exists.";
        
        /**
         * Private Constructor.
         */
        private ErrorMessages () {
        
        }
    }
    
    /**
     * Configures the scope of Sentry and prompts the user for their email if it cannot be found.
     */
    private void configureSentryScope() {
        Sentry.configureScope( scope->{
            User user = new User();
            String userEmail = prefs.get( "userEmail" , "N/A" );
            boolean specified = prefs.getBoolean( "specifiedEmail", false );
            
            if (!specified) {
                if ( userEmail.equals( "N/A" ) ) {
                    String line = "\n";
                    String str1 = "EDA utilizes an internal issue tracker that automatically reports issues.";
                    String str2 = "This feature captures no private or personal data and is completely anonymous.";
                    String str3 = "If you so choose, you can enter your email if you would like us to contact you about issues, ";
                    String str4 = "more specifically, issues that are proving to be more troublesome to debug and fix.";
        
                    //TODO Create custom UI for JOptionPane
                    String input = JOptionPane.showInputDialog( null , str1 + line + str2 + line + str3 + line + str4 );
        
                    if ( ! input.isBlank() || ! input.isEmpty() && input.contains( "@" ) ) {
                        user.setEmail( input );
                        user.setUsername( userEmail.substring( 0 , userEmail.indexOf( "@" ) - 1 ) );
                    }
                    prefs.putBoolean( "specifiedEmail", true );
                }
            }
        } );
    }
    
    /**
     *
     */
    public void startFirstTimeSetup() {
        System.out.println( "Starting" );
        boolean ready = false;
        
        String line = "\n";
        String str1 = "Welcome to your Elite: Dangerous Assistant (E.D.A.)";
        String str2 = "We have detected this is your first time initiating EDA and we'll need to run through a setup process.";
        String str3 = "During this setup, you will receive various popups asking for your permission to do certain things or for certain " +
                      "information.";
        String str4 = "At any point you deny permissions, the application will close. If you have any questions you may email me at: PLACEHOLDER";
        String str5 = "Thank you for trying EDA and I hope it enriches your Elite:Dangerous experience.";
        int option = JOptionPane.showOptionDialog( null, str1 + line + str2 + line + str3 + line + str4 + line + str5,
                                                   "Welcome to EDA",
                                                   JOptionPane.YES_NO_OPTION,
                                                   JOptionPane.INFORMATION_MESSAGE,
                                                   null, null, null );
        
        if (option == JOptionPane.YES_OPTION) {
            configureSentryScope();
            
            boolean installLocSet = hasInstallLocationSet();
            
            if (!installLocSet) {
                String nstr1 = "EDA installs required app files within a specified or default directory.";
                String nstr2 = "If a directory is not specified, a default will be chosen.";
                String nstr3 = "Would you like to continue?";
                
                int option2 = JOptionPane.showOptionDialog( null, nstr1 + line + nstr2 + line + nstr3, "Install Directory",
                                                           JOptionPane.YES_NO_OPTION,
                                                           JOptionPane.QUESTION_MESSAGE, null, null, null );
                if (option2 == JOptionPane.YES_OPTION) {
                    JFileChooser chooser = new JFileChooser();
                    
                    chooser.setFileFilter( new FileFilter() {
                        @Override
                        public boolean accept ( File f ) {
                            
                            if (f.isDirectory()) {
                                return true;
                            }
                            
                            return false;
                        }
    
                        @Override
                        public String getDescription () {
                            return null;
                        }
                    } );
                    
                    int chooserOption = chooser.showOpenDialog( null );
                    
                    if (chooserOption == JFileChooser.APPROVE_OPTION) {
                        File selected = chooser.getSelectedFile();
                        setInstallLocation( selected.getAbsolutePath() );
                    } else {
                        setInstallLocation( defaultInstallLocation );
                    }
                    
                } else {
                    System.exit( 0 );
                }
            }
            
            
            if (!hasInstallLocationCreated()) {
                String n1str1 = "The EDA installation directory will now be created.";
                String n1str2 = "We will also download required application files and extract them.";
                String n1str3 = "Would you like to continue?";
                
                int option3 = JOptionPane.showOptionDialog( null, n1str1 + line + n1str2 + line + n1str3,
                                                            "EDA files", JOptionPane.YES_NO_OPTION,
                                                            JOptionPane.QUESTION_MESSAGE, null, null, null);
                
                if (option3 == JOptionPane.YES_OPTION) {
                    boolean created = createInstallLocationDirectories();
                    
                    if (created) {
                        JDialog dDialog = EDAOptionPane.showProgressDialog( null, "Downloading" );
                        boolean success = downloadAssets();
                        if (success) {
                            Utils.invokeSetVisibleDialog( dDialog, false );
                            if (hasDownloadedAssetsInFolder()) {
                                JDialog dialog = EDAOptionPane.showProgressDialog( null, "Downloading" );
                                boolean extracted = extractAssets();
                                Utils.invokeSetVisibleDialog( dialog, false );
                                if (!extracted) {
                                    JOptionPane.showMessageDialog( null, "We had an issue extracting the assets. EDA will now Exit." );
                                }
                            } else {
                                JOptionPane.showMessageDialog( null, "The downloaded assets could not be located. EDA will now exit." );
                            }
                        } else {
                            JOptionPane.showMessageDialog( null, "We had an issue with downloading the assets. EDA will now Exit." );
                        }
                    } else {
                        JOptionPane.showMessageDialog( null, "The EDA install directory has not been created. Cannot proceed." );
                    }
                    
                    ready = hasInstallLocationSet() && hasInstallLocationCreated() && hasAssetsExtracted();
                } else {
                    System.exit( 0 );
                }
            }
            
        } else {
            System.exit( 0 );
        }
        
        if (ready) {
            String n1str1 = "EDA setup is now complete. The app will now launch.";
            String n1str2 = "Thank you for trying out EDA, I hope you enjoy her.";
            JOptionPane.showMessageDialog( null, n1str1 + line + n1str2, "EDA setup complete", JOptionPane.INFORMATION_MESSAGE );
        } else {
            String n1str1 = "It appears the setup process did not complete successfully.";
            String n1str2 = "I apologize for the inconvenience. Errors that have occured in the background have been reported to me.";
            JOptionPane.showMessageDialog( null, n1str1 + line + n1str2, "Error", JOptionPane.ERROR_MESSAGE );
        }
    }
    
    /**
     * Clears all preferences and deletes all EDA related files to clean up
     * for testing.
     */
    public void clearEverythingForTesting () {
        try {
            FileUtils.deleteDirectory( new File( defaultInstallLocation ) );
        } catch ( IOException e ) {
            e.printStackTrace();
            SentryId id = Sentry.captureException( e );
            NotificationManager.captureUserFeedback( id );
        }
        
        if ( hasAssetsExtracted() ) {
            String javaPath = Utils.getJavaPath();
            System.out.println(javaPath);
            File binDirectory = new File( Utils.getJavaPath() + javaPathBinFolder );
            
            if ( binDirectory.exists() ) {
                List<String> assetNames = getAssetNamesForPlatform();
                File[] binFiles = binDirectory.listFiles();
                
                if ( binFiles != null ) {
                    for ( File f : binFiles ) {
                        String name = f.getName();
            
                        for ( String as : assetNames ) {
                            if ( as.equals( name ) ) {
                                boolean success = f.delete();
                                if ( ! success ) {
                                    Sentry.captureMessage( "File could not be deleted: " + f.getAbsolutePath() );
                                }
                            }
                        }
                    }
                }
            }
        }
        
        try {
            prefs.clear();
        } catch ( BackingStoreException e ) {
            e.printStackTrace();
            SentryId id = Sentry.captureException( e );
            NotificationManager.captureUserFeedback( id );
        }
    }
    
    /**
     *
     */
    public void putPermissionsForTesting () {
        prefs.putBoolean( downloadUpdatesKey , true );
        prefs.putBoolean( downloadAssetsKey , true );
        prefs.putBoolean( installUpdatesKey , true );
        prefs.putBoolean( createAppFolderKey , true );
    }
    
    /**
     *
     */
    public String getDefaultInstallLocation () {
        return defaultInstallLocation;
    }
    
    /**
     *
     */
    public String getInstallLocation () {
        return prefs.get( installLocKey , "N/A" );
    }
    
    /**
     *
     */
    public String getJavaPathBinFolder () {
        return javaPathBinFolder;
    }
    
    /**
     * Returns a list of error messages that have occured.
     */
    public List<String> getErrorMessages () {
        return errorMessages;
    }
    
    /**
     * Downloads required assets to the install directory.
     *
     * @return True if assets downloaded successfully. False if it was not successful.
     * Check errorMessage for any issues that occurred if this returned false.
     */
    public boolean downloadAssets () {
        
        if ( ! hasInstallLocationSet() ) {
            errorMessages.add( ErrorMessages.installLocationNotSet );
            Sentry.addBreadcrumb( ErrorMessages.installLocationNotSet );
            return false;
        }
        
        if ( ! hasPermissionToDownloadAssets() ) {
            errorMessages.add( ErrorMessages.noPermissionForAssetDownload );
            Sentry.addBreadcrumb( ErrorMessages.noPermissionForAssetDownload );
            return false;
        }
        
        URL nativeJARURL = null;
        
        if ( Utils.isWindows() ) {
            try {
                nativeJARURL = new URL( windowsNativeJARURLStr );
            } catch ( MalformedURLException e ) {
                e.printStackTrace();
                SentryId id = Sentry.captureException( e );
                NotificationManager.captureUserFeedback( id );
            }
        } else if ( Utils.isMac() ) {
            try {
                nativeJARURL = new URL( osxNativeJARURLStr );
            } catch ( MalformedURLException e ) {
                e.printStackTrace();
                SentryId id = Sentry.captureException( e );
                NotificationManager.captureUserFeedback( id );
            }
        } else if ( Utils.isLinux() ) {
            try {
                nativeJARURL = new URL( linuxNativeJARURLStr );
            } catch ( MalformedURLException e ) {
                e.printStackTrace();
                SentryId id = Sentry.captureException( e );
                NotificationManager.captureUserFeedback( id );
            }
        } else {
            return false;
        }
        
        String installLocation = prefs.get( installLocKey , "N/A" );
        
        File downloadDirectory = new File( installLocation + assetsFolder );
        File downloadedJarFile = new File( downloadDirectory.getAbsolutePath() + File.separator + "jinput-native.jar" );
        
        if ( ! downloadDirectory.exists() ) {
            return false;
        }
        
        if ( ! downloadedJarFile.exists() ) {
            boolean success = false;
            try {
                success = downloadedJarFile.createNewFile();
            } catch ( IOException e ) {
                e.printStackTrace();
                SentryId id = Sentry.captureException( e );
                NotificationManager.captureUserFeedback( id );
            }
            
            if ( ! success ) {
                return false;
            }
        }
    
        if ( nativeJARURL == null ) {
            return false;
        }
        
        InputStream in = null;
        try {
            in = nativeJARURL.openStream();
        } catch ( IOException e ) {
            e.printStackTrace();
            SentryId id = Sentry.captureException( e );
            NotificationManager.captureUserFeedback( id );
        }
    
        if ( in == null ) {
            return false;
        }
        
        try {
            Files.copy( in , downloadedJarFile.toPath() , StandardCopyOption.REPLACE_EXISTING );
        } catch ( IOException e ) {
            e.printStackTrace();
            SentryId id = Sentry.captureException( e );
            NotificationManager.captureUserFeedback( id );
        }
        
        try {
            in.close();
        } catch ( IOException e ) {
            e.printStackTrace();
            SentryId id = Sentry.captureException( e );
            NotificationManager.captureUserFeedback( id );
        }
        
        return true;
    }
    
    /**
     * Downloads available updates. The user must consent to the download.
     * TODO
     * @return True if updates successfully download. False if it was not successful.
     * Check errorMessages for any issues that occurred if this returned false.
     */
    public boolean downloadUpdates () {
        if ( ! hasPermissionToDownloadUpdate() ) {
            errorMessages.add( ErrorMessages.noPermissionForUpdateDownload );
            Sentry.addBreadcrumb( ErrorMessages.noPermissionForUpdateDownload );
            return false;
        }
        
        if ( ! hasInstallLocationSet() ) {
            errorMessages.add( ErrorMessages.installLocationNotSet );
            Sentry.addBreadcrumb( ErrorMessages.installLocationNotSet );
            return false;
        }
        
        URL releaseURL;
        
        return false;
    }
    
    /**
     * Extracts downloaded assets to the required locations, such as the JInput binaries extracted
     * to the java installation path.
     */
    public boolean extractAssets () {
        Sentry.addBreadcrumb( "Checking if assets exist." );
        if ( ! hasDownloadedAssetsInFolder() ) {
            return false;
        }
        
        String installLocation = prefs.get( installLocKey , "N/A" );
        File assetDirectory = new File( installLocation + assetsFolder );
        File nativeJAR = new File( assetDirectory.getAbsolutePath() + File.separator + "jinput-native.jar" );
        File binDirectory = new File( Utils.getJavaPath() + javaPathBinFolder + File.separator );
        
        Sentry.addBreadcrumb( "Checking if native jar exists." );
        if ( ! nativeJAR.exists() ) {
            errorMessages.add( ErrorMessages.nativeJarDoesNotExist );
            Sentry.addBreadcrumb( ErrorMessages.nativeJarDoesNotExist );
            return false;
        }
        
        Sentry.addBreadcrumb( "Checking if java bin folder exists." );
        if ( ! binDirectory.exists() ) {
            errorMessages.add( ErrorMessages.javaBinFolderNotFound );
            Sentry.addBreadcrumb( ErrorMessages.javaBinFolderNotFound );
            return false;
        }
        
        Sentry.addBreadcrumb( "Attempting to open jar file." );
        try ( ZipFile nativeArchive = new ZipFile( nativeJAR ) ) {
            Sentry.addBreadcrumb( "Gathering jar file entries." );
            List<? extends ZipEntry> entries = nativeArchive.stream().collect( Collectors.toList() );
            
            for ( ZipEntry ze : entries ) {
                
                if ( ! ze.isDirectory() ) {
                    String name = ze.getName();
                    
                    if ( name.equals( "jinput-dx8_64.dll" ) ||
                         name.equals( "jinput-raw_64.dll" ) ||
                         name.equals( "libjinput-linux64.so" ) ||
                         name.equals( "libjinput-osx.jnilib" ) ) {
                        Sentry.addBreadcrumb( "Extracting entry: " + name );
                        File newFile = new File( binDirectory.getAbsolutePath() + File.separator + name );
                        
                        Files.copy( nativeArchive.getInputStream( ze ) , newFile.toPath() );
                        Sentry.addBreadcrumb( "Entry extracted: " + name );
                    }
                }
            }
            
        } catch ( IOException e ) {
            Sentry.addBreadcrumb( ErrorMessages.couldNotOpenJarFile );
            e.printStackTrace();
            SentryId id = Sentry.captureException( e );
            NotificationManager.captureUserFeedback( id );
            return false;
        }
        
        return true;
    }
    
    /**
     * Registers the specified installation directory into the preferences tree.
     * If a directory has been entered previously, it will be overridden.
     *
     * @param installLocation The directory EDA should be installed too.
     * @return True if successfully set, false otherwise.
     */
    public boolean setInstallLocation ( String installLocation ) {
        prefs.put( installLocKey , installLocation );
        
        return hasInstallLocationSet();
    }
    
    /**
     * Creates EDA's installation directories.
     *
     * @return Returns true if successful, false if not. Check errorMessages to see what
     * occurred if false.
     */
    public boolean createInstallLocationDirectories () {
        if ( ! hasInstallLocationSet() ) {
            errorMessages.add( ErrorMessages.installLocationNotSet );
            Sentry.addBreadcrumb( "Application installation folder not set." );
            return false;
        }
        
        String installLocation = prefs.get( installLocKey , defaultInstallLocation );
        File installDir = new File( installLocation );
        
        if ( ! installDir.exists() ) {
            Sentry.addBreadcrumb( "Creating installation directory." );
            boolean success = installDir.mkdir();
            
            if ( success ) {
                Sentry.addBreadcrumb( "Installation directory created" );
                Sentry.addBreadcrumb( "Creating sub directories." );
                File logDir = new File( installLocation + logFolder );
                File updateDir = new File( installLocation + updateFolder );
                File assetDir = new File( installLocation + assetsFolder );
                File pluginsDir = new File(installLocation + pluginsFolder);
                
                if ( ! logDir.exists() ) {
                    boolean logSuccess = logDir.mkdir();
                    if ( logSuccess ) {
                        Sentry.addBreadcrumb( "Log folder created successfully." );
                    }
                }
                
                if ( ! updateDir.exists() ) {
                    boolean updateSuccess = updateDir.mkdir();
                    if ( updateSuccess ) {
                        Sentry.addBreadcrumb( "Update folder created successfully." );
                    }
                }
                
                if ( ! assetDir.exists() ) {
                    boolean assetSuccess = assetDir.mkdir();
                    if ( assetSuccess ) {
                        Sentry.addBreadcrumb( "Asset folder created successfully." );
                    }
                }
                
                if (pluginsDir.exists()) {
                    boolean pluginsSuccess = pluginsDir.mkdir();
                    if (pluginsSuccess) {
                        Sentry.addBreadcrumb( "Plugins folder created successfully." );
                    }
                }
            } else {
                errorMessages.add( ErrorMessages.installDirectoryNotCreated );
                Sentry.addBreadcrumb( "Could not create installation directory." );
                return false;
            }
        } else {
            errorMessages.add( ErrorMessages.installationDirectoryAlreadyExists );
            Sentry.addBreadcrumb( "Installation directory already exists." );
            return false;
        }
        
        return true;
    }
    
    /**
     * @return True if the user granted permission to download application assets.
     */
    public boolean hasPermissionToDownloadAssets () {
        return prefs.getBoolean( downloadAssetsKey , false );
    }
    
    /**
     *
     */
    public boolean hasPermissionToDownloadUpdate () {
        return prefs.getBoolean( downloadUpdatesKey , false );
    }
    
    /**
     * @return True if the user has granted permission to update.
     */
    public boolean hasPermissionToUpdate () {
        return prefs.getBoolean( installUpdatesKey , false );
    }
    
    /**
     * @return True if the user has granted permission to create the application install folder.
     */
    public boolean hasPermissionToCreateInstallLoc () {
        return prefs.getBoolean( createAppFolderKey , false );
    }
    
    /**
     * @return True if the application install location has been set.
     */
    public boolean hasInstallLocationSet () {
        String installLocation = prefs.get( installLocKey , "N/A" );
        
        return ! installLocation.equals( "N/A" );
    }
    
    /**
     * Determines if the EDA application install directory has been created.
     *
     * @return True if the applications install location has been created.
     */
    public boolean hasInstallLocationCreated () {
        if ( ! hasInstallLocationSet() ) {
            errorMessages.add( ErrorMessages.installLocationNotSet );
            Sentry.addBreadcrumb( ErrorMessages.installLocationNotSet );
            return false;
        }
        
        File edaFolder = new File( getInstallLocation() );
        
        return edaFolder.exists() && edaFolder.isDirectory();
    }
    
    /**
     * Checks if the downloaded assets can be found in the EDA 'assets' folder.
     *
     * @return True if assets were found, false otherwise.
     */
    public boolean hasDownloadedAssetsInFolder () {
        if ( ! hasInstallLocationSet() ) {
            errorMessages.add( ErrorMessages.installLocationNotSet );
            Sentry.addBreadcrumb( ErrorMessages.installLocationNotSet );
            return false;
        }
        
        if ( ! hasInstallLocationCreated() ) {
            errorMessages.add( ErrorMessages.installDirectoryNotCreated );
            Sentry.addBreadcrumb( ErrorMessages.installDirectoryNotCreated );
            return false;
        }
        
        File assetDirectory = new File( getInstallLocation() + assetsFolder );
        File nativeJAR = new File( assetDirectory.getAbsolutePath() + File.separator + "jinput-native.jar" );
        
        if ( ! assetDirectory.exists() ) {
            errorMessages.add( "Asset directory does not exist." );
            Sentry.addBreadcrumb( "Asset directory does not exist." );
            return false;
        }
        
        if ( ! nativeJAR.exists() ) {
            errorMessages.add( ErrorMessages.nativeJarDoesNotExist );
            Sentry.addBreadcrumb( ErrorMessages.nativeJarDoesNotExist );
            return false;
        }
        
        return true;
    }
    
    /**
     * Checks if assets have been extracted to their required directories.
     * JInput binaries are extracted to the java.home bin folder.
     *
     * @return True if assets have been downloaded, false if something during the check failed.
     * Calling getErrorMessage() will return a String for what failed.
     */
    public boolean hasAssetsExtracted () {
        if ( ! hasInstallLocationSet() ) {
            errorMessages.add( ErrorMessages.installLocationNotSet );
            Sentry.addBreadcrumb( "Application installation folder not set." );
            return false;
        }
        
        if ( Utils.isJavaPathNull() ) {
            errorMessages.add( ErrorMessages.javaInstallPathNull );
            Sentry.addBreadcrumb( "Java installation path is null or unavailable." );
            return false;
        }
        
        String javaInstallPath = Utils.getJavaPath();
        File binDirectory = new File( javaInstallPath + javaPathBinFolder );
        
        if ( ! binDirectory.exists() || ! binDirectory.isDirectory() ) {
            errorMessages.add( ErrorMessages.javaBinFolderNotFound );
            Sentry.addBreadcrumb( "Java bin folder could not be found." );
            return false;
        }
        
        List<String> requiredAssets = getAssetNamesForPlatform();
        
        if ( requiredAssets.size() == 0 ) {
            errorMessages.add( ErrorMessages.requiredAssetsNotDetermined );
            Sentry.addBreadcrumb( ErrorMessages.requiredAssetsNotDetermined );
            return false;
        }
        
        List<String> foundAssets = new ArrayList<>();
        File[] binFiles = binDirectory.listFiles();
        
        if ( binFiles == null ) {
            errorMessages.add( ErrorMessages.javaBinFilesNotObtained );
            Sentry.addBreadcrumb( ErrorMessages.javaBinFilesNotObtained );
            return false;
        }
        
        for ( String s : requiredAssets ) {
            for ( File f : binFiles ) {
                if ( f.getName().equals( s ) ) {
                    foundAssets.add( s );
                }
            }
        }
        
        if ( foundAssets.size() != requiredAssets.size() ) {
            for ( String s : requiredAssets ) {
                if ( ! foundAssets.contains( s ) ) {
                    missingAssets.add( s );
                }
            }
            Sentry.addBreadcrumb( "Missing assets determined." );
            errorMessages.add( ErrorMessages.missingAssetMessage );
            return false;
        }
        
        return true;
    }
    
    /**
     * Connects to the Github repo for EDA and validates if updates are available.
     *
     * @return Returns true of updates are available, false otherwise.
     */
    public boolean checkForUpdate () {
        URL updateURL = null;
        Sentry.addBreadcrumb( "Getting update url." );
        
        try {
            updateURL = new URL( updateURLStr );
            Sentry.addBreadcrumb( "Update url retrieved" );
        } catch ( MalformedURLException e ) {
            e.printStackTrace();
            SentryId id = Sentry.captureException( e );
            NotificationManager.captureUserFeedback( id );
            return false;
        }
        
        BufferedReader urlReader = null;
        Sentry.addBreadcrumb( "Creating BufferedReader for url." );
        try {
            urlReader = new BufferedReader( new InputStreamReader( updateURL.openStream() , StandardCharsets.UTF_8 ) );
        } catch ( IOException e ) {
            e.printStackTrace();
            SentryId id = Sentry.captureException( e );
            NotificationManager.captureUserFeedback( id );
            return false;
        }
        
        Sentry.addBreadcrumb( "Creating BufferedReader for file." );
        BufferedReader fileReader = new BufferedReader( new InputStreamReader( EDASetup.class.getResourceAsStream( "/Version" ) ) );
        
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
        if ( ! urlRead.equals( fileRead ) ) {
            Sentry.addBreadcrumb( "Updates available." );
            return true;
        }
        
        Sentry.addBreadcrumb( "No updates available." );
        return false;
    }
    
    /**
     * Registers Eurostile font for use with the application.
     *
     * @return True of the font was successfully registered.
     */
    public boolean registerEurostileFont () {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        
        try {
            return ge.registerFont( Font.createFont( Font.TRUETYPE_FONT ,
                                                     new File( Utils.class.getResource( "/io/edbm/resources/eurostile.TTF" ).toURI() ) ) );
        } catch ( FontFormatException | URISyntaxException | IOException e ) {
            e.printStackTrace();
            SentryId id = Sentry.captureException( e );
            NotificationManager.captureUserFeedback( id );
        }
        
        return false;
    }
    
    /**
     * Retrieves the names of the assets required dependent on OS.
     * Currently the required assets are the Eurostile.ttf file and the Jinput
     * binaries.
     *
     * @return A list of asset names. If the OS is not determined, an empty list with an initial capacity of 0.
     */
    private List<String> getAssetNamesForPlatform () {
        Sentry.addBreadcrumb( "Retrieving required asset names for platform." );
        if ( Utils.isWindows() ) {
            return Arrays.asList( "jinput-dx8_64.dll" , "jinput-raw_64.dll" );
        } else if ( Utils.isMac() ) {
            return Arrays.asList("libjinput-osx.jnilib" );
        } else if ( Utils.isLinux() ) {
            return Arrays.asList( "libjinput-linux64.so" );
        } else {
            Sentry.captureMessage( ErrorMessages.requiredAssetsNotDetermined );
            errorMessages.add( ErrorMessages.requiredAssetsNotDetermined );
            return new ArrayList<>( 0 );
        }
    }
}