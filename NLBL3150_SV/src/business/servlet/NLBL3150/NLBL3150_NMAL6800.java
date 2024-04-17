/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3150;

import static business.servlet.NLBL3150.constant.NLBL3150Constant.CLOSE_EV;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.IDX_0;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.IDX_1;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.OPEN_WIN_SEARCH_ITEM_NUM_EV;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.OPEN_WIN_SEARCH_ITEM_NUM_LINK_EV;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/07/21   Fujitsu         K.Ishizuka      Create          N/A
 *</pre>
 */
public class NLBL3150_NMAL6800 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //do nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if (CLOSE_EV.equals(getLastGuard())) {
            return;
        }
        NLBL3150BMsg scrnMsg = (NLBL3150BMsg) bMsg;
        if (OPEN_WIN_SEARCH_ITEM_NUM_LINK_EV.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd, scrnMsg.Y.no(IDX_0).xxPopPrm);
            scrnMsg.setFocusItem(scrnMsg.rtlWhCd);
        } else if (OPEN_WIN_SEARCH_ITEM_NUM_EV.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).mdseCd_A, scrnMsg.Y.no(IDX_0).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).mdseDescShortTxt_A, scrnMsg.Y.no(IDX_1).xxPopPrm);
            scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).rtlWhCd_A);
        }

    }
}
