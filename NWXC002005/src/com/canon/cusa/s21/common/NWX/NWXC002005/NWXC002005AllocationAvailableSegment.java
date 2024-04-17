/*
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC002005;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.math.BigDecimal.ONE;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDTMsg;
import business.db.DIST_STRU_SEGTMsg;
import business.db.DIST_STRU_SEGTMsgArray;
import business.db.MDSETMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.S21_ORGTMsg;
import business.db.S21_ORGTMsgArray;
import business.db.SOFT_ALLOC_AVAL_SEGTMsg;

import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DIST_PLN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DIST_SEG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DIST_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_SYS_TP;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;

/**
 * <pre>
 * Soft Allocation Available Segment Get Function
 *
 * Date          Company     Name           Create/Update   Defect No
 * ------------------ ----------------------------------------------------
 * 07/24/2009    Fujitsu     T.Takinoue     Create          N/A
 * 11/17/2009    Fujitsu     R.Watanabe     Update          N/A
 * 12/14/2009    Fujitsu     K.Tajima       Update          N/A (refactoring to get high-performance)
 * 01/06/2010    Fujitsu     K.Tajima       Update          N/A (debug and refactoring.)
 * 04/21/2010    Fujitsu     A.Suda    		Update          5879
 *</pre>
 */
public class NWXC002005AllocationAvailableSegment {

    /** SQLID */
    private static final String SQLID_SELECT_S21_ORG = "003";

    /** SQLID */
    private static final String SQLID_SELECT_DIST_STRU_SEG_SINGLE = "002";

    /** SQLID */
    private static final String SQLID_SELECT_DIST_STRU_SEG_TOC = "003";

    /** SQLID */
    private static final String SQLID_SELECT_DIST_STRU_SEG_ORG = "004";

    /** SQLID */
    private static final String SQLID_SELECT_DIST_STRU_SEG_CUST = "006";

    /** SQLID */
    private static final String SQLID_SELECT_DIST_STRU_SEG_ELEMENT = "007";

    /** layer No */
    private static final int LEAF_LAYER_NO_1 = 1;

    /** layer No */
    private static final int LEAF_LAYER_NO_2 = 2;

    /** layer No */
    private static final int LEAF_LAYER_NO_3 = 3;

    /** layer No */
    private static final int LEAF_LAYER_NO_4 = 4;

    /** layer No */
    private static final int LEAF_LAYER_NO_5 = 5;

    /** layer No */
    private static final int LEAF_LAYER_NO_6 = 6;

    /** layer No */
    private static final int LEAF_LAYER_NO_7 = 7;

    /** leaf layer */
    private static final int LEAF_LAYER_NO_8 = 8;

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient;

    private static enum ItemDigit {
        
        PRTY_NUM( new SOFT_ALLOC_AVAL_SEGTMsg(), "prtyNum" ),
        DIST_STRU_LEAF_LAYER_NUM( new DIST_STRU_SEGTMsg(), "distStruLeafLayerNum" ),
        ORD_TAKE_MDSE_CD( new ORD_TAKE_MDSETMsg(), "ordTakeMdseCd" );

        private final int digit;
        
        private ItemDigit( EZDTMsg tMsg, String key ) {
            digit = tMsg.getAttr(key).getDigit();
        }

        protected int getDigit() {
            return digit;
        }
    }
    
