/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1280;

import static business.servlet.NPAL1280.constant.NPAL1280Constant.BUSINESS_APPL_ID;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.FUNCTION_CD_UPDATE;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1280.NPAL1280CMsg;
import business.servlet.NPAL1280.common.NPAL1280CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NPAL1280 PO Requisition Entry
 * Function Name : CMN_Submit
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/28/2016   CITS            K.Ogino          Create          N/A
 * 02/28/2016   CITS            K.Ogino          Create          QC#4686
 *</pre>
 */
public class NPAL1280Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;
        String glblCmpyCd = getGlobalCompanyCode();
        NPAL1280CommonLogic.checkItem(scrnMsg, glblCmpyCd);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;

        NPAL1280CMsg bizMsg = new NPAL1280CMsg();
        bizMsg.setBusinessID(BUSINESS_APPL_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

        // return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;
        NPAL1280CMsg bizMsg = (NPAL1280CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        String glblCmpyCd = getGlobalCompanyCode();
        String loginFullName = getContextUserInfo().getFullName();
        String loginUserId = getContextUserInfo().getUserId();
        NPAL1280CommonLogic.retCheckItems(scrnMsg);
        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_APPL_ID);
        if (scrnMsg.A.getValidCount() > 0 && ZYPCommonFunc.hasValue(scrnMsg.openStsFlg_H)) {
            if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.openStsFlg_H.getValue())) {
                NPAL1280CommonLogic.controlItemsOnSearchNoOpen(this, scrnMsg, glblCmpyCd, funcList);
            } else if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.prchReqSavedFlg.getValue())) {
                NPAL1280CommonLogic.controlItemsOnSearchOpenY(this, scrnMsg, glblCmpyCd, funcList);
            } else if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.prchReqSavedFlg.getValue())) {
                NPAL1280CommonLogic.controlItemsOnSearchOpenN(this, scrnMsg, glblCmpyCd, loginFullName, loginUserId, funcList, false);
            }
        }
        scrnMsg.putErrorScreen();
    }
}
