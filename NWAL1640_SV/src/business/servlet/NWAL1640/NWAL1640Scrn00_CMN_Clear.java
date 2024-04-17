/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1640;

import static business.servlet.NWAL1640.constant.NWAL1640Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1640.NWAL1640CMsg;
import business.servlet.NWAL1640.common.NWAL1640CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1640Scrn00_CMN_Clear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/02   Fujitsu         C.Yokoi         Create          N/A
 * 2019/10/04   Fujitsu         K.Kato          Update          S21_NA#51372
 *</pre>
 */
public class NWAL1640Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1640BMsg scrnMsg = (NWAL1640BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(scrnMsg.splyTpCd, scrnMsg.splyTpCd_BK);
        // 2019/10/04 S21_NA#51372 Mod Start
        //ZYPEZDItemValueSetter.setValue(scrnMsg.splyNm, scrnMsg.splyNm_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm, scrnMsg.prntVndNm_BK);
        // 2019/10/04 S21_NA#51372 Mod End
        ZYPEZDItemValueSetter.setValue(scrnMsg.splyFirstAddr, scrnMsg.splyFirstAddr_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.splyStCd, scrnMsg.splyStCd_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.stNm, scrnMsg.stNm_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.splyCtyAddr, scrnMsg.splyCtyAddr_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.splyPostCd, scrnMsg.splyPostCd_BK);

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

        NWAL1640CommonLogic.initCmnBtnProp(this, scrnMsg);

        scrnMsg.stNm.setInputProtected(true);
        scrnMsg.setFocusItem(scrnMsg.splyTpCd);
    }
}
