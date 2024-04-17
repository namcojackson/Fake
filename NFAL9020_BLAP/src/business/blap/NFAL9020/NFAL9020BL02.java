/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL9020;

import java.text.DecimalFormat;

import parts.common.EZDAbendException;
import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFAL9020.constant.NFAL9020Constant;
import business.db.SYS_SRCTMsg;
import business.db.SYS_SRCTMsgArray;
import business.db.TRXTMsg;
import business.db.TRXTMsgArray;
import business.db.TRX_RSNTMsg;
import business.db.TRX_RSNTMsgArray;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * Class name: NFAL9020BL02
 * <dd>The class explanation: Business processing for Component ID :
 * NFAL9020BL02
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL9020BL02 extends S21BusinessHandler implements NFAL9020Constant {

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

            if ("NFAL9020_INIT".equals(screenAplID)) {
                doProcess_NFAL9020_INIT(cMsg, sMsg);
            } else if ("NFAL9020Scrn00_SearchBtn".equals(screenAplID)) {
                doProcess_NFAL9020Scrn00_SearchBtn(cMsg, sMsg);
            } else if ("NFAL9020Scrn00_OnChange_TRX_CD".equals(screenAplID)) {
                doProcess_NFAL9020Scrn00_OnChange_TRX_CD(cMsg, sMsg);
            } else if ("NFAL9020Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFAL9020Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NFAL9020Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFAL9020Scrn00_PageNext(cMsg, sMsg);
            } else if ("NFAL9020Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NFAL9020Scrn00_PageJump(cMsg, sMsg);
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
     * Method name: doProcess_NFAL9020_INIT
     * <dd>The method explanation: Initializing.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL9020_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL9020_INIT================================START", this);

        NFAL9020CMsg bizMsg = (NFAL9020CMsg) cMsg;
        bizMsg.A.setValidCount(0);

        // Sys Src
        setSysSrcListBox(cMsg);
        // Trx
        setTrxListBox(cMsg);

        EZDDebugOutput.println(5, "doProcess_NFAL9020_INIT================================END", this);
    }

    private void setSysSrcListBox(EZDCMsg cMsg) {

        NFAL9020CMsg bizMsg = (NFAL9020CMsg) cMsg;

        bizMsg.sysSrcCd_1.clear();
        bizMsg.sysSrcCd_2.clear();

        // <ID>801</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01?
        // ORDER BY
        // SYS_SRC_SORT_NUM ASC
        SYS_SRCTMsg tMsg = new SYS_SRCTMsg();
        tMsg.setSQLID("801");
        tMsg.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
        SYS_SRCTMsgArray tMsgArr = (SYS_SRCTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            for (int i = 0; i < tMsgArr.length(); i++) {
                bizMsg.sysSrcCd_1.no(i).setValue(tMsgArr.no(i).sysSrcCd.getValue());
                bizMsg.sysSrcCd_2.no(i).setValue(tMsgArr.no(i).sysSrcCd.getValue());
            }
        }
    }

    private void setTrxListBox(EZDCMsg cMsg) {

        NFAL9020CMsg bizMsg = (NFAL9020CMsg) cMsg;

        bizMsg.trxCd_1.clear();
        bizMsg.trxCd_2.clear();

        // <ID>801</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01?
        // ORDER BY
        // TRX_SORT_NUM ASC
        TRXTMsg tMsg = new TRXTMsg();
        tMsg.setSQLID("801");
        tMsg.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
        TRXTMsgArray tMsgArr = (TRXTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            for (int i = 0; i < tMsgArr.length(); i++) {
                bizMsg.trxCd_1.no(i).setValue(tMsgArr.no(i).trxCd.getValue());
                bizMsg.trxCd_2.no(i).setValue(tMsgArr.no(i).trxCd.getValue());
            }
        }
    }

    /**
     * Method name: doProcess_NFAL9020Scrn00_SearchBtn
     * <dd>The method explanation: Call setData.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL9020Scrn00_SearchBtn(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL9020Scrn00_SearchBtn================================START", this);

        getResult_SSM(cMsg, sMsg);

        EZDDebugOutput.println(5, "doProcess_NFAL9020Scrn00_SearchBtn================================END", this);
    }

    /**
     * Method name: doProcess_NFAL9020Scrn00_OnChange_TRX_CD
     * <dd>The method explanation: Call setData.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL9020Scrn00_OnChange_TRX_CD(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL9020Scrn00_OnChange_TRX_CD================================START", this);

        NFAL9020CMsg bizMsg = (NFAL9020CMsg) cMsg;

        bizMsg.trxRsnCd_1.clear();
        bizMsg.trxRsnCd_2.clear();
        bizMsg.trxRsnCd_3.clear();

        // <ID>801</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01? AND
        // TRX_CD = ?trxCd01?
        // ORDER BY
        // TRX_RSN_CD ASC
        TRX_RSNTMsg tMsg = new TRX_RSNTMsg();
        tMsg.setSQLID("801");
        tMsg.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
        tMsg.setConditionValue("trxCd01", bizMsg.trxCd_3.getValue());
        TRX_RSNTMsgArray tMsgArr = (TRX_RSNTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            for (int i = 0; i < tMsgArr.length(); i++) {
                bizMsg.trxRsnCd_1.no(i).setValue(tMsgArr.no(i).trxRsnCd.getValue());
                bizMsg.trxRsnCd_2.no(i).setValue(tMsgArr.no(i).trxRsnCd.getValue());
            }
        }

        EZDDebugOutput.println(1, "doProcess_NFAL9020Scrn00_OnChange_TRX_CD================================END", null);
    }

    /**
     * Method name: doProcess_NFAL9020Scrn00_PagePrev
     * <dd>The method explanation: Call setData.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL9020Scrn00_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL9020_Prev================================START", this);

        NFAL9020CMsg bizMsg = (NFAL9020CMsg) cMsg;
        NFAL9020SMsg globalMsg = (NFAL9020SMsg) sMsg;

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

        pagenationFrom = pagenationFrom + 1;
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount() - 1);

        EZDDebugOutput.println(5, "doProcess_NFAL9020_Prev================================END", this);
        return;
    }

    /**
     * Method name: doProcess_NFAL9020Scrn00_PageNext
     * <dd>The method explanation: Call setData.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL9020Scrn00_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL9020_Next================================START", this);

        NFAL9020CMsg bizMsg = (NFAL9020CMsg) cMsg;
        NFAL9020SMsg globalMsg = (NFAL9020SMsg) sMsg;

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

        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());

        EZDDebugOutput.println(5, "doProcess_NFAL9020_Next================================END", this);

        return;
    }

    /**
     * Method name: NFAL9020Scrn00_PageJump
     * <dd>The method explanation: Call setData.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL9020Scrn00_PageJump(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "NFAL9020Scrn00_PageJump================================START", this);

        NFAL9020CMsg bizMsg = (NFAL9020CMsg) cMsg;
        NFAL9020SMsg globalMsg = (NFAL9020SMsg) sMsg;

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
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());

        EZDDebugOutput.println(5, "NFAL9020Scrn00_PageJump================================END", this);
    }

    private void getResult_SSM(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "getRessult================================START", null);

        NFAL9020CMsg bizMsg = (NFAL9020CMsg) cMsg;
        NFAL9020SMsg globalMsg = (NFAL9020SMsg) sMsg;

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);

        bizMsg.glblCmpyCd.setValue(this.getGlobalCompanyCode());

        long recordTotal = Long.parseLong(listCount00_SSM(cMsg));
        long limit = globalMsg.A.length();

        S21SsmEZDResult ssmResult = NFAL9020Query.getInstance().getResultListNFAL9020(bizMsg, globalMsg);

        if (ssmResult.isCodeNormal()) {
            // has a result
            // in case of exceesing maximum limit of record
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > globalMsg.A.length()) {
                cMsg.setMessageInfo("NZZM0001W");
                queryResCnt = globalMsg.A.length();
            }

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

        NFAL9020CMsg bizMsg = (NFAL9020CMsg) cMsg;

        S21SsmEZDResult ssmResult = NFAL9020Query.getInstance().getResultCountNFAL9020(bizMsg);

        if (ssmResult.isCodeNormal()) {
            return new DecimalFormat("0").format(bizMsg.xxTotCnt.getValue());
        } else {
            return "0";
        }
    }
}
