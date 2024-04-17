/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7180;

import static business.servlet.NMAL7180.constant.NMAL7180Constant.BIZ_ID;
import static business.servlet.NMAL7180.constant.NMAL7180Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7180.NMAL7180CMsg;
import business.servlet.NMAL7180.common.NMAL7180CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NMAL7180_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   Fujitsu         W.Honda         Create          N/A
 * 2016/09/02   Fujitsu         R.Nakamura      Update          QC#8282
 * 2020/02/19   Fujitsu         C.Hara          Update          QC#55203
 *</pre>
 */
public class NMAL7180_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //security violation check. 
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7180BMsg scrnMsg = (NMAL7180BMsg) bMsg;
        NMAL7180CMsg bizMsg = new NMAL7180CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7180BMsg scrnMsg = (NMAL7180BMsg) bMsg;
        NMAL7180CMsg bizMsg = (NMAL7180CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Clear Attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);
        NMAL7180CommonLogic.controlScreen(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.prcGrpNm);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL7180BMsg scrnMsg = (NMAL7180BMsg) bMsg;

        //Header
        scrnMsg.prcGrpNm.setNameForMessage("Price Group Name(*)");
        scrnMsg.prcGrpDescTxt.setNameForMessage("Group Description(*)");
        scrnMsg.prcGrpTpCd.setNameForMessage("Pricing Group Type");
        scrnMsg.prcGrpTrgtTpCd.setNameForMessage("Target Context");
        scrnMsg.prcGrpTrgtAttrbCd.setNameForMessage("Attribute Name");
        scrnMsg.prcGrpFromTxt.setNameForMessage("Attribute Target From(*)");
        scrnMsg.prcGrpThruTxt.setNameForMessage("Attribute Target To(*)");
        scrnMsg.actvFlg.setNameForMessage("Active");
        scrnMsg.effFromDt.setNameForMessage("Effective Date From");
        scrnMsg.effThruDt.setNameForMessage("Effective Date To");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).prcGrpNm_A1.setNameForMessage("Pricing Group Name");
            // Add Start 2016/09/02 QC#8282
            scrnMsg.A.no(i).prcGrpDescTxt_A1.setNameForMessage("Group Description");
            // Add End 2016/09/02 QC#8282
            scrnMsg.A.no(i).prcGrpTpDescTxt_A1.setNameForMessage("Pricing Group Type");
            scrnMsg.A.no(i).actvFlg_A1.setNameForMessage("Active");
            // 2020/02/19 QC#55203 Del Start
            //scrnMsg.A.no(i).prcGrpTrgtTpDescTxt_A1.setNameForMessage("Target Context");
            //scrnMsg.A.no(i).prcGrpTrgtAttrbDescTxt_A1.setNameForMessage("Attribute Name");
            //scrnMsg.A.no(i).prcGrpOpDescTxt_A1.setNameForMessage("Operator");
            //scrnMsg.A.no(i).prcGrpFromTxt_A1.setNameForMessage("Target From");
            //scrnMsg.A.no(i).prcGrpThruTxt_A1.setNameForMessage("Target To");
            // 2020/02/19 QC#55203 Del End
            scrnMsg.A.no(i).effFromDt_A1.setNameForMessage("Date From");
            scrnMsg.A.no(i).effThruDt_A1.setNameForMessage("Date To");
        }
    }
}
