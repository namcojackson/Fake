/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3120;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL3120.NFCL3120CMsg;
//import business.servlet.NFCL3120.constant.NFCL3120Constant;

import business.blap.NFCL3120.NFCL3120CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/08   Fujitsu         T.Tanaka        Create          N/A
 *</pre>
 */
public class NFCL3120_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3120BMsg scrnMsg = (NFCL3120BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3120BMsg scrnMsg = (NFCL3120BMsg) bMsg;

        NFCL3120CMsg bizMsg = new NFCL3120CMsg();
        bizMsg.setBusinessID("NFCL3120");
        bizMsg.setFunctionCode("20");

        
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankAcctNum_H1, scrnMsg.Z.no(0).xxComnScrColValTxt.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankAcctNm_H1, scrnMsg.Z.no(1).xxComnScrColValTxt.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankBrNm_H1, scrnMsg.Z.no(2).xxComnScrColValTxt.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.bankRteNum_H1, scrnMsg.Z.no(3).xxComnScrColValTxt.getValue());
        
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3120BMsg scrnMsg = (NFCL3120BMsg) bMsg;
        NFCL3120CMsg bizMsg  = (NFCL3120CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
