/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL3150;

import static business.blap.NLBL3150.constant.NLBL3150Constant.ASTERISK;
import static business.blap.NLBL3150.constant.NLBL3150Constant.CHKBOX_ON_A_NM;
import static business.blap.NLBL3150.constant.NLBL3150Constant.LBL_MDSE;
import static business.blap.NLBL3150.constant.NLBL3150Constant.LBL_SWH;
import static business.blap.NLBL3150.constant.NLBL3150Constant.LBL_WH;
import static business.blap.NLBL3150.constant.NLBL3150Constant.NLBM1365I;
import static business.blap.NLBL3150.constant.NLBL3150Constant.NLBM0081E;
import static business.blap.NLBL3150.constant.NLBL3150Constant.NLBM1232I;
import static business.blap.NLBL3150.constant.NLBL3150Constant.NLZM2278E;
import static business.blap.NLBL3150.constant.NLBL3150Constant.NZZM0001W;
import static business.blap.NLBL3150.constant.NLBL3150Constant.ZZM9000E;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLBL3150.common.NLBL3150CommonLogic;
import business.db.MDSETMsg;
import business.db.RTL_SWHTMsg;
import business.db.RTL_SWHTMsgArray;
import business.db.RTL_WHTMsg;

import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NLBL3150BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/07/21   Fujitsu         K.Ishizuka      Create          N/A
 * 2017/08/17   Fujitsu         M.Yamada        Update          QC#20556
 * 2017/08/25   Fujitsu         M.Ohno          Update          QC#20772
 * 2017/09/08   CITS            T.Hakodate      Update          QC#20772
 * 2017/10/24   CITS            T.Kikuhara      Update          QC#20772
 *</pre>
 */
