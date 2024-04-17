package business.blap.NLBL3160;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLBL3160.common.NLBL3160CommonLogic;
import business.blap.NLBL3160.constant.NLBL3160Constant;
import business.db.MDSETMsg;
import business.db.RTL_SWHTMsg;
import business.db.RTL_SWHTMsgArray;
import business.db.RTL_WHTMsg;
import business.db.STTMsg;

import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_COORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_CUST_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Distribution Coordinator Work Bench
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/13   CITS            S.Katsuma       Create          N/A
 * 2017/10/11   CITS            S.Katsuma       Update          QC#21684
 * 2017/10/17   CITS            S.Katsuma       Update          QC#21684
 * 2017/10/23   CITS            S.Katsuma       Update          QC#21945,21947
 * 2018/02/06   CITS            K.Ogino         Update          QC#23875
 * 2018/04/24   CITS            S.Katsuma       Update          QC#24821
 * 2018/05/29   CITS            S.Katsuma       Update          QC#25232
 * 2018/10/25   CITS            M.Naito         Update          QC#28867
 * 2019/02/20   Fujitsu         T.Ogura         Update          QC#30369
 * 2022/10/07   Hitachi         T.NEMA          Update          QC#60607
 * 2023/08/01   Hitachi         M.Kikushima     Update          QC#61677
 *</pre>
 */
