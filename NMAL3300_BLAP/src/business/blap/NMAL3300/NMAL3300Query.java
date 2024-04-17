/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.blap.NMAL3300;

import static business.blap.NMAL3300.constant.NMAL3300Constant.ATT_BIZ_APL_ID_ACCT;
import static business.blap.NMAL3300.constant.NMAL3300Constant.ATT_BIZ_APL_ID_LOC;
import static business.blap.NMAL3300.constant.NMAL3300Constant.CUST_SPCL_INSTN_CTX_TP_BIZ_AREA;
import static business.blap.NMAL3300.constant.NMAL3300Constant.CUST_SPCL_INSTN_CTX_TP_INSTRUCTION;
import static business.blap.NMAL3300.constant.NMAL3300Constant.SELECT_CNT_SORT_NUM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CUST_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;


/**
 *<pre>
 *  Account Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/07   Fujitsu         N.Sugiura       Create          N/A
 * 2016/02/24   SRAA            Y.Chen          Update          QC#4482
 * 2016/02/24   Fujitsu         H.Ikeda         Update          QC#2823
 * 2016/04/06   SRAA            Y.Chen          Update          QC#5585
 * 2018/07/10   Fujitsu         T.Noguchi       Update          QC#26661
 * 2018/11/12   Fujitsu         Hd.Sugawara     Update          QC#28683
 *</pre>
 */
public final class NMAL3300Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL3300Query MYINSTANCE = new NMAL3300Query();

    /**
     * Constructor.
     */
    private NMAL3300Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL6710Query
     */
    public static NMAL3300Query getInstance() {
        return MYINSTANCE;
    }

    /**
     * @param shipToCustCd String
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLocNumFromShip(String shipToCustCd, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("shipToCustCd", shipToCustCd);

        return getSsmEZDClient().queryObjectList("getLocNumFromShipTo", ssmParam);

    }
    /**
     * @param billToCustCd String
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLocNumFromBill(String billToCustCd, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("billToCustCd", billToCustCd);

        return getSsmEZDClient().queryObjectList("getLocNumFromBillTo", ssmParam);

    }
    /**
     * @param dsAcctNum String
     * @param glblCmpyCd String
     * @return Map<String, String>
     */
    public S21SsmEZDResult getAcctNum(String dsAcctNum, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("dsAcctNum", dsAcctNum);

        return getSsmEZDClient().queryObjectList("getAcctNum", ssmParam);

    }

    // Del Start 2018/11/12 QC#28683
//    /**
//     * @param cMsg NMAL3300CMsg
//     * @param dsAcctNum String
//     * @param glblCmpyCd String
//     * @return Map<String, String>
//     */
//    public S21SsmEZDResult getCustSpclInstnForAcct(NMAL3300CMsg cMsg, String dsAcctNum, String glblCmpyCd) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("searchKey", dsAcctNum);
//        ssmParam.put("selectAcctFlg", ZYPConstant.FLG_ON_Y);
//        ssmParam.put("selectLocFlg", ZYPConstant.FLG_OFF_N);
//        ssmParam.put("custSpclInstnCtxTpCd", CUST_SPCL_INSTN_CTX_TP_TRX_TP);
//        ssmParam.put("cMsg", cMsg);
//
//        return getSsmEZDClient().queryObjectList("getCustSpclInstn", ssmParam);
//
//    }
//    /**
//     * @param cMsg NMAL3300CMsg
//     * @param locNum String
//     * @param glblCmpyCd String
//     * @return Map<String, String>
//     */
//    public S21SsmEZDResult getCustSpclInstnForLoc(NMAL3300CMsg cMsg, String locNum, String glblCmpyCd) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("searchKey", locNum);
//        ssmParam.put("selectAcctFlg", ZYPConstant.FLG_OFF_N);
//        ssmParam.put("selectLocFlg", ZYPConstant.FLG_ON_Y);
//        ssmParam.put("custSpclInstnCtxTpCd", CUST_SPCL_INSTN_CTX_TP_TRX_TP);
//        ssmParam.put("cMsg", cMsg);
//
//        return getSsmEZDClient().queryObjectList("getCustSpclInstn", ssmParam);
//
//    }
    // Del End 2018/11/12 QC#28683

    /**
     * chkHrchEffLvlTp
     * @param dsAcctNum String
     * @param glblCmpyCd String
     * @return Map<String, String>
     */
    public S21SsmEZDResult chkHrchEffLvlTp(String dsAcctNum, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);

        return getSsmEZDClient().queryObject("cntHrchEffLvlTp", ssmParam);

    }
    
    // QC#5585
    // Del Start 2018/11/12 QC#28683
