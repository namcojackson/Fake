/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.RESULT_PARAM_SPECIAL_INSTRUCTION;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogic;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForOrderEntryAction;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/05   Fujitsu         T.Yoshida       Create          N/A
 * 2016/02/09   Fujitsu         M.Mikio         Create          QC#1636
 * 2016/02/23   Fujitsu         Y.Taoka         Update          QC#1694
 * 2018/04/04   Fujitsu         S.Takami        Update          S21_NA#25152
 * 2018/07/27   Fujitsu         T.Noguchi       Update          S21_NA#14307
 *</pre>
 */
public class NWAL1500Scrn00_OnBlur_DeriveFromBillToName extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // 2018/04/04 S21_NA#25152 Add Start
        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        String dplyTab = scrnMsg.xxDplyTab.getValue();
        int selectIndex = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(selectIndex);
        EZDBStringItem strItem = null;

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            strItem = scrnMsg.A.no(selectIndex).billToCustAcctNm_LC;
        } else if (TAB_RMA.equals(dplyTab)) {
            strItem = scrnMsg.C.no(selectIndex).billToCustAcctNm_RC;
        } else {
            strItem = scrnMsg.billToCustAcctNm;
        }

        boolean rslt = NWAL1500CommonLogic.checkSerchKey(strItem);
        if (!rslt) {
            scrnMsg.addCheckItem(strItem);
            scrnMsg.putErrorScreen();
        }
        // 2018/04/04 S21_NA#25152 Add End
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        String dplyTab = scrnMsg.xxDplyTab.getValue();
        int selectIndex = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(selectIndex);

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIndex).billToCustAcctNm_LC)) {
                return null;
            }
        } else if (TAB_RMA.equals(dplyTab)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.C.no(selectIndex).billToCustAcctNm_RC)) {
                return null;
            }
        } else {
            if (!ZYPCommonFunc.hasValue(scrnMsg.billToCustAcctNm)) {
                return null;
            }
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
        setResult(ZYPConstant.FLG_OFF_N);

        String dplyTab = scrnMsg.xxDplyTab.getValue();
        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIndex).billToCustAcctNm_LC)) {
                scrnMsg.A.no(selectIndex).billToCustAcctCd_LC.clear();
                scrnMsg.A.no(selectIndex).billToCustCd_LC.clear();
                scrnMsg.A.no(selectIndex).billToCustLocAddr_LC.clear();
                scrnMsg.setFocusItem(scrnMsg.A.no(selectIndex).xxImageTxt_AD);
                return;
            }
        } else if (TAB_RMA.equals(dplyTab)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.C.no(selectIndex).billToCustAcctNm_RC)) {
                scrnMsg.C.no(selectIndex).billToCustAcctCd_RC.clear();
                scrnMsg.C.no(selectIndex).billToCustCd_RC.clear();
                scrnMsg.C.no(selectIndex).billToCustLocAddr_RC.clear();
                scrnMsg.setFocusItem(scrnMsg.C.no(selectIndex).xxImageTxt_CD);
                return;
            }
        } else {
            if (!ZYPCommonFunc.hasValue(scrnMsg.billToCustAcctNm)) {
                scrnMsg.billToCustAcctCd.clear();
                scrnMsg.billToCustCd.clear();
                scrnMsg.xxAllLineAddr_BT.clear();
                scrnMsg.entBillToCustLocAddr.clear();
                scrnMsg.setFocusItem(scrnMsg.billToCustAcctCd);
                return;
            }
        }

        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            scrnMsg.addCheckItem(scrnMsg.A.no(selectIndex).billToCustAcctNm_LC);
        } else if (TAB_RMA.equals(dplyTab)) {
            scrnMsg.addCheckItem(scrnMsg.C.no(selectIndex).billToCustAcctNm_RC);
        } else {
            scrnMsg.addCheckItem(scrnMsg.billToCustAcctNm);
        }
        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        // 2018/07/27 S21_NA#14307 Add Start
        if (NWAL1500CommonLogic.checkSpecialInstrctionData(scrnMsg)) {
            setResult(RESULT_PARAM_SPECIAL_INSTRUCTION);
            NWAL1500CommonLogicForScrnFields.setProtectByBillTo(this, scrnMsg);

            Object[] params = NWAL1500CommonLogicForOrderEntryAction.getArgForSubScreenSpecialInstruction(scrnMsg);
            setArgForSubScreen(params);
            return;
        }
        // 2018/07/27 S21_NA#14307 Add End

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

            Object[] params = NWAL1500CommonLogic.getParamNMAL6760ForBillTo(scrnMsg);
            setArgForSubScreen(params);
            return;
        }
        NWAL1500CommonLogicForScrnFields.setProtectByBillTo(this, scrnMsg); // QC#17474 2017/02/21 Add

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            scrnMsg.setFocusItem(scrnMsg.A.no(selectIndex).xxImageTxt_AD);
        } else if (TAB_RMA.equals(dplyTab)) {
            scrnMsg.setFocusItem(scrnMsg.C.no(selectIndex).xxImageTxt_CD);
        } else {
            scrnMsg.setFocusItem(scrnMsg.shipToCustAcctNm);
        }
    }
}
