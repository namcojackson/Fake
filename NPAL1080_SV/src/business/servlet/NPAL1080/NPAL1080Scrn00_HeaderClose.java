/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1080;

import static business.servlet.NPAL1080.constant.NPAL1080Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.MODE_BTN_OTHER;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.NPAM1598I;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1080.NPAL1080CMsg;
import business.servlet.NPAL1080.common.NPAL1080CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1080 Tech Parts Request Entry
 * Function Name : Button Action to Close
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/22/2016   CITS            K.Ogino         Create          QC#8295
 *</pre>
 */
public class NPAL1080Scrn00_HeaderClose extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxSupdFlg_CL.getValue())) {
            scrnMsg.setMessageInfo(NPAM1598I);
            scrnMsg.putErrorScreen();
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSupdFlg_CL, ZYPConstant.FLG_ON_Y);
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;

        NPAL1080CMsg bizMsg = new NPAL1080CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
        NPAL1080CMsg bizMsg = (NPAL1080CMsg) cMsg;

        if (!"E".equals(bizMsg.getMessageKind())) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_APPLICATION_ID);
            NPAL1080CommonLogic.commonInit(scrnMsg);
            NPAL1080CommonLogic.control(this, scrnMsg, MODE_BTN_OTHER, funcList);
            scrnMsg.setFocusItem(scrnMsg.prchReqNum_H1);
        }

    }
}
