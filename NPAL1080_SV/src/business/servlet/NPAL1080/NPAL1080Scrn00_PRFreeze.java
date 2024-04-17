/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1080;

import static business.servlet.NPAL1080.constant.NPAL1080Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.MODE_BTN_OTHER;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.NPAM0049E;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.NPAM1649E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1080.NPAL1080CMsg;
import business.servlet.NPAL1080.common.NPAL1080CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1080 Tech Parts Request Entry
 * Function Name : Button Action to Freeze
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/16/2015   CITS       Yasushi Nomura       Create          N/A
 * 02/13/2020   CITS            Y.Iwasaki       Update          QC#55702,55709
 *</pre>
 */
public class NPAL1080Scrn00_PRFreeze extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
        int chkCount = 0;
        int cancelCount = 0;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_D1.getValue())) {
                ++chkCount;
                if (PRCH_REQ_LINE_STS.CANCELLED.equals(scrnMsg.A.no(i).prchReqLineStsCd_D1.getValue())) {
                    ++cancelCount;
                }
            }
        }

        if (chkCount <= 0) {
            scrnMsg.setMessageInfo(NPAM0049E);
            throw new EZDFieldErrorException();
        }
        if (cancelCount > 0) {
            scrnMsg.setMessageInfo(NPAM1649E);
            throw new EZDFieldErrorException();
        }

        NPAL1080CommonLogic.inputCheck(scrnMsg);
        scrnMsg.putErrorScreen();
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

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NPAL1080CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_APPLICATION_ID);
        NPAL1080CommonLogic.commonInit(scrnMsg);
        NPAL1080CommonLogic.control(this, scrnMsg, MODE_BTN_OTHER, funcList);
    }
}
