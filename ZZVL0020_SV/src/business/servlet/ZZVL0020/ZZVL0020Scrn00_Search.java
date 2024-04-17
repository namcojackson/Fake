/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZVL0020;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZVL0020.ZZVL0020CMsg;
import business.servlet.ZZVL0020.common.ZZVL0020CommonLogic;
import business.servlet.ZZVL0020.constant.ZZVL0020Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/26   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZVL0020Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZVL0020BMsg scrnMsg = (ZZVL0020BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd_1);

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZVL0020BMsg scrnMsg = (ZZVL0020BMsg) bMsg;

        // set values to items of pagenation for next page pagenation
        ZYPTableUtil.clear(scrnMsg.C);
        scrnMsg.xxPageShowFromNum_1.clear();
        scrnMsg.xxPageShowToNum_1.clear();
        scrnMsg.xxPageShowOfNum_1.clear();

        // set values to items of pagenation for next page pagenation
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum_A1.clear();
        scrnMsg.xxPageShowToNum_A1.clear();
        scrnMsg.xxPageShowOfNum_A1.clear();

        ZZVL0020CMsg bizMsg = new ZZVL0020CMsg();
        bizMsg.setBusinessID("ZZVL0020");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

//        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZVL0020BMsg scrnMsg = (ZZVL0020BMsg) bMsg;
        ZZVL0020CMsg bizMsg  = (ZZVL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21SortColumnIMGController.clearIMG(ZZVL0020Constant.SCREEN_NAME0, scrnMsg, scrnMsg.C.no(0).getBaseContents());

        ZZVL0020CommonLogic.dspScrn00(scrnMsg, this);
        setResult(ZZVL0020Constant.SHOW_RESULT);

        if (scrnMsg.C.getValidCount() == 1) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum_1, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(scrnMsg.roleNm_2, scrnMsg.C.no(0).roleNm_C.getValue());
            S21SortColumnIMGController.clearIMG(ZZVL0020Constant.SCREEN_NAME1, scrnMsg, scrnMsg.A.no(0).getBaseContents());
            S21SortColumnIMGController.clearIMG(ZZVL0020Constant.SCREEN_NAME1, scrnMsg, scrnMsg.B.no(0).getBaseContents());
            ZZVL0020CommonLogic.dspScrn01(scrnMsg, this);

            setResult(ZZVL0020Constant.GO_TO_MAINTENANCE);
        }
    }
}