//    /**
//     * getAcctRefAttrb
//     * @param dsAcctNum String
//     * @param glblCmpyCd String
//     * @return Map<String, String>
//     */
//    public S21SsmEZDResult getAcctRefAttrbForLoc(String locNum, String glblCmpyCd) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("locNum", locNum);
//
//        return getSsmEZDClient().queryObjectList("getAcctRefAttrb", ssmParam);
//    }
//    
//    /**
//     * getAcctRefAttrb
//     * @param dsAcctNum String
//     * @param glblCmpyCd String
//     * @return Map<String, String>
//     */
//    public S21SsmEZDResult getAcctRefAttrbForAcct(String dsAcctNum, String glblCmpyCd) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("dsAcctNum", dsAcctNum);
//
//        return getSsmEZDClient().queryObjectList("getAcctRefAttrb", ssmParam);
//    }
//    /**
//     * getSpclHdlg
//     * @param dsAcctNum String
//     * @param glblCmpyCd String
//     * @return Map<String, String>
//     */
//    public S21SsmEZDResult getSpclHdlg(NMAL3300CMsg cMsg, String dsAcctNum, String glblCmpyCd) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("dsAcctNum", dsAcctNum);
//        ssmParam.put("cMsg", cMsg);
//
//        return getSsmEZDClient().queryObjectList("getSpclHdlg", ssmParam);
//    }
    // Del End 2018/11/12 QC#28683

    /**
     * getCustSpclMsgForAcct
     * @param dsAcctNum String
     * @param glblCmpyCd String
     * @return Map<String, String>
     */
    public S21SsmEZDResult getCustSpclMsgForAcct(NMAL3300CMsg cMsg, String dsAcctNum, String glblCmpyCd, String slsDt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("searchKey", dsAcctNum);
        ssmParam.put("custMsgTpCd", DS_CUST_MSG_TP.NOTE);
        ssmParam.put("selectAcctFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("selectLocFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("custSpclInstnCtxTpCd", CUST_SPCL_INSTN_CTX_TP_BIZ_AREA);
        // 2018/07/10 QC#26661 Add Start
        ssmParam.put("lineBizTpAll", LINE_BIZ_TP.ALL);
        // 2018/07/10 QC#26661 Add End
        ssmParam.put("cMsg", cMsg);
        // QC#4482
        setParamAttBizAplId(ssmParam);

        return getSsmEZDClient().queryObjectList("getCustSpclMsg", ssmParam);

    }
    /**
     * getCustSpclMsgForLoc
     * @param locNum String
     * @param glblCmpyCd String
     * @return Map<String, String>
     */
    public S21SsmEZDResult getCustSpclMsgForLoc(NMAL3300CMsg cMsg, String locNum, String glblCmpyCd, String slsDt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("searchKey", locNum);
        ssmParam.put("custMsgTpCd", DS_CUST_MSG_TP.NOTE);
        ssmParam.put("selectAcctFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("selectLocFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("custSpclInstnCtxTpCd", CUST_SPCL_INSTN_CTX_TP_BIZ_AREA);
        ssmParam.put("cMsg", cMsg);
        // 2018/07/10 QC#26661 Add Start
        ssmParam.put("lineBizTpAll", LINE_BIZ_TP.ALL);
        // 2018/07/10 QC#26661 Add End
        // QC#4482
        setParamAttBizAplId(ssmParam);

        return getSsmEZDClient().queryObjectList("getCustSpclMsg", ssmParam);
    }
    
    private void setParamAttBizAplId(Map<String, Object> ssmParam) {
        List<String> list = new ArrayList<String>();
        list.add(ATT_BIZ_APL_ID_ACCT);
        list.add(ATT_BIZ_APL_ID_LOC);
        ssmParam.put("attBizAplIds", list);
    }
    
    /**
     * getLobTypeWithMessageType
     * @param glblCmpyCd String
     * @return Map<String, String>
     */
    public S21SsmEZDResult getLobTypeWithMessageType(String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("custMsgTpCd", DS_CUST_MSG_TP.NOTE);

        return getSsmEZDClient().queryObjectList("getLobTypeWithMessageType", ssmParam);

    }

    // Del Start 2018/11/12 QC#28683
//    /**
//     * chkDisplayForTrxDriver
//     * @param cMsg NMAL3300CMsg
//     * @param glblCmpyCd String
//     * @return Map<String, String>
//     */
//    public S21SsmEZDResult chkDisplayForTrxDriver(NMAL3300CMsg cMsg, String glblCmpyCd) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("custSpclInstnCtxTpCd", CUST_SPCL_INSTN_CTX_TP_TRANSACTION);
//        ssmParam.put("cMsg", cMsg);
//
//        return getSsmEZDClient().queryObject("cntDisplay", ssmParam);
//
//    }
//    /**
//     * getDisplayAvalCdForTrxDriver
//     * @param sMsg NMAL3300SMsg
//     * @param glblCmpyCd String
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getDisplayAvalCdForTrxDriver(NMAL3300SMsg sMsg, String glblCmpyCd) {
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("custSpclInstnCtxTpCd", CUST_SPCL_INSTN_CTX_TP_TRANSACTION);
//        return getSsmEZDClient().queryEZDMsg("getDisplayAvalCd", ssmParam, sMsg);
//    }
//    /**
//     * getDisplayAvalFlgForTrxDriver
//     * @param cMsg NMAL3300CMsg
//     * @param sMsg NMAL3300SMsg
//     * @param glblCmpyCd String
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getDisplayAvalFlgForTrxDriver(NMAL3300CMsg cMsg, NMAL3300SMsg sMsg, String glblCmpyCd) {
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("custSpclInstnCtxTpCd", CUST_SPCL_INSTN_CTX_TP_TRANSACTION);
//        ssmParam.put("cMsg", cMsg);
//
//        return getSsmEZDClient().queryEZDMsg("getDisplayAvalFlg", ssmParam, sMsg);
//    }
//
//    /**
//     * chkDisplayForReference
//     * @param cMsg NMAL3300CMsg
//     * @param glblCmpyCd String
//     * @return Map<String, String>
//     */
//    public S21SsmEZDResult chkDisplayForReference(NMAL3300CMsg cMsg, String glblCmpyCd) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("custSpclInstnCtxTpCd", CUST_SPCL_INSTN_CTX_TP_REFERENCE);
//        ssmParam.put("cMsg", cMsg);
//
//        return getSsmEZDClient().queryObject("cntDisplay", ssmParam);
//
//    }
//    /**
//     * getDisplayAvalCdForReference
//     * @param sMsg NMAL3300SMsg
//     * @param glblCmpyCd String
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getDisplayAvalCdForReference(NMAL3300SMsg sMsg, String glblCmpyCd) {
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("custSpclInstnCtxTpCd", CUST_SPCL_INSTN_CTX_TP_REFERENCE);
//
//        return getSsmEZDClient().queryEZDMsg("getDisplayAvalCd", ssmParam, sMsg);
//    }
//    /**
//     * getDisplayAvalFlgForReference
//     * @param cMsg NMAL3300CMsg
//     * @param sMsg NMAL3300SMsg
//     * @param glblCmpyCd String
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getDisplayAvalFlgForReference(NMAL3300CMsg cMsg, NMAL3300SMsg sMsg, String glblCmpyCd) {
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("custSpclInstnCtxTpCd", CUST_SPCL_INSTN_CTX_TP_REFERENCE);
//        ssmParam.put("cMsg", cMsg);
//
//        return getSsmEZDClient().queryEZDMsg("getDisplayAvalFlg", ssmParam, sMsg);
//    }
//    /**
//     * chkDisplayForHandling
//     * @param cMsg NMAL3300CMsg
//     * @param glblCmpyCd String
//     * @return Map<String, String>
//     */
//    public S21SsmEZDResult chkDisplayForHandling(NMAL3300CMsg cMsg, String glblCmpyCd) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("custSpclInstnCtxTpCd", CUST_SPCL_INSTN_CTX_TP_HANDLING);
//        ssmParam.put("cMsg", cMsg);
//
//        return getSsmEZDClient().queryObject("cntDisplay", ssmParam);
//
//    }
//    /**
//     * getDisplayAvalCdForHandling
//     * @param sMsg NMAL3300SMsg
//     * @param glblCmpyCd String
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getDisplayAvalCdForHandling(NMAL3300SMsg sMsg, String glblCmpyCd) {
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("custSpclInstnCtxTpCd", CUST_SPCL_INSTN_CTX_TP_HANDLING);
//
//        return getSsmEZDClient().queryEZDMsg("getDisplayAvalCd", ssmParam, sMsg);
//    }
//    /**
//     * getDisplayAvalFlgForHandling
//     * @param cMsg NMAL3300CMsg
//     * @param sMsg NMAL3300SMsg
//     * @param glblCmpyCd String
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getDisplayAvalFlgForHandling(NMAL3300CMsg cMsg, NMAL3300SMsg sMsg, String glblCmpyCd) {
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("custSpclInstnCtxTpCd", CUST_SPCL_INSTN_CTX_TP_HANDLING);
//        ssmParam.put("cMsg", cMsg);
//
//        return getSsmEZDClient().queryEZDMsg("getDisplayAvalFlg", ssmParam, sMsg);
//    }
    // Del End 2018/11/12 QC#28683

    /**
     * chkDisplayForInstruction
     * @param cMsg NMAL3300CMsg
     * @param glblCmpyCd String
     * @return Map<String, String>
     */
    public S21SsmEZDResult chkDisplayForInstruction(NMAL3300CMsg cMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("custSpclInstnCtxTpCd", CUST_SPCL_INSTN_CTX_TP_INSTRUCTION);
        ssmParam.put("cMsg", cMsg);

        return getSsmEZDClient().queryObject("cntDisplay", ssmParam);

    }
    /**
     * getDisplayAvalCdForInstruction
     * @param sMsg NMAL3300SMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDisplayAvalCdForInstruction(NMAL3300SMsg sMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("custSpclInstnCtxTpCd", CUST_SPCL_INSTN_CTX_TP_INSTRUCTION);

        return getSsmEZDClient().queryEZDMsg("getDisplayAvalCd", ssmParam, sMsg);
    }
    /**
     * getDisplayAvalFlgForInstruction
     * @param cMsg NMAL3300CMsg
     * @param sMsg NMAL3300SMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDisplayAvalFlgForInstruction(NMAL3300CMsg cMsg, NMAL3300SMsg sMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("custSpclInstnCtxTpCd", CUST_SPCL_INSTN_CTX_TP_INSTRUCTION);
        ssmParam.put("cMsg", cMsg);

        return getSsmEZDClient().queryEZDMsg("getDisplayAvalFlg", ssmParam, sMsg);
    }

    // Del Start 2018/11/12 QC#28683
//    /**
//     * getLvlSortForTransaction
//     * @param cMsg NMAL3300CMsg
//     * @param sMsg NMAL3300SMsg
//     * @param glblCmpyCd String
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getLvlSortForTransaction(NMAL3300CMsg cMsg, NMAL3300SMsg sMsg, String glblCmpyCd) {
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("custSpclInstnCtxTpCd", CUST_SPCL_INSTN_CTX_TP_TRANSACTION);
//        ssmParam.put("cMsg", cMsg);
//
//        return getSsmEZDClient().queryEZDMsg("getLvlSort", ssmParam, sMsg);
//    }
    // Del End 2018/11/12 QC#28683
    // 2016/2/25 QC#2823 ADD Start
    /**
     * getSortNum
     * 
     * @param glblCmpyCd String
     * @param custSpclInstnCtxCd String
     * @param funcId String
     * @param funcCatgId String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSortNum(String glblCmpyCd, String custSpclInstnCtxCd, String funcId, String funcCatgId) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("custSpclInstnCtxCd", custSpclInstnCtxCd);
        ssmParam.put("funcMstrId", funcId);
        ssmParam.put("funcMstrIdDescTxt", funcCatgId);
        ssmParam.put("rowNum", SELECT_CNT_SORT_NUM);

        return getSsmEZDClient().queryObject("getSortNum", ssmParam);
    }
    
    /**
     * getSortDefNum
     * 
     * @param glblCmpyCd String
     * @param custSpclInstnCtxCd String
     * @param funcId String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSortDefNum(String glblCmpyCd, String custSpclInstnCtxCd, String funcId) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("custSpclInstnCtxTpCd", custSpclInstnCtxCd);
        ssmParam.put("mstrFuncId", funcId);
        ssmParam.put("rowNum", SELECT_CNT_SORT_NUM);

        return getSsmEZDClient().queryObject("getSortDefNum", ssmParam);
    }
    // 2016/2/25 QC#2823 ADD End
}
