/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NPAL1410;

import static business.blap.NPAL1410.constant.NPAL1410Constant.BUSINESS_APPLICATION_ID;
import static business.blap.NPAL1410.constant.NPAL1410Constant.DEFAULT_SWH;
import static business.blap.NPAL1410.constant.NPAL1410Constant.EVENT_TYPE_CC;
import static business.blap.NPAL1410.constant.NPAL1410Constant.EVENT_TYPE_CO;
import static business.blap.NPAL1410.constant.NPAL1410Constant.EVENT_TYPE_NPAL6800;
import static business.blap.NPAL1410.constant.NPAL1410Constant.EVENT_TYPE_P;
import static business.blap.NPAL1410.constant.NPAL1410Constant.NPAM0002E;
import static business.blap.NPAL1410.constant.NPAL1410Constant.NPAM0076E;
import static business.blap.NPAL1410.constant.NPAL1410Constant.NPAM0224E;
import static business.blap.NPAL1410.constant.NPAL1410Constant.NPAM1577W;
import static business.blap.NPAL1410.constant.NPAL1410Constant.NPAM1582W;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1410.common.NPAL1410CommonLogic;
import business.db.MDL_NMTMsg;
import business.parts.NSZC048001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC048001.NSZC048001;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RMNF_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RMNF_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NPAL1410 Reman Workbench
 * Function Name : 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/05/2016   CITS       Yasushi Nomura       Create          N/A
 * 09/06/2016   CITS            S.Endo          Update          QC#14000
 * 10/05/2016   CITS            T.Hakodate      Update          QC#13276
 * 10/07/2016   CITS            T.Wada          Update          QC#13807
 * 10/17/2016   CITS            T.Wada          Update          QC#14010
 * 10/17/2016   CITS            T.Wada          Update          QC#14011
 * 10/19/2016   CITS            T.Wada          Update          QC#14010
 * 10/20/2016   CITS            T.Wada          Update          QC#15220
 * 10/21/2016   CITS            T.Wada          Update          QC#13882
 * 10/25/2016   CITS            T.Wada          Update          QC#13875
 * 09/20/2017   CITS            T.Kikuhara      Update          QC#18675(SOL#219)
 * 10/16/2017   CITS            K.Ogino         Update          QC#21805
 * 10/24/2017   CITS            T.Kikuhara      Update          QC#21856
 * 12/15/2017   CITS            K.Ogino         Update          QC#22836
 * 06/11/2018   CITS            K.Ogino         Update          QC#26486
 * 07/13/2018   CITS            T.Hakodate      Update          QC#26868
 * 01/16/2019   Fujitsu         T.Ogura         Update          QC#29893
 * 01/31/2019   CITS            T.Hakodate      Update          QC#30019
 * 04/01/2019   CITS            K.Ogino         Update          QC#30851
 *</pre>
 */
