/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3140;

import static business.servlet.NLBL3140.constant.NLBL3140Constant.CLOSE_EV;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.IDX_0;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.IDX_1;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.OPEN_WIN_SEARCH_ORD_CATG_EV;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.OPEN_WIN_SEARCH_ORD_CATG_LINK_EV;
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
public class NLBL3140_NWAL1130 extends S21CommonHandler {

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

        // 2017/08/14 QC#20555 ADD BEGIN
        if (CLOSE_EV.equals(getLastGuard())) {
            return;
        }
        // 2017/08/14 QC#20555 ADD END
        NLBL3140BMsg scrnMsg = (NLBL3140BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        if (OPEN_WIN_SEARCH_ORD_CATG_LINK_EV.equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdCatgCd, scrnMsg.X.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdCatgDescTxt, scrnMsg.X.no(IDX_1).xxComnScrColValTxt.getValue());
        } else if (OPEN_WIN_SEARCH_ORD_CATG_EV.equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).dsOrdCatgCd_A, scrnMsg.X.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).dsOrdCatgDescTxt_A, scrnMsg.X.no(IDX_1).xxComnScrColValTxt.getValue());
        }
    }
}
