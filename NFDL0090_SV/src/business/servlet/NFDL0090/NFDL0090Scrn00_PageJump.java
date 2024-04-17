/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0090;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0090.NFDL0090CMsg;
import business.servlet.NFDL0090.common.NFDL0090CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/12    Fujitsu         M.Nakamura      Create          N/A
 * 2018/09/11    Hitachi         Y.Takeno        Update          QC#24884
 *</pre>
 */
public class NFDL0090Scrn00_PageJump extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0090BMsg scrnMsg = (NFDL0090BMsg) bMsg;

        NFDL0090CommonLogic.checkDetail(scrnMsg);

        S21BatchSearchPagenationScrnSupport.checkInputForPageJump(
            scrnMsg
            , scrnMsg.A
            , scrnMsg.xxPageShowFromNum
            , scrnMsg.xxPageShowToNum
            , scrnMsg.xxPageShowOfNum
            , scrnMsg.xxPageShowCurNum
            , scrnMsg.xxPageShowTotNum);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0090BMsg scrnMsg = (NFDL0090BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageShowFromNum_BK, scrnMsg.xxPageShowFromNum.getValue().subtract(BigDecimal.ONE));

        // Clear scrnMsg.A - Use Edit Screen
        S21BatchSearchPagenationScrnSupport.setRequestDataForPageJump(
            scrnMsg
            , scrnMsg.A
            , scrnMsg.xxPageShowFromNum
            , scrnMsg.xxPageShowToNum
            , scrnMsg.xxPageShowOfNum
            , scrnMsg.xxPageShowCurNum
            , scrnMsg.xxPageShowTotNum);

        NFDL0090CMsg bizMsg = new NFDL0090CMsg();
        bizMsg.setBusinessID("NFDL0090");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFDL0090BMsg scrnMsg = (NFDL0090BMsg) bMsg;
        NFDL0090CMsg bizMsg  = (NFDL0090CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2018/09/11 [QC#24884, ADD]
        NFDL0090CommonLogic.protectHeader(scrnMsg);
        // END   2018/09/11 [QC#24884, ADD]
        NFDL0090CommonLogic.protectDetail(scrnMsg);
    }
}