public class NPAL1410BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (("NPAL1410_INIT".equals(screenAplID)) || ("NPAL1410Scrn00_CMN_Reset".equals(screenAplID))) {
                doProcess_NPAL1410_INIT((NPAL1410CMsg) cMsg, (NPAL1410SMsg) sMsg);
            } else if ("NPAL1410Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NPAL1410Scrn00_CMN_Clear((NPAL1410CMsg) cMsg, (NPAL1410SMsg) sMsg);
            } else if (("NPAL1410Scrn00_Search".equals(screenAplID)) || ("NPAL1410Scrn00_DeleteTask".equals(screenAplID))) {
                doProcess_NPAL1410Scrn00_Search((NPAL1410CMsg) cMsg, (NPAL1410SMsg) sMsg);
            //QC#18675 ADD START
            } else if (("NPAL1410Scrn00_SearchSoRws".equals(screenAplID))) {
                doProcess_NPAL1410Scrn00_SearchSoRws((NPAL1410CMsg) cMsg, (NPAL1410SMsg) sMsg);
            //QC#18675 ADD END
            } else if (("NPAL1410Scrn00_CMN_Save".equals(screenAplID)) || ("NPAL1410Scrn00_Create".equals(screenAplID)) || ("NPAL1410Scrn00_CMN_Submit".equals(screenAplID)) || ("NPAL1410Scrn00_Cancel".equals(screenAplID))) {
                doProcess_NPAL1410Scrn00_Search((NPAL1410CMsg) cMsg, (NPAL1410SMsg) sMsg);
            } else if ("NPAL1410Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NPAL1410Scrn00_PageNext((NPAL1410CMsg) cMsg, (NPAL1410SMsg) sMsg);
            } else if ("NPAL1410Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NPAL1410Scrn00_PagePrev((NPAL1410CMsg) cMsg, (NPAL1410SMsg) sMsg);
            } else if (("NPAL1410Scrn00_OpenWin_WH".equals(screenAplID)) || ("NPAL1410Scrn00_OpenWin_SWH".equals(screenAplID))) {
                ((NPAL1410CMsg) cMsg).xxPopPrm_H1.setValue(NMXC100001EnableWH.getLocRoleTpForPopup(getGlobalCompanyCode(), BUSINESS_APPLICATION_ID));
            } else if ("NPAL1410Scrn00_getConfig".equals(screenAplID)) {
                doProcess_NPAL1410Scrn00_getConfig((NPAL1410CMsg) cMsg, (NPAL1410SMsg) sMsg);
            } else if ("NPAL1410Scrn00_TAB_Parts".equals(screenAplID)) {
                doProcess_NPAL1410Scrn00_TAB_Parts((NPAL1410CMsg) cMsg, (NPAL1410SMsg) sMsg);
            //QC#18675 ADD START
            } else if ("NPAL1410Scrn00_TAB_SoRws".equals(screenAplID)) {
                doProcess_NPAL1410Scrn00_TAB_SoRws((NPAL1410CMsg) cMsg, (NPAL1410SMsg) sMsg);
            //QC#18675 ADD END
            } else if ("NPAL1410Scrn00_GetWhName".equals(screenAplID)) {
                doProcess_NPAL1410Scrn00_GetWhName((NPAL1410CMsg) cMsg, (NPAL1410SMsg) sMsg);
            } else if ("NPAL1410Scrn00_GetTechName".equals(screenAplID)) {
                doProcess_NPAL1410Scrn00_GetTechName((NPAL1410CMsg) cMsg, (NPAL1410SMsg) sMsg);
            } else if ("NPAL1410Scrn00_AddParts".equals(screenAplID)) {
                doProcess_NPAL1410Scrn00_AddParts((NPAL1410CMsg) cMsg, (NPAL1410SMsg) sMsg);
            } else if ("NPAL1410Scrn00_AddComponent".equals(screenAplID)) {
                doProcess_NPAL1410Scrn00_AddComponent((NPAL1410CMsg) cMsg, (NPAL1410SMsg) sMsg);
            } else if ("NPAL1410Scrn00_DeleteComponent".equals(screenAplID)) {
                doProcess_NPAL1410Scrn00_DeleteComponent((NPAL1410CMsg) cMsg, (NPAL1410SMsg) sMsg);
            } else if ("NPAL1410Scrn00_GetItemName_CC".equals(screenAplID)) {
                doProcess_NPAL1410Scrn00_GetItemName((NPAL1410CMsg) cMsg, (NPAL1410SMsg) sMsg, EVENT_TYPE_CC);
            } else if ("NPAL1410Scrn00_GetItemName_CO".equals(screenAplID)) {
                doProcess_NPAL1410Scrn00_GetItemName((NPAL1410CMsg) cMsg, (NPAL1410SMsg) sMsg, EVENT_TYPE_CO);
            } else if ("NPAL1410Scrn00_GetItemName_P".equals(screenAplID)) {
                doProcess_NPAL1410Scrn00_GetItemDetail((NPAL1410CMsg) cMsg, (NPAL1410SMsg) sMsg, EVENT_TYPE_P);
            } else if ("NPAL1410_NSAL1240".equals(screenAplID)) {
                doProcess_NPAL1410_NSAL1240((NPAL1410CMsg) cMsg, (NPAL1410SMsg) sMsg);
            } else if ("NPAL1410Scrn00_DeleteParts".equals(screenAplID)) {
                doProcess_NPAL1410Scrn00_DeleteParts((NPAL1410CMsg) cMsg, (NPAL1410SMsg) sMsg);
            } else if ("NPAL1410_NMAL6800".equals(screenAplID)) {
                doProcess_NPAL1410Scrn00_GetItemDetail((NPAL1410CMsg) cMsg, (NPAL1410SMsg) sMsg, EVENT_TYPE_NPAL6800);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     */
    private void doProcess_NPAL1410_INIT(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg) {
        String rmnfOrdNum = null;
        if (ZYPCommonFunc.hasValue(cMsg.rmnfOrdNum_TR)) {
            rmnfOrdNum = cMsg.rmnfOrdNum_TR.getValue();
        }

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(cMsg.C);
        ZYPTableUtil.clear(sMsg.C);
        ZYPTableUtil.clear(cMsg.D);
        ZYPTableUtil.clear(sMsg.D);
        //QC#18675 ADD START
        ZYPTableUtil.clear(cMsg.E);
        ZYPTableUtil.clear(sMsg.E);
        //QC#18675 ADD END
        cMsg.clear();
        sMsg.clear();

        // create pull down
        int index = 0;
        Map<String, String> ordTp = NPAL1410CommonLogic.getOrderType(getGlobalCompanyCode());
        for (Map.Entry<String, String> e : ordTp.entrySet()) {
            if (index >= cMsg.rmnfOrdTpCd_IX.length()) {
                break;
            }
            ZYPEZDItemValueSetter.setValue(cMsg.rmnfOrdTpCd_IX.no(index), e.getKey());
            ZYPEZDItemValueSetter.setValue(cMsg.rmnfOrdTpDescTxt_TX.no(index), e.getValue());
            index++;
        }
        sMsg.aftDeclPntDigitNum_HH.setValue(NPAL1410CommonLogic.getAftDeclPnt(getGlobalCompanyCode()));
        ZYPEZDItemValueSetter.setValue(cMsg.aftDeclPntDigitNum_HH, sMsg.aftDeclPntDigitNum_HH);
        if (rmnfOrdNum != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.rmnfOrdNum_TR, rmnfOrdNum);
            ZYPEZDItemValueSetter.setValue(cMsg.rmnfOrdNum_H1, rmnfOrdNum);
            doProcess_NPAL1410Scrn00_Search(cMsg, sMsg);
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.rmnfStartDt_H1, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        }

        // QC#22836
        // create Stock Status pull down
        NPAL1410CommonLogic.createPullDownStockStatus(cMsg, sMsg, getGlobalCompanyCode());
        cMsg.rmnfRtlSwhCd_H1.setValue(DEFAULT_SWH);
    }

    /**
     * Clear
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     */
    private void doProcess_NPAL1410Scrn00_CMN_Clear(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg) {
        String rmnfOrdNum = null;
        if (ZYPCommonFunc.hasValue(cMsg.rmnfOrdNum_TR)) {
            rmnfOrdNum = cMsg.rmnfOrdNum_TR.getValue();
        }

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(cMsg.C);
        ZYPTableUtil.clear(sMsg.C);
        ZYPTableUtil.clear(cMsg.D);
        ZYPTableUtil.clear(sMsg.D);
        //QC#18675 ADD START
        ZYPTableUtil.clear(cMsg.E);
        ZYPTableUtil.clear(sMsg.E);
        //QC#18675 ADD END
        cMsg.clear();
        sMsg.clear();

        // create pull down
        int index = 0;
        Map<String, String> ordTp = NPAL1410CommonLogic.getOrderType(getGlobalCompanyCode());
        for (Map.Entry<String, String> e : ordTp.entrySet()) {
            if (index >= cMsg.rmnfOrdTpCd_IX.length()) {
                break;
            }
            ZYPEZDItemValueSetter.setValue(cMsg.rmnfOrdTpCd_IX.no(index), e.getKey());
            ZYPEZDItemValueSetter.setValue(cMsg.rmnfOrdTpDescTxt_TX.no(index), e.getValue());
            index++;
        }
        if (rmnfOrdNum != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.rmnfOrdNum_TR, rmnfOrdNum);
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.rmnfStartDt_H1, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        }
        // QC#22836
        // create Stock Status pull down
        NPAL1410CommonLogic.createPullDownStockStatus(cMsg, sMsg, getGlobalCompanyCode());
        cMsg.rmnfRtlSwhCd_H1.setValue("NEW");
    }

    /**
     * <pre>
     * Serch Event
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     */
    private void doProcess_NPAL1410Scrn00_Search(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfOrdNum_BK, cMsg.rmnfOrdNum_H1);
        String globalCompanyCode = getGlobalCompanyCode();

        searchHeaderInfo(cMsg, sMsg, globalCompanyCode);
        if (0 < sMsg.A.getValidCount()) {
            searchPartsInfo(cMsg, sMsg, globalCompanyCode);
            searchTaskInfo(cMsg, sMsg, globalCompanyCode);
        }

        NPAL1410CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        //QC#18675 ADD START
        if (ZYPCommonFunc.hasValue(cMsg.xxDplyTab) && "SoRws".equals(cMsg.xxDplyTab.getValue())) {
            doProcess_NPAL1410Scrn00_SearchSoRws(cMsg, sMsg);
        }
        //QC#18675 ADD END
    }

    //QC#18675 ADD START
    /**
     * <pre>
     * Serch SO/RWS Event
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     */
    private void doProcess_NPAL1410Scrn00_SearchSoRws(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.E);
        ZYPTableUtil.clear(sMsg.E);

        // Search Default SO/RWS data
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("rmnfOrdNum", cMsg.rmnfOrdNum_H1);
        String[] sceOrdTpList = new String[3];
        sceOrdTpList[0] = SCE_ORD_TP.REMAN_LOCATOR_TRANSFER;
        sceOrdTpList[1] = SCE_ORD_TP.REMAN_PARTS_USAGE;
        sceOrdTpList[2] = SCE_ORD_TP.REMAN_PARTS_BACK_INVENTORY;
        if (ZYPCommonFunc.hasValue(cMsg.sceOrdTpCd_ES)) {
            sceOrdTpList = new String[1];
            sceOrdTpList[0] = cMsg.sceOrdTpCd_ES.getValue();
        }
        ssmParam.put("sceOrdTpList", sceOrdTpList);
        ssmParam.put("remanUsage", SCE_ORD_TP.REMAN_PARTS_USAGE);
        ssmParam.put("outbound", INBD_OTBD.OUTBOUND);
        ssmParam.put("inbound", INBD_OTBD.INBOUND);

        S21SsmEZDResult result = NPAL1410Query.getInstance().searchSoRws(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultMap = (List<Map>) result.getResultObject();
            for (int i = 0; i < resultMap.size(); i++) {
                if ((sMsg.E.length()) <= i) {
                    break;
                }
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).rwsNum_E1, (String) resultMap.get(i).get("SO_RWS_NUM"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).actvFlg_E1, (String) resultMap.get(i).get("RWS_NUM_FLG"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).sceOrdTpNm_E1, (String) resultMap.get(i).get("SCE_ORD_TP_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).rtlWhNm_E1, (String) resultMap.get(i).get("RTL_WH_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).rtlSwhNm_E1, (String) resultMap.get(i).get("RTL_SWH_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).shpgStsDescTxt_E1, (String) resultMap.get(i).get("STATUS"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).xxCratDt_E1, (String) resultMap.get(i).get("EZINTIME"));
                sMsg.E.setValidCount(i + 1);
            }
        } else {
            // not has search result
            cMsg.setMessageInfo(NPAM0002E);
            return;
        }
        NPAL1410CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }
    //QC#18675 ADD END

    /**
     * <pre>
     * Delete parts Event
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     */
    private void doProcess_NPAL1410Scrn00_DeleteParts(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg) {
        NPAL1410CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        List<NPAL1410_CSMsg> list = new ArrayList<NPAL1410_CSMsg>();
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.C.no(i).xxChkBox_C1.getValue())) {
                if (ZYPCommonFunc.hasValue(sMsg.C.no(i).rmnfPrtReqPk_C1)) {
                    list.add(sMsg.C.no(i));
                }
            } else {
                list.add(sMsg.C.no(i));
            }
        }
        for (int i = 0; i < list.size(); i++) {
            EZDMsg.copy(list.get(i), null, sMsg.C.no(i), null);
        }

        sMsg.C.setValidCount(list.size());
        
        // QC# 26868 ADD START
        // Standard Parts
        List<NPAL1410_BSMsg> standardPartsList = new ArrayList<NPAL1410_BSMsg>();
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(i).xxChkBox_B1.getValue())) {
                if (ZYPCommonFunc.hasValue(sMsg.B.no(i).rmnfPrtReqPk_B1)) {
                    standardPartsList.add(sMsg.B.no(i));
                }
            } else {
                standardPartsList.add(sMsg.B.no(i));
            }
        }
        for (int i = 0; i < standardPartsList.size(); i++) {
            EZDMsg.copy(standardPartsList.get(i), null, sMsg.B.no(i), null);
        }
        sMsg.B.setValidCount(standardPartsList.size());
        // QC# 26868 ADD END

        String msg = "Save";
        if (RMNF_ORD_STS.IN_PROCESS.equals(sMsg.rmnfOrdStsCd_HH.getValue())) {
            msg = "Create";
        }
        cMsg.setMessageInfo(NPAM1577W, new String[] {msg });
        NPAL1410CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
