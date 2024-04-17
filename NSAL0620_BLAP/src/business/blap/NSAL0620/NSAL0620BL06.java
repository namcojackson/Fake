/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0620;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0620.common.NSAL0620CommonLogic;
import business.blap.NSAL0620.constant.NSAL0620Constant.MASS_UPDATE_LIST;
import business.blap.NSAL0620.constant.NSAL0620Constant.MSG_ID;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/23   Hitachi         T.Tsuchida      Create          N/A
 * 2016/02/19   Hitachi         T.Aoyagi        Update          QC3694
 * 2016/02/26   Hitachi         O.Okuma         Update          QC3029
 * 2016/03/14   Hitachi         M.Gotou         Update          QC4428
 * 2016/05/18   Hitachi         A.Kohinata      Update          QC4212
 * 2016/07/28   Hitachi         A.Kohinata      Update          QC6923
 * 2016/08/25   Hitachi         A.Kohinata      Update          QC#11027
 * 2017/02/07   Hitachi         K.Kojima        Update          QC#17303
 * 2017/08/21   Hitachi         E.Kameishi      Update          QC#8661
 *</pre>
 */
public class NSAL0620BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        String screenAplId = cMsg.getScreenAplID();

        try {

            if (screenAplId.startsWith("NSAL0620Scrn00")) {
                if (screenAplId.endsWith("_OpenWin_PrepMassUpdScrn")) {
                doProcess_NSAL0620_OpenWin_PrepMassUpdScrn((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

                } else if (screenAplId.endsWith("SaveSrchOpt")) {
                    doProcess_NSAL0620_SaveSearch((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

                } else if (screenAplId.endsWith("DelSrchOpt")) {
                    doProcess_NSAL0620_DeleteSearch((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

                } else if (screenAplId.endsWith("CMN_ColSave")) {
                    ZYPGUITableColumn.setColData((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

                } else if (screenAplId.endsWith("CMN_ColClear")) {
                    ZYPGUITableColumn.clearColData((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);
                //START 2017/08/21 E.Kameishi [QC#8661,ADD]
                } else if (screenAplId.endsWith("Print")) {
                    doProcess_NSAL0620_Print((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);
                }
                //END 2017/08/21 E.Kameishi [QC#8661,ADD]

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }
            return;

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NSAL0620_OpenWin_PrepMassUpdScrn(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {
        NSAL0620CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        // START 2017/02/07 K.Kojima [QC#17303,ADD]
        if (!MASS_UPDATE_LIST.ZYPL0210.getScrnId().equals(cMsg.xxGenlFldAreaTxt_B.getValue())) {
        // END 2017/02/07 K.Kojima [QC#17303,ADD]
        if (!NSAL0620CommonLogic.checkSelect(cMsg, sMsg)) {
            return;
        }
        // START 2017/02/07 K.Kojima [QC#17303,ADD]
        }
        // END 2017/02/07 K.Kojima [QC#17303,ADD]
        if (!ZYPCommonFunc.hasValue(cMsg.xxGenlFldAreaTxt_B)) {
            cMsg.xxGenlFldAreaTxt_B.setErrorInfo(1, MSG_ID.NSAM0366E.toString());
        }
        if (MASS_UPDATE_LIST.NSAL0710.getScrnId().equals(cMsg.xxGenlFldAreaTxt_B.getValue())) {
        } else if (MASS_UPDATE_LIST.NSAL0460.getScrnId().equals(cMsg.xxGenlFldAreaTxt_B.getValue())) {
        } else if (MASS_UPDATE_LIST.NSAL0400.getScrnId().equals(cMsg.xxGenlFldAreaTxt_B.getValue())) {
            // add start 2016/08/25 CSA Defect#11027
            if (NSAL0620CommonLogic.isExistExcludedTerminateContr(cMsg, sMsg)) {
                return;
            }
            // add end 2016/08/25 CSA Defect#11027
        } else if (MASS_UPDATE_LIST.NSAL0690.getScrnId().equals(cMsg.xxGenlFldAreaTxt_B.getValue())) {
            // add start 2016/07/28 CSA Defect#6923
            if (NSAL0620CommonLogic.isExistNotManualRenewContr(cMsg, sMsg)) {
                return;
            }
            // add end 2016/07/28 CSA Defect#6923
        } else if (MASS_UPDATE_LIST.NSAL0670.getScrnId().equals(cMsg.xxGenlFldAreaTxt_B.getValue())) {
        } else if (MASS_UPDATE_LIST.NSAL0630.getScrnId().equals(cMsg.xxGenlFldAreaTxt_B.getValue())) {
        } else if (MASS_UPDATE_LIST.NSAL0700.getScrnId().equals(cMsg.xxGenlFldAreaTxt_B.getValue())) {
        } else if (MASS_UPDATE_LIST.NSAL0640.getScrnId().equals(cMsg.xxGenlFldAreaTxt_B.getValue())) {
        } else if (MASS_UPDATE_LIST.NSAL0730.getScrnId().equals(cMsg.xxGenlFldAreaTxt_B.getValue())) {
        } else if (MASS_UPDATE_LIST.NSAL0390.getScrnId().equals(cMsg.xxGenlFldAreaTxt_B.getValue())) {
            // START 2016/05/19 [QC4212, DEL]
//            if (!NSAL0620CommonLogic.checkOnlyOneSelect(cMsg, sMsg)) {
//                return;
//            }
            // END 2016/05/19 [QC4212, DEL]
        } else if (MASS_UPDATE_LIST.NSAL0650.getScrnId().equals(cMsg.xxGenlFldAreaTxt_B.getValue())) {
        } else if (MASS_UPDATE_LIST.NSAL0660.getScrnId().equals(cMsg.xxGenlFldAreaTxt_B.getValue())) {
        } else if (MASS_UPDATE_LIST.NSAL0750.getScrnId().equals(cMsg.xxGenlFldAreaTxt_B.getValue())) {
        } else if (MASS_UPDATE_LIST.NSAL0720.getScrnId().equals(cMsg.xxGenlFldAreaTxt_B.getValue())) {
        } else if (MASS_UPDATE_LIST.NSAL0740.getScrnId().equals(cMsg.xxGenlFldAreaTxt_B.getValue())) {
        } else if (MASS_UPDATE_LIST.NSAL0380.getScrnId().equals(cMsg.xxGenlFldAreaTxt_B.getValue())) {
            // START 2016/02/16 O.Okuma [QC3029, DEL]
//            if (!NSAL0620CommonLogic.checkOnlyOneSelect(cMsg, sMsg)) {
//                return;
//            }
            // END 2016/02/16 O.Okuma [QC3029, DEL]
        // START 2017/02/07 K.Kojima [QC#17303,ADD]
        } else if (MASS_UPDATE_LIST.ZYPL0210.getScrnId().equals(cMsg.xxGenlFldAreaTxt_B.getValue())) {
        // END 2017/02/07 K.Kojima [QC#17303,ADD]
        } else {
            cMsg.setMessageInfo(MSG_ID.NSAM0366E.toString());
        }
        ZYPTableUtil.clear(cMsg.P);
        int index = 0;
        // mod start 2016/03/14 CSA Defect#4428
        List<BigDecimal> chkList = new ArrayList<BigDecimal>();
        BigDecimal chkIndex = null;
        // mod end 2016/03/14 CSA Defect#4428
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).xxChkBox_A)
                    && ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.P.no(index).dsContrPk_P1, sMsg.A.no(i).dsContrPk_A);
                // START 2016/02/19 T.Aoyagi [QC3694, ADD]
                ZYPEZDItemValueSetter.setValue(cMsg.P.no(index).dsContrDtlPk_P1, sMsg.A.no(i).dsContrDtlPk_A);
                // END 2016/02/19 T.Aoyagi [QC3694, ADD]
                ZYPEZDItemValueSetter.setValue(cMsg.P.no(index).svcMachMstrPk_P1, sMsg.A.no(i).svcMachMstrPk_A);
                index++;
                // mod start 2016/03/14 CSA Defect#4428
                if (MASS_UPDATE_LIST.NSAL0750.getScrnId().equals(cMsg.xxGenlFldAreaTxt_B.getValue())) {
                    if (sMsg.A.no(i).dsContrCatgCd_A.getValue().equals(DS_CONTR_CATG.WARRANTY)) {
                        if (chkIndex == null) {
                            chkIndex = new BigDecimal(i);
                        }
                        chkList.add(sMsg.A.no(i).dsContrPk_A.getValue());
                    }
                }
                // mod end 2016/03/14 CSA Defect#4428
            }
        }
        // mod start 2016/03/14 CSA Defect#4428
        chkInvoiceRule(chkList, chkIndex, cMsg, sMsg);
        cMsg.setCommitSMsg(true);
        // mod end 2016/03/14 CSA Defect#4428
        cMsg.P.setValidCount(index);
    }

    // mod start 2016/03/14 CSA Defect#4428
    private void chkInvoiceRule(List<BigDecimal> chkList, BigDecimal chkIndex, NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {
        if (chkList.isEmpty()) {
            return;
        }
        BigDecimal fromNum;
        BigDecimal len = new BigDecimal(cMsg.A.length());
        fromNum = chkIndex.divide(len, 0, BigDecimal.ROUND_DOWN);
        fromNum = fromNum.multiply(len).add(BigDecimal.ONE);
        cMsg.xxPageShowFromNum.setValue(fromNum);
        NSAL0620CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            for (int index = 0; index < chkList.size(); index++) {
                if ((ZYPCommonFunc.hasValue(cMsg.A.no(i).xxChkBox_A))
                        && (cMsg.A.no(i).dsContrPk_A.getValue().equals(chkList.get(index)))) {
                    cMsg.A.no(i).xxChkBox_A.setErrorInfo(1, MSG_ID.NSAM0441E.toString(), new String[] {"Warranty Contract" });
                }
            }
        }
    }
    // mod end 2016/03/14 CSA Defect#4428

    private void doProcess_NSAL0620_SaveSearch(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S) //
                && !ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {
            cMsg.srchOptNm_S.setErrorInfo(1, MSG_ID.ZZM9000E.toString(), new String[] {"Search Option Name" });
            return;
        }
        if (NSAL0620CommonLogic.isExistSaveSearchName(cMsg)) {
            cMsg.srchOptNm_S.setErrorInfo(1, MSG_ID.NSAM0059E.toString(), new String[] {"Search Option Name" });
            return;
        }

        NSAL0620CommonLogic.callNszc0330forSaveSearch(cMsg, sMsg, getContextUserInfo().getUserId());
    }

    private void doProcess_NSAL0620_DeleteSearch(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {
            cMsg.srchOptPk_S.setErrorInfo(1, MSG_ID.NSAM0199E.toString(), new String[] {"Saved Search Options" });
            return;
        }

        NSAL0620CommonLogic.callNszc0330forDeleteSearch(cMsg, sMsg, getContextUserInfo().getUserId());
    }
    //START 2017/08/21 E.Kameishi [QC#8661,ADD]
    private void doProcess_NSAL0620_Print(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {
        NSAL0620CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        if (!NSAL0620CommonLogic.checkSelect(cMsg, sMsg)) {
            return;
        }
        if (!NSAL0620CommonLogic.checkOverSelect(cMsg, sMsg)) {
            return;
        }

        NSAL0620CommonLogic.createLetter(cMsg, sMsg);
    }
    //END 2017/08/21 E.Kameishi [QC#8661,ADD]
}