    /**
     * Soft Allocation Available Segment Get Function.
     * @param param NWXC002005AllocAvailSegParam
     * @return Soft Allocation Available Segment.
     */
    public NWXC002005AllocAvailSegDataArray get(NWXC002005AllocAvailSegParam param) {
        writeDebugLog("get [Start]");

        NWXC002005AllocAvailSegDataArray dataArray = new NWXC002005AllocAvailSegDataArray();

        // Check parameter
        if (!hasValue(param.getGlblCmpyCd())) {
            dataArray.setResultCode(NWXC002005AllocAvailSegDataArray.PARAM_ERROR);
            return dataArray;
        }
        if (!hasValue(param.getTrxSrcTpCd())) {
            dataArray.setResultCode(NWXC002005AllocAvailSegDataArray.PARAM_ERROR);
            return dataArray;
        }
        if (!hasValue(param.getMdseCd())) {
            dataArray.setResultCode(NWXC002005AllocAvailSegDataArray.PARAM_ERROR);
            return dataArray;
        }
        if (!hasValue(param.getBillToCustCd())) {
            dataArray.setResultCode(NWXC002005AllocAvailSegDataArray.PARAM_ERROR);
            return dataArray;
        }
        if (!hasValue(param.getSellToCustCd())) {
            dataArray.setResultCode(NWXC002005AllocAvailSegDataArray.PARAM_ERROR);
            return dataArray;
        }
        // 04/21/2010 Defect 5879
        if(!TRX_SRC_TP.WAREHOUSE_TRANSFER.equals(param.getTrxSrcTpCd())){
        	if(!TRX_SRC_TP.KITTING.equals(param.getTrxSrcTpCd())){
		        if (!hasValue(param.getTocCd())) {
		            dataArray.setResultCode(NWXC002005AllocAvailSegDataArray.PARAM_ERROR);
		            return dataArray;
		        }
        	}
        }
        if (!hasValue(param.getGlblCmpyCd())) {
            dataArray.setResultCode(NWXC002005AllocAvailSegDataArray.PARAM_ERROR);
            return dataArray;
        }

        dataArray.setResultCode(NWXC002005AllocAvailSegDataArray.NOT_FOUND);

        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        List<TargetDistStructure> distStructureRcdLst = getDistStructure(param);
        if (isEmpty(distStructureRcdLst)) {
            return dataArray;
        }

        String trxSrcTpCd = param.getTrxSrcTpCd();

        boolean result;
        if (TRX_SRC_TP.WHOLE_SALES.equals(trxSrcTpCd)) {
            result = getDistStruSegWSRetail(param, distStructureRcdLst);
        } else if (TRX_SRC_TP.RETAIL.equals(trxSrcTpCd)) {
            result = getDistStruSegWSRetail(param, distStructureRcdLst);
        } else if (TRX_SRC_TP.KITTING.equals(trxSrcTpCd)) {
            result = getDistStruSegKitting(param, distStructureRcdLst);
        } else if (TRX_SRC_TP.WAREHOUSE_TRANSFER.equals(trxSrcTpCd)) {
            result = getDistStruSegWHTransfer(param, distStructureRcdLst);
        } else {
            result = false;
        }
        if (!result) {
            return dataArray;
        }

        BigDecimal prtyNum = ONE;

        for ( TargetDistStructure distStructure : distStructureRcdLst ) {

            List<BigDecimal> distStruSegRcdLst = distStructure.getDistStruSegRcdLst();
            for( BigDecimal distStruSegPk : distStruSegRcdLst ) {
                
                NWXC002005AllocAvailSegData data = new NWXC002005AllocAvailSegData();
                dataArray.add(data);
                
                data.setDistPlnNum(distStructure.getDistPlnNum());
                data.setDistStruSegPk(distStruSegPk);

                String prtyNumStr = ZYPCommonFunc.leftPad(prtyNum.toString(), ItemDigit.PRTY_NUM.getDigit(), "0");
                data.setPrtyNum(prtyNumStr);
                
                prtyNum = prtyNum.add(ONE);
            }
        }
        
        dataArray.setResultCode(NWXC002005AllocAvailSegDataArray.NORMAL_RTN);
        
        writeDebugLog("get [End]");
        return dataArray;
    }

    @SuppressWarnings("unchecked")
    private List<TargetDistStructure> getDistStructure(NWXC002005AllocAvailSegParam param) {
        writeDebugLog("getDistStructure [Start]");

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", param.getGlblCmpyCd());
        queryParam.put("ordTakeMdseCd", ZYPCommonFunc.subByteString(param.getMdseCd(), 0, ItemDigit.ORD_TAKE_MDSE_CD.getDigit()));
        queryParam.put("mdseCd", param.getMdseCd());
        queryParam.put("invtyLocCd", param.getInvtyLocCd());
        queryParam.put("distPlnStsCdPlanning", DIST_PLN_STS.PLANNING);
        queryParam.put("distPlnStsCdSubmitted", DIST_PLN_STS.SUBMITTED);

        List<TargetDistStructure> distStructureRcdLst = (List<TargetDistStructure>) this.ssmBatchClient.queryObjectList("getDistStructure", queryParam);
        
        writeDebugLog("getDistStructure [End]");
        return distStructureRcdLst;
    }

