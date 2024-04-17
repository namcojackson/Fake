/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_ITEM_NUM;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogic;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/29   Fujitsu         T.Yoshida       Create          N/A
 * 2016/02/18   Fujitsu         T.Ishii         Update          QC#1634
 * 2016/02/20   Fujitsu         Y.Kanefusa      Update          QC#3943
 * 2017/03/02   Fujitsu         S.Takami        Update          S21_NA#17714-2
 * 2018/07/12   Fujitsu         S.Takami        Update          S21_NA#26895-2
 *</pre>
 */
public class NWAL1500Scrn00_OnBlur_DeriveFromQty extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        int selectIndex = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(selectIndex);

        // Check Qty
        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).ordCustUomQty_LL);
        } else {
            scrnMsg.addCheckItem(scrnMsg.D.no(selectIndex).ordCustUomQty_RL);
        }
        scrnMsg.putErrorScreen();

        // Check MDSE
        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.B.no(selectIndex).mdseCd_LL.clearErrorInfo();
            if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIndex).mdseCd_LL)) {
                scrnMsg.B.no(selectIndex).mdseCd_LL.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_ITEM_NUM });
                scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).mdseCd_LL);
            }
        } else {
            scrnMsg.D.no(selectIndex).mdseCd_RL.clearErrorInfo();
            if (!ZYPCommonFunc.hasValue(scrnMsg.D.no(selectIndex).mdseCd_RL)) {
                scrnMsg.D.no(selectIndex).mdseCd_RL.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_ITEM_NUM });
                scrnMsg.addCheckItem(scrnMsg.D.no(selectIndex).mdseCd_RL);
            }
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        if (TAB_RMA.equals(scrnMsg.xxDplyTab.getValue())) {
            NWAL1500CommonLogic.setRmaQtyAsNegative(scrnMsg.D.no(scrnMsg.xxCellIdx.getValueInt()));
        }

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
        setResult(ZYPConstant.FLG_OFF_N);

        String dplyTab = scrnMsg.xxDplyTab.getValue();
        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).mdseCd_LL);
            scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).ordCustUomQty_LL); // 2017/03/02 S21_NA#17714-2 Add
        } else {
            scrnMsg.addCheckItem(scrnMsg.D.no(selectIndex).mdseCd_RL);
            scrnMsg.addCheckItem(scrnMsg.D.no(selectIndex).ordCustUomQty_RL); // 2017/03/02 S21_NA#17714-2 Add
        }
        scrnMsg.putErrorScreen();

        NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg);//add QC#3943 
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            Object[] params = NWAL1500CommonLogic.getParamNWAL1870(scrnMsg);
            setArgForSubScreen(params);
            return;
        }

        //NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg);  //Del QC#3943 
        // S21_NA#1634NWAL1500CommonLogic.controlMdseField(scrnMsg);

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            // 2018/07/12 S21_NA#26895-2 Mod Start
//            scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).ordCustUomQty_LL);
            scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).entCpoDtlDealSlsAmt_LL);
            // 2018/07/12 S21_NA#26895-2 Mod End
        } else {
            // 2018/07/12 S21_NA#26895-2 Mod Start
//            scrnMsg.setFocusItem(scrnMsg.D.no(selectIndex).ordCustUomQty_RL);
            scrnMsg.setFocusItem(scrnMsg.D.no(selectIndex).rtrnRsnCd_RL);
            // 2018/07/12 S21_NA#26895-2 Mod End
        }
    }
}
