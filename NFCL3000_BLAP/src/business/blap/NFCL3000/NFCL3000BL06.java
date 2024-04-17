/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL3000;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL3000.common.NFCL3000CommonLogic;
import business.blap.NFCL3000.common.NFCL3000CommonLogicForConst;
import business.blap.NFCL3000.constant.NFCL3000Constant;
import business.db.AJE_INV_ACCT_DISTTMsg;
import business.db.DS_INV_TPTMsg;
import business.db.SVC_INV_CHRG_TPTMsg;
import business.parts.NFACommonJrnlEntry;
import business.parts.NMZC600001PMsg;
import business.parts.NWZC040001PMsg;
import business.parts.NWZC040002PMsg;
import business.parts.NWZC040003PMsg;
import business.parts.NWZC040005PMsg;
import business.parts.NWZC040006PMsg;
import business.parts.NWZC040007PMsg;

import com.canon.cusa.s21.api.NFA.NFZC103001.common.NFZC103001Common;
import com.canon.cusa.s21.api.NMZ.NMZC600001.NMZC600001;
import com.canon.cusa.s21.api.NWZ.NWZC040001.NWZC040001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_INV_ACCT_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_DEF_ACCT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BIZ_AREA;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SLS_TAX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Fujitsu         T.Tanaka        Create          N/A
 * 2016/05/03   CSAI            K.Uramori       Update          QC#7444
 * 2016/05/10   Fujitsu         S.Fujita        Update          QC#6961
 * 2016/05/17   Fujitsu         S.Fujita        Update          QC#8373
 * 2016/05/17   Fujitsu         S.Fujita        Update          QC#8375
 * 2016/05/20   Fujitsu         S.Fujita        Update          QC#8550
 * 2016/05/24   Fujitsu         S.Fujita        Update          QC#8522
 * 2016/06/03   Fujitsu         S.Fujita        Update          QC#9452
 * 2016/06/07   Fujitsu         S.Fujita        Update          QC#9515
 * 2016/06/07   Fujitsu         S.Fujita        Update          QC#9590
 * 2016/06/07   Fujitsu         S.Fujita        Update          QC#9216
 * 2016/06/20   Fujitsu         S.Fujita        Update          QC#9454
 * 2016/07/11   Fujitsu         S.Fujita        Update          QC#10160
 * 2016/07/19   Fujitsu         S.Yoshida       Update          QC#12006
 * 2016/07/20   Fujitsu         S.Yoshida       Update          QC#12149
 * 2016/07/20   Fujitsu         S.Fujita        Update          QC#10148
 * 2016/07/29   Fujitsu         S.Fujita        Update          QC#12503
 * 2016/08/03   Fujitsu         S.Fujita        Update          QC#12864
 * 2016/08/05   Fujitsu         S.Fujita        Update          QC#13110
 * 2016/09/12   Fujitsu         S.Fujita        Update          QC#14112
 * 2016/09/21   Fujitsu         S.Fujita        Update          QC#14114
 * 2016/09/21   Fujitsu         S.Yoshida       Update          QC#11049
 * 2016/09/21   Fujitsu         S.Fujita        Update          QC#14481
 * 2016/09/26   Fujitsu         S.Fujita        Update          QC#13362
 * 2016/10/14   Fujitsu         S.Fujita        Update          QC#10281
 * 2016/10/26   Fujitsu         T.Murai         Update          QC#13639
 * 2016/11/17   Fujitsu         T.Murai         Update          QC#15987
 * 2016/11/22   Fujitsu         S.Fujita        Update          QC#16154
 * 2017/03/09   Fujitsu         T.Murai         Update          QC#17901
 * 2017/03/13   Fujitsu         T.Murai         Update          QC#17933
 * 2017/10/04   Fujitsu         J.Kim           Update          QC#21461
 * 2017/11/17   Hitachi         E.Kameishi      Update          QC#19735
 * 2017/12/25   Hitachi         E.Kameishi      Update          QC#20312
 * 2018/03/29   Hitachi         E.Kameishi      Update          QC#24390
 * 2018/05/10   Hitachi         E.Kameishi      Update          QC#25716
 * 2018/05/22   Fujitsu         Y.Matsui        Update          QC#21841
 * 2018/06/01   Fujitsu         Y.Matsui        Update          QC#21841
 * 2018/07/17   Hitachi         E.Kameishi      Update          QC#27007
 * 2018/07/25   Fujitsu         S.Ohki          Update          QC#26968
 * 2018/09/28   Fujitsu         T.Ogura         Update          QC#28526
 * 2018/10/24   Fujitsu         S.Takami        Update          QC#27069
 * 2019/04/17   Fujitsu         S.Takami        Update          QC#31204
 * 2019/04/25   Fujitsu         S.Takami        Update          QC#50078
 * 2019/05/10   Fujitsu         S.Takami        Update          QC#50148
 * 2019/06/05   Fujitsu         S.Takami        Update          QC#50683
 * 2019/06/06   Fujitsu         S.Takami        Update          QC#50691
 * 2020/04/22   Fujitsu         H.Mizukami      Update          QC#56117
 * 2021/06/16   CITS            G.Delgado       Update          QC#57086
 * 2021/10/25   CITS            S.Go            Update          QC#55215
 *</pre>
 */
public class NFCL3000BL06 extends S21BusinessHandler implements NFCL3000Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            // START 2018/09/28 T.Ogura [QC#28526,ADD]
            NFCL3000CommonLogic.setEachQtyToShipQty(cMsg);
            // END   2018/09/28 T.Ogura [QC#28526,ADD]

            if ("NFCL3000Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_CMN_Submit(cMsg, sMsg);
            // START 2016/06/03 S.Fujita [QC#9452,ADD]
            } else if ("NFCL3000Scrn00_TAB_Accounting".equals(screenAplID)) {
                doProcess_NFCL3000Scrn00_TAB_Accounting(cMsg, sMsg);
            // END   2016/06/03 S.Fujita [QC#9452,ADD]
            // START 2018/07/13 S.Ohki [QC#26968,MOD]
//              // START 2016/08/01 S.Fujita [QC#10148,ADD]
//              } else if ("NFCL3050Scrn00_CMN_ColClear".equals(screenAplID)) {
//                  ZYPGUITableColumn.clearColData(cMsg, sMsg);
//              } else if ("NFCL3050Scrn00_CMN_ColSave".equals(screenAplID)) {
//                  ZYPGUITableColumn.setColData(cMsg, sMsg);
//              // END   2016/08/01 S.Fujita [QC#10148,ADD]
            } else if ("NFCL3000Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NFCL3000CMsg) cMsg, (NFCL3000SMsg) sMsg);
            } else if ("NFCL3000Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NFCL3000CMsg) cMsg, (NFCL3000SMsg) sMsg);
            // END   2018/07/13 S.Ohki [QC#26968,MOD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Singleton instance.
     */
    private static final NFCL3000BL06 myInstance = new NFCL3000BL06();

    /**
     * Singleton instance getter.
     * @return NFCL3000BL06
     */
    public static NFCL3000BL06 getInstance() {
        return myInstance;
    }

    /**
     * doProcess_NFCL3000Scrn00_CMN_Submit
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

//        // START 2018/09/28 T.Ogura [QC#28526,ADD]
//        NFCL3000CommonLogic.setEachQtyToShipQty(bizMsg);
//        // END   2018/09/28 T.Ogura [QC#28526,ADD]

        // START 2016/07/20 S.Fujita [QC#10148,DEL]
//        if(bizMsg.xxRadioBtn_C.getValue().equals(ACCT_DIST_SMRY)) {
//            NFCL3000CommonLogic.setEditMode(bizMsg, globalMsg);
//        }
        // END   2016/07/20 S.Fujita [QC#10148,DEL]

        // START 2016/07/28 S.Fujita [QC#10148,ADD]
        if(!NFCL3000CommonLogic.checkHeader(bizMsg)) {
            return;
        }
        // END   2016/07/28 S.Fujita [QC#10148,ADD]
        // START 2019/04/17 S.Takami [QC#31204,ADD]
        // START 2019/04/25 S.Takami [QC#50078,MOD]
//        if (!checkConfigData(bizMsg)) {
//            return;
//        }
        boolean isErrCheckConfigData = !checkConfigData(bizMsg);
        // END 2019/04/25 S.Takami [QC#50078,MOD]
        // END 2019/04/17 S.Takami [QC#31204,ADD]

        // START 2019/04/25 S.Takami [QC#50078,ADD]
        boolean isErrCheckFsrLine = false;
        if (NFCL3000CommonLogic.isDsInvTpFsr(bizMsg)) {
            isErrCheckFsrLine = !checkFsrLine(bizMsg);
        }

        boolean isMandatoryErrorForInvoiceType = isMandatoryErrorForInvoiceType(bizMsg);
        if (isErrCheckConfigData || isErrCheckFsrLine || isMandatoryErrorForInvoiceType) {
            return;
        }
        // END 2019/04/25 S.Takami [QC#50078,ADD]

        if(ZYPCommonFunc.hasValue(bizMsg.invNum_H1.getValue())) {
            // START 2018/05/10 E.Kameishi [QC#25716,ADD]
            if(bizMsg.D.getValidCount() > 0) {
                NFCL3000CommonLogic.setDefaultBOL(bizMsg, 0, true);
            }
            // END 2018/05/10 E.Kameishi [QC#25716,ADD]
            if(!checkInvoice(cMsg, sMsg)) {
                return;
            }

            updateInvoice(cMsg, sMsg);

        } else {

            initialSetup_SlsCr_BOL(cMsg, sMsg);
            if(!checkInvoice(cMsg, sMsg)) {
                return;
            }
            if(!createInvoice(cMsg, sMsg)) {
                return;
            }
        }
    }

    /**
     * initialSetup_SlsCr_BOL
     * @param cMsg
     * @param sMsg
     * @return
     */
    private boolean initialSetup_SlsCr_BOL(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        NFCL3000CommonLogic.check_LineTAB(bizMsg);
        NFCL3000CommonLogic.calcAmount_InvLine(bizMsg);
        if(bizMsg.B.getValidCount() < 1) {
            NFCL3000CommonLogic.initialSetup_SlsCrTab(bizMsg);
            NFCL3000CommonLogic.calcAmount_SlsCr(bizMsg);
        }

        if(bizMsg.D.getValidCount() < 1) {
            NFCL3000CommonLogic.initialSetup_BOLTab(bizMsg);
        }

        NFCL3000CommonLogic.setCheckBox(bizMsg);
 
        return true;
    }

    /**
     * checkInvoice
     * @param cMsg
     * @param sMsg
     * @return
     */
    private boolean checkInvoice(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        NFCL3000SMsg globalMsg = (NFCL3000SMsg) sMsg;

        // START 2017/03/13 [QC#17933,ADD]
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.fnlzInvFlg_H4.getValue())) {
            return true;
        }
        // END   2017/03/13 [QC#17933,ADD]

        // Check Line TAB
        if(!NFCL3000CommonLogic.check_LineTAB(bizMsg)) {
            bizMsg.xxDplyTab.setValue(TAB_Line);
            return false;
        }

        // START 2017/10/04 J.Kim [QC#21461,MOD]
        // // Before adding lines, calc Line, BOL and Total Amount
        // if (!NFCL3000CommonLogic.calcAmount(bizMsg, true)) {
        //    return false;
        // }
        if (!NFCL3000CommonLogic.calcAmount(bizMsg, false)) {
            return false;
        }
        // END 2017/10/04 J.Kim [QC#21461,MOD]

        // START 2020/04/22 [QC#56117,ADD]
        if (!NFCL3000CommonLogic.chkMeterChargeAmount(bizMsg)) {
            return false;
        }
        // END   2020/04/22 [QC#56117,ADD]

        // START 2018/07/17 E.Kameishi [QC#27007,ADD]
        NFCL3000CommonLogic.deleteLine_SlsCrTab(bizMsg, globalMsg);
        NFCL3000CommonLogic.setupMdseCd(bizMsg);
        // END 2018/07/17 E.Kameishi [QC#27007,ADD]
        // START 2016/07/20 S.Fujita [QC#10148,ADD]
        if(ZYPCommonFunc.hasValue(bizMsg.invNum_H1.getValue())) {
            NFCL3000CommonLogic.defaultSetup_SlsCrTabAndAcctTab(bizMsg, globalMsg);

            // START 2016/09/12 S.Fujita [QC#14112,ADD]
            NFCL3000CommonLogic.deleteLine_SlsCrTabAndAcctTab(bizMsg, globalMsg);
            // END   2016/09/12 S.Fujita [QC#14112,ADD]

            // START 2017/10/04 J.Kim [QC#21461,ADD]
            // START 2021/06/16 G.Delgado [QC#57086,MOD]
            // if (!NFCL3000CommonLogic.calcAmount(bizMsg, true)) {
            if (!NFCL3000CommonLogic.calcAmount(bizMsg, false)) {
            // END 2021/06/16 G.Delgado [QC#57086,MOD]
                return false;
            }
            // END 2017/10/04 J.Kim [QC#21461,ADD]
        }

        // START 2017/10/04 J.Kim [QC#21461,DEL]
        //// After lines are deleted, calc Line, BOL and Total Amount
        //if (!NFCL3000CommonLogic.calcAmount(bizMsg, true)) {
        //    return false;
        // }
        //// END   2016/07/20 S.Fujita [QC#10148,ADD]
        // END 2017/10/04 J.Kim [QC#21461,DEL]

        // Check Sales Credit TAB
        if(!NFCL3000CommonLogic.check_SlsCrTAB(bizMsg)) {
            bizMsg.xxDplyTab.setValue(TAB_SalesCredit);
            return false;
        }
        // Check Accounting TAB
        if(!NFCL3000CommonLogic.check_AccountingTAB(bizMsg)) {
            bizMsg.xxDplyTab.setValue(TAB_Accounting);
            return false;
        }

        // START 2016/07/20 S.Fujita [QC#10148,ADD]
        if(!NFCL3000CommonLogic.checkSalesCrBolLine(bizMsg)) {
            return false;
        }
        // END   2016/07/20 S.Fujita [QC#10148,ADD]

        // START 2021/06/16 G.Delgado [QC#57086,ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.invNum_H1.getValue())) {
            if (!NFCL3000CommonLogic.calcAmount(bizMsg, true)) {
                return false;
            }
        }
        // END 2021/06/16 G.Delgado [QC#57086,ADD]

        NFCL3000CommonLogic.setCheckBox(bizMsg);
        NFCL3000CommonLogic.setShipAmt(bizMsg);
        // START 2018/07/17 E.Kameishi [QC#27007,ADD]
        NFCL3000CommonLogic.delAcctDist(bizMsg);
        // END 2018/07/17 E.Kameishi [QC#27007,ADD]

        return true;
    }

    /**
     * createInvoice
     * @param cMsg
     * @param sMsg
     */
    private boolean createInvoice(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        /****************************************/
        /* Invoice Header Parameter             */
        /****************************************/
        List<NWZC040001PMsg> pMsgHdrList = new ArrayList<NWZC040001PMsg>();

        NWZC040001PMsg pMsg1 = new NWZC040001PMsg();

        // START 2016/07/19 S.Yoshida [QC#12006,ADD]
        String invTpCd = bizMsg.invTpCd_H1.getValue();
        // END   2016/07/19 S.Yoshida [QC#12006,ADD]

        // Get DEF_SRC_SYS_DOC_NUM
        String defSrcSysDocNum = ZYPCodeDataUtil.getVarCharConstValue("DEF_SRC_SYS_DOC_NUM", bizMsg.glblCmpyCd.getValue());
        if (!ZYPCommonFunc.hasValue(bizMsg.srcSysDocNum_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.srcSysDocNum_H1,defSrcSysDocNum);
        }

        // START 2016/09/21 S.Fujita [QC#14114,ADD]
        String manAutoSeqCd = ZYPCodeDataUtil.getVarCharConstValue("NFCL3000_AUTO_SEQ_CD", bizMsg.glblCmpyCd.getValue());
        String manInvNum = BLANK;
        if (ZYPCommonFunc.hasValue(manAutoSeqCd)) {
            // START 2017/11/17 E.Kameishi [QC#19735, MOD]
            //manInvNum = ZYPExtnNumbering.getUniqueID(bizMsg.glblCmpyCd.getValue(), manAutoSeqCd);
            manInvNum = ZYPMaxTenDigitsNumbering.getUniqueID(bizMsg.glblCmpyCd.getValue(), manAutoSeqCd);
            // END 2017/11/17 E.Kameishi [QC#19735, MOD]
        }
        // END   2016/09/21 S.Fujita [QC#14114,MOD]

        // START 2017/12/25 E.Kameishi [QC#20312,ADD]
        boolean isTaxAdj = false;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxMstChkFlg_A1.getValue())) {
                isTaxAdj = true;            }
        }
        // END 2017/12/25 E.Kameishi [QC#20312,ADD]
        // START 2017/10/04 J.Kim [QC#21461,ADD]
        bizMsg.invNum_H1.setValue(manInvNum);
        if (!NFCL3000CommonLogic.calcAmount(bizMsg, true)) {
            bizMsg.invNum_H1.clear();
            return false;
        }
        bizMsg.invNum_H1.clear();
        // END 20176/10/04 J.Kim [QC#21461,ADD]

        ZYPEZDItemValueSetter.setValue(pMsg1.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg1.invNum, manInvNum);
        ZYPEZDItemValueSetter.setValue(pMsg1.origInvNum, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.grpInvNum, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.invDt, bizMsg.invDt_H1.getValue());
        // START 2016/08/01 S.Fujita [QC#10148,MOD]
