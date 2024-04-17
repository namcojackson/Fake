/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0550;

import static business.blap.NSAL0550.constant.NSAL0550Constant.*;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleTypes;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;

import business.db.INVTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.batch.internal.S21BatchTransactionQuery;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/22   Hitachi         Y.Takeno        Create          N/A
 * 2016/08/23   Hitachi         K.Yamada        Update          CSA QC#2148
 * 2016/11/10   Hitachi         K.Ochiai        Update          CSA QC#2148
 * 2017/01/04   Hitachi         A.Kohinata      Update          CSA QC#12497
 * 2017/01/24   Hitachi         A.Kohinata      Update          CSA QC#17030
 * 2017/09/21   CITS            M.Naito         Update          CSA QC#18758
 * 2017/10/12   CITS            M.Naito         Update          CSA QC#21762
 * 2019/06/12   Hitachi         K.Kim           Update          CSA QC#50525
 * 2019/12/06   Hitachi         E.Kameishi      Update          CSA QC#54749
 *</pre>
 */
public class NSAL0550BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {

        NSAL0550CMsg cMsg = (NSAL0550CMsg) arg0;
        NSAL0550SMsg sMsg = (NSAL0550SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            // add start 2017/09/21 CSA Defect#18758
            cMsg.xxSrvUrlTxt_H1.clear();
            // add end 2017/09/21 CSA Defect#18758
            if ("NSAL0550Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(cMsg, sMsg);

            } else if ("NSAL0550Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(cMsg, sMsg);

            } else if ("NSAL0550Scrn00_CreditAndRebill".equals(screenAplID)) {
                doProcess_NSAL0550Scrn00_CreditAndRebill(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    // add start 2016/08/23 CSA Defect#2148
    private void doProcess_NSAL0550Scrn00_CreditAndRebill(NSAL0550CMsg cMsg, NSAL0550SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.xxRowNum_H1)) {
            cMsg.setMessageInfo(NSAM0034E);
            return;
        }
        int rowNum = cMsg.xxRowNum_H1.getValueInt();

        String svcInvNum = cMsg.A.no(rowNum).svcInvNum_A1.getValue();
        Map<String, Object> ctacInfo = getCtacInfo(svcInvNum);

        // add start 2017/10/12 CSA Defect#21762
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", getGlobalCompanyCode());
        param.put("svcInvNum", svcInvNum);
        param.put("svcCrRebilStsCancelled", SVC_CR_REBIL_STS.CANCELLED);
        // START 2019/12/06 E.Kameishi [QC#54749, ADD]
        param.put("creditMemo", INV_TP.CREDIT_MEMO);
        // END 2019/12/06 E.Kameishi [QC#54749, ADD]
        BigDecimal getCreditAndRebillCnt = NSAL0550Query.getInstance().getCreditAndRebillCnt(param);
        if (BigDecimal.ZERO.compareTo(getCreditAndRebillCnt) < 0) {
            cMsg.setMessageInfo(NSAM0708E);
            return;
        }
        // START 2019/12/06 E.Kameishi [QC#54749, ADD]

        INVTMsg inMsg = new INVTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inMsg.invNum, svcInvNum);

        INVTMsg outMsg = (INVTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);
        
        if (outMsg != null && SYS_SRC.S21_ACCOUNTING_AR.equals(outMsg.sysSrcCd.getValue())) {
            cMsg.setMessageInfo(NSAM0756E);
            return;
        }
        // END 2019/12/06 E.Kameishi [QC#54749, ADD]
        // add end 2017/10/12 CSA Defect#21762
        CallableStatement callStmt = null;
        OracleConnection oraConnection = null;
        S21BatchTransactionQuery queryUtil = new S21BatchTransactionQuery();

        Object[] hdrTbl = setParam_CANON_E193_HDR(svcInvNum, ctacInfo);
        // mod start 2017/01/04 CSA Defect#12497
        // MOD START 2016/11/10 CSA QC#2148
        Object[] lineTbl = setParam_CANON_E193_LINE();
        // MOD END 2016/11/10 CSA QC#2148
        // mod end 2017/01/04 CSA Defect#12497
        Object[] subLineTbl = setParam_CANON_E193_SUBLINE();

        try {
            // add start 2017/09/21 CSA Defect#18758
            oraConnection = (OracleConnection) EZDConnectionMgr.getInstance().getConnection().getMetaData().getConnection();
            callStmt = oraConnection.prepareCall(CALL_PRC_CANON_E193_CREAT_BILL_TICKET);

            // Bind Values to the IN parameter
            callStmt.setArray(1, queryUtil.convertSQLTypeArray(ORACLE_STRUCT_CANON_E193_S21_HDR, ORACLE_ARRAY_CANON_E193_S21_HDR, oraConnection, hdrTbl));
            callStmt.setArray(2, queryUtil.convertSQLTypeArray(ORACLE_STRUCT_CANON_E193_S21_LINE, ORACLE_ARRAY_CANON_E193_S21_LINE, oraConnection, lineTbl));
            callStmt.setArray(3, queryUtil.convertSQLTypeArray(ORACLE_STRUCT_CANON_E193_S21_SUBLINE, ORACLE_ARRAY_CANON_E193_S21_SUBLINE, oraConnection, subLineTbl));
            callStmt.setString(4, CANON_E193_NOTES_DETAIL);

            // Register OUT parameter
            callStmt.registerOutParameter(5, OracleTypes.NUMBER);
            callStmt.registerOutParameter(6, OracleTypes.NUMBER);
            callStmt.registerOutParameter(7, OracleTypes.NUMBER);
            callStmt.registerOutParameter(8, OracleTypes.NUMBER);
            callStmt.registerOutParameter(9, OracleTypes.VARCHAR);

            // Execute the CallableStatement
            callStmt.execute();

            // Retrieve the value from the OUT parameter
            if (hasError(callStmt)) {
                String message = callStmt.getString(9);
                //set error message
                cMsg.setMessageInfo(NSAM0603E, new String[] {message});
                return;
            }
            BigDecimal ticketId = callStmt.getBigDecimal(5);
            cMsg.setMessageInfo(NSAM0605I, new String[] {ticketId.toString(), svcInvNum});

            // START 2019/06/12 [QC#50525,MOD]
            // // set URL of Customer Care Ticket History Screen
            // String urlText = ZYPCodeDataUtil.getVarCharConstValue(CUST_CARE_TKT_HIST_URL, getGlobalCompanyCode());
            // if (ZYPCommonFunc.hasValue(urlText)) {
            //     urlText = urlText + ticketId.toString();
            //     cMsg.xxSrvUrlTxt_H1.setValue(urlText);
            // }
            // // add end 2017/09/21 CSA Defect#18758
            cMsg.xxSrvUrlTxt_H1.setValue(ticketId.toString());
            // END 2019/06/12 [QC#50525,MOD]

        } catch (SQLException e) {
            cMsg.setMessageInfo(NSAM0604E);
            e.printStackTrace();
        } catch (Exception ex){
            cMsg.setMessageInfo(NSAM0604E);
            ex.printStackTrace();
        } finally {
            // Mandatory, Close CallableStatement object
            if (callStmt != null) {
                try {
                    callStmt.close();
                } catch (SQLException e) {
                    cMsg.setMessageInfo(NSAM0604E);
                    e.printStackTrace();
                }
            }

        }

    }

