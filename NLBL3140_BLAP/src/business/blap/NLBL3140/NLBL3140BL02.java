/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL3140;

import static business.blap.NLBL3140.constant.NLBL3140Constant.CHKBOX_ON_A_NM;
import static business.blap.NLBL3140.constant.NLBL3140Constant.NLBM1365I;
import static business.blap.NLBL3140.constant.NLBL3140Constant.NLBM0081E;
import static business.blap.NLBL3140.constant.NLBL3140Constant.NLBM1232I;
import static business.blap.NLBL3140.constant.NLBL3140Constant.NZZM0001W;
import static business.blap.NLBL3140.constant.NLBL3140Constant.ZZM9000E;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLBL3140.common.NLBL3140CommonLogic;
import business.db.COA_PRODTMsg;
import business.db.RTL_SWHTMsg;
import business.db.RTL_SWHTMsgArray;
import business.db.RTL_WHTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HARD_ALLOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NLBL3140BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/07/21   Fujitsu         K.Ishizuka      Create          N/A
 * 2017/08/16   Fujitsu         S.Yamamoto      Update          QC#20555
 * 2017/10/24   CITS            T.Kikuhara      Update          QC#20772
 * 2023/07/10   Hitachi         G.Quan          Update          QC#61543
 *</pre>
 */
