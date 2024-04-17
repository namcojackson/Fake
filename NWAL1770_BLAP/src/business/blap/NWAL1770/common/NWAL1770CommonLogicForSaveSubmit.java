/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1770.common;

import static business.blap.NWAL1770.constant.NWAL1770Constant.BIZ_ID;
import static business.blap.NWAL1770.constant.NWAL1770Constant.DROP_SHIP_RTL_WH_CD;
import static business.blap.NWAL1770.constant.NWAL1770Constant.KEY_PKG_UOM_FOR_PRC;
import static business.blap.NWAL1770.constant.NWAL1770Constant.SHPG_CMNT_PRINT_CHK_BOX_ON;
import static business.blap.NWAL1770.constant.NWAL1770Constant.SUB_WH_CD_NEW;
import static business.blap.NWAL1770.constant.NWAL1770Constant.TARGET;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_BILL_TO_LOC;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_BILL_TO_NM;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_BILL_TO_NUM;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_BTN_PRC;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_CARR_SVC_LVL;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_CATG;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_CUST_PO_NUM;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_DAYS_VLD;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_FRT_TERMS;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_ITEM;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_LINE_CATG;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_LINE_SRC;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_PMT_TERMS;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_PRC_BASE_DT;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_PRC_LIST;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_QTY;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_QUOTE_DT;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_RSN_CD;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_SAVE;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_SELL_PRC;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_SHIP_TO_LOC;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_SHIP_TO_NM;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_SHIP_TO_NUM;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_SHPG_SVC_LVL;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_SLS_REP_NM;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_SLS_REP_NUM;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_SOLD_TO_LOC;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_SOLD_TO_NM;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_SOLD_TO_NUM;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_SRC_NM;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_SUBMIT;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_SUB_WH;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_UOM;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_WH;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM0053E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM0181E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM0311E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM0429E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM0673E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM0793W;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM8465E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWZM0900W;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWZM1338E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWZM1344E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWZM1345E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWZM1387E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWZM1388E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWZM2014E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWZM2023E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWZM2024E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWZM2025E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM0987E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM0988E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM0989E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.REFERENCE_1;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.REFERENCE_2;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.REFERENCE_3;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.REFERENCE_4;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.REFERENCE_5;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.REFERENCE_6;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.ZZM8100I;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.ZZM9000E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.ZZM9037E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1770.NWAL1770CMsg;
import business.blap.NWAL1770.NWAL1770Query;
import business.blap.NWAL1770.NWAL1770QueryForCustomer;
import business.blap.NWAL1770.NWAL1770QueryForPricing;
import business.blap.NWAL1770.NWAL1770QueryForSaveSubmit;
import business.blap.NWAL1770.NWAL1770SMsg;
import business.blap.NWAL1770.NWAL1770_ACMsg;
import business.blap.NWAL1770.NWAL1770_BCMsg;
import business.blap.NWAL1770.NWAL1770_BSMsg;
import business.blap.NWAL1770.NWAL1770_DCMsg;
import business.blap.NWAL1770.NWAL1770_ECMsg;
import business.blap.NWAL1770.NWAL1770_FSMsg;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.CTAC_TPTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SPLY_QUOTETMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NWZC167001PMsg;
import business.parts.NWZC167001_BPMsg;
import business.parts.NWZC167001_CPMsg;
import business.parts.NWZC167002PMsg;
import business.parts.NWZC167002_APMsg;
import business.parts.NWZC167002_xxMsgIdListPMsg;
import business.parts.NWZC180001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.constants.NWZC150001CpouConstant;
import com.canon.cusa.s21.api.NWZ.NWZC167001.NWZC167001;
import com.canon.cusa.s21.api.NWZ.NWZC167001.constant.NWZC167001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC180001.NWZC180001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001SalesRepValidator;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_TERM_CASH_DISC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_QUOTE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/26   Fujitsu         T.Yoshida       Create          N/A
 * 2016/05/16   Fujitsu         T.Murai         Update          S21_NA#7861
 * 2016/08/03   Fujitsu         H.Ikeda         Update          S21_NA#11657
 * 2016/08/26   Fujitsu         T.Murai         Update          S21_NA#13067
 * 2016/09/06   Fujitsu         H.Ikeda         Update          S21_NA#13917
 * 2016/09/09   Fujitsu         H.Ikeda         Update          S21_NA#11994
 * 2016/09/21   Fujitsu         H.Ikeda         Update          S21_NA#14578
 * 2016/09/27   Fujitsu         H.Ikeda         Update          S21_NA#13914
 * 2016/09/29   Fujitsu         T.Murai         Update          S21_NA#13921
 * 2016/10/06   Fujitsu         H.Ikeda         Update          S21_NA#11994
 * 2017/08/07   Fujitsu         Y.Kanefusa      Update          S21_NA#10347
 * 2017/08/09   Fujitsu         H.Nagashima     Update          S21_NA#16452
 * 2017/08/17   Fujitsu         S.Takami        Update          S21_NA#20659
 * 2017/09/11   Fujitsu         S.Takami        Update          S21_NA#21013
 * 2017/10/03   Fujitsu         S.Ohki          Update          S21_NA#21068-2
 * 2017/10/18   Fujitsu         W.Honda         Create          S21_NA#20246-1(L3 Sol#454)
 * 2017/12/20   Fujitsu         Mz.Takahashi    Update          S21_NA#20164(L3 Sol#356)/
 * 2018/02/13   Fujitsu         T.Aoi           Update          S21_NA#21165
 * 2018/02/26   Fujitsu         Hd.Sugawara     Update          S21_NA#22967
 * 2018/03/02   Fujitsu         K.Ishizuka      Update          S21_NA#22956
 * 2018/03/06   Fujitsu         T.Aoi           Update          S21_NA#24460
 * 2018/03/19   Fujitsu         A.Kosai         Update          S21_NA#24810
 * 2018/05/11   Fujitsu         T.Aoi           Update          S21_NA#22139
 * 2018/07/19   Fujitsu         M.Ishii         Update          S21_NA#26153
 * 2018/08/21   Fujitsu         T.Noguchi       Update          S21_NA#26550
 * 2018/11/28   Fujitsu         Hd.Sugawara     Update          S21_NA#29252
 * 2018/12/13   Fujitsu         K.Kato          Update          S21_NA#29315
 * 2019/01/29   Fujitsu         S.Kosaka        Update          QC#30036
 * 2019/02/15   Fujitsu         C.Hara          Update          QC#30319
 * 2019/10/03   Fujitsu         A.Kazuki        Update          QC#53595
 * 2020/06/09   CITS            K.Ogino         Update          QC#56978
 * 2022/08/18   Hitachi         H.Watanabe      Update          QC#60255
 * 2023/07/20   Hitachi         T.Fukuta        Update          CSA-QC#61467
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 * 2024/04/03   CITS            A.Shimada       Update          QC#63691
 * </pre>
 */
public class NWAL1770CommonLogicForSaveSubmit {

