/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1670;

import static business.blap.NWAL1670.constant.NWAL1670Constant.NWAM0186E;
import static business.blap.NWAL1670.constant.NWAL1670Constant.NWAM0495E;
import static business.blap.NWAL1670.constant.NWAL1670Constant.NWAM0827E;
import static business.blap.NWAL1670.constant.NWAL1670Constant.NWAM0828E;
import static business.blap.NWAL1670.constant.NWAL1670Constant.NWAM0829E;
import static business.blap.NWAL1670.constant.NWAL1670Constant.NWAM0830E;
import static business.blap.NWAL1670.constant.NWAL1670Constant.ZZZM9004E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1670.common.NWAL1670CommonLogic;
import business.db.ORD_TEAM_MSTR_HDRTMsg;
import business.db.S21_PSN_VTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Order Team Set up
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/07   Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public class NWAL1670BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NWAL1670CMsg cMsg = (NWAL1670CMsg) arg0;
        NWAL1670SMsg sMsg = (NWAL1670SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NWAL1670_INIT".equals(screenAplID)) {
                doProcess_NWAL1670_INIT(cMsg, sMsg);
            } else if ("NWAL1670Scrn00_Add_Attribute".equals(screenAplID)) {
                doProcess_NWAL1670Scrn00_Add_Attribute(cMsg, sMsg);
            } else if ("NWAL1670Scrn00_Add_Member".equals(screenAplID)) {
                doProcess_NWAL1670Scrn00_Add_Member(cMsg, sMsg);
            } else if ("NWAL1670Scrn00_Add_Team".equals(screenAplID)) {
                doProcess_NWAL1670Scrn00_Add_Team(cMsg, sMsg);
            } else if ("NWAL1670Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NWAL1670Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NWAL1670Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWAL1670Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NWAL1670Scrn00_Copy_Team".equals(screenAplID)) {
                doProcess_NWAL1670Scrn00_Copy_Team(cMsg, sMsg);
            } else if ("NWAL1670Scrn00_Del_Attribute".equals(screenAplID)) {
                doProcess_NWAL1670Scrn00_Del_Attribute(cMsg, sMsg);
            } else if ("NWAL1670Scrn00_Del_Member".equals(screenAplID)) {
                doProcess_NWAL1670Scrn00_Del_Member(cMsg, sMsg);
            } else if ("NWAL1670Scrn00_Del_Team".equals(screenAplID)) {
                doProcess_NWAL1670Scrn00_Del_Team(cMsg, sMsg);
            } else if ("NWAL1670Scrn00_OnChangeOrdTeamAttrbTpCd".equals(screenAplID)) {
                doProcess_NWAL1670Scrn00_OnChangeOrdTeamAttrbTpCd(cMsg, sMsg);
            } else if ("NWAL1670Scrn00_OnClick_Team".equals(screenAplID)) {
                doProcess_NWAL1670Scrn00_OnClick_Team(cMsg, sMsg);
            } else if ("NWAL1670Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NWAL1670Scrn00_PageJump(cMsg, sMsg);
            } else if ("NWAL1670Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NWAL1670Scrn00_PageNext(cMsg, sMsg);
            } else if ("NWAL1670Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NWAL1670Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NWAL1670Scrn00_Search".equals(screenAplID)) {
                doProcess_NWAL1670Scrn00_Search(cMsg, sMsg);
            } else if ("NWAL1670_NMAL2570".equals(screenAplID)) {
                doProcess_NWAL1670_NMAL2570(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NWAL1670_INIT(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {

        NWAL1670CommonLogic.clearMsg(cMsg, sMsg);

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));

        NWAL1670CommonLogic.createPullDown(cMsg);
        NWAL1670CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NWAL1670Scrn00_Add_Attribute(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {
        if (sMsg.C.getValidCount() >= sMsg.C.length()) {
            cMsg.setMessageInfo(NWAM0495E, new String[] {Integer.toString(sMsg.C.length()) });
            return;
        }

        NWAL1670CommonLogic.copyToCSmsg(cMsg, sMsg);
        addAttribute(cMsg, sMsg);
        NWAL1670CommonLogic.doFilterForAttribute(cMsg, sMsg);
    }

    private void doProcess_NWAL1670Scrn00_Add_Member(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {

        if (cMsg.B.getValidCount() >= cMsg.B.length()) {
            cMsg.setMessageInfo(NWAM0495E, new String[] {Integer.toString(cMsg.B.length()) });
            return;
        }
        addMember(cMsg);
    }

    private void doProcess_NWAL1670Scrn00_Add_Team(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {

        if (sMsg.A.getValidCount() >= sMsg.A.length()) {
            cMsg.setMessageInfo(NWAM0495E, new String[] {Integer.toString(sMsg.A.length()) });
            return;
        }
        if (!(cMsg.xxPageShowToNum.getValueInt() == cMsg.xxPageShowOfNum.getValueInt() && cMsg.A.getValidCount() < cMsg.A.length())) {
            if (!NWAL1670CommonLogic.cheackTeamList(cMsg, sMsg)) {
                return;
            }
        }

        copyToASMsg(cMsg, sMsg);
        addTeam(cMsg, sMsg);
        NWAL1670CommonLogic.clearRadioButton(cMsg, sMsg);

        pagenationForAddTeam(cMsg, sMsg);
    }

    private void doProcess_NWAL1670Scrn00_CMN_Clear(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {
        doProcess_NWAL1670_INIT(cMsg, sMsg);
    }

    private void doProcess_NWAL1670Scrn00_CMN_Submit(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {

        NWAL1670CommonLogic.getSearchData(cMsg, sMsg, true);
        NWAL1670CommonLogic.pagenation(cMsg, sMsg, 0);
    }

    private void doProcess_NWAL1670Scrn00_Copy_Team(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {

        if (!hasValue(cMsg.xxRadioBtn)) {
            cMsg.setMessageInfo(NWAM0827E);
            return;
        }

        if (sMsg.A.getValidCount() >= sMsg.A.length()) {
            cMsg.setMessageInfo(NWAM0495E, new String[] {Integer.toString(sMsg.A.length()) });
            return;
        }

        int index = cMsg.xxRadioBtn.getValueInt();
        if (!hasValue(cMsg.A.no(index).ordTeamMstrHdrPk_A)) {
            cMsg.setMessageInfo(NWAM0828E);
            return;
        }
        if (!(cMsg.xxPageShowToNum.getValueInt() == cMsg.xxPageShowOfNum.getValueInt() && cMsg.A.getValidCount() < cMsg.A.length())) {
            if (!NWAL1670CommonLogic.cheackTeamList(cMsg, sMsg)) {
                return;
            }
        }
        copyToASMsg(cMsg, sMsg);

        BigDecimal ordTeamMstrHdrPk = cMsg.A.no(index).ordTeamMstrHdrPk_A.getValue();
        cpyTeam(cMsg, sMsg, ordTeamMstrHdrPk);

        pagenationForAddTeam(cMsg, sMsg);
    }

    private void doProcess_NWAL1670Scrn00_Del_Attribute(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {

        NWAL1670CommonLogic.copyToCSmsg(cMsg, sMsg);
        if (!delAttribute(cMsg, sMsg)) {
            return;
        }
        NWAL1670CommonLogic.doFilterForAttribute(cMsg, sMsg);
    }

    private void doProcess_NWAL1670Scrn00_Del_Member(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {
        delMember(cMsg, sMsg);
    }

    private void doProcess_NWAL1670Scrn00_Del_Team(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {
        copyToASMsg(cMsg, sMsg);

        if (!NWAL1670CommonLogic.checkRadioButton(cMsg)) {
            cMsg.setMessageInfo(NWAM0827E);
            return;
        }

        int index = cMsg.xxRadioBtn.getValueInt();
        if (hasValue(cMsg.A.no(index).ordTeamMstrHdrPk_A)) {
            cMsg.A.no(index).effThruDt_A.setErrorInfo(1, NWAM0830E);
            return;
        }

        delTeam(cMsg, sMsg, cMsg.A.no(index).xxRowNum_A.getValueInt());

        int pagenationFrom = NWAL1670CommonLogic.convertPageNumToFirstRowIndex(cMsg);
        NWAL1670CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
        NWAL1670CommonLogic.clearRadioButton(cMsg, sMsg);
    }

    private void doProcess_NWAL1670Scrn00_OnChangeOrdTeamAttrbTpCd(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {
        NWAL1670CommonLogic.copyToCSmsg(cMsg, sMsg);
        NWAL1670CommonLogic.doFilterForAttribute(cMsg, sMsg);
    }

    private void doProcess_NWAL1670Scrn00_OnClick_Team(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {
        if (hasValue(sMsg.xxRadioBtn) && cMsg.xxRadioBtn.getValueInt() == sMsg.xxRadioBtn.getValueInt()) {
            return;
        }
        NWAL1670CommonLogic.getDetailData(cMsg, sMsg);
        NWAL1670CommonLogic.doFilterForAttribute(cMsg, sMsg);
        setValue(sMsg.xxRadioBtn, cMsg.xxRadioBtn);
    }

    private void doProcess_NWAL1670Scrn00_PageJump(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {

        if (!NWAL1670CommonLogic.cheackTeamList(cMsg, sMsg)) {
            return;
        }
        copyToASMsg(cMsg, sMsg);

        int pageFromNum = NWAL1670CommonLogic.convertPageNumToFirstRowIndex(cMsg);
        NWAL1670CommonLogic.pagenation(cMsg, sMsg, pageFromNum);
        NWAL1670CommonLogic.clearRadioButton(cMsg, sMsg);

    }

    private void doProcess_NWAL1670Scrn00_PageNext(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {

        if (!NWAL1670CommonLogic.cheackTeamList(cMsg, sMsg)) {
            return;
        }
        copyToASMsg(cMsg, sMsg);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NWAL1670CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
        NWAL1670CommonLogic.clearRadioButton(cMsg, sMsg);

    }

    private void doProcess_NWAL1670Scrn00_PagePrev(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {

        if (!NWAL1670CommonLogic.cheackTeamList(cMsg, sMsg)) {
            return;
        }
        copyToASMsg(cMsg, sMsg);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NWAL1670CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
        NWAL1670CommonLogic.clearRadioButton(cMsg, sMsg);

    }

    private void doProcess_NWAL1670Scrn00_Search(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {

        NWAL1670CommonLogic.getSearchData(cMsg, sMsg, false);
        NWAL1670CommonLogic.pagenation(cMsg, sMsg, 0);
    }

    private void doProcess_NWAL1670_NMAL2570(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {
        int index = cMsg.xxNum_PO.getValueInt();
        S21_PSN_VTMsg tMsg = NWAL1670CommonLogic.getPsnV(cMsg.glblCmpyCd.getValue(), cMsg.B.no(index).ordTeamAttrbValTxt_B.getValue());

        if (tMsg == null) {
            cMsg.B.no(index).fullPsnNm_B.clear();
            return;
        }
        setValue(cMsg.B.no(index).fullPsnNm_B, tMsg.fullPsnNm);
    }

    private void copyToASMsg(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {
        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NWAL1670CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);
    }

    private void pagenationForAddTeam(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {

        int pageFromNum = 0;
        if (cMsg.xxPageShowCurNum.getValueInt() > 1) {
            pageFromNum = NWAL1670CommonLogic.convertPageNumToFirstRowIndex(cMsg);
        }

        NWAL1670CommonLogic.pagenation(cMsg, sMsg, pageFromNum);
        setValue(cMsg.xxRadioBtn, new BigDecimal(cMsg.A.getValidCount() - 1));
        setValue(sMsg.xxRadioBtn, cMsg.xxRadioBtn);
    }

    private void addTeam(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {

        int index = sMsg.A.getValidCount();
        int rowNum = 0;

        if (index > 0) {
            rowNum = sMsg.A.no(index - 1).xxRowNum_A.getValueInt();
        }

        setValue(sMsg.A.no(index).effFromDt_A, cMsg.slsDt);
        setValue(sMsg.A.no(index).xxRowNum_A, new BigDecimal(rowNum + 1));

        sMsg.A.setValidCount(index + 1);

        setValue(cMsg.xxPageShowTotNum, getLastPageNum(cMsg, sMsg));
        setValue(cMsg.xxPageShowCurNum, cMsg.xxPageShowTotNum);
    }

    private void addMember(NWAL1670CMsg cMsg) {

        int index = cMsg.B.getValidCount();
        int rowNum = 0;

        if (index > 0) {
            rowNum = cMsg.B.no(index - 1).xxRowNum_B.getValueInt();
        }

        setValue(cMsg.B.no(index).xxRowNum_B, new BigDecimal(rowNum + 1));
        cMsg.B.setValidCount(index + 1);
    }

    private void addAttribute(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {

        int index = sMsg.C.getValidCount();
        int rowNum = 0;

        if (index > 0) {
            rowNum = sMsg.C.no(index - 1).xxRowNum_C.getValueInt();
        }

        setValue(sMsg.C.no(index).xxRowNum_C, new BigDecimal(rowNum + 1));
        setValue(sMsg.C.no(index).ordTeamAttrbTpCd_C, cMsg.ordTeamAttrbTpCd);
        sMsg.C.setValidCount(index + 1);
    }

    private BigDecimal getLastPageNum(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {

        if (sMsg.A.getValidCount() <= cMsg.A.length()) {
            return BigDecimal.ONE;
        }
        return BigDecimal.valueOf(sMsg.A.getValidCount()).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP);
    }

    private void delTeam(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg, int rowNum) {

        List<Integer> selectedRows = new ArrayList<Integer>(1);

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (sMsg.A.no(i).xxRowNum_A.getValueInt() == rowNum) {
                selectedRows.add(i);
                break;
            }
        }

        ZYPTableUtil.deleteRows(sMsg.A, selectedRows);
        setValue(cMsg.xxPageShowTotNum, getLastPageNum(cMsg, sMsg));

        if (cMsg.xxPageShowFromNum.getValueInt() < sMsg.A.getValidCount()) {
            setValue(cMsg.xxPageShowTotNum, getLastPageNum(cMsg, sMsg));
            setValue(cMsg.xxPageShowCurNum, cMsg.xxPageShowTotNum);
        }
    }

    private void delMember(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(cMsg.B, "xxChkBox_B", ZYPConstant.CHKBOX_ON_Y);

        if (!isDelete(cMsg, sMsg, selectedRows)) {
            return;
        }

        int cnt = sMsg.E.getValidCount();

        for (int index : selectedRows) {
            NWAL1670_BCMsg bcMsg = cMsg.B.no(index);
            if (hasValue(bcMsg.ordTeamMstrDtlPk_B)) {
                EZDMsg.copy(cMsg.B.get(index), "B", sMsg.E.get(cnt++), "E");
            }
        }
        sMsg.E.setValidCount(cnt);

        ZYPTableUtil.deleteRows(cMsg.B, selectedRows);
    }

    private boolean delAttribute(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.C, "xxChkBox_C", ZYPConstant.CHKBOX_ON_Y);

        if (!isDelete(cMsg, sMsg, selectedRows)) {
            return false;
        }

        int cnt = sMsg.E.getValidCount();

        for (int index : selectedRows) {
            NWAL1670_CSMsg csMsg = sMsg.C.no(index);
            if (hasValue(csMsg.ordTeamMstrDtlPk_C)) {
                EZDMsg.copy(sMsg.C.get(index), "C", sMsg.E.get(cnt++), "E");
            }
        }
        sMsg.E.setValidCount(cnt);

        ZYPTableUtil.deleteRows(sMsg.C, selectedRows);
        return true;
    }

    private boolean isDelete(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg, List<Integer> selectedRows) {
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NWAM0186E);
            return false;
        }
        if (cMsg.B.getValidCount() + sMsg.C.getValidCount() <= 1) {
            cMsg.setMessageInfo(NWAM0829E);
            return false;
        }
        return true;
    }

    private void cpyTeam(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg, BigDecimal ordTeamMstrHdrPk) {

        ORD_TEAM_MSTR_HDRTMsg tMsg = NWAL1670CommonLogic.getOrdTeamMstrHdr(cMsg.glblCmpyCd.getValue(), ordTeamMstrHdrPk, false);

        if (tMsg == null) {
            cMsg.setMessageInfo(ZZZM9004E);
            return;
        }

        addTeam(cMsg, sMsg);

        int lastIdx = sMsg.A.getValidCount() - 1;
        setValue(sMsg.A.no(lastIdx).ordTeamMstrNm_A, tMsg.ordTeamMstrNm);
        setValue(sMsg.A.no(lastIdx).ordZnCd_A, tMsg.ordZnCd);
        setValue(sMsg.A.no(lastIdx).effFromDt_A, tMsg.effFromDt);
        setValue(sMsg.A.no(lastIdx).effThruDt_A, tMsg.effThruDt);

        NWAL1670CommonLogic.getDetailData(cMsg, sMsg);

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            cMsg.B.no(i).ordTeamMstrDtlPk_B.clear();
            cMsg.B.no(i).ezUpTime_B.clear();
            cMsg.B.no(i).ezUpTimeZone_B.clear();
        }

        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            sMsg.C.no(i).ordTeamMstrDtlPk_C.clear();
            sMsg.C.no(i).ezUpTime_C.clear();
            sMsg.C.no(i).ezUpTimeZone_C.clear();
        }

        cMsg.ordTeamAttrbTpCd.clear();
        NWAL1670CommonLogic.doFilterForAttribute(cMsg, sMsg);
    }
}
