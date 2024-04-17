/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0060;

import static business.servlet.NSAL0060.constant.NSAL0060Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 *
 * Model Group Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/08/2013   Hitachi         Y.Igarashi         Create          N/A
 *</pre>
 */
public class NSAL0060Scrn00_OpenWin_ModelList extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0060BMsg scrnMsg = (NSAL0060BMsg) bMsg;
        List<Integer> list = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_SR", ZYPConstant.CHKBOX_ON_Y);
        if (list == null || list.size() < 1) {
            scrnMsg.setMessageInfo(NSAM0015E);
            throw new EZDFieldErrorException();
        }
        if (list.size() > 1) {
            for (Integer target : list) {
                scrnMsg.A.no(target.intValue()).xxChkBox_SR.setErrorInfo(1, NSAM0132E);
                scrnMsg.addCheckItem(scrnMsg.A.no(target.intValue()).xxChkBox_SR);
            }
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0060BMsg scrnMsg = (NSAL0060BMsg) bMsg;

        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();

        List<Integer> list = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_SR", ZYPConstant.CHKBOX_ON_Y);
        setValue(scrnMsg.xxPopPrm_P6, scrnMsg.A.no(list.get(0).intValue()).mdlGrpNm_SR);

        // Open NSAL0070
        Object[] param = new Object[ARGS_POP_LENGTH];
        param[ARGS_POP_MDL_NM] = scrnMsg.xxPopPrm_P0;
        param[ARGS_POP_MDSE_CD] = scrnMsg.xxPopPrm_P1;
        param[ARGS_POP_MDL_TP_CD] = scrnMsg.xxPopPrm_P2;
        param[ARGS_POP_LTST_IND] = scrnMsg.xxPopPrm_P3;
        param[ARGS_POP_MDSE_NM] = scrnMsg.xxPopPrm_P4;
        param[ARGS_POP_MDSE_TP_CD] = scrnMsg.xxPopPrm_P5;
        param[ARGS_POP_MDL_GRP_NM] = scrnMsg.xxPopPrm_P6;

        setArgForSubScreen(param);
    }
}
