/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3200;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3200.NLBL3200CMsg;
import business.servlet.NLBL3200.NLBL3200BMsg;
import business.servlet.NLBL3200.common.NLBL3200CommonLogic;
import business.servlet.NLBL3200.constant.NLBL3200Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Manage Shipping Orders
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   CITS            T.Tokutomi      Create          N/A
 * 06/19/2018   CITS            Y.Iwasaki       Update          QC#21717
 *</pre>
 */
public class NLBL3200Scrn00_Ship extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;

        NLBL3200CommonLogic.addCheckItemApply(scrnMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;

        NLBL3200CMsg bizMsg = new NLBL3200CMsg();
        bizMsg.setBusinessID(NLBL3200Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;
        NLBL3200CMsg bizMsg = (NLBL3200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(NLBL3200Constant.BUSINESS_ID);
        NLBL3200CommonLogic.ctrlScrnItemProtection(scrnMsg, this, funcList);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {

            NLBL3200CommonLogic.addCheckTableItem(scrnMsg);
            scrnMsg.putErrorScreen();

        } else if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_WARNING && ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxWrnSkipFlg.getValue())) {

            NLBL3200CommonLogic.addCheckTableItem(scrnMsg);
            scrnMsg.putErrorScreen();

        } else {

            scrnMsg.setMessageInfo(NLBL3200Constant.ZZZM9003I, new String[] {"Ship Confirmation" });
            scrnMsg.setFocusItem(scrnMsg.trxHdrNum_H1);
        }
    }
}
