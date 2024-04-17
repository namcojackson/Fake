/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL0170;

import static business.blap.NMAL0170.constant.NMAL0170Constant.NMAM0007I;
import static business.blap.NMAL0170.constant.NMAL0170Constant.NMAM0050E;
import static business.blap.NMAL0170.constant.NMAL0170Constant.NMAM8121E;
import static business.blap.NMAL0170.constant.NMAL0170Constant.NMAM8175E;
import static business.blap.NMAL0170.constant.NMAL0170Constant.NZZM0001W;
import static business.blap.NMAL0170.constant.NMAL0170Constant.ZZM8100I;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.CHKBOX_ON_Y;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL0170.common.NMAL0170CommonLogic;
import business.db.SUPD_RELN_STAGETMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL0170BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Fujitsu         T.Arima          Create          N/A
 * 2015/12/11   Fujitsu         T.Arima          Update          QC#1882
 * 2016/02/23   CITS            S.Tanikawa       Update          QC#2669
 * 2016/04/21   CITS            S.Tanikawa       Update          QC#6176
 * 2016/11/10   Fujitsu         R.Nakamura       Update          QC#2872
 * 2017/01/23   Fujitsu         R.Nakamura       Update          QC#17195
 * 2017/02/09   Fujitsu         K.Ishizuka       Update          QC#17513
 * 2017/02/09   Fujitsu         K.Ishizuka       Update          QC#17463 
 * 2017/02/09   Fujitsu         K.Ishizuka       Update          QC#17259 
 *</pre>
 */
public class NMAL0170BL02 extends S21BusinessHandler {

    @Override
    /*
     * Do Process Event.
     */
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL0170CMsg bizMsg = (NMAL0170CMsg) cMsg;
            NMAL0170SMsg glblMsg = (NMAL0170SMsg) sMsg;

