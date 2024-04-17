/**
 *<pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 *</pre>
 */
package business.blap.NLCL1000;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLCL1000.common.NLCL1000CommonLogic;
import business.blap.NLCL1000.constant.NLCL1000Constant;
import business.db.ALL_MDSE_VTMsg;
import business.db.ALL_MDSE_VTMsgArray;
import business.parts.NLZC300001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC300001.NLZC300001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Stock Over View Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/07/17   Fujitsu         Y.Mori          Create          N/A
 * 2016/05/20   CSAI            K.Lee           Update          QC#7441
 * 2018/07/12   Fujitsu         Hd.Sugawara     Update          QC#21076
 * 2018/07/24   Fujitsu         Hd.Sugawara     Update          QC#21076-1
 * 2018/08/26   CITS            K.Ogino         Update          QC#27450
 *</pre>
 */
public class NLCL1000BL02 extends S21BusinessHandler implements NLCL1000Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLCL1000_INIT.equals(screenAplID)) {
                doProcess_NLCL1000Scrn00_INIT((NLCL1000CMsg) cMsg, (NLCL1000SMsg) sMsg);

            } else if (EVENT_NM_NLCL1000_SEARCH.equals(screenAplID)) {
                doProcess_NLCL1000Scrn00_Search((NLCL1000CMsg) cMsg, (NLCL1000SMsg) sMsg);

            } else if (EVENT_NM_NLCL1000_PAGE_NEXT.equals(screenAplID)) {
                doProcess_NLCL1000Scrn00_PageNext((NLCL1000CMsg) cMsg, (NLCL1000SMsg) sMsg);

            } else if (EVENT_NM_NLCL1000_PAGE_PREV.equals(screenAplID)) {
                doProcess_NLCL1000Scrn00_PagePrev((NLCL1000CMsg) cMsg, (NLCL1000SMsg) sMsg);

            } else if (EVENT_NM_NLCL1000_CMN_CLEAR.equals(screenAplID)) {
                doProcess_NLCL1000Scrn00_CMN_Clear((NLCL1000CMsg) cMsg, (NLCL1000SMsg) sMsg);

            } else if (EVENT_NM_NLCL1000_CMN_CLOSE.equals(screenAplID)) {
                doProcess_NLCL1000Scrn00_CMN_Close((NLCL1000CMsg) cMsg, (NLCL1000SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * @param bizMsg NLCL1000CMsg
     * @param sMsg NLCL1000SMsg
     */
    private void doProcess_NLCL1000Scrn00_INIT(NLCL1000CMsg bizMsg, NLCL1000SMsg sMsg) {
        doProcess_Search_Common(bizMsg, sMsg);
    }

    /**
     * @param bizMsg NLCL1000CMsg
     * @param sMsg NLCL1000SMsg
     */
    private void doProcess_NLCL1000Scrn00_Search(NLCL1000CMsg bizMsg, NLCL1000SMsg sMsg) {
        doProcess_Search_Common(bizMsg, sMsg);
    }

    /**
     * @param bizMsg NLCL1000CMsg
     * @param sMsg NLCL1000SMsg
     */
    private void doProcess_Search_Common(NLCL1000CMsg bizMsg, NLCL1000SMsg sMsg) {

        Map<String, Object> mapMdseCdSupdMdseCdForCurPArrRec = new HashMap<String, Object>();
        List<String> mdseCdListWithOrdTake = new ArrayList<String>();
        List<String> finalMdseCdList = new ArrayList<String>();
        Map<String, Object> mapMdseCdSupdMdseCdForAllPArrRec = new HashMap<String, Object>();

        List<String> externalLocList = new ArrayList<String>();
        String externalLocConstValue = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_COSNT_KEY_NLCL1000_DROP_SHIP_RTL_WH_CD, getGlobalCompanyCode());
        if (externalLocConstValue != null) {
            String[] externalLocConstArr = externalLocConstValue.split(",");
            for (int i = 0; i < externalLocConstArr.length; i++) {
                externalLocList.add(externalLocConstArr[i]);
            }
        } else {
            externalLocList.add(DEF_DROP_SHIP_RTL_WH_CD);
        }

        List<Map<String, Object>> procRecMapList = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < bizMsg.P.getValidCount(); i++) {
            // Check ORD_TAKE_MDSE
            if (checkOrdTakeMdseInfo(bizMsg.P.no(i).mdseCd_P1.getValue())) {
                List<String> listProcMdseCd = getMdseCdListFromEightDigitMdseCd(bizMsg.P.no(i).mdseCd_P1.getValue());
                for (int j = 0; j < listProcMdseCd.size(); j++) {
                    mdseCdListWithOrdTake.add((String)listProcMdseCd.get(j));
                    boolean existsFinalList = false;
                    for (int j2 = 0; j2 < finalMdseCdList.size(); j2++) {
                        if (finalMdseCdList.get(j2).equals((String)listProcMdseCd.get(j))) {
                            existsFinalList = true;
                        }
                    }
                    if (!existsFinalList) {
                        finalMdseCdList.add((String)listProcMdseCd.get(j));
                    }
                }
            } else {
                // Not ORD_TAKE_MDSE
                mdseCdListWithOrdTake.add(bizMsg.P.no(i).mdseCd_P1.getValue());
                boolean existsFinalList = false;
                for (int j2 = 0; j2 < finalMdseCdList.size(); j2++) {
                    if (finalMdseCdList.get(j2).equals(bizMsg.P.no(i).mdseCd_P1.getValue())) {
                        existsFinalList = true;
                    }
                }
                if (!existsFinalList) {
                    finalMdseCdList.add(bizMsg.P.no(i).mdseCd_P1.getValue());
                }
            }
            // Check Supersede MDSE
            for (int j = 0; j < mdseCdListWithOrdTake.size(); j++) {
                String origMdseCd = mdseCdListWithOrdTake.get(j);
                List<String> listSupdMdseCd = getSupdMdseCdList(origMdseCd);

                while (listSupdMdseCd.size() > 0) {
                    mapMdseCdSupdMdseCdForCurPArrRec.put(origMdseCd, listSupdMdseCd);
                    if (!mapMdseCdSupdMdseCdForAllPArrRec.containsKey(origMdseCd)) {
                        mapMdseCdSupdMdseCdForAllPArrRec.put(origMdseCd, listSupdMdseCd);
                    }
                    boolean whileBreak = false;
                    for (int k = 0; k < listSupdMdseCd.size(); k++) {
                        String supdMdseCd = listSupdMdseCd.get(k);

                        if (mapMdseCdSupdMdseCdForCurPArrRec.containsKey(supdMdseCd)) {
                            // Avoid infinite loop.
                            whileBreak = true;
                        } else {
                            boolean existsFinalList = false;
                            for (int j2 = 0; j2 < finalMdseCdList.size(); j2++) {
                                if (finalMdseCdList.get(j2).equals(supdMdseCd)) {
                                    existsFinalList = true;
                                }
                            }
                            if (!existsFinalList) {
                                finalMdseCdList.add(supdMdseCd);
                            }
                            origMdseCd = supdMdseCd;
                        }
                    }
                    if (whileBreak) {
                        break;
                    }
                    listSupdMdseCd = getSupdMdseCdList(origMdseCd);
                }
            }

            for (int j = 0; j < finalMdseCdList.size(); j++) {
                // Add to procRecMapList
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("RTL_WH_CD", bizMsg.P.no(i).rtlWhCd_P1.getValue());
                map.put("RTL_SWH_CD", bizMsg.P.no(i).rtlSwhCd_P1.getValue());
                map.put("INVTY_LOC_CD", bizMsg.P.no(i).invtyLocCd_P1.getValue());
                map.put("SHIP_TO_CUST_CD", bizMsg.P.no(i).shipToCustCd_P1.getValue());
                map.put("MDSE_CD", finalMdseCdList.get(j));
                BigDecimal ordQty = bizMsg.P.no(i).ordQty_P1.getValue();
                if (!ZYPCommonFunc.hasValue(ordQty)) {
                    ordQty = BigDecimal.ZERO;
                }
                map.put("ORD_QTY", ordQty);
                map.put("RDD_DT", bizMsg.P.no(i).rddDt_P1.getValue());
                map.put("LINE_NUM", bizMsg.P.no(i).xxScrItem20Txt_P1.getValue());
                map.put("DPLY_LINE_NUM", bizMsg.P.no(i).xxScrItem20Txt_P2.getValue());
                procRecMapList.add(map);
            }

            // Initialize map and array for next P array records.
            mapMdseCdSupdMdseCdForCurPArrRec = new HashMap<String, Object>();
            mdseCdListWithOrdTake = new ArrayList<String>();
            finalMdseCdList = new ArrayList<String>();
        }

        // Call Inventory Reference API
        NLZC300001PMsg csaPMsg = new NLZC300001PMsg();
        NLZC300001PMsg cusaPMsg = new NLZC300001PMsg();

        ZYPEZDItemValueSetter.setValue(csaPMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(csaPMsg.procDt, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        ZYPEZDItemValueSetter.setValue(csaPMsg.xxRqstFlg_01, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(csaPMsg.xxRqstFlg_02, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(csaPMsg.xxRqstFlg_03, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(csaPMsg.xxRqstFlg_04, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(csaPMsg.xxRqstFlg_WH, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(csaPMsg.xxRqstFlg_TC, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(cusaPMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(cusaPMsg.procDt, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        ZYPEZDItemValueSetter.setValue(cusaPMsg.xxRqstFlg_01, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cusaPMsg.xxRqstFlg_02, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(cusaPMsg.xxRqstFlg_03, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(cusaPMsg.xxRqstFlg_04, ZYPConstant.FLG_OFF_N);

        int csaDetailListCnt = 0;
        int cusaDetailListCnt = 0;

        // Reflect Inventory Reference API result on screen.
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        sMsg.A.clear();
        sMsg.A.setValidCount(0);

        String curLineNum = null;
        String curDplyLineNum = null;
        String prevLineNum = null;
        String prevDplyLineNum = null;

        String curRtlWhCd = null;
        String curRtlSwhCd = null;
        String prevRtlWhCd = null;
        String prevRtlSwhCd = null;

        for (int i = 0; i <= procRecMapList.size(); i++) {

            if (sMsg.A.getValidCount() == sMsg.A.length()) {
                break;
            }

            if (i < procRecMapList.size()) {
                curLineNum = (String) procRecMapList.get(i).get("LINE_NUM");
                curDplyLineNum = (String) procRecMapList.get(i).get("DPLY_LINE_NUM");
                curRtlWhCd = (String) procRecMapList.get(i).get("RTL_WH_CD");
                curRtlSwhCd = (String) procRecMapList.get(i).get("RTL_SWH_CD");
            }

            if ((i > 0 && !curLineNum.equals(prevLineNum)) || i == procRecMapList.size()) {

                csaPMsg.xxDetailList.setValidCount(csaDetailListCnt);
                cusaPMsg.xxDetailList.setValidCount(cusaDetailListCnt);

                // Del Start 2018/07/12 QC#21076
                //// Inventory Reference API
                //NLZC300001 nLZC300001 = new NLZC300001();
                // Del End 2018/07/12 QC#21076
                if (csaDetailListCnt > 0) {
                    // Add Start 2018/07/12 QC#21076
                    // Inventory Reference API
                    NLZC300001 nLZC300001 = new NLZC300001();
                    // Add End 2018/07/12 QC#21076
                    nLZC300001.execute(csaPMsg, ONBATCH_TYPE.ONLINE);
                    setResultToSMsg(bizMsg, sMsg, csaPMsg, mapMdseCdSupdMdseCdForAllPArrRec, prevLineNum, prevDplyLineNum, prevRtlWhCd, prevRtlSwhCd);
                    ZYPTableUtil.clear(csaPMsg.xxDetailList);
                }
                if (cusaDetailListCnt > 0) {
                    // Add Start 2018/07/12 QC#21076
                    // Inventory Reference API
                    NLZC300001 nLZC300001 = new NLZC300001();
                    // Add End 2018/07/12 QC#21076
                    nLZC300001.execute(cusaPMsg, ONBATCH_TYPE.ONLINE);
                    setResultToSMsg(bizMsg, sMsg, cusaPMsg, mapMdseCdSupdMdseCdForAllPArrRec, prevLineNum, prevDplyLineNum, prevRtlWhCd, prevRtlSwhCd);
                    ZYPTableUtil.clear(cusaPMsg.xxDetailList);
                }
                csaDetailListCnt = 0;
                cusaDetailListCnt = 0;
            }

            if (i < procRecMapList.size()) {
                Map<String, Object> map = (Map<String, Object>) procRecMapList.get(i);

                // Mod Start 2018/07/12 QC#21076
                //if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_AL.getValue()) || ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_NW.getValue()) || !externalLocList.contains((String)map.get("RTL_WH_CD"))) {
                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_AL.getValue()) || ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_NW.getValue()) || //
                        (!externalLocList.contains((String)map.get("RTL_WH_CD")) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_DS.getValue()))) {
                    // Mod End 2018/07/12 QC#21076
                    if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_AL.getValue()) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_NW.getValue())) {
                        String rtlWhCd = (String) map.get("RTL_WH_CD");
                        String rtlSwhCd = (String) map.get("RTL_SWH_CD");
                        ZYPEZDItemValueSetter.setValue(csaPMsg.xxDetailList.no(csaDetailListCnt).rtlWhCd, rtlWhCd);
                        ZYPEZDItemValueSetter.setValue(csaPMsg.xxDetailList.no(csaDetailListCnt).rtlSwhCd, rtlSwhCd);
                    } else if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_AL.getValue()) && ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_NW.getValue())) {
                        ZYPEZDItemValueSetter.setValue(csaPMsg.xxDetailList.no(csaDetailListCnt).rtlSwhCd, "NEW");
                    }
                    ZYPEZDItemValueSetter.setValue(csaPMsg.xxDetailList.no(csaDetailListCnt).shipToCustCd, (String)map.get("SHIP_TO_CUST_CD"));
                    ZYPEZDItemValueSetter.setValue(csaPMsg.xxDetailList.no(csaDetailListCnt).mdseCd, (String)map.get("MDSE_CD"));
                    ZYPEZDItemValueSetter.setValue(csaPMsg.xxDetailList.no(csaDetailListCnt).ordQty, (BigDecimal)map.get("ORD_QTY"));
                    csaPMsg.xxDetailList.no(csaDetailListCnt).rsdDt.clear();
                    ZYPEZDItemValueSetter.setValue(csaPMsg.xxDetailList.no(csaDetailListCnt).rddDt, (String)map.get("RDD_DT"));
                    csaDetailListCnt++;
                }

                // Add Start 2018/07/24 QC#21076-1
                if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_AL.getValue()) && //
                        !ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_NW.getValue()) && //
                        !ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_DS.getValue())) {
                // Add End 2018/07/24 QC#21076-1

                if (externalLocList.contains((String)map.get("RTL_WH_CD"))) {
                    ZYPEZDItemValueSetter.setValue(cusaPMsg.xxDetailList.no(cusaDetailListCnt).shipToCustCd, (String) map.get("RTL_WH_CD"));
                    ZYPEZDItemValueSetter.setValue(cusaPMsg.xxDetailList.no(cusaDetailListCnt).mdseCd, (String)map.get("MDSE_CD"));
                    ZYPEZDItemValueSetter.setValue(cusaPMsg.xxDetailList.no(cusaDetailListCnt).ordQty, (BigDecimal)map.get("ORD_QTY"));
                    cusaPMsg.xxDetailList.no(cusaDetailListCnt).rsdDt.clear();
                    ZYPEZDItemValueSetter.setValue(cusaPMsg.xxDetailList.no(cusaDetailListCnt).rddDt, (String)map.get("RDD_DT"));
                    cusaDetailListCnt++;
                }

                // Add Start 2018/07/24 QC#21076-1
                }
                // Add End 2018/07/24 QC#21076-1

                // Add Start 2018/07/12 QC#21076
                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_DS.getValue()) && //
                        cusaDetailListCnt < cusaPMsg.xxDetailList.length()) {
                    for (String extLocCd : externalLocList) {
                        // Del Start 2018/07/24 QC#21076-1
                        //if (extLocCd.equals((String) map.get("RTL_WH_CD"))) {
                        //    continue;
                        //}
                        // Del End 2018/07/24 QC#21076-1

                        ZYPEZDItemValueSetter.setValue(cusaPMsg.xxDetailList.no(cusaDetailListCnt).shipToCustCd, extLocCd);
                        ZYPEZDItemValueSetter.setValue(cusaPMsg.xxDetailList.no(cusaDetailListCnt).mdseCd, (String) map.get("MDSE_CD"));
                        ZYPEZDItemValueSetter.setValue(cusaPMsg.xxDetailList.no(cusaDetailListCnt).ordQty, (BigDecimal) map.get("ORD_QTY"));
                        cusaPMsg.xxDetailList.no(cusaDetailListCnt).rsdDt.clear();
                        ZYPEZDItemValueSetter.setValue(cusaPMsg.xxDetailList.no(cusaDetailListCnt).rddDt, (String) map.get("RDD_DT"));
                        cusaDetailListCnt++;

                        if (cusaDetailListCnt == cusaPMsg.xxDetailList.length()) {
                            break;
                        }
                    }
                }
                // Add End 2018/07/12 QC#21076

                if (csaDetailListCnt == csaPMsg.xxDetailList.length()) {
                    break;
                }

                if (cusaDetailListCnt == cusaPMsg.xxDetailList.length()) {
                    break;
                }

                prevLineNum = curLineNum;
                prevDplyLineNum = curDplyLineNum;
                prevRtlWhCd = curRtlWhCd;
                prevRtlSwhCd = curRtlSwhCd;
            }
        }

        // Inventory Reference API normally completed.
        if (sMsg.A.getValidCount() == 0) {
            bizMsg.setMessageInfo(NLCM0002I);
        } else {
            bizMsg.setMessageInfo(ZZM8002I);
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            boolean isCsaInvty = false;
            if (sMsg.A.no(i).xxRsltFlg_EX.getValue().equals(ZYPConstant.FLG_OFF_N)) {
                isCsaInvty = true;
            }

            // Available Location Only 
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxSetFlg_AV.getValue())) {
                // Available
                if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxScrItem10Txt_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxScrItem10Txt_A1, AVAILABILITY_YES);
                } else if (ZYPConstant.FLG_OFF_N.equals(sMsg.A.no(i).xxScrItem10Txt_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxScrItem10Txt_A1, AVAILABILITY_NO);
                } else {
                    if (new BigDecimal(sMsg.A.no(i).xxScrItem10Txt_A1.getValue()).compareTo(sMsg.A.no(i).ordQty_A1.getValue()) >= 0) {
                        if (!isCsaInvty) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxScrItem10Txt_A1, AVAILABILITY_YES);
                        }
                    } else {
                        if (!isCsaInvty) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxScrItem10Txt_A1, AVAILABILITY_NO);
                        }
                    }
                }
            } else {
                if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxScrItem10Txt_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxScrItem10Txt_A1, AVAILABILITY_YES);
                } else if (ZYPConstant.FLG_OFF_N.equals(sMsg.A.no(i).xxScrItem10Txt_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxScrItem10Txt_A1, AVAILABILITY_NO);
                } else {
                    if (new BigDecimal(sMsg.A.no(i).xxScrItem10Txt_A1.getValue()).compareTo(sMsg.A.no(i).ordQty_A1.getValue()) >= 0) {
                        if (!isCsaInvty) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxScrItem10Txt_A1, AVAILABILITY_YES);
                        }
                    } else {
                        if (!isCsaInvty) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxScrItem10Txt_A1, AVAILABILITY_NO);
                        }
                    }
                }
            }
        }

        int i = 0;
        for (; i < sMsg.A.getValidCount(); i++) {
            if (i == bizMsg.A.length()) {
                break;
            }
            EZDMsg.copy(sMsg.A.no(i), null, bizMsg.A.no(i), null);
        }
        bizMsg.A.setValidCount(i);
        if (bizMsg.A.getValidCount() == 0) {
            bizMsg.xxPageShowFromNum_A1.setValue(0);
        } else {
            bizMsg.xxPageShowFromNum_A1.setValue(1);
        }
        bizMsg.xxPageShowToNum_A1.setValue(bizMsg.A.getValidCount());
        bizMsg.xxPageShowOfNum_A1.setValue(sMsg.A.getValidCount());
    }

    /**
     * @param bizMsg
     * @param sMsg
     */
    private void doProcess_NLCL1000Scrn00_PageNext(NLCL1000CMsg bizMsg, NLCL1000SMsg sMsg) {

        NLCL1000CommonLogic.copyPage(bizMsg, bizMsg.A, sMsg.A);
        NLCL1000CommonLogic.nextPage(bizMsg, sMsg);
    }

    /**
     * @param bizMsg
     * @param sMsg
     */
    private void doProcess_NLCL1000Scrn00_PagePrev(NLCL1000CMsg bizMsg, NLCL1000SMsg sMsg) {

        NLCL1000CommonLogic.copyPage(bizMsg, bizMsg.A, sMsg.A);
        NLCL1000CommonLogic.prevPage(bizMsg, sMsg);
    }

    /**
     * @param bizMsg NLCL1000CMsg
     * @param sMsg NLCL1000SMsg
     */
    private void doProcess_NLCL1000Scrn00_CMN_Clear(NLCL1000CMsg bizMsg, NLCL1000SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_NW, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_AL, ZYPConstant.FLG_OFF_N);
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
    }

    /**
     * @param bizMsg NLCL1000CMsg
     * @param sMsg NLCL1000SMsg
     */
    private void doProcess_NLCL1000Scrn00_CMN_Close(NLCL1000CMsg bizMsg, NLCL1000SMsg sMsg) {
        NLCL1000CommonLogic.copyPage(bizMsg, bizMsg.A, sMsg.A);
        bizMsg.P.clear();
        bizMsg.P.setValidCount(0);
        int pmsgCnt = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {

                ZYPEZDItemValueSetter.setValue(bizMsg.P.no(pmsgCnt).xxScrItem20Txt_P1, sMsg.A.no(i).xxScrItem20Txt_A1.getValue());
                if (sMsg.A.no(i).xxRsltFlg_EX.getValue().equals(ZYPConstant.FLG_OFF_N)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.P.no(pmsgCnt).rtlWhCd_P1, sMsg.A.no(i).rtlWhCd_A1.getValue());
                    ZYPEZDItemValueSetter.setValue(bizMsg.P.no(pmsgCnt).rtlSwhCd_P1, sMsg.A.no(i).rtlSwhCd_A1.getValue());
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.P.no(pmsgCnt).invtyLocCd_P1, sMsg.A.no(i).invtyLocCd_A1.getValue());
                }
                ZYPEZDItemValueSetter.setValue(bizMsg.P.no(pmsgCnt).mdseCd_P1, sMsg.A.no(i).mdseCd_A1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.P.no(pmsgCnt).ordQty_P1, sMsg.A.no(i).ordQty_A1);
                ZYPEZDItemValueSetter.setValue(bizMsg.P.no(pmsgCnt).mdseCd_PS, sMsg.A.no(i).mdseCd_AS.getValue());
                pmsgCnt++;
            }
        }
        bizMsg.P.setValidCount(pmsgCnt);
    }

    private NLCL1000Query getQuery() {
        return NLCL1000Query.getInstance();
    }
    
    private String getMdseNm(String mdseCd) {
//        MDSETMsg mdseTMsg = new MDSETMsg();
//        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, getGlobalCompanyCode());
//        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);
//        mdseTMsg = (MDSETMsg) S21FastTBLAccessor.findByKey(mdseTMsg);
//        if (mdseTMsg == null) {
//            return null;
//        } else {
//            return mdseTMsg.mdseNm.getValue();
//        }
        ALL_MDSE_VTMsg cond = new ALL_MDSE_VTMsg();
        ALL_MDSE_VTMsgArray outAllMdseVTMsg = null;
        cond.setSQLID("003");
        cond.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        cond.setConditionValue("mdseCd01", mdseCd);

        outAllMdseVTMsg = (ALL_MDSE_VTMsgArray) EZDTBLAccessor.findByCondition(cond);
        if (outAllMdseVTMsg == null || outAllMdseVTMsg.length() == 0) {
            return null;
        } else {
            return ((ALL_MDSE_VTMsg)outAllMdseVTMsg.get(0)).mdseDescShortTxt.getValue();
        }
    }

    /**
     * @param mdseCd String
     * @return List<String>
     */
    @SuppressWarnings("unchecked")
    private List<String> getSupdMdseCdList(String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(BIND_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(BIND_MDSE_CD, mdseCd);
        S21SsmEZDResult ssmResult = getQuery().getSupdMdseCd(ssmParam);

        if (ssmResult.isCodeNormal()) {
            List<String> supdMdseCdList = (List<String>) ssmResult.getResultObject();
            return supdMdseCdList;
        } else {
            return new ArrayList<String>();
        }
    }

    /**
     * @param ordTakeMdseCd String
     * @return boolean 
     */
    private boolean checkOrdTakeMdseInfo(String ordTakeMdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(BIND_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(BIND_ORD_TAKE_MDSE_CD, ordTakeMdseCd);
        S21SsmEZDResult ssmResult = getQuery().checkOrdTakeMdseInfo(ssmParam);

        if (ssmResult.isCodeNormal()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param mdseCd String
     * @return List<String>
     */
    @SuppressWarnings("unchecked")
    private List<String> getMdseCdListFromEightDigitMdseCd(String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(BIND_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(BIND_MDSE_CD, mdseCd);
        ssmParam.put(BIND_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);
        S21SsmEZDResult ssmResult = getQuery().getMdseCdListFromEightDigitMdseCd(ssmParam);

        if (ssmResult.isCodeNormal()) {
            return (List<String>) ssmResult.getResultObject();
        } else {
            return new ArrayList<String>();
        }
    }

    private void setResultToSMsg(NLCL1000CMsg bizMsg, NLCL1000SMsg sMsg, NLZC300001PMsg pMsg, Map<String, Object> mapMdseCdSupdMdseCdForAllPArrRec, String lineNum, String dplyLineNum, String rtlWhCd, String rtlSwhCd) {

        // Get only Original Merchandise Code API result
        int sMsgCnt = sMsg.A.getValidCount();
        for (int j = 0; j < pMsg.xxDetailList.getValidCount(); j++) {
            if (sMsgCnt == sMsg.A.length()) {
                bizMsg.setMessageInfo(NLCM0001W);
                break;
            }

            boolean isExternal = false;
            boolean isSkip = false;

            if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxDetailList.no(j).xxRsltFlg_EX.getValue())) {
                isExternal = true;
            }

            if (isExternal) {
                isSkip = false;
            } else if (pMsg.xxDetailList.no(j).rtlWhCd.getValue().equals(rtlWhCd) && pMsg.xxDetailList.no(j).rtlSwhCd.getValue().equals(rtlSwhCd)) {
                isSkip = false;
            } else if (pMsg.xxDetailList.no(j).rtlWhCd.getValue().equals(rtlWhCd) && !ZYPCommonFunc.hasValue(rtlSwhCd)) {
                isSkip = false;
            } else {
                // QC#27450
                if (!ZYPCommonFunc.hasValue(rtlWhCd) && !ZYPCommonFunc.hasValue(rtlSwhCd) //
                        && ZYPCommonFunc.hasValue(pMsg.xxDetailList.no(j).xxAvalQty) //
                        && BigDecimal.ZERO.compareTo(pMsg.xxDetailList.no(j).xxAvalQty.getValue()) < 0) {
                    isSkip = false;
                } else {
                    if (bizMsg.xxChkBox_AL.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
                        isSkip = false;
                    } else if (!bizMsg.xxChkBox_AL.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && !bizMsg.xxChkBox_NW.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
                        isSkip = true;
                    } else if (!bizMsg.xxChkBox_AL.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.xxChkBox_NW.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
                        if (pMsg.xxDetailList.no(j).xxRsltFlg_EX.getValue().equals(ZYPConstant.FLG_OFF_N) && !pMsg.xxDetailList.no(j).rtlSwhCd.getValue().equals("NEW")) {
                            isSkip = true;
                        }
                    }
                    if (!isSkip) {
                        // QC#27450
                        if (bizMsg.xxSetFlg_AV.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && pMsg.xxDetailList.no(j).xxAvalOrdFlg.getValue().equals(ZYPConstant.FLG_OFF_N) //
                                && ZYPCommonFunc.hasValue(pMsg.xxDetailList.no(j).xxAvalQty) //
                                && BigDecimal.ZERO.compareTo(pMsg.xxDetailList.no(j).xxAvalQty.getValue()) >= 0) {

                            isSkip = true;
                        }
                    }
                }
            }

            if (isSkip) {
                continue;
            }

            ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgCnt).mdseCd_A1, pMsg.xxDetailList.no(j).mdseCd); // Item Code
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgCnt).mdseDescShortTxt_A1, getMdseNm(pMsg.xxDetailList.no(j).mdseCd.getValue())); // Item Description

            List<String> listSupdMdseCd = (List<String>) mapMdseCdSupdMdseCdForAllPArrRec.get(pMsg.xxDetailList.no(j).mdseCd.getValue());
            String supdMdseCd = "";
            if (listSupdMdseCd != null) {
                for (int k = 0; k < listSupdMdseCd.size(); k++) {
                    if (ZYPCommonFunc.hasValue(listSupdMdseCd.get(k))) {
                        supdMdseCd = listSupdMdseCd.get(k);
                        break;
                    }
                }
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxSetFlg_DI.getValue()) &&  ZYPCommonFunc.hasValue(supdMdseCd)) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgCnt).mdseCd_AS, supdMdseCd); // Supersede Item
            } else {
                sMsg.A.no(sMsgCnt).mdseCd_AS.clear(); // Supersede Item
            }

            ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgCnt).invtyLocCd_A1, pMsg.xxDetailList.no(j).invtyLocCd.getValue()); // Inventory Qty (Hidden)
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgCnt).stkStsCd_A1, STK_STS.GOOD); // SS
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgCnt).ordQty_A1, pMsg.xxDetailList.no(j).ordQty); // Request Qty
            
            if (ZYPCommonFunc.hasValue(pMsg.xxDetailList.no(j).xxAvalQty)) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgCnt).xxScrItem10Txt_A1, pMsg.xxDetailList.no(j).xxAvalQty.getValue().toString()); // Availability
            } else{
                if (ZYPCommonFunc.hasValue(pMsg.xxDetailList.no(j).xxAvalOrdFlg)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgCnt).xxScrItem10Txt_A1, pMsg.xxDetailList.no(j).xxAvalOrdFlg.getValue()); // Availability
                } else {
                    if (pMsg.xxDetailList.no(j).xxRsltFlg_EX.getValue().equals(ZYPConstant.FLG_OFF_N)) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgCnt).xxScrItem10Txt_A1, BigDecimal.ZERO.toString()); // Availability
                    } else {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgCnt).xxScrItem10Txt_A1, ZYPConstant.FLG_OFF_N); // Availability
                    }
                }
            }

            ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgCnt).etaDt_A1, pMsg.xxDetailList.no(j).xxWhInEtaDtTxt.getValue()); // ETA
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgCnt).xxScrItem20Txt_A1, lineNum); // Line#
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgCnt).xxScrItem20Txt_A2, dplyLineNum); // Display Line#
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgCnt).whNm_A1, pMsg.xxDetailList.no(j).locNm.getValue()); // Location Name
            if (ZYPCommonFunc.hasValue(pMsg.xxDetailList.no(j).rtlWhCd)) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgCnt).rtlWhCd_A1, pMsg.xxDetailList.no(j).rtlWhCd.getValue()); // Retail Warehouse
            } else {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgCnt).rtlWhCd_A1, pMsg.xxDetailList.no(j).invtyLocCd.getValue()); // Retail Warehouse
            }

            ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgCnt).rtlSwhCd_A1, pMsg.xxDetailList.no(j).rtlSwhCd.getValue()); // Retail Sub Warehouse
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgCnt).locTpCd_A1, pMsg.xxDetailList.no(j).locTpCd.getValue()); // Location Type Code
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgCnt).xxRsltFlg_EX, pMsg.xxDetailList.no(j).xxRsltFlg_EX.getValue()); // External Flag
            sMsgCnt++;
        }
        sMsg.A.setValidCount(sMsgCnt);
    }
}
