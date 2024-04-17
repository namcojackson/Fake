/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1350;

import static business.servlet.NPAL1350.constant.NPAL1350Constant.BIZ_APP_ID;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.FUNCTION_CD_SEARCH;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1350.NPAL1350CMsg;
import business.servlet.NPAL1350.common.NPAL1350CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NPAL1350 Kitting WO Search
 * Function Name : Reset
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/26/2016   CITS            Makoto Okigami  Create          N/A
 *</pre>
 */
public class NPAL1350Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Do nothing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1350BMsg scrnMsg = (NPAL1350BMsg) bMsg;

        EZDBStringItem str = scrnMsg.xxComnColOrdTxt;

        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.clear();

        NPAL1350CMsg bizMsg = new NPAL1350CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // Set UserID
        ZYPEZDItemValueSetter.setValue(bizMsg.srchOptUsrId_U1, getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxComnColOrdTxt, str);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1350BMsg scrnMsg = (NPAL1350BMsg) bMsg;
        NPAL1350CMsg bizMsg  = (NPAL1350CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);

        NPAL1350CommonLogic.ctrlScrnItemDispInit(this, scrnMsg, funcList);

        // Set Forcus
        scrnMsg.setFocusItem(scrnMsg.wrkOrdNum);
    }
}