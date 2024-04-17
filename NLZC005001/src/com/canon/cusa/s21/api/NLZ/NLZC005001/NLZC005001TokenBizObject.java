/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC005001;

import com.canon.cusa.s21.framework.nwf.util.token.S21NwfUtilTokenObj;

/**
 *<pre>
 * Inventory Adjustment Approval to WF API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/12/2018   CITS            T.Tokutomi      Create          QC#18380
 * 01/28/2020   Fujitsu         T.Ogura         Update          QC#55569
 *</pre>
 */
public class NLZC005001TokenBizObject extends S21NwfUtilTokenObj {

    /** Serial */
    private static final long serialVersionUID = 1L;

    /** invtyOrdNum */
    private String invtyOrdNum;

    /** apvlHistSrcTpCd */
    private String apvlHistSrcTpCd;

    // START 01/27/2020 T.Ogura [QC#55497,ADD]
    /** commentsLbl */
    private String commentsLbl;

    /** comments */
    private String comments;
    // END   01/27/2020 T.Ogura [QC#55497,ADD]

    /**
     * getInvtyOrdNum
     * @return inventory order number
     */
    public String getInvtyOrdNum() {
        return invtyOrdNum;
    }

    /**
     * setInvtyOrdNum
     * @param invtyOrdNum String
     */
    public void setInvtyOrdNum(String invtyOrdNum) {
        this.invtyOrdNum = invtyOrdNum;
    }

    /**
     * getApvlHistSrcTpCd
     * @return approval history source type code.
     */
    public String getApvlHistSrcTpCd() {
        return apvlHistSrcTpCd;
    }

    /**
     * setApvlHistSrcTpCd
     * @param apvlHistSrcTpCd String
     */
    public void setApvlHistSrcTpCd(String apvlHistSrcTpCd) {
        this.apvlHistSrcTpCd = apvlHistSrcTpCd;
    }

    // START 01/27/2020 T.Ogura [QC#55497,ADD]
    /**
     * getCommentsLbl
     * @return commentsLbl
     */
    public String getCommentsLbl() {
        return commentsLbl;
    }

    /**
     * setCommentsLbl
     * @param commentsLbl String
     */
    public void setCommentsLbl(String commentsLbl) {
        this.commentsLbl = commentsLbl;
    }

    /**
     * getComments
     * @return comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * setComments
     * @param comments String
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
    // END   01/27/2020 T.Ogura [QC#55497,ADD]
}
