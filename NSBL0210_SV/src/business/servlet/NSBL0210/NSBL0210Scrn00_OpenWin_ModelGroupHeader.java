/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0210;

import static business.servlet.NSBL0210.constant.NSBL0210Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSBL0210Scrn00_OpenWin_ModelGroupHeader extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSBL0210BMsg scrnMsg = (NSBL0210BMsg) bMsg;
        setArgForSubScreen(setCommonPopUpParam(ctx, scrnMsg));
    }

    private Object[] setCommonPopUpParam(EZDApplicationContext ctx, NSBL0210BMsg scrnMsg) {

        // Event Name
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        // Table Name
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, PRM_VALUE_NMAL6050_TBL_NM);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, PRM_VALUE_NMAL6050_CD_COL_NM);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, PRM_VALUE_NMAL6050_NM_COL_NM);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_03, PRM_VALUE_NMAL6050_CD_COL_NM);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_04, PRM_VALUE_NMAL6050_SCR_NM);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_05, PRM_VALUE_NMAL6050_CD_LBL_NM);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_06, PRM_VALUE_NMAL6050_NM_LBL_NM);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_07, PRM_VALUE_NMAL6050_CD_LBL_NM);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_08, PRM_VALUE_NMAL6050_NM_LBL_NM);
        scrnMsg.xxPopPrm_09.clear();

        Object[] params = new Object[PRM_LENGTH_NMAL6050];
        int i = 0;
        params[i++] = scrnMsg.xxPopPrm_00;
        params[i++] = scrnMsg.xxPopPrm_01;
        params[i++] = scrnMsg.xxPopPrm_02;
        params[i++] = scrnMsg.xxPopPrm_03;
        params[i++] = scrnMsg.xxPopPrm_04;
        params[i++] = scrnMsg.xxPopPrm_05;
        params[i++] = scrnMsg.xxPopPrm_06;
        params[i++] = scrnMsg.xxPopPrm_07;
        params[i++] = scrnMsg.xxPopPrm_08;
        params[i++] = scrnMsg.mdlGrpNm_H;
        params[i++] = scrnMsg.xxPopPrm_09;

        return params;
    }
}
