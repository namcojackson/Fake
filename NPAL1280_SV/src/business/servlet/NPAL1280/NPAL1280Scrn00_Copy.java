/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1280;

import static business.servlet.NPAL1280.constant.NPAL1280Constant.BUSINESS_APPL_ID;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.BUSINESS_SCREEN_ID;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.NAMM0027E;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.PRCH_REQ_NUM;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.TAB_DETAIL;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1280.NPAL1280CMsg;
import business.servlet.NPAL1280.common.NPAL1280CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Business ID : NPAL1280 PO Requisition Entry
 * Function Name : Copy
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/28/2016   CITS            K.Ogino          Create          N/A
 * 03/01/2016   CITS            K.Ogino          Update          QC#4729
 * 08/20/2018   CITS            K.Ogino          Update          QC#27846
 *</pre>
 */
public class NPAL1280Scrn00_Copy extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // QC#27846
        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;
        scrnMsg.clearErrorInfo();
        scrnMsg.addCheckItem(scrnMsg.prchReqNum);
        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
        scrnMsg.addCheckItem(scrnMsg.dsOrdPosnNum);

        if (ZYPCommonFunc.hasValue(scrnMsg.dsOrdPosnNum) && !ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum)) {
            scrnMsg.cpoOrdNum.setErrorInfo(1, NAMM0027E, new String[] {"Order#" });
        }
        
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;
        NPAL1280CMsg bizMsg = new NPAL1280CMsg();
        bizMsg.setBusinessID(BUSINESS_APPL_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;
        NPAL1280CMsg bizMsg = (NPAL1280CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if ("E".equals(bizMsg.getMessageKind())) {
            scrnMsg.addCheckItem(scrnMsg.prchReqNum);
            scrnMsg.putErrorScreen();
        } else {
            scrnMsg.prchReqNum.clear();
            scrnMsg.cpoOrdNum.clear();
            // QC#27846
            scrnMsg.dsOrdPosnNum.clear();
            String glblCmpyCd = getGlobalCompanyCode();
            String loginFullName = getContextUserInfo().getFullName();
            String loginUserId = getContextUserInfo().getUserId();
            List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_APPL_ID);
            if (bizMsg.A.getValidCount() > 0) {
                String salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
                NPAL1280CommonLogic.controlItemsOnInit(this, scrnMsg, glblCmpyCd, loginFullName, loginUserId, funcList, salesDate);
            }

            scrnMsg.setFocusItem(scrnMsg.prchReqNum);
            scrnMsg.xxDplyTab.setValue(TAB_DETAIL);
            S21TableColorController tblColor = new S21TableColorController(BUSINESS_SCREEN_ID, scrnMsg);
            tblColor.setAlternateRowsBG("A", scrnMsg.A);
        }

    }
}
