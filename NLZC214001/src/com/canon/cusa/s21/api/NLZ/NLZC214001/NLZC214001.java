/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC214001;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.SHPG_ORDTMsg;
import business.parts.NLZC214001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;

/**
 * <pre>
 * SO Report API
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/25/2009   Fujitsu         M.Irisawa       Create          N/A
 * 11/25/2009   Fujitsu         M.Irisawa       Update          RQ0649
 * 07/15/2010   CSAI            D.Fukaya        Update          
 * 2014/05/21   Fujitsu         M.Yamada        Update          CSA#38
 *</pre>
 */
public class NLZC214001 extends S21ApiCommonBase {

    /** EXT */
    public static final String EXT = ".pdf";

    /** REPORT_ID */
    private static final String REPORT_ID = "NLBF0010"; // 2014/06/06 Update CSA#38

    //    /** REPORT_BRA_NO */
    //    private static final String REPORT_BRA_NO = "00";         2014/05/21 Del CSA#38

    /** REPORT_TITLE */
    private static final String REPORT_TITLE = "S/O Report";

    //    /** BIZ_APP_ID */
    //    private static final String BIZ_APP_ID = "BIZ_APP_ID";    2014/05/21 Del CSA#38

    /** GLBL_CMPY_CD */
    private static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** USER_ID */
    private static final String USER_ID = "USR_ID"; // 2014/05/21 Update CSA#38

    /** PROC_START_TS */
    private static final String PROC_START_TS = "PROC_START_TS";

    // 2014/05/21 Add CSA#38 Start
    /** INTL_LANG_VAL_COL_NM */
    private static final String INTL_LANG_VAL_COL_NM = "INTL_LANG_VAL_COL_NM";

    // 2014/05/21 Add CSA#38 End

    /** NLZM2001E */
    private static final String NLZM2001E = "NLZM2001E";

    /** NLZM1041E */
    private static final String NLZM1041E = "NLZM1041E";

    /** NLZM1043E */
    private static final String NLZM1043E = "NLZM1043E";

    // 07/15/2010 D.Fukaya append start
    /** ZZZM9004E */
    private static final String ZZZM9004E = "ZZZM9004E";

    // =================================================
    // DB access parts return code
    // =================================================
    /** DB_ACCESS_PARTS_RETURN_CODE_NORMAL */
    private static final String DB_ACCESS_PARTS_RETURN_CODE_NORMAL = "0000";

    // 07/15/2010 D.Fukaya append end

    /**
     * <pre>
     * </pre>
     */
    public NLZC214001() {
        super();
    }

    /**
     * <pre>
     * execute.
     * </pre>
     * @param param NLZC214001PMsg
     * @param onbatchType ONBATCH_TYPE
     */
    public void execute(final NLZC214001PMsg param, final ONBATCH_TYPE onbatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        getSoReport(msgMap);

        msgMap.flush();

    }

