package com.canon.cusa.s21.common.NPX.NPXC001001;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Nine Segment Bean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/12/12   Fujitsu         S.Takami        Create          QC#29456
 * 2019/02/06   Fujitsu         S.Takami        Update          QC#30181
 *</pre>
 */
public class NPXC001001GetDefNineSegDataBean {

    /** COA_CMPY_CD (Company) */
    private String coaCmpyCd = null;

    /** COA_BR_CD (Branch) */
    private String coaBrCd = null;

    /** COA_CC_CD (Cost Center) */
    private String coaCcCd = null;

    /** COA_ACCT_CD (Account) */
    private String coaAcctCd = null;

    /** COA_PROD_CD (Product) */
    private String coaProdCd = null;

    /** COA_CH_CD (Channel) */
    private String coaChCd = null;

    /** COA_AFFL_CD (Intercompany) */
    private String coaAfflCd = null;

    /** COA_PROJ_CD (Merchandise) */
    private String coaProjCd = null;

    /** COA_EXTN_CD (Business Unit) */
    private String coaExtnCd = null;

    /** Error Message List */
    private List<String> msgIdList = new ArrayList<String>(0);

    // 2019/02/06 QC#30181 Add Start
    /** Charge Account Text. COA_* item concatenated with '.' */
    private String chrgAcctText = null;

    /** Original Coa Prod Code Null flag*/
    private boolean origCoaProdNullFlg = false;

    /** Original Coa Channel Code Null flag*/
    private boolean origCoaChNullFlg = false;

    /** Original Coa Prod Code Null flag*/
    private boolean origCoaProjNullFlg = false;
    // 2019/02/06 QC#30181 Add End
    /**
     * get COA_CMPY_CD (Company)
     * @return COA_CMPY_CD (Company)
     */
    public String getCoaCmpyCd() {
        return coaCmpyCd;
    }

    /**
     * set COA_CMPY_CD (Company)
     * @param coaCmpyCd COA_CMPY_CD (Company)
     */
    public void setCoaCmpyCd(String coaCmpyCd) {
        this.coaCmpyCd = coaCmpyCd;
    }

    /**
     * get COA_BR_CD (Branch)
     * @return COA_BR_CD (Branch)
     */
    public String getCoaBrCd() {
        return coaBrCd;
    }

    /**
     * set COA_BR_CD (Branch)
     * @param coaBrCd COA_BR_CD (Branch)
     */
    public void setCoaBrCd(String coaBrCd) {
        this.coaBrCd = coaBrCd;
    }

    /**
     * get COA_CC_CD (Cost Center)
     * @return COA_CC_CD (Cost Center)
     */
    public String getCoaCcCd() {
        return coaCcCd;
    }

    /**
     * set COA_CC_CD (Cost Center)
     * @param coaCcCd COA_CC_CD (Cost Center)
     */
    public void setCoaCcCd(String coaCcCd) {
        this.coaCcCd = coaCcCd;
    }

    /**
     * get COA_ACCT_CD (Account)
     * @return COA_ACCT_CD (Account)
     */
    public String getCoaAcctCd() {
        return coaAcctCd;
    }

    /**
     * set COA_ACCT_CD (Account)
     * @param coaAcctCd COA_ACCT_CD (Account)
     */
    public void setCoaAcctCd(String coaAcctCd) {
        this.coaAcctCd = coaAcctCd;
    }

    /**
     * get COA_PROD_CD (Product)
     * @return COA_PROD_CD (Product)
     */
    public String getCoaProdCd() {
        return coaProdCd;
    }

    /**
     * set COA_PROD_CD (Product)
     * @param coaProdCd COA_PROD_CD (Product)
     */
    public void setCoaProdCd(String coaProdCd) {
        this.coaProdCd = coaProdCd;
    }

    /**
     * get COA_CH_CD (Channel)
     * @return get COA_CH_CD (Channel)
     */
    public String getCoaChCd() {
        return coaChCd;
    }

