/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogic;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_RMA;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/24   Fujitsu         T.Yoshida       Create          N/A
 * 2016/08/26   Fujitsu         S.Takami        Update          S21_NA#9806
 * 2016/08/31   Fujitsu         S.Takami        Update          S21_NA#10535
 * </pre>
 */
public class NWAL1500Scrn00_OnBlur_DeriveFromCSMPNumber extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        int cellIdx = getButtonSelectNumber(); // 2016/08/26 S21_NA#9806 Add

        // 2016/08/26 S21_NA#9806 Mod Start
//        if (!ZYPCommonFunc.hasValue(scrnMsg.csmpContrNum)) {
//            return null;
//        }
        if (cellIdx < 0) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.csmpContrNum)) {
                return null;
            }
//        } else { 2016/08/31 S21_NA#10535 Del Start
//            String dplyTab = scrnMsg.xxDplyTab.getValue();
//            if (TAB_LINE_CONFIG.equals(dplyTab)) {
//                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(cellIdx).csmpContrNum_LC)) {
//                    return null;
//                }
//            } else if (TAB_RMA.equals(dplyTab)) {
//                if (!ZYPCommonFunc.hasValue(scrnMsg.C.no(cellIdx).csmpContrNum_RC)) {
//                    return null;
//                }
//            } 2016/08/31 S21_NA#10535 Del Start
        }
        scrnMsg.xxCellIdx.setValue(cellIdx);
        // 2016/08/26 S21_NA#9806 Mod End

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

        // 2016/08/26 S21_NA#9806 Mod Start
//        if (!ZYPCommonFunc.hasValue(scrnMsg.csmpContrNum)) {
//            scrnMsg.setFocusItem(scrnMsg.dlrRefNum);
//            return;
//        }
//
//        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//
//        scrnMsg.addCheckItem(scrnMsg.csmpContrNum);
//        scrnMsg.putErrorScreen();
//
//        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
//            setResult(ZYPConstant.FLG_ON_Y);
//            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
//            Object[] params = NWAL1500CommonLogic.getParamNWAL1130ForCSMPNumber(scrnMsg);
//            setArgForSubScreen(params);
//            return;
//        }
//
//        scrnMsg.setFocusItem(scrnMsg.ordSgnDt);

        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        if (bizMsg == null) {
            return;
        }
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        int cellIdx = scrnMsg.xxCellIdx.getValueInt();
        if (cellIdx < 0) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.csmpContrNum)) {
                scrnMsg.setFocusItem(scrnMsg.dlrRefNum);
                return;
            }

            scrnMsg.addCheckItem(scrnMsg.csmpContrNum);
            scrnMsg.putErrorScreen();

            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
                setResult(ZYPConstant.FLG_ON_Y);
                scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
                Object[] params = NWAL1500CommonLogic.getParamNWAL1130ForCSMPNumber(scrnMsg);
                setArgForSubScreen(params);
                return;
            }

            scrnMsg.setFocusItem(scrnMsg.ordSgnDt);
        } else {
            String dplyTab = scrnMsg.xxDplyTab.getValue();
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                NWAL1500_ABMsg lineConfigMsg = scrnMsg.A.no(cellIdx);
                if (!ZYPCommonFunc.hasValue(lineConfigMsg.csmpContrNum_LC)) {
                    scrnMsg.setFocusItem(lineConfigMsg.dlrRefNum_LC);
                    return;
                }

                scrnMsg.addCheckItem(lineConfigMsg.csmpContrNum_LC);
                scrnMsg.putErrorScreen();

                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
                    setResult(ZYPConstant.FLG_ON_Y);
                    scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
                    Object[] params = NWAL1500CommonLogic.getParamNWAL1130ForCSMPNumber(scrnMsg);
                    setArgForSubScreen(params);
                    return;
                }

                scrnMsg.setFocusItem(lineConfigMsg.dlrRefNum_LC);
            } else if (TAB_RMA.equals(dplyTab)) {
                NWAL1500_CBMsg rmaConfigMsg = scrnMsg.C.no(cellIdx);
                if (!ZYPCommonFunc.hasValue(rmaConfigMsg.csmpContrNum_RC)) {
                    scrnMsg.setFocusItem(rmaConfigMsg.dlrRefNum_RC);
                    return;
                }

                scrnMsg.addCheckItem(rmaConfigMsg.csmpContrNum_RC);
                scrnMsg.putErrorScreen();

                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
                    setResult(ZYPConstant.FLG_ON_Y);
                    scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
                    Object[] params = NWAL1500CommonLogic.getParamNWAL1130ForCSMPNumber(scrnMsg);
                    setArgForSubScreen(params);
                    return;
                }

                scrnMsg.setFocusItem(rmaConfigMsg.dlrRefNum_RC);
            }
        }
        // 2016/08/26 S21_NA#9806 Mod End
    }
}
