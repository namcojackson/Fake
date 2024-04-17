/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3130;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL3130.NFCL3130CMsg;
//import business.servlet.NFCL3130.constant.NFCL3130Constant;

import business.blap.NFCL3130.NFCL3130CMsg;
import business.servlet.NFCL3130.common.NFCL3130CommonLogic;
import business.servlet.NFCL3130.constant.NFCL3130Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NFCL3130_NMAL2550 extends S21CommonHandler implements NFCL3130Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3130BMsg scrnMsg = (NFCL3130BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3130BMsg scrnMsg = (NFCL3130BMsg) bMsg;
        NFCL3130CMsg bizMsg  = (NFCL3130CMsg) cMsg;
        
        String strNineSeg = NFCL3130CommonLogic.nineSegCoaLayout(scrnMsg); 

        if (scrnMsg.appFuncId.getValue().equals(COA_EVENT.NFCL3130_CASH.toString())) {
            scrnMsg.xxScrItem61Txt_C1.setValue(strNineSeg);
        }
        if (scrnMsg.appFuncId.getValue().equals(COA_EVENT.NFCL3130_RCPT.toString())) {
            scrnMsg.xxScrItem61Txt_C2.setValue(strNineSeg);
        }
        if (scrnMsg.appFuncId.getValue().equals(COA_EVENT.NFCL3130_RMTC.toString())) {
            scrnMsg.xxScrItem61Txt_C3.setValue(strNineSeg);
        }
        if (scrnMsg.appFuncId.getValue().equals(COA_EVENT.NFCL3130_UNAP.toString())) {
            scrnMsg.xxScrItem61Txt_C4.setValue(strNineSeg);
        }
        if (scrnMsg.appFuncId.getValue().equals(COA_EVENT.NFCL3130_UNDF.toString())) {
            scrnMsg.xxScrItem61Txt_C5.setValue(strNineSeg);
        }
        if (scrnMsg.appFuncId.getValue().equals(COA_EVENT.NFCL3130_OACC.toString())) {
            scrnMsg.xxScrItem61Txt_C6.setValue(strNineSeg);
        }
        scrnMsg.xxSignaDataDescTxt.setValue(strNineSeg);

    }
}
