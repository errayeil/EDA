package io.edbm.Utilities;

import javax.swing.*;
import java.awt.*;

/**
 * @author Steven Frizell
 * @since HIP 2
 * @version HIP 2
 */
public class Utils {
    
    /**
     * Private Constructor.
     */
    private Utils() {}

    /**
     *
     * @param comp
     * @param visible
     */
    public static void invokeSetVisible( Container container, boolean visible) {
        SwingUtilities.invokeLater( () -> {
            container.setVisible( visible);
        });
    }

    /**
     *
     * @param comp
     * @param visible
     */
    public static void invokeSetVisibleDialog( Dialog comp, boolean visible) {
        SwingUtilities.invokeLater( () -> {
            comp.setVisible( visible);
        });
    }

    /**
     *
     * @param comp
     * @param visible
     */
    public static void invokeSetVisibleWind( JWindow comp, boolean visible) {
        SwingUtilities.invokeLater( () -> {
            comp.setVisible( visible);
        });
    }

    /**
     *
     * @param comp
     * @param visible
     */
    public static void invokeSetVisibleComp( JComponent comp, boolean visible) {
        SwingUtilities.invokeLater( () -> {
         comp.setVisible( visible);
        });
    }
    /**
     * Retrieves the current installed Java Version.
     * @return
     */
    public static String getJavaVersion() {
        return System.getProperty( "java.version" );
    }
    
    /**
     * Returns the install path of java. This can
     * return null.
     * @return
     */
    public static String getJavaPath() {
        return System.getProperty("java.home");
    }
    
    /**
     * Retrieves the current system users home directory.
     * @return
     */
    public static String getUserHome() {
        return System.getProperty( "user.home" );
    }
    
    /**
     * Retrieves the underlying OS name.
     * @return
     */
    public static String getSystemOS() {
        return System.getProperty( "os.name" );
    }
    
    /**
     * Determines if the underlying OS is Windows.
     * @return
     */
    public static boolean isWindows() {
        return getSystemOS().contains( "Wind" );
    }
    
    /**
     * Determines if the underlying OS is Macintosh.
     * @return
     */
    public static boolean isMac() {
        return getSystemOS().contains( "mac" );
    }
    
    /**
     * Determines if the underlying OS is Linux based.
     * @return
     */
    public static boolean isLinux() {
        String OS = getSystemOS();
        
        return OS.contains("nix") || OS.contains( "nux" ) || OS.contains( "aix" );
    }
    
    /**
     * Determines if the java install path is null.
     * @return Returns true if Java is installed correctly, false otherwise.
     */
    public static boolean isJavaPathNull() {
        return getJavaPath() == null;
    }
    
    /**
     * TODO
     * Returns if the current java install meets the minimum version requirement.
     * @return
     */
    public static boolean isJavaInstallCompatible() {
        return getJavaVersion().contains( "15" );
    }
    
    /**
     * TODO: Ensure compatibility for other operating systems.
     * @return Returns true if the underlying OS is compatible with JInput, false otherwise
     */
    public static boolean isJInputCompatible() {
        return getSystemOS().contains( "Windows" );
    }
}