    /**
     * @param msgMap
     */
    private void getSoReport(S21ApiMessageMap msgMap) {

        NLZC214001PMsg pMsg = (NLZC214001PMsg) msgMap.getPmsg();

        // 2014/05/21 M.Yamada CSA#38 Mod Start
        if (!checkParam(msgMap)) {
            return;
        }

        S21EIPPrintingService service = new S21EIPPrintingService();

        S21ReportRequestBean request = null;
        S21InputParameter inputParam = null;

        request = new S21ReportRequestBean(REPORT_ID);
        request.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
        request.setRptArcFlg(false);
        request.setRptTtlNm(REPORT_TITLE);

        inputParam = request.getInputParamBeanInstance();

        inputParam.addReportParameter(GLBL_CMPY_CD, pMsg.glblCmpyCd.getValue());
        inputParam.addReportParameter(USER_ID, pMsg.usrId.getValue());
        inputParam.addReportParameter(PROC_START_TS, pMsg.procStartTs.getValue());
        inputParam.addReportParameter(INTL_LANG_VAL_COL_NM, inputParam.getSystemDefaultLanguage());

        request.setInputParamBean(inputParam);

        //XXX if local test then comment out start
        byte[] pdf = service.onlineReport(request);

        if (pdf == null) {
            msgMap.addXxMsgId(NLZM1041E);
            return;
        }

        ZYPFileWriter.writeFile(pMsg.xxTempFilePathTxt.getValue(), pdf);
        //XXX if local test then comment out end

        // S21BOPrintingService boBasic = new S21BOPrintingService();
        // try {
        // if (!checkParam(msgMap)) {
        // return;
        // }
        // boBasic.open();
        // S21ReportParameter repoParam = new S21ReportParameter();
        // repoParam.setReportId(REPORT_ID);
        // repoParam.setReportBranchNo(REPORT_BRA_NO);
        // repoParam.setReportTitle(REPORT_TITLE);
        //
        // S21InputParameter param = boBasic.getInputParameter();
        // param.addReportParameter(GLBL_CMPY_CD,
        // pMsg.glblCmpyCd.getValue());
        // param.addReportParameter(USR_ID, pMsg.usrId.getValue());
        // param.addReportParameter(PROC_START_TS,
        // pMsg.procStartTs.getValue());
        //
        // byte[] data = boBasic.onlineReport(param, repoParam, null);
        // if (data == null) {
        // msgMap.addXxMsgId(NLZM1041E);
        // return;
        // }
        //
        // ZYPFileWriter.writeFile(pMsg.xxTempFilePathTxt.getValue(),
        // data);
        //

        // 07/15/2010 D.Fukaya append start
        if (!updateSoPrintFlg(msgMap)) {
            return;
        }

        // // 07/15/2010 D.Fukaya append end
        // } finally {
        // boBasic.close();
        // }
        // 2014/05/21 M.Yamada CSA#38 Mod End
    }

    /**
     * @param msgMap S21ApiMessageMap
     */
    private boolean checkParam(S21ApiMessageMap msgMap) {
        NLZC214001PMsg pMsg = (NLZC214001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd.getValue())) {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLZM2001E), this);
            msgMap.addXxMsgId(NLZM2001E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.usrId.getValue())) {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLZM2001E), this);
            msgMap.addXxMsgId(NLZM2001E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.procStartTs.getValue())) {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLZM2001E), this);
            msgMap.addXxMsgId(NLZM2001E);
            return false;
        }

        if (pMsg.soNumList.getValidCount() <= 0) {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLZM2001E), this);
            msgMap.addXxMsgId(NLZM2001E);
            return false;
        }

        String tmpFilePath = pMsg.xxTempFilePathTxt.getValue();
        if (ZYPCommonFunc.hasValue(tmpFilePath)) {
            if (!tmpFilePath.endsWith(EXT)) {
                EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLZM1043E), this);
                msgMap.addXxMsgId(NLZM1043E);
                return false;
            }
        } else {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLZM2001E), this);
            msgMap.addXxMsgId(NLZM2001E);
            return false;
        }
        return true;
    }

    // 07/15/2010 D.Fukaya append start
    private boolean updateSoPrintFlg(S21ApiMessageMap msgMap) {

        NLZC214001PMsg pMsg = (NLZC214001PMsg) msgMap.getPmsg();

        SHPG_ORDTMsg tmsg;
        for (int i = 0; i < pMsg.soNumList.getValidCount(); i++) {

            tmsg = new SHPG_ORDTMsg();
            tmsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
            tmsg.soNum.setValue(pMsg.soNumList.no(i).soNum.getValue());
            tmsg = (SHPG_ORDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tmsg);
            if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(tmsg.getReturnCode())) {
                msgMap.addXxMsgId(ZZZM9004E);
                return false;
            }

            tmsg.soPrtFlg.setValue(ZYPConstant.FLG_ON_Y);
            EZDTBLAccessor.update(tmsg);
            if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(tmsg.getReturnCode())) {
                msgMap.addXxMsgId(ZZZM9004E);
                return false;
            }
        }
        return true;
    }
    // 07/15/2010 D.Fukaya append end
}
