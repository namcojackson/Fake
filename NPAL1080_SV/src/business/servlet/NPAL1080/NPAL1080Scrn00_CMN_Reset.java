/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1080;

import static business.servlet.NPAL1080.constant.NPAL1080Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.MODE_BTN_INIT;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.MODE_BTN_OTHER;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.TAB_DETAIL;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1080.NPAL1080CMsg;
import business.servlet.NPAL1080.common.NPAL1080CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1080 Tech Parts Request Entry
 * Function Name : Button Action to Reset
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/16/2015   CITS       Yasushi Nomura        Create          N/A
 * 12/19/2016   CITS            K.Ogino         Update          QC#15820
 * 10/01/2019   Fujitsu         T.Ogura         Update          QC#53307
 *</pre>
 */
public class NPAL1080Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length == 1) {
            // START 2019/10/01 T.Ogura [QC#53307,MOD]
//            EZDBStringItem param01 = (EZDBStringItem) params[0];
//            ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqNum_H1, param01);
            Object param01 = (Object) params[0];
            if (param01 instanceof EZDBStringItem) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqNum_H1, (EZDBStringItem) param01);

            } else {
                // String
                ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqNum_H1, (String) param01);
            }
            // END   2019/10/01 T.Ogura [QC#53307,MOD]
        } else {
            scrnMsg.prchReqNum_H1.clear();
        }

        NPAL1080CMsg bizMsg = new NPAL1080CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
        NPAL1080CMsg bizMsg = (NPAL1080CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // initial only
        NPAL1080CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NPAL1080CommonLogic.setAppFracDigit(scrnMsg);
        NPAL1080CommonLogic.commonInit(scrnMsg);

        scrnMsg.xxDplyTab.setValue(TAB_DETAIL);
        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_APPLICATION_ID);
        if (ZYPCommonFunc.hasValue(scrnMsg.prchReqNum_H1)) {
            NPAL1080CommonLogic.control(this, scrnMsg, MODE_BTN_OTHER, funcList);
        } else {
            NPAL1080CommonLogic.control(this, scrnMsg, MODE_BTN_INIT, funcList);
        }
        scrnMsg.setFocusItem(scrnMsg.prchReqNum_H1);
    }
}
