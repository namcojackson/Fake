/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/07/13   Fujitsu         N.Yamamoto      Create          4486
 *</pre>
 */
package business.blap.NLCL0320;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLCL0320.NLCL0320CMsg;
import business.blap.NLCL0320.NLCL0320SMsg;
import business.blap.NLCL0320.common.NLCL0320CommonLogic;
import business.blap.NLCL0320.constant.NLCL0320Constant;
import business.db.ADJ_ACCT_ALIASTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/11   CSAI            K.Lee           Create          N/A
 *</pre>
 */
public class NLCL0320BL06 extends S21BusinessHandler implements NLCL0320Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("NLCL0320Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NLCL0320CMsg) cMsg, (NLCL0320SMsg) sMsg);
            } else if ("NLCL0320Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NLCL0320CMsg) cMsg, (NLCL0320SMsg) sMsg);
            } else if ("NLCL0320Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLCL0320Scrn00_CMN_Submit(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NLCL0320Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(1, "----- doProcess_NLCL0320Scrn00_CMN_Submit START -----", null);
//
        NLCL0320CMsg bizMsg = (NLCL0320CMsg) cMsg;
        NLCL0320SMsg globalMsg = (NLCL0320SMsg) sMsg;

        NLCL0320CommonLogic.copyPage(bizMsg, bizMsg.A, globalMsg.A);

        int firstErrNum = -1;
        int procCnt = 0;
        boolean errFlg = false;

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).rtlWhCd_A)) {
                globalMsg.A.no(i).rtlWhCd_A.setErrorInfo(1, MESSAGE_ID.NFCM0504E.toString(), new String[] {"Retail Warehouse"});
                errFlg = true;
            }
            if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).adjAcctAliasNm_A)) {
                globalMsg.A.no(i).adjAcctAliasNm_A.setErrorInfo(1, MESSAGE_ID.NFCM0504E.toString(), new String[] {"Account Allias Name"});
                errFlg = true;
            }
            if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).xxScrItem130Txt_A)) {
                globalMsg.A.no(i).xxScrItem130Txt_A.setErrorInfo(1, MESSAGE_ID.NFCM0504E.toString(), new String[] {"Account"});
                errFlg = true;
            } else{
                // check 9seg.
                if(!NLCL0320CommonLogic.checkManualSegmentElementForSMsg(globalMsg, getGlobalCompanyCode(), i)){
                    errFlg = true;
                }
            }
            if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).effFromDt_A)) {
                globalMsg.A.no(i).effFromDt_A.setErrorInfo(1, MESSAGE_ID.NFCM0504E.toString(), new String[] {"Eff From Date"});
                errFlg = true;
            }
            if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).effThruDt_A)) {
                globalMsg.A.no(i).effThruDt_A.setErrorInfo(1 ,MESSAGE_ID.NFCM0504E.toString(), new String[] {"Eff Thru Date"});
                errFlg = true;
            }
            if (errFlg) {
                if (firstErrNum < 0) {
                    firstErrNum = i;
                }
                NLCL0320CommonLogic.firstErrorPage(bizMsg, globalMsg, firstErrNum);
                return;
            }
        }

        int i = 0;

        for (; i < globalMsg.B.getValidCount(); i++) {

            if (NLCL0320CommonLogic.isUpdatedLine(globalMsg, i)) {
                procCnt++;
                if (NLCL0320CommonLogic.hasLineError(getGlobalCompanyCode(), globalMsg, i)) {
                    if (firstErrNum < 0) {
                        firstErrNum = i;
                    }
                    NLCL0320CommonLogic.firstErrorPage(bizMsg, globalMsg, i);
                    return;
                }
                ADJ_ACCT_ALIASTMsg delMsg = new ADJ_ACCT_ALIASTMsg();
                ZYPEZDItemValueSetter.setValue(delMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(delMsg.rtlWhCd, globalMsg.B.no(i).rtlWhCd_B);
                ZYPEZDItemValueSetter.setValue(delMsg.adjAcctAliasNm, globalMsg.B.no(i).adjAcctAliasNm_B);
                ZYPEZDItemValueSetter.setValue(delMsg.effFromDt, globalMsg.B.no(i).effFromDt_B);
                EZDTBLAccessor.remove(delMsg);

                String returnCode = delMsg.getReturnCode();
                if (!RTNCD_NORMAL.equals(returnCode)) {
                    bizMsg.setMessageInfo(MESSAGE_ID.NLBM1295E.toString(), new String[]{delMsg.getTableName()});
                    if (firstErrNum < 0) {
                        firstErrNum = i;
                    }
                    NLCL0320CommonLogic.firstErrorPage(bizMsg, globalMsg, i);
                    return;
                }

                if (NLCL0320CommonLogic.isDuplicate(getGlobalCompanyCode(), globalMsg.A.no(i).rtlWhCd_A.getValue(), globalMsg.A.no(i).adjAcctAliasNm_A.getValue(), globalMsg.A.no(i).effFromDt_A.getValue(), globalMsg.A.no(i).effThruDt_A.getValue(), ZYPConstant.FLG_ON_Y)) {
                    globalMsg.A.no(i).effThruDt_A.setErrorInfo(1, MESSAGE_ID.NFCM0063E.toString(), new String[] {});
                    if (firstErrNum < 0) {
                        firstErrNum = i;
                    }
                    NLCL0320CommonLogic.firstErrorPage(bizMsg, globalMsg, i);
                    return;
                }

                ADJ_ACCT_ALIASTMsg createMsg = new ADJ_ACCT_ALIASTMsg();
                ZYPEZDItemValueSetter.setValue(createMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(createMsg.rtlWhCd, globalMsg.A.no(i).rtlWhCd_A);
                ZYPEZDItemValueSetter.setValue(createMsg.adjAcctAliasNm, globalMsg.A.no(i).adjAcctAliasNm_A);
                ZYPEZDItemValueSetter.setValue(createMsg.effFromDt, globalMsg.A.no(i).effFromDt_A);
                ZYPEZDItemValueSetter.setValue(createMsg.effThruDt, globalMsg.A.no(i).effThruDt_A);
                ZYPEZDItemValueSetter.setValue(createMsg.coaCmpyCd, globalMsg.A.no(i).coaCmpyCd_A);
                ZYPEZDItemValueSetter.setValue(createMsg.coaBrCd, globalMsg.A.no(i).coaBrCd_A);
                ZYPEZDItemValueSetter.setValue(createMsg.coaAcctCd, globalMsg.A.no(i).coaAcctCd_A);
                ZYPEZDItemValueSetter.setValue(createMsg.coaProdCd, globalMsg.A.no(i).coaProdCd_A);
                ZYPEZDItemValueSetter.setValue(createMsg.coaChCd, globalMsg.A.no(i).coaChCd_A);
                ZYPEZDItemValueSetter.setValue(createMsg.coaCcCd, globalMsg.A.no(i).coaCcCd_A);
                ZYPEZDItemValueSetter.setValue(createMsg.coaAfflCd, globalMsg.A.no(i).coaAfflCd_A);
                ZYPEZDItemValueSetter.setValue(createMsg.coaExtnCd, globalMsg.A.no(i).coaExtnCd_A);
                ZYPEZDItemValueSetter.setValue(createMsg.coaProjCd, globalMsg.A.no(i).coaProjCd_A);
                ZYPEZDItemValueSetter.setValue(createMsg.adjAcctAliasDescTxt, globalMsg.A.no(i).adjAcctAliasDescTxt_A);
                EZDTBLAccessor.create(createMsg);

                returnCode = createMsg.getReturnCode();
                if (!RTNCD_NORMAL.equals(returnCode)) {
                    bizMsg.setMessageInfo(MESSAGE_ID.NLBM1295E.toString(), new String[]{createMsg.getTableName()});
                    if (firstErrNum < 0) {
                        firstErrNum = i;
                    }
                    NLCL0320CommonLogic.firstErrorPage(bizMsg, globalMsg, i);
                    return;
                }
            }
        }

        for (; i < globalMsg.A.getValidCount(); i++) {
            procCnt++;
            if (NLCL0320CommonLogic.hasLineError(getGlobalCompanyCode(), globalMsg, i)) {
                if (firstErrNum < 0) {
                    firstErrNum = i;
                }
                NLCL0320CommonLogic.firstErrorPage(bizMsg, globalMsg, i);
                return;
            }

            if (NLCL0320CommonLogic.isDuplicate(getGlobalCompanyCode(), globalMsg.A.no(i).rtlWhCd_A.getValue(), globalMsg.A.no(i).adjAcctAliasNm_A.getValue(), globalMsg.A.no(i).effFromDt_A.getValue(), globalMsg.A.no(i).effThruDt_A.getValue(), ZYPConstant.FLG_OFF_N)) {
                globalMsg.A.no(i).effFromDt_A.setErrorInfo(1, MESSAGE_ID.NFCM0063E.toString(), new String[] {});
                globalMsg.A.no(i).effThruDt_A.setErrorInfo(1, MESSAGE_ID.NFCM0063E.toString(), new String[] {});
                if (firstErrNum < 0) {
                    firstErrNum = i;
                }
                NLCL0320CommonLogic.firstErrorPage(bizMsg, globalMsg, i);
                return;
            }

            ADJ_ACCT_ALIASTMsg createMsg = new ADJ_ACCT_ALIASTMsg();
            ZYPEZDItemValueSetter.setValue(createMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(createMsg.rtlWhCd, globalMsg.A.no(i).rtlWhCd_A);
            ZYPEZDItemValueSetter.setValue(createMsg.adjAcctAliasNm, globalMsg.A.no(i).adjAcctAliasNm_A);
            ZYPEZDItemValueSetter.setValue(createMsg.effFromDt, globalMsg.A.no(i).effFromDt_A);
            ZYPEZDItemValueSetter.setValue(createMsg.effThruDt, globalMsg.A.no(i).effThruDt_A);
            ZYPEZDItemValueSetter.setValue(createMsg.coaCmpyCd, globalMsg.A.no(i).coaCmpyCd_A);
            ZYPEZDItemValueSetter.setValue(createMsg.coaBrCd, globalMsg.A.no(i).coaBrCd_A);
            ZYPEZDItemValueSetter.setValue(createMsg.coaAcctCd, globalMsg.A.no(i).coaAcctCd_A);
            ZYPEZDItemValueSetter.setValue(createMsg.coaProdCd, globalMsg.A.no(i).coaProdCd_A);
            ZYPEZDItemValueSetter.setValue(createMsg.coaChCd, globalMsg.A.no(i).coaChCd_A);
            ZYPEZDItemValueSetter.setValue(createMsg.coaCcCd, globalMsg.A.no(i).coaCcCd_A);
            ZYPEZDItemValueSetter.setValue(createMsg.coaAfflCd, globalMsg.A.no(i).coaAfflCd_A);
            ZYPEZDItemValueSetter.setValue(createMsg.coaExtnCd, globalMsg.A.no(i).coaExtnCd_A);
            ZYPEZDItemValueSetter.setValue(createMsg.coaProjCd, globalMsg.A.no(i).coaProjCd_A);
            ZYPEZDItemValueSetter.setValue(createMsg.adjAcctAliasDescTxt, globalMsg.A.no(i).adjAcctAliasDescTxt_A);
            EZDTBLAccessor.create(createMsg);
            String returnCode = createMsg.getReturnCode();

            if (!RTNCD_NORMAL.equals(returnCode)) {
                bizMsg.setMessageInfo(MESSAGE_ID.NLBM1295E.toString() , new String[]{createMsg.getTableName()});
                if (firstErrNum < 0) {
                    firstErrNum = i;
                }
                NLCL0320CommonLogic.firstErrorPage(bizMsg, globalMsg, i);
                return;
            }
        }
        if (procCnt == 0) {
            bizMsg.setMessageInfo(MESSAGE_ID.NFCM0764E.toString());
        }
        EZDDebugOutput.println(1, "----- doProcess_NLCL0320Scrn00_CMN_Submit END -----", null);
    }
}
