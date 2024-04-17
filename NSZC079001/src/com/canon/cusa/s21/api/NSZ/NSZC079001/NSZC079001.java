/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC079001;

import static com.canon.cusa.s21.api.NSZ.NSZC079001.constant.NSZC079001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDItemAttribute;
import parts.common.EZDPItem;
import business.db.DS_COND_CONSTTMsg;
import business.db.DS_COND_CONSTTMsgArray;
import business.db.RTL_WHTMsg;
import business.db.WH_OWNRTMsg;
import business.parts.NLZC300001PMsg;
import business.parts.NSZC079001PMsg;
import business.parts.NSZC079001_mdseListPMsg;

import com.canon.cusa.s21.api.NLZ.NLZC300001.NLZC300001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_OWNR;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_OWNR;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Inventory Search API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/21/2015   Hitachi         T.Iwamoto       Create          NA#Inventory Search API
 * 12/22/2015   Fujitsu         S.Nakai         Update          Add columns to PMsg
 * 04/11/2016   Hitachi         K.Yamada        Update          QC#2603
 * 11/07/2016   Hitachi         T.Tomita        Update          QC#15757
 * 11/09/2016   Hitachi         T.Mizuki        Update          QC#15862
 * 11/10/2016   Hitachi         T.Tomita        Update          QC#15757
 * 11/15/2016   Hitachi         T.Tomita        Update          QC#15757
 * 11/18/2016   Hitachi         A.Kohinata      Update          QC#15952
 * 11/22/2016   Hitachi         A.Kohinata      Update          QC#15952
 * 11/30/2016   Hitachi         A.Kohinata      Update          QC#15952
 * 12/01/2016   Hitachi         A.Kohinata      Update          QC#16299
 * 10/24/2016   Hitachi         K.Fujimoto      Update          QC#53410
 * 2019/11/21   Hitachi         K.Kitachi       Update          QC#54768
 * 2019/11/22   Hitachi         H.Umeda         Update          QC#52261
 * 2019/12/12   Hitachi         K.Kitachi       Update          QC#55055
 * 2021/01/06   CITS            M.Furugoori     Update          QC#58207
 * 2023/09/06   Hitachi         N.Takatsu       Update          QC#61661
 * </pre>
 */
