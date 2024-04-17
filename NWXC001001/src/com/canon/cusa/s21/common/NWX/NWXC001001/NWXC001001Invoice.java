/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 * 
 * <pre>
 * Make a worktable to make Invoice book vote.
 * If making is possible, Do Invoice with an amount of 
 * money calculated in Ratio of the input parameter.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/09/30   Fujitsu         R.Watanabe      Create          N/A
 * 2009/10/14   Fujitsu         R.Watanabe      Update          688
 * 2010/01/29   Fujitsu         N.Mitsuishi     Update          3167
 * 2010/03/10   Fujitsu         S.Sugino        Update          4598
 * 2010/04/20   Fujitsu         A.Suda          Update          5505 
 * 2010/05/27   Fujitsu         R.Watanabe      Update          6758 
 * 2010/06/15   Fujitsu         R.Watanabe      Update          7154 
 * 2010/07/15   Fujitsu         R.Watanabe      Update          7834 
 * 2010/08/02   Fujitsu         R.Watanabe      Update          140(All2) 
 * 2010/08/02   Fujitsu         R.Watanabe      Update          141(All2) 
 * 2010/09/09   Fujitsu         R.Watanabe      Update          438(All2)
 * 2010/10/15   Fujitsu         R.Watanabe      Update          505(All2)
 * 2011/02/07   Fujitsu         K.Tajima        Update          1353(Prod)
 * 2011/06/03   CSAI	        A.Katayama      Update          2282(Prod)
 * 2012/10/19   Fujitsu         S.Tsunaki       Update          WDS #118
 * 2013/02/27   Fujitsu         D.Yanagisawa    Update          WDS Defect#183
 *</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.DOWN;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDMsg;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTMsgArray;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CCYTMsg;
import business.db.CPOTMsg;
import business.db.CR_DR_RSNTMsg;
import business.db.CR_DR_SUB_RSNTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.INVTMsg;
import business.db.INVTY_LOC_VTMsg;
import business.db.INVTY_LOC_VTMsgArray;
import business.db.INV_BOLTMsg;
import business.db.INV_BOLTMsgArray;
import business.db.INV_BOL_WRKTMsg;
import business.db.INV_CASH_DISC_TERMTMsg;
import business.db.INV_CASH_DISC_TERMTMsgArray;
import business.db.INV_CASH_DISC_TERM_WRKTMsg;
import business.db.INV_LINE_WRKTMsg;
import business.db.INV_LINE_WRKTMsgArray;
import business.db.INV_PRINT_MSGTMsg;
import business.db.INV_PRMO_INFOTMsg;
import business.db.INV_PRMO_INFOTMsgArray;
import business.db.INV_PRMO_INFO_WRKTMsg;
//import business.db.INV_TMPLTMsg;
import business.db.INV_TPTMsg;
import business.db.INV_WRKTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BILL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CCY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GLBL_CMPY;
//import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TMPL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

public class NWXC001001Invoice {
    
    private static final String NWCM0006E = "NWCM0006E";
    private static final String NWCM0007E = "NWCM0007E";
    private static final String NWCM0019E = "NWCM0019E";
    private static final String NWCM0063E = "NWCM0063E";
    private static final String NWCM0064E = "NWCM0064E";
    private static final String NWCM0065E = "NWCM0065E";
    private static final String NWCM0077E = "NWCM0077E";

    /** Result Message Id */
    private String messageId = "";

    /** Invoice Template Code */
    private String resultInvTmplCd = "";

    private S21LRUMap<String, String> s21LRUMap = new S21LRUMap<String, String>();

    public static final Class clazz = NWXC001001Invoice.class;

    public static final String clazzNm = clazz.getSimpleName();

    public static final S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(clazz);
    
    /** Flg : Y */
    public static final String Y = ZYPConstant.FLG_ON_Y;

    /** Flg : N */
    public static final String N = ZYPConstant.FLG_OFF_N;

    // WDS #118 Add Start
    /**
     * BOL Level Line Number (None)
     */
    String BOL_LVL_LINE_NUM_NONE = "000000";

    /**
     * Legacy Invoice Credit Card Detail Work Line Number (000)
     */
    String LGCY_INV_CC_DTL_WRK_LINE_NUM = "000";
    // WDS #118 Add End

    //02/27/2013 Add START Defect#183
    private static final String SET_HDR_INV_LINE_SUB_NUM = "000";
    //02/27/2013 Add E N D Defect#183

    /**
     * <pre>
     * Make a worktable to make Invoice pdf files.
     * </pre>
     * 
     * @param glblCmpyCd String
     * @param bizAppId String
     * @param userId String
     * @param invTs String
     * @param invNum String
     * @return true/normal. false/abnormality.
     */
    public boolean createInvoicePrintWork(String glblCmpyCd, String bizAppId, String userId, String invTs, String invNum) {

        checkInputParameter(glblCmpyCd, bizAppId, userId, invTs, invNum);

        List<String> invNumList = Arrays.asList(new String[] {invNum });

        return createInvoicePrintWork(glblCmpyCd, bizAppId, userId, invTs, invNumList, null);
    }

    /**
     * <pre>
     * Make a worktable to make Invoice pdf files.
     * </pre>
     * 
     * @param glblCmpyCd String
     * @param bizAppId String
     * @param userId String
     * @param invTs String
     * @param invNum String
     * @param ratio BigDecimal
     * @return true/normal. false/abnormality.
     */
    public boolean createInvoicePrintWork(String glblCmpyCd, String bizAppId, String userId, String invTs, String invNum, BigDecimal ratio) {

        checkInputParameter(glblCmpyCd, bizAppId, userId, invTs, invNum);

        List<String> invNumList = Arrays.asList(new String[] {invNum });

        return createInvoicePrintWork(glblCmpyCd, bizAppId, userId, invTs, invNumList, ratio);
    }

    /**
     * <pre>
     * Make a worktable to make Invoice pdf files.
     * </pre>
     * 
     * @param glblCmpyCd String
     * @param bizAppId String
     * @param userId String
     * @param invTs String
     * @param invNumList List<String>
     * @return true/normal. false/abnormality.
     */
    public boolean createInvoicePrintWork(String glblCmpyCd, String bizAppId, String userId, String invTs, List<String> invNumList) {

        checkInputParameter(glblCmpyCd, bizAppId, userId, invTs, invNumList);

        return createInvoicePrintWork(glblCmpyCd, bizAppId, userId, invTs, invNumList, null);
    }

    /**
     * <pre>
     * Repay message ID.
     * </pre>
     * 
     * @return MessageId
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * <pre>
     * Repay Invoice Template Code.
     * </pre>
     * 
     * @return Invoice Template Code.
     */
    public String getInvTmplCd() {
        return resultInvTmplCd;
    }
    
