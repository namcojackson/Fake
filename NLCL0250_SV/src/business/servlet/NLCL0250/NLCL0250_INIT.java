/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0250;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0250.NLCL0250CMsg;
import business.servlet.NLCL0250.common.NLCL0250CommonLogic;
import business.servlet.NLCL0250.constant.NLCL0250Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Onhand Inventory Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/14/2015   CSAI            Y.Imazu         Create          N/A
 * 01/20/2016   CSAI            Y.Imazu         Update          QC#3134
 * 01/20/2016   CSAI            Y.Imazu         Update          QC#3137
 * 02/18/2016   CSAI            Y.Imazu         Update          QC#3141
 * 12/18/2017   CITS            S.Katsuma       Update          QC#22469
 * 03/07/2023   Hitachi         S.Dong          Update          QC#61205
 *</pre>
 */
public class NLCL0250_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), NLCL0250Constant.BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0250BMsg scrnMsg = (NLCL0250BMsg) bMsg;

        // Set global parameter
        ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(scrnMsg.slsDt, ZYPDateUtil.getSalesDate());

        NLCL0250CMsg bizMsg = new NLCL0250CMsg();

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {

            Object[] params = (Object[]) arg;
            int paramCnt = params.length;

            // Merchandise Code
            if (paramCnt > 0) {

                EZDBStringItem param00 = (EZDBStringItem) params[0];

                if (ZYPCommonFunc.hasValue(param00)) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxMdseSrchTxt_BK, param00);
                }
            }

            // Retail Warehouse Code
            if (paramCnt > 1) {

                EZDBStringItem param01 = (EZDBStringItem) params[1];

                if (ZYPCommonFunc.hasValue(param01)) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCdSrchTxt_BR, param01);
                }
            }

            // Retail Sub Warehouse Code
            if (paramCnt > 2) {

                EZDBStringItem param02 = (EZDBStringItem) params[2];

                if (ZYPCommonFunc.hasValue(param02)) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNmSrchTxt_BS, param02);
                }
            }

            // Serial Number
            if (paramCnt > 3) {

                    EZDBStringItem param03 = (EZDBStringItem) params[3];

                if (ZYPCommonFunc.hasValue(param03)) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxSerNumSrchTxt_BK, param03);
                }
            }

            // Configuration PK
            if (paramCnt > 4) {

                EZDBBigDecimalItem param04 = (EZDBBigDecimalItem) params[4];

                if (ZYPCommonFunc.hasValue(param04)) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.srchOptTxt_BK, param04.toString());
                }
            }
        }

        bizMsg.setBusinessID(NLCL0250Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0250BMsg scrnMsg = (NLCL0250BMsg) bMsg;
        NLCL0250CMsg bizMsg  = (NLCL0250CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLCL0250CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);

        // Set Focus
        scrnMsg.setFocusItem(scrnMsg.zerothProdCtrlNm_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NLCL0250BMsg scrnMsg = (NLCL0250BMsg) bMsg;

        // Search Option
        scrnMsg.srchOptPk_S.setNameForMessage("Search Option");
        scrnMsg.srchOptNm_S.setNameForMessage("Search Option Name");

        // Search Condition
        scrnMsg.zerothProdCtrlNm_H1.setNameForMessage("Business Unit");
        scrnMsg.frthProdCtrlNm_H1.setNameForMessage("Product Group");
        scrnMsg.scdProdCtrlNm_H1.setNameForMessage("Product Family");
        scrnMsg.thirdProdCtrlNm_H1.setNameForMessage("Product Line");
        scrnMsg.frthProdCtrlNm_H1.setNameForMessage("Product Series");
        scrnMsg.coaProjCd_H1.setNameForMessage("Merchandise Type");
        scrnMsg.mdseItemTpCd_H1.setNameForMessage("Item Type");
        scrnMsg.xxMdseSrchTxt_H1.setNameForMessage("Item Number");
        scrnMsg.xxSerNumSrchTxt_H1.setNameForMessage("Serial Number");
        scrnMsg.srchOptTxt_CF.setNameForMessage("Config ID");
        scrnMsg.rtlWhCatgCd_H1.setNameForMessage("Warehouse Type");
        scrnMsg.rtlWhCdSrchTxt_RW.setNameForMessage("Warehouse Code");
        scrnMsg.rtlWhNmSrchTxt_RW.setNameForMessage("Warehouse Name");
        scrnMsg.rtlWhCdSrchTxt_SW.setNameForMessage("Sub Warehouse Code");
        scrnMsg.rtlWhNmSrchTxt_SW.setNameForMessage("Sub Warehouse Name");
        scrnMsg.xxFldValTxt_HC.setNameForMessage("To Location Code");
        scrnMsg.xxFldValTxt_HN.setNameForMessage("To Location Name");
        scrnMsg.xxFromDt_H1.setNameForMessage("Age From Date");
        scrnMsg.xxThruDt_H1.setNameForMessage("Age Through Date");
        scrnMsg.xxPageShowCurNum_A.setNameForMessage("Current Page Number");
        // START 2017/12/18 S.Katsuma [QC#22469,ADD]
        scrnMsg.xxChkBox_PR.setNameForMessage("Parts Return Controlled");
        scrnMsg.rtrnCtrlTpVndRelnPk_H1.setNameForMessage("Return Control Type");
        // END 2017/12/18 S.Katsuma [QC#22469,ADD]
        // START 2023/03/07 S.Dong [QC#61205, ADD]
        scrnMsg.whOwnrCd_H1.setNameForMessage("WH Operation");
        // END 2023/03/07 S.Dong [QC#61205, ADD]

        for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {

            scrnMsg.L.no(i).xxChkBox_LS.setNameForMessage("Location Status");
        }
    }
}
