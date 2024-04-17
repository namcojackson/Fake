/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC004001.common;

import static com.canon.cusa.s21.api.NMZ.NMZC004001.constant.NMZC004001Constant.AMER_MDSE_CLS_TP_CD_A;
import static com.canon.cusa.s21.api.NMZ.NMZC004001.constant.NMZC004001Constant.ERR_MAX_CNT;
import static com.canon.cusa.s21.api.NMZ.NMZC004001.constant.NMZC004001Constant.LG_8;
import static com.canon.cusa.s21.api.NMZ.NMZC004001.constant.NMZC004001Constant.MSG_KIND_E;
import static com.canon.cusa.s21.api.NMZ.NMZC004001.constant.NMZC004001Constant.MSG_KIND_W;
import static com.canon.cusa.s21.api.NMZ.NMZC004001.constant.NMZC004001Constant.NMAM8304E;
import static com.canon.cusa.s21.api.NMZ.NMZC004001.constant.NMZC004001Constant.NMAM8305W;
import static com.canon.cusa.s21.api.NMZ.NMZC004001.constant.NMZC004001Constant.NMZM0163E;
import static com.canon.cusa.s21.api.NMZ.NMZC004001.constant.NMZC004001Constant.DB_PARAM_SRCH_OPT_APL_ID;
import static com.canon.cusa.s21.api.NMZ.NMZC004001.constant.NMZC004001Constant.DB_PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.api.NMZ.NMZC004001.constant.NMZC004001Constant.DB_PARAM_VND_CD;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMessageInfo;
import parts.common.EZDPBigDecimalItem;
import parts.common.EZDPItem;
import parts.common.EZDPStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AMER_MSTRTMsg;
import business.db.AMER_MSTRTMsgArray;
import business.db.COA_ACCTTMsg;
import business.db.COA_PROJTMsg;
import business.db.CTRYTMsg;
import business.db.DS_CMSN_GRPTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_ITEM_STSTMsg;
import business.db.MDSE_ITEM_TPTMsg;
import business.db.MDSE_TP_VAL_SETTMsg;
import business.db.MDSE_TP_VAL_SETTMsgArray;
import business.db.MRP_INFOTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.PKG_UOM_CLSTMsg;
import business.db.PRC_CATGTMsg;
import business.db.PROD_CTRLTMsg;
import business.db.SLS_MAT_GRPTMsg;
import business.parts.NMZC004001PMsg;
import business.parts.NMZC004001_xxErrItemListPMsg;
import business.parts.NMZC004002PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC004001.NMZC004001Query;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre> 
 * NMZC004001CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/14/2016   Fujitsu         C.Tanaka        Create          N/A
 * 02/26/2016   SRAA            K.Aratani       Update          QC3767, QC4507, V1.5, V1.6
 * 05/18/2016   SRAA            K.Aratani       Update          QC#4203
 * 06/27/2016   SRAA            K.Aratani       Update          QC#9823, QC#10129
 * 01/23/2017   Fujitsu         Y.Kanefusa      Update          QC#17260
 * 01/24/2017   Fujitsu         S.Yamamoto      Update          QC#17078
 * 04/03/2018   CITS            K.Ogino         Update          Sol#477(QC21587)
 * 08/09/2019   CITS            R.Shimamoto     Update          QC#52184
 * 04/07/2023   CITS            F.Komaki        Update          QC#61371
 *</pre>
 */
public class NMZC004001CommonLogic {

    /** Message Kind */
    private String msgKind;

    /** Previous Index */
    private int preIdx = 0;

    /** Error Count */
    private int errCnt = 0;