    /**
     * set COA_CH_CD (Channel)
     * @param coaChCd COA_CH_CD (Channel)
     */
    public void setCoaChCd(String coaChCd) {
        this.coaChCd = coaChCd;
    }

    /**
     * get COA_AFFL_CD (Intercompany)
     * @return COA_AFFL_CD (Intercompany)
     */
    public String getCoaAfflCd() {
        return coaAfflCd;
    }

    /**
     * set COA_AFFL_CD (Intercompany)
     * @param coaAfflCd COA_AFFL_CD (Intercompany)
     */
    public void setCoaAfflCd(String coaAfflCd) {
        this.coaAfflCd = coaAfflCd;
    }

    /**
     * get COA_PROJ_CD (Merchandise)
     * @return COA_PROJ_CD (Merchandise)
     */
    public String getCoaProjCd() {
        return coaProjCd;
    }

    /**
     * set COA_PROJ_CD (Merchandise)
     * @param coaProjCd COA_PROJ_CD (Merchandise)
     */
    public void setCoaProjCd(String coaProjCd) {
        this.coaProjCd = coaProjCd;
    }

    /**
     * get COA_EXTN_CD (Business Unit)
     * @return COA_EXTN_CD (Business Unit)
     */
    public String getCoaExtnCd() {
        return coaExtnCd;
    }

    /**
     * set COA_EXTN_CD (Business Unit)
     * @param coaExtnCd COA_EXTN_CD (Business Unit)
     */
    public void setCoaExtnCd(String coaExtnCd) {
        this.coaExtnCd = coaExtnCd;
    }

    /**
     * get Message Id List
     * @return msgIdList
     */
    public List<String> getMsgIdList() {
        return this.msgIdList;
    }

    /**
     * <pre>
     * get message id from message ID List.
     * If idx > message list size, return null.
     * @param idx index of message id list.
     * @return Message id (Designated Index)
     * </pre>
     */
    public String getMsgId(int idx) {

        try {
            return this.msgIdList.get(idx);
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * add msgId to the tail of the msgIdLisg.
     * @param msgId Message ID
     */
    public void addMsgIdList(String msgId) {
        if (this.msgIdList == null) {
            this.msgIdList = new ArrayList<String>(0);
        }
        this.msgIdList.add(msgId);
    }

    // 2019/02/06 QC#30181 Add Start
    /**
     * get chrgAcctText
     * @return chrgAcctText
     */
    public String getChrgAcctText() {
        return chrgAcctText;
    }

    /**
     * set chrgAcctText
     * @param chrgAccntTxt chargAcctTxt
     */
    public void setChrgAcctText(String chrgAccntTxt) {
        this.chrgAcctText = chrgAccntTxt;
    }

    /** @return origCoaProdNullFlg */
    public boolean isOrigCoaProdNullFlg() {
        return origCoaProdNullFlg;
    }

    /** @param origCoaProdNullFlg true: original coa prod code is null false: is not null */
    public void setOrigCoaProdNullFlg(boolean origCoaProdNullFlg) {
        this.origCoaProdNullFlg = origCoaProdNullFlg;
    }

    /** @return true: original coa channel is null. false: is not null */
    public boolean isOrigCoaChNullFlg() {
        return origCoaChNullFlg;
    }

    /** @param origCoaChNullFlg true: original coa channel is null. false: is not null */
    public void setOrigCoaChNullFlg(boolean origCoaChNullFlg) {
        this.origCoaChNullFlg = origCoaChNullFlg;
    }

    /** @return true: original coa proj code is null, false: is not null */
    public boolean isOrigCoaProjNullFlg() {
        return origCoaProjNullFlg;
    }

    /** @param origCoaProjNullFlg true: original coa proj code is null, false: is not null */
    public void setOrigCoaProjNullFlg(boolean origCoaProjNullFlg) {
        this.origCoaProjNullFlg = origCoaProjNullFlg;
    }

    // 2019/02/06 QC#30181 Add End
}
