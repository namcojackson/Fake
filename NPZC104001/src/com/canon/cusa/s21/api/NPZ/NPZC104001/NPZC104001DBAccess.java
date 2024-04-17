/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC104001;

import static com.canon.cusa.s21.api.NPZ.NPZC104001.constant.NPZC104001Constant.DOMESTIC_VENDOR_PO;
import static com.canon.cusa.s21.api.NPZ.NPZC104001.constant.NPZC104001Constant.STAR;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.CM_DEF_ACCTTMsg;
import business.db.CPO_DTLTMsg;
import business.db.DS_PO_TPTMsg;
import business.db.FRT_COND_SVC_LVL_RELNTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.MDSETMsg;
import business.db.OTBD_CARR_VTMsg;
import business.db.POTMsg;
import business.db.PO_ACCTTMsg;
import business.db.PO_DTLTMsg;
import business.db.PO_LINE_TPTMsg;
import business.db.PO_MSGTMsg;
import business.db.PO_SER_NUMTMsg;
import business.db.PRCH_REQTMsg;
import business.db.PRNT_CMPY_VNDTMsg;
import business.db.RCV_ASN_VNDTMsg;
import business.db.RTL_SWHTMsg;
import business.db.RTL_SWHTMsgArray;
import business.db.RTL_WHTMsg;
import business.db.RWS_HDRTMsg;
import business.db.RWS_HDRTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_PLNTMsgArray;
import business.db.SHPG_SVC_LVLTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.parts.NPZC104001PMsg;
import business.parts.NPZC104001_poAcctInfoPMsg;
import business.parts.NPZC104001_poInfoPMsg;
import business.parts.NPZC104001_poLineInfoPMsg;
import business.parts.NPZC104001_serNumListPMsg;

import com.canon.cusa.s21.api.NPZ.NPZC104001.constant.NPZC104001Constant;
import com.canon.cusa.s21.common.NLX.NLXC016001.NLXSceConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_LOC_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * PO Create API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/08/2015   CITS            T.kikuhara      Create          CSA Ver3.0
 * 04/14/2016   CSAI            K.Lee           Update          QC#6664
 * 05/03/2016   CSAI            K.Lee           Update          QC#6460
 * 05/03/2016   CSAI            K.Lee           Update          QC#7300
 * 09/29/2016   CITS            T.Gotoda        Update          QC#13163
 * 10/26/2016   CITS            R.Shimamoto     Update          QC#15178
 * 12/13/2016   CITS            T.Hakodate      Update          QC#16441
 * 12/20/2016   CITS            S.Endo          Update          QC#16715
 * 06/13/2017   CITS            S.Endo          Update          QC#19072
 * 06/23/2017   CITS            S.Endo          Update          QC#19485
 * 08/03/2017   CITS            R.Shimamoto     Update          QC#18200
 * 09/01/2017   CITS            R.Shimamoto     Update          QC#20439
 * 09/29/2017   CITS            K.Ogino         Update          QC#21152
 * 10/17/2017   CITS            S.Katsuma       Update          QC#21206
 * 01/25/2018   CITS            K.Ogino         Update          QC#23617
 * 01/30/2018   CITS            K.Ogino         Update          QC#23616
 * 06/21/2018   CITS            K.Kameoka       Update          QC#18420
 * 07/30/2018   CITS            K.Kameoka       Update          QC#27024
 * 10/01/2018   CITS            K.Kameoka       Update          QC#28463
 * 12/14/2018   CITS            M.Naito         Update          QC#29027
 * 07/05/2019   CITS            K.Ogino         Update          QC#51175
 * 10/08/2019   CITS            R.Shimamoto     Update          QC#53392
 * 03/02/2020   Fujitsu         T.Ogura         Update          QC#55920
 * 04/06/2020   Fujitsu         T.Ogura         Update          QC#56390
 * 04/13/2020   Fujitsu         T.Ogura         Update          QC#56385
 * 02/15/2023   Hitachi         S.Dong          Update          QC#60966
 * </pre>
 */
public class NPZC104001DBAccess {

    protected static POTMsg getPo(String glblCmpyCd, String poOrdNum) {
        POTMsg inMsg = new POTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdNum, poOrdNum);
        return (POTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    protected static PO_DTLTMsg getPoDtl(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum) {
        PO_DTLTMsg inMsg = new PO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdNum, poOrdNum);
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdDtlLineNum, poOrdDtlLineNum);
        return (PO_DTLTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    protected static DS_PO_TPTMsg getDsPoTp(String glblCmpyCd, String dsPoTpCd) {
        DS_PO_TPTMsg inMsg = new DS_PO_TPTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsPoTpCd, dsPoTpCd);
        return (DS_PO_TPTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    protected static PO_SER_NUMTMsg getPoSerNum(String glblCmpyCd, BigDecimal poSerNumPk) {
        PO_SER_NUMTMsg inMsg = new PO_SER_NUMTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poSerNumPk, poSerNumPk);
        return (PO_SER_NUMTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    protected static PO_ACCTTMsg getPoAcct(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum, String poAcctTpCd) {
        PO_ACCTTMsg inMsg = new PO_ACCTTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdNum, poOrdNum);
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdDtlLineNum, poOrdDtlLineNum);
        ZYPEZDItemValueSetter.setValue(inMsg.poAcctTpCd, poAcctTpCd);
        return (PO_ACCTTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    protected static Map<String, Object> getShipToCustMap(String glblCmpyCd, String shipToCustCd, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NPZC104001Constant.GLBL_CMPY_CD, glblCmpyCd);
        param.put(NPZC104001Constant.SHIP_TO_CUST_CD, shipToCustCd);
        param.put(NPZC104001Constant.RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject(NPZC104001Constant.GET_SHIP_TO_CUST, param);
        return result;
    }

    protected static Map<String, Object> getVendor(String glblCmpyCd, String vndCd, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NPZC104001Constant.GLBL_CMPY_CD, glblCmpyCd);
        param.put(NPZC104001Constant.VND_CD, vndCd);
        param.put(NPZC104001Constant.RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);
        // QC#16441 START
        // param.put(NPZC104001Constant.INTL_VND_FLG,
        // ZYPConstant.FLG_OFF_N);
        // QC#16441 END
        param.put(NPZC104001Constant.VND_TP_CD, VND_TP.SUPPLIER);
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject(NPZC104001Constant.GET_VENDOR, param);
        return result;
    }

    protected static Map<String, Object> getVendorBuyBack(String glblCmpyCd, String vndCd, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NPZC104001Constant.GLBL_CMPY_CD, glblCmpyCd);
        param.put(NPZC104001Constant.VND_CD, vndCd);
        param.put(NPZC104001Constant.RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);
        // QC#16441 START
        // param.put(NPZC104001Constant.INTL_VND_FLG,
        // ZYPConstant.FLG_OFF_N);
        // QC#16441 END
        param.put(NPZC104001Constant.VND_TP_CD, VND_TP.SUPPLIER);
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject(NPZC104001Constant.GET_VENDOR_BUY_BACK, param);
        return result;
    }

    protected static Map<String, Object> getRetailWarehouse(String glblCmpyCd, String shipToStCd, String shipToPostCd, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NPZC104001Constant.GLBL_CMPY_CD, glblCmpyCd);
        param.put(NPZC104001Constant.ST_CD, shipToStCd);
        param.put(NPZC104001Constant.POST_CD, shipToPostCd);
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject(NPZC104001Constant.GET_RETAIL_WARE_HOUSE, param);
        return result;
    }

