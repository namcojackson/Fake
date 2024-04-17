package business.servlet.NFCL0730.common;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import business.servlet.NFCL0730.NFCL0730BMsg;
import business.servlet.NFCL0730.constant.NFCL0730Constant;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

/**
 *<pre>
 * Batch Entry
 * 
 * Date         Company         Name           Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/17/2015   Fujitsu         T.Tanaka        Create          Initial
 * 3/10/2016    CSAI            K.Uramori       Update
 * 3/16/2016    CSAI            K.Uramori       Update          Secutiry Check
 * 2016/09/07   Hitachi         K.Kojima        Update          QC#13509
 * 2016/09/13   Hitachi         K.Kasai         Update          QC#13898
 * 2017/08/21   Hitachi         T.Tsuchida      Update          QC#19573
 * 2018/01/17   Fujitsu         T.Murai         Update          QC#20563
 * 2022/11/10   Hitachi         S.Naya          Update          QC#57252
 * 2023/01/27   Hitachi         T.Kuroda        Update          QC#61089
 *</pre>
 */
public class NFCL0730CommonLogic implements NFCL0730Constant {

    public static void initialize(EZDCommonHandler scrnAppli, NFCL0730BMsg scrnMsg, S21UserProfileService userProfileService) {
        scrnMsg.clearAllGUIAttribute("NFCL0730Scrn00");
        S21TableColorController tblColor = new S21TableColorController("NFCL0730Scrn00", scrnMsg);
        tblColor.clearRowsBG(TABLE_A, scrnMsg.A);
        
        scrnMsg.setInputProtected(false);
        scrnAppli.setButtonProperties("AddLine", "AddLine", "Add", 1, null);
        scrnAppli.setButtonProperties("DeleteLines", "DeleteLines", "Delete", 0, null);

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        //---- start 2016/03/16 disable download button
        /*if (scrnMsg.A.getValidCount() > 0) {
            scrnAppli.setButtonProperties("btn6", "CMN_Download", "Download", 1, null);
        } else {
            scrnAppli.setButtonProperties("btn6", "CMN_Download", "Download", 0, null);
        }
        */
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        //---- end 2016/03/16
        
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "CMN_Clear", "Clear", 1, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return", "Return", 1, null);
        
        scrnMsg.arTrxNum_H1.setInputProtected(true);
        scrnMsg.dealRmngBalGrsAmt_H1.setInputProtected(true);

        scrnMsg.arAdjTpCd_H1.setInputProtected(false);
        scrnMsg.arAdjTpCd_LC.setInputProtected(false);
        scrnMsg.arAdjTpCd_LC.setInputProtected(false);
        
        scrnMsg.arAdjTpCd_H1.setIndispensable(true);
        scrnMsg.adjDt_H1.setIndispensable(true);
        scrnMsg.dealArAdjAmt_H1.setIndispensable(true);
        // START 2022/11/10 S.Naya [QC#57252,ADD]
        if (NFCL0730Constant.OTHER_CODE.equals(scrnMsg.arAdjTpCd_H1.getValue())) {
            scrnMsg.xxCmntTxt_H1.setInputProtected(false);
            scrnAppli.setButtonEnabled(BTN_A, true);
        } else {
            scrnMsg.xxCmntTxt_H1.setInputProtected(true);
            scrnAppli.setButtonEnabled(BTN_A, false);
        }
        // END   2022/11/10 S.Naya [QC#57252,ADD]
        
        
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            // if the line is already processed, check box should be disabled.
            if (hasValue(scrnMsg.A.no(i).arAdjNum_A1)) {
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
            }
            scrnMsg.A.no(i).arAdjNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).arAdjTpCd_A1.setInputProtected(true);
            // START 2016/09/13 [QC#13898, MOD]
            scrnMsg.A.no(i).arAdjTpDescTxt_A1.setInputProtected(true);
            // END 2016/09/13 [QC#13898, MOD]
            scrnMsg.A.no(i).adjCmntTxt_A1.setInputProtected(true);
            // START 2017/08/21 T.Tsuchida [QC#19573,ADD]
            scrnMsg.A.no(i).arWrtOffNoteTxt_A1.setInputProtected(true);
            // END 2017/08/21 T.Tsuchida [QC#19573,ADD]
            // START 2022/11/10 S.Naya [QC#57252,ADD]
            scrnMsg.A.no(i).xxCmntTxt_A1.setInputProtected(true);
            // END   2022/11/10 S.Naya [QC#57252,ADD]
            scrnMsg.A.no(i).dealApplyAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).glDt_A1.setInputProtected(true);
            
            scrnMsg.A.no(i).xxChkBox_A1.setValue(ZYPConstant.FLG_OFF_N);
        }
        
        setReadonly(scrnAppli, scrnMsg);
    }
    
    public static void transMsgCheck(NFCL0730BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.arAdjTpCd_H1);
        scrnMsg.addCheckItem(scrnMsg.adjDt_H1);
        scrnMsg.addCheckItem(scrnMsg.dealArAdjAmt_H1);
    }

    /**
     * 
     * @param scrnMsg NFCL0730BMsg
     */
    public static void addLineCheck(NFCL0730BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.arAdjTpCd_H1);
        scrnMsg.addCheckItem(scrnMsg.adjDt_H1);
        scrnMsg.addCheckItem(scrnMsg.dealArAdjAmt_H1);
        // START 2022/11/10 S.Naya [QC#57252,ADD]
        scrnMsg.addCheckItem(scrnMsg.xxCmntTxt_H1);
        // END   2022/11/10 S.Naya [QC#57252,ADD]
        
        //---- start 2016/03/10 mod amount check
        if (hasValue(scrnMsg.dealArAdjAmt_H1)) {
            // $0 check
            if (BigDecimal.ZERO.compareTo(scrnMsg.dealArAdjAmt_H1.getValue()) == 0) {
                scrnMsg.dealArAdjAmt_H1.setErrorInfo(1, "NFCM0191E");
                return;
            }
        
        
            // Check Amount
            BigDecimal lineAmount = new BigDecimal(0);
            
            lineAmount= lineAmount.add(scrnMsg.dealArAdjAmt_H1.getValue());
            
            // remove. Balance is updated when line is added, so no need.
            /*for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                // don't include processed data
                if (hasValue(scrnMsg.A.no(i).arAdjNum_A1)) {
                    continue;
                }
                lineAmount = lineAmount.add(scrnMsg.A.no(i).dealApplyAmt_A1.getValue());
            }
            */

            // START 2016/09/07 K.Kojima [QC#13509,ADD]
            BigDecimal totalDealApplyAmt = BigDecimal.ZERO;
            for(int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).arAdjNum_A1)) {
                    continue;
                } else {
                    totalDealApplyAmt = totalDealApplyAmt.add(scrnMsg.A.no(i).dealApplyAmt_A1.getValue());
                }
            }
            totalDealApplyAmt = totalDealApplyAmt.add(scrnMsg.dealArAdjAmt_H1.getValue());
            // END 2016/09/07 K.Kojima [QC#13509,ADD]

            // Invoice
            if (AR_TRX_TP.INVOICE.equals(scrnMsg.arTrxTpCd_H2.getValue())) {
            
                if(scrnMsg.dealRmngBalGrsAmt_H1.getValue().add(lineAmount).compareTo(BigDecimal.ZERO) < 0) {
                    scrnMsg.dealArAdjAmt_H1.setErrorInfo(1, "NFCM0070E");
                }
                // START 2018/01/17 T.Murai [QC#20563,ADD]
                if(scrnMsg.dealRmngBalGrsAmt_H1.getValue().subtract(scrnMsg.dealApplyAdjRsvdAmt_H2.getValue()).add(lineAmount) //
                        .compareTo(BigDecimal.ZERO) < 0) {
                    scrnMsg.dealArAdjAmt_H1.setErrorInfo(1, NFCM0881E);
                }
                // END 2018/01/17 T.Murai [QC#20563,ADD]

                // START 2016/09/07 K.Kojima [QC#13509,DEL]
                // if((scrnMsg.dealRmngBalGrsAmt_H1.getValue().add(lineAmount)).compareTo(scrnMsg.dealRmngBalGrsAmt_H1.getValue())
                // > 0) {
                // scrnMsg.dealArAdjAmt_H1.setErrorInfo(1,
                // "NFCM0826E");
                // }
                // END 2016/09/07 K.Kojima [QC#13509,DEL]
                // START 2016/09/07 K.Kojima [QC#13509,ADD]
                if (scrnMsg.dealApplyAdjAmt_H2.getValue().subtract(totalDealApplyAmt).compareTo(BigDecimal.ZERO) < 0) {
                    scrnMsg.dealArAdjAmt_H1.setErrorInfo(1, "NFCM0826E");
                }
                // END 2016/09/07 K.Kojima [QC#13509,ADD]
            } else {  // credit
                
                if(scrnMsg.dealRmngBalGrsAmt_H1.getValue().add(lineAmount).compareTo(BigDecimal.ZERO) > 0) {
                    scrnMsg.dealArAdjAmt_H1.setErrorInfo(1, "NFCM0070E");
                }
                // START 2016/09/07 K.Kojima [QC#13509,DEL]
                // if((scrnMsg.dealRmngBalGrsAmt_H1.getValue().add(lineAmount)).compareTo(scrnMsg.dealRmngBalGrsAmt_H1.getValue())
                // < 0) {
                // scrnMsg.dealArAdjAmt_H1.setErrorInfo(1,
                // "NFCM0826E");
                // }
                // END 2016/09/07 K.Kojima [QC#13509,DEL]
                // START 2016/09/07 K.Kojima [QC#13509,ADD]
                if (scrnMsg.dealApplyAdjAmt_H2.getValue().subtract(totalDealApplyAmt).compareTo(BigDecimal.ZERO) > 0) {
                    scrnMsg.dealArAdjAmt_H1.setErrorInfo(1, "NFCM0826E");
                }
                // END 2016/09/07 K.Kojima [QC#13509,ADD]
            }
        }
        //---- end 2016/03/10
        
        if (hasValue(scrnMsg.adjDt_H1)) { // added condition 2016/03/11
            // Check Sales Date
            String slsDt = ZYPDateUtil.addDays(scrnMsg.slsDt.getValue(), 1);
            
            if( ZYPDateUtil.compare(scrnMsg.adjDt_H1.getValue(), slsDt) > 0) {
                scrnMsg.adjDt_H1.setErrorInfo(1, "NFCM0040E");
            }
        
            // Check Accounting Date
            String slsDtYrMth = scrnMsg.slsDt.getValue().substring(0, 6);
            if( scrnMsg.acctYrMth_H2.getValue().equals(slsDtYrMth)) {
                String adjDtYrMth = scrnMsg.adjDt_H1.getValue().substring(0,6);
                if(!scrnMsg.acctYrMth_H2.getValue().equals(adjDtYrMth)) {
                    scrnMsg.adjDt_H1.setErrorInfo(1, "NFCM0045E");
                }
            } else {
                // START 2016/09/06 K.Kojima [QC#13509,MOD]
                // String preYrMth =
                // getBeforeMonth(scrnMsg.acctDt_H2.getValue());
                String preYrMth = getBeforeMonth(scrnMsg.slsDt.getValue());
                String adjDtYrMth = scrnMsg.adjDt_H1.getValue().substring(0,6);
                // END 2016/09/06 K.Kojima [QC#13509,MOD]
                preYrMth = preYrMth.substring(0, 6);
                // START 2016/09/06 K.Kojima [QC#13509,MOD]
                // if(!slsDtYrMth.equals(scrnMsg.acctYrMth_H2.getValue())
                // && !slsDtYrMth.equals(preYrMth)) {
                if (!preYrMth.equals(adjDtYrMth) && !slsDtYrMth.equals(adjDtYrMth)) {
                    // END 2016/09/06 K.Kojima [QC#13509,MOD]
                    scrnMsg.adjDt_H1.setErrorInfo(1, "NFCM0045E");
                }
            }
        }
        // START 2022/11/10 S.Naya [QC#57252,ADD]
        if (OTHER_CODE.equals(scrnMsg.arAdjTpCd_H1.getValue()) && !ZYPCommonFunc.hasValue(scrnMsg.xxCmntTxt_H1)) {
            scrnMsg.xxCmntTxt_H1.setErrorInfo(1, ZZM9000E, new String[] {"Charge Account" });
        }
        // END   2022/11/10 S.Naya [QC#57252,ADD]
    }
    
    /**
     * 
     * @param param
     * @return
     */
    public static String getBeforeMonth(String param) {

        String retVal = "";

        // START 2016/09/06 K.Kojima [QC#13509,MOD]
        // SimpleDateFormat ft = new SimpleDateFormat("yyyymmdd");
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        // END 2016/09/06 K.Kojima [QC#13509,MOD]
        Calendar cal = Calendar.getInstance();
        try {

            cal.setTime(ft.parse(param));
            cal.add(Calendar.MONTH, -1);
            retVal = ft.format(cal.getTime());

        } catch (ParseException pe) {
        }
        return retVal;
    }

    /**
     * 
     * @param scrnAppli
     * @param scrnMsg
     */
    public static void commonBtnControl_AddLine(EZDCommonHandler scrnAppli, NFCL0730BMsg scrnMsg) {
        scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 0, null);
    }

    /**
     * 
     * @param scrnAppli
     * @param scrnMsg
     */
    public static void protectSubmit(EZDCommonHandler scrnAppli, NFCL0730BMsg scrnMsg) {

        scrnAppli.setButtonProperties("AddLine", "AddLine", "Add", 1, null);  // add button to be enabled
        scrnAppli.setButtonProperties("DeleteLines", "DeleteLines", "Delete", 0, null);

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "CMN_Clear", "Clear", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return", "Return", 1, null);
        
        //---- start 2016/03/10 no need to protect and clear. Data needs to be re-searched and shown.
        /*
        scrnMsg.arAdjTpCd_H1.clear();
        scrnMsg.adjDt_H1.clear();
        scrnMsg.dealArAdjAmt_H1.clear();
        scrnMsg.adjCmntTxt_H1.clear();
        scrnMsg.arAdjTpCd_H1.setInputProtected(true);
        scrnMsg.adjDt_H1.setInputProtected(true);
        scrnMsg.dealArAdjAmt_H1.setInputProtected(true);
        scrnMsg.adjCmntTxt_H1.setInputProtected(true);

        scrnMsg.A.setInputProtected(true);
        */
        //---- end 2016/03/10
    }
    
    //---- start add 2016/03/10
    public static int getAddedCount(NFCL0730BMsg scrnMsg) {
        int cnt = 0;        
        for (int i=0; i < scrnMsg.A.getValidCount(); i++) {
            
            if (hasValue(scrnMsg.A.no(i).arAdjNum_A1)) {
                continue;
            }
            cnt = cnt + 1;
        }
        return cnt;
    }
    //---- end
    
    //---- start add 2016/03/16
    /**
     * hasUpdateFuncId
     * @return boolean
     */
    private static boolean hasUpdateFuncId() {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(FUNC_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("ZZSM4300E", new String[] {userProfSvc.getContextUserInfo().getUserId() });
        }

        // START 2023/01/27 T.Kuroda [QC#61089, MOD]
//        if (funcList.contains(FUNCTION_UPDATE)) {
//            return true;
//        }
//
//        return false;
        if (funcList.contains(FUNCTION_REFERENCE) && funcList.size() == 1) {
            return false;
        }

        return true;
        // END   2023/01/27 T.Kuroda [QC#61089, MOD]
    }
    
    private static void setReadonly(EZDCommonHandler scrnAppli, NFCL0730BMsg scrnMsg) {
        if (!hasUpdateFuncId()) {
            
            // protect all
            scrnAppli.setButtonProperties("AddLine", "", "Add", 0, null);
            scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);
            
            scrnMsg.arAdjTpCd_H1.setInputProtected(true);
            scrnMsg.arAdjTpCd_LC.setInputProtected(true);
            scrnMsg.arAdjTpCd_LC.setInputProtected(true);
            scrnMsg.adjDt_H1.setInputProtected(true);
            scrnMsg.dealArAdjAmt_H1.setInputProtected(true);
            scrnMsg.adjCmntTxt_H1.setInputProtected(true);
        }
    }
    //---- end 2016/03/16

    // START 2022/11/10 S.Naya [QC#57252,ADD]
    /**
     * Get Param NMAL2550 For Charge Account
     * @param scrnMsg NFCL0730BMsg
     * @return Param NMAL2550 For Payment Term
     */
    public static Object[] getParamForChargeAccount(NFCL0730BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);
        if (!splitCOA9Seg(scrnMsg)) {
            return null;
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I0, FUNC_ID);

        Object[] params = new Object[PARAM_INDEX_10];
        int index = 0;
        params[index++] = scrnMsg.xxPopPrm_I0;
        params[index++] = scrnMsg.xxPopPrm_I1;
        params[index++] = scrnMsg.xxPopPrm_I7;
        params[index++] = scrnMsg.xxPopPrm_I2;
        params[index++] = scrnMsg.xxPopPrm_I3;
        params[index++] = scrnMsg.xxPopPrm_I4;
        params[index++] = scrnMsg.xxPopPrm_I5;
        params[index++] = scrnMsg.xxPopPrm_I6;
        params[index++] = scrnMsg.xxPopPrm_I8;
        params[index++] = scrnMsg.xxPopPrm_I9;
        return params;
    }

    /**
     * Clear PopUpParam
     * @param scrnMsg NFCL0730BMsg
     */
    private static void clearPopUpParam(NFCL0730BMsg scrnMsg) {
        scrnMsg.xxPopPrm_I0.clear();
        scrnMsg.xxPopPrm_I1.clear();
        scrnMsg.xxPopPrm_I2.clear();
        scrnMsg.xxPopPrm_I3.clear();
        scrnMsg.xxPopPrm_I4.clear();
        scrnMsg.xxPopPrm_I5.clear();
        scrnMsg.xxPopPrm_I6.clear();
        scrnMsg.xxPopPrm_I7.clear();
        scrnMsg.xxPopPrm_I8.clear();
        scrnMsg.xxPopPrm_I9.clear();
    }

    /**
     * splitCOA9Seg
     * @param scrnMsg NFCL0730BMsg
     * @return boolean
     */
    private static boolean splitCOA9Seg(NFCL0730BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxCmntTxt_H1)) {
            // Set Default Value
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I1, scrnMsg.coaCmpyCd_DF.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I2, scrnMsg.coaBrCd_DF.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I3, scrnMsg.coaCcCd_DF.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I4, scrnMsg.coaAcctCd_DF.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I5, scrnMsg.coaProdCd_DF.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I6, scrnMsg.coaChCd_DF.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I7, scrnMsg.coaAfflCd_DF.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I8, scrnMsg.coaProjCd_DF.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I9, scrnMsg.coaExtnCd_DF.getValue());
            
            return true;
        }
        
        String coa[] = scrnMsg.xxCmntTxt_H1.getValue().split("\\.", PARAM_INDEX_9);

        if(coa.length != PARAM_INDEX_9) {
            String errMsg = "9 segments format";
            errMsg = errMsg.concat("(");
            errMsg = errMsg.concat(String.valueOf(coa.length));
            errMsg = errMsg.concat(")");
            scrnMsg.xxCmntTxt_H1.setErrorInfo(1, NFBM0041E, new String[]{errMsg});
            return false;
        }

        int index = 0;
        
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I1, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I2, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I3, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I4, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I5, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I6, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I7, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I8, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I9, coa[index++]);

        return true;
    }
    // END   2022/11/10 S.Naya [QC#57252,ADD]
}

