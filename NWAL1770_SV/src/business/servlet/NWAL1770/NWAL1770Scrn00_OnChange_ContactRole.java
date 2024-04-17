/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1770;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1770.common.NWAL1770CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/09   Fujitsu         H.Nagashima     Create          N/A
 * 2017/10/18   Fujitsu         W.Honda         Create          S21_NA#20246-1(L3 Sol#454)
 * 2017/12/20   Fujitsu         Mz.Takahashi    Update          S21_NA#20164(L3 Sol#356)
 *</pre>
 */
public class NWAL1770Scrn00_OnChange_ContactRole extends S21CommonHandler {

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

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        String ctacTpCd = scrnMsg.A.no(row).ctacPsnTpCd_A.getValue();
        if (!ZYPCommonFunc.hasValue(ctacTpCd)) {
            return;
        }

        NWAL1770CommonLogicForScrnFields.setProtect(this, scrnMsg);
        // set default
        if (!scrnMsg.A.no(row).ctacCustRefTpCd_A.isInputProtected()) {
            String ctacCustRefCd = null;
            for (int i = 0; i < scrnMsg.ctacTpCd_PL.length(); i++) {
                if (ctacTpCd.equals(scrnMsg.ctacTpCd_PL.no(i).getValue())) {
                    ctacCustRefCd = scrnMsg.L.no(i).ctacCustRefTpCd_L.getValue();
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(row).ctacCustRefTpCd_A, ctacCustRefCd);
                    break;
                }
            }
        }

        // Del Start 2017/10/13 QC#20164(L3 Sol#356)
        // Add Start 2017/10/13 QC#20246-1(L3 Sol#454)
        //NWAL1770CommonLogic.contactToAttention(scrnMsg, row);
        // Add End 2017/10/13 QC#20246-1(L3 Sol#454)
        // Del End 2017/10/13 QC#20164(L3 Sol#356)
        scrnMsg.setFocusItem(scrnMsg.A.no(row).ctacPsnTpCd_A);

    }
}
