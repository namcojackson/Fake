/* <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre> */
package com.canon.cusa.s21.api.NWZ.NWZC164001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.framework.nwf.util.token.S21NwfUtilTokenObj;

/**
 * <pre>
 * NWZC164001TokenObject
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/07   Fujitsu         H.Nagashima     Create          N/A
 * 2018/07/24   Fujitsu         Y.Matsui        Update          QC#26798
 *</pre>
 */
public class NWZC164001TokenObject extends S21NwfUtilTokenObj {

    /**  */
    private static final long serialVersionUID = 1L;
    /** */
    List<NWZC164001TokenObjectLine> lineData = new ArrayList<NWZC164001TokenObjectLine>();

    /**
     * @param data S21NwfUtilTokenObjExtLine
     */
    public void addLineData(NWZC164001TokenObjectLine data) {
        lineData.add(data);
    }

    /**
     * @param index int
     * @return S21NwfUtilTokenObjExtLine
     */
    public NWZC164001TokenObjectLine getLineData(int index) {
        NWZC164001TokenObjectLine ret = null;

        if (lineData.size() > index) {
            ret = lineData.get(index);
        }

        return ret;
    }

    /** Document ID */
    private String docId;

    /** GLBL_CMPY_CD */
    private String glblCmpyCd;

    /** CPO_ORD_NUM */
    private String cpoOrdNum;

    /** SLS_DT */
    private String slsDt;

    /** ORD_PROC_NODE_CD */
    private String ordProcNodeCd;

    /** ORD_PROC_NODE_NM */
    private String ordProcNodeNm;

    /** ORD_PROC_NODE_PRFL_CD */
    private String ordProcNodePrflCd;

    /** ORD_PROC_NODE_PRFL_EQUIP_FLG */
    private String ordProcNodePrflEquipFlg;

    /** HLD_PK */
    private BigDecimal hldPk;

    /** HLD_RSN_CD */
    private String hldRsnCd;

    /** CPO_DTL_LINE_NUM */
    private String cpoDtlLineNum;

    /** CPO_DTL_LINE_SUB_NUM */
    private String cpoDtlLineSubNum;

    /** CR_LIMIT_AMT */
    private BigDecimal crLimitAmt;

    /** ORD_TOT_AMT */
    private BigDecimal ordTotAmt;

    /** BILL_TO_CUST_CD */
    private String billToCustCd;

    /** BILL_TO_CUST_ACCT_CD */
    private String billToCustAcctCd;

    /** dtlAttrb1 */
    private String dtlAttrb1;

    /** dtlAttrb2 */
    private String dtlAttrb2;

    /** dtlAttrb3 */
    private String dtlAttrb3;

    /** dtlAttrb4 */
    private String dtlAttrb4;

    /** dtlAttrb5 */
    private String dtlAttrb5;

    /** dtlAttrb6 */
    private String dtlAttrb6;

    /** dtlAttrb7 */
    private String dtlAttrb7;

    /** dtlAttrb8 */
    private String dtlAttrb8;

    /** dtlAttrb9 */
    private String dtlAttrb9;

    /** dtlAttrb10 */
    private String dtlAttrb10;

    /** dtlAttrb11 */
    private String dtlAttrb11;

    /** dtlAttrb12 */
    private String dtlAttrb12;

    /** dtlAttrb13 */
    private String dtlAttrb13;

    /** dtlAttrb14 */
    private String dtlAttrb14;

    /** dtlAttrb15 */
    private String dtlAttrb15;

    /** dtlAttrb16 */
    private String dtlAttrb16;

    /** dtlAttrb17 */
    private String dtlAttrb17;

    /** dtlAttrb18 */
    private String dtlAttrb18;

    /** dtlAttrb19 */
    private String dtlAttrb19;

    /** dtlAttrb20 */
    private String dtlAttrb20;

    /** dtlAttrb21 */
    private String dtlAttrb21;

    /** dtlAttrb22 */
    private String dtlAttrb22;

    /** dtlAttrb23 */
    private String dtlAttrb23;

    /** style01 */
    private String style01;

