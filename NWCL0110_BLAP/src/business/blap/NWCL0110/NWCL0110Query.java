/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0110;

import java.util.HashMap;
import java.util.Map;

import business.blap.NWCL0110.constant.NWCL0110Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/25   Fujitsu         H.Nagashima     Create          N/A
 * 2017/12/21   SRAA            K.Aratani       Update          QC#18692
 *</pre>
 */
public final class NWCL0110Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWCL0110Query MY_INSTANCE = new NWCL0110Query();

    /**
     * Private constructor
     */
    private NWCL0110Query() {
        super();
    }

    /**
     * Get the NWCL0110Query instance.
     * @return NWCL0110Query instance
     */
    public static NWCL0110Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * get Invoice Print Control
     * @param cMsg NWCL0110CMsg
     * @param sMsg NWCL0110SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvPrintControl(NWCL0110CMsg cMsg, NWCL0110SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", sMsg.A.length() + 1);
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("invPrtBatTpCd",   cMsg.invPrtBatTpCd.getValue());
        ssmParam.put("invPrtBrCd",      cMsg.invPrtBrCd.getValue());
        ssmParam.put("conslBillNum_FR", cMsg.conslBillNum_FR.getValue());
        ssmParam.put("conslBillNum_TO", cMsg.conslBillNum_TO.getValue());
        ssmParam.put("invNum_FR",       cMsg.invNum_FR.getValue());
        ssmParam.put("invNum_TO",       cMsg.invNum_TO.getValue());
        ssmParam.put("xxInvNum_FR",     cMsg.xxInvNum_FR.getValue());
        ssmParam.put("xxInvNum_TO",     cMsg.xxInvNum_TO.getValue());
        ssmParam.put("procDt_FR",       cMsg.procDt_FR.getValue());
        ssmParam.put("procDt_TO",       cMsg.procDt_TO.getValue());
        ssmParam.put("invPrtRqstSq_FR", cMsg.invPrtRqstSq_FR.getValue());
        ssmParam.put("invPrtRqstSq_TO", cMsg.invPrtRqstSq_TO.getValue());
        ssmParam.put("cupsPrintRqstId_FR", cMsg.cupsPrintRqstId_FR.getValue());
        ssmParam.put("cupsPrintRqstId_TO", cMsg.cupsPrintRqstId_TO.getValue());
        // QC#53014 2019/09/17 Add Start
        ssmParam.put("xxChkBoxSP", cMsg.xxChkBox_SP.getValue());
        ssmParam.put("ExclBillTxtNo", NWCL0110Constant.NO);
        // QC#53014 2019/09/17 Add End
        
        if (NWCL0110Constant.CONST_OTPT_TP_PRT.equals(cMsg.xxTpCd.getValue())
         || NWCL0110Constant.CONST_OTPT_TP_ALL.equals(cMsg.xxTpCd.getValue())) {
            ssmParam.put("prtFlg", ZYPConstant.FLG_ON_Y);
        }
        if (NWCL0110Constant.CONST_OTPT_TP_EML.equals(cMsg.xxTpCd.getValue())
         || NWCL0110Constant.CONST_OTPT_TP_ALL.equals(cMsg.xxTpCd.getValue())) {
            ssmParam.put("emlFlg", ZYPConstant.FLG_ON_Y);
        }
        if (NWCL0110Constant.CONST_OTPT_TP_PDF.equals(cMsg.xxTpCd.getValue())
         || NWCL0110Constant.CONST_OTPT_TP_ALL.equals(cMsg.xxTpCd.getValue())) {
            ssmParam.put("pdfFlg", ZYPConstant.FLG_ON_Y);
        }
        if (NWCL0110Constant.CONST_OTPT_TP_EDI.equals(cMsg.xxTpCd.getValue())
         || NWCL0110Constant.CONST_OTPT_TP_ALL.equals(cMsg.xxTpCd.getValue())) {
            ssmParam.put("ediFlg", ZYPConstant.FLG_ON_Y);
        }
        if (NWCL0110Constant.CONST_OTPT_TP_SB.equals(cMsg.xxTpCd.getValue())
         || NWCL0110Constant.CONST_OTPT_TP_ALL.equals(cMsg.xxTpCd.getValue())) {
            ssmParam.put("sbFlg", ZYPConstant.FLG_ON_Y);
        }

        return getSsmEZDClient().queryEZDMsgArray("getInvPrtCtrl", ssmParam, sMsg.A);
    }

    /**
     * get EIP Report Control
     * @param rptId String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getEipReportControl(String rptId) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("rptId",   rptId);

        return getSsmEZDClient().queryObjectList("getEipRptCtrl", ssmParam);
    }
    
    /**
     * get Invoice Print Control Data For Print Mass Request
     * @param rptId String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvPrtCtrlForPrintMassRequest(NWCL0110CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("invPrtBatTpCd",   cMsg.invPrtBatTpCd.getValue());
        ssmParam.put("invPrtBrCd",      cMsg.invPrtBrCd.getValue());
        ssmParam.put("conslBillNum_FR", cMsg.conslBillNum_FR.getValue());
        ssmParam.put("conslBillNum_TO", cMsg.conslBillNum_TO.getValue());
        ssmParam.put("invNum_FR",       cMsg.invNum_FR.getValue());
        ssmParam.put("invNum_TO",       cMsg.invNum_TO.getValue());
        ssmParam.put("xxInvNum_FR",     cMsg.xxInvNum_FR.getValue());
        ssmParam.put("xxInvNum_TO",     cMsg.xxInvNum_TO.getValue());
        ssmParam.put("procDt_FR",       cMsg.procDt_FR.getValue());
        ssmParam.put("procDt_TO",       cMsg.procDt_TO.getValue());
        ssmParam.put("invPrtRqstSq_FR", cMsg.invPrtRqstSq_FR.getValue());
        ssmParam.put("invPrtRqstSq_TO", cMsg.invPrtRqstSq_TO.getValue());
        ssmParam.put("cupsPrintRqstId_FR", cMsg.cupsPrintRqstId_FR.getValue());
        ssmParam.put("cupsPrintRqstId_TO", cMsg.cupsPrintRqstId_TO.getValue());
        
        if (NWCL0110Constant.CONST_OTPT_TP_PRT.equals(cMsg.xxTpCd.getValue())
         || NWCL0110Constant.CONST_OTPT_TP_ALL.equals(cMsg.xxTpCd.getValue())) {
            ssmParam.put("prtFlg", ZYPConstant.FLG_ON_Y);
        }
        if (NWCL0110Constant.CONST_OTPT_TP_EML.equals(cMsg.xxTpCd.getValue())
         || NWCL0110Constant.CONST_OTPT_TP_ALL.equals(cMsg.xxTpCd.getValue())) {
            ssmParam.put("emlFlg", ZYPConstant.FLG_ON_Y);
        }
        if (NWCL0110Constant.CONST_OTPT_TP_PDF.equals(cMsg.xxTpCd.getValue())
         || NWCL0110Constant.CONST_OTPT_TP_ALL.equals(cMsg.xxTpCd.getValue())) {
            ssmParam.put("pdfFlg", ZYPConstant.FLG_ON_Y);
        }
        if (NWCL0110Constant.CONST_OTPT_TP_EDI.equals(cMsg.xxTpCd.getValue())
         || NWCL0110Constant.CONST_OTPT_TP_ALL.equals(cMsg.xxTpCd.getValue())) {
            ssmParam.put("ediFlg", ZYPConstant.FLG_ON_Y);
        }
        if (NWCL0110Constant.CONST_OTPT_TP_SB.equals(cMsg.xxTpCd.getValue())
         || NWCL0110Constant.CONST_OTPT_TP_ALL.equals(cMsg.xxTpCd.getValue())) {
            ssmParam.put("sbFlg", ZYPConstant.FLG_ON_Y);
        }

        return getSsmEZDClient().queryObjectList("getInvPrtCtrlForPrintMassRequest", ssmParam);
    }
}
