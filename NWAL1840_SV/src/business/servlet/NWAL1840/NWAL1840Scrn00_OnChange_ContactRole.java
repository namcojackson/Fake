/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1840;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1840.common.NWAL1840CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/04   Fujitsu         H.Nagashima     Create          N/A
 * 2017/10/18   Fujitsu         S.Yamamoto      Update          S21_NA#20246
 *</pre>
 */
public class NWAL1840Scrn00_OnChange_ContactRole extends S21CommonHandler {

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

        int row = getButtonSelectNumber();

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;
        String ctacTpCd = scrnMsg.D.no(row).ctacPsnTpCd_D.getValue();
        if (!ZYPCommonFunc.hasValue(ctacTpCd)) {
            return;
        }

        NWAL1840CommonLogic.setProtect(this, scrnMsg);
        // set default
        if (!scrnMsg.D.no(row).ctacCustRefTpCd_D.isInputProtected()) {
            String ctacCustRefCd = null;
            for (int i = 0; i < scrnMsg.ctacTpCd_CD.length(); i++) {
                if (ctacTpCd.equals(scrnMsg.ctacTpCd_CD.no(i).getValue())) {
                    ctacCustRefCd = scrnMsg.L.no(i).ctacCustRefTpCd_L.getValue();
                    ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(row).ctacCustRefTpCd_D, ctacCustRefCd);
                    break;
                }
            }
        }

        scrnMsg.setFocusItem(scrnMsg.D.no(row).ctacPsnTpCd_D);

        // S21_NA ADD QC#20246
        NWAL1840CommonLogic.setContactToAttention(scrnMsg, row);

    }
}
