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

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   Fujitsu         T.Yoshida       Create          N/A
 * 2016/07/28   Fujitsu         M.Hara          Update          S21_NA#1698,3235
 * 2019/11/21   Fujitsu         S.Takami        Update          S21_NA#54202
 *</pre>
 */
public class NWAL1500Scrn00_OnBlur_DeriveFromSubstituteItem extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        int selectIndex = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(selectIndex);

        scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).mdseCd_LL);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        // 2019/11/21 S21_NA#54202 Del Start
//        int selectIndex = scrnMsg.xxCellIdx.getValueInt();
//
//        if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIndex).sbstMdseCd_LL)) {
//            return null;
//        }
        // 2019/11/21 S21_NA#54202 Del End

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

        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        // 2019/11/21 S21_NA#54202 Del Start
//        if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIndex).sbstMdseCd_LL)) {
//            scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).custMdseCd_LL);
//            return;
//        }
        // 2019/11/21 S21_NA#54202 Del End

        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).sbstMdseCd_LL);
        scrnMsg.putErrorScreen();

        // 2016/07/28 S21_NA#1698,3235 Mode Start
//        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
//            setResult(ZYPConstant.FLG_ON_Y);
//            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
//            Object[] params = NWAL1500CommonLogic.getParamNWAL1130ForSbstItem(scrnMsg);
//            setArgForSubScreen(params);
//            return;
//        }
        // 2016/07/28 S21_NA#1698,3235 Mode End

        scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).custMdseCd_LL);
    }
}
