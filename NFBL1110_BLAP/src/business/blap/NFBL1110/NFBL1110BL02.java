/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL1110;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.common.EZDSystemEnv;
import business.blap.NFBL1110.NFBL1110CMsg;
import business.blap.NFBL1110.common.NFBL1110CommonLogic;
import business.blap.NFBL1110.constant.NFBL1110Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * AP Invoice Maintenance Batch Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/01   CUSA            Y.Aikawa        Create          N/A
 * 2016/08/04   Fujitsu         T.Murai         Update          QC#12692
 * 2016/08/05   Hitachi         K.Kojima        Update          QC#12971
 * 2016/08/23   Fujitsu         T.Murai         Update          QC#12830
 * 2016/09/30   Hitachi         K.Kojima        Update          QC#13442
 * 2016/09/29   Fujitsu         W.Honda         Update          Unit Test#201
 * 2017/12/22   Hitachi         Y.Takeno        Update          QC#22831
 * </pre>
 */
public class NFBL1110BL02 extends S21BusinessHandler implements NFBL1110Constant {

    /** S21UserProfileService Instance */
    public static final S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();
    /** Global Company Code */
    public static final String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();

    /**
     * Method name: doProcess
     * <dd>The method explanation: Call each process by screen id.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            // +++++ [START] : Scrn00
            if ("NFBL1110_INIT".equals(screenAplID)) {
                doProcess_NFBL1110_INIT(cMsg, sMsg);
            } else if ("NFBL1110Scrn00_SearchSupplierName".equals(screenAplID)) {
                doProcess_NFBL1110Scrn00_SearchSupplierName(cMsg, sMsg);
            } else if ("NFBL1110Scrn00_AddSerial".equals(screenAplID)) {
                doProcess_NFBL1110Scrn00_AddSerial(cMsg, sMsg);
            } else if ("NFBL1110Scrn00_AddCounterTp".equals(screenAplID)) {
                doProcess_NFBL1110Scrn00_AddCounterTp(cMsg, sMsg);
            } else if ("NFBL1110Scrn00_NextInvoice".equals(screenAplID)) {
                doProcess_NFBL1110Scrn00_NextInvoice(cMsg, sMsg);
            } else if ("NFBL1110Scrn00_PrevInvoice".equals(screenAplID)) {
                doProcess_NFBL1110Scrn00_PrevInvoice(cMsg, sMsg);
            } else if ("NFBL1110Scrn00_DeleteInvoice".equals(screenAplID)) {
                doProcess_NFBL1110Scrn00_DeleteInvoice(cMsg, sMsg);
            } else if ("NFBL1110Scrn00_DeleteCounterTp".equals(screenAplID)) {
                doProcess_NFBL1110Scrn00_DeleteCounterTp(cMsg, sMsg);
            } else if ("NFBL1110Scrn00_OpenWin_PrntVnd".equals(screenAplID)) {
                doProcess_NFBL1110Scrn00_OpenWin_PrntVnd(cMsg, sMsg);
            } else if ("NFBL1110Scrn00_OpenWin_Vnd".equals(screenAplID)) {
                doProcess_NFBL1110Scrn00_OpenWin_Vnd(cMsg, sMsg);
                // START 2016/09/29 K.Kojima [QC#13442,ADD]
            } else if ("NFBL1110Scrn00_OpenWin_Serial".equals(screenAplID)) {
                doProcess_NFBL1110Scrn00_OpenWin_Serial(cMsg, sMsg);
                // END 2016/09/29 K.Kojima [QC#13442,ADD]
            } else if ("NFBL1110Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFBL1110Scrn00_CMN_Clear(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Scrn01
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_NFBL1110_INIT
     * <dd>The method explanation: Init
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @SuppressWarnings("unchecked")
    private void doProcess_NFBL1110_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL1110_INIT================================START", this);
        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;
        NFBL1110SMsg globalMsg = (NFBL1110SMsg) sMsg;

        // Create [Adj. Reason] pulldown.
        NFBL1110CommonLogic.createAdjReasonPulldownList(bizMsg);
        // Save [Counter Type] pulldown value.
        NFBL1110CommonLogic.saveCntrTpPulldownListValue(bizMsg);

        // START 2016/09/29 W.Honda [Unit Test#201,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.xxSrvUrlTxt_BK, EZDSystemEnv.getString(EZDSYS_KEY_THEREFORE_URL));
        // END   2016/09/29 W.Honda [Unit Test#201,ADD]

        // With Parameter
        if (ZYPCommonFunc.hasValue(bizMsg.apInvNum_IH)
        ||  ZYPCommonFunc.hasValue(bizMsg.apVndCd_HD)
        ) {
            // Get Batch Number
            String apBatNum = NFBL1110CommonLogic.getApBatNum(bizMsg, getGlobalCompanyCode());
            BigDecimal cmMaintInvBatPk = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(apBatNum)) {
                cmMaintInvBatPk = NFBL1110CommonLogic.getBatPkByBatNum(apBatNum);
            }
            if (ZYPCommonFunc.hasValue(apBatNum)
            &&  ZYPCommonFunc.hasValue(cmMaintInvBatPk)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.apBatNum_BA, apBatNum);
                ZYPEZDItemValueSetter.setValue(bizMsg.cmMaintInvBatPk_BA, cmMaintInvBatPk);
                // START 2016/09/29 W.Honda [Unit Test#201,ADD]
//                NFBL1110CommonLogic.keepAllInvInfoOnSMsg(bizMsg, globalMsg);
                NFBL1110CommonLogic.keepAllInvInfoOnSMsg(bizMsg, globalMsg, getGlobalCompanyCode());
                // END   2016/09/29 W.Honda [Unit Test#201,ADD]
                NFBL1110CommonLogic.setCurrentInvIndex(bizMsg);
                bizMsg.A.setValidCount(0);
                bizMsg.A.clear();
                S21SsmEZDResult ssmResult = NFBL1110CommonLogic.searchRecordByInvInfo(bizMsg.apInvNum_IH.getValue(), bizMsg.apVndCd_HD.getValue());
                if (ssmResult.isCodeNormal()) {
                    List resultList = (List) ssmResult.getResultObject();
                    int totRecCnt = NFBL1110CommonLogic.getInvLineTotalRecordCount(bizMsg.apInvNum_IH.getValue(), bizMsg.apVndCd_HD.getValue());
                    int intValidCount = 0;
                    // Mod Start 2016/08/22 QC#12830
//                    if ((totRecCnt / INT_6) > (bizMsg.A.length() / INT_6)) {
//                        intValidCount = bizMsg.A.length() / INT_6 * INT_6;
//                        bizMsg.setMessageInfo(NFBM0001W, new String[] {Long.toString(bizMsg.A.length() / INT_6 * INT_6), Long.toString(totRecCnt) });
                    if (totRecCnt > bizMsg.A.length()) {
                        intValidCount = bizMsg.A.length();
                        bizMsg.setMessageInfo(NFBM0001W, new String[] {Long.toString(bizMsg.A.length()), Long.toString(totRecCnt) });
                        // Mod End 2016/08/22 QC#12830
                    } else {
                        intValidCount = totRecCnt;
                        bizMsg.setMessageInfo(ZZM8002I);
                    }
                    bizMsg.A.setValidCount(intValidCount);
                    // START 2016/09/29 W.Honda [Unit Test#201,MOD]
//                    NFBL1110CommonLogic.setDBInfoToScreenCommon(bizMsg, resultList);
                    NFBL1110CommonLogic.setDBInfoToScreenCommon(bizMsg, resultList, getGlobalCompanyCode());
                    // START 2016/09/29 W.Honda [Unit Test#201,MOD]
                } else {
                    bizMsg.A.clear();
                    bizMsg.A.setValidCount(0);
                    bizMsg.setMessageInfo(NFBM0069E);
                    // For Submit button for deleting invoice.
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxInvDelFlg, ZYPConstant.FLG_OFF_N);
                }
            } else {
                bizMsg.setMessageInfo(NWAM0299E);
                NFBL1110CommonLogic.initializeScreenValue(bizMsg, globalMsg);
            }
        } else {
            NFBL1110CommonLogic.initializeScreenValue(bizMsg, globalMsg);
        }

        EZDDebugOutput.println(5, "doProcess_NFBL1110_INIT================================END", this);
    }

    /**
     * Method name: doProcess_NFBL1110Scrn00_OpenWin_PrntVnd
     * <dd>The method explanation: [Supplier] link event
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1110Scrn00_OpenWin_PrntVnd(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_OpenWin_PrntVnd================================START", this);
        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;

        if (ZYPCommonFunc.hasValue(bizMsg.vndSiteNm_IH)) {
            if (!NFBL1110CommonLogic.checkLocNm(bizMsg)) {
                // START 2017/12/22 [QC#22831, MOD]
                bizMsg.vndSiteNm_IH.setErrorInfo(1, NFBM0041E, new String[] {"Supplier Site Name" });
                // END   2017/12/22 [QC#22831, MOD]
                return;
            }
        } else {
            bizMsg.apVndCd_HD.clear();
        }

        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_OpenWin_PrntVnd================================END", this);
    }

    /**
     * Method name: doProcess_NFBL1110Scrn00_OpenWin_Vnd
     * <dd>The method explanation: [Supplier] link event
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1110Scrn00_OpenWin_Vnd(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_OpenWin_Vnd================================START", this);
        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;

        if (ZYPCommonFunc.hasValue(bizMsg.prntVndCd_IH)) {
            if (!NFBL1110CommonLogic.checkPrntVnd(bizMsg)) {
                bizMsg.prntVndCd_IH.setErrorInfo(1, NFBM0041E, new String[] {"Supplier" });
                return;
            }
        }

        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_OpenWin_Vnd================================END", this);
    }

    // START 2016/09/29 K.Kojima [QC#13442,ADD]
    /**
     * Method name: doProcess_NFBL1110Scrn00_OpenWin_Serial
     * <dd>The method explanation: [Supplier] link event
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1110Scrn00_OpenWin_Serial(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_OpenWin_Serial================================START", this);
        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;
        if (ZYPCommonFunc.hasValue(bizMsg.prntVndCd_IH)) {
            if (!NFBL1110CommonLogic.checkPrntVnd(bizMsg)) {
                bizMsg.prntVndCd_IH.setErrorInfo(1, NFBM0041E, new String[] {"Supplier" });
            }
        }
        if (ZYPCommonFunc.hasValue(bizMsg.vndSiteNm_IH)) {
            if (!NFBL1110CommonLogic.checkLocNm(bizMsg)) {
                // START 2017/12/22 [QC#22831, MOD]
                bizMsg.vndSiteNm_IH.setErrorInfo(1, NFBM0041E, new String[] {"Supplier Site Name" });
                // END   2017/12/22 [QC#22831, MOD]
            }
        }
        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_OpenWin_Serial================================END", this);
    }
    // END 2016/09/29 K.Kojima [QC#13442,ADD]

    /**
     * Method name: doProcess_NFBL1110Scrn00_SearchSupplierName
     * <dd>The method explanation: [>>] button event
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1110Scrn00_SearchSupplierName(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_SearchSupplierName================================START", this);
        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;

        NFBL1110CommonLogic.setPrntVndNm(bizMsg);

        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_SearchSupplierName================================END", this);
    }

    /**
     * Method name: doProcess_NFBL1110Scrn00_AddSerial
     * <dd>The method explanation: [Add Serial] button event
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1110Scrn00_AddSerial(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_AddSerial================================START", this);
        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;

        // Add Start 2016/08/04 QC#12692
        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.ovrdSerNum_AD.getValue())) {
            if (!NFBL1110CommonLogic.checkSerial(bizMsg, bizMsg.serNum_AD.getValue(), getGlobalCompanyCode())) {
                bizMsg.serNum_AD.setErrorInfo(1, NFBM0228E);
                return;
            }
        }
        // Check Site Name existence for serial Check
        if (!NFBL1110CommonLogic.checkLocNm(bizMsg)) {
            // START 2017/12/22 [QC#22831, MOD]
            bizMsg.vndSiteNm_IH.setErrorInfo(1, NFBM0041E, new String[] {"Supplier Site Name" });
            // END   2017/12/22 [QC#22831, MOD]
            return;
        }
        // Add End 2016/08/04 QC#12692

        // Mod Start 2016/08/22 QC#12830
        // Only 1st Counter
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.A.getValidCount()).xxGrpFlg_A1, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.A.getValidCount()).ovrdSerNum_A1, bizMsg.ovrdSerNum_AD.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.A.getValidCount()).serNum_A1, bizMsg.serNum_AD.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.A.getValidCount()).startDt_A1, bizMsg.startDt_AD.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.A.getValidCount()).endDt_A1, bizMsg.endDt_AD.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.A.getValidCount()).baseAmt_A1, bizMsg.baseAmt_AD.getValue());

        // Create [Counter Type] pulldown.
        NFBL1110CommonLogic.createCntrTpPulldown(bizMsg, bizMsg.A.getValidCount());
        bizMsg.A.no(bizMsg.A.getValidCount()).cntrTpCd_A1.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.A.getValidCount()).startReadMtrCnt_A1, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.A.getValidCount()).endReadMtrCnt_A1, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.A.getValidCount()).readMtrCnt_A1, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.A.getValidCount()).apTolAmt_A1, BigDecimal.ZERO);
        bizMsg.A.setValidCount(bizMsg.A.getValidCount() + 1);

//      bizMsg.A.setValidCount(bizMsg.A.getValidCount() + 6);
//      for (int i = (bizMsg.A.getValidCount() - 6); i < bizMsg.A.getValidCount(); i++) {
//          if (i == (bizMsg.A.getValidCount() - 6)) {
//              // Only 1st Counter
//              ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).ovrdSerNum_A1, bizMsg.ovrdSerNum_AD.getValue());
//              ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).serNum_A1, bizMsg.serNum_AD.getValue());
//              ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).startDt_A1, bizMsg.startDt_AD.getValue());
//              ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).endDt_A1, bizMsg.endDt_AD.getValue());
//              ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).baseAmt_A1, bizMsg.baseAmt_AD.getValue());
//          } else {
//              bizMsg.A.no(i).ovrdSerNum_A1.clear();
//              bizMsg.A.no(i).serNum_A1.clear();
//              bizMsg.A.no(i).startDt_A1.clear();
//              bizMsg.A.no(i).endDt_A1.clear();
//              bizMsg.A.no(i).baseAmt_A1.clear();
//          }
//          // Create [Counter Type] pulldown.
//          NFBL1110CommonLogic.createCntrTpPulldown(bizMsg, i);
//          bizMsg.A.no(i).cntrTpCd_A1.clear();
//          ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).startReadMtrCnt_A1, BigDecimal.ZERO);
//          ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).endReadMtrCnt_A1, BigDecimal.ZERO);
//          ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).readMtrCnt_A1, BigDecimal.ZERO);
//          ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).apTolAmt_A1, BigDecimal.ZERO);
//      }
        // Mod End 2016/08/22 QC#12830

        // Clear input
        bizMsg.ovrdSerNum_AD.clear();
        bizMsg.serNum_AD.clear();
        bizMsg.startDt_AD.clear();
        bizMsg.endDt_AD.clear();
        bizMsg.baseAmt_AD.clear();

        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_AddSerial================================END", this);
    }

    // Add Start 2016/08/22 QC#12830
    /**
     * Method name: doProcess_NFBL1110Scrn00_AddCounterTp
     * <dd>The method explanation: [Add Counter] button event
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1110Scrn00_AddCounterTp(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_AddCounterTp================================START", this);
        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;

        for (int i = 0 ; i < bizMsg.A.getValidCount(); i++ ) {
            NFBL1110_ACMsg lineMsg = bizMsg.A.no(i);

            if (!ZYPConstant.FLG_ON_Y.equals(lineMsg.xxGrpFlg_A1.getValue())
                    && ZYPConstant.CHKBOX_ON_Y.equals(lineMsg.xxChkBox_A1.getValue())) {

                int insertLineNum = bizMsg.A.getValidCount();
                String serNum = lineMsg.serNum_A1.getValue();
                // Insert Line
                for (int j = i + 1; j < bizMsg.A.getValidCount(); j++) {
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(j).serNum_A1)) {
                        insertLineNum = j;
                        break;
                    }
                }
                
                for (int x = bizMsg.A.getValidCount() - 1; x >= insertLineNum ; x--) {
                    EZDMsg.copy(bizMsg.A.no(x), null, bizMsg.A.no(x+1), null);
                }
                bizMsg.A.no(insertLineNum).clear();
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(insertLineNum).startReadMtrCnt_A1, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(insertLineNum).endReadMtrCnt_A1, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(insertLineNum).readMtrCnt_A1, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(insertLineNum).apTolAmt_A1, BigDecimal.ZERO);
                bizMsg.A.no(insertLineNum).xxGrpFlg_A1.setValue(ZYPConstant.FLG_ON_Y);

                // Create [Counter Type] pulldown.
                NFBL1110CommonLogic.createCntrTpPulldown(bizMsg, insertLineNum);
                bizMsg.A.no(insertLineNum).cntrTpCd_A1.clear();
                
                bizMsg.A.setValidCount(bizMsg.A.getValidCount() + 1);
            }
            
        }

        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_AddCounterTp================================END", this);
    }
    // Add End 2016/08/22 QC#12830

    /**
     * Method name: doProcess_NFBL1110Scrn00_PrevInvoice
     * <dd>The method explanation: [>>] button event
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1110Scrn00_PrevInvoice(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_PrevInvoice================================START", this);
        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;
        NFBL1110SMsg globalMsg = (NFBL1110SMsg) sMsg;

        boolean checkInput = false;

        if (bizMsg.apInvAmt_IH.getValue().compareTo(BigDecimal.ZERO) != 0
        ||  ZYPCommonFunc.hasValue(bizMsg.prntVndCd_IH)
        ||  bizMsg.apMiscAmt_IH.getValue().compareTo(BigDecimal.ZERO) != 0
        ||  ZYPCommonFunc.hasValue(bizMsg.apInvNum_IH)
        ||  ZYPCommonFunc.hasValue(bizMsg.vndSiteNm_IH)
        ||  bizMsg.apTaxAmt_IH.getValue().compareTo(BigDecimal.ZERO) != 0
        ||  ZYPCommonFunc.hasValue(bizMsg.invDt_IH)
        ||  bizMsg.lateFeeAmt_IH.getValue().compareTo(BigDecimal.ZERO) != 0
        ||  bizMsg.A.getValidCount() > 0
        ||  ZYPCommonFunc.hasValue(bizMsg.invCmntTxt_CO)
        ||  bizMsg.apAdjAmt_CO.getValue().compareTo(BigDecimal.ZERO) != 0
        ||  ZYPCommonFunc.hasValue(bizMsg.apAdjRsnCd_CO)
        ) {
            checkInput = true;
        }

        if (checkInput) {
            // Save Current Invoice Info to SMessage.
            if (!NFBL1110CommonLogic.keepCurrentInvInfo(bizMsg, globalMsg, getGlobalCompanyCode())) {
                return;
            }
        }
        // Initialize current invoice information from Screen.
        NFBL1110CommonLogic.initInvoiceHeader(bizMsg);
        NFBL1110CommonLogic.initInvoiceDetail(bizMsg);
        NFBL1110CommonLogic.initComment(bizMsg);

        int intPrevInvIndex = bizMsg.xxListNum_Y.getValueInt() - 1;
        // Set previous invoice information from SMsg.
        NFBL1110CommonLogic.setInvInfoFromSMsg(bizMsg, globalMsg, intPrevInvIndex);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxListNum_Y, new BigDecimal(intPrevInvIndex));
        ZYPEZDItemValueSetter.setValue(bizMsg.invFlg_NE, ZYPConstant.FLG_OFF_N); // Existing Invoice

        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_PrevInvoice================================END", this);
    }

    /**
     * Method name: doProcess_NFBL1110Scrn00_NextInvoice
     * <dd>The method explanation: [>>] button event
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1110Scrn00_NextInvoice(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_NextInvoice================================START", this);
        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;
        NFBL1110SMsg globalMsg = (NFBL1110SMsg) sMsg;

        // Save Current Invoice Info to SMessage.
        if (NFBL1110CommonLogic.keepCurrentInvInfo(bizMsg, globalMsg, getGlobalCompanyCode())) {
            // Clear current invoice information from Screen.
            NFBL1110CommonLogic.initInvoiceHeader(bizMsg);
            NFBL1110CommonLogic.initInvoiceDetail(bizMsg);
            NFBL1110CommonLogic.initComment(bizMsg);
        } else {
            return;
        }

        int intNextInvIndex = bizMsg.xxListNum_Y.getValueInt() + 1;
        if (intNextInvIndex >= bizMsg.Y.getValidCount()) {
            // Blank invoice.
            ZYPEZDItemValueSetter.setValue(bizMsg.xxListNum_Y, new BigDecimal(intNextInvIndex));
            ZYPEZDItemValueSetter.setValue(bizMsg.invFlg_NE, ZYPConstant.FLG_ON_Y); // New Invoice
        } else {
            // Set next invoice information from SMsg.
            NFBL1110CommonLogic.setInvInfoFromSMsg(bizMsg, globalMsg, intNextInvIndex);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxListNum_Y, new BigDecimal(intNextInvIndex));
            ZYPEZDItemValueSetter.setValue(bizMsg.invFlg_NE, ZYPConstant.FLG_OFF_N); // Existing Invoice
        }

        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_NextInvoice================================END", this);
    }

    /**
     * Method name: doProcess_NFBL1110Scrn00_DeleteInvoice
     * <dd>The method explanation: [>>] button event
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1110Scrn00_DeleteInvoice(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_DeleteInvoice================================START", this);
        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;
        NFBL1110SMsg globalMsg = (NFBL1110SMsg) sMsg;

        if (bizMsg.invFlg_NE.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            // Initialize current invoice information from Screen.
            NFBL1110CommonLogic.initInvoiceHeader(bizMsg);
            NFBL1110CommonLogic.initInvoiceDetail(bizMsg);
            NFBL1110CommonLogic.initComment(bizMsg);

            int intPrevInvIndex = bizMsg.xxListNum_Y.getValueInt() - 1;
            // Set previous invoice information from SMsg.
            NFBL1110CommonLogic.setInvInfoFromSMsg(bizMsg, globalMsg, intPrevInvIndex);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxListNum_Y, new BigDecimal(intPrevInvIndex));
            ZYPEZDItemValueSetter.setValue(bizMsg.invFlg_NE, ZYPConstant.FLG_OFF_N); // Existing Invoice
        } else {
            // Clear current invoice information from Y Message.
            NFBL1110CommonLogic.deleteCurrentInvInfoFromY(bizMsg);
            // Clear current invoice information from SMessage.
            NFBL1110CommonLogic.deleteCurrentInvInfoFromS(bizMsg, globalMsg);
            // Clear current invoice information from Screen.
            NFBL1110CommonLogic.initInvoiceHeader(bizMsg);
            NFBL1110CommonLogic.initInvoiceDetail(bizMsg);
            NFBL1110CommonLogic.initComment(bizMsg);
            int intNextInvIndex = bizMsg.xxListNum_Y.getValueInt();
            if (intNextInvIndex >= bizMsg.Y.getValidCount()) {
                // Blank invoice.
                ZYPEZDItemValueSetter.setValue(bizMsg.xxListNum_Y, new BigDecimal(intNextInvIndex));
                ZYPEZDItemValueSetter.setValue(bizMsg.invFlg_NE, ZYPConstant.FLG_ON_Y); // New Invoice
            } else {
                // Set next invoice information from SMsg.
                NFBL1110CommonLogic.setInvInfoFromSMsg(bizMsg, globalMsg, intNextInvIndex);
                ZYPEZDItemValueSetter.setValue(bizMsg.xxListNum_Y, new BigDecimal(intNextInvIndex));
                ZYPEZDItemValueSetter.setValue(bizMsg.invFlg_NE, ZYPConstant.FLG_OFF_N); // Existing Invoice
            }
            NFBL1110CommonLogic.updateRunningValue(bizMsg);
        }

        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_DeleteInvoice================================END", this);
    }

    // Add Start 2016/08/22 QC#12830
    /**
     * Method name: doProcess_NFBL1110Scrn00_DeleteCounterTp
     * <dd>The method explanation: [Delete Counter] button event
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1110Scrn00_DeleteCounterTp(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_DeleteCounterTp================================START", this);
        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox_A1", ZYPConstant.FLG_ON_Y);
        List<Integer> deleteList = new ArrayList<Integer>();

        for (Integer checkNum : checkList ) {
            
            NFBL1110_ACMsg lineMsg = bizMsg.A.no(checkNum);

            if (ZYPConstant.FLG_ON_Y.equals(lineMsg.xxGrpFlg_A1.getValue())
                    && ZYPConstant.CHKBOX_ON_Y.equals(lineMsg.xxChkBox_A1.getValue())) {
                deleteList.add(checkNum);
            }
            
        }
        ZYPTableUtil.deleteRows(bizMsg.A, deleteList);

        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_DeleteCounterTp================================END", this);
    }
    // Add End 2016/08/22 QC#12830
    
    /**
     * Method name: doProcess_NFBL1110Scrn00_CMN_Clear M
     * <dd>The method explanation: Clear values.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1110Scrn00_CMN_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_CMN_Clear================================START", this);

        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;
        NFBL1110SMsg globalMsg = (NFBL1110SMsg) sMsg;

        NFBL1110CommonLogic.initializeScreenValue(bizMsg, globalMsg);

        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_CMN_Clear================================END", this);
    }

}