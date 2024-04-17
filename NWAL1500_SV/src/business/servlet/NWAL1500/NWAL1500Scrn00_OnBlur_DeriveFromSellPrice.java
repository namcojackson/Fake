/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
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

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/19   Fujitsu         M.Nakamura      Create          N/A
 * 2016/09/07   Fujitsu         Y.Taoka         Update          S21_NA#7942
 *</pre>
 */
public class NWAL1500Scrn00_OnBlur_DeriveFromSellPrice extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        int selectIndex = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(selectIndex);

        // Check Qty
        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).ordCustUomQty_LL);
            scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).entCpoDtlDealSlsAmt_LL);
        } else {
            scrnMsg.addCheckItem(scrnMsg.D.no(selectIndex).ordCustUomQty_RL);
            scrnMsg.addCheckItem(scrnMsg.D.no(selectIndex).entCpoDtlDealSlsAmt_RL);
        }
        scrnMsg.putErrorScreen();

        // Check MDSE
        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).mdseCd_LL);
            if (scrnMsg.B.no(selectIndex).mdseCd_LL.isError()) {
                scrnMsg.B.no(selectIndex).ordCustUomQty_LL.clear();
                scrnMsg.B.no(selectIndex).entCpoDtlDealSlsAmt_LL.clear();
            }
        } else {
            scrnMsg.addCheckItem(scrnMsg.D.no(selectIndex).mdseCd_RL);
            if (scrnMsg.D.no(selectIndex).mdseCd_RL.isError()) {
                scrnMsg.D.no(selectIndex).ordCustUomQty_RL.clear();
                scrnMsg.D.no(selectIndex).entCpoDtlDealSlsAmt_RL.clear();
            }
        }
        scrnMsg.putErrorScreen();
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

        String dplyTab = scrnMsg.xxDplyTab.getValue();
        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).mdseCd_LL);
            scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).entCpoDtlDealSlsAmt_LL);
        } else {
            scrnMsg.addCheckItem(scrnMsg.D.no(selectIndex).mdseCd_RL);
            scrnMsg.addCheckItem(scrnMsg.D.no(selectIndex).entCpoDtlDealSlsAmt_RL);
        }
        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg);

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            if (scrnMsg.B.no(selectIndex).entCpoDtlDealSlsAmt_LL.getErrorCode() == 2) {
                scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).entCpoDtlDealSlsAmt_LL); // S21_NA#7942 ADD
            } else {
                scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).dsOrdLineCatgCd_LL);
            }
        } else {
            if (scrnMsg.D.no(selectIndex).entCpoDtlDealSlsAmt_RL.getErrorCode() == 2) {
                scrnMsg.setFocusItem(scrnMsg.D.no(selectIndex).entCpoDtlDealSlsAmt_RL);  // S21_NA#7942 ADD
            } else {
                scrnMsg.setFocusItem(scrnMsg.D.no(selectIndex).dsOrdLineCatgCd_RL);
            }
        }
    }
}
