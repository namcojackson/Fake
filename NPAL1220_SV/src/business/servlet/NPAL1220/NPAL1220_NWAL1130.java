/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1220;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/05   CITS            T.Gotoda        Create          N/A
 * 2018/01/11   CITS            S.Katsuma       Update          QC#12226
 *</pre>
 */
public class NPAL1220_NWAL1130 extends S21CommonHandler {

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

        NPAL1220BMsg scrnMsg = (NPAL1220BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndCd, scrnMsg.R.no(0).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm, scrnMsg.R.no(1).xxComnScrColValTxt);
        // START 2018/01/11 S.Katsuma [QC#12226,ADD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.vndCd, scrnMsg.R.no(2).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.locNm, scrnMsg.R.no(3).xxComnScrColValTxt);
        // END 2018/01/11 S.Katsuma [QC#12226,ADD]
        scrnMsg.setFocusItem(scrnMsg.prntVndCd);
    }
}
