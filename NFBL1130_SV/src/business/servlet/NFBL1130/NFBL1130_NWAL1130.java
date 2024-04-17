/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL1130;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFBL1130.constant.NFBL1130Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/09/30   Hitachi         K.Kojima        Update          QC#14179
 *</pre>
 */
public class NFBL1130_NWAL1130 extends S21CommonHandler implements NFBL1130Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }

        NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OnClick_SupplierLink".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndCd, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }
        NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OnClick_SupplierLink".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.prntVndCd);
        }
    }
}
