/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1840;

import static business.servlet.NWAL1840.constant.NWAL1840Constant.BIZ_ID;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.FUNC_CD_SRCH;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.MSG_PARAM_QTY_ALLW;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.NWAM0683E;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.NWAM0962E;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.ZZM9000E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1840.NWAL1840CMsg;
import business.servlet.NWAL1840.common.NWAL1840CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1840Scrn00_OpenWin_PriceChanges
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/17   Fujitsu         T.Murai         Create          N/A
 * 2018/04/16   Fujitsu         H.Nagashima     Update          QC#22965
 * 2018/08/08   Fujitsu         M.Ishii         Update          QC#26551
 * 2019/12/10   Fujitsu         S.Kosaka        Update          QC#54233
 *</pre>
 */
public class NWAL1840Scrn00_OpenWin_PriceChanges extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A", ZYPConstant.FLG_ON_Y);

//        if (selectedRows.size() == 0) {
//            scrnMsg.setMessageInfo(NWAM0667E, new String[] {MSG_PARAM_LINE });
//            return;
//        }

        if (selectedRows.size() > 1) {
            scrnMsg.setMessageInfo(NWAM0683E);
            return;
        }
        // 2018/08/08 QC#26551 Add Start
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).dealNetUnitPrcAmt_A)) {
                scrnMsg.A.no(i).dealNetUnitPrcAmt_A.setErrorInfo(1, NWAM0962E);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).dealNetUnitPrcAmt_A);

            } else if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdseCd_A)) {
                scrnMsg.A.no(i).mdseCd_A.setErrorInfo(1, ZZM9000E, new String[] {"Item#" });
                scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_A);
            }
            // 2019/12/10 QC#54233 Add Start
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).schdAllwQty_A)) {
                scrnMsg.A.no(i).schdAllwQty_A.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_QTY_ALLW });
                scrnMsg.addCheckItem(scrnMsg.A.no(i).schdAllwQty_A);
            }
            // 2019/12/10 QC#54233 Add End
        }
        // 2018/08/08 QC#26551 Add End
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;

        NWAL1840CMsg bizMsg = new NWAL1840CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;
        NWAL1840CMsg bizMsg = (NWAL1840CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        Object[] params = NWAL1840CommonLogic.getParamNWAL1660(scrnMsg);

        setArgForSubScreen(params);
    }
}
