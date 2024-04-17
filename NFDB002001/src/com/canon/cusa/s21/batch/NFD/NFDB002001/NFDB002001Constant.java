package com.canon.cusa.s21.batch.NFD.NFDB002001;

/**
 * Dunning Letter Work Data Creation
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 01/14/2016   CSAI            K.Uramori       Create          N/A
 * 2016/07/21   Hitachi         K.Kojima        Update          QC#11128
 * 2018/06/18   Hitachi         E.Kameishi      Update          QC#25343
 * 2021/08/13   CITS            K.Suzuki        Update          QC#59070
 *</pre>
 */
public interface NFDB002001Constant {

    /** business_id : NFDB0020 */
    String BUSINESS_ID = "NFDB0020";

    /** Message */
    String ZZZM9025E = "ZZZM9025E";

    /** Message */
    String NFDM0004E = "NFDM0004E";

    /** Message */
    String NFDM0003E = "NFDM0003E";

    /** Message */
    String NFDM0013E = "NFDM0013E";

    /** Message */
    String NFCM0531E = "NFCM0531E";

    /** Fetch size for SSM */
    int FETCH_SIZE_MAX = 1000;

    /** var char key */
    String AR_CLT_DEF_EML_ADDR = "AR_CLT_DEF_EML_ADDR";

    /** var char key */
    String AR_DUN_LTR_OFC_FIRST_ADDR = "AR_DUN_LTR_OFC_FIRST_ADDR";

    /** var char key */
    String AR_DUN_LTR_OFC_SCD_ADDR = "AR_DUN_LTR_OFC_SCD_ADDR";

    /** var char key */
    String AR_DUN_LTR_OFC_THIRD_ADDR = "AR_DUN_LTR_OFC_THIRD_ADDR";

    /** var char key */
    String AR_DUN_LTR_OFC_FRTH_ADDR = "AR_DUN_LTR_OFC_FRTH_ADDR";

    /** var char key */
    String AR_DUN_LTR_OFC_CITY_ADDR = "AR_DUN_LTR_OFC_CITY_ADDR";

    /** var char key */
    String AR_DUN_LTR_OFC_PROV_NM = "AR_DUN_LTR_OFC_PROV_NM";

    /** var char key */
    String AR_DUN_LTR_OFC_ST_CD = "AR_DUN_LTR_OFC_ST_CD";

    /** var char key */
    String AR_DUN_LTR_OFC_POST_CD = "AR_DUN_LTR_OFC_POST_CD";

    /** var char key */
    String AR_DUN_LTR_OFC_TEL_NUM = "AR_DUN_LTR_OFC_TEL_NUM";

    /** var char key */
    String AR_DUN_LTR_CMPY_URL = "AR_DUN_LTR_CMPY_URL";

    // START 2016/07/21 K.Kojima [QC#11128,ADD]
    /** var char key */
    String AR_HDR_PRINT_CMPY_NM = "AR_HDR_PRINT_CMPY_NM";
    // END 2016/07/21 K.Kojima [QC#11128,ADD]
    // START 2018/06/18 E.Kameishi [QC#25343,ADD]
    /** Max Late Days*/
    public static final String MAX_LATE_DAYS = "999";
    // END 2018/06/18 E.Kameishi [QC#25343,ADD]
    // START 2021/08/13 K.Suzuki [QC#59070,ADD]
    /** Automatically executing Manual Work Item */
    String AUTO_EXE_MANUAL_WRK_ITEM_CD = "AUTO_EXE_MANUAL_WRK_ITEM_CD";
    // END   2021/08/13 K.Suzuki [QC#59070,ADD]
}
