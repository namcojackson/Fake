/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.BIZ_ID;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_RMA;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2200.NWAL2200CMsg;
import business.servlet.NWAL2200.common.NWAL2200CommonLogic;
import business.servlet.NWAL2200.common.NWAL2200CommonLogicForScreenFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2200Scrn00_OnBlur_DeriveFromShipToLocation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 * 2016/09/15   Fujitsu         R.Nakamura      Update          QC#21118
 * 2018/07/25   Fujitsu         Mz.Takahashi    Update          S21_NA#14307
 * 2019/02/06   Fujitsu         K.Kato          Update          S21_NA#30237
 *</pre>
 */
public class NWAL2200Scrn00_OnBlur_DeriveFromShipToLocation extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        NWAL2200CommonLogicForScreenFields.addCheckItem(scrnMsg, false, scrnMsg.xxDplyTab.getValue());
        scrnMsg.putErrorScreen();

        // Mod Start 2017/09/15 QC#21118
        String dplyTab = scrnMsg.xxDplyTab.getValue();
        int selectIndex = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, BigDecimal.valueOf(selectIndex));
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIndex).shipToCustLocCd_LC)) {
                scrnMsg.addCheckItem(scrnMsg.A.no(selectIndex).shipToCustLocCd_LC);
                scrnMsg.putErrorScreen();
            }
        } else if (TAB_RMA.equals(dplyTab)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.C.no(selectIndex).shipToCustLocCd_RC)) {
                scrnMsg.addCheckItem(scrnMsg.C.no(selectIndex).shipToCustLocCd_RC);
                scrnMsg.putErrorScreen();
            }
        } else {
            if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd)) {
                scrnMsg.addCheckItem(scrnMsg.shipToCustCd);
                scrnMsg.putErrorScreen();
            }
        }
        // Mod End 2017/09/15 QC#21118
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        // Mod Start 2017/09/15 QC#21118
        String dplyTab = scrnMsg.xxDplyTab.getValue();
        int selectIndex = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(selectIndex);

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIndex).shipToCustLocCd_LC)) {
                return null;
            }
        } else if (TAB_RMA.equals(dplyTab)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.C.no(selectIndex).shipToCustLocCd_RC)) {
                return null;
            }
        } else {
            if (!ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd)) {
                // Add Start 2018/07/27 S21_NA#14307
                scrnMsg.xxAllLineAddr_SH.clear();
                // Add End 2018/07/27 S21_NA#14307
                return null;
            }
        }
        // Mod End 2017/09/15 QC#21118

        NWAL2200CMsg bizMsg = new NWAL2200CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        setResult(ZYPConstant.FLG_OFF_N);

        // Mod Start 2017/09/15 QC#21118
        String dplyTab = scrnMsg.xxDplyTab.getValue();
        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIndex).shipToCustLocCd_LC)) {
                scrnMsg.A.no(selectIndex).xxAllLineAddr_LS.clear();
                // 2019/02/06 QC#30237 Add Start
                scrnMsg.A.no(selectIndex).shipToCustAcctCd_LC.clear();
                scrnMsg.A.no(selectIndex).shipToLocNm_LC.clear();
                scrnMsg.A.no(selectIndex).shipToAddlLocNm_LC.clear();
                scrnMsg.A.no(selectIndex).shipToCntyNm_LC.clear();
                scrnMsg.A.no(selectIndex).shipToCtryCd_LC.clear();
                scrnMsg.A.no(selectIndex).shipToCtyAddr_LC.clear();
                scrnMsg.A.no(selectIndex).shipToFirstLineAddr_LC.clear();
                scrnMsg.A.no(selectIndex).shipToScdLineAddr_LC.clear();
                scrnMsg.A.no(selectIndex).shipToThirdLineAddr_LC.clear();
                scrnMsg.A.no(selectIndex).shipToFrthLineAddr_LC.clear();
                scrnMsg.A.no(selectIndex).shipToPostCd_LC.clear();
                scrnMsg.A.no(selectIndex).shipToStCd_LC.clear();
                // 2019/02/06 QC#30237 Add End
                return;
            }
        } else if (TAB_RMA.equals(dplyTab)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.C.no(selectIndex).shipToCustLocCd_RC)) {
                scrnMsg.C.no(selectIndex).xxAllLineAddr_RS.clear();
                // 2019/02/06 QC#30237 Add Start
                scrnMsg.C.no(selectIndex).shipToCustAcctCd_RC.clear();
                scrnMsg.C.no(selectIndex).shipToLocNm_RC.clear();
                scrnMsg.C.no(selectIndex).shipToAddlLocNm_RC.clear();
                scrnMsg.C.no(selectIndex).shipToCntyNm_RC.clear();
                scrnMsg.C.no(selectIndex).shipToCtryCd_RC.clear();
                scrnMsg.C.no(selectIndex).shipToCtyAddr_RC.clear();
                scrnMsg.C.no(selectIndex).shipToFirstLineAddr_RC.clear();
                scrnMsg.C.no(selectIndex).shipToScdLineAddr_RC.clear();
                scrnMsg.C.no(selectIndex).shipToThirdLineAddr_RC.clear();
                scrnMsg.C.no(selectIndex).shipToFrthLineAddr_RC.clear();
                scrnMsg.C.no(selectIndex).shipToPostCd_RC.clear();
                scrnMsg.C.no(selectIndex).shipToStCd_RC.clear();
                // 2019/02/06 QC#30237 Add End
                return;
            }
        } else {
            if (!ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd)) {
                scrnMsg.setFocusItem(scrnMsg.soldToCustAcctNm);
                return;
            }
        }
        // Mod End 2017/09/15 QC#21118

        NWAL2200CMsg bizMsg = (NWAL2200CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Mod Start 2017/09/15 QC#21118
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            scrnMsg.addCheckItem(scrnMsg.A.no(selectIndex).shipToCustLocCd_LC);
        } else if (TAB_RMA.equals(dplyTab)) {
            scrnMsg.addCheckItem(scrnMsg.C.no(selectIndex).shipToCustLocCd_RC);
        } else {
            scrnMsg.addCheckItem(scrnMsg.shipToCustCd);
        }
        // Mod End 2017/09/15 QC#21118
        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            //scrnMsg.xxCellIdx.setValue(-1);
            scrnMsg.shipToCustAcctNm.clear();
            scrnMsg.shipToCustAcctCd.clear();

            Object[] params = NWAL2200CommonLogic.getParamNMAL6760ForShipTo(scrnMsg);
            setArgForSubScreen(params);
            return;

        // Add Start 2018/07/25 S21_NA#14307
        } else if (ZYPConstant.FLG_ON_1.equals(scrnMsg.xxRqstFlg.getValue())){
            setResult(ZYPConstant.FLG_ON_1);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            setArgForSubScreen(NWAL2200CommonLogic.getArgForSubScreen(scrnMsg, this.getGlobalCompanyCode()));
            return;
        }
        // Add End 2018/07/25 S21_NA#14307

        // 2019/02/06 QC#30237 Add Start
        //scrnMsg.setFocusItem(scrnMsg.soldToCustAcctNm);
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            scrnMsg.setFocusItem(scrnMsg.A.no(selectIndex).billToCustLocCd_LC);
        } else if (TAB_RMA.equals(dplyTab)) {
            scrnMsg.setFocusItem(scrnMsg.C.no(selectIndex).billToCustLocCd_RC);
        } else {
            scrnMsg.setFocusItem(scrnMsg.soldToCustAcctNm);
        }
        // 2019/02/06 QC#30237 Add End
    }
}
