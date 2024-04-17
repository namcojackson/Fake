/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL0090;

import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_0;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_1;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_10;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_11;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_12;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_2;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_3;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_4;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_5;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_6;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_7;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_8;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_9;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NLBL0090 BOL Tracking
 * Function Name : Open WH popup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 4/07/2016   CITS       Yasushi Nomura       Create          N/A
 *</pre>
 */
public class NLBL0090Scrn00_OpenWin_WH extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NLBL0090BMsg scrnMsg = (NLBL0090BMsg) bMsg;
        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P5, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.whCd_H1);
        scrnMsg.xxPopPrm_P7.clear();
        scrnMsg.xxPopPrm_P8.clear();
        scrnMsg.xxPopPrm_P9.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PA, ZYPConstant.FLG_OFF_N);
        scrnMsg.xxPopPrm_PB.clear();

        Object[] params = new Object[IDX_12];
        params[IDX_0] = scrnMsg.xxPopPrm_P0;
        params[IDX_1] = scrnMsg.xxPopPrm_P1;
        params[IDX_2] = scrnMsg.xxPopPrm_P2;
        params[IDX_3] = scrnMsg.xxPopPrm_P3;
        params[IDX_4] = scrnMsg.xxPopPrm_P4;
        params[IDX_5] = scrnMsg.xxPopPrm_P5;
        params[IDX_6] = scrnMsg.xxPopPrm_P6;
        params[IDX_7] = scrnMsg.xxPopPrm_P7;
        params[IDX_8] = scrnMsg.xxPopPrm_P8;
        params[IDX_9] = scrnMsg.xxPopPrm_P9;
        params[IDX_10] = scrnMsg.xxPopPrm_PA;
        params[IDX_11] = scrnMsg.xxPopPrm_PB;

        setArgForSubScreen(params);
    }
}