//        ZYPEZDItemValueSetter.setValue(pMsg1.acctDt, bizMsg.acctDt.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.acctDt_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg1.acctDt, bizMsg.acctDt_H1.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg1.acctDt, bizMsg.acctDt.getValue());
        }
        // END   2016/08/01 S.Fujita [QC#10148,MOD]
        ZYPEZDItemValueSetter.setValue(pMsg1.invTpCd, bizMsg.invTpCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg1.netDueDt, bizMsg.netDueDt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg1.cpoOrdNum, "");
        // START 2016/10/27 T.Murai [QC#13639, MOD]
        //ZYPEZDItemValueSetter.setValue(pMsg1.ordDt, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.ordDt, bizMsg.A.no(0).ordDt_A1);
        // END   2016/10/27 T.Murai [QC#13639, MOD]
        ZYPEZDItemValueSetter.setValue(pMsg1.custIssPoNum, bizMsg.custIssPoNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg1.custIssPoDt, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.billToCustCd, bizMsg.billToCustCd_H3.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg1.sellToCustCd, bizMsg.sellToCustCd_H3.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg1.sellToLocNm, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.sellToAddlLocNm, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.sellToFirstLineAddr, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.sellToScdLineAddr, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.sellToThirdLineAddr, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.sellToFrthLineAddr, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.sellToCtyAddr, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.sellToCntyNm, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.sellToProvNm, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.sellToStCd, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.sellToPostCd, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.sellToCtryCd, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.sellToFirstRefCmntTxt, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.sellToScdRefCmntTxt, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.payerCustCd, bizMsg.billToCustCd_H3.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg1.advRcptNum, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.pmtTermStartDt, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.invPmtMethCd, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.invPmtCondCd, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.pmtTermCd, bizMsg.pmtTermCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg1.pmtTermNm, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.cashDiscTermCd, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.slsAdminPsnCd, "");
        // START 2016/07/20 S.Yoshida [QC#12149,MOD]
//        //---- start 2016/05/04 If it's credit memo, negate the amount
//        ZYPEZDItemValueSetter.setValue(pMsg1.invTotDealNetAmt, bizMsg.xxTotAmt_T4.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//        ZYPEZDItemValueSetter.setValue(pMsg1.invTotDealSlsAmt, bizMsg.xxTotAmt_T1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//        ZYPEZDItemValueSetter.setValue(pMsg1.invTotDealFrtAmt, bizMsg.xxTotAmt_T2.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//        ZYPEZDItemValueSetter.setValue(pMsg1.invTotDealTaxAmt, bizMsg.xxTotAmt_T3.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//        //---- end 2016/05/04
        ZYPEZDItemValueSetter.setValue(pMsg1.invTotDealNetAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.xxTotAmt_T4));
        ZYPEZDItemValueSetter.setValue(pMsg1.invTotDealSlsAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.xxTotAmt_T1));
        ZYPEZDItemValueSetter.setValue(pMsg1.invTotDealFrtAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.xxTotAmt_T2));
        ZYPEZDItemValueSetter.setValue(pMsg1.invTotDealTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.xxTotAmt_T3));
        // END   2016/07/20 S.Yoshida [QC#12149,MOD]
        ZYPEZDItemValueSetter.setValue(pMsg1.invTotDealDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg1.invTotDealInsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg1.invTotFuncInsAmt, BigDecimal.ZERO);
        // START 2019/05/10 S.Takami [QC#50148,MOD]
//        ZYPEZDItemValueSetter.setValue(pMsg1.invFirstCmntTxt, "");
//        ZYPEZDItemValueSetter.setValue(pMsg1.invScdCmntTxt, "");
//        ZYPEZDItemValueSetter.setValue(pMsg1.invThirdCmntTxt, "");
//        ZYPEZDItemValueSetter.setValue(pMsg1.invFrthCmntTxt, "");
        String[] devidedComments = NFCL3000CommonLogic.devideMoreInfoComment(bizMsg.xxInvMemoTxt_E1.getValue());
        int cmntIdx = 0;
        ZYPEZDItemValueSetter.setValue(pMsg1.invFirstCmntTxt, devidedComments[cmntIdx++]);
        ZYPEZDItemValueSetter.setValue(pMsg1.invScdCmntTxt, devidedComments[cmntIdx++]);
        ZYPEZDItemValueSetter.setValue(pMsg1.invThirdCmntTxt, devidedComments[cmntIdx++]);
        ZYPEZDItemValueSetter.setValue(pMsg1.invFrthCmntTxt, devidedComments[cmntIdx]);
        // END 2019/05/10 S.Takami [QC#50148,MOD]
        ZYPEZDItemValueSetter.setValue(pMsg1.dealCcyCd, bizMsg.stdCcyCd.getValue());
//20160303
        ZYPEZDItemValueSetter.setValue(pMsg1.actlApplyExchRate, BigDecimal.ONE);

        ZYPEZDItemValueSetter.setValue(pMsg1.flPlnFlg, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.invPrintStsCd, bizMsg.invPrintStsCd_E1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg1.trxSrcTpCd, TRX_SRC_TP.WHOLE_SALES);
        ZYPEZDItemValueSetter.setValue(pMsg1.invMlSendStsCd, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.invEdiSendStsCd, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.invFaxSendStsCd, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.invEmlSendStsCd, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.authCd, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.crDrRsnCd, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.crDrRsnSubCd, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.sysSrcCd, bizMsg.sysSrcCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg1.rossOrdTpCd, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.pmtTermCashDiscCd, bizMsg.pmtTermCashDiscCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg1.crCardOrdNum, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.histCratCpltFlg, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.dsOrdTpCd, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.dsOrdRsnCd, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.dsOrdRsnCd, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.invRcpntCustCd, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.crCardChrgCpltCd, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.crCardCustRefNum, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.crCardAuthRefNum, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.crCardAuthDt, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.crCardTpCd, "");
//        ZYPEZDItemValueSetter.setValue(pMsg1.dsInvExprDt, bizMsg.crCardExprYrMth_E.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg1.easyPackRate, "");
//        ZYPEZDItemValueSetter.setValue(pMsg1.easyPackMthQuotSumQty, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.dsInvTpCd, bizMsg.dsInvTpCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg1.srcSysDocNum, bizMsg.srcSysDocNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg1.slsRepTocCd, bizMsg.slsRepTocCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg1.itrlInvErrFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pMsg1.custCareTktNum, bizMsg.custCareTktNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg1.billToCustAcctCd, bizMsg.billToCustAcctCd_H3.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg1.svcInvPk, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.soldToCustLocCd, bizMsg.billToCustCd_H3.getValue());
        // START 2021/10/25 S.Go [QC#55215,MOD]
//        ZYPEZDItemValueSetter.setValue(pMsg1.lineBizTpCd, bizMsg.lineBizTpCd_H3.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg1.lineBizTpCd, NFCL3000Query.getInstance().getCOALineBizTpCd(bizMsg));
        // END 2021/10/25 S.Go [QC#55215,MOD]
        ZYPEZDItemValueSetter.setValue(pMsg1.dsBizAreaCd, DS_BIZ_AREA.INVOICING);
        ZYPEZDItemValueSetter.setValue(pMsg1.dplyMdseDtlFlg, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.billToCtacPsnFirstNm, bizMsg.billToCtacPsnFirstNm_H3.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg1.billToCtacPsnMidNm, "");
        ZYPEZDItemValueSetter.setValue(pMsg1.billToCtacPsnLastNm, bizMsg.billToCtacPsnLastNm_H3.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg1.invPrtBatTpCd, "");
//        ZYPEZDItemValueSetter.setValue(pMsg1.contrRnwTotCnt,);
        // START 2019/06/06 S.Takami [QC#50691,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg1.dsOrdCatgCd, NFCL3000CommonLogic.supplyInvoicdOrdCatgCd(bizMsg));
        // END 2019/06/06 S.Takami [QC#50691,ADD]
        pMsg1.xxMsgIdList.clear();

        pMsgHdrList.add(pMsg1);

        /****************************************/
        /* Shipment Parameter                   */
        /****************************************/
        List<NWZC040002PMsg> pMsgBolList = new ArrayList<NWZC040002PMsg>();

        for(int i = 0; i < bizMsg.D.getValidCount(); i++) {
            NWZC040002PMsg pMsg2 = new NWZC040002PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg2.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg2.invNum, manInvNum);
            ZYPEZDItemValueSetter.setValue(pMsg2.invBolLineNum, bizMsg.D.no(i).invBolLineNum_D1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg2.soNum, bizMsg.D.no(i).soNum_D1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg2.carrCd, bizMsg.D.no(i).carrCd_D1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg2.carrNm, bizMsg.D.no(i).carrNm_D1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg2.shipFromInvtyLocCd, bizMsg.D.no(i).shipFromInvtyLocCd_D1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg2.shipToCustCd, bizMsg.D.no(i).shipToCustCd_D1.getValue());
//#6469
//            ZYPEZDItemValueSetter.setValue(pMsg2.dropShipFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(pMsg2.dropShipFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(pMsg2.shipToLocNm, bizMsg.D.no(i).shipToLocNm_D1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg2.shipToAddlLocNm, bizMsg.D.no(i).shipToAddlLocNm_D1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg2.shipToFirstLineAddr, bizMsg.D.no(i).shipToFirstLineAddr_D1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg2.shipToScdLineAddr, bizMsg.D.no(i).shipToScdLineAddr_D1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg2.shipToThirdLineAddr, bizMsg.D.no(i).shipToThirdLineAddr_D1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg2.shipToFrthLineAddr, bizMsg.D.no(i).shipToFrthLineAddr_D1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg2.shipToCtyAddr, bizMsg.D.no(i).shipToCtyAddr_D1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg2.shipToCntyNm, bizMsg.D.no(i).shipToCntyNm_D1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg2.shipToProvNm, bizMsg.D.no(i).shipToProvNm_D1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg2.shipToStCd, bizMsg.D.no(i).shipToStCd_D1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg2.shipToPostCd, bizMsg.D.no(i).shipToPostCd_D1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg2.shipToCtryCd, bizMsg.D.no(i).shipToCtryCd_D1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg2.shipToFirstRefCmntTxt, bizMsg.D.no(i).shipToFirstRefCmntTxt_D1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg2.shipToScdRefCmntTxt, bizMsg.D.no(i).shipToScdRefCmntTxt_D1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg2.exptFlg, ZYPConstant.FLG_OFF_N);
            // START 2016/07/20 S.Yoshida [QC#12149,MOD]
//            // START 2016/07/11 S.Fujita [QC#10160,MOD]
//            ZYPEZDItemValueSetter.setValue(pMsg2.shipDealSlsAmt, bizMsg.D.no(i).shipDealSlsAmt_D1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(pMsg2.shipDealNetAmt, bizMsg.D.no(i).shipDealNetAmt_D1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(pMsg2.shipDealFrtAmt, bizMsg.D.no(i).shipDealFrtAmt_D1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(pMsg2.shipDealDiscAmt, bizMsg.D.no(i).shipDealDiscAmt_D1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(pMsg2.shipDealHdlgChrgAmt, bizMsg.D.no(i).shipDealHdlgChrgAmt_D1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(pMsg2.totBolDealTaxAmt, bizMsg.D.no(i).totBolDealTaxAmt_D1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(pMsg2.totBolFuncTaxAmt, bizMsg.D.no(i).totBolDealTaxAmt_D1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(pMsg2.frtDealTaxAmt, bizMsg.D.no(i).frtDealTaxAmt_D1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(pMsg2.frtFuncTaxAmt, bizMsg.D.no(i).frtDealTaxAmt_D1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(pMsg2.shipFuncSlsAmt, bizMsg.D.no(i).shipDealSlsAmt_D1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(pMsg2.shipFuncNetAmt, bizMsg.D.no(i).shipDealNetAmt_D1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(pMsg2.shipFuncFrtAmt, bizMsg.D.no(i).shipDealFrtAmt_D1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(pMsg2.shipFuncDiscAmt, bizMsg.D.no(i).shipDealDiscAmt_D1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(pMsg2.shipFuncHdlgChrgAmt, bizMsg.D.no(i).shipDealHdlgChrgAmt_D1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            // END   2016/07/11 S.Fujita [QC#10160,MOD]
            ZYPEZDItemValueSetter.setValue(pMsg2.shipDealSlsAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).shipDealSlsAmt_D1));
            ZYPEZDItemValueSetter.setValue(pMsg2.shipDealNetAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).shipDealNetAmt_D1));
            ZYPEZDItemValueSetter.setValue(pMsg2.shipDealFrtAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).shipDealFrtAmt_D1));
            ZYPEZDItemValueSetter.setValue(pMsg2.shipDealDiscAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).shipDealDiscAmt_D1));
            ZYPEZDItemValueSetter.setValue(pMsg2.shipDealHdlgChrgAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).shipDealHdlgChrgAmt_D1));
            ZYPEZDItemValueSetter.setValue(pMsg2.totBolDealTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).totBolDealTaxAmt_D1));
            ZYPEZDItemValueSetter.setValue(pMsg2.totBolFuncTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).totBolDealTaxAmt_D1));
            ZYPEZDItemValueSetter.setValue(pMsg2.frtDealTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).frtDealTaxAmt_D1));
            ZYPEZDItemValueSetter.setValue(pMsg2.frtFuncTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).frtDealTaxAmt_D1));
            ZYPEZDItemValueSetter.setValue(pMsg2.shipFuncSlsAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).shipDealSlsAmt_D1));
            ZYPEZDItemValueSetter.setValue(pMsg2.shipFuncNetAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).shipDealNetAmt_D1));
            ZYPEZDItemValueSetter.setValue(pMsg2.shipFuncFrtAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).shipDealFrtAmt_D1));
            ZYPEZDItemValueSetter.setValue(pMsg2.shipFuncDiscAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).shipDealDiscAmt_D1));
            ZYPEZDItemValueSetter.setValue(pMsg2.shipFuncHdlgChrgAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).shipDealHdlgChrgAmt_D1));
            // END   2016/07/20 S.Yoshida [QC#12149,MOD]
            ZYPEZDItemValueSetter.setValue(pMsg2.frtTaxPct, bizMsg.D.no(i).frtTaxPct_D1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg2.shipDt, bizMsg.D.no(i).shipDt_D1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg2.arvDt, "");
            ZYPEZDItemValueSetter.setValue(pMsg2.shpgSvcLvlCd, bizMsg.D.no(i).shpgSvcLvlCd_D1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg2.frtChrgToCd, "");
            ZYPEZDItemValueSetter.setValue(pMsg2.frtChrgMethCd, "");
            ZYPEZDItemValueSetter.setValue(pMsg2.apvlNum, "");
            ZYPEZDItemValueSetter.setValue(pMsg2.cpoOrdNum, "");
            ZYPEZDItemValueSetter.setValue(pMsg2.custIssPoNum, "");
            ZYPEZDItemValueSetter.setValue(pMsg2.ctacPsnPk, bizMsg.ctacPsnPk_H2.getValue());

            ZYPEZDItemValueSetter.setValue(pMsg2.shipToCustAcctCd, bizMsg.D.no(i).shipToCustAcctCd_D1.getValue());

            pMsg2.xxMsgIdList.clear();
            pMsgBolList.add(pMsg2);
        }

        /****************************************/
        /* Invoice Line Parameter               */
        /****************************************/
        List<NWZC040003PMsg> pMsgLineList = new ArrayList<NWZC040003PMsg>();
        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWZC040003PMsg pMsg3 = new NWZC040003PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg3.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg3.invNum, manInvNum);
            ZYPEZDItemValueSetter.setValue(pMsg3.invBolLineNum, bizMsg.A.no(i).invBolLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg3.invLineNum, bizMsg.A.no(i).invLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg3.invLineSubNum, bizMsg.A.no(i).invLineSubNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg3.invLineSubTrxNum, bizMsg.A.no(i).invLineSubTrxNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg3.stkStsCd, "");

            ZYPEZDItemValueSetter.setValue(pMsg3.slsRepTocCd, bizMsg.slsRepTocCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg3.brCd, "");
            ZYPEZDItemValueSetter.setValue(pMsg3.mdseCd, bizMsg.A.no(i).mdseCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg3.mdseNm, bizMsg.A.no(i).mdseNm_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg3.coaProdCd, "");
            // START 2016/06/03 S.Fujita [QC#9454,MOD]
