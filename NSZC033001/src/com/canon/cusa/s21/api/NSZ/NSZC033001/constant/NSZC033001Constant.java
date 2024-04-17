/*
 * <pre>Copyright (c) 2014 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC033001.constant;

/**
 *<pre>
 * Search Option API Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/11   Fujitsu         H.Nakajima      Create          
 *</pre>
 */
public class NSZC033001Constant {

    /**
     * Message ID
     */
    public enum MESSAGE_ID {
        /** Input parameter "Global Company Code" is a mandatory field. */
        NSZM0001E,
        /** Input parameter "Process Mode" is a mandatory field. */
        NSZM0003E,
        /** Input parameter "Search Option In Application ID" is a mandatory field. */
        NSZM0533E,
        /** Input parameter "Search Option PK" is a mandatory field. */
        NSZM0534E,
        /** Saved Search Option has been deleted. */
        NSZM0529E,
        /** Please input 'Search Option Name'. */
        NSZM0530E,
        /** Saved search options exceeds 50.  No more options can be added. */
        NSZM0535E,
        /** Failed to insert a Save Search Option record. */
        NSZM0536E,
        /** Failed to update a Save Search Option record. */
        NSZM0537E,
        /** Failed to remove a Save Search Option record. */
        NSZM0538E,
    }
    
    /**
     * Process Mode 1:Search.
     */
    public static final String PROCESS_MODE_SEARCH = "1";
    
    /**
     * Process Mode 2:Insert/Update.
     */
    public static final String PROCESS_MODE_INSERT_UPDATE = "2";
    
    /**
     * Process Mode 3:Delete.
     */
    public static final String PROCESS_MODE_DELETE = "3";
    
    /**
     * NUM CONST KEY : NSZC0330_SRCH_OPT_MAX_NUM
     */
    public static final String NUM_CONST_KEY_SRCH_OPT_MAX_NUM = "NSZC0330_SRCH_OPT_MAX_NUM";


}
