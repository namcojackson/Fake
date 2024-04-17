/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1230;

import static business.servlet.NSAL1230.constant.NSAL1230Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1230.NSAL1230CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/11   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1230_NMAL2550 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1230BMsg scrnMsg = (NSAL1230BMsg) bMsg;
        NSAL1230CMsg bizMsg = new NSAL1230CMsg();

        int index = scrnMsg.P.no(0).xxRowNum_P1.getValueInt();
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).coaCmpyCd_A1, scrnMsg.P.no(0).coaCmpyCd_P1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).coaAfflCd_A1, scrnMsg.P.no(0).coaAfflCd_P1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).coaBrCd_A1, scrnMsg.P.no(0).coaBrCd_P1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).coaCcCd_A1, scrnMsg.P.no(0).coaCcCd_P1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).coaAcctCd_A1, scrnMsg.P.no(0).coaAcctCd_P1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).coaProdCd_A1, scrnMsg.P.no(0).coaProdCd_P1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).coaChCd_A1, scrnMsg.P.no(0).coaChCd_P1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).coaProjCd_A1, scrnMsg.P.no(0).coaProjCd_P1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).coaExtnCd_A1, scrnMsg.P.no(0).coaExtnCd_P1);

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUCNTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1230BMsg scrnMsg = (NSAL1230BMsg) bMsg;
        NSAL1230CMsg bizMsg  = (NSAL1230CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
