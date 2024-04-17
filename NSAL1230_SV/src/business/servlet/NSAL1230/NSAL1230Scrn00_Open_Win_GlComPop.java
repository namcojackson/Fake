/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1230;

import static business.servlet.NSAL1230.constant.NSAL1230Constant.*;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL1230.NSAL1230CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/15   Hitachi         Y.Takeno        Create          N/A
 * 2016/03/23   Hitachi         Y.Takeno        Update          QC#2728
 *</pre>
 */
public class NSAL1230Scrn00_Open_Win_GlComPop extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1230BMsg scrnMsg = (NSAL1230BMsg) bMsg;
        NSAL1230CMsg bizMsg = new NSAL1230CMsg();

        int index = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRowNum, BigDecimal.valueOf(index));

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

// START 2016/03/23 Y.Takeno [QC#2728, ADD]
        scrnMsg.A.setCheckParam(new String[] {NSAL1230Bean.coaAfflAcctNm_A1 }, 1);
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        if ("W".equals(bizMsg.getMessageKind())) {
            return;
        }
// END   2016/03/23 Y.Takeno [QC#2728, ADD]

        int i = 0;
        int index = getButtonSelectNumber();

        scrnMsg.P.setValidCount(1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxRowNum_P1, BigDecimal.valueOf(index));
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).appFuncId_P1, BUSINESS_ID);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).coaCmpyCd_P1, scrnMsg.A.no(index).coaCmpyCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).coaAfflCd_P1, scrnMsg.A.no(index).coaAfflCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).coaBrCd_P1, scrnMsg.A.no(index).coaBrCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).coaCcCd_P1, scrnMsg.A.no(index).coaCcCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).coaAcctCd_P1, scrnMsg.A.no(index).coaAcctCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).coaProdCd_P1, scrnMsg.A.no(index).coaProdCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).coaChCd_P1, scrnMsg.A.no(index).coaChCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).coaProjCd_P1, scrnMsg.A.no(index).coaProjCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).coaExtnCd_P1, scrnMsg.A.no(index).coaExtnCd_A1);

        Object[] param = new Object[PARAM_ARRAY_LENGTH_NMAL2550];
        param[i++] = scrnMsg.P.no(0).appFuncId_P1;
        param[i++] = scrnMsg.P.no(0).coaCmpyCd_P1;
        param[i++] = scrnMsg.P.no(0).coaAfflCd_P1;
        param[i++] = scrnMsg.P.no(0).coaBrCd_P1;
        param[i++] = scrnMsg.P.no(0).coaCcCd_P1;
        param[i++] = scrnMsg.P.no(0).coaAcctCd_P1;
        param[i++] = scrnMsg.P.no(0).coaProdCd_P1;
        param[i++] = scrnMsg.P.no(0).coaChCd_P1;
        param[i++] = scrnMsg.P.no(0).coaProjCd_P1;
        param[i++] = scrnMsg.P.no(0).coaExtnCd_P1;

        setArgForSubScreen(param);
    }
}
