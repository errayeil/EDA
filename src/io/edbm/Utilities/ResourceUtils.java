package io.edbm.Utilities;

import com.sun.jna.platform.unix.Resource;
import java.io.File;
import java.net.URISyntaxException;
import javax.swing.ImageIcon;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class ResourceUtils {
    
    /**
     * Private Constructor.
     */
    private ResourceUtils() {}
    
    /**
     *
     * @return
     */
    public static String getEurostilePathInPackage() {
        return "/io/edbm/resources/eurostile.TTF";
    }
    
    /**
     *
     * @return
     * @throws URISyntaxException
     */
    public static ImageIcon get32PixelHasNotificationPNG() {
        return new ImageIcon(ResourceUtils.class.getResource( "/io/edbm/resources/hasnotif32p.png" ));
    }
    
    /**
     *
     * @return
     */
    public static ImageIcon get32PixelNoNotificationPNG() {
        return new ImageIcon(ResourceUtils.class.getResource( "/io/edbm/resources/nonotif32p.png" ));
    }
    
    /**
     *
     * @return
     */
    public static ImageIcon get32PixelHomePNG() {
        return new ImageIcon( ResourceUtils.class.getResource( "/io/edbm/resource/home32p.png" ) );
    }
}
