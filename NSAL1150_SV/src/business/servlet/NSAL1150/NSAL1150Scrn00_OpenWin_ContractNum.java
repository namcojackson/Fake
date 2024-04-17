/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1150;

import static business.servlet.NSAL1150.constant.NSAL1150Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL1150.common.NSAL1150CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/20   Hitachi         T.Kanasaka      Create          N/A
 *</pre>
 */
public class NSAL1150Scrn00_OpenWin_ContractNum extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1150BMsg scrnMsg = (NSAL1150BMsg) bMsg;
        NSAL1150CommonLogic.clearPopupParam(scrnMsg);

        String dsContrNum = null;
        if (hasValue(scrnMsg.condSqlTxt_CO)) {
            dsContrNum = scrnMsg.condSqlTxt_CO.getValue().split(SRCH_COND_SEPT)[0];
            if (dsContrNum.length() > LEN_DS_CONTR_NUM) {
                dsContrNum = null;
            }
        }
        setValue(scrnMsg.xxPopPrm_00, dsContrNum);

        Object[] prm = new Object[12];
        prm[0] = scrnMsg.xxPopPrm_00;
        prm[1] = scrnMsg.xxPopPrm_01;
        prm[2] = scrnMsg.xxPopPrm_02;
        prm[3] = scrnMsg.xxPopPrm_03;
        prm[4] = scrnMsg.xxPopPrm_04;
        prm[5] = scrnMsg.xxPopPrm_05;
        prm[6] = scrnMsg.xxPopPrm_06;
        prm[7] = scrnMsg.xxPopPrm_07;
        prm[8] = scrnMsg.xxPopPrm_08;
        prm[9] = scrnMsg.xxPopPrm_09;
        prm[10] = scrnMsg.xxPopPrm_10;
        prm[11] = scrnMsg.xxPopPrm_11;
        setArgForSubScreen(prm);
    }
}
