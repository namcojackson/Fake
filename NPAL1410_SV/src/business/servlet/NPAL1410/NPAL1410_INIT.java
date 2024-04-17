/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1410;

import static business.servlet.NPAL1410.constant.NPAL1410Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.TAB_CONF;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1410.NPAL1410CMsg;
import business.servlet.NPAL1410.common.NPAL1410CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Business ID : NPAL1410 Reman Workbench
 * Function Name : 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 4/05/2016   CITS       Yasushi Nomura       Create          N/A
 * 12/15/2017  CITS            K.Ogino         Update          QC#22836
 *</pre>
 */
public class NPAL1410_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_APPLICATION_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1410BMsg scrnMsg = (NPAL1410BMsg) bMsg;
        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length == 1) {
            EZDBStringItem param01 = (EZDBStringItem) params[0];
            ZYPEZDItemValueSetter.setValue(scrnMsg.rmnfOrdNum_TR, param01);
        }

        NPAL1410CMsg bizMsg = new NPAL1410CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1410BMsg scrnMsg = (NPAL1410BMsg) bMsg;
        NPAL1410CMsg bizMsg = (NPAL1410CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // init
        NPAL1410CommonLogic.setAppFracDigit(scrnMsg);
        NPAL1410CommonLogic.initCommonButton(this);
        scrnMsg.xxDplyTab.setValue(TAB_CONF);
        NPAL1410CommonLogic.commonInit(scrnMsg);
        NPAL1410CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.A.no(0).getBaseContents());

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_APPLICATION_ID);
        NPAL1410CommonLogic.control(this, scrnMsg, funcList);

//        scrnMsg.xxDplyTab.setValue(TAB_CONF);
        scrnMsg.setFocusItem(scrnMsg.rmnfOrdNum_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NPAL1410BMsg scrnMsg = (NPAL1410BMsg) bMsg;

        scrnMsg.rmnfRtlWhCd_H1.setNameForMessage("Reman Warehouse");
        scrnMsg.rmnfOrdNum_H1.setNameForMessage("Reman Order#");
        scrnMsg.ownrTechTocCd_H1.setNameForMessage("Reman Tech Owner");
        scrnMsg.rmnfMainUnitSerNum_H1.setNameForMessage("Main Unit Serial");
        scrnMsg.rmnfOrdTpCd_SE.setNameForMessage("Order Type");
        scrnMsg.whLoctrCd_H1.setNameForMessage("Locator");
        scrnMsg.rmnfRtlSwhCd_H1.setNameForMessage("Sub WH");
        scrnMsg.rmnfStartDt_H1.setNameForMessage("Start Date");
        scrnMsg.rmnfOrdCmntTxt_H1.setNameForMessage("Notes");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).mdseCd_A1.setNameForMessage("Item Number");
            scrnMsg.A.no(i).serNum_A1.setNameForMessage("Serial Number / Licence Key");
            scrnMsg.A.no(i).rmnfToCmptMdseCd_A1.setNameForMessage("Convert to Material");
            scrnMsg.A.no(i).rmnfToCmptSerNum_A1.setNameForMessage("Convert to Serial / Licence Key");
            // QC#22836
            scrnMsg.A.no(i).srcRtlSwhCd_A1.setNameForMessage("Source SWH");
            scrnMsg.A.no(i).stkStsCd_A1.setNameForMessage("Stock Status");
        }
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).prtRqstQty_B1.setNameForMessage("Qty");
        }
        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).xxChkBox_C1.setNameForMessage("Check Box");
            scrnMsg.C.no(i).mdseCd_C1.setNameForMessage("Item Number");
            scrnMsg.C.no(i).prtRqstQty_C1.setNameForMessage("Qty");
        }
        for (int i = 0; i < scrnMsg.D.length(); i++) {
            scrnMsg.D.no(i).xxChkBox_D1.setNameForMessage("Check Box");
        }
    }
}