    private boolean getDistStruSegWSRetail(NWXC002005AllocAvailSegParam param, List<TargetDistStructure> distStructureRcdLst) {

        for ( TargetDistStructure distStructure : distStructureRcdLst ) {

            boolean result;
            if (DIST_STRU_TP.ORIGINATED_FROM_CUSTOMER_STRUCTURE.equals(distStructure.getDistStruTpCd())) {
                result = searchCustDistStructure(param, distStructure);
            } else {
                result = searchOrgDistStructure(param, distStructure);
            }
            if (!result) {
                if (isEmpty(distStructure.getDistStruSegRcdLst())) {
                    result = getDistStruSegTop(param, distStructure);
                    if (!result) {
                        continue;
                    }
                }
            }
        }
        
        return true;
    }

    private boolean searchCustDistStructure(NWXC002005AllocAvailSegParam param, TargetDistStructure distStructure) {

        boolean result = getStartSegmentByCust(distStructure, LOC_ROLE_TP.SELL_TO, param.getSellToCustCd());
        if (!result) {
            result = getStartSegmentByCust(distStructure, LOC_ROLE_TP.BILL_TO, param.getBillToCustCd());
            if (!result) {
                result = getStartSegmentByCustStru(param, distStructure);
                if (!result) {
                    return false;
                }
            }
        }

        List<BigDecimal> distStruSegRcdLst = distStructure.getDistStruSegRcdLst();

        DIST_STRU_SEGTMsg distStruSegTMsg = distStructure.getStartDistSegment();

        int layer = Integer.parseInt(distStruSegTMsg.distStruLeafLayerNum.getValue());

        if (layer >= LEAF_LAYER_NO_8) {
            if (hasValue(distStruSegTMsg.eighthDistStruSegPk)) {
                distStruSegRcdLst.add(distStruSegTMsg.eighthDistStruSegPk.getValue());
            }
        }

        if (layer >= LEAF_LAYER_NO_7) {
            if (hasValue(distStruSegTMsg.svnthDistStruSegPk)) {
                distStruSegRcdLst.add(distStruSegTMsg.svnthDistStruSegPk.getValue());
            }
        }

        if (layer >= LEAF_LAYER_NO_6) {
            if (hasValue(distStruSegTMsg.sixthDistStruSegPk)) {
                distStruSegRcdLst.add(distStruSegTMsg.sixthDistStruSegPk.getValue());
            }
        }

        if (layer >= LEAF_LAYER_NO_5) {
            if (hasValue(distStruSegTMsg.fifthDistStruSegPk)) {
                distStruSegRcdLst.add(distStruSegTMsg.fifthDistStruSegPk.getValue());
            }
        }

        if (layer >= LEAF_LAYER_NO_4) {
            if (hasValue(distStruSegTMsg.frthDistStruSegPk)) {
                distStruSegRcdLst.add(distStruSegTMsg.frthDistStruSegPk.getValue());
            }
        }

        if (layer >= LEAF_LAYER_NO_3) {
            if (hasValue(distStruSegTMsg.thirdDistStruSegPk)) {
                distStruSegRcdLst.add(distStruSegTMsg.thirdDistStruSegPk.getValue());
            }
        }

        if (layer >= LEAF_LAYER_NO_2) {
            if (hasValue(distStruSegTMsg.scdDistStruSegPk)) {
                distStruSegRcdLst.add(distStruSegTMsg.scdDistStruSegPk.getValue());
            }
        }
        
        return true;

    }

    private boolean getStartSegmentByCust(TargetDistStructure distStructure, String locRoleTpCd, String custCd) {

        DIST_STRU_SEGTMsg reqDistStruSegTMsg = new DIST_STRU_SEGTMsg();
        reqDistStruSegTMsg.setSQLID(SQLID_SELECT_DIST_STRU_SEG_CUST);
        reqDistStruSegTMsg.setConditionValue("glblCmpyCd01", distStructure.getGlblCmpyCd());
        reqDistStruSegTMsg.setConditionValue("distStruPk01", distStructure.getDistStruPk());
        reqDistStruSegTMsg.setConditionValue("distSegTpCd01", DIST_SEG_TP.CUSTOMER);
        reqDistStruSegTMsg.setConditionValue("custCd01", custCd);
        reqDistStruSegTMsg.setConditionValue("locRoleTpCd01", locRoleTpCd);
        reqDistStruSegTMsg.setMaxCount(1);

        DIST_STRU_SEGTMsgArray resDistStruSegTMsgArray = (DIST_STRU_SEGTMsgArray) S21ApiTBLAccessor.findByCondition(reqDistStruSegTMsg);
        if (resDistStruSegTMsgArray.length() == 0) {
            return false;
        }
        
        distStructure.setStartDistSegment(resDistStruSegTMsgArray.no(0));
        return true;
    }