    /**
     * @return docId
     */
    public String getDocId() {
        return docId;
    }

    /**
     * @return glblCmpyCd
     */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /**
     * @return cpoOrdNum
     */
    public String getCpoOrdNum() {
        return cpoOrdNum;
    }

    /**
     * @return slsDt
     */
    public String getSlsDt() {
        return slsDt;
    }

    /**
     * @return ordProcNodeCd
     */
    public String getOrdProcNodeCd() {
        return ordProcNodeCd;
    }

    /**
     * @return ordProcNodeNm
     */
    public String getOrdProcNodeNm() {
        return ordProcNodeNm;
    }

    /**
     * @return ordProcNodePrflCd
     */
    public String getOrdProcNodePrflCd() {
        return ordProcNodePrflCd;
    }

    /**
     * @return hldPk
     */
    public BigDecimal getHldPk() {
        return hldPk;
    }

    /**
     * @return hldRsnCd
     */
    public String getHldRsnCd() {
        return hldRsnCd;
    }

    /**
     * @return cpoDtlLineNum
     */
    public String getCpoDtlLineNum() {
        return cpoDtlLineNum;
    }

    /**
     * @return cpoDtlLineSubNum
     */
    public String getCpoDtlLineSubNum() {
        return cpoDtlLineSubNum;
    }

    /**
     * @return dtlAttrb1
     */
    public String getDtlAttrb1() {
        return dtlAttrb1;
    }

    /**
     * @return dtlAttrb2
     */
    public String getDtlAttrb2() {
        return dtlAttrb2;
    }

    /**
     * @return dtlAttrb3
     */
    public String getDtlAttrb3() {
        return dtlAttrb3;
    }

    /**
     * @return dtlAttrb4
     */
    public String getDtlAttrb4() {
        return dtlAttrb4;
    }

    /**
     * @return dtlAttrb5
     */
    public String getDtlAttrb5() {
        return dtlAttrb5;
    }

    /**
     * @return dtlAttrb6
     */
    public String getDtlAttrb6() {
        return dtlAttrb6;
    }

    /**
     * @return dtlAttrb7
     */
    public String getDtlAttrb7() {
        return dtlAttrb7;
    }

    /**
     * @return dtlAttrb8
     */
    public String getDtlAttrb8() {
        return dtlAttrb8;
    }

    /**
     * @return dtlAttrb9
     */
    public String getDtlAttrb9() {
        return dtlAttrb9;
    }

    /**
     * @return dtlAttrb10
     */
    public String getDtlAttrb10() {
        return dtlAttrb10;
    }

    /**
     * @return dtlAttrb11
     */
    public String getDtlAttrb11() {
        return dtlAttrb11;
    }

    /**
     * @return dtlAttrb12
     */
    public String getDtlAttrb12() {
        return dtlAttrb12;
    }

    /**
     * @return dtlAttrb13
     */
    public String getDtlAttrb13() {
        return dtlAttrb13;
    }

    /**
     * @return dtlAttrb14
     */
    public String getDtlAttrb14() {
        return dtlAttrb14;
    }

    /**
     * @return dtlAttrb15
     */
    public String getDtlAttrb15() {
        return dtlAttrb15;
    }

    /**
     * @return dtlAttrb16
     */
    public String getDtlAttrb16() {
        return dtlAttrb16;
    }

    /**
     * @return dtlAttrb17
     */
    public String getDtlAttrb17() {
        return dtlAttrb17;
    }

    /**
     * @return dtlAttrb18
     */
    public String getDtlAttrb18() {
        return dtlAttrb18;
    }

    /**
     * @return dtlAttrb19
     */
    public String getDtlAttrb19() {
        return dtlAttrb19;
    }

    /**
     * @return dtlAttrb20
     */
    public String getDtlAttrb20() {
        return dtlAttrb20;
    }

    /**
     * @return getDtlAttrb21
     */
    public String getDtlAttrb21() {
        return dtlAttrb21;
    }

    /**
     * @return getDtlAttrb22
     */
    public String getDtlAttrb22() {
        return dtlAttrb22;
    }

