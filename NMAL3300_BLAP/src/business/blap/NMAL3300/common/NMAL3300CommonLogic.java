/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.blap.NMAL3300.common;

import static business.blap.NMAL3300.constant.NMAL3300Constant.CUST_SPCL_INSTN_CTX_TP_INSTRUCTION;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL3300.NMAL3300CMsg;
import business.blap.NMAL3300.NMAL3300Query;
import business.blap.NMAL3300.NMAL3300SMsg;
import business.blap.NMAL3300.NMAL3300_DSMsg;
import business.blap.NMAL3300.NMAL3300_DSMsgArray;
import business.blap.NMAL3300.NMAL3300_RSMsg;
import business.db.HRCH_EFF_LVL_TPTMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_InstructionListPMsg;
import business.parts.NMZC610001_InstructionListPMsgArray;
import business.parts.NMZC610001_RelatedBillShipListPMsg;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HRCH_EFF_LVL_TP;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
/**
 *<pre>
 *  Account Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/07   Fujitsu         N.Sugiura       Create          N/A
 * 2016/02/24   SRAA            Y.Chen          Update          QC#4482
 * 2016/02/25   Fujitsu         H.Ikeda         Update          QC#2823
 * 2016/04/06   SRAA            Y.Chen          Update          QC#5585
 * 2018/07/11   Fujitsu         T.Noguchi       Update          QC#26713
 * 2018/11/12   Fujitsu         Hd.Sugawara     Update          QC#28683
 *</pre>
 */
public class NMAL3300CommonLogic {

    /**
     * getHrchEffLvlTpCd
     * @param dsAcctRelnTpCd String
     * @param relnDsAcctNum relnDsAcctNumString
     * @param glblCmpyCd String
     * @return String
     */
    public static String getHrchEffLvlTpCd(String dsAcctRelnTpCd, String relnDsAcctNum, String glblCmpyCd) {
        String hrchEffLvlTpCd = "";

        if (!ZYPCommonFunc.hasValue(dsAcctRelnTpCd)) {
            hrchEffLvlTpCd = HRCH_EFF_LVL_TP.ACCT;
        } else {
            S21SsmEZDResult rslt = NMAL3300Query.getInstance().chkHrchEffLvlTp(relnDsAcctNum, glblCmpyCd);
            Integer cnt = (Integer) rslt.getResultObject();
            if (cnt > 0) {
                hrchEffLvlTpCd = HRCH_EFF_LVL_TP.PARENT;
            } else {
                hrchEffLvlTpCd = HRCH_EFF_LVL_TP.TOP;
            }
        }
        return hrchEffLvlTpCd;
    }
    /**
     * getHrchEffLvlTpNm
     * @param hrchEffLvlTpCd String
     * @param glblCmpyCd String
     * @return String
     */
    public static String getHrchEffLvlTpNm(String hrchEffLvlTpCd, String glblCmpyCd) {

        HRCH_EFF_LVL_TPTMsg hrchEffLvlTpTMsg = new HRCH_EFF_LVL_TPTMsg();
        ZYPEZDItemValueSetter.setValue(hrchEffLvlTpTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(hrchEffLvlTpTMsg.hrchEffLvlTpCd, hrchEffLvlTpCd);
        hrchEffLvlTpTMsg = (HRCH_EFF_LVL_TPTMsg) EZDTBLAccessor.findByKey(hrchEffLvlTpTMsg);
        if (hrchEffLvlTpTMsg == null) {
            return "";
        }
        return hrchEffLvlTpTMsg.hrchEffLvlTpNm.getValue();
    }
    /**
     * setSearchRow
     * @param bizMsg NMAL3300CMsg
     */
    public static void setSearchRow(NMAL3300CMsg bizMsg) {

        for (int i = 0; i < bizMsg.locNum_01.length(); i++) {
            if (bizMsg.locNum_D.getValue().equals(bizMsg.locNum_01.no(i).getValue())) {
                bizMsg.S.setValidCount(i);

            }
        }
    }
    /**
     * getSearchRec
     * @param bizMsg NMAL3300CMsg
     * @param sharedMsg NMAL3300SMsg
     */
    public static void getSearchRec(NMAL3300CMsg bizMsg, NMAL3300SMsg sharedMsg) {
        int idx = bizMsg.S.getValidCount();
        int searchCnt = 0;
        String acctNum = bizMsg.S.no(idx).dsAcctNum_S2.getValue();
        String glblCmpyCd = bizMsg.glblCmpyCd_S.getValue();
        sharedMsg.R.clear();
        if (ZYPCommonFunc.hasValue(bizMsg.S.no(idx).locNum_S2)) {
            ZYPEZDItemValueSetter.setValue(sharedMsg.R.no(searchCnt).locNum_R1, bizMsg.S.no(idx).locNum_S2.getValue());
            ZYPEZDItemValueSetter.setValue(sharedMsg.R.no(searchCnt).hrchEffLvlTpCd_R1, HRCH_EFF_LVL_TP.LOC);
            ZYPEZDItemValueSetter.setValue(sharedMsg.R.no(searchCnt).hrchEffLvlTpNm_R1, NMAL3300CommonLogic.getHrchEffLvlTpNm(sharedMsg.R.no(searchCnt).hrchEffLvlTpCd_R1.getValue(), glblCmpyCd));

            searchCnt++;

        }

        final NMZC610001 custInfoGetApi = new NMZC610001();

        // Create Api Parameter
        NMZC610001PMsg custInfoGetApiPMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.xxModeCd, "07");
        ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.xxChildRelnFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.dsAcctNum_I1, acctNum);

        // Call Api
        custInfoGetApi.execute(custInfoGetApiPMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(custInfoGetApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(custInfoGetApiPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);

                if (msgId.endsWith("E")) {
                    bizMsg.locNum_D.setErrorInfo(1, msgId, msgPrms);
                }
            }
        }
        String duplChkNum = "";
        String duplTgtNum = "";
        for (int i = 0; i < custInfoGetApiPMsg.RelatedBillShipList.getValidCount(); i++) {
            if (searchCnt >= sharedMsg.R.length()) {
                break;
            }
            NMZC610001_RelatedBillShipListPMsg inPmsg = custInfoGetApiPMsg.RelatedBillShipList.no(i);
            duplTgtNum = S21StringUtil.concatStrings(inPmsg.dsAcctRelnTpCd.getValue(), inPmsg.dsAcctNum.getValue(), inPmsg.relnDsAcctNum.getValue());
            if (!duplChkNum.equals(duplTgtNum)) {
                if (!(ZYPCommonFunc.hasValue(inPmsg.dsAcctRelnTpCd)
                        && DS_ACCT_RELN_TP.RELATED_ACCOUNT.equals(inPmsg.dsAcctRelnTpCd.getValue())
                            || DS_ACCT_RELN_TP.LEASE_ACCOUNT.equals(inPmsg.dsAcctRelnTpCd.getValue()))) {
                    ZYPEZDItemValueSetter.setValue(sharedMsg.R.no(searchCnt).dsAcctRelnTpCd_R1, inPmsg.dsAcctRelnTpCd.getValue());
                    ZYPEZDItemValueSetter.setValue(sharedMsg.R.no(searchCnt).dsAcctNum_R1, inPmsg.dsAcctNum.getValue());
                    ZYPEZDItemValueSetter.setValue(sharedMsg.R.no(searchCnt).relnDsAcctNum_R1, inPmsg.relnDsAcctNum.getValue());
                    ZYPEZDItemValueSetter.setValue(sharedMsg.R.no(searchCnt).hrchEffLvlTpCd_R1, NMAL3300CommonLogic.getHrchEffLvlTpCd(inPmsg.dsAcctRelnTpCd.getValue(), inPmsg.relnDsAcctNum.getValue(), glblCmpyCd));
                    ZYPEZDItemValueSetter.setValue(sharedMsg.R.no(searchCnt).hrchEffLvlTpNm_R1, NMAL3300CommonLogic.getHrchEffLvlTpNm(sharedMsg.R.no(searchCnt).hrchEffLvlTpCd_R1.getValue(), glblCmpyCd));

                    searchCnt++;

                }
                duplChkNum = duplTgtNum;
            }
        }
        sharedMsg.R.setValidCount(searchCnt);