    /**
     * Set Error Message
     * @param param NMZC004001PMsg
     * @param idx int
     * @param errCd String
     * @param errVal String[]
     * @return boolean
     */
    public boolean setErrorMessage(NMZC004001PMsg param, int idx, String errCd, String[] errVal) {

        if (errCnt > ERR_MAX_CNT) {
            return false;
        }

        if (preIdx != idx) {
            errCnt = 0;
            preIdx = idx;
        }

        if (msgKind == null) {
            if (MSG_KIND_E.equals(String.valueOf(errCd.charAt(errCd.length() - 1)))) {
                msgKind = MSG_KIND_E;
            } else {
                msgKind = MSG_KIND_W;
            }
        } else {
            if (MSG_KIND_W.equals(msgKind) && MSG_KIND_E.equals(String.valueOf(errCd.charAt(errCd.length() - 1)))) {
                msgKind = MSG_KIND_E;
            }
        }

        EZDMessageInfo msgInfo;
        if (errVal == null) {
            msgInfo = new EZDMessageInfo(errCd);
        } else {
            msgInfo = new EZDMessageInfo(errCd, errVal);
        }

        int len = param.xxErrItemList.getValidCount();
        NMZC004001_xxErrItemListPMsg xxErrItem = param.xxErrItemList.no(len);
        String mdseCd = param.NMZC004002PMsg.no(idx).mdseCd_M.getValue();

        if (mdseCd != null) {
            ZYPEZDItemValueSetter.setValue(xxErrItem.mdseCd, mdseCd);
        }
        ZYPEZDItemValueSetter.setValue(xxErrItem.appMsgTxt, msgInfo.getMessage());
        param.xxErrItemList.setValidCount(++len);
        errCnt++;

        if (errCnt >= ERR_MAX_CNT) {
            return false;
        }

        return true;
    }

    /**
     * Set Message Map
     * @param msgMap S21ApiMessageMap
     */
    public void setMsgMap(S21ApiMessageMap msgMap) {
        if (MSG_KIND_W.equals(msgKind)) {
            msgMap.addXxMsgId(NMAM8305W);
        } else if (MSG_KIND_E.equals(msgKind)) {
            msgMap.addXxMsgId(NMAM8304E);
        }
    }

    /**
     * Check if error count is already max.
     * @param idx int
     * @param len int
     * @return boolean
     */
    public boolean isErrMax(int idx, int len) {
        if (preIdx != idx) {
            errCnt = 0;
            preIdx = idx;
        }

        if (errCnt >= len) {
            return false;
        }
        return true;
    }