//            ZYPEZDItemValueSetter.setValue(pMsg3.trxCd, "");
//            ZYPEZDItemValueSetter.setValue(pMsg3.trxRsnCd, "");
            // START 2017/12/25 E.Kameishi [QC#20312,MOD]
            if (isTaxAdj) {
                ZYPEZDItemValueSetter.setValue(pMsg3.trxCd, TRX_TAX_ADJUSTMENT);
                ZYPEZDItemValueSetter.setValue(pMsg3.trxRsnCd, TRX_RSN_TAX_ADJUSTMENT);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg3.trxCd, TRX_MANUAL_INVOICE);
                ZYPEZDItemValueSetter.setValue(pMsg3.trxRsnCd, TRX_RSN_MANUAL_INVOICE);
            }
            // START 2017/12/25 E.Kameishi [QC#20312,MOD]
            // END   2016/06/03 S.Fujita [QC#9454,MOD]
//            ZYPEZDItemValueSetter.setValue(pMsg3.ordQty, );
//            ZYPEZDItemValueSetter.setValue(pMsg3.boQty, );
            ZYPEZDItemValueSetter.setValue(pMsg3.manPrcFlg, ZYPConstant.FLG_OFF_N);

            // START 2016/07/20 S.Yoshida [QC#12149,MOD]
//            // START 2016/07/11 S.Fujita [QC#10160,MOD]
//            ZYPEZDItemValueSetter.setValue(pMsg3.shipQty, bizMsg.A.no(i).shipQty_A1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(pMsg3.dealNetUnitPrcAmt, bizMsg.A.no(i).dealNetUnitPrcAmt_A1.getValue());
//            ZYPEZDItemValueSetter.setValue(pMsg3.invLineDealTaxAmt, bizMsg.A.no(i).invLineDealTaxAmt_A1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(pMsg3.invLineDealNetAmt, bizMsg.A.no(i).invLineDealNetAmt_A1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(pMsg3.dealDiscUnitPrcAmt, BigDecimal.ZERO);
//            ZYPEZDItemValueSetter.setValue(pMsg3.funcNetUnitPrcAmt, bizMsg.A.no(i).dealNetUnitPrcAmt_A1.getValue());
//            ZYPEZDItemValueSetter.setValue(pMsg3.invLineFuncTaxAmt, bizMsg.A.no(i).invLineDealTaxAmt_A1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(pMsg3.invLineFuncNetAmt, bizMsg.A.no(i).invLineDealNetAmt_A1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(pMsg3.funcDiscUnitPrcAmt, BigDecimal.ZERO);
//            // END   2016/07/11 S.Fujita [QC#10160,MOD]
            ZYPEZDItemValueSetter.setValue(pMsg3.shipQty, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).shipQty_A1));
            ZYPEZDItemValueSetter.setValue(pMsg3.dealNetUnitPrcAmt, NFCL3000CommonLogic.getAmount(bizMsg.A.no(i).dealNetUnitPrcAmt_A1));
            // START 2018/03/29 E.Kameishi [QC#24390,MOD]
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxMstChkFlg_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg3.invLineDealTaxAmt, bizMsg.A.no(i).invLineDealTaxAmt_A1);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg3.invLineDealTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).invLineDealTaxAmt_A1));
            }
            ZYPEZDItemValueSetter.setValue(pMsg3.invLineDealNetAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).invLineDealNetAmt_A1));
            ZYPEZDItemValueSetter.setValue(pMsg3.dealDiscUnitPrcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(pMsg3.funcNetUnitPrcAmt, NFCL3000CommonLogic.getAmount(bizMsg.A.no(i).dealNetUnitPrcAmt_A1));

            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxMstChkFlg_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg3.invLineFuncTaxAmt, bizMsg.A.no(i).invLineDealTaxAmt_A1);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg3.invLineFuncTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).invLineDealTaxAmt_A1));
            }
            // END 2018/03/29 E.Kameishi [QC#24390,MOD]
            ZYPEZDItemValueSetter.setValue(pMsg3.invLineFuncNetAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).invLineDealNetAmt_A1));
            ZYPEZDItemValueSetter.setValue(pMsg3.funcDiscUnitPrcAmt, BigDecimal.ZERO);
            // END   2016/07/20 S.Yoshida [QC#12149,MOD]
            // START 2016/06/03 S.Fujita [QC#9454,ADD]
//            ZYPEZDItemValueSetter.setValue(pMsg3.taxFlg, "");
            ZYPEZDItemValueSetter.setValue(pMsg3.taxFlg, ZYPConstant.FLG_ON_Y);
            // END   2016/06/03 S.Fujita [QC#9454,ADD]
            ZYPEZDItemValueSetter.setValue(pMsg3.taxPct, bizMsg.A.no(i).taxPct_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg3.coaAcctCd, "");
            ZYPEZDItemValueSetter.setValue(pMsg3.coaProdCd, "");
            ZYPEZDItemValueSetter.setValue(pMsg3.crDrRsnSubCd, "");
            ZYPEZDItemValueSetter.setValue(pMsg3.setMdseCd, "");
//            ZYPEZDItemValueSetter.setValue(pMsg3.shipCmplCostAmt, "");
            // START 2016/07/20 S.Yoshida [QC#12149,MOD]
//            // START 2016/07/11 S.Fujita [QC#10160,MOD]
//            //---- start 2016/05/03 QC#7444 Discount Amount is $0, so set same amount as net amount.
//            ZYPEZDItemValueSetter.setValue(pMsg3.dealGrsUnitPrcAmt, bizMsg.A.no(i).dealNetUnitPrcAmt_A1.getValue());
//            ZYPEZDItemValueSetter.setValue(pMsg3.dealGrsTotPrcAmt, bizMsg.A.no(i).invLineDealNetAmt_A1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(pMsg3.funcGrsUnitPrcAmt, bizMsg.A.no(i).dealNetUnitPrcAmt_A1.getValue());
//            ZYPEZDItemValueSetter.setValue(pMsg3.funcGrsTotPrcAmt, bizMsg.A.no(i).invLineDealNetAmt_A1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            //---- end 2016/05/03
//            // END   2016/07/11 S.Fujita [QC#10160,MOD]
            ZYPEZDItemValueSetter.setValue(pMsg3.dealGrsUnitPrcAmt, bizMsg.A.no(i).dealNetUnitPrcAmt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg3.dealGrsTotPrcAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).invLineDealNetAmt_A1));
            ZYPEZDItemValueSetter.setValue(pMsg3.funcGrsUnitPrcAmt, bizMsg.A.no(i).dealNetUnitPrcAmt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg3.funcGrsTotPrcAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).invLineDealNetAmt_A1));
            // END   2016/07/20 S.Yoshida [QC#12149,MOD]
            ZYPEZDItemValueSetter.setValue(pMsg3.setRatioKeepFlg, "");
            ZYPEZDItemValueSetter.setValue(pMsg3.univsPrmoId, "");
            ZYPEZDItemValueSetter.setValue(pMsg3.prmoShortNm, "");
            ZYPEZDItemValueSetter.setValue(pMsg3.srcTrxNum, "");
//            ZYPEZDItemValueSetter.setValue(pMsg3.svcConfigMstrPk, "");
            ZYPEZDItemValueSetter.setValue(pMsg3.dsContrNum, "");
            ZYPEZDItemValueSetter.setValue(pMsg3.dsContrSqNum, "");
//            ZYPEZDItemValueSetter.setValue(pMsg3.espacLineShipQty, "");
            ZYPEZDItemValueSetter.setValue(pMsg3.firstBllgAttrbValTxt, "");
            ZYPEZDItemValueSetter.setValue(pMsg3.scdBllgAttrbValTxt, "");
            ZYPEZDItemValueSetter.setValue(pMsg3.thirdBllgAttrbValTxt, "");
            ZYPEZDItemValueSetter.setValue(pMsg3.frthBllgAttrbValTxt, "");
            ZYPEZDItemValueSetter.setValue(pMsg3.fifthBllgAttrbValTxt, "");
            ZYPEZDItemValueSetter.setValue(pMsg3.sixthBllgAttrbValTxt, "");
            ZYPEZDItemValueSetter.setValue(pMsg3.uomCd, bizMsg.A.no(i).pkgUomCd_A1.getValue());
//            ZYPEZDItemValueSetter.setValue(pMsg3.svcInvLinePk, "");
//            ZYPEZDItemValueSetter.setValue(pMsg3.dsContrDtlPk, "");
//            ZYPEZDItemValueSetter.setValue(pMsg3.mdlId, "");
            ZYPEZDItemValueSetter.setValue(pMsg3.svcInvChrgTpCd, "");
            ZYPEZDItemValueSetter.setValue(pMsg3.bllgPerFromDt, "");
            ZYPEZDItemValueSetter.setValue(pMsg3.bllgPerThruDt, "");
//            ZYPEZDItemValueSetter.setValue(pMsg3.bllgCopyQty, "");
            ZYPEZDItemValueSetter.setValue(pMsg3.taxCalcGeoCd, bizMsg.A.no(i).taxCalcGeoCd_A1.getValue());
//            ZYPEZDItemValueSetter.setValue(pMsg3.dsCpoLineNum, "");
//            ZYPEZDItemValueSetter.setValue(pMsg3.dsCpoLineSubNum, "");
            ZYPEZDItemValueSetter.setValue(pMsg3.custIssPoNum, bizMsg.custIssPoNum_H1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg3.custIssPoDt, "");
//            ZYPEZDItemValueSetter.setValue(pMsg3.invDplyQty, "");
            //ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineSubNum, ""); // 2016/10/26 [QC#13639, DEL]
//            ZYPEZDItemValueSetter.setValue(pMsg3.splyPgmShipQty, "");
//            ZYPEZDItemValueSetter.setValue(pMsg3.splyPgmUnitAmtRate, "");

//20160303
//            ZYPEZDItemValueSetter.setValue(pMsg3.invLineFuncTaxAmt, BigDecimal.ZERO);

            // START 2016/11/17 T.Murai [QC#15987, DEL]
            // StART 2016/10/26 T.Murai [QC#13639, ADD]
//            NFCL3000CommonLogic.setLineAndSubLineNum(bizMsg.A.no(i));
//            ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineNum, bizMsg.A.no(i).cpoDtlLineNum_A1.getValue());
//            ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineSubNum, bizMsg.A.no(i).cpoDtlLineSubNum_A1.getValue());
            // END   2016/10/26 T.Murai [QC#13639, ADD]
            // END   2016/11/17 T.Murai [QC#15987, DEL]
            
            // START 2016/08/01 S.Fujita [QC#10148,ADD]
            //ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineNum, bizMsg.A.no(i).cpoDtlLineNum_A1.getValue()); // 2016/10/26 [QC#13639, DEL]
            ZYPEZDItemValueSetter.setValue(pMsg3.dsContrNum, bizMsg.A.no(i).dsContrNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg3.dsContrSqNum, bizMsg.A.no(i).dsContrSqNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg3.svcConfigMstrPk, bizMsg.A.no(i).svcConfigMstrPk_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg3.svcInvChrgTpCd, bizMsg.A.no(i).svcInvChrgTpCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg3.bllgPerFromDt, bizMsg.A.no(i).bllgPerFromDt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg3.bllgPerThruDt, bizMsg.A.no(i).bllgPerThruDt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg3.bllgCopyQty, bizMsg.A.no(i).bllgCopyQty_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg3.ordQty, bizMsg.A.no(i).ordQty_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg3.dsContrDtlPk, bizMsg.A.no(i).dsContrDtlPk_A1.getValue());
            // END   2016/08/01 S.Fujita [QC#10148,ADD]

            // START 2016/10/14 S.Fujita [QC#10281,ADD]
            ZYPEZDItemValueSetter.setValue(pMsg3.firstBllgAttrbValTxt, bizMsg.A.no(i).firstBllgAttrbValTxt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg3.scdBllgAttrbValTxt, bizMsg.A.no(i).scdBllgAttrbValTxt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg3.thirdBllgAttrbValTxt, bizMsg.A.no(i).thirdBllgAttrbValTxt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg3.frthBllgAttrbValTxt, bizMsg.A.no(i).frthBllgAttrbValTxt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg3.fifthBllgAttrbValTxt, bizMsg.A.no(i).fifthBllgAttrbValTxt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg3.sixthBllgAttrbValTxt, bizMsg.A.no(i).sixthBllgAttrbValTxt_A1.getValue());
            // END   2016/10/14 S.Fujita [QC#10281,ADD]

            // START 2018/05/22 Y.Matsui [QC#21841,ADD]
            ZYPEZDItemValueSetter.setValue(pMsg3.invLineCatgCd, bizMsg.A.no(i).invLineCatgCd_A1.getValue());
            // END   2018/05/22 Y.Matsui [QC#21841,ADD]

            // START 2018/09/28 T.Ogura [QC#28526,ADD]
            ZYPEZDItemValueSetter.setValue(pMsg3.ordCustUomQty, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).ordCustUomQty_A1));
            // END   2018/09/28 T.Ogura [QC#28526,ADD]

            // START 2019/04/25 S.Takami [QC#50078,ADD]
            boolean isSetInvDplyQty = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, bizMsg.A.no(i).xxDplyCtrlFlg_A1.getValue());
            isSetInvDplyQty |= NFCL3000CommonLogic.isDsInvTpFsr(bizMsg);
            if (isSetInvDplyQty) {
                ZYPEZDItemValueSetter.setValue(pMsg3.invDplyQty, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).invDplyQty_A1));
            } else {
                pMsg3.invDplyQty.clear();
            }
            // END 2019/04/25 S.Takami [QC#50078,ADD]

            // START 2018/10/24 S.Takami [QC#27069, Add]
            ZYPEZDItemValueSetter.setValue(pMsg3.svcMachMstrPk, bizMsg.A.no(i).svcMachMstrPk_A1);
            ZYPEZDItemValueSetter.setValue(pMsg3.mdlId, bizMsg.A.no(i).mdlId_A1);
            // END   2018/10/24 S.Takami [QC#27069, Add]
            pMsg3.xxMsgIdList.clear();

            pMsgLineList.add(pMsg3);
        }

        /****************************************/
        /* Invoice Sales Credit                 */
        /****************************************/
        List<NWZC040007PMsg> pMsgSlsCrList = new ArrayList<NWZC040007PMsg>();
        for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWZC040007PMsg pMsg7 = new NWZC040007PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg7.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg7.invNum, manInvNum);
            ZYPEZDItemValueSetter.setValue(pMsg7.invBolLineNum, bizMsg.B.no(i).invBolLineNum_B1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg7.invLineNum, bizMsg.B.no(i).invLineNum_B1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg7.invLineSubNum, bizMsg.B.no(i).invLineSubNum_B1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg7.invLineSubTrxNum, bizMsg.B.no(i).invTrxLineSubNum_B1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg7.mdseCd, bizMsg.B.no(i).mdseCd_B1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg7.invLineSplTpCd, bizMsg.B.no(i).invLineSplTpCd_B1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg7.invLineSplPct, bizMsg.B.no(i).invLineSplPct_B2.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg7.slsRepTocCd, bizMsg.B.no(i).slsRepTocCd_B1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg7.slsRepCrPct, bizMsg.B.no(i).slsRepCrPct_B2.getValue());
            // START 2016/07/20 S.Yoshida [QC#12149,MOD]
