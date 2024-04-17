/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL0060.constant;

/**
 * Class name: NFAL0060Constant
 * <dd>The class explanation: Constant variable for screen component.
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public interface NFAL0060Constant {

    /** Submit - Insert */
    static final boolean INSERT = true;

    /** Submit - Delete */
    static final boolean DELETE = false;

    /** Submit */
    static final boolean SUBMIT = true;

    /** Not Submit */
    static final boolean NOT_SUBMIT = false;

    /** Blank */
    static final String BLANK = new String("");

    /** Suffix for Code */
    public final String SUFFIX_CD = "_CD";

    /** Suffix for Name */
    public final String SUFFIX_NM = "_NM";

    /** Default Interface Type Code */
    public final String DEFAULT_IND_TP_CD = "ZZZ";

    /** Default Interface Type Code */
    public final String DEFAULT_INTFC_TP_CD = "ZZ";

    /** Suffix for Name */
    public final String SUFFIX_SORT_NUM = "_SORT_NUM";

    /** Search Mode */
    static final int SEARCH_BY_KEY = 1;

    /** Search Mode */
    static final int SEARCH_ALL = 2;

    /** Search Mode */
    static final int RESET = 3;
    
    /** Fixed AJE_INTFC_TP_CD */
    static final String INTFC_TP_CD = "01";

    /** Search */
    // static final boolean SEARCH = true;
    /** Not Search */
    // static final boolean NOT_SEARCH = false;
}