    private Map<String, Object> getCtacInfo(String svcInvNum) {
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("glblCmpyCd", getGlobalCompanyCode());
        queryMap.put("svcInvNum", svcInvNum);

        S21SsmEZDResult ssmResult = NSAL0550Query.getInstance().getCtacInfo(queryMap);

        if (ssmResult.isCodeNormal()) {
            return (Map<String, Object>) ssmResult.getResultObject();
        }
        return null;
    }

    private Object[] setParam_CANON_E193_HDR(String svcInvNum, Map<String, Object> ctacInfo) {
        S21UserInfo userInfo = getUserProfileService().getContextUserInfo();

        return new Object[] 
                {
                    ZYPConstant.FLG_ON_Y, //BILLING_ISSUE
                    null, //CUSTOMER_NAME
                    null, //CUSTOMER_NUMBER
                    svcInvNum, //INVOICE_NUMBER
                    null, //CONTRACT_NUMBER
                    null, //ORDER_NUMBER
                    null, //ORDER_TYPE
                    getOrigNm(userInfo), //ORIG_NAME
                    getPhoneNum(userInfo), //ORIG_PH_NUMBER
                    getEmail(userInfo), //ORIG_E_MAIL
                    CANON_E193_HDR_ORIG_TYPE, //ORIG_TYPE
                    // mod start 2017/01/04 CSA Defect#12497
                    // mod start 2017/01/24 CSA Defect#17030
                    getCtacPsnNm(ctacInfo, userInfo), //CUST_CONTACT
                    getCtacPntValTxt(ctacInfo, userInfo), //CUST_PH_NUMBER
                    // mod end 2017/01/24 CSA Defect#17030
                    null, //CUST_E_MAIL
                    null, //REGION
                    CANON_E193_HDR_SOURCE, //SOURCE
                    userInfo.getUserId(), //CREATED_BY
                    // mod start 2017/09/21 CSA Defect#18758
                    ZYPCodeDataUtil.getVarCharConstValue(CANON_E193_HDR_CAT_DESC, getGlobalCompanyCode()) //CAT_DESC
                    // mod end 2017/09/21 CSA Defect#18758
                };
    }

