package business.servlet.NFDL0040.common;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFDL0040.NFDL0040BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_PRMS_STS;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Promise/Dispute Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/08/08   Hitachi         K.Kojima        Update          QC#13005
 * 2016/08/25   Hitachi         K.Kojima        Update          QC#11045
 * 2016/08/26   Hitachi         K.Kojima        Update          QC#10786
 * 2016/12/27   Fujitsu         H.Ikeda         Update          QC#12865
 * 2017/01/16   Fujitsu         H.Ikeda         Update          QC#10786
 * 2017/08/07   Hitachi         T.Tsuchida      Update          QC#19576
 * 2023/05/26   Hitachi         S.Nakatani      Update          QC#61271
 *</pre>
 */
public class NFDL0040CommonLogic {

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFDL0040BMsg
     */
    public static void initialize(EZDCommonHandler scrnAppli, NFDL0040BMsg scrnMsg) {
        scrnMsg.setInputProtected(false);

        scrnAppli.setButtonProperties("OnClick_DisputeAdd", "OnClick_DisputeAdd", "Add", 1, null);
        scrnAppli.setButtonProperties("OnClick_PromiseAdd", "OnClick_PromiseAdd", "Add", 1, null);

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        // Start 2016/12/27 H.Ikeda [QC#12865,ADD]
        if (hasUpdateFuncId(scrnMsg)) {
            scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 1, null);
        } else {
            scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 0, null);
        }
        // Start 2016/12/27 H.Ikeda [QC#12865,ADD]
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "CMN_Clear", "Clear", 1, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return", "Return", 1, null);

        scrnMsg.setInputProtected(false);

        scrnMsg.dsAcctNum_H.setInputProtected(true);
        scrnMsg.dsAcctNm_H.setInputProtected(true);
        // START 2023/05/26 S.Nakatani [QC#61271,MOD]
