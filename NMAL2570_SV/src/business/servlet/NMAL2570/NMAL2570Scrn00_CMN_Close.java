/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2570;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2570.constant.NMAL2570Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/30   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/06/07   SRAA            Y.Chen          Update          QC#7781
 *</pre>
 */
public class NMAL2570Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // QC#7781
        NMAL2570BMsg scrnMsg = (NMAL2570BMsg) bMsg;

        if(NMAL2570Constant.MULT_SEL_MODE_CD.equals(scrnMsg.xxModeCd_H1.getValue())){
            Object[] params = (Object[]) getArgForSubScreen();
            if (params instanceof Object[]) {
                // Person Code
                if (params.length >= 10) {
                    EZDBStringItem[] param = (EZDBStringItem[]) params[9];
                    for(EZDBStringItem item : param){
                        item.clear();
                    }
                    for(int i=0; i<scrnMsg.B.getValidCount(); i++){
                        if (i < param.length) {
                            ZYPEZDItemValueSetter.setValue(param[i], scrnMsg.B.no(i).psnCd_B1.getValue());
                        }
                    }
                }
            }
        }
    }
}
