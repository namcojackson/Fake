/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0280;

import static business.blap.NSBL0280.constant.NSBL0280Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSBL0280.common.NSBL0280CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Resource Skill Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/17   Hitachi         J.Kim           Create          N/A
 * 2016/06/08   Hitachi         J.Kim           Update          QC#9650
 *</pre>
 */
public class NSBL0280BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSBL0280CMsg cMsg = (NSBL0280CMsg) arg0;
        NSBL0280SMsg sMsg = (NSBL0280SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0280_INIT".equals(screenAplID)) {
                doProcess_NSBL0280_INIT(cMsg, sMsg);
            } else if ("NSBL0280Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSBL0280Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NSBL0280Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSBL0280Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NSBL0280Scrn00_Delete_Row".equals(screenAplID)) {
                doProcess_NSBL0280Scrn00_Delete_Row(cMsg, sMsg);
            } else if ("NSBL0280Scrn00_Insert_Row".equals(screenAplID)) {
                doProcess_NSBL0280Scrn00_Insert_Row(cMsg, sMsg);
            } else if ("NSBL0280Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSBL0280Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSBL0280Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSBL0280Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSBL0280Scrn00_Search_Resource".equals(screenAplID)) {
                doProcess_NSBL0280Scrn00_Search_Resource(cMsg, sMsg);
            } else if ("NSBL0280Scrn00_Search_Skill".equals(screenAplID)) {
                doProcess_NSBL0280Scrn00_Search_Skill(cMsg, sMsg);
            } else if ("NSBL0280Scrn00_Switch_View".equals(screenAplID)) {
                doProcess_NSBL0280Scrn00_Switch_View(cMsg, sMsg);
            } else if ("NSBL0280Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSBL0280Scrn00_TBLColumnSort(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0280_INIT(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg) {

        doProcessInit(cMsg, sMsg);
    }

    private void doProcess_NSBL0280Scrn00_CMN_Clear(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg) {

        doProcessInit(cMsg, sMsg);
    }

    private void doProcess_NSBL0280Scrn00_CMN_Submit(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg) {

        if (SKILL_TYPE.equals(cMsg.xxScrDply.getValue())) {
            searchSkill(cMsg, sMsg);
        } else {
            searchResource(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0280Scrn00_Delete_Row(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg) {

        int cMsgLength = 0;
        int sMsgValidCount = 0;
        int pageFromNum = 0;
        int xxPageShowFromNum = 0;
        if (SKILL_TYPE.equals(cMsg.xxScrDply.getValue())) {
            cMsgLength = cMsg.B.length();
            sMsgValidCount = sMsg.B.getValidCount();
            pageFromNum = cMsg.xxPageShowFromNum_B.getValueInt() - 1;
            xxPageShowFromNum = cMsg.xxPageShowFromNum_B.getValueInt();
        } else {
            cMsgLength = cMsg.A.length();
            sMsgValidCount = sMsg.A.getValidCount();
            pageFromNum = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
            xxPageShowFromNum = cMsg.xxPageShowFromNum_A.getValueInt();
        }

        NSBL0280CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        if (!NSBL0280CommonLogic.deleteRow(cMsg, sMsg)) {
            return;
        }

        if (SKILL_TYPE.equals(cMsg.xxScrDply.getValue())) {
            sMsgValidCount = sMsg.B.getValidCount();
            pageFromNum = cMsg.xxPageShowFromNum_B.getValueInt() - 1;
            xxPageShowFromNum = cMsg.xxPageShowFromNum_B.getValueInt();
        } else {
            sMsgValidCount = sMsg.A.getValidCount();
            pageFromNum = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
            xxPageShowFromNum = cMsg.xxPageShowFromNum_A.getValueInt();
        }
        int beforePageNum = NSBL0280CommonLogic.getPageNum(cMsgLength, xxPageShowFromNum);
        int afterMaxPageNum = NSBL0280CommonLogic.getPageNum(cMsgLength, sMsgValidCount);
        if (afterMaxPageNum < beforePageNum) {
            pageFromNum = cMsgLength * (afterMaxPageNum - 1);
        }

        NSBL0280CommonLogic.pagenation(cMsg, sMsg, pageFromNum);
        if (SKILL_TYPE.equals(cMsg.xxScrDply.getValue())) {
            cMsg.xxPageShowOfNum_B.setValue(sMsgValidCount);

        } else {
            cMsg.xxPageShowOfNum_A.setValue(sMsgValidCount);
        }
    }

    private void doProcess_NSBL0280Scrn00_Insert_Row(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg) {

        if (SKILL_TYPE.equals(cMsg.xxScrDply.getValue())) {
            int pageFromNum = cMsg.xxPageShowFromNum_B.getValueInt() - 1;
            NSBL0280CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

            int sMsgCount = sMsg.B.getValidCount();
            setValue(sMsg.B.no(sMsgCount).effFromDt_B, ZYPDateUtil.getSalesDate());
            sMsg.B.setValidCount(sMsgCount + 1);

            ZYPTableUtil.clear(cMsg.B);
            int rowIndex = NSBL0280CommonLogic.convertPageNumToFirstRowIndex(cMsg.B.length(), sMsgCount);
            NSBL0280CommonLogic.pagenation(cMsg, sMsg, rowIndex);
            cMsg.xxPageShowOfNum_B.setValue(sMsg.B.getValidCount());
        } else {
            int pageFromNum = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
            NSBL0280CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

            int sMsgCount = sMsg.A.getValidCount();
            setValue(sMsg.A.no(sMsgCount).effFromDt_A, ZYPDateUtil.getSalesDate());
            sMsg.A.setValidCount(sMsgCount + 1);

            ZYPTableUtil.clear(cMsg.A);
            int rowIndex = NSBL0280CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), sMsgCount);
            NSBL0280CommonLogic.pagenation(cMsg, sMsg, rowIndex);
            cMsg.xxPageShowOfNum_A.setValue(sMsg.A.getValidCount());
        }
    }

    private void doProcess_NSBL0280Scrn00_PageNext(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg) {

        if (RESOURCE_TYPE.equals(cMsg.xxScrDply.getValue())) {
            if (!NSBL0280CommonLogic.checkEffFromDtResourceType(cMsg)) {
                return;
            }
        } else {
            if (!NSBL0280CommonLogic.checkEffFromDtSkillType(cMsg)) {
                return;
            }
        }

        NSBL0280CommonLogic.setUpdateFlg(cMsg, sMsg);

        int pageFromNum = 0;
        int pagenationFrom = 0;
        if (SKILL_TYPE.equals(cMsg.xxScrDply.getValue())) {
            pageFromNum = cMsg.xxPageShowFromNum_B.getValueInt() - 1;
        } else {
            pageFromNum = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
        }
        NSBL0280CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        if (SKILL_TYPE.equals(cMsg.xxScrDply.getValue())) {
            pagenationFrom = cMsg.xxPageShowFromNum_B.getValueInt() + cMsg.B.length() - 1;

        } else {
            pagenationFrom = cMsg.xxPageShowFromNum_A.getValueInt() + cMsg.A.length() - 1;
        }
        NSBL0280CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSBL0280Scrn00_PagePrev(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg) {

        if (RESOURCE_TYPE.equals(cMsg.xxScrDply.getValue())) {
            if (!NSBL0280CommonLogic.checkEffFromDtResourceType(cMsg)) {
                return;
            }
        } else {
            if (!NSBL0280CommonLogic.checkEffFromDtSkillType(cMsg)) {
                return;
            }
        }

        NSBL0280CommonLogic.setUpdateFlg(cMsg, sMsg);

        int pageFromNum = 0;
        int pagenationFrom = 0;
        if (SKILL_TYPE.equals(cMsg.xxScrDply.getValue())) {
            if (!NSBL0280CommonLogic.checkEffFromDtResourceType(cMsg)) {
                return;
            }
            pageFromNum = cMsg.xxPageShowFromNum_B.getValueInt() - 1;
        } else {
            if (!NSBL0280CommonLogic.checkEffFromDtSkillType(cMsg)) {
                return;
            }
            pageFromNum = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
        }
        NSBL0280CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        if (SKILL_TYPE.equals(cMsg.xxScrDply.getValue())) {
            pagenationFrom = cMsg.xxPageShowFromNum_B.getValueInt() - cMsg.B.length() - 1;
        } else {
            pagenationFrom = cMsg.xxPageShowFromNum_A.getValueInt() - cMsg.A.length() - 1;
        }
        NSBL0280CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSBL0280Scrn00_Search_Resource(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg) {

        searchResource(cMsg, sMsg);

    }

    private void doProcess_NSBL0280Scrn00_Search_Skill(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg) {

        searchSkill(cMsg, sMsg);

    }

    private void doProcess_NSBL0280Scrn00_Switch_View(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg) {

        int pageFromNum = 0;
        if (SKILL_TYPE.equals(cMsg.xxScrDply.getValue())) {
            pageFromNum = cMsg.xxPageShowFromNum_B.getValueInt() - 1;
        } else {
            pageFromNum = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
        }
        NSBL0280CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        if (SKILL_TYPE.equals(cMsg.xxScrDply.getValue())) {

            int targetIdxNum = sMsg.xxRadioBtn_B.getValueInt();
            ZYPEZDItemValueSetter.setValue(cMsg.psnCd, sMsg.B.no(targetIdxNum).techCd_B);
            ZYPEZDItemValueSetter.setValue(cMsg.svcSkillResrcTpCd_S, sMsg.B.no(targetIdxNum).svcSkillResrcTpCd_BS);
            setValue(cMsg.xxScrDply, RESOURCE_TYPE);
            searchResource(cMsg, sMsg);

        } else {

            int targetIdxNum = sMsg.xxRadioBtn_A.getValueInt();
            ZYPEZDItemValueSetter.setValue(cMsg.svcSkillNum, sMsg.A.no(targetIdxNum).svcSkillNum_A);
            ZYPEZDItemValueSetter.setValue(cMsg.svcSkillTpCd_S, sMsg.A.no(targetIdxNum).svcSkillTpCd_A);
            ZYPEZDItemValueSetter.setValue(cMsg.svcSkillLvlGrpCd_BK, sMsg.A.no(targetIdxNum).svcSkillLvlGrpCd_A);
            ZYPEZDItemValueSetter.setValue(cMsg.svcSkillDescTxt, sMsg.A.no(targetIdxNum).svcSkillDescTxt_A);
            setValue(cMsg.xxScrDply, SKILL_TYPE);
            searchSkill(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0280Scrn00_TBLColumnSort(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        if ("A".equals(sortTblNm)) {

            // add start 2016/06/08 CSA Defect#9650
            int pageFromNum = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
            NSBL0280CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);
            // add end 2016/06/08 CSA Defect#9650

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            // SMsg -> CMsg
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // Show 1st page
            setValue(cMsg.xxPageShowFromNum_A, BigDecimal.ONE);
            setValue(cMsg.xxPageShowToNum_A, new BigDecimal(cMsg.A.getValidCount()));
        }

        if ("B".equals(sortTblNm)) {

            // add start 2016/06/08 CSA Defect#9650
            int pageFromNum = cMsg.xxPageShowFromNum_B.getValueInt() - 1;
            NSBL0280CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);
            // add end 2016/06/08 CSA Defect#9650

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.B, sMsg.B.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.B.getValidCount());

            // SMsg -> CMsg
            int i = 0;
            for (; i < sMsg.B.getValidCount(); i++) {
                if (i == cMsg.B.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
            }
            cMsg.B.setValidCount(i);

            // Show 1st page
            setValue(cMsg.xxPageShowFromNum_B, BigDecimal.ONE);
            setValue(cMsg.xxPageShowToNum_B, new BigDecimal(cMsg.B.getValidCount()));
        }
    }

    private void searchResource(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg) {

        if (!NSBL0280CommonLogic.isErrorSearchCondition(cMsg)) {
            return;
        }

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(sMsg.B);
        NSBL0280CommonLogic.getSearchResourceData(cMsg, sMsg);
        NSBL0280CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum_A.setValue(sMsg.A.getValidCount());
    }

    private void searchSkill(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg) {

        if (!NSBL0280CommonLogic.isErrorSearchCondition(cMsg)) {
            return;
        }

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(sMsg.B);
        NSBL0280CommonLogic.getSearchSkillData(cMsg, sMsg);
        NSBL0280CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum_B.setValue(sMsg.B.getValidCount());
    }

    private void doProcessInit(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg) {

        NSBL0280CommonLogic.clearMsg(cMsg, sMsg);

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());
        setValue(cMsg.xxScrDply, RESOURCE_TYPE);
        NSBL0280CommonLogic.createPullDown(cMsg);

        NSBL0280CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum_A.setValue(sMsg.A.getValidCount());
    }

}