//            // START 2016/07/11 S.Fujita [QC#10160,MOD]
//            ZYPEZDItemValueSetter.setValue(pMsg7.dealSlsCrAmt, bizMsg.B.no(i).dealSlsCrAmt_B1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(pMsg7.funcSlsCrAmt, bizMsg.B.no(i).dealSlsCrAmt_B1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            // END   2016/07/11 S.Fujita [QC#10160,MOD]
            ZYPEZDItemValueSetter.setValue(pMsg7.dealSlsCrAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.B.no(i).dealSlsCrAmt_B1));
            ZYPEZDItemValueSetter.setValue(pMsg7.funcSlsCrAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.B.no(i).dealSlsCrAmt_B1));
            // END   2016/07/20 S.Yoshida [QC#12149,MOD]
            ZYPEZDItemValueSetter.setValue(pMsg7.dealCcyCd, bizMsg.stdCcyCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg7.dfrdAcctgRuleCd, bizMsg.B.no(i).dfrdAcctgRuleCd_B1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg7.dfrdAcctgRuleDurnAot, bizMsg.B.no(i).dfrdAcctgRuleDurnAot_B1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg7.slsRepBrCd, "");
            ZYPEZDItemValueSetter.setValue(pMsg7.coaCmpyCd, "");
            ZYPEZDItemValueSetter.setValue(pMsg7.coaBrCd, "");
            ZYPEZDItemValueSetter.setValue(pMsg7.coaCcCd, "");
            ZYPEZDItemValueSetter.setValue(pMsg7.coaAcctCd, "");
            ZYPEZDItemValueSetter.setValue(pMsg7.coaProdCd, "");
            ZYPEZDItemValueSetter.setValue(pMsg7.coaChCd, "");
            ZYPEZDItemValueSetter.setValue(pMsg7.coaAfflCd, "");
            ZYPEZDItemValueSetter.setValue(pMsg7.coaProjCd, "");
            ZYPEZDItemValueSetter.setValue(pMsg7.coaExtnCd, "");
            // START 2017/12/25 E.Kameishi [QC#20312,MOD]
            if (isTaxAdj) {
                ZYPEZDItemValueSetter.setValue(pMsg7.trxCd, TRX_TAX_ADJUSTMENT);
                ZYPEZDItemValueSetter.setValue(pMsg7.trxRsnCd, TRX_RSN_TAX_ADJUSTMENT);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg7.trxCd, TRX.SALES);
                ZYPEZDItemValueSetter.setValue(pMsg7.trxRsnCd, TRX_RSN_MANUAL_INVOICE);
            }
            // END 2017/12/25 E.Kameishi [QC#20312,MOD]
            pMsg7.xxMsgIdList.clear();

            pMsgSlsCrList.add(pMsg7);
        }

        /****************************************/
        /* Invoice Line TAX Detail              */
        /****************************************/
        List<NWZC040005PMsg> pMsgTaxList = new ArrayList<NWZC040005PMsg>();
        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWZC040005PMsg pMsg5 = new NWZC040005PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg5.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg5.invNum, manInvNum);
            ZYPEZDItemValueSetter.setValue(pMsg5.invBolLineNum, bizMsg.A.no(i).invBolLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg5.invLineNum, bizMsg.A.no(i).invLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg5.invLineSubNum, bizMsg.A.no(i).invLineSubNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg5.invTrxLineSubNum, bizMsg.A.no(i).invLineSubTrxNum_A1.getValue());
            // START 2018/06/01 Y.Matsui [QC#21841,MOD]
            if (NFCL3000CommonLogic.isFreightInvoiceLine(bizMsg,  bizMsg.A.no(i))) {
                ZYPEZDItemValueSetter.setValue(pMsg5.dsSlsTaxTpCd, DS_SLS_TAX_TP.FREIGHT_STATE_TAX);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg5.dsSlsTaxTpCd, bizMsg.A.no(i).dsSlsTaxTpCd_A1.getValue());
            }
            // END   2018/06/01 Y.Matsui [QC#21841,MOD]
            // START 2016/07/19 S.Yoshida [QC#12006,MOD]
//            // START 2016/07/11 S.Fujita [QC#10160,MOD]
//            ZYPEZDItemValueSetter.setValue(pMsg5.funcSlsTaxAmt, bizMsg.A.no(i).dealSlsTaxAmt_A1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(pMsg5.dealSlsTaxAmt, bizMsg.A.no(i).dealSlsTaxAmt_A1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            // END   2016/07/11 S.Fujita [QC#10160,MOD]
            ZYPEZDItemValueSetter.setValue(pMsg5.funcSlsTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).dealSlsTaxAmt_A1));
            ZYPEZDItemValueSetter.setValue(pMsg5.dealSlsTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).dealSlsTaxAmt_A1));
            // END   2016/07/19 S.Yoshida [QC#12006,MOD]
            ZYPEZDItemValueSetter.setValue(pMsg5.slsTaxPct, bizMsg.A.no(i).slsTaxPct_A1.getValue());

            // START 2017/03/09 T.Murai [QC#17901,ADD]
            ZYPEZDItemValueSetter.setValue(pMsg5.taxAreaId, bizMsg.A.no(i).taxAreaId_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg5.taxRsltDescTxt, bizMsg.A.no(i).taxRsltDescTxt_A1.getValue());
            // END   2017/03/09 T.Murai [QC#17901,ADD]
            // START 2016/07/29 S.Fujita [QC#12503,MOD]
            if(ZYPCommonFunc.hasValue(pMsg5.funcSlsTaxAmt) && ZYPCommonFunc.hasValue(pMsg5.slsTaxPct) && pMsg5.funcSlsTaxAmt.getValue().compareTo(BigDecimal.ZERO) != 0) {
                pMsgTaxList.add(pMsg5);
            }
            // END   2016/07/29 S.Fujita [QC#12503,MOD]

            NWZC040005PMsg pMsg5_1 = new NWZC040005PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg5_1.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg5_1.invNum, manInvNum);
            ZYPEZDItemValueSetter.setValue(pMsg5_1.invBolLineNum, bizMsg.A.no(i).invBolLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg5_1.invLineNum, bizMsg.A.no(i).invLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg5_1.invLineSubNum, bizMsg.A.no(i).invLineSubNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg5_1.invTrxLineSubNum, bizMsg.A.no(i).invLineSubTrxNum_A1.getValue());
            // START 2018/06/01 Y.Matsui [QC#21841,MOD]
            if (NFCL3000CommonLogic.isFreightInvoiceLine(bizMsg,  bizMsg.A.no(i))) {
                ZYPEZDItemValueSetter.setValue(pMsg5_1.dsSlsTaxTpCd, DS_SLS_TAX_TP.FREIGHT_COUNTY_TAX);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg5_1.dsSlsTaxTpCd, bizMsg.A.no(i).dsSlsTaxTpCd_A2.getValue());
            }
            // END   2018/06/01 Y.Matsui [QC#21841,MOD]
            // START 2016/07/19 S.Yoshida [QC#12006,MOD]
//            // START 2016/07/11 S.Fujita [QC#10160,MOD]
//            ZYPEZDItemValueSetter.setValue(pMsg5_1.funcSlsTaxAmt, bizMsg.A.no(i).dealSlsTaxAmt_A2.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(pMsg5_1.dealSlsTaxAmt, bizMsg.A.no(i).dealSlsTaxAmt_A2.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            // END   2016/07/11 S.Fujita [QC#10160,MOD]
            ZYPEZDItemValueSetter.setValue(pMsg5_1.funcSlsTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).dealSlsTaxAmt_A2));
            ZYPEZDItemValueSetter.setValue(pMsg5_1.dealSlsTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).dealSlsTaxAmt_A2));
            // END   2016/07/19 S.Yoshida [QC#12006,MOD]
            ZYPEZDItemValueSetter.setValue(pMsg5_1.slsTaxPct, bizMsg.A.no(i).slsTaxPct_A2.getValue());

            // START 2017/03/09 T.Murai [QC#17901,ADD]
            ZYPEZDItemValueSetter.setValue(pMsg5_1.taxAreaId, bizMsg.A.no(i).taxAreaId_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg5_1.taxRsltDescTxt, bizMsg.A.no(i).taxRsltDescTxt_A2.getValue());
            // END   2017/03/09 T.Murai [QC#17901,ADD]
            // START 2016/07/29 S.Fujita [QC#12503,MOD]
            if(ZYPCommonFunc.hasValue(pMsg5_1.funcSlsTaxAmt) && ZYPCommonFunc.hasValue(pMsg5_1.slsTaxPct) && pMsg5_1.funcSlsTaxAmt.getValue().compareTo(BigDecimal.ZERO) != 0) {
                pMsgTaxList.add(pMsg5_1);
            }
            // END   2016/07/29 S.Fujita [QC#12503,MOD]

            NWZC040005PMsg pMsg5_2 = new NWZC040005PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg5_2.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg5_2.invNum, manInvNum);
            ZYPEZDItemValueSetter.setValue(pMsg5_2.invBolLineNum, bizMsg.A.no(i).invBolLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg5_2.invLineNum, bizMsg.A.no(i).invLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg5_2.invLineSubNum, bizMsg.A.no(i).invLineSubNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg5_2.invTrxLineSubNum, bizMsg.A.no(i).invLineSubTrxNum_A1.getValue());
            // START 2018/06/01 Y.Matsui [QC#21841,MOD]
            if (NFCL3000CommonLogic.isFreightInvoiceLine(bizMsg,  bizMsg.A.no(i))) {
                ZYPEZDItemValueSetter.setValue(pMsg5_2.dsSlsTaxTpCd, DS_SLS_TAX_TP.FREIGHT_CITY_TAX);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg5_2.dsSlsTaxTpCd, bizMsg.A.no(i).dsSlsTaxTpCd_A3.getValue());
            }
            // END   2018/06/01 Y.Matsui [QC#21841,MOD]
            // START 2016/07/19 S.Yoshida [QC#12006,MOD]
//            // START 2016/07/11 S.Fujita [QC#10160,MOD]
//            ZYPEZDItemValueSetter.setValue(pMsg5_2.funcSlsTaxAmt, bizMsg.A.no(i).dealSlsTaxAmt_A3.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(pMsg5_2.dealSlsTaxAmt, bizMsg.A.no(i).dealSlsTaxAmt_A3.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            // END   2016/07/11 S.Fujita [QC#10160,MOD]
            ZYPEZDItemValueSetter.setValue(pMsg5_2.funcSlsTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).dealSlsTaxAmt_A3));
            ZYPEZDItemValueSetter.setValue(pMsg5_2.dealSlsTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).dealSlsTaxAmt_A3));
            // END   2016/07/19 S.Yoshida [QC#12006,MOD]
            ZYPEZDItemValueSetter.setValue(pMsg5_2.slsTaxPct, bizMsg.A.no(i).slsTaxPct_A3.getValue());

            // START 2017/03/09 T.Murai [QC#17901,ADD]
            ZYPEZDItemValueSetter.setValue(pMsg5_2.taxAreaId, bizMsg.A.no(i).taxAreaId_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg5_2.taxRsltDescTxt, bizMsg.A.no(i).taxRsltDescTxt_A3.getValue());
            // END   2017/03/09 T.Murai [QC#17901,ADD]
            // START 2016/07/29 S.Fujita [QC#12503,MOD]
            if(ZYPCommonFunc.hasValue(pMsg5_2.funcSlsTaxAmt) && ZYPCommonFunc.hasValue(pMsg5_2.slsTaxPct) && pMsg5_2.funcSlsTaxAmt.getValue().compareTo(BigDecimal.ZERO) != 0) {
                pMsgTaxList.add(pMsg5_2);
            }
            // END   2016/07/29 S.Fujita [QC#12503,MOD]
        }