public class NLBL3160BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NLBL3160_INIT".equals(screenAplID)) {
                doProcess_NLBL3160_INIT((NLBL3160CMsg) cMsg, (NLBL3160SMsg) sMsg);
            } else if ("NLBL3160Scrn00_Search_CoordInfo".equals(screenAplID)) {
                doProcess_NLBL3160Scrn00_Search_CoordInfo((NLBL3160CMsg) cMsg);
            } else if ("NLBL3160Scrn00_Get_Branch".equals(screenAplID)) {
                doProcess_NLBL3160Scrn00_Get_Branch((NLBL3160CMsg) cMsg);
            } else if ("NLBL3160Scrn00_Get_Customer".equals(screenAplID)) {
                doProcess_NLBL3160Scrn00_Get_Customer((NLBL3160CMsg) cMsg);
            } else if ("NLBL3160Scrn00_Get_MdseInfo".equals(screenAplID)) {
                doProcess_NLBL3160Scrn00_Get_MdseInfo((NLBL3160CMsg) cMsg);
            } else if ("NLBL3160Scrn00_Get_RtlSWHInfo".equals(screenAplID)) {
                doProcess_NLBL3160Scrn00_Get_RtlSWHInfo((NLBL3160CMsg) cMsg);
            } else if ("NLBL3160Scrn00_Get_RtlWHInfo".equals(screenAplID)) {
                doProcess_NLBL3160Scrn00_Get_RtlWHInfo((NLBL3160CMsg) cMsg);
            } else if ("NLBL3160Scrn00_Get_State".equals(screenAplID)) {
                doProcess_NLBL3160Scrn00_Get_State((NLBL3160CMsg) cMsg);
            } else if ("NLBL3160Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NLBL3160Scrn00_CMN_Clear((NLBL3160CMsg) cMsg);
            } else if ("NLBL3160Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NLBL3160Scrn00_PageNext((NLBL3160CMsg) cMsg, (NLBL3160SMsg) sMsg);
            } else if ("NLBL3160Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NLBL3160Scrn00_PagePrev((NLBL3160CMsg) cMsg, (NLBL3160SMsg) sMsg);
            } else if ("NLBL3160Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NLBL3160Scrn00_PageJump((NLBL3160CMsg) cMsg, (NLBL3160SMsg) sMsg);
            } else if ("NLBL3160Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {
                doProcess_NLBL3160_OnChangeSavedSearchOption((NLBL3160CMsg) cMsg);
            } else if ("NLBL3160Scrn00_Refresh".equals(screenAplID)) {
                doProcess_NLBL3160Scrn00_Refresh((NLBL3160CMsg) cMsg, (NLBL3160SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NLBL3080_INIT
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private void doProcess_NLBL3160_INIT(NLBL3160CMsg cMsg, NLBL3160SMsg sMsg) {
        // clearAll(cMsg);

        // Setup select box
        NLBL3160CommonLogic.createSavedSearchOptionsPullDown(cMsg, getContextUserInfo().getUserId());
        // create Pull-Down
        createPulldownList(cMsg);
 
        // Sales Order Number
        boolean doSearch = false;
        if (ZYPCommonFunc.hasValue(cMsg.cpoNum)) {
            doSearch = true;
        }

        if (doSearch) {
            doProcess_NLBL3160Scrn00_Refresh(cMsg, sMsg);
        }
    }

    /**
     * Search_CoordInfo
     * @param cMsg NLBL3160CMsg
     */
    private void doProcess_NLBL3160Scrn00_Search_CoordInfo(NLBL3160CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("psnCd", cMsg.psnCd.getValue());

        S21SsmEZDResult ssmResult = NLBL3160Query.getInstance().getPsnNm(params);

        if (ssmResult.isCodeNormal()) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxPsnFirstMidLastNm, (String) ssmResult.getResultObject());
        } else {
            // Error
            cMsg.psnCd.setErrorInfo(1, "NLZM2278E", new String[] {cMsg.psnCd.getValue() });
            cMsg.xxPsnFirstMidLastNm.clear();
        }
    }

    /**
     * Get_Branch
     * @param cMsg NLBL3160CMsg
     */
    private void doProcess_NLBL3160Scrn00_Get_Branch(NLBL3160CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("svcBrCd", cMsg.svcBrCd.getValue());

        S21SsmEZDResult ssmResult = NLBL3160Query.getInstance().getBranch(params);

        if (ssmResult.isCodeNormal()) {
            ZYPEZDItemValueSetter.setValue(cMsg.svcBrCdDescTxt, (String) ssmResult.getResultObject());
            return;
        } else {
            // Error
            cMsg.svcBrCd.setErrorInfo(1, "NLZM2278E", new String[] {cMsg.svcBrCd.getValue() });
            cMsg.svcBrCdDescTxt.clear();
        }
    }

    /**
     * Get_Customer
     * @param cMsg NLBL3160CMsg
     */
    private void doProcess_NLBL3160Scrn00_Get_Customer(NLBL3160CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsAcctNum", cMsg.dsAcctNum.getValue());

        S21SsmEZDResult ssmResult = NLBL3160Query.getInstance().getCustomer(params);

        if (ssmResult.isCodeNormal()) {

            ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm, (String) ssmResult.getResultObject());

        } else {

            // Error
            cMsg.dsAcctNum.setErrorInfo(1, "NLZM2278E", new String[] {cMsg.dsAcctNum.getValue() });
            cMsg.dsAcctNm.clear();
        }
    }

    /**
     * Get_MdseInfo
     * @param cMsg NLBL3160CMsg
     */
    private void doProcess_NLBL3160Scrn00_Get_MdseInfo(NLBL3160CMsg cMsg) {
        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, cMsg.mdseCd.getValue());
        mdseTMsg = (MDSETMsg) EZDFastTBLAccessor.findByKey(mdseTMsg);

        if (mdseTMsg == null) {

            cMsg.mdseCd.setErrorInfo(1, "NLZM2278E", new String[] {cMsg.mdseCd.getValue() });
            cMsg.mdseNm.clear();
            return;
        }

        ZYPEZDItemValueSetter.setValue(cMsg.mdseNm, mdseTMsg.mdseNm);
    }

    /**
     * Get_RtlWHInfo
     * @param cMsg NLBL3160CMsg
     */
    private void doProcess_NLBL3160Scrn00_Get_RtlWHInfo(NLBL3160CMsg cMsg) {

        RTL_WHTMsg rtlWh = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlWh.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(rtlWh.rtlWhCd, cMsg.rtlWhCd.getValue());
        rtlWh = (RTL_WHTMsg) EZDFastTBLAccessor.findByKey(rtlWh);

        if (rtlWh == null) {

            cMsg.rtlWhCd.setErrorInfo(1, "NLZM2278E", new String[] {cMsg.rtlWhCd.getValue() });
            cMsg.rtlWhNm.clear();
            return;
        }

        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm, rtlWh.rtlWhNm);
    }

    /**
     * Get_RtlSWHInfo
     * @param cMsg NLBL3160CMsg
     */
    private void doProcess_NLBL3160Scrn00_Get_RtlSWHInfo(NLBL3160CMsg cMsg) {

        RTL_SWHTMsg rtlSwh = new RTL_SWHTMsg();
        rtlSwh.setSQLID("002");
        rtlSwh.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        rtlSwh.setConditionValue("rtlSwhCd01", cMsg.rtlSwhCd.getValue());
        RTL_SWHTMsgArray rtlSwhList = (RTL_SWHTMsgArray) EZDTBLAccessor.findByCondition(rtlSwh);

        if (rtlSwhList == null || rtlSwhList.getValidCount() == 0) {

            cMsg.rtlSwhCd.setErrorInfo(1, "NLZM2278E", new String[] {cMsg.rtlSwhCd.getValue() });
            cMsg.rtlSwhNm.clear();
            return;
        }

        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm, rtlSwhList.no(0).rtlSwhNm);
    }

    /**
     * Get_State
     * @param cMsg NLBL3160CMsg
     */
    private void doProcess_NLBL3160Scrn00_Get_State(NLBL3160CMsg cMsg) {

        STTMsg stTMsg = new STTMsg();
        ZYPEZDItemValueSetter.setValue(stTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(stTMsg.stCd, cMsg.stCd.getValue());
        stTMsg = (STTMsg) EZDFastTBLAccessor.findByKey(stTMsg);

        if (stTMsg == null) {

            cMsg.stCd.setErrorInfo(1, "NLZM2278E", new String[] {cMsg.stCd.getValue() });
            cMsg.stNm.clear();
            return;
        }

        ZYPEZDItemValueSetter.setValue(cMsg.stNm, stTMsg.stNm);
    }

    /**
     * doProcess_NLBL3160Scrn00_CMN_Clear
     * @param cMsg NLBL3160CMsg
     * @param sMsg NLBL3160SMsg
     */
    private void doProcess_NLBL3160Scrn00_CMN_Clear(NLBL3160CMsg cMsg) {
        clearAll(cMsg);

        // create Pull-Down
        createPulldownList(cMsg);
    }

    /**
     * createPulldownList
     * @param cMsg NLBL3160CMsg
     */
    private void createPulldownList(NLBL3160CMsg cMsg) {
        ZYPCodeDataUtil.createPulldownList(SCHD_COORD_STS.class, cMsg.schdCoordStsCd_P, cMsg.schdCoordStsDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(SHPG_SVC_LVL.class, cMsg.shpgSvcLvlCd_P, cMsg.shpgSvcLvlDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, cMsg.lineBizTpCd_P, cMsg.lineBizTpDescTxt_P);
    }

    /**
     * clearAll
     * @param cMsg NLBL3160CMsg
     */
    private void clearAll(NLBL3160CMsg cMsg) {

        // Clear Table
        ZYPTableUtil.clear(cMsg.A);

        // Header
        cMsg.srchOptPk_S.clear();
        cMsg.srchOptNm_S.clear();
        cMsg.cpoNum.clear();
        if (!NLBL3160Constant.FUNC_ID_COORD.equals(cMsg.xxFuncId.getValue())) {
            cMsg.psnCd.clear();
            cMsg.xxPsnFirstMidLastNm.clear();
        }
        cMsg.dsOrdCatgDescTxt.clear();
        cMsg.schdCoordStsCd.clear();
        cMsg.schdCoordStsCd_P.clear();
        cMsg.schdCoordStsDescTxt_P.clear();
        cMsg.svcConfigMstrPk.clear();
        cMsg.shpgSvcLvlCd.clear();
        cMsg.shpgSvcLvlCd_P.clear();
        cMsg.shpgSvcLvlDescTxt_P.clear();
        cMsg.lineBizTpCd.clear();
        cMsg.lineBizTpCd_P.clear();
        cMsg.lineBizTpDescTxt_P.clear();
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_S, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_DC, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_NA, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_F, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_RS, ZYPConstant.FLG_ON_Y);
        // START 2022/11/07 T.NEMA [QC#60607, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_I, ZYPConstant.FLG_OFF_N);
        // END   2022/11/07 T.NEMA [QC#60607, ADD]
        
        ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_S, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_DC, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_NA, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_F, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_RS, BigDecimal.ZERO);
        cMsg.mdseCd.clear();
        cMsg.mdseNm.clear();
        cMsg.dsAcctNum.clear();
        cMsg.dsAcctNm.clear();
        cMsg.stCd.clear();
        cMsg.stNm.clear();
        cMsg.svcBrCd.clear();
        cMsg.svcBrCdDescTxt.clear();
        cMsg.rddDt_FR.clear();
        cMsg.rddDt_TO.clear();
        cMsg.rtlWhCd.clear();
        cMsg.rtlWhNm.clear();
        cMsg.rtlSwhCd.clear();
        cMsg.rtlSwhNm.clear();
        cMsg.nextActDt_FR.clear();
        cMsg.nextActDt_TO.clear();

        // Detail
        cMsg.xxPageShowFromNum_A.clear();
        cMsg.xxPageShowToNum_A.clear();
        cMsg.xxPageShowOfNum_A.clear();
        cMsg.xxPageShowCurNum_A.clear();
        cMsg.xxPageShowTotNum_A.clear();
        cMsg.xxSortTblNm_A.clear();
        cMsg.xxSortItemNm_A.clear();
        cMsg.xxSortOrdByTxt_A.clear();
    }

    /**
     * doProcess_NLBL3160Scrn00_PageNext
     * @param cMsg NLBL3160CMsg
     * @param sMsg NLBL3160SMsg
     */
    private void doProcess_NLBL3160Scrn00_PageNext(NLBL3160CMsg cMsg, NLBL3160SMsg sMsg) {

        NLBL3160CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);
        ZYPTableUtil.clear(cMsg.A);
        NLBL3160CommonLogic.pagenation(cMsg, sMsg, cMsg.xxPageShowToNum_A.getValueInt());
    }

    /**
     * doProcess_NLBL3160Scrn00_PagePrev
     * @param cMsg NLBL3160CMsg
     * @param sMsg NLBL3160SMsg
     */
    private void doProcess_NLBL3160Scrn00_PagePrev(NLBL3160CMsg cMsg, NLBL3160SMsg sMsg) {

        NLBL3160CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);
        ZYPTableUtil.clear(cMsg.A);
        NLBL3160CommonLogic.pagenation(cMsg, sMsg, cMsg.xxPageShowFromNum_A.getValueInt() - cMsg.A.length() - 1);
    }

    /**
     * doProcess_NLBL3160Scrn00_PageJump
     * @param cMsg NLBL3160CMsg
     * @param sMsg NLBL3160SMsg
     */
    private void doProcess_NLBL3160Scrn00_PageJump(NLBL3160CMsg cMsg, NLBL3160SMsg sMsg) {

        NLBL3160CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);
        ZYPTableUtil.clear(cMsg.A);
        NLBL3160CommonLogic.pagenation(cMsg, sMsg, cMsg.A.length() * (cMsg.xxPageShowCurNum_A.getValueInt() - 1));
    }

    /**
     * doProcess_NLBL3070_OnChangeSavedSearchOption
     * @param cMsg NLBL3160CMsg
     */
    private void doProcess_NLBL3160_OnChangeSavedSearchOption(NLBL3160CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {

            NLBL3160CommonLogic.callNszc0330forSearchOption(cMsg, getContextUserInfo().getUserId());
        }
    }

    /**
     * doProcess_NLBL3160Scrn00_Search
     * @param cMsg NLBL3160CMsg
     */
    private void doProcess_NLBL3160Scrn00_Refresh(NLBL3160CMsg cMsg, NLBL3160SMsg sMsg) {
        // Master Check
        if (checkInputHeader(cMsg)) {
            S21SsmEZDResult ssmResult = null;
            List<Map<String, Object>> allResultList = new ArrayList<Map<String, Object>>();
            // START 2017/10/23 S.Katsuma QC#21947 DEL
//            BigDecimal xxTotCnt_S = BigDecimal.ZERO;
            // END 2017/10/23 S.Katsuma QC#21947 DEL

            // QC#23875 Start
            // Role Function
            List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NLBL3160Constant.BUSINESS_ID);
            String loginUser = getContextUserInfo().getUserId();
            // WH Permission List
            List<String> whList = NLBL3160CommonLogic.getWarehousePermission(cMsg, functionList, loginUser);
            // QC#23875 End

            // QC#28391
            List<String> svcExchgCatgList = getSvcExchgCatgList();

            if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_S.getValue())) {
                List<String> dsOrdCatgDescTxtList = splitSrchTxt(cMsg.dsOrdCatgDescTxt);

                Map<String, Object> params = new HashMap<String, Object>();
                params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
                params.put("slsDt", cMsg.slsDt.getValue());
                params.put("cpoOrdNum", cMsg.cpoNum.getValue());
                if (ZYPCommonFunc.hasValue(cMsg.svcConfigMstrPk.getValue())) {
                    params.put("svcConfigMstrPk", cMsg.svcConfigMstrPk.getValue());
                }
                params.put("stCd", cMsg.stCd.getValue());
                params.put("rtlWhCd", cMsg.rtlWhCd.getValue());
                params.put("rtlSwhCd", cMsg.rtlSwhCd.getValue());
                params.put("schdCoordStsCd", cMsg.schdCoordStsCd.getValue());
                params.put("dsAcctNum", cMsg.dsAcctNum.getValue());
                params.put("svcBrCd", cMsg.svcBrCd.getValue());
                params.put("psnCd", cMsg.psnCd.getValue());
                params.put("mdseCd", cMsg.mdseCd.getValue());
                params.put("rddDt_FR", cMsg.rddDt_FR.getValue());
                params.put("rddDt_TO", cMsg.rddDt_TO.getValue());
                params.put("nextActDt_FR", cMsg.nextActDt_FR.getValue());
                params.put("nextActDt_TO", cMsg.nextActDt_TO.getValue());
                params.put("shpgSvcLvlCd", cMsg.shpgSvcLvlCd.getValue());
                params.put("lineBizTpCd", cMsg.lineBizTpCd.getValue());
                if (dsOrdCatgDescTxtList != null) {
                    params.put("dsOrdCatgDescTxtList", dsOrdCatgDescTxtList);
                }
                params.put("sceOrdTpRT", SCE_ORD_TP.RETURN);
                params.put("shpgStsShipped", SHPG_STS.SHIPPED);
                params.put("inbdOtbdCd", INBD_OTBD.OUTBOUND);
                params.put("flgY", ZYPConstant.FLG_ON_Y);
                params.put("flgN", ZYPConstant.FLG_OFF_N);
                // QC#23875
                params.put("whList", whList);
                
                // START 2022/11/07 T.NEMA [QC#60607, ADD]
                if (!ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_I.getValue())) {
                    params.put("exSoLineSts", SHPG_STS.SHIPPED);
                }
                // END   2022/11/07 T.NEMA [QC#60607, ADD]
                
                // START 2018/10/25 M.Naito [QC#28867,ADD]
                S21InfoLogOutput.println("### Set Parameters (SQLID: getOrdersToSchedule) ###################################");
                S21InfoLogOutput.println(params.toString());
                // END 2018/10/25 M.Naito [QC#28867,ADD]
                ssmResult = NLBL3160Query.getInstance().getOrdersToSchedule(params);
                if (ssmResult.isCodeNormal()) {
                    allResultList.addAll(new ArrayList<Map<String, Object>>((List<Map<String, Object>>) ssmResult.getResultObject()));
                    // START 2017/10/23 S.Katsuma QC#21947 DEL
//                    xxTotCnt_S = new BigDecimal(ssmResult.getQueryResultCount());
                    // END 2017/10/23 S.Katsuma QC#21947 DEL
                }
                ssmResult = null;
            }

            // START 2017/10/23 S.Katsuma QC#21947 DEL
