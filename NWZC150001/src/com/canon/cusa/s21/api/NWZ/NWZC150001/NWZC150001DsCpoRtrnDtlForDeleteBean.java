/**<pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>*/
package com.canon.cusa.s21.api.NWZ.NWZC150001;

import java.math.BigDecimal;

import parts.common.EZDMsg;
import business.parts.NWZC150001_rtnDtlPMsg;


/**
 * <pre>
 * DS CPO Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/06   Fujitsu         S.Takami        Create          S21_NA#7821
 * 2018/06/05   Fujitsu         S.Takami        Update          S21_NA#25151
 * </pre>
 */
public class NWZC150001DsCpoRtrnDtlForDeleteBean {

    /** Current (On Database) DS_ORD_POSN_NUM */
    private String curDsOrdPosnNum;

    /** Current (On Database) Cpo Detail Line Num  */
    private String curCpoDtlLineNum;

    /** Current (On Database) Cpo Detail Line Sub Num  */
    private String curCpoDtlLineSubNum;

    /** Current (On Database) DS_CPO_LINE_NUM */
    private BigDecimal curDsCpoLineNum;

    /** Current (On Database) DS_CPO_LINE__SUB_NUM */
    private BigDecimal curDsCpoLineSubNum;

    // 2018/06/05 S21_NA#25151 Add Start
    /**  Modified Flag */
    private boolean isModified = false;
    // 2018/06/05 S21_NA#25151 Add End

    /** DS CPO Update API parameter: rtnDtlPMsg */
    private NWZC150001_rtnDtlPMsg rtnDtlPMsg;

    /**
     * Constructor
     * @param rtnDtlPMsg return detail PMsg
     */
    public NWZC150001DsCpoRtrnDtlForDeleteBean(NWZC150001_rtnDtlPMsg rtnDtlPMsg) {

        this.rtnDtlPMsg = new NWZC150001_rtnDtlPMsg();
        EZDMsg.copy(rtnDtlPMsg, null, this.rtnDtlPMsg, null);
    }

    /**
     * get current ds_ord_posn_num
     * @return current ds_ord_posn_num
     */
    public String getCurDsOrdPosnNum() {
        return curDsOrdPosnNum;
    }

    /**
     * set current ds_ord_posn_num
     * @param curDsOrdPosnNum current ds ord position number
     */
    public void setCurDsOrdPosnNum(String curDsOrdPosnNum) {
        this.curDsOrdPosnNum = curDsOrdPosnNum;
    }

    /**
     * get current cpo detail line num
     * @return current cpo detail line num
     */
    public String getCurCpoDtlLineNum() {
        return curCpoDtlLineNum;
    }

    /**
     * set current cpo detail line num
     * @param curCpoDtlLineNum current cpo detail line num
     */
    public void setCurCpoDtlLineNum(String curCpoDtlLineNum) {
        this.curCpoDtlLineNum = curCpoDtlLineNum;
    }

    /**
     * get current cpo detail line Sub num
     * @return current cpo detail line Sub num
     */
    public String getCurCpoDtlLineSubNum() {
        return curCpoDtlLineSubNum;
    }

    /**
     * set current cpo detail line num
     * @param curCpoDtlLineNum current cpo detail line num
     */
    public void setCurCpoDtlLineSubNum(String curCpoDtlLineSubNum) {
        this.curCpoDtlLineSubNum = curCpoDtlLineSubNum;
    }

    /**
     * get current ds cpo line num
     * @return current ds cpo line num
     */
    public BigDecimal getCurDsCpoLineNum() {
        return curDsCpoLineNum;
    }

    /**
     * set current ds cpo line num
     * @param curDsCpoLineNum current ds cpo line num
     */
    public void setCurDsCpoLineNum(BigDecimal curDsCpoLineNum) {
        this.curDsCpoLineNum = curDsCpoLineNum;
    }

    /**
     * get current ds cpo line sub num
     * @return current ds cpo line sub num
     */
    public BigDecimal getCurDsCpoLineSubNum() {
        return curDsCpoLineSubNum;
    }

    /**
     * set current ds cpo line sub num
     * @param curDsCpoLineNum current ds cpo line sub num
     */
    public void setCurDsCpoLineSubNum(BigDecimal curDsCpoLineSubNum) {
        this.curDsCpoLineSubNum = curDsCpoLineSubNum;
    }

    /**
     * get return detail pmsg
     * @return return detail pmsg
     */
    public NWZC150001_rtnDtlPMsg getRtnDtlPMsg() {
        return rtnDtlPMsg;
    }

    // 2018/06/05 S21_NA#25151 Add Start
    /**
     * @return Modified Flag
     */
    public boolean isModified() {
        return isModified;
    }

    /**
     * @param isModifiedPos Modified Position
     */
    public void setModified(boolean isModified) {
        this.isModified = isModified;
    }
    // 2018/06/05 S21_NA#25151 Add End
}
