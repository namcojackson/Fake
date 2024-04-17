/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0230;

import static business.servlet.NMAL0230.constant.NMAL0230Constant.BIZ_ID;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.BOM_PROD_POPUP;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.CMPSN_PROD_POPUP;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0230.NMAL0230CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/26   Fujitsu         C.Tanaka        Create          CSA
 *</pre>
 */
public class NMAL0230_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0230BMsg scrnMsg = (NMAL0230BMsg) bMsg;

        NMAL0230CMsg bizMsg = new NMAL0230CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NMAL0230BMsg scrnMsg = (NMAL0230BMsg) bMsg;

        if (BOM_PROD_POPUP.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.coaProdCd_BO, scrnMsg.O.no(9).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.coaProdNm_BO, scrnMsg.O.no(10).xxPopPrm);
            scrnMsg.setFocusItem(scrnMsg.coaProdCd_BO);
        } else if (CMPSN_PROD_POPUP.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.coaProdCd_CO, scrnMsg.O.no(9).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.coaProdNm_CO, scrnMsg.O.no(10).xxPopPrm);
            scrnMsg.setFocusItem(scrnMsg.coaProdCd_CO);
        }
    }
}
