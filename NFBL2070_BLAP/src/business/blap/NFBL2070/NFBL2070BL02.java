/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL2070;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NFBL2070.common.NFBL2070CommonLogic;
import business.blap.NFBL2070.constant.NFBL2070Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Compensation Credit I/F Error Correction
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/09   CSAI            Miyauchi        Create          N/A
 * 2016/10/11   Hitachi         K.Kojima        Update          QC#12824
 * 2016/11/02   Fujitsu         S.Fujita        Update          QC#15734
 * 2016/11/21   Fujitsu         S.Yoshida       Update          QC#16156
 * 2016/11/22   Hitachi         T.Tsuchida      Update          QC#16041
 * 2016/11/28   Fujitsu         T.Murai         Update          QC#16158
 * </pre>
 */
public class NFBL2070BL02 extends S21BusinessHandler implements NFBL2070Constant {

	@Override
    protected void doProcess(EZDCMsg bizMsg, EZDSMsg glblMsg) {
        super.preDoProcess(bizMsg, glblMsg);

        try {

            String screenAplID = bizMsg.getScreenAplID();
            NFBL2070CMsg cMsg = (NFBL2070CMsg) bizMsg;
            NFBL2070SMsg sMsg = (NFBL2070SMsg) glblMsg;

            cMsg.setCommitSMsg(true);

            //Error Message Clear
            sMsg.clearErrorInfo();
            cMsg.clearErrorInfo();

            if (SCREEN_ID.NFBL2070_INIT.name().equals(screenAplID)) {
                doProcess_NFBL2070_INIT(cMsg, sMsg);
            } else if (SCREEN_ID.NFBL2070Scrn00_CMN_Reset.name().equals(screenAplID)) {
                doProcess_NFBL2070Scrn00_CMN_Reset(cMsg, sMsg);
            } else if (SCREEN_ID.NFBL2070Scrn00_Search.name().equals(screenAplID)) {
                doProcess_NFBL2070Scrn00_Search(cMsg, sMsg);
            } else if (SCREEN_ID.NFBL2070Scrn00_OnClick_Fix.name().equals(screenAplID)) {
                doProcess_NFBL2070Scrn00_OnClick_Fix(cMsg, sMsg);
            } else if (SCREEN_ID.NFBL2070Scrn00_PagePrev.name().equals(screenAplID)) {
                doProcess_NFBL2070Scrn00_PagePrev(cMsg, sMsg);
            } else if (SCREEN_ID.NFBL2070Scrn00_PageNext.name().equals(screenAplID)) {
                doProcess_NFBL2070Scrn00_PageNext(cMsg, sMsg);
            } else if (SCREEN_ID.NFBL2070Scrn00_PageJump.name().equals(screenAplID)) {
                doProcess_NFBL2070Scrn00_PageJump(cMsg, sMsg);
            } else if (SCREEN_ID.NFBL2070Scrn00_CMN_Clear.name().equals(screenAplID)) {
                doProcess_NFBL2070Scrn00_CMN_Clear(cMsg, sMsg);
            } else if (SCREEN_ID.NFBL2070Scrn00_CMN_Submit.name().equals(screenAplID)) {
                doProcess_NFBL2070Scrn00_CMN_Submit(cMsg, sMsg);
            }  else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(bizMsg, glblMsg);
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2070_INIT(NFBL2070CMsg cMsg, NFBL2070SMsg sMsg) {

        NFBL2070CMsg bizMsg = (NFBL2070CMsg) cMsg;
        NFBL2070SMsg globalMsg = (NFBL2070SMsg) sMsg;

        bizMsg.clear();
        globalMsg.clear();
    }
    
    // START 2016/11/28 [QC#16158, ADD]
    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    public void doProcess_NFBL2070Scrn00_CMN_Reset(NFBL2070CMsg cMsg, NFBL2070SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        if (ZYPConstant.FLG_ON_Y.equals(cMsg.submtFlg.getValue())) {

            EZDMsg.copy(sMsg.B, "B1", sMsg.A, "A1");
            sMsg.A.setValidCount(sMsg.B.getValidCount());

            if (sMsg.B.getValidCount() > cMsg.A.length()) {
                cMsg.A.setValidCount(cMsg.A.length());
            } else {
                cMsg.A.setValidCount(sMsg.B.getValidCount());
            }

            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_P1, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_P1, BigDecimal.valueOf(cMsg.A.getValidCount()));
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum_P1, BigDecimal.valueOf(sMsg.A.getValidCount()));

            NFBL2070CommonLogic.copyCMsgFromSMsg(cMsg, sMsg);
        } else {
            doProcess_NFBL2070_INIT(cMsg, sMsg);
        }
    }
    // END 2016/11/28 [QC#16158, ADD]

