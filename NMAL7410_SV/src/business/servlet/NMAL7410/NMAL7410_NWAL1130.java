/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7410;

import static business.servlet.NMAL7410.constant.NMAL7410Constant.IDX_0;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.IDX_1;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/29   Fujitsu         T.Murai         Create          N/A
 *</pre>
 */
public class NMAL7410_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do noting
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }
        NMAL7410BMsg scrnMsg = (NMAL7410BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OpenWin_PrcCatg".equals(scrEventNm)) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgNm, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
            scrnMsg.setFocusItem(scrnMsg.prcCatgNm);
        } else if ("OpenWin_ListPrcCatg".equals(scrEventNm)) {

            int selectIndex = scrnMsg.xxCellIdx.getValueInt();
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIndex).csaPrcCatgCd_A, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIndex).prcCatgNm_A, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
            scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).csaPrcCatgCd_A);
        }
    }
}
