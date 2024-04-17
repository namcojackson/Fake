/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1230;

import static business.servlet.NPAL1230.constant.NPAL1230Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.EVENT_NM_OPEN_WIN_ASLNAME;
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
 * 2018/01/12   CITS            S.Katsuma       Update          QC#12226
 *</pre>
 */
public class NPAL1230_NWAL1130 extends S21CommonHandler {

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

        int selectNum = getButtonSelectNumber();

        if (EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            if (selectNum >= 0) {
                scrnMsg.setFocusItem(scrnMsg.A.no(selectNum).vndCd_A);
            } else {
                scrnMsg.setFocusItem(scrnMsg.prntVndCd);
            }
        } else {
            // START 2018/01/12 S.Katsuma [QC#12226,ADD]
            int i = 0;
            if (ZYPCommonFunc.hasValue(scrnMsg.xxScrNm_Z) && EVENT_NM_OPEN_WIN_ASLNAME.equals(scrnMsg.xxScrNm_Z.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.aslNm, scrnMsg.R.no(i++).xxComnScrColValTxt);
            }

            ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndCd, scrnMsg.R.no(i++).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm, scrnMsg.R.no(i++).xxComnScrColValTxt);

            if (selectNum >= 0) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectNum).vndCd_A, scrnMsg.R.no(i++).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.A.no(selectNum).vndCd_A);
            } else {
                if (i == 1) {
                    scrnMsg.setFocusItem(scrnMsg.prntVndCd);
                } else if (i == 2) {
                    scrnMsg.setFocusItem(scrnMsg.aslNm);
                }
            }
            // START 2018/01/12 S.Katsuma [QC#12226,ADD]
        }
        scrnMsg.xxScrNm_Z.clear();
    }
}