//            BigDecimal xxTotCnt_NA = BigDecimal.ZERO;
            // END 2017/10/23 S.Katsuma QC#21947 DEL
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_NA.getValue())) {
                List<String> dsOrdCatgDescTxtList = splitSrchTxt(cMsg.dsOrdCatgDescTxt);

                Map<String, Object> params = new HashMap<String, Object>();
                params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
                params.put("slsDt", cMsg.slsDt.getValue());
                params.put("cpoOrdNum", cMsg.cpoNum.getValue());
                if (ZYPCommonFunc.hasValue(cMsg.svcConfigMstrPk.getValue())) {
                    params.put("svcConfigMstrPk", cMsg.svcConfigMstrPk.getValue());
                }
                params.put("stCd", cMsg.stCd.getValue());
                params.put("rtlWhCd", cMsg.rtlWhCd.getValue());
                params.put("rtlSwhCd", cMsg.rtlSwhCd.getValue());
                params.put("dsAcctNum", cMsg.dsAcctNum.getValue());
                params.put("svcBrCd", cMsg.svcBrCd.getValue());
                params.put("mdseCd", cMsg.mdseCd.getValue());
                params.put("rddDt_FR", cMsg.rddDt_FR.getValue());
                params.put("rddDt_TO", cMsg.rddDt_TO.getValue());
                params.put("lineBizTpCd", cMsg.lineBizTpCd.getValue());
                if (dsOrdCatgDescTxtList != null) {
                    params.put("dsOrdCatgDescTxtList", dsOrdCatgDescTxtList);
                }
                params.put("rtrnLineBooked", RTRN_LINE_STS.BOOKED);
                params.put("shpgStsValidated", SHPG_STS.VALIDATED);
                params.put("shpgStsHardAllocated", SHPG_STS.HARD_ALLOCATED);
                params.put("dsCondConstGrpId", NLBL3160Constant.NLBL3080_NOT_ALLC_WH);
                params.put("flgY", ZYPConstant.FLG_ON_Y);
                params.put("flgN", ZYPConstant.FLG_OFF_N);
                // QC#23875
                params.put("whList", whList);

                // START 2022/11/07 T.NEMA [QC#60607, ADD]
                if (!ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_I.getValue())) {
                    params.put("exSoLineSts", SHPG_STS.SHIPPED);
                }
                // END   2022/11/07 T.NEMA [QC#60607, ADD]

                // START 2018/10/25 M.Naito [QC#28867,ADD]
                S21InfoLogOutput.println("### Set Parameters (SQLID: getOrdersNotAvalToSchedule) ###########################");
                S21InfoLogOutput.println(params.toString());
                // END 2018/10/25 M.Naito [QC#28867,ADD]
                ssmResult = NLBL3160Query.getInstance().getOrdersNotAvalToSchedule(params);
                if (ssmResult.isCodeNormal()) {
                    allResultList.addAll(new ArrayList<Map<String, Object>>((List<Map<String, Object>>) ssmResult.getResultObject()));
                    // START 2017/10/23 S.Katsuma QC#21947 DEL
//                    xxTotCnt_NA = new BigDecimal(ssmResult.getQueryResultCount());
                    // END 2017/10/23 S.Katsuma QC#21947 DEL
                }
                ssmResult = null;
            }

