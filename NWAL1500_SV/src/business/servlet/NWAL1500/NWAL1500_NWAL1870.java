/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/18   Fujitsu         T.Yoshida       Create          N/A
 *</pre>
 */
public class NWAL1500_NWAL1870 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID("NWAL1500");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg);

        String executeSupersede = bizMsg.xxPopPrm_P0.getValue();
        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        if (ZYPConstant.FLG_ON_Y.equals(executeSupersede)) {
            String dsOrdPosnNum = scrnMsg.B.no(selectIndex).dsOrdPosnNum_LL.getValue();
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (dsOrdPosnNum.equals(scrnMsg.A.no(i).dsOrdPosnNum_LC.getValue())) {
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).svcConfigMstrPk_LC);
                }
            }
            scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).mdseCd_LL);
            scrnMsg.putErrorScreen();
        }

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).entCpoDtlDealSlsAmt_LL);
    }
}
