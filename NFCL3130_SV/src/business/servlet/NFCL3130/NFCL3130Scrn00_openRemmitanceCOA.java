/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3130;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL3130.NFCL3130CMsg;
//import business.servlet.NFCL3130.constant.NFCL3130Constant;

import business.blap.NFCL3130.NFCL3130CMsg;
import business.servlet.NFCL3130.constant.NFCL3130Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/07/14   Hitachi         K.Kojima        Update          QC#11576
 *</pre>
 */
public class NFCL3130Scrn00_openRemmitanceCOA extends S21CommonHandler implements NFCL3130Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3130BMsg scrnMsg = (NFCL3130BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3130BMsg scrnMsg = (NFCL3130BMsg) bMsg;

        //NFCL3130CMsg bizMsg = new NFCL3130CMsg();
        //bizMsg.setBusinessID("NFCL3130");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3130BMsg scrnMsg = (NFCL3130BMsg) bMsg;
        NFCL3130CMsg bizMsg  = (NFCL3130CMsg) cMsg;
        scrnMsg.appFuncId.setValue(COA_EVENT.NFCL3130_RMTC.toString());

        Object[] param = new Object[10];
        param[0] = scrnMsg.appFuncId;
        param[1] = scrnMsg.remCoaCmpyCd;
        param[2] = scrnMsg.remCoaAfflCd;
        // START 2016/07/14 K.Kojima [QC#11576,MOD]
        // param[3] = scrnMsg.remCoaCcCd;
        // param[4] = scrnMsg.remCoaBrCd;
        param[3] = scrnMsg.remCoaBrCd;
        param[4] = scrnMsg.remCoaCcCd;
        // END 2016/07/14 K.Kojima [QC#11576,MOD]
        param[5] = scrnMsg.remCoaAcctCd;
        param[6] = scrnMsg.remCoaProdCd;
        param[7] = scrnMsg.remCoaChCd;
        param[8] = scrnMsg.remCoaProjCd;
        param[9] = scrnMsg.remCoaExtnCd;

        setArgForSubScreen(param);

    }
}