    /**
     * @return getDtlAttrb23
     */
    public String getDtlAttrb23() {
        return dtlAttrb23;
    }

    /**
     * @return getStyle01
     */
    public String getStyle01() {
        return style01;
    }

    /**
     * @param docId String
     */
    public void setDocId(String docId) {
        this.docId = docId;
    }

    /**
     * @param glblCmpyCd String
     */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    /**
     * @param cpoOrdNum String
     */
    public void setCpoOrdNum(String cpoOrdNum) {
        this.cpoOrdNum = cpoOrdNum;
    }

    /**
     * @param slsDt String
     */
    public void setSlsDt(String slsDt) {
        this.slsDt = slsDt;
    }

    /**
     * @param ordProcNodeCd String
     */
    public void setOrdProcNodeCd(String ordProcNodeCd) {
        this.ordProcNodeCd = ordProcNodeCd;
    }

    /**
     * @param ordProcNodeNm String
     */
    public void setOrdProcNodeNm(String ordProcNodeNm) {
        this.ordProcNodeNm = ordProcNodeNm;
    }

    /**
     * @param ordProcNodePrflCd String
     */
    public void setOrdProcNodePrflCd(String ordProcNodePrflCd) {
        this.ordProcNodePrflCd = ordProcNodePrflCd;
    }

    /**
     * @param hldPk BigDecimal
     */
    public void setHldPk(BigDecimal hldPk) {
        this.hldPk = hldPk;
    }

    /**
     * @param hldRsnCd String
     */
    public void setHldRsnCd(String hldRsnCd) {
        this.hldRsnCd = hldRsnCd;
    }

    /**
     * @param cpoDtlLineNum String
     */
    public void setCpoDtlLineNum(String cpoDtlLineNum) {
        this.cpoDtlLineNum = cpoDtlLineNum;
    }

    /**
     * @param cpoDtlLineSubNum String
     */
    public void setCpoDtlLineSubNum(String cpoDtlLineSubNum) {
        this.cpoDtlLineSubNum = cpoDtlLineSubNum;
    }

    /**
     * @param dtlAttrb1 String
     */
    public void setDtlAttrb1(String dtlAttrb1) {
        this.dtlAttrb1 = dtlAttrb1;
    }

    /**
     * @param dtlAttrb2 String
     */
    public void setDtlAttrb2(String dtlAttrb2) {
        this.dtlAttrb2 = dtlAttrb2;
    }

    /**
     * @param dtlAttrb3 String
     */
    public void setDtlAttrb3(String dtlAttrb3) {
        this.dtlAttrb3 = dtlAttrb3;
    }

    /**
     * @param dtlAttrb4 String
     */
    public void setDtlAttrb4(String dtlAttrb4) {
        this.dtlAttrb4 = dtlAttrb4;
    }

    /**
     * @param dtlAttrb5 String
     */
    public void setDtlAttrb5(String dtlAttrb5) {
        this.dtlAttrb5 = dtlAttrb5;
    }

    /**
     * @param dtlAttrb6 String
     */
    public void setDtlAttrb6(String dtlAttrb6) {
        this.dtlAttrb6 = dtlAttrb6;
    }

    /**
     * @param dtlAttrb7 String
     */
    public void setDtlAttrb7(String dtlAttrb7) {
        this.dtlAttrb7 = dtlAttrb7;
    }

    /**
     * @param dtlAttrb8 String
     */
    public void setDtlAttrb8(String dtlAttrb8) {
        this.dtlAttrb8 = dtlAttrb8;
    }

    /**
     * @param dtlAttrb9 String
     */
    public void setDtlAttrb9(String dtlAttrb9) {
        this.dtlAttrb9 = dtlAttrb9;
    }

    /**
     * @param dtlAttrb10 String
     */
    public void setDtlAttrb10(String dtlAttrb10) {
        this.dtlAttrb10 = dtlAttrb10;
    }

    /**
     * @param dtlAttrb11 String
     */
    public void setDtlAttrb11(String dtlAttrb11) {
        this.dtlAttrb11 = dtlAttrb11;
    }

