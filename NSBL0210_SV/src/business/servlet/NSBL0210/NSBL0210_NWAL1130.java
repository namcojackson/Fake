/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0210;

import static business.servlet.NSBL0210.constant.NSBL0210Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   Hitachi         Y.Takeno        Create          N/A
 * 2016/05/24   Hitachi         Y.Takeno        Update          QC#8565
 *</pre>
 */
public class NSBL0210_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

// START 05/24/2016 [QC#8565, MOD]
    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSBL0210BMsg scrnMsg = (NSBL0210BMsg) bMsg;

        if (NWAL1130_EVENT_NM_CLOSE.equals(getLastGuard())) {
            return;
        }

        String eventName = scrnMsg.xxScrEventNm.getValue();
        if (LINK_NM_OPEN_WIN_SHIFT_HDR.equals(eventName)) {
            doProcess_ShiftHeader(ctx, scrnMsg);
            return;
        }

        if (BTN_NM_OPEN_WIN_SHIFT_DTL.equals(eventName)) {
            doProcess_ShiftDetail(ctx, scrnMsg);
            return;
        }

        if (BTN_NM_OPEN_WIN_INTG_ITEM.equals(eventName)) {
            doProcess_IntgItemPopup(ctx, scrnMsg);
            return;
        }
    }

    private EZDCMsg doProcess_ShiftHeader(EZDApplicationContext ctx, NSBL0210BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.P.length(); i++) {
            if (PRM_VALUE_SVC_PRC_SHIFT_DESC_TXT.equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.svcPrcShiftDescTxt_H, scrnMsg.P.no(i).xxComnScrColValTxt.getValue());
                } else {
                    scrnMsg.svcPrcShiftDescTxt_H.clear();
                }
            }
        }
        scrnMsg.setFocusItem(scrnMsg.svcPrcShiftDescTxt_H);

        return null;
    }

    private EZDCMsg doProcess_ShiftDetail(EZDApplicationContext ctx, NSBL0210BMsg scrnMsg) {
        int selIndex = getButtonSelectNumber();

        for (int i = 0; i < scrnMsg.P.length(); i++) {
            if (PRM_VALUE_SVC_PRC_SHIFT_NUM.equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selIndex).svcPrcShiftNum, scrnMsg.P.no(i).xxComnScrColValTxt.getValue());
                } else {
                    scrnMsg.A.no(selIndex).svcPrcShiftNum.clear();
                }
            } else if (PRM_VALUE_SVC_PRC_SHIFT_DESC_TXT.equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selIndex).svcPrcShiftDescTxt, scrnMsg.P.no(i).xxComnScrColValTxt.getValue());
                } else {
                    scrnMsg.A.no(selIndex).svcPrcShiftDescTxt.clear();
                }
            }
        }
        scrnMsg.setFocusItem(scrnMsg.A.no(selIndex).svcPrcShiftNum);

        return null;
    }

    private EZDCMsg doProcess_IntgItemPopup(EZDApplicationContext ctx, NSBL0210BMsg scrnMsg) {
        int selIndex = getButtonSelectNumber();

        for (int i = 0; i < scrnMsg.P.length(); i++) {
            if (PRM_VALUE_MDSE_CD.equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selIndex).intgMdseCd, scrnMsg.P.no(i).xxComnScrColValTxt.getValue());
                } else {
                    scrnMsg.A.no(selIndex).intgMdseCd.clear();
                }
            } else if (PRM_VALUE_MDSE_DESC_SHORT_TXT.equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selIndex).mdseDescShortTxt, scrnMsg.P.no(i).xxComnScrColValTxt.getValue());
                } else {
                    scrnMsg.A.no(selIndex).mdseDescShortTxt.clear();
                }
            }
        }
        scrnMsg.setFocusItem(scrnMsg.A.no(selIndex).intgMdseCd);

        return null;
    }
    // END   05/24/2016 [QC#8565, MOD]
}
