package com.canon.cusa.s21.batch.NWC.NWCB013001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/** <pre>
 * Consolidation Bill Regenerate Bean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/25   Fujitsu         H.Nagashima     Create          N/A
 * 2016/08/16   SRAA            K.Aratani       Create          QC#13107
 * </pre>
 */
public class ConslBillRgnrBean {

    /** Invoice Number */
    private String invNum;

    /** Consolidation Bill PK */
    private BigDecimal conslBillPk;

    /** Invoice Type Code */
    private String invTpCd;

    /** Bill To Customer Code */
    private String billToCustCd;

    /** Customer Issue PO Number */
    private String custIssPoNum;

    /** DS Contract Number */
    private String dsContrNum;

    /** Billing per Date */
    private String bllgPer;

    /** Serial Number */
    private String serNum;

    /** Model Name */
    private String mdlNm;

    /** Customer Invoice Source Code */
    private String custInvSrcCd;
    
    /** Invoice Group Number */
    private String invGrpNum;
    
    /** Easy Pac Flag */
    private String easyPacFlg;
    
    /** Consolidated Bill To Customer Code */
    private String conslBillToCustCd;

    /**
     * Constructor
     * @param rs ResultSet
     * @throws SQLException e
     */
    public ConslBillRgnrBean(ResultSet rs) throws SQLException {
        this.invNum      = rs.getString("INV_NUM");
        this.conslBillPk = rs.getBigDecimal("CONSL_BILL_PK");
        this.invTpCd = rs.getString("INV_TP_CD");
        this.billToCustCd = rs.getString("BILL_TO_CUST_CD");
        //QC#13107
        this.custIssPoNum = rs.getString("CUST_ISS_PO_NUM") == null ? "" : rs.getString("CUST_ISS_PO_NUM");
        this.dsContrNum = rs.getString("DS_CONTR_NUM") == null ? "" : rs.getString("DS_CONTR_NUM");
        this.bllgPer = rs.getString("BLLG_PER") == null ? "" : rs.getString("BLLG_PER");
        this.serNum = rs.getString("SER_NUM") == null ? "" : rs.getString("SER_NUM");
        this.mdlNm = rs.getString("MDL_NM") == null ? "" : rs.getString("MDL_NM");
        this.custInvSrcCd = rs.getString("CUST_INV_SRC_CD") == null ? "" : rs.getString("CUST_INV_SRC_CD");
        this.invGrpNum = rs.getString("INV_GRP_NUM") == null ? "" : rs.getString("INV_GRP_NUM");
        this.easyPacFlg = rs.getString("EASY_PAC_FLG") == null ? "" : rs.getString("EASY_PAC_FLG");
        this.conslBillToCustCd = rs.getString("CONSL_BILL_TO_CUST_CD") == null ? "" : rs.getString("CONSL_BILL_TO_CUST_CD");

    }
    /**
     * @return invNum
     */
    public String getInvNum() {
        return invNum;
    }
    /**
     * @return conslBillPk
     */
    public BigDecimal getConslBillPk() {
        return conslBillPk;
    }
    /**
     * @return invTpCd
     */
    public String getInvTpCd() {
        return invTpCd;
    }
    /**
     * @return billToCustCd
     */
    public String getBillToCustCd() {
        return billToCustCd;
    }
    /**
     * @return custIssPoNum
     */
    public String getCustIssPoNum() {
        return custIssPoNum;
    }
    /**
     * @return dsContrNum
     */
    public String getDsContrNum() {
        return dsContrNum;
    }
    /**
     * @return bllgPer
     */
    public String getBllgPer() {
        return bllgPer;
    }
    /**
     * @return serNum
     */
    public String getSerNum() {
        return serNum;
    }
    /**
     * @return mdlNm
     */
    public String getMdlNm() {
        return mdlNm;
    }
    /**
     * @return custInvSrcCd
     */
    public String getCustInvSrcCd() {
        return custInvSrcCd;
    }
    /**
     * @return invGrpNum
     */
    public String getInvGrpNum() {
        return invGrpNum;
    }
    /**
     * @return easyPacFlg
     */
    public String getEasyPacFlg() {
        return easyPacFlg;
    }
    /**
     * @return conslBillToCustCd
     */
    public String getConslBillToCustCd() {
        return conslBillToCustCd;
    }
    
}