// START 2018/05/22 Y.Matsui [QC#21841,DEL]
//        for(int i = 0; i < bizMsg.D.getValidCount(); i++) {
//            NWZC040005PMsg pMsg5 = new NWZC040005PMsg();
//            ZYPEZDItemValueSetter.setValue(pMsg5.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
//            ZYPEZDItemValueSetter.setValue(pMsg5.invNum, manInvNum);
//            ZYPEZDItemValueSetter.setValue(pMsg5.invBolLineNum, bizMsg.D.no(i).invBolLineNum_D1.getValue());
//            ZYPEZDItemValueSetter.setValue(pMsg5.invLineNum, FREIGHT_TAX_LINE_NUM);
//            ZYPEZDItemValueSetter.setValue(pMsg5.invLineSubNum, FREIGHT_TAX_LINE_NUM);
//            ZYPEZDItemValueSetter.setValue(pMsg5.invTrxLineSubNum, FREIGHT_TAX_LINE_NUM);
//            ZYPEZDItemValueSetter.setValue(pMsg5.dsSlsTaxTpCd, bizMsg.D.no(i).dsSlsTaxTpCd_D1.getValue());
//            // START 2016/07/19 S.Yoshida [QC#12006,MOD]
////            // START 2016/07/11 S.Fujita [QC#10160,MOD]
////            ZYPEZDItemValueSetter.setValue(pMsg5.funcSlsTaxAmt, bizMsg.D.no(i).dealSlsTaxAmt_D1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
////            ZYPEZDItemValueSetter.setValue(pMsg5.dealSlsTaxAmt, bizMsg.D.no(i).dealSlsTaxAmt_D1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
////            // END   2016/07/11 S.Fujita [QC#10160,MOD]
//            ZYPEZDItemValueSetter.setValue(pMsg5.funcSlsTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).dealSlsTaxAmt_D1));
//            ZYPEZDItemValueSetter.setValue(pMsg5.dealSlsTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).dealSlsTaxAmt_D1));
//            // END   2016/07/19 S.Yoshida [QC#12006,MOD]
//            ZYPEZDItemValueSetter.setValue(pMsg5.slsTaxPct, bizMsg.D.no(i).slsTaxPct_D1.getValue());
//
//            // START 2017/03/09 T.Murai [QC#17901,ADD]
//            ZYPEZDItemValueSetter.setValue(pMsg5.taxAreaId, bizMsg.D.no(i).taxAreaId_D1.getValue());
//            ZYPEZDItemValueSetter.setValue(pMsg5.taxRsltDescTxt, bizMsg.D.no(i).taxRsltDescTxt_D1.getValue());
//            // END   2017/03/09 T.Murai [QC#17901,ADD]
//            // START 2016/07/29 S.Fujita [QC#12503,MOD]
//            if(ZYPCommonFunc.hasValue(pMsg5.funcSlsTaxAmt) && ZYPCommonFunc.hasValue(pMsg5.slsTaxPct) && pMsg5.funcSlsTaxAmt.getValue().compareTo(BigDecimal.ZERO) != 0) {
//                pMsgTaxList.add(pMsg5);
//            }
//            // END   2016/07/29 S.Fujita [QC#12503,MOD]
//
//            NWZC040005PMsg pMsg5_1 = new NWZC040005PMsg();
//            ZYPEZDItemValueSetter.setValue(pMsg5_1.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
//            ZYPEZDItemValueSetter.setValue(pMsg5_1.invNum, manInvNum);
//            ZYPEZDItemValueSetter.setValue(pMsg5_1.invBolLineNum, bizMsg.D.no(i).invBolLineNum_D1.getValue());
//            ZYPEZDItemValueSetter.setValue(pMsg5_1.invLineNum, FREIGHT_TAX_LINE_NUM);
//            ZYPEZDItemValueSetter.setValue(pMsg5_1.invLineSubNum, FREIGHT_TAX_LINE_NUM);
//            ZYPEZDItemValueSetter.setValue(pMsg5_1.invTrxLineSubNum, FREIGHT_TAX_LINE_NUM);
//            ZYPEZDItemValueSetter.setValue(pMsg5_1.dsSlsTaxTpCd, bizMsg.D.no(i).dsSlsTaxTpCd_D2.getValue());
//            // START 2016/07/19 S.Yoshida [QC#12006,MOD]
////            // START 2016/07/11 S.Fujita [QC#10160,MOD]
////            ZYPEZDItemValueSetter.setValue(pMsg5_1.funcSlsTaxAmt, bizMsg.D.no(i).dealSlsTaxAmt_D2.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
////            ZYPEZDItemValueSetter.setValue(pMsg5_1.dealSlsTaxAmt, bizMsg.D.no(i).dealSlsTaxAmt_D2.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
////            // END   2016/07/11 S.Fujita [QC#10160,MOD]
//            ZYPEZDItemValueSetter.setValue(pMsg5_1.funcSlsTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).dealSlsTaxAmt_D2));
//            ZYPEZDItemValueSetter.setValue(pMsg5_1.dealSlsTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).dealSlsTaxAmt_D2));
//            // END   2016/07/19 S.Yoshida [QC#12006,MOD]
//            ZYPEZDItemValueSetter.setValue(pMsg5_1.slsTaxPct, bizMsg.D.no(i).slsTaxPct_D2.getValue());
//
//            // START 2017/03/09 T.Murai [QC#17901,ADD]
//            ZYPEZDItemValueSetter.setValue(pMsg5_1.taxAreaId, bizMsg.D.no(i).taxAreaId_D1.getValue());
//            ZYPEZDItemValueSetter.setValue(pMsg5_1.taxRsltDescTxt, bizMsg.D.no(i).taxRsltDescTxt_D2.getValue());
//            // END   2017/03/09 T.Murai [QC#17901,ADD]
//            // START 2016/07/29 S.Fujita [QC#12503,MOD]
//            if(ZYPCommonFunc.hasValue(pMsg5_1.funcSlsTaxAmt) && ZYPCommonFunc.hasValue(pMsg5_1.slsTaxPct) && pMsg5_1.funcSlsTaxAmt.getValue().compareTo(BigDecimal.ZERO) != 0) {
//                pMsgTaxList.add(pMsg5_1);
//            }
//            // END   2016/07/29 S.Fujita [QC#12503,MOD]
//
//            NWZC040005PMsg pMsg5_2 = new NWZC040005PMsg();
//            ZYPEZDItemValueSetter.setValue(pMsg5_2.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
//            ZYPEZDItemValueSetter.setValue(pMsg5_2.invNum, manInvNum);
//            ZYPEZDItemValueSetter.setValue(pMsg5_2.invBolLineNum, bizMsg.D.no(i).invBolLineNum_D1.getValue());
//            ZYPEZDItemValueSetter.setValue(pMsg5_2.invLineNum, "*");
//            ZYPEZDItemValueSetter.setValue(pMsg5_2.invLineSubNum, "*");
//            ZYPEZDItemValueSetter.setValue(pMsg5_2.invTrxLineSubNum, "*");
//            ZYPEZDItemValueSetter.setValue(pMsg5_2.dsSlsTaxTpCd, bizMsg.D.no(i).dsSlsTaxTpCd_D3.getValue());
//            // START 2016/07/19 S.Yoshida [QC#12006,MOD]
////            // START 2016/07/11 S.Fujita [QC#10160,MOD]
////            ZYPEZDItemValueSetter.setValue(pMsg5_2.funcSlsTaxAmt, bizMsg.D.no(i).dealSlsTaxAmt_D3.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
////            ZYPEZDItemValueSetter.setValue(pMsg5_2.dealSlsTaxAmt, bizMsg.D.no(i).dealSlsTaxAmt_D3.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
////            // END   2016/07/11 S.Fujita [QC#10160,MOD]
//            ZYPEZDItemValueSetter.setValue(pMsg5_2.funcSlsTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).dealSlsTaxAmt_D3));
//            ZYPEZDItemValueSetter.setValue(pMsg5_2.dealSlsTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).dealSlsTaxAmt_D3));
//            // END   2016/07/19 S.Yoshida [QC#12006,MOD]
//            ZYPEZDItemValueSetter.setValue(pMsg5_2.slsTaxPct, bizMsg.D.no(i).slsTaxPct_D3.getValue());
//
//            // START 2017/03/09 T.Murai [QC#17901,ADD]
//            ZYPEZDItemValueSetter.setValue(pMsg5_2.taxAreaId, bizMsg.D.no(i).taxAreaId_D1.getValue());
//            ZYPEZDItemValueSetter.setValue(pMsg5_2.taxRsltDescTxt, bizMsg.D.no(i).taxRsltDescTxt_D3.getValue());
//            // END   2017/03/09 T.Murai [QC#17901,ADD]
//            // START 2016/07/29 S.Fujita [QC#12503,MOD]
//            if(ZYPCommonFunc.hasValue(pMsg5_2.funcSlsTaxAmt) && ZYPCommonFunc.hasValue(pMsg5_2.slsTaxPct) && pMsg5_2.funcSlsTaxAmt.getValue().compareTo(BigDecimal.ZERO) != 0) {
//                pMsgTaxList.add(pMsg5_2);
//            }
//            // END   2016/07/29 S.Fujita [QC#12503,MOD]
//        }
// END   2018/05/22 Y.Matsui [QC#21841,DEL]

        /****************************************/
        /* Invoice Line Serial Parameter        */
        /****************************************/
        List<NWZC040006PMsg> pMsgLineSerList = new ArrayList<NWZC040006PMsg>();
        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWZC040006PMsg pMsg6 = new NWZC040006PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg6.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg6.invNum, manInvNum);
            ZYPEZDItemValueSetter.setValue(pMsg6.invBolLineNum, bizMsg.A.no(i).invBolLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg6.invLineNum, bizMsg.A.no(i).invLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg6.invLineSubNum, bizMsg.A.no(i).invLineSubNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg6.invTrxLineSubNum, bizMsg.A.no(i).invLineSubTrxNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg6.serNum, ""); // 2018/10/24 S.Takami [QC#27069, Attention] No need to setup Serial#!
            pMsgLineSerList.add(pMsg6);
        }

        /****************************************/
        /* Invoice Import API                   */
        /****************************************/
        NWZC040001 imptInvApi = new NWZC040001();
        // START 2016/06/20 S.Fujita [QC#9454,DEL] Delete Serial
        //imptInvApi.execute(pMsgHdrList, pMsgBolList, pMsgLineList, null, pMsgTaxList, pMsgLineSerList, pMsgSlsCrList, ONBATCH_TYPE.ONLINE);
        imptInvApi.execute(pMsgHdrList, pMsgBolList, pMsgLineList, null, pMsgTaxList, null, pMsgSlsCrList, ONBATCH_TYPE.ONLINE);
        // END   2016/06/20 S.Fujita [QC#9454,DEL]

        /****************************************/
        /* Invoice Import API Error             */
        /****************************************/
        // Header
        for(NWZC040001PMsg pMsgHdr : pMsgHdrList ) {
            if(pMsgHdr.xxMsgIdList.getValidCount() > 0) {
                for(int i = 0; i < pMsgHdr.xxMsgIdList.getValidCount(); i++) {
                    String errId = pMsgHdr.xxMsgIdList.no(i).xxMsgId.getValue();
                    bizMsg.setMessageInfo(errId);
                    return false;
                }
            }
        }
        // BOL
        for(NWZC040002PMsg pMsgBol : pMsgBolList ) {
            if(pMsgBol.xxMsgIdList.getValidCount() > 0) {
                for(int i = 0; i < pMsgBol.xxMsgIdList.getValidCount(); i++) {
                    String errId = pMsgBol.xxMsgIdList.no(i).xxMsgId.getValue();
                    bizMsg.setMessageInfo(errId);
                    bizMsg.xxDplyTab.setValue(TAB_BOL);
                    return false;
                }
            }
        }
        // Line
        for(NWZC040003PMsg pMsgLine : pMsgLineList ) {
            if(pMsgLine.xxMsgIdList.getValidCount() > 0) {
                for(int i = 0; i < pMsgLine.xxMsgIdList.getValidCount(); i++) {
                    String errId = pMsgLine.xxMsgIdList.no(i).xxMsgId.getValue();
                    bizMsg.setMessageInfo(errId);
                    bizMsg.xxDplyTab.setValue(TAB_Line);
                    return false;
                }
            }
        }
        // Sales Credit
        for(NWZC040007PMsg pMsgSlsCr : pMsgSlsCrList ) {
            if(pMsgSlsCr.xxMsgIdList.getValidCount() > 0) {
                for(int i = 0; i < pMsgSlsCr.xxMsgIdList.getValidCount(); i++) {
                    String errId = pMsgSlsCr.xxMsgIdList.no(i).xxMsgId.getValue();
                    bizMsg.setMessageInfo(errId);
                    bizMsg.xxDplyTab.setValue(TAB_SalesCredit);
                    return false;
                }
            }
        }
        // TAX
        for(NWZC040005PMsg pMsgTax : pMsgTaxList ) {
            if(pMsgTax.xxMsgIdList.getValidCount() > 0) {
                for(int i = 0; i < pMsgTax.xxMsgIdList.getValidCount(); i++) {
                    String errId = pMsgTax.xxMsgIdList.no(i).xxMsgId.getValue();
                    bizMsg.setMessageInfo(errId);
                    bizMsg.xxDplyTab.setValue(TAB_Line);
                    return false;
                }
            }
        }

        // Invoice Number
        String invNum = pMsgHdrList.get(0).invNum.getValue();
        bizMsg.invNum_H1.setValue(invNum);
        bizMsg.setMessageInfo("NZZM0002I");

        EZDConnectionMgr.getInstance().commit();

        // START 2016/08/01 S.Fujita [QC#10148,MOD]
        // Update INV
        if(!NFCL3000CommonLogic.updateDsInv(bizMsg)) {
            return false;
        }
        // Update INV_LINE
        if(!NFCL3000CommonLogic.updateInvLine(bizMsg)) {
            return false;
        }

        // END   2016/08/01 S.Fujita [QC#10148,MOD]
        // START 2016/05/24 S.Fujita [QC#8522,ADD]
        // Update INV_BOL
        if(!NFCL3000CommonLogic.updateInvBol(bizMsg)) {
            return false;
        } 
        // Update Sales Credit
        if(!NFCL3000CommonLogic.updateSalseCredit(bizMsg)) {
            return false;
        }
        // END   2016/05/24 S.Fujita [QC#8522,ADD]
        // Update Invoice Status
        if(!NFCL3000CommonLogic.updateInvoiceStatus(bizMsg)) {
            return false;
        }

        // START 2016/08/03 S.Fujita [QC#12864,DEL]
        // Update AJE Invoice Distribution Status
//        if(!NFCL3000CommonLogic.updateAjeInvAcctDist(bizMsg, globalMsg)) {
//            return false;
//        }
        // END   2016/08/03 S.Fujita [QC#12864,DEL]

        // Update Credit Profile
        if(NFCL3000CommonLogic.isFinalize(bizMsg)) {
            if(!updateCreditProfile(cMsg, sMsg)) {
                return false;
            }
        }
        EZDConnectionMgr.getInstance().commit();

        return true;
    }

    /**
     * updateInvoice
     * @param cMsg
     * @param sMsg
     */
    private boolean updateInvoice(EZDCMsg cMsg, EZDSMsg sMsg) {

        // START 2016/06/20 S.Fujita [QC#9454,ADD]
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        NFCL3000SMsg globalMsg = (NFCL3000SMsg) sMsg;

        // Update INV
        if(!NFCL3000CommonLogic.updateInvoice_INV(cMsg, sMsg)) {
            return false;
        }

        if(!bizMsg.fnlzInvFlg_H4.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            if(bizMsg.sysSrcCd_H1.getValue().equals(SYS_SRC.S21_ACCOUNTING_AR)) {
                // Update INV_BOL
                if(!NFCL3000CommonLogic.updateInvoice_INV_BOL(cMsg, sMsg)) {
                    return false;
                }
                // Update INV_LINE
                if(!NFCL3000CommonLogic.updateInvoice_INV_LINE(cMsg, sMsg)) {
                    return false;
                }
                // Update INV_PRMO_INFO
                if(!NFCL3000CommonLogic.updateInvoice_INV_PRMO_INFO(cMsg, sMsg)) {
                    return false;
                }
                // Update DS_INV_LINE_TAX_DTL
                if(!NFCL3000CommonLogic.updateInvoice_DS_INV_LINE_TAX_DTL(cMsg, sMsg)) {
                    return false;
                }
            }
            // Update DS_INV_SLS_CR
            if(!NFCL3000CommonLogic.updateInvoice_DS_INV_SLS_CR(cMsg, sMsg)) {
                return false;
            }
            // END   2016/06/20 S.Fujita [QC#9454,ADD]

            // START 2016/07/20 S.Fujita [QC#10148,ADD]
            NFCL3000CommonLogic.copyEditCtoF(bizMsg, globalMsg);
            // END   2016/07/20 S.Fujita [QC#10148,ADD]

            try {
                // Update AJE_INV_ACCT_DIST
                if(!updateInvoice_ACCT_DIST(cMsg, sMsg)) {
                    return false;
                }
            } catch (SQLException ex){
                bizMsg.setMessageInfo("NFCM0624E");
            }

            // START 2016/11/22 S.Fujita [QC#16154,ADD]
            // Update AJE_LINE_IDX_NUM in AJE_INV_ACCT_DIST
            if(!NFCL3000CommonLogic.updateInvoice_ACCT_DISTWithIdxNum(bizMsg, globalMsg)) {
                return false;
            }
            // END   2016/11/22 S.Fujita [QC#16154,MOD]

            // Update Credit Profile
            if(!ZYPCommonFunc.hasValue(bizMsg.sysSrcCd_H1.getValue()) || bizMsg.sysSrcCd_H1.getValue().equals(SYS_SRC.S21_ACCOUNTING_AR)) {
                if(ZYPCommonFunc.hasValue(bizMsg.xxChkBox_H3.getValue())) {
                    if(!updateCreditProfile(cMsg, sMsg)) {
                        return false;
                    }
                }
            }
        // START 2016/09/26 S.Fujita [QC#13362,ADD]
        } else {
            // Copy 9 segment of CMsg to FMsg For Deferred
            NFCL3000CommonLogic.copyEditCtoFForDeferred(bizMsg, globalMsg);

            // remove AJE_INV_ACCT_DIST_ERR
            if(!NFCL3000CommonLogic.removeAjeInvAcctDistErr(bizMsg)) {
                return false;
            }

            // Update AJE_INV_ACCT_DIST For Deferred
            if(!NFCL3000CommonLogic.updateInvoice_ACCT_DISTForDeferred(bizMsg, globalMsg)) {
                return false;
            }
        // END   2016/09/26 S.Fujita [QC#13362,ADD]
        }
        return true;
    }

    /**
     * updateInvoice_ACCT_DIST
     * @param cMsg
     * @param sMsg
     * @return
     */
    private boolean updateInvoice_ACCT_DIST(EZDCMsg cMsg, EZDSMsg sMsg) throws SQLException {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        NFCL3000SMsg globalMsg = (NFCL3000SMsg) sMsg;

        // START 2016/05/20 S.Fujita [QC#8550,MOD]
        // Check 9 Segment
//        if(!NFCL3000CommonLogic.check9Segment(bizMsg)) {
//            return false;
//        }
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            if (!NFCL3000CommonLogic.check9Segment_idx(bizMsg, bizMsg.C.no(i))) {
                return false;
            }
        }
        // END 2016/05/20 S.Fujita [QC#8550,MOD]

        // START 2016/07/20 S.Fujita [QC#10148,MOD]
        // Delete AJE_INV_ACCT_DIST
