package com.canon.cusa.s21.batch.NFC.NFCB026001;

public interface NFCB026001Constant {
    /** Bulk Insert Count */
    int BULK_CNT = 1000;

    /** Program Id */
    final String[] PROGRAM_ID = {"NFCB026001"};

    /** Process Started: [@] */
    final String NFCM0584I = "NFCM0584I";

    /** Entry Parameter Error [@] */
    final String NFCM0501E = "NFCM0501E";

    /** The specified date does not exist. */
    final String NFCM0574E = "NFCM0574E";

    /** Entry Parameter Error. */
    final String NFCM0522E = "NFCM0522E";

    /** The field of [@] is illegal. */
    final String ZZMM0007E = "ZZMM0007E";

    /** GLBL_CMPY_CD */
    final String[] MSG_STR_GLBL_CMPY_CD = {"GLBL_CMPY_CD"};

    /** Message String : ITEM Name */
    final String[] MSG_STR_INTERFACE_ID = {"INTERFACE_ID"};

    /** Mail Type Normal */
    final String TYPE_NORMAL = "N";

    /** Mail Type Error */
    final String TYPE_ERROR = "E";

    // Mail
    /** Mail Group ID */
    final String ML_GRP_ID = "NFCB0260";

    /** Mail Group ID key From */
    final String ML_GRP_ID_KEY_FROM = "FROM";

    /** Mail Group ID key To */
    final String ML_GRP_ID_KEY_TO = "TO";

    /** Mail Template ID Normal */
    final String ML_TMPL_ID_NORMAL = "NFCB0260M001";
    
    /** Mail Template ID Error */
    final String ML_TMPL_ID_ERROR = "NFCB0260M002";

    /** Debug Msg Lv */
    final int DEBUG_MSG_LVL = 8;


}