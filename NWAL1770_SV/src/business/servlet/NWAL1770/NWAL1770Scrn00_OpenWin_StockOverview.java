/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1770;

import static business.servlet.NWAL1770.constant.NWAL1770Constant.MSG_PARAM_ITEM_LINE;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.NWAM0667E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1770.common.NWAL1770CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/21   Fujitsu         T.Yoshida       Create          N/A
 *</pre>
 */
public class NWAL1770Scrn00_OpenWin_StockOverview extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_B", ZYPConstant.FLG_ON_Y);

        if (checkList.size() == 0) {
            scrnMsg.setMessageInfo(NWAM0667E, new String[] {MSG_PARAM_ITEM_LINE });
            throw new EZDFieldErrorException();
        }

        for (int slctLineNum : checkList) {
            NWAL1770_BBMsg itemLineMsg = scrnMsg.B.no(slctLineNum);
            scrnMsg.addCheckItem(itemLineMsg.mdseCd_B);
            scrnMsg.addCheckItem(itemLineMsg.ordCustUomQty_B);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        Object[] params = NWAL1770CommonLogic.getParamNLCL1000(scrnMsg);
        setArgForSubScreen(params);
    }
}
