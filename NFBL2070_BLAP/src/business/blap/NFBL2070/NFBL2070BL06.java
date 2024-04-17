/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL2070;

import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFBL2070.common.NFBL2070CommonLogic;
import business.blap.NFBL2070.constant.NFBL2070Constant;
import business.db.AP_INV_ROSSTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Compensation Credit I/F Error Correction
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/09   CSAI            Miyauchi        Create          N/A
 * 2016/11/22   Hitachi         T.Tsuchida      Update          QC#16041
 * 2016/11/28   Fujitsu         T.Murai         Update          QC#16158
 * 2016/12/05   Fujitsu         H.Ikeda         Update          QC#12865
 * </pre>
 */
public class NFBL2070BL06 extends S21BusinessHandler implements NFBL2070Constant {

    @Override
    protected void doProcess(EZDCMsg bizMsg, EZDSMsg glblMsg) {
        super.preDoProcess(bizMsg, glblMsg);

        try {

            String screenAplID = bizMsg.getScreenAplID();
            NFBL2070CMsg cMsg = (NFBL2070CMsg) bizMsg;
            NFBL2070SMsg sMsg = (NFBL2070SMsg) glblMsg;

            cMsg.setCommitSMsg(true);

            // NFBL2080Scrn00_Click_Reprocess
            if (SCREEN_ID.NFBL2070Scrn00_CMN_Submit.name().equals(screenAplID)) {
                doProcess_NFBL2070Scrn00_CMN_Submit(cMsg, sMsg);
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
    public void doProcess_NFBL2070Scrn00_CMN_Submit(NFBL2070CMsg cMsg, NFBL2070SMsg sMsg) {

        // Copy to SMsg from CMsg
        NFBL2070CommonLogic.copySMsgFromCMsg(cMsg, sMsg);

        // START 2016/11/28 [QC#16158, ADD]
        List<Integer> checkList = ZYPTableUtil.getSelectedRows(cMsg.A, "xxChkBox_A1", ZYPConstant.FLG_ON_Y);
        if (checkList.size() == 0) {
            cMsg.setMessageInfo(MSG_ID.NZZM0011E.name());
            return;
        }
        // END 2016/11/28 [QC#16158, ADD]

        AP_INV_ROSSTMsg arInvRossTMsg = new AP_INV_ROSSTMsg();
        // START 2016/12/05 H.Ikeda [QC#15823,MOD]
//        for (int iCnt=0; iCnt<sMsg.A.getValidCount(); iCnt++) {
//            if ( sMsg.A.no(iCnt).xxChkBox_A1.getValue().equals(ZYPConstant.FLG_ON_Y) ) {
//                arInvRossTMsg.clear();
//
//                arInvRossTMsg.glblCmpyCd.setValue( getGlobalCompanyCode() );
//                arInvRossTMsg.rtlInvPk.setValue(sMsg.A.no(iCnt).rtlInvPk_A1.getValue());
//                arInvRossTMsg.rtlInvLinePk.setValue(sMsg.A.no(iCnt).rtlInvLinePk_A1.getValue());
//
//                // Get ROSS data by Key
//                arInvRossTMsg = (AP_INV_ROSSTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(arInvRossTMsg);
//
//                //Update the data
//                //ZYPEZDItemValueSetter.setValue(arInvRossTMsg.rtlInvPk, sMsg.A.no(iCnt).rtlInvPk_A1);
//                //ZYPEZDItemValueSetter.setValue(arInvRossTMsg.rtlInvLinePk, sMsg.A.no(iCnt).rtlInvLinePk_A1);
//                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.rtlInvStsCd, sMsg.A.no(iCnt).rtlInvStsCd_A1);
//                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.billToCustCd, sMsg.A.no(iCnt).billToCustCd_A1);
//                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.sellToCustCd, sMsg.A.no(iCnt).sellToCustCd_A1);
//                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.serNum, sMsg.A.no(iCnt).serNum_A1);
//                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.mdlNm, sMsg.A.no(iCnt).mdlNm_A1);
//                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.rtlInvApvlDt, sMsg.A.no(iCnt).rtlInvApvlDt_A1);
//                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.bllgPerFromDt, sMsg.A.no(iCnt).bllgPerFromDt_A1);
//                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.bllgPerThruDt, sMsg.A.no(iCnt).bllgPerThruDt_A1);
//                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.rtlInvLineNum, sMsg.A.no(iCnt).rtlInvLineNum_A1);
//                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.rtlInvChrgTpDescTxt, sMsg.A.no(iCnt).rtlInvChrgTpDescTxt_A1);
//                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.shipQty, sMsg.A.no(iCnt).shipQty_A1);
//                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.dealGrsUnitPrcAmt, sMsg.A.no(iCnt).dealGrsUnitPrcAmt_A1);
//                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.slsTaxRate, sMsg.A.no(iCnt).slsTaxRate_A1);
//                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.bllgBizTpCd, sMsg.A.no(iCnt).bllgBizTpCd_A1);
//                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.rtlDivCd, sMsg.A.no(iCnt).rtlDivCd_A1);
//                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.rtlInvNum, sMsg.A.no(iCnt).rtlInvNum_A1);
//                // START 2016/11/22 T.Tsuchida [QC#16041,MOD]
//                //ZYPEZDItemValueSetter.setValue(arInvRossTMsg.rtlSmryInvNum, sMsg.A.no(iCnt).rtlSmryInvNum_A1);
//                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.itrlRtlSmryInvNum, sMsg.A.no(iCnt).itrlRtlSmryInvNum_A1);
//                // END 2016/11/22 T.Tsuchida [QC#16041,MOD]
//                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.mdseCd, sMsg.A.no(iCnt).mdseCd_A1);
//                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.svcDlrCd, sMsg.A.no(iCnt).svcDlrCd_A1);
//                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.instlPostCd, sMsg.A.no(iCnt).instlPostCd_A1);
//                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.instlCd, sMsg.A.no(iCnt).instlCd_A1);
//                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.istlSubLocCd, sMsg.A.no(iCnt).istlSubLocCd_A1);
//                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.invLineCratDt, sMsg.A.no(iCnt).invLineCratDt_A1);
//                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.invLineModDt, sMsg.A.no(iCnt).invLineModDt_A1);
//                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.apInvRossStsCd, sMsg.A.no(iCnt).apInvRossStsCd_A1);
//
//                EZDTBLAccessor.update(arInvRossTMsg);
//                // START 2016/11/28 [QC#16158, ADD]
//                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(arInvRossTMsg.getReturnCode())) {
//                    cMsg.setMessageInfo(MSG_ID.NFBM0073E.name(), new String[] {"AP_INV_ROSS", arInvRossTMsg.getReturnCode()} );
//                }
//                // END 2016/11/28 [QC#16158, ADD]
//            }
//        }
        AP_INV_ROSSTMsg arInvRossTMsgs[] = new AP_INV_ROSSTMsg[sMsg.A.getValidCount()];
        int cnt = 0;
        for (int iCnt=0; iCnt<sMsg.A.getValidCount(); iCnt++) {
            if ( sMsg.A.no(iCnt).xxChkBox_A1.getValue().equals(ZYPConstant.FLG_ON_Y) ) {
                arInvRossTMsg.clear();

                arInvRossTMsg.glblCmpyCd.setValue( getGlobalCompanyCode() );
                arInvRossTMsg.rtlInvPk.setValue(sMsg.A.no(iCnt).rtlInvPk_A1.getValue());
                arInvRossTMsg.rtlInvLinePk.setValue(sMsg.A.no(iCnt).rtlInvLinePk_A1.getValue());

                // Get ROSS data by Key
                arInvRossTMsg = (AP_INV_ROSSTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(arInvRossTMsg);

                //Update the data
                //ZYPEZDItemValueSetter.setValue(arInvRossTMsg.rtlInvPk, sMsg.A.no(iCnt).rtlInvPk_A1);
                //ZYPEZDItemValueSetter.setValue(arInvRossTMsg.rtlInvLinePk, sMsg.A.no(iCnt).rtlInvLinePk_A1);
                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.rtlInvStsCd, sMsg.A.no(iCnt).rtlInvStsCd_A1);
                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.billToCustCd, sMsg.A.no(iCnt).billToCustCd_A1);
                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.sellToCustCd, sMsg.A.no(iCnt).sellToCustCd_A1);
                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.serNum, sMsg.A.no(iCnt).serNum_A1);
                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.mdlNm, sMsg.A.no(iCnt).mdlNm_A1);
                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.rtlInvApvlDt, sMsg.A.no(iCnt).rtlInvApvlDt_A1);
                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.bllgPerFromDt, sMsg.A.no(iCnt).bllgPerFromDt_A1);
                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.bllgPerThruDt, sMsg.A.no(iCnt).bllgPerThruDt_A1);
                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.rtlInvLineNum, sMsg.A.no(iCnt).rtlInvLineNum_A1);
                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.rtlInvChrgTpDescTxt, sMsg.A.no(iCnt).rtlInvChrgTpDescTxt_A1);
                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.shipQty, sMsg.A.no(iCnt).shipQty_A1);
                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.dealGrsUnitPrcAmt, sMsg.A.no(iCnt).dealGrsUnitPrcAmt_A1);
                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.slsTaxRate, sMsg.A.no(iCnt).slsTaxRate_A1);
                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.bllgBizTpCd, sMsg.A.no(iCnt).bllgBizTpCd_A1);
                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.rtlDivCd, sMsg.A.no(iCnt).rtlDivCd_A1);
                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.rtlInvNum, sMsg.A.no(iCnt).rtlInvNum_A1);
                // START 2016/11/22 T.Tsuchida [QC#16041,MOD]
                //ZYPEZDItemValueSetter.setValue(arInvRossTMsg.rtlSmryInvNum, sMsg.A.no(iCnt).rtlSmryInvNum_A1);
                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.itrlRtlSmryInvNum, sMsg.A.no(iCnt).itrlRtlSmryInvNum_A1);
                // END 2016/11/22 T.Tsuchida [QC#16041,MOD]
                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.mdseCd, sMsg.A.no(iCnt).mdseCd_A1);
                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.svcDlrCd, sMsg.A.no(iCnt).svcDlrCd_A1);
                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.instlPostCd, sMsg.A.no(iCnt).instlPostCd_A1);
                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.instlCd, sMsg.A.no(iCnt).instlCd_A1);
                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.istlSubLocCd, sMsg.A.no(iCnt).istlSubLocCd_A1);
                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.invLineCratDt, sMsg.A.no(iCnt).invLineCratDt_A1);
                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.invLineModDt, sMsg.A.no(iCnt).invLineModDt_A1);
                ZYPEZDItemValueSetter.setValue(arInvRossTMsg.apInvRossStsCd, sMsg.A.no(iCnt).apInvRossStsCd_A1);

                arInvRossTMsgs[cnt] = arInvRossTMsg;
                cnt++;
            }

        }
        if (cnt > 0) {
            int updateCnt = S21FastTBLAccessor.update(arInvRossTMsgs);
            if (updateCnt != cnt) {
                cMsg.setMessageInfo(MSG_ID.NFZM0027E.name());
            }
        }
        // END 2016/12/05 H.Ikeda [QC#15823,MOD]

        // START 2016/11/28 [QC#16158, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.submtFlg, ZYPConstant.FLG_ON_Y);
        cMsg.setMessageInfo(MSG_ID.NZZM0002I.name());
        // END 2016/11/28 [QC#16158, ADD]

    }
}