//        for(int i = 0; i < globalMsg.E.getValidCount(); i++) {
//            AJE_INV_ACCT_DISTTMsg inMsg = new AJE_INV_ACCT_DISTTMsg();
//            inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
//            inMsg.ajeInvAcctDistPk.setValue(globalMsg.E.no(i).ajeInvAcctDistPk_E1.getValue());
//            AJE_INV_ACCT_DISTTMsg remMsg = (AJE_INV_ACCT_DISTTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(remMsg.getReturnCode())) {
//                bizMsg.setMessageInfo("NFCM0041E");
//                return false;
//            }
            // START 2016/05/10 S.Fujita [QC#6961,MOD]  
//            EZDTBLAccessor.logicalRemove(remMsg);
//            EZDTBLAccessor.remove(remMsg);
            // END 2016/05/10 S.Fujita [QC#6961,MOD]
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(remMsg.getReturnCode())) {
//                bizMsg.setMessageInfo("NFCM0041E");
//                return false;
//            }
//        }
//        globalMsg.E.clear();
//        globalMsg.E.setValidCount(0);

        /************************************************/
        /* remove AJE_INV_ACCT_DIST                     */
        /************************************************/
        if(!NFCL3000CommonLogic.removeAjeInvAcctDist(bizMsg)) {
            return false;
        }
        /************************************************/
        /* remove AJE_INV_ACCT_DIST_ERR                 */
        /************************************************/
        if(!NFCL3000CommonLogic.removeAjeInvAcctDistErr(bizMsg)) {
            return false;
        }
        // END   2016/07/20 S.Fujita [QC#10148,MOD]

        // START 2016/07/20 S.Fujita [QC#10148,DEL]
        // Update AJE_INV_ACCT_DIST
//        int ajeLineIdxNum = 0;
//        int ajeInvAcctDistSqNum = 0;
//        if(globalMsg.F.getValidCount() > 0 ) {
//            String numStr = globalMsg.F.no(0).ajeInvAcctDistSqNum_F1.getValue();
//            String numStr2 = globalMsg.F.no(0).ajeLineIdxNum_F1.getValue();
//            for(int i = 0; i < globalMsg.F.getValidCount(); i++) {
//                if(numStr.compareTo(globalMsg.F.no(i).ajeInvAcctDistSqNum_F1.getValue()) < 0) {
//                    numStr = globalMsg.F.no(i).ajeInvAcctDistSqNum_F1.getValue();
//                }
//                if(numStr2.compareTo(globalMsg.F.no(i).ajeLineIdxNum_F1.getValue()) < 0) {
//                    numStr2 = globalMsg.F.no(i).ajeLineIdxNum_F1.getValue();
//                }
//            }
//
//            if(ZYPCommonFunc.hasValue(numStr)) {
//                ajeInvAcctDistSqNum = Integer.parseInt(numStr);
//                ajeInvAcctDistSqNum ++;
//            } else {
//                ajeInvAcctDistSqNum = 0;
//            }
//
//            if(ZYPCommonFunc.hasValue(numStr2)) {
//                ajeLineIdxNum = Integer.parseInt(numStr2);
//            } else {
//                ajeLineIdxNum = 0;
//            }
//            ajeLineIdxNum ++;
//        }
        // END   2016/07/20 S.Fujita [QC#10148,DEL]
        final NFACommonJrnlEntry commonJrnlEntry = new NFACommonJrnlEntry();

        int ajeSqNum = 0;

        //START 2016/09/21 S.Yoshida [QC#11049,ADD]
        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        String procDt = bizMsg.procDt.getValue();
        String acctDt = bizMsg.acctDt_H1.getValue();
        String glDt = NFZC103001Common.getGlDt(glblCmpyCd, procDt, acctDt);
        //END   2016/09/21 S.Yoshida [QC#11049,ADD]

        for(int i = 0; i < globalMsg.F.getValidCount(); i++) {
            // Get AJE Pattern List for Debit / Credit
            List<Map> resAjePtrnList_Debit = (List<Map>) commonJrnlEntry.getAjePtrn2(bizMsg.glblCmpyCd.getValue(), bizMsg.sysSrcCd_H1.getValue(), globalMsg.F.no(i).trxCd_F1.getValue(), globalMsg.F.no(i).trxRsnCd_F1.getValue(), DR_CR_TP_DEBIT);
            List<Map> resAjePtrnList_Credit = (List<Map>) commonJrnlEntry.getAjePtrn2(bizMsg.glblCmpyCd.getValue(), bizMsg.sysSrcCd_H1.getValue(), globalMsg.F.no(i).trxCd_F1.getValue(), globalMsg.F.no(i).trxRsnCd_F1.getValue(), DR_CR_TP_CREDIT);

            // START 2016/05/17 S.Fujita [QC#8373,ADD]
            // get AJE Pattern by Account Class Code
            Map<String, String>resAjePtrn_Debit = NFCL3000CommonLogic.getAjePtrn(resAjePtrnList_Debit, globalMsg.F.no(i).ajeInvAcctClsCd_F1.getValue(), globalMsg, bizMsg, i);
            if (resAjePtrn_Debit == null ) {
                bizMsg.setMessageInfo("NFAM0037E");
                return false;
            }
            Map<String, String>resAjePtrn_Credit = NFCL3000CommonLogic.getAjePtrn(resAjePtrnList_Credit, globalMsg.F.no(i).ajeInvAcctClsCd_F1.getValue(), globalMsg, bizMsg, i);
            if (resAjePtrn_Credit == null ) {
                bizMsg.setMessageInfo("NFAM0037E");
                return false;
            }
            // END 2016/05/17 S.Fujita [QC#8373,ADD]

            // START 2016/07/20 S.Fujita [QC#10148,DEL]
//            if(ZYPCommonFunc.hasValue(globalMsg.F.no(i).ajeInvAcctDistPk_F1.getValue())) {
//                //--------------------------------
//                // Update AJE_INV_ACCT_DIST
//                //--------------------------------
//                AJE_INV_ACCT_DISTTMsg inMsg = new AJE_INV_ACCT_DISTTMsg();
//                inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
//                inMsg.ajeInvAcctDistPk.setValue(globalMsg.F.no(i).ajeInvAcctDistPk_F1.getValue());
//                AJE_INV_ACCT_DISTTMsg updMsg = (AJE_INV_ACCT_DISTTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
//                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
//                    bizMsg.setMessageInfo("NFCM0041E");
//                    return false;
//                }
//                // START 2016/05/17 S.Fujita [QC#8375,MOD]
////                updMsg.procStsCd.setValue(PROC_STS.COMPLEATED);
//                if(ZYPCommonFunc.hasValue(bizMsg.xxChkBox_H3.getValue())) {
//                    updMsg.procStsCd.setValue(PROC_STS.COMPLEATED);
//                } else {
//                    updMsg.procStsCd.setValue(PROC_STS.ERROR);
//                }
//                // END 2016/05/17 S.Fujita [QC#8375,MOD]
//                updMsg.ajeInvAcctClsCd.setValue(globalMsg.F.no(i).ajeInvAcctClsCd_F1.getValue());
//                updMsg.ajeCoaCmpyCd.setValue(globalMsg.F.no(i).ajeCoaCmpyCd_F1.getValue());
//                updMsg.ajeCoaBrCd.setValue(globalMsg.F.no(i).ajeCoaBrCd_F1.getValue());
//                updMsg.ajeCoaCcCd.setValue(globalMsg.F.no(i).ajeCoaCcCd_F1.getValue());
//                updMsg.ajeCoaAcctCd.setValue(globalMsg.F.no(i).ajeCoaAcctCd_F1.getValue());
//                updMsg.ajeCoaProdCd.setValue(globalMsg.F.no(i).ajeCoaProdCd_F1.getValue());
//                updMsg.ajeCoaChCd.setValue(globalMsg.F.no(i).ajeCoaChCd_F1.getValue());
//                updMsg.ajeCoaAfflCd.setValue(globalMsg.F.no(i).ajeCoaAfflCd_F1.getValue());
//                updMsg.ajeCoaProjCd.setValue(globalMsg.F.no(i).ajeCoaProjCd_F1.getValue());
//                updMsg.ajeCoaExtnCd.setValue(globalMsg.F.no(i).ajeCoaExtnCd_F1.getValue());
//
//                if(globalMsg.F.no(i).drCrTpCd_F1.getValue().equals(DR_CR_TP_DEBIT)) {
//                    updMsg.jrnlDealAmt.setValue(globalMsg.F.no(i).jrnlDealAmt_F1.getValue());
//                    updMsg.jrnlFuncAmt.setValue(globalMsg.F.no(i).jrnlDealAmt_F1.getValue());
//                } else {
//                    updMsg.jrnlDealAmt.setValue(globalMsg.F.no(i).jrnlDealAmt_F2.getValue());
//                    updMsg.jrnlFuncAmt.setValue(globalMsg.F.no(i).jrnlDealAmt_F2.getValue());
//                }
//                // START 2016/05/17 S.Fujita [QC#8373,ADD]
//                updMsg.dsInvSlsCrPk.setValue(globalMsg.F.no(i).dsInvSlsCrPk_F1.getValue());
//                updMsg.ajeInvAcctDistPct.setValue(globalMsg.F.no(i).ajeInvAcctDistPct_F1.getValue());
//                // START 2016/06/07 S.Fujita [QC#9515,MOD]
////                updMsg.ajeInvAcctDistSqNum.setValue(String.format("%03d", i));
//                updMsg.ajeInvAcctDistSqNum.setValue(String.format("%06d", i));
//                // END 2016/06/07 S.Fujita [QC#9515,MOD]
//
//                if(globalMsg.F.no(i).drCrTpCd_F1.getValue().equals(DR_CR_TP_DEBIT)) {
//                    ZYPEZDItemValueSetter.setValue(updMsg.sysSrcCd, bizMsg.sysSrcCd_H1.getValue());
//                    ZYPEZDItemValueSetter.setValue(updMsg.sysSrcNm, bizMsg.sysSrcNm_H1.getValue());
//                    ZYPEZDItemValueSetter.setValue(updMsg.trxCd, resAjePtrn_Debit.get(TRX_CD));
//                    ZYPEZDItemValueSetter.setValue(updMsg.trxNm, resAjePtrn_Debit.get(TRX_NM));
//                    ZYPEZDItemValueSetter.setValue(updMsg.trxRsnCd, resAjePtrn_Debit.get(TRX_RSN_CD));
//                    ZYPEZDItemValueSetter.setValue(updMsg.trxRsnNm, resAjePtrn_Debit.get(TRX_RSN_NM));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajePtrnIndTpCd_01, resAjePtrn_Debit.get(AJE_PTRN_IND_TP_CD_01));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajePtrnIndTpNm_01, resAjePtrn_Debit.get(AJE_PTRN_IND_TP_NM_01));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajePtrnActlCd_01, resAjePtrn_Debit.get(AJE_PTRN_ACTL_CD_01));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajePtrnActlNm_01, resAjePtrn_Debit.get(AJE_PTRN_ACTL_NM_01));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajePtrnIndTpCd_02, resAjePtrn_Debit.get(AJE_PTRN_IND_TP_CD_02));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajePtrnIndTpNm_02, resAjePtrn_Debit.get(AJE_PTRN_IND_TP_NM_02));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajePtrnActlCd_02, resAjePtrn_Debit.get(AJE_PTRN_ACTL_CD_02));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajePtrnActlNm_02, resAjePtrn_Debit.get(AJE_PTRN_ACTL_NM_02));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajePtrnIndTpCd_03, resAjePtrn_Debit.get(AJE_PTRN_IND_TP_CD_03));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajePtrnIndTpNm_03, resAjePtrn_Debit.get(AJE_PTRN_IND_TP_NM_03));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajePtrnActlCd_03, resAjePtrn_Debit.get(AJE_PTRN_ACTL_CD_03));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajePtrnActlNm_03, resAjePtrn_Debit.get(AJE_PTRN_ACTL_NM_03));
//                    ZYPEZDItemValueSetter.setValue(updMsg.jrnlSrcCd, bizMsg.sysSrcCd_H1.getValue());
//                    ZYPEZDItemValueSetter.setValue(updMsg.jrnlSrcNm, bizMsg.sysSrcNm_H1.getValue());
//                    ZYPEZDItemValueSetter.setValue(updMsg.jrnlCatgCd, resAjePtrn_Debit.get(JRNL_CATG_CD));
//                    ZYPEZDItemValueSetter.setValue(updMsg.jrnlCatgNm, resAjePtrn_Debit.get(JRNL_CATG_NM));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajeLineIdxNum, resAjePtrn_Debit.get(AJE_LINE_IDX_NUM));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajeLineIdxDescTxt, resAjePtrn_Debit.get(DR_AJE_LINE_IDX_DESC_TXT));
//                } else {
//                    ZYPEZDItemValueSetter.setValue(updMsg.sysSrcCd, bizMsg.sysSrcCd_H1.getValue());
//                    ZYPEZDItemValueSetter.setValue(updMsg.sysSrcNm, bizMsg.sysSrcNm_H1.getValue());
//                    ZYPEZDItemValueSetter.setValue(updMsg.trxCd, resAjePtrn_Credit.get(TRX_CD));
//                    ZYPEZDItemValueSetter.setValue(updMsg.trxNm, resAjePtrn_Credit.get(TRX_NM));
//                    ZYPEZDItemValueSetter.setValue(updMsg.trxRsnCd, resAjePtrn_Credit.get(TRX_RSN_CD));
//                    ZYPEZDItemValueSetter.setValue(updMsg.trxRsnNm, resAjePtrn_Credit.get(TRX_RSN_NM));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajePtrnIndTpCd_01, resAjePtrn_Credit.get(AJE_PTRN_IND_TP_CD_01));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajePtrnIndTpNm_01, resAjePtrn_Credit.get(AJE_PTRN_IND_TP_NM_01));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajePtrnActlCd_01, resAjePtrn_Credit.get(AJE_PTRN_ACTL_CD_01));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajePtrnActlNm_01, resAjePtrn_Credit.get(AJE_PTRN_ACTL_NM_01));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajePtrnIndTpCd_02, resAjePtrn_Credit.get(AJE_PTRN_IND_TP_CD_02));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajePtrnIndTpNm_02, resAjePtrn_Credit.get(AJE_PTRN_IND_TP_NM_02));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajePtrnActlCd_02, resAjePtrn_Credit.get(AJE_PTRN_ACTL_CD_02));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajePtrnActlNm_02, resAjePtrn_Credit.get(AJE_PTRN_ACTL_NM_02));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajePtrnIndTpCd_03, resAjePtrn_Credit.get(AJE_PTRN_IND_TP_CD_03));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajePtrnIndTpNm_03, resAjePtrn_Credit.get(AJE_PTRN_IND_TP_NM_03));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajePtrnActlCd_03, resAjePtrn_Credit.get(AJE_PTRN_ACTL_CD_03));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajePtrnActlNm_03, resAjePtrn_Credit.get(AJE_PTRN_ACTL_NM_03));
//                    ZYPEZDItemValueSetter.setValue(updMsg.jrnlSrcCd, bizMsg.sysSrcCd_H1.getValue());
//                    ZYPEZDItemValueSetter.setValue(updMsg.jrnlSrcNm, bizMsg.sysSrcNm_H1.getValue());
//                    ZYPEZDItemValueSetter.setValue(updMsg.jrnlCatgCd, resAjePtrn_Credit.get(JRNL_CATG_CD));
//                    ZYPEZDItemValueSetter.setValue(updMsg.jrnlCatgNm, resAjePtrn_Credit.get(JRNL_CATG_NM));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajeLineIdxNum, resAjePtrn_Credit.get(AJE_LINE_IDX_NUM));
//                    ZYPEZDItemValueSetter.setValue(updMsg.ajeLineIdxDescTxt, resAjePtrn_Credit.get(CR_AJE_LINE_IDX_DESC_TXT));
//                }
//                // END 2016/05/17 S.Fujita [QC#8373,ADD]
//                EZDTBLAccessor.update(updMsg);
//                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
//                    bizMsg.setMessageInfo("NFCM0041E");
//                    return false;
//                }
//                //--------------------------------
//                // Delete AJE_INV_ACCT_DIST_ERR
//                //--------------------------------
//                if(ZYPCommonFunc.hasValue(globalMsg.F.no(i).ajeInvAcctDistErrPk_F1.getValue())) {
//                    AJE_INV_ACCT_DIST_ERRTMsg inMsg2 = new AJE_INV_ACCT_DIST_ERRTMsg();
//                    inMsg2.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
//                    inMsg2.ajeInvAcctDistErrPk.setValue(globalMsg.F.no(i).ajeInvAcctDistErrPk_F1.getValue());
//                    AJE_INV_ACCT_DIST_ERRTMsg remMsg = (AJE_INV_ACCT_DIST_ERRTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg2);
//                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(remMsg.getReturnCode())) {
//                        bizMsg.setMessageInfo("NFCM0041E");
//                        return false;
//                    }
//                    // START 2016/05/10 S.Fujita [QC#6961,MOD]
////                    EZDTBLAccessor.logicalRemove(remMsg);
//                    EZDTBLAccessor.remove(remMsg);
//                    // END 2016/05/10 S.Fujita [QC#6961,MOD]
//                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(remMsg.getReturnCode())) {
//                        bizMsg.setMessageInfo("NFCM0041E");
//                        return false;
//                    }
//                }
            // END   2016/07/20 S.Fujita [QC#10148,DEL]

            //--------------------------------
            // Create AJE_INV_ACCT_DIST
            //--------------------------------
            BigDecimal ajeInvAcctDistSeqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_INV_ACCT_DIST_SQ);

            AJE_INV_ACCT_DISTTMsg ajeInvAcctDistMsg = new AJE_INV_ACCT_DISTTMsg();

            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajeInvAcctDistPk, ajeInvAcctDistSeqNum);
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.invNum, bizMsg.invNum_H1.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.invBolLineNum, globalMsg.F.no(i).invBolLineNum_F1.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.invLineNum, globalMsg.F.no(i).invLineNum_F1.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.invLineSubNum, globalMsg.F.no(i).invLineSubNum_F1.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.invLineSubTrxNum, globalMsg.F.no(i).invLineSubTrxNum_F1.getValue());
            // START 2016/05/17 S.Fujita [QC#8373,MOD]
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajeInvAcctDistSqNum, String.format("%03d", ajeInvAcctDistSqNum));
//                ajeInvAcctDistSqNum++;
            // START 2016/06/07 S.Fujita [QC#9515,MOD]
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajeInvAcctDistSqNum, String.format("%03d", i));
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajeInvAcctDistSqNum, String.format("%06d", i));
            // END   2016/06/07 S.Fujita [QC#9515,MOD]
            // END 2016/05/17 S.Fujita [QC#8373,MOD]
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.procDt, bizMsg.procDt.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajeInvAcctClsCd, globalMsg.F.no(i).ajeInvAcctClsCd_F1.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.drCrTpCd, globalMsg.F.no(i).drCrTpCd_F1.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajeCoaCmpyCd, globalMsg.F.no(i).ajeCoaCmpyCd_F1.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajeCoaBrCd, globalMsg.F.no(i).ajeCoaBrCd_F1.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajeCoaCcCd, globalMsg.F.no(i).ajeCoaCcCd_F1.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajeCoaAcctCd, globalMsg.F.no(i).ajeCoaAcctCd_F1.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajeCoaProdCd, globalMsg.F.no(i).ajeCoaProdCd_F1.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajeCoaChCd, globalMsg.F.no(i).ajeCoaChCd_F1.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajeCoaAfflCd, globalMsg.F.no(i).ajeCoaAfflCd_F1.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajeCoaProjCd, globalMsg.F.no(i).ajeCoaProjCd_F1.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajeCoaExtnCd, globalMsg.F.no(i).ajeCoaExtnCd_F1.getValue());
            if(globalMsg.F.no(i).drCrTpCd_F1.getValue().equals(DR_CR_TP_DEBIT)) {
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.jrnlDealAmt, globalMsg.F.no(i).jrnlDealAmt_F1.getValue());
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.jrnlFuncAmt, globalMsg.F.no(i).jrnlDealAmt_F1.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.jrnlDealAmt, globalMsg.F.no(i).jrnlDealAmt_F2.getValue());
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.jrnlFuncAmt, globalMsg.F.no(i).jrnlDealAmt_F2.getValue());
            }
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.dealCcyCd, bizMsg.stdCcyCd.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.funcCcyCd, bizMsg.stdCcyCd.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajeInvAcctDistPct, globalMsg.F.no(i).ajeInvAcctDistPct_F1.getValue());
            //START 2016/09/21 S.Yoshida [QC#11049,MOD]
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.glDt, bizMsg.procDt.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.glDt, glDt);
            //END   2016/09/21 S.Yoshida [QC#11049,MOD]
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.dsInvSlsCrPk, globalMsg.F.no(i).dsInvSlsCrPk_F1.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajeOmIntfcPk, BigDecimal.ZERO);
            // START 2016/05/17 S.Fujita [QC#8375,MOD]
