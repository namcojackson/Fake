/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0360;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0360.NSAL0360CMsg;
import business.servlet.NSAL0360.common.NSAL0360CommonLogic;
import business.servlet.NSAL0360.constant.NSAL0360Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/03   CUSA            Fujitsu         Create          N/A
 * 2015/10/23   Hitach          T.Tomita        Update          N/A
 * 2016/05/18   Hitachi         T.Kanasaka      Update          QC#2184
 * 2016/10/25   Hitachi         Y.Takeno        Update          QC#15518
 *</pre>
 */
public class NSAL0360_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), NSAL0360Constant.BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0360BMsg scrnMsg = (NSAL0360BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length > 0) {
            // START 2015/10/23 T.Tomita [N/A, MOD]
            if (params.length > 0 && params[0] != null && params[0] instanceof EZDBBigDecimalItem) {
                EZDBBigDecimalItem param00 = (EZDBBigDecimalItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrDtlPk_H1, param00);
            }

            if (params.length > 1 && params[1] != null && params[1] instanceof EZDBBigDecimalItem) {
                EZDBBigDecimalItem param01 = (EZDBBigDecimalItem) params[1];
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrPrcEffPk_HD, param01);
            }

            if (params.length > 2 && params[2] != null && params[2] instanceof EZDBStringItem) {
                EZDBStringItem param02 = (EZDBStringItem) params[2];
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_HD, param02);
            }
            // END 2015/10/23 T.Tomita [N/A, MOD]
        }

        NSAL0360CMsg bizMsg = new NSAL0360CMsg();
        bizMsg.setBusinessID(NSAL0360Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0360BMsg scrnMsg = (NSAL0360BMsg) bMsg;
        NSAL0360CMsg bizMsg = (NSAL0360CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0360CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg, bizMsg.getUserID());
        scrnMsg.setFocusItem(scrnMsg.mtrBllgTmgCd_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0360BMsg scrnMsg = (NSAL0360BMsg) bMsg;

        // Header
        scrnMsg.dsContrNum_H1.setNameForMessage("Contract#");
        scrnMsg.serNum_H1.setNameForMessage("Serial#");
        // START 2015/10/23 T.Tomita [N/A, DEL]
//        scrnMsg.t_MdlNm_H1.setNameForMessage("Serial#");
        // END 2015/10/23 T.Tomita [N/A, DEL]

        scrnMsg.contrEffFromDt_H1.setNameForMessage("Effective Start Date");
        scrnMsg.contrEffThruDt_H1.setNameForMessage("Effective Thru Date");
        scrnMsg.mtrBllgTmgCd_H1.setNameForMessage("Billing Timing");
        // START 2016/05/18 T.Kanasaka [QC#2184, MOD]
        // START 2016/10/25 [QC#15518, MOD]
//        scrnMsg.mtrDplyPerEndDay_H1.setNameForMessage("Closing Day");
        scrnMsg.mtrDplyPerEndDay_H1.setNameForMessage("Period End Date");
        // END 2016/10/25 [QC#15518, MOD]
        // END 2016/05/18 T.Kanasaka [QC#2184, MOD]

        scrnMsg.mtrBllgLastBllgDt_H1.setNameForMessage("Billed Upto");
        // START 2015/10/23 T.Tomita [N/A, DEL]
//        scrnMsg.xsChrgTpCd_H3.setNameForMessage("Pricing Type");
        // END 2015/10/23 T.Tomita [N/A, DEL]
        // START 2016/10/25 [QC#15518, MOD]
//        scrnMsg.mtrBllgDay_H1.setNameForMessage("Billing Day");
        scrnMsg.mtrBllgDay_H1.setNameForMessage("Invoice Date");
        // END 2016/10/25 [QC#15518, MOD]

        scrnMsg.svcMemoRsnCd_F3.setNameForMessage("Reason Code");
        scrnMsg.svcCmntTxt_F1.setNameForMessage("Comment");

        for (String tblNm : NSAL0360Constant.TBL_NM_ARRAY) {
            EZDMsgArray tblArray = NSAL0360CommonLogic.getTableArrayFromEZDMsg(scrnMsg, tblNm);

            for (int i = 0; i < tblArray.length(); i++) {
                EZDMsg ezdMsg = tblArray.get(i);
                // START 2015/10/23 T.Tomita [N/A, MOD]
                NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "dsContrBllgSchdSqNum_A1").setNameForMessage("Seq No");
                NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "dsContrPrcEffSqNum_A1").setNameForMessage("PE Seq#");
                NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "perSchdNum_A1").setNameForMessage("Periods");
                NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "perBllgCycleCd_A1").setNameForMessage("Sched.UOM");
                NSAL0360CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdFromDt_A1").setNameForMessage("Sched.From Date");
                NSAL0360CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdThruDt_A1").setNameForMessage("Sched.Thru Date");
                NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "bllgCycleDescTxt_A1").setNameForMessage("Billing Cycle");
                NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "xsMtrCopyQty_A1").setNameForMessage("Allowance/UOM");
                NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "xsMtrAmtRate_A1").setNameForMessage("Price");
                NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "ccyCd_A1").setNameForMessage("Currency");
                // END 2015/10/23 T.Tomita [N/A, MOD]
            }

        }

    }
}
