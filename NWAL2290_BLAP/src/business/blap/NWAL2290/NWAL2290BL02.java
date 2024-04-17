/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2290;

import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL2290.constant.NWAL2290Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL2290BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/01/05   Fujitsu         A.Kosai         Create          N/A
 * 2019/03/27   Fujitsu         K.Ishizuka      Update          S21_NA#30765
 *</pre>
 */
public class NWAL2290BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();
            NWAL2290CMsg bizMsg = (NWAL2290CMsg) cMsg;

            if ("NWAL2290_INIT".equals(screenAplID)) {
                doProcess_NWAL2290_INIT(bizMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NWAL2290_INIT(NWAL2290CMsg bizMsg) {

        if (!checkParams(bizMsg)) {
            return;
        }

        // 2019/03/27 S21_NA#30765 Mod Start
        // S21SsmEZDResult ssmResult = getEopsCpoList(bizMsg);
        S21SsmEZDResult ssmResult = null;
        if (CPO_SRC_TP.EOPS.equals(bizMsg.cpoSrcTpCd.getValue())) {
            ssmResult = getEopsCpoList(bizMsg);
        } else if (CPO_SRC_TP.DEAL_CONFIGURATOR.equals(bizMsg.cpoSrcTpCd.getValue())) {
            ssmResult = getDealConfigCpoList(bizMsg);
        } else {
            bizMsg.setMessageInfo(NWAL2290Constant.NMZM0143E, new String[] { "CPO Source Type Code" });
        }
        // 2019/03/27 S21_NA#30765 Mod End

        if (ssmResult.isCodeNormal()) {

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int i = 0; i < rsltList.size();) {
                Map<String, Object> rsltMap = rsltList.get(i);

                NWAL2290_ACMsg row = bizMsg.A.no(i);
                ZYPEZDItemValueSetter.setValue(row.cpoOrdNum_A, (String) rsltMap.get("CPO_ORD_NUM"));
                ZYPEZDItemValueSetter.setValue(row.ordSrcRefNum_A, (String) rsltMap.get("ORD_SRC_REF_NUM"));
                ZYPEZDItemValueSetter.setValue(row.imptStsDescTxt_A, (String) rsltMap.get("IMPT_STS_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(row.dsImptOrdErrTxt_A, (String) rsltMap.get("DS_IMPT_ORD_ERR_TXT"));
                bizMsg.A.setValidCount(++i);
            }
        } else {
            bizMsg.setMessageInfo(NWAL2290Constant.NZZM0000E);
        }
    }

    private boolean checkParams(NWAL2290CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.ordSrcRefNum)) {
            bizMsg.setMessageInfo(NWAL2290Constant.NZZM0012E, new String[] { "Source Reference Number" });
            return false;
        }
        
        // 2019/03/27 S21_NA#30765 Add Start
        if (!ZYPCommonFunc.hasValue(bizMsg.cpoSrcTpCd)) {
            bizMsg.setMessageInfo(NWAL2290Constant.NZZM0012E, new String[] { "CPO Source Type Code" });
            return false;
        }
        // 2019/03/27 S21_NA#30765 Add End

        return true;
    }

    private S21SsmEZDResult getEopsCpoList(NWAL2290CMsg bizMsg) {

        return NWAL2290Query.getInstance().getEopsCpoList(getGlobalCompanyCode(), bizMsg);
    }
    
    // 2019/03/27 S21_NA#30765 Add Start
    private S21SsmEZDResult getDealConfigCpoList(NWAL2290CMsg bizMsg) {

        return NWAL2290Query.getInstance().getDealConfigCpoList(getGlobalCompanyCode(), bizMsg);
    }
    // 2019/03/27 S21_NA#30765 Add Start
}