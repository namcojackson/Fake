/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1360.common;

import static business.blap.NPAL1360.constant.NPAL1360Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCItem;
import parts.common.EZDMsg;
import parts.common.EZDPMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1360.NPAL1360CMsg;
import business.blap.NPAL1360.NPAL1360Query;
import business.blap.NPAL1360.NPAL1360SMsg;
import business.blap.NPAL1360.NPAL1360_SCMsg;
import business.blap.NPAL1360.NPAL1360_WCMsg;
import business.db.DS_WRK_ORD_TPTMsg;
import business.db.INVTYTMsg;
import business.db.KIT_BOM_WRKTMsg;
import business.db.RTL_SWHTMsg;
import business.db.RTL_WHTMsg;
import business.db.SCE_ORD_TPTMsg;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_ORD_DTLTMsg;
import business.db.SWHTMsg;
import business.db.WRK_ORDTMsg;
import business.db.WRK_ORD_DTLTMsg;
import business.db.WRK_ORD_SERTMsg;
import business.parts.NLZC200001PMsg;
import business.parts.NLZC201001PMsg;
import business.parts.NLZC205001PMsg;
import business.parts.NLZC207001PMsg;
import business.parts.NLZC304001PMsg;
import business.parts.NLZC403001PMsg;
import business.parts.NPZC103001PMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NWZC003001PMsg;
import business.parts.NWZC107001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC200001.NLZC200001;
import com.canon.cusa.s21.api.NLZ.NLZC201001.NLZC201001;
import com.canon.cusa.s21.api.NLZ.NLZC205001.NLZC205001;
import com.canon.cusa.s21.api.NLZ.NLZC207001.NLZC207001;
import com.canon.cusa.s21.api.NLZ.NLZC304001.NLZC304001;
import com.canon.cusa.s21.api.NLZ.NLZC403001.NLZC403001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.NPZC103001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NWZ.NWZC003001.NWZC003001;
import com.canon.cusa.s21.api.NWZ.NWZC107001.NWZC107001;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWHData;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.common.ZYPFormatUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DRCT_SHIP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_WRK_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PUT_AWAY_CHK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WRK_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WRK_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Business ID : NPAL1360 Kitting Work Order Entry
 * Function Name : Common Logic
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/12/2016   CITS            Keiichi Masaki  Create          N/A
 * 11/15/2016   CITS            Y.IWASAKI       Update          QC#15826
 * 01/26/2017   CITS            Y.IWASAKI       Update          QC#17242
 * 01/31/2017   CITS            Y.IWASAKI       Update          QC#16109
 * 02/16/2017   CITS            R.Shimamoto     Update          QC#17439
 * 08/30/2017   CITS            T.Hakodate      Update          Sol#069(QC#18270)
 * 03/14/2018   CITS            K.Fukumura      Update          QC#22324
 * 07/10/2018   CITS            K.Ogino         Update          QC#26243
 * 10/24/2018   CITS            M.Naito         Update          QC#28886
 *</pre>
 */

public class NPAL1360CommonLogic {

    /**
     * Set Kit Item Name
     * @param cMsg NPAL1360CMsg
     * @return boolean
     */
    public static boolean setKitItemName(NPAL1360CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_KIT_ITEM, MDSE_TP.KIT);
        ssmParam.put(DB_PARAM_ROWNUM, ROWNUM_1);

