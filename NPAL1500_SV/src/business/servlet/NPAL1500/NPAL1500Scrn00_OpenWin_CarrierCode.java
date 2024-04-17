/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1500;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   CITS            N.Akaishi       Create          n/a
 *</pre>
 */
public class NPAL1500Scrn00_OpenWin_CarrierCode extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        scrnMsg.xxTblNm_P1.clear();
        scrnMsg.xxTblCdColNm_P1.clear();
        scrnMsg.xxTblNmColNm_P1.clear();
        scrnMsg.xxTblSortColNm_P1.clear();
        scrnMsg.xxScrNm_P1.clear();
        scrnMsg.xxHdrCdLbNm_P1.clear();
        scrnMsg.xxHdrNmLbNm_P1.clear();
        scrnMsg.xxDtlCdLbNm_P1.clear();
        scrnMsg.xxDtlNmLbNm_P1.clear();
        scrnMsg.xxCondCd_P1.clear();
        scrnMsg.xxCondNm_P1.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, "OTBD_CARR_V");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P1, "CARR_CD");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P1, "CARR_NM");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P1, "CARR_CD");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, "Carrier Search");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, "Carrier Code");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, "Carrier Name");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, "Carrier Code");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, "Carrier Name");
        if (ZYPCommonFunc.hasValue(scrnMsg.carrCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxCondCd_P1, scrnMsg.carrCd);
        }

        int paramCount = 0;
        Object[] params = new Object[11];
        params[paramCount++] = scrnMsg.xxTblNm_P1;
        params[paramCount++] = scrnMsg.xxTblCdColNm_P1;
        params[paramCount++] = scrnMsg.xxTblNmColNm_P1;
        params[paramCount++] = scrnMsg.xxTblSortColNm_P1;
        params[paramCount++] = scrnMsg.xxScrNm_P1;
        params[paramCount++] = scrnMsg.xxHdrCdLbNm_P1;
        params[paramCount++] = scrnMsg.xxHdrNmLbNm_P1;
        params[paramCount++] = scrnMsg.xxDtlCdLbNm_P1;
        params[paramCount++] = scrnMsg.xxDtlNmLbNm_P1;
        params[paramCount++] = scrnMsg.xxCondCd_P1;
        params[paramCount++] = scrnMsg.xxCondNm_P1;
        setArgForSubScreen(params);
    }
}
