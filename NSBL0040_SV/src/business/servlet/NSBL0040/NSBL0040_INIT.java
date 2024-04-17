/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0040;

import java.io.Serializable;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0040.NSBL0040CMsg;
import business.servlet.NSBL0040.common.NSBL0040CommonLogic;
import business.servlet.NSBL0040.constant.NSBL0040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * Credit Approval
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/01   SRA             Otsuji          Create          N/A
 * 2013/09/10   SRA             Yanada          Update          QC2131
 *</pre>
 */
public class NSBL0040_INIT extends S21INITCommonHandler implements NSBL0040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0040BMsg scrnMsg = (NSBL0040BMsg) bMsg;
        NSBL0040CMsg bizMsg = new NSBL0040CMsg();

        scrnMsg.svcTaskNum.clear();

        Serializable arg = getArgForSubScreen();

        Object[] params = (Object[]) arg;
        if (params != null && params.length > 0) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.svcTaskNum, (EZDBStringItem) params[0]);
        }

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(SEARCH_FUNCTION);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0040BMsg scrnMsg = (NSBL0040BMsg) bMsg;
        NSBL0040CMsg bizMsg = (NSBL0040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);

        NSBL0040CommonLogic.activateButtons(this, functionList);
        NSBL0040CommonLogic.activateScreenItems(scrnMsg, functionList);

        if (ZYPCommonFunc.hasValue(scrnMsg.svcTaskNum)) {
            scrnMsg.addCheckItem(scrnMsg.svcTaskNum);
            scrnMsg.putErrorScreen();

            S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
            NSBL0040CommonLogic.alternateTableRowColors(scrnMsg);
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSBL0040BMsg scrnMsg = (NSBL0040BMsg) bMsg;

        scrnMsg.billToCustCd.setNameForMessage("Bill to");
        scrnMsg.sellToCustCd.setNameForMessage("Sell to");
        scrnMsg.shipToCustCd.setNameForMessage("Ship to");
        //MOD START 11/19/2015 for CSA
        //scrnMsg.svcTaskNum.setNameForMessage("Task");
        scrnMsg.fsrNum.setNameForMessage("FSR");
        scrnMsg.techCd.setNameForMessage("Tech");
        scrnMsg.ezInUserID.setNameForMessage("Call Created By");
        //scrnMsg.svcBillTpCd_01.setNameForMessage("Bill Type");
        //MOD END 11/19/2015 for CSA
    }

}