            if ("NMAL0170_INIT".equals(screenAplID)) {
                doProcess_NMAL0170_INIT(bizMsg, glblMsg);
            } else if ("NMAL0170Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL0170Scrn00_Search(bizMsg, glblMsg);
            } else if ("NMAL0170Scrn00_DeleteRow".equals(screenAplID)) {
                doProcess_NMAL0170Scrn00_Delete_Row(bizMsg, glblMsg);
            } else if ("NMAL0170Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NMAL0170Scrn00_Insert_Row(bizMsg, glblMsg); // ADD S21_NA #17463
            } else if ("NMAL0170Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL0170Scrn00_Clear(bizMsg, glblMsg);
            } else if ("NMAL0170Scrn00_PageJump".equals(screenAplID)) { // MOD S21_NA #17513
                doProcess_NMAL0170_PageJump(bizMsg, (NMAL0170SMsg) sMsg);
            } else if ("NMAL0170Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL0170_PagePrev(bizMsg, (NMAL0170SMsg) sMsg);
            } else if ("NMAL0170Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL0170_PageNext(bizMsg, (NMAL0170SMsg) sMsg);
            // ADD START 2016/04/21 QC#6176
            } else if ("NMAL0170Scrn00_SetItemDescriptionFm".equals(screenAplID)) {
                doProcess_NMAL0170Scrn00_SetItemDescriptoin(bizMsg, glblMsg, true);
            } else if ("NMAL0170Scrn00_SetItemDescriptionTo".equals(screenAplID)) {
                doProcess_NMAL0170Scrn00_SetItemDescriptoin(bizMsg, glblMsg, false);
            } else if ("NMAL0170Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL0170Scrn00_Search(bizMsg, glblMsg);
            } else if ("NMAL0170_NMAL0190".equals(screenAplID)) {
                doProcess_NMAL0170Scrn00_Search(bizMsg, glblMsg);
            }
            // ADD END  2016/04/21 QC#6176
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    // MOD START S21_NA #17463
    /**
     * INIT Event
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
     //private void doProcess_NMAL0170_INIT(NMAL0170CMsg bizMsg, NMAL0170SMsg glblMsg) {
     //   NMAL0170CommonLogic.createPulldownList(bizMsg);
     //}
    private void doProcess_NMAL0170_INIT(NMAL0170CMsg bizMsg, NMAL0170SMsg glblMsg) {
        NMAL0170CommonLogic.createPulldownList(bizMsg, glblMsg);
    }
    // MOD END S21_NA #17463
    
    // ADD START S21_NA #17463
    /**
     * INSERT ROW Event
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
    private void doProcess_NMAL0170Scrn00_Insert_Row(NMAL0170CMsg bizMsg, NMAL0170SMsg glblMsg) {
        NMAL0170CommonLogic.updateSMsg(bizMsg, glblMsg);
        if (glblMsg.A.getValidCount() == glblMsg.A.length()) {
            bizMsg.setMessageInfo(NMAM0050E, new String[] {Integer.toString(glblMsg.A.length()) });
            return;
        }
        bizMsg.xxPageShowFromNum.setValue(glblMsg.A.getValidCount());
        glblMsg.A.setValidCount(glblMsg.A.getValidCount() + 1);
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(glblMsg.A.getValidCount() -1).supdRelnStageDt_A1, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        NMAL0170CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }
    // ADD END S21_NA #17463

    /**
     * DELETE ROW Event
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
    private void doProcess_NMAL0170Scrn00_Delete_Row(NMAL0170CMsg bizMsg, NMAL0170SMsg glblMsg) {
        NMAL0170CommonLogic.updateSMsg(bizMsg, glblMsg);
        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(glblMsg.A, "xxChkBox_A1", CHKBOX_ON_Y);
        //List<Integer> deleteIndex = new ArrayList<Integer>();
        // ADD start S21_NA #17259
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(glblMsg.B);
        // ADD end S21_NA #17259
        for (int index : selectRows) {
           // BigDecimal supdRelnStagePk = bizMsg.A.no(index).supdRelnStagePk_A1.getValue();
            if (ZYPCommonFunc.hasValue( glblMsg.A.no(index).supdRelnStagePk_A1)) {
                // MOD start S21_NA #17259
                int validCnt = glblMsg.B.getValidCount();
                ZYPEZDItemValueSetter.setValue(glblMsg.B.no(validCnt).supdRelnStagePk_A2, glblMsg.A.no(index).supdRelnStagePk_A1);
                ZYPEZDItemValueSetter.setValue(glblMsg.B.no(validCnt).xxRecHistUpdTs_A2, glblMsg.A.no(index).xxRecHistUpdTs_A1);
                ZYPEZDItemValueSetter.setValue(glblMsg.B.no(validCnt).ezUpTimeZone_A2, glblMsg.A.no(index).ezUpTimeZone_A1);
                glblMsg.B.setValidCount(validCnt + 1);
                // Delete Row from Insert Row.
                //deleteIndex.add(index);
//            } else {
//                // Delete Row form existed SUPD_RELN_STAGE.
//                SUPD_RELN_STAGETMsg deleteMsg = NMAL0170CommonLogic.findSupdRelnStage(getGlobalCompanyCode(), supdRelnStagePk);
//                EZDTBLAccessor.logicalRemove(deleteMsg);
//                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(deleteMsg.getReturnCode())) {
//                    // NMAM8175E=0,This data has been updated by
//                    // another user.
//                    // [ TableName = @ , key = @, value= @ ]
//                    bizMsg.setMessageInfo(NMAM8175E, new String[] {"SUPD_RELN_STAGE", "SUPD_RELN_STAGE_PK", supdRelnStagePk.toString() });
//                } else {
//                    deleteIndex.add(index);
//                }
                // MOD end S21_NA #17259
            }
        }
        ZYPTableUtil.deleteRows(glblMsg.A, selectRows);
        if (bizMsg.xxPageShowFromNum.getValueInt() > glblMsg.A.getValidCount()) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum, BigDecimal.valueOf(glblMsg.A.getValidCount() - 1));
        }
        NMAL0170CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }

    // MOD START S21_NA #17463
    /**
     * CLEAR Event
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
    private void doProcess_NMAL0170Scrn00_Clear(NMAL0170CMsg bizMsg, NMAL0170SMsg glblMsg) {
        NMAL0170CommonLogic.createPulldownList(bizMsg, glblMsg);
    }
    // MOD END S21_NA #17463

    /**
     * Search Event
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
    private void doProcess_NMAL0170Scrn00_Search(NMAL0170CMsg bizMsg, NMAL0170SMsg glblMsg) {
        // search
        search(bizMsg, glblMsg);
    }

    /**
     * Search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NMAL0170CMsg bizMsg, NMAL0170SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        S21SsmEZDResult ssmResult = NMAL0170Query.getInstance().search(bizMsg, glblMsg);

        if (ssmResult.isCodeNotFound()) {

            bizMsg.setMessageInfo(NMAM0007I);

        // UPDATE START 2016/02/25 QC#2669
        } else if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length());
            }

            // EZDMsg.copy(glblMsg.A, null, bizMsg.A, null);
            // Copy 1 page Data(SMsg -> CMsg)
            int i = 0;
            for (; i < queryResCnt; i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }
                // Add Start 2016/11/10 QC#2872
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(glblMsg.A.no(0).xxRecHistCratByNm_B1.getValue()));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(glblMsg.A.no(0).xxRecHistCratByNm_B1.getValue()));
                // Add End 2016/11/10 QC#2872
                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i), null);
            }
            bizMsg.A.setValidCount(i);

            // Add Start 2017/01/23 QC#17195
            bizMsg.setMessageInfo(ZZM8100I);
            // Add End 2017/01/23 QC#17195

            // Setting Next Page
            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum.setValue(queryResCnt);
        } else {
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
        }
        // UPDATE START 2016/02/25 QC#2669
    }

    // ADD START 2016/02/23 QC#2669
    private void doProcess_NMAL0170_PageNext(NMAL0170CMsg cMsg, NMAL0170SMsg sMsg) {

        // Save from cMsg to sMsg
        NMAL0170CommonLogic.copyFromSMsgToCMsg(cMsg, sMsg, true);

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                // Add Start 2016/11/10 QC#2872
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(0).xxRecHistCratByNm_B1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(0).xxRecHistCratByNm_B1.getValue()));
                // Add End 2016/11/10 QC#2872
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to page items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
    }

    private void doProcess_NMAL0170_PagePrev(NMAL0170CMsg cMsg, NMAL0170SMsg sMsg) {

        // Save from cMsg to sMsg
        NMAL0170CommonLogic.copyFromSMsgToCMsg(cMsg, sMsg, true);

        // copy data from SMsg to CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                // Add Start 2016/11/10 QC#2872
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(0).xxRecHistCratByNm_B1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(0).xxRecHistCratByNm_B1.getValue()));
                // Add End 2016/11/10 QC#2872
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to page items
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pagenationFrom);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount() - 1);
    }

    private void doProcess_NMAL0170_PageJump(NMAL0170CMsg cMsg, NMAL0170SMsg sMsg) {

        // Save from cMsg to sMsg
        NMAL0170CommonLogic.copyFromSMsgToCMsg(cMsg, sMsg, true);
        
        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                // Add Start 2016/11/10 QC#2872
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(0).xxRecHistCratByNm_B1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(0).xxRecHistCratByNm_B1.getValue()));
                // Add End 2016/11/10 QC#2872
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to page items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
    }
    // ADD END 2016/02/23 QC#2669
    // ADD START 2016/04/21 QC#6176
    private void doProcess_NMAL0170Scrn00_SetItemDescriptoin(NMAL0170CMsg cMsg, NMAL0170SMsg sMsg, boolean isFrom) {

        int idx = cMsg.xxCellIdx.getValueInt();
        String mdseCd = "";
        if (isFrom) {
            mdseCd = cMsg.A.no(idx).supdFromMdseCd_A1.getValue();

        } else {
            mdseCd = cMsg.A.no(idx).supdToMdseCd_A1.getValue();
        }

        S21SsmEZDResult result = NMAL0170Query.getInstance().getItemDescription(mdseCd);
        if (result.isCodeNormal()) {
            Map resultItemInfo = (Map) result.getResultObject();
            if (isFrom) {
                if (ZYPCommonFunc.hasValue((String) resultItemInfo.get("MDSE_DESC_SHORT_TXT"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).mdseDescShortTxt_AF, resultItemInfo.get("MDSE_DESC_SHORT_TXT").toString());
                }
            } else {
                if (ZYPCommonFunc.hasValue((String) resultItemInfo.get("MDSE_DESC_SHORT_TXT"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).mdseDescShortTxt_AT, resultItemInfo.get("MDSE_DESC_SHORT_TXT").toString());
                }
                if (ZYPCommonFunc.hasValue((String) resultItemInfo.get("MDSE_ITEM_TP_NM"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).mdseItemTpNm_A1, resultItemInfo.get("MDSE_ITEM_TP_NM").toString());
                }
                if (ZYPCommonFunc.hasValue((String) resultItemInfo.get("MDSE_ITEM_CLS_TP_NM"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).mdseItemClsTpNm_A1, resultItemInfo.get("MDSE_ITEM_CLS_TP_NM").toString());
                }
            }
        } else {
            if (isFrom) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).mdseDescShortTxt_AF, "");
                cMsg.A.no(idx).supdFromMdseCd_A1.setErrorInfo(1, NMAM8121E, new String[] {"MDSE Code" });
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).mdseDescShortTxt_AT, "");
                cMsg.A.no(idx).supdToMdseCd_A1.setErrorInfo(1, NMAM8121E, new String[] {"MDSE Code" });
            }
        }
    }
    // ADD END 2016/04/21 QC#6176
}
