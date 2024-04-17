/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7220;

import static business.blap.NMAL7220.constant.NMAL7220Constant.NMAM0007I;
import static business.blap.NMAL7220.constant.NMAL7220Constant.NMAM8203W;
import static business.blap.NMAL7220.constant.NMAL7220Constant.VAR_CHAR_CONST_STD_COST_DUMMY_PRC_LIST;

import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL7220.common.NMAL7220CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_FMLA_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL7210BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/27   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public class NMAL7220BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7220CMsg bizMsg = (NMAL7220CMsg) cMsg;

            if ("NMAL7220_INIT".equals(screenAplID)) {
                doProcess_NMAL7220_INIT(bizMsg);
            } else if ("NMAL7220Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NMAL7220Scrn00_CMN_Reset(bizMsg);

            } else if ("NMAL7220Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7220Scrn00_CMN_Submit(bizMsg);

            } else if ("NMAL7220Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL7220Scrn00_Search(bizMsg);

            } else if ("NMAL7220Scrn00_OnChange_Formula_Type".equals(screenAplID)) {
                doProcess_NMAL7220_OnChange_Formula_Type(bizMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * 
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7220_INIT(NMAL7220CMsg bizMsg) {
        // Create Pull Down.
        NMAL7220CommonLogic.createPullDown(bizMsg);

        if (ZYPCommonFunc.hasValue(bizMsg.prcFmlaPk_BK)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcFmlaPk_H1, bizMsg.prcFmlaPk_BK);
            bizMsg.A.setValidCount(1);
            search(bizMsg);
        } else {
            bizMsg.A.setValidCount(1);
        }
    }

    /**
     * CMN_Reset Event
     * 
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7220Scrn00_CMN_Reset(NMAL7220CMsg bizMsg) {
        doProcess_NMAL7220_INIT(bizMsg);
    }

    /**
     * CMN_Submit Event
     * 
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7220Scrn00_CMN_Submit(NMAL7220CMsg bizMsg) {
        // search
        search(bizMsg);
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7220Scrn00_Search(NMAL7220CMsg bizMsg) {
        // search
        search(bizMsg);
    }

    /**
     * OnChange_Formula_Type
     * 
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7220_OnChange_Formula_Type(NMAL7220CMsg bizMsg) {
        String formulaType = bizMsg.A.no(0).prcFmlaTpCd_A1.getValue();
        if (PRC_FMLA_TP.STANDARD_COST.equals(formulaType)) {
            // Get VarCharConst
            String priceList = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_STD_COST_DUMMY_PRC_LIST, getGlobalCompanyCode());
            if (!ZYPCommonFunc.hasValue(priceList)) {
                bizMsg.A.no(0).prcCatgNm_A1.setErrorInfo(2, NMAM8203W, new String[] {"Standard Cost"});
                bizMsg.A.no(0).prcCatgNm_A1.clear();
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).prcCatgNm_A1, priceList);
            }
        } else {
            bizMsg.A.no(0).prcCatgNm_A1.clear();
        }
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     */
    private void search(NMAL7220CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NMAL7220Query.getInstance().search(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0007I);
            bizMsg.A.setValidCount(1);
            return;
        } else {
            NMAL7220CommonLogic.createSearchData(bizMsg, (Map< ? , ? >) ssmResult.getResultObject(), getGlobalCompanyCode());
            bizMsg.A.setValidCount(1);
            ZYPEZDItemValueSetter.setValue(bizMsg.prcFmlaPk_BK, bizMsg.prcFmlaPk_H1);
        }
    }
}