    /**
     * Check And Clear Code
     * @param bizMsg NWAL1770CMsg
     * @return No Error : true
     */
    public static boolean checkAndClearCode(NWAL1770CMsg bizMsg) {

        S21SsmEZDResult ssmResult = null;
        boolean isError = false;

        // For Performance
        Map<String, String> prcCatgCdMap = new HashMap<String, String>();
        Map<String, String> rtlWhCdMap = new HashMap<String, String>();
        Map<Map<String, String>, String> rtlSwhCdMap = new HashMap<Map<String, String>, String>();

        // Header
        if (!ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgDescTxt)) {
            bizMsg.splyQuoteCatgCd.clear();
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.prcCatgNm)) {
            bizMsg.prcCatgCd.clear();
        } else {
            String prcCatgCd = getPrcCatgCdFromCache(bizMsg, bizMsg.prcCatgNm.getValue(), prcCatgCdMap);
            if (ZYPCommonFunc.hasValue(prcCatgCd)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd, prcCatgCd);
            } else {
                bizMsg.prcCatgNm.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_PRC_LIST });
                isError = true;
            }
        }

        // Delivery / Payment Tab
        if (!ZYPCommonFunc.hasValue(bizMsg.frtCondDescTxt)) {
            bizMsg.frtCondCd.clear();
        } else {
            ssmResult = NWAL1770Query.getInstance().getFrtCondCd(bizMsg);
            if (ssmResult.isCodeNormal()) {
                ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd, (String) ssmResult.getResultObject());
            } else {
                bizMsg.frtCondDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_FRT_TERMS });
                isError = true;
            }
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.carrSvcLvlDescTxt)) {
            bizMsg.carrSvcLvlCd.clear();
        } else {
            ssmResult = NWAL1770Query.getInstance().getCarrSvcLvlCd(bizMsg);
            if (ssmResult.isCodeNormal()) {
                ZYPEZDItemValueSetter.setValue(bizMsg.carrSvcLvlCd, (String) ssmResult.getResultObject());
            } else {
                bizMsg.carrSvcLvlDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_CARR_SVC_LVL });
                isError = true;
            }
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.pmtTermCashDiscDescTxt)) {
            bizMsg.pmtTermCashDiscCd.clear();
        } else {
            ssmResult = NWAL1770Query.getInstance().getPmtTermCashDiscCd(bizMsg);
            if (ssmResult.isCodeNormal()) {
                ZYPEZDItemValueSetter.setValue(bizMsg.pmtTermCashDiscCd, (String) ssmResult.getResultObject());
            } else {
                bizMsg.pmtTermCashDiscDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_PMT_TERMS });
                isError = true;
            }
        }

        // Items Tab
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(i);
            if (!ZYPCommonFunc.hasValue(itemLineMsg.prcCatgNm_B)) {
                itemLineMsg.prcCatgCd_B.clear();
            } else {
                String prcCatgCd = getPrcCatgCdFromCache(bizMsg, itemLineMsg.prcCatgNm_B.getValue(), prcCatgCdMap);
                if (ZYPCommonFunc.hasValue(prcCatgCd)) {
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.prcCatgCd_B, prcCatgCd);
                } else {
                    itemLineMsg.prcCatgNm_B.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_PRC_LIST });
                    isError = true;
                }
            }

            if (!ZYPCommonFunc.hasValue(itemLineMsg.rtlWhNm_B)) {
                itemLineMsg.rtlWhCd_B.clear();
            } else {
                String rtlWhCd = getRtlWhCdFromCache(bizMsg, itemLineMsg.rtlWhNm_B.getValue(), rtlWhCdMap);
                if (ZYPCommonFunc.hasValue(rtlWhCd)) {
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.rtlWhCd_B, rtlWhCd);
                } else {
                    itemLineMsg.rtlWhNm_B.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_WH });
                    isError = true;
                }
            }

            if (!ZYPCommonFunc.hasValue(itemLineMsg.rtlWhNm_B) || !ZYPCommonFunc.hasValue(itemLineMsg.rtlSwhNm_B)) {
                itemLineMsg.rtlSwhCd_B.clear();
                itemLineMsg.rtlSwhNm_B.clear();
            } else if (ZYPCommonFunc.hasValue(itemLineMsg.rtlWhNm_B) && ZYPCommonFunc.hasValue(itemLineMsg.rtlSwhNm_B)) {
                String rtlSwhCd = getRtlSwhCdFromCache(bizMsg, itemLineMsg.rtlWhCd_B.getValue(), itemLineMsg.rtlSwhNm_B.getValue(), rtlSwhCdMap);
                if (ZYPCommonFunc.hasValue(rtlSwhCd)) {
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.rtlSwhCd_B, rtlSwhCd);
                } else {
                    itemLineMsg.rtlSwhNm_B.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_SUB_WH });
                    isError = true;
                }
            }
            // S21_NA#11994 Del Start
            // S21_NA#11994 Add Start
            //boolean isDropShipRtlWh = false;
            //if (ZYPCommonFunc.hasValue(itemLineMsg.rtlWhCd_B)) {
            //    List<String> dropShipRtlWhCd = getVarCharConstDataList(bizMsg.glblCmpyCd.getValue(), DROP_SHIP_RTL_WH_CD);
            //    isDropShipRtlWh = dropShipRtlWhCd.contains(itemLineMsg.rtlWhCd_B.getValue());
            //}
            //if (isDropShipRtlWh) {
            //    String mdseCd = getOrdTakeMdse(bizMsg.glblCmpyCd.getValue(), itemLineMsg.mdseCd_B.getValue());
            //    ZYPEZDItemValueSetter.setValue(itemLineMsg.mdseCd_B, mdseCd);
            //}
            // S21_NA#11994 Add End
            // S21_NA#11994 Del End
        }

        if (isError) {
            return false;
        }

        return true;
    }
    // S21_NA#11994 Del Start
    // S21_NA#11994 Add Start
    //private static String getOrdTakeMdse(String glblCmpyCd, String mdseCd) {
    //
    //    String mdseCd8 = null;
    //    if (8 < mdseCd.length()) {
    //        // 10MDSE -> 8MDSE
    //        mdseCd8 = S21StringUtil.subStringByLength(mdseCd, 0, 8);
    //    } else {
    //        mdseCd8 = mdseCd;
    //    }
    //    
    //    ORD_TAKE_MDSETMsg tMsg = new ORD_TAKE_MDSETMsg();
    //    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
    //    ZYPEZDItemValueSetter.setValue(tMsg.ordTakeMdseCd, mdseCd8);
    //    tMsg = (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);
    //    if (tMsg != null) {
    //        return tMsg.mdseCd.getValue();
    //    }
    //    return mdseCd;
    //}
    // S21_NA#11994 Add End
    // S21_NA#11994 Del End
    /**
     * Prepare Set To Component
     * @param bizMsg NWAL1770CMsg
     */
    public static void prepareSetToConponent(NWAL1770CMsg bizMsg) {

        NWAL1770_BCMsg parentItemLine = null;
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1770_BCMsg currentItemLine = bizMsg.B.no(i);
            if (!ZYPCommonFunc.hasValue(currentItemLine.dplyQuoteLineSubNum_B)) {
                parentItemLine = currentItemLine;
            } else {
                // Component
                if (parentItemLine != null) {
                    copyParentValue(parentItemLine, currentItemLine);
                }
            }
        }
    }

    /**
     * Check Mandatory Field
     * @param bizMsg     NWAL1770CMsg
     * @param isCallSave boolean
     * @return No Error : true
     */
    // Mod Start 2016/09/27 S21_NA#13914
    //public static boolean checkMandatoryField(NWAL1770CMsg bizMsg) {
    public static boolean checkMandatoryField(NWAL1770CMsg bizMsg, boolean isCallSave) {
    // Mod End 2016/09/27 S21_NA#13914
        // Mod Start 2016/09/27 S21_NA#13914
        if (!checkMandatoryHeader(bizMsg, isCallSave)) {
        //if (!checkMandatoryHeader(bizMsg)) {
         // Mod End 2016/09/27 S21_NA#13914
            return false;
        }

        if (!checkMandatoryCustomerTab(bizMsg)) {
            return false;
        }

        if (!checkMandatoryDelyPmtTab(bizMsg)) {
            return false;
        }

        if (!checkMandatoryItemsTab(bizMsg)) {
            return false;
        }
        // S21_NA#13977 Add Start
        if (!checkMandatoryAdditionalTab(bizMsg)) {
            return false;
        }
        // S21_NA#13977 Add End

        return true;
    }

    /**
     * Check Mandatory Field For Header
     * @param bizMsg     NWAL1770CMsg
     * @param isCallSave boolean
     * @return No Error : true
     */
    //private static boolean checkMandatoryHeader(NWAL1770CMsg bizMsg) {
    private static boolean checkMandatoryHeader(NWAL1770CMsg bizMsg, boolean isCallSave) {
        boolean isNormal = true;

        if (!ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgDescTxt)) {
            bizMsg.dsOrdCatgDescTxt.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_CATG });
            isNormal = false;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
            bizMsg.dsOrdTpCd.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_RSN_CD });
            isNormal = false;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.splyQuoteSrcTpCd)) {
            bizMsg.splyQuoteSrcTpCd.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_SRC_NM });
            isNormal = false;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.splyQuoteDt)) {
            bizMsg.splyQuoteDt.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_QUOTE_DT });
            isNormal = false;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.splyQuoteVldDaysAot)) {
            bizMsg.splyQuoteVldDaysAot.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_DAYS_VLD });
            isNormal = false;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.slsRepTocNm)) {
            bizMsg.slsRepTocNm.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_SLS_REP_NM });
            isNormal = false;
        }

        // S21_NA#7861 Mod Start
        // if (!ZYPCommonFunc.hasValue(bizMsg.slsRepPsnCd)) {
        // bizMsg.slsRepPsnCd.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_SLS_REP_CD });
        // isNormal = false;
        // }
        if (!ZYPCommonFunc.hasValue(bizMsg.psnNum)) {
            bizMsg.psnNum.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_SLS_REP_NUM });
            isNormal = false;
        }
        // S21_NA#7861 Mod End

        if (!ZYPCommonFunc.hasValue(bizMsg.prcCatgNm)) {
            bizMsg.prcCatgNm.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_PRC_LIST });
            isNormal = false;
        }

        // 2016/08/03 S21_NA#11657 Add Start
        // 2016/09/27 S21_NA#13914 Mod Start
        if (!isCallSave && checkPoNum(bizMsg)) {
        //if (checkPoNum(bizMsg)) {
        // 2016/09/27 S21_NA#13914 Mod End
            bizMsg.custIssPoNum.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_CUST_PO_NUM });
            isNormal = false;
        }
        // 2016/08/03 S21_NA#11657 Add End

        return isNormal;
    }

    /**
     * Check Mandatory Field For Customer
     * @param bizMsg NWAL1770CMsg
     * @return No Error : true
     */
    private static boolean checkMandatoryCustomerTab(NWAL1770CMsg bizMsg) {

        boolean isNormal = true;

        if (!ZYPCommonFunc.hasValue(bizMsg.billToCustAcctCd)) {
            bizMsg.billToCustAcctCd.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_BILL_TO_NUM });
            isNormal = false;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.billToCustCd)) {
            bizMsg.billToCustCd.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_BILL_TO_LOC });
            isNormal = false;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.billToCustAcctNm)) {
            bizMsg.billToCustAcctNm.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_BILL_TO_NM });
            isNormal = false;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.shipToCustAcctCd)) {
            bizMsg.shipToCustAcctCd.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_SHIP_TO_NUM });
            isNormal = false;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {
            bizMsg.shipToCustCd.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_SHIP_TO_LOC });
            isNormal = false;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.shipToCustAcctNm)) {
            bizMsg.shipToCustAcctNm.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_SHIP_TO_NM });
            isNormal = false;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.sellToCustCd)) {
            bizMsg.sellToCustCd.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_SOLD_TO_NUM });
            isNormal = false;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.soldToCustLocCd)) {
            bizMsg.soldToCustLocCd.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_SOLD_TO_LOC });
            isNormal = false;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.soldToCustAcctNm)) {
            bizMsg.soldToCustAcctNm.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_SOLD_TO_NM });
            isNormal = false;
        }

        // Add Start 2017/12/20 QC#20164(L3 Sol#356)
        if (!checkContact(bizMsg)){
            isNormal = false;
        }
        // Add End 2017/12/20 QC#20164(L3 Sol#356)

        return isNormal;
    }

    /**
     * Check Mandatory Field For Delivery Payment Tab
     * @param bizMsg NWAL1770CMsg
     * @return No Error : true
     */
    private static boolean checkMandatoryDelyPmtTab(NWAL1770CMsg bizMsg) {

        boolean isNormal = true;

        if (!ZYPCommonFunc.hasValue(bizMsg.frtCondDescTxt)) {
            bizMsg.frtCondDescTxt.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_FRT_TERMS });
            isNormal = false;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.shpgSvcLvlCd)) {
            bizMsg.shpgSvcLvlCd.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_SHPG_SVC_LVL });
            isNormal = false;
        }

        return isNormal;
    }

    /**
     * Check Mandatory Field For Items Tab
     * @param bizMsg NWAL1770CMsg
     * @return No Error : true
     */
    private static boolean checkMandatoryItemsTab(NWAL1770CMsg bizMsg) {

        boolean isNormal = true;
      //START 2024/04/03 [CSA-QC#63691,ADD]
        HashMap<Integer, String> mapIndex = new HashMap<Integer, String>();
        boolean valueFlag = false;
        boolean valueNextBlankFlag = false;
      //END 2024/04/03 [CSA-QC#63691,ADD]
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(i);
          //START 2024/04/03 [CSA-QC#63691,ADD]
            boolean isItemBlank = !ZYPCommonFunc.hasValue(itemLineMsg.mdseCd_B);
            boolean isQtyBlank = !ZYPCommonFunc.hasValue(itemLineMsg.ordCustUomQty_B);
            boolean isBlank = isItemBlank && isQtyBlank;
                if (isBlank) {
                    if (valueFlag) {
                        // The next line is not blank.
                        valueNextBlankFlag = true;
                    }
                } else {
                        // The current line has a value.
                        valueFlag = true;
                        mapIndex.put(i, TARGET);
                        if (valueNextBlankFlag) {
                            // While there are incomplete line.
                            for (int j = i - 1; j >= 0; j--) {
                                NWAL1770_BCMsg itemPrev = bizMsg.B.no(j);
                                  boolean isItemBlankPrev = !ZYPCommonFunc.hasValue(itemPrev.mdseCd_B);
                                  boolean isQtyBlankPrev = !ZYPCommonFunc.hasValue(itemPrev.ordCustUomQty_B);
                                  boolean isBlankPrev = isItemBlankPrev && isQtyBlankPrev;
                                if (isBlankPrev) {
                                    // Set incomplete line into mapIndex.
                                    mapIndex.put(j, TARGET);
                                } else {
                                    // When the previous line has a value, stop setting the validation target.
                                    valueNextBlankFlag = false;
                                    break;
                                }
                            }
                        }
                    }
                    //Ensure all the indexes.
                if (!mapIndex.containsKey(i)) {
                    mapIndex.put(i, "");
                } 
        }
        for (Map.Entry<Integer, String> entry : mapIndex.entrySet()) {
            if(entry.getKey() == 0 && entry.getValue().isEmpty()){
                NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(entry.getKey());
        //END 2024/04/03 [CSA-QC#63691,ADD]

            if (!ZYPCommonFunc.hasValue(itemLineMsg.mdseCd_B)) {
                itemLineMsg.mdseCd_B.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_ITEM });
                isNormal = false;
            }

            if (!ZYPCommonFunc.hasValue(itemLineMsg.ordCustUomQty_B)) {
                itemLineMsg.ordCustUomQty_B.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_QTY });
                isNormal = false;
            }

            if (!ZYPCommonFunc.hasValue(itemLineMsg.custUomCd_B)) {
                itemLineMsg.custUomCd_B.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_UOM });
                isNormal = false;
            }

            if (!ZYPCommonFunc.hasValue(itemLineMsg.dealSplyQuoteDtlSlsAmt_B)) {
                itemLineMsg.dealSplyQuoteDtlSlsAmt_B.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_SELL_PRC });
                isNormal = false;
            }

            if (!ZYPCommonFunc.hasValue(itemLineMsg.prcCatgNm_B)) {
                itemLineMsg.prcCatgNm_B.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_PRC_LIST });
                isNormal = false;
            }
            // QC#10347 2017/07/24 Add Start
            if (!ZYPCommonFunc.hasValue(itemLineMsg.prcBaseDt_B)) {
                itemLineMsg.prcBaseDt_B.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_PRC_BASE_DT });
                isNormal = false;
            }
            // QC#10347 2017/07/24 Add End
            if (!ZYPCommonFunc.hasValue(itemLineMsg.dsOrdLineCatgCd_B)) {
                itemLineMsg.dsOrdLineCatgCd_B.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_LINE_CATG });
                isNormal = false;
            }

            if (!ZYPCommonFunc.hasValue(itemLineMsg.ordLineSrcCd_B)) {
                itemLineMsg.ordLineSrcCd_B.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_LINE_SRC });
                isNormal = false;
            }
         //START 2024/04/03 [CSA-QC#63691,ADD]
            }
            if (entry.getValue().equals(TARGET)) {
                NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(entry.getKey());
                if (!ZYPCommonFunc.hasValue(itemLineMsg.mdseCd_B)) {
                    itemLineMsg.mdseCd_B.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_ITEM });
                    isNormal = false;
                 }

                 if (!ZYPCommonFunc.hasValue(itemLineMsg.ordCustUomQty_B)) {
                     itemLineMsg.ordCustUomQty_B.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_QTY });
                     isNormal = false;
                 }

                 if (!ZYPCommonFunc.hasValue(itemLineMsg.custUomCd_B)) {
                     itemLineMsg.custUomCd_B.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_UOM });
                     isNormal = false;
                 }

                 if (!ZYPCommonFunc.hasValue(itemLineMsg.dealSplyQuoteDtlSlsAmt_B)) {
                     itemLineMsg.dealSplyQuoteDtlSlsAmt_B.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_SELL_PRC });
                     isNormal = false;
                 }

                 if (!ZYPCommonFunc.hasValue(itemLineMsg.prcCatgNm_B)) {
                     itemLineMsg.prcCatgNm_B.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_PRC_LIST });
                     isNormal = false;
                 }
                 // QC#10347 2017/07/24 Add Start
                 if (!ZYPCommonFunc.hasValue(itemLineMsg.prcBaseDt_B)) {
                     itemLineMsg.prcBaseDt_B.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_PRC_BASE_DT });
                     isNormal = false;
                 }
                 // QC#10347 2017/07/24 Add End
                 if (!ZYPCommonFunc.hasValue(itemLineMsg.dsOrdLineCatgCd_B)) {
                     itemLineMsg.dsOrdLineCatgCd_B.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_LINE_CATG });
                     isNormal = false;
                 }

                 if (!ZYPCommonFunc.hasValue(itemLineMsg.ordLineSrcCd_B)) {
                     itemLineMsg.ordLineSrcCd_B.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_LINE_SRC });
                     isNormal = false;
                 }
            }
         //END 2024/04/03 [CSA-QC#63691,ADD]
        }

        return isNormal;
    }

    // 2016/09/06 S21_NA#13917 Add Start
    /**
     * Check Mandatory Field For Additional Tab
     * @param bizMsg NWAL1770CMsg
     * @return No Error : true
     */
    private static boolean checkMandatoryAdditionalTab(NWAL1770CMsg bizMsg) {

        boolean isNormal = true;
        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        String shipToCustAcctCd = bizMsg.shipToCustAcctCd.getValue();
        

        if (!ZYPCommonFunc.hasValue(bizMsg.firstBllgAttrbValTxt)) {
            if (NWAL1770QueryForSaveSubmit.getInstance().getBllgAttrbReqFlg(glblCmpyCd, shipToCustAcctCd, bizMsg.firstBllgAttrbNm.getValue())){
                bizMsg.firstBllgAttrbValTxt.setErrorInfo(1, ZZM9000E, new String[] {REFERENCE_1});
                isNormal = false;                
            }
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.scdBllgAttrbValTxt)) {
            if (NWAL1770QueryForSaveSubmit.getInstance().getBllgAttrbReqFlg(glblCmpyCd, shipToCustAcctCd, bizMsg.scdBllgAttrbNm.getValue())){
                bizMsg.scdBllgAttrbValTxt.setErrorInfo(1, ZZM9000E, new String[] {REFERENCE_2});
                isNormal = false;                
            }
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.thirdBllgAttrbValTxt)) {
            if (NWAL1770QueryForSaveSubmit.getInstance().getBllgAttrbReqFlg(glblCmpyCd, shipToCustAcctCd, bizMsg.thirdBllgAttrbNm.getValue())){
                bizMsg.thirdBllgAttrbValTxt.setErrorInfo(1, ZZM9000E, new String[] {REFERENCE_3});
                isNormal = false;                
            }
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.frthBllgAttrbValTxt)) {
            if (NWAL1770QueryForSaveSubmit.getInstance().getBllgAttrbReqFlg(glblCmpyCd, shipToCustAcctCd, bizMsg.frthBllgAttrbNm.getValue())){
                bizMsg.frthBllgAttrbValTxt.setErrorInfo(1, ZZM9000E, new String[] {REFERENCE_4});
                isNormal = false;                
            }
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.fifthBllgAttrbValTxt)) {
            if (NWAL1770QueryForSaveSubmit.getInstance().getBllgAttrbReqFlg(glblCmpyCd, shipToCustAcctCd, bizMsg.fifthBllgAttrbNm.getValue())){
                bizMsg.fifthBllgAttrbValTxt.setErrorInfo(1, ZZM9000E, new String[] {REFERENCE_5});
                isNormal = false;                
            }
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.sixthBllgAttrbValTxt)) {
            if (NWAL1770QueryForSaveSubmit.getInstance().getBllgAttrbReqFlg(glblCmpyCd, shipToCustAcctCd, bizMsg.sixthBllgAttrbNm.getValue())){
                bizMsg.sixthBllgAttrbValTxt.setErrorInfo(1, ZZM9000E, new String[] {REFERENCE_6});
                isNormal = false;                
            }
        }

        return isNormal;
    }
    // 2016/09/06 S21_NA#13917 Add End

    /**
     * Copy Parent Value
     * @param parentItemLine parentLine
     * @param childItemLine parentLine
     */
    private static void copyParentValue(NWAL1770_BCMsg parentItemLine, NWAL1770_BCMsg childItemLine) {

        ZYPEZDItemValueSetter.setValue(childItemLine.prcCatgCd_B, parentItemLine.prcCatgCd_B);
        ZYPEZDItemValueSetter.setValue(childItemLine.prcCatgNm_B, parentItemLine.prcCatgNm_B);
        ZYPEZDItemValueSetter.setValue(childItemLine.ordLineSrcCd_B, parentItemLine.ordLineSrcCd_B);
        ZYPEZDItemValueSetter.setValue(childItemLine.rtlWhCd_B, parentItemLine.rtlWhCd_B);
        ZYPEZDItemValueSetter.setValue(childItemLine.rtlWhNm_B, parentItemLine.rtlWhNm_B);
        ZYPEZDItemValueSetter.setValue(childItemLine.rtlSwhCd_B, parentItemLine.rtlSwhCd_B);
        ZYPEZDItemValueSetter.setValue(childItemLine.rtlSwhNm_B, parentItemLine.rtlSwhNm_B);
        ZYPEZDItemValueSetter.setValue(childItemLine.prcBaseDt_B, parentItemLine.prcBaseDt_B); // 2017/10/03 S21_NA#21068 Add
        ZYPEZDItemValueSetter.setValue(childItemLine.rddDt_B, parentItemLine.rddDt_B); // 2018/02/13 S21_NA#21165 Add
    }

    /**
     * Check Rerun Price $ Event
     * @param bizMsg NWAL1770CMsg
     * @param glblMsg NWAL1770SMsg
     * @return Need ReRun : true
     */
    public static boolean needRePricing(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg) {

        String alreadyPricing = bizMsg.xxBtnFlg_PR.getValue();
        boolean needRerun = false;

        if (!ZYPCommonFunc.hasValue(alreadyPricing) || ZYPConstant.FLG_OFF_N.equals(alreadyPricing)) {
            needRerun = true;
        } else if (needRePricingForHeader(bizMsg, glblMsg)) {
            needRerun = true;
        } else if (needRePricingForCustomer(bizMsg, glblMsg)) {
            needRerun = true;
        } else if (needRePricingForDelyPmt(bizMsg, glblMsg)) {
            needRerun = true;
        } else if (needRePricingForAddlData(bizMsg, glblMsg)) {
            needRerun = true;
        } else if (needRePricingForItemLine(bizMsg, glblMsg)) {
            needRerun = true;
        }

        if (needRerun) {
            bizMsg.setMessageInfo(NWAM0673E, new String[] {MSG_PARAM_BTN_PRC });
            return true;
        }

        return false;
    }

    /**
     * Check Rerun Price $ Event For Header
     * @param bizMsg NWAL1770CMsg
     * @param glblMsg NWAL1770SMsg
     * @return Need ReRun : true
     */
    private static boolean needRePricingForHeader(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg) {

        if (!NWAL1770CommonLogic.isEqualsEZDItem(bizMsg.dsOrdCatgDescTxt, glblMsg.dsOrdCatgDescTxt)) {
            return true;
        } else if (!NWAL1770CommonLogic.isEqualsEZDItem(bizMsg.dsOrdTpCd, glblMsg.dsOrdTpCd)) {
            return true;
        } else if (!NWAL1770CommonLogic.isEqualsEZDItem(bizMsg.slsRepTocCd, glblMsg.slsRepTocCd)) {
            return true;
        } else if (!NWAL1770CommonLogic.isEqualsEZDItem(bizMsg.custIssPoNum, glblMsg.custIssPoNum)) {
            return true;
        }

        return false;
    }

    /**
     * Check Rerun Price $ Event For Customer
     * @param bizMsg NWAL1770CMsg
     * @param glblMsg NWAL1770SMsg
     * @return Need ReRun : true
     */
    private static boolean needRePricingForCustomer(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg) {

        if (!NWAL1770CommonLogic.isEqualsEZDItem(bizMsg.sellToCustCd, glblMsg.sellToCustCd)) {
            return true;
        }

        return false;
    }

    /**
     * Check Rerun Price $ Event For Delivery / Payment
     * @param bizMsg NWAL1770CMsg
     * @param glblMsg NWAL1770SMsg
     * @return Need Rerun : true
     */
    private static boolean needRePricingForDelyPmt(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg) {

        if (!NWAL1770CommonLogic.isEqualsEZDItem(bizMsg.frtCondDescTxt, glblMsg.frtCondDescTxt)) {
            return true;
        } else if (!NWAL1770CommonLogic.isEqualsEZDItem(bizMsg.spclHdlgTpCd, glblMsg.spclHdlgTpCd)) {
            return true;
        } else if (!NWAL1770CommonLogic.isEqualsEZDItem(bizMsg.shpgSvcLvlCd, glblMsg.shpgSvcLvlCd)) {
            return true;
        }

        return false;
    }

    /**
     * Check Rerun Price $ Event For Additional Data
     * @param bizMsg NWAL1770CMsg
     * @param glblMsg NWAL1770SMsg
     * @return Need ReRun : true
     */
    private static boolean needRePricingForAddlData(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg) {

        if (!NWAL1770CommonLogic.isEqualsEZDItem(bizMsg.prcContrNum, glblMsg.prcContrNum)) {
            return true;
        }

        return false;
    }

    /**
     * Check Rerun Price $ Event For Item Line
     * @param bizMsg NWAL1770CMsg
     * @param glblMsg NWAL1770SMsg
     * @return Need ReRun : true
     */
    private static boolean needRePricingForItemLine(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg) { // S21_NA#1952

        // 2017/08/17 S21_NA#20659 Add Start
        if (bizMsg.B.getValidCount() != glblMsg.B.getValidCount()) {
            return true;
        }
        // 2017/08/17 S21_NA#20659 Add End
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(i);
            NWAL1770_BSMsg itemLineGlblMsg = glblMsg.B.no(i);

            if (ZYPCommonFunc.hasValue(itemLineMsg.dplyQuoteLineSubNum_B)) {
                continue;
            }

            if (!NWAL1770CommonLogic.isEqualsEZDItem(itemLineMsg.mdseCd_B, itemLineGlblMsg.mdseCd_B)) {
                return true;
            } else if (!NWAL1770CommonLogic.isEqualsEZDItem(itemLineMsg.ordCustUomQty_B, itemLineGlblMsg.ordCustUomQty_B)) {
                return true;
            } else if (!NWAL1770CommonLogic.isEqualsEZDItem(itemLineMsg.custUomCd_B, itemLineGlblMsg.custUomCd_B)) {
                return true;
            } else if (!NWAL1770CommonLogic.isEqualsEZDItem(itemLineMsg.dealSplyQuoteDtlSlsAmt_B, itemLineGlblMsg.dealSplyQuoteDtlSlsAmt_B)) {
                return true;
            } else if (!NWAL1770CommonLogic.isEqualsEZDItem(itemLineMsg.prcCatgNm_B, itemLineGlblMsg.prcCatgNm_B)) {
                return true;
            } else if (!NWAL1770CommonLogic.isEqualsEZDItem(itemLineMsg.dsOrdLineCatgCd_B, itemLineGlblMsg.dsOrdLineCatgCd_B)) {
                return true;
            // QC#10347 2017/07/24 Add Start
            } else if (!NWAL1770CommonLogic.isEqualsEZDItem(itemLineMsg.prcBaseDt_B, itemLineGlblMsg.prcBaseDt_B)) {
                return true;
            // QC#10347 2017/07/24 Add End
            } else if (!NWAL1770CommonLogic.isEqualsEZDItem(itemLineMsg.rtlWhNm_B, itemLineGlblMsg.rtlWhNm_B)) {
                return true;
            } else if (!NWAL1770CommonLogic.isEqualsEZDItem(itemLineMsg.rtlSwhNm_B, itemLineGlblMsg.rtlSwhNm_B)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check Modified By Other User
     * @param bizMsg NWAL1770CMsg
     * @param glblMsg NWAL1770SMsg
     * @return Modified By Other User : true
     */
    public static boolean isModifiedByOtherUser(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.splyQuoteStsDescTxt)) {
            return false;
        }

        SPLY_QUOTETMsg splyQuoteTMsg = new SPLY_QUOTETMsg();
        ZYPEZDItemValueSetter.setValue(splyQuoteTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteTMsg.splyQuoteNum, bizMsg.splyQuoteNum);
        splyQuoteTMsg = (SPLY_QUOTETMsg) S21FastTBLAccessor.findByKey(splyQuoteTMsg);

        String origUpdtDt = glblMsg.ezUpTime.getValue();
        String origTimeZone = glblMsg.ezUpTimeZone.getValue();
        String curUpdtDt = splyQuoteTMsg.ezUpTime.getValue();
        String curTimeZone = splyQuoteTMsg.ezUpTimeZone.getValue();

        if (!ZYPDateUtil.isSameTimeStamp(origUpdtDt, origTimeZone, curUpdtDt, curTimeZone)) {
            bizMsg.setMessageInfo(NWAM0429E);
            return true;
        }

        return false;
    }

    /**
     * Execute Record Lock
     * @param bizMsg NWAL1770CMsg
     * @return Modified By Other User : false
     */
    public static boolean executeRecordLock(NWAL1770CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.splyQuoteStsDescTxt)) {
            return true;
        }

        SPLY_QUOTETMsg splyQuoteTMsg = new SPLY_QUOTETMsg();
        ZYPEZDItemValueSetter.setValue(splyQuoteTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteTMsg.splyQuoteNum, bizMsg.splyQuoteNum);
        splyQuoteTMsg = (SPLY_QUOTETMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(splyQuoteTMsg);

        if (splyQuoteTMsg == null) {
            bizMsg.setMessageInfo(NWAM0429E);
            return false;
        }

        return true;
    }

    /**
     * Call NWZC1670 Supply Quote Update API
     * @param bizMsg NWAL1770CMsg
     * @param glblMsg NWAL1770SMsg
     * @param isCallSave Save Flag
     */
    // 2018/07/19 QC#26153 Mod Start
