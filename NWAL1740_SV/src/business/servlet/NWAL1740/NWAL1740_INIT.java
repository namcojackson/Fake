/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1740;

import static business.servlet.NWAL1740.constant.NWAL1740Constant.BIZ_ID;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.DPLY_TAB_MDSE;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.BTN_ADD_MDSE;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.BTN_ADD_LINE;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.BTN_ADD_WH;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.BTN_REMOVE_MDSE;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.BTN_REMOVE_LINE;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.BTN_REMOVE_WH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1740.NWAL1740CMsg;
import business.servlet.NWAL1740.common.NWAL1740CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWAL1740_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/25   Fujitsu         M.Suzuki        Create          N/A
 * 2016/05/11   Fujitsu         M.Suzuki        Update          S21_NA#7914
 *</pre>
 */
public class NWAL1740_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
         checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1740BMsg scrnMsg = (NWAL1740BMsg) bMsg;
        NWAL1740CMsg bizMsg = new NWAL1740CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1740BMsg scrnMsg = (NWAL1740BMsg) bMsg;
        NWAL1740CMsg bizMsg = (NWAL1740CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.xxDplyTab.setValue(DPLY_TAB_MDSE);
        NWAL1740CommonLogic.initCmnBtnProp(this, getUserProfileService());
        setScreenProtected(scrnMsg);
        if (!NWAL1740CommonLogic.isUpdateUser(getUserProfileService())) {
            initScreenControlRead(scrnMsg);
        }
        scrnMsg.setFocusItem(scrnMsg.ordProcNodePrflCd);
    }

    private void initScreenControlRead(NWAL1740BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
            scrnMsg.A.no(i).flPrcCalcInclFlg_A.setInputProtected(true);
            scrnMsg.A.no(i).repRevCalcInclFlg_AR.setInputProtected(true);
            scrnMsg.A.no(i).repRevCalcInclFlg_AD.setInputProtected(true);
            scrnMsg.A.no(i).repRevCalcInclFlg_AF.setInputProtected(true);
            scrnMsg.A.no(i).redRepRevFlg_A.setInputProtected(true);
            scrnMsg.A.no(i).discAllocMethDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).dlrCrPrftInclFlg_A.setInputProtected(true);
            scrnMsg.A.no(i).redCompAmtFlg_A.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).xxChkBox_B.setInputProtected(true);
            scrnMsg.B.no(i).actvFlg_B.setInputProtected(true);
            scrnMsg.B.no(i).effFromDt_B.setInputProtected(true);
            scrnMsg.B.no(i).effThruDt_B.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).xxChkBox_C.setInputProtected(true);
            scrnMsg.C.no(i).actvFlg_C.setInputProtected(true);
            scrnMsg.C.no(i).effFromDt_C.setInputProtected(true);
            scrnMsg.C.no(i).effThruDt_C.setInputProtected(true);
        }

        this.setButtonEnabled(BTN_ADD_MDSE, false);
        this.setButtonEnabled(BTN_REMOVE_MDSE, false);
        this.setButtonEnabled(BTN_ADD_LINE, false);
        this.setButtonEnabled(BTN_REMOVE_LINE, false);
        this.setButtonEnabled(BTN_ADD_WH, false);
        this.setButtonEnabled(BTN_REMOVE_WH, false);
    }

    private void setScreenProtected(NWAL1740BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).coaMdseTpCd_A.setInputProtected(true);
            scrnMsg.A.no(i).coaProjNm_A.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).dsOrdLineCatgNm_B.setInputProtected(true);
            scrnMsg.B.no(i).dsOrdLineCatgDescTxt_B.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).rtlWhNm_C.setInputProtected(true);
            scrnMsg.C.no(i).rtlSwhNm_C.setInputProtected(true);
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL1740BMsg scrnMsg = (NWAL1740BMsg) bMsg;
        scrnMsg.ordProcNodePrflCd.setNameForMessage("Profitability Process Rule Name");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A.setNameForMessage("Check Box");
            scrnMsg.A.no(i).coaMdseTpCd_A.setNameForMessage("Merchandise Type");
            scrnMsg.A.no(i).coaProjNm_A.setNameForMessage("Merchandise Type Name");
            scrnMsg.A.no(i).flPrcCalcInclFlg_A.setNameForMessage("Floor Price - Include");
            scrnMsg.A.no(i).repRevCalcInclFlg_AR.setNameForMessage("Rep Equip Revenue - Include");
            scrnMsg.A.no(i).repRevCalcInclFlg_AD.setNameForMessage("Discount");
            //2016/05/11 S21_NA#7914 MOD Start --------------
            scrnMsg.A.no(i).repRevCalcInclFlg_AF.setNameForMessage("Floor Adjustment");
            scrnMsg.A.no(i).redRepRevFlg_A.setNameForMessage("Rep Revenue Adjustment");
            //2016/05/11 S21_NA#7914 MOD Start --------------
            scrnMsg.A.no(i).discAllocMethDescTxt_A.setNameForMessage("Adjustment Allocation Type");
            scrnMsg.A.no(i).dlrCrPrftInclFlg_A.setNameForMessage("Include in Dealer Credit Profit SUM");
            scrnMsg.A.no(i).redCompAmtFlg_A.setNameForMessage("Reduction in Compensation Amt");
        }

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).xxChkBox_B.setNameForMessage("Check Box");
            scrnMsg.B.no(i).dsOrdLineCatgNm_B.setNameForMessage("Line Category ");
            scrnMsg.B.no(i).dsOrdLineCatgDescTxt_B.setNameForMessage("Description");
            scrnMsg.B.no(i).actvFlg_B.setNameForMessage("Active");
            scrnMsg.B.no(i).effFromDt_B.setNameForMessage("Effective Date");
            scrnMsg.B.no(i).effThruDt_B.setNameForMessage("End Date");

        }

        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).xxChkBox_C.setNameForMessage("Check Box");
            scrnMsg.C.no(i).rtlWhNm_C.setNameForMessage("Warehouse");
            scrnMsg.C.no(i).rtlSwhNm_C.setNameForMessage("Sub Warehouse");
            scrnMsg.C.no(i).mdseInvtyCostPct_C.setNameForMessage("Valuation%");
            scrnMsg.C.no(i).actvFlg_C.setNameForMessage("Active");
            scrnMsg.C.no(i).effFromDt_C.setNameForMessage("Effective Date");
            scrnMsg.C.no(i).effThruDt_C.setNameForMessage("End Date");
        }
    }
}
