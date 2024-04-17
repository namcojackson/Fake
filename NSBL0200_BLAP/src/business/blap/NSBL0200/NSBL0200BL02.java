/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0200;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static business.blap.NSBL0200.constant.NSBL0200Constant.*;

import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.NSBL0200.common.NSBL0200CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 *
 * Technician Recommend Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/03   Hitachi         Y.Igarashi         Create          N/A
 * 2013/08/30   WDS Team        K.Aratani          Update          QC1457
 * 2015/11/25   Hitachi         T.Harada           Update          CSA
 * 
 *</pre>
 */
public class NSBL0200BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            // START 2015/11/25 [CSA,CHANGE]
            String screenAplID = cMsg.getScreenAplID();
            String gcc = getGlobalCompanyCode();
            String slsDt = ZYPDateUtil.getSalesDate(gcc);
            // END 2015/11/25 [CSA,CHANGE]

            if ("NSBL0200_INIT".equals(screenAplID)) {
                doProcess_NSBL0200Scrn00_init((NSBL0200CMsg) cMsg, (NSBL0200SMsg) sMsg, gcc, slsDt);
            } else if ("NSBL0200Scrn00_Search".equals(screenAplID)) {
                doProcess_NSBL0200Scrn00_Search((NSBL0200CMsg) cMsg, (NSBL0200SMsg) sMsg, gcc, slsDt);
            } else if ("NSBL0200Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSBL0200Scrn00_PageNext((NSBL0200CMsg) cMsg, (NSBL0200SMsg) sMsg);
            } else if ("NSBL0200Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSBL0200Scrn00_PagePrev((NSBL0200CMsg) cMsg, (NSBL0200SMsg) sMsg);
            } else if ("NSBL0200Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSBL0200Scrn00_CMN_Clear((NSBL0200CMsg) cMsg, (NSBL0200SMsg) sMsg);
            } else if ("NSBL0200Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSBL0200Scrn00_TBLColumnSort((NSBL0200CMsg) cMsg, (NSBL0200SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0200Scrn00_init(NSBL0200CMsg cMsg, NSBL0200SMsg sMsg, String gcc, String slsDt) {
        ZYPPulldownValueSetter.insertFirstData(SELECTED_VALUE_PASSED, cMsg.xxScrItem10Txt_CD, SELECTED_VALUE_PASSED, cMsg.xxScrItem10Txt_NM);
        setValue(cMsg.techAvalFlg_SV, ZYPConstant.FLG_ON_Y);
        setValue(cMsg.xxScrItem10Txt_SV, SELECTED_VALUE_PASSED);

        // START 2015/11/25 [CSA,CHANGE]

        // ZYPPulldownValueSetter.insertFirstData(ZYPConstant.FLG_ON_Y,
        // cMsg.techAvalFlg_CD, ZYPConstant.FLG_ON_Y,
        // cMsg.techAvalFlg_NM);

        ZYPCodeDataUtil.createPulldownList("LINE_BIZ_TP", cMsg.lineBizTpNm_CD, cMsg.lineBizTpNm_NM);

        if (hasValue(cMsg.svcMachMstrPk_SC.getValue())) {
            Map<String, Object> machineInfo = NSBL0200Query.getInstance().getMachineInfo(cMsg.svcMachMstrPk_SC.getValue());
            if (machineInfo != null) {
                setValue(cMsg.svcContrBrDescTxt_SC, (String) machineInfo.get("SVC_CONTR_BR_DESC_TXT"));
                setValue(cMsg.lineBizTpDescTxt_SC, (String) machineInfo.get("LINE_BIZ_TP_DESC_TXT"));
                setValue(cMsg.fldSvcBrCd_SC, (String) machineInfo.get("SVC_CONTR_BR_CD"));
                setValue(cMsg.lineBizTpNm_SV, (String) machineInfo.get("LINE_BIZ_TP_CD"));
            }
        }

        if (hasValue(cMsg.mdlNm_SC.getValue())) {
            Map<String, Object> skillInfo = NSBL0200Query.getInstance().getSkillInfo(cMsg.mdlNm_SC.getValue());
            if (skillInfo != null) {
                setValue(cMsg.svcSkillNum_SC, (String) skillInfo.get("SVC_SKILL_NUM"));
                setValue(cMsg.svcSkillNm_SC, (String) skillInfo.get("SVC_SKILL_NM"));
            }
        }
        // NSBL0200CommonLogic.initDateTime(cMsg,
        // getGlobalCompanyCode());
        // NSBL0200CommonLogic.searchInfo(cMsg, sMsg,
        // getGlobalCompanyCode());

        NSBL0200CommonLogic.searchTechInfo(cMsg, sMsg, gcc, slsDt);

        // END 2015/11/25 [CSA,CHANGE]
    }

    private void doProcess_NSBL0200Scrn00_Search(NSBL0200CMsg cMsg, NSBL0200SMsg sMsg, String gcc, String slsDt) {
        // START 2015/11/25 [CSA,CHANGE]
        // NSBL0200CommonLogic.getTimeZone(cMsg,
        // getGlobalCompanyCode());
        // NSBL0200CommonLogic.searchInfo(cMsg, sMsg,
        // getGlobalCompanyCode());
        NSBL0200CommonLogic.searchTechInfo(cMsg, sMsg, gcc, slsDt);
        // END 2015/11/25 [CSA,CHANGE]
    }

    private void doProcess_NSBL0200Scrn00_PageNext(NSBL0200CMsg cMsg, NSBL0200SMsg sMsg) {
        // set values to items of pageNation for next page pageNation
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowToNum.getValueInt());
        cMsg.xxPageShowToNum.clear();

        NSBL0200CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    private void doProcess_NSBL0200Scrn00_PagePrev(NSBL0200CMsg cMsg, NSBL0200SMsg sMsg) {
        // set values to items of pageNation for next page pageNation
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1);
        cMsg.xxPageShowToNum.clear();

        NSBL0200CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    private void doProcess_NSBL0200Scrn00_CMN_Clear(NSBL0200CMsg cMsg, NSBL0200SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        NSBL0200CommonLogic.clearPageNum(cMsg);
        // START 2015/11/25 [CSA,CHANGE]
        // QC1457
        // cMsg.orgLayerNum_TC.clear();
        // cMsg.orgCd_TC.clear();
        // cMsg.orgNm_TC.clear();
        cMsg.fldSvcBrCd_SC.clear();
        cMsg.lineBizTpNm_SV.clear();
        cMsg.techCd_SC.clear();
        cMsg.techNm_SC.clear();
        // setValue(cMsg.techAvalFlg_SV, ZYPConstant.FLG_ON_Y);
        setValue(cMsg.xxScrItem10Txt_SV, SELECTED_VALUE_PASSED);
        // NSBL0200CommonLogic.initDateTime(cMsg,
        // getGlobalCompanyCode());
        // END 2015/11/25 [CSA,CHANGE]
    }

    private void doProcess_NSBL0200Scrn00_TBLColumnSort(NSBL0200CMsg cMsg, NSBL0200SMsg sMsg) {
        NSBL0200CommonLogic.clearPageNum(cMsg);

        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(sortItemNm, sortOrdBy);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

        EZDMsg.copy(sMsg.A, null, cMsg.A, null);
        NSBL0200CommonLogic.setPageNum(cMsg, 1, cMsg.A.getValidCount(), sMsg.A.getValidCount());
    }
}
