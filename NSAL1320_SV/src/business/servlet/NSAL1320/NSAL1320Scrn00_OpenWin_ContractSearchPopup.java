/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1320;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL1320.NSAL1320BMsg;
import business.servlet.NSAL1320.common.NSAL1320CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1320Scrn00_OpenWin_ContractSearchPopup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/19   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1320Scrn00_OpenWin_ContractSearchPopup extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;
        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;

        // Clear Params
        scrnMsg.W.clear();
        //
        // Set Value
        //scrnMsg.XX.setValue(scrnMsg.YYY.getValue());
        //
        // Set Params
//        int ixA = scrnMsg.xxCellIdx.getValueInt();
//        EZDBBigDecimalItem xxPrmWk = new EZDBBigDecimalItem();
//
//        List<Object> itemList = new ArrayList<Object>(12);
//        itemList.add(scrnMsg.A.no(ixA).dsContrNum); // dsContrNum
//        itemList.add(scrnMsg.W.no(1).xxPopPrm_W);
//        itemList.add(scrnMsg.W.no(1).xxPopPrm_W);
//        itemList.add(scrnMsg.W.no(1).xxPopPrm_W);
//        itemList.add(scrnMsg.A.no(ixA).dsAcctNum); // dsAcctNum
//        itemList.add(scrnMsg.W.no(1).xxPopPrm_W);
//        itemList.add(scrnMsg.W.no(1).xxPopPrm_W);
//        itemList.add(scrnMsg.W.no(1).xxPopPrm_W); // serial#
//        itemList.add(scrnMsg.W.no(1).xxPopPrm_W);
//        itemList.add(scrnMsg.W.no(1).xxPopPrm_W);
//        itemList.add(scrnMsg.A.no(ixA).dsContrPk); // dsContrPk
//        itemList.add(xxPrmWk);

        Object[] params = NSAL1320CommonLogic.getNSAL0110Prm(scrnMsg, scrnMsg.xxCellIdx.getValueInt());
        //
        setArgForSubScreen(params);
    }
}
