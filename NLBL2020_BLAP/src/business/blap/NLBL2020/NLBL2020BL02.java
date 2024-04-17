/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL2020;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLBL2020.common.NLBL2020CommonLogic;
import business.blap.NLBL2020.constant.NLBL2020Constant;
import business.db.OTBD_CARR_VTMsg;
import business.db.OTBD_CARR_VTMsgArray;
import business.file.NLBL2020F00FMsg;
import business.file.NLBL2020F01FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_ORD_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
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
 * Manage Shipping Orders
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   CITS            T.Tokutomi      Create          N/A
 * 2016/03/10   CITS      Takeshi Tokutomi      Update          QC#5242
 * 06/21/2016   CSAI            Y.Imazu         Update          QC#10463
 * 07/14/2016   CSAI            Y.Imazu         Update          QC#11900
 * 06/13/2017   CITS            K.Fukumura      Update          QC#19077
 * 03/06/2018   CITS            T.Tokutomi      Update          QC#21913
 * 06/19/2018   CITS            Y.Iwasaki       Update          QC#21717
 * 27/08/2019   CITS            R.Kurahashi     Update          QC#52895
 * 2022/10/18   Hitachi         A.Kohinata      Update          QC#60559
 * 2023/08/08   Hitachi         M.Kikushima     Update          QC#61677
 *</pre>
 */
