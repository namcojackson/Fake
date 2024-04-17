/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2720;

import static business.servlet.NMAL2720.constant.NMAL2720Constant.BTN_CMN_CLOSE;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.EVENT_NM_RESRC_LOOK_UP_DTL;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.EVENT_NM_RESRC_LOOK_UP_FOR_CD;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.EVENT_NM_RESRC_LOOK_UP_FOR_NAME;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.EVENT_NM_RESRC_LOOK_UP_FOR_NUM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2720_NMAL2570
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/22   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL2720_NMAL2570 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2720BMsg scrnMsg = (NMAL2720BMsg) bMsg;

        if (BTN_CMN_CLOSE.equals(getLastGuard())) {
            return;
        }

        if (EVENT_NM_RESRC_LOOK_UP_FOR_CD.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.psnCd_H, scrnMsg.P.no(1).xxPopPrm);
            scrnMsg.setFocusItem(scrnMsg.psnCd_H);
        } else if (EVENT_NM_RESRC_LOOK_UP_FOR_NAME.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPsnNm_H, scrnMsg.P.no(3).xxPopPrm);
            scrnMsg.setFocusItem(scrnMsg.xxPsnNm_H);
        } else if (EVENT_NM_RESRC_LOOK_UP_FOR_NUM.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.psnNum_H, scrnMsg.P.no(0).xxPopPrm);
            scrnMsg.setFocusItem(scrnMsg.psnNum_H);
        } else if (EVENT_NM_RESRC_LOOK_UP_DTL.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPsnNm_D1, scrnMsg.P.no(3).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.psnNum_D1, scrnMsg.P.no(0).xxPopPrm);
            scrnMsg.setFocusItem(scrnMsg.xxPsnNm_D1);
        }
    }
}
