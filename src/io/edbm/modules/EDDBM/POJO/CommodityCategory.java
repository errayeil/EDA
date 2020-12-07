package io.edbm.modules.EDDBM.POJO;

/**
 *
 */
public class CommodityCategory implements Comparable<String> {
    
    /**
     *
     */
    public String id;
    
    /**
     *
     */
    public String name;
    
    /**
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo ( String o ) {
        
        if (id.equals( 0 )) {
            return 0;
        }
        
        return -1;
    }
}
