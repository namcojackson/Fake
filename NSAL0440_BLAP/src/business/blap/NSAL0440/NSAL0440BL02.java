/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0440;

import static business.blap.NSAL0440.constant.NSAL0440Constant.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0440.common.NSAL0440CommonLogic;
import business.db.SVC_TERM_COND_CATGTMsgArray;

import com.canon.cusa.s21.common.NSX.NSXC002001.DefSvcTermCondInfoBean;
import com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001SvcTermCond;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Terms & Conditions
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/25   Hitachi         T.Iwamoto       Create          N/A
 * 2015/12/01   Hitachi         T.Iwamoto       Update          QC#1208
 * 2016/09/29   Hitachi         A.Kohinata      Update          QC#12898
 * 2016/11/01   Hitachi         T.Kanasaka      Update          QC#15136
 * 2022/08/03   Hitachi         N.Takatsu       Update          QC#60077
 *</pre>
 */
public class NSAL0440BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0440_INIT".equals(screenAplID)) {
                doProcess_NSAL0440_INIT((NSAL0440CMsg) cMsg, (NSAL0440SMsg) sMsg);
            } else if ("NSAL0440Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0440Scrn00_PagePrev((NSAL0440CMsg) cMsg, (NSAL0440SMsg) sMsg);
            } else if ("NSAL0440Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0440Scrn00_PageNext((NSAL0440CMsg) cMsg, (NSAL0440SMsg) sMsg);
            } else if ("NSAL0440Scrn00_Collapse".equals(screenAplID)) {
                doProcess_NSAL0440Scrn00_Collapse((NSAL0440CMsg) cMsg, (NSAL0440SMsg) sMsg);
            } else if ("NSAL0440Scrn00_Search".equals(screenAplID) || "NSAL0440Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0440Scrn00_Search((NSAL0440CMsg) cMsg, (NSAL0440SMsg) sMsg);
            // START 2022/08/03 N.Takatsu [QC#60077, ADD]
            } else if ("NSAL0440Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0440_INIT((NSAL0440CMsg) cMsg, (NSAL0440SMsg) sMsg);
            } else if ("NSAL0440Scrn00_ExpandAll".equals(screenAplID)) {
                doProcess_NSAL0440Scrn00_ExpandAll((NSAL0440CMsg) cMsg, (NSAL0440SMsg) sMsg);
            // END 2022/08/03 N.Takatsu [QC#60077, ADD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Init)
     * @param cMsg NSAL0440CMsg
     * @param sMsg NSAL0440SMsg
     */
    private void doProcess_NSAL0440_INIT(NSAL0440CMsg cMsg, NSAL0440SMsg sMsg) {

        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);

        cMsg.glblCmpyCd.setValue(S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());
        // START 2022/08/03 N.Takatsu [QC#60077, ADD]
        cMsg.xxChkBox_H1.clear();
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_H2, ZYPConstant.CHKBOX_ON_Y);
        // END 2022/08/03 N.Takatsu [QC#60077, ADD]
        if (createSvcTermCategoryPullDown(cMsg)) {
            if (getContractData(cMsg)) {
                // get Detail Data and Set SMsg
                getSearchData(cMsg, sMsg);
                // START 2016/09/29 A.Kohinata [QC#12898, ADD]
                setSvcTermCondAttrbPkList(cMsg);
                // END 2016/09/29 A.Kohinata [QC#12898, ADD]
            }
            // START 2022/08/03 N.Takatsu [QC#60077, ADD]
            setParamSerNum(cMsg);
            // END 2022/08/03 N.Takatsu [QC#60077, ADD]
        }
    }

    // START 2022/08/03 N.Takatsu [QC#60077, ADD]
    void setParamSerNum(NSAL0440CMsg cMsg) {
        if (cMsg.R.getValidCount() == 0) {
            return;
        }
        for (int i = 0; i < cMsg.R.getValidCount(); i++) {
            String serNum = NSAL0440Query.getInstance().getSerNumByDsContrDtlPk(cMsg.glblCmpyCd.getValue(), cMsg.R.no(i).dsContrDtlPk_R.getValue());
            if (serNum != null) {
                ZYPEZDItemValueSetter.setValue(cMsg.serNum, serNum);
                break;
            }
        }
    }

    // END 2022/08/03 N.Takatsu [QC#60077, ADD]

    /**
     * do process (Page Prev)
     * @param cMsg NSAL0440CMsg
     * @param sMsg NSAL0440SMsg
     */
    private void doProcess_NSAL0440Scrn00_PagePrev(NSAL0440CMsg cMsg, NSAL0440SMsg sMsg) {

        NSAL0440CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSAL0440CommonLogic.setViewData_A(sMsg);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSAL0440CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);

        // createDetailPulldown
        NSAL0440CommonLogic.createDetailPulldown(cMsg);
    }

    /**
     * do process (Page Next)
     * @param cMsg NSAL0440CMsg
     * @param sMsg NSAL0440SMsg
     */
    private void doProcess_NSAL0440Scrn00_PageNext(NSAL0440CMsg cMsg, NSAL0440SMsg sMsg) {

        NSAL0440CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSAL0440CommonLogic.setViewData_A(sMsg);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSAL0440CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);

        // createDetailPulldown
        NSAL0440CommonLogic.createDetailPulldown(cMsg);
    }

    /**
     * do process (Collapse)
     * @param cMsg NSAL0440CMsg
     * @param sMsg NSAL0440SMsg
     */
    private void doProcess_NSAL0440Scrn00_Collapse(NSAL0440CMsg cMsg, NSAL0440SMsg sMsg) {
        // CMsg->BSMsg
        NSAL0440CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        // BMsg->ASMsg
        NSAL0440CommonLogic.setViewData_A(sMsg);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL0440CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);

        // createDetailPulldown
        NSAL0440CommonLogic.createDetailPulldown(cMsg);
    }
    
	// START 2022/08/03 N.Takatsu [QC#60077, MOD]
    /**
     * do process (ExpandAll)
     * @param cMsg NSAL0440CMsg
     * @param sMsg NSAL0440SMsg
     */
    private void doProcess_NSAL0440Scrn00_ExpandAll(NSAL0440CMsg cMsg, NSAL0440SMsg sMsg) {

        String openCloseFlg = ZYPConstant.FLG_ON_Y;
        if (IMG_OPEN_ALL.equals(cMsg.xxFilePathTxt.getValue())) {
            openCloseFlg =  ZYPConstant.FLG_ON_Y;
            ZYPEZDItemValueSetter.setValue(cMsg.xxFilePathTxt, IMG_CLOSE_ALL);
        } else {
            openCloseFlg =  ZYPConstant.FLG_OFF_N;
            ZYPEZDItemValueSetter.setValue(cMsg.xxFilePathTxt, IMG_OPEN_ALL);
        }
        // CMsg->BSMsg
        NSAL0440CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxBtnFlg_A, openCloseFlg);
        }

        // BMsg->ASMsg
        NSAL0440CommonLogic.setViewData_A(sMsg);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        if (pagenationFrom >= sMsg.A.getValidCount()) {
            pagenationFrom = 0;
        }
        NSAL0440CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);

        // createDetailPulldown
        NSAL0440CommonLogic.createDetailPulldown(cMsg);
    }
    // END 2022/08/03 N.Takatsu [QC#60077, ADD]

    /**
     * do process (Search)
     * @param cMsg NSAL0440CMsg
     * @param sMsg NSAL0440SMsg
     */
    private void doProcess_NSAL0440Scrn00_Search(NSAL0440CMsg cMsg, NSAL0440SMsg sMsg) {

        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);

        // get Detail Data and Set SMsg
        getSearchData(cMsg, sMsg);

        // [QC#1208,MOD]START
        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NSAM0200I);
        }
        // [QC#1208,MOD]END

    }

    /**
     * get Search Data List
     * @param cMsg NSAL0440CMsg
     * @return Data List
     */
    private void getSearchData(NSAL0440CMsg cMsg, NSAL0440SMsg sMsg) {

        sMsg.clear();
        S21SsmEZDResult ssmResult = NSAL0440Query.getInstance().getSearchData(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {
            // Result > 2000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NSAM0024W, new String[] {Integer.toString(sMsg.A.length()), Integer.toString(sMsg.A.length()) });
                queryResCnt = sMsg.A.length();
            }

            // START 2016/11/01 T.Kanasaka [QC#15136, ADD]
            setAttrbUpdAvalFlg(cMsg, sMsg);
            // END 2016/11/01 T.Kanasaka [QC#15136, ADD]

            // BSMsg->ASMsg
            NSAL0440CommonLogic.setViewData_A(sMsg);

            // Copy one page from SAMsg to CMsg
            if (cMsg.A.length() > sMsg.A.getValidCount()) {
                cMsg.A.setValidCount(sMsg.A.getValidCount());
            } else {
                cMsg.A.setValidCount(cMsg.A.length());
            }
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }

            // createDetailPulldown
            NSAL0440CommonLogic.createDetailPulldown(cMsg);

            // set Paging Data
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
        } else {
            // No result
            cMsg.setMessageInfo(NZZM0000E);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }

    private boolean getContractData(NSAL0440CMsg cMsg) {
        S21SsmEZDResult ssmResult = NSAL0440Query.getInstance().getContractData(cMsg);

        if (ssmResult.isCodeNormal()) {
            return true;
        }
        // No result
        cMsg.setMessageInfo(NSAM0353E, new String[] {ERR_PRAM_NO_DATA });
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();
        return false;
    }

    private boolean createSvcTermCategoryPullDown(NSAL0440CMsg cMsg) {
        S21SsmEZDResult ssmResult = NSAL0440Query.getInstance().getSvcTermCategoryPulldownList(cMsg);

        if (ssmResult.isCodeNormal()) {
            SVC_TERM_COND_CATGTMsgArray tMsgArray = (SVC_TERM_COND_CATGTMsgArray) ssmResult.getResultObject();
            Map<String, String> tMsgKeys = new HashMap<String, String>();
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "svcTermCondCatgNm");
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "svcTermCondCatgDescTxt");
            // START 2022/08/03 N.Takatsu [QC#60077, MOD]            
            // ZYPPulldownValueSetter.set(tMsgArray, tMsgKeys, cMsg.svcTermCondCatgNm_PC, cMsg.svcTermCondCatgDescTxt_PC);
            // ZYPPulldownValueSetter.insertFirstData(ALL_VALUE, cMsg.svcTermCondCatgNm_PC, ALL, cMsg.svcTermCondCatgDescTxt_PC);
            ZYPEZDItemValueSetter.setValue(cMsg.xxFilePathTxt, IMG_OPEN_ALL);
            cMsg.X.clear();
            ZYPEZDItemValueSetter.setValue(cMsg.X.no(0).svcTermCondCatgDescTxt_X, ALL);
            ZYPEZDItemValueSetter.setValue(cMsg.X.no(0).svcTermCondCatgPk_X, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.X.no(0).xxChkBox_X, ZYPConstant.FLG_ON_Y);
            cMsg.X.setValidCount(1);
            for (int i = 0; i <= tMsgArray.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(cMsg.X.no(i + 1).svcTermCondCatgDescTxt_X, tMsgArray.no(i).svcTermCondCatgDescTxt);
                ZYPEZDItemValueSetter.setValue(cMsg.X.no(i + 1).svcTermCondCatgPk_X, tMsgArray.no(i).svcTermCondCatgPk);
                cMsg.X.setValidCount(i + 1);
            }
            // END 2022/08/03 N.Takatsu [QC#60077, MOD]
            return true;
        }
        // No result
        cMsg.setMessageInfo(NSAM0353E, new String[] {ERR_PRAM_NO_DATA });
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();
        return false;
    }

    // START 2016/09/29 A.Kohinata [QC#12898, ADD]
    private void setSvcTermCondAttrbPkList(NSAL0440CMsg cMsg) {
        if (!DS_CONTR_CATG.FLEET.equals(cMsg.dsContrCatgCd.getValue())) {
            return;
        }
        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        String constVal = ZYPCodeDataUtil.getVarCharConstValue(TERM_COND_FLT_CAP_INACT_FLG, glblCmpyCd);
        if (!ZYPConstant.FLG_ON_Y.equals(constVal)) {
            return;
        }
        List<String> svcTermCondAttrbNmList = new ArrayList<String>();
        setConstValList(svcTermCondAttrbNmList, glblCmpyCd, TERM_COND_CAP_BW_ORG_ATTRB_NM);
        setConstValList(svcTermCondAttrbNmList, glblCmpyCd, TERM_COND_CAP_CLR_ORG_ATTRB_NM);
        setConstValList(svcTermCondAttrbNmList, glblCmpyCd, TERM_COND_CAP_TOT_ORG_ATTRB_NM);
        setConstValList(svcTermCondAttrbNmList, glblCmpyCd, TERM_COND_CAP_BW_RUN_ATTRB_NM);
        setConstValList(svcTermCondAttrbNmList, glblCmpyCd, TERM_COND_CAP_CLR_RUN_ATTRB_NM);
        setConstValList(svcTermCondAttrbNmList, glblCmpyCd, TERM_COND_CAP_TOT_RUN_ATTRB_NM);
        if (svcTermCondAttrbNmList.size() == 0) {
            return;
        }
        NSAL0440Query.getInstance().getSvcTermCondAttrbPkList(cMsg, svcTermCondAttrbNmList);
    }

    private void setConstValList(List<String> constValList, String glblCmpyCd, String constKey) {
        String constVal = ZYPCodeDataUtil.getVarCharConstValue(constKey, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(constVal)) {
            return;
        }
        constValList.add(constVal);
    }
    // END 2016/09/29 A.Kohinata [QC#12898, ADD]

    private void setAttrbUpdAvalFlg(NSAL0440CMsg cMsg, NSAL0440SMsg sMsg) {
        List<DefSvcTermCondInfoBean> defSvcTermCondInfoBeanListH = null;
        Map<String, List<DefSvcTermCondInfoBean>> defSvcTermCondMap = new HashMap<String, List<DefSvcTermCondInfoBean>>();

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(sMsg.B.no(i).dsContrDtlPk_A)) {
                // Contract Level
                if (defSvcTermCondInfoBeanListH == null) {
                    String svcPgmMdseCd = sMsg.B.no(i).svcPgmMdseCd_A.getValue();
                    if (!ZYPCommonFunc.hasValue(svcPgmMdseCd)) {
                        continue;
                    }
                    defSvcTermCondInfoBeanListH = NSXC002001SvcTermCond.getTermCondInfoForContrLvl(cMsg.glblCmpyCd.getValue(), cMsg.slsDt.getValue(), svcPgmMdseCd);
                }

                for (DefSvcTermCondInfoBean defSvcTermCondInfoBean : defSvcTermCondInfoBeanListH) {
                    if (sMsg.B.no(i).svcTermCondAttrbPk_A.getValue().compareTo(defSvcTermCondInfoBean.getSvcTermCondAttrbPk()) == 0) {
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).attrbUpdAvalFlg_A, defSvcTermCondInfoBean.getAttrbUpdAvalFlg());
                        break;
                    }
                }
            } else {
                // Machine Level
                String svcPgmMdseCd = sMsg.B.no(i).svcPgmMdseCd_A.getValue();
                if (!ZYPCommonFunc.hasValue(svcPgmMdseCd)) {
                    continue;
                }
                List<DefSvcTermCondInfoBean> defSvcTermCondInfoBeanListD = null;
                if (defSvcTermCondMap.containsKey(svcPgmMdseCd)) {
                    defSvcTermCondInfoBeanListD = defSvcTermCondMap.get(svcPgmMdseCd);
                } else {
                    defSvcTermCondInfoBeanListD = NSXC002001SvcTermCond.getTermCondInfoForMachLvl(cMsg.glblCmpyCd.getValue(), cMsg.slsDt.getValue(), svcPgmMdseCd);
                    defSvcTermCondMap.put(svcPgmMdseCd, defSvcTermCondInfoBeanListD);
                }

                for (DefSvcTermCondInfoBean defSvcTermCondInfoBean : defSvcTermCondInfoBeanListD) {
                    if (sMsg.B.no(i).svcTermCondAttrbPk_A.getValue().compareTo(defSvcTermCondInfoBean.getSvcTermCondAttrbPk()) == 0) {
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).attrbUpdAvalFlg_A, defSvcTermCondInfoBean.getAttrbUpdAvalFlg());
                        break;
                    }
                }
            }
        }
    }
}