        // Display / non-display setting
        // Del Start 2018/11/12 QC#28683
        //setTransactionDisplayFlg(bizMsg, sharedMsg, glblCmpyCd);
        //setReferenceDisplayFlg(bizMsg, sharedMsg, glblCmpyCd);
        //setHandlingDisplayFlg(bizMsg, sharedMsg, glblCmpyCd);
        // Del End 2018/11/12 QC#28683
        setInstructionDisplayFlg(bizMsg, sharedMsg, glblCmpyCd);

        S21SsmEZDResult acctRslt = NMAL3300Query.getInstance().getAcctNum(acctNum, glblCmpyCd);
        if (acctRslt.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) acctRslt.getResultObject();
            Map<String, String> map = (Map<String, String>) resultList.get(0);
            ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_D, map.get("DS_ACCT_NM"));
        }
    }
    // 2016/2/25 QC#2823 ADD Start
    private static void getSortNum(NMAL3300CMsg bizMsg, NMAL3300SMsg sharedMsg, String glblCmpyCd, String custSpclInstnCtxCd){
        S21SsmEZDResult rslt = null;
        rslt = NMAL3300Query.getInstance().getSortNum(glblCmpyCd, custSpclInstnCtxCd, bizMsg.funcMstrId_S.getValue(), bizMsg.funcMstrIdDescTxt_S.getValue());
        Map<String, Object> resulMap = (Map<String, Object>) rslt.getResultObject();
        if (resulMap != null && resulMap.size() != 0) {
            String sortItem = getString(resulMap, "FIRST_DATA");
            // 2018/07/11 QC#26713 Mod Start
            // String sortNum = getString(resulMap, "FIRST_NUM");
            String sortNum = getBigDecimal(resulMap, "FIRST_NUM").toString();
            // 2018/07/11 QC#26713 Mod End
            if (!ZYPCommonFunc.hasValue(sortItem)) {
                getSortDefNum(bizMsg, sharedMsg, glblCmpyCd, custSpclInstnCtxCd);
            } else {
                if (ZYPCommonFunc.hasValue(sortNum)) {
                    if (setSortNum(sortItem, sortNum, sharedMsg)) {
                        return;
                    }
                    // 2018/07/11 QC#26713 Mod Start
                    // if (setSortNum(getString(resulMap, "SCD_DATA"), getString(resulMap, "SCD_NUM"), sharedMsg)) {
                    if (setSortNum(getString(resulMap, "SCD_DATA"), getBigDecimal(resulMap, "SCD_NUM").toString(), sharedMsg)) {
                    // 2018/07/11 QC#26713 Mod End
                        return;
                    }
                    // 2018/07/11 QC#26713 Mod Start
                    // if (setSortNum(getString(resulMap, "THIRD_NUM"), getString(resulMap, "THIRD_NUM"), sharedMsg)) {
                    if (setSortNum(getString(resulMap, "THIRD_DATA"), getBigDecimal(resulMap, "THIRD_NUM").toString(), sharedMsg)) {
                    // 2018/07/11 QC#26713 Mod End
                        return;
                    }
                    // 2018/07/11 QC#26713 Mod Start
                    // if (setSortNum(getString(resulMap, "FRTH_DATA"), getString(resulMap, "FRTH_NUM"), sharedMsg)) {
                    if (setSortNum(getString(resulMap, "FRTH_DATA"), getBigDecimal(resulMap, "FRTH_NUM").toString(), sharedMsg)) {
                    // 2018/07/11 QC#26713 Mod End
                        return;
                    }
                } else {
                    getSortDefNum(bizMsg, sharedMsg, glblCmpyCd, custSpclInstnCtxCd);
                }
                
            }
        } else {
            getSortDefNum(bizMsg, sharedMsg, glblCmpyCd, custSpclInstnCtxCd);
        }
    }

    private static void getSortDefNum(NMAL3300CMsg bizMsg, NMAL3300SMsg sharedMsg, String glblCmpyCd, String custSpclInstnCtxCd){
        S21SsmEZDResult rslt = null;
        rslt = NMAL3300Query.getInstance().getSortDefNum(glblCmpyCd, custSpclInstnCtxCd, bizMsg.funcMstrId_S.getValue());
        Map<String, Object> resultMap = (Map<String, Object>) rslt.getResultObject();
        if (resultMap != null && resultMap.size() != 0) {

            String sortItem = getString(resultMap, "FIRST_DEF_DATA");
            String sortNum = getString(resultMap, "FIRST_DEF_NUM");
            if (!ZYPCommonFunc.hasValue(sortItem)) {
                return;
            } else {
                if (ZYPCommonFunc.hasValue(sortNum)) {
                    if (setSortNum(sortItem, sortNum, sharedMsg)) {
                        return;
                    }
                    if (setSortNum(getString(resultMap, "SCD_DEF_DATA"), getString(resultMap, "SCD_DEF_NUM"), sharedMsg)) {
                        return;
                    }
                    if (setSortNum(getString(resultMap, "THIRD_DEF_DATA"), getString(resultMap, "THIRD_DEF_NUM"), sharedMsg)) {
                        return;
                    }
                    if (setSortNum(getString(resultMap, "FRTH_DEF_DATA"), getString(resultMap, "FRTH_DEF_NUM"), sharedMsg)) {
                        return;
                    }
                }
            }
        }
    }

    private static boolean setSortNum(String sortItem, String sortNum, NMAL3300SMsg sharedMsg){
        boolean rtnFlg = false;
        if (!ZYPCommonFunc.hasValue(sortItem)) {
            rtnFlg = true;
        } else {
            if (ZYPCommonFunc.hasValue(sortNum)) {
                if (HRCH_EFF_LVL_TP.LOC.equals(sortItem)) {
                    ZYPEZDItemValueSetter.setValue(sharedMsg.firstBizCtxAttrbNum_B, new BigDecimal(sortNum));
                    rtnFlg = false;
                } else if (HRCH_EFF_LVL_TP.ACCT.equals(sortItem)) {
                    ZYPEZDItemValueSetter.setValue(sharedMsg.scdBizCtxAttrbNum_B, new BigDecimal(sortNum));
                    rtnFlg = false;
                } else if (HRCH_EFF_LVL_TP.PARENT.equals(sortItem)) {
                    ZYPEZDItemValueSetter.setValue(sharedMsg.thirdBizCtxAttrbNum_B, new BigDecimal(sortNum));
                    rtnFlg = false;
                } else if (HRCH_EFF_LVL_TP.TOP.equals(sortItem)) {
                    ZYPEZDItemValueSetter.setValue(sharedMsg.frthBizCtxAttrbNum_B, new BigDecimal(sortNum));
                    rtnFlg = false;
                }
            } else {
                rtnFlg = true;
            }
        }
        return rtnFlg;
    }

    private static void setSortNum(NMAL3300SMsg sharedMsg, String[][] baseContents){

        if (chkSortData(sharedMsg)) {
            for (int i = 0; i < sharedMsg.R.getValidCount(); i++) {
                String workCd = sharedMsg.R.no(i).hrchEffLvlTpCd_R1.getValue();
                if (HRCH_EFF_LVL_TP.LOC.equals(workCd)) {
                    ZYPEZDItemValueSetter.setValue(sharedMsg.R.no(i).xxSortNum_R1, sharedMsg.firstBizCtxAttrbNum_B);
                } else if (HRCH_EFF_LVL_TP.ACCT.equals(workCd)) {
                    ZYPEZDItemValueSetter.setValue(sharedMsg.R.no(i).xxSortNum_R1, sharedMsg.scdBizCtxAttrbNum_B);
                } else if (HRCH_EFF_LVL_TP.PARENT.equals(workCd)) {
                    ZYPEZDItemValueSetter.setValue(sharedMsg.R.no(i).xxSortNum_R1, sharedMsg.thirdBizCtxAttrbNum_B);
                } else if (HRCH_EFF_LVL_TP.TOP.equals(workCd)) {
                    ZYPEZDItemValueSetter.setValue(sharedMsg.R.no(i).xxSortNum_R1, sharedMsg.frthBizCtxAttrbNum_B);
                }
            }

            S21SortTarget sortTarget = new S21SortTarget(sharedMsg.R, baseContents);
            S21SortKey sortKey = new S21SortKey();
            sortKey.add("xxSortNum_R1", S21SortKey.ASC);

            S21EZDMsgArraySort.sortNullsLast(sortTarget, sortKey, 0, sharedMsg.R.getValidCount());
        }
    }

    private static boolean chkSortData(NMAL3300SMsg sharedMsg){
        boolean rtnFlg = false;
        if (ZYPCommonFunc.hasValue(sharedMsg.firstBizCtxAttrbNum_B)) {
            rtnFlg = true;
        }
        if (ZYPCommonFunc.hasValue(sharedMsg.scdBizCtxAttrbNum_B)) {
            rtnFlg = true;
        }
        if (ZYPCommonFunc.hasValue(sharedMsg.thirdBizCtxAttrbNum_B)) {
            rtnFlg = true;
        }
        if (ZYPCommonFunc.hasValue(sharedMsg.frthBizCtxAttrbNum_B)) {
            rtnFlg = true;
        }
        return rtnFlg;
    }
    // 2016/2/25 QC#2823 ADD End

    // Del Start 2018/11/12 QC#28683