    /**
     * @param dtlAttrb12 String
     */
    public void setDtlAttrb12(String dtlAttrb12) {
        this.dtlAttrb12 = dtlAttrb12;
    }

    /**
     * @param dtlAttrb13 String
     */
    public void setDtlAttrb13(String dtlAttrb13) {
        this.dtlAttrb13 = dtlAttrb13;
    }

    /**
     * @param dtlAttrb14 String
     */
    public void setDtlAttrb14(String dtlAttrb14) {
        this.dtlAttrb14 = dtlAttrb14;
    }

    /**
     * @param dtlAttrb15 String
     */
    public void setDtlAttrb15(String dtlAttrb15) {
        this.dtlAttrb15 = dtlAttrb15;
    }

    /**
     * @param dtlAttrb16 String
     */
    public void setDtlAttrb16(String dtlAttrb16) {
        this.dtlAttrb16 = dtlAttrb16;
    }

    /**
     * @param dtlAttrb17 String
     */
    public void setDtlAttrb17(String dtlAttrb17) {
        this.dtlAttrb17 = dtlAttrb17;
    }

    /**
     * @param dtlAttrb18 String
     */
    public void setDtlAttrb18(String dtlAttrb18) {
        this.dtlAttrb18 = dtlAttrb18;
    }

    /**
     * @param dtlAttrb19 String
     */
    public void setDtlAttrb19(String dtlAttrb19) {
        this.dtlAttrb19 = dtlAttrb19;
    }

    /**
     * @param dtlAttrb20 String
     */
    public void setDtlAttrb20(String dtlAttrb20) {
        this.dtlAttrb20 = dtlAttrb20;
    }

    /**
     * @param dtlAttrb21 String
     */
    public void setDtlAttrb21(String dtlAttrb21) {
        this.dtlAttrb21 = dtlAttrb21;
    }

    /**
     * @param dtlAttrb22 String
     */
    public void setDtlAttrb22(String dtlAttrb22) {
        this.dtlAttrb22 = dtlAttrb22;
    }

    /**
     * @param dtlAttrb23 String
     */
    public void setDtlAttrb23(String dtlAttrb23) {
        this.dtlAttrb23 = dtlAttrb23;
    }

    /**
     * @param style01 String
     */
    public void setStyle01(String style01) {
        this.style01 = style01;
    }

    /**
     * @return crLimitAmt
     */
    public BigDecimal getCrLimitAmt() {
        return crLimitAmt;
    }

    /**
     * @param crLimitAmt BigDecimal
     */
    public void setCrLimitAmt(BigDecimal crLimitAmt) {
        this.crLimitAmt = crLimitAmt;
    }

    /**
     * @return ordAmt
     */
    public BigDecimal getOrdTotAmt() {
        return ordTotAmt;
    }

    /**
     * @param ordTotAmt BigDecimal
     */
    public void setOrdTotAmt(BigDecimal ordTotAmt) {
        this.ordTotAmt = ordTotAmt;
    }

    /**
     * @return ordProcNodePrflEquipFlg
     */
    public String getOrdProcNodePrflEquipFlg() {
        return ordProcNodePrflEquipFlg;
    }

    /**
     * @param ordProcNodePrflEquipFlg String
     */
    public void setOrdProcNodePrflEquipFlg(String ordProcNodePrflEquipFlg) {
        this.ordProcNodePrflEquipFlg = ordProcNodePrflEquipFlg;
    }

    /**
     * @return billToCustCd
     */
    public String getBillToCustCd() {
        return billToCustCd;
    }

    /**
     * @return billToCustAcctCd
     */
    public String getBillToCustAcctCd() {
        return billToCustAcctCd;
    }

    /**
     * @param billToCustCd String
     */
    public void setBillToCustCd(String billToCustCd) {
        this.billToCustCd = billToCustCd;
    }

    /**
     * @param billToCustAcctCd String
     */
    public void setBillToCustAcctCd(String billToCustAcctCd) {
        this.billToCustAcctCd = billToCustAcctCd;
    }


}
