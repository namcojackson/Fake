/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.constant.NWAL1500Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/12   Fujitsu         T.Yoshida       Create          N/A
 * 2018/08/21   Fujitsu         S.Takami        Update          S21_NA#26767
 * </pre>
 */
public class NWAL1500Scrn00_OnBlur_DeriveFromSerial extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2018/08/21 S21_NA#26767 Del Start
//      return null;
      // 2018/08/21 S21_NA#26767 Del End
        // 2018/08/21 S21_NA#26767 Add Start
        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID("NWAL1500");
        bizMsg.setFunctionCode("20");
        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());

        EZDMsg.forceCopy(scrnMsg, bizMsg);
        return bizMsg;
        // 2018/08/21 S21_NA#26767 Add End
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        int selectIndex = getButtonSelectNumber();

        // 2018/08/21 S21_NA#26767 Add Start
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // 2018/08/21 S21_NA#26767 Add End
        // Clear Machine Master ID => S21_NA#26767 Clear or Set depending on BLAP.
        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            // 2018/08/21 S21_NA#26767 Del Start
//            scrnMsg.B.no(selectIndex).svcMachMstrPk_LL.clear();
            // 2018/08/21 S21_NA#26767 Del End
            scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).sbstMdseCd_LL);
        } else {
            // 2018/08/21 S21_NA#26767 Del Start
//            scrnMsg.D.no(selectIndex).svcMachMstrPk_RL.clear();
            // 2018/08/21 S21_NA#26767 Del End
            scrnMsg.setFocusItem(scrnMsg.D.no(selectIndex).custMdseCd_RL);
        }
    }
}