    protected static Map<String, Object> getRetailWarehouseDefault(String glblCmpyCd, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NPZC104001Constant.GLBL_CMPY_CD, glblCmpyCd);
        //QC#28463 Mod Start
        param.put(NPZC104001Constant.STAR_TXT, NPZC104001Constant.STAR);
        //QC#28463 Mod End
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject(NPZC104001Constant.GET_RETAIL_WARE_HOUSE_DEFAULT, param);
        return result;
    }

    protected static int getOtbdCarrV(String glblCmpyCd, String carrCd) {
        OTBD_CARR_VTMsg inMsg = new OTBD_CARR_VTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("carrCd01", carrCd);
        return S21ApiTBLAccessor.count(inMsg);
    }

    protected static String getCarrSvcLvlCd(String glblCmpyCd, String carrCd, String shpgSvcLvlCd, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NPZC104001Constant.GLBL_CMPY_CD, glblCmpyCd);
        param.put(NPZC104001Constant.SHPG_SVC_LVL_CD, shpgSvcLvlCd);
        param.put(NPZC104001Constant.CARR_CD, carrCd);
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject(NPZC104001Constant.GET_CARR_SVC_LVL_CD, param);
        if (result == null) {
            return "";
        } else {
            return (String) result.get(NPZC104001Constant.CARR_SVC_LVL_CD);
        }
    }

    protected static String getfFrtCondCd(String glblCmpyCd, String lineBizTpCd, String frtCondCd, String shpgSvcLvlCd, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NPZC104001Constant.GLBL_CMPY_CD, glblCmpyCd);
        param.put(NPZC104001Constant.LINE_BIZ_TP_CD, lineBizTpCd);
        param.put(NPZC104001Constant.FRT_COND_CD, frtCondCd);
        param.put(NPZC104001Constant.SHPG_SVC_LVL_CD, shpgSvcLvlCd);
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject(NPZC104001Constant.GET_FRT_COND_CD, param);
        if (result == null) {
            return "";
        } else {
            return (String) result.get(NPZC104001Constant.FRT_COND_CD);
        }
    }

    protected static FRT_COND_SVC_LVL_RELNTMsg getFrtCondSvcLvlReln(String glblCmpyCd, String lineBizTpCd, String frtCondCd, String shpgSvcLvlCd, String carrSvcLvlCd) {
        FRT_COND_SVC_LVL_RELNTMsg inMsg = new FRT_COND_SVC_LVL_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.lineBizTpCd, lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(inMsg.frtCondCd, frtCondCd);
        ZYPEZDItemValueSetter.setValue(inMsg.shpgSvcLvlCd, shpgSvcLvlCd);
        // QC#18200 Mod.
        if (carrSvcLvlCd != null && !carrSvcLvlCd.isEmpty()) {
            ZYPEZDItemValueSetter.setValue(inMsg.carrSvcLvlCd, carrSvcLvlCd);
        } else {
        	ZYPEZDItemValueSetter.setValue(inMsg.carrSvcLvlCd, STAR);
        }
        return (FRT_COND_SVC_LVL_RELNTMsg) S21CacheTBLAccessor.findByKey(inMsg);
    }

    protected static Map<String, Object> getRtlWhSrcDest(String glblCmpyCd, String srcRtlWhCd, String destRtlWhCd, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NPZC104001Constant.GLBL_CMPY_CD, glblCmpyCd);
        param.put(NPZC104001Constant.SRC_RTL_WH_CD, srcRtlWhCd);
        param.put(NPZC104001Constant.DEST_RTL_WH_CD, destRtlWhCd);
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject(NPZC104001Constant.GET_RTL_WH_SRC_DEST, param);
        return result;
    }

    protected static PO_LINE_TPTMsg getPoLineTp(String glblCmpyCd, String poLineTpCd) {
        PO_LINE_TPTMsg inMsg = new PO_LINE_TPTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poLineTpCd, poLineTpCd);
        return (PO_LINE_TPTMsg) S21CacheTBLAccessor.findByKey(inMsg);
    }

    protected static Map<String, Object> getDsInvtyLocV(String glblCmpyCd, String destRtlWhCd, String destRtlSwhCd, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NPZC104001Constant.GLBL_CMPY_CD, glblCmpyCd);
        param.put(NPZC104001Constant.DEST_RTL_WH_CD, destRtlWhCd);
        param.put(NPZC104001Constant.DEST_RTL_SWH_CD, destRtlSwhCd);
        param.put(NPZC104001Constant.RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject(NPZC104001Constant.GET_DS_INVTY_LOC_V, param);
        return result;
    }

    protected static Map<String, Object> getMdse(String glblCmpyCd, String mdseCd, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NPZC104001Constant.GLBL_CMPY_CD, glblCmpyCd);
        param.put(NPZC104001Constant.MDSE_CD, mdseCd);
        param.put(NPZC104001Constant.RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject(NPZC104001Constant.GET_MDSE, param);
        return result;
    }

    protected static GLBL_CMPYTMsg getGlblCmpy(String glblCmpyCd) {
        GLBL_CMPYTMsg inMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        return (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(inMsg);
    }

    protected static CM_DEF_ACCTTMsg getCmDefAcct(String glblCmpyCd, String acrlCmDefAcctCd) {
        CM_DEF_ACCTTMsg inMsg = new CM_DEF_ACCTTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.cmDefAcctCd, acrlCmDefAcctCd);
        return (CM_DEF_ACCTTMsg) S21CacheTBLAccessor.findByKey(inMsg);
    }

    protected static RTL_WHTMsg getRtlWh(String glblCmpyCd, String rtlWhCd) {
        RTL_WHTMsg inMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rtlWhCd, rtlWhCd);
        return (RTL_WHTMsg) S21CacheTBLAccessor.findByKey(inMsg);
    }

    protected static RTL_SWHTMsg getRtlSwh(String glblCmpyCd, String invtyLocCd, String slsDt) {
        RTL_SWHTMsg inMsg = new RTL_SWHTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("invtyLocCd01", invtyLocCd);
        inMsg.setConditionValue("effFromDt01", slsDt);
        inMsg.setConditionValue("effThruDt01", slsDt);

        RTL_SWHTMsgArray resultList = (RTL_SWHTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        RTL_SWHTMsg result = null;
        if (resultList != null && resultList.getValidCount() > 0) {
            result = (RTL_SWHTMsg) resultList.get(0);
        }
        return result;
    }

    protected static Map<String, Object> getCoaProjAcct(String glblCmpyCd, String mdseCd, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NPZC104001Constant.GLBL_CMPY_CD, glblCmpyCd);
        param.put(NPZC104001Constant.MDSE_CD, mdseCd);
        param.put(NPZC104001Constant.PO_ACCT_TP_CD, PO_ACCT_TP.ACCRUAL);
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject(NPZC104001Constant.GET_COA_PROJ_ACCT, param);
        return result;
    }

    protected static List<Map<String, Object>> getConfigComponet(String glblCmpyCd, BigDecimal svcConfigMstrPk, String srcRtlWhCd, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NPZC104001Constant.GLBL_CMPY_CD, glblCmpyCd);
        param.put(NPZC104001Constant.SVC_CONFIG_MSTR_PK, svcConfigMstrPk.toString());
        param.put(NPZC104001Constant.CREATED, SVC_MACH_MSTR_STS.CREATED);
        param.put(NPZC104001Constant.REMOVED, SVC_MACH_MSTR_STS.REMOVED);
        param.put(NPZC104001Constant.CUR_LOC_NUM, srcRtlWhCd + "%");
        param.put(NPZC104001Constant.SVC_MACH_MAINT_AVAL_FLG, ZYPConstant.FLG_ON_Y);
        List<Map<String, Object>> result = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(NPZC104001Constant.GET_CONFIG_COMPONENT, param);
        return result;
    }

    protected static List<Map<String, Object>> getSetComponent(String glblCmpyCd, String mdseCd, String poSubmtTs, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NPZC104001Constant.GLBL_CMPY_CD, glblCmpyCd);
        param.put(NPZC104001Constant.PRNT_MDSE_CD, mdseCd);
        param.put(NPZC104001Constant.MDSE_CMPSN_TP_CD, MDSE_CMPSN_TP.SET_MDSE);
        param.put(NPZC104001Constant.PO_SUBMT_TS, poSubmtTs);
        param.put(NPZC104001Constant.RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);
        param.put(NPZC104001Constant.PO_ACCT_TP_CD, PO_ACCT_TP.ACCRUAL);
        param.put(NPZC104001Constant.MDSE_CMPSN_TP_CD_ORDERTAKE, MDSE_CMPSN_TP.SET_ORDERTAKE_MDSE);
        List<Map<String, Object>> result = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(NPZC104001Constant.GET_SET_COMPONENT, param);
        return result;
    }

    protected static int countPrntCmpyVnd(String glblCmpyCd, String vndCd) {
        PRNT_CMPY_VNDTMsg inMsg = new PRNT_CMPY_VNDTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("prntCmpyVndCd01", vndCd);
        inMsg.setConditionValue("vndSysTpCd01", VND_SYS_TP.PARTS);
        return S21ApiTBLAccessor.count(inMsg);
    }

    protected static void deletePo(String glblCmpyCd, String poOrdNum) {
        POTMsg inMsg = new POTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdNum, poOrdNum);
        S21ApiTBLAccessor.remove(inMsg);
    }

    protected static void deletePoDtl(String glblCmpyCd, String poOrdNum) {
        PO_DTLTMsg inMsg = new PO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdNum, poOrdNum);
        S21ApiTBLAccessor.removeByPartialKey(inMsg);
    }

    protected static void deletePoAcct(String glblCmpyCd, String poOrdNum) {
        PO_ACCTTMsg inMsg = new PO_ACCTTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdNum, poOrdNum);
        S21ApiTBLAccessor.removeByPartialKey(inMsg);
    }

    protected static void deletePoSerNum(String glblCmpyCd, String poOrdNum, S21SsmBatchClient ssmBatchClient) {
        List<BigDecimal> poSerNumPkList = getPoSerNumPkList(glblCmpyCd, poOrdNum, ssmBatchClient);
        if (poSerNumPkList != null) {
            for (BigDecimal poSerNumPk : poSerNumPkList) {
                PO_SER_NUMTMsg inMsg = new PO_SER_NUMTMsg();
                ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inMsg.poSerNumPk, poSerNumPk);
                S21ApiTBLAccessor.remove(inMsg);
            }
        }
    }

    protected static void insertPo(NPZC104001PMsg pMsg, String poOrdNum, String poStsCd, String rwsAutoCratFlg) {
        POTMsg inMsg = new POTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdNum, poOrdNum);
        ZYPEZDItemValueSetter.setValue(inMsg.poApvlDt, pMsg.poApvlDt);
        ZYPEZDItemValueSetter.setValue(inMsg.poStsCd, poStsCd);
        ZYPEZDItemValueSetter.setValue(inMsg.vndCd, pMsg.vndCd);
        ZYPEZDItemValueSetter.setValue(inMsg.vndNm, pMsg.vndNm);
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdCmntTxt, pMsg.poOrdCmntTxt);
        ZYPEZDItemValueSetter.setValue(inMsg.poSendFlg, pMsg.poSendFlg);
        if (ZYPCommonFunc.hasValue(pMsg.vndDropShipFlg)) {
            ZYPEZDItemValueSetter.setValue(inMsg.vndDropShipFlg, pMsg.vndDropShipFlg);
        } else {
            if (ZYPCommonFunc.hasValue(pMsg.poQlfyCd)) {
                ZYPEZDItemValueSetter.setValue(inMsg.vndDropShipFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(inMsg.vndDropShipFlg, ZYPConstant.FLG_OFF_N);
            }
        }
        ZYPEZDItemValueSetter.setValue(inMsg.poPrintFlg, pMsg.poPrintFlg);
        ZYPEZDItemValueSetter.setValue(inMsg.poPrchOrdTpCd, DOMESTIC_VENDOR_PO);
        ZYPEZDItemValueSetter.setValue(inMsg.poCpltDelyFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inMsg.vndIssOrdNum, pMsg.vndIssOrdNum);
        ZYPEZDItemValueSetter.setValue(inMsg.poSubmtPsnCd, pMsg.poSubmtPsnCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poSubmtTs, pMsg.poSubmtTs);
        ZYPEZDItemValueSetter.setValue(inMsg.poApvlPsnCd, pMsg.poApvlPsnCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poApvlTs, pMsg.poApvlTs);
        if (rwsAutoCratFlg == null || rwsAutoCratFlg.isEmpty()) {
            rwsAutoCratFlg = ZYPConstant.FLG_OFF_N;
        }
        ZYPEZDItemValueSetter.setValue(inMsg.rwsAutoCratFlg, rwsAutoCratFlg);
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdSrcCd, pMsg.poOrdSrcCd);

        //additional column
        ZYPEZDItemValueSetter.setValue(inMsg.dsPoTpCd, pMsg.dsPoTpCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsPoTpNm, pMsg.dsPoTpNm);
        ZYPEZDItemValueSetter.setValue(inMsg.trsmtMethTpCd, pMsg.trsmtMethTpCd);
        ZYPEZDItemValueSetter.setValue(inMsg.sendPoFaxNum, pMsg.sendPoFaxNum);
        ZYPEZDItemValueSetter.setValue(inMsg.sendPoEmlAddr, pMsg.sendPoEmlAddr);
        ZYPEZDItemValueSetter.setValue(inMsg.carrAcctNum, pMsg.carrAcctNum);
        ZYPEZDItemValueSetter.setValue(inMsg.poHdrStsCd, PO_HDR_STS.OPEN);
        if (ZYPCommonFunc.hasValue(pMsg.poApvlStsCd)) {
            ZYPEZDItemValueSetter.setValue(inMsg.poApvlStsCd, pMsg.poApvlStsCd);
        } else {
            ZYPEZDItemValueSetter.setValue(inMsg.poApvlStsCd, PO_APVL_STS.ENTERED);
        }
        ZYPEZDItemValueSetter.setValue(inMsg.poQlfyCd, pMsg.poQlfyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.prntVndCd, pMsg.prntVndCd);
        ZYPEZDItemValueSetter.setValue(inMsg.prntVndNm, pMsg.prntVndNm);
        ZYPEZDItemValueSetter.setValue(inMsg.rqstTechTocCd, pMsg.rqstTechTocCd);
        ZYPEZDItemValueSetter.setValue(inMsg.destRtlWhCd, pMsg.destRtlWhCd);
        ZYPEZDItemValueSetter.setValue(inMsg.srcRtlWhCd, pMsg.srcRtlWhCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rqstRcvDt, pMsg.rqstRcvDt);
        ZYPEZDItemValueSetter.setValue(inMsg.rqstRcvTm, pMsg.rqstRcvTm);
        // START 2023/02/15 S.Dong [QC#60966, ADD]
        ZYPEZDItemValueSetter.setValue(inMsg.rqstShipDt, pMsg.rqstShipDt);
        // END 2023/02/15 S.Dong [QC#60966, ADD]
        ZYPEZDItemValueSetter.setValue(inMsg.prchGrpCd, pMsg.prchGrpCd);
        ZYPEZDItemValueSetter.setValue(inMsg.vndPmtTermCd, pMsg.vndPmtTermCd);
        ZYPEZDItemValueSetter.setValue(inMsg.vndPmtTermDescTxt, pMsg.vndPmtTermDescTxt);
        ZYPEZDItemValueSetter.setValue(inMsg.rtrnShipToRtlWhCd, pMsg.rtrnShipToRtlWhCd);
        ZYPEZDItemValueSetter.setValue(inMsg.shipFromSoNumListTxt, pMsg.shipFromSoNumListTxt);
        ZYPEZDItemValueSetter.setValue(inMsg.poSendTs, pMsg.poSendTs);
        ZYPEZDItemValueSetter.setValue(inMsg.eipRptRqstPk, pMsg.eipRptRqstPk);


        S21ApiTBLAccessor.insert(inMsg);
    }

    protected static PO_MSGTMsg getPoMsg(NPZC104001PMsg pMsg, BigDecimal poMsgPk) {
        PO_MSGTMsg inMsg = new PO_MSGTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poMsgPk, poMsgPk);
        return (PO_MSGTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    protected static void updatePoMsg(PO_MSGTMsg inMsg, String poMsgTxt) {
        ZYPEZDItemValueSetter.setValue(inMsg.poMsgTxt, poMsgTxt);
        S21ApiTBLAccessor.update(inMsg);
    }

    protected static void insertPoMsg(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum, NPZC104001_poInfoPMsg poInfo, int segId, String poMsgTxt) {
        PO_MSGTMsg inMsg = new PO_MSGTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poMsgPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PO_MSG_SQ));
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdNum, poOrdNum);
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdDtlLineNum, poOrdDtlLineNum);
        ZYPEZDItemValueSetter.setValue(inMsg.poMsgTpCd, poInfo.poMsgTpCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poMsgSegId, new BigDecimal(segId));
        ZYPEZDItemValueSetter.setValue(inMsg.poMsgSubmtPsnCd, poInfo.poMsgSubmtPsnCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poMsgTxt, poMsgTxt);
        inMsg.prchReqNum.clear();
        inMsg.prchReqLineNum.clear();
        inMsg.prchReqLineSubNum.clear();
        S21ApiTBLAccessor.insert(inMsg);
    }

    protected static void insertPoDtl(NPZC104001PMsg pMsg, NPZC104001_poLineInfoPMsg poLineInfo, String poStsCd, BigDecimal poInvBalQty, String poLineStsCd) {
        PO_DTLTMsg inMsg = new PO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdNum, pMsg.poOrdNum);
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdDtlLineNum, poLineInfo.poOrdDtlLineNum);
        ZYPEZDItemValueSetter.setValue(inMsg.poStsCd, poStsCd);
        ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, poLineInfo.mdseCd);
        ZYPEZDItemValueSetter.setValue(inMsg.mdseNm, poLineInfo.mdseNm);
        ZYPEZDItemValueSetter.setValue(inMsg.poQty, poLineInfo.poQty);
        ZYPEZDItemValueSetter.setValue(inMsg.poRcvQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.poCancQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.poBalQty, poLineInfo.poQty);
        ZYPEZDItemValueSetter.setValue(inMsg.thisMthFobCostAmt, poLineInfo.thisMthFobCostAmt);
        ZYPEZDItemValueSetter.setValue(inMsg.ccyCd, pMsg.dealCcyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.etaDt, poLineInfo.rqstRcvDt);
        ZYPEZDItemValueSetter.setValue(inMsg.shpgPlnNum, poLineInfo.shpgPlnNum);
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdDtlCmntTxt, poLineInfo.poOrdDtlCmntTxt);
        ZYPEZDItemValueSetter.setValue(inMsg.cpoOrdNum, poLineInfo.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(inMsg.custIssPoNum, poLineInfo.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(inMsg.custIssPoDt, poLineInfo.custIssPoDt);
        ZYPEZDItemValueSetter.setValue(inMsg.cpoOrdTpCd, poLineInfo.cpoOrdTpCd);
        ZYPEZDItemValueSetter.setValue(inMsg.adminPsnCd, poLineInfo.adminPsnCd);
        ZYPEZDItemValueSetter.setValue(inMsg.billToCustCd, poLineInfo.billToCustCd);
        ZYPEZDItemValueSetter.setValue(inMsg.sellToCustCd, poLineInfo.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(inMsg.cpoDtlLineNum, poLineInfo.cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(inMsg.cpoDtlLineSubNum, poLineInfo.cpoDtlLineSubNum);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToCustCd, pMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToLocNm, pMsg.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToAddlLocNm, pMsg.shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToFirstLineAddr, pMsg.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToScdLineAddr, pMsg.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToThirdLineAddr, pMsg.shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToFrthLineAddr, pMsg.shipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToFirstRefCmntTxt, pMsg.shipToFirstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToScdRefCmntTxt, pMsg.shipToScdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToCtyAddr, pMsg.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToStCd, pMsg.shipToStCd);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToProvNm, pMsg.shipToProvNm);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToCntyNm, pMsg.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToPostCd, pMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToCtryCd, pMsg.shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(inMsg.ordQty, poLineInfo.ordQty);
        ZYPEZDItemValueSetter.setValue(inMsg.frtChrgToCd, pMsg.frtChrgToCd);
        ZYPEZDItemValueSetter.setValue(inMsg.frtChrgMethCd, pMsg.frtChrgMethCd);
        ZYPEZDItemValueSetter.setValue(inMsg.shpgSvcLvlCd, pMsg.shpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(inMsg.custUomCd, poLineInfo.custUomCd);
        ZYPEZDItemValueSetter.setValue(inMsg.carrCd, pMsg.carrCd);
        //QC#18420 Add Start
        ZYPEZDItemValueSetter.setValue(inMsg.poDtlDiscPct, poLineInfo.poDtlDiscPct);
        //QC#18420 Add End
        // QC#53392 2019/10/05 Add Start
        ZYPEZDItemValueSetter.setValue(inMsg.poDtlDiscPrcAmt, poLineInfo.poDtlDiscPrcAmt);
        // QC#53392 2019/10/05 Add End

        //additional column
        ZYPEZDItemValueSetter.setValue(inMsg.vndInvtyLocCd, poLineInfo.vndInvtyLocCd);
        ZYPEZDItemValueSetter.setValue(inMsg.prchReqNum, poLineInfo.prchReqNum);
        ZYPEZDItemValueSetter.setValue(inMsg.prchReqLineNum, poLineInfo.prchReqLineNum);
        ZYPEZDItemValueSetter.setValue(inMsg.prchReqLineSubNum, poLineInfo.prchReqLineSubNum);
        ZYPEZDItemValueSetter.setValue(inMsg.fromStkStsCd, poLineInfo.fromStkStsCd);
        ZYPEZDItemValueSetter.setValue(inMsg.toStkStsCd, poLineInfo.toStkStsCd);
        ZYPEZDItemValueSetter.setValue(inMsg.prchGrpCd, pMsg.prchGrpCd);
        ZYPEZDItemValueSetter.setValue(inMsg.ctacPsnNm, pMsg.ctacPsnNm);
        ZYPEZDItemValueSetter.setValue(inMsg.setPoOrdDtlLineNum, poLineInfo.setPoOrdDtlLineNum);
        ZYPEZDItemValueSetter.setValue(inMsg.poMdseCmpsnTpCd, poLineInfo.poMdseCmpsnTpCd);
        // START 2017/10/20 S.Katsuma QC#21206 ADD
        String lineStsCd = "";
        if (!ZYPCommonFunc.hasValue(poLineStsCd)) {
            lineStsCd = PO_LINE_STS.OPEN;
        } else {
            lineStsCd = poLineStsCd;
        }
//        ZYPEZDItemValueSetter.setValue(inMsg.poLineStsCd, PO_LINE_STS.OPEN);
        ZYPEZDItemValueSetter.setValue(inMsg.poLineStsCd, lineStsCd);
        // END 2017/10/20 S.Katsuma QC#21206 ADD
        ZYPEZDItemValueSetter.setValue(inMsg.poLineTpCd, poLineInfo.poLineTpCd);
        ZYPEZDItemValueSetter.setValue(inMsg.invtyLocCd, poLineInfo.invtyLocCd);
        ZYPEZDItemValueSetter.setValue(inMsg.destRtlWhCd, pMsg.destRtlWhCd);
        ZYPEZDItemValueSetter.setValue(inMsg.destRtlSwhCd, poLineInfo.destRtlSwhCd);
        ZYPEZDItemValueSetter.setValue(inMsg.srcRtlWhCd, pMsg.srcRtlWhCd);
        ZYPEZDItemValueSetter.setValue(inMsg.srcRtlSwhCd, poLineInfo.srcRtlSwhCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rqstRcvDt, poLineInfo.rqstRcvDt);
        ZYPEZDItemValueSetter.setValue(inMsg.rqstRcvTm, poLineInfo.rqstRcvTm);
        // START 2023/02/15 S.Dong [QC#60966, ADD]
        ZYPEZDItemValueSetter.setValue(inMsg.rqstShipDt, poLineInfo.rqstShipDt);
        // END 2023/02/15 S.Dong [QC#60966, ADD]
        ZYPEZDItemValueSetter.setValue(inMsg.poDispQty, poLineInfo.poDispQty);
        ZYPEZDItemValueSetter.setValue(inMsg.poDispUomCd, poLineInfo.poDispUomCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poInvQty, poLineInfo.poInvQty);
        ZYPEZDItemValueSetter.setValue(inMsg.poInvBalQty, poInvBalQty);
        ZYPEZDItemValueSetter.setValue(inMsg.aslDtlPk, poLineInfo.aslDtlPk);
        ZYPEZDItemValueSetter.setValue(inMsg.aslMdseCd, poLineInfo.aslMdseCd);
        if (!ZYPCommonFunc.hasValue(poLineInfo.aslMdseCd)) {
            // QC#23617. QC#23616 Mod
            if (!(PO_LINE_TP.EXPENSE.equals(poLineInfo.poLineTpCd.getValue()) //
                    || PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(poLineInfo.poLineTpCd.getValue()) //
                    || PO_LINE_TP.ASSET.equals(poLineInfo.poLineTpCd.getValue()))) {
                ZYPEZDItemValueSetter.setValue(inMsg.aslMdseCd, poLineInfo.mdseCd);
            }
        }
        ZYPEZDItemValueSetter.setValue(inMsg.aslUnitPrcAmt, poLineInfo.aslUnitPrcAmt);
        ZYPEZDItemValueSetter.setValue(inMsg.entDealNetUnitPrcAmt, poLineInfo.entDealNetUnitPrcAmt);
        ZYPEZDItemValueSetter.setValue(inMsg.entPoDtlDealNetAmt, poLineInfo.entPoDtlDealNetAmt);
        ZYPEZDItemValueSetter.setValue(inMsg.entFuncNetUnitPrcAmt, poLineInfo.entFuncNetUnitPrcAmt);
        ZYPEZDItemValueSetter.setValue(inMsg.entPoDtlFuncNetAmt, poLineInfo.entPoDtlFuncNetAmt);
        ZYPEZDItemValueSetter.setValue(inMsg.exchRate, poLineInfo.exchRate);
        ZYPEZDItemValueSetter.setValue(inMsg.poMatchTpCd, poLineInfo.poMatchTpCd);
        ZYPEZDItemValueSetter.setValue(inMsg.frtCondCd, poLineInfo.frtCondCd);
        ZYPEZDItemValueSetter.setValue(inMsg.origMdseCd, poLineInfo.origMdseCd);
        if (!ZYPCommonFunc.hasValue(poLineInfo.origMdseCd)) {
            ZYPEZDItemValueSetter.setValue(inMsg.origMdseCd, poLineInfo.mdseCd);
        }
        ZYPEZDItemValueSetter.setValue(inMsg.trxRefNum, poLineInfo.trxRefNum);
        ZYPEZDItemValueSetter.setValue(inMsg.trxRefLineNum, poLineInfo.trxRefLineNum);
        ZYPEZDItemValueSetter.setValue(inMsg.trxRefLineSubNum, poLineInfo.trxRefLineSubNum);
        ZYPEZDItemValueSetter.setValue(inMsg.vndIssPoOrdNum, poLineInfo.vndIssPoOrdNum);
        ZYPEZDItemValueSetter.setValue(inMsg.shipFromSoNumListTxt, poLineInfo.shipFromSoNumListTxt);
        ZYPEZDItemValueSetter.setValue(inMsg.proNum, poLineInfo.proNum);
        ZYPEZDItemValueSetter.setValue(inMsg.dispPoDtlLineNum, poLineInfo.dispPoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(inMsg.vndPoAckStsCd, poLineInfo.vndPoAckStsCd);
        ZYPEZDItemValueSetter.setValue(inMsg.origPoOrdNum, poLineInfo.origPoOrdNum);
        if (!ZYPCommonFunc.hasValue(poLineInfo.origPoOrdNum)) {
            ZYPEZDItemValueSetter.setValue(inMsg.origPoOrdNum, pMsg.poOrdNum);
        }
        ZYPEZDItemValueSetter.setValue(inMsg.origPoOrdDtlLineNum, poLineInfo.origPoOrdDtlLineNum);
        if (!ZYPCommonFunc.hasValue(poLineInfo.origPoOrdDtlLineNum)) {
            ZYPEZDItemValueSetter.setValue(inMsg.origPoOrdDtlLineNum, poLineInfo.poOrdDtlLineNum);
        }
        ZYPEZDItemValueSetter.setValue(inMsg.origDispPoDtlLineNum, poLineInfo.origDispPoDtlLineNum);
        if (!ZYPCommonFunc.hasValue(poLineInfo.origDispPoDtlLineNum)) {
            ZYPEZDItemValueSetter.setValue(inMsg.origDispPoDtlLineNum, poLineInfo.dispPoDtlLineNum);
        }
        ZYPEZDItemValueSetter.setValue(inMsg.svcConfigMstrPk, poLineInfo.svcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(inMsg.poSendTs, poLineInfo.poSendTs);
        ZYPEZDItemValueSetter.setValue(inMsg.mdseDescShortTxt, poLineInfo.mdseDescShortTxt);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToAcctNm, pMsg.shipToAcctNm);
        // QC#21152 Add
        if (!ZYPCommonFunc.hasValue(inMsg.entPoDtlDealNetAmt) && ZYPCommonFunc.hasValue(inMsg.entDealNetUnitPrcAmt)) {
            ZYPEZDItemValueSetter.setValue(inMsg.entPoDtlDealNetAmt, inMsg.entDealNetUnitPrcAmt.getValue().multiply(inMsg.poQty.getValue()));
        }
        if (!ZYPCommonFunc.hasValue(inMsg.entPoDtlFuncNetAmt) && ZYPCommonFunc.hasValue(inMsg.entFuncNetUnitPrcAmt)) {
            ZYPEZDItemValueSetter.setValue(inMsg.entPoDtlFuncNetAmt, inMsg.entFuncNetUnitPrcAmt.getValue().multiply(inMsg.poQty.getValue()));
        }
        // QC#21152 End
        S21ApiTBLAccessor.insert(inMsg);
    }

    protected static void insertPoSerNum(NPZC104001PMsg pMsg, NPZC104001_poLineInfoPMsg poLineInfo, NPZC104001_serNumListPMsg serNum, String invtyLocNm) {
        PO_SER_NUMTMsg inMsg = new PO_SER_NUMTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(serNum.poSerNumPk)) {
            ZYPEZDItemValueSetter.setValue(inMsg.poSerNumPk, serNum.poSerNumPk);
        } else {
            ZYPEZDItemValueSetter.setValue(inMsg.poSerNumPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PO_SER_NUM_SQ));
        }
        ZYPEZDItemValueSetter.setValue(inMsg.serOwnrId, NLXSceConst.OWNER_PROCUREMENT);
        ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, poLineInfo.mdseCd);
        ZYPEZDItemValueSetter.setValue(inMsg.serNum, serNum.serNum);
        ZYPEZDItemValueSetter.setValue(inMsg.serEventTs, pMsg.xxRqstTs);
        ZYPEZDItemValueSetter.setValue(inMsg.serLocGrpCd, SER_LOC_GRP.DEALER_OR_RETAILER_OR_DISTRIBUTOR);
        ZYPEZDItemValueSetter.setValue(inMsg.serEventCd, NLXSceConst.SER_EVENT_CD_THIRD_PARTY_ORDER_RELEASED);
        ZYPEZDItemValueSetter.setValue(inMsg.fromLocCd, pMsg.vndCd);
        ZYPEZDItemValueSetter.setValue(inMsg.fromLocNm, pMsg.vndNm);
        if (ZYPCommonFunc.hasValue(pMsg.shipToCustCd)) {
            ZYPEZDItemValueSetter.setValue(inMsg.toLocCd, pMsg.shipToCustCd);
        } else {
            ZYPEZDItemValueSetter.setValue(inMsg.toLocCd, poLineInfo.invtyLocCd);
        }
        if (ZYPCommonFunc.hasValue(pMsg.shipToLocNm)) {
            ZYPEZDItemValueSetter.setValue(inMsg.toLocNm, pMsg.shipToLocNm);
        } else {
            ZYPEZDItemValueSetter.setValue(inMsg.toLocCd, invtyLocNm);
        }
        ZYPEZDItemValueSetter.setValue(inMsg.keyInfoCd_01, poLineInfo.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(inMsg.keyInfoCd_02, poLineInfo.cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(inMsg.keyInfoCd_03, poLineInfo.cpoDtlLineSubNum);
        ZYPEZDItemValueSetter.setValue(inMsg.keyInfoCd_04, pMsg.poOrdNum);
        ZYPEZDItemValueSetter.setValue(inMsg.keyInfoCd_05, poLineInfo.poOrdDtlLineNum);
        ZYPEZDItemValueSetter.setValue(inMsg.serNumSendFlg, ZYPConstant.FLG_OFF_N);
        S21ApiTBLAccessor.insert(inMsg);
    }

    protected static void insertPoAcct(NPZC104001PMsg pMsg, NPZC104001_poAcctInfoPMsg poAcctInfoList) {
        PO_ACCTTMsg inMsg = new PO_ACCTTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdNum, pMsg.poOrdNum);
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdDtlLineNum, poAcctInfoList.poOrdDtlLineNum);
        ZYPEZDItemValueSetter.setValue(inMsg.poAcctTpCd, poAcctInfoList.poAcctTpCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaCmpyCd, poAcctInfoList.coaCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaAfflCd, poAcctInfoList.coaAfflCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaBrCd, poAcctInfoList.coaBrCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaChCd, poAcctInfoList.coaChCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaCcCd, poAcctInfoList.coaCcCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaAcctCd, poAcctInfoList.coaAcctCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaProdCd, poAcctInfoList.coaProdCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaProjCd, poAcctInfoList.coaProjCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaExtnCd, poAcctInfoList.coaExtnCd);
        S21ApiTBLAccessor.insert(inMsg);
    }

    protected static SHPG_PLNTMsg getShpgPlnTMsg(String glblCmpyCd, String trxHdrNum, String trxLineNum, String trxLineSubNum, String trxSrcTpCd) {
        SHPG_PLNTMsg inMsg = new SHPG_PLNTMsg();
        inMsg.setSQLID("008");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("trxHdrNum01", trxHdrNum);
        inMsg.setConditionValue("trxLineNum01", trxLineNum);
        inMsg.setConditionValue("trxLineSubNum01", trxLineSubNum);
        inMsg.setConditionValue("trxSrcTpCd01", trxSrcTpCd);

        SHPG_PLNTMsgArray resultList = (SHPG_PLNTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        SHPG_PLNTMsg result = null;
        if (resultList != null && resultList.getValidCount() > 0) {
            result = (SHPG_PLNTMsg) resultList.get(0);
        }
        return result;
    }

    protected static void updateCpoDtl(NPZC104001PMsg pMsg, NPZC104001_poLineInfoPMsg poLineInfo) {
        CPO_DTLTMsg inMsg = new CPO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.cpoOrdNum, poLineInfo.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(inMsg.cpoDtlLineNum, poLineInfo.cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(inMsg.cpoDtlLineSubNum, poLineInfo.cpoDtlLineSubNum);
        inMsg = (CPO_DTLTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        ZYPEZDItemValueSetter.setValue(inMsg.invtyLocCd, pMsg.vndCd);
        S21ApiTBLAccessor.update(inMsg);
    }

    protected static MDSETMsg findMdse(String glblCmpyCd, String mdseCd) {
        MDSETMsg inMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, mdseCd);
        return (MDSETMsg) S21CacheTBLAccessor.findByKey(inMsg);
    }

    protected static BigDecimal getRwsQty(NPZC104001PMsg pMsg, String poOrdDtlLineNum, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NPZC104001Constant.GLBL_CMPY_CD, pMsg.glblCmpyCd.getValue());
        param.put(NPZC104001Constant.PO_ORD_NUM, pMsg.poOrdNum.getValue());
        param.put(NPZC104001Constant.GET_PO_ORD_DTL_LINE_NUM, poOrdDtlLineNum);
        param.put(NPZC104001Constant.PRINTED, RWS_STS.PRINTED);
        param.put(NPZC104001Constant.RECEIVING, RWS_STS.RECEIVING);
        BigDecimal rwsQty = (BigDecimal) ssmBatchClient.queryObject(NPZC104001Constant.GET_RWS_QTY, param);
        if (rwsQty != null && BigDecimal.ZERO.compareTo(rwsQty) < 0) {
            return rwsQty;
        } else {
            return null;
        }
    }

    // 2016/10/17 QC#6159 Mod Start.
//    protected static String getRwsRefNum(NPZC104001PMsg pMsg, String rwsRefNum) {
    protected static String getRwsRefNum(String glblCmpyCd, String destRtlWhCd, String rwsRefNum) {
        RWS_HDRTMsg inMsg = new RWS_HDRTMsg();
        inMsg.setSQLID("001");
//        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("rwsRefNum01", rwsRefNum);
//        inMsg.setConditionValue("whCd01", pMsg.destRtlWhCd.getValue());
        inMsg.setConditionValue("whCd01", destRtlWhCd);
        RWS_HDRTMsgArray resultList = (RWS_HDRTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        RWS_HDRTMsg result = null;
        if (resultList != null && resultList.getValidCount() > 0) {
            result = (RWS_HDRTMsg) resultList.get(0);
            return result.rwsRefNum.getValue();
        } else {
            return null;
        }
    }
    // 2016/10/17 QC#6159 Mod End.

    // 2016/10/17 QC#6159 Mod Start.
//    protected static String getMaxRwsRefNum(NPZC104001PMsg pMsg, String rwsRefNum, S21SsmBatchClient ssmBatchClient) {
    protected static String getMaxRwsRefNum(String glblCmpyCd, String destRtlWhCd, String rwsRefNum, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> param = new HashMap<String, String>();
//        param.put(NPZC104001Constant.GLBL_CMPY_CD, pMsg.glblCmpyCd.getValue());
//        param.put(NPZC104001Constant.DEST_RTL_WH_CD, pMsg.destRtlWhCd.getValue());
        param.put(NPZC104001Constant.GLBL_CMPY_CD, glblCmpyCd);
        param.put(NPZC104001Constant.DEST_RTL_WH_CD, destRtlWhCd);
        param.put(NPZC104001Constant.RWS_REF_NUM, "^" + rwsRefNum + "-" + "[0-9]{1,2}" + "$");
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject(NPZC104001Constant.GET_MAX_RWS_REF_NUM, param);
        if (result != null && result.get(NPZC104001Constant.RWS_REF_NUM) != null) {
            return (String) result.get(NPZC104001Constant.RWS_REF_NUM);
        } else {
            return null;
        }
    }
    // 2016/10/17 QC#6159 Mod End.

    protected static Integer countPrntCmpyVndByKey(NPZC104001PMsg pMsg, String vndCd, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NPZC104001Constant.GLBL_CMPY_CD, pMsg.glblCmpyCd.getValue());
        param.put(NPZC104001Constant.VND_CD, vndCd);
        return (Integer) ssmBatchClient.queryObject("countPrntCmpyVndByKey", param);
    }

    protected static Integer countRcvAsnVndByKey(NPZC104001PMsg pMsg, String vndCd, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NPZC104001Constant.GLBL_CMPY_CD, pMsg.glblCmpyCd.getValue());
        param.put(NPZC104001Constant.VND_CD, vndCd);
        return (Integer) ssmBatchClient.queryObject("countRcvAsnVndByKey", param);
    }

    protected static List<Map<String, Object>> getSerialList(String glblCmpyCd, String rwsNum, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NPZC104001Constant.GLBL_CMPY_CD, glblCmpyCd);
        param.put(NPZC104001Constant.RWS_NUM, rwsNum);
        //QC#27024 Add Start
        param.put(NPZC104001Constant.RWS_STS_CD, RWS_STS.CANCELED);
        //QC#27024 Add End
        List<Map<String, Object>> result = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(NPZC104001Constant.GET_SERIAL_LIST, param);
        return result;
    }

    protected static RCV_ASN_VNDTMsg getRcvAsnVnd(String glblCmpyCd, String poOrdNum) {
        RCV_ASN_VNDTMsg inMsg = new RCV_ASN_VNDTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rcvAsnVndCd, poOrdNum);
        return (RCV_ASN_VNDTMsg) S21CacheTBLAccessor.findByKey(inMsg);
    }

    protected static void updatePo(POTMsg inMsg, NPZC104001PMsg pMsg) {
        ZYPEZDItemValueSetter.setValue(inMsg.vndCd, pMsg.vndCd);
        ZYPEZDItemValueSetter.setValue(inMsg.vndNm, pMsg.vndNm);
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdCmntTxt, pMsg.poOrdCmntTxt);
        ZYPEZDItemValueSetter.setValue(inMsg.poSendFlg, pMsg.poSendFlg);
        ZYPEZDItemValueSetter.setValue(inMsg.poPrintFlg, pMsg.poPrintFlg);
        ZYPEZDItemValueSetter.setValue(inMsg.poPrchOrdTpCd, DOMESTIC_VENDOR_PO);
        // QC#20439
        if (PO_APVL_STS.ENTERED.equals(inMsg.poApvlStsCd.getValue()) && ZYPCommonFunc.hasValue(pMsg.poSubmtPsnCd)) {
        	ZYPEZDItemValueSetter.setValue(inMsg.poSubmtPsnCd, pMsg.poSubmtPsnCd);
        }

        //QC#19485
        if (ZYPCommonFunc.hasValue(pMsg.vndIssOrdNum)){
            ZYPEZDItemValueSetter.setValue(inMsg.vndIssOrdNum, pMsg.vndIssOrdNum);
        }
        //QC#19485
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdSrcCd, pMsg.poOrdSrcCd);
        if (PO_STS.SAVED.equals(inMsg.poStsCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(inMsg.poStsCd, PO_STS.WAITING_FOR_APPROVAL);
        }
        S21ApiTBLAccessor.update(inMsg);
    }

    protected static void updateAdditionalPo(POTMsg inMsg, NPZC104001PMsg pMsg, String poWfRqstFlg, String wfFlg) {
        ZYPEZDItemValueSetter.setValue(inMsg.dsPoTpCd, pMsg.dsPoTpCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsPoTpNm, pMsg.dsPoTpNm);
        ZYPEZDItemValueSetter.setValue(inMsg.trsmtMethTpCd, pMsg.trsmtMethTpCd);
        ZYPEZDItemValueSetter.setValue(inMsg.sendPoFaxNum, pMsg.sendPoFaxNum);
        ZYPEZDItemValueSetter.setValue(inMsg.sendPoEmlAddr, pMsg.sendPoEmlAddr);
        ZYPEZDItemValueSetter.setValue(inMsg.carrAcctNum, pMsg.carrAcctNum);

        ZYPEZDItemValueSetter.setValue(inMsg.vndCd, pMsg.vndCd);
        ZYPEZDItemValueSetter.setValue(inMsg.vndNm, pMsg.vndNm);
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdCmntTxt, pMsg.poOrdCmntTxt);
        ZYPEZDItemValueSetter.setValue(inMsg.poSendFlg, pMsg.poSendFlg);
        ZYPEZDItemValueSetter.setValue(inMsg.poPrintFlg, pMsg.poPrintFlg);
        ZYPEZDItemValueSetter.setValue(inMsg.poPrchOrdTpCd, DOMESTIC_VENDOR_PO);
        //QC#19485
        if (ZYPCommonFunc.hasValue(pMsg.vndIssOrdNum)){
            ZYPEZDItemValueSetter.setValue(inMsg.vndIssOrdNum, pMsg.vndIssOrdNum);
        }
        //QC#19485
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdSrcCd, pMsg.poOrdSrcCd);

        if (ZYPConstant.FLG_ON_Y.equals(poWfRqstFlg) //
                && ZYPConstant.FLG_ON_Y.equals(wfFlg)) {
            // Status change > Awaiting Approval
            ZYPEZDItemValueSetter.setValue(inMsg.poApvlStsCd, PO_APVL_STS.AWAITING_APPROVAL);
        }

        ZYPEZDItemValueSetter.setValue(inMsg.poQlfyCd, pMsg.poQlfyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.prntVndCd, pMsg.prntVndCd);
        ZYPEZDItemValueSetter.setValue(inMsg.prntVndNm, pMsg.prntVndNm);
        ZYPEZDItemValueSetter.setValue(inMsg.rqstTechTocCd, pMsg.rqstTechTocCd);
        ZYPEZDItemValueSetter.setValue(inMsg.destRtlWhCd, pMsg.destRtlWhCd);
        ZYPEZDItemValueSetter.setValue(inMsg.srcRtlWhCd, pMsg.srcRtlWhCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rqstRcvDt, pMsg.rqstRcvDt);
        ZYPEZDItemValueSetter.setValue(inMsg.rqstRcvTm, pMsg.rqstRcvTm);
        // START 2023/02/15 S.Dong [QC#60966, ADD]
        ZYPEZDItemValueSetter.setValue(inMsg.rqstShipDt, pMsg.rqstShipDt);
        // END 2023/02/15 S.Dong [QC#60966, ADD]
        ZYPEZDItemValueSetter.setValue(inMsg.prchGrpCd, pMsg.prchGrpCd);
        ZYPEZDItemValueSetter.setValue(inMsg.vndPmtTermCd, pMsg.vndPmtTermCd);
        ZYPEZDItemValueSetter.setValue(inMsg.vndPmtTermDescTxt, pMsg.vndPmtTermDescTxt);
        ZYPEZDItemValueSetter.setValue(inMsg.rtrnShipToRtlWhCd, pMsg.rtrnShipToRtlWhCd);
        ZYPEZDItemValueSetter.setValue(inMsg.shipFromSoNumListTxt, pMsg.shipFromSoNumListTxt);
        ZYPEZDItemValueSetter.setValue(inMsg.poSendTs, pMsg.poSendTs);
        S21ApiTBLAccessor.update(inMsg);
    }

    protected static void updatePoApprovalStatus(POTMsg inMsg, String poApprovalStatus) {
        ZYPEZDItemValueSetter.setValue(inMsg.poApvlStsCd, poApprovalStatus);
        S21ApiTBLAccessor.update(inMsg);
    }

    protected static void updatePoDtl(PO_DTLTMsg inMsg, NPZC104001PMsg pMsg, NPZC104001_poLineInfoPMsg poLineInfo, String poStsCd) {
        if (PO_STS.SAVED.equals(poStsCd)) {
            ZYPEZDItemValueSetter.setValue(inMsg.poStsCd, PO_STS.WAITING_FOR_APPROVAL);
        }
        ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, poLineInfo.mdseCd);
        ZYPEZDItemValueSetter.setValue(inMsg.mdseNm, poLineInfo.mdseNm);
        ZYPEZDItemValueSetter.setValue(inMsg.poQty, poLineInfo.poQty);
        BigDecimal poQty = poLineInfo.poQty.getValue();
        BigDecimal poCancQty = inMsg.poCancQty.getValue();
        BigDecimal poRcvQty = inMsg.poRcvQty.getValue();
        BigDecimal poBalQty = poQty.subtract(poCancQty).subtract(poRcvQty);
        ZYPEZDItemValueSetter.setValue(inMsg.poBalQty, poBalQty);
        ZYPEZDItemValueSetter.setValue(inMsg.thisMthFobCostAmt, poLineInfo.thisMthFobCostAmt);
        ZYPEZDItemValueSetter.setValue(inMsg.ccyCd, pMsg.dealCcyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.etaDt, poLineInfo.rqstRcvDt);
        ZYPEZDItemValueSetter.setValue(inMsg.shpgPlnNum, poLineInfo.shpgPlnNum);
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdDtlCmntTxt, poLineInfo.poOrdDtlCmntTxt);
        ZYPEZDItemValueSetter.setValue(inMsg.cpoOrdNum, poLineInfo.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(inMsg.custIssPoNum, poLineInfo.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(inMsg.custIssPoDt, poLineInfo.custIssPoDt);
        ZYPEZDItemValueSetter.setValue(inMsg.cpoOrdTpCd, poLineInfo.cpoOrdTpCd);
        ZYPEZDItemValueSetter.setValue(inMsg.adminPsnCd, poLineInfo.adminPsnCd);
        ZYPEZDItemValueSetter.setValue(inMsg.billToCustCd, poLineInfo.billToCustCd);
        ZYPEZDItemValueSetter.setValue(inMsg.sellToCustCd, poLineInfo.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(inMsg.cpoDtlLineNum, poLineInfo.cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(inMsg.cpoDtlLineSubNum, poLineInfo.cpoDtlLineSubNum);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToCustCd, pMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToLocNm, pMsg.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToAddlLocNm, pMsg.shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToFirstLineAddr, pMsg.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToScdLineAddr, pMsg.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToThirdLineAddr, pMsg.shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToFrthLineAddr, pMsg.shipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToFirstRefCmntTxt, pMsg.shipToFirstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToScdRefCmntTxt, pMsg.shipToScdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToCtyAddr, pMsg.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToStCd, pMsg.shipToStCd);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToProvNm, pMsg.shipToProvNm);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToCntyNm, pMsg.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToPostCd, pMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(inMsg.shipToCtryCd, pMsg.shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(inMsg.ordQty, poLineInfo.ordQty);
        ZYPEZDItemValueSetter.setValue(inMsg.frtChrgToCd, pMsg.frtChrgToCd);
        ZYPEZDItemValueSetter.setValue(inMsg.frtChrgMethCd, pMsg.frtChrgMethCd);
        ZYPEZDItemValueSetter.setValue(inMsg.shpgSvcLvlCd, pMsg.shpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(inMsg.custUomCd, poLineInfo.custUomCd);
        ZYPEZDItemValueSetter.setValue(inMsg.carrCd, pMsg.carrCd);
        //QC#18420 Add Start
        ZYPEZDItemValueSetter.setValue(inMsg.poDtlDiscPct, poLineInfo.poDtlDiscPct);
        //QC#18420 Add End
        // QC#53392 2019/10/05 Add Start
        ZYPEZDItemValueSetter.setValue(inMsg.poDtlDiscPrcAmt, poLineInfo.poDtlDiscPrcAmt);
        // QC#53392 2019/10/05 Add End
        S21ApiTBLAccessor.update(inMsg);
    }

    protected static void updateAdditionalPoDtl(PO_DTLTMsg inMsg, NPZC104001PMsg pMsg, NPZC104001_poLineInfoPMsg poLineInfo, BigDecimal poInvBalQty) {
        ZYPEZDItemValueSetter.setValue(inMsg.vndInvtyLocCd, poLineInfo.vndInvtyLocCd);
        ZYPEZDItemValueSetter.setValue(inMsg.prchReqNum, poLineInfo.prchReqNum);
        ZYPEZDItemValueSetter.setValue(inMsg.prchReqLineNum, poLineInfo.prchReqLineNum);
        ZYPEZDItemValueSetter.setValue(inMsg.prchReqLineSubNum, poLineInfo.prchReqLineSubNum);
        ZYPEZDItemValueSetter.setValue(inMsg.fromStkStsCd, poLineInfo.fromStkStsCd);
        ZYPEZDItemValueSetter.setValue(inMsg.toStkStsCd, poLineInfo.toStkStsCd);
        ZYPEZDItemValueSetter.setValue(inMsg.prchGrpCd, pMsg.prchGrpCd);
        ZYPEZDItemValueSetter.setValue(inMsg.ctacPsnNm, pMsg.ctacPsnNm);
        ZYPEZDItemValueSetter.setValue(inMsg.setPoOrdDtlLineNum, poLineInfo.setPoOrdDtlLineNum);
        ZYPEZDItemValueSetter.setValue(inMsg.poMdseCmpsnTpCd, poLineInfo.poMdseCmpsnTpCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poLineTpCd, poLineInfo.poLineTpCd);
        ZYPEZDItemValueSetter.setValue(inMsg.invtyLocCd, poLineInfo.invtyLocCd);
        ZYPEZDItemValueSetter.setValue(inMsg.destRtlWhCd, pMsg.destRtlWhCd);
        ZYPEZDItemValueSetter.setValue(inMsg.destRtlSwhCd, poLineInfo.destRtlSwhCd);
        ZYPEZDItemValueSetter.setValue(inMsg.srcRtlWhCd, pMsg.srcRtlWhCd);
        ZYPEZDItemValueSetter.setValue(inMsg.srcRtlSwhCd, poLineInfo.srcRtlSwhCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rqstRcvDt, poLineInfo.rqstRcvDt);
        ZYPEZDItemValueSetter.setValue(inMsg.rqstRcvTm, poLineInfo.rqstRcvTm);
        // START 2023/02/15 S.Dong [QC#60966, ADD]
        ZYPEZDItemValueSetter.setValue(inMsg.rqstShipDt, poLineInfo.rqstShipDt);
        // END 2023/02/15 S.Dong [QC#60966, ADD]
        ZYPEZDItemValueSetter.setValue(inMsg.poDispQty, poLineInfo.poDispQty);
        ZYPEZDItemValueSetter.setValue(inMsg.poDispUomCd, poLineInfo.poDispUomCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poInvQty, poLineInfo.poInvQty);
        ZYPEZDItemValueSetter.setValue(inMsg.poInvBalQty, poInvBalQty);
        ZYPEZDItemValueSetter.setValue(inMsg.aslDtlPk, poLineInfo.aslDtlPk);
        ZYPEZDItemValueSetter.setValue(inMsg.aslMdseCd, poLineInfo.aslMdseCd);
        if (!ZYPCommonFunc.hasValue(poLineInfo.aslMdseCd)) {
            // QC#23617. QC#23616 Mod
            if (!(PO_LINE_TP.EXPENSE.equals(poLineInfo.poLineTpCd.getValue()) //
                    || PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(poLineInfo.poLineTpCd.getValue()) //
                    || PO_LINE_TP.ASSET.equals(poLineInfo.poLineTpCd.getValue()))) {
                ZYPEZDItemValueSetter.setValue(inMsg.aslMdseCd, poLineInfo.mdseCd);
            }
        }
        ZYPEZDItemValueSetter.setValue(inMsg.aslUnitPrcAmt, poLineInfo.aslUnitPrcAmt);
        ZYPEZDItemValueSetter.setValue(inMsg.entDealNetUnitPrcAmt, poLineInfo.entDealNetUnitPrcAmt);
        ZYPEZDItemValueSetter.setValue(inMsg.entPoDtlDealNetAmt, poLineInfo.entPoDtlDealNetAmt);
        ZYPEZDItemValueSetter.setValue(inMsg.entFuncNetUnitPrcAmt, poLineInfo.entFuncNetUnitPrcAmt);
        ZYPEZDItemValueSetter.setValue(inMsg.entPoDtlFuncNetAmt, poLineInfo.entPoDtlFuncNetAmt);
        ZYPEZDItemValueSetter.setValue(inMsg.exchRate, poLineInfo.exchRate);
        ZYPEZDItemValueSetter.setValue(inMsg.poMatchTpCd, poLineInfo.poMatchTpCd);
        ZYPEZDItemValueSetter.setValue(inMsg.frtCondCd, poLineInfo.frtCondCd);
        ZYPEZDItemValueSetter.setValue(inMsg.origMdseCd, poLineInfo.origMdseCd);
        if (!ZYPCommonFunc.hasValue(poLineInfo.origMdseCd)) {
            ZYPEZDItemValueSetter.setValue(inMsg.origMdseCd, poLineInfo.mdseCd);
        }
        ZYPEZDItemValueSetter.setValue(inMsg.trxRefNum, poLineInfo.trxRefNum);
        ZYPEZDItemValueSetter.setValue(inMsg.trxRefLineNum, poLineInfo.trxRefLineNum);
        ZYPEZDItemValueSetter.setValue(inMsg.trxRefLineSubNum, poLineInfo.trxRefLineSubNum);
        ZYPEZDItemValueSetter.setValue(inMsg.vndIssPoOrdNum, poLineInfo.vndIssPoOrdNum);
        ZYPEZDItemValueSetter.setValue(inMsg.shipFromSoNumListTxt, poLineInfo.shipFromSoNumListTxt);
        ZYPEZDItemValueSetter.setValue(inMsg.proNum, poLineInfo.proNum);
        ZYPEZDItemValueSetter.setValue(inMsg.dispPoDtlLineNum, poLineInfo.dispPoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(inMsg.vndPoAckStsCd, poLineInfo.vndPoAckStsCd);
        ZYPEZDItemValueSetter.setValue(inMsg.origPoOrdNum, poLineInfo.origPoOrdNum);
        if (!ZYPCommonFunc.hasValue(poLineInfo.origPoOrdNum)) {
            ZYPEZDItemValueSetter.setValue(inMsg.origPoOrdNum, pMsg.poOrdNum);
        }
        ZYPEZDItemValueSetter.setValue(inMsg.origPoOrdDtlLineNum, poLineInfo.origPoOrdDtlLineNum);
        if (!ZYPCommonFunc.hasValue(poLineInfo.origPoOrdDtlLineNum)) {
            ZYPEZDItemValueSetter.setValue(inMsg.origPoOrdDtlLineNum, poLineInfo.poOrdDtlLineNum);
        }
        ZYPEZDItemValueSetter.setValue(inMsg.origDispPoDtlLineNum, poLineInfo.origDispPoDtlLineNum);
        if (!ZYPCommonFunc.hasValue(poLineInfo.origDispPoDtlLineNum)) {
            ZYPEZDItemValueSetter.setValue(inMsg.origDispPoDtlLineNum, poLineInfo.dispPoDtlLineNum);
        }
        ZYPEZDItemValueSetter.setValue(inMsg.svcConfigMstrPk, poLineInfo.svcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(inMsg.poSendTs, poLineInfo.poSendTs);
        ZYPEZDItemValueSetter.setValue(inMsg.mdseDescShortTxt, poLineInfo.mdseDescShortTxt);
        // QC#21152 Add
        if (!ZYPCommonFunc.hasValue(inMsg.entPoDtlDealNetAmt) && ZYPCommonFunc.hasValue(inMsg.entDealNetUnitPrcAmt)) {
            ZYPEZDItemValueSetter.setValue(inMsg.entPoDtlDealNetAmt, inMsg.entDealNetUnitPrcAmt.getValue().multiply(inMsg.poQty.getValue()));
        }
        if (!ZYPCommonFunc.hasValue(inMsg.entPoDtlFuncNetAmt) && ZYPCommonFunc.hasValue(inMsg.entFuncNetUnitPrcAmt)) {
            ZYPEZDItemValueSetter.setValue(inMsg.entPoDtlFuncNetAmt, inMsg.entFuncNetUnitPrcAmt.getValue().multiply(inMsg.poQty.getValue()));
        }
        // QC#21152 End
        S21ApiTBLAccessor.update(inMsg);
    }

    protected static void updatePoSerNum(PO_SER_NUMTMsg inMsg, NPZC104001PMsg pMsg, NPZC104001_poLineInfoPMsg poLineInfo, NPZC104001_serNumListPMsg serNum, String invtyLocNm) {
        ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, poLineInfo.mdseCd);
        ZYPEZDItemValueSetter.setValue(inMsg.serNum, serNum.serNum);
        ZYPEZDItemValueSetter.setValue(inMsg.serEventTs, pMsg.xxRqstTs);
        ZYPEZDItemValueSetter.setValue(inMsg.fromLocCd, pMsg.vndCd);
        ZYPEZDItemValueSetter.setValue(inMsg.fromLocNm, pMsg.vndNm);
        if (ZYPCommonFunc.hasValue(pMsg.shipToCustCd)) {
            ZYPEZDItemValueSetter.setValue(inMsg.toLocCd, pMsg.shipToCustCd);
        } else {
            ZYPEZDItemValueSetter.setValue(inMsg.toLocCd, poLineInfo.invtyLocCd);
        }
        if (ZYPCommonFunc.hasValue(pMsg.shipToLocNm)) {
            ZYPEZDItemValueSetter.setValue(inMsg.toLocNm, pMsg.shipToLocNm);
        } else {
            ZYPEZDItemValueSetter.setValue(inMsg.toLocCd, invtyLocNm);
        }
        ZYPEZDItemValueSetter.setValue(inMsg.keyInfoCd_01, poLineInfo.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(inMsg.keyInfoCd_02, poLineInfo.cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(inMsg.keyInfoCd_03, poLineInfo.cpoDtlLineSubNum);
        ZYPEZDItemValueSetter.setValue(inMsg.keyInfoCd_04, pMsg.poOrdNum);
        ZYPEZDItemValueSetter.setValue(inMsg.keyInfoCd_05, poLineInfo.poOrdDtlLineNum);
        ZYPEZDItemValueSetter.setValue(inMsg.serNumSendFlg, ZYPConstant.FLG_OFF_N);
        S21ApiTBLAccessor.update(inMsg);
    }

    protected static void updatePoAcct(PO_ACCTTMsg inMsg, NPZC104001_poAcctInfoPMsg poAcctInfoList) {
        ZYPEZDItemValueSetter.setValue(inMsg.coaCmpyCd, poAcctInfoList.coaCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaAfflCd, poAcctInfoList.coaAfflCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaBrCd, poAcctInfoList.coaBrCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaChCd, poAcctInfoList.coaChCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaCcCd, poAcctInfoList.coaCcCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaAcctCd, poAcctInfoList.coaAcctCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaProdCd, poAcctInfoList.coaProdCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaProjCd, poAcctInfoList.coaProjCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaExtnCd, poAcctInfoList.coaExtnCd);
        S21ApiTBLAccessor.update(inMsg);
    }

    protected static void clearSendTs(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum) {
        PO_DTLTMsg inMsg = new PO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdNum, poOrdNum);
        inMsg.poSendTs.clear();
        S21ApiTBLAccessor.update(inMsg);
    }

    protected static void updatePoCancel(POTMsg inMsg, NPZC104001PMsg pMsg) {
        if (ZYPCommonFunc.hasValue(pMsg.poSendFlg)) {
            ZYPEZDItemValueSetter.setValue(inMsg.poSendFlg, pMsg.poSendFlg);
        // START 04/13/2020 T.Ogura [QC#56385,DEL]
//        } else {
//            ZYPEZDItemValueSetter.setValue(inMsg.poSendFlg, ZYPConstant.FLG_OFF_N);
        // END   04/13/2020 T.Ogura [QC#56385,DEL]
        }
        if (ZYPCommonFunc.hasValue(pMsg.poPrintFlg)) {
            ZYPEZDItemValueSetter.setValue(inMsg.poPrintFlg, pMsg.poPrintFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(inMsg.poPrintFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(inMsg.vndIssOrdNum, pMsg.vndIssOrdNum);
        S21ApiTBLAccessor.update(inMsg);
    }

    protected static void updateAdditionalPoForSend(POTMsg inMsg, NPZC104001PMsg pMsg) {
        ZYPEZDItemValueSetter.setValue(inMsg.trsmtMethTpCd, pMsg.trsmtMethTpCd);
        ZYPEZDItemValueSetter.setValue(inMsg.sendPoFaxNum, pMsg.sendPoFaxNum);
        ZYPEZDItemValueSetter.setValue(inMsg.sendPoEmlAddr, pMsg.sendPoEmlAddr);
        ZYPEZDItemValueSetter.setValue(inMsg.poSendTs, pMsg.poSendTs);
        ZYPEZDItemValueSetter.setValue(inMsg.eipRptRqstPk, pMsg.eipRptRqstPk);

        S21ApiTBLAccessor.update(inMsg);
    }

    protected static void updateDsPoDtlForSend(PO_DTLTMsg inMsg, NPZC104001PMsg pMsg, NPZC104001_poLineInfoPMsg poLineInfo) {

        ZYPEZDItemValueSetter.setValue(inMsg.vndInvtyLocCd, poLineInfo.vndInvtyLocCd);
        ZYPEZDItemValueSetter.setValue(inMsg.vndIssPoOrdNum, poLineInfo.vndIssPoOrdNum);
        ZYPEZDItemValueSetter.setValue(inMsg.proNum, poLineInfo.proNum);
        ZYPEZDItemValueSetter.setValue(inMsg.vndPoAckStsCd, poLineInfo.vndPoAckStsCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poSendTs, poLineInfo.poSendTs);

        S21ApiTBLAccessor.update(inMsg);
    }

    protected static PRCH_REQTMsg getPrchReq(String glblCmpyCd, String prchReqNum) {
        PRCH_REQTMsg inMsg = new PRCH_REQTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.prchReqNum, prchReqNum);
        return (PRCH_REQTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    protected static List<Map<String, BigDecimal>> getSvcMachMstrPk(String glblCmpyCd, String poOrdNum, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NPZC104001Constant.GLBL_CMPY_CD, glblCmpyCd);
        param.put(NPZC104001Constant.PO_ORD_NUM, poOrdNum);
        param.put(NPZC104001Constant.SVC_MACH_MAINT_AVAL_FLG, ZYPConstant.FLG_OFF_N);
        List<Map<String, BigDecimal>> result = (List<Map<String, BigDecimal>>) ssmBatchClient.queryObjectList(NPZC104001Constant.GET_SVC_MACH_MSTR_PK, param);
        return result;
    }

    protected static Map<String, Object> getSceOrdTp(String glblCmpyCd, String sceOrdTpCd, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NPZC104001Constant.GLBL_CMPY_CD, glblCmpyCd);
        param.put(NPZC104001Constant.SCE_ORD_TP_CD, sceOrdTpCd);
        param.put(NPZC104001Constant.INBD_OTBD_CD, INBD_OTBD.OUTBOUND);
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject(NPZC104001Constant.GET_SCE_ORD_TP, param);
        return result;
    }

    protected static SHPG_SVC_LVLTMsg getShpgSvcLvl(String glblCmpyCd, String shpgSvcLvlCd) {
        SHPG_SVC_LVLTMsg inMsg = new SHPG_SVC_LVLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.shpgSvcLvlCd, shpgSvcLvlCd);
        return (SHPG_SVC_LVLTMsg) S21CacheTBLAccessor.findByKey(inMsg);
    }

    protected static String getPoMsgTxt(String glblCmpyCd, String poMsgTpCd, int poMsgSegId, String poOrdNum, S21SsmBatchClient ssmBatchClient) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(NPZC104001Constant.GLBL_CMPY_CD, glblCmpyCd);
        param.put(NPZC104001Constant.PO_MSG_TP_CD, poMsgTpCd);
        param.put(NPZC104001Constant.PO_MSG_SEG_ID, poMsgSegId);
        param.put(NPZC104001Constant.PO_ORD_NUM, poOrdNum);
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject(NPZC104001Constant.GET_PO_MSG_TXT, param);
        if (result == null) {
            return "";
        }
        return (String) result.get(NPZC104001Constant.PO_MSG_TXT);
    }

    protected static String getSellToCustCd(String glblCmpyCd, String shipToCustCd) {
        SHIP_TO_CUSTTMsg inMsg = new SHIP_TO_CUSTTMsg();
        inMsg.setSQLID("004");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("shipToCustCd01", shipToCustCd);
        SHIP_TO_CUSTTMsgArray resultList = (SHIP_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (resultList != null && resultList.getValidCount() > 0) {
            return resultList.no(0).sellToCustCd.getValue();
        } else {
            return null;
        }
    }

    protected static List<Map<String, Object>> getPoDtlMap(String glblCmpyCd, String poOrdNum, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NPZC104001Constant.GLBL_CMPY_CD, glblCmpyCd);
        param.put(NPZC104001Constant.PO_ORD_NUM, poOrdNum);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getPoDtlMap", param);
    }

    /**
     * @param glblCmpyCd
     * @param poOrdNum
     * @param ssmBatchClient
     * @return
     */
    protected static Map<String, Object> getPoDtlMapSendPo(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NPZC104001Constant.GLBL_CMPY_CD, glblCmpyCd);
        param.put(NPZC104001Constant.PO_ORD_NUM, poOrdNum);
        param.put(NPZC104001Constant.PO_ORD_DTL_LINE_NUM, poOrdDtlLineNum);
        return (Map<String, Object>) ssmBatchClient.queryObject("getPoDtlMap", param);
    }

    /**
     * checkBuyBackPoConf
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @return SVC_MACH_MSTRTMsg
     */
    protected static SVC_MACH_MSTRTMsg getSvcMachMstr(String glblCmpyCd, BigDecimal svcMachMstrPk) {

        SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.svcMachMstrPk, svcMachMstrPk);

        return (SVC_MACH_MSTRTMsg) S21CacheTBLAccessor.findByKey(svcMachMstrTMsg);
    }

    // START 2022/03/24 A.Cullano [QC#59359, ADD]
    /**
     * Get the PK for a non-serial item
     * @param glblCmpyCd String
     * @param wkPoDtl PO_DTLTMsg
     * @param ssmBatchClient interface
     * @return List<BigDecimal>
     */
    protected static List<BigDecimal> getSvcMachMstrBuyBack(String glblCmpyCd, PO_DTLTMsg wkPoDtl, S21SsmBatchClient ssmBatchClient) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("mdseCd", wkPoDtl.mdseCd.getValue());
        queryParam.put("curLocNum", wkPoDtl.srcRtlWhCd.getValue() + wkPoDtl.srcRtlSwhCd.getValue());
        queryParam.put("stkStsCd", wkPoDtl.fromStkStsCd.getValue());

        return (List<BigDecimal>) ssmBatchClient.queryObjectList("getSvcMachMstrBuyBack", queryParam);
    }

    /**
     * Get SO Info for Buy Back process
     * @param glblCmpyCd String
     * @param soNum String
     * @param svcMachMstrPk BigDecimal
     * @param ssmBatchClient interface
     * @return SoInfo
     */
    protected static SoInfo getSoInfoBuyBack(String glblCmpyCd, String soNum, String poOrdDtlLineNum, BigDecimal svcMachMstrPk, S21SsmBatchClient ssmBatchClient) {

        // Get SoSerial Info
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("soNum", soNum);
        queryParam.put("svcMachMstrPk", svcMachMstrPk);
        queryParam.put("poOrdDtlLineNum", poOrdDtlLineNum);
        SoInfo soInfo = (SoInfo) ssmBatchClient.queryObject("getSoInfoBuyBack", queryParam);

        return soInfo;
    }

    /**
     * Get PO Serial Num for Buy Back process
     * @param glblCmpyCd String
     * @param soNum String
     * @param ssmBatchClient interface
     * @return SoInfo
     */
    protected static Map<String, Object> getPoSerNumBuyBack(String glblCmpyCd, PO_DTLTMsg wkPoDtl, S21SsmBatchClient ssmBatchClient) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("mdseCd", wkPoDtl.mdseCd.getValue());
        queryParam.put("poOrdNum", wkPoDtl.poOrdNum.getValue());
        return (Map<String, Object>) ssmBatchClient.queryObject("getPoSerNumBuyBack", queryParam);
    }
    // END 2022/03/24 A.Cullano [QC#59359, ADD]

    protected static List<BigDecimal> getPoSerNumPkList(String glblCmpyCd, String poOrdNum, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NPZC104001Constant.GLBL_CMPY_CD, glblCmpyCd);
        param.put(NPZC104001Constant.PO_ORD_NUM, poOrdNum);
        return (List<BigDecimal>) ssmBatchClient.queryObjectList("getPoSerNumPkList", param);
    }

    // QC#13163 Start
    protected static String getVndPmtTermCd(String glblCmpyCd, String vndPmtTermDescTxt, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> param = new HashMap<String, String>();
        param.put(NPZC104001Constant.GLBL_CMPY_CD, glblCmpyCd);
        param.put(NPZC104001Constant.VND_PMT_TERM_DESC_TXT, vndPmtTermDescTxt);
        return (String) ssmBatchClient.queryObject("getVndPmtTermCd", param);
    }
    // QC#13163 End

    // QC#8424
    protected static String searchPoStatus(String glblCmpyCd, String poOrdNum, S21SsmBatchClient ssmBatchClient) {

        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("poOrdNum", poOrdNum);

        return (String) ssmBatchClient.queryObject("searchPoStatus", param);
    }
    
    /** QC#18671
     * getMdseFromSupplierItem
     * @param glblCmpyCd
     * @param shipToCustCd
     * @param ssmBatchClient
     * @return
     */
    protected static List<Map<String, Object>> getMdseFromSupplierItem(String glblCmpyCd, String vndCd, String mdseCd, S21SsmBatchClient ssmBatchClient) {
    	 Map<String, Object> params = new HashMap<String, Object>();
         params.put("glblCmpyCd", glblCmpyCd);
         params.put("vndCd", vndCd);
         params.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
         params.put("mdseCd", mdseCd);

         return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getMdseFromSupplierItem", params);
    }

    // START 2018/12/14 M.Naito [QC#29027,ADD]
    protected static SHPG_PLNTMsg getShpgPlnTMsgForDropShip(String glblCmpyCd, String trxHdrNum, String trxLineNum, String trxLineSubNum, String trxSrcTpCd) {
        SHPG_PLNTMsg inMsg = new SHPG_PLNTMsg();
        inMsg.setSQLID("008");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("trxHdrNum01", trxHdrNum);
        inMsg.setConditionValue("trxLineNum01", trxLineNum);
        inMsg.setConditionValue("trxLineSubNum01", trxLineSubNum);
        inMsg.setConditionValue("trxSrcTpCd01", trxSrcTpCd);

        SHPG_PLNTMsgArray resultList = (SHPG_PLNTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        SHPG_PLNTMsg result = null;
        SHPG_PLNTMsg shipOrPOPrintedesult = null;

        if (resultList != null && resultList.getValidCount() > 0) {
            if (resultList.getValidCount() != 1) {
                for (int i = 0; i < resultList.getValidCount(); i++) {
                    SHPG_PLNTMsg tMsg = (SHPG_PLNTMsg) resultList.get(i);
                    // QC#51175
                    if (SHPG_STS.VALIDATED.equals(tMsg.shpgStsCd.getValue())) {
                        result = tMsg;
                    } else if(SHPG_STS.P_OR_O_PRINTED.equals(tMsg.shpgStsCd.getValue()) || SHPG_STS.SHIPPED.equals(tMsg.shpgStsCd.getValue())) {
                        shipOrPOPrintedesult = tMsg;
                    }
                }
            } else {
                result = (SHPG_PLNTMsg) resultList.get(0);
            }
        }

        if (result == null && shipOrPOPrintedesult != null) {
            result = shipOrPOPrintedesult;
        }
        return result;
    }
    // END 2018/12/14 M.Naito [QC#29027,ADD]

    // START 03/02/2020 T.Ogura [QC#55920,ADD]
    protected static Integer getPoSerNumPk(NPZC104001PMsg pMsg, String serNum, String mdseCd, S21SsmBatchClient ssmBatchClient) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("serNum", serNum);
        param.put("mdseCd", mdseCd);
        param.put("poOrdNum", pMsg.poOrdNum.getValue());
        String[] poLineStsList = {PO_LINE_STS.CLOSED , PO_LINE_STS.CANCELLED};
        param.put("PO_LINE_STS_CD", poLineStsList);
        return (Integer) ssmBatchClient.queryObject("getPoSerNumPk", param);
    }
    // END   03/02/2020 T.Ogura [QC#55920,ADD]

    // START 04/06/2020 T.Ogura [QC#56390,ADD]
    protected static String getOpenPoFlg(NPZC104001PMsg pMsg, String poOrdDtlLineNum, S21SsmBatchClient ssmBatchClient) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("poOrdNum", pMsg.poOrdNum.getValue());
        param.put("poOrdDtlLineNum", poOrdDtlLineNum);
        return (String) ssmBatchClient.queryObject("getOpenPoFlg", param);
    }
    // END   04/06/2020 T.Ogura [QC#56390,ADD]

}
