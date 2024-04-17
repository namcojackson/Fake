/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/23   Fujitsu         T.Yoshida       Create          N/A
 * 2016/02/18   Fujitsu         T.Ishii         Update          QC#1634
 * 2016/09/28   Fujitsu         Y.Taoka         Update          S21_NA#9761
 * 2016/09/29   Fujitsu         Y.Taoka         Update          S21_NA#14805
 * 2018/06/07   Fujitsu         Y.Kanefusa      Update          S21_NA#26415
 *</pre>
 */
public class NWAL1500_NMAL6800 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID("NWAL1500");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            // S21_NA#9761 2016/09/28 ADD START
            NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.srchOrigItemFlg_MF.getValue())) {
                int slctLine = scrnMsg.xxCellIdx.getValueInt();
                if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(slctLine).mdseCd_LL, scrnMsg.xxPopPrm_P1);
                    scrnMsg.B.no(slctLine).srchOrigItemFlg_LL.clear(); // S21_NA#14805 ADD
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(slctLine).mdseCd_RL, scrnMsg.xxPopPrm_P1);
                    scrnMsg.D.no(slctLine).srchOrigItemFlg_RL.clear(); // S21_NA#26415 ADD
                }
            }
            // S21_NA#9761 2016/09/28 ADD END
            return;
        }

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg);

        int slctLine = scrnMsg.xxCellIdx.getValueInt();
        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.B.no(slctLine).mdseCd_LL);
        } else {
            scrnMsg.addCheckItem(scrnMsg.D.no(slctLine).mdseCd_RL);
        }
        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.B.no(slctLine).ordCustUomQty_LL);
        } else {
            scrnMsg.setFocusItem(scrnMsg.D.no(slctLine).ordCustUomQty_RL);
        }
    }
}