//    public static void callSplyQuoteUpdateApi(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg, boolean isCallSave) {
    // START 2023/07/20 T.Fukuta [CSA-QC#61467,MOD]
//    public static void callSplyQuoteUpdateApi(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg, boolean isCallSave, boolean isCallProfitability) {
    public static boolean callSplyQuoteUpdateApi(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg, boolean isCallSave, boolean isCallProfitability) {
    // END 2023/07/20 T.Fukuta [CSA-QC#61467,MOD]
    // 2018/07/19 QC#26153 Mod End

        NWZC167001PMsg splyQuoteUpdApiPMsg = new NWZC167001PMsg();

        // Create Parameter
        createHdrParam(bizMsg, splyQuoteUpdApiPMsg, isCallSave);
        createDtlParam(bizMsg, splyQuoteUpdApiPMsg, isCallSave);
        createSlsCreditParam(bizMsg, splyQuoteUpdApiPMsg, isCallSave);
        createCtacPsnParam(bizMsg, glblMsg, splyQuoteUpdApiPMsg);

        // Call Supply Quote Update API
        new NWZC167001().execute(splyQuoteUpdApiPMsg, ONBATCH_TYPE.ONLINE);

        // Add Start 2016/09/28 S21_NA#13921
        setUpdateApiErrMsg(bizMsg, splyQuoteUpdApiPMsg);

        final boolean isNormalEnd = setCpoUpdateApiDtlErrMsg(bizMsg, splyQuoteUpdApiPMsg);
        if (!isNormalEnd) {
            // START 2023/07/20 T.Fukuta [CSA-QC#61467,MOD]
//            return;
            return false;
            // END 2023/07/20 T.Fukuta [CSA-QC#61467,MOD]
        }

        if ("E".equals(bizMsg.getMessageKind())) {
            // START 2023/07/20 T.Fukuta [CSA-QC#61467,MOD]
//            return;
            return false;
            // END 2023/07/20 T.Fukuta [CSA-QC#61467,MOD]
        }
        // Add End 2016/09/28 S21_NA#13921
        
        // Del Start 2016/09/28 S21_NA#13921
//        if (S21ApiUtil.isXxMsgId(splyQuoteUpdApiPMsg)) {
//            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(splyQuoteUpdApiPMsg);
//            for (int i = 0; i < msgList.size(); i++) {
//                S21ApiMessage msg = msgList.get(i);
//                String msgId = msg.getXxMsgid();
//                String[] msgPrms = msg.getXxMsgPrmArray();
//                bizMsg.setMessageInfo(msgId, msgPrms);
//                return;
//            }
//        }
//
//        for (int i = 0; i < splyQuoteUpdApiPMsg.NWZC167002PMsg.getValidCount(); i++) {
//            NWZC167002PMsg pMsg = splyQuoteUpdApiPMsg.NWZC167002PMsg.no(i);
//            if (S21ApiUtil.isXxMsgId(pMsg)) {
//                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
//                for (int j = 0; j < msgList.size(); j++) {
//                    S21ApiMessage msg = msgList.get(j);
//                    String msgId = msg.getXxMsgid();
//                    String[] msgPrms = msg.getXxMsgPrmArray();
//                    bizMsg.setMessageInfo(msgId, msgPrms);
//                    return;
//                }
//            }
//        }
        // Del End 2016/09/28 S21_NA#13921

        // Set Numbering Supply Quote Number
        ZYPEZDItemValueSetter.setValue(bizMsg.splyQuoteNum, splyQuoteUpdApiPMsg.splyQuoteNum);

        // 2018/07/19 QC#26153 Mod Start
//        bizMsg.setMessageInfo(ZZM8100I);
        if (!isCallProfitability) {
            bizMsg.setMessageInfo(ZZM8100I);
        }
        // 2018/07/19 QC#26153 Mod End
        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        if (!isCallSave && !isCallProfitability) {
            ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdNum, splyQuoteUpdApiPMsg.cpoOrdNum.getValue());
        }
        return true;
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
    }

    /**
     * Create Header Parameter
     * @param bizMsg NWAL1770CMsg
     * @param splyQuoteApiPMsg NWZC167001PMsg
     * @param isSave Save Flag
     */
    private static void createHdrParam(NWAL1770CMsg bizMsg, NWZC167001PMsg splyQuoteApiPMsg, boolean isSave) {

        // Process Mode
        String procMode = NWZC167001Constant.MODE_SAVE;
        if (!isSave) {
            procMode = NWZC167001Constant.MODE_SUBMIT;
        }

        // Request Type
        String rqstTpCd = NWZC167001Constant.RQST_TP_NEW;
        if (ZYPCommonFunc.hasValue(bizMsg.splyQuoteStsDescTxt)) {
            rqstTpCd = NWZC167001Constant.RQST_TP_MOD;
        }

        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.procModeCd, procMode);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.xxRqstTpCd, rqstTpCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.slsDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.usrId, bizMsg.adminPsnCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.bizAppId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.splyQuoteNum, bizMsg.splyQuoteNum);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.splyQuoteNm, bizMsg.splyQuoteNm); // 2018/03/02 S21_NA#22956 Add
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.cpoOrdTpCd, bizMsg.cpoOrdTpCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.splyQuoteCatgCd, bizMsg.splyQuoteCatgCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.splyQuoteSrcTpCd, bizMsg.splyQuoteSrcTpCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.dsOrdCatgCd, bizMsg.splyQuoteCatgCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.splyQuoteDt, bizMsg.splyQuoteDt);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.splyQuoteVldDaysAot, bizMsg.splyQuoteVldDaysAot);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.custIssPoNum, bizMsg.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.custIssPoDt, bizMsg.custIssPoDt);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.billToCustAcctCd, bizMsg.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.billToCustCd, bizMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.shipToCustAcctCd, bizMsg.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.shipToCustCd, bizMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.sellToCustCd, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.soldToCustLocCd, bizMsg.soldToCustLocCd);
        // Add Start 2017/10/13 QC#20246-1(L3 Sol#454)
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.sellToFirstRefCmntTxt, bizMsg.sellToFirstRefCmntTxt);
        // Add End 2017/10/13 QC#20246-1(L3 Sol#454)
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.adminPsnCd, bizMsg.adminPsnCd);
        if (!isSave) {
            ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.splyQuoteSubmtPsnCd, bizMsg.adminPsnCd);
        }
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.rddDt, bizMsg.rddDt);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.spclHdlgTpCd, bizMsg.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.frtCondCd, bizMsg.frtCondCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.carrSvcLvlCd, bizMsg.carrSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.shpgSvcLvlCd, bizMsg.shpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.dropShipFlg, bizMsg.dropShipFlg);
        // 2018/12/12 QC#29315 Add Start
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.shipToLocNum, bizMsg.shipToLocNum);
        // 2018/12/12 QC#29315 Add End
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.shipToLocNm, bizMsg.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.shipToAddlLocNm, bizMsg.shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.shipToFirstLineAddr, bizMsg.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.shipToScdLineAddr, bizMsg.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.shipToThirdLineAddr, bizMsg.shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.shipToFrthLineAddr, bizMsg.shipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.shipToCtyAddr, bizMsg.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.shipToStCd, bizMsg.shipToStCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.shipToProvNm, bizMsg.shipToProvNm);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.shipToPostCd, bizMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.shipToCntyNm, bizMsg.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.shipToCtryCd, bizMsg.shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.shipTo01RefCmntTxt, bizMsg.shipToFirstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.shipTo02RefCmntTxt, bizMsg.shipToScdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.pmtTermCashDiscCd, bizMsg.pmtTermCashDiscCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.carrAcctNum, bizMsg.carrAcctNum);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.dsPmtMethCd, bizMsg.dsPmtMethCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.slsRepTocCd, bizMsg.slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.prcBaseDt, bizMsg.splyQuoteDt);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.prcCalcDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.prcCatgCd, bizMsg.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.flPrcListCd, bizMsg.flPrcListCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.prcContrNum, bizMsg.prcContrNum);
        // 2018/05/11 QC#22139 Add Start
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.quotePrintCmntTxt, bizMsg.quotePrintCmntTxt);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.ordPrintCmntTxt, bizMsg.ordPrintCmntTxt);
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.shpgCmntPrintCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.shpgCmntPrintCd, SHPG_CMNT_PRINT_CHK_BOX_ON);
        }
        // 2018/05/11 QC#22139 Add End
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.shpgCmntTxt, bizMsg.shpgCmntTxt);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.splyQuoteCmntTxt, bizMsg.splyQuoteCmntTxt);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.invCmntTxt, bizMsg.invCmntTxt);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.dsCrCardPk, bizMsg.dsCrCardPk);
        // 2017/09/11 S21_NA#21013 Add Start
        if (!S21StringUtil.isEquals(PMT_TERM_CASH_DISC.CREDIT_CARD, splyQuoteApiPMsg.pmtTermCashDiscCd.getValue())) {
            splyQuoteApiPMsg.dsCrCardPk.clear();
        }
        // 2017/09/11 S21_NA#21013 Add End
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.firstBllgAttrbNm, bizMsg.firstBllgAttrbNm);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.firstBllgAttrbValTxt, bizMsg.firstBllgAttrbValTxt);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.scdBllgAttrbNm, bizMsg.scdBllgAttrbNm);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.scdBllgAttrbValTxt, bizMsg.scdBllgAttrbValTxt);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.thirdBllgAttrbNm, bizMsg.thirdBllgAttrbNm);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.thirdBllgAttrbValTxt, bizMsg.thirdBllgAttrbValTxt);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.frthBllgAttrbNm, bizMsg.frthBllgAttrbNm);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.frthBllgAttrbValTxt, bizMsg.frthBllgAttrbValTxt);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.fifthBllgAttrbNm, bizMsg.fifthBllgAttrbNm);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.fifthBllgAttrbValTxt, bizMsg.fifthBllgAttrbValTxt);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.sixthBllgAttrbNm, bizMsg.sixthBllgAttrbNm);
        ZYPEZDItemValueSetter.setValue(splyQuoteApiPMsg.sixthBllgAttrbValTxt, bizMsg.sixthBllgAttrbValTxt);
    }

    /**
     * Create Detail Parameter
     * @param bizMsg NWAL1770CMsg
     * @param splyQuoteApiPMsg NWZC167001PMsg
     * @param isSave Save Flag
     */
    private static void createDtlParam(NWAL1770CMsg bizMsg, NWZC167001PMsg splyQuoteApiPMsg, boolean isSave) {

        // 2018/03/19 S21_NA#24810 Mod Start
//        String cancelStsTxt = ZYPCodeDataUtil.getVarCharConstValue(KEY_QUOTE_CANCELLED, bizMsg.glblCmpyCd.getValue());
        String cancelStsTxt = NWAL1770CommonLogic.getCancelStsTxt(bizMsg);
        // 2018/03/19 S21_NA#24810 Mod End
        // Add 2016/08/26 S21_NA#13067
        List<String> dropShipRtlWhCd = getVarCharConstDataList(bizMsg.glblCmpyCd.getValue(), DROP_SHIP_RTL_WH_CD);

        int lineMsgValidCnt = 0;
        int lineMsgValidCntForCancelled = 0;
        //START 2024/04/03 [CSA-QC#63691,ADD]
        int lineMsgValidCntForItemBlankLine = 0;
        //END 2024/04/03 [CSA-QC#63691,ADD]
        for (; lineMsgValidCnt < bizMsg.B.getValidCount(); lineMsgValidCnt++) {
            NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(lineMsgValidCnt);
            String splyQuoteDtlLineNum = itemLineMsg.splyQuoteDtlLineNum_B.getValue();
            String splyQuoteDtlLineSubNum = itemLineMsg.splyQuoteDtlLineSubNum_B.getValue();

            // Skip Cancel Line
            if (SPLY_QUOTE_STS.CANCELLED.equals(itemLineMsg.splyQuoteLineStsCd_B.getValue())) {
                lineMsgValidCntForCancelled++;
                continue;
            }
            //START 2024/04/03 [CSA-QC#63691,ADD]
            //Skip Blank Line
            boolean isItemBlank = !ZYPCommonFunc.hasValue(itemLineMsg.mdseCd_B);
            boolean isQtyBlank = !ZYPCommonFunc.hasValue(itemLineMsg.ordCustUomQty_B);
            boolean isBlank = isItemBlank && isQtyBlank;
                 if (isBlank) {
                   lineMsgValidCntForItemBlankLine++;
                   continue;
                 }
            //END 2024/04/03 [CSA-QC#63691,ADD]
            // Request Type
            String lineStsTxt = itemLineMsg.splyQuoteStsDescTxt_B.getValue();
            String rqstTpCd = NWZC167001Constant.RQST_TP_NEW;
            if (ZYPCommonFunc.hasValue(lineStsTxt)) {
                if (cancelStsTxt.equals(lineStsTxt)) {
                    rqstTpCd = NWZC167001Constant.RQST_TP_CAN;
                } else {
                    rqstTpCd = NWZC167001Constant.RQST_TP_MOD;
                }
            }
            
            // Add Start 2016/08/26 S21_NA#13067
            String rtlWhCd = itemLineMsg.rtlWhCd_B.getValue();
            String rtlSwhCd = itemLineMsg.rtlSwhCd_B.getValue();
            
            boolean isDropShipRtlWh = dropShipRtlWhCd.contains(rtlWhCd);

            String invtyLocCd = rtlWhCd;
            if (!isDropShipRtlWh) {
                if (ZYPCommonFunc.hasValue(rtlSwhCd)) {
                    invtyLocCd = invtyLocCd + rtlSwhCd;
                } else {
                    if (ZYPCommonFunc.hasValue(rtlWhCd)) {
                        invtyLocCd = invtyLocCd + SUB_WH_CD_NEW;
                        ZYPEZDItemValueSetter.setValue(itemLineMsg.rtlSwhCd_B, SUB_WH_CD_NEW);
                    }
                }
            }
            // Add End 2016/08/26 S21_NA#13067

          //START 2024/04/03 [CSA-QC#63691,DEL]
//            NWZC167002PMsg splyQuoteLinePMsg = splyQuoteApiPMsg.NWZC167002PMsg.no(lineMsgValidCnt - lineMsgValidCntForCancelled);
          //END 2024/04/03 [CSA-QC#63691,DEL]
          //START 2024/04/03 [CSA-QC#63691,ADD]
            NWZC167002PMsg splyQuoteLinePMsg = splyQuoteApiPMsg.NWZC167002PMsg.no(lineMsgValidCnt - lineMsgValidCntForCancelled - lineMsgValidCntForItemBlankLine);
          //END 2024/04/03 [CSA-QC#63691,ADD]
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.xxRqstTpCd, rqstTpCd);
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.splyQuoteDtlLineNum, splyQuoteDtlLineNum);
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.splyQuoteDtlLineSubNum, splyQuoteDtlLineSubNum);
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.cpoOrdTpCd, bizMsg.cpoOrdTpCd);
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.mdseCd, itemLineMsg.mdseCd_B);
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.ordQty, itemLineMsg.ordQty_B);
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.ordCustUomQty, itemLineMsg.ordCustUomQty_B);
            // Mod Start 2016/08/26 QC#13067
            //ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.invtyLocCd, itemLineMsg.rtlWhCd_B.getValue() + itemLineMsg.rtlSwhCd_B.getValue());
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.invtyLocCd, invtyLocCd);
            // Mod End 2016/08/26 QC#13067
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.ccyCd, bizMsg.ccyCd);
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.stkStsCd, STK_STS.GOOD);
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.custMdseCd, itemLineMsg.custMdseCd_B);
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.custUomCd, itemLineMsg.custUomCd_B);
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.manPrcFlg, getFlagValue(itemLineMsg.manPrcFlg_B.getValue()));

            BigDecimal unitNetWt = getUnitNetWt(bizMsg, itemLineMsg);
            if (null == unitNetWt) {
                return;
            }

            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.unitNetWt, unitNetWt);
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.setItemShipCpltFlg, getFlagValue(itemLineMsg.setItemShipCpltFlg_B.getValue()));
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.dealSplyQuoteDtlSlsAmt, getAmount(itemLineMsg.dealSplyQuoteDtlSlsAmt_B));
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.dealNetUnitPrcAmt, getAmount(itemLineMsg.xxDtlDiscAmt_B));
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.xxTotDiscAmt, getAmount(itemLineMsg.xxTotDiscAmt_B));
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.dealSplyQuoteDtlNetAmt, getAmount(itemLineMsg.dealSplyQuoteDtlNetAmt_B));
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.xxTotFrtAmt, getAmount(itemLineMsg.xxTotFrtAmt_B));
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.xxTotTaxAmt, getAmount(itemLineMsg.xxTotTaxAmt_B));
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.xxTotNetPrcAmt, getAmount(itemLineMsg.xxSubTotCalcPrcAmt_B));
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.xxTotAmt, getAmount(itemLineMsg.xxTotAmt_B));
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.dsOrdLineCatgCd, itemLineMsg.dsOrdLineCatgCd_B);
            // 2018/03/06 QC#24460 Mod Start
            // 2018/02/13 QC#21165 Add Start
            //ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.rddDt, itemLineMsg.rddDt_B);
            if (ZYPCommonFunc.hasValue(bizMsg.rddDt) && !ZYPCommonFunc.hasValue(itemLineMsg.rddDt_B)) {
                ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.rddDt, bizMsg.rddDt);
            } else {
                ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.rddDt, itemLineMsg.rddDt_B);
            }
            // 2018/02/13 QC#21165 Add End
            // 2018/03/06 QC#24460 Mod End
            // QC#10347 2017/07/24 Add Start
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.prcBaseDt, itemLineMsg.prcBaseDt_B);
            // QC#10347 2017/07/24 Add End
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.ordLineSrcCd, itemLineMsg.ordLineSrcCd_B);
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.rtlWhCd, itemLineMsg.rtlWhCd_B);
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.rtlSwhCd, itemLineMsg.rtlSwhCd_B);
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.dealPrcListPrcAmt, getAmount(itemLineMsg.dealPrcListPrcAmt_B));
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.funcPrcListPrcAmt, getAmount(itemLineMsg.dealPrcListPrcAmt_B));
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.supdLockFlg, getFlagValue(itemLineMsg.supdLockFlg_B.getValue()));
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.prcCatgCd, itemLineMsg.prcCatgCd_B);
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.origMdseCd, itemLineMsg.origMdseCd_B);
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.dplyQuoteLineNum, itemLineMsg.dplyQuoteLineNum_B);
            ZYPEZDItemValueSetter.setValue(splyQuoteLinePMsg.dplyQuoteLineSubNum, itemLineMsg.dplyQuoteLineSubNum_B);

            // Set Calc Base Param
            int calcBaseValidCnt = 0;
            for (int i = 0; i < bizMsg.E.getValidCount(); i++) {
                NWAL1770_ECMsg calcBaseMsg = bizMsg.E.no(i);
                String lineNum = calcBaseMsg.splyQuoteDtlLineNum_E.getValue();
                String subLineNum = calcBaseMsg.splyQuoteDtlLineSubNum_E.getValue();

                if (lineNum.equals(splyQuoteDtlLineNum) && subLineNum.equals(splyQuoteDtlLineSubNum)) {
                    NWZC167002_APMsg calcBaseApiMsg = splyQuoteLinePMsg.A.no(calcBaseValidCnt);
                    EZDMsg.copy(calcBaseMsg, "E", calcBaseApiMsg, "A");
                    ZYPEZDItemValueSetter.setValue(calcBaseApiMsg.splyQuoteDtlLineNum_A, lineNum);
                    ZYPEZDItemValueSetter.setValue(calcBaseApiMsg.splyQuoteDtlLineSubNum_A, subLineNum);
                    calcBaseValidCnt++;
                }
            }
            splyQuoteLinePMsg.A.setValidCount(calcBaseValidCnt);
        }
      //START 2024/04/03 [CSA-QC#63691,DEL]
