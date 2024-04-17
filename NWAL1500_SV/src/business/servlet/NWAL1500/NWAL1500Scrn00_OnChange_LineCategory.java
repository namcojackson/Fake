/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.BIZ_ID;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_BUYOUT_BTN_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_BUYOUT_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_BUYOUT_LABEL;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_CATG;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1500Scrn00_OnChange_LineCategory
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/01   Fujitsu         S.Takami       Create          N/A
 * 2016/02/18   Fujitsu         T.Ishii         Update          QC#1634
 * 2022/08/01   CITS            F.Fadriquela    Update          QC#60273
 *</pre>
 */
public class NWAL1500Scrn00_OnChange_LineCategory extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        int idx = getButtonSelectNumber();
        scrnMsg.xxCellIdx_BB.setValue(idx);
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

        if (DS_ORD_LINE_CATG.LEASE_BUYOUT.equals(scrnMsg.dsOrdLineCatgCd_XX.getValue())) {
            setButtonProperties(BTN_BUYOUT_BTN_NM, BTN_BUYOUT_EVENT_NM, BTN_BUYOUT_LABEL, 1, null);
        } else {
            setButtonProperties(BTN_BUYOUT_BTN_NM, BTN_BUYOUT_EVENT_NM, BTN_BUYOUT_LABEL, 0, null);
        }

        NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg);
        // S21_NA#1634NWAL1500CommonLogic.controlMdseField(scrnMsg);

        // START 2022/08/01 F.Fadriquela [QC#60273, ADD]
        scrnMsg.addCheckItem(scrnMsg.B.no(scrnMsg.xxCellIdx_BB.getValueInt()).dsOrdLineCatgCd_LL);
        scrnMsg.putErrorScreen(); 
        // END 2022/08/01 F.Fadriquela [QC#60273, ADD]

        scrnMsg.setFocusItem(scrnMsg.B.no(scrnMsg.xxCellIdx_BB.getValueInt()).dsOrdLineCatgCd_LL);
    }
}