public class NLBL3140BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NLBL3140CMsg bizMsg = (NLBL3140CMsg) cMsg;
            NLBL3140SMsg glblMsg = (NLBL3140SMsg) sMsg;

            if ("NLBL3140_INIT".equals(screenAplID)) {
                doProcess_NLBL3140Scrn00_INIT(bizMsg, glblMsg);
            } else if ("NLBL3140Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NLBL3140Scrn00_INIT(bizMsg, glblMsg);
            } else if ("NLBL3140Scrn00_AddNewRow".equals(screenAplID)) {
                doProcess_NLBL3140Scrn00_AddNewRow(bizMsg, glblMsg);
            } else if ("NLBL3140Scrn00_LineCancel".equals(screenAplID)) {
                doProcess_NLBL3140Scrn00_LineCancel(bizMsg, glblMsg);
            } else if ("NLBL3140Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NLBL3140Scrn00_PageNext(bizMsg, glblMsg);
            } else if ("NLBL3140Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NLBL3140Scrn00_PagePrev(bizMsg, glblMsg);
            } else if ("NLBL3140Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NLBL3140Scrn00_PageJump(bizMsg, glblMsg);
            } else if ("NLBL3140Scrn00_SelectAll".equals(screenAplID)) {
                doProcess_NLBL3140Scrn00_SelectAll(bizMsg, glblMsg);
            } else if ("NLBL3140Scrn00_UnSelectAll".equals(screenAplID)) {
                doProcess_NLBL3140Scrn00_UnSelectAll(bizMsg, glblMsg);
            } else if ("NLBL3140Scrn00_Search".equals(screenAplID)) {
                doProcess_NLBL3140Scrn00_Search(bizMsg, glblMsg);
            } else if ("NLBL3140Scrn00_SetWh".equals(screenAplID)) {
                doProcess_NLBL3140Scrn00_SetWh(bizMsg, glblMsg);
            } else if ("NLBL3140Scrn00_SetSwh".equals(screenAplID)) {
                doProcess_NLBL3140Scrn00_SetSwh(bizMsg, glblMsg);
            } else if ("NLBL3140Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLBL3140Scrn00_Search(bizMsg, glblMsg);
             // START 2023/07/10 G.Quan [QC#61543, ADD]
            } else if ("NLBL3140Scrn00_SetPC".equals(screenAplID)) {
                doProcess_NLBL3140Scrn00_SetPC(bizMsg, glblMsg);
            }
            // End 2023/07/10 G.Quan [QC#61543, ADD]
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * execute initial process
     * @param bizMsg
     * @param glblMsg
     */
    private void doProcess_NLBL3140Scrn00_INIT(NLBL3140CMsg bizMsg, NLBL3140SMsg glblMsg) {
        bizMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        glblMsg.clear();
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(glblMsg.C);

        ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, bizMsg.lineBizTpCd_PL, bizMsg.lineBizTpDescTxt_PL);
        ZYPCodeDataUtil.createPulldownList(HARD_ALLOC_TP.class, bizMsg.hardAllocTpCd_PL, bizMsg.hardAllocTpDescTxt_PL);

        NLBL3140CommonLogic.setAuthority(bizMsg, getUserProfileService());
    }

    /**
     * execute Add New Row button
     * @param bizMsg
     * @param sMsg
     */
    private void doProcess_NLBL3140Scrn00_AddNewRow(NLBL3140CMsg bizMsg, NLBL3140SMsg glblMsg) {

        NLBL3140CommonLogic.updateSMsg(bizMsg, glblMsg);
        if (glblMsg.A.getValidCount() == glblMsg.A.length()) {
            bizMsg.setMessageInfo(NLBM0081E, new String[] {Integer.toString(glblMsg.A.length()) });
            return;
        }
        bizMsg.xxPageShowFromNum.setValue(glblMsg.A.getValidCount());
        glblMsg.A.setValidCount(glblMsg.A.getValidCount() + 1);
        NLBL3140CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }

    /**
     * execute Delete button to delete row from table
     * @param bizMsg
     * @param sMsg
     */
    private void doProcess_NLBL3140Scrn00_LineCancel(NLBL3140CMsg bizMsg, NLBL3140SMsg glblMsg) {
        NLBL3140CommonLogic.updateSMsg(bizMsg, glblMsg);
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(glblMsg.A, CHKBOX_ON_A_NM, ZYPConstant.CHKBOX_ON_Y);

        for (int index : selectedRows) {
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(index).mdseWhCondPk_A)) {
                int validCnt = glblMsg.B.getValidCount();
                ZYPEZDItemValueSetter.setValue(glblMsg.B.no(validCnt).mdseWhCondPk_B, glblMsg.A.no(index).mdseWhCondPk_A);
                ZYPEZDItemValueSetter.setValue(glblMsg.B.no(validCnt).ezUpTime_B, glblMsg.A.no(index).ezUpTime_A);
                ZYPEZDItemValueSetter.setValue(glblMsg.B.no(validCnt).ezUpTimeZone_B, glblMsg.A.no(index).ezUpTimeZone_A);
                glblMsg.B.setValidCount(validCnt + 1);
            }
        }

        ZYPTableUtil.deleteRows(glblMsg.A, selectedRows);
        ZYPTableUtil.deleteRows(glblMsg.C, selectedRows);
        if (bizMsg.xxPageShowFromNum.getValueInt() > glblMsg.A.getValidCount()) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum, BigDecimal.valueOf(glblMsg.A.getValidCount() - 1));
        }
        NLBL3140CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);

        // 2017/10/24 QC#20772 ADD START
        bizMsg.setMessageInfo(NLBM1365I, new String[] {"Line Cancel", "Submit" });
        // 2017/10/24 QC#20772 ADD END

    }

    /**
     * execute next page transition button
     * @param bizMsg
     * @param sMsg
     */
    private void doProcess_NLBL3140Scrn00_PageNext(NLBL3140CMsg bizMsg, NLBL3140SMsg glblMsg) {
        NLBL3140CommonLogic.updateSMsg(bizMsg, glblMsg);
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NLBL3140CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }

    /**
     * execute previous page transition button
     * @param bizMsg
     * @param sMsg
     */
    private void doProcess_NLBL3140Scrn00_PagePrev(NLBL3140CMsg bizMsg, NLBL3140SMsg glblMsg) {
        NLBL3140CommonLogic.updateSMsg(bizMsg, glblMsg);
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NLBL3140CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }

    /**
     * execute jump to selected screen transition button
     * @param bizMsg
     * @param sMsg
     */
    private void doProcess_NLBL3140Scrn00_PageJump(NLBL3140CMsg bizMsg, NLBL3140SMsg glblMsg) {
        NLBL3140CommonLogic.updateSMsg(bizMsg, glblMsg);
        bizMsg.xxPageShowFromNum.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NLBL3140CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }

    /**
     * doProcess_NLBL3140Scrn00_SelectAll
     * @param cMsg NLBL3140CMsg
     * @param sMsg NLBL3140SMsg
     */
    private void doProcess_NLBL3140Scrn00_SelectAll(NLBL3140CMsg cMsg, NLBL3140SMsg sMsg) {

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxChkBox_A, ZYPConstant.CHKBOX_ON_Y);
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_A, ZYPConstant.CHKBOX_ON_Y);
        }

    }

    /**
     * doProcess_NLBL3140Scrn00_UnSelectAll
     * @param cMsg NLBL3140CMsg
     * @param sMsg NLBL3140SMsg
     */
    private void doProcess_NLBL3140Scrn00_UnSelectAll(NLBL3140CMsg cMsg, NLBL3140SMsg sMsg) {

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            cMsg.A.no(i).xxChkBox_A.clear();
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            sMsg.A.no(i).xxChkBox_A.clear();
        }
    }

    /**
     * execute search process
     * @param bizMsg
     * @param glblMsg
     **/
    private void doProcess_NLBL3140Scrn00_Search(NLBL3140CMsg bizMsg, NLBL3140SMsg glblMsg) {
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        glblMsg.clear();
        ZYPTableUtil.clear(glblMsg.C);

        S21SsmEZDResult ssmResult = NLBL3140Query.getInstance().search(bizMsg, glblMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > glblMsg.A.length() - 1) {
                bizMsg.setMessageInfo(NZZM0001W);
                queryResCnt = glblMsg.A.length();
            }

            int i = 0;
            int bzNum = 0;
            for (; i < glblMsg.A.getValidCount(); i++) {
                if (glblMsg.A.no(i).dsOrdCatgCd_A.getValue().equals("*")) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).dsOrdCatgDescTxt_A, "*");
                }
                if (i < bizMsg.A.length()) {
                    EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i), null);
                }
                ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).mdseWhCondPk_C, glblMsg.A.no(i).mdseWhCondPk_A);
                ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).lineBizTpCd_C, glblMsg.A.no(i).lineBizTpCd_A);
                ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).rtlWhCd_C, glblMsg.A.no(i).rtlWhCd_A);
                ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).rtlSwhCd_C, glblMsg.A.no(i).rtlSwhCd_A);
                ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).dsOrdCatgCd_C, glblMsg.A.no(i).dsOrdCatgCd_A);
                ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).dsOrdCatgDescTxt_C, glblMsg.A.no(i).dsOrdCatgDescTxt_A);
                ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).hardAllocTpCd_C, glblMsg.A.no(i).hardAllocTpCd_A);
                ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).tmFenceDaysAot_C, glblMsg.A.no(i).tmFenceDaysAot_A);
                // START 2023/07/11 G.Quan [QC#61543, ADD]
                ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).coaProdCd_C, glblMsg.A.no(i).coaProdCd_A);
                // END 2023/07/11 G.Quan [QC#61543, ADD]
            }
            if (i > bizMsg.A.length()) {
                bzNum = bizMsg.A.length();
            } else {
                bzNum = i;
            }
            bizMsg.A.setValidCount(bzNum);
            glblMsg.C.setValidCount(bzNum);

            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum.setValue(queryResCnt);
        } else {
            bizMsg.setMessageInfo(NLBM1232I);
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
        }
    }

    /**
     * doProcess_NLBL3140Scrn00_/**
     * doProcess_NLBL3140Scrn00_Search_SetWh
     * @param cMsg NLBL3140CMsg
     * @param sMsg NLBL3140SMsg
     */
    private void doProcess_NLBL3140Scrn00_SetWh(NLBL3140CMsg cMsg, NLBL3140SMsg sMsg) {

        int selectNum = cMsg.xxCellIdx.getValueInt();

        if (ZYPCommonFunc.hasValue(cMsg.A.no(selectNum).rtlWhCd_A)) {

            RTL_WHTMsg rtlWh = new RTL_WHTMsg();
            ZYPEZDItemValueSetter.setValue(rtlWh.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(rtlWh.rtlWhCd, cMsg.A.no(selectNum).rtlWhCd_A.getValue());
            rtlWh = (RTL_WHTMsg) EZDFastTBLAccessor.findByKey(rtlWh);

            if (rtlWh == null) {
                // 2017/08/16 QC#20555 START
//                cMsg.A.no(selectNum).rtlWhCd_A.setErrorInfo(1, NLBM1120E, new String[] {"WH" });
                cMsg.A.no(selectNum).rtlWhNm_A.clear();
                // 2017/08/16 QC#20555 END
                return;
            }
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(selectNum).rtlWhNm_A, rtlWh.rtlWhNm);
        } else {
            cMsg.A.no(selectNum).rtlWhCd_A.setErrorInfo(1, ZZM9000E, new String[] {"WH" });
        }
    }

    /**
     * doProcess_NLBL3140Scrn00_Search_SetSwh
     * @param cMsg NLBL3140CMsg
     * @param sMsg NLBL3140SMsg
     */
    private void doProcess_NLBL3140Scrn00_SetSwh(NLBL3140CMsg cMsg, NLBL3140SMsg sMsg) {

        int selectNum = cMsg.xxCellIdx.getValueInt();

        if (ZYPCommonFunc.hasValue(cMsg.A.no(selectNum).rtlSwhCd_A)) {

            RTL_SWHTMsg rtlSwh = new RTL_SWHTMsg();
            rtlSwh.setSQLID("002");
            rtlSwh.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
            rtlSwh.setConditionValue("rtlSwhCd01", cMsg.A.no(selectNum).rtlSwhCd_A.getValue());
            RTL_SWHTMsgArray rtlSwhList = (RTL_SWHTMsgArray) EZDTBLAccessor.findByCondition(rtlSwh);

            if (rtlSwhList == null || rtlSwhList.getValidCount() == 0) {
                // 2017/08/16 QC#20555 START
//                cMsg.A.no(selectNum).rtlSwhCd_A.setErrorInfo(1, NLBM1120E, new String[] {"SWH" });
                cMsg.A.no(selectNum).rtlSwhNm_A.clear();
                // 2017/08/16 QC#20555 END
                return;
            }
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(selectNum).rtlSwhNm_A, rtlSwhList.no(0).rtlSwhNm);
        } else {
            cMsg.A.no(selectNum).rtlSwhCd_A.setErrorInfo(1, ZZM9000E, new String[] {"SWH" });
        }
    }

    // START 2023/07/10 G.Quan [QC#61543, ADD]
    /**
     * doProcess_NLBL3140Scrn00_/**
     * doProcess_NLBL3140Scrn00_Search_SetPC
     * @param cMsg NLBL3140CMsg
     * @param sMsg NLBL3140SMsg
     */
    private void doProcess_NLBL3140Scrn00_SetPC(NLBL3140CMsg cMsg, NLBL3140SMsg sMsg) {

        int selectNum = cMsg.xxCellIdx.getValueInt();

        if (ZYPCommonFunc.hasValue(cMsg.A.no(selectNum).coaProdCd_A)) {

            COA_PRODTMsg coaProdCd = new COA_PRODTMsg();
            ZYPEZDItemValueSetter.setValue(coaProdCd.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(coaProdCd.coaProdCd, cMsg.A.no(selectNum).coaProdCd_A.getValue());
            coaProdCd = (COA_PRODTMsg) EZDFastTBLAccessor.findByKey(coaProdCd);

            if (coaProdCd == null) {
                cMsg.A.no(selectNum).coaProdCd_A.clear();
                return;
            }
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(selectNum).coaProdNm_A, coaProdCd.coaProdNm);
        } else {
            cMsg.A.no(selectNum).coaProdCd_A.setErrorInfo(1, ZZM9000E, new String[] {"PC" });
        }
    }
    // END 2023/07/10 G.Quan [QC#61543, ADD]

}
