/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0990;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0990.constant.NSAL0990Constant;
import business.servlet.NSAL0990.common.NSAL0990CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/15   Hitachi         U.Kim           Create          QC#18726
 *</pre>
 */
public class NSAL0990Scrn00_OpenWin_InventoryStockOverview extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;

        if (NSAL0990Constant.MODE_1.equals(scrnMsg.xxScrDply.getValue())) {

            List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_C", ZYPConstant.FLG_ON_Y);

            if (checkList.size() == 0) {
                scrnMsg.setMessageInfo(NSAL0990Constant.NSAM0015E);
                throw new EZDFieldErrorException();
            }

            for (int slctLineNum : checkList) {
                NSAL0990_CBMsg lineMsg = scrnMsg.C.no(slctLineNum);
                scrnMsg.addCheckItem(lineMsg.mdseCd_C);
                scrnMsg.addCheckItem(lineMsg.ordCustUomQty_C);
            }
            scrnMsg.putErrorScreen();
        } else if (NSAL0990Constant.MODE_2.equals(scrnMsg.xxScrDply.getValue())) {

            List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.E, "xxChkBox_E", ZYPConstant.FLG_ON_Y);

            if (checkList.size() == 0) {
                scrnMsg.setMessageInfo(NSAL0990Constant.NSAM0015E);
                throw new EZDFieldErrorException();
            }

            for (int slctLineNum : checkList) {
                NSAL0990_EBMsg lineMsg = scrnMsg.E.no(slctLineNum);
                scrnMsg.addCheckItem(lineMsg.mdseCd_E);
                scrnMsg.addCheckItem(lineMsg.ordCustUomQty_E);
            }
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        Object[] params = NSAL0990CommonLogic.getParamNLCL1000(scrnMsg);
        setArgForSubScreen(params);

    }
}
