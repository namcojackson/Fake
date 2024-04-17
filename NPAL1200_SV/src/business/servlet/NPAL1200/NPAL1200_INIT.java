/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1200;

import static business.servlet.NPAL1200.constant.NPAL1200Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1200.NPAL1200CMsg;
import business.servlet.NPAL1200.common.NPAL1200CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Business ID : NPAL1200 Insourcing Planning Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/25/2016   CITS       Yasushi Nomura        Create          N/A
 * 01/24/2017   CITS            T.Kikuhara      Update          QC#10621
 *</pre>
 */
public class NPAL1200_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_APPLICATION_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1200BMsg scrnMsg = (NPAL1200BMsg) bMsg;
        NPAL1200CMsg bizMsg = new NPAL1200CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1200BMsg scrnMsg = (NPAL1200BMsg) bMsg;
        NPAL1200CMsg bizMsg = (NPAL1200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.xxNum_MD.setValue(MODE_INIT);
        // init
        NPAL1200CommonLogic.initCommonButton(this);
        NPAL1200CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.A.no(0).getBaseContents());

        NPAL1200CommonLogic.control(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.srcZnCd_SF);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NPAL1200BMsg scrnMsg = (NPAL1200BMsg) bMsg;
        //Header
        // QC#10621 add start
        scrnMsg.srchOptNm_TX.setNameForMessage("Search Option Name");
        scrnMsg.rtlWhCd_HF.setNameForMessage("Tech Zone Warehouse");
        scrnMsg.rtlWhCd_HT.setNameForMessage("Insourcing Zone Warehouse");
        scrnMsg.rtlWhCd_DF.setNameForMessage("Tech Zone Warehouse");
        scrnMsg.rtlWhCd_DT.setNameForMessage("Insourcing Zone Warehouse");
        // QC#10621 add end

        //Detail
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).insrcItemPrcThrhdAmt_D1.setNameForMessage("$ Threshold");
            scrnMsg.A.no(i).insrcRnkSortNum_D1.setNameForMessage("Rank");
        }
    }
}
