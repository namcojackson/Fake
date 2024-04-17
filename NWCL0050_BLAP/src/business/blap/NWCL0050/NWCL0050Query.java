/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0050;

import static business.blap.NWCL0050.constant.NWCL0050Constant.BASE;
import static business.blap.NWCL0050.constant.NWCL0050Constant.CSV_MAX_ROW;
import static business.blap.NWCL0050.constant.NWCL0050Constant.FETCH_SIZE_MAX;
import static business.blap.NWCL0050.constant.NWCL0050Constant.NO;
import static business.blap.NWCL0050.constant.NWCL0050Constant.PERCENT;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import business.blap.NWCL0050.constant.NWCL0050Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PRT_CTRL_REC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_SMRY_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/26   Fujitsu         T.Yoshida       Create          N/A
 * 2017/03/07   Fujitsu         K.Ishizuka      Update          QC#13856
 * 2019/03/05   Fujitsu         C.Hara          Update          QC#30614
 *</pre>
 */
public final class NWCL0050Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWCL0050Query MY_INSTANCE = new NWCL0050Query();

    /**
     * Private constructor
     */
    private NWCL0050Query() {
        super();
    }

    /**
     * Get the NWCL0050Query instance.
     * @return NWCL0050Query instance
     */
    public static NWCL0050Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * get Invoice Print Info
     * @param cMsg NWCL0050CMsg
     * @param sMsg NWCL0050SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvPrintInfo(NWCL0050CMsg cMsg, NWCL0050SMsg sMsg) {

        String inputSerNum = cMsg.xxSerNumSrchTxt.getValue();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("consoliBill", INV_PRT_CTRL_REC.CONSOLIDATED_BILL);
        ssmParam.put("dateFormat", ZYPDateUtil.getDateFormatString(true) + " HH24:MI");
        ssmParam.put("rowNum", sMsg.A.length() + 2);
        ssmParam.put("isExistSmryWrk", isExistSmryWrkInput(cMsg));
        ssmParam.put("isExistHdrWrk", isExistHdrWrkInput(cMsg));
        ssmParam.put("isExistSvcMachWrk", isExistSvcMachWrkInput(cMsg));
        ssmParam.put("convertSerNum", convertSerNum(inputSerNum));
        ssmParam.put("convertSerNumTxt", convertSerNumTxt(inputSerNum));
        ssmParam.put("CANON_E479_INV_SRCH_TBL", ZYPCodeDataUtil.getVarCharConstValue("CANON_E479_INV_SRCH_TBL", getGlobalCompanyCode()));
        ssmParam.put("zeroDollar", "$0.00");
        ssmParam.put("freight", INV_SMRY_LINE_TP.FREIGHT);
        // QC#53014 2019/09/17 Add Start
        ssmParam.put("ExclBillTxtNo", NO);
        // QC#53014 2019/09/17 Add End

        return getSsmEZDClient().queryEZDMsgArray("getInvPrintInfo", ssmParam, sMsg.A);
    }

    // ADD START S21_NA QC#13856
    /**
     * Get Search Result For DownLoad
     * @param bizMsg NWCL0050CMsg
     * @param glblMsg NWCL0050SMsg
     * @param rsSupport S21SsmBooleanResultSetHandlerSupport
     * @return Boolean
     */
    public Boolean searchForCSV(NWCL0050CMsg cMsg, NWCL0050SMsg sMsg, S21SsmBooleanResultSetHandlerSupport rsSupport) {

        String inputSerNum = cMsg.xxSerNumSrchTxt.getValue();
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("rowNum", CSV_MAX_ROW + 1);
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("consoliBill", INV_PRT_CTRL_REC.CONSOLIDATED_BILL);
        ssmParam.put("dateFormat", ZYPDateUtil.getDateFormatString(true) + " HH24:MI");
        //ssmParam.put("rowNum", sMsg.A.length() + 2); // 2019/03/05 QC#30614 Del
        ssmParam.put("isExistSmryWrk", isExistSmryWrkInput(cMsg));
        ssmParam.put("isExistHdrWrk", isExistHdrWrkInput(cMsg));
        ssmParam.put("isExistSvcMachWrk", isExistSvcMachWrkInput(cMsg));
        ssmParam.put("convertSerNum", convertSerNum(inputSerNum));
        ssmParam.put("convertSerNumTxt", convertSerNumTxt(inputSerNum));
        ssmParam.put("CANON_E479_INV_SRCH_TBL", ZYPCodeDataUtil.getVarCharConstValue("CANON_E479_INV_SRCH_TBL", getGlobalCompanyCode()));
        ssmParam.put("zeroDollar", "$0.00");
        ssmParam.put("freight", INV_SMRY_LINE_TP.FREIGHT);
        
        S21SsmExecutionParameter ssmExecParam = new S21SsmExecutionParameter();
        ssmExecParam.setFetchSize(FETCH_SIZE_MAX);
        ssmExecParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmExecParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmExecParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        // QC#53014 2019/09/17 Add Start
        ssmParam.put("ExclBillTxtNo", NO);
        // QC#53014 2019/09/17 Add End

        S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        return (Boolean) ssmBatchClient.queryObject("getInvPrintInfo", ssmParam, ssmExecParam, rsSupport);

    }
    // ADD END S21_NA QC#13856

    /**
     * F is Exist INV_PRT_SMRY_WRK Input
     * @param cMsg NWCL0050CMsg
     * @return is Exist INV_PRT_SMRY_WRK Input
     */
    private String isExistSmryWrkInput(NWCL0050CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.ordClsCd)) {
            return ZYPConstant.FLG_ON_Y;
        }

        if (ZYPCommonFunc.hasValue(cMsg.invSmryLineTpCd)) {
            return ZYPConstant.FLG_ON_Y;
        }

        return ZYPConstant.FLG_OFF_N;
    }

    /**
     * is Exist INV_PRT_HDR_WRK Input
     * @param cMsg NWCL0050CMsg
     * @return is Exist INV_PRT_HDR_WRK Input
     */
    private String isExistHdrWrkInput(NWCL0050CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.cpoOrdNum)) {
            return ZYPConstant.FLG_ON_Y;
        }

        if (ZYPCommonFunc.hasValue(cMsg.xxSerNumSrchTxt)) {
            return ZYPConstant.FLG_ON_Y;
        }

        if (ZYPCommonFunc.hasValue(cMsg.invAvgGrpNum)) {
            return ZYPConstant.FLG_ON_Y;
        }

        return ZYPConstant.FLG_OFF_N;
    }

    /**
     * is Exist INV_CONTR_SVC_MACH_WRK Input
     * @param cMsg NWCL0050CMsg
     * @return is Exist INV_CONTR_SVC_MACH_WRK Input
     */
    private String isExistSvcMachWrkInput(NWCL0050CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.xxSerNumSrchTxt)) {
            return ZYPConstant.FLG_ON_Y;
        }

        if (ZYPCommonFunc.hasValue(cMsg.dsContrNum)) {
            return ZYPConstant.FLG_ON_Y;
        }

        return ZYPConstant.FLG_OFF_N;
    }

    /**
     * convert Serial Number
     * @param inputSerNum Input Serial Number
     * @return converted Serial Number
     */
    private String convertSerNum(String inputSerNum) {

        if (!ZYPCommonFunc.hasValue(inputSerNum)) {
            return null;
        }

        if (!inputSerNum.startsWith(PERCENT)) {
            inputSerNum = PERCENT + inputSerNum;
        }

        if (!inputSerNum.endsWith(PERCENT)) {
            inputSerNum += PERCENT;
        }

        return inputSerNum;
    }

    /**
     * convert Serial Number Text
     * @param inputSerNum Input Serial Number
     * @return converted Serial Number Text
     */
    private String convertSerNumTxt(String inputSerNum) {

        if (!ZYPCommonFunc.hasValue(inputSerNum)) {
            return null;
        }

        if (!inputSerNum.endsWith(PERCENT)) {
            inputSerNum += PERCENT;
        }

        return BASE + inputSerNum;
    }
}