//        NPAL1410CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
//
//        List<NPAL1410_CSMsg> deleteRow = new ArrayList<NPAL1410_CSMsg>();
//        List<NPAL1410_CSMsg> normalRow = new ArrayList<NPAL1410_CSMsg>();
//        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
//            if (ZYPConstant.FLG_ON_Y.equals(sMsg.C.no(i).xxEdtModeFlg_C1.getValue())) {
//                // already deleted parts
//                deleteRow.add((NPAL1410_CSMsg) sMsg.C.no(i).clone());
//            } else if (ZYPConstant.FLG_ON_Y.equals(sMsg.C.no(i).xxChkBox_C1.getValue())) {
//                // new deleted parts
//                sMsg.C.no(i).xxEdtModeFlg_C1.setValue(ZYPConstant.FLG_ON_Y);
//                // deleteRow.add(sMsg.C.no(i));
//                deleteRow.add((NPAL1410_CSMsg) sMsg.C.no(i).clone());
//            } else {
//                normalRow.add((NPAL1410_CSMsg) sMsg.C.no(i).clone());
//            }
//        }
//        int rowCount = 0;
//        for (int i = 0; i < deleteRow.size(); i++) {
//            EZDMsg.copy(deleteRow.get(i), null, sMsg.C.no(rowCount), null);
//            rowCount++;
//        }
//        for (int i = 0; i < normalRow.size(); i++) {
//            EZDMsg.copy(normalRow.get(i), null, sMsg.C.no(rowCount), null);
//            rowCount++;
//        }
//        sMsg.C.setValidCount(rowCount);
//        String msg = "Save";
//        if (RMNF_ORD_STS.IN_PROCESS.equals(sMsg.rmnfOrdStsCd_HH.getValue())) {
//            msg = "Create";
//        }
//        cMsg.setMessageInfo(NPAM1577W, new String[] {msg });
//        NPAL1410CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }
//    private void doProcess_NPAL1410Scrn00_DeleteParts(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg) {
//        NPAL1410CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
//// QC#14005
//        String msg = "Save";
//        if (RMNF_ORD_STS.IN_PROCESS.equals(sMsg.rmnfOrdStsCd_HH.getValue())) {
//            msg = "Create";
//        }
//        cMsg.setMessageInfo(NPAM1577W, new String[] {msg });
//        NPAL1410CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
//    }

    /**
     * <pre>
     * serial search popup Event
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     */
    private void doProcess_NPAL1410_NSAL1240(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.rmnfMainUnitSerNum_H1)) {
            doProcess_NPAL1410Scrn00_getConfig(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * search header info
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param globalCompanyCode globalCompanyCode
     */
    private void searchHeaderInfo(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, String globalCompanyCode) {
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfOrdNum_H1, cMsg.rmnfOrdNum_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfOrdNum_BK, cMsg.rmnfOrdNum_BK);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfOrdNum_TR, cMsg.rmnfOrdNum_TR);
        sMsg.aftDeclPntDigitNum_HH.setValue(NPAL1410CommonLogic.getAftDeclPnt(getGlobalCompanyCode()));
        ZYPEZDItemValueSetter.setValue(cMsg.aftDeclPntDigitNum_HH, sMsg.aftDeclPntDigitNum_HH);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("swLicReqFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("rmnfOrdNum", sMsg.rmnfOrdNum_BK);
        //QC#18675 ADD START
        ssmParam.put("rmnItemChange", SCE_ORD_TP.REMAN_ITEM_CHANGE);
        //QC#18675 ADD END

        // Execute
        S21SsmEZDResult result = NPAL1410Query.getInstance().search(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultMap = (List<Map>) result.getResultObject();
            for (int i = 0; i < resultMap.size(); i++) {
                if ((sMsg.A.length()) <= i) {
                    break;
                }
                Map<String, Object> recode = (Map<String, Object>) resultMap.get(i);
                if (i == 0) {
                    // Header
                    setHeader(sMsg, recode, globalCompanyCode);
                }
                // Config Detail
                NPAL1410CommonLogic.setConfigDtl(sMsg, i, recode, false);
            }
        } else {
            // not has search result
            cMsg.setMessageInfo(NPAM0002E);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }

    /**
     * <pre>
     * search parts info
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param globalCompanyCode globalCompanyCode
     */
    private void searchPartsInfo(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, String globalCompanyCode) {
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(sMsg.C);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("rmnfOrdNum", cMsg.rmnfOrdNum_H1);
        ssmParam.put("invtyLocCd", sMsg.rmnfRtlWhCd_H1);
        ssmParam.put("locStsCd", LOC_STS.DC_STOCK);
        ssmParam.put("stkStsCd", STK_STS.GOOD);
        ssmParam.put("sceOrdTpCd", SCE_ORD_TP.REMAN_LOCATOR_TRANSFER);
        // Execute
        S21SsmEZDResult result = NPAL1410Query.getInstance().searchParts(ssmParam);

        // assetRecovCostAmt_H1
        BigDecimal assetRecovCostAmt = BigDecimal.ZERO;

        if (result.isCodeNormal()) {

            int stdPartsCount = 0;
            int nonStdPartsCount = 0;

            List<Map> resultMap = (List<Map>) result.getResultObject();
            for (int i = 0; i < resultMap.size(); i++) {
                Map<String, Object> recode = (Map<String, Object>) resultMap.get(i);

                assetRecovCostAmt = assetRecovCostAmt.add((BigDecimal) recode.get("ASSET_RECOV_COST_AMT"));

                if (ZYPConstant.FLG_ON_Y.equals((String) recode.get("RMNF_STD_PRT_FLG"))) {
                    setStdPartsDtl(sMsg, stdPartsCount, recode);
                    stdPartsCount++;
                } else {
                    setNonStdPartsDtl(sMsg, nonStdPartsCount, recode);
                    nonStdPartsCount++;
                }
            }
        }
        ZYPEZDItemValueSetter.setValue(sMsg.assetRecovCostAmt_H1, assetRecovCostAmt);
    }

    /**
     * <pre>
     * search task info
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param globalCompanyCode globalCompanyCode
     */
    private void searchTaskInfo(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, String globalCompanyCode) {
        ZYPTableUtil.clear(sMsg.D);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("rmnfOrdNum", cMsg.rmnfOrdNum_H1);
        ssmParam.put("rmnfTaskStsCd", RMNF_TASK_STS.CANCEL);
        // Execute
        S21SsmEZDResult result = NPAL1410Query.getInstance().searchTask(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultMap = (List<Map>) result.getResultObject();

            for (int i = 0; i < resultMap.size(); i++) {
                Map<String, Object> recode = (Map<String, Object>) resultMap.get(i);
                setTaskDtl(sMsg, i, recode);

            }
        }
    }

    /**
     * <pre>
     * getInvtyLocCd
     * </pre>
     * @param sMsg NPAL1410SMsg
     */
    private String getInvtyLocCd(NPAL1410SMsg sMsg) {

        String whCd = "";
        String swhCd = "";
        if (ZYPCommonFunc.hasValue(sMsg.rmnfRtlWhCd_H1)) {
            whCd = sMsg.rmnfRtlWhCd_H1.getValue();
        }
        if (ZYPCommonFunc.hasValue(sMsg.rmnfRtlSwhCd_H1)) {
            swhCd = sMsg.rmnfRtlSwhCd_H1.getValue();
        }
        return whCd + swhCd;
    }

    /**
     * <pre>
     * set header info
     * </pre>
     * @param sMsg NPAL1410SMsg
     * @param recode Map<String, Object>
     * @param globalCompanyCode globalCompanyCode
     */
    private String setHeader(NPAL1410SMsg sMsg, Map<String, Object> recode, String globalCompanyCode) {

        ZYPEZDItemValueSetter.setValue(sMsg.rmnfRtlWhCd_H1, (String) recode.get("RMNF_RTL_WH_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.rtlWhNm_H1, (String) recode.get("RTL_WH_NM"));
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfOrdNum_H1, (String) recode.get("RMNF_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsg.ownrTechTocCd_H1, (String) recode.get("OWNR_TECH_TOC_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.techNm_H1, (String) recode.get("TECH_NM"));
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfMainUnitSerNum_H1, (String) recode.get("RMNF_MAIN_UNIT_SER_NUM"));
        // QC#30851
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfMainUnitSerNum_HH, (String) recode.get("RMNF_MAIN_UNIT_SER_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfOrdStsNm_H1, (String) recode.get("RMNF_ORD_STS_NM"));
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfOrdTpCd_SE, (String) recode.get("RMNF_ORD_TP_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.whLoctrCd_H1, (String) recode.get("WH_LOCTR_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfRtlSwhCd_H1, (String) recode.get("RMNF_RTL_SWH_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfStartDt_H1, (String) recode.get("RMNF_START_DT"));
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfEndDt_H1, (String) recode.get("RMNF_END_DT"));
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfMainUnitMdseCd_HH, (String) recode.get("RMNF_MAIN_UNIT_MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.t_MdlNm_H1, (String) recode.get("T_MDL_NM"));
        //QC#18675 ADD START
        ZYPEZDItemValueSetter.setValue(sMsg.t_MdlNm_H2, (String) recode.get("O_MDL_NM"));
        //QC#18675 ADD END
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfOrdCmntTxt_H1, (String) recode.get("RMNF_ORD_CMNT_TXT"));

        if (ZYPConstant.FLG_ON_Y.equals((String) recode.get("OPEN_STS_FLG"))) {

            NLXC001001GetInventoryItemCostBean parm = new NLXC001001GetInventoryItemCostBean();
            parm.setGlblCmpyCd(globalCompanyCode);
            parm.setInvtyLocCd(getInvtyLocCd(sMsg));
            parm.setMdseCd(sMsg.rmnfMainUnitMdseCd_HH.getValue());
            parm.setQty(BigDecimal.ONE);
            parm = NLXC001001GetInventoryItemCost.getInventoryItemCost(parm);

            if (0 < parm.getErrList().size()) {
                return parm.getErrList().get(0);
            }

            ZYPEZDItemValueSetter.setValue(sMsg.rmnfMachCostAmt_H1, parm.getTotPrcAmt());

        } else {

            ZYPEZDItemValueSetter.setValue(sMsg.rmnfMachCostAmt_H1, (BigDecimal) recode.get("RMNF_MACH_COST_AMT"));
        }

      //QC#30019 mod start
        //if (RMNF_ORD_STS.COMPLETED.equals((String) recode.get("RMNF_ORD_STS_CD"))) {

        ZYPEZDItemValueSetter.setValue(sMsg.rmnfPrtUsgCostAmt_H1, (BigDecimal) recode.get("RMNF_PRT_USG_COST_AMT"));
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfTotLborCostAmt_H1, (BigDecimal) recode.get("RMNF_TOT_LBOR_COST_AMT"));

        BigDecimal rmnfTotCostAmt = (BigDecimal) recode.get("RMNF_TOT_COST_AMT");

        if (!ZYPCommonFunc.hasValue(rmnfTotCostAmt)) {
            ZYPEZDItemValueSetter.setValue(sMsg.rmnfTotCostAmt_H1, NPAL1410CommonLogic.getRmnfTotCostAmtBeforeComp(globalCompanyCode, sMsg));
        } else if (ZYPCommonFunc.hasValue(rmnfTotCostAmt) && rmnfTotCostAmt.compareTo(BigDecimal.ZERO) == 0) {
            ZYPEZDItemValueSetter.setValue(sMsg.rmnfTotCostAmt_H1, NPAL1410CommonLogic.getRmnfTotCostAmtBeforeComp(globalCompanyCode, sMsg));
        } else {
            ZYPEZDItemValueSetter.setValue(sMsg.rmnfTotCostAmt_H1, (BigDecimal) recode.get("RMNF_TOT_COST_AMT"));
        }

       // } else {

          //  // RMNF_PRT_USG_COST_AMT
          //  ZYPEZDItemValueSetter.setValue(sMsg.rmnfPrtUsgCostAmt_H1, NPAL1410CommonLogic.getRmnfPrtUsgCostAmt(globalCompanyCode, sMsg.rmnfOrdNum_H1.getValue(), null));

          //  // RMNF_TOT_LBOR_COST_AMT
          //  ZYPEZDItemValueSetter.setValue(sMsg.rmnfTotLborCostAmt_H1, NPAL1410CommonLogic.getRmnTotLborCostAmtBeforeComp(globalCompanyCode, sMsg.rmnfOrdNum_H1.getValue(), null));

          //  // RMNF_TOT_COST_AMT
          //  ZYPEZDItemValueSetter.setValue(sMsg.rmnfTotCostAmt_H1, NPAL1410CommonLogic.getRmnfTotCostAmtBeforeComp(globalCompanyCode, sMsg));
       // }
      //QC#30019 mod end
        
        // New Value
        NLXC001001GetInventoryItemCostBean parm = new NLXC001001GetInventoryItemCostBean();
        parm.setGlblCmpyCd(globalCompanyCode);
        parm.setInvtyLocCd(getInvtyLocCd(sMsg));
        parm.setMdseCd((String) recode.get("RMNF_TO_CMPT_MDSE_CD"));
        parm.setQty(BigDecimal.ONE);
        parm = NLXC001001GetInventoryItemCost.getInventoryItemCost(parm);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfTotCostAmt_H2, parm.getTotPrcAmt());

        // Config
        ZYPEZDItemValueSetter.setValue(sMsg.svcConfigMstrPk_A1, (BigDecimal) recode.get("SVC_CONFIG_MSTR_PK"));

        //QC#18675 ADD START
        ZYPEZDItemValueSetter.setValue(sMsg.rwsNum_A1, (String) recode.get("RWS_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsg.soNum_A1, (String) recode.get("SO_NUM"));
        //QC#18675 ADD END

        ZYPEZDItemValueSetter.setValue(sMsg.t_MdlNm_A1, sMsg.t_MdlNm_H1);
        // hidden
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfOrdStsCd_HH, (String) recode.get("RMNF_ORD_STS_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfInvtyLocCd_HH, (String) recode.get("RMNF_INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfUsgRtlWhCd_HH, (String) recode.get("RMNF_USG_RTL_WH_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfUsgRtlSwhCd_HH, (String) recode.get("RMNF_USG_RTL_SWH_CD"));

        ZYPEZDItemValueSetter.setValue(sMsg.rmnfMdlId_HH, (BigDecimal) recode.get("RMNF_MDL_ID"));

        //QC#18675 ADD START
        ZYPEZDItemValueSetter.setValue(sMsg.origMdlId_HH, (BigDecimal) recode.get("ORIG_MDL_ID"));
        //QC#18675 ADD END

        // additional info
        ZYPEZDItemValueSetter.setValue(sMsg.ezUpTime_HH, (String) recode.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(sMsg.ezUpTimeZone_HH, (String) recode.get("EZUPTIMEZONE"));

        return null;
    }

    /**
     * <pre>
     * set standard parts info
     * </pre>
     * @param sMsg NPAL1410SMsg
     * @param index row index
     * @param recode Map<String, Object>
     */
    private void setStdPartsDtl(NPAL1410SMsg sMsg, int index, Map<String, Object> recode) {

        if (sMsg.B.length() <= index) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(sMsg.B.no(index).mdseCd_B1, (String) recode.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(index).mdseDescShortTxt_B1, (String) recode.get("MDSE_DESC_SHORT_TXT"));
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(index).prtRqstQty_B1, (BigDecimal) recode.get("PRT_RQST_QTY"));
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(index).thisMthTotStdCostAmt_B1, (BigDecimal) recode.get("THIS_MTH_TOT_STD_COST_AMT"));
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(index).invtyAvalQty_B1, (BigDecimal) recode.get("INVTY_AVAL_QTY"));

        if (ZYPConstant.FLG_ON_Y.equals((String) recode.get("RTRN_REQ_PRT_FLG"))) {
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(index).rtrnReqPrtFlg_B1, ZYPConstant.FLG_ON_Y);
        } else {
            sMsg.B.no(index).rtrnReqPrtFlg_B1.clear();
        }

        ZYPEZDItemValueSetter.setValue(sMsg.B.no(index).assetRecovCostAmt_B1, (BigDecimal) recode.get("ASSET_RECOV_COST_AMT"));
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(index).procrTpNm_B1, (String) recode.get("PROCR_TP_NM"));
        // QC#26832 ADD START
        // QC#21856 ADD START
        if (!ZYPCommonFunc.hasValue((String) recode.get("SO_NUM"))) {
            if (ZYPCommonFunc.hasValue(sMsg.B.no(index).invtyAvalQty_B1) && (sMsg.B.no(index).invtyAvalQty_B1.getValue().compareTo(BigDecimal.ZERO) <= 0)) {
                String srcTpTxtBo = ZYPCodeDataUtil.getVarCharConstValue("NPAL1410_PARTS_SRC_TP_BO_TXT", getGlobalCompanyCode());
                if (ZYPCommonFunc.hasValue(srcTpTxtBo)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(index).procrTpNm_B1, srcTpTxtBo);
                }
            }
        }
        // QC#21856 ADD END
        // QC#26832 ADD END
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(index).prchReqNum_B1, (String) recode.get("PRCH_REQ_NUM"));

        // this column DPLY_VND_NM or RTL_WH_NM
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(index).dplyVndNm_B1, (String) recode.get("RTL_WH_NM"));

        if (RWS_STS.RECEIVED.equals((String) recode.get("RWS_STS_CD"))) {
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(index).xxFlgNm_B1, ZYPConstant.FLG_ON_Y);
        } else {
            sMsg.B.no(index).xxFlgNm_B1.clear();
        }

        ZYPEZDItemValueSetter.setValue(sMsg.B.no(index).rmnfPrtRqstProcCd_B1, (String) recode.get("RMNF_PRT_RQST_PROC_CD"));
        // QC#26486
        if (ZYPCommonFunc.hasValue((BigDecimal) recode.get("RMNF_PRT_QTY"))) {
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(index).rmnfPrtQty_B1, (BigDecimal) recode.get("RMNF_PRT_QTY"));
        } else {
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(index).rmnfPrtQty_B1, BigDecimal.ZERO);
        }
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(index).srcRtlSwhCd_BH, DEFAULT_SWH);
        
        // QC#26868 ADD START
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(index).rmnfPrtRqstProcCd_B1, (String) recode.get("RMNF_PRT_RQST_PROC_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(index).rmnfPrtReqPk_B1, (BigDecimal) recode.get("RMNF_PRT_REQ_PK"));
        // QC#26868 ADD END

        sMsg.B.setValidCount(index + 1);
    }

    /**
     * <pre>
     * set non standard parts info
     * </pre>
     * @param sMsg NPAL1410SMsg
     * @param index row index
     * @param recode Map<String, Object>
     */
    private void setNonStdPartsDtl(NPAL1410SMsg sMsg, int index, Map<String, Object> recode) {

        if (sMsg.C.length() <= index) {
            return;
        }

        sMsg.C.no(index).xxNewRowNum_C1.setValue(index);
        sMsg.C.no(index).xxChkBox_C1.clear();
        ZYPEZDItemValueSetter.setValue(sMsg.C.no(index).mdseCd_C1, (String) recode.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.C.no(index).mdseDescShortTxt_C1, (String) recode.get("MDSE_DESC_SHORT_TXT"));
        ZYPEZDItemValueSetter.setValue(sMsg.C.no(index).prtRqstQty_C1, (BigDecimal) recode.get("PRT_RQST_QTY"));
        ZYPEZDItemValueSetter.setValue(sMsg.C.no(index).thisMthTotStdCostAmt_C1, (BigDecimal) recode.get("THIS_MTH_TOT_STD_COST_AMT"));
        ZYPEZDItemValueSetter.setValue(sMsg.C.no(index).invtyAvalQty_C1, (BigDecimal) recode.get("INVTY_AVAL_QTY"));

        if (ZYPConstant.FLG_ON_Y.equals((String) recode.get("RTRN_REQ_PRT_FLG"))) {
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(index).rtrnReqPrtFlg_C1, ZYPConstant.FLG_ON_Y);
        } else {
            sMsg.C.no(index).rtrnReqPrtFlg_C1.clear();
        }

        ZYPEZDItemValueSetter.setValue(sMsg.C.no(index).assetRecovCostAmt_C1, (BigDecimal) recode.get("ASSET_RECOV_COST_AMT"));
        ZYPEZDItemValueSetter.setValue(sMsg.C.no(index).procrTpNm_C1, (String) recode.get("PROCR_TP_NM"));
        
        // QC#26832 ADD START
        // QC#21856 ADD START
        if (!ZYPCommonFunc.hasValue((String) recode.get("SO_NUM"))) {
            if (ZYPCommonFunc.hasValue(sMsg.C.no(index).invtyAvalQty_C1) && (sMsg.C.no(index).invtyAvalQty_C1.getValue().compareTo(BigDecimal.ZERO) <= 0)) {
                String srcTpTxtBo = ZYPCodeDataUtil.getVarCharConstValue("NPAL1410_PARTS_SRC_TP_BO_TXT", getGlobalCompanyCode());
                if (ZYPCommonFunc.hasValue(srcTpTxtBo)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.C.no(index).procrTpNm_C1, srcTpTxtBo);
                }
            }
        }
        // QC#21856 ADD END
        // QC#26832 ADD END
        
        ZYPEZDItemValueSetter.setValue(sMsg.C.no(index).prchReqNum_C1, (String) recode.get("PRCH_REQ_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsg.C.no(index).dplyVndNm_C1, (String) recode.get("RTL_WH_NM"));

        if (RWS_STS.RECEIVED.equals((String) recode.get("RWS_STS_CD"))) {
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(index).xxFlgNm_C1, ZYPConstant.FLG_ON_Y);
        } else {
            sMsg.C.no(index).xxFlgNm_C1.clear();
        }
        // QC#26486
        if (ZYPCommonFunc.hasValue((BigDecimal) recode.get("RMNF_PRT_QTY"))) {
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(index).rmnfPrtQty_C1, (BigDecimal) recode.get("RMNF_PRT_QTY"));
        } else {
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(index).rmnfPrtQty_C1, BigDecimal.ZERO);
        }
        ZYPEZDItemValueSetter.setValue(sMsg.C.no(index).srcRtlSwhCd_CH, DEFAULT_SWH);

        // QC#13807
        ZYPEZDItemValueSetter.setValue(sMsg.C.no(index).rmnfPrtRqstProcCd_C1, (String) recode.get("RMNF_PRT_RQST_PROC_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.C.no(index).rmnfPrtReqPk_C1, (BigDecimal) recode.get("RMNF_PRT_REQ_PK"));

        sMsg.C.setValidCount(index + 1);
    }

    /**
     * <pre>
     * set task info
     * </pre>
     * @param sMsg NPAL1410SMsg
     * @param index row index
     * @param recode Map<String, Object>
     */
    private void setTaskDtl(NPAL1410SMsg sMsg, int index, Map<String, Object> recode) {

        if (sMsg.D.length() <= index) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(sMsg.D.no(index).xxPopPrm_D1, (String) recode.get("RMNF_ORD_NUM") + "-" + (String) recode.get("RMNF_TASK_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsg.D.no(index).rmnfTaskDescTxt_D1, (String) recode.get("RMNF_TASK_DESC_TXT"));

        if (ZYPCommonFunc.hasValue((String) recode.get("RMNF_TASK_START_DT"))) {

            ZYPEZDItemValueSetter.setValue(sMsg.D.no(index).xxPopPrm_DF, ZYPDateUtil.formatEzd8ToDisp((String) recode.get("RMNF_TASK_START_DT"), true));

        } else {

            sMsg.D.no(index).xxPopPrm_DF.clear();
        }

        if (ZYPCommonFunc.hasValue((String) recode.get("RMNF_TASK_END_DT"))) {

            ZYPEZDItemValueSetter.setValue(sMsg.D.no(index).xxPopPrm_DT, ZYPDateUtil.formatEzd8ToDisp((String) recode.get("RMNF_TASK_END_DT"), true));

        } else {

            sMsg.D.no(index).xxPopPrm_DT.clear();
        }

        ZYPEZDItemValueSetter.setValue(sMsg.D.no(index).techTocCd_D1, (String) recode.get("TECH_TOC_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.D.no(index).rmnfTaskNum_DH, (String) recode.get("RMNF_TASK_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsg.D.no(index).rmnfTaskPk_DH, (BigDecimal) recode.get("RMNF_TASK_PK"));
        ZYPEZDItemValueSetter.setValue(sMsg.D.no(index).ezUpTime_DH, (String) recode.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(sMsg.D.no(index).ezUpTimeZone_DH, (String) recode.get("EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(sMsg.D.no(index).techNm_D1, (String) recode.get("TECH_NM"));
        ZYPEZDItemValueSetter.setValue(sMsg.D.no(index).rmnfLborAot_D1, (BigDecimal) recode.get("RMNF_LBOR_AOT"));
        
        //QC#30019 mod start

        //if (!RMNF_ORD_STS.COMPLETED.equals(sMsg.rmnfOrdStsCd_HH.getValue())) {

            //ZYPEZDItemValueSetter.setValue(sMsg.D.no(index).rmnfPrtUsgCostAmt_D1, NPAL1410CommonLogic.getRmnfPrtUsgCostAmt((String) recode.get("GLBL_CMPY_CD"), (String) recode.get("RMNF_ORD_NUM"), (String) recode.get("RMNF_TASK_NUM")));
            //ZYPEZDItemValueSetter.setValue(sMsg.D.no(index).rmnfLborCostAmt_D1, NPAL1410CommonLogic.getRmnTotLborCostAmtBeforeComp((String) recode.get("GLBL_CMPY_CD"), (String) recode.get("RMNF_ORD_NUM"), (String) recode
            //        .get("RMNF_TASK_NUM")));
            //ZYPEZDItemValueSetter.setValue(sMsg.D.no(index).rmnfTotCostAmt_D1, sMsg.D.no(index).rmnfPrtUsgCostAmt_D1.getValue().add(sMsg.D.no(index).rmnfLborCostAmt_D1.getValue()));

        //} else {

            ZYPEZDItemValueSetter.setValue(sMsg.D.no(index).rmnfPrtUsgCostAmt_D1, (BigDecimal) recode.get("RMNF_PRT_USG_COST_AMT"));
            ZYPEZDItemValueSetter.setValue(sMsg.D.no(index).rmnfLborCostAmt_D1, (BigDecimal) recode.get("RMNF_LBOR_COST_AMT"));
            ZYPEZDItemValueSetter.setValue(sMsg.D.no(index).rmnfTotCostAmt_D1, (BigDecimal) recode.get("RMNF_TOT_COST_AMT"));

        //}
      //QC#30019 mod end

        sMsg.D.setValidCount(index + 1);
    }

    /**
     * <pre>
     * PagePrev Event
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     */
    private void doProcess_NPAL1410Scrn00_PagePrev(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg) {
        NPAL1410CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length());
        NPAL1410CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * <pre>
     * PageNext Event
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     */
    private void doProcess_NPAL1410Scrn00_PageNext(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg) {
        NPAL1410CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length());
        NPAL1410CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * <pre>
     * GetConfig Event
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     */
    private void doProcess_NPAL1410Scrn00_getConfig(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg) {
        String globalCompanyCode = getGlobalCompanyCode();
        NPAL1410CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        Map<String, Object> recodeSerial = NPAL1410CommonLogic.checkGetConfigAndGetSerial(cMsg, sMsg, globalCompanyCode);
        if (recodeSerial == null) {
            return;
        }

//        sMsg.t_MdlNm_HH.clear();    // 2019/01/16 T.Ogura [QC#29893,DEL]
        sMsg.t_MdlNm_H2.clear();
        cMsg.t_MdlNm_H2.clear();
        sMsg.origMdlId_HH.clear();
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfMainUnitMdseCd_HH, (String) recodeSerial.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.svcConfigMstrPk_A1, (BigDecimal) recodeSerial.get("SVC_CONFIG_MSTR_PK"));
        if (ZYPCommonFunc.hasValue(sMsg.svcConfigMstrPk_A1)) {
            // Get Configration
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", globalCompanyCode);
            ssmParam.put("svcConfigMstrPk", (BigDecimal) recodeSerial.get("SVC_CONFIG_MSTR_PK"));
            S21SsmEZDResult result = NPAL1410Query.getInstance().searchConfigration(ssmParam);
            if (!result.isCodeNormal()) {
                cMsg.rmnfMainUnitSerNum_H1.setErrorInfo(1, NPAM0224E, new String[] {"Serial#" });
                return;
            }
            ZYPTableUtil.clear(sMsg.A);
            List<Map> resultMap = (List<Map>) result.getResultObject();
            for (int i = 0; i < resultMap.size(); i++) {
                if (sMsg.A.length() <= i) {
                    break;
                }
                Map<String, Object> recode = (Map<String, Object>) resultMap.get(i);
                // Config Detail
                NPAL1410CommonLogic.setConfigDtl(sMsg, i, recode, true);
            }
        } else {
            ZYPTableUtil.clear(sMsg.A);
            NPAL1410CommonLogic.setConfigDtl(sMsg, 0, recodeSerial, true);
        }

        if (ZYPCommonFunc.hasValue((String) recodeSerial.get("T_MDL_NM"))) {
            ZYPEZDItemValueSetter.setValue(sMsg.rmnfMdlId_HH, (BigDecimal) recodeSerial.get("T_MDL_ID"));
            ZYPEZDItemValueSetter.setValue(sMsg.t_MdlNm_H1, (String) recodeSerial.get("T_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(sMsg.t_MdlNm_A1, (String) recodeSerial.get("T_MDL_NM"));
            //QC#18675 ADD START
            if (!ZYPCommonFunc.hasValue(cMsg.rmnfOrdStsCd_HH)) {
                ZYPEZDItemValueSetter.setValue(sMsg.origMdlId_HH, (BigDecimal) recodeSerial.get("T_MDL_ID"));
                ZYPEZDItemValueSetter.setValue(sMsg.t_MdlNm_H2, (String) recodeSerial.get("T_MDL_NM"));
            }
            //QC#18675 ADD END
        } else {
            // QC#21805 Start
            NSZC048001PMsg pMsg = new NSZC048001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(globalCompanyCode));

            boolean isFirst = true;
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {

                Map<String, Object> item = NPAL1410CommonLogic.getSerial(sMsg.A.no(i).serNum_A1.getValue(), sMsg.A.no(i).mdseCd_A1.getValue(), globalCompanyCode);
                if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).mainUnitLineFlg_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(pMsg.prntMdseCd, sMsg.A.no(i).mdseCd_A1);
                    isFirst = false;
                } else {
                    if (isFirst) {
                        ZYPEZDItemValueSetter.setValue(pMsg.prntMdseCd, sMsg.A.no(i).mdseCd_A1);
                        isFirst = false;
                    }
                    int ix = pMsg.xxChildMdseCdList.getValidCount();
                    ZYPEZDItemValueSetter.setValue(pMsg.xxChildMdseCdList.no(ix++).childMdseCd, sMsg.A.no(i).mdseCd_A1);
                    pMsg.xxChildMdseCdList.setValidCount(ix);
                }
            }

            NSZC048001 smApi = new NSZC048001();
            smApi.execute(pMsg, ONBATCH_TYPE.ONLINE);
            if (!S21ApiUtil.isXxMsgId(pMsg) || ZYPCommonFunc.hasValue(pMsg.mdlId)) {
                MDL_NMTMsg inMsg = new MDL_NMTMsg();
                ZYPEZDItemValueSetter.setValue(inMsg.t_GlblCmpyCd, globalCompanyCode);
                ZYPEZDItemValueSetter.setValue(inMsg.t_MdlId, pMsg.mdlId);
                MDL_NMTMsg tMsg = (MDL_NMTMsg) EZDTBLAccessor.findByKey(inMsg);
                if (tMsg == null) {
                    return;
                }

                ZYPEZDItemValueSetter.setValue(sMsg.rmnfMdlId_HH, tMsg.t_MdlId.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.t_MdlNm_H1, tMsg.t_MdlNm.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.t_MdlNm_A1, tMsg.t_MdlNm.getValue());
                ZYPEZDItemValueSetter.setValue(cMsg.rmnfMdlId_HH, tMsg.t_MdlId.getValue());
                ZYPEZDItemValueSetter.setValue(cMsg.t_MdlNm_H1, tMsg.t_MdlNm.getValue());
                ZYPEZDItemValueSetter.setValue(cMsg.t_MdlNm_A1, tMsg.t_MdlNm.getValue());

                if (!ZYPCommonFunc.hasValue(cMsg.rmnfOrdStsCd_HH)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.origMdlId_HH, tMsg.t_MdlId);
                    ZYPEZDItemValueSetter.setValue(sMsg.t_MdlNm_H2, tMsg.t_MdlNm);
                }

            } else {
                sMsg.rmnfMdlId_HH.clear();
                sMsg.t_MdlNm_H1.clear();
                sMsg.t_MdlNm_A1.clear();
                sMsg.t_MdlNm_H2.clear();
                cMsg.rmnfMdlId_HH.clear();
                cMsg.t_MdlNm_H1.clear();
                cMsg.t_MdlNm_A1.clear();
                cMsg.t_MdlNm_H2.clear();
            }
            // QC#21805 End
        }
        // QC#14011
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfRtlWhCd_H1, (String) recodeSerial.get("RTL_WH_CD"));

        // START 2019/01/16 T.Ogura [QC#29893,ADD]
        if (ZYPCommonFunc.hasValue(sMsg.t_MdlNm_H1)) {
            if (!ZYPCommonFunc.hasValue(cMsg.rmnfOrdStsCd_HH) && !sMsg.t_MdlNm_H1.getValue().equals(sMsg.t_MdlNm_HH.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.t_MdlNm_HH, sMsg.t_MdlNm_H1);
                S21SsmEZDResult resultParts = NPAL1410CommonLogic.getStarndardParts(sMsg, globalCompanyCode);
                if (resultParts.isCodeNormal()) {
                    List<Map> resultPartsMap = (List<Map>) resultParts.getResultObject();
                    for (int i = 0; i < resultPartsMap.size(); i++) {
                        setStdPartsDtl(sMsg, i, (Map<String, Object>) resultPartsMap.get(i));
                    }
                } else {
                    ZYPTableUtil.clear(sMsg.B);
                }
            }
        }
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfMainUnitSerNum_HH, sMsg.rmnfMainUnitSerNum_H1);
        // END   2019/01/16 T.Ogura [QC#29893,ADD]

        cMsg.xxPageShowFromNum.setValue(1);
        // QC#30851
        sMsg.svcConfigMstrPk_H1.clear();
        NPAL1410CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * <pre>
     * Parts Tab Event
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     */
    private void doProcess_NPAL1410Scrn00_TAB_Parts(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.rmnfOrdStsCd_HH)) {
            // if already saved.
            return;
        }
        NPAL1410CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        String globalCompanyCode = getGlobalCompanyCode();

        // START 2019/01/16 T.Ogura [QC#29893,MOD]
//        if (!ZYPCommonFunc.hasValue(sMsg.t_MdlNm_H1)) {
        if (!ZYPCommonFunc.hasValue(sMsg.t_MdlNm_H1) || !sMsg.rmnfMainUnitSerNum_H1.getValue().equals(sMsg.rmnfMainUnitSerNum_HH.getValue())) {
        // END   2019/01/16 T.Ogura [QC#29893,MOD]
            Map<String, Object> recodeSerial = NPAL1410CommonLogic.checkGetConfigAndGetSerial(cMsg, sMsg, globalCompanyCode);
            if (recodeSerial == null) {
                return;
            }
//            sMsg.t_MdlNm_HH.clear();    // 2019/01/16 T.Ogura [QC#29893,DEL]
            ZYPEZDItemValueSetter.setValue(sMsg.rmnfMainUnitMdseCd_HH, (String) recodeSerial.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(sMsg.svcConfigMstrPk_A1, (BigDecimal) recodeSerial.get("SVC_CONFIG_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(sMsg.rmnfRtlWhCd_H1, (String) recodeSerial.get("RTL_WH_CD"));    // 2019/01/16 T.Ogura [QC#29893,ADD]
            if (ZYPCommonFunc.hasValue((String) recodeSerial.get("T_MDL_NM"))) {
                ZYPEZDItemValueSetter.setValue(sMsg.rmnfMdlId_HH, (BigDecimal) recodeSerial.get("T_MDL_ID"));
                ZYPEZDItemValueSetter.setValue(sMsg.t_MdlNm_H1, (String) recodeSerial.get("T_MDL_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.t_MdlNm_A1, (String) recodeSerial.get("T_MDL_NM"));
                // START 2019/01/16 T.Ogura [QC#29893,ADD]
                if (!ZYPCommonFunc.hasValue(cMsg.rmnfOrdStsCd_HH)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.origMdlId_HH, (BigDecimal) recodeSerial.get("T_MDL_ID"));
                    ZYPEZDItemValueSetter.setValue(sMsg.t_MdlNm_H2, (String) recodeSerial.get("T_MDL_NM"));
                }
                // END   2019/01/16 T.Ogura [QC#29893,ADD]
            } else {
                MDL_NMTMsg tMsg = NPAL1410CommonLogic.searchModel((String) recodeSerial.get("MDSE_CD"), globalCompanyCode);
                if (tMsg != null) {
                    ZYPEZDItemValueSetter.setValue(sMsg.rmnfMdlId_HH, tMsg.t_MdlId);
                    ZYPEZDItemValueSetter.setValue(sMsg.t_MdlNm_H1, tMsg.t_MdlNm);
                    ZYPEZDItemValueSetter.setValue(sMsg.t_MdlNm_A1, tMsg.t_MdlNm);
                }
            }
        // START 2019/01/16 T.Ogura [QC#29893,DEL]
//        } else {
//            if (sMsg.t_MdlNm_H1.getValue().equals(sMsg.t_MdlNm_HH.getValue())) {
//                return;
//            }
        // END   2019/01/16 T.Ogura [QC#29893,DEL]
        }
        if (ZYPCommonFunc.hasValue(sMsg.t_MdlNm_H1)) {
            if (!sMsg.t_MdlNm_H1.getValue().equals(sMsg.t_MdlNm_HH.getValue())) {    // 2019/01/16 T.Ogura [QC#29893,ADD]
                ZYPEZDItemValueSetter.setValue(sMsg.t_MdlNm_HH, sMsg.t_MdlNm_H1);
                S21SsmEZDResult resultParts = NPAL1410CommonLogic.getStarndardParts(sMsg, globalCompanyCode);
                if (resultParts.isCodeNormal()) {
                    List<Map> resultPartsMap = (List<Map>) resultParts.getResultObject();
                    for (int i = 0; i < resultPartsMap.size(); i++) {
                        setStdPartsDtl(sMsg, i, (Map<String, Object>) resultPartsMap.get(i));
                    }
                } else {
                    ZYPTableUtil.clear(sMsg.B);
                }
            }                                                                        // 2019/01/16 T.Ogura [QC#29893,ADD]
        }
        // QC#13882
        sMsg.aftDeclPntDigitNum_HH.setValue(NPAL1410CommonLogic.getAftDeclPnt(getGlobalCompanyCode()));
        ZYPEZDItemValueSetter.setValue(cMsg.aftDeclPntDigitNum_HH, sMsg.aftDeclPntDigitNum_HH);

        NPAL1410CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    //QC#18675 ADD START
    /**
     * <pre>
     * Parts Tab Event
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     */
    private void doProcess_NPAL1410Scrn00_TAB_SoRws(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.E);
        ZYPTableUtil.clear(sMsg.E);

        // Create Source Type PulldownList
        String[] sceOrdTpList = new String[3];
        sceOrdTpList[0] = SCE_ORD_TP.REMAN_LOCATOR_TRANSFER;
        sceOrdTpList[1] = SCE_ORD_TP.REMAN_PARTS_USAGE;
        sceOrdTpList[2] = SCE_ORD_TP.REMAN_PARTS_BACK_INVENTORY;

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("sceOrdTpList", sceOrdTpList);
        ssmParam.put("inbound", INBD_OTBD.INBOUND);

        S21SsmEZDResult result = NPAL1410Query.getInstance().getSceOrdTp(ssmParam);
        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();
            int i = 0;
            for (Map resultMap : resultList) {
                ZYPEZDItemValueSetter.setValue(cMsg.sceOrdTpCd_EC.no(i), (String) resultMap.get("SCE_ORD_TP_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.sceOrdTpNm_EN.no(i), (String) resultMap.get("SCE_ORD_TP_NM"));
                i++;
            }
        }

        // Search Default SO/RWS data
        ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("rmnfOrdNum", cMsg.rmnfOrdNum_H1);
        if (ZYPCommonFunc.hasValue(cMsg.sceOrdTpCd_ES)) {
            sceOrdTpList = new String[1];
            sceOrdTpList[0] = cMsg.sceOrdTpCd_ES.getValue();
        }
        ssmParam.put("sceOrdTpList", sceOrdTpList);
        ssmParam.put("remanUsage", SCE_ORD_TP.REMAN_PARTS_USAGE);
        ssmParam.put("outbound", INBD_OTBD.OUTBOUND);
        ssmParam.put("inbound", INBD_OTBD.INBOUND);

        result = NPAL1410Query.getInstance().searchSoRws(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultMap = (List<Map>) result.getResultObject();
            for (int i = 0; i < resultMap.size(); i++) {
                if ((sMsg.E.length()) <= i) {
                    break;
                }
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).rwsNum_E1, (String) resultMap.get(i).get("SO_RWS_NUM"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).actvFlg_E1, (String) resultMap.get(i).get("RWS_NUM_FLG"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).sceOrdTpNm_E1, (String) resultMap.get(i).get("SCE_ORD_TP_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).rtlWhNm_E1, (String) resultMap.get(i).get("RTL_WH_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).rtlSwhNm_E1, (String) resultMap.get(i).get("RTL_SWH_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).shpgStsDescTxt_E1, (String) resultMap.get(i).get("STATUS"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).xxCratDt_E1, (String) resultMap.get(i).get("EZINTIME"));
                sMsg.E.setValidCount(i + 1);
            }
        } else {
            // not has search result
            cMsg.setMessageInfo(NPAM0002E);
        }
        NPAL1410CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

    }
    //QC#18675 ADD END

    /**
     * <pre>
     * Get Tech name Event
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     */
    private void doProcess_NPAL1410Scrn00_GetTechName(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.ownrTechTocCd_H1)) {
            String name = NPAL1410CommonLogic.getTechName(cMsg.ownrTechTocCd_H1.getValue(), getGlobalCompanyCode());
            if (name != null) {
                ZYPEZDItemValueSetter.setValue(cMsg.techNm_H1, name);
            } else {
                cMsg.ownrTechTocCd_H1.setErrorInfo(1, NPAM0224E, new String[] {"Technician" });
                cMsg.techNm_H1.clear();
            }
        }
    }

    /**
     * <pre>
     * Get WH name Event
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     */
    private void doProcess_NPAL1410Scrn00_GetWhName(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.rmnfRtlWhCd_H1)) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
            ssmParam.put("rtlWhCd", cMsg.rmnfRtlWhCd_H1);
            S21SsmEZDResult result = NPAL1410Query.getInstance().getWhName(ssmParam);

            if (result.isCodeNormal()) {
                List<Map> resultMap = (List<Map>) result.getResultObject();
                if (0 < resultMap.size()) {
                    Map<String, Object> recode = (Map<String, Object>) resultMap.get(0);
                    ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_H1, (String) recode.get("RTL_WH_NM"));
                }
            } else {
                cMsg.rmnfRtlWhCd_H1.setErrorInfo(1, NPAM0224E, new String[] {"Warehouse" });
                cMsg.rtlWhNm_H1.clear();
            }
        }
    }

    /**
     * <pre>
     * Add parts line Event
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     */
    private void doProcess_NPAL1410Scrn00_AddParts(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg) {
        // check max count
        int index = sMsg.C.getValidCount();
        if (index == sMsg.C.length()) {
            return;
        } else {
            NPAL1410CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
            sMsg.C.no(index).xxNewRowNum_C1.setValue(-1);
            // clear old data
            sMsg.C.no(index).xxEdtModeFlg_C1.clear();
            sMsg.C.no(index).xxChkBox_C1.clear();
            sMsg.C.no(index).mdseCd_C1.clear();
            sMsg.C.no(index).mdseDescShortTxt_C1.clear();
            sMsg.C.no(index).prtRqstQty_C1.clear();
            sMsg.C.no(index).thisMthTotStdCostAmt_C1.clear();
            sMsg.C.no(index).invtyAvalQty_C1.clear();
            sMsg.C.no(index).rtrnReqPrtFlg_C1.clear();
            sMsg.C.no(index).assetRecovCostAmt_C1.clear();
            sMsg.C.no(index).procrTpNm_C1.clear();
            sMsg.C.no(index).prchReqNum_C1.clear();
            sMsg.C.no(index).dplyVndNm_C1.clear();
            sMsg.C.no(index).xxFlgNm_C1.clear();
            sMsg.C.no(index).rmnfPrtQty_C1.clear();
            sMsg.C.no(index).rmnfPrtReqLineNum_CH.clear();
            sMsg.C.no(index).rmnfStdPrtFlg_CH.setValue(ZYPConstant.FLG_OFF_N);
            sMsg.C.no(index).srcRtlSwhCd_CH.setValue(DEFAULT_SWH);
            sMsg.C.no(index).srcInvtyLocCd_CH.clear();

            sMsg.C.setValidCount(index + 1);
            NPAL1410CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * Add component Event
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     */
    private void doProcess_NPAL1410Scrn00_AddComponent(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg) {
        // check max count
        int index = sMsg.A.getValidCount();
        if (index == sMsg.A.length()) {
            return;
        } else {
            NPAL1410CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
            sMsg.A.no(index).addConfigFlg_A1.setValue(ZYPConstant.FLG_ON_Y);
            // clear old data
            sMsg.A.no(index).rmvConfigFlg_A1.clear();
            sMsg.A.no(index).mdseCd_A1.clear();
            sMsg.A.no(index).mdseDescShortTxt_A1.clear();
            sMsg.A.no(index).mainUnitLineFlg_A1.clear();
            sMsg.A.no(index).serNum_A1.clear();
            sMsg.A.no(index).rmnfToCmptMdseCd_A1.clear();
            sMsg.A.no(index).mdseDescShortTxt_A2.clear();
            sMsg.A.no(index).rmnfToCmptSerNum_A1.clear();
            sMsg.A.no(index).rmnfOrdDtlLineNum_AH.clear();
            sMsg.A.no(index).svcMachMstrPk_AH.clear();
            sMsg.A.no(index).svcMachMstrLocStsCd_AH.clear();
            sMsg.A.no(index).stkStsCd_A1.clear();
            sMsg.A.no(index).srcRtlSwhCd_A1.clear();
            sMsg.A.no(index).svcMachQty_AH.clear();

            sMsg.A.setValidCount(index + 1);
            NPAL1410CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * delete component Event
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     */
    private void doProcess_NPAL1410Scrn00_DeleteComponent(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg) {
        NPAL1410CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        List<NPAL1410_ASMsg> list = new ArrayList<NPAL1410_ASMsg>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).rmvConfigFlg_A1.getValue())) {
                if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).addConfigFlg_A1.getValue())) {
                    list.add(sMsg.A.no(i));
                }
            } else {
                list.add(sMsg.A.no(i));
            }
        }
        for (int i = 0; i < list.size(); i++) {
            EZDMsg.copy(list.get(i), null, sMsg.A.no(i), null);
        }

        sMsg.A.setValidCount(list.size());
        // QC#14010
        cMsg.setMessageInfo(NPAM1582W);
        NPAL1410CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * <pre>
     * Get item name Event
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param mode call from type
     */
    private void doProcess_NPAL1410Scrn00_GetItemName(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, String mode) {
        NPAL1410CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        int idx = NPAL1410CommonLogic.getSMsgIndexFromCMsg(cMsg, cMsg.xxNum.getValueInt());

        if (EVENT_TYPE_CC.equals(mode)) {
            String temp = NPAL1410CommonLogic.getItemName(sMsg.A.no(idx).rmnfToCmptMdseCd_A1.getValue(), getGlobalCompanyCode());
            if (ZYPCommonFunc.hasValue(temp)) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).mdseDescShortTxt_A2, temp);
            } else {
                sMsg.A.no(idx).rmnfToCmptMdseCd_A1.setErrorInfo(1, NPAM0076E, new String[] {"Item" });
            }
        } else if (EVENT_TYPE_CO.equals(mode)) {
            String temp = NPAL1410CommonLogic.getItemName(sMsg.A.no(idx).mdseCd_A1.getValue(), getGlobalCompanyCode());
            if (ZYPCommonFunc.hasValue(temp)) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).mdseDescShortTxt_A1, temp);
            } else {
                sMsg.A.no(idx).mdseCd_A1.setErrorInfo(1, NPAM0076E, new String[] {"Item" });
            }
        } else if (EVENT_TYPE_P.equals(mode)) {
            String temp = NPAL1410CommonLogic.getItemName(sMsg.C.no(idx).mdseCd_C1.getValue(), getGlobalCompanyCode());
            if (ZYPCommonFunc.hasValue(temp)) {
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(idx).mdseDescShortTxt_C1, temp);
            } else {
                sMsg.C.no(idx).mdseCd_C1.setErrorInfo(1, NPAM0076E, new String[] {"Item" });
            }
        }
        NPAL1410CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * <pre>
     * Get item detail Event
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param mode call from type
     */
    private void doProcess_NPAL1410Scrn00_GetItemDetail(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, String mode) {
        NPAL1410CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        int idx = NPAL1410CommonLogic.getSMsgIndexFromCMsg(cMsg, cMsg.xxNum.getValueInt());

        if (EVENT_TYPE_NPAL6800.equals(mode)) {
            sMsg.C.no(idx).mdseCd_C1.setValue(cMsg.xxPopPrm_P0.getValue());
        }
        String mdseCd = sMsg.C.no(idx).mdseCd_C1.getValue();
        String invtyLocCd = sMsg.rmnfRtlWhCd_H1.getValue() + sMsg.C.no(idx).srcRtlSwhCd_CH.getValue();
        BigDecimal qty = sMsg.C.no(idx).prtRqstQty_C1.getValue();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("invtyLocCd", invtyLocCd);
        ssmParam.put("locStsCd", LOC_STS.DC_STOCK);
        ssmParam.put("stkStsCd", STK_STS.GOOD);
        // Execute
        S21SsmEZDResult result = NPAL1410Query.getInstance().getPartsItemInfo(ssmParam);

        if (result.isCodeNormal() && result.getQueryResultCount() == 1) {

            Map<String, Object> resultMap = (Map<String, Object>) result.getResultObject();

            String mdseDescShortTxt = (String) resultMap.get("MDSE_DESC_SHORT_TXT");
            BigDecimal assetRecovCostAmt = (BigDecimal) resultMap.get("ASSET_RECOV_COST_AMT");
            String rtrnReqPrtFlg = (String) resultMap.get("RTRN_REQ_PRT_FLG");
            BigDecimal invtyAvalQty = (BigDecimal) resultMap.get("INVTY_AVAL_QTY");

            ZYPEZDItemValueSetter.setValue(sMsg.C.no(idx).mdseDescShortTxt_C1, mdseDescShortTxt);
            if (!ZYPConstant.FLG_ON_Y.equals(rtrnReqPrtFlg)) {
                rtrnReqPrtFlg = "";
            }
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(idx).rtrnReqPrtFlg_C1, rtrnReqPrtFlg);
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(idx).invtyAvalQty_C1, invtyAvalQty);

            NLXC001001GetInventoryItemCostBean parm = new NLXC001001GetInventoryItemCostBean();
            parm.setGlblCmpyCd(getGlobalCompanyCode());
            parm.setInvtyLocCd(invtyLocCd);
            parm.setMdseCd(mdseCd);
            if (!ZYPCommonFunc.hasValue(qty)) {
                qty = BigDecimal.ZERO;
            }
            parm.setQty(qty);
            parm = NLXC001001GetInventoryItemCost.getInventoryItemCost(parm);

            ZYPEZDItemValueSetter.setValue(sMsg.C.no(idx).thisMthTotStdCostAmt_C1, parm.getUnitPrcAmt());
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(idx).assetRecovCostAmt_C1, qty.multiply(assetRecovCostAmt).setScale(4, BigDecimal.ROUND_DOWN));
        } else {
            sMsg.C.no(idx).mdseCd_C1.setErrorInfo(1, NPAM0076E, new String[] {"Item" });
        }
        NPAL1410CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }
}
