package business.servlet.NFCL3010.common;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBItem;
import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFCL3010.NFCL3010BMsg;
import business.servlet.NFCL3010.NFCL3010_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

/**
 *<pre>
 * Batch Receipt Inquiry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2018/03/20   Fujitsu         H.Ikeda         Update          QC#21737
 * 2018/10/17   Fujitsu         T.Noguchi       Update          QC#28428
 *</pre>
 */
public class NFCL3010CommonLogic {

    /**
     * Initialize
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL3010BMsg
     */
    public static void initialize(EZDCommonHandler scrnAppli, NFCL3010BMsg scrnMsg) {

        NFCL3010_ABMsgArray lineMsgArray = scrnMsg.A;
        
        scrnMsg.clearAllGUIAttribute("NFCL3010Scrn00");
        scrnMsg.setInputProtected(false);

        scrnAppli.setButtonProperties("OnClick_Select", "OnClick_Select", "Select", 1, null);
        scrnAppli.setButtonProperties("OnClick_Prev", "OnClick_Prev", "Prev", 1, null);
        scrnAppli.setButtonProperties("OnClick_Next", "OnClick_Next", "Next", 1, null);
        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn2", "", "Submit", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        if (lineMsgArray.getValidCount() > 0) {
            scrnAppli.setButtonProperties("btn6", "CMN_Download", "Download", 1, null);
        } else {
            scrnAppli.setButtonProperties("btn6", "CMN_Download", "Download", 0, null);
        }
        //scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "CMN_Reset", "Clear", 1, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return", "Return", 1, null);

        scrnMsg.fullPsnNm_H.setInputProtected(true);
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).arBatRcptNm_A.setInputProtected(true);
            scrnMsg.A.no(i).arRcptSrcNm_A.setInputProtected(true);
            scrnMsg.A.no(i).arBatRcptDt_A.setInputProtected(true);
            scrnMsg.A.no(i).arBatRcptStsNm_A.setInputProtected(true);
            scrnMsg.A.no(i).arBatRcptCnt_A.setInputProtected(true);
            scrnMsg.A.no(i).arBatRcptAmt_A.setInputProtected(true);
            scrnMsg.A.no(i).arLockBoxFileNm_A.setInputProtected(true);
            scrnMsg.A.no(i).arLockBoxNm_A.setInputProtected(true);
            scrnMsg.A.no(i).arLockBoxBatNum_A.setInputProtected(true);
            // Mod Start 2018/03/20 S21_NA#21737
            scrnMsg.A.no(i).rcptChkNum_A.setInputProtected(true);
            scrnMsg.A.no(i).rcptNum_A.setInputProtected(true);
            scrnMsg.A.no(i).arRcptTrxTpNm_A.setInputProtected(true);
            scrnMsg.A.no(i).rcptDt_A.setInputProtected(true);
            scrnMsg.A.no(i).funcRcptAmt_A.setInputProtected(true);
            scrnMsg.A.no(i).funcApplyAmt_A.setInputProtected(true);
            scrnMsg.A.no(i).payerCustCd_A.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A.setInputProtected(true);
            scrnMsg.A.no(i).xxTotAmt_A.setInputProtected(true);
            scrnMsg.A.no(i).funcRcptRmngBalAmt_A.setInputProtected(true);
        }
        //for (int i = 0; i < scrnMsg.B.length(); i++) {
        //    scrnMsg.B.no(i).arBatRcptNm_B.setInputProtected(true);
        //    scrnMsg.B.no(i).rcptChkNum_B.setInputProtected(true);
        //   scrnMsg.B.no(i).rcptNum_B.setInputProtected(true);
        //    scrnMsg.B.no(i).arRcptTrxTpNm_B.setInputProtected(true);
        //    scrnMsg.B.no(i).rcptDt_B.setInputProtected(true);
        //    scrnMsg.B.no(i).funcRcptAmt_B.setInputProtected(true);
        //    scrnMsg.B.no(i).arRcptStsNm_B.setInputProtected(true);
        //    scrnMsg.B.no(i).arTrxNum_B.setInputProtected(true);
        //    scrnMsg.B.no(i).arTrxTpNm_B.setInputProtected(true);
        //    scrnMsg.B.no(i).funcApplyAmt_B.setInputProtected(true);
        //    scrnMsg.B.no(i).payerCustCd_B.setInputProtected(true);
        //    scrnMsg.B.no(i).dsAcctNm_B.setInputProtected(true);
        //}
        // Mod End   2018/03/20 S21_NA#21737

        // Mod Start 2018/03/20 S21_NA#21737
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).arBatRcptNm_A)) {
                setItemVisibility(scrnMsg, "arBatRcptNm_A#" + i, true);
            } else {
                setItemVisibility(scrnMsg, "arBatRcptNm_A#" + i, false);
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).rcptNum_A)) {
                setItemVisibility(scrnMsg, "rcptNum_A#" + i, true);
            } else {
                setItemVisibility(scrnMsg, "rcptNum_A#" + i, false);
            }
        }
        //for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
        //    if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).arBatRcptNm_B)) {
        //        setItemVisibility(scrnMsg, "arBatRcptNm_B#" + i, true);
        //    } else {
        //        setItemVisibility(scrnMsg, "arBatRcptNm_B#" + i, false);
        //    }
        //    if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).rcptNum_B)) {
//      //          setItemVisibility(scrnMsg, "rcptNum_B#" + i, true);
        //        setItemVisibility(scrnMsg, "rcptNum_B#" + i, true);
        //    } else {