    private boolean getStartSegmentByCustStru(NWXC002005AllocAvailSegParam param1, TargetDistStructure distStructure) {

        boolean result = getCustStruMaintUnit(param1, distStructure);
        if (!result) {
            return false;
        }
        
        return true;
    }

    @SuppressWarnings("unchecked")
    private boolean getCustStruMaintUnit(NWXC002005AllocAvailSegParam param1, TargetDistStructure distStructure) {

        MDSETMsg mdseTMsgResult = NWXMdseTMsgThreadLocalCache.getInstance().get(param1.getGlblCmpyCd(), param1.getMdseCd());

        List<String> prodCtrlList = new ArrayList<String>();
        
        if (hasValue(mdseTMsgResult.zerothProdCtrlCd)) {
            prodCtrlList.add(mdseTMsgResult.zerothProdCtrlCd.getValue());
        }

        if (hasValue(mdseTMsgResult.firstProdCtrlCd)) {
            prodCtrlList.add(mdseTMsgResult.firstProdCtrlCd.getValue());
        }

        if (hasValue(mdseTMsgResult.scdProdCtrlCd)) {
            prodCtrlList.add(mdseTMsgResult.scdProdCtrlCd.getValue());
        }

        if (hasValue(mdseTMsgResult.thirdProdCtrlCd)) {
            prodCtrlList.add(mdseTMsgResult.thirdProdCtrlCd.getValue());
        }

        if (hasValue(mdseTMsgResult.frthProdCtrlCd)) {
            prodCtrlList.add(mdseTMsgResult.frthProdCtrlCd.getValue());
        }

        if (hasValue(mdseTMsgResult.fifthProdCtrlCd)) {
            prodCtrlList.add(mdseTMsgResult.fifthProdCtrlCd.getValue());
        }

        if (hasValue(mdseTMsgResult.sixthProdCtrlCd)) {
            prodCtrlList.add(mdseTMsgResult.sixthProdCtrlCd.getValue());
        }

        if (hasValue(mdseTMsgResult.svnthProdCtrlCd)) {
            prodCtrlList.add(mdseTMsgResult.svnthProdCtrlCd.getValue());
        }

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", param1.getGlblCmpyCd());
        queryParam.put("custStruTpCd", CUST_STRU_TP.BASE_CUSTOMER_STRUCTURE);
        queryParam.put("prodCtrlList", prodCtrlList);

        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getCustomerMaintenanceUnit", queryParam);

        if (result == null) {
            return false;
        }

        distStructure.setCustStruMaintUnitCd((String) result.get("CUST_STRU_MAINT_UNIT_CD"));
        distStructure.setSetLocRoleTpCd((String) result.get("SET_LOC_ROLE_TP_CD"));
        return true;
    }

    private static class QueryCustStructureByMaintUnit extends S21SsmBooleanResultSetHandlerSupport {

        private TargetDistStructure distStructure;

        public QueryCustStructureByMaintUnit(TargetDistStructure distStructure) {
            this.distStructure = distStructure;
        }

        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            if( rs.next() ) {
                do {
                    List<String> elmtCdRcdLst = new ArrayList<String>();
    
                    if (hasValue(rs.getString("FIFTH_CUST_STRU_ELMNT_CD"))) {
                        elmtCdRcdLst.add(rs.getString("FIFTH_CUST_STRU_ELMNT_CD"));
                    }
                    if (hasValue(rs.getString("FRTH_CUST_STRU_ELMNT_CD"))) {
                        elmtCdRcdLst.add(rs.getString("FRTH_CUST_STRU_ELMNT_CD"));
                    }
                    if (hasValue(rs.getString("THIRD_CUST_STRU_ELMNT_CD"))) {
                        elmtCdRcdLst.add(rs.getString("THIRD_CUST_STRU_ELMNT_CD"));
                    }
                    if (hasValue(rs.getString("SCD_CUST_STRU_ELMNT_CD"))) {
                        elmtCdRcdLst.add(rs.getString("SCD_CUST_STRU_ELMNT_CD"));
                    }
                    if (hasValue(rs.getString("FIRST_CUST_STRU_ELMNT_CD"))) {
                        elmtCdRcdLst.add(rs.getString("FIRST_CUST_STRU_ELMNT_CD"));
                    }
                    
                    for( String elmtCd : elmtCdRcdLst ) {
                        boolean result = getStartSegmentByCustElement(elmtCd);
                        if (result) {
                            return TRUE;
                        }
                    }
                } while( rs.next() );
            }
            
