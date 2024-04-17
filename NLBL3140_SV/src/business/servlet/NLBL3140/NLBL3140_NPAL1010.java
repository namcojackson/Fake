/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3140;

import static business.servlet.NLBL3140.constant.NLBL3140Constant.CLOSE_EV;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.IDX_6;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.IDX_7;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.IDX_8;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.IDX_9;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.OPEN_WIN_SEARCH_SWH_EV;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.OPEN_WIN_SEARCH_WH_EV;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.OPEN_WIN_SEARCH_WH_LINK_EV;
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
public class NLBL3140_NPAL1010 extends S21CommonHandler {

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
        NLBL3140BMsg scrnMsg = (NLBL3140BMsg) bMsg;
        if (OPEN_WIN_SEARCH_WH_LINK_EV.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd, scrnMsg.Y.no(IDX_6).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm, scrnMsg.Y.no(IDX_7).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd, scrnMsg.Y.no(IDX_8).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm, scrnMsg.Y.no(IDX_9).xxPopPrm);
            scrnMsg.setFocusItem(scrnMsg.lineBizTpCd);
        } else if (OPEN_WIN_SEARCH_WH_EV.equals(scrnMsg.xxScrEventNm.getValue())|OPEN_WIN_SEARCH_SWH_EV.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).rtlWhCd_A, scrnMsg.Y.no(IDX_6).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).rtlWhNm_A, scrnMsg.Y.no(IDX_7).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).rtlSwhCd_A, scrnMsg.Y.no(IDX_8).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).rtlSwhNm_A, scrnMsg.Y.no(IDX_9).xxPopPrm);
            scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).lineBizTpCd_A);
        }
    }
}
