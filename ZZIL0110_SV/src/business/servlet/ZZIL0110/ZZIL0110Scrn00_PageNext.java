/**
 * <pre>
 * Date         Company         Name           Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/23/2013   Fujitsu         M.Yaguchi      Create          N/A
 *</pre>
 */
package business.servlet.ZZIL0110;

import parts.common.*;
import parts.servletcommon.*;
import business.blap.ZZIL0110.ZZIL0110CMsg;
import business.servlet.ZZIL0110.common.ZZIL0110CommonLogic;
import business.servlet.ZZIL0110.constant.ZZIL0110Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZIL0110Scrn00_PageNext extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // ZZIL0110BMsg scrnMsg = (ZZIL0110BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZIL0110BMsg scrnMsg = (ZZIL0110BMsg) bMsg;

        // set values to items of pagenation for next page pagenation
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowToNum.getValueInt());
        scrnMsg.xxPageShowToNum.clear();

        ZZIL0110CMsg bizMsg = new ZZIL0110CMsg();
        bizMsg.setBusinessID(ZZIL0110Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(ZZIL0110Constant.ReadCode);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZIL0110BMsg scrnMsg = (ZZIL0110BMsg) bMsg;
        ZZIL0110CMsg bizMsg = (ZZIL0110CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // clear image file of sort columns (Gif file. ASC or DESC.)
        S21SortColumnIMGController.clearIMG(ZZIL0110Constant.SCREEN_ID0, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        ZZIL0110CommonLogic.setTableColorA(scrnMsg, bizMsg);
        ZZIL0110CommonLogic.checkCommonInputScrn00(scrnMsg);
    }

}