        S21SsmEZDResult result = NPAL1360Query.getInstance().getKitItemName(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();
            Map record = resultList.get(INDEX_0);

            ZYPEZDItemValueSetter.setValue(cMsg.fnshMdseDescShortTxt, (String) record.get(DB_COLUMN_MDSE_DESC_SHORT_TXT));
            ZYPEZDItemValueSetter.setValue(cMsg.fnshGoodsMdseNm, (String) record.get(DB_COLUMN_MDSE_NM));
            ZYPEZDItemValueSetter.setValue(cMsg.firstProdCtrlCd, (String) record.get(DB_COLUMN_FIRST_PROD_CTRL_CD));
            ZYPEZDItemValueSetter.setValue(cMsg.rcvSerTakeFlg, (String) record.get(DB_COLUMN_RCV_SER_TAKE_FLG));
        } else {
            cMsg.fnshMdseDescShortTxt.clear();
            cMsg.fnshGoodsMdseCd.setErrorInfo(1, NZZM0010E, new String[] {cMsg.fnshGoodsMdseCd.getValue() });
            return false;
        }
        return true;
    }

    /**
     * Set Warehouse Name
     * @param cMsg NPAL1360CMsg
     * @return boolean
     */
    public static boolean setWarehouseName(NPAL1360CMsg cMsg) {

        RTL_WHTMsg tMsg = new RTL_WHTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.rtlWhCd.setValue(cMsg.rtlWhCd.getValue());

        RTL_WHTMsg existTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (existTMsg != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm, existTMsg.rtlWhNm.getValue());
        } else {
            cMsg.rtlWhNm.clear();
            cMsg.rtlWhCd.setErrorInfo(1, NZZM0010E, new String[] {cMsg.rtlWhCd.getValue() });
            return false;
        }

        return true;
    }

    /**
     * Set Sub Warehouse Name
     * @param cMsg NPAL1360CMsg
     * @return boolean
     */
    public static boolean setSubWarehouseName(NPAL1360CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.rtlWhCd) && ZYPCommonFunc.hasValue(cMsg.cpltRtlSwhCd)) {

            SWHTMsg tMsg = new SWHTMsg();

            tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
            tMsg.rtlSwhCd.setValue(cMsg.cpltRtlSwhCd.getValue());

            SWHTMsg existTMsg = (SWHTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (existTMsg != null) {
                ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm, existTMsg.rtlSwhNm.getValue());
            } else {
                cMsg.rtlSwhNm.clear();
                cMsg.cpltRtlSwhCd.setErrorInfo(1, NZZM0010E, new String[] {cMsg.cpltRtlSwhCd.getValue() });
                return false;
            }
        } else if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd) && ZYPCommonFunc.hasValue(cMsg.cpltRtlSwhCd)) {

            RTL_SWHTMsg tMsg = new RTL_SWHTMsg();

            tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
            tMsg.rtlWhCd.setValue(cMsg.rtlWhCd.getValue());
            tMsg.rtlSwhCd.setValue(cMsg.cpltRtlSwhCd.getValue());

            RTL_SWHTMsg existTMsg = (RTL_SWHTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (existTMsg != null) {
                ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm, existTMsg.rtlSwhNm.getValue());
                ZYPEZDItemValueSetter.setValue(cMsg.invtyLocCd, tMsg.invtyLocCd);
            } else {
                cMsg.rtlSwhNm.clear();
                cMsg.cpltRtlSwhCd.setErrorInfo(1, NZZM0010E, new String[] {cMsg.cpltRtlSwhCd.getValue() });
                cMsg.setMessageInfo(NPAM1361E, new String[] {COMBINATION_WH_SWH });
                return false;
            }

            List<String> locationTypeList = new ArrayList<String>();
            locationTypeList.add(LOC_TP.WAREHOUSE);

            NMXC100001EnableWHData locData = NMXC100001EnableWH.checkEnableWH(cMsg.glblCmpyCd.getValue(), cMsg.rtlWhCd.getValue() + cMsg.cpltRtlSwhCd.getValue(), BIZ_APP_ID, locationTypeList, null, ZYPConstant.FLG_ON_Y);

            if (ZYPCommonFunc.hasValue(locData.getXxMsgId())) {
                cMsg.setMessageInfo(locData.getXxMsgId());
                return false;
            }

        }
        return true;
    }

    /**
     * Open Window Kit Serial#
     * @param cMsg NPAL1360CMsg
     */
    public static void openWin_KitSerial(NPAL1360CMsg cMsg) {

        // WH Check
        if (!setWarehouseName(cMsg)) {
            return;
        }
        // Kit Item Check
        if (!setKitItemName(cMsg)) {
            return;
        }
        // Serialize Item Check
        if (ZYPConstant.FLG_OFF_N.equals(cMsg.rcvSerTakeFlg.getValue())) {
            cMsg.fnshGoodsMdseCd.setErrorInfo(1, NPAM1519E, new String[] {cMsg.fnshGoodsMdseCd.getValue() });
            return;
        }
        // QC#16109
        // Kit serial is always set by the popup even when requested quantity is 1.
        // Before change, kit serial was set into input text if requested quantity is 1.
        /*
        // Order Quantity Check
        if (BigDecimal.ONE.equals(cMsg.fnshGoodsOrdQty.getValue())) {
            cMsg.serNum.setErrorInfo(1, NPAM1520E);
            return;
        }
        */

        if (ZYPCommonFunc.hasValue(cMsg.wrkOrdStsCd) && cMsg.S.getValidCount() == 0) {
            Map<String, Object> ssmParamSerial = new HashMap<String, Object>();
            ssmParamSerial.put(DB_PARAM_CMSG, cMsg);
            ssmParamSerial.put(DB_PARAM_ROWNUM, cMsg.S.length());

            S21SsmEZDResult resultSerial = NPAL1360Query.getInstance().getWorkOrderSerial(ssmParamSerial);

            if (resultSerial.isCodeNormal()) {
                List<Map> resultListSerial = (List<Map>) resultSerial.getResultObject();

                cMsg.S.setValidCount(resultListSerial.size());
                for (int i = 0; i < resultListSerial.size(); i++) {
                    Map recordSerial = resultListSerial.get(i);

                    ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).wrkOrdDtlLineNum_S1, (String) recordSerial.get(DB_COLUMN_WRK_ORD_DTL_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).serNum_S1, (String) recordSerial.get(DB_COLUMN_SER_NUM));
                }

                // QC#16109
                // Concatenate and display serials into cMsg.serNum
                ZYPEZDItemValueSetter.setValue(cMsg.serNum, concatSerials(cMsg, KIT_ITEM_LINE_NUM));

                for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                    if (ZYPConstant.FLG_OFF_N.equals(cMsg.A.no(i).rcvSerTakeFlg_D1.getValue())) {
                        continue;
                    }
                    for (int j = 0; j < cMsg.S.getValidCount(); j++) {
                        if (cMsg.A.no(i).wrkOrdDtlLineNum_A1.getValue().equals(cMsg.S.no(j).wrkOrdDtlLineNum_S1.getValue())) {
                            if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).serNum_A1)) {
                                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).serNum_A1, cMsg.S.no(j).serNum_S1);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Open Window Supply Serial#
     * @param cMsg NPAL1360CMsg
     */
    public static void openWin_SupplySerial(NPAL1360CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.wrkOrdStsCd) && cMsg.S.getValidCount() == 0) {
            Map<String, Object> ssmParamSerial = new HashMap<String, Object>();
            ssmParamSerial.put(DB_PARAM_CMSG, cMsg);
            ssmParamSerial.put(DB_PARAM_ROWNUM, cMsg.S.length());

            S21SsmEZDResult resultSerial = NPAL1360Query.getInstance().getWorkOrderSerial(ssmParamSerial);

            if (resultSerial.isCodeNormal()) {
                List<Map> resultListSerial = (List<Map>) resultSerial.getResultObject();

                cMsg.S.setValidCount(resultListSerial.size());
                for (int i = 0; i < resultListSerial.size(); i++) {
                    Map recordSerial = resultListSerial.get(i);

                    ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).wrkOrdDtlLineNum_S1, (String) recordSerial.get(DB_COLUMN_WRK_ORD_DTL_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).serNum_S1, (String) recordSerial.get(DB_COLUMN_SER_NUM));
                }

                // QC#16109
                // Concatenate and display serials into cMsg.serNum
                ZYPEZDItemValueSetter.setValue(cMsg.serNum, concatSerials(cMsg, KIT_ITEM_LINE_NUM));

                for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                    if (ZYPConstant.FLG_OFF_N.equals(cMsg.A.no(i).rcvSerTakeFlg_D1.getValue())) {
                        continue;
                    }

                    // QC#16109
                    // Concatenate and display serials into cMsg.serNum
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).serNum_A1, concatSerials(cMsg, cMsg.A.no(i).wrkOrdDtlLineNum_A1.getValue()));
                }
            }
        }
    }

    /**
     * Search
     * @param cMsg NPAL1360CMsg
     * @param sMsg NPAL1360SMsg
     * @param eventName String
     */
    public static void search(NPAL1360CMsg cMsg, NPAL1360SMsg sMsg, String eventName) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_LOC_STS_CD, LOC_STS.DC_STOCK);
        ssmParam.put(DB_PARAM_STK_STS_CD, STK_STS.GOOD);
        ssmParam.put(DB_PARAM_ROWNUM, cMsg.A.length() + 1);

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.S);
        cMsg.serNum.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.S);
        sMsg.serNum.clear();

        S21SsmEZDResult result = NPAL1360Query.getInstance().search(ssmParam, cMsg);

        if (result.isCodeNormal()) {
            // Set Header Columns
            ZYPEZDItemValueSetter.setValue(cMsg.xxRqstTs_WH, cMsg.A.no(INDEX_0).xxRqstTs_W1);
            ZYPEZDItemValueSetter.setValue(cMsg.xxRqstTz_WH, cMsg.A.no(INDEX_0).xxRqstTz_W1);
            ZYPEZDItemValueSetter.setValue(cMsg.xxRqstTs_DH, cMsg.A.no(INDEX_0).xxRqstTs_D1);
            ZYPEZDItemValueSetter.setValue(cMsg.xxRqstTz_DH, cMsg.A.no(INDEX_0).xxRqstTz_D1);
            ZYPEZDItemValueSetter.setValue(cMsg.wrkOrdNum, cMsg.A.no(INDEX_0).wrkOrdNum_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.dsWrkOrdTpCd_SL, cMsg.A.no(INDEX_0).dsWrkOrdTpCd_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.wrkOrdStsCd, cMsg.A.no(INDEX_0).wrkOrdStsCd_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.dsWrkOrdStsNm, cMsg.A.no(INDEX_0).dsWrkOrdStsNm_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.wrkOrdDt, cMsg.A.no(INDEX_0).wrkOrdDt_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum, cMsg.A.no(INDEX_0).prchReqNum_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd, cMsg.A.no(INDEX_0).rtlWhCd_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm, cMsg.A.no(INDEX_0).rtlWhNm_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.cpltRtlSwhCd, cMsg.A.no(INDEX_0).cpltRtlSwhCd_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm, cMsg.A.no(INDEX_0).rtlSwhNm_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, cMsg.A.no(INDEX_0).rqstRcvDt_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.fnshGoodsMdseCd, cMsg.A.no(INDEX_0).fnshGoodsMdseCd_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.fnshMdseDescShortTxt, cMsg.A.no(INDEX_0).fnshMdseDescShortTxt_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.fnshGoodsMdseNm, cMsg.A.no(INDEX_0).fnshGoodsMdseNm_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.firstProdCtrlCd, cMsg.A.no(INDEX_0).firstProdCtrlCd_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.rcvSerTakeFlg, cMsg.A.no(INDEX_0).rcvSerTakeFlg_H1);
            ZYPEZDItemValueSetter.setValue(cMsg.basePkgUomCd, cMsg.A.no(INDEX_0).basePkgUomCd_H1);
            ZYPEZDItemValueSetter.setValue(cMsg.fnshGoodsOrdQty, cMsg.A.no(INDEX_0).fnshGoodsOrdQty_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.invtyAllocQty, cMsg.A.no(INDEX_0).fnshGoodsOrdQty_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.fnshGoodsRcvQty, cMsg.A.no(INDEX_0).fnshGoodsRcvQty_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.fnshGoodsBalQty, cMsg.A.no(INDEX_0).fnshGoodsBalQty_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.fnshGoodsCancQty, cMsg.A.no(INDEX_0).fnshGoodsCancQty_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.wrkOrdMsgTxt, cMsg.A.no(INDEX_0).wrkOrdMsgTxt_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.cmpsnRevnNum, cMsg.A.no(INDEX_0).cmpsnRevnNum_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.trxSrcTpCd, cMsg.A.no(INDEX_0).trxSrcTpCd_A1);

            // Get Kit Item Effect Date
            Map<String, Object> ssmParamEffDt = new HashMap<String, Object>();
            ssmParamEffDt.put(DB_PARAM_CMSG, cMsg);
            ssmParamEffDt.put(DB_PARAM_HIST_ACT_NM, HIST_ACT_NM_DELETE);

            S21SsmEZDResult resultEffDt = NPAL1360Query.getInstance().getKitItemEffectDate(ssmParamEffDt);

            if (resultEffDt.isCodeNormal()) {
                List<Map> resultListEffDt = (List<Map>) resultEffDt.getResultObject();
                Map recordEffdt = resultListEffDt.get(INDEX_0);

                ZYPEZDItemValueSetter.setValue(cMsg.effFromDt, (String) recordEffdt.get(DB_COLUMN_EFF_FROM_DT));
                ZYPEZDItemValueSetter.setValue(cMsg.effThruDt, (String) recordEffdt.get(DB_COLUMN_EFF_THRU_DT));
            }

            // Get Available Inventory
            for (int i = 0; i < (cMsg.A.getValidCount()); i++) {
                getAvailableInventory(cMsg, sMsg, i);
            }

            // Get Serial#
            Map<String, Object> ssmParamSerial = new HashMap<String, Object>();
            ssmParamSerial.put(DB_PARAM_CMSG, cMsg);
            ssmParamSerial.put(DB_PARAM_ROWNUM, cMsg.S.length());

            S21SsmEZDResult resultSerial = NPAL1360Query.getInstance().getWorkOrderSerial(ssmParamSerial);

            if (resultSerial.isCodeNormal()) {
                List<Map> resultListSerial = (List<Map>) resultSerial.getResultObject();

                cMsg.S.setValidCount(resultListSerial.size());
                for (int i = 0; i < resultListSerial.size(); i++) {
                    Map recordSerial = resultListSerial.get(i);

                    ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).wrkOrdDtlLineNum_S1, (String) recordSerial.get(DB_COLUMN_WRK_ORD_DTL_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).serNum_S1, (String) recordSerial.get(DB_COLUMN_SER_NUM));
                }

                // QC#16109
                // Concatenate and display serials into cMsg.serNum
                ZYPEZDItemValueSetter.setValue(cMsg.serNum, concatSerials(cMsg, KIT_ITEM_LINE_NUM));
                for (int j = 0; j < (cMsg.A.getValidCount()); j++) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).serNum_A1, concatSerials(cMsg, cMsg.A.no(j).wrkOrdDtlLineNum_A1.getValue()));
                }
            }

            // Max Record Over
            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > cMsg.A.length()) {
                cMsg.setMessageInfo(NMAM8181W, new String[] {MAX_ROW, MAX_ROW });
            }

            if (ZYPCommonFunc.hasValue(cMsg.wrkOrdNum_SV)) {
                cMsg.setMessageInfo(NPAM1526I, new String[] {cMsg.wrkOrdNum_SV.getValue() });
                cMsg.wrkOrdNum_SV.clear();
            } else {
                if (eventName.equals(EVENT_NM_NPAL1360_SEARCH)) {
                    cMsg.setMessageInfo(ZZZM9003I, new String[] {BTN_SEARCH });
                } else if (eventName.equals(EVENT_NM_NPAL1360_CMN_SUBMIT)) {
                    cMsg.setMessageInfo(ZZZM9003I, new String[] {BTN_SUBMIT });
                } else if (eventName.equals(EVENT_NM_NPAL1360_CANCEL)) {
                    cMsg.setMessageInfo(ZZZM9003I, new String[] {BTN_CANCEL });
                }
            }

        } else {
            cMsg.setMessageInfo(NMAM0038I);
        }
        // Copy from cMsg to sMsg
        EZDMsg.copy(cMsg, null, sMsg, null);
    }

    /**
     * Component
     * @param cMsg NPAL1360CMsg
     * @param sMsg NPAL1360SMsg
     */
    public static void component(NPAL1360CMsg cMsg, NPAL1360SMsg sMsg) {

        if (!checkWhSwH(cMsg, sMsg)) {
            return;
        }

        if (!checkKitItem(cMsg, sMsg)) {
            return;
        }

        clearLineInfo(cMsg, sMsg);

        // Get Kit Component Items Information
        getKitComponentItemsInfo(cMsg, sMsg);

    }

    /**
     * Check WH/SWH
     * @param cMsg NPAL1360CMsg
     * @param sMsg NPAL1360SMsg
     * @return boolean
     */
    public static boolean checkWhSwH(NPAL1360CMsg cMsg, NPAL1360SMsg sMsg) {

        if (!setWarehouseName(cMsg)) {
            return false;
        }

        if (!setSubWarehouseName(cMsg)) {
            return false;
        }

        EZDMsg.copy(cMsg, null, sMsg, null);

        return true;

    }

    /**
     * Check Line WH/SWH
     * @param cMsg NPAL1360CMsg
     * @param sMsg NPAL1360SMsg
     * @return boolean
     */
    public static boolean checkLineWhSwH(NPAL1360CMsg cMsg, NPAL1360SMsg sMsg) {

        RTL_SWHTMsg tMsg = new RTL_SWHTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.rtlWhCd.setValue(cMsg.rtlWhCd.getValue());

        for (int i = 0; i < (cMsg.A.getValidCount()); i++) {

            if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).splyRtlSwhCd_A1)) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).splyRtlSwhCd_A1, cMsg.cpltRtlSwhCd);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).splyRtlSwhCd_A1, sMsg.cpltRtlSwhCd);
            }

            tMsg.rtlSwhCd.setValue(cMsg.A.no(i).splyRtlSwhCd_A1.getValue());

            RTL_SWHTMsg existTMsg = (RTL_SWHTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (existTMsg == null) {
                cMsg.rtlWhCd.setErrorInfo(1, NPAM1361E, new String[] {COMBINATION_WH_SWH });
                cMsg.A.no(i).splyRtlSwhCd_A1.setErrorInfo(1, NPAM1361E, new String[] {COMBINATION_WH_SWH });
                return false;
            }
        }

        return true;
    }

    /**
     * Check Serial#
     * @param cMsg NPAL1360CMsg
     * @return boolean
     */
    public static boolean checkSerial(NPAL1360CMsg cMsg) {

        ZYPTableUtil.clear(cMsg.W);
        int validCnt = 0;

        // QC#16109
        // All serials are set into cMsg.S.serNum_S1.
        /*
        // QC#15826
        // If one serial is set by hand, serial is put into cMsg.serNum and the serial is not set into cMsg.S.serNum_S1.
        // If multiple serials are set from dialog, all serials are put into cMsg.S.serNum_S1 and first serial is displayed in cMsg.serNum.
        // In this logic, if QTY is one, put the serial at cMsg.serNum into cMsg.S.serNum_S1 so that subsequent logic can refer all serials from cMsg.S.serNum_S1.
        if (BigDecimal.ONE.equals(cMsg.fnshGoodsOrdQty.getValue())) {
            boolean found = false;
            for (int i = 0; i < cMsg.S.getValidCount(); ++i) {
                if (KIT_ITEM_LINE_NUM.equals(cMsg.S.no(i).wrkOrdDtlLineNum_S1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).serNum_S1, cMsg.serNum);
                    found = true;
                    break;
                }
            }
            if (!found) {
                ZYPEZDItemValueSetter.setValue(cMsg.S.no(cMsg.S.getValidCount()).wrkOrdDtlLineNum_S1, KIT_ITEM_LINE_NUM);
                ZYPEZDItemValueSetter.setValue(cMsg.S.no(cMsg.S.getValidCount()).serNum_S1, cMsg.serNum);
                cMsg.S.setValidCount(cMsg.S.getValidCount() + 1);
            }
        }
        */

        // QC#15826
        // If one serial is set by hand, serial is put into cMsg.A.serNum_A1 and the serial is not set into cMsg.S.serNum_S1.
        // If multiple serials are set from dialog, all serials are put into cMsg.S.serNum_S1 and first serial is displayed in cMsg.A.serNum_A1.
        // In this logic, if QTY is one, put the serial at cMsg.A.serNum_A1 into cMsg.S.serNum_S1 so that subsequent logic can refer all serials from cMsg.S.serNum_S1.
        for (int i = 0; i < (cMsg.A.getValidCount()); i++) {
            if (BigDecimal.ONE.equals(cMsg.A.no(i).origGoodsOrdQty_A1.getValue())) {
                boolean found = false;
                for (int j = 0; j < cMsg.S.getValidCount(); ++j) {
                    if (cMsg.A.no(i).wrkOrdDtlLineNum_A1.getValue().equals(cMsg.S.no(j).wrkOrdDtlLineNum_S1.getValue())) {
                        ZYPEZDItemValueSetter.setValue(cMsg.S.no(j).serNum_S1, cMsg.A.no(i).serNum_A1);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    ZYPEZDItemValueSetter.setValue(cMsg.S.no(cMsg.S.getValidCount()).wrkOrdDtlLineNum_S1, cMsg.A.no(i).wrkOrdDtlLineNum_A1);
                    ZYPEZDItemValueSetter.setValue(cMsg.S.no(cMsg.S.getValidCount()).serNum_S1, cMsg.A.no(i).serNum_A1);
                    cMsg.S.setValidCount(cMsg.S.getValidCount() + 1);
                }
            }
        }
        // QC#15826
        // Cleanup empty serials
        ZYPTableUtil.clear(cMsg.W);
        validCnt = 0;
        for (int i = 0; i < cMsg.S.getValidCount(); ++i) {
            NPAL1360_SCMsg scMsg = cMsg.S.no(i);
            if (!ZYPCommonFunc.hasValue(scMsg.serNum_S1)) {
                continue;
            }

            NPAL1360_WCMsg wcMsg = cMsg.W.no(validCnt);
            ZYPEZDItemValueSetter.setValue(wcMsg.wrkOrdDtlLineNum_W1, scMsg.wrkOrdDtlLineNum_S1);
            ZYPEZDItemValueSetter.setValue(wcMsg.serNum_W1, scMsg.serNum_S1);
            ++validCnt;
        }
        cMsg.W.setValidCount(validCnt);
        ZYPTableUtil.clear(cMsg.S);
        for (int i = 0; i < cMsg.W.getValidCount(); ++i) {
            NPAL1360_WCMsg wcMsg = cMsg.W.no(i);
            NPAL1360_SCMsg scMsg = cMsg.S.no(i);

            ZYPEZDItemValueSetter.setValue(scMsg.wrkOrdDtlLineNum_S1, wcMsg.wrkOrdDtlLineNum_W1);
            ZYPEZDItemValueSetter.setValue(scMsg.serNum_S1, wcMsg.serNum_W1);
        }
        cMsg.S.setValidCount(cMsg.W.getValidCount());

        ZYPTableUtil.clear(cMsg.W);
        validCnt = 0;
        if (DS_WRK_ORD_TP.UN_KIT.equals(cMsg.dsWrkOrdTpCd_SL.getValue()) && ZYPConstant.FLG_ON_Y.equals(cMsg.rcvSerTakeFlg.getValue())) {
            int kitSerialCnt = 0;

            for (int i = 0; i < (cMsg.S.getValidCount()); i++) {
                if (KIT_ITEM_LINE_NUM.equals(cMsg.S.no(i).wrkOrdDtlLineNum_S1.getValue())) {
                    kitSerialCnt++;
                } else {
                    // Save Component Items Serial#
                    ZYPEZDItemValueSetter.setValue(cMsg.W.no(validCnt).wrkOrdDtlLineNum_W1, cMsg.S.no(i).wrkOrdDtlLineNum_S1);
                    ZYPEZDItemValueSetter.setValue(cMsg.W.no(validCnt).serNum_W1, cMsg.S.no(i).serNum_S1);
                    validCnt++;
                    cMsg.W.setValidCount(validCnt);
                }
            }
            if (kitSerialCnt < cMsg.fnshGoodsOrdQty.getValueInt()) {
                cMsg.serNum.setErrorInfo(1, NAMM0027E, new String[] {KIT_ITEM_SERIAL });
                return false;
            }
        } else if (DS_WRK_ORD_TP.KIT.equals(cMsg.dsWrkOrdTpCd_SL.getValue())) {

            for (int i = 0; i < (cMsg.A.getValidCount()); i++) {

                if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).rcvSerTakeFlg_D1.getValue())) {

                    String lineNum = String.format("%03d", i + 1);
                    int componentSerialCnt = 0;

                    for (int j = 0; j < (cMsg.S.getValidCount()); j++) {
                        if (lineNum.equals(cMsg.S.no(j).wrkOrdDtlLineNum_S1.getValue())) {
                            componentSerialCnt++;
                        } else {
                            // Save Kit Items Serial#
                            if (KIT_ITEM_LINE_NUM.equals(cMsg.S.no(j).wrkOrdDtlLineNum_S1.getValue())) {
                                ZYPEZDItemValueSetter.setValue(cMsg.W.no(validCnt).wrkOrdDtlLineNum_W1, cMsg.S.no(j).wrkOrdDtlLineNum_S1);
                                ZYPEZDItemValueSetter.setValue(cMsg.W.no(validCnt).serNum_W1, cMsg.S.no(j).serNum_S1);
                                validCnt++;
                                cMsg.W.setValidCount(validCnt);
                            }
                        }
                    }
                }
            }
        }

        // QC#15826
        // Execute Serial Validation API
        NLZC403001 nlzc403001Api = new NLZC403001();

        if (DS_WRK_ORD_TP.UN_KIT.equals(cMsg.dsWrkOrdTpCd_SL.getValue()) && ZYPConstant.FLG_ON_Y.equals(cMsg.rcvSerTakeFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(cMsg.invtyCtrlFlg.getValue())) {

            // Check kit items
            NLZC403001PMsg nlzc403001OutPmsg = new NLZC403001PMsg();
            ZYPEZDItemValueSetter.setValue(nlzc403001OutPmsg.glblCmpyCd, cMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(nlzc403001OutPmsg.xxModeCd, OUTBOUND);
            ZYPEZDItemValueSetter.setValue(nlzc403001OutPmsg.mdseCd, cMsg.fnshGoodsMdseCd);
            ZYPEZDItemValueSetter.setValue(nlzc403001OutPmsg.whCd, cMsg.rtlWhCd.getValue() + cMsg.cpltRtlSwhCd.getValue());

            for (int i = 0; i < (cMsg.S.getValidCount()); i++) {
                if (KIT_ITEM_LINE_NUM.equals(cMsg.S.no(i).wrkOrdDtlLineNum_S1.getValue())) {
                    // Kit Item Check
                    ZYPEZDItemValueSetter.setValue(nlzc403001OutPmsg.serNum, cMsg.S.no(i).serNum_S1);

                    nlzc403001Api.execute(nlzc403001OutPmsg, ONBATCH_TYPE.ONLINE);

                    if (ERROR.equals(nlzc403001OutPmsg.xxRsltStsCd.getValue())) {
                        setErrorInfo(cMsg.serNum, nlzc403001OutPmsg);
                        return false;
                    }
                }
            }

            // Check component items
            NLZC403001PMsg nlzc403001InPmsg = new NLZC403001PMsg();

            ZYPEZDItemValueSetter.setValue(nlzc403001InPmsg.glblCmpyCd, cMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(nlzc403001InPmsg.xxModeCd, INBOUND);

            for (int i = 0; i < (cMsg.W.getValidCount()); i++) {

                if (!KIT_ITEM_LINE_NUM.equals(cMsg.W.no(i).wrkOrdDtlLineNum_W1.getValue())) {

                    int lineNum = Integer.parseInt(cMsg.W.no(i).wrkOrdDtlLineNum_W1.getValue()) - 1;
                    // Set 10 digits Item Code
                    ZYPEZDItemValueSetter.setValue(nlzc403001InPmsg.mdseCd, cMsg.A.no(lineNum).mdseCd_A1);
                    ZYPEZDItemValueSetter.setValue(nlzc403001InPmsg.whCd, cMsg.rtlWhCd.getValue() + cMsg.A.no(lineNum).splyRtlSwhCd_A1.getValue());
                    ZYPEZDItemValueSetter.setValue(nlzc403001InPmsg.serNum, cMsg.W.no(i).serNum_W1);

                    nlzc403001Api.execute(nlzc403001InPmsg, ONBATCH_TYPE.ONLINE);

                    if (ERROR.equals(nlzc403001InPmsg.xxRsltStsCd.getValue())) {
                        setErrorInfo(cMsg.A.no(lineNum).serNum_A1, nlzc403001InPmsg);
                        return false;
                    }
                }
            }

        } else if (DS_WRK_ORD_TP.KIT.equals(cMsg.dsWrkOrdTpCd_SL.getValue())) {

            // Check component items
            NLZC403001PMsg nlzc403001OutPmsg = new NLZC403001PMsg();
            ZYPEZDItemValueSetter.setValue(nlzc403001OutPmsg.glblCmpyCd, cMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(nlzc403001OutPmsg.xxModeCd, OUTBOUND);

            for (int i = 0; i < (cMsg.S.getValidCount()); i++) {

                if (!KIT_ITEM_LINE_NUM.equals(cMsg.S.no(i).wrkOrdDtlLineNum_S1.getValue())) {

                    int lineNum = Integer.parseInt(cMsg.S.no(i).wrkOrdDtlLineNum_S1.getValue()) - 1;

                    if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(lineNum).rcvSerTakeFlg_D1.getValue()) && ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(lineNum).invtyCtrlFlg_A1.getValue())) {
                        // Set10 digits Item Code
                        ZYPEZDItemValueSetter.setValue(nlzc403001OutPmsg.mdseCd, cMsg.A.no(lineNum).mdseCd_A1);
                        ZYPEZDItemValueSetter.setValue(nlzc403001OutPmsg.whCd, cMsg.rtlWhCd.getValue() + cMsg.A.no(lineNum).splyRtlSwhCd_A1.getValue());
                        ZYPEZDItemValueSetter.setValue(nlzc403001OutPmsg.serNum, cMsg.S.no(i).serNum_S1);

                        nlzc403001Api.execute(nlzc403001OutPmsg, ONBATCH_TYPE.ONLINE);

                        if (NO_NEED_TO_ENTER_SERIAL.equals(nlzc403001OutPmsg.xxRsltStsCd.getValue())) {
                            continue;
                        } else if (ERROR.equals(nlzc403001OutPmsg.xxRsltStsCd.getValue())) {
                            setErrorInfo(cMsg.A.no(lineNum).serNum_A1, nlzc403001OutPmsg);
                            return false;
                        }
                    }
                }
            }

            // Check kit items
            NLZC403001PMsg nlzc403001InPmsg = new NLZC403001PMsg();

            ZYPEZDItemValueSetter.setValue(nlzc403001InPmsg.glblCmpyCd, cMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(nlzc403001InPmsg.xxModeCd, INBOUND);
            ZYPEZDItemValueSetter.setValue(nlzc403001InPmsg.mdseCd, cMsg.fnshGoodsMdseCd);
            ZYPEZDItemValueSetter.setValue(nlzc403001InPmsg.whCd, cMsg.rtlWhCd.getValue() + cMsg.cpltRtlSwhCd.getValue());

            for (int i = 0; i < (cMsg.W.getValidCount()); i++) {

                if (KIT_ITEM_LINE_NUM.equals(cMsg.W.no(i).wrkOrdDtlLineNum_W1.getValue())) {
                    // Kit Item Check
                    ZYPEZDItemValueSetter.setValue(nlzc403001InPmsg.serNum, cMsg.W.no(i).serNum_W1);

                    nlzc403001Api.execute(nlzc403001InPmsg, ONBATCH_TYPE.ONLINE);

                    if (ERROR.equals(nlzc403001InPmsg.xxRsltStsCd.getValue())) {
                        setErrorInfo(cMsg.serNum, nlzc403001InPmsg);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Check Configuration
     * @param cMsg NPAL1360CMsg
     * @return boolean
     */
    public static boolean checkConfiguration(NPAL1360CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_SVC_MACH_MSTR_STS_CD, SVC_MACH_MSTR_STS.TERMINATED);

        String instlBaseCtrlFlg = null;
        for (int i = 0; i < cMsg.S.getValidCount(); i++) {
            if (KIT_ITEM_LINE_NUM.equals(cMsg.S.no(i).wrkOrdDtlLineNum_S1.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(cMsg.instlBaseCtrlFlg.getValue())) {
                    ssmParam.put(DB_PARAM_MDSE_CD, cMsg.fnshGoodsMdseCd.getValue());
                    ssmParam.put(DB_PARAM_SER_NUM, cMsg.S.no(i).serNum_S1.getValue());
                }
            } else {
                int lineNum = Integer.parseInt(cMsg.S.no(i).wrkOrdDtlLineNum_S1.getValue()) - 1;
                if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(lineNum).instlBaseCtrlFlg_A1.getValue())) {
                    ssmParam.put(DB_PARAM_MDSE_CD, cMsg.A.no(lineNum).mdseCd_A1.getValue());
                    ssmParam.put(DB_PARAM_SER_NUM, cMsg.S.no(i).serNum_S1.getValue());
                }
            }

            if (ZYPConstant.FLG_ON_Y.equals(instlBaseCtrlFlg)) {
                S21SsmEZDResult result = NPAL1360Query.getInstance().checkConfiguration(ssmParam);

                if (result.isCodeNormal()) {
                    List<Map> resultList = (List<Map>) result.getResultObject();
                    Map record = resultList.get(INDEX_0);

                    if (BigDecimal.ONE.compareTo((BigDecimal) record.get(DB_COLUMN_COUNT)) < 0) {
                        cMsg.serNum.setErrorInfo(1, NLZM2324E);
                        return false;
                    }
                }
            }
        }

        return true;

    }

    /**
     * Check Kit Item
     * @param cMsg NPAL1360CMsg
     * @param sMsg NPAL1360SMsg
     * @return boolean
     */
    public static boolean checkKitItem(NPAL1360CMsg cMsg, NPAL1360SMsg sMsg) {

        // Check Kit Item
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_KIT_ITEM, MDSE_TP.KIT);
        ssmParam.put(DB_PARAM_LIMIT_EFF_THRU_DT, LIMIT_DT);

        S21SsmEZDResult result = NPAL1360Query.getInstance().checkKitItem(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();
            Map record = resultList.get(INDEX_0);

            ZYPEZDItemValueSetter.setValue(cMsg.cmpsnRevnNum, (BigDecimal) record.get(DB_COLUMN_CMPSN_REVN_NUM));
            ZYPEZDItemValueSetter.setValue(cMsg.fnshMdseDescShortTxt, (String) record.get(DB_COLUMN_MDSE_DESC_SHORT_TXT));
            ZYPEZDItemValueSetter.setValue(cMsg.fnshGoodsMdseNm, (String) record.get(DB_COLUMN_MDSE_NM));
            ZYPEZDItemValueSetter.setValue(cMsg.firstProdCtrlCd, (String) record.get(DB_COLUMN_FIRST_PROD_CTRL_CD));
            ZYPEZDItemValueSetter.setValue(cMsg.rcvSerTakeFlg, (String) record.get(DB_COLUMN_RCV_SER_TAKE_FLG));
            ZYPEZDItemValueSetter.setValue(cMsg.invtyCtrlFlg, (String) record.get(DB_COLUMN_INVTY_CTRL_FLG));
            ZYPEZDItemValueSetter.setValue(cMsg.instlBaseCtrlFlg, (String) record.get(DB_COLUMN_INSTL_BASE_CTRL_FLG));
            ZYPEZDItemValueSetter.setValue(cMsg.basePkgUomCd, (String) record.get(DB_COLUMN_BASE_PKG_UOM_CD));

        } else {
            cMsg.fnshGoodsMdseCd.setErrorInfo(1, NPAM0224E, new String[] {COMPONENT_ITEM });
            return false;
        }

        // Check Kit Item Effect Date
        Map<String, Object> ssmParamEffDt = new HashMap<String, Object>();
        ssmParamEffDt.put(DB_PARAM_CMSG, cMsg);
        ssmParamEffDt.put(DB_PARAM_HIST_ACT_NM, HIST_ACT_NM_DELETE);

        S21SsmEZDResult resultEffDt = NPAL1360Query.getInstance().checkKitItemEffectDate(ssmParamEffDt);

        if (resultEffDt.isCodeNormal()) {
            List<Map> resultListEffDt = (List<Map>) resultEffDt.getResultObject();
            Map recordEffdt = resultListEffDt.get(INDEX_0);

            ZYPEZDItemValueSetter.setValue(cMsg.effFromDt, (String) recordEffdt.get(DB_COLUMN_EFF_FROM_DT));
            ZYPEZDItemValueSetter.setValue(cMsg.effThruDt, (String) recordEffdt.get(DB_COLUMN_EFF_THRU_DT));
        } else {
            cMsg.fnshGoodsMdseCd.setErrorInfo(1, NPAM0224E, new String[] {COMPONENT_ITEM });
            return false;
        }

        EZDMsg.copy(cMsg, null, sMsg, null);

        return true;
    }

    /**
     * Get Kit Component Items Information
     * @param cMsg NPAL1360CMsg
     * @param sMsg NPAL1360SMsg
     */
    public static void getKitComponentItemsInfo(NPAL1360CMsg cMsg, NPAL1360SMsg sMsg) {

        // Set header Column
        ZYPEZDItemValueSetter.setValue(cMsg.fnshGoodsRcvQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cMsg.fnshGoodsBalQty, cMsg.fnshGoodsOrdQty);
        ZYPEZDItemValueSetter.setValue(cMsg.fnshGoodsCancQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cMsg.invtyAllocQty, cMsg.fnshGoodsOrdQty);

        // Get Kit Component Items Information
        Map<String, Object> ssmParamKit = new HashMap<String, Object>();
        ssmParamKit.put(DB_PARAM_CMSG, cMsg);
        ssmParamKit.put(DB_PARAM_MDSE_CD, cMsg.fnshGoodsMdseCd.getValue());
        ssmParamKit.put(DB_PARAM_ORD_QTY, cMsg.fnshGoodsOrdQty.getValue());

        S21SsmEZDResult resultKit = NPAL1360Query.getInstance().getComponentItemsInfo(ssmParamKit);

        if (resultKit.isCodeNormal()) {
            List<Map> resultListKit = (List<Map>) resultKit.getResultObject();

            int lineNum = 0;
            for (int i = 0; i < resultListKit.size(); i++) {

                Map recordKit = resultListKit.get(i);
                if (i > 0) {
                    lineNum++;
                }

                ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).wrkOrdDtlLineNum_A1, String.format("%03d", lineNum + 1));

                ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).origGoodsMdseCd_A1, (String) recordKit.get(DB_COLUMN_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).origGoodsMdseNm_A1, (String) recordKit.get(DB_COLUMN_MDSE_NM));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).origMdseDescShortTxt_A1, (String) recordKit.get(DB_COLUMN_MDSE_DESC_SHORT_TXT));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).mdseTpCd_A1, (String) recordKit.get(DB_COLUMN_MDSE_TP_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).mdseTpDescTxt_A1, (String) recordKit.get(DB_COLUMN_MDSE_TP_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).basePkgUomCd_D1, (String) recordKit.get(DB_COLUMN_BASE_PKG_UOM_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).origGoodsOrdQty_A1, (BigDecimal) recordKit.get(DB_COLUMN_ORD_QTY));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).rcvSerTakeFlg_D1, (String) recordKit.get(DB_COLUMN_RCV_SER_TAKE_FLG));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).childMdseQty_A1, (BigDecimal) recordKit.get(DB_COLUMN_CHILD_MDSE_QTY));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).invtyCtrlFlg_A1, (String) recordKit.get(DB_COLUMN_INVTY_CTRL_FLG));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).instlBaseCtrlFlg_A1, (String) recordKit.get(DB_COLUMN_INSTL_BASE_CTRL_FLG));

                if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(lineNum).invtyCtrlFlg_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).splyRtlSwhCd_A1, cMsg.cpltRtlSwhCd);
                }
                // Get Available Inventory
                getAvailableInventory(cMsg, sMsg, lineNum);

                if (MDSE_TP.SET.equals(cMsg.A.no(lineNum).mdseTpCd_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).origGoodsMdseCd_A1, cMsg.A.no(lineNum).mdseCd_A1);
                    // Get Kit within Set Component Items Information
                    Map<String, Object> ssmParamSet1 = new HashMap<String, Object>();
                    ssmParamSet1.put(DB_PARAM_CMSG, cMsg);
                    ssmParamSet1.put(DB_PARAM_MDSE_CD, cMsg.A.no(lineNum).origGoodsMdseCd_A1.getValue());
                    ssmParamSet1.put(DB_PARAM_ORD_QTY, cMsg.A.no(lineNum).origGoodsOrdQty_A1.getValue());

                    S21SsmEZDResult resultSet1 = NPAL1360Query.getInstance().getComponentItemsInfo(ssmParamSet1);

                    if (resultSet1.isCodeNormal()) {
                        List<Map> resultListSet1 = (List<Map>) resultSet1.getResultObject();

                        for (int j = 0; j < resultListSet1.size(); j++) {

                            Map recordSet1 = resultListSet1.get(j);
                            lineNum++;

                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).wrkOrdDtlLineNum_A1, String.format("%03d", lineNum + 1));

                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).origGoodsMdseCd_A1, (String) recordSet1.get(DB_COLUMN_MDSE_CD));
                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).origGoodsMdseNm_A1, (String) recordSet1.get(DB_COLUMN_MDSE_NM));
                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).origMdseDescShortTxt_A1, (String) recordSet1.get(DB_COLUMN_MDSE_DESC_SHORT_TXT));
                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).mdseTpCd_A1, (String) recordSet1.get(DB_COLUMN_MDSE_TP_CD));
                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).mdseTpDescTxt_A1, (String) recordSet1.get(DB_COLUMN_MDSE_TP_DESC_TXT));
                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).basePkgUomCd_D1, (String) recordSet1.get(DB_COLUMN_BASE_PKG_UOM_CD));
                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).origGoodsOrdQty_A1, (BigDecimal) recordSet1.get(DB_COLUMN_ORD_QTY));
                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).rcvSerTakeFlg_D1, (String) recordSet1.get(DB_COLUMN_RCV_SER_TAKE_FLG));
                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).childMdseQty_A1, (BigDecimal) recordSet1.get(DB_COLUMN_CHILD_MDSE_QTY));
                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).invtyCtrlFlg_A1, (String) recordSet1.get(DB_COLUMN_INVTY_CTRL_FLG));
                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).instlBaseCtrlFlg_A1, (String) recordSet1.get(DB_COLUMN_INSTL_BASE_CTRL_FLG));

                            if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(lineNum).invtyCtrlFlg_A1.getValue())) {
                                ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).splyRtlSwhCd_A1, cMsg.cpltRtlSwhCd);
                            }
                            // Get Available Inventory
                            getAvailableInventory(cMsg, sMsg, lineNum);

                            if (MDSE_TP.SET.equals(cMsg.A.no(lineNum).mdseTpCd_A1.getValue())) {
                                ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).origGoodsMdseCd_A1, cMsg.A.no(lineNum).mdseCd_A1);
                                ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).mdseTpDescTxt_A1, SET_CHILD);

                                // Get Set within Set Component Items
                                // Information
                                Map<String, Object> ssmParamSet2 = new HashMap<String, Object>();
                                ssmParamSet2.put(DB_PARAM_CMSG, cMsg);
                                ssmParamSet2.put(DB_PARAM_MDSE_CD, cMsg.A.no(lineNum).origGoodsMdseCd_A1.getValue());
                                ssmParamSet2.put(DB_PARAM_ORD_QTY, cMsg.A.no(lineNum).origGoodsOrdQty_A1.getValue());

                                S21SsmEZDResult resultSet2 = NPAL1360Query.getInstance().getComponentItemsInfo(ssmParamSet2);

                                if (resultSet2.isCodeNormal()) {
                                    List<Map> resultListSet2 = (List<Map>) resultSet2.getResultObject();

                                    for (int k = 0; k < resultListSet2.size(); k++) {

                                        Map recordSet2 = resultListSet2.get(k);
                                        lineNum++;

                                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).wrkOrdDtlLineNum_A1, String.format("%03d", lineNum + 1));

                                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).origGoodsMdseCd_A1, (String) recordSet2.get(DB_COLUMN_MDSE_CD));
                                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).origGoodsMdseNm_A1, (String) recordSet2.get(DB_COLUMN_MDSE_NM));
                                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).origMdseDescShortTxt_A1, (String) recordSet2.get(DB_COLUMN_MDSE_DESC_SHORT_TXT));
                                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).mdseTpCd_A1, (String) recordSet2.get(DB_COLUMN_MDSE_TP_CD));
                                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).mdseTpDescTxt_A1, (String) recordSet2.get(DB_COLUMN_MDSE_TP_DESC_TXT));
                                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).basePkgUomCd_D1, (String) recordSet2.get(DB_COLUMN_BASE_PKG_UOM_CD));
                                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).origGoodsOrdQty_A1, (BigDecimal) recordSet2.get(DB_COLUMN_ORD_QTY));
                                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).rcvSerTakeFlg_D1, (String) recordSet2.get(DB_COLUMN_RCV_SER_TAKE_FLG));
                                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).childMdseQty_A1, (BigDecimal) recordSet2.get(DB_COLUMN_CHILD_MDSE_QTY));
                                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).invtyCtrlFlg_A1, (String) recordSet2.get(DB_COLUMN_INVTY_CTRL_FLG));
                                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).instlBaseCtrlFlg_A1, (String) recordSet2.get(DB_COLUMN_INSTL_BASE_CTRL_FLG));

                                        if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(lineNum).invtyCtrlFlg_A1.getValue())) {
                                            ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).splyRtlSwhCd_A1, cMsg.cpltRtlSwhCd);
                                        }
                                        // Get Available Inventory
                                        getAvailableInventory(cMsg, sMsg, lineNum);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            // Set Valid Line#
            cMsg.A.setValidCount(lineNum + 1);
            cMsg.setMessageInfo(ZZZM9003I, new String[] {BTN_COMPONENT });
        }
    }

    /**
     * Get Available Inventory
     * @param cMsg NPAL1360CMsg
     * @param sMsg NPAL1360SMsg
     * @param lineNum int
     */
    public static void getAvailableInventory(NPAL1360CMsg cMsg, NPAL1360SMsg sMsg, int lineNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_LOC_STS_CD, LOC_STS.DC_STOCK);
        ssmParam.put(DB_PARAM_STK_STS_CD, STK_STS.GOOD);
        ssmParam.put(DB_PARAM_MDSE_CD, cMsg.A.no(lineNum).origGoodsMdseCd_A1.getValue());
        ssmParam.put(DB_PARAM_INVTY_LOC_CD, cMsg.rtlWhCd.getValue() + cMsg.A.no(lineNum).splyRtlSwhCd_A1.getValue());
        ssmParam.put(DB_PARAM_ROWNUM, ROWNUM_1);

        S21SsmEZDResult result = NPAL1360Query.getInstance().getAvailableInventory(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();
            Map record = resultList.get(INDEX_0);

            ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).mdseCd_A1, (String) record.get(DB_COLUMN_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).origGoodsMdseNm_A1, (String) record.get(DB_COLUMN_MDSE_NM));
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).origMdseDescShortTxt_A1, (String) record.get(DB_COLUMN_MDSE_DESC_SHORT_TXT));
            if (DS_WRK_ORD_TP.KIT.equals(cMsg.dsWrkOrdTpCd_SL.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(lineNum).invtyCtrlFlg_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).invtyAvalQty_A1, (BigDecimal) record.get(DB_COLUMN_INVTY_AVAL_QTY));
                } else {
                    cMsg.A.no(lineNum).invtyAvalQty_A1.clear();
                }
            } else if (DS_WRK_ORD_TP.UN_KIT.equals(cMsg.dsWrkOrdTpCd_SL.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).invtyAllocQty_A1, cMsg.A.no(lineNum).origGoodsOrdQty_A1);
                cMsg.A.no(lineNum).invtyAvalQty_A1.clear();
            }
        }
    }

    /**
     * Get Kit Item Available Inventory
     * @param cMsg NPAL1360CMsg
     */
    public static void getKitItemAvailableInventory(NPAL1360CMsg cMsg) {

        ZYPEZDItemValueSetter.setValue(cMsg.totOrdQty, BigDecimal.ZERO);

        INVTYTMsg tMsg = new INVTYTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.mdseCd.setValue(cMsg.fnshGoodsMdseCd.getValue());
        tMsg.invtyLocCd.setValue(cMsg.rtlWhCd.getValue() + cMsg.cpltRtlSwhCd.getValue());
        tMsg.locStsCd.setValue(LOC_STS.DC_STOCK);
        tMsg.stkStsCd.setValue(STK_STS.GOOD);

        INVTYTMsg invtyTMsg = (INVTYTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (invtyTMsg == null) {
            ZYPEZDItemValueSetter.setValue(cMsg.invtyAllocQty, BigDecimal.ZERO);
        } else {
            if (cMsg.fnshGoodsOrdQty.getValue().compareTo(invtyTMsg.invtyAvalQty.getValue()) <= 0) {
                ZYPEZDItemValueSetter.setValue(cMsg.invtyAllocQty, cMsg.fnshGoodsOrdQty.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.invtyAllocQty, BigDecimal.ZERO);
            }
        }
    }

    /**
     * Get Serial Number for Component Items
     * @param cMsg NPAL1360CMsg
     * @param sMsg NPAL1360SMsg
     * @param lineNum int
     */
    public static void getSerialForComponentItems(NPAL1360CMsg cMsg, NPAL1360SMsg sMsg, int lineNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_WRK_ORD_DTL_LINE_NUM, cMsg.A.no(lineNum).wrkOrdDtlLineNum_A1.getValue());

        S21SsmEZDResult result = NPAL1360Query.getInstance().getWorkOrderSerial(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();
            Map record = resultList.get(INDEX_0);

            ZYPEZDItemValueSetter.setValue(cMsg.A.no(lineNum).serNum_A1, (String) record.get(DB_COLUMN_SER_NUM));
        }
    }

    /**
     * Clear Work Order Line Information
     * @param cMsg NPAL1360CMsg
     * @param sMsg NPAL1360SMsg
     */
    public static void clearLineInfo(NPAL1360CMsg cMsg, NPAL1360SMsg sMsg) {

        // Line Clear
        ZYPTableUtil.clear(cMsg.A);

        // Line Serial# Clear
        if (ZYPCommonFunc.hasValue(cMsg.fnshGoodsMdseCd) && ZYPCommonFunc.hasValue(sMsg.fnshGoodsMdseCd) && cMsg.fnshGoodsOrdQty.getValue().equals(sMsg.fnshGoodsOrdQty.getValue())) {
            for (int i = 0; i < cMsg.S.getValidCount(); i++) {
                if (KIT_ITEM_LINE_NUM.equals(cMsg.S.no(i).wrkOrdDtlLineNum_S1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cMsg.W.no(i).wrkOrdDtlLineNum_W1, cMsg.S.no(i).wrkOrdDtlLineNum_S1);
                    ZYPEZDItemValueSetter.setValue(cMsg.W.no(i).serNum_W1, cMsg.S.no(i).serNum_S1);
                    cMsg.W.setValidCount(i + 1);
                }
            }

            ZYPTableUtil.clear(cMsg.S);

            for (int i = 0; i < cMsg.W.getValidCount(); i++) {
                if (KIT_ITEM_LINE_NUM.equals(cMsg.W.no(i).wrkOrdDtlLineNum_W1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).wrkOrdDtlLineNum_S1, cMsg.W.no(i).wrkOrdDtlLineNum_W1);
                    ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).serNum_S1, cMsg.W.no(i).serNum_W1);
                    cMsg.S.setValidCount(i + 1);
                }
            }

            ZYPTableUtil.clear(cMsg.W);

        } else {
            ZYPTableUtil.clear(cMsg.S);
            ZYPTableUtil.clear(cMsg.W);
        }
    }

    /**
     * Check Other Process Update
     * @param cMsg NPAL1360CMsg
     * @param wrkOrdTMsg WRK_ORDTMsg
     * @return boolean
     */
    public static boolean checkOtherProcessUpdate(NPAL1360CMsg cMsg, WRK_ORDTMsg wrkOrdTMsg) {

        // Check WRK_ORD
        //WRK_ORDTMsg wrkOrdTMsg = getWrkOrd(cMsg);

        if (wrkOrdTMsg != null) {
            if (!checkTimeStamp(wrkOrdTMsg.ezUpTime.getValue(), wrkOrdTMsg.ezUpTimeZone.getValue(), cMsg.xxRqstTs_WH.getValue(), cMsg.xxRqstTz_WH.getValue())) {
                cMsg.setMessageInfo(NPAM0006E);
                return false;
            }
        } else {
            cMsg.setMessageInfo(NPAM0006E);
            return false;
        }

        if (wrkOrdTMsg != null) {
            if (!checkTimeStamp(wrkOrdTMsg.ezUpTime.getValue(), wrkOrdTMsg.ezUpTimeZone.getValue(), cMsg.xxRqstTs_DH.getValue(), cMsg.xxRqstTz_DH.getValue())) {
                cMsg.setMessageInfo(NPAM0006E);
                return false;
            }
        } else {
            cMsg.setMessageInfo(NPAM0006E);
            return false;
        }

        return true;
    }

    /**
     * Get DS_WRK_ORD_TP
     * @param cMsg NPAL1360CMsg
     * @return DS_WRK_ORD_TPTMsg
     */
    public static DS_WRK_ORD_TPTMsg getDsWrkOrdTp(NPAL1360CMsg cMsg) {

        DS_WRK_ORD_TPTMsg tMsg = new DS_WRK_ORD_TPTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.dsWrkOrdTpCd.setValue(cMsg.dsWrkOrdTpCd_SL.getValue());

        DS_WRK_ORD_TPTMsg dsWrkOrdTpTMsg = (DS_WRK_ORD_TPTMsg) EZDTBLAccessor.findByKey(tMsg);

        return dsWrkOrdTpTMsg;
    }

    /**
     * Get WRK_ORD
     * @param cMsg NPAL1360CMsg
     * @return WRK_ORDTMsg
     */
    public static WRK_ORDTMsg getWrkOrd(NPAL1360CMsg cMsg) {

        WRK_ORDTMsg tMsg = new WRK_ORDTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.wrkOrdNum.setValue(cMsg.wrkOrdNum.getValue());

        WRK_ORDTMsg wrkOrdTMsg = (WRK_ORDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

        return wrkOrdTMsg;
    }

    /**
     * Get WRK_ORD_DTL
     * @param cMsg NPAL1360CMsg
     * @param lineNum String
     * @return WRK_ORD_DTLTMsg
     */
    public static WRK_ORD_DTLTMsg getWrkOrdDtl(NPAL1360CMsg cMsg, String lineNum) {

        WRK_ORD_DTLTMsg tMsg = new WRK_ORD_DTLTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.wrkOrdNum.setValue(cMsg.wrkOrdNum.getValue());
        tMsg.wrkOrdDtlLineNum.setValue(lineNum);

        WRK_ORD_DTLTMsg wrkOrdDtlTMsg = (WRK_ORD_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

        return wrkOrdDtlTMsg;
    }


    /**
     * Check Other Process Update
     * @param tmpTime1 String
     * @param tmpTimeZone1 String
     * @param tmpTime2 String
     * @param tmpTimeZone2 String
     * @return boolean
     */
    public static boolean checkTimeStamp(String tmpTime1, String tmpTimeZone1, String tmpTime2, String tmpTimeZone2) {

        if (!ZYPDateUtil.isSameTimeStamp(tmpTime1, tmpTimeZone1, tmpTime2, tmpTimeZone2)) {
            return false;
        }

        return true;
    }

    /**
     * Delete WRK_ORD
     * @param cMsg NPAL1360CMsg
     * @return boolean
     */
    public static boolean deleteWrkOrd(NPAL1360CMsg cMsg) {

        WRK_ORDTMsg tMsg = new WRK_ORDTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.wrkOrdNum.setValue(cMsg.wrkOrdNum.getValue());

        EZDTBLAccessor.remove(tMsg);

        if (DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(tMsg.getReturnCode())) {
            return true;
        } else {
            cMsg.setMessageInfo(NLBM0024E, new String[] {DELETE_WRK_ORD });
            return false;
        }
    }

    /**
     * Delete WRK_ORD_DTL
     * @param cMsg NPAL1360CMsg
     * @return boolean
     */
    public static boolean deleteWrkOrdDtl(NPAL1360CMsg cMsg) {

        WRK_ORD_DTLTMsg tMsg = new WRK_ORD_DTLTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.wrkOrdNum.setValue(cMsg.wrkOrdNum.getValue());

        EZDTBLAccessor.removeByPartialKey(tMsg);

        if (DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(tMsg.getReturnCode())) {
            return true;
        } else {
            cMsg.setMessageInfo(NLBM0024E, new String[] {DELETE_WRK_ORD });
            return false;
        }
    }

    /**
     * Delete WRK_ORD_SER
     * @param cMsg NPAL1360CMsg
     * @return boolean
     */
    public static boolean deleteWrkOrdSer(NPAL1360CMsg cMsg) {

        WRK_ORD_SERTMsg tMsg = new WRK_ORD_SERTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.wrkOrdNum.setValue(cMsg.wrkOrdNum.getValue());

        EZDTBLAccessor.removeByPartialKey(tMsg);

        if (DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(tMsg.getReturnCode())) {
            return true;
        } else if (DB_ACCESS_PARTS_NO_DATA_FOUND.equals(tMsg.getReturnCode())) {
            return true;
        } else {
            cMsg.setMessageInfo(NLBM0024E, new String[] {DELETE_WRK_ORD });
            return false;
        }
    }

    /**
     * Delete Work Order
     * @param cMsg NPAL1360CMsg
     * @return boolean
     */
    public static boolean deleteWorkOrder(NPAL1360CMsg cMsg) {

        // Delete WRK_ORD
        if (!deleteWrkOrd(cMsg)) {
            return false;
        }
        // Delete WRK_ORD_DTL
        if (!deleteWrkOrdDtl(cMsg)) {
            return false;
        }
        // Delete WRK_ORD_SER
        if (!deleteWrkOrdSer(cMsg)) {
            return false;
        }

        return true;
    }

    /**
     * Check Composition
     * @param cMsg NPAL1360CMsg
     * @param sMsg NPAL1360SMsg
     * @return boolean
     */
    public static boolean checkComposition(NPAL1360CMsg cMsg, NPAL1360SMsg sMsg) {

        // Get Kit Component Items Information
        Map<String, Object> ssmParamKit = new HashMap<String, Object>();
        ssmParamKit.put(DB_PARAM_CMSG, cMsg);
        ssmParamKit.put(DB_PARAM_MDSE_CD, cMsg.fnshGoodsMdseCd.getValue());
        ssmParamKit.put(DB_PARAM_ORD_QTY, cMsg.fnshGoodsOrdQty.getValue());

        S21SsmEZDResult resultKit = NPAL1360Query.getInstance().getComponentItemsInfo(ssmParamKit);

        if (resultKit.isCodeNormal()) {
            List<Map> resultListKit = (List<Map>) resultKit.getResultObject();

            int lineNum = 0;
            for (int i = 0; i < resultListKit.size(); i++) {

                Map recordKit = resultListKit.get(i);
                if (i > 0) {
                    lineNum++;
                }

                if (lineNum > cMsg.A.getValidCount()) {
                    cMsg.setMessageInfo(NPAM1523E);
                    return false;
                }

                if (cMsg.A.no(lineNum).origGoodsMdseCd_A1.getValue().equals((String) recordKit.get(DB_COLUMN_MDSE_CD)) && (cMsg.A.no(lineNum).childMdseQty_A1.getValue().equals((BigDecimal) recordKit.get(DB_COLUMN_CHILD_MDSE_QTY)))) {
                    if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(lineNum).invtyCtrlFlg_A1.getValue())) {
                        // Get Available Inventory
                        getAvailableInventory(cMsg, sMsg, lineNum);
                    }
                } else {
                    cMsg.setMessageInfo(NPAM1523E);
                    return false;
                }

                if (MDSE_TP.SET.equals(cMsg.A.no(lineNum).mdseTpCd_A1.getValue())) {
                    // Get Kit within Set Component Items Information
                    Map<String, Object> ssmParamSet1 = new HashMap<String, Object>();
                    ssmParamSet1.put(DB_PARAM_CMSG, cMsg);
                    ssmParamSet1.put(DB_PARAM_MDSE_CD, cMsg.A.no(lineNum).origGoodsMdseCd_A1.getValue());
                    ssmParamSet1.put(DB_PARAM_ORD_QTY, cMsg.A.no(lineNum).origGoodsOrdQty_A1.getValue());

                    S21SsmEZDResult resultSet1 = NPAL1360Query.getInstance().getComponentItemsInfo(ssmParamSet1);

                    if (resultSet1.isCodeNormal()) {
                        List<Map> resultListSet1 = (List<Map>) resultSet1.getResultObject();

                        for (int j = 0; j < resultListSet1.size(); j++) {

                            Map recordSet1 = resultListSet1.get(j);
                            lineNum++;

                            if (lineNum > cMsg.A.getValidCount()) {
                                cMsg.setMessageInfo(NPAM1523E);
                                return false;
                            }

                            if (cMsg.A.no(lineNum).origGoodsMdseCd_A1.getValue().equals((String) recordSet1.get(DB_COLUMN_MDSE_CD)) && (cMsg.A.no(lineNum).childMdseQty_A1.getValue().equals((BigDecimal) recordSet1.get(DB_COLUMN_CHILD_MDSE_QTY)))) {
                                if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(lineNum).invtyCtrlFlg_A1.getValue())) {
                                    // Get Available Inventory
                                    getAvailableInventory(cMsg, sMsg, lineNum);
                                }
                            } else {
                                cMsg.setMessageInfo(NPAM1523E);
                                return false;
                            }

                            if (MDSE_TP.SET.equals(cMsg.A.no(lineNum).mdseTpCd_A1.getValue())) {
                                // Get Set within Set Component Items
                                // Information
                                Map<String, Object> ssmParamSet2 = new HashMap<String, Object>();
                                ssmParamSet2.put(DB_PARAM_CMSG, cMsg);
                                ssmParamSet2.put(DB_PARAM_MDSE_CD, cMsg.A.no(lineNum).origGoodsMdseCd_A1.getValue());
                                ssmParamSet2.put(DB_PARAM_ORD_QTY, cMsg.A.no(lineNum).origGoodsOrdQty_A1.getValue());

                                S21SsmEZDResult resultSet2 = NPAL1360Query.getInstance().getComponentItemsInfo(ssmParamSet2);

                                if (resultSet2.isCodeNormal()) {
                                    List<Map> resultListSet2 = (List<Map>) resultSet2.getResultObject();

                                    for (int k = 0; k < resultListSet2.size(); k++) {

                                        Map recordSet2 = resultListSet2.get(k);
                                        lineNum++;

                                        if (lineNum > cMsg.A.getValidCount()) {
                                            cMsg.setMessageInfo(NPAM1523E);
                                            return false;
                                        }

                                        if (cMsg.A.no(lineNum).origGoodsMdseCd_A1.getValue().equals((String) recordSet2.get(DB_COLUMN_MDSE_CD))
                                                && (cMsg.A.no(lineNum).childMdseQty_A1.getValue().equals((BigDecimal) recordSet2.get(DB_COLUMN_CHILD_MDSE_QTY)))) {
                                            if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(lineNum).invtyCtrlFlg_A1.getValue())) {
                                                // Get Available
                                                // Inventory
                                                getAvailableInventory(cMsg, sMsg, lineNum);
                                            }
                                        } else {
                                            cMsg.setMessageInfo(NPAM1523E);
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Set Allocation Quantity
     * @param cMsg NPAL1360CMsg
     * @param sMsg NPAL1360SMsg
     * @return boolean
     */
    public static boolean setAllocationQuantity(NPAL1360CMsg cMsg, NPAL1360SMsg sMsg) {

        BigDecimal allocQty = BigDecimal.ZERO;
        String splitWrkOrdFlg = ZYPConstant.FLG_OFF_N;

        for (int i = 0; i < cMsg.fnshGoodsOrdQty.getValueInt(); i++) {

            BigDecimal availQty = BigDecimal.ZERO;
            allocQty = allocQty.add(BigDecimal.ONE);

            for (int j = 0; j < cMsg.A.getValidCount(); j++) {
                if (!ZYPCommonFunc.hasValue(cMsg.A.no(j).invtyAvalQty_A1)) {
                    // Skip Set Item
                    continue;
                }
                availQty = allocQty.multiply(cMsg.A.no(j).childMdseQty_A1.getValue());
                if (availQty.compareTo(cMsg.A.no(j).invtyAvalQty_A1.getValue()) <= 0) {
                    continue;
                } else {
                    allocQty = allocQty.subtract(BigDecimal.ONE);
                    if (BigDecimal.ZERO.equals(allocQty)) {
                        ZYPEZDItemValueSetter.setValue(cMsg.invtyAllocQty, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(cMsg.totOrdQty, BigDecimal.ZERO);
                        cMsg.setMessageInfo(NPAM1524W, new String[] {COMPONENT_ITEM });
                        return false;
                    } else {
                        splitWrkOrdFlg = ZYPConstant.FLG_ON_Y;
                        break;
                    }
                }
            }
        }

        if (ZYPConstant.FLG_ON_Y.equals(splitWrkOrdFlg)) {
            ZYPEZDItemValueSetter.setValue(cMsg.invtyAllocQty, allocQty);
            ZYPEZDItemValueSetter.setValue(cMsg.totOrdQty, cMsg.fnshGoodsOrdQty.getValue().subtract(allocQty));

        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.invtyAllocQty, cMsg.fnshGoodsOrdQty);
            ZYPEZDItemValueSetter.setValue(cMsg.totOrdQty, BigDecimal.ZERO);
        }

        for (int k = 0; k < cMsg.A.getValidCount(); k++) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(k).invtyCtrlFlg_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(k).invtyAllocQty_A1, cMsg.A.no(k).childMdseQty_A1.getValue().multiply(allocQty));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(k).totOrdQty_A1, cMsg.A.no(k).origGoodsOrdQty_A1.getValue().subtract(cMsg.A.no(k).invtyAllocQty_A1.getValue()));
            }
        }
        return true;
    }

    /**
     * Insert Work Order
     * @param cMsg NPAL1360CMsg
     * @return boolean
     */
    public static boolean insertWorkOder(NPAL1360CMsg cMsg) {

        String splitWrkOrdFlg = ZYPConstant.FLG_OFF_N;

        // Insert WRK_ORD
        if (!insertWrkOrd(cMsg, splitWrkOrdFlg)) {
            return false;
        }
        // Insert WRK_ORD_DTL
        if (!insertWrkOrdDtl(cMsg, splitWrkOrdFlg)) {
            return false;
        }
        // Insert WRK_ORD_SER
        if (!insertWrkOrdSer(cMsg, splitWrkOrdFlg)) {
            return false;
        }
        return true;
    }

    /**
     * Insert WRK_ORD
     * @param cMsg NPAL1360CMsg
     * @param splitWrkOrdFlg String
     * @return boolean
     */
    public static boolean insertWrkOrd(NPAL1360CMsg cMsg, String splitWrkOrdFlg) {

        String wrkOrdNum = null;
        cMsg.wrkOrdNum_SV.clear();

        WRK_ORDTMsg tMsg = new WRK_ORDTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.wrkOrdTpCd.setValue(WRK_ORD_TP.INTERNAL_KIT);
        tMsg.wrkOrdStsCd.setValue(WRK_ORD_STS.SAVED);
        tMsg.wrkOrdDt.setValue(cMsg.slsDt.getValue());
        tMsg.invtyLocCd.setValue(cMsg.rtlWhCd.getValue());
        tMsg.rcvInvtyLocCd.setValue(cMsg.rtlWhCd.getValue() + cMsg.cpltRtlSwhCd.getValue());
        tMsg.stkStsCd.setValue(STK_STS.GOOD);
        tMsg.kitBomPrintFlg.setValue(ZYPConstant.FLG_OFF_N);
        tMsg.fnshGoodsMdseCd.setValue(cMsg.fnshGoodsMdseCd.getValue());
        tMsg.fnshGoodsMdseNm.setValue(cMsg.fnshGoodsMdseNm.getValue());
        tMsg.firstProdCtrlCd.setValue(cMsg.firstProdCtrlCd.getValue());
        tMsg.fnshGoodsRcvQty.setValue(BigDecimal.ZERO);
        tMsg.fnshGoodsCancQty.setValue(BigDecimal.ZERO);
        tMsg.rqstRcvDt.setValue(cMsg.rqstRcvDt.getValue());
        tMsg.wrkOrdMsgTxt.setValue(cMsg.wrkOrdMsgTxt.getValue());
        //Additional columns
        if (ZYPCommonFunc.hasValue(cMsg.wrkOrdNum_SV)) {
            tMsg.wrkOrdNum.setValue(cMsg.wrkOrdNum_SV.getValue());
        } else {
            tMsg.wrkOrdNum.setValue(cMsg.wrkOrdNum.getValue());
        }
        tMsg.dsWrkOrdTpCd.setValue(cMsg.dsWrkOrdTpCd_SL.getValue());
        tMsg.rtlWhCd.setValue(cMsg.rtlWhCd.getValue());
        tMsg.cpltRtlSwhCd.setValue(cMsg.cpltRtlSwhCd.getValue());
        tMsg.oldWrkOrdNum.setValue(cMsg.wrkOrdNum.getValue());
        tMsg.fnshMdseDescShortTxt.setValue(cMsg.fnshMdseDescShortTxt.getValue());
        tMsg.prchReqNum.setValue(cMsg.prchReqNum_P1.getValue());
        tMsg.prchReqLineNum.setValue(cMsg.prchReqLineNum_P1.getValue());
        tMsg.prchReqLineSubNum.setValue(cMsg.prchReqLineSubNum_P1.getValue());
        tMsg.cmpsnRevnNum.setValue(cMsg.cmpsnRevnNum.getValue());
        //Additional columns
        if (ZYPConstant.FLG_OFF_N.equals(splitWrkOrdFlg)) {
            if (ZYPCommonFunc.hasValue(cMsg.wrkOrdNum)) {
                wrkOrdNum = cMsg.wrkOrdNum.getValue();
            } else {
                wrkOrdNum = ZYPNumbering.getUniqueID(WRK_ORD_NUM);
                ZYPEZDItemValueSetter.setValue(cMsg.wrkOrdNum, wrkOrdNum);
            }
            tMsg.wrkOrdNum.setValue(wrkOrdNum);
            tMsg.fnshGoodsOrdQty.setValue(cMsg.fnshGoodsOrdQty.getValue());
            tMsg.fnshGoodsBalQty.setValue(cMsg.fnshGoodsOrdQty.getValue());
        } else if (ZYPConstant.FLG_ON_Y.equals(splitWrkOrdFlg)) {
            wrkOrdNum = ZYPNumbering.getUniqueID(WRK_ORD_NUM);
            ZYPEZDItemValueSetter.setValue(cMsg.wrkOrdNum_SV, wrkOrdNum);
            tMsg.wrkOrdNum.setValue(wrkOrdNum);
            tMsg.fnshGoodsOrdQty.setValue(cMsg.totOrdQty.getValue());
            tMsg.fnshGoodsBalQty.setValue(cMsg.totOrdQty.getValue());
        }

        EZDTBLAccessor.insert(tMsg);

        if (DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(tMsg.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxRqstTs_WH, tMsg.ezUpTime);
            ZYPEZDItemValueSetter.setValue(cMsg.xxRqstTz_WH, tMsg.ezUpTimeZone);
            return true;
        } else {
            cMsg.setMessageInfo(NLBM0024E, new String[] {INSERT_WRK_ORD});
            return false;
        }
    }

    /**
     * Insert WRK_ORD_DTL
     * @param cMsg NPAL1360CMsg
     * @param splitWrkOrdFlg String
     * @return boolean
     */
    public static boolean insertWrkOrdDtl(NPAL1360CMsg cMsg, String splitWrkOrdFlg) {

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            WRK_ORD_DTLTMsg tMsg = new WRK_ORD_DTLTMsg();

            tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
            tMsg.wrkOrdDtlLineNum.setValue(cMsg.A.no(i).wrkOrdDtlLineNum_A1.getValue());
            tMsg.origGoodsMdseCd.setValue(cMsg.A.no(i).origGoodsMdseCd_A1.getValue());
            tMsg.origGoodsMdseNm.setValue(cMsg.A.no(i).origGoodsMdseNm_A1.getValue());
            tMsg.origGoodsHardAllocQty.setValue(BigDecimal.ZERO);
            tMsg.origGoodsCancQty.setValue(BigDecimal.ZERO);
            tMsg.stkStsCd.setValue(STK_STS.GOOD);

            if (ZYPCommonFunc.hasValue(cMsg.wrkOrdNum_SV)) {
                tMsg.wrkOrdNum.setValue(cMsg.wrkOrdNum_SV.getValue());
            } else {
                tMsg.wrkOrdNum.setValue(cMsg.wrkOrdNum.getValue());
            }

            if (ZYPConstant.FLG_OFF_N.equals(splitWrkOrdFlg)) {
                tMsg.origGoodsOrdQty.setValue(cMsg.A.no(i).origGoodsOrdQty_A1.getValue());
                tMsg.origGoodsBalQty.setValue(cMsg.A.no(i).origGoodsOrdQty_A1.getValue());
            } else if (ZYPConstant.FLG_ON_Y.equals(splitWrkOrdFlg)) {
                tMsg.origGoodsOrdQty.setValue(cMsg.A.no(i).totOrdQty_A1.getValue());
                tMsg.origGoodsBalQty.setValue(cMsg.A.no(i).totOrdQty_A1.getValue());
            }
            //Additional columns
            tMsg.childMdseQty.setValue(cMsg.A.no(i).childMdseQty_A1.getValue());
            tMsg.origMdseDescShortTxt.setValue(cMsg.A.no(i).origMdseDescShortTxt_A1.getValue());
            tMsg.splyRtlSwhCd.setValue(cMsg.A.no(i).splyRtlSwhCd_A1.getValue());
            tMsg.origGoodsRcvQty.setValue(BigDecimal.ZERO);

            if (SET_CHILD.equals(cMsg.A.no(i).mdseTpDescTxt_A1.getValue())) {
                tMsg.wrkOrdSetCmpsnFlg.setValue(ZYPConstant.FLG_ON_Y);
            } else {
                tMsg.wrkOrdSetCmpsnFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
            //Additional columns
            EZDTBLAccessor.insert(tMsg);

            if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NLBM0024E, new String[] {INSERT_WRK_ORD});
                return false;
            }
        }
        return true;
    }

    /**
     * Insert WRK_ORD_SER
     * @param cMsg NPAL1360CMsg
     * @param splitWrkOrdFlg String
     * @return boolean
     */
    public static boolean insertWrkOrdSer(NPAL1360CMsg cMsg, String splitWrkOrdFlg) {

        if (BigDecimal.ZERO.compareTo(cMsg.totOrdQty.getValue()) == 0) {
            // Insert Kit and Component Serial# 
            for (int i = 0; i < cMsg.S.getValidCount(); i++) {

                WRK_ORD_SERTMsg tMsg = new WRK_ORD_SERTMsg();

                tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
                tMsg.wrkOrdNum.setValue(cMsg.wrkOrdNum.getValue());
                tMsg.wrkOrdDtlLineNum.setValue(cMsg.S.no(i).wrkOrdDtlLineNum_S1.getValue());
                tMsg.serNum.setValue(cMsg.S.no(i).serNum_S1.getValue());

                EZDTBLAccessor.insert(tMsg);

                if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(tMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NLBM0024E, new String[] {INSERT_WRK_ORD});
                    return false;
                }
            }
        } else if (BigDecimal.ZERO.compareTo(cMsg.totOrdQty.getValue()) < 0) {
            int allocSerCnt = 0;
            int boSerCnt = 0;
            if (ZYPConstant.FLG_OFF_N.equals(splitWrkOrdFlg)) {
                // Insert Kit Item Serial#
                ZYPTableUtil.clear(cMsg.W);

                if (ZYPConstant.FLG_ON_Y.equals(cMsg.rcvSerTakeFlg.getValue())) {
                    for (int i = 0; i < cMsg.S.getValidCount(); i++) {
                        if (KIT_ITEM_LINE_NUM.equals(cMsg.S.no(i).wrkOrdDtlLineNum_S1.getValue())) {
                            allocSerCnt++;
                            if (allocSerCnt <= cMsg.invtyAllocQty.getValueInt()) {
                                WRK_ORD_SERTMsg tMsg = new WRK_ORD_SERTMsg();

                                tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
                                tMsg.wrkOrdNum.setValue(cMsg.wrkOrdNum.getValue());
                                tMsg.wrkOrdDtlLineNum.setValue(cMsg.S.no(i).wrkOrdDtlLineNum_S1.getValue());
                                tMsg.serNum.setValue(cMsg.S.no(i).serNum_S1.getValue());

                                EZDTBLAccessor.insert(tMsg);

                                if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(tMsg.getReturnCode())) {
                                    cMsg.setMessageInfo(NLBM0024E, new String[] {INSERT_WRK_ORD });
                                    return false;
                                }
                            } else if (allocSerCnt > cMsg.invtyAllocQty.getValueInt()) {
                                ZYPEZDItemValueSetter.setValue(cMsg.W.no(boSerCnt).wrkOrdDtlLineNum_W1, (cMsg.S.no(i).wrkOrdDtlLineNum_S1.getValue()));
                                ZYPEZDItemValueSetter.setValue(cMsg.W.no(boSerCnt).serNum_W1, (cMsg.S.no(i).serNum_S1.getValue()));
                                boSerCnt++;
                                cMsg.W.setValidCount(boSerCnt);
                            }

                        }
                    }
                }
                // Insert Component Items Serial#
                for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                    allocSerCnt = 0;
                    if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).rcvSerTakeFlg_D1.getValue())) {
                        for (int j = 0; j < cMsg.S.getValidCount(); j++) {
                            if (cMsg.A.no(i).wrkOrdDtlLineNum_A1.getValue().equals(cMsg.S.no(j).wrkOrdDtlLineNum_S1.getValue())) {
                                allocSerCnt++;
                                if (allocSerCnt <= cMsg.A.no(i).invtyAllocQty_A1.getValueInt()) {
                                    WRK_ORD_SERTMsg tMsg = new WRK_ORD_SERTMsg();

                                    tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
                                    tMsg.wrkOrdNum.setValue(cMsg.wrkOrdNum.getValue());
                                    tMsg.wrkOrdDtlLineNum.setValue(cMsg.S.no(j).wrkOrdDtlLineNum_S1.getValue());
                                    tMsg.serNum.setValue(cMsg.S.no(j).serNum_S1.getValue());

                                    EZDTBLAccessor.insert(tMsg);

                                    if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(tMsg.getReturnCode())) {
                                        cMsg.setMessageInfo(NLBM0024E, new String[] {INSERT_WRK_ORD});
                                        return false;
                                    }
                                } else if (allocSerCnt > cMsg.invtyAllocQty.getValueInt()) {
                                    ZYPEZDItemValueSetter.setValue(cMsg.W.no(boSerCnt).wrkOrdDtlLineNum_W1, (cMsg.S.no(j).wrkOrdDtlLineNum_S1.getValue()));
                                    ZYPEZDItemValueSetter.setValue(cMsg.W.no(boSerCnt).serNum_W1, (cMsg.S.no(j).serNum_S1.getValue()));
                                    boSerCnt++;
                                    cMsg.W.setValidCount(boSerCnt);
                                }
                            }
                        }
                    }
                }
                // Get from Work Order Serial# Table to cMsg.S
                ZYPTableUtil.clear(cMsg.S);

                Map<String, Object> ssmParamSerial = new HashMap<String, Object>();
                ssmParamSerial.put(DB_PARAM_CMSG, cMsg);
                ssmParamSerial.put(DB_PARAM_ROWNUM, cMsg.S.length());

                S21SsmEZDResult resultSerial = NPAL1360Query.getInstance().getWorkOrderSerial(ssmParamSerial);

                if (resultSerial.isCodeNormal()) {
                    List<Map> resultListSerial = (List<Map>) resultSerial.getResultObject();

                    for (int i = 0; i < resultListSerial.size(); i++) {
                        Map recordSerial = resultListSerial.get(i);

                        ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).wrkOrdDtlLineNum_S1, (String) recordSerial.get(DB_COLUMN_WRK_ORD_DTL_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).serNum_S1, (String) recordSerial.get(DB_COLUMN_SER_NUM));
                        cMsg.S.setValidCount(i + 1);
                    }
                }
            }
            // Insert Back Order Serial#
            if (ZYPConstant.FLG_ON_Y.equals(splitWrkOrdFlg)) {
                for (int i = 0; i < cMsg.W.getValidCount(); i++) {
                    WRK_ORD_SERTMsg tMsg = new WRK_ORD_SERTMsg();

                    tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
                    tMsg.wrkOrdNum.setValue(cMsg.wrkOrdNum_SV.getValue());
                    tMsg.wrkOrdDtlLineNum.setValue(cMsg.W.no(i).wrkOrdDtlLineNum_W1.getValue());
                    tMsg.serNum.setValue(cMsg.W.no(i).serNum_W1.getValue());

                    EZDTBLAccessor.insert(tMsg);

                    if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(tMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NLBM0024E, new String[] {INSERT_WRK_ORD});
                        return false;
                    }
                }
            }
        }

        return true;

    }

    /**
     * Call Allocation For Non CPO API
     * @param cMsg NPAL1360CMsg
     * @return boolean
     */
    public static boolean callAllocationForNonCpoApi(NPAL1360CMsg cMsg) {

        // Set parameter for Allocation for non CPO API.
        NWZC107001PMsg allocNonCpoApiPMsg = new NWZC107001PMsg();

        allocNonCpoApiPMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        allocNonCpoApiPMsg.xxSysSrcCd.setValue(SYS_SRC.S21_PROCUREMENT);
        allocNonCpoApiPMsg.xxRqstTpCd.setValue(NWZC107001.REQ_TP_NEW);
        allocNonCpoApiPMsg.xxPrtlAcptFlg.setValue(ZYPConstant.FLG_OFF_N);
        allocNonCpoApiPMsg.trxSrcTpCd.setValue(cMsg.trxSrcTpCd.getValue());

        allocNonCpoApiPMsg.trxHdrNum.setValue(cMsg.wrkOrdNum.getValue());
        allocNonCpoApiPMsg.trxLineSubNum.setValue(WRK_ORD_SUB_LINE_NUM);

        allocNonCpoApiPMsg.locStsCd.setValue(LOC_STS.DC_STOCK);
        allocNonCpoApiPMsg.stkStsCd.setValue(STK_STS.GOOD);

        allocNonCpoApiPMsg.rsdDt.setValue(cMsg.slsDt.getValue());
        allocNonCpoApiPMsg.xxUnitPrc.setValue(BigDecimal.ZERO);
        allocNonCpoApiPMsg.xxSlsAmt.setValue(BigDecimal.ZERO);
        allocNonCpoApiPMsg.slsDt.setValue(cMsg.slsDt.getValue());
        allocNonCpoApiPMsg.dropShipFlg.setValue(ZYPConstant.FLG_OFF_N);

        SCE_ORD_TPTMsg tMsg = new SCE_ORD_TPTMsg();
        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.inbdOtbdCd.setValue(INBD_OTBD.OUTBOUND);

        ZYPEZDItemValueSetter.setValue(cMsg.xxYesNoCd, ZYPConstant.FLG_OFF_N);

        if (DS_WRK_ORD_TP.KIT.equals(cMsg.dsWrkOrdTpCd_SL.getValue())) {
            tMsg.sceOrdTpCd.setValue(SCE_ORD_TP.KITTING);
            SCE_ORD_TPTMsg sceOrdTpTmsg = (SCE_ORD_TPTMsg) EZDTBLAccessor.findByKey(tMsg);

            allocNonCpoApiPMsg.trxCd.setValue(sceOrdTpTmsg.trxCd.getValue());
            allocNonCpoApiPMsg.trxRsnCd.setValue(sceOrdTpTmsg.trxRsnCd.getValue());

            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                if (MDSE_TP.SET.equals(cMsg.A.no(i).mdseTpCd_A1.getValue())) {
                    // Skip Set Item
                    continue;
                }
                allocNonCpoApiPMsg.trxLineNum.setValue(cMsg.A.no(i).wrkOrdDtlLineNum_A1.getValue());

                allocNonCpoApiPMsg.mdseCd.setValue(cMsg.A.no(i).mdseCd_A1.getValue());
                allocNonCpoApiPMsg.xxRqstQty.setValue(cMsg.A.no(i).invtyAllocQty_A1.getValue());

                allocNonCpoApiPMsg.invtyLocCd.setValue(cMsg.rtlWhCd.getValue() + cMsg.A.no(i).splyRtlSwhCd_A1.getValue());
                allocNonCpoApiPMsg.billToCustCd.setValue(cMsg.rtlWhCd.getValue() + cMsg.cpltRtlSwhCd.getValue());
                allocNonCpoApiPMsg.sellToCustCd.setValue(cMsg.rtlWhCd.getValue() + cMsg.cpltRtlSwhCd.getValue());
                allocNonCpoApiPMsg.shipToCustCd.setValue(cMsg.rtlWhCd.getValue() + cMsg.cpltRtlSwhCd.getValue());

                NWZC107001 nwzc107001 = new NWZC107001();
                nwzc107001.execute(allocNonCpoApiPMsg, ONBATCH_TYPE.ONLINE);

                if (!S21ApiUtil.getXxMsgIdList(allocNonCpoApiPMsg).isEmpty()) {
                    for (int j = 0; j < allocNonCpoApiPMsg.xxMsgIdList.getValidCount(); j++) {
                        String msgId = allocNonCpoApiPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                        if (ZYPCommonFunc.hasValue(msgId)) {
                            if (msgId.endsWith("E")) {
                                if (NWZM0452E.equals(msgId)) {
                                    ZYPEZDItemValueSetter.setValue(cMsg.xxYesNoCd, ZYPConstant.FLG_ON_Y);
                                } else {
                                    ZYPEZDItemValueSetter.setValue(cMsg.xxYesNoCd, ZYPConstant.FLG_OFF_N);
                                    cMsg.setMessageInfo(msgId, null);
                                    return false;
                                }
                           } else {
                                cMsg.setMessageInfo(msgId, null);
                                return false;
                            }
                        }
                    }
                }
            }
        } else if (DS_WRK_ORD_TP.UN_KIT.equals(cMsg.dsWrkOrdTpCd_SL.getValue())) {
            tMsg.sceOrdTpCd.setValue(SCE_ORD_TP.UN_KITTING);
            SCE_ORD_TPTMsg sceOrdTpTmsg = (SCE_ORD_TPTMsg) EZDTBLAccessor.findByKey(tMsg);

            allocNonCpoApiPMsg.trxCd.setValue(sceOrdTpTmsg.trxCd.getValue());
            allocNonCpoApiPMsg.trxRsnCd.setValue(sceOrdTpTmsg.trxRsnCd.getValue());

            allocNonCpoApiPMsg.trxLineNum.setValue(KIT_ITEM_LINE_NUM);
            allocNonCpoApiPMsg.mdseCd.setValue(cMsg.fnshGoodsMdseCd.getValue());
            allocNonCpoApiPMsg.xxRqstQty.setValue(cMsg.fnshGoodsOrdQty.getValue());

            allocNonCpoApiPMsg.invtyLocCd.setValue(cMsg.rtlWhCd.getValue() + cMsg.cpltRtlSwhCd.getValue());
            allocNonCpoApiPMsg.billToCustCd.setValue(cMsg.rtlWhCd.getValue() + cMsg.cpltRtlSwhCd.getValue());
            allocNonCpoApiPMsg.sellToCustCd.setValue(cMsg.rtlWhCd.getValue() + cMsg.cpltRtlSwhCd.getValue());
            allocNonCpoApiPMsg.shipToCustCd.setValue(cMsg.rtlWhCd.getValue() + cMsg.cpltRtlSwhCd.getValue());

            NWZC107001 nwzc107001 = new NWZC107001();
            nwzc107001.execute(allocNonCpoApiPMsg, ONBATCH_TYPE.ONLINE);

            if (!S21ApiUtil.getXxMsgIdList(allocNonCpoApiPMsg).isEmpty()) {
                for (int j = 0; j < allocNonCpoApiPMsg.xxMsgIdList.getValidCount(); j++) {
                    String msgId = allocNonCpoApiPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    if (ZYPCommonFunc.hasValue(msgId)) {
                        if (msgId.endsWith("E")) {
                            if (NWZM0452E.equals(msgId)) {
                                ZYPEZDItemValueSetter.setValue(cMsg.xxYesNoCd, ZYPConstant.FLG_ON_Y);
                            } else {
                                ZYPEZDItemValueSetter.setValue(cMsg.xxYesNoCd, ZYPConstant.FLG_OFF_N);
                                cMsg.setMessageInfo(msgId, null);
                            }
                       } else {
                            cMsg.setMessageInfo(msgId, null);
                            return false;
                        }
                    }
                }
            }
        }

        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxYesNoCd.getValue())) {
            cMsg.setMessageInfo(NPAM1525W, null);
        }

        return true;

    }

    /**
     * Update Work Order After Allocation
     * @param cMsg NPAL1360CMsg
     * @return boolean
     */
    public static boolean updateWorkOrderAfterAllocation(NPAL1360CMsg cMsg) {

        if (!updateWrkOrd(cMsg, WRK_ORD_STS.HARD_ALLOCATED, ZYPConstant.FLG_OFF_N)) {
            return false;
        }

        if (!updateWrkOrdDtl(cMsg, WRK_ORD_STS.HARD_ALLOCATED)) {
            return false;
        }

        return true;

    }

    /**
     * Update Work Order After Submit
     * @param cMsg NPAL1360CMsg
     * @return boolean
     */
    public static boolean updateWorkOrderAfterSubmit(NPAL1360CMsg cMsg) {

        if (!updateWrkOrd(cMsg, WRK_ORD_STS.VALIDATED, ZYPConstant.FLG_OFF_N)) {
            return false;
        }

        if (!updateWrkOrdDtl(cMsg, WRK_ORD_STS.VALIDATED)) {
            return false;
        }

        return true;

    }

    /**
     * Update WRK_ORD
     * @param cMsg NPAL1360CMsg
     * @param wrkOrdStsCd String
     * @param kitBomFlg String
     * @return boolean
     */
    public static boolean updateWrkOrd(NPAL1360CMsg cMsg, String wrkOrdStsCd, String kitBomFlg) {

        WRK_ORDTMsg wrkOrdTMsg = getWrkOrd(cMsg);

        if (wrkOrdTMsg != null) {
            if (!checkTimeStamp(wrkOrdTMsg.ezUpTime.getValue(), wrkOrdTMsg.ezUpTimeZone.getValue(), cMsg.xxRqstTs_WH.getValue(), cMsg.xxRqstTz_WH.getValue())) {
                cMsg.setMessageInfo(NPAM0006E);
                return false;
            }
        } else {
            cMsg.setMessageInfo(NPAM0006E);
            return false;
        }

        WRK_ORDTMsg tMsg = new WRK_ORDTMsg();

        tMsg.glblCmpyCd.setValue(wrkOrdTMsg.glblCmpyCd.getValue());
        tMsg.wrkOrdNum.setValue(wrkOrdTMsg.wrkOrdNum.getValue());
        tMsg.wrkOrdTpCd.setValue(wrkOrdTMsg.wrkOrdTpCd.getValue());
        if (WRK_ORD_STS_PRINT.equals(wrkOrdStsCd)) {
            tMsg.wrkOrdStsCd.setValue(wrkOrdTMsg.wrkOrdStsCd.getValue());
        } else {
            tMsg.wrkOrdStsCd.setValue(wrkOrdStsCd);
        }
        tMsg.wrkOrdDt.setValue(cMsg.slsDt.getValue());
        tMsg.invtyLocCd.setValue(wrkOrdTMsg.invtyLocCd.getValue());
        tMsg.vndCd.setValue(wrkOrdTMsg.vndCd.getValue());
        tMsg.vndNm.setValue(wrkOrdTMsg.vndNm.getValue());
        tMsg.tocCd.setValue(wrkOrdTMsg.tocCd.getValue());
        tMsg.fnshGoodsMdseCd.setValue(wrkOrdTMsg.fnshGoodsMdseCd.getValue());
        tMsg.fnshGoodsMdseNm.setValue(wrkOrdTMsg.fnshGoodsMdseNm.getValue());
        tMsg.fnshGoodsOrdQty.setValue(cMsg.invtyAllocQty.getValue());
        tMsg.fnshGoodsRcvQty.setValue(wrkOrdTMsg.fnshGoodsRcvQty.getValue());
        if (WRK_ORD_STS.CANCELLED.equals(wrkOrdStsCd)) {
            tMsg.fnshGoodsBalQty.setValue(wrkOrdTMsg.fnshGoodsCancQty.getValue());
            tMsg.fnshGoodsCancQty.setValue(wrkOrdTMsg.fnshGoodsOrdQty.getValue());
        } else {
            tMsg.fnshGoodsBalQty.setValue(cMsg.invtyAllocQty.getValue());
            tMsg.fnshGoodsCancQty.setValue(wrkOrdTMsg.fnshGoodsCancQty.getValue());
        }
        tMsg.rqstRcvDt.setValue(wrkOrdTMsg.rqstRcvDt.getValue());
        tMsg.wrkOrdMsgTxt.setValue(cMsg.wrkOrdMsgTxt.getValue());
        tMsg.stkStsCd.setValue(wrkOrdTMsg.stkStsCd.getValue());
        tMsg.rcvInvtyLocCd.setValue(wrkOrdTMsg.rcvInvtyLocCd.getValue());
        tMsg.poChrgCd.setValue(wrkOrdTMsg.poChrgCd.getValue());
        tMsg.firstProdCtrlCd.setValue(wrkOrdTMsg.firstProdCtrlCd.getValue());
        tMsg.kitBomPrintFlg.setValue(kitBomFlg);

        //Additional columns
        tMsg.wrkOrdNum.setValue(wrkOrdTMsg.wrkOrdNum.getValue());
        tMsg.dsWrkOrdTpCd.setValue(wrkOrdTMsg.dsWrkOrdTpCd.getValue());
        tMsg.rtlWhCd.setValue(wrkOrdTMsg.rtlWhCd.getValue());
        tMsg.cpltRtlSwhCd.setValue(wrkOrdTMsg.cpltRtlSwhCd.getValue());
        tMsg.oldWrkOrdNum.setValue(wrkOrdTMsg.oldWrkOrdNum.getValue());
        tMsg.fnshMdseDescShortTxt.setValue(wrkOrdTMsg.fnshMdseDescShortTxt.getValue());
        tMsg.prchReqNum.setValue(wrkOrdTMsg.prchReqNum.getValue());
        tMsg.prchReqLineNum.setValue(wrkOrdTMsg.prchReqLineNum.getValue());
        tMsg.prchReqLineSubNum.setValue(wrkOrdTMsg.prchReqLineSubNum.getValue());
        tMsg.cmpsnRevnNum.setValue(wrkOrdTMsg.cmpsnRevnNum.getValue());
        //Additional columns

        EZDTBLAccessor.update(tMsg);

        if (DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(tMsg.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxRqstTs_WH, tMsg.ezUpTime);
            ZYPEZDItemValueSetter.setValue(cMsg.xxRqstTz_WH, tMsg.ezUpTimeZone);
            return true;
        } else {
            cMsg.setMessageInfo(NLBM0024E, new String[] {UPDATE_WRK_ORD});
            return false;
        }
    }

    /**
     * Update WRK_ORD_DTL
     * @param cMsg NPAL1360CMsg
     * @param wrkOrdStsCd String
     * @return boolean
     */
    public static boolean updateWrkOrdDtl(NPAL1360CMsg cMsg, String wrkOrdStsCd) {

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {

            WRK_ORD_DTLTMsg wrkOrdDtlTMsg = getWrkOrdDtl(cMsg, cMsg.A.no(i).wrkOrdDtlLineNum_A1.getValue());

            WRK_ORD_DTLTMsg tMsg = new WRK_ORD_DTLTMsg();

            tMsg.glblCmpyCd.setValue(wrkOrdDtlTMsg.glblCmpyCd.getValue());
            tMsg.wrkOrdNum.setValue(wrkOrdDtlTMsg.wrkOrdNum.getValue());
            tMsg.wrkOrdDtlLineNum.setValue(wrkOrdDtlTMsg.wrkOrdDtlLineNum.getValue());
            tMsg.origGoodsMdseCd.setValue(cMsg.A.no(i).mdseCd_A1.getValue());
            tMsg.origGoodsMdseNm.setValue(wrkOrdDtlTMsg.origGoodsMdseNm.getValue());
            tMsg.kitMatCd.setValue(wrkOrdDtlTMsg.kitMatCd.getValue());
            tMsg.kitMatNm.setValue(wrkOrdDtlTMsg.kitMatNm.getValue());
            if (WRK_ORD_STS.CANCELLED.equals(wrkOrdStsCd)) {
                tMsg.origGoodsOrdQty.setValue(wrkOrdDtlTMsg.origGoodsOrdQty.getValue());
                tMsg.origGoodsHardAllocQty.setValue(wrkOrdDtlTMsg.origGoodsHardAllocQty.getValue());
                tMsg.origGoodsBalQty.setValue(wrkOrdDtlTMsg.origGoodsCancQty.getValue());
                tMsg.origGoodsCancQty.setValue(wrkOrdDtlTMsg.origGoodsBalQty.getValue());
            } else {
                tMsg.origGoodsOrdQty.setValue(cMsg.A.no(i).invtyAllocQty_A1.getValue());
                tMsg.origGoodsHardAllocQty.setValue(cMsg.A.no(i).invtyAllocQty_A1.getValue());
                tMsg.origGoodsBalQty.setValue(cMsg.A.no(i).invtyAllocQty_A1.getValue());
                tMsg.origGoodsCancQty.setValue(wrkOrdDtlTMsg.origGoodsCancQty.getValue());
            }
            tMsg.soNum.setValue(cMsg.soNum.getValue());
            tMsg.stkStsCd.setValue(wrkOrdDtlTMsg.stkStsCd.getValue());
            tMsg.wrkOrdSetCmpsnFlg.setValue(wrkOrdDtlTMsg.wrkOrdSetCmpsnFlg.getValue());
            //Additional columns
            tMsg.childMdseQty.setValue(wrkOrdDtlTMsg.childMdseQty.getValue());
            tMsg.origMdseDescShortTxt.setValue(wrkOrdDtlTMsg.origMdseDescShortTxt.getValue());
            tMsg.splyRtlSwhCd.setValue(wrkOrdDtlTMsg.splyRtlSwhCd.getValue());
            tMsg.origGoodsRcvQty.setValue(wrkOrdDtlTMsg.origGoodsRcvQty.getValue());

            tMsg.wrkOrdSetCmpsnFlg.setValue(wrkOrdDtlTMsg.wrkOrdSetCmpsnFlg.getValue());

            //Additional columns
            EZDTBLAccessor.update(tMsg);

            if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NLBM0024E, new String[] {UPDATE_WRK_ORD});
                return false;
            }
        }

        return true;

    }

    /**
     * Close Purchase Requisition
     * @param cMsg NPAL1360CMsg
     * @return boolean
     */
    public static boolean closePurchaseRequisition(NPAL1360CMsg cMsg) {

        // QC#17439 Add.
        boolean prHeaderCloseFlg = false;
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("prchReqNum", cMsg.prchReqNum_P1.getValue());
        ssmParam.put("lineStsCancel", PRCH_REQ_LINE_STS.CANCELLED);
        ssmParam.put("lineStsClose", PRCH_REQ_LINE_STS.CLOSED);

        S21SsmEZDResult result = NPAL1360Query.getInstance().checkPrCloseDetail(ssmParam);

        if (result.isCodeNormal()) {
            List<BigDecimal> openCnt = (List<BigDecimal>) result.getResultObject();
            if (openCnt.get(0).intValue() == 0) {
                prHeaderCloseFlg = true;
            }
        }

        // Set parameter for PR Update API.
        NPZC103001PMsg prUpdateApiPMsg = new NPZC103001PMsg();

        prUpdateApiPMsg.xxModeCd.setValue(MODE_UPDATE);
        prUpdateApiPMsg.eventId.setValue(EVENT_ID_ORDER_RELEASE);
        prUpdateApiPMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        prUpdateApiPMsg.prchReqNum.setValue(cMsg.prchReqNum_P1.getValue());
        // QC#17439 Mod.
//        prUpdateApiPMsg.prchReqStsCd.setValue(PRCH_REQ_STS.CLOSED);
        if (prHeaderCloseFlg) {
            prUpdateApiPMsg.prchReqStsCd.setValue(PRCH_REQ_STS.CLOSED);
        }

        prUpdateApiPMsg.prchReqInfo.no(INDEX_0).prchReqLineNum.setValue(cMsg.prchReqLineNum_P1.getValue());
        prUpdateApiPMsg.prchReqInfo.no(INDEX_0).prchReqLineSubNum.setValue(cMsg.prchReqLineSubNum_P1.getValue());
        prUpdateApiPMsg.prchReqInfo.no(INDEX_0).prchReqLineStsCd.setValue(PRCH_REQ_LINE_STS.CLOSED);
        prUpdateApiPMsg.prchReqInfo.no(INDEX_0).prchReqRelQty.setValue(cMsg.fnshGoodsOrdQty.getValue());
        prUpdateApiPMsg.prchReqInfo.no(INDEX_0).prchReqRelStsCd.setValue(PRCH_REQ_REL_STS.COMPLEATED);
        prUpdateApiPMsg.prchReqInfo.no(INDEX_0).wrkOrdNum.setValue(cMsg.wrkOrdNum.getValue());

        prUpdateApiPMsg.prchReqInfo.setValidCount(INDEX_1);

        NPZC103001 npzc103001 = new NPZC103001();
        npzc103001.execute(prUpdateApiPMsg, ONBATCH_TYPE.ONLINE);

        if (!S21ApiUtil.getXxMsgIdList(prUpdateApiPMsg).isEmpty()) {
            for (int j = 0; j < prUpdateApiPMsg.xxMsgIdList.getValidCount(); j++) {
                String msgId = prUpdateApiPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                if (ZYPCommonFunc.hasValue(msgId)) {
                    if (msgId.endsWith("E")) {
                        cMsg.setMessageInfo(msgId, null);
                        return false;
                    }
                }
            }
        }

        return true;

    }

    /**
     * Create New Work Order
     * @param cMsg NPAL1360CMsg
     * @return boolean
     */
    public static boolean createNewWorkOrder(NPAL1360CMsg cMsg) {

        String splitWrkOrdFlg = ZYPConstant.FLG_ON_Y;

        // Insert WRK_ORD
        if (!insertWrkOrd(cMsg, splitWrkOrdFlg)) {
            return false;
        }
        // Insert WRK_ORD_DTL
        if (!insertWrkOrdDtl(cMsg, splitWrkOrdFlg)) {
            return false;
        }
        // Insert WRK_ORD_SER
        if (!insertWrkOrdSer(cMsg, splitWrkOrdFlg)) {
            return false;
        }

        return true;

    }

    // 08/30/2017 CITS T.Hakodate Add Sol#069(QC#18270) START
    /**
     * createRws
     * @param NPAL1360CMsg
     * @return boolean
     */
    public static boolean createRws(NPAL1360CMsg cMsg) {

        String poRcvNum = null;

        // Get SO data
        SHPG_ORDTMsg shpgOrdTMsg = new SHPG_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.soNum, cMsg.soNum.getValue());
        shpgOrdTMsg = (SHPG_ORDTMsg) EZDTBLAccessor.findByKey(shpgOrdTMsg);

        // Call PO Receiving API
        poRcvNum = callPoReceivingAPI(shpgOrdTMsg, cMsg);

        // Call RWS API
        ArrayList<String> rwsNumList = callRwsAPI(shpgOrdTMsg, poRcvNum, cMsg);

        // RWS Serial API
        ArrayList<String> errMsg = callRwsSerialAPI(rwsNumList, shpgOrdTMsg, cMsg);

        if (errMsg.isEmpty()) {

            return true;

        } else {

            return false;

        }

    }

    /**
     * Call callPoReceivingAPI
     * @param SHPG_ORDTMsg
     * @return poRcvNum
     */
    public static String callPoReceivingAPI(SHPG_ORDTMsg shpgOrdTMsg, NPAL1360CMsg cMsg) {

        String whCd = null;
        String poRcvNum = null;
        String rwsRefNum = null;
        String toInvtyLocCd = null;
        String sysSrcCd = null;
        String poRcvFromLocTpCd = null;
        String poRcvFromLocCd = null;
        String poRcvTrxHdrNum = null;
        String poRcvEtaDt = null;

        String sceOrdTpCd = shpgOrdTMsg.sceOrdTpCd.getValue();

        // Get SO detail
        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put("glblCmpyCd", shpgOrdTMsg.glblCmpyCd.getValue());
        queryParam.put("soNum", shpgOrdTMsg.soNum.getValue());

        S21SsmEZDResult result = NPAL1360Query.getInstance().getShippingPlanDetail(queryParam);

        if (result.isCodeNormal()) {

            List<Map> resultList = (List<Map>) result.getResultObject();

            Map record = resultList.get(0);

            toInvtyLocCd = (String) record.get("TO_INVTY_LOC_CD");
            sysSrcCd = (String) record.get("SYS_SRC_CD");

        }

        // Get WRK_ORD data
        WRK_ORDTMsg wrkOrdTMsg = new WRK_ORDTMsg();

        ZYPEZDItemValueSetter.setValue(wrkOrdTMsg.glblCmpyCd, shpgOrdTMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(wrkOrdTMsg.wrkOrdNum, shpgOrdTMsg.trxHdrNum.getValue());

        wrkOrdTMsg = (WRK_ORDTMsg) EZDTBLAccessor.findByKey(wrkOrdTMsg);

        NLZC201001PMsg msg = new NLZC201001PMsg();

        if (SCE_ORD_TP.KITTING.equals(sceOrdTpCd)) {

            // Set list parameter

            ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(0).mdseCd, wrkOrdTMsg.fnshGoodsMdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(0).stkStsCd, wrkOrdTMsg.stkStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(0).poRcvQty, wrkOrdTMsg.fnshGoodsOrdQty.getValue());
            ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(0).poRcvTrxLineNum, "001");
            ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(0).invtyLocCd, wrkOrdTMsg.rcvInvtyLocCd.getValue());
            ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(0).shipFromInvtyLocCd, toInvtyLocCd);
            msg.OrdInfoLIst.setValidCount(1);

            whCd = wrkOrdTMsg.rtlWhCd.getValue();

        } else if (SCE_ORD_TP.UN_KITTING.equals(sceOrdTpCd)) {

            // Get Work Order Detail
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("glblCmpyCd", wrkOrdTMsg.glblCmpyCd.getValue());
            params.put("wrkOrdNum", wrkOrdTMsg.wrkOrdNum.getValue());
            params.put("flgY", ZYPConstant.FLG_ON_Y);

            S21SsmEZDResult woDetailResult = NPAL1360Query.getInstance().getWrkOrdDtl(params);

            if (woDetailResult.isCodeNormal()) {

                List<Map> resultList = (List<Map>) woDetailResult.getResultObject();

                for (int i = 0; i < resultList.size(); i++) {

                    Map record = resultList.get(i);

                    ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(i).mdseCd, (String) record.get("ORIG_GOODS_MDSE_CD"));
                    ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(i).stkStsCd, (String) record.get("STK_STS_CD"));
                    ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(i).poRcvQty, (BigDecimal) record.get("ORIG_GOODS_ORD_QTY"));
                    ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(i).poRcvTrxLineNum, (String) record.get("WRK_ORD_DTL_LINE_NUM"));
                    ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(i).invtyLocCd, (String) record.get("RCV_INVTY_LOC_CD"));
                    ZYPEZDItemValueSetter.setValue(msg.OrdInfoLIst.no(i).shipFromInvtyLocCd, toInvtyLocCd);
                    msg.OrdInfoLIst.setValidCount(i + 1);

                }
            }

            whCd = wrkOrdTMsg.invtyLocCd.getValue();
        }

        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, shpgOrdTMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, shpgOrdTMsg.shipToCustCd.getValue());
        rtlWhTMsg = (RTL_WHTMsg) S21ApiTBLAccessor.findByKey(rtlWhTMsg);

        poRcvFromLocTpCd = rtlWhTMsg.locTpCd.getValue();
        poRcvFromLocCd = shpgOrdTMsg.shipToCustCd.getValue();
        poRcvTrxHdrNum = shpgOrdTMsg.soNum.getValue();
        poRcvEtaDt = wrkOrdTMsg.rqstRcvDt.getValue();
        rwsRefNum = shpgOrdTMsg.soNum.getValue();

        ZYPEZDItemValueSetter.setValue(msg.glblCmpyCd, shpgOrdTMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(msg.sceOrdTpCd, shpgOrdTMsg.sceOrdTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(msg.poRcvFromLocTpCd, poRcvFromLocTpCd);
        ZYPEZDItemValueSetter.setValue(msg.poRcvFromLocCd, poRcvFromLocCd);
        ZYPEZDItemValueSetter.setValue(msg.whCd, whCd);
        ZYPEZDItemValueSetter.setValue(msg.poRcvDrctShipTpCd, DRCT_SHIP_TP.N_OR_A);
        ZYPEZDItemValueSetter.setValue(msg.poRcvEtaDt, poRcvEtaDt);
        ZYPEZDItemValueSetter.setValue(msg.poRcvTrxHdrNum, poRcvTrxHdrNum);
        ZYPEZDItemValueSetter.setValue(msg.rwsRefNum, rwsRefNum);
        ZYPEZDItemValueSetter.setValue(msg.sysSrcCd, sysSrcCd);
        ZYPEZDItemValueSetter.setValue(msg.svcConfigMstrPk, shpgOrdTMsg.svcConfigMstrPk);

        NLZC201001 api = new NLZC201001();
        api.execute(msg, ONBATCH_TYPE.ONLINE);

        if (!S21ApiUtil.getXxMsgIdList(msg).isEmpty()) {
            for (int j = 0; j < msg.xxMsgIdList.getValidCount(); j++) {
                String msgId = msg.xxMsgIdList.no(j).xxMsgId.getValue();
                if (ZYPCommonFunc.hasValue(msgId)) {
                    if (msgId.endsWith("E")) {
                        cMsg.setMessageInfo(msgId, null);
                    }
                }
            }
        }

        // Get PO receiving#
        poRcvNum = msg.poRcvNum.getValue();
        return poRcvNum;

    }

    /**
     * callRwsAPI
     * @param shpgOrdTMsg
     * @param poRcvNum
     * @param cMsg
     * @return
     */
    public static ArrayList<String> callRwsAPI(SHPG_ORDTMsg shpgOrdTMsg, String poRcvNum, NPAL1360CMsg cMsg) {

        String sysSrcCd = null;
        SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = null;

        // Get SO detail
        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put("glblCmpyCd", shpgOrdTMsg.glblCmpyCd.getValue());
        queryParam.put("soNum", shpgOrdTMsg.soNum.getValue());

        S21SsmEZDResult result = NPAL1360Query.getInstance().getShippingPlanDetail(queryParam);

        if (result.isCodeNormal()) {

            List<Map> resultList = (List<Map>) result.getResultObject();

            Map record = resultList.get(0);

            sysSrcCd = (String) record.get("SYS_SRC_CD");

        }

        NLZC200001PMsg msg = new NLZC200001PMsg();

        msg.glblCmpyCd.setValue(shpgOrdTMsg.glblCmpyCd.getValue());
        msg.sysSrcCd.setValue(sysSrcCd);
        msg.inbdSrcTpCd.setValue(INBD_SRC_TP.PO_RECEIVING);
        msg.poRcvNum.setValue(poRcvNum);

        NLZC200001 api = new NLZC200001();
        api.execute(msg, ONBATCH_TYPE.ONLINE);

        ArrayList<String> rwsNumList = new ArrayList<String>();

        if (!S21ApiUtil.getXxMsgIdList(msg).isEmpty()) {
            for (int j = 0; j < msg.xxMsgIdList.getValidCount(); j++) {
                String msgId = msg.xxMsgIdList.no(j).xxMsgId.getValue();
                if (ZYPCommonFunc.hasValue(msgId)) {
                    if (msgId.endsWith("E")) {
                        cMsg.setMessageInfo(msgId, null);
                    }
                }
            }

        } else {

            for (int i = 0; i < msg.RWSNumList.getValidCount(); i++) {
                rwsNumList.add(msg.RWSNumList.no(i).rwsNum.getValue());
            }
        }

        return rwsNumList;

    }

    /**
     * callRwsSerialAPI
     * @param rwsNumList
     * @param shpgOrdTMsg
     * @param cMsg
     * @return
     */
    public static ArrayList<String> callRwsSerialAPI(ArrayList<String> rwsNumList, SHPG_ORDTMsg shpgOrdTMsg, NPAL1360CMsg cMsg) {

        String sceOrdTpCd = shpgOrdTMsg.sceOrdTpCd.getValue();

        S21SsmEZDResult result = null;

        ArrayList<String> msgIdList = new ArrayList<String>();

        for (String rwsNum : rwsNumList) {

            Map<String, Object> queryParam = new HashMap<String, Object>();

            queryParam.put("glblCmpyCd", shpgOrdTMsg.glblCmpyCd.getValue());
            queryParam.put("rwsNum", rwsNum);

            String serSrchSmmId = null;

            if (SCE_ORD_TP.KITTING.equals(sceOrdTpCd)) {

                queryParam.put("rwsDtlLineNum", "001");
                queryParam.put("wrkOrdDtlLineNum", "000");

                result = NPAL1360Query.getInstance().getWrkRwsSerKit(queryParam);

            } else if (SCE_ORD_TP.UN_KITTING.equals(sceOrdTpCd)) {

                result = NPAL1360Query.getInstance().getWrkRwsSerUnKit(queryParam);
            }

            if (result.isCodeNormal()) {

                NLZC304001PMsg pMsg = new NLZC304001PMsg();
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, shpgOrdTMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.rwsNum, rwsNum);

                int cnt = 0;

                List<Map> resultList = (List<Map>) result.getResultObject();

                for (int i = 0; i < resultList.size(); i++) {

                    Map record = resultList.get(i);

                    ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(cnt).rwsDtlLineNum, (String) record.get("RWS_DTL_LINE_NUM"));
                    ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(cnt).serNum, (String) record.get("SER_NUM"));
                    ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(cnt).mdseCd, (String) record.get("MDSE_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(cnt).putAwayChkStsCd, PUT_AWAY_CHK_STS.NOT_PROCESSED);
                    ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(cnt).serNumSendFlg, ZYPConstant.FLG_OFF_N);
                    cnt++;

                }

                pMsg.SerialList.setValidCount(cnt);

                NLZC304001 api = new NLZC304001();
                api.execute(pMsg, ONBATCH_TYPE.ONLINE);

                if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
                    for (int j = 0; j < pMsg.xxMsgIdList.getValidCount(); j++) {
                        String msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                        if (ZYPCommonFunc.hasValue(msgId)) {
                            if (msgId.endsWith("E")) {
                                cMsg.setMessageInfo(msgId, null);
                                msgIdList.add(pMsg.xxMsgIdList.no(j).xxMsgId.getValue());
                            }
                        }
                    }

                }
            }
        }

        return msgIdList;

    }

    // 08/30/2017 CITS T.Hakodate Add Sol#069(QC#18270) END

    /**
     * Call Shipping Plan Update API
     * @param cMsg NPAL1360CMsg
     * @return boolean
     */
    public static boolean callShippingPlanUpdateApi(NPAL1360CMsg cMsg) {

        // Set parameter for Shipping Plan Update API.
        NWZC003001PMsg shpgPlnUpdApiPMsg = new NWZC003001PMsg();

        shpgPlnUpdApiPMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        shpgPlnUpdApiPMsg.shpgModeCd.setValue(NWZC003001.MODE_SHIPPINGREQUEST);
        shpgPlnUpdApiPMsg.trxSrcTpCd.setValue(cMsg.trxSrcTpCd.getValue());
        shpgPlnUpdApiPMsg.trxHdrNum.setValue(cMsg.wrkOrdNum.getValue());
        shpgPlnUpdApiPMsg.trxLineSubNum.setValue(WRK_ORD_SUB_LINE_NUM);

        if (DS_WRK_ORD_TP.KIT.equals(cMsg.dsWrkOrdTpCd_SL.getValue())) {
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).invtyCtrlFlg_A1.getValue())) {
                    shpgPlnUpdApiPMsg.trxLineNum.setValue(cMsg.A.no(i).wrkOrdDtlLineNum_A1.getValue());
                    shpgPlnUpdApiPMsg.mdseCd.setValue(cMsg.A.no(i).mdseCd_A1.getValue());
                    shpgPlnUpdApiPMsg.avalSoQty.setValue(cMsg.A.no(i).invtyAllocQty_A1.getValue());
                    shpgPlnUpdApiPMsg.shipCmntFirstLineTxt.setValue("WO#:" + cMsg.wrkOrdNum.getValue() + ", KIT ITEM:" + cMsg.fnshGoodsMdseCd.getValue() + " AND TOTAL QUANTITY: " +  cMsg.fnshGoodsOrdQty.getValue());

                    List<NWZC003001PMsg> paramList = new ArrayList<NWZC003001PMsg>();
                    paramList.add(shpgPlnUpdApiPMsg);

                    NWZC003001 nwzc003001 = new NWZC003001();
                    nwzc003001.execute(paramList, ONBATCH_TYPE.ONLINE);

                    for (NWZC003001PMsg param : paramList) {
                        if (!S21ApiUtil.getXxMsgIdList(param).isEmpty()) {
                            for (int j = 0; j < shpgPlnUpdApiPMsg.xxMsgIdList.getValidCount(); j++) {
                                String msgId = shpgPlnUpdApiPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                                if (ZYPCommonFunc.hasValue(msgId)) {
                                    if (msgId.endsWith("E")) {
                                        cMsg.setMessageInfo(msgId, null);
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (DS_WRK_ORD_TP.UN_KIT.equals(cMsg.dsWrkOrdTpCd_SL.getValue())) {
            shpgPlnUpdApiPMsg.trxLineNum.setValue(KIT_ITEM_LINE_NUM);
            shpgPlnUpdApiPMsg.mdseCd.setValue(cMsg.fnshGoodsMdseCd.getValue());
            shpgPlnUpdApiPMsg.avalSoQty.setValue(cMsg.fnshGoodsOrdQty.getValue());
            shpgPlnUpdApiPMsg.shipCmntFirstLineTxt.setValue("WO#:" + cMsg.wrkOrdNum.getValue() + ", UnKIT ITEM:" + cMsg.fnshGoodsMdseCd.getValue() + " AND TOTAL QUANTITY: " +  cMsg.fnshGoodsOrdQty.getValue());

            List<NWZC003001PMsg> paramList = new ArrayList<NWZC003001PMsg>();
            paramList.add(shpgPlnUpdApiPMsg);

            NWZC003001 nwzc003001 = new NWZC003001();
            nwzc003001.execute(paramList, ONBATCH_TYPE.ONLINE);

            for (NWZC003001PMsg param : paramList) {
                if (!S21ApiUtil.getXxMsgIdList(param).isEmpty()) {
                    for (int j = 0; j < shpgPlnUpdApiPMsg.xxMsgIdList.getValidCount(); j++) {
                        String msgId = shpgPlnUpdApiPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                        if (ZYPCommonFunc.hasValue(msgId)) {
                            if (msgId.endsWith("E")) {
                                cMsg.setMessageInfo(msgId, null);
                                return false;
                            }
                        }
                    }
                }
            }
        }

        return true;

    }

    /**
     * Call SO API
     * @param cMsg NPAL1360CMsg
     * @return boolean
     */
    public static boolean callShippingOrderApi(NPAL1360CMsg cMsg) {

        List<NLZC205001PMsg> soApiPMsgList = new ArrayList<NLZC205001PMsg>();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_WRK_ORD_DTL_LINE_SUB_NUM, WRK_ORD_SUB_LINE_NUM);
        ssmParam.put(DB_PARAM_SHPG_STS_CD, SHPG_STS.HARD_ALLOCATED);

        if (DS_WRK_ORD_TP.KIT.equals(cMsg.dsWrkOrdTpCd_SL.getValue())) {
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).invtyCtrlFlg_A1.getValue())) {
                    ssmParam.put(DB_PARAM_WRK_ORD_DTL_LINE_NUM, cMsg.A.no(i).wrkOrdDtlLineNum_A1.getValue());

                    S21SsmEZDResult result = NPAL1360Query.getInstance().getShippingPlan(ssmParam);

                    if (result.isCodeNormal()) {
                        List<Map> resultList = (List<Map>) result.getResultObject();
                        Map record = resultList.get(INDEX_0);

                        NLZC205001PMsg soApiPMsg = new NLZC205001PMsg();

                        soApiPMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
                        soApiPMsg.sceOrdTpCd.setValue(SCE_ORD_TP.KITTING);
                        soApiPMsg.shpgPlnNum.setValue((String) record.get(DB_COLUMN_SHPG_PLN_NUM));
                        soApiPMsg.shpgFrceFlg.setValue(NLZC205001.SHPG_FRCE_FLG_OFF);
                        soApiPMsg.xxModeCd.setValue(NLZC205001.MODE_NEW);
                        soApiPMsg.delyReqFlg.setValue(ZYPConstant.FLG_OFF_N);

                        soApiPMsgList.add(soApiPMsg);
                    }
                }
            }
        } else if (DS_WRK_ORD_TP.UN_KIT.equals(cMsg.dsWrkOrdTpCd_SL.getValue())) {
            ssmParam.put(DB_PARAM_WRK_ORD_DTL_LINE_NUM, KIT_ITEM_LINE_NUM);

            S21SsmEZDResult result = NPAL1360Query.getInstance().getShippingPlan(ssmParam);

            if (result.isCodeNormal()) {
                List<Map> resultList = (List<Map>) result.getResultObject();
                Map record = resultList.get(INDEX_0);

                NLZC205001PMsg soApiPMsg = new NLZC205001PMsg();

                soApiPMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
                soApiPMsg.sceOrdTpCd.setValue(SCE_ORD_TP.UN_KITTING);
                soApiPMsg.shpgPlnNum.setValue((String) record.get(DB_COLUMN_SHPG_PLN_NUM));
                soApiPMsg.shpgFrceFlg.setValue(NLZC205001.SHPG_FRCE_FLG_OFF);
                soApiPMsg.xxModeCd.setValue(NLZC205001.MODE_NEW);
                soApiPMsg.delyReqFlg.setValue(ZYPConstant.FLG_OFF_N);

                soApiPMsgList.add(soApiPMsg);
            }
        }

        // Call SO API
        NLZC205001 soApi = new NLZC205001();
        soApi.execute(soApiPMsgList, ONBATCH_TYPE.ONLINE);

        String soNum = null;
        for (NLZC205001PMsg param : soApiPMsgList) {
            if (S21ApiUtil.getXxMsgIdList(param).isEmpty()) {
                if (ZYPCommonFunc.hasValue(param.soNum)) {
                    soNum = param.soNum.getValue();
                    break;
                }
            } else {
                for (int j = 0; j < param.xxMsgIdList.getValidCount(); j++) {
                    String msgId = param.xxMsgIdList.no(j).xxMsgId.getValue();
                    if (ZYPCommonFunc.hasValue(msgId)) {
                        cMsg.setMessageInfo(msgId, null);
                        if (msgId.endsWith("E")) {
                            return false;
                        }
                    }
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(cMsg.soNum, soNum);

        return true;

    }

    /**
     * Call Machine Master Update API
     * @param cMsg NPAL1360CMsg
     * @return boolean
     */
    public static boolean callMachineMasterUpdateApi(NPAL1360CMsg cMsg) {

        List<NSZC001001PMsg> svcMachMstrApiPMsgList = new ArrayList<NSZC001001PMsg>();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);

        if (DS_WRK_ORD_TP.KIT.equals(cMsg.dsWrkOrdTpCd_SL.getValue())) {
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).instlBaseCtrlFlg_A1.getValue()) && ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).rcvSerTakeFlg_D1.getValue())) {
                    for (int j = 0; j < cMsg.S.getValidCount(); j++) {
                        if (cMsg.A.no(i).wrkOrdDtlLineNum_A1.getValue().equals(cMsg.S.no(j).wrkOrdDtlLineNum_S1.getValue())) {
                            ssmParam.put(DB_PARAM_MDSE_CD, cMsg.A.no(i).origGoodsMdseCd_A1.getValue());
                            ssmParam.put(DB_PARAM_SER_NUM, cMsg.S.no(j).serNum_S1.getValue());
                        } else {
                            continue;
                        }

                        S21SsmEZDResult result = NPAL1360Query.getInstance().checkServiceMachineMaster(ssmParam);

                        if (result.isCodeNormal()) {
                            List<Map> resultList = (List<Map>) result.getResultObject();
                            Map record = resultList.get(INDEX_0);

                            NSZC001001PMsg svcMachMstrApiPMsg = new NSZC001001PMsg();

                            svcMachMstrApiPMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
                            svcMachMstrApiPMsg.slsDt.setValue(cMsg.slsDt.getValue());
                            svcMachMstrApiPMsg.xxModeCd.setValue(ALLOC_ON);
                            svcMachMstrApiPMsg.svcMachMstrPk.setValue((BigDecimal) record.get(DB_COLUMN_SVC_MACH_MSTR_PK));
                            svcMachMstrApiPMsg.trxHdrNum.setValue(cMsg.wrkOrdNum.getValue());
                            svcMachMstrApiPMsg.trxLineNum.setValue(cMsg.A.no(i).wrkOrdDtlLineNum_A1.getValue());
                            svcMachMstrApiPMsg.trxLineSubNum.setValue(WRK_ORD_SUB_LINE_NUM);

                            svcMachMstrApiPMsgList.add(svcMachMstrApiPMsg);

                        }
                    }
                    // Call Machine Master Update API
                    NSZC001001 svcMachMstrApi = new NSZC001001();
                    svcMachMstrApi.execute(svcMachMstrApiPMsgList, ONBATCH_TYPE.ONLINE);

                    for (NSZC001001PMsg svcMachMstrApiPMsg : svcMachMstrApiPMsgList) {
                        if (!S21ApiUtil.getXxMsgIdList(svcMachMstrApiPMsg).isEmpty()) {
                            for (int j = 0; j < svcMachMstrApiPMsg.xxMsgIdList.getValidCount(); j++) {
                                String msgId = svcMachMstrApiPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                                if (ZYPCommonFunc.hasValue(msgId)) {
                                    cMsg.setMessageInfo(msgId, null);
                                    if (msgId.endsWith("E")) {
                                        return false;
                                    }
                                }
                            }
                        }
                    }

                } else {
                    // QC#26243
                    // START 2018/10/24 M.Naito [QC#28886, MOD]
//                    S21SsmEZDResult ssmResult = NPAL1360Query.getInstance().getNonSerSvcMachMstrList(cMsg, cMsg.A.no(i).mdseCd_A1.getValue(), cMsg.rtlWhCd.getValue() + cMsg.A.no(i).splyRtlSwhCd_A1.getValue(), cMsg.A.no(i).origGoodsOrdQty_A1.getValueInt());
                    S21SsmEZDResult ssmResult = NPAL1360Query.getInstance().getNonSerSvcMachMstrList(cMsg, cMsg.A.no(i).mdseCd_A1.getValue(), cMsg.rtlWhCd.getValue() + cMsg.A.no(i).splyRtlSwhCd_A1.getValue(), cMsg.A.no(i).invtyAllocQty_A1.getValueInt());
                    // END 2018/10/24 M.Naito [QC#28886, MOD]

                    if (ssmResult.isCodeNormal()) {

                        ArrayList<Map<String, Object>> nonSerSvcMachMstrList = (ArrayList<Map<String, Object>>) ssmResult.getResultObject();

                        if (nonSerSvcMachMstrList != null && !nonSerSvcMachMstrList.isEmpty()) {

                            for (Map<String, Object> nonSerSvcMachMstrMap : nonSerSvcMachMstrList) {

                                NSZC001001PMsg svcMachMstrApiPMsg = new NSZC001001PMsg();
                                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.slsDt, cMsg.slsDt.getValue());
                                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrPk, (BigDecimal) nonSerSvcMachMstrMap.get("SVC_MACH_MSTR_PK"));
                                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.xxModeCd, ProcessMode.ALLOCATION_ON.code);
                                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.trxHdrNum, cMsg.wrkOrdNum.getValue());
                                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.trxLineNum, cMsg.A.no(i).wrkOrdDtlLineNum_A1.getValue());
                                svcMachMstrApiPMsg.trxLineSubNum.setValue(WRK_ORD_SUB_LINE_NUM);

                                svcMachMstrApiPMsgList.add(svcMachMstrApiPMsg);
                            }
                        }
                    }
                    // Call Machine Master Update API
                    NSZC001001 svcMachMstrApi = new NSZC001001();
                    svcMachMstrApi.execute(svcMachMstrApiPMsgList, ONBATCH_TYPE.ONLINE);

                    for (NSZC001001PMsg svcMachMstrApiPMsg : svcMachMstrApiPMsgList) {
                        if (!S21ApiUtil.getXxMsgIdList(svcMachMstrApiPMsg).isEmpty()) {
                            for (int j = 0; j < svcMachMstrApiPMsg.xxMsgIdList.getValidCount(); j++) {
                                String msgId = svcMachMstrApiPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                                if (ZYPCommonFunc.hasValue(msgId)) {
                                    cMsg.setMessageInfo(msgId, null);
                                    if (msgId.endsWith("E")) {
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } else if (DS_WRK_ORD_TP.UN_KIT.equals(cMsg.dsWrkOrdTpCd_SL.getValue())) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.instlBaseCtrlFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(cMsg.rcvSerTakeFlg.getValue())) {
                for (int i = 0; i < cMsg.S.getValidCount(); i++) {
                    if (KIT_ITEM_LINE_NUM.equals(cMsg.S.no(i).wrkOrdDtlLineNum_S1.getValue())) {
                        ssmParam.put(DB_PARAM_MDSE_CD, cMsg.fnshGoodsMdseCd.getValue());
                        ssmParam.put(DB_PARAM_SER_NUM, cMsg.S.no(i).serNum_S1.getValue());
                    } else {
                        continue;
                    }

                    S21SsmEZDResult result = NPAL1360Query.getInstance().checkServiceMachineMaster(ssmParam);

                    if (result.isCodeNormal()) {
                        List<Map> resultList = (List<Map>) result.getResultObject();
                        Map record = resultList.get(INDEX_0);

                        NSZC001001PMsg svcMachMstrApiPMsg = new NSZC001001PMsg();

                        svcMachMstrApiPMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
                        svcMachMstrApiPMsg.slsDt.setValue(cMsg.slsDt.getValue());
                        svcMachMstrApiPMsg.xxModeCd.setValue(ALLOC_ON);
                        svcMachMstrApiPMsg.svcMachMstrPk.setValue((BigDecimal) record.get(DB_COLUMN_SVC_MACH_MSTR_PK));
                        svcMachMstrApiPMsg.trxHdrNum.setValue(cMsg.wrkOrdNum.getValue());
                        svcMachMstrApiPMsg.trxLineNum.setValue(KIT_ITEM_LINE_NUM);
                        svcMachMstrApiPMsg.trxLineSubNum.setValue(WRK_ORD_SUB_LINE_NUM);

                        svcMachMstrApiPMsgList.add(svcMachMstrApiPMsg);

                    }
                }
            }

            // Call Machine Master Update API
            NSZC001001 svcMachMstrApi = new NSZC001001();
            svcMachMstrApi.execute(svcMachMstrApiPMsgList, ONBATCH_TYPE.ONLINE);

            for (NSZC001001PMsg svcMachMstrApiPMsg : svcMachMstrApiPMsgList) {
                if (!S21ApiUtil.getXxMsgIdList(svcMachMstrApiPMsg).isEmpty()) {
                    for (int j = 0; j < svcMachMstrApiPMsg.xxMsgIdList.getValidCount(); j++) {
                        String msgId = svcMachMstrApiPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                        if (ZYPCommonFunc.hasValue(msgId)) {
                            cMsg.setMessageInfo(msgId, null);
                            if (msgId.endsWith("E")) {
                                return false;
                            }
                        }
                    }
                }
            }
        }

        return true;

    }

    /**
     * Cancel
     * @param cMsg NPAL1360CMsg
     * @param sMsg NPAL1360SMsg
     * @return boolean
     */
    public static boolean cancel(NPAL1360CMsg cMsg, NPAL1360SMsg sMsg) {

        if (WRK_ORD_STS.SAVED.equals(cMsg.wrkOrdStsCd.getValue())) {

            if (!updateWrkOrd(cMsg, WRK_ORD_STS.CANCELLED, ZYPConstant.FLG_OFF_N)) {
                return false;
            }

            if (!updateWrkOrdDtl(cMsg, WRK_ORD_STS.CANCELLED)) {
                return false;
            }

        } else if (WRK_ORD_STS.SHIPPED.equals(cMsg.wrkOrdStsCd.getValue())) {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_CMSG, cMsg);

            S21SsmEZDResult result = NPAL1360Query.getInstance().getRws(ssmParam);

            if (result.isCodeNormal()) {
                List<Map> resultList = (List<Map>) result.getResultObject();
                Map record = resultList.get(INDEX_0);

                if (!ZYPCommonFunc.hasValue(cMsg.xxYesNoCd)) {
                    if (ZYPConstant.FLG_ON_1.equals((String) record.get(DB_COLUMN_WMS_DROP_STS_CD))) {
                        ZYPEZDItemValueSetter.setValue(cMsg.xxYesNoCd, ZYPConstant.FLG_ON_Y);
                        cMsg.setMessageInfo(NLAM1278W, null);
                        return false;
                    }
                }

                // Set RWS Complete API Parameter
                NLZC207001PMsg rwsCpltApiPMsg = new NLZC207001PMsg();

                String procStartTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss");

                rwsCpltApiPMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
                rwsCpltApiPMsg.slsDt.setValue(cMsg.slsDt.getValue());
                rwsCpltApiPMsg.rwsNum.setValue((String) record.get(DB_COLUMN_RWS_NUM));
                rwsCpltApiPMsg.rwsCloDtTmTs.setValue(procStartTs);
                rwsCpltApiPMsg.whCd.setValue((String) record.get(DB_COLUMN_WH_CD));
                rwsCpltApiPMsg.rwsRefNum.setValue((String) record.get(DB_COLUMN_RWS_REF_NUM));

                if (DS_WRK_ORD_TP.KIT.equals(cMsg.dsWrkOrdTpCd_SL.getValue())) {
                    rwsCpltApiPMsg.sceOrdTpCd.setValue(SCE_ORD_TP.KITTING);
                } else if (DS_WRK_ORD_TP.UN_KIT.equals(cMsg.dsWrkOrdTpCd_SL.getValue())) {
                    rwsCpltApiPMsg.sceOrdTpCd.setValue(SCE_ORD_TP.UN_KITTING);
                }

                // Call RWS Complete API
                NLZC207001 nlzc207001 = new NLZC207001();
                nlzc207001.execute(rwsCpltApiPMsg, ONBATCH_TYPE.ONLINE);

                if (!S21ApiUtil.getXxMsgIdList(rwsCpltApiPMsg).isEmpty()) {
                    for (int i = 0; i < rwsCpltApiPMsg.xxMsgIdList.getValidCount(); i++) {
                        String msgId = rwsCpltApiPMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                        if (ZYPCommonFunc.hasValue(msgId)) {
                            cMsg.setMessageInfo(msgId, null);
                            if (msgId.endsWith("E")) {
                                return false;
                            }
                        }
                    }
                }
            }
        }

        return true;

    }

    /**
     * Print
     * @param cMsg NPAL1360CMsg
     * @return boolean
     */
    public static boolean print(NPAL1360CMsg cMsg) {

        // 2018/03/14 Start
        Map<String, Object> ssmParamCmpsnMsg = new HashMap<String, Object>();
        ssmParamCmpsnMsg.put(DB_PARAM_CMSG, cMsg);
        Map<String, Object> cmpsnMsgMap = getCmpsnMsgMap(ssmParamCmpsnMsg);
        // 2018/03/14 End
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);

        if (DS_WRK_ORD_TP.KIT.equals(cMsg.dsWrkOrdTpCd_SL.getValue())) {
            ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.rtlWhCd.getValue());
        } else if (DS_WRK_ORD_TP.UN_KIT.equals(cMsg.dsWrkOrdTpCd_SL.getValue())) {
            ssmParam.put(DB_PARAM_INVTY_LOC_CD, cMsg.rtlWhCd.getValue());
        }

        S21SsmEZDResult result = NPAL1360Query.getInstance().getKitBom(ssmParam);

        // Get UserProfile
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        S21UserInfo userInfo = profileService.getContextUserInfo();

        String procStartTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {
                Map record = resultList.get(i);

                KIT_BOM_WRKTMsg tMsg = new KIT_BOM_WRKTMsg();

                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, (String) record.get(DB_COLUMN_GLBL_CMPY_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.wrkOrdNum, (String) record.get(DB_COLUMN_WRK_ORD_NUM));
                ZYPEZDItemValueSetter.setValue(tMsg.wrkOrdDtlLineNum, (String) record.get(DB_COLUMN_WRK_ORD_DTL_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(tMsg.bomRptTs, (procStartTs));
                ZYPEZDItemValueSetter.setValue(tMsg.usrId, (userInfo.getUserId()));
                ZYPEZDItemValueSetter.setValue(tMsg.attnNm, (String) record.get(DB_COLUMN_ATTN_NM));
                ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, (String) record.get(DB_COLUMN_FIRST_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(tMsg.fnshGoodsMdseCd, (String) record.get(DB_COLUMN_FNSH_GOODS_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.fnshMdseDescShortTxt, (String) record.get(DB_COLUMN_FNSH_MDSE_DESC_SHORT_TXT));
                ZYPEZDItemValueSetter.setValue(tMsg.fnshGoodsOrdQtyTxt, ZYPFormatUtil.formatNumToSysDisp((BigDecimal) record.get(DB_COLUMN_FNSH_GOODS_ORD_QTY)).toString());
                // QC#22324 2018/03/14 Start
                //ZYPEZDItemValueSetter.setValue(tMsg.wrkOrdMsgTxt, (String) record.get(DB_COLUMN_DS_WRK_ORD_TP_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(tMsg.wrkOrdMsgTxt, (String) record.get(DB_COLUMN_WRK_ORD_MSG_TXT));
                // QC#22324 2018/03/14 End
                ZYPEZDItemValueSetter.setValue(tMsg.rqstRcvDtTxt, (ZYPDateUtil.formatEzd8ToSysDisp((String) record.get(DB_COLUMN_RQST_RCV_DT))));
                ZYPEZDItemValueSetter.setValue(tMsg.origGoodsMdseCd, (String) record.get(DB_COLUMN_ORIG_GOODS_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.origMdseDescShortTxt, (String) record.get(DB_COLUMN_ORIG_MDSE_DESC_SHORT_TXT));
                ZYPEZDItemValueSetter.setValue(tMsg.childMdseQtyTxt, ZYPFormatUtil.formatNumToSysDisp((BigDecimal) record.get(DB_COLUMN_CHILD_MDSE_QTY)).toString());
                ZYPEZDItemValueSetter.setValue(tMsg.origGoodsOrdQtyTxt, ZYPFormatUtil.formatNumToSysDisp((BigDecimal) record.get(DB_COLUMN_ORIG_GOODS_ORD_QTY)).toString());
                ZYPEZDItemValueSetter.setValue(tMsg.bomRptDtTxt, (ZYPDateUtil.formatEzd8ToSysDisp(cMsg.slsDt.getValue())));

                if (ZYPConstant.FLG_ON_Y.equals((String) record.get(DB_COLUMN_KIT_BOM_PRINT_FLG))) {
                    ZYPEZDItemValueSetter.setValue(tMsg.kitBomTxt, (String) record.get(REPRINT));
                }

                String scdLineAddr = (String) record.get(DB_COLUMN_SCD_LINE_ADDR);
                String thirdLineAddr = (String) record.get(DB_COLUMN_THIRD_LINE_ADDR);
                String frthLineAddr = (String) record.get(DB_COLUMN_FRTH_LINE_ADDR);
                String fifthLineAddr = (String) record.get(DB_COLUMN_FIFTH_LINE_ADDR);
                String sixthLineAddr = (String) record.get(DB_COLUMN_SIXTH_LINE_ADDR);

                if (scdLineAddr == null && thirdLineAddr == null && frthLineAddr == null) {
                    ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, fifthLineAddr);
                    ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, sixthLineAddr);
                } else if (scdLineAddr != null && thirdLineAddr == null && frthLineAddr == null) {
                    ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, scdLineAddr);
                    ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, fifthLineAddr);
                    ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, sixthLineAddr);
                } else if (scdLineAddr != null && thirdLineAddr != null && frthLineAddr == null) {
                    ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, scdLineAddr);
                    ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, thirdLineAddr);
                    ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, fifthLineAddr);
                    ZYPEZDItemValueSetter.setValue(tMsg.fifthLineAddr, sixthLineAddr);
                } else if (scdLineAddr != null && thirdLineAddr != null && frthLineAddr != null) {
                    ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, scdLineAddr);
                    ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, thirdLineAddr);
                    ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, frthLineAddr);
                    ZYPEZDItemValueSetter.setValue(tMsg.fifthLineAddr, fifthLineAddr);
                    ZYPEZDItemValueSetter.setValue(tMsg.sixthLineAddr, sixthLineAddr);
                }
                // QC#22324 2018/03/14 Start
                ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, (String) record.get(DB_COLUMN_RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.rtlWhNm, (String) record.get(DB_COLUMN_RTL_WH_NM));
                ZYPEZDItemValueSetter.setValue(tMsg.whOwnrCd, (String) record.get(DB_COLUMN_WH_OWNR_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.splyRtlSwhCd, (String) record.get(DB_COLUMN_SPLY_RTL_SWH_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.soNum, (String) record.get(DB_COLUMN_SO_NUM));
                
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_01, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_01));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_02, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_02));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_03, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_03));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_04, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_04));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_05, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_05));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_06, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_06));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_07, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_07));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_08, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_08));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_09, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_09));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_10, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_10));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_11, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_11));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_12, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_12));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_13, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_13));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_14, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_14));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_15, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_15));
                // QC#22324 2018/03/14 End
                EZDTBLAccessor.insert(tMsg);

                if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(tMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NLBM0024E, new String[] {INSERT_WRK_ORD_BOM});
                    return false;
                }
            }
        }

        try {

            String rptId = ZYPCodeDataUtil.getVarCharConstValue(WO_RPT_ID, cMsg.glblCmpyCd.getValue());

            S21EIPPrintingService service = new S21EIPPrintingService();
            S21ReportRequestBean request = new S21ReportRequestBean(rptId);
            S21InputParameter inputParam = request.getInputParamBeanInstance();

            request.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
            request.setRptArcFlg(true);
            request.setRptTtlNm(REPORT_NAME + cMsg.wrkOrdNum.getValue() + " " + procStartTs);
            inputParam.addReportParameter(DB_COLUMN_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
            inputParam.addReportParameter(DB_COLUMN_USR_ID, userInfo.getUserId());
            inputParam.addReportParameter(DB_COLUMN_BOM_RPT_TS, procStartTs);
            inputParam.addReportParameter(DB_COLUMN_WRK_ORD_NUM, cMsg.wrkOrdNum.getValue());
            inputParam.addReportParameter(DB_COLUMN_INTL_LANG_VAL_COL_NM, inputParam.getSystemDefaultLanguage());
            request.setInputParamBean(inputParam);

            byte[] pdf = service.onlineReport(request);

            if (pdf != null) {
                StringBuilder fileName = new StringBuilder();
                fileName.append(String.valueOf(System.currentTimeMillis()));
                cMsg.xxFileData.setTempFilePath(null, fileName.toString(), ".pdf");
                ZYPFileWriter.writeFile(cMsg.xxFileData.getTempFilePath(), pdf);

                if (!updateWrkOrd(cMsg, WRK_ORD_STS_PRINT, ZYPConstant.FLG_ON_Y)) {
                    return false;
                }

            } else {
                throw new S21AbendException(REPORT_EXCEPTION);
            }
        } catch (S21AbendException e) {
                cMsg.setMessageInfo(ZZXM0001E, new String[] {e.getMessage()});
        }

        return true;

    }

    // 2018/03/14 Start
    /**
     * getCmpsnMsgMap
     * @param Map<String, Object> ssmParam(DB_PARAM_CMSG)
     * @return Map<String, String>
     */
    protected static Map<String, Object> getCmpsnMsgMap(Map<String, Object> ssmParam){
        
        Map<String, Object> returnCmpsnMsgMap = new HashMap<String, Object>();
        
        S21SsmEZDResult resultCmpsnMsg = NPAL1360Query.getInstance().getCmpsnMsg(ssmParam);
        if (resultCmpsnMsg.isCodeNormal() && resultCmpsnMsg.getQueryResultCount() == 1) {
            List<Map> cmpsnMsgMapList = (List<Map>) resultCmpsnMsg.getResultObject();
            Map cmpsnMsgMap = cmpsnMsgMapList.get(0);
            
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_01, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_01));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_02, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_02));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_03, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_03));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_04, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_04));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_05, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_05));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_06, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_06));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_07, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_07));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_08, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_08));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_09, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_09));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_10, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_10));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_11, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_11));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_12, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_12));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_13, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_13));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_14, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_14));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_15, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_15));
        } else {
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_01, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_02, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_03, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_04, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_05, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_06, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_07, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_08, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_09, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_10, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_11, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_12, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_13, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_14, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_15, "");
        }
        // Get Serial#
        return returnCmpsnMsgMap;
    }
    // 2018/03/14 End

    /**
     * concatSerials
     * Load serials corresponding to DTL_LINE_NUM, concatenate them, truncate up to 30 length.
     * @param cMsg NPAL1360CMsg
     * @param wrkOrdDtlLineNum String
     * @return String
     */
    protected static String concatSerials(NPAL1360CMsg cMsg, String wrkOrdDtlLineNum) {
        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < cMsg.S.getValidCount(); ++n) {
            if (wrkOrdDtlLineNum.equals(cMsg.S.no(n).wrkOrdDtlLineNum_S1.getValue()) && ZYPCommonFunc.hasValue(cMsg.S.no(n).serNum_S1)) {
                sb.append(",").append(cMsg.S.no(n).serNum_S1.getValue());
            }
        }

        // Remove first char ","
        if (sb.length() > 0) {
            sb.delete(0, 1);
        }
        // Truncate upto 30 length
        if (sb.length() > 30) {
            sb.delete(30, sb.length());
        }

        return sb.toString();
    }

    protected static void setErrorInfo(EZDCItem cItem, EZDPMsg pMsg) {
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();

                if (msgId.endsWith("E")) {
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    cItem.setErrorInfo(1, msgId, msgPrms);
                    break;
                }
            }
        }
    }
}