//    /**
//     * getTrxDriverSection
//     * @param bizMsg NMAL3300CMsg
//     * @param sharedMsg NMAL3300SMsg
//     * @param baseContents String[][]
//     */
//    public static void getTrxDriverSection(NMAL3300CMsg bizMsg, NMAL3300SMsg sharedMsg, String[][] baseContents) {
//        String glblCmpyCd = bizMsg.glblCmpyCd_S.getValue();
//        int recordCnt = 0;
//        sharedMsg.A.clear();
//        S21SsmEZDResult rslt = null;
//        // 2016/2/25 QC#2823 ADD Start
//        getSortNum(bizMsg, sharedMsg, glblCmpyCd, CUST_SPCL_INSTN_CTX_TP_TRANSACTION);
//        setSortNum(sharedMsg, baseContents);
//        // 2016/2/25 QC#2823 ADD End
//        for (int i = 0; i < sharedMsg.R.getValidCount(); i++) {
//
//            if (recordCnt >= sharedMsg.R.length()) {
//                break;
//            }
//            NMAL3300_RSMsg rsMsg = sharedMsg.R.no(i);
//            if (ZYPConstant.FLG_ON_Y.equals(rsMsg.xxYesNoCd_R1.getValue())) {
//                String searchKey = "";
//
//                if (ZYPCommonFunc.hasValue(rsMsg.locNum_R1)) {
//                    searchKey = rsMsg.locNum_R1.getValue();
//                    rslt = NMAL3300Query.getInstance().getCustSpclInstnForLoc(bizMsg, searchKey, glblCmpyCd);
//
//                } else {
//
//                    if (ZYPCommonFunc.hasValue(rsMsg.relnDsAcctNum_R1)) {
//                        searchKey = rsMsg.relnDsAcctNum_R1.getValue();
//                    } else {
//                        searchKey = rsMsg.dsAcctNum_R1.getValue();
//                    }
//                    rslt = NMAL3300Query.getInstance().getCustSpclInstnForAcct(bizMsg, searchKey, glblCmpyCd);
//                }
//
//                if (rslt.isCodeNormal()) {
//                    if (ZYPCommonFunc.hasValue(bizMsg.hrchEffLvlTpCd_D.getValue())
//                            && !rsMsg.hrchEffLvlTpCd_R1.getValue().equals(bizMsg.hrchEffLvlTpCd_D.getValue())) {
//                        continue;
//                    }
//                    List<Map<String, Object>> resultList = (List<Map<String, Object>>) rslt.getResultObject();
//                    for (int j = 0; j < resultList.size(); j++) {
//                        if (recordCnt < sharedMsg.R.length()) {
//                            Map<String, Object> map = (Map<String, Object>) resultList.get(j);
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.A.no(recordCnt).hrchEffLvlTpCd_A1, rsMsg.hrchEffLvlTpCd_R1.getValue());
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.A.no(recordCnt).hrchEffLvlTpNm_A1, rsMsg.hrchEffLvlTpNm_R1.getValue());
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.A.no(recordCnt).locNum_A1, searchKey);
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.A.no(recordCnt).dsCustTrxRulePk_A1, getBigDecimal(map, "DS_CUST_TRX_RULE_PK"));
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.A.no(recordCnt).dsTrxRuleTpCd_A1, getString(map, "DS_TRX_RULE_TP_CD"));
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.A.no(recordCnt).dsTrxRuleTpNm_A1, getString(map, "DS_TRX_RULE_TP_NM"));
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.A.no(recordCnt).xxChkBox_A1, getString(map, "DS_PO_REQ_FLG"));
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.A.no(recordCnt).dsBlktPoNum_A1, getString(map, "DS_BLKT_PO_NUM"));
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.A.no(recordCnt).dsPoExprDt_A1, getString(map, "DS_PO_EXPR_DT"));
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.A.no(recordCnt).dsDefBillToCd_A1, getString(map, "DS_DEF_BILL_TO_CD"));
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.A.no(recordCnt).xxAllLineAddr_A1, getString(map, "XX_ALL_LINE_ADDR_BILL"));
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.A.no(recordCnt).dsDefShipToCd_A1, getString(map, "DS_DEF_SHIP_TO_CD"));
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.A.no(recordCnt).xxAllLineAddr_A2, getString(map, "XX_ALL_LINE_ADDR_SHIP"));
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.A.no(recordCnt).xxChkBox_A2, getString(map, "DS_CR_CARD_REQ_FLG"));
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.A.no(recordCnt).xxChkBox_A3, getString(map, "DS_OVNGT_ALLW_FLG"));
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.A.no(recordCnt).custEffLvlNm_A1, getString(map, "CUST_EFF_LVL_NM"));
//                            recordCnt++;
//                        }
//                    }
//                }
//            }
//
//        }
//        sharedMsg.A.setValidCount(recordCnt);
//
//        boolean lvlSortFlg = false;
//        rslt = NMAL3300Query.getInstance().getLvlSortForTransaction(bizMsg, sharedMsg, glblCmpyCd);
//        if (rslt.isCodeNormal()) {
//            lvlSortFlg = true;
//        }
////        if (lvlSortFlg) {
////            setLvlSortForTransaction(sharedMsg);
////        }
//
//        // Set Default Transaction Driver
//        if (recordCnt > 0 && ZYPCommonFunc.hasValue(bizMsg.dsTrxRuleTpCd_S)) {
//            int idx = bizMsg.S.getValidCount();
//            String acctNum = bizMsg.S.no(idx).dsAcctNum_S1.getValue();
//            String billToCustCd = bizMsg.S.no(idx).billToCustCd_S1.getValue();
//            String shipToCustCd = bizMsg.S.no(idx).shipToCustCd_S1.getValue();
//            final NMZC610001 custInfoGetApi = new NMZC610001();
//
//            // Create Api Parameter
//            NMZC610001PMsg custInfoGetApiPMsg = new NMZC610001PMsg();
//            ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.glblCmpyCd, glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_TRANSACTION);
//            ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.dsTrxRuleTpCd, bizMsg.dsTrxRuleTpCd_S.getValue());
//            ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.dsAcctNum_I1, acctNum);
//            ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.billToCustCd, billToCustCd);
//            ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.shipToCustCd, shipToCustCd);
//
//            // Call Api
//            custInfoGetApi.execute(custInfoGetApiPMsg, ONBATCH_TYPE.ONLINE);
//
//            if (S21ApiUtil.isXxMsgId(custInfoGetApiPMsg)) {
//                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(custInfoGetApiPMsg);
//                for (int i = 0; i < msgList.size(); i++) {
//                    S21ApiMessage msg = msgList.get(i);
//                    String msgId = msg.getXxMsgid();
//                    String[] msgPrms = msg.getXxMsgPrmArray();
//                    bizMsg.setMessageInfo(msgId, msgPrms);
//
//                    if (msgId.endsWith("E")) {
//                        bizMsg.locNum_D.setErrorInfo(1, msgId, msgPrms);
//                    }
//                }
//            }
//            // Set Default Account Number or Location Number
//            setDefaultForTrx(sharedMsg.A, custInfoGetApiPMsg.TransactionRuleList);
//        }
//
//        if (recordCnt > 0) {
//            int i = 0;
//            for (; i < recordCnt; i++) {
//                if (i == bizMsg.A.length()) {
//                    break;
//                }
//                EZDMsg.copy(sharedMsg.A.no(i), null, bizMsg.A.no(i), null);
//            }
//            bizMsg.A.setValidCount(i);
//
//            bizMsg.xxPageShowFromNum_A.setValue(1);
//            bizMsg.xxPageShowToNum_A.setValue(bizMsg.A.getValidCount());
//            bizMsg.xxPageShowOfNum_A.setValue(recordCnt);
//        } else {
//            bizMsg.A.setValidCount(0);
//            bizMsg.xxPageShowFromNum_A.clear();
//            bizMsg.xxPageShowToNum_A.clear();
//            bizMsg.xxPageShowOfNum_A.clear();
//        }
//    }
//    /**
//     * getCustReferenceSection
//     * @param bizMsg NMAL3300CMsg
//     * @param sharedMsg NMAL3300SMsg
//     * @param baseContents String[][]
//     */
//    public static void getCustReferenceSection(NMAL3300CMsg bizMsg, NMAL3300SMsg sharedMsg, String[][] baseContents) {
//        String glblCmpyCd = bizMsg.glblCmpyCd_S.getValue();
//        int recordCnt = 0;
//        sharedMsg.B.clear();
//        S21SsmEZDResult rslt = null;
//        // 2016/2/25 QC#2823 ADD Start
//        getSortNum(bizMsg, sharedMsg, glblCmpyCd, CUST_SPCL_INSTN_CTX_TP_REFERENCE);
//        setSortNum(sharedMsg, baseContents);
//        // 2016/2/25 QC#2823 ADD End
//        for (int i = 0; i < sharedMsg.R.getValidCount(); i++) {
//            
//            if (recordCnt >= sharedMsg.R.length()) {
//                break;
//            }
//
//            NMAL3300_RSMsg rsMsg = sharedMsg.R.no(i);
//            if (ZYPConstant.FLG_ON_Y.equals(rsMsg.xxYesNoCd_R2.getValue())) {
//                // QC#5585
//                String searchKey = "";
//                if (ZYPCommonFunc.hasValue(rsMsg.locNum_R1)) {
//                    searchKey = rsMsg.locNum_R1.getValue();
//                    rslt = NMAL3300Query.getInstance().getAcctRefAttrbForLoc(searchKey, glblCmpyCd);
//                } else {
//                    if (ZYPCommonFunc.hasValue(rsMsg.relnDsAcctNum_R1)) {
//                        searchKey = rsMsg.relnDsAcctNum_R1.getValue();
//                    } else {
//                        searchKey = rsMsg.dsAcctNum_R1.getValue();
//                    }
//                    rslt = NMAL3300Query.getInstance().getAcctRefAttrbForAcct(searchKey, glblCmpyCd);
//                }
//
//                if (rslt.isCodeNormal()) {
//                    if (ZYPCommonFunc.hasValue(bizMsg.hrchEffLvlTpCd_D2.getValue())
//                            && !rsMsg.hrchEffLvlTpCd_R1.getValue().equals(bizMsg.hrchEffLvlTpCd_D2.getValue())) {
//                        continue;
//                    }
//                    List<Map<String, String>> resultList = (List<Map<String, String>>) rslt.getResultObject();
//                    for (Map<String, String> map : resultList) {
//                        if (recordCnt < sharedMsg.R.length()) {
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.B.no(recordCnt).hrchEffLvlTpCd_B1, rsMsg.hrchEffLvlTpCd_R1.getValue());
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.B.no(recordCnt).hrchEffLvlTpNm_B1, rsMsg.hrchEffLvlTpNm_R1.getValue());
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.B.no(recordCnt).locNum_B1, searchKey);
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.B.no(recordCnt).dsAcctRefAttrbNum_B1, map.get("DS_ACCT_REF_ATTRB_NUM"));
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.B.no(recordCnt).bllgAttrbNm_B1, map.get("BLLG_ATTRB_NM"));
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.B.no(recordCnt).bllgAttrbValTxt_B1, map.get("BLLG_ATTRB_VAL_TXT"));
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.B.no(recordCnt).bllgAttrbEnblFlg_B1, map.get("BLLG_ATTRB_ENBL_FLG"));
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.B.no(recordCnt).bllgAttrbReqFlg_B1, map.get("BLLG_ATTRB_REQ_FLG"));
//
//                        }
//                        recordCnt++;
//                    }
//                }
//            }
//        }
//
//        if (recordCnt > 0) {
//            int i = 0;
//            for (; i < recordCnt; i++) {
//                if (i == bizMsg.B.length()) {
//                    break;
//                }
//                EZDMsg.copy(sharedMsg.B.no(i), null, bizMsg.B.no(i), null);
//            }
//            bizMsg.B.setValidCount(i);
//
//            bizMsg.xxPageShowFromNum_B.setValue(1);
//            bizMsg.xxPageShowToNum_B.setValue(bizMsg.B.getValidCount());
//            bizMsg.xxPageShowOfNum_B.setValue(recordCnt);
//        } else {
//            bizMsg.B.setValidCount(0);
//            bizMsg.xxPageShowFromNum_B.clear();
//            bizMsg.xxPageShowToNum_B.clear();
//            bizMsg.xxPageShowOfNum_B.clear();
//        }
//    }
//    /**
//     * getSpclHandlingSection
//     * @param bizMsg NMAL3300CMsg
//     * @param sharedMsg NMAL3300SMsg
//     * @param baseContents String[][]
//     */
//    public static void getSpclHandlingSection(NMAL3300CMsg bizMsg, NMAL3300SMsg sharedMsg, String[][] baseContents) {
//        String glblCmpyCd = bizMsg.glblCmpyCd_S.getValue();
//        int recordCnt = 0;
//        sharedMsg.C.clear();
//        S21SsmEZDResult rslt = null;
//        // 2016/2/25 QC#2823 ADD Start
//        // 2018/07/11 QC#26713 Mod Start
//        // getSortNum(bizMsg, sharedMsg, glblCmpyCd, CUST_SPCL_INSTN_CTX_TP_INSTRUCTION);
//        getSortNum(bizMsg, sharedMsg, glblCmpyCd, CUST_SPCL_INSTN_CTX_TP_HANDLING);
//        // 2018/07/11 QC#26713 Mod End
//        setSortNum(sharedMsg, baseContents);
//        // 2016/2/25 QC#2823 ADD End
//        for (int i = 0; i < sharedMsg.R.getValidCount(); i++) {
//
//            if (recordCnt >= sharedMsg.R.length()) {
//                break;
//            }
//
//            NMAL3300_RSMsg rsMsg = sharedMsg.R.no(i);
//            if (ZYPConstant.FLG_ON_Y.equals(rsMsg.xxYesNoCd_R3.getValue())) {
//                if (ZYPCommonFunc.hasValue(rsMsg.locNum_R1)) {
//                    continue;
//                }
//                String searchKey = "";
//                if (ZYPCommonFunc.hasValue(rsMsg.relnDsAcctNum_R1)) {
//                    searchKey = rsMsg.relnDsAcctNum_R1.getValue();
//                } else {
//                    searchKey = rsMsg.dsAcctNum_R1.getValue();
//                }
//                rslt = NMAL3300Query.getInstance().getSpclHdlg(bizMsg, searchKey, glblCmpyCd);
//
//                if (rslt.isCodeNormal()) {
//                    if (ZYPCommonFunc.hasValue(bizMsg.hrchEffLvlTpCd_D3.getValue())
//                            && !rsMsg.hrchEffLvlTpCd_R1.getValue().equals(bizMsg.hrchEffLvlTpCd_D3.getValue())) {
//                        continue;
//                    }
//                    List<Map<String, String>> resultList = (List<Map<String, String>>) rslt.getResultObject();
//                    for (Map<String, String> map : resultList) {
//                        if (recordCnt < sharedMsg.R.length()) {
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.C.no(recordCnt).hrchEffLvlTpCd_C1, rsMsg.hrchEffLvlTpCd_R1.getValue());
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.C.no(recordCnt).hrchEffLvlTpNm_C1, rsMsg.hrchEffLvlTpNm_R1.getValue());
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.C.no(recordCnt).locNum_C1, searchKey);
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.C.no(recordCnt).dsSpclHdlgTpNm_C1, map.get("DS_SPCL_HDLG_TP_NM"));
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.C.no(recordCnt).dsSpclHdlgTpValNm_C1, map.get("DS_SPCL_HDLG_TP_VAL_NM"));
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.C.no(recordCnt).custEffLvlNm_C1, map.get("CUST_EFF_LVL_NM"));
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.C.no(recordCnt).effFromDt_C1, map.get("EFF_FROM_DT"));
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.C.no(recordCnt).effThruDt_C1, map.get("EFF_THRU_DT"));
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.C.no(recordCnt).ezInTime_C1, map.get("EZINTIME"));
//                            ZYPEZDItemValueSetter.setValue(sharedMsg.C.no(recordCnt).ezUpTime_C1, map.get("EZUPTIME"));
//
//                            recordCnt++;
//                        }
//
//                    }
//                }
//            }
//            
//        }
//        if (recordCnt > 0) {
//            int i = 0;
//            for (; i < recordCnt; i++) {
//                if (i == bizMsg.C.length()) {
//                    break;
//                }
//                EZDMsg.copy(sharedMsg.C.no(i), null, bizMsg.C.no(i), null);
//            }
//            bizMsg.C.setValidCount(i);
//
//            bizMsg.xxPageShowFromNum_C.setValue(1);
//            bizMsg.xxPageShowToNum_C.setValue(bizMsg.C.getValidCount());
//            bizMsg.xxPageShowOfNum_C.setValue(recordCnt);
//        } else {
//            bizMsg.C.setValidCount(0);
//            bizMsg.xxPageShowFromNum_C.clear();
//            bizMsg.xxPageShowToNum_C.clear();
//            bizMsg.xxPageShowOfNum_C.clear();
//        }
//    }
    // Del End 2018/11/12 QC#28683

    /**
     * getSpclMessageSection
     * @param bizMsg NMAL3300CMsg
     * @param sharedMsg NMAL3300SMsg
     * @param baseContents String[][]
     */
    public static void getSpclMessageSection(NMAL3300CMsg bizMsg, NMAL3300SMsg sharedMsg, String[][] baseContents) {
        String glblCmpyCd = bizMsg.glblCmpyCd_S.getValue();
        // Sales Date
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        int recordCnt = 0;
        S21SsmEZDResult rslt = null;

        // 2016/2/25 QC#2823 ADD Start
        // 2018/07/11 QC#26713 Mod Start
        // getSortNum(bizMsg, sharedMsg, glblCmpyCd, CUST_SPCL_INSTN_CTX_TP_HANDLING);
        getSortNum(bizMsg, sharedMsg, glblCmpyCd, CUST_SPCL_INSTN_CTX_TP_INSTRUCTION);
        // 2018/07/11 QC#26713 Mod End
        setSortNum(sharedMsg, baseContents);
        // 2016/2/25 QC#2823 ADD End
        for (int i = 0; i < sharedMsg.R.getValidCount(); i++) {

            if (recordCnt >= sharedMsg.R.length()) {
                break;
            }

            NMAL3300_RSMsg rsMsg = sharedMsg.R.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(rsMsg.xxYesNoCd_R4.getValue())) {
                String searchKey = "";
                if (ZYPCommonFunc.hasValue(rsMsg.locNum_R1)) {
                    searchKey = rsMsg.locNum_R1.getValue();
                    rslt = NMAL3300Query.getInstance().getCustSpclMsgForLoc(bizMsg, searchKey, glblCmpyCd, slsDt);

                } else {

                    if (ZYPCommonFunc.hasValue(rsMsg.relnDsAcctNum_R1)) {
                        searchKey = rsMsg.relnDsAcctNum_R1.getValue();
                    } else {
                        searchKey = rsMsg.dsAcctNum_R1.getValue();
                    }
                    rslt = NMAL3300Query.getInstance().getCustSpclMsgForAcct(bizMsg, searchKey, glblCmpyCd, slsDt);
                }

                if (rslt.isCodeNormal()) {
                    // Del Start 2018/11/12 QC#28683
                    //if (ZYPCommonFunc.hasValue(bizMsg.hrchEffLvlTpCd_D4.getValue())
                    //        && !rsMsg.hrchEffLvlTpCd_R1.getValue().equals(bizMsg.hrchEffLvlTpCd_D4.getValue())) {
                    //    continue;
                    //}
                    // Del End 2018/11/12 QC#28683
                    List<Map<String, Object>> resultList = (List<Map<String, Object>>) rslt.getResultObject();
                    for (Map<String, Object> map : resultList) {
                        if (recordCnt < sharedMsg.R.length()) {
                            ZYPEZDItemValueSetter.setValue(sharedMsg.D.no(recordCnt).hrchEffLvlTpCd_D1, rsMsg.hrchEffLvlTpCd_R1.getValue());
                            ZYPEZDItemValueSetter.setValue(sharedMsg.D.no(recordCnt).hrchEffLvlTpNm_D1, rsMsg.hrchEffLvlTpNm_R1.getValue());
                            ZYPEZDItemValueSetter.setValue(sharedMsg.D.no(recordCnt).locNum_D1, searchKey);
                            ZYPEZDItemValueSetter.setValue(sharedMsg.D.no(recordCnt).lineBizTpCd_D1, (String) map.get("LINE_BIZ_TP_CD"));
                            ZYPEZDItemValueSetter.setValue(sharedMsg.D.no(recordCnt).lineBizTpNm_D1, (String) map.get("LINE_BIZ_TP_NM"));
                            ZYPEZDItemValueSetter.setValue(sharedMsg.D.no(recordCnt).dsBizAreaCd_D1, (String) map.get("DS_BIZ_AREA_CD"));
                            ZYPEZDItemValueSetter.setValue(sharedMsg.D.no(recordCnt).dsBizAreaNm_D1, (String) map.get("DS_BIZ_AREA_NM"));
                            ZYPEZDItemValueSetter.setValue(sharedMsg.D.no(recordCnt).dsCustMsgTxt_D1, (String) map.get("DS_CUST_MSG_TXT"));
                            ZYPEZDItemValueSetter.setValue(sharedMsg.D.no(recordCnt).dsCustMsgTpNm_D1, (String) map.get("DS_CUST_MSG_TP_NM"));
                            ZYPEZDItemValueSetter.setValue(sharedMsg.D.no(recordCnt).xxChkBox_D1, (String) map.get("DS_PRINT_ON_INV_FLG"));
                            ZYPEZDItemValueSetter.setValue(sharedMsg.D.no(recordCnt).custEffLvlNm_D1, (String) map.get("CUST_EFF_LVL_NM"));
                            ZYPEZDItemValueSetter.setValue(sharedMsg.D.no(recordCnt).effFromDt_D1, (String) map.get("EFF_FROM_DT"));
                            ZYPEZDItemValueSetter.setValue(sharedMsg.D.no(recordCnt).effThruDt_D1, (String) map.get("EFF_THRU_DT"));
                            ZYPEZDItemValueSetter.setValue(sharedMsg.D.no(recordCnt).ezInTime_D1, (String) map.get("EZINTIME"));
                            ZYPEZDItemValueSetter.setValue(sharedMsg.D.no(recordCnt).ezUpTime_D1, (String) map.get("EZUPTIME"));
                            // QC#4482
                            ZYPEZDItemValueSetter.setValue(sharedMsg.D.no(recordCnt).dsCustSpclMsgPk_D1, (BigDecimal) map.get("DS_CUST_SPCL_MSG_PK"));
                            ZYPEZDItemValueSetter.setValue(sharedMsg.D.no(recordCnt).ezBusinessID_D1, (String) map.get("EZBUSINESSID"));
                            recordCnt++;
                        }

                    }
                }
            }
        }
        sharedMsg.D.setValidCount(recordCnt);

        // Set Default Special Instruction
        if (recordCnt > 0 && ZYPCommonFunc.hasValue(bizMsg.dsBizAreaCd_S)) {
            int idx = bizMsg.S.getValidCount();
            String acctNum = bizMsg.S.no(idx).dsAcctNum_S1.getValue();
            String billToCustCd = bizMsg.S.no(idx).billToCustCd_S1.getValue();
            String shipToCustCd = bizMsg.S.no(idx).shipToCustCd_S1.getValue();

            rslt = NMAL3300Query.getInstance().getLobTypeWithMessageType(glblCmpyCd);
            if (rslt.isCodeNormal()) {
                final NMZC610001 custInfoGetApi = new NMZC610001();
                List<Map<String, String>> resultList = (List<Map<String, String>>) rslt.getResultObject();
                for (Map<String, String> map : resultList) {

                     // Create Api Parameter
                    NMZC610001PMsg custInfoGetApiPMsg = new NMZC610001PMsg();
                    ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_INSTRUCTION);
                    ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.lineBizTpCd, map.get("LINE_BIZ_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.dsBizAreaCd, bizMsg.dsBizAreaCd_S.getValue());
                    ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.dsCustMsgTpCd, map.get("DS_CUST_MSG_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.dsAcctNum_I1, acctNum);
                    ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.billToCustCd, billToCustCd);
                    ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.shipToCustCd, shipToCustCd);
                    ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.slsDt, slsDt);

                    // Call Api
                    custInfoGetApi.execute(custInfoGetApiPMsg, ONBATCH_TYPE.ONLINE);

                    if (S21ApiUtil.isXxMsgId(custInfoGetApiPMsg)) {
                        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(custInfoGetApiPMsg);
                        for (int i = 0; i < msgList.size(); i++) {
                            S21ApiMessage msg = msgList.get(i);
                            String msgId = msg.getXxMsgid();
                            String[] msgPrms = msg.getXxMsgPrmArray();
                            bizMsg.setMessageInfo(msgId, msgPrms);

                            if (msgId.endsWith("E")) {
                                bizMsg.locNum_D.setErrorInfo(1, msgId, msgPrms);
                            }
                        }
                    }
                   // Set Default Account Number or Location Number
                    setDefaultForInstruction(sharedMsg.D, custInfoGetApiPMsg.InstructionList);
                }
            }
        }

        if (recordCnt > 0) {
            int i = 0;
            for (; i < recordCnt; i++) {
                if (i == bizMsg.D.length()) {
                    break;
                }
                EZDMsg.copy(sharedMsg.D.no(i), null, bizMsg.D.no(i), null);
            }
            bizMsg.D.setValidCount(i);

            bizMsg.xxPageShowFromNum_D.setValue(1);
            bizMsg.xxPageShowToNum_D.setValue(bizMsg.D.getValidCount());
            bizMsg.xxPageShowOfNum_D.setValue(recordCnt);
        } else {
            bizMsg.D.setValidCount(0);
            bizMsg.xxPageShowFromNum_D.clear();
            bizMsg.xxPageShowToNum_D.clear();
            bizMsg.xxPageShowOfNum_D.clear();
        }
    }

    // Del Start 2018/11/12 QC#28683
    ///**
    // * setDefaultForTrx
    // * @param asmsgArray NMAL3300_ASMsgArray
    // * @param trxRulePMsgArray NMZC610001_TransactionRuleListPMsgArray
    // */
    //public static void setDefaultForTrx(NMAL3300_ASMsgArray asmsgArray, NMZC610001_TransactionRuleListPMsgArray trxRulePMsgArray) {
    //
    //    for (int i = 0; i < trxRulePMsgArray.getValidCount(); i++) {
    //        NMZC610001_TransactionRuleListPMsg pmsg = trxRulePMsgArray.no(i);
    //        for (int j = 0; j < asmsgArray.getValidCount(); j++) {
    //            NMAL3300_ASMsg asmsg = asmsgArray.no(j);
    //            if (asmsg.dsCustTrxRulePk_A1.getValue().equals(pmsg.dsCustTrxRulePk.getValue())) {
    //                asmsg.xxYesNoCd_A1.setValue(ZYPConstant.FLG_ON_Y);
    //            }
    //        }
    //    }
    //}
    // Del End 2018/11/12 QC#28683

    /**
     * setDefaultForInstruction
     * @param dsmsgArray NMAL3300_ASMsgArray
     * @param instructionPMsgArray NMZC610001_TransactionRuleListPMsgArray
     */
    public static void setDefaultForInstruction(NMAL3300_DSMsgArray dsmsgArray, NMZC610001_InstructionListPMsgArray instructionPMsgArray) {

        for (int i = 0; i < instructionPMsgArray.getValidCount(); i++) {
            NMZC610001_InstructionListPMsg pmsg = instructionPMsgArray.no(i);
            for (int j = 0; j < dsmsgArray.getValidCount(); j++) {
                NMAL3300_DSMsg dsmsg = dsmsgArray.no(j);
                if (dsmsg.dsCustSpclMsgPk_D1.getValue().equals(pmsg.dsCustSpclMsgPk.getValue())) {
                    dsmsg.xxYesNoCd_D1.setValue(ZYPConstant.FLG_ON_Y);
                }
            }
        }
    }
    /**
     * Get String Value from Map. (With Conversion from Null to "")
     * @param map Map&lt;Object, Object&gt;
     * @param key String
     * @return String
     */
    public static String getString(Map<String, Object> map, String key) {
        String ret = (String) map.get(key);
        if (ZYPCommonFunc.hasValue(ret)) {
            return ret;
        }
        return "";
    }
    /**
     * Get BigDecimal Value from Map. (With Conversion from Null to BigDecimal.ZERO)
     * @param map Map&lt;Object, Object&gt;
     * @param key String
     * @return BigDecimal
     */
    public static BigDecimal getBigDecimal(Map<String, Object> map, String key) {
        BigDecimal ret = (BigDecimal) map.get(key);
        if (ZYPCommonFunc.hasValue(ret)) {
            return ret;
        }
        return BigDecimal.ZERO;
    }

    // Del Start 2018/11/12 QC#28683
