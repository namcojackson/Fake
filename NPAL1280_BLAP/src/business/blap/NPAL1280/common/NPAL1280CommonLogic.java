/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.blap.NPAL1280.common;

import static business.blap.NPAL1280.constant.NPAL1280Constant.*;
import static business.blap.NPAL1280.constant.NPAL1280Constant.DOT;
import static business.blap.NPAL1280.constant.NPAL1280Constant.MDSE_TP_SET;
import static business.blap.NPAL1280.constant.NPAL1280Constant.ZERO;
import static business.blap.NPAL1280.constant.NPAL1280Constant.ZZM9037E;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAM1329E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDPStringItem;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSDateItem;
import parts.common.EZDSStringItem;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTDateItem;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1280.NPAL1280CMsg;
import business.blap.NPAL1280.NPAL1280Query;
import business.blap.NPAL1280.NPAL1280SMsg;
import business.blap.NPAL1280.NPAL1280_ASMsg;
import business.blap.NPAL1280.NPAL1280_ASMsgArray;
import business.blap.NPAL1280.NPAL1280_RSMsg;
import business.blap.NPAL1280.constant.NPAL1280Constant;
import business.db.ALL_MDSE_VTMsg;
import business.db.ALL_MDSE_VTMsgArray;
import business.db.CPOTMsg;
import business.db.DS_CPO_CTAC_PSNTMsg;
import business.db.DS_CPO_CTAC_PSNTMsgArray;
import business.db.GLBL_CMPYTMsg;
import business.db.MDSETMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.OTBD_CARR_VTMsg;
import business.db.OTBD_CARR_VTMsgArray;
import business.db.PO_MSGTMsg;
import business.db.PRCH_REQTMsg;
import business.db.PRCH_REQ_DTLTMsg;
import business.db.RCV_ASN_VNDTMsg;
import business.db.RTL_WHTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SHPG_SVC_LVL_CARR_RELNTMsg;
import business.db.SHPG_SVC_LVL_CARR_RELNTMsgArray;
import business.db.VNDTMsg;
import business.parts.NFZC102001PMsg;
import business.parts.NMZC003001PMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_DefaultBillShipListPMsg;
import business.parts.NPZC103001PMsg;
import business.parts.NPZC113001PMsg;
import business.parts.NPZC129001PMsg;

import com.canon.cusa.s21.api.NFA.NFZC102001.NFZC102001;
import com.canon.cusa.s21.api.NMZ.NMZC003001.NMZC003001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.NPZC103001;
import com.canon.cusa.s21.api.NPZ.NPZC113001.NPZC113001;
import com.canon.cusa.s21.api.NPZ.NPZC129001.NPZC129001;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CurrencyConversion;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CurrencyConversionBean;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001GetDefNineSegData;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001GetDefNineSegDataBean;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_OWNR;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_QLFY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_OWNR;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * Business ID : NPAL1280 PO Requisition Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/28/2016   CITS            K.Ogino          Create          N/A
 * 02/18/2016   CITS            K.Ogino          Update          QC#3676
 * 02/26/2016   CITS            K.Ogino          Update          QC#4730
 * 02/29/2016   CITS            K.Ogino          Update          QC#4743
 * 02/29/2016   CITS            K.Ogino          Update          QC#4614
 * 02/29/2016   CITS            K.Ogino          Update          QC#4636
 * 03/01/2016   CITS            K.Ogino          Update          QC#4729
 * 03/01/2016   CITS            K.Ogino          Update          QC#4780
 * 03/03/2016   CITS            K.Ogino          Update          QC#4981
 * 03/08/2016   CITS            K.Ogino          Update          QC#5156
 * 03/08/2016   CITS            K.Ogino          Update          QC#4685
 * 03/17/2016   CITS            K.Ogino          Update          QC#5659
 * 03/21/2016   CITS            K.Ogino          Update          QC#5794
 * 03/24/2016   CITS            K.Ogino          Create          QC#5964
 * 04/04/2016   CITS            K.Ogino          Create          QC#5964
 * 05/26/2016   CITS            K.Ogino          Create          QC#8945
 * 05/26/2016   CITS            K.Ogino          Create          QC#8943
 * 11/02/2016   CITS            Y.IWASAKI        Update          QC#15072
 * 12/20/2016   CITS            S.Endo           Update          QC#15717
 * 01/19/2016   CITS            S.Endo           Update          QC#15717
 * 01/26/2016   CITS            S.Endo           Update          QC#15717
 * 02/10/2017   CITS            R.Shimamoto      Update          QC#5060
 * 03/01/2017   CITS            Y.IWASAKI        Update          QC#17487
 * 07/18/2017   CITS            K.Ogino          Update          QC#19433
 * 08/03/2017   CITS            R.Shimamoto      Update          QC#18761
 * 08/07/2017   CITS            R.Shimamoto      Update          QC#20439
 * 08/17/2017   CITS            K.Ogino          Update          QC#20506
 * 08/23/2017   CITS            H.Naoi           Update          Sol#097(QC#18398)
 * 10/06/2017   CITS            T.Tokutomi       Update          QC#21209
 * 12/19/2017   CITS            K.Kameoka        Update          QC#14858(SOL#060)
 * 02/26/2018   CITS            S.Katsuma        Update          QC#21867
 * 04/04/2018   CITS            K.Ogino          Update          QC#18738
 * 04/04/2018   CITS            T.Wada           Update          QC#21170
 * 04/16/2018   CITS            K.Fukumura       Update          QC#21170
 * 04/20/2018   CITS            Y.Iwasaki        Update          QC#25019
 * 05/31/2018   CITS            Y.Iwasaki        Update          QC#26231
 * 05/31/2018   CITS            Y.Iwasaki        Update          QC#26215
 * 06/26/2018   CITS            Y.Iwasaki        Update          QC#26548
 * 07/03/2018   CITS            T.Tokutomi       Update          QC#23726
 * 07/09/2018   CITS            K.Ogino          Update          QC#24918
 * 07/20/2018   CITS            K.Kameoka        Update          QC#26990
 * 08/02/2018   CITS            S.Katsuma        Update          QC#26971
 * 08/16/2018   CITS            K.Ogino          Update          QC#27819
 * 08/20/2018   CITS            T.Tokutomi       Update          QC#27655
 * 08/20/2018   CITS            K.Ogino          Update          QC#27846
 * 09/14/2018   CITS            K.Ogino          Update          QC#28216/QC#28143
 * 09/26/2018   CITS            T.Tokutomi       Update          QC#28216
 * 10/08/2018   CITS            T.Hakodate       Update          QC#28400
 * 10/24/2018   CITS            T.Tokutomi       Update          QC#28941
 * 10/24/2018   CITS            K.Kameoka        Update          QC#27770
 * 11/08/2018   CITS            T.Hakodate       Update          QC#28962
 * 11/12/2018   CITS            T.Tokutomi       Update          QC#28939
 * 11/15/2018   CITS            K.Ogino          Update          QC#29255
 * 11/15/2018   CITS            T.Tokutomi       Update          QC#29155
 * 12/17/2018   Fujitsu         S.Takami         Update          QC#29397
 * 12/17/2018   Fujitsu         S.Takami         Update          QC#29456
 * 12/21/2018   CITS            K.Ogino          Update          QC#29729
 * 01/15/2019   Fujitsu         S.Takami         Update          QC#29778
 * 01/15/2019   CITS            T.Tokutomi       Update          QC#28709
 * 01/24/2019   CITS            K.Ogino          Update          QC#29997
 * 01/28/2019   Fujitsu         S.Takami         Update          QC#29778-2 partially Deleting Logic At QC#30181
 * 2019/02/08   Fujitsu         S.Takami         Update          QC#30181
 * 2019/02/21   CITS            K.Ogino          Update          QC#30444
 * 2019/03/20   Fujitsu         T.Ogura          Update          QC#30769
 * 2019/03/25   CITS            K.Ogino          Update          QC#30768
 * 2019/04/09   CITS            K.Ogino          Update          QC#30994
 * 2019/04/17   CITS            K.Ogino          Update          QC#31087
 * 2019/07/15   CITS            K.Ogino          Update          QC#51575
 * 2019/07/17   CITS            T.Wada           Update          QC#51578
 * 2019/08/16   CITS            R.Shimamoto      Update          QC#52756
 * 2019/09/17   CITS            R.Shimamoto      Update          QC#53271
 * 2019/10/02   CITS            R.Shimamoto      Update          QC#53816
 * 10/04/2019   CITS            R.Shimamoto      Update          QC#53300
 * 10/18/2019   CITS            K.Ogino          Update          QC#53483
 * 12/03/2019   Fujitsu         T.Ogura          Update          QC#54814
 * 03/19/2020   CITS            M.Furugoori      Update          QC#56122
 * 04/23/2021   CITS            M.Furugoori      Update          QC#58645
 * 10/31/2022   Hitachi         N.Takatsu        Update          QC#60604
 * 11/18/2022   Hitachi         M.Kikushim       Update          QC#60605
 * 12/15/2022   CITS            F.Fadriquela     Update          QC#60917
 * 02/03/2023   Hitachi         T.Kuroda         Update          QC#60966
 * 04/05/2023   Hitachi         TZ.Win           Update          QC#60966
 * 04/28/2023   Hitachi         S.Dong           Update          QC#60966
 * 01/15/2024   CITS            K.Iwamoto        Update          QC#62443
 * 03/04/2024   CITS            S.Okamoto        Update          QC#62443
 *</pre>
 */
public class NPAL1280CommonLogic {

    // 2019/01/28 QC#29778-2 Add Start
    /** Account Defaulting Mode */
    private enum AcctDefMode { NO_OPE, SHIP_ITEM, ITEM }
    // 2019/01/28 QC#29778-2 Add End

    /**
     * Create requisition type pulldown
     * @param glblCmpyCd String
     * @param cMsg NPAL1280CMsg
     */
    public static void setRequisitionTypePulldown(String glblCmpyCd, NPAL1280CMsg cMsg) {
        S21SsmEZDResult ssmResult = null;
        if (ZYPCommonFunc.hasValue(cMsg.prchReqNum)) {
            ssmResult = NPAL1280Query.getInstance().getRequisitionList(glblCmpyCd, null);
        } else {
            ssmResult = NPAL1280Query.getInstance().getRequisitionList(glblCmpyCd, ZYPConstant.FLG_ON_Y);
        }
        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> list = (List) ssmResult.getResultObject();
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> map = list.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpCd_PD.no(i), map.get(RS_PRCH_REQ_TP_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpNm_PD.no(i), map.get(RS_PRCH_REQ_TP_DESC_TXT));
            }
        }
    }

    /**
     * Create business unit pulldown
     * @param glblCmpyCd String
     * @param cMsg NPAL1280CMsg
     */
    public static void setBusinessUnitPulldown(String glblCmpyCd, NPAL1280CMsg cMsg) {
        S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().getBusinessUnitList(glblCmpyCd);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> list = (List) ssmResult.getResultObject();
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> map = list.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.prchGrpCd_PD.no(i), map.get(RS_PRCH_GRP_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.prchGrpDescTxt_PD.no(i), map.get(RS_PRCH_GRP_DESC_TXT));
            }
        }
    }

    /**
     * Create freight term pulldown
     * @param cMsg NPAL1280CMsg
     */
    public static void setFreightTermPulldown(NPAL1280CMsg cMsg) {
        ZYPCodeDataUtil.createPulldownList(FRT_COND.class, cMsg.frtCondCd_PD, cMsg.frtCondDescTxt_PD);
    }

    /**
     * Create Service Level pulldown
     * @param cMsg NPAL1280CMsg
     */
    public static void setShipMethodPulldown(NPAL1280CMsg cMsg) {
        ZYPCodeDataUtil.createPulldownList(SHPG_SVC_LVL.class, cMsg.shpgSvcLvlCd_PD, cMsg.shpgSvcLvlDescTxt_PD);
    }

    /**
     * Create PR line type pulldown
     * @param glblCmpyCd String
     * @param asMsg NPAL1280_ACMsg
     */
    public static void setPrLineType(String glblCmpyCd, NPAL1280_ASMsg asMsg) {
        
        S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().getPrLineTypeList(glblCmpyCd, ZYPConstant.FLG_ON_Y);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> list = (List) ssmResult.getResultObject();
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> map = list.get(i);
                ZYPEZDItemValueSetter.setValue(asMsg.prchReqLineTpCd_PD.no(i), map.get(RS_PRCH_REQ_LINE_TP_CD));
                ZYPEZDItemValueSetter.setValue(asMsg.prchReqLineTpNm_PD.no(i), map.get(RS_PRCH_REQ_LINE_TP_DESC_TXT));
            }
        }
    }

    /**
     * Create AM/PM pulldown
     * @param cMsg NPAL1280CMsg
     */
    public static void setAmPmPulldownList(NPAL1280CMsg cMsg) {
        cMsg.xxTpCd_PD.no(0).setValue(AM_PM_PULLDOWN_CD_AM);
        cMsg.xxTpNm_PD.no(0).setValue(AM_PM_PULLDOWN_NM_AM);
        cMsg.xxTpCd_PD.no(1).setValue(AM_PM_PULLDOWN_CD_PM);
        cMsg.xxTpNm_PD.no(1).setValue(AM_PM_PULLDOWN_NM_PM);
    }

    /**
     * Find Default account info
     * @param glblCmpyCd String
     * @param appFuncId String
     * @param cMsg NPAL1280CMsg
     */
    //QC#28400 mod start 
    //public static void getDefAccInfo(String glblCmpyCd, String appFuncId, NPAL1280CMsg cMsg) {
    public static void getDefAccInfo(String glblCmpyCd, NPAL1280CMsg cMsg) {
      //QC#28400 mod end
        S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().getDefAccInfo(glblCmpyCd, cMsg);
        if (ssmResult.isCodeNormal()) {
            Map<String, String> result = (Map<String, String>) ssmResult.getResultObject();
            if(result!=null) {
                ZYPEZDItemValueSetter.setValue(cMsg.coaCmpyCd_GO, glblCmpyCd);
                //ZYPEZDItemValueSetter.setValue(cMsg.coaAfflCd_GO, map.get(RS_COA_AFFL_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.coaCcCd_GO, result.get(DB_COLUMN_COA_CC_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.coaBrCd_GO, result.get(DB_COLUMN_COA_BR_CD));
                //ZYPEZDItemValueSetter.setValue(cMsg.coaProdCd_GO, map.get(RS_COA_PROD_CD));
                //ZYPEZDItemValueSetter.setValue(cMsg.coaChCd_GO, map.get(RS_COA_CH_CD));
                //ZYPEZDItemValueSetter.setValue(cMsg.coaProjCd_GO, map.get(RS_COA_PROJ_CD));
                //ZYPEZDItemValueSetter.setValue(cMsg.coaExtnCd_GO, map.get(RS_COA_EXTN_CD));
                // QC#14858(Sol#060) MOD END
            }
        }
    }

    /**
     * PR Search
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     */
    public static void getPoReqList(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd) {
        cMsg.ezUpTime_AH.clear();
        cMsg.ezUpTimeZone_AH.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().getPoReqList(cMsg, glblCmpyCd, sMsg.A.length() + 1);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NMAM8181W);
                queryResCnt = sMsg.A.length();
            }

            List<Map<String, Object>> list = (List) ssmResult.getResultObject();
            BigDecimal xxTotAmt = BigDecimal.ZERO;

            // QC#28400 ADD START
            S21SsmEZDResult ssmResultPrLineTypeList = NPAL1280Query.getInstance().getPrLineTypeList(glblCmpyCd, ZYPConstant.FLG_ON_Y);
            List<Map<String, String>> prLineTypeList = new ArrayList<Map<String, String>>();
            if (ssmResult.isCodeNormal()) {
                prLineTypeList = (List) ssmResultPrLineTypeList.getResultObject();
            }
            // QC#28400 ADD END
            
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                // Header
                if (i == 0) {
                    ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum, (String) map.get(RS_PRCH_REQ_NUM));
                    ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpCd_SL, (String) map.get(RS_PRCH_REQ_TP_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.prchReqStsCd, (String) map.get(RS_PRCH_REQ_STS_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.prchReqStsNm, (String) map.get(RS_PRCH_REQ_STS_NM));
                    ZYPEZDItemValueSetter.setValue(cMsg.prchReqApvlStsCd, (String) map.get(RS_PRCH_REQ_APVL_STS_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.prchReqApvlStsNm, (String) map.get(RS_PRCH_REQ_APVL_STS_NM));
                    ZYPEZDItemValueSetter.setValue(cMsg.prchReqCratDt, (String) map.get(RS_PRCH_REQ_CRAT_DT));
                    // if (!ZYPCommonFunc.hasValue(cMsg.rqstRcvDt)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, (String) map.get(RS_RQST_RCV_DT));
                    // START 2023/02/03 T.Kuroda [QC#60966, ADD]
                    ZYPEZDItemValueSetter.setValue(cMsg.rqstShipDt, (String) map.get(RS_RQST_SHIP_DT));
                    // END   2023/02/03 T.Kuroda [QC#60966, ADD]
                    ZYPEZDItemValueSetter.setValue(cMsg.xxDtTm, (String) map.get(RS_RQST_RCV_TM));
                    // String to HH24MISS Conv HH12:MI AM/PM
                    if (ZYPCommonFunc.hasValue(cMsg.xxDtTm)) {
                        String rqstRcvTm = cMsg.xxDtTm.getValue();
                        // Substring HH24
                        String hh = rqstRcvTm.substring(0, IDX_2);
                        // Substring MI
                        String mi = rqstRcvTm.substring(IDX_2, IDX_4);
                        int hhInt = Integer.parseInt(hh);
                        String xxDtTm = "";
                        // PM pulldown select (input parameter > 12)
                        // QC#51575
                        if (hhInt >= IDX_12) {
                            // Conversion from HH24 to HH12
                            // hhInt:12-23
                            if (IDX_12 == hhInt) {
                                xxDtTm = ZYPCommonFunc.leftPad((hhInt) + ":" + mi, IDX_5, ZERO);
                            } else {
                                xxDtTm = ZYPCommonFunc.leftPad((hhInt - IDX_12) + ":" + mi, IDX_5, ZERO);
                            }
                            ZYPEZDItemValueSetter.setValue(cMsg.xxTpCd_SL, ZYPConstant.FLG_ON_1);
                            // AM pulldown select
                        } else {
                            xxDtTm = ZYPCommonFunc.leftPad((hhInt) + ":" + mi, IDX_5, ZERO);
                            ZYPEZDItemValueSetter.setValue(cMsg.xxTpCd_SL, ZYPConstant.FLG_OFF_0);
                        }
                        ZYPEZDItemValueSetter.setValue(cMsg.xxDtTm, xxDtTm);
                    }
                    // }
                    ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpNm, (String) map.get(RS_PRCH_REQ_SRC_TP_NM));
                    ZYPEZDItemValueSetter.setValue(cMsg.trxRefNum, (String) map.get(RS_TRX_REF_NUM));
                    ZYPEZDItemValueSetter.setValue(cMsg.poQlfyCd, (String) map.get(RS_PO_QLFY_CD));
                    //08/08/2017 CITS H.Naoi Add Sol#097(QC#18398) START
                    ZYPEZDItemValueSetter.setValue(cMsg.mrpPlnNm, (String) map.get(RS_MRP_PLN_NM));
                    //08/08/2017 CITS H.Naoi Add Sol#097(QC#18398) END
                    ZYPEZDItemValueSetter.setValue(cMsg.prchReqCratByNm, (String) map.get(RS_PRCH_REQ_CRAT_BY_NM));
                    ZYPEZDItemValueSetter.setValue(cMsg.prchReqRqstByPsnCd, (String) map.get(RS_PRCH_REQ_RQST_BY_PSN_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm, (String) map.get(RS_FULL_PSN_NM));
                    ZYPEZDItemValueSetter.setValue(cMsg.prchGrpCd_SL, (String) map.get(RS_PRCH_GRP_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.prchReqCmntTxt, (String) map.get(RS_PRCH_REQ_CMNT_TXT));
                    ZYPEZDItemValueSetter.setValue(cMsg.destRtlWhCd, (String) map.get(RS_DEST_RTL_WH_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm, (String) map.get(RS_RTL_WH_NM));
                    ZYPEZDItemValueSetter.setValue(cMsg.destRtlSwhCd, (String) map.get(RS_DEST_RTL_SWH_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm, (String) map.get(RS_RTL_SWH_NM));
                    ZYPEZDItemValueSetter.setValue(cMsg.xxLocNm, (String) map.get(RS_XX_LOC_NM));
                    ZYPEZDItemValueSetter.setValue(cMsg.ccyCd, (String) map.get(RS_CCY_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.prntVndCd, (String) map.get(RS_PRNT_VND_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.prntVndNm, (String) map.get(RS_PRNT_VND_NM));
                    ZYPEZDItemValueSetter.setValue(cMsg.vndCd, (String) map.get(RS_VND_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.locNm, (String) map.get(RS_LOC_NM));
                    //QC#26990 Add Start
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd, (String) map.get(RS_SHIP_TO_CUST_CD));
                    // QC#27819
                    if (!ZYPCommonFunc.hasValue(cMsg.shipToCustCd)) {
                        ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd, cMsg.destRtlWhCd);
                    }
                    //QC#26990 Add End
                    // Additional Header Ship To Location
                    ZYPEZDItemValueSetter.setValue(cMsg.xxLocNm_HS, (String) map.get(RS_SHIP_TO_LOC_NM_HS));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToAddlLocNm_HS, (String) map.get(RS_SHIP_TO_ADDL_LOC_NM_HS));
                    ZYPEZDItemValueSetter.setValue(cMsg.xxAllLineAddr_HS, (String) map.get(RS_XX_ALL_LINE_ADDR_HS));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToPostCd_HS, (String) map.get(RS_SHIP_TO_POST_CD_HS));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToCtyAddr_HS, (String) map.get(RS_SHIP_TO_CTY_ADDR_HS));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToCntyNm_HS, (String) map.get(RS_SHIP_TO_CNTY_NM_HS));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToStCd_HS, (String) map.get(RS_SHIP_TO_ST_CD_HS));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToProvNm_HS, (String) map.get(RS_SHIP_TO_PROV_NM_HS));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToCtryCd_HS, (String) map.get("CTRY_CD_HS"));
                    ZYPEZDItemValueSetter.setValue(cMsg.ctryNm_HS, (String) map.get(RS_CTRY_NM_HS));
                    ZYPEZDItemValueSetter.setValue(cMsg.ctacPsnNm_HS, (String) map.get(RS_CTAC_PSN_NM_HS));
                    //QC#26990 Add Start
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToFirstLineAddr_HS, (String) map.get(DB_SHIP_TO_FIRST_LINE_ADDR));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToScdLineAddr_HS, (String) map.get(DB_SHIP_TO_SCD_LINE_ADDR));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToThirdLineAddr_HS, (String) map.get(DB_SHIP_TO_THIRD_LINE_ADDR));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToFrthLineAddr_HS, (String) map.get(DB_SHIP_TO_FRTH_LINE_ADDR));
                    //QC#26990 Add End
                    // Additional Header Freight Information
                    // START 2022/12/15 F.Fadriquela [QC#60917, MOD]
                    /*ZYPEZDItemValueSetter.setValue(cMsg.frtCondCd_SL, (String) map.get(RS_FRT_COND_CD_HF));
                    ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_SL, (String) map.get(RS_SHPG_SVC_LVL_CD_HF));
                    ZYPEZDItemValueSetter.setValue(cMsg.carrCd_HF, (String) map.get(RS_CARR_CD_HF));
                    ZYPEZDItemValueSetter.setValue(cMsg.locNm_HF, (String) map.get(RS_LOC_NM_HF));
                    ZYPEZDItemValueSetter.setValue(cMsg.carrAcctNum_HF, (String) map.get(RS_CARR_ACCT_NUM_HF));*/
                    String prchReqLineStsCd = null;
                    if (!PRCH_REQ_STS.CANCELLED.equals((String) map.get(RS_PRCH_REQ_STS_CD))) {
                        prchReqLineStsCd = PRCH_REQ_LINE_STS.CANCELLED;
                    }
                    S21SsmEZDResult headerFrghtInfoResult = NPAL1280Query.getInstance().getHeaderFrghtInfo(glblCmpyCd, (String) map.get(RS_PRCH_REQ_NUM), prchReqLineStsCd);
                    if (headerFrghtInfoResult.isCodeNormal()) {
                        List< ? > headerFrghtInfoResultList = (List< ? >) headerFrghtInfoResult.getResultObject();
                        if (headerFrghtInfoResultList != null && headerFrghtInfoResultList.size() > 0) {
                            Map< ? , ? > headerFrghtInfoMap = (Map< ? , ? >) headerFrghtInfoResultList.get(0);
                            ZYPEZDItemValueSetter.setValue(cMsg.frtCondCd_SL, (String) headerFrghtInfoMap.get(RS_FRT_COND_CD_HF));
                            ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_SL, (String) headerFrghtInfoMap.get(RS_SHPG_SVC_LVL_CD_HF));
                            ZYPEZDItemValueSetter.setValue(cMsg.carrCd_HF, (String) headerFrghtInfoMap.get(RS_CARR_CD_HF));
                            ZYPEZDItemValueSetter.setValue(cMsg.locNm_HF, (String) headerFrghtInfoMap.get(RS_LOC_NM_HF));
                            ZYPEZDItemValueSetter.setValue(cMsg.carrAcctNum_HF, (String) headerFrghtInfoMap.get(RS_CARR_ACCT_NUM_HF));
                        }
                    }
                    // END 2022/12/15 F.Fadriquela [QC#60917, MOD]

                    // Additional Header PO Release
                    ZYPEZDItemValueSetter.setValue(cMsg.prchReqRelErrMsgTxt_P0.no(i), (String) map.get(RS_PRCH_REQ_REL_ERR_MSG_TXT_HP));
                    ZYPEZDItemValueSetter.setValue(cMsg.prchReqRelErrMsgTxt_P1.no(i), (String) map.get(RS_PRCH_REQ_REL_ERR_MSG_TXT_HP));
                    ZYPEZDItemValueSetter.setValue(cMsg.xxDtTm_HP, (String) map.get(RS_PRCH_REQ_REL_DT_TM_TS_HP));
                    if (ZYPCommonFunc.hasValue(cMsg.xxDtTm_HP)) {
                        // format yyyyMMddHHmmssSSS
                        String prchReqRelDtTmTsHp = cMsg.xxDtTm_HP.getValue();
                        // format yyyyMMddHHmmssSSS substring yyyy
                        String yyyy = prchReqRelDtTmTsHp.substring(0, IDX_4);
                        // format yyyyMMddHHmmssSSS substring mm
                        String mm = prchReqRelDtTmTsHp.substring(IDX_4, IDX_6);
                        // format yyyyMMddHHmmssSSS substring dd
                        String dd = prchReqRelDtTmTsHp.substring(IDX_6, IDX_8);
                        // format yyyyMMddHHmmssSSS substring hh
                        String hh = prchReqRelDtTmTsHp.substring(IDX_8, IDX_10);
                        // format yyyyMMddHHmmssSSS substring mi
                        String mi = prchReqRelDtTmTsHp.substring(IDX_10, IDX_12);
                        String tmpPrchReqRelDtTmTsHP = "";
                        int hhInt = Integer.parseInt(hh);
                        // QC#51575
                        // PM
                        if (hhInt >= IDX_12) {
                            // Conversion from HH24 to HH12
                            // hhint:13-23
                            if (IDX_12 == hhInt) {
                                tmpPrchReqRelDtTmTsHP = ZYPCommonFunc.leftPad((hhInt) + ":" + mi + " PM", IDX_8, ZERO);
                            } else {
                                tmpPrchReqRelDtTmTsHP = ZYPCommonFunc.leftPad((hhInt - IDX_12) + ":" + mi + " PM", IDX_8, ZERO);
                            }
                            // AM
                        } else {
                            tmpPrchReqRelDtTmTsHP = ZYPCommonFunc.leftPad(hhInt + ":" + mi + " AM", IDX_8, ZERO);
                        }
                        cMsg.xxDtTm_HP.setValue(mm + "/" + dd + "/" + yyyy + " " + tmpPrchReqRelDtTmTsHP);
                    }
                    // Additional Header Bill To Location
                    if (NPAL1280CommonLogic.isManualDropShipWHCd(glblCmpyCd, cMsg.destRtlWhCd.getValue())) {
                        // MD. QC#29997
                        String sellToCustCd = getBillToSellToByShipTo(cMsg.destRtlWhCd.getValue(), glblCmpyCd);
                        if (ZYPCommonFunc.hasValue(sellToCustCd)) {
                            getBillToLocation(cMsg, glblCmpyCd, null, sellToCustCd);
                        }
                    } else {
                        // non MD
                        getBillToLocation(cMsg, glblCmpyCd, null, (String) map.get(RS_SELL_TO_CUST_CD));
                    }
                    // Additional Header CSA Return Address
                    if (ZYPCommonFunc.hasValue(cMsg.shipToPostCd_HS)) {
                        String postCd = cMsg.shipToPostCd_HS.getValue().substring(0, IDX_5);
                        if (ZYPCommonFunc.hasValue(cMsg.shipToStCd_HS)) {
                            String shipToStCd = cMsg.shipToStCd_HS.getValue();
                            getReturnToWh(cMsg, glblCmpyCd, postCd, shipToStCd);
                        } else {
                            getDefaultReturnToWh(cMsg, glblCmpyCd);
                        }
                    } else {
                        getDefaultReturnToWh(cMsg, glblCmpyCd);
                    }
                    // Other
                    ZYPEZDItemValueSetter.setValue(cMsg.prchReqSavedFlg, (String) map.get(RS_PRCH_REQ_SAVED_FLG));
                    ZYPEZDItemValueSetter.setValue(cMsg.openStsFlg_H, (String) map.get(RS_OPEN_STS_FLG_PR));
                    ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime_AH, (String) map.get(RS_EZUPTIME_AH));
                    ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone_AH, (String) map.get(RS_EZUPTIMEZONE_AH));

                    // Who Column
                    ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistCratTs, (String) map.get(XX_REC_HIST_CRAT_TS));
                    ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistCratByNm, ZYPRecHistUtil.getFullNameForRecHist((String) map.get(XX_REC_HIST_CRAT_BY_NM)));
                    ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistUpdTs, (String) map.get(XX_REC_HIST_UPD_TS));
                    ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistUpdByNm, ZYPRecHistUtil.getFullNameForRecHist((String) map.get(XX_REC_HIST_UPD_BY_NM)));
                    ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistTblNm, (String) map.get(XX_REC_HIST_TBL_NM));
                }

                // Detail
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxLineNum_A1, (String) map.get(RS_XX_LINE_NUM));
                // QC#28400 MOD START
                // setPrLineType(glblCmpyCd, sMsg.A.no(i));
                for (int j = 0; j < prLineTypeList.size(); j++) {
                    Map<String, String> prLineMap = prLineTypeList.get(j);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqLineTpCd_PD.no(j), prLineMap.get(RS_PRCH_REQ_LINE_TP_CD));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqLineTpNm_PD.no(j), prLineMap.get(RS_PRCH_REQ_LINE_TP_DESC_TXT));
                }
                // QC#28400 MOD END
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqLineTpCd_A1, (String) map.get(RS_PRCH_REQ_LINE_TP_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseCd_A1, (String) map.get(RS_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).aslMdseCd_A1, (String) map.get(RS_ASL_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseDescShortTxt_A1, (String) map.get(RS_MDSE_DESC_SHORT_TXT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqDispQty_A1, (BigDecimal) map.get(RS_PRCH_REQ_DISP_QTY));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqDsplUomCd_A1, (String) map.get(RS_PRCH_REQ_DSPL_UOM_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prntVndNm_A1, (String) map.get(RS_PRNT_VND_NM_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).locNm_A1, (String) map.get(RS_LOC_NM_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqLineStsNm_A1, (String) map.get(RS_PRCH_REQ_LINE_STS_NM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).entDealNetUnitPrcAmt_A1, (BigDecimal) map.get(RS_ENT_DEAL_NET_UNIT_PRC_AMT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxUnitPrc_A1, (BigDecimal) map.get(RS_XX_EXT_PRICE));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqLineStsCd_HD, (String) map.get(RS_PRCH_REQ_LINE_STS_CD));
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).prchReqLineStsCd_HD) && !(PRCH_REQ_LINE_STS.CANCELLED.equals(sMsg.A.no(i).prchReqLineStsCd_HD.getValue())) && ZYPCommonFunc.hasValue(sMsg.A.no(i).xxUnitPrc_A1)) {
                    xxTotAmt = xxTotAmt.add(sMsg.A.no(i).xxUnitPrc_A1.getValue());
                }
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rqstRcvDt_A1, (String) map.get(RS_DTL_RQST_RCV_DT));
                // START 2023/02/03 T.Kuroda [QC#60966, ADD]
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rqstShipDt_A1, (String) map.get(RS_DTL_RQST_SHIP_DT));
                // END   2023/02/03 T.Kuroda [QC#60966, ADD]
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxScrItem130Txt_A1, (String) map.get(RS_XX_CHG_ACCOUNT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaMdseTpCd_A1, (String) map.get(RS_COA_MDSE_TP_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaProdCd_A1, (String) map.get(RS_COA_PROD_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqLineCmntTxt_A1, (String) map.get(RS_PRCH_REQ_LINE_CMNT_TXT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxLineNum_TR, (String) map.get(RS_DPLY_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).relRqstToPoOrdNum_A1, (String) map.get(RS_REL_RQST_TO_PO_ORD_NUM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).poSchdRelDt_A1, (String) map.get(RS_PO_SCHD_REL_DT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).poOrdNum_A1, (String) map.get(RS_PO_ORD_NUM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).poOrdDtlLineNum_A1, (String) map.get(RS_PO_ORD_DTL_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqRelDtTmTs_A1, ZYPDateUtil.formatEzd8ToDisp((String) map.get(RS_PRCH_REQ_REL_DT_TM_TS)));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).poSubmtTs_A1, ZYPDateUtil.formatEzd8ToDisp((String) map.get(RS_PO_SUBMT_TS)));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqRelErrMsgTxt_A1, (String) map.get(RS_PRCH_REQ_REL_ERR_MSG_TXT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqLineNum_A1, (String) map.get(RS_PRCH_REQ_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqLineSubNum_A1, (BigDecimal) map.get(RS_PRCH_REQ_LINE_SUB_NUM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).poMdseCmpsnTpCd_A1, (String) map.get(RS_PO_MDSE_CMPSN_TP_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prntVndCd_A1, (String) map.get(RS_PRNT_VND_CD_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).vndCd_A1, (String) map.get(RS_VND_CD_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaCmpyCd_A1, (String) map.get(RS_COA_CMPY_CD_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaAfflCd_A1, (String) map.get(RS_COA_AFFL_CD_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaBrCd_A1, (String) map.get(RS_COA_BR_CD_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaChCd_A1, (String) map.get(RS_COA_CH_CD_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaCcCd_A1, (String) map.get(RS_COA_CC_CD_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaAcctCd_A1, (String) map.get(RS_COA_ACCT_CD_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaProjCd_A1, (String) map.get(RS_COA_PROJ_CD_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaExtnCd_A1, (String) map.get(RS_COA_EXTN_CD_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaProdCd_HD, (String) map.get(RS_COA_PROD_CD_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prntCmpySetMdseFlg_A1, (String) map.get(RS_PRNT_CMPY_SET_MDSE_FLG));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).ezUpTime_AD, (String) map.get(RS_EZUPTIME_AD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).ezUpTimeZone_AD, (String) map.get(RS_EZUPTIMEZONE_AD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).openStsFlg_D, (String) map.get(RS_OPEN_STS_FLG_PRD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseCd_HD, sMsg.A.no(i).mdseCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqDispQty_HD, sMsg.A.no(i).prchReqDispQty_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).entDealNetUnitPrcAmt_HD, sMsg.A.no(i).entDealNetUnitPrcAmt_A1);
                // QC#21209
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).spclInstnCmntTxt_A1, (String) map.get(RS_SPCL_INSTN_CMNT_TXT));
                // QC#18738
                if (!ZYPCommonFunc.hasValue(cMsg.prntVndCd) && ZYPCommonFunc.hasValue((String) map.get(RS_PRNT_VND_CD))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.prntVndCd, (String) map.get(RS_PRNT_VND_CD));
                }
                if (!ZYPCommonFunc.hasValue(cMsg.prntVndNm) && ZYPCommonFunc.hasValue((String) map.get(RS_PRNT_VND_NM))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.prntVndNm, (String) map.get(RS_PRNT_VND_NM));
                }
                if (!ZYPCommonFunc.hasValue(cMsg.vndCd) && ZYPCommonFunc.hasValue((String) map.get(RS_VND_CD))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.vndCd, (String) map.get(RS_VND_CD));
                }
                if (!ZYPCommonFunc.hasValue(cMsg.locNm) && ZYPCommonFunc.hasValue((String) map.get(RS_LOC_NM))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.locNm, (String) map.get(RS_LOC_NM));
                }
                // QC#18738 End
                // QC#53483
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).trxRefLineNum_A1, (String) map.get(RS_TRX_REF_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).trxRefLineSubNum_A1, (String) map.get(RS_TRX_REF_LINE_SUB_NUM));

                // Who Column
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistCratTs_A1, (String) map.get(XX_REC_HIST_CRAT_TS_A1));
                // QC#28400 MOD START
                //ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist((String) map.get(XX_REC_HIST_CRAT_BY_NM_A1)));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistUpdTs_A1, (String) map.get(XX_REC_HIST_UPD_TS_A1));
                //ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist((String) map.get(XX_REC_HIST_UPD_BY_NM_A1)));
                
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistCratByNm_A1, (String) map.get(XX_REC_HIST_CRAT_BY_NM_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistUpdByNm_A1, (String) map.get(XX_REC_HIST_UPD_BY_NM_A1));
                // QC#28400 MOD END
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistTblNm_A1, (String) map.get(XX_REC_HIST_TBL_NM_A1));
            }
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotAmt, xxTotAmt);

            sMsg.A.setValidCount(list.size());

            // QC#21170
            NPAL1280CommonLogic.getHeaderRqstRcvDt(cMsg, sMsg, ZYPDateUtil.getSalesDate(glblCmpyCd)) ;
            // START 2023/02/03 T.Kuroda [QC#60966, ADD]
            NPAL1280CommonLogic.getHeaderRqstShipDt(cMsg, sMsg, ZYPDateUtil.getSalesDate(glblCmpyCd)) ;
            // END   2023/02/03 T.Kuroda [QC#60966, ADD]

            // Copy from SMsg to cMsg
            int i = 0;
            for (; i < queryResCnt; i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);
            // Set page number
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);
        } else {
            cMsg.setMessageInfo(NMAM0038I);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            return;
        }
    }

    /**
     * Find PO Message Info
     * @param cMsg NPAL1280CMsg
     * @param glblCmpyCd String
     */
    public static void getPoMsg(NPAL1280CMsg cMsg, String glblCmpyCd) {

        S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().getPoMsg(glblCmpyCd, cMsg.prchReqNum.getValue(), PO_MSG_TP.INTERNAL_PO_MESSAGE);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> list = (List) ssmResult.getResultObject();
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.R.no(i).poMsgPk, (BigDecimal) map.get(PO_MSG_PK));
                ZYPEZDItemValueSetter.setValue(cMsg.R.no(i).poMsgSegId, (BigDecimal) map.get(PO_MSG_SEG_ID));
                ZYPEZDItemValueSetter.setValue(cMsg.R.no(i).poMsgSubmtPsnCd, (String) map.get(PO_MSG_SUBMT_PSN_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.R.no(i).poMsgTxt, (String) map.get(PO_MSG_TXT));
            }
        }
    }

    /**
     * Copy button event
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param salesDate String
     * @param loginUserId String
     * @param loginUserNm String
     */
    public static void copyPoReqList(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, String salesDate, String loginUserId, String loginUserNm) {
        S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().getPoReqList(cMsg, glblCmpyCd, sMsg.A.length() + 1);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NMAM8181W);
                queryResCnt = sMsg.A.length();
            }

            List<Map<String, Object>> list = (List) ssmResult.getResultObject();
            BigDecimal xxTotAmt = BigDecimal.ZERO;

            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                String printVndCd = (String) map.get(RS_PRNT_VND_CD);
                String prntVndNm = (String) map.get(RS_PRNT_VND_NM);
                String vndCd = (String) map.get(RS_VND_CD);
                String locNm = (String) map.get(RS_LOC_NM);

                // Header
                if (i == 0) {
                    cMsg.prchReqNum.clear();
                    ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpCd_SL, (String) map.get(RS_PRCH_REQ_TP_CD));
                    cMsg.prchReqStsNm.clear();
                    cMsg.prchReqApvlStsCd.clear();
                    cMsg.prchReqApvlStsNm.clear();
                    ZYPEZDItemValueSetter.setValue(cMsg.prchReqCratDt, salesDate);
                    cMsg.rqstRcvDt.clear();
                    // START 2023/02/03 T.Kuroda [QC#60966, ADD]
                    cMsg.rqstShipDt.clear();
                    // END   2023/02/03 T.Kuroda [QC#60966, ADD]
                    cMsg.xxDtTm.clear();
                    ZYPEZDItemValueSetter.setValue(cMsg.xxTpCd_SL, ZYPConstant.FLG_OFF_0);
                    ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpNm, PRCH_REQ_ENT);
                    cMsg.trxRefNum.clear();
                    cMsg.poQlfyCd.clear();
                    //08/08/2017 CITS H.Naoi Add Sol#097(QC#18398) START
                    cMsg.mrpPlnNm.clear();
                    //08/08/2017 CITS H.Naoi Add Sol#097(QC#18398) END
                    ZYPEZDItemValueSetter.setValue(cMsg.prchReqCratByNm, loginUserNm);
                    ZYPEZDItemValueSetter.setValue(cMsg.prchReqRqstByPsnCd, loginUserId);
                    ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm, loginUserNm);
                    ZYPEZDItemValueSetter.setValue(cMsg.prchGrpCd_SL, (String) map.get(RS_PRCH_GRP_CD));
                    cMsg.prchReqCmntTxt.clear();
                    ZYPEZDItemValueSetter.setValue(cMsg.destRtlWhCd, (String) map.get(RS_DEST_RTL_WH_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm, (String) map.get(RS_RTL_WH_NM));
                    ZYPEZDItemValueSetter.setValue(cMsg.destRtlSwhCd, (String) map.get(RS_DEST_RTL_SWH_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm, (String) map.get(RS_RTL_SWH_NM));
                    ZYPEZDItemValueSetter.setValue(cMsg.xxLocNm, (String) map.get(RS_XX_LOC_NM));
                    ZYPEZDItemValueSetter.setValue(cMsg.prntVndCd, (String) map.get(RS_PRNT_VND_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.prntVndNm, (String) map.get(RS_PRNT_VND_NM));
                    ZYPEZDItemValueSetter.setValue(cMsg.vndCd, (String) map.get(RS_VND_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.locNm, (String) map.get(RS_LOC_NM));
                    cMsg.ccyCd.clear();
                    // Additional Header Ship To Location
                    ZYPEZDItemValueSetter.setValue(cMsg.xxLocNm_HS, (String) map.get(RS_SHIP_TO_LOC_NM_HS));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToAddlLocNm_HS, (String) map.get(RS_SHIP_TO_ADDL_LOC_NM_HS));
                    ZYPEZDItemValueSetter.setValue(cMsg.xxAllLineAddr_HS, (String) map.get(RS_XX_ALL_LINE_ADDR_HS));
                    //QC#26990 Add Start
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd, (String) map.get(RS_SHIP_TO_CUST_CD));
                    if (!ZYPCommonFunc.hasValue(cMsg.shipToCustCd)) {
                        ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd, cMsg.destRtlWhCd);
                    }
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToFirstLineAddr_HS, (String) map.get(DB_SHIP_TO_FIRST_LINE_ADDR));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToScdLineAddr_HS, (String) map.get(DB_SHIP_TO_SCD_LINE_ADDR));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToThirdLineAddr_HS, (String) map.get(DB_SHIP_TO_THIRD_LINE_ADDR));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToFrthLineAddr_HS, (String) map.get(DB_SHIP_TO_FRTH_LINE_ADDR));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToCtryCd_HS, (String) map.get(DB_CTRY_CD));
                    //QC#26990 Add End
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToPostCd_HS, (String) map.get(RS_SHIP_TO_POST_CD_HS));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToCtyAddr_HS, (String) map.get(RS_SHIP_TO_CTY_ADDR_HS));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToCntyNm_HS, (String) map.get(RS_SHIP_TO_CNTY_NM_HS));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToStCd_HS, (String) map.get(RS_SHIP_TO_ST_CD_HS));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToProvNm_HS, (String) map.get(RS_SHIP_TO_PROV_NM_HS));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToCtryCd_HS, (String) map.get("CTRY_CD_HS"));
                    ZYPEZDItemValueSetter.setValue(cMsg.ctryNm_HS, (String) map.get(RS_CTRY_NM_HS));
                    ZYPEZDItemValueSetter.setValue(cMsg.ctacPsnNm_HS, (String) map.get(RS_CTAC_PSN_NM_HS));
                    // Additional Header Freight Information
                    ZYPEZDItemValueSetter.setValue(cMsg.frtCondCd_SL, (String) map.get(RS_FRT_COND_CD_HF));
                    ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_SL, (String) map.get(RS_SHPG_SVC_LVL_CD_HF));
                    ZYPEZDItemValueSetter.setValue(cMsg.carrCd_HF, (String) map.get(RS_CARR_CD_HF));
                    ZYPEZDItemValueSetter.setValue(cMsg.locNm_HF, (String) map.get(RS_LOC_NM_HF));
                    ZYPEZDItemValueSetter.setValue(cMsg.carrAcctNum_HF, (String) map.get(RS_CARR_ACCT_NUM_HF));
                    // Additional Header PO Release
                    cMsg.prchReqRelErrMsgTxt_P0.clear();
                    cMsg.prchReqRelErrMsgTxt_P1.clear();
                    cMsg.prchReqRelErrMsgTxt_SL.clear();
                    cMsg.xxDtTm_HP.clear();
                    // Additional Header Bill To Location
                    if (NPAL1280CommonLogic.isManualDropShipWHCd(glblCmpyCd, cMsg.destRtlWhCd.getValue())) {
                        // MD
                        String sellToCustCd = getBillToSellToByShipTo(cMsg.shipToCustCd.getValue(), glblCmpyCd);
                        if (ZYPCommonFunc.hasValue(sellToCustCd)) {
                            getBillToLocation(cMsg, glblCmpyCd, null, sellToCustCd);
                        }
                    } else {
                        // non MD
                        getBillToLocation(cMsg, glblCmpyCd, null, (String) map.get(RS_SELL_TO_CUST_CD));
                    }
                    // Additional Header CSA Return Address
                    if (ZYPCommonFunc.hasValue(cMsg.shipToPostCd_HS)) {
                        String postCd = cMsg.shipToPostCd_HS.getValue().substring(0, IDX_5);
                        if (ZYPCommonFunc.hasValue(cMsg.shipToStCd_HS)) {
                            String shipToStCd = cMsg.shipToStCd_HS.getValue();
                            getReturnToWh(cMsg, glblCmpyCd, postCd, shipToStCd);
                        } else {
                            getDefaultReturnToWh(cMsg, glblCmpyCd);
                        }
                    } else {
                        getDefaultReturnToWh(cMsg, glblCmpyCd);
                    }
                    // Other
                    cMsg.prchReqSavedFlg.clear();
                    cMsg.openStsFlg_H.clear();
                    cMsg.ezUpTime_AH.clear();
                    cMsg.ezUpTimeZone_AH.clear();
                }
                // Detail
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxLineNum_A1, (String) map.get(RS_XX_LINE_NUM));
                setPrLineType(glblCmpyCd, sMsg.A.no(i));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqLineTpCd_A1, (String) map.get(RS_PRCH_REQ_LINE_TP_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseCd_A1, (String) map.get(RS_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).aslMdseCd_A1, (String) map.get(RS_ASL_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseDescShortTxt_A1, (String) map.get(RS_MDSE_DESC_SHORT_TXT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqDispQty_A1, (BigDecimal) map.get(RS_PRCH_REQ_DISP_QTY));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqDsplUomCd_A1, (String) map.get(RS_PRCH_REQ_DSPL_UOM_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prntVndNm_A1, (String) map.get(RS_PRNT_VND_NM_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).locNm_A1, (String) map.get(RS_LOC_NM_A1));
                sMsg.A.no(i).prchReqLineStsNm_A1.clear();
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).entDealNetUnitPrcAmt_A1, (BigDecimal) map.get(RS_ENT_DEAL_NET_UNIT_PRC_AMT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxUnitPrc_A1, (BigDecimal) map.get(RS_XX_EXT_PRICE));
                sMsg.A.no(i).prchReqLineStsCd_HD.clear();
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).xxUnitPrc_A1)) {
                    xxTotAmt = xxTotAmt.add(sMsg.A.no(i).xxUnitPrc_A1.getValue());
                }
                sMsg.A.no(i).rqstRcvDt_A1.clear();
                // START 2023/02/03 T.Kuroda [QC#60966, ADD]
                sMsg.A.no(i).rqstShipDt_A1.clear();
                // END   2023/02/03 T.Kuroda [QC#60966, ADD]
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxScrItem130Txt_A1, (String) map.get(RS_XX_CHG_ACCOUNT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaMdseTpCd_A1, (String) map.get(RS_COA_MDSE_TP_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaProdCd_A1, (String) map.get(RS_COA_PROD_CD));
                sMsg.A.no(i).prchReqLineCmntTxt_A1.clear();
                sMsg.A.no(i).xxLineNum_TR.clear();
                sMsg.A.no(i).relRqstToPoOrdNum_A1.clear();
                sMsg.A.no(i).poOrdNum_A1.clear();
                sMsg.A.no(i).poOrdDtlLineNum_A1.clear();
                sMsg.A.no(i).poSchdRelDt_A1.clear();
                sMsg.A.no(i).poSubmtTs_A1.clear();
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqRelDtTmTs_A1, ZYPDateUtil.formatDisp8ToEzd((String) map.get(RS_PRCH_REQ_REL_DT_TM_TS)));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqRelErrMsgTxt_A1, (String) map.get(RS_PRCH_REQ_REL_ERR_MSG_TXT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqLineNum_A1, (String) map.get(RS_PRCH_REQ_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqLineSubNum_A1, (BigDecimal) map.get(RS_PRCH_REQ_LINE_SUB_NUM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).poMdseCmpsnTpCd_A1, (String) map.get(RS_PO_MDSE_CMPSN_TP_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prntVndCd_A1, (String) map.get(RS_PRNT_VND_CD_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).vndCd_A1, (String) map.get(RS_VND_CD_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaCmpyCd_A1, (String) map.get(RS_COA_CMPY_CD_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaAfflCd_A1, (String) map.get(RS_COA_AFFL_CD_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaBrCd_A1, (String) map.get(RS_COA_BR_CD_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaChCd_A1, (String) map.get(RS_COA_CH_CD_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaCcCd_A1, (String) map.get(RS_COA_CC_CD_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaAcctCd_A1, (String) map.get(RS_COA_ACCT_CD_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaProjCd_A1, (String) map.get(RS_COA_PROJ_CD_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaExtnCd_A1, (String) map.get(RS_COA_EXTN_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaProdCd_HD, (String) map.get(RS_COA_PROD_CD_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prntCmpySetMdseFlg_A1, (String) map.get(RS_PRNT_CMPY_SET_MDSE_FLG));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseCd_HD, sMsg.A.no(i).mdseCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqDispQty_HD, sMsg.A.no(i).prchReqDispQty_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).entDealNetUnitPrcAmt_HD, sMsg.A.no(i).entDealNetUnitPrcAmt_A1);
                sMsg.A.no(i).ezUpTime_AD.clear();
                sMsg.A.no(i).ezUpTimeZone_AD.clear();
                sMsg.A.no(i).openStsFlg_D.clear();
                // QC#21209
                sMsg.A.no(i).spclInstnCmntTxt_A1.clear();

                // QC#21170 Start
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).vndCd_A1)) {
                    S21SsmEZDResult aslResult = NPAL1280Query.getInstance().findAslDtl(glblCmpyCd, sMsg.A.no(i).vndCd_A1.getValue(), sMsg.A.no(i).mdseCd_A1.getValue(), salesDate);
                    if (aslResult.isCodeNormal()) {
                        List<Map<String, Object>> aslResultList = (List) aslResult.getResultObject();
                        if (aslResultList != null && aslResultList.size() > 0) {
                            Map<String, Object> aslMap = aslResultList.get(0);
                            setRqstRcvDt(cMsg, sMsg, aslMap, glblCmpyCd, salesDate, i) ;
                            // START 2023/02/03 T.Kuroda [QC#60966, ADD]
                            // START 2023/04/28 S.Dong [QC#60966, MOD]
//                            setRqstShipDt(sMsg, aslMap, glblCmpyCd, i);
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rqstShipDt_A1, "");
                            // END 2023/04/28 S.Dong [QC#60966, MOD]
                            // END   2023/02/03 T.Kuroda [QC#60966, ADD]
                        }
                    }
                }
                // QC#21170 End
                // QC#53483
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).trxRefLineNum_A1, (String) map.get(RS_TRX_REF_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).trxRefLineSubNum_A1, (String) map.get(RS_TRX_REF_LINE_SUB_NUM));

                // QC#18738
                if (!ZYPCommonFunc.hasValue(cMsg.prntVndCd) && ZYPCommonFunc.hasValue((String) map.get(RS_PRNT_VND_CD))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.prntVndCd, (String) map.get(RS_PRNT_VND_CD));
                }
                if (!ZYPCommonFunc.hasValue(cMsg.prntVndNm) && ZYPCommonFunc.hasValue((String) map.get(RS_PRNT_VND_NM))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.prntVndNm, (String) map.get(RS_PRNT_VND_NM));
                }
                if (!ZYPCommonFunc.hasValue(cMsg.vndCd) && ZYPCommonFunc.hasValue((String) map.get(RS_VND_CD))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.vndCd, (String) map.get(RS_VND_CD));
                }
                if (!ZYPCommonFunc.hasValue(cMsg.locNm) && ZYPCommonFunc.hasValue((String) map.get(RS_LOC_NM))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.locNm, (String) map.get(RS_LOC_NM));
                }
                // QC#18738 End

            }
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotAmt, xxTotAmt);
            sMsg.A.setValidCount(list.size());

            // QC#21170
            NPAL1280CommonLogic.getHeaderRqstRcvDt(cMsg, sMsg, salesDate) ;
            // START 2023/02/03 T.Kuroda [QC#60966, ADD]
            NPAL1280CommonLogic.getHeaderRqstShipDt(cMsg, sMsg, salesDate);
            // END   2023/02/03 T.Kuroda [QC#60966, ADD]

            // Copy from SMsg to cMsg
            int i = 0;
            for (; i < queryResCnt; i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);
            // Set page number
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);
        } else {
            cMsg.setMessageInfo(NMAM0038I);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            return;
        }
    }

    /**
     * Copy button event for Cpo number
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param salesDate String
     * @param loginUserId String
     * @param loginUserNm String
     * @param coaProjAcctTpCd String
     */
    public static void copyCpoNumList(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, String salesDate, String loginUserId, String loginUserNm, String coaProjAcctTpCd) {
        S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().findCpoNumberList(cMsg, glblCmpyCd, coaProjAcctTpCd);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NMAM8181W);
                queryResCnt = sMsg.A.length();
            }
            List<Map<String, Object>> copyAllList = new ArrayList<Map<String, Object>>();
            List<String> cpoDtlLineList = new ArrayList<String>();
            List<Map<String, Object>> list = (List) ssmResult.getResultObject();

            BigDecimal xxTotAmt = BigDecimal.ZERO;
            BigDecimal dsCpoConfigPk = BigDecimal.ZERO;
            String billToCustCd = "";
            // QC#28962 mod start
            boolean billToCustExist = false;
            // QC#28962 mod end
            
            // QC#27846
            boolean isMultiAddr = false;
            boolean isMultiWH = false;
            String preDestWhCd = "";

            int lineCount = 0;
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                copyAllList.add(map);
                // CUSA Set Item Check
                String cpoOrdNum = (String) map.get(RS_CPO_ORD_NUM);
                String cpoDtlLineNum = (String) map.get(RS_CPO_DTL_LINE_NUM);
                String prntCmpySetMdseFlg = (String) map.get(RS_PRNT_CMPY_SET_MDSE_FLG);
                if (ZYPConstant.FLG_ON_Y.equals(prntCmpySetMdseFlg)) {
                    String cpoDtlLineSubNum = CPO_DTL_LINE_SUB_NUM_000;
                    // find CUSA Set Item
                    if (!(cpoDtlLineList.contains(cpoDtlLineNum))) {
                        S21SsmEZDResult ssmResult2 = NPAL1280Query.getInstance().findSetItemList(cMsg, glblCmpyCd, cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum, coaProjAcctTpCd);
                        if (ssmResult2.isCodeNormal()) {
                            int queryResCnt2 = ssmResult2.getQueryResultCount();
                            if (queryResCnt2 > sMsg.A.length()) {
                                cMsg.setMessageInfo(NMAM8181W);
                                queryResCnt2 = sMsg.A.length();
                            }
                            List<Map<String, Object>> list2 = (List) ssmResult2.getResultObject();
                            for (int c = 0; c < list2.size(); c++) {
                                Map<String, Object> map2 = list2.get(c);
                                copyAllList.add(map2);
                            }
                        }
                    }
                }
                cpoDtlLineList.add(cpoDtlLineNum);
            }
            Collections.sort(copyAllList, new Comparator<Map<String, Object>>() {
                public int compare(Map<String, Object> subNum1, Map<String, Object> subNum2) {
                    String cpoDtlLineSubNum1 = subNum1.get(RS_CPO_DTL_LINE_SUB_NUM).toString();
                    String cpoDtlLineSubNum2 = subNum2.get(RS_CPO_DTL_LINE_SUB_NUM).toString();
                    return cpoDtlLineSubNum1.compareTo(cpoDtlLineSubNum2);
                }
            });
            Collections.sort(copyAllList, new Comparator<Map<String, Object>>() {
                public int compare(Map<String, Object> dtlLineNum1, Map<String, Object> dtlLineNum2) {
                    String cpoDtlLineNum1 = dtlLineNum1.get(RS_CPO_DTL_LINE_NUM).toString();
                    String cpoDtlLineNum2 = dtlLineNum2.get(RS_CPO_DTL_LINE_NUM).toString();
                    return cpoDtlLineNum1.compareTo(cpoDtlLineNum2);
                }
            });
            // Set line data
            int orderLineCnt = 0;
            for (Map<String, Object> allMap : copyAllList) {
                String maxPrchReqLineNum = FIRST_DTL_LINE_NUM;
                String prchReqLineNum = null;
                // START 2022/11/18 M.Kikushima[QC#60605, ADD]
                boolean vndLine = false;
                // END 2022/11/18 M.Kikushima[QC#60605, ADD]
                if (sMsg.A.getValidCount() > 0) {
                    prchReqLineNum = sMsg.A.no(sMsg.A.getValidCount() - 1).prchReqLineNum_A1.getValue();
                    if (ZYPCommonFunc.hasValue(prchReqLineNum)) {
                        maxPrchReqLineNum = addDtlLineNum(prchReqLineNum);
                    } else {
                        maxPrchReqLineNum = addDtlLineNum(maxPrchReqLineNum);
                    }
                }
                // QC#27846
                if (ZYPCommonFunc.hasValue(cMsg.cpoOrdNum) && !ZYPCommonFunc.hasValue(cMsg.dsOrdPosnNum) && orderLineCnt != 0 && !isMultiAddr) {
                    // Address Check
                    if (BigDecimal.ONE.compareTo((BigDecimal) allMap.get("CPO_ROW_NUMBER")) == 0) {
                        isMultiAddr = true;
                    }
                }
                
                if (ZYPConstant.FLG_ON_Y.equals(allMap.get(RS_PRNT_CMPY_SET_MDSE_FLG))) {
                    if (CPO_DTL_LINE_SUB_NUM_000.equals(String.valueOf(allMap.get(RS_CPO_DTL_LINE_SUB_NUM)))) {
                        // Parent
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).poMdseCmpsnTpCd_A1, PO_MDSE_CMPSN_TP.PARENT);
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).prchReqLineNum_A1, maxPrchReqLineNum);
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).prchReqLineSubNum_A1, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).xxLineNum_A1, sMsg.A.no(lineCount).prchReqLineNum_A1.getValue());
                        setPrLineType(glblCmpyCd, sMsg.A.no(lineCount));
                        // START 2022/11/18 M.Kikushima[QC#60605, MOD]
                        //ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).prchReqLineTpCd_A1, PRCH_REQ_LINE_TP.GOODS);
                        //ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).prchReqLineTpCd_HD, PRCH_REQ_LINE_TP.GOODS);
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).prchReqLineTpCd_A1, (String) allMap.get(RS_LINE_TP));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).prchReqLineTpCd_HD, (String) allMap.get(RS_LINE_TP));
                        // END 2022/11/18 M.Kikushima[QC#60605, MOD]
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).mdseCd_A1, (String) allMap.get(RS_MDSE_CD));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).mdseCd_HD, (String) allMap.get(RS_MDSE_CD));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).mdseDescShortTxt_A1, (String) allMap.get(RS_MDSE_DESC_SHORT_TXT));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).prchReqDispQty_A1, (BigDecimal) allMap.get(RS_ORD_QTY));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).prchReqDispQty_HD, (BigDecimal) allMap.get(RS_ORD_QTY));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).coaMdseTpCd_A1, (String) allMap.get(RS_COA_MDSE_TP_CD));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).coaProdCd_A1, (String) allMap.get(RS_COA_PROD_CD));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).xxLineNum_TR, (String) allMap.get(RS_DPLY_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).xxScrItem130Txt_A1, (String) allMap.get(RS_XX_CHG_ACCOUNT));
                        // QC#53483
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).trxRefLineNum_A1, (String) allMap.get(RS_CPO_DTL_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).trxRefLineSubNum_A1, (String) allMap.get(RS_CPO_DTL_LINE_SUB_NUM));

                        // NPZC1130 Get Primary Vendor from
                        // ASL
                        NPZC113001 api = new NPZC113001();
                        NPZC113001PMsg pMsg = new NPZC113001PMsg();
                        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, salesDate);
                        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, (String) allMap.get(RS_SHIP_TO_CUST_CD));
                        pMsg.vndCd.clear();
                        pMsg.prntVndCd.clear();
                        // START 2018/08/02 S.Katsuma [QC#26971,MOD]
                        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, (String) allMap.get(RS_MDSE_CD));
                        // END 2018/08/02 S.Katsuma [QC#26971,MOD]
                        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
                        if (pMsg.xxMsgIdList.getValidCount() > 0) {
                            return;
                        }

                        if (pMsg.xxMsgIdList.getValidCount() == 0 && ZYPConstant.FLG_ON_Y.equals(pMsg.xxErrFlg.getValue())) {
                            // no process.
                        	// START 2022/11/18 M.Kikushima[QC#60605, MOD]
                            sMsg.A.no(lineCount).clear();
                            vndLine = true;
                        	// END 2022/11/18 M.Kikushima[QC#60605, MOD]
                        } else if (ZYPCommonFunc.hasValue(pMsg.vndCd)) {
                            findPoVndV(sMsg, glblCmpyCd, pMsg.vndCd.getValue(), lineCount);
                            findAslDtl(sMsg, glblCmpyCd, pMsg.vndCd.getValue(), (String) allMap.get(RS_MDSE_CD), salesDate, lineCount);
                            if (ZYPCommonFunc.hasValue(sMsg.A.no(lineCount).entDealNetUnitPrcAmt_A1)) {
                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).xxUnitPrc_A1, sMsg.A.no(lineCount).entDealNetUnitPrcAmt_A1.getValue().multiply((BigDecimal) allMap.get(RS_ORD_QTY)));
                            }
                            if (ZYPCommonFunc.hasValue(sMsg.A.no(lineCount).xxUnitPrc_A1)) {
                                xxTotAmt = xxTotAmt.add(sMsg.A.no(lineCount).xxUnitPrc_A1.getValue());
                            }
                        }

                    } else {
                        // Child
                        orderLineCnt++;
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).poMdseCmpsnTpCd_A1, PO_MDSE_CMPSN_TP.CHILD);
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).prchReqLineNum_A1, prchReqLineNum);
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).prchReqLineSubNum_A1, new BigDecimal((String) allMap.get(RS_CPO_DTL_LINE_SUB_NUM)));
                        if (sMsg.A.no(lineCount).prchReqLineSubNum_A1.getValueInt() == 0) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).xxLineNum_A1, sMsg.A.no(lineCount).prchReqLineNum_A1.getValue());
                        } else {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).xxLineNum_A1, sMsg.A.no(lineCount).prchReqLineNum_A1.getValue() + "." + sMsg.A.no(lineCount).prchReqLineSubNum_A1.getValue().toString());
                        }
                        setPrLineType(glblCmpyCd, sMsg.A.no(lineCount));
                        // START 2022/11/18 M.Kikushima[QC#60605, MOD]
                        //ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).prchReqLineTpCd_A1, PRCH_REQ_LINE_TP.GOODS);
                        //ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).prchReqLineTpCd_HD, PRCH_REQ_LINE_TP.GOODS);
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).prchReqLineTpCd_A1, (String) allMap.get(RS_LINE_TP));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).prchReqLineTpCd_HD, (String) allMap.get(RS_LINE_TP));
                        // END 2022/11/18 M.Kikushima[QC#60605, MOD]
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).mdseCd_A1, (String) allMap.get(RS_MDSE_CD));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).mdseCd_HD, (String) allMap.get(RS_MDSE_CD));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).mdseDescShortTxt_A1, (String) allMap.get(RS_MDSE_DESC_SHORT_TXT));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).prchReqDispQty_A1, (BigDecimal) allMap.get(RS_ORD_QTY));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).prchReqDispQty_HD, (BigDecimal) allMap.get(RS_ORD_QTY));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).coaMdseTpCd_A1, (String) allMap.get(RS_COA_MDSE_TP_CD));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).coaProdCd_A1, (String) allMap.get(RS_COA_PROD_CD));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).xxLineNum_TR, (String) allMap.get(RS_DPLY_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).xxScrItem130Txt_A1, (String) allMap.get(RS_XX_CHG_ACCOUNT));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).prntCmpySetMdseFlg_A1, (String) allMap.get(RS_PRNT_CMPY_SET_MDSE_FLG));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).entDealNetUnitPrcAmt_A1, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).entDealNetUnitPrcAmt_HD, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).prntVndNm_A1, sMsg.A.no(lineCount - 1).prntVndNm_A1);
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).prntVndCd_A1, sMsg.A.no(lineCount - 1).prntVndCd_A1);
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).locNm_A1, sMsg.A.no(lineCount - 1).locNm_A1);
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).vndCd_A1, sMsg.A.no(lineCount - 1).vndCd_A1);
                        // QC#53483
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).trxRefLineNum_A1, (String) allMap.get(RS_CPO_DTL_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).trxRefLineSubNum_A1, (String) allMap.get(RS_CPO_DTL_LINE_SUB_NUM));
                    }
                } else {
                    orderLineCnt++;
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).poMdseCmpsnTpCd_A1, PO_MDSE_CMPSN_TP.REGULAR);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).prchReqLineNum_A1, maxPrchReqLineNum);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).prchReqLineSubNum_A1, BigDecimal.ZERO);
                    // Detail
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).xxLineNum_A1, sMsg.A.no(lineCount).prchReqLineNum_A1.getValue());
                    setPrLineType(glblCmpyCd, sMsg.A.no(lineCount));
                    // START 2022/11/18 M.Kikushima[QC#60605, MOD]
                    //ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).prchReqLineTpCd_A1, PRCH_REQ_LINE_TP.GOODS);
                    //ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).prchReqLineTpCd_HD, PRCH_REQ_LINE_TP.GOODS);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).prchReqLineTpCd_A1, (String) allMap.get(RS_LINE_TP));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).prchReqLineTpCd_HD, (String) allMap.get(RS_LINE_TP));
                    // END 2022/11/18 M.Kikushima[QC#60605, MOD]
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).mdseCd_A1, (String) allMap.get(RS_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).mdseCd_HD, (String) allMap.get(RS_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).mdseDescShortTxt_A1, (String) allMap.get(RS_MDSE_DESC_SHORT_TXT));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).prchReqDispQty_A1, (BigDecimal) allMap.get(RS_ORD_QTY));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).prchReqDispQty_HD, (BigDecimal) allMap.get(RS_ORD_QTY));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).coaMdseTpCd_A1, (String) allMap.get(RS_COA_MDSE_TP_CD));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).coaProdCd_A1, (String) allMap.get(RS_COA_PROD_CD));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).xxLineNum_TR, (String) allMap.get(RS_DPLY_LINE_NUM));
                    // QC#53483
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).trxRefLineNum_A1, (String) allMap.get(RS_CPO_DTL_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).trxRefLineSubNum_A1, (String) allMap.get(RS_CPO_DTL_LINE_SUB_NUM));

                    // NPZC1130 Get Primary Vendor from ASL
                    NPZC113001 api = new NPZC113001();
                    NPZC113001PMsg pMsg = new NPZC113001PMsg();
                    ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.slsDt, salesDate);
                    ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, (String) allMap.get(RS_SHIP_TO_CUST_CD));
                    pMsg.vndCd.clear();
                    pMsg.prntVndCd.clear();
                    // START 2018/08/02 S.Katsuma [QC#26971,MOD]
                    ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, (String) allMap.get(RS_MDSE_CD));
                    // END 2018/08/02 S.Katsuma [QC#26971,MOD]
                    api.execute(pMsg, ONBATCH_TYPE.ONLINE);
                    if (pMsg.xxMsgIdList.getValidCount() > 0) {
                        return;
                    }
                    if (pMsg.xxMsgIdList.getValidCount() == 0 && ZYPConstant.FLG_ON_Y.equals(pMsg.xxErrFlg.getValue())) {
                        // no process.
                    	// START 2022/11/18 M.Kikushima[QC#60605, MOD]
                        sMsg.A.no(lineCount).clear();
                        vndLine = true;
                    	// END 2022/11/18 M.Kikushima[QC#60605, MOD]
                    } else if (ZYPCommonFunc.hasValue(pMsg.vndCd)) {
                        findPoVndV(sMsg, glblCmpyCd, pMsg.vndCd.getValue(), lineCount);
                        findAslDtl(sMsg, glblCmpyCd, pMsg.vndCd.getValue(), (String) allMap.get(RS_MDSE_CD), salesDate, lineCount);
                        if (ZYPCommonFunc.hasValue(sMsg.A.no(lineCount).entDealNetUnitPrcAmt_A1)) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineCount).xxUnitPrc_A1, sMsg.A.no(lineCount).entDealNetUnitPrcAmt_A1.getValue().multiply((BigDecimal) allMap.get(RS_ORD_QTY)));
                        }
                        if (ZYPCommonFunc.hasValue(sMsg.A.no(lineCount).xxUnitPrc_A1)) {
                            xxTotAmt = xxTotAmt.add(sMsg.A.no(lineCount).xxUnitPrc_A1.getValue());
                        }
                    }
                }

                // Header
                if (lineCount == 0) {
                    ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpCd_SL, PRCH_REQ_TP.STANDARD);
                    ZYPEZDItemValueSetter.setValue(cMsg.prchReqCratDt, salesDate);
                    ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, (String) allMap.get(RS_RDD_DT));
                    // START 2023/02/03 T.Kuroda [QC#60966, ADD]
                    ZYPEZDItemValueSetter.setValue(cMsg.rqstShipDt, salesDate);
                    // END   2023/02/03 T.Kuroda [QC#60966, ADD]
                    ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpNm, PRCH_REQ_ENT);
                    ZYPEZDItemValueSetter.setValue(cMsg.trxRefNum, (String) allMap.get(RS_CPO_ORD_NUM));
                    ZYPEZDItemValueSetter.setValue(cMsg.prchReqCratByNm, loginUserNm);
                    ZYPEZDItemValueSetter.setValue(cMsg.prchReqRqstByPsnCd, loginUserId);
                    ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm, loginUserNm);
                    ZYPEZDItemValueSetter.setValue(cMsg.prchGrpCd_SL, (String) allMap.get(RS_PRCH_GRP_CD));
                    dsCpoConfigPk = (BigDecimal) allMap.get(RS_DS_CPO_CONFIG_PK);
                    billToCustCd = (String) allMap.get(RS_BILL_TO_CUST_CD);
                    // QC#27819
                    ZYPEZDItemValueSetter.setValue(cMsg.xxLocNm, (String) allMap.get(RS_SHIP_TO_LOC_NM_HS));
                    ZYPEZDItemValueSetter.setValue(cMsg.xxLocNm_HS, (String) allMap.get(RS_SHIP_TO_LOC_NM_HS));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToAddlLocNm_HS, (String) allMap.get(RS_SHIP_TO_ADDL_LOC_NM_HS));
                    ZYPEZDItemValueSetter.setValue(cMsg.xxAllLineAddr_HS, (String) allMap.get(RS_XX_ALL_LINE_ADDR_HS));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToFirstLineAddr_HS, (String) allMap.get(DB_SHIP_TO_FIRST_LINE_ADDR));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToScdLineAddr_HS, (String) allMap.get(DB_SHIP_TO_SCD_LINE_ADDR));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToThirdLineAddr_HS, (String) allMap.get(DB_SHIP_TO_THIRD_LINE_ADDR));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToFrthLineAddr_HS, (String) allMap.get(DB_SHIP_TO_FRTH_LINE_ADDR));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToCtryCd_HS, (String) allMap.get(DB_CTRY_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToPostCd_HS, (String) allMap.get(RS_SHIP_TO_POST_CD_HS));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToCtyAddr_HS, (String) allMap.get(RS_SHIP_TO_CTY_ADDR_HS));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToCntyNm_HS, (String) allMap.get(RS_SHIP_TO_CNTY_NM_HS));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToStCd_HS, (String) allMap.get(RS_SHIP_TO_ST_CD_HS));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToProvNm_HS, (String) allMap.get("SHIP_TO_PROV_ADDR_HS"));
                    ZYPEZDItemValueSetter.setValue(cMsg.ctryNm_HS, (String) allMap.get(RS_CTRY_NM_HS));

                    ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd, (String) allMap.get(RS_SHIP_TO_CUST_CD));

                    if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd)) {

                        ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd_CP, cMsg.shipToCustCd);
                        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, glblCmpyCd);

                        if (DS_ORD_CATG.INTERNAL_CSA.equals((String) allMap.get("DS_ORD_CATG_CD"))) {
                            ZYPEZDItemValueSetter.setValue(cMsg.destRtlWhCd, (String) allMap.get(DB_COLUMN_RTL_WH_CD));
                            ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd, cMsg.destRtlWhCd);
                            NPAL1280CommonLogic.setDestWhInfo(cMsg, sMsg);
                            ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm, cMsg.destRtlSwhCd);
                            NPAL1280CommonLogic.setShipToAddressFromShipToCustomer(cMsg, sMsg);
                        } else {
                            cMsg.destRtlWhCd.clear();
                            // QC#28962 mod start
                            String destWhCd = (String) allMap.get("RTL_WH_CD");
                            String manualDropShipWHCd = NPAL1280CommonLogic.getFirstManualDropShipWHCd(cMsg.glblCmpyCd.getValue());
                            if (RTL_WH_CATG.VIRTUAL.equals((String) allMap.get("RTL_WH_CATG_CD")) || manualDropShipWHCd.equals(destWhCd)) {
                                destWhCd = NPAL1280CommonLogic.getFirstManualDropShipWHCd(cMsg.glblCmpyCd.getValue());
                            } else {
                                if (ZYPCommonFunc.hasValue(destWhCd)) {
                                    ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd, destWhCd);
                                    ZYPEZDItemValueSetter.setValue(cMsg.xxLocNm, getRtlWhNm(glblCmpyCd, destWhCd));
                                    NPAL1280CommonLogic.setShipToAddressFromS21Wh(cMsg, sMsg);
                                    String sellToCustCd = NPAL1280CommonLogic.getBillToSellToByShipTo(cMsg.shipToCustCd.getValue(), glblCmpyCd);
                                    if (ZYPCommonFunc.hasValue(sellToCustCd)) {
                                        NPAL1280CommonLogic.getBillToLocation(cMsg, glblCmpyCd, null, sellToCustCd);
                                        billToCustExist = true;
                                    }
                                }
                            }
                            // QC#28962 mod end
                            ZYPEZDItemValueSetter.setValue(cMsg.destRtlWhCd, destWhCd);
                            NPAL1280CommonLogic.setDestWhInfo(cMsg, sMsg);
                            ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm, cMsg.destRtlSwhCd);
                        }
                    }
                    
                    // QC#28941 Add.
                    ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_SL, (String) allMap.get(RS_REQ_SHPG_SVC_LVL_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.carrCd_HF, (String) allMap.get(RS_RQST_CARR_CD));

                    if(ZYPCommonFunc.hasValue(cMsg.carrCd_HF)){
                        ZYPEZDItemValueSetter.setValue(cMsg.locNm_HF, getCarrierName(glblCmpyCd, cMsg.carrCd_HF.getValue()));
                    }

                    String frtCondCd = (String) allMap.get(RS_FRT_COND_CD);
                    if(ZYPCommonFunc.hasValue(frtCondCd) && FRT_COND.COLLECT.equals(frtCondCd)){
                        ZYPEZDItemValueSetter.setValue(cMsg.frtCondCd_SL, frtCondCd);
                        ZYPEZDItemValueSetter.setValue(cMsg.carrAcctNum_HF, (String) allMap.get(RS_CARR_ACCT_NUM));
                    }
                }

                String destWhCd = (String) allMap.get("RTL_WH_CD");

                // QC#28962 mod start
                if (!ZYPCommonFunc.hasValue(cMsg.destRtlWhCd)) {
                    String manualDropShipWHCd = NPAL1280CommonLogic.getFirstManualDropShipWHCd(cMsg.glblCmpyCd.getValue());
                    if (RTL_WH_CATG.VIRTUAL.equals((String) allMap.get("RTL_WH_CATG_CD")) || manualDropShipWHCd.equals(destWhCd)) {
                        destWhCd = NPAL1280CommonLogic.getFirstManualDropShipWHCd(cMsg.glblCmpyCd.getValue());
                    } else {
                        if (ZYPCommonFunc.hasValue(destWhCd)) {
                            ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd, destWhCd);
                            ZYPEZDItemValueSetter.setValue(cMsg.xxLocNm, getRtlWhNm(glblCmpyCd, destWhCd));
                            NPAL1280CommonLogic.setShipToAddressFromS21Wh(cMsg, sMsg);
                            String sellToCustCd = NPAL1280CommonLogic.getBillToSellToByShipTo(cMsg.shipToCustCd.getValue(), glblCmpyCd);
                            if (ZYPCommonFunc.hasValue(sellToCustCd)) {
                                NPAL1280CommonLogic.getBillToLocation(cMsg, glblCmpyCd, null, sellToCustCd);
                                billToCustExist = true;
                            }
                        }
                    }
                    ZYPEZDItemValueSetter.setValue(cMsg.destRtlWhCd, destWhCd);
                    NPAL1280CommonLogic.setDestWhInfo(cMsg, sMsg);
                    ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm, cMsg.destRtlSwhCd);
                } else {
                    // QC#29997
                    if (ZYPCommonFunc.hasValue(destWhCd) && (!"DS".equals(destWhCd) && !isManualDropShipWHCd(glblCmpyCd, destWhCd))) {
                        ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd, destWhCd);
                        ZYPEZDItemValueSetter.setValue(cMsg.xxLocNm, getRtlWhNm(glblCmpyCd, destWhCd));
                        NPAL1280CommonLogic.setShipToAddressFromS21Wh(cMsg, sMsg);
                        String sellToCustCd = NPAL1280CommonLogic.getBillToSellToByShipTo(cMsg.shipToCustCd.getValue(), glblCmpyCd);
                        if (ZYPCommonFunc.hasValue(sellToCustCd)) {
                            NPAL1280CommonLogic.getBillToLocation(cMsg, glblCmpyCd, null, sellToCustCd);
                            billToCustExist = true;
                        }
                    } else if (ZYPCommonFunc.hasValue(destWhCd)){
                        String sellToCustCd = NPAL1280CommonLogic.getBillToSellToByShipTo(destWhCd, glblCmpyCd);
                        if (ZYPCommonFunc.hasValue(sellToCustCd)) {
                            NPAL1280CommonLogic.getBillToLocation(cMsg, glblCmpyCd, null, sellToCustCd);
                            billToCustExist = true;
                        }
                    }
                }

                if (ZYPCommonFunc.hasValue(destWhCd) && ZYPCommonFunc.hasValue(preDestWhCd) && !destWhCd.equals(preDestWhCd)) {
                    isMultiWH = true;
                }

                // START 2022/11/18 M.Kikushima[QC#60605, MOD]
                if(!vndLine) {
                    lineCount++;
                    sMsg.A.setValidCount(lineCount);
                }
                //lineCount++;
                //sMsg.A.setValidCount(lineCount);
                // END 2022/11/18 M.Kikushima[QC#60605, MOD]
                if (ZYPCommonFunc.hasValue(destWhCd)) {
                    preDestWhCd = destWhCd;
                }
            }
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotAmt, xxTotAmt);

            // get Contact Person Info
            if (ZYPCommonFunc.hasValue(dsCpoConfigPk)) {
                S21SsmEZDResult contactResult = NPAL1280Query.getInstance().getContactPerson(glblCmpyCd, cMsg.trxRefNum.getValue(), dsCpoConfigPk);
                if (!(contactResult.isCodeNormal())) {
                    DS_CPO_CTAC_PSNTMsg tMsg2 = new DS_CPO_CTAC_PSNTMsg();
                    tMsg2.setSQLID("009");
                    ZYPEZDItemValueSetter.setValue(tMsg2.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(tMsg2.cpoOrdNum, cMsg.trxRefNum);

                    DS_CPO_CTAC_PSNTMsgArray resultList2 = (DS_CPO_CTAC_PSNTMsgArray) EZDTBLAccessor.findByCondition(tMsg2);
                    if (resultList2 != null && 0 < resultList2.getValidCount()) {
                        String fllPsnName = resultList2.no(0).ctacPsnFirstNm.getValue() + " " + resultList2.no(0).ctacPsnMidNm.getValue() + " " + resultList2.no(0).ctacPsnLastNm.getValue();
                        ZYPEZDItemValueSetter.setValue(cMsg.ctacPsnNm_HS, fllPsnName);
                    }
                } else {
                    List<Map<String, Object>> contactList = (List) contactResult.getResultObject();
                    if (contactList != null && 0 < contactList.size()) {
                        Map<String, Object> contactMap = contactList.get(0);
                        ZYPEZDItemValueSetter.setValue(cMsg.ctacPsnNm_HS, (String) contactMap.get(RS_CTAC_PERSON));
                    }
                }
            } else {
                DS_CPO_CTAC_PSNTMsg tMsg2 = new DS_CPO_CTAC_PSNTMsg();
                tMsg2.setSQLID("009");
                ZYPEZDItemValueSetter.setValue(tMsg2.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg2.cpoOrdNum, cMsg.trxRefNum);

                DS_CPO_CTAC_PSNTMsgArray resultList2 = (DS_CPO_CTAC_PSNTMsgArray) EZDTBLAccessor.findByCondition(tMsg2);
                if (resultList2 != null && 0 < resultList2.getValidCount()) {
                    String fllPsnName = resultList2.no(0).ctacPsnFirstNm.getValue() + " " + resultList2.no(0).ctacPsnMidNm.getValue() + " " + resultList2.no(0).ctacPsnLastNm.getValue();
                    ZYPEZDItemValueSetter.setValue(cMsg.ctacPsnNm_HS, fllPsnName);
                }
            }

            // get Bill To Location Info
            // QC#28962 mod start
            if (!billToCustExist) {
                getBillToLocation(cMsg, glblCmpyCd, billToCustCd, null);
            }
            // QC#28962 mod end

            // Copy from SMsg to cMsg
            int i = 0;
            // START 2022/11/18 M.Kikushima[QC#60605, MOD]
            //for (; i < copyAllList.size(); i++) {
            for (; i < sMsg.A.getValidCount(); i++) {
            // END 2022/11/18 M.Kikushima[QC#60605, MOD]
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }

            // QC#27846
            if (isMultiAddr) {
                cMsg.setMessageInfo(NPAM1626W);
            } else if(isMultiWH) {
                cMsg.setMessageInfo(NPAM1635W);
            }

            cMsg.A.setValidCount(i);
            // Set page number
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            // START 2022/11/18 M.Kikushima[QC#60605, MOD]
            //cMsg.xxPageShowOfNum.setValue(copyAllList.size());
            cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
            // END 2022/11/18 M.Kikushima[QC#60605, MOD]
        } else {
            cMsg.setMessageInfo(NMAM0038I);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            return;
        }
    }

    /**
     * find return to warehouse
     * @param cMsg NPAL1280CMsg
     * @param glblCmpyCd String
     * @param postCd String
     * @param shipToStCd String
     */
    private static void getReturnToWh(NPAL1280CMsg cMsg, String glblCmpyCd, String postCd, String shipToStCd) {
        S21SsmEZDResult ssmResult1 = NPAL1280Query.getInstance().getReturnToAddress(glblCmpyCd, shipToStCd, postCd, postCd);
        String rtrnShipToRtlWhXref = null;
        if (ssmResult1.isCodeNormal()) {
            List<Map<String, Object>> list = (List) ssmResult1.getResultObject();
            if (list != null && 0 < list.size()) {
                Map<String, Object> map = list.get(0);
                rtrnShipToRtlWhXref = (String) map.get(RS_RTRN_SHIP_TO_RTL_WH_CD);
            }
            S21SsmEZDResult ssmResult3 = NPAL1280Query.getInstance().getDefaultReturnToWh(glblCmpyCd, rtrnShipToRtlWhXref);
            if (ssmResult3.isCodeNormal()) {
                List<Map<String, Object>> list2 = (List) ssmResult3.getResultObject();
                if (list2 != null && 0 < list2.size()) {
                    Map<String, Object> map = list2.get(0);
                    ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_HC, (String) map.get(RS_RTL_WH_NM));
                    ZYPEZDItemValueSetter.setValue(cMsg.xxAllLineAddr_HC, (String) map.get(RS_XX_ALL_LINE_ADDR));
                    ZYPEZDItemValueSetter.setValue(cMsg.postCd_HC, (String) map.get(RS_POST_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.ctyAddr_HC, (String) map.get(RS_CTY_ADDR));
                    ZYPEZDItemValueSetter.setValue(cMsg.stCd_HC, (String) map.get(RS_ST_CD));
                }
            }
        } else {
            getDefaultReturnToWh(cMsg, glblCmpyCd);
        }
    }

    /**
     * find default return to warehouse
     * @param cMsg NPAL1280CMsg
     * @param glblCmpyCd String
     */
    private static void getDefaultReturnToWh(NPAL1280CMsg cMsg, String glblCmpyCd) {
        String rtrnShipToRtlWhXref = null;
        S21SsmEZDResult ssmResult2 = NPAL1280Query.getInstance().getDefaultReturnToAddress(glblCmpyCd, ASTARISK, ASTARISK, ASTARISK);
        if (ssmResult2.isCodeNormal()) {
            List<Map<String, Object>> list = (List) ssmResult2.getResultObject();
            if (list != null && 0 < list.size()) {
                Map<String, Object> map = list.get(0);
                rtrnShipToRtlWhXref = (String) map.get(RS_RTRN_SHIP_TO_RTL_WH_CD);
            }
        } else {
            cMsg.setMessageInfo(NPAM0076E, new String[] {CSA_RET_ADDR });
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            return;
        }
        S21SsmEZDResult ssmResult3 = NPAL1280Query.getInstance().getDefaultReturnToWh(glblCmpyCd, rtrnShipToRtlWhXref);
        if (ssmResult3.isCodeNormal()) {
            List<Map<String, Object>> list = (List) ssmResult3.getResultObject();
            if (list != null && 0 < list.size()) {
                Map<String, Object> map = list.get(0);
                ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_HC, (String) map.get(RS_RTL_WH_NM));
                ZYPEZDItemValueSetter.setValue(cMsg.xxAllLineAddr_HC, (String) map.get(RS_XX_ALL_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(cMsg.postCd_HC, (String) map.get(RS_POST_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.ctyAddr_HC, (String) map.get(RS_CTY_ADDR));
                ZYPEZDItemValueSetter.setValue(cMsg.stCd_HC, (String) map.get(RS_ST_CD));
            }
        }
    }

    /**
     * find bill to location
     * @param cMsg NPAL1280CMsg
     * @param glblCmpyCd String
     * @param sellToCustCd String
     */
    public static void getBillToLocation(NPAL1280CMsg cMsg, String glblCmpyCd, String billToCustCd, String sellToCustCd) {
        if (ZYPCommonFunc.hasValue(billToCustCd)) {
            S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().getBillToLocation(glblCmpyCd, billToCustCd);
            if (ssmResult.isCodeNormal()) {
                List<Map<String, Object>> list = (List) ssmResult.getResultObject();
                if (list != null && 0 < list.size()) {
                    Map<String, Object> map = list.get(0);
                    ZYPEZDItemValueSetter.setValue(cMsg.locNm_HB, (String) map.get(RS_LOC_NM));
                    ZYPEZDItemValueSetter.setValue(cMsg.addlLocNm_HB, (String) map.get(RS_ADDL_LOC_NM));
                    ZYPEZDItemValueSetter.setValue(cMsg.xxAllLineAddr_HB, (String) map.get(RS_XX_ALL_LINE_ADDR));
                    ZYPEZDItemValueSetter.setValue(cMsg.postCd_HB, (String) map.get(RS_POST_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.ctyAddr_HB, (String) map.get(RS_CTY_ADDR));
                    ZYPEZDItemValueSetter.setValue(cMsg.cntyNm_HB, (String) map.get(RS_CNTY_NM));
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToCntyNm_HB, (String) map.get(RS_SHIP_TO_CNTY_NM));
                    ZYPEZDItemValueSetter.setValue(cMsg.stCd_HB, (String) map.get(RS_ST_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.provNm_HB, (String) map.get(RS_PROV_NM));
                    ZYPEZDItemValueSetter.setValue(cMsg.ctryNm_HB, (String) map.get(RS_CTRY_NM));
                }
            }
        } else {
            if (ZYPCommonFunc.hasValue(sellToCustCd)) {
                NMZC610001PMsg pMsg = new NMZC610001PMsg();
                pMsg.glblCmpyCd.setValue(glblCmpyCd);
                pMsg.xxModeCd.setValue("04");
                pMsg.dsAcctNum_I1.setValue(sellToCustCd);
                NMZC610001 nMZC610001 = new NMZC610001();
                nMZC610001.execute(pMsg, ONBATCH_TYPE.ONLINE);

                for (int i = pMsg.DefaultBillShipList.getValidCount() - 1; i >= 0; i--) {
                    NMZC610001_DefaultBillShipListPMsg dpMsg = pMsg.DefaultBillShipList.no(i);
                    S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().getBillToLocation(glblCmpyCd, dpMsg.billToCustCd.getValue());
                    if (ssmResult.isCodeNormal()) {
                        List<Map<String, Object>> list = (List) ssmResult.getResultObject();
                        if (list != null && 0 < list.size()) {
                            Map<String, Object> map = list.get(0);
                            ZYPEZDItemValueSetter.setValue(cMsg.locNm_HB, (String) map.get(RS_LOC_NM));
                            ZYPEZDItemValueSetter.setValue(cMsg.addlLocNm_HB, (String) map.get(RS_ADDL_LOC_NM));
                            ZYPEZDItemValueSetter.setValue(cMsg.xxAllLineAddr_HB, (String) map.get(RS_XX_ALL_LINE_ADDR));
                            ZYPEZDItemValueSetter.setValue(cMsg.postCd_HB, (String) map.get(RS_POST_CD));
                            ZYPEZDItemValueSetter.setValue(cMsg.ctyAddr_HB, (String) map.get(RS_CTY_ADDR));
                            ZYPEZDItemValueSetter.setValue(cMsg.cntyNm_HB, (String) map.get(RS_CNTY_NM));
                            ZYPEZDItemValueSetter.setValue(cMsg.shipToCntyNm_HB, (String) map.get(RS_SHIP_TO_CNTY_NM));
                            ZYPEZDItemValueSetter.setValue(cMsg.stCd_HB, (String) map.get(RS_ST_CD));
                            ZYPEZDItemValueSetter.setValue(cMsg.provNm_HB, (String) map.get(RS_PROV_NM));
                            ZYPEZDItemValueSetter.setValue(cMsg.ctryNm_HB, (String) map.get(RS_CTRY_NM));
                            break;
                        }
                    }
                }
            }
        }
    }

    // Save Check Methods

    /**
     * chkDetailValues
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param i int
     * @return boolean true:OK
     */
    public static boolean chkDetailValues(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, int i) {
        //QC#14858 MOD START
        // Delete multiple Line Type Check.
        //String prchReqLineTypePre = "";
        //String prchReqLineType = sMsg.A.no(i).prchReqLineTpCd_A1.getValue();
        //QC#14858 MOD END
        if (!(ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).prntCmpySetMdseFlg_A1.getValue()))) {
            if (!(ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseCd_A1))) {
                sMsg.A.no(i).mdseCd_A1.setErrorInfo(1, ZZM9000E, new String[] {MDSE_CD_D });
                return false;
            }
            if (!(ZYPCommonFunc.hasValue(sMsg.A.no(i).prchReqLineTpCd_A1))) {
                sMsg.A.no(i).prchReqLineTpCd_A1.setErrorInfo(1, ZZM9000E, new String[] {LINE_TYPE_D });
                return false;
            }
            if (!(ZYPCommonFunc.hasValue(sMsg.A.no(i).prchReqDispQty_A1))) {
                sMsg.A.no(i).prchReqDispQty_A1.setErrorInfo(1, ZZM9000E, new String[] {ORDER_QTY_D });
                return false;
            }
        }
        if (PRCH_REQ_LINE_TP.EXPENSE.equals(sMsg.A.no(i).prchReqLineTpCd_A1.getValue()) || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(sMsg.A.no(i).prchReqLineTpCd_A1.getValue())) //
        {
            if (!(ZYPCommonFunc.hasValue(sMsg.A.no(i).xxScrItem130Txt_A1))) {
                sMsg.A.no(i).xxScrItem130Txt_A1.setErrorInfo(1, NPAM1489E, new String[] {CHARGE_ACCOUNT_D });
                return false;
            }
            String[] list = sMsg.A.no(i).xxScrItem130Txt_A1.getValue().split(Pattern.quote(DOT), -1);
            if (list.length < SEGMENT_TOKEN_LIST_SIZE) {
                sMsg.A.no(i).xxScrItem130Txt_A1.setErrorInfo(1, NPAM1193E, new String[] {CHARGE_ACCOUNT_D });
                return false;
            }
            if (!checkManualSegmentElementForSMsg(cMsg, sMsg, glblCmpyCd, i)) {
                return false;
            }
        }
        //QC#14858 MOD START
        //if (ZYPCommonFunc.hasValue(prchReqLineTypePre) && !prchReqLineTypePre.equals(prchReqLineType)) {
        //    sMsg.A.no(i).prchReqLineTpCd_A1.setErrorInfo(1, NPAM1490E, new String[] {LINE_TYPE_D });
        //    return false;
        //}
        //QC#14858 MOD END
        if (0 >= sMsg.A.no(i).prchReqDispQty_A1.getValueInt()) {
            sMsg.A.no(i).prchReqDispQty_A1.setErrorInfo(1, NPAM0046E, new String[] {ORDER_QTY_D });
            return false;
        }
        if (0 > sMsg.A.no(i).entDealNetUnitPrcAmt_A1.getValueInt()) {
            sMsg.A.no(i).entDealNetUnitPrcAmt_A1.setErrorInfo(1, NPAM0046E, new String[] {UNIT_PRICE_D });
            return false;
        }
        //QC#14858 MOD START
        //prchReqLineTypePre = prchReqLineType;
        //QC#14858 MOD END

        if (!PO_MDSE_CMPSN_TP.CHILD.equals(sMsg.A.no(i).poMdseCmpsnTpCd_A1.getValue())) {
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rqstRcvDt_A1)) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rqstRcvDt_A1, sMsg.rqstRcvDt);
            }
            // START 2023/02/03 T.Kuroda [QC#60966, ADD]
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rqstShipDt_A1)) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rqstShipDt_A1, sMsg.rqstShipDt);
            }
            // END   2023/02/03 T.Kuroda [QC#60966, ADD]
            if (!(PRCH_REQ_STS.CLOSED.equals(sMsg.prchReqStsCd.getValue())
                    || Arrays.asList(PRCH_REQ_LINE_STS.CANCELLED, PRCH_REQ_LINE_STS.CLOSED).contains(sMsg.A.no(i).prchReqLineStsCd_HD.getValue()))) {
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rqstRcvDt_A1)) {
                    String rqstRcvDt = sMsg.A.no(i).rqstRcvDt_A1.getValue();
                    String now = ZYPDateUtil.getSalesDate();
                    if (ZYPDateUtil.compare(rqstRcvDt, now) < 0) {
                        sMsg.A.no(i).rqstRcvDt_A1.setErrorInfo(1, NPAM0079E, new String[] {DATE_NEEDED });
                        return false;
                    }
                    // START 2019/03/20 T.Ogura [QC#30769,DEL]
//                    if (!(ZYPDateUtil.isBusinessDay(glblCmpyCd, rqstRcvDt))) {
//                        sMsg.A.no(i).rqstRcvDt_A1.setErrorInfo(1, NPAM0094E, new String[] {DATE_NEEDED });
//                        return false;
//                    }
                    // END   2019/03/20 T.Ogura [QC#30769,DEL]
                }
                // START 2023/02/03 T.Kuroda [QC#60966, ADD]
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rqstShipDt_A1)) {
                    String rqstShipDt = sMsg.A.no(i).rqstShipDt_A1.getValue();
                    String now = ZYPDateUtil.getSalesDate();
                    if (ZYPDateUtil.compare(rqstShipDt, now) < 0) {
                        sMsg.A.no(i).rqstShipDt_A1.setErrorInfo(1, NPAM0079E, new String[] {VENDOR_SHIP_DATE });
                        return false;
                    }
                }
                // END   2023/02/03 T.Kuroda [QC#60966, ADD]
            }
        }
        return true;
    }

    /**
     * check item number master
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param index int
     * @return boolean true:OK
     */
    public static boolean chkItemNumber(NPAL1280SMsg sMsg, String glblCmpyCd, int index) {
        ALL_MDSE_VTMsg allMdseV = new ALL_MDSE_VTMsg();
        allMdseV.setSQLID("003");
        allMdseV.setConditionValue(BIND_GLBL_CMPY_CD_01, glblCmpyCd);
        allMdseV.setConditionValue(BIND_MDSE_CD_01, sMsg.A.no(index).mdseCd_A1.getValue());
        ALL_MDSE_VTMsgArray allMdseVList = (ALL_MDSE_VTMsgArray) EZDTBLAccessor.findByCondition(allMdseV);
        if (!(allMdseVList != null && 0 < allMdseVList.getValidCount())) {
            sMsg.A.no(index).mdseCd_A1.setErrorInfo(1, NPAM1361E, new String[] {ITEM_D });
            return false;
        }

        // 2018/12/17 QC#29397 Add Start
        if (isOnlyCsaSetItem(glblCmpyCd, sMsg.A.no(index).mdseCd_A1.getValue())) {
            sMsg.A.no(index).mdseCd_A1.setErrorInfo(1, NPAM1639E);
            return false;
        }
        // 2018/12/17 QC#29397 Add End
        return true;
    }

    /**
     * check supplier master
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param index int
     * @return boolean true:OK
     */
    public static boolean chkSupplier(NPAL1280SMsg sMsg, String glblCmpyCd, int index) {
        String prntVndCd = sMsg.A.no(index).prntVndCd_A1.getValue();
        String prntVndNm = sMsg.A.no(index).prntVndNm_A1.getValue();
        Map<String, Object> map = null;

        // Assume user input Supplier from a popup.
        S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().chkPrntVndCd(glblCmpyCd, prntVndCd, prntVndNm);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> list = (List) ssmResult.getResultObject();
            if (list != null && 1 == list.size()) {
                // Found Supplier uniquely.
                map = list.get(0);
            }
        }
        if (map == null) {
            // Assume user input Supplier by hand.
            ssmResult = NPAL1280Query.getInstance().chkPrntVndCd(glblCmpyCd, null, prntVndNm);
            if (ssmResult.isCodeNormal()) {
                List<Map<String, Object>> list = (List) ssmResult.getResultObject();
                if (list != null) {
                    if (list.size() == 1) {
                        // Found Supplier uniquely.
                        map = list.get(0);
                    } else if (list.size() > 1) {
                        // Found Supplier multiply...
                        sMsg.A.no(index).prntVndNm_A1.setErrorInfo(1, NPAM1589E, new String[] {SUPPLIER_D });
                    }
                }
                if (list != null && 1 == list.size()) {
                    map = list.get(0);
                }
            }
        }

        if (map == null) {
            // Supplier not found...
            sMsg.A.no(index).prntVndNm_A1.setErrorInfo(1, NPAM1361E, new String[] {SUPPLIER_D });
            return false;
        }

        // Found Supplier uniquely.
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prntVndCd_A1, (String) map.get(RS_PRNT_VND_CD));

        return true;
    }

    /**
     * check supplier site master. Mod QC#31087
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param index int
     * @param submitFlg boolean
     * @return boolean false:Check NG
     */
    public static boolean chkSupplierSite(NPAL1280SMsg sMsg, String glblCmpyCd, int index, boolean submitFlg) {
        String vndCd = sMsg.A.no(index).vndCd_A1.getValue();
        String locNm = sMsg.A.no(index).locNm_A1.getValue();
        Map<String, Object> map = null;

        // Assume user input Supplier Site from a popup.
        S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().chkVndCd(glblCmpyCd, vndCd, locNm);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> list = (List) ssmResult.getResultObject();
            if (list != null && 1 == list.size()) {
                // Found Supplier Site uniquely.
                map = list.get(0);
            }
        }

        if (map == null) {
            // Assume user input Supplier Site by hand.
            ssmResult = NPAL1280Query.getInstance().chkVndCd(glblCmpyCd, null, locNm);
            if (ssmResult.isCodeNormal()) {
                List<Map<String, Object>> list = (List) ssmResult.getResultObject();
                if (list != null) {
                    if (list.size() == 1) {
                        // Found Supplier Site uniquely.
                        map = list.get(0);
                    } else if (list.size() > 1) {
                        // Found Supplier Site multiply...
                        sMsg.A.no(index).locNm_A1.setErrorInfo(1, NPAM1589E, new String[] {SITE_D });
                        return false;
                    }
                }
            }
        }

        if (map == null) {
            // Supplier Site not found...
            sMsg.A.no(index).locNm_A1.setErrorInfo(1, NPAM1361E, new String[] {SITE_D });
            return false;
        }

        // Found Supplier Site uniquely.
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).vndCd_A1, (String) map.get(RS_VND_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).vndPk_A1, (BigDecimal) map.get(RS_VND_PK));

        // QC#31087
        if (submitFlg && ZYPCommonFunc.hasValue(sMsg.destRtlWhCd)) {
            
            RTL_WHTMsg rwTMsg = new RTL_WHTMsg();
            ZYPEZDItemValueSetter.setValue(rwTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(rwTMsg.rtlWhCd, sMsg.destRtlWhCd);

            rwTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(rwTMsg);

            if (rwTMsg == null) {
                return true;
            }
            
            RCV_ASN_VNDTMsg inMsg = new RCV_ASN_VNDTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.rcvAsnVndCd, sMsg.A.no(index).vndCd_A1);
            inMsg = (RCV_ASN_VNDTMsg) EZDTBLAccessor.findByKey(inMsg);

            if (inMsg == null) {
                return true;
            } else if (INVTY_OWNR.GMD.equals(rwTMsg.invtyOwnrCd.getValue()) && ZYPConstant.FLG_ON_Y.equals(inMsg.poSendFlg.getValue())){
                sMsg.A.no(index).locNm_A1.setErrorInfo(1, NPAM1643E, null);
                sMsg.A.no(index).prntVndNm_A1.setErrorInfo(1, NPAM1643E, null);
                return false;
            }
        }

        return true;
    }

    /**
     * check carrier master
     * @param cMsg NPAL1280CMsg
     * @param glblCmpyCd String
     * @return boolean true:OK
     */
    public static boolean chkCarrier(NPAL1280CMsg cMsg, String glblCmpyCd) {
        OTBD_CARR_VTMsg otbdCarrV = new OTBD_CARR_VTMsg();
        otbdCarrV.setSQLID("001");
        otbdCarrV.setConditionValue(BIND_GLBL_CMPY_CD_01, glblCmpyCd);
        otbdCarrV.setConditionValue(BIND_CARR_CD_01, cMsg.carrCd_HF.getValue());
        OTBD_CARR_VTMsgArray otbdCarrVList = (OTBD_CARR_VTMsgArray) EZDTBLAccessor.findByCondition(otbdCarrV);
        if (!(otbdCarrVList != null && 0 < otbdCarrVList.getValidCount())) {
            cMsg.carrCd_HF.setErrorInfo(1, NPAM1361E, new String[] {CARRIER_D });
            return false;
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.locNm_HF, otbdCarrVList.no(0).carrNm);
        }

        return true;
    }

    /**
     * check po vnd master
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param index int
     * @return boolean true:OK
     */
    public static boolean chkPoVnd(NPAL1280SMsg sMsg, String glblCmpyCd, int index) {
        S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().chkPoVnd(glblCmpyCd, sMsg.A.no(index).prntVndCd_A1.getValue(), sMsg.A.no(index).vndCd_A1.getValue());
        if (!(ssmResult.isCodeNormal())) {
            sMsg.A.no(index).prntVndNm_A1.setErrorInfo(1, NPAM1363E, new String[] {SUPPLIER_D, SITE_D });
            sMsg.A.no(index).locNm_A1.setErrorInfo(1, NPAM1363E, new String[] {SUPPLIER_D, SITE_D });
            return false;
        }
        return true;
    }

    /**
     * check asl detail info
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param salesDate String
     * @param index int
     * @return boolean true:OK
     */
    public static boolean chkAslDtl(NPAL1280SMsg sMsg, String glblCmpyCd, String salesDate, int index) {

        // QC#28939 Add check.
        if (isExpenceTextItem(sMsg, glblCmpyCd, index)) {
            // Set uom.
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqDsplUomCd_A1, PKG_UOM.EACH);
            // Skip ASL check.
            return true;
        }

        S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().findAslDtl(glblCmpyCd, sMsg.A.no(index).vndCd_A1.getValue(), sMsg.A.no(index).mdseCd_A1.getValue(), salesDate);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> list = (List) ssmResult.getResultObject();
            if (list != null && 0 < list.size()) {
                Map<String, Object> map = list.get(0);
                // QC#53271 Mod Start
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).aslMdseCd_A1, (String) map.get(RS_SPLY_ITEM_NUM));
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(index).aslMdseCd_A1) ||
                		(!PRCH_REQ_LINE_TP.EXPENSE.equals(sMsg.A.no(index).prchReqLineTpCd_A1.getValue()) &&
                		!PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(sMsg.A.no(index).prchReqLineTpCd_A1.getValue()))) {
                	ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).aslMdseCd_A1, (String) map.get(RS_SPLY_ITEM_NUM));
                }
                // QC#53271 Mod End
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqDsplUomCd_A1, (String) map.get(RS_VND_UOM_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).aslDtlPk_A1, (BigDecimal) map.get(RS_ASL_DTL_PK));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).aslUnitPrcAmt_A1, (BigDecimal) map.get(RS_UNIT_PRC_AMT));

                if (!PO_MDSE_CMPSN_TP.CHILD.equals(sMsg.A.no(index).poMdseCmpsnTpCd_A1.getValue())) {
                    BigDecimal unitPrcAmt = (BigDecimal) map.get(RS_UNIT_PRC_AMT);
                    if (!(ZYPCommonFunc.hasValue(sMsg.A.no(index).entDealNetUnitPrcAmt_A1))) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).entDealNetUnitPrcAmt_A1, unitPrcAmt);
                    }
                    if (ZYPCommonFunc.hasValue(sMsg.A.no(index).entDealNetUnitPrcAmt_A1) //
                            && BigDecimal.ZERO.compareTo(sMsg.A.no(index).entDealNetUnitPrcAmt_A1.getValue()) == 0) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).entDealNetUnitPrcAmt_A1, unitPrcAmt);
                    }

                    BigDecimal detailUnitPrc = sMsg.A.no(index).entDealNetUnitPrcAmt_A1.getValue();
                    if (detailUnitPrc.compareTo(BigDecimal.ZERO) < 0 || unitPrcAmt.compareTo(BigDecimal.ZERO) < 0) {
                        sMsg.A.no(index).entDealNetUnitPrcAmt_A1.setErrorInfo(1, NPAM0046E, new String[] {UNIT_PRICE_D });
                        return false;
                    }
                }
            }
        } else {
            sMsg.A.no(index).mdseCd_A1.setErrorInfo(1, NPAM1364E, new String[] {ITEM_D });
            return false;
        }
        if (!PO_MDSE_CMPSN_TP.CHILD.equals(sMsg.A.no(index).poMdseCmpsnTpCd_A1.getValue())) {
            if (sMsg.A.no(index).entDealNetUnitPrcAmt_A1.getValue().compareTo(BigDecimal.ZERO) < 0) {
                sMsg.A.no(index).entDealNetUnitPrcAmt_A1.setErrorInfo(1, NPAM0046E, new String[] {UNIT_PRICE_D });
                return false;
            }
        }
        return true;
    }

    /**
     * check shpg svc level releation
     * @param cMsg NPAL1280CMsg
     * @param glblCmpyCd String
     * @return boolean true:OK
     */
    public static boolean chkShpSvcLvlCarrRelation(NPAL1280CMsg cMsg, String glblCmpyCd) {
        if (ZYPCommonFunc.hasValue(cMsg.shpgSvcLvlCd_SL) && ZYPCommonFunc.hasValue(cMsg.carrCd_HF)) {
            SHPG_SVC_LVL_CARR_RELNTMsg tMsg = new SHPG_SVC_LVL_CARR_RELNTMsg();
            tMsg.setSQLID("001");
            tMsg.setConditionValue(BIND_GLBL_CMPY_CD_01, glblCmpyCd);
            tMsg.setConditionValue(BIND_SHPG_SVC_LVL_CD_01, cMsg.shpgSvcLvlCd_SL.getValue());
            tMsg.setConditionValue(BIND_CARR_CD_01, cMsg.carrCd_HF.getValue());
            SHPG_SVC_LVL_CARR_RELNTMsgArray sslcrList = (SHPG_SVC_LVL_CARR_RELNTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
            if (!(sslcrList != null && 0 < sslcrList.getValidCount())) {
                cMsg.shpgSvcLvlCd_SL.setErrorInfo(2, NPAM1636W, new String[] {});
                // QC#29155 Update.
                cMsg.carrCd_HF.setErrorInfo(2, NPAM1636W, new String[] {});
                return false;
            } 

            // QC#29155 Delete chkFrtCondSvcLvlRelation method.
        }
        return true;
    }

    /**
     * check frt condition relation
     * @param cMsg NPAL1280CMsg
     * @param glblCmpyCd String
     * @param shpgSvcLvlCd String
     * @param carrSvcLvlCd String
     * @return boolean true:OK
     */
    private static boolean chkFrtCondSvcLvlRelation(NPAL1280CMsg cMsg, String glblCmpyCd, String frtCondCd, String shpgSvcLvlCd, String carrSvcLvlCd) {
        if (ZYPCommonFunc.hasValue(cMsg.frtCondCd_SL)) {
            S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().chkFrtCondSvcLvlReln(glblCmpyCd, frtCondCd, shpgSvcLvlCd, carrSvcLvlCd);
            if (ssmResult.isCodeNormal()) {
                List<Map<String, Object>> list = (List) ssmResult.getResultObject();

                if (list.isEmpty()) {
                    cMsg.shpgSvcLvlCd_SL.setErrorInfo(1, NPAM1366E, new String[] {});
                    cMsg.frtCondCd_SL.setErrorInfo(1, NPAM1366E, new String[] {});
                    return false;
                }
            } else {
                cMsg.shpgSvcLvlCd_SL.setErrorInfo(1, NPAM1366E, new String[] {});
                cMsg.frtCondCd_SL.setErrorInfo(1, NPAM1366E, new String[] {});
                return false;
            }
        }
        return true;
    }

    /**
     * chkCarrierAccountRelation
     * @param cMsg NPAL1280CMsg
     * @param frtCondCd String
     * @return true:OK false:NG
     */
    public static boolean chkCarrierAccountRelation(NPAL1280CMsg cMsg, String frtCondCd) {
        if (FRT_COND.COLLECT.equals(frtCondCd) && !(ZYPCommonFunc.hasValue(cMsg.carrAcctNum_HF))) {
            cMsg.carrAcctNum_HF.setErrorInfo(1, NPAM1367E, new String[] {});
            return false;
        } else {
            return true;
        }
    }

    /**
     * Call NPZC1290 API
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param salesDate String
     * @param index int
     * @param cMsg NPAL1280CMsg
     * @return boolean true:OK
     */
    public static boolean convNPZC1290Call(NPAL1280SMsg sMsg, String glblCmpyCd, String salesDate, int index, NPAL1280CMsg cMsg) {

        // QC#28939 Add check.
        if (isExpenceTextItem(sMsg, glblCmpyCd, index)) {
            // Set Qty.
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqQty_A1, sMsg.A.no(index).prchReqDispQty_A1);
            // Skip.
            return true;
        }

        // QC#20506 Add
        if (ZYPCommonFunc.hasValue(cMsg.poQlfyCd) && PO_QLFY.DROPSHIP.equals(cMsg.poQlfyCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqQty_A1, sMsg.A.no(index).prchReqDispQty_A1);
            return true;
        }

        NPZC129001PMsg npzc129001PMsg = new NPZC129001PMsg();
        ZYPEZDItemValueSetter.setValue(npzc129001PMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(npzc129001PMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(npzc129001PMsg.prntVndCd, sMsg.A.no(index).prntVndCd_A1);
        ZYPEZDItemValueSetter.setValue(npzc129001PMsg.vndCd, sMsg.A.no(index).vndCd_A1);
        ZYPEZDItemValueSetter.setValue(npzc129001PMsg.mdseCd, sMsg.A.no(index).mdseCd_A1);
        ZYPEZDItemValueSetter.setValue(npzc129001PMsg.poDispQty, sMsg.A.no(index).prchReqDispQty_A1);
        NPZC129001 npzc129001 = new NPZC129001();
        npzc129001.execute(npzc129001PMsg, ONBATCH_TYPE.ONLINE);

        // 02/10/2017 QC#5060 Mod.
//        if (npzc129001PMsg.xxWrnSkipFlg.getValue().equals(ZYPConstant.FLG_OFF_N) && npzc129001PMsg.xxMsgIdList.getValidCount() > 0) {
        if (npzc129001PMsg.xxMsgIdList.getValidCount() > 0) {
//            cMsg.setMessageInfo(npzc129001PMsg.xxMsgIdList.no(0).xxMsgId.getValue(), new String[] {});
            sMsg.A.no(index).prchReqDispQty_A1.setErrorInfo(1, npzc129001PMsg.xxMsgIdList.no(0).xxMsgId.getValue(), new String[] {});
            return false;
        } else {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqQty_A1, npzc129001PMsg.poQty_O1);
        }
        return true;
    }

    /**
     * conversion unit price
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param salesDate String
     * @param index int
     */
    public static void convUnitPrice(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, String salesDate, int index) {
        if (ZYPCommonFunc.hasValue(sMsg.A.no(index).entDealNetUnitPrcAmt_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(index).prchReqDispQty_A1)) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxUnitPrc_A1, sMsg.A.no(index).entDealNetUnitPrcAmt_A1.getValue().multiply(sMsg.A.no(index).prchReqDispQty_A1.getValue()));
        } else {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxUnitPrc_A1, BigDecimal.ZERO);
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxTotAmt)) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotAmt, cMsg.xxTotAmt.getValue().add(sMsg.A.no(index).xxUnitPrc_A1.getValue()));
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotAmt, sMsg.A.no(index).xxUnitPrc_A1.getValue());
        }

        VNDTMsg tMsg = new VNDTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.vndPk, sMsg.A.no(index).vndPk_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        VNDTMsg result = (VNDTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (result != null) {
            String dealCcyCd = result.dealCcyCd.getValue();
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).ccyCd_A1, dealCcyCd);
            GLBL_CMPYTMsg glblTMsg = new GLBL_CMPYTMsg();
            ZYPEZDItemValueSetter.setValue(glblTMsg.glblCmpyCd, glblCmpyCd);
            GLBL_CMPYTMsg glblResult = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(glblTMsg);
            if (glblResult != null) {
                String stdCcyCd = glblResult.stdCcyCd.getValue();
                if (dealCcyCd.equals(stdCcyCd)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).entFuncNetUnitPrcAmt_A1, sMsg.A.no(index).xxUnitPrc_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).exchRate_A1, EXCH_RATE_100);
                } else {
                    NPXC001001CurrencyConversion api = new NPXC001001CurrencyConversion();
                    HashMap<String, Object> exchRateData = null;
                    NPXC001001CurrencyConversionBean bean = api.convertCurrency(glblCmpyCd, dealCcyCd, sMsg.A.no(index).entDealNetUnitPrcAmt_A1.getValue(), salesDate, exchRateData);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).entFuncNetUnitPrcAmt_A1, bean.getFuncAmt());
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).exchRate_A1, bean.getExchRate());
                }
            }

        }
    }

    /**
     * find PRCH_REQ Table record
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param cMsg NPAL1280CMsg
     * @return PRCH_REQTMsg
     */
    public static PRCH_REQTMsg findPrchReqByKeyForUpdate(NPAL1280SMsg sMsg, String glblCmpyCd, NPAL1280CMsg cMsg) {

        PRCH_REQTMsg prTMsg = new PRCH_REQTMsg();
        ZYPEZDItemValueSetter.setValue(prTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prTMsg.prchReqNum, cMsg.prchReqNum);

        prTMsg = (PRCH_REQTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(prTMsg);
        if (prTMsg == null) {
            return null;
        }

        String findEzUpTime = cMsg.ezUpTime_AH.getValue();
        String findEzUpTimeZone = cMsg.ezUpTimeZone_AH.getValue();
        String currentEzUpTime = prTMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = prTMsg.ezUpTimeZone.getValue();

        if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            cMsg.setMessageInfo(NPAM0006E);
            return null;
        }
        return prTMsg;
    }

    /**
     * find PRCH_REQ_DTL Table record
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param index int
     * @param cMsg NPAL1280CMsg
     * @return PRCH_REQ_DTLTMsg
     */
    public static PRCH_REQ_DTLTMsg findPrchReqDtlByKeyForUpdate(NPAL1280SMsg sMsg, String glblCmpyCd, int index, NPAL1280CMsg cMsg) {

        PRCH_REQ_DTLTMsg prdTMsg = new PRCH_REQ_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(prdTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prdTMsg.prchReqNum, cMsg.prchReqNum);
        ZYPEZDItemValueSetter.setValue(prdTMsg.prchReqLineNum, sMsg.A.no(index).prchReqLineNum_A1);
        ZYPEZDItemValueSetter.setValue(prdTMsg.prchReqLineSubNum, sMsg.A.no(index).prchReqLineSubNum_A1);
        ZYPEZDItemValueSetter.setValue(prdTMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);

        prdTMsg = (PRCH_REQ_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(prdTMsg);
        if (prdTMsg == null) {
            return null;
        }
        return prdTMsg;
    }

    /**
     * PR Update API New parameter set
     * @param cMsg NPAL1280CMsg
     * @param prdTMsgNewList List<NPAL1280_ASMsg>
     * @param glblCmpyCd String
     * @param salesDate String
     * @param loginUserId String
     * @param prchReqApvlStsCd String
     * @param prTMsg PRCH_REQTMsg 
     * @return NPZC103001PMsg
     */
    public static NPZC103001PMsg savePrUpdateNew(NPAL1280CMsg cMsg, List<NPAL1280_ASMsg> prdTMsgNewList, String glblCmpyCd, String salesDate, String loginUserId, String prchReqApvlStsCd, PRCH_REQTMsg prTMsg) {
        NPZC103001PMsg params = new NPZC103001PMsg();

        // for Header
        ZYPEZDItemValueSetter.setValue(params.eventId, ZYPConstant.FLG_ON_1);
        ZYPEZDItemValueSetter.setValue(params.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(params.procDt, salesDate);

        if (prTMsg != null) {
            // add line
            ZYPEZDItemValueSetter.setValue(params.xxModeCd, UPDATE_MODE);
            ZYPEZDItemValueSetter.setValue(params.prchReqNum, prTMsg.prchReqNum);
        } else {
            // new pr
            params.prchReqNum.clear();
            ZYPEZDItemValueSetter.setValue(params.xxModeCd, ZYPConstant.FLG_ON_1);
        }

        ZYPEZDItemValueSetter.setValue(params.prchReqRecTpCd, PRCH_REQ_REC_TP.PO_REQUISITION);
        ZYPEZDItemValueSetter.setValue(params.prchReqTpCd, cMsg.prchReqTpCd_SL);
        ZYPEZDItemValueSetter.setValue(params.prchReqCratByPsnCd, loginUserId);
        ZYPEZDItemValueSetter.setValue(params.prchReqRqstByPsnCd, cMsg.prchReqRqstByPsnCd);
        ZYPEZDItemValueSetter.setValue(params.prchReqSrcTpCd, PRCH_REQ_REC_TP.PO_REQUISITION);
        ZYPEZDItemValueSetter.setValue(params.prchGrpCd, cMsg.prchGrpCd_SL);
        ZYPEZDItemValueSetter.setValue(params.prchReqApvlStsCd, prchReqApvlStsCd);
        params.fsrNum.clear();
        params.svcTaskNum.clear();
        params.cpoOrdNum.clear();
        params.custIssPoNum.clear();
        params.custIssPoDt.clear();
        params.cpoOrdTpCd.clear();
        params.rqstTechTocCd.clear();
        params.shipToCustCd.clear();
        params.shipToLocNm.clear();
        params.shipToAddlLocNm.clear();
        params.shipToFirstLineAddr.clear();
        params.shipToScdLineAddr.clear();
        params.shipToThirdLineAddr.clear();
        params.shipToFrthLineAddr.clear();
        params.shipToFirstRefCmntTxt.clear();
        params.shipToScdRefCmntTxt.clear();
        params.shipToCtyAddr.clear();
        params.shipToStCd.clear();
        params.shipToProvNm.clear();
        params.shipToCntyNm.clear();
        params.shipToPostCd.clear();
        params.shipToCtryCd.clear();
        params.billToCustCd.clear();
        params.sellToCustCd.clear();
        params.lineBizTpCd.clear();
        params.ctacPsnNm.clear();
        params.adminPsnCd.clear();
        ZYPEZDItemValueSetter.setValue(params.prchReqCmntTxt, cMsg.prchReqCmntTxt);
        params.delyAddlCmntTxt.clear();
        params.rcvAddlCmntTxt.clear();
        // START 2023/02/16 T.Kuroda [QC#60966, ADD]
        ZYPEZDItemValueSetter.setValue(params.rqstShipDt, cMsg.rqstShipDt);
        // END   2023/02/16 T.Kuroda [QC#60966, ADD]

        // QC#26990 Add Start: QC#27809 Update.
        // for Ship to Customer Address for MD
        int lineAddrLength = cMsg.xxAllLineAddr_HS.getValue().length();
        if (lineAddrLength < 61) {
            ZYPEZDItemValueSetter.setValue(params.shipToFirstLineAddr, cMsg.xxAllLineAddr_HS.getValue());
        } else if (lineAddrLength < 121) {
            ZYPEZDItemValueSetter.setValue(params.shipToFirstLineAddr, cMsg.xxAllLineAddr_HS.getValue().substring(0, 60));
            ZYPEZDItemValueSetter.setValue(params.shipToScdLineAddr, cMsg.xxAllLineAddr_HS.getValue().substring(60));
        } else if (lineAddrLength < 181) {
            ZYPEZDItemValueSetter.setValue(params.shipToFirstLineAddr, cMsg.xxAllLineAddr_HS.getValue().substring(0, 60));
            ZYPEZDItemValueSetter.setValue(params.shipToScdLineAddr, cMsg.xxAllLineAddr_HS.getValue().substring(60, 120));
            ZYPEZDItemValueSetter.setValue(params.shipToThirdLineAddr, cMsg.xxAllLineAddr_HS.getValue().substring(120));
        } else {
            ZYPEZDItemValueSetter.setValue(params.shipToFirstLineAddr, cMsg.xxAllLineAddr_HS.getValue().substring(0, 60));
            ZYPEZDItemValueSetter.setValue(params.shipToScdLineAddr, cMsg.xxAllLineAddr_HS.getValue().substring(60, 120));
            ZYPEZDItemValueSetter.setValue(params.shipToThirdLineAddr, cMsg.xxAllLineAddr_HS.getValue().substring(120, 180));
            ZYPEZDItemValueSetter.setValue(params.shipToFrthLineAddr, cMsg.xxAllLineAddr_HS.getValue().substring(180));
        }
        ZYPEZDItemValueSetter.setValue(params.shipToCustCd, cMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(params.shipToLocNm, cMsg.xxLocNm);
        ZYPEZDItemValueSetter.setValue(params.shipToAddlLocNm, cMsg.shipToAddlLocNm_HS);
        // ZYPEZDItemValueSetter.setValue(params.shipToFirstLineAddr,
        // cMsg.shipToFirstLineAddr_HS);
        // ZYPEZDItemValueSetter.setValue(params.shipToScdLineAddr,
        // cMsg.shipToScdLineAddr_HS);
        // ZYPEZDItemValueSetter.setValue(params.shipToThirdLineAddr,
        // cMsg.shipToThirdLineAddr_HS);
        // ZYPEZDItemValueSetter.setValue(params.shipToFrthLineAddr,
        // cMsg.shipToFrthLineAddr_HS);
        ZYPEZDItemValueSetter.setValue(params.shipToCtyAddr, cMsg.shipToCtyAddr_HS);
        ZYPEZDItemValueSetter.setValue(params.shipToStCd, cMsg.shipToStCd_HS);
        ZYPEZDItemValueSetter.setValue(params.shipToProvNm, cMsg.shipToProvNm_HS);
        ZYPEZDItemValueSetter.setValue(params.shipToCntyNm, cMsg.shipToCntyNm_HS);
        ZYPEZDItemValueSetter.setValue(params.shipToPostCd, cMsg.shipToPostCd_HS);
        ZYPEZDItemValueSetter.setValue(params.shipToCtryCd, cMsg.shipToCtryCd_HS);

        ZYPEZDItemValueSetter.setValue(params.ctacPsnNm, cMsg.ctacPsnNm_HS);
        // QC#26990 Add End

        // Technician warehouse:PC / Not technician warehouse: null
        if (isTechnicianWh(glblCmpyCd, cMsg.destRtlWhCd.getValue())) {
            // set PO_QLFY_CD 'PC'
            ZYPEZDItemValueSetter.setValue(params.poQlfyCd //
                    , ZYPCodeDataUtil.getVarCharConstValue(TECH_INSRC_PO_QLFY_CD, glblCmpyCd));
        } else {
            params.poQlfyCd.clear();
        }

        params.batErrMsgTxt.clear();
        // for Detail
        int addRowCount = 0;
        for (int i = 0; i < prdTMsgNewList.size(); i++) {
            params.prchReqInfo.no(i).prchReqIntfcPk.clear();
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).prchReqLineNum, prdTMsgNewList.get(i).prchReqLineNum_A1);
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).prchReqLineSubNum, prdTMsgNewList.get(i).prchReqLineSubNum_A1);
            params.prchReqInfo.no(i).xxDtlLineNum.clear();
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).prchReqLineTpCd, prdTMsgNewList.get(i).prchReqLineTpCd_A1);
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).rqstRcvDt, prdTMsgNewList.get(i).rqstRcvDt_A1);
            // START 2023/02/03 T.Kuroda [QC#60966, ADD]
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).rqstShipDt, prdTMsgNewList.get(i).rqstShipDt_A1);
            // END   2023/02/03 T.Kuroda [QC#60966, ADD]
            if (ZYPConstant.FLG_OFF_0.equals(cMsg.xxTpCd_SL.getValue())) {
                // Pulldown AM Select
                if (ZYPCommonFunc.hasValue(cMsg.xxDtTm)) {
                    ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).rqstRcvTm, cMsg.xxDtTm.getValue().replaceAll(":", ""));
                } else {
                    ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).rqstRcvTm, cMsg.xxDtTm);
                }
            } else if (ZYPCommonFunc.hasValue(cMsg.xxDtTm)) {
                // Pulldown PM Select
                ZYPEZDItemValueSetter.setValue(cMsg.xxDtTm, cMsg.xxDtTm.getValue().replaceAll(":", ""));
                String hh = cMsg.xxDtTm.getValue().substring(0, IDX_2);
                String mi = cMsg.xxDtTm.getValue().substring(IDX_2, IDX_4);
                // QC#51575
                int hhInt  = 0;
                if (Integer.parseInt(hh) == IDX_12) {
                    hhInt = Integer.parseInt(hh);
                } else {
                    hhInt = Integer.parseInt(hh) + IDX_12;
                }
                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).rqstRcvTm, Integer.valueOf(hhInt).toString() + mi);
            } else {
                params.prchReqInfo.no(i).rqstRcvTm.clear();
            }
            params.prchReqInfo.no(i).shipFromSoNum.clear();
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).poSchdRelDt, prdTMsgNewList.get(i).poSchdRelDt_A1);
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).frtCondCd, cMsg.frtCondCd_SL);
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).shpgSvcLvlCd, cMsg.shpgSvcLvlCd_SL);
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).carrCd, cMsg.carrCd_HF);
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).carrAcctNum, cMsg.carrAcctNum_HF);
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).procrTpCd, ZYPConstant.FLG_ON_1);
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).destInvtyLocCd, cMsg.destRtlWhCd.getValue() + cMsg.destRtlSwhCd.getValue());
            params.prchReqInfo.no(i).srcInvtyLocCd.clear();
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).prntVndCd, prdTMsgNewList.get(i).prntVndCd_A1);
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).vndCd, prdTMsgNewList.get(i).vndCd_A1);
            params.prchReqInfo.no(i).vndInvtyLocCd.clear();
            params.prchReqInfo.no(i).vndRtrnRsnCd.clear();
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).poMdseCmpsnTpCd, prdTMsgNewList.get(i).poMdseCmpsnTpCd_A1);
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).mdseCd, prdTMsgNewList.get(i).mdseCd_A1);
            // QC#28939 Update.
            if (isTextitem(glblCmpyCd, params.prchReqInfo.no(i).mdseCd.getValue())) {
                // setup item description.
                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).mdseDescShortTxt, prdTMsgNewList.get(i).mdseDescShortTxt_A1);

            // QC#53271 Add Start
            } else if (ZYPCommonFunc.hasValue(prdTMsgNewList.get(i).mdseDescShortTxt_A1) &&
            		PRCH_REQ_LINE_TP.EXPENSE.equals(prdTMsgNewList.get(i).prchReqLineTpCd_A1.getValue()) ||
            		PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(prdTMsgNewList.get(i).prchReqLineTpCd_A1.getValue())) {
            	ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).mdseDescShortTxt, prdTMsgNewList.get(i).mdseDescShortTxt_A1);
            }
            // QC#53271 Mod End
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).aslMdseCd, prdTMsgNewList.get(i).aslMdseCd_A1);
            params.prchReqInfo.no(i).svcConfigMstrPk.clear();
            params.prchReqInfo.no(i).configTpCd.clear();
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).prchReqQty, prdTMsgNewList.get(i).prchReqQty_A1);
            params.prchReqInfo.no(i).ordQty.clear();
            params.prchReqInfo.no(i).custUomCd.clear();
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).prchReqDispQty, prdTMsgNewList.get(i).prchReqDispQty_A1);
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).prchReqDsplUomCd, prdTMsgNewList.get(i).prchReqDsplUomCd_A1);
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).ropQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).minOrdQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).incrOrdQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).maxInvtyQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).curInvtyQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).curInvtyAvalQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).schdInbdQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).schdOtbdQty, BigDecimal.ZERO);
            params.prchReqInfo.no(i).proNum.clear();
            params.prchReqInfo.no(i).fromStkStsCd.clear();
            params.prchReqInfo.no(i).toStkStsCd.clear();
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).aslDtlPk, prdTMsgNewList.get(i).aslDtlPk_A1);
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).aslUnitPrcAmt, prdTMsgNewList.get(i).aslUnitPrcAmt_A1);
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).entDealNetUnitPrcAmt, prdTMsgNewList.get(i).entDealNetUnitPrcAmt_A1);
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).entFuncNetUnitPrcAmt, prdTMsgNewList.get(i).entFuncNetUnitPrcAmt_A1);
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).ccyCd, prdTMsgNewList.get(i).ccyCd_A1);
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).exchRate, prdTMsgNewList.get(i).exchRate_A1);
            if (PRCH_REQ_LINE_TP.EXPENSE.equals(prdTMsgNewList.get(i).prchReqLineTpCd_A1.getValue()) || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(prdTMsgNewList.get(i).prchReqLineTpCd_A1.getValue())) {
                if (ZYPCommonFunc.hasValue(prdTMsgNewList.get(i).xxScrItem130Txt_A1)) {
                    List<String> tokenList = tokenListSplit(prdTMsgNewList.get(i).xxScrItem130Txt_A1.getValue(), glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).coaCmpyCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
                    ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).coaAfflCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
                    ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).coaBrCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
                    ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).coaChCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
                    ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).coaCcCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
                    ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).coaAcctCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
                    ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).coaProdCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
                    ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).coaProjCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
                    ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).coaExtnCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));
                }
            }
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).relRqstToPoOrdNum, prdTMsgNewList.get(i).relRqstToPoOrdNum_A1);
            params.prchReqInfo.no(i).poOrdNum.clear();
            params.prchReqInfo.no(i).poOrdDtlLineNum.clear();
            params.prchReqInfo.no(i).invtyOrdNum.clear();
            params.prchReqInfo.no(i).invtyOrdLineNum.clear();
            params.prchReqInfo.no(i).wrkOrdNum.clear();
            params.prchReqInfo.no(i).wrkOrdDtlLineNum.clear();
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).trxRefNum, cMsg.trxRefNum);
            params.prchReqInfo.no(i).trxRefLineNum.clear();
            params.prchReqInfo.no(i).trxRefLineSubNum.clear();
            params.prchReqInfo.no(i).shpgPlnNum.clear();
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).prchReqLineCmntTxt, prdTMsgNewList.get(i).prchReqLineCmntTxt_A1);
            // QC#21209
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).spclInstnCmntTxt, prdTMsgNewList.get(i).spclInstnCmntTxt_A1);

            // QC#53483
            if (ZYPCommonFunc.hasValue(cMsg.trxRefNum) && ZYPCommonFunc.hasValue(prdTMsgNewList.get(i).trxRefLineNum_A1)) {
                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).trxRefLineNum, prdTMsgNewList.get(i).trxRefLineNum_A1);
                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).trxRefLineSubNum, prdTMsgNewList.get(i).trxRefLineSubNum_A1);
            } else {
                params.prchReqInfo.no(i).trxRefNum.clear();
            }

            addRowCount++;
        }
        params.prchReqInfo.setValidCount(addRowCount);
        return params;
    }

    /**
     * compare
     * @param item1 EZDSStringItem
     * @param item2 EZDTStringItem
     * @return boolean
     */
    private static boolean compare(EZDSStringItem item1, EZDTStringItem item2) {
        if (ZYPCommonFunc.hasValue(item1) && ZYPCommonFunc.hasValue(item2)) {
            if (item1.getValue().equals(item2.getValue())) {
                return true;
            } else {
                return false;
            }
        }
        if (!ZYPCommonFunc.hasValue(item1) && !ZYPCommonFunc.hasValue(item2)) {
            return true;
        }
        return false;
    }

    /**
     * compare
     * @param item1 String
     * @param item2 EZDTStringItem
     * @return boolean
     */
    private static boolean compare(String item1, EZDTStringItem item2) {
        if (ZYPCommonFunc.hasValue(item1) && ZYPCommonFunc.hasValue(item2)) {
            if (item1.equals(item2.getValue())) {
                return true;
            } else {
                return false;
            }
        }
        if (!ZYPCommonFunc.hasValue(item1) && !ZYPCommonFunc.hasValue(item2)) {
            return true;
        }
        return false;
    }

    /**
     * compare
     * @param item1 EZDCStringItem
     * @param item2 EZDTStringItem
     * @return boolean
     */
    private static boolean compare(EZDCStringItem item1, EZDTStringItem item2) {
        if (ZYPCommonFunc.hasValue(item1) && ZYPCommonFunc.hasValue(item2)) {
            if (item1.getValue().equals(item2.getValue())) {
                return true;
            } else {
                return false;
            }
        }
        if (!ZYPCommonFunc.hasValue(item1) && !ZYPCommonFunc.hasValue(item2)) {
            return true;
        }
        return false;
    }

    /**
     * compare
     * @param item1 EZDSBigDecimalItem
     * @param item2 EZDTBigDecimalItem
     * @return boolean
     */
    private static boolean compare(EZDSBigDecimalItem item1, EZDTBigDecimalItem item2) {
        if (ZYPCommonFunc.hasValue(item1) && ZYPCommonFunc.hasValue(item2)) {
            if (item1.getValue().equals(item2.getValue())) {
                return true;
            } else {
                return false;
            }
        }
        if (!ZYPCommonFunc.hasValue(item1) && !ZYPCommonFunc.hasValue(item2)) {
            return true;
        }
        return false;
    }

    /**
     * compare
     * @param item1 EZDSDateItem
     * @param item2 EZDTDateItem
     * @return boolean
     */
    private static boolean compare(EZDSDateItem item1, EZDTDateItem item2) {
        if (ZYPCommonFunc.hasValue(item1) && ZYPCommonFunc.hasValue(item2)) {
            if (item1.getValue().equals(item2.getValue())) {
                return true;
            } else {
                return false;
            }
        }
        if (!ZYPCommonFunc.hasValue(item1) && !ZYPCommonFunc.hasValue(item2)) {
            return true;
        }
        return false;
    }

    /**
     * PR Update API update params set
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param salesDate String
     * @param prTMsg PRCH_REQTMsg
     * @param prdTMsgList List<PRCH_REQ_DTLTMsg>
     * @param prchReqApvlStsCd String
     * @param loginUserId String
     * @return NPZC103001PMsg
     */
    public static NPZC103001PMsg savePrUpdateUpdate(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, String salesDate, PRCH_REQTMsg prTMsg, List<PRCH_REQ_DTLTMsg> prdTMsgList, String prchReqApvlStsCd, String loginUserId) {
        NPZC103001PMsg params = new NPZC103001PMsg();
        // for Header
        ZYPEZDItemValueSetter.setValue(params.xxModeCd, UPDATE_MODE);
        ZYPEZDItemValueSetter.setValue(params.eventId, ZYPConstant.FLG_ON_1);
        ZYPEZDItemValueSetter.setValue(params.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(params.procDt, salesDate);
        ZYPEZDItemValueSetter.setValue(params.prchReqNum, cMsg.prchReqNum);
        ZYPEZDItemValueSetter.setValue(params.prchReqRqstByPsnCd, cMsg.prchReqRqstByPsnCd);
        ZYPEZDItemValueSetter.setValue(params.prchReqApvlStsCd, prchReqApvlStsCd);
        ZYPEZDItemValueSetter.setValue(params.fsrNum, prTMsg.fsrNum);
        ZYPEZDItemValueSetter.setValue(params.svcTaskNum, prTMsg.svcTaskNum);
        ZYPEZDItemValueSetter.setValue(params.cpoOrdNum, prTMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(params.custIssPoNum, prTMsg.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(params.custIssPoDt, prTMsg.custIssPoDt);
        ZYPEZDItemValueSetter.setValue(params.cpoOrdTpCd, prTMsg.cpoOrdTpCd);
        ZYPEZDItemValueSetter.setValue(params.rqstTechTocCd, prTMsg.rqstTechTocCd);
        ZYPEZDItemValueSetter.setValue(params.shipToCustCd, prTMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(params.shipToLocNm, prTMsg.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(params.shipToAddlLocNm, prTMsg.shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(params.shipToFirstLineAddr, prTMsg.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(params.shipToScdLineAddr, prTMsg.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(params.shipToThirdLineAddr, prTMsg.shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(params.shipToFrthLineAddr, prTMsg.shipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(params.shipToFirstRefCmntTxt, prTMsg.shipToFirstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(params.shipToScdRefCmntTxt, prTMsg.shipToScdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(params.shipToCtyAddr, prTMsg.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(params.shipToStCd, prTMsg.shipToStCd);
        ZYPEZDItemValueSetter.setValue(params.shipToProvNm, prTMsg.shipToProvNm);
        ZYPEZDItemValueSetter.setValue(params.shipToCntyNm, prTMsg.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(params.shipToPostCd, prTMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(params.shipToCtryCd, prTMsg.shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(params.billToCustCd, prTMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(params.sellToCustCd, prTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(params.lineBizTpCd, prTMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(params.ctacPsnNm, prTMsg.ctacPsnNm);
        ZYPEZDItemValueSetter.setValue(params.adminPsnCd, prTMsg.adminPsnCd);
        // QC#30444
        ZYPEZDItemValueSetter.setValue(params.prchGrpCd, cMsg.prchGrpCd_SL);
        // START 2023/02/16 T.Kuroda [QC#60966, ADD]
        ZYPEZDItemValueSetter.setValue(params.rqstShipDt, cMsg.rqstShipDt);
        // END   2023/02/16 T.Kuroda [QC#60966, ADD]
        //QC#26990 Add Start
        // for Ship to Customer Address for MD
        if(NPAL1280CommonLogic.isManualDropShipWHCd(glblCmpyCd, cMsg.destRtlWhCd.getValue())){
            int lineAddrLength = cMsg.xxAllLineAddr_HS.getValue().length();
            if (lineAddrLength < 61) {
                ZYPEZDItemValueSetter.setValue(params.shipToFirstLineAddr, cMsg.xxAllLineAddr_HS.getValue());
            } else if (lineAddrLength < 121) {
                ZYPEZDItemValueSetter.setValue(params.shipToFirstLineAddr, cMsg.xxAllLineAddr_HS.getValue().substring(0, 60));
                ZYPEZDItemValueSetter.setValue(params.shipToScdLineAddr, cMsg.xxAllLineAddr_HS.getValue().substring(60));
            } else if (lineAddrLength < 181) {
                ZYPEZDItemValueSetter.setValue(params.shipToFirstLineAddr, cMsg.xxAllLineAddr_HS.getValue().substring(0, 60));
                ZYPEZDItemValueSetter.setValue(params.shipToScdLineAddr, cMsg.xxAllLineAddr_HS.getValue().substring(60, 120));
                ZYPEZDItemValueSetter.setValue(params.shipToThirdLineAddr, cMsg.xxAllLineAddr_HS.getValue().substring(120));
            } else {
                ZYPEZDItemValueSetter.setValue(params.shipToFirstLineAddr, cMsg.xxAllLineAddr_HS.getValue().substring(0, 60));
                ZYPEZDItemValueSetter.setValue(params.shipToScdLineAddr, cMsg.xxAllLineAddr_HS.getValue().substring(60, 120));
                ZYPEZDItemValueSetter.setValue(params.shipToThirdLineAddr, cMsg.xxAllLineAddr_HS.getValue().substring(120,180));
                ZYPEZDItemValueSetter.setValue(params.shipToFrthLineAddr, cMsg.xxAllLineAddr_HS.getValue().substring(180));
            }
            ZYPEZDItemValueSetter.setValue(params.shipToCustCd, cMsg.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(params.shipToLocNm, cMsg.xxLocNm);
            ZYPEZDItemValueSetter.setValue(params.shipToAddlLocNm, cMsg.shipToAddlLocNm_HS);
//            ZYPEZDItemValueSetter.setValue(params.shipToFirstLineAddr, cMsg.shipToFirstLineAddr_HS);
//            ZYPEZDItemValueSetter.setValue(params.shipToScdLineAddr, cMsg.shipToScdLineAddr_HS);
//            ZYPEZDItemValueSetter.setValue(params.shipToThirdLineAddr, cMsg.shipToThirdLineAddr_HS);
//            ZYPEZDItemValueSetter.setValue(params.shipToFrthLineAddr, cMsg.shipToFrthLineAddr_HS);
            ZYPEZDItemValueSetter.setValue(params.shipToCtyAddr, cMsg.shipToCtyAddr_HS);
            ZYPEZDItemValueSetter.setValue(params.shipToStCd, cMsg.shipToStCd_HS);
            ZYPEZDItemValueSetter.setValue(params.shipToProvNm, cMsg.shipToProvNm_HS);
            ZYPEZDItemValueSetter.setValue(params.shipToCntyNm, cMsg.shipToCntyNm_HS);
            ZYPEZDItemValueSetter.setValue(params.shipToPostCd, cMsg.shipToPostCd_HS);
            ZYPEZDItemValueSetter.setValue(params.shipToCtryCd, cMsg.shipToCtryCd_HS);
        }
        ZYPEZDItemValueSetter.setValue(params.ctacPsnNm, cMsg.ctacPsnNm_HS);
        //QC#26990 Add End

        if (!compare(cMsg.prchReqCmntTxt, prTMsg.prchReqCmntTxt)) {
            ZYPEZDItemValueSetter.setValue(params.prchReqCmntTxt, cMsg.prchReqCmntTxt);
        } else {
            ZYPEZDItemValueSetter.setValue(params.prchReqCmntTxt, prTMsg.prchReqCmntTxt);
        }
        ZYPEZDItemValueSetter.setValue(params.delyAddlCmntTxt, prTMsg.delyAddlCmntTxt);
        ZYPEZDItemValueSetter.setValue(params.rcvAddlCmntTxt, prTMsg.rcvAddlCmntTxt);
        ZYPEZDItemValueSetter.setValue(params.poQlfyCd, prTMsg.poQlfyCd);
        ZYPEZDItemValueSetter.setValue(params.batErrMsgTxt, prTMsg.batErrMsgTxt);

        // QC#17075 START
        // QC#20439 Mod START
//        if (PRCH_REQ_SRC_TP.WH_PLANNING.equals(prTMsg.prchReqSrcTpCd.getValue())) {
//            ZYPEZDItemValueSetter.setValue(params.prchReqCratByPsnCd, loginUserId);
//        }
        if (PRCH_REQ_APVL_STS.ENTERED.equals(cMsg.prchReqApvlStsCd.getValue())) {
        	ZYPEZDItemValueSetter.setValue(params.prchReqCratByPsnCd, loginUserId);
        }

        // QC#20439 Mod END
        // QC#17075 END

        // for Detail
        int addRowCount = 0;
        for (PRCH_REQ_DTLTMsg prdTMsg : prdTMsgList) {
            boolean updateFlg = false;
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                if (sMsg.A.no(i).prchReqLineNum_A1 != null && prdTMsg.prchReqLineNum.getValue().equals(sMsg.A.no(i).prchReqLineNum_A1.getValue()) && prdTMsg.prchReqLineSubNum.getValue().equals(sMsg.A.no(i).prchReqLineSubNum_A1.getValue())) {
                    if (prdTMsg == null) {
                        // add new line
                        params.prchReqInfo.no(addRowCount).prchReqIntfcPk.clear();
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).prchReqLineNum, sMsg.A.no(i).prchReqLineNum_A1);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).prchReqLineSubNum, sMsg.A.no(i).prchReqLineSubNum_A1);
                        params.prchReqInfo.no(addRowCount).xxDtlLineNum.clear();
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).prchReqLineTpCd, sMsg.A.no(i).prchReqLineTpCd_A1);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).rqstRcvDt, sMsg.A.no(i).rqstRcvDt_A1);
                        // START 2023/02/03 T.Kuroda [QC#60966, ADD]
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).rqstShipDt, sMsg.A.no(i).rqstShipDt_A1);
                        // END   2023/02/03 T.Kuroda [QC#60966, ADD]
                        if (ZYPConstant.FLG_OFF_0.equals(cMsg.xxTpCd_SL.getValue())) {
                            if (ZYPCommonFunc.hasValue(cMsg.xxDtTm)) {
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).rqstRcvTm, cMsg.xxDtTm.getValue().replaceAll(":", ""));
                            } else {
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).rqstRcvTm, cMsg.xxDtTm);
                            }
                        } else if (ZYPCommonFunc.hasValue(cMsg.xxDtTm)) {
                            // AM/PM pulldown PM Select
                            ZYPEZDItemValueSetter.setValue(cMsg.xxDtTm, cMsg.xxDtTm.getValue().replaceAll(":", ""));
                            String hh = cMsg.xxDtTm.getValue().substring(0, IDX_2);
                            String mi = cMsg.xxDtTm.getValue().substring(IDX_2, IDX_4);
                            int hhInt = Integer.parseInt(hh) + IDX_12;
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).rqstRcvTm, Integer.valueOf(hhInt).toString() + mi);
                        } else {
                            params.prchReqInfo.no(addRowCount).rqstRcvTm.clear();
                        }
                        params.prchReqInfo.no(addRowCount).shipFromSoNum.clear();
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).poSchdRelDt, sMsg.A.no(i).poSchdRelDt_A1);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).frtCondCd, cMsg.frtCondCd_SL);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).shpgSvcLvlCd, cMsg.shpgSvcLvlCd_SL);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).carrCd, cMsg.carrCd_HF);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).carrAcctNum, cMsg.carrAcctNum_HF);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).procrTpCd, ZYPConstant.FLG_ON_1);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).destInvtyLocCd, cMsg.destRtlWhCd.getValue() + cMsg.destRtlSwhCd.getValue());
                        params.prchReqInfo.no(addRowCount).srcInvtyLocCd.clear();
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).prntVndCd, sMsg.A.no(i).prntVndCd_A1);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).vndCd, sMsg.A.no(i).vndCd_A1);
                        params.prchReqInfo.no(addRowCount).vndInvtyLocCd.clear();
                        params.prchReqInfo.no(addRowCount).vndRtrnRsnCd.clear();
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).poMdseCmpsnTpCd, sMsg.A.no(i).poMdseCmpsnTpCd_A1);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).mdseCd, sMsg.A.no(i).mdseCd_A1);
                        // QC#28939 Update.
                        if(isTextitem(glblCmpyCd, params.prchReqInfo.no(addRowCount).mdseCd.getValue())){
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).mdseDescShortTxt, sMsg.A.no(i).mdseDescShortTxt_A1);
                        // QC#53271 Add Start
                        } else if (ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseDescShortTxt_A1) &&
                        		(PRCH_REQ_LINE_TP.EXPENSE.equals(sMsg.A.no(i).prchReqLineTpCd_A1.getValue()) ||
                        		PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(sMsg.A.no(i).prchReqLineTpCd_A1.getValue()))) {
                        	ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).mdseDescShortTxt, sMsg.A.no(i).mdseDescShortTxt_A1);
                        }
                        // QC#53271 Add End
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).aslMdseCd, sMsg.A.no(i).aslMdseCd_A1);
                        params.prchReqInfo.no(addRowCount).svcConfigMstrPk.clear();
                        params.prchReqInfo.no(addRowCount).configTpCd.clear();
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).prchReqQty, sMsg.A.no(i).prchReqQty_A1);
                        // QC#52756 Start
                        String prchReqNum = "";
                        String prchReqLineNum = "";
                        BigDecimal prchReqLineSubNum = null;
                        if (ZYPCommonFunc.hasValue(cMsg.prchReqNum)) {
                        	prchReqNum = cMsg.prchReqNum.getValue();
                        }
                        if (ZYPCommonFunc.hasValue(sMsg.A.no(i).prchReqLineNum_A1)) {
                        	prchReqLineNum = sMsg.A.no(i).prchReqLineNum_A1.getValue();
                        }
                        if (ZYPCommonFunc.hasValue(sMsg.A.no(i).prchReqLineSubNum_A1)) {
                        	prchReqLineSubNum = sMsg.A.no(i).prchReqLineSubNum_A1.getValue();
                        }
                        PRCH_REQ_DTLTMsg dtlTMsg = getPrchReqDtlTMsg(glblCmpyCd, prchReqNum, prchReqLineNum, prchReqLineSubNum);
                        if (dtlTMsg != null) {
                        	ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).prchReqRelQty, dtlTMsg.prchReqRelQty);
                        }
                        // QC#52756 End

                        params.prchReqInfo.no(addRowCount).ordQty.clear();
                        params.prchReqInfo.no(addRowCount).custUomCd.clear();
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).prchReqDispQty, sMsg.A.no(i).prchReqDispQty_A1);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).prchReqDsplUomCd, sMsg.A.no(i).prchReqDsplUomCd_A1);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).ropQty, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).minOrdQty, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).incrOrdQty, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).maxInvtyQty, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).curInvtyQty, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).curInvtyAvalQty, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).schdInbdQty, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).schdOtbdQty, BigDecimal.ZERO);
                        params.prchReqInfo.no(addRowCount).proNum.clear();
                        params.prchReqInfo.no(addRowCount).fromStkStsCd.clear();
                        params.prchReqInfo.no(addRowCount).toStkStsCd.clear();
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).aslDtlPk, sMsg.A.no(i).aslDtlPk_A1);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).aslUnitPrcAmt, sMsg.A.no(i).aslUnitPrcAmt_A1);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).entDealNetUnitPrcAmt, sMsg.A.no(i).entDealNetUnitPrcAmt_A1);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).entFuncNetUnitPrcAmt, sMsg.A.no(i).entFuncNetUnitPrcAmt_A1);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).ccyCd, sMsg.A.no(i).ccyCd_A1);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).exchRate, sMsg.A.no(i).exchRate_A1);
                        if (PRCH_REQ_LINE_TP.EXPENSE.equals(sMsg.A.no(i).prchReqLineTpCd_A1.getValue()) || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(sMsg.A.no(i).prchReqLineTpCd_A1.getValue())) {
                            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).xxScrItem130Txt_A1)) {
                                List<String> tokenList = tokenListSplit(sMsg.A.no(i).xxScrItem130Txt_A1.getValue(), glblCmpyCd);
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).coaCmpyCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).coaAfflCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).coaBrCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).coaChCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).coaCcCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).coaAcctCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).coaProdCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).coaProjCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).coaExtnCd, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));
                            }
                        }
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).relRqstToPoOrdNum, sMsg.A.no(i).relRqstToPoOrdNum_A1);
                        params.prchReqInfo.no(addRowCount).poOrdNum.clear();
                        params.prchReqInfo.no(addRowCount).poOrdDtlLineNum.clear();
                        params.prchReqInfo.no(addRowCount).invtyOrdNum.clear();
                        params.prchReqInfo.no(addRowCount).invtyOrdLineNum.clear();
                        params.prchReqInfo.no(addRowCount).wrkOrdNum.clear();
                        params.prchReqInfo.no(addRowCount).wrkOrdDtlLineNum.clear();
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).trxRefNum, cMsg.trxRefNum);
                        params.prchReqInfo.no(addRowCount).trxRefLineNum.clear();
                        params.prchReqInfo.no(addRowCount).trxRefLineSubNum.clear();
                        params.prchReqInfo.no(addRowCount).shpgPlnNum.clear();
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).prchReqLineCmntTxt, sMsg.A.no(i).prchReqLineCmntTxt_A1);
                        // QC#21209
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).spclInstnCmntTxt, sMsg.A.no(i).spclInstnCmntTxt_A1);
                        // QC#53483
                        if (ZYPCommonFunc.hasValue(cMsg.trxRefNum) && ZYPCommonFunc.hasValue(sMsg.A.no(i).trxRefLineNum_A1)) {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).trxRefLineNum, sMsg.A.no(i).trxRefLineNum_A1);
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).trxRefLineSubNum, sMsg.A.no(i).trxRefLineSubNum_A1);
                        } else {
                            params.prchReqInfo.no(addRowCount).trxRefNum.clear();
                        }

                        updateFlg = true;
                    } else {
                        // update line
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).prchReqIntfcPk, prdTMsg.prchReqIntfcPk);
                        if (!compare(sMsg.A.no(i).prchReqLineNum_A1, prdTMsg.prchReqLineNum)) {
                            updateFlg = true;
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).prchReqLineNum, sMsg.A.no(i).prchReqLineNum_A1);
                        } else {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).prchReqLineNum, prdTMsg.prchReqLineNum);
                        }
                        if (!compare(sMsg.A.no(i).prchReqLineSubNum_A1, prdTMsg.prchReqLineSubNum)) {
                            updateFlg = true;
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).prchReqLineSubNum, sMsg.A.no(i).prchReqLineSubNum_A1);
                        } else {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).prchReqLineSubNum, prdTMsg.prchReqLineSubNum);
                        }
                        params.prchReqInfo.no(addRowCount).xxDtlLineNum.clear();
                        if (!compare(sMsg.A.no(i).rqstRcvDt_A1, prdTMsg.rqstRcvDt)) {
                            updateFlg = true;
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).rqstRcvDt, sMsg.A.no(i).rqstRcvDt_A1);
                        } else {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).rqstRcvDt, prdTMsg.rqstRcvDt);
                        }
                        // START 2023/02/03 T.Kuroda [QC#60966, ADD]
                        if (!compare(sMsg.A.no(i).rqstShipDt_A1, prdTMsg.rqstShipDt)) {
                            updateFlg = true;
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).rqstShipDt, sMsg.A.no(i).rqstShipDt_A1);
                        } else {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).rqstShipDt, prdTMsg.rqstShipDt);
                        }
                        // END   2023/02/03 T.Kuroda [QC#60966, ADD]

                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).prchReqLineTpCd, prdTMsg.prchReqLineTpCd);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).mdseCd, prdTMsg.mdseCd);
                        // QC#28939 Update.
                        if(isTextitem(glblCmpyCd, params.prchReqInfo.no(addRowCount).mdseCd.getValue())){
                            if(!compare(sMsg.A.no(i).mdseDescShortTxt_A1, prdTMsg.mdseDescShortTxt)){
                                updateFlg = true;
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).mdseDescShortTxt, sMsg.A.no(i).mdseDescShortTxt_A1);
                            } else {
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).mdseDescShortTxt, prdTMsg.mdseDescShortTxt);
                            }
                        // QC#53271 Add Start
                        } else if (ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseDescShortTxt_A1) &&
                        		(PRCH_REQ_LINE_TP.EXPENSE.equals(sMsg.A.no(i).prchReqLineTpCd_A1.getValue()) ||
                        		PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(sMsg.A.no(i).prchReqLineTpCd_A1.getValue()))) {
                        	if(!compare(sMsg.A.no(i).mdseDescShortTxt_A1, prdTMsg.mdseDescShortTxt)){
                                updateFlg = true;
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).mdseDescShortTxt, sMsg.A.no(i).mdseDescShortTxt_A1);
                            } else {
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).mdseDescShortTxt, prdTMsg.mdseDescShortTxt);
                            }
                        }
                        // QC#53271 Add End

                        if (ZYPConstant.FLG_OFF_0.equals(cMsg.xxTpCd_SL.getValue())) {
                            // AM/PM pulldown AM Select
                            if (!compare(cMsg.xxDtTm, prdTMsg.rqstRcvTm)) {
                                updateFlg = true;
                                if (ZYPCommonFunc.hasValue(cMsg.xxDtTm)) {
                                    ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).rqstRcvTm, cMsg.xxDtTm.getValue().replaceAll(":", ""));
                                } else {
                                    ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).rqstRcvTm, cMsg.xxDtTm);
                                }
                            } else {
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).rqstRcvTm, prdTMsg.rqstRcvTm);
                            }
                        } else if (ZYPCommonFunc.hasValue(cMsg.xxDtTm)) {
                            // AM/PM pulldown PM Select
                            ZYPEZDItemValueSetter.setValue(cMsg.xxDtTm, cMsg.xxDtTm.getValue().replaceAll(":", ""));
                            String hh = cMsg.xxDtTm.getValue().substring(0, IDX_2);
                            String mi = cMsg.xxDtTm.getValue().substring(IDX_2, IDX_4);
                            // QC#51575
                            int hhInt = 0;
                            if (Integer.parseInt(hh) == IDX_12) {
                                hhInt = IDX_12;
                            } else {
                                hhInt = Integer.parseInt(hh) + IDX_12;
                            }
                            if (!compare(Integer.valueOf(hhInt).toString() + mi, prdTMsg.rqstRcvTm)) {
                                updateFlg = true;
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).rqstRcvTm, Integer.valueOf(hhInt).toString() + mi);
                            } else {
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).rqstRcvTm, prdTMsg.rqstRcvTm);
                            }
                        } else {
                            params.prchReqInfo.no(addRowCount).rqstRcvTm.clear();
                        }
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).shipFromSoNum, prdTMsg.shipFromSoNum);
                        if (!compare(sMsg.A.no(i).poSchdRelDt_A1, prdTMsg.poSchdRelDt)) {
                            updateFlg = true;
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).poSchdRelDt, sMsg.A.no(i).poSchdRelDt_A1);
                        } else {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).poSchdRelDt, prdTMsg.poSchdRelDt);
                        }
                        if (!compare(cMsg.frtCondCd_SL, prdTMsg.frtCondCd)) {
                            updateFlg = true;
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).frtCondCd, cMsg.frtCondCd_SL);
                        } else {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).frtCondCd, prdTMsg.frtCondCd);
                        }
                        if (!compare(cMsg.shpgSvcLvlCd_SL, prdTMsg.shpgSvcLvlCd)) {
                            updateFlg = true;
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).shpgSvcLvlCd, cMsg.shpgSvcLvlCd_SL);
                        } else {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).shpgSvcLvlCd, prdTMsg.shpgSvcLvlCd);
                        }
                        if (!compare(cMsg.carrCd_HF, prdTMsg.carrCd)) {
                            updateFlg = true;
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).carrCd, cMsg.carrCd_HF);
                        } else {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).carrCd, prdTMsg.carrCd);
                        }
                        if (!compare(cMsg.carrAcctNum_HF, prdTMsg.carrAcctNum)) {
                            updateFlg = true;
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).carrAcctNum, cMsg.carrAcctNum_HF);
                        } else {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).carrAcctNum, prdTMsg.carrAcctNum);
                        }
                        if (!compare(cMsg.carrAcctNum_HF, prdTMsg.carrAcctNum)) {
                            updateFlg = true;
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).carrAcctNum, cMsg.carrAcctNum_HF);
                        } else {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).carrAcctNum, prdTMsg.carrAcctNum);
                        }
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).procrTpCd, prdTMsg.procrTpCd);

                        String destInvtyLocCd = cMsg.destRtlWhCd.getValue() + cMsg.destRtlSwhCd.getValue();
                        if (!compare(destInvtyLocCd, prdTMsg.destInvtyLocCd)) {
                            updateFlg = true;
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).destInvtyLocCd, cMsg.destRtlWhCd.getValue() + cMsg.destRtlSwhCd.getValue());
                        } else {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).destInvtyLocCd, prdTMsg.destInvtyLocCd);
                        }

                        if (!compare(sMsg.A.no(i).prntVndCd_A1, prdTMsg.prntVndCd)) {
                            updateFlg = true;
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).prntVndCd, sMsg.A.no(i).prntVndCd_A1);
                        } else {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).prntVndCd, prdTMsg.prntVndCd);
                        }
                        if (!compare(sMsg.A.no(i).vndCd_A1, prdTMsg.vndCd)) {
                            updateFlg = true;
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).vndCd, sMsg.A.no(i).vndCd_A1);
                        } else {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).vndCd, prdTMsg.vndCd);
                        }
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).vndInvtyLocCd, prdTMsg.vndInvtyLocCd);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).vndRtrnRsnCd, prdTMsg.vndRtrnRsnCd);
                        if (!compare(sMsg.A.no(i).poMdseCmpsnTpCd_A1, prdTMsg.poMdseCmpsnTpCd)) {
                            updateFlg = true;
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).poMdseCmpsnTpCd, sMsg.A.no(i).poMdseCmpsnTpCd_A1);
                        } else {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).poMdseCmpsnTpCd, prdTMsg.poMdseCmpsnTpCd);
                        }
                        if (!compare(sMsg.A.no(i).aslMdseCd_A1, prdTMsg.aslMdseCd)) {
                            updateFlg = true;
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).aslMdseCd, sMsg.A.no(i).aslMdseCd_A1);
                        } else {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).aslMdseCd, prdTMsg.aslMdseCd);
                        }
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).svcConfigMstrPk, prdTMsg.svcConfigMstrPk);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).configTpCd, prdTMsg.configTpCd);

                        if (!compare(sMsg.A.no(i).prchReqQty_A1, prdTMsg.prchReqQty)) {
                            updateFlg = true;
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).prchReqQty, sMsg.A.no(i).prchReqQty_A1);
                        } else {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).prchReqQty, prdTMsg.prchReqQty);
                        }
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).ordQty, prdTMsg.ordQty);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).custUomCd, prdTMsg.custUomCd);
                        if (!compare(sMsg.A.no(i).prchReqDispQty_A1, prdTMsg.prchReqDispQty)) {
                            updateFlg = true;
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).prchReqDispQty, sMsg.A.no(i).prchReqDispQty_A1);
                        } else {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).prchReqDispQty, prdTMsg.prchReqDispQty);
                        }
                        if (!compare(sMsg.A.no(i).prchReqDsplUomCd_A1, prdTMsg.prchReqDsplUomCd)) {
                            updateFlg = true;
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).prchReqDsplUomCd, sMsg.A.no(i).prchReqDsplUomCd_A1);
                        } else {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).prchReqDsplUomCd, prdTMsg.prchReqDsplUomCd);
                        }
                        // QC#52756 Start
                        String prchReqNum = "";
                        String prchReqLineNum = "";
                        BigDecimal prchReqLineSubNum = null;
                        if (ZYPCommonFunc.hasValue(cMsg.prchReqNum)) {
                        	prchReqNum = cMsg.prchReqNum.getValue();
                        }
                        if (ZYPCommonFunc.hasValue(sMsg.A.no(i).prchReqLineNum_A1)) {
                        	prchReqLineNum = sMsg.A.no(i).prchReqLineNum_A1.getValue();
                        }
                        if (ZYPCommonFunc.hasValue(sMsg.A.no(i).prchReqLineSubNum_A1)) {
                        	prchReqLineSubNum = sMsg.A.no(i).prchReqLineSubNum_A1.getValue();
                        }
                        PRCH_REQ_DTLTMsg dtlTMsg = getPrchReqDtlTMsg(glblCmpyCd, prchReqNum, prchReqLineNum, prchReqLineSubNum);
                        if (dtlTMsg != null) {
                        	ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).prchReqRelQty, dtlTMsg.prchReqRelQty);
                        }
                        // QC#52756 End
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).ropQty, prdTMsg.ropQty);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).minOrdQty, prdTMsg.minOrdQty);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).incrOrdQty, prdTMsg.incrOrdQty);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).maxInvtyQty, prdTMsg.maxInvtyQty);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).curInvtyQty, prdTMsg.curInvtyQty);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).curInvtyAvalQty, prdTMsg.curInvtyAvalQty);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).schdInbdQty, prdTMsg.schdInbdQty);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).schdOtbdQty, prdTMsg.schdOtbdQty);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).proNum, prdTMsg.proNum);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).fromStkStsCd, prdTMsg.fromStkStsCd);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).toStkStsCd, prdTMsg.toStkStsCd);
                        if (!compare(sMsg.A.no(i).aslDtlPk_A1, prdTMsg.aslDtlPk)) {
                            updateFlg = true;
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).aslDtlPk, sMsg.A.no(i).aslDtlPk_A1);
                        } else {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).aslDtlPk, prdTMsg.aslDtlPk);
                        }
                        if (!compare(sMsg.A.no(i).aslUnitPrcAmt_A1, prdTMsg.aslUnitPrcAmt)) {
                            updateFlg = true;
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).aslUnitPrcAmt, sMsg.A.no(i).aslUnitPrcAmt_A1);
                        } else {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).aslUnitPrcAmt, prdTMsg.aslUnitPrcAmt);
                        }
                        if (!compare(sMsg.A.no(i).entDealNetUnitPrcAmt_A1, prdTMsg.entDealNetUnitPrcAmt)) {
                            updateFlg = true;
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).entDealNetUnitPrcAmt, sMsg.A.no(i).entDealNetUnitPrcAmt_A1);
                        } else {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).entDealNetUnitPrcAmt, prdTMsg.entDealNetUnitPrcAmt);
                        }
                        if (!compare(sMsg.A.no(i).entFuncNetUnitPrcAmt_A1, prdTMsg.entFuncNetUnitPrcAmt)) {
                            updateFlg = true;
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).entFuncNetUnitPrcAmt, sMsg.A.no(i).entFuncNetUnitPrcAmt_A1);
                        } else {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).entFuncNetUnitPrcAmt, prdTMsg.entFuncNetUnitPrcAmt);
                        }
                        if (!compare(sMsg.A.no(i).ccyCd_A1, prdTMsg.ccyCd)) {
                            updateFlg = true;
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).ccyCd, sMsg.A.no(i).ccyCd_A1);
                        } else {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).ccyCd, prdTMsg.ccyCd);
                        }
                        if (!compare(sMsg.A.no(i).exchRate_A1, prdTMsg.exchRate)) {
                            updateFlg = true;
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).exchRate, sMsg.A.no(i).exchRate_A1);
                        } else {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).exchRate, prdTMsg.exchRate);
                        }
                        if (PRCH_REQ_LINE_TP.EXPENSE.equals(sMsg.A.no(i).prchReqLineTpCd_A1.getValue()) || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(sMsg.A.no(i).prchReqLineTpCd_A1.getValue()))
                        {
                            if (!compare(sMsg.A.no(i).coaCmpyCd_A1, prdTMsg.coaCmpyCd)) {
                                updateFlg = true;
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).coaCmpyCd, sMsg.A.no(i).coaCmpyCd_A1);
                            } else {
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).coaCmpyCd, prdTMsg.coaCmpyCd);
                            }
                            if (!compare(sMsg.A.no(i).coaAfflCd_A1, prdTMsg.coaAfflCd)) {
                                updateFlg = true;
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).coaAfflCd, sMsg.A.no(i).coaAfflCd_A1);
                            } else {
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).coaAfflCd, prdTMsg.coaAfflCd);
                            }
                            if (!compare(sMsg.A.no(i).coaBrCd_A1, prdTMsg.coaBrCd)) {
                                updateFlg = true;
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).coaBrCd, sMsg.A.no(i).coaBrCd_A1);
                            } else {
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).coaBrCd, prdTMsg.coaBrCd);
                            }
                            if (!compare(sMsg.A.no(i).coaChCd_A1, prdTMsg.coaChCd)) {
                                updateFlg = true;
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).coaChCd, sMsg.A.no(i).coaChCd_A1);
                            } else {
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).coaChCd, prdTMsg.coaChCd);
                            }
                            if (!compare(sMsg.A.no(i).coaCcCd_A1, prdTMsg.coaCcCd)) {
                                updateFlg = true;
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).coaCcCd, sMsg.A.no(i).coaCcCd_A1);
                            } else {
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).coaCcCd, prdTMsg.coaCcCd);
                            }
                            if (!compare(sMsg.A.no(i).coaAcctCd_A1, prdTMsg.coaAcctCd)) {
                                updateFlg = true;
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).coaAcctCd, sMsg.A.no(i).coaAcctCd_A1);
                            } else {
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).coaAcctCd, prdTMsg.coaAcctCd);
                            }
                            // QC#29729
                            if (!compare(sMsg.A.no(i).coaProdCd_A1, prdTMsg.coaProdCd)) {
                                updateFlg = true;
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).coaProdCd, sMsg.A.no(i).coaProdCd_A1);
                            } else {
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).coaProdCd, prdTMsg.coaProdCd);
                            }
                            if (!compare(sMsg.A.no(i).coaProjCd_A1, prdTMsg.coaProjCd)) {
                                updateFlg = true;
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).coaProjCd, sMsg.A.no(i).coaProjCd_A1);
                            } else {
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).coaProjCd, prdTMsg.coaProjCd);
                            }
                            if (!compare(sMsg.A.no(i).coaExtnCd_A1, prdTMsg.coaExtnCd)) {
                                updateFlg = true;
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).coaExtnCd, sMsg.A.no(i).coaExtnCd_A1);
                            } else {
                                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).coaExtnCd, prdTMsg.coaExtnCd);
                            }
                        }
                        if (!compare(sMsg.A.no(i).relRqstToPoOrdNum_A1, prdTMsg.relRqstToPoOrdNum)) {
                            updateFlg = true;
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).relRqstToPoOrdNum, sMsg.A.no(i).relRqstToPoOrdNum_A1);
                        } else {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).relRqstToPoOrdNum, prdTMsg.relRqstToPoOrdNum);
                        }
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).poOrdNum, prdTMsg.poOrdNum);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).poOrdDtlLineNum, prdTMsg.poOrdDtlLineNum);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).invtyOrdNum, prdTMsg.invtyOrdNum);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).invtyOrdLineNum, prdTMsg.invtyOrdLineNum);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).wrkOrdNum, prdTMsg.wrkOrdNum);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).wrkOrdDtlLineNum, prdTMsg.wrkOrdDtlLineNum);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).trxRefNum, prdTMsg.trxRefNum);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).trxRefLineNum, prdTMsg.trxRefLineNum);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).trxRefLineSubNum, prdTMsg.trxRefLineSubNum);
                        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).shpgPlnNum, prdTMsg.shpgPlnNum);
                        if (!compare(sMsg.A.no(i).prchReqLineCmntTxt_A1, prdTMsg.prchReqLineCmntTxt)) {
                            updateFlg = true;
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).prchReqLineCmntTxt, sMsg.A.no(i).prchReqLineCmntTxt_A1);
                        } else {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).prchReqLineCmntTxt, prdTMsg.prchReqLineCmntTxt);
                        }
                        // QC#21209
                        if (!compare(sMsg.A.no(i).spclInstnCmntTxt_A1, prdTMsg.spclInstnCmntTxt)) {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).spclInstnCmntTxt, sMsg.A.no(i).spclInstnCmntTxt_A1);
                        } else {
                            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(addRowCount).spclInstnCmntTxt, prdTMsg.spclInstnCmntTxt);
                        }
                    }
                }
            }

            if (updateFlg) {
                addRowCount++;
            } else {
                params.prchReqInfo.no(addRowCount).clear();
            }
        }
        params.prchReqInfo.setValidCount(addRowCount);
        return params;
    }

    /**
     * call NPZC1030 API(PR update API)
     * @param params NPZC103001PMsg
     * @param cMsg NPAL1280CMsg
     */
    public static void callNPZC1030(NPZC103001PMsg params, NPAL1280CMsg cMsg) {
        NPZC103001 api = new NPZC103001();
        api.execute(params, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(params)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(params);
            for (String xxMsgId : xxMsgIdList) {
                S21InfoLogOutput.println(xxMsgId);
                cMsg.setMessageInfo(xxMsgId);
            }
        } else {
            cMsg.setMessageInfo(ZZM8100I);
        }
    }

    /**
     * <pre>
     * copyFromCmsgOntoSmsg
     * Copy data From CMsg Onto SMsg
     * </pre>
     * @param sMsg NPAL1280CMsg
     * @param cMsg NPAL1280SMsg
     */
    public static void copyFromCmsgOntoSmsg(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg) {

        // po message info
        int poMsgValidCount = 0;
        // copy po message
        for (int i = 0; i < cMsg.R.length(); i++) {
            EZDMsg.copy(cMsg.R.no(i), null, sMsg.R.no(i), null);

            poMsgValidCount++;
        }
        sMsg.R.setValidCount(poMsgValidCount);

        // copy detail list
        if (sMsg.A.getValidCount() == 0) {
            return;
        }
        for (int j = cMsg.xxPageShowFromNum.getValueInt() - 1, k = 0; j < cMsg.xxPageShowToNum.getValueInt(); j++, k++) {
            EZDMsg.copy(cMsg.A.no(k), null, sMsg.A.no(j), null);
        }

        // QC#29155 Update.
        ZYPEZDItemValueSetter.setValue(sMsg.shpgSvcLvlCd_SL, cMsg.shpgSvcLvlCd_SL);
        ZYPEZDItemValueSetter.setValue(sMsg.locNm_HF, cMsg.locNm_HF);
        ZYPEZDItemValueSetter.setValue(sMsg.carrCd_HF, cMsg.carrCd_HF);
        // QC#31087
        ZYPEZDItemValueSetter.setValue(sMsg.destRtlWhCd, cMsg.destRtlWhCd);
    }

    /**
     * <pre>
     * copyFromSmsgOntoCmsg
     * Copy data From Smsg Onto Cmsg
     * </pre>
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     */
    public static void copyFromSMsgOntoCmsg(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.A);
        setPagePos(cMsg, sMsg);

        if (0 < sMsg.A.getValidCount()) {
            int cMsgCount = 0;
            for (int i = cMsg.xxPageShowFromNum.getValueInt() - 1; i < cMsg.xxPageShowToNum.getValueInt(); i++) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(cMsgCount), null);
                // QC# 28939 Update.
                if (isTextitem(cMsg.glblCmpyCd.getValue(), cMsg.A.no(cMsgCount).mdseCd_A1.getValue())) {
                    cMsg.A.no(cMsgCount).mdseCd_A1.clear();
                }
                cMsgCount++;
            }
            cMsg.A.setValidCount(cMsgCount);
        }
    }

    /**
     * <pre>
     * Set page pos.
     * </pre>
     * @param cMsg NLBL3090CMsg
     * @param sMsg NLBL3090SMsg
     */
    public static void setPagePos(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg) {
        if ((cMsg.A.length() == 0) || (sMsg.A.length() == 0) || (sMsg.A.getValidCount() <= 0)) {
            cMsg.xxPageShowFromNum.setValue(0);
            cMsg.xxPageShowToNum.setValue(0);
            cMsg.xxPageShowOfNum.setValue(0);
            return;
        }
        int startRowCount = 0;
        if (ZYPCommonFunc.hasValue(cMsg.xxPageShowFromNum)) {
            startRowCount = cMsg.xxPageShowFromNum.getValueInt();
        }
        int allRowCount = sMsg.A.getValidCount();
        if (startRowCount == 0) {
            cMsg.xxPageShowFromNum.setValue(1);
        } else if ((startRowCount < 0) || ((allRowCount <= startRowCount))) {
            // last page
            cMsg.xxPageShowFromNum.setValue(getLastPageStartCount(allRowCount, cMsg.A.length()));
        } else {
            if ((startRowCount % cMsg.A.length()) != 1) {
                startRowCount = startRowCount - (startRowCount % cMsg.A.length()) + 1;
            }
            cMsg.xxPageShowFromNum.setValue(startRowCount);
        }
        if ((cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1) < allRowCount) {
            cMsg.xxPageShowToNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1);
        } else {
            cMsg.xxPageShowToNum.setValue(allRowCount);
        }
        cMsg.xxPageShowOfNum.setValue(allRowCount);
    }

    /**
     * pagenation method
     * @param allRowCount int
     * @param pageRowCount int
     * @return
     */
    private static int getLastPageStartCount(int allRowCount, int pageRowCount) {
        if ((allRowCount <= 0) || (pageRowCount <= 0)) {
            return 0;
        }
        if (allRowCount <= pageRowCount) {
            return 1;
        }
        if (allRowCount % pageRowCount == 0) {
            return allRowCount - pageRowCount + 1;
        }
        return ((int) (allRowCount / pageRowCount)) * pageRowCount + 1;
    }

    /**
     * find PO_VND_V
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param vndCd String
     * @param index int
     */
    private static void findPoVndV(NPAL1280SMsg sMsg, String glblCmpyCd, String vndCd, int index) {
        S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().getVendorName(glblCmpyCd, vndCd);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> list = (List) ssmResult.getResultObject();
            if (list != null && 0 < list.size()) {
                Map<String, Object> map = list.get(0);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prntVndNm_A1, (String) map.get(RS_PRNT_VND_NM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).locNm_A1, (String) map.get(RS_VND_NM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prntVndCd_A1, (String) map.get(RS_PRNT_VND_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).vndCd_A1, (String) map.get(RS_VND_CD));
            }
        }
    }

    /**
     * find ASL_DTL
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param vndCd String
     * @param mdseCd String
     * @param salesDate String
     * @param index int
     */
    private static void findAslDtl(NPAL1280SMsg sMsg, String glblCmpyCd, String vndCd, String mdseCd, String salesDate, int index) {
        S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().findAslDtl(glblCmpyCd, vndCd, mdseCd, salesDate);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> list = (List) ssmResult.getResultObject();
            if (list != null && 0 < list.size()) {
                Map<String, Object> map = list.get(0);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).aslMdseCd_A1, (String) map.get(RS_SPLY_ITEM_NUM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqDsplUomCd_A1, (String) map.get(RS_VND_UOM_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).entDealNetUnitPrcAmt_A1, (BigDecimal) map.get(RS_UNIT_PRC_AMT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).entDealNetUnitPrcAmt_HD, (BigDecimal) map.get(RS_UNIT_PRC_AMT));

                // QC#28216 Update. copy with QC#21170.
                setRqstRcvDt(sMsg, map, glblCmpyCd, salesDate, index) ;
                // START 2023/02/03 T.Kuroda [QC#60966, ADD]
                setRqstShipDt(sMsg, map, glblCmpyCd, index) ;
                // END   2023/02/03 T.Kuroda [QC#60966, ADD]
            }
        }
    }

    /**
     * Header cancel parameter
     * @param cMsg NPAL1280CMsg
     * @param glblCmpyCd String
     * @return NPZC103001PMsg
     */
    public static NPZC103001PMsg getHeaderCancelParam(NPAL1280CMsg cMsg, String glblCmpyCd) {
        NPZC103001PMsg params = new NPZC103001PMsg();
        // for Header
        ZYPEZDItemValueSetter.setValue(params.xxModeCd, CANCEL_MODE);
        ZYPEZDItemValueSetter.setValue(params.eventId, ZYPConstant.FLG_ON_1);
        ZYPEZDItemValueSetter.setValue(params.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(params.prchReqNum, cMsg.prchReqNum);
        return params;
    }

    /**
     * Line cancel paramter
     * @param cMsg NPAL1280CMsg
     * @param glblCmpyCd String
     * @param prdTMsgList List<PRCH_REQ_DTLTMsg>
     * @return NPZC103001PMsg
     */
    public static NPZC103001PMsg getLineCancelParam(NPAL1280CMsg cMsg, String glblCmpyCd, List<PRCH_REQ_DTLTMsg> prdTMsgList) {
        NPZC103001PMsg params = new NPZC103001PMsg();
        // for Header
        ZYPEZDItemValueSetter.setValue(params.xxModeCd, CANCEL_MODE);
        ZYPEZDItemValueSetter.setValue(params.eventId, ZYPConstant.FLG_ON_1);
        ZYPEZDItemValueSetter.setValue(params.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(params.prchReqNum, cMsg.prchReqNum);
        for (int i = 0; i < prdTMsgList.size(); i++) {
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(i).prchReqLineNum, prdTMsgList.get(i).prchReqLineNum);
        }
        return params;
    }

    /**
     * find Max PR line number
     * @param prchReqNum String
     * @param glblCmpyCd String
     * @return String
     */
    public static String getMaxPrLineNum(String prchReqNum, String glblCmpyCd) {
        S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().getMaxPrLineNum(prchReqNum, glblCmpyCd);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> list = (List) ssmResult.getResultObject();
            for (int c = 0; c < list.size(); c++) {
                Map<String, Object> map = list.get(c);
                return (String) map.get(RS_MAX_LINE_NUM);
            }
        }
        return CPO_DTL_LINE_SUB_NUM_000;
    }

    /**
     * add new line button event
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param chgAccount String
     */
    public static void addNewLine(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, String chgAccount) {
        if (sMsg.A.length() < sMsg.xxPageShowOfNum.getValueInt() + 1) {
            cMsg.setMessageInfo(NPAM0077E);
            return;
        }

        PRCH_REQTMsg prTMsg = new PRCH_REQTMsg();
        ZYPEZDItemValueSetter.setValue(prTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prTMsg.prchReqNum, cMsg.prchReqNum);

        prTMsg = (PRCH_REQTMsg) EZDTBLAccessor.findByKey(prTMsg);
        String maxPrchReqLineNum = FIRST_DTL_LINE_NUM;
        String prchReqLineNum = null;
        if (prTMsg == null) {
            if (sMsg.A.getValidCount() > 0) {
                prchReqLineNum = sMsg.A.no(sMsg.A.getValidCount() - 1).prchReqLineNum_A1.getValue();
                if (ZYPCommonFunc.hasValue(prchReqLineNum)) {
                    maxPrchReqLineNum = addDtlLineNum(prchReqLineNum);
                } else {
                    maxPrchReqLineNum = addDtlLineNum(maxPrchReqLineNum);
                }
            }
        } else {
            maxPrchReqLineNum = addDtlLineNum(getMaxPrLineNum(prTMsg.prchReqNum.getValue(), glblCmpyCd));
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                if (maxPrchReqLineNum.equals(sMsg.A.no(i).prchReqLineNum_A1.getValue())) {
                    maxPrchReqLineNum = addDtlLineNum(maxPrchReqLineNum);
                }
            }
        }
        int addIndex = cMsg.xxPageShowOfNum.getValueInt();

        String prntVndCd = null;
        String prntVndNm = null;
        String vndCd = null;
        String vndNm = null;
        if (ZYPCommonFunc.hasValue(cMsg.prntVndCd)) {
            prntVndNm = cMsg.prntVndCd.getValue();
        }
        if (ZYPCommonFunc.hasValue(cMsg.prntVndNm)) {
            prntVndNm = cMsg.prntVndNm.getValue();
        }
        if (ZYPCommonFunc.hasValue(cMsg.vndCd)) {
            vndCd = cMsg.vndCd.getValue();
        }
        if (ZYPCommonFunc.hasValue(cMsg.locNm)) {
            vndNm = cMsg.locNm.getValue();
        }
        S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().getVendorName(glblCmpyCd, prntVndCd, prntVndNm, vndCd, vndNm);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> list = (List) ssmResult.getResultObject();
            if (ssmResult.getQueryResultCount() == 1) {
                if (list != null && 0 < list.size()) {
                    Map<String, Object> map = list.get(0);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).prchReqLineNum_A1, maxPrchReqLineNum);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).prchReqLineSubNum_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).xxLineNum_A1, maxPrchReqLineNum);
                    setPrLineType(glblCmpyCd, sMsg.A.no(addIndex));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).prchReqLineTpCd_A1, PRCH_REQ_LINE_TP.GOODS);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).prchReqLineTpCd_HD, PRCH_REQ_LINE_TP.GOODS);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).prntVndNm_A1, (String) map.get(RS_PRNT_VND_NM));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).locNm_A1, (String) map.get(RS_VND_NM));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).prntVndCd_A1, (String) map.get(RS_PRNT_VND_CD));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).vndCd_A1, (String) map.get(RS_VND_CD));
//                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).xxScrItem130Txt_A1, chgAccount);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).xxChkBox_A1, ZYPConstant.FLG_OFF_N);
                    // QC#21170
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).rqstRcvDt_A1, cMsg.rqstRcvDt);
                    // START 2023/02/03 T.Kuroda [QC#60966, ADD]
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).rqstShipDt_A1, cMsg.rqstShipDt);
                    // END   2023/02/03 T.Kuroda [QC#60966, ADD]
                }
            } else {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).prchReqLineNum_A1, maxPrchReqLineNum);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).prchReqLineSubNum_A1, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).xxLineNum_A1, maxPrchReqLineNum);
                setPrLineType(glblCmpyCd, sMsg.A.no(addIndex));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).prchReqLineTpCd_A1, PRCH_REQ_LINE_TP.GOODS);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).prchReqLineTpCd_HD, PRCH_REQ_LINE_TP.GOODS);
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).xxScrItem130Txt_A1, chgAccount);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).xxChkBox_A1, ZYPConstant.FLG_OFF_N);
                // QC#21170
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).rqstRcvDt_A1, cMsg.rqstRcvDt);
                // START 2023/02/03 T.Kuroda [QC#60966, ADD]
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).rqstShipDt_A1, cMsg.rqstShipDt);
                // END   2023/02/03 T.Kuroda [QC#60966, ADD]
            }
        } else {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).prchReqLineNum_A1, maxPrchReqLineNum);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).prchReqLineSubNum_A1, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).xxLineNum_A1, maxPrchReqLineNum);
            setPrLineType(glblCmpyCd, sMsg.A.no(addIndex));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).prchReqLineTpCd_A1, PRCH_REQ_LINE_TP.GOODS);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).prchReqLineTpCd_HD, PRCH_REQ_LINE_TP.GOODS);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).xxScrItem130Txt_A1, chgAccount);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).xxChkBox_A1, ZYPConstant.FLG_OFF_N);
            // QC#21170
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).rqstRcvDt_A1, cMsg.rqstRcvDt);
            // START 2023/02/03 T.Kuroda [QC#60966, ADD]
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).rqstShipDt_A1, cMsg.rqstShipDt);
            // END   2023/02/03 T.Kuroda [QC#60966, ADD]
        }

        cMsg.xxPageShowFromNum.setValue(sMsg.A.getValidCount());
        sMsg.A.setValidCount(addIndex + 1);
    }

    /**
     * 0 pading
     * @param maxPrchReqLineNum
     * @return String
     */
    private static String addDtlLineNum(String maxPrchReqLineNum) {
        int i = Integer.parseInt(maxPrchReqLineNum);
        i++;
        return ZYPCommonFunc.leftPad(Integer.valueOf(i).toString(), IDX_3, "0");
    }

    /**
     * onBlur item code event
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param salesDate String
     * @param lineIndex int
     */
    public static void onBlurItemCode(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, String salesDate, int lineIndex) {

        String prchReqLine = sMsg.A.no(lineIndex).prchReqLineNum_A1.getValue();
        for (int i = lineIndex; i < sMsg.A.getValidCount(); i++) {
            if (prchReqLine.equals(sMsg.A.no(i).prchReqLineNum_A1.getValue()) && 0 < sMsg.A.no(i).prchReqLineSubNum_A1.getValueInt()) {
                removeRow(sMsg.A, i);
                i--;
            }
        }

        MDSETMsg tMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, sMsg.A.no(lineIndex).mdseCd_A1);

        tMsg = (MDSETMsg) EZDTBLAccessor.findByKey(tMsg);

        if (tMsg != null) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIndex).mdseCd_A1, tMsg.mdseCd);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIndex).mdseDescShortTxt_A1, tMsg.mdseDescShortTxt);
            chkPrchAvalFlg(cMsg, sMsg, glblCmpyCd, salesDate, lineIndex);
        } else {
            sMsg.A.no(lineIndex).mdseCd_A1.setErrorInfo(1, NPAM0076E, new String[] {ITEM_NUMBER_TXT });
        }
    }

    /**
     * check MDSE
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param salesDate String
     * @param lineIndex int
     * @return boolean
     */
    public static boolean checkItemCode(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, String salesDate, int lineIndex) {

        // Index of SMsg Line
        int pageShowFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int indexS = pageShowFromNum + lineIndex;

        MDSETMsg tMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, sMsg.A.no(indexS).mdseCd_A1);

        tMsg = (MDSETMsg) EZDTBLAccessor.findByKey(tMsg);

        if (tMsg != null) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(indexS).mdseCd_A1, tMsg.mdseCd);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(indexS).mdseDescShortTxt_A1, tMsg.mdseDescShortTxt);
            // QC#18761 check Mdse Code is duplicated from ASL Supplier item code.
            if (!chkMdseDuplicateFromAsl(cMsg, sMsg, glblCmpyCd, salesDate, lineIndex)) {
                return false;
            }
            // QC#28216
            boolean isResult = chkPrchAvalFlgItemCode(cMsg, sMsg, glblCmpyCd, salesDate, indexS);
            if (!isResult) {
                return isResult;
            }
        } else {
            sMsg.A.no(indexS).mdseCd_A1.setErrorInfo(1, NPAM0076E, new String[] {ITEM_NUMBER_TXT });
            return false;
        }

        // QC#28216 Get Primary Vendor from ASL
        boolean isSupplierSite = false;
        boolean isSupplier = false;

        if(ZYPCommonFunc.hasValue(sMsg.A.no(indexS).locNm_A1)){
            // QC#31087
            isSupplierSite = chkSupplierSite(sMsg, glblCmpyCd, indexS, false);
        }
        if(ZYPCommonFunc.hasValue(sMsg.A.no(indexS).prntVndNm_A1)){
            isSupplier = chkSupplier(sMsg, glblCmpyCd, indexS);
        }
        
        boolean isCombinationSupplier = false;
        if(isSupplierSite && isSupplier){
            isCombinationSupplier = chkPoVnd(sMsg, glblCmpyCd, indexS);
        }
        
        boolean isAslDtlChk = false;
        if(isCombinationSupplier){
            isAslDtlChk = chkAslDtl(sMsg, glblCmpyCd, salesDate, indexS);
        }
        
        if(isAslDtlChk){
            // PrchReqTpCd_SL is necessary for processing setRqstRcvDt(findAslDtl).
            ZYPEZDItemValueSetter.setValue(sMsg.prchReqTpCd_SL, cMsg.prchReqTpCd_SL);
            findAslDtl(sMsg, glblCmpyCd, sMsg.A.no(indexS).vndCd_A1.getValue(), sMsg.A.no(indexS).mdseCd_A1.getValue(), salesDate, indexS);
            if (ZYPCommonFunc.hasValue(sMsg.A.no(indexS).entDealNetUnitPrcAmt_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(indexS).prchReqQty_A1)) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(indexS).xxUnitPrc_A1, sMsg.A.no(indexS).entDealNetUnitPrcAmt_A1.getValue().multiply((BigDecimal) sMsg.A.no(indexS).prchReqQty_A1.getValue()));
            }
        } else {
            // Not set supplier or error.
            // derive primary supplier.
            // QC#30994
            if (ZYPCommonFunc.hasValue(sMsg.A.no(indexS).prntVndNm_A1) || ZYPCommonFunc.hasValue(sMsg.A.no(indexS).locNm_A1)) {
                return false;
            }
            // clear error info.
            sMsg.A.no(indexS).mdseCd_A1.clearErrorInfo();
            sMsg.A.no(indexS).locNm_A1.clearErrorInfo();
            sMsg.A.no(indexS).prntVndNm_A1.clearErrorInfo();
            
            // derive.
            NPZC113001 api = new NPZC113001();
            NPZC113001PMsg pMsg = new NPZC113001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, salesDate);
            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, cMsg.shipToCustCd);
            pMsg.vndCd.clear();
            pMsg.prntVndCd.clear();
            ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, (sMsg.A.no(indexS).mdseCd_A1));

            api.execute(pMsg, ONBATCH_TYPE.ONLINE);
            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                return true;
            }
    
            if (pMsg.xxMsgIdList.getValidCount() == 0 && ZYPConstant.FLG_ON_Y.equals(pMsg.xxErrFlg.getValue())) {
                // QC#28216 Update.
                sMsg.A.no(indexS).mdseCd_A1.setErrorInfo(1, NPZM0268E);
                return false;
            } else if (ZYPCommonFunc.hasValue(pMsg.vndCd)) {
                findPoVndV(sMsg, glblCmpyCd, pMsg.vndCd.getValue(), indexS);
                // PrchReqTpCd_SL is necessary for processing setRqstRcvDt(findAslDtl).
                ZYPEZDItemValueSetter.setValue(sMsg.prchReqTpCd_SL, cMsg.prchReqTpCd_SL);
                findAslDtl(sMsg, glblCmpyCd, pMsg.vndCd.getValue(), sMsg.A.no(indexS).mdseCd_A1.getValue(), salesDate, indexS);
                if (ZYPCommonFunc.hasValue(sMsg.A.no(indexS).entDealNetUnitPrcAmt_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(indexS).prchReqQty_A1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(indexS).xxUnitPrc_A1, sMsg.A.no(indexS).entDealNetUnitPrcAmt_A1.getValue().multiply((BigDecimal) sMsg.A.no(indexS).prchReqQty_A1.getValue()));
                }
            }
        }

        return true;
    }

    //QC#28939 Add method.
    private static boolean isExpenceTextItem(NPAL1280SMsg msg, String glblCmpyCd, int lineIndex) {

        if (PRCH_REQ_LINE_TP.EXPENSE.equals(msg.A.no(lineIndex).prchReqLineTpCd_A1.getValue()) //
                || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(msg.A.no(lineIndex).prchReqLineTpCd_A1.getValue())) {

            String mdseCd = msg.A.no(lineIndex).mdseCd_A1.getValue();

            if (!ZYPCommonFunc.hasValue(mdseCd) //
                    || (ZYPCommonFunc.hasValue(mdseCd) && isTextitem(glblCmpyCd, mdseCd))) {
                return true;
            }
        }

        return false;
    }

    /**
     * isTextitem
     * QC#28939 Add method
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return true (text item) / false (not text item)
     */
    public static boolean isTextitem(String glblCmpyCd, String mdseCd) {

        MDSETMsg cond = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(cond.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cond.mdseCd, mdseCd);

        MDSETMsg mdse = (MDSETMsg) EZDTBLAccessor.findByKey(cond);

        if (mdse != null) {
            if (MDSE_ITEM_TP.TEXT_ITEM.equals(mdse.mdseItemTpCd.getValue())) {
                return true;
            }
        }

        return false;
    }

    /**
     * check aval flag
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param salesDate String
     * @param lineIndex int
     * @return boolean
     */
    public static boolean chkPrchAvalFlgItemCode(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, String salesDate, int lineIndex) {
        String varCharConstVal = ZYPCodeDataUtil.getVarCharConstValue(CUST_DROP_SHIP_PO_QULF, glblCmpyCd);

        if (!ZYPCommonFunc.hasValue(sMsg.A.no(lineIndex).prntVndNm_A1)) {
            sMsg.A.no(lineIndex).prntVndCd_A1.clear();
        }
        if (!ZYPCommonFunc.hasValue(sMsg.A.no(lineIndex).locNm_A1)) {
            sMsg.A.no(lineIndex).vndCd_A1.clear();
        }
        // QC#53483 Add
        Map<String, NPAL1280_ASMsg> tmpTrxMap = new HashMap<String, NPAL1280_ASMsg>();
        if (!varCharConstVal.equals(cMsg.poQlfyCd.getValue())) {
            String prchReqLine = sMsg.A.no(lineIndex).prchReqLineNum_A1.getValue();
            for (int i = lineIndex; i < sMsg.A.getValidCount(); i++) {
                if (prchReqLine.equals(sMsg.A.no(i).prchReqLineNum_A1.getValue()) && 0 < sMsg.A.no(i).prchReqLineSubNum_A1.getValueInt()) {
                    String prKey = ZYPCommonFunc.concatString(prchReqLine, sMsg.A.no(i).prchReqLineNum_A1.getValue(), sMsg.A.no(i).prchReqLineSubNum_A1.getValue().toPlainString());

                    NPAL1280_ASMsg tmpSMsg = new NPAL1280_ASMsg();
                    EZDMsg.copy(sMsg.A.no(i), null, tmpSMsg, null);
                    tmpTrxMap.put(prKey, tmpSMsg);
                    // QC#53483 End
                    removeRow(sMsg.A, i);
                    i--;
                }
            }
            S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().chkPrchAvalFlg(glblCmpyCd //
                    , sMsg.A.no(lineIndex).mdseCd_A1.getValue() //
                    , sMsg.destRtlWhCd.getValue());
            int addLineCount = 0;
            if (ssmResult.isCodeNormal()) {
                List<Map<String, Object>> list = (List) ssmResult.getResultObject();
                for (int c = 0; c < list.size(); c++) {
                    Map<String, Object> map = list.get(c);
                    // Check Prch Aval Flg
                    if (ZYPConstant.FLG_OFF_N.equals((String) map.get(RS_PRCH_AVAL_FLG))) {
                        // QC#27655 Update.
                        sMsg.A.no(lineIndex).mdseCd_A1.setErrorInfo(1, NPAM1627E);
                        return false;
                    }

                    // set item info
                    // mdseCd
                    /*
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIndex).mdseCd_A1//
                            , convMdseCd(glblCmpyCd, sMsg.A.no(lineIndex).mdseCd_A1.getValue()));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIndex).coaMdseTpCd_A1//
                            , (String) map.get("COA_MDSE_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIndex).coaProdCd_A1//
                            , (String) map.get("COA_PROD_CD"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIndex).coaBrCd_A1//
                            , (String) map.get("COA_BR_CD"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIndex).coaAcctCd_A1//
                            , (String) map.get("COA_ACCT_CD"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIndex).coaCmpyCd_A1//
                            , cMsg.coaCmpyCd_GO);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIndex).coaAfflCd_A1//
                            , cMsg.coaAfflCd_GO);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIndex).coaChCd_A1//
                            , cMsg.coaChCd_GO);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIndex).coaCcCd_A1//
                            , cMsg.coaCcCd_GO);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIndex).coaProdCd_A1//
                            , cMsg.coaProdCd_GO);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIndex).coaProjCd_A1//
                            , cMsg.coaProjCd_GO);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIndex).coaExtnCd_A1//
                            , cMsg.coaExtnCd_GO);
                    */

                    // 2019/01/15 QC#29778 Add Start
                    //QC#62443 2024/3/4 Add Start
                    if (!PRCH_REQ_LINE_TP.EXPENSE.equals(sMsg.A.no(lineIndex).prchReqLineTpCd_A1.getValue())) {
                        getDefaultAccount(cMsg, sMsg, glblCmpyCd, Integer.valueOf(cMsg.xxLineNum.getValue()));
                        // 2019/01/15 QC#29778 Add End
                    }
                    //QC#62443 2024/3/4 Add End
                    // set item
                    if (!ZYPCommonFunc.hasValue(sMsg.prchReqNum)) {
                        if (ZYPConstant.FLG_ON_Y.equals((String) map.get(RS_PRNT_CMPY_SET_MDSE_FLG))) {
                            // CUSA SET Parent Line
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIndex).poMdseCmpsnTpCd_A1, PO_MDSE_CMPSN_TP.PARENT);

                            // get set item
                            S21SsmEZDResult cmpResult = NPAL1280Query.getInstance().getComponent(glblCmpyCd//
                                    , sMsg.A.no(lineIndex).mdseCd_A1.getValue() //
                                    , salesDate //
                                    , sMsg.A.no(lineIndex).prchReqLineTpCd_A1.getValue());
                            if (cmpResult.isCodeNormal()) {
                                List<Map<String, Object>> cmpList = recursiveComponent(glblCmpyCd, salesDate, sMsg.A.no(lineIndex).prchReqLineTpCd_A1.getValue(), (List) cmpResult.getResultObject());

                                BigDecimal subLineNum = BigDecimal.ONE;

                                for (int i = 0; i < cmpList.size(); i++) {
                                    Map<String, Object> cmpMap = cmpList.get(i);
                                    addLineCount++;
                                    int rowIndex = addLineCount + lineIndex;
                                    moveShiftRow(sMsg.A, rowIndex);
                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).prchReqLineNum_A1, sMsg.A.no(lineIndex).prchReqLineNum_A1);
                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).prchReqLineSubNum_A1, subLineNum);
                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).xxLineNum_A1, sMsg.A.no(lineIndex).prchReqLineNum_A1.getValue() + "." + subLineNum.toString());
                                    setPrLineType(glblCmpyCd, sMsg.A.no(rowIndex));
                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).prchReqLineTpCd_A1, sMsg.A.no(lineIndex).prchReqLineTpCd_A1.getValue());
                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).mdseCd_A1, convMdseCd(glblCmpyCd, (String) cmpMap.get(RS_MDSE_CD)));
                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).mdseDescShortTxt_A1, (String) cmpMap.get(RS_MDSE_DESC_SHORT_TXT));
                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).prchReqDispQty_A1, (BigDecimal) cmpMap.get(RS_CHILD_MDSE_QTY));
                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).prntVndNm_A1, sMsg.A.no(lineIndex).prntVndNm_A1);
                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).locNm_A1, sMsg.A.no(lineIndex).locNm_A1);
                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).prchReqLineStsNm_A1, sMsg.A.no(lineIndex).prchReqLineStsNm_A1);
                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).prchReqLineStsCd_HD, sMsg.A.no(lineIndex).prchReqLineStsCd_HD);

                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaMdseTpCd_A1, (String) cmpMap.get(RS_COA_MDSE_TP_CD));
                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaProdCd_A1, (String) cmpMap.get(RS_COA_PROD_CD));

                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).prntVndCd_A1, sMsg.A.no(lineIndex).prntVndCd_A1);
                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).vndCd_A1, sMsg.A.no(lineIndex).vndCd_A1);

                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).entDealNetUnitPrcAmt_A1, BigDecimal.ZERO);
                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).poMdseCmpsnTpCd_A1, PO_MDSE_CMPSN_TP.CHILD);
                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).prntCmpySetMdseFlg_A1, ZYPConstant.FLG_ON_Y);
                                    // QC#53483
                                    String prKey = ZYPCommonFunc.concatString(prchReqLine, sMsg.A.no(rowIndex).prchReqLineNum_A1.getValue(), sMsg.A.no(rowIndex).prchReqLineSubNum_A1.getValue().toPlainString());
                                    if (tmpTrxMap.containsKey(prKey)) {
                                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).xxLineNum_TR, tmpTrxMap.get(prKey).xxLineNum_TR.getValue());
                                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).trxRefLineNum_A1, tmpTrxMap.get(prKey).trxRefLineNum_A1.getValue());
                                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).trxRefLineSubNum_A1, tmpTrxMap.get(prKey).trxRefLineSubNum_A1.getValue());
                                    }
                                    // QC#29729
                                    if (!sMsg.A.no(rowIndex).mdseCd_A1.isError()) {
                                        // 2019/01/15 QC#29778 Mod Start
//                                        NPAL1280CommonLogic.setDefaultChargAccount(sMsg.A.no(rowIndex), glblCmpyCd, cMsg.shipToCustCd.getValue());
                                        getDefaultAccount(cMsg, sMsg, glblCmpyCd, rowIndex);
                                        // 2019/01/15 QC#29778 Mod End
                                    }
                                    subLineNum = subLineNum.add(BigDecimal.ONE);
                                    //QC#62443 2024/3/4 Add Start
                                    if (PRCH_REQ_LINE_TP.EXPENSE.equals(sMsg.A.no(rowIndex).prchReqLineTpCd_A1.getValue()) && ZYPCommonFunc.hasValue(sMsg.A.no(rowIndex).mdseCd_A1.getValue())) {

                                        String varCharExpSplyCoaCmpyCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_CMPY_CD, cMsg.glblCmpyCd.getValue());
                                        String varCharExpSplyCoaBrCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_BR_CD, cMsg.glblCmpyCd.getValue());
                                        String varCharExpSplyCoaAcctCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_ACCT_CD, cMsg.glblCmpyCd.getValue());
                                        String varCharExpSplyCoaChCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_CH_CD, cMsg.glblCmpyCd.getValue());
                                        String varCharExpSplyCoaAfflCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_AFFL_CD, cMsg.glblCmpyCd.getValue());
                                        String varCharExpSplyCoaProjCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_PROJ_CD, cMsg.glblCmpyCd.getValue());
                                        String varCharExpSplyCoaExtnCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_EXTN_CD, cMsg.glblCmpyCd.getValue());

                                        MDSETMsg mdseTMsg = new MDSETMsg();

                                        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, cMsg.glblCmpyCd);
                                        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, sMsg.A.no(rowIndex).mdseCd_A1.getValue());
                                        mdseTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTMsg);

                                        S21SsmEZDResult result = NPAL1280Query.getInstance().getCoaCcCd(cMsg.glblCmpyCd.getValue(), sMsg.A.no(rowIndex).mdseCd_A1.getValue());

                                        String splyCoaCcCd = (String) result.getResultObject();

                                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaCcCd_A1, splyCoaCcCd);
                                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaProdCd_A1, mdseTMsg.coaProdCd.getValue());

                                        StringBuffer sb = new StringBuffer();
                                        sb.append(varCharExpSplyCoaCmpyCd);
                                        sb.append(PERIOD);
                                        sb.append(varCharExpSplyCoaBrCd);
                                        sb.append(PERIOD);
                                        sb.append(sMsg.A.no(rowIndex).coaCcCd_A1.getValue());
                                        sb.append(PERIOD);
                                        sb.append(varCharExpSplyCoaAcctCd);
                                        sb.append(PERIOD);
                                        sb.append(sMsg.A.no(rowIndex).coaProdCd_A1.getValue());
                                        sb.append(PERIOD);
                                        sb.append(varCharExpSplyCoaChCd);
                                        sb.append(PERIOD);
                                        sb.append(varCharExpSplyCoaAfflCd);
                                        sb.append(PERIOD);
                                        sb.append(varCharExpSplyCoaProjCd);
                                        sb.append(PERIOD);
                                        sb.append(varCharExpSplyCoaExtnCd);

                                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).xxScrItem130Txt_A1, sb.toString());
                                    }
                                    //QC#62443 2024/3/4 Add End
                                    /*
                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaCmpyCd_A1//
                                            , cMsg.coaCmpyCd_GO);

                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaAfflCd_A1//
                                            , cMsg.coaAfflCd_GO);

                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaBrCd_A1//
                                            , sMsg.A.no(lineIndex).coaBrCd_A1);

                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaAcctCd_A1//
                                            , (String) map.get("COA_ACCT_CD"));

                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaChCd_A1//
                                            , cMsg.coaChCd_GO);

                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaCcCd_A1//
                                            , cMsg.coaCcCd_GO);

                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaAcctCd_A1//
                                            , sMsg.A.no(lineIndex).coaAcctCd_A1);

                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaProjCd_A1//
                                            , cMsg.coaProjCd_GO);

                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaExtnCd_A1//
                                            , cMsg.coaExtnCd_GO);

                                    String nineSegMent = sMsg.A.no(rowIndex).coaCmpyCd_A1.getValue() //
                                            + "." + sMsg.A.no(rowIndex).coaBrCd_A1.getValue() //
                                            + "." + sMsg.A.no(rowIndex).coaCcCd_A1.getValue()//
                                            + "." + sMsg.A.no(rowIndex).coaAcctCd_A1.getValue()//
                                            + "." + sMsg.A.no(rowIndex).coaProdCd_A1.getValue()//
                                            + "." + sMsg.A.no(rowIndex).coaChCd_A1.getValue()//
                                            + "." + sMsg.A.no(rowIndex).coaAfflCd_A1.getValue() //
                                            + "." + sMsg.A.no(rowIndex).coaProjCd_A1.getValue()//
                                            + "." + sMsg.A.no(rowIndex).coaExtnCd_A1.getValue();

                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).xxScrItem130Txt_A1, nineSegMent);
                                    */

                                    // child Mdse qty
                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).childMdseQty_HD, (BigDecimal) cmpMap.get(RS_CHILD_MDSE_QTY));
                                }
                            }
                        } else {
                            // Regular Line
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIndex).poMdseCmpsnTpCd_A1, PO_MDSE_CMPSN_TP.REGULAR);
                        }
                    }
                }
            } else {
                sMsg.A.no(lineIndex).mdseCd_A1.setErrorInfo(1, NPAM0076E, new String[] {ITEM_NUMBER_TXT });
                cMsg.setMessageInfo(ZZM9037E, new String[] {sMsg.A.no(lineIndex).mdseCd_A1.getValue() });
                return false;
            }
        }
        
        // QC#28216 Delete ASL info. Find ASL Info is implemented in the upper logic.

        return true;
    }

    /**
     * check aval flag
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param salesDate String
     * @param lineIndex int
     * @return boolean
     */
    public static boolean chkPrchAvalFlg(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, String salesDate, int lineIndex) {
        // QC#29729
        List<String> asMsgList = new ArrayList<String>();
        boolean isAcctErr = false;
        String varCharConstVal = ZYPCodeDataUtil.getVarCharConstValue(CUST_DROP_SHIP_PO_QULF, glblCmpyCd);
        if (!varCharConstVal.equals(cMsg.poQlfyCd.getValue())) {
            // QC#53483 Start
            Map<String, NPAL1280_ASMsg> tmpTrxMap = new HashMap<String, NPAL1280_ASMsg>();
            String prchReqLine = sMsg.A.no(lineIndex).prchReqLineNum_A1.getValue();
            for (int i = lineIndex; i < sMsg.A.getValidCount(); i++) {
                if (prchReqLine.equals(sMsg.A.no(i).prchReqLineNum_A1.getValue()) && 0 < sMsg.A.no(i).prchReqLineSubNum_A1.getValueInt()) {
                    // QC#29729
                    asMsgList.add(sMsg.A.no(i).xxScrItem130Txt_A1.getValue());

                    String prKey = ZYPCommonFunc.concatString(prchReqLine, sMsg.A.no(i).prchReqLineNum_A1.getValue(), sMsg.A.no(i).prchReqLineSubNum_A1.getValue().toPlainString());

                    NPAL1280_ASMsg tmpSMsg = new NPAL1280_ASMsg();
                    EZDMsg.copy(sMsg.A.no(i), null, tmpSMsg, null);
                    tmpTrxMap.put(prKey, tmpSMsg);
                    // QC#53483 End
                    removeRow(sMsg.A, i);
                    i--;
                }
            }

            // QC#28939 Add check.
            if (isExpenceTextItem(sMsg, glblCmpyCd, lineIndex)) {
                // skip.
                return true;
            }

            S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().chkPrchAvalFlg(glblCmpyCd, sMsg.A.no(lineIndex).mdseCd_A1.getValue(), sMsg.destRtlWhCd.getValue());
            int addLineCount = 0;
            if (ssmResult.isCodeNormal()) {
                List<Map<String, Object>> list = (List) ssmResult.getResultObject();
                for (int c = 0; c < list.size(); c++) {
                    Map<String, Object> map = list.get(c);
                    // Check Prch Aval Flg
                    if (ZYPConstant.FLG_OFF_N.equals((String) map.get(RS_PRCH_AVAL_FLG))) {
                        // QC#27655 Update.
                        sMsg.A.no(lineIndex).mdseCd_A1.setErrorInfo(1, NPAM1627E);
                        return false;
                    }
                    if (ZYPConstant.FLG_ON_Y.equals((String) map.get(RS_PRNT_CMPY_SET_MDSE_FLG))) {
                        // CUSA SET Parent Line
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIndex).poMdseCmpsnTpCd_A1, PO_MDSE_CMPSN_TP.PARENT);
                        S21SsmEZDResult cmpResult = NPAL1280Query.getInstance().getComponent(glblCmpyCd //
                                , sMsg.A.no(lineIndex).mdseCd_A1.getValue() //
                                , salesDate //
                                , sMsg.A.no(lineIndex).prchReqLineTpCd_A1.getValue());
                        if (cmpResult.isCodeNormal()) {

                            List<Map<String, Object>> cmpList = recursiveComponent(glblCmpyCd, salesDate, sMsg.A.no(lineIndex).prchReqLineTpCd_A1.getValue(), (List) cmpResult.getResultObject());

                            BigDecimal subLineNum = BigDecimal.ONE;
                            // QC#29729
                            int cnt = 0;
                            for (int i = 0; i < cmpList.size(); i++) {
                                Map<String, Object> cmpMap = cmpList.get(i);
                                addLineCount++;
                                int rowIndex = addLineCount + lineIndex;

                                moveShiftRow(sMsg.A, rowIndex);
                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).prchReqLineNum_A1, sMsg.A.no(lineIndex).prchReqLineNum_A1);
                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).prchReqLineSubNum_A1, subLineNum);
                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).xxLineNum_A1, sMsg.A.no(lineIndex).prchReqLineNum_A1.getValue() + "." + subLineNum.toString());
                                setPrLineType(glblCmpyCd, sMsg.A.no(rowIndex));
                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).prchReqLineTpCd_A1, sMsg.A.no(lineIndex).prchReqLineTpCd_A1.getValue());
                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).mdseCd_A1, convMdseCd(glblCmpyCd, (String) cmpMap.get(RS_MDSE_CD)));
                                // Parent Qty * Child Qty
                                if (ZYPCommonFunc.hasValue(sMsg.A.no(lineIndex).prchReqDispQty_A1)) {
                                    BigDecimal childQty = (sMsg.A.no(lineIndex).prchReqDispQty_A1.getValue() //
                                            .multiply((BigDecimal) cmpMap.get(RS_CHILD_MDSE_QTY))).setScale(0);
                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).prchReqDispQty_A1, childQty);
                                }

                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).prntVndNm_A1, sMsg.A.no(lineIndex).prntVndNm_A1);
                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).locNm_A1, sMsg.A.no(lineIndex).locNm_A1);
                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).entDealNetUnitPrcAmt_A1, BigDecimal.ZERO);
                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).poMdseCmpsnTpCd_A1, PO_MDSE_CMPSN_TP.CHILD);
                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).prntCmpySetMdseFlg_A1, ZYPConstant.FLG_ON_Y);
                                // QC#53483
                                String prKey = ZYPCommonFunc.concatString(prchReqLine, sMsg.A.no(rowIndex).prchReqLineNum_A1.getValue(), sMsg.A.no(rowIndex).prchReqLineSubNum_A1.getValue().toPlainString());
                                if (tmpTrxMap.containsKey(prKey)) {
                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).xxLineNum_TR, tmpTrxMap.get(prKey).xxLineNum_TR.getValue());
                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).trxRefLineNum_A1, tmpTrxMap.get(prKey).trxRefLineNum_A1.getValue());
                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).trxRefLineSubNum_A1, tmpTrxMap.get(prKey).trxRefLineSubNum_A1.getValue());
                                }
                                subLineNum = subLineNum.add(BigDecimal.ONE);
                                // QC#29729
                                if (!asMsgList.isEmpty() && cnt < asMsgList.size()) {
                                    if (!ZYPCommonFunc.hasValue(asMsgList.get(cnt))) {
                                        // 2019/02/08 QC#30181 Mod Start
//                                        NPAL1280CommonLogic.setDefaultChargAccount(sMsg.A.no(rowIndex), glblCmpyCd, cMsg.shipToCustCd.getValue());
                                        NPAL1280CommonLogic.setDefaultChargAccount(cMsg, sMsg.A.no(rowIndex), glblCmpyCd);
                                        // 2019/02/08 QC#30181 Mod End
                                    } else {
                                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).xxScrItem130Txt_A1, asMsgList.get(cnt));
                                        if (!checkManualSegmentElementForSMsg(cMsg, sMsg, glblCmpyCd, rowIndex)) {
                                            isAcctErr = true;
                                            cnt++;
                                            continue;
                                        }
                                        if (ZYPCommonFunc.hasValue(sMsg.A.no(rowIndex).xxScrItem130Txt_A1)) {
                                            List<String> tokenList = tokenListSplit(sMsg.A.no(rowIndex).xxScrItem130Txt_A1.getValue(), glblCmpyCd);
                                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaCmpyCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
                                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaAfflCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
                                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaBrCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
                                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaChCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
                                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaCcCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
                                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaAcctCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
                                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaProdCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
                                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaProjCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
                                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaExtnCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));
                                        }
                                    }
                                    cnt++;
                                } else {
                                    // 2019/02/08 QC#30181 Mod Start
//                                    NPAL1280CommonLogic.setDefaultChargAccount(sMsg.A.no(rowIndex), glblCmpyCd, cMsg.shipToCustCd.getValue());
                                    NPAL1280CommonLogic.setDefaultChargAccount(cMsg, sMsg.A.no(rowIndex), glblCmpyCd);
                                    // 2019/02/08 QC#30181 Mod End
                                }
                            }
                        }
                    } else {
                        // Regular Line
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIndex).poMdseCmpsnTpCd_A1, PO_MDSE_CMPSN_TP.REGULAR);
                    }
                }
            }
        }
        // find ASL Info
        if (ZYPCommonFunc.hasValue(sMsg.A.no(lineIndex).vndCd_A1) && !(PO_MDSE_CMPSN_TP.CHILD.equals(sMsg.A.no(lineIndex).poMdseCmpsnTpCd_A1.getValue()))) {
            S21SsmEZDResult aslResult = NPAL1280Query.getInstance().findAslDtl(glblCmpyCd, sMsg.A.no(lineIndex).vndCd_A1.getValue(), sMsg.A.no(lineIndex).mdseCd_A1.getValue(), salesDate);
            if (aslResult.isCodeNormal()) {
                List<Map<String, Object>> aslResultList = (List) aslResult.getResultObject();
                if (aslResultList != null && aslResultList.size() > 0) {
                    Map<String, Object> aslMap = aslResultList.get(0);
                    BigDecimal unitPrcAmt = (BigDecimal) aslMap.get(RS_UNIT_PRC_AMT);
                    BigDecimal detailUnitPrc = sMsg.A.no(lineIndex).entDealNetUnitPrcAmt_A1.getValue();
                    if (!(ZYPCommonFunc.hasValue(detailUnitPrc)) || detailUnitPrc.compareTo(BigDecimal.ZERO) == 0) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIndex).entDealNetUnitPrcAmt_A1, unitPrcAmt);
                    }
                    if (detailUnitPrc.compareTo(BigDecimal.ZERO) < 0 || unitPrcAmt.compareTo(BigDecimal.ZERO) < 0) {
                        sMsg.A.no(lineIndex).entDealNetUnitPrcAmt_A1.setErrorInfo(1, NPAM0046E, new String[] {UNIT_PRICE_D });
                        return false;
                    }
                    // QC#53271 Mod Start
//                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIndex).aslMdseCd_A1, (String) aslMap.get(RS_SPLY_ITEM_NUM));
                    if (!ZYPCommonFunc.hasValue(sMsg.A.no(lineIndex).aslMdseCd_A1) ||
                    		(!PRCH_REQ_LINE_TP.EXPENSE.equals(sMsg.A.no(lineIndex).prchReqLineTpCd_A1.getValue()) &&
                    		!PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(sMsg.A.no(lineIndex).prchReqLineTpCd_A1.getValue()))) {
                    	ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIndex).aslMdseCd_A1, (String) aslMap.get(RS_SPLY_ITEM_NUM));
                    }
                    // QC#53271 Mod End
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIndex).prchReqDsplUomCd_A1, (String) aslMap.get(RS_VND_UOM_CD));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIndex).aslDtlPk_A1, (BigDecimal) aslMap.get(RS_ASL_DTL_PK));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIndex).aslUnitPrcAmt_A1, (BigDecimal) aslMap.get(RS_UNIT_PRC_AMT));
                }
            } else {
                sMsg.A.no(lineIndex).mdseCd_A1.setErrorInfo(1, NPAM1364E, new String[] {ITEM_D });
                return false;
            }
        }
        // QC#29729
        if (isAcctErr) {
            return false;
        }
        return true;
    }

    /**
     * sMsg Moving row
     * @param table NPAL1280_ASMsgArray
     * @param rowIndex int
     */
    private static void moveShiftRow(NPAL1280_ASMsgArray table, int rowIndex) {
        if (table.length() < table.getValidCount() + 1) {
            return;
        }
        for (int i = table.getValidCount(); rowIndex < i; i--) {
            EZDMsg.copy(table.no(i - 1), null, table.no(i), null);
        }
        table.no(rowIndex).clear();
        table.setValidCount(table.getValidCount() + 1);
    }

    /**
     * sMsg remove row
     * @param table NPAL1280_ASMsgArray
     * @param index int
     */
    public static void removeRow(NPAL1280_ASMsgArray table, int index) {
        if (index < 0) {
            return;
        }
        table.no(index).clear();
        for (int i = index; i < table.getValidCount(); i++) {
            EZDMsg.copy(table.no(i + 1), null, table.no(i), null);
        }
        table.setValidCount(table.getValidCount() - 1);
    }

    /**
     * convMdseCd
     * @param glblCmpyCd
     * @param mdseCd
     * @return mdseCd(10 digit)
     */
    private static String convMdseCd(String glblCmpyCd, String mdseCd) {
        if (mdseCd.length() > MDESE_8_DIGIT) {
            return mdseCd;
        } else {
            ORD_TAKE_MDSETMsg otm = new ORD_TAKE_MDSETMsg();
            ZYPEZDItemValueSetter.setValue(otm.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(otm.ordTakeMdseCd, mdseCd);

            ORD_TAKE_MDSETMsg result = (ORD_TAKE_MDSETMsg) EZDTBLAccessor.findByKey(otm);

            if (result != null) {
                return result.mdseCd.getValue();
            } else {
                return mdseCd;
            }
        }
    }

    /**
     * recursiveComponent
     * @param glblCmpyCd
     * @param salesDate
     * @param prchReqLineTpCd
     * @param compList
     * @return
     */
    private static List<Map<String, Object>> recursiveComponent(String glblCmpyCd, String salesDate, String prchReqLineTpCd, List<Map<String, Object>> compList) {

        ArrayList<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> map : compList) {

            String mdseCd = (String) map.get(RS_MDSE_CD);

            // get set item
            S21SsmEZDResult cmpResult = NPAL1280Query.getInstance().getComponent(glblCmpyCd//
                    , mdseCd //
                    , salesDate //
                    , prchReqLineTpCd);
            if (cmpResult.isCodeNormal()) {
                List<Map<String, Object>> comp //
                = recursiveComponent(glblCmpyCd//
                        , salesDate//
                        , prchReqLineTpCd//
                        , (List<Map<String, Object>>) cmpResult.getResultObject());
                for (Map<String, Object> mMap : comp) {
                    result.add(mMap);
                }
            } else {
                result.add(map);
            }
        }

        return result;
    }

    /**
     * getPoMsg
     * @param glblCmpyCd String
     * @param poMsgPk BigDecimal
     * @return PO_MSGTMsg
     */
    public static PO_MSGTMsg getPoMsg(String glblCmpyCd, BigDecimal poMsgPk) {
        PO_MSGTMsg inMsg = new PO_MSGTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poMsgPk, poMsgPk);
        return (PO_MSGTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    /**
     * updatePoMsg
     * @param cMsg NPAL1280CMsg
     * @param inMsg PO_MSGTMsg
     * @param poMsgTxt String
     */
    public static void updatePoMsg(NPAL1280CMsg cMsg, PO_MSGTMsg inMsg, String poMsgTxt) {
        ZYPEZDItemValueSetter.setValue(inMsg.poMsgTxt, poMsgTxt);
        EZDTBLAccessor.update(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            cMsg.setMessageInfo(NPAM1172E, new String[] {PO_MSG });
        }
    }

    /**
     * insertPoMsg
     * @param cMsg NPAL1280CMsg
     * @param glblCmpyCd String
     * @param msgInfo NPAL1280_RSMsg
     * @param segId int
     * @param poMsgTxt String
     * @param sMsg NPAL1280SMsg
     */
    public static void insertPoMsg(NPAL1280CMsg cMsg, String glblCmpyCd, NPAL1280_RSMsg msgInfo, int segId, String poMsgTxt, NPAL1280SMsg sMsg) {
        PO_MSGTMsg inMsg = new PO_MSGTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poMsgPk, msgInfo.poMsgPk);
        inMsg.poOrdNum.clear();
        inMsg.poOrdDtlLineNum.clear();
        ZYPEZDItemValueSetter.setValue(inMsg.poMsgTpCd, PO_MSG_TP.INTERNAL_PO_MESSAGE);
        ZYPEZDItemValueSetter.setValue(inMsg.poMsgSegId, new BigDecimal(segId));
        ZYPEZDItemValueSetter.setValue(inMsg.poMsgSubmtPsnCd, msgInfo.poMsgSubmtPsnCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poMsgTxt, poMsgTxt);
        ZYPEZDItemValueSetter.setValue(inMsg.prchReqNum, cMsg.prchReqNum);
        inMsg.prchReqLineNum.clear();
        inMsg.prchReqLineSubNum.clear();
        EZDTBLAccessor.insert(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            cMsg.setMessageInfo(NPAM1172E, new String[] {PO_MSG });
        }
    }

    /**
     * relocationLineNumber
     * @param glblCmpyCd String
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     */
    public static void relocationLineNumber(String glblCmpyCd, NPAL1280CMsg cMsg, NPAL1280SMsg sMsg) {

        int childLine = 1;
        for (int index = 0; index < sMsg.A.getValidCount(); index++) {
            PRCH_REQ_DTLTMsg prdTMsg = NPAL1280CommonLogic.findPrchReqDtlByKey(sMsg, glblCmpyCd, index, cMsg);

            if (prdTMsg == null) {
                if (PO_MDSE_CMPSN_TP.CHILD.equals(sMsg.A.no(index).poMdseCmpsnTpCd_A1.getValue())) {
                    String number = sMsg.A.no(index - 1).xxLineNum_A1.getValue().substring(0, IDX_3);
                    String childNumber = number + "." + String.valueOf(childLine);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxLineNum_A1, childNumber);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineNum_A1, number);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineSubNum_A1, new BigDecimal(childLine));
                    childLine++;
                } else {
                    // Regular or Parent
                    if (index == 0) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxLineNum_A1, FIRST_DTL_LINE_NUM);
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineNum_A1, FIRST_DTL_LINE_NUM);
                    } else {
                        String number = sMsg.A.no(index - 1).xxLineNum_A1.getValue().substring(0, IDX_3);
                        String nextLineNum = addDtlLineNum(number);
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxLineNum_A1, nextLineNum); // view
                        // only
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineNum_A1, nextLineNum);
                    }

                    // init
                    childLine = 1;
                }
            }

        }
    }

    /**
     * find PRCH_REQ_DTL Table record
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param index int
     * @param cMsg NPAL1280CMsg
     * @return PRCH_REQ_DTLTMsg
     */
    public static PRCH_REQ_DTLTMsg findPrchReqDtlByKey(NPAL1280SMsg sMsg, String glblCmpyCd, int index, NPAL1280CMsg cMsg) {

        PRCH_REQ_DTLTMsg prdTMsg = new PRCH_REQ_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(prdTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prdTMsg.prchReqNum, cMsg.prchReqNum);
        ZYPEZDItemValueSetter.setValue(prdTMsg.prchReqLineNum, sMsg.A.no(index).prchReqLineNum_A1);
        ZYPEZDItemValueSetter.setValue(prdTMsg.prchReqLineSubNum, sMsg.A.no(index).prchReqLineSubNum_A1);
        ZYPEZDItemValueSetter.setValue(prdTMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);

        prdTMsg = (PRCH_REQ_DTLTMsg) EZDTBLAccessor.findByKey(prdTMsg);
        if (prdTMsg == null) {
            return null;
        }
        return prdTMsg;
    }

    /**
     * Customer drop ship check
     * @param glblCmpyCd String
     * @param poQlufCd String
     * @return true:Drop ship / false:Not Drop ship
     */
    public static boolean isCustDropShipPoQulf(String glblCmpyCd, String poQlufCd) {
        if (ZYPCommonFunc.hasValue(poQlufCd)) {
            if (poQlufCd.equals(ZYPCodeDataUtil.getVarCharConstValue(CONST_CUST_DROP_SHIP_PO_QULF, glblCmpyCd))) {
                // Drop ship
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * fill Detail Items
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param index int
     * @return true OK / false Error
     */
    public static boolean fillDetailItems(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, int index) {
        boolean successCheckFlg = true;
        // fill unit price
        if (!ZYPCommonFunc.hasValue(sMsg.A.no(index).entDealNetUnitPrcAmt_A1)) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).entDealNetUnitPrcAmt_A1, BigDecimal.ZERO);
        }

        // fill supplier site
        if (!ZYPCommonFunc.hasValue(sMsg.A.no(index).locNm_A1)) {
            //QC#28216
            if (ZYPCommonFunc.hasValue(cMsg.locNm) && !MULTIPLE.equals(cMsg.locNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).locNm_A1, cMsg.locNm);
            } else if (ZYPCommonFunc.hasValue(cMsg.vndCd) && !MULTIPLE.equals(cMsg.vndCd.getValue())) {
                findPoVndV(sMsg, glblCmpyCd, cMsg.vndCd.getValue(), index);
            } else {
                // Error
                sMsg.A.no(index).locNm_A1.setErrorInfo(1, ZZM9000E, new String[] {SITE_D });
                successCheckFlg = false;
            }
        }

        // fill supplier. Mod QC#28216
        if (!ZYPCommonFunc.hasValue(sMsg.A.no(index).prntVndNm_A1) && !MULTIPLE.equals(cMsg.prntVndNm.getValue())) {
            if (ZYPCommonFunc.hasValue(cMsg.prntVndNm) && !MULTIPLE.equals(cMsg.prntVndNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prntVndNm_A1, cMsg.prntVndNm);
            } else {
                sMsg.A.no(index).prntVndNm_A1.setErrorInfo(1, ZZM9000E, new String[] {SUPPLIER_D });
                successCheckFlg = false;
            }
        } else if (!ZYPCommonFunc.hasValue(sMsg.A.no(index).prntVndNm_A1) && MULTIPLE.equals(cMsg.prntVndNm.getValue())) {
            sMsg.A.no(index).prntVndNm_A1.setErrorInfo(1, ZZM9000E, new String[] {SUPPLIER_D });
            successCheckFlg = false;
        }

        // QC#24918
        String[] materialParts = ZYPCodeDataUtil.getVarCharConstValue(VAR_CONST_CREATE_MATERIAL_PARTS, glblCmpyCd).split(",");
        if (materialParts != null) {

            if (Arrays.asList(materialParts).contains(sMsg.A.no(index).mdseCd_A1.getValue())) {

                if (!ZYPCommonFunc.hasValue(sMsg.A.no(index).prchReqLineCmntTxt_A1)) {
                    sMsg.A.no(index).prchReqLineCmntTxt_A1.setErrorInfo(1, NPAM1579E, new String[] {"Line Comment" });
                    successCheckFlg = false;
                }
            }
        }

        return successCheckFlg;
    }

    /**
     * Is technician warehouse
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return true:Technician warehouse / false: Not Technician
     * warehouse
     */
    private static boolean isTechnicianWh(String glblCmpyCd, String rtlWhCd) {

        S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().chkTechnicianWh(glblCmpyCd, rtlWhCd);

        if (ssmResult.getQueryResultCount() > 0) {
            // Technician warehouse
            return true;
        } else {
            // Not Technician warehouse
            return false;
        }
    }

    /**
     * check PR Approval Status for Auto PO Button
     * @param cMsg NPAL1280CMsg
     * @param glblCmpyCd String
     * @param lineIndex int
     * @return boolean
     */
    public static boolean checkPrApvlStatus(NPAL1280CMsg cMsg, String glblCmpyCd, int lineIndex) {

        S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().checkPrApvlStatus(cMsg, glblCmpyCd, lineIndex);

        if (ssmResult.isCodeNormal()) {

            return true;
        } else {
            // PR is NOT in approved status
            return false;
        }
    }

        /**
         * checkManualSegmentElementForSMsg
         * @param cMsg NPAL1280CMsg
         * @param sMsg NPAL1280SMsg
         * @param glblCmpyCd String
         * @param rowIndex int
         * @return boolean
         */
        public static boolean checkManualSegmentElementForSMsg(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, int rowIndex) {


            List<EZDSStringItem> checkTarget = Arrays.asList(sMsg.A.no(rowIndex).xxScrItem130Txt_A1);
            for (EZDSStringItem target : checkTarget) {

                if (ZYPCommonFunc.hasValue(target)) {
                    if ((target.getValue().equals(sMsg.A.no(rowIndex).xxScrItem130Txt_A1.getValue()))
                            && !(PRCH_REQ_LINE_TP.EXPENSE.equals(sMsg.A.no(rowIndex).prchReqLineTpCd_A1.getValue())
                            || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(sMsg.A.no(rowIndex).prchReqLineTpCd_A1.getValue()))) {
                        // Skip segment check unless PRCH_REQ_LINE_TP is "Expense" or "Expense w/z receipt"
                        continue;
                    }
                } else {
                    continue;
                }

                // Length Check
                List<String> tokenList = tokenListSplit(target.getValue(), glblCmpyCd);
                if (tokenList == null || tokenList.size() != SEGMENT_TOKEN_LIST_SIZE) {
                    target.setErrorInfo(1, NPAM1193E, new String[] {MSG_PARAM_SEGMENT });
                    return false;
                }

                if (!ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD))) {
                    target.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_CMPY });
                    return false;
                }
                if (!ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD))) {
                    target.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_EXTN });
                    return false;
                }
                if (!ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD))) {
                    target.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_CC });
                    return false;
                }
                if (!ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD))) {
                    target.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_ACCT });
                    return false;
                }
                if (!ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD))) {
                    target.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_PROJ });
                    return false;
                }
                if (!ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD))) {
                    target.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_PROD });
                    return false;
                }
                if (!ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD))) {
                    target.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_AFFL });
                    return false;
                }
                if (!ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD))) {
                    target.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_CH });
                    return false;
                }
                if (!ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD))) {
                    target.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_BR });
                    return false;
                }

                // coaCmpyCd check
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD), SEGMENT_ELEMENT_LENGTH_CMPY)) {
                    target.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_CMPY });
                    return false;
                }

                // coaExtnCd check
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD), SEGMENT_ELEMENT_LENGTH_EXTN)) {
                    target.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_EXTN });
                    return false;
                }

                // coaCcCd check
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD), SEGMENT_ELEMENT_LENGTH_CC)) {
                    target.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_CC });
                    return false;
                }

                // coaAcctCd check
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD), SEGMENT_ELEMENT_LENGTH_ACCT)) {
                    target.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_ACCT });
                    return false;
                }

                // coaProjCd check
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD), SEGMENT_ELEMENT_LENGTH_PROJ)) {
                    target.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_PROJ });
                    return false;
                }

                // coaProdCd check
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD), SEGMENT_ELEMENT_LENGTH_PROD)) {
                    target.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_PROD });
                    return false;
                }

                // coaAfflCd check
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD), SEGMENT_ELEMENT_LENGTH_AFFL)) {
                    target.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_AFFL });
                    return false;
                }

                // coaChCd check
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD), SEGMENT_ELEMENT_LENGTH_CH)) {
                    target.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_CH });
                    return false;
                }

                // coaBrCd check
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD), SEGMENT_ELEMENT_LENGTH_BR)) {
                    target.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_BR });
                    return false;
                }

                // GL Code Combination Check API NFZC102001
                NFZC102001 afzc102001 = new NFZC102001();
                NFZC102001PMsg paramMsg = new NFZC102001PMsg();

                paramMsg.glblCmpyCd.setValue(glblCmpyCd);
                paramMsg.xxMstChkFlg.setValue(ZYPConstant.FLG_ON_Y);
                paramMsg.xxGlCmbnChkFlg.setValue(ZYPConstant.FLG_ON_Y);
                paramMsg.xxArcsApiChkFlg.setValue("");
                paramMsg.coaCmpyCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
                paramMsg.coaAfflCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
                paramMsg.coaBrCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
                paramMsg.coaCcCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
                paramMsg.coaAcctCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
                paramMsg.coaProdCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
                paramMsg.coaChCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
                paramMsg.coaProjCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
                paramMsg.coaExtnCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));

                // QC#19433 Start
                ZYPEZDItemValueSetter.setValue(paramMsg.resrcObjNm, BUSINESS_APPL_ID + sMsg.A.no(rowIndex).prchReqLineTpCd_A1.getValue());
                // QC#19433 End
                afzc102001.execute(paramMsg, ONBATCH_TYPE.ONLINE);

                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(paramMsg);
                for (int i = 0; i < msgList.size(); i++) {
                    S21ApiMessage msg = msgList.get(i);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();

                    if (msgPrms != null && msgPrms.length > 0) {

                        if (msgPrms[0].equals(DB_COLUMN_COA_CMPY_CD)) {
                            target.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CMPY });
                        }
                        if (msgPrms[0].equals(DB_COLUMN_COA_BR_CD)) {
                            target.setErrorInfo(1, msgId, new String[] {MSG_PARAM_BR });
                        }
                        if (msgPrms[0].equals(DB_COLUMN_COA_CC_CD)) {
                            target.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CC });
                        }
                        if (msgPrms[0].equals(DB_COLUMN_COA_ACCT_CD)) {
                            target.setErrorInfo(1, msgId, new String[] {MSG_PARAM_ACCT });
                        }
                        if (msgPrms[0].equals(DB_COLUMN_COA_PROD_CD)) {
                            target.setErrorInfo(1, msgId, new String[] {MSG_PARAM_PROD });
                        }
                        if (msgPrms[0].equals(DB_COLUMN_COA_CH_CD)) {
                            target.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CH });
                        }
                        if (msgPrms[0].equals(DB_COLUMN_COA_AFFL_CD)) {
                            target.setErrorInfo(1, msgId, new String[] {MSG_PARAM_AFFL });
                        }
                        if (msgPrms[0].equals(DB_COLUMN_COA_PROJ_CD)) {
                            target.setErrorInfo(1, msgId, new String[] {MSG_PARAM_PROJ });
                        }
                        if (msgPrms[0].equals(DB_COLUMN_COA_EXTN_CD)) {
                            target.setErrorInfo(1, msgId, new String[] {MSG_PARAM_EXTN });
                        } else {
                            target.setErrorInfo(1, msgId, new String[] {msgPrms[0] });
                        }

                    } else {
                        // QC#30768
                        target.setErrorInfo(1, msgId, null);
                        cMsg.setMessageInfo(msgId);
                    }

                    return false;
                }

                if (target == sMsg.A.no(rowIndex).xxScrItem130Txt_A1) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaCmpyCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaAfflCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaBrCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaCcCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaAcctCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaProdCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaChCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaProjCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaExtnCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));
                }
            }
            return true;
        }

        /**
         * tokenListSplit
         * @param token String
         * @param glblCmpyCd String
         * @return tokenList List<String>
         */
        private static List<String> tokenListSplit(String token, String glblCmpyCd) {
            String delimiter = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_KEY_DELIMITER, glblCmpyCd);

            if (!hasValue(token)) {
                return new ArrayList<String>();
            }
            String[] list = token.split(Pattern.quote(delimiter), -1);
            List<String> tokenList = new ArrayList<String>();
            for (String val : list) {
                tokenList.add(val);
            }

            return tokenList;
        }

        /**
         * validateSegmentElement
         * @param element String
         * @param len int
         * @return boolean
         */
        private static boolean validateSegmentElement(String element, int len) {
            if (!hasValue(element)) {
                return true;
            }
            if (element.length() > len) {
                return false;
            }
            return true;
        }

    /**
     * <pre>
     * isSvcLvlWhOwnr
     * Set Service Level to Ground if target WH is included in specified WH owners.
     * Condition:
     * + Service Level is not specified
     * + Req Type is "Standard PO"
     * + Target WH's owner is in "PR_SVC_LVL_WH_OWNR_CD"
     * </pre>
     * @param glblCmpyCd String
     * @param cMsg NPAL1280CMsg
     * @return boolean
     */
    public static boolean isSvcLvlWhOwnr(String glblCmpyCd, NPAL1280CMsg cMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.shpgSvcLvlCd_SL)) {
            return false;
        }
        if (!PRCH_REQ_TP.STANDARD_2.equals(cMsg.prchReqTpCd_SL.getValue())) {
            return false;
        }

        String prShpgSvcWhOwnrCd = ZYPCodeDataUtil.getVarCharConstValue(NPAL1280Constant.VAR_CHAR_PR_SVC_LVL_WH_OWNR_CD, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(prShpgSvcWhOwnrCd)) {
            return false;
        }

        String rtlWhCd = cMsg.destRtlWhCd.getValue();
        String[] whOwnrCdArray = prShpgSvcWhOwnrCd.split(",");
        for (int n = 0; n < whOwnrCdArray.length; ++n) {
            if (ZYPCommonFunc.hasValue(whOwnrCdArray[n])) {
                whOwnrCdArray[n] = whOwnrCdArray[n].trim();
            }
        }

        int count = NPAL1280Query.getInstance().countRtlWhInWhOwnr(glblCmpyCd, rtlWhCd, whOwnrCdArray);
        return (count > 0);
    }
    
    /**QC#18671 Add.
     * chkMdseDuplicateFromAsl
     * @param cMsg
     * @param sMsg
     * @param glblCmpyCd
     * @param salesDate
     * @param lineIndex
     * @return
     */
    public static boolean chkMdseDuplicateFromAsl(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, String salesDate, int lineIndex) {

        // QC#28939 Add check.
        if (isExpenceTextItem(sMsg, glblCmpyCd, lineIndex)) {
            // Skip.
            return true;
        }

        if (ZYPCommonFunc.hasValue(sMsg.A.no(lineIndex).vndCd_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(lineIndex).mdseCd_A1)) {

            // VND check
            RCV_ASN_VNDTMsg tMsg = new RCV_ASN_VNDTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.rcvAsnVndCd, sMsg.A.no(lineIndex).vndCd_A1);
            tMsg = (RCV_ASN_VNDTMsg) EZDTBLAccessor.findByKey(tMsg);
            if (!(tMsg != null && EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode()))) {
                return true;
            }
            if (!VND_SYS_TP.PARTS.equals(tMsg.vndSysTpCd.getValue())) {
                // Skip checking unless CUSA Parts
                return true;
            }

            S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().getMdseFromSupplierItem(sMsg, glblCmpyCd, salesDate, lineIndex);
            if (!ssmResult.isCodeNormal()) {
                // No Check.
                return true;
            }
            List<Map<String, String>> mdseMapList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (Map<String, String> mdseMap : mdseMapList) {
                for (int i = 0; sMsg.A.getValidCount() > i; i++) {
                    if (!PRCH_REQ_LINE_STS.CANCELLED.equals(sMsg.A.no(i).prchReqLineStsCd_HD.getValue())
                            && mdseMap.get("MDSE_CD").equals(sMsg.A.no(i).mdseCd_A1.getValue())
                            && (mdseMap.get("VND_CD").equals(sMsg.A.no(i).vndCd_A1.getValue()))) {

                        // Duplicated Error.
                        sMsg.A.no(lineIndex).mdseCd_A1.setErrorInfo(1, NPAM1602E, new String[] {sMsg.A.no(i).mdseCd_A1.getValue() });
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    /**
     * getDefaultAccount
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param index int
     * @return boolean
     */
    public static void getDefaultAccount(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, int index) {

        // Index of SMsg Line
        int pageShowFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int indexS = pageShowFromNum + index;
        NPAL1280_ASMsg curMsg=sMsg.A.no(indexS);

        // COA will be blank if PRCH_REQ_LINE_TP is not Expense.
        if (!(ZYPCommonFunc.hasValue(curMsg.prchReqLineTpCd_A1) //
                && (PRCH_REQ_LINE_TP.EXPENSE.equals(curMsg.prchReqLineTpCd_A1.getValue()) // 
                || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(curMsg.prchReqLineTpCd_A1.getValue())))) {

            curMsg.coaCmpyCd_A1.clear();
            curMsg.coaBrCd_A1.clear();
            curMsg.coaCcCd_A1.clear();
            curMsg.coaAcctCd_A1.clear();
            curMsg.coaProdCd_A1.clear();
            curMsg.coaChCd_A1.clear();
            curMsg.coaAfflCd_A1.clear();
            curMsg.coaProjCd_A1.clear();
            curMsg.coaExtnCd_A1.clear();
            curMsg.xxScrItem130Txt_A1.clear();
            return;
        }

        // Find previous Expense record - used below.
        int last=indexS-1;
        for(; last>=0; --last) {
            if(ZYPCommonFunc.hasValue(sMsg.A.no(last).prchReqLineTpCd_A1) //
                    && (PRCH_REQ_LINE_TP.EXPENSE.equals(sMsg.A.no(last).prchReqLineTpCd_A1.getValue()) // 
                    || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(sMsg.A.no(last).prchReqLineTpCd_A1.getValue()))) {
                break;
            }
        }

        if(last>=0) {
            // Copy account from previous Expense record if exists.
            NPAL1280_ASMsg lastMsg=sMsg.A.no(last);

            // START 03/19/2020 [QC#56122,MOD]
//            applyChrgAccount(lastMsg, lastMsg.xxScrItem130Txt_A1.getValue());
            if (!applyChrgAccount(lastMsg, lastMsg.xxScrItem130Txt_A1.getValue())) {
                return;
            }
            // END 03/19/2020 [QC#56122,MOD]

            ZYPEZDItemValueSetter.setValue(curMsg.coaCmpyCd_A1, lastMsg.coaCmpyCd_A1);
            ZYPEZDItemValueSetter.setValue(curMsg.coaBrCd_A1, lastMsg.coaBrCd_A1);
            ZYPEZDItemValueSetter.setValue(curMsg.coaCcCd_A1, lastMsg.coaCcCd_A1);
            ZYPEZDItemValueSetter.setValue(curMsg.coaAcctCd_A1, lastMsg.coaAcctCd_A1);
            ZYPEZDItemValueSetter.setValue(curMsg.coaProdCd_A1, lastMsg.coaProdCd_A1);
            ZYPEZDItemValueSetter.setValue(curMsg.coaChCd_A1, lastMsg.coaChCd_A1);
            ZYPEZDItemValueSetter.setValue(curMsg.coaAfflCd_A1, lastMsg.coaAfflCd_A1);
            ZYPEZDItemValueSetter.setValue(curMsg.coaProjCd_A1, lastMsg.coaProjCd_A1);
            ZYPEZDItemValueSetter.setValue(curMsg.coaExtnCd_A1, lastMsg.coaExtnCd_A1);
            // 2019/01/15 QC#29778 Del Start
//            ZYPEZDItemValueSetter.setValue(curMsg.xxScrItem130Txt_A1, lastMsg.xxScrItem130Txt_A1);
            // 2019/01/15 QC#29778 Del End
            // 2019/01/15 QC#29778 Add Start -> 2019/02/08 QC#30181 Change Call Method, and parameters
            NPXC001001GetDefNineSegDataBean defNineSegDataBean = NPXC001001GetDefNineSegData.getInstance().getDefNineSegData( //
                    glblCmpyCd //
                    , null
                    , curMsg.mdseCd_A1.getValue()
                    , lastMsg.xxScrItem130Txt_A1.getValue());
            if (defNineSegDataBean != null) {
                // 2019/01/28 QC#29778-2 Mod Start
//                if (ZYPCommonFunc.hasValue(defNineSegDataBean.getCoaProjCd())) {
//                    ZYPEZDItemValueSetter.setValue(curMsg.coaProjCd_A1, defNineSegDataBean.getCoaProjCd());
//                }
//                if (ZYPCommonFunc.hasValue(defNineSegDataBean.getCoaProdCd())) {
//                    ZYPEZDItemValueSetter.setValue(curMsg.coaProdCd_A1, defNineSegDataBean.getCoaProdCd());
//                }
                ZYPEZDItemValueSetter.setValue(curMsg.coaProjCd_A1, getValidStr(curMsg.coaProjCd_A1, defNineSegDataBean.getCoaProjCd()));
                ZYPEZDItemValueSetter.setValue(curMsg.coaProdCd_A1, getValidStr(curMsg.coaProdCd_A1, defNineSegDataBean.getCoaProdCd()));
                // 2019/01/28 QC#29778-2 Mod End
            }
            fillChrgAccountText(curMsg);
            // 2019/01/15 QC#29778 Add End
        } else {
            applyChrgAccount(curMsg, curMsg.xxScrItem130Txt_A1.getValue());

            ZYPEZDItemValueSetter.setValue(curMsg.coaCmpyCd_A1, glblCmpyCd);

            // 2018/12/17 QC#29456 Del Start
//            // Create account from header values.
//            S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().getDefAccInfo(glblCmpyCd, cMsg);
//            Map<String, String> result = null;
//            if(ssmResult.isCodeNormal()) {
//                result = (Map<String, String>)ssmResult.getResultObject();
//            }
//            if(result==null) {
//                //curMsg.coaCmpyCd_A1.clear();
//                curMsg.coaBrCd_A1.clear();
//                curMsg.coaCcCd_A1.clear();
//                curMsg.coaAcctCd_A1.clear();
//                curMsg.coaProdCd_A1.clear();
//                curMsg.coaChCd_A1.clear();
//                curMsg.coaAfflCd_A1.clear();
//                curMsg.coaProjCd_A1.clear();
//                curMsg.coaExtnCd_A1.clear();
//                curMsg.xxScrItem130Txt_A1.clear();
//                fillChrgAccountText(curMsg);
//                return;
//            }
//            
//            ZYPEZDItemValueSetter.setValue(curMsg.coaBrCd_A1, result.get("COA_BR_CD")); //Branch
//            ZYPEZDItemValueSetter.setValue(curMsg.coaCcCd_A1, result.get("COA_CC_CD"));
//            ZYPEZDItemValueSetter.setValue(curMsg.coaAfflCd_A1, result.get("COA_AFFL_CD"));
            // 2018/12/17 QC#29456 Del End
            // 2018/12/17 QC#29456 Add Start
            // 2019/01/28 QC#29778-2 Mod Start
//            setDefaultChargAccount(curMsg, glblCmpyCd, cMsg.shipToCustCd.getValue());
            setDefaultChargAccount(cMsg, curMsg, glblCmpyCd); // 2019/02/08 QC#30181 Parameter Change.
            // 2019/01/28 QC#29778-2 Mod End
            // 2018/12/17 QC#29456 Add End
        }
        // 2018/12/17 QC#29456 Del Start
//        // Fill Account from MDSE if specified.
//        getMdseAccount(cMsg, sMsg, glblCmpyCd, index);
//        // Set xxScrItem130Txt_A1
//        fillChrgAccountText(curMsg);
        // 2018/12/17 QC#29456 Del End
    }

    // 2018/12/17 QC#29456 Del Start
//    /**
//     * getMdseAccount
//     * @param cMsg
//     * @param sMsg
//     * @param glblCmpyCd
//     * @param index
//     */
//    public static void getMdseAccount(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, int index) {
//
//        // Index of SMsg Line
//        int pageShowFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
//        int indexS = pageShowFromNum + index;
//        NPAL1280_ASMsg curMsg=sMsg.A.no(indexS);
//
//        // COA will be blank if PRCH_REQ_LINE_TP is not Expense.
//        if (!(ZYPCommonFunc.hasValue(curMsg.prchReqLineTpCd_A1) //
//        && (PRCH_REQ_LINE_TP.EXPENSE.equals(curMsg.prchReqLineTpCd_A1.getValue()) // 
//        || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(curMsg.prchReqLineTpCd_A1.getValue())))) {
//
//            curMsg.coaCmpyCd_A1.clear();
//            curMsg.coaBrCd_A1.clear();
//            curMsg.coaCcCd_A1.clear();
//            curMsg.coaAcctCd_A1.clear();
//            curMsg.coaProdCd_A1.clear();
//            curMsg.coaChCd_A1.clear();
//            curMsg.coaAfflCd_A1.clear();
//            curMsg.coaProjCd_A1.clear();
//            curMsg.coaExtnCd_A1.clear();
//            curMsg.xxScrItem130Txt_A1.clear();
//            return;
//        }
//
//        // Skip if MDSE is blank.
//        if (!ZYPCommonFunc.hasValue(curMsg.mdseCd_A1)) {
//            return;
//        }
//
//        // QC#27770 MOD START
//        if (curMsg.xxScrItem130Txt_A1.isError()) {
//            return;
//        }
//        // QC#27770 MOD END
//
//        applyChrgAccount(curMsg, curMsg.xxScrItem130Txt_A1.getValue());
//        
//        MDSETMsg tMsg = new MDSETMsg();
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(tMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);
//        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, curMsg.mdseCd_A1);
//
//        tMsg = (MDSETMsg) EZDTBLAccessor.findByKey(tMsg);
//
//        if (tMsg == null) {
//            //curMsg.coaCmpyCd_A1.clear();
//            //curMsg.coaBrCd_A1.clear();
//            //curMsg.coaCcCd_A1.clear();
//            curMsg.coaAcctCd_A1.clear();
//            curMsg.coaProdCd_A1.clear();
//            curMsg.coaChCd_A1.clear();
//            //curMsg.coaAfflCd_A1.clear();
//            curMsg.coaProjCd_A1.clear();
//            //curMsg.coaExtnCd_A1.clear();
//            curMsg.xxScrItem130Txt_A1.clear();
//            fillChrgAccountText(curMsg);
//            return;
//        }
//
//        // See QC#25019 for filling rules from MDSE.
//        ZYPEZDItemValueSetter.setValue(curMsg.coaAcctCd_A1, tMsg.cogsCoaAcctCd.getValue());
//        if (!ZYPCommonFunc.hasValue(tMsg.cogsCoaAcctCd)) {
//        } else if (tMsg.cogsCoaAcctCd.getValue().startsWith("5")) {
//            ZYPEZDItemValueSetter.setValue(curMsg.coaProdCd_A1, tMsg.coaProdCd.getValue());
//            ZYPEZDItemValueSetter.setValue(curMsg.coaChCd_A1, COA_CH.PRODUCT_ADMINI); // Channel
//            // ZYPEZDItemValueSetter.setValue(curMsg.coaAfflCd_A1, ); //Intercompany(manual)
//            ZYPEZDItemValueSetter.setValue(curMsg.coaProjCd_A1, tMsg.coaMdseTpCd.getValue()); // MDSE_TP
//            // ZYPEZDItemValueSetter.setValue(curMsg.coaExtnCd_A1, ); //Business Unit(manual)
//        } else if (tMsg.cogsCoaAcctCd.getValue().startsWith("6")) {
//            ZYPEZDItemValueSetter.setValue(curMsg.coaProdCd_A1, COA_PROD.ADMINISTRATION);
//            ZYPEZDItemValueSetter.setValue(curMsg.coaChCd_A1, COA_CH.CORPORATE_ADMINI);
//            // ZYPEZDItemValueSetter.setValue(curMsg.coaAfflCd_A1, ); //Intercompany(manual)
//            ZYPEZDItemValueSetter.setValue(curMsg.coaProjCd_A1, COA_PROJ.DEFAULT);
//            // ZYPEZDItemValueSetter.setValue(curMsg.coaExtnCd_A1, ); //Business Unit(manual)
//        }
//
//        // Set xxScrItem130Txt_A1
//        fillChrgAccountText(curMsg);
//    }
    // 2018/12/17 QC#29456 Del End

    /**
     * fillAccountText Set xxScrItem130Txt_A1
     * @param asMsg
     */
    public static void fillChrgAccountText(NPAL1280_ASMsg asMsg) {
        StringBuilder sb = new StringBuilder();
        sb.append(asMsg.coaCmpyCd_A1.getValue());
        sb.append(".");
        sb.append(asMsg.coaBrCd_A1.getValue());
        sb.append(".");
        sb.append(asMsg.coaCcCd_A1.getValue());
        sb.append(".");
        sb.append(asMsg.coaAcctCd_A1.getValue());
        sb.append(".");
        sb.append(asMsg.coaProdCd_A1.getValue());
        sb.append(".");
        sb.append(asMsg.coaChCd_A1.getValue());
        sb.append(".");
        sb.append(asMsg.coaAfflCd_A1.getValue());
        sb.append(".");
        sb.append(asMsg.coaProjCd_A1.getValue());
        sb.append(".");
        sb.append(asMsg.coaExtnCd_A1.getValue());

        ZYPEZDItemValueSetter.setValue(asMsg.xxScrItem130Txt_A1, sb.toString());
    }

    /**
     * Apply to Charge Account fields from 9 Segment
     * @param asMsg
     * @param chrgAcctText
     */
    // START 03/19/2020 [QC#56122,MOD]
//    private static void applyChrgAccount(NPAL1280_ASMsg asMsg, String chrgAcctText) {
    private static boolean applyChrgAccount(NPAL1280_ASMsg asMsg, String chrgAcctText) {
        if (!ZYPCommonFunc.hasValue(chrgAcctText)) {
//            return;
            return false;
        }
        String[] values = chrgAcctText.split(Pattern.quote(DOT));

//        if (values == null || values.length <= 0) {
//            return;
//        }
        if (values.length != SEGMENT_TOKEN_LIST_SIZE) {
            asMsg.xxScrItem130Txt_A1.setErrorInfo(1, NPAM1193E, new String[] {MSG_PARAM_SEGMENT });
            return false;
        } else {
            // coaCmpyCd check
            if (!validateSegmentElement(values[SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD], SEGMENT_ELEMENT_LENGTH_CMPY)) {
                asMsg.xxScrItem130Txt_A1.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_CMPY });
                return false;
            }

            // coaExtnCd check
            if (!validateSegmentElement(values[SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD], SEGMENT_ELEMENT_LENGTH_EXTN)) {
                asMsg.xxScrItem130Txt_A1.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_EXTN });
                return false;
            }

            // coaCcCd check
            if (!validateSegmentElement(values[SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD], SEGMENT_ELEMENT_LENGTH_CC)) {
                asMsg.xxScrItem130Txt_A1.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_CC });
                return false;
            }

            // coaAcctCd check
            if (!validateSegmentElement(values[SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD], SEGMENT_ELEMENT_LENGTH_ACCT)) {
                asMsg.xxScrItem130Txt_A1.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_ACCT });
                return false;
            }

            // coaProjCd check
            if (!validateSegmentElement(values[SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD], SEGMENT_ELEMENT_LENGTH_PROJ)) {
                asMsg.xxScrItem130Txt_A1.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_PROJ });
                return false;
            }

            // coaProdCd check
            if (!validateSegmentElement(values[SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD], SEGMENT_ELEMENT_LENGTH_PROD)) {
                asMsg.xxScrItem130Txt_A1.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_PROD });
                return false;
            }

                // coaAfflCd check
            if (!validateSegmentElement(values[SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD], SEGMENT_ELEMENT_LENGTH_AFFL)) {
                asMsg.xxScrItem130Txt_A1.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_AFFL });
                return false;
            }

                // coaChCd check
            if (!validateSegmentElement(values[SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD], SEGMENT_ELEMENT_LENGTH_CH)) {
                asMsg.xxScrItem130Txt_A1.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_CH });
                return false;
            }

            // coaBrCd check
            if (!validateSegmentElement(values[SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD], SEGMENT_ELEMENT_LENGTH_BR)) {
                asMsg.xxScrItem130Txt_A1.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_BR });
                return false;
            }
        }

        EZDSStringItem[] fields = new EZDSStringItem[] {asMsg.coaCmpyCd_A1, asMsg.coaBrCd_A1, asMsg.coaCcCd_A1, asMsg.coaAcctCd_A1, asMsg.coaProdCd_A1, asMsg.coaChCd_A1, asMsg.coaAfflCd_A1, asMsg.coaProjCd_A1, asMsg.coaExtnCd_A1 };
        for (int n = 0; n < fields.length; ++n) {
            fields[n].clear();
        }
        for (int n = 0; n < values.length; ++n) {
            String val = values[n];
            if (ZYPCommonFunc.hasValue(val)) {
                ZYPEZDItemValueSetter.setValue(fields[n], val);
            }
        }
        return true;
    }
    // END 03/19/2020 [QC#56122,MOD]

    // QC#21170 ADD Start
    /**
     * dtCompare
     * @param date1
     * @param date2
     * @return
     */
    private static boolean dtCompare (String date1, String date2) {
        if (ZYPCommonFunc.hasValue(date1) && ZYPCommonFunc.hasValue(date2)) {
            if (new BigDecimal(date1).compareTo(new BigDecimal(date2)) > 0 ) {
                return true;
            }
        }
        return false;
    }

    /**
     * getHeaderRqstRcvDt
     * @param cMsg
     * @param sMsg
     * @param salesDate
     */
    public static void getHeaderRqstRcvDt(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String salesDate ) {
        // get Max RqstRcvDt
        String maxDt = salesDate;
        for (int i=0; i<sMsg.A.getValidCount(); i++) {
            if(!ZYPCommonFunc.hasValue(sMsg.A.no(i).rqstRcvDt_A1) || PRCH_REQ_LINE_STS.CANCELLED.equals(sMsg.A.no(i).prchReqLineStsCd_HD.getValue())) {
                continue;
            }
            if(dtCompare(sMsg.A.no(i).rqstRcvDt_A1.getValue(), maxDt)) {
                maxDt = sMsg.A.no(i).rqstRcvDt_A1.getValue();
            }
        }
        ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, maxDt);
    }
    /**
     * setRqstRcvDt
     * @param sMsg
     * @param aslMap
     * @param glblCmpyCd
     * @param salesDate
     * @param lineIdx
     */
    private static void setRqstRcvDt(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, Map<String, Object> aslMap, String glblCmpyCd, String salesDate, int lineIdx) {

        String etaDt = salesDate;

        if (ZYPCommonFunc.hasValue((BigDecimal) aslMap.get(RS_VND_LT_DAYS_NUM))) {
            // START 2019/03/20 T.Ogura [QC#30769,MOD]
//            etaDt = ZYPDateUtil.addBusinessDay(glblCmpyCd, salesDate, ((BigDecimal) aslMap.get(RS_VND_LT_DAYS_NUM)).intValue());
            etaDt = ZYPDateUtil.addDays(salesDate, ((BigDecimal) aslMap.get(RS_VND_LT_DAYS_NUM)).intValue());
            // END   2019/03/20 T.Ogura [QC#30769,MOD]
        } else {
            if (ZYPCommonFunc.hasValue(cMsg.prchReqTpCd_SL)) {
                BigDecimal defPrchReqTpDaysNum = getDefPrchReqTpDaysNum(glblCmpyCd, cMsg.prchReqTpCd_SL.getValue());
                if (ZYPCommonFunc.hasValue(defPrchReqTpDaysNum)) {
                    // START 2019/03/20 T.Ogura [QC#30769,MOD]
//                    etaDt = ZYPDateUtil.addBusinessDay(glblCmpyCd, salesDate, defPrchReqTpDaysNum.intValue());
                    etaDt = ZYPDateUtil.addDays(salesDate, defPrchReqTpDaysNum.intValue());
                    // END   2019/03/20 T.Ogura [QC#30769,MOD]
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIdx).rqstRcvDt_A1, etaDt);
    }
    
    /**
     * setRqstRcvDt
     * QC#28216 Add method.
     * @param sMsg
     * @param aslMap
     * @param glblCmpyCd
     * @param salesDate
     * @param lineIdx
     */
    private static void setRqstRcvDt(NPAL1280SMsg sMsg, Map<String, Object> aslMap, String glblCmpyCd, String salesDate, int lineIdx) {

        String etaDt = salesDate;

        if (ZYPCommonFunc.hasValue((BigDecimal) aslMap.get(RS_VND_LT_DAYS_NUM))) {
            // START 2019/03/20 T.Ogura [QC#30769,MOD]
//            etaDt = ZYPDateUtil.addBusinessDay(glblCmpyCd, salesDate, ((BigDecimal) aslMap.get(RS_VND_LT_DAYS_NUM)).intValue());
            etaDt = ZYPDateUtil.addDays(salesDate, ((BigDecimal) aslMap.get(RS_VND_LT_DAYS_NUM)).intValue());
            // END   2019/03/20 T.Ogura [QC#30769,MOD]
        } else {
            if (ZYPCommonFunc.hasValue(sMsg.prchReqTpCd_SL)) {
                BigDecimal defPrchReqTpDaysNum = getDefPrchReqTpDaysNum(glblCmpyCd, sMsg.prchReqTpCd_SL.getValue());
                if (ZYPCommonFunc.hasValue(defPrchReqTpDaysNum)) {
                    // START 2019/03/20 T.Ogura [QC#30769,MOD]
//                    etaDt = ZYPDateUtil.addBusinessDay(glblCmpyCd, salesDate, defPrchReqTpDaysNum.intValue());
                    etaDt = ZYPDateUtil.addDays(salesDate, defPrchReqTpDaysNum.intValue());
                    // END   2019/03/20 T.Ogura [QC#30769,MOD]
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIdx).rqstRcvDt_A1, etaDt);
    }

    // START 2023/02/03 T.Kuroda [QC#60966, ADD]
    /**
     * getHeaderRqstShipDt
     * @param cMsg
     * @param sMsg
     * @param salesDate
     */
    public static void getHeaderRqstShipDt(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String salesDate ) {
        // get Max RqstShipDt
        String maxDt = salesDate;
        for (int i=0; i<sMsg.A.getValidCount(); i++) {
            if(!ZYPCommonFunc.hasValue(sMsg.A.no(i).rqstShipDt_A1) || PRCH_REQ_LINE_STS.CANCELLED.equals(sMsg.A.no(i).prchReqLineStsCd_HD.getValue())) {
                continue;
            }
            if(dtCompare(sMsg.A.no(i).rqstShipDt_A1.getValue(), maxDt)) {
                maxDt = sMsg.A.no(i).rqstShipDt_A1.getValue();
            }
        }
        ZYPEZDItemValueSetter.setValue(cMsg.rqstShipDt, maxDt);
    }

    /**
     * setRqstShipDt
     * @param cMsg
     * @param sMsg
     * @param aslMap
     * @param glblCmpyCd
     * @param lineIdx
     */
    private static void setRqstShipDt(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, Map<String, Object> aslMap, String glblCmpyCd, int lineIdx) {
        String etaDt = "";

        if (ZYPCommonFunc.hasValue((BigDecimal) aslMap.get(RS_VND_SHIP_LT_DAYS_NUM))
                && BigDecimal.ZERO.compareTo((BigDecimal) aslMap.get(RS_VND_SHIP_LT_DAYS_NUM)) < 0) {
            etaDt = ZYPDateUtil.addDays(cMsg.A.no(lineIdx).rqstRcvDt_A1.getValue(), ((BigDecimal) aslMap.get(RS_VND_SHIP_LT_DAYS_NUM)).intValue() * -1);
        }

        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIdx).rqstShipDt_A1, etaDt);
    }

    /**
     * setRqstShipDt
     * @param sMsg
     * @param aslMap
     * @param glblCmpyCd
     * @param lineIdx
     */
    private static void setRqstShipDt(NPAL1280SMsg sMsg, Map<String, Object> aslMap, String glblCmpyCd, int lineIdx) {
        String etaDt = "";

        if (ZYPCommonFunc.hasValue((BigDecimal) aslMap.get(RS_VND_SHIP_LT_DAYS_NUM))
                && BigDecimal.ZERO.compareTo((BigDecimal) aslMap.get(RS_VND_SHIP_LT_DAYS_NUM)) < 0) {
            etaDt = ZYPDateUtil.addDays(sMsg.A.no(lineIdx).rqstRcvDt_A1.getValue(), ((BigDecimal) aslMap.get(RS_VND_SHIP_LT_DAYS_NUM)).intValue() * -1);
        }

        ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIdx).rqstShipDt_A1, etaDt);
    }
    // END   2023/02/03 T.Kuroda [QC#60966, ADD]

    // START 2023/04/28 S.Dong [QC#60966, ADD]
    /**
     * setRqstShipDtRddApply
     * @param sMsg
     * @param aslMap
     * @param glblCmpyCd
     * @param lineIdx
     */
    public static void setRqstShipDtRddApply(NPAL1280SMsg sMsg, Map<String, Object> aslMap, String glblCmpyCd, int lineIdx) {
        String etaDt = "";

        if (ZYPCommonFunc.hasValue((BigDecimal) aslMap.get(RS_VND_SHIP_LT_DAYS_NUM))
                && BigDecimal.ZERO.compareTo((BigDecimal) aslMap.get(RS_VND_SHIP_LT_DAYS_NUM)) < 0) {
            etaDt = ZYPDateUtil.addDays(sMsg.A.no(lineIdx).rqstRcvDt_A1.getValue(), ((BigDecimal) aslMap.get(RS_VND_SHIP_LT_DAYS_NUM)).intValue() * -1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(lineIdx).rqstShipDt_A1, etaDt);
        } else if (!ZYPCommonFunc.hasValue((BigDecimal) aslMap.get(RS_VND_SHIP_LT_DAYS_NUM))
                || BigDecimal.ZERO.equals((BigDecimal) aslMap.get(RS_VND_SHIP_LT_DAYS_NUM))) {
            return;
        }

    }
    // END 2023/04/28 S.Dong [QC#60966, ADD]

    /**
     * getDefPrchReqTpDaysNum
     * @param glblCmpyCd
     * @param prchReqTpCd
     * @return
     */
    private static BigDecimal getDefPrchReqTpDaysNum(String glblCmpyCd, String prchReqTpCd) {
        S21SsmEZDResult ssmResult = null;
        BigDecimal ret = null;
        ssmResult = NPAL1280Query.getInstance().getDefPrchReqTpDaysNum(glblCmpyCd, prchReqTpCd);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> list = (List) ssmResult.getResultObject();
            Map<String, Object> map = list.get(0);
            ret = (BigDecimal) map.get("DEF_PRCH_REQ_TP_DAYS_NUM");
        }
        return ret;
    }
    /**
     * chkRqstDt
     * @param cMsg
     * @param sMsg
     * @param glblCmpyCd
     * @return
     */
    public static boolean chkRqstDt(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd) {
        String salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
        Boolean flgErr = false;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rqstRcvDt_A1)) {
              //ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).rqstRcvDt_A1, scrnMsg.rqstRcvDt);
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).vndCd_A1)) {
                    S21SsmEZDResult aslResult = NPAL1280Query.getInstance().findAslDtl(glblCmpyCd, sMsg.A.no(i).vndCd_A1.getValue(), sMsg.A.no(i).mdseCd_A1.getValue(), salesDate);
                    if (aslResult.isCodeNormal()) {
                        List<Map<String, Object>> aslResultList = (List) aslResult.getResultObject();
                        if (aslResultList != null && aslResultList.size() > 0) {
                            Map<String, Object> aslMap = aslResultList.get(0);
                            setRqstRcvDt(cMsg, sMsg, aslMap, glblCmpyCd, salesDate, i) ;
                        }
                    }
                }
            }

            if (!(PRCH_REQ_STS.CLOSED.equals(cMsg.prchReqStsCd.getValue())
                    || Arrays.asList(PRCH_REQ_LINE_STS.CANCELLED, PRCH_REQ_LINE_STS.CLOSED).contains(sMsg.A.no(i).prchReqLineStsCd_HD.getValue())
                    || PO_MDSE_CMPSN_TP.CHILD.equals(sMsg.A.no(i).poMdseCmpsnTpCd_A1.getValue()))) {
                    //scrnMsg.addCheckItem(scrnMsg.A.no(i).rqstRcvDt_A1);
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rqstRcvDt_A1)) {
                    String rqstRcvDt = sMsg.A.no(i).rqstRcvDt_A1.getValue();
                    String now = ZYPDateUtil.getSalesDate();
                    if (ZYPDateUtil.compare(rqstRcvDt, now) < 0) {
                        sMsg.A.no(i).rqstRcvDt_A1.setErrorInfo(1, NPAM0079E, new String[] {DATE_NEEDED });
                        flgErr = true;
                        //scrnMsg.addCheckItem(scrnMsg.A.no(i).rqstRcvDt_A1);
                        cMsg.xxDplyTab.setValue(TAB_DETAIL);
                        sMsg.xxDplyTab.setValue(TAB_DETAIL);
                    }
                    // START 2019/03/20 T.Ogura [QC#30769,DEL]
//                    if (!(ZYPDateUtil.isBusinessDay(glblCmpyCd, rqstRcvDt))) {
//                        sMsg.A.no(i).rqstRcvDt_A1.setErrorInfo(1, NPAM0094E, new String[] {DATE_NEEDED });
//                        flgErr = true;
//                        //scrnMsg.addCheckItem(scrnMsg.A.no(i).rqstRcvDt_A1);
//                        sMsg.xxDplyTab.setValue(TAB_DETAIL);
//                        sMsg.xxDplyTab.setValue(TAB_DETAIL);
//                    }
                    // END   2019/03/20 T.Ogura [QC#30769,DEL]
                }

                // START 2023/02/03 T.Kuroda [QC#60966, ADD]
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rqstShipDt_A1)) {
                    String rqstShipDt = sMsg.A.no(i).rqstShipDt_A1.getValue();
                    String now = ZYPDateUtil.getSalesDate();
                    if (ZYPDateUtil.compare(rqstShipDt, now) < 0) {
                        // START 2023/04/05 TZ.Win [QC#60966, MOD]
                        sMsg.A.no(i).rqstShipDt_A1.setErrorInfo(1, NPAM1657E, new String[] {VENDOR_SHIP_DATE });
                        // END 2023/04/05 TZ.Win [QC#60966, MOD]
                        flgErr = true;
                        cMsg.xxDplyTab.setValue(TAB_DETAIL);
                        sMsg.xxDplyTab.setValue(TAB_DETAIL);
                    }
                    // START 2023/04/05 TZ.Win [QC#60966, ADD]
                    if (ZYPDateUtil.compare(rqstShipDt, sMsg.A.no(i).rqstRcvDt_A1.getValue()) > 0) {
                        
                        sMsg.A.no(i).rqstShipDt_A1.setErrorInfo(1, NPAM1658E,  new String[] {VENDOR_SHIP_DATE });
                        flgErr = true;
                        cMsg.xxDplyTab.setValue(TAB_DETAIL);
                        sMsg.xxDplyTab.setValue(TAB_DETAIL);
                    }
                    // END 2023/04/05 TZ.Win [QC#60966, ADD]
                }
                // END   2023/02/03 T.Kuroda [QC#60966, ADD]
            }
        }
        return flgErr;
    }
    private static boolean isTime(EZDCStringItem... timeItems) {
        for (EZDCStringItem timeItem : timeItems) {
            if (ZYPCommonFunc.hasValue(timeItem)) {
                // IDX_5:5 hh24mi input length check
                if (timeItem.getValue().length() < IDX_5) {
                    // timeItem.setErrorInfo(1, NWAM0664E, new
                    // String[] {TIME_FORMAT });
                    timeItem.setErrorInfo(1, NPAM1515E, new String[] {TIME_FORMAT });
                    return false;
                }

                if (!timeItem.getValue().matches(CHK_TIME_PATTERN)) {
                    timeItem.setErrorInfo(1, NPAM1515E);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Get Time
     * @param tm time
     * @return time
     */
    public static String getTm(String tm) {
        if (ZYPCommonFunc.hasValue(tm)) {
            return tm.replace(COLON, "");
        }
        return tm;
    }
    //QC#21170 ADD End

    /**
     * checkCustCarrSvcLvlRelation
     * QC#23726 Add method
     * @param glblCmpyCd String
     * @param cMsg NPAL1280CMsg
     * @return boolean if error then return true.
     */
    public static boolean checkCustCarrSvcLvlRelation(String glblCmpyCd, NPAL1280CMsg cMsg) {

        // QC#28941 Update.
        if(!PRCH_REQ_SRC_TP.SALES_ORDER.equals(cMsg.prchReqSrcTpCd.getValue())){
            // Not targert data.
            return false;
        }

        // Get CPO
        if (!ZYPCommonFunc.hasValue(cMsg.trxRefNum)) {
            // Not target data.
            return false;
        }

        CPOTMsg cpo = getCPOTMsg(glblCmpyCd, cMsg.trxRefNum.getValue());

        if(cpo == null){
            // Not target data.
            return false;
        }

        // Get Carrier Service Level Code.
        String carrSvcLvlCd = null;
        if (ZYPCommonFunc.hasValue(cMsg.carrCd_HF) && ZYPCommonFunc.hasValue(cMsg.shpgSvcLvlCd_SL)) {
            S21SsmEZDResult result = NPAL1280Query.getInstance().getCarrSvcLvlCd(glblCmpyCd, cMsg.carrCd_HF.getValue(), cMsg.shpgSvcLvlCd_SL.getValue());

            if (result.isCodeNormal()) {
                carrSvcLvlCd = (String) result.getResultObject();
            } else {
                // Error.
                return true;
            }
        } else {
            // Not target data.
            return false;
        }

        // Get DsAcctNum
        String dsAcctNum = null;
        S21SsmEZDResult resultDsAcctNum = NPAL1280Query.getInstance().getDsAcctNum(glblCmpyCd, cMsg.prchReqNum.getValue());
        if (resultDsAcctNum.isCodeNormal()) {
            dsAcctNum = (String) resultDsAcctNum.getResultObject();
        }

        // Check Customer Carrier Service Level Relation
        return NWXC150001DsCheck.checkCustCarrSvcLvlRelation(glblCmpyCd //
                , cpo.dsOrdCatgCd.getValue() //
                , cpo.dsOrdTpCd.getValue() //
                , cpo.dsOrdRsnCd.getValue() //
                , dsAcctNum //
                , carrSvcLvlCd //
                , cMsg.frtCondCd_SL.getValue());
    }

    private static CPOTMsg getCPOTMsg(String glblCmpyCd, String cpoOrdNum) {

        CPOTMsg param = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.cpoOrdNum, cpoOrdNum);

        return (CPOTMsg) EZDTBLAccessor.findByKey(param);
    }
    
    //QC#26990 Add Start
    /**
     * setShipToAddressFromShipToCustomer
     * @param glblMsg NPAL1280SMsg
     * @param bizMsg NPAL1280CMsg
     */
    // QC#53816 2019/10/1 Mod Start
//    public static void setShipToAddressFromShipToCustomer(NPAL1280CMsg bizMsg, NPAL1280SMsg glblMsg) {
    public static boolean setShipToAddressFromShipToCustomer(NPAL1280CMsg bizMsg, NPAL1280SMsg glblMsg) {
    // QC#53816 2019/10/1 Mod End
        Map<String, String> resultMap = null;

        S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().getShipToAddressFromShipToCustomer(bizMsg);

        if (ssmResult.isCodeNormal()) {
            resultMap = (Map<String, String>) ssmResult.getResultObject();
            if (resultMap != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxLocNm, (String) resultMap.get("LOC_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToAddlLocNm_HS, (String) resultMap.get("ADDL_LOC_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_HS, (String) resultMap.get("ALL_SHIP_TO_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr_HS, (String) resultMap.get("FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdLineAddr_HS, (String) resultMap.get("SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToThirdLineAddr_HS, (String) resultMap.get("THIRD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToFrthLineAddr_HS, (String) resultMap.get("FRTH_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd_HS, (String) resultMap.get("CTRY_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd_HS, (String) resultMap.get("POST_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr_HS, (String) resultMap.get("CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd_HS, (String) resultMap.get("ST_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToProvNm_HS, (String) resultMap.get("PROV_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.ctryNm_HS, (String) resultMap.get("CTRY_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm_HS, (String) resultMap.get("CNTY_NM"));
            }
        }

        if (resultMap == null) {
            bizMsg.shipToCustCd.setErrorInfo(1, NPAM0076E, new String[] {"Ship To Location Code" });
            return false;
        }

//        ssmResult = NPAL1280Query.getInstance().countShipToCustAddr(bizMsg);
//        BigDecimal count = (BigDecimal) ssmResult.getResultObject();
//        if (BigDecimal.ZERO.compareTo(count) < 0) {
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg, ZYPConstant.FLG_ON_Y);
//        }
        return true;
    }

    
    /**
     * getManualDropShipWHCd
     * @param glblCmpyCd String
     * @return String[]
     */
    public static String[] getManualDropShipWHCd(String glblCmpyCd) {
        String constValue = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_KEY_MANUAL_DROPSHIP_WAREHOUSE_CD, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(constValue)) {
            return new String[] { MANUAL_DIRECT_SHIP_CUST_CD };
        }
        return constValue.split(COMMA);
    }

    /**
     * isManualDropShipWHCd
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return boolean
     */
    public static boolean isManualDropShipWHCd(String glblCmpyCd, String rtlWhCd) {
        String[] whCds = getManualDropShipWHCd(glblCmpyCd);
        return Arrays.asList(whCds).contains(rtlWhCd);
    }

    /**
     * getManualDropShipWHCd
     * @param glblCmpyCd String
     * @return String[]
     */
    public static String getFirstManualDropShipWHCd(String glblCmpyCd) {
        String[] whCds = getManualDropShipWHCd(glblCmpyCd);
        return whCds[0];
    }
    
    /**
     * set Destination WH Info.
     * @param bizMsg NPAL1280CMsg
     * @param glblMsg NPAL1280SMsg
     * @param destWhCd String
     */
    public static void setDestWhInfo(NPAL1280CMsg bizMsg, NPAL1280SMsg glblMsg) {
        List<Map<String, Object>> resultMapList = null;

        S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().getRtlWhNm(bizMsg.glblCmpyCd.getValue(), bizMsg.destRtlWhCd.getValue());

        if (ssmResult.isCodeNormal()) {
            resultMapList = (List<Map<String, Object>>) ssmResult.getResultObject();

            if (!resultMapList.isEmpty()) {
                if (resultMapList.size() == 1) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.destRtlWhCd, (String) resultMapList.get(0).get(DB_COLUMN_RTL_WH_CD));
                    ZYPEZDItemValueSetter.setValue(bizMsg.destRtlSwhCd, (String) resultMapList.get(0).get(DB_COLUMN_RTL_SWH_CD));
                    ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm, (String) resultMapList.get(0).get(DB_COLUMN_RTL_WH_NM));
                }
            }

//        } else {
//            if (!PO_HDR_STS.CLOSED.equals(bizMsg.poHdrStsCd.getValue())) {
//                bizMsg.destRtlWhCd_DS.setErrorInfo(1, NPAM0076E, new String[] {DISPLAY_DEST_RTL_WH_CD });
//            }
        }
    }

    // QC#28709 method update.
    /**
     * set Destination WH Info.
     * @param bizMsg NPAL1280CMsg
     * @param glblMsg NPAL1280SMsg
     * @param destWhCd String
     * @return true:OK/false:NG
     */
     public static boolean setManualDropWh(NPAL1280CMsg bizMsg, NPAL1280SMsg glblMsg, String glblCmpyCd) {

        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, glblCmpyCd);

        // Get Ship to Location Information
        if (!ZYPCommonFunc.hasValue(bizMsg.shipToCustCd) //
                && !ZYPCommonFunc.hasValue(bizMsg.shipToPostCd_HS) //
                && !ZYPCommonFunc.hasValue(bizMsg.shipToCtyAddr_HS) //
                && !ZYPCommonFunc.hasValue(bizMsg.shipToCtryCd_HS) //
                && !ZYPCommonFunc.hasValue(bizMsg.xxAllLineAddr_HS)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(bizMsg.shipToCustCd) //
                && !ZYPCommonFunc.hasValue(bizMsg.shipToPostCd_HS) //
                && !ZYPCommonFunc.hasValue(bizMsg.shipToCtyAddr_HS) //
                && !ZYPCommonFunc.hasValue(bizMsg.shipToCtryCd_HS) //
                && !ZYPCommonFunc.hasValue(bizMsg.xxAllLineAddr_HS)) {
        	// QC#53816 2019/10/1 Mod Start
//            NPAL1280CommonLogic.setShipToAddressFromShipToCustomer(bizMsg, glblMsg);
        	if (!NPAL1280CommonLogic.setShipToAddressFromShipToCustomer(bizMsg, glblMsg)) {
        		// Error.
                return false;
        	}
        	// QC#53816 2019/10/1 Mod End
        } else {
        	// QC#53816 2019/10/1 Add Start
        	if (!NPAL1280CommonLogic.checkShipToCustomer(bizMsg, glblMsg)) {
        		// Error.
                return false;
        	}
        	// QC#53816 2019/10/1 Add Start
        	if (!checkShipToAddress(bizMsg, glblMsg, glblCmpyCd)) {
                // Error.
                return false;
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {
                NPAL1280CommonLogic.setDestWhInfo(bizMsg, glblMsg);
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd, bizMsg.destRtlWhCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.xxLocNm, bizMsg.rtlWhNm);
            }
        }

        if (ZYPCommonFunc.hasValue(bizMsg.destRtlWhCd)) {

            // NPAL1280CommonLogic.setDestWhInfo(bizMsg, glblMsg);

            if (!bizMsg.destRtlWhCd.isError()) {
                if (!NPAL1280CommonLogic.isManualDropShipWHCd(bizMsg.glblCmpyCd.getValue(), bizMsg.destRtlWhCd.getValue()) //
                        && !(RTL_WH_CATG.CUSTOMER.equals(bizMsg.rtlWhCatgCd.getValue()))) {

                    // NPAL1280CommonLogic.setShipToAddressFromDestinationWH(bizMsg, glblMsg);
                }
            } else if (ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {

                bizMsg.rtlWhNm.clear();
                bizMsg.rtlWhCatgCd.clear();

            }
        } else {
            if (ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {
                String mdWhCd = NPAL1280CommonLogic.getFirstManualDropShipWHCd(bizMsg.glblCmpyCd.getValue());
                bizMsg.destRtlWhCd.setValue(mdWhCd);
                NPAL1280CommonLogic.setDestWhInfo(bizMsg, glblMsg);
            }
        }

        bizMsg.rtlWhCatgCd.clear();

        return true;
    }
    //QC#26990 Add End

     public static String getBillToSellToByShipTo(String shipToCustCd, String glblCmpyCd) {

         SHIP_TO_CUSTTMsg shipToCust = new SHIP_TO_CUSTTMsg();
         shipToCust.setConditionValue("shipToCustCd01", shipToCustCd);
         shipToCust.setConditionValue("glblCmpyCd01", glblCmpyCd);
         shipToCust.setSQLID("004");

         SHIP_TO_CUSTTMsgArray shipToCustAry = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(shipToCust);
         if (shipToCustAry != null && shipToCustAry.getValidCount() > 0) {
             return shipToCustAry.no(0).sellToCustCd.getValue();
         }

         return "";
     }

     public static void setMultipleHeader(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg) {

        String prntVndCdPre = "";
        String prntVndNmPre = "";
        String vndCdPre = "";
        String locNmPre = "";
        String prntVndCdTmp = "";
        String prntVndNmTmp = "";
        String vndCdTmp = "";
        String locNmTmp = "";
        boolean prntVndCdMultiFlg = false;
        boolean prntVndNmMultiFlg = false;
        boolean vndCdMultiFlg = false;
        boolean locNmMultiFlg = false;
        BigDecimal xxTotAmt = BigDecimal.ZERO;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            NPAL1280_ASMsg asMsg = sMsg.A.no(i);
            String prntVndNm = asMsg.prntVndNm_A1.getValue();
            if (!ZYPCommonFunc.hasValue(prntVndNm)) {
                asMsg.prntVndCd_A1.clear();
            }
            String prntVndCd = asMsg.prntVndCd_A1.getValue();
            String locNm = asMsg.locNm_A1.getValue();
            if (!ZYPCommonFunc.hasValue(locNm)) {
                asMsg.vndCd_A1.clear();
            }
            String vndCd = asMsg.vndCd_A1.getValue();

            if (i > 0 && ZYPCommonFunc.hasValue(prntVndNm) && ZYPCommonFunc.hasValue(prntVndNmPre) && !prntVndNmPre.equals(prntVndNm)) {
                prntVndNmMultiFlg = true;
            }
            if (i > 0 && ZYPCommonFunc.hasValue(vndCd) && ZYPCommonFunc.hasValue(vndCdPre) && !vndCdPre.equals(vndCd)) {
                vndCdMultiFlg = true;
            }
            if (i > 0 && ZYPCommonFunc.hasValue(locNm) && ZYPCommonFunc.hasValue(locNmPre) && !locNmPre.equals(locNm)) {
                locNmMultiFlg = true;
            }
            if (i > 0 && ZYPCommonFunc.hasValue(prntVndCd) && ZYPCommonFunc.hasValue(prntVndCdPre) && !prntVndCdPre.equals(prntVndCd)) {
                prntVndCdMultiFlg = true;
            }

            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).prchReqLineStsCd_HD) && !(PRCH_REQ_LINE_STS.CANCELLED.equals(sMsg.A.no(i).prchReqLineStsCd_HD.getValue())) && ZYPCommonFunc.hasValue(sMsg.A.no(i).xxUnitPrc_A1)) {
                xxTotAmt = xxTotAmt.add(sMsg.A.no(i).xxUnitPrc_A1.getValue());
            }

            if (ZYPCommonFunc.hasValue(prntVndCd)) {
                prntVndCdPre = prntVndCd;
            }
            if (ZYPCommonFunc.hasValue(prntVndNm)) {
                prntVndNmPre = prntVndNm;
            }
            if (ZYPCommonFunc.hasValue(vndCd)) {
                vndCdPre = vndCd;
            }
            if (ZYPCommonFunc.hasValue(locNm)) {
                locNmPre = locNm;
            }

            if (ZYPCommonFunc.hasValue(prntVndCdPre)) {
                prntVndCdTmp = prntVndCdPre;
            }
            if (ZYPCommonFunc.hasValue(prntVndNmPre)) {
                prntVndNmTmp = prntVndNmPre;
            }
            if (ZYPCommonFunc.hasValue(vndCdPre)) {
                vndCdTmp = vndCdPre;
            }
            if (ZYPCommonFunc.hasValue(locNmPre)) {
                locNmTmp = locNmPre;
            }
        }

        ZYPEZDItemValueSetter.setValue(cMsg.xxTotAmt, xxTotAmt);

        if (prntVndCdMultiFlg) {
            ZYPEZDItemValueSetter.setValue(cMsg.prntVndCd, MULTIPLE);
        } else if (ZYPCommonFunc.hasValue(prntVndCdTmp)){
            ZYPEZDItemValueSetter.setValue(cMsg.prntVndCd, prntVndCdTmp);
        }
        if (prntVndNmMultiFlg) {
            ZYPEZDItemValueSetter.setValue(cMsg.prntVndNm, MULTIPLE);
        } else if (ZYPCommonFunc.hasValue(prntVndNmTmp)){
            ZYPEZDItemValueSetter.setValue(cMsg.prntVndNm, prntVndNmTmp);
        }
        if (vndCdMultiFlg) {
            ZYPEZDItemValueSetter.setValue(cMsg.vndCd, MULTIPLE);
        } else if (ZYPCommonFunc.hasValue(vndCdTmp)){
            ZYPEZDItemValueSetter.setValue(cMsg.vndCd, vndCdTmp);
        }
        if (locNmMultiFlg) {
            cMsg.locNm.clear();
        } else if (ZYPCommonFunc.hasValue(locNmTmp)){
            ZYPEZDItemValueSetter.setValue(cMsg.locNm, locNmTmp);
        }
    }

     /**
      * QC#28941 Add method
      * @param glblCmpyCd
      * @param carrCd
      * @return carrName
      */
     public static String getCarrierName(String glblCmpyCd, String carrCd) {
        OTBD_CARR_VTMsg otbdCarrV = new OTBD_CARR_VTMsg();
        otbdCarrV.setSQLID("001");
        otbdCarrV.setConditionValue(BIND_GLBL_CMPY_CD_01, glblCmpyCd);
        otbdCarrV.setConditionValue(BIND_CARR_CD_01, carrCd);
        OTBD_CARR_VTMsgArray otbdCarrVList = (OTBD_CARR_VTMsgArray) EZDTBLAccessor.findByCondition(otbdCarrV);
        if (!(otbdCarrVList != null && 0 < otbdCarrVList.getValidCount())) {
            return null;
        } else {
            return otbdCarrVList.no(0).carrNm.getValue();
        }
    }

    // QC#28962 mod start
    /**
     * @param glblCmpyCd
     * @param destWhCd
     * @return
     */
    public static String getRtlWhNm(String glblCmpyCd, String destWhCd) {
        String rtlWhNm = null;
        List<Map<String, Object>> resultMapList = null;
        S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().getRtlWhNm(glblCmpyCd, destWhCd);
        if (ssmResult.isCodeNormal()) {
            resultMapList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (!resultMapList.isEmpty()) {
                if (resultMapList.size() == 1) {
                    rtlWhNm = (String) resultMapList.get(0).get("RTL_WH_NM");
                }
            }
        }
        return rtlWhNm;
    }

    /**
     * 
     * @param bizMsg
     * @param glblMsg
     */
    public static void setShipToAddressFromS21Wh(NPAL1280CMsg bizMsg, NPAL1280SMsg glblMsg) {
        Map<String, String> resultMap = null;

        S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().gettShipToAddressFromS21Wh(bizMsg);

        if (ssmResult.isCodeNormal()) {
            resultMap = (Map<String, String>) ssmResult.getResultObject();
            if (resultMap != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxLocNm, (String) resultMap.get("LOC_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToAddlLocNm_HS, (String) resultMap.get("ADDL_LOC_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_HS, (String) resultMap.get("FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr_HS, (String) resultMap.get("FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdLineAddr_HS, (String) resultMap.get("SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToThirdLineAddr_HS, (String) resultMap.get("THIRD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToFrthLineAddr_HS, (String) resultMap.get("FRTH_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd_HS, (String) resultMap.get("CTRY_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd_HS, (String) resultMap.get("POST_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr_HS, (String) resultMap.get("CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd_HS, (String) resultMap.get("ST_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToProvNm_HS, (String) resultMap.get("PROV_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.ctryNm_HS, (String) resultMap.get("CTRY_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm_HS, (String) resultMap.get("CNTY_NM"));
            }
        }

        if (resultMap == null) {
            bizMsg.shipToCustCd.setErrorInfo(1, NPAM0076E, new String[] {"Ship To Location Code" });
        }
    }
    // QC#28962 mod end

    /**
     * setupTextItemQueue
     * QC#28939
     * @param glblCmpyCd String
     * return textitem queue
     */
    public static Deque<String> setupTextItemQueue(String glblCmpyCd) {

        ArrayDeque<String> que = new ArrayDeque<String>();

        S21SsmEZDResult result = NPAL1280Query.getInstance().getTextItemList(glblCmpyCd);

        if (result.isCodeNormal()) {
            ArrayList<String> textItemList = (ArrayList<String>) result.getResultObject();

            for (String mdseCd : textItemList) {
                que.add(mdseCd);
            }
        }

        return que;
    }

    /**
     * chkAndSetTextItem
     * QC#28939 Add method.
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param index int
     * @param textItemQue Deque<String>
     * @return true(check clear) / false (check NG)
     */
    public static boolean chkAndSetTextItem(NPAL1280SMsg sMsg, String glblCmpyCd, int index, Deque<String> textItemQue) {

        if (!(ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(index).prntCmpySetMdseFlg_A1.getValue()))) {
            if (isExpenceTextItem(sMsg, glblCmpyCd, index)) {
                // check mandartory.
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(index).mdseCd_A1) //
                        && !ZYPCommonFunc.hasValue(sMsg.A.no(index).mdseDescShortTxt_A1)) {
                    sMsg.A.no(index).mdseCd_A1.setErrorInfo(1, ZZM9000E, new String[] {"Item Number or Item Description" });
                    sMsg.A.no(index).mdseDescShortTxt_A1.setErrorInfo(1, ZZM9000E, new String[] {"Item Number or Item Description" });
                    return false;
                }

                String textItem = textItemQue.pollFirst();

                if (textItem != null) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mdseCd_A1, textItem);
                } else {
                    // Text Item full.
                    // Blank Item# have exceeded TEXT ITEM in Item
                    // Master.
                    sMsg.A.no(index).mdseCd_A1.setErrorInfo(1, NPAM1611E);
                    return false;
                }

            }
        }
        return true;
    }

    /**
     * isModifyShpgSvcLvlReln
     * QC#29155 Add method.
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @return true:Modify
     */
    public static boolean isModifyShpgSvcLvlReln(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg) {

        // Edit check shpgSvcLvl
        if (ZYPCommonFunc.hasValue(cMsg.shpgSvcLvlCd_SL) && !ZYPCommonFunc.hasValue(sMsg.shpgSvcLvlCd_SL)) {
            return true;
        } else if (!ZYPCommonFunc.hasValue(cMsg.shpgSvcLvlCd_SL) && ZYPCommonFunc.hasValue(sMsg.shpgSvcLvlCd_SL)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(cMsg.shpgSvcLvlCd_SL) && ZYPCommonFunc.hasValue(sMsg.shpgSvcLvlCd_SL)) {
            String cMsgShpgSvcLvlCd = cMsg.shpgSvcLvlCd_SL.getValue();
            String sMsgShpgSvcLvlCd = sMsg.shpgSvcLvlCd_SL.getValue();
            if (!cMsgShpgSvcLvlCd.equals(sMsgShpgSvcLvlCd)) {
                return true;
            }
        }

        // Edit check carrir
        if (ZYPCommonFunc.hasValue(cMsg.carrCd_HF) && !ZYPCommonFunc.hasValue(sMsg.carrCd_HF)) {
            return true;
        } else if (!ZYPCommonFunc.hasValue(cMsg.carrCd_HF) && ZYPCommonFunc.hasValue(sMsg.carrCd_HF)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(cMsg.carrCd_HF) && ZYPCommonFunc.hasValue(sMsg.carrCd_HF)) {
            String cMsgCarrCd = cMsg.carrCd_HF.getValue();
            String sMsgCarrCd = sMsg.carrCd_HF.getValue();
            if (!cMsgCarrCd.equals(sMsgCarrCd)) {
                return true;
            }
        }

        return false;
    }

    // 2018/12/17 QC#29397 Add Start
    /**
     * isOnlyCsaSetItem
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return boolean
     */
    private static boolean isOnlyCsaSetItem(String glblCmpyCd, String mdseCd) {
        boolean rtrnCd = false;

        S21SsmEZDResult result = NPAL1280Query.getInstance().checkPrntCmpySetMdse(glblCmpyCd, mdseCd);
        BigDecimal count = BigDecimal.ZERO;
        if (result.isCodeNormal()) {
            count = (BigDecimal) result.getResultObject();
        }
        if (BigDecimal.ZERO.compareTo(count) < 0) {
            rtrnCd = true;
        }

        return rtrnCd;
    }
    // 2018/12/17 QC#29397 Add End

    // 2018/12/17 QC#29456 Add Start
    /**
     * <pre>
     * Get Default Charge Account by common logic.
     * @param curMsg NPAL1280_ASMsg
     * @param glblCmpyCd Global Company Code
     * @param shipToCustCd Ship To Customer Code
     * </pre>
     */
//    public static void setDefaultChargAccount(NPAL1280_ASMsg curMsg, String glblCmpyCd, String shipToCustCd) {
    public static void setDefaultChargAccount(NPAL1280CMsg bizMsg, NPAL1280_ASMsg curMsg, String glblCmpyCd) {

        if (!isExpenceLine(curMsg)) {
            return;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.shipToCustCd) //
                && !ZYPCommonFunc.hasValue(curMsg.mdseCd_A1)) { // 2019/02/08 QC#30181 Add Condition curMsg.mdseCd_A1 has value or not.
            return;
        }

        // START 03/19/2020 [QC#56122,MOD]
        if (!applyChrgAccount(curMsg, curMsg.xxScrItem130Txt_A1.getValue())) {
            return;
        }
        // END 03/19/2020 [QC#56122,MOD]

        // 2019/02/08 QC#30181 Add Start
        String shipToCustCd = bizMsg.shipToCustCd.getValue();
        AcctDefMode acctDefMod = getAccountDefaultingMode(bizMsg, curMsg);
        if (AcctDefMode.ITEM.equals(acctDefMod)) {
            shipToCustCd = "";
        }
        // 2019/02/08 QC#30181 Add End
        NPXC001001GetDefNineSegDataBean defNineSegDataBean = NPXC001001GetDefNineSegData.getInstance().getDefNineSegData( //
                glblCmpyCd, //
                shipToCustCd, //
                curMsg.mdseCd_A1.getValue(), //
                curMsg.xxScrItem130Txt_A1.getValue()); // 2019/02/08 QC#30181 Add parameter
        if (defNineSegDataBean == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(curMsg.coaCmpyCd_A1, defNineSegDataBean.getCoaCmpyCd());
        ZYPEZDItemValueSetter.setValue(curMsg.coaBrCd_A1, defNineSegDataBean.getCoaBrCd());
        ZYPEZDItemValueSetter.setValue(curMsg.coaCcCd_A1, defNineSegDataBean.getCoaCcCd());
        ZYPEZDItemValueSetter.setValue(curMsg.coaAcctCd_A1, defNineSegDataBean.getCoaAcctCd());
        ZYPEZDItemValueSetter.setValue(curMsg.coaProdCd_A1,defNineSegDataBean.getCoaProdCd());
        ZYPEZDItemValueSetter.setValue(curMsg.coaChCd_A1,defNineSegDataBean.getCoaChCd());
        ZYPEZDItemValueSetter.setValue(curMsg.coaAfflCd_A1,defNineSegDataBean.getCoaAfflCd());
        ZYPEZDItemValueSetter.setValue(curMsg.coaProjCd_A1,defNineSegDataBean.getCoaProjCd());
        ZYPEZDItemValueSetter.setValue(curMsg.coaExtnCd_A1,defNineSegDataBean.getCoaExtnCd());

        // Set xxScrItem130Txt_A1
        fillChrgAccountText(curMsg);

        if (defNineSegDataBean.getMsgIdList() != null //
                && !defNineSegDataBean.getMsgIdList().isEmpty()) {
            curMsg.xxScrItem130Txt_A1.setErrorInfo(1, defNineSegDataBean.getMsgId(IDX_0));
        }

        return;
    }

    /**
     * <pre>
     * Check if correct Charge Account.
     * @param curMsg Detail Global Message
     * @param glblCmpyCd Global Company Code
     * @return true: no error false: error
     * </pre>
     */
    public static boolean checkChargeAccount(NPAL1280_ASMsg curMsg, String glblCmpyCd) {

        applyChrgAccount(curMsg, curMsg.xxScrItem130Txt_A1.getValue());
        NPXC001001GetDefNineSegDataBean defNineSegDataBean = NPXC001001GetDefNineSegData.getInstance().checkDefault(glblCmpyCd //
                , curMsg.coaAcctCd_A1.getValue() //
                , curMsg.coaProdCd_A1.getValue() //
                , curMsg.coaProjCd_A1.getValue());
        if (defNineSegDataBean == null) {
            return true;
        }
        if (defNineSegDataBean.getMsgIdList() == null //
                || defNineSegDataBean.getMsgIdList().isEmpty()) {
            return true;
        } else {
            String errMsgId = defNineSegDataBean.getMsgIdList().get(IDX_0);
            curMsg.xxScrItem130Txt_A1.setErrorInfo(1, errMsgId);
            return false;
        }
    }

    private static boolean isExpenceLine(NPAL1280_ASMsg curMsg) {

        if (ZYPCommonFunc.hasValue(curMsg.prchReqLineTpCd_A1) //
                && (PRCH_REQ_LINE_TP.EXPENSE.equals(curMsg.prchReqLineTpCd_A1.getValue()) // 
                        || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(curMsg.prchReqLineTpCd_A1.getValue()))) {
            return true;
        }
        return false;
    }
    // 2018/12/17 QC#29456 Add End
    
    // QC#28709 Add method.
    /**
     * checkShipToAddress
     * @param bizMsg NPAL1280CMsg
     * @param glblMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @return true:OK/false:NG
     */
    public static boolean checkShipToAddress(NPAL1280CMsg bizMsg, NPAL1280SMsg glblMsg, String glblCmpyCd) {

        String addrChkFlg = ZYPCodeDataUtil.getVarCharConstValue("NPAL1280_ADDR_CHK_FLG", glblCmpyCd);

        if (ZYPCommonFunc.hasValue(addrChkFlg) //
                && ZYPConstant.FLG_OFF_N.equals(addrChkFlg)) {
            // Unnecessary address check.
            return true;
        }

        // Address validation
        String firstAddr = "";
        String scdAddr = "";
        String address = bizMsg.xxAllLineAddr_HS.getValue();

        if (address.length() > 60) {
            firstAddr = address.substring(0, 60);
            scdAddr = address.substring(60);
            if (scdAddr.length() > 60) {
                scdAddr = scdAddr.substring(0, 60);
            }
        } else {
            firstAddr = address;
            scdAddr = "";
        }

        NMZC003001PMsg addrValidApiPMsg = new NMZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.firstLineAddr, firstAddr);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.scdLineAddr, scdAddr);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.ctyAddr, bizMsg.shipToCtyAddr_HS);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.stCd, bizMsg.shipToStCd_HS);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.postCd, bizMsg.shipToPostCd_HS);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.ctryCd, bizMsg.shipToCtryCd_HS);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.cntyNm, bizMsg.shipToCntyNm_HS);

        NMZC003001 addrValidApi = new NMZC003001();
        addrValidApi.execute(addrValidApiPMsg, ONBATCH_TYPE.ONLINE);

        List<String> msgIdList = S21ApiUtil.getXxMsgIdList(addrValidApiPMsg);

        boolean rtrnCd = true;

        // check Error
        // Address Line
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_01.getValue()) //
                || NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_02.getValue())) {
            bizMsg.xxAllLineAddr_HS.setErrorInfo(1, NPAM0076E, new String[] {"Ship to Location Address Line" });
            rtrnCd = false;
        }
        // City
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_03.getValue())) {
            bizMsg.shipToCtyAddr_HS.setErrorInfo(1, NPAM0076E, new String[] {"Ship to Location City" });
            rtrnCd = false;
        }
        // State
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_04.getValue())) {
            bizMsg.shipToStCd_HS.setErrorInfo(1, NPAM0076E, new String[] {"Ship to Location State" });
            rtrnCd = false;
        }
        // Postal code
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_05.getValue())) {
            bizMsg.shipToPostCd_HS.setErrorInfo(1, NPAM0076E, new String[] {"Ship to Location Postal Code" });
            rtrnCd = false;
        }
        // Ctry
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_06.getValue())) {
            bizMsg.shipToCtryCd_HS.setErrorInfo(1, NPAM0076E, new String[] {"Ship to Location Country" });
            rtrnCd = false;
        }
        // Ctry
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_07.getValue())) {
            bizMsg.shipToCntyNm_HS.setErrorInfo(1, NPAM0076E, new String[] {"Ship to Location County" });
            rtrnCd = false;
        }

        if (msgIdList != null && msgIdList.size() > 0) {
            for (int i = 0; i < msgIdList.size(); i++) {

                setAddrValidResult((String) msgIdList.get(0), bizMsg.shipToCtyAddr_HS, addrValidApiPMsg.xxVldStsCd_03);
                setAddrValidResult((String) msgIdList.get(0), bizMsg.shipToStCd_HS, addrValidApiPMsg.xxVldStsCd_04);
                setAddrValidResult((String) msgIdList.get(0), bizMsg.shipToPostCd_HS, addrValidApiPMsg.xxVldStsCd_05);
                setAddrValidResult((String) msgIdList.get(0), bizMsg.shipToCtryCd_HS, addrValidApiPMsg.xxVldStsCd_06);
                setAddrValidResult((String) msgIdList.get(0), bizMsg.shipToCntyNm_HS, addrValidApiPMsg.xxVldStsCd_07);

                rtrnCd = false;
            }
        }

        // Not Error => replace address data.
        if (rtrnCd) {
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr_HS, addrValidApiPMsg.firstLineAddr);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdLineAddr_HS, addrValidApiPMsg.scdLineAddr);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr_HS, addrValidApiPMsg.ctyAddr);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd_HS, addrValidApiPMsg.stCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd_HS, addrValidApiPMsg.postCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd_HS, addrValidApiPMsg.ctryCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm_HS, addrValidApiPMsg.cntyNm);

            String addrLine = bizMsg.shipToFirstLineAddr_HS.getValue() //
                    + bizMsg.shipToScdLineAddr_HS.getValue() //
                    + bizMsg.shipToThirdLineAddr_HS.getValue() //
                    + bizMsg.shipToFrthLineAddr_HS.getValue();

            ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_HS, addrLine);
        }

        return rtrnCd;
    }

    /**
     * setAddrValidResult
     * @param msgId String
     * @param checkItem EZDCStringItem
     * @param rtnStsCd EZDPStringItem
     */
    private static void setAddrValidResult(String msgId, EZDCStringItem checkItem, EZDPStringItem rtnStsCd) {
        if (NMZC0030_ERROR.equals(rtnStsCd.getValue())) {
            // Replace Error Message.
            checkItem.clearErrorInfo();
            checkItem.setErrorInfo(1, msgId);
        }
    }

    // 2019/01/26 QC#29778-2 Add Start
    /**
     * <pre>
     * Getting Account Defaulting Mode.
     * @param bizMsg Biz. Message
     * @param dtlMsg target detail line global message
     * @return 0: No Operation 1: Defaulting Ship To And Item 2: Defaulting Item only
     * </ore>
     */
    private static AcctDefMode getAccountDefaultingMode(NPAL1280CMsg bizMsg, NPAL1280_ASMsg dtlMsg) {

        AcctDefMode rslt = AcctDefMode.NO_OPE;
        if (!ZYPCommonFunc.hasValue(bizMsg.shipToCustCd) && !ZYPCommonFunc.hasValue(dtlMsg.mdseCd_A1)) {
            return AcctDefMode.NO_OPE;
        }
        String screenAplID = bizMsg.getScreenAplID();
        if (NPAL1280_NMAL6800.equals(screenAplID) //
                || NPAL1280_GET_MDSE_INFO.equals(screenAplID) //
                || CMN_SAVE.equals(screenAplID) //
                || CMN_SUBMIT.equals(screenAplID)) {
            rslt = AcctDefMode.ITEM;

            applyChrgAccount(dtlMsg, dtlMsg.xxScrItem130Txt_A1.getValue());
            EZDSStringItem[] checkItemList = new EZDSStringItem []{
                    dtlMsg.coaCmpyCd_A1,
                    dtlMsg.coaBrCd_A1,
                    dtlMsg.coaCcCd_A1,
                    dtlMsg.coaAcctCd_A1,
                    dtlMsg.coaProdCd_A1,
                    dtlMsg.coaChCd_A1,
                    dtlMsg.coaAfflCd_A1,
                    dtlMsg.coaProjCd_A1,
                    dtlMsg.coaExtnCd_A1,
                    dtlMsg.xxScrItem130Txt_A1
            };

            for (EZDSStringItem checkItem : checkItemList) {
                if (!ZYPCommonFunc.hasValue(checkItem)) {
                    rslt = AcctDefMode.SHIP_ITEM;
                    break;
                }
            }
        } else {
            rslt = AcctDefMode.SHIP_ITEM;
        }

        // 2019/02/08 QC#30181 Add Start
        if (AcctDefMode.SHIP_ITEM.equals(rslt) //
                && !ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {
            rslt = AcctDefMode.ITEM;
        }
        // 2019/02/08 QC#30181 Add End
        return rslt;
    }

    private static String getValidStr(EZDSStringItem item, String val) {

        if (ZYPCommonFunc.hasValue(val)) {
            return val;
        } else {
            return item.getValue();
        }
    }
    // 2019/01/26 QC#29778-2 Add End

    // QC#51578 Add
    /**
     * isGmdWh
     */
    public static boolean isGmdWh(String glblCmpyCd, String destRtlWhCd) {
        RTL_WHTMsg rwTMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rwTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rwTMsg.rtlWhCd, destRtlWhCd);

        rwTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(rwTMsg);

        if (rwTMsg == null) {
            return false;
        }

        if (INVTY_OWNR.GMD.equals(rwTMsg.invtyOwnrCd.getValue())){
            return true;
        }
        return false;
    }

    /** QC#52756 Add
     * getPrchReqDtlTMsg
     * @param glblCmpyCd
     * @param prchReqNum
     * @param prchReqLineNum
     * @param prchReqLineSubNum
     * @return
     */
    public static PRCH_REQ_DTLTMsg getPrchReqDtlTMsg(String glblCmpyCd, String prchReqNum, String prchReqLineNum, BigDecimal prchReqLineSubNum) {
    	PRCH_REQ_DTLTMsg inMsg = new PRCH_REQ_DTLTMsg();
    	ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
    	ZYPEZDItemValueSetter.setValue(inMsg.prchReqNum, prchReqNum);
    	ZYPEZDItemValueSetter.setValue(inMsg.prchReqLineNum, prchReqLineNum);
    	ZYPEZDItemValueSetter.setValue(inMsg.prchReqLineSubNum, prchReqLineSubNum);
        return (PRCH_REQ_DTLTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }
    
    /**QC#53816 Add
     * checkShipToCustomer
     * @param bizMsg
     * @param glblMsg
     */
    public static boolean checkShipToCustomer(NPAL1280CMsg bizMsg, NPAL1280SMsg glblMsg) {

        Map<String, String> resultMap = null;
        S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().getShipToAddressFromShipToCustomer(bizMsg);

        if (ssmResult.isCodeNormal()) {
            resultMap = (Map<String, String>) ssmResult.getResultObject();
            // START 2019/12/03 T.Ogura [QC#54814,ADD]
            if (!ZYPCommonFunc.hasValue(bizMsg.xxLocNm)) {
                if (resultMap != null) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxLocNm, (String) resultMap.get("LOC_NM"));
                }
            }
            // END   2019/12/03 T.Ogura [QC#54814,ADD]
        }

        if (resultMap == null) {
            bizMsg.shipToCustCd.setErrorInfo(1, NPAM0076E, new String[] {"Ship To Location Code" });
            return false;
        }

        return true;
    }

    // QC#53300 2019/10/04 Add Start
    /**
     * get getSupplierName
     * @param glblMsg NPAL1280SMsg
     * @param bizMsg NPAL1280CMsg
     * @param glblCmpyCd String
     */
    public static boolean getSupplierName(NPAL1280CMsg bizMsg, NPAL1280SMsg glblMsg, String glblCmpyCd) {
        if (ZYPCommonFunc.hasValue(bizMsg.vndCd)) {
        	ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, glblCmpyCd);
            S21SsmEZDResult result = NPAL1280Query.getInstance().getVendorInfo(bizMsg);

            if (result.isCodeNormal()) {
                List<Map> vndInfo = (List<Map>) result.getResultObject();
                // Set First Record Data
                if (0 < vndInfo.size()) {
                    Map recode = vndInfo.get(0);
                    ZYPEZDItemValueSetter.setValue(bizMsg.prntVndCd, (String) recode.get("PRNT_VND_CD"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.prntVndNm, (String) recode.get("PRNT_VND_NM"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.vndCd, (String) recode.get("VND_CD"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.locNm, (String) recode.get("LOC_NM"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.ccyCd, (String) recode.get("DEAL_CCY_CD"));
                }
            } else {
                bizMsg.vndCd.setErrorInfo(1, NPAM0076E, new String[] {DISPLAY_VND_CD });
                return false;
            }
        } else {

            return false;
        }
        return true;
    }

    /**
     * setShipToAddressFromDestinationWH
     * @param glblMsg NPAL1280SMsg
     * @param bizMsg NPAL1280CMsg
     * @param glblCmpyCd String
     */
    public static void setShipToAddressFromDestinationWH(NPAL1280CMsg bizMsg, NPAL1280SMsg glblMsg, String glblCmpyCd) {

        Map<String, String> resultMap = null;

        S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().getShipToAddress(bizMsg);

        if (ssmResult.isCodeNormal()) {
            resultMap = (Map<String, String>) ssmResult.getResultObject();
            if (resultMap != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd, bizMsg.destRtlWhCd.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.xxLocNm, (String) resultMap.get("LOC_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm, (String) resultMap.get("LOC_NM"));
            }
        }

        if (resultMap == null) {
//            bizMsg.setMessageInfo(NPAM0076E, new String[] {"Ship To Customer Code" });
        	bizMsg.shipToCustCd.setErrorInfo(1, NPAM0076E, new String[] {"Ship To Customer Code" });
        }

        // CUSA Global Company Code
        String cusaGlblCmpyCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_PO_CUST_DROP_SHIP_QLFY_CD, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(cusaGlblCmpyCd)) {
        	cusaGlblCmpyCd = DEF_CUSA_GLBL_CMPY_CD;
        }

    }

    /**
     * changeSupplierApplyToDetail
     * @param bizMsg
     * @param glblMsg
     * @param glblCmpyCd
     * @param lineIndex
     */
    public static boolean changeSupplierApplyToDetail(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, String salesDate, int indexS) {

    	boolean errFlg = false;

    	// ASL Check
    	ZYPEZDItemValueSetter.setValue(sMsg.A.no(indexS).prntVndCd_A1, cMsg.prntVndCd);
		ZYPEZDItemValueSetter.setValue(sMsg.A.no(indexS).prntVndNm_A1, cMsg.prntVndNm);
		ZYPEZDItemValueSetter.setValue(sMsg.A.no(indexS).vndCd_A1, cMsg.vndCd);
		ZYPEZDItemValueSetter.setValue(sMsg.A.no(indexS).locNm_A1, cMsg.locNm);
		if (isExpenceTextItem(sMsg, glblCmpyCd, indexS)) {
            // Set uom.
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(indexS).prchReqDsplUomCd_A1, PKG_UOM.EACH);
            // Skip ASL check.
            return true;
        }

    	if (!NPAL1280CommonLogic.checkItemCode(cMsg, sMsg, glblCmpyCd, salesDate, indexS)) {
    		errFlg = true;
    	}

    	if (!errFlg) {
    		// Call NPZC129001
        	NPZC129001PMsg npzc129001PMsg = new NPZC129001PMsg();
        	ZYPEZDItemValueSetter.setValue(npzc129001PMsg.glblCmpyCd, glblCmpyCd);
        	ZYPEZDItemValueSetter.setValue(npzc129001PMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        	ZYPEZDItemValueSetter.setValue(npzc129001PMsg.prntVndCd, cMsg.prntVndCd);
        	ZYPEZDItemValueSetter.setValue(npzc129001PMsg.vndCd, cMsg.vndCd);
        	ZYPEZDItemValueSetter.setValue(npzc129001PMsg.mdseCd, sMsg.A.no(indexS).mdseCd_A1);
        	ZYPEZDItemValueSetter.setValue(npzc129001PMsg.poDispQty, sMsg.A.no(indexS).prchReqDispQty_A1);
        	NPZC129001 npzc129001 = new NPZC129001();
        	npzc129001.execute(npzc129001PMsg, ONBATCH_TYPE.ONLINE);

        	if (npzc129001PMsg.xxMsgIdList.getValidCount() > 0) {
        		sMsg.A.no(indexS).prchReqDispQty_A1.setErrorInfo(1, npzc129001PMsg.xxMsgIdList.no(0).xxMsgId.getValue(), new String[] {});
        		cMsg.setMessageInfo(npzc129001PMsg.xxMsgIdList.no(0).xxMsgId.getValue(), new String[] {});
        		errFlg = true;
        	}
        	ZYPEZDItemValueSetter.setValue(sMsg.A.no(indexS).prchReqQty_A1, npzc129001PMsg.poQty_O1);

        	// derive.
        	NPZC113001 api = new NPZC113001();
        	NPZC113001PMsg pMsg = new NPZC113001PMsg();
        	ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        	ZYPEZDItemValueSetter.setValue(pMsg.slsDt, salesDate);
        	ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, cMsg.shipToCustCd);
        	ZYPEZDItemValueSetter.setValue(pMsg.vndCd, cMsg.vndCd);
        	ZYPEZDItemValueSetter.setValue(pMsg.prntVndCd, cMsg.prntVndCd);
        	ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, (sMsg.A.no(indexS).mdseCd_A1));

        	api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        	if (pMsg.xxMsgIdList.getValidCount() > 0) {
        		sMsg.A.no(indexS).mdseCd_A1.setErrorInfo(1, NPZM0268E);
        		cMsg.setMessageInfo(NPZM0268E, new String[] {});
        		errFlg = true;
        	}

        	if (pMsg.xxMsgIdList.getValidCount() == 0 && ZYPConstant.FLG_ON_Y.equals(pMsg.xxErrFlg.getValue())) {
        		sMsg.A.no(indexS).mdseCd_A1.setErrorInfo(1, NPZM0268E);
        		cMsg.setMessageInfo(NPZM0268E, new String[] {});
        		errFlg = true;
        	} else if (ZYPCommonFunc.hasValue(pMsg.unitPrcAmt)) {
        		ZYPEZDItemValueSetter.setValue(sMsg.A.no(indexS).aslUnitPrcAmt_A1, pMsg.unitPrcAmt);
        		ZYPEZDItemValueSetter.setValue(sMsg.A.no(indexS).entDealNetUnitPrcAmt_A1, pMsg.unitPrcAmt);
        		ZYPEZDItemValueSetter.setValue(sMsg.A.no(indexS).aslDtlPk_A1, pMsg.aslDtlPk);
        		ZYPEZDItemValueSetter.setValue(sMsg.A.no(indexS).aslMdseCd_A1, pMsg.splyItemNum);
        	}
    	}

    	ZYPEZDItemValueSetter.setValue(sMsg.A.no(indexS).prntVndCd_A1, cMsg.prntVndCd);
    	ZYPEZDItemValueSetter.setValue(sMsg.A.no(indexS).prntVndNm_A1, cMsg.prntVndNm);
    	ZYPEZDItemValueSetter.setValue(sMsg.A.no(indexS).vndCd_A1, cMsg.vndCd);
    	ZYPEZDItemValueSetter.setValue(sMsg.A.no(indexS).locNm_A1, cMsg.locNm);

    	if (errFlg) {
    		return false;
    	} else {
    		return true;
    	}

    }

    /**
     * getDestWhInfo
     * @param bizMsg
     * @param glblMsg
     */
    public static void getDestWhInfo(NPAL1280CMsg bizMsg, NPAL1280SMsg glblMsg) {
        List<Map<String, Object>> resultMapList = null;

        S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().getRtlWhNm(bizMsg.glblCmpyCd.getValue(), bizMsg.destRtlWhCd.getValue());

        if (ssmResult.isCodeNormal()) {
            resultMapList = (List<Map<String, Object>>) ssmResult.getResultObject();

            if (!resultMapList.isEmpty()) {
                if (resultMapList.size() == 1) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.destRtlWhCd, (String) resultMapList.get(0).get(DB_COLUMN_RTL_WH_CD));
                    ZYPEZDItemValueSetter.setValue(bizMsg.destRtlSwhCd, (String) resultMapList.get(0).get(DB_COLUMN_RTL_SWH_CD));
                    ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm, (String) resultMapList.get(0).get(DB_COLUMN_RTL_WH_NM));
                    ZYPEZDItemValueSetter.setValue(bizMsg.rtlSwhNm, (String) resultMapList.get(0).get(RS_RTL_SWH_NM));
                }
            } else {
                bizMsg.destRtlWhCd.setErrorInfo(1, NPAM0076E, new String[] {DISPLAY_DEST_RTL_WH_CD });
            }

        } else {
              bizMsg.destRtlWhCd.setErrorInfo(1, NPAM0076E, new String[] {DISPLAY_DEST_RTL_WH_CD });
        }
    }
    // QC#53300 2019/10/04 Add End

    // START 2021/04/23 [QC#58645,ADD]
    /**
     * checkAutoApprove
     * @param params NPZC103001PMsg
     * @return true:Auto Approve false:Need Approval
     */
    public static boolean checkAutoApprove(String glblCmpyCd, List<PRCH_REQ_DTLTMsg> prdTMsgList) {

        String str = ZYPCodeDataUtil.getVarCharConstValue(NPAL1280Constant.VAR_CHAR_NPAL1280_AUTO_APPROVE_AMT, glblCmpyCd);
        BigDecimal autoApproveAmt = BigDecimal.ZERO;

        if (ZYPCommonFunc.hasValue(str)) {
            autoApproveAmt = new BigDecimal(str);
        }

        boolean rtrnCd = true;

        BigDecimal total = BigDecimal.ZERO;

        for (int i = 0; i < prdTMsgList.size(); i++) {
            total = total.add(prdTMsgList.get(i).entDealNetUnitPrcAmt.getValue());
            if (autoApproveAmt.compareTo(total) < 0) {
                rtrnCd = false;
                break;
            }
        }
        return rtrnCd;
    }
    // END 2021/04/23 [QC#58645,ADD]

    // START 2022/10/31 N.Takatsu[QC#60604, ADD]
    public static boolean isCsaWh(String glblCmpyCd, String shipToCustCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("shipToCustCd", shipToCustCd);
        param.put("whOwnr", WH_OWNR.CSA);
        // Execute
        S21SsmEZDResult result = NPAL1280Query.getInstance().getCsaWhCount(param);

        BigDecimal count = BigDecimal.ZERO;
        if (result.isCodeNormal()) {
            count = (BigDecimal) result.getResultObject();
        }
        if (BigDecimal.ZERO.compareTo(count) != 0) {
            return true;
        }
        return false;
    }
    // END 2022/10/31 N.Takatsu[QC#60604, ADD]
    // START 2024/1/1 K.Iwamoto [QC#62443, ADD]]
    public static boolean checkAllLineType(NPAL1280CMsg bizMsg, NPAL1280SMsg glblMsg, String glblCd) {
    	boolean hasChangeLine = false;
    	boolean checkresult = false;
        for(int i = 0; i < glblMsg.A.getValidCount(); i++) {
            checkresult = checkLineType(bizMsg, glblMsg, i, glblCd);
            if (checkresult) {
                glblMsg.A.no(i).xxScrItem130Txt_A1.setErrorInfo(1, NPAM1329E, new String[] {"Change ACC", "Please click 'Acct' button." });
                if (!hasChangeLine){
                    hasChangeLine = true;
                    bizMsg.setMessageInfo(ZZM9037E);
                }
                for (int j = 0;j < bizMsg.A.getValidCount(); j++) {
                     if(glblMsg.A.no(i).prchReqLineNum_A1.getValue().equals(bizMsg.A.no(j).prchReqLineNum_A1.getValue())) {
                        bizMsg.A.no(j).xxScrItem130Txt_A1.setErrorInfo(1, NPAM1329E, new String[] {"Change ACC", "Please click 'Acct' button." });
                     }
                }
            }
        }

        return hasChangeLine;
    }
    
    public static boolean checkLineType(NPAL1280CMsg bizMsg, NPAL1280SMsg glblMsg, int index, String glblCd) {
        boolean hasChangeLine = false;
        String mdseCd = glblMsg.A.no(index).mdseCd_A1.getValue();
        if (!hasValue(glblCd)){
            return hasChangeLine;
        }
        if (!hasValue(mdseCd)){
            return hasChangeLine;
        }
        if (checkItemTp(glblCd, mdseCd)) {
            // Intangible Item
            if (checkSetItem(mdseCd, glblMsg.A.no(index).xxLineNum_A1.getValue(), glblCd)) {
                // Intangible Reguler Item
                if(PRCH_REQ_LINE_TP.GOODS.equals(glblMsg.A.no(index).prchReqLineTpCd_A1.getValue())){
                    glblMsg.A.no(index).prchReqLineTpCd_A1.setValue(PRCH_REQ_LINE_TP.EXPENSE);
                    hasChangeLine = true;
                }
            }
        }
        return hasChangeLine;
    
    }
    
    private static boolean checkItemTp(String glblCd, String mdseCd) {
        MDSETMsg mdseTmsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTmsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(mdseTmsg.glblCmpyCd, glblCd);
        mdseTmsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTmsg);
        if (mdseTmsg != null && EZDTBLAccessor.RTNCD_NORMAL.equals(mdseTmsg.getReturnCode())) {
            if (ZYPConstant.FLG_OFF_N.equals(mdseTmsg.invtyCtrlFlg.getValue())) {
                // Intangible Item
                return true;
            }
        }
        return false;
    }
    
    private static boolean checkSetItem(String mdseCd, String dispPoDtlLineChar, String glblCd) {
        MDSETMsg mdseTmsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTmsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(mdseTmsg.glblCmpyCd, glblCd);
        mdseTmsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTmsg);
        if (mdseTmsg != null && EZDTBLAccessor.RTNCD_NORMAL.equals(mdseTmsg.getReturnCode())) {
            if(!MDSE_TP_SET.equals(mdseTmsg.mdseTpCd.getValue())) {
            	int indexNum = dispPoDtlLineChar.indexOf(DOT);
                if (NOT_SET.equals(String.valueOf(indexNum))) {
                    //Not Set Item
                    return true;
                }
            }
        }

        return false;
    }
    // END 2024/1/1 K.Iwamoto [QC#62443, ADD]]

}
