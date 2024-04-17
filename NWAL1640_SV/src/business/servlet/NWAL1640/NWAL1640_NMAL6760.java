/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1640;

import static business.servlet.NWAL1640.constant.NWAL1640Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1640.NWAL1640CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1640_NMAL6760
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/26   Hitachi         T.Tomita        Create          CSA QC#1029
 * 2019/10/04   Fujitsu         K.Kato          Update          S21_NA#51372
 *</pre>
 */
public class NWAL1640_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1640BMsg scrnMsg = (NWAL1640BMsg) bMsg;

        // 2019/10/04 S21_NA#51372 Mod Start
        //ZYPEZDItemValueSetter.setValue(scrnMsg.splyNm, scrnMsg.P.no(1).xxPopPrm_P);
        String prntVndNm = scrnMsg.P.no(1).xxPopPrm_P.getValue();
        if (prntVndNm.length() >  scrnMsg.getAttr("prntVndNm").getDigit()) {
            prntVndNm = prntVndNm.substring(0, scrnMsg.getAttr("prntVndNm").getDigit());
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm, prntVndNm);
        // 2019/10/04 S21_NA#51372 Mod End
        ZYPEZDItemValueSetter.setValue(scrnMsg.splyFirstAddr, scrnMsg.P.no(4).xxPopPrm_P);
        ZYPEZDItemValueSetter.setValue(scrnMsg.splyCtyAddr, scrnMsg.P.no(5).xxPopPrm_P);
        ZYPEZDItemValueSetter.setValue(scrnMsg.splyStCd, scrnMsg.P.no(6).xxPopPrm_P);
        ZYPEZDItemValueSetter.setValue(scrnMsg.splyPostCd, scrnMsg.P.no(7).xxPopPrm_P);

        if (ZYPCommonFunc.hasValue(scrnMsg.splyStCd)) {
            NWAL1640CMsg bizMsg = new NWAL1640CMsg();
            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1640BMsg scrnMsg = (NWAL1640BMsg) bMsg;

        if (ZYPCommonFunc.hasValue(scrnMsg.splyStCd)) {
            NWAL1640CMsg bizMsg = (NWAL1640CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        scrnMsg.setFocusItem(scrnMsg.splyTpCd);
    }
}