//    /**
//     * setTransactionDisplayFlg
//     * @param cMsg NMAL3300CMsg
//     * @param sMsg NMAL3300SMsg
//     * @param glblCmpyCd String
//     */
//    public static void setTransactionDisplayFlg(NMAL3300CMsg cMsg, NMAL3300SMsg sMsg, String glblCmpyCd) {
//
//        for (int i = 0; i < sMsg.R.getValidCount(); i++) {
//            ZYPEZDItemValueSetter.setValue(sMsg.R.no(i).xxYesNoCd_R1, ZYPConstant.FLG_ON_Y);
//        }
//        S21SsmEZDResult rslt = null;
//        rslt = NMAL3300Query.getInstance().chkDisplayForTrxDriver(cMsg, glblCmpyCd);
//        Integer cnt = (Integer) rslt.getResultObject();
//        if (cnt > 0) {
//            rslt = NMAL3300Query.getInstance().getDisplayAvalCdForTrxDriver(sMsg, glblCmpyCd);
//            if (rslt.isCodeNormal()) {
//                rslt = NMAL3300Query.getInstance().getDisplayAvalFlgForTrxDriver(cMsg, sMsg, glblCmpyCd);
//
//            }
//        } else {
//            return;
//        }
//        if (rslt.isCodeNormal()) {
//            for (int i = 0; i < sMsg.R.getValidCount(); i++) {
//                if (!chkDisplayAval(sMsg, sMsg.R.no(i).hrchEffLvlTpCd_R1.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(sMsg.R.no(i).xxYesNoCd_R1, ZYPConstant.FLG_OFF_N);
//                }
//            }
//        }
//    }
//    /**
//     * setReferenceDisplayFlg
//     * @param cMsg NMAL3300CMsg
//     * @param sMsg NMAL3300SMsg
//     * @param glblCmpyCd String
//     */
//    public static void setReferenceDisplayFlg(NMAL3300CMsg cMsg, NMAL3300SMsg sMsg, String glblCmpyCd) {
//
//        for (int i = 0; i < sMsg.R.getValidCount(); i++) {
//            ZYPEZDItemValueSetter.setValue(sMsg.R.no(i).xxYesNoCd_R2, ZYPConstant.FLG_ON_Y);
//        }
//        S21SsmEZDResult rslt = null;
//        rslt = NMAL3300Query.getInstance().chkDisplayForReference(cMsg, glblCmpyCd);
//        Integer cnt = (Integer) rslt.getResultObject();
//        if (cnt > 0) {
//            rslt = NMAL3300Query.getInstance().getDisplayAvalCdForReference(sMsg, glblCmpyCd);
//            if (rslt.isCodeNormal()) {
//                rslt = NMAL3300Query.getInstance().getDisplayAvalFlgForReference(cMsg, sMsg, glblCmpyCd);
//
//            }
//        } else {
//            return;
//        }
//        if (rslt.isCodeNormal()) {
//            for (int i = 0; i < sMsg.R.getValidCount(); i++) {
//                if (!chkDisplayAval(sMsg, sMsg.R.no(i).hrchEffLvlTpCd_R1.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(sMsg.R.no(i).xxYesNoCd_R2, ZYPConstant.FLG_OFF_N);
//                }
//            }
//        }
//    }
//    /**
//     * setHandlingDisplayFlg
//     * @param cMsg NMAL3300CMsg
//     * @param sMsg NMAL3300SMsg
//     * @param glblCmpyCd String
//     */
//    public static void setHandlingDisplayFlg(NMAL3300CMsg cMsg, NMAL3300SMsg sMsg, String glblCmpyCd) {
//
//        for (int i = 0; i < sMsg.R.getValidCount(); i++) {
//            ZYPEZDItemValueSetter.setValue(sMsg.R.no(i).xxYesNoCd_R3, ZYPConstant.FLG_ON_Y);
//        }
//        S21SsmEZDResult rslt = null;
//        rslt = NMAL3300Query.getInstance().chkDisplayForHandling(cMsg, glblCmpyCd);
//        Integer cnt = (Integer) rslt.getResultObject();
//        if (cnt > 0) {
//            rslt = NMAL3300Query.getInstance().getDisplayAvalCdForHandling(sMsg, glblCmpyCd);
//            if (rslt.isCodeNormal()) {
//                rslt = NMAL3300Query.getInstance().getDisplayAvalFlgForHandling(cMsg, sMsg, glblCmpyCd);
//
//            }
//        } else {
//            return;
//        }
//        if (rslt.isCodeNormal()) {
//            for (int i = 0; i < sMsg.R.getValidCount(); i++) {
//                if (!chkDisplayAval(sMsg, sMsg.R.no(i).hrchEffLvlTpCd_R1.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(sMsg.R.no(i).xxYesNoCd_R3, ZYPConstant.FLG_OFF_N);
//                }
//            }
//        }
//    }
    // Del End 2018/11/12 QC#28683

    /**
     * setInstructionDisplayFlg
     * @param cMsg NMAL3300CMsg
     * @param sMsg NMAL3300SMsg
     * @param glblCmpyCd String
     */
    public static void setInstructionDisplayFlg(NMAL3300CMsg cMsg, NMAL3300SMsg sMsg, String glblCmpyCd) {

        for (int i = 0; i < sMsg.R.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(sMsg.R.no(i).xxYesNoCd_R4, ZYPConstant.FLG_ON_Y);
        }
        S21SsmEZDResult rslt = null;
        rslt = NMAL3300Query.getInstance().chkDisplayForInstruction(cMsg, glblCmpyCd);
        Integer cnt = (Integer) rslt.getResultObject();
        if (cnt > 0) {
            rslt = NMAL3300Query.getInstance().getDisplayAvalCdForInstruction(sMsg, glblCmpyCd);
            if (rslt.isCodeNormal()) {
                rslt = NMAL3300Query.getInstance().getDisplayAvalFlgForInstruction(cMsg, sMsg, glblCmpyCd);

            }
        } else {
            return;
        }
        if (rslt.isCodeNormal()) {
            for (int i = 0; i < sMsg.R.getValidCount(); i++) {
                if (!chkDisplayAval(sMsg, sMsg.R.no(i).hrchEffLvlTpCd_R1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.R.no(i).xxYesNoCd_R4, ZYPConstant.FLG_OFF_N);
                }
            }
        }
    }
    /**
     * chkDisplayAval
     * @param sMsg NMAL3300SMsg
     * @param hrchEffLvlTpCd String
     * @return boolean
     */
    public static boolean chkDisplayAval(NMAL3300SMsg sMsg, String hrchEffLvlTpCd) {
        if (hrchEffLvlTpCd.equals(sMsg.hrchEffLvlTpCd_A1.getValue())) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.firstBizCtxAttrbTxt_A.getValue())) {
                return true;
            }
        }
        if (hrchEffLvlTpCd.equals(sMsg.hrchEffLvlTpCd_A2.getValue())) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.scdBizCtxAttrbTxt_A.getValue())) {
                return true;
            }
        }
        if (hrchEffLvlTpCd.equals(sMsg.hrchEffLvlTpCd_A3.getValue())) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.thirdBizCtxAttrbTxt_A.getValue())) {
                return true;
            }
        }
        if (hrchEffLvlTpCd.equals(sMsg.hrchEffLvlTpCd_A4.getValue())) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.frthBizCtxAttrbTxt_A.getValue())) {
                return true;
            }
        }
        return false;
    }

    // Del Start 2018/11/12 QC#28683
