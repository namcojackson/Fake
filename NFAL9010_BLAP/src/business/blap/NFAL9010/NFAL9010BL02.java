/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL9010;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import parts.common.EZDAbendException;
import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFAL9010.constant.NFAL9010Constant;
import business.db.PL_CATGTMsg;
import business.db.PL_CATGTMsgArray;
import business.parts.NFACommonJrnlEntry;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * Class name: NFAL9010BL02
 * <dd>The class explanation: Business processing for Component ID :
 * NFAL9010BL02
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */

/**
 * <pre>
 * COA Account Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/05   CSAI            K.Uramori       Update          CSA Modification
 * </pre>
 */
public class NFAL9010BL02 extends S21BusinessHandler implements NFAL9010Constant {

    /** Journal Entry Common Module */
    private NFACommonJrnlEntry parts = new NFACommonJrnlEntry();

    /**
     * Method name: doProcess
     * <dd>The method explanation: Call each process by screen id.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();
            EZDDebugOutput.println(5, " ***************** screenAplID = " + screenAplID + "!!!!!!!!!!!!!!!", this);

            if ("NFAL9010_INIT".equals(screenAplID)) {
                doProcess_NFAL9010_INIT(cMsg, sMsg);
            } else if ("NFAL9010Scrn00_Select".equals(screenAplID)) {
                doProcess_NFAL9010Scrn00_Select(cMsg, sMsg);
            } else if ("NFAL9010Scrn00_Search".equals(screenAplID)) {
                doProcess_NFAL9010Scrn00_Search(cMsg, sMsg);
            } else if ("NFAL9010Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFAL9010Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NFAL9010Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFAL9010Scrn00_PageNext(cMsg, sMsg);
            } else {
                throw new EZDAbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_NFAL9010_INIT
     * <dd>The method explanation: Initializing.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL9010_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL9010_INIT================================START", this);

        NFAL9010CMsg bizMsg = (NFAL9010CMsg) cMsg;

        getTrialBalTpListBox(bizMsg);
        //getDrCrTpListBox(bizMsg);
        //getBsPlListBox(bizMsg);
        //getPlCatgListBox(bizMsg);

        EZDDebugOutput.println(5, "doProcess_NFAL9010_INIT================================END", this);

    }

    private void getTrialBalTpListBox(NFAL9010CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NFAL9010Query.getInstance().getTrialBalTp();

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                bizMsg.trialBalTpCd_1.no(i).setValue(parts.checkNull((String) map.get(TRIAL_BAL_TP_CD)));
                bizMsg.trialBalTpNm_2.no(i).setValue(parts.checkNull((String) map.get(TRIAL_BAL_TP_NM)));
            }
        }
    }

    /*
    private void getDrCrTpListBox(NFAL9010CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NFAL9010Query.getInstance().getDrCrTp();

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                bizMsg.drCrTpCd_1.no(i).setValue(parts.checkNull((String) map.get(DR_CR_TP_CD)));
                bizMsg.drCrTpNm_2.no(i).setValue(parts.checkNull((String) map.get(DR_CR_TP_NM)));
            }
        }
    }

    private void getBsPlListBox(NFAL9010CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NFAL9010Query.getInstance().getBsPl();

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                bizMsg.bsPlTpCd_1.no(i).setValue(parts.checkNull((String) map.get(BS_PL_TP_CD)));
                bizMsg.bsPlTpNm_2.no(i).setValue(parts.checkNull((String) map.get(BS_PL_TP_NM)));
            }
        }
    }

    private void getPlCatgListBox(NFAL9010CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NFAL9010Query.getInstance().getPlCatg();

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                bizMsg.plCatgCd_1.no(i).setValue(parts.checkNull((String) map.get(PL_CATG_CD)));
                bizMsg.plCatgNm_2.no(i).setValue(parts.checkNull((String) map.get(PL_CATG_NM)));
            }
        }
    }
    */