//        splyQuoteApiPMsg.NWZC167002PMsg.setValidCount(lineMsgValidCnt - lineMsgValidCntForCancelled);
      //END 2024/04/03 [CSA-QC#63691,DEL]
      //START 2024/04/03 [CSA-QC#63691,ADD]
        splyQuoteApiPMsg.NWZC167002PMsg.setValidCount(lineMsgValidCnt - lineMsgValidCntForCancelled - lineMsgValidCntForItemBlankLine);
      //END 2024/04/03 [CSA-QC#63691,ADD]
    }

    /**
     * Create Sales Credit Parameter
     * @param bizMsg NWAL1770CMsg
     * @param splyQuoteApiPMsg NWZC167001PMsg
     * @param isSave Save Flag
     */
    private static void createSlsCreditParam(NWAL1770CMsg bizMsg, NWZC167001PMsg splyQuoteApiPMsg, boolean isSave) {

        int validCnt = 0;
        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            NWAL1770_DCMsg slsCreditMsg = bizMsg.D.no(i);
            NWZC167001_BPMsg slsCreditApiMsg = splyQuoteApiPMsg.B.no(validCnt);
            EZDMsg.copy(slsCreditMsg, "D", slsCreditApiMsg, "B");

            // Request Type
            String rqstTpCd = NWZC150001Constant.RQST_TP_SLS_CR_NEW;
            if (!ZYPCommonFunc.hasValue(slsCreditMsg.xxRqstTpCd_D) && ZYPCommonFunc.hasValue(slsCreditMsg.splyQuoteSlsCrPk_D)) {
                rqstTpCd = NWZC150001Constant.RQST_TP_SLS_CR_MODIFY;
            } else if (NWZC150001Constant.RQST_TP_SLS_CR_NEW.equals(slsCreditMsg.xxRqstTpCd_D.getValue()) && ZYPCommonFunc.hasValue(slsCreditMsg.splyQuoteSlsCrPk_D)) {
                rqstTpCd = NWZC150001Constant.RQST_TP_SLS_CR_MODIFY;
            } else if (ZYPCommonFunc.hasValue(slsCreditMsg.xxRqstTpCd_D)) {
                rqstTpCd = slsCreditMsg.xxRqstTpCd_D.getValue();
            }
            ZYPEZDItemValueSetter.setValue(slsCreditApiMsg.xxRqstTpCd_B, rqstTpCd);

            validCnt++;
        }

        splyQuoteApiPMsg.B.setValidCount(validCnt);
    }

    /**
     * Create Contact Person Parameter
     * @param bizMsg NWAL1770CMsg
     * @param glblMsg NWAL1770SMsg
     * @param splyQuoteApiPMsg NWZC167001PMsg
     */
    private static void createCtacPsnParam(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg, NWZC167001PMsg splyQuoteApiPMsg) {

        int validCnt = 0;

        // Set Insert Or Update Param
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL1770_ACMsg ctacLineMsg = bizMsg.A.no(i);
            NWZC167001_CPMsg ctacLineApiMsg = splyQuoteApiPMsg.C.no(validCnt);

            EZDMsg.copy(ctacLineMsg, "A", ctacLineApiMsg, "C");
            ZYPEZDItemValueSetter.setValue(ctacLineApiMsg.dsCtacPntPk_01, ctacLineMsg.dsCtacPntPk_A);
            ZYPEZDItemValueSetter.setValue(ctacLineApiMsg.dsCtacPntPk_02, ctacLineMsg.dsCtacPntPk_A);
            ZYPEZDItemValueSetter.setValue(ctacLineApiMsg.dsCtacPntPk_03, ctacLineMsg.dsCtacPntPk_A);
            ZYPEZDItemValueSetter.setValue(ctacLineApiMsg.dsCtacPntPk_04, ctacLineMsg.dsCtacPntPk_A);

            // Request Type
            if (ZYPCommonFunc.hasValue(ctacLineMsg.splyQuoteCtacPsnPk_A)) {
                ZYPEZDItemValueSetter.setValue(ctacLineApiMsg.xxRqstTpCd_C, NWZC167001Constant.RQST_TP_MOD);
            } else {
                ZYPEZDItemValueSetter.setValue(ctacLineApiMsg.xxRqstTpCd_C, NWZC167001Constant.RQST_TP_NEW);
            }
            // QC#16452 add Start
            S21SsmEZDResult ssmResult = NWAL1770QueryForSaveSubmit.getInstance().getLocationNumber(bizMsg, ctacLineMsg);
            if (!ssmResult.isCodeNotFound()) {
                ZYPEZDItemValueSetter.setValue(ctacLineApiMsg.locNum_C, (String) ssmResult.getResultObject());
            }
            ZYPEZDItemValueSetter.setValue(ctacLineApiMsg.ctacCustRefTpCd_C, ctacLineMsg.ctacCustRefTpCd_A);
            // QC#16452 add End

            validCnt++;
        }

        // Set Delete Param
        for (int i = 0; i < glblMsg.F.getValidCount(); i++) {
            NWAL1770_FSMsg ctacLineGlblMsg = glblMsg.F.no(i);
            NWZC167001_CPMsg ctacLineApiMsg = splyQuoteApiPMsg.C.no(validCnt);

            EZDMsg.copy(ctacLineGlblMsg, "F", ctacLineApiMsg, "C");
            ZYPEZDItemValueSetter.setValue(ctacLineApiMsg.xxRqstTpCd_C, NWZC167001Constant.RQST_TP_CAN);
            validCnt++;
        }

        splyQuoteApiPMsg.C.setValidCount(validCnt);
    }
    
    // Add Start 2016/09/29 S21_NA#13921
    /**
     * Set Error Message at item
     * @param bizMsg NWAL1770CMsg
     * @param splyQuoteUpdApiPMsg NWZC167001PMsg
     */
    private static void setUpdateApiErrMsg(NWAL1770CMsg bizMsg , NWZC167001PMsg splyQuoteUpdApiPMsg) {

        for (int i = 0; i < splyQuoteUpdApiPMsg.xxMsgIdList.getValidCount(); i++) {

            final String xxMsgId = splyQuoteUpdApiPMsg.xxMsgIdList.no(i).xxMsgId.getValue();

            if (xxMsgId.endsWith("E") && chkCpoUpdAPIErrIdBCH(xxMsgId)) {
                relationCpoUpdateApiErrMsg(bizMsg, xxMsgId);

            } else {
                if (!NWZC167001Constant.NWZM0135W.equals(xxMsgId)) {
                    bizMsg.setMessageInfo(xxMsgId);
                }
            }
        }
    }

    private static boolean chkCpoUpdAPIErrIdBCH(String msgId) {

        final Set<String> chkIdSet = new HashSet<String>();
        chkIdSet.add(NWZC150001CpouConstant.NWZM0024E);
        chkIdSet.add(NWZC150001CpouConstant.NWZM0025E);
        chkIdSet.add(NWZC150001CpouConstant.NWZM0026E);
        chkIdSet.add(NWZC150001CpouConstant.NWZM0028E);
        chkIdSet.add(NWZC150001CpouConstant.NWZM0112E);
        chkIdSet.add(NWZC150001CpouConstant.NWZM0114E);
        chkIdSet.add(NWZC167001Constant.NWZM2010E);
        chkIdSet.add(NWZC167001Constant.NWZM2005E);
        chkIdSet.add(NWZC167001Constant.NWZM1459E);
        chkIdSet.add(NWZC167001Constant.NWZM1452E);
        chkIdSet.add(NWZC167001Constant.NWZM1453E);
        // Del Start 2018/02/26 QC#22967
        //chkIdSet.add(NWZC167001Constant.NWZM1455E);
        // Del End 2018/02/26 QC#22967
        chkIdSet.add(NWZC150001Constant.NWZM1952E);
        chkIdSet.add(NWZC167001Constant.NWZM1813E);
        chkIdSet.add(NWZC167001Constant.NWZM1814E);
        chkIdSet.add(NWZC167001Constant.NWZM1815E);
        chkIdSet.add(NWZC167001Constant.NWZM1416E);
        chkIdSet.add(NWZC167001Constant.NWZM1444E);
        chkIdSet.add(NWZC167001Constant.NWZM1417E);
        chkIdSet.add(NWZC167001Constant.NWZM1817E);
        chkIdSet.add(NWZC167001Constant.NWZM1418E);
        chkIdSet.add(NWZC167001Constant.NWZM1445E);
        chkIdSet.add(NWZC167001Constant.NWZM1821E);
        chkIdSet.add(NWZC167001Constant.NWZM0054E);
        chkIdSet.add(NWZC167001Constant.NWZM1419E);
        chkIdSet.add(NWZC167001Constant.NWZM1421E);
        chkIdSet.add(NWZC167001Constant.NWZM1426E);
        chkIdSet.add(NWZC167001Constant.NWZM1427E);
        chkIdSet.add(NWZC167001Constant.NWZM1822E);
        chkIdSet.add(NWZC167001Constant.NWZM1825E);
        chkIdSet.add(NWZC167001Constant.NWZM1428E);
        // Add Start 2018/02/26 QC#22967
        chkIdSet.add(NWZC167001Constant.NWZM2254E);
        chkIdSet.add(NWZC167001Constant.NWZM2255E);
        // Add End 2018/02/26 QC#22967
        // Add Start 2018/11/28 QC#29252
        chkIdSet.add(NWZC167001Constant.NWZM1838E);
        chkIdSet.add(NWZC167001Constant.NWZM1839E);
        // Add End 2018/11/28 QC#29252
        return chkIdSet.contains(msgId);
    }

    private static void relationCpoUpdateApiErrMsg(NWAL1770CMsg bizMsg, String msgId) {

        boolean errFlg = false;
        if (NWZC150001CpouConstant.NWZM0024E.equals(msgId)) {
            bizMsg.sellToCustCd.setErrorInfo(1, NWZC150001CpouConstant.NWZM0024E);
            errFlg = true;
        } else if (NWZC150001CpouConstant.NWZM0025E.equals(msgId)) {
            bizMsg.billToCustCd.setErrorInfo(1, NWZC150001CpouConstant.NWZM0025E);
            errFlg = true;
        } else if (NWZC150001CpouConstant.NWZM0026E.equals(msgId)) {
            bizMsg.shipToCustCd.setErrorInfo(1, NWZC150001CpouConstant.NWZM0026E);
            errFlg = true;
        } else if (NWZC150001CpouConstant.NWZM0028E.equals(msgId)) {
            bizMsg.dsOrdTpCd.setErrorInfo(1, NWZC150001CpouConstant.NWZM0028E);
            errFlg = true;
        } else if (NWZC150001CpouConstant.NWZM0112E.equals(msgId)) {
            bizMsg.billToCustCd.setErrorInfo(1, NWZC150001CpouConstant.NWZM0112E);
            errFlg = true;
        } else if (NWZC150001CpouConstant.NWZM0114E.equals(msgId)) {
            bizMsg.shipToCustCd.setErrorInfo(1, NWZC150001CpouConstant.NWZM0114E);
            errFlg = true;
        } else if (NWZC167001Constant.NWZM2010E.equals(msgId)) {
            bizMsg.carrSvcLvlDescTxt.setErrorInfo(1, NWZC167001Constant.NWZM2010E);
            errFlg = true;
        } else if (NWZC167001Constant.NWZM2005E.equals(msgId)) {
            bizMsg.pmtTermCashDiscDescTxt.setErrorInfo(1, NWZC167001Constant.NWZM2005E);
            bizMsg.dsPmtMethCd.setErrorInfo(1, NWZC167001Constant.NWZM2005E);
            errFlg = true;
        } else if (NWZC167001Constant.NWZM1459E.equals(msgId)) {
            bizMsg.carrAcctNum.setErrorInfo(1, NWZC167001Constant.NWZM1459E);
            errFlg = true;
        } else if (NWZC167001Constant.NWZM1452E.equals(msgId)) {
            bizMsg.billToCustCd.setErrorInfo(1, NWZC167001Constant.NWZM1452E);
            bizMsg.billToCustAcctCd.setErrorInfo(1, NWZC167001Constant.NWZM1452E);
            errFlg = true;
        } else if (NWZC167001Constant.NWZM1453E.equals(msgId)) {
            bizMsg.shipToCustCd.setErrorInfo(1, NWZC167001Constant.NWZM1453E);
            bizMsg.shipToCustAcctCd.setErrorInfo(1, NWZC167001Constant.NWZM1453E);
            errFlg = true;
            // Del Start 2018/02/26 QC#22967
        //} else if (NWZC167001Constant.NWZM1455E.equals(msgId)) {
        //    NWAL1770CommonLogic.checkBillShipSoldRelation(bizMsg);
        //    errFlg = true;
            // Del End 2018/02/26 QC#22967
        } else if (NWZC150001Constant.NWZM1952E.equals(msgId)) {
            bizMsg.dsPmtMethCd.setErrorInfo(1, msgId);
            errFlg = true;
        }
         else if (NWZC167001Constant.NWZM1813E.equals(msgId)) {
            bizMsg.dsOrdCatgDescTxt.setErrorInfo(1, msgId);
            errFlg = true;
        } else if (NWZC167001Constant.NWZM1814E.equals(msgId)) {
            bizMsg.splyQuoteSrcTpCd.setErrorInfo(1, msgId);
            errFlg = true;
        } else if (NWZC167001Constant.NWZM1815E.equals(msgId)) {
            bizMsg.dsOrdTpCd.setErrorInfo(1, msgId);
            errFlg = true;
        } else if (NWZC167001Constant.NWZM1416E.equals(msgId)) {
            bizMsg.billToCustAcctCd.setErrorInfo(1, msgId);
            errFlg = true;
        } else if (NWZC167001Constant.NWZM1444E.equals(msgId)) {
            bizMsg.billToCustCd.setErrorInfo(1, msgId);
            errFlg = true;
        } else if (NWZC167001Constant.NWZM1417E.equals(msgId)) {
            bizMsg.shipToCustAcctCd.setErrorInfo(1, msgId);
            errFlg = true;
        } else if (NWZC167001Constant.NWZM1817E.equals(msgId)) {
            bizMsg.sellToCustCd.setErrorInfo(1, msgId);
            errFlg = true;
        } else if (NWZC167001Constant.NWZM1418E.equals(msgId)) {
            bizMsg.soldToCustLocCd.setErrorInfo(1, msgId);
            errFlg = true;
        } else if (NWZC167001Constant.NWZM1445E.equals(msgId)) {
            bizMsg.shipToCustCd.setErrorInfo(1, msgId);
            errFlg = true;
        } else if (NWZC167001Constant.NWZM1821E.equals(msgId)) {
            bizMsg.pmtTermCashDiscDescTxt.setErrorInfo(1, msgId);
            errFlg = true;
        } else if (NWZC167001Constant.NWZM0054E.equals(msgId)) {
            bizMsg.slsRepTocNm.setErrorInfo(1, msgId);
            errFlg = true;
        } else if (NWZC167001Constant.NWZM1419E.equals(msgId)) {
            bizMsg.prcCatgNm.setErrorInfo(1, msgId);
            errFlg = true;
        } else if (NWZC167001Constant.NWZM1421E.equals(msgId)) {
            bizMsg.prcContrNum.setErrorInfo(1, msgId);
            errFlg = true;
        } else if (NWZC167001Constant.NWZM1426E.equals(msgId)) {
            bizMsg.frtCondDescTxt.setErrorInfo(1, msgId);
            errFlg = true;
        } else if (NWZC167001Constant.NWZM1427E.equals(msgId)) {
            bizMsg.carrSvcLvlDescTxt.setErrorInfo(1, msgId);
            errFlg = true;
        } else if (NWZC167001Constant.NWZM1822E.equals(msgId)) {
            bizMsg.shpgSvcLvlCd.setErrorInfo(1, msgId);
            errFlg = true;
        } else if (NWZC167001Constant.NWZM1825E.equals(msgId)) {
            bizMsg.dsPmtMethCd.setErrorInfo(1, msgId);
            errFlg = true;
        } else if (NWZC167001Constant.NWZM1428E.equals(msgId)) {
            bizMsg.spclHdlgTpCd.setErrorInfo(1, msgId);
            errFlg = true;
            // Add Start 2018/02/26 QC#22967
        } else if (NWZC167001Constant.NWZM2254E.equals(msgId)) {
            NWAL1770CommonLogic.checkBillShipSoldRelation(bizMsg);
            errFlg = true;
        } else if (NWZC167001Constant.NWZM2255E.equals(msgId)) {
            NWAL1770CommonLogic.checkBillShipSoldRelation(bizMsg);
            errFlg = true;
            // Add End 2018/02/26 QC#22967
            // Add Start 2018/11/28 QC#29252
        } else if (NWZC167001Constant.NWZM1838E.equals(msgId)) {
            bizMsg.pmtTermCashDiscDescTxt.setErrorInfo(1, NWZC167001Constant.NWZM1838E);
            errFlg = true;
        } else if (NWZC167001Constant.NWZM1839E.equals(msgId)) {
            bizMsg.pmtTermCashDiscDescTxt.setErrorInfo(1, NWZC167001Constant.NWZM1839E);
            errFlg = true;
            // Add End 2018/11/28 QC#29252
        }
        if (errFlg) {
            bizMsg.setMessageInfo(ZZM9037E);
        }
    }

    private static boolean setCpoUpdateApiDtlErrMsg(NWAL1770CMsg bizMsg, NWZC167001PMsg splyQuoteUpdApiMsg) {

        boolean isNormalEnd = true;

        
        for (int i = 0; i < splyQuoteUpdApiMsg.NWZC167002PMsg.getValidCount(); i++) {
            NWZC167002PMsg pMsg = splyQuoteUpdApiMsg.NWZC167002PMsg.no(i);

            for (int j = 0; j < pMsg.xxMsgIdList.getValidCount(); j++) {

                final String xxMsgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                // QC#56978
                final String[] xxMsgParam = getMsgParamArray(pMsg.xxMsgIdList.no(j));

               if (xxMsgId.endsWith("E") && chkCpoUpdAPIErrIdDtl(xxMsgId) || NWZM0900W.equals(xxMsgId)) {

                    isNormalEnd = false;
                    NWAL1770_BCMsg lineMsg = null;
                    for (int x = i; x < bizMsg.B.getValidCount(); x++) {
                        lineMsg = bizMsg.B.no(x);
                        // Skip Cancel Line
                        if (SPLY_QUOTE_STS.CANCELLED.equals(lineMsg.splyQuoteLineStsCd_B.getValue())) {
                            continue;
                        }
                        String splyQuoteDtlLineNum = lineMsg.splyQuoteDtlLineNum_B.getValue();
                        String splyQuoteDtlLineSubNum = lineMsg.splyQuoteDtlLineSubNum_B.getValue();
                        if (ZYPCommonFunc.hasValue(splyQuoteDtlLineNum) && splyQuoteDtlLineNum.equals(pMsg.splyQuoteDtlLineNum.getValue()) //
                                && ZYPCommonFunc.hasValue(splyQuoteDtlLineSubNum) && splyQuoteDtlLineSubNum.equals(pMsg.splyQuoteDtlLineSubNum.getValue())) {
                            break;
                        }
                    }
                    
                    // isComponent?. Mod QC#56978
                    relationCpoUpdateApiDtlErrMsg(bizMsg, lineMsg, xxMsgId, splyQuoteUpdApiMsg, xxMsgParam);

                } else {
                    bizMsg.setMessageInfo(xxMsgId);
                }
            }
        }
        return isNormalEnd;
    }

    // 2019/10/03 QC#53595 Add Start
    /**
     * Check SalesRep assign to correct IS Order 
     * @param bizMsg Business Message
     * @return true: normal end, false: abnornal end
     */
    public static boolean checkISOrderBindToISGroup(NWAL1770CMsg bizMsg) {
        boolean isOkFlg = true;
        BigDecimal count = NWAL1770QueryForSaveSubmit.getInstance().cntOrdTpCd(bizMsg);
        if(count.compareTo(BigDecimal.ZERO) == 0){
            return isOkFlg;
        }

        count = NWAL1770QueryForSaveSubmit.getInstance().cntAssignedISGroup(bizMsg);
        if (count.compareTo(BigDecimal.ZERO) == 0) {
            isOkFlg = false;
            return isOkFlg;
        }
        return isOkFlg;
    }
    // 2019/10/03 QC#53595 Add End

    private static boolean chkCpoUpdAPIErrIdDtl(String msgId) {

        final List<String> msgList = new ArrayList<String>();
        msgList.add(NWZC150001CpouConstant.NWZM0041E);
        msgList.add(NWZC150001CpouConstant.NWZM0042E);
        msgList.add(NWZC150001CpouConstant.NWZM0044E);
        msgList.add(NWZC150001CpouConstant.NWZM0947E);
        msgList.add(NWZC150001CpouConstant.NWZM0048E);
        msgList.add(NWZC150001CpouConstant.NWZM0927E);
        msgList.add(NWZC150001CpouConstant.NWZM0054E);
        msgList.add(NWXC001001SalesRepValidator.MSG_ID_INVALID_SLS_REP);
        msgList.add(NWZC150001CpouConstant.NWZM0140E);
        msgList.add(NWZC150001CpouConstant.NWZM0141E);
        msgList.add(NWZC150001CpouConstant.NWZM0142E);
        msgList.add(NWZC167001Constant.NWZM1488E);  
        msgList.add(NWZC150001CpouConstant.NWZM0143E);
        msgList.add(NWZC167001Constant.NWZM1489E);
        msgList.add(NWZC150001CpouConstant.NWZM0144E);
        msgList.add(NWZC167001Constant.NWZM1492E);
        msgList.add(NWZC150001CpouConstant.NWZM0148E);
        msgList.add(NWZC150001CpouConstant.NWZM0152E);
        msgList.add(NWZC150001CpouConstant.NWZM0153E);
        msgList.add(NWZC150001CpouConstant.NWZM0161E);
        msgList.add(NWZC150001CpouConstant.NWZM0335E);
        msgList.add(NWZC150001CpouConstant.NWZM0642E);
        msgList.add(NWZC150001CpouConstant.NWZM0263E);
        msgList.add(NWZC150001CpouConstant.NWZM0458E);
        msgList.add(NWZC150001CpouConstant.NWZM0418E);
        msgList.add(NWZC150001CpouConstant.NWZM0419E);
        msgList.add(NWZC150001CpouConstant.NWZM0047E);
        msgList.add(NWZM0900W);
        msgList.add(NWZC150001CpouConstant.NWZM0942E);
        msgList.add(NWZC150001CpouConstant.NWZM0943E);
        msgList.add(NWZC150001CpouConstant.NWZM1298E);
        msgList.add(NWZC150001CpouConstant.NWZM1311E);
        msgList.add(NWZC150001CpouConstant.NWZM1312E);
        msgList.add(NWZC150001CpouConstant.NWZM1262E);
        msgList.add(NWZC150001Constant.NWZM1468E);
        msgList.add(NWZC150001Constant.NWZM1469E);
        msgList.add(NWZC150001Constant.NWZM1470E);
        msgList.add(NWZC150001Constant.NWZM1475E);
        msgList.add(NWZC150001Constant.NWZM1478E);
        msgList.add(NWZC150001Constant.NWZM1889E);
        msgList.add(NWZC150001Constant.NWZM1431E);
        msgList.add(NWZC150001Constant.NWZM1432E);
        msgList.add(NWZC150001Constant.NWZM1433E);
        msgList.add(NWZC150001Constant.NWZM1434E);
        msgList.add(NWZC150001Constant.NWZM1427E);
        msgList.add(NWZC150001Constant.NWZM1419E);
        msgList.add(NWZC150001Constant.NWZM1530E);
        msgList.add(NWZC150001Constant.NWZM1917E);
        msgList.add(NWZC150001Constant.NWZM1484E);
        msgList.add(NWZC150001Constant.NWZM1485E);
        msgList.add(NWZC150001Constant.NWZM1487E);
        msgList.add(NWZC167001Constant.NWZM1826E);
        msgList.add(NWZC167001Constant.NWZM1827E);
        msgList.add(NWZC167001Constant.NWZM0598E);
        // 2018/08/21 S21_NA#26550 Add Start
        msgList.add(NWZC150001Constant.NWZM1990E);
        // 2018/08/21 S21_NA#26550 Add End
        msgList.add(NWZC150001Constant.NWAM0965E); // 2019/02/15 QC#30319 Add
        msgList.add(NWZC167001Constant.NWAM0983E); // 2020/06/09 QC#56978

        return msgList.contains(msgId);
    }
    // QC#56978 Start
    // private static void relationCpoUpdateApiDtlErrMsg(NWAL1770CMsg bizMsg, NWAL1770_BCMsg lineMsg, String msgId, NWZC167001PMsg splyQuoteUpdApiMsg) {
    private static void relationCpoUpdateApiDtlErrMsg(NWAL1770CMsg bizMsg, NWAL1770_BCMsg lineMsg, String msgId, NWZC167001PMsg splyQuoteUpdApiMsg, String[] xxMsgParam) {
        if (NWZC150001CpouConstant.NWZM0041E.equals(msgId)) {
            lineMsg.ordCustUomQty_B.setErrorInfo(1, msgId); 

        } else if (NWZC150001CpouConstant.NWZM0042E.equals(msgId)) {
            lineMsg.ordCustUomQty_B.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0044E.equals(msgId)) {
            lineMsg.rtlWhNm_B.setErrorInfo(1, msgId);
            lineMsg.rtlSwhNm_B.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0947E.equals(msgId)) {
            lineMsg.rtlWhNm_B.setErrorInfo(1, msgId);
            lineMsg.rtlSwhNm_B.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0048E.equals(msgId)) {
            lineMsg.dealSplyQuoteDtlSlsAmt_B.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0927E.equals(msgId)) {
            bizMsg.pmtTermCashDiscDescTxt.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0054E.equals(msgId)) {
            bizMsg.slsRepTocNm.setErrorInfo(1, msgId);

        } else if (NWXC001001SalesRepValidator.MSG_ID_INVALID_SLS_REP.equals(msgId)) {
            bizMsg.slsRepTocNm.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0140E.equals(msgId)) {
            lineMsg.rtlWhNm_B.setErrorInfo(1, msgId);
            lineMsg.rtlSwhNm_B.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0141E.equals(msgId)) {
            lineMsg.rtlWhNm_B.setErrorInfo(1, msgId);
            lineMsg.rtlSwhNm_B.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0142E.equals(msgId) || NWZC167001Constant.NWZM1488E.equals(msgId) ) {
            MDSETMsg mdseMsg = NWAL1770CommonLogic.getMdseWithRgtnStsCd(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_B.getValue());
            if (mdseMsg != null) {
                lineMsg.ordCustUomQty_B.setErrorInfo(1, NWZM2023E, new String[] {String.valueOf(mdseMsg.cpoMinOrdQty.getValue()) });
            } else {
                lineMsg.ordCustUomQty_B.setErrorInfo(1, msgId);
            }

        } else if (NWZC150001CpouConstant.NWZM0143E.equals(msgId) || NWZC167001Constant.NWZM1489E.equals(msgId)) {
            MDSETMsg mdseMsg = NWAL1770CommonLogic.getMdseWithRgtnStsCd(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_B.getValue());
            if (mdseMsg != null) {
                lineMsg.ordCustUomQty_B.setErrorInfo(1, NWZM2024E, new String[] {String.valueOf(mdseMsg.cpoMaxOrdQty.getValue()) });
            } else {
                lineMsg.ordCustUomQty_B.setErrorInfo(1, msgId);
            }

        } else if (NWZC150001CpouConstant.NWZM0144E.equals(msgId) || NWZC167001Constant.NWZM1492E.equals(msgId)) {
            MDSETMsg mdseMsg = NWAL1770CommonLogic.getMdseWithRgtnStsCd(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_B.getValue());
            if (mdseMsg != null) {
                lineMsg.ordCustUomQty_B.setErrorInfo(1, NWZM2025E, new String[] {String.valueOf(mdseMsg.cpoIncrOrdQty.getValue()) });
            } else {
                lineMsg.ordCustUomQty_B.setErrorInfo(1, msgId);
            }

        } else if (NWZC150001CpouConstant.NWZM0148E.equals(msgId)) {
            lineMsg.dealSplyQuoteDtlSlsAmt_B.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0152E.equals(msgId)) {
            lineMsg.dealSplyQuoteDtlSlsAmt_B.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0153E.equals(msgId)) {
            lineMsg.ordCustUomQty_B.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0161E.equals(msgId)) {
            lineMsg.ordCustUomQty_B.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0335E.equals(msgId)) {
            lineMsg.ordCustUomQty_B.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0642E.equals(msgId)) {
            bizMsg.slsRepTocNm.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0263E.equals(msgId)) {
            bizMsg.slsRepTocNm.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0458E.equals(msgId)) {
            bizMsg.carrAcctNum.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0418E.equals(msgId)) {
            lineMsg.ordCustUomQty_B.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0419E.equals(msgId)) {
            lineMsg.ordCustUomQty_B.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0047E.equals(msgId)) {
            lineMsg.rtlWhNm_B.setErrorInfo(1, msgId);
            lineMsg.rtlSwhNm_B.setErrorInfo(1, msgId);

        } else if (NWZM0900W.equals(msgId)) {
            lineMsg.rtlWhNm_B.setErrorInfo(2, msgId);
            lineMsg.rtlSwhNm_B.setErrorInfo(2, msgId);
            bizMsg.setMessageInfo(NWZM0900W);

        } else if (NWZC150001CpouConstant.NWZM0942E.equals(msgId)) {
            lineMsg.rtlWhNm_B.setErrorInfo(1, msgId);
            lineMsg.rtlSwhNm_B.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0943E.equals(msgId)) {
            bizMsg.pmtTermCashDiscDescTxt.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM1298E.equals(msgId)) {
            lineMsg.ordCustUomQty_B.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM1311E.equals(msgId)) {
            lineMsg.mdseCd_B.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM1312E.equals(msgId)) {
            lineMsg.mdseCd_B.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM1262E.equals(msgId)) {
            lineMsg.rtlWhNm_B.setErrorInfo(1, msgId);
            lineMsg.rtlSwhNm_B.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1468E.equals(msgId)) {
            lineMsg.mdseCd_B.setErrorInfo(1, msgId);
            lineMsg.custMdseCd_B.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1469E.equals(msgId)) {
            lineMsg.dsOrdLineCatgCd_B.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1470E.equals(msgId)) {
            lineMsg.rtlWhNm_B.setErrorInfo(1, msgId);
            lineMsg.rtlSwhNm_B.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1475E.equals(msgId)) {
            lineMsg.mdseCd_B.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1478E.equals(msgId)) {
            lineMsg.dsOrdLineCatgCd_B.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1889E.equals(msgId)) {
            lineMsg.dsOrdLineCatgCd_B.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1431E.equals(msgId)) {
            lineMsg.dsOrdLineCatgCd_B.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1432E.equals(msgId)) {
            lineMsg.ordLineSrcCd_B.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1433E.equals(msgId)) {
            lineMsg.rtlWhNm_B.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1434E.equals(msgId)) {
            lineMsg.rtlSwhNm_B.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1427E.equals(msgId)) {
            bizMsg.carrSvcLvlDescTxt.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1419E.equals(msgId)) {
            lineMsg.prcCatgNm_B.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1530E.equals(msgId)) {
            lineMsg.mdseCd_B.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1917E.equals(msgId) 
                || NWZC150001Constant.NWZM1484E.equals(msgId) //
                || NWZC150001Constant.NWZM1485E.equals(msgId) //
                || NWZC150001Constant.NWZM1487E.equals(msgId) //
                ) {
            // Qty Check Error
            lineMsg.ordCustUomQty_B.setErrorInfo(1, msgId);
        } else if (NWZC167001Constant.NWZM1826E.equals(msgId)) {
            lineMsg.custUomCd_B.setErrorInfo(1, msgId);
        } else if (NWZC167001Constant.NWZM1827E.equals(msgId)) {
            lineMsg.rtlWhNm_B.setErrorInfo(1, msgId);
            lineMsg.rtlSwhNm_B.setErrorInfo(1, msgId);
        } else if (NWZC167001Constant.NWZM0598E.equals(msgId)) {
            lineMsg.mdseCd_B.setErrorInfo(1, msgId);
        }
        // 2018/08/21 S21_NA#26550 Add Start
        else if (NWZC150001Constant.NWZM1990E.equals(msgId)) {
            lineMsg.rtlWhNm_B.setErrorInfo(1, msgId);
        }
        // 2018/08/21 S21_NA#26550 Add End
        // 2019/02/15 QC#30319 Add Start
        else if (NWZC150001Constant.NWAM0965E.equals(msgId)) {
            lineMsg.ordLineSrcCd_B.setErrorInfo(1, msgId);
        } else if (NWZC167001Constant.NWAM0983E.equals(msgId)) { // 2020/06/09 QC#56978 NEW Error Code
            lineMsg.rtlWhNm_B.setErrorInfo(1, msgId, xxMsgParam);
        }
        // 2019/02/15 QC#30319 Add End
    }
