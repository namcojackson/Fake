/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NPAL1140;

import static business.blap.NPAL1140.constant.NPAL1140Constant.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL1140.common.NPAL1140CommonLogic;
import business.blap.NPAL1140.constant.NPAL1140Constant;
import business.db.PO_ACK_HDR_WRKTMsg;
import business.db.PO_ACK_HDR_WRKTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACK_EDI_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * ACK Error Correction
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/30/2013   Hitachi         K.Kishimoto     Create          N/A
 * 06/11/2013   Hitachi         K.Kishimoto     Update          1233
 * 07/10/2013   Hitachi         K.Kishimoto     Update          QC1233
 * 07/26/2013   Hitachi         T.Kawazu        Update          QC1388
 * 07/30/2013   Hitachi         A.Kohinata      Update          QC1388
 * 08/14/2013   Hitachi         K.Kishimoto     Update          QC1233
 *</pre>
 */
public class NPAL1140BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            NPAL1140CMsg bizMsg = (NPAL1140CMsg) cMsg;
            NPAL1140SMsg globalMsg = (NPAL1140SMsg) sMsg;
            String screenAplID = bizMsg.getScreenAplID();

            if ("NPAL1140_INIT".equals(screenAplID)) {
                doProcess_NPAL1140_INIT(bizMsg, globalMsg);
            } else if ("NPAL1140Scrn00_Search".equals(screenAplID)) {
                doProcess_NPAL1140Scrn00_Search(bizMsg, globalMsg,NPAL1140Constant.SEARCH_TYPE_SEARCH);
            } else if ("NPAL1140Scrn00_ackDisplay".equals(screenAplID)) {
                doProcess_NPAL1140Scrn00_ackDisplay(bizMsg, globalMsg);
            } else if ("NPAL1140Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NPAL1140Scrn00_PageNext(bizMsg, globalMsg);
            } else if ("NPAL1140Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NPAL1140Scrn00_PagePrev(bizMsg, globalMsg);
            } else if ("NPAL1140Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NPAL1140Scrn00_CMN_Reset(bizMsg, globalMsg);
            } else if ("NPAL1140Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NPAL1140Scrn00_CMN_Save(bizMsg, globalMsg);
            } else if ("NPAL1140Scrn00_ackReprocess".equals(screenAplID)) {
                doProcess_NPAL1140Scrn00_ackReprocess(bizMsg, globalMsg);
            } else if ("NPAL1140Scrn00_ackCancel".equals(screenAplID)) {
                doProcess_NPAL1140Scrn00_ackCancel(bizMsg, globalMsg);
            } else if ("NPAL1140Scrn00_SelectAll".equals(screenAplID)) {
                doProcess_NPAL1140Scrn00_SelectAll(bizMsg, globalMsg);
            } else if ("NPAL1140Scrn00_UnSelectAll".equals(screenAplID)) {
                doProcess_NPAL1140Scrn00_UnSelectAll(bizMsg, globalMsg);
            } else if ("NPAL1140Scrn00_Refresh".equals(screenAplID)) {
                doProcess_NPAL1140Scrn00_Refresh(bizMsg, globalMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * Init Event
     * </pre>
     * @param bizMsg NPAL1140CMsg
     * @param globalMsg NPAL1140SMsg
     */
    private void doProcess_NPAL1140_INIT(NPAL1140CMsg bizMsg, NPAL1140SMsg globalMsg) {

        globalMsg.clear();
        bizMsg.xxDplyTab.setValue(TAB_HEADER);
        bizMsg.xxPageShowFromNum.setValue(BigDecimal.ZERO);
        bizMsg.xxPageShowToNum.setValue(BigDecimal.ZERO);
        bizMsg.xxPageShowOfNum.setValue(BigDecimal.ZERO);

        List<String> intfcList = NPAL1140Query.getInstance().getIntdfcId();
        if (intfcList.size() > 0) {
            int idx = 0;
            PO_ACK_HDR_WRKTMsg[] aList = new PO_ACK_HDR_WRKTMsg[intfcList.size()];
            for (String intfcId : intfcList) {
                aList[idx] = new PO_ACK_HDR_WRKTMsg();
                ZYPEZDItemValueSetter.setValue(aList[idx].itrlIntfcId, intfcId);
                idx++;
            }
            Map<String, String> tMsgKeys = new HashMap<String, String>();
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "itrlIntfcId");
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "itrlIntfcId");
            PO_ACK_HDR_WRKTMsgArray tMsgArray = new PO_ACK_HDR_WRKTMsgArray();
            tMsgArray.setMsgList(aList);
            tMsgArray.setValidCount(intfcList.size());
        }

        try {
            ZYPCodeDataUtil.createPulldownList("ACK_EDI_PROC_STS", bizMsg.ediProcStsCd_0C, bizMsg.ediProcStsNm_0D);
            bizMsg.ediProcStsCd_0V.setValue(ACK_EDI_PROC_STS.ERROR);
        } catch (S21AbendException ex) {
            bizMsg.setMessageInfo(MSG_ID_NPAM1304E, new String[] {"ACK_EDI_PROC_STS" });
            return;
        }

    }

    /**
     * <pre>
     * Search Event
     * </pre>
     * @param bizMsg NPAL1140CMsg
     * @param globalMsg NPAL1140SMsg
     */
    private void doProcess_NPAL1140Scrn00_Search(NPAL1140CMsg bizMsg, NPAL1140SMsg globalMsg,String searchType) {

        Boolean flgReSearch = false;
        if (NPAL1140Constant.SEARCH_TYPE_RESEARCH.equals(searchType))
        {
            flgReSearch = true;
        }
        else
        {
            flgReSearch = false;
        }
        
        if (ZYPCommonFunc.hasValue(bizMsg.xxCratDt_H1) && ZYPCommonFunc.hasValue(bizMsg.xxCratDt_H2)) {
            if (bizMsg.xxCratDt_H1.getValue().compareTo(bizMsg.xxCratDt_H2.getValue()) > 0) {
                bizMsg.xxCratDt_H1.setErrorInfo(1, MSG_ID_NPAM0225E, new String[] {DISP_ITEM_NAME_EDI_RCV_DATE });
                bizMsg.xxCratDt_H2.setErrorInfo(1, MSG_ID_NPAM0225E, new String[] {DISP_ITEM_NAME_EDI_RCV_DATE });
                return;
            }
        }

        // Global Message initialized
        globalMsg.clear();
        ZYPTableUtil.clear(globalMsg.A);
        ZYPTableUtil.clear(globalMsg.B);
        NPAL1140CommonLogic.underTabClear(globalMsg);

        bizMsg.xxDplyTab.setValue(TAB_HEADER);

        // copy Business Message to Global Message
        EZDMsg.copy(bizMsg, null, globalMsg, null);

        // search List
        S21SsmEZDResult ssmResult = NPAL1140Query.getInstance().getSearchList(globalMsg);

        // set to global message
        NPAL1140CommonLogic.searchListSet(globalMsg, ssmResult);

        if (ssmResult.isCodeNormal()) {
            // max row count check
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > globalMsg.A.length() && !flgReSearch) {
                bizMsg.setMessageInfo("NZZM0001W");
                queryResCnt = globalMsg.A.length();
            }

            // copy current page data (SMsg -> CMsg)
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
            //bizMsg.xxPageShowOfNum.setValue(queryResCnt);
            bizMsg.xxPageShowOfNum.setValue(globalMsg.A.getValidCount());
            
            if(!flgReSearch)
            {
                bizMsg.setMessageInfo("ZZZM9003I", new String[]{"Search"});
            }
            // page data not found
        } else {
            if(!flgReSearch)
            {
                bizMsg.setMessageInfo("NZZM0000E"); // No search results found.
            }
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
        }
    }

    /**
     * <pre>
     * PagePrev Event
     * </pre>
     * @param bizMsg NPAL1140CMsg
     * @param globalMsg NPAL1140SMsg
     */
    private void doProcess_NPAL1140Scrn00_PagePrev(NPAL1140CMsg bizMsg, NPAL1140SMsg globalMsg) {

        NPAL1140CommonLogic.copyFromCmsgOntoSmsg(bizMsg, globalMsg);

        // set values to items of pageNation for previous page
        // pageNation
        ZYPTableUtil.clear(bizMsg.A);
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        bizMsg.xxPageShowToNum.clear();
        NPAL1140CommonLogic.copyFromSMsgOntoCmsg(bizMsg, globalMsg);
    }

    /**
     * <pre>
     * PageNext Event
     * </pre>
     * @param bizMsg NPAL1140CMsg
     * @param globalMsg NPAL1140SMsg
     */
    private void doProcess_NPAL1140Scrn00_PageNext(NPAL1140CMsg bizMsg, NPAL1140SMsg globalMsg) {

        NPAL1140CommonLogic.copyFromCmsgOntoSmsg(bizMsg, globalMsg);

        // set values to items of pageNation for next page pageNation
        ZYPTableUtil.clear(bizMsg.A);
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        bizMsg.xxPageShowToNum.clear();
        NPAL1140CommonLogic.copyFromSMsgOntoCmsg(bizMsg, globalMsg);
    }

    /**
     * <pre>
     * Link Event
     * </pre>
     * @param bizMsg NPAL1140CMsg
     * @param globalMsg NPAL1140SMsg
     */
    private void doProcess_NPAL1140Scrn00_ackDisplay(NPAL1140CMsg bizMsg, NPAL1140SMsg globalMsg) {

        NPAL1140CommonLogic.copyFromCmsgOntoSmsg(bizMsg, globalMsg);

        int selectLine = bizMsg.xxNum.getValueInt();
        if (!NPAL1140CommonLogic.underTabset(globalMsg, bizMsg, bizMsg.A.no(selectLine))) {
            return;
        }

        EZDMsg.copy(globalMsg, null, bizMsg, null);

        globalMsg.xxChkBox_B0.clear();

        NPAL1140CommonLogic.detailListDisp(bizMsg, globalMsg);

        NPAL1140CommonLogic.copyFromSMsgOntoCmsg(bizMsg, globalMsg);
        bizMsg.xxDplyTab.setValue(TAB_HEADER);
    }

    /**
     * <pre>
     * Reset Event
     * </pre>
     * @param bizMsg NPAL1140CMsg
     * @param globalMsg NPAL1140SMsg
     */
    private void doProcess_NPAL1140Scrn00_CMN_Reset(NPAL1140CMsg bizMsg, NPAL1140SMsg globalMsg) {

        bizMsg.clear();
        doProcess_NPAL1140_INIT(bizMsg, globalMsg);
    }

    /**
     * <pre>
     * Save Event
     * </pre>
     * @param bizMsg NPAL1140CMsg
     * @param globalMsg NPAL1140SMsg
     */
    private void doProcess_NPAL1140Scrn00_CMN_Save(NPAL1140CMsg bizMsg, NPAL1140SMsg globalMsg) {

        if (!ZYPConstant.FLG_ON_1.equals(bizMsg.xxErrFlg.getValue())) {
            int selectLine = -1;
            selectLine = NPAL1140CommonLogic.searchPoOrdNumAarray(globalMsg, bizMsg.ediPoOrdNum_HT.getValue());
            if (!NPAL1140CommonLogic.underTabset(globalMsg, bizMsg, bizMsg.A.no(selectLine))) {
                return;
            }
            if (selectLine != -1) {
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(selectLine).ackEdiProcStsNm_A0, globalMsg.ackEdiProcStsNm);
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(selectLine).openPoAckWrkFlg_A0, globalMsg.openPoAckWrkFlg);
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(selectLine).ezUpTime_A0, globalMsg.ezUpTime_HT);
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(selectLine).ezUpTimeZone_A0, globalMsg.ezUpTimeZone_HT);
            }
            EZDMsg.copy(globalMsg, null, bizMsg, null);
            bizMsg.xxDplyTab.setValue(TAB_HEADER);
        }

        NPAL1140CommonLogic.copyFromSMsgOntoCmsg(bizMsg, globalMsg);
        NPAL1140CommonLogic.detailListDisp(bizMsg, globalMsg);
    }

    /**
     * <pre>
     * Reprocess Event
     * </pre>
     * @param bizMsg NPAL1140CMsg
     * @param globalMsg NPAL1140SMsg
     */
    private void doProcess_NPAL1140Scrn00_ackReprocess(NPAL1140CMsg bizMsg, NPAL1140SMsg globalMsg) {
        doProcess_NPAL1140Scrn00_Search(bizMsg, globalMsg,NPAL1140Constant.SEARCH_TYPE_RESEARCH);
    }

    /**
     * <pre>
     * Cancel Event
     * </pre>
     * @param bizMsg NPAL1140CMsg
     * @param globalMsg NPAL1140SMsg
     */
    private void doProcess_NPAL1140Scrn00_ackCancel(NPAL1140CMsg bizMsg, NPAL1140SMsg globalMsg) {
        doProcess_NPAL1140Scrn00_Search(bizMsg, globalMsg,NPAL1140Constant.SEARCH_TYPE_RESEARCH);
    }

    /**
     * <pre>
     * SelectAll Event
     * </pre>
     * @param bizMsg NPAL1140CMsg
     * @param globalMsg NPAL1140SMsg
     */
    private void doProcess_NPAL1140Scrn00_SelectAll(NPAL1140CMsg bizMsg, NPAL1140SMsg globalMsg) {
        NPAL1140CommonLogic.copyFromCmsgOntoSmsg(bizMsg, globalMsg);
        for (int idx = 0; idx < globalMsg.A.getValidCount(); idx++) {
            if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(idx).openPoAckWrkFlg_A0.getValue())) {
                globalMsg.A.no(idx).xxChkBox_A0.setValue(ZYPConstant.CHKBOX_ON_Y);
            }
        }
        NPAL1140CommonLogic.copyFromSMsgOntoCmsg(bizMsg, globalMsg);
    }

    /**
     * <pre>
     * UnSelectAll Event
     * </pre>
     * @param bizMsg NPAL1140CMsg
     * @param globalMsg NPAL1140SMsg
     */
    private void doProcess_NPAL1140Scrn00_UnSelectAll(NPAL1140CMsg bizMsg, NPAL1140SMsg globalMsg) {
        NPAL1140CommonLogic.copyFromCmsgOntoSmsg(bizMsg, globalMsg);
        for (int idx = 0; idx < globalMsg.A.getValidCount(); idx++) {
            globalMsg.A.no(idx).xxChkBox_A0.clear();
        }
        NPAL1140CommonLogic.copyFromSMsgOntoCmsg(bizMsg, globalMsg);
    }

    /**
     * <pre>
     * Refresh Event
     * </pre>
     * @param bizMsg NPAL1140CMsg
     * @param globalMsg NPAL1140SMsg
     */
    private void doProcess_NPAL1140Scrn00_Refresh(NPAL1140CMsg bizMsg, NPAL1140SMsg globalMsg) {
        NPAL1140CommonLogic.copyFromCmsgOntoSmsg(bizMsg, globalMsg);
        globalMsg.xxChkBox_B0.setValue(bizMsg.xxChkBox_B0.getValue());
        NPAL1140CommonLogic.detailListDisp(bizMsg, globalMsg);
        NPAL1140CommonLogic.copyFromSMsgOntoCmsg(bizMsg, globalMsg);
    }
}
