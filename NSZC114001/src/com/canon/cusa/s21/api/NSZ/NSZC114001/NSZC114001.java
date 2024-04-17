/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC114001;

import static com.canon.cusa.s21.api.NSZ.NSZC114001.constant.NSZC114001Constant.CALL_PRC_CANON_E193_CREATE_MYCSA_TICKET;
import static com.canon.cusa.s21.api.NSZ.NSZC114001.constant.NSZC114001Constant.NSZM0001E;
import static com.canon.cusa.s21.api.NSZ.NSZC114001.constant.NSZC114001Constant.NSZM1110E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleTypes;
import parts.common.EZDPItem;
import parts.dbcommon.EZDConnectionMgr;
import business.parts.NSZC114001PMsg;

import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;

/**
 * <pre>
 * Create MyCSA Ticket API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/02/09   Hitachi         A.Kohinata      Create          QC#16993
 * 2017/04/12   Hitachi         K.Kitachi       Update          QC#18008
 * 2017/07/13   Hitachi         U.Kim           Update          QC#19817
 * 2017/07/19   Hitachi         U.Kim           Update          QC#19817
 *</pre>
 */
public class NSZC114001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
 // START 2017/07/19 U.Kim [QC#19817, DEL]
//    private S21SsmBatchClient ssmBatchClient = null;
 // END 2017/07/19 U.Kim [QC#19817, DEL]

    /**
     * Constructor
     */
    public NSZC114001() {
        super();
     // START 2017/07/19 U.Kim [QC#19817, DEL]
//       ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
     // END 2017/07/19 U.Kim [QC#19817, DEL]
    }

    /**
     * execute
     * @param pMsgList List<NSZC114001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NSZC114001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {
        for (NSZC114001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType);
        }
    }

    /**
     * execute Create MyCSA Ticket API.
     * @param param NSZC114001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC114001PMsg param, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        if (checkParameter(msgMap)) {
            createTicket(msgMap);
        }

        msgMap.flush();
    }

    private boolean checkParameter(S21ApiMessageMap msgMap) {
        NSZC114001PMsg pMsg = (NSZC114001PMsg) msgMap.getPmsg();

        mandatoryCheck(msgMap, pMsg.glblCmpyCd, NSZM0001E);
        // START 2017/04/12 K.Kitachi [QC#18008, DEL]
//        mandatoryCheck(msgMap, pMsg.xxCustCatgId_H, NSZM1102E);
//        mandatoryCheck(msgMap, pMsg.invNum, NSZM1103E);
//        mandatoryCheck(msgMap, pMsg.xxCustPsnNm, NSZM1104E);
//        mandatoryCheck(msgMap, pMsg.workTelNum, NSZM1105E);
//        mandatoryCheck(msgMap, pMsg.emlAddr, NSZM1106E);
//        mandatoryCheck(msgMap, pMsg.cratByUsrId_H, NSZM1107E);
//        mandatoryCheck(msgMap, pMsg.xxCustCatgId_L, NSZM1108E);
        // END 2017/04/12 K.Kitachi [QC#18008, DEL]

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    private void mandatoryCheck(S21ApiMessageMap msgMap, EZDPItem targetItem, String messageId) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgId(messageId);
        }
    }

    private void createTicket(S21ApiMessageMap msgMap) {
        NSZC114001PMsg pMsg = (NSZC114001PMsg) msgMap.getPmsg();
        // START 2017/07/19 U.Kim [QC#19817, DEL]
//        Map<String, Object> invInfo = getInvInfo(pMsg);
//        // START 2017/04/12 K.Kitachi [QC#18008, MOD]
//        String sellToCustCd = null;
//        String sellToLocNm = null;
//        String dsContrNum = null;
//        String svcContrBrDescTxt = null;
//        if (invInfo != null && !invInfo.isEmpty()) {
//            sellToCustCd = (String) invInfo.get("SELL_TO_CUST_CD");
//            sellToLocNm = (String) invInfo.get("SELL_TO_LOC_NM");
//            dsContrNum = (String) invInfo.get("DS_CONTR_NUM");
//            svcContrBrDescTxt = (String) invInfo.get("SVC_CONTR_BR_DESC_TXT");
//        }
//        // END 2017/04/12 K.Kitachi [QC#18008, MOD]
//        BigDecimal sellToCustCdBD = null;
//        if (hasValue(sellToCustCd)) {
//            sellToCustCdBD = new BigDecimal(sellToCustCd);
//        }
//        String poReqFlg = getPoReqFlg(pMsg, sellToCustCd);
        // END 2017/07/19 U.Kim [QC#19817, DEL]
        CallableStatement callStmt = null;
        OracleConnection oraConnection = null;

        try {
            oraConnection = (OracleConnection) EZDConnectionMgr.getInstance().getConnection().getMetaData().getConnection();
            callStmt = oraConnection.prepareCall(CALL_PRC_CANON_E193_CREATE_MYCSA_TICKET);
            
            // Bind Values to the IN parameter
         // START 2017/07/19 U.Kim [QC#19817, MOD]
            //callStmt.setBigDecimal(1, pMsg.xxCustCatgId_H.getValue());
//          // START 2017/07/13 U.Kim [QC#19817, MOD]
////        callStmt.setString(2, getValue(pMsg.xxCustIncdtStsCd_H.getValue(), CANON_E193_HDR_TICKET_STATUS));
////        callStmt.setString(3, getValue(pMsg.xxCustRecurFlg.getValue(), ZYPConstant.FLG_OFF_N));
////        callStmt.setString(4, getValue(pMsg.bllgFlg.getValue(), ZYPConstant.FLG_ON_Y));
////        callStmt.setBigDecimal(5, getValue(pMsg.xxCustOrgId.getValue(), CANON_E193_HDR_ORG_ID));
////        callStmt.setBigDecimal(6, getValue(pMsg.xxCustAcctId.getValue(), sellToCustCdBD));
////        callStmt.setString(7, getValue(pMsg.sellToLocNm.getValue(), sellToLocNm));
////        callStmt.setString(8, getValue(pMsg.sellToCustCd.getValue(), sellToCustCd));
//          callStmt.setString(2, pMsg.xxCustIncdtStsCd_H.getValue());
//          callStmt.setString(3, pMsg.xxCustRecurFlg.getValue());
//          callStmt.setString(4, pMsg.bllgFlg.getValue());
//          callStmt.setBigDecimal(5, pMsg.xxCustOrgId.getValue());
//          callStmt.setBigDecimal(6, pMsg.xxCustAcctId.getValue());
//          callStmt.setString(7, pMsg.sellToLocNm.getValue());
//          callStmt.setString(8, pMsg.sellToCustCd.getValue());
//          // END 2017/07/13 U.Kim [QC#19817, MOD]
//          callStmt.setString(9, pMsg.invNum.getValue());
//          // START 2017/07/13 U.Kim [QC#19817, MOD]
////        callStmt.setString(10, getValue(pMsg.dsContrNum.getValue(), dsContrNum));
////        callStmt.setString(11, getValue(pMsg.dsContrNum_HM.getValue(), dsContrNum));
//          callStmt.setString(10, pMsg.dsContrNum.getValue());
//          callStmt.setString(11, pMsg.dsContrNum_HM.getValue());
//          // END 2017/07/13 U.Kim [QC#19817, MOD]
//          callStmt.setString(12, null);
//          callStmt.setString(13, null);
//          callStmt.setString(14, pMsg.xxCustPsnNm.getValue());
//          callStmt.setString(15, pMsg.workTelNum.getValue());
//          callStmt.setString(16, pMsg.emlAddr.getValue());
//          // START 2017/07/13 U.Kim [QC#19817, MOD]
////        callStmt.setString(17, getValue(pMsg.xxCustTpCd.getValue(), CANON_E193_HDR_ORIG_TYPE));
////        callStmt.setString(18, getValue(pMsg.xxCustCtacPsnNm.getValue(), pMsg.xxCustPsnNm.getValue()));
////        callStmt.setString(19, getValue(pMsg.ctacPsnTelNum.getValue(), pMsg.workTelNum.getValue()));
////        callStmt.setString(20, getValue(pMsg.ctacPsnEmlAddr.getValue(), pMsg.emlAddr.getValue()));
////        callStmt.setString(21, ZYPConstant.FLG_ON_Y);
////        callStmt.setString(22, getValue(pMsg.xxCustAttrbTxt_H1.getValue(), svcContrBrDescTxt));
////        callStmt.setString(23, getValue(pMsg.xxCustAttrbTxt_H2.getValue(), poReqFlg));
//          callStmt.setString(17, pMsg.xxCustTpCd.getValue());
//          callStmt.setString(18, pMsg.xxCustCtacPsnNm.getValue());
//          callStmt.setString(19, pMsg.ctacPsnTelNum.getValue());
//          callStmt.setString(20, pMsg.ctacPsnEmlAddr.getValue());
//          callStmt.setString(21, null);
//          callStmt.setString(22, pMsg.xxCustAttrbTxt_H1.getValue());
//          callStmt.setString(23, pMsg.xxCustAttrbTxt_H2.getValue());
//          // END 2017/07/13 U.Kim [QC#19817, MOD]
//          callStmt.setString(24, null);
//          callStmt.setString(25, null);
//          callStmt.setString(26, null);
//          // START 2017/07/13 U.Kim [QC#19817, MOD]
////        callStmt.setString(27, CANON_E193_HDR_REGION);
//          callStmt.setString(27, null);
//          // END 2017/07/13 U.Kim [QC#19817, MOD]
//          callStmt.setString(28, null);
//          callStmt.setString(29, null);
//          callStmt.setString(30, null);
//          callStmt.setString(31, null);
//          callStmt.setString(32, null);
//          callStmt.setString(33, null);
//          callStmt.setString(34, null);
//          callStmt.setString(35, null);
//          callStmt.setString(36, null);
//          callStmt.setString(37, null);
//          callStmt.setString(38, null);
//          callStmt.setString(39, null);
//          callStmt.setString(40, pMsg.cratByUsrId_H.getValue());
//          callStmt.setString(41, null);
//          // START 2017/07/13 U.Kim [QC#19817, MOD]
////        callStmt.setString(42, getValue(pMsg.cratByUsrId_HR.getValue(), pMsg.cratByUsrId_H.getValue()));
//          callStmt.setString(42, pMsg.cratByUsrId_HR.getValue());
//          // END 2017/07/13 U.Kim [QC#19817, MOD]
//          callStmt.setString(43, null);
//          callStmt.setBigDecimal(44, pMsg.xxCustCatgId_L.getValue());
//          // START 2017/07/13 U.Kim [QC#19817, MOD]
////        callStmt.setString(45, getValue(pMsg.xxCustIncdtStsCd_L.getValue(), CANON_E193_LINE_STATUS));
////        callStmt.setString(46, getValue(pMsg.xxCustSevtyCd.getValue(), CANON_E193_LINE_SEVERITY));
////        callStmt.setString(47, getValue(pMsg.xxCustRsnCd.getValue(), CANON_E193_LINE_REASON_CODE));
////        callStmt.setString(48, getValue(pMsg.xxCustRsnTxt.getValue(), CANON_E193_LINE_REASON));
////        callStmt.setString(49, ZYPConstant.FLG_ON_Y);
//          callStmt.setString(45, pMsg.xxCustIncdtStsCd_L.getValue());
//          callStmt.setString(46, pMsg.xxCustSevtyCd.getValue());
//          callStmt.setString(47, pMsg.xxCustRsnCd.getValue());
//          callStmt.setString(48, pMsg.xxCustRsnTxt.getValue());
//          callStmt.setString(49, null);
//          // END 2017/07/13 U.Kim [QC#19817, MOD]
//          callStmt.setString(50, null);
//          callStmt.setString(51, null);
//          callStmt.setString(52, null);
//          callStmt.setString(53, null);
//          callStmt.setString(54, null);
//          // START 2017/07/13 U.Kim [QC#19817, MOD]
////        callStmt.setString(55, getValue(pMsg.cratByUsrId_L.getValue(), pMsg.cratByUsrId_H.getValue()));
////        callStmt.setString(56, getValue(pMsg.svcCmntTxt.getValue(), CANON_E193_NOTES_DETAIL));
////        callStmt.setString(57, getValue(pMsg.xxCustSendEmlFlg.getValue(), ZYPConstant.FLG_OFF_))N;
//          callStmt.setString(55, pMsg.cratByUsrId_L.getValue());
//          callStmt.setString(56, pMsg.svcCmntTxt.getValue());
//          callStmt.setString(57, pMsg.xxCustSendEmlFlg.getValue());
//          // END 2017/07/13 U.Kim [QC#19817, MOD]
            
            callStmt.setBigDecimal(1, pMsg.xxCustCatgId_H.getValue());
            callStmt.setString(2, pMsg.xxCustIncdtStsCd_H.getValue());
            callStmt.setString(3, pMsg.xxCustRecurFlg.getValue());
            callStmt.setString(4, pMsg.bllgFlg.getValue());
            callStmt.setBigDecimal(5, pMsg.xxCustOrgId.getValue());
            callStmt.setBigDecimal(6, pMsg.xxCustAcctId.getValue());
            callStmt.setString(7, pMsg.sellToLocNm.getValue());
            callStmt.setString(8, pMsg.sellToCustCd.getValue());
            callStmt.setString(9, pMsg.invNum.getValue());
            callStmt.setString(10, pMsg.dsContrNum.getValue());
            callStmt.setString(11, pMsg.dsContrNum_HM.getValue());
            callStmt.setString(12, convertString(pMsg.xxCustOrdNum.getValue()));
            callStmt.setString(13, pMsg.ordTpDescTxt.getValue());
            callStmt.setString(14, pMsg.xxCustPsnNm.getValue());
            callStmt.setString(15, pMsg.workTelNum.getValue());
            callStmt.setString(16, pMsg.emlAddr.getValue());
            callStmt.setString(17, pMsg.xxCustTpCd.getValue());
            callStmt.setString(18, pMsg.xxCustCtacPsnNm.getValue());
            callStmt.setString(19, pMsg.ctacPsnTelNum.getValue());
            callStmt.setString(20, pMsg.ctacPsnEmlAddr.getValue());
            callStmt.setString(21, pMsg.svcMemoFlg_H.getValue());
            callStmt.setString(22, pMsg.xxCustAttrbTxt_H1.getValue());
            callStmt.setString(23, pMsg.xxCustAttrbTxt_H2.getValue());
            callStmt.setString(24, pMsg.xxCustAttrbTxt_H3.getValue());
            callStmt.setString(25, pMsg.xxCustAttrbTxt_H4.getValue());
            callStmt.setString(26, pMsg.xxCustAttrbTxt_H5.getValue());
            callStmt.setString(27, pMsg.xxCustAttrbTxt_H6.getValue());
            callStmt.setString(28, pMsg.xxCustAttrbTxt_H7.getValue());
            callStmt.setString(29, pMsg.xxCustAttrbTxt_H8.getValue());
            callStmt.setString(30, pMsg.xxCustAttrbTxt_H9.getValue());
            callStmt.setString(31, pMsg.xxCustAttrbTxt_HA.getValue());
            callStmt.setString(32, pMsg.xxCustAttrbTxt_HB.getValue());
            callStmt.setString(33, pMsg.xxCustAttrbTxt_HC.getValue());
            callStmt.setString(34, pMsg.xxCustAttrbTxt_HD.getValue());
            callStmt.setString(35, pMsg.xxCustAttrbTxt_HE.getValue());
            callStmt.setString(36, pMsg.xxCustAttrbTxt_HF.getValue());
            callStmt.setString(37, pMsg.xxCustOwnrRoleId.getValue());
            callStmt.setString(38, pMsg.xxCustOwnrUsrId.getValue());
            callStmt.setString(39, pMsg.xxCustOwnrDeptCd.getValue());
            callStmt.setString(40, pMsg.cratByUsrId_H.getValue());
            callStmt.setString(41, pMsg.xxCustCratByRoleId.getValue());
            callStmt.setString(42, pMsg.cratByUsrId_HR.getValue());
            callStmt.setString(43, pMsg.xxCustCratByDeptCd.getValue());
            callStmt.setBigDecimal(44, pMsg.xxCustCatgId_L.getValue());
            callStmt.setString(45, pMsg.xxCustIncdtStsCd_L.getValue());
            callStmt.setString(46, pMsg.xxCustSevtyCd.getValue());
            callStmt.setString(47, pMsg.xxCustRsnCd.getValue());
            callStmt.setString(48, pMsg.xxCustRsnTxt.getValue());
            callStmt.setString(49, pMsg.svcMemoFlg_L.getValue());
            callStmt.setString(50, pMsg.xxCustAttrbTxt_L1.getValue());
            callStmt.setString(51, pMsg.xxCustAttrbTxt_L2.getValue());
            callStmt.setString(52, pMsg.xxCustAttrbTxt_L3.getValue());
            callStmt.setString(53, pMsg.xxCustAttrbTxt_L4.getValue());
            callStmt.setString(54, pMsg.xxCustAttrbTxt_L5.getValue());
            callStmt.setString(55, pMsg.cratByUsrId_L.getValue());
            callStmt.setString(56, pMsg.svcCmntTxt.getValue());
            callStmt.setString(57, pMsg.xxCustSendEmlFlg.getValue());
            
            // END 2017/07/19 U.Kim [QC#19817, MOD]

            // Register OUT parameter
            callStmt.registerOutParameter(58, OracleTypes.NUMBER);
            callStmt.registerOutParameter(59, OracleTypes.NUMBER);
            callStmt.registerOutParameter(60, OracleTypes.NUMBER);

            // Execute the CallableStatement
            callStmt.execute();
            
            // Retrieve the value from the OUT parameter
            if (hasError(callStmt)) {
                msgMap.addXxMsgId(NSZM1110E);
            } else {
                if (hasValue(callStmt.getBigDecimal(58))) {
                    setValue(pMsg.custIncdtId, callStmt.getBigDecimal(58).toString());
                }
            }

        } catch (Exception ex) {
            msgMap.addXxMsgId(NSZM1110E);
            ex.printStackTrace();
        } finally {
            // Mandatory, Close CallableStatement object
            if (callStmt != null) {
                try {
                    callStmt.close();
                } catch (SQLException e) {
                    msgMap.addXxMsgId(NSZM1110E);
                    e.printStackTrace();
                }
            }
        }
    }

// START 2017/07/19 U.Kim [QC#19817, DEL]
//    private Map<String, Object> getInvInfo(NSZC114001PMsg pMsg) {
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//        param.put("invNum", pMsg.invNum.getValue());
//        return (Map<String, Object>) ssmBatchClient.queryObject("getInvInfo", param);
//    }
//
//    private String getPoReqFlg(NSZC114001PMsg pMsg, String sellToCustCd) {
//        String poReqFlg = ZYPConstant.FLG_OFF_N;
//        if (!hasValue(sellToCustCd)) {
//            return poReqFlg;
//        }
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//        param.put("dsAcctNum", sellToCustCd);
//        param.put("dsTrxRuleTpContract", DS_TRX_RULE_TP.CONTRACT);
//        param.put("rgtnStsP20", RGTN_STS.READY_FOR_ORDER_TAKING);
//        BigDecimal count = (BigDecimal) ssmBatchClient.queryObject("countPoReq", param);
//
//        if (BigDecimal.ZERO.compareTo(count) < 0) {
//            poReqFlg = ZYPConstant.FLG_ON_Y;
//        }
//        return poReqFlg;
//    }
//    
//    private String getValue(String val1, String val2) {
//        if (hasValue(val1)) {
//            return val1;
//        }
//        return val2;
//    }
//
//    private BigDecimal getValue(BigDecimal val1, BigDecimal val2) {
//        if (hasValue(val1)) {
//            return val1;
//        }
//        return val2;
//    }
// END 2017/07/19 U.Kim [QC#19817, DEL]
    
    private static boolean hasError(CallableStatement callStmt) throws SQLException {
        int insCntHC = callStmt.getInt(59);
        int insCntLC = callStmt.getInt(60);
        if (insCntHC < 0 || insCntLC < 0) {
            return true;
        }
        return false;
    }
    
    // START 2017/07/19 U.Kim [QC#19817, ADD]
    private static String convertString(BigDecimal val) {
        if (val == null) {
            return null;
        }
        return String.valueOf(val);
    }
    // END 2017/07/19 U.Kim [QC#19817, ADD]
    
}