public class NLBL2020BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NLBL2020_INIT".equals(screenAplID)) {
                doProcess_Init((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);
                getColData((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_Search_RtlWHInfo".equals(screenAplID)) {
                doProcess_SearchRtlWh((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_Search_RtlSWHInfo".equals(screenAplID)) {
                doProcess_SearchRtlSubWh((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_Search_MdseInfo".equals(screenAplID)) {
                doProcess_SearchMdseInfo((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_Search_CarrInfo".equals(screenAplID)) {
                doProcess_SearchCarrNm((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_Search_ShipToCustInfo".equals(screenAplID)) {
                doProcess_SearchShipToCustName((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_Select_Search".equals(screenAplID)) {
                doProcess_SelectSearch((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_Search".equals(screenAplID)) {
                doProcess_Search((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_Tab_SoList".equals(screenAplID)) {
                doProcess_TabSoList((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_Tab_PickList".equals(screenAplID)) {
                doProcess_TabPickList((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

                // Error or Warning Table ctrl
                if ("E".equals(cMsg.getMessageKind()) || "W".equals(cMsg.getMessageKind())) {

                    NLBL2020CommonLogic.viewErrorRecordPage((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);
                }

            } else if ("NLBL2020Scrn00_PageJump".equals(screenAplID)) {
                doProcess_PageJump((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_PageNext".equals(screenAplID)) {
                doProcess_PageNext((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_PagePrev((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_TblColumnSort((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_Clear((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_Init((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);
                getColData((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_Print".equals(screenAplID)) {
                //no process.

            } else if ("NLBL2020Scrn00_OnChange_ChkBoxHdr".equals(screenAplID)) {
                doProcess_ChkBoxHdr((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_OnChange_ChkBoxLine".equals(screenAplID)) {
                doProcess_ChkBoxLine((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_OnClick_Apply".equals(screenAplID)) {
                doProcess_OnClick_Apply((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_OpenWin_ShpgInstn".equals(screenAplID)) {
                doProcess_OpenWin_ShpgInstn((NLBL2020CMsg) cMsg);

                // After update process
            } else if ("NLBL2020Scrn00_Cancel".equals(screenAplID)) {
                doProcess_Cancel((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_SO_Close".equals(screenAplID)) {
                doProcess_SoClose((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_Ship".equals(screenAplID)) {
                doProcess_Ship((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_Submit((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            // No process
            } else if ("NLBL2020Scrn00_CMN_ColClear".equals(screenAplID)) {
                doProcess_NNLBL2020Scrn00_CMN_ColClear((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_CMN_ColSave".equals(screenAplID)) {
                doProcess_NLBL2020Scrn00_CMN_ColSave((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_OpenWin_Tracking".equals(screenAplID)) {
                doProcess_NLBL2020Scrn00_OpenWin_Tracking((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020_NLBL3170".equals(screenAplID)) {
                doProcess_NLBL2020_NLBL3170((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);
            } else if ("NLBL2020Scrn00_Select_All".equals(screenAplID)) {
                doProcess_Select_All((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);
            } else if ("NLBL2020Scrn00_UnSelect_All".equals(screenAplID)) {
                doProcess_UnSelect_All((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);
            // add start 2022/10/18 QC#60559
            } else if ("NLBL2020Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NLBL2020Scrn00_CMN_Download((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);
            // add end 2022/10/18 QC#60559
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_Clear
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_Clear(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        String col = cMsg.xxComnColOrdTxt.getValue();

        cMsg.clear();
        sMsg.clear();

        // Initialize
        NLBL2020CommonLogic.clearAllTbl(cMsg, sMsg);
        NLBL2020CommonLogic.setCommonValues(cMsg, sMsg, getGlobalCompanyCode(), getContextUserInfo().getUserId());

        // PullDown
        NLBL2020CommonLogic.setPulldownSaveSearch(cMsg);
        NLBL2020CommonLogic.setPulldownOrderLineStatus(cMsg);
        NLBL2020CommonLogic.setPulldownSceOrdType(cMsg);
        NLBL2020CommonLogic.setPulldownShippingSearviceLevel(cMsg);
        NLBL2020CommonLogic.setPulldownTimeAmPm(cMsg);

        ZYPEZDItemValueSetter.setValue(cMsg.xxComnColOrdTxt, col);
    }

    /**
     * doProcess_Init
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_Init(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        // Initialize
        NLBL2020CommonLogic.clearAllTbl(cMsg, sMsg);
        NLBL2020CommonLogic.setCommonValues(cMsg, sMsg, getGlobalCompanyCode(), getContextUserInfo().getUserId());

        // PullDown
        NLBL2020CommonLogic.setPulldownSaveSearch(cMsg);
        NLBL2020CommonLogic.setPulldownOrderLineStatus(cMsg);
        NLBL2020CommonLogic.setPulldownSceOrdType(cMsg);
        NLBL2020CommonLogic.setPulldownShippingSearviceLevel(cMsg);
        NLBL2020CommonLogic.setPulldownTimeAmPm(cMsg);

        // Search
        if (ZYPCommonFunc.hasValue(cMsg.trxHdrNum_H1) || ZYPCommonFunc.hasValue(cMsg.soNum_H1) || ZYPCommonFunc.hasValue(cMsg.xxRtrnInvtyLocSrchTxt_RW)
                || ZYPCommonFunc.hasValue(cMsg.xxRtrnInvtyLocSrchTxt_SW) || ZYPCommonFunc.hasValue(cMsg.shipToCustCd_H1) || ZYPCommonFunc.hasValue(cMsg.carrCd_H1)) {

            doProcess_Search(cMsg, sMsg);
        }
    }

    /**
     * getColData
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private static void getColData(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        ZYPGUITableColumn.getColData((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg, "AHEAD");
    }

    /**
     * doProcess_SearchRtlWh
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_SearchRtlWh(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        cMsg.rtlWhNm_H1.clear();

        List<String> srchRtlWhList = NLBL2020CommonLogic.splitSrchTxt(cMsg.xxRtrnInvtyLocSrchTxt_RW);

        if (srchRtlWhList != null && !srchRtlWhList.isEmpty()) {

            if (srchRtlWhList.size() == 1 && srchRtlWhList.get(0).indexOf("%") == -1) {

                String rtlWhName = NLBL2020CommonLogic.getRtlWhName(cMsg);

                if (ZYPCommonFunc.hasValue(rtlWhName)) {

                    ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_H1, rtlWhName);
                    return;
                }

            } else {

                Map<String, Object> params = new HashMap<String, Object>();
                params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
                params.put("whCdList", srchRtlWhList);

                S21SsmEZDResult ssmResult = NLBL2020Query.getInstance().getRtlWhList(params);

                if (ssmResult.isCodeNormal()) {

                    List<Map<String, Object>> rtlWhMapList = (ArrayList<Map<String, Object>>) ssmResult.getResultObject();

                    if (rtlWhMapList != null && !rtlWhMapList.isEmpty()) {

                        if (rtlWhMapList.size() > 1) {

                            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_H1, "Multiple");
                            return;

                        } else {

                            ZYPEZDItemValueSetter.setValue(cMsg.xxRtrnInvtyLocSrchTxt_RW, (String) rtlWhMapList.get(0).get("RTL_WH_CD"));
                            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_H1, (String) rtlWhMapList.get(0).get("RTL_WH_NM"));
                            return;
                        }
                    }
                }
            }
        }

        cMsg.xxRtrnInvtyLocSrchTxt_RW.setErrorInfo(1, NLBL2020Constant.NLZM2278E, new String[] {"Source WH" });
        cMsg.setMessageInfo(NLBL2020Constant.ZZM9037E);
    }

    /**
     * doProcess_SearchRtlSubWh
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_SearchRtlSubWh(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        cMsg.rtlSwhNm_H1.clear();

        List<String> srchRtlSwhList = NLBL2020CommonLogic.splitSrchTxt(cMsg.xxRtrnInvtyLocSrchTxt_SW);

        if (srchRtlSwhList != null && !srchRtlSwhList.isEmpty()) {

            if (srchRtlSwhList.size() == 1 && srchRtlSwhList.get(0).indexOf("%") == -1) {

                String rtlSubWhName = NLBL2020CommonLogic.getRtlSubWhName(cMsg);

                if (ZYPCommonFunc.hasValue(rtlSubWhName)) {

                    ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_H1, rtlSubWhName);
                    return;
                }

            } else {

                Map<String, Object> params = new HashMap<String, Object>();
                params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
                params.put("swhCdList", srchRtlSwhList);

                S21SsmEZDResult ssmResult = NLBL2020Query.getInstance().getRtlSwhList(params);

                if (ssmResult.isCodeNormal()) {

                    List<Map<String, Object>> rtlSwhMapList = (ArrayList<Map<String, Object>>) ssmResult.getResultObject();

                    if (rtlSwhMapList != null && !rtlSwhMapList.isEmpty()) {

                        if (rtlSwhMapList.size() > 1) {

                            ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_H1, "Multiple");
                            return;

                        } else {

                            ZYPEZDItemValueSetter.setValue(cMsg.xxRtrnInvtyLocSrchTxt_SW, (String) rtlSwhMapList.get(0).get("RTL_SWH_CD"));
                            ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_H1, (String) rtlSwhMapList.get(0).get("RTL_SWH_NM"));
                            return;
                        }
                    }
                }
            }
        }

        cMsg.xxRtrnInvtyLocSrchTxt_SW.setErrorInfo(1, NLBL2020Constant.NLZM2278E, new String[] {"Source SWH" });
        cMsg.setMessageInfo(NLBL2020Constant.ZZM9037E);
    }

    /**
     * doProcess_SearchMdseInfo
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_SearchMdseInfo(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        cMsg.mdseDescShortTxt_H1.clear();

        List<String> srchMdseList = NLBL2020CommonLogic.splitSrchTxt(cMsg.mdseCd_H1);

        if (srchMdseList != null && !srchMdseList.isEmpty()) {

            if (srchMdseList.size() == 1 && srchMdseList.get(0).indexOf("%") == -1) {

                String mdseDescShortTxt = NLBL2020CommonLogic.getMdseName(cMsg);

                if (ZYPCommonFunc.hasValue(mdseDescShortTxt)) {

                    ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt_H1, mdseDescShortTxt);
                    return;
                }

            } else {

                Map<String, Object> params = new HashMap<String, Object>();
                params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
                params.put("mdseCdList", srchMdseList);

                S21SsmEZDResult ssmResult = NLBL2020Query.getInstance().getMdseList(params);

                if (ssmResult.isCodeNormal()) {

                    List<Map<String, Object>> mdseMapList = (ArrayList<Map<String, Object>>) ssmResult.getResultObject();

                    if (mdseMapList != null && !mdseMapList.isEmpty()) {

                        if (mdseMapList.size() > 1) {

                            ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt_H1, "Multiple");
                            return;

                        } else {

                            ZYPEZDItemValueSetter.setValue(cMsg.mdseCd_H1, (String) mdseMapList.get(0).get("MDSE_CD"));
                            ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt_H1, (String) mdseMapList.get(0).get("MDSE_DESC_SHORT_TXT"));
                            return;
                        }
                    }
                }
            }
        }

        cMsg.mdseCd_H1.setErrorInfo(1, NLBL2020Constant.NLZM2278E, new String[] {"Item Number" });
        cMsg.setMessageInfo(NLBL2020Constant.ZZM9037E);
    }

    /**
     * doProcess_SearchShipToCustName
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_SearchShipToCustName(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        cMsg.dsAcctNm_H1.clear();

        List<String> srchShipToList = NLBL2020CommonLogic.splitSrchTxt(cMsg.shipToCustCd_H1);

        if (srchShipToList != null && !srchShipToList.isEmpty()) {

            if (srchShipToList.size() == 1 && srchShipToList.get(0).indexOf("%") == -1) {

                String shipToCustNm = NLBL2020CommonLogic.getShipToCustName(cMsg);

                if (ZYPCommonFunc.hasValue(shipToCustNm)) {

                    ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_H1, shipToCustNm);
                    return;
                }

            } else {

                Map<String, Object> params = new HashMap<String, Object>();
                params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
                params.put("shipToCdList", srchShipToList);
                params.put("locTpCust", LOC_TP.CUSTOMER);

                S21SsmEZDResult ssmResult = NLBL2020Query.getInstance().getShipToList(params);

                if (ssmResult.isCodeNormal()) {

                    List<Map<String, Object>> shipToMapList = (ArrayList<Map<String, Object>>) ssmResult.getResultObject();

                    if (shipToMapList != null && !shipToMapList.isEmpty()) {

                        if (shipToMapList.size() > 1) {

                            ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_H1, "Multiple");
                            return;

                        } else {

                            ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd_H1, (String) shipToMapList.get(0).get("LOC_CD"));
                            ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_H1, (String) shipToMapList.get(0).get("LOC_NM"));
                            return;
                        }
                    }
                }
            }
        }

        cMsg.shipToCustCd_H1.setErrorInfo(1, NLBL2020Constant.NLZM2278E, new String[] {"Ship to Customer" });
        cMsg.setMessageInfo(NLBL2020Constant.ZZM9037E);
    }

    /**
     * doProcess_SearchCarrNm
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_SearchCarrNm(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        cMsg.carrNm_H1.clear();

        List<String> srchCarrList = NLBL2020CommonLogic.splitSrchTxt(cMsg.carrCd_H1);

        if (srchCarrList != null && !srchCarrList.isEmpty()) {

            if (srchCarrList.size() == 1 && srchCarrList.get(0).indexOf("%") == -1) {

                String carrNm = NLBL2020CommonLogic.getCarrName(cMsg);

                if (ZYPCommonFunc.hasValue(carrNm)) {

                    ZYPEZDItemValueSetter.setValue(cMsg.carrNm_H1, carrNm);
                    return;
                }

            } else {

                Map<String, Object> params = new HashMap<String, Object>();
                params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
                params.put("carrCdList", srchCarrList);

                S21SsmEZDResult ssmResult = NLBL2020Query.getInstance().getCarrList(params);

                if (ssmResult.isCodeNormal()) {

                    List<Map<String, Object>> carrMapList = (ArrayList<Map<String, Object>>) ssmResult.getResultObject();

                    if (carrMapList != null && !carrMapList.isEmpty()) {

                        if (carrMapList.size() > 1) {

                            ZYPEZDItemValueSetter.setValue(cMsg.carrNm_H1, "Multiple");
                            return;

                        } else {

                            ZYPEZDItemValueSetter.setValue(cMsg.carrCd_H1, (String) carrMapList.get(0).get("CARR_CD"));
                            ZYPEZDItemValueSetter.setValue(cMsg.carrNm_H1, (String) carrMapList.get(0).get("CARR_NM"));
                            return;
                        }
                    }
                }
            }
        }

        cMsg.carrCd_H1.setErrorInfo(1, NLBL2020Constant.NLZM2278E, new String[] {"Carrier" });
        cMsg.setMessageInfo(NLBL2020Constant.ZZM9037E);
    }

    /**
     * doProcess_SelectSearch
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_SelectSearch(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_PS)) {

            NLBL2020CommonLogic.callNszc0330forSearchSearchOption(cMsg, sMsg);
        }
    }

    /**
     * doProcess_Search
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_Search(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);

        if (!chkSearchSoList(cMsg, sMsg)) {

            return;
        }

        // Clear Table
        NLBL2020CommonLogic.clearAllTbl(cMsg, sMsg);

        // Search
        Map<String, Object> ssmParam = NLBL2020CommonLogic.setSearchSoListParam(cMsg, sMsg);
        NLBL2020Query.getInstance().searchSoList(ssmParam, cMsg, sMsg);


        // 1 page copy
        int i = 0;

        for (; i < sMsg.A.getValidCount(); i++) {

            if (i == cMsg.A.length()) {

                break;
            }

            EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
        }

        cMsg.A.setValidCount(i);

        // set Pagenation
        cMsg.xxPageShowFromNum_A.setValue(1);
        cMsg.xxPageShowToNum_A.setValue(cMsg.A.getValidCount());
    }

    /**
     * chkSearchSoList
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     * @return true : OK, false : NG
     */
    private boolean chkSearchSoList(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        // Exist check Warehouse
        if (ZYPCommonFunc.hasValue(cMsg.xxRtrnInvtyLocSrchTxt_RW) && ZYPCommonFunc.hasValue(cMsg.xxRtrnInvtyLocSrchTxt_SW)) {

            doProcess_SearchRtlWh(cMsg, sMsg);
            doProcess_SearchRtlSubWh(cMsg, sMsg);

            if (!cMsg.xxRtrnInvtyLocSrchTxt_RW.isError() && !cMsg.xxRtrnInvtyLocSrchTxt_SW.isError()) {

                chkInvtyLoc(cMsg, sMsg);
            }

        } else {

            if (ZYPCommonFunc.hasValue(cMsg.xxRtrnInvtyLocSrchTxt_RW)) {

                doProcess_SearchRtlWh(cMsg, sMsg);
            }

            if (ZYPCommonFunc.hasValue(cMsg.xxRtrnInvtyLocSrchTxt_SW)) {

                doProcess_SearchRtlSubWh(cMsg, sMsg);
            }
        }

        // Exist check Ship To Customer
        if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd_H1)) {

            doProcess_SearchShipToCustName(cMsg, sMsg);
        }

        // Exist check Carrier
        if (ZYPCommonFunc.hasValue(cMsg.carrCd_H1)) {

            doProcess_SearchCarrNm(cMsg, sMsg);
        }

        // Exist check Mdse
        if (ZYPCommonFunc.hasValue(cMsg.mdseCd_H1)) {

            doProcess_SearchMdseInfo(cMsg, sMsg);
        }

        // Exist Error
        if (cMsg.xxRtrnInvtyLocSrchTxt_RW.isError() || cMsg.xxRtrnInvtyLocSrchTxt_SW.isError() || cMsg.shipToCustCd_H1.isError()
                || cMsg.carrCd_H1.isError() || cMsg.mdseCd_H1.isError()) {

            return false;
        }

        return true;
    }

    /**
     * chkInvtyLoc
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void chkInvtyLoc(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        List<String> srchRtlWhList = NLBL2020CommonLogic.splitSrchTxt(cMsg.xxRtrnInvtyLocSrchTxt_RW);
        List<String> srchRtlSwhList = NLBL2020CommonLogic.splitSrchTxt(cMsg.xxRtrnInvtyLocSrchTxt_SW);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("whCdList", srchRtlWhList);
        params.put("swhCdList", srchRtlSwhList);

        S21SsmEZDResult ssmResult = NLBL2020Query.getInstance().getInvtyLocCnt(params);

        if (ssmResult.isCodeNormal()) {

            BigDecimal invtyLocCnt = (BigDecimal) ssmResult.getResultObject();

            if (invtyLocCnt != null && invtyLocCnt.compareTo(BigDecimal.ZERO) > 0) {

                return;
            }
        }

        cMsg.xxRtrnInvtyLocSrchTxt_RW.setErrorInfo(1, NLBL2020Constant.NLZM2279E, new String[] {"Source WH", "Source SWH" });
        cMsg.xxRtrnInvtyLocSrchTxt_SW.setErrorInfo(1, NLBL2020Constant.NLZM2279E, new String[] {"Source WH", "Source SWH" });
    }

    /**
     * doProcess_TabSoList
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_TabSoList(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(sMsg.xxModeCd_TB) //
                && ZYPConstant.FLG_ON_Y.equals(sMsg.xxModeCd_TB.getValue())) {

            // Copy Header
            NLBL2020CommonLogic.copyHeaderSMsgToCMsg(cMsg, sMsg);

            // tab control
            ZYPEZDItemValueSetter.setValue(sMsg.xxModeCd_TB, ZYPConstant.FLG_OFF_N);

            // Table Clear
            ZYPTableUtil.clear(cMsg.A);
            cMsg.xxPageShowFromNum_A.clear();
            cMsg.xxPageShowToNum_A.clear();
            cMsg.xxPageShowOfNum_A.clear();

            doProcess_Search((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);
            getColData((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);
        }
    }

    /**
     * doProcess_TabPickList
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_TabPickList(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
        NLBL2020CommonLogic.saveCurrentPageToSMsgSoList(cMsg, sMsg);
        // Copy Header
        NLBL2020CommonLogic.copyHeaderCMsgToSMsg(cMsg, sMsg);
        ZYPTableUtil.clear(sMsg.B);

        ArrayList<String> soNumList = new ArrayList<String>();
        ArrayList<String> soNumSlpNumList = new ArrayList<String>();
        String headNum = "";
        BigDecimal pickSvcConfigMstrPk = BigDecimal.ZERO;

        // Role Function
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NLBL2020Constant.BUSINESS_ID);

        boolean errFlg = false;

        if (!NLBL2020CommonLogic.chkSoLineCheck(cMsg, sMsg)) {
            errFlg = true;
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            NLBL2020_ASMsg sMsgALine = sMsg.A.no(i);

            if (ZYPCommonFunc.hasValue(sMsgALine.xxExstFlg_A1)) {

                // Line status check
                if (ZYPConstant.FLG_OFF_N.equals(sMsgALine.soLineOpenFlg_AH.getValue())) {
                    sMsgALine.xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLZM2283E);
                    errFlg = true;
                }

                // WH Permission check
                if (!NLBL2020CommonLogic.isWarehousePermission(cMsg, sMsgALine.shipFromRtlWhCd_AH.getValue(), functionList)) {
                    sMsgALine.xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLZM2313E);
                    errFlg = true;
                    continue;
                }

                soNumSlpNumList.add(sMsgALine.soNum_HI.getValue() + sMsgALine.soSlpNum_HI.getValue());
                headNum = sMsgALine.soNum_HI.getValue();

                if (!soNumList.contains(sMsgALine.soNum_HI.getValue())) {

                    soNumList.add(sMsgALine.soNum_HI.getValue());
                }

            } else if (ZYPCommonFunc.hasValue(sMsgALine.xxExstFlg_A2)) {

                // Line status check
                if (ZYPConstant.FLG_OFF_N.equals(sMsgALine.soLineOpenFlg_AH.getValue())) {
                    sMsgALine.xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLZM2283E);
                    errFlg = true;
                }

                // WH Permission check
                if (!NLBL2020CommonLogic.isWarehousePermission(cMsg, sMsgALine.shipFromRtlWhCd_AH.getValue(), functionList)) {
                    sMsgALine.xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLZM2313E);
                    errFlg = true;
                    continue;
                }

                soNumSlpNumList.add(sMsgALine.soNum_HI.getValue() + sMsgALine.soSlpNum_HI.getValue());

                if (!soNumList.contains(sMsgALine.soNum_HI.getValue())) {

                    soNumList.add(sMsgALine.soNum_HI.getValue());
                }

            } else if (!ZYPCommonFunc.hasValue(sMsgALine.xxExstFlg_A1) && ZYPCommonFunc.hasValue(sMsgALine.soNum_A1.getValue())) {

                headNum = "";

            } else if (ZYPCommonFunc.hasValue(headNum) && !ZYPCommonFunc.hasValue(sMsgALine.soNum_A1.getValue())) {

                // Line status check
                if (ZYPConstant.FLG_OFF_N.equals(sMsgALine.soLineOpenFlg_AH.getValue())) {
                    sMsgALine.xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLZM2283E);
                    errFlg = true;
                }

                // WH Permission check
                if (!NLBL2020CommonLogic.isWarehousePermission(cMsg, sMsgALine.shipFromRtlWhCd_AH.getValue(), functionList)) {
                    sMsgALine.xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLZM2313E);
                    errFlg = true;
                    continue;
                }

                soNumSlpNumList.add(headNum + sMsgALine.soSlpNum_HI.getValue());
            }

            // Selection check for config items
            if (ZYPCommonFunc.hasValue(sMsgALine.pickSvcConfigMstrPk_A1) && !pickSvcConfigMstrPk.equals(sMsgALine.pickSvcConfigMstrPk_A1.getValue())) {
                pickSvcConfigMstrPk = sMsgALine.pickSvcConfigMstrPk_A1.getValue();

                // Find first pick config line
                int firstLine = i - 1;
                for (; firstLine >= 0; --firstLine) {
                    BigDecimal curPickConfigPk = sMsg.A.no(firstLine).pickSvcConfigMstrPk_A1.getValue();
                    if (curPickConfigPk == null || pickSvcConfigMstrPk.compareTo(curPickConfigPk) != 0) {
                        break;
                    }
                }
                ++firstLine;
                // Find un-selected items from same pick config lines
                for (int j = firstLine; j < sMsg.A.getValidCount(); j++) {
                    NLBL2020_ASMsg sMsgALine2 = sMsg.A.no(j);
                    BigDecimal curPickConfigPk = sMsgALine2.pickSvcConfigMstrPk_A1.getValue();
                    if (curPickConfigPk == null || pickSvcConfigMstrPk.compareTo(curPickConfigPk) != 0) {
                        break;
                    }

                    if (!ZYPCommonFunc.hasValue(sMsgALine2.xxExstFlg_A2)) {
                        sMsgALine2.xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLZM2284E, new String[] {pickSvcConfigMstrPk.toPlainString() });
                        errFlg = true;
                    }
                }
            }
        }

        if(errFlg){
            //Error
            cMsg.setMessageInfo(NLBL2020Constant.ZZM9037E);
            return;
        }

        if (!soNumSlpNumList.isEmpty()) {

            HashMap<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
            ssmParam.put("soNumList", soNumList);
            ssmParam.put("soNumSlpNumList", soNumSlpNumList);

            S21SsmEZDResult result = NLBL2020Query.getInstance().searchPickList(ssmParam);

            if (result.isCodeNormal()) {

                NLBL2020CommonLogic.setSearchPickListRslt(result, cMsg, sMsg);
                return;
            }
        }

        cMsg.xxPageShowFromNum_B.clear();
        cMsg.xxPageShowToNum_B.clear();
        cMsg.xxPageShowOfNum_B.clear();
        cMsg.setMessageInfo(NLBL2020Constant.NZZM0000E);
    }

    /**
     * doProcess_PageJump
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_PageJump(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        if (NLBL2020Constant.TAB_SO_LIST.equals(cMsg.xxDplyTab.getValue())) {

            // copy data from CMsg onto SMsg
            NLBL2020CommonLogic.saveCurrentPageToSMsgSoList(cMsg, sMsg);
            ZYPTableUtil.clear(cMsg.A);

            // copy data from SMsg onto CMsg
            int pagenationFrom = cMsg.A.length() * (cMsg.xxPageShowCurNum_A.getValueInt() - 1);
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
            cMsg.xxPageShowFromNum_A.setValue(pagenationFrom + 1);
            cMsg.xxPageShowToNum_A.setValue(pagenationFrom + cMsg.A.getValidCount());

        } else {

            // copy data from CMsg onto SMsg
            NLBL2020CommonLogic.saveCurrentPageToSMsgPickList(cMsg, sMsg);
            ZYPTableUtil.clear(cMsg.B);

            // copy data from SMsg onto CMsg
            int pagenationFrom = cMsg.B.length() * (cMsg.xxPageShowCurNum_B.getValueInt() - 1);
            int i = pagenationFrom;

            for (; i < pagenationFrom + cMsg.B.length(); i++) {

                if (i < sMsg.B.getValidCount()) {

                    EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i - pagenationFrom), null);

                } else {

                    break;
                }
            }

            cMsg.B.setValidCount(i - pagenationFrom);

            // set value to pagenation items
            cMsg.xxPageShowFromNum_B.setValue(pagenationFrom + 1);
            cMsg.xxPageShowToNum_B.setValue(pagenationFrom + cMsg.B.getValidCount());
        }
    }

    /**
     * doProcess_PageNext
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_PageNext(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        if (NLBL2020Constant.TAB_SO_LIST.equals(cMsg.xxDplyTab.getValue())) {

            // copy data from CMsg onto SMsg
            NLBL2020CommonLogic.saveCurrentPageToSMsgSoList(cMsg, sMsg);
            ZYPTableUtil.clear(cMsg.A);

            // copy data from SMsg onto CMsg
            int pagenationFrom = cMsg.xxPageShowToNum_A.getValueInt();
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
            cMsg.xxPageShowFromNum_A.setValue(pagenationFrom + 1);
            cMsg.xxPageShowToNum_A.setValue(pagenationFrom + cMsg.A.getValidCount());

        } else {

            // copy data from CMsg onto SMsg
            NLBL2020CommonLogic.saveCurrentPageToSMsgPickList(cMsg, sMsg);
            ZYPTableUtil.clear(cMsg.B);

            // copy data from SMsg onto CMsg
            int pagenationFrom = cMsg.xxPageShowToNum_B.getValueInt();
            int i = pagenationFrom;

            for (; i < pagenationFrom + cMsg.B.length(); i++) {

                if (i < sMsg.B.getValidCount()) {

                    EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i - pagenationFrom), null);

                } else {

                    break;
                }
            }

            cMsg.B.setValidCount(i - pagenationFrom);

            // set value to pagenation items
            cMsg.xxPageShowFromNum_B.setValue(pagenationFrom + 1);
            cMsg.xxPageShowToNum_B.setValue(pagenationFrom + cMsg.B.getValidCount());
        }
    }

    /**
     * doProcess_PagePrev
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_PagePrev(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        if (NLBL2020Constant.TAB_SO_LIST.equals(cMsg.xxDplyTab.getValue())) {

            // copy data from CMsg onto SMsg
            NLBL2020CommonLogic.saveCurrentPageToSMsgSoList(cMsg, sMsg);
            ZYPTableUtil.clear(cMsg.A);

            // copy data from SMsg onto CMsg
            int pagenationFrom = cMsg.xxPageShowFromNum_A.getValueInt() - cMsg.A.length() - 1;
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
            cMsg.xxPageShowFromNum_A.setValue(pagenationFrom);
            cMsg.xxPageShowToNum_A.setValue(pagenationFrom + cMsg.A.getValidCount() - 1);

        } else {

            // copy data from CMsg onto SMsg
            NLBL2020CommonLogic.saveCurrentPageToSMsgPickList(cMsg, sMsg);
            ZYPTableUtil.clear(cMsg.B);

            // copy data from SMsg onto CMsg
            int pagenationFrom = cMsg.xxPageShowFromNum_B.getValueInt() - cMsg.B.length() - 1;
            int i = pagenationFrom;

            for (; i < pagenationFrom + cMsg.B.length(); i++) {

                if (i < sMsg.B.getValidCount()) {

                    EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i - pagenationFrom), null);

                } else {

                    break;
                }
            }

            cMsg.B.setValidCount(i - pagenationFrom);

            // set value to pagenation items
            pagenationFrom = pagenationFrom + 1;
            cMsg.xxPageShowFromNum_B.setValue(pagenationFrom);
            cMsg.xxPageShowToNum_B.setValue(pagenationFrom + cMsg.B.getValidCount() - 1);
        }
    }

    /**
     * doProcess_TblColumnSort
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_TblColumnSort(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        if (NLBL2020Constant.TAB_SO_LIST.equals(cMsg.xxDplyTab.getValue())) {

            if ("A".equals(sortTblNm)) {

                // execute column sort function
                S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
                S21SortKey sortKey = new S21SortKey();
                sortKey.add(sortItemNm, sortOrdBy);
                S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

                // Copy（SMsg -> CMsg）
                int i = 0;
                for (; i < sMsg.A.getValidCount(); i++) {
                    if (i == cMsg.A.length()) {
                        break;
                    }
                    EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
                }
                cMsg.A.setValidCount(i);

                // Set Paging
                cMsg.xxPageShowFromNum_A.setValue(1);
                cMsg.xxPageShowToNum_A.setValue(cMsg.A.getValidCount());
            }

        } else {

            if ("B".equals(sortTblNm)) {

                // execute column sort function
                S21SortTarget sortTarget = new S21SortTarget(sMsg.B, sMsg.B.no(0).getBaseContents());
                S21SortKey sortKey = new S21SortKey();
                sortKey.add(sortItemNm, sortOrdBy);
                S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.B.getValidCount());

                // Copy（SMsg -> CMsg）
                int i = 0;

                for (; i < sMsg.B.getValidCount(); i++) {

                    if (i == cMsg.B.length()) {

                        break;
                    }

                    EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
                }

                cMsg.B.setValidCount(i);

                // Set Paging
                cMsg.xxPageShowFromNum_B.setValue(1);
                cMsg.xxPageShowToNum_B.setValue(cMsg.B.getValidCount());

                // Backup Tbl C
                S21SortTarget sortTargetC = new S21SortTarget(sMsg.C, sMsg.C.no(0).getBaseContents());
                S21SortKey sortKeyC = new S21SortKey();
                sortKeyC.add(sortItemNm.replace("_B1", "_C1"), sortOrdBy.replace("_B1", "_C1"));
                S21EZDMsgArraySort.sort(sortTargetC, sortKeyC, 0, sMsg.C.getValidCount());
            }
        }
    }

    /**
     * doProcess_Cancel (After Update)
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_Cancel(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        // If update Program success, re-Search Table Data.
        if (!"E".equals(cMsg.getMessageKind()) && !"W".equals(cMsg.getMessageKind())) {

            reSearchSoList(cMsg, sMsg);
        }
    }

    /**
     * doProcess_SoClose (After Update)
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_SoClose(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        // If update Program success, re-Search Table Data.
        if (!"E".equals(cMsg.getMessageKind())) {

            reSearchSoList(cMsg, sMsg);
        }
    }

    /**
     * doProcess_Ship (After Update)
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_Ship(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        // If update Program success, re-Search Table Data.
        if (!"E".equals(cMsg.getMessageKind())
                && !("W".equals(cMsg.getMessageKind()) && ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg.getValue()))) {

            reSearchSoList(cMsg, sMsg);
        }
    }

    /**
     * reSearchSoList
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void reSearchSoList(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        EZDMessageInfo msgInfo =  cMsg.getMessageInfo();

        Map<String, Object> ssmParam = NLBL2020CommonLogic.setReSearchSoListParam(cMsg, sMsg);
        NLBL2020CommonLogic.clearAllTbl(cMsg, sMsg);

        NLBL2020Query.getInstance().searchSoList(ssmParam, cMsg, sMsg);

        // 1 page copy
        int i = 0;

        for (; i < sMsg.A.getValidCount(); i++) {

            if (i == cMsg.A.length()) {

                break;
            }

            EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
        }

        cMsg.A.setValidCount(i);

        // set Pagenation
        cMsg.xxPageShowFromNum_A.setValue(1);
        cMsg.xxPageShowToNum_A.setValue(cMsg.A.getValidCount());

        if (msgInfo != null) {

            cMsg.setMessageInfo(msgInfo.getCode(), msgInfo.getParameter());
        }
    }

    /**
     * doProcess_Submit (After Update)
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_Submit(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        // If update Program success, re-Search Table Data.
        if (!"E".equals(cMsg.getMessageKind())
                && !("W".equals(cMsg.getMessageKind()) && ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg.getValue()))) {

            EZDMessageInfo msgInfo =  cMsg.getMessageInfo();

            List<String> soNumList = new ArrayList<String>();
            List<String> soNumSlpNumList = new ArrayList<String>();

            for (int i = 0; i < sMsg.B.getValidCount(); i++) {

                if (ZYPCommonFunc.hasValue(sMsg.B.no(i).soNum_HD) && ZYPCommonFunc.hasValue(sMsg.B.no(i).soSlpNum_HD)) {

                    if (soNumList.isEmpty() || !soNumList.contains(sMsg.B.no(i).soNum_HD.getValue())) {

                        soNumList.add(sMsg.B.no(i).soNum_HD.getValue());
                    }

                    String soNumSoSlipNum = sMsg.B.no(i).soNum_HD.getValue().concat(sMsg.B.no(i).soSlpNum_HD.getValue());

                    if (soNumSlpNumList.isEmpty() || !soNumSlpNumList.contains(soNumSoSlipNum)) {

                        soNumSlpNumList.add(soNumSoSlipNum);
                    }
                }
            }

            ZYPTableUtil.clear(sMsg.B);

            HashMap<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
            ssmParam.put("soNumList", soNumList);
            ssmParam.put("soNumSlpNumList", soNumSlpNumList);

            // Search
            S21SsmEZDResult result = NLBL2020Query.getInstance().searchPickList(ssmParam);

            if (result.isCodeNormal()) {

                NLBL2020CommonLogic.setSearchPickListRslt(result, cMsg, sMsg);

            } else {

                cMsg.xxPageShowFromNum_B.clear();
                cMsg.xxPageShowToNum_B.clear();
                cMsg.xxPageShowOfNum_B.clear();
                cMsg.setMessageInfo(NLBL2020Constant.ZZZM9005W);
            }

            if (msgInfo != null) {

                cMsg.setMessageInfo(msgInfo.getCode(), msgInfo.getParameter());
            }
        }
    }

    /**
     * doProcess_ChkBoxHdr
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_ChkBoxHdr(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        int eventLine = cMsg.xxNum_EV.getValueInt();
        String val = cMsg.A.no(eventLine).xxExstFlg_A1.getValue();
        String soNum = cMsg.A.no(eventLine).soNum_HI.getValue();

        for (int i = eventLine; i < cMsg.A.getValidCount(); i++) {

            NLBL2020_ACMsg cMsgALine = cMsg.A.no(i);

            if (!soNum.equals(cMsgALine.soNum_HI.getValue())) {
                break;
            }

            ZYPEZDItemValueSetter.setValue(cMsgALine.xxExstFlg_A1, val);
            ZYPEZDItemValueSetter.setValue(cMsgALine.xxExstFlg_A2, val);
        }

        int pageNum = (cMsg.xxPageShowFromNum_A.getValueInt() - 1) + eventLine;

        for (int i = pageNum; i < sMsg.A.getValidCount(); i++) {

            NLBL2020_ASMsg sMsgALine = sMsg.A.no(i);

            if (!soNum.equals(sMsgALine.soNum_HI.getValue())) {
                break;
            }

            ZYPEZDItemValueSetter.setValue(sMsgALine.xxExstFlg_A1, val);
            ZYPEZDItemValueSetter.setValue(sMsgALine.xxExstFlg_A2, val);
        }

        // SO may continue from last page.
        for (int i = pageNum; i >= 0; --i) {

            NLBL2020_ASMsg sMsgALine = sMsg.A.no(i);

            if (!soNum.equals(sMsgALine.soNum_HI.getValue())) {
                break;
            }

            ZYPEZDItemValueSetter.setValue(sMsgALine.xxExstFlg_A1, val);
            ZYPEZDItemValueSetter.setValue(sMsgALine.xxExstFlg_A2, val);
        }
    }

    /**
     * doProcess_ChkBoxLine
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_ChkBoxLine(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        int eventLine = cMsg.xxNum_EV.getValueInt();
        String val = cMsg.A.no(eventLine).xxExstFlg_A2.getValue();
        int sLineNum = (cMsg.xxPageShowFromNum_A.getValueInt() - 1) + eventLine;

        // ON --> OFF
        if (!ZYPConstant.FLG_ON_Y.equals(val)) {
            String preSoNum = sMsg.A.no(sLineNum).soNum_HI.getValue();

            sMsg.A.no(sLineNum).xxExstFlg_A2.clear();

            // Find first related line
            int firstLine = sLineNum - 1;
            for (; firstLine >= 0; --firstLine) {
                if (!preSoNum.equals(sMsg.A.no(firstLine).soNum_HI.getValue())) {
                    break;
                }
            }
            ++firstLine;
            // Clear all xxExstFlg_A1 from related lines
            for (int i = firstLine; i < sMsg.A.getValidCount(); ++i) {
                NLBL2020_ASMsg sMsgALine = sMsg.A.no(i);
                if (!preSoNum.equals(sMsgALine.soNum_HI.getValue())) {
                    break;
                }
                sMsgALine.xxExstFlg_A1.clear();
                int cIndex = i - (cMsg.xxPageShowFromNum_A.getValueInt() - 1);
                if (cIndex >= 0 && cIndex < cMsg.A.getValidCount()) {
                    cMsg.A.no(cIndex).xxExstFlg_A1.clear();
                }
            }
            if (NLBL2020CommonLogic.ctrlShipConfButton(sMsg)) {
                // Ship Conf btn active
                ZYPEZDItemValueSetter.setValue(cMsg.actvFlg_SC, ZYPConstant.FLG_ON_Y);
            }

            return;
        }

        // OFF --> ON
        if (ZYPConstant.FLG_ON_Y.equals(val)) {
            String preSoNum = sMsg.A.no(sLineNum).soNum_HI.getValue();

            ZYPEZDItemValueSetter.setValue(sMsg.A.no(sLineNum).xxExstFlg_A2, ZYPConstant.FLG_ON_Y);

            int offCount = 0;

            // Find first related line
            int firstLine = sLineNum - 1;
            for (; firstLine >= 0; --firstLine) {
                if (!preSoNum.equals(sMsg.A.no(firstLine).soNum_HI.getValue())) {
                    break;
                }
            }
            ++firstLine;
            // Count un-selected lines from related lines
            for (int i = firstLine; i < sMsg.A.getValidCount(); ++i) {
                NLBL2020_ASMsg sMsgALine = sMsg.A.no(i);
                if (!preSoNum.equals(sMsgALine.soNum_HI.getValue())) {
                    break;
                }
                if (!ZYPConstant.FLG_ON_Y.equals(sMsgALine.xxExstFlg_A2.getValue())) {
                    // Off
                    offCount++;
                }
            }
            if (offCount == 0) {
                for (int i = firstLine; i < sMsg.A.getValidCount(); ++i) {
                    NLBL2020_ASMsg sMsgALine = sMsg.A.no(i);
                    if (!preSoNum.equals(sMsgALine.soNum_HI.getValue())) {
                        break;
                    }
                    ZYPEZDItemValueSetter.setValue(sMsgALine.xxExstFlg_A1, ZYPConstant.FLG_ON_Y);
                    int cIndex = i - (cMsg.xxPageShowFromNum_A.getValueInt() - 1);
                    if (cIndex >= 0 && cIndex < cMsg.A.getValidCount()) {
                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(cIndex).xxExstFlg_A1, ZYPConstant.FLG_ON_Y);
                    }
                }
            }

            return;
        }
    }

    /**
     * doProcess_OnClick_Apply
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_OnClick_Apply(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        // copy data from CMsg onto SMsg
        NLBL2020CommonLogic.saveCurrentPageToSMsgSoList(cMsg, sMsg);

        // Input check in Detail
        boolean chkBoxOn = false;
        int firstErrIdx = -1;
        boolean hasErr = false;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            NLBL2020_ASMsg sMsgALine = sMsg.A.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsgALine.xxExstFlg_A2.getValue())) {

                chkBoxOn = true;

                if (SHPG_STS.SHIPPED.equals(sMsgALine.dsSoLineStsCd_AH.getValue())) {

                    // Not Open Status
                    sMsgALine.xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLBM1307E);
                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }
                }
            }
        }

        if (hasErr) {
            NLBL2020CommonLogic.pagenationForTabSoList(cMsg, sMsg, NLBL2020CommonLogic.getPageStartRowIndexForTabSoList(firstErrIdx, cMsg, sMsg));
        }

        if (!chkBoxOn) {

            for (int i = 0; i < cMsg.A.getValidCount(); i++) {

                NLBL2020_ACMsg cMsgALine = cMsg.A.no(i);
                cMsgALine.xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLBM0002E);
            }

            return;
        }

        // Input check in Apply Info (Carrier)
        if (ZYPCommonFunc.hasValue(cMsg.carrNm_D1)) {

            // Master Check
            OTBD_CARR_VTMsg otbdCarrV = new OTBD_CARR_VTMsg();
            otbdCarrV.setSQLID("005");
            otbdCarrV.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
            otbdCarrV.setConditionValue("carrNm01", cMsg.carrNm_D1.getValue());
            OTBD_CARR_VTMsgArray otbdCarrVList = (OTBD_CARR_VTMsgArray) EZDTBLAccessor.findByCondition(otbdCarrV);

            if (otbdCarrVList == null || otbdCarrVList.getValidCount() == 0) {

                // Not found
                cMsg.carrNm_D1.setErrorInfo(1, NLBL2020Constant.NLZM2278E, new String[]{"Carrier"});
                return;
            }

            ZYPEZDItemValueSetter.setValue(cMsg.carrCd_D1, otbdCarrVList.no(0).carrCd);
        }

        //Apply Copy
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            NLBL2020_ASMsg sMsgALine = sMsg.A.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsgALine.xxExstFlg_A2.getValue())) {

                if (ZYPCommonFunc.hasValue(cMsg.carrCd_D1)) {

                    ZYPEZDItemValueSetter.setValue(sMsgALine.carrCd_A1, cMsg.carrCd_D1);
                    ZYPEZDItemValueSetter.setValue(sMsgALine.carrNm_A1, cMsg.carrNm_D1);
                }

                if (ZYPCommonFunc.hasValue(cMsg.totFrtAmt_D1)) {

                    ZYPEZDItemValueSetter.setValue(sMsgALine.totFrtAmt_A1, cMsg.totFrtAmt_D1);
                }

                if (ZYPCommonFunc.hasValue(cMsg.proNum_D1)) {

                    ZYPEZDItemValueSetter.setValue(sMsgALine.proNum_A1, cMsg.proNum_D1);
                }
            }
        }

        if (hasErr) {
            cMsg.setMessageInfo(NLBL2020Constant.ZZM9037E);
            NLBL2020CommonLogic.pagenationForTabSoList(cMsg, sMsg, NLBL2020CommonLogic.getPageStartRowIndexForTabSoList(firstErrIdx, cMsg, sMsg));
            return;
        }

        NLBL2020CommonLogic.pagenationForTabSoList(cMsg, sMsg, cMsg.xxPageShowFromNum_A.getValueInt() - 1);
    }

    /**
     * doProcess_OpenWin_ShpgInstn
     * @param cMsg NLBL2020CMsg
     */
    private void doProcess_OpenWin_ShpgInstn(NLBL2020CMsg cMsg) {

        int selectedIndex = cMsg.xxNum_EV.getValueInt();

        if (ZYPCommonFunc.hasValue(cMsg.A.no(selectedIndex).xxDsMultMsgDplyTxt_A1)) {

            return;
        }

        HashMap<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("soNum", cMsg.A.no(selectedIndex).soNum_HI.getValue());
        ssmParam.put("soMsgTpCd", SHPG_ORD_MSG_TP.CPO_INFORMATION);

        S21SsmEZDResult ssmResult = NLBL2020Query.getInstance().getShpgInstnCmntTxt(ssmParam);

        if (ssmResult.isCodeNormal()) {

            List<String> soMsgDescTxtList = (ArrayList<String>) ssmResult.getResultObject();

            if (soMsgDescTxtList != null && !soMsgDescTxtList.isEmpty()) {

                StringBuilder soMsgDescTxtBuilder = new StringBuilder();

                for (String soMsgDescTxt : soMsgDescTxtList) {

                    soMsgDescTxtBuilder.append(soMsgDescTxt);
                }

                if (soMsgDescTxtBuilder != null) {

                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(selectedIndex).xxDsMultMsgDplyTxt_A1, soMsgDescTxtBuilder.toString());
                }
            }
        }
    }

    /**
     * doProcess_NNLBL2020Scrn00_CMN_ColClear
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_NNLBL2020Scrn00_CMN_ColClear(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {
        // There is no process.
        return;
    }

    /**
     * doProcess_NLBL2020Scrn00_CMN_ColSave
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_NLBL2020Scrn00_CMN_ColSave(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {
        // There is no process.
        return;
    }

    /**
     * doProcess_NLBL2020Scrn00_OpenWin_Tracking
     * QC#21913 Add method.
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_NLBL2020Scrn00_OpenWin_Tracking(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        int selectIndex = cMsg.xxNum.getValueInt();

        // Set write Data.
        cMsg.T.clear();

        String soNum = cMsg.A.no(selectIndex).soNum_HI.getValue();
        int cnt = 0;
        for (int i = 0; i < sMsg.T.getValidCount() && cnt < cMsg.T.length(); i++) {
            if (soNum.equals(sMsg.T.no(i).soNum.getValue())) {
                EZDMsg.copy(sMsg.T.no(i), null, cMsg.T.no(cnt), null);
                cnt++;
            }
        }
        cMsg.T.setValidCount(cnt);

    }

    /**
     * doProcess_NLBL2020_NLBL3170
     * QC#21913 Add method.
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_NLBL2020_NLBL3170(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        HashMap<String, ArrayList<NLBL2020_TSMsg>> map = new HashMap<String, ArrayList<NLBL2020_TSMsg>>();
        for (int i = 0; i < sMsg.T.getValidCount(); i++) {
            NLBL2020_TSMsg tmp = new NLBL2020_TSMsg();
            EZDMsg.copy(sMsg.T.no(i), null, tmp, null);

            if (map.containsKey(tmp.soNum.getValue())) {
                ArrayList<NLBL2020_TSMsg> list = map.get(tmp.soNum.getValue());
                list.add(tmp);
            } else {
                ArrayList<NLBL2020_TSMsg> listNew = new ArrayList<NLBL2020_TSMsg>();
                listNew.add(tmp);
                map.put(tmp.soNum.getValue(), listNew);
            }
        }

        // change TrackingList of soNum
        int selectIndex = cMsg.xxNum.getValueInt();
        String soNum = cMsg.A.no(selectIndex).soNum_HI.getValue();
        ArrayList<NLBL2020_TSMsg> targetList = map.get(soNum);
        if (targetList == null) {
            targetList = new ArrayList<NLBL2020_TSMsg>();
            map.put(soNum, targetList);
        } else {
            targetList.clear();
        }
        ArrayList<String> duplicateCheckList = new ArrayList<String>();
        for (int i = 0; i < cMsg.T.getValidCount(); i++) {
            NLBL2020_TCMsg tCMsg = cMsg.T.no(i);
            String proNum = tCMsg.proNum.getValue();

            if (!duplicateCheckList.contains(proNum)) {
                NLBL2020_TSMsg tmp = new NLBL2020_TSMsg();
                EZDMsg.copy(tCMsg, null, tmp, null);
                ZYPEZDItemValueSetter.setValue(tmp.soNum, soNum);

                targetList.add(tmp);
                duplicateCheckList.add(proNum);
            }
        }

        // return sMsg data.
        int cnt = 0;
        sMsg.T.clear();
        for (Map.Entry<String, ArrayList<NLBL2020_TSMsg>> entry : map.entrySet()) {
            ArrayList<NLBL2020_TSMsg> list = entry.getValue();

            for (NLBL2020_TSMsg t : list) {
                EZDMsg.copy(t, null, sMsg.T.no(cnt), null);
                cnt++;
            }
        }

        sMsg.T.setValidCount(cnt);
    }
    
    /**
     * doProcess_Select_All
     * QC#52895 Mod method.
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_Select_All(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {
        String protectOrdListTxt;
        List<String> protectOrdList;

        protectOrdListTxt = ZYPCodeDataUtil.getVarCharConstValue("NLBL2020_PROTECT_ORD", sMsg.glblCmpyCd.getValue());
        protectOrdList = new ArrayList<String>();
        if (protectOrdListTxt != null) {
            protectOrdList = Arrays.asList(protectOrdListTxt.split(","));
        }

        for (int i = 0; i < sMsg.A.getValidCount(); ++i) {
            NLBL2020_ASMsg sMsgALine = sMsg.A.no(i);
            if (!protectOrdList.contains(sMsgALine.sceOrdTpCd_AH.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsgALine.xxExstFlg_A1, ZYPConstant.CHKBOX_ON_Y);
                ZYPEZDItemValueSetter.setValue(sMsgALine.xxExstFlg_A2, ZYPConstant.CHKBOX_ON_Y);
            }
        }

        protectOrdListTxt = ZYPCodeDataUtil.getVarCharConstValue("NLBL2020_PROTECT_ORD", cMsg.glblCmpyCd.getValue());
        protectOrdList = new ArrayList<String>();
        if (protectOrdListTxt != null) {
            protectOrdList = Arrays.asList(protectOrdListTxt.split(","));
        }

        for (int i = 0; i < cMsg.A.getValidCount(); ++i) {
            NLBL2020_ACMsg cMsgALine = cMsg.A.no(i);
            if (!protectOrdList.contains(cMsgALine.sceOrdTpCd_AH.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsgALine.xxExstFlg_A1, ZYPConstant.CHKBOX_ON_Y);
                ZYPEZDItemValueSetter.setValue(cMsgALine.xxExstFlg_A2, ZYPConstant.CHKBOX_ON_Y);
            }
        }
    }
    
    /**
     * doProcess_UnSelect_All
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_UnSelect_All(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {
        for(int i=0; i<sMsg.A.getValidCount(); ++i) {
            NLBL2020_ASMsg sMsgALine = sMsg.A.no(i);
            sMsgALine.xxExstFlg_A1.clear();
            sMsgALine.xxExstFlg_A2.clear();
        }
        for(int i=0; i<cMsg.A.getValidCount(); ++i) {
            NLBL2020_ACMsg cMsgALine = cMsg.A.no(i);
            cMsgALine.xxExstFlg_A1.clear();
            cMsgALine.xxExstFlg_A2.clear();
        }
    }

    // add start 2022/10/18 QC#60559
    private void doProcess_NLBL2020Scrn00_CMN_Download(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {
        if (NLBL2020Constant.TAB_SO_LIST.equals(cMsg.xxDplyTab.getValue())) {
            NLBL2020CommonLogic.saveCurrentPageToSMsgSoList(cMsg, sMsg);
            writeSoListCsvFile(cMsg, sMsg);
        } else if (NLBL2020Constant.TAB_PICK_LIST.equals(cMsg.xxDplyTab.getValue())) {
            NLBL2020CommonLogic.saveCurrentPageToSMsgPickList(cMsg, sMsg);
            writePickListCsvFile(cMsg, sMsg);
        }
    }

    private void writeSoListCsvFile(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {
        NLBL2020F00FMsg fMsg = new NLBL2020F00FMsg();
        cMsg.xxFileData_DL.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NLBL2020Constant.SO_LIST_CSV_NAME), NLBL2020Constant.CSV);
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_DL.getTempFilePath(), fMsg);
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));
        fMsg.addExclusionItem("xxExstFlg_A1");
        fMsg.addExclusionItem("xxExstFlg_A2");

        // write header
        csvOutFile.writeHeader(NLBL2020Constant.SO_LIST_CSV_HEADER, fMsg, ZYPGUITableColumn.getColOrder(cMsg));

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NLBL2020_ASMsg aSMsg = sMsg.A.no(i);
            ZYPEZDItemValueSetter.setValue(fMsg.trxHdrNum_A1, aSMsg.trxHdrNum_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.dplyLineNum_A1, aSMsg.dplyLineNum_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.soNum_A1, aSMsg.soNum_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.soSlpNum_A1, aSMsg.soSlpNum_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm_A1, aSMsg.rtlWhNm_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.shipFromRtlSwhCd_A1, aSMsg.shipFromRtlSwhCd_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.shipToStCd_A1, aSMsg.shipToStCd_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.shipToCtacPsnNm_A1, aSMsg.shipToCtacPsnNm_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.mdseCd_A1, aSMsg.mdseCd_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt_A1, aSMsg.mdseDescShortTxt_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.backOrdImpctTpDescTxt_A1, aSMsg.backOrdImpctTpDescTxt_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.shpgQty_A1, aSMsg.shpgQty_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.basePkgUomCd_A1, aSMsg.basePkgUomCd_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.serNum_A1, aSMsg.serNum_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.xxTsDsp19Txt_A1, aSMsg.xxTsDsp19Txt_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.rddDt_A1, aSMsg.rddDt_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.xxTsDsp19Txt_A4, aSMsg.xxTsDsp19Txt_A4);
            ZYPEZDItemValueSetter.setValue(fMsg.pickConfQty_A1, aSMsg.pickConfQty_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.shipQty_A1, aSMsg.shipQty_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.shipQty_A1, aSMsg.shipQty_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem19Txt_A1, aSMsg.xxScrItem19Txt_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.xxTsDsp19Txt_A2, aSMsg.xxTsDsp19Txt_A2);
            ZYPEZDItemValueSetter.setValue(fMsg.xxTsDsp19Txt_A5, aSMsg.xxTsDsp19Txt_A5);
            ZYPEZDItemValueSetter.setValue(fMsg.xxTsDsp19Txt_A3, aSMsg.xxTsDsp19Txt_A3);
            ZYPEZDItemValueSetter.setValue(fMsg.carrNm_A2, aSMsg.carrNm_A2);
            ZYPEZDItemValueSetter.setValue(fMsg.shpgSvcLvlDescTxt_A1, aSMsg.shpgSvcLvlDescTxt_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.totFrtAmt_A1, aSMsg.totFrtAmt_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.carrAcctNum_A1, aSMsg.carrAcctNum_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.tocNm_A1, aSMsg.tocNm_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.dsSoLineStsDescTxt_A1, aSMsg.dsSoLineStsDescTxt_A1);
            // add start 2023/08/08 QC#61677
            ZYPEZDItemValueSetter.setValue(fMsg.schdCoordStsDescTxt_A1, aSMsg.schdCoordStsDescTxt_A1);
            // add end 2023/08/08 QC#61677
            ZYPEZDItemValueSetter.setValue(fMsg.carrNm_A1, aSMsg.carrNm_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.shpgSvcLvlDescTxt_A2, NLBL2020CommonLogic.getShpgSvcLvlDescTxt(cMsg.glblCmpyCd.getValue(), aSMsg.actlShpgSvcLvlCd_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.proNum_A2, aSMsg.proNum_A2);
            ZYPEZDItemValueSetter.setValue(fMsg.proNum_A1, aSMsg.proNum_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.xxYesNoCd_A2, aSMsg.xxYesNoCd_A2);
            ZYPEZDItemValueSetter.setValue(fMsg.sceOrdTpNm_A1, aSMsg.sceOrdTpNm_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.svcConfigMstrPk_A1, aSMsg.svcConfigMstrPk_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.pickSvcConfigMstrPk_A1, aSMsg.pickSvcConfigMstrPk_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.fromStkStsCd_A1, aSMsg.fromStkStsCd_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.inEachQty_A1, aSMsg.inEachQty_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.shipToLocNm_A1, aSMsg.shipToLocNm_A1);
            csvOutFile.write();
        }
        csvOutFile.close();
    }

    private void writePickListCsvFile(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {
        NLBL2020F01FMsg fMsg = new NLBL2020F01FMsg();
        cMsg.xxFileData_DL.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NLBL2020Constant.PICK_LIST_CSV_NAME), NLBL2020Constant.CSV);
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_DL.getTempFilePath(), fMsg);

        // write header
        csvOutFile.writeHeader(NLBL2020Constant.PICK_LIST_CSV_HEADER);

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NLBL2020_BSMsg bSMsg = sMsg.B.no(i);
            ZYPEZDItemValueSetter.setValue(fMsg.trxHdrNum_B1, bSMsg.trxHdrNum_B1);
            ZYPEZDItemValueSetter.setValue(fMsg.dplyLineNum_B1, bSMsg.dplyLineNum_B1);
            ZYPEZDItemValueSetter.setValue(fMsg.soNum_B1, bSMsg.soNum_B1);
            ZYPEZDItemValueSetter.setValue(fMsg.soSlpNum_B1, bSMsg.soSlpNum_B1);
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm_B1, bSMsg.rtlWhNm_B1);
            ZYPEZDItemValueSetter.setValue(fMsg.shipFromRtlSwhCd_B1, bSMsg.shipFromRtlSwhCd_B1);
            ZYPEZDItemValueSetter.setValue(fMsg.mdseCd_B1, bSMsg.mdseCd_B1);
            ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt_B1, bSMsg.mdseDescShortTxt_B1);
            ZYPEZDItemValueSetter.setValue(fMsg.pickConfQty_B1, bSMsg.pickConfQty_B1);
            ZYPEZDItemValueSetter.setValue(fMsg.serNum_B1, bSMsg.serNum_B1);
            ZYPEZDItemValueSetter.setValue(fMsg.pickSvcConfigMstrPk_B1, bSMsg.pickSvcConfigMstrPk_B1);
            ZYPEZDItemValueSetter.setValue(fMsg.shpgQty_B1, bSMsg.shpgQty_B1);
            ZYPEZDItemValueSetter.setValue(fMsg.pickCpltQty_B1, bSMsg.pickCpltQty_B1);
            ZYPEZDItemValueSetter.setValue(fMsg.shipQty_B1, bSMsg.shipQty_B1);
            csvOutFile.write();
        }
        csvOutFile.close();
    }
    // add end 2022/10/18 QC#60559
}
