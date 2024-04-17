/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1610;

import static business.servlet.NWAL1610.constant.NWAL1610Constant.BTN_CMN_CLOSE;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.OPENWIN_CSMP_NUM;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.OPENWIN_WH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1610_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/07   Fujitsu         Yokoi           Create          N/A
 * 2016/08/30   Fujitsu         S.Takami        Update          S21_NA#9806
 *</pre>
 */
public class NWAL1610_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL1610BMsg scrnMsg = (NWAL1610BMsg) bMsg;

        if (!BTN_CMN_CLOSE.equals(getLastGuard())) {
            if (OPENWIN_WH.equals(scrnMsg.xxScrEventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd, scrnMsg.S.no(0).xxComnScrColValTxt_S);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm, scrnMsg.S.no(1).xxComnScrColValTxt_S);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd, scrnMsg.S.no(2).xxComnScrColValTxt_S);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm, scrnMsg.S.no(3).xxComnScrColValTxt_S);
                scrnMsg.setFocusItem(scrnMsg.rtlWhCd);
            } else if (OPENWIN_CSMP_NUM.equals(scrnMsg.xxScrEventNm.getValue())) { // 2016/08/30 S21_NA#9806 Add
                ZYPEZDItemValueSetter.setValue(scrnMsg.csmpContrNum, scrnMsg.S.no(0).xxComnScrColValTxt_S);
                ZYPEZDItemValueSetter.setValue(scrnMsg.dlrRefNum, scrnMsg.S.no(1).xxComnScrColValTxt_S);
                scrnMsg.setFocusItem(scrnMsg.csmpContrNum);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem20Txt, scrnMsg.S.no(0).xxComnScrColValTxt_S);
                scrnMsg.setFocusItem(scrnMsg.xxScrItem20Txt);
            }
        }
    }
}
