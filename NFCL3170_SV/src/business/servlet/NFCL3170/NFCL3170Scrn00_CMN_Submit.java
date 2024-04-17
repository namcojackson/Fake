/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3170;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFCL3170.common.NFCL3170CommonLogic;
import business.servlet.NFCL3170.constant.NFCL3170Constant;

import business.blap.NFCL3170.NFCL3170CMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BANK_ACCT_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   Fujitsu         T.Tanaka        Update          Def#2625
 * 2016/02/29   Fujitsu         T.Tanaka        Update          Def#5144
 * 2016/05/27   Fujitsu         S.Fujita        Update          Def#8534
 * 2016/12/16   Fujitsu         T.Murai         Update          QC#16533
 * 2018/01/12   Fujitsu         T.Murai         Update          QC#21290
 *</pre>
 */
public class NFCL3170Scrn00_CMN_Submit extends S21CommonHandler implements NFCL3170Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3170BMsg scrnMsg = (NFCL3170BMsg) bMsg;

        NFCL3170CommonLogic.chkecItem_Submit(scrnMsg);
        if(scrnMsg.xxBizAppId.getValue().equals(BUSINESS_ID)) {
            if(ZYPCommonFunc.hasValue(scrnMsg.effFromDt_H1.getValue())) {
                if(ZYPDateUtil.compare(scrnMsg.effFromDt_H1.getValue(), scrnMsg.procDt.getValue()) < 0) {
                    scrnMsg.effFromDt_H1.setErrorInfo(1, "NFCM0042E");
                }
            }
        }
        if(ZYPCommonFunc.hasValue(scrnMsg.effFromDt_H1.getValue()) && ZYPCommonFunc.hasValue(scrnMsg.effThruDt_H1.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);
            if(ZYPDateUtil.compare(scrnMsg.effFromDt_H1.getValue(), scrnMsg.effThruDt_H1.getValue()) > 0) {
                scrnMsg.effThruDt_H1.setErrorInfo(1, "NFCM0023E");
            }
        }
//        if(!ZYPCommonFunc.hasValue(scrnMsg.dsBankAcctPk_H1.getValue())) {
//            if(ZYPCommonFunc.hasValue(scrnMsg.effFromDt_H1.getValue()) && ZYPCommonFunc.hasValue(scrnMsg.effThruDt_H1.getValue())) {
//                scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);
//                if(ZYPDateUtil.compare(scrnMsg.effFromDt_H1.getValue(), scrnMsg.effThruDt_H1.getValue()) > 0) {
//                    scrnMsg.effThruDt_H1.setErrorInfo(1, "NFCM0023E");
//                }
//            }
//        }
//        if(ZYPCommonFunc.hasValue(scrnMsg.effFromDt_H1.getValue()) && ZYPCommonFunc.hasValue(scrnMsg.effFromDt_BK.getValue())) {
//            if(scrnMsg.effFromDt_H1.getValue().equals(scrnMsg.effFromDt_BK.getValue())) {
//                if(scrnMsg.effFromDt_H1.getValue().compareTo(scrnMsg.procDt.getValue()) < 0) {
//                    scrnMsg.effFromDt_H1.setErrorInfo(1, "NFCM0042E");
//                }
//            }
//        }
//        if(ZYPCommonFunc.hasValue(scrnMsg.cntyNm_H1.getValue())) {
//            scrnMsg.addCheckItem(scrnMsg.stCd_H1);
//        }
        scrnMsg.putErrorScreen();

        if(BigDecimal.ONE.compareTo(scrnMsg.xxRadioBtn.getValue()) == 0) {
            if(scrnMsg.A.getValidCount() < 1) {
                scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H1);
                scrnMsg.addCheckItem(scrnMsg.dsAcctNm_H1);
                scrnMsg.addCheckItem(scrnMsg.locNum_H1);
                // START 2016/05/27 S.Fujita [QC#8534,ADD]
                scrnMsg.dsAcctNum_H1.clearErrorInfo();
                scrnMsg.dsAcctNm_H1.clearErrorInfo();
                scrnMsg.locNum_H1.clearErrorInfo();
                // END   2016/05/27 S.Fujita [QC#8534,ADD]
                scrnMsg.dsAcctNum_H1.setErrorInfo(1, "NFCM0823E");
                scrnMsg.dsAcctNm_H1.setErrorInfo(1, "NFCM0823E");
                scrnMsg.locNum_H1.setErrorInfo(1, "NFCM0823E");
                // START 2016/05/27 S.Fujita [QC#8534,DEL]
//                scrnMsg.putErrorScreen();
                // END   2016/05/27 S.Fujita [QC#8534,DEL]
            }
        }
        // START 2016/05/27 S.Fujita [QC#8534,ADD]
        scrnMsg.addCheckItem(scrnMsg.firstLineAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.ctyAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.cntyNm_H1);
        scrnMsg.addCheckItem(scrnMsg.stCd_H1);
        scrnMsg.addCheckItem(scrnMsg.postCd_H1);

        scrnMsg.putErrorScreen();
        // END   2016/05/27 S.Fujita [QC#8534,ADD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3170BMsg scrnMsg = (NFCL3170BMsg) bMsg;

        NFCL3170CMsg bizMsg = new NFCL3170CMsg();
        bizMsg.setBusinessID("NFCL3170");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3170BMsg scrnMsg = (NFCL3170BMsg) bMsg;
        NFCL3170CMsg bizMsg  = (NFCL3170CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        //Def#5743
        scrnMsg.addCheckItem(scrnMsg.dsBankAcctNm_H1);
        scrnMsg.addCheckItem(scrnMsg.dsBankAcctNum_H1);
        scrnMsg.addCheckItem(scrnMsg.bankRteNum_H1);
        
        // START 2016/12/13 [QC#16533,ADD]
        scrnMsg.addCheckItem(scrnMsg.firstLineAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.scdLineAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.ctyAddr_H1);
        // END   2016/12/13 [QC#16533,ADD]
        scrnMsg.addCheckItem(scrnMsg.postCd_H1);
        scrnMsg.addCheckItem(scrnMsg.stCd_H1);
        scrnMsg.addCheckItem(scrnMsg.cntyNm_H1);

        // START 2018/01/12 [QC#21290,ADD]
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
        }
        // END   2018/01/12 [QC#21290,ADD]
        scrnMsg.putErrorScreen();
        
        if(ZYPCommonFunc.hasValue(scrnMsg.dsBankAcctPk_H1.getValue())) {
            NFCL3170CommonLogic.initialize_Update(this, scrnMsg);
        } else {
            NFCL3170CommonLogic.initialize_Update(this, scrnMsg);
        }
        if(bizMsg.dsBankAcctTpCd_H1.getValue().equals(DS_BANK_ACCT_TP.INTERNAL)) {
            scrnMsg.dsAcctNum_H1.setInputProtected(true);
            scrnMsg.dsAcctNm_H1.setInputProtected(true);
            scrnMsg.locNum_H1.setInputProtected(true);
        }
    }
}