//        scrnMsg.arTrxNum_H.setInputProtected(true);
        scrnMsg.xxTrxNumSrchTxt.setInputProtected(true);
        // END 2023/05/26 S.Nakatani [QC#61271,MOD]
        // START 2016/08/08 K.Kojima [QC#13005,ADD]
        scrnMsg.dealRmngBalGrsAmt_H.setInputProtected(true);
        // END 2016/08/08 K.Kojima [QC#13005,ADD]

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).fullPsnNm_A.setInputProtected(true);
            scrnMsg.A.no(i).dealCltPrmsAmt_A.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).cltPrmsDtlPk_A)) {
                scrnMsg.A.no(i).dealCltPrmsAmt_A.setInputProtected(false);
            } else if (scrnMsg.A.no(i).cltPrmsStsCd_A.getValue().equals(CLT_PRMS_STS.COLLECTIBLE)) {
                scrnMsg.A.no(i).dealCltPrmsAmt_A.setInputProtected(false);
            }
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.B.no(i).dealCltDsptAmt_B.setInputProtected(true);
            scrnMsg.B.no(i).cltDsptRsnCd_B.setInputProtected(true);
            scrnMsg.B.no(i).fullPsnNm_B.setInputProtected(true);
            scrnMsg.B.no(i).dealRmngBalGrsAmt_B.setInputProtected(true);
            // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
            scrnAppli.setButtonEnabled("OnClick_Create", i, true);
            // END 2017/08/07 T.Tsuchida [QC#19576,MOD]
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.B.no(i).dealCltDsptAmt_B.setInputProtected(false);
        }

        // START 2023/05/26 S.Nakatani [QC#61271,ADD]
        boolean isMultiInv = scrnMsg.xxTrxNumSrchTxt.getValue().contains(",");
        if (isMultiInv) {
            scrnMsg.dealCltPrmsAmt_AH.setInputProtected(true);
            scrnMsg.xxChkBox_AH.setInputProtected(true);
            scrnAppli.setButtonProperties("OnClick_PromiseApply", "OnClick_PromiseApply", "Search", 0, null);
            scrnMsg.dealCltDsptAmt_BH.setInputProtected(true);
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).dealCltPrmsAmt_A.setInputProtected(true);
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).cltPrmsDtlPk_A)) {
                    scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 0, null);
                }
            }
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                scrnMsg.B.no(i).dealCltDsptAmt_B.setInputProtected(true);
                if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).cltDsptTrxPk_B)) {
                    scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 0, null);
                }
            }
        }
        // END 2023/05/26 S.Nakatani [QC#61271,ADD]

        // START 2016/08/25 K.Kojima [QC#11045,DEL]
        // if (!ZYPCommonFunc.hasValue(scrnMsg.arTrxNum_H)) {
        // scrnAppli.setButtonEnabled("OnClick_DisputeAdd", false);
        // }
        // END 2016/08/25 K.Kojima [QC#11045,DEL]
    }

    /**
     * @param scrnMsg  NFDL0040BMsg
     */
    public static void clearScrnBackgroundColor(NFDL0040BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute("NFDL0040Scrn00");
    }

    /**
     * @param scrnMsg  NFDL0040BMsg
     */
    public static void addCheckItem(NFDL0040BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dealCltPrmsAmt_A);
            // START 2017/01/16 H.Ikeda [QC#10786,ADD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).cltPrmsNoteTxt_A);
            // END   2017/01/16 H.Ikeda [QC#10786,ADD]
        }
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).dealCltDsptAmt_B);
            // START 2016/08/26 K.Kojima [QC#10786,ADD]
            scrnMsg.addCheckItem(scrnMsg.B.no(i).cltDsptNoteTxt_B);
            // END 2016/08/26 K.Kojima [QC#10786,ADD]
        }
    }

    // Start 2016/12/27 H.Ikeda [QC#12865,ADD]
    /**
     * hasUpdateFuncId
     * @return boolean
     */
    private static boolean hasUpdateFuncId(NFDL0040BMsg scrnMsg) {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId("NFDL0040");
        if (funcList == null || funcList.isEmpty()) {
            scrnMsg.setMessageInfo("ZZSM4300E", new String[] {userProfSvc.getContextUserInfo().getUserId()});
            return false;
        }
        if (funcList.contains("NFDL0040T020")) {
            return true;
        }
        return false;
    }
    // End  2016/12/27 H.Ikeda [QC#12865,ADD]

    // START 2017/01/16 H.Ikeda [QC#16810,ADD]
    /**
     * @param scrnMsg  NFDL0040BMsg
     */
    public static void checkPromiseAddBtn(NFDL0040BMsg scrnMsg) {
        if (ZYPCommonFunc.hasValue(scrnMsg.dealCltPrmsAmt_AH)) {
            if (BigDecimal.ZERO.compareTo(scrnMsg.dealCltPrmsAmt_AH.getValue()) >= 0) {
                scrnMsg.dealCltPrmsAmt_AH.setErrorInfo(1, "NFDM0043E", new String[] {"Promise Amount"});
            }
        }
    }

    /**
     * @param scrnMsg  NFDL0040BMsg
     */
    public static void checkPromiseAmount(NFDL0040BMsg scrnMsg) {
        // START 2023/05/26 S.Nakatani [QC#61271,MOD]
//        if (ZYPCommonFunc.hasValue(scrnMsg.arTrxNum_H)) {
        if (ZYPCommonFunc.hasValue(scrnMsg.xxTrxNumSrchTxt)) {
        // END 2023/05/26 S.Nakatani [QC#61271,MOD]
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).dealCltPrmsAmt_A)) {
                    BigDecimal dealCltPrmsAmt = scrnMsg.A.no(i).dealCltPrmsAmt_A.getValue();
                    BigDecimal dealRmngBalGrsAmt = BigDecimal.ZERO;
                    if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).dealRmngBalGrsAmt_A2)) {
                        dealRmngBalGrsAmt = scrnMsg.A.no(i).dealRmngBalGrsAmt_A2.getValue();
                    }
                    if (dealCltPrmsAmt.compareTo(dealRmngBalGrsAmt) > 0) {
                        // START 2023/05/26 S.Nakatani [QC#61271,MOD]
//                        scrnMsg.A.no(i).dealCltPrmsAmt_A.setErrorInfo(1, "NFDM0044E", new String[] {"Avtual Invoice Balance", "Promise Amount"});
                        scrnMsg.A.no(i).dealCltPrmsAmt_A.setErrorInfo(1, "NFDM0044E", new String[] {"Actual Invoice Balance", "Promise Amount"});
                        // END 2023/05/26 S.Nakatani [QC#61271,MOD]
                    }
                }
            }
        }
    }

    /**
     * @param scrnMsg  NFDL0040BMsg
     */
    public static void checkDispute(NFDL0040BMsg scrnMsg) {

        Set<String> set =  new HashSet<String>();
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            set.add(scrnMsg.B.no(i).arTrxNum_B.getValue());
        }
        Iterator invNoList = set.iterator();
        while (invNoList.hasNext()){
            String invNo = (String)invNoList.next();
            BigDecimal actInvBal = BigDecimal.ZERO;
            BigDecimal disAmt = BigDecimal.ZERO;
            for (int j = 0; j < scrnMsg.B.getValidCount(); j++) {
                if (invNo.equals(scrnMsg.B.no(j).arTrxNum_B.getValue())) {
                    if (ZYPCommonFunc.hasValue(scrnMsg.B.no(j).dealRmngBalGrsAmt_B)) {
                        actInvBal = actInvBal.add(scrnMsg.B.no(j).dealRmngBalGrsAmt_B.getValue());
                    }
                    if (ZYPCommonFunc.hasValue(scrnMsg.B.no(j).dealCltDsptAmt_B)) {
                        disAmt = disAmt.add(scrnMsg.B.no(j).dealCltDsptAmt_B.getValue());
                    }
                }
                if (ZYPCommonFunc.hasValue(scrnMsg.B.no(j).dealCltDsptAmt_B)) {
                    if (BigDecimal.ZERO.compareTo(scrnMsg.B.no(j).dealCltDsptAmt_B.getValue()) > 0) {
                        scrnMsg.B.no(j).dealCltDsptAmt_B.setErrorInfo(1, "NFDM0043E", new String[] {"Dispute Amount"});
                    }
                }
            }
            if (disAmt.compareTo(actInvBal) > 0) {
                for (int k = 0; k < scrnMsg.B.getValidCount(); k++) {
                    if (invNo.equals(scrnMsg.B.no(k).arTrxNum_B.getValue())) {
                        // START 2023/05/26 S.Nakatani [QC#61271,MOD]
//                        scrnMsg.B.no(k).dealCltDsptAmt_B.setErrorInfo(1, "NFDM0044E", new String[] {"Dispute Amount", "Avtual Invoice Balance"});
                        scrnMsg.B.no(k).dealCltDsptAmt_B.setErrorInfo(1, "NFDM0044E", new String[] {"Actual Invoice Balance", "Dispute Amount"});
                        // END 2023/05/26 S.Nakatani [QC#61271,MOD]
                    }
                }
            }
        }
    }
    // END   2017/01/16 H.Ikeda [QC#16810,ADD]

}
