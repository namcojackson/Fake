/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPBL0020.common;

import static business.blap.NPBL0020.constant.NPBL0020Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import parts.common.EZDCSVInFile;
import parts.common.EZDCStringItem;
import parts.common.EZDFStringItem;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.common.EZDMsgCommons;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPBL0020.NPBL0020CMsg;
import business.blap.NPBL0020.NPBL0020Query;
import business.blap.NPBL0020.NPBL0020SMsg;
import business.blap.NPBL0020.NPBL0020_ACMsg;
import business.blap.NPBL0020.NPBL0020_ASMsg;
import business.db.CAL_RELNTMsg;
import business.db.CAL_RELNTMsgArray;
import business.db.DEF_DPLY_COA_INFOTMsg;
import business.db.DS_MDLTMsg;
import business.db.INVTYTMsg;
import business.db.MDSETMsg;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.db.OTBD_CARR_VTMsg;
import business.db.OTBD_CARR_VTMsgArray;
import business.db.PO_DTLTMsg;
import business.db.PRCH_REQTMsg;
import business.db.PRCH_REQ_DTLTMsg;
import business.db.PRCH_REQ_TPTMsg;
import business.db.SHPG_SVC_FRT_CHRG_RELNTMsg;
import business.db.STK_STSTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.file.NPBL0020F00FMsg;
import business.parts.NFZC102001PMsg;
import business.parts.NLZC215001PMsg;
import business.parts.NLZC403001PMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NPZC103001PMsg;
import business.parts.NPZC103001_prchReqInfoPMsg;
import business.parts.NPZC103001_serNumInfoPMsg;
import business.parts.NSZC048001PMsg;

import com.canon.cusa.s21.api.NFA.NFZC102001.NFZC102001;
import com.canon.cusa.s21.api.NLZ.NLZC215001.NLZC215001;
import com.canon.cusa.s21.api.NLZ.NLZC403001.NLZC403001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.NPZC103001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.constant.NPZC103001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC048001.NSZC048001;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWHData;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001GetDefNineSegData;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001GetDefNineSegDataBean;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DefaultCustomer;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DefaultCustomerBean;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CAL_SUB_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CCY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_CHRG_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_CHRG_TO;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_PLN_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_RTRN_RSN;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/27/2016   CITS            Makoto Okigami  Create          N/A
 * 04/05/2016   CITS            K.Ogino         Update          N/A
 * 04/13/2016   CITS            K.Ogino         Update          QC#6990
 * 04/21/2016   CITS            K.Ogino         Update          QC#6878
 * 04/21/2016   CITS            K.Ogino         Update          QC#7047
 * 04/21/2016   CITS            K.Ogino         Update          QC#7045
 * 04/26/2016   CITS            K.Ogino         Update          QC#6877
 * 05/31/2016   CITS            K.Ogino         Update          QC#8228
 * 06/09/2016   CSAI            D.Fukaya        Update          QC#9067
 * 06/14/2016   CSAI            D.Fukaya        Update          QC#9044
 * 06/15/2016   CSAI            D.Fukaya        Update          QC#9057
 * 06/15/2016   CSAI            D.Fukaya        Update          QC#9297
 * 06/23/2016   CSAI            D.Fukaya        Update          QC#8000
 * 06/24/2016   CSAI            D.Fukaya        Update          QC#9292
 * 06/27/2016   CSAI            D.Fukaya        Update          QC#9294
 * 06/30/2016   CSAI            D.Fukaya        Update          QC#7735
 * 07/26/2016   CITS            K.Ogino         Update          QC#9051
 * 07/29/2016   CITS            K.Ogino         Update          QC#8288
 * 08/03/2016   CITS            K.Ogino         Update          QC#12513
 * 08/03/2016   CITS            K.Ogino         Update          QC#9050
 * 08/09/2016   CITS            K.Ogino         Update          QC#12172
 * 08/24/2016   CITS            K.Ogino         Update          QC#13720
 * 08/25/2016   CITS            K.Ogino         Update          QC#13772
 * 08/30/2016   CITS            R.Shimamoto     Update          QC#12523
 * 09/08/2016   CITS            K.Ogino         Update          QC#14169
 * 10/03/2016   CITS            Y.IWASAKI       Update          QC#14571
 * 11/01/2016   CITS            K.Ogino         Update          QC#15592
 * 11/10/2016   CITS            K.Ogino         Update          QC#15925
 * 12/20/2016   CITS            K.Ogino         Update          QC#15815
 * 01/10/2017   CITS            K.Ogino         Update          QC#16296
 * 01/11/2017   CITS            T.Hakodate      Update          QC#17075
 * 01/13/2017   CITS            R.Shimamoto     Update          QC#17098
 * 01/13/2017   CITS            T.Kikuhara      Update          QC#17099
 * 02/08/2017   CITS            K.Ogino         Update          QC#17483
 * 04/21/2017   CITS            K.Ogino         Update          QC#18366
 * 05/23/2017   CITS            K.Ogino         Update          QC#18408
 * 07/05/2017   CITS            K.Ogino         Update          QC#19593
 * 08/03/2017   CITS            K.Ogino         Update          QC#18718
 * 08/08/2017   CITS            Y.Iwasaki       Update          QC#20421
 * 08/08/2017   CITS            Y.Iwasaki       Update          QC#20118
 * 08/23/2017   CITS            H.Naoi          Update          Sol#097(QC#18398)
 * 09/01/2017   CITS            R.Shimamoto     Update          QC#20439
 * 09/26/2017   CITS            K.Ogino         Update          QC#21288,QC#21132,#21259
 * 10/10/2017   CITS            K.Ogino         Update          QC#21682
 * 10/24/2017   CITS            K.Ogino         Update          QC#22026
 * 11/14/2017   CITS            K.Ogino         Update          QC#22345
 * 11/15/2017   CITS            K.Ogino         Update          QC#22608
 * 11/16/2017   CITS            K.Ogino         Update          QC#22346
 * 11/28/2017   CITS            K.Ogino         Update          QC#22481
 * 12/26/2017   CITS            K.Ogino         Update          QC#22467
 * 02/22/2018   CITS            K.Ogino         Update          QC#24356
 * 02/23/2018   CITS            T.Tokutomi      Update          QC#22376
 * 02/27/2018   CITS            K.Ogino         Update          QC#22518
 * 03/27/2018   CITS            T.Wada          Update          QC#22517
 * 04/03/2018   CITS            S.Katsuma       Update          QC#23521,25063
 * 04/09/2018   CITS            T.Kikuhara      Update          QC#24994
 * 05/17/2018   CITS            Y.Iwasaki       Update          QC#25814
 * 05/25/2018   CITS            S.Katsuma       Update          QC#25893
 * 06/01/2018   CITS            S.Katsuma       Update          QC#26234
 * 06/06/2018   CITS            T.Hakodate      Update          QC#26511
 * 11/06/2018   CITS            M.Naito         Update          QC#28698
 * 11/19/2018   CITS            T.Tokutomi      Update          QC#29154
 * 11/20/2018   CITS            Y.Iwasaki       Update          QC#29212
 * 12/12/2018   Fujitsu         S.Takami        Update          QC#29456
 * 12/20/2018   CITS            K.Kameoka       Update          QC#29456
 * 01/15/2019   Fujitsu         T.Ogura         Update          QC#29774
 * 01/28/2019   Fujitsu         S.Takami        Update          QC#29778-2 -> Partially deleting logic by QC#30181 without any comments.
 * 02/04/2019   CITS            M.Naito         Update          QC#30217
 * 02/08/2019   Fujitsu         S.Takami        Update          QC#30181
 * 03/20/2019   Fujitsu         T.Ogura         Update          QC#30769
 * 05/17/2019   CITS            M.Naito         Update          QC#50076
 * 08/27/2019   CITS            M.Naito         Update          QC#52276
 * 09/11/2019   CITS            K.Ogino         Update          QC#52809
 * 09/25/2019   Fujitsu         T.Ogura         Update          QC#53694
 * 10/03/2019   CITS            M.Naito         Update          QC#52809
 * 12/05/2019   Fujitsu         R.Nakamura      Update          QC#54969
 * 02/01/2020   CITS            K.Ogino         Update          QC#55313
 * 02/07/2020   Fujitsu         A.Kazuki        Update          QC#55642
 * 05/01/2020   CITS            K.Fukumura      Update          QC#56543
 * 05/18/2020   CITS            K.Ogino         Update          QC#56867
 * 06/12/2020   Fujitsu         T.Ogura         Update          QC#57002
 * 06/25/2020   CITS            K.Ogino         Update          QC#57250
 * 06/26/2020   CITS            K.Ogino         Update          QC#57052. QC#57351
 * 11/09/2021   CITS            R.Azucena       Update          QC#58586
 * 12/08/2021   CITS            K.Ogino         Update          QC#58586-1
 * 11/16/2022   CITS            R.Azucena       Update          QC#60808
 * 12/27/2022   Hitachi         T.Kuroda        Update          QC#60562
 * 03/13/2023   CITS            R.Azucena       Update          QC#61282
 * 07/04/2023   Hitachi         T.Kuroda        Update          QC#61440
 *</pre>
 */
public class NPBL0020CommonLogic {

    // 01/28/2019 QC#29778-2 Add Start
    /** Account Defaulting Mode */
    private enum AcctDefMode { NO_OPE, SHIP_ITEM, ITEM, SHIP }
    // 01/28/2019 QC#29778-2 Add End

    /**
     * Create Pulldown Requisition Type
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @param scrEntAvalFlg boolean /true;ScreenEntryMode
     */
    public static void createPullDownRequisitionType(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd, boolean scrEntAvalFlg) {

        // Clear Pulldown Data
        cMsg.prchReqTpCd_PD.clear();
        cMsg.prchReqTpDescTxt_PD.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRCH_REQ_REC_TP_CD, PRCH_REQ_REC_TP.INVENTORY_REQUEST);
        if (scrEntAvalFlg) {
            ssmParam.put(DB_PARAM_SCR_ENT_AVAL_FLG, ZYPConstant.FLG_ON_Y);
        }

        // Execute
        S21SsmEZDResult result = NPBL0020Query.getInstance().getRequisitionTypePulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map<String, String>> requisitionTypeList = (List<Map<String, String>>) result.getResultObject();

            for (int i = 0; i < requisitionTypeList.size(); i++) {
                Map<String, String> record = requisitionTypeList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpCd_PD.no(i), (String) record.get(DB_COLUMN_PRCH_REQ_TP_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpDescTxt_PD.no(i), (String) record.get(DB_COLUMN_PRCH_REQ_TP_DESC_TXT));

                if (i >= cMsg.prchReqTpCd_PD.length() - 1) {
                    break;
                }
            }
        }
    }

    /**
     * Create Pulldown Requested Service Level
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownRequestedShipMethod(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {

        String srcShpgSvcLvlCd = null;
        if (ZYPCommonFunc.hasValue(cMsg.shpgSvcLvlCd_SL.getValue())) {
            srcShpgSvcLvlCd = cMsg.shpgSvcLvlCd_SL.getValue();
        }

        // Clear Pulldown Data
        cMsg.shpgSvcLvlCd_PD.clear();
        cMsg.shpgSvcLvlDescTxt_PD.clear();

        ZYPCodeDataUtil.createPulldownList(SHPG_SVC_LVL.class, cMsg.shpgSvcLvlCd_PD, cMsg.shpgSvcLvlDescTxt_PD);

        String defShpgSvcLvlCd = ZYPCodeDataUtil.getVarCharConstValue(INVTY_REQ_DEF_SHPG_SVC_LVL_CD, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(srcShpgSvcLvlCd)) {
            if (ZYPCommonFunc.hasValue(defShpgSvcLvlCd)) {
                ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_SL, defShpgSvcLvlCd);
            } else {
                if (ZYPCommonFunc.hasValue(cMsg.shpgSvcLvlCd_PD.no(0))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_SL, cMsg.shpgSvcLvlCd_PD.no(0).getValue());
                }
            }
        }
    }

    /**
     * Create Pulldown Return Reason
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownReturnReason(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {

        // Clear Pulldown Data
        cMsg.vndRtrnRsnCd_PD.clear();
        cMsg.vndRtrnRsnDescTxt_PD.clear();

        ZYPCodeDataUtil.createPulldownList(VND_RTRN_RSN.class, cMsg.vndRtrnRsnCd_PD, cMsg.vndRtrnRsnDescTxt_PD);

    }

    /**
     * Create Pulldown Line Type
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @param scrEntAvalFlg boolean /true;ScreenEntryMode
     * @param defPrchReqLineTpCd String
     */
    public static void createPullDownLineType(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd, boolean scrEntAvalFlg, String defPrchReqLineTpCd) {

        // Clear Pulldown Data
        cMsg.prchReqLineTpCd_PD.clear();
        cMsg.prchReqLineTpDescTxt_PD.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRCH_REQ_REC_TP_CD, PRCH_REQ_REC_TP.INVENTORY_REQUEST);
        if (scrEntAvalFlg) {
            ssmParam.put(DB_PARAM_SCR_ENT_AVAL_FLG, ZYPConstant.FLG_ON_Y);
        }
        if (ZYPCommonFunc.hasValue(defPrchReqLineTpCd)) {
            ssmParam.put(DB_PARAM_PRCH_REQ_LINE_TP, defPrchReqLineTpCd);
        }

        // Execute
        S21SsmEZDResult result = NPBL0020Query.getInstance().getLineTypePulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map<String, String>> lineTypeList = (List<Map<String, String>>) result.getResultObject();

            for (int i = 0; i < lineTypeList.size(); i++) {
                Map<String, String> record = lineTypeList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.prchReqLineTpCd_PD.no(i), (String) record.get(DB_COLUMN_PRCH_REQ_LINE_TP_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.prchReqLineTpDescTxt_PD.no(i), (String) record.get(DB_COLUMN_PRCH_REQ_LINE_TP_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(cMsg.fromStkStsCd_HD.no(i), (String) record.get(DB_COLUMN_FROM_STK_STS_CD));

                if (i >= cMsg.prchReqLineTpCd_PD.length() - 1) {
                    break;
                }
            }
        }
    }

    /**
     * Create Pulldown Stock Status
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownStockStatus(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {

        // Clear Pulldown Data
        cMsg.fromStkStsCd_PD.clear();
        cMsg.stkStsDescTxt_PD.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);

        // Execute
        S21SsmEZDResult result = NPBL0020Query.getInstance().getStockStatusPulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map<String, String>> stkStsList = (List<Map<String, String>>) result.getResultObject();

            for (int i = 0; i < stkStsList.size(); i++) {
                if (i >= cMsg.prchReqLineTpCd_PD.length()) {
                    break;
                }

                Map<String, String> record = stkStsList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.fromStkStsCd_PD.no(i), (String) record.get(DB_COLUMN_STK_STS_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.stkStsDescTxt_PD.no(i), (String) record.get(DB_COLUMN_STK_STS_DESC_TXT));
            }
        }
    }

    /**
     * Get Line Status Name
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     */
    public static void getLineStatusName(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {
        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRCH_REQ_LINE_STS_CD, PRCH_REQ_LINE_STS.OPEN);

        // Execute
        S21SsmEZDResult result = NPBL0020Query.getInstance().getLineStatusName(ssmParam);

        if (result.isCodeNormal()) {
            String prchReqLineStsDescTxt = (String) result.getResultObject();
            ZYPEZDItemValueSetter.setValue(sMsg.prchReqLineStsDescTxt, prchReqLineStsDescTxt);
            ZYPEZDItemValueSetter.setValue(cMsg.prchReqLineStsDescTxt, prchReqLineStsDescTxt);
        }
    }

    /**
     * Get Config Type Name
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     */
    public static void getConfigTypeName(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {
        // Create Param (NEW)
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_CONFIG_TP_CD, CONFIG_TP.NEW);

        // Execute
        S21SsmEZDResult result = NPBL0020Query.getInstance().getConfigTypeName(ssmParam);

        if (result.isCodeNormal()) {
            String configTpDescTxt = (String) result.getResultObject();
            ZYPEZDItemValueSetter.setValue(sMsg.configTpDescTxt_01, configTpDescTxt);
            ZYPEZDItemValueSetter.setValue(cMsg.configTpDescTxt_01, configTpDescTxt);
        }

        // Create Param (EXISTING)
        ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_CONFIG_TP_CD, CONFIG_TP.EXISTING);

        // Execute
        result = NPBL0020Query.getInstance().getConfigTypeName(ssmParam);

        if (result.isCodeNormal()) {
            String configTpDescTxt = (String) result.getResultObject();
            ZYPEZDItemValueSetter.setValue(sMsg.configTpDescTxt_03, configTpDescTxt);
            ZYPEZDItemValueSetter.setValue(cMsg.configTpDescTxt_03, configTpDescTxt);
        }
    }

    /**
     * Get Default Header Status Name
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     */
    public static void getDefaultHeaderStatusName(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {
        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRCH_REQ_STS_CD, PRCH_REQ_STS.OPEN);

        // Execute
        S21SsmEZDResult result = NPBL0020Query.getInstance().getHeaderStatusName(ssmParam);

        if (result.isCodeNormal()) {
            String prchReqStsDescTxt = (String) result.getResultObject();
            ZYPEZDItemValueSetter.setValue(sMsg.prchReqStsDescTxt, prchReqStsDescTxt);
            ZYPEZDItemValueSetter.setValue(cMsg.prchReqStsDescTxt, prchReqStsDescTxt);
        }
    }

    /**
     * Get Default Approval Status Name
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     */
    public static void getDefaultApprovalStatusName(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {
        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRCH_REQ_APVL_STS_CD, PRCH_REQ_APVL_STS.ENTERED);

        // Execute
        S21SsmEZDResult result = NPBL0020Query.getInstance().getApprovalStatusName(ssmParam);

        if (result.isCodeNormal()) {
            String prchReqApvlStsDescTxt = (String) result.getResultObject();
            ZYPEZDItemValueSetter.setValue(sMsg.prchReqApvlStsDescTxt, prchReqApvlStsDescTxt);
            ZYPEZDItemValueSetter.setValue(cMsg.prchReqApvlStsDescTxt, prchReqApvlStsDescTxt);
        }
    }

    /**
     * Get Default Document Source Type Name
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     */
    public static void getDefaultDocumentSourceTypeName(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {
        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRCH_REQ_SRC_TP_CD, PRCH_REQ_SRC_TP.INVENTORY_REQUEST_ENTRY);

        // Execute
        S21SsmEZDResult result = NPBL0020Query.getInstance().getDocumentSourceTypeName(ssmParam);

        if (result.isCodeNormal()) {
            String prchReqSrcTpDescTxt = (String) result.getResultObject();
            ZYPEZDItemValueSetter.setValue(sMsg.prchReqSrcTpDescTxt, prchReqSrcTpDescTxt);
            ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpDescTxt, prchReqSrcTpDescTxt);
        }
    }

    /**
     * Search
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     */
    public static boolean search(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRCH_REQ_REC_TP_CD, PRCH_REQ_REC_TP.INVENTORY_REQUEST);
        ssmParam.put(DB_PARAM_EXPENSE_ORDER_TP, PRCH_REQ_TP.EXPENSE_ORDER);
        ssmParam.put(DB_PARAM_WH_TRANSFER_TP, PRCH_REQ_TP.WH_TRANSFER);
        ssmParam.put(DB_PARAM_KITTING_TP, PRCH_REQ_TP.KITTING);
        // START 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
//        ssmParam.put(DB_PARAM_PRCH_REQ_TP_CD, PRCH_REQ_TP.VENDOR_RETURN);
        ssmParam.put(DB_PARAM_FLG_N, ZYPConstant.FLG_OFF_N);
        // END 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
        ssmParam.put(DB_PARAM_ROWNUM, sMsg.A.length() + 1);

        //QC#29456 Add Start
        ZYPEZDItemValueSetter.setValue(cMsg.chrgAcctEdtblFlg, NPBL0020CommonLogic.getChrgAcctEdtblFlg(cMsg.prchReqTpCd_SL.getValue(), glblCmpyCd));
        //QC#29456 Add End

        S21SsmEZDResult result = null;
        result = NPBL0020Query.getInstance().search(ssmParam, sMsg);

        if (!result.isCodeNormal()) {
            String prchReqNumIp = null;
            String prchReqNum = null;
            String prchReqTpCdSL = PRCH_REQ_TP.SUBCONTRACT;
            String prchReqSrcTpCd = PRCH_REQ_SRC_TP.PARTS_REFURB_AUTO;
            String trxRefNum = null;
            //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) START
            String mrpPlnNm = null;
            //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) END
            String poOrdNum = null;
            String poOrdDtlLineNum = null;

            if (ZYPCommonFunc.hasValue(cMsg.prchReqNum_IP)) {
                prchReqNumIp = cMsg.prchReqNum_IP.getValue();
            }
            if (ZYPCommonFunc.hasValue(cMsg.prchReqNum)) {
                prchReqNum = cMsg.prchReqNum.getValue();
            }
            if (ZYPCommonFunc.hasValue(cMsg.trxRefNum)) {
                trxRefNum = cMsg.trxRefNum.getValue();
            }
            //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) START
            if (ZYPCommonFunc.hasValue(cMsg.mrpPlnNm)) {
                mrpPlnNm = cMsg.mrpPlnNm.getValue();
            }
            //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) END
            if (ZYPCommonFunc.hasValue(cMsg.poOrdNum)) {
                poOrdNum = cMsg.poOrdNum.getValue();
            }
            if (ZYPCommonFunc.hasValue(cMsg.poOrdDtlLineNum)) {
                poOrdDtlLineNum = cMsg.poOrdDtlLineNum.getValue();
            }
            cMsg.clear();
            sMsg.clear();
            ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum, prchReqNum);
            ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum_IP, prchReqNumIp);
            ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpCd_SL, prchReqTpCdSL);
            ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpCd, prchReqSrcTpCd);
            ZYPEZDItemValueSetter.setValue(cMsg.trxRefNum, trxRefNum);
            //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) START
            ZYPEZDItemValueSetter.setValue(cMsg.mrpPlnNm, mrpPlnNm);
            //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) END
            ZYPEZDItemValueSetter.setValue(cMsg.poOrdNum, poOrdNum);
            ZYPEZDItemValueSetter.setValue(cMsg.poOrdDtlLineNum, poOrdDtlLineNum);
            noSearchResultInit(cMsg, sMsg, glblCmpyCd);
            cMsg.setMessageInfo(NMAM0038I);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            return true;
        }

        // Max Recode Over
        int queryResCnt = result.getQueryResultCount();
        if (queryResCnt > sMsg.A.length()) {
            cMsg.setMessageInfo(NMAM8181W);
            queryResCnt = sMsg.A.length();
        }

        // change requisition type. not screen entry mode.
        NPBL0020CommonLogic.createPullDownRequisitionType(cMsg, sMsg, glblCmpyCd, false);
        // Add Start 2019/12/05 QC#54969
        boolean isFirstLine = true;
        // Add End 2019/12/05 QC#54969

        // Set Header Item
        for (int j = 0; j < sMsg.A.getValidCount(); j++) {
            // Mod Start 2019/12/05 QC#54969
//            if (j == 0) {
            // Mod Start 2019/12/27 QC#55642
//            if (!PRCH_REQ_LINE_STS.CANCELLED.equals(sMsg.A.no(j).prchReqLineStsCd_A1.getValue()) && isFirstLine) {
            if (isFirstLine && (!PRCH_REQ_LINE_STS.CANCELLED.equals(sMsg.A.no(j).prchReqLineStsCd_A1.getValue()) || j == sMsg.A.getValidCount() - 1)){
            // Mod End   2019/12/27 QC#55642
                ZYPEZDItemValueSetter.setValue(sMsg.prchReqNum, sMsg.A.no(j).prchReqNum_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.prchReqNum_HD, sMsg.A.no(j).prchReqNum_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.prchReqTpCd_SL, sMsg.A.no(j).prchReqTpCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.prchReqStsCd, sMsg.A.no(j).prchReqStsCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.prchReqStsDescTxt, sMsg.A.no(j).prchReqStsDescTxt_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.prchReqApvlStsCd, sMsg.A.no(j).prchReqApvlStsCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.prchReqApvlStsDescTxt, sMsg.A.no(j).prchReqApvlStsDescTxt_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.prchReqCratDt, sMsg.A.no(j).prchReqCratDt_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.rqstRcvDt, sMsg.A.no(j).rqstRcvDt_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.prchReqSrcTpCd, sMsg.A.no(j).prchReqSrcTpCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.prchReqSrcTpDescTxt, sMsg.A.no(j).prchReqSrcTpDescTxt_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.trxRefNum, sMsg.A.no(j).trxRefNum_A1);
                //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) START
                ZYPEZDItemValueSetter.setValue(sMsg.mrpPlnNm, sMsg.A.no(j).mrpPlnNm_A1);
                //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) END
                ZYPEZDItemValueSetter.setValue(sMsg.prchReqRqstByPsnCd, sMsg.A.no(j).prchReqRqstByPsnCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.fullPsnNm, sMsg.A.no(j).fullPsnNm_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.shpgSvcLvlCd_SL, sMsg.A.no(j).shpgSvcLvlCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.carrCd, sMsg.A.no(j).carrCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.carrNm, sMsg.A.no(j).carrNm_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.ctacPsnNm, sMsg.A.no(j).ctacPsnNm_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.prchReqCmntTxt, sMsg.A.no(j).prchReqCmntTxt_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.shipToLocNm, sMsg.A.no(j).shipToLocNm_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.shipToAddlLocNm, sMsg.A.no(j).shipToAddlLocNm_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.shipToFirstLineAddr, sMsg.A.no(j).shipToFirstLineAddr_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.shipToScdLineAddr, sMsg.A.no(j).shipToScdLineAddr_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.shipToThirdLineAddr, sMsg.A.no(j).shipToThirdLineAddr_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.shipToFrthLineAddr, sMsg.A.no(j).shipToFrthLineAddr_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.xxShipVndAddr, sMsg.A.no(j).xxShipVndAddr_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.shipToPostCd, sMsg.A.no(j).shipToPostCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.shipToCtyAddr, sMsg.A.no(j).shipToCtyAddr_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.shipToCntyNm, sMsg.A.no(j).shipToCntyNm_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.shipToStCd, sMsg.A.no(j).shipToStCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.shipToProvNm, sMsg.A.no(j).shipToProvNm_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.shipToCtryCd, sMsg.A.no(j).shipToCtryCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.ctryNm, sMsg.A.no(j).ctryNm_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.vndRtrnRsnCd_SL, sMsg.A.no(j).vndRtrnRsnCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.srcRtlWhCd, sMsg.A.no(j).srcRtlWhCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.rtlWhNm_SW, sMsg.A.no(j).rtlWhNm_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.srcRtlSwhCd, sMsg.A.no(j).srcRtlSwhCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.rtlSwhNm_SS, sMsg.A.no(j).rtlSwhNm_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.destRtlWhCd, sMsg.A.no(j).destRtlWhCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.rtlWhNm_DW, sMsg.A.no(j).rtlWhNm_A2);
                ZYPEZDItemValueSetter.setValue(sMsg.destRtlSwhCd, sMsg.A.no(j).destRtlSwhCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.rtlSwhNm_DS, sMsg.A.no(j).rtlSwhNm_A2);
                ZYPEZDItemValueSetter.setValue(sMsg.prntVndCd, sMsg.A.no(j).prntVndCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.vndCd, sMsg.A.no(j).vndCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.dplyVndNm, sMsg.A.no(j).dplyVndNm_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.prchReqRecTpCd, sMsg.A.no(j).prchReqRecTpCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.prchReqCratByPsnCd, sMsg.A.no(j).prchReqCratByPsnCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.prchGrpCd, sMsg.A.no(j).prchGrpCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.prchReqApvlDt, sMsg.A.no(j).prchReqApvlDt_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.prchReqApvlByPsnCd, sMsg.A.no(j).prchReqApvlByPsnCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.fsrNum, sMsg.A.no(j).fsrNum_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.svcTaskNum, sMsg.A.no(j).svcTaskNum_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.svcMachSerNum, sMsg.A.no(j).svcMachSerNum_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.cpoOrdNum, sMsg.A.no(j).cpoOrdNum_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.custIssPoNum, sMsg.A.no(j).custIssPoNum_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.custIssPoDt, sMsg.A.no(j).custIssPoDt_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.cpoOrdTpCd, sMsg.A.no(j).cpoOrdTpCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.rqstTechTocCd, sMsg.A.no(j).rqstTechTocCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.shipToFirstRefCmntTxt, sMsg.A.no(j).shipToFirstRefCmntTxt_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.shipToScdRefCmntTxt, sMsg.A.no(j).shipToScdRefCmntTxt_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.billToCustCd, sMsg.A.no(j).billToCustCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.sellToCustCd, sMsg.A.no(j).sellToCustCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.lineBizTpCd, sMsg.A.no(j).lineBizTpCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.adminPsnCd, sMsg.A.no(j).adminPsnCd_A1);
                // QC#21209: Delete spclInstnCmntTxt. spclInstnCmntTxt does not use.
                ZYPEZDItemValueSetter.setValue(sMsg.delyAddlCmntTxt, sMsg.A.no(j).delyAddlCmntTxt_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.rcvAddlCmntTxt, sMsg.A.no(j).rcvAddlCmntTxt_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.poQlfyCd, sMsg.A.no(j).poQlfyCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.batErrMsgTxt, sMsg.A.no(j).batErrMsgTxt_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.prchReqSavedFlg, sMsg.A.no(j).prchReqSavedFlg_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.prchReqApvlFlg, sMsg.A.no(j).prchReqApvlFlg_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.openStsFlg, sMsg.A.no(j).openStsFlg_A1);
                if (ZYPCommonFunc.hasValue(sMsg.A.no(j).defPrchReqLineTpCd_A1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.defPrchReqLineTpCd, sMsg.A.no(j).defPrchReqLineTpCd_A1);
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.defPrchReqLineTpCd, PRCH_REQ_LINE_TP.EXPENSE_2);
                }
                ZYPEZDItemValueSetter.setValue(sMsg.rtlWhCatgCd_SW, sMsg.A.no(j).rtlWhCatgCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.rtlWhCatgCd_DW, sMsg.A.no(j).rtlWhCatgCd_A2);

                if (PRCH_REQ_TP.DISPOSAL.equals(sMsg.prchReqTpCd_SL.getValue()) //
                        || PRCH_REQ_TP.VENDOR_RETURN.equals(sMsg.prchReqTpCd_SL.getValue()) //
                        || PRCH_REQ_TP.REFURBISHING.equals(sMsg.prchReqTpCd_SL.getValue()) //
                        || PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue()) //
                        || PRCH_REQ_TP.EXPENSE_ORDER.equals(sMsg.prchReqTpCd_SL.getValue())) {

                    ZYPEZDItemValueSetter.setValue(sMsg.shipToCustCd_EO, sMsg.A.no(j).shipToCustCd_E1);
                    ZYPEZDItemValueSetter.setValue(sMsg.shipToLocNm_EO, sMsg.A.no(j).shipToLocNm_E1);

                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.shipToCustCd_EO, sMsg.A.no(j).shipToCustCd_E1);
                    ZYPEZDItemValueSetter.setValue(sMsg.shipToLocNm_EO, sMsg.A.no(j).shipToLocNm_E1);
                }

                ZYPEZDItemValueSetter.setValue(sMsg.xxRqstTs, sMsg.A.no(j).xxRqstTs_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.xxRqstTz, sMsg.A.no(j).xxRqstTz_A1);

                isFirstLine = false;

            } else {
                if (!ZYPCommonFunc.hasValue(sMsg.srcRtlWhCd)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.srcRtlWhCd, sMsg.A.no(j).srcRtlWhCd_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.rtlWhNm_SW, sMsg.A.no(j).rtlWhNm_A1);
                } else if (!SCREEN_CTRL_VALUE_MULTIPLE.equals(sMsg.srcRtlWhCd.getValue()) && ZYPCommonFunc.hasValue(sMsg.A.no(j).srcRtlWhCd_A1) && !sMsg.srcRtlWhCd.getValue().equals(sMsg.A.no(j).srcRtlWhCd_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.srcRtlWhCd, SCREEN_CTRL_VALUE_MULTIPLE);
                    sMsg.rtlWhNm_SW.clear();
                    sMsg.rtlWhCatgCd_SW.clear();
                }
                if (!ZYPCommonFunc.hasValue(sMsg.srcRtlSwhCd)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.srcRtlSwhCd, sMsg.A.no(j).srcRtlSwhCd_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.rtlSwhNm_SS, sMsg.A.no(j).rtlSwhNm_A1);
                } else if (!SCREEN_CTRL_VALUE_MULTIPLE.equals(sMsg.srcRtlSwhCd.getValue()) && ZYPCommonFunc.hasValue(sMsg.A.no(j).srcRtlSwhCd_A1) && !sMsg.srcRtlSwhCd.getValue().equals(sMsg.A.no(j).srcRtlSwhCd_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.srcRtlSwhCd, SCREEN_CTRL_VALUE_MULTIPLE);
                    sMsg.rtlSwhNm_SS.clear();
                }
                if (!ZYPCommonFunc.hasValue(sMsg.destRtlWhCd)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.destRtlWhCd, sMsg.A.no(j).destRtlWhCd_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.rtlWhNm_DW, sMsg.A.no(j).rtlWhNm_A2);
                } else if (!SCREEN_CTRL_VALUE_MULTIPLE.equals(sMsg.destRtlWhCd.getValue()) && ZYPCommonFunc.hasValue(sMsg.A.no(j).destRtlWhCd_A1) && !sMsg.destRtlWhCd.getValue().equals(sMsg.A.no(j).destRtlWhCd_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.destRtlWhCd, SCREEN_CTRL_VALUE_MULTIPLE);
                    sMsg.rtlWhNm_DW.clear();
                    sMsg.rtlWhCatgCd_DW.clear();
                }
                if (!ZYPCommonFunc.hasValue(sMsg.destRtlSwhCd)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.destRtlSwhCd, sMsg.A.no(j).destRtlSwhCd_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.rtlSwhNm_DS, sMsg.A.no(j).rtlSwhNm_A2);
                } else if (!SCREEN_CTRL_VALUE_MULTIPLE.equals(sMsg.destRtlSwhCd.getValue()) && ZYPCommonFunc.hasValue(sMsg.A.no(j).destRtlSwhCd_A1) && !sMsg.destRtlSwhCd.getValue().equals(sMsg.A.no(j).destRtlSwhCd_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.destRtlSwhCd, SCREEN_CTRL_VALUE_MULTIPLE);
                    sMsg.rtlSwhNm_DS.clear();
                }
                if (!ZYPCommonFunc.hasValue(sMsg.shipToCustCd_EO)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.shipToCustCd_EO, sMsg.A.no(j).shipToCustCd_E1);
                    ZYPEZDItemValueSetter.setValue(sMsg.shipToLocNm_EO, sMsg.A.no(j).shipToLocNm_E1);
                } else if (!SCREEN_CTRL_VALUE_MULTIPLE.equals(sMsg.shipToCustCd_EO.getValue()) && ZYPCommonFunc.hasValue(sMsg.A.no(j).shipToCustCd_E1) && !sMsg.shipToCustCd_EO.getValue().equals(sMsg.A.no(j).shipToCustCd_E1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.shipToCustCd_EO, SCREEN_CTRL_VALUE_MULTIPLE);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.shipToLocNm_EO)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.shipToCustCd_EO, sMsg.A.no(j).shipToCustCd_E1);
                    ZYPEZDItemValueSetter.setValue(sMsg.shipToLocNm_EO, sMsg.A.no(j).shipToLocNm_E1);
                } else if (!SCREEN_CTRL_VALUE_MULTIPLE.equals(sMsg.shipToLocNm_EO.getValue()) && ZYPCommonFunc.hasValue(sMsg.A.no(j).shipToLocNm_E1) && !sMsg.shipToLocNm_EO.getValue().equals(sMsg.A.no(j).shipToLocNm_E1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.shipToLocNm_EO, SCREEN_CTRL_VALUE_MULTIPLE);
                }
            }
            // Mod End 2019/12/05 QC#54969

            getCoaEnableFlg(cMsg, sMsg, ZYPCommonFunc.concatString(BIZ_APP_ID, "", sMsg.prchReqTpCd_SL.getValue()), glblCmpyCd, j);
        }

        // total cost
        BigDecimal xxTotAmt = BigDecimal.ZERO;

        // Derive Amount
        // START 2018/05/25 S.Katsuma [QC#25893,MOD]
        if (!PRCH_REQ_TP.VENDOR_RETURN.equals(sMsg.prchReqTpCd_SL.getValue())) {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseCd_A1)) {
                    NLXC001001GetInventoryItemCostBean nlxc001001Bean = getInvtyItemCost(glblCmpyCd,
                            sMsg.A.no(i).srcRtlWhCd_A1.getValue(),
                            sMsg.A.no(i).srcRtlSwhCd_A1.getValue(),
                            sMsg.A.no(i).mdseCd_A1.getValue(),
                            sMsg.A.no(i).prchReqDispQty_A1.getValue());
                    if (!nlxc001001Bean.getErrList().isEmpty()) {
                        cMsg.setMessageInfo(nlxc001001Bean.getErrList().get(0));
                    } else {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).entDealNetUnitPrcAmt_A1, nlxc001001Bean.getUnitPrcAmt());
                        if (ZYPCommonFunc.hasValue(sMsg.A.no(i).prchReqDispQty_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(i).entDealNetUnitPrcAmt_A1)) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).entPoDtlDealNetAmt_A1, sMsg.A.no(i).entDealNetUnitPrcAmt_A1.getValue().multiply(ZYPCommonFunc.getBigDecimal(sMsg.A.no(i).prchReqDispQty_A1, "0")));
                        }
                        // total cost
                        if (ZYPCommonFunc.hasValue(sMsg.A.no(i).entPoDtlDealNetAmt_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(i).openStsFlg_A2) && ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).openStsFlg_A2.getValue())) {
                            xxTotAmt = xxTotAmt.add(sMsg.A.no(i).entPoDtlDealNetAmt_A1.getValue());
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                // total cost
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).entPoDtlDealNetAmt_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(i).openStsFlg_A2) && ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).openStsFlg_A2.getValue())) {
                    xxTotAmt = xxTotAmt.add(sMsg.A.no(i).entPoDtlDealNetAmt_A1.getValue());
                }
            }
        }
        // END 2018/05/25 S.Katsuma [QC#25893,MOD]

        // Copy 1 page Data(SMsg -> CMsg)
        int j = 0;
        for (; j < sMsg.A.getValidCount(); j++) {
            if (j == cMsg.A.length()) {
                break;
            }
            EZDMsg.copy(sMsg.A.no(j), null, cMsg.A.no(j), null);
        }

        // total cost
        ZYPEZDItemValueSetter.setValue(sMsg.xxTotAmt, xxTotAmt);
        cMsg.A.setValidCount(j);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum, sMsg.prchReqNum);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum_HD, sMsg.prchReqNum_HD);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpCd_SL, sMsg.prchReqTpCd_SL);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqStsCd, sMsg.prchReqStsCd);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqStsDescTxt, sMsg.prchReqStsDescTxt);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqApvlStsCd, sMsg.prchReqApvlStsCd);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqApvlStsDescTxt, sMsg.prchReqApvlStsDescTxt);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqCratDt, sMsg.prchReqCratDt);
        ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, sMsg.rqstRcvDt);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpCd, sMsg.prchReqSrcTpCd);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpDescTxt, sMsg.prchReqSrcTpDescTxt);
        ZYPEZDItemValueSetter.setValue(cMsg.trxRefNum, sMsg.trxRefNum);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        ZYPEZDItemValueSetter.setValue(cMsg.mrpPlnNm, sMsg.mrpPlnNm);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqRqstByPsnCd, sMsg.prchReqRqstByPsnCd);
        ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm, sMsg.fullPsnNm);
        ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_SL, sMsg.shpgSvcLvlCd_SL);
        ZYPEZDItemValueSetter.setValue(cMsg.carrCd, sMsg.carrCd);
        ZYPEZDItemValueSetter.setValue(cMsg.carrNm, sMsg.carrNm);
        ZYPEZDItemValueSetter.setValue(cMsg.ctacPsnNm, sMsg.ctacPsnNm);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqCmntTxt, sMsg.prchReqCmntTxt);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToLocNm, sMsg.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToAddlLocNm, sMsg.shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToFirstLineAddr, sMsg.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToScdLineAddr, sMsg.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToThirdLineAddr, sMsg.shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToFrthLineAddr, sMsg.shipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(cMsg.xxShipVndAddr, sMsg.xxShipVndAddr);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToPostCd, sMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCtyAddr, sMsg.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCntyNm, sMsg.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToStCd, sMsg.shipToStCd);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToProvNm, sMsg.shipToProvNm);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCtryCd, sMsg.shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(cMsg.ctryNm, sMsg.ctryNm);
        ZYPEZDItemValueSetter.setValue(cMsg.vndRtrnRsnCd_SL, sMsg.vndRtrnRsnCd_SL);
        ZYPEZDItemValueSetter.setValue(cMsg.srcRtlWhCd, sMsg.srcRtlWhCd);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_SW, sMsg.rtlWhNm_SW);
        ZYPEZDItemValueSetter.setValue(cMsg.srcRtlSwhCd, sMsg.srcRtlSwhCd);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_SS, sMsg.rtlSwhNm_SS);
        ZYPEZDItemValueSetter.setValue(cMsg.destRtlWhCd, sMsg.destRtlWhCd);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_DW, sMsg.rtlWhNm_DW);
        ZYPEZDItemValueSetter.setValue(cMsg.destRtlSwhCd, sMsg.destRtlSwhCd);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_DS, sMsg.rtlSwhNm_DS);
        ZYPEZDItemValueSetter.setValue(cMsg.prntVndCd, sMsg.prntVndCd);
        ZYPEZDItemValueSetter.setValue(cMsg.vndCd, sMsg.vndCd);
        ZYPEZDItemValueSetter.setValue(cMsg.dplyVndNm, sMsg.dplyVndNm);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqRecTpCd, sMsg.prchReqRecTpCd);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqCratByPsnCd, sMsg.prchReqCratByPsnCd);
        ZYPEZDItemValueSetter.setValue(cMsg.prchGrpCd, sMsg.prchGrpCd);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqApvlDt, sMsg.prchReqApvlDt);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqApvlByPsnCd, sMsg.prchReqApvlByPsnCd);
        ZYPEZDItemValueSetter.setValue(cMsg.fsrNum, sMsg.fsrNum);
        ZYPEZDItemValueSetter.setValue(cMsg.svcTaskNum, sMsg.svcTaskNum);
        ZYPEZDItemValueSetter.setValue(cMsg.svcMachSerNum, sMsg.svcMachSerNum);
        ZYPEZDItemValueSetter.setValue(cMsg.cpoOrdNum, sMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(cMsg.custIssPoNum, sMsg.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(cMsg.custIssPoDt, sMsg.custIssPoDt);
        ZYPEZDItemValueSetter.setValue(cMsg.cpoOrdTpCd, sMsg.cpoOrdTpCd);
        ZYPEZDItemValueSetter.setValue(cMsg.rqstTechTocCd, sMsg.rqstTechTocCd);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToFirstRefCmntTxt, sMsg.shipToFirstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToScdRefCmntTxt, sMsg.shipToScdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(cMsg.billToCustCd, sMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(cMsg.sellToCustCd, sMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(cMsg.lineBizTpCd, sMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(cMsg.adminPsnCd, sMsg.adminPsnCd);
        ZYPEZDItemValueSetter.setValue(cMsg.spclInstnCmntTxt, sMsg.spclInstnCmntTxt);
        ZYPEZDItemValueSetter.setValue(cMsg.delyAddlCmntTxt, sMsg.delyAddlCmntTxt);
        ZYPEZDItemValueSetter.setValue(cMsg.rcvAddlCmntTxt, sMsg.rcvAddlCmntTxt);
        ZYPEZDItemValueSetter.setValue(cMsg.poQlfyCd, sMsg.poQlfyCd);
        ZYPEZDItemValueSetter.setValue(cMsg.batErrMsgTxt, sMsg.batErrMsgTxt);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqSavedFlg, sMsg.prchReqSavedFlg);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqApvlFlg, sMsg.prchReqApvlFlg);
        ZYPEZDItemValueSetter.setValue(cMsg.openStsFlg, sMsg.openStsFlg);
        ZYPEZDItemValueSetter.setValue(cMsg.defPrchReqLineTpCd, sMsg.defPrchReqLineTpCd);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_SW, sMsg.rtlWhCatgCd_SW);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_DW, sMsg.rtlWhCatgCd_DW);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd_EO, sMsg.shipToCustCd_EO);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToLocNm_EO, sMsg.shipToLocNm_EO);
        ZYPEZDItemValueSetter.setValue(cMsg.xxRqstTs, sMsg.xxRqstTs);
        ZYPEZDItemValueSetter.setValue(cMsg.xxRqstTz, sMsg.xxRqstTz);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTotAmt, sMsg.xxTotAmt);

        // PR Line type Default
        ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRCH_REQ_TP_CD, cMsg.prchReqTpCd_SL.getValue());

        result = NPBL0020Query.getInstance().getRequisitionType(ssmParam);

        if (result.isCodeNormal()) {
            String defPrchReqLineTpCd = (String) result.getResultObject();
            NPBL0020CommonLogic.createPullDownLineType(cMsg, sMsg, glblCmpyCd, true, defPrchReqLineTpCd);
            ZYPEZDItemValueSetter.setValue(sMsg.defPrchReqLineTpCd, defPrchReqLineTpCd);
            ZYPEZDItemValueSetter.setValue(cMsg.defPrchReqLineTpCd, defPrchReqLineTpCd);
        } else {
            NPBL0020CommonLogic.createPullDownLineType(cMsg, sMsg, glblCmpyCd, true, null);
        }

        // Service Level Pulldown
        if (PRCH_REQ_TP.EXPENSE_ORDER.equals(cMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.WH_TRANSFER.equals(cMsg.prchReqTpCd_SL.getValue())) {
            NPBL0020CommonLogic.createPullDownShpgSvcLvl(cMsg, sMsg, glblCmpyCd);
        } else {
            NPBL0020CommonLogic.createPullDownRequestedShipMethod(cMsg, sMsg, glblCmpyCd);
        }

        // Setting Next Page
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(queryResCnt);

        allMultipleCheckSetCMsg(sMsg, cMsg);

        return false;

    }

    /**
     * Change PR Type
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @param salesDate String
     */
    public static void changePRType(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd, String salesDate) {

        // Service Level Pulldown
        if (PRCH_REQ_TP.EXPENSE_ORDER.equals(cMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.WH_TRANSFER.equals(cMsg.prchReqTpCd_SL.getValue())) {
            NPBL0020CommonLogic.createPullDownShpgSvcLvl(cMsg, sMsg, glblCmpyCd);
        } else {
            NPBL0020CommonLogic.createPullDownRequestedShipMethod(cMsg, sMsg, glblCmpyCd);
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRCH_REQ_TP_CD, cMsg.prchReqTpCd_SL.getValue());

        S21SsmEZDResult result = NPBL0020Query.getInstance().getRequisitionType(ssmParam);

        if (result.isCodeNormal()) {
            String defPrchReqLineTpCd = (String) result.getResultObject();
            NPBL0020CommonLogic.createPullDownLineType(cMsg, sMsg, glblCmpyCd, true, defPrchReqLineTpCd);
            if (ZYPCommonFunc.hasValue(defPrchReqLineTpCd)) {
                ZYPEZDItemValueSetter.setValue(sMsg.defPrchReqLineTpCd, defPrchReqLineTpCd);
                ZYPEZDItemValueSetter.setValue(cMsg.defPrchReqLineTpCd, defPrchReqLineTpCd);
            } else {
                ZYPEZDItemValueSetter.setValue(sMsg.defPrchReqLineTpCd, PRCH_REQ_LINE_TP.EXPENSE_2);
                ZYPEZDItemValueSetter.setValue(cMsg.defPrchReqLineTpCd, PRCH_REQ_LINE_TP.EXPENSE_2);
            }
        } else {
            NPBL0020CommonLogic.createPullDownLineType(cMsg, sMsg, glblCmpyCd, true, null);
        }

        // START 2018/04/03 S.Katsuma [QC#23521,25063,ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.chrgAcctEdtblFlg, getChrgAcctEdtblFlg(cMsg.prchReqTpCd_SL.getValue(), glblCmpyCd));
        // END 2018/04/03 S.Katsuma [QC#23521,25063,ADD]

        String locRoleTpCdList = NMXC100001EnableWH.getLocRoleTpForPopup(glblCmpyCd, BIZ_APP_ID, cMsg.prchReqTpCd_SL.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.xxLocRoleTpCdListTxt, locRoleTpCdList);
        ZYPEZDItemValueSetter.setValue(cMsg.xxLocRoleTpCdListTxt, locRoleTpCdList);

        cMsg.prchReqNum.clear();
        // // QC#22346 Start
        if (PRCH_REQ_TP.WH_TRANSFER.equals(cMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.DISPOSAL.equals(cMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.EXPENSE_ORDER.equals(cMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.REFURBISHING.equals(cMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.VENDOR_RETURN.equals(cMsg.prchReqTpCd_SL.getValue())) {

            PRCH_REQ_TPTMsg tMsg = new PRCH_REQ_TPTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.prchReqTpCd, cMsg.prchReqTpCd_SL.getValue());
            tMsg = (PRCH_REQ_TPTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.defPrchReqTpDaysNum)) {
                // START 2019/03/20 T.Ogura [QC#30769,MOD]
//                ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addBusinessDay(glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), tMsg.defPrchReqTpDaysNum.getValueInt()));
                ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addDays(ZYPDateUtil.getSalesDate(glblCmpyCd), tMsg.defPrchReqTpDaysNum.getValueInt()));
                // END   2019/03/20 T.Ogura [QC#30769,MOD]
            } else {
                BigDecimal defDaysAot = ZYPCodeDataUtil.getNumConstValue(DEF_PRCH_REQ_TP_AOT_DAYS, glblCmpyCd);
                // START 2019/03/20 T.Ogura [QC#30769,MOD]
//                ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addBusinessDay(glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), defDaysAot.intValue()));
                ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addDays(ZYPDateUtil.getSalesDate(glblCmpyCd), defDaysAot.intValue()));
                // END   2019/03/20 T.Ogura [QC#30769,MOD]
            }

        } else if (!ZYPCommonFunc.hasValue(cMsg.prchReqTpCd_SL)) {
            BigDecimal defDaysAot = ZYPCodeDataUtil.getNumConstValue(DEF_PRCH_REQ_TP_AOT_DAYS, glblCmpyCd);
            if (ZYPCommonFunc.hasValue(defDaysAot)) {
                // START 2019/03/20 T.Ogura [QC#30769,MOD]
//                ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addBusinessDay(glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), defDaysAot.intValue()));
                ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addDays(ZYPDateUtil.getSalesDate(glblCmpyCd), defDaysAot.intValue()));
                // END   2019/03/20 T.Ogura [QC#30769,MOD]
            } else {
                // START 2019/03/20 T.Ogura [QC#30769,MOD]
//                ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addBusinessDay(glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), defDaysAot.intValue()));
                ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.addDays(ZYPDateUtil.getSalesDate(glblCmpyCd), defDaysAot.intValue()));
                // END   2019/03/20 T.Ogura [QC#30769,MOD]
            }
        } else {
            cMsg.rqstRcvDt.clear();
        }
        // QC#22346 End

        // QC#17483
        // Header
        if (PRCH_REQ_TP.WH_TRANSFER.equals(cMsg.prchReqTpCd_SL.getValue())) {
//
            if (ZYPCommonFunc.hasValue(cMsg.srcRtlWhCd) 
                    || ZYPCommonFunc.hasValue(cMsg.srcRtlSwhCd) //
                    ||ZYPCommonFunc.hasValue(cMsg.vndCd) //
                    || ZYPCommonFunc.hasValue(cMsg.shipToCustCd_EO) //
                    || ZYPCommonFunc.hasValue(cMsg.shipToLocNm_EO) //
                    || ZYPCommonFunc.hasValue(cMsg.dplyVndNm) //
                    || ZYPCommonFunc.hasValue(cMsg.shipToLocNm_EO)) {

                clearAddress(cMsg);
            }

            cMsg.prntVndCd.clear();
            cMsg.vndCd.clear();
            cMsg.dplyVndNm.clear();
            cMsg.shipToCustCd_EO.clear();
            cMsg.shipToLocNm_EO.clear();

            if (ZYPCommonFunc.hasValue(cMsg.srcRtlWhCd)) {
                setRtlWh(cMsg, sMsg, glblCmpyCd, salesDate, true, false);
            }
            if (ZYPCommonFunc.hasValue(cMsg.srcRtlSwhCd)) {
                setRtlSwh(cMsg, sMsg, glblCmpyCd, salesDate, true, false);
            }
            if (ZYPCommonFunc.hasValue(cMsg.destRtlWhCd)) {
                clearAddress(cMsg);
                setRtlWh(cMsg, sMsg, glblCmpyCd, salesDate, false, false);
            }
            if (ZYPCommonFunc.hasValue(cMsg.destRtlSwhCd)) {
                clearAddress(cMsg);
                setRtlSwh(cMsg, sMsg, glblCmpyCd, salesDate, false, false);
            }

        } else if (PRCH_REQ_TP.DISPOSAL.equals(cMsg.prchReqTpCd_SL.getValue())//
                || PRCH_REQ_TP.VENDOR_RETURN.equals(cMsg.prchReqTpCd_SL.getValue())) {

            if (ZYPCommonFunc.hasValue(cMsg.destRtlWhCd) //
                    || ZYPCommonFunc.hasValue(cMsg.rtlWhNm_DW)) {

                clearAddress(cMsg);
            }

            cMsg.destRtlWhCd.clear();
            cMsg.rtlWhNm_DW.clear();
            cMsg.rtlWhCatgCd_DW.clear();
            cMsg.destRtlSwhCd.clear();
            cMsg.rtlSwhNm_DS.clear();

            if (ZYPCommonFunc.hasValue(cMsg.srcRtlWhCd)) {
                if (PRCH_REQ_TP.DISPOSAL.equals(cMsg.prchReqTpCd_SL.getValue())) {
                    clearAddress(cMsg);
                }
                setRtlWh(cMsg, sMsg, glblCmpyCd, salesDate, true, false);
            }
            if (ZYPCommonFunc.hasValue(cMsg.srcRtlSwhCd)) {
                if (PRCH_REQ_TP.DISPOSAL.equals(cMsg.prchReqTpCd_SL.getValue())) {
                    clearAddress(cMsg);
                }
                setRtlSwh(cMsg, sMsg, glblCmpyCd, salesDate, true, false);
            }
            if (ZYPCommonFunc.hasValue(cMsg.vndCd)) {
                clearAddress(cMsg);
                setShiptoSply(cMsg, sMsg, glblCmpyCd, false);
            } else if (PRCH_REQ_TP.VENDOR_RETURN.equals(cMsg.prchReqTpCd_SL.getValue()) && !ZYPCommonFunc.hasValue(cMsg.vndCd)) {
                clearAddress(cMsg);
            }

        } else if (PRCH_REQ_TP.REFURBISHING.equals(cMsg.prchReqTpCd_SL.getValue())) {

            if (ZYPCommonFunc.hasValue(cMsg.destRtlWhCd) //
                    || ZYPCommonFunc.hasValue(cMsg.rtlWhNm_DW)) {

                clearAddress(cMsg);
            }

            cMsg.destRtlWhCd.clear();
            cMsg.rtlWhNm_DW.clear();
            cMsg.rtlWhCatgCd_DW.clear();
            cMsg.destRtlSwhCd.clear();
            cMsg.rtlSwhNm_DS.clear();

            if (ZYPCommonFunc.hasValue(cMsg.srcRtlWhCd)) {
                setRtlWh(cMsg, sMsg, glblCmpyCd, salesDate, true, false);
            }
            if (ZYPCommonFunc.hasValue(cMsg.srcRtlSwhCd)) {
                setRtlSwh(cMsg, sMsg, glblCmpyCd, salesDate, true, false);
            }
            if (ZYPCommonFunc.hasValue(cMsg.vndCd)) {
                clearAddress(cMsg);
                setShiptoSply(cMsg, sMsg, glblCmpyCd, false);
            } else {
                clearAddress(cMsg);
            }

        } else if (PRCH_REQ_TP.EXPENSE_ORDER.equals(cMsg.prchReqTpCd_SL.getValue())) {

            if (ZYPCommonFunc.hasValue(cMsg.destRtlWhCd) //
                    || ZYPCommonFunc.hasValue(cMsg.rtlWhNm_DW) //
                    || ZYPCommonFunc.hasValue(cMsg.vndCd) //
                    || ZYPCommonFunc.hasValue(cMsg.dplyVndNm)) {

                clearAddress(cMsg);
            }

            cMsg.destRtlWhCd.clear();
            cMsg.rtlWhNm_DW.clear();
            cMsg.rtlWhCatgCd_DW.clear();
            cMsg.destRtlSwhCd.clear();
            cMsg.rtlSwhNm_DS.clear();
            cMsg.prntVndCd.clear();
            cMsg.vndCd.clear();
            cMsg.dplyVndNm.clear();

            if (ZYPCommonFunc.hasValue(cMsg.srcRtlWhCd)) {
                setRtlWh(cMsg, sMsg, glblCmpyCd, salesDate, true, false);
            }
            if (ZYPCommonFunc.hasValue(cMsg.srcRtlSwhCd)) {
                setRtlSwh(cMsg, sMsg, glblCmpyCd, salesDate, true, false);
            }
            if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd_EO)) {

                String dsAcctnm = NPBL0020CommonLogic.getShipToCustName(glblCmpyCd, cMsg.shipToCustCd_EO.getValue());

                if (ZYPCommonFunc.hasValue(dsAcctnm)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToLocNm_EO, dsAcctnm);
                }
                Map<String, String> record = getShipToCustAddr(cMsg, glblCmpyCd, cMsg.shipToCustCd_EO.getValue());
                if (record != null) {
                    setShipToAddr(cMsg, record);
                } else {
                    clearAddress(cMsg);
                }
            } else {
                clearAddress(cMsg);
            }

        }

        if ((PRCH_REQ_TP.DISPOSAL.equals(cMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.VENDOR_RETURN.equals(cMsg.prchReqTpCd_SL.getValue()) // 
                || PRCH_REQ_TP.REFURBISHING.equals(cMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue())) // 
                &&ZYPCommonFunc.hasValue(cMsg.shipToCustCd_EO)) {

            String dsAcctnm = NPBL0020CommonLogic.getShipToCustName(glblCmpyCd, cMsg.shipToCustCd_EO.getValue());

            if (ZYPCommonFunc.hasValue(dsAcctnm)) {
                ZYPEZDItemValueSetter.setValue(cMsg.shipToLocNm_EO, dsAcctnm);
            }
            Map<String, String> record = getShipToCustAddr(cMsg, glblCmpyCd, cMsg.shipToCustCd_EO.getValue());
            if (record != null) {
                setShipToAddr(cMsg, record);
            } else {
                clearAddress(cMsg);
            }
        }

        if (PRCH_REQ_TP.VENDOR_RETURN.equals(cMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.REFURBISHING.equals(cMsg.prchReqTpCd_SL.getValue())) {

            cMsg.svcConfigMstrPk.clear();
        }

        if (!PRCH_REQ_TP.VENDOR_RETURN.equals(cMsg.prchReqTpCd_SL.getValue())) {

            cMsg.vndRtrnRsnCd_SL.clear();

        }

        cMsg.trxRefNum.clear();
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        cMsg.mrpPlnNm.clear();
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        cMsg.xxTotAmt.clear();
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();

        copyFromCmsgOntoSmsg(cMsg, sMsg, false);
        ZYPTableUtil.clear(sMsg.A);

    }

    /**
     * Add New Line
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     */
    public static void addNewLine(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {

        if (sMsg.A.getValidCount() == sMsg.A.length()) {
            cMsg.setMessageInfo(NPAM0077E, new String[] {String.valueOf(sMsg.A.length()) });
            return;
        }

        copyFromCmsgOntoSmsg(cMsg, sMsg, false);

        int checkedCount = getCheckedCount(cMsg, sMsg);
        if (checkedCount > 1) {
            cMsg.setMessageInfo(NPAM1239E);
            return;
        }

        // QC#22376 Add value.
        int focusLineNumber = 0;

        // QC#22481. QC#22376 Update.
        if (!PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue()) && checkedCount == 0) {
            // Add New Line
            int newLineIndex = sMsg.A.getValidCount();
            int lastReqLineNum = 0;
            if (newLineIndex != 0) {
                lastReqLineNum = Integer.valueOf(sMsg.A.no(newLineIndex - 1).prchReqLineNum_A1.getValue());
            }
            sMsg.A.no(newLineIndex).clear();
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).xxChkBox_A1, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).prchReqLineNum_A1, String.format("%03d", lastReqLineNum + 1));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).prchReqLineSubNum_A1, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).xxScrItem20Txt_A1, String.format("%03d.%d", lastReqLineNum + 1, 0));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).prchReqLineTpCd_A1, sMsg.defPrchReqLineTpCd);

            if (ZYPCommonFunc.hasValue(cMsg.srcRtlWhCd) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.srcRtlWhCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).srcRtlWhCd_A1, cMsg.srcRtlWhCd);
                Map<String, String> record = findRtlWhName(sMsg, glblCmpyCd, cMsg.srcRtlWhCd.getValue(), newLineIndex);
                if (record != null) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).rtlWhNm_A1, record.get(DB_COLUMN_RTL_WH_NM));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).rtlWhCatgCd_A1, record.get(DB_COLUMN_RTL_WH_CATG_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_SW, record.get(DB_COLUMN_RTL_WH_CATG_CD));
                }
            } else if (ZYPCommonFunc.hasValue(cMsg.rtlWhNm_SW) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.rtlWhNm_SW.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).rtlWhNm_A1, cMsg.rtlWhNm_SW);
            }

            if (ZYPCommonFunc.hasValue(cMsg.srcRtlSwhCd) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.srcRtlSwhCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).srcRtlSwhCd_A1, cMsg.srcRtlSwhCd);
            }

            if (ZYPCommonFunc.hasValue(cMsg.destRtlWhCd) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.destRtlWhCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).destRtlWhCd_A1, cMsg.destRtlWhCd);
                Map<String, String> record = findRtlWhName(sMsg, glblCmpyCd, cMsg.destRtlWhCd.getValue(), newLineIndex);
                if (record != null) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).rtlWhNm_A2, record.get(DB_COLUMN_RTL_WH_NM));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).rtlWhCatgCd_A2, record.get(DB_COLUMN_RTL_WH_CATG_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_DW, record.get(DB_COLUMN_RTL_WH_CATG_CD));
                }
            } else if (ZYPCommonFunc.hasValue(cMsg.rtlWhNm_DW) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.rtlWhNm_DW.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).rtlWhNm_A2, cMsg.rtlWhNm_DW);
            }

            if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd_EO) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.shipToCustCd_EO.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).shipToCustCd_E1, cMsg.shipToCustCd_EO);
                String dsAcctNm = getShipToCustName(glblCmpyCd, cMsg.shipToCustCd_EO.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).shipToLocNm_E1, dsAcctNm);
            } else if (ZYPCommonFunc.hasValue(cMsg.shipToLocNm_EO) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.shipToLocNm_EO.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).shipToLocNm_E1, cMsg.shipToLocNm_EO);
            }

            if (ZYPCommonFunc.hasValue(cMsg.destRtlSwhCd) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.destRtlSwhCd.getValue())) {
                if (PRCH_REQ_TP.WH_TRANSFER.equals(cMsg.prchReqTpCd_SL.getValue())) {
                    // QC#24994 MOD START
                    //if (RTL_WH_CATG.SHOWROOM.equals(sMsg.A.no(newLineIndex).rtlWhCatgCd_A1.getValue()) || RTL_WH_CATG.SHOWROOM.equals(sMsg.A.no(newLineIndex).rtlWhCatgCd_A2.getValue())) {
                    if (RTL_WH_CATG.SHOWROOM.equals(sMsg.A.no(newLineIndex).rtlWhCatgCd_A1.getValue()) && RTL_WH_CATG.SHOWROOM.equals(sMsg.A.no(newLineIndex).rtlWhCatgCd_A2.getValue())) {
                        //ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).destRtlSwhCd_A1, cMsg.destRtlSwhCd);
                        sMsg.A.no(newLineIndex).destRtlSwhCd_A1.clear();
                    } else {
                        //sMsg.A.no(newLineIndex).destRtlSwhCd_A1.clear();
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).destRtlSwhCd_A1, cMsg.destRtlSwhCd);
                    }
                    // QC#24994 MOD END
                } else {
                    sMsg.A.no(newLineIndex).destRtlSwhCd_A1.clear();
                }
            }

            if (ZYPCommonFunc.hasValue(cMsg.vndCd) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.vndCd.getValue())) {

                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
                ssmParam.put(DB_PARAM_VND_CD, cMsg.vndCd.getValue());

                S21SsmEZDResult result = NPBL0020Query.getInstance().executeQueryResultSingle("findShipToSupplier", ssmParam);

                if (result.isCodeNormal()) {
                    Map<String, Object> retMap = (Map<String, Object>) result.getResultObject();
                    ZYPEZDItemValueSetter.setValue(cMsg.dplyVndNm, (String) retMap.get(DB_COLUMN_DPLY_VND_NM));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).vndCd_A1, cMsg.vndCd);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).dplyVndNm_A1, cMsg.dplyVndNm);
                }
            } else if (ZYPCommonFunc.hasValue(cMsg.dplyVndNm) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.dplyVndNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).dplyVndNm_A1, cMsg.dplyVndNm);
            }

            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).xxChkBox_A1, ZYPConstant.FLG_OFF_N);
            // QC#22481
            if (PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).xxChkBox_A1, ZYPConstant.FLG_OFF_N);
            }

            // set stock status
            if (ZYPCommonFunc.hasValue(sMsg.A.no(newLineIndex).prchReqLineTpCd_A1)) {
                String slctprchReqLineTpCd = sMsg.A.no(newLineIndex).prchReqLineTpCd_A1.getValue();
                String fromStkStsCd = "";
                for (int i = 0; i < sMsg.prchReqLineTpCd_PD.length(); i++) {
                    if (!ZYPCommonFunc.hasValue(sMsg.prchReqLineTpCd_PD.no(i))) {
                        // record end.
                        break;
                    } else {
                        if (slctprchReqLineTpCd.equals(sMsg.prchReqLineTpCd_PD.no(i).getValue())) {
                            fromStkStsCd = sMsg.fromStkStsCd_HD.no(i).getValue();
                            break;
                        }
                    }
                }
                if (ZYPCommonFunc.hasValue(fromStkStsCd)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).fromStkStsCd_A1, fromStkStsCd);
                } else {
                    // set stock status first record.
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).fromStkStsCd_A1, sMsg.fromStkStsCd_PD.no(IDX_0).getValue());
                }
            } else {
                // set stock status first record.
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).fromStkStsCd_A1, sMsg.fromStkStsCd_PD.no(IDX_0).getValue());
            }

            // QC#22518
            if (newLineIndex != 0) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).xxScrItem50Txt_A1, sMsg.A.no(newLineIndex - 1).xxScrItem50Txt_A1);
            } else {
                cMsg.xxNum.setValue(newLineIndex);
                if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd_EO)) {
                    getAccount(cMsg, sMsg, glblCmpyCd, newLineIndex, false, false);
                }
            }

            getCoaEnableFlg(cMsg, sMsg, ZYPCommonFunc.concatString(BIZ_APP_ID, "", sMsg.prchReqTpCd_SL.getValue()), glblCmpyCd, newLineIndex);

            sMsg.A.setValidCount(newLineIndex + 1);

            focusLineNumber = newLineIndex;

        // QC#22481
        } else if (PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue())) {

            // Add New Line
            int newLineIndex = sMsg.A.getValidCount();
            int lastReqLineNum = 0;
            if (newLineIndex != 0) {
                lastReqLineNum = Integer.valueOf(sMsg.A.no(newLineIndex - 1).prchReqLineNum_A1.getValue());
            }
            sMsg.A.no(newLineIndex).clear();
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).xxChkBox_A1, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).prchReqLineNum_A1, String.format("%03d", lastReqLineNum + 1));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).prchReqLineSubNum_A1, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).xxScrItem20Txt_A1, String.format("%03d.%d", lastReqLineNum + 1, 0));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).prchReqLineTpCd_A1, sMsg.defPrchReqLineTpCd);

            if (ZYPCommonFunc.hasValue(cMsg.srcRtlWhCd) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.srcRtlWhCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).srcRtlWhCd_A1, cMsg.srcRtlWhCd);
                Map<String, String> record = findRtlWhName(sMsg, glblCmpyCd, cMsg.srcRtlWhCd.getValue(), newLineIndex);
                if (record != null) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).rtlWhNm_A1, record.get(DB_COLUMN_RTL_WH_NM));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).rtlWhCatgCd_A1, record.get(DB_COLUMN_RTL_WH_CATG_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_SW, record.get(DB_COLUMN_RTL_WH_CATG_CD));
                }
            } else if (ZYPCommonFunc.hasValue(cMsg.rtlWhNm_SW) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.rtlWhNm_SW.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).rtlWhNm_A1, cMsg.rtlWhNm_SW);
            }

            if (ZYPCommonFunc.hasValue(cMsg.srcRtlSwhCd) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.srcRtlSwhCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).srcRtlSwhCd_A1, cMsg.srcRtlSwhCd);
            }

            if (ZYPCommonFunc.hasValue(cMsg.destRtlWhCd) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.destRtlWhCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).destRtlWhCd_A1, cMsg.destRtlWhCd);
                Map<String, String> record = findRtlWhName(sMsg, glblCmpyCd, cMsg.destRtlWhCd.getValue(), newLineIndex);
                if (record != null) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).rtlWhNm_A2, record.get(DB_COLUMN_RTL_WH_NM));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).rtlWhCatgCd_A2, record.get(DB_COLUMN_RTL_WH_CATG_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_DW, record.get(DB_COLUMN_RTL_WH_CATG_CD));
                }
            } else if (ZYPCommonFunc.hasValue(cMsg.rtlWhNm_DW) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.rtlWhNm_DW.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).rtlWhNm_A2, cMsg.rtlWhNm_DW);
            }

            if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd_EO) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.shipToCustCd_EO.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).shipToCustCd_E1, cMsg.shipToCustCd_EO);
                String dsAcctNm = getShipToCustName(glblCmpyCd, cMsg.shipToCustCd_EO.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).shipToLocNm_E1, dsAcctNm);
            } else if (ZYPCommonFunc.hasValue(cMsg.shipToLocNm_EO) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.shipToLocNm_EO.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).shipToLocNm_E1, cMsg.shipToLocNm_EO);
            }

            if (ZYPCommonFunc.hasValue(cMsg.destRtlSwhCd) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.destRtlSwhCd.getValue())) {
                sMsg.A.no(newLineIndex).destRtlSwhCd_A1.clear();
            }

            if (ZYPCommonFunc.hasValue(cMsg.vndCd) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.vndCd.getValue())) {

                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
                ssmParam.put(DB_PARAM_VND_CD, cMsg.vndCd.getValue());

                S21SsmEZDResult result = NPBL0020Query.getInstance().executeQueryResultSingle("findShipToSupplier", ssmParam);

                if (result.isCodeNormal()) {
                    Map<String, Object> retMap = (Map<String, Object>) result.getResultObject();
                    ZYPEZDItemValueSetter.setValue(cMsg.dplyVndNm, (String) retMap.get(DB_COLUMN_DPLY_VND_NM));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).vndCd_A1, cMsg.vndCd);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).dplyVndNm_A1, cMsg.dplyVndNm);
                }
            } else if (ZYPCommonFunc.hasValue(cMsg.dplyVndNm) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.dplyVndNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).dplyVndNm_A1, cMsg.dplyVndNm);
            }

            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).xxChkBox_A1, ZYPConstant.FLG_OFF_N);

            if (checkedCount != 0) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).trxRefLineNum_A1, cMsg.A.no(ZYPTableUtil.getSelectedRows(cMsg.A, "xxChkBox_A1", ZYPConstant.FLG_ON_Y).get(0)).trxRefLineNum_A1);
            }

            // set stock status
            if (ZYPCommonFunc.hasValue(sMsg.A.no(newLineIndex).prchReqLineTpCd_A1)) {
                String slctprchReqLineTpCd = sMsg.A.no(newLineIndex).prchReqLineTpCd_A1.getValue();
                String fromStkStsCd = "";
                for (int i = 0; i < sMsg.prchReqLineTpCd_PD.length(); i++) {
                    if (!ZYPCommonFunc.hasValue(sMsg.prchReqLineTpCd_PD.no(i))) {
                        // record end.
                        break;
                    } else {
                        if (slctprchReqLineTpCd.equals(sMsg.prchReqLineTpCd_PD.no(i).getValue())) {
                            fromStkStsCd = sMsg.fromStkStsCd_HD.no(i).getValue();
                            break;
                        }
                    }
                }
                if (ZYPCommonFunc.hasValue(fromStkStsCd)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).fromStkStsCd_A1, fromStkStsCd);
                } else {
                    // set stock status first record.
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).fromStkStsCd_A1, sMsg.fromStkStsCd_PD.no(IDX_0).getValue());
                }
            } else {
                // set stock status first record.
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).fromStkStsCd_A1, sMsg.fromStkStsCd_PD.no(IDX_0).getValue());
            }

            // QC#22518
            if (newLineIndex != 0) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).xxScrItem50Txt_A1, sMsg.A.no(newLineIndex - 1).xxScrItem50Txt_A1);
            } else {
                getAccount(cMsg, sMsg, glblCmpyCd, newLineIndex, false, false);
            }

            getCoaEnableFlg(cMsg, sMsg, ZYPCommonFunc.concatString(BIZ_APP_ID, "", sMsg.prchReqTpCd_SL.getValue()), glblCmpyCd, newLineIndex);

            sMsg.A.setValidCount(newLineIndex + 1);

            focusLineNumber = newLineIndex;

        }else {
            // Add ConfigLine
            int checkedConfigPos = getCheckedConfigPos(sMsg);
            if (checkedConfigPos == -1) {
                cMsg.setMessageInfo(NWAM0682E);
                return;
            }
            int checkedConfigItemLastPos = getCheckedConfigItemLastPos(sMsg);
            for (int i = sMsg.A.getValidCount() - 1; i > checkedConfigItemLastPos; i--) {
                EZDMsg.copy(sMsg.A.no(i), null, sMsg.A.no(i + 1), null);
            }
            sMsg.A.no(checkedConfigItemLastPos + 1).clear();
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).xxChkBox_A1, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).prchReqLineNum_A1, sMsg.A.no(checkedConfigPos).prchReqLineNum_A1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).prchReqLineSubNum_A1, sMsg.A.no(checkedConfigItemLastPos).prchReqLineSubNum_A1.getValue().add(BigDecimal.ONE));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).xxScrItem20Txt_A1, String.format("%s.%d", sMsg.A.no(checkedConfigItemLastPos + 1).prchReqLineNum_A1.getValue(),
                    sMsg.A.no(checkedConfigItemLastPos + 1).prchReqLineSubNum_A1.getValueInt()));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).configTpCd_A1, sMsg.A.no(checkedConfigPos).configTpCd_A1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).configTpDescTxt_A1, sMsg.A.no(checkedConfigPos).configTpDescTxt_A1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).prchReqLineTpCd_A1, sMsg.defPrchReqLineTpCd);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).svcConfigMstrPk_A1, sMsg.A.no(checkedConfigPos).svcConfigMstrPk_A1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).srcRtlWhCd_A1, sMsg.A.no(checkedConfigPos).srcRtlWhCd_A1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).rtlWhNm_A1, sMsg.A.no(checkedConfigPos).rtlWhNm_A1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).rtlWhCatgCd_A1, sMsg.A.no(checkedConfigPos).rtlWhCatgCd_A1);
            if (ZYPCommonFunc.hasValue(cMsg.srcRtlSwhCd) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.srcRtlSwhCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).srcRtlSwhCd_A1, cMsg.srcRtlSwhCd);
            }
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).destRtlWhCd_A1, sMsg.A.no(checkedConfigPos).destRtlWhCd_A1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).rtlWhNm_A2, sMsg.A.no(checkedConfigPos).rtlWhNm_A2);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).rtlWhCatgCd_A2, sMsg.A.no(checkedConfigPos).rtlWhCatgCd_A2);
            // QC#24994 MOD START
            //ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).destRtlSwhCd_A1, sMsg.A.no(checkedConfigItemLastPos + 1).srcRtlSwhCd_A1);
            if (ZYPCommonFunc.hasValue(cMsg.destRtlSwhCd) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.destRtlSwhCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).destRtlSwhCd_A1, cMsg.destRtlSwhCd);
            }
            // QC#24994 MOD END
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).vndCd_A1, cMsg.vndCd);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).dplyVndNm_A1, cMsg.dplyVndNm);

            ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).shipToCustCd_E1, sMsg.A.no(checkedConfigPos).shipToCustCd_E1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).shipToLocNm_E1, sMsg.A.no(checkedConfigPos).shipToLocNm_E1);

            ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).prchReqLineStsDescTxt_A1, sMsg.prchReqLineStsDescTxt);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos).xxChkBox_A1, ZYPConstant.FLG_OFF_N);

            // QC#22518
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).xxScrItem50Txt_A1, sMsg.A.no(checkedConfigItemLastPos).xxScrItem50Txt_A1);

            getCoaEnableFlg(cMsg, sMsg, ZYPCommonFunc.concatString(BIZ_APP_ID, "", sMsg.prchReqTpCd_SL.getValue()), glblCmpyCd, checkedConfigItemLastPos + 1);

            sMsg.A.setValidCount(sMsg.A.getValidCount() + 1);

            focusLineNumber = checkedConfigItemLastPos + 1;
        }

        setUpdateScreenValue(cMsg, sMsg, glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), true);

        allMultipleCheck(sMsg, glblCmpyCd);

        // QC#22376 Set Current Page.
        setPageShowFromNumOnCMsg(cMsg, sMsg, focusLineNumber);

        // QC#22376 Set Focus Line Number
        int pagePosition = focusLineNumber % cMsg.A.length();
        ZYPEZDItemValueSetter.setValue(cMsg.xxNum_FC, BigDecimal.valueOf(pagePosition));

        copyFromSMsgOntoCmsg(cMsg, sMsg);

    }

    /**
     * Add New Config
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     */
    public static void addNewConfig(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {

        if (sMsg.A.getValidCount() == sMsg.A.length()) {
            cMsg.setMessageInfo(NPAM0077E, new String[] {String.valueOf(sMsg.A.length()) });
            return;
        }

        copyFromCmsgOntoSmsg(cMsg, sMsg, false);

        int newLineIndex = sMsg.A.getValidCount();
        int lastReqLineNum = 0;
        if (newLineIndex != 0) {
            lastReqLineNum = Integer.valueOf(sMsg.A.no(newLineIndex - 1).prchReqLineNum_A1.getValue());
        }

        // QC#22376 Add. Clear check box.
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            sMsg.A.no(i).xxChkBox_A1.clear();
        }

        sMsg.A.no(newLineIndex).clear();
        // QC#22376 Add. Current Line Check.
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).xxChkBox_A1, ZYPConstant.CHKBOX_ON_Y);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).prchReqLineNum_A1, String.format("%03d", lastReqLineNum + 1));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).prchReqLineSubNum_A1, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).xxScrItem20Txt_A1, String.format("%03d.%d", lastReqLineNum + 1, 0));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).configTpCd_A1, CONFIG_TP.NEW);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).configTpDescTxt_A1, sMsg.configTpDescTxt_01);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).prchReqLineTpCd_A1, sMsg.defPrchReqLineTpCd);
        if (ZYPCommonFunc.hasValue(cMsg.srcRtlWhCd) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.srcRtlWhCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).srcRtlWhCd_A1, cMsg.srcRtlWhCd);
            Map<String, String> record = findRtlWhName(sMsg, glblCmpyCd, cMsg.srcRtlWhCd.getValue(), newLineIndex);
            // QC#57052
            if (record != null) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).rtlWhNm_A1, record.get(DB_COLUMN_RTL_WH_NM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).rtlWhCatgCd_A1, record.get(DB_COLUMN_RTL_WH_CATG_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_SW, record.get(DB_COLUMN_RTL_WH_CATG_CD));
            }
        }

        if (ZYPCommonFunc.hasValue(cMsg.destRtlWhCd) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.destRtlWhCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).destRtlWhCd_A1, cMsg.destRtlWhCd);
            Map<String, String> record = findRtlWhName(sMsg, glblCmpyCd, cMsg.destRtlWhCd.getValue(), newLineIndex);
            // QC#57052
            if (record != null) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).rtlWhNm_A2, record.get(DB_COLUMN_RTL_WH_NM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).rtlWhCatgCd_A2, record.get(DB_COLUMN_RTL_WH_CATG_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_DW, record.get(DB_COLUMN_RTL_WH_CATG_CD));
            }
        }

        if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd_EO) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.shipToCustCd_EO.getValue())) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).shipToCustCd_E1, cMsg.shipToCustCd_EO);
            if (ZYPCommonFunc.hasValue(cMsg.shipToLocNm_EO)) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).shipToLocNm_E1, cMsg.shipToLocNm_EO);
            } else {
                String dsAcctNm = getShipToCustName(glblCmpyCd, cMsg.shipToCustCd_EO.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).shipToLocNm_E1, dsAcctNm);
            }
        } else {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).shipToLocNm_E1, cMsg.shipToLocNm_EO);
        }

        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).vndCd_A1, cMsg.vndCd);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).dplyVndNm_A1, cMsg.dplyVndNm);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).prchReqLineStsDescTxt_A1, sMsg.prchReqLineStsDescTxt);

        getCoaEnableFlg(cMsg, sMsg, ZYPCommonFunc.concatString(BIZ_APP_ID, "", sMsg.prchReqTpCd_SL.getValue()), glblCmpyCd, newLineIndex);

        // set stock status = null
        sMsg.A.no(newLineIndex).fromStkStsCd_A1.clear();

        sMsg.A.setValidCount(newLineIndex + 1);

        sMsg.A.no(newLineIndex + 1).clear();
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex + 1).xxChkBox_A1, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex + 1).prchReqLineNum_A1, sMsg.A.no(newLineIndex).prchReqLineNum_A1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex + 1).prchReqLineSubNum_A1, sMsg.A.no(newLineIndex).prchReqLineSubNum_A1.getValue().add(BigDecimal.ONE));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex + 1).xxScrItem20Txt_A1, String.format("%s.%d", sMsg.A.no(newLineIndex + 1).prchReqLineNum_A1.getValue(), sMsg.A.no(newLineIndex + 1).prchReqLineSubNum_A1.getValueInt()));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex + 1).configTpCd_A1, sMsg.A.no(newLineIndex).configTpCd_A1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex + 1).configTpDescTxt_A1, sMsg.A.no(newLineIndex).configTpDescTxt_A1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex + 1).prchReqLineTpCd_A1, sMsg.defPrchReqLineTpCd);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex + 1).svcConfigMstrPk_A1, sMsg.A.no(newLineIndex).svcConfigMstrPk_A1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex + 1).srcRtlWhCd_A1, sMsg.A.no(newLineIndex).srcRtlWhCd_A1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex + 1).rtlWhNm_A1, sMsg.A.no(newLineIndex).rtlWhNm_A1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex + 1).rtlWhCatgCd_A1, sMsg.A.no(newLineIndex).rtlWhCatgCd_A1);
        if (ZYPCommonFunc.hasValue(cMsg.srcRtlSwhCd) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.srcRtlSwhCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex + 1).srcRtlSwhCd_A1, cMsg.srcRtlSwhCd);
        }
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex + 1).destRtlWhCd_A1, sMsg.A.no(newLineIndex).destRtlWhCd_A1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex + 1).rtlWhNm_A2, sMsg.A.no(newLineIndex).rtlWhNm_A2);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex + 1).rtlWhCatgCd_A2, sMsg.A.no(newLineIndex).rtlWhCatgCd_A2);
        // QC#24994 MOD START
        //ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex + 1).destRtlSwhCd_A1, sMsg.A.no(newLineIndex + 1).srcRtlSwhCd_A1);
        if (ZYPCommonFunc.hasValue(cMsg.srcRtlSwhCd) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.srcRtlSwhCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex + 1).destRtlSwhCd_A1, cMsg.destRtlSwhCd);
        }
        // QC#24994 MOD END
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex + 1).vndCd_A1, cMsg.vndCd);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex + 1).dplyVndNm_A1, cMsg.dplyVndNm);

        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex + 1).shipToCustCd_E1, sMsg.A.no(newLineIndex).shipToCustCd_E1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex + 1).shipToLocNm_E1, sMsg.A.no(newLineIndex).shipToLocNm_E1);

        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex + 1).prchReqLineStsDescTxt_A1, sMsg.prchReqLineStsDescTxt);

        // QC#22518
        if (ZYPCommonFunc.hasValue(sMsg.A.no(0).configTpCd_A1)) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex + 1).xxScrItem50Txt_A1, sMsg.A.no(1).xxScrItem50Txt_A1);   
        } else {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex + 1).xxScrItem50Txt_A1, sMsg.A.no(0).xxScrItem50Txt_A1);
        }

        if (ZYPCommonFunc.hasValue(sMsg.A.no(newLineIndex + 1).shipToCustCd_E1) && newLineIndex == 0) {
            cMsg.xxNum.setValue(newLineIndex + 1);
            getAccount(cMsg, sMsg, glblCmpyCd, newLineIndex + 1, false, false);
        }

        getCoaEnableFlg(cMsg, sMsg, ZYPCommonFunc.concatString(BIZ_APP_ID, "", sMsg.prchReqTpCd_SL.getValue()), glblCmpyCd, newLineIndex + 1);

        sMsg.A.setValidCount(sMsg.A.getValidCount() + 1);

        setUpdateScreenValue(cMsg, sMsg, glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), true);

        allMultipleCheck(sMsg, glblCmpyCd);

        // QC#22376 Set Current Page.
        setPageShowFromNumOnCMsg(cMsg, sMsg, sMsg.A.getValidCount() - 1);

        copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * Add Existing Config
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     */
    public static void addExistingConfig(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {

        // Check Duplicate Config
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).configTpCd_A1) && CONFIG_TP.EXISTING.equals(sMsg.A.no(i).configTpCd_A1.getValue())) {
                if (cMsg.svcConfigMstrPk.getValue().compareTo((sMsg.A.no(i).svcConfigMstrPk_A1.getValue())) == 0) {
                    cMsg.svcConfigMstrPk.setErrorInfo(1, NPAM0073E, new String[] {cMsg.svcConfigMstrPk.getValue().toPlainString() });
                    return;
                }
            }
        }

        // Check Service Machine Master
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_SVC_CONFIG_MSTR_PK, cMsg.svcConfigMstrPk.getValue());
        // START 2023/03/13 R.Azucena[QC#61282, MOD]
        // List<String> svcMachMstrStsCdList = new ArrayList<String>(2);
        // svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.CREATED);
        // svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.REMOVED);
        // ssmParam.put(DB_PARAM_SVC_MACH_MSTR_STS_CD_LIST, svcMachMstrStsCdList);
        // ssmParam.put(DB_PARAM_SVC_MACH_MAINT_AVAL_FLG, ZYPConstant.FLG_ON_Y);
        ssmParam.put(DB_PARAM_SVC_MACH_MSTR_STS_CD, SVC_MACH_MSTR_STS.TERMINATED);
        // END 2023/03/13 R.Azucena[QC#61282, MOD]

        S21SsmEZDResult result = NPBL0020Query.getInstance().getConfigComponents(ssmParam);

        // The entered @ does not exist in Master.
        if (!result.isCodeNormal()) {
            cMsg.setMessageInfo(NPAM1361E, new String[] {cMsg.svcConfigMstrPk.getValue().toPlainString() });
            return;
        }

        List<Map<String, Object>> configComponentList = (List<Map<String, Object>>) result.getResultObject();
        // START 2023/03/13 R.Azucena[QC#61282, MOD]
        // int count = configComponentList.size();
        List<String> errTrxHdrNumList = new ArrayList<String>();
        int count = getAvailableConfigItemCnt(configComponentList, errTrxHdrNumList);
        // END 2023/03/13 R.Azucena[QC#61282, MOD]

        if (count == 0) {
            cMsg.setMessageInfo(NPAM1361E, new String[] {cMsg.svcConfigMstrPk.getValue().toPlainString() });
            return;
        }

        // START 2023/03/13 R.Azucena[QC#61282, ADD]
        if (count != configComponentList.size()) {
            cMsg.svcConfigMstrPk.setErrorInfo(1, NPBM0024E, new String[] {errTrxHdrNumList.toString() });
            return;
        }
        // END 2023/03/13 R.Azucena[QC#61282, ADD]

        if (sMsg.A.getValidCount() + count + 1 > sMsg.A.length()) {
            cMsg.setMessageInfo(NPAM0077E, new String[] {String.valueOf(sMsg.A.length()) });
            return;
        }

        copyFromCmsgOntoSmsg(cMsg, sMsg, false);

        // Add config line
        int newLineIndex = sMsg.A.getValidCount();
        int lastReqLineNum = 0;
        if (newLineIndex != 0) {
            lastReqLineNum = Integer.valueOf(sMsg.A.no(newLineIndex - 1).prchReqLineNum_A1.getValue());
        }
        Map<String, Object> firstRecord = configComponentList.get(0);
        sMsg.A.no(newLineIndex).clear();
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).prchReqLineNum_A1, String.format("%03d", lastReqLineNum + 1));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).prchReqLineSubNum_A1, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).xxScrItem20Txt_A1, String.format("%03d.%d", lastReqLineNum + 1, 0));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).configTpCd_A1, CONFIG_TP.EXISTING);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).configTpDescTxt_A1, sMsg.configTpDescTxt_03);
        if (ZYPCommonFunc.hasValue(sMsg.defPrchReqLineTpCd)) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).prchReqLineTpCd_A1, sMsg.defPrchReqLineTpCd);
        } else {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).prchReqLineTpCd_A1, PRCH_REQ_LINE_TP.EXPENSE_2);
        }
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).svcConfigMstrPk_A1, sMsg.svcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).srcRtlWhCd_A1, (String) firstRecord.get(DB_COLUMN_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).rtlWhNm_A1, (String) firstRecord.get(DB_COLUMN_RTL_WH_NM));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).rtlWhCatgCd_A1, (String) firstRecord.get(DB_COLUMN_RTL_WH_CATG_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).srcRtlSwhCd_A1, (String) firstRecord.get(DB_COLUMN_RTL_SWH_CD));

        if (ZYPCommonFunc.hasValue(cMsg.destRtlWhCd) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.destRtlWhCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).destRtlWhCd_A1, cMsg.destRtlWhCd);
            Map<String, String> record = findRtlWhName(sMsg, glblCmpyCd, cMsg.destRtlWhCd.getValue(), newLineIndex);
            // QC#57052
            if (record != null) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).rtlWhNm_A2, record.get(DB_COLUMN_RTL_WH_NM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).rtlWhCatgCd_A2, record.get(DB_COLUMN_RTL_WH_CATG_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_DW, record.get(DB_COLUMN_RTL_WH_CATG_CD));
            }
        }

        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).vndCd_A1, cMsg.vndCd);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).dplyVndNm_A1, cMsg.dplyVndNm);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).prchReqLineStsDescTxt_A1, sMsg.prchReqLineStsDescTxt);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).xxChkBox_A1, ZYPConstant.FLG_OFF_N);

        if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd_EO) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.shipToCustCd_EO.getValue())) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).shipToCustCd_E1, cMsg.shipToCustCd_EO);
            if (ZYPCommonFunc.hasValue(cMsg.shipToLocNm_EO)) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).shipToLocNm_E1, cMsg.shipToLocNm_EO);
            } else {
                String dsAcctNm = getShipToCustName(glblCmpyCd, cMsg.shipToCustCd_EO.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).shipToLocNm_E1, dsAcctNm);
            }
        } else {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).shipToLocNm_E1, cMsg.shipToLocNm_EO);
        }

        getCoaEnableFlg(cMsg, sMsg, ZYPCommonFunc.concatString(BIZ_APP_ID, "", sMsg.prchReqTpCd_SL.getValue()), glblCmpyCd, newLineIndex);

        // stock status = null
        sMsg.A.no(newLineIndex).fromStkStsCd_A1.clear();

        int configLineNum = newLineIndex;
        newLineIndex++;
        sMsg.A.setValidCount(newLineIndex);

        // Add item line
        int lineSubNum = 1;
        for (int i = 0; i < configComponentList.size(); i++) {
            sMsg.A.no(newLineIndex).clear();
            Map<String, Object> record = configComponentList.get(i);

            // Get MDSE Description Short Name
            Map<String, Object> ssmParamForItem = new HashMap<String, Object>();
            ssmParamForItem.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParamForItem.put(DB_PARAM_MDSE_CD, (String) record.get(DB_COLUMN_MDSE_CD));
            ssmParamForItem.put(DB_PARAM_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);

            S21SsmEZDResult resultItem = NPBL0020Query.getInstance().getItemDescription(ssmParamForItem);

            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).prchReqLineNum_A1, sMsg.A.no(configLineNum).prchReqLineNum_A1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).prchReqLineSubNum_A1, new BigDecimal(lineSubNum));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).xxScrItem20Txt_A1, String.format("%s.%d", sMsg.A.no(newLineIndex).prchReqLineNum_A1.getValue(), sMsg.A.no(newLineIndex).prchReqLineSubNum_A1.getValueInt()));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).configTpCd_A1, sMsg.A.no(configLineNum).configTpCd_A1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).configTpDescTxt_A1, sMsg.A.no(configLineNum).configTpDescTxt_A1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).prchReqLineTpCd_A1, sMsg.A.no(configLineNum).prchReqLineTpCd_A1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).mdseCd_A1, (String) record.get(DB_COLUMN_MDSE_CD));
            // START 2022/11/16 R.Azucena[QC#60808, ADD]
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).svcMachMstrPk_A1, (BigDecimal) record.get(DB_COLUMN_SVC_MACH_MSTR_PK));
            // END 2022/11/16 R.Azucena[QC#60808, ADD]
            if (resultItem.isCodeNormal()) {
                String mdseDescShortTxt = (String) resultItem.getResultObject();
                if (ZYPCommonFunc.hasValue(mdseDescShortTxt)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).mdseDescShortTxt_A1, mdseDescShortTxt);
                }
            }
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).svcConfigMstrPk_A1, sMsg.A.no(configLineNum).svcConfigMstrPk_A1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).prchReqDispQty_A1, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).srcRtlWhCd_A1, (String) record.get(DB_COLUMN_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).rtlWhNm_A1, sMsg.A.no(configLineNum).rtlWhNm_A1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).rtlWhCatgCd_A1, sMsg.A.no(configLineNum).rtlWhCatgCd_A1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).srcRtlSwhCd_A1, (String) record.get(DB_COLUMN_RTL_SWH_CD));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).destRtlWhCd_A1, sMsg.A.no(configLineNum).destRtlWhCd_A1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).rtlWhNm_A2, sMsg.A.no(configLineNum).rtlWhNm_A2);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).rtlWhCatgCd_A2, sMsg.A.no(configLineNum).rtlWhCatgCd_A2);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).destRtlSwhCd_A1, sMsg.A.no(configLineNum).destRtlSwhCd_A1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).prchReqLineStsDescTxt_A1, sMsg.prchReqLineStsDescTxt);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).xxLogDtlTxt_A1, (String) record.get(DB_COLUMN_SER_NUM));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).xxChkBox_A1, ZYPConstant.FLG_OFF_N);

            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).shipToCustCd_E1, sMsg.A.no(configLineNum).shipToCustCd_E1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).shipToLocNm_E1, sMsg.A.no(configLineNum).shipToLocNm_E1);

            // set stock status
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).fromStkStsCd_A1, (String) record.get(DB_COLUMN_STK_STS_CD));

            // QC#22518
            if (ZYPCommonFunc.hasValue(sMsg.A.no(0).configTpCd_A1)) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).xxScrItem50Txt_A1, sMsg.A.no(1).xxScrItem50Txt_A1);   
            } else {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).xxScrItem50Txt_A1, sMsg.A.no(0).xxScrItem50Txt_A1);
            }

            if (ZYPCommonFunc.hasValue(sMsg.A.no(newLineIndex).shipToCustCd_E1) && configLineNum == 0) {
                cMsg.xxNum.setValue(newLineIndex);
                getAccount(cMsg, sMsg, glblCmpyCd, newLineIndex, false, false);
            }

            getCoaEnableFlg(cMsg, sMsg, ZYPCommonFunc.concatString(BIZ_APP_ID, "", sMsg.prchReqTpCd_SL.getValue()), glblCmpyCd, newLineIndex);

            newLineIndex++;
            lineSubNum++;
        }
        sMsg.A.setValidCount(newLineIndex);

        setUpdateScreenValue(cMsg, sMsg, glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), true);

        allMultipleCheck(sMsg, glblCmpyCd);

        copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * Set Ship To Address
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @param shipToCustCd String
     * @return boolean
     */
    public static boolean setShipToAddress(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd, String shipToCustCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_SHIP_TO_CUST_CD, shipToCustCd);
        ssmParam.put(DB_PARAM_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);

        S21SsmEZDResult result = NPBL0020Query.getInstance().getShipToAddress(ssmParam);

        if (!result.isCodeNormal()) {
            clearAddress(cMsg);
            cMsg.vndCd.setErrorInfo(1, NPAM0076E, new String[] {DISPLAY_NM_VND_CD });
            sMsg.vndCd.setErrorInfo(1, NPAM0076E, new String[] {DISPLAY_NM_VND_CD });
            return false;
        }

        List<Map<String, String>> shipToAddressList = (List<Map<String, String>>) result.getResultObject();
        if (shipToAddressList.size() == 0) {
            clearAddress(cMsg);
            cMsg.vndCd.setErrorInfo(1, NPAM0076E, new String[] {DISPLAY_NM_VND_CD });
            sMsg.vndCd.setErrorInfo(1, NPAM0076E, new String[] {DISPLAY_NM_VND_CD });
            return false;
        }
        Map<String, String> record = shipToAddressList.get(0);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToLocNm, (String) record.get(DB_COLUMN_LOC_NM));
        ZYPEZDItemValueSetter.setValue(cMsg.shipToAddlLocNm, (String) record.get(DB_COLUMN_ADDL_LOC_NM));
        ZYPEZDItemValueSetter.setValue(cMsg.shipToFirstLineAddr, (String) record.get(DB_COLUMN_FIRST_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(cMsg.shipToScdLineAddr, (String) record.get(DB_COLUMN_SCD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(cMsg.shipToThirdLineAddr, (String) record.get(DB_COLUMN_THIRD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(cMsg.shipToFrthLineAddr, (String) record.get(DB_COLUMN_FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(cMsg.xxShipVndAddr, (String) record.get(DB_COLUMN_SHIP_VND_ADDR));
        ZYPEZDItemValueSetter.setValue(cMsg.shipToPostCd, (String) record.get(DB_COLUMN_POST_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCtyAddr, (String) record.get(DB_COLUMN_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCntyNm, (String) record.get(DB_COLUMN_CNTY_NM));
        ZYPEZDItemValueSetter.setValue(cMsg.shipToStCd, (String) record.get(DB_COLUMN_ST_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.shipToProvNm, (String) record.get(DB_COLUMN_PROV_NM));
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCtryCd, (String) record.get(DB_COLUMN_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.ctryNm, (String) record.get(DB_COLUMN_CTRY_NM));

        return true;
    }

    /**
     * Set Warehouse Category
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     */
    public static void setWarehouseCategory(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);

        // QC#24994 MOD START
        if (SOURCE_WAREHOUSE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue())) {
            ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.srcRtlWhCd.getValue());
        } else if (SOURCE_SUB_WAREHOUSE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue())) {
            ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.srcRtlWhCd.getValue());
        } else if (DESTINATION_WAREHOUSE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue())) {
            ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.destRtlWhCd.getValue());
        } else if (DESTINATION_SUB_WAREHOUSE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue())) {
            ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.destRtlWhCd.getValue());
        } else {
            ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.P.no(IDX_6).xxPopPrm.getValue());
        }
        //} else if (SOURCE_WAREHOUSE_FOR_LINE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue())) {
        //    ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.A.no(cMsg.xxNum.getValueInt()).srcRtlWhCd_A1.getValue());
        //} else if (DESTINATION_WAREHOUSE_FOR_LINE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue())) {
        //    ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.A.no(cMsg.xxNum.getValueInt()).destRtlWhCd_A1.getValue());
        //} else if (SOURCE_SUB_WAREHOUSE_FOR_LINE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue())) {
        //    ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.A.no(cMsg.xxNum.getValueInt()).srcRtlWhCd_A1.getValue());
        //} else if (DESTINATION_SUB_WAREHOUSE_FOR_LINE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue())) {
        //    ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.A.no(cMsg.xxNum.getValueInt()).destRtlWhCd_A1.getValue());
        //}
        // QC#24994 MOD END

        S21SsmEZDResult result = NPBL0020Query.getInstance().findRtlWh(ssmParam);

        if (!result.isCodeNormal()) {
            return;
        }

        List<Map<String, String>> warehouseCategoryList = (List<Map<String, String>>) result.getResultObject();
        if (warehouseCategoryList.size() == 0) {
            return;
        }

        Map<String, String> record = warehouseCategoryList.get(0);
        if (SOURCE_WAREHOUSE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_SW, (String) record.get(DB_COLUMN_RTL_WH_CATG_CD));
        } else if (SOURCE_SUB_WAREHOUSE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_SW, (String) record.get(DB_COLUMN_RTL_WH_CATG_CD));
        } else if (DESTINATION_WAREHOUSE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_DW, (String) record.get(DB_COLUMN_RTL_WH_CATG_CD));
        } else if (DESTINATION_SUB_WAREHOUSE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_DW, (String) record.get(DB_COLUMN_RTL_WH_CATG_CD));
        // QC#24994 MOD START
        } else if (SOURCE_WAREHOUSE_FOR_LINE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(cMsg.xxNum.getValueInt()).rtlWhCatgCd_A1, (String) record.get(DB_COLUMN_RTL_WH_CATG_CD));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(cMsg.xxNum.getValueInt()).rtlWhCatgCd_A1, (String) record.get(DB_COLUMN_RTL_WH_CATG_CD));
        } else if (DESTINATION_WAREHOUSE_FOR_LINE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(cMsg.xxNum.getValueInt()).rtlWhCatgCd_A2, (String) record.get(DB_COLUMN_RTL_WH_CATG_CD));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(cMsg.xxNum.getValueInt()).rtlWhCatgCd_A2, (String) record.get(DB_COLUMN_RTL_WH_CATG_CD));
        } else if (SOURCE_SUB_WAREHOUSE_FOR_LINE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(cMsg.xxNum.getValueInt()).rtlWhCatgCd_A1, (String) record.get(DB_COLUMN_RTL_WH_CATG_CD));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(cMsg.xxNum.getValueInt()).rtlWhCatgCd_A1, (String) record.get(DB_COLUMN_RTL_WH_CATG_CD));
        } else if (DESTINATION_SUB_WAREHOUSE_FOR_LINE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(cMsg.xxNum.getValueInt()).rtlWhCatgCd_A2, (String) record.get(DB_COLUMN_RTL_WH_CATG_CD));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(cMsg.xxNum.getValueInt()).rtlWhCatgCd_A2, (String) record.get(DB_COLUMN_RTL_WH_CATG_CD));
        // QC#24994 MOD END
        }
    }

    /**
     * Set Carrier
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     */
    public static void setCarrier(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);

        if (DESTINATION_WAREHOUSE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue())) {
            ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.destRtlWhCd.getValue());
        } else if (DESTINATION_SUB_WAREHOUSE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue())) {
            ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.destRtlWhCd.getValue());
        } else if (DESTINATION_WAREHOUSE_FOR_LINE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue())) {
            ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.A.no(cMsg.xxNum.getValueInt()).destRtlWhCd_A1.getValue());
        } else if (SOURCE_SUB_WAREHOUSE_FOR_LINE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue())) {
            ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.A.no(cMsg.xxNum.getValueInt()).srcRtlWhCd_A1.getValue());
        } else if (DESTINATION_SUB_WAREHOUSE_FOR_LINE.equals(cMsg.P.no(IDX_12).xxPopPrm.getValue())) {
            ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.A.no(cMsg.xxNum.getValueInt()).destRtlWhCd_A1.getValue());
        }

        S21SsmEZDResult result = NPBL0020Query.getInstance().findCarrierFromRtlWH(ssmParam);

        if (!result.isCodeNormal()) {
            return;
        }

        List<Map<String, String>> carrierList = (List<Map<String, String>>) result.getResultObject();
        if (carrierList.size() == 0) {
            return;
        }
        Map<String, String> record = carrierList.get(0);
        ZYPEZDItemValueSetter.setValue(cMsg.carrCd, (String) record.get(DB_COLUMN_PRF_CARR_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.carrNm, (String) record.get(DB_COLUMN_CARR_NM));
    }

    /**
     * 02/08/2019 QC#30181 original interface.
     * Get Account
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @param rowIndex int
     * @return boolean
     */
    public static boolean getAccount(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd, int rowIndex, boolean submitFlg, boolean winFlg) {
        return getAccount(cMsg, sMsg, glblCmpyCd, rowIndex, submitFlg, winFlg, false);
    }

    /**
     * 02/08/2019 QC#30181 Add param forceFlg
     * Get Account
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @param rowIndex int
     * @param forceFlg true: force get account false. not force get account.
     * @return boolean
     */
    public static boolean getAccount(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd, int rowIndex, boolean submitFlg, boolean winFlg, boolean forceFlg) {

        // START 2018/04/03 S.Katsuma [QC#23521,25063,ADD]
        if (!NPBL0020CommonLogic.isNeededAccount(cMsg.chrgAcctEdtblFlg.getValue())) {
            return true;
        }
        // END 2018/04/03 S.Katsuma [QC#23521,25063,ADD]

        cMsg.P.clear();

        if (PRCH_REQ_TP.WH_TRANSFER.equals(cMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue())) {

            Map<String, Object> ssmParamBranch = new HashMap<String, Object>();
            ssmParamBranch.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            if (PRCH_REQ_TP.WH_TRANSFER.equals(cMsg.prchReqTpCd_SL.getValue())) {
                ssmParamBranch.put(DB_PARAM_RTL_WH_CD, sMsg.A.no(rowIndex).destRtlWhCd_A1.getValue());
            } else {
                ssmParamBranch.put(DB_PARAM_RTL_WH_CD, cMsg.destRtlWhCd.getValue());
            }

            S21SsmEZDResult resultBranch = NPBL0020Query.getInstance().findAccountBranchFromRtlWH(ssmParamBranch);

            List<Map<String, String>> accountBranchList = (List<Map<String, String>>) resultBranch.getResultObject();

            Map<String, String> defaultRecord = getDefaultSegment(cMsg, ZYPCommonFunc.concatString(BIZ_APP_ID, "", cMsg.prchReqTpCd_SL.getValue()), glblCmpyCd);

            Map<String, String> record = new HashMap<String, String>();
            if (accountBranchList.size() != 0) {
                record = accountBranchList.get(0);
            }
            Map<String, String> vndRecord = new HashMap<String, String>();
            vndRecord.put(DB_COLUMN_COA_BR_CD, (String) record.get(DB_COLUMN_COA_BR_CD));

            Map<String, Object> ssmParamAccount = new HashMap<String, Object>();
            ssmParamAccount.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParamAccount.put(DB_PARAM_MDSE_CD, sMsg.A.no(rowIndex).mdseCd_A1.getValue());
            ssmParamAccount.put(DB_PARAM_PRCH_REQ_TP, cMsg.prchReqTpCd_SL.getValue());

            S21SsmEZDResult resultAccountAcct = NPBL0020Query.getInstance().findAccountAcctFromRtlWH(ssmParamAccount);

            List<Map<String, String>> accountAcctList = (List<Map<String, String>>) resultAccountAcct.getResultObject();

            if (accountAcctList.size() != 0) {
                record = accountAcctList.get(0);
            }
            vndRecord.put(DB_COLUMN_COA_ACCT_CD, (String) record.get(DB_COLUMN_COA_ACCT_CD));

            // Account Check(WH_TRANSFER, SUBCONTRACT)
            if (!submitFlg) {
                int idx = cMsg.xxNum.getValueInt();

                accountBranchList = new ArrayList<Map<String, String>>();
                Map<String, String> vndMap = new HashMap<String, String>();

                if (ZYPCommonFunc.hasValue(vndRecord.get(DB_COLUMN_COA_BR_CD))) {
                    vndMap.put(DB_COLUMN_COA_BR_CD, (String) vndRecord.get(DB_COLUMN_COA_BR_CD));
                } else {
                    vndMap.put(DB_COLUMN_COA_BR_CD, (String) defaultRecord.get(DB_COLUMN_COA_BR_CD));
                }

                if (ZYPCommonFunc.hasValue(vndRecord.get(DB_COLUMN_COA_ACCT_CD))) {
                    vndMap.put(DB_COLUMN_COA_ACCT_CD, (String) vndRecord.get(DB_COLUMN_COA_ACCT_CD));
                } else {
                    vndMap.put(DB_COLUMN_COA_ACCT_CD, (String) defaultRecord.get(DB_COLUMN_COA_ACCT_CD));
                }

                vndMap.put(DB_COLUMN_COA_CMPY_CD, (String) defaultRecord.get(DB_COLUMN_COA_CMPY_CD));
                vndMap.put(DB_COLUMN_COA_AFFL_CD, (String) defaultRecord.get(DB_COLUMN_COA_AFFL_CD));
                vndMap.put(DB_COLUMN_COA_CC_CD, (String) defaultRecord.get(DB_COLUMN_COA_CC_CD));
                vndMap.put(DB_COLUMN_COA_PROD_CD, (String) defaultRecord.get(DB_COLUMN_COA_PROD_CD));
                vndMap.put(DB_COLUMN_COA_CH_CD, (String) defaultRecord.get(DB_COLUMN_COA_CH_CD));
                vndMap.put(DB_COLUMN_COA_PROJ_CD, (String) defaultRecord.get(DB_COLUMN_COA_PROJ_CD));
                vndMap.put(DB_COLUMN_COA_EXTN_CD, (String) defaultRecord.get(DB_COLUMN_COA_EXTN_CD));

                vndMap.put(DB_COLUMN_COA_CMPY_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_CMPY_DPLY_FLG));
                vndMap.put(DB_COLUMN_COA_AFFL_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_AFFL_DPLY_FLG));
                vndMap.put(DB_COLUMN_COA_BR_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_BR_DPLY_FLG));
                vndMap.put(DB_COLUMN_COA_CC_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_CC_DPLY_FLG));
                vndMap.put(DB_COLUMN_COA_ACCT_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_ACCT_DPLY_FLG));
                vndMap.put(DB_COLUMN_COA_PROD_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_PROD_DPLY_FLG));
                vndMap.put(DB_COLUMN_COA_CH_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_PROD_DPLY_FLG));
                vndMap.put(DB_COLUMN_COA_PROJ_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_PROJ_DPLY_FLG));
                vndMap.put(DB_COLUMN_COA_EXTN_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_EXTN_DPLY_FLG));

                accountBranchList.add(vndMap);
                record = accountBranchList.get(0);

                if (ZYPCommonFunc.hasValue(cMsg.A.no(idx).xxScrItem50Txt_A1)) {

                    if (!checkManualSegmentElement(cMsg, sMsg, glblCmpyCd, record, ZYPCommonFunc.concatString(BIZ_APP_ID, "", cMsg.prchReqTpCd_SL.getValue()))) {
                        return false;
                    }

                } else {

                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_1).xxPopPrm, (String) record.get(DB_COLUMN_COA_CMPY_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_2).xxPopPrm, (String) record.get(DB_COLUMN_COA_AFFL_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_3).xxPopPrm, (String) record.get(DB_COLUMN_COA_BR_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_4).xxPopPrm, (String) record.get(DB_COLUMN_COA_CC_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_5).xxPopPrm, (String) record.get(DB_COLUMN_COA_ACCT_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_6).xxPopPrm, (String) record.get(DB_COLUMN_COA_PROD_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_7).xxPopPrm, (String) record.get(DB_COLUMN_COA_CH_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_8).xxPopPrm, (String) record.get(DB_COLUMN_COA_PROJ_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_9).xxPopPrm, (String) record.get(DB_COLUMN_COA_EXTN_CD));

                }

            } else {
                // sMsg
                if (ZYPCommonFunc.hasValue(sMsg.A.no(rowIndex).xxScrItem50Txt_A1)) {
                    accountBranchList = new ArrayList<Map<String, String>>();
                    Map<String, String> vndMap = new HashMap<String, String>();

                    if (ZYPCommonFunc.hasValue(vndRecord.get(DB_COLUMN_COA_BR_CD))) {
                        vndMap.put(DB_COLUMN_COA_BR_CD, (String) vndRecord.get(DB_COLUMN_COA_BR_CD));
                    } else {
                        vndMap.put(DB_COLUMN_COA_BR_CD, (String) defaultRecord.get(DB_COLUMN_COA_BR_CD));
                    }

                    if (ZYPCommonFunc.hasValue(vndRecord.get(DB_COLUMN_COA_ACCT_CD))) {
                        vndMap.put(DB_COLUMN_COA_ACCT_CD, (String) vndRecord.get(DB_COLUMN_COA_ACCT_CD));
                    } else {
                        vndMap.put(DB_COLUMN_COA_ACCT_CD, (String) defaultRecord.get(DB_COLUMN_COA_ACCT_CD));
                    }

                    vndMap.put(DB_COLUMN_COA_CMPY_CD, (String) defaultRecord.get(DB_COLUMN_COA_CMPY_CD));
                    vndMap.put(DB_COLUMN_COA_AFFL_CD, (String) defaultRecord.get(DB_COLUMN_COA_AFFL_CD));
                    vndMap.put(DB_COLUMN_COA_CC_CD, (String) defaultRecord.get(DB_COLUMN_COA_CC_CD));
                    vndMap.put(DB_COLUMN_COA_PROD_CD, (String) defaultRecord.get(DB_COLUMN_COA_PROD_CD));
                    vndMap.put(DB_COLUMN_COA_CH_CD, (String) defaultRecord.get(DB_COLUMN_COA_CH_CD));
                    vndMap.put(DB_COLUMN_COA_PROJ_CD, (String) defaultRecord.get(DB_COLUMN_COA_PROJ_CD));
                    vndMap.put(DB_COLUMN_COA_EXTN_CD, (String) defaultRecord.get(DB_COLUMN_COA_EXTN_CD));

                    vndMap.put(DB_COLUMN_COA_CMPY_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_CMPY_DPLY_FLG));
                    vndMap.put(DB_COLUMN_COA_AFFL_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_AFFL_DPLY_FLG));
                    vndMap.put(DB_COLUMN_COA_BR_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_BR_DPLY_FLG));
                    vndMap.put(DB_COLUMN_COA_CC_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_CC_DPLY_FLG));
                    vndMap.put(DB_COLUMN_COA_ACCT_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_ACCT_DPLY_FLG));
                    vndMap.put(DB_COLUMN_COA_PROD_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_PROD_DPLY_FLG));
                    vndMap.put(DB_COLUMN_COA_CH_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_PROD_DPLY_FLG));
                    vndMap.put(DB_COLUMN_COA_PROJ_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_PROJ_DPLY_FLG));
                    vndMap.put(DB_COLUMN_COA_EXTN_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_EXTN_DPLY_FLG));

                    accountBranchList.add(vndMap);
                    record = accountBranchList.get(0);

                    if (!checkManualSegmentElementForSMsg(sMsg, glblCmpyCd, record, ZYPCommonFunc.concatString(BIZ_APP_ID, "", cMsg.prchReqTpCd_SL.getValue()), rowIndex, winFlg)) {
                        return false;
                    }
                }

            }

        } else if (PRCH_REQ_TP.DISPOSAL.equals(cMsg.prchReqTpCd_SL.getValue())) {

            Map<String, Object> ssmParamBranch = new HashMap<String, Object>();
            ssmParamBranch.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParamBranch.put(DB_PARAM_RTL_WH_CD, sMsg.A.no(rowIndex).srcRtlWhCd_A1.getValue());

            S21SsmEZDResult resultBranch = NPBL0020Query.getInstance().findAccountBranchFromRtlWH(ssmParamBranch);

            Map<String, String> defaultRecord = getDefaultSegment(cMsg, ZYPCommonFunc.concatString(BIZ_APP_ID, "", cMsg.prchReqTpCd_SL.getValue()), glblCmpyCd);

            List<Map<String, String>> accountBranchList = (List<Map<String, String>>) resultBranch.getResultObject();
            Map<String, String> record = new HashMap<String, String>();
            if (accountBranchList.size() != 0) {
                record = accountBranchList.get(0);
            }
            Map<String, String> checkRecord = new HashMap<String, String>();
            checkRecord.put(DB_COLUMN_COA_BR_CD, (String) record.get(DB_COLUMN_COA_BR_CD));

            Map<String, Object> ssmParamAccount = new HashMap<String, Object>();
            ssmParamAccount.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParamAccount.put(DB_PARAM_MDSE_CD, sMsg.A.no(rowIndex).mdseCd_A1.getValue());
            ssmParamAccount.put(DB_PARAM_PRCH_REQ_TP, cMsg.prchReqTpCd_SL.getValue());

            S21SsmEZDResult resultAccountAcct = NPBL0020Query.getInstance().findAccountAcctFromRtlWH(ssmParamAccount);

            List<Map<String, String>> accountAcctList = (List<Map<String, String>>) resultAccountAcct.getResultObject();

            if (accountAcctList.size() != 0) {
                record = accountAcctList.get(0);
                checkRecord.put(DB_COLUMN_COA_ACCT_CD, (String) record.get(DB_COLUMN_COA_ACCT_CD));
            }

            // Account Check(DISPOSAL)
            if (!submitFlg) {
                int idx = cMsg.xxNum.getValueInt();

                accountBranchList = new ArrayList<Map<String, String>>();
                Map<String, String> vndMap = new HashMap<String, String>();

                if (ZYPCommonFunc.hasValue(checkRecord.get(DB_COLUMN_COA_BR_CD))) {
                    vndMap.put(DB_COLUMN_COA_BR_CD, (String) checkRecord.get(DB_COLUMN_COA_BR_CD));
                } else {
                    vndMap.put(DB_COLUMN_COA_BR_CD, (String) defaultRecord.get(DB_COLUMN_COA_BR_CD));
                }

                if (ZYPCommonFunc.hasValue(checkRecord.get(DB_COLUMN_COA_ACCT_CD))) {
                    vndMap.put(DB_COLUMN_COA_ACCT_CD, (String) checkRecord.get(DB_COLUMN_COA_ACCT_CD));
                } else {
                    vndMap.put(DB_COLUMN_COA_ACCT_CD, (String) defaultRecord.get(DB_COLUMN_COA_ACCT_CD));
                }

                vndMap.put(DB_COLUMN_COA_CMPY_CD, (String) defaultRecord.get(DB_COLUMN_COA_CMPY_CD));
                vndMap.put(DB_COLUMN_COA_AFFL_CD, (String) defaultRecord.get(DB_COLUMN_COA_AFFL_CD));
                vndMap.put(DB_COLUMN_COA_CC_CD, (String) defaultRecord.get(DB_COLUMN_COA_CC_CD));
                vndMap.put(DB_COLUMN_COA_PROD_CD, (String) defaultRecord.get(DB_COLUMN_COA_PROD_CD));
                vndMap.put(DB_COLUMN_COA_CH_CD, (String) defaultRecord.get(DB_COLUMN_COA_CH_CD));
                vndMap.put(DB_COLUMN_COA_PROJ_CD, (String) defaultRecord.get(DB_COLUMN_COA_PROJ_CD));
                vndMap.put(DB_COLUMN_COA_EXTN_CD, (String) defaultRecord.get(DB_COLUMN_COA_EXTN_CD));

                vndMap.put(DB_COLUMN_COA_CMPY_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_CMPY_DPLY_FLG));
                vndMap.put(DB_COLUMN_COA_AFFL_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_AFFL_DPLY_FLG));
                vndMap.put(DB_COLUMN_COA_BR_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_BR_DPLY_FLG));
                vndMap.put(DB_COLUMN_COA_CC_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_CC_DPLY_FLG));
                vndMap.put(DB_COLUMN_COA_ACCT_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_ACCT_DPLY_FLG));
                vndMap.put(DB_COLUMN_COA_PROD_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_PROD_DPLY_FLG));
                vndMap.put(DB_COLUMN_COA_CH_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_PROD_DPLY_FLG));
                vndMap.put(DB_COLUMN_COA_PROJ_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_PROJ_DPLY_FLG));
                vndMap.put(DB_COLUMN_COA_EXTN_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_EXTN_DPLY_FLG));

                accountBranchList.add(vndMap);
                record = accountBranchList.get(0);

                if (ZYPCommonFunc.hasValue(cMsg.A.no(idx).xxScrItem50Txt_A1)) {

                    if (!checkManualSegmentElement(cMsg, sMsg, glblCmpyCd, record, ZYPCommonFunc.concatString(BIZ_APP_ID, "", cMsg.prchReqTpCd_SL.getValue()))) {
                        return false;
                    }

                } else {

                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_1).xxPopPrm, (String) record.get(DB_COLUMN_COA_CMPY_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_2).xxPopPrm, (String) record.get(DB_COLUMN_COA_AFFL_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_3).xxPopPrm, (String) record.get(DB_COLUMN_COA_BR_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_4).xxPopPrm, (String) record.get(DB_COLUMN_COA_CC_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_5).xxPopPrm, (String) record.get(DB_COLUMN_COA_ACCT_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_6).xxPopPrm, (String) record.get(DB_COLUMN_COA_PROD_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_7).xxPopPrm, (String) record.get(DB_COLUMN_COA_CH_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_8).xxPopPrm, (String) record.get(DB_COLUMN_COA_PROJ_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_9).xxPopPrm, (String) record.get(DB_COLUMN_COA_EXTN_CD));

                }

            } else {
                // sMsg
                if (ZYPCommonFunc.hasValue(sMsg.A.no(rowIndex).xxScrItem50Txt_A1)) {
                    accountBranchList = new ArrayList<Map<String, String>>();
                    Map<String, String> vndMap = new HashMap<String, String>();

                    if (ZYPCommonFunc.hasValue(checkRecord.get(DB_COLUMN_COA_BR_CD))) {
                        vndMap.put(DB_COLUMN_COA_BR_CD, (String) checkRecord.get(DB_COLUMN_COA_BR_CD));
                    } else {
                        vndMap.put(DB_COLUMN_COA_BR_CD, (String) defaultRecord.get(DB_COLUMN_COA_BR_CD));
                    }
                    if (ZYPCommonFunc.hasValue(checkRecord.get(DB_COLUMN_COA_ACCT_CD))) {
                        vndMap.put(DB_COLUMN_COA_ACCT_CD, (String) checkRecord.get(DB_COLUMN_COA_ACCT_CD));
                    } else {
                        vndMap.put(DB_COLUMN_COA_ACCT_CD, (String) defaultRecord.get(DB_COLUMN_COA_ACCT_CD));
                    }

                    vndMap.put(DB_COLUMN_COA_CMPY_CD, (String) defaultRecord.get(DB_COLUMN_COA_CMPY_CD));
                    vndMap.put(DB_COLUMN_COA_AFFL_CD, (String) defaultRecord.get(DB_COLUMN_COA_AFFL_CD));
                    vndMap.put(DB_COLUMN_COA_CC_CD, (String) defaultRecord.get(DB_COLUMN_COA_CC_CD));
                    vndMap.put(DB_COLUMN_COA_PROD_CD, (String) defaultRecord.get(DB_COLUMN_COA_PROD_CD));
                    vndMap.put(DB_COLUMN_COA_CH_CD, (String) defaultRecord.get(DB_COLUMN_COA_CH_CD));
                    vndMap.put(DB_COLUMN_COA_PROJ_CD, (String) defaultRecord.get(DB_COLUMN_COA_PROJ_CD));
                    vndMap.put(DB_COLUMN_COA_EXTN_CD, (String) defaultRecord.get(DB_COLUMN_COA_EXTN_CD));

                    vndMap.put(DB_COLUMN_COA_CMPY_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_CMPY_DPLY_FLG));
                    vndMap.put(DB_COLUMN_COA_AFFL_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_AFFL_DPLY_FLG));
                    vndMap.put(DB_COLUMN_COA_BR_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_BR_DPLY_FLG));
                    vndMap.put(DB_COLUMN_COA_CC_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_CC_DPLY_FLG));
                    vndMap.put(DB_COLUMN_COA_ACCT_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_ACCT_DPLY_FLG));
                    vndMap.put(DB_COLUMN_COA_PROD_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_PROD_DPLY_FLG));
                    vndMap.put(DB_COLUMN_COA_CH_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_PROD_DPLY_FLG));
                    vndMap.put(DB_COLUMN_COA_PROJ_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_PROJ_DPLY_FLG));
                    vndMap.put(DB_COLUMN_COA_EXTN_DPLY_FLG, defaultRecord.get(DB_COLUMN_COA_EXTN_DPLY_FLG));

                    accountBranchList.add(vndMap);
                    record = accountBranchList.get(0);

                    if (!checkManualSegmentElementForSMsg(sMsg, glblCmpyCd, record, ZYPCommonFunc.concatString(BIZ_APP_ID, "", cMsg.prchReqTpCd_SL.getValue()), rowIndex, winFlg)) {
                        return false;
                    }
                }

            }

        } else if (PRCH_REQ_TP.EXPENSE_ORDER.equals(cMsg.prchReqTpCd_SL.getValue())) {

            // Account Check(EXPENSE_ORDER)
            Map<String, String> defaultRecord = getDefaultSegment(cMsg, ZYPCommonFunc.concatString(BIZ_APP_ID, "", cMsg.prchReqTpCd_SL.getValue()), glblCmpyCd);

            if (!submitFlg) {
                int idx = cMsg.xxNum.getValueInt();
                if (idx < cMsg.A.getValidCount() && ZYPCommonFunc.hasValue(cMsg.A.no(idx).xxScrItem50Txt_A1)) {
                    if (!checkManualSegmentElement(cMsg, sMsg, glblCmpyCd, defaultRecord, ZYPCommonFunc.concatString(BIZ_APP_ID, "", cMsg.prchReqTpCd_SL.getValue()))) {
                        return false;
                    }
                } else {

                    // 12/12/2018 QC#29456 Add Start
                    String errMsgId = null;
                    // 12/12/2018 QC#29456 Add End
                    // 02/08/2019 QC#30181 Add Start
                    if (forceFlg) {
                        fillChrgAccountText(sMsg.A.no(rowIndex));
                    }
                    // 02/08/2019 QC#30181 Add End
                    // QC#22518
                    if (ZYPCommonFunc.hasValue(sMsg.A.no(rowIndex).shipToCustCd_E1)) {
                        // 12/12/2018 QC#29456 Del Start
//                        Map<String, Object> shipToCoaMap = NPBL0020Query.getInstance().getShipToCoaMap(glblCmpyCd, sMsg.A.no(rowIndex).shipToCustCd_E1.getValue());
//
//                        if (shipToCoaMap == null) {
//                            return true;
//                        }
//                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_1).xxPopPrm, (String) shipToCoaMap.get(DB_COLUMN_COA_CMPY_CD));
//                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_2).xxPopPrm, (String) shipToCoaMap.get(DB_COLUMN_COA_AFFL_CD));
//                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_3).xxPopPrm, (String) shipToCoaMap.get(DB_COLUMN_COA_BR_CD));
//                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_4).xxPopPrm, (String) shipToCoaMap.get(DB_COLUMN_COA_CC_CD));
//                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_5).xxPopPrm, (String) shipToCoaMap.get(DB_COLUMN_COA_ACCT_CD));
//                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_6).xxPopPrm, (String) shipToCoaMap.get(DB_COLUMN_COA_PROD_CD));
//                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_7).xxPopPrm, (String) shipToCoaMap.get(DB_COLUMN_COA_CH_CD));
//                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_8).xxPopPrm, (String) shipToCoaMap.get(DB_COLUMN_COA_PROJ_CD));
//                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_9).xxPopPrm, (String) shipToCoaMap.get(DB_COLUMN_COA_EXTN_CD));
                        // 12/12/2018 QC#29456 Del End
                        // 01/28/2019 QC#29778-2 Del Start
                        // 12/12/2018 QC#29456 Add Start
//                        NPXC001001GetDefNineSegDataBean defNineSegDataBean = NPXC001001GetDefNineSegData.getInstance().getDefNineSegData(glblCmpyCd //
//                                , sMsg.A.no(rowIndex).shipToCustCd_E1.getValue() //
//                                , sMsg.A.no(rowIndex).mdseCd_A1.getValue());
//                        if (defNineSegDataBean == null) {
//                            return true;
//                        } else if (defNineSegDataBean.getMsgIdList() != null && !defNineSegDataBean.getMsgIdList().isEmpty()) {
//                            errMsgId = defNineSegDataBean.getMsgId(0);
//                        }
//                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_1).xxPopPrm, defNineSegDataBean.getCoaCmpyCd());
//                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_2).xxPopPrm, defNineSegDataBean.getCoaAfflCd());
//                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_3).xxPopPrm, defNineSegDataBean.getCoaBrCd());
//                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_4).xxPopPrm, defNineSegDataBean.getCoaCcCd());
//                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_5).xxPopPrm, defNineSegDataBean.getCoaAcctCd());
//                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_6).xxPopPrm, defNineSegDataBean.getCoaProdCd());
//                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_7).xxPopPrm, defNineSegDataBean.getCoaChCd());
//                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_8).xxPopPrm, defNineSegDataBean.getCoaProjCd());
//                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_9).xxPopPrm, defNineSegDataBean.getCoaExtnCd());
                        // 12/12/2018 QC#29456 Add End
                        // 01/28/2019 QC#29778-2 Del End
                        // 01/28/2019 QC#29778-2 Add Start -> 02/08/2019 QC#30181 Mod Start wituout any comments.
                        AcctDefMode mode = getAccountDefaultingMode(cMsg, sMsg.A.no(rowIndex));
                        String shipToCustCd = sMsg.A.no(rowIndex).shipToCustCd_E1.getValue();
                        if (AcctDefMode.ITEM.equals(mode)) {
                            shipToCustCd = null;
                        } else if (AcctDefMode.NO_OPE.equals(mode)) {
                            return true;
                        }

                        NPXC001001GetDefNineSegDataBean defNineSegDataBean = NPXC001001GetDefNineSegData.getInstance().getDefNineSegData( //
                                glblCmpyCd //
                                , shipToCustCd //
                                , sMsg.A.no(rowIndex).mdseCd_A1.getValue()
                                , sMsg.A.no(rowIndex).xxScrItem50Txt_A1.getValue());
                        if (defNineSegDataBean == null) {
                            return true;
                        } else if (defNineSegDataBean.getMsgIdList() != null && !defNineSegDataBean.getMsgIdList().isEmpty()) {
                            errMsgId = defNineSegDataBean.getMsgId(0);
                        }
                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_1).xxPopPrm, getValidStr(sMsg.A.no(rowIndex).coaCmpyCd_A1, defNineSegDataBean.getCoaCmpyCd()));
                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_2).xxPopPrm, getValidStr(sMsg.A.no(rowIndex).coaAfflCd_A1, defNineSegDataBean.getCoaAfflCd()));
                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_3).xxPopPrm, getValidStr(sMsg.A.no(rowIndex).coaBrCd_A1, defNineSegDataBean.getCoaBrCd()));
                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_4).xxPopPrm, getValidStr(sMsg.A.no(rowIndex).coaCcCd_A1, defNineSegDataBean.getCoaCcCd()));
                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_5).xxPopPrm, getValidStr(sMsg.A.no(rowIndex).coaAcctCd_A1, defNineSegDataBean.getCoaAcctCd()));
                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_6).xxPopPrm, getValidStr(sMsg.A.no(rowIndex).coaProdCd_A2, defNineSegDataBean.getCoaProdCd()));
                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_7).xxPopPrm, getValidStr(sMsg.A.no(rowIndex).coaChCd_A1, defNineSegDataBean.getCoaChCd()));
                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_8).xxPopPrm, getValidStr(sMsg.A.no(rowIndex).coaProjCd_A1, defNineSegDataBean.getCoaProjCd()));
                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_9).xxPopPrm, getValidStr(sMsg.A.no(rowIndex).coaExtnCd_A1, defNineSegDataBean.getCoaExtnCd()));
                        // 01/28/2019 QC#29778-2 Add End -> 02/08/2019 QC#30181 End Start wituout any comments.
                    } else {
                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_1).xxPopPrm, (String) defaultRecord.get(DB_COLUMN_COA_CMPY_CD));
                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_2).xxPopPrm, (String) defaultRecord.get(DB_COLUMN_COA_AFFL_CD));
                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_3).xxPopPrm, (String) defaultRecord.get(DB_COLUMN_COA_BR_CD));
                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_4).xxPopPrm, (String) defaultRecord.get(DB_COLUMN_COA_CC_CD));
                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_5).xxPopPrm, (String) defaultRecord.get(DB_COLUMN_COA_ACCT_CD));
                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_6).xxPopPrm, (String) defaultRecord.get(DB_COLUMN_COA_PROD_CD));
                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_7).xxPopPrm, (String) defaultRecord.get(DB_COLUMN_COA_CH_CD));
                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_8).xxPopPrm, (String) defaultRecord.get(DB_COLUMN_COA_PROJ_CD));
                        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_9).xxPopPrm, (String) defaultRecord.get(DB_COLUMN_COA_EXTN_CD));

                        // 2019/02/08 QC#30181 Add Start
                        if (ZYPCommonFunc.hasValue(sMsg.A.no(rowIndex).mdseCd_A1)) {
                            NPXC001001GetDefNineSegDataBean defNineSegDataBean = NPXC001001GetDefNineSegData.getInstance().getDefNineSegData( //
                                    glblCmpyCd //
                                    , null //
                                    , sMsg.A.no(rowIndex).mdseCd_A1.getValue()
                                    , sMsg.A.no(rowIndex).xxScrItem50Txt_A1.getValue());
                            ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_1).xxPopPrm, getValidStr(sMsg.A.no(rowIndex).coaCmpyCd_A1, defNineSegDataBean.getCoaCmpyCd()));
                            ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_2).xxPopPrm, getValidStr(sMsg.A.no(rowIndex).coaAfflCd_A1, defNineSegDataBean.getCoaAfflCd()));
                            ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_3).xxPopPrm, getValidStr(sMsg.A.no(rowIndex).coaBrCd_A1, defNineSegDataBean.getCoaBrCd()));
                            ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_4).xxPopPrm, getValidStr(sMsg.A.no(rowIndex).coaCcCd_A1, defNineSegDataBean.getCoaCcCd()));
                            ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_5).xxPopPrm, getValidStr(sMsg.A.no(rowIndex).coaAcctCd_A1, defNineSegDataBean.getCoaAcctCd()));
                            ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_6).xxPopPrm, getValidStr(sMsg.A.no(rowIndex).coaProdCd_A2, defNineSegDataBean.getCoaProdCd()));
                            ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_7).xxPopPrm, getValidStr(sMsg.A.no(rowIndex).coaChCd_A1, defNineSegDataBean.getCoaChCd()));
                            ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_8).xxPopPrm, getValidStr(sMsg.A.no(rowIndex).coaProjCd_A1, defNineSegDataBean.getCoaProjCd()));
                            ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_9).xxPopPrm, getValidStr(sMsg.A.no(rowIndex).coaExtnCd_A1, defNineSegDataBean.getCoaExtnCd()));
                        }
                        // 2019/02/08 QC#30181 Add End
                    }

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaCmpyCd_A1, cMsg.P.no(IDX_1).xxPopPrm);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaAfflCd_A1, cMsg.P.no(IDX_2).xxPopPrm);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaBrCd_A1, cMsg.P.no(IDX_3).xxPopPrm);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaCcCd_A1, cMsg.P.no(IDX_4).xxPopPrm);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaAcctCd_A1, cMsg.P.no(IDX_5).xxPopPrm);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaProdCd_A2, cMsg.P.no(IDX_6).xxPopPrm);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaChCd_A1, cMsg.P.no(IDX_7).xxPopPrm);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaProjCd_A1, cMsg.P.no(IDX_8).xxPopPrm);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaExtnCd_A1, cMsg.P.no(IDX_9).xxPopPrm);

                    StringBuilder chargeAccount = new StringBuilder();
                    chargeAccount.append(cMsg.P.no(1).xxPopPrm.getValue());
                    chargeAccount.append(".");
                    chargeAccount.append(cMsg.P.no(3).xxPopPrm.getValue());
                    chargeAccount.append(".");
                    chargeAccount.append(cMsg.P.no(4).xxPopPrm.getValue());
                    chargeAccount.append(".");
                    chargeAccount.append(cMsg.P.no(5).xxPopPrm.getValue());
                    chargeAccount.append(".");
                    chargeAccount.append(cMsg.P.no(6).xxPopPrm.getValue());
                    chargeAccount.append(".");
                    chargeAccount.append(cMsg.P.no(7).xxPopPrm.getValue());
                    chargeAccount.append(".");
                    chargeAccount.append(cMsg.P.no(2).xxPopPrm.getValue());
                    chargeAccount.append(".");
                    chargeAccount.append(cMsg.P.no(8).xxPopPrm.getValue());
                    chargeAccount.append(".");
                    chargeAccount.append(cMsg.P.no(9).xxPopPrm.getValue());

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).xxScrItem50Txt_A1, chargeAccount.toString());
                    // 12/12/2018 QC#29456 Add Start
                    if (ZYPCommonFunc.hasValue(errMsgId)) {
                        sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, errMsgId);
                    }
                    // 12/12/2018 QC#29456 Add End

                }

            } else {
                // sMsg
                if (ZYPCommonFunc.hasValue(sMsg.A.no(rowIndex).xxScrItem50Txt_A1)) {
                    if (!checkManualSegmentElementForSMsg(sMsg, glblCmpyCd, defaultRecord, ZYPCommonFunc.concatString(BIZ_APP_ID, "", cMsg.prchReqTpCd_SL.getValue()), rowIndex, winFlg)) {
                        return false;
                    }
                }
            }

        } else if (PRCH_REQ_TP.REFURBISHING.equals(cMsg.prchReqTpCd_SL.getValue())) {

            Map<String, String> defaultRecord = getDefaultSegment(cMsg, ZYPCommonFunc.concatString(BIZ_APP_ID, "", cMsg.prchReqTpCd_SL.getValue()), glblCmpyCd);

            // Account Check(WH_TRANSFER, SUBCONTRACT)
            if (!submitFlg) {
                int idx = cMsg.xxNum.getValueInt();
                if (ZYPCommonFunc.hasValue(cMsg.A.no(idx).xxScrItem50Txt_A1)) {
                    if (!checkManualSegmentElement(cMsg, sMsg, glblCmpyCd, defaultRecord, ZYPCommonFunc.concatString(BIZ_APP_ID, "", cMsg.prchReqTpCd_SL.getValue()))) {
                        return false;
                    }
                } else {

                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_1).xxPopPrm, (String) defaultRecord.get(DB_COLUMN_COA_CMPY_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_2).xxPopPrm, (String) defaultRecord.get(DB_COLUMN_COA_AFFL_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_3).xxPopPrm, (String) defaultRecord.get(DB_COLUMN_COA_BR_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_4).xxPopPrm, (String) defaultRecord.get(DB_COLUMN_COA_CC_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_5).xxPopPrm, (String) defaultRecord.get(DB_COLUMN_COA_ACCT_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_6).xxPopPrm, (String) defaultRecord.get(DB_COLUMN_COA_PROD_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_7).xxPopPrm, (String) defaultRecord.get(DB_COLUMN_COA_CH_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_8).xxPopPrm, (String) defaultRecord.get(DB_COLUMN_COA_PROJ_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_9).xxPopPrm, (String) defaultRecord.get(DB_COLUMN_COA_EXTN_CD));

                }

            } else {
                // sMsg
                if (ZYPCommonFunc.hasValue(sMsg.A.no(rowIndex).xxScrItem50Txt_A1)) {
                    if (!checkManualSegmentElementForSMsg(sMsg, glblCmpyCd, defaultRecord, ZYPCommonFunc.concatString(BIZ_APP_ID, "", cMsg.prchReqTpCd_SL.getValue()), rowIndex, winFlg)) {
                        return false;
                    }
                }

            }
        }

        return true;
    }

    /**
     * Get PO
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     */
    public static void getPO(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PO_ORD_NUM, cMsg.poOrdNum);

        // Execute
        S21SsmEZDResult result = NPBL0020Query.getInstance().findPO(ssmParam);

        if (result.isCodeNormal()) {
            // QC#55313
            List<Map<String, Object>> poList = (List<Map<String, Object>>) result.getResultObject();
            // QC#22481
            for (int i = 0; i < poList.size(); i++) {
                Map<String, Object> record = poList.get(i);

                if (i == 0) {
                    ZYPEZDItemValueSetter.setValue(sMsg.prntVndCd, (String) record.get(DB_COLUMN_PRNT_VND_CD));
                    ZYPEZDItemValueSetter.setValue(sMsg.vndCd, (String) record.get(DB_COLUMN_VND_CD));
                    ZYPEZDItemValueSetter.setValue(sMsg.dplyVndNm, (String) record.get(DB_COLUMN_DPLY_VND_NM));
                    ZYPEZDItemValueSetter.setValue(cMsg.prntVndCd, (String) record.get(DB_COLUMN_PRNT_VND_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.vndCd, (String) record.get(DB_COLUMN_VND_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.dplyVndNm, (String) record.get(DB_COLUMN_DPLY_VND_NM));
                }

                addNewLine(cMsg, sMsg, glblCmpyCd);
                int count = sMsg.A.getValidCount();
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).mdseCd_A1, (String) record.get(DB_COLUMN_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).mdseDescShortTxt_A1, (String) record.get(DB_COLUMN_MDSE_DESC_SHORT_TXT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).trxRefLineNum_A1, (String) record.get(DB_COLUMN_PO_ORD_DTL_LINE_NUM));
                // QC#55313
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).prchReqDispQty_A1, (BigDecimal) record.get(DB_COLUMN_PO_DISP_QTY));

                NPBL0020CommonLogic.copyFromSMsgDetailOntoCmsgDetail(cMsg, sMsg);
            }
        }


    }

    /**
     * Check
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean check(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {

        // Check Requester
        if (ZYPCommonFunc.hasValue(sMsg.fullPsnNm)) {
            if (!checkRequester(sMsg, glblCmpyCd)) {
                cMsg.fullPsnNm.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_FULL_PSN_NM });
                return false;
            }
        }
        // Check Carrier
        if (ZYPCommonFunc.hasValue(sMsg.carrNm)) {
            if (!checkCarrier(sMsg, glblCmpyCd)) {
                cMsg.carrNm.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_CARR_NM });
                return false;
            }
        // START 2019/01/15 T.Ogura [QC#29774,ADD]
        } else {
            sMsg.carrCd.clear();
        // END   2019/01/15 T.Ogura [QC#29774,ADD]
        }
        // Check Header Ship To Supplier
        // QC#12513 Disposal not Mandatory
        if (PRCH_REQ_TP.DISPOSAL.equals(sMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.VENDOR_RETURN.equals(sMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.REFURBISHING.equals(sMsg.prchReqTpCd_SL.getValue())
                || PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue())) {

            if (ZYPCommonFunc.hasValue(sMsg.vndCd)) {
                if (!checkShipToSupplier(sMsg, glblCmpyCd)) {
                    cMsg.vndCd.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_VND_CD });
                    return false;
                }
            } else if (ZYPCommonFunc.hasValue(sMsg.dplyVndNm)) {
                int count = checkShipToSupplierFromName(sMsg, glblCmpyCd);
                if (count == 0) {
                    cMsg.dplyVndNm.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_DPLY_VND_NM });
                    return false;
                } else if (count > 1) {
                    cMsg.dplyVndNm.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_DPLY_VND_NM });
                    return false;
                }
            }
            if (!PRCH_REQ_TP.DISPOSAL.equals(sMsg.prchReqTpCd_SL.getValue()) && !ZYPCommonFunc.hasValue(sMsg.vndCd)) {
                cMsg.vndCd.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_VND_CD });
                return false;
            }
        }
        // Check Header Destination Warehouse
        if (PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue())) {
            if (!ZYPCommonFunc.hasValue(sMsg.destRtlWhCd) && ZYPCommonFunc.hasValue(sMsg.rtlWhNm_DW)) {
                int count = checkDestRtlWhFromName(glblCmpyCd, sMsg.rtlWhNm_DW, sMsg.destRtlWhCd, sMsg.rtlWhCatgCd_DW);
                if (count == 0) {
                    cMsg.rtlWhNm_DW.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_DW });
                    return false;
                } else if (count > 1) {
                    cMsg.rtlWhNm_DW.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_RTL_WH_NM_DW });
                    return false;
                }
            }
            if (!ZYPCommonFunc.hasValue(sMsg.destRtlWhCd)) {
                cMsg.destRtlWhCd.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_DEST_RTL_WH_CD });
                return false;
            }
        }
        // Check Header Source Warehouse
        if (PRCH_REQ_TP.VENDOR_RETURN.equals(sMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.REFURBISHING.equals(sMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue())) {
            if (!ZYPCommonFunc.hasValue(sMsg.srcRtlWhCd) && ZYPCommonFunc.hasValue(sMsg.rtlWhNm_SW)) {
                int count = checkSrcRtlWhFromName(glblCmpyCd, sMsg.rtlWhNm_SW, sMsg.srcRtlWhCd, sMsg.rtlWhCatgCd_SW);
                if (count == 0) {
                    cMsg.rtlWhNm_SW.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_SW });
                    return false;
                } else if (count > 1) {
                    cMsg.rtlWhNm_SW.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_RTL_WH_NM_SW });
                    return false;
                }
            }
            if (!ZYPCommonFunc.hasValue(sMsg.srcRtlWhCd)) {
                cMsg.srcRtlWhCd.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_SRC_RTL_WH_CD });
                return false;
            }
        }
        // Check relationship Carrier, Shipping Service Level
        if (ZYPCommonFunc.hasValue(sMsg.carrCd) && ZYPCommonFunc.hasValue(sMsg.shpgSvcLvlCd_SL)) {
            if (!checkRelationshipCarrierAndShippingServiceLevel(sMsg, glblCmpyCd)) {
                cMsg.carrNm.setErrorInfo(1, NPAM1365E);
                cMsg.shpgSvcLvlCd_SL.setErrorInfo(1, NPAM1365E);
                return false;
            }
        }

        // QC#18366 Add
        if ((PRCH_REQ_TP.WH_TRANSFER.equals(sMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.EXPENSE_ORDER.equals(sMsg.prchReqTpCd_SL.getValue())) && ZYPCommonFunc.hasValue(cMsg.shpgSvcLvlCd_SL)) {

            SHPG_SVC_FRT_CHRG_RELNTMsg reqTMsg = new SHPG_SVC_FRT_CHRG_RELNTMsg();
            reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
            reqTMsg.shpgSvcLvlCd.setValue(cMsg.shpgSvcLvlCd_SL.getValue());
            if (PRCH_REQ_TP.EXPENSE_ORDER.equals(cMsg.prchReqTpCd_SL.getValue())) {
                reqTMsg.frtChrgToCd.setValue(FRT_CHRG_TO.CUSTOMER);
                reqTMsg.frtChrgMethCd.setValue(FRT_CHRG_METH.INVOICE_IN);
            } else {
                reqTMsg.frtChrgToCd.setValue(FRT_CHRG_TO.CANON);
                reqTMsg.frtChrgMethCd.setValue(FRT_CHRG_METH.N_OR_A);
            }

            reqTMsg = (SHPG_SVC_FRT_CHRG_RELNTMsg) EZDTBLAccessor.findByKey(reqTMsg);

            if (reqTMsg == null) {
                cMsg.shpgSvcLvlCd_SL.setErrorInfo(1, NPBM0014E);
                return false;
            }
        }

        // START 2022/12/27 T.Kuroda[QC#60562, MOD]
        // setWarehouseToConfigItemLine(sMsg, glblCmpyCd);
        if (!PRCH_REQ_TP.WH_TRANSFER.equals(sMsg.prchReqTpCd_SL.getValue()) && !PRCH_REQ_TP.EXPENSE_ORDER.equals(sMsg.prchReqTpCd_SL.getValue())) {
            setWarehouseToConfigItemLine(sMsg, glblCmpyCd);
        }
        // END 2022/12/27 T.Kuroda[QC#60562, MOD]
        setConfigItemLine(sMsg, glblCmpyCd);

        // QC#22346 Start
        if (PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue())) {
            // QC#22346 Start
            PRCH_REQ_TPTMsg tMsg = new PRCH_REQ_TPTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.prchReqTpCd, sMsg.prchReqTpCd_SL.getValue());
            tMsg = (PRCH_REQ_TPTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.defPrchReqTpDaysNum)) {
                // START 2019/03/20 T.Ogura [QC#30769,MOD]
//                ZYPEZDItemValueSetter.setValue(sMsg.rqstRcvDt, ZYPDateUtil.addBusinessDay(glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), tMsg.defPrchReqTpDaysNum.getValueInt()));
                ZYPEZDItemValueSetter.setValue(sMsg.rqstRcvDt, ZYPDateUtil.addDays(ZYPDateUtil.getSalesDate(glblCmpyCd), tMsg.defPrchReqTpDaysNum.getValueInt()));
                // END   2019/03/20 T.Ogura [QC#30769,MOD]
            } else {
                BigDecimal defDaysAot = ZYPCodeDataUtil.getNumConstValue(DEF_PRCH_REQ_TP_AOT_DAYS, glblCmpyCd);
                if (ZYPCommonFunc.hasValue(defDaysAot)) {
                    // START 2019/03/20 T.Ogura [QC#30769,MOD]
//                    ZYPEZDItemValueSetter.setValue(sMsg.rqstRcvDt, ZYPDateUtil.addBusinessDay(glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), defDaysAot.intValue()));
                    ZYPEZDItemValueSetter.setValue(sMsg.rqstRcvDt, ZYPDateUtil.addDays(ZYPDateUtil.getSalesDate(glblCmpyCd), defDaysAot.intValue()));
                    // END   2019/03/20 T.Ogura [QC#30769,MOD]
                } else {
                    // START 2019/03/20 T.Ogura [QC#30769,MOD]
//                    ZYPEZDItemValueSetter.setValue(sMsg.rqstRcvDt, ZYPDateUtil.addBusinessDay(glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), 5));
                    ZYPEZDItemValueSetter.setValue(sMsg.rqstRcvDt, ZYPDateUtil.addDays(ZYPDateUtil.getSalesDate(glblCmpyCd), 5));
                    // END   2019/03/20 T.Ogura [QC#30769,MOD]
                }
            }
        }
        // QC#22346 End

        // START 2021/11/09 R.Azucena[QC#58586, ADD]
        List<String> rqstTypesSingleItemCheck = new ArrayList<String>();
        String valRqstTypesSingleItemCheck = ZYPCodeDataUtil.getVarCharConstValue(RQST_TYPES_SINGLE_ITEM_CHECK, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(valRqstTypesSingleItemCheck)) {
            rqstTypesSingleItemCheck = Arrays.asList(valRqstTypesSingleItemCheck.split(","));
        }
        // END 2021/11/09 R.Azucena[QC#58586, ADD]

        Map<String, String> showroomSwhMap = new HashMap<String, String>();
        int configLineIndex = -1;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            // QC#29154 Add status check.
            if (PRCH_REQ_LINE_STS.CANCELLED.equals(sMsg.A.no(i).prchReqLineStsCd_A1.getValue()) //
                    || PRCH_REQ_LINE_STS.CLOSED.equals(sMsg.A.no(i).prchReqLineStsCd_A1.getValue())) {
                // Skip cancel or skip record.
                continue;
            }

            // START 2022/11/16 R.Azucena[QC#60808, ADD]
            if (PRCH_REQ_TP.DISPOSAL.equals(sMsg.prchReqTpCd_SL.getValue())
                    && ZYPCommonFunc.hasValue(sMsg.A.no(i).svcConfigMstrPk_A1)
                    && ZYPCommonFunc.hasValue(sMsg.A.no(i).svcMachMstrPk_A1)
                    && isActiveServiceExchangeItem(sMsg.A.no(i).svcMachMstrPk_A1.getValue(), glblCmpyCd)) {
                sMsg.A.no(i).mdseCd_A1.setErrorInfo(1, NPBM0023E);
                setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                return false;
            }
            // END 2022/11/16 R.Azucena[QC#60808, ADD]

            // QC#22481
            // Check PO_STS_CD
            if (PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue())) {
                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
                ssmParam.put(DB_PARAM_PO_ORD_NUM, sMsg.poOrdNum.getValue());
                ssmParam.put(DB_PARAM_PO_ORD_DTL_LINE_NUM, sMsg.A.no(i).trxRefLineNum_A1.getValue());
                ssmParam.put(DB_PARAM_ROWNUM, IDX_1);

                S21SsmEZDResult result = NPBL0020Query.getInstance().getPoOrderStatus(ssmParam);

                if (!result.isCodeNormal()) {
                    cMsg.setMessageInfo(NPAM0032E);
                    return false;
                }
                List<Map<String, String>> poOrderStatusList = (List<Map<String, String>>) result.getResultObject();
                if (poOrderStatusList.size() == 0 || !PO_STS.SAVED.equals((String) poOrderStatusList.get(0).get(DB_COLUMN_PO_STS_CD))) {
                    cMsg.setMessageInfo(NPAM0032E);
                    return false;
                }
            }

            // Check Line Type
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).prchReqLineTpCd_A1)) {
                sMsg.A.no(i).prchReqLineTpCd_A1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_PRCH_REQ_LINE_TP_DESC_TXT });
                setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                return false;
            }

            // Check Item Line
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).configTpCd_A1) || (ZYPCommonFunc.hasValue(sMsg.A.no(i).configTpCd_A1) && sMsg.A.no(i).prchReqLineSubNum_A1.getValueInt() != 0)) {
                // Check Qty
                if (sMsg.A.no(i).prchReqDispQty_A1.getValueInt() <= 0) {
                    sMsg.A.no(i).prchReqDispQty_A1.setErrorInfo(1, NPAM0046E, new String[] {DISPLAY_NM_PRCH_REQ_DISP_QTY });
                    setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                    return false;
                }
                // Check Item
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseCd_A1)) {
                    sMsg.A.no(i).mdseCd_A1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_MDSE_CD });
                    setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                    return false;
                }

                // QC#17098 Mod.
                if (!checkItemNumber(sMsg.A.no(i), glblCmpyCd)) {
                    setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                    return false;
                }

                // check stock status
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).fromStkStsCd_A1)) {
                    sMsg.A.no(i).fromStkStsCd_A1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_STOCK_STATUS_CD, "" });
                    setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                    return false;
                }

                // START 06/12/2020 T.Ogura [QC#57002,ADD]
                // Check Transfer Qty for Min Order Qty and Max Order Qty and Incr Order Qty
                if (PRCH_REQ_TP.WH_TRANSFER.equals(sMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.EXPENSE_ORDER.equals(sMsg.prchReqTpCd_SL.getValue())) {
                    if (!checkTransferQtyForMinAndMaxAndIncl(sMsg.A.no(i), glblCmpyCd)) {
                        setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                        return false;
                    }
                }
                // END   06/12/2020 T.Ogura [QC#57002,ADD]
            }

            // START 2018/04/03 S.Katsuma [QC#3521,25063,ADD]
            // In consideration of changing WH Name by manual, update WH Code.
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlWhNm_A1)) {
                setRtlWhFromName(glblCmpyCd, sMsg.A.no(i).rtlWhNm_A1, sMsg.A.no(i).srcRtlWhCd_A1, sMsg.A.no(i).rtlWhCatgCd_A1);
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlWhNm_A2)) {
                setRtlWhFromName(glblCmpyCd, sMsg.A.no(i).rtlWhNm_A2, sMsg.A.no(i).destRtlWhCd_A1, sMsg.A.no(i).rtlWhCatgCd_A2);
            }
            // END 2018/04/03 S.Katsuma [QC#3521,25063,ADD]

            // START 2018/06/11 S.Katsuma [QC#26193,ADD]
            // Check whether serial is entered or not.
            List<String> serNeededReqTp = getSerNeededReqTp(glblCmpyCd);
            if (serNeededReqTp != null && !serNeededReqTp.isEmpty()) {
                if (serNeededReqTp.contains(sMsg.prchReqTpCd_SL.getValue())) {
                    if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).shpgSerTakeFlg_A1.getValue())) {
                        if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).xxLogDtlTxt_A1)) {
                            sMsg.A.no(i).xxLogDtlTxt_A1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_SER_NUM});
                            return false;
                        }
                    }
                }
            }

            // Check serial Qty/Transfer Qty
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).xxLogDtlTxt_A1)) {
                if (sMsg.A.no(i).xxLogDtlTxt_A1.getValue().contains(SCREEN_CTRL_VALUE_SERIAL_NUM_DELIM)) {
                    String[] serialNumber = sMsg.A.no(i).xxLogDtlTxt_A1.getValue().split(SCREEN_CTRL_VALUE_SERIAL_NUM_DELIM);

                    if (serialNumber != null && serialNumber.length > 0) {
                        if (!(sMsg.A.no(i).prchReqDispQty_A1.getValue().compareTo(new BigDecimal(serialNumber.length)) == 0)) {
                            sMsg.A.no(i).xxLogDtlTxt_A1.setErrorInfo(1, NPBM0017E);
                            return false;
                        }
                    }
                } else {
                    if (!(sMsg.A.no(i).prchReqDispQty_A1.getValue().compareTo(new BigDecimal(1)) == 0)) {
                        sMsg.A.no(i).xxLogDtlTxt_A1.setErrorInfo(1, NPBM0017E);
                        return false;
                    }
                }
            }
            // END 2018/06/11 S.Katsuma [QC#26193,ADD]

            // Warehouse Header -> Detail
            if (PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).destRtlWhCd_A1, sMsg.destRtlWhCd);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhNm_A2, sMsg.rtlWhNm_DW);
                // QC#22608 Start
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).destRtlSwhCd_A1, sMsg.A.no(i).srcRtlSwhCd_A1);
                // QC#22608 End
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlSwhNm_A2, sMsg.rtlSwhNm_DS);
            }
            if (PRCH_REQ_TP.VENDOR_RETURN.equals(sMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.REFURBISHING.equals(sMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).srcRtlWhCd_A1, sMsg.srcRtlWhCd);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhNm_A1, sMsg.rtlWhNm_SW);
            }
            // QC#22511 Delete SrcSWH Copy header to detail.
            // Check SrcSWH.
            if (PRCH_REQ_TP.VENDOR_RETURN.equals(sMsg.prchReqTpCd_SL.getValue())) {
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).srcRtlSwhCd_A1)) {
                    sMsg.A.no(i).srcRtlSwhCd_A1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_SRC_RTL_SWH_CD});
                    return false;
                }
            }

            // Check Warehouse
            if (PRCH_REQ_TP.WH_TRANSFER.equals(sMsg.prchReqTpCd_SL.getValue())) {
                // check Need by date
                String calTpCd = getCalTpCd(glblCmpyCd, sMsg.A.no(i).destRtlWhCd_A1.getValue());
                if (!ZYPCommonFunc.hasValue(calTpCd) || calTpCd.equals(NWZM0949E) || calTpCd.equals(NWZM0673E)) {
                    if (!ZYPCommonFunc.hasValue(calTpCd)) {
                        cMsg.setMessageInfo(NWZM0949E, new String[] {DISPLAY_NM_DEST_RTL_WH_CD });
                        return false;
                    } else {
                        cMsg.setMessageInfo(calTpCd, new String[] {DISPLAY_NM_DEST_RTL_WH_CD });
                        return false;
                    }

                }
                // START 2019/03/20 T.Ogura [QC#30769,DEL]
//                if (!ZYPDateUtil.isBusinessDayEx(glblCmpyCd, calTpCd, sMsg.rqstRcvDt.getValue())) {
//                    cMsg.setMessageInfo(NPAM0094E, null);
//                    return false;
//                }
                // END   2019/03/20 T.Ogura [QC#30769,DEL]

                // V0.5
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).configTpCd_A1)) {
                    if (sMsg.A.no(i).prchReqLineSubNum_A1.getValueInt() == 0) {
                        configLineIndex = i;
                        // ConfigLine
                        // SRC_WH Check
                        if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlWhNm_A1)) {
                            sMsg.A.no(i).rtlWhNm_A1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                            setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                            return false;
                        } else {
                            int count = checkSrcRtlWhFromName(glblCmpyCd, sMsg.A.no(i).rtlWhNm_A1, sMsg.A.no(i).srcRtlWhCd_A1, sMsg.A.no(i).rtlWhCatgCd_A1);
                            if (count == 0) {
                                sMsg.A.no(i).rtlWhNm_A1.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                                setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                                return false;
                            }
                            if (count > 1) {
                                sMsg.A.no(i).rtlWhNm_A1.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                                setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                                return false;
                            }
                        }

                        // DEST_WH Check
                        if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlWhNm_A2)) {
                            sMsg.A.no(i).rtlWhNm_A2.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW });
                            setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                            return false;
                        } else {
                            int count = checkDestRtlWhFromName(glblCmpyCd, sMsg.A.no(i).rtlWhNm_A2, sMsg.A.no(i).destRtlWhCd_A1, sMsg.A.no(i).rtlWhCatgCd_A2);
                            if (count == 0) {
                                sMsg.A.no(i).rtlWhNm_A2.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW });
                                setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                                return false;
                            }
                            if (count > 1) {
                                sMsg.A.no(i).rtlWhNm_A2.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW });
                                setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                                return false;
                            }
                        }

                        // QC#18718
                        if (sMsg.A.no(i).srcRtlWhCd_A1.getValue().equals(sMsg.A.no(i).destRtlWhCd_A1.getValue())) {
                            sMsg.A.no(i).rtlWhNm_A1.setErrorInfo(1, NPBM0015E, null);
                            sMsg.A.no(i).rtlWhNm_A2.setErrorInfo(1, NPBM0015E, null);
                            setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                            return false;
                        }

                    } else {
                        // ConfigItemLine
                        if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).srcRtlSwhCd_A1)) {
                            sMsg.A.no(i).srcRtlSwhCd_A1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_SRC_RTL_SWH_CD_LINE });
                            setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                            return false;
                        }

                        // Set ConfigLine
                        // START 2022/12/27 T.Kuroda[QC#60562, MOD]
                        // ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).srcRtlWhCd_A1, sMsg.A.no(configLineIndex).srcRtlWhCd_A1);
                        // ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhNm_A1, sMsg.A.no(configLineIndex).rtlWhNm_A1);
                        // ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlSwhNm_A1, sMsg.A.no(configLineIndex).rtlSwhNm_A1);
                        // ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).destRtlWhCd_A1, sMsg.A.no(configLineIndex).destRtlWhCd_A1);
                        // ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhNm_A2, sMsg.A.no(configLineIndex).rtlWhNm_A2);
                        // ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlSwhNm_A2, sMsg.A.no(configLineIndex).rtlSwhNm_A2);
                        // ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhCatgCd_A1, sMsg.A.no(configLineIndex).rtlWhCatgCd_A1);
                        // ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhCatgCd_A2, sMsg.A.no(configLineIndex).rtlWhCatgCd_A2);
                        if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).srcRtlWhCd_A1)) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).srcRtlWhCd_A1, sMsg.A.no(configLineIndex).srcRtlWhCd_A1);
                        }
                        if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlWhNm_A1)) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhNm_A1, sMsg.A.no(configLineIndex).rtlWhNm_A1);
                        }
                        if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlSwhNm_A1)) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlSwhNm_A1, sMsg.A.no(configLineIndex).rtlSwhNm_A1);
                        }
                        if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).destRtlWhCd_A1)) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).destRtlWhCd_A1, sMsg.A.no(configLineIndex).destRtlWhCd_A1);
                        }
                        if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlWhNm_A2)) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhNm_A2, sMsg.A.no(configLineIndex).rtlWhNm_A2);
                        }
                        if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlSwhNm_A2)) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlSwhNm_A2, sMsg.A.no(configLineIndex).rtlSwhNm_A2);
                        }
                        if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlWhCatgCd_A1)) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhCatgCd_A1, sMsg.A.no(configLineIndex).rtlWhCatgCd_A1);
                        }
                        if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlWhCatgCd_A2)) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhCatgCd_A2, sMsg.A.no(configLineIndex).rtlWhCatgCd_A2);
                        }
                        // END 2022/12/27 T.Kuroda[QC#60562, MOD]

                        // V0.5
                        if (RTL_WH_CATG.SHOWROOM.equals(sMsg.A.no(i).rtlWhCatgCd_A1.getValue()) && RTL_WH_CATG.SHOWROOM.equals(sMsg.A.no(i).rtlWhCatgCd_A2.getValue())) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).destRtlSwhCd_A1, sMsg.A.no(i).srcRtlSwhCd_A1);
                        // QC#24994 MOD START
                        //} else if (!RTL_WH_CATG.SHOWROOM.equals(sMsg.A.no(i).rtlWhCatgCd_A1.getValue()) && !RTL_WH_CATG.SHOWROOM.equals(sMsg.A.no(i).rtlWhCatgCd_A2.getValue())) {
                        //    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).destRtlSwhCd_A1, sMsg.A.no(i).srcRtlSwhCd_A1);
                        } else if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).destRtlSwhCd_A1)) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).destRtlSwhCd_A1, sMsg.A.no(i).srcRtlSwhCd_A1);
                        // QC#24994 MOD END
                        }
                    }

                } else {
                    // Item line
                    if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlWhNm_A1)) {
                        sMsg.A.no(i).rtlWhNm_A1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                        setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                        return false;
                    } else {
                        int count = checkSrcRtlWhFromName(glblCmpyCd, sMsg.A.no(i).rtlWhNm_A1, sMsg.A.no(i).srcRtlWhCd_A1, sMsg.A.no(i).rtlWhCatgCd_A1);
                        if (count == 0) {
                            sMsg.A.no(i).rtlWhNm_A1.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                            setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                            return false;
                        }
                        if (count > 1) {
                            sMsg.A.no(i).rtlWhNm_A1.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                            setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                            return false;
                        }
                    }

                    if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlWhNm_A2)) {
                        sMsg.A.no(i).rtlWhNm_A2.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW });
                        setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                        return false;
                    } else {
                        int count = checkDestRtlWhFromName(glblCmpyCd, sMsg.A.no(i).rtlWhNm_A2, sMsg.A.no(i).destRtlWhCd_A1, sMsg.A.no(i).rtlWhCatgCd_A2);
                        if (count == 0) {
                            sMsg.A.no(i).rtlWhNm_A2.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW });
                            setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                            return false;
                        }
                        if (count > 1) {
                            sMsg.A.no(i).rtlWhNm_A2.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW });
                            setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                            return false;
                        }
                    }

                    // QC#18718
                    if (sMsg.A.no(i).srcRtlWhCd_A1.getValue().equals(sMsg.A.no(i).destRtlWhCd_A1.getValue())) {
                        sMsg.A.no(i).rtlWhNm_A1.setErrorInfo(1, NPBM0015E, null);
                        sMsg.A.no(i).rtlWhNm_A2.setErrorInfo(1, NPBM0015E, null);
                        setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                        return false;
                    }

                    // V0.5
                    if (RTL_WH_CATG.SHOWROOM.equals(sMsg.A.no(i).rtlWhCatgCd_A1.getValue()) && RTL_WH_CATG.SHOWROOM.equals(sMsg.A.no(i).rtlWhCatgCd_A2.getValue())) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).destRtlSwhCd_A1, sMsg.A.no(i).srcRtlSwhCd_A1);
                    // QC#24994 DEL START
                    //} else if (!RTL_WH_CATG.SHOWROOM.equals(sMsg.A.no(i).rtlWhCatgCd_A1.getValue()) && !RTL_WH_CATG.SHOWROOM.equals(sMsg.A.no(i).rtlWhCatgCd_A2.getValue())) {
                    //    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).destRtlSwhCd_A1, sMsg.A.no(i).srcRtlSwhCd_A1);
                    // QC#24994 DEL END
                    }
                }
            }

            if (PRCH_REQ_TP.DISPOSAL.equals(sMsg.prchReqTpCd_SL.getValue())) {
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlWhNm_A1)) {
                    sMsg.A.no(i).rtlWhNm_A1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                    setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                    return false;
                } else {
                    int count = checkSrcRtlWhFromName(glblCmpyCd, sMsg.A.no(i).rtlWhNm_A1, sMsg.A.no(i).srcRtlWhCd_A1, sMsg.A.no(i).rtlWhCatgCd_A1);
                    if (count == 0) {
                        sMsg.A.no(i).rtlWhNm_A1.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                        setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                        return false;
                    }
                    if (count > 1) {
                        sMsg.A.no(i).rtlWhNm_A1.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                        setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                        return false;
                    }
                }

                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).configTpCd_A1) || (ZYPCommonFunc.hasValue(sMsg.A.no(i).configTpCd_A1) && sMsg.A.no(i).prchReqLineSubNum_A1.getValueInt() > 0)) {
                    if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).srcRtlSwhCd_A1)) {
                        sMsg.A.no(i).rtlWhNm_A1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_SRC_RTL_SWH_CD_LINE });
                        setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                        return false;
                    }
                }
            }

            if (PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.REFURBISHING.equals(sMsg.prchReqTpCd_SL.getValue())) {
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).srcRtlSwhCd_A1)) {
                    sMsg.A.no(i).srcRtlSwhCd_A1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_SRC_RTL_SWH_CD_LINE });
                    setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                    return false;
                }
            }

            // Check Expense Order
            if (PRCH_REQ_TP.EXPENSE_ORDER.equals(sMsg.prchReqTpCd_SL.getValue())) {
                // START 2018/04/03 S.Katsuma [QC#23521,25063,ADD]
                // In consideration of changing Ship To Cust Name by manual, update Ship To Cust Code.
                if (getShipToCustCdForDetail(glblCmpyCd, cMsg, sMsg, i)) {
                    return false;
                }
                // END 2018/04/03 S.Katsuma [QC#23521,25063,ADD]

                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).configTpCd_A1)) {
                    if (sMsg.A.no(i).prchReqLineSubNum_A1.getValueInt() == 0) {
                        // ConfigLine
                        configLineIndex = i;
                        // SRC_WH Check
                        if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlWhNm_A1)) {
                            sMsg.A.no(i).rtlWhNm_A1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                            setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                            return false;
                        } else {
                            int count = checkSrcRtlWhFromName(glblCmpyCd, sMsg.A.no(i).rtlWhNm_A1, sMsg.A.no(i).srcRtlWhCd_A1, sMsg.A.no(i).rtlWhCatgCd_A1);
                            if (count == 0) {
                                sMsg.A.no(i).rtlWhNm_A1.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                                setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                                return false;
                            }
                            if (count > 1) {
                                sMsg.A.no(i).rtlWhNm_A1.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                                setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                                return false;
                            }
                        }

                        // Ship tp Customer Check
                        if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).shipToLocNm_E1)) {
                            sMsg.A.no(i).shipToLocNm_E1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_SHIP_TO_LOC_NM });
                            setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                            return false;
                        } else {
                            int count = getShipToCustCd(glblCmpyCd, sMsg.A.no(i).shipToCustCd_E1, sMsg.A.no(i).shipToLocNm_E1);
                            if (count == 0) {
                                sMsg.A.no(i).shipToLocNm_E1.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_SHIP_TO_LOC_NM });
                                setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                                return false;
                            }
                            if (count > 1) {
                                sMsg.A.no(i).shipToLocNm_E1.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_SHIP_TO_LOC_NM });
                                setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                                return false;
                            }
                        }

                        // Default Bill Check
                        // QC#52809
//                        NMZC610001PMsg pMsg = checkDefaultBillShip(glblCmpyCd, sMsg.A.no(i).shipToCustCd_E1.getValue());
//                        if (pMsg == null) {
//                            sMsg.A.no(i).shipToLocNm_E1.setErrorInfo(1, NPBM0004E, new String[] {DISPLAY_NM_SHIP_TO_CUST_CD, DISPLAY_NM_SELL_TO_CUST_CD });
//                            setPageShowFromNumOnCMsg(cMsg, sMsg, i);
//                            return false;
//                        }
//                        if (pMsg.xxMsgIdList.getValidCount() != 0) {
//                            for (int ii = 0; ii < pMsg.xxMsgIdList.getValidCount(); ii++) {
//                                sMsg.A.no(i).shipToLocNm_E1.setErrorInfo(1, pMsg.xxMsgIdList.no(ii).xxMsgId.getValue());
//                            }
//                            return false;
//                        }
//                        if (!hasValidValue(pMsg.DefaultBillShipList)) {
//                            sMsg.A.no(i).shipToLocNm_E1.setErrorInfo(1, NPBM0004E, new String[] {DISPLAY_NM_SELL_TO_CUST_CD, DISPLAY_NM_BILL_TO_CUST_CD });
//                            setPageShowFromNumOnCMsg(cMsg, sMsg, i);
//                            return false;
//                        }
//                        if (!ZYPCommonFunc.hasValue((pMsg.DefaultBillShipList.no(0).billToCustCd))) {
//                            sMsg.A.no(i).shipToLocNm_E1.setErrorInfo(1, NPBM0004E, new String[] {DISPLAY_NM_SELL_TO_CUST_CD, DISPLAY_NM_BILL_TO_CUST_CD });
//                            setPageShowFromNumOnCMsg(cMsg, sMsg, i);
//                            return false;
//                        }
                        String defBillToCustCd = checkDefaultBillShip(sMsg.A.no(i), glblCmpyCd, sMsg.A.no(i).shipToCustCd_E1.getValue(), true);
                        if (defBillToCustCd == null) {
                            sMsg.A.no(i).shipToLocNm_E1.setErrorInfo(1, NPBM0004E, new String[] {DISPLAY_NM_SHIP_TO_CUST_CD, DISPLAY_NM_BILL_TO_CUST_CD });
                            setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                            return false;
                        }

                    } else {
                        // ConfigItemLine
                        if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).srcRtlSwhCd_A1)) {
                            sMsg.A.no(i).srcRtlSwhCd_A1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_SRC_RTL_SWH_CD_LINE });
                            setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                            return false;
                        }

                        // Set ConfigLine
                        // START 2022/12/27 T.Kuroda[QC#60562, MOD]
                        // ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).srcRtlWhCd_A1, sMsg.A.no(configLineIndex).srcRtlWhCd_A1);
                        // ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhNm_A1, sMsg.A.no(configLineIndex).rtlWhNm_A1);
                        // ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlSwhNm_A1, sMsg.A.no(configLineIndex).rtlSwhNm_A1);
                        // ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhCatgCd_A1, sMsg.A.no(configLineIndex).rtlWhCatgCd_A1);
                        // ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqLineTpCd_A1, sMsg.A.no(configLineIndex).prchReqLineTpCd_A1);
                        // ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).shipToCustCd_E1, sMsg.A.no(configLineIndex).shipToCustCd_E1);
                        // ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).shipToLocNm_E1, sMsg.A.no(configLineIndex).shipToLocNm_E1);
                        if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).srcRtlWhCd_A1)) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).srcRtlWhCd_A1, sMsg.A.no(configLineIndex).srcRtlWhCd_A1);
                        }
                        if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlWhNm_A1)) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhNm_A1, sMsg.A.no(configLineIndex).rtlWhNm_A1);
                        }
                        if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlSwhNm_A1)) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlSwhNm_A1, sMsg.A.no(configLineIndex).rtlSwhNm_A1);
                        }
                        if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlWhCatgCd_A1)) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhCatgCd_A1, sMsg.A.no(configLineIndex).rtlWhCatgCd_A1);
                        }
                        if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).prchReqLineTpCd_A1)) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqLineTpCd_A1, sMsg.A.no(configLineIndex).prchReqLineTpCd_A1);
                        }
                        if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).shipToCustCd_E1)) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).shipToCustCd_E1, sMsg.A.no(configLineIndex).shipToCustCd_E1);
                        }
                        if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).shipToLocNm_E1)) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).shipToLocNm_E1, sMsg.A.no(configLineIndex).shipToLocNm_E1);
                        }
                        // END 2022/12/27 T.Kuroda[QC#60562, MOD]

                        if (!RTL_WH_CATG.SHOWROOM.equals(sMsg.A.no(i).rtlWhCatgCd_A1.getValue()) && !RTL_WH_CATG.SHOWROOM.equals(sMsg.A.no(i).rtlWhCatgCd_A2.getValue()) && !ZYPCommonFunc.hasValue(sMsg.A.no(i).destRtlSwhCd_A1)) {
                            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).destRtlSwhCd_A1)) {
                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).destRtlSwhCd_A1, sMsg.A.no(i).srcRtlSwhCd_A1);
                            }
                        }
                    }

                } else {
                    // Item line
                    if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlWhNm_A1)) {
                        sMsg.A.no(i).rtlWhNm_A1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                        setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                        return false;
                    } else {
                        int count = checkSrcRtlWhFromName(glblCmpyCd, sMsg.A.no(i).rtlWhNm_A1, sMsg.A.no(i).srcRtlWhCd_A1, sMsg.A.no(i).rtlWhCatgCd_A1);
                        if (count == 0) {
                            sMsg.A.no(i).rtlWhNm_A1.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                            setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                            return false;
                        }
                        if (count > 1) {
                            sMsg.A.no(i).rtlWhNm_A1.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                            setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                            return false;
                        }
                    }

                    if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).shipToLocNm_E1)) {
                        sMsg.A.no(i).shipToLocNm_E1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_SHIP_TO_LOC_NM });
                        setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                        return false;
                    } else {
                        int count = getShipToCustCd(glblCmpyCd, sMsg.A.no(i).shipToCustCd_E1, sMsg.A.no(i).shipToLocNm_E1);
                        if (count == 0) {
                            sMsg.A.no(i).shipToLocNm_E1.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_SHIP_TO_LOC_NM });
                            setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                            return false;
                        }
                        if (count > 1) {
                            sMsg.A.no(i).shipToLocNm_E1.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_SHIP_TO_LOC_NM });
                            setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                            return false;
                        }
                    }

                    // Default Bill Check
                    // QC#52809
//                    NMZC610001PMsg pMsg = checkDefaultBillShip(glblCmpyCd, sMsg.A.no(i).shipToCustCd_E1.getValue());
//                    if (pMsg == null) {
//                        sMsg.A.no(i).shipToLocNm_E1.setErrorInfo(1, NPBM0004E, new String[] {DISPLAY_NM_SHIP_TO_CUST_CD, DISPLAY_NM_SELL_TO_CUST_CD });
//                        setPageShowFromNumOnCMsg(cMsg, sMsg, i);
//                        return false;
//                    }
//                    if (pMsg.xxMsgIdList.getValidCount() != 0) {
//                        for (int ii = 0; ii < pMsg.xxMsgIdList.getValidCount(); ii++) {
//                            sMsg.A.no(i).shipToLocNm_E1.setErrorInfo(1, pMsg.xxMsgIdList.no(ii).xxMsgId.getValue());
//                        }
//                        return false;
//                    }
//                    if (!hasValidValue(pMsg.DefaultBillShipList)) {
//                        sMsg.A.no(i).shipToLocNm_E1.setErrorInfo(1, NPBM0004E, new String[] {DISPLAY_NM_SELL_TO_CUST_CD, DISPLAY_NM_BILL_TO_CUST_CD });
//                        setPageShowFromNumOnCMsg(cMsg, sMsg, i);
//                        return false;
//                    }
//                    if (!ZYPCommonFunc.hasValue((pMsg.DefaultBillShipList.no(0).billToCustCd))) {
//                        sMsg.A.no(i).shipToLocNm_E1.setErrorInfo(1, NPBM0004E, new String[] {DISPLAY_NM_SELL_TO_CUST_CD, DISPLAY_NM_BILL_TO_CUST_CD });
//                        setPageShowFromNumOnCMsg(cMsg, sMsg, i);
//                        return false;
//                    }
                    String defBillToCustCd = checkDefaultBillShip(sMsg.A.no(i), glblCmpyCd, sMsg.A.no(i).shipToCustCd_E1.getValue(), false);
                    if (defBillToCustCd == null) {
                        sMsg.A.no(i).shipToLocNm_E1.setErrorInfo(1, NPBM0004E, new String[] {DISPLAY_NM_SHIP_TO_CUST_CD, DISPLAY_NM_BILL_TO_CUST_CD });
                        setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                        return false;
                    }
                }
            }

            // Get SubWh(d-3)
            if (PRCH_REQ_TP.WH_TRANSFER.equals(sMsg.prchReqTpCd_SL.getValue()) && (RTL_WH_CATG.SHOWROOM.equals(sMsg.A.no(i).rtlWhCatgCd_A1.getValue()) || RTL_WH_CATG.SHOWROOM.equals(sMsg.A.no(i).rtlWhCatgCd_A2.getValue()))) {
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseCd_A1)) {
                    // V0.9

                    // 2018/06/06 QC#26511 MOD START
                    // showroom to equipment transfer does not Re-Val.
                    if ((RTL_WH_CATG.SHOWROOM.equals(sMsg.A.no(i).rtlWhCatgCd_A1.getValue()) && RTL_WH_CATG.EQUIPMENT.equals(sMsg.A.no(i).rtlWhCatgCd_A2.getValue()))) {

                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).destRtlSwhCd_A1, sMsg.A.no(i).srcRtlSwhCd_A1.getValue());

                    } else
                    // 2018/06/06 QC#26511 MOD END
                    if (!(RTL_WH_CATG.SHOWROOM.equals(sMsg.A.no(i).rtlWhCatgCd_A1.getValue()) && RTL_WH_CATG.SHOWROOM.equals(sMsg.A.no(i).rtlWhCatgCd_A2.getValue()))) {
                        String prchReqLineNum = sMsg.A.no(i).prchReqLineNum_A1.getValue();
                        String destRtlSwhCd = showroomSwhMap.get(prchReqLineNum);

                        if (!ZYPCommonFunc.hasValue(destRtlSwhCd)) {
                            BigDecimal mdlGrpId = BigDecimal.ONE.negate();
                            BigDecimal mdlId = BigDecimal.ONE.negate();
                            String mdseCd = sMsg.A.no(i).mdseCd_A1.getValue();
                            String srcRtlWhCd = sMsg.A.no(i).srcRtlWhCd_A1.getValue();
                            String destRtlWhCd = sMsg.A.no(i).destRtlWhCd_A1.getValue();

                            String configTpCd = sMsg.A.no(i).configTpCd_A1.getValue();
                            if (ZYPCommonFunc.hasValue(configTpCd)) {
                                // Config Item
                                List<Map<String, Object>> configItemList = getConfigItemList(sMsg, glblCmpyCd, prchReqLineNum);
                                Map<String, Object> mainMach = getMainMachFromConfigItemList(configItemList);
                                if (mainMach != null) {
                                    String mainMachLineNum = (String) mainMach.get(MAP_KEY_PRCH_REQ_LINE_NUM);
                                    BigDecimal mainMachLineSubNum = (BigDecimal) mainMach.get(MAP_KEY_PRCH_REQ_LINE_SUB_NUM);
                                    int mainMachIndex = getIndexByLineSubNum(sMsg, mainMachLineNum, mainMachLineSubNum);
                                    if (mainMachIndex >= 0) {
                                        NPBL0020_ASMsg asMsg = sMsg.A.no(mainMachIndex);
                                        mdseCd = asMsg.mdseCd_A1.getValue();
                                        srcRtlWhCd = asMsg.srcRtlWhCd_A1.getValue();
                                        destRtlWhCd = asMsg.destRtlWhCd_A1.getValue();
                                    }

                                    // Get MDL_ID
                                    NSZC048001PMsg pMsg = createServiceModelAPIParam(glblCmpyCd, mainMach, configItemList);
                                    NSZC048001 api = new NSZC048001();
                                    api.execute(pMsg, ONBATCH_TYPE.ONLINE);
                                    boolean success = true;
                                    if (S21ApiUtil.isXxMsgId(pMsg)) {
                                        List<String> msgIdList = S21ApiUtil.getXxMsgIdList(pMsg);
                                        for (String msgId : msgIdList) {
                                            if (msgId.endsWith("E")) {
                                                success = false;
                                                break;
                                            }
                                        }
                                    }
                                    if (success) {
                                        mdlId = pMsg.mdlId.getValue();
                                    }
                                }

                                // Get MDL_GRP_ID from MDL_ID
                                if (mdlId != null && BigDecimal.ZERO.compareTo(mdlId) <= 0) {
                                    DS_MDLTMsg tMsg = new DS_MDLTMsg();
                                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                                    ZYPEZDItemValueSetter.setValue(tMsg.mdlId, mdlId);
                                    tMsg = (DS_MDLTMsg) EZDTBLAccessor.findByKey(tMsg);
                                    if (tMsg != null && EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                                        mdlGrpId = tMsg.mdlGrpId.getValue();
                                    }
                                }
                            }

                            // START 2019/05/17 M.Naito [QC#50076,MOD]
                            // check Default Sub WH
                            String srcRtlWhCatgCd = getRtlWhCatgCd(glblCmpyCd, srcRtlWhCd);
                            String destRtlWhCatgCd = getRtlWhCatgCd(glblCmpyCd, destRtlWhCd);
                            if (ZYPCommonFunc.hasValue(srcRtlWhCatgCd) && ZYPCommonFunc.hasValue(destRtlWhCatgCd) && !checkDefSwhByWhCatg(glblCmpyCd, srcRtlWhCatgCd, destRtlWhCatgCd, mdlGrpId)) {
                                destRtlSwhCd = sMsg.A.no(i).srcRtlSwhCd_A1.getValue();
                            } else {
                                // Get Default Sub WH
                                NLZC215001PMsg pMsg = new NLZC215001PMsg();
                                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
                                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
                                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, MODE_VAL_SHRM);
                                ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, mdseCd);
                                ZYPEZDItemValueSetter.setValue(pMsg.srcRtlWhCd, srcRtlWhCd);
                                ZYPEZDItemValueSetter.setValue(pMsg.destRtlWhCd, destRtlWhCd);
                                ZYPEZDItemValueSetter.setValue(pMsg.mdlGrpId, mdlGrpId);

                                NLZC215001 nlzc215001 = new NLZC215001();
                                nlzc215001.execute(pMsg, ONBATCH_TYPE.ONLINE);
                                if (pMsg.xxMsgIdList.getValidCount() != 0) {
                                    for (int ii = 0; ii < pMsg.xxMsgIdList.getValidCount(); ii++) {
                                        cMsg.setMessageInfo(pMsg.xxMsgIdList.no(ii).xxMsgId.getValue());
                                    }
                                    return false;
                                }

                                destRtlSwhCd = pMsg.destRtlSwhCd.getValue();
                                showroomSwhMap.put(prchReqLineNum, destRtlSwhCd);
                            }
                            // END 2019/05/17 M.Naito [QC#50076,MOD]
                        }

                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).destRtlSwhCd_A1, destRtlSwhCd);
                    }
                }
            }

            // Location Security Check
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).configTpCd_A1) || (ZYPCommonFunc.hasValue(sMsg.A.no(i).configTpCd_A1) && sMsg.A.no(i).prchReqLineSubNum_A1.getValueInt() > 0)) {
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).srcRtlWhCd_A1)) {
                    NMXC100001EnableWHData result = NMXC100001EnableWH.checkEnableWH(glblCmpyCd, ZYPCommonFunc.concatString(sMsg.A.no(i).srcRtlWhCd_A1.getValue(), "", sMsg.A.no(i).srcRtlSwhCd_A1.getValue()), BIZ_APP_ID, null,
                            ZYPConstant.FLG_OFF_N, sMsg.prchReqTpCd_SL.getValue());
                    if (result.getXxMsgId() != null) {
                        sMsg.A.no(i).rtlWhNm_A1.setErrorInfo(1, result.getXxMsgId());
                        sMsg.A.no(i).srcRtlSwhCd_A1.setErrorInfo(1, result.getXxMsgId());
                        setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                        return false;
                    }
                }

                if (!PRCH_REQ_TP.EXPENSE_ORDER.equals(sMsg.prchReqTpCd_SL.getValue())) {
                    if (ZYPCommonFunc.hasValue(sMsg.A.no(i).destRtlWhCd_A1)) {
                        //QC#17362
                        NMXC100001EnableWHData result = null;
                        if (PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue())) {
                            result = NMXC100001EnableWH.checkEnableWH(glblCmpyCd, ZYPCommonFunc.concatString(sMsg.A.no(i).destRtlWhCd_A1.getValue(), "", sMsg.A.no(i).destRtlSwhCd_A1.getValue()), BIZ_APP_ID, null, ZYPConstant.FLG_ON_Y,
                                    sMsg.prchReqTpCd_SL.getValue());
                        } else {
                            result = NMXC100001EnableWH.checkEnableWH(glblCmpyCd, ZYPCommonFunc.concatString(sMsg.A.no(i).destRtlWhCd_A1.getValue(), "", sMsg.A.no(i).destRtlSwhCd_A1.getValue()), BIZ_APP_ID, null, ZYPConstant.FLG_OFF_N,
                                    sMsg.prchReqTpCd_SL.getValue());
                        }
                        if (result.getXxMsgId() != null) {
                            sMsg.A.no(i).rtlWhNm_A2.setErrorInfo(1, result.getXxMsgId());
                            sMsg.A.no(i).destRtlSwhCd_A1.setErrorInfo(1, result.getXxMsgId());
                            setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                            return false;
                        }
                        //QC#17362
                        if (PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue())) {
                            if (!NPBL0020Query.getInstance().checkVendorWh(glblCmpyCd, sMsg.A.no(i).destRtlWhCd_A1.getValue(), sMsg.prntVndCd.getValue())) {
                                sMsg.A.no(i).rtlWhNm_A2.setErrorInfo(1, "NMXM0010E");
                                sMsg.A.no(i).destRtlSwhCd_A1.setErrorInfo(1, "NMXM0010E");
                                setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                                return false;
                            }
                        }
                    }
                }
            }

            // SubWh Check(d-2)
            if (PRCH_REQ_TP.WH_TRANSFER.equals(sMsg.prchReqTpCd_SL.getValue()) && !RTL_WH_CATG.SHOWROOM.equals(sMsg.A.no(i).rtlWhCatgCd_A1.getValue()) && !RTL_WH_CATG.SHOWROOM.equals(sMsg.A.no(i).rtlWhCatgCd_A2.getValue())) {
                // START 2018/04/03 S.Katsuma [QC#23521,25063,DEL]
                // Check Inventory Account
//                if (!checkInventoryAccount(sMsg.A.no(i), glblCmpyCd)) {
//                    sMsg.A.no(i).rtlWhNm_A1.setErrorInfo(1, NPAM1363E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW, DISPLAY_NM_RTL_WH_NM_LINE_SW });
//                    sMsg.A.no(i).rtlWhNm_A2.setErrorInfo(1, NPAM1363E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW, DISPLAY_NM_RTL_WH_NM_LINE_SW });
//                    setPageShowFromNumOnCMsg(cMsg, sMsg, i);
//                    return false;
//                }
                // END 2018/04/03 S.Katsuma [QC#23521,25063,DEL]
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseCd_A1)) {

                    // Check Cost Type / Cost Ratio
                    NLXC001001GetInventoryItemCostBean nlxc001001BeanSrc = getInvtyItemCost(glblCmpyCd,
                            sMsg.A.no(i).srcRtlWhCd_A1.getValue(),
                            sMsg.A.no(i).srcRtlSwhCd_A1.getValue(),
                            sMsg.A.no(i).mdseCd_A1.getValue(),
                            sMsg.A.no(i).prchReqDispQty_A1.getValue());

                    NLXC001001GetInventoryItemCostBean nlxc001001BeanDest = getInvtyItemCost(glblCmpyCd,
                            sMsg.A.no(i).destRtlWhCd_A1.getValue(),
                            sMsg.A.no(i).destRtlSwhCd_A1.getValue(),
                            sMsg.A.no(i).mdseCd_A1.getValue(),
                            sMsg.A.no(i).prchReqDispQty_A1.getValue());

                    if (!nlxc001001BeanSrc.getErrList().isEmpty() || !nlxc001001BeanDest.getErrList().isEmpty()) {
                        sMsg.A.no(i).rtlWhNm_A1.setErrorInfo(1, NPAM1363E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW, DISPLAY_NM_RTL_WH_NM_LINE_SW });
                        sMsg.A.no(i).rtlWhNm_A2.setErrorInfo(1, NPAM1363E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW, DISPLAY_NM_RTL_WH_NM_LINE_SW });
                        setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                        return false;
                    }
                    if (nlxc001001BeanSrc.getMdseCostTpCd() != null && !nlxc001001BeanSrc.getMdseCostTpCd().equals(nlxc001001BeanDest.getMdseCostTpCd())) {
                        sMsg.A.no(i).rtlWhNm_A1.setErrorInfo(1, NPAM1363E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW, DISPLAY_NM_RTL_WH_NM_LINE_SW });
                        sMsg.A.no(i).rtlWhNm_A2.setErrorInfo(1, NPAM1363E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW, DISPLAY_NM_RTL_WH_NM_LINE_SW });
                        setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                        return false;
                    } else if (nlxc001001BeanSrc.getMdseCostTpCd() == null && nlxc001001BeanDest.getMdseCostTpCd() != null) {
                        sMsg.A.no(i).rtlWhNm_A1.setErrorInfo(1, NPAM1363E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW, DISPLAY_NM_RTL_WH_NM_LINE_SW });
                        sMsg.A.no(i).rtlWhNm_A2.setErrorInfo(1, NPAM1363E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW, DISPLAY_NM_RTL_WH_NM_LINE_SW });
                        setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                        return false;
                    }
                    if (nlxc001001BeanSrc.getMdseInvtyCostPct() != null && !nlxc001001BeanSrc.getMdseInvtyCostPct().equals(nlxc001001BeanDest.getMdseInvtyCostPct())) {
                        sMsg.A.no(i).rtlWhNm_A1.setErrorInfo(1, NPAM1363E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW, DISPLAY_NM_RTL_WH_NM_LINE_SW });
                        sMsg.A.no(i).rtlWhNm_A2.setErrorInfo(1, NPAM1363E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW, DISPLAY_NM_RTL_WH_NM_LINE_SW });
                        setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                        return false;
                    } else if (nlxc001001BeanSrc.getMdseInvtyCostPct() == null && nlxc001001BeanDest.getMdseInvtyCostPct() != null) {
                        sMsg.A.no(i).rtlWhNm_A1.setErrorInfo(1, NPAM1363E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW, DISPLAY_NM_RTL_WH_NM_LINE_SW });
                        sMsg.A.no(i).rtlWhNm_A2.setErrorInfo(1, NPAM1363E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW, DISPLAY_NM_RTL_WH_NM_LINE_SW });
                        setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                        return false;
                    }
                }
            } else if (PRCH_REQ_TP.WH_TRANSFER.equals(sMsg.prchReqTpCd_SL.getValue())) {
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseCd_A1)) {
                    // Check Cost Type / Cost Ratio
                    NLXC001001GetInventoryItemCostBean nlxc001001BeanSrc = getInvtyItemCost(glblCmpyCd,
                            sMsg.A.no(i).srcRtlWhCd_A1.getValue(),
                            sMsg.A.no(i).srcRtlSwhCd_A1.getValue(),
                            sMsg.A.no(i).mdseCd_A1.getValue(),
                            sMsg.A.no(i).prchReqDispQty_A1.getValue());
                    if (!nlxc001001BeanSrc.getErrList().isEmpty()) {
                        sMsg.A.no(i).rtlWhNm_A1.setErrorInfo(1, NPAM1363E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW, DISPLAY_NM_RTL_WH_NM_LINE_SW });
                        sMsg.A.no(i).rtlWhNm_A2.setErrorInfo(1, NPAM1363E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW, DISPLAY_NM_RTL_WH_NM_LINE_SW });
                        setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                        return false;
                    }
                }
            }

            // call Serial Validation API
            if (!PRCH_REQ_TP.KITTING.equals(sMsg.prchReqTpCd_SL.getValue())) {
                if (!callSerialValidationAPI(sMsg.A.no(i), glblCmpyCd)) {
                    setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                    return false;
                }
            }
            // START 2019/08/27 M.Naito [QC#52276,ADD]
            // START 2018/11/06 M.Naito [QC#28698,ADD]
//            if (PRCH_REQ_TP.WH_TRANSFER.equals(sMsg.prchReqTpCd_SL.getValue())) {
            if (PRCH_REQ_TP.WH_TRANSFER.equals(sMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.DISPOSAL.equals(sMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.EXPENSE_ORDER.equals(sMsg.prchReqTpCd_SL.getValue())) {

                if (!checkStkStsCd(sMsg.A.no(i), glblCmpyCd)) {
                    sMsg.A.no(i).fromStkStsCd_A1.setErrorInfo(1, NLZM2414E);
                    setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                    return false;
                }
                if (isExistConfig(sMsg.A.no(i), glblCmpyCd)) {
                    sMsg.A.no(i).xxLogDtlTxt_A1.setErrorInfo(1, NPBM0018E);
                    setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                    return false;
                }
            }
            // END 2018/11/06 M.Naito [QC#28698,ADD]
            // END 2019/08/27 M.Naito [QC#52276,ADD]

            // QC#21288 Check Invty Avairable Qty
            if (!(PRCH_REQ_TP.WH_TRANSFER.equals(sMsg.prchReqTpCd_SL.getValue()) //
                    || PRCH_REQ_TP.EXPENSE_ORDER.equals(sMsg.prchReqTpCd_SL.getValue()) //
                    || PRCH_REQ_TP.KITTING.equals(sMsg.prchReqTpCd_SL.getValue())
                    )
                    && ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseCd_A1)) {
                if (!checkAvairableQty(sMsg.A.no(i), glblCmpyCd)) {
                    sMsg.A.no(i).prchReqDispQty_A1.setErrorInfo(1, NPBM0016E, new String[] {DISPLAY_NM_PRCH_REQ_DISP_QTY });
                    setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                    return false;
                }
            }

            // START 2021/11/09 R.Azucena[QC#58586, ADD]
            if (rqstTypesSingleItemCheck.contains(sMsg.prchReqTpCd_SL.getValue())
                    && !ZYPCommonFunc.hasValue(sMsg.A.no(i).svcConfigMstrPk_A1)
                    && ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseCd_A1)) {
                if (!checkAvailableSingleQty(sMsg.A.no(i), glblCmpyCd)) {
                    sMsg.A.no(i).prchReqDispQty_A1.setErrorInfo(1, NPBM0022E, new String[] {DISPLAY_NM_PRCH_REQ_DISP_QTY });
                    setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                    return false;
                }
            }
            // END 2021/11/09 R.Azucena[QC#58586, ADD]

            // QC#15815
            // START 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
            if (!(!NPBL0020CommonLogic.isNeededAccount(sMsg.chrgAcctEdtblFlg.getValue())||
            // END 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
                    (ZYPCommonFunc.hasValue(sMsg.A.no(i).configTpCd_A1)&&BigDecimal.ZERO.equals(sMsg.A.no(i).prchReqLineSubNum_A1.getValue())))) {
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).xxScrItem50Txt_A1)) {
                    // Set Account
                    getAccountForSaveAndSubmit(sMsg, glblCmpyCd, i);
                } else {
                    // Manual Account Check
                    boolean check = NPBL0020CommonLogic.getAccount(cMsg, sMsg, glblCmpyCd, i, true, false);
                    if (!check) {
                        setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                        return false;
                    }
                }

                // Check Account
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).coaCmpyCd_A1) || !ZYPCommonFunc.hasValue(sMsg.A.no(i).coaAfflCd_A1) || !ZYPCommonFunc.hasValue(sMsg.A.no(i).coaBrCd_A1) || !ZYPCommonFunc.hasValue(sMsg.A.no(i).coaCcCd_A1)
                        || !ZYPCommonFunc.hasValue(sMsg.A.no(i).coaAcctCd_A1) || !ZYPCommonFunc.hasValue(sMsg.A.no(i).coaProdCd_A2) || !ZYPCommonFunc.hasValue(sMsg.A.no(i).coaChCd_A1) || !ZYPCommonFunc.hasValue(sMsg.A.no(i).coaProjCd_A1)
                        || !ZYPCommonFunc.hasValue(sMsg.A.no(i).coaExtnCd_A1)) {
                    sMsg.A.no(i).xxScrItem50Txt_A1.setErrorInfo(1, ZZZM9025E, new String[] {MSG_VALUE_CA });
                    setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                    return false;
                }
            }

            // QC#15592
            if (PRCH_REQ_TP.EXPENSE_ORDER.equals(sMsg.prchReqTpCd_SL.getValue())) {
                if(!(ZYPCommonFunc.hasValue(sMsg.A.no(i).configTpCd_A1) && BigDecimal.ZERO.equals(sMsg.A.no(i).prchReqLineSubNum_A1.getValue()))) {
                    if (isCoaGlCmbn4SegError(sMsg.A.no(i), glblCmpyCd)) {
                        sMsg.A.no(i).xxScrItem50Txt_A1.setErrorInfo(1, NPBM0010E, null);
                        setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                        return false;
                    } else { // 12/12/2018 QC#29456 Add Start
                        NPXC001001GetDefNineSegDataBean defNineSegDataBean = NPXC001001GetDefNineSegData.getInstance().checkDefault(glblCmpyCd, //
                                sMsg.A.no(i).coaAcctCd_A1.getValue(), //
                                sMsg.A.no(i).coaProdCd_A2.getValue(), //
                                sMsg.A.no(i).coaProjCd_A1.getValue());
                        if (defNineSegDataBean != null //
                                && defNineSegDataBean.getMsgIdList() != null //
                                && !defNineSegDataBean.getMsgIdList().isEmpty()) {
                            String errMsgId = defNineSegDataBean.getMsgId(IDX_0);
                            sMsg.A.no(i).xxScrItem50Txt_A1.setErrorInfo(1, errMsgId, null);
                            setPageShowFromNumOnCMsg(cMsg, sMsg, i);
                            return false;
                        }
                        // 12/12/2018 QC#29456 Add End
                    }
                }
            }

            // Set Cost
            // START 2018/05/25 S.Katsuma [QC#25893,MOD]
            if (!PRCH_REQ_TP.VENDOR_RETURN.equals(sMsg.prchReqTpCd_SL.getValue())
                    || (PRCH_REQ_TP.VENDOR_RETURN.equals(sMsg.prchReqTpCd_SL.getValue()) && !ZYPCommonFunc.hasValue(sMsg.A.no(i).entDealNetUnitPrcAmt_A1))) {
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseCd_A1)) {
                    NLXC001001GetInventoryItemCostBean nlxc001001Bean = getInvtyItemCost(glblCmpyCd,
                            sMsg.A.no(i).srcRtlWhCd_A1.getValue(),
                            sMsg.A.no(i).srcRtlSwhCd_A1.getValue(),
                            sMsg.A.no(i).mdseCd_A1.getValue(),
                            sMsg.A.no(i).prchReqDispQty_A1.getValue());
                    if (!nlxc001001Bean.getErrList().isEmpty()) {
                        cMsg.setMessageInfo(nlxc001001Bean.getErrList().get(0));
                        return false;
                    }
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).entDealNetUnitPrcAmt_A1, nlxc001001Bean.getUnitPrcAmt());
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).entPoDtlDealNetAmt_A1, nlxc001001Bean.getTotPrcAmt());
                }
            } else {
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).prchReqDispQty_A1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).entPoDtlDealNetAmt_A1, sMsg.A.no(i).entDealNetUnitPrcAmt_A1.getValue().multiply(ZYPCommonFunc.getBigDecimal(sMsg.A.no(i).prchReqDispQty_A1, "0")));
                }
            }
            // END 2018/05/25 S.Katsuma [QC#25893,MOD]
        }

        // Set Ship To Address
        if (PRCH_REQ_TP.WH_TRANSFER.equals(sMsg.prchReqTpCd_SL.getValue()) && //
                !SCREEN_CTRL_VALUE_MULTIPLE.equals(sMsg.destRtlWhCd.getValue()) && //
                ZYPCommonFunc.hasValue(sMsg.destRtlSwhCd)) {

            boolean ret = setShipToAddressForSaveAndSubmit(cMsg, sMsg, glblCmpyCd, sMsg.A.no(0).destRtlWhCd_A1.getValue() + sMsg.A.no(0).destRtlSwhCd_A1.getValue());
            if (!ret) {
                sMsg.A.no(0).destRtlWhCd_A1.setErrorInfo(1, NPZM0146E);
                cMsg.setMessageInfo(NPZM0146E);
            }

        } else if (!PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue()) && ZYPCommonFunc.hasValue(sMsg.vndCd)) {

            boolean ret = setShipToAddressForSaveAndSubmit(cMsg, sMsg, glblCmpyCd, sMsg.vndCd.getValue());
            if (!ret) {
                cMsg.vndCd.setErrorInfo(1, NPZM0146E);
                cMsg.setMessageInfo(NPZM0146E);
            }

        } else if (SCREEN_CTRL_VALUE_MULTIPLE.equals(sMsg.destRtlWhCd.getValue())) {

            boolean ret = setShipToAddressForSaveAndSubmit(cMsg, sMsg, glblCmpyCd, sMsg.A.no(0).destRtlWhCd_A1.getValue());
            if (!ret) {
                sMsg.A.no(0).destRtlWhCd_A1.setErrorInfo(1, NPZM0146E);
                cMsg.setMessageInfo(NPZM0146E);
            }

        } else if (SCREEN_CTRL_VALUE_MULTIPLE.equals(sMsg.shipToCustCd_EO.getValue())) {

            boolean ret = setShipToAddressForSaveAndSubmit(cMsg, sMsg, glblCmpyCd, sMsg.A.no(0).shipToCustCd_E1.getValue());

            if (!ret) {
                sMsg.A.no(0).shipToCustCd_E1.setErrorInfo(1, NPZM0146E);
                cMsg.setMessageInfo(NPZM0146E);
            }

        } else if (PRCH_REQ_TP.WH_TRANSFER.equals(sMsg.prchReqTpCd_SL.getValue())) {

            boolean ret = setShipToAddressForSaveAndSubmit(cMsg, sMsg, glblCmpyCd, sMsg.A.no(0).destRtlWhCd_A1.getValue());
            if (!ret) {
                sMsg.A.no(0).destRtlWhCd_A1.setErrorInfo(1, NPZM0146E);
                cMsg.setMessageInfo(NPZM0146E);
            }

        } else if (PRCH_REQ_TP.EXPENSE_ORDER.equals(sMsg.prchReqTpCd_SL.getValue())) {

            boolean ret = setShipToAddressForSaveAndSubmit(cMsg, sMsg, glblCmpyCd, sMsg.A.no(0).shipToCustCd_E1.getValue());
            if (!ret) {
                sMsg.A.no(0).shipToCustCd_E1.setErrorInfo(1, NPZM0146E);
                cMsg.setMessageInfo(NPZM0146E);
            }

        } else if (PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue())) {

            boolean ret = setShipToAddressForSaveAndSubmit(cMsg, sMsg, glblCmpyCd, sMsg.A.no(0).destRtlWhCd_A1.getValue());
            if (!ret) {
                sMsg.A.no(0).destRtlWhCd_A1.setErrorInfo(1, NPZM0146E);
                cMsg.setMessageInfo(NPZM0146E);
            }

        } else if (PRCH_REQ_TP.DISPOSAL.equals(sMsg.prchReqTpCd_SL.getValue())) {

            boolean ret = setShipToAddressForSaveAndSubmit(cMsg, sMsg, glblCmpyCd, sMsg.A.no(0).srcRtlWhCd_A1.getValue());
            if (!ret) {
                sMsg.A.no(0).srcRtlWhCd_A1.setErrorInfo(1, NPZM0146E);
                cMsg.setMessageInfo(NPZM0146E);
            }
        // QC#18408
        } else if (PRCH_REQ_TP.KITTING.equals(sMsg.prchReqTpCd_SL.getValue())) {

            boolean ret = setShipToAddressForSaveAndSubmit(cMsg, sMsg, glblCmpyCd, sMsg.destRtlWhCd.getValue());
            if (!ret) {
                cMsg.destRtlWhCd.setErrorInfo(1, NPZM0146E);
                cMsg.setMessageInfo(NPZM0146E);
            }
        }

        // QC#21682 Start
        if ((PRCH_REQ_TP.DISPOSAL.equals(sMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.VENDOR_RETURN.equals(sMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.REFURBISHING.equals(sMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue()) //
                ) //
                && ZYPCommonFunc.hasValue(sMsg.shipToCustCd_EO)) {

            int count = getShipToCustCd(glblCmpyCd, sMsg.shipToCustCd_EO, sMsg.shipToLocNm_EO);
            if (count == 0) {
                sMsg.shipToCustCd_EO.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_SHIP_TO_LOC_NM });
                cMsg.setMessageInfo(NPZM0146E);
            }
            if (count > 1) {
                sMsg.shipToCustCd_EO.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_SHIP_TO_LOC_NM });
                cMsg.setMessageInfo(NPZM0146E);
            }

            boolean ret = setShipToAddressForSaveAndSubmit(cMsg, sMsg, glblCmpyCd, sMsg.shipToCustCd_EO.getValue());
            if (!ret) {
                sMsg.A.no(0).shipToCustCd_E1.setErrorInfo(1, NPZM0146E);
                cMsg.setMessageInfo(NPZM0146E);
            }

        }
        // QC#21682 End

        // Check Config Line
        if (!checkConfigLine(cMsg, sMsg, glblCmpyCd)) {
            return false;
        }

        return true;
    }

    public static void setPageShowFromNumOnCMsg(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, int positionNum) {
        int page = positionNum / cMsg.A.length();
        int showFrom = page * cMsg.A.length() + 1;
        cMsg.xxPageShowFromNum.setValue(showFrom);
    }

    /**
     * Save
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     */
    public static void save(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {

        //Clear readonly components - filled on response
        //cMsg
    	// QC#22517
    	if(PRCH_REQ_TP.EXPENSE_ORDER.equals(cMsg.prchReqTpCd_SL.getValue())) {
    		// Do Nothing
    	} else {
            cMsg.shipToLocNm.clear();
            cMsg.shipToAddlLocNm.clear();
            cMsg.shipToFirstLineAddr.clear();
            cMsg.shipToScdLineAddr.clear();
            cMsg.shipToThirdLineAddr.clear();
            cMsg.shipToFrthLineAddr.clear();
            cMsg.xxShipVndAddr.clear();
            cMsg.shipToPostCd.clear();
            cMsg.shipToCtyAddr.clear();
            cMsg.shipToCntyNm.clear();
            cMsg.shipToStCd.clear();
            cMsg.shipToProvNm.clear();
            cMsg.shipToCtryCd.clear();
            cMsg.ctryNm.clear();
    		
    	}

        String salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);

        if (SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.srcRtlWhCd.getValue())) {
            cMsg.rtlWhNm_SW.clear();
        } else {
            setRtlWh(cMsg, sMsg, glblCmpyCd, salesDate, true, false);
        }
        if (SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.srcRtlSwhCd.getValue())) {
            cMsg.rtlSwhNm_SS.clear();
        } else {
            setRtlSwh(cMsg, sMsg, glblCmpyCd, salesDate, true, false);
        }
        if (SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.destRtlWhCd.getValue())) {
            cMsg.rtlWhNm_DW.clear();
        } else {
            setRtlWh(cMsg, sMsg, glblCmpyCd, salesDate, false, false);
        }
        if (SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.destRtlSwhCd.getValue())) {
            cMsg.rtlSwhNm_DS.clear();
        } else {
            setRtlSwh(cMsg, sMsg, glblCmpyCd, salesDate, false, false);
        }
        if (SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.shipToCustCd_EO.getValue())) {
            cMsg.shipToLocNm_EO.clear();
        } else {
            String dsAcctnm = NPBL0020CommonLogic.getShipToCustName(glblCmpyCd, cMsg.shipToCustCd_EO.getValue());

            if (ZYPCommonFunc.hasValue(dsAcctnm)) {
                ZYPEZDItemValueSetter.setValue(cMsg.shipToLocNm_EO, dsAcctnm);
            }
            Map<String, String> record = getShipToCustAddr(cMsg, glblCmpyCd, cMsg.shipToCustCd_EO.getValue());
            if (record != null) {
                setShipToAddr(cMsg, record);
            } else {
                clearAddress(cMsg);
            }
        }
        if (SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.vndCd.getValue())) {
            cMsg.dplyVndNm.clear();
        } else {
            checkShipToSupplierCMsg(cMsg, glblCmpyCd);
            Map<String, String> record = getShipToCustAddr(cMsg, glblCmpyCd, cMsg.shipToCustCd_EO.getValue());
            if (record != null) {
                setShipToAddr(cMsg, record);
            } else {
                clearAddress(cMsg);
            }
        }

        copyFromCmsgOntoSmsg(cMsg, sMsg, false);

        if (!check(cMsg, sMsg, glblCmpyCd)) {
            copyFromSMsgDetailOntoCmsgDetail(cMsg, sMsg);
            return;
        }

        // START 2023/07/04 T.Kuroda [QC#61440, ADD]
        if (PRCH_REQ_TP.DISPOSAL.equals(cMsg.prchReqTpCd_SL.getValue())
                && !checkWrnSkip(cMsg, sMsg, glblCmpyCd)) {
            return;
        }
        // END 2023/07/04 T.Kuroda [QC#61440, ADD]

        // Save PR
        PRCH_REQTMsg tMsg = findPR(cMsg, glblCmpyCd);
        NPZC103001PMsg pMsg = null;
        if (tMsg != null) {
            // update
            // optimistic lock
            if (!tryLock(cMsg, sMsg, tMsg)) {
                // cannot lock
                return;
            }
            pMsg = createNPZC103001PMsgForSaveUpdate(sMsg, glblCmpyCd);
        } else {
            // new
            pMsg = createNPZC103001PMsgForSaveCreate(sMsg, glblCmpyCd);
        }

        // call api
        if (executeNPZC1030(cMsg, pMsg)) {
            if (PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue())) {
                if (updatePO(cMsg, sMsg, glblCmpyCd)) {
                    cMsg.setMessageInfo(ZZM8100I);
                }
            } else {
                cMsg.setMessageInfo(ZZM8100I);
            }
        }
    }

    /**
     * Submit
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     */
    public static void submit(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {

        //Clear readonly components - filled on response
        // QC#22517
    	if(PRCH_REQ_TP.EXPENSE_ORDER.equals(cMsg.prchReqTpCd_SL.getValue())) {
    		// Do Nothing
    	} else {
    		cMsg.shipToLocNm.clear();
			cMsg.shipToAddlLocNm.clear();
			cMsg.shipToFirstLineAddr.clear();
			cMsg.shipToScdLineAddr.clear();
			cMsg.shipToThirdLineAddr.clear();
			cMsg.shipToFrthLineAddr.clear();
			cMsg.xxShipVndAddr.clear();
			cMsg.shipToPostCd.clear();
			cMsg.shipToCtyAddr.clear();
			cMsg.shipToCntyNm.clear();
			cMsg.shipToStCd.clear();
			cMsg.shipToProvNm.clear();
			cMsg.shipToCtryCd.clear();
			cMsg.ctryNm.clear();
    	}
        String salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);

        if (SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.srcRtlWhCd.getValue())) {
            cMsg.rtlWhNm_SW.clear();
        } else {
            setRtlWh(cMsg, sMsg, glblCmpyCd, salesDate, true, false);
        }
        if (SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.srcRtlSwhCd.getValue())) {
            cMsg.rtlSwhNm_SS.clear();
        } else {
            setRtlSwh(cMsg, sMsg, glblCmpyCd, salesDate, true, false);
        }
        if (SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.destRtlWhCd.getValue())) {
            cMsg.rtlWhNm_DW.clear();
        } else {
            setRtlWh(cMsg, sMsg, glblCmpyCd, salesDate, false, false);
        }
        if (SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.destRtlSwhCd.getValue())) {
            cMsg.rtlSwhNm_DS.clear();
        } else {
            setRtlSwh(cMsg, sMsg, glblCmpyCd, salesDate, false, false);
        }
        if (SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.shipToCustCd_EO.getValue())) {
            cMsg.shipToLocNm_EO.clear();
        } else {
            String dsAcctnm = NPBL0020CommonLogic.getShipToCustName(glblCmpyCd, cMsg.shipToCustCd_EO.getValue());

            if (ZYPCommonFunc.hasValue(dsAcctnm)) {
                ZYPEZDItemValueSetter.setValue(cMsg.shipToLocNm_EO, dsAcctnm);
            }
            Map<String, String> record = getShipToCustAddr(cMsg, glblCmpyCd, cMsg.shipToCustCd_EO.getValue());
            if (record != null) {
                setShipToAddr(cMsg, record);
            } else {
                clearAddress(cMsg);
            }
        }

        copyFromCmsgOntoSmsg(cMsg, sMsg, false);

        if (!check(cMsg, sMsg, glblCmpyCd)) {
            copyFromSMsgDetailOntoCmsgDetail(cMsg, sMsg);
            return;
        }

        // START 2023/07/04 T.Kuroda [QC#61440, ADD]
        if (PRCH_REQ_TP.DISPOSAL.equals(cMsg.prchReqTpCd_SL.getValue())
                && !checkWrnSkip(cMsg, sMsg, glblCmpyCd)) {
            return;
        }
        // END 2023/07/04 T.Kuroda [QC#61440, ADD]

        // Submit PR
        PRCH_REQTMsg tMsg = findPR(cMsg, glblCmpyCd);
        NPZC103001PMsg pMsg = null;
        if (tMsg != null) {
            // update
            // optimistic lock
            if (!tryLock(cMsg, sMsg, tMsg)) {
                // cannot lock
                return;
            }
            pMsg = createNPZC103001PMsgForSubmitUpdate(sMsg, glblCmpyCd);
        } else {
            cMsg.setMessageInfo(NPAM0006E);
            return;
        }

        // call api
        if (executeNPZC1030(cMsg, pMsg)) {
            cMsg.setMessageInfo(ZZM8100I);
        }
    }

    /**
     * Upload
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     */
    public static void upload(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {

        copyFromCmsgOntoSmsg(cMsg, sMsg, false);

        int checkedCount = getCheckedCount(cMsg, sMsg);
        if (checkedCount > 1) {
            cMsg.setMessageInfo(NPAM1239E);
            return;
        }

        int checkedConfigPos = 0;
        int checkedConfigItemLastPos = 0;
        if (checkedCount != 0) {
            checkedConfigPos = getCheckedConfigPos(sMsg);
            if (checkedConfigPos == -1) {
                cMsg.setMessageInfo(NWAM0682E);
                return;
            }
            checkedConfigItemLastPos = getCheckedConfigItemLastPos(sMsg);
        }

        /******************************************/
        /****** Validate File for Upload ******/
        /******************************************/
        if (!validateUploadFileForAllLines(cMsg, sMsg, glblCmpyCd)) {
            copyFromSMsgOntoCmsg(cMsg, sMsg);
            cMsg.xxFileData.deleteTempFile();
            return;
        }

        /*****************************/
        /****** Upload File ******/
        /*****************************/
        String path = cMsg.xxFileData.getTempFilePath();
        NPBL0020F00FMsg fMsg = new NPBL0020F00FMsg();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
        EZDCSVInFile mappedFile = new EZDCSVInFile(path, fMsg, option);
        try {
            int header = mappedFile.read();
            if (header == 1) {
                cMsg.setMessageInfo(ZYEM0004E);
                return;
            }
            int loopCnt = 0;
            while (mappedFile.read() != 1) {

                ZYPEZDItemValueSetter.setValue(sMsg.mdseCd_U1, toUpperCaseForEZDFStringItem(fMsg.mdseCd_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.prchReqDispQty_U1, fMsg.prchReqDispQty_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.srcRtlWhCd_U1, toUpperCaseForEZDFStringItem(fMsg.srcRtlWhCd_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.srcRtlSwhCd_U1, toUpperCaseForEZDFStringItem(fMsg.srcRtlSwhCd_A1));
                if (PRCH_REQ_TP.WH_TRANSFER .equals(sMsg.prchReqTpCd_SL.getValue()) //
                        || PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue()) //
                        || PRCH_REQ_TP.KITTING.equals(cMsg.prchReqTpCd_SL.getValue())) {

                    ZYPEZDItemValueSetter.setValue(sMsg.destRtlWhCd_U1, toUpperCaseForEZDFStringItem(fMsg.destRtlWhCd_A1));
                    if (PRCH_REQ_TP.WH_TRANSFER .equals(sMsg.prchReqTpCd_SL.getValue()) //
                            || PRCH_REQ_TP.KITTING.equals(cMsg.prchReqTpCd_SL.getValue())) {
                        ZYPEZDItemValueSetter.setValue(sMsg.destRtlSwhCd_U1, toUpperCaseForEZDFStringItem(fMsg.destRtlSwhCd_A1));
                    }
                }
                // QC#22467
                if (PRCH_REQ_TP.DISPOSAL.equals(sMsg.prchReqTpCd_SL.getValue()) //
                        || PRCH_REQ_TP.VENDOR_RETURN.equals(sMsg.prchReqTpCd_SL.getValue()) //
                        || PRCH_REQ_TP.REFURBISHING.equals(cMsg.prchReqTpCd_SL.getValue()) //
                        || PRCH_REQ_TP.EXPENSE_ORDER.equals(cMsg.prchReqTpCd_SL.getValue()) //
                        || PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue()) ) {

                    ZYPEZDItemValueSetter.setValue(sMsg.shipToCustCd_EO, toUpperCaseForEZDFStringItem(fMsg.shipToCustCd_A1));
                    ZYPEZDItemValueSetter.setValue(sMsg.shipToLocNm_EO, toUpperCaseForEZDFStringItem(fMsg.shipToLocNm_A1));
                }
                ZYPEZDItemValueSetter.setValue(sMsg.prchReqLineCmntTxt_U1, toUpperCaseForEZDFStringItem(fMsg.prchReqLineCmntTxt_A1));
                // QC#22467
                ZYPEZDItemValueSetter.setValue(sMsg.stkStsCd_U1, toUpperCaseForEZDFStringItem(fMsg.stkStsCd_A1));
                // START 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
                if (NPBL0020CommonLogic.isNeededAccount(cMsg.chrgAcctEdtblFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.xxScrItem50Txt_U1, toUpperCaseForEZDFStringItem(fMsg.xxScrItem50Txt_A1));
                } else {
                    sMsg.xxScrItem50Txt_U1.clear();
                }
                // END 2018/04/03 S.Katsuma [QC#23521,25063,MOD]

                if (!checkLocationForUpload(cMsg, sMsg, glblCmpyCd, loopCnt + 1)) {
                    copyFromSMsgOntoCmsg(cMsg, sMsg);
                    return;
                }

                // header input check
                if (loopCnt == 0) {
                    if (PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue()) //
                            || PRCH_REQ_TP.KITTING.equals(sMsg.prchReqTpCd_SL.getValue()) //
                            || PRCH_REQ_TP.REFURBISHING.equals(cMsg.prchReqTpCd_SL.getValue()) //
                            || PRCH_REQ_TP.VENDOR_RETURN.equals(cMsg.prchReqTpCd_SL.getValue())) {

                        if (!ZYPCommonFunc.hasValue(sMsg.srcRtlWhCd)) {
                            ZYPEZDItemValueSetter.setValue(sMsg.srcRtlWhCd, sMsg.srcRtlWhCd_U1);
                        }
                    }

                    if (PRCH_REQ_TP.REFURBISHING.equals(cMsg.prchReqTpCd_SL.getValue()) //
                            || PRCH_REQ_TP.VENDOR_RETURN.equals(cMsg.prchReqTpCd_SL.getValue())) {

                        if (!ZYPCommonFunc.hasValue(sMsg.srcRtlSwhCd)) {
                            ZYPEZDItemValueSetter.setValue(sMsg.srcRtlSwhCd, sMsg.srcRtlSwhCd_U1);
                        }
                    }
                }

                if (checkedCount == 0) {
                    // first line
                    int newLineIndex = sMsg.A.getValidCount();
                    int lastReqLineNum = 0;
                    if (newLineIndex != 0) {
                        lastReqLineNum = Integer.valueOf(sMsg.A.no(newLineIndex - 1).prchReqLineNum_A1.getValue());
                    }
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).prchReqLineNum_A1, String.format("%03d", lastReqLineNum + 1));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).prchReqLineSubNum_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).xxScrItem20Txt_A1, String.format("%03d.%d", lastReqLineNum + 1, 0));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).prchReqLineTpCd_A1, sMsg.defPrchReqLineTpCd);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).srcRtlWhCd_A1, sMsg.srcRtlWhCd_U1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).rtlWhNm_A1, sMsg.rtlWhNm_U1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).srcRtlSwhCd_A1, sMsg.srcRtlSwhCd_U1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).destRtlWhCd_A1, sMsg.destRtlWhCd_U1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).rtlWhNm_A2, sMsg.rtlWhNm_U2);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).destRtlSwhCd_A1, sMsg.destRtlSwhCd_U1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).vndCd_A1, sMsg.vndCd);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).dplyVndNm_A1, sMsg.dplyVndNm);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).shipToCustCd_E1, sMsg.shipToCustCd_EO);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).shipToLocNm_E1, sMsg.shipToLocNm_EO);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).prchReqLineStsDescTxt_A1, sMsg.prchReqLineStsDescTxt);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).mdseCd_A1, sMsg.mdseCd_U1.getValue());
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).prchReqDispQty_A1, sMsg.prchReqDispQty_U1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).prchReqLineCmntTxt_A1, sMsg.prchReqLineCmntTxt_U1.getValue());
                    // QC#22467
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).fromStkStsCd_A1, sMsg.stkStsCd_U1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newLineIndex).xxScrItem50Txt_A1, sMsg.xxScrItem50Txt_U1);
                    if (!ZYPCommonFunc.hasValue(sMsg.A.no(newLineIndex).xxScrItem50Txt_A1)) {
                        // QC#57052. Mod QC#57351
//                        ZYPEZDItemValueSetter.setValue(cMsg.xxNum, new BigDecimal(newLineIndex));
                        NPBL0020CommonLogic.getAccountForce(cMsg, sMsg, glblCmpyCd, newLineIndex, false, false);
                    }

                    sMsg.A.setValidCount(newLineIndex + 1);

                } else {
                    // other line
                    for (int i = sMsg.A.getValidCount() - 1; i > checkedConfigItemLastPos; i--) {
                        EZDMsg.copy(sMsg.A.no(i), null, sMsg.A.no(i + 1), null);
                    }
                    sMsg.A.no(checkedConfigItemLastPos + 1).clear();

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).prchReqLineNum_A1, sMsg.A.no(checkedConfigPos).prchReqLineNum_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).prchReqLineSubNum_A1, sMsg.A.no(checkedConfigItemLastPos).prchReqLineSubNum_A1.getValue().add(BigDecimal.ONE));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).xxScrItem20Txt_A1, String.format("%s.%d", sMsg.A.no(checkedConfigItemLastPos + 1).prchReqLineNum_A1.getValue(), sMsg.A
                            .no(checkedConfigItemLastPos + 1).prchReqLineSubNum_A1.getValueInt()));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).configTpCd_A1, sMsg.A.no(checkedConfigPos).configTpCd_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).configTpDescTxt_A1, sMsg.A.no(checkedConfigPos).configTpDescTxt_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).prchReqLineTpCd_A1, sMsg.defPrchReqLineTpCd);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).svcConfigMstrPk_A1, sMsg.A.no(checkedConfigPos).svcConfigMstrPk_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).srcRtlWhCd_A1, sMsg.A.no(checkedConfigPos).srcRtlWhCd_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).rtlWhNm_A1, sMsg.A.no(checkedConfigPos).rtlWhNm_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).rtlWhCatgCd_A1, sMsg.A.no(checkedConfigPos).rtlWhCatgCd_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).srcRtlSwhCd_A1, sMsg.A.no(checkedConfigPos).srcRtlSwhCd_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).destRtlWhCd_A1, sMsg.A.no(checkedConfigPos).destRtlWhCd_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).rtlWhNm_A2, sMsg.A.no(checkedConfigPos).rtlWhNm_A2);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).rtlWhCatgCd_A2, sMsg.A.no(checkedConfigPos).rtlWhCatgCd_A2);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).destRtlSwhCd_A1, sMsg.A.no(checkedConfigPos).destRtlSwhCd_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).vndCd_A1, cMsg.vndCd);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).dplyVndNm_A1, cMsg.dplyVndNm);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).shipToCustCd_E1, sMsg.A.no(checkedConfigPos).shipToCustCd_E1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).shipToLocNm_E1, sMsg.A.no(checkedConfigPos).shipToLocNm_E1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).prchReqLineStsDescTxt_A1, sMsg.prchReqLineStsDescTxt);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).mdseCd_A1, sMsg.mdseCd_U1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).prchReqDispQty_A1, sMsg.prchReqDispQty_U1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).prchReqLineCmntTxt_A1, sMsg.prchReqLineCmntTxt_U1);
                    // QC#22467
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).fromStkStsCd_A1, sMsg.stkStsCd_U1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(checkedConfigItemLastPos + 1).xxScrItem50Txt_A1, sMsg.xxScrItem50Txt_U1);
                    if (!ZYPCommonFunc.hasValue(sMsg.A.no(checkedConfigItemLastPos + 1).xxScrItem50Txt_A1)) {
                        NPBL0020CommonLogic.getAccountForce(cMsg, sMsg, glblCmpyCd, checkedConfigItemLastPos + 1, false, false);
                    }
                    checkedConfigItemLastPos++;

                    sMsg.A.setValidCount(sMsg.A.getValidCount() + 1);
                }
                sMsg.mdseCd_U1.clear();
                sMsg.prchReqDispQty_U1.clear();
                sMsg.srcRtlWhCd_U1.clear();
                sMsg.rtlWhNm_U1.clear();
                sMsg.srcRtlSwhCd_U1.clear();
                sMsg.destRtlWhCd_U1.clear();
                sMsg.rtlWhNm_U2.clear();
                sMsg.destRtlSwhCd_U1.clear();
                sMsg.prchReqLineCmntTxt_U1.clear();
                sMsg.stkStsCd_U1.clear();
                sMsg.xxScrItem50Txt_U1.clear();

                loopCnt++;
            }

            copyFromSMsgOntoCmsg(cMsg, sMsg);

            setUpdateScreenValue(cMsg, sMsg, glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), true);

            allMultipleCheckSetCMsg(sMsg, cMsg);

        } finally {
            mappedFile.close();
            cMsg.xxFileData.deleteTempFile();
        }
    }

    private static String toUpperCaseForEZDFStringItem(EZDFStringItem stringItem) {
        if (ZYPCommonFunc.hasValue(stringItem)) {
            return stringItem.getValue().toUpperCase();
        }
        return "";
    }

    private static boolean validateUploadFileForAllLines(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {

        String path = cMsg.xxFileData.getTempFilePath();
        NPBL0020F00FMsg fMsg = new NPBL0020F00FMsg();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;

        EZDCSVInFile mappedFile = new EZDCSVInFile(path, fMsg, option);
        try {
            int header = mappedFile.read();
            if (header == 1) {
                cMsg.setMessageInfo(ZYEM0004E);
                return false;
            }

            int status = -1;
            int uploadFileLineNum = 0;

            while ((status = mappedFile.read()) != 1) {
                uploadFileLineNum++;

                if (!validateUploadFileForEachLine(cMsg, status, fMsg, uploadFileLineNum)) {
                    return false;
                }

                ZYPEZDItemValueSetter.setValue(sMsg.srcRtlWhCd_U1, toUpperCaseForEZDFStringItem(fMsg.srcRtlWhCd_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.srcRtlSwhCd_U1, toUpperCaseForEZDFStringItem(fMsg.srcRtlSwhCd_A1));
                if (PRCH_REQ_TP.WH_TRANSFER .equals(sMsg.prchReqTpCd_SL.getValue()) //
                        || PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue()) //
                        || PRCH_REQ_TP.KITTING.equals(cMsg.prchReqTpCd_SL.getValue())) {

                    ZYPEZDItemValueSetter.setValue(sMsg.destRtlWhCd_U1, toUpperCaseForEZDFStringItem(fMsg.destRtlWhCd_A1));
                    if (PRCH_REQ_TP.WH_TRANSFER .equals(sMsg.prchReqTpCd_SL.getValue()) //
                            || PRCH_REQ_TP.KITTING.equals(cMsg.prchReqTpCd_SL.getValue())) {
                        ZYPEZDItemValueSetter.setValue(sMsg.destRtlSwhCd_U1, toUpperCaseForEZDFStringItem(fMsg.destRtlSwhCd_A1));
                    }
                }
                // QC#22467
                if (PRCH_REQ_TP.DISPOSAL.equals(sMsg.prchReqTpCd_SL.getValue()) //
                        || PRCH_REQ_TP.VENDOR_RETURN.equals(sMsg.prchReqTpCd_SL.getValue()) //
                        || PRCH_REQ_TP.REFURBISHING.equals(cMsg.prchReqTpCd_SL.getValue()) //
                        || PRCH_REQ_TP.EXPENSE_ORDER.equals(cMsg.prchReqTpCd_SL.getValue()) //
                        || PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue()) ) {

                    ZYPEZDItemValueSetter.setValue(sMsg.shipToCustCd_EO, toUpperCaseForEZDFStringItem(fMsg.shipToCustCd_A1));
                    ZYPEZDItemValueSetter.setValue(sMsg.shipToLocNm_EO, toUpperCaseForEZDFStringItem(fMsg.shipToLocNm_A1));
                }
                ZYPEZDItemValueSetter.setValue(sMsg.stkStsCd_U1, toUpperCaseForEZDFStringItem(fMsg.stkStsCd_A1));
                // START 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
                if (NPBL0020CommonLogic.isNeededAccount(cMsg.chrgAcctEdtblFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.xxScrItem50Txt_U1, toUpperCaseForEZDFStringItem(fMsg.xxScrItem50Txt_A1));
                }
                // END 2018/04/03 S.Katsuma [QC#23521,25063,MOD]

                if (!checkLocationForUpload(cMsg, sMsg, glblCmpyCd, uploadFileLineNum)) {
                    return false;
                }
                sMsg.srcRtlWhCd_U1.clear();
                sMsg.rtlWhNm_U1.clear();
                sMsg.srcRtlSwhCd_U1.clear();
                sMsg.destRtlWhCd_U1.clear();
                sMsg.rtlWhNm_U2.clear();
                sMsg.destRtlSwhCd_U1.clear();
                sMsg.destRtlSwhCd_U1.clear();
                sMsg.stkStsCd_U1.clear();
                sMsg.xxScrItem50Txt_U1.clear();
            }

            if (sMsg.A.getValidCount() + uploadFileLineNum > sMsg.A.length()) {
                cMsg.setMessageInfo(NPAM0077E, new String[] {String.valueOf(sMsg.A.length()) });
                copyFromSMsgOntoCmsg(cMsg, sMsg);
                return false;
            }

        } finally {
            mappedFile.close();
            sMsg.srcRtlWhCd_U1.clear();
            sMsg.rtlWhNm_U1.clear();
            sMsg.srcRtlSwhCd_U1.clear();
            sMsg.destRtlWhCd_U1.clear();
            sMsg.rtlWhNm_U2.clear();
            sMsg.destRtlSwhCd_U1.clear();
            sMsg.stkStsCd_U1.clear();
            sMsg.xxScrItem50Txt_U1.clear();
        }
        return true;
    }

    private static boolean validateUploadFileForEachLine(NPBL0020CMsg cMsg, int status, NPBL0020F00FMsg fMsg, int uploadFileLineNum) {

        if (status == IDX_1000) {
            cMsg.setMessageInfo(NMAM0052E, new String[] {UPLOAD_DATA_FORMAT });

            return false;
        }

        int errCol1 = status - 1000;
        int errCol2 = status - 2000;

        // Item#
        if (errCol1 == 1 || errCol2 == 1) {
            cMsg.setMessageInfo(NMAM8191E, new String[] {"Item# (Line# " + String.valueOf(uploadFileLineNum) + ")" });
            return false;
            // Transfer Qty
        } else if (errCol1 == 2 || errCol2 == 2) {
            cMsg.setMessageInfo(NMAM8191E, new String[] {"Transfer Qty (Line# " + String.valueOf(uploadFileLineNum) + ")" });
            return false;
            // Source WH Code
        } else if (errCol1 == 3 || errCol2 == 3) {
            cMsg.setMessageInfo(NMAM8191E, new String[] {"Source WH Code (Line# " + String.valueOf(uploadFileLineNum) + ")" });
            return false;
            // Source SWH Code
        } else if (errCol1 == 4 || errCol2 == 4) {
            cMsg.setMessageInfo(NMAM8191E, new String[] {"Source SWH Code (Line# " + String.valueOf(uploadFileLineNum) + ")" });
            return false;
            // Stock Status Code
        } else if (errCol1 == 5 || errCol2 == 5) {
            cMsg.setMessageInfo(NMAM8191E, new String[] {"Stock Status Code (Line# " + String.valueOf(uploadFileLineNum) + ")" });
            return false;        
            // Destination WH Code
        } else if (errCol1 == 6 || errCol2 == 6) {
            cMsg.setMessageInfo(NMAM8191E, new String[] {"Destination WH Code (Line# " + String.valueOf(uploadFileLineNum) + ")" });
            return false;
            // Destination SWH Code
        } else if (errCol1 == 7 || errCol2 == 7) {
            cMsg.setMessageInfo(NMAM8191E, new String[] {"Destination SWH Code (Line# " + String.valueOf(uploadFileLineNum) + ")" });
            return false;
            // Ship To Customer Code
        } else if (errCol1 == 8 || errCol2 == 8) {
            cMsg.setMessageInfo(NMAM8191E, new String[] {"Ship To Customer Code (Line# " + String.valueOf(uploadFileLineNum) + ")" });
            return false;
            // Ship To Customer Name
        } else if (errCol1 == 9 || errCol2 == 9) {
            cMsg.setMessageInfo(NMAM8191E, new String[] {"Ship To Customer Name (Line# " + String.valueOf(uploadFileLineNum) + ")" });
            return false;
            // Charge Account
        } else if (errCol1 == 11 || errCol2 == 11) {
            // START 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
            if (NPBL0020CommonLogic.isNeededAccount(cMsg.chrgAcctEdtblFlg.getValue())) {
                cMsg.setMessageInfo(NMAM8191E, new String[] {"Charge Account (Line# " + String.valueOf(uploadFileLineNum) + ")" });
                return false;
            }
            // END 2018/04/03 S.Katsuma [QC#23521,25063,MOD]
            // Special Instructions
        } else if (errCol1 == 11 || errCol2 == 11) {
            cMsg.setMessageInfo(NMAM8191E, new String[] {"Special Instructions (Line# " + String.valueOf(uploadFileLineNum) + ")" });
            return false;
        }

        if (!ZYPCommonFunc.hasValue(fMsg.mdseCd_A1)) {
            cMsg.setMessageInfo(ZZZM9025E, new String[] {"Item# (Line# " + String.valueOf(uploadFileLineNum) + ")" });
            return false;
        }
        if (!ZYPCommonFunc.hasValue(fMsg.prchReqDispQty_A1)) {
            cMsg.setMessageInfo(ZZZM9025E, new String[] {"Transfer Qty (Line# " + String.valueOf(uploadFileLineNum) + ")" });
            return false;
        }
        BigDecimal prchReqDispQty = fMsg.prchReqDispQty_A1.getValue();
        if (BigDecimal.valueOf(0).compareTo(prchReqDispQty) >= 0) {
            cMsg.setMessageInfo(NPAM0046E, new String[] {"Transfer Qty (Line# " + String.valueOf(uploadFileLineNum) + ")" });
            return false;

        } else if (prchReqDispQty.precision() - prchReqDispQty.scale() > 10) {
            cMsg.setMessageInfo(NMAM8191E, new String[] {"Transfer Qty (Line# " + String.valueOf(uploadFileLineNum) + ")" });
            return false;

        } else if (prchReqDispQty.scale() > 0) {
            cMsg.setMessageInfo(NMAM8191E, new String[] {"Transfer Qty (Line# " + String.valueOf(uploadFileLineNum) + ")" });
            return false;
        }

        return true;
    }

    /**
     * Template Download
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     */
    public static void templateDownload(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {

        NPBL0020F00FMsg fMsg = new NPBL0020F00FMsg();

        cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), TMPL_FILE_EXTENSION);
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        csvOutFile.writeHeader(CSV_HEADER);
        csvOutFile.close();

    }

    /**
     * Header Cancel
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     */
    public static void headerCancel(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {

        if (PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue()) && ZYPConstant.FLG_ON_Y.equals(cMsg.prchReqApvlFlg.getValue())) {
            cMsg.setMessageInfo(NPAM0008E);
            return;
        }

        if (!(ZYPCommonFunc.hasValue(cMsg.xxBtnFlg)) || ZYPConstant.FLG_OFF_N.equals(cMsg.xxBtnFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxBtnFlg, ZYPConstant.FLG_ON_Y);
            cMsg.setMessageInfo(NPAM1359I);
            return;
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.xxBtnFlg, ZYPConstant.FLG_OFF_N);
        }

        boolean prReleasedFlag = false;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (PRCH_REQ_REL_STS.COMPLEATED.equals(sMsg.A.no(i).prchReqRelStsCd_A1.getValue())) {
                prReleasedFlag = true;
                break;
            }
        }
        if (prReleasedFlag) {
            PRCH_REQ_TPTMsg prchReqTpTMsg = findPrReqTpTMsg(glblCmpyCd, cMsg.prchReqTpCd_SL.getValue());
            String trxSrcTpCd = prchReqTpTMsg.trxSrcTpCd.getValue();
            if (TRX_SRC_TP.WHOLE_SALES.equals(trxSrcTpCd)) {
                if (NPBL0020Query.getInstance().checkExistenceForCPO(glblCmpyCd, sMsg.prchReqNum.getValue())) {
                    cMsg.setMessageInfo(NPAM1532E);
                    return;
                }
            } else if (TRX_SRC_TP.MOVEMENT.equals(trxSrcTpCd)) {
                if (NPBL0020Query.getInstance().checkExistenceForInventoryOrder(glblCmpyCd, sMsg.prchReqNum.getValue())) {
                    cMsg.setMessageInfo(NPAM1532E);
                    return;
                }
            } else if (TRX_SRC_TP.VENDOR_RETURN.equals(trxSrcTpCd)) {
                if (NPBL0020Query.getInstance().checkExistenceForVendorReturn(glblCmpyCd, sMsg.prchReqNum.getValue())) {
                    cMsg.setMessageInfo(NPAM1532E);
                    return;
                }
            } else {
                if (PRCH_REQ_TP.KITTING.equals(cMsg.prchReqTpCd_SL.getValue())) {
                	// QC#56543 2020/05/01 Start
                    //if (NPBL0020Query.getInstance().checkExistenceForWorkOrder(glblCmpyCd, sMsg.prchReqNum.getValue())) {
                    //    cMsg.setMessageInfo(NPAM1532E);
                    //    return;
                    //}
                    // QC#56543 2020/05/01 End
                }
            }
        }

        PRCH_REQTMsg tMsg = findPR(cMsg, glblCmpyCd);
        if (tMsg != null) {
            // update
            // optimistic lock
            if (!tryLock(cMsg, sMsg, tMsg)) {
                // cannot lock
                return;
            }
        } else {
            cMsg.setMessageInfo(NPAM0076E, new String[] {MSG_VALUE_PR });
            return;
        }

        NPZC103001PMsg pMsg = new NPZC103001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC103001Constant.MODE_CANCEL);
        ZYPEZDItemValueSetter.setValue(pMsg.eventId, NPZC103001Constant.EVENT_SUBMIT);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqNum, sMsg.prchReqNum);

        // QC#57250
        int pMsgCount = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (PRCH_REQ_LINE_STS.CANCELLED.equals(sMsg.A.no(i).prchReqLineStsCd_A1.getValue())) {
                // Cancel Line
                continue;
            }
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).openStsFlg_A2.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(pMsgCount).prchReqLineNum, sMsg.A.no(i).prchReqLineNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(pMsgCount).prchReqLineSubNum, sMsg.A.no(i).prchReqLineSubNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(pMsgCount).prchReqLineCmntTxt, sMsg.A.no(i).prchReqLineCmntTxt_A1);
                pMsgCount++;
            }
        }
        pMsg.prchReqInfo.setValidCount(pMsgCount);

        // call api
        if (executeNPZC1030(cMsg, pMsg)) {
            cMsg.setMessageInfo(ZZM8100I);
        }

    }

    /**
     * Find PRCH_REQ_TP TMsg by primary key.
     * @param glblCmpyCd String
     * @param prchReqTpCd String
     * @return PRCH_REQ_TPTMsg
     */
    private static PRCH_REQ_TPTMsg findPrReqTpTMsg(String glblCmpyCd, String prchReqTpCd) {

        PRCH_REQ_TPTMsg inMsg = new PRCH_REQ_TPTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.prchReqTpCd, prchReqTpCd);

        PRCH_REQ_TPTMsg outMsg = (PRCH_REQ_TPTMsg) EZDTBLAccessor.findByKey(inMsg);
        return outMsg;
    }

    /**
     * Line Cancel. QC#56867 Mod
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @param isLineCancel boolean
     */
    public static void lineCancelOrClose(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd, boolean isLineCancel) {

        if (cMsg.A.getValidCount() == 0) {
            cMsg.setMessageInfo(NPAM0049E);
            return;
        }

        if (PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue()) && ZYPConstant.FLG_ON_Y.equals(cMsg.prchReqApvlFlg.getValue())) {
            cMsg.setMessageInfo(NPAM0008E);
            return;
        }

        if (!(ZYPCommonFunc.hasValue(cMsg.xxBtnFlg)) || ZYPConstant.FLG_OFF_N.equals(cMsg.xxBtnFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxBtnFlg, ZYPConstant.FLG_ON_Y);
            // QC#56867 Mod
            if (isLineCancel) {
                cMsg.setMessageInfo(NPAM1359I);
            } else {
                cMsg.setMessageInfo(NPAM1598I);
            }
            return;
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.xxBtnFlg, ZYPConstant.FLG_OFF_N);
        }

        copyFromCmsgOntoSmsg(cMsg, sMsg, false);

        boolean inNewLine = false;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if ((isChecked(cMsg.A.no(i))) && (isNewLine(cMsg.A.no(i)))) {
                inNewLine = true;
                break;
            }
        }

        if (inNewLine) {
            sMsg.xxNum.setValue(1);
            int cancelCount = 0;
            List<NPBL0020_ASMsg> valueList = new ArrayList<NPBL0020_ASMsg>();
            // V0.5
            String prchReqLineNum = "000";
            int prchReqLineSubNum = 0;
            // START 2018/06/01 S.Katsuma [QC#26234,MOD]
            boolean configPrntChk = false;
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                if (isNewLine(sMsg.A.no(i))) {
                    // Add Line
                    if (isConfigParentDetail(sMsg.A.no(i))) {
                        if (isChecked(sMsg.A.no(i))) {
                            configPrntChk = true;

                            prchReqLineNum = sMsg.A.no(i).prchReqLineNum_A1.getValue();
                            prchReqLineSubNum = sMsg.A.no(i).prchReqLineSubNum_A1.getValueInt();
                            cancelCount++;
                            sMsg.A.no(i).xxChkBox_A1.clear();
                        } else {
                            configPrntChk = false;

                            NPBL0020_ASMsg asMsg = new NPBL0020_ASMsg();
                            EZDMsg.copy(sMsg.A.no(i), null, asMsg, null);
                            valueList.add(asMsg);
                        }
                    } else {
                        if (isChecked(sMsg.A.no(i))) {
                            // V0.5
                            prchReqLineNum = sMsg.A.no(i).prchReqLineNum_A1.getValue();
                            prchReqLineSubNum = sMsg.A.no(i).prchReqLineSubNum_A1.getValueInt();
                            cancelCount++;
                            sMsg.A.no(i).xxChkBox_A1.clear();
                        } else if (prchReqLineNum.equals(sMsg.A.no(i).prchReqLineNum_A1.getValue()) && prchReqLineSubNum < sMsg.A.no(i).prchReqLineSubNum_A1.getValueInt() && configPrntChk) {
                            cancelCount++;
                            sMsg.A.no(i).xxChkBox_A1.clear();
                        } else {
                            NPBL0020_ASMsg asMsg = new NPBL0020_ASMsg();
                            EZDMsg.copy(sMsg.A.no(i), null, asMsg, null);
                            valueList.add(asMsg);
                        }
                    }
                }
            }

            if (0 < valueList.size()) {
                int num = 0;
                for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                    if (!isNewLine(sMsg.A.no(i))) {
                        int temp = Integer.valueOf(sMsg.A.no(i).prchReqLineNum_A1.getValue());
                        if (num < temp) {
                            num = temp;
                        }
                    } else {
                        sMsg.A.no(i).xxChkBox_A1.clear();
                    }
                }
                int subLineNum = 0;
                String lastPrchReqLineNum = "";
                for (int i = 0, j = 0; (i < sMsg.A.getValidCount()) && (j < valueList.size()); i++) {
                    if (isNewLine(sMsg.A.no(i))) {
                        num++;
                        if (valueList.get(j).prchReqLineSubNum_A1.getValueInt() > 0) {
                            num--;
                        }

                        if (!lastPrchReqLineNum.equals(valueList.get(j).prchReqLineNum_A1.getValue())) {
                            lastPrchReqLineNum = valueList.get(j).prchReqLineNum_A1.getValue();
                            subLineNum = 0;
                        }

                        valueList.get(j).prchReqLineNum_A1.setValue(String.format("%03d", num));
                        valueList.get(j).prchReqLineSubNum_A1.setValue(new BigDecimal(subLineNum));
                        valueList.get(j).xxScrItem20Txt_A1.setValue(String.format("%03d.%s", num, String.valueOf(subLineNum++)));
                        EZDMsg.copy(valueList.get(j), null, sMsg.A.no(i), null);
                        j++;
                    }
                }
            }
            // END 2018/06/01 S.Katsuma [QC#26234,MOD]

            sMsg.A.setValidCount(sMsg.A.getValidCount() - cancelCount);

            setPagePos(cMsg, sMsg);

            if (sMsg.A.getValidCount() == 1) {
                ZYPEZDItemValueSetter.setValue(sMsg.srcRtlWhCd, sMsg.A.no(0).srcRtlWhCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.srcRtlSwhCd, sMsg.A.no(0).srcRtlSwhCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.rtlWhNm_SW, sMsg.A.no(0).rtlWhNm_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.destRtlWhCd, sMsg.A.no(0).destRtlWhCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.destRtlSwhCd, sMsg.A.no(0).destRtlSwhCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.rtlWhNm_DW, sMsg.A.no(0).rtlWhNm_A2);
                ZYPEZDItemValueSetter.setValue(sMsg.vndCd, sMsg.A.no(0).vndCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.dplyVndNm, sMsg.A.no(0).dplyVndNm_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.shipToCustCd_EO, sMsg.A.no(0).shipToCustCd_E1);
                ZYPEZDItemValueSetter.setValue(sMsg.shipToLocNm_EO, sMsg.A.no(0).shipToLocNm_E1);
            } else {
                allMultipleCheck(sMsg, glblCmpyCd);
            }

            setUpdateScreenValue(cMsg, sMsg, glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), true);

            copyFromSMsgOntoCmsg(cMsg, sMsg);

            setUpdateScreenValue(cMsg, sMsg, glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd), false);

            return;
        }
        // QC#56867 Mod Start
        // After Save / Submit Process.
        sMsg.xxNum.clear();
        // QC#56867

        // Get CheckBox ON Number
        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A1", "Y");

        boolean isChecked = false;
        String prchReqLineNum = "000";
        int prchReqLineSubNum = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (isParentDetail(sMsg.A.no(i))) {
                if (isChecked(sMsg.A.no(i))) {
                    prchReqLineNum = sMsg.A.no(i).prchReqLineNum_A1.getValue();
                    prchReqLineSubNum = sMsg.A.no(i).prchReqLineSubNum_A1.getValueInt();
                    isChecked = true;
                } else if (prchReqLineNum.equals(sMsg.A.no(i).prchReqLineNum_A1.getValue()) && prchReqLineSubNum < sMsg.A.no(i).prchReqLineSubNum_A1.getValueInt()) {
                    isChecked = true;
                } else {
                    isChecked = false;
                }
            }
            if (isChecked) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_A1, ZYPConstant.FLG_ON_Y);
            }
        }

        selectRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A1", "Y");

        boolean isError = checkLineCancelOrClose(glblCmpyCd, cMsg, sMsg, selectRows, isLineCancel);

        if (isError) {
            copyFromSMsgDetailOntoCmsgDetail(cMsg, sMsg);
            return;
        }

        PRCH_REQTMsg tMsg = findPR(cMsg, glblCmpyCd);
        if (tMsg != null) {
            // update
            // optimistic lock
            if (!tryLock(cMsg, sMsg, tMsg)) {
                // cannot lock
                return;
            }
        } else {
            cMsg.setMessageInfo(NPAM0076E, new String[] {MSG_VALUE_PR });
            return;
        }

        NPZC103001PMsg pMsg = createNPZC103001PMsgForLineCancelOrClose(sMsg, glblCmpyCd, isLineCancel);

        if (executeNPZC1030(cMsg, pMsg)) {
            if (isLineCancel) {
                cMsg.setMessageInfo(ZZZM9003I, new String[] {"LineCancel" });
            } else {
                cMsg.setMessageInfo(ZZZM9003I, new String[] {"LineClose" });
            }
        }
        // QC#56867 Mod End

    }

    /**
     * @param msg NPBL0020_ASMsg
     * @return true if given ASMsg is not saved on DB.
     */
    private static boolean isNewLine(NPBL0020_ASMsg msg) {
        return !ZYPCommonFunc.hasValue(msg.prchReqLineStsCd_A1);
    }

    /**
     * @param msg NPBL0020_ACMsg
     * @return true if given ASMsg is not saved on DB.
     */
    private static boolean isNewLine(NPBL0020_ACMsg msg) {
        return !ZYPCommonFunc.hasValue(msg.prchReqLineStsCd_A1);
    }

    /**
     * @param acMsg NPBL0020_ACMsg
     * @return true if given line is checked
     */
    private static boolean isChecked(NPBL0020_ACMsg acMsg) {
        return ZYPConstant.CHKBOX_ON_Y.equals(acMsg.xxChkBox_A1.getValue());
    }

    /**
     * @param asMsg NPBL0020_ASMsg
     * @return true if given line is checked
     */
    private static boolean isChecked(NPBL0020_ASMsg asMsg) {
        return ZYPConstant.CHKBOX_ON_Y.equals(asMsg.xxChkBox_A1.getValue());
    }

    private static boolean isParentDetail(NPBL0020_ASMsg aMsg) {
        return BigDecimal.ZERO.equals(aMsg.prchReqLineSubNum_A1.getValue());
    }

    /**
     * Creates new pMsg. QC#56867 Mod
     * @param sMsg NPBL0020SMsg
     * @param String glblCmpyCd
     * @param isLineCancel boolean
     * @return NPZC103001PMsg
     */
    private static NPZC103001PMsg createNPZC103001PMsgForLineCancelOrClose(NPBL0020SMsg sMsg, String glblCmpyCd, boolean isLineCancel) {

        NPZC103001PMsg pMsg = new NPZC103001PMsg();
        if (isLineCancel) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC103001Constant.MODE_CANCEL);
            ZYPEZDItemValueSetter.setValue(pMsg.eventId, NPZC103001Constant.EVENT_SUBMIT);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC103001Constant.MODE_UPDATE);
            ZYPEZDItemValueSetter.setValue(pMsg.eventId, NPZC103001Constant.EVENT_UPDATE);
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqStsCd, PRCH_REQ_STS.CLOSED);
        }
        
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqNum, sMsg.prchReqNum);
        ZYPEZDItemValueSetter.setValue(pMsg.procDt, ZYPDateUtil.getSalesDate(glblCmpyCd));

        int pMsgCount = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (PRCH_REQ_LINE_STS.CANCELLED.equals(sMsg.A.no(i).prchReqLineStsCd_A1.getValue())) {
                // Cancel Line
                continue;
            }
            if (isChecked(sMsg.A.no(i))) {
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(pMsgCount).prchReqLineNum, sMsg.A.no(i).prchReqLineNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(pMsgCount).prchReqLineSubNum, sMsg.A.no(i).prchReqLineSubNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(pMsgCount).prchReqLineCmntTxt, sMsg.A.no(i).prchReqLineCmntTxt_A1);
                if (!isLineCancel) {
                    if (PRCH_REQ_TP.WH_TRANSFER.equals(sMsg.prchReqTpCd_SL.getValue())) {
                        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(pMsgCount).prchReqLineStsCd, PRCH_REQ_LINE_STS.RECEIVED);
                    } else if (PRCH_REQ_TP.KITTING.equals(sMsg.prchReqTpCd_SL.getValue())) {
                        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(pMsgCount).prchReqLineStsCd, PRCH_REQ_LINE_STS.CLOSED);
                    } else {
                        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(pMsgCount).prchReqLineStsCd, PRCH_REQ_LINE_STS.SHIPPED);
                    }
                }
                pMsgCount++;
            }
        }
        pMsg.prchReqInfo.setValidCount(pMsgCount);
        return pMsg;
    }

    /**
     * Check Item#
     * @param asMsg NPBL0020_ASMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private static boolean checkItemNumber(NPBL0020_ASMsg asMsg, String glblCmpyCd) {

        // QC#17098 Mod.
//        ALL_MDSE_VTMsg inMsg = new ALL_MDSE_VTMsg();
//        inMsg.setSQLID("003");
//        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        inMsg.setConditionValue("mdseCd01", asMsg.mdseCd_A1.getValue());
//
//        ALL_MDSE_VTMsgArray outMsgsTechLoc = (ALL_MDSE_VTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
//        if (0 < outMsgsTechLoc.length()) {
//            ZYPEZDItemValueSetter.setValue(asMsg.shpgSerTakeFlg_A1, outMsgsTechLoc.no(0).shpgSerTakeFlg);
//            return outMsgsTechLoc.no(0);
//        }
//        return null;

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_MDSE_CD, asMsg.mdseCd_A1.getValue());
        ssmParam.put(DB_PARAM_FLG_N, ZYPConstant.FLG_OFF_N);
        ssmParam.put(DB_PARAM_FLG_Y, ZYPConstant.CHKBOX_ON_Y);

        S21SsmEZDResult result = NPBL0020Query.getInstance().checkItemNumber(ssmParam);

        if (result.isCodeNormal()) {

            List<Map<String, String>> itemInfoList = (List<Map<String, String>>) result.getResultObject();

            if (itemInfoList.size() != 0) {

                Map<String, String> itemInfo = itemInfoList.get(0);
                if (ZYPConstant.CHKBOX_ON_Y.equals(itemInfo.get("ORD_TAKE_FLG"))) {

                    asMsg.mdseCd_A1.setErrorInfo(1, NEAM0018E);
                    asMsg.mdseDescShortTxt_A1.clear();
                    return false;

                } else if (ZYPConstant.FLG_OFF_N.equals(itemInfo.get("INVTY_CTRL_FLG"))) {

                    asMsg.mdseCd_A1.setErrorInfo(1, NPAM0084E, null);
                    asMsg.mdseDescShortTxt_A1.clear();
                    return false;

                } else {

                    ZYPEZDItemValueSetter.setValue(asMsg.shpgSerTakeFlg_A1, itemInfo.get("SHPG_SER_TAKE_FLG"));
                    ZYPEZDItemValueSetter.setValue(asMsg.mdseDescShortTxt_A1, itemInfo.get("MDSE_DESC_SHORT_TXT"));
                }
            }

        } else {

            asMsg.mdseCd_A1.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_MDSE_CD });
            asMsg.mdseDescShortTxt_A1.clear();
            return false;

        }

        return true;
    }

    /**
     * Check Item#
     * @param acMsg NPBL0020_ACMsg
     * @param glblCmpyCd String
     */
    public static void checkItemNumberScrn(NPBL0020_ACMsg acMsg, String glblCmpyCd) {

        // QC#17098 Mod.
//        ALL_MDSE_VTMsg inMsg = new ALL_MDSE_VTMsg();
//        inMsg.setSQLID("003");
//        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        inMsg.setConditionValue("mdseCd01", acMsg.mdseCd_A1.getValue());
//
//        ALL_MDSE_VTMsgArray outMsgsTechLoc = (ALL_MDSE_VTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
//        if (0 < outMsgsTechLoc.length()) {
//            ZYPEZDItemValueSetter.setValue(acMsg.shpgSerTakeFlg_A1, outMsgsTechLoc.no(0).shpgSerTakeFlg);
//            return outMsgsTechLoc.no(0);
//        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_MDSE_CD, acMsg.mdseCd_A1.getValue());
        ssmParam.put(DB_PARAM_FLG_N, ZYPConstant.FLG_OFF_N);
        ssmParam.put(DB_PARAM_FLG_Y, ZYPConstant.CHKBOX_ON_Y);

        S21SsmEZDResult result = NPBL0020Query.getInstance().checkItemNumber(ssmParam);

        if (result.isCodeNormal()) {

            List<Map<String, String>> itemInfoList = (List<Map<String, String>>) result.getResultObject();

            if (itemInfoList.size() != 0) {

                Map<String, String> itemInfo = itemInfoList.get(0);
                if (ZYPConstant.CHKBOX_ON_Y.equals(itemInfo.get("ORD_TAKE_FLG"))) {
                    acMsg.mdseCd_A1.setErrorInfo(1, NEAM0018E);
                    acMsg.mdseDescShortTxt_A1.clear();

                } else if (ZYPConstant.FLG_OFF_N.equals(itemInfo.get("INVTY_CTRL_FLG"))) {
                    acMsg.mdseCd_A1.setErrorInfo(1, NPAM0084E, null);
                    acMsg.mdseDescShortTxt_A1.clear();

                } else {
                    ZYPEZDItemValueSetter.setValue(acMsg.shpgSerTakeFlg_A1, itemInfo.get("SHPG_SER_TAKE_FLG"));
                    ZYPEZDItemValueSetter.setValue(acMsg.mdseDescShortTxt_A1, itemInfo.get("MDSE_DESC_SHORT_TXT"));
                }
            }

        } else {

            acMsg.mdseCd_A1.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_MDSE_CD });
            acMsg.mdseDescShortTxt_A1.clear();
        }

    }

    // START 2018/04/03 S.Katsuma [QC#23521,25063,DEL]
//    /**
//     * <pre>
//     * Check Location
//     * </pre>
//     * @param cMsg NPBL0020CMsg
//     * @param sMsg NPBL0020SMsg
//     * @param glblCmpyCd String
//     * @return false for error
//     */
//    private static boolean checkLocation(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {
//
//        // Check Header Source Warehouse
//        // QC#17099 add start
//        if (!SCREEN_CTRL_VALUE_MULTIPLE.equals(sMsg.srcRtlWhCd.getValue())
//          && (PRCH_REQ_TP.VENDOR_RETURN.equals(cMsg.prchReqTpCd_SL.getValue())
//          ||  PRCH_REQ_TP.REFURBISHING.equals(cMsg.prchReqTpCd_SL.getValue()))
//          && !ZYPCommonFunc.hasValue(sMsg.srcRtlWhCd)) {
//            cMsg.srcRtlWhCd.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_SRC_RTL_WH_CD });
//            cMsg.setMessageInfo(ZZZM9025E, new String[] {DISPLAY_NM_SRC_RTL_WH_CD });
//            return false;
//        }
//        // QC#17099 add end
//        if (ZYPCommonFunc.hasValue(sMsg.srcRtlWhCd) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(sMsg.srcRtlWhCd.getValue())) {
//            if (!ZYPCommonFunc.hasValue(sMsg.srcRtlWhCd) && ZYPCommonFunc.hasValue(sMsg.rtlWhNm_SW)) {
//                int count = checkSrcRtlWhFromName(glblCmpyCd, sMsg.rtlWhNm_SW, sMsg.srcRtlWhCd, sMsg.rtlWhCatgCd_SW);
//                if (count == 0) {
//                    cMsg.rtlWhNm_SW.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_SW });
//                    cMsg.setMessageInfo(NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_SW });
//                    return false;
//                } else if (count > 1) {
//                    cMsg.rtlWhNm_SW.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_RTL_WH_NM_SW });
//                    cMsg.setMessageInfo(NPBM0001E, new String[] {DISPLAY_NM_RTL_WH_NM_SW });
//                    return false;
//                }
//            }
//        }
//        if (ZYPCommonFunc.hasValue(sMsg.srcRtlSwhCd) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(sMsg.srcRtlSwhCd.getValue())) {
//            if (!ZYPCommonFunc.hasValue(sMsg.srcRtlSwhCd) && ZYPCommonFunc.hasValue(sMsg.rtlSwhNm_SS)) {
//                int count = checkSrcRtlSwhFromName(sMsg, glblCmpyCd);
//                if (count == 0) {
//                    cMsg.rtlSwhNm_SS.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_SWH_NM_SS });
//                    cMsg.setMessageInfo(NPAM1361E, new String[] {DISPLAY_NM_RTL_SWH_NM_SS });
//                    return false;
//                } else if (count > 1) {
//                    cMsg.rtlSwhNm_SS.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_RTL_SWH_NM_SS });
//                    cMsg.setMessageInfo(NPBM0001E, new String[] {DISPLAY_NM_RTL_SWH_NM_SS });
//                    return false;
//                }
//            }
//        }
//
//        // Check Header Destination Warehouse
//        if (ZYPCommonFunc.hasValue(sMsg.destRtlWhCd) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(sMsg.destRtlWhCd.getValue())) {
//            if (!ZYPCommonFunc.hasValue(sMsg.destRtlWhCd) && ZYPCommonFunc.hasValue(sMsg.rtlWhNm_DW)) {
//                int count = checkDestRtlWhFromName(glblCmpyCd, sMsg.rtlWhNm_DW, sMsg.destRtlWhCd, sMsg.rtlWhCatgCd_DW);
//                if (count == 0) {
//                    cMsg.rtlWhNm_DW.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_DW });
//                    cMsg.setMessageInfo(NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_DW });
//                    return false;
//                } else if (count > 1) {
//                    cMsg.rtlWhNm_DW.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_RTL_WH_NM_DW });
//                    cMsg.setMessageInfo(NPBM0001E, new String[] {DISPLAY_NM_RTL_WH_NM_DW });
//                    return false;
//                }
//            }
//        }
//        if (ZYPCommonFunc.hasValue(sMsg.destRtlSwhCd) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(sMsg.destRtlSwhCd.getValue())) {
//            if (!ZYPCommonFunc.hasValue(sMsg.destRtlSwhCd) && ZYPCommonFunc.hasValue(sMsg.rtlSwhNm_DS)) {
//                int count = checkDestRtlSwhFromName(sMsg, glblCmpyCd);
//                if (count == 0) {
//                    cMsg.rtlSwhNm_DS.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_SWH_NM_DS });
//                    cMsg.setMessageInfo(NPAM1361E, new String[] {DISPLAY_NM_RTL_SWH_NM_DS });
//                    return false;
//                } else if (count > 1) {
//                    cMsg.rtlSwhNm_DS.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_RTL_SWH_NM_DS });
//                    cMsg.setMessageInfo(NPBM0001E, new String[] {DISPLAY_NM_RTL_SWH_NM_DS });
//                    return false;
//                }
//            }
//        }
//        // QC#22481
//        if (!PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue())) {
//            // Location Security Check
//            if (ZYPCommonFunc.hasValue(sMsg.srcRtlWhCd) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(sMsg.srcRtlWhCd.getValue()) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(sMsg.srcRtlSwhCd.getValue())) {
//                NMXC100001EnableWHData result = NMXC100001EnableWH.checkEnableWH(glblCmpyCd, ZYPCommonFunc.concatString(sMsg.srcRtlWhCd.getValue(), "", sMsg.srcRtlSwhCd.getValue()), BIZ_APP_ID, null, ZYPConstant.FLG_OFF_N,
//                        sMsg.prchReqTpCd_SL.getValue());
//                if (result.getXxMsgId() != null) {
//                    // QC#22026 Add
//                    if (!ZYPCommonFunc.hasValue(sMsg.srcRtlSwhCd)) {
//                        cMsg.srcRtlSwhCd.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_SRC_RTL_SWH_CD });
//                    } else {
//                        cMsg.srcRtlWhCd.setErrorInfo(1, result.getXxMsgId(), null);
//                        cMsg.srcRtlSwhCd.setErrorInfo(1, result.getXxMsgId(), null);
//                    }
//                    // QC#22026 End
//                    return false;
//                }
//            }
//        }
//        
//        if (PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue()) && !ZYPCommonFunc.hasValue(sMsg.destRtlSwhCd)) {
//            return true;
//        }
//        if (ZYPCommonFunc.hasValue(sMsg.destRtlWhCd) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(sMsg.destRtlWhCd.getValue()) && !SCREEN_CTRL_VALUE_MULTIPLE.equals(sMsg.destRtlSwhCd.getValue())) {
//            NMXC100001EnableWHData result = null;
//            if (PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue())) {
//                result = NMXC100001EnableWH.checkEnableWH(glblCmpyCd, ZYPCommonFunc.concatString(sMsg.destRtlWhCd.getValue(), "", sMsg.destRtlSwhCd.getValue()), BIZ_APP_ID, null, ZYPConstant.FLG_ON_Y, sMsg.prchReqTpCd_SL.getValue());
//            } else {
//                result = NMXC100001EnableWH.checkEnableWH(glblCmpyCd, ZYPCommonFunc.concatString(sMsg.destRtlWhCd.getValue(), "", sMsg.destRtlSwhCd.getValue()), BIZ_APP_ID, null, ZYPConstant.FLG_OFF_N, sMsg.prchReqTpCd_SL.getValue());
//            }
//            if (result.getXxMsgId() != null) {
//                // QC#22026 Add
//                if (!ZYPCommonFunc.hasValue(sMsg.destRtlSwhCd)) {
//                    cMsg.destRtlSwhCd.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_DEST_RTL_SWH_CD });
//                } else {
//                    cMsg.destRtlWhCd.setErrorInfo(1, result.getXxMsgId(), null);
//                    cMsg.destRtlSwhCd.setErrorInfo(1, result.getXxMsgId(), null);
//                }
//                // QC#22026 End
//                return false;
//            }
//        }
//        return true;
//    }
    // END 2018/04/03 S.Katsuma [QC#23521,25063,DEL]

    /**
     * <pre>
     * Check Location For Upload
     * </pre>
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @param uploadFileLineNum int
     * @return false for error
     */
    private static boolean checkLocationForUpload(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd, int uploadFileLineNum) {

        // Check Header Source Warehouse
        if (ZYPCommonFunc.hasValue(sMsg.srcRtlWhCd_U1)) {
            if (!checkSrcRtlWhForUpload(sMsg, glblCmpyCd)) {
                cMsg.setMessageInfo(NPAM1361E, new String[] {"Source WH Code (Line# " + String.valueOf(uploadFileLineNum) + ")" });
                return false;
            }
        }

        // Check Header Destination Warehouse
        if (ZYPCommonFunc.hasValue(sMsg.destRtlWhCd_U1)) {
            if (!checkDestRtlWhForUpload(sMsg, glblCmpyCd)) {
                cMsg.setMessageInfo(NPAM1361E, new String[] {"Destination WH Code (Line# " + String.valueOf(uploadFileLineNum) + ")" });
                return false;
            }
        }

        // Check Header Ship To Supplier
        if (!ZYPCommonFunc.hasValue(sMsg.vndCd) && ZYPCommonFunc.hasValue(sMsg.dplyVndNm)) {
            int count = checkShipToSupplierFromName(sMsg, glblCmpyCd);
            if (count == 0) {
                cMsg.dplyVndNm.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_DPLY_VND_NM });
                cMsg.setMessageInfo(NPAM1361E, new String[] {DISPLAY_NM_DPLY_VND_NM });
                return false;
            } else if (count > 1) {
                cMsg.dplyVndNm.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_DPLY_VND_NM });
                cMsg.setMessageInfo(NPBM0001E, new String[] {DISPLAY_NM_DPLY_VND_NM });
                return false;
            }
        }

        // Check Header Ship To Customer
        if (!ZYPCommonFunc.hasValue(sMsg.shipToCustCd_EO) && ZYPCommonFunc.hasValue(sMsg.shipToLocNm_EO)) {
            int count = getShipToCustCd(glblCmpyCd, sMsg.shipToCustCd_EO, sMsg.shipToLocNm_EO);
            if (count == 0) {
                cMsg.shipToLocNm_EO.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_SHIP_TO_LOC_NM });
                cMsg.setMessageInfo(NPAM1361E, new String[] {DISPLAY_NM_SHIP_TO_LOC_NM });
                return false;
            } else if (count > 1) {
                cMsg.shipToLocNm_EO.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_SHIP_TO_LOC_NM });
                cMsg.setMessageInfo(NPBM0001E, new String[] {DISPLAY_NM_SHIP_TO_LOC_NM });
                return false;
            }
        }

        // Location Security Check
        if (ZYPCommonFunc.hasValue(sMsg.srcRtlWhCd_U1) && ZYPCommonFunc.hasValue(sMsg.srcRtlSwhCd_U1)) {
            NMXC100001EnableWHData result = NMXC100001EnableWH.checkEnableWH(glblCmpyCd, ZYPCommonFunc.concatString(sMsg.srcRtlWhCd_U1.getValue(), "", sMsg.srcRtlSwhCd_U1.getValue()), BIZ_APP_ID, null, ZYPConstant.FLG_OFF_N,
                    sMsg.prchReqTpCd_SL.getValue());
            if (result.getXxMsgId() != null) {
                cMsg.setMessageInfo(result.getXxMsgId());
                return false;
            }
        }
        if (ZYPCommonFunc.hasValue(sMsg.destRtlWhCd_U1) && ZYPCommonFunc.hasValue(sMsg.destRtlSwhCd_U1)) {
            NMXC100001EnableWHData result = NMXC100001EnableWH.checkEnableWH(glblCmpyCd, ZYPCommonFunc.concatString(sMsg.destRtlWhCd_U1.getValue(), "", sMsg.destRtlSwhCd_U1.getValue()), BIZ_APP_ID, null, ZYPConstant.FLG_OFF_N,
                    sMsg.prchReqTpCd_SL.getValue());
            if (result.getXxMsgId() != null) {
                cMsg.setMessageInfo(result.getXxMsgId());
                return false;
            }
        }

        // Check Stock Status Code
        if (ZYPCommonFunc.hasValue(sMsg.stkStsCd_U1)) {

            STK_STSTMsg sstMsg = new STK_STSTMsg();
            ZYPEZDItemValueSetter.setValue(sstMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(sstMsg.stkStsCd, sMsg.stkStsCd_U1);

            sstMsg = (STK_STSTMsg) EZDTBLAccessor.findByKey(sstMsg);

            if (sstMsg == null) {
                cMsg.setMessageInfo(NPAM1361E, new String[] {"Stock Status Code (Line# " + String.valueOf(uploadFileLineNum) + ")" });
                return false;
            }
        }

        return true;
    }

    /**
     * <pre>
     * Check Ship To Supplier CMsg
     * </pre>
     * @param cMsg NPBL0020CMsg
     * @param glblCmpyCd String
     * @return false for error
     */
    public static boolean checkShipToSupplierCMsg(NPBL0020CMsg cMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_VND_CD, cMsg.vndCd.getValue());

        S21SsmEZDResult result = NPBL0020Query.getInstance().findShipToSupplier(ssmParam);

        if (!result.isCodeNormal()) {
            return false;
        }
        List<Map<String, String>> shipToSupplierList = (List<Map<String, String>>) result.getResultObject();
        if (shipToSupplierList.size() == 0) {
            return false;
        }
        Map<String, String> recode = shipToSupplierList.get(0);
        ZYPEZDItemValueSetter.setValue(cMsg.prntVndCd, (String) recode.get(DB_COLUMN_PRNT_VND_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.dplyVndNm, (String) recode.get(DB_COLUMN_DPLY_VND_NM));
        return true;
    }

    /**
     * <pre>
     * Check Ship To Supplier
     * </pre>
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @return false for error
     */
    public static boolean checkShipToSupplier(NPBL0020SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_VND_CD, sMsg.vndCd.getValue());

        S21SsmEZDResult result = NPBL0020Query.getInstance().findShipToSupplier(ssmParam);

        if (!result.isCodeNormal()) {
            return false;
        }
        List<Map<String, String>> shipToSupplierList = (List<Map<String, String>>) result.getResultObject();
        if (shipToSupplierList.size() == 0) {
            return false;
        }
        Map<String, String> recode = shipToSupplierList.get(0);
        ZYPEZDItemValueSetter.setValue(sMsg.prntVndCd, (String) recode.get(DB_COLUMN_PRNT_VND_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.dplyVndNm, (String) recode.get(DB_COLUMN_DPLY_VND_NM));
        return true;
    }

    /**
     * <pre>
     * Check Ship To Supplier From Name
     * </pre>
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @return result count
     */
    private static int checkShipToSupplierFromName(NPBL0020SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_DPLY_VND_NM, sMsg.dplyVndNm.getValue());

        S21SsmEZDResult result = NPBL0020Query.getInstance().findShipToSupplierFromName(ssmParam);

        if (!result.isCodeNormal()) {
            return 0;
        }
        List<Map<String, String>> shipToSupplierList = (List<Map<String, String>>) result.getResultObject();
        if (shipToSupplierList.size() != 1) {
            return shipToSupplierList.size();
        }
        Map<String, String> recode = shipToSupplierList.get(0);
        ZYPEZDItemValueSetter.setValue(sMsg.prntVndCd, (String) recode.get(DB_COLUMN_PRNT_VND_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.vndCd, (String) recode.get(DB_COLUMN_VND_CD));
        return shipToSupplierList.size();
    }

    /**
     * <pre>
     * Check Ship To Supplier From Name
     * </pre>
     * @param glblCmpyCd String
     * @param dplyVndNm String
     * @param vndCd EZDCStringItem
     * @param prntVndCd EZDCStringItem
     * @return result count
     */
    public static int checkShipToSupplierFromNameForLine(String glblCmpyCd, String dplyVndNm, EZDCStringItem vndCd ,EZDCStringItem prntVndCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_DPLY_VND_NM, dplyVndNm);

        S21SsmEZDResult result = NPBL0020Query.getInstance().findShipToSupplierFromName(ssmParam);

        if (!result.isCodeNormal()) {
            return 0;
        }
        List<Map<String, String>> shipToSupplierList = (List<Map<String, String>>) result.getResultObject();
        if (shipToSupplierList.size() != 1) {
            return shipToSupplierList.size();
        }
        Map<String, String> recode = shipToSupplierList.get(0);
        ZYPEZDItemValueSetter.setValue(prntVndCd, (String) recode.get(DB_COLUMN_PRNT_VND_CD));
        ZYPEZDItemValueSetter.setValue(vndCd, (String) recode.get(DB_COLUMN_VND_CD));
        return shipToSupplierList.size();
    }

    /**
     * <pre>
     * Check Source Rtl WH From Name
     * </pre>
     * @param glblCmpyCd String
     * @param whNm EZDSStringItem
     * @param whCd EZDSStringItem
     * @param whCatgCd EZDSStringItem
     * @return result count
     */
    private static int checkSrcRtlWhFromName(String glblCmpyCd, EZDSStringItem whNm, EZDSStringItem whCd, EZDSStringItem whCatgCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_WH_NM, whNm.getValue());
        if (ZYPCommonFunc.hasValue(whCd)) {
            ssmParam.put(DB_PARAM_RTL_WH_CD, whCd.getValue());
        }
        S21SsmEZDResult result = NPBL0020Query.getInstance().findRtlWhFromName(ssmParam);

        if (!result.isCodeNormal()) {
            return 0;
        }
        List<Map<String, String>> rtlWhList = (List<Map<String, String>>) result.getResultObject();
        if (rtlWhList.size() != 1) {
            return rtlWhList.size();
        }
        Map<String, String> recode = rtlWhList.get(0);
        ZYPEZDItemValueSetter.setValue(whCd, (String) recode.get(DB_COLUMN_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(whCatgCd, (String) recode.get(DB_COLUMN_RTL_WH_CATG_CD));
        return rtlWhList.size();
    }

    /**
     * <pre>
     * Check Source Rtl WH For Upload
     * </pre>
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private static boolean checkSrcRtlWhForUpload(NPBL0020SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_WH_CD, sMsg.srcRtlWhCd_U1.getValue());

        S21SsmEZDResult result = NPBL0020Query.getInstance().findRtlWh(ssmParam);

        if (!result.isCodeNormal()) {
            return false;
        }
        List<Map<String, String>> rtlWhList = (List<Map<String, String>>) result.getResultObject();
        if (rtlWhList.size() == 0) {
            return false;
        }
        Map<String, String> record = rtlWhList.get(0);
        ZYPEZDItemValueSetter.setValue(sMsg.rtlWhNm_U1, (String) record.get(DB_COLUMN_RTL_WH_NM));
        return true;
    }

    /**
     * <pre>
     * Check Destination Rtl WH From Name
     * </pre>
     * @param glblCmpyCd String
     * @param whNm EZDSStringItem
     * @param whCd EZDSStringItem
     * @param whCatgCd EZDSStringItem
     * @return result count
     */
    private static int checkDestRtlWhFromName(String glblCmpyCd, EZDSStringItem whNm, EZDSStringItem whCd, EZDSStringItem whCatgCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_WH_NM, whNm.getValue());
        if (ZYPCommonFunc.hasValue(whCd)) {
            ssmParam.put(DB_PARAM_RTL_WH_CD, whCd.getValue());
        }
        S21SsmEZDResult result = NPBL0020Query.getInstance().findRtlWhFromName(ssmParam);

        if (!result.isCodeNormal()) {
            return 0;
        }
        List<Map<String, String>> rtlWhList = (List<Map<String, String>>) result.getResultObject();
        if (rtlWhList.size() != 1) {
            return rtlWhList.size();
        }
        Map<String, String> recode = rtlWhList.get(0);
        ZYPEZDItemValueSetter.setValue(whCd, (String) recode.get(DB_COLUMN_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(whCatgCd, (String) recode.get(DB_COLUMN_RTL_WH_CATG_CD));
        return rtlWhList.size();
    }

    /**
     * <pre>
     * Check Destination Rtl WH From Name
     * </pre>
     * @param glblCmpyCd String
     * @param whNm EZDCStringItem
     * @param whCd EZDCStringItem
     * @param whCatgCd EZDSStringItem
     * @return result count
     */
    public static int checkDestRtlWhFromName(String glblCmpyCd, EZDCStringItem whNm, EZDCStringItem whCd, EZDCStringItem whCatgCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_WH_NM, whNm.getValue());

        S21SsmEZDResult result = NPBL0020Query.getInstance().findRtlWhFromName(ssmParam);

        if (!result.isCodeNormal()) {
            return 0;
        }
        List<Map<String, String>> rtlWhList = (List<Map<String, String>>) result.getResultObject();
        if (rtlWhList.size() != 1) {
            return rtlWhList.size();
        }
        Map<String, String> recode = rtlWhList.get(0);
        ZYPEZDItemValueSetter.setValue(whCd, (String) recode.get(DB_COLUMN_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(whCatgCd, (String) recode.get(DB_COLUMN_RTL_WH_CATG_CD));
        return rtlWhList.size();
    }

    /**
     * <pre>
     * Check Destination Rtl WH For Upload
     * </pre>
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private static boolean checkDestRtlWhForUpload(NPBL0020SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_WH_CD, sMsg.destRtlWhCd_U1.getValue());

        S21SsmEZDResult result = NPBL0020Query.getInstance().findRtlWh(ssmParam);

        if (!result.isCodeNormal()) {
            return false;
        }
        List<Map<String, String>> rtlWhList = (List<Map<String, String>>) result.getResultObject();
        if (rtlWhList.size() == 0) {
            return false;
        }
        Map<String, String> record = rtlWhList.get(0);
        ZYPEZDItemValueSetter.setValue(sMsg.rtlWhNm_U2, (String) record.get(DB_COLUMN_RTL_WH_NM));
        return true;
    }

    /**
     * <pre>
     * findRtlWhName
     * </pre>
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @param index int
     * @return Map<String, String>
     */
    private static Map<String, String> findRtlWhName(NPBL0020SMsg sMsg, String glblCmpyCd, String whCd, int index) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_WH_CD, whCd);

        S21SsmEZDResult result = NPBL0020Query.getInstance().findRtlWh(ssmParam);

        if (!result.isCodeNormal()) {
            return null;
        }
        List<Map<String, String>> rtlWhList = (List<Map<String, String>>) result.getResultObject();
        if (rtlWhList.size() == 0) {
            return null;
        }
        Map<String, String> record = rtlWhList.get(0);
        return record;
    }

    // START 2018/04/03 S.Katsuma [QC#23521,25063,DEL]
//    /**
//     * <pre>
//     * Check Source Rtl SWH From Name
//     * </pre>
//     * @param sMsg NPBL0020SMsg
//     * @param glblCmpyCd String
//     * @return result count
//     */
//    private static int checkSrcRtlSwhFromName(NPBL0020SMsg sMsg, String glblCmpyCd) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
//        ssmParam.put(DB_PARAM_RTL_WH_CD, sMsg.srcRtlWhCd.getValue());
//        ssmParam.put(DB_PARAM_RTL_SWH_NM, sMsg.rtlSwhNm_SS.getValue());
//
//        S21SsmEZDResult result = NPBL0020Query.getInstance().findRtlSwhFromName(ssmParam);
//
//        if (!result.isCodeNormal()) {
//            return 0;
//        }
//        List<Map<String, String>> rtlSwhList = (List<Map<String, String>>) result.getResultObject();
//        if (rtlSwhList.size() != 1) {
//            return rtlSwhList.size();
//        }
//        Map<String, String> recode = rtlSwhList.get(0);
//        ZYPEZDItemValueSetter.setValue(sMsg.srcRtlSwhCd, (String) recode.get(DB_COLUMN_RTL_SWH_CD));
//        return rtlSwhList.size();
//    }

//    /**
//     * <pre>
//     * Check Destination Rtl SWH From Name
//     * </pre>
//     * @param sMsg NPBL0020SMsg
//     * @param glblCmpyCd String
//     * @return result count
//     */
//    private static int checkDestRtlSwhFromName(NPBL0020SMsg sMsg, String glblCmpyCd) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
//        ssmParam.put(DB_PARAM_RTL_WH_CD, sMsg.destRtlWhCd.getValue());
//        ssmParam.put(DB_PARAM_RTL_SWH_NM, sMsg.rtlSwhNm_DS.getValue());
//
//        S21SsmEZDResult result = NPBL0020Query.getInstance().findRtlSwhFromName(ssmParam);
//
//        if (!result.isCodeNormal()) {
//            return 0;
//        }
//        List<Map<String, String>> rtlSwhList = (List<Map<String, String>>) result.getResultObject();
//        if (rtlSwhList.size() != 1) {
//            return rtlSwhList.size();
//        }
//        Map<String, String> recode = rtlSwhList.get(0);
//        ZYPEZDItemValueSetter.setValue(sMsg.destRtlSwhCd, (String) recode.get(DB_COLUMN_RTL_SWH_CD));
//        return rtlSwhList.size();
//    }
    // END 2018/04/03 S.Katsuma [QC#23521,25063,DEL]

    /**
     * <pre>
     * Check Requester
     * </pre>
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @return false for error
     */
    private static boolean checkRequester(NPBL0020SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_FULL_PSN_NM, sMsg.fullPsnNm.getValue());

        S21SsmEZDResult result = NPBL0020Query.getInstance().findRequester(ssmParam);

        if (!result.isCodeNormal()) {
            return false;
        }
        List<Map<String, String>> requesterList = (List<Map<String, String>>) result.getResultObject();
        if (requesterList.size() == 0) {
            return false;
        }
        Map<String, String> recode = requesterList.get(0);
        ZYPEZDItemValueSetter.setValue(sMsg.prchReqRqstByPsnCd, (String) recode.get(DB_COLUMN_PSN_CD));
        return true;
    }

    /**
     * <pre>
     * Check Carrier
     * </pre>
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @return false for error
     */
    private static boolean checkCarrier(NPBL0020SMsg sMsg, String glblCmpyCd) {

        OTBD_CARR_VTMsg inMsg = new OTBD_CARR_VTMsg();
        inMsg.setSQLID("005");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("carrNm01", sMsg.carrNm.getValue());
        OTBD_CARR_VTMsgArray outMsgs = (OTBD_CARR_VTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outMsgs.length() == 0) {
            return false;
        } else {
            ZYPEZDItemValueSetter.setValue(sMsg.carrCd, outMsgs.no(0).carrCd);
            return true;
        }
    }

    /**
     * <pre>
     * Check Relationship Carrier And ShippingServiceLevel
     * </pre>
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @return false for error
     */
    private static boolean checkRelationshipCarrierAndShippingServiceLevel(NPBL0020SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_SHPG_SVC_LVL_CD, sMsg.shpgSvcLvlCd_SL.getValue());
        ssmParam.put(DB_PARAM_CARR_CD, sMsg.carrCd.getValue());

        S21SsmEZDResult result = NPBL0020Query.getInstance().findShpgSvcLvlCarrierRelation(ssmParam);

        if (!result.isCodeNormal()) {
            return false;
        }
        List<Map<String, Object>> relationList = (List<Map<String, Object>>) result.getResultObject();
        if (relationList.size() == 0) {
            return false;
        }
        return true;

    }

    // START 2018/04/03 S.Katsuma [QC#23521,25063,DEL]
//    /**
//     * <pre>
//     * Check Inventory Account
//     * </pre>
//     * @param asMsg NPBL0020_ASMsg
//     * @param glblCmpyCd String
//     * @return false for error
//     */
//    private static boolean checkInventoryAccount(NPBL0020_ASMsg asMsg, String glblCmpyCd) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
//        ssmParam.put(DB_PARAM_SRC_RTL_WH_CD, asMsg.srcRtlWhCd_A1.getValue());
//        ssmParam.put(DB_PARAM_DEST_RTL_WH_CD, asMsg.destRtlWhCd_A1.getValue());
//
//        S21SsmEZDResult result = NPBL0020Query.getInstance().checkInventoryAccount(ssmParam);
//
//        if (!result.isCodeNormal()) {
//            return false;
//        }
//        List<Map<String, Object>> rtlWhList = (List<Map<String, Object>>) result.getResultObject();
//        if (rtlWhList.size() == 0) {
//            return false;
//        }
//        return true;
//
//    }

//    /**
//     * <pre>
//     * Check Relationship Serial And Src Warehouse
//     * </pre>
//     * @param asMsg NPBL0020_ASMsg
//     * @param glblCmpyCd String
//     * @return false for error
//     */
//    private static boolean checkRelationshipSerialAndSrcWarehouse(NPBL0020_ASMsg asMsg, String glblCmpyCd) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
//        ssmParam.put(DB_PARAM_SER_NUM, asMsg.xxLogDtlTxt_A1.getValue().split(SCREEN_CTRL_VALUE_SERIAL_NUM_DELIM));
//
//        S21SsmEZDResult result = NPBL0020Query.getInstance().findSerialWarehouseRelation(ssmParam);
//
//        if (!result.isCodeNormal()) {
//            return true;
//        }
//        List<Map<String, String>> warehouseList = (List<Map<String, String>>) result.getResultObject();
//        for (int i = 0; i < warehouseList.size(); i++) {
//            Map<String, String> recode = warehouseList.get(0);
//            if (!ZYPCommonFunc.concatString(asMsg.srcRtlWhCd_A1.getValue(), "", asMsg.srcRtlSwhCd_A1.getValue()).equals((String) recode.get(DB_COLUMN_CUR_LOC_NUM))) {
//                return false;
//            }
//        }
//        return true;
//    }

//    /**
//     * <pre>
//     * Check Relationship Config# And Src Warehouse
//     * </pre>
//     * @param asMsg NPBL0020_ASMsg
//     * @param glblCmpyCd String
//     * @return false for error
//     */
//    private static boolean checkRelationshipConfigAndSrcWarehouse(NPBL0020_ASMsg asMsg, String glblCmpyCd) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
//        ssmParam.put(DB_PARAM_SVC_CONFIG_MSTR_PK, asMsg.svcConfigMstrPk_A1.getValue());
//        List<String> svcMachMstrStsCdList = new ArrayList<String>(2);
//        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.CREATED);
//        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.REMOVED);
//        ssmParam.put(DB_PARAM_SVC_MACH_MSTR_STS_CD_LIST, svcMachMstrStsCdList);
//        ssmParam.put(DB_PARAM_CUR_LOC_NUM, ZYPCommonFunc.concatString(asMsg.srcRtlWhCd_A1.getValue(), "", asMsg.srcRtlSwhCd_A1.getValue()));
//        ssmParam.put(DB_PARAM_SVC_MACH_MAINT_AVAL_FLG, ZYPConstant.FLG_ON_Y);
//
//        S21SsmEZDResult result = NPBL0020Query.getInstance().getConfigComponents(ssmParam);
//
//        if (!result.isCodeNormal()) {
//            // Error
//            asMsg.svcConfigMstrPk_A1.setErrorInfo(1, NPBM0003E, new String[] {DISPLAY_NM_SRC_RTL_SWH_CD_LINE });
//            asMsg.rtlWhNm_A1.setErrorInfo(1, NPBM0003E, new String[] {DISPLAY_NM_SRC_RTL_SWH_CD_LINE });
//            return false;
//        }
//        List<Map<String, Object>> configComponentList = (List<Map<String, Object>>) result.getResultObject();
//        if (configComponentList.size() == 0) {
//            // Error
//            asMsg.svcConfigMstrPk_A1.setErrorInfo(1, NPBM0003E, new String[] {DISPLAY_NM_SRC_RTL_SWH_CD_LINE });
//            asMsg.rtlWhNm_A1.setErrorInfo(1, NPBM0003E, new String[] {DISPLAY_NM_SRC_RTL_SWH_CD_LINE });
//            return false;
//        } else {
//            // search item
//            for (Map<String, Object> comp : configComponentList) {
//                String mdseCd = (String) comp.get(DB_COLUMN_MDSE_CD);
//                String stkStsCd = (String) comp.get(DB_COLUMN_STK_STS_CD);
//
//                if (ZYPCommonFunc.hasValue(mdseCd) //
//                        && mdseCd.equals(asMsg.mdseCd_A1.getValue())) {
//                    // item stock status check
//                    if (ZYPCommonFunc.hasValue(stkStsCd) //
//                            && stkStsCd.equals(asMsg.fromStkStsCd_A1.getValue())) {
//                        break;
//                    } else {
//                        // Error
//                        asMsg.svcConfigMstrPk_A1.setErrorInfo(1 //
//                                , NPBM0004E //
//                                , new String[] {DISPLAY_NM_CONFIG_NUM, DISPLAY_NM_STOCK_STATUS_CD });
//                        asMsg.fromStkStsCd_A1.setErrorInfo(1 //
//                                , NPBM0004E //
//                                , new String[] {DISPLAY_NM_CONFIG_NUM, DISPLAY_NM_STOCK_STATUS_CD });
//                        return false;
//                    }
//                }
//            }
//        }
//
//        return true;
//    }
    // END 2018/04/03 S.Katsuma [QC#23521,25063,DEL]

    /**
     * <pre>
     * Set Warehouse To ConfigItemLine
     * </pre>
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     */
    private static void setWarehouseToConfigItemLine(NPBL0020SMsg sMsg, String glblCmpyCd) {

        int configLineIndex = -1;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).configTpCd_A1)) {
                if (sMsg.A.no(i).prchReqLineSubNum_A1.getValueInt() == 0) {
                    configLineIndex = i;
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).srcRtlWhCd_A1, sMsg.A.no(configLineIndex).srcRtlWhCd_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhNm_A1, sMsg.A.no(configLineIndex).rtlWhNm_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlSwhNm_A1, sMsg.A.no(configLineIndex).rtlSwhNm_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).destRtlWhCd_A1, sMsg.A.no(configLineIndex).destRtlWhCd_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhNm_A2, sMsg.A.no(configLineIndex).rtlWhNm_A2);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlSwhNm_A2, sMsg.A.no(configLineIndex).rtlSwhNm_A2);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhCatgCd_A1, sMsg.A.no(configLineIndex).rtlWhCatgCd_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhCatgCd_A2, sMsg.A.no(configLineIndex).rtlWhCatgCd_A2);
                }
            }
        }
    }

    /**
     * <pre>
     * Get Checked Count
     * QC#2376 Update.
     * </pre>
     * @param sMsg NPBL0020CMsg
     * @param cMsg NPBL0020SMsg
     */
    private static int getCheckedCount(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {

        int count = 0;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {
                count++;
            }
        }
        return count;
    }

    /**
     * <pre>
     * Get Checked Config Position
     * </pre>
     * @param sMsg NPBL0020CMsg
     */
    private static int getCheckedConfigPos(NPBL0020SMsg sMsg) {

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {
                if (CONFIG_TP.NEW.equals(sMsg.A.no(i).configTpCd_A1.getValue()) && sMsg.A.no(i).prchReqLineSubNum_A1.getValueInt() == 0) {
                    return i;
                } else {
                    return -1;
                }
            }
        }
        return -1;
    }

    /**
     * <pre>
     * Get Checked Config Item Last Position
     * </pre>
     * @param cMsg NPBL0020SMsg
     */
    private static int getCheckedConfigItemLastPos(NPBL0020SMsg sMsg) {

        String targetLineNum = null;
        int i = 0;

        for (; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {
                targetLineNum = sMsg.A.no(i).prchReqLineNum_A1.getValue();
            } else if (targetLineNum != null) {
                if (!targetLineNum.equals(sMsg.A.no(i).prchReqLineNum_A1.getValue())) {
                    return i - 1;
                }
            }
        }
        return i - 1;
    }

    /**
     * <pre>
     * copyFromCmsgOntoSmsg
     * Copy data From CMsg Onto SMsg
     * </pre>
     * @param sMsg NPBL0020CMsg
     * @param cMsg NPBL0020SMsg
     * @param pageFlg boolean
     */
    public static void copyFromCmsgOntoSmsg(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, boolean pageFlg) {

        ZYPEZDItemValueSetter.setValue(sMsg.poOrdNum, cMsg.poOrdNum);
        ZYPEZDItemValueSetter.setValue(sMsg.poOrdDtlLineNum, cMsg.poOrdDtlLineNum);
        ZYPEZDItemValueSetter.setValue(sMsg.prchReqNum, cMsg.prchReqNum);
        ZYPEZDItemValueSetter.setValue(sMsg.prchReqTpCd_SL, cMsg.prchReqTpCd_SL);
        ZYPEZDItemValueSetter.setValue(sMsg.prchReqStsCd, cMsg.prchReqStsCd);
        ZYPEZDItemValueSetter.setValue(sMsg.prchReqStsDescTxt, cMsg.prchReqStsDescTxt);
        ZYPEZDItemValueSetter.setValue(sMsg.prchReqApvlStsCd, cMsg.prchReqApvlStsCd);
        ZYPEZDItemValueSetter.setValue(sMsg.prchReqApvlStsDescTxt, cMsg.prchReqApvlStsDescTxt);
        ZYPEZDItemValueSetter.setValue(sMsg.prchReqCratDt, cMsg.prchReqCratDt);
        ZYPEZDItemValueSetter.setValue(sMsg.rqstRcvDt, cMsg.rqstRcvDt);
        ZYPEZDItemValueSetter.setValue(sMsg.prchReqSrcTpCd, cMsg.prchReqSrcTpCd);
        ZYPEZDItemValueSetter.setValue(sMsg.prchReqSrcTpDescTxt, cMsg.prchReqSrcTpDescTxt);
        ZYPEZDItemValueSetter.setValue(sMsg.trxRefNum, cMsg.trxRefNum);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        ZYPEZDItemValueSetter.setValue(sMsg.mrpPlnNm, cMsg.mrpPlnNm);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        ZYPEZDItemValueSetter.setValue(sMsg.prchReqRqstByPsnCd, cMsg.prchReqRqstByPsnCd);
        ZYPEZDItemValueSetter.setValue(sMsg.fullPsnNm, cMsg.fullPsnNm);
        ZYPEZDItemValueSetter.setValue(sMsg.shpgSvcLvlCd_SL, cMsg.shpgSvcLvlCd_SL);
        ZYPEZDItemValueSetter.setValue(sMsg.carrCd, cMsg.carrCd);
        ZYPEZDItemValueSetter.setValue(sMsg.carrNm, cMsg.carrNm);
        ZYPEZDItemValueSetter.setValue(sMsg.ctacPsnNm, cMsg.ctacPsnNm);
        ZYPEZDItemValueSetter.setValue(sMsg.prchReqCmntTxt, cMsg.prchReqCmntTxt);
        ZYPEZDItemValueSetter.setValue(sMsg.srcRtlWhCd, cMsg.srcRtlWhCd);
        ZYPEZDItemValueSetter.setValue(sMsg.rtlWhNm_SW, cMsg.rtlWhNm_SW);
        ZYPEZDItemValueSetter.setValue(sMsg.srcRtlSwhCd, cMsg.srcRtlSwhCd);
        ZYPEZDItemValueSetter.setValue(sMsg.rtlSwhNm_SS, cMsg.rtlSwhNm_SS);
        ZYPEZDItemValueSetter.setValue(sMsg.destRtlWhCd, cMsg.destRtlWhCd);
        ZYPEZDItemValueSetter.setValue(sMsg.rtlWhNm_DW, cMsg.rtlWhNm_DW);
        ZYPEZDItemValueSetter.setValue(sMsg.destRtlSwhCd, cMsg.destRtlSwhCd);
        ZYPEZDItemValueSetter.setValue(sMsg.rtlSwhNm_DS, cMsg.rtlSwhNm_DS);
        ZYPEZDItemValueSetter.setValue(sMsg.prntVndCd, cMsg.prntVndCd);
        ZYPEZDItemValueSetter.setValue(sMsg.vndCd, cMsg.vndCd);
        ZYPEZDItemValueSetter.setValue(sMsg.dplyVndNm, cMsg.dplyVndNm);
        ZYPEZDItemValueSetter.setValue(sMsg.svcConfigMstrPk, cMsg.svcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(sMsg.shipToLocNm, cMsg.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(sMsg.shipToAddlLocNm, cMsg.shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(sMsg.shipToFirstLineAddr, cMsg.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(sMsg.shipToScdLineAddr, cMsg.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(sMsg.shipToThirdLineAddr, cMsg.shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(sMsg.shipToFrthLineAddr, cMsg.shipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(sMsg.xxShipVndAddr, cMsg.xxShipVndAddr);
        ZYPEZDItemValueSetter.setValue(sMsg.shipToPostCd, cMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(sMsg.shipToCtyAddr, cMsg.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(sMsg.shipToCntyNm, cMsg.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(sMsg.shipToStCd, cMsg.shipToStCd);
        ZYPEZDItemValueSetter.setValue(sMsg.shipToProvNm, cMsg.shipToProvNm);
        ZYPEZDItemValueSetter.setValue(sMsg.shipToCtryCd, cMsg.shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(sMsg.ctryNm, cMsg.ctryNm);
        ZYPEZDItemValueSetter.setValue(sMsg.vndRtrnRsnCd_SL, cMsg.vndRtrnRsnCd_SL);
        ZYPEZDItemValueSetter.setValue(sMsg.rtlWhCatgCd_SW, cMsg.rtlWhCatgCd_SW);
        ZYPEZDItemValueSetter.setValue(sMsg.rtlWhCatgCd_DW, cMsg.rtlWhCatgCd_DW);
        ZYPEZDItemValueSetter.setValue(sMsg.shipToCustCd_EO, cMsg.shipToCustCd_EO);
        ZYPEZDItemValueSetter.setValue(sMsg.shipToLocNm_EO, cMsg.shipToLocNm_EO);
        // START 2018/04/03 S.Katsuma [QC#23521,25063,ADD]
        ZYPEZDItemValueSetter.setValue(sMsg.chrgAcctEdtblFlg, cMsg.chrgAcctEdtblFlg);
        // END 2018/04/03 S.Katsuma [QC#23521,25063,ADD]
        // START 2023/07/04 T.Kuroda [QC#61440, ADD]
        ZYPEZDItemValueSetter.setValue(sMsg.xxWrnSkipFlg_01, cMsg.xxWrnSkipFlg_01);
        ZYPEZDItemValueSetter.setValue(sMsg.xxWrnSkipFlg_02, cMsg.xxWrnSkipFlg_02);
        // END 2023/07/04 T.Kuroda [QC#61440, ADD]

        if (cMsg.A.getValidCount() == 0) {
            return;
        }
        for (int j = cMsg.xxPageShowFromNum.getValueInt() - 1, k = 0; j < cMsg.xxPageShowToNum.getValueInt(); j++, k++) {
            if (pageFlg) {
                cMsg.A.no(k).xxChkBox_A1.setValue(ZYPConstant.FLG_OFF_N);
            }
            EZDMsg.copy(cMsg.A.no(k), null, sMsg.A.no(j), null);
        }
    }

    /**
     * <pre>
     * copyFromSmsgOntoCmsg
     * Copy data From Smsg Onto Cmsg
     * </pre>
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    public static void copyFromSMsgOntoCmsg(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {

        ZYPEZDItemValueSetter.setValue(cMsg.poOrdNum, sMsg.poOrdNum);
        ZYPEZDItemValueSetter.setValue(cMsg.poOrdDtlLineNum, sMsg.poOrdDtlLineNum);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum, sMsg.prchReqNum);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpCd_SL, sMsg.prchReqTpCd_SL);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqStsCd, sMsg.prchReqStsCd);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqStsDescTxt, sMsg.prchReqStsDescTxt);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqApvlStsCd, sMsg.prchReqApvlStsCd);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqApvlStsDescTxt, sMsg.prchReqApvlStsDescTxt);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqCratDt, sMsg.prchReqCratDt);
        ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, sMsg.rqstRcvDt);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpCd, sMsg.prchReqSrcTpCd);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpDescTxt, sMsg.prchReqSrcTpDescTxt);
        ZYPEZDItemValueSetter.setValue(cMsg.trxRefNum, sMsg.trxRefNum);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        ZYPEZDItemValueSetter.setValue(cMsg.mrpPlnNm, sMsg.mrpPlnNm);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqRqstByPsnCd, sMsg.prchReqRqstByPsnCd);
        ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm, sMsg.fullPsnNm);
        ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_SL, sMsg.shpgSvcLvlCd_SL);
        ZYPEZDItemValueSetter.setValue(cMsg.carrCd, sMsg.carrCd);
        ZYPEZDItemValueSetter.setValue(cMsg.carrNm, sMsg.carrNm);
        ZYPEZDItemValueSetter.setValue(cMsg.ctacPsnNm, sMsg.ctacPsnNm);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqCmntTxt, sMsg.prchReqCmntTxt);
        ZYPEZDItemValueSetter.setValue(cMsg.srcRtlWhCd, sMsg.srcRtlWhCd);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_SW, sMsg.rtlWhNm_SW);
        ZYPEZDItemValueSetter.setValue(cMsg.srcRtlSwhCd, sMsg.srcRtlSwhCd);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_SS, sMsg.rtlSwhNm_SS);
        ZYPEZDItemValueSetter.setValue(cMsg.destRtlWhCd, sMsg.destRtlWhCd);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_DW, sMsg.rtlWhNm_DW);
        ZYPEZDItemValueSetter.setValue(cMsg.destRtlSwhCd, sMsg.destRtlSwhCd);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_DS, sMsg.rtlSwhNm_DS);
        ZYPEZDItemValueSetter.setValue(cMsg.prntVndCd, sMsg.prntVndCd);
        ZYPEZDItemValueSetter.setValue(cMsg.vndCd, sMsg.vndCd);
        ZYPEZDItemValueSetter.setValue(cMsg.dplyVndNm, sMsg.dplyVndNm);
        ZYPEZDItemValueSetter.setValue(cMsg.svcConfigMstrPk, sMsg.svcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToLocNm, sMsg.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToAddlLocNm, sMsg.shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToFirstLineAddr, sMsg.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToScdLineAddr, sMsg.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToThirdLineAddr, sMsg.shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToFrthLineAddr, sMsg.shipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(cMsg.xxShipVndAddr, sMsg.xxShipVndAddr);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToPostCd, sMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCtyAddr, sMsg.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCntyNm, sMsg.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToStCd, sMsg.shipToStCd);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToProvNm, sMsg.shipToProvNm);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCtryCd, sMsg.shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(cMsg.ctryNm, sMsg.ctryNm);
        ZYPEZDItemValueSetter.setValue(cMsg.vndRtrnRsnCd_SL, sMsg.vndRtrnRsnCd_SL);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_SW, sMsg.rtlWhCatgCd_SW);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_DW, sMsg.rtlWhCatgCd_DW);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd_EO, sMsg.shipToCustCd_EO);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToLocNm_EO, sMsg.shipToLocNm_EO);
        // START 2018/04/03 S.Katsuma [QC#23521,25063,ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.chrgAcctEdtblFlg, sMsg.chrgAcctEdtblFlg);
        // END 2018/04/03 S.Katsuma [QC#23521,25063,ADD]
        // copy data from SMsg onto CMsg
        ZYPTableUtil.clear(cMsg.A);
        setPagePos(cMsg, sMsg);

        if (0 < sMsg.A.getValidCount()) {
            int cMsgCount = 0;
            for (int i = cMsg.xxPageShowFromNum.getValueInt() - 1; i < cMsg.xxPageShowToNum.getValueInt(); i++) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(cMsgCount), null);
                cMsgCount++;
            }
            cMsg.A.setValidCount(cMsgCount);
        }
    }

    /**
     * <pre>
     * copyFromSmsgOntoCmsg
     * Copy data From Smsg Onto Cmsg
     * </pre>
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    public static void copyFromSMsgDetailOntoCmsgDetail(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {
        // copy data from SMsg onto CMsg
        ZYPTableUtil.clear(cMsg.A);
        setPagePos(cMsg, sMsg);

        if (0 < sMsg.A.getValidCount()) {
            int cMsgCount = 0;
            for (int i = cMsg.xxPageShowFromNum.getValueInt() - 1; i < cMsg.xxPageShowToNum.getValueInt(); i++) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(cMsgCount), null);
                cMsgCount++;
            }
            cMsg.A.setValidCount(cMsgCount);
        }
    }

    /**
     * <pre>
     * Set page pos.
     * </pre>
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private static void setPagePos(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {
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
     * <pre>
     * Set page pos.
     * </pre>
     * @param allRowCount int
     * @param pageRowCount int
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
     * Find PR by primary key, for update.
     * @param cMsg NPBL0020CMsg
     * @param glblCmpyCd String
     * @return PRCH_REQTMsg
     */
    private static PRCH_REQTMsg findPR(NPBL0020CMsg cMsg, String glblCmpyCd) {
        if (!ZYPCommonFunc.hasValue(cMsg.prchReqNum)) {
            return null;
        }

        PRCH_REQTMsg inMsg = new PRCH_REQTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.prchReqNum, cMsg.prchReqNum);
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);

        PRCH_REQTMsg outMsg = (PRCH_REQTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);
        return outMsg;
    }

    /**
     * If lock cannot acquired successfully, set error message and
     * then returns false.
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param tMsg PRCH_REQTMsg
     * @return boolean
     */
    private static boolean tryLock(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, PRCH_REQTMsg tMsg) {
        if (isSameTimeStamp(sMsg, tMsg)) {
            return true;
        } else {
            cMsg.setMessageInfo(NPAM0006E);
            return false;
        }
    }

    /**
     * @param sMsg NPBL0020SMsg
     * @param tMsg PRCH_REQTMsg
     * @return returns true if timestamps are the same.
     */
    private static boolean isSameTimeStamp(NPBL0020SMsg sMsg, PRCH_REQTMsg tMsg) {
        if (tMsg == null) {
            return false;
        }

        String preUpTime = sMsg.xxRqstTs.getValue();
        String preTimeZone = sMsg.xxRqstTz.getValue();
        String currUpTime = tMsg.ezUpTime.getValue();
        String currTimeZone = tMsg.ezUpTimeZone.getValue();

        return ZYPDateUtil.isSameTimeStamp(preUpTime, preTimeZone, currUpTime, currTimeZone);
    }

    /**
     * Creates new pMsg for NPZC103001 mode=create event=save
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @return NPZC103001PMsg
     */
    private static NPZC103001PMsg createNPZC103001PMsgForSaveCreate(NPBL0020SMsg sMsg, String glblCmpyCd) {

        NPZC103001PMsg pMsg = new NPZC103001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC103001Constant.MODE_CREATE);
        ZYPEZDItemValueSetter.setValue(pMsg.eventId, NPZC103001Constant.EVENT_SUBMIT);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.procDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqRecTpCd, PRCH_REQ_REC_TP.INVENTORY_REQUEST);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqTpCd, sMsg.prchReqTpCd_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqCratByPsnCd, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        if (ZYPCommonFunc.hasValue(sMsg.prchReqRqstByPsnCd) && ZYPCommonFunc.hasValue(sMsg.fullPsnNm)) {
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqRqstByPsnCd, sMsg.prchReqRqstByPsnCd);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqRqstByPsnCd, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqSrcTpCd, PRCH_REQ_SRC_TP.INVENTORY_REQUEST_ENTRY);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqApvlStsCd, PRCH_REQ_APVL_STS.ENTERED);

        if (PRCH_REQ_TP.WH_TRANSFER.equals(sMsg.prchReqTpCd_SL.getValue()) && //
                !SCREEN_CTRL_VALUE_MULTIPLE.equals(sMsg.destRtlWhCd.getValue()) && //
                ZYPCommonFunc.hasValue(sMsg.destRtlSwhCd)) {

            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, sMsg.destRtlWhCd.getValue());

        } else if (!PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue()) && ZYPCommonFunc.hasValue(sMsg.vndCd)) {

            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, sMsg.vndCd);

        } else if (SCREEN_CTRL_VALUE_MULTIPLE.equals(sMsg.destRtlWhCd.getValue())) {

            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, sMsg.A.no(0).destRtlWhCd_A1.getValue());

        } else if (SCREEN_CTRL_VALUE_MULTIPLE.equals(sMsg.shipToCustCd_EO.getValue())) {

            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, sMsg.A.no(0).shipToCustCd_E1);

        } else if (PRCH_REQ_TP.WH_TRANSFER.equals(sMsg.prchReqTpCd_SL.getValue())) {

            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, sMsg.A.no(0).destRtlWhCd_A1.getValue());

        } else if (PRCH_REQ_TP.EXPENSE_ORDER.equals(sMsg.prchReqTpCd_SL.getValue())) {

            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, sMsg.A.no(0).shipToCustCd_E1);

        } else if (PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue())) {

            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, sMsg.destRtlWhCd.getValue());

        } else if (PRCH_REQ_TP.DISPOSAL.equals(sMsg.prchReqTpCd_SL.getValue())) {

            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, sMsg.A.no(0).srcRtlWhCd_A1.getValue());

        // QC#18408
        } else if (PRCH_REQ_TP.KITTING.equals(sMsg.prchReqTpCd_SL.getValue())) {

            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, sMsg.destRtlWhCd.getValue());

        }

        if ((PRCH_REQ_TP.DISPOSAL.equals(sMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.VENDOR_RETURN.equals(sMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.REFURBISHING.equals(sMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue()) //
                ) //
                && ZYPCommonFunc.hasValue(sMsg.shipToCustCd_EO)) {

            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, sMsg.shipToCustCd_EO);
        }

        // QC#22517
        if (PRCH_REQ_TP.EXPENSE_ORDER.equals(sMsg.prchReqTpCd_SL.getValue())) {
	        int lineAddrLength = sMsg.xxShipVndAddr.getValue().length();
	        if (lineAddrLength < 61) {
	            ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, sMsg.xxShipVndAddr.getValue());
	        } else if (lineAddrLength < 121) {
	            ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, sMsg.xxShipVndAddr.getValue().toString().trim().substring(0, 60));
	            ZYPEZDItemValueSetter.setValue(pMsg.shipToScdLineAddr, sMsg.xxShipVndAddr.getValue().substring(60).toString().trim());
	        } else if (lineAddrLength < 181) {
	            ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, sMsg.xxShipVndAddr.getValue().substring(0, 60));
	            ZYPEZDItemValueSetter.setValue(pMsg.shipToScdLineAddr, sMsg.xxShipVndAddr.getValue().substring(60, 120).toString().trim());
	            ZYPEZDItemValueSetter.setValue(pMsg.shipToThirdLineAddr, sMsg.xxShipVndAddr.getValue().substring(120).toString().trim());
	        } else {
	            ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, sMsg.xxShipVndAddr.getValue().substring(0, 60).toString().trim());
	            ZYPEZDItemValueSetter.setValue(pMsg.shipToScdLineAddr, sMsg.xxShipVndAddr.getValue().substring(60, 120).toString().trim());
	            ZYPEZDItemValueSetter.setValue(pMsg.shipToThirdLineAddr, sMsg.xxShipVndAddr.getValue().substring(120, 180).toString().trim());
	            ZYPEZDItemValueSetter.setValue(pMsg.shipToFrthLineAddr, sMsg.xxShipVndAddr.getValue().substring(180).toString().trim());
	        }
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, sMsg.shipToFirstLineAddr);
            ZYPEZDItemValueSetter.setValue(pMsg.shipToScdLineAddr, sMsg.shipToScdLineAddr);
            ZYPEZDItemValueSetter.setValue(pMsg.shipToThirdLineAddr, sMsg.shipToThirdLineAddr);
            ZYPEZDItemValueSetter.setValue(pMsg.shipToFrthLineAddr, sMsg.shipToFrthLineAddr);
        	
        }

        ZYPEZDItemValueSetter.setValue(pMsg.shipToLocNm, sMsg.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToAddlLocNm, sMsg.shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCtyAddr, sMsg.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToStCd, sMsg.shipToStCd);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToProvNm, sMsg.shipToProvNm);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCntyNm, sMsg.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToPostCd, sMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCtryCd, sMsg.shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnNm, sMsg.ctacPsnNm);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqCmntTxt, sMsg.prchReqCmntTxt);

        int i = 0;
        int serNumInfoCounter = 0;
        int cntForPRInfo = 0;

        for (i = 0; i < sMsg.A.getValidCount(); i++) {
            
         // QC#29154 Add status check.
            if (PRCH_REQ_LINE_STS.CANCELLED.equals(sMsg.A.no(i).prchReqLineStsCd_A1.getValue()) //
                    || PRCH_REQ_LINE_STS.CLOSED.equals(sMsg.A.no(i).prchReqLineStsCd_A1.getValue())) {
                // Skip cancel or skip record.
                continue;
            }

            NPZC103001_prchReqInfoPMsg apMsg = pMsg.prchReqInfo.no(cntForPRInfo);
            NPBL0020_ASMsg asMsg = sMsg.A.no(i);
            cntForPRInfo++;

            ZYPEZDItemValueSetter.setValue(apMsg.prchReqLineNum, asMsg.prchReqLineNum_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.prchReqLineSubNum, asMsg.prchReqLineSubNum_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.xxDtlLineNum, String.valueOf(i));
            ZYPEZDItemValueSetter.setValue(apMsg.prchReqLineTpCd, asMsg.prchReqLineTpCd_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.rqstRcvDt, sMsg.rqstRcvDt);
            ZYPEZDItemValueSetter.setValue(apMsg.shpgSvcLvlCd, sMsg.shpgSvcLvlCd_SL);
            ZYPEZDItemValueSetter.setValue(apMsg.carrCd, sMsg.carrCd);
            if (PRCH_REQ_TP.KITTING.equals(sMsg.prchReqTpCd_SL.getValue())) {
                ZYPEZDItemValueSetter.setValue(apMsg.procrTpCd, PROCR_TP.WORK_ORDER);
            } else {
                ZYPEZDItemValueSetter.setValue(apMsg.procrTpCd, PROCR_TP.WAREHOUSE);
            }
            if (PRCH_REQ_TP.EXPENSE_ORDER.equals(sMsg.prchReqTpCd_SL.getValue())) {
                ZYPEZDItemValueSetter.setValue(apMsg.destInvtyLocCd, asMsg.shipToCustCd_E1);
            } else {
                ZYPEZDItemValueSetter.setValue(apMsg.destInvtyLocCd, ZYPCommonFunc.concatString(asMsg.destRtlWhCd_A1.getValue(), "", asMsg.destRtlSwhCd_A1.getValue()));
            }
            if (ZYPCommonFunc.hasValue(asMsg.configTpCd_A1) && asMsg.prchReqLineSubNum_A1.getValueInt() == 0) {
                ZYPEZDItemValueSetter.setValue(apMsg.srcInvtyLocCd, asMsg.srcRtlWhCd_A1);
            } else {
                ZYPEZDItemValueSetter.setValue(apMsg.srcInvtyLocCd, ZYPCommonFunc.concatString(asMsg.srcRtlWhCd_A1.getValue(), "", asMsg.srcRtlSwhCd_A1.getValue()));
            }
            ZYPEZDItemValueSetter.setValue(apMsg.prntVndCd, sMsg.prntVndCd);
            ZYPEZDItemValueSetter.setValue(apMsg.vndCd, sMsg.vndCd);
            ZYPEZDItemValueSetter.setValue(apMsg.vndRtrnRsnCd, sMsg.vndRtrnRsnCd_SL);
            ZYPEZDItemValueSetter.setValue(apMsg.mdseCd, asMsg.mdseCd_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.svcConfigMstrPk, asMsg.svcConfigMstrPk_A1);
            if (ZYPCommonFunc.hasValue(asMsg.configTpCd_A1) && BigDecimal.ZERO.compareTo(asMsg.prchReqLineSubNum_A1.getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(apMsg.prchReqQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(apMsg.prchReqDispQty, BigDecimal.ZERO);
            } else {
                ZYPEZDItemValueSetter.setValue(apMsg.prchReqQty, asMsg.prchReqDispQty_A1);
                ZYPEZDItemValueSetter.setValue(apMsg.prchReqDispQty, asMsg.prchReqDispQty_A1);
            }
            ZYPEZDItemValueSetter.setValue(apMsg.configTpCd, asMsg.configTpCd_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.prchReqDsplUomCd, PRCH_REQ_DSPL_UOM_CD_EA);
            ZYPEZDItemValueSetter.setValue(apMsg.entDealNetUnitPrcAmt, asMsg.entDealNetUnitPrcAmt_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.entFuncNetUnitPrcAmt, asMsg.entDealNetUnitPrcAmt_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.ccyCd, CCY.US_DOLLAR);
            ZYPEZDItemValueSetter.setValue(apMsg.exchRate, EXCH_RATE_1);
            // QC#15815
            if (!PRCH_REQ_TP.VENDOR_RETURN.equals(sMsg.prchReqTpCd_SL.getValue())) {
                ZYPEZDItemValueSetter.setValue(apMsg.coaCmpyCd, asMsg.coaCmpyCd_A1);
                ZYPEZDItemValueSetter.setValue(apMsg.coaAfflCd, asMsg.coaAfflCd_A1);
                ZYPEZDItemValueSetter.setValue(apMsg.coaBrCd, asMsg.coaBrCd_A1);
                ZYPEZDItemValueSetter.setValue(apMsg.coaChCd, asMsg.coaChCd_A1);
                ZYPEZDItemValueSetter.setValue(apMsg.coaCcCd, asMsg.coaCcCd_A1);
                ZYPEZDItemValueSetter.setValue(apMsg.coaAcctCd, asMsg.coaAcctCd_A1);
                ZYPEZDItemValueSetter.setValue(apMsg.coaProdCd, asMsg.coaProdCd_A2);
                ZYPEZDItemValueSetter.setValue(apMsg.coaProjCd, asMsg.coaProjCd_A1);
                ZYPEZDItemValueSetter.setValue(apMsg.coaExtnCd, asMsg.coaExtnCd_A1);
            }
            ZYPEZDItemValueSetter.setValue(apMsg.poOrdNum, sMsg.poOrdNum);
            // QC#22481
            ZYPEZDItemValueSetter.setValue(apMsg.poOrdDtlLineNum, asMsg.trxRefLineNum_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.trxRefNum, sMsg.trxRefNum);
            // QC#22481
            ZYPEZDItemValueSetter.setValue(apMsg.trxRefLineNum, asMsg.trxRefLineNum_A1);
            //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) START
            //ZYPEZDItemValueSetter.setValue(apMsg.mrpPlnNm, sMsg.mrpPlnNm);
            //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) END
            ZYPEZDItemValueSetter.setValue(apMsg.prchReqLineCmntTxt, asMsg.prchReqLineCmntTxt_A1);

            ZYPEZDItemValueSetter.setValue(apMsg.fromStkStsCd, asMsg.fromStkStsCd_A1);

            if (PRCH_REQ_TP.WH_TRANSFER.equals(sMsg.prchReqTpCd_SL.getValue()) //
                    || PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue()) // 
                    || PRCH_REQ_TP.EXPENSE_ORDER.equals(sMsg.prchReqTpCd_SL.getValue())) {
                ZYPEZDItemValueSetter.setValue(apMsg.toStkStsCd, asMsg.fromStkStsCd_A1);
            }

            if (ZYPCommonFunc.hasValue(asMsg.xxLogDtlTxt_A1)) {
                String[] serialNumber = asMsg.xxLogDtlTxt_A1.getValue().split(SCREEN_CTRL_VALUE_SERIAL_NUM_DELIM);
                for (int j = 0; j < serialNumber.length; j++) {
                    NPZC103001_serNumInfoPMsg asmMsg = pMsg.serNumInfo.no(serNumInfoCounter);

                    ZYPEZDItemValueSetter.setValue(asmMsg.prchReqLineNum, asMsg.prchReqLineNum_A1);
                    ZYPEZDItemValueSetter.setValue(asmMsg.prchReqLineSubNum, asMsg.prchReqLineSubNum_A1);
                    ZYPEZDItemValueSetter.setValue(asmMsg.xxDtlLineNum, String.valueOf(i));
                    ZYPEZDItemValueSetter.setValue(asmMsg.serNum, serialNumber[j]);
                    serNumInfoCounter++;
                }
            }
        }
        pMsg.prchReqInfo.setValidCount(cntForPRInfo);
        pMsg.serNumInfo.setValidCount(serNumInfoCounter);

        return pMsg;
    }

    /**
     * Creates new pMsg for NPZC103001 mode=update event=save
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @return NPZC103001PMsg
     */
    private static NPZC103001PMsg createNPZC103001PMsgForSaveUpdate(NPBL0020SMsg sMsg, String glblCmpyCd) {

        NPZC103001PMsg pMsg = new NPZC103001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC103001Constant.MODE_UPDATE);
        ZYPEZDItemValueSetter.setValue(pMsg.eventId, NPZC103001Constant.EVENT_SUBMIT);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.procDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqNum, sMsg.prchReqNum);
        if (ZYPCommonFunc.hasValue(sMsg.prchReqRqstByPsnCd) && ZYPCommonFunc.hasValue(sMsg.fullPsnNm)) {
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqRqstByPsnCd, sMsg.prchReqRqstByPsnCd);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqRqstByPsnCd, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqApvlStsCd, sMsg.prchReqApvlStsCd);
        ZYPEZDItemValueSetter.setValue(pMsg.fsrNum, sMsg.fsrNum);
        ZYPEZDItemValueSetter.setValue(pMsg.svcTaskNum, sMsg.svcTaskNum);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachSerNum, sMsg.svcMachSerNum);

        if (PRCH_REQ_TP.WH_TRANSFER.equals(sMsg.prchReqTpCd_SL.getValue()) && //
                !SCREEN_CTRL_VALUE_MULTIPLE.equals(sMsg.destRtlWhCd.getValue()) && //
                ZYPCommonFunc.hasValue(sMsg.destRtlSwhCd)) {

            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, sMsg.destRtlWhCd.getValue());

        } else if (!PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue()) && ZYPCommonFunc.hasValue(sMsg.vndCd)) {

            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, sMsg.vndCd);

        } else if (SCREEN_CTRL_VALUE_MULTIPLE.equals(sMsg.destRtlWhCd.getValue())) {

            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, sMsg.A.no(0).destRtlWhCd_A1.getValue());

        } else if (SCREEN_CTRL_VALUE_MULTIPLE.equals(sMsg.shipToCustCd_EO.getValue())) {

            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, sMsg.A.no(0).shipToCustCd_E1);

        } else if (PRCH_REQ_TP.WH_TRANSFER.equals(sMsg.prchReqTpCd_SL.getValue())) {

            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, sMsg.A.no(0).destRtlWhCd_A1.getValue());

        } else if (PRCH_REQ_TP.EXPENSE_ORDER.equals(sMsg.prchReqTpCd_SL.getValue())) {

            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, sMsg.A.no(0).shipToCustCd_E1);

        } else if (PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue())) {

            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, sMsg.A.no(0).destRtlWhCd_A1.getValue());

        } else if (PRCH_REQ_TP.DISPOSAL.equals(sMsg.prchReqTpCd_SL.getValue())) {

            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, sMsg.A.no(0).srcRtlWhCd_A1.getValue());

        // QC#18408
        } else if (PRCH_REQ_TP.KITTING.equals(sMsg.prchReqTpCd_SL.getValue())) {

            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, sMsg.destRtlWhCd.getValue());

        }

        if ((PRCH_REQ_TP.DISPOSAL.equals(sMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.VENDOR_RETURN.equals(sMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.REFURBISHING.equals(sMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue()) //
                ) //
                && ZYPCommonFunc.hasValue(sMsg.shipToCustCd_EO)) {

            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, sMsg.shipToCustCd_EO);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.shipToLocNm, sMsg.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToAddlLocNm, sMsg.shipToAddlLocNm);

        // QC#22517
        if (PRCH_REQ_TP.EXPENSE_ORDER.equals(sMsg.prchReqTpCd_SL.getValue())) {
	        int lineAddrLength = sMsg.xxShipVndAddr.getValue().length();
	        if (lineAddrLength < 61) {
	            ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, sMsg.xxShipVndAddr.getValue());
	        } else if (lineAddrLength < 121) {
	            ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, sMsg.xxShipVndAddr.getValue().toString().trim().substring(0, 60));
	            ZYPEZDItemValueSetter.setValue(pMsg.shipToScdLineAddr, sMsg.xxShipVndAddr.getValue().substring(60).toString().trim());
	        } else if (lineAddrLength < 181) {
	            ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, sMsg.xxShipVndAddr.getValue().substring(0, 60));
	            ZYPEZDItemValueSetter.setValue(pMsg.shipToScdLineAddr, sMsg.xxShipVndAddr.getValue().substring(60, 120).toString().trim());
	            ZYPEZDItemValueSetter.setValue(pMsg.shipToThirdLineAddr, sMsg.xxShipVndAddr.getValue().substring(120).toString().trim());
	        } else {
	            ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, sMsg.xxShipVndAddr.getValue().substring(0, 60).toString().trim());
	            ZYPEZDItemValueSetter.setValue(pMsg.shipToScdLineAddr, sMsg.xxShipVndAddr.getValue().substring(60, 120).toString().trim());
	            ZYPEZDItemValueSetter.setValue(pMsg.shipToThirdLineAddr, sMsg.xxShipVndAddr.getValue().substring(120, 180).toString().trim());
	            ZYPEZDItemValueSetter.setValue(pMsg.shipToFrthLineAddr, sMsg.xxShipVndAddr.getValue().substring(180).toString().trim());
	        }
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, sMsg.shipToFirstLineAddr);
            ZYPEZDItemValueSetter.setValue(pMsg.shipToScdLineAddr, sMsg.shipToScdLineAddr);
            ZYPEZDItemValueSetter.setValue(pMsg.shipToThirdLineAddr, sMsg.shipToThirdLineAddr);
            ZYPEZDItemValueSetter.setValue(pMsg.shipToFrthLineAddr, sMsg.shipToFrthLineAddr);
        	
        }

        ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstRefCmntTxt, sMsg.shipToFirstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToScdRefCmntTxt, sMsg.shipToScdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCtyAddr, sMsg.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToStCd, sMsg.shipToStCd);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToProvNm, sMsg.shipToProvNm);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCntyNm, sMsg.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToPostCd, sMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCtryCd, sMsg.shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, sMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(pMsg.sellToCustCd, sMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, sMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnNm, sMsg.ctacPsnNm);
        ZYPEZDItemValueSetter.setValue(pMsg.adminPsnCd, sMsg.adminPsnCd);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqCmntTxt, sMsg.prchReqCmntTxt);
        // QC#21209: Delete spclInstnCmntTxt. spclInstnCmntTxt does not use.
        ZYPEZDItemValueSetter.setValue(pMsg.delyAddlCmntTxt, sMsg.delyAddlCmntTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.rcvAddlCmntTxt, sMsg.rcvAddlCmntTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.batErrMsgTxt, sMsg.batErrMsgTxt);

        // START QC#20439
        if (PRCH_REQ_APVL_STS.ENTERED.equals(sMsg.prchReqApvlStsCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqCratByPsnCd, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }
        // END QC#20439
        // QC#17075 START
        if (PRCH_REQ_SRC_TP.WH_PLANNING.equals(sMsg.prchReqSrcTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqCratByPsnCd, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }
        // QC#17075 END

        int i = 0;
        int serNumInfoCounter = 0;
        int cntForPRInfo = 0;
        for (i = 0; i < sMsg.A.getValidCount(); i++) {
            // QC#29154 Add status check.
            if (PRCH_REQ_LINE_STS.CANCELLED.equals(sMsg.A.no(i).prchReqLineStsCd_A1.getValue()) //
                    || PRCH_REQ_LINE_STS.CLOSED.equals(sMsg.A.no(i).prchReqLineStsCd_A1.getValue())) {
                // Skip cancel or skip record.
                continue;
            }

            NPZC103001_prchReqInfoPMsg apMsg = pMsg.prchReqInfo.no(cntForPRInfo);
            cntForPRInfo++;
            NPBL0020_ASMsg asMsg = sMsg.A.no(i);

            ZYPEZDItemValueSetter.setValue(apMsg.prchReqIntfcPk, asMsg.prchReqIntfcPk_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.prchReqLineNum, asMsg.prchReqLineNum_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.prchReqLineSubNum, asMsg.prchReqLineSubNum_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.xxDtlLineNum, asMsg.prchReqLineNum_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.origPrchReqLineNum, asMsg.origPrchReqLineNum_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.origPrchReqLineSubNum, asMsg.origPrchReqLineSubNum_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.prchReqLineTpCd, asMsg.prchReqLineTpCd_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.prchReqLineStsCd, asMsg.prchReqLineStsCd_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.rqstRcvDt, sMsg.rqstRcvDt);
            ZYPEZDItemValueSetter.setValue(apMsg.rqstRcvTm, asMsg.rqstRcvTm_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.poSchdRelDt, asMsg.poSchdRelDt_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.frtChrgToCd, asMsg.frtChrgToCd_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.frtChrgMethCd, asMsg.frtChrgMethCd_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.frtCondCd, asMsg.frtCondCd_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.shpgSvcLvlCd, sMsg.shpgSvcLvlCd_SL);
            ZYPEZDItemValueSetter.setValue(apMsg.carrCd, sMsg.carrCd);
            ZYPEZDItemValueSetter.setValue(apMsg.carrAcctNum, asMsg.carrAcctNum_A1);
            if (PRCH_REQ_TP.KITTING.equals(sMsg.prchReqTpCd_SL.getValue())) {
                ZYPEZDItemValueSetter.setValue(apMsg.procrTpCd, PROCR_TP.WORK_ORDER);
            } else {
                ZYPEZDItemValueSetter.setValue(apMsg.procrTpCd, PROCR_TP.WAREHOUSE);
            }
            if (PRCH_REQ_TP.EXPENSE_ORDER.equals(sMsg.prchReqTpCd_SL.getValue())) {
                ZYPEZDItemValueSetter.setValue(apMsg.destInvtyLocCd, asMsg.shipToCustCd_E1);
            } else {
                ZYPEZDItemValueSetter.setValue(apMsg.destInvtyLocCd, ZYPCommonFunc.concatString(asMsg.destRtlWhCd_A1.getValue(), "", asMsg.destRtlSwhCd_A1.getValue()));
            }
            if (ZYPCommonFunc.hasValue(asMsg.configTpCd_A1) && asMsg.prchReqLineSubNum_A1.getValueInt() == 0) {
                ZYPEZDItemValueSetter.setValue(apMsg.srcInvtyLocCd, asMsg.srcRtlWhCd_A1);
            } else {
                ZYPEZDItemValueSetter.setValue(apMsg.srcInvtyLocCd, ZYPCommonFunc.concatString(asMsg.srcRtlWhCd_A1.getValue(), "", asMsg.srcRtlSwhCd_A1.getValue()));
            }
            ZYPEZDItemValueSetter.setValue(apMsg.prntVndCd, sMsg.prntVndCd);
            ZYPEZDItemValueSetter.setValue(apMsg.vndCd, sMsg.vndCd);
            ZYPEZDItemValueSetter.setValue(apMsg.vndInvtyLocCd, asMsg.vndInvtyLocCd_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.vndRtrnRsnCd, sMsg.vndRtrnRsnCd_SL);
            ZYPEZDItemValueSetter.setValue(apMsg.poMdseCmpsnTpCd, asMsg.poMdseCmpsnTpCd_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.mdseCd, asMsg.mdseCd_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.aslMdseCd, asMsg.aslMdseCd_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.svcConfigMstrPk, asMsg.svcConfigMstrPk_A1);
            if (ZYPCommonFunc.hasValue(asMsg.configTpCd_A1) && BigDecimal.ZERO.compareTo(asMsg.prchReqLineSubNum_A1.getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(apMsg.prchReqQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(apMsg.prchReqDispQty, BigDecimal.ZERO);
            } else {
                ZYPEZDItemValueSetter.setValue(apMsg.prchReqQty, asMsg.prchReqDispQty_A1);
                ZYPEZDItemValueSetter.setValue(apMsg.prchReqDispQty, asMsg.prchReqDispQty_A1);
            }
            ZYPEZDItemValueSetter.setValue(apMsg.configTpCd, asMsg.configTpCd_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.prchReqDsplUomCd, PRCH_REQ_DSPL_UOM_CD_EA);
            ZYPEZDItemValueSetter.setValue(apMsg.proNum, asMsg.proNum_A1);

            ZYPEZDItemValueSetter.setValue(apMsg.fromStkStsCd, asMsg.fromStkStsCd_A1);
            if (PRCH_REQ_TP.WH_TRANSFER.equals(sMsg.prchReqTpCd_SL.getValue()) //
                    || PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue())//
                    || PRCH_REQ_TP.EXPENSE_ORDER.equals(sMsg.prchReqTpCd_SL.getValue())) {
                ZYPEZDItemValueSetter.setValue(apMsg.toStkStsCd, asMsg.fromStkStsCd_A1);
            }

            ZYPEZDItemValueSetter.setValue(apMsg.aslDtlPk, asMsg.aslDtlPk_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.aslUnitPrcAmt, asMsg.aslUnitPrcAmt_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.entDealNetUnitPrcAmt, asMsg.entDealNetUnitPrcAmt_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.entFuncNetUnitPrcAmt, asMsg.entDealNetUnitPrcAmt_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.ccyCd, CCY.US_DOLLAR);
            ZYPEZDItemValueSetter.setValue(apMsg.exchRate, EXCH_RATE_1);

            // QC#15815
            if (!PRCH_REQ_TP.VENDOR_RETURN.equals(sMsg.prchReqTpCd_SL.getValue())) {
                ZYPEZDItemValueSetter.setValue(apMsg.coaCmpyCd, asMsg.coaCmpyCd_A1);
                ZYPEZDItemValueSetter.setValue(apMsg.coaAfflCd, asMsg.coaAfflCd_A1);
                ZYPEZDItemValueSetter.setValue(apMsg.coaBrCd, asMsg.coaBrCd_A1);
                ZYPEZDItemValueSetter.setValue(apMsg.coaChCd, asMsg.coaChCd_A1);
                ZYPEZDItemValueSetter.setValue(apMsg.coaCcCd, asMsg.coaCcCd_A1);
                ZYPEZDItemValueSetter.setValue(apMsg.coaAcctCd, asMsg.coaAcctCd_A1);
                ZYPEZDItemValueSetter.setValue(apMsg.coaProdCd, asMsg.coaProdCd_A2);
                ZYPEZDItemValueSetter.setValue(apMsg.coaProjCd, asMsg.coaProjCd_A1);
                ZYPEZDItemValueSetter.setValue(apMsg.coaExtnCd, asMsg.coaExtnCd_A1);
            }
            ZYPEZDItemValueSetter.setValue(apMsg.relRqstToPoOrdNum, asMsg.relRqstToPoOrdNum_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.poOrdNum, sMsg.poOrdNum);
            // QC#22481
            ZYPEZDItemValueSetter.setValue(apMsg.poOrdDtlLineNum, asMsg.trxRefLineNum_A1);
            ZYPEZDItemValueSetter.setValue(apMsg.trxRefNum, sMsg.trxRefNum);
            // QC#22481
            ZYPEZDItemValueSetter.setValue(apMsg.trxRefLineNum, asMsg.trxRefLineNum_A1);
            //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) START
            //ZYPEZDItemValueSetter.setValue(apMsg.mrpPlnNm, sMsg.mrpPlnNm);
            //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) END
            ZYPEZDItemValueSetter.setValue(apMsg.prchReqLineCmntTxt, asMsg.prchReqLineCmntTxt_A1);

            if (ZYPCommonFunc.hasValue(asMsg.xxLogDtlTxt_A1)) {
                String[] serialNumber = asMsg.xxLogDtlTxt_A1.getValue().split(SCREEN_CTRL_VALUE_SERIAL_NUM_DELIM);
                String[] serialPk = null;
                if (ZYPCommonFunc.hasValue(asMsg.xxLogDtlTxt_A2)) {
                    serialPk = asMsg.xxLogDtlTxt_A2.getValue().split(SCREEN_CTRL_VALUE_SERIAL_NUM_DELIM);
                }
                for (int j = 0; j < serialNumber.length; j++) {
                    NPZC103001_serNumInfoPMsg asmMsg = pMsg.serNumInfo.no(serNumInfoCounter);
                    // QC#19593 Start
                    if (serialPk != null && serialPk.length > j) {
                        ZYPEZDItemValueSetter.setValue(asmMsg.prchReqSerPk, new BigDecimal(serialPk[j]));
                    }
                    // QC#19593 End
                    ZYPEZDItemValueSetter.setValue(asmMsg.prchReqLineNum, asMsg.prchReqLineNum_A1);
                    ZYPEZDItemValueSetter.setValue(asmMsg.prchReqLineSubNum, asMsg.prchReqLineSubNum_A1);
                    ZYPEZDItemValueSetter.setValue(asmMsg.xxDtlLineNum, asMsg.prchReqLineNum_A1);
                    ZYPEZDItemValueSetter.setValue(asmMsg.serNum, serialNumber[j]);
                    serNumInfoCounter++;
                }
            }
        }
        pMsg.prchReqInfo.setValidCount(cntForPRInfo);
        pMsg.serNumInfo.setValidCount(serNumInfoCounter);

        return pMsg;
    }

    /**
     * Creates new pMsg for NPZC103001 mode=update event=submit
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @return NPZC103001PMsg
     */
    private static NPZC103001PMsg createNPZC103001PMsgForSubmitUpdate(NPBL0020SMsg sMsg, String glblCmpyCd) {

        NPZC103001PMsg pMsg = createNPZC103001PMsgForSaveUpdate(sMsg, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqApvlStsCd, PRCH_REQ_APVL_STS.SUBMIT_FOR_APPROVAL);
        return pMsg;
    }

    /**
     * <pre>
     * Execute PR Update Api
     * </pre>
     * @param cMsg NPBL0020CMsg
     * @param pMsg NPZC103001PMsg
     * @return false for error
     */
    private static boolean executeNPZC1030(NPBL0020CMsg cMsg, NPZC103001PMsg pMsg) {

        NPZC103001 dPZC103001 = new NPZC103001();
        dPZC103001.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (pMsg.xxMsgIdList.getValidCount() != 0) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                String messageId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                cMsg.setMessageInfo(messageId);
            }
            return false;
        }
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum, pMsg.prchReqNum);
        return true;
    }

    /**
     * <pre>
     * Update PO
     * </pre>
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @return false for error
     */
    private static boolean updatePO(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {

        // QC#22481
        for (int i = 0; i < sMsg.A.getValidCount(); i++ ) {
            PO_DTLTMsg inMsg = new PO_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.poOrdNum, cMsg.poOrdNum);
            ZYPEZDItemValueSetter.setValue(inMsg.poOrdDtlLineNum, sMsg.A.no(i).trxRefLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);

            PO_DTLTMsg outMsg = (PO_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);

            if (outMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_VALUE_PO });
                return false;
            }

            ZYPEZDItemValueSetter.setValue(outMsg.prchReqNum, cMsg.prchReqNum);
            outMsg.prchReqLineNum.clear();
            outMsg.prchReqLineSubNum.clear();

            EZDTBLAccessor.update(outMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_VALUE_PO });
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return false;
            }
        }
        return true;
    }

    /**
     * get Calendar Type Code
     * @param glblCmpyCd String
     * @param invtyLocCd String
     * @return Calendar Type Code or Error Code
     */
    private static String getCalTpCd(final String glblCmpyCd, final String invtyLocCd) {

        CAL_RELNTMsg calRelnTMsg = null;
        String calTpCd = null;

        if (ZYPCommonFunc.hasValue(invtyLocCd)) {
            calRelnTMsg = new CAL_RELNTMsg();
            calRelnTMsg.glblCmpyCd.setValue(glblCmpyCd);
            calRelnTMsg.calSubTpCd.setValue(CAL_SUB_TP.WAREHOUSE_CALENDAR);
            calRelnTMsg.calMultCd.setValue(invtyLocCd);
            calRelnTMsg = (CAL_RELNTMsg) EZDTBLAccessor.findByKey(calRelnTMsg);
        }

        if (calRelnTMsg != null) {
            calTpCd = calRelnTMsg.calTpCd.getValue();
        } else {
            // Search Company Calendar
            CAL_RELNTMsg calRelnTKey = new CAL_RELNTMsg();
            calRelnTKey.setSQLID("001");
            calRelnTKey.setConditionValue("glblCmpyCd01", glblCmpyCd);
            calRelnTKey.setConditionValue("calSubTpCd01", CAL_SUB_TP.COMPANY_CALENDAR);

            CAL_RELNTMsgArray calRelnTRcd = (CAL_RELNTMsgArray) EZDTBLAccessor.findByCondition(calRelnTKey);

            if (calRelnTRcd == null || calRelnTRcd.length() == 0) {
                calTpCd = NWZM0949E;
            } else if (calRelnTRcd.length() > 1) {
                // When acquire more than two cases,
                // process is finished as master setting error.
                calTpCd = NWZM0673E;
            } else {
                calTpCd = calRelnTRcd.no(0).calTpCd.getValue();
            }
        }

        return calTpCd;
    }

    /**
     * Get Account
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @param rowIndex int
     */
    private static void getAccountForSaveAndSubmit(NPBL0020SMsg sMsg, String glblCmpyCd, int rowIndex) {
        if (PRCH_REQ_TP.WH_TRANSFER.equals(sMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue())) {

            Map<String, Object> ssmParamBranch = new HashMap<String, Object>();
            ssmParamBranch.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParamBranch.put(DB_PARAM_RTL_WH_CD, sMsg.A.no(rowIndex).destRtlWhCd_A1.getValue());
            S21SsmEZDResult resultBranch = NPBL0020Query.getInstance().findAccountBranchFromRtlWH(ssmParamBranch);

            if (resultBranch.isCodeNormal()) {
                List<Map<String, String>> accountBranchList = (List<Map<String, String>>) resultBranch.getResultObject();
                if (accountBranchList.size() == 0) {
                    return;
                }
                Map<String, String> record = accountBranchList.get(0);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaBrCd_A1, (String) record.get(DB_COLUMN_COA_BR_CD));
            }

            Map<String, Object> ssmParamAccount = new HashMap<String, Object>();
            ssmParamAccount.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParamAccount.put(DB_PARAM_MDSE_CD, sMsg.A.no(rowIndex).mdseCd_A1.getValue());
            ssmParamAccount.put(DB_PARAM_PRCH_REQ_TP, sMsg.prchReqTpCd_SL.getValue());
            S21SsmEZDResult resultAccountAcct = NPBL0020Query.getInstance().findAccountAcctFromRtlWH(ssmParamAccount);

            if (resultAccountAcct.isCodeNormal()) {
                List<Map<String, String>> accountAcctList = (List<Map<String, String>>) resultAccountAcct.getResultObject();
                if (accountAcctList.size() == 0) {
                    return;
                }
                Map<String, String> record = accountAcctList.get(0);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaAcctCd_A1, (String) record.get(DB_COLUMN_COA_ACCT_CD));
            }

        } else if (PRCH_REQ_TP.DISPOSAL.equals(sMsg.prchReqTpCd_SL.getValue())) {

            Map<String, Object> ssmParamBranch = new HashMap<String, Object>();
            ssmParamBranch.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParamBranch.put(DB_PARAM_RTL_WH_CD, sMsg.A.no(rowIndex).srcRtlWhCd_A1.getValue());
            S21SsmEZDResult resultBranch = NPBL0020Query.getInstance().findAccountBranchFromRtlWH(ssmParamBranch);

            if (resultBranch.isCodeNormal()) {
                List<Map<String, String>> accountBranchList = (List<Map<String, String>>) resultBranch.getResultObject();
                if (accountBranchList.size() == 0) {
                    return;
                }
                Map<String, String> record = accountBranchList.get(0);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaBrCd_A1, (String) record.get(DB_COLUMN_COA_BR_CD));
            }

            Map<String, Object> ssmParamAccount = new HashMap<String, Object>();
            ssmParamAccount.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParamAccount.put(DB_PARAM_MDSE_CD, sMsg.A.no(rowIndex).mdseCd_A1.getValue());
            ssmParamAccount.put(DB_PARAM_PRCH_REQ_TP, sMsg.prchReqTpCd_SL.getValue());

            S21SsmEZDResult resultAccountAcct = NPBL0020Query.getInstance().findAccountAcctFromRtlWH(ssmParamAccount);

            if (resultAccountAcct.isCodeNormal()) {
                List<Map<String, String>> accountAcctList = (List<Map<String, String>>) resultAccountAcct.getResultObject();
                if (accountAcctList.size() == 0) {
                    return;
                }
                Map<String, String> record = accountAcctList.get(0);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaAcctCd_A1, (String) record.get(DB_COLUMN_COA_ACCT_CD));
            }
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_APP_FUNC_ID, BIZ_APP_ID + sMsg.prchReqTpCd_SL.getValue());

        S21SsmEZDResult result = NPBL0020Query.getInstance().findDefDplyCoaInfo(ssmParam);

        if (result.isCodeNormal()) {
            List<Map<String, String>> defDplyCoaInfoList = (List<Map<String, String>>) result.getResultObject();
            if (defDplyCoaInfoList.size() == 0) {
                return;
            }
            Map<String, String> record = defDplyCoaInfoList.get(0);
            if (PRCH_REQ_TP.WH_TRANSFER.equals(sMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaCmpyCd_A1, (String) record.get(DB_COLUMN_COA_CMPY_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaAfflCd_A1, (String) record.get(DB_COLUMN_COA_AFFL_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaCcCd_A1, (String) record.get(DB_COLUMN_COA_CC_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaProdCd_A2, (String) record.get(DB_COLUMN_COA_PROD_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaChCd_A1, (String) record.get(DB_COLUMN_COA_CH_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaProjCd_A1, (String) record.get(DB_COLUMN_COA_PROJ_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaExtnCd_A1, (String) record.get(DB_COLUMN_COA_EXTN_CD));
            }
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(rowIndex).coaBrCd_A1)) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaBrCd_A1, (String) record.get(DB_COLUMN_COA_BR_CD));
            }
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(rowIndex).coaAcctCd_A1)) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaAcctCd_A1, (String) record.get(DB_COLUMN_COA_ACCT_CD));
            }
        }
    }

    /**
     * Set Ship To Address
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @param shipToCustCd String
     */
    private static boolean setShipToAddressForSaveAndSubmit(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd, String shipToCustCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_SHIP_TO_CUST_CD, shipToCustCd);
        ssmParam.put(DB_PARAM_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);

        S21SsmEZDResult result = NPBL0020Query.getInstance().getShipToAddress(ssmParam);

        if (!result.isCodeNormal()) {
            return false;
        }

        List<Map<String, String>> shipToAddressList = (List<Map<String, String>>) result.getResultObject();
        if (shipToAddressList.size() == 0) {
            return false;
        }
        // QC#22517
    	if (PRCH_REQ_TP.EXPENSE_ORDER.equals(sMsg.prchReqTpCd_SL.getValue())) {
    		// Do Nothing
    	} else {
            Map<String, String> record = shipToAddressList.get(0);
            ZYPEZDItemValueSetter.setValue(sMsg.shipToLocNm, (String) record.get(DB_COLUMN_LOC_NM));
            ZYPEZDItemValueSetter.setValue(sMsg.shipToAddlLocNm, (String) record.get(DB_COLUMN_ADDL_LOC_NM));
            ZYPEZDItemValueSetter.setValue(sMsg.shipToFirstLineAddr, (String) record.get(DB_COLUMN_FIRST_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(sMsg.shipToScdLineAddr, (String) record.get(DB_COLUMN_SCD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(sMsg.shipToThirdLineAddr, (String) record.get(DB_COLUMN_THIRD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(sMsg.shipToFrthLineAddr, (String) record.get(DB_COLUMN_FRTH_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(sMsg.xxShipVndAddr, (String) record.get(DB_COLUMN_SHIP_VND_ADDR));
            ZYPEZDItemValueSetter.setValue(sMsg.shipToPostCd, (String) record.get(DB_COLUMN_POST_CD));
            ZYPEZDItemValueSetter.setValue(sMsg.shipToCtyAddr, (String) record.get(DB_COLUMN_CTY_ADDR));
            ZYPEZDItemValueSetter.setValue(sMsg.shipToCntyNm, (String) record.get(DB_COLUMN_CNTY_NM));
            ZYPEZDItemValueSetter.setValue(sMsg.shipToStCd, (String) record.get(DB_COLUMN_ST_CD));
            ZYPEZDItemValueSetter.setValue(sMsg.shipToProvNm, (String) record.get(DB_COLUMN_PROV_NM));
            ZYPEZDItemValueSetter.setValue(sMsg.shipToCtryCd, (String) record.get(DB_COLUMN_CTRY_CD));
            ZYPEZDItemValueSetter.setValue(sMsg.ctryNm, (String) record.get(DB_COLUMN_CTRY_NM));
    	}

        return true;
    }

    /**
     * Set NLCL0250 Parameters
     * @param cMsg NPBL0020CMsg
     * @param glblCmpyCd String
     */
    public static void setMoveToOnHandInv(NPBL0020CMsg cMsg, String glblCmpyCd) {
        cMsg.P.clear();

        int selectCheckIndex = -1;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).xxChkBox_A1.getValue())) {
                selectCheckIndex = i;
                break;
            }
        }
        // set Warehouse Code
        setMoveToOnHandInvWh(cMsg, glblCmpyCd, selectCheckIndex);

        // Set Detail Paramters
        if (ZYPCommonFunc.hasValue(cMsg.A.no(selectCheckIndex).configTpCd_A1)) {
            // config line
            if (CONFIG_TP.NEW.equals(cMsg.A.no(selectCheckIndex).configTpCd_A1.getValue())) {
                // new config line
                cMsg.P.no(IDX_4).xxPopPrm.clear();
                StringBuilder sbMdseCd = new StringBuilder();
                StringBuilder sbSerNum = new StringBuilder();
                for (int i = selectCheckIndex + 1; i < cMsg.A.getValidCount(); i++) {
                    if (CONFIG_TP.NEW.equals(cMsg.A.no(i).configTpCd_A1.getValue())) {
                        // add config line
                        if (ZYPCommonFunc.hasValue(cMsg.A.no(i).mdseCd_A1)) {
                            if (i > selectCheckIndex) {
                                sbMdseCd.append(",");
                            }
                            sbMdseCd.append(cMsg.A.no(i).mdseCd_A1.getValue());
                        }
                        if (ZYPCommonFunc.hasValue(cMsg.A.no(i).xxLogDtlTxt_A1)) {
                            if (i > selectCheckIndex) {
                                sbSerNum.append(",");
                            }
                            sbSerNum.append(cMsg.A.no(i).xxLogDtlTxt_A1.getValue());
                        }
                    } else {
                        break;
                    }
                }
                if (sbMdseCd.length() > 0) {
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_0).xxPopPrm, sbMdseCd.toString());
                    sbMdseCd.setLength(0);
                }
                if (sbSerNum.length() > 0) {
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_3).xxPopPrm, sbSerNum.toString());
                    sbSerNum.setLength(0);
                }
            } else if (CONFIG_TP.EXISTING.equals(cMsg.A.no(selectCheckIndex).configTpCd_A1.getValue())) {
                // existing config line
                cMsg.P.no(0).xxPopPrm.clear();
                cMsg.P.no(IDX_3).xxPopPrm.clear();
                ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_4).svcConfigMstrPk_P1, cMsg.A.no(selectCheckIndex).svcConfigMstrPk_A1);
            }
        } else {
            // non config line
            ZYPEZDItemValueSetter.setValue(cMsg.P.no(0).xxPopPrm, cMsg.A.no(selectCheckIndex).mdseCd_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_3).xxPopPrm, cMsg.A.no(selectCheckIndex).xxLogDtlTxt_A1);
            cMsg.P.no(IDX_4).xxPopPrm.clear();
        }
    }

    /**
     * <pre>
     * find Destination Rtl WH From Name
     * </pre>
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @param selectCheckIndex int
     */
    private static void setMoveToOnHandInvWh(NPBL0020CMsg cMsg, String glblCmpyCd, int selectCheckIndex) {

        Map<String, Object> ssmParamRtlWh = new HashMap<String, Object>();
        ssmParamRtlWh.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParamRtlWh.put(DB_PARAM_RTL_WH_NM, cMsg.A.no(selectCheckIndex).rtlWhNm_A1.getValue());

        S21SsmEZDResult rtlWhResult = NPBL0020Query.getInstance().findRtlWhFromName(ssmParamRtlWh);

        if (!rtlWhResult.isCodeNormal()) {
            cMsg.P.no(1).xxPopPrm.clear();
        }
        List<Map<String, String>> rtlWhList = (List<Map<String, String>>) rtlWhResult.getResultObject();
        if (rtlWhList.size() != 1) {
            cMsg.P.no(1).xxPopPrm.clear();
        } else {
            Map<String, String> recode = rtlWhList.get(0);
            ZYPEZDItemValueSetter.setValue(cMsg.P.no(1).xxPopPrm, (String) recode.get(DB_COLUMN_RTL_WH_CD));
        }

        Map<String, Object> ssmParamRtlSwh = new HashMap<String, Object>();
        ssmParamRtlSwh.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParamRtlSwh.put(DB_PARAM_RTL_SWH_NM, cMsg.A.no(selectCheckIndex).srcRtlSwhCd_A1.getValue());
        ssmParamRtlSwh.put(DB_PARAM_RTL_WH_CD, cMsg.P.no(1).xxPopPrm.getValue());

        S21SsmEZDResult rtlSwhResult = NPBL0020Query.getInstance().findRtlSwhFromName(ssmParamRtlSwh);
        if (!rtlSwhResult.isCodeNormal()) {
            cMsg.P.no(2).xxPopPrm.clear();
        }
        List<Map<String, String>> rtlSwhList = (List<Map<String, String>>) rtlSwhResult.getResultObject();
        if (rtlSwhList.size() != 1) {
            cMsg.P.no(2).xxPopPrm.clear();
        } else {
            Map<String, String> recode = rtlSwhList.get(0);
            ZYPEZDItemValueSetter.setValue(cMsg.P.no(2).xxPopPrm, (String) recode.get(DB_COLUMN_RTL_SWH_CD));
        }
    }

    /**
     * Check
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static void checkForOpenSerialEntryPopup(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd, int indexS) {

        setWarehouseToConfigItemLine(sMsg, glblCmpyCd);

        // Source WH
        if (PRCH_REQ_TP.WH_TRANSFER.equals(cMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.DISPOSAL.equals(cMsg.prchReqTpCd_SL.getValue())) {

            if (ZYPCommonFunc.hasValue(sMsg.A.no(indexS).rtlWhNm_A1)) {
                int count = checkSrcRtlWhFromName(glblCmpyCd, sMsg.A.no(indexS).rtlWhNm_A1, sMsg.A.no(indexS).srcRtlWhCd_A1, sMsg.A.no(indexS).rtlWhCatgCd_A1);
                if (count == 0) {
                    if (ZYPCommonFunc.hasValue(sMsg.A.no(indexS).configTpCd_A1)) {
                        int configLineIndex = getIndexForConfigLine(sMsg, indexS);
                        if (configLineIndex >= 0) {
                            sMsg.A.no(configLineIndex).rtlWhNm_A1.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                            setPageShowFromNumOnCMsg(cMsg, sMsg, configLineIndex);
                        } else {
                            sMsg.A.no(indexS).rtlWhNm_A1.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                        }
                    } else {
                        sMsg.A.no(indexS).rtlWhNm_A1.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                    }
                }
                if (count > 1) {
                    if (ZYPCommonFunc.hasValue(sMsg.A.no(indexS).configTpCd_A1)) {
                        int configLineIndex = getIndexForConfigLine(sMsg, indexS);
                        if (configLineIndex >= 0) {
                            sMsg.A.no(configLineIndex).rtlWhNm_A1.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                            setPageShowFromNumOnCMsg(cMsg, sMsg, configLineIndex);
                        } else {
                            sMsg.A.no(indexS).rtlWhNm_A1.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                        }
                    } else {
                        sMsg.A.no(indexS).rtlWhNm_A1.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                    }
                }
            } else {
                if (ZYPCommonFunc.hasValue(sMsg.A.no(indexS).configTpCd_A1)) {
                    int configLineIndex = getIndexForConfigLine(sMsg, indexS);
                    if (configLineIndex >= 0) {
                        sMsg.A.no(configLineIndex).rtlWhNm_A1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                        setPageShowFromNumOnCMsg(cMsg, sMsg, configLineIndex);
                    } else {
                        sMsg.A.no(indexS).rtlWhNm_A1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                    }
                } else {
                    sMsg.A.no(indexS).rtlWhNm_A1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                }
            }
        } else {
            if (!ZYPCommonFunc.hasValue(cMsg.srcRtlWhCd) && !ZYPCommonFunc.hasValue(cMsg.rtlWhNm_SW)) {
                cMsg.srcRtlWhCd.setErrorInfo(1, ZZZM9025E, new String[] {"Source WH Code or Name" });
                cMsg.rtlWhNm_SW.setErrorInfo(1, ZZZM9025E, new String[] {"Source WH Code or Name" });

            } else {
                if (!ZYPCommonFunc.hasValue(cMsg.srcRtlWhCd) && ZYPCommonFunc.hasValue(cMsg.rtlWhNm_SW)) {
                    int count = checkSrcRtlWhFromName(glblCmpyCd, sMsg.rtlWhNm_SW, sMsg.srcRtlWhCd, sMsg.rtlWhCatgCd_SW);
                    if (count == 0) {
                        cMsg.rtlWhNm_SW.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_SW });
                    } else if (count > 1) {
                        cMsg.rtlWhNm_SW.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_RTL_WH_NM_SW });
                    } else {
                        ZYPEZDItemValueSetter.setValue(cMsg.srcRtlWhCd, sMsg.srcRtlWhCd);
                    }
                }
            }
        }

        // Source Sub-WH
        if (PRCH_REQ_TP.VENDOR_RETURN.equals(cMsg.prchReqTpCd_SL.getValue())) {
            if (!ZYPCommonFunc.hasValue(sMsg.srcRtlSwhCd)) {
                cMsg.srcRtlSwhCd.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_SRC_RTL_SWH_CD });
            }
        } else {
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(indexS).srcRtlSwhCd_A1)) {
                if (ZYPCommonFunc.hasValue(sMsg.A.no(indexS).configTpCd_A1)) {
                    int configLineIndex = getIndexForConfigLine(sMsg, indexS);
                    if (configLineIndex >= 0) {
                        sMsg.A.no(configLineIndex).srcRtlSwhCd_A1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_SRC_RTL_SWH_CD_LINE });
                        setPageShowFromNumOnCMsg(cMsg, sMsg, configLineIndex);
                    } else {
                        sMsg.A.no(indexS).srcRtlSwhCd_A1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_SRC_RTL_SWH_CD_LINE });
                    }
                } else {
                    sMsg.A.no(indexS).srcRtlSwhCd_A1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_SRC_RTL_SWH_CD_LINE });
                }
            }
        }

        // Check Qty
        if (sMsg.A.no(indexS).prchReqDispQty_A1.getValueInt() <= 0) {
            sMsg.A.no(indexS).prchReqDispQty_A1.setErrorInfo(1, NPAM0046E, new String[] {DISPLAY_NM_PRCH_REQ_DISP_QTY });
        }

        // Check Item
        if (ZYPCommonFunc.hasValue(sMsg.A.no(indexS).mdseCd_A1)) {
            // QC#17098 Mod.
            checkItemNumber(sMsg.A.no(indexS), glblCmpyCd);
//            // QC#9050
//            ALL_MDSE_VTMsg tMsg = checkItemNumber(sMsg.A.no(indexS), glblCmpyCd);
//            if (tMsg == null) {
//                sMsg.A.no(indexS).mdseCd_A1.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_MDSE_CD });
//            } else if (ZYPConstant.FLG_OFF_N.equals(tMsg.invtyCtrlFlg.getValue())) {
//                sMsg.A.no(indexS).mdseCd_A1.setErrorInfo(1, NPAM0084E, null);
//            }
        } else {
            sMsg.A.no(indexS).mdseCd_A1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_MDSE_CD });
        }
    }

    /**
     * Check
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkForOpenAccountPopup(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd, int indexS) {

        boolean noErrorFlag = true;

        setWarehouseToConfigItemLine(sMsg, glblCmpyCd);

        // START 2018/04/03 S.Katsuma [QC#3521,25063,ADD]
        // In consideration of changing WH Name by manual, update WH Code.
        if (ZYPCommonFunc.hasValue(sMsg.A.no(indexS).rtlWhNm_A1)) {
            setRtlWhFromName(glblCmpyCd, sMsg.A.no(indexS).rtlWhNm_A1, sMsg.A.no(indexS).srcRtlWhCd_A1, sMsg.A.no(indexS).rtlWhCatgCd_A1);
        }
        if (ZYPCommonFunc.hasValue(sMsg.A.no(indexS).rtlWhNm_A2)) {
            setRtlWhFromName(glblCmpyCd, sMsg.A.no(indexS).rtlWhNm_A2, sMsg.A.no(indexS).destRtlWhCd_A1, sMsg.A.no(indexS).rtlWhCatgCd_A2);
        }
        // In consideration of changing Ship To Cust Name by manual, update Ship To Cust Code.
        if (getShipToCustCdForDetail(glblCmpyCd, cMsg, sMsg, indexS)) {
            noErrorFlag = false;
        }
        // END 2018/04/03 S.Katsuma [QC#3521,25063,ADD]

        // Destination WH
        if (PRCH_REQ_TP.VENDOR_RETURN.equals(cMsg.prchReqTpCd_SL.getValue())) {
            if (!ZYPCommonFunc.hasValue(cMsg.vndCd) && !ZYPCommonFunc.hasValue(cMsg.dplyVndNm)) {
                cMsg.vndCd.setErrorInfo(1, ZZZM9025E, new String[] {"Ship To Supplier Code or Name" });
                cMsg.dplyVndNm.setErrorInfo(1, ZZZM9025E, new String[] {"Ship To Supplier Code or Name" });
                noErrorFlag = false;
            } else {
                if (ZYPCommonFunc.hasValue(sMsg.vndCd)) {
                    if (checkShipToSupplier(sMsg, glblCmpyCd)) {
                        ZYPEZDItemValueSetter.setValue(cMsg.dplyVndNm, sMsg.dplyVndNm);
                    } else {
                        cMsg.vndCd.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_VND_CD });
                        noErrorFlag = false;
                    }
                } else if (ZYPCommonFunc.hasValue(sMsg.dplyVndNm)) {
                    int count = checkShipToSupplierFromName(sMsg, glblCmpyCd);
                    if (count == 0) {
                        cMsg.dplyVndNm.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_DPLY_VND_NM });
                        noErrorFlag = false;
                    } else if (count > 1) {
                        cMsg.dplyVndNm.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_DPLY_VND_NM });
                        noErrorFlag = false;
                    } else {
                        ZYPEZDItemValueSetter.setValue(cMsg.vndCd, sMsg.vndCd);
                    }
                }
            }

        } else if (PRCH_REQ_TP.WH_TRANSFER.equals(cMsg.prchReqTpCd_SL.getValue())) {

            if (ZYPCommonFunc.hasValue(sMsg.A.no(indexS).rtlWhNm_A2)) {
                int count = checkDestRtlWhFromName(glblCmpyCd, sMsg.A.no(indexS).rtlWhNm_A2, sMsg.A.no(indexS).destRtlWhCd_A1, sMsg.A.no(indexS).rtlWhCatgCd_A1);
                if (count == 0) {
                    if (ZYPCommonFunc.hasValue(sMsg.A.no(indexS).configTpCd_A1)) {
                        int configLineIndex = getIndexForConfigLine(sMsg, indexS);
                        if (configLineIndex >= 0) {
                            sMsg.A.no(configLineIndex).rtlWhNm_A2.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW });
                            setPageShowFromNumOnCMsg(cMsg, sMsg, configLineIndex);
                            noErrorFlag = false;
                        } else {
                            sMsg.A.no(indexS).rtlWhNm_A2.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW });
                            noErrorFlag = false;
                        }
                    } else {
                        sMsg.A.no(indexS).rtlWhNm_A2.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW });
                        noErrorFlag = false;
                    }
                }
                if (count > 1) {
                    if (ZYPCommonFunc.hasValue(sMsg.A.no(indexS).configTpCd_A1)) {
                        int configLineIndex = getIndexForConfigLine(sMsg, indexS);
                        if (configLineIndex >= 0) {
                            sMsg.A.no(configLineIndex).rtlWhNm_A2.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW });
                            setPageShowFromNumOnCMsg(cMsg, sMsg, configLineIndex);
                            noErrorFlag = false;
                        } else {
                            sMsg.A.no(indexS).rtlWhNm_A2.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW });
                            noErrorFlag = false;
                        }
                    } else {
                        sMsg.A.no(indexS).rtlWhNm_A2.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW });
                        noErrorFlag = false;
                    }
                }
            } else {
                if (ZYPCommonFunc.hasValue(sMsg.A.no(indexS).configTpCd_A1)) {
                    int configLineIndex = getIndexForConfigLine(sMsg, indexS);
                    if (configLineIndex >= 0) {
                        sMsg.A.no(configLineIndex).rtlWhNm_A2.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW });
                        setPageShowFromNumOnCMsg(cMsg, sMsg, configLineIndex);
                        noErrorFlag = false;
                    } else {
                        sMsg.A.no(indexS).rtlWhNm_A2.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW });
                        noErrorFlag = false;
                    }
                } else {
                    sMsg.A.no(indexS).rtlWhNm_A2.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_DW });
                    noErrorFlag = false;
                }
            }
        } else if (PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue())) {
            if (!ZYPCommonFunc.hasValue(cMsg.destRtlWhCd) && !ZYPCommonFunc.hasValue(cMsg.rtlWhNm_DW)) {
                cMsg.destRtlWhCd.setErrorInfo(1, ZZZM9025E, new String[] {"Destination WH Code or Name" });
                cMsg.rtlWhNm_DW.setErrorInfo(1, ZZZM9025E, new String[] {"Destination WH Code or Name" });
                noErrorFlag = false;
            } else {
                if (!ZYPCommonFunc.hasValue(cMsg.destRtlWhCd) && ZYPCommonFunc.hasValue(cMsg.rtlWhNm_DW)) {
                    int count = checkDestRtlWhFromName(glblCmpyCd, sMsg.rtlWhNm_DW, sMsg.destRtlWhCd, sMsg.rtlWhCatgCd_SW);
                    if (count == 0) {
                        cMsg.rtlWhNm_DW.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_DW });
                        noErrorFlag = false;
                    } else if (count > 1) {
                        cMsg.rtlWhNm_DW.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_RTL_WH_NM_DW });
                        noErrorFlag = false;
                    } else {
                        ZYPEZDItemValueSetter.setValue(cMsg.destRtlWhCd, sMsg.destRtlWhCd);
                    }
                }
            }
        } else if (PRCH_REQ_TP.DISPOSAL.equals(cMsg.prchReqTpCd_SL.getValue())) {
            if (ZYPCommonFunc.hasValue(sMsg.A.no(indexS).rtlWhNm_A1)) {
                int count = checkSrcRtlWhFromName(glblCmpyCd, sMsg.A.no(indexS).rtlWhNm_A1, sMsg.A.no(indexS).srcRtlWhCd_A1, sMsg.A.no(indexS).rtlWhCatgCd_A1);
                if (count == 0) {
                    if (ZYPCommonFunc.hasValue(sMsg.A.no(indexS).configTpCd_A1)) {
                        int configLineIndex = getIndexForConfigLine(sMsg, indexS);
                        if (configLineIndex >= 0) {
                            sMsg.A.no(configLineIndex).rtlWhNm_A1.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                            setPageShowFromNumOnCMsg(cMsg, sMsg, configLineIndex);
                            noErrorFlag = false;
                        } else {
                            sMsg.A.no(indexS).rtlWhNm_A1.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                            noErrorFlag = false;
                        }
                    } else {
                        sMsg.A.no(indexS).rtlWhNm_A1.setErrorInfo(1, NPAM1361E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                        noErrorFlag = false;
                    }
                }
                if (count > 1) {
                    if (ZYPCommonFunc.hasValue(sMsg.A.no(indexS).configTpCd_A1)) {
                        int configLineIndex = getIndexForConfigLine(sMsg, indexS);
                        if (configLineIndex >= 0) {
                            sMsg.A.no(configLineIndex).rtlWhNm_A1.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                            setPageShowFromNumOnCMsg(cMsg, sMsg, configLineIndex);
                            noErrorFlag = false;
                        } else {
                            sMsg.A.no(indexS).rtlWhNm_A1.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                            noErrorFlag = false;
                        }
                    } else {
                        sMsg.A.no(indexS).rtlWhNm_A1.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                        noErrorFlag = false;
                    }
                }
            } else {
                if (ZYPCommonFunc.hasValue(sMsg.A.no(indexS).configTpCd_A1)) {
                    int configLineIndex = getIndexForConfigLine(sMsg, indexS);
                    if (configLineIndex >= 0) {
                        sMsg.A.no(configLineIndex).rtlWhNm_A1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                        setPageShowFromNumOnCMsg(cMsg, sMsg, configLineIndex);
                        noErrorFlag = false;
                    } else {
                        sMsg.A.no(indexS).rtlWhNm_A1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                        noErrorFlag = false;
                    }
                } else {
                    sMsg.A.no(indexS).rtlWhNm_A1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_RTL_WH_NM_LINE_SW });
                    noErrorFlag = false;
                }
            }
        // QC22518
        } else if (PRCH_REQ_TP.EXPENSE_ORDER.equals(cMsg.prchReqTpCd_SL.getValue())) {

            if (!ZYPCommonFunc.hasValue(sMsg.A.no(indexS).configTpCd_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(indexS).shipToLocNm_E1)) {

                // Item Line
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(indexS).xxScrItem50Txt_A1)) {
                    int count = getShipToCustCd(glblCmpyCd, sMsg.A.no(indexS).shipToCustCd_E1, sMsg.A.no(indexS).shipToLocNm_E1);
                    if (count == 0) {
                        sMsg.A.no(indexS).shipToCustCd_E1.clear();
                        cMsg.P.clear();
                        count = getShipToCustCd(glblCmpyCd, sMsg.A.no(indexS).shipToCustCd_E1, sMsg.A.no(indexS).shipToLocNm_E1);
                        if (count == 0) {
                            noErrorFlag = false;
                        }
                    }
    
                    if (noErrorFlag && count > 1 && !ZYPCommonFunc.hasValue(sMsg.A.no(indexS).shipToCustCd_E1)) {
                        sMsg.A.no(indexS).shipToLocNm_E1.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_SHIP_TO_LOC_NM });
                        setPageShowFromNumOnCMsg(cMsg, sMsg, indexS);
                        noErrorFlag = false;
                    }
                }

            } else if (ZYPCommonFunc.hasValue(sMsg.A.no(indexS).configTpCd_A1)) {
                // Config Item Line
                int configLineIndex = getIndexForConfigLine(sMsg, indexS);
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(configLineIndex).xxScrItem50Txt_A1)) {
                    int count = getShipToCustCd(glblCmpyCd, sMsg.A.no(configLineIndex).shipToCustCd_E1, sMsg.A.no(configLineIndex).shipToLocNm_E1);
                    if (count == 0) {
                        sMsg.A.no(configLineIndex).shipToCustCd_E1.clear();
                        sMsg.A.no(indexS).shipToCustCd_E1.clear();
                        cMsg.P.clear();
                        count = getShipToCustCd(glblCmpyCd, sMsg.A.no(configLineIndex).shipToCustCd_E1, sMsg.A.no(configLineIndex).shipToLocNm_E1);
                        if (count == 0) {
                            noErrorFlag = false;
                        }
                    }
    
                    if (noErrorFlag && count > 1 && !ZYPCommonFunc.hasValue(sMsg.A.no(configLineIndex).shipToCustCd_E1)) {
                        sMsg.A.no(configLineIndex).shipToLocNm_E1.setErrorInfo(1, NPBM0001E, new String[] {DISPLAY_NM_SHIP_TO_LOC_NM });
                        setPageShowFromNumOnCMsg(cMsg, sMsg, configLineIndex);
                        noErrorFlag = false;
                    }
                }
            }
        }

        NPBL0020CommonLogic.copyFromSMsgDetailOntoCmsgDetail(cMsg, sMsg);
        return noErrorFlag;
    }

    /**
     * <pre>
     * Get index for Config line
     * </pre>
     * @param sMsg NPBL0020CMsg
     * @param targetSMsgIndex int
     */
    private static int getIndexForConfigLine(NPBL0020SMsg sMsg, int targetSMsgIndex) {

        for (int i = targetSMsgIndex; i >= 0; i--) {
            if (BigDecimal.ZERO.compareTo(sMsg.A.no(i).prchReqLineSubNum_A1.getValue()) == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * <pre>
     * Get Config Item Line Info
     * </pre>
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @return Map
     */
    private static Map<String, Object> getConfigItemLineInfo(String glblCmpyCd, NPBL0020_ASMsg lineMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", lineMsg.mdseCd_A1.getValue());
        ssmParam.put("mdseTpCtxTpCd", MDSE_TP_CTX_TP.MAIN_MACHINE);

        S21SsmEZDResult result = NPBL0020Query.getInstance().getConfigItemLineInfo(ssmParam);

        if (!result.isCodeNormal()) {
            return null;
        }
        List<Map<String, String>> additionalLineInfoList = (List<Map<String, String>>) result.getResultObject();

        Map<String, Object> additionalLineInfoMap = new HashMap<String, Object>();
        additionalLineInfoMap.put(MAP_KEY_PRCH_REQ_LINE_NUM, lineMsg.prchReqLineNum_A1.getValue());
        additionalLineInfoMap.put(MAP_KEY_PRCH_REQ_LINE_SUB_NUM, lineMsg.prchReqLineSubNum_A1.getValue());
        additionalLineInfoMap.put(MAP_KEY_PRCH_REQ_DISP_QTY, lineMsg.prchReqDispQty_A1.getValue());
        additionalLineInfoMap.put(MAP_KEY_MDSE_CD, lineMsg.mdseCd_A1.getValue());
        additionalLineInfoMap.put(MAP_KEY_INSTL_BASE_CTRL_FLG, (String) additionalLineInfoList.get(0).get(DB_COLUMN_INSTL_BASE_CTRL_FLG));
        additionalLineInfoMap.put(MAP_KEY_MDSE_TP_CTX_TP_CD, (String) additionalLineInfoList.get(0).get(DB_COLUMN_MDSE_TP_CTX_TP_CD));
        additionalLineInfoMap.put(MAP_KEY_SHPG_SER_TAKE_FLG, (String) additionalLineInfoList.get(0).get(DB_COLUMN_SHPG_SER_TAKE_FLG));

        return additionalLineInfoMap;
    }

    /**
     * <pre>
     * Call Serial Validation API(NLZC4030)
     * </pre>
     * @param lineMsg NPBL0020_ASMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private static boolean callSerialValidationAPI(NPBL0020_ASMsg lineMsg, String glblCmpyCd) {

        if (!ZYPCommonFunc.hasValue(lineMsg.xxLogDtlTxt_A1)) {
            return true;
        }

        String[] serialNumber = lineMsg.xxLogDtlTxt_A1.getValue().split(SCREEN_CTRL_VALUE_SERIAL_NUM_DELIM);

        NLZC403001 api = new NLZC403001();
        for (int j = 0; j < serialNumber.length; j++) {
            NLZC403001PMsg pMsg = new NLZC403001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, SER_VAL_API_MODE_OUTBOUND);
            ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, lineMsg.mdseCd_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.serNum, serialNumber[j]);
            ZYPEZDItemValueSetter.setValue(pMsg.whCd, S21StringUtil.concatStrings(lineMsg.srcRtlWhCd_A1.getValue(), lineMsg.srcRtlSwhCd_A1.getValue()));
            api.execute(pMsg, ONBATCH_TYPE.ONLINE);

            if (SER_VAL_API_RETURN_CODE_ERROR.equals(pMsg.xxRsltStsCd.getValue())) {
                if (S21ApiUtil.isXxMsgId(pMsg)) {
                    List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                    for (int i = 0; i < msgList.size(); i++) {
                        S21ApiMessage msg = msgList.get(i);
                        String msgId = msg.getXxMsgid();

                        if (msgId.endsWith("E")) {
                            String[] msgPrms = msg.getXxMsgPrmArray();
                            lineMsg.xxLogDtlTxt_A1.setErrorInfo(1, msgId, msgPrms);
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * <pre>
     * Check Config Line
     * </pre>
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private static boolean checkConfigLine(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {
        if (!PRCH_REQ_TP.WH_TRANSFER.equals(sMsg.prchReqTpCd_SL.getValue())) {
            return true;
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).configTpCd_A1)) {
                continue;
            }

            // Check Base Component existence
            List<Map<String, Object>> configItemLineInfoMapList = new ArrayList<Map<String, Object>>();
            int configLineStartIndex = i;
            int configLineEndIndex = i + 1;
            String lineNumForConfigLine = sMsg.A.no(i).prchReqLineNum_A1.getValue();

            for (int j = i + 1; j < sMsg.A.getValidCount(); j++) {

                if (!lineNumForConfigLine.equals(sMsg.A.no(j).prchReqLineNum_A1.getValue())) {
                    configLineEndIndex = j - 1;
                    i = j - 1;
                    break;
                }

                Map<String, Object> configItemLineInfoMap = getConfigItemLineInfo(glblCmpyCd, sMsg.A.no(j));
                if (configItemLineInfoMap != null) {
                    configItemLineInfoMapList.add(configItemLineInfoMap);
                }
                i++;
            }

            Map<String, Object> baseComponentLineMap = null;

            for (int j = 0; j < configItemLineInfoMapList.size(); j++) {
                Map<String, Object> configItemLineInfoMap = (Map<String, Object>) configItemLineInfoMapList.get(j);
                String mdseTpCtxTpCd = (String) configItemLineInfoMap.get(MAP_KEY_MDSE_TP_CTX_TP_CD);
                if (ZYPCommonFunc.hasValue(mdseTpCtxTpCd)) {
                    baseComponentLineMap = configItemLineInfoMap;
                    break;
                }
                String instlBaseCtrlFlg = (String) configItemLineInfoMap.get(MAP_KEY_INSTL_BASE_CTRL_FLG);
                if (ZYPConstant.FLG_ON_Y.equals(instlBaseCtrlFlg)) {
                    if (baseComponentLineMap == null) {
                        baseComponentLineMap = configItemLineInfoMap;
                    }
                }
            }
            if (baseComponentLineMap == null) {
                for (int j = configLineStartIndex + 1; j <= configLineEndIndex; j++) {
                    sMsg.A.no(j).mdseCd_A1.setErrorInfo(1, NWZM1482E);
                }
                setPageShowFromNumOnCMsg(cMsg, sMsg, configLineStartIndex + 1);
                return false;
            }

            // Check Qty for Base Component and Serialized Item
            boolean checkQtyErrorFlag = false;
            BigDecimal prchReqLineSubNumForBaseComponent = (BigDecimal) baseComponentLineMap.get(MAP_KEY_PRCH_REQ_LINE_SUB_NUM);
            for (int j = 0; j < configItemLineInfoMapList.size(); j++) {
                Map<String, Object> configItemLineInfoMap = (Map<String, Object>) configItemLineInfoMapList.get(j);

                String prchReqLineNum = (String) configItemLineInfoMap.get(MAP_KEY_PRCH_REQ_LINE_NUM);
                BigDecimal prchReqLineSubNum = (BigDecimal) configItemLineInfoMap.get(MAP_KEY_PRCH_REQ_LINE_SUB_NUM);
                String shpgSerTakeFlg = (String) configItemLineInfoMap.get(MAP_KEY_SHPG_SER_TAKE_FLG);
                BigDecimal prchReqDispQty = (BigDecimal) configItemLineInfoMap.get(MAP_KEY_PRCH_REQ_DISP_QTY);

                if (prchReqLineSubNumForBaseComponent.compareTo(prchReqLineSubNum) == 0 && BigDecimal.ONE.compareTo(prchReqDispQty) != 0) {

                    for (int k = configLineStartIndex + 1; k <= configLineEndIndex; k++) {
                        if (prchReqLineNum.equals(sMsg.A.no(k).prchReqLineNum_A1.getValue()) && prchReqLineSubNum.compareTo(sMsg.A.no(k).prchReqLineSubNum_A1.getValue()) == 0) {
                            sMsg.A.no(k).prchReqDispQty_A1.setErrorInfo(1, NPAM1534E);
                            if (!checkQtyErrorFlag) {
                                setPageShowFromNumOnCMsg(cMsg, sMsg, k);
                                checkQtyErrorFlag = true;
                            }
                        }
                    }
                } else if (ZYPConstant.FLG_ON_Y.equals(shpgSerTakeFlg) && BigDecimal.ONE.compareTo(prchReqDispQty) != 0) {

                    for (int k = configLineStartIndex + 1; k <= configLineEndIndex; k++) {
                        if (prchReqLineNum.equals(sMsg.A.no(k).prchReqLineNum_A1.getValue()) && prchReqLineSubNum.compareTo(sMsg.A.no(k).prchReqLineSubNum_A1.getValue()) == 0) {
                            sMsg.A.no(k).prchReqDispQty_A1.setErrorInfo(1, NPAM1535E);
                            if (!checkQtyErrorFlag) {
                                setPageShowFromNumOnCMsg(cMsg, sMsg, k);
                                checkQtyErrorFlag = true;
                            }
                        }
                    }
                }
            }
            if (checkQtyErrorFlag) {
                return false;
            }

            // Call Service Model API
            if (!callServiceModelAPI(cMsg, sMsg, configItemLineInfoMapList, baseComponentLineMap, glblCmpyCd)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <pre>
     * Call Service Model API(NSZC0480)
     * </pre>
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param configItemLineInfoMapList List<Map<String, Object>>
     * @param baseComponentLineMap Map<String, Object>
     * @param glblCmpyCd String
     * @return boolean
     */
    private static boolean callServiceModelAPI(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, List<Map<String, Object>> configItemLineInfoMapList, Map<String, Object> baseComponentLineMap, String glblCmpyCd) {

        String prchReqLineNumForBaseComponent = (String) baseComponentLineMap.get(MAP_KEY_PRCH_REQ_LINE_NUM);
        BigDecimal prchReqLineSubNumForBaseComponent = (BigDecimal) baseComponentLineMap.get(MAP_KEY_PRCH_REQ_LINE_SUB_NUM);

        NSZC048001PMsg pMsg = new NSZC048001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));

        for (int i = 0; i < configItemLineInfoMapList.size(); i++) {
            Map<String, Object> configItemLineInfoMap = (Map<String, Object>) configItemLineInfoMapList.get(i);

            BigDecimal prchReqLineSubNum = (BigDecimal) configItemLineInfoMap.get(MAP_KEY_PRCH_REQ_LINE_SUB_NUM);
            String mdseCd = (String) configItemLineInfoMap.get(MAP_KEY_MDSE_CD);

            if (prchReqLineSubNumForBaseComponent.compareTo(prchReqLineSubNum) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.prntMdseCd, mdseCd);

            } else {
                int ix = pMsg.xxChildMdseCdList.getValidCount();
                ZYPEZDItemValueSetter.setValue(pMsg.xxChildMdseCdList.no(ix++).childMdseCd, mdseCd);
                pMsg.xxChildMdseCdList.setValidCount(ix);
            }
        }
        NSZC048001 smApi = new NSZC048001();
        smApi.execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();

                if (msgId.endsWith("E")) {
                    String[] msgPrms = msg.getXxMsgPrmArray();

                    for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                        String prchReqLineNum = sMsg.A.no(j).prchReqLineNum_A1.getValue();
                        BigDecimal prchReqLineSubNum = sMsg.A.no(j).prchReqLineSubNum_A1.getValue();

                        if (prchReqLineNumForBaseComponent.equals(prchReqLineNum) && prchReqLineSubNumForBaseComponent.compareTo(prchReqLineSubNum) == 0) {
                            sMsg.A.no(j).mdseCd_A1.setErrorInfo(1, msgId, msgPrms);
                            setPageShowFromNumOnCMsg(cMsg, sMsg, j);
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * getShipToCustName
     * @param glblCmpyCd String
     * @param shipToCustNm String
     * @return shipToCustName or null
     */
    public static String getShipToCustName(String glblCmpyCd, String shipToCustCd) {

        HashMap<String, String> param = new HashMap<String, String>();
        param.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        param.put(DB_PARAM_SHIP_TO_CUST_CD, shipToCustCd);

        S21SsmEZDResult result = NPBL0020Query.getInstance().getShipToCustName(param);

        if (result.isCodeNormal()) {

            return (String) result.getResultObject();
        }

        return null;
    }

    /**
     * <pre>
     * getShipToCustCd
     * </pre>
     * @param glblCmpyCd String
     * @param shipToCustCd EZDSStringItem
     * @param shipToLocNmd EZDSStringItem
     * @return result count
     */
    private static int getShipToCustCd(String glblCmpyCd, EZDSStringItem shipToCustCd, EZDSStringItem shipToLocNm) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(shipToLocNm)) {
            ssmParam.put(DB_PARAM_DS_ACCT_NM, shipToLocNm.getValue());
        }
        if (ZYPCommonFunc.hasValue(shipToCustCd)) {
            ssmParam.put(DB_PARAM_SHIP_TO_CUST_CD, shipToCustCd.getValue());
        }

        S21SsmEZDResult result = NPBL0020Query.getInstance().getShipToCustCd(ssmParam);

        if (!result.isCodeNormal()) {
            return 0;
        }
        List<Map<String, String>> shipToCustCdList = (List<Map<String, String>>) result.getResultObject();
        if (shipToCustCdList.size() != 1) {
            return shipToCustCdList.size();
        }
        Map<String, String> recode = shipToCustCdList.get(0);
        ZYPEZDItemValueSetter.setValue(shipToCustCd, (String) recode.get(DB_COLUMN_SHIP_TO_CUST_CD));
        return shipToCustCdList.size();
    }

    /**
     * <pre>
     * getShipToCustCd
     * </pre>
     * @param glblCmpyCd String
     * @param shipToCustCd EZDCStringItem
     * @param shipToLocNmd EZDCStringItem
     * @return result count
     */
    public static int getShipToCustCd(String glblCmpyCd, EZDCStringItem shipToCustCd, EZDCStringItem shipToLocNm) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(shipToLocNm)) {
            ssmParam.put(DB_PARAM_DS_ACCT_NM, shipToLocNm.getValue());
        }
        if (ZYPCommonFunc.hasValue(shipToCustCd)) {
            ssmParam.put(DB_PARAM_SHIP_TO_CUST_CD, shipToCustCd.getValue());
        }

        S21SsmEZDResult result = NPBL0020Query.getInstance().getShipToCustCd(ssmParam);

        if (!result.isCodeNormal()) {
            return 0;
        }
        List<Map<String, String>> shipToCustCdList = (List<Map<String, String>>) result.getResultObject();
        if (shipToCustCdList.size() != 1) {
            return shipToCustCdList.size();
        }
        Map<String, String> recode = shipToCustCdList.get(0);
        ZYPEZDItemValueSetter.setValue(shipToCustCd, (String) recode.get(DB_COLUMN_SHIP_TO_CUST_CD));
        return shipToCustCdList.size();
    }

    /**
     * <pre>
     * Set To ConfigItemLine
     * </pre>
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     */
    private static void setConfigItemLine(NPBL0020SMsg sMsg, String glblCmpyCd) {

        int configLineIndex = -1;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).configTpCd_A1)) {
                if (sMsg.A.no(i).prchReqLineSubNum_A1.getValueInt() == 0) {
                    configLineIndex = i;
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqLineTpCd_A1, sMsg.A.no(configLineIndex).prchReqLineTpCd_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).shipToCustCd_E1, sMsg.A.no(configLineIndex).shipToCustCd_E1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).shipToLocNm_E1, sMsg.A.no(configLineIndex).shipToLocNm_E1);
                }
            }
        }
    }

    /**
     * get CCY_CD
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     */
    public static void getCcyCd(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);

        // Execute
        S21SsmEZDResult result = NPBL0020Query.getInstance().getCcyCd(ssmParam);

        if (result.isCodeNormal()) {
            ZYPEZDItemValueSetter.setValue(cMsg.aftDeclPntDigitNum, (BigDecimal) result.getResultObject());
            ZYPEZDItemValueSetter.setValue(sMsg.aftDeclPntDigitNum, (BigDecimal) result.getResultObject());
        }
    }

    /**
     * No Search Result Initialize Process
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     */
    private static void noSearchResultInit(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {
        getCcyCd(cMsg, sMsg, glblCmpyCd);
        createPullDownRequestedShipMethod(cMsg, sMsg, glblCmpyCd);
        createPullDownReturnReason(cMsg, sMsg, glblCmpyCd);
        createPullDownStockStatus(cMsg, sMsg, glblCmpyCd);
        getLineStatusName(cMsg, sMsg, glblCmpyCd);
        getConfigTypeName(cMsg, sMsg, glblCmpyCd);

        ZYPEZDItemValueSetter.setValue(cMsg.prchReqStsCd, PRCH_REQ_STS.OPEN);
        getDefaultHeaderStatusName(cMsg, sMsg, glblCmpyCd);

        ZYPEZDItemValueSetter.setValue(cMsg.prchReqApvlStsCd, PRCH_REQ_APVL_STS.ENTERED);
        getDefaultApprovalStatusName(cMsg, sMsg, glblCmpyCd);

        ZYPEZDItemValueSetter.setValue(cMsg.prchReqCratDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpCd, PRCH_REQ_SRC_TP.INVENTORY_REQUEST_ENTRY);
        ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        getDefaultDocumentSourceTypeName(cMsg, sMsg, glblCmpyCd);
        createPullDownRequisitionType(cMsg, sMsg, glblCmpyCd, true);

        if (ZYPCommonFunc.hasValue(cMsg.prchReqTpCd_SL)) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParam.put(DB_PARAM_PRCH_REQ_TP_CD, cMsg.prchReqTpCd_SL.getValue());

            S21SsmEZDResult result = NPBL0020Query.getInstance().getRequisitionType(ssmParam);

            if (result.isCodeNormal()) {
                String defPrchReqLineTpCd = (String) result.getResultObject();
                NPBL0020CommonLogic.createPullDownLineType(cMsg, sMsg, glblCmpyCd, true, defPrchReqLineTpCd);
                ZYPEZDItemValueSetter.setValue(sMsg.defPrchReqLineTpCd, defPrchReqLineTpCd);
                ZYPEZDItemValueSetter.setValue(cMsg.defPrchReqLineTpCd, defPrchReqLineTpCd);
            } else {
                NPBL0020CommonLogic.createPullDownLineType(cMsg, sMsg, glblCmpyCd, true, null);
            }
        }
    }

    /**
     * tokenListSplit
     * @param xxScrItem50Txt_A1 String
     * @param glblCmpyCd String
     * @return tokenList List<String>
     */
    private static List<String> tokenListSplit(String xxScrItem50Txt_A1, String glblCmpyCd) {
        String delimiter = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_KEY_DELIMITER, glblCmpyCd);

        if (!hasValue(xxScrItem50Txt_A1)) {
            return null;
        }
        String[] list = xxScrItem50Txt_A1.split(Pattern.quote(delimiter), -1);
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
     * Get Default Display COA Information
     * @param bizAppId String
     * @param glblCmpyCd String
     * @return DEF_DPLY_COA_INFOTMsg
     */
    private static DEF_DPLY_COA_INFOTMsg getDefDplyCoaInfo(String appFuncId, String glblCmpyCd) {

        DEF_DPLY_COA_INFOTMsg tMsg = new DEF_DPLY_COA_INFOTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.appFuncId.setValue(appFuncId);

        return (DEF_DPLY_COA_INFOTMsg) S21FastTBLAccessor.findByKey(tMsg);
    }

    /**
     * getDefaultSegment
     * @param cMsg NPBL0020CMsg
     * @param bizAppId String
     * @param glblCmpyCd String
     * @return Map<String, String>
     */
    private static Map<String, String> getDefaultSegment(NPBL0020CMsg cMsg, String bizAppId, String glblCmpyCd) {

        Map<String, String> defaultRecord = new HashMap<String, String>();
        // get 9seg Default
        DEF_DPLY_COA_INFOTMsg tMsg = getDefDplyCoaInfo(bizAppId, glblCmpyCd);
        if (tMsg == null) {
            cMsg.setMessageInfo(NZZM0000E);
        } else {

            defaultRecord.put(DB_COLUMN_COA_CMPY_DPLY_FLG, tMsg.coaCmpyDplyFlg.getValue());
            defaultRecord.put(DB_COLUMN_COA_AFFL_DPLY_FLG, tMsg.coaAfflDplyFlg.getValue());
            defaultRecord.put(DB_COLUMN_COA_BR_DPLY_FLG, tMsg.coaBrDplyFlg.getValue());
            defaultRecord.put(DB_COLUMN_COA_CC_DPLY_FLG, tMsg.coaCcDplyFlg.getValue());
            defaultRecord.put(DB_COLUMN_COA_ACCT_DPLY_FLG, tMsg.coaAcctDplyFlg.getValue());
            defaultRecord.put(DB_COLUMN_COA_PROD_DPLY_FLG, tMsg.coaProdDplyFlg.getValue());
            defaultRecord.put(DB_COLUMN_COA_CH_DPLY_FLG, tMsg.coaChDplyFlg.getValue());
            defaultRecord.put(DB_COLUMN_COA_PROJ_DPLY_FLG, tMsg.coaProjDplyFlg.getValue());
            defaultRecord.put(DB_COLUMN_COA_EXTN_DPLY_FLG, tMsg.coaExtnDplyFlg.getValue());

            defaultRecord.put(DB_COLUMN_COA_CMPY_CD, tMsg.coaCmpyCd.getValue());
            defaultRecord.put(DB_COLUMN_COA_AFFL_CD, tMsg.coaAfflCd.getValue());
            defaultRecord.put(DB_COLUMN_COA_BR_CD, tMsg.coaBrCd.getValue());
            defaultRecord.put(DB_COLUMN_COA_CC_CD, tMsg.coaCcCd.getValue());
            defaultRecord.put(DB_COLUMN_COA_ACCT_CD, tMsg.coaAcctCd.getValue());
            defaultRecord.put(DB_COLUMN_COA_PROD_CD, tMsg.coaProdCd.getValue());
            defaultRecord.put(DB_COLUMN_COA_CH_CD, tMsg.coaChCd.getValue());
            defaultRecord.put(DB_COLUMN_COA_PROJ_CD, tMsg.coaProjCd.getValue());
            defaultRecord.put(DB_COLUMN_COA_EXTN_CD, tMsg.coaExtnCd.getValue());

        }

        return defaultRecord;
    }

    /**
     * checkManualSegmentElement
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @param record Map<String, String>
     * @param bizAppId String
     * @return boolean
     */
    private static boolean checkManualSegmentElement(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd, Map<String, String> record, String bizAppId) {

        // Account Manual Check
        int idx = cMsg.xxNum.getValueInt();
        // Length Check
        List<String> tokenList = tokenListSplit(cMsg.A.no(idx).xxScrItem50Txt_A1.getValue(), glblCmpyCd);
        if (tokenList.size() != SEGMENT_TOKEN_LIST_SIZE) {
            cMsg.setMessageInfo(NPBM0005E);
            cMsg.A.no(idx).xxScrItem50Txt_A1.setErrorInfo(1, NPBM0005E, new String[] {MSG_PARAM_SEGMENT });
            return false;
        }

        if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD))) {
            if (ZYPCommonFunc.hasValue(record.get(DB_COLUMN_COA_CMPY_CD)) && ZYPConstant.FLG_OFF_N.equals(record.get(DB_COLUMN_COA_CMPY_DPLY_FLG).toString())) {
                tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD, record.get(DB_COLUMN_COA_CMPY_CD).toString());
            }
        } else {
            tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD, record.get(DB_COLUMN_COA_CMPY_CD).toString());
        }

        if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD))) {
            if (ZYPCommonFunc.hasValue(record.get(DB_COLUMN_COA_EXTN_CD)) && ZYPConstant.FLG_OFF_N.equals(record.get(DB_COLUMN_COA_EXTN_DPLY_FLG).toString())) {
                tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD, record.get(DB_COLUMN_COA_EXTN_CD).toString());
            }
        } else {
            tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD, record.get(DB_COLUMN_COA_EXTN_CD).toString());
        }

        if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD))) {
            if (ZYPCommonFunc.hasValue(record.get(DB_COLUMN_COA_CC_CD)) && ZYPConstant.FLG_OFF_N.equals(record.get(DB_COLUMN_COA_CC_DPLY_FLG).toString())) {
                tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD, record.get(DB_COLUMN_COA_CC_CD).toString());
            }
        } else {
            tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD, record.get(DB_COLUMN_COA_CC_CD).toString());
        }

        if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD))) {
            if (ZYPCommonFunc.hasValue(record.get(DB_COLUMN_COA_ACCT_CD)) && ZYPConstant.FLG_OFF_N.equals(record.get(DB_COLUMN_COA_ACCT_DPLY_FLG).toString())) {
                tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD, record.get(DB_COLUMN_COA_ACCT_CD).toString());
            }
        } else {
            tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD, record.get(DB_COLUMN_COA_ACCT_CD).toString());
        }

        if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD))) {
            if (ZYPCommonFunc.hasValue(record.get(DB_COLUMN_COA_PROJ_CD)) && ZYPConstant.FLG_OFF_N.equals(record.get(DB_COLUMN_COA_PROJ_DPLY_FLG).toString())) {
                tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD, record.get(DB_COLUMN_COA_PROJ_CD).toString());
            }
        } else {
            tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD, record.get(DB_COLUMN_COA_PROJ_CD).toString());
        }

        if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD))) {
            if (ZYPCommonFunc.hasValue(record.get(DB_COLUMN_COA_PROD_CD)) && ZYPConstant.FLG_OFF_N.equals(record.get(DB_COLUMN_COA_PROD_DPLY_FLG).toString())) {
                tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD, record.get(DB_COLUMN_COA_PROD_CD).toString());
            }
        } else {
            tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD, record.get(DB_COLUMN_COA_PROD_CD).toString());
        }

        if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD))) {
            if (ZYPCommonFunc.hasValue(record.get(DB_COLUMN_COA_AFFL_CD)) && ZYPConstant.FLG_OFF_N.equals(record.get(DB_COLUMN_COA_AFFL_DPLY_FLG).toString())) {
                tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD, record.get(DB_COLUMN_COA_AFFL_CD).toString());
            }
        } else {
            tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD, record.get(DB_COLUMN_COA_AFFL_CD).toString());
        }

        if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD))) {
            if (ZYPCommonFunc.hasValue(record.get(DB_COLUMN_COA_CH_CD)) && ZYPConstant.FLG_OFF_N.equals(record.get(DB_COLUMN_COA_CH_DPLY_FLG).toString())) {
                tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD, record.get(DB_COLUMN_COA_CH_CD).toString());
            }
        } else {
            tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD, record.get(DB_COLUMN_COA_CH_CD).toString());
        }

        if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD))) {
            if (ZYPCommonFunc.hasValue(record.get(DB_COLUMN_COA_BR_CD)) && ZYPConstant.FLG_OFF_N.equals(record.get(DB_COLUMN_COA_BR_DPLY_FLG).toString())) {
                tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD, record.get(DB_COLUMN_COA_BR_CD).toString());
            }
        } else {
            tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD, record.get(DB_COLUMN_COA_BR_CD).toString());
        }

        // window PopUp
        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD), SEGMENT_ELEMENT_LENGTH_CMPY));
        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD), SEGMENT_ELEMENT_LENGTH_EXTN));
        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD), SEGMENT_ELEMENT_LENGTH_CC));
        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD), SEGMENT_ELEMENT_LENGTH_ACCT));
        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD), SEGMENT_ELEMENT_LENGTH_PROJ));
        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD), SEGMENT_ELEMENT_LENGTH_PROD));
        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD), SEGMENT_ELEMENT_LENGTH_AFFL));
        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD), SEGMENT_ELEMENT_LENGTH_CH));
        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD), SEGMENT_ELEMENT_LENGTH_BR));

        // PopUp Param set
        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_1).xxPopPrm, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_2).xxPopPrm, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_3).xxPopPrm, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_4).xxPopPrm, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_5).xxPopPrm, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_6).xxPopPrm, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_7).xxPopPrm, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_8).xxPopPrm, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.P.no(IDX_9).xxPopPrm, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));

        // DB COLUMN SET
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaCmpyCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaAfflCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaBrCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaCcCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaAcctCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaProdCd_A2, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaChCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaProjCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaExtnCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));

        return true;
    }

    /**
     * checkManualSegmentElementForSMsg
     * @param element String
     * @param len int
     * @return boolean
     */
    private static boolean checkManualSegmentElementForSMsg(NPBL0020SMsg sMsg, String glblCmpyCd, Map<String, String> record, String bizAppId, int rowIndex, boolean winFlg) {

        // Length Check
        List<String> tokenList = tokenListSplit(sMsg.A.no(rowIndex).xxScrItem50Txt_A1.getValue(), glblCmpyCd);
        if (tokenList.size() != SEGMENT_TOKEN_LIST_SIZE) {
            sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, NPBM0005E, new String[] {MSG_PARAM_SEGMENT });
            return false;
        }

        if (!winFlg) {

            // coaCmpyCd check
            if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD), SEGMENT_ELEMENT_LENGTH_CMPY)) {
                sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, NPBM0006E, new String[] {MSG_PARAM_CMPY });
                return false;
            }

            // coaExtnCd check
            if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD), SEGMENT_ELEMENT_LENGTH_EXTN)) {
                sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, NPBM0006E, new String[] {MSG_PARAM_EXTN });
                return false;
            }

            // coaCcCd check
            if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD), SEGMENT_ELEMENT_LENGTH_CC)) {
                sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, NPBM0006E, new String[] {MSG_PARAM_CC });
                return false;
            }

            // coaAcctCd check
            if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD), SEGMENT_ELEMENT_LENGTH_ACCT)) {
                sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, NPBM0006E, new String[] {MSG_PARAM_ACCT });
                return false;
            }

            // coaProjCd check
            if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD), SEGMENT_ELEMENT_LENGTH_PROJ)) {
                sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, NPBM0006E, new String[] {MSG_PARAM_PROJ });
                return false;
            }

            // coaProdCd check
            if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD), SEGMENT_ELEMENT_LENGTH_PROD)) {
                sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, NPBM0006E, new String[] {MSG_PARAM_PROD });
                return false;
            }

            // coaAfflCd check
            if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD), SEGMENT_ELEMENT_LENGTH_AFFL)) {
                sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, NPBM0006E, new String[] {MSG_PARAM_AFFL });
                return false;
            }

            // coaChCd check
            if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD), SEGMENT_ELEMENT_LENGTH_CH)) {
                sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, NPBM0006E, new String[] {MSG_PARAM_CH });
                return false;
            }

            // coaBrCd check
            if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD), SEGMENT_ELEMENT_LENGTH_BR)) {
                sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, NPBM0006E, new String[] {MSG_PARAM_BR });
                return false;
            }

            // default check
            if (record != null) {
                if (ZYPCommonFunc.hasValue(record.get(DB_COLUMN_COA_CMPY_CD)) && ZYPConstant.FLG_OFF_N.equals(record.get(DB_COLUMN_COA_CMPY_DPLY_FLG).toString())) {
                    if (!record.get(DB_COLUMN_COA_CMPY_CD).toString().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD))) {
                        sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, NPBM0007E, new String[] {MSG_PARAM_CMPY });
                        return false;
                    }
                }
                if (ZYPCommonFunc.hasValue(record.get(DB_COLUMN_COA_AFFL_CD)) && ZYPConstant.FLG_OFF_N.equals(record.get(DB_COLUMN_COA_AFFL_DPLY_FLG).toString())) {
                    if (!record.get(DB_COLUMN_COA_AFFL_CD).toString().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD))) {
                        sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, NPBM0007E, new String[] {MSG_PARAM_AFFL });
                        return false;
                    }
                }
                if (ZYPCommonFunc.hasValue(record.get(DB_COLUMN_COA_BR_CD)) && ZYPConstant.FLG_OFF_N.equals(record.get(DB_COLUMN_COA_BR_DPLY_FLG).toString())) {
                    if (!record.get(DB_COLUMN_COA_BR_CD).toString().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD))) {
                        sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, NPBM0007E, new String[] {MSG_PARAM_BR });
                        return false;
                    }
                }
                if (ZYPCommonFunc.hasValue(record.get(DB_COLUMN_COA_CC_CD)) && ZYPConstant.FLG_OFF_N.equals(record.get(DB_COLUMN_COA_CC_DPLY_FLG).toString())) {
                    if (!record.get(DB_COLUMN_COA_CC_CD).toString().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD))) {
                        sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, NPBM0007E, new String[] {MSG_PARAM_CC });
                        return false;
                    }
                }
                if (ZYPCommonFunc.hasValue(record.get(DB_COLUMN_COA_ACCT_CD)) && ZYPConstant.FLG_OFF_N.equals(record.get(DB_COLUMN_COA_ACCT_DPLY_FLG).toString())) {
                    if (!record.get(DB_COLUMN_COA_ACCT_CD).toString().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD))) {
                        sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, NPBM0007E, new String[] {MSG_PARAM_ACCT });
                        return false;
                    }
                }
                if (ZYPCommonFunc.hasValue(record.get(DB_COLUMN_COA_PROD_CD)) && ZYPConstant.FLG_OFF_N.equals(record.get(DB_COLUMN_COA_PROD_DPLY_FLG).toString())) {
                    if (!record.get(DB_COLUMN_COA_PROD_CD).toString().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD))) {
                        sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, NPBM0007E, new String[] {MSG_PARAM_PROD });
                        return false;
                    }
                }
                if (ZYPCommonFunc.hasValue(record.get(DB_COLUMN_COA_CH_CD)) && ZYPConstant.FLG_OFF_N.equals(record.get(DB_COLUMN_COA_CH_DPLY_FLG).toString())) {
                    if (!record.get(DB_COLUMN_COA_CH_CD).toString().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD))) {
                        sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, NPBM0007E, new String[] {MSG_PARAM_CH });
                        return false;
                    }
                }
                if (ZYPCommonFunc.hasValue(record.get(DB_COLUMN_COA_PROJ_CD)) && ZYPConstant.FLG_OFF_N.equals(record.get(DB_COLUMN_COA_PROJ_DPLY_FLG).toString())) {
                    if (!record.get(DB_COLUMN_COA_PROJ_CD).toString().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD))) {
                        sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, NPBM0007E, new String[] {MSG_PARAM_PROJ });
                        return false;
                    }
                }
                if (ZYPCommonFunc.hasValue(record.get(DB_COLUMN_COA_EXTN_CD)) && ZYPConstant.FLG_OFF_N.equals(record.get(DB_COLUMN_COA_EXTN_DPLY_FLG).toString())) {
                    if (!record.get(DB_COLUMN_COA_EXTN_CD).toString().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD))) {
                        sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, NPBM0007E, new String[] {MSG_PARAM_EXTN });
                        return false;
                    }
                }
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
            paramMsg.resrcObjNm.setValue(bizAppId);

            afzc102001.execute(paramMsg, ONBATCH_TYPE.ONLINE);

            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(paramMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);

                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();

                // QC#15925
                if (msgPrms != null && msgPrms.length > 0) {

                    if (msgPrms[0].equals(DB_COLUMN_COA_CMPY_CD)) {
                        sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CMPY });
                    }
                    if (msgPrms[0].equals(DB_COLUMN_COA_BR_CD)) {
                        sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, msgId, new String[] {MSG_PARAM_BR });
                    }
                    if (msgPrms[0].equals(DB_COLUMN_COA_CC_CD)) {
                        sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CC });
                    }
                    if (msgPrms[0].equals(DB_COLUMN_COA_ACCT_CD)) {
                        sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, msgId, new String[] {MSG_PARAM_ACCT });
                    }
                    if (msgPrms[0].equals(DB_COLUMN_COA_PROD_CD)) {
                        sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, msgId, new String[] {MSG_PARAM_PROD });
                    }
                    if (msgPrms[0].equals(DB_COLUMN_COA_CH_CD)) {
                        sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CH });
                    }
                    if (msgPrms[0].equals(DB_COLUMN_COA_AFFL_CD)) {
                        sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, msgId, new String[] {MSG_PARAM_AFFL });
                    }
                    if (msgPrms[0].equals(DB_COLUMN_COA_PROJ_CD)) {
                        sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, msgId, new String[] {MSG_PARAM_PROJ });
                    }
                    if (msgPrms[0].equals(DB_COLUMN_COA_EXTN_CD)) {
                        sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, msgId, new String[] {MSG_PARAM_EXTN });
                    // START 2019/02/04 M.Naito [QC#30217,MOD]
                    } else {
                        sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, msgId, new String[] {msgPrms[0] });
                    // END 2019/02/04 M.Naito [QC#30217,MOD]
                    }
                } else {
                    sMsg.A.no(rowIndex).xxScrItem50Txt_A1.setErrorInfo(1, msgId, null);
                }

                return false;
            }

        } else {

            if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD))) {
                if (ZYPCommonFunc.hasValue(record.get(DB_COLUMN_COA_CMPY_CD)) && ZYPConstant.FLG_OFF_N.equals(record.get(DB_COLUMN_COA_CMPY_DPLY_FLG).toString())) {
                    tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD, record.get(DB_COLUMN_COA_CMPY_CD).toString());
                }
            } else {
                tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD, record.get(DB_COLUMN_COA_CMPY_CD).toString());
            }

            if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD))) {
                if (ZYPCommonFunc.hasValue(record.get(DB_COLUMN_COA_EXTN_CD)) && ZYPConstant.FLG_OFF_N.equals(record.get(DB_COLUMN_COA_EXTN_DPLY_FLG).toString())) {
                    tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD, record.get(DB_COLUMN_COA_EXTN_CD).toString());
                }
            } else {
                tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD, record.get(DB_COLUMN_COA_EXTN_CD).toString());
            }

            if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD))) {
                if (ZYPCommonFunc.hasValue(record.get(DB_COLUMN_COA_CC_CD)) && ZYPConstant.FLG_OFF_N.equals(record.get(DB_COLUMN_COA_CMPY_DPLY_FLG).toString())) {
                    tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD, record.get(DB_COLUMN_COA_CC_CD).toString());
                }
            } else {
                tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD, record.get(DB_COLUMN_COA_CC_CD).toString());
            }

            if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD))) {
                if (ZYPCommonFunc.hasValue(record.get(DB_COLUMN_COA_ACCT_CD)) && ZYPConstant.FLG_OFF_N.equals(record.get(DB_COLUMN_COA_ACCT_DPLY_FLG).toString())) {
                    tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD, record.get(DB_COLUMN_COA_ACCT_CD).toString());
                }
            } else {
                tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD, record.get(DB_COLUMN_COA_ACCT_CD).toString());
            }

            if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD))) {
                if (ZYPCommonFunc.hasValue(record.get(DB_COLUMN_COA_PROJ_CD)) && ZYPConstant.FLG_OFF_N.equals(record.get(DB_COLUMN_COA_PROJ_DPLY_FLG).toString())) {
                    tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD, record.get(DB_COLUMN_COA_PROJ_CD).toString());
                }
            } else {
                tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD, record.get(DB_COLUMN_COA_PROJ_CD).toString());
            }

            if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD))) {
                if (ZYPCommonFunc.hasValue(record.get(DB_COLUMN_COA_PROD_CD)) && ZYPConstant.FLG_OFF_N.equals(record.get(DB_COLUMN_COA_PROD_DPLY_FLG).toString())) {
                    tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD, record.get(DB_COLUMN_COA_PROD_CD).toString());
                }
            } else {
                tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD, record.get(DB_COLUMN_COA_PROD_CD).toString());
            }

            if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD))) {
                if (ZYPCommonFunc.hasValue(record.get(DB_COLUMN_COA_AFFL_CD)) && ZYPConstant.FLG_OFF_N.equals(record.get(DB_COLUMN_COA_AFFL_DPLY_FLG).toString())) {
                    tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD, record.get(DB_COLUMN_COA_AFFL_CD).toString());
                }
            } else {
                tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD, record.get(DB_COLUMN_COA_AFFL_CD).toString());
            }

            if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD))) {
                if (ZYPCommonFunc.hasValue(record.get(DB_COLUMN_COA_CH_CD)) && ZYPConstant.FLG_OFF_N.equals(record.get(DB_COLUMN_COA_CH_DPLY_FLG).toString())) {
                    tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD, record.get(DB_COLUMN_COA_CH_CD).toString());
                }
            } else {
                tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD, record.get(DB_COLUMN_COA_CH_CD).toString());
            }

            if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD))) {
                if (ZYPCommonFunc.hasValue(record.get(DB_COLUMN_COA_BR_CD)) && ZYPConstant.FLG_OFF_N.equals(record.get(DB_COLUMN_COA_EXTN_DPLY_FLG).toString())) {
                    tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD, record.get(DB_COLUMN_COA_BR_CD).toString());
                }
            } else {
                tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD, record.get(DB_COLUMN_COA_BR_CD).toString());
            }

            // window PopUp
            tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD), SEGMENT_ELEMENT_LENGTH_CMPY));
            tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD), SEGMENT_ELEMENT_LENGTH_EXTN));
            tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD), SEGMENT_ELEMENT_LENGTH_CC));
            tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD), SEGMENT_ELEMENT_LENGTH_ACCT));
            tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD), SEGMENT_ELEMENT_LENGTH_PROJ));
            tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD), SEGMENT_ELEMENT_LENGTH_PROD));
            tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD), SEGMENT_ELEMENT_LENGTH_AFFL));
            tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD), SEGMENT_ELEMENT_LENGTH_CH));
            tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD), SEGMENT_ELEMENT_LENGTH_BR));

        }

        // DB COLUMN SET
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaCmpyCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaAfflCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaBrCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaCcCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaAcctCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaProdCd_A2, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaChCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaProjCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaExtnCd_A1, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));

        return true;
    }

    /**
     * getCoaEnableFlg
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param appFuncId String
     * @param glblCmpyCd String
     * @param index int
     */
    public static void getCoaEnableFlg(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String appFuncId, String glblCmpyCd, int index) {

        DEF_DPLY_COA_INFOTMsg tMsg = getDefDplyCoaInfo(appFuncId, glblCmpyCd);
        if (tMsg == null) {
            cMsg.setMessageInfo(NZZM0000E);
        } else {

            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).coaEnblFlg_A1, ZYPConstant.FLG_OFF_N);

            if (!ZYPConstant.FLG_OFF_N.equals(tMsg.coaCmpyDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).coaEnblFlg_A1, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(tMsg.coaAfflDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).coaEnblFlg_A1, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(tMsg.coaBrDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).coaEnblFlg_A1, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(tMsg.coaCcDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).coaEnblFlg_A1, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(tMsg.coaAcctDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).coaEnblFlg_A1, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(tMsg.coaProdDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).coaEnblFlg_A1, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(tMsg.coaChDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).coaEnblFlg_A1, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(tMsg.coaProjDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).coaEnblFlg_A1, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(tMsg.coaExtnDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).coaEnblFlg_A1, ZYPConstant.FLG_ON_Y);
            }
        }

    }

    private static String splitSegmentElement(String element, int len) {
        if (!hasValue(element)) {
            return null;
        }
        if (element.length() > len) {
            element = element.substring(0, len);
        }
        return element;
    }
    // QC#52809 Mod
    private static String checkDefaultBillShip(NPBL0020_ASMsg asMsg, String glblCmpyCd, String shipToCustCd, boolean isConfig) {

        String defBillToCustCd = null;

        HashMap<String, String> ssmParam = new HashMap<String, String>();

        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_SHIP_TO_CUST_CD, shipToCustCd);
        ssmParam.put(DB_PARAM_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);

        S21SsmEZDResult result = NPBL0020Query.getInstance().getShipToCustInfo(ssmParam);

        if (result.isCodeNormal()) {

            Map<String, Object> shipToCustMap = (Map<String, Object>) result.getResultObject();

            // START 2019/10/03 M.Naito [QC#52809,ADD]
            // get Ship To
            String shipToCustAcctCd = (String) shipToCustMap.get(DB_COLUMN_DS_ACCT_NUM);

            // get Sold To
            String sellToCustCd = shipToCustAcctCd;
            String soldToCustLocCd = null;
            NMZC610001PMsg nMZC610001BSPMsg = callCustInfoGetApiForDefaultMode(asMsg, glblCmpyCd, sellToCustCd, null, null, NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT, ZYPConstant.FLG_OFF_N, isConfig);
            if (nMZC610001BSPMsg != null && nMZC610001BSPMsg.DefaultBillShipList.getValidCount() != 0) {
                soldToCustLocCd = nMZC610001BSPMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
            }

            // get Bill To
            defBillToCustCd = NWXC150001DefaultCustomer.getRelatedBillTo(glblCmpyCd, shipToCustCd);
            if (!ZYPCommonFunc.hasValue(defBillToCustCd)) {
                if (nMZC610001BSPMsg == null) {
                    return null;
                }
                defBillToCustCd = nMZC610001BSPMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
                if (!ZYPCommonFunc.hasValue(defBillToCustCd)) {
                    return null;
                }
            }
            S21SsmEZDResult billToCustResult = NPBL0020Query.getInstance().getBillToCustInfo(glblCmpyCd, defBillToCustCd);
            if (billToCustResult.isCodeNotFound()) {
                return null;
            }
            Map<String, String> billToCustMap = (Map<String, String>) billToCustResult.getResultObject();
            String billToCustAcctCd = (String) billToCustMap.get(DB_COLUMN_DS_ACCT_NUM);

            if (!ZYPCommonFunc.hasValue(soldToCustLocCd)) {
                soldToCustLocCd = defBillToCustCd;
                sellToCustCd = billToCustAcctCd;
            }

            if (NWXC150001DsCheck.checkBillToRalation(glblCmpyCd, defBillToCustCd, billToCustAcctCd)) {
                asMsg.shipToLocNm_E1.setErrorInfo(1, "NWZM1452E", null);
                return null;
            }
            if (NWXC150001DsCheck.checkShipToRalation(glblCmpyCd, shipToCustCd, shipToCustAcctCd)) {
                asMsg.shipToLocNm_E1.setErrorInfo(1, "NWZM1453E", null);
                return null;
            }
            if (NWXC150001DsCheck.checkSoldToRalation(glblCmpyCd, soldToCustLocCd, sellToCustCd)) {
                asMsg.shipToLocNm_E1.setErrorInfo(1, "NWZM1454E", null);
            }
            // END 2019/10/03 M.Naito [QC#52809,ADD]

        } else {
            return null;
        }

        return defBillToCustCd;
    }
    // QC#52809 End
//    /**
//     * callCustomerInfoApi
//     * @param glblCmpyCd String
//     * @param shipToCustAcctCd String
//     * @return NMZC610001PMsg
//     */
//    private static NMZC610001PMsg callCustomerInfoApi(String glblCmpyCd, String shipToCustAcctCd) {
//
//        NMZC610001PMsg pMsg = new NMZC610001PMsg();
//
//        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, PROCESS_DEFAULT_BILL_SHIP);
//        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_I1, shipToCustAcctCd);
//
//        // call NMZC6100 Customer Information Get API
//        new NMZC610001().execute(pMsg, ONBATCH_TYPE.ONLINE);
//
//        return pMsg;
//    }

    private static <T extends EZDMsgArray> boolean hasValidValue(T msgEzArray) {
        return (msgEzArray != null && msgEzArray.getValidCount() > 0);
    }

    /**
     * Expense Order Check isCoaGlCmbn4SegError
     * @param asMsg NPBL0020_ASMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean isCoaGlCmbn4SegError(NPBL0020_ASMsg asMsg, String glblCmpyCd) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_COA_BR_CD, asMsg.coaBrCd_A1.getValue());
        ssmParam.put(DB_PARAM_COA_CC_CD, asMsg.coaCcCd_A1.getValue());
        ssmParam.put(DB_PARAM_COA_CH_CD, asMsg.coaChCd_A1.getValue());
        ssmParam.put(DB_PARAM_COA_PROD_CD, asMsg.coaProdCd_A2.getValue());
        ssmParam.put(DB_PARAM_COA_GL_CMBN_ACTV_FLG, ZYPConstant.FLG_ON_Y);

        S21SsmEZDResult result = NPBL0020Query.getInstance().getCoaGlCmbn4Seg(ssmParam);

        if (result.getQueryResultCount() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Header Close
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     */
    public static void headerClose(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {

        boolean prReleasedFlag = false;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (PRCH_REQ_REL_STS.COMPLEATED.equals(sMsg.A.no(i).prchReqRelStsCd_A1.getValue())) {
                prReleasedFlag = true;
                break;
            }
        }

        if (prReleasedFlag) {
            PRCH_REQ_TPTMsg prchReqTpTMsg = findPrReqTpTMsg(glblCmpyCd, cMsg.prchReqTpCd_SL.getValue());
            String trxSrcTpCd = prchReqTpTMsg.trxSrcTpCd.getValue();

            if (TRX_SRC_TP.WHOLE_SALES.equals(trxSrcTpCd)) {
                if (NPBL0020Query.getInstance().checkExistenceForCPOClose(glblCmpyCd, sMsg.prchReqNum.getValue())) {
                    cMsg.setMessageInfo(NPAM1532E);
                    return;
                }
            } else if (TRX_SRC_TP.MOVEMENT.equals(trxSrcTpCd) || TRX_SRC_TP.VENDOR_RETURN.equals(trxSrcTpCd)) {
                if (NPBL0020Query.getInstance().checkOpenSO(glblCmpyCd, sMsg.prchReqNum.getValue())) {
                    cMsg.setMessageInfo(NPAM1532E);
                    return;
                }
            } else {
                if (PRCH_REQ_TP.KITTING.equals(cMsg.prchReqTpCd_SL.getValue())) {
                	// QC#56543 2020/05/01 Start
                    //if (NPBL0020Query.getInstance().checkOpenWorkOrder(glblCmpyCd, sMsg.prchReqNum.getValue())) {
                    //    cMsg.setMessageInfo(NPAM1532E);
                    //    return;
                    //}
                    // QC#56543 2020/05/01 End
                }
            }
        }

        PRCH_REQTMsg tMsg = findPR(cMsg, glblCmpyCd);
        if (tMsg != null) {
            // update
            // optimistic lock
            if (!tryLock(cMsg, sMsg, tMsg)) {
                // cannot lock
                return;
            }
        } else {
            cMsg.setMessageInfo(NPAM0076E, new String[] {MSG_VALUE_PR });
            return;
        }

        NPZC103001PMsg pMsg = new NPZC103001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC103001Constant.MODE_CLOSE);
        ZYPEZDItemValueSetter.setValue(pMsg.eventId, NPZC103001Constant.EVENT_SUBMIT);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqNum, sMsg.prchReqNum);
        ZYPEZDItemValueSetter.setValue(pMsg.procDt, ZYPDateUtil.getSalesDate());

        if (executeNPZC1030(cMsg, pMsg)) {
            cMsg.setMessageInfo(ZZM8100I);
        }
    }

    /**
     * Get_RtlWh
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @param salesDate String
     * @param isSrcWh boolean
     * @param isError boolean
     */
    public static void setRtlWh(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd, String salesDate, boolean isSrcWh, boolean isError) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        if (isSrcWh) {
            ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.srcRtlWhCd.getValue());
        } else {
            ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.destRtlWhCd.getValue());
        }

        S21SsmEZDResult result = NPBL0020Query.getInstance().executeQueryResultSingle("findRtlWh", ssmParam);

        if (!result.isCodeNormal()) {
            if (isSrcWh) {
                if (isError) {
                    cMsg.srcRtlWhCd.setErrorInfo(1, NPAM1361E, new String[] {"(" + cMsg.srcRtlWhCd.getValue() + ")" });
                }
            } else {
                if (isError) {
                    cMsg.destRtlWhCd.setErrorInfo(1, NPAM1361E, new String[] {"(" + cMsg.destRtlWhCd.getValue() + ")" });
                } else {
                    clearAddress(cMsg);
                }
            }
            return;
        }

        Map<String, Object> retMap = (Map<String, Object>) result.getResultObject();

        if (isSrcWh) {
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_SW, (String) retMap.get(DB_COLUMN_RTL_WH_NM));
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_SW, (String) retMap.get(DB_COLUMN_RTL_WH_CATG_CD));
            if (PRCH_REQ_TP.DISPOSAL.equals(cMsg.prchReqTpCd_SL.getValue()) && !ZYPCommonFunc.hasValue(cMsg.vndCd)) {
                Map<String, String> record = NPBL0020CommonLogic.getShipToCustAddr(cMsg, glblCmpyCd, cMsg.srcRtlWhCd.getValue());
                if (record != null) {
                    NPBL0020CommonLogic.setShipToAddr(cMsg, record);
                } else {
                    clearAddress(cMsg);
                }
            }
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_DW, (String) retMap.get(DB_COLUMN_RTL_WH_NM));
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_DW, (String) retMap.get(DB_COLUMN_RTL_WH_CATG_CD));
            Map<String, String> record = NPBL0020CommonLogic.getShipToCustAddr(cMsg, glblCmpyCd, cMsg.destRtlWhCd.getValue());
            if (record != null) {
                NPBL0020CommonLogic.setShipToAddr(cMsg, record);
            } else {
                clearAddress(cMsg);
            }
        }
    }

    /**
     * Get_SrcSwhH
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @param salesDate String
     * @param isSrcWh boolean
     * @param isError boolean
     */
    public static void setRtlSwh(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd, String salesDate, boolean isSrcSwh, boolean isError) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_SALES_DATE, salesDate);
        if (isSrcSwh) {
            ssmParam.put(DB_PARAM_RTL_SWH_CD, cMsg.srcRtlSwhCd.getValue());

        } else {
            ssmParam.put(DB_PARAM_RTL_SWH_CD, cMsg.destRtlSwhCd.getValue());
        }

        S21SsmEZDResult result = NPBL0020Query.getInstance().executeQueryResultSingle("findRtlSwhNm", ssmParam);

        if (!result.isCodeNormal()) {
            if (isSrcSwh) {
                if (isError) {
                    cMsg.srcRtlSwhCd.setErrorInfo(1, NPAM1361E, new String[] {"(" + cMsg.srcRtlSwhCd.getValue() +")" });
                }
            } else {
                if (isError) {
                    cMsg.destRtlSwhCd.setErrorInfo(1, NPAM1361E, new String[] {"(" + cMsg.destRtlSwhCd.getValue() + ")" });
                }
            }
            return;
        }

        Map<String, Object> retMap = (Map<String, Object>) result.getResultObject();

        if (isSrcSwh) {
            ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_SS, (String) retMap.get(DB_COLUMN_RTL_SWH_NM));
            if (PRCH_REQ_TP.DISPOSAL.equals(cMsg.prchReqTpCd_SL.getValue()) && !ZYPCommonFunc.hasValue(cMsg.vndCd)) {
                Map<String, String> record = getShipToCustAddr(cMsg, glblCmpyCd, cMsg.srcRtlWhCd.getValue());
                if (record != null) {
                    setShipToAddr(cMsg, record);
                } else {
                    clearAddress(cMsg);
                }
            }
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_DS, (String) retMap.get(DB_COLUMN_RTL_SWH_NM));
            Map<String, String> record = getShipToCustAddr(cMsg, glblCmpyCd, cMsg.destRtlWhCd.getValue());
            if (record != null) {
                setShipToAddr(cMsg, record);
            } else {
                clearAddress(cMsg);
            }
        }
    }

    /**
     * get ShiptoCustAddress
     * @param cMsg NPBL0020CMsg
     * @param glblCmpyCd String
     * @return Map<String, String>
     */
    public static Map<String, String> getShipToCustAddr(NPBL0020CMsg cMsg, String glblCmpyCd, String shipToCustCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_SHIP_TO_CUST_CD, shipToCustCd);
        ssmParam.put(DB_PARAM_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);

        S21SsmEZDResult result = NPBL0020Query.getInstance().getShipToAddress(ssmParam);

        if (!result.isCodeNormal()) {
            return null;
        }

        List<Map<String, String>> shipToAddressList = (List<Map<String, String>>) result.getResultObject();
        if (shipToAddressList.size() == 0) {
            return null;
        }

        Map<String, String> record = shipToAddressList.get(0);
        return record;
    }

    /**
     * @param cMsg NPBL0020CMsg
     * @param record Map<String, String>
     */
    public static void setShipToAddrSMsg(NPBL0020SMsg sMsg, Map<String, String> record) {
        // QC#22517
    	if (PRCH_REQ_TP.EXPENSE_ORDER.equals(sMsg.prchReqTpCd_SL.getValue())) {
    	    // Do Nothing
    	} else {
    		clearAddressSMsg(sMsg);
            ZYPEZDItemValueSetter.setValue(sMsg.shipToLocNm, (String) record.get(DB_COLUMN_LOC_NM));
            ZYPEZDItemValueSetter.setValue(sMsg.shipToAddlLocNm, (String) record.get(DB_COLUMN_ADDL_LOC_NM));
            ZYPEZDItemValueSetter.setValue(sMsg.shipToFirstLineAddr, (String) record.get(DB_COLUMN_FIRST_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(sMsg.shipToScdLineAddr, (String) record.get(DB_COLUMN_SCD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(sMsg.shipToThirdLineAddr, (String) record.get(DB_COLUMN_THIRD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(sMsg.shipToFrthLineAddr, (String) record.get(DB_COLUMN_FRTH_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(sMsg.xxShipVndAddr, (String) record.get(DB_COLUMN_SHIP_VND_ADDR));
            ZYPEZDItemValueSetter.setValue(sMsg.shipToPostCd, (String) record.get(DB_COLUMN_POST_CD));
            ZYPEZDItemValueSetter.setValue(sMsg.shipToCtyAddr, (String) record.get(DB_COLUMN_CTY_ADDR));
            ZYPEZDItemValueSetter.setValue(sMsg.shipToCntyNm, (String) record.get(DB_COLUMN_CNTY_NM));
            ZYPEZDItemValueSetter.setValue(sMsg.shipToStCd, (String) record.get(DB_COLUMN_ST_CD));
            ZYPEZDItemValueSetter.setValue(sMsg.shipToProvNm, (String) record.get(DB_COLUMN_PROV_NM));
            ZYPEZDItemValueSetter.setValue(sMsg.shipToCtryCd, (String) record.get(DB_COLUMN_CTRY_CD));
            ZYPEZDItemValueSetter.setValue(sMsg.ctryNm, (String) record.get(DB_COLUMN_CTRY_NM));
    	}

    }
        /**
     * @param cMsg NPBL0020CMsg
     * @param record Map<String, String>
     */
    public static void setShipToAddr(NPBL0020CMsg cMsg, Map<String, String> record) {
    	// QC#22517
    	if (PRCH_REQ_TP.EXPENSE_ORDER.equals(cMsg.prchReqTpCd_SL.getValue())) {
            // Do Nothing
    		if (!ZYPCommonFunc.hasValue(cMsg.shipToLocNm)) {
               	ZYPEZDItemValueSetter.setValue(cMsg.shipToLocNm, (String) record.get(DB_COLUMN_LOC_NM));
    		}
    		if (!ZYPCommonFunc.hasValue(cMsg.shipToAddlLocNm)) {
                ZYPEZDItemValueSetter.setValue(cMsg.shipToAddlLocNm, (String) record.get(DB_COLUMN_ADDL_LOC_NM));
    		}
    		if (!ZYPCommonFunc.hasValue(cMsg.xxShipVndAddr)) {
                ZYPEZDItemValueSetter.setValue(cMsg.shipToFirstLineAddr, (String) record.get(DB_COLUMN_FIRST_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(cMsg.shipToScdLineAddr, (String) record.get(DB_COLUMN_SCD_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(cMsg.shipToThirdLineAddr, (String) record.get(DB_COLUMN_THIRD_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(cMsg.shipToFrthLineAddr, (String) record.get(DB_COLUMN_FRTH_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(cMsg.xxShipVndAddr, (String) record.get(DB_COLUMN_SHIP_VND_ADDR));
    		}
    		if (!ZYPCommonFunc.hasValue(cMsg.shipToPostCd)) {
                ZYPEZDItemValueSetter.setValue(cMsg.shipToPostCd, (String) record.get(DB_COLUMN_POST_CD));
    		}
    		if (!ZYPCommonFunc.hasValue(cMsg.shipToCtyAddr)) {
                ZYPEZDItemValueSetter.setValue(cMsg.shipToCtyAddr, (String) record.get(DB_COLUMN_CTY_ADDR));
    		}
    		if (!ZYPCommonFunc.hasValue(cMsg.shipToCntyNm)) {
    			ZYPEZDItemValueSetter.setValue(cMsg.shipToCntyNm, (String) record.get(DB_COLUMN_CNTY_NM));
    		}
    		if (!ZYPCommonFunc.hasValue(cMsg.shipToStCd)) {
    			ZYPEZDItemValueSetter.setValue(cMsg.shipToStCd, (String) record.get(DB_COLUMN_ST_CD));
    		}
    		if (!ZYPCommonFunc.hasValue(cMsg.shipToProvNm)) {
    			ZYPEZDItemValueSetter.setValue(cMsg.shipToProvNm, (String) record.get(DB_COLUMN_PROV_NM));
    		}
    		if (!ZYPCommonFunc.hasValue(cMsg.shipToCtryCd)) {
    			ZYPEZDItemValueSetter.setValue(cMsg.shipToCtryCd, (String) record.get(DB_COLUMN_CTRY_CD));
    		}
    		if (!ZYPCommonFunc.hasValue(cMsg.ctryNm)) {
    			ZYPEZDItemValueSetter.setValue(cMsg.ctryNm, (String) record.get(DB_COLUMN_CTRY_NM));
    		}
    	} else {
            clearAddress(cMsg);
           	ZYPEZDItemValueSetter.setValue(cMsg.shipToLocNm, (String) record.get(DB_COLUMN_LOC_NM));
            ZYPEZDItemValueSetter.setValue(cMsg.shipToAddlLocNm, (String) record.get(DB_COLUMN_ADDL_LOC_NM));
            ZYPEZDItemValueSetter.setValue(cMsg.shipToFirstLineAddr, (String) record.get(DB_COLUMN_FIRST_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(cMsg.shipToScdLineAddr, (String) record.get(DB_COLUMN_SCD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(cMsg.shipToThirdLineAddr, (String) record.get(DB_COLUMN_THIRD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(cMsg.shipToFrthLineAddr, (String) record.get(DB_COLUMN_FRTH_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(cMsg.xxShipVndAddr, (String) record.get(DB_COLUMN_SHIP_VND_ADDR));
            ZYPEZDItemValueSetter.setValue(cMsg.shipToPostCd, (String) record.get(DB_COLUMN_POST_CD));
            ZYPEZDItemValueSetter.setValue(cMsg.shipToCtyAddr, (String) record.get(DB_COLUMN_CTY_ADDR));
            ZYPEZDItemValueSetter.setValue(cMsg.shipToCntyNm, (String) record.get(DB_COLUMN_CNTY_NM));
            ZYPEZDItemValueSetter.setValue(cMsg.shipToStCd, (String) record.get(DB_COLUMN_ST_CD));
            ZYPEZDItemValueSetter.setValue(cMsg.shipToProvNm, (String) record.get(DB_COLUMN_PROV_NM));
            ZYPEZDItemValueSetter.setValue(cMsg.shipToCtryCd, (String) record.get(DB_COLUMN_CTRY_CD));
            ZYPEZDItemValueSetter.setValue(cMsg.ctryNm, (String) record.get(DB_COLUMN_CTRY_NM));
    	}
    }

    /**
     * setShipToAddrLine
     * @param cMsg NPBL0020CMsg
     * @param record Map<String, String>
     * @param index int
     */
    public static void setShipToAddrLine(NPBL0020CMsg cMsg, Map<String, String> record, int index) {
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).shipToCustCd_A1, (String) record.get(DB_COLUMN_SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).shipToLocNm_A1, (String) record.get(DB_COLUMN_LOC_NM));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).shipToAddlLocNm_A1, (String) record.get(DB_COLUMN_ADDL_LOC_NM));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).shipToFirstLineAddr_A1, (String) record.get(DB_COLUMN_FIRST_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).shipToScdLineAddr_A1, (String) record.get(DB_COLUMN_SCD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).shipToThirdLineAddr_A1, (String) record.get(DB_COLUMN_THIRD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).shipToFrthLineAddr_A1, (String) record.get(DB_COLUMN_FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).xxShipVndAddr_A1, (String) record.get(DB_COLUMN_SHIP_VND_ADDR));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).shipToPostCd_A1, (String) record.get(DB_COLUMN_POST_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).shipToCtyAddr_A1, (String) record.get(DB_COLUMN_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).shipToCntyNm_A1, (String) record.get(DB_COLUMN_CNTY_NM));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).shipToStCd_A1, (String) record.get(DB_COLUMN_ST_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).shipToProvNm_A1, (String) record.get(DB_COLUMN_PROV_NM));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).shipToCtryCd_A1, (String) record.get(DB_COLUMN_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).ctryNm_A1, (String) record.get(DB_COLUMN_CTRY_NM));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPopPrm_P1, cMsg.A.no(index).prchReqLineNum_A1.getValue() + "." + cMsg.A.no(index).prchReqLineSubNum_A1.getValueInt());
        ZYPEZDItemValueSetter.setValue(cMsg.xxPopPrm_P2, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(cMsg.xxPopPrm_P3, ZYPConstant.FLG_OFF_N);
    }

    /**
     * setShipToAddrLineSMsg
     * @param cMsg NPBL0020CMsg
     * @param record Map<String, String>
     * @param index int
     */
    public static void setShipToAddrLineSMsg(NPBL0020SMsg sMsg, Map<String, String> record, int index) {
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shipToCustCd_A1, (String) record.get(DB_COLUMN_SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shipToLocNm_A1, (String) record.get(DB_COLUMN_LOC_NM));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shipToAddlLocNm_A1, (String) record.get(DB_COLUMN_ADDL_LOC_NM));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shipToFirstLineAddr_A1, (String) record.get(DB_COLUMN_FIRST_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shipToScdLineAddr_A1, (String) record.get(DB_COLUMN_SCD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shipToThirdLineAddr_A1, (String) record.get(DB_COLUMN_THIRD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shipToFrthLineAddr_A1, (String) record.get(DB_COLUMN_FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxShipVndAddr_A1, (String) record.get(DB_COLUMN_SHIP_VND_ADDR));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shipToPostCd_A1, (String) record.get(DB_COLUMN_POST_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shipToCtyAddr_A1, (String) record.get(DB_COLUMN_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shipToCntyNm_A1, (String) record.get(DB_COLUMN_CNTY_NM));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shipToStCd_A1, (String) record.get(DB_COLUMN_ST_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shipToProvNm_A1, (String) record.get(DB_COLUMN_PROV_NM));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shipToCtryCd_A1, (String) record.get(DB_COLUMN_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).ctryNm_A1, (String) record.get(DB_COLUMN_CTRY_NM));
        ZYPEZDItemValueSetter.setValue(sMsg.xxPopPrm_P1, sMsg.A.no(index).prchReqLineNum_A1.getValue() + "." + sMsg.A.no(index).prchReqLineSubNum_A1.getValueInt());
        ZYPEZDItemValueSetter.setValue(sMsg.xxPopPrm_P2, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(sMsg.xxPopPrm_P3, ZYPConstant.FLG_OFF_N);
    }

    /**
     * setShiptoSply
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @param isError boolean
     */
    public static void setShiptoSply(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd, boolean isError) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_VND_CD, cMsg.vndCd.getValue());

        S21SsmEZDResult result = NPBL0020Query.getInstance().executeQueryResultSingle("findShipToSupplier", ssmParam);

        if (!result.isCodeNormal()) {
            if (isError) {
                cMsg.vndCd.setErrorInfo(1, NPAM1361E, new String[] {"(" + cMsg.vndCd.getValue() + ")" });
            }
            return;
        }

        Map<String, Object> retMap = (Map<String, Object>) result.getResultObject();

        ZYPEZDItemValueSetter.setValue(cMsg.dplyVndNm, (String) retMap.get(DB_COLUMN_DPLY_VND_NM));

        if (!PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue())) {

            Map<String, String> record = NPBL0020CommonLogic.getShipToCustAddr(cMsg, glblCmpyCd, cMsg.vndCd.getValue());
            if (record != null) {
                NPBL0020CommonLogic.setShipToAddr(cMsg, record);

                for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                    if (record != null) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dplyVndNm_A1, cMsg.vndCd);
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dplyVndNm_A1, cMsg.dplyVndNm);
                        NPBL0020CommonLogic.setShipToAddrLineSMsg(sMsg, record, i);
                    }
                }
            } else {
                clearAddress(cMsg);
            }
        }
    }

    /**
     * Addtional Header Address Clear
     * @param sMsg NPBL0020SMsg
     */
    public static void clearAddressSMsg(NPBL0020SMsg sMsg) {
        sMsg.shipToLocNm.clear();
        sMsg.shipToAddlLocNm.clear();
        sMsg.xxShipVndAddr.clear();
        sMsg.shipToPostCd.clear();
        sMsg.shipToCtyAddr.clear();
        sMsg.shipToCntyNm.clear();
        sMsg.shipToStCd.clear();
        sMsg.shipToProvNm.clear();
        sMsg.ctryNm.clear();
    }

    /**
     * Addtional Header Address Clear
     * @param cMsg NPBL0020CMsg
     */
    public static void clearAddress(NPBL0020CMsg cMsg) {
       	if(PRCH_REQ_TP.EXPENSE_ORDER.equals(cMsg.prchReqTpCd_SL.getValue())) {
       		// Do Nothing
       	} else {
            cMsg.shipToLocNm.clear();
            cMsg.shipToAddlLocNm.clear();
            cMsg.xxShipVndAddr.clear();
            cMsg.shipToPostCd.clear();
            cMsg.shipToCtyAddr.clear();
            cMsg.shipToCntyNm.clear();
            cMsg.shipToStCd.clear();
            cMsg.shipToProvNm.clear();
            cMsg.ctryNm.clear();
       	}

    }

    /**
     * setUpdateScreenValue
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @param salesDate String
     * @param isSmsg boolean
     */
    public static void setUpdateScreenValue(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd, String salesDate, boolean isSmsg) {

        if (SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.srcRtlWhCd.getValue())) {
            cMsg.rtlWhNm_SW.clear();
        } else {
            if (ZYPCommonFunc.hasValue(cMsg.srcRtlWhCd)) {
                setRtlWh(cMsg, sMsg, glblCmpyCd, salesDate, true, false);
            } else {
                clearAddress(cMsg);
            }
            if (isSmsg && (PRCH_REQ_TP.VENDOR_RETURN.equals(sMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.REFURBISHING.equals(sMsg.prchReqTpCd_SL.getValue()))) {
                for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).srcRtlWhCd_A1, cMsg.srcRtlWhCd);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhNm_A1, cMsg.rtlWhNm_SW);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhCatgCd_A1, cMsg.rtlWhCatgCd_SW);
                }
            }
            if (isSmsg && (PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue()))) {
                for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).srcRtlWhCd_A1, cMsg.srcRtlWhCd);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhNm_A1, cMsg.rtlWhNm_SW);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhCatgCd_A1, cMsg.rtlWhCatgCd_SW);
                }
            }
        }
        if (SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.srcRtlSwhCd.getValue())) {
            cMsg.rtlSwhNm_SS.clear();
        } else {
            if (ZYPCommonFunc.hasValue(cMsg.srcRtlSwhCd)) {
                setRtlSwh(cMsg, sMsg, glblCmpyCd, salesDate, true, false);
                // QC#22511 Modify.
                if (isSmsg && PRCH_REQ_TP.REFURBISHING.equals(sMsg.prchReqTpCd_SL.getValue())) {
                    for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).srcRtlSwhCd_A1, cMsg.srcRtlSwhCd);
                    }
                }
            }
        }
        if (SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.destRtlWhCd.getValue())) {
            cMsg.rtlWhNm_DW.clear();
        } else {
            if (ZYPCommonFunc.hasValue(cMsg.destRtlWhCd)) {
                setRtlWh(cMsg, sMsg, glblCmpyCd, salesDate, false, false);
                if (isSmsg && (PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue()))) {
                    for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).destRtlWhCd_A1, cMsg.destRtlWhCd);
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhNm_A2, cMsg.rtlWhNm_DW);
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhCatgCd_A2, cMsg.rtlWhCatgCd_DW);
                    }
                }
            } else if ((PRCH_REQ_TP.WH_TRANSFER.equals(cMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue())) && !ZYPCommonFunc.hasValue(cMsg.destRtlWhCd)) {
                clearAddress(cMsg);
            }
        }
        if (SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.destRtlSwhCd.getValue())) {
            cMsg.rtlSwhNm_DS.clear();
        } else {
            if (ZYPCommonFunc.hasValue(cMsg.destRtlSwhCd)) {
                setRtlSwh(cMsg, sMsg, glblCmpyCd, salesDate, false, false);
                if (isSmsg && (PRCH_REQ_TP.SUBCONTRACT.equals(sMsg.prchReqTpCd_SL.getValue()))) {
                    for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).destRtlSwhCd_A1, cMsg.destRtlSwhCd);
                    }
                }
            } else if ((PRCH_REQ_TP.WH_TRANSFER.equals(cMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue())) && !ZYPCommonFunc.hasValue(cMsg.destRtlWhCd)) {
                clearAddress(cMsg);
            }
        }
        if (SCREEN_CTRL_VALUE_MULTIPLE.equals(cMsg.shipToCustCd_EO.getValue())) {
            cMsg.shipToLocNm_EO.clear();
        } else {
            if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd_EO)) {
                String dsAcctnm = NPBL0020CommonLogic.getShipToCustName(glblCmpyCd, cMsg.shipToCustCd_EO.getValue());

                if (ZYPCommonFunc.hasValue(dsAcctnm)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToLocNm_EO, dsAcctnm);
                }

                Map<String, String> record = getShipToCustAddr(cMsg, glblCmpyCd, cMsg.shipToCustCd_EO.getValue());
                if (record != null) {
                    setShipToAddr(cMsg, record);
                    setShipToAddrSMsg(sMsg, record);
                } else {
                    clearAddress(cMsg);
                }
            } else if (PRCH_REQ_TP.EXPENSE_ORDER.equals(cMsg.prchReqTpCd_SL.getValue()) && !ZYPCommonFunc.hasValue(cMsg.shipToCustCd_EO)) {
                clearAddress(cMsg);
            }
        }

        if (!PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue()) && ZYPCommonFunc.hasValue(cMsg.vndCd)) {

            checkShipToSupplierCMsg(cMsg, glblCmpyCd);

            NPBL0020CommonLogic.setShipToAddress(cMsg, sMsg, glblCmpyCd, cMsg.vndCd.getValue());

            if (isSmsg) {
                for (int i = 0; i < sMsg.A.getValidCount(); i++) {

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dplyVndNm_A1, cMsg.dplyVndNm);

                    Map<String, String> record = NPBL0020CommonLogic.getShipToCustAddr(cMsg, glblCmpyCd, cMsg.vndCd.getValue());

                    if (record != null) {

                        NPBL0020CommonLogic.setShipToAddrLineSMsg(sMsg, record, i);

                    }
                }
            }
        } else if ((PRCH_REQ_TP.DISPOSAL.equals(cMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.VENDOR_RETURN.equals(cMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.REFURBISHING.equals(cMsg.prchReqTpCd_SL.getValue())) && !ZYPCommonFunc.hasValue(cMsg.vndCd)) {

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                sMsg.A.no(i).vndCd_A1.clear();
                sMsg.A.no(i).dplyVndNm_A1.clear();
            }

            if (!PRCH_REQ_TP.DISPOSAL.equals(cMsg.prchReqTpCd_SL.getValue())) {
                clearAddress(cMsg);
            }
        }

        if ((PRCH_REQ_TP.DISPOSAL.equals(cMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.VENDOR_RETURN.equals(cMsg.prchReqTpCd_SL.getValue()) // 
                || PRCH_REQ_TP.REFURBISHING.equals(cMsg.prchReqTpCd_SL.getValue()) //
                || PRCH_REQ_TP.SUBCONTRACT.equals(cMsg.prchReqTpCd_SL.getValue())) // 
                &&ZYPCommonFunc.hasValue(cMsg.shipToCustCd_EO)) {
            String dsAcctnm = NPBL0020CommonLogic.getShipToCustName(glblCmpyCd, cMsg.shipToCustCd_EO.getValue());

            if (ZYPCommonFunc.hasValue(dsAcctnm)) {
                ZYPEZDItemValueSetter.setValue(cMsg.shipToLocNm_EO, dsAcctnm);
            }

            Map<String, String> record = getShipToCustAddr(cMsg, glblCmpyCd, cMsg.shipToCustCd_EO.getValue());
            if (record != null) {
                setShipToAddr(cMsg, record);
                setShipToAddrSMsg(sMsg, record);
            } else {
                clearAddress(cMsg);
            }

            if (isSmsg) {
                for (int i = 0; i < sMsg.A.getValidCount(); i++) {

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).shipToLocNm_E1, cMsg.shipToLocNm_EO);

                    if (record != null) {

                        NPBL0020CommonLogic.setShipToAddrLineSMsg(sMsg, record, i);

                    }
                }
            }
        }
        
        if (!ZYPCommonFunc.hasValue(cMsg.prchReqTpCd_SL.getValue())) {
            clearAddress(cMsg);
        }
    }

    /**
     * The method NPBL0020SMsg: set Multiple Check and Set
     * @param sMsg NPBL0020BMsg
     * @param glblCmpy
     */
    public static void allMultipleCheck(NPBL0020SMsg sMsg, String glblCmpyCd) {

        // Multiple Check
        String destRtlWhCdPre = null;
        String destRtlSwhCdPre = null;
        String srcRtlWhCdPre = null;
        String srcRtlSwhCdPre = null;
        String shipToCustCdPre = null;

        boolean isSrcWhMulti = false;
        boolean isDestWhMulti = false;
        boolean isShipToCustMulti = false;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            String destRtlWhCd = null;
            String destRtlSwhCd = null;
            String srcRtlWhCd = null;
            String srcRtlSwhCd = null;
            String shipToCustCd = null;
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).shipToCustCd_E1)) {
                shipToCustCd = sMsg.A.no(i).shipToCustCd_E1.getValue();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).destRtlWhCd_A1)) {
                destRtlWhCd = sMsg.A.no(i).destRtlWhCd_A1.getValue();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).destRtlSwhCd_A1)) {
                destRtlSwhCd = sMsg.A.no(i).destRtlSwhCd_A1.getValue();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).srcRtlWhCd_A1)) {
                srcRtlWhCd = sMsg.A.no(i).srcRtlWhCd_A1.getValue();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).srcRtlSwhCd_A1)) {
                srcRtlSwhCd = sMsg.A.no(i).srcRtlSwhCd_A1.getValue();
            }
            if ((ZYPCommonFunc.hasValue(srcRtlWhCd) && ZYPCommonFunc.hasValue(srcRtlWhCdPre)) && !srcRtlWhCdPre.equals(srcRtlWhCd)) {
                sMsg.srcRtlWhCd.setValue(SCREEN_CTRL_VALUE_MULTIPLE);
                sMsg.rtlWhNm_SW.clear();
                if (PRCH_REQ_TP.DISPOSAL.equals(sMsg.prchReqTpCd_SL.getValue()) && !ZYPCommonFunc.hasValue(sMsg.vndCd)) {
                    setShipToMultipleSMsg(sMsg);
                }
                isSrcWhMulti = true;
            }
            if ((ZYPCommonFunc.hasValue(srcRtlSwhCd) && ZYPCommonFunc.hasValue(srcRtlSwhCdPre)) && !srcRtlSwhCdPre.equals(srcRtlSwhCd)) {
                sMsg.srcRtlSwhCd.setValue(SCREEN_CTRL_VALUE_MULTIPLE);
                sMsg.rtlSwhNm_SS.clear();
            }
            if ((ZYPCommonFunc.hasValue(destRtlWhCd) && ZYPCommonFunc.hasValue(destRtlWhCdPre)) && !destRtlWhCdPre.equals(destRtlWhCd)) {
                sMsg.destRtlWhCd.setValue(SCREEN_CTRL_VALUE_MULTIPLE);
                sMsg.rtlWhNm_DW.clear();
                setShipToMultipleSMsg(sMsg);
                isDestWhMulti = true;
            }
            if ((ZYPCommonFunc.hasValue(destRtlSwhCd) && ZYPCommonFunc.hasValue(destRtlSwhCdPre)) && !destRtlSwhCdPre.equals(destRtlSwhCd)) {
                sMsg.destRtlSwhCd.setValue(SCREEN_CTRL_VALUE_MULTIPLE);
                sMsg.rtlSwhNm_DS.clear();
            }
            if ((ZYPCommonFunc.hasValue(shipToCustCd) && ZYPCommonFunc.hasValue(shipToCustCdPre)) && !shipToCustCdPre.equals(shipToCustCd)) {
                sMsg.shipToCustCd_EO.setValue(SCREEN_CTRL_VALUE_MULTIPLE);
                sMsg.shipToLocNm_EO.clear();
                setShipToMultipleSMsg(sMsg);
                isShipToCustMulti = true;
            }

            shipToCustCdPre = shipToCustCd;
            destRtlWhCdPre = destRtlWhCd;
            destRtlSwhCdPre = destRtlSwhCd;
            srcRtlWhCdPre = srcRtlWhCd;
            srcRtlSwhCdPre = srcRtlSwhCd;
        }

        if (!isSrcWhMulti && ZYPCommonFunc.hasValue(srcRtlWhCdPre)) {
            if (PRCH_REQ_TP.DISPOSAL.equals(sMsg.prchReqTpCd_SL.getValue()) && !ZYPCommonFunc.hasValue(sMsg.vndCd)) {
                Map<String, String> record = NPBL0020CommonLogic.getShipToCustAddr(null, glblCmpyCd, srcRtlWhCdPre);
                if (record != null) {
                    NPBL0020CommonLogic.setShipToAddrSMsg(sMsg, record);
                } else {
                    clearAddressSMsg(sMsg);
                }
            }
        }

        if (!isDestWhMulti && ZYPCommonFunc.hasValue(destRtlWhCdPre)) {
            Map<String, String> record = NPBL0020CommonLogic.getShipToCustAddr(null, glblCmpyCd, destRtlWhCdPre);
            if (record != null) {
                NPBL0020CommonLogic.setShipToAddrSMsg(sMsg, record);
            } else {
                clearAddressSMsg(sMsg);
            }
        }

        if (!isShipToCustMulti && ZYPCommonFunc.hasValue(shipToCustCdPre)) {
            Map<String, String> record = getShipToCustAddr(null, glblCmpyCd, shipToCustCdPre);
            if (record != null) {
                setShipToAddrSMsg(sMsg, record);
            } else {
                clearAddressSMsg(sMsg);
            }
        }
    }

    /**
     * The method NPBL0020SMsg: set Multiple Check and Set
     * @param sMsg NPBL0020BMsg
     * @param glblCmpy
     */
    public static void allMultipleCheckTabAction(NPBL0020SMsg sMsg, String glblCmpyCd) {

        // Multiple Check
        String destRtlWhCdPre = null;
        String srcRtlWhCdPre = null;
        String shipToCustCdPre = null;

        boolean isSrcWhMulti = false;
        boolean isDestWhMulti = false;
        boolean isShipToCustMulti = false;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            String destRtlWhCd = null;
            String srcRtlWhCd = null;
            String shipToCustCd = null;
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).shipToCustCd_E1)) {
                shipToCustCd = sMsg.A.no(i).shipToCustCd_E1.getValue();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).destRtlWhCd_A1)) {
                destRtlWhCd = sMsg.A.no(i).destRtlWhCd_A1.getValue();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).srcRtlWhCd_A1)) {
                srcRtlWhCd = sMsg.A.no(i).srcRtlWhCd_A1.getValue();
            }
            if ((ZYPCommonFunc.hasValue(srcRtlWhCd) && ZYPCommonFunc.hasValue(srcRtlWhCdPre)) && !srcRtlWhCdPre.equals(srcRtlWhCd)) {
                if (PRCH_REQ_TP.DISPOSAL.equals(sMsg.prchReqTpCd_SL.getValue()) && !ZYPCommonFunc.hasValue(sMsg.vndCd)) {
                    setShipToMultipleSMsg(sMsg);
                }
                isSrcWhMulti = true;
            }
            if ((ZYPCommonFunc.hasValue(destRtlWhCd) && ZYPCommonFunc.hasValue(destRtlWhCdPre)) && !destRtlWhCdPre.equals(destRtlWhCd)) {
                setShipToMultipleSMsg(sMsg);
                isDestWhMulti = true;
            }
            if ((ZYPCommonFunc.hasValue(shipToCustCd) && ZYPCommonFunc.hasValue(shipToCustCdPre)) && !shipToCustCdPre.equals(shipToCustCd)) {
                setShipToMultipleSMsg(sMsg);
                isShipToCustMulti = true;
            }

            shipToCustCdPre = shipToCustCd;
            destRtlWhCdPre = destRtlWhCd;
            srcRtlWhCdPre = srcRtlWhCd;
        }

        if (!isSrcWhMulti && ZYPCommonFunc.hasValue(srcRtlWhCdPre)) {
            if (PRCH_REQ_TP.DISPOSAL.equals(sMsg.prchReqTpCd_SL.getValue()) && !ZYPCommonFunc.hasValue(sMsg.vndCd)) {
                Map<String, String> record = NPBL0020CommonLogic.getShipToCustAddr(null, glblCmpyCd, srcRtlWhCdPre);
                if (record != null) {
                    NPBL0020CommonLogic.setShipToAddrSMsg(sMsg, record);
                } else {
                    clearAddressSMsg(sMsg);
                }
            }
        }

        if (!isDestWhMulti && ZYPCommonFunc.hasValue(destRtlWhCdPre)) {
            Map<String, String> record = NPBL0020CommonLogic.getShipToCustAddr(null, glblCmpyCd, destRtlWhCdPre);
            if (record != null) {
                NPBL0020CommonLogic.setShipToAddrSMsg(sMsg, record);
            } else {
                clearAddressSMsg(sMsg);
            }
        }

        if (!isShipToCustMulti && ZYPCommonFunc.hasValue(shipToCustCdPre)) {
            Map<String, String> record = getShipToCustAddr(null, glblCmpyCd, shipToCustCdPre);
            if (record != null) {
                setShipToAddrSMsg(sMsg, record);
            } else {
                clearAddressSMsg(sMsg);
            }
        }
    }

    /**
     * The method explanation: set Multiple Location
     * @param cMsg NPBL0020CMsg
     */
    public static void setShipToMultiple(NPBL0020CMsg cMsg) {
        cMsg.shipToLocNm.setValue(SCREEN_CTRL_VALUE_MULTIPLE);
        cMsg.shipToAddlLocNm.clear();
        cMsg.xxShipVndAddr.clear();
        cMsg.shipToPostCd.clear();
        cMsg.shipToCtyAddr.clear();
        cMsg.shipToCntyNm.clear();
        cMsg.shipToStCd.clear();
        cMsg.shipToProvNm.clear();
        cMsg.ctryNm.clear();
    }

    /**
     * The method explanation: set Multiple Location
     * @param sMsg NPBL0020SMsg
     */
    public static void setShipToMultipleSMsg(NPBL0020SMsg sMsg) {
        sMsg.shipToLocNm.setValue(SCREEN_CTRL_VALUE_MULTIPLE);
        sMsg.shipToAddlLocNm.clear();
        sMsg.xxShipVndAddr.clear();
        sMsg.shipToPostCd.clear();
        sMsg.shipToCtyAddr.clear();
        sMsg.shipToCntyNm.clear();
        sMsg.shipToStCd.clear();
        sMsg.shipToProvNm.clear();
        sMsg.ctryNm.clear();
    }

    /**
     * The method NPBL0020SMsg: set Multiple Check and Set
     * @param sMsg NPBL0020BMsg
     * @param cMsg NPBL0020CMsg
     */
    public static void allMultipleCheckSetCMsg(NPBL0020SMsg sMsg, NPBL0020CMsg cMsg) {

        // Multiple Check
        String destRtlWhCdPre = null;
        String destRtlSwhCdPre = null;
        String srcRtlWhCdPre = null;
        String srcRtlSwhCdPre = null;
        String shipToCustCdPre = null;

        boolean isSrcWhMulti = false;
        boolean isSrcSwhMulti = false;
        boolean isDestWhMulti = false;
        boolean isDestSwhMulti = false;
        boolean isShipToCustMulti = false;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            String destRtlWhCd = null;
            String destRtlSwhCd = null;
            String srcRtlWhCd = null;
            String srcRtlSwhCd = null;
            String shipToCustCd = null;
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).shipToCustCd_E1)) {
                shipToCustCd = sMsg.A.no(i).shipToCustCd_E1.getValue();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).destRtlWhCd_A1)) {
                destRtlWhCd = sMsg.A.no(i).destRtlWhCd_A1.getValue();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).destRtlSwhCd_A1)) {
                destRtlSwhCd = sMsg.A.no(i).destRtlSwhCd_A1.getValue();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).srcRtlWhCd_A1)) {
                srcRtlWhCd = sMsg.A.no(i).srcRtlWhCd_A1.getValue();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).srcRtlSwhCd_A1)) {
                srcRtlSwhCd = sMsg.A.no(i).srcRtlSwhCd_A1.getValue();
            }
            if ((ZYPCommonFunc.hasValue(srcRtlWhCd) && ZYPCommonFunc.hasValue(srcRtlWhCdPre)) && !srcRtlWhCdPre.equals(srcRtlWhCd)) {
                cMsg.srcRtlWhCd.setValue(SCREEN_CTRL_VALUE_MULTIPLE);
                cMsg.rtlWhNm_SW.clear();
                if (PRCH_REQ_TP.DISPOSAL.equals(sMsg.prchReqTpCd_SL.getValue())) {
                    setShipToMultiple(cMsg);
                }
                isSrcWhMulti = true;
            }
            if ((ZYPCommonFunc.hasValue(srcRtlSwhCd) && ZYPCommonFunc.hasValue(srcRtlSwhCdPre)) && !srcRtlSwhCdPre.equals(srcRtlSwhCd)) {
                cMsg.srcRtlSwhCd.setValue(SCREEN_CTRL_VALUE_MULTIPLE);
                cMsg.rtlSwhNm_SS.clear();
                isSrcSwhMulti = true;
            }
            if ((ZYPCommonFunc.hasValue(destRtlWhCd) && ZYPCommonFunc.hasValue(destRtlWhCdPre)) && !destRtlWhCdPre.equals(destRtlWhCd)) {
                cMsg.destRtlWhCd.setValue(SCREEN_CTRL_VALUE_MULTIPLE);
                cMsg.rtlWhNm_DW.clear();
                setShipToMultiple(cMsg);
                isDestWhMulti = true;
            }
            if ((ZYPCommonFunc.hasValue(destRtlSwhCd) && ZYPCommonFunc.hasValue(destRtlSwhCdPre)) && !destRtlSwhCdPre.equals(destRtlSwhCd)) {
                cMsg.destRtlSwhCd.setValue(SCREEN_CTRL_VALUE_MULTIPLE);
                cMsg.rtlSwhNm_DS.clear();
                isDestSwhMulti = true;
            }
            if ((ZYPCommonFunc.hasValue(shipToCustCd) && ZYPCommonFunc.hasValue(shipToCustCdPre)) && !shipToCustCdPre.equals(shipToCustCd)) {
                cMsg.shipToCustCd_EO.setValue(SCREEN_CTRL_VALUE_MULTIPLE);
                cMsg.shipToLocNm_EO.clear();
                setShipToMultiple(cMsg);
                isShipToCustMulti = true;
            }

            shipToCustCdPre = shipToCustCd;
            destRtlWhCdPre = destRtlWhCd;
            destRtlSwhCdPre = destRtlSwhCd;
            srcRtlWhCdPre = srcRtlWhCd;
            srcRtlSwhCdPre = srcRtlSwhCd;
        }

        if (!isSrcWhMulti && ZYPCommonFunc.hasValue(srcRtlWhCdPre)) {
            ZYPEZDItemValueSetter.setValue(cMsg.srcRtlWhCd, srcRtlWhCdPre);
        }
        if (!isSrcSwhMulti && ZYPCommonFunc.hasValue(srcRtlSwhCdPre)) {
            ZYPEZDItemValueSetter.setValue(cMsg.srcRtlSwhCd, srcRtlSwhCdPre);
        }
        if (!isDestWhMulti && ZYPCommonFunc.hasValue(destRtlWhCdPre)) {
            ZYPEZDItemValueSetter.setValue(cMsg.destRtlWhCd, destRtlWhCdPre);
        }
        if (!isDestSwhMulti && ZYPCommonFunc.hasValue(destRtlSwhCdPre)) {
            ZYPEZDItemValueSetter.setValue(cMsg.destRtlSwhCd, destRtlSwhCdPre);
        }
        if (!isShipToCustMulti && ZYPCommonFunc.hasValue(shipToCustCdPre)) {
            ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd_EO, shipToCustCdPre);
        }

        if (ZYPCommonFunc.hasValue(sMsg.vndCd) && ZYPCommonFunc.hasValue(sMsg.shipToCustCd_EO) //
                && sMsg.vndCd.getValue().equals(sMsg.shipToCustCd_EO.getValue())) {

            sMsg.shipToCustCd_EO.clear();
            sMsg.shipToLocNm_EO.clear();
            cMsg.shipToCustCd_EO.clear();
            cMsg.shipToLocNm_EO.clear();
        }
    }

    /**
     * Ship to Customer Multiple Check
     * @param sMsg NPBL0020BMsg
     * @param cMsg NPBL0020CMsg
     */
    public static void multipleCheckShipTo(NPBL0020SMsg sMsg, NPBL0020CMsg cMsg, String glblCmpyCd) {

        if (sMsg.A.getValidCount() == 0) {
            // Header Only
            Map<String, String> record = NPBL0020CommonLogic.getShipToCustAddr(cMsg, glblCmpyCd, cMsg.shipToCustCd_EO.getValue());
            if (record != null) {
                NPBL0020CommonLogic.setShipToAddr(cMsg, record);
            } else {
                NPBL0020CommonLogic.clearAddress(cMsg);
            }
        } else {
            // Multiple Check
            String shipToCustCdPre = null;

            boolean isShipToCustMulti = false;

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                String shipToCustCd = null;
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).shipToCustCd_E1)) {
                    shipToCustCd = sMsg.A.no(i).shipToCustCd_E1.getValue();
                }

                if ((ZYPCommonFunc.hasValue(shipToCustCd) && ZYPCommonFunc.hasValue(shipToCustCdPre)) && !shipToCustCdPre.equals(shipToCustCd)) {
                    NPBL0020CommonLogic.setShipToMultiple(cMsg);
                    isShipToCustMulti = true;
                }

                shipToCustCdPre = shipToCustCd;
            }

            if (!isShipToCustMulti && ZYPCommonFunc.hasValue(shipToCustCdPre)) {
                Map<String, String> record = NPBL0020CommonLogic.getShipToCustAddr(null, glblCmpyCd, shipToCustCdPre);
                if (record != null) {
                    NPBL0020CommonLogic.setShipToAddr(cMsg, record);
                } else {
                    NPBL0020CommonLogic.clearAddress(cMsg);
                }
            }
        }
    }

    /**
     * WH Multiple Check
     * @param sMsg NPBL0020BMsg
     * @param cMsg NPBL0020CMsg
     */
    public static void multipleCheckWh(NPBL0020SMsg sMsg, NPBL0020CMsg cMsg, String glblCmpyCd) {

        String destRtlWhCdPre = null;
        String destRtlSwhCdPre = null;
        String srcRtlWhCdPre = null;
        String srcRtlSwhCdPre = null;

        boolean isSrcWhMulti = false;
        boolean isDestWhMulti = false;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            String destRtlWhCd = null;
            String destRtlSwhCd = null;
            String srcRtlWhCd = null;
            String srcRtlSwhCd = null;

            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).destRtlWhCd_A1)) {
                destRtlWhCd = sMsg.A.no(i).destRtlWhCd_A1.getValue();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).destRtlSwhCd_A1)) {
                destRtlSwhCd = sMsg.A.no(i).destRtlSwhCd_A1.getValue();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).srcRtlWhCd_A1)) {
                srcRtlWhCd = sMsg.A.no(i).srcRtlWhCd_A1.getValue();
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).srcRtlSwhCd_A1)) {
                srcRtlSwhCd = sMsg.A.no(i).srcRtlSwhCd_A1.getValue();
            }
            if ((ZYPCommonFunc.hasValue(srcRtlWhCd) && ZYPCommonFunc.hasValue(srcRtlWhCdPre)) && !srcRtlWhCdPre.equals(srcRtlWhCd)) {
                if (PRCH_REQ_TP.DISPOSAL.equals(sMsg.prchReqTpCd_SL.getValue()) && !ZYPCommonFunc.hasValue(sMsg.vndCd)) {
                    NPBL0020CommonLogic.setShipToMultiple(cMsg);
                }
                isSrcWhMulti = true;
            }
            if ((ZYPCommonFunc.hasValue(srcRtlSwhCd) && ZYPCommonFunc.hasValue(srcRtlSwhCdPre)) && !srcRtlSwhCdPre.equals(srcRtlSwhCd)) {
                sMsg.srcRtlSwhCd.setValue(SCREEN_CTRL_VALUE_MULTIPLE);
                sMsg.rtlSwhNm_SS.clear();
            }
            if ((ZYPCommonFunc.hasValue(destRtlWhCd) && ZYPCommonFunc.hasValue(destRtlWhCdPre)) && !destRtlWhCdPre.equals(destRtlWhCd)) {
                sMsg.destRtlWhCd.setValue(SCREEN_CTRL_VALUE_MULTIPLE);
                sMsg.rtlWhNm_DW.clear();
                NPBL0020CommonLogic.setShipToMultiple(cMsg);
                isDestWhMulti = true;
            }
            if ((ZYPCommonFunc.hasValue(destRtlSwhCd) && ZYPCommonFunc.hasValue(destRtlSwhCdPre)) && !destRtlSwhCdPre.equals(destRtlSwhCd)) {
                sMsg.destRtlSwhCd.setValue(SCREEN_CTRL_VALUE_MULTIPLE);
                sMsg.rtlSwhNm_DS.clear();
            }

            destRtlWhCdPre = destRtlWhCd;
            destRtlSwhCdPre = destRtlSwhCd;
            srcRtlWhCdPre = srcRtlWhCd;
            srcRtlSwhCdPre = srcRtlSwhCd;
        }

        if (!isSrcWhMulti && ZYPCommonFunc.hasValue(srcRtlWhCdPre)) {
            if (PRCH_REQ_TP.DISPOSAL.equals(sMsg.prchReqTpCd_SL.getValue()) && !ZYPCommonFunc.hasValue(sMsg.vndCd)) {
                Map<String, String> record = NPBL0020CommonLogic.getShipToCustAddr(null, glblCmpyCd, srcRtlWhCdPre);
                if (record != null) {
                    NPBL0020CommonLogic.setShipToAddr(cMsg, record);
                } else {
                    NPBL0020CommonLogic.clearAddress(cMsg);
                }
            }
        }

        if (!isDestWhMulti && ZYPCommonFunc.hasValue(destRtlWhCdPre)) {
            Map<String, String> record = NPBL0020CommonLogic.getShipToCustAddr(null, glblCmpyCd, destRtlWhCdPre);
            if (record != null) {
                NPBL0020CommonLogic.setShipToAddr(cMsg, record);
            } else {
                NPBL0020CommonLogic.clearAddress(cMsg);
            }
        }
    }

    /**
     * Create Pulldown Service Level
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @param scrEntAvalFlg boolean /true;ScreenEntryMode
     * @param defPrchReqLineTpCd String
     */
    public static void createPullDownShpgSvcLvl(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {

        String srcShpgSvcLvlCd = null;
        boolean matchShpgSvcLvlCd = false;
        if (ZYPCommonFunc.hasValue(cMsg.shpgSvcLvlCd_SL.getValue())) {
            srcShpgSvcLvlCd = cMsg.shpgSvcLvlCd_SL.getValue();
        }

        // Clear Pulldown Data
        cMsg.shpgSvcLvlCd_PD.clear();
        cMsg.shpgSvcLvlCd_SL.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        if (PRCH_REQ_TP.EXPENSE_ORDER.equals(cMsg.prchReqTpCd_SL.getValue())) {
            ssmParam.put(DB_PARAM_FRT_CHRG_METH_CD, FRT_CHRG_METH.INVOICE_IN);
            ssmParam.put(DB_PARAM_FRT_CHRG_TO_CD, FRT_CHRG_TO.CUSTOMER);
        } else if (PRCH_REQ_TP.WH_TRANSFER.equals(cMsg.prchReqTpCd_SL.getValue())) {
            ssmParam.put(DB_PARAM_FRT_CHRG_METH_CD, FRT_CHRG_METH.N_OR_A);
            ssmParam.put(DB_PARAM_FRT_CHRG_TO_CD, FRT_CHRG_TO.CANON);
        }

        // Execute
        S21SsmEZDResult result = NPBL0020Query.getInstance().getShpgSvcLvlPulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map<String, String>> lineTypeList = (List<Map<String, String>>) result.getResultObject();

            for (int i = 0; i < lineTypeList.size(); i++) {
                Map<String, String> record = lineTypeList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_PD.no(i), (String) record.get(DB_COLUMN_SHPG_SVC_LVL_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlDescTxt_PD.no(i), (String) record.get(DB_COLUMN_SHPG_SVC_LVL_DESC_TXT));

                if (ZYPCommonFunc.hasValue(cMsg.shpgSvcLvlCd_PD.no(i)) && cMsg.shpgSvcLvlCd_PD.no(i).getValue().equals(srcShpgSvcLvlCd)) {
                    matchShpgSvcLvlCd = true;
                }
                if (i >= cMsg.shpgSvcLvlCd_PD.length() - 1) {
                    break;
                }
            }
        }

        String defShpgSvcLvlCd = ZYPCodeDataUtil.getVarCharConstValue(INVTY_REQ_DEF_SHPG_SVC_LVL_CD, glblCmpyCd);

        if (!matchShpgSvcLvlCd) {
            if (ZYPCommonFunc.hasValue(defShpgSvcLvlCd)) {
                ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_SL, defShpgSvcLvlCd);
            } else {
                if (ZYPCommonFunc.hasValue(cMsg.shpgSvcLvlCd_PD.no(0))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_SL, cMsg.shpgSvcLvlCd_PD.no(0).getValue());
                }
            }  
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_SL, srcShpgSvcLvlCd);
        }
    }

    /**
     * Check Avairable Qty
     * @param asMsg NPBL0020_ASMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private static boolean checkAvairableQty(NPBL0020_ASMsg asMsg, String glblCmpyCd) {

        INVTYTMsg inMsg = new INVTYTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.invtyLocCd, asMsg.srcRtlWhCd_A1.getValue() + asMsg.srcRtlSwhCd_A1.getValue());
        ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, asMsg.mdseCd_A1.getValue());
        ZYPEZDItemValueSetter.setValue(inMsg.stkStsCd, asMsg.fromStkStsCd_A1.getValue());
        ZYPEZDItemValueSetter.setValue(inMsg.locStsCd, LOC_STS.DC_STOCK);
        INVTYTMsg outMsg = (INVTYTMsg) EZDTBLAccessor.findByKey(inMsg);

        if (outMsg == null) {
            return false;
        }

        BigDecimal avalQty = outMsg.invtyAvalQty.getValue();
        BigDecimal curQty = asMsg.prchReqDispQty_A1.getValue();

        if (avalQty.compareTo(curQty) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    // START 2018/04/03 S.Katsuma [QC#23521,25063,ADD]
    /**
     * setRtlWhFromName
     * @param glblCmpyCd
     * @param whNm
     * @param whCd
     * @param whCatgCd
     */
    private static void setRtlWhFromName(String glblCmpyCd, EZDSStringItem whNm, EZDSStringItem whCd, EZDSStringItem whCatgCd) {

        // QC#29212
        // If RTL_WH_CD is not null and data only exists 1 record,
        // assume no change is made by hand.
        // Otherwise (0 or more than 1), find WH from RLT_WH_NM.
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        if(ZYPCommonFunc.hasValue(whCd)) {
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParam.put(DB_PARAM_RTL_WH_NM, whNm);
            ssmParam.put(DB_PARAM_RTL_WH_CD, whCd);
            S21SsmEZDResult result = NPBL0020Query.getInstance().findRtlWhFromName(ssmParam);
            if(result.getQueryResultCount()==1) {
                return;
            }
        }
        
        ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_WH_NM, whNm.getValue());
        S21SsmEZDResult result = NPBL0020Query.getInstance().findRtlWhFromName(ssmParam);

        if (result.isCodeNormal()) {
            List<Map<String, String>> rtlWhList = (List<Map<String, String>>) result.getResultObject();
            if (rtlWhList != null && !rtlWhList.isEmpty()) {
                Map<String, String> recode = rtlWhList.get(0);
                ZYPEZDItemValueSetter.setValue(whCd, (String) recode.get(DB_COLUMN_RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(whCatgCd, (String) recode.get(DB_COLUMN_RTL_WH_CATG_CD));
            }
        }
    }

    /**
     * getShipToCustCdForDetail
     * @param glblCmpyCd
     * @param cMsg
     * @param sMsg
     * @param i
     * @return boolean
     */
    private static boolean getShipToCustCdForDetail(String glblCmpyCd, NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, int i) {
        boolean errFlg = false;
        String errMsg = null;

        EZDSStringItem ezdShipToCustCd = sMsg.A.no(i).shipToCustCd_E1;
        EZDSStringItem ezdShipToLocNm = sMsg.A.no(i).shipToLocNm_E1;
        String shipToCustCd = ezdShipToCustCd.getValue();
        if (ZYPCommonFunc.hasValue(ezdShipToLocNm)) {
            int count = getShipToCustCd(glblCmpyCd, ezdShipToCustCd, ezdShipToLocNm);
            if (count == 0) {

                ezdShipToCustCd.clear();
                count = getShipToCustCd(glblCmpyCd, ezdShipToCustCd, ezdShipToLocNm);
                if (count == 0) {
                    errMsg = NPAM1361E;
                    errFlg = true;
                } else if (count > 1) {
                    errMsg = NPBM0001E;
                    errFlg = true;
                }
            } else if (count > 1) {
                errMsg = NPBM0001E;
                errFlg = true;
            }
        }

        if (errFlg) {
            ezdShipToLocNm.setErrorInfo(1, errMsg, new String[] {DISPLAY_NM_SHIP_TO_LOC_NM });
            ZYPEZDItemValueSetter.setValue(ezdShipToCustCd, shipToCustCd);
            setPageShowFromNumOnCMsg(cMsg, sMsg, i);
        }

        return errFlg;
    }

    /**
     * getChrgAcctEdtblFlg
     * @param prchReqTpCd
     * @param glblCmpyCd
     * @return String
     */
    public static String getChrgAcctEdtblFlg(String prchReqTpCd, String glblCmpyCd) {
        String chrgAcctEdtblFlg = null;

        PRCH_REQ_TPTMsg prchReqTMsg = findPrReqTpTMsg(glblCmpyCd, prchReqTpCd);
        if (prchReqTMsg != null) {
            chrgAcctEdtblFlg = prchReqTMsg.chrgAcctEdtblFlg.getValue();
        } else {
            chrgAcctEdtblFlg = ZYPConstant.FLG_OFF_N;
        }

        return chrgAcctEdtblFlg;
    }

    /**
     * getConfigItemList
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd NPBL0020SMsg
     * @param prchReqLineNum String
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getConfigItemList(NPBL0020SMsg sMsg, String glblCmpyCd, String prchReqLineNum) {
        List<Map<String, Object>> configItemList = new ArrayList<Map<String, Object>>();

        for (int n = 0; n < sMsg.A.getValidCount(); ++n) {
            if (!prchReqLineNum.equals(sMsg.A.no(n).prchReqLineNum_A1.getValue())) {
                continue;
            }
            if(!ZYPCommonFunc.hasValue(sMsg.A.no(n).mdseCd_A1.getValue())) {
                continue;
            }
            Map<String, Object> configItem = getConfigItemLineInfo(glblCmpyCd, sMsg.A.no(n));
            if(configItem!=null) {
                configItemList.add(configItem);
            }
        }
        return configItemList;
    }

    /**
     * getMainMachFromConfigItemList
     * @param configItemList List<Map<String, Object>>
     * @return Map<String, Object>
     */
    public static Map<String, Object> getMainMachFromConfigItemList(List<Map<String, Object>> configItemList) {
        Map<String, Object> mainMach = null;
        for (Map<String, Object> configItem : configItemList) {
            String mdseTpCtxTpCd = (String) configItem.get(MAP_KEY_MDSE_TP_CTX_TP_CD);
            if (ZYPCommonFunc.hasValue(mdseTpCtxTpCd) && MDSE_TP_CTX_TP.MAIN_MACHINE.equals(mdseTpCtxTpCd)) {
                mainMach = configItem;
                break;
            }
            String instlBaseCtrlFlg = (String) configItem.get(MAP_KEY_INSTL_BASE_CTRL_FLG);
            if (ZYPConstant.FLG_ON_Y.equals(instlBaseCtrlFlg)) {
                // Maybe Main Machine
                mainMach = configItem;
            }
        }

        return mainMach;
    }

    /**
     * createServiceModelAPIParam
     * @param glblCmpyCd String
     * @param mainMach Map<String, Object>
     * @param configItemList List<Map<String, Object>>
     * @return NSZC048001PMsg
     */
    public static NSZC048001PMsg createServiceModelAPIParam(String glblCmpyCd, Map<String, Object> mainMach, List<Map<String, Object>> configItemList) {
        NSZC048001PMsg pMsg = new NSZC048001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));

        ZYPEZDItemValueSetter.setValue(pMsg.prntMdseCd, (String) mainMach.get(MAP_KEY_MDSE_CD));

        BigDecimal mainLineSubNum = (BigDecimal) mainMach.get(MAP_KEY_PRCH_REQ_LINE_SUB_NUM);
        int count=0;
        for (Map<String, Object> configItem : configItemList) {
            BigDecimal itemLineSubNum = (BigDecimal) configItem.get(MAP_KEY_PRCH_REQ_LINE_SUB_NUM);
            if (mainLineSubNum.compareTo(itemLineSubNum) == 0) {
                continue;
            }

            ZYPEZDItemValueSetter.setValue(pMsg.xxChildMdseCdList.no(count++).childMdseCd, (String) configItem.get(MAP_KEY_MDSE_CD));
        }
        pMsg.xxChildMdseCdList.setValidCount(count);

        return pMsg;
    }

    /**
     * getIndexByLineSubNum
     * @param sMsg NPBL0020SMsg
     * @param lineNum String
     * @param lineSubNum String
     * @return int
     */
    public static int getIndexByLineSubNum(NPBL0020SMsg sMsg, String lineNum, BigDecimal lineSubNum) {
        int index = -1;

        for (int n = 0; n < sMsg.A.getValidCount(); ++n) {
            if (lineNum.equals(sMsg.A.no(n).prchReqLineNum_A1.getValue()) && lineSubNum.compareTo(sMsg.A.no(n).prchReqLineSubNum_A1.getValue()) == 0) {
                index = n;
                break;
            }
        }

        return index;
    }

    /**
     * isNeededAccount
     * @param chrgAcctEdtblFlg
     * @return boolean
     */
    public static boolean isNeededAccount(String chrgAcctEdtblFlg) {
        return isFlgOn(chrgAcctEdtblFlg);
    }

    /**
     * isFlgOn
     * @param <T>
     * @param flg
     * @return boolean
     */
    private static <T> boolean isFlgOn(T obj) {
        boolean ret = false;

        if (obj != null) {
            if (obj instanceof String) {
                String flg = obj.toString();

                if (ZYPConstant.FLG_ON_Y.equals(flg)) {
                    ret = true;
                } else {
                    ret = false;
                }
            } else {
                ret = false;
            }
        } else {
            ret = false;
        }

        return ret;
    }
    // END 2018/04/03 S.Katsuma [QC#23521,25063,ADD]

    // START 2018/05/25 S.Katsuma [QC#25893,ADD]
    /**
     * getInvtyItemCost
     * @pamram glblCmpyCd String
     * @pamram String String
     * @pamram rtlSwhCd String
     * @pamram mdseCd String
     * @pamram qty BigDecimal
     */
    public static NLXC001001GetInventoryItemCostBean getInvtyItemCost(String glblCmpyCd, String rtlWhCd, String rtlSwhCd, String mdseCd, BigDecimal qty) {
        NLXC001001GetInventoryItemCostBean nlxc001001Bean = new NLXC001001GetInventoryItemCostBean();
        nlxc001001Bean.setGlblCmpyCd(glblCmpyCd);
        nlxc001001Bean.setInvtyLocCd(ZYPCommonFunc.concatString(rtlWhCd, "", rtlSwhCd));
        nlxc001001Bean.setMdseCd(mdseCd);
        if (ZYPCommonFunc.hasValue(qty)) {
            nlxc001001Bean.setQty(qty);
        } else {
            nlxc001001Bean.setQty(BigDecimal.ONE);
        }

        NLXC001001GetInventoryItemCost.getInventoryItemCost(nlxc001001Bean);
        return nlxc001001Bean;
    }
    // END 2018/05/25 S.Katsuma [QC#25893,ADD]

    // START 2018/06/01 S.Katsuma [QC#26234,ADD]
    private static boolean isConfigParentDetail(NPBL0020_ASMsg aMsg) {
        if (BigDecimal.ZERO.equals(aMsg.prchReqLineSubNum_A1.getValue()) && ZYPCommonFunc.hasValue(aMsg.configTpCd_A1)) {
            return true;
        } else {
            return false;
        }
    }

    private static List<String> getSerNeededReqTp(String glblCmpyCd) {
        List<String> serNeededReqTp = null;

        String varCharConstVal = ZYPCodeDataUtil.getVarCharConstValue(NPBL0020_SER_NEED_PRCH_REQ_TP, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(varCharConstVal)) {
            serNeededReqTp = Arrays.asList(varCharConstVal.split(","));
        }
        return serNeededReqTp;
    }
    // END 2018/06/01 S.Katsuma [QC#26234,ADD]

    // START 2018/11/06 M.Naito [QC#28698,ADD]
    /**
     * isExistConfig
     * @param asMsg NPBL0020_ASMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean isExistConfig(NPBL0020_ASMsg asMsg, String glblCmpyCd) {

        if (!ZYPCommonFunc.hasValue(asMsg.xxLogDtlTxt_A1) || ZYPCommonFunc.hasValue(asMsg.svcConfigMstrPk_A1.getValue())) {
            return false;
        }
        String[] serialNumber = asMsg.xxLogDtlTxt_A1.getValue().split(SCREEN_CTRL_VALUE_SERIAL_NUM_DELIM);
        for (int i = 0; i < serialNumber.length; i++) {
            String curLocNum = S21StringUtil.concatStrings(asMsg.srcRtlWhCd_A1.getValue(), asMsg.srcRtlSwhCd_A1.getValue());
            if (NPBL0020Query.getInstance().checkExistConfig(glblCmpyCd, asMsg.mdseCd_A1.getValue(), serialNumber[i], curLocNum, asMsg.fromStkStsCd_A1.getValue())) {
                return true;
            }
        }
        return false;
    }
    // END 2018/11/06 M.Naito [QC#28698,ADD]

    // 12/12/2018 QC#29456 Add Start
    /**
     * <pre>
     * call getAccountForce after clear xxScrItem50Txt_A1 (current Charge Account)
     * @param cMsg Biz Message
     * @param sMsg Global Message
     * @param glblCmpyCd Global Company Code
     * @param rowIndex rowIndex of Global Message "A" table
     * @param submitFlg Submit Flag
     * @param winFlg Window Flag
     * @return true: Success false: error
     * </pre>
     */
    public static boolean getAccountForce(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd, int rowIndex, boolean submitFlg, boolean winFlg) {

        int bizIndex = cMsg.xxNum.getValueInt();
        // QC#57052
        if (bizIndex < 0) {
            return false;
        }
        // 01/28/2019 QC#29778-2 Add Start
        // START 2019/09/25 T.Ogura [QC#53694,MOD]
//        applyChrgAccount(cMsg.A.no(bizIndex), glblCmpyCd);
        if (!applyChrgAccount(cMsg.A.no(bizIndex), glblCmpyCd, sMsg.A.no(rowIndex))) {
            return false;
        }
        // END   2019/09/25 T.Ogura [QC#53694,MOD]
        copy9SegDataToGlblMsg(cMsg.A.no(bizIndex), sMsg.A.no(rowIndex));
        // 01/28/2019 QC#29778-2 Add End
        cMsg.A.no(bizIndex).xxScrItem50Txt_A1.clear();
        sMsg.A.no(rowIndex).xxScrItem50Txt_A1.clear();
        return getAccount(cMsg, sMsg, glblCmpyCd, rowIndex, submitFlg, winFlg, true); // 02/08/2019 QC#30181 Add 7th param: true
    }
    // 12/12/2018 QC#29456 Add End

    // 01/28/2019 QC#29778-2 Add Start
    private static String getValidStr(EZDSStringItem item, String val) {

        if (ZYPCommonFunc.hasValue(val)) {
            return val;
        } else {
            return item.getValue();
        }
    }

    // START 2019/09/25 T.Ogura [QC#53694,MOD]
//    private static void applyChrgAccount(NPBL0020_ACMsg acMsg, String glblCmpyCd) {
    private static boolean applyChrgAccount(NPBL0020_ACMsg acMsg, String glblCmpyCd, NPBL0020_ASMsg asMsg) {
    // END   2019/09/25 T.Ogura [QC#53694,MOD]

        String chargAcctText = acMsg.xxScrItem50Txt_A1.getValue();
        if (!ZYPCommonFunc.hasValue(chargAcctText)) {
            acMsg.coaCmpyCd_A1.clear();
            acMsg.coaBrCd_A1.clear();
            acMsg.coaCcCd_A1.clear();
            acMsg.coaAcctCd_A1.clear();
            acMsg.coaProdCd_A2.clear();
            acMsg.coaChCd_A1.clear();
            acMsg.coaAfflCd_A1.clear();
            acMsg.coaProjCd_A1.clear();
            acMsg.coaExtnCd_A1.clear();

            // START 2019/09/25 T.Ogura [QC#53694,MOD]
//            return;
            return true;
            // END   2019/09/25 T.Ogura [QC#53694,MOD]
        }
        List<String> tokenList = tokenListSplit(chargAcctText, glblCmpyCd);
        if (tokenList.size() != SEGMENT_TOKEN_LIST_SIZE) {
            // START 2019/09/25 T.Ogura [QC#53694,MOD]
//            return;
            asMsg.xxScrItem50Txt_A1.setErrorInfo(1, NPBM0005E, new String[] {MSG_PARAM_SEGMENT });
            return false;
            // END   2019/09/25 T.Ogura [QC#53694,MOD]
        }

        // START 2019/09/25 T.Ogura [QC#53694,ADD]
        // coaCmpyCd check
        if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD), SEGMENT_ELEMENT_LENGTH_CMPY)) {
            asMsg.xxScrItem50Txt_A1.setErrorInfo(1, NPBM0006E, new String[] {MSG_PARAM_CMPY });
            return false;
        }

        // coaExtnCd check
        if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD), SEGMENT_ELEMENT_LENGTH_EXTN)) {
            asMsg.xxScrItem50Txt_A1.setErrorInfo(1, NPBM0006E, new String[] {MSG_PARAM_EXTN });
            return false;
        }

        // coaCcCd check
        if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD), SEGMENT_ELEMENT_LENGTH_CC)) {
            asMsg.xxScrItem50Txt_A1.setErrorInfo(1, NPBM0006E, new String[] {MSG_PARAM_CC });
            return false;
        }

        // coaAcctCd check
        if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD), SEGMENT_ELEMENT_LENGTH_ACCT)) {
            asMsg.xxScrItem50Txt_A1.setErrorInfo(1, NPBM0006E, new String[] {MSG_PARAM_ACCT });
            return false;
        }

        // coaProjCd check
        if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD), SEGMENT_ELEMENT_LENGTH_PROJ)) {
            asMsg.xxScrItem50Txt_A1.setErrorInfo(1, NPBM0006E, new String[] {MSG_PARAM_PROJ });
            return false;
        }

        // coaProdCd check
        if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD), SEGMENT_ELEMENT_LENGTH_PROD)) {
            asMsg.xxScrItem50Txt_A1.setErrorInfo(1, NPBM0006E, new String[] {MSG_PARAM_PROD });
            return false;
        }

        // coaAfflCd check
        if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD), SEGMENT_ELEMENT_LENGTH_AFFL)) {
            asMsg.xxScrItem50Txt_A1.setErrorInfo(1, NPBM0006E, new String[] {MSG_PARAM_AFFL });
            return false;
        }

        // coaChCd check
        if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD), SEGMENT_ELEMENT_LENGTH_CH)) {
            asMsg.xxScrItem50Txt_A1.setErrorInfo(1, NPBM0006E, new String[] {MSG_PARAM_CH });
            return false;
        }

        // coaBrCd check
        if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD), SEGMENT_ELEMENT_LENGTH_BR)) {
            asMsg.xxScrItem50Txt_A1.setErrorInfo(1, NPBM0006E, new String[] {MSG_PARAM_BR });
            return false;
        }
        // END   2019/09/25 T.Ogura [QC#53694,ADD]

        int i = 0;
        ZYPEZDItemValueSetter.setValue(acMsg.coaCmpyCd_A1, tokenList.get(i++));
        ZYPEZDItemValueSetter.setValue(acMsg.coaBrCd_A1, tokenList.get(i++));
        ZYPEZDItemValueSetter.setValue(acMsg.coaCcCd_A1, tokenList.get(i++));
        ZYPEZDItemValueSetter.setValue(acMsg.coaAcctCd_A1, tokenList.get(i++));
        ZYPEZDItemValueSetter.setValue(acMsg.coaProdCd_A2, tokenList.get(i++));
        ZYPEZDItemValueSetter.setValue(acMsg.coaChCd_A1, tokenList.get(i++));
        ZYPEZDItemValueSetter.setValue(acMsg.coaAfflCd_A1, tokenList.get(i++));
        ZYPEZDItemValueSetter.setValue(acMsg.coaProjCd_A1, tokenList.get(i++));
        ZYPEZDItemValueSetter.setValue(acMsg.coaExtnCd_A1, tokenList.get(i++));

        // START 2019/09/25 T.Ogura [QC#53694,MOD]
//        return;
        return true;
        // END   2019/09/25 T.Ogura [QC#53694,MOD]
    }

    private static void copy9SegDataToGlblMsg(NPBL0020_ACMsg acMsg, NPBL0020_ASMsg asMsg) {

        ZYPEZDItemValueSetter.setValue(asMsg.coaCmpyCd_A1, acMsg.coaCmpyCd_A1);
        ZYPEZDItemValueSetter.setValue(asMsg.coaBrCd_A1, acMsg.coaBrCd_A1);
        ZYPEZDItemValueSetter.setValue(asMsg.coaCcCd_A1, acMsg.coaCcCd_A1);
        ZYPEZDItemValueSetter.setValue(asMsg.coaAcctCd_A1, acMsg.coaAcctCd_A1);
        ZYPEZDItemValueSetter.setValue(asMsg.coaProdCd_A2, acMsg.coaProdCd_A2);
        ZYPEZDItemValueSetter.setValue(asMsg.coaChCd_A1, acMsg.coaChCd_A1);
        ZYPEZDItemValueSetter.setValue(asMsg.coaAfflCd_A1, acMsg.coaAfflCd_A1);
        ZYPEZDItemValueSetter.setValue(asMsg.coaProjCd_A1, acMsg.coaProjCd_A1);
        ZYPEZDItemValueSetter.setValue(asMsg.coaExtnCd_A1, acMsg.coaExtnCd_A1);

        return;
    }

    /**
     * <pre>
     * Getting Account Defaulting Mode.
     * @param bizMsg Biz. Message
     * @param dtlMsg target detail line global message
     * @return 0: No Operation 1: Defaulting Ship To And Item 2: Defaulting Item only
     * </ore>
     */
    private static AcctDefMode getAccountDefaultingMode(NPBL0020CMsg bizMsg, NPBL0020_ASMsg dtlMsg) {

        AcctDefMode rslt = AcctDefMode.NO_OPE;
        if (!ZYPCommonFunc.hasValue(dtlMsg.shipToCustCd_E1) && !ZYPCommonFunc.hasValue(dtlMsg.mdseCd_A1)) {
            return AcctDefMode.NO_OPE;
        }
        String screenAplID = bizMsg.getScreenAplID();
        if (EVENT_NM_NPBL0020_GET_ITEM_INFO.equals(screenAplID) //
                || EVENT_NM_NPBL0020_NMAL6800.equals(screenAplID)) {
            rslt = AcctDefMode.ITEM;

            EZDSStringItem[] checkItemList = new EZDSStringItem []{
                    dtlMsg.coaCmpyCd_A1,
                    dtlMsg.coaBrCd_A1,
                    dtlMsg.coaCcCd_A1,
                    dtlMsg.coaAcctCd_A1,
                    dtlMsg.coaProdCd_A2,
                    dtlMsg.coaChCd_A1,
                    dtlMsg.coaAfflCd_A1,
                    dtlMsg.coaProjCd_A1,
                    dtlMsg.coaExtnCd_A1
            };

            for (EZDSStringItem checkItem : checkItemList) {
                if (!ZYPCommonFunc.hasValue(checkItem)) {
                    rslt = AcctDefMode.SHIP_ITEM;
                    break;
                }
            }
        } else if (EVENT_NM_NPBL0020_NMAL6760.equals(screenAplID)) {
            rslt = AcctDefMode.SHIP;
        } else {
            rslt = AcctDefMode.SHIP_ITEM;
        }

        return rslt;
    }
    // 01/28/2019 QC#29778-2 Add End

    // 02/08/2019 QC#30181 Add Start
    /**
     * fillAccountText Set xxScrItem130Txt_A1
     * @param asMsg
     */
    private static void fillChrgAccountText(NPBL0020_ASMsg asMsg) {
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

        ZYPEZDItemValueSetter.setValue(asMsg.xxScrItem50Txt_A1, sb.toString());
    }
    // 02/08/2019 QC#30181 Add End

    // START 2019/05/17 M.Naito [QC#50076,ADD]
    /**
     * getRtlWhCatgCd
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return rtlWhCatgCd or null
     */
    private static String getRtlWhCatgCd(String glblCmpyCd, String rtlWhCd) {

        Map<String, String> param = new HashMap<String, String>();
        param.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        param.put(DB_PARAM_SALES_DATE, ZYPDateUtil.getSalesDate(glblCmpyCd));
        param.put(DB_PARAM_RTL_WH_CD, rtlWhCd);

        S21SsmEZDResult result = NPBL0020Query.getInstance().getRtlWhCatgCd(param);

        if (result.isCodeNormal()) {

            return (String) result.getResultObject();
        }

        return null;
    }

    /**
     * checkDefSwhByWhCatg
     * @param glblCmpyCd
     * @param srcRtlWhCatgCd
     * @param destRtlWhCatgCd
     * @param mdlGrpId
     * @return
     */
    private static boolean checkDefSwhByWhCatg(String glblCmpyCd, String srcRtlWhCatgCd, String destRtlWhCatgCd, BigDecimal mdlGrpId) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("srcRtlWhCatgCd", srcRtlWhCatgCd);
        ssmParam.put("astr", ASTERISK);
        ssmParam.put("destRtlWhCatgCd", destRtlWhCatgCd);
        ssmParam.put("mdlGrpId", mdlGrpId);
        ssmParam.put("defMdlGrpId", new BigDecimal(-1));

        S21SsmEZDResult result = NPBL0020Query.getInstance().checkDefSwhByWhCatg(ssmParam);

        if (result.isCodeNormal()) {

            Integer count = (Integer) result.getResultObject();
            if (count.intValue() > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    // END 2019/05/17 M.Naito [QC#50076,ADD]
    // START 2019/08/27 M.Naito [QC#52276,ADD]
    /**
     * checkStkStsCd
     * @param glblCmpyCd String
     * @param asMsg NPBL0020_ASMsg
     * @return boolean
     */
    private static boolean checkStkStsCd(NPBL0020_ASMsg asMsg, String glblCmpyCd) {

        if (!ZYPCommonFunc.hasValue(asMsg.xxLogDtlTxt_A1)) {
            return true;
        }
        String[] serialNumber = asMsg.xxLogDtlTxt_A1.getValue().split(SCREEN_CTRL_VALUE_SERIAL_NUM_DELIM);
        for (int i = 0; i < serialNumber.length; i++) {

            SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
            svcMachMstrTMsg.setSQLID("001");
            svcMachMstrTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            svcMachMstrTMsg.setConditionValue("serNum01", serialNumber[i]);
            svcMachMstrTMsg.setConditionValue("mdseCd01", asMsg.mdseCd_A1.getValue());

            SVC_MACH_MSTRTMsgArray rsltSvcMachMstrTMsgArray = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(svcMachMstrTMsg);

            if (rsltSvcMachMstrTMsgArray == null || rsltSvcMachMstrTMsgArray.getValidCount() == 0) {
                return true;
            }
            if (!hasValue(rsltSvcMachMstrTMsgArray.no(0).stkStsCd) || !rsltSvcMachMstrTMsgArray.no(0).stkStsCd.getValue().equals(asMsg.fromStkStsCd_A1.getValue())) {
                return false;
            }
        }
        return true;
    }
    // END 2019/08/27 M.Naito [QC#52276,ADD]

    // QC#52809 Add
    private static NMZC610001PMsg callCustInfoGetApiForDefaultMode(NPBL0020_ASMsg asMsg, String glblCmpyCd, String dsAcctNum, String shipToCustCd, String billToCustCd, String xxMode, String xxSelFlg, boolean isConfig) {

        Map<String, Object> ordInfoMap = getOrdInfo(glblCmpyCd, isConfig);

        NWXC150001DefaultCustomerBean paramBean = new NWXC150001DefaultCustomerBean();
        paramBean.setGlblCmpyCd(glblCmpyCd);
        paramBean.setDsTrxRuleTpCd(getDsTrxRuleTpCd(glblCmpyCd, ordInfoMap));
        paramBean.setDsAcctNum(dsAcctNum);
        if (ZYPCommonFunc.hasValue(shipToCustCd)) {
            paramBean.setShipToCustCd(shipToCustCd);
        } else if (ZYPCommonFunc.hasValue(billToCustCd)) {
            if (!S21StringUtil.isEquals(NWXC150001DefaultCustomer.MODE_SHIP_ACCT_TO_BILL_TO, xxMode)) {
                paramBean.setBillToCustCd(billToCustCd);
            }
        }
        paramBean.setXxMode(xxMode);
        paramBean.setXxSelFlg(xxSelFlg);
        paramBean.setOnBatchType(ONBATCH_TYPE.ONLINE);

        NMZC610001PMsg pMsg = NWXC150001DefaultCustomer.getDefaultCustomerData(paramBean);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                asMsg.shipToLocNm_E1.setErrorInfo(1, msgId, msgPrms);
                return null;
            }
        }

        return pMsg;
    }

    private static String getDsTrxRuleTpCd(String glblCmpyCd, Map<String, Object> ordInfoMap) {

        String dsOrdCatgCd = (String) ordInfoMap.get(DS_ORD_CATG_CD);
        String dsOrdTpCd = (String) ordInfoMap.get(DS_ORD_TP_CD);

        ORD_CATG_BIZ_CTXTMsg condition = new ORD_CATG_BIZ_CTXTMsg();
        condition.setSQLID("003");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("ordCatgCtxTpCd01A", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
        condition.setConditionValue("ordCatgCtxTpCd01B", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        condition.setConditionValue("dsOrdCatgCd01", dsOrdCatgCd);
        condition.setConditionValue("dsOrdTpCd01", dsOrdTpCd);

        ORD_CATG_BIZ_CTXTMsgArray tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).firstBizCtxAttrbTxt.getValue();
        }

        condition = new ORD_CATG_BIZ_CTXTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("ordCatgCtxTpCd01A", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
        condition.setConditionValue("ordCatgCtxTpCd01B", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        condition.setConditionValue("dsOrdCatgCd01", dsOrdCatgCd);

        tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).firstBizCtxAttrbTxt.getValue();
        }

        return BLANK;
    }


    private static Map<String, Object> getOrdInfo(String glblCmpyCd, boolean isConfig) {

        Map<String, Object> ordInfoMap = NPBL0020Query.getInstance().getOrdInfo(glblCmpyCd, isConfig);
        return ordInfoMap;
    }
    // QC#52809 End

    // QC#56867 Add Start
    private static boolean checkLineCancelOrClose(String glblCmpyCd, NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, List<Integer> selectRows, boolean isCancel) {

        if (PRCH_REQ_APVL_STS.AWAITING_APPROVAL.equals(cMsg.prchReqApvlStsCd.getValue())) {
            cMsg.setMessageInfo(NPAM0008E);
            return true;
        }

        boolean isError = false;
        int preConfigLineNum = -1;
        for (int i = 0; i < selectRows.size(); i++) {

            NPBL0020_ASMsg asMsg = sMsg.A.no(selectRows.get(i));

            PRCH_REQ_DTLTMsg dtlTMsg = findPRDtl(asMsg, glblCmpyCd);

            if(dtlTMsg == null) {
                continue;
            }

            if (!ZYPCommonFunc.hasValue(asMsg.mdseCd_A1)) {
                // config line
                preConfigLineNum = selectRows.get(i);
                continue;
            }

            String prchReqLineStsCd = dtlTMsg.prchReqLineStsCd.getValue();

            if (PRCH_REQ_REL_STS.COMPLEATED.equals(dtlTMsg.prchReqRelStsCd.getValue())) {
                // After Inventory Request Release batch process.
                PRCH_REQ_TPTMsg prchReqTpTMsg = findPrReqTpTMsg(glblCmpyCd, cMsg.prchReqTpCd_SL.getValue());
                String trxSrcTpCd = prchReqTpTMsg.trxSrcTpCd.getValue();

                if (TRX_SRC_TP.WHOLE_SALES.equals(trxSrcTpCd)) {

                    if (PRCH_REQ_TP.WH_TRANSFER.equals(cMsg.prchReqTpCd_SL.getValue()) && PRCH_REQ_LINE_STS.RECEIVED.equals(prchReqLineStsCd)) {
                        asMsg.xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                        if (preConfigLineNum > -1 && ZYPCommonFunc.hasValue(asMsg.prchReqLineSubNum_A1) && BigDecimal.ZERO.compareTo(asMsg.prchReqLineSubNum_A1.getValue()) < 0) {
                            sMsg.A.no(preConfigLineNum).xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                        }
                        isError = true;
                        setPageShowFromNumOnCMsg(cMsg, sMsg, selectRows.get(i));
                        continue;
                    }

                    if (PRCH_REQ_TP.EXPENSE_ORDER.equals(cMsg.prchReqTpCd_SL.getValue()) && PRCH_REQ_LINE_STS.SHIPPED.equals(prchReqLineStsCd)) {
                        asMsg.xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                        if (preConfigLineNum > -1 && ZYPCommonFunc.hasValue(asMsg.prchReqLineSubNum_A1) && BigDecimal.ZERO.compareTo(asMsg.prchReqLineSubNum_A1.getValue()) < 0) {
                            sMsg.A.no(preConfigLineNum).xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                        }
                        isError = true;
                        setPageShowFromNumOnCMsg(cMsg, sMsg, selectRows.get(i));
                        continue;
                    }

                    if (PRCH_REQ_LINE_STS.CANCELLED.equals(prchReqLineStsCd)) {
                        asMsg.xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                        if (preConfigLineNum > -1 && ZYPCommonFunc.hasValue(asMsg.prchReqLineSubNum_A1) && BigDecimal.ZERO.compareTo(asMsg.prchReqLineSubNum_A1.getValue()) < 0) {
                            sMsg.A.no(preConfigLineNum).xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                        }
                        isError = true;
                        setPageShowFromNumOnCMsg(cMsg, sMsg, selectRows.get(i));
                        continue;
                    }

                    // DS_OMPT_ORD Check
                    List<String> imptStsList = new ArrayList<String>();
                    imptStsList.add(IMPT_STS.NOT_PROCESSED);
                    imptStsList.add(IMPT_STS.VALIDATED);

                    if (NPBL0020Query.getInstance().isExistDsImptOrd(glblCmpyCd, sMsg.prchReqNum.getValue(), asMsg.prchReqLineNum_A1.getValue(), asMsg.prchReqLineSubNum_A1.getValue(), imptStsList)) {
                        asMsg.xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                        if (preConfigLineNum > -1 && ZYPCommonFunc.hasValue(asMsg.prchReqLineSubNum_A1) && BigDecimal.ZERO.compareTo(asMsg.prchReqLineSubNum_A1.getValue()) < 0) {
                            sMsg.A.no(preConfigLineNum).xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                        }
                        isError = true;
                        setPageShowFromNumOnCMsg(cMsg, sMsg, selectRows.get(i));
                        continue;
                    }
                    // CPO Status Check
                    Map<String, Object> ordInfoMap = NPBL0020Query.getInstance().findCpoInfo(glblCmpyCd, sMsg.prchReqNum.getValue(), asMsg.prchReqLineNum_A1.getValue(), asMsg.prchReqLineSubNum_A1.getValue());
                    if (ordInfoMap != null && !ordInfoMap.isEmpty()) {
                        String ordLineStsCd = (String) ordInfoMap.get("ORD_LINE_STS_CD");
                        String rwsStsCd = (String) ordInfoMap.get("RWS_STS_CD");
                        String shpgPlnDplyStsCd = (String) ordInfoMap.get("SHPG_PLN_DPLY_STS_CD");
                        if (isCancel && !ORD_LINE_STS.CANCELLED.equals(ordLineStsCd)) {
                            asMsg.xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                            if (preConfigLineNum > -1 && ZYPCommonFunc.hasValue(asMsg.prchReqLineSubNum_A1) && BigDecimal.ZERO.compareTo(asMsg.prchReqLineSubNum_A1.getValue()) < 0) {
                                sMsg.A.no(preConfigLineNum).xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                            }
                            isError = true;
                            setPageShowFromNumOnCMsg(cMsg, sMsg, selectRows.get(i));
                            continue;
                        } else {
                            if (ORD_LINE_STS.CLOSED.equals(ordLineStsCd)) {
                                continue;
                            }
                            if (ZYPCommonFunc.hasValue(rwsStsCd) && !isCancel && !RWS_STS.RECEIVED.equals(rwsStsCd)) {
                                asMsg.xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                                if (preConfigLineNum > -1 && ZYPCommonFunc.hasValue(asMsg.prchReqLineSubNum_A1) && BigDecimal.ZERO.compareTo(asMsg.prchReqLineSubNum_A1.getValue()) < 0) {
                                    sMsg.A.no(preConfigLineNum).xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                                }
                                isError = true;
                                setPageShowFromNumOnCMsg(cMsg, sMsg, selectRows.get(i));
                                continue;
                            } else if (!isCancel && !SHPG_PLN_DPLY_STS.CLOSED.equals(shpgPlnDplyStsCd)) {
                                asMsg.xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                                if (preConfigLineNum > -1 && ZYPCommonFunc.hasValue(asMsg.prchReqLineSubNum_A1) && BigDecimal.ZERO.compareTo(asMsg.prchReqLineSubNum_A1.getValue()) < 0) {
                                    sMsg.A.no(preConfigLineNum).xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                                }
                                isError = true;
                                setPageShowFromNumOnCMsg(cMsg, sMsg, selectRows.get(i));
                                continue;
                            }
                        }
                    } else if (!isCancel) {
                        asMsg.xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                        if (preConfigLineNum > -1 && ZYPCommonFunc.hasValue(asMsg.prchReqLineSubNum_A1) && BigDecimal.ZERO.compareTo(asMsg.prchReqLineSubNum_A1.getValue()) < 0) {
                            sMsg.A.no(preConfigLineNum).xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                        }
                        isError = true;
                        setPageShowFromNumOnCMsg(cMsg, sMsg, selectRows.get(i));
                        continue;
                    }

                } else if (TRX_SRC_TP.MOVEMENT.equals(trxSrcTpCd) || TRX_SRC_TP.VENDOR_RETURN.equals(trxSrcTpCd)) {

                    if (PRCH_REQ_LINE_STS.SHIPPED.equals(prchReqLineStsCd)) {
                        asMsg.xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                        if (preConfigLineNum > -1 && ZYPCommonFunc.hasValue(asMsg.prchReqLineSubNum_A1) && BigDecimal.ZERO.compareTo(asMsg.prchReqLineSubNum_A1.getValue()) < 0) {
                            sMsg.A.no(preConfigLineNum).xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                        }
                        isError = true;
                        setPageShowFromNumOnCMsg(cMsg, sMsg, selectRows.get(i));
                        continue;
                    }

                    if (isCancel && PRCH_REQ_LINE_STS.PARTIALLY_SHIPPED.equals(prchReqLineStsCd)) {
                        asMsg.xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                        if (preConfigLineNum > -1 && ZYPCommonFunc.hasValue(asMsg.prchReqLineSubNum_A1) && BigDecimal.ZERO.compareTo(asMsg.prchReqLineSubNum_A1.getValue()) < 0) {
                            sMsg.A.no(preConfigLineNum).xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                        }
                        isError = true;
                        setPageShowFromNumOnCMsg(cMsg, sMsg, selectRows.get(i));
                        continue;
                    }

                    if (PRCH_REQ_LINE_STS.CANCELLED.equals(prchReqLineStsCd)) {
                        asMsg.xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                        if (preConfigLineNum > -1 && ZYPCommonFunc.hasValue(asMsg.prchReqLineSubNum_A1) && BigDecimal.ZERO.compareTo(asMsg.prchReqLineSubNum_A1.getValue()) < 0) {
                            sMsg.A.no(preConfigLineNum).xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                        }
                        isError = true;
                        setPageShowFromNumOnCMsg(cMsg, sMsg, selectRows.get(i));
                        continue;
                    }

                    if (NPBL0020Query.getInstance().checkOpenSODtl(glblCmpyCd, sMsg.prchReqNum.getValue(), asMsg.prchReqLineNum_A1.getValue(), asMsg.prchReqLineSubNum_A1.getValue())) {
                        asMsg.xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                        if (preConfigLineNum > -1 && ZYPCommonFunc.hasValue(asMsg.prchReqLineSubNum_A1) && BigDecimal.ZERO.compareTo(asMsg.prchReqLineSubNum_A1.getValue()) < 0) {
                            sMsg.A.no(preConfigLineNum).xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                        }
                        isError = true;
                        setPageShowFromNumOnCMsg(cMsg, sMsg, selectRows.get(i));
                        continue;
                    }
                } else if (PRCH_REQ_LINE_STS.CANCELLED.equals(prchReqLineStsCd) || PRCH_REQ_LINE_STS.CLOSED.equals(prchReqLineStsCd)) {
                    asMsg.xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                    if (preConfigLineNum > -1 && ZYPCommonFunc.hasValue(asMsg.prchReqLineSubNum_A1) && BigDecimal.ZERO.compareTo(asMsg.prchReqLineSubNum_A1.getValue()) < 0) {
                        sMsg.A.no(preConfigLineNum).xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                    }
                    isError = true;
                    setPageShowFromNumOnCMsg(cMsg, sMsg, selectRows.get(i));
                    continue;
                }

            } else if (PRCH_REQ_LINE_STS.CANCELLED.equals(prchReqLineStsCd)) {
                asMsg.xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                if (preConfigLineNum > -1 && ZYPCommonFunc.hasValue(asMsg.prchReqLineSubNum_A1) && BigDecimal.ZERO.compareTo(asMsg.prchReqLineSubNum_A1.getValue()) < 0) {
                    sMsg.A.no(preConfigLineNum).xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                }
                isError = true;
                setPageShowFromNumOnCMsg(cMsg, sMsg, selectRows.get(i));
                continue;

            } else if (!isCancel && !PRCH_REQ_TP.KITTING.equals(cMsg.prchReqTpCd_SL.getValue())) {

                asMsg.xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                if (preConfigLineNum > -1 && ZYPCommonFunc.hasValue(asMsg.prchReqLineSubNum_A1) && BigDecimal.ZERO.compareTo(asMsg.prchReqLineSubNum_A1.getValue()) < 0) {
                    sMsg.A.no(preConfigLineNum).xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                }
                isError = true;
                setPageShowFromNumOnCMsg(cMsg, sMsg, selectRows.get(i));
                continue;

            } else if (!isCancel && PRCH_REQ_TP.KITTING.equals(cMsg.prchReqTpCd_SL.getValue()) //
                    && (PRCH_REQ_APVL_STS.ENTERED.equals(cMsg.prchReqApvlStsCd.getValue()) //
                            || PRCH_REQ_APVL_STS.REJECTED.equals(cMsg.prchReqApvlStsCd.getValue()))) {

                asMsg.xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                if (preConfigLineNum > -1 && ZYPCommonFunc.hasValue(asMsg.prchReqLineSubNum_A1) && BigDecimal.ZERO.compareTo(asMsg.prchReqLineSubNum_A1.getValue()) < 0) {
                    sMsg.A.no(preConfigLineNum).xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                }
                isError = true;
                setPageShowFromNumOnCMsg(cMsg, sMsg, selectRows.get(i));
                continue;
            }
        }
        return isError;
    }
    // QC#56867 Add End

    /**
     * Find PR_DTL by primary key, for update.
     * @param asMsg NPBL0020_ASMsg
     * @param glblCmpyCd String
     * @return PRCH_REQTMsg
     */
    private static PRCH_REQ_DTLTMsg findPRDtl(NPBL0020_ASMsg asMsg, String glblCmpyCd) {
        if (!ZYPCommonFunc.hasValue(asMsg.prchReqNum_A1)) {
            return null;
        }
        if (!ZYPCommonFunc.hasValue(asMsg.prchReqLineNum_A1)) {
            return null;
        }
        if (!ZYPCommonFunc.hasValue(asMsg.prchReqLineSubNum_A1)) {
            return null;
        }

        PRCH_REQ_DTLTMsg inMsg = new PRCH_REQ_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.prchReqNum, asMsg.prchReqNum_A1);
        ZYPEZDItemValueSetter.setValue(inMsg.prchReqLineNum, asMsg.prchReqLineNum_A1);
        ZYPEZDItemValueSetter.setValue(inMsg.prchReqLineSubNum, asMsg.prchReqLineSubNum_A1);

        PRCH_REQ_DTLTMsg outMsg = (PRCH_REQ_DTLTMsg) EZDTBLAccessor.findByKey(inMsg);
        return outMsg;
    }

    // START 06/12/2020 T.Ogura [QC#57002,ADD]
    /**
     * Check Transfer Qty for Min Order Qty and Max Order Qty and Incr Order Qty
     * @param asMsg NPBL0020_ASMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private static boolean checkTransferQtyForMinAndMaxAndIncl(NPBL0020_ASMsg asMsg, String glblCmpyCd) {

        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, asMsg.mdseCd_A1.getValue());

        mdseTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTMsg);

        if (mdseTMsg != null) {
            BigDecimal prchReqDispQty = asMsg.prchReqDispQty_A1.getValue();
            BigDecimal cpoMinOrdQty = mdseTMsg.cpoMinOrdQty.getValue();
            BigDecimal cpoMaxOrdQty = mdseTMsg.cpoMaxOrdQty.getValue();
            BigDecimal cpoIncrOrdQty = mdseTMsg.cpoIncrOrdQty.getValue();

            // Check Transfer Qty for Min Order Qty
            if (ZYPCommonFunc.hasValue(cpoMinOrdQty) && cpoMinOrdQty.compareTo(prchReqDispQty) > 0) {
                asMsg.prchReqDispQty_A1.setErrorInfo(1, NPBM0019E, new String[] {String.valueOf(cpoMinOrdQty) });
                return false;
            }

            // Check Transfer Qty for Max Order Qty
            if (ZYPCommonFunc.hasValue(cpoMaxOrdQty) && cpoMaxOrdQty.compareTo(prchReqDispQty) < 0) {
                asMsg.prchReqDispQty_A1.setErrorInfo(1, NPBM0020E, new String[] {String.valueOf(cpoMaxOrdQty) });
                return false;
            }

            // Check Transfer Qty for Incr Order Qty
            if (ZYPCommonFunc.hasValue(cpoIncrOrdQty) && (prchReqDispQty.remainder(cpoIncrOrdQty)).compareTo(BigDecimal.ZERO) != 0) {
                asMsg.prchReqDispQty_A1.setErrorInfo(1, NPBM0021E, new String[] {String.valueOf(cpoIncrOrdQty) });
                return false;
            }
        }

        return true;
    }
    // END   06/12/2020 T.Ogura [QC#57002,ADD]

    // START 2021/11/09 R.Azucena[QC#58586, ADD]
    /**
     * Checks available single quantity in SVC_MACH_MSTR
     * @param asMsg NPBL0020_ASMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private static boolean checkAvailableSingleQty(NPBL0020_ASMsg asMsg, String glblCmpyCd) {

        // QC#58586-1 Add
        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, asMsg.mdseCd_A1.getValue());

        mdseTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTMsg);

        if (mdseTMsg == null) {
            return true;
        }

        if (ZYPConstant.FLG_OFF_N.equals(mdseTMsg.invtyCtrlFlg.getValue())) {
            return true;
        }

        if (ZYPConstant.FLG_OFF_N.equals(mdseTMsg.instlBaseCtrlFlg.getValue())) {
            return true;
        }
        // QC#58586-1 End

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_MDSE_CD, asMsg.mdseCd_A1.getValue());
        ssmParam.put(DB_PARAM_STK_STS_CD, asMsg.fromStkStsCd_A1.getValue());
        ssmParam.put(DB_PARAM_CUR_LOC_NUM, S21StringUtil.concatStrings(asMsg.srcRtlWhCd_A1.getValue(), asMsg.srcRtlSwhCd_A1.getValue()));
        ssmParam.put(DB_PARAM_SVC_MACH_MSTR_STS_CD_LIST, new String[] {SVC_MACH_MSTR_STS.CREATED, SVC_MACH_MSTR_STS.REMOVED });
        ssmParam.put(DB_PARAM_SVC_MACH_MAINT_AVAL_FLG, ZYPConstant.FLG_ON_Y);
        S21SsmEZDResult result = NPBL0020Query.getInstance().getAvailableSingleQty(ssmParam);

        BigDecimal avalSingleQty = (BigDecimal) result.getResultObject();
        BigDecimal curQty = asMsg.prchReqDispQty_A1.getValue();

        if (avalSingleQty.compareTo(curQty) >= 0) {
            return true;
        }

        return false;
    }
    // END 2021/11/09 R.Azucena[QC#58586, ADD]

    // START 2022/11/16 R.Azucena[QC#60808, ADD]
    /**
     * isActiveServiceExchangeItem
     * @param svcMachMstrPk BigDecimal
     * @param glblCmpyCd String
     * @return boolean
     */
    private static boolean isActiveServiceExchangeItem(BigDecimal svcMachMstrPk, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_SVC_MACH_MSTR_PK, svcMachMstrPk);
        ssmParam.put(DB_PARAM_SVC_MACH_MSTR_STS_CD, SVC_MACH_MSTR_STS.REMOVED);
        ssmParam.put(DB_PARAM_ORD_CATG_CTX_CD, ORD_CATG_CTX_TP.SERVICE_EXCHANGE_ORDER_VALUE_SET);
        ssmParam.put(DB_PARAM_ORD_HDR_STS_CD_LIST, new String[] {ORD_HDR_STS.CLOSED, ORD_HDR_STS.CANCELLED});
        S21SsmEZDResult result = NPBL0020Query.getInstance().getServiceExchangeItemCnt(ssmParam);
        BigDecimal cnt = (BigDecimal) result.getResultObject();

        if (BigDecimal.ZERO.equals(cnt)) {
            return false;
        }

        return true;
    }
    // END 2022/11/16 R.Azucena[QC#60808, ADD]

    // START 2023/03/13 R.Azucena[QC#61282, ADD]
    /**
     * getAvailableConfigItemCnt
     * @param configComponentList List<Map<String, Object>>
     * @param errTrxHdrNumList List<String>
     * @return int
     */
    private static int getAvailableConfigItemCnt(List<Map<String, Object>> configComponentList, List<String> errTrxHdrNumList) {
        int retCnt = 0;

        for (Map<String, Object> configComponentMap : configComponentList) {
            String stsCd = (String) configComponentMap.get(DB_COLUMN_SVC_MACH_MSTR_STS_CD);
            String availFlg = (String) configComponentMap.get(DB_COLUMN_SVC_MACH_MAINT_AVAL_FLG);

            if ((SVC_MACH_MSTR_STS.CREATED.equals(stsCd) || SVC_MACH_MSTR_STS.REMOVED.equals(stsCd))
                    && ZYPConstant.FLG_ON_Y.equals(availFlg)) {
                retCnt++;
            } else {
                String trxHdrNum = (String) configComponentMap.get(DB_COLUMN_TRX_HDR_NUM);

                if (ZYPCommonFunc.hasValue(trxHdrNum) && !errTrxHdrNumList.contains(trxHdrNum)) {
                    errTrxHdrNumList.add(trxHdrNum);
                }
            }
        }

        return retCnt;
    }
    // END 2023/03/13 R.Azucena[QC#61282, ADD]

    // START 2023/07/04 T.Kuroda [QC#61440, ADD]
    /**
     * setSaveWrnSkipFlg
     * @param cMsg NPBL0020CMsg
     * @param value String
     */
    public static void setSaveWrnSkipFlg(NPBL0020CMsg cMsg, String value) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg_01, value);
    }

    /**
     * setSubmitWrnSkipFlg
     * @param cMsg NPBL0020CMsg
     */
    public static void setSubmitWrnSkipFlg(NPBL0020CMsg cMsg, String value) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg_02, value);
    }

    /**
     * checkWrnSkip
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private static boolean checkWrnSkip(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, String glblCmpyCd) {
        String screenAplID = cMsg.getScreenAplID();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseCd_A1)) {
                NLXC001001GetInventoryItemCostBean nlxc001001Bean =
                    getInvtyItemCost(glblCmpyCd
                            , sMsg.A.no(i).srcRtlWhCd_A1.getValue()
                            , sMsg.A.no(i).srcRtlSwhCd_A1.getValue()
                            , sMsg.A.no(i).mdseCd_A1.getValue()
                            , sMsg.A.no(i).prchReqDispQty_A1.getValue());

                if (!nlxc001001Bean.getErrList().isEmpty()) {
                    cMsg.setMessageInfo(nlxc001001Bean.getErrList().get(0));
                    return false;
                } else {
                    if (ZYPCommonFunc.hasValue(nlxc001001Bean.getMdseInvtyCostPct())
                            && BigDecimal.ZERO.compareTo(nlxc001001Bean.getMdseInvtyCostPct()) == 0) {
                        continue;
                    } else {
                        if (EVENT_NM_NPBL0020_CMN_SAVE.equals(screenAplID)) {
                            if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg_01.getValue())) {
                                setSaveWrnSkipFlg(cMsg, ZYPConstant.FLG_OFF_N);
                                return true;
                            } else {
                                setSaveWrnSkipFlg(cMsg, ZYPConstant.FLG_ON_Y);
                            }
                        } else if (EVENT_NM_NPBL0020_CMN_SUBMIT.equals(screenAplID)) {
                            if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg_02.getValue())) {
                                setSubmitWrnSkipFlg(cMsg, ZYPConstant.FLG_OFF_N);
                                return true;
                            } else {
                                setSubmitWrnSkipFlg(cMsg, ZYPConstant.FLG_ON_Y);
                            }
                        }

                        cMsg.setMessageInfo(NPBM0025W);
                        return false;
                    }
                }
            }
        }

        return true;
    }
    // END 2023/07/04 T.Kuroda [QC#61440, ADD]
}