    /**
     * <pre>
     * Delete a worktable to make Invoice pdf files.
     * </pre>
     * 
     * @param glblCmpyCd String
     * @param bizAppId String
     * @param userId String
     * @param invTs String
     * @param invNum String
     */
    public void removeWrk(String glblCmpyCd, String bizAppId, String userId, String invTs, String invNum) {

        removeInvWrk(glblCmpyCd, bizAppId, userId, invTs, invNum);
        removeInvCashDiscTermWrk(glblCmpyCd, bizAppId, userId, invTs, invNum);
        removeInvBolWrk(glblCmpyCd, bizAppId, userId, invTs, invNum);
        removeInvLineWrk(glblCmpyCd, bizAppId, userId, invTs, invNum);
        removeInvPrmoInfoWrk(glblCmpyCd, bizAppId, userId, invTs, invNum);

    }

    /**
     * <pre>
     * Delete a worktable to make Invoice pdf files.
     * </pre>
     * 
     * @param glblCmpyCd String
     * @param bizAppId String
     * @param userId String
     * @param invTs String
     * @param invNumList List<String>
     */
    public void removeWrk(String glblCmpyCd, String bizAppId, String userId, String invTs, List<String> invNumList) {

        for (String invNum : invNumList) {
            removeWrk(glblCmpyCd, bizAppId, userId, invTs, invNum);
        }
    }

    private void removeInvWrk(String glblCmpyCd, String bizAppId, String userId, String invTs, String invNum) {

        INV_WRKTMsg inMsg = new INV_WRKTMsg();

        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.bizAppId.setValue(bizAppId);
        inMsg.invOtptOpCd.setValue(userId);
        inMsg.invTs.setValue(invTs);
        inMsg.invNum.setValue(invNum);

        EZDTBLAccessor.removeByPartialKey(inMsg);
    }

    private void removeInvCashDiscTermWrk(String glblCmpyCd, String bizAppId, String userId, String invTs, String invNum) {

        INV_CASH_DISC_TERM_WRKTMsg inMsg = new INV_CASH_DISC_TERM_WRKTMsg();

        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.bizAppId.setValue(bizAppId);
        inMsg.invOtptOpCd.setValue(userId);
        inMsg.invTs.setValue(invTs);
        inMsg.invNum.setValue(invNum);

        EZDTBLAccessor.removeByPartialKey(inMsg);
    }

    private void removeInvBolWrk(String glblCmpyCd, String bizAppId, String userId, String invTs, String invNum) {

        INV_BOL_WRKTMsg inMsg = new INV_BOL_WRKTMsg();

        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.bizAppId.setValue(bizAppId);
        inMsg.invOtptOpCd.setValue(userId);
        inMsg.invTs.setValue(invTs);
        inMsg.invNum.setValue(invNum);

        EZDTBLAccessor.removeByPartialKey(inMsg);
    }

    private void removeInvLineWrk(String glblCmpyCd, String bizAppId, String userId, String invTs, String invNum) {

        INV_LINE_WRKTMsg inMsg = new INV_LINE_WRKTMsg();

        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.bizAppId.setValue(bizAppId);
        inMsg.invOtptOpCd.setValue(userId);
        inMsg.invTs.setValue(invTs);
        inMsg.invNum.setValue(invNum);

        EZDTBLAccessor.removeByPartialKey(inMsg);
    }

    private void removeInvPrmoInfoWrk(String glblCmpyCd, String bizAppId, String userId, String invTs, String invNum) {

        INV_PRMO_INFO_WRKTMsg inMsg = new INV_PRMO_INFO_WRKTMsg();

        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.bizAppId.setValue(bizAppId);
        inMsg.invOtptOpCd.setValue(userId);
        inMsg.invTs.setValue(invTs);
        inMsg.invNum.setValue(invNum);
        
        EZDTBLAccessor.removeByPartialKey(inMsg);
    }
    
    private String getInvTmplCd(INVTMsg invMsg) {

        String invTpCd = invMsg.invTpCd.getValue();
        String invTmplCd = "";

//        if (INV_TP.CREDIT_MEMO.equals(invTpCd) || INV_TP.DEBIT_MEMO.equals(invTpCd)) {
//
//            String ctryCd = getCtryCd(invMsg.glblCmpyCd.getValue());
//            if (ctryCd.equals(invMsg.sellToCtryCd.getValue())) {
//                invTmplCd = INV_TMPL.REGULAR;
//            } else {
//            	invTmplCd = setInvTmpl(invMsg);
//                //invTmplCd = INV_TMPL.OPTION_F;
//            }
//
//        } else {
//        	invTmplCd = setInvTmpl(invMsg);
//        }

        return invTmplCd;
    }

    private String getCtryCd(String glblCmpyCd) {

        GLBL_CMPYTMsg glblCmpyTMsg = (GLBL_CMPYTMsg) ZYPCodeDataUtil.findByCode(GLBL_CMPY.class, glblCmpyCd, glblCmpyCd);
        return glblCmpyTMsg.ctryCd.getValue();
    }

    private String getInvPrintMsgCdByInvTp(String glblCmpyCd, String invTpCd) {

        INV_TPTMsg invTpMsg = (INV_TPTMsg) ZYPCodeDataUtil.findByCode(INV_TP.class, glblCmpyCd, invTpCd);
        return invTpMsg.invPrintMsgCd.getValue();
    }
//
//    private String getInvPrintMsgCdByInvTmpl(String glblCmpyCd, String invTmplCd) {
//
//        INV_TMPLTMsg invTmplMsg = (INV_TMPLTMsg) ZYPCodeDataUtil.findByCode(INV_TMPL.class, glblCmpyCd, invTmplCd);
//        return invTmplMsg.invMsgCd.getValue();
//    }

    private boolean isEmpty(EZDTMsgArray msgArray) {

        return msgArray == null || msgArray.getValidCount() == 0;
    }

    private INVTMsg findInv(String glblCmpyCd, String invNum) {

        INVTMsg inMsg = new INVTMsg();

        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.invNum.setValue(invNum);

        return (INVTMsg) S21FastTBLAccessor.findByKey(inMsg);
    }

