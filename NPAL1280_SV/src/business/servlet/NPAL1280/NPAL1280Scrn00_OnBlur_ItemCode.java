/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1280;

import static business.servlet.NPAL1280.constant.NPAL1280Constant.BUSINESS_APPL_ID;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.FUNCTION_CD_SEARCH;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1280.NPAL1280CMsg;
import business.servlet.NPAL1280.common.NPAL1280CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NPAL1280 PO Requisition Entry
 * Function Name : OnBlur_ItemCode
 * OnChange_LineType
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/28/2016   CITS            K.Ogino          Create          N/A
 *</pre>
 */
public class NPAL1280Scrn00_OnBlur_ItemCode extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;
        NPAL1280CMsg bizMsg = new NPAL1280CMsg();
        int lineNum = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxLineNum, Integer.valueOf(lineNum).toString());
        bizMsg.setBusinessID(BUSINESS_APPL_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;
        NPAL1280CMsg bizMsg = (NPAL1280CMsg) cMsg;
        int lineNum = getButtonSelectNumber();
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        String glblCmpyCd = getGlobalCompanyCode();
        String loginFullName = getContextUserInfo().getFullName();
        String loginUserId = getContextUserInfo().getUserId();
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineNum).mdseCd_HD, scrnMsg.A.no(lineNum).mdseCd_A1);
        NPAL1280CommonLogic.retCheckItems(scrnMsg);
        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_APPL_ID);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (PO_MDSE_CMPSN_TP.CHILD.equals(scrnMsg.A.no(i).poMdseCmpsnTpCd_A1.getValue()) || (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).openStsFlg_D) && ZYPConstant.FLG_OFF_N.equals(scrnMsg.A.no(i).openStsFlg_D.getValue()))) {
                NPAL1280CommonLogic.controlItemsChild(this, scrnMsg, glblCmpyCd, funcList, i);
            } else {
                NPAL1280CommonLogic.controlItemsDetailLineDef(this, scrnMsg, i);
            }
        }
    }
}
