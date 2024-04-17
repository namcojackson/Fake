/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6860;

import static business.servlet.NMAL6860.constant.NMAL6860Constant.DISP_HIERARCHY_ACCT_ALL;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID   : NMAL6860 Supplier Entry
 * Function Name : The business process for Open Window Customer Number.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/03   CITS            M.Ouchi         Create          N/A
 *</pre>
 */
public class NMAL6860Scrn00_OpenWin_CustomerNumber extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6860BMsg scrnMsg = (NMAL6860BMsg) bMsg;

        // sets the parameter values.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_CA, scrnMsg.dsAcctNum_H1);  //Customer Number
        scrnMsg.xxPopPrm_CB.clear();
        scrnMsg.xxPopPrm_CC.clear();
        scrnMsg.xxPopPrm_CD.clear();
        scrnMsg.xxPopPrm_CE.clear();
        scrnMsg.xxPopPrm_CF.clear();
        scrnMsg.xxPopPrm_CG.clear();
        scrnMsg.xxPopPrm_CH.clear();
        scrnMsg.xxPopPrm_CI.clear();
        scrnMsg.xxPopPrm_CJ.clear();
        scrnMsg.xxPopPrm_CK.clear();
        scrnMsg.xxPopPrm_CL.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_CM, DISP_HIERARCHY_ACCT_ALL);
        scrnMsg.xxPopPrm_CN.clear();
        scrnMsg.xxPopPrm_CO.clear();
        scrnMsg.xxPopPrm_CP.clear();
        scrnMsg.xxPopPrm_CQ.clear();
        scrnMsg.xxPopPrm_CR.clear();
        scrnMsg.xxPopPrm_CS.clear();
        scrnMsg.xxPopPrm_CT.clear();
        scrnMsg.xxPopPrm_CU.clear();
        scrnMsg.xxPopPrm_CV.clear();
        scrnMsg.xxPopPrm_CW.clear();
        scrnMsg.xxPopPrm_CX.clear();

        // creates the parameter.
        Object[] params = new Object[24];

        params[0]  = scrnMsg.xxPopPrm_CA;
        params[1]  = scrnMsg.xxPopPrm_CB;
        params[2]  = scrnMsg.xxPopPrm_CC;
        params[3]  = scrnMsg.xxPopPrm_CD;
        params[4]  = scrnMsg.xxPopPrm_CE;
        params[5]  = scrnMsg.xxPopPrm_CF;
        params[6]  = scrnMsg.xxPopPrm_CG;
        params[7]  = scrnMsg.xxPopPrm_CH;
        params[8]  = scrnMsg.xxPopPrm_CI;
        params[9]  = scrnMsg.xxPopPrm_CJ;
        params[10] = scrnMsg.xxPopPrm_CK;
        params[11] = scrnMsg.xxPopPrm_CL;
        params[12] = scrnMsg.xxPopPrm_CM;
        params[13] = scrnMsg.xxPopPrm_CN;
        params[14] = scrnMsg.xxPopPrm_CO;
        params[15] = scrnMsg.xxPopPrm_CP;
        params[16] = scrnMsg.xxPopPrm_CQ;
        params[17] = scrnMsg.xxPopPrm_CR;
        params[18] = scrnMsg.xxPopPrm_CS;
        params[19] = scrnMsg.xxPopPrm_CT;
        params[20] = scrnMsg.xxPopPrm_CU;
        params[21] = scrnMsg.xxPopPrm_CV;
        params[22] = scrnMsg.xxPopPrm_CW;
        params[23] = scrnMsg.xxPopPrm_CX;

        setArgForSubScreen(params);
    }
}