    /**
     * Check if Mandatory field is set
     * @param obj EZDPItem
     * @param param NMZC004001PMsg
     * @param idx int
     * @param msgParam String[]
     * @return boolean
     */
    public boolean hasMandatory(EZDPItem obj, NMZC004001PMsg param, int idx, String[] msgParam) {
        if (obj instanceof EZDPStringItem) {
            obj = (EZDPStringItem) obj;
        } else if (obj instanceof EZDPBigDecimalItem) {
            obj = (EZDPBigDecimalItem) obj;
        }

        if (!ZYPCommonFunc.hasValue(obj)) {
            if (!setErrorMessage(param, idx, NMZM0163E, msgParam)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Validate flag
     * @param obj EZDPStringItem
     * @return boolean
     */
    public boolean verifyFlag(EZDPStringItem obj) {
        if (!ZYPCommonFunc.hasValue(obj)) {
            ZYPEZDItemValueSetter.setValue(obj, ZYPConstant.FLG_OFF_N);
        } else {
            String flg = obj.getValue();
            if (!ZYPConstant.FLG_ON_Y.equals(flg) && !ZYPConstant.FLG_OFF_N.equals(flg)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get data from American Master
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return AMER_MSTRTMsgArray
     */
    public AMER_MSTRTMsgArray getAmerMstr(String glblCmpyCd, String mdseCd) {
        AMER_MSTRTMsg amerMstrTMsg = new AMER_MSTRTMsg();
        amerMstrTMsg.setSQLID("001");
        amerMstrTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        amerMstrTMsg.setConditionValue("amerMdseCd01", mdseCd);
        AMER_MSTRTMsgArray amerMstrTMsgArray = (AMER_MSTRTMsgArray) EZDTBLAccessor.findByCondition(amerMstrTMsg);
        return amerMstrTMsgArray;
    }

    /**
     * Get data from Merchandise Type Value Set
     * @param glblCmpyCd String
     * @param mdseTpCtxTpCd String
     * @param coaMdseTpCd String
     * @return MDSE_TP_VAL_SETTMsgArray
     */
    public Map<String, Object> getMdseTpValSet(String glblCmpyCd, String mdseTpCtxTpCd, String mdseItemTpCd, String coaMdseTpCd) {
    	if (!ZYPCommonFunc.hasValue(mdseItemTpCd) || !ZYPCommonFunc.hasValue(coaMdseTpCd)) {
    		return null;
    	}
        Map<String, Object> map = NMZC004001Query.getInstance().getMdseTpValSet(glblCmpyCd, mdseTpCtxTpCd, mdseItemTpCd, coaMdseTpCd);
        return map;
    }

    /**
     * Get data from Merchandise Type Value Set
     * @param glblCmpyCd String
     * @param mdseTpCtxTpCd String
     * @param coaMdseTpCd String
     * @return MDSE_TP_VAL_SETTMsgArray
     */
    public MDSE_TP_VAL_SETTMsgArray getMdseTpValSet(String glblCmpyCd, String mdseTpCtxTpCd, String coaMdseTpCd) {
        MDSE_TP_VAL_SETTMsg mdseTpValSetTMsg = new MDSE_TP_VAL_SETTMsg();
        mdseTpValSetTMsg.setSQLID("001");
        mdseTpValSetTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        mdseTpValSetTMsg.setConditionValue("mdseTpCtxTpCd01", mdseTpCtxTpCd);
        mdseTpValSetTMsg.setConditionValue("coaMdseTpCd01", coaMdseTpCd);
        mdseTpValSetTMsg.setConditionValue("coaMdseTpCd01", coaMdseTpCd);
        MDSE_TP_VAL_SETTMsgArray mdseTpValSetTMsgArray = (MDSE_TP_VAL_SETTMsgArray) EZDTBLAccessor.findByCondition(mdseTpValSetTMsg);
        return mdseTpValSetTMsgArray;
    }


    /**
     * Get Merchandise
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return MDSETMsg
     */
    public MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {
        MDSETMsg tMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseCd);
        tMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return tMsg;
    }

    /**
     * Get Merchandise Item Status
     * @param glblCmpyCd String
     * @param mdseItemStsCd String
     * @return MDSE_ITEM_STSTMsg
     */
    public MDSE_ITEM_STSTMsg getMdseItemSts(String glblCmpyCd, String mdseItemStsCd) {
        MDSE_ITEM_STSTMsg tMsg = new MDSE_ITEM_STSTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseItemStsCd, mdseItemStsCd);
        tMsg = (MDSE_ITEM_STSTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return tMsg;
    }

    /**
     * Get Merchandise Item Type
     * @param glblCmpyCd String
     * @param mdseItemTpCd String
     * @return MDSE_ITEM_STSTMsg
     */
    public MDSE_ITEM_TPTMsg getMdseItemTp(String glblCmpyCd, String mdseItemTpCd) {
        MDSE_ITEM_TPTMsg tMsg = new MDSE_ITEM_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseItemTpCd, mdseItemTpCd);
        tMsg = (MDSE_ITEM_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return tMsg;
    }

    /**
     * Get Chart of Account Project
     * @param glblCmpyCd String
     * @param coaProjCd String
     * @return COA_PROJTMsg
     */
    public COA_PROJTMsg getCoaProj(String glblCmpyCd, String coaProjCd) {
        COA_PROJTMsg tMsg = new COA_PROJTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.coaProjCd, coaProjCd);
        tMsg = (COA_PROJTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return tMsg;
    }

    /**
     * Get Order Take Merchandise
     * @param glblCmpyCd String
     * @param ordTakeMdseCd String
     * @return ORD_TAKE_MDSETMsg
     */
    public ORD_TAKE_MDSETMsg getOrdTakeMdse(String glblCmpyCd, String ordTakeMdseCd) {
        // QC#17260 2017/01/23 Add Start
        if(ordTakeMdseCd.length() > 8){
            return null;
        }
        // QC#17260 2017/01/23 Add End
        ORD_TAKE_MDSETMsg tMsg = new ORD_TAKE_MDSETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ordTakeMdseCd, ordTakeMdseCd);
        tMsg = (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return tMsg;
    }

    /**
     * Get Package UOM Class
     * @param glblCmpyCd String
     * @param pkgUomClsCd String
     * @return PKG_UOM_CLSTMsg
     */
    public PKG_UOM_CLSTMsg getPkgUomClsTMsg(String glblCmpyCd, String pkgUomClsCd) {
        PKG_UOM_CLSTMsg tMsg = new PKG_UOM_CLSTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.pkgUomClsCd, pkgUomClsCd);
        tMsg = (PKG_UOM_CLSTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return tMsg;
    }

    /**
     * Get Product Control
     * @param glblCmpyCd String
     * @param prodCtrlCd String
     * @return PROD_CTRLTMsg
     */
    public PROD_CTRLTMsg getProdCtrl(String glblCmpyCd, String prodCtrlCd) {
        PROD_CTRLTMsg tMsg = new PROD_CTRLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prodCtrlCd, prodCtrlCd);
        tMsg = (PROD_CTRLTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return tMsg;
        
    }

    //V1.5
    public SLS_MAT_GRPTMsg getSlsMatGrp(String glblCmpyCd, String slsMatGrpCd) {
    	SLS_MAT_GRPTMsg tMsg = new SLS_MAT_GRPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.slsMatGrpCd, slsMatGrpCd);
        tMsg = (SLS_MAT_GRPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return tMsg;
    }

    //V1.5
    public DS_CMSN_GRPTMsg getDsCmsnGrp(String glblCmpyCd, String dsCmsnGrpCd) {
    	DS_CMSN_GRPTMsg tMsg = new DS_CMSN_GRPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsCmsnGrpCd, dsCmsnGrpCd);
        tMsg = (DS_CMSN_GRPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return tMsg;
    }

    /**
     * Get Chart of Account Account
     * @param glblCmpyCd String
     * @param coaAcctCd String
     * @return COA_ACCTTMsg
     */
    public COA_ACCTTMsg getCoaAcct(String glblCmpyCd, String coaAcctCd) {
        COA_ACCTTMsg tMsg = new COA_ACCTTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.coaAcctCd, coaAcctCd);
        tMsg = (COA_ACCTTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return tMsg;
    }

    /**
     * Get Country
     * @param glblCmpyCd String
     * @param ctryCd String
     * @return CTRYTMsg
     */
    public CTRYTMsg getCtry(String glblCmpyCd, String ctryCd) {
        CTRYTMsg tMsg = new CTRYTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ctryCd, ctryCd);
        tMsg = (CTRYTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return tMsg;
    }

    /**
     * Get Price Category
     * @param glblCmpyCd String
     * @param prcCatgCd String
     * @return PRC_CATGTMsg
     */
    public PRC_CATGTMsg getPrcCatg(String glblCmpyCd, String prcCatgCd) {
        PRC_CATGTMsg tMsg = new PRC_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, prcCatgCd);
        tMsg = (PRC_CATGTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return tMsg;
    }

    /**
     * Check if 8 length Merchandise Code is able to use.
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param mdseClsTpCd String
     * @return boolean
     */
    public boolean usableMdseCd8(String glblCmpyCd, String mdseCd, String mdseClsTpCd) {
        AMER_MSTRTMsg amerMstrTMsg = new AMER_MSTRTMsg();
        amerMstrTMsg.setSQLID("005");
        amerMstrTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        amerMstrTMsg.setConditionValue("amerMdseCd01", mdseCd);
        amerMstrTMsg.setConditionValue("amerMdseClsTpCd01", mdseClsTpCd);
        AMER_MSTRTMsgArray amerMstrTMsgArray = (AMER_MSTRTMsgArray) EZDTBLAccessor.findByCondition(amerMstrTMsg);
        if (amerMstrTMsgArray.length() == 0) {
            return false;
        }
        return true;
    }

    /**
     * Get new Price Through Date
     * @param glblCmpyCd String
     * @param slsDt String
     * @param mdseCd String
     * @param msrcPrcCatgCd String
     * @param prcListEquipPrcAmt BigDecimal
     * @return Map
     */
    public Map<String, Object> getNewPrcThruDt(String glblCmpyCd, String slsDt, String mdseCd, String msrcPrcCatgCd, BigDecimal prcListEquipPrcAmt) {
        String prcItemCd = mdseCd;

        Map<String, Object> newMap = new HashMap<String, Object>();
        if (usableMdseCd8(glblCmpyCd, mdseCd, AMER_MDSE_CLS_TP_CD_A)) {
            // 01/27/2017 QC#17078 Mod Start
//          prcItemCd = mdseCd.substring(0, LG_8);
          prcItemCd = mdseCd;
          if (prcItemCd.length() > 8) {
              prcItemCd = prcItemCd.substring(0, LG_8);
          }
          // 01/27/2017 QC#17078 Mod End

            Map<String, Object> map = NMZC004001Query.getInstance().getPrcListEquip(glblCmpyCd, slsDt, msrcPrcCatgCd, prcItemCd, ZYPConstant.FLG_ON_Y);
            if (map != null) {
                if (prcListEquipPrcAmt.compareTo((BigDecimal) map.get("PRC_LIST_EQUIP_PRC_AMT")) == 0) {
                    newMap.put("OldPrcThruDt", ZYPDateUtil.addDays(slsDt, -1));
                    newMap.put("OldPrcListEquipPk", (BigDecimal) map.get("PRC_LIST_EQUIP_PK"));
                } else {
                    newMap.put("NewPrcThruDt", (String) map.get("EFF_THRU_DT"));
                }
            } else {
                map = NMZC004001Query.getInstance().getPrcListEquip(glblCmpyCd, slsDt, msrcPrcCatgCd, prcItemCd, null);
                if (map != null) {
                    newMap.put("NewPrcThruDt", (String) map.get("EFF_FROM_DT"));
                }
            }
        }

        newMap.put("PrcItemCd", prcItemCd);
        return newMap;
    }

    //QC#4203, QC#10449
    public Map<String, Object> getRtrnCtrlTpVndReln(String glblCmpyCd, String rtrnCtrlTpCd, String rtrnToVndCd, String rtrnWhCd, String slsDt) {
        Map<String, Object> map = NMZC004001Query.getInstance().getRtrnCtrlTpVndReln(glblCmpyCd, rtrnCtrlTpCd, rtrnToVndCd, rtrnWhCd, slsDt);
        return map;
    }
    public Map<String, Object> getPoVndV(String glblCmpyCd, String rtrnToVndCd, String rtrnToPrntVndCd) {
        Map<String, Object> map = NMZC004001Query.getInstance().getPoVndV(glblCmpyCd, rtrnToVndCd, rtrnToPrntVndCd);
        return map;
    }
    //QC#9823, QC#10129
    public static boolean existsCmpsn(String glblCmpyCd, String mdseCd) {
    	boolean ret = true;
        S21SsmEZDResult r = NMZC004001Query.getInstance().getCmpsn(glblCmpyCd, mdseCd);
        if (!r.isCodeNormal()) {
        	ret = false;
        }
        return ret;
    }
    //QC#16628
    public boolean existsPkgUomAndPkgUomClsReln(String glblCmpyCd, String pkgUomClsCd, String pkgUomCd) {
    	boolean ret = true;
        S21SsmEZDResult r = NMZC004001Query.getInstance().getPkgUomAndPkgUomClsReln(glblCmpyCd, pkgUomClsCd, pkgUomCd);
        if (!r.isCodeNormal()) {
            ret = false;
        }
        return ret;
    }
    // Sol#477(QC#21587)
    public List<Map<String, String>> getMrpDefItemPln(String glblCmpyCd, String slsDt, MDSETMsg mdseTMsg) {
        List<Map<String, String>> map = NMZC004001Query.getInstance().getMrpDefItemPln(glblCmpyCd, slsDt, mdseTMsg);
        return map;
    }
    public BigDecimal getMrpInfoPk(String glblCmpyCd, String mrpPlnNm) {
        BigDecimal mrpInfoPk = NMZC004001Query.getInstance().getMrpInfoPk(glblCmpyCd, mrpPlnNm);
        return mrpInfoPk;
    }
    public MRP_INFOTMsg getMrpInfo(String glblCmpyCd, BigDecimal mrpInfoPk) {
        MRP_INFOTMsg tMsg = new MRP_INFOTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mrpInfoPk, mrpInfoPk);
        tMsg = (MRP_INFOTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return tMsg;
    }
    // QC#52184
    public String getSupplierName(String glblCmpyCd, String prntVndCd) {
        String supplierName = NMZC004001Query.getInstance().getSupplierName(glblCmpyCd, prntVndCd);
        return supplierName;
    }

    // 2023/04/07 QC#61371 START
    /**
     * Get Supplier Item Code Max Length
     * @param glblCmpyCd String
     * @param vndCd String
     * @return supplierItemCodeMaxLength
     */
    public static String getSupplierItemCodeMaxLength(String glblCmpyCd, String vndCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_SRCH_OPT_APL_ID, "NPAL1230");
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_VND_CD, vndCd);

        S21SsmEZDResult result = NMZC004001Query.getInstance().getSupplierItemCodeMaxLength(ssmParam);

        if (result.isCodeNormal()) {
            return (String) result.getResultObject();
        } else {
            return null;
        }
    }
    // 2023/04/07 QC#61371 END
}
