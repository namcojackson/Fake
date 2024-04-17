/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */

package business.blap.NLBL3050;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDFastTBLAccessor;
import business.blap.NLBL3050.constant.NLBL3050Constant;
import business.db.RTL_WHTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_COORD_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Distribution Coordinator Work Bench
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/19/2015   Fujitsu         W.Honda         Create          N/A
 * 01/21/2016   CSAI            Y.Imazu         Update          QC#2048
 * 10/26/2016   CSAI            Y.Imazu         Update          QC#9760
 * 01/12/2017   CITS            T.Kikuhara      Update          QC#16283
 * 01/27/2017   CITS            T.Kikuhara      Update          QC#15650
 *</pre>
 */
public class NLBL3050BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NLBL3050_INIT".equals(screenAplID)) {

                doProcess_NLBL3050_INIT((NLBL3050CMsg) cMsg);

            } else if ("NLBL3050Scrn00_CMN_Clear".equals(screenAplID)) {

                doProcess_NLBL3050Scrn00_CMN_Clear((NLBL3050CMsg) cMsg);

            } else if ("NLBL3050Scrn00_Search".equals(screenAplID)) {

                doProcess_NLBL3050Scrn00_Search((NLBL3050CMsg) cMsg);

            } else if ("NLBL3050Scrn00_Search_CoordInfo".equals(screenAplID)) {

                doProcess_NLBL3050Scrn00_Search_CoordInfo((NLBL3050CMsg) cMsg);

            } else {

                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {

            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NLBL3050_INIT
     * @param cMsg NLBL3050CMsg
     */
    private void doProcess_NLBL3050_INIT(NLBL3050CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.asgTaskFlg)) {

            doProcess_NLBL3050Scrn00_Search(cMsg);

        } else {

            cMsg.setMessageInfo(NLBL3050Constant.NLBM0042E);
        }
    }

    /**
     * doProcess_NLBL3050Scrn00_Search
     * @param cMsg NLBL3050CMsg
     */
    private void doProcess_NLBL3050Scrn00_Search(NLBL3050CMsg cMsg) {

        List<String> srchRtlWhList = new ArrayList<String>();

        if (ZYPCommonFunc.hasValue(cMsg.xxRtlWhSrchTxt_H)) {

            srchRtlWhList = splitSrchTxt(cMsg.xxRtlWhSrchTxt_H);
        }

        // Master Check
        if (checkInputHeader(cMsg, srchRtlWhList)) {

            // Clear Table
            ZYPTableUtil.clear(cMsg.A);
            ZYPTableUtil.clear(cMsg.B);
            ZYPTableUtil.clear(cMsg.C);
            ZYPTableUtil.clear(cMsg.D);
            ZYPTableUtil.clear(cMsg.F);
            ZYPTableUtil.clear(cMsg.L);
            ZYPTableUtil.clear(cMsg.T);

            ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_A, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_OA, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_IA, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_B, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_OB, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_IB, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_L, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_OL, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_IL, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_T, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_OT, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_IT, BigDecimal.ZERO);

            // Search Available Order
            String rtrnMsgAvalOrd = searchAvalOrd(cMsg, srchRtlWhList);

            // Search Back Order
            String rtrnMsgBackOrd = searchBackOrd(cMsg, srchRtlWhList);

            // Set Message
            if (rtrnMsgAvalOrd.endsWith("W")) {

                cMsg.setMessageInfo(rtrnMsgAvalOrd);

            } else if (rtrnMsgBackOrd.endsWith("W")) {

                cMsg.setMessageInfo(rtrnMsgBackOrd);

            } else if (rtrnMsgAvalOrd.endsWith("I")) {

                cMsg.setMessageInfo(rtrnMsgAvalOrd);

            } else if (rtrnMsgBackOrd.endsWith("I")) {

                cMsg.setMessageInfo(rtrnMsgBackOrd);

            } else {

                cMsg.setMessageInfo(rtrnMsgAvalOrd);
            }
        }
    }

    /**
     * doProcess_NLBL3050Scrn00_CMN_Clear
     * @param cMsg NLBL3050CMsg
     */
    private void doProcess_NLBL3050Scrn00_CMN_Clear(NLBL3050CMsg cMsg) {

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(cMsg.C);
        ZYPTableUtil.clear(cMsg.D);
        ZYPTableUtil.clear(cMsg.F);
        ZYPTableUtil.clear(cMsg.P);
        ZYPTableUtil.clear(cMsg.L);
        ZYPTableUtil.clear(cMsg.T);

        cMsg.cpoNum_H.clear();
        cMsg.xxRtlWhSrchTxt_H.clear();
        cMsg.xxMntEventNm.clear();

        if(!ZYPCommonFunc.hasValue(cMsg.asgTaskFlg) || !ZYPConstant.FLG_ON_Y.equals(cMsg.asgTaskFlg.getValue())){

            cMsg.psnCd_H.clear();
            cMsg.xxPsnFirstMidLastNm_H.clear();
        }

        cMsg.xxSortTblNm_A.clear();
        cMsg.xxSortItemNm_A.clear();
        cMsg.xxSortOrdByTxt_A.clear();
        cMsg.xxSortTblNm_B.clear();
        cMsg.xxSortItemNm_B.clear();
        cMsg.xxSortOrdByTxt_B.clear();
        cMsg.xxSortTblNm_C.clear();
        cMsg.xxSortItemNm_C.clear();
        cMsg.xxSortOrdByTxt_C.clear();
        cMsg.xxSortTblNm_D.clear();
        cMsg.xxSortItemNm_D.clear();
        cMsg.xxSortOrdByTxt_D.clear();
        cMsg.xxSortTblNm_F.clear();
        cMsg.xxSortItemNm_F.clear();
        cMsg.xxSortOrdByTxt_F.clear();

        ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_OA, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_IA, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_B, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_OB, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_IB, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_L, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_OL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_IL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_T, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_OT, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_IT, BigDecimal.ZERO);
    }

    /**
     * Search_CoordInfo
     * @param cMsg NLBL3050CMsg
     */
    private void doProcess_NLBL3050Scrn00_Search_CoordInfo(NLBL3050CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("psnCd", cMsg.psnCd_H.getValue());

        S21SsmEZDResult ssmResult = NLBL3050Query.getInstance().getPsnNm(params);

        if (ssmResult.isCodeNormal()) {

            ZYPEZDItemValueSetter.setValue(cMsg.xxPsnFirstMidLastNm_H, (String)ssmResult.getResultObject());

        } else {

            // Error
            cMsg.psnCd_H.setErrorInfo(1, "NLZM2278E", new String[]{cMsg.psnCd_H.getValue()});
            cMsg.xxPsnFirstMidLastNm_H.clear();
        }
    }

    /**
     * Check Input Header
     * @param cMsg NLBL3050CMsg
     * @param srchRtlWhList List<String>
     */
    private boolean checkInputHeader(NLBL3050CMsg cMsg, List<String> srchRtlWhList) {

        // Warehouse
        if (srchRtlWhList != null && !srchRtlWhList.isEmpty()) {

            if (srchRtlWhList.size() == 1 && srchRtlWhList.get(0).indexOf("%") == -1) {

                RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
                ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, cMsg.xxRtlWhSrchTxt_H);
                rtlWhTMsg = (RTL_WHTMsg) EZDFastTBLAccessor.findByKey(rtlWhTMsg);

                if (rtlWhTMsg == null) {

                    cMsg.xxRtlWhSrchTxt_H.setErrorInfo(1, NLBL3050Constant.NLZM2278E, new String[]{"Warehouse"});
                    cMsg.setMessageInfo(NLBL3050Constant.ZZM9037E);
                    return false;
                }

            } else {

                Map<String, Object> params = new HashMap<String, Object>();
                params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
                params.put("whCdList", srchRtlWhList);

                S21SsmEZDResult ssmResult = NLBL3050Query.getInstance().getRtlWhList(params);

                if (ssmResult.isCodeNormal()) {

                    List<Map<String, Object>> rtlWhMapList = (ArrayList<Map<String, Object>>) ssmResult.getResultObject();

                    if (rtlWhMapList == null || rtlWhMapList.isEmpty()) {

                        cMsg.xxRtlWhSrchTxt_H.setErrorInfo(1, NLBL3050Constant.NLZM2278E, new String[]{"Warehouse"});
                        cMsg.setMessageInfo(NLBL3050Constant.ZZM9037E);
                        return false;
                    }
                }
            }
        }

        // Check Coordinator
        if(ZYPCommonFunc.hasValue(cMsg.psnCd_H)){

            doProcess_NLBL3050Scrn00_Search_CoordInfo(cMsg);

            if(cMsg.psnCd_H.isError()){

                if (ZYPConstant.FLG_ON_Y.equals(cMsg.asgTaskFlg.getValue())) {

                    cMsg.psnCd_H.clearErrorInfo();

                } else {

                    return false;
                }
            }

        } else {

            cMsg.xxPsnFirstMidLastNm_H.clear();
        }

        return true;
    }

    /**
     * get array from search text if it has "," in text field.
     * splitSrchTxt
     * @param srchTxtItem EZDCStringItemring
     * @return ArrayList<String>
     */
    public static ArrayList<String> splitSrchTxt(EZDCStringItem srchTxtItem) {

        ArrayList<String> splitSrchTxtList = new ArrayList<String>();
        boolean isSplit = false;

        if (ZYPCommonFunc.hasValue(srchTxtItem)) {

            if (srchTxtItem.getValue().indexOf(",") != -1) {

                String[] srchTxtArray = srchTxtItem.getValue().split(",");

                for (int i = 0; i < srchTxtArray.length; i++) {

                    if (!srchTxtArray[i].trim().equals("") && srchTxtArray[i].length() > 0) {

                        String chkTxt = srchTxtArray[i].trim();

                        if (ZYPCommonFunc.hasValue(chkTxt) && chkTxt.length() > 0) {

                            splitSrchTxtList.add(srchTxtArray[i].trim());
                        }

                        isSplit = true;
                    }
                }
            }

            if (!isSplit) {

                String chkTxt = srchTxtItem.getValue().trim();

                if (ZYPCommonFunc.hasValue(chkTxt) && chkTxt.length() > 0) {

                    splitSrchTxtList.add(srchTxtItem.getValue().trim());
                }
            }
        }

        if (splitSrchTxtList != null && !splitSrchTxtList.isEmpty()) {

            return splitSrchTxtList;
        }

        return null;
    }

    /**
     * Search Available Order
     * @param cMsg NLBL3050CMsg
     * @param srchRtlWhList List<String>
     * @return String
     */
    private String searchAvalOrd(NLBL3050CMsg cMsg, List<String> srchRtlWhList) {

        S21SsmEZDResult ssmResult = NLBL3050Query.getInstance().getOrder(cMsg, srchRtlWhList);

        if (ssmResult.isCodeNormal()) {

            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

            // Available Order for Display (Table A)
            List<String> avalCpoNumList = new ArrayList<String>();
            int tblIndexA = 0;
            int totTblIndexA = 0;
            int cpoIndexA = 0;
            int soCntA = 0;
            int rwsCntA = 0;
            int cpoCntA = 0;
            int totSoCntA = 0;
            int totRwsCntA = 0;

            String schdCoordStsDescTxt = null;
            String shipToAcctNm = null;
            String shipToLineAddr = null;
            String shipToCtyAddr = null;
            String shipToStCd = null;

            // Available Order Today's Task (Table T)
            List<String> todayCpoNumList = new ArrayList<String>();
            int tblIndexT = 0;
            int totTblIndexT = 0;
            int cpoIndexT = 0;
            int soCntT = 0;
            int rwsCntT = 0;
            int cpoCntT = 0;
            int totSoCntT = 0;
            int totRwsCntT = 0;

            String schdCoordStsDescTxtT = null;
            String shipToAcctNmT = null;
            String shipToLineAddrT = null;
            String shipToCtyAddrT = null;
            String shipToStCdT = null;

            // Scheduled SO (Table C)
            List<String> soSchdCpoNumList = new ArrayList<String>();
            int tblIndexC = 0;
            int totTblIndexC = 0;
            int cpoIndexC = 0;
            int soCntC = 0;

            // Scheduled SO Future (Table D)
            List<String> soSchdFtuCpoNumList = new ArrayList<String>();
            int tblIndexD = 0;
            int totTblIndexD = 0;
            int cpoIndexD = 0;
            int soCntD = 0;

            // Scheduled RWS (Table F)
            List<String> rwsSchdCpoNumList = new ArrayList<String>();
            int tblIndexF = 0;
            int totTblIndexF = 0;
            int cpoIndexF = 0;
            int rwsCntF = 0;

            for (Map<String, Object> resultMap : resultList) {

                String soNum = (String) resultMap.get("SO_NUM");
                String rwsNum = (String) resultMap.get("RWS_NUM");
                String schdOpenFlg = (String) resultMap.get("SCHD_OPEN_FLG");
                String schdConfFlg = (String) resultMap.get("SCHD_CONF_FLG");
                String schdDelyDt = (String) resultMap.get("SCHD_DELY_DT");
                String cratDt = (String) resultMap.get("CRAT_DT");

                /*************************************************************
                 * Available Order (Table A)
                 ************************************************************/
                if (ZYPConstant.FLG_ON_Y.equals(schdOpenFlg) && ZYPConstant.FLG_OFF_N.equals(schdConfFlg)) {

                    if (avalCpoNumList.isEmpty() || !avalCpoNumList.contains((String) resultMap.get("CPO_ORD_NUM"))) {

                        if (!avalCpoNumList.isEmpty() && cpoIndexA < cMsg.A.length()) {

                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(cpoIndexA).xxTotCnt_AO, BigDecimal.valueOf(soCntA));
                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(cpoIndexA).xxTotCnt_AI, BigDecimal.valueOf(rwsCntA));
                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(cpoIndexA).delyCoordStsDescTxt_A1, schdCoordStsDescTxt);
                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(cpoIndexA).shipToAcctNm_A1, shipToAcctNm);
                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(cpoIndexA).shipToAddr_A1, shipToLineAddr);
                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(cpoIndexA).shipToCtyAddr_A1, shipToCtyAddr);
                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(cpoIndexA).shipToStCd_A1, shipToStCd);

                            ZYPEZDItemValueSetter.setValue(cMsg.L.no(cpoIndexA).xxTotCnt_LO, BigDecimal.valueOf(soCntA));
                            ZYPEZDItemValueSetter.setValue(cMsg.L.no(cpoIndexA).xxTotCnt_LI, BigDecimal.valueOf(rwsCntA));
                            ZYPEZDItemValueSetter.setValue(cMsg.L.no(cpoIndexA).delyCoordStsDescTxt_L1, schdCoordStsDescTxt);
                            ZYPEZDItemValueSetter.setValue(cMsg.L.no(cpoIndexA).shipToAcctNm_L1, shipToAcctNm);
                            ZYPEZDItemValueSetter.setValue(cMsg.L.no(cpoIndexA).shipToAddr_L1, shipToLineAddr);
                            ZYPEZDItemValueSetter.setValue(cMsg.L.no(cpoIndexA).shipToCtyAddr_L1, shipToCtyAddr);
                            ZYPEZDItemValueSetter.setValue(cMsg.L.no(cpoIndexA).shipToStCd_L1, shipToStCd);
                        }

                        soCntA = 0;
                        rwsCntA = 0;

                        schdCoordStsDescTxt = (String) resultMap.get("SCHD_COORD_STS_DESC_TXT");
                        shipToAcctNm = (String) resultMap.get("SHIP_TO_ACCT_NM");
                        shipToLineAddr = (String) resultMap.get("SHIP_TO_LINE_ADDR");
                        shipToCtyAddr = (String) resultMap.get("SHIP_TO_CTY_ADDR");
                        shipToStCd = (String) resultMap.get("SHIP_TO_ST_CD");

                        if (tblIndexA < cMsg.A.length()) {

                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(tblIndexA).trxHdrNumTpCd_A1, NLBL3050Constant.DISPLAY_TYPE_ORDER);
                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(tblIndexA).trxHdrNum_A1, (String) resultMap.get("CPO_ORD_NUM"));
                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(tblIndexA).xxTblSortColNm_AS, (String) resultMap.get("CPO_ORD_NUM"));

                            ZYPEZDItemValueSetter.setValue(cMsg.L.no(tblIndexA).trxHdrNumTpCd_L1, NLBL3050Constant.DISPLAY_TYPE_ORDER);
                            ZYPEZDItemValueSetter.setValue(cMsg.L.no(tblIndexA).trxHdrNum_L1, (String) resultMap.get("CPO_ORD_NUM"));
                            ZYPEZDItemValueSetter.setValue(cMsg.L.no(tblIndexA).xxTblSortColNm_LS, (String) resultMap.get("CPO_ORD_NUM"));

                            if (ZYPCommonFunc.hasValue((BigDecimal) resultMap.get("ORD_BOOK_TS"))) {

                                ZYPEZDItemValueSetter.setValue(cMsg.A.no(tblIndexA).xxOrdTs_A1, ((BigDecimal) resultMap.get("ORD_BOOK_TS")).toString());
                                ZYPEZDItemValueSetter.setValue(cMsg.L.no(tblIndexA).xxOrdTs_L1, ((BigDecimal) resultMap.get("ORD_BOOK_TS")).toString());
                                ZYPEZDItemValueSetter.setValue(cMsg.A.no(tblIndexA).cutOffAot_AS, (BigDecimal) resultMap.get("ORD_BOOK_TS"));
                                ZYPEZDItemValueSetter.setValue(cMsg.L.no(tblIndexA).cutOffAot_LS, (BigDecimal) resultMap.get("ORD_BOOK_TS"));
                            }

                            cpoIndexA = tblIndexA;
                            tblIndexA++;

                        } else {

                            cpoIndexA = totTblIndexA;
                        }

                        avalCpoNumList.add((String) resultMap.get("CPO_ORD_NUM"));
                        cpoCntA++;
                        totTblIndexA++;

                    } else {

                        // Check Same Value
                        schdCoordStsDescTxt = chkSameVal(schdCoordStsDescTxt, (String) resultMap.get("SCHD_COORD_STS_DESC_TXT"));
                        shipToAcctNm =  chkSameVal(shipToAcctNm, (String) resultMap.get("SHIP_TO_ACCT_NM"));
                        shipToLineAddr =  chkSameVal(shipToLineAddr, (String) resultMap.get("SHIP_TO_LINE_ADDR"));
                        shipToCtyAddr =  chkSameVal(shipToCtyAddr, (String) resultMap.get("SHIP_TO_CTY_ADDR"));
                        shipToStCd =  chkSameVal(shipToStCd, (String) resultMap.get("SHIP_TO_ST_CD"));
                    }

                    if (tblIndexA < cMsg.A.length()) {

                        if (ZYPCommonFunc.hasValue(soNum)) {

                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(tblIndexA).trxHdrNumTpCd_A1, NLBL3050Constant.DISPLAY_TYPE_SO);
                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(tblIndexA).trxHdrNum_A1, (String) resultMap.get("SO_NUM"));
                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(tblIndexA).xxTblSortColNm_AS, (String) resultMap.get("CPO_ORD_NUM"));

                            ZYPEZDItemValueSetter.setValue(cMsg.L.no(tblIndexA).trxHdrNumTpCd_L1, NLBL3050Constant.DISPLAY_TYPE_SO);
                            ZYPEZDItemValueSetter.setValue(cMsg.L.no(tblIndexA).trxHdrNum_L1, (String) resultMap.get("SO_NUM"));
                            ZYPEZDItemValueSetter.setValue(cMsg.L.no(tblIndexA).xxTblSortColNm_LS, (String) resultMap.get("CPO_ORD_NUM"));

                        } else if (ZYPCommonFunc.hasValue(rwsNum)) {

                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(tblIndexA).trxHdrNumTpCd_A1, NLBL3050Constant.DISPLAY_TYPE_RWS);
                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(tblIndexA).trxHdrNum_A1, (String) resultMap.get("RWS_NUM"));
                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(tblIndexA).xxTblSortColNm_AS, (String) resultMap.get("CPO_ORD_NUM"));

                            ZYPEZDItemValueSetter.setValue(cMsg.L.no(tblIndexA).trxHdrNumTpCd_L1, NLBL3050Constant.DISPLAY_TYPE_RWS);
                            ZYPEZDItemValueSetter.setValue(cMsg.L.no(tblIndexA).trxHdrNum_L1, (String) resultMap.get("RWS_NUM"));
                            ZYPEZDItemValueSetter.setValue(cMsg.L.no(tblIndexA).xxTblSortColNm_LS, (String) resultMap.get("CPO_ORD_NUM"));
                        }

                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(tblIndexA).delyCoordStsDescTxt_A1, (String) resultMap.get("SCHD_COORD_STS_DESC_TXT"));
                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(tblIndexA).shipToAcctNm_A1, (String) resultMap.get("SHIP_TO_ACCT_NM"));
                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(tblIndexA).shipToAddr_A1, (String) resultMap.get("SHIP_TO_LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(tblIndexA).shipToCtyAddr_A1, (String) resultMap.get("SHIP_TO_CTY_ADDR"));
                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(tblIndexA).shipToStCd_A1, (String) resultMap.get("SHIP_TO_ST_CD"));

                        if (ZYPCommonFunc.hasValue((BigDecimal) resultMap.get("ORD_BOOK_TS"))) {

                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(tblIndexA).cutOffAot_AS, (BigDecimal) resultMap.get("ORD_BOOK_TS"));
                        }

                        ZYPEZDItemValueSetter.setValue(cMsg.L.no(tblIndexA).delyCoordStsDescTxt_L1, (String) resultMap.get("SCHD_COORD_STS_DESC_TXT"));
                        ZYPEZDItemValueSetter.setValue(cMsg.L.no(tblIndexA).shipToAcctNm_L1, (String) resultMap.get("SHIP_TO_ACCT_NM"));
                        ZYPEZDItemValueSetter.setValue(cMsg.L.no(tblIndexA).shipToAddr_L1, (String) resultMap.get("SHIP_TO_LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(cMsg.L.no(tblIndexA).shipToCtyAddr_L1, (String) resultMap.get("SHIP_TO_CTY_ADDR"));
                        ZYPEZDItemValueSetter.setValue(cMsg.L.no(tblIndexA).shipToStCd_L1, (String) resultMap.get("SHIP_TO_ST_CD"));

                        if (ZYPCommonFunc.hasValue((BigDecimal) resultMap.get("ORD_BOOK_TS"))) {

                            ZYPEZDItemValueSetter.setValue(cMsg.L.no(tblIndexA).cutOffAot_LS, (BigDecimal) resultMap.get("ORD_BOOK_TS"));
                        }

                        tblIndexA++;
                    }

                    totTblIndexA++;

                    if (ZYPCommonFunc.hasValue(soNum)) {

                        soCntA++;
                        totSoCntA++;

                    } else if (ZYPCommonFunc.hasValue(rwsNum)) {

                        rwsCntA++;
                        totRwsCntA++;
                    }

                    /*************************************************************
                     * Available Order Today's Task (Table T)
                     ************************************************************/
                    if (ZYPCommonFunc.hasValue(cratDt) && cMsg.slsDt.getValue().equals(cratDt)) {

                        if (todayCpoNumList.isEmpty() || !todayCpoNumList.contains((String) resultMap.get("CPO_ORD_NUM"))) {

                            if (!todayCpoNumList.isEmpty() && cpoIndexT < cMsg.T.length()) {

                                ZYPEZDItemValueSetter.setValue(cMsg.T.no(cpoIndexT).xxTotCnt_TO, BigDecimal.valueOf(soCntT));
                                ZYPEZDItemValueSetter.setValue(cMsg.T.no(cpoIndexT).xxTotCnt_TI, BigDecimal.valueOf(rwsCntT));
                                ZYPEZDItemValueSetter.setValue(cMsg.T.no(cpoIndexT).delyCoordStsDescTxt_T1, schdCoordStsDescTxtT);
                                ZYPEZDItemValueSetter.setValue(cMsg.T.no(cpoIndexT).shipToAcctNm_T1, shipToAcctNmT);
                                ZYPEZDItemValueSetter.setValue(cMsg.T.no(cpoIndexT).shipToAddr_T1, shipToLineAddrT);
                                ZYPEZDItemValueSetter.setValue(cMsg.T.no(cpoIndexT).shipToCtyAddr_T1, shipToCtyAddrT);
                                ZYPEZDItemValueSetter.setValue(cMsg.T.no(cpoIndexT).shipToStCd_T1, shipToStCdT);
                            }

                            soCntT = 0;
                            rwsCntT = 0;

                            schdCoordStsDescTxtT = (String) resultMap.get("SCHD_COORD_STS_DESC_TXT");
                            shipToAcctNmT = (String) resultMap.get("SHIP_TO_ACCT_NM");
                            shipToLineAddrT = (String) resultMap.get("SHIP_TO_LINE_ADDR");
                            shipToCtyAddrT = (String) resultMap.get("SHIP_TO_CTY_ADDR");
                            shipToStCdT = (String) resultMap.get("SHIP_TO_ST_CD");

                            if (tblIndexT < cMsg.T.length()) {

                                ZYPEZDItemValueSetter.setValue(cMsg.T.no(tblIndexT).trxHdrNumTpCd_T1, NLBL3050Constant.DISPLAY_TYPE_ORDER);
                                ZYPEZDItemValueSetter.setValue(cMsg.T.no(tblIndexT).trxHdrNum_T1, (String) resultMap.get("CPO_ORD_NUM"));
                                ZYPEZDItemValueSetter.setValue(cMsg.T.no(tblIndexT).xxTblSortColNm_TS, (String) resultMap.get("CPO_ORD_NUM"));

                                if (ZYPCommonFunc.hasValue((BigDecimal) resultMap.get("ORD_BOOK_TS"))) {

                                    ZYPEZDItemValueSetter.setValue(cMsg.T.no(tblIndexT).xxOrdTs_T1, ((BigDecimal) resultMap.get("ORD_BOOK_TS")).toString());
                                    ZYPEZDItemValueSetter.setValue(cMsg.T.no(tblIndexT).cutOffAot_TS, (BigDecimal) resultMap.get("ORD_BOOK_TS"));
                                }

                                cpoIndexT = tblIndexT;
                                tblIndexT++;

                            } else {

                                cpoIndexT = totTblIndexT;
                            }

                            todayCpoNumList.add((String) resultMap.get("CPO_ORD_NUM"));
                            cpoCntT++;
                            totTblIndexT++;

                        } else {

                            // Check Same Value
                            schdCoordStsDescTxtT = chkSameVal(schdCoordStsDescTxtT, (String) resultMap.get("SCHD_COORD_STS_DESC_TXT"));
                            shipToAcctNmT =  chkSameVal(shipToAcctNmT, (String) resultMap.get("SHIP_TO_ACCT_NM"));
                            shipToLineAddrT =  chkSameVal(shipToLineAddrT, (String) resultMap.get("SHIP_TO_LINE_ADDR"));
                            shipToCtyAddrT =  chkSameVal(shipToCtyAddrT, (String) resultMap.get("SHIP_TO_CTY_ADDR"));
                            shipToStCdT =  chkSameVal(shipToStCdT, (String) resultMap.get("SHIP_TO_ST_CD"));
                        }

                        if (tblIndexT < cMsg.T.length()) {

                            if (ZYPCommonFunc.hasValue(soNum)) {

                                ZYPEZDItemValueSetter.setValue(cMsg.T.no(tblIndexT).trxHdrNumTpCd_T1, NLBL3050Constant.DISPLAY_TYPE_SO);
                                ZYPEZDItemValueSetter.setValue(cMsg.T.no(tblIndexT).trxHdrNum_T1, (String) resultMap.get("SO_NUM"));
                                ZYPEZDItemValueSetter.setValue(cMsg.T.no(tblIndexT).xxTblSortColNm_TS, (String) resultMap.get("CPO_ORD_NUM"));

                            } else if (ZYPCommonFunc.hasValue(rwsNum)) {

                                ZYPEZDItemValueSetter.setValue(cMsg.T.no(tblIndexT).trxHdrNumTpCd_T1, NLBL3050Constant.DISPLAY_TYPE_RWS);
                                ZYPEZDItemValueSetter.setValue(cMsg.T.no(tblIndexT).trxHdrNum_T1, (String) resultMap.get("RWS_NUM"));
                                ZYPEZDItemValueSetter.setValue(cMsg.T.no(tblIndexT).xxTblSortColNm_TS, (String) resultMap.get("CPO_ORD_NUM"));
                            }

                            ZYPEZDItemValueSetter.setValue(cMsg.T.no(tblIndexT).delyCoordStsDescTxt_T1, (String) resultMap.get("SCHD_COORD_STS_DESC_TXT"));
                            ZYPEZDItemValueSetter.setValue(cMsg.T.no(tblIndexT).shipToAcctNm_T1, (String) resultMap.get("SHIP_TO_ACCT_NM"));
                            ZYPEZDItemValueSetter.setValue(cMsg.T.no(tblIndexT).shipToAddr_T1, (String) resultMap.get("SHIP_TO_LINE_ADDR"));
                            ZYPEZDItemValueSetter.setValue(cMsg.T.no(tblIndexT).shipToCtyAddr_T1, (String) resultMap.get("SHIP_TO_CTY_ADDR"));
                            ZYPEZDItemValueSetter.setValue(cMsg.T.no(tblIndexT).shipToStCd_T1, (String) resultMap.get("SHIP_TO_ST_CD"));

                            if (ZYPCommonFunc.hasValue((BigDecimal) resultMap.get("ORD_BOOK_TS"))) {

                                ZYPEZDItemValueSetter.setValue(cMsg.T.no(tblIndexT).cutOffAot_TS, (BigDecimal) resultMap.get("ORD_BOOK_TS"));
                            }

                            tblIndexT++;
                        }

                        totTblIndexT++;

                        if (ZYPCommonFunc.hasValue(soNum)) {

                            soCntT++;
                            totSoCntT++;

                        } else if (ZYPCommonFunc.hasValue(rwsNum)) {

                            rwsCntT++;
                            totRwsCntT++;
                        }
                    }

                } else if (ZYPConstant.FLG_ON_Y.equals(schdConfFlg) && ZYPCommonFunc.hasValue(schdDelyDt)) {

                    if (ZYPCommonFunc.hasValue(soNum)) {

                        /*************************************************************
                         * Scheduled SO (Table C)
                         ************************************************************/
                        if (cMsg.slsDt.getValue().equals(schdDelyDt) || ZYPDateUtil.addDays(cMsg.slsDt.getValue(), 1).equals(schdDelyDt)) {

                            if (soSchdCpoNumList.isEmpty() || !soSchdCpoNumList.contains((String) resultMap.get("SAME_ORD_CHK_KEY"))) {

                                if (!soSchdCpoNumList.isEmpty() && cpoIndexC < cMsg.C.length()) {

                                    ZYPEZDItemValueSetter.setValue(cMsg.C.no(cpoIndexC).xxTotCnt_CO, BigDecimal.valueOf(soCntC));
                                }

                                soCntC = 0;

                                if (tblIndexC < cMsg.C.length()) {

                                    ZYPEZDItemValueSetter.setValue(cMsg.C.no(tblIndexC).trxHdrNumTpCd_C1, NLBL3050Constant.DISPLAY_TYPE_ORDER);
                                    ZYPEZDItemValueSetter.setValue(cMsg.C.no(tblIndexC).trxHdrNum_C1, (String) resultMap.get("CPO_ORD_NUM"));
                                    ZYPEZDItemValueSetter.setValue(cMsg.C.no(tblIndexC).xxTblSortColNm_CS, (String) resultMap.get("CPO_ORD_NUM"));
                                    ZYPEZDItemValueSetter.setValue(cMsg.C.no(tblIndexC).shipToAcctNm_C1, (String) resultMap.get("SHIP_TO_ACCT_NM"));
                                    ZYPEZDItemValueSetter.setValue(cMsg.C.no(tblIndexC).shipToCtyAddr_C1, (String) resultMap.get("SHIP_TO_CTY_ADDR"));
                                    ZYPEZDItemValueSetter.setValue(cMsg.C.no(tblIndexC).shipToStCd_C1, (String) resultMap.get("SHIP_TO_ST_CD"));

                                    cpoIndexC = tblIndexC;
                                    tblIndexC++;

                                } else {

                                    cpoIndexC = totTblIndexC;
                                }

                                soSchdCpoNumList.add((String) resultMap.get("SAME_ORD_CHK_KEY"));
                                totTblIndexC++;
                            }

                            if (tblIndexC < cMsg.C.length()) {

                                ZYPEZDItemValueSetter.setValue(cMsg.C.no(tblIndexC).trxHdrNumTpCd_C1, NLBL3050Constant.DISPLAY_TYPE_SO);
                                ZYPEZDItemValueSetter.setValue(cMsg.C.no(tblIndexC).trxHdrNum_C1, (String) resultMap.get("SO_NUM"));
                                ZYPEZDItemValueSetter.setValue(cMsg.C.no(tblIndexC).xxTblSortColNm_CS, (String) resultMap.get("CPO_ORD_NUM"));
                                ZYPEZDItemValueSetter.setValue(cMsg.C.no(tblIndexC).mdlNm_C1, (String) resultMap.get("MDL_NM"));
                                ZYPEZDItemValueSetter.setValue(cMsg.C.no(tblIndexC).xxDt10Dt_C1, (String) resultMap.get("SCHD_DELY_DT"));
                                ZYPEZDItemValueSetter.setValue(cMsg.C.no(tblIndexC).carrNm_C1, (String) resultMap.get("CARR_NM"));

                                if (ZYPCommonFunc.hasValue((BigDecimal) resultMap.get("SVC_CONFIG_MSTR_PK"))) {

                                    String configId = ZYPCommonFunc.concatString("Config#", " ", ((BigDecimal) resultMap.get("SVC_CONFIG_MSTR_PK")).toString());
                                    ZYPEZDItemValueSetter.setValue(cMsg.C.no(tblIndexC).shipToAcctNm_C1, configId);
                                }

                                tblIndexC++;
                            }

                            totTblIndexC++;
                            soCntC++;

                        /*************************************************************
                         * Scheduled SO Future (Table D)
                         ************************************************************/
                        } else if (ZYPDateUtil.compare(schdDelyDt, ZYPDateUtil.addDays(cMsg.slsDt.getValue(), 1)) > 0) {

                            if (soSchdFtuCpoNumList.isEmpty() || !soSchdFtuCpoNumList.contains((String) resultMap.get("SAME_ORD_CHK_KEY"))) {

                                if (!soSchdFtuCpoNumList.isEmpty() && cpoIndexD < cMsg.D.length()) {

                                    ZYPEZDItemValueSetter.setValue(cMsg.D.no(cpoIndexD).xxTotCnt_DO, BigDecimal.valueOf(soCntD));
                                }

                                soCntD = 0;

                                if (tblIndexD < cMsg.D.length()) {

                                    ZYPEZDItemValueSetter.setValue(cMsg.D.no(tblIndexD).trxHdrNumTpCd_D1, NLBL3050Constant.DISPLAY_TYPE_ORDER);
                                    ZYPEZDItemValueSetter.setValue(cMsg.D.no(tblIndexD).trxHdrNum_D1, (String) resultMap.get("CPO_ORD_NUM"));
                                    ZYPEZDItemValueSetter.setValue(cMsg.D.no(tblIndexD).xxTblSortColNm_DS, (String) resultMap.get("CPO_ORD_NUM"));
                                    ZYPEZDItemValueSetter.setValue(cMsg.D.no(tblIndexD).shipToAcctNm_D1, (String) resultMap.get("SHIP_TO_ACCT_NM"));
                                    ZYPEZDItemValueSetter.setValue(cMsg.D.no(tblIndexD).shipToCtyAddr_D1, (String) resultMap.get("SHIP_TO_CTY_ADDR"));
                                    ZYPEZDItemValueSetter.setValue(cMsg.D.no(tblIndexD).shipToStCd_D1, (String) resultMap.get("SHIP_TO_ST_CD"));

                                    cpoIndexD = tblIndexD;
                                    tblIndexD++;

                                } else {

                                    cpoIndexD = totTblIndexD;
                                }

                                soSchdFtuCpoNumList.add((String) resultMap.get("SAME_ORD_CHK_KEY"));
                                totTblIndexD++;
                            }

                            if (tblIndexD < cMsg.D.length()) {

                                ZYPEZDItemValueSetter.setValue(cMsg.D.no(tblIndexD).trxHdrNumTpCd_D1, NLBL3050Constant.DISPLAY_TYPE_SO);
                                ZYPEZDItemValueSetter.setValue(cMsg.D.no(tblIndexD).trxHdrNum_D1, (String) resultMap.get("SO_NUM"));
                                ZYPEZDItemValueSetter.setValue(cMsg.D.no(tblIndexD).xxTblSortColNm_DS, (String) resultMap.get("CPO_ORD_NUM"));
                                ZYPEZDItemValueSetter.setValue(cMsg.D.no(tblIndexD).mdlNm_D1, (String) resultMap.get("MDL_NM"));
                                ZYPEZDItemValueSetter.setValue(cMsg.D.no(tblIndexD).xxDt10Dt_D1, (String) resultMap.get("SCHD_DELY_DT"));
                                ZYPEZDItemValueSetter.setValue(cMsg.D.no(tblIndexD).carrNm_D1, (String) resultMap.get("CARR_NM"));

                                if (ZYPCommonFunc.hasValue((BigDecimal) resultMap.get("SVC_CONFIG_MSTR_PK"))) {

                                    String configId = ZYPCommonFunc.concatString("Config#", " ", ((BigDecimal) resultMap.get("SVC_CONFIG_MSTR_PK")).toString());
                                    ZYPEZDItemValueSetter.setValue(cMsg.D.no(tblIndexD).shipToAcctNm_D1, configId);
                                }

                                tblIndexD++;
                            }

                            totTblIndexD++;
                            soCntD++;
                        }

                    /*************************************************************
                     * Scheduled RWS (Table F)
                     ************************************************************/
                    } else if (ZYPCommonFunc.hasValue(rwsNum) && ZYPDateUtil.compare(cMsg.slsDt.getValue(), schdDelyDt) < 1) {

                        if (rwsSchdCpoNumList.isEmpty() || !rwsSchdCpoNumList.contains((String) resultMap.get("SAME_ORD_CHK_KEY"))) {

                            if (!rwsSchdCpoNumList.isEmpty() && cpoIndexF < cMsg.F.length()) {

                                ZYPEZDItemValueSetter.setValue(cMsg.F.no(cpoIndexF).xxTotCnt_FI, BigDecimal.valueOf(rwsCntF));
                            }

                            rwsCntF = 0;

                            if (tblIndexF < cMsg.F.length()) {

                                ZYPEZDItemValueSetter.setValue(cMsg.F.no(tblIndexF).trxHdrNumTpCd_F1, NLBL3050Constant.DISPLAY_TYPE_ORDER);
                                ZYPEZDItemValueSetter.setValue(cMsg.F.no(tblIndexF).trxHdrNum_F1, (String) resultMap.get("CPO_ORD_NUM"));
                                ZYPEZDItemValueSetter.setValue(cMsg.F.no(tblIndexF).xxTblSortColNm_FS, (String) resultMap.get("CPO_ORD_NUM"));
                                ZYPEZDItemValueSetter.setValue(cMsg.F.no(tblIndexF).shipToAcctNm_F1, (String) resultMap.get("SHIP_TO_ACCT_NM"));
                                ZYPEZDItemValueSetter.setValue(cMsg.F.no(tblIndexF).shipToCtyAddr_F1, (String) resultMap.get("SHIP_TO_CTY_ADDR"));
                                ZYPEZDItemValueSetter.setValue(cMsg.F.no(tblIndexF).shipToStCd_F1, (String) resultMap.get("SHIP_TO_ST_CD"));

                                cpoIndexF = tblIndexF;
                                tblIndexF++;

                            } else {

                                cpoIndexF = totTblIndexF;
                            }

                            rwsSchdCpoNumList.add((String) resultMap.get("SAME_ORD_CHK_KEY"));
                            totTblIndexF++;
                        }

                        if (tblIndexF < cMsg.F.length()) {

                            ZYPEZDItemValueSetter.setValue(cMsg.F.no(tblIndexF).trxHdrNumTpCd_F1, NLBL3050Constant.DISPLAY_TYPE_RWS);
                            ZYPEZDItemValueSetter.setValue(cMsg.F.no(tblIndexF).trxHdrNum_F1, (String) resultMap.get("RWS_NUM"));
                            ZYPEZDItemValueSetter.setValue(cMsg.F.no(tblIndexF).xxTblSortColNm_FS, (String) resultMap.get("CPO_ORD_NUM"));
                            ZYPEZDItemValueSetter.setValue(cMsg.F.no(tblIndexF).mdlNm_F1, (String) resultMap.get("MDL_NM"));
                            ZYPEZDItemValueSetter.setValue(cMsg.F.no(tblIndexF).xxDt10Dt_F1, (String) resultMap.get("SCHD_DELY_DT"));
                            ZYPEZDItemValueSetter.setValue(cMsg.F.no(tblIndexF).carrNm_F1, (String) resultMap.get("CARR_NM"));

                            if (ZYPCommonFunc.hasValue((BigDecimal) resultMap.get("SVC_CONFIG_MSTR_PK"))) {

                                String configId = ZYPCommonFunc.concatString("Config#", " ", ((BigDecimal) resultMap.get("SVC_CONFIG_MSTR_PK")).toString());
                                ZYPEZDItemValueSetter.setValue(cMsg.F.no(tblIndexF).shipToAcctNm_F1, configId);
                            }

                            tblIndexF++;
                        }

                        totTblIndexF++;
                        rwsCntF++;
                    }
                }
            }

            /*************************************************************
             * Remaining Data
             ************************************************************/
            if (BigDecimal.ZERO.compareTo(BigDecimal.valueOf(soCntA)) < 0 || BigDecimal.ZERO.compareTo(BigDecimal.valueOf(rwsCntA)) < 0) {

                if (!avalCpoNumList.isEmpty() && cpoIndexA < cMsg.A.length()) {

                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(cpoIndexA).xxTotCnt_AO, BigDecimal.valueOf(soCntA));
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(cpoIndexA).xxTotCnt_AI, BigDecimal.valueOf(rwsCntA));
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(cpoIndexA).delyCoordStsDescTxt_A1, schdCoordStsDescTxt);
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(cpoIndexA).shipToAcctNm_A1, shipToAcctNm);
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(cpoIndexA).shipToAddr_A1, shipToLineAddr);
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(cpoIndexA).shipToCtyAddr_A1, shipToCtyAddr);
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(cpoIndexA).shipToStCd_A1, shipToStCd);

                    ZYPEZDItemValueSetter.setValue(cMsg.L.no(cpoIndexA).xxTotCnt_LO, BigDecimal.valueOf(soCntA));
                    ZYPEZDItemValueSetter.setValue(cMsg.L.no(cpoIndexA).xxTotCnt_LI, BigDecimal.valueOf(rwsCntA));
                    ZYPEZDItemValueSetter.setValue(cMsg.L.no(cpoIndexA).delyCoordStsDescTxt_L1, schdCoordStsDescTxt);
                    ZYPEZDItemValueSetter.setValue(cMsg.L.no(cpoIndexA).shipToAcctNm_L1, shipToAcctNm);
                    ZYPEZDItemValueSetter.setValue(cMsg.L.no(cpoIndexA).shipToAddr_L1, shipToLineAddr);
                    ZYPEZDItemValueSetter.setValue(cMsg.L.no(cpoIndexA).shipToCtyAddr_L1, shipToCtyAddr);
                    ZYPEZDItemValueSetter.setValue(cMsg.L.no(cpoIndexA).shipToStCd_L1, shipToStCd);
                }
            }

            if (BigDecimal.ZERO.compareTo(BigDecimal.valueOf(soCntT)) < 0 || BigDecimal.ZERO.compareTo(BigDecimal.valueOf(rwsCntT)) < 0) {

                if (!todayCpoNumList.isEmpty() && cpoIndexT < cMsg.T.length()) {

                    ZYPEZDItemValueSetter.setValue(cMsg.T.no(cpoIndexT).xxTotCnt_TO, BigDecimal.valueOf(soCntT));
                    ZYPEZDItemValueSetter.setValue(cMsg.T.no(cpoIndexT).xxTotCnt_TI, BigDecimal.valueOf(rwsCntT));
                    ZYPEZDItemValueSetter.setValue(cMsg.T.no(cpoIndexT).delyCoordStsDescTxt_T1, schdCoordStsDescTxtT);
                    ZYPEZDItemValueSetter.setValue(cMsg.T.no(cpoIndexT).shipToAcctNm_T1, shipToAcctNmT);
                    ZYPEZDItemValueSetter.setValue(cMsg.T.no(cpoIndexT).shipToAddr_T1, shipToLineAddrT);
                    ZYPEZDItemValueSetter.setValue(cMsg.T.no(cpoIndexT).shipToCtyAddr_T1, shipToCtyAddrT);
                    ZYPEZDItemValueSetter.setValue(cMsg.T.no(cpoIndexT).shipToStCd_T1, shipToStCdT);
                }
            }

            if (!soSchdCpoNumList.isEmpty() && cpoIndexC < cMsg.C.length() && BigDecimal.ZERO.compareTo(BigDecimal.valueOf(soCntC)) < 0) {

                ZYPEZDItemValueSetter.setValue(cMsg.C.no(cpoIndexC).xxTotCnt_CO, BigDecimal.valueOf(soCntC));
            }

            if (!soSchdFtuCpoNumList.isEmpty() && cpoIndexD < cMsg.D.length() && BigDecimal.ZERO.compareTo(BigDecimal.valueOf(soCntD)) < 0) {

                ZYPEZDItemValueSetter.setValue(cMsg.D.no(cpoIndexD).xxTotCnt_DO, BigDecimal.valueOf(soCntD));
            }

            if (!rwsSchdCpoNumList.isEmpty() && cpoIndexF < cMsg.F.length() && BigDecimal.ZERO.compareTo(BigDecimal.valueOf(rwsCntF)) < 0) {

                ZYPEZDItemValueSetter.setValue(cMsg.F.no(cpoIndexF).xxTotCnt_FI, BigDecimal.valueOf(rwsCntF));
            }

            /*************************************************************
             * Total Outbound & Inbound
             ************************************************************/
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_A, BigDecimal.valueOf(cpoCntA));
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_OA, BigDecimal.valueOf(totSoCntA));
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_IA, BigDecimal.valueOf(totRwsCntA));
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_L, BigDecimal.valueOf(cpoCntA));
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_OL, BigDecimal.valueOf(totSoCntA));
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_IL, BigDecimal.valueOf(totRwsCntA));
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_T, BigDecimal.valueOf(cpoCntT));
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_OT, BigDecimal.valueOf(totSoCntT));
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_IT, BigDecimal.valueOf(totRwsCntT));

            cMsg.A.setValidCount(tblIndexA);
            cMsg.L.setValidCount(tblIndexA);
            cMsg.C.setValidCount(tblIndexC);
            cMsg.D.setValidCount(tblIndexD);
            cMsg.F.setValidCount(tblIndexF);
            cMsg.T.setValidCount(tblIndexT);

            if (tblIndexA == 0 && tblIndexC == 0 && tblIndexD == 0 && tblIndexF == 0) {

                return NLBL3050Constant.NZZM0000E;

            } else if (tblIndexA < cMsg.A.length() && tblIndexC < cMsg.C.length() && tblIndexD < cMsg.D.length() && tblIndexF < cMsg.F.length()) {

                return NLBL3050Constant.NZZM0002I;

            } else {

                return NLBL3050Constant.NZZM0001W;
            }

        } else {

            return NLBL3050Constant.NZZM0000E;
        }
    }

    /**
     * Search Back Order
     * @param cMsg NLBL3050CMsg
     * @param srchRtlWhList List<String>
     * @return String
     */
    private String searchBackOrd(NLBL3050CMsg cMsg, List<String> srchRtlWhList) {

        if(ZYPCommonFunc.hasValue(cMsg.psnCd_H)){

            return NLBL3050Constant.NZZM0000E;
        }

        S21SsmEZDResult ssmResult = NLBL3050Query.getInstance().getBackOrder(cMsg, srchRtlWhList);

        if (ssmResult.isCodeNormal()) {

            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

            List<String> boCpoNumList = new ArrayList<String>();
            int tblIndexB = 0;
            int totTblIndexB = 0;
            int cpoIndexB = 0;
            int soCntB = 0;
            int rmaCntB = 0;
            int cpoCntB = 0;
            int totSoCntB = 0;
            int totRmaCntB = 0;
            final String DISPLAY_NAME_AWAITING_BACKORDER_FULFILLMENT = ZYPCodeDataUtil.getName(SCHD_COORD_STS.class, cMsg.glblCmpyCd.getValue(), SCHD_COORD_STS.AWAITING_BACKORDER_FULFILLMENT);

            for (Map<String, Object> resultMap : resultList) {

                if (boCpoNumList.isEmpty() || !boCpoNumList.contains((String) resultMap.get("SAME_ORD_CHK_KEY"))) {

                    if (!boCpoNumList.isEmpty() && cpoIndexB < cMsg.B.length()) {

                        ZYPEZDItemValueSetter.setValue(cMsg.B.no(cpoIndexB).xxTotCnt_BO, BigDecimal.valueOf(soCntB));
                        ZYPEZDItemValueSetter.setValue(cMsg.B.no(cpoIndexB).xxTotCnt_BI, BigDecimal.valueOf(rmaCntB));
                    }

                    soCntB = 0;
                    rmaCntB = 0;

                    if (tblIndexB < cMsg.B.length()) {

                        ZYPEZDItemValueSetter.setValue(cMsg.B.no(tblIndexB).trxHdrNum_B1, (String) resultMap.get("CPO_ORD_NUM"));
                        // QC#15650 and 16283 update START
                        // If CR_HLD_QTY>0, SalesOrder# is not in back order yet. Still waiting its hold to be released.
                        //if (BigDecimal.ZERO.compareTo((BigDecimal) resultMap.get("CR_HLD_QTY")) < 0) {
                        if (ZYPConstant.FLG_ON_Y.equals((String) resultMap.get("HOLD_FLG"))) {
                        // QC#15650 and 16283 update END
                            ZYPEZDItemValueSetter.setValue(cMsg.B.no(tblIndexB).delyCoordStsDescTxt_B1, NLBL3050Constant.DISPLAY_NAME_AWAITING_HOLD_RELEASE);
                            ZYPEZDItemValueSetter.setValue(cMsg.B.no(tblIndexB).xxAncrCtrlFlg_B1, ZYPConstant.FLG_OFF_N);
                        } else {
                            ZYPEZDItemValueSetter.setValue(cMsg.B.no(tblIndexB).delyCoordStsDescTxt_B1, DISPLAY_NAME_AWAITING_BACKORDER_FULFILLMENT);
                            ZYPEZDItemValueSetter.setValue(cMsg.B.no(tblIndexB).xxAncrCtrlFlg_B1, ZYPConstant.FLG_ON_Y);
                        }
                        ZYPEZDItemValueSetter.setValue(cMsg.B.no(tblIndexB).shipToAcctNm_B1, (String) resultMap.get("SHIP_TO_ACCT_NM"));
                        ZYPEZDItemValueSetter.setValue(cMsg.B.no(tblIndexB).shipToAddr_B1, (String) resultMap.get("SHIP_TO_LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(cMsg.B.no(tblIndexB).shipToCtyAddr_B1, (String) resultMap.get("SHIP_TO_CTY_ADDR"));
                        ZYPEZDItemValueSetter.setValue(cMsg.B.no(tblIndexB).shipToStCd_B1, (String) resultMap.get("SHIP_TO_ST_CD"));

                        if (ZYPCommonFunc.hasValue((BigDecimal) resultMap.get("ORD_BOOK_TS"))) {

                            ZYPEZDItemValueSetter.setValue(cMsg.B.no(tblIndexB).xxOrdTs_B1, ((BigDecimal) resultMap.get("ORD_BOOK_TS")).toString());
                            ZYPEZDItemValueSetter.setValue(cMsg.B.no(tblIndexB).cutOffAot_BS, (BigDecimal) resultMap.get("ORD_BOOK_TS"));
                        }

                        cpoIndexB = tblIndexB;
                        tblIndexB++;

                    } else {

                        cpoIndexB = totTblIndexB;
                    }

                    boCpoNumList.add((String) resultMap.get("SAME_ORD_CHK_KEY"));
                    cpoCntB++;
                    totTblIndexB++;
                }

                if (ZYPCommonFunc.hasValue((String) resultMap.get("SHPG_PLN_NUM"))) {

                    soCntB++;
                    totSoCntB++;

                } else {

                    rmaCntB++;
                    totRmaCntB++;
                }
            }

            if (!boCpoNumList.isEmpty() && cpoIndexB < cMsg.B.length() &&
                    (BigDecimal.ZERO.compareTo(BigDecimal.valueOf(soCntB)) < 0 || BigDecimal.ZERO.compareTo(BigDecimal.valueOf(rmaCntB)) < 0)) {

                ZYPEZDItemValueSetter.setValue(cMsg.B.no(cpoIndexB).xxTotCnt_BO, BigDecimal.valueOf(soCntB));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(cpoIndexB).xxTotCnt_BI, BigDecimal.valueOf(rmaCntB));
            }

            ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_B, BigDecimal.valueOf(cpoCntB));
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_OB, BigDecimal.valueOf(totSoCntB));
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_IB, BigDecimal.valueOf(totRmaCntB));

            cMsg.B.setValidCount(tblIndexB);

            if (tblIndexB == 0) {

                return NLBL3050Constant.NZZM0000E;

            } else if (tblIndexB < cMsg.B.length()) {

                return NLBL3050Constant.NZZM0002I;

            } else {

                return NLBL3050Constant.NZZM0001W;
            }

        } else {

            return NLBL3050Constant.NZZM0000E;
        }
    }

    /**
     * Check Same Value
     * @param val1 String
     * @param val2 String
     * @return String
     */
    private String chkSameVal(String val1, String val2) {

        if (!ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {

            return null;

        } else if (!ZYPCommonFunc.hasValue(val1) && ZYPCommonFunc.hasValue(val2)) {

            return null;

        } else if (ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {

            return null;

        } else if (!val1.equals(val2)) {

            return null;
        }

        return val1;
    }
}