// Add End 2016/09/29 S21_NA#13921
    
    /**
     * Get Unit Net Weight
     * @param bizMsg NWAL1770CMsg
     * @param itemLineMsg NWAL1770_BCMsg
     * @return Unit Net Weight
     */
    private static BigDecimal getUnitNetWt(NWAL1770CMsg bizMsg, NWAL1770_BCMsg itemLineMsg) {

        BigDecimal inPountNetWt = getPoundWt(bizMsg, itemLineMsg);
        if (!ZYPCommonFunc.hasValue(inPountNetWt)) {
            itemLineMsg.mdseCd_B.setErrorInfo(1, NWAM0311E, new String[] {"MDSE_STORE_PKG", itemLineMsg.mdseCd_B.getValue() });
            return null;
        }

        return inPountNetWt;
    }

    /**
     * Get Pound Weight
     * @param bizMsg NWAL1770CMsg
     * @param itemLineMsg NWAL1770_BCMsg
     * @return Unit Net Weight
     */
    private static BigDecimal getPoundWt(NWAL1770CMsg bizMsg, NWAL1770_BCMsg itemLineMsg) {

        String localMdseCd = itemLineMsg.mdseCd_B.getValue();
        MDSETMsg baseMdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), localMdseCd);
        if (null != baseMdseTMsg) {
            localMdseCd = baseMdseTMsg.mdseCd.getValue();
        }

        String pkgUomCd = ZYPCodeDataUtil.getVarCharConstValue(KEY_PKG_UOM_FOR_PRC, bizMsg.glblCmpyCd.getValue());
        if (!ZYPCommonFunc.hasValue(pkgUomCd)) {
            pkgUomCd = PKG_UOM.EACH;
        }

        MDSE_STORE_PKGTMsg mdseStorePkgTMsg = new MDSE_STORE_PKGTMsg();
        mdseStorePkgTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        mdseStorePkgTMsg.mdseCd.setValue(localMdseCd);
        mdseStorePkgTMsg.pkgUomCd.setValue(pkgUomCd);
        mdseStorePkgTMsg = (MDSE_STORE_PKGTMsg) S21FastTBLAccessor.findByKey(mdseStorePkgTMsg);

        if (mdseStorePkgTMsg == null) {
            return null;
        }

        return mdseStorePkgTMsg.inPoundWt.getValue();
    }

    /**
     * Derive Default Warehouse
     * @param bizMsg NWAL1770CMsg
     * @return No Error : true
     */
    public static boolean deriveDefaultWh(NWAL1770CMsg bizMsg, NWAL1770_BCMsg itemLineMsg) {

        // START 2017/10/19 J.Kim [QC#21157,DEL]
        //for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
        //    NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(i);
        // END 2017/10/19 J.Kim [QC#21157,DEL]

            // START 2017/10/19 J.Kim [QC#21157,DEL]
            // if (ZYPCommonFunc.hasValue(itemLineMsg.dplyQuoteLineSubNum_B) || ZYPCommonFunc.hasValue(itemLineMsg.rtlWhNm_B)) {
            //    return true;
            // }
            // END 2017/10/19 J.Kim [QC#21157,DEL]

            if (needDefWh(bizMsg.glblCmpyCd.getValue(), itemLineMsg.mdseCd_B.getValue())) {
                // Call Default WH API
                NWZC180001PMsg pMsg = callDefWhApi(bizMsg, itemLineMsg);
                if (pMsg == null) {
                    return false;
                }

                String rtlWhCd = pMsg.rtlWhCd.getValue();
                String rtlSwhCd = pMsg.rtlSwhCd.getValue();

                if (ZYPCommonFunc.hasValue(rtlWhCd)) {
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.rtlWhCd_B, rtlWhCd);
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.rtlWhNm_B, NWAL1770CommonLogic.getRtlWhNm(bizMsg, rtlWhCd));
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.rtlSwhCd_B, rtlSwhCd);
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.rtlSwhNm_B, NWAL1770CommonLogic.getRtlSubWhNm(bizMsg, rtlWhCd, rtlSwhCd));
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.ordLineSrcCd_B, pMsg.ordLineSrcCd);
                }

                // START 2017/10/19 J.Kim [QC#21157,DEL]
                //// Set Component
                //for (int j = i + 1; j < bizMsg.B.getValidCount(); j++) {
                //    if (itemLineMsg.dplyQuoteLineNum_B.getValue().compareTo(bizMsg.B.no(j).dplyQuoteLineNum_B.getValue()) == 0) {
                //        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(j).rtlWhCd_B, itemLineMsg.rtlWhCd_B);
                //        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(j).rtlWhNm_B, itemLineMsg.rtlWhNm_B);
                //        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(j).rtlSwhCd_B, itemLineMsg.rtlSwhCd_B);
                //         ZYPEZDItemValueSetter.setValue(bizMsg.B.no(j).rtlSwhNm_B, itemLineMsg.rtlSwhNm_B);
                //        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(j).ordLineSrcCd_B, itemLineMsg.ordLineSrcCd_B);
                //    } else {
                //        break;
                //    }
                //}
                // END 2017/10/19 J.Kim [QC#21157,DEL]
            }
        // START 2017/10/19 J.Kim [QC#21157,DEL]
        //}
        // END 2017/10/19 J.Kim [QC#21157,DEL]

        return true;
    }

    /**
     * Need Default Warehouse
     * @param glblCmpyCd Global Company Code
     * @param mdseCd MDSE Code
     * @return Need Warehouse : true
     */
    private static boolean needDefWh(String glblCmpyCd, String mdseCd) {

        if (NWAL1770CommonLogic.isSetMdse(glblCmpyCd, mdseCd)) {
            return true;
        }

        if (NWAL1770CommonLogic.isTangibleMdse(glblCmpyCd, mdseCd)) {
            return true;
        }

        return false;
    }

    /**
     * Call NWZC1800 Default WH API
     * @param bizMsg NWAL1770CMsg
     * @param itemLineMsg NWAL1770_BCMsg
     * @return NWZC180001PMsg
     */
    private static NWZC180001PMsg callDefWhApi(NWAL1770CMsg bizMsg, NWAL1770_BCMsg itemLineMsg) {

        NWZC180001PMsg pMsg = new NWZC180001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC180001Constant.PROC_MODE_OTBD);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, bizMsg.splyQuoteCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, itemLineMsg.mdseCd_B.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.postCd, bizMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(pMsg.ordQty, itemLineMsg.ordQty_B.getValue());
        // call NWZC1800 Default WH API
        new NWZC180001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);

                if (msgId.endsWith("E")) {
                    return null;
                }
            }
        }

        return pMsg;
    }

    /**
     * Check Duplication PO Number
     * @param bizMsg NWAL1770CMsg
     * @param isCallSave Called Save Button
     * @return Duplication : true
     */
    public static boolean isDuplicationPoNum(NWAL1770CMsg bizMsg, boolean isCallSave) {

        boolean isDuplication = false;
        String buttonNm = null;

        if (isCallSave) {
            if (NWAL1770QueryForSaveSubmit.getInstance().isDuplicationPoNumForQuote(bizMsg)) {
                isDuplication = true;
                buttonNm = MSG_PARAM_SAVE;
            }
        } else {
            if (NWAL1770QueryForSaveSubmit.getInstance().isDuplicationPoNumForOrder(bizMsg)) {
                isDuplication = true;
                buttonNm = MSG_PARAM_SUBMIT;
            }
        }

        if (isDuplication) {
            bizMsg.custIssPoNum.setErrorInfo(2, NWAM0793W, new String[] {MSG_PARAM_CUST_PO_NUM, buttonNm });
            bizMsg.setMessageInfo(NWAM0793W, new String[] {MSG_PARAM_CUST_PO_NUM, buttonNm });
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_PO, ZYPConstant.FLG_ON_Y);
        }

        return isDuplication;
    }

    /**
     * Get Flag Value
     * @param flagValue Flag Value
     * @return Flag Value
     */
    private static String getFlagValue(String flagValue) {

        if (ZYPCommonFunc.hasValue(flagValue)) {
            return flagValue;
        }

        return ZYPConstant.FLG_OFF_N;
    }

    /**
     * Get Amount
     * @param amount Target Amount
     * @return Amount
     */
    private static BigDecimal getAmount(EZDCBigDecimalItem amount) {

        if (ZYPCommonFunc.hasValue(amount)) {
            return amount.getValue();
        }

        return BigDecimal.ZERO;
    }

    /**
     * Get Price Category Code From Cache
     * @param bizMsg NWAL1770CMsg
     * @param prcCatgNm Price Category Name
     * @param prcCatgCdMap Cache Map
     * @return Price Category Code From Cache
     */
    private static String getPrcCatgCdFromCache(NWAL1770CMsg bizMsg, String prcCatgNm, Map<String, String> prcCatgCdMap) {

        if (prcCatgCdMap.containsKey(prcCatgNm)) {
            return prcCatgCdMap.get(prcCatgNm);
        }

        S21SsmEZDResult ssmResult = NWAL1770QueryForPricing.getInstance().getPrcCatgCd(bizMsg, prcCatgNm);
        if (ssmResult.isCodeNormal()) {
            String prcCatgCd = (String) ssmResult.getResultObject();
            prcCatgCdMap.put(prcCatgNm, prcCatgCd);
            return prcCatgCd;
        }

        return null;
    }

    /**
     * Get Retail WH Code From Cache
     * @param bizMsg NWAL1770CMsg
     * @param rtlWhNm Retail WH Name
     * @param rtlWhCdMap Cache Map
     * @return Retail WH Code From Cache
     */
    private static String getRtlWhCdFromCache(NWAL1770CMsg bizMsg, String rtlWhNm, Map<String, String> rtlWhCdMap) {

        if (rtlWhCdMap.containsKey(rtlWhNm)) {
            return rtlWhCdMap.get(rtlWhNm);
        }

        S21SsmEZDResult ssmResult = NWAL1770Query.getInstance().getRtlWhCd(bizMsg, rtlWhNm);
        if (ssmResult.isCodeNormal()) {
            String rtlWhCd = (String) ssmResult.getResultObject();
            rtlWhCdMap.put(rtlWhNm, rtlWhCd);
            return rtlWhCd;
        }

        return null;
    }

    /**
     * Get Retail Sub WH Code From Cache
     * @param bizMsg NWAL1770CMsg
     * @param rtlWhCd Retail WH Code
     * @param rtlSwhNm Retail Sub WH Name
     * @param rtlSwhCdMap Cache Map
     * @return Retail Sub WH Code From Cache
     */
    private static String getRtlSwhCdFromCache(NWAL1770CMsg bizMsg, String rtlWhCd, String rtlSwhNm, Map<Map<String, String>, String> rtlSwhCdMap) {

        Map<String, String> mapKey = new HashMap<String, String>();
        mapKey.put("rtlWhCd", rtlWhCd);
        mapKey.put("rtlSwhNm", rtlSwhNm);

        if (rtlSwhCdMap.containsKey(mapKey)) {
            return rtlSwhCdMap.get(mapKey);
        }

        S21SsmEZDResult ssmResult = NWAL1770Query.getInstance().getRtlSubWhCd(bizMsg, rtlWhCd, rtlSwhNm);
        if (ssmResult.isCodeNormal()) {
            String rtlSwhCd = (String) ssmResult.getResultObject();
            rtlSwhCdMap.put(mapKey, rtlSwhCd);
            return rtlSwhCd;
        }

        return null;
    }

    public static boolean checkPoNum(NWAL1770CMsg bizMsg) {
        boolean errFlg = false;

        String trxRuleTp = NWAL1770CommonLogicForCustomer.getTrxRuleTp(bizMsg);

        NMZC610001PMsg pMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_TRANSACTION);
        ZYPEZDItemValueSetter.setValue(pMsg.dsTrxRuleTpCd, trxRuleTp);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_I1, bizMsg.sellToCustCd);
        // 2019/01/29 QC#30036 Add Start
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, bizMsg.soldToCustLocCd);
        // 2019/01/29 QC#30036 Add End
        new NMZC610001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (pMsg.TransactionRuleList.getValidCount() > 0) {
            for (int i = 0; i < pMsg.TransactionRuleList.getValidCount(); i++) {
                String dsCrCardReqFlg = pMsg.TransactionRuleList.no(i).dsPoReqFlg.getValue();
                if (ZYPConstant.FLG_ON_Y.equals(dsCrCardReqFlg)) {
                    if (!ZYPCommonFunc.hasValue(bizMsg.custIssPoNum)) {
                        errFlg = true;
                        break;
                    }
                }
            }
        }

        return errFlg;
    }
    
    // Add Start 2016/08/26 S21_NA#16067
    /**
     * Get Varchar Const Data List
     * @param glblCmpyCd Global Company Code
     * @param varCharConstKey Const Key
     * @return List<String>
     */
    public static List<String> getVarCharConstDataList(String glblCmpyCd, String varCharConstKey) {

        List<String> varcharConstValueList = new ArrayList<String>(0);

        String varcharConstValue = ZYPCodeDataUtil.getVarCharConstValue(varCharConstKey, glblCmpyCd);
        if (null == varcharConstValue || varcharConstValue.length() == 0) {
            return varcharConstValueList;
        }

        String[] varcharConstValueArray = varcharConstValue.split(",");
        for (String val : varcharConstValueArray) {
            varcharConstValueList.add(val);
        }

        return varcharConstValueList;
    }
    // Add End 2016/08/26 S21_NA#16067

    // Add Start 2016/09/21 S21_NA#14578
    /**
     * Check Sales Credit Info
     * @param bizMsg NWAL1770CMsg
     * @return No Error : true
     */
    public static boolean checkSlsCrInfo(NWAL1770CMsg bizMsg) {

        boolean hasQuota = false;
        BigDecimal totalCrPct = BigDecimal.ZERO;

        // Salesrep List Check
        for (int index = 0; index < bizMsg.D.getValidCount(); index++) {

            NWAL1770_DCMsg salesRepMsg = bizMsg.D.no(index);
            
            if (!ZYPCommonFunc.hasValue(salesRepMsg.xxRqstTpCd_D) //
                    || NWZC150001Constant.RQST_TP_SLS_CR_NEW.equals(salesRepMsg.xxRqstTpCd_D.getValue()) //
                    || NWZC150001Constant.RQST_TP_SLS_CR_MODIFY.equals(salesRepMsg.xxRqstTpCd_D.getValue())) {

                if (!ZYPCommonFunc.hasValue(salesRepMsg.slsRepTocCd_D)) {
                    bizMsg.setMessageInfo(NWAM0053E);
                    return false;
                }
                if (!ZYPCommonFunc.hasValue(salesRepMsg.slsRepRoleTpCd_D)) {
                    bizMsg.setMessageInfo(NWZM1338E);
                    return false;
                }
                if (!ZYPCommonFunc.hasValue(salesRepMsg.slsRepCrPct_D)) {
                    bizMsg.setMessageInfo(NWZM1344E);
                    return false;
                }
                if (!ZYPCommonFunc.hasValue(salesRepMsg.slsCrQuotFlg_D)) {
                    bizMsg.setMessageInfo(NWZM1345E);
                    return false;
                }

                if (ZYPConstant.FLG_ON_Y.equals(salesRepMsg.slsCrQuotFlg_D.getValue())) {
                    hasQuota = true;
                    if (ZYPCommonFunc.hasValue(salesRepMsg.slsRepCrPct_D)) {
                        if (BigDecimal.ZERO.compareTo(salesRepMsg.slsRepCrPct_D.getValue()) == 0) {
                            bizMsg.setMessageInfo(NWZM1388E);
                            return false;
                        } else {
                            totalCrPct = totalCrPct.add(salesRepMsg.slsRepCrPct_D.getValue());
                        }
                    }
                }
                if (ZYPConstant.FLG_OFF_N.equals(salesRepMsg.slsCrQuotFlg_D.getValue())) {
                    salesRepMsg.slsRepCrPct_D.setValue(BigDecimal.ZERO);
                }
            }
        }

        if (!hasQuota) {
            bizMsg.setMessageInfo(NWZM2014E);
            return false;
        }

        if (totalCrPct.compareTo(BigDecimal.valueOf(100)) != 0) {
            bizMsg.setMessageInfo(NWZM1387E);
            return false;
        }
        return true;
    }
    // Add End 2016/09/21 S21_NA#14578
    

    // Add Start 2017/12/20 QC#20164(L3 Sol#356)
    /**
     * Check Conatct
     * @param bizMsg
     * @return Check Result
     */
    private static boolean checkContact(NWAL1770CMsg bizMsg){
        boolean ret = true;

        S21SsmEZDResult  result = NWAL1770QueryForCustomer.getInstance().getContactType(bizMsg);

        if (!result.isCodeNormal()){
            return ret;
        }

        String contactTpStr = (String)result.getResultObject();

        if (!ZYPCommonFunc.hasValue(contactTpStr)){
            return ret;
        }

        String[] contactTpList = contactTpStr.split(",");

        if (contactTpList == null){
            return ret;
        }

        for (String tmp : contactTpList){
            String contactTp = tmp.trim();

            if (!ZYPCommonFunc.hasValue(contactTp)){
                continue;
            }

            ret = checkContact(bizMsg, contactTp);

            if (ret == false){
                String contactTpNm = toCtacTpNm(contactTp, bizMsg.glblCmpyCd.getValue());

                bizMsg.setMessageInfo(ZZM9000E, new String[]{String.format("Contact Role:%s", contactTpNm)});
                int max = bizMsg.A.getValidCount();

                for (int cnt = 0; cnt < max; cnt ++){
                    bizMsg.A.no(cnt).ctacPsnTpCd_A.setErrorInfo(1, ZZM9000E, new String[]{String.format("Contact Role:%s", contactTpNm)});
                    bizMsg.A.no(cnt).ctacCustRefTpCd_AP.setErrorInfo(1, ZZM9000E, new String[]{String.format("Contact Role:%s", contactTpNm)});
                }

               break;
           }
        }
        return ret;
    }

    /**
     * Check Conatct
     * @param bizMsg
     * @param contactTp
     * @return Check Result
     */
    private static boolean checkContact(NWAL1770CMsg bizMsg, String contactTp){
        boolean ret = false;
        int max = bizMsg.A.getValidCount();

        for (int cnt = 0; cnt < max; cnt++){
            if (contactTp.equals(bizMsg.A.no(cnt).ctacPsnTpCd_A.getValue())){
                ret = true;
                break;
            }
        }
        return ret;
    }

    /**
     * To Contact Type Name
     * @param ctacTpCd
     * @param glblCmpyCd
     * @return Contact Type Name
     */
    private static String toCtacTpNm(String ctacTpCd, String glblCmpyCd){
        String ret = ctacTpCd;

        if (ZYPCommonFunc.hasValue(ctacTpCd) == false){
            return ret;
        }

        CTAC_TPTMsg tmsg = new CTAC_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.ctacTpCd, ctacTpCd);
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, glblCmpyCd);
        CTAC_TPTMsg cTacTMsg = (CTAC_TPTMsg) S21FastTBLAccessor.findByKey(tmsg);

        if (cTacTMsg != null){
            if (ZYPCommonFunc.hasValue(cTacTMsg.ctacTpNm)){
                ret = cTacTMsg.ctacTpNm.getValue();
            }
        }

        return ret;
    }
    // Add End 2017/12/20 QC#20164(L3 Sol#356)

    // QC#56978 Add Start
    private static String[] getMsgParamArray(NWZC167002_xxMsgIdListPMsg xxMsg) {
        List<String> msgParamList = new ArrayList<String>(0);
        if (ZYPCommonFunc.hasValue(xxMsg.xxMsgPrmTxt_0)) {
            msgParamList.add(xxMsg.xxMsgPrmTxt_0.getValue());
        }
        if (ZYPCommonFunc.hasValue(xxMsg.xxMsgPrmTxt_1)) {
            msgParamList.add(xxMsg.xxMsgPrmTxt_1.getValue());
        }
        if (ZYPCommonFunc.hasValue(xxMsg.xxMsgPrmTxt_2)) {
            msgParamList.add(xxMsg.xxMsgPrmTxt_2.getValue());
        }
        if (ZYPCommonFunc.hasValue(xxMsg.xxMsgPrmTxt_3)) {
            msgParamList.add(xxMsg.xxMsgPrmTxt_3.getValue());
        }
        if (ZYPCommonFunc.hasValue(xxMsg.xxMsgPrmTxt_4)) {
            msgParamList.add(xxMsg.xxMsgPrmTxt_4.getValue());
        }
        if (msgParamList.size() > 0) {
            return msgParamList.toArray(new String[0]);
        } else {
            return new String[]{};
        }
    }
    // QC#56978 Add End

    // ADD START 2022/08/19 H.Watanabe [QC#60255]
    /**
     * checkCarrAcctNumValidation
     * @param cMsg
     * @return true : no error / false : error
     */
    public static boolean checkCarrAcctNumValidation(NWAL1770CMsg bizMsg) {
        if (!NWXC150001DsCheck.chkCarrierAccountNumberNeedValidation(bizMsg.glblCmpyCd.getValue(), bizMsg.carrAcctNum.getValue(), bizMsg.shpgSvcLvlCd.getValue(), bizMsg.carrSvcLvlCd.getValue())) {
            bizMsg.carrAcctNum.setErrorInfo(1, NWAM8465E, new String[] {"Carrier Acct Num" });
            return false;
        }
        return true;
    }
    // ADD END   2022/08/19 H.Watanabe [QC#60255]

    // 2023/11/06 QC#61924 Add Start
    /**
     * hasDeactivateAccountOrLocation
     * @param bizMsg NWAL1770CMsg
     * @param glblMsg NWAL1770SMsg
     * @return boolean
     */
    public static boolean hasDeactivateAccountOrLocation(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
            return true;
        }

        boolean errorFlg = false;

        BILL_TO_CUSTTMsg billToCustTMsg = new BILL_TO_CUSTTMsg();
        billToCustTMsg = getBillToCustByCondition(bizMsg.glblCmpyCd.getValue(), bizMsg.billToCustCd.getValue());
        if (null != billToCustTMsg) {
            if (ZYPConstant.FLG_ON_Y.equals(billToCustTMsg.deacNewTrxFlg.getValue())) {
                bizMsg.billToCustCd.setErrorInfo(1, NWAM0987E, new String[] {bizMsg.billToCustCd.getValue() });
                errorFlg = true;
            }
        }

        SHIP_TO_CUSTTMsg shipToCustTMsg = new SHIP_TO_CUSTTMsg();
        shipToCustTMsg = getShipToCustByCondition(bizMsg.glblCmpyCd.getValue(), bizMsg.shipToCustCd.getValue());
        if (null != shipToCustTMsg) {
            if (ZYPConstant.FLG_ON_Y.equals(shipToCustTMsg.deacNewTrxFlg.getValue())) {
                bizMsg.shipToCustCd.setErrorInfo(1, NWAM0988E, new String[] {bizMsg.shipToCustCd.getValue() });
                errorFlg = true;
            }
        }

        BILL_TO_CUSTTMsg soldToCustTMsg = new BILL_TO_CUSTTMsg();
        soldToCustTMsg = getBillToCustByCondition(bizMsg.glblCmpyCd.getValue(), bizMsg.soldToCustLocCd.getValue());
        if (null != soldToCustTMsg) {
            if (ZYPConstant.FLG_ON_Y.equals(soldToCustTMsg.deacNewTrxFlg.getValue())) {
                bizMsg.soldToCustLocCd.setErrorInfo(1, NWAM0989E, new String[] {bizMsg.soldToCustLocCd.getValue() });
                errorFlg = true;
            }
        }

        if (errorFlg) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * <pre>
     * get Bill To Customer
     * @param glblCmpyCd Global Company Code
     * @param billToCustCd Bill To Customer Code
     * @return BILL_TO_CUSTTMsg, if system can't find BILL_TO_CUST record, return null
     * </pre>
     */
    private static BILL_TO_CUSTTMsg getBillToCustByCondition(String glblCmpyCd, String billToCustCd) {

        BILL_TO_CUSTTMsg condition = new BILL_TO_CUSTTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("billToCustCd01", billToCustCd);
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        BILL_TO_CUSTTMsgArray tmsgArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);

        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0);
        } else {
            return null;
        }
    }

    /**
     * <pre>
     * get Ship To Customer
     * @param glblCmpyCd Global Company Code
     * @param shipToCustCd Ship To Customer Code
     * @return SHIP_TO_CUSTTMsg, if system can't find SHIP_TO_CUST record, return null
     * </pre>
     */
    private static SHIP_TO_CUSTTMsg getShipToCustByCondition(String glblCmpyCd, String shipToCustCd) {

        SHIP_TO_CUSTTMsg condition = new SHIP_TO_CUSTTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("shipToCustCd01", shipToCustCd);
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        SHIP_TO_CUSTTMsgArray tmsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);

        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0);
        } else {
            return null;
        }
    }
    // 2023/11/06 QC#61924 Add End
}
