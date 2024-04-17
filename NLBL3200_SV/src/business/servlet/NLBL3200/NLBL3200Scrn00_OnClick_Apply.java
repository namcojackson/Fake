/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3200;

import static business.servlet.NLBL3200.constant.NLBL3200Constant.BUSINESS_ID;
import static business.servlet.NLBL3200.constant.NLBL3200Constant.SCRN_ID;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3200.NLBL3200CMsg;
import business.servlet.NLBL3200.NLBL3200BMsg;
import business.servlet.NLBL3200.common.NLBL3200CommonLogic;
import business.servlet.NLBL3200.constant.NLBL3200Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Business ID : NMAL2020 Manage Shipping Orders
 * Function Name : Click Apply Button
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/03/2016   CSAI            D.Fukaya        Create          QC# 2200
 *</pre>
 */
public class NLBL3200Scrn00_OnClick_Apply extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;

        // check Ship Cost format check
        scrnMsg.addCheckItem(scrnMsg.totFrtAmt_D1);
        scrnMsg.putErrorScreen();

        // check mandatory
        if (!ZYPCommonFunc.hasValue(scrnMsg.carrNm_D1) && !ZYPCommonFunc.hasValue(scrnMsg.totFrtAmt_D1) && !ZYPCommonFunc.hasValue(scrnMsg.proNum_D1)) {

            // All blank
            scrnMsg.carrNm_D1.setErrorInfo(1, "NLBM1306E", new String[]{"Carrier", "Ship Cost", "Tracking#"});
            scrnMsg.totFrtAmt_D1.setErrorInfo(1, "NLBM1306E", new String[]{"Carrier", "Ship Cost", "Tracking#"});
            scrnMsg.proNum_D1.setErrorInfo(1, "NLBM1306E", new String[]{"Carrier", "Ship Cost", "Tracking#"});
        }

        NLBL3200CommonLogic.addCheckItemApply(scrnMsg);
        scrnMsg.putErrorScreen();
    }


    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;
        NLBL3200CMsg bizMsg = new NLBL3200CMsg();
        bizMsg.setBusinessID(NLBL3200Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;
        NLBL3200CMsg bizMsg = (NLBL3200CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL3200CommonLogic.addCheckItemApply(scrnMsg);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {

            throw new EZDFieldErrorException();
        }

        // clear image file of sort columns (Gif file. ASC or DESC.)
        S21SortColumnIMGController.clearIMG(SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        // screen ctrl
        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        NLBL3200CommonLogic.ctrlScrnItemProtection(scrnMsg, this, funcList);

        scrnMsg.setFocusItem(scrnMsg.carrCd_D1);
    }
}
