/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/01/2009   Fujitsu         FXS)BY.Bao          Create          N/A
 * 09/28/2009   Fujitsu         FAP)Y.Furuta        Update          N/A
 *</pre>
 */
package business.servlet.NLCL0180;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0180.NLCL0180CMsg;
import business.servlet.NLCL0180.common.NLCL0180CommonLogic;
import business.servlet.NLCL0180.constant.NLCL0180Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NLCL0180_NMAL6050 extends S21CommonHandler implements NLCL0180Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NLCL0180CMsg bizMsg = null;
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0180BMsg scrnMsg = (NLCL0180BMsg) bMsg;

        NLCL0180CommonLogic.initialize(this, scrnMsg);

        NLCL0180CommonLogic.commonBtnControl_NLCL0180_NMAL6050(this);

        NLCL0180CommonLogic.scrnItemControl_NLCL0180_NMAL6050(scrnMsg);

        if (ZYPCommonFunc.hasValue(scrnMsg.vndNm_OT)) {
            scrnMsg.vndNm.setValue(scrnMsg.vndNm_OT.getValue());
        } else {
            // do nothing
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).mdseNm_A1.setInputProtected(true);
        }
    }
}