public class NSZC079001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** onBatchType */
    private ONBATCH_TYPE onBatchType = null;

    // mod start 2016/11/15 CSA Defect#15757
    /** MDSE List : No records Count */
    private BigDecimal noRecordCnt = BigDecimal.ZERO;

    /** MDSE List : No Records MDSE Name */
    private String noRecordMdseNm = null;

    /** MDSE List : Too many Count */
    private BigDecimal tooManyCnt = new BigDecimal(100);

    /** MDSE List : Too many MDSE Name */
    private String tooManyMdseNm = null;

    /** VarCharConst:WH_OWNR_CUSA */
    private String whOwnrCusa = null;

    /** VarCharConst:WH_OWNR_CVI */
    private String whOwnrCvi = null;

    /** VarCharConst:LOCAL */
    private String local = null;

    // START 2019/10/24 K.Fujimoto [QC#53410,ADD]
    /** VarCharConst:NSZC0790_ALL_SATELLITES */
    private String allSatellites = null;
    // END   2019/10/24 K.Fujimoto [QC#53410,ADD]

    // START 2023/09/06 N.Takatsu [QC#61661, ADD]
    /** VarCharConst:NSZC0790_ALL_PARTS_CENTERS */
    private String allPartsCenters = null;
    // END   2023/09/06 N.Takatsu [QC#61661, ADD]
    /** VarCharConst:whRtlSwhCdList */
    private List<String> whRtlSwhCdList = null;

    /** VarCharConst:tecRtlSwhCdList */
    private List<String> tecRtlSwhCdList = null;

    /** DS_COND_CONST Data : NPZC0790_LIST_01 */
    private DS_COND_CONSTTMsg dsCondConstList01 = null;

    /** DS_COND_CONST Data : NPZC0790_LIST_02 */
    private DS_COND_CONSTTMsg dsCondConstList02 = null;

    /** DS_COND_CONST Data : NPZC0790_LIST_03 */
    private DS_COND_CONSTTMsg dsCondConstList03 = null;

    /** DS_COND_CONST Data : NPZC0790_LIST_04 */
    private DS_COND_CONSTTMsg dsCondConstList04 = null;

    /** DS_COND_CONST Data : NPZC0790_LIST_05 */
    private DS_COND_CONSTTMsg dsCondConstList05 = null;

    /** DS_COND_CONST Data : NPZC0790_LIST_06 */
    private DS_COND_CONSTTMsg dsCondConstList06 = null;
    // mod end 2016/11/15 CSA Defect#15757

    // add start 2016/11/18 CSA QC#15952
    /** No Records Message */
    private String noRecordMessage = null;

    /** Too many Message */
    private String tooManyMessage = null;

    /** Too many flg */
    boolean tooManyFlg = false;
    // add end 2016/11/18 CSA QC#15952

    /**
     * Constructor
     */
    public NSZC079001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param param NSZC079001PMsg
     * @param onBatchTp ONBATCH_TYPE
     */
    public void execute(final NSZC079001PMsg param, final ONBATCH_TYPE onBatchTp) {
        this.onBatchType = onBatchTp;
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        if (checkParameter(msgMap)) {
            doProcess(msgMap, param);
        }

        msgMap.flush();
    }

    private boolean checkParameter(S21ApiMessageMap msgMap) {
        NSZC079001PMsg pMsg = (NSZC079001PMsg) msgMap.getPmsg();

        if (!hasValue(pMsg.glblCmpyCd)) {
            setValue(pMsg.glblCmpyCd, GLBL_CMPY_CD);
        }
        if (!hasValue(pMsg.slsDt)) {
            setValue(pMsg.slsDt, (String) ZYPDateUtil.getSalesDate(pMsg.glblCmpyCd.getValue()));
        }
        mandatoryCheck(msgMap, pMsg.procFlg, NSZM0732E);
        mandatoryCheck(msgMap, pMsg.mdseCd, NSZM0733E);
        mandatoryCheck(msgMap, pMsg.reqTechCd, NSZM0734E);
        if (hasValue(pMsg.procFlg)) {
            if (!FLG_ON_Y.equals(pMsg.procFlg.getValue()) && !FLG_OFF_N.equals(pMsg.procFlg.getValue())) {
                msgMap.addXxMsgId(NSZM0735E);
            }
        }
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        // START 2019/11/22 H.Umeda [QC#52261,ADD]
        String mdsecd = pMsg.mdseCd.getValue();
        if(mdsecd.length() > 16 ){
            pMsg.mdseCd.setValue(mdsecd.substring(0, 16));
        }
        // END   2019/11/22 H.Umeda [QC#52261,ADD]
        return true;
    }

    private void mandatoryCheck(S21ApiMessageMap msgMap, EZDPItem targetItem, String messageId) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgId(messageId);
        }
    }

    private void doProcess(S21ApiMessageMap msgMap, NSZC079001PMsg pMsg) {
        // add start 2016/11/18 CSA QC#15952
        setUpDsCondConst(pMsg.glblCmpyCd.getValue());
        // add end 2016/11/18 CSA QC#15952

        if (FLG_ON_Y.equals(pMsg.procFlg.getValue())) {
            // del start 2016/11/18 CSA QC#15952
            // add start 2016/11/07 CSA Defect#15757
            //setUpDsCondConst(pMsg.glblCmpyCd.getValue());
            // add end 2016/11/07 CSA Defect#15757
            // del end 2016/11/18 CSA QC#15952
            partsSearch(pMsg);
        } else if (FLG_OFF_N.equals(pMsg.procFlg.getValue())) {
            inventoryAvailability(pMsg, msgMap);
        }
    }

    // add start 2016/11/07 CSA Defect#15757
    private void setUpDsCondConst(String glblCmpyCd) {
// mod start 2016/11/09 CSA QC#15862
        NSZC079001_mdseListPMsg mdseList = new NSZC079001_mdseListPMsg();
        EZDItemAttribute itemMdseNm = mdseList.getAttr("mdseNm");
// mod end 2016/11/09 CSA QC#15862

        DS_COND_CONSTTMsg dsCondConstTMsg;
        // No records
        dsCondConstTMsg = getDsCondConst(glblCmpyCd, NSZC0790_SEARCH, NO_RECORDS);
        if (dsCondConstTMsg != null) {
            if (hasValue(dsCondConstTMsg.dsCondConstValTxt_01)) {
                this.noRecordCnt = new BigDecimal(dsCondConstTMsg.dsCondConstValTxt_01.getValue());
            }
// mod start 2016/11/09 CSA QC#15862
//            this.noRecordMdseCd = dsCondConstTMsg.dsCondConstValTxt_02.getValue();
            this.noRecordMdseNm = dsCondConstTMsg.dsCondConstValTxt_03.getValue();
            if (hasValue(this.noRecordMdseNm) && itemMdseNm.getDigit() < this.noRecordMdseNm.length()) {
                this.noRecordMdseNm = this.noRecordMdseNm.substring(0,itemMdseNm.getDigit());
            }
// mod end 2016/11/09 CSA QC#15862

            // add start 2016/11/18 CSA QC#15952
            this.noRecordMessage = dsCondConstTMsg.dsCondConstValTxt_02.getValue();
            // add end 2016/11/18 CSA QC#15952
        }

        // Too many records
        dsCondConstTMsg = getDsCondConst(glblCmpyCd, NSZC0790_SEARCH, MAX_RECORDS);
        if (dsCondConstTMsg != null) {
            if (hasValue(dsCondConstTMsg.dsCondConstValTxt_01)) {
                this.tooManyCnt = new BigDecimal(dsCondConstTMsg.dsCondConstValTxt_01.getValue());
            }
// mod start 2016/11/09 CSA QC#15862
//            this.tooManyMdseCd = dsCondConstTMsg.dsCondConstValTxt_02.getValue();
            this.tooManyMdseNm = dsCondConstTMsg.dsCondConstValTxt_03.getValue();
            if (hasValue(this.tooManyMdseNm) && itemMdseNm.getDigit() < this.tooManyMdseNm.length()) {
                this.tooManyMdseNm = this.tooManyMdseNm.substring(0,itemMdseNm.getDigit());
            }
// mod end 2016/11/09 CSA QC#15862

            // add start 2016/11/18 CSA QC#15952
            this.tooManyMessage = dsCondConstTMsg.dsCondConstValTxt_02.getValue();
            // add end 2016/11/18 CSA QC#15952
        }
    }

    private DS_COND_CONSTTMsg getDsCondConst(String glblCmpyCd, String grpId, String constCd) {
        DS_COND_CONSTTMsg inMsg = new DS_COND_CONSTTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsCondConstGrpId, grpId);
        setValue(inMsg.dsCondConstCd, constCd);
        return (DS_COND_CONSTTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }
    // add end 2016/11/07 CSA Defect#15757

    private void partsSearch(NSZC079001PMsg pMsg) {
        // add start 2016/11/07 CSA Defect#15757
        if (!chkMdseList(pMsg)) {
            return;
        }
        // add end 2016/11/07 CSA Defect#15757
        List<Map<String, Object>> partsInfo = getMerchandise(pMsg);
        if (partsInfo.size() > 0) {
            setOutputParamByParts(pMsg, partsInfo);
        }
    }

    // add start 2016/11/07 CSA Defect#15757
    private boolean chkMdseList(NSZC079001PMsg pMsg) {

        BigDecimal mdseCnt = getMerchandiseCnt(pMsg);
        if (mdseCnt.compareTo(this.noRecordCnt) <= 0) {
            // No records
            // mod start 2016/11/30 CSA QC#15862
            // mod start 2016/11/09 CSA QC#15862
            //setValue(pMsg.mdseList.no(0).mdseCd, this.noRecordMdseCd);
            //setValue(pMsg.mdseList.no(0).mdseCd, pMsg.mdseCd);
            // mod end 2016/11/09 CSA QC#15862
            //setValue(pMsg.mdseList.no(0).mdseNm, this.noRecordMdseNm);
            //pMsg.mdseList.setValidCount(1);
            pMsg.mdseList.clear();
            pMsg.mdseList.setValidCount(0);
            // mod end 2016/11/30 CSA QC#15862
            // add start 2016/11/18 CSA QC#15952
            setValue(pMsg.clickMsgTxt, this.noRecordMessage);
            // add end 2016/11/18 CSA QC#15952
            return false;
        }

        if (mdseCnt.compareTo(this.tooManyCnt) >= 0) {
            // Too many records
            // mod start 2016/11/18 CSA QC#15952
            // mod start 2016/11/09 CSA QC#15862
            //setValue(pMsg.mdseList.no(0).mdseCd, this.tooManyMdseCd);
            //setValue(pMsg.mdseList.no(0).mdseCd, pMsg.mdseCd);
            // mod end 2016/11/09 CSA QC#15862
            //setValue(pMsg.mdseList.no(0).mdseNm, this.tooManyMdseNm);
            //pMsg.mdseList.setValidCount(1);
            pMsg.mdseList.clear();
            pMsg.mdseList.setValidCount(0);
            // mod start 2016/11/22 CSA QC#15952
            setValue(pMsg.clickMsgTxt, this.tooManyMessage);
            return false;
            // mod end 2016/11/22 CSA QC#15952
            // mod end 2016/11/18 CSA QC#15952
        }
        return true;
    }
    // add end 2016/11/07 CSA Defect#15757

    // mod start 2016/11/15 CSA Defect#15757
    private void inventoryAvailability(NSZC079001PMsg pMsg, S21ApiMessageMap msgMap) {
        if (!initVarCharConst(msgMap, pMsg.glblCmpyCd.getValue())) {
            return;
        }

        // CSA
        if (!setOutputParamByCSA(pMsg, msgMap)) {
            return;
        }

        // CVI,CUSA
        NLZC300001PMsg rsIvtRefApiMsg = callInventoryReferenceAPI(pMsg);
        setOutputParamByIvtRefApi(msgMap, pMsg, rsIvtRefApiMsg);

        // add start 2016/11/18 CSA QC#15952
        if (pMsg.whList.getValidCount() == 0) {
            setValue(pMsg.clickMsgTxt, this.noRecordMessage);
        }
        if (this.tooManyFlg) {
            setValue(pMsg.clickMsgTxt, this.tooManyMessage);
            // add start 2016/11/22 CSA QC#15952
            pMsg.whList.clear();
            pMsg.whList.setValidCount(0);
            // add end 2016/11/22 CSA QC#15952
        }
        // add end 2016/11/18 CSA QC#15952
    }
    // mod end 2016/11/15 CSA Defect#15757

    // add start 2016/11/10 CSA Defect#15757
    private boolean initVarCharConst(S21ApiMessageMap msgMap, String glblCmpyCd) {
        // CUSA
        this.whOwnrCusa = ZYPCodeDataUtil.getVarCharConstValue(NSZC0790_WH_OWNER_CUSA, glblCmpyCd);
        if (!hasValue(this.whOwnrCusa)) {
            msgMap.addXxMsgId(NSZM0102E);
            return false;
        }

        // CVI
        this.whOwnrCvi = ZYPCodeDataUtil.getVarCharConstValue(NSZC0790_WH_OWNER_CVI, glblCmpyCd);
        if (!hasValue(this.whOwnrCvi)) {
            msgMap.addXxMsgId(NSZM0102E);
            return false;
        }

        // LOCAL
        this.local = ZYPCodeDataUtil.getVarCharConstValue(LOCAL, glblCmpyCd);
        if (!hasValue(this.local)) {
            msgMap.addXxMsgId(NSZM0102E);
            return false;
        }

        // whRtlSwhCdList
        this.whRtlSwhCdList = addConstData(msgMap, NEW_SUBWH_WH, (String) glblCmpyCd);
        if (this.whRtlSwhCdList == null) {
            return false;
        }

        // tecRtlSwhCdList
        this.tecRtlSwhCdList = addConstData(msgMap, NEW_SUBWH_TECH, (String) glblCmpyCd);
        if (this.tecRtlSwhCdList == null) {
            return false;
        }

        // NPZC0790_LIST_01
        this.dsCondConstList01 = getDsCondConst(glblCmpyCd, NSZC0790_LIST_01);
        if (dsCondConstList01 == null) {
            msgMap.addXxMsgId(NSZM1076E);
            return false;
        }

        // NPZC0790_LIST_02
        this.dsCondConstList02 = getDsCondConst(glblCmpyCd, NSZC0790_LIST_02);
        if (dsCondConstList02 == null) {
            msgMap.addXxMsgId(NSZM1076E);
            return false;
        }

        // NPZC0790_LIST_03
        this.dsCondConstList03 = getDsCondConst(glblCmpyCd, NSZC0790_LIST_03);
        if (dsCondConstList03 == null) {
            msgMap.addXxMsgId(NSZM1076E);
            return false;
        }

        // NPZC0790_LIST_04
        this.dsCondConstList04 = getDsCondConst(glblCmpyCd, NSZC0790_LIST_04);
        if (dsCondConstList04 == null) {
            msgMap.addXxMsgId(NSZM1076E);
            return false;
        }

        // NPZC0790_LIST_05
        this.dsCondConstList05 = getDsCondConst(glblCmpyCd, NSZC0790_LIST_05);
        if (dsCondConstList05 == null) {
            msgMap.addXxMsgId(NSZM1076E);
            return false;
        }

        // NPZC0790_LIST_06
        this.dsCondConstList06 = getDsCondConst(glblCmpyCd, NSZC0790_LIST_06);
        if (dsCondConstList06 == null) {
            msgMap.addXxMsgId(NSZM1076E);
            return false;
        }

        // START 2023/09/06 N.Takatsu [QC#61661, ADD]
        this.allPartsCenters = ZYPCodeDataUtil.getVarCharConstValue(NSZC0790_ALL_PARTS_CENTERS, glblCmpyCd);
        if (!hasValue(this.allPartsCenters)) {
            this.allPartsCenters = ALL_PARTS_CENTERS;
        }
        // END   2023/09/06 N.Takatsu [QC#61661, ADD]

        // START 2019/10/24 K.Fujimoto [QC#53410,ADD]
        this.allSatellites = ZYPCodeDataUtil.getVarCharConstValue(NSZC0790_ALL_SATELLITES, glblCmpyCd);
        if (!hasValue(this.allSatellites)) {
            this.allSatellites = ALL_SATELLITES;
        }

        // END   2019/10/24 K.Fujimoto [QC#53410,ADD]
        return true;
    }
    // add end 2016/11/10 CSA Defect#15757

    // add start 2016/11/15 CSA Defect#15757
    private boolean setOutputParamByCSA(NSZC079001PMsg pMsg, S21ApiMessageMap msgMap) {
        Map<String, Object> retailWhInfo = getRetailWareHouseInfo(pMsg);
        if (retailWhInfo == null) {
            msgMap.addXxMsgId(NSZM0736E);
            return false;
        }

        List<String> techTocCdList = new ArrayList<String>();
        // My Warehouse
        techTocCdList.add(pMsg.reqTechCd.getValue());
        List<Map<String, Object>> csaInvtyAvltyMyWh = getCsaInventoryAvailabilityTech(pMsg, techTocCdList);
        setOutputParamByCsaMyWh(pMsg, csaInvtyAvltyMyWh);

        // Team
        techTocCdList = new ArrayList<String>();
        for (int i = 0; i < pMsg.teamTechList.getValidCount(); i++) {
            if (hasValue(pMsg.teamTechList.no(i).techTocCd.getValue()) && !pMsg.reqTechCd.getValue().equals(pMsg.teamTechList.no(i).techTocCd.getValue())) {
                techTocCdList.add((String) pMsg.teamTechList.no(i).techTocCd.getValue());
            }
        }
        List<Map<String, Object>> csaInvtyAvltyTeam = getCsaInventoryAvailabilityTech(pMsg, techTocCdList);
        setOutputParamByCsaTeam(pMsg, csaInvtyAvltyTeam, techTocCdList);

        // CSA Warehouse
        List<Map<String, Object>> csaInvtyAvltyWh = getCsaInventoryAvailabilityWh(pMsg, retailWhInfo);
        setOutputParamByCsaWh(pMsg, csaInvtyAvltyWh);

        return true;
    }
    // add end 2016/11/15 CSA Defect#15757

    private List<Map<String, Object>> getMerchandise(NSZC079001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        String searchMdseCd = (String) pMsg.mdseCd.getValue();
        searchMdseCd = "%".concat(searchMdseCd).concat("%");
        param.put("mdseCd", searchMdseCd);
        // add start 2016/11/15 CSA QC#15952
        param.put("rowNum", this.tooManyCnt);
        // add end 2016/11/15 CSA QC#15952
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rs = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getMerchandise", param);
        return rs;
    }

    // add start 2016/11/07 CSA Defect#15757
    private BigDecimal getMerchandiseCnt(NSZC079001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        String searchMdseCd = (String) pMsg.mdseCd.getValue();
        searchMdseCd = "%".concat(searchMdseCd).concat("%");
        param.put("mdseCd", searchMdseCd);
        return (BigDecimal) this.ssmBatchClient.queryObject("getMerchandiseCnt", param);
    }
    // add end 2016/11/07 CSA Defect#15757

    private Map<String, Object> getRetailWareHouseInfo(NSZC079001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("reqTechCd", pMsg.reqTechCd.getValue());
        @SuppressWarnings("unchecked")
        Map<String, Object> rs = (Map<String, Object>) this.ssmBatchClient.queryObject("getRetailWareHouseInfo", param);
        return rs;
    }

    // mod start 2016/11/15 CSA Defect#15757
    private List<Map<String, Object>> getCsaInventoryAvailabilityWh(NSZC079001PMsg pMsg, Map<String, Object> inMap) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("mdseCd", pMsg.mdseCd.getValue());
        param.put("locStsCd", LOC_STS.DC_STOCK);
        param.put("whLocTpCd", LOC_TP.WAREHOUSE);
        // START 2019/12/12 K.Kitachi [QC#55055, ADD]
        param.put("stkStsCd", STK_STS.GOOD);
        // END 2019/12/12 K.Kitachi [QC#55055, ADD]
        param.put("invtyOwnrCd", INVTY_OWNR.CSA);
        param.put("local", this.local);
        param.put("whRtlSwhCdList", this.whRtlSwhCdList);

        String defSrcRtlSwhCd = (String) inMap.get("DEF_SRC_RTL_SWH_CD");
        if (hasValue(defSrcRtlSwhCd)) {
            param.put("defSrcRtlSwhCd", defSrcRtlSwhCd);
        } else {
           param.put("defSrcRtlSwhCd", BRANK);
        }

        String defSrcRtlWhCd = (String) inMap.get("DEF_SRC_RTL_WH_CD");
        if (hasValue(defSrcRtlWhCd)) {
            param.put("defSrcRtlWhCd", defSrcRtlWhCd);
        } else {
            param.put("defSrcRtlWhCd", BRANK);
        }

        @SuppressWarnings("unchecked")
        // START 2023/09/06 N.Takatsu [QC#61661, MOD]
