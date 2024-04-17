/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL1110;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFBL1110.NFBL1110CMsg;
//import business.servlet.NFBL1110.constant.NFBL1110Constant;

import business.blap.NFBL1110.NFBL1110CMsg;
import business.servlet.NFBL1110.common.NFBL1110CommonLogic;
import business.servlet.NFBL1110.constant.NFBL1110Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * AP Invoice Maintenance Batch Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03   CUSA            Y.Aikawa        Create          N/A
 * 2016/08/04   Hitachi         J.Kim           Update          QC#12712
 * 2016/08/05   Hitachi         K.Kojima        Update          QC#12690
 * 2016/08/05   Fujitsu         T.Murai         Update          QC#12692
 * 2016/08/23   Fujitsu         T.Murai         Update          QC#12830
 * 2017/11/13   CITS            K.Ogino         Update          QC#21686
 * </pre>
 */
public class NFBL1110Scrn00_CMN_Submit extends S21CommonHandler implements NFBL1110Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL1110BMsg scrnMsg = (NFBL1110BMsg) bMsg;

        // START 2016/08/05 K.Kojima [QC#12690,DEL]
        // if (scrnMsg.Y.getValidCount() > 0) {
        // END 2016/08/05 K.Kojima [QC#12690,DEL]
            // Mandatory Check
            // Batch Header
            // Check batch date
            if (ZYPCommonFunc.hasValue(scrnMsg.apBatDt_BA)
            &&  Integer.parseInt(scrnMsg.apBatDt_BA.getValue()) > Integer.parseInt(ZYPDateUtil.getCurrentSystemTime(YYYYMMDD))) {
                scrnMsg.apBatDt_BA.setErrorInfo(1, NFBM0141E, new String[] {"Batch Date", "today"});
            }
            scrnMsg.addCheckItem(scrnMsg.apCtrlAmt_BA);
            scrnMsg.addCheckItem(scrnMsg.apCtrlCnt_BA);
            scrnMsg.addCheckItem(scrnMsg.apBatDt_BA);
            // Invoice Header
            scrnMsg.addCheckItem(scrnMsg.apInvAmt_IH);
            scrnMsg.addCheckItem(scrnMsg.prntVndCd_IH);
            scrnMsg.addCheckItem(scrnMsg.apMiscAmt_IH);
            scrnMsg.addCheckItem(scrnMsg.apInvNum_IH);
            scrnMsg.addCheckItem(scrnMsg.vndSiteNm_IH);
            scrnMsg.addCheckItem(scrnMsg.apTaxAmt_IH);
            scrnMsg.addCheckItem(scrnMsg.invDt_IH);
            scrnMsg.addCheckItem(scrnMsg.lateFeeAmt_IH);
            // Invoice Line
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                // Mod Start 2016/08/22 QC#12830
                //int remainder = i % INT_6;
                //if (remainder == 0) {
                if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.A.no(i).xxGrpFlg_A1.getValue())) {
                    // Mod End 2016/08/22 QC#12830
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).serNum_A1);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).ovrdSerNum_A1);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).startDt_A1);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).endDt_A1);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).baseAmt_A1);
                }
                scrnMsg.addCheckItem(scrnMsg.A.no(i).startReadMtrCnt_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).endReadMtrCnt_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).apTolAmt_A1);
            }
            // Comment
            scrnMsg.addCheckItem(scrnMsg.invCmntTxt_CO);
            scrnMsg.addCheckItem(scrnMsg.apAdjAmt_CO);
            scrnMsg.addCheckItem(scrnMsg.apAdjRsnCd_CO);
            scrnMsg.putErrorScreen();
            // Invoice Line
            if (scrnMsg.A.getValidCount() == 0) {
                scrnMsg.setMessageInfo(NFAM0033E, new String[] {"Serial"});
                throw new EZDFieldErrorException();
            }
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                // Mod Start 2016/08/22 QC#12830
                //int remainder = i % INT_6;
                //if (remainder == 0) {
                if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.A.no(i).xxGrpFlg_A1.getValue())) {
                    // Mod End 2016/08/22 QC#12830
                    // Check Start Date and End Date
                    if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).startDt_A1)
                    &&  ZYPCommonFunc.hasValue(scrnMsg.A.no(i).endDt_A1)
                    &&  Integer.parseInt(scrnMsg.A.no(i).startDt_A1.getValue()) >= Integer.parseInt(scrnMsg.A.no(i).endDt_A1.getValue())) {
                        scrnMsg.A.no(i).startDt_A1.setErrorInfo(1, NMAM8061E, new String[] {"End Date", "Start Date"});
                        scrnMsg.A.no(i).endDt_A1.setErrorInfo(1, NMAM8061E, new String[] {"End Date", "Start Date"});                    
                    }
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).serNum_A1);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).ovrdSerNum_A1);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).startDt_A1);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).endDt_A1);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).baseAmt_A1);
                    /*
                    if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).cntrTpCd_A1)) {
                        scrnMsg.A.no(i).cntrTpCd_A1.setErrorInfo(1, ZZM9000E, new String[] {"Counter Type"});
                    }
                    if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).startReadMtrCnt_A1)) {
                        scrnMsg.A.no(i).startReadMtrCnt_A1.setErrorInfo(1, ZZM9000E, new String[] {"Start MTR"});	
                    }
                    if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).endReadMtrCnt_A1)) {
                        scrnMsg.A.no(i).endReadMtrCnt_A1.setErrorInfo(1, ZZM9000E, new String[] {"End MTR"});	
                    }
                    if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).apTolAmt_A1)) {
                        scrnMsg.A.no(i).apTolAmt_A1.setErrorInfo(1, ZZM9000E, new String[] {"Overage"});	
                    }
                    */
                    if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).cntrTpCd_A1)) {
                        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).startReadMtrCnt_A1)) {
                            scrnMsg.A.no(i).startReadMtrCnt_A1.setErrorInfo(1, ZZM9000E, new String[] {"Start MTR"});	
                        }
                        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).endReadMtrCnt_A1)) {
                            scrnMsg.A.no(i).endReadMtrCnt_A1.setErrorInfo(1, ZZM9000E, new String[] {"End MTR"});	
                        }
                        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).apTolAmt_A1)) {
                            scrnMsg.A.no(i).apTolAmt_A1.setErrorInfo(1, ZZM9000E, new String[] {"Overage"});	
                        }
                    }
                } else {
                    // Check 2nd-6th lines.
                    if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).cntrTpCd_A1)
                    ||  (
                            ZYPCommonFunc.hasValue(scrnMsg.A.no(i).startReadMtrCnt_A1)
                        &&  scrnMsg.A.no(i).startReadMtrCnt_A1.getValue().compareTo(BigDecimal.ZERO) != 0
                        )
                    ||  (
                            ZYPCommonFunc.hasValue(scrnMsg.A.no(i).endReadMtrCnt_A1)
                        &&  scrnMsg.A.no(i).endReadMtrCnt_A1.getValue().compareTo(BigDecimal.ZERO) != 0
                        )
                    ||  (
                            ZYPCommonFunc.hasValue(scrnMsg.A.no(i).apTolAmt_A1)
                        &&  scrnMsg.A.no(i).apTolAmt_A1.getValue().compareTo(BigDecimal.ZERO) != 0
                        )
                    ) {
                        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).cntrTpCd_A1)) {
                            scrnMsg.A.no(i).cntrTpCd_A1.setErrorInfo(1, NAAM0041E, new String[] {"Counter Type", "this line"});
                        }
                        /*
                        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).startReadMtrCnt_A1)
                        ||  scrnMsg.A.no(i).startReadMtrCnt_A1.getValue().compareTo(BigDecimal.ZERO) == 0) {
                            scrnMsg.A.no(i).startReadMtrCnt_A1.setErrorInfo(1, NAAM0041E, new String[] {"Start MTR", "this line"});
                        }
                        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).endReadMtrCnt_A1)
                        ||  scrnMsg.A.no(i).endReadMtrCnt_A1.getValue().compareTo(BigDecimal.ZERO) == 0) {
                            scrnMsg.A.no(i).endReadMtrCnt_A1.setErrorInfo(1, NAAM0041E, new String[] {"End MTR", "this line"});
                        }
                        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).apTolAmt_A1)
                        ||  scrnMsg.A.no(i).apTolAmt_A1.getValue().compareTo(BigDecimal.ZERO) == 0) {
                            scrnMsg.A.no(i).apTolAmt_A1.setErrorInfo(1, NAAM0041E, new String[] {"Overage", "this line"});
                        }
                        */
                        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).startReadMtrCnt_A1)) {
                            scrnMsg.A.no(i).startReadMtrCnt_A1.setErrorInfo(1, NAAM0041E, new String[] {"Start MTR", "this line"});
                        }
                        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).endReadMtrCnt_A1)) {
                            scrnMsg.A.no(i).endReadMtrCnt_A1.setErrorInfo(1, NAAM0041E, new String[] {"End MTR", "this line"});
                        }
                        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).apTolAmt_A1)) {
                            scrnMsg.A.no(i).apTolAmt_A1.setErrorInfo(1, NAAM0041E, new String[] {"Overage", "this line"});
                        }
                    }
                }
                // Check Meter Count
                int readMtrCnt = scrnMsg.A.no(i).endReadMtrCnt_A1.getValueInt() - scrnMsg.A.no(i).startReadMtrCnt_A1.getValueInt();
                if (readMtrCnt < 0) {
                    scrnMsg.A.no(i).startReadMtrCnt_A1.setErrorInfo(1, NTAM0128E, new String[] {"End MTR", "Start MTR"});
                    scrnMsg.A.no(i).endReadMtrCnt_A1.setErrorInfo(1, NTAM0128E, new String[] {"End MTR", "Start MTR"});
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).startReadMtrCnt_A1);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).endReadMtrCnt_A1);
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).readMtrCnt_A1, new BigDecimal(readMtrCnt));
                }
                scrnMsg.addCheckItem(scrnMsg.A.no(i).cntrTpCd_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).startReadMtrCnt_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).endReadMtrCnt_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).apTolAmt_A1);
            }
            scrnMsg.putErrorScreen();
            // Same Couter in one serial error
            List<Map<String,String>> checkList = new ArrayList<Map<String,String>>();
            List<Map<String,String>> errorList = new ArrayList<Map<String,String>>();
            String currentSerNum = EMPTY_STRING;
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                // Mod Start 2016/08/22 QC#12830
                //int remainder = i % INT_6;
                //if (remainder == 0) {
                if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.A.no(i).xxGrpFlg_A1.getValue())) {
                    // Mod End 2016/08/22 QC#12830
                    currentSerNum = scrnMsg.A.no(i).serNum_A1.getValue();
                }
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).cntrTpCd_A1.getValue())) {
                    Map<String, String> mapSerNum = new HashMap<String, String>();
                    mapSerNum.put(SER_NUM, currentSerNum);
                    mapSerNum.put(CNT_TP_CD,  scrnMsg.A.no(i).cntrTpCd_A1.getValue());
                    if (checkList.contains(mapSerNum)) {
                        errorList.add(mapSerNum);
                    } else {
                        checkList.add(mapSerNum);
                    }
                }
            }
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                // Mod Start 2016/08/22 QC#12830
                //int remainder = i % INT_6;
                //if (remainder == 0) {
                if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.A.no(i).xxGrpFlg_A1.getValue())) {
                    // Mod End 2016/08/22 QC#12830
                    currentSerNum = scrnMsg.A.no(i).serNum_A1.getValue();
                }
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).cntrTpCd_A1.getValue())) {
                    Map<String, String> mapSerNum = new HashMap<String, String>();
                    mapSerNum.put(SER_NUM, currentSerNum);
                    mapSerNum.put(CNT_TP_CD,  scrnMsg.A.no(i).cntrTpCd_A1.getValue());
                    if (errorList.contains(mapSerNum)) {
                        scrnMsg.A.no(i).cntrTpCd_A1.setErrorInfo(1, NLBM1004E, new String[] {"Counter", "One serial number"});
                        scrnMsg.addCheckItem(scrnMsg.A.no(i).cntrTpCd_A1);
                    }
                }
            }
            scrnMsg.putErrorScreen();
            // Already exists same invoice # under batch.
            for (int i = 0; i < scrnMsg.Y.getValidCount(); i++) {
                if (scrnMsg.apInvNum_IH.getValue().equals(scrnMsg.Y.no(i).apInvNum_Y1.getValue())
                &&  scrnMsg.apVndCd_HD.getValue().equals(scrnMsg.Y.no(i).apVndCd_Y1.getValue())
                ) {
                    if (i != scrnMsg.xxListNum_Y.getValueInt()) {
                        scrnMsg.apInvNum_IH.setErrorInfo(1, NFBM0041E, new String[] {"Invoice #"});
                        scrnMsg.vndSiteNm_IH.setErrorInfo(1, NFBM0041E, new String[] {"Supplier Site"});
                        scrnMsg.addCheckItem(scrnMsg.apInvNum_IH);
                        scrnMsg.addCheckItem(scrnMsg.vndSiteNm_IH);
                    }
                }
            }
            scrnMsg.putErrorScreen();
            // Check current invoice amount
            if (!ZYPCommonFunc.hasValue(scrnMsg.apInvAmt_IH.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.apInvAmt_IH, BigDecimal.ZERO);
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.apMiscAmt_IH.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.apMiscAmt_IH, BigDecimal.ZERO);
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.apTaxAmt_IH.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.apTaxAmt_IH, BigDecimal.ZERO);
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.lateFeeAmt_IH.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.lateFeeAmt_IH, BigDecimal.ZERO);
            }
            BigDecimal bdTotAmt = BigDecimal.ZERO;
            bdTotAmt = bdTotAmt.add(scrnMsg.apMiscAmt_IH.getValue());
            bdTotAmt = bdTotAmt.add(scrnMsg.apTaxAmt_IH.getValue());
            bdTotAmt = bdTotAmt.add(scrnMsg.lateFeeAmt_IH.getValue());
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).baseAmt_A1.getValue())) {
                    bdTotAmt = bdTotAmt.add(scrnMsg.A.no(i).baseAmt_A1.getValue());
                }
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).apTolAmt_A1.getValue())) {
                    bdTotAmt = bdTotAmt.add(scrnMsg.A.no(i).apTolAmt_A1.getValue());
                }
            }
            if (scrnMsg.apInvAmt_IH.getValue().compareTo(bdTotAmt) != 0) {
                scrnMsg.apInvAmt_IH.setErrorInfo(1, NFBM0041E, new String[] {"Invoice Amount"});
                scrnMsg.addCheckItem(scrnMsg.apInvAmt_IH);
            }
            scrnMsg.putErrorScreen();

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // Mod Start 2016/08/22 QC#12830
            // if (!scrnMsg.A.no(i).xxChkBox_A1.isInputProtected()) {
            if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.A.no(i).xxGrpFlg_A1.getValue())) {
                // Mod End 2016/08/22 QC#12830
                if (Integer.parseInt(scrnMsg.A.no(i).startDt_A1.getValue()) > Integer.parseInt(scrnMsg.A.no(i).endDt_A1.getValue())) {
                    scrnMsg.A.no(i).startDt_A1.setErrorInfo(1, NMAM8061E, new String[] {"End Date", "Start Date" });
                    scrnMsg.A.no(i).endDt_A1.setErrorInfo(1, NMAM8061E, new String[] {"End Date", "Start Date"});
                        scrnMsg.addCheckItem(scrnMsg.A.no(i).startDt_A1);
                        scrnMsg.addCheckItem(scrnMsg.A.no(i).endDt_A1);
                    }
                }
                int readMtrCnt = scrnMsg.A.no(i).endReadMtrCnt_A1.getValueInt() - scrnMsg.A.no(i).startReadMtrCnt_A1.getValueInt();
                if (readMtrCnt < 0) {
                    scrnMsg.A.no(i).startReadMtrCnt_A1.setErrorInfo(1, NTAM0128E, new String[] {"End MTR", "Start MTR"});
                    scrnMsg.A.no(i).endReadMtrCnt_A1.setErrorInfo(1, NTAM0128E, new String[] {"End MTR", "Start MTR"});
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).startReadMtrCnt_A1);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).endReadMtrCnt_A1);
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).readMtrCnt_A1, new BigDecimal(readMtrCnt));
                }
                scrnMsg.addCheckItem(scrnMsg.A.no(i).startReadMtrCnt_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).endReadMtrCnt_A1);
            }
            scrnMsg.putErrorScreen();
        // START 2016/08/05 K.Kojima [QC#12690,DEL]
        // }
        // END 2016/08/05 K.Kojima [QC#12690,DEL]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL1110BMsg scrnMsg = (NFBL1110BMsg) bMsg;

        NFBL1110CMsg bizMsg = new NFBL1110CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_40);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL1110BMsg scrnMsg = (NFBL1110BMsg) bMsg;
        NFBL1110CMsg bizMsg  = (NFBL1110CMsg) cMsg;

        if (ERROR_STR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // QC#21686 Start
        scrnMsg.addCheckItem(scrnMsg.apInvNum_IH);
        // QC#21686 End
        scrnMsg.addCheckItem(scrnMsg.prntVndCd_IH);
        scrnMsg.addCheckItem(scrnMsg.vndSiteNm_IH);
        scrnMsg.addCheckItem(scrnMsg.apCtrlAmt_BA);
        scrnMsg.addCheckItem(scrnMsg.apCtrlCnt_BA);
        // START 2016/08/04 J.Kim [QC#12712,ADD]
        scrnMsg.addCheckItem(scrnMsg.invDt_IH);
        // END 2016/08/04 J.Kim [QC#12712,ADD]
        // ADD START 2016/08/05 QC#12692
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++ ) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).serNum_A1);
        }
        // ADD END 2016/08/05 QC#12692
        scrnMsg.putErrorScreen();

        /** Initialize input control */ 
        NFBL1110CommonLogic.initControl(this, scrnMsg);
        /** Set focus when opening screen */
        scrnMsg.setFocusItem(scrnMsg.apBatNum_BA);

    }
}