            return FALSE;
        }

        private boolean getStartSegmentByCustElement(String elementCd) {

            DIST_STRU_SEGTMsg reqDistStruSegTMsg = new DIST_STRU_SEGTMsg();
            reqDistStruSegTMsg.setSQLID(SQLID_SELECT_DIST_STRU_SEG_ELEMENT);
            reqDistStruSegTMsg.setConditionValue("glblCmpyCd01", distStructure.getGlblCmpyCd());
            reqDistStruSegTMsg.setConditionValue("distStruPk01", distStructure.getDistStruPk());
            reqDistStruSegTMsg.setConditionValue("distSegTpCd01", DIST_SEG_TP.CUSTOMER_STRUCTURE);
            reqDistStruSegTMsg.setConditionValue("custStruElmntCd01", elementCd);
            reqDistStruSegTMsg.setMaxCount(1);

            DIST_STRU_SEGTMsgArray resDistStruSegTMsgResult = (DIST_STRU_SEGTMsgArray) S21ApiTBLAccessor.findByCondition(reqDistStruSegTMsg);
            if (resDistStruSegTMsgResult.length() == 0) {
                return false;
            }
            
            distStructure.setStartDistSegment(resDistStruSegTMsgResult.no(0));
            return true;
        }
    }

    private boolean searchOrgDistStructure(NWXC002005AllocAvailSegParam param1, TargetDistStructure distStructure) {

        boolean result = getStartSegmentByToc(param1, distStructure);
        if (!result) {
            result = getStartSegmentByS21Org(param1, distStructure);
            if (!result) {
                return false;
            }
        }
        getStartSegmentBySellTo(param1, distStructure);

        ArrayList<BigDecimal> distStruSegRcdLst = distStructure.getDistStruSegRcdLst();

        DIST_STRU_SEGTMsg distStruSegTMsg = distStructure.getStartDistSegment();

        int layer = Integer.parseInt(distStruSegTMsg.distStruLeafLayerNum.getValue());

        if (layer >= LEAF_LAYER_NO_8) {
            if (hasValue(distStruSegTMsg.eighthDistStruSegPk)) {
                distStruSegRcdLst.add(distStruSegTMsg.eighthDistStruSegPk.getValue());
            }
        }

        if (layer >= LEAF_LAYER_NO_7) {
            if (hasValue(distStruSegTMsg.svnthDistStruSegPk)) {
                distStruSegRcdLst.add(distStruSegTMsg.svnthDistStruSegPk.getValue());
            }
        }

        if (layer >= LEAF_LAYER_NO_6) {
            if (hasValue(distStruSegTMsg.sixthDistStruSegPk)) {
                distStruSegRcdLst.add(distStruSegTMsg.sixthDistStruSegPk.getValue());
            }
        }

        if (layer >= LEAF_LAYER_NO_5) {
            if (hasValue(distStruSegTMsg.fifthDistStruSegPk)) {
                distStruSegRcdLst.add(distStruSegTMsg.fifthDistStruSegPk.getValue());
            }
        }

        if (layer >= LEAF_LAYER_NO_4) {
            if (hasValue(distStruSegTMsg.frthDistStruSegPk)) {
                distStruSegRcdLst.add(distStruSegTMsg.frthDistStruSegPk.getValue());
            }
        }

        if (layer >= LEAF_LAYER_NO_3) {
            if (hasValue(distStruSegTMsg.thirdDistStruSegPk)) {
                distStruSegRcdLst.add(distStruSegTMsg.thirdDistStruSegPk.getValue());
            }
        }

        if (layer >= LEAF_LAYER_NO_2) {
            if (hasValue(distStruSegTMsg.scdDistStruSegPk)) {
                distStruSegRcdLst.add(distStruSegTMsg.scdDistStruSegPk.getValue());
            }
        }
        return true;
    }

    private boolean getStartSegmentByToc(NWXC002005AllocAvailSegParam param1, TargetDistStructure distStructure) {

        DIST_STRU_SEGTMsg reqDistStruSegTMsg = new DIST_STRU_SEGTMsg();
        reqDistStruSegTMsg.setSQLID(SQLID_SELECT_DIST_STRU_SEG_TOC);
        reqDistStruSegTMsg.setConditionValue("glblCmpyCd01", distStructure.getGlblCmpyCd());
        reqDistStruSegTMsg.setConditionValue("distStruPk01", distStructure.getDistStruPk());
        reqDistStruSegTMsg.setConditionValue("distSegTpCd01", DIST_SEG_TP.TOC);
        reqDistStruSegTMsg.setConditionValue("tocCd01", param1.getTocCd());
        reqDistStruSegTMsg.setMaxCount(1);

        DIST_STRU_SEGTMsgArray resDistStruSegTMsgResult = (DIST_STRU_SEGTMsgArray) S21ApiTBLAccessor.findByCondition(reqDistStruSegTMsg);
        if (resDistStruSegTMsgResult.length() == 0) {
            return false;
        }
        
        distStructure.setStartDistSegment(resDistStruSegTMsgResult.no(0));
        return true;
    }

    private boolean getStartSegmentByS21Org(NWXC002005AllocAvailSegParam param1, TargetDistStructure distStructure) {

        S21_ORGTMsg reqS21OrgTMsg = new S21_ORGTMsg();
        reqS21OrgTMsg.setSQLID(SQLID_SELECT_S21_ORG);
        reqS21OrgTMsg.setConditionValue("glblCmpyCd01", distStructure.getGlblCmpyCd());
        reqS21OrgTMsg.setConditionValue("tocCd01", param1.getTocCd());
        reqS21OrgTMsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        S21_ORGTMsgArray resS21OrgTMsgArray = (S21_ORGTMsgArray) S21ApiTBLAccessor.findByCondition(reqS21OrgTMsg);
        if (resS21OrgTMsgArray.length() == 0) {
            return false;
        }

        for (int i = 0; i < resS21OrgTMsgArray.length(); i++) {

            List<String> orgCdRcdLst = new ArrayList<String>();

            S21_ORGTMsg resS21OrgTMsg = resS21OrgTMsgArray.no(i);
            
            if (hasValue(resS21OrgTMsg.eighthOrgCd)) {
                orgCdRcdLst.add(resS21OrgTMsg.eighthOrgCd.getValue());
            }
            if (hasValue(resS21OrgTMsg.svnthOrgCd)) {
                orgCdRcdLst.add(resS21OrgTMsg.svnthOrgCd.getValue());
            }
            if (hasValue(resS21OrgTMsg.sixthOrgCd)) {
                orgCdRcdLst.add(resS21OrgTMsg.sixthOrgCd.getValue());
            }
            if (hasValue(resS21OrgTMsg.fifthOrgCd)) {
                orgCdRcdLst.add(resS21OrgTMsg.fifthOrgCd.getValue());
            }
            if (hasValue(resS21OrgTMsg.frthOrgCd)) {
                orgCdRcdLst.add(resS21OrgTMsg.frthOrgCd.getValue());
            }
            if (hasValue(resS21OrgTMsg.thirdOrgCd)) {
                orgCdRcdLst.add(resS21OrgTMsg.thirdOrgCd.getValue());
            }
            if (hasValue(resS21OrgTMsg.scdOrgCd)) {
                orgCdRcdLst.add(resS21OrgTMsg.scdOrgCd.getValue());
            }
            if (hasValue(resS21OrgTMsg.firstOrgCd)) {
                orgCdRcdLst.add(resS21OrgTMsg.firstOrgCd.getValue());
            }

            for( String orgCd : orgCdRcdLst ) {
                boolean result = getStartSegmentByOrg(distStructure, orgCd);
                if (result) {
                    return true;
                }
            }
            
        }
        return false;
    }

    private boolean getStartSegmentByOrg(TargetDistStructure distStructure, String nodeOrgCd) {

        DIST_STRU_SEGTMsg reqDistStruSegTMsg = new DIST_STRU_SEGTMsg();
        reqDistStruSegTMsg.setSQLID(SQLID_SELECT_DIST_STRU_SEG_ORG);
        reqDistStruSegTMsg.setConditionValue("glblCmpyCd01", distStructure.getGlblCmpyCd());
        reqDistStruSegTMsg.setConditionValue("distStruPk01", distStructure.getDistStruPk());
        reqDistStruSegTMsg.setConditionValue("distSegTpCd01", DIST_SEG_TP.ORGANIZATION_STRUCTURE);
        reqDistStruSegTMsg.setConditionValue("orgCd01", nodeOrgCd);
        reqDistStruSegTMsg.setMaxCount(1);

        DIST_STRU_SEGTMsgArray resDistStruSegTMsgArray = (DIST_STRU_SEGTMsgArray) S21ApiTBLAccessor.findByCondition(reqDistStruSegTMsg);
        if (resDistStruSegTMsgArray.length() == 0) {
            return false;
        }
        
        distStructure.setStartDistSegment(resDistStruSegTMsgArray.no(0));
        return true;
    }

    @SuppressWarnings("unchecked")
    private boolean getStartSegmentBySellTo(NWXC002005AllocAvailSegParam param1, TargetDistStructure distStructure) {
        writeDebugLog("getStartSegmentBySellTo [Satrt]");

        Map<String, Object> key = new HashMap<String, Object>();
        DIST_STRU_SEGTMsg startDistSegment = distStructure.getStartDistSegment();

        BigDecimal leafLayerNo = new BigDecimal(startDistSegment.distStruLeafLayerNum.getValue());
        int intLeafLayerNo = leafLayerNo.intValue();
        writeDebugLog("intLeafLayerNo :" + intLeafLayerNo);

        if (intLeafLayerNo >= LEAF_LAYER_NO_1) {
            key.put("firstDistStruSegPk", startDistSegment.firstDistStruSegPk);
        }

        if (intLeafLayerNo >= LEAF_LAYER_NO_2) {
            key.put("sndDistStruSegPk", startDistSegment.scdDistStruSegPk);
        }

        if (intLeafLayerNo >= LEAF_LAYER_NO_3) {
            key.put("thirdDistStruSegPk", startDistSegment.thirdDistStruSegPk);
        }

        if (intLeafLayerNo >= LEAF_LAYER_NO_4) {
            key.put("frthDistStruSegPk", startDistSegment.frthDistStruSegPk);
        }

        if (intLeafLayerNo >= LEAF_LAYER_NO_5) {
            key.put("fifthDistStruSegPk", startDistSegment.fifthDistStruSegPk);
        }

        if (intLeafLayerNo >= LEAF_LAYER_NO_6) {
            key.put("sixthDistStruSegPk", startDistSegment.sixthDistStruSegPk);
        }

        if (intLeafLayerNo >= LEAF_LAYER_NO_7) {
            key.put("svnthDistStruSegPk", startDistSegment.svnthDistStruSegPk);
        }

        if (intLeafLayerNo >= LEAF_LAYER_NO_8) {
            key.put("eighthDistStruSegPk", startDistSegment.eighthDistStruSegPk);
        }

        leafLayerNo = leafLayerNo.add(ONE);

        String stringLeafNum = leafLayerNo.toString();
        stringLeafNum = ZYPCommonFunc.leftPad(stringLeafNum, ItemDigit.DIST_STRU_LEAF_LAYER_NUM.getDigit(), "0");

        key.put("glblCmpyCd", distStructure.getGlblCmpyCd());
        key.put("distStruPk", distStructure.getDistStruPk());
        key.put("distSegTpCd", DIST_SEG_TP.CUSTOMER);
        key.put("custCd", param1.getSellToCustCd());
        key.put("distStruLeafLayerNum", stringLeafNum);
        key.put("locRoleTpCd", LOC_ROLE_TP.SELL_TO);

        List<DIST_STRU_SEGTMsg> list = (List<DIST_STRU_SEGTMsg>) this.ssmBatchClient.queryObjectList("getSegmentBySellTo", key);

        if (isEmpty(list)) {
            writeDebugLog(" ** Enpty ** ");
            return false;
        }
        
        distStructure.setStartDistSegment(list.get(0));
        return true;
    }

    private boolean getDistStruSegKitting(NWXC002005AllocAvailSegParam param1, List<TargetDistStructure> distStructureRcdLst) {

        for( TargetDistStructure distStructure : distStructureRcdLst ) {
            
            DIST_STRU_SEGTMsg reqDistStruSegTMsg = new DIST_STRU_SEGTMsg();
            reqDistStruSegTMsg.setSQLID(SQLID_SELECT_DIST_STRU_SEG_SINGLE);
            reqDistStruSegTMsg.setConditionValue("glblCmpyCd01", distStructure.getGlblCmpyCd());
            reqDistStruSegTMsg.setConditionValue("distStruPk01", distStructure.getDistStruPk());
            reqDistStruSegTMsg.setConditionValue("distSegTpCd01", DIST_SEG_TP.KITTING);
            reqDistStruSegTMsg.setMaxCount(1);

            DIST_STRU_SEGTMsgArray resDistStruSegTMsgArray = (DIST_STRU_SEGTMsgArray) S21ApiTBLAccessor.findByCondition(reqDistStruSegTMsg);
            if (resDistStruSegTMsgArray.length() == 0) {
                return false;
            }

            List<BigDecimal> distStruSegRcdLst = distStructure.getDistStruSegRcdLst();
            distStruSegRcdLst.add(resDistStruSegTMsgArray.no(0).distStruSegPk.getValue());
        }
        
        return true;
    }

    private boolean getDistStruSegWHTransfer(NWXC002005AllocAvailSegParam param1, List<TargetDistStructure> distStructureRcdLst) {

        for( TargetDistStructure distStructure : distStructureRcdLst ) {

            DIST_STRU_SEGTMsg reqDistStruSegTMsg = new DIST_STRU_SEGTMsg();
            reqDistStruSegTMsg.setSQLID(SQLID_SELECT_DIST_STRU_SEG_SINGLE);
            reqDistStruSegTMsg.setConditionValue("glblCmpyCd01", distStructure.getGlblCmpyCd());
            reqDistStruSegTMsg.setConditionValue("distStruPk01", distStructure.getDistStruPk());
            if (WH_SYS_TP.FULFILLMENT.equals(param1.getWhSysTpCd())) {
                reqDistStruSegTMsg.setConditionValue("distSegTpCd01", DIST_SEG_TP.E_STORE);
            } else {
                reqDistStruSegTMsg.setConditionValue("distSegTpCd01", DIST_SEG_TP.WAREHOUSE_TRANSFER);
            }
            reqDistStruSegTMsg.setMaxCount(1);

            DIST_STRU_SEGTMsgArray resDistStruSegTMsgArray = (DIST_STRU_SEGTMsgArray) S21ApiTBLAccessor.findByCondition(reqDistStruSegTMsg);
            if (resDistStruSegTMsgArray.length() == 0) {
                return false;
            }

            List<BigDecimal> distStruSegRcdLst = distStructure.getDistStruSegRcdLst();
            distStruSegRcdLst.add(resDistStruSegTMsgArray.no(0).distStruSegPk.getValue());
        }
        
        return true;
    }

    private boolean getDistStruSegTop(NWXC002005AllocAvailSegParam param1, TargetDistStructure distStructure) {

        DIST_STRU_SEGTMsg reqDistStruSegTMsg = new DIST_STRU_SEGTMsg();
        reqDistStruSegTMsg.setSQLID(SQLID_SELECT_DIST_STRU_SEG_SINGLE);
        reqDistStruSegTMsg.setConditionValue("glblCmpyCd01", distStructure.getGlblCmpyCd());
        reqDistStruSegTMsg.setConditionValue("distStruPk01", distStructure.getDistStruPk());
        reqDistStruSegTMsg.setConditionValue("distSegTpCd01", DIST_SEG_TP.TOP);
        reqDistStruSegTMsg.setMaxCount(1);

        DIST_STRU_SEGTMsgArray resDistStruSegTMsgArray = (DIST_STRU_SEGTMsgArray) S21ApiTBLAccessor.findByCondition(reqDistStruSegTMsg);
        if (resDistStruSegTMsgArray.length() == 0) {
            return false;
        }
        
        List<BigDecimal> distStruSegRcdLst = distStructure.getDistStruSegRcdLst();
        distStruSegRcdLst.add(resDistStruSegTMsgArray.no(0).distStruSegPk.getValue());
        return true;
    }

    private void writeDebugLog(String str) {
        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, "[ NWXC002005 ] " + str, this);
        }
    }

    /**
     * <pre>
     * Check empty list.
     * </pre>
     * 
     * @param list List
     * @return true/empty. false/not empty.
     */
    private boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

}
