/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.BIZ_ID;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_CMPT_LINE;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0667E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Fujitsu         T.Yoshida       Create          N/A
 * 2016/07/05   Fujitsu         M.Hara          Update          S21_NA#7441
 *</pre>
 */
public class NWAL1500Scrn00_OpenWin_StockOverview extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);

        if (checkList.size() == 0) {
            scrnMsg.setMessageInfo(NWAM0667E, new String[] {MSG_PARAM_CMPT_LINE });
            throw new EZDFieldErrorException();
        }

        for (int slctLineNum : checkList) {
            NWAL1500_BBMsg lineMsg = scrnMsg.B.no(slctLineNum);
            scrnMsg.addCheckItem(lineMsg.mdseCd_LL);
            scrnMsg.addCheckItem(lineMsg.ordCustUomQty_LL);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2016/07/05 S21_NA#7441 Add Start
        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // 2016/07/05 S21_NA#7441 Add End
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        Object[] params = NWAL1500CommonLogic.getParamNLCL1000(scrnMsg);
        setArgForSubScreen(params);
    }
}
