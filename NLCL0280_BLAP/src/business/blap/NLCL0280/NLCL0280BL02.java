/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0280;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NLCL0280.common.NLCL0280CommonLogic;
import business.blap.NLCL0280.constant.NLCL0280Constant;
import business.file.NLCL0280F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * NLCL0280BL02 
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/25   CITS            T.Tokutomi      Create          N/A
 * 05/25/2016   CSAI            Y.Imazu         Update          QC#6864
 * 2019/03/08   Hitachi         H.Umeda         Update          QC#30166
 * 04/21/2023   CITS            DuyLe           Update          QC#61403
 *</pre>
 */
public class NLCL0280BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NLCL0280_INIT".equals(screenAplID)) {
                doProcess_Init((NLCL0280CMsg) cMsg, (NLCL0280SMsg) sMsg);
                ZYPGUITableColumn.getColData((NLCL0280CMsg) cMsg, (NLCL0280SMsg) sMsg);

            } else if ("NLCL0280Scrn00_Search".equals(screenAplID)) {
                doProcess_Search((NLCL0280CMsg) cMsg, (NLCL0280SMsg) sMsg);

            } else if ("NLCL0280Scrn00_PageNext".equals(screenAplID)) {
                doProcess_PageNext((NLCL0280CMsg) cMsg, (NLCL0280SMsg) sMsg);

            } else if ("NLCL0280Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_PagePrev((NLCL0280CMsg) cMsg, (NLCL0280SMsg) sMsg);

            } else if ("NLCL0280Scrn00_PageJump".equals(screenAplID)) {
                doProcess_PageJump((NLCL0280CMsg) cMsg, (NLCL0280SMsg) sMsg);

            } else if ("NLCL0280Scrn00_Select_TrxTp".equals(screenAplID)) {
                doProcess_SelTrxTp((NLCL0280CMsg) cMsg, (NLCL0280SMsg) sMsg);

            } else if ("NLCL0280Scrn00_SelectTrx".equals(screenAplID)) {
                doProcess_SelTrx((NLCL0280CMsg) cMsg, (NLCL0280SMsg) sMsg);

            } else if ("NLCL0280Scrn00_Search_ProdInfo".equals(screenAplID)) {
                doProcess_SrchPrd((NLCL0280CMsg) cMsg);

            } else if ("NLCL0280Scrn00_Search_RtlSWHInfo".equals(screenAplID)) {
                doProcess_SrchRtlSubWh((NLCL0280CMsg) cMsg);

            } else if ("NLCL0280Scrn00_Search_RtlWHInfo".equals(screenAplID)) {
                doProcess_SrchRtlWh((NLCL0280CMsg) cMsg);

            } else if ("NLCL0280Scrn00_Search_VndInfo".equals(screenAplID)) {
                doProcess_SrchVnd((NLCL0280CMsg) cMsg);

            } else if ("NLCL0280Scrn00_Search_ShipLocInfoFrom".equals(screenAplID)) {
                doProcess_SrchShipFrom((NLCL0280CMsg) cMsg);

            } else if ("NLCL0280Scrn00_Search_ShipLocInfoTo".equals(screenAplID)) {
                doProcess_SrchShipTo((NLCL0280CMsg) cMsg);

            } else if ("NLCL0280Scrn00_Select_Search".equals(screenAplID)) {
                doProcess_SelectSearch((NLCL0280CMsg) cMsg, (NLCL0280SMsg) sMsg);

            } else if ("NLCL0280Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_CmnClear((NLCL0280CMsg) cMsg, (NLCL0280SMsg) sMsg);

            } else if ("NLCL0280Scrn00_CMN_Download".equals(screenAplID)) {
                // Updated by DuyLe - 04/21/2023 - QC#61403 - START
//                doProcess_Search((NLCL0280CMsg) cMsg, (NLCL0280SMsg) sMsg);

//                if (!"E".equals(cMsg.getMessageKind())) {

                    doProcess_CmnDownload((NLCL0280CMsg) cMsg, (NLCL0280SMsg) sMsg);
//                }
                // Updated by DuyLe - 04/21/2023 - QC#61403 - END
            } else if ("NLCL0280Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_CmnSort((NLCL0280CMsg) cMsg, (NLCL0280SMsg) sMsg);

            } else if ("NLCL0280Scrn00_CMN_Reset".equals(screenAplID)) {
                // Same Init
                doProcess_Init((NLCL0280CMsg) cMsg, (NLCL0280SMsg) sMsg);
                ZYPGUITableColumn.getColData((NLCL0280CMsg) cMsg, (NLCL0280SMsg) sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Init
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_Init(NLCL0280CMsg cMsg, NLCL0280SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        String userId = getContextUserInfo().getUserId();

        // Set PullDown
        NLCL0280CommonLogic.setPulldownSaveSearch(cMsg, glblCmpyCd, userId);
        NLCL0280CommonLogic.setPulldownItemType(cMsg);
        NLCL0280CommonLogic.setPulldownMdseType(cMsg);
        NLCL0280CommonLogic.createLocStsChkBox(cMsg, glblCmpyCd);

        if (ZYPCommonFunc.hasValue(cMsg.invtyTrxTpCd_PS)) {

            doProcess_SelTrxTp(cMsg, sMsg);

        } else {

            NLCL0280CommonLogic.setPulldownTrxType(cMsg, glblCmpyCd);
        }

        if (ZYPCommonFunc.hasValue(cMsg.trxCd_PS)) {

            doProcess_SelTrx(cMsg, sMsg);

        } else {

            NLCL0280CommonLogic.setPulldownTrxCd(cMsg, glblCmpyCd);
        }

        NLCL0280CommonLogic.setPulldownXrefType(cMsg);

        // check trx data
        if (ZYPCommonFunc.hasValue(cMsg.invtyTrxTpCd_PS) && ZYPCommonFunc.hasValue(cMsg.invtyTrxTpCd_PS) && ZYPCommonFunc.hasValue(cMsg.invtyTrxTpCd_PS)) {

            // check trx type
            if (ZYPCommonFunc.hasValue(cMsg.invtyTrxTpCd_PS)) {

                boolean errFlg = true;

                for (int i = 0; i < cMsg.invtyTrxTpCd_PD.length(); i++) {

                    if (cMsg.invtyTrxTpCd_PS.getValue().equals(cMsg.invtyTrxTpCd_PD.no(i).getValue())) {

                        errFlg = false;
                        break;
                    }
                }

                if (errFlg) {

                    cMsg.invtyTrxTpCd_PS.setErrorInfo(1, NLCL0280Constant.NLZM2278E, new String[] {"Transaction Type" });
                    cMsg.setMessageInfo(NLCL0280Constant.ZZM9037E);
                }
            }

            // check trx Code
            if (ZYPCommonFunc.hasValue(cMsg.trxCd_PS)) {

                boolean errFlg = true;

                for (int i = 0; i < cMsg.trxCd_PD.length(); i++) {

                    if (cMsg.trxCd_PS.getValue().equals(cMsg.trxCd_PD.no(i).getValue())) {

                        errFlg = false;
                        break;
                    }
                }

                if (errFlg) {

                    cMsg.trxCd_PS.setErrorInfo(1, NLCL0280Constant.NLZM2278E, new String[] {"Transaction Code" });
                    cMsg.setMessageInfo(NLCL0280Constant.ZZM9037E);
                }
            }

            // check trx reason Code
            if (ZYPCommonFunc.hasValue(cMsg.trxRsnCd_PS)) {

                boolean errFlg = true;

                for (int i = 0; i < cMsg.trxRsnCd_PD.length(); i++) {

                    if (cMsg.trxRsnCd_PS.getValue().equals(cMsg.trxRsnCd_PD.no(i).getValue())) {

                        errFlg = false;
                        break;
                    }
                }

                if (errFlg) {

                    cMsg.trxRsnCd_PS.setErrorInfo(1, NLCL0280Constant.NLZM2278E, new String[] {"Transaction Reason Code" });
                    cMsg.setMessageInfo(NLCL0280Constant.ZZM9037E);
                }
            }
        }

        if (NLCL0280CommonLogic.chkInitParam(cMsg)) {

            // Search
            doProcess_Search(cMsg, sMsg);
        }
    }

    /**
     * Search
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_Search(NLCL0280CMsg cMsg, NLCL0280SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        // Updated by DuyLe - 04/21/2023 - QC#61403 - Separate error handlers into separate functions that can be reused - START
        boolean hasError = checkError(glblCmpyCd, cMsg);
        boolean ordTakeMdse = getOrdTakeMdse(hasError, glblCmpyCd, cMsg);
        String shipFromLocTpCd = getShipFromLocTpCd(hasError, glblCmpyCd, cMsg);
        String shipToLocTpCd = getShipToLocTpCd(hasError, glblCmpyCd, cMsg);
        // Updated by DuyLe - 04/21/2023 - QC#61403 - Separate error handlers into separate functions that can be reused - END

        // Search (Not Error)
        if (!hasError) {
            NLCL0280CommonLogic.search(glblCmpyCd, cMsg, sMsg, ordTakeMdse, shipFromLocTpCd, shipToLocTpCd);
        }
    }
    
    /**
     * Added by DuyLe - 04/21/2023 - QC#61403
     * checkError
     * @param glblCmpyCd
     * @param cMsg
     */
    private boolean checkError(String glblCmpyCd, NLCL0280CMsg cMsg) {
        boolean hasError = false;
        // Retail Warehouse
        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd_H1)) {

            if (!NLCL0280CommonLogic.setRtlWhNm(glblCmpyCd, cMsg)) {

                hasError = true;
            }
        }

        // Sub Warehouse
        if (ZYPCommonFunc.hasValue(cMsg.rtlSwhCd_H1)) {

            if (!NLCL0280CommonLogic.setRtlSubWhNm(glblCmpyCd, cMsg)) {

                hasError = true;
            }
        }

        // Location
        if (!hasError && ZYPCommonFunc.hasValue(cMsg.rtlWhCd_H1) && ZYPCommonFunc.hasValue(cMsg.rtlSwhCd_H1)) {

            if (!NLCL0280CommonLogic.hasWhLocation(glblCmpyCd, cMsg)) {

                hasError = true;
            }
        }

        // Product Code
        if (ZYPCommonFunc.hasValue(cMsg.coaProdCd_H1)) {

            if (!NLCL0280CommonLogic.setCorProdDescTxt(glblCmpyCd, cMsg)) {

                hasError = true;
            }
        }
        
        // Supplier(VND)
        if (ZYPCommonFunc.hasValue(cMsg.vndCd_H1)) {

            if (!NLCL0280CommonLogic.setVndNm(glblCmpyCd, cMsg)) {

                hasError = true;
            }
        }
        return hasError;
    }
    
    /**
     * Added by DuyLe - 04/21/2023 - QC#61403
     * getShipFromLocTpCd
     * @param hasError
     * @param glblCmpyCd
     * @param cMsg
     */
    private String getShipFromLocTpCd(boolean hasError, String glblCmpyCd, NLCL0280CMsg cMsg) {
        String shipFromLocTpCd = null;
        if (ZYPCommonFunc.hasValue(cMsg.shipFromLocCustCd_H1)) {

            Map<String, Object> map = NLCL0280CommonLogic.setShipFromToLocNm(glblCmpyCd, cMsg, cMsg.shipFromLocCustCd_H1, cMsg.dsAcctNm_H1, shipFromLocTpCd, "Ship From Location");
            if (map == null || map.isEmpty()) {

                hasError = true;

            } else {

                ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_H1, (String) map.get("LOC_NM"));
                shipFromLocTpCd = (String) map.get("LOC_TP_CD");

            }
        }
        return shipFromLocTpCd;
    }
    
    /**
     * Added by DuyLe - 04/21/2023 - QC#61403
     * getShipToLocTpCd
     * @param hasError
     * @param glblCmpyCd
     * @param cMsg
     */
    private String getShipToLocTpCd(boolean hasError, String glblCmpyCd, NLCL0280CMsg cMsg) {
        String shipToLocTpCd = null;
        if (ZYPCommonFunc.hasValue(cMsg.shipToLocCustCd_H1)) {

            Map<String, Object> map = NLCL0280CommonLogic.setShipFromToLocNm(glblCmpyCd, cMsg, cMsg.shipToLocCustCd_H1, cMsg.dsAcctNm_H2, shipToLocTpCd, "Ship To Location");
            if (map == null || map.isEmpty()) {

                hasError = true;

            } else {

                ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_H2, (String) map.get("LOC_NM"));
                shipToLocTpCd = (String) map.get("LOC_TP_CD");

            }
        }
        return shipToLocTpCd;
    }
    
    /**
     * Added by DuyLe - 04/21/2023 - QC#61403
     * getOrdTakeMdse
     * @param hasError
     * @param glblCmpyCd
     * @param cMsg
     */
    private boolean getOrdTakeMdse(boolean hasError, String glblCmpyCd, NLCL0280CMsg cMsg) {
        boolean ordTakeMdse = false;
        // MdseCd
        if (ZYPCommonFunc.hasValue(cMsg.mdseCd_H1)) {

            ordTakeMdse = NLCL0280CommonLogic.chkOrdTakeMdse(glblCmpyCd, cMsg.mdseCd_H1.getValue());

            if (!ordTakeMdse && !NLCL0280CommonLogic.hasMdseCd(glblCmpyCd, cMsg.mdseCd_H1.getValue())) {

                cMsg.mdseCd_H1.setErrorInfo(1, NLCL0280Constant.NLZM2278E, new String[] {"Item Number" });
                cMsg.setMessageInfo(NLCL0280Constant.ZZM9037E);
                hasError = true;
            }
        }
        return ordTakeMdse;
    }

    /**
     * doProcess_PageNext
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_PageNext(NLCL0280CMsg cMsg, NLCL0280SMsg sMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;

        for (; i < pagenationFrom + cMsg.A.length(); i++) {

            if (i < sMsg.A.getValidCount()) {

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);

            } else {

                break;
            }
        }

        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
    }

    /**
     * doProcess_PagePrev
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_PagePrev(NLCL0280CMsg cMsg, NLCL0280SMsg sMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;

        for (; i < pagenationFrom + cMsg.A.length(); i++) {

            if (i < sMsg.A.getValidCount()) {

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);

            } else {

                break;
            }
        }

        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pagenationFrom);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount() - 1);
    }

    /**
     * doProcess_PageJump
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_PageJump(NLCL0280CMsg cMsg, NLCL0280SMsg sMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;

        for (; i < pagenationFrom + cMsg.A.length(); i++) {

            if (i < sMsg.A.getValidCount()) {

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);

            } else {

                break;
            }
        }

        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
    }

    /**
     * doProcess_SelTrxTp
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_SelTrxTp(NLCL0280CMsg cMsg, NLCL0280SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        if (ZYPCommonFunc.hasValue(cMsg.invtyTrxTpCd_PS)) {

            // Change pulldown list
            NLCL0280CommonLogic.changePulldownTrxCdList(cMsg, glblCmpyCd);

            if (ZYPCommonFunc.hasValue(cMsg.trxCd_PS)) {

                boolean hasSelTrxCd = false;

                for (int i = 0; i < cMsg.trxCd_PD.length(); i++) {

                    if (cMsg.trxCd_PD.no(i).getValue().equals(cMsg.trxCd_PS.getValue())) {

                        hasSelTrxCd = true;
                        break;
                    }
                }

                if (!hasSelTrxCd) {

                    cMsg.trxCd_PS.clear();
                }
            }

            if (ZYPCommonFunc.hasValue(cMsg.trxCd_PS)) {

                NLCL0280CommonLogic.changePulldownTrxRsnCdListSelTrxCdHasType(cMsg, glblCmpyCd);

            } else {

                cMsg.trxRsnCd_PS.clear();
                NLCL0280CommonLogic.changePulldownTrxRsnCdList(cMsg, glblCmpyCd);
            }

        } else {

            NLCL0280CommonLogic.setPulldownTrxCd(cMsg, glblCmpyCd);

            if (ZYPCommonFunc.hasValue(cMsg.trxCd_PS)) {

                NLCL0280CommonLogic.changePulldownTrxRsnCdListSelTrxCdNoType(cMsg, glblCmpyCd);

            } else {

                cMsg.trxRsnCd_PS.clear();
                cMsg.trxRsnCd_PD.clear();
                cMsg.xxScrItem61Txt_TR.clear();

                NLCL0280CommonLogic.setPulldownTrxCd(cMsg, glblCmpyCd);
            }
        }
    }

    /**
     * doProcess_SelTrx
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_SelTrx(NLCL0280CMsg cMsg, NLCL0280SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        cMsg.trxRsnCd_PS.clear();

        if (ZYPCommonFunc.hasValue(cMsg.trxCd_PS)) {

            // Change pulldown list
            if (ZYPCommonFunc.hasValue(cMsg.invtyTrxTpCd_PS)) {

                NLCL0280CommonLogic.changePulldownTrxRsnCdListSelTrxCdHasType(cMsg, glblCmpyCd);

            } else {

                NLCL0280CommonLogic.changePulldownTrxRsnCdListSelTrxCdNoType(cMsg, glblCmpyCd);
            }

        } else if (ZYPCommonFunc.hasValue(cMsg.invtyTrxTpCd_PS)) {

            NLCL0280CommonLogic.changePulldownTrxCdList(cMsg, glblCmpyCd);
            NLCL0280CommonLogic.changePulldownTrxRsnCdList(cMsg, glblCmpyCd);

        } else {

            cMsg.trxRsnCd_PD.clear();
            cMsg.xxScrItem61Txt_TR.clear();

            NLCL0280CommonLogic.setPulldownTrxType(cMsg, glblCmpyCd);
            NLCL0280CommonLogic.setPulldownTrxCd(cMsg, glblCmpyCd);
        }
    }

    /**
     * doProcess_SrchPrd
     * @param cMsg NLCL0280CMsg
     */
    private void doProcess_SrchPrd(NLCL0280CMsg cMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        NLCL0280CommonLogic.setCorProdDescTxt(glblCmpyCd, cMsg);
    }

    /**
     * doProcess_SrchRtlSubWh
     * @param cMsg NLCL0280CMsg
     */
    private void doProcess_SrchRtlSubWh(NLCL0280CMsg cMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        NLCL0280CommonLogic.setRtlSubWhNm(glblCmpyCd, cMsg);
    }

    /**
     * doProcess_SrchRtlWh
     * @param cMsg NLCL0280CMsg
     */
    private void doProcess_SrchRtlWh(NLCL0280CMsg cMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        NLCL0280CommonLogic.setRtlWhNm(glblCmpyCd, cMsg);
    }

    /**
     * doProcess_SrchVnd
     * @param cMsg NLCL0280CMsg
     */
    private void doProcess_SrchVnd(NLCL0280CMsg cMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        NLCL0280CommonLogic.setVndNm(glblCmpyCd, cMsg);
    }

    /**
     * doProcess_SrchShipFrom
     * @param cMsg NLCL0280CMsg
     */
    private void doProcess_SrchShipFrom(NLCL0280CMsg cMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        String shipFromLocTpCd = null;
        // START 2019/03/08 H.Umeda [QC#30166,MOD]
        // NLCL0280CommonLogic.setShipFromToLocNm(glblCmpyCd, cMsg, cMsg.shipFromLocCustCd_H1, cMsg.dsAcctNm_H1, shipFromLocTpCd, "Ship From Location");
        Map<String, Object> map = NLCL0280CommonLogic.setShipFromToLocNm(glblCmpyCd, cMsg, cMsg.shipFromLocCustCd_H1, cMsg.dsAcctNm_H1, shipFromLocTpCd, "Ship From Location");
        if (map == null || map.isEmpty()) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_H1, (String) map.get("LOC_NM"));
        shipFromLocTpCd = (String) map.get("LOC_TP_CD");
        // END   2019/03/08 H.Umeda [QC#30166,MOD]
    }

    /**
     * doProcess_SrchShipTo
     * @param cMsg NLCL0280CMsg
     */
    private void doProcess_SrchShipTo(NLCL0280CMsg cMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        String shipToLocTpCd = null;
        // START 2019/03/08 H.Umeda [QC#30166,MOD]
        // NLCL0280CommonLogic.setShipFromToLocNm(glblCmpyCd, cMsg, cMsg.shipToLocCustCd_H1, cMsg.dsAcctNm_H2, shipToLocTpCd, "Ship To Location");
        Map<String, Object> map = NLCL0280CommonLogic.setShipFromToLocNm(glblCmpyCd, cMsg, cMsg.shipToLocCustCd_H1, cMsg.dsAcctNm_H2, shipToLocTpCd, "Ship To Location");
        if (map == null || map.isEmpty()) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_H2, (String) map.get("LOC_NM"));
        shipToLocTpCd = (String) map.get("LOC_TP_CD");
        // END   2019/03/08 H.Umeda [QC#30166,MOD]
    }

    /**
     * doProcess_SelectSearch
     * @param cMsg NLCL0280CMsg
     * @param sMsg NLCL0280SMsg
     */
    private void doProcess_SelectSearch(NLCL0280CMsg cMsg, NLCL0280SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        String usrId = getContextUserInfo().getUserId();

        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_PS)) {

            NLCL0280CommonLogic.callNszc0330forSearchSearchOption(cMsg, sMsg, usrId, glblCmpyCd);
        }

        if (ZYPCommonFunc.hasValue(cMsg.coaProdCd_H1)) {

            doProcess_SrchPrd(cMsg);
            // Don't send Error Msg
            cMsg.coaProdCd_H1.clearErrorInfo();
        }

        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd_H1)) {

            doProcess_SrchRtlWh(cMsg);
            // Don't send Error Msg
            cMsg.rtlWhCd_H1.clearErrorInfo();
        }

        if (ZYPCommonFunc.hasValue(cMsg.rtlSwhCd_H1)) {

            doProcess_SrchRtlSubWh(cMsg);
            // Don't send Error Msg
            cMsg.rtlSwhCd_H1.clearErrorInfo();
        }

        if (ZYPCommonFunc.hasValue(cMsg.vndCd_H1)) {

            doProcess_SrchVnd(cMsg);
            // Don't send Error Msg
            cMsg.vndCd_H1.clearErrorInfo();
        }

        if (ZYPCommonFunc.hasValue(cMsg.shipFromLocCustCd_H1)) {

            doProcess_SrchShipFrom(cMsg);
            // Don't send Error Msg
            cMsg.shipFromLocCustCd_H1.clearErrorInfo();
        }

        if (ZYPCommonFunc.hasValue(cMsg.shipToLocCustCd_H1)) {

            doProcess_SrchShipTo(cMsg);
            // Don't send Error Msg
            cMsg.shipToLocCustCd_H1.clearErrorInfo();
        }

        // Update Success Message.
        cMsg.setMessageInfo(NLCL0280Constant.ZZZM9003I, new String[] {"Select Search" });
    }

    /**
     * doProcess_SrchShipTo
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_CmnClear(NLCL0280CMsg cMsg, NLCL0280SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        String userId = getContextUserInfo().getUserId();

        // evacuation xxComnColOrdTxt
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();

        // Clear Msg
        cMsg.clear();
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(cMsg.L);
        ZYPTableUtil.clear(sMsg.L);

        // Set xxComnColOrdTxt
        ZYPEZDItemValueSetter.setValue(cMsg.xxComnColOrdTxt, xxComnColOrdTxt);

        // Set PullDown
        NLCL0280CommonLogic.setPulldownSaveSearch(cMsg, glblCmpyCd, userId);
        NLCL0280CommonLogic.setPulldownItemType(cMsg);
        NLCL0280CommonLogic.setPulldownMdseType(cMsg);
        NLCL0280CommonLogic.setPulldownTrxCd(cMsg, glblCmpyCd);
        NLCL0280CommonLogic.setPulldownTrxType(cMsg, glblCmpyCd);
        NLCL0280CommonLogic.setPulldownXrefType(cMsg);
        NLCL0280CommonLogic.createLocStsChkBox(cMsg, glblCmpyCd);
    }

    /**
     * doProcess_SrchShipTo
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_CmnDownload(NLCL0280CMsg cMsg, NLCL0280SMsg sMsg) {
        // Updated by DuyLe - 04/21/2023 - QC#61403 - START
        String glblCmpyCd = getGlobalCompanyCode();
        boolean hasError = checkError(glblCmpyCd, cMsg);
        boolean ordTakeMdse = getOrdTakeMdse(hasError, glblCmpyCd, cMsg);
        String shipFromLocTpCd = getShipFromLocTpCd(hasError, glblCmpyCd, cMsg);
        String shipToLocTpCd = getShipToLocTpCd(hasError, glblCmpyCd, cMsg);
        if (!hasError) {
            S21SsmEZDResult result = NLCL0280Query
                .getInstance()
                .getInvtyTrx(glblCmpyCd, cMsg, ordTakeMdse,
                    shipFromLocTpCd, shipToLocTpCd, NLCL0280Constant.LIMIT_DL_ROWNUM);
            writeCsvFile(cMsg, result);
        }
        // Updated by DuyLe - 04/21/2023 - QC#61403 - END
    }
    
    /**
     * Added by DuyLe - 04/21/2023 - QC#61403
     * writeCsvFile
     * @param cMsg
     * @param rs
     */
    private void writeCsvFile(NLCL0280CMsg cMsg, S21SsmEZDResult result) {
        // set CSV File
        cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm("NLCL0280_InventoryTransactionInqiury"), ".csv");

        // Create FMsg
        NLCL0280F00FMsg fMsg = new NLCL0280F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        // Set Header record
        csvOutFile.writeHeader(new String[] {
                "Retail Warehouse Code"
                        , "Retail Warehouse Name"
                        , "Sub Warehouse Code"
                        , "Merchandise Code"
                        , "Merchandise Description"
                        , "Merchandise Type"
                        , "Item Type"
                        , "Product"
                        , "Cross Refference"
                        , "Supplier Item"
                        , "Manufacturer Item"
                        , "Config ID"
                        , "Model name"
                        , "Serial"
                        , "Transaction ID"
                        , "Transaction Type"
                        , "Transaction Code"
                        , "Transaction Reason"
                        , "Transaction Quantity"
                        , "Stock Status"
                        , "Location Status"
                        , "Transaction Source Number"
                        , "Transaction Source Line Number"
                        , "Shippng Order Number"
                        , "Shippng Order Line Number"
                        , "RWS Number"
                        , "RWS Line Number"
                        , "Trx Party"
                        , "Trx Date and Time"
                        , "Destination Location"
                        , "Aje Link Flg"
                });

        if (!result.isCodeNormal()) {
            cMsg.setMessageInfo(NLCL0280Constant.NMAM8298E, null);
            csvOutFile.close();
            return;
        }
        List<Map<String, Object>> invtyTrxList = (ArrayList<Map<String, Object>>) result.getResultObject();

        // copy invtyTrxMap to fMsg
        for (Map<String, Object> invtyTrxMap : invtyTrxList) {
            setRsltToFMsg(fMsg, invtyTrxMap);
            csvOutFile.write();
        }

        // output
        csvOutFile.close();
    }

    /**
     * Added by DuyLe - 04/21/2023 - QC#61403
     * setRsltToFMsg
     * @param fMsg NLCL0280F00FMsg
     * @param invtyTrxMap Map<String, Object>
     */
    private void setRsltToFMsg(NLCL0280F00FMsg fMsg, Map<String, Object> invtyTrxMap) {
        ZYPEZDItemValueSetter.setValue(fMsg.rtlWhCd_A1, (String) invtyTrxMap.get("RTL_WH_CD"));
        ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm_A1, (String) invtyTrxMap.get("RTL_WH_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.rtlSwhCd_A1, (String) invtyTrxMap.get("RTL_SWH_CD"));
        ZYPEZDItemValueSetter.setValue(fMsg.mdseCd_A1, (String) invtyTrxMap.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt_A1, (String) invtyTrxMap.get("MDSE_DESC_SHORT_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_PJ, ZYPCommonFunc.concatString((String) invtyTrxMap.get("COA_PROJ_CD"), ":", (String) invtyTrxMap.get("COA_PROJ_DESC_TXT")));
        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_MD, ZYPCommonFunc.concatString((String) invtyTrxMap.get("MDSE_ITEM_TP_CD"), ":", (String) invtyTrxMap.get("MDSE_ITEM_TP_DESC_TXT")));
        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_PD, ZYPCommonFunc.concatString((String) invtyTrxMap.get("COA_PROD_CD"), ":", (String) invtyTrxMap.get("COA_PROD_DESC_TXT")));
        ZYPEZDItemValueSetter.setValue(fMsg.xxRelnTrgtFlg_A1, (String) invtyTrxMap.get("ITEM_FLIP_FLG"));
        ZYPEZDItemValueSetter.setValue(fMsg.splyItemNum_A1, (String) invtyTrxMap.get("SPLY_ITEM_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.mnfItemCd_A1, (String) invtyTrxMap.get("MNF_ITEM_CD"));
        ZYPEZDItemValueSetter.setValue(fMsg.svcConfigMstrPk_A1, (BigDecimal) invtyTrxMap.get("SVC_CONFIG_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(fMsg.t_MdlNm_A1, (String) invtyTrxMap.get("MDL_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.serNumFlg_A1, (String) invtyTrxMap.get("SER_TRX_FLG"));
        ZYPEZDItemValueSetter.setValue(fMsg.invtyTrxPk_A1, (BigDecimal) invtyTrxMap.get("INVTY_TRX_PK"));
        ZYPEZDItemValueSetter.setValue(fMsg.invtyTrxTpDescTxt_A1, (String) invtyTrxMap.get("INVTY_TRX_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_AX, ZYPCommonFunc.concatString((String) invtyTrxMap.get("TRX_CD"), ":", (String) invtyTrxMap.get("TRX_DESC_TXT")));
        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_AR, ZYPCommonFunc.concatString((String) invtyTrxMap.get("TRX_RSN_CD"), ":", (String) invtyTrxMap.get("TRX_RSN_DESC_TXT")));
        ZYPEZDItemValueSetter.setValue(fMsg.invtyTrxQty_A1, (BigDecimal) invtyTrxMap.get("INVTY_TRX_QTY"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_SS, ZYPCommonFunc.concatString((String) invtyTrxMap.get("STK_STS_CD"), ":", (String) invtyTrxMap.get("STK_STS_DESC_TXT")));
        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_AS, ZYPCommonFunc.concatString((String) invtyTrxMap.get("LOC_STS_CD"), ":", (String) invtyTrxMap.get("LOC_STS_DESC_TXT")));
        ZYPEZDItemValueSetter.setValue(fMsg.invtyTrxSlpNum_A1, (String) invtyTrxMap.get("SRC_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.dplyLineNum_A1, (String) invtyTrxMap.get("SRC_ORD_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.soNum_A1, (String) invtyTrxMap.get("SO_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.soSlpNum_A1, (String) invtyTrxMap.get("SO_SLP_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.rwsNum_A1, (String) invtyTrxMap.get("RWS_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.rwsDtlLineNum_A1, (String) invtyTrxMap.get("RWS_DTL_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_A1, (String) invtyTrxMap.get("PARTY_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxTsDsp19Txt_A1, (String) invtyTrxMap.get("EZINTIME"));
        ZYPEZDItemValueSetter.setValue(fMsg.shipToLocCustNm_A1, (String) invtyTrxMap.get("SHIP_TO_LOC_CUST_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.ajeLinkFlg_A1, (String) invtyTrxMap.get("AJE_LINK_FLG"));

        if (ZYPCommonFunc.hasValue(fMsg.xxTsDsp19Txt_A1)) {
            String dispTs = ZYPDateUtil.formatEzd17ToDisp(fMsg.xxTsDsp19Txt_A1.getValue()).substring(0, 19);
            ZYPEZDItemValueSetter.setValue(fMsg.xxTsDsp19Txt_A1, dispTs);
        }
    }

    /**
     * doProcess_CmnSort
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_CmnSort(NLCL0280CMsg cMsg, NLCL0280SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            // Copy
            int i = 0;

            for (; i < sMsg.A.getValidCount(); i++) {

                if (i == cMsg.A.length()) {

                    break;
                }

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }

            cMsg.A.setValidCount(i);

            // set Page
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        }
    }
}