    private INV_BOLTMsgArray findInvBol(String glblCmpyCd, String invNum) {

        INV_BOLTMsg inMsg = new INV_BOLTMsg();

        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("invNum01", invNum);

        return (INV_BOLTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    // WDS #118 Delete Start
//    private INV_LINETMsgArray findInvLine(String glblCmpyCd, String invNum) {
//
//        INV_LINETMsg inMsg = new INV_LINETMsg();
//
//        inMsg.setSQLID("002");
//        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        inMsg.setConditionValue("invNum01", invNum);
//
//        return (INV_LINETMsgArray) EZDTBLAccessor.findByCondition(inMsg);
//    }
    // WDS #118 Delete End

    // WDS #118 Add Start
    private INV_LINE_WRKTMsgArray findInvLineToInvLineWrk(String glblCmpyCd, String invNum, String bizAppId, String invOtptOpCd, String invTs) {
        //07/15/2013 Add START R-OM028
        Set<String> bomBolFlgSet  = new HashSet<String>();
        Map<String, String> bomShipCpltMap  = new HashMap<String, String>();
        
      //07/15/2013 Add END R-OM028
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("invNum", invNum);
        param.put("mdseTpBOM", MDSE_TP.SALES_BOM);
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("queryInvLine", param);

        if (resultList == null) {
            return null;
        }

        List<INV_LINE_WRKTMsg> wrkList = new ArrayList<INV_LINE_WRKTMsg>();
        Map<String, INV_LINE_WRKTMsg> mapBom = new HashMap<String, INV_LINE_WRKTMsg>();

        for (Map<String, Object> result : resultList) {

            String mdseTpCd = (String) result.get("MDSE_TP_CD");
            String dsOrdPosnNum = (String) result.get("DS_ORD_POSN_NUM");
            //02/27/2013 Add START Defect#183
            String invLineSubNum = (String) result.get("INV_LINE_SUB_NUM");
            //02/27/2013 Add E N D Defect#183

            if (!MDSE_TP.SALES_BOM.equals(mdseTpCd)) {

                if (ZYPCommonFunc.hasValue(dsOrdPosnNum) && mapBom.containsKey(dsOrdPosnNum)) {

                    INV_LINE_WRKTMsg invLineWrkTMsgForBOM = mapBom.get(dsOrdPosnNum);

                    // BOM Item
                    //02/27/2013 Add START Defect#183
                    if (!SET_HDR_INV_LINE_SUB_NUM.equals(invLineSubNum)) {
                        // BOM Item Summary
                        summaryAmount(invLineWrkTMsgForBOM, result);
                        //UPD START R_OM028
                        String cpoOrdNum = (String) result.get("CPO_ORD_NUM");
                        String invtyCtrlFlg = (String) result.get("INVTY_CTRL_FLG");
                        String shipCpltCd = (String) result.get("SHPG_PLN_SHIP_CPLT_CD");
                        
                        if (ZYPCommonFunc.hasValue(dsOrdPosnNum) && ZYPCommonFunc.hasValue(cpoOrdNum) && ZYPCommonFunc.hasValue(invtyCtrlFlg) 
                                && ZYPConstant.FLG_ON_Y.equals(invtyCtrlFlg) && !bomBolFlgSet.contains(dsOrdPosnNum)) {
                            String bomHdrShipCpltCd = bomShipCpltMap.get(dsOrdPosnNum);
                            if (!ZYPCommonFunc.hasValue(shipCpltCd) && !ZYPCommonFunc.hasValue(bomHdrShipCpltCd)
                                    || ZYPCommonFunc.hasValue(shipCpltCd) && shipCpltCd.equals(bomHdrShipCpltCd)) {
                                bomBolFlgSet.add(dsOrdPosnNum);
                                ZYPEZDItemValueSetter.setValue(invLineWrkTMsgForBOM.invBolLineNum, (String) result.get("INV_BOL_LINE_NUM"));
                                ZYPEZDItemValueSetter.setValue(invLineWrkTMsgForBOM.invLineNum, (String) result.get("INV_LINE_NUM"));
                                ZYPEZDItemValueSetter.setValue(invLineWrkTMsgForBOM.invLineSubNum, (String) result.get("INV_LINE_SUB_NUM"));
                                ZYPEZDItemValueSetter.setValue(invLineWrkTMsgForBOM.invLineSubTrxNum, (String) result.get("INV_LINE_SUB_TRX_NUM"));
                            }
                        }
                        //UPD END R_OM028
                    }
                    //02/27/2013 Add E N D Defect#183

                } else {

                    // Other

                    // Insert Other
                    INV_LINE_WRKTMsg invLineWrk = setInvLineWrk(glblCmpyCd, bizAppId, invOtptOpCd, invTs, result, true);
                    wrkList.add(invLineWrk);
                }

            } else {

                // BOM Head

                // Create BOM Header
                INV_LINE_WRKTMsg invLineWrkTMsgForBOM = setInvLineWrk(glblCmpyCd, bizAppId, invOtptOpCd, invTs, result, false);

                mapBom.put(dsOrdPosnNum, invLineWrkTMsgForBOM);
                wrkList.add(invLineWrkTMsgForBOM);
                //07/15/2013 Add START R-OM028
                String shipCpltCd = (String) result.get("SHPG_PLN_SHIP_CPLT_CD");
                bomShipCpltMap.put(dsOrdPosnNum, shipCpltCd);
              //07/15/2013 Add End R-OM028
            }
        }

//        if (!mapBom.isEmpty()) {
//            for (Entry<String, INV_LINE_WRKTMsg> setBom : mapBom.entrySet()) {
//                updateBomBol(setBom.getValue());
//            }
//        }

        INV_LINE_WRKTMsgArray array = new INV_LINE_WRKTMsgArray();
        array.setMsgList((INV_LINE_WRKTMsg[]) wrkList.toArray(new INV_LINE_WRKTMsg[wrkList.size()]));
        array.setValidCount(wrkList.size());

        return array;
    }

    private INV_LINE_WRKTMsg setInvLineWrk(String glblCmpyCd, String bizAppId, String invOtptOpCd, String invTs, Map<String, Object> result, boolean setAmt) {

        INV_LINE_WRKTMsg invLineWrkTMsg = new INV_LINE_WRKTMsg();

        setValue(invLineWrkTMsg.glblCmpyCd, glblCmpyCd);
        setValue(invLineWrkTMsg.invOtptOpCd, invOtptOpCd);
        setValue(invLineWrkTMsg.bizAppId, bizAppId);
        setValue(invLineWrkTMsg.invTs, invTs);
        setValue(invLineWrkTMsg.invNum, (String) result.get("INV_NUM"));
        setValue(invLineWrkTMsg.invBolLineNum, (String) result.get("INV_BOL_LINE_NUM"));
        setValue(invLineWrkTMsg.invLineNum, (String) result.get("INV_LINE_NUM"));
        setValue(invLineWrkTMsg.invLineSubNum, (String) result.get("INV_LINE_SUB_NUM"));
        setValue(invLineWrkTMsg.invLineSubTrxNum, (String) result.get("INV_LINE_SUB_TRX_NUM"));
        setValue(invLineWrkTMsg.stkStsCd, (String) result.get("STK_STS_CD"));
        setValue(invLineWrkTMsg.mdseCd, (String) result.get("MDSE_CD"));
        setValue(invLineWrkTMsg.mdseNm, (String) result.get("MDSE_NM"));
        setValue(invLineWrkTMsg.ordQty, (BigDecimal) result.get("ORD_QTY"));
        setValue(invLineWrkTMsg.shipQty, (BigDecimal) result.get("SHIP_QTY"));
        setValue(invLineWrkTMsg.boQty, (BigDecimal) result.get("BO_QTY"));

        setValue(invLineWrkTMsg.u_CfcTxt, (String) result.get("U_CFC_TXT"));
        setValue(invLineWrkTMsg.mercStmtOnInvTxt, (String) result.get("MERC_STMT_ON_INV_TXT"));
        setValue(invLineWrkTMsg.setRatioKeepFlg, (String) result.get("SET_RATIO_KEEP_FLG"));

        setValue(invLineWrkTMsg.setMdseCd, (String) result.get("SET_MDSE_CD"));

        if (setAmt) {
            setValue(invLineWrkTMsg.dealNetUnitPrcAmt, (BigDecimal) result.get("DEAL_NET_UNIT_PRC_AMT"));
            setValue(invLineWrkTMsg.invLineDealNetAmt, (BigDecimal) result.get("INV_LINE_DEAL_NET_AMT"));
            setValue(invLineWrkTMsg.dealGrsUnitPrcAmt, (BigDecimal) result.get("DEAL_GRS_UNIT_PRC_AMT"));
            setValue(invLineWrkTMsg.dealGrsTotPrcAmt, (BigDecimal) result.get("DEAL_GRS_TOT_PRC_AMT"));
        } else {
            setValue(invLineWrkTMsg.dealNetUnitPrcAmt, BigDecimal.ZERO);
            setValue(invLineWrkTMsg.invLineDealNetAmt, BigDecimal.ZERO);
            setValue(invLineWrkTMsg.dealGrsUnitPrcAmt, BigDecimal.ZERO);
            setValue(invLineWrkTMsg.dealGrsTotPrcAmt, BigDecimal.ZERO);
        }

        return invLineWrkTMsg;
    }

    private void summaryAmount(INV_LINE_WRKTMsg invLineWrkTMsg, Map<String, Object> result) {

        BigDecimal invLineDealNetAmt = addBigDecimal(invLineWrkTMsg.invLineDealNetAmt.getValue(), (BigDecimal) result.get("INV_LINE_DEAL_NET_AMT"));
        BigDecimal dealNetUnitPrcAmt = addBigDecimal(invLineWrkTMsg.dealNetUnitPrcAmt.getValue(), (BigDecimal) result.get("DEAL_NET_UNIT_PRC_AMT"));
        BigDecimal dealGrsUnitPrcAmt = addBigDecimal(invLineWrkTMsg.dealGrsUnitPrcAmt.getValue(), (BigDecimal) result.get("DEAL_GRS_UNIT_PRC_AMT"));
        BigDecimal dealGrsTotPrcAmt = addBigDecimal(invLineWrkTMsg.dealGrsTotPrcAmt.getValue(), (BigDecimal) result.get("DEAL_GRS_TOT_PRC_AMT"));

        setValue(invLineWrkTMsg.invLineDealNetAmt, getValidBigDecimalValue(invLineDealNetAmt));
        setValue(invLineWrkTMsg.dealNetUnitPrcAmt, getValidBigDecimalValue(dealNetUnitPrcAmt));
        setValue(invLineWrkTMsg.dealGrsUnitPrcAmt, getValidBigDecimalValue(dealGrsUnitPrcAmt));
        setValue(invLineWrkTMsg.dealGrsTotPrcAmt, getValidBigDecimalValue(dealGrsTotPrcAmt));
    }

    private BigDecimal addBigDecimal(BigDecimal value1, BigDecimal value2) {

        if (value1 == null) {
            return value2;
        }

        if (value2 == null) {
            return value1;
        }
        return value1.add(value2);
    }

    private BigDecimal getValidBigDecimalValue(BigDecimal value) {

        if (value == null) {
            return BigDecimal.ZERO;
        } else {
            return value;
        }
    }

    private void updateBomBol(INV_LINE_WRKTMsg invLineWrkTMsg) {

        INV_BOL_WRKTMsg invBolWrkTMsg = new INV_BOL_WRKTMsg();
        setValue(invBolWrkTMsg.glblCmpyCd, invLineWrkTMsg.glblCmpyCd);
        setValue(invBolWrkTMsg.invNum, invLineWrkTMsg.invNum);
        setValue(invBolWrkTMsg.invBolLineNum, invLineWrkTMsg.invBolLineNum);
        setValue(invBolWrkTMsg.invTs, invLineWrkTMsg.invTs);
        setValue(invBolWrkTMsg.invOtptOpCd, invLineWrkTMsg.invOtptOpCd);
        setValue(invBolWrkTMsg.bizAppId, invLineWrkTMsg.bizAppId);

        invBolWrkTMsg = (INV_BOL_WRKTMsg) EZDTBLAccessor.findByKey(invBolWrkTMsg);

        if (invBolWrkTMsg != null) {
            setValue(invBolWrkTMsg.shipDealNetAmt, invLineWrkTMsg.invLineDealNetAmt);
            EZDTBLAccessor.update(invBolWrkTMsg);
        }
    }

    private INV_PRINT_MSGTMsg findInvPrintMsg(String glblCmpyCd, String invPrintMsgCd) {

        INV_PRINT_MSGTMsg inMsg = new INV_PRINT_MSGTMsg();

        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.invPrintMsgCd.setValue(invPrintMsgCd);

        // return (INV_PRINT_MSGTMsg) EZDTBLAccessor.findByKey(inMsg);
        return (INV_PRINT_MSGTMsg) S21FastTBLAccessor.findByKey(inMsg);
    }

    private INV_CASH_DISC_TERMTMsgArray findInvCashDiscTerm(String glblCmpyCd, String invNum) {

        final INV_CASH_DISC_TERMTMsg inMsg = new INV_CASH_DISC_TERMTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("invNum01",     invNum);

        INV_CASH_DISC_TERMTMsgArray invCashDiscTermTMsgArray = (INV_CASH_DISC_TERMTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        
        // Def#Prod(1353) Cash discount term on Invoice
        if (invCashDiscTermTMsgArray.getValidCount() > 0) {
            
            final List<INV_CASH_DISC_TERMTMsg> tMsgList = new ArrayList<INV_CASH_DISC_TERMTMsg>();
            
            for (int i = 0; i < invCashDiscTermTMsgArray.getValidCount(); i++) {
                
                final INV_CASH_DISC_TERMTMsg invCashDiscTermTMsg = invCashDiscTermTMsgArray.no(i);
                
                // INV_CASH_DISC_RATIO_PCT
                final BigDecimal invCashDiscRatioPct = invCashDiscTermTMsg.invCashDiscRatioPct.getValue();
                
                if (invCashDiscRatioPct != null) {
                    // exclude '0.00%'
                    if (invCashDiscRatioPct.compareTo(ZERO) > 0) {
                        tMsgList.add(invCashDiscTermTMsg);
                    }
                }
            }
            
            invCashDiscTermTMsgArray = new INV_CASH_DISC_TERMTMsgArray();
            invCashDiscTermTMsgArray.setMsgList(tMsgList.toArray(new INV_CASH_DISC_TERMTMsg[0]));
            invCashDiscTermTMsgArray.setValidCount(tMsgList.size());
        }
        
        return invCashDiscTermTMsgArray;
    }

    private INV_PRMO_INFOTMsgArray findInvPrmoInfo(String glblCmpyCd, String invNum) {

        INV_PRMO_INFOTMsg inMsg = new INV_PRMO_INFOTMsg();

        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("invNum01", invNum);

        return (INV_PRMO_INFOTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    private INVTY_LOC_VTMsgArray findInvtyLocV(String glblCmpyCd, String shipFromInvtyLocCd) {

        INVTY_LOC_VTMsg inMsg = new INVTY_LOC_VTMsg();

        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("invtyLocCd01", shipFromInvtyLocCd);

        return (INVTY_LOC_VTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    private void checkInputParameter(String glblCmpyCd, String bizAppId, String userId, String invTs, String invNum) {

        checkInputParameter(glblCmpyCd, bizAppId, userId, invTs);

        if (!hasValue(invNum)) {
            throw new S21AbendException("no value is set for invNum.");
        }
    }

    private void checkInputParameter(String glblCmpyCd, String bizAppId, String userId, String invTs, List<String> invNumList) {

        checkInputParameter(glblCmpyCd, bizAppId, userId, invTs);

        if (invNumList == null || invNumList.isEmpty()) {
            throw new S21AbendException("no value is set for invNumList.");
        }
    }

    private void checkInputParameter(String glblCmpyCd, String bizAppId, String userId, String invTs) {

        if (!hasValue(glblCmpyCd)) {
            throw new S21AbendException("no value is set for glblCmpyCd.");
        }

        if (!hasValue(bizAppId)) {
            throw new S21AbendException("no value is set for bizAppId.");
        }

        if (!hasValue(userId)) {
            throw new S21AbendException("no value is set for userId.");
        }

        if (!hasValue(invTs)) {
            throw new S21AbendException("no value is set for invTs.");
        }
    }

    private boolean createInvoicePrintWork(String glblCmpyCd, String bizAppId, String userId, String invTs, List<String> invNumList, BigDecimal ratio) {

        this.messageId = null;
        this.resultInvTmplCd = null;

        List<INVTMsg> invMsgList = new ArrayList<INVTMsg>();

        for (int i = 0; i < invNumList.size(); i++) {

            INVTMsg invMsg = findInv(glblCmpyCd, invNumList.get(i));

            if (invMsg == null) {
                throw new S21AbendException("INV table is not found : INV_NUM [" + invNumList.get(i) + "]");
            }

            invMsgList.add(invMsg);
        }

        String invTmplCd = getInvTmplCd(invMsgList.get(0));

        if (!hasValue(invTmplCd)) {
            messageId = NWCM0019E;
            return false;
        }

        if (!createInvoiceWork(bizAppId, userId, invTs, ratio, invTmplCd, invMsgList)) {
            return false;
        }

        this.resultInvTmplCd = invTmplCd;
        return true;
    }

    private boolean createInvoiceWork(String bizAppId, String invOtptOpCd, String invTs, BigDecimal ratio, String invTmplCd, List<INVTMsg> invMsgList) {

        for (int i = 0; i < invMsgList.size(); i++) {

            if (!createInvoiceWork(bizAppId, invOtptOpCd, invTs, ratio, invTmplCd, invMsgList.get(i))) {
                return false;
            }
        }

        return true;
    }

    private boolean createInvoiceWork(String bizAppId, String invOtptOpCd, String invTs, BigDecimal ratio, String invTmplCd, INVTMsg invMsg) {

//        String glblCmpyCd = invMsg.glblCmpyCd.getValue();
//        String invNum = invMsg.invNum.getValue();
//
//        CCYTMsg ccyMsg = (CCYTMsg) ZYPCodeDataUtil.findByCode(CCY.class, glblCmpyCd, invMsg.dealCcyCd.getValue());
//
//        if (ccyMsg == null) {
//            throw new S21AbendException("CCY table is not found : CCY_CD [" + invMsg.dealCcyCd.getValue() + "]");
//        }
//
//        BigDecimal aftDeclPntDigitNum = ccyMsg.aftDeclPntDigitNum.getValue();
//
//        INV_BOLTMsgArray invBolMsgArray = findInvBol(glblCmpyCd, invNum);
//        // WDS #118 Change Start
//        //INV_LINETMsgArray invLineMsgArray = findInvLine(glblCmpyCd, invNum);
//        // WDS #118 Change End
//
//        INV_CASH_DISC_TERMTMsgArray invCashDiscTermMsgArray = findInvCashDiscTerm(glblCmpyCd, invNum);
//
//        INV_PRMO_INFOTMsgArray invPrmoInfoMsgArray = null;
//
//        if (INV_TMPL.OPTION_C.equals(invTmplCd) || INV_TMPL.OPTION_D.equals(invTmplCd) || INV_TMPL.OPTION_E.equals(invTmplCd)) {
//
//            invPrmoInfoMsgArray = findInvPrmoInfo(glblCmpyCd, invNum);
//        }
//
//        if (!createInvWrk(bizAppId, glblCmpyCd, invOtptOpCd, invTs, invTmplCd, ratio, aftDeclPntDigitNum, invMsg)) {
//            return false;
//        }
//
//        if (!isEmpty(invCashDiscTermMsgArray)) {
//            if (!createInvCashDiscTermWrk(bizAppId, glblCmpyCd, invOtptOpCd, invTs, ratio, aftDeclPntDigitNum, invMsg, invCashDiscTermMsgArray)) {
//                return false;
//            }
//        }
//        // WDS MOVE START R-OM028
////        if (!isEmpty(invBolMsgArray)) {
////            if (!createInvBolWrk(bizAppId, glblCmpyCd, invOtptOpCd, invTs, ratio, aftDeclPntDigitNum, invMsg, invBolMsgArray)) {
////                return false;
////            }
////        }
//        // WDS MOVE START R-OM028
//
//        // WDS #118 Change Start
////        if (!isEmpty(invLineMsgArray)) {
////            if (!createInvLineWrk(bizAppId, glblCmpyCd, invOtptOpCd, invTs, ratio, aftDeclPntDigitNum, invMsg, invBolMsgArray, invLineMsgArray)) {
////                return false;
////            }
////        }
//        INV_LINE_WRKTMsgArray invLineWrkMsgArray = findInvLineToInvLineWrk(glblCmpyCd, invNum, bizAppId, invOtptOpCd, invTs);
//        if (!isEmpty(invLineWrkMsgArray)) {
//            if (!createInvLineWrk(invLineWrkMsgArray)) {
//                return false;
//            }
//        }
//        // WDS #118 Change End
//        // WDS MOVE START R-OM028
//        if (!isEmpty(invBolMsgArray)) {
//            if (!createInvBolWrk(bizAppId, glblCmpyCd, invOtptOpCd, invTs, ratio, aftDeclPntDigitNum, invMsg, invBolMsgArray)) {
//                return false;
//            }
//        }
//        // WDS MOVE START R-OM028
//
//        if (!isEmpty(invPrmoInfoMsgArray)) {
//            if (!createInvPrmoInfoWrk(bizAppId, glblCmpyCd, invOtptOpCd, invTs, ratio, aftDeclPntDigitNum, invMsg, invPrmoInfoMsgArray)) {
//                return false;
//            }
//        }

        return true;
    }

    private boolean createInvWrk(String bizAppId, String glblCmpyCd, String invOtptOpCd, String invTs, String invTmplCd, BigDecimal ratio, BigDecimal aftDeclPntDigitNum, INVTMsg invMsg) {
//
//        INV_PRINT_MSGTMsg invPrintComnMsg = findInvPrintMsg(glblCmpyCd, getInvPrintMsgCdByInvTp(glblCmpyCd, invMsg.invTpCd.getValue()));
//
//        INV_PRINT_MSGTMsg invPrintPrmoMsg = findInvPrintMsg(glblCmpyCd, getInvPrintMsgCdByInvTmpl(glblCmpyCd, invTmplCd));
//
//        String cpoSrcTpCd = getCpoSrcTpCd(invMsg);
//        String crDrRsnNm = "";
//        String crDrSubRsnNm = "";
//
//        if (CPO_SRC_TP.CREDIT_AND_REBILL_ENTRY.equals(cpoSrcTpCd)) {
//        
//            if (hasValue(invMsg.crDrRsnCd)) {
//    
//                crDrRsnNm = getCrDrRsnNm(invMsg);
//    
//                if (!hasValue(crDrRsnNm)) {
//    
//                    messageId = NWCM0063E;
//                    return false;
//                }
//            }
//    
//            if (hasValue(invMsg.crDrRsnSubCd)) {
//    
//                crDrSubRsnNm = getCrDrSubRsnNm(invMsg);
//    
//                if (!hasValue(crDrSubRsnNm)) {
//    
//                    messageId = NWCM0064E;
//                    return false;
//                }
//            }
//        }
//
//        setValue(invMsg.invTotDealSlsAmt, calculationRatio(invMsg.invTotDealSlsAmt, ratio, aftDeclPntDigitNum, HALF_UP));
//        setValue(invMsg.invTotDealNetAmt, calculationRatio(invMsg.invTotDealNetAmt, ratio, aftDeclPntDigitNum, HALF_UP));
//        setValue(invMsg.invTotDealFrtAmt, calculationRatio(invMsg.invTotDealFrtAmt, ratio, aftDeclPntDigitNum, HALF_UP));
//        setValue(invMsg.invTotDealTaxAmt, calculationRatio(invMsg.invTotDealTaxAmt, ratio, aftDeclPntDigitNum, HALF_UP));
//
//        INV_WRKTMsg inMsg = new INV_WRKTMsg();
//        EZDMsg.copy(invMsg, null, inMsg, null);
//
//        setValue(inMsg.glblCmpyCd, glblCmpyCd);
//        setValue(inMsg.bizAppId, bizAppId);
//        setValue(inMsg.invOtptOpCd, invOtptOpCd);
//        setValue(inMsg.invTs, invTs);
//        setValue(inMsg.crDrRsnNm, crDrRsnNm);
//        setValue(inMsg.crDrSubRsnNm, crDrSubRsnNm);
//        setValue(inMsg.rcpntCtryNm, getCtryNm(glblCmpyCd, invMsg.rcpntCtryCd.getValue()));
//
//        if (invPrintComnMsg != null) {
//
//            setValue(inMsg.invPrintMsgTxt, invPrintComnMsg.invPrintMsgTxt);
//
//        } else {
//
//            inMsg.invPrintMsgTxt.clear();
//        }
//
//        if (invPrintPrmoMsg != null) {
//
//            setValue(inMsg.invDiscMsgTxt, invPrintPrmoMsg.invPrintMsgTxt);
//
//        } else {
//
//            inMsg.invDiscMsgTxt.clear();
//        }
//
//        setValue(inMsg.invTmplCd, invTmplCd);
//
//        EZDTBLAccessor.create(inMsg);
//        
//        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
//            messageId = NWCM0006E;
//            return false;
//        }

        return true;
    }

    private boolean createInvCashDiscTermWrk(String bizAppId, String glblCmpyCd, String invOtptOpCd, String invTs, BigDecimal ratio, BigDecimal aftDeclPntDigitNum, INVTMsg invMsg, INV_CASH_DISC_TERMTMsgArray invCashDiscTermMsgArray) {

        for (int i = 0; i < invCashDiscTermMsgArray.getValidCount(); i++) {

            INV_CASH_DISC_TERM_WRKTMsg inMsg = new INV_CASH_DISC_TERM_WRKTMsg();
            EZDMsg.copy(invCashDiscTermMsgArray.no(i), null, inMsg, null);

            setValue(inMsg.glblCmpyCd, glblCmpyCd);
            setValue(inMsg.bizAppId, bizAppId);
            setValue(inMsg.invOtptOpCd, invOtptOpCd);
            setValue(inMsg.invTs, invTs);

            setValue(inMsg.invCashDiscAmt, calculationRatio(invCashDiscTermMsgArray.no(i).invCashDiscAmt, ratio, aftDeclPntDigitNum, HALF_UP));

            EZDTBLAccessor.create(inMsg);
            
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                messageId = NWCM0006E;
                return false;
            }
        }
        return true;
    }

    private boolean createInvBolWrk(String bizAppId, String glblCmpyCd, String invOtptOpCd, String invTs, BigDecimal ratio, BigDecimal aftDeclPntDigitNum, INVTMsg invMsg, INV_BOLTMsgArray invBolMsgArray) {

        BigDecimal invTotDealSlsAmt = invMsg.invTotDealSlsAmt.getValue();
        BigDecimal invTotDealNetAmt = invMsg.invTotDealNetAmt.getValue();
        BigDecimal invTotDealFrtAmt = invMsg.invTotDealFrtAmt.getValue();
        BigDecimal invTotDealTaxAmt = invMsg.invTotDealTaxAmt.getValue();

        BigDecimal diffShipDealNetAmt = invTotDealNetAmt.subtract(invTotDealFrtAmt).subtract(invTotDealTaxAmt);

        //WDS Add Start R-OM028
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", glblCmpyCd);
        mapParam.put("bizAppId", bizAppId);
        mapParam.put("invOtptOpCd", invOtptOpCd);
        mapParam.put("invTs", invTs);
        mapParam.put("invNum", invMsg.invNum.getValue());
        mapParam.put("setSubNum", SET_HDR_INV_LINE_SUB_NUM);
        List<Map<String, Object>> amtList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("queryINV_WRK", mapParam);
        Map<String, Map<String, Object>> amtMap = new HashMap<String, Map<String,Object>>();
        for (Map<String, Object> amtObj : amtList) {
            amtMap.put((String)amtObj.get("INV_BOL_LINE_NUM"), amtObj);
        }
        List<INV_BOLTMsg> invBolTMsgList = new ArrayList<INV_BOLTMsg>();
        for (int i = 0; i < invBolMsgArray.getValidCount(); i++) {
            Map<String, Object> amtObj = amtMap.get(invBolMsgArray.no(i).invBolLineNum.getValue());
            if (amtObj == null) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(invBolMsgArray.no(i).shipDealSlsAmt, (BigDecimal)amtObj.get("SHIP_DEAL_SLS_AMT"));
            ZYPEZDItemValueSetter.setValue(invBolMsgArray.no(i).shipDealNetAmt, (BigDecimal)amtObj.get("SHIP_DEAL_NET_AMT"));
            invBolTMsgList.add(invBolMsgArray.no(i));
        }
        INV_BOLTMsgArray invBolMsgArray2 = new INV_BOLTMsgArray();
        invBolMsgArray2.setMsgList(invBolTMsgList.toArray(new INV_BOLTMsg[0]));
        invBolMsgArray2.setValidCount(invBolTMsgList.size());
        //WDS Add Start R-OM028
        //WDS Upd Start R-OM028 invBolMsgArray => invBolMsgArray2
        for (int i = 0; i < invBolMsgArray2.getValidCount(); i++) {

            String shipFromInvtyLocCd = invBolMsgArray2.no(i).shipFromInvtyLocCd.getValue();
            String locRoleTpCd = null;

            if (hasValue(shipFromInvtyLocCd)) {

                INVTY_LOC_VTMsgArray invtyLocVMsgArray = findInvtyLocV(glblCmpyCd, shipFromInvtyLocCd);

                if (isEmpty(invtyLocVMsgArray)) {

                    messageId = NWCM0065E;
                    return false;
                }

                locRoleTpCd = invtyLocVMsgArray.no(0).locRoleTpCd.getValue();
            }

            INV_BOL_WRKTMsg inMsg = new INV_BOL_WRKTMsg();
            EZDMsg.copy(invBolMsgArray2.no(i), null, inMsg, null);

            setValue(inMsg.glblCmpyCd, glblCmpyCd);
            setValue(inMsg.bizAppId, bizAppId);
            setValue(inMsg.invOtptOpCd, invOtptOpCd);
            setValue(inMsg.invTs, invTs);
            setValue(inMsg.locRoleTpCd, locRoleTpCd);

            // 04/20/2010 Defect 5505
            if (hasValue(invBolMsgArray2.no(i).shipToCtryCd)) {
                String shipToCtryNm = getCtryNm(glblCmpyCd, invBolMsgArray2.no(i).shipToCtryCd.getValue());

                if (shipToCtryNm == null) {
                    messageId = NWCM0077E;
                    return false;
                }

                setValue(inMsg.shipToCtryNm, shipToCtryNm);
            }

            BigDecimal shipDealSlsAmt = calculationRatio(invBolMsgArray2.no(i).shipDealSlsAmt, ratio, aftDeclPntDigitNum, DOWN);
            BigDecimal shipDealNetAmt = calculationRatio(invBolMsgArray2.no(i).shipDealNetAmt, ratio, aftDeclPntDigitNum, DOWN);

            if (i < invBolMsgArray2.getValidCount() - 1) {

                invTotDealSlsAmt = invTotDealSlsAmt.subtract(shipDealSlsAmt);
                diffShipDealNetAmt = diffShipDealNetAmt.subtract(shipDealNetAmt);
                setValue(inMsg.shipDealSlsAmt, shipDealSlsAmt);
                setValue(inMsg.shipDealNetAmt, shipDealNetAmt);

            } else {

                setValue(inMsg.shipDealSlsAmt, invTotDealSlsAmt);
                setValue(inMsg.shipDealNetAmt, diffShipDealNetAmt);
            }

            setValue(invBolMsgArray2.no(i).shipDealSlsAmt, inMsg.shipDealSlsAmt);
            setValue(invBolMsgArray2.no(i).shipDealNetAmt, inMsg.shipDealNetAmt);

            EZDTBLAccessor.create(inMsg);
            
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                messageId = NWCM0006E;
                return false;
            }
          //WDS Upd End R-OM028 i
        }

        return true;
    }

    // WDS #118 Change Start
//    private boolean createInvLineWrk(String bizAppId, String glblCmpyCd, String invOtptOpCd, String invTs, BigDecimal ratio, BigDecimal aftDeclPntDigitNum, INVTMsg invMsg, INV_BOLTMsgArray invBolMsgArray, INV_LINETMsgArray invLineMsgArray) {
//
//        for (int i = 0; i < invLineMsgArray.getValidCount(); i++) {
//
//            INV_LINE_WRKTMsg inMsg = new INV_LINE_WRKTMsg();
//            EZDMsg.copy(invLineMsgArray.no(i), null, inMsg, null);
//
//            setValue(inMsg.glblCmpyCd, glblCmpyCd);
//            setValue(inMsg.bizAppId, bizAppId);
//            setValue(inMsg.invOtptOpCd, invOtptOpCd);
//            setValue(inMsg.invTs, invTs);
//
//            EZDTBLAccessor.create(inMsg);
//            
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
//                messageId = NWCM0006E;
//                return false;
//            }
//        }
//
//        return true;
//    }

    private boolean createInvLineWrk(INV_LINE_WRKTMsgArray invLineWrkMsgArray) {

        for (int i = 0; i < invLineWrkMsgArray.getValidCount(); i++) {

            INV_LINE_WRKTMsg inMsg = (INV_LINE_WRKTMsg) invLineWrkMsgArray.get(i);

            EZDTBLAccessor.create(inMsg);
            
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                messageId = NWCM0006E;
                return false;
            }
        }

        return true;
    }
    // WDS #118 Change End

    private boolean createInvPrmoInfoWrk(String bizAppId, String glblCmpyCd, String invOtptOpCd, String invTs, BigDecimal ratio, BigDecimal aftDeclPntDigitNum, INVTMsg invMsg, INV_PRMO_INFOTMsgArray invPrmoInfoMsgArray) {

        INV_PRMO_INFO_WRKTMsg inMsg = new INV_PRMO_INFO_WRKTMsg();

        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.bizAppId, bizAppId);
        setValue(inMsg.invOtptOpCd, invOtptOpCd);
        setValue(inMsg.invTs, invTs);

        for (int i = 0; i < invPrmoInfoMsgArray.getValidCount(); i++) {

            setValue(inMsg.invPrmoInfoPk, invPrmoInfoMsgArray.no(i).invPrmoInfoPk);
            setValue(inMsg.invNum, invPrmoInfoMsgArray.no(i).invNum);
            setValue(inMsg.invBolLineNum, invPrmoInfoMsgArray.no(i).invBolLineNum);
            setValue(inMsg.invLineNum, invPrmoInfoMsgArray.no(i).invLineNum);
            setValue(inMsg.invLineSubNum, invPrmoInfoMsgArray.no(i).invLineSubNum);
            setValue(inMsg.invLineSubTrxNum, invPrmoInfoMsgArray.no(i).invLineSubTrxNum);
            setValue(inMsg.prmoCatgNm, invPrmoInfoMsgArray.no(i).prmoCatgNm);
            setValue(inMsg.prmoShortNm, invPrmoInfoMsgArray.no(i).prmoShortNm);
            setValue(inMsg.dealDiscAmt, calculationRatio(invPrmoInfoMsgArray.no(i).dealDiscAmt, ratio, aftDeclPntDigitNum, HALF_UP));
            setValue(inMsg.dealPerUnitFixAmt, calculationRatio(invPrmoInfoMsgArray.no(i).dealPerUnitFixAmt, ratio, aftDeclPntDigitNum, HALF_UP));
            setValue(inMsg.invPrmoInfoSqNum, invPrmoInfoMsgArray.no(i).invPrmoInfoSqNum);

            EZDTBLAccessor.create(inMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                messageId = NWCM0006E;
                return false;
            }
        }
        
        return true;
    }

    private BigDecimal calculationRatio(EZDTBigDecimalItem item, BigDecimal ratio, BigDecimal aftDeclPntDigitNum, RoundingMode rounding) {

        BigDecimal value = item.getValue();

        if (!hasValue(value) || !hasValue(ratio)) {
            return value;
        }

        BigDecimal calculationResult = ratio.divide(new BigDecimal("100")).multiply(value);

        return calculationResult.setScale(aftDeclPntDigitNum.intValue(), rounding);
    }

    private String getCtryNm(final String glblCmpyCd, final String ctryCd) {

        StringBuilder cacheKeySb = new StringBuilder(70);
        cacheKeySb.append("glblCmpyCd=").append(glblCmpyCd).append(",");
        cacheKeySb.append("ctryCd=").append(ctryCd).append(",");

        String cacheKey = cacheKeySb.toString();

        String ctryNm = s21LRUMap.get(cacheKey);

        if (ctryNm == null) {
            Map<String, String> mapParam = new HashMap<String, String>();
            mapParam.put("glblCmpyCd", glblCmpyCd);
            mapParam.put("ctryCd", ctryCd);
            ctryNm = (String) ssmBatchClient.queryObject("queryGetCtryNm", mapParam);

            if (ctryNm == null) {
                throw new S21AbendException(NWCM0007E, new String[] {"CTRY", "GLBL_CMPY_CD : " + glblCmpyCd + " CTRY_CD : " + ctryCd });
            }
            
            s21LRUMap.put(cacheKey, ctryNm);
        }

        return ctryNm;
    }

    private String getCpoSrcTpCd(INVTMsg invMsg) {
        
        String trxSrcTpCd = invMsg.trxSrcTpCd.getValue();
        String invTpCd = invMsg.invTpCd.getValue();

        String cpoSrcTpCd = "";

        if (TRX_SRC_TP.WHOLE_SALES.equals(trxSrcTpCd) && (INV_TP.CREDIT_MEMO.equals(invTpCd) || INV_TP.DEBIT_MEMO.equals(invTpCd))) {

            CPOTMsg inMsg = new CPOTMsg();
            setValue(inMsg.glblCmpyCd, invMsg.glblCmpyCd);
            setValue(inMsg.cpoOrdNum, invMsg.cpoOrdNum);
            
            CPOTMsg outMsg = (CPOTMsg)S21FastTBLAccessor.findByKey(inMsg);
            
            if (outMsg != null) {
                cpoSrcTpCd = outMsg.cpoSrcTpCd.getValue();
            }
        }
        
        return cpoSrcTpCd;
    }
    
    private String getCrDrRsnNm(INVTMsg invMsg) {
        
        String crDrRsnNm = "";
        
        if (!hasValue(invMsg.crDrRsnCd)) {
            return crDrRsnNm;
        }
        
        CR_DR_RSNTMsg inMsg = new CR_DR_RSNTMsg();
        setValue(inMsg.glblCmpyCd, invMsg.glblCmpyCd);
        setValue(inMsg.crDrRsnCd, invMsg.crDrRsnCd);
        
        CR_DR_RSNTMsg outMsg = (CR_DR_RSNTMsg)S21FastTBLAccessor.findByKey(inMsg);
        
        if (outMsg != null) {
            crDrRsnNm = outMsg.crDrRsnNm.getValue();
        }
        
        return crDrRsnNm;
    }
    
    private String getCrDrSubRsnNm(INVTMsg invMsg) {
        
        String crDrSubRsnNm = "";
        
        if (!hasValue(invMsg.crDrRsnSubCd)) {
            return crDrSubRsnNm;
        }
        
        CR_DR_SUB_RSNTMsg inMsg = new CR_DR_SUB_RSNTMsg();
        setValue(inMsg.glblCmpyCd, invMsg.glblCmpyCd);
        setValue(inMsg.crDrSubRsnCd, invMsg.crDrRsnSubCd);

        CR_DR_SUB_RSNTMsg outMsg = (CR_DR_SUB_RSNTMsg)S21FastTBLAccessor.findByKey(inMsg);
        
        if (outMsg != null) {
            crDrSubRsnNm = outMsg.crDrSubRsnNm.getValue();
        }
        
        return crDrSubRsnNm;
    }

    private String setInvTmpl(INVTMsg invMsg) {
    	
    	Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", invMsg.glblCmpyCd.getValue());
        if (INV_TP.CREDIT_MEMO.equals(invMsg.invTpCd.getValue()) || INV_TP.DEBIT_MEMO.equals(invMsg.invTpCd.getValue())) {
        	mapParam.put("custCd", invMsg.sellToCustCd.getValue());
        	mapParam.put("invTpFlg", N);
        } else {
        	if (Y.equals(invMsg.flPlnFlg.getValue())) {
                mapParam.put("custCd", invMsg.payerCustCd.getValue());
            } else {
                mapParam.put("custCd", invMsg.sellToCustCd.getValue());
            }
        	mapParam.put("invTpFlg", Y);
        }
        
        mapParam.put("billTpCd", BILL_TP.REQULAR_INVOICE);
        mapParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        Map result = (Map) ssmBatchClient.queryObject("queryGetInvTmplCd", mapParam);

        if (result == null) {
            mapParam.put("rgtnStsCd", RGTN_STS.TERMINATED);
            result = (Map) ssmBatchClient.queryObject("queryGetInvTmplCd", mapParam);
            if (result == null) {
                return null;
            }
        }
        
        String invTmplCd = "";
        String ctryCd = (String)result.get("CTRY_CD");
//
//        if (getCtryCd(invMsg.glblCmpyCd.getValue()).equals(ctryCd)) {
//            invTmplCd = (String)result.get("INV_TMPL_CD");
//        } else if (result.get("INV_TMPL_CD").equals(INV_TMPL.OPTION_G)){
//        	invTmplCd = INV_TMPL.OPTION_G;
//        } else {
//            invTmplCd = INV_TMPL.OPTION_F;
//        }

        return invTmplCd;
    }
}