//      List<Map<String, Object>> rs = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getCsaInventoryAvailabilityWh", param);
        List<Map<String, Object>> getCsaInventoryAvailabilityWhList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getCsaInventoryAvailabilityWh", param);
        // END   2023/09/06 N.Takatsu [QC#61661, MOD]

        // START 2023/09/06 N.Takatsu [QC#61661, ADD]
        List<Map<String, Object>> getCsaInventoryAvailabilityAllPartsCentersList = getCsaInventoryAvailabilityAllPartsCenters(pMsg, inMap);
        List<Map<String, Object>> getCsaInventoryAvailabilityDefaultSourceWhList = getCsaInventoryAvailabilityDefaultSourceWh(pMsg, inMap);
        List<Map<String, Object>> rs = getCsaInventoryAvailabilityWhSort(pMsg, getCsaInventoryAvailabilityWhList, getCsaInventoryAvailabilityAllPartsCentersList, getCsaInventoryAvailabilityDefaultSourceWhList, defSrcRtlWhCd);
        // END   2023/09/06 N.Takatsu [QC#61661, ADD]

        // START 2019/10/24 K.Fujimoto [QC#53410,ADD]
        // CSA Warehouse(Satellite)
        if (rs == null || rs.size() == 0) {
            return rs;
        }
        List<Map<String, Object>> csaSatelliteAvltyWh = getSatellitesInventoryAvailabilityWh(pMsg, inMap);
        
        if (csaSatelliteAvltyWh == null || csaSatelliteAvltyWh.size() == 0) {
            return rs;
        }
        WH_OWNRTMsg whOwnrTmsg = getWhOwnrFindByKey(pMsg);
        if (whOwnrTmsg == null) {
            return rs;
        }
        for (int i = 0 ; i < rs.size() ; i++) {
            Map<String, Object> csaInvtyAvltyWh = rs.get(i);
            String svcMblWhOwnrTxt = (String) csaInvtyAvltyWh.get("SVC_MBL_WH_OWNR_TXT");
            if (hasValue(svcMblWhOwnrTxt) && svcMblWhOwnrTxt.equals(whOwnrTmsg.svcMblWhOwnrTxt.getValue())) {
                rs.addAll(i+1, csaSatelliteAvltyWh);
                csaInvtyAvltyWh.put("SVC_MBL_WH_OWNR_TXT", this.allSatellites);
                break;
            }
        }
        // END   2019/10/24 K.Fujimoto [QC#53410,ADD]
        return rs;
    }

    private List<Map<String, Object>> getCsaInventoryAvailabilityTech(NSZC079001PMsg pMsg, List<String> techTocCdList) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("mdseCd", pMsg.mdseCd.getValue());
        param.put("locStsCd", LOC_STS.DC_STOCK);
        param.put("tecLocTpCd", LOC_TP.TECHNICIAN);
        // START 2019/12/12 K.Kitachi [QC#55055, ADD]
        param.put("stkStsCd", STK_STS.GOOD);
        // END 2019/12/12 K.Kitachi [QC#55055, ADD]
        param.put("rtlWhCatgCdTechCarStk", RTL_WH_CATG.TECH_CAR_STOCK);
        param.put("rtlWhCatgCdCust", RTL_WH_CATG.CUSTOMER);
        param.put("tecRtlSwhCdList", this.tecRtlSwhCdList);
        param.put("techTocCdList", techTocCdList);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rs = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getCsaInventoryAvailabilityTech", param);
        return rs;
    }
    // mod end 2016/11/15 CSA Defect#15757
    // START 2019/10/24 K.Fujimoto [QC#53410,ADD]
    private List<Map<String, Object>> getSatellitesInventoryAvailabilityWh(NSZC079001PMsg pMsg, Map<String, Object> inMap) {
        String rtlWhCd = (String) inMap.get("RTL_WH_CD");
        if (!hasValue(rtlWhCd)) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("mdseCd", pMsg.mdseCd.getValue());
        param.put("locStsCd", LOC_STS.DC_STOCK);
        param.put("whLocTpCd", LOC_TP.WAREHOUSE);
        // START 2019/12/12 K.Kitachi [QC#55055, ADD]
        param.put("stkStsCd", STK_STS.GOOD);
        // END 2019/12/12 K.Kitachi [QC#55055, ADD]
        param.put("invtyOwnrCd", INVTY_OWNR.CSA);
        param.put("local", this.local);
        param.put("whRtlSwhCdList", this.whRtlSwhCdList);
        // START 2021/01/06 [QC#58207, MOD]
//        param.put("whOwnrCd", WH_OWNR.CHOICE);
//        param.put("rtlWhCd", rtlWhCd);
        param.put("rtlWhCd", pMsg.reqTechCd.getValue());
        // END 2021/01/06 [QC#58207, MOD]
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rs = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSatellitesInventoryAvailabilityWh", param);
        return rs;
    }
    
    private WH_OWNRTMsg getWhOwnrFindByKey(NSZC079001PMsg pMsg) {
        WH_OWNRTMsg inMsg = new WH_OWNRTMsg();
        setValue(inMsg.glblCmpyCd,  pMsg.glblCmpyCd.getValue());
        setValue(inMsg.whOwnrCd,  WH_OWNR.CHOICE);
        WH_OWNRTMsg rs = (WH_OWNRTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        return rs;
    }
    // END   2019/10/24 K.Fujimoto [QC#53410,ADD]

    private List<String> addConstData(S21ApiMessageMap msgMap, String srcKey, String glblCmpyCd) {
        String constValue = ZYPCodeDataUtil.getVarCharConstValue(srcKey, glblCmpyCd);
        if (!hasValue(constValue)) {
            msgMap.addXxMsgId(NSZM0102E);
            return null;
        }
        String[] arg = constValue.split(",");
        List<String> list = new ArrayList<String>(Arrays.asList(arg));
        return list;
    }

    private NLZC300001PMsg callInventoryReferenceAPI(NSZC079001PMsg pMsg) {
        NLZC300001PMsg apiPMsg = new NLZC300001PMsg();
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(apiPMsg.procDt, pMsg.slsDt);
        setValue(apiPMsg.xxRqstFlg_01, FLG_OFF_N);
        setValue(apiPMsg.xxRqstFlg_02, FLG_ON_Y);
        setValue(apiPMsg.xxRqstFlg_03, FLG_ON_Y);
        setValue(apiPMsg.xxRqstFlg_04, FLG_ON_Y);
        // add start 2016/04/11 CSA Defect#2603
        setValue(apiPMsg.xxRqstFlg_WH, FLG_ON_Y);
        setValue(apiPMsg.xxRqstFlg_TC, FLG_ON_Y);
        // add end 2016/04/11 CSA Defect#2603
        setValue(apiPMsg.xxDetailList.no(0).mdseCd, pMsg.mdseCd);
        apiPMsg.xxDetailList.setValidCount(1);
        NLZC300001 api = new NLZC300001();
        api.execute(apiPMsg, this.onBatchType);
        return apiPMsg;
    }

    private void setOutputParamByParts(NSZC079001PMsg pMsg, List<Map<String, Object>> inArray) {
        int i = 0;
        for (Map<String, Object> rs : inArray) {
            setValue(pMsg.mdseList.no(i).mdseCd, (String) rs.get("MDSE_CD"));
            setValue(pMsg.mdseList.no(i).mdseNm, (String) rs.get("MDSE_NM"));
            setValue(pMsg.mdseList.no(i).siteSrvyReqFlg, (String) rs.get("SITE_SRVY_REQ_FLG"));
            i++;
        }
        pMsg.mdseList.setValidCount(i);
    }

    // mod start 2016/11/15 CSA Defect#15757 
    private void setOutputParamByCsaWh(NSZC079001PMsg pMsg, List<Map<String, Object>> resultList) {
        String visFlg = this.dsCondConstList01.dsCondConstValTxt_04.getValue();

        int i = pMsg.whList.getValidCount();
        BigDecimal avalQty;
        for (Map<String, Object> map : resultList) {
            avalQty = (BigDecimal) map.get("INVTY_AVAL_QTY");
            if (!hasValue(avalQty)) {
                avalQty = BigDecimal.ZERO;
            }

            if (FLG_OFF_N.equals(visFlg) && BigDecimal.ZERO.compareTo(avalQty) >= 0) {
                continue;
            }

            // add start 2016/11/15 CSA QC#15952
            if (i >= this.tooManyCnt.intValue()) {
                this.tooManyFlg = true;
                break;
            }
            // add end 2016/11/15 CSA QC#15952

            setValue(pMsg.whList.no(i).whNm, (String) map.get("SVC_MBL_WH_OWNR_TXT"));
            setValue(pMsg.whList.no(i).whCd, (String) map.get("RTL_WH_CD"));
            setValue(pMsg.whList.no(i).avalQty, avalQty);

            setValue(pMsg.whList.no(i).xxWhOwnrTxt, this.dsCondConstList01.dsCondConstValTxt_01);
            setValue(pMsg.whList.no(i).xxInvtySrchActTxt, this.dsCondConstList01.dsCondConstValTxt_02);
            setValue(pMsg.whList.no(i).xxInvtySrchGrpTxt, this.dsCondConstList01.dsCondConstValTxt_03);

            if (FLG_ON_Y.equals(visFlg) && BigDecimal.ZERO.compareTo(avalQty) >= 0) {
                setValue(pMsg.whList.no(i).xxInvtySrchActTxt, this.dsCondConstList01.dsCondConstValTxt_05);
            }
            // add start 2016/12/015 CSA QC#16299
            setValue(pMsg.whList.no(i).xxCustStkFlg, this.dsCondConstList01.dsCondConstValTxt_06);
            // add end 2016/12/015 CSA QC#16299
            i++;
        }

        if (FLG_ON_Y.equals(visFlg) && resultList.size() == 0) {
            // mod start 2016/11/15 CSA QC#15952
            if (i >= this.tooManyCnt.intValue()) {
                this.tooManyFlg = true;
            } else {
                setValue(pMsg.whList.no(i).whNm, this.dsCondConstList01.dsCondConstValTxt_01);
                setValue(pMsg.whList.no(i).avalQty, BigDecimal.ZERO);
                setValue(pMsg.whList.no(i).xxWhOwnrTxt, this.dsCondConstList01.dsCondConstValTxt_01);
                setValue(pMsg.whList.no(i).xxInvtySrchActTxt, this.dsCondConstList01.dsCondConstValTxt_05);
                setValue(pMsg.whList.no(i).xxInvtySrchGrpTxt, this.dsCondConstList01.dsCondConstValTxt_03);
                // add start 2016/12/015 CSA QC#16299
                setValue(pMsg.whList.no(i).xxCustStkFlg, this.dsCondConstList01.dsCondConstValTxt_06);
                // add end 2016/12/015 CSA QC#16299
                i++;
            }
            // mod end 2016/11/15 CSA QC#15952
        }
        pMsg.whList.setValidCount(i);
    }

    private void setOutputParamByCsaMyWh(NSZC079001PMsg pMsg, List<Map<String, Object>> resultList) {
        // Tech Car Stock
        setOutputParamByCsaMyWhForTechCarStk(pMsg, resultList);
        // Customer
        setOutputParamByCsaMyWhForCust(pMsg, resultList);
    }

    private void setOutputParamByCsaMyWhForTechCarStk(NSZC079001PMsg pMsg, List<Map<String, Object>> resultList) {
        String visFlg = this.dsCondConstList04.dsCondConstValTxt_04.getValue();

        int i = pMsg.whList.getValidCount();
        BigDecimal avalQty;
        String rtlWhCatgCd;
        boolean procFlg = false;
        for (Map<String, Object> map : resultList) {
            rtlWhCatgCd = (String) map.get("RTL_WH_CATG_CD");
            if (!hasValue(rtlWhCatgCd) || !RTL_WH_CATG.TECH_CAR_STOCK.equals(rtlWhCatgCd)) {
                continue;
            }
            avalQty = (BigDecimal) map.get("INVTY_AVAL_QTY");
            if (!hasValue(avalQty)) {
                avalQty = BigDecimal.ZERO;
            }
            if (FLG_OFF_N.equals(visFlg) && BigDecimal.ZERO.compareTo(avalQty) >= 0) {
                continue;
            }

            // add start 2016/11/15 CSA QC#15952
            if (i >= this.tooManyCnt.intValue()) {
                this.tooManyFlg = true;
                break;
            }
            // add end 2016/11/15 CSA QC#15952

            setValue(pMsg.whList.no(i).whNm, (String) map.get("SVC_MBL_WH_OWNR_TXT"));
            setValue(pMsg.whList.no(i).whCd, (String) map.get("RTL_WH_CD"));
            setValue(pMsg.whList.no(i).avalQty, avalQty);

            setValue(pMsg.whList.no(i).xxWhOwnrTxt, (String) map.get("TECH_TOC_CD"));
            setValue(pMsg.whList.no(i).xxInvtySrchActTxt, this.dsCondConstList04.dsCondConstValTxt_02);
            setValue(pMsg.whList.no(i).xxInvtySrchGrpTxt, this.dsCondConstList04.dsCondConstValTxt_03);

            if (FLG_ON_Y.equals(visFlg) && BigDecimal.ZERO.compareTo(avalQty) >= 0) {
                setValue(pMsg.whList.no(i).xxInvtySrchActTxt, this.dsCondConstList04.dsCondConstValTxt_05);
            }
            // add start 2016/12/015 CSA QC#16299
            setValue(pMsg.whList.no(i).xxCustStkFlg, this.dsCondConstList04.dsCondConstValTxt_06);
            // add end 2016/12/015 CSA QC#16299
            i++;
            procFlg = true;
        }

        if (FLG_ON_Y.equals(visFlg) && !procFlg) {
            Map<String, Object> whInfo = getTechCarStkWhInfo(pMsg);
            if (whInfo == null) {
                return;
            }
            // mod start 2016/11/15 CSA QC#15952
            if (i >= this.tooManyCnt.intValue()) {
                this.tooManyFlg = true;
            } else {
                setValue(pMsg.whList.no(i).whNm, (String) whInfo.get("RTL_WH_NM"));
                setValue(pMsg.whList.no(i).whCd, (String) whInfo.get("RTL_WH_CD"));
                setValue(pMsg.whList.no(i).avalQty, BigDecimal.ZERO);
                setValue(pMsg.whList.no(i).xxWhOwnrTxt, pMsg.reqTechCd);
                setValue(pMsg.whList.no(i).xxInvtySrchActTxt, this.dsCondConstList04.dsCondConstValTxt_05);
                setValue(pMsg.whList.no(i).xxInvtySrchGrpTxt, this.dsCondConstList04.dsCondConstValTxt_03);
                // add start 2016/12/015 CSA QC#16299
                setValue(pMsg.whList.no(i).xxCustStkFlg, this.dsCondConstList04.dsCondConstValTxt_06);
                // add end 2016/12/015 CSA QC#16299
                i++;
            }
            // mod end 2016/11/15 CSA QC#15952
        }
        pMsg.whList.setValidCount(i);
    }

    private void setOutputParamByCsaMyWhForCust(NSZC079001PMsg pMsg, List<Map<String, Object>> resultList) {
        String visFlg = this.dsCondConstList05.dsCondConstValTxt_04.getValue();

        int i = pMsg.whList.getValidCount();
        BigDecimal avalQty;
        String rtlWhCatgCd;
        boolean procFlg = false;
        for (Map<String, Object> map : resultList) {
            rtlWhCatgCd = (String) map.get("RTL_WH_CATG_CD");
            if (!hasValue(rtlWhCatgCd) || !RTL_WH_CATG.CUSTOMER.equals(rtlWhCatgCd)) {
                continue;
            }
            avalQty = (BigDecimal) map.get("INVTY_AVAL_QTY");
            if (!hasValue(avalQty)) {
                avalQty = BigDecimal.ZERO;
            }
            if (FLG_OFF_N.equals(visFlg) && BigDecimal.ZERO.compareTo(avalQty) >= 0) {
                continue;
            }

            // add start 2016/11/15 CSA QC#15952
            if (i >= this.tooManyCnt.intValue()) {
                this.tooManyFlg = true;
                break;
            }
            // add end 2016/11/15 CSA QC#15952

            setValue(pMsg.whList.no(i).whNm, (String) map.get("SVC_MBL_WH_OWNR_TXT"));
            setValue(pMsg.whList.no(i).whCd, (String) map.get("RTL_WH_CD"));
            setValue(pMsg.whList.no(i).avalQty, avalQty);

            setValue(pMsg.whList.no(i).xxWhOwnrTxt, (String) map.get("TECH_TOC_CD"));
            setValue(pMsg.whList.no(i).xxInvtySrchActTxt, this.dsCondConstList05.dsCondConstValTxt_02);
            setValue(pMsg.whList.no(i).xxInvtySrchGrpTxt, this.dsCondConstList05.dsCondConstValTxt_03);

            if (FLG_ON_Y.equals(visFlg) && BigDecimal.ZERO.compareTo(avalQty) >= 0) {
                setValue(pMsg.whList.no(i).xxInvtySrchActTxt, this.dsCondConstList05.dsCondConstValTxt_05);
            }
            // add start 2016/12/015 CSA QC#16299
            setValue(pMsg.whList.no(i).xxCustStkFlg, this.dsCondConstList05.dsCondConstValTxt_06);
            // add end 2016/12/015 CSA QC#16299
            i++;
            procFlg = true;
        }

        if (FLG_ON_Y.equals(visFlg) && !procFlg) {
            Map<String, Object> whInfo = getCustWhInfo(pMsg);
            if (whInfo == null) {
                return;
            }
            // mod start 2016/11/15 CSA QC#15952
            if (i >= this.tooManyCnt.intValue()) {
                this.tooManyFlg = true;
            } else {
                setValue(pMsg.whList.no(i).whNm, (String) whInfo.get("RTL_WH_NM"));
                setValue(pMsg.whList.no(i).whCd, (String) whInfo.get("RTL_WH_CD"));
                setValue(pMsg.whList.no(i).avalQty, BigDecimal.ZERO);
                setValue(pMsg.whList.no(i).xxWhOwnrTxt, pMsg.reqTechCd);
                setValue(pMsg.whList.no(i).xxInvtySrchActTxt, this.dsCondConstList05.dsCondConstValTxt_05);
                setValue(pMsg.whList.no(i).xxInvtySrchGrpTxt, this.dsCondConstList05.dsCondConstValTxt_03);
                // add start 2016/12/015 CSA QC#16299
                setValue(pMsg.whList.no(i).xxCustStkFlg, this.dsCondConstList05.dsCondConstValTxt_06);
                // add end 2016/12/015 CSA QC#16299
                i++;
            }
            // mod end 2016/11/15 CSA QC#15952
        }
        pMsg.whList.setValidCount(i);
    }

    private void setOutputParamByCsaTeam(NSZC079001PMsg pMsg, List<Map<String, Object>> resultList, List<String> techTocCdList) {
        String visFlg = this.dsCondConstList06.dsCondConstValTxt_04.getValue();

        int i = 0;
        BigDecimal avalQty;
        String rsTechTocCd;
        boolean procFlg;
        for (String techTocCd : techTocCdList) {
            i = pMsg.whList.getValidCount();
            procFlg = false;
            for (Map<String, Object> map : resultList) {
                rsTechTocCd = (String) map.get("TECH_TOC_CD");
                if (!techTocCd.equals(rsTechTocCd)) {
                    continue;
                }
                avalQty = (BigDecimal) map.get("INVTY_AVAL_QTY");
                if (!hasValue(avalQty)) {
                    avalQty = BigDecimal.ZERO;
                }
                if (FLG_OFF_N.equals(visFlg) && BigDecimal.ZERO.compareTo(avalQty) >= 0) {
                    continue;
                }

                // add start 2016/11/15 CSA QC#15952
                if (i >= this.tooManyCnt.intValue()) {
                    this.tooManyFlg = true;
                    break;
                }
                // add end 2016/11/15 CSA QC#15952

                setValue(pMsg.whList.no(i).whNm, (String) map.get("SVC_MBL_WH_OWNR_TXT"));
                setValue(pMsg.whList.no(i).whCd, (String) map.get("RTL_WH_CD"));
                setValue(pMsg.whList.no(i).avalQty, avalQty);

                setValue(pMsg.whList.no(i).xxWhOwnrTxt, (String) map.get("TECH_TOC_CD"));
                setValue(pMsg.whList.no(i).xxInvtySrchActTxt, this.dsCondConstList06.dsCondConstValTxt_02);
                setValue(pMsg.whList.no(i).xxInvtySrchGrpTxt, this.dsCondConstList06.dsCondConstValTxt_03);

                if (FLG_ON_Y.equals(visFlg) && BigDecimal.ZERO.compareTo(avalQty) >= 0) {
                    setValue(pMsg.whList.no(i).xxInvtySrchActTxt, this.dsCondConstList06.dsCondConstValTxt_05);
                }
                // add start 2016/12/015 CSA QC#16299
                setValue(pMsg.whList.no(i).xxCustStkFlg, this.dsCondConstList06.dsCondConstValTxt_06);
                // add end 2016/12/015 CSA QC#16299
                // START 2019/11/21 K.Kitachi [QC#54768, ADD]
                if (RTL_WH_CATG.CUSTOMER.equals((String) map.get("RTL_WH_CATG_CD"))) {
                    setValue(pMsg.whList.no(i).xxCustStkFlg, FLG_ON_Y);
                }
                // END 2019/11/21 K.Kitachi [QC#54768, ADD]
                i++;
            }

            if (FLG_ON_Y.equals(visFlg) && !procFlg) {
                List<Map<String, Object>> whInfoList = getTeamWhInfo(pMsg.glblCmpyCd.getValue(), techTocCd);
                for (Map<String, Object> map : whInfoList) {
                    // add start 2016/11/15 CSA QC#15952
                    if (i >= this.tooManyCnt.intValue()) {
                        this.tooManyFlg = true;
                        break;
                    }
                    // add end 2016/11/15 CSA QC#15952

                    setValue(pMsg.whList.no(i).whNm, (String) map.get("RTL_WH_NM"));
                    setValue(pMsg.whList.no(i).whCd, (String) map.get("RTL_WH_CD"));
                    setValue(pMsg.whList.no(i).avalQty, BigDecimal.ZERO);
                    setValue(pMsg.whList.no(i).xxWhOwnrTxt, techTocCd);
                    setValue(pMsg.whList.no(i).xxInvtySrchActTxt, this.dsCondConstList06.dsCondConstValTxt_05);
                    setValue(pMsg.whList.no(i).xxInvtySrchGrpTxt, this.dsCondConstList06.dsCondConstValTxt_03);
                    // add start 2016/12/015 CSA QC#16299
                    setValue(pMsg.whList.no(i).xxCustStkFlg, this.dsCondConstList06.dsCondConstValTxt_06);
                    // add end 2016/12/015 CSA QC#16299
                    // START 2019/11/21 K.Kitachi [QC#54768, ADD]
                    if (RTL_WH_CATG.CUSTOMER.equals((String) map.get("RTL_WH_CATG_CD"))) {
                        setValue(pMsg.whList.no(i).xxCustStkFlg, FLG_ON_Y);
                    }
                    // END 2019/11/21 K.Kitachi [QC#54768, ADD]
                    i++;
                }
            }
            pMsg.whList.setValidCount(i);
        }
    }

    private void setOutputParamByCusaCvi(NSZC079001PMsg pMsg, List<Map<String, Object>> apiResultList) {
        String cusaVisFlg = this.dsCondConstList02.dsCondConstValTxt_04.getValue();
        String cviVisFlg = this.dsCondConstList03.dsCondConstValTxt_04.getValue();
        int i = pMsg.whList.getValidCount();
        String xxAvalOrdFlg;
        BigDecimal avalQty;
        boolean cusaProcFlg = false;
        boolean cviProcFlg = false;
        for (Map<String, Object> map : apiResultList) {
            if (this.whOwnrCusa.equals((String) map.get("whNm"))) {
                // CUSA
                xxAvalOrdFlg = (String) map.get("xxAvalOrdFlg");
                if (FLG_OFF_N.equals(cusaVisFlg) && FLG_OFF_N.equals(xxAvalOrdFlg)) {
                    continue;
                }

                // add start 2016/11/15 CSA QC#15952
                if (i >= this.tooManyCnt.intValue()) {
                    this.tooManyFlg = true;
                    break;
                }
                // add end 2016/11/15 CSA QC#15952

                setValue(pMsg.whList.no(i).whNm, (String) map.get("whNm"));
                setValue(pMsg.whList.no(i).xxAvalOrdFlg, xxAvalOrdFlg);
                setValue(pMsg.whList.no(i).xxWhOwnrTxt, this.dsCondConstList02.dsCondConstValTxt_01);
                setValue(pMsg.whList.no(i).xxInvtySrchActTxt, this.dsCondConstList02.dsCondConstValTxt_02);
                setValue(pMsg.whList.no(i).xxInvtySrchGrpTxt, this.dsCondConstList02.dsCondConstValTxt_03);

                if (FLG_ON_Y.equals(cusaVisFlg) && FLG_OFF_N.equals(xxAvalOrdFlg)) {
                    setValue(pMsg.whList.no(i).xxInvtySrchActTxt, this.dsCondConstList02.dsCondConstValTxt_05);
                }
                // add start 2016/12/015 CSA QC#16299
                setValue(pMsg.whList.no(i).xxCustStkFlg, this.dsCondConstList02.dsCondConstValTxt_06);
                // add end 2016/12/015 CSA QC#16299
                i++;
                cusaProcFlg = true;
            } else if (this.whOwnrCvi.equals((String) map.get("whNm"))) {
                // CVI
                avalQty = (BigDecimal) map.get("avalQty");
                if (!hasValue(avalQty)) {
                    avalQty = BigDecimal.ZERO;
                }

                if (FLG_OFF_N.equals(cviVisFlg) && BigDecimal.ZERO.compareTo(avalQty) >= 0) {
                    continue;
                }

                // add start 2016/11/15 CSA QC#15952
                if (i >= this.tooManyCnt.intValue()) {
                    this.tooManyFlg = true;
                    break;
                }
                // add end 2016/11/15 CSA QC#15952

                setValue(pMsg.whList.no(i).whNm, (String) map.get("whNm"));
                setValue(pMsg.whList.no(i).avalQty, avalQty);
                setValue(pMsg.whList.no(i).xxWhOwnrTxt, this.dsCondConstList03.dsCondConstValTxt_01);
                setValue(pMsg.whList.no(i).xxInvtySrchActTxt, this.dsCondConstList03.dsCondConstValTxt_02);
                setValue(pMsg.whList.no(i).xxInvtySrchGrpTxt, this.dsCondConstList03.dsCondConstValTxt_03);

                if (FLG_ON_Y.equals(cviVisFlg) && BigDecimal.ZERO.compareTo(avalQty) >= 0) {
                    setValue(pMsg.whList.no(i).xxInvtySrchActTxt, this.dsCondConstList03.dsCondConstValTxt_05);
                }
                // add start 2016/12/015 CSA QC#16299
                setValue(pMsg.whList.no(i).xxCustStkFlg, this.dsCondConstList03.dsCondConstValTxt_06);
                // add end 2016/12/015 CSA QC#16299
                i++;
                cviProcFlg = true;
            }
        }

        if (FLG_ON_Y.equals(cusaVisFlg) && !cusaProcFlg) {
            // CUSA
            // mod start 2016/11/15 CSA QC#15952
            if (i >= this.tooManyCnt.intValue()) {
                this.tooManyFlg = true;
            } else {
                setValue(pMsg.whList.no(i).whNm, this.whOwnrCusa);
                setValue(pMsg.whList.no(i).xxAvalOrdFlg, FLG_OFF_N);
                setValue(pMsg.whList.no(i).xxWhOwnrTxt, this.dsCondConstList02.dsCondConstValTxt_01);
                setValue(pMsg.whList.no(i).xxInvtySrchActTxt, this.dsCondConstList02.dsCondConstValTxt_05);
                setValue(pMsg.whList.no(i).xxInvtySrchGrpTxt, this.dsCondConstList02.dsCondConstValTxt_03);
                // add start 2016/12/015 CSA QC#16299
                setValue(pMsg.whList.no(i).xxCustStkFlg, this.dsCondConstList02.dsCondConstValTxt_06);
                // add end 2016/12/015 CSA QC#16299
                i++;
            }
            // mod end 2016/11/15 CSA QC#15952
        }

        if (FLG_ON_Y.equals(cviVisFlg) && !cviProcFlg) {
            // CVI
            // mod start 2016/11/15 CSA QC#15952
            if (i >= this.tooManyCnt.intValue()) {
                this.tooManyFlg = true;
            } else {
                setValue(pMsg.whList.no(i).whNm, this.whOwnrCvi);
                setValue(pMsg.whList.no(i).avalQty, BigDecimal.ZERO);
                setValue(pMsg.whList.no(i).xxWhOwnrTxt, this.dsCondConstList03.dsCondConstValTxt_01);
                setValue(pMsg.whList.no(i).xxInvtySrchActTxt, this.dsCondConstList03.dsCondConstValTxt_05);
                setValue(pMsg.whList.no(i).xxInvtySrchGrpTxt, this.dsCondConstList03.dsCondConstValTxt_03);
                // add start 2016/12/015 CSA QC#16299
                setValue(pMsg.whList.no(i).xxCustStkFlg, this.dsCondConstList03.dsCondConstValTxt_06);
                // add end 2016/12/015 CSA QC#16299
                i++;
            }
            // mod end 2016/11/15 CSA QC#15952
        }
        pMsg.whList.setValidCount(i);
    }
    // mod end 2016/11/15 CSA Defect#15757

    private void setOutputParamByIvtRefApi(S21ApiMessageMap msgMap, NSZC079001PMsg pMsg, NLZC300001PMsg apiMsg) {
        if (apiMsg.xxMsgIdList.getValidCount() != 0) {
            for (int i = 0; i < apiMsg.xxMsgIdList.getValidCount(); i++) {
                msgMap.addXxMsgId(apiMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
        }
        String constInvtyLocCd = ZYPCodeDataUtil.getVarCharConstValue(CVI_LOCATION, (String) pMsg.glblCmpyCd.getValue());
        if (!hasValue(constInvtyLocCd)) {
            msgMap.addXxMsgId(NSZM0102E);
            return;
        }

        // mod start 2016/11/15 CSA Defect#15757
        List<Map<String, Object>> apiResultList = new ArrayList<Map<String, Object>>();
        Map<String, Object> apiResult;
        if (apiMsg.xxDetailList.getValidCount() != 0) {
            for (int k = 0; k < apiMsg.xxDetailList.getValidCount(); k++) {
                // CVI
                if (constInvtyLocCd.equals(apiMsg.xxDetailList.no(k).invtyLocCd.getValue())) {
                    String existsNoStr = checkExistCVIOrCusa(this.whOwnrCvi, apiResultList);
                    if (!hasValue(existsNoStr)) {
                        apiResult = new HashMap<String, Object>();
                        apiResult.put("whNm", this.whOwnrCvi);
                        apiResult.put("avalQty", apiMsg.xxDetailList.no(k).xxAvalQty.getValue());
                        apiResultList.add(apiResult);
                    } else {
                        apiResult = apiResultList.get(Integer.valueOf(existsNoStr).intValue());
                        BigDecimal avalQty = (BigDecimal) apiResult.get("avalQty");
                        avalQty = avalQty.add(apiMsg.xxDetailList.no(k).xxAvalQty.getValue());
                        apiResult.put("avalQty", avalQty);
                    }

                // CUSA
                } else {
                    String existsNoStr = checkExistCVIOrCusa(this.whOwnrCusa, apiResultList);
                    if (!hasValue(existsNoStr)) {
                        apiResult = new HashMap<String, Object>();
                        apiResult.put("whNm", this.whOwnrCusa);
                        if (!hasValue(apiMsg.xxDetailList.no(k).xxAvalOrdFlg)) {
                            apiResult.put("xxAvalOrdFlg", FLG_OFF_N);
                        } else {
                            apiResult.put("xxAvalOrdFlg", apiMsg.xxDetailList.no(k).xxAvalOrdFlg.getValue());
                        }
                        apiResultList.add(apiResult);
                    } else {
                        apiResult = apiResultList.get(Integer.valueOf(existsNoStr).intValue());
                        String xxAvalOrdFlg = apiMsg.xxDetailList.no(k).xxAvalOrdFlg.getValue();
                        if (hasValue(xxAvalOrdFlg) && !FLG_ON_Y.equals((String) apiResult.get("xxAvalOrdFlg"))) {
                            apiResult.put("xxAvalOrdFlg", xxAvalOrdFlg);
                        }
                    }
                }
            }
        }
        // Set CUSA & CVI Output Parameter
        setOutputParamByCusaCvi(pMsg, apiResultList);

        Map<String, Object> trnsfToTech = getTrnsfToTech(pMsg);
        if (trnsfToTech != null) {
            setValue(pMsg.xxTrnsfToTechCd, (String) trnsfToTech.get("RTL_WH_CD"));
            setValue(pMsg.xxTrnsfToTechNm, (String) trnsfToTech.get("RTL_WH_NM"));
        }
        // mod end 2016/11/15 CSA Defect#15757
    }

    // mod start 2016/11/15 CSA Defect#15757
    private String checkExistCVIOrCusa(String cviOrCusa, List<Map<String, Object>> apiResultList) {
        String whNm;
        int i = 0;
        for (Map<String, Object> map : apiResultList) {
            whNm = (String) map.get("whNm");
            if (cviOrCusa.equals(whNm)) {
                return String.valueOf(i);
            }
            i++;
        }
        return null;
    }

    private Map<String, Object> getTrnsfToTech(NSZC079001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("reqTechCd", pMsg.reqTechCd.getValue());
        param.put("rtlWhCatgCd", RTL_WH_CATG.TECH_CAR_STOCK);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getTrnsfToTech", param);
    }

    private DS_COND_CONSTTMsg getDsCondConst(String glblCmpyCd, String grpId) {
        DS_COND_CONSTTMsg inMsg = new DS_COND_CONSTTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsCondConstGrpId01", grpId);
        DS_COND_CONSTTMsgArray resultList = (DS_COND_CONSTTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (resultList.getValidCount() == 0) {
            return null;
        }
        return resultList.no(0);
    }

    private Map<String, Object> getTechCarStkWhInfo(NSZC079001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("reqTechCd", pMsg.reqTechCd.getValue());
        param.put("rtlWhCatgCdTechCarStk", RTL_WH_CATG.TECH_CAR_STOCK);
        param.put("tecRtlSwhCdList", this.tecRtlSwhCdList);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getMyWhInfo", param);
    }

    private Map<String, Object> getCustWhInfo(NSZC079001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("reqTechCd", pMsg.reqTechCd.getValue());
        param.put("rtlWhCatgCdCust", RTL_WH_CATG.CUSTOMER);
        param.put("tecRtlSwhCdList", this.tecRtlSwhCdList);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getMyWhInfo", param);
    }

    private List<Map<String, Object>> getTeamWhInfo(String glblCmpyCd, String reqTechCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("reqTechCd", reqTechCd);
        param.put("tecRtlSwhCdList", this.tecRtlSwhCdList);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getMyWhInfo", param);
    }
    // mod end 2016/11/15 CSA Defect#15757

    // START 2023/09/06 N.Takatsu [QC#61661, ADD]
    private List<Map<String, Object>> getCsaInventoryAvailabilityWhSort(NSZC079001PMsg pMsg, List<Map<String, Object>> getCsaInventoryAvailabilityWhList, List<Map<String, Object>> getCsaInventoryAvailabilityAllPartsCentersList,
            List<Map<String, Object>> getCsaInventoryAvailabilityDefaultSourceWhList, String defSrcRtlWhCd) {
        List<Map<String, Object>> rs = new ArrayList<Map<String, Object>>();
        if (isDbs(pMsg, defSrcRtlWhCd)) {
            rs = sortDbsDefaultSourceWh(getCsaInventoryAvailabilityWhList, getCsaInventoryAvailabilityAllPartsCentersList, getCsaInventoryAvailabilityDefaultSourceWhList);
        } else {
            rs = sortNotDbsDefaultSourceWh(getCsaInventoryAvailabilityWhList, getCsaInventoryAvailabilityAllPartsCentersList, getCsaInventoryAvailabilityDefaultSourceWhList);
        }
        return rs;
    }

    private List<Map<String, Object>> sortDbsDefaultSourceWh(List<Map<String, Object>> getCsaInventoryAvailabilityWhList, List<Map<String, Object>> getCsaInventoryAvailabilityAllPartsCentersList,
            List<Map<String, Object>> getCsaInventoryAvailabilityDefaultSourceWhList) {
        List<Map<String, Object>> rs = new ArrayList<Map<String, Object>>();
        int csaWhCount = 0;

        if (getCsaInventoryAvailabilityWhList.size() > 0) {
            for (int i = 0; i < getCsaInventoryAvailabilityWhList.size(); i++) {
                Map<String, Object> getCsaInventoryAvailabilityWh = getCsaInventoryAvailabilityWhList.get(i);
                String svcMblWhOwnrTxt = (String) getCsaInventoryAvailabilityWh.get("SVC_MBL_WH_OWNR_TXT");
                if (hasValue(svcMblWhOwnrTxt) && this.local.equals(svcMblWhOwnrTxt)) {
                    rs.add(getCsaInventoryAvailabilityWh);
                    csaWhCount++;
                } else {
                    break;
                }
            }
        }

        if (getCsaInventoryAvailabilityAllPartsCentersList.size() > 0) {
            rs.addAll(getCsaInventoryAvailabilityAllPartsCentersList);
        }

        if (getCsaInventoryAvailabilityWhList.size() > 0) {
            for (int i = csaWhCount; i < getCsaInventoryAvailabilityWhList.size(); i++) {
                Map<String, Object> getCsaInventoryAvailabilityWh = getCsaInventoryAvailabilityWhList.get(i);
                rs.add(getCsaInventoryAvailabilityWh);
            }
        }
        return rs;
    }

    private List<Map<String, Object>> sortNotDbsDefaultSourceWh(List<Map<String, Object>> getCsaInventoryAvailabilityWhList, List<Map<String, Object>> getCsaInventoryAvailabilityAllPartsCentersList,
            List<Map<String, Object>> getCsaInventoryAvailabilityDefaultSourceWhList) {

        List<Map<String, Object>> rs = new ArrayList<Map<String, Object>>();
        if (getCsaInventoryAvailabilityDefaultSourceWhList.size() > 0) {
            rs.addAll(getCsaInventoryAvailabilityDefaultSourceWhList);
        }

        if (getCsaInventoryAvailabilityAllPartsCentersList.size() > 0) {
            rs.addAll(getCsaInventoryAvailabilityAllPartsCentersList);
        }

        if (getCsaInventoryAvailabilityWhList.size() > 0) {
            rs.addAll(getCsaInventoryAvailabilityWhList);
        }
        return rs;
    }

    private boolean isDbs(NSZC079001PMsg pMsg, String defSrcRtlWhCd) {
        if (!hasValue(defSrcRtlWhCd)) {
            return false;
        }
        RTL_WHTMsg tMsg = new RTL_WHTMsg();
        setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(tMsg.rtlWhCd, defSrcRtlWhCd);
        RTL_WHTMsg resTMsg = (RTL_WHTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (resTMsg == null) {
            return false;
        } else if (WH_OWNR.DBS.equals(resTMsg.whOwnrCd.getValue())) {
            return true;
        }
        return false;
    }

    private List<Map<String, Object>> getCsaInventoryAvailabilityAllPartsCenters(NSZC079001PMsg pMsg, Map<String, Object> inMap) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("mdseCd", pMsg.mdseCd.getValue());
        param.put("locStsCd", LOC_STS.DC_STOCK);
        param.put("whLocTpCd", LOC_TP.WAREHOUSE);
        param.put("stkStsCd", STK_STS.GOOD);
        param.put("whRtlSwhCdList", this.whRtlSwhCdList);
        param.put("rtlWhCatgCd", RTL_WH_CATG.PARTS_DEPO);
        param.put("allPartsCenters", this.allPartsCenters);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getCsaInventoryAvailabilityAllPartsCenters", param);
    }

    private List<Map<String, Object>> getCsaInventoryAvailabilityDefaultSourceWh(NSZC079001PMsg pMsg, Map<String, Object> inMap) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("mdseCd", pMsg.mdseCd.getValue());
        param.put("locStsCd", LOC_STS.DC_STOCK);
        param.put("whLocTpCd", LOC_TP.WAREHOUSE);
        param.put("stkStsCd", STK_STS.GOOD);
        param.put("whRtlSwhCdList", this.whRtlSwhCdList);
        String defSrcRtlSwhCd = (String) inMap.get("DEF_SRC_RTL_SWH_CD");
        if (hasValue(defSrcRtlSwhCd)) {
            param.put("defSrcRtlSwhCd", defSrcRtlSwhCd);
        } else {
           param.put("defSrcRtlSwhCd", BRANK);
        }

        String defSrcRtlWhCd = (String) inMap.get("DEF_SRC_RTL_WH_CD");
        if (hasValue(defSrcRtlWhCd)) {
            param.put("defSrcRtlWhCd", defSrcRtlWhCd);
        } else {
            param.put("defSrcRtlWhCd", BRANK);
        }
        param.put("whOwnr", WH_OWNR.DBS);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getCsaInventoryAvailabilityDefaultSourceWh", param);
    }
    // END   2023/09/06 N.Takatsu [QC#61661, ADD]
}