    // mod start 2017/01/04 CSA Defect#12497
    // MOD START 2016/11/10 CSA QC#2148
    private Object[] setParam_CANON_E193_LINE() {
        return new Object[]
                {
                    null, //SEVERITY
                    // mod start 2017/09/21 CSA Defect#18758
                    ZYPCodeDataUtil.getVarCharConstValue(CANON_E193_LINE_REASON_CODE, getGlobalCompanyCode()), //REASON_CODE
                    // mod start 2017/01/04 CSA Defect#12497
                    // MOD START 2016/11/10 CSA QC#2148
                    ZYPCodeDataUtil.getVarCharConstValue(CANON_E193_LINE_REASON, getGlobalCompanyCode()), //REASON
//                    getReason(svcInvNum),
                    // MOD END 2016/11/10 CSA QC#2148
                    // mod end 2017/01/04 CSA Defect#12497
                    null, //CREATED_BY
                    ZYPCodeDataUtil.getVarCharConstValue(CANON_E193_LINE_CAT_DESC, getGlobalCompanyCode()) //CAT_DESC
                    // mod end 2017/09/21 CSA Defect#18758
                };
    }
    // MOD END 2016/11/10 CSA QC#2148
    // mod end 2017/01/04 CSA Defect#12497

    private Object[] setParam_CANON_E193_SUBLINE() {
        return new Object[]
                {};
//                null, //CR_FLAG
//                null, //CREDIT_REASON
//                null, //SERIAL_NUM
//                null, //OBJECT_TYPE
//                null, //OBJECT_VALUE
//                null, //CURRENT_VALUE
//                null, //NEW_VALUE
//                null, //ATTRIBUTE1
//                null, //ATTRIBUTE2
//                null, //ATTRIBUTE3
//                null, //INVOICE_NUMBER
//                null, //INSTANCE_NUMBER
//                null //CAT_DESC
//                };
    }

    // del start 2017/01/04 CSA Defect#12497
    // ADD START 2016/11/10 CSA QC#2148
//    private String getReason(String svcInvNum) {
//        Map<String, String> queryMap = new HashMap<String, String>();
//        queryMap.put("glblCmpyCd", getGlobalCompanyCode());
//        queryMap.put("svcInvNum", svcInvNum);
//        queryMap.put("svcInvChrgTp", SVC_INV_CHRG_TP.BASE_CHARGE);
//        BigDecimal SvcInvChgTpCnt = NSAL0550Query.getInstance().getSvcInvChgTpCnt(queryMap);
//        String reason = new String();
//        if (BigDecimal.ZERO.compareTo(SvcInvChgTpCnt) < 0) {
//            reason = CANON_E193_LINE_REASON_BASE;
//        } else {
//            reason = CANON_E193_LINE_REASON_NO_BASE;
//        }
//        return reason;
//    }
    // ADD START 2016/11/10 CSA QC#2148
    // del end 2017/01/04 CSA Defect#12497

    private String getOrigNm(S21UserInfo userInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append(userInfo.getFirstName());
        sb.append(" ");
        sb.append(userInfo.getLastName());
        return sb.toString();
    }

    private String getPhoneNum(S21UserInfo userInfo) {
        if (ZYPCommonFunc.hasValue(userInfo.getUserDetails().getWorkPhoneNumber())) {
            return userInfo.getUserDetails().getWorkPhoneNumber();
        }
        String phoneNum = userInfo.getUserDetails().getCellNumber();
        if (!ZYPCommonFunc.hasValue(phoneNum)) {
            return phoneNum = NO_PHONE;
        }
        return phoneNum ;
    }

    private String getEmail(S21UserInfo userInfo) {
        if (ZYPCommonFunc.hasValue(userInfo.getEmailAddress())) {
            return userInfo.getEmailAddress();
        }
        return NO_EMAIL ;
    }

    // mod start 2017/01/24 CSA Defect#17030
    private String getCtacPsnNm(Map<String, Object> ctacInfo, S21UserInfo userInfo) {
        if (ctacInfo == null || ctacInfo.isEmpty()) {
            return getOrigNm(userInfo);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(ctacInfo.get("CTAC_PSN_FIRST_NM"));
        sb.append(" ");
        sb.append(ctacInfo.get("CTAC_PSN_LAST_NM"));
        return sb.toString();
    }

    private String getCtacPntValTxt(Map<String, Object> ctacInfo, S21UserInfo userInfo) {
        if (ctacInfo == null || ctacInfo.isEmpty()) {
            return getPhoneNum(userInfo);
        }
        return ctacInfo.get("DS_CTAC_PNT_VAL_TXT").toString();
    }
    // mod end 2017/01/24 CSA Defect#17030

    private boolean hasError(CallableStatement callStmt) throws SQLException {
        int insCntHC = callStmt.getInt(6);
        int insCntLC = callStmt.getInt(7);
        int insCntSLC = callStmt.getInt(8);
        if (insCntHC < 0 || insCntLC < 0 || insCntSLC < 0) {
            return true;
        }
        return false;
    }
    // add end 2016/08/23 CSA Defect#2148
}