//    /**
//     * setLvlSortForTransaction
//     * @param sMsg NMAL3300SMsg
//     */
//    public static void setLvlSortForTransaction(NMAL3300SMsg sMsg) {
//
//        EZDMsg.copy(sMsg.A, null, sMsg.E, null);
//        sMsg.A.clear();
//        int j = 0;
//        int idx = 0;
//        for (int i = 1; i < 5; i++) {
//            if (String.valueOf(i).equals(sMsg.firstBizCtxAttrbNum_B.getValue().toString())) {
//                for (j = 0; j < sMsg.E.getValidCount(); j++) {
//                    if (sMsg.hrchEffLvlTpCd_B1.getValue().equals(sMsg.E.no(j).hrchEffLvlTpCd_A1.getValue())) {
//                        EZDMsg.copy(sMsg.E.no(j), null, sMsg.A.no(idx), null);
//                    }
//                }
//            }
//            if (String.valueOf(i).equals(sMsg.scdBizCtxAttrbNum_B.getValue().toString())) {
//                for (j = 0; j < sMsg.E.getValidCount(); j++) {
//                    if (sMsg.hrchEffLvlTpCd_B2.getValue().equals(sMsg.E.no(j).hrchEffLvlTpCd_A1.getValue())) {
//                        EZDMsg.copy(sMsg.E.no(j), null, sMsg.A.no(idx), null);
//                    }
//                }
//            }
//            if (String.valueOf(i).equals(sMsg.thirdBizCtxAttrbNum_B.getValue().toString())) {
//                for (j = 0; j < sMsg.E.getValidCount(); j++) {
//                    if (sMsg.hrchEffLvlTpCd_B3.getValue().equals(sMsg.E.no(j).hrchEffLvlTpCd_A1.getValue())) {
//                        EZDMsg.copy(sMsg.E.no(j), null, sMsg.A.no(idx), null);
//                    }
//                }
//            }
//            if (String.valueOf(i).equals(sMsg.frthBizCtxAttrbNum_B.getValue().toString())) {
//                for (j = 0; j < sMsg.E.getValidCount(); j++) {
//                    if (sMsg.hrchEffLvlTpCd_B4.getValue().equals(sMsg.E.no(j).hrchEffLvlTpCd_A1.getValue())) {
//                        EZDMsg.copy(sMsg.E.no(j), null, sMsg.A.no(idx), null);
//                    }
//                }
//            }
//            idx++;
//        }
//    }
    // Del End 2018/11/12 QC#28683

    /**
     * <pre>
     * copyFromSMsgOntoCmsg
     * </pre>
     * 
     * @param cMsg NMAL3300CMsg
     * @param sMsg NMAL3300SMsg
     */
    // Mod Start 2018/11/12 QC#28683
    //public static void copyFromSMsgOntoCmsg(NMAL3300CMsg cMsg, NMAL3300SMsg sMsg, String isTbl) {
    public static void copyFromSMsgOntoCmsg(NMAL3300CMsg cMsg, NMAL3300SMsg sMsg) {
        // Mod End 2018/11/12 QC#28683
        // Del Start 2018/11/12 QC#28683
        //// copy data from SMsg onto CMsg From A
        //if (isTbl.equals(TABLE_A)) {
        //    int pagenationFrom = cMsg.xxPageShowFromNum_A.getValueInt();
        //    int i = pagenationFrom;
        //        for (; i < pagenationFrom + cMsg.A.length(); i++) {
        //            if (i < sMsg.A.getValidCount()) {
        //
        //                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
        //            } else {
        //                break;
        //            }
        //        }
        //        cMsg.A.setValidCount(i - pagenationFrom);
        //        sMsg.xxPageShowFromNum_A.setValue(pagenationFrom);
        //        sMsg.xxPageShowToNum_A.setValue(cMsg.A.getValidCount());
        //        sMsg.xxPageShowOfNum_A.setValue(sMsg.A.getValidCount());
        //}
        // Del End 2018/11/12 QC#28683

        // Add Start 2018/11/12 QC#28683
        // copy data from SMsg onto CMsg From D
        int pagenationFrom = cMsg.xxPageShowFromNum_D.getValueInt();
        int i = pagenationFrom;

        for (; i < pagenationFrom + cMsg.D.length(); i++) {
            if (i < sMsg.D.getValidCount()) {
                EZDMsg.copy(sMsg.D.no(i), null, cMsg.D.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }

        cMsg.D.setValidCount(i - pagenationFrom);
        cMsg.xxPageShowFromNum_D.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum_D.setValue(pagenationFrom + cMsg.D.getValidCount());
        cMsg.xxPageShowOfNum_D.setValue(sMsg.D.getValidCount());

        sMsg.xxPageShowFromNum_D.setValue(pagenationFrom + 1);
        sMsg.xxPageShowToNum_D.setValue(pagenationFrom + cMsg.D.getValidCount());
        sMsg.xxPageShowOfNum_D.setValue(sMsg.D.getValidCount());
        // Add End 2018/11/12 QC#28683
    }
}