    /**
     * Method name: doProcess_NFAL9010Scrn00_Search
     * <dd>The method explanation: Call setData.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL9010Scrn00_Search(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL9010_Search================================START", this);

        NFAL9010CMsg bizMsg = (NFAL9010CMsg) cMsg;

        getResult_SSM(bizMsg, sMsg);

        EZDDebugOutput.println(5, "doProcess_NFAL9010_Search================================END", this);
    }

    /**
     * Method name: doProcess_NFAL9010Scrn00_Select
     * <dd>The method explanation: Call setData.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL9010Scrn00_Select(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL9010Scrn00_Select================================START", this);

        /*NFAL9010CMsg bizMsg = (NFAL9010CMsg) cMsg;
        if (!bizMsg.plCatgCd.isClear()) {
            // <ID>801</ID>
            // <query><![CDATA[
            // WHERE
            // EZCANCELFLAG = '0' AND
            // GLBL_CMPY_CD = ?glblCmpyCd01? AND
            // PL_CATG_CD = ?plCatgCd01?
            // ORDER BY PL_CATG_CD ASC
            PL_CATGTMsg tMsg = new PL_CATGTMsg();
            tMsg.setSQLID("801");
            tMsg.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
            tMsg.setConditionValue("plCatgCd01", bizMsg.plCatgCd.getValue());

            PL_CATGTMsgArray tMsgArr = (PL_CATGTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

            if (tMsgArr != null && tMsgArr.length() > 0) {
                bizMsg.plCatgNm.setValue(tMsgArr.no(0).plCatgNm.getValue());
            }
        }
        */
        EZDDebugOutput.println(5, "doProcess_NFAL9010Scrn00_Select================================END", this);
    }

    /**
     * Method name: doProcess_NFAL9010Scrn00_PagePrev
     * <dd>The method explanation: Call setData.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL9010Scrn00_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL9010_Prev================================START", this);

        NFAL9010CMsg bizMsg = (NFAL9010CMsg) cMsg;
        NFAL9010SMsg globalMsg = (NFAL9010SMsg) sMsg;

        bizMsg.A.clear();
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount() - 1);

        EZDDebugOutput.println(5, "doProcess_NFAL9010_Prev================================END", this);
    }

    /**
     * Method name: doProcess_NFAL9010Scrn00_PageNext
     * <dd>The method explanation: Call setData.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL9010Scrn00_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL9010_Next================================START", this);

        NFAL9010CMsg bizMsg = (NFAL9010CMsg) cMsg;
        NFAL9010SMsg globalMsg = (NFAL9010SMsg) sMsg;

        bizMsg.A.clear();
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());

        EZDDebugOutput.println(5, "doProcess_NFAL9010_Next================================END", this);
    }

    private void getResult_SSM(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "getRessult================================START", null);

        NFAL9010CMsg bizMsg = (NFAL9010CMsg) cMsg;
        NFAL9010SMsg globalMsg = (NFAL9010SMsg) sMsg;

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);

        long recordTotal = Long.parseLong(listCount00_SSM(cMsg));
        long limit = globalMsg.A.length();

        bizMsg.glblCmpyCd.setValue(this.getGlobalCompanyCode());

        S21SsmEZDResult ssmResult = NFAL9010Query.getInstance().getResultListNFAL9010(bizMsg, globalMsg);

        if (ssmResult.isCodeNormal()) {

            // in case of exceesing maximum limit of record
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > globalMsg.A.length()) {
                cMsg.setMessageInfo("NZZM0001W");
                queryResCnt = globalMsg.A.length();
            }
            // one page data（SMsg -> CMsg）
            int i = 0;
            for (; i < globalMsg.A.getValidCount(); i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);
            }
            bizMsg.A.setValidCount(i);

            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum.setValue(queryResCnt);
        } else {
            // Message for no result
            bizMsg.setMessageInfo("NFAM0002E", new String[] {"result" });
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
        }

        if (limit < recordTotal) {
            bizMsg.setMessageInfo("NFAM0001W", new String[] {Long.toString(limit), Long.toString(recordTotal) });
        }

        EZDDebugOutput.println(1, "getRessult================================END", null);
    }

    private String listCount00_SSM(EZDCMsg cMsg) {

        NFAL9010CMsg bizMsg = (NFAL9010CMsg) cMsg;

        S21SsmEZDResult ssmResult = NFAL9010Query.getInstance().getResultCountNFAL9010(bizMsg);

        if (ssmResult.isCodeNormal()) {
            return new DecimalFormat("0").format(bizMsg.xxTotCnt.getValue());
        } else {
            return ZERO_VAL;
        }
    }
}
