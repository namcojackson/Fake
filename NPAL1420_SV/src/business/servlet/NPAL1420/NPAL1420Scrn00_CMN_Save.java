/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1420;

import static business.servlet.NPAL1420.constant.NPAL1420Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1420.constant.NPAL1420Constant.NPAM1173E;
import static business.servlet.NPAL1420.constant.NPAL1420Constant.NPAM1513E;
import static business.servlet.NPAL1420.constant.NPAL1420Constant.NPZM0201E;
import static business.servlet.NPAL1420.constant.NPAL1420Constant.NPAM1234E;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1420.NPAL1420CMsg;
import business.servlet.NPAL1420.common.NPAL1420CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

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
 * 1/29/2018   CITS       T.Wada               Update          QC#23072
 *</pre>
 */
public class NPAL1420Scrn00_CMN_Save extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1420BMsg scrnMsg = (NPAL1420BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.rmnfTaskDescTxt);
        scrnMsg.addCheckItem(scrnMsg.rmnfTaskStartDt);
        scrnMsg.addCheckItem(scrnMsg.rmnfTaskEndDt);
        scrnMsg.addCheckItem(scrnMsg.spclInstnCmntTxt);
        scrnMsg.addCheckItem(scrnMsg.techTocCd);
        scrnMsg.addCheckItem(scrnMsg.rmnfLborAot);
        scrnMsg.addCheckItem(scrnMsg.rmnfLborCmntTxt);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_A1);
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).rmnfPrtQty_A1) && !ZYPCommonFunc.hasValue(scrnMsg.A.no(i).rmnfPrtRelQty_A1)) {
            	scrnMsg.addCheckItem(scrnMsg.A.no(i).rmnfPrtQty_A1);
            }

        }
        
        if (ZYPCommonFunc.hasValue(scrnMsg.rmnfTaskStartDt)) {
            if (ZYPDateUtil.getSalesDate().compareTo(scrnMsg.rmnfTaskStartDt.getValue()) < 0) {
                scrnMsg.rmnfTaskStartDt.setErrorInfo(1, NPZM0201E, new String[] {"Start Date" });
            }
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.rmnfTaskEndDt)) {
            if (ZYPDateUtil.getSalesDate().compareTo(scrnMsg.rmnfTaskEndDt.getValue()) < 0) {
                scrnMsg.rmnfTaskEndDt.setErrorInfo(1, NPZM0201E, new String[] {"End Date" });
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.rmnfLborAot)) {
                scrnMsg.rmnfLborAot.setErrorInfo(1, NPAM1173E, new String[] {"Hours" });
            }
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.rmnfTaskStartDt) && ZYPCommonFunc.hasValue(scrnMsg.rmnfTaskEndDt)) {
            if (scrnMsg.rmnfTaskEndDt.getValue().compareTo(scrnMsg.rmnfTaskStartDt.getValue()) < 0) {
                scrnMsg.rmnfTaskStartDt.setErrorInfo(1, NPAM1513E, new String[] {"End Date", "Start Date" });
                scrnMsg.rmnfTaskEndDt.setErrorInfo(1, NPAM1513E, new String[] {"End Date", "Start Date" });
            }
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.rmnfLborAot)) {
            if (scrnMsg.rmnfLborAot.getValue().compareTo(BigDecimal.ZERO) <= 0) {
                scrnMsg.rmnfLborAot.setErrorInfo(1, NPAM1234E, new String[] {"Hours" });
            }
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1420BMsg scrnMsg = (NPAL1420BMsg) bMsg;

        NPAL1420CMsg bizMsg = new NPAL1420CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1420BMsg scrnMsg = (NPAL1420BMsg) bMsg;
        NPAL1420CMsg bizMsg = (NPAL1420CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.rmnfTaskDescTxt);
        scrnMsg.addCheckItem(scrnMsg.rmnfTaskStartDt);
        scrnMsg.addCheckItem(scrnMsg.rmnfTaskEndDt);
        scrnMsg.addCheckItem(scrnMsg.spclInstnCmntTxt);
        scrnMsg.addCheckItem(scrnMsg.techTocCd);
        scrnMsg.addCheckItem(scrnMsg.rmnfLborAot);
        scrnMsg.addCheckItem(scrnMsg.rmnfLborCmntTxt);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rmnfPrtQty_A1);
        }
        scrnMsg.putErrorScreen();

        NPAL1420CommonLogic.setAppFracDigit(scrnMsg);
        NPAL1420CommonLogic.initCommonButton(this);
        NPAL1420CommonLogic.commonInit(scrnMsg);
        NPAL1420CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.A.no(0).getBaseContents());

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_APPLICATION_ID);
        NPAL1420CommonLogic.control(this, scrnMsg, funcList);
        NPAL1420CommonLogic.setTableColor(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.rmnfTaskDescTxt);
    }
}
