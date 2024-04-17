/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1230;

import static business.servlet.NPAL1230.constant.NPAL1230Constant.EVENT_NM_SELECT_ITEM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1230 ASL Entry
 * </pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/12   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public class NPAL1230_NMAL6800 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1230BMsg scrnMsg = (NPAL1230BMsg) bMsg;
        if (EVENT_NM_SELECT_ITEM.equals(getLastGuard())) {
            int selectNum = getButtonSelectNumber();
            if (selectNum >= 0) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectNum).mdseCd_A, scrnMsg.xxPopPrm_P0);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectNum).mdseDescShortTxt_A, scrnMsg.xxPopPrm_P7);

                String coaMdseTpCd = scrnMsg.xxPopPrm_P4.getValue();
                if (ZYPCommonFunc.hasValue(coaMdseTpCd)) {
                    for (int i = 0; i < scrnMsg.coaMdseTpCd_PD.length(); i++) {
                        if (coaMdseTpCd.equals(scrnMsg.coaMdseTpCd_PD.no(i).getValue())) {
                            scrnMsg.A.no(selectNum).xxComnScrFirstValTxt_A.setValue(coaMdseTpCd + ":" + scrnMsg.coaProjDescTxt_PD.no(i).getValue());
                            break;
                        }
                    }
                }
                scrnMsg.setFocusItem(scrnMsg.A.no(selectNum).mdseCd_A);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd, scrnMsg.xxPopPrm_P0);
                ZYPEZDItemValueSetter.setValue(scrnMsg.mdseDescShortTxt, scrnMsg.xxPopPrm_P7);
                scrnMsg.setFocusItem(scrnMsg.mdseCd);
            }
        }
    }
}
