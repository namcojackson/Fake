/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL6720.NMAL6720CMsg;
//import business.servlet.NMAL6720.constant.NMAL6720Constant;

import business.servlet.NMAL6720.common.NMAL6720CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/10/21   Fujitsu         C.Yokoi         Update          QC#14982
 *</pre>
 */
public class NMAL6720Scrn00_OnChange_ShipTo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

        if (scrnMsg.dsAcctRelnShipToFlg_SH.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.effFromDt_SH)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.effFromDt_SH, ZYPDateUtil.getSalesDate());
            }
        } else {
            if (!ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd_SH)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.effFromDt_SH, "");
                // 2016/10/21 CSA-QC#14982 Add Start
                scrnMsg.billToCustCd_SH.clear();
            }
        }
        NMAL6720CommonLogic.controlProtectShipTo(scrnMsg, this);
        // 2016/10/21 CSA-QC#14982 Add End
    }
}
