/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0990;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0990.common.NSAL0990CommonLogic;
import business.servlet.NSAL0990.constant.NSAL0990Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/20   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSAL0990Scrn00_MoveWin_MemoEntry extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;

        NSAL0990CommonLogic.clearPopUpParam(scrnMsg);
        setParams(scrnMsg);

        Object[] params = new Object[NSAL0990Constant.PRM_NSBL0160];
        int i = 0;

        params[i++] = scrnMsg.xxPopPrm_P0;
        params[i++] = scrnMsg.xxPopPrm_P1;
        params[i++] = scrnMsg.xxPopPrm_P2;
        params[i++] = scrnMsg.xxPopPrm_P3;
        params[i++] = scrnMsg.xxPopPrm_P4;
        params[i++] = scrnMsg.xxPopPrm_P5;
        params[i++] = scrnMsg.xxPopPrm_P6;
        params[i++] = scrnMsg.xxPopPrm_P7;
        params[i++] = scrnMsg.xxPopPrm_P8;
        params[i++] = scrnMsg.xxPopPrm_P9;
        params[i++] = scrnMsg.xxPopPrm_PA;
        params[i++] = scrnMsg.xxPopPrm_PB;
        params[i++] = scrnMsg.xxPopPrm_PC;
        params[i++] = scrnMsg.xxPopPrm_PD;
        params[i++] = scrnMsg.xxPopPrm_PE;
        params[i++] = scrnMsg.xxPopPrm_PF;
        params[i++] = scrnMsg.xxPopPrm_PG;
        params[i++] = scrnMsg.xxPopPrm_PH;
        params[i++] = scrnMsg.xxPopPrm_PI;
        params[i++] = scrnMsg.xxPopPrm_PJ;
        params[i++] = scrnMsg.xxPopPrm_PK;
        params[i++] = scrnMsg.xxPopPrm_PL;
        params[i++] = scrnMsg.xxPopPrm_PM;

        setArgForSubScreen(params);
    }

    private void setParams(NSAL0990BMsg scrnMsg) {
        setValue(scrnMsg.xxPopPrm_P0, SVC_MEMO_CATG.ACCOUNT_MEMO);
        setValue(scrnMsg.xxPopPrm_P1, SVC_MEMO_TP.SUPPLY_ORDER);
        setValue(scrnMsg.xxPopPrm_P2, "Account#");
        setValue(scrnMsg.xxPopPrm_P3, scrnMsg.ownrAcctNum.getValue());
        setValue(scrnMsg.xxPopPrm_PC, "SVC_MEMO_TRX_NUM");
        setValue(scrnMsg.xxPopPrm_PD, scrnMsg.ownrAcctNum.getValue());
        setValue(scrnMsg.xxPopPrm_PE, "SVC_MEMO_TRX_NM");
        setValue(scrnMsg.xxPopPrm_PF, "DS_ACCT_CUST_PK");
        setValue(scrnMsg.xxPopPrm_PG, "DS_CONTR_PK");
        setValue(scrnMsg.xxPopPrm_PH, scrnMsg.dsContrPk.getValue().toString());
        setValue(scrnMsg.xxPopPrm_PI, "");
        setValue(scrnMsg.xxPopPrm_PJ, "");
        setValue(scrnMsg.xxPopPrm_PK, "");
        setValue(scrnMsg.xxPopPrm_PL, "");
        setValue(scrnMsg.xxPopPrm_PM, ZYPConstant.FLG_OFF_N);
    }
}
