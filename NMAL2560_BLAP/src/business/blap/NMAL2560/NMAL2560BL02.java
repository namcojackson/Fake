/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2560;

import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL2560.common.NMAL2560CommonLogic;
import business.blap.NMAL2560.constant.NMAL2560Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_TIER;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL2560BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/13   Fujitsu         T.Ogura         Create          N/A
 *</pre>
 */
public class NMAL2560BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL2560CMsg bizMsg = (NMAL2560CMsg) cMsg;
            NMAL2560SMsg glblMsg = (NMAL2560SMsg) sMsg;

            if ("NMAL2560_INIT".equals(screenAplID)) {
                doProcess_NMAL2560_INIT(bizMsg, glblMsg);

            } else if ("NMAL2560Scrn00_AddBusinessArea".equals(screenAplID)) {
                doProcess_NMAL2560Scrn00_AddBusinessArea(bizMsg, glblMsg);

            } else if ("NMAL2560Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL2560Scrn00_CMN_Download(bizMsg, glblMsg);

            } else if ("NMAL2560Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL2560Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NMAL2560Scrn00_DelBusinessArea".equals(screenAplID)) {
                doProcess_NMAL2560Scrn00_DelBusinessArea(bizMsg, glblMsg);

            } else if ("NMAL2560Scrn00_Search_OrgHierarchyStructure".equals(screenAplID)) {
                doProcess_NMAL2560Scrn00_Search_OrgHierarchyStructure(bizMsg, glblMsg);

            } else if ("NMAL2560Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL2560Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2560_INIT(NMAL2560CMsg bizMsg, NMAL2560SMsg glblMsg) {

        // clear
        clearAll(bizMsg, glblMsg);

        // create Pulldown
        ZYPCodeDataUtil.createPulldownList("BIZ_AREA_ORG", bizMsg.bizAreaOrgCd_L, bizMsg.bizAreaOrgDescTxt_L);
        ZYPCodeDataUtil.createPulldownList("STRU_DFN", bizMsg.struDfnCd_L, bizMsg.struDfnDescTxt_L);
    }

    /**
     * AddBusinessArea Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2560Scrn00_AddBusinessArea(NMAL2560CMsg bizMsg, NMAL2560SMsg glblMsg) {

        int nextIdx = bizMsg.A.getValidCount();
        bizMsg.A.setValidCount(nextIdx + 1);

        // set Parameter
        bizMsg.A.no(nextIdx).xxRowId_A.setValue(NMAL2560Constant.INSERT_FLG);

        if (ZYPCommonFunc.hasValue(bizMsg.bizAreaOrgCd)) {
            bizMsg.A.no(nextIdx).bizAreaOrgCd_A.setValue(bizMsg.bizAreaOrgCd.getValue());
        }

        bizMsg.A.no(nextIdx).effFromDt_A.setValue(ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2560Scrn00_CMN_Download(NMAL2560CMsg bizMsg, NMAL2560SMsg glblMsg) {
        NMAL2560Query.getInstance().createDetailCSV(bizMsg, glblMsg);
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2560Scrn00_CMN_Submit(NMAL2560CMsg bizMsg, NMAL2560SMsg glblMsg) {
        search(bizMsg, glblMsg);
    }

    /**
     * DelBusinessArea Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2560Scrn00_DelBusinessArea(NMAL2560CMsg bizMsg, NMAL2560SMsg glblMsg) {

        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox_A", ZYPConstant.FLG_ON_Y);

        if (selectRows.isEmpty()) {
            bizMsg.setMessageInfo(NMAL2560Constant.NMAM8054E);
            return;
        }

        ZYPTableUtil.deleteRows(bizMsg.A, selectRows);
    }

    /**
     * Search_OrgHierarchyStructure Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2560Scrn00_Search_OrgHierarchyStructure(NMAL2560CMsg bizMsg, NMAL2560SMsg glblMsg) {
        search(bizMsg, glblMsg);
    }

    /**
     * CMN_Clear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2560Scrn00_CMN_Clear(NMAL2560CMsg bizMsg, NMAL2560SMsg glblMsg) {
        clearAll(bizMsg, glblMsg);
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NMAL2560CMsg bizMsg, NMAL2560SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(glblMsg.C);

        S21SsmEZDResult ssmResult = NMAL2560Query.getInstance().getSearchList(bizMsg, glblMsg);

        if (ssmResult.isCodeNormal()) {

            String wkBizAreaOrgCd = "";
            String wkEffFromDt = "";
            int dispIdx = 0;

            for (int i = 0; i < glblMsg.C.getValidCount(); i++) {

                if (i != 0 && (!glblMsg.C.no(i).bizAreaOrgCd_C.getValue().equals(wkBizAreaOrgCd) || !glblMsg.C.no(i).effFromDt_C.getValue().equals(wkEffFromDt))) {
                    ++dispIdx;
                }

                if (dispIdx + 1 > glblMsg.A.length()) {
                    bizMsg.setMessageInfo(NMAL2560Constant.ZZZM9002W);
                    glblMsg.A.setValidCount(glblMsg.A.length());
                    break;
                } else {
                    glblMsg.A.setValidCount(dispIdx + 1);
                }

                if (ORG_TIER._1.equals(glblMsg.C.no(i).orgTierCd_C.getValue())) {
                    glblMsg.A.no(dispIdx).xxRowId_A.setValue(glblMsg.C.no(i).xxRowId_C.getValue());
                    glblMsg.A.no(dispIdx).bizAreaOrgCd_A.setValue(glblMsg.C.no(i).bizAreaOrgCd_C.getValue());
                    glblMsg.A.no(dispIdx).orgHrchStruDfnNm_A.setValue(glblMsg.C.no(i).orgHrchStruDfnNm_C.getValue());
                    glblMsg.A.no(dispIdx).effFromDt_A.setValue(glblMsg.C.no(i).effFromDt_C.getValue());
                    glblMsg.A.no(dispIdx).effThruDt_A.setValue(glblMsg.C.no(i).effThruDt_C.getValue());
                    glblMsg.A.no(dispIdx).struDfnCd_1.setValue(glblMsg.C.no(i).struDfnCd_C.getValue());
                    glblMsg.A.no(dispIdx).xxChkMaxDescTxt_IN.setValue(glblMsg.C.no(i).xxChkMaxDescTxt_IN.getValue());
                    glblMsg.A.no(dispIdx).xxDtTm_IN.setValue(NMAL2560CommonLogic.formatDt14ToDisp(glblMsg.C.no(i).ezInTime_C.getValue()));
                    glblMsg.A.no(dispIdx).xxChkMaxDescTxt_UP.setValue(glblMsg.C.no(i).xxChkMaxDescTxt_UP.getValue());
                    glblMsg.A.no(dispIdx).xxDtTm_UP.setValue(NMAL2560CommonLogic.formatDt14ToDisp(glblMsg.C.no(i).ezUpTime_C.getValue()));
                    glblMsg.A.no(dispIdx).orgHrchStruDfnPk_1.setValue(glblMsg.C.no(i).orgHrchStruDfnPk_C.getValue());
                    glblMsg.A.no(dispIdx).ezUpTime_A.setValue(glblMsg.C.no(i).ezUpTime_C.getValue());
                    glblMsg.A.no(dispIdx).ezUpTimeZone_A.setValue(glblMsg.C.no(i).ezUpTimeZone_C.getValue());
                } else if (ORG_TIER._2.equals(glblMsg.C.no(i).orgTierCd_C.getValue())) {
                    glblMsg.A.no(dispIdx).struDfnCd_2.setValue(glblMsg.C.no(i).struDfnCd_C.getValue());
                    glblMsg.A.no(dispIdx).orgHrchStruDfnPk_2.setValue(glblMsg.C.no(i).orgHrchStruDfnPk_C.getValue());
                } else if (ORG_TIER._3.equals(glblMsg.C.no(i).orgTierCd_C.getValue())) {
                    glblMsg.A.no(dispIdx).struDfnCd_3.setValue(glblMsg.C.no(i).struDfnCd_C.getValue());
                    glblMsg.A.no(dispIdx).orgHrchStruDfnPk_3.setValue(glblMsg.C.no(i).orgHrchStruDfnPk_C.getValue());
                } else if (ORG_TIER._4.equals(glblMsg.C.no(i).orgTierCd_C.getValue())) {
                    glblMsg.A.no(dispIdx).struDfnCd_4.setValue(glblMsg.C.no(i).struDfnCd_C.getValue());
                    glblMsg.A.no(dispIdx).orgHrchStruDfnPk_4.setValue(glblMsg.C.no(i).orgHrchStruDfnPk_C.getValue());
                } else if (ORG_TIER._5.equals(glblMsg.C.no(i).orgTierCd_C.getValue())) {
                    glblMsg.A.no(dispIdx).struDfnCd_5.setValue(glblMsg.C.no(i).struDfnCd_C.getValue());
                    glblMsg.A.no(dispIdx).orgHrchStruDfnPk_5.setValue(glblMsg.C.no(i).orgHrchStruDfnPk_C.getValue());
                } else if (ORG_TIER._6.equals(glblMsg.C.no(i).orgTierCd_C.getValue())) {
                    glblMsg.A.no(dispIdx).struDfnCd_6.setValue(glblMsg.C.no(i).struDfnCd_C.getValue());
                    glblMsg.A.no(dispIdx).orgHrchStruDfnPk_6.setValue(glblMsg.C.no(i).orgHrchStruDfnPk_C.getValue());
                } else if (ORG_TIER._7.equals(glblMsg.C.no(i).orgTierCd_C.getValue())) {
                    glblMsg.A.no(dispIdx).struDfnCd_7.setValue(glblMsg.C.no(i).struDfnCd_C.getValue());
                    glblMsg.A.no(dispIdx).orgHrchStruDfnPk_7.setValue(glblMsg.C.no(i).orgHrchStruDfnPk_C.getValue());
                } else if (ORG_TIER._8.equals(glblMsg.C.no(i).orgTierCd_C.getValue())) {
                    glblMsg.A.no(dispIdx).struDfnCd_8.setValue(glblMsg.C.no(i).struDfnCd_C.getValue());
                    glblMsg.A.no(dispIdx).orgHrchStruDfnPk_8.setValue(glblMsg.C.no(i).orgHrchStruDfnPk_C.getValue());
                } else if (ORG_TIER._9.equals(glblMsg.C.no(i).orgTierCd_C.getValue())) {
                    glblMsg.A.no(dispIdx).struDfnCd_9.setValue(glblMsg.C.no(i).struDfnCd_C.getValue());
                    glblMsg.A.no(dispIdx).orgHrchStruDfnPk_9.setValue(glblMsg.C.no(i).orgHrchStruDfnPk_C.getValue());
                } else if (ORG_TIER._10.equals(glblMsg.C.no(i).orgTierCd_C.getValue())) {
                    glblMsg.A.no(dispIdx).struDfnCd_10.setValue(glblMsg.C.no(i).struDfnCd_C.getValue());
                    glblMsg.A.no(dispIdx).orgHrchStruDfnPk_10.setValue(glblMsg.C.no(i).orgHrchStruDfnPk_C.getValue());
                }

                wkBizAreaOrgCd = glblMsg.C.no(i).bizAreaOrgCd_C.getValue();
                wkEffFromDt = glblMsg.C.no(i).effFromDt_C.getValue();
            }

            EZDMsg.copy(glblMsg.A, "A", glblMsg.B, "B");
            EZDMsg.copy(glblMsg.A, null, glblMsg.B, null);
            EZDMsg.copy(glblMsg.A, null, bizMsg.A, null);
        } else {
            bizMsg.setMessageInfo(NMAL2560Constant.NMAM0007I);
        }
    }

    /**
     * clearAll
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void clearAll(NMAL2560CMsg bizMsg, NMAL2560SMsg glblMsg) {

        // Clear Table
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(glblMsg.C);

        // Header
        bizMsg.bizAreaOrgCd.clear();
        bizMsg.xxChkBox.clear();
        bizMsg.xxFileData.clear();
    }
}
