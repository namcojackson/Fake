/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/18   SRA             E.Inada         Create          N/A
 * 2013/09/06   SRA             Y.Chen          Create          QC2058
 * 
 *</pre>
 */
public class NSBL0010Scrn00_AllCheck extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;

        int size = scrnMsg.A.getValidCount();
        for (int i = 0; i < size; i++) {
            // QC2058
            if (!scrnMsg.A.no(i).xxChkBox_A.isInputProtected()) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxChkBox_A, scrnMsg.xxChkBox_AL.getValue());
            }
        }
    }
}