//              ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.procStsCd, PROC_STS.COMPLEATED);
            if(ZYPCommonFunc.hasValue(bizMsg.xxChkBox_H3.getValue())) {
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.procStsCd, PROC_STS.COMPLEATED);
            } else {
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.procStsCd, PROC_STS.IN_COMPLETED);
            }
            // END 2016/05/17 S.Fujita [QC#8375,MOD]
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.dfrdRevGlStrgFlg, ZYPConstant.FLG_OFF_N);

            if(globalMsg.F.no(i).drCrTpCd_F1.getValue().equals(DR_CR_TP_DEBIT)) {
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.sysSrcCd, bizMsg.sysSrcCd_H1.getValue());
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.sysSrcNm, bizMsg.sysSrcNm_H1.getValue());
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.trxCd, resAjePtrn_Debit.get(TRX_CD));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.trxNm, resAjePtrn_Debit.get(TRX_NM));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.trxRsnCd, resAjePtrn_Debit.get(TRX_RSN_CD));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.trxRsnNm, resAjePtrn_Debit.get(TRX_RSN_NM));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajePtrnIndTpCd_01, resAjePtrn_Debit.get(AJE_PTRN_IND_TP_CD_01));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajePtrnIndTpNm_01, resAjePtrn_Debit.get(AJE_PTRN_IND_TP_NM_01));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajePtrnActlCd_01, resAjePtrn_Debit.get(AJE_PTRN_ACTL_CD_01));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajePtrnActlNm_01, resAjePtrn_Debit.get(AJE_PTRN_ACTL_NM_01));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajePtrnIndTpCd_02, resAjePtrn_Debit.get(AJE_PTRN_IND_TP_CD_02));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajePtrnIndTpNm_02, resAjePtrn_Debit.get(AJE_PTRN_IND_TP_NM_02));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajePtrnActlCd_02, resAjePtrn_Debit.get(AJE_PTRN_ACTL_CD_02));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajePtrnActlNm_02, resAjePtrn_Debit.get(AJE_PTRN_ACTL_NM_02));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajePtrnIndTpCd_03, resAjePtrn_Debit.get(AJE_PTRN_IND_TP_CD_03));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajePtrnIndTpNm_03, resAjePtrn_Debit.get(AJE_PTRN_IND_TP_NM_03));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajePtrnActlCd_03, resAjePtrn_Debit.get(AJE_PTRN_ACTL_CD_03));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajePtrnActlNm_03, resAjePtrn_Debit.get(AJE_PTRN_ACTL_NM_03));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.jrnlSrcCd, bizMsg.sysSrcCd_H1.getValue());
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.jrnlSrcNm, bizMsg.sysSrcNm_H1.getValue());
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.jrnlCatgCd, resAjePtrn_Debit.get(JRNL_CATG_CD));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.jrnlCatgNm, resAjePtrn_Debit.get(JRNL_CATG_NM));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajeLineIdxNum, resAjePtrn_Debit.get(AJE_LINE_IDX_NUM));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajeLineIdxDescTxt, resAjePtrn_Debit.get(DR_AJE_LINE_IDX_DESC_TXT));
            } else {
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.sysSrcCd, bizMsg.sysSrcCd_H1.getValue());
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.sysSrcNm, bizMsg.sysSrcNm_H1.getValue());
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.trxCd, resAjePtrn_Credit.get(TRX_CD));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.trxNm, resAjePtrn_Credit.get(TRX_NM));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.trxRsnCd, resAjePtrn_Credit.get(TRX_RSN_CD));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.trxRsnNm, resAjePtrn_Credit.get(TRX_RSN_NM));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajePtrnIndTpCd_01, resAjePtrn_Credit.get(AJE_PTRN_IND_TP_CD_01));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajePtrnIndTpNm_01, resAjePtrn_Credit.get(AJE_PTRN_IND_TP_NM_01));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajePtrnActlCd_01, resAjePtrn_Credit.get(AJE_PTRN_ACTL_CD_01));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajePtrnActlNm_01, resAjePtrn_Credit.get(AJE_PTRN_ACTL_NM_01));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajePtrnIndTpCd_02, resAjePtrn_Credit.get(AJE_PTRN_IND_TP_CD_02));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajePtrnIndTpNm_02, resAjePtrn_Credit.get(AJE_PTRN_IND_TP_NM_02));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajePtrnActlCd_02, resAjePtrn_Credit.get(AJE_PTRN_ACTL_CD_02));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajePtrnActlNm_02, resAjePtrn_Credit.get(AJE_PTRN_ACTL_NM_02));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajePtrnIndTpCd_03, resAjePtrn_Credit.get(AJE_PTRN_IND_TP_CD_03));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajePtrnIndTpNm_03, resAjePtrn_Credit.get(AJE_PTRN_IND_TP_NM_03));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajePtrnActlCd_03, resAjePtrn_Credit.get(AJE_PTRN_ACTL_CD_03));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajePtrnActlNm_03, resAjePtrn_Credit.get(AJE_PTRN_ACTL_NM_03));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.jrnlSrcCd, bizMsg.sysSrcCd_H1.getValue());
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.jrnlSrcNm, bizMsg.sysSrcNm_H1.getValue());
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.jrnlCatgCd, resAjePtrn_Credit.get(JRNL_CATG_CD));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.jrnlCatgNm, resAjePtrn_Credit.get(JRNL_CATG_NM));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajeLineIdxNum, resAjePtrn_Credit.get(AJE_LINE_IDX_NUM));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistMsg.ajeLineIdxDescTxt, resAjePtrn_Credit.get(CR_AJE_LINE_IDX_DESC_TXT));
            }
            EZDTBLAccessor.create(ajeInvAcctDistMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ajeInvAcctDistMsg.getReturnCode())) {
                bizMsg.setMessageInfo("NFCM0861E", new String[]{"AJE_INV_ACCT_DIST"});
                return false;
            }
            ajeSqNum = i;
        }
        // START 2016/06/20 S.Fujita [QC#9454,ADD]
        /************************************************/
        /* remove AJE_INV_ACCT_DIST(Deferred)           */
        /************************************************/
        if(!NFCL3000CommonLogic.removeAjeInvAcctDistDfrd(bizMsg)) {
            return false;
        }

        // Create AJE_INV_ACCT_DIST(Deferred)
        for(int i = 0; i < bizMsg.B.getValidCount(); i++) {

            if (bizMsg.B.no(i).dfrdRevFlg_B1.getValue().equals(ZYPConstant.FLG_ON_Y)) {

                PreparedStatement stmtSelect = null;
                ResultSet dsInvSlsCr = null;
                S21SsmLowLevelCodingClient ssmLLClient = null;
                S21SsmExecutionParameter execParam;
                // Set SSM Cursor Parameter
                execParam = new S21SsmExecutionParameter();
                execParam.setFetchSize(FETCH_SIZE);
                execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
                execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
                execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
                // Initialize ssmLLClient
                ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
                try {
                    Map<String, Object> ssmParam = new HashMap<String, Object>();
                    ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
                    ssmParam.put("dsInvSlsCrPk", bizMsg.B.no(i).dsInvSlsCrPk_B1.getValue());
                    ssmParam.put("csmp", CM_DEF_ACCT.CSMP_COA);

                    stmtSelect = ssmLLClient.createPreparedStatement("getInvoice", ssmParam, execParam);
                    dsInvSlsCr = stmtSelect.executeQuery();

                    while (dsInvSlsCr.next()) {
                        // Get AJE Pattern List for Debit / Credit
                        List<Map> resAjePtrnList_DfrdDebit = (List<Map>) commonJrnlEntry.getAjePtrn2(bizMsg.glblCmpyCd.getValue(), bizMsg.sysSrcCd_H1.getValue(), bizMsg.B.no(i).trxCd_B1.getValue(), bizMsg.B.no(i).trxRsnCd_B1.getValue(), DR_CR_TP_DEBIT);
                        List<Map> resAjePtrnList_DfrdCredit = (List<Map>) commonJrnlEntry.getAjePtrn2(bizMsg.glblCmpyCd.getValue(), bizMsg.sysSrcCd_H1.getValue(), bizMsg.B.no(i).trxCd_B1.getValue(), bizMsg.B.no(i).trxRsnCd_B1.getValue(), DR_CR_TP_CREDIT);

                        // get AJE Pattern by Account Class Code
                        // Debit Record
                        Map<String, String>resAjePtrn_DfrdDebit = NFCL3000CommonLogic.getDfrdAndImeAjePtrn(resAjePtrnList_DfrdDebit, AJE_INV_ACCT_CLS.REV_EARN_TMPLT, bizMsg);
                        if (resAjePtrn_DfrdDebit == null ) {
                            bizMsg.setMessageInfo("NFAM0037E");
                            return false;
                        }
                        ajeSqNum++;
                        if (!NFCL3000CommonLogic.createAjeInvAcctDistDfrd(bizMsg, globalMsg, dsInvSlsCr, DR_CR_TP_DEBIT, resAjePtrn_DfrdDebit, ajeSqNum, i)) {
                            return false;
                        }
                        // Credit Record
                        Map<String, String>resAjePtrn_DfrdCredit = NFCL3000CommonLogic.getDfrdAndImeAjePtrn(resAjePtrnList_DfrdCredit, AJE_INV_ACCT_CLS.REV_EARN_TMPLT, bizMsg);
                        if (resAjePtrn_DfrdCredit == null ) {
                            bizMsg.setMessageInfo("NFAM0037E");
                            return false;
                        }
                        ajeSqNum++;
                        if (!NFCL3000CommonLogic.createAjeInvAcctDistDfrd(bizMsg, globalMsg, dsInvSlsCr, DR_CR_TP_CREDIT, resAjePtrn_DfrdCredit, ajeSqNum, i)) {
                            return false;
                        }
                    }
                } finally {
                    S21SsmLowLevelCodingClient.closeResource(stmtSelect, dsInvSlsCr);
                }
            }
        }
        // END 2016/06/20 S.Fujita [QC#9454,MOD]
        return true;
    }

    /**
     * updateCreditProfile
     * @param cMsg
     * @return
     */
    private boolean updateCreditProfile(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        NMZC600001 api = new NMZC600001();
        NMZC600001PMsg apiMsg = new NMZC600001PMsg();

        apiMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        apiMsg.xxModeCd.setValue(NMZC600001.MODE_ALL);
        apiMsg.dsAcctNum.setValue(bizMsg.billToCustAcctCd_H3.getValue());
        apiMsg.billToCustCd.setValue(bizMsg.billToCustCd_H3.getValue());
        //---- start 2016/05/04 remove credit memo logic, since it's already negated.
        //if(bizMsg.invTpCd_H1.getValue().equals(INV_TP.CREDIT_MEMO)) {
        //    apiMsg.invAmt.setValue(bizMsg.xxTotAmt_T4.getValue().negate());
        //} else {
            apiMsg.invAmt.setValue(bizMsg.xxTotAmt_T4.getValue());
        //}
        //---- end 2016/05/04
        apiMsg.invDt.setValue(bizMsg.invDt_H1.getValue());
        apiMsg.updKeyNum.setValue(bizMsg.invNum_H1.getValue());
        apiMsg.slsDt.setValue(bizMsg.invDt_H1.getValue());

        api.execute(apiMsg, ONBATCH_TYPE.ONLINE);
        if(apiMsg.xxMsgIdList.getValidCount() > 0) {
            bizMsg.setMessageInfo(apiMsg.xxMsgIdList.no(0).xxMsgId.getValue());
            return false;
        }
        return true;
    }

    // START 2016/06/03 S.Fujita [QC#9452,ADD]
    /**
     * doProcess_NFCL3000Scrn00_TAB_Accounting
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3000Scrn00_TAB_Accounting(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

//        // START 2018/09/28 T.Ogura [QC#28526,ADD]
//        NFCL3000CommonLogic.setEachQtyToShipQty(bizMsg);
//        // END   2018/09/28 T.Ogura [QC#28526,ADD]

        if(NFCL3000CommonLogic.isManualInvoice(bizMsg)) {
            // START 2016/07/28 S.Fujita [QC#10148,ADD]
            if(!NFCL3000CommonLogic.checkHeader(bizMsg)) {
                return;
            }
            // END   2016/07/28 S.Fujita [QC#10148,ADD]

            // START 2019/04/17 S.Takami [QC#31204,ADD]
            boolean isErrCheckConfigData = !checkConfigData(bizMsg);
            // END 2019/04/17 S.Takami [QC#31204,ADD]

            // START 2019/04/25 S.Takami [QC#50078,ADD]
            boolean isErrCheckFsrLine = false;
            if (NFCL3000CommonLogic.isDsInvTpFsr(bizMsg)) {
                isErrCheckFsrLine = !checkFsrLine(bizMsg);
            }

            boolean isMandatoryErrorForInvoiceType = isMandatoryErrorForInvoiceType(bizMsg);
            if (isErrCheckConfigData || isErrCheckFsrLine || isMandatoryErrorForInvoiceType) {
                return;
            }
            // END 2019/04/25 S.Takami [QC#50078,ADD]

            NFCL3000CommonLogic.initialSetup_SlsCrTab(bizMsg);
            NFCL3000CommonLogic.calcAmount_InvLine(bizMsg);
            NFCL3000CommonLogic.calcAmount_SlsCr(bizMsg);
            NFCL3000CommonLogic.initialSetup_BOLTab(bizMsg);
            NFCL3000CommonLogic.setCheckBox(bizMsg);
        }

        if(ZYPCommonFunc.hasValue(bizMsg.invNum_H1.getValue())) {
            if(!bizMsg.fnlzInvFlg_H4.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                // START 2016/09/21 S.Fujita [QC#14481,MOD]
//                if(!NFCL3000CommonLogic.setAcctDistError(bizMsg)) {
//                    NFCL3000CommonLogic.check9Segment(bizMsg);
//                }
                NFCL3000CommonLogic.check9Segment(bizMsg);
                // END   2016/09/21 S.Fujita [QC#14481,MOD]
            }

        } else {
            // Create Invoice
            bizMsg.xxPgFlg.setValue(ZYPConstant.FLG_ON_Y);
            if(!ZYPCommonFunc.hasValue(bizMsg.invNum_H1.getValue()) && bizMsg.C.getValidCount() < 1) {

                if(!checkInvoice(cMsg, sMsg)) {
                    bizMsg.xxPgFlg.setValue(ZYPConstant.FLG_OFF_N);
                    return;
                }
                if(!createInvoice(cMsg, sMsg)) {
                    bizMsg.xxPgFlg.setValue(ZYPConstant.FLG_OFF_N);
                    return;
                }
            }
        }
        // END   2016/06/03 S.Fujita [QC#9452,ADD]
    }

    // START 2019/04/17 S.Takami [QC#31204,ADD]
    private boolean checkConfigData(NFCL3000CMsg bizMsg) {

        boolean isOk = true;

        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        String selMndSvcInvChrgTpVal = ZYPCodeDataUtil.getVarCharConstValue(NFCL3000_MND_SEL_SVC_INV_CHRG, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(selMndSvcInvChrgTpVal)) {
            selMndSvcInvChrgTpVal = NFCL3000_MND_SEL_SVC_INV_CHRG_VAL;
        }
        String[] selMndSvcInvChrgTpValArray = selMndSvcInvChrgTpVal.split(",");
        Map<String, String> svcInvChrgTpNmMap = new HashMap<String, String>(0);
        for (String selMndSvcInvChrgTpCd : selMndSvcInvChrgTpValArray) {
            SVC_INV_CHRG_TPTMsg svcInvChrgTpTMsg = getSvcInvChrgTp(bizMsg.glblCmpyCd.getValue(), selMndSvcInvChrgTpCd);
            if (svcInvChrgTpTMsg != null) {
                svcInvChrgTpNmMap.put(selMndSvcInvChrgTpCd, svcInvChrgTpTMsg.svcInvChrgTpDescTxt.getValue());
            } else {
                svcInvChrgTpNmMap.put(selMndSvcInvChrgTpCd, selMndSvcInvChrgTpCd);
            }
        }
        boolean withCntrlBillgTp = false;
        for (int lineIdx = 0; lineIdx < bizMsg.A.getValidCount(); lineIdx++) {
            String svcInvChrgTpCd = bizMsg.A.no(lineIdx).svcInvChrgTpCd_A1.getValue();
            if (svcInvChrgTpNmMap.keySet().contains(svcInvChrgTpCd)) {
                if (isMandatoryErrorForBilling(bizMsg, lineIdx)) {
                    isOk = false;
                }
                withCntrlBillgTp = true;
            }
        }

        StringBuilder addMsgBuilder = new StringBuilder("");
        for (String selMndSvcInvChrgTpCd : selMndSvcInvChrgTpValArray) {
            addMsgBuilder.append(" or " + String.valueOf(svcInvChrgTpNmMap.get(selMndSvcInvChrgTpCd)));
        }
        String addMsg = addMsgBuilder.toString().substring(4, addMsgBuilder.length());
        if (withCntrlBillgTp) {
            for (int lineIdx = 0; lineIdx < bizMsg.A.getValidCount(); lineIdx++) {
                String svcInvChrgTpCd = bizMsg.A.no(lineIdx).svcInvChrgTpCd_A1.getValue();
                boolean checkContrType = false;
                if (svcInvChrgTpNmMap.keySet().contains(svcInvChrgTpCd)) {
                    checkContrType = true;
                }
                if (!checkContrType) {
                    bizMsg.A.no(lineIdx).svcInvChrgTpCd_A1.setErrorInfo(1, NFCM0899E, new String[] {addMsg, addMsg});
                    isOk = false;
                }
            }
        }
        return isOk;
    }

    private boolean isMandatoryErrorForBilling(NFCL3000CMsg bizMsg, int lineIdx) {

        boolean isError = false;

        NFCL3000_ACMsg lineMsg = bizMsg.A.no(lineIdx);
        String svcInvChrgTpCd = lineMsg.svcInvChrgTpCd_A1.getValue();

        SVC_INV_CHRG_TPTMsg svcInvChrgTpTMsg = getSvcInvChrgTp(bizMsg.glblCmpyCd.getValue(), svcInvChrgTpCd);
        String svcInvChrgTpDescTxt = "";

        if (svcInvChrgTpTMsg != null) {
            svcInvChrgTpDescTxt = svcInvChrgTpTMsg.svcInvChrgTpDescTxt.getValue();
        }
        if (!ZYPCommonFunc.hasValue(lineMsg.svcConfigMstrPk_A1) //
                || (!ZYPCommonFunc.hasValue(lineMsg.svcMachMstrPk_A1) && !ZYPCommonFunc.hasValue(lineMsg.serNum_A1))) {
            lineMsg.serNum_A1.setErrorInfo(1, NFCM0900E);
            isError = true;
        }
        if (!ZYPCommonFunc.hasValue(lineMsg.dsContrNum_A1)) {
            lineMsg.dsContrNum_A1.setErrorInfo(1, NFCM0901E, new String[] {"Contract#", svcInvChrgTpDescTxt, "Billing Type"}); // 2019/04/25 S.Takami [QC#50078,ADD] 3rd additinal string
            isError = true;
        }
        if (!ZYPCommonFunc.hasValue(lineMsg.mdlNm_A1)) {
            lineMsg.mdlNm_A1.setErrorInfo(1, NFCM0901E, new String[] {"Model#", svcInvChrgTpDescTxt, "Billing Type"}); // 2019/04/25 S.Takami [QC#50078,ADD] 3rd additinal string
            isError = true;
        }
        if (!ZYPCommonFunc.hasValue(lineMsg.bllgPerFromDt_A1)) {
            lineMsg.bllgPerFromDt_A1.setErrorInfo(1, NFCM0902E, new String[] {"Bill From Date", svcInvChrgTpDescTxt, "Billing Type"}); // 2019/04/25 S.Takami [QC#50078,ADD] 3rd additinal string
            isError = true;
        }
        if (!ZYPCommonFunc.hasValue(lineMsg.bllgPerThruDt_A1)) {
            lineMsg.bllgPerThruDt_A1.setErrorInfo(1, NFCM0902E, new String[] {"Bill To Date", svcInvChrgTpDescTxt, "Billing Type"}); // 2019/04/25 S.Takami [QC#50078,ADD] 3rd additinal string
            isError = true;
        }
        return isError;
    }

    private SVC_INV_CHRG_TPTMsg getSvcInvChrgTp(String glblCmpyCd, String svcInvChrgTpCd) {

        SVC_INV_CHRG_TPTMsg svcInvChrgTpTMsg = new SVC_INV_CHRG_TPTMsg();
        ZYPEZDItemValueSetter.setValue(svcInvChrgTpTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcInvChrgTpTMsg.svcInvChrgTpCd, svcInvChrgTpCd);

        return  (SVC_INV_CHRG_TPTMsg) S21CacheTBLAccessor.findByKey(svcInvChrgTpTMsg);
    }
    // END 2019/04/17 S.Takami [QC#31204,ADD]

    // Start 2019/04/25 S.Takami [QC#50078,ADD]
    private boolean checkFsrLine(NFCL3000CMsg bizMsg) {

        boolean isError = false;
        String dsInvTpDescTxt = "";

        DS_INV_TPTMsg dsInvTpTMsg = getDsInfTp(bizMsg.glblCmpyCd.getValue(), bizMsg.dsInvTpCd_H1.getValue());
        if (dsInvTpTMsg != null) {
            dsInvTpDescTxt = dsInvTpTMsg.dsInvTpDescTxt.getValue();
        }

        String fsrBillTypeVal = NFCL3000CommonLogicForConst.getInstance().getVarCharConstVal(bizMsg.glblCmpyCd.getValue(), NFCL3000_FSR_BLLG_TP);
        if (fsrBillTypeVal == null) {
            fsrBillTypeVal = NFCL3000_FSR_BLLG_TP_VAL;
        }
        String[] fsrBillTypeArray = fsrBillTypeVal.split(",");

        StringBuilder msgArgs = new StringBuilder("");
        for (String fsrBillType : fsrBillTypeArray) {
            SVC_INV_CHRG_TPTMsg svcInvChrgTpTMsg = getSvcInvChrgTp(bizMsg.glblCmpyCd.getValue(), fsrBillType);
            if (svcInvChrgTpTMsg != null && ZYPCommonFunc.hasValue(svcInvChrgTpTMsg.svcInvChrgTpDescTxt)) {
                msgArgs.append(" or " + svcInvChrgTpTMsg.svcInvChrgTpDescTxt.getValue());
            }
            svcInvChrgTpTMsg = null;
        }
        String msgArg = "";
        if (msgArgs.length() > 4) {
            msgArg = msgArgs.toString().substring(4);
        }

        for (int lineIdx = 0; lineIdx < bizMsg.A.getValidCount(); lineIdx++) {
            NFCL3000_ACMsg lineMsg = bizMsg.A.no(lineIdx);

            if (!ZYPCommonFunc.hasValue(lineMsg.svcInvChrgTpCd_A1)) {
                lineMsg.svcInvChrgTpCd_A1.setErrorInfo(1, NFCM0903E, new String[] {dsInvTpDescTxt});
                isError = true;
            } else {
                String svcInvChrgTp = lineMsg.svcInvChrgTpCd_A1.getValue();
                boolean isFsrBillType = false;
                for (String fsrBillType : fsrBillTypeArray) {
                    if (S21StringUtil.isEquals(fsrBillType, svcInvChrgTp)) {
                        isFsrBillType = true;
                        break;
                    }
                }
                if (!isFsrBillType) {
                    lineMsg.svcInvChrgTpCd_A1.setErrorInfo(1, NFCM0904E, new String[] {msgArg, dsInvTpDescTxt});
                    isError = true;
                }
            }
            // START 2019/06/05 S.Takami [QC#50683,ADD]
            if (!ZYPCommonFunc.hasValue(lineMsg.svcConfigMstrPk_A1) //
                    || !ZYPCommonFunc.hasValue(lineMsg.svcMachMstrPk_A1) //
                    || !ZYPCommonFunc.hasValue(lineMsg.mdlNm_A1)) {
                lineMsg.mdlNm_A1.setErrorInfo(1, NFCM0907E);
                isError = true;
            }
            // END 2019/06/05 S.Takami [QC#50683,ADD]
        }
        return !isError;
    }

    private boolean isMandatoryErrorForInvoiceType(NFCL3000CMsg bizMsg) {

        boolean isError = false;

        if (!NFCL3000CommonLogic.isInvoiceWithContract(bizMsg)) {
            return false;
        }

        String dsInvTpDescTxt = "";
        DS_INV_TPTMsg dsInvTpTMsg = getDsInfTp(bizMsg.glblCmpyCd.getValue(), bizMsg.dsInvTpCd_H1.getValue());
        if (dsInvTpTMsg != null) {
            dsInvTpDescTxt = dsInvTpTMsg.dsInvTpDescTxt.getValue();
        }

        for (int lineIdx = 0; lineIdx < bizMsg.A.getValidCount(); lineIdx++) {
            NFCL3000_ACMsg lineMsg = bizMsg.A.no(lineIdx);

            if (!ZYPCommonFunc.hasValue(lineMsg.dsContrNum_A1)) {
                lineMsg.dsContrNum_A1.setErrorInfo(1, NFCM0901E, new String[] {"Contract#", dsInvTpDescTxt, "Invoice Type"});
                isError = true;
            }
            if (!ZYPCommonFunc.hasValue(lineMsg.mdlNm_A1)) {
                lineMsg.mdlNm_A1.setErrorInfo(1, NFCM0901E, new String[] {"Model#", dsInvTpDescTxt, "Invoice Type"});
                isError = true;
            }
            if (!ZYPCommonFunc.hasValue(lineMsg.bllgPerFromDt_A1)) {
                lineMsg.bllgPerFromDt_A1.setErrorInfo(1, NFCM0902E, new String[] {"Bill From Date", dsInvTpDescTxt, "Invoice Type"});
                isError = true;
            }
            if (!ZYPCommonFunc.hasValue(lineMsg.bllgPerThruDt_A1)) {
                lineMsg.bllgPerThruDt_A1.setErrorInfo(1, NFCM0902E, new String[] {"Bill To Date", dsInvTpDescTxt, "Invoice Type"});
                isError = true;
            }
        }
        return isError;
    }

    private DS_INV_TPTMsg getDsInfTp(String glblCmpyCd, String dsInvTpCd) {

        DS_INV_TPTMsg dsInvTpTMsg = new DS_INV_TPTMsg();
        ZYPEZDItemValueSetter.setValue(dsInvTpTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsInvTpTMsg.dsInvTpCd, dsInvTpCd);

        return (DS_INV_TPTMsg) S21CacheTBLAccessor.findByKey(dsInvTpTMsg);
    }
    // END 2019/04/25 S.Takami [QC#50078,ADD]
}
