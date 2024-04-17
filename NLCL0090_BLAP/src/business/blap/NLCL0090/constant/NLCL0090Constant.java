/**
 * <Pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLCL0090.constant;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/25/2009   Fujitsu         FXS)KF.Qian     Create          N/A
 * 04/15/2010   Fujitsu         S.Yoshida       Update          Def.5017
 * 12/02/2019   CITS            K.Ogino         Update          QC#54823
 *</pre>
 */
public interface NLCL0090Constant {

    /**
     */
    String BUSINESS_ID = "NLCL0090";

    /**
     */
    String MDSECD = "Item Number";

    /**
     */
    String STKSTS = "Stock Status";

    /**
     */
    String FROM_TO_FLG_ZERO = "0";

    /**
     */
    String FROM_TO_FLG_ONE = "1";

    /**
     */
    String TRX_LINE_SUB_NUM = "001";

    /**
     */
    int FIRST_TRX_LINE_NUM = 1;

    /**
     */
    int FIRST_LOOP_NUM = 0;

    /**
     */
    int LINE_NUM_LENGTH = 3;

    /**
     */
    String SPARE_VALUE = "0";

    /**
     */
    String MAXLENGTH = "100";

    /**
     */
    int MAXLINE = 100;

    /***************************************************
     * Message
     ***************************************************/

    /** It is being updated by another user.  Please start again from a search. */
    String NLBM0009E = "NLBM0009E";

    /** Due to an error, process cannot be completed.  Please contact IT Department. */
    String NLCM0001E = "NLCM0001E";

    /** Entered "Location Code" does not exist. */
    String NLCM0004E = "NLCM0004E";

    /** Entered "Transaction Number" is for a different Inventory Order Type. */
    String NLCM0020E = "NLCM0020E";

    /** Inventory with the entered Key does not exist. */
    String NLCM0021E = "NLCM0021E";

    /** The number of Detail rows reached to the maximum.  No more Details can be registered. */
    String NLCM0025E = "NLCM0025E";

    /** Entered Quantity exceeds "Current Available". */
    String NLCM0031E = "NLCM0031E";

    /** "Current Available" has a negative value. */
    String NLCM0032E = "NLCM0032E";

    /** Entered @ is invalid. */
    String NLCM0065E = "NLCM0065E";

    /** Entered @ cannot handle the target inventory. */
    String NLCM0067E = "NLCM0067E";

    /** The entered Serial Number is a component of configuration. */
    String NLCM0150E = "NLCM0150E";

    /** The Serial # specified does not exist in this WH. */
    String NLCM0151E = "NLCM0151E";

    /** Location status of the specified Serial number is different from IB. */
    String NLCM0152E = "NLCM0152E";

    /** Stock status of the specified Serial number is different from IB. */
    String NLCM0153E = "NLCM0153E";

    /** Can not specify multiple Serial Numbers which is a component configuration. */
    String NLCM0166E = "NLCM0166E";

    /** Quantity should be 1 if specify serial# which is a component configuration. */
    String NLCM0167E ="NLCM0167E";

    /** The specified Serial # already exists. */
    String NLZM2141E = "NLZM2141E";

    /** The entered [@] does not exist in master. */
    String NLZM2278E = "NLZM2278E";

    /** The combination of [@] and [@] does not exist in master. */
    String NLZM2279E = "NLZM2279E";

    /** The entered Serial Number is already allocated by other order. */
    String NLZM2317E = "NLZM2317E";

    /** The entered Serial Number is located at customer site. */
    String NLZM2318E = "NLZM2318E";

    /** No search results found. */
    String NZZM0000E = "NZZM0000E";

    /** The process [@] has been successfully completed. */
    String ZZZM9003I = "ZZZM9003I";

    /** Please execute again after correcting the error field */
    String ZZM9037E = "ZZM9037E";

    /** Current Available Qty is component of other configuration. */
    String NLCM0238E = "NLCM0238E";
}
