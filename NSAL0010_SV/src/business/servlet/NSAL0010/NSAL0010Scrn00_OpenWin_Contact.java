/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0010.NSAL0010CMsg;
import business.servlet.NSAL0010.common.NSAL0010CommonLogic;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/14   Hitachi         K.Kasai         Create          N/A
 * 2015/11/16   Hitachi         T.Tomita        Update          QC#647
 * 2016/02/25   Hitachi         T.Tomita        Update          QC#2690
 * 2016/04/15   Hitachi         T.Tomita        Update          QC#1041
 * 2016/04/19   Hitachi         T.Tomita        Update          QC#6223
 * 2016/06/30   Hitachi         T.Tomita        Update          QC#11140
 * 2017/10/18   CITS            M.Naito         Update          QC#20246
 *</pre>
 */
public class NSAL0010Scrn00_OpenWin_Contact extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // mod start 2016/04/19 CSA Defect#6223
        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;

        NSAL0010CMsg bizMsg = new NSAL0010CMsg();
        bizMsg.setBusinessID("NSAL0010");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // mod end 2016/04/19 CSA Defect#6223
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
        NSAL0010CommonLogic.clearPopupParameter(scrnMsg);
        // START 2015/11/16 T.Tomita [QC#647, MOD]
        clearContactPopupParams(scrnMsg);
        // START 2016/02/25 T.Tomita [QC#2690, MOD]
        // START 2017/10/18 M.Naito [QC#20246, MOD]
//        setValue(scrnMsg.xxPopPrm_00, CTAC_TP.IB_CONTACT);
        setValue(scrnMsg.xxPopPrm_00, CTAC_TP.DELIVERY_INSTALL);
        // END 2017/10/18 M.Naito [QC#20246, MOD]
        setValue(scrnMsg.xxPopPrm_03, scrnMsg.curLocAcctNum_M3);
        // add start 2016/04/15 CSA Defect#1041
        setValue(scrnMsg.xxPopPrm_14, scrnMsg.serNum_H1);
        // add end 2016/04/15 CSA Defect#1041
        // add start 2016/06/30 CSA Defect#11140
        setValue(scrnMsg.xxPopPrm_12, ZYPConstant.FLG_ON_Y);
        // add end 2016/06/30 CSA Defect#11140
        Object[] params = new Object[18];
        params[0] = scrnMsg.xxPopPrm_00;
        params[1] = scrnMsg.xxPopPrm_01;
        params[2] = scrnMsg.xxPopPrm_02;
        params[3] = scrnMsg.xxPopPrm_03;
        params[4] = scrnMsg.xxPopPrm_04;
        params[5] = scrnMsg.xxPopPrm_05;
        params[6] = scrnMsg.xxPopPrm_06;
        params[7] = scrnMsg.xxPopPrm_07;
        params[8] = scrnMsg.xxPopPrm_08;
        params[9] = scrnMsg.xxPopPrm_09;
        params[10] = scrnMsg.xxPopPrm_10;
        params[11] = scrnMsg.xxPopPrm_11;
        params[12] = scrnMsg.xxPopPrm_12;
        params[13] = scrnMsg.xxPopPrm_13;
        params[14] = scrnMsg.xxPopPrm_14;
        params[15] = scrnMsg.xxPopPrm_15;
        params[16] = scrnMsg.xxPopPrm_16;
        params[17] = scrnMsg.Y;
        // END 2016/02/25 T.Tomita [QC#2690, MOD]
        // END 2015/11/16 T.Tomita [QC#647, MOD]
        setArgForSubScreen(params);
    }

    private void clearContactPopupParams(NSAL0010BMsg scrnMsg) {
        scrnMsg.dsCtacPntPk_PA.clear();
        scrnMsg.dsCtacPntPk_PB.clear();
        scrnMsg.dsCtacPntPk_PC.clear();
        scrnMsg.ctacPsnPk_PA.clear();

        ZYPTableUtil.clear(scrnMsg.Y);
    }
}
