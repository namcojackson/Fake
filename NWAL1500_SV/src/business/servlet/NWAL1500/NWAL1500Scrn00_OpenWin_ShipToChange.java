/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.BIZ_ID;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_SHIP_TO_LOC;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0671E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/13   Fujitsu         T.Yoshida       Create          N/A
 *</pre>
 */
public class NWAL1500Scrn00_OpenWin_ShipToChange extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        String dplyTab = scrnMsg.xxDplyTab.getValue();
        int selectIdx = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIdx).shipToCustCd_LC)) {
                scrnMsg.A.no(selectIdx).shipToCustCd_LC.setErrorInfo(1, NWAM0671E, new String[] {MSG_PARAM_SHIP_TO_LOC });
                scrnMsg.addCheckItem(scrnMsg.A.no(selectIdx).shipToCustCd_LC);
            }
        } else if (TAB_RMA.equals(dplyTab)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.C.no(selectIdx).shipToCustCd_RC)) {
                scrnMsg.C.no(selectIdx).shipToCustCd_RC.setErrorInfo(1, NWAM0671E, new String[] {MSG_PARAM_SHIP_TO_LOC });
                scrnMsg.addCheckItem(scrnMsg.C.no(selectIdx).shipToCustCd_RC);
            }
        } else {
            if (!ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd)) {
                scrnMsg.shipToCustCd.setErrorInfo(1, NWAM0671E, new String[] {MSG_PARAM_SHIP_TO_LOC });
                scrnMsg.addCheckItem(scrnMsg.shipToCustCd);
            }
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        String dplyTab = scrnMsg.xxDplyTab.getValue();
        int selectIdx = scrnMsg.xxCellIdx.getValueInt();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            scrnMsg.addCheckItem(scrnMsg.A.no(selectIdx).shipToCustCd_LC);
        } else if (TAB_RMA.equals(dplyTab)) {
            scrnMsg.addCheckItem(scrnMsg.C.no(selectIdx).shipToCustCd_RC);
        } else {
            scrnMsg.addCheckItem(scrnMsg.shipToCustCd);
        }
        scrnMsg.putErrorScreen();

        Object[] params = NWAL1500CommonLogic.getParamNWAL0140(scrnMsg);
        setArgForSubScreen(params);
    }
}
