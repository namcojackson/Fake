/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0240;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;

import static business.servlet.NSBL0240.constant.NSBL0240Constant.*;

import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/15   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSBL0240_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0240BMsg scrnMsg = (NSBL0240BMsg) bMsg;

        int selIndex = getButtonSelectNumber();

        for (int i = 0; i < scrnMsg.P.length(); i++) {
            if (PRM_VALUE_MDSE_CD.equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
                    setValue(scrnMsg.A.no(selIndex).intgMdseCd, scrnMsg.P.no(i).xxComnScrColValTxt.getValue());
                } else {
                    scrnMsg.A.no(selIndex).intgMdseCd.clear();
                }
            } else if (PRM_VALUE_MDSE_DESC_SHORT_TXT.equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
                    setValue(scrnMsg.A.no(selIndex).mdseDescShortTxt, scrnMsg.P.no(i).xxComnScrColValTxt.getValue());
                } else {
                    scrnMsg.A.no(selIndex).mdseDescShortTxt.clear();
                }
            }
        }
        scrnMsg.setFocusItem(scrnMsg.A.no(selIndex).intgMdseCd);

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
    }
}