//      //          setItemVisibility(scrnMsg, "rcptNum_B#" + i, false);
        //        setItemVisibility(scrnMsg, "rcptNum_B#" + i, false);
        //    }
        //}
        // Mod End   2018/03/20 S21_NA#21737
    }

    /**
     * Clear Screen background color
     * @param scrnMsg NFCL3010BMsg
     */
    public static void clearScrnBackgroundColor(NFCL3010BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute("NFCL3010Scrn00");
    }

    /**
     * Set Item Visibility
     * @param scrnMsg NFCL3010BMsg
     * @param itemNm String
     * @param visibility boolean
     */
    public static void setItemVisibility(NFCL3010BMsg scrnMsg, String itemNm, boolean visibility) {
        EZDGUIAttribute itemVisibility = new EZDGUIAttribute("NFCL3010Scrn00", itemNm);
        itemVisibility.setVisibility(visibility);
        scrnMsg.addGUIAttribute(itemVisibility);
    }

    // 2018/10/17 QC#28428 Add Start
    /**
     * Check Mandatory Search Condition
     * @param scrnMsg NFCL3010BMsg
     */
    public static void checkMandatorySearchCondition(NFCL3010BMsg scrnMsg) {
        List<EZDBItem> list = new ArrayList<EZDBItem>();

        list.add(scrnMsg.arRcptSrcCd_H);       // Receipt Source
        list.add(scrnMsg.arBatRcptNm_H);       // Batch Name
        list.add(scrnMsg.arBatRcptStsCd_H);    // Batch Status
        list.add(scrnMsg.arLockBoxFileNm_H);   // Lockbox File Name
        list.add(scrnMsg.arLockBoxCd_H);       // Lockbox
        list.add(scrnMsg.arLockBoxBatNum_H);   // Lockbox Batch
        list.add(scrnMsg.dsAcctNum_H);         // Customer Number
        list.add(scrnMsg.dsAcctNm_H);          // Customer Name
        list.add(scrnMsg.rcptDt_H1);           // Receipt Dates (FROM)
        list.add(scrnMsg.rcptDt_H2);           // Receipt Dates (TO)
        list.add(scrnMsg.rcptChkNum_H1);       // Receipt Numbers (FROM)
        list.add(scrnMsg.rcptChkNum_H2);       // Receipt Numbers (TO)
        list.add(scrnMsg.rcptNum_H1);          // Receipt Doc Numbers (FROM)
        list.add(scrnMsg.rcptNum_H2);          // Receipt Doc Numbers (TO)
        list.add(scrnMsg.funcRcptAmt_H1);      // Receipt Amount (FROM)
        list.add(scrnMsg.funcRcptAmt_H2);      // Receipt Amount (TO)
        list.add(scrnMsg.arRcptStsCd_H);       // Receipt Status
        list.add(scrnMsg.arCashApplyStsCd_H);  // Cash App Status
        list.add(scrnMsg.psnCd_H);             // Collector
        list.add(scrnMsg.cratDt_H1);           // Creation Date (FROM)
        list.add(scrnMsg.cratDt_H2);           // Creation Date (TO)
        list.add(scrnMsg.dsBankAcctNm_H);      // Remittance Bank Name
        list.add(scrnMsg.dsBankBrNm_H);        // Remittance Branch Name
        list.add(scrnMsg.dsBankAcctNum_H);     // Remittance Account Name

        boolean hasSearchCondition = false;
        for (EZDBItem item : list) {
            if (ZYPCommonFunc.hasValue(item)) {
                hasSearchCondition = true;
                break;
            }
        }
        if (!hasSearchCondition) {
            for (EZDBItem item : list) {
                item.setErrorInfo(1, "NFCM0050E");
                scrnMsg.addCheckItem(item);
            }
        }
    }

    /**
     * Set To Search Condition
     * @param scrnMsg NFCL3010BMsg
     */
    public static void setToSearchCondition(NFCL3010BMsg scrnMsg) {

        // Receipt Dates
        if (ZYPCommonFunc.hasValue(scrnMsg.rcptDt_H1) && !ZYPCommonFunc.hasValue(scrnMsg.rcptDt_H2)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.rcptDt_H2, scrnMsg.rcptDt_H1);
        }

        // Receipt Numbers
        if (ZYPCommonFunc.hasValue(scrnMsg.rcptChkNum_H1) && !ZYPCommonFunc.hasValue(scrnMsg.rcptChkNum_H2)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.rcptChkNum_H2, scrnMsg.rcptChkNum_H1);
        }

        // Receipt Doc Numbers
        if (ZYPCommonFunc.hasValue(scrnMsg.rcptNum_H1) && !ZYPCommonFunc.hasValue(scrnMsg.rcptNum_H2)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.rcptNum_H2, scrnMsg.rcptNum_H1);
        }

        // Receipt Amount
        if (ZYPCommonFunc.hasValue(scrnMsg.funcRcptAmt_H1) && !ZYPCommonFunc.hasValue(scrnMsg.funcRcptAmt_H2)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.funcRcptAmt_H2, scrnMsg.funcRcptAmt_H1);
        }

        // Creation Date
        if (ZYPCommonFunc.hasValue(scrnMsg.cratDt_H1) && !ZYPCommonFunc.hasValue(scrnMsg.cratDt_H2)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.cratDt_H2, scrnMsg.cratDt_H1);
        }
    }
    // 2018/10/17 QC#28428 Add End
}
