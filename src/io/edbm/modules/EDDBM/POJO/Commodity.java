package io.edbm.modules.EDDBM.POJO;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class Commodity implements Comparable<String> {
    
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
     */
    public String category_id;
    
    /**
     *
     */
    public String average_price;
    
    /**
     *
     */
    public String is_rare;
    
    /**
     *
     */
    public String max_buy_price;
    
    /**
     *
     */
    public String max_sell_price;
    
    /**
     *
     */
    public String min_buy_price;
    
    /**
     *
     */
    public String min_sell_price;
    
    /**
     *
     */
    public String buy_price_lower_average;
    
    /**
     *
     */
    public String sell_price_upper_average;
    
    /**
     *
     */
    public String is_non_marketable;
    
    /**
     *
     */
    public String ed_id;
    
    /**
     *
     */
    public CommodityCategory category;
    
    /**
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo ( String o ) {
        
        if (id.equals( o )) {
            return 0;
        }
        
        return -1;
    }
}