    /**
     * The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2070Scrn00_Search(NFBL2070CMsg cMsg, NFBL2070SMsg sMsg){

        //
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        // START 2016/12/01 [QC#16158, ADD]
        ZYPTableUtil.clear(sMsg.B); 
        ZYPEZDItemValueSetter.setValue(cMsg.submtFlg, ZYPConstant.FLG_OFF_N);
        // END 2016/12/01 [QC#16158, ADD]

        NFBL2070Query queryObj = NFBL2070Query.getInstance();
        S21SsmEZDResult ssmResult = queryObj.findDetailData(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {
             List<Map<String, Object>> rossErrList =  (List<Map<String, Object>>) ssmResult.getResultObject();
             
             // Limit over
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) { // MOD 2016/11/28 [QC#16158] MAX_ROW_CNT -> sMsg.A.length()
                // START 2016/11/02 S.Fujita [QC#15734,MOD]
                // cMsg.setMessageInfo( MSG_ID.NFBM0085W.name() );
                cMsg.setMessageInfo(MSG_ID.NZZM0001W.name());
                // END 2016/11/02 S.Fujita [QC#15734,MOD]
                queryResCnt = sMsg.A.length(); // MOD 2016/11/28 [QC#16158] MAX_ROW_CNT -> sMsg.A.length()
            } else if (queryResCnt == 0) {
                // START 2016/10/11 K.Kojima [QC#12824,MOD]
                // cMsg.setMessageInfo(MSG_ID.NFBM0002E.name());
                cMsg.setMessageInfo(MSG_ID.ZZZM9001E.name());
                // END 2016/10/11 K.Kojima [QC#12824,MOD]
            }

             if (rossErrList.size() > cMsg.A.length()) {
                 cMsg.A.setValidCount(cMsg.A.length());
             } else {
                 cMsg.A.setValidCount(rossErrList.size());
             }

             // START 2016/11/21 K.Kojima [QC#16156,ADD]
             if (rossErrList.size() > sMsg.A.length()) {
                 sMsg.A.setValidCount(sMsg.A.length());
             } else {
                 sMsg.A.setValidCount(rossErrList.size());
             }
             // END 2016/11/21 K.Kojima [QC#16156,ADD]

             NFBL2070CommonLogic.setRossErrorData(cMsg, sMsg, rossErrList);

             ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_P1, BigDecimal.ONE);
             ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_P1, BigDecimal.valueOf(cMsg.A.getValidCount()));
             ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum_P1, BigDecimal.valueOf(queryResCnt));
             ZYPEZDItemValueSetter.setValue(sMsg.xxPageShowFromNum_P1, BigDecimal.ONE);
             ZYPEZDItemValueSetter.setValue(sMsg.xxPageShowToNum_P1, BigDecimal.valueOf(cMsg.A.getValidCount()));
             ZYPEZDItemValueSetter.setValue(sMsg.xxPageShowOfNum_P1, BigDecimal.valueOf(queryResCnt));

             //Copy to SMsg from CMsg
             NFBL2070CommonLogic.copyCMsgFromSMsg(cMsg, sMsg);
        } else {
            // START 2016/10/11 K.Kojima [QC#12824,MOD]
            // cMsg.setMessageInfo(MSG_ID.NFBM0002E.name());
            cMsg.setMessageInfo(MSG_ID.ZZZM9001E.name());
            // END 2016/10/11 K.Kojima [QC#12824,MOD]
            cMsg.xxPageShowFromNum_P1.clear();
            cMsg.xxPageShowToNum_P1.clear();
            cMsg.xxPageShowOfNum_P1.clear();
            sMsg.xxPageShowFromNum_P1.clear();
            sMsg.xxPageShowToNum_P1.clear();
            sMsg.xxPageShowOfNum_P1.clear();
        }
    }

    /**
     * The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFBL2070Scrn00_OnClick_Fix(NFBL2070CMsg cMsg, NFBL2070SMsg sMsg){
        //
        NFBL2070CMsg bizMsg = (NFBL2070CMsg) cMsg;
        NFBL2070SMsg globalMsg = (NFBL2070SMsg) sMsg;

        int pagenationFrom = bizMsg.xxPageShowFromNum_P1.getValueInt();
        for (int iCnt=0; iCnt<bizMsg.A.getValidCount(); iCnt++ ){
            if ( bizMsg.A.no(iCnt).xxChkBox_A1.getValue().equals(ZYPConstant.FLG_ON_Y) ) {
                // Nothing to do
            } else {
                // Back the grid data
                bizMsg.A.no(iCnt).xxChkBox_A1.setValue(ZYPConstant.FLG_OFF_N);
                bizMsg.A.no(iCnt).rtlInvPk_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).rtlInvPk_B1.getValue());
                bizMsg.A.no(iCnt).rtlInvLinePk_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).rtlInvLinePk_B1.getValue());
                bizMsg.A.no(iCnt).rtlInvStsCd_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).rtlInvStsCd_B1.getValue());
                bizMsg.A.no(iCnt).billToCustCd_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).billToCustCd_B1.getValue());
                bizMsg.A.no(iCnt).sellToCustCd_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).sellToCustCd_B1.getValue());
                bizMsg.A.no(iCnt).serNum_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).serNum_B1.getValue());
                bizMsg.A.no(iCnt).mdlNm_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).mdlNm_B1.getValue());
                bizMsg.A.no(iCnt).rtlInvApvlDt_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).rtlInvApvlDt_B1.getValue());
                bizMsg.A.no(iCnt).bllgPerFromDt_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).bllgPerFromDt_B1.getValue());
                bizMsg.A.no(iCnt).bllgPerThruDt_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).bllgPerThruDt_B1.getValue());
                bizMsg.A.no(iCnt).rtlInvLineNum_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).rtlInvLineNum_B1.getValue());
                bizMsg.A.no(iCnt).rtlInvChrgTpDescTxt_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).rtlInvChrgTpDescTxt_B1.getValue());
                bizMsg.A.no(iCnt).shipQty_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).shipQty_B1.getValue());
                bizMsg.A.no(iCnt).dealGrsUnitPrcAmt_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).dealGrsUnitPrcAmt_B1.getValue());
                bizMsg.A.no(iCnt).slsTaxRate_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).slsTaxRate_B1.getValue());
                bizMsg.A.no(iCnt).bllgBizTpCd_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).bllgBizTpCd_B1.getValue());
                bizMsg.A.no(iCnt).rtlDivCd_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).rtlDivCd_B1.getValue());
                bizMsg.A.no(iCnt).rtlInvNum_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).rtlInvNum_B1.getValue());
                // START 2016/11/22 T.Tsuchida [QC#16041,MOD]
                //bizMsg.A.no(iCnt).rtlSmryInvNum_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).rtlSmryInvNum_B1.getValue());
                bizMsg.A.no(iCnt).itrlRtlSmryInvNum_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).itrlRtlSmryInvNum_B1.getValue());
                // END 2016/11/22 T.Tsuchida [QC#16041,MOD]
                bizMsg.A.no(iCnt).mdseCd_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).mdseCd_B1.getValue());
                bizMsg.A.no(iCnt).svcDlrCd_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).svcDlrCd_B1.getValue());
                bizMsg.A.no(iCnt).instlPostCd_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).instlPostCd_B1.getValue());
                bizMsg.A.no(iCnt).instlCd_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).instlCd_B1.getValue());
                bizMsg.A.no(iCnt).istlSubLocCd_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).istlSubLocCd_B1.getValue());
                bizMsg.A.no(iCnt).invLineCratDt_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).invLineCratDt_B1.getValue());
                bizMsg.A.no(iCnt).invLineModDt_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).invLineModDt_B1.getValue());
                bizMsg.A.no(iCnt).apInvRossStsCd_A1.setValue(globalMsg.B.no(pagenationFrom - 1 + iCnt).apInvRossStsCd_B1.getValue());
            }
        }

        // Copy To globalMsg from bizMsg
        NFBL2070CommonLogic.copySMsgFromCMsg(bizMsg, globalMsg);
    }

    /**
     * The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFBL2070Scrn00_PagePrev (NFBL2070CMsg cMsg, NFBL2070SMsg sMsg) {

    	NFBL2070CMsg bizMsg = (NFBL2070CMsg) cMsg;
        NFBL2070SMsg globalMsg = (NFBL2070SMsg) sMsg;

        NFBL2070CommonLogic.copySMsgFromCMsg(bizMsg, globalMsg);

        ZYPTableUtil.clear(bizMsg.A);

        int pagenationFrom = bizMsg.xxPageShowFromNum_P1.getValueInt() - bizMsg.A.length();
        int endCnt = pagenationFrom + bizMsg.A.length();
        int cnt = 0;
        for (int i = pagenationFrom; i < endCnt; i++) {

            EZDMsg.copy(globalMsg.A.no(i - 1), null, bizMsg.A.no(i - pagenationFrom), null);
            cnt ++;
        }

        bizMsg.A.setValidCount(cnt);

        bizMsg.xxPageShowFromNum_P1.setValue(pagenationFrom);
        bizMsg.xxPageShowToNum_P1.setValue(pagenationFrom + bizMsg.A.getValidCount() - 1);

    }

    /**
     * The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFBL2070Scrn00_PageNext (NFBL2070CMsg cMsg, NFBL2070SMsg sMsg) {

        NFBL2070CMsg bizMsg = (NFBL2070CMsg) cMsg;
        NFBL2070SMsg globalMsg = (NFBL2070SMsg) sMsg;

        NFBL2070CommonLogic.copySMsgFromCMsg(bizMsg, globalMsg);

        ZYPTableUtil.clear(bizMsg.A);

        int pagenationFrom = bizMsg.xxPageShowFromNum_P1.getValueInt() + bizMsg.A.length();
        int endCnt = pagenationFrom + bizMsg.A.length();
        int cnt = 0;
        for (int i = pagenationFrom; i < endCnt; i++) {

            EZDMsg.copy(globalMsg.A.no(i - 1), null, bizMsg.A.no(i - pagenationFrom), null);
            cnt ++;
        }

        bizMsg.A.setValidCount(cnt);

        bizMsg.xxPageShowFromNum_P1.setValue(pagenationFrom);
        bizMsg.xxPageShowToNum_P1.setValue(pagenationFrom + bizMsg.A.getValidCount() - 1);

        //NFBL2070CommonLogic.copyCMsgFromSMsg(bizMsg, globalMsg);
    }

    /**
     * The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFBL2070Scrn00_PageJump (NFBL2070CMsg cMsg, NFBL2070SMsg sMsg) {

        NFBL2070CMsg bizMsg = (NFBL2070CMsg) cMsg;
        NFBL2070SMsg globalMsg = (NFBL2070SMsg) sMsg;

        NFBL2070CommonLogic.copySMsgFromCMsg(bizMsg, globalMsg);

        ZYPTableUtil.clear(bizMsg.A);

        int pagenationFrom = (bizMsg.xxPageShowCurNum_P1.getValueInt() - 1) * bizMsg.A.length() + 1;

        int endCnt = pagenationFrom + bizMsg.A.length();
        int cnt = 0;
        for (int i = pagenationFrom; i < endCnt; i++) {

            EZDMsg.copy(globalMsg.A.no(i - 1), null, bizMsg.A.no(i - pagenationFrom), null);
            cnt ++;
        }

        bizMsg.A.setValidCount(cnt);

        bizMsg.xxPageShowFromNum_P1.setValue(pagenationFrom);
        bizMsg.xxPageShowToNum_P1.setValue(pagenationFrom + bizMsg.A.getValidCount() - 1);
    }

    /**
     * The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFBL2070Scrn00_CMN_Clear (NFBL2070CMsg cMsg, NFBL2070SMsg sMsg) {

        // START 2016/11/28 [QC#16158, MOD]
//        NFBL2070CMsg bizMsg = (NFBL2070CMsg) cMsg;
//        NFBL2070SMsg globalMsg = (NFBL2070SMsg) sMsg;
//
//        NFBL2070CommonLogic.copySMsgFromCMsg(bizMsg, globalMsg);
//
//        ZYPTableUtil.clear(bizMsg.A);
//        ZYPTableUtil.clear(globalMsg.A);
//
//        bizMsg.A.setValidCount(BigDecimal.ZERO.intValue());
//
//        cMsg.xxPageShowFromNum_P1.clear();
//        cMsg.xxPageShowToNum_P1.clear();
//        cMsg.xxPageShowOfNum_P1.clear();
//        sMsg.xxPageShowFromNum_P1.clear();
//        sMsg.xxPageShowToNum_P1.clear();
//        sMsg.xxPageShowOfNum_P1.clear();
        doProcess_NFBL2070_INIT(cMsg, sMsg);
        // END 2016/11/28 [QC#16158, MOD]
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    public void doProcess_NFBL2070Scrn00_CMN_Submit(NFBL2070CMsg cMsg, NFBL2070SMsg sMsg) {

        // START 2016/11/28 [QC#16158, ADD]
        if ("W".equals(cMsg.getMessageKind())) {
            return;
        }
        // END  2016/11/28 [QC#16158, ADD]

        for (int iCnt=0; iCnt<sMsg.A.length(); iCnt++) {
            sMsg.A.no(iCnt).xxChkBox_A1.setValue(ZYPConstant.FLG_OFF_N);
        }

        NFBL2070CommonLogic.copyCMsgFromSMsg(cMsg, sMsg);
        EZDMsg.copy(sMsg.A, "A1", sMsg.B, "B1"); // ADD 2016/11/28 [QC#16158]77
        
    }
}