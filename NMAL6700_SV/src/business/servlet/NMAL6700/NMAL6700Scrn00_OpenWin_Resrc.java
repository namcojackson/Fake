/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700;

import static business.servlet.NMAL6700.constant.NMAL6700Constant.ZZM9001E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6700.common.NMAL6700CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Fujitsu         N.Sugiura       Create          N/A
 *</pre>
 */
public class NMAL6700Scrn00_OpenWin_Resrc extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;
        int selectIdx = getButtonSelectNumber();
        NMAL6700_GBMsg gBMsg = scrnMsg.G.no(selectIdx);

        scrnMsg.addCheckItem(gBMsg.xxGenlFldAreaTxt_G1);
        scrnMsg.putErrorScreen();

        String psnCdList = gBMsg.xxGenlFldAreaTxt_G1.getValue();
        String[] psnCdArray = NMAL6700CommonLogic.splitByComma(psnCdList);
        for (String psnCd : psnCdArray) {
            if (psnCd.length() > scrnMsg.Q.no(0).getAttr("psnCd_Q1").getDigit()) {
                gBMsg.xxGenlFldAreaTxt_G1.setErrorInfo(1, ZZM9001E, new String[] {"Internal Email Review" });
                scrnMsg.addCheckItem(gBMsg.xxGenlFldAreaTxt_G1);
                break;
            }
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        int selectIdx = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(selectIdx);

        Object[] params = NMAL6700CommonLogic.setParamForResourceSearchPopup(scrnMsg);

        setArgForSubScreen(params);
    }
}
