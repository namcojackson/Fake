/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1200;

import static business.servlet.NSAL1200.constant.NSAL1200Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1200.NSAL1200CMsg;
import business.servlet.NSAL1200.common.NSAL1200CommonLogic;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Counter Group Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   Hitachi         Y.Takeno        Create          N/A
 * 2017/08/03   Hitachi         T.Kanasaka      Update          QC#18193
 * 2017/09/04   Hitachi         T.Kanasaka      Update          QC#15134
 * 2018/12/05   Hitachi         K.Morita        Update          QC#28644
 *</pre>
 */
public class NSAL1200_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1200BMsg scrnMsg = (NSAL1200BMsg) bMsg;

        NSAL1200CMsg bizMsg = new NSAL1200CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1200BMsg scrnMsg = (NSAL1200BMsg) bMsg;
        NSAL1200CMsg bizMsg = (NSAL1200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL1200CommonLogic.initialControlScreen(this, scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL1200BMsg scrnMsg = (NSAL1200BMsg) bMsg;
        scrnMsg.xxPageShowCurNum.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName());
        scrnMsg.mtrGrpNm_H.setNameForMessage("Counter Group");
        scrnMsg.mtrGrpTpCd_H.setNameForMessage("Counter Group Type");
        scrnMsg.mtrGrpDescTxt_H.setNameForMessage("Description");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).mdlMtrLbCd_A.setNameForMessage("Counter");
            scrnMsg.A.no(i).mtrLbDescTxt_CN.setNameForMessage("Counter");
            // START 2017/08/03 T.Kanasaka [QC#18193,MOD]
//            scrnMsg.A.no(i).bllgMtrLbCd_A.setNameForMessage("Default Billing");
//            scrnMsg.A.no(i).mtrLbDescTxt_DB.setNameForMessage("Default Billing");
//            scrnMsg.A.no(i).mtrEntryMndFlg_A.setNameForMessage("Mandatory");
            scrnMsg.A.no(i).mtrEntryMndFlg_A.setNameForMessage("Default As Billable");
            scrnMsg.A.no(i).mtrMultRate_A.setNameForMessage("Multiplier");
//            scrnMsg.A.no(i).fleetMtrLbCd_A.setNameForMessage("FLT Counter");
//            scrnMsg.A.no(i).mtrLbDescTxt_FL.setNameForMessage("FLT Counter");
//            scrnMsg.A.no(i).aggrMtrLbCd_A.setNameForMessage("AGG Counter");
//            scrnMsg.A.no(i).mtrLbDescTxt_AG.setNameForMessage("AGG Counter");
            scrnMsg.A.no(i).bllgMtrLbCd_L1.setNameForMessage("Billing Counter LVL1");
            scrnMsg.A.no(i).mtrLbDescTxt_L1.setNameForMessage("Billing Counter LVL1");
            scrnMsg.A.no(i).bllgMtrLbCd_L2.setNameForMessage("Billing Counter LVL2");
            scrnMsg.A.no(i).mtrLbDescTxt_L2.setNameForMessage("Billing Counter LVL2");
            scrnMsg.A.no(i).bllgMtrLbCd_L3.setNameForMessage("Billing Counter LVL3");
            scrnMsg.A.no(i).mtrLbDescTxt_L3.setNameForMessage("Billing Counter LVL3");
            // END 2017/08/03 T.Kanasaka [QC#18193,MOD]
            // START 2017/09/04 T.Kanasaka [QC#15134,ADD]
            scrnMsg.A.no(i).cntrDigitNum_A.setNameForMessage("Counter Digit");
            // END 2017/09/04 T.Kanasaka [QC#15134,ADD]
            
            //START 2018/12/05 [QC#28644,ADD]
            scrnMsg.A.no(i).techReadMndFlg_A.setNameForMessage("Tech Read Mandatory");
            // END 2018/12/05 [QC#28644,ADD]
            
            scrnMsg.A.no(i).actvFlg_A.setNameForMessage("Active");
            scrnMsg.A.no(i).effFromDt_A.setNameForMessage("Start Date");
            scrnMsg.A.no(i).effThruDt_A.setNameForMessage("End Date");
        }
    }
}