//            // START 2017/10/23 S.Katsuma QC#21947 DEL
//            BigDecimal xxTotCnt_DC = BigDecimal.ZERO;
//            BigDecimal xxTotCnt_F = BigDecimal.ZERO;
//            BigDecimal xxTotCnt_RS = BigDecimal.ZERO;
            // END 2017/10/23 S.Katsuma QC#21947 DEL
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_DC.getValue()) || ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_F.getValue()) || ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_RS.getValue())) {
                List<String> dsOrdCatgDescTxtList = splitSrchTxt(cMsg.dsOrdCatgDescTxt);

                Map<String, Object> params = new HashMap<String, Object>();
                params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
                params.put("slsDt", cMsg.slsDt.getValue());
                params.put("cpoOrdNum", cMsg.cpoNum.getValue());
                if (ZYPCommonFunc.hasValue(cMsg.svcConfigMstrPk.getValue())) {
                    params.put("svcConfigMstrPk", cMsg.svcConfigMstrPk.getValue());
                }
                params.put("stCd", cMsg.stCd.getValue());
                params.put("rtlWhCd", cMsg.rtlWhCd.getValue());
                params.put("rtlSwhCd", cMsg.rtlSwhCd.getValue());
                params.put("schdCoordStsCd", cMsg.schdCoordStsCd.getValue());
                params.put("dsAcctNum", cMsg.dsAcctNum.getValue());
                params.put("svcBrCd", cMsg.svcBrCd.getValue());
                params.put("psnCd", cMsg.psnCd.getValue());
                params.put("mdseCd", cMsg.mdseCd.getValue());
                params.put("rddDt_FR", cMsg.rddDt_FR.getValue());
                params.put("rddDt_TO", cMsg.rddDt_TO.getValue());
                params.put("nextActDt_FR", cMsg.nextActDt_FR.getValue());
                params.put("nextActDt_TO", cMsg.nextActDt_TO.getValue());
                params.put("shpgSvcLvlCd", cMsg.shpgSvcLvlCd.getValue());
                params.put("lineBizTpCd", cMsg.lineBizTpCd.getValue());
                // START 2017/10/11 S.Katsuma QC#21684 ADD
                params.put("flgY", ZYPConstant.FLG_ON_Y);
                // END 2017/10/11 S.Katsuma QC#21684 ADD
                // START 2017/10/17 S.Katsuma QC#21684 ADD
                params.put("flgN", ZYPConstant.FLG_OFF_N);
                params.put("shpgStsShipped", SHPG_STS.SHIPPED);
                params.put("inbdOtbdCd", INBD_OTBD.OUTBOUND);
                params.put("soCustDataTpShipTo", SO_CUST_DATA_TP.SHIP_TO);
                // END 2017/10/17 S.Katsuma QC#21684 ADD
                if (dsOrdCatgDescTxtList != null) {
                    params.put("dsOrdCatgDescTxtList", dsOrdCatgDescTxtList);
                }

                // START 2022/11/07 T.NEMA [QC#60607, ADD]
                if (!ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_I.getValue())) {
                    params.put("exSoLineSts", SHPG_STS.SHIPPED);
                }
                // END   2022/11/07 T.NEMA [QC#60607, ADD]

                String xxChkBox = ZYPConstant.FLG_OFF_N;
                String leadCriteria = "";

                if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_DC.getValue())) {
                    params.put("xxChkBox_DC", cMsg.xxChkBox_DC.getValue());
                    xxChkBox = ZYPConstant.FLG_ON_Y;
                    if ("".equals(leadCriteria)) {
                        leadCriteria = "xxChkBox_DC";
                    }
                    // START 2023/08/01 M.Kikushima [QC#61677, ADD]
                    params.put("schdCoordStsCd1", SCHD_COORD_STS.SCHEDULED);
                    params.put("schdCoordStsCd2", SCHD_COORD_STS.RE_SCHEDULED);
                    params.put("schdCoordStsCd3", SCHD_COORD_STS.PRODUCT_IN_ROUTE_NO_CONFIRMED_DATE);
                    // END 2023/08/01 M.Kikushima [QC#61677, ADD]
                }
                if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_F.getValue())) {
                    params.put("xxChkBox_F", cMsg.xxChkBox_F.getValue());
                    xxChkBox = ZYPConstant.FLG_ON_Y;
                    if ("".equals(leadCriteria)) {
                        leadCriteria = "xxChkBox_F";
                    }
                }
                if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_RS.getValue())) {
                    params.put("xxChkBox_RS", cMsg.xxChkBox_RS.getValue());
                    xxChkBox = ZYPConstant.FLG_ON_Y;
                    if ("".equals(leadCriteria)) {
                        leadCriteria = "xxChkBox_RS";
                    }
                }
                params.put("xxChkBox", xxChkBox);
                params.put("leadCriteria", leadCriteria);
                // QC#23875
                params.put("whList", whList);

                // START 2018/10/25 M.Naito [QC#28867,ADD]
                S21InfoLogOutput.println("### Set Parameters (SQLID: getScheduledOrders) ###################################");
                S21InfoLogOutput.println(params.toString());
                // END 2018/10/25 M.Naito [QC#28867,ADD]
                ssmResult = NLBL3160Query.getInstance().getScheduledOrders(params);
                if (ssmResult.isCodeNormal()) {
                    List<Map<String, Object>> rsList = (List<Map<String, Object>>) ssmResult.getResultObject();
                    allResultList.addAll(new ArrayList<Map<String, Object>>(rsList));
                    // START 2017/10/23 S.Katsuma QC#21947 DEL
//                    for (Map<String, Object> rs : rsList) {
//                        String schdDelyDt = (String) rs.get(NLBL3160Constant.DELIVERY_SCHEDULED_DATE);
//                        if (cMsg.slsDt.getValue().compareTo(schdDelyDt) == 0 || ZYPDateUtil.addDays(cMsg.slsDt.getValue(), 1).compareTo(schdDelyDt) == 0) {
//                            xxTotCnt_DC = xxTotCnt_DC.add(new BigDecimal(1));
//                        } else if (ZYPDateUtil.addDays(cMsg.slsDt.getValue(), 1).compareTo(schdDelyDt) < 0) {
//                            xxTotCnt_F = xxTotCnt_F.add(new BigDecimal(1));
//                        } else {
//                            if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_RS.getValue())) {
//                                if (cMsg.slsDt.getValue().compareTo(schdDelyDt) > 0) {
//                                    xxTotCnt_RS = xxTotCnt_RS.add(new BigDecimal(1));
//                                }
//                            }
//                        }
//                    }
                    // END 2017/10/23 S.Katsuma QC#21947 DEL
                }
                ssmResult = null;
            }

            if (allResultList.size() > 0) {
                // sort
                Collections.sort(allResultList, new Comparator<Map<String, Object>>() {
                    public int compare(final Map<String, Object> o1, final Map<String, Object> o2) {
                        if (o1.get(NLBL3160Constant.TRX_REF_NUM) == null) {
                            return -1;
                        } else if (o2.get(NLBL3160Constant.TRX_REF_NUM) == null) {
                            return 1;
                        } else {
                            return ((String) o1.get(NLBL3160Constant.TRX_REF_NUM)).compareTo((String) o2.get(NLBL3160Constant.TRX_REF_NUM));
                        }
                    }
                });
                Collections.sort(allResultList, new Comparator<Map<String, Object>>() {
                    public int compare(final Map<String, Object> o1, final Map<String, Object> o2) {
                        if (o1.get(NLBL3160Constant.CONFIG_ID) == null) {
                            return -1;
                        } else if (o2.get(NLBL3160Constant.CONFIG_ID) == null) {
                            return 1;
                        } else {
                            return ((BigDecimal) o1.get(NLBL3160Constant.CONFIG_ID)).compareTo((BigDecimal) o2.get(NLBL3160Constant.CONFIG_ID));
                        }
                    }
                });
                Collections.sort(allResultList, new Comparator<Map<String, Object>>() {
                    public int compare(final Map<String, Object> o1, final Map<String, Object> o2) {
                        return ((String) o1.get(NLBL3160Constant.CPO_ORD_NUM)).compareTo((String) o2.get(NLBL3160Constant.CPO_ORD_NUM));
                    }
                });

                sMsg.A.clear();
//                int queryResCnt = allResultList.size();
//                if (queryResCnt > sMsg.A.length()) {
//                    cMsg.setMessageInfo("NZZM0001W");
//                    sMsg.A.setValidCount(sMsg.A.length());
//                    queryResCnt = sMsg.A.length();
//                }

                int idx = 0;
                String lastCpoOrdNum = null;
                // used to aggregate the configuration unit in the
                // same order
                List<Map<String, Object>> sameOdrNumOrdList = new ArrayList<Map<String, Object>>();
                // used for conversion of resultList and SMsg
                List<Map<String, Object>> outputList = new ArrayList<Map<String, Object>>();
                for (Map<String, Object> resultMap : allResultList) {
                    // START 2018/04/24 S.Katsuma [QC#24821,DEL]
//                    if (idx > sMsg.A.length()) {
//                        break;
//                    }
                    // END 2018/04/24 S.Katsuma [QC#24821,DEL]

                    String cpoOrdNum = (String) resultMap.get(NLBL3160Constant.CPO_ORD_NUM);
                    if (ZYPCommonFunc.hasValue(lastCpoOrdNum)) {
                        if (cpoOrdNum.equals(lastCpoOrdNum)) {
                            sameOdrNumOrdList.add(resultMap);
                        } else {
                            // handle last same order list
                            handleSameOdrNumOrdList(sameOdrNumOrdList, outputList, sMsg.A.length(), svcExchgCatgList);
                            if (outputList.size() >= sMsg.A.length()) {
                                cMsg.setMessageInfo("NZZM0001W");
                                break;
                            }

                            sameOdrNumOrdList.clear();
                            lastCpoOrdNum = cpoOrdNum;
                            sameOdrNumOrdList.add(resultMap);
                        }
                    } else {
                        lastCpoOrdNum = cpoOrdNum;
                        sameOdrNumOrdList.add(resultMap);
                    }

                    idx++;
                }

                if (sameOdrNumOrdList.size() > 0) {
                    if (outputList.size() + sameOdrNumOrdList.size() > sMsg.A.length()) {
                        cMsg.setMessageInfo("NZZM0001W");
                    }
                    handleSameOdrNumOrdList(sameOdrNumOrdList, outputList, sMsg.A.length(), svcExchgCatgList);
                }
                sameOdrNumOrdList.clear();

                sMsg.A.setValidCount(outputList.size());
                for (int i = 0; i < outputList.size(); i++) {
                    Map<String, Object> rec = outputList.get(i);

                    String outputMode = (String) rec.get(NLBL3160Constant.OUTPUT_MODE);
                    if (ZYPCommonFunc.hasValue(outputMode)) {
                        if (outputMode.equals((String) NLBL3160Constant.OUTPUT_MODE_CPO)) {
                            setOrderValue(rec, sMsg, i);
                        } else if (outputMode.equals(NLBL3160Constant.OUTPUT_MODE_CONFIG)) {
                            setConfigValue(rec, sMsg, i);
                        }
                    }
                }

                // START 2017/10/23 S.Katsuma QC#21947 ADD
//                ZYPEZDItemValueSetter.setValue(sMsg.xxTotCnt_S, xxTotCnt_S);
//                ZYPEZDItemValueSetter.setValue(sMsg.xxTotCnt_NA, xxTotCnt_NA);
//                ZYPEZDItemValueSetter.setValue(sMsg.xxTotCnt_DC, xxTotCnt_DC);
//                ZYPEZDItemValueSetter.setValue(sMsg.xxTotCnt_F, xxTotCnt_F);
//                ZYPEZDItemValueSetter.setValue(sMsg.xxTotCnt_RS, xxTotCnt_RS);
//                ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_S, xxTotCnt_S);
//                ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_NA, xxTotCnt_NA);
//                ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_DC, xxTotCnt_DC);
//                ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_F, xxTotCnt_F);
//                ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_RS, xxTotCnt_RS);
                countUniqueConfigLine(cMsg, sMsg, allResultList);
                // END 2017/10/23 S.Katsuma QC#21947 ADD

                // Copy from SMsg to cMsg
                int i = 0;
                for (; i < outputList.size(); i++) {
                    if (i == cMsg.A.length()) {
                        break;
                    }
                    EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
                }
                cMsg.A.setValidCount(i);

                // Set page number
                cMsg.xxPageShowFromNum_A.setValue(1);
                cMsg.xxPageShowToNum_A.setValue(cMsg.A.getValidCount());
                cMsg.xxPageShowOfNum_A.setValue(outputList.size());
                cMsg.setMessageInfo("ZZZM9003I", new String[] {"Refresh" });
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_S, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_NA, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_DC, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_F, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_RS, BigDecimal.ZERO);
                ZYPTableUtil.clear(cMsg.A);
                ZYPTableUtil.clear(sMsg.A);
                cMsg.xxPageShowFromNum_A.clear();
                cMsg.xxPageShowToNum_A.clear();
                cMsg.xxPageShowOfNum_A.clear();
                cMsg.setMessageInfo("NZZM0000E");
            }
        }
    }

    // START 2017/10/23 S.Katsuma QC#21947 ADD
    /**
     * @param cMsg
     * @param sMsg
     * @param allResultList
     */
    private void countUniqueConfigLine(NLBL3160CMsg cMsg, NLBL3160SMsg sMsg, List<Map<String, Object>> resultList) {
        BigDecimal xxTotCnt_S = BigDecimal.ZERO;
        BigDecimal xxTotCnt_NA = BigDecimal.ZERO;
        BigDecimal xxTotCnt_DC = BigDecimal.ZERO;
        BigDecimal xxTotCnt_F = BigDecimal.ZERO;
        BigDecimal xxTotCnt_RS = BigDecimal.ZERO;
        List<Object> ordConfList = new ArrayList<Object>();
        Map<List<Object>, String> ordConfMap = new LinkedHashMap<List<Object>, String>();
        for (Map<String, Object> rs : resultList) {
            String cpoOrdNum = (String) rs.get(NLBL3160Constant.CPO_ORD_NUM);
            BigDecimal dsCpoConfigPk = (BigDecimal) rs.get(NLBL3160Constant.DS_CPO_CONFIG_PK);
            String trxRefNum = (String) rs.get(NLBL3160Constant.TRX_REF_NUM);
            ordConfList.add(cpoOrdNum);
            ordConfList.add(dsCpoConfigPk);
            ordConfList.add(trxRefNum);

            if (!ordConfMap.containsKey(ordConfList)) {
                String dataStatus = (String) rs.get(NLBL3160Constant.DATA_STATUS);

                if (dataStatus != null && dataStatus.equals(NLBL3160Constant.DS_SCHEDULED)) {
                    String configCatgCd = (String) rs.get(NLBL3160Constant.CONFIG_CATG_CD);    // 2019/02/20 T.Ogura [QC#30369,ADD]
                    String schdDelyDt = (String) rs.get(NLBL3160Constant.DELIVERY_SCHEDULED_DATE);
                    // START 2019/02/20 T.Ogura [QC#30369,MOD]
//                    if (cMsg.slsDt.getValue().compareTo(schdDelyDt) == 0 || ZYPDateUtil.addDays(cMsg.slsDt.getValue(), 1).compareTo(schdDelyDt) == 0) {
                    if (CONFIG_CATG.OUTBOUND.equals(configCatgCd) && ZYPDateUtil.addDays(cMsg.slsDt.getValue(), 1).compareTo(schdDelyDt) >= 0) {
                    // END   2019/02/20 T.Ogura [QC#30369,MOD]
                        xxTotCnt_DC = xxTotCnt_DC.add(new BigDecimal(1));
                    // START 2019/02/20 T.Ogura [QC#30369,MOD]
//                    } else if (ZYPDateUtil.addDays(cMsg.slsDt.getValue(), 1).compareTo(schdDelyDt) < 0) {
                    } else if (CONFIG_CATG.OUTBOUND.equals(configCatgCd) && ZYPDateUtil.addDays(cMsg.slsDt.getValue(), 1).compareTo(schdDelyDt) < 0) {
                    // END   2019/02/20 T.Ogura [QC#30369,MOD]
                        xxTotCnt_F = xxTotCnt_F.add(new BigDecimal(1));
                    } else {
                        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_RS.getValue())) {
                            // START 2019/02/20 T.Ogura [QC#30369,MOD]
//                            if (cMsg.slsDt.getValue().compareTo(schdDelyDt) > 0) {
                            if (CONFIG_CATG.INBOUND.equals(configCatgCd) && cMsg.slsDt.getValue().compareTo(schdDelyDt) >= 0) {
                            // END   2019/02/20 T.Ogura [QC#30369,MOD]
                                xxTotCnt_RS = xxTotCnt_RS.add(new BigDecimal(1));
                            }
                        }
                    }
                } else if (dataStatus != null && dataStatus.equals(NLBL3160Constant.DS_NOT_AVAILABLE)) {
                    xxTotCnt_NA = xxTotCnt_NA.add(new BigDecimal(1));
                } else if (dataStatus != null && dataStatus.equals(NLBL3160Constant.DS_ORDERS_TO_SCHEDULE)) {
                    xxTotCnt_S = xxTotCnt_S.add(new BigDecimal(1));
                }

                ordConfMap.put(ordConfList, null);
            }

            ordConfList = new ArrayList<Object>();
        }

        ZYPEZDItemValueSetter.setValue(sMsg.xxTotCnt_S, xxTotCnt_S);
        ZYPEZDItemValueSetter.setValue(sMsg.xxTotCnt_NA, xxTotCnt_NA);
        ZYPEZDItemValueSetter.setValue(sMsg.xxTotCnt_DC, xxTotCnt_DC);
        ZYPEZDItemValueSetter.setValue(sMsg.xxTotCnt_F, xxTotCnt_F);
        ZYPEZDItemValueSetter.setValue(sMsg.xxTotCnt_RS, xxTotCnt_RS);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_S, xxTotCnt_S);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_NA, xxTotCnt_NA);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_DC, xxTotCnt_DC);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_F, xxTotCnt_F);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt_RS, xxTotCnt_RS);
    }
    // END 2017/10/23 S.Katsuma QC#21947 ADD

    /**
     * get array from search text if it has ";" in text field.
     * splitSrchTxt
     * @param srchTxtItem EZDCStringItemring
     * @return ArrayList<String>
     */
    private ArrayList<String> splitSrchTxt(EZDCStringItem srchTxtItem) {

        ArrayList<String> splitSrchTxtList = new ArrayList<String>();
        boolean isSplit = false;

        if (ZYPCommonFunc.hasValue(srchTxtItem)) {
            if (srchTxtItem.getValue().indexOf(NLBL3160Constant.XX_SPL_CHAR_TXT) != -1) {
                String[] srchTxtArray = srchTxtItem.getValue().split(NLBL3160Constant.XX_SPL_CHAR_TXT);

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
     * Check Input Header
     * @param cMsg NLBL3160CMsg
     */
    private boolean checkInputHeader(NLBL3160CMsg cMsg) {
        // Check Coordinator
        if (ZYPCommonFunc.hasValue(cMsg.psnCd)) {
            doProcess_NLBL3160Scrn00_Search_CoordInfo(cMsg);

            if (cMsg.psnCd.isError()) {
                if (NLBL3160Constant.FUNC_ID_COORD.equals(cMsg.xxFuncId.getValue())) {
                    cMsg.psnCd.clearErrorInfo();
                } else {
                    return false;
                }
            }
        } else {
            cMsg.xxPsnFirstMidLastNm.clear();
        }

        // Check Item Number
        if (ZYPCommonFunc.hasValue(cMsg.mdseCd)) {
            doProcess_NLBL3160Scrn00_Get_MdseInfo(cMsg);

            if (cMsg.mdseCd.isError()) {
                return false;
            }
        } else {
            cMsg.mdseNm.clear();
        }

        // Check Customer
        if (ZYPCommonFunc.hasValue(cMsg.dsAcctNum)) {
            doProcess_NLBL3160Scrn00_Get_Customer(cMsg);

            if (cMsg.dsAcctNum.isError()) {
                return false;
            }
        } else {
            cMsg.dsAcctNm.clear();
        }

        // Check Deliver to ST
        if (ZYPCommonFunc.hasValue(cMsg.stCd)) {
            doProcess_NLBL3160Scrn00_Get_State(cMsg);

            if (cMsg.stCd.isError()) {
                return false;
            }
        } else {
            cMsg.stNm.clear();
        }

        // Check Deliver to BR
        if (ZYPCommonFunc.hasValue(cMsg.svcBrCd)) {
            doProcess_NLBL3160Scrn00_Get_Branch(cMsg);

            if (cMsg.svcBrCd.isError()) {
                return false;
            }
        } else {
            cMsg.svcBrCd.clear();
        }

        // Check Warehouse
        // START 2018/04/24 S.Katsuma [QC#24821,MOD]
        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd) && !cMsg.rtlWhCd.getValue().contains("%")) {
            doProcess_NLBL3160Scrn00_Get_RtlWHInfo(cMsg);

            if (cMsg.rtlWhCd.isError()) {
                return false;
            }
        } else {
            cMsg.rtlWhNm.clear();
        }

        // Check Sub Warehouse
        if (ZYPCommonFunc.hasValue(cMsg.rtlSwhCd) && !cMsg.rtlSwhCd.getValue().contains("%")) {
            doProcess_NLBL3160Scrn00_Get_RtlSWHInfo(cMsg);

            if (cMsg.rtlSwhCd.isError()) {
                return false;
            }
        } else {
            cMsg.rtlSwhNm.clear();
        }

        // Combination Source WH and Source SWH
        if ((ZYPCommonFunc.hasValue(cMsg.rtlWhCd) && !cMsg.rtlWhCd.getValue().contains("%"))
                && (ZYPCommonFunc.hasValue(cMsg.rtlSwhCd) && !cMsg.rtlSwhCd.getValue().contains("%"))) {
            RTL_SWHTMsg rtlSwh = new RTL_SWHTMsg();
            ZYPEZDItemValueSetter.setValue(rtlSwh.glblCmpyCd, cMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(rtlSwh.rtlWhCd, cMsg.rtlWhCd.getValue());
            ZYPEZDItemValueSetter.setValue(rtlSwh.rtlSwhCd, cMsg.rtlSwhCd.getValue());
            rtlSwh = (RTL_SWHTMsg) EZDTBLAccessor.findByKey(rtlSwh);

            if (rtlSwh == null) {
                cMsg.rtlWhCd.setErrorInfo(1, "NLZM2279E", new String[] {cMsg.rtlWhCd.getValue(), cMsg.rtlSwhCd.getValue() });
                cMsg.rtlSwhCd.setErrorInfo(1, "NLZM2279E", new String[] {cMsg.rtlWhCd.getValue(), cMsg.rtlSwhCd.getValue() });
                return false;
            }
        }
        // END 2018/04/24 S.Katsuma [QC#24821,MOD]

        // Check User and Coordinator Relation
        if (ZYPCommonFunc.hasValue(cMsg.xxFuncId) && NLBL3160Constant.FUNC_ID_SUPERVISOR_MANAGER.equals(cMsg.xxFuncId.getValue())) {
            if (ZYPCommonFunc.hasValue(cMsg.psnCd)) {
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
                params.put("userId", S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());

                S21SsmEZDResult ssmResult = NLBL3160Query.getInstance().getUserAndCoordRel(params);

                if (ssmResult.isCodeNormal() && ssmResult.getQueryResultCount() > 0) {
                    List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                    if (!resultList.contains(cMsg.psnCd.getValue())) {
                        // Error
                        cMsg.psnCd.setErrorInfo(1, "NLBM1364E", new String[] {"Coordinator" });
                        return false;
                    }
                } else {
                    // Error
                    cMsg.psnCd.setErrorInfo(1, "NLBM1364E", new String[] {"Coordinator" });
                    return false;
                }
            } else {
                // Error
                cMsg.psnCd.setErrorInfo(1, "NLBM0001E", new String[] {});
                return false;
            }
        }
        return true;
    }

    /**
     * setCommonValue
     * @param resultMap
     * @param sMsg
     * @param idx
     */
    private void setCommonValue(Map<String, Object> resultMap, NLBL3160SMsg sMsg, int idx) {
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).xxDt10Dt_A1, (String) resultMap.get(NLBL3160Constant.ORD_BOOK_TS));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).sellToCustCd_A1, (String) resultMap.get(NLBL3160Constant.SELL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).dsAcctNm_A1, (String) resultMap.get(NLBL3160Constant.DS_ACCT_NM));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).shipToCustCd_A1, (String) resultMap.get(NLBL3160Constant.SHIP_TO_CUST_LOC_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).shipToCtyAddr_A1, (String) resultMap.get(NLBL3160Constant.SHIP_TO_CITY_ADDR));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).shipToStCd_A1, (String) resultMap.get(NLBL3160Constant.SHIP_TO_ST_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).svcBrCdDescTxt_A1, (String) resultMap.get(NLBL3160Constant.SVC_BR_CD_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).cpoTotDealNetAmt_A1, (BigDecimal) resultMap.get(NLBL3160Constant.CPO_TOT_DEAL_NET_AMT));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).configCatgCd_A1, (String) resultMap.get(NLBL3160Constant.CONFIG_CATG_CD));
        // START 2018/05/29 S.Katsuma [QC#25232,ADD]
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).dsOrdCatgCd_A1, (String) resultMap.get(NLBL3160Constant.DS_ORD_CATG_CD));
        // END 2018/05/29 S.Katsuma [QC#25232,ADD]
    }

    /**
     * setOrderValue
     * @param resultMap
     * @param sMsg
     * @param idx
     */
    private void setOrderValue(Map<String, Object> resultMap, NLBL3160SMsg sMsg, int idx) {
        setCommonValue(resultMap, sMsg, idx);

        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).cpoOrdNum_A1, (String) resultMap.get(NLBL3160Constant.CPO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).xxTotCnt_BC, (BigDecimal) resultMap.get(NLBL3160Constant.BACK_ORDER_CRITICAL));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).xxTotCnt_BE, (BigDecimal) resultMap.get(NLBL3160Constant.BACK_ORDER_ESSENTIAL));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).xxOrdTs_A1, (String) resultMap.get(NLBL3160Constant.AGE));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).xxTotCnt_TM, (BigDecimal) resultMap.get(NLBL3160Constant.TOT_MACH_ON_SALES_ORDER));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).xxTotCnt_TR, (BigDecimal) resultMap.get(NLBL3160Constant.TOT_RET_ON_SALES_ORDER));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).xxSelFlg_A1, (String) resultMap.get(NLBL3160Constant.CPO_ANCHOR_ACTIVE_FLG));
    }

    /**
     * setConfigValue
     * @param resultMap
     * @param sMsg
     * @param idx
     */
    private void setConfigValue(Map<String, Object> resultMap, NLBL3160SMsg sMsg, int idx) {
        setCommonValue(resultMap, sMsg, idx);

        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).svcConfigMstrPk_A1, (BigDecimal) resultMap.get(NLBL3160Constant.CONFIG_ID));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).soNum_A1, (String) resultMap.get(NLBL3160Constant.TRX_REF_NUM));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).mdlDescTxt_A1, (String) resultMap.get(NLBL3160Constant.MDL_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).rtlSwhCd_A1, (String) resultMap.get(NLBL3160Constant.RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).rddDt_A1, (String) resultMap.get(NLBL3160Constant.RDD_DT));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).nextActDt_A1, (String) resultMap.get(NLBL3160Constant.NEXT_ACT_DT));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).schdCoordStsDescTxt_A1, (String) resultMap.get(NLBL3160Constant.SCHD_COORD_STS_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).schdDelyDt_A1, (String) resultMap.get(NLBL3160Constant.DELIVERY_SCHEDULED_DATE));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).dsSoLineStsDescTxt_A1, (String) resultMap.get(NLBL3160Constant.WMS_STATUS_DESC_TXT));
        if (ZYPCommonFunc.hasValue((String) resultMap.get(NLBL3160Constant.DEINSTALL_DATE))){
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).schdDelyTsDplyTxt_DI, ZYPDateUtil.formatEzd14ToDisp((String) resultMap.get(NLBL3160Constant.DEINSTALL_DATE), true, true, true));
        }
        if (ZYPCommonFunc.hasValue((String) resultMap.get(NLBL3160Constant.EARLIEST_INSTALL_DATE))){
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).schdDelyTsDplyTxt_EI, ZYPDateUtil.formatEzd14ToDisp((String) resultMap.get(NLBL3160Constant.EARLIEST_INSTALL_DATE), true, true, true));
        }
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).fsrNum_A1, (String) resultMap.get(NLBL3160Constant.FSR_NUM));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).locNm_A1, (String) resultMap.get(NLBL3160Constant.LOC_NM));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).rtlWhNm_A1, (String) resultMap.get(NLBL3160Constant.RTL_WH_NM));
        // START 2018/05/29 S.Katsuma [QC#25232,ADD]
        if (resultMap.get(NLBL3160Constant.RMA_CONFIG_ID_PARAM_FLG) != null){
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).xxSelFlg_CA, (String) resultMap.get(NLBL3160Constant.RMA_CONFIG_ID_PARAM_FLG));
        }
        // END 2018/05/29 S.Katsuma [QC#25232,ADD]
    }

    /**
     * handleSameOdrNumOrdList
     * @param sameOdrNumOrdList
     * @param outputList
     * @param validCnt
     * @param svcExchgCatgList
     */
    private void handleSameOdrNumOrdList(List<Map<String, Object>> sameOdrNumOrdList, List<Map<String, Object>> outputList, int validCnt, List<String> svcExchgCatgList) {
        int size = sameOdrNumOrdList.size();
        boolean cpoAncActFlg = false;
        Map<String, Object> cpoWork = null;
        Map<List<Object>, Map<String, Object>> configMap = new LinkedHashMap<List<Object>, Map<String, Object>>();
        BigDecimal backOrdC = BigDecimal.ZERO;
        BigDecimal backOrdE = BigDecimal.ZERO;
        BigDecimal totMachOnSalesOrder = BigDecimal.ZERO;
        BigDecimal totRetOnSalesOrder = BigDecimal.ZERO;
        BigDecimal oldConfigId = null;
        for (int i = 0; i < size; i++) {
            if (outputList.size() >= validCnt) {
                return;
            }
            Map<String, Object> sameOdrNumOdr = sameOdrNumOrdList.get(i);

            if (i == 0) {
                // Sales Order Line
                cpoWork = new HashMap<String, Object>(sameOdrNumOdr);
                // Output Mode
                cpoWork.put(NLBL3160Constant.OUTPUT_MODE, NLBL3160Constant.OUTPUT_MODE_CPO);
            }

            // Output Mode
            sameOdrNumOdr.put(NLBL3160Constant.OUTPUT_MODE, NLBL3160Constant.OUTPUT_MODE_CONFIG);

            // Check CPO Anchor Activation
            String configCatgCd = (String) sameOdrNumOdr.get(NLBL3160Constant.CONFIG_CATG_CD);
            if (!cpoAncActFlg) {
                if (CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
                    cpoAncActFlg = true;
                }
            }

            // B/O C, B/O E
            if (((BigDecimal) sameOdrNumOdr.get(NLBL3160Constant.BACK_ORDER_CRITICAL)).compareTo(BigDecimal.ZERO) > 0) {
                backOrdC = backOrdC.add((BigDecimal) sameOdrNumOdr.get(NLBL3160Constant.BACK_ORDER_CRITICAL));
            }
            if (((BigDecimal) sameOdrNumOdr.get(NLBL3160Constant.BACK_ORDER_ESSENTIAL)).compareTo(BigDecimal.ZERO) > 0) {
                backOrdE = backOrdE.add((BigDecimal) sameOdrNumOdr.get(NLBL3160Constant.BACK_ORDER_ESSENTIAL));
            }

            // START 2017/10/23 S.Katsuma QC#21945 ADD
            final BigDecimal configId = (BigDecimal) sameOdrNumOdr.get(NLBL3160Constant.CONFIG_ID);
            // Calculate Tot Mach on Sales Order / Tot Ret. on Sales Order
            if (ZYPCommonFunc.hasValue(oldConfigId)) {
                if (oldConfigId.compareTo(configId) != 0) {
                    if (CONFIG_CATG.INBOUND.equals(configCatgCd)) {
                        totRetOnSalesOrder = totRetOnSalesOrder.add(new BigDecimal(1));
                    } else if (CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
                        totMachOnSalesOrder = totMachOnSalesOrder.add(new BigDecimal(1));
                    }
                }
            } else {
                if (ZYPCommonFunc.hasValue(configId)) {
                    if (CONFIG_CATG.INBOUND.equals(configCatgCd)) {
                        totRetOnSalesOrder = totRetOnSalesOrder.add(new BigDecimal(1));
                    } else if (CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
                        totMachOnSalesOrder = totMachOnSalesOrder.add(new BigDecimal(1));
                    }
                }
            }
            oldConfigId = configId;
            // END 2017/10/23 S.Katsuma QC#21945 ADD

            final String trxRefNum = (String) sameOdrNumOdr.get(NLBL3160Constant.TRX_REF_NUM);
            final String rtlSubWh = (String) sameOdrNumOdr.get(NLBL3160Constant.RTL_SWH_CD);
            List<Object> sameConfigPtrnList = new ArrayList<Object>() {
                {
                    add(configId);
                    add(trxRefNum);
                    add(rtlSubWh);
                }
            };
            if (configMap.containsKey(sameConfigPtrnList)) {
                // Exist same config pattarn
                Map<String, Object> sameConfigMap = configMap.get(sameConfigPtrnList);

                // Compare SCHD_COORD_STS_CD
                if (sameConfigMap.get(NLBL3160Constant.SCHD_COORD_STS_CD) == null || sameOdrNumOdr.get(NLBL3160Constant.SCHD_COORD_STS_CD) == null) {
                    sameConfigMap.put(NLBL3160Constant.SCHD_COORD_STS_CD, null);
                    sameConfigMap.put(NLBL3160Constant.SCHD_COORD_STS_DESC_TXT, null);
                } else {
                    String lastSchdCoodStsCd = (String) sameConfigMap.get(NLBL3160Constant.SCHD_COORD_STS_CD);
                    String currentSchdCoodStsCd = (String) sameOdrNumOdr.get(NLBL3160Constant.SCHD_COORD_STS_CD);
                    if (Integer.parseInt(currentSchdCoodStsCd) < Integer.parseInt(lastSchdCoodStsCd)) {
                        sameConfigMap.put(NLBL3160Constant.SCHD_COORD_STS_CD, currentSchdCoodStsCd.toString());
                        sameConfigMap.put(NLBL3160Constant.SCHD_COORD_STS_DESC_TXT, (String) sameOdrNumOdr.get(NLBL3160Constant.SCHD_COORD_STS_DESC_TXT));
                    }
                }

                // Compare DELIVERY_SCHEDULED_DATE
                if (sameConfigMap.get(NLBL3160Constant.DELIVERY_SCHEDULED_DATE) == null || sameOdrNumOdr.get(NLBL3160Constant.DELIVERY_SCHEDULED_DATE) == null) {
                    sameConfigMap.put(NLBL3160Constant.DELIVERY_SCHEDULED_DATE, null);
                } else {
                    String lastDelyScheDt = (String) sameConfigMap.get(NLBL3160Constant.DELIVERY_SCHEDULED_DATE);
                    String currentDelyScheDt = (String) sameOdrNumOdr.get(NLBL3160Constant.DELIVERY_SCHEDULED_DATE);
                    if (0 < lastDelyScheDt.compareTo(currentDelyScheDt)) {
                        sameConfigMap.put(NLBL3160Constant.DELIVERY_SCHEDULED_DATE, currentDelyScheDt);
                    }
                }

                // Compare WMS Status
                if (sameConfigMap.get(NLBL3160Constant.WMS_STATUS) == null || sameOdrNumOdr.get(NLBL3160Constant.WMS_STATUS) == null) {
                    sameConfigMap.put(NLBL3160Constant.WMS_STATUS, null);
                    sameConfigMap.put(NLBL3160Constant.WMS_STATUS_DESC_TXT, null);
                } else {
                    String lastWmsStatus = (String) sameConfigMap.get(NLBL3160Constant.WMS_STATUS);
                    String currentWmsStatus = (String) sameOdrNumOdr.get(NLBL3160Constant.WMS_STATUS);
                    if (Integer.parseInt(currentWmsStatus) < Integer.parseInt(lastWmsStatus)) {
                        sameConfigMap.put(NLBL3160Constant.WMS_STATUS, currentWmsStatus.toString());
                        sameConfigMap.put(NLBL3160Constant.WMS_STATUS_DESC_TXT, (String) sameOdrNumOdr.get(NLBL3160Constant.WMS_STATUS_DESC_TXT));
                    }
                }

                // Compare EARLIEST_INSTALL_DATE
                if (sameConfigMap.get(NLBL3160Constant.EARLIEST_INSTALL_DATE) == null || sameOdrNumOdr.get(NLBL3160Constant.EARLIEST_INSTALL_DATE) == null) {
                    sameConfigMap.put(NLBL3160Constant.EARLIEST_INSTALL_DATE, null);
                    sameConfigMap.put(NLBL3160Constant.FSR_NUM, (String) sameOdrNumOdr.get(NLBL3160Constant.FSR_NUM));
                } else {
                    String lastEarliestInstallDt = (String) sameConfigMap.get(NLBL3160Constant.EARLIEST_INSTALL_DATE);
                    String currenEarliestInstallDt = (String) sameOdrNumOdr.get(NLBL3160Constant.EARLIEST_INSTALL_DATE);
                    if (0 < lastEarliestInstallDt.compareTo(currenEarliestInstallDt)) {
                        sameConfigMap.put(NLBL3160Constant.EARLIEST_INSTALL_DATE, currenEarliestInstallDt);
                        sameConfigMap.put(NLBL3160Constant.FSR_NUM, (String) sameOdrNumOdr.get(NLBL3160Constant.FSR_NUM));
                    }
                }
            } else {
                // New config pattarn
                configMap.put(sameConfigPtrnList, sameOdrNumOdr);
            }

            if (i == size - 1) {
                if (cpoAncActFlg) {
                    cpoWork.put(NLBL3160Constant.CPO_ANCHOR_ACTIVE_FLG, ZYPConstant.FLG_ON_Y);
                } else {
                    cpoWork.put(NLBL3160Constant.CPO_ANCHOR_ACTIVE_FLG, ZYPConstant.FLG_OFF_N);
                }
                // B/O C
                cpoWork.put(NLBL3160Constant.BACK_ORDER_CRITICAL, backOrdC);
                // B/O E
                cpoWork.put(NLBL3160Constant.BACK_ORDER_ESSENTIAL, backOrdE);
                // Tot Mach on Sales Order
                cpoWork.put(NLBL3160Constant.TOT_MACH_ON_SALES_ORDER, totMachOnSalesOrder);
                // Tot Ret. on Sales Order
                cpoWork.put(NLBL3160Constant.TOT_RET_ON_SALES_ORDER, totRetOnSalesOrder);
                outputList.add(cpoWork);

                for (List<Object> key : configMap.keySet()) {
                    if (outputList.size() >= validCnt) {
                        return;
                    }

                    // START 2018/05/29 S.Katsuma [QC#25232,ADD]
                    Map<String, Object> rec = configMap.get(key);
                    if (rec.get(NLBL3160Constant.CONFIG_ID) != null) {
                        if (CONFIG_CATG.INBOUND.equals(rec.get(NLBL3160Constant.CONFIG_CATG_CD).toString())) {
                            if (hasConfigIdForRMA(rec, svcExchgCatgList)) {
                                rec.put(NLBL3160Constant.RMA_CONFIG_ID_PARAM_FLG, ZYPConstant.FLG_ON_Y);
                            }
                        }
                    }
                    // END 2018/05/29 S.Katsuma [QC#25232,ADD]
                    outputList.add(rec);
                }
            }
        }
    }

    // START 2018/05/29 S.Katsuma [QC#25232,ADD]
    /**
     * hasConfigIdForRMA
     * @param asMsg NLBL3160_ASMsg
     * @param svcExchgCatgList List<String>
     * @return boolean
     */
    private boolean hasConfigIdForRMA(Map<String, Object> map, List<String> svcExchgCatgList) {
        boolean hasConfig = false;

        if (map != null && !map.isEmpty()) {
            // If RMA does not have Base Component, true
            if (isBaseComponent(map)) {
                // If RMA is not Service Exchange Order, true
                if (isSvcExchangeOrder(map.get(NLBL3160Constant.DS_ORD_CATG_CD).toString(), svcExchgCatgList)) {
                    hasConfig = false;
                } else {
                    hasConfig = true;
                }
            } else {
                hasConfig = false;
            }
        } else {
            hasConfig = false;
        }

        return hasConfig;
    }

    /**
     * isBaseComponent
     * @param asMsg NLBL3160_ASMsg
     * @return boolean
     */
    private boolean isBaseComponent(Map<String, Object> map) {
        boolean isBaseComponent = false;

        if (ZYPCommonFunc.hasValue(map.get(NLBL3160Constant.CONFIG_ID).toString())) {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("glblCmpyCd", getGlobalCompanyCode());
            param.put("svcConfigMstrPk", map.get(NLBL3160Constant.CONFIG_ID).toString());
            param.put("trxHdrNum", map.get(NLBL3160Constant.CPO_ORD_NUM).toString());

            S21SsmEZDResult ssmResult = NLBL3160Query.getInstance().getSvcMachTpCdList(param);

            if (ssmResult.isCodeNormal()) {
                List<String> svcMachTpCdList = (List<String>) ssmResult.getResultObject();
                if (svcMachTpCdList == null || svcMachTpCdList.isEmpty()) {
                    isBaseComponent = false;
                } else {
                    for (String svcMachTpCd : svcMachTpCdList) {
                        if (ZYPCommonFunc.hasValue(svcMachTpCd) && SVC_MACH_TP.MACHINE.equals(svcMachTpCd)) {
                            isBaseComponent = true;
                            break;
                        }
                    }
                }
            } else {
                isBaseComponent = false;
            }
        }

        return isBaseComponent;
    }

    /**
     * isSvcExchangeOrder
     * @param dsOrdCatgCd String
     * @param svcExchgCatgList List<String>
     * @return List<String>
     */
    private boolean isSvcExchangeOrder(String dsOrdCatgCd, List<String> svcExchgCatgList) {
        boolean isSvcExchangeOrder = false;

        if (svcExchgCatgList != null && !svcExchgCatgList.isEmpty()) {
            if (svcExchgCatgList.contains(dsOrdCatgCd)) {
                isSvcExchangeOrder = true;
            } else {
                isSvcExchangeOrder = false;
            }
        } else {
            isSvcExchangeOrder = false;
        }

        return isSvcExchangeOrder;
    }

    /**
     * getSvcExchgCatgList
     * @return List<String>
     */
    private List<String> getSvcExchgCatgList() {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", getGlobalCompanyCode());
        param.put("ordCatgCtxTpCd", NLBL3160Constant.ORD_CATG_CTX_TP_CD_SE);

        S21SsmEZDResult ssmResult = NLBL3160Query.getInstance().getSvcExchgCatgList(param);
        if (ssmResult.isCodeNormal()) {
            List<String> svcExchgCatgList = (List<String>) ssmResult.getResultObject();
            if (svcExchgCatgList == null || svcExchgCatgList.isEmpty()) {
                return null;
            } else {
                return svcExchgCatgList;
            }
        } else {
            return null;
        }
    }
    // END 2018/05/29 S.Katsuma [QC#25232,ADD]
}
