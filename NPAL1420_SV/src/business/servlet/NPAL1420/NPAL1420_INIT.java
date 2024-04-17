/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1420;

import static business.servlet.NPAL1420.constant.NPAL1420Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1420.constant.NPAL1420Constant.IDX_3;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1420.NPAL1420CMsg;
import business.servlet.NPAL1420.common.NPAL1420CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Business ID : NPAL1420 Reman Task Entry
 * Function Name : 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 5/17/2016   CITS       Yasushi Nomura       Create          N/A
 *</pre>
 */
public class NPAL1420_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_APPLICATION_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1420BMsg scrnMsg = (NPAL1420BMsg) bMsg;
        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length == IDX_3) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, (EZDBStringItem) params[0]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rmnfOrdNum, (EZDBStringItem) params[1]);
            if (params[2] != null) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rmnfTaskNum, (EZDBStringItem) params[2]);
            }
        } else {
            scrnMsg.xxModeCd.clear();
        }

        NPAL1420CMsg bizMsg = new NPAL1420CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1420BMsg scrnMsg = (NPAL1420BMsg) bMsg;
        NPAL1420CMsg bizMsg = (NPAL1420CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // init
        NPAL1420CommonLogic.setAppFracDigit(scrnMsg);
        NPAL1420CommonLogic.initCommonButton(this);
        NPAL1420CommonLogic.commonInit(scrnMsg);
        NPAL1420CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.A.no(0).getBaseContents());

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_APPLICATION_ID);
        NPAL1420CommonLogic.control(this, scrnMsg, funcList);
        NPAL1420CommonLogic.setTableColor(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.rmnfTaskDescTxt);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NPAL1420BMsg scrnMsg = (NPAL1420BMsg) bMsg;

        scrnMsg.rmnfTaskDescTxt.setNameForMessage("Description");
        scrnMsg.rmnfTaskStartDt.setNameForMessage("Start Date");
        scrnMsg.rmnfTaskEndDt.setNameForMessage("End Date");
        scrnMsg.spclInstnCmntTxt.setNameForMessage("Notes");
        scrnMsg.techTocCd.setNameForMessage("Technician#");
        scrnMsg.rmnfLborAot.setNameForMessage("Hours");
        scrnMsg.rmnfLborCmntTxt.setNameForMessage("Notes");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setNameForMessage("Check Box");
            scrnMsg.A.no(i).mdseCd_A1.setNameForMessage("Item Number");
            scrnMsg.A.no(i).rmnfPrtQty_A1.setNameForMessage("Used Qty");
        }
    }
}
