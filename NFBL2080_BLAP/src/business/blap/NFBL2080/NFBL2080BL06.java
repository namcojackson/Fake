/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL2080;

import parts.common.EZDCMsg;
import parts.common.EZDMsgCommons;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFBL2080.common.NFBL2080CommonLogic;
import business.blap.NFBL2080.constant.NFBL2080Constant;
import business.db.VND_INV_WRKTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * AP Invoice I/F Error Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   CSAI            Miyauchi        Create          N/A
 * 2016/12/06   Fujitsu         H.Ikeda         Update          QC#15823
 * 2019/01/09   Fujitsu         Y.Matsui        Update          QC#29884
 * 2019/03/25   Hitachi         Y.Takeno        Update          QC#30850
 * </pre>
 */
public class NFBL2080BL06 extends S21BusinessHandler implements NFBL2080Constant {

    @Override
    protected void doProcess(EZDCMsg bizMsg, EZDSMsg glblMsg) {
        super.preDoProcess(bizMsg, glblMsg);

        try {

            String screenAplID = bizMsg.getScreenAplID();
            NFBL2080CMsg cMsg = (NFBL2080CMsg) bizMsg;
            NFBL2080SMsg sMsg = (NFBL2080SMsg) glblMsg;

            cMsg.setCommitSMsg(true);

            // NFBL2080Scrn00_Click_Reprocess
            if (SCRN_MSG_06.NFBL2080Scrn00_Click_Reprocess.name().equals(screenAplID)) {
                doProcess_NFBL2080_Click_Reprocess(cMsg, sMsg);
            } else if (SCRN_MSG_06.NFBL2080Scrn00_Click_Cancel.name().equals(screenAplID)) {
                doProcess_NFBL2080_Click_Cancel(cMsg, sMsg);
            } else if (SCRN_MSG_06.NFBL2080Scrn00_CMN_Submit.name().equals(screenAplID)) {
            	doProcess_NFBL2080_Click_Submit(cMsg, sMsg);
            } else {
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
    private void doProcess_NFBL2080_Click_Reprocess(NFBL2080CMsg cMsg, NFBL2080SMsg sMsg) {

    	//Update Vendor Process Status Code
        updateVndInvProcStsCd(cMsg, sMsg, VND_INV_PROC_STS_CD_LIST.S.name());

        // Clear the A List
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        sMsg.A.clear();
        sMsg.A.setValidCount(0);

        // Clear [Header] tab information.
        NFBL2080CommonLogic.clearHeaderTab(cMsg, sMsg);

        // Clear [Detail] tab information.
        NFBL2080CommonLogic.clearDetailTab(cMsg, sMsg);

        // Set Display Tab Index
        NFBL2080CommonLogic.getSetTabIndex(cMsg, sMsg);

        // Retrieve search condition.
        NFBL2080CommonLogic.retrieveSearchCondition(cMsg);

        // Find vendor invoice data
        // START 2019/01/09 Y.Matsui [QC#29884,MOD]
        NFBL2080CommonLogic.findAndSetVndInvDataList(cMsg, sMsg, false);
        // END   2019/01/09 Y.Matsui [QC#29884,MOD]

        // Process ended normally.
        cMsg.setMessageInfo(MSG_ID.ZZM8100I.name());
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2080_Click_Cancel(NFBL2080CMsg cMsg, NFBL2080SMsg sMsg) {

        // Update Vendor Process Status Code
        updateVndInvProcStsCd(cMsg, sMsg, VND_INV_PROC_STS_CD_LIST.C.name());

        // Clear the A List
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        sMsg.A.clear();
        sMsg.A.setValidCount(0);

        // Clear [Header] tab information.
        NFBL2080CommonLogic.clearHeaderTab(cMsg, sMsg);

        // Clear [Detail] tab information.
        NFBL2080CommonLogic.clearDetailTab(cMsg, sMsg);

        // Set Display Tab Index
        NFBL2080CommonLogic.getSetTabIndex(cMsg, sMsg);

        // Retrieve search condition.
        NFBL2080CommonLogic.retrieveSearchCondition(cMsg);

        // Find vendor invoice data
        // START 2019/01/09 Y.Matsui [QC#29884,MOD]
        NFBL2080CommonLogic.findAndSetVndInvDataList(cMsg, sMsg, false);
        // END   2019/01/09 Y.Matsui [QC#29884,MOD]

        // Process ended normally.
        cMsg.setMessageInfo(MSG_ID.ZZM8100I.name());
    }
    

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg
     * @param sMsg
     * @param updateStsCd
     */
    private void updateVndInvProcStsCd( NFBL2080CMsg cMsg, NFBL2080SMsg sMsg, String updateStsCd ){
        
        VND_INV_WRKTMsg updateTable = new VND_INV_WRKTMsg();
        VND_INV_WRKTMsg findTable = new VND_INV_WRKTMsg();
        // START 2016/12/06 H.Ikeda [QC#15823,MOD]
        VND_INV_WRKTMsg updateTables[] = new VND_INV_WRKTMsg[cMsg.A.getValidCount()];
        int msgCnt = 0;
        for (int iCnt=0; iCnt<cMsg.A.getValidCount(); iCnt++) {

            if (cMsg.A.no(iCnt).xxChkBox_A1.getValue().equals(FLG.Y.name())) {

                ZYPEZDItemValueSetter.setValue(findTable.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(findTable.vndInvNum, cMsg.A.no(iCnt).vndInvNum_A1);
                updateTable = (VND_INV_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(findTable);

                // Update Status
                ZYPEZDItemValueSetter.setValue(updateTable.vndInvProcStsCd, updateStsCd);
                
                updateTables[msgCnt] = updateTable;
                msgCnt++;
                //EZDTBLAccessor.update(updateTable);
                
                //if (!RTNCD_NORMAL.equals(updateTable.getReturnCode())) {
                //	cMsg.setMessageInfo("NFCM0032E");
                //    return;
                //}
            }
        }
        if (msgCnt > 0) {
            int updateCnt = S21FastTBLAccessor.update(updateTables);
            if (updateCnt != msgCnt) {
                cMsg.setMessageInfo("NFCM0032E");
                // roll back
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return;
            }
        }
        // START 2016/12/06 H.Ikeda [QC#15823,MOD]
    }

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFBL2080_Click_Submit( NFBL2080CMsg cMsg, NFBL2080SMsg sMsg ) {

        //Update Header Information
        NFBL2080CommonLogic.updatePoInfo(cMsg, getGlobalCompanyCode() );
        // START 2019/03/25 [QC#30850, ADD]
        if (MESSAGE_KIND_E.equals(cMsg.getMessageKind())) {
            return;
        }
        cMsg.xxDplyTab.clear();
        // END   2019/03/25 [QC#30850, ADD]

        // Clear the A List
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        sMsg.A.clear();
        sMsg.A.setValidCount(0);

        // Clear [Header] tab information.
        NFBL2080CommonLogic.clearHeaderTab(cMsg, sMsg);

        // Clear [Detail] tab information.
        NFBL2080CommonLogic.clearDetailTab(cMsg, sMsg);

        // Set Display Tab Index
        NFBL2080CommonLogic.getSetTabIndex(cMsg, sMsg);

        // Retrieve search condition.
        NFBL2080CommonLogic.retrieveSearchCondition(cMsg);

        // Find vendor invoice data
        // START 2019/01/09 Y.Matsui [QC#29884,MOD]
        NFBL2080CommonLogic.findAndSetVndInvDataList(cMsg, sMsg, false);
        // END   2019/01/09 Y.Matsui [QC#29884,MOD]

        // Process ended normally.
        cMsg.setMessageInfo(MSG_ID.ZZM8100I.name());
    }
}
