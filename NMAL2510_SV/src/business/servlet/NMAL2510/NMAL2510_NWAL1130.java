/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2510;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2510.constant.NMAL2510Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/19/  SRAA            Y.Chen          Create           QC#14595
 *</pre>
 */
public class NMAL2510_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;
        
        if (!NMAL2510Constant.POPUP_CLOSE.equals(this.getLastGuard())) {
            StringBuilder fullName = new StringBuilder();
            String contactID = scrnMsg.L.no(0).xxComnScrColValTxt.getValue();
            String fisrtName = scrnMsg.L.no(3).xxComnScrColValTxt.getValue();
            String lastName = scrnMsg.L.no(4).xxComnScrColValTxt.getValue();
    
            if (ZYPCommonFunc.hasValue(contactID)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.ctacPsnPk_D1, new BigDecimal(contactID));
            }
            if (ZYPCommonFunc.hasValue(fisrtName)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.ctacPsnFirstNm_D1, fisrtName);
                fullName.append(fisrtName);
                fullName.append(NMAL2510Constant.SPACE);
            }
            if (ZYPCommonFunc.hasValue(lastName)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.ctacPsnLastNm_D1, lastName);
                fullName.append(lastName);
            }
    
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPsnNm_D1, fullName.toString());
        }

        scrnMsg.setFocusItem(scrnMsg.ctacPsnPk_D1);
    }
}
