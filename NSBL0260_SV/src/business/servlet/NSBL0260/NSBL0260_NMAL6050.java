/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0260;

import static business.servlet.NSBL0260.constant.NSBL0260Constant.SELECT_POPUP_MDL_HEADER;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.SELECT_POPUP_MDL_HEADER_COMB;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.SELECT_POPUP_MDL_LINE;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0260.NSBL0260CMsg;


import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         Y.Tsuchimoto    Create          N/A
 *</pre>
 */
public class NSBL0260_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if (!"CMN_Close".equals(getLastGuard())) {
            NSBL0260BMsg scrnMsg = (NSBL0260BMsg) bMsg;
            NSBL0260CMsg bizMsg  = (NSBL0260CMsg) cMsg;

            if (bizMsg != null) {
                EZDMsg.copy(bizMsg, null, scrnMsg, null);
            }

            if (SELECT_POPUP_MDL_HEADER_COMB.equals(scrnMsg.xxPopPrm_SE.getValue())) {
                setValue(scrnMsg.mdlNm_C, scrnMsg.xxCondNm.getValue());
                scrnMsg.setFocusItem(scrnMsg.mdlNm_C);
            } else if (SELECT_POPUP_MDL_HEADER.equals(scrnMsg.xxPopPrm_SE.getValue())) {
                setValue(scrnMsg.mdlNm_H, scrnMsg.xxCondNm.getValue());
                scrnMsg.setFocusItem(scrnMsg.mdlNm_H);
            } else if (SELECT_POPUP_MDL_LINE.equals(scrnMsg.xxPopPrm_SE.getValue())) {
                int index = getButtonSelectNumber();
                setValue(scrnMsg.A.no(index).mdlNm_A, scrnMsg.xxCondNm.getValue());
                scrnMsg.setFocusItem(scrnMsg.A.no(index).mdlNm_A);
            }
        }
    }
}
