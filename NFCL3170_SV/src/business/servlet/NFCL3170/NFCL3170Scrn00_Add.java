/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3170;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL3170.NFCL3170CMsg;
//import business.servlet.NFCL3170.constant.NFCL3170Constant;

import business.blap.NFCL3170.NFCL3170CMsg;
import business.servlet.NFCL3170.common.NFCL3170CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/05/27   Fujitsu         S.Fujita        Update          QC#8534
 *</pre>
 */
public class NFCL3170Scrn00_Add extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3170BMsg scrnMsg = (NFCL3170BMsg) bMsg;
        
        if(BigDecimal.ONE.compareTo(scrnMsg.xxRadioBtn.getValue()) == 0) {
            if(!ZYPCommonFunc.hasValue(scrnMsg.dsAcctNm_H1.getValue())) {
//                scrnMsg.setMessageInfo("NFCM0517E", new String[]{"Customer"});
//                throw new EZDFieldErrorException();
                scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H1);
                scrnMsg.addCheckItem(scrnMsg.dsAcctNm_H1);
                scrnMsg.addCheckItem(scrnMsg.locNum_H1);
                // START 2016/05/27 S.Fujita [QC#8534,DEL]
//                scrnMsg.dsAcctNum_H1.setErrorInfo(1, "NFCM0823E");
//                scrnMsg.dsAcctNm_H1.setErrorInfo(1, "NFCM0823E");
//                scrnMsg.locNum_H1.setErrorInfo(1, "NFCM0823E");
                // END   2016/05/27 S.Fujita [QC#8534,DEL]
                // START 2016/05/27 S.Fujita [QC#8534,ADD]
                scrnMsg.dsAcctNum_H1.clearErrorInfo();
                scrnMsg.dsAcctNm_H1.clearErrorInfo();
                scrnMsg.locNum_H1.clearErrorInfo();
                scrnMsg.dsAcctNum_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Customer Number"});
                scrnMsg.dsAcctNm_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Customer Name"});
                scrnMsg.locNum_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Site Number"});
                // END   2016/05/27 S.Fujita [QC#8534,ADD]
                scrnMsg.putErrorScreen();
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3170BMsg scrnMsg = (NFCL3170BMsg) bMsg;

        NFCL3170CMsg bizMsg = new NFCL3170CMsg();
        bizMsg.setBusinessID("NFCL3170");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3170BMsg scrnMsg = (NFCL3170BMsg) bMsg;
        NFCL3170CMsg bizMsg  = (NFCL3170CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        NFCL3170CommonLogic.setCustomerProtect(this, scrnMsg);
    }
}
