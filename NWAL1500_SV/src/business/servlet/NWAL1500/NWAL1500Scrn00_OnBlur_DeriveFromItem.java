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
import business.servlet.NWAL1500.common.NWAL1500CommonLogic;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/21   Fujitsu         T.Yoshida       Create          N/A
 * 2016/02/18   Fujitsu         T.Ishii         Update          QC#1634
 * 2017/03/02   Fujitsu         S.Takami        Update          S21_NA#17714-2
 *</pre>
 */
public class NWAL1500Scrn00_OnBlur_DeriveFromItem extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        int selectIndex = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(selectIndex);

        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIndex).mdseCd_LL)) {
                scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).mdseCd_LL);
            }
        } else {
            if (ZYPCommonFunc.hasValue(scrnMsg.D.no(selectIndex).mdseCd_RL)) {
                scrnMsg.addCheckItem(scrnMsg.D.no(selectIndex).mdseCd_RL);
            }
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.B.no(selectIndex).custMdseCd_LL.clear();
            scrnMsg.B.no(selectIndex).baseCmptFlg_LL.clear();
            if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIndex).mdseCd_LL)) {
                return null;
            }
        } else {
            scrnMsg.D.no(selectIndex).custMdseCd_RL.clear();
            scrnMsg.D.no(selectIndex).baseCmptFlg_RL.clear();
            if (!ZYPCommonFunc.hasValue(scrnMsg.D.no(selectIndex).mdseCd_RL)) {
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
            if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIndex).mdseCd_LL)) {
                scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).ordCustUomQty_LL);
                return;
            }
        } else {
            if (!ZYPCommonFunc.hasValue(scrnMsg.D.no(selectIndex).mdseCd_RL)) {
                scrnMsg.setFocusItem(scrnMsg.D.no(selectIndex).ordCustUomQty_RL);
                return;
            }
        }

        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg);

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            // 2016/01/19 S21_NA#3339 Add Start
            String dsOrdPosnNum = scrnMsg.B.no(selectIndex).dsOrdPosnNum_LL.getValue();
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (dsOrdPosnNum.equals(scrnMsg.A.no(i).dsOrdPosnNum_LC.getValue())) {
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).svcConfigMstrPk_LC);
                }
            }
            // 2016/01/19 S21_NA#3339 Add End
            scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).mdseCd_LL);
            scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).ordCustUomQty_LL); // 2017/03/02 S21_NA#17714-2 Add
        } else {
            scrnMsg.addCheckItem(scrnMsg.D.no(selectIndex).mdseCd_RL);
            scrnMsg.addCheckItem(scrnMsg.D.no(selectIndex).ordCustUomQty_RL); // 2017/03/02 S21_NA#17714-2 Add
        }
        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            Object[] params = NWAL1500CommonLogic.getParamNMAL6800(scrnMsg);
            setArgForSubScreen(params);
            return;
        }

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            // S21_NA#1634
            // startNWAL1500CommonLogic.controlMdseField(scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).ordCustUomQty_LL);
        } else {
            // S21_NA#1634
            // startNWAL1500CommonLogic.controlMdseFieldForRma(scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.D.no(selectIndex).ordCustUomQty_RL);
        }
    }
}
