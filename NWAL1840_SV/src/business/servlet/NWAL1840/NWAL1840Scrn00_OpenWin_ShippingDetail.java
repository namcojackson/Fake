/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1840;

import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_0;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_1;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.MSG_PARAM_SCHD_LINE;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.NWAM0667E;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.NWAM0681E;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.NWAM0802E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NWAL1840.common.NWAL1840CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Fujitsu         T.Murai         Create          N/A
 * 2016/07/19   Fujitsu         T.Murai         Update          QC#11797
 * 2016/09/16   Fujitsu         T.Murai         Update          QC#13040
 *</pre>
 */
public class NWAL1840Scrn00_OpenWin_ShippingDetail extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_B", ZYPConstant.FLG_ON_Y);

        if (checkList.size() == 0) {
            scrnMsg.setMessageInfo(NWAM0667E, new String[] {MSG_PARAM_SCHD_LINE });
            throw new EZDFieldErrorException(); // Add 2016/07/19 QC#11797

        } else if (checkList.size() != 1) {
            scrnMsg.setMessageInfo(NWAM0681E, new String[] {MSG_PARAM_SCHD_LINE });
            throw new EZDFieldErrorException(); // Add 2016/07/19 QC#11797

        } else if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(checkList.get(IDX_0)).cpoOrdNum_B)) {
            scrnMsg.B.no(checkList.get(IDX_0)).xxChkBox_B.setErrorInfo(1, NWAM0802E, new String[] {MSG_PARAM_SCHD_LINE });
            
            // Add 2016/09/16 S21_NA#13040
            NWAL1840CommonLogic.expandSchdLineBySchdPln(scrnMsg, scrnMsg.B.no(checkList.get(IDX_0)).schdAgmtLineNum_B.getValueInt());

            // Add Start 2016/07/19 QC#11797
            scrnMsg.addCheckItem(scrnMsg.B.no(checkList.get(IDX_0)).xxChkBox_B);
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
            // Add End 2016/07/19 QC#11797
        }
        scrnMsg.xxCellIdx.setValue(checkList.get(IDX_0));
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }
        // set param
        Object[] params = new Object[IDX_1];
        params[IDX_0] = scrnMsg.B.no(scrnMsg.xxCellIdx.getValueInt()).cpoOrdNum_B;
        setArgForSubScreen(params);
        openMultiModeScreen();
    }
}