public class NLBL3150BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NLBL3150CMsg bizMsg = (NLBL3150CMsg) cMsg;
            NLBL3150SMsg glblMsg = (NLBL3150SMsg) sMsg;

            if ("NLBL3150_INIT".equals(screenAplID)) {
                doProcess_NLBL3150Scrn00_INIT(bizMsg, glblMsg);
            } else if ("NLBL3150Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NLBL3150Scrn00_INIT(bizMsg, glblMsg);
            } else if ("NLBL3150Scrn00_AddNewRow".equals(screenAplID)) {
                doProcess_NLBL3150Scrn00_AddNewRow(bizMsg, glblMsg);
            } else if ("NLBL3150Scrn00_LineCancel".equals(screenAplID)) {
                doProcess_NLBL3150Scrn00_LineCancel(bizMsg, glblMsg);
            } else if ("NLBL3150Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NLBL3150Scrn00_PageNext(bizMsg, glblMsg);
            } else if ("NLBL3150Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NLBL3150Scrn00_PagePrev(bizMsg, glblMsg);
            } else if ("NLBL3150Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NLBL3150Scrn00_PageJump(bizMsg, glblMsg);
            } else if ("NLBL3150Scrn00_SelectAll".equals(screenAplID)) {
                doProcess_NLBL3150Scrn00_SelectAll(bizMsg, glblMsg);
            } else if ("NLBL3150Scrn00_UnSelectAll".equals(screenAplID)) {
                doProcess_NLBL3150Scrn00_UnSelectAll(bizMsg, glblMsg);
            } else if ("NLBL3150Scrn00_SetWh".equals(screenAplID)) {
                doProcess_NLBL3150Scrn00_SetWh(bizMsg, glblMsg);
            } else if ("NLBL3150Scrn00_SetSwh".equals(screenAplID)) {
                doProcess_NLBL3150Scrn00_SetSwh(bizMsg, glblMsg);
            } else if ("NLBL3150Scrn00_Search".equals(screenAplID)) {
                doProcess_NLBL3150Scrn00_Search(bizMsg, glblMsg);
            } else if ("NLBL3150Scrn00_SetItemDesc".equals(screenAplID)) {
                doProcess_NLBL3150Scrn00_SetItemDesc(bizMsg, glblMsg);
            } else if ("NLBL3150Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLBL3150Scrn00_Search(bizMsg, glblMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * execute initial process
     * @param bizMsg
     * @param glblMsg
     */
    private void doProcess_NLBL3150Scrn00_INIT(NLBL3150CMsg bizMsg, NLBL3150SMsg glblMsg) {
        bizMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        glblMsg.clear();
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(glblMsg.C);

        NLBL3150CommonLogic.setAuthority(bizMsg, getUserProfileService());
    }

    /**
     * execute Add New Row button
     * @param bizMsg
     * @param sMsg
     */
    private void doProcess_NLBL3150Scrn00_AddNewRow(NLBL3150CMsg bizMsg, NLBL3150SMsg glblMsg) {

        NLBL3150CommonLogic.updateSMsg(bizMsg, glblMsg);
        if (glblMsg.A.getValidCount() == glblMsg.A.length()) {
            bizMsg.setMessageInfo(NLBM0081E, new String[] {Integer.toString(glblMsg.A.length()) });
            return;
        }
        bizMsg.xxPageShowFromNum.setValue(glblMsg.A.getValidCount());
        glblMsg.A.setValidCount(glblMsg.A.getValidCount() + 1);
        NLBL3150CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }

    /**
     * execute Delete button to delete row from table
     * @param bizMsg
     * @param sMsg
     */
    private void doProcess_NLBL3150Scrn00_LineCancel(NLBL3150CMsg bizMsg, NLBL3150SMsg glblMsg) {
        NLBL3150CommonLogic.updateSMsg(bizMsg, glblMsg);
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
        NLBL3150CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);

        // 2017/10/24 QC#20772 ADD START
        bizMsg.setMessageInfo(NLBM1365I, new String[] {"Line Cancel", "Submit" });
        // 2017/10/24 QC#20772 ADD END

    }

    /**
     * execute next page transition button
     * @param bizMsg
     * @param sMsg
     */
    private void doProcess_NLBL3150Scrn00_PageNext(NLBL3150CMsg bizMsg, NLBL3150SMsg glblMsg) {
        NLBL3150CommonLogic.updateSMsg(bizMsg, glblMsg);
        // copy data from SMsg onto CMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NLBL3150CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }

    /**
     * execute previous page transition button
     * @param bizMsg
     * @param sMsg
     */
    private void doProcess_NLBL3150Scrn00_PagePrev(NLBL3150CMsg bizMsg, NLBL3150SMsg glblMsg) {
        NLBL3150CommonLogic.updateSMsg(bizMsg, glblMsg);
        // copy data from SMsg onto CMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NLBL3150CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }

    /**
     * execute jump to selected screen transition button
     * @param bizMsg
     * @param sMsg
     */
    private void doProcess_NLBL3150Scrn00_PageJump(NLBL3150CMsg bizMsg, NLBL3150SMsg glblMsg) {
        NLBL3150CommonLogic.updateSMsg(bizMsg, glblMsg);
        // copy data from SMsg onto CMsg
        bizMsg.xxPageShowFromNum.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NLBL3150CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }

    /**
     * doProcess_NLBL3150Scrn00_SelectAll
     * @param cMsg NLBL3150CMsg
     * @param sMsg NLBL3150SMsg
     */
    private void doProcess_NLBL3150Scrn00_SelectAll(NLBL3150CMsg cMsg, NLBL3150SMsg sMsg) {

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxChkBox_A, ZYPConstant.CHKBOX_ON_Y);
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_A, ZYPConstant.CHKBOX_ON_Y);
        }

    }

    /**
     * doProcess_NLBL3150Scrn00_UnSelectAll
     * @param cMsg NLBL3150CMsg
     * @param sMsg NLBL3150SMsg
     */
    private void doProcess_NLBL3150Scrn00_UnSelectAll(NLBL3150CMsg cMsg, NLBL3150SMsg sMsg) {

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            cMsg.A.no(i).xxChkBox_A.clear();
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            sMsg.A.no(i).xxChkBox_A.clear();
        }
    }

    /**
     * doProcess_NLBL3150Scrn00_/**
     * doProcess_NLBL3150Scrn00_Search_SetWh
     * @param cMsg NLBL3150CMsg
     * @param sMsg NLBL3150SMsg
     */
    private void doProcess_NLBL3150Scrn00_SetWh(NLBL3150CMsg cMsg, NLBL3150SMsg sMsg) {

        int selectNum = cMsg.xxCellIdx.getValueInt();

        if (ZYPCommonFunc.hasValue(cMsg.A.no(selectNum).rtlWhCd_A)) {

            RTL_WHTMsg rtlWh = new RTL_WHTMsg();
            ZYPEZDItemValueSetter.setValue(rtlWh.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(rtlWh.rtlWhCd, cMsg.A.no(selectNum).rtlWhCd_A.getValue());
            rtlWh = (RTL_WHTMsg) S21FastTBLAccessor.findByKey(rtlWh);

            if (rtlWh == null) {
                cMsg.A.no(selectNum).rtlWhCd_A.setErrorInfo(1, NLZM2278E, new String[] {LBL_WH });
                return;
            }
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(selectNum).rtlWhNm_A, rtlWh.rtlWhNm);
        } else {
            cMsg.A.no(selectNum).rtlWhCd_A.setErrorInfo(1, ZZM9000E, new String[] {LBL_WH });
        }
    }

    /**
     * doProcess_NLBL3150Scrn00_Search_SetSwh
     * @param cMsg NLBL3150CMsg
     * @param sMsg NLBL3150SMsg
     */
    private void doProcess_NLBL3150Scrn00_SetSwh(NLBL3150CMsg cMsg, NLBL3150SMsg sMsg) {

        int selectNum = cMsg.xxCellIdx.getValueInt();

        // 2017/08/17 QC#20556 MOD START
        if (!ZYPCommonFunc.hasValue(cMsg.A.no(selectNum).rtlSwhCd_A) //
                || ASTERISK.equals(cMsg.A.no(selectNum).rtlSwhCd_A.getValue())) {
            cMsg.A.no(selectNum).rtlSwhNm_A.clear();
            return;
        }
        // if
        // (ZYPCommonFunc.hasValue(cMsg.A.no(selectNum).rtlSwhCd_A)) {
        RTL_SWHTMsg rtlSwh = new RTL_SWHTMsg();
        rtlSwh.setSQLID("002");
        rtlSwh.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        rtlSwh.setConditionValue("rtlSwhCd01", cMsg.A.no(selectNum).rtlSwhCd_A.getValue());
        RTL_SWHTMsgArray rtlSwhList = (RTL_SWHTMsgArray) EZDTBLAccessor.findByCondition(rtlSwh);

        if (rtlSwhList == null || rtlSwhList.getValidCount() == 0) {
            cMsg.A.no(selectNum).rtlSwhCd_A.setErrorInfo(1, NLZM2278E, new String[] {LBL_SWH });
            cMsg.A.no(selectNum).rtlSwhNm_A.clear();
            return;
        }
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(selectNum).rtlSwhNm_A, rtlSwhList.no(0).rtlSwhNm);
        // } else {
        // cMsg.A.no(selectNum).rtlSwhCd_A.setErrorInfo(1, ZZM9000E,
        // new String[] {"SWH" });
        // }
        // 2017/08/17 QC#20556 MOD END
    }

    /**
     * execute search process
     * @param bizMsg
     * @param glblMsg
     **/
    private void doProcess_NLBL3150Scrn00_Search(NLBL3150CMsg bizMsg, NLBL3150SMsg glblMsg) {
        // 2017/08/25 QC#20772 ADD BEGIN
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        glblMsg.clear();
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(glblMsg.C);
        // 2017/08/25 QC#20772 ADD END
        
        S21SsmEZDResult ssmResult = NLBL3150Query.getInstance().search(bizMsg, glblMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > glblMsg.A.length() - 1) {
                bizMsg.setMessageInfo(NZZM0001W);
                queryResCnt = glblMsg.A.length();
            }

            int i = 0;
            int bzNum = 0;
            for (; i < glblMsg.A.getValidCount(); i++) {
                if (i < bizMsg.A.length()) {
                    EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i), null);
                }
                ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).mdseWhCondPk_C, glblMsg.A.no(i).mdseWhCondPk_A);
                ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).mdseCd_C, glblMsg.A.no(i).mdseCd_A);
                ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).rtlWhCd_C, glblMsg.A.no(i).rtlWhCd_A);
                ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).rtlSwhCd_C, glblMsg.A.no(i).rtlSwhCd_A);
            }
            if (i > bizMsg.A.length()) {
                bzNum = bizMsg.A.length();
            } else {
                bzNum = i;
            }
            bizMsg.A.setValidCount(bzNum);
            glblMsg.C.setValidCount(i);

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
     * execute to set item description
     * @param bizMsg
     * @param sMsg
     * @param glblMsg
     */
    private void doProcess_NLBL3150Scrn00_SetItemDesc(NLBL3150CMsg bizMsg, NLBL3150SMsg glblMsg) {

        int selectNum = bizMsg.xxCellIdx.getValueInt();
        String mdseCd = bizMsg.A.no(selectNum).mdseCd_A.getValue();

        MDSETMsg mdse = (MDSETMsg) NWXMdseTMsgThreadLocalCache.getInstance().get(getGlobalCompanyCode(), mdseCd);

        if (mdse == null) {
            // 2017/09/08 CITS T.Hakodate Mod QC#20772 START
            bizMsg.A.no(selectNum).mdseCd_A.setErrorInfo(1, NLZM2278E, new String[] {LBL_MDSE });
            // 2017/09/08 CITS T.Hakodate Mod QC#20772 END
            return;
        }
        
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(selectNum).mdseDescShortTxt_A, mdse.mdseDescShortTxt);

    }

}
