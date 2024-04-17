/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1840.common;

import static business.blap.NWAL1840.constant.NWAL1840Constant.DUP_VALID_ERROR;
import static business.blap.NWAL1840.constant.NWAL1840Constant.EQUIPMENT_ORDER_VALUE_SET;
import static business.blap.NWAL1840.constant.NWAL1840Constant.KEY_NWAL1840_DUP_VALID;
import static business.blap.NWAL1840.constant.NWAL1840Constant.SUPPLIES_ORDER_VALUE_SET;
import static business.blap.NWAL1840.constant.NWAL1840Constant.TAB_LINES;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_BILL_TO_LOC;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_BILL_TO_NM;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_BILL_TO_NUM;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_BTN_PRC;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_CARR_SVC_LVL;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_CATG;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_CONF;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_CONTR_NUM;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_CUST_PO_NUM;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_FRT_TERMS;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_ITEM;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_MDL_NM;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_ORD_QTY;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_PMT_TERMS;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_PRC_LIST;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_QTY_ALLW;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_RDD_DT;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_RSN_CD;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_SAVE;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_SER_NUM;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_SHIP_TO_LOC;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_SHIP_TO_NM;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_SHIP_TO_NUM;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_SHPG_SVC_LVL;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_SLS_REP_CD;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_SLS_REP_NM;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_SOLD_TO_LOC;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_SOLD_TO_NM;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_SOLD_TO_NUM;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_SRC_NM;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_SUBMIT;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_UNIT_SELL_PRC;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_UOM;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_VALID_FROM;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_VALID_TO;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0053E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0054E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0181E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0429E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0545E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0673E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0712E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0793W;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0817E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0818E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0819E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0820E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0822E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0823E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0824E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0825W;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0839E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0882E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0901E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0936E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM8465E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM8475E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM8476W;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM8477E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWZM1338E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWZM1344E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWZM1345E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWZM1387E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWZM1388E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWZM1530E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWZM2008E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWZM2014E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWZM2023E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWZM2024E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWZM2025E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWZM2251E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWZM2254E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWZM2255E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWZM2267E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.ZZM8100I;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.ZZM9000E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWZM2321E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1839E;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1840.NWAL1840CMsg;
import business.blap.NWAL1840.NWAL1840Query;
import business.blap.NWAL1840.NWAL1840QueryForPricing;
import business.blap.NWAL1840.NWAL1840QueryForSaveSubmit;
import business.blap.NWAL1840.NWAL1840SMsg;
import business.blap.NWAL1840.NWAL1840_ACMsg;
import business.blap.NWAL1840.NWAL1840_ASMsg;
import business.blap.NWAL1840.NWAL1840_BCMsg;
import business.blap.NWAL1840.NWAL1840_CCMsg;
import business.blap.NWAL1840.NWAL1840_DCMsg;
import business.blap.NWAL1840.NWAL1840_DSMsg;
import business.blap.NWAL1840.NWAL1840_ECMsg;
import business.db.DS_ORD_LINE_CATGTMsg;
import business.db.DS_ORD_LINE_PROC_DFNTMsg;
import business.db.DS_ORD_LINE_PROC_DFNTMsgArray;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.MDL_NMTMsg;
import business.db.MDL_NMTMsgArray;
import business.db.MDSETMsg;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.db.SCHD_AGMTTMsg;
import business.db.TOCTMsg;
import business.parts.NMZC002001PMsg;
import business.parts.NMZC002001_ContactPointInfoListPMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_EligibleCheckListPMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157001_xxPrcListPMsgArray;
import business.parts.NWZC171001PMsg;
import business.parts.NWZC171001_CPMsg;
import business.parts.NWZC171001_DPMsg;
import business.parts.NWZC171001_EPMsg;
import business.parts.NWZC171002PMsg;
import business.parts.NWZC171003PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC002001.NMZC002001;
import com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant;
import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.api.NWZ.NWZC171001.NWZC171001;
import com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_TERM_CASH_DISC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/22   Fujitsu         T.Murai         Create          N/A
 * 2016/05/13   Fujitsu         T.Murai         update          S21_NA#7861
 * 2016/06/21   Fujitsu         Y.Kanefusa      Update          S21_NA#9437
 * 2016/07/11   Fujitsu         T.Yoshida       Update          S21_NA#11658
 * 2016/07/28   Fujitsu         H.Ikeda         Update          S21_NA#11591
 * 2016/08/03   Fujitsu         H.Ikeda         Update          S21_NA#11340
 * 2016/08/03   Fujitsu         H.Ikeda         Update          S21_NA#11657
 * 2016/09/06   Fujitsu         T.Murai         Update          S21_NA#11595
 * 2016/09/16   Fujitsu         T.Murai         Update          S21_NA#13040
 * 2016/09/20   Fujitsu         T.Murai         Update          S21_NA#14578
 * 2016/10/07   Fujitsu         T.Murai         Update          S21_NA#11883
 * 2016/11/09   Fujitsu         T.Murai         Update          S21_NA#15851
 * 2017/02/21   Fujitsu         Y.Kanefusa      Update          S21_NA#17474
 * 2017/04/12   Fujitsu         Y.Kanefusa      Update          S21_NA#18235
 * 2017/05/11   Hitachi         T.Tomita        Update          S21_NA:RS#8341
 * 2017/05/21   Hitachi         T.Tomita        Update          S21_NA:RS#8385
 * 2017/06/09   Fujitsu         N.Aoyama        Update          S21_NA#18296
 * 2017/08/04   Fujitsu         H.Nagashima     Update          S21_NA#16452
 * 2017/11/20   Fujitsu         A.Kosai         Update          S21_NA#22388
 * 2018/01/12   Fujitsu         K.Ishizuka      Update          S21_NA#20164
 * 2018/02/26   Fujitsu         Hd.Sugawara     Update          QC#22967
 * 2018/06/01   Fujitsu         M.Ohno          Update          S21_NA#26273
 * 2018/06/27   Fujitsu         H.Nagashima     Update          S21_NA#23726
 * 2018/08/25   Fujitsu         T.Aoi           Update          S21_NA#27213
 * 2018/12/11   Fujitsu         M.Ohno          Update          S21_NA#29315
 * 2019/01/16   Fujitsu         S.Kosaka        Update          QC#29642
 * 2019/01/29   Fujitsu         S.Kosaka        Update          QC#30036
 * 2019/12/07   Fujitsu         M.Ohno          Update          QC#54670
 * 2022/08/18   Hitachi         H.Watanabe      Update          QC#60255
 * 2023/04/28   CITS            R.Kurahashi     Update          QC#61281
 * 2023/10/10   Hitachi         T.Fukuta        Update          CSA-QC#61730
 * </pre>
 */
public class NWAL1840CommonLogicForSaveSubmit {

    /**
     * Check And Clear Code
     * @param bizMsg NWAL1840CMsg
     * @return No Error : true
     */
    public static boolean checkAndClearCode(NWAL1840CMsg bizMsg) {

        S21SsmEZDResult ssmResult = null;
        boolean isError = false;

        // Header
        if (!ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgDescTxt)) {
            bizMsg.dsOrdCatgCd.clear();
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.prcCatgNm)) {
            bizMsg.prcCatgCd.clear();
        } else {
            ssmResult = NWAL1840QueryForPricing.getInstance().getPrcCatgCd(bizMsg, bizMsg.prcCatgNm.getValue());
            if (ssmResult.isCodeNotFound()) {
                bizMsg.prcCatgNm.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_PRC_LIST });
                isError = true;
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd, (String) ssmResult.getResultObject());
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.mdlNm)) {
            bizMsg.mdlId.clear();
        } else {
            MDL_NMTMsg mdlTMsg = new MDL_NMTMsg();
            mdlTMsg.setSQLID("001");
            mdlTMsg.setConditionValue("t_GlblCmpyCd01", bizMsg.glblCmpyCd.getValue());
            mdlTMsg.setConditionValue("t_MdlNm01", bizMsg.mdlNm.getValue());

            MDL_NMTMsgArray mdlTmsgArray = (MDL_NMTMsgArray) EZDTBLAccessor.findByCondition(mdlTMsg);

            if (mdlTmsgArray != null && mdlTmsgArray.getValidCount() > 0) {
                ZYPEZDItemValueSetter.setValue(bizMsg.mdlId, mdlTmsgArray.no(0).t_MdlId.getValue());
            } else {
                bizMsg.mdlNm.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_MDL_NM });
            }
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.pmtTermCashDiscDescTxt)) {
            bizMsg.pmtTermCashDiscCd.clear();
        } else {
            ssmResult = NWAL1840Query.getInstance().getPmtTermCashDiscCd(bizMsg);
            if (ssmResult.isCodeNotFound()) {
                bizMsg.pmtTermCashDiscDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_PMT_TERMS });
                isError = true;
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.pmtTermCashDiscCd, (String) ssmResult.getResultObject());
        }

        // Header Tab
        if (!ZYPCommonFunc.hasValue(bizMsg.frtCondDescTxt)) {
            bizMsg.frtCondCd.clear();
        } else {
            ssmResult = NWAL1840Query.getInstance().getFrtCondCd(bizMsg);
            if (ssmResult.isCodeNotFound()) {
                bizMsg.frtCondDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_FRT_TERMS });
                isError = true;
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd, (String) ssmResult.getResultObject());
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.carrSvcLvlDescTxt)) {
            bizMsg.carrSvcLvlCd.clear();
        } else {
            ssmResult = NWAL1840Query.getInstance().getCarrSvcLvlCd(bizMsg);
            if (ssmResult.isCodeNotFound()) {
                bizMsg.carrSvcLvlDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_CARR_SVC_LVL });
                isError = true;
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.carrSvcLvlCd, (String) ssmResult.getResultObject());
        }

        if (isError) {
            return false;
        }

        return true;
    }

    /**
     * Check Mandatory Field
     * @param bizMsg NWAL1840CMsg
     * @return No Error : true
     */
    public static boolean checkMandatoryField(NWAL1840CMsg bizMsg, boolean isCallSave) { 
//  public static boolean checkMandatoryField(NWAL1840CMsg bizMsg) { //Mod 2016/09/15 S21_NA#13914

        if (!checkMandatoryHeader(bizMsg)) {
            return false;
        }

        if (!checkMandatoryCustomerTab(bizMsg)) {
            return false;
        }

        if (!checkMandatoryHeaderTab(bizMsg, isCallSave)) {
  //    if (!checkMandatoryHeaderTab(bizMsg)) {//Mod 2016/09/15 S21_NA#13914
            return false;
        }

        if (!checkMandatorySchdLineTab(bizMsg)) {
            return false;
        }

        return true;
    }

    // Add Start 2016/09/20 S21_NA#14578
    /**
     * Check Sales Credit Info
     * @param bizMsg NWAL1840CMsg
     * @return No Error : true
     */
    public static boolean checkSlsCrInfo(NWAL1840CMsg bizMsg) {

        boolean hasQuota = false;
        BigDecimal totalCrPct = BigDecimal.ZERO;

        // Salesrep List Check
        for (int index = 0; index < bizMsg.C.getValidCount(); index++) {
            NWAL1840_CCMsg salesRepMsg = bizMsg.C.no(index);

            if (!ZYPCommonFunc.hasValue(salesRepMsg.xxRqstTpCd_C) //
                    || NWZC150001Constant.RQST_TP_SLS_CR_NEW.equals(salesRepMsg.xxRqstTpCd_C.getValue()) //
                    || NWZC150001Constant.RQST_TP_SLS_CR_MODIFY.equals(salesRepMsg.xxRqstTpCd_C.getValue())) {

                if (!ZYPCommonFunc.hasValue(salesRepMsg.slsRepTocCd_C)) {
                    bizMsg.setMessageInfo(NWAM0053E);
                    return false;
                }
                if (!ZYPCommonFunc.hasValue(salesRepMsg.slsRepRoleTpCd_C)) {
                    bizMsg.setMessageInfo(NWZM1338E);
                    return false;
                }
                if (!ZYPCommonFunc.hasValue(salesRepMsg.slsRepCrPct_C)) {
                    bizMsg.setMessageInfo(NWZM1344E);
                    return false;
                }
                if (!ZYPCommonFunc.hasValue(salesRepMsg.slsCrQuotFlg_C)) {
                    bizMsg.setMessageInfo(NWZM1345E);
                    return false;
                }

                if (ZYPConstant.FLG_ON_Y.equals(salesRepMsg.slsCrQuotFlg_C.getValue())) {
                    hasQuota = true;
                    if (ZYPCommonFunc.hasValue(salesRepMsg.slsRepCrPct_C)) {
                        if (BigDecimal.ZERO.compareTo(salesRepMsg.slsRepCrPct_C.getValue()) == 0) {
                            bizMsg.setMessageInfo(NWZM1388E);
                            return false;
                        } else {
                            totalCrPct = totalCrPct.add(salesRepMsg.slsRepCrPct_C.getValue());
                        }
                    }
                }
                if (ZYPConstant.FLG_OFF_N.equals(salesRepMsg.slsCrQuotFlg_C.getValue())) {
                    salesRepMsg.slsRepCrPct_C.setValue(BigDecimal.ZERO);
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
    // Add End 2016/09/20 S21_NA#14578

    /**
     * Check Mandatory Field
     * @param bizMsg NWAL1840CMsg
     * @param isCallSave boolean;
     * @return No Error : true
     */
    public static boolean relationCheck(NWAL1840CMsg bizMsg, boolean isCallSave) {
       
        // QC#17474 2017/02/21 Add Start
        if(NWXC150001DsCheck.checkCcReq(bizMsg.glblCmpyCd.getValue(), bizMsg.billToCustAcctCd.getValue(), bizMsg.billToCustLocCd.getValue(), bizMsg.pmtTermCashDiscCd.getValue())){
            bizMsg.pmtTermCashDiscDescTxt.setErrorInfo(1, NWZM1839E);
            return false;
        }
        // QC#17474 2017/02/21 Add End

        // Add Start 2016/10/06 S21_NA#11883
        // Contract# Check
        if (!checkContrCatgRelation(bizMsg)) {
            bizMsg.dsContrNum.setErrorInfo(1, NWAM0901E);
            return false;
        }
        // Add End 2016/10/06 S21_NA#11883

        if (isErrorContrSts(bizMsg)) {
            bizMsg.dsContrNum.setErrorInfo(1, NWAM0936E, new String[] {MSG_PARAM_CONTR_NUM });
            return false;
        }

        // Floor Price List 
        if (!getUniquFlPrcList(bizMsg)) {
            getDefFlPrcList(bizMsg);
        }

        // salesRep relation check
        if (NWXC150001DsCheck.checkSalesRepRelation(bizMsg.glblCmpyCd.getValue(), bizMsg.slsRepTocCd.getValue())) {
            bizMsg.slsRepTocNm.setErrorInfo(1, NWAM0054E);
            bizMsg.psnNum.setErrorInfo(1, NWAM0054E); // 2016/05/13 S21_NA#7861 Mod slsRepPsnCd -> psnNum
            return false;
        }

        // FreightCond LOB ShpgSvcLevl relation
        if (NWXC150001DsCheck.checkFrtCondSvcLvlRelation(//
                bizMsg.glblCmpyCd.getValue() //
                , bizMsg.slsDt.getValue() //
                , bizMsg.dsOrdTpCd.getValue() //
                , bizMsg.frtCondCd.getValue() //
                , bizMsg.shpgSvcLvlCd.getValue() //
                , bizMsg.carrSvcLvlCd.getValue() //
                // 2018/12/11 S21_NA#29315 Add Start
                , bizMsg.shipToCustAcctCd.getValue() //
                , bizMsg.shipToLocNum.getValue())) {
                // 2018/12/11 S21_NA#29315 Add Start

            bizMsg.frtCondDescTxt.setErrorInfo(1, NWAM0823E);
            bizMsg.shpgSvcLvlCd.setErrorInfo(1, NWAM0823E);
            bizMsg.carrSvcLvlDescTxt.setErrorInfo(1, NWAM0823E);
            return false;
        }

        // Add Start 2016/09/06 S21_NA#11595
        // Carrier Service Level Cd
        if (NWXC150001DsCheck.checkCarrSvcLevelRelation(bizMsg.glblCmpyCd.getValue(), bizMsg.carrSvcLvlCd.getValue(), bizMsg.frtCondCd.getValue())) {
            bizMsg.carrSvcLvlDescTxt.setErrorInfo(1, NWAM0882E);
            return false;
        }
        // Add Start 2016/09/06 S21_NA#11595
        // QC#23726 2018/06/27 add Start
        if (NWXC150001DsCheck.checkCustCarrSvcLvlRelation(
                 bizMsg.glblCmpyCd.getValue()
                ,bizMsg.dsOrdCatgCd.getValue()
                ,bizMsg.dsOrdTpCd.getValue()
                ,bizMsg.dsOrdRsnCd.getValue()
                ,bizMsg.shipToCustAcctCd.getValue()
                ,bizMsg.carrSvcLvlCd.getValue()
                ,bizMsg.frtCondCd.getValue())) {
            bizMsg.carrSvcLvlDescTxt.setErrorInfo(1, NWZM2267E);
            return false;
        }
        // QC#23726 2018/06/27 add End

        // CarrAcct Num
        if (NWXC150001DsCheck.checkAddlCarrAcctNumRelation(bizMsg.glblCmpyCd.getValue(), bizMsg.carrAcctNum.getValue(), bizMsg.frtCondCd.getValue())) {
            bizMsg.carrAcctNum.setErrorInfo(1, NWAM0822E);
            return false;
        }

        // ADD START 2022/08/19 H.Watanabe [QC#60255]
        // CarrAcctNum Format Check
        if (!NWXC150001DsCheck.chkCarrierAccountNumberNeedValidation(bizMsg.glblCmpyCd.getValue(), bizMsg.carrAcctNum.getValue(), bizMsg.shpgSvcLvlCd.getValue(), bizMsg.carrSvcLvlCd.getValue())) {
            bizMsg.carrAcctNum.setErrorInfo(1, NWAM8465E, new String[] {"Carrier Acct Num" });
            return false;
        }
        // ADD END   2022/08/19 H.Watanabe [QC#60255]

        // Vaild Date Check
        if (!validDateCheck(bizMsg, isCallSave)) {
            return false;
        }

        // Configuration relation param Check
        if (!checkConfigParam(bizMsg)) {
            return false;
        }

        // Customer Check
        if (!checkAllCustCd(bizMsg)) {
            return false;
        }
        // Line - Merchandise Check
        if (!checkMerchandise(bizMsg)) {
            return false;
        }
        
        // QC#13918,13919 2016/09/08 Add Start 
        if (!otherEasyPackCheck(bizMsg)) {
            return false;
        }
        // QC#13918,13919 2016/09/08 Add End 

        // QC#11340 2016/08/03 Del Start
        // Line - Model-Merchandise Check
        //if (!checkMdlComponent(bizMsg)) {
        //    return false;
        //}
        // QC#11340 2016/08/03 Del End
        // Line - substitute Merchandise Check
        if (!checkSbstItem(bizMsg)) {
            return false;
        }
        // Line - Qty Check
        if (!checkSchdLineQty(bizMsg)) {
            return false;
        }

        return true;
    }

    // Add Start 2016/10/06 S21_NA#11883
    private static boolean checkContrCatgRelation(NWAL1840CMsg bizMsg) {
        
        // Mod START 2016/11/09 S21_NA#15851
        if (NWAL1840QueryForSaveSubmit.getInstance().countDsOrdTpFlgY(bizMsg)) {
            // if (NWAL1840QueryForSaveSubmit.getInstance().countDsOrdCatgRelnTp(bizMsg)) {
        // Mod END   2016/11/09 S21_NA#15851

            if (!ZYPCommonFunc.hasValue(bizMsg.dsContrNum)) {
                return false;
            }
        }
        return true;
    }
    // Add Start 2016/10/06 S21_NA#11883

    private static boolean isErrorContrSts(NWAL1840CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.dsContrNum)) {
            return false;
        }

        if (!NWAL1840QueryForSaveSubmit.getInstance().countDsContrCtrlSts(bizMsg)) {
            return true;
        }
        return false;
    }

    private static boolean getUniquFlPrcList(NWAL1840CMsg bizMsg) {
        NWZC157001PMsg prcApiPMsg = //
        callPricingApiForGetPrcList(bizMsg, NWZC157001.GET_PRICE_LIST, PRC_CTX_TP.FLOOR_PRICE, ZYPConstant.FLG_ON_Y);

        if (prcApiPMsg == null) {
            return false;
        }

        if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);
                if (msgId.endsWith("E")) {
                    return false;
                }
            }
        }

        // set Default Price List
        NWZC157001_xxPrcListPMsgArray prcListArray = prcApiPMsg.xxPrcList;
        if (prcListArray.getValidCount() == 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.flPrcListCd, prcListArray.no(0).prcCatgCd);
            return true;
        }
        return false;
    }

    private static void getDefFlPrcList(NWAL1840CMsg bizMsg) {
        DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, bizMsg.dsOrdTpCd.getValue());

        tMsg = (DS_ORD_TP_PROC_DFNTMsg) S21CacheTBLAccessor.findByKey(tMsg);

        if (tMsg != null) {

            if (ZYPConstant.FLG_ON_Y.equals(tMsg.actvFlg.getValue())) {

                if ((ZYPDateUtil.compare(tMsg.effFromDt.getValue(), bizMsg.slsDt.getValue()) <= 0) //
                        && (!ZYPCommonFunc.hasValue(tMsg.effThruDt) || ZYPDateUtil.compare(tMsg.effThruDt.getValue(), bizMsg.slsDt.getValue()) >= 0)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.flPrcListCd, tMsg.defPrcCatgCd);
                }
            }
        }
    }

    /**
     * <pre>
     * @param bizMsg          NWZC167001PMsg
     * @param modeCd        modeCd
     * @param prcCtxTpCd    prcCtxTpCd
     * @param prcCatgOpeFlg prcCatgOpeFlg(when check then set to "Y")
     * @return  executed result of NWZC157001PMsg
     * </pre>
     */
    private static NWZC157001PMsg callPricingApiForGetPrcList(NWAL1840CMsg bizMsg, String modeCd, String prcCtxTpCd, String prcCatgOpeFlg) {

        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, modeCd);
        // QC#9437 2016/06/21 Mod Start
        // if (ZYPCommonFunc.hasValue(bizMsg.prcBaseDt)) {
        //     ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, bizMsg.prcBaseDt);
        // } else {
        //     ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, bizMsg.slsDt);
        // }
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, bizMsg.slsDt);
        // QC#9437 2016/06/21 Mod End
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, prcCtxTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, NWXC150001DsCheck.getLineBizTpCd(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), bizMsg.dsOrdTpCd.getValue()));
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcContrNum, bizMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.coaBrCd, getCoaBrCd(bizMsg));
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxPrcCatgOpFlg, prcCatgOpeFlg);

        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.ONLINE);
        return prcApiPMsg;
    }

    private static String getCoaBrCd(NWAL1840CMsg bizMsg) {

        TOCTMsg tMsg = new TOCTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.tocCd, bizMsg.slsRepTocCd.getValue());
        tMsg = (TOCTMsg) S21CacheTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            return "";
        }

        return tMsg.coaBrCd.getValue();
    }

    /**
     * Relation Check Validation Date
     * @param bizMsg
     * @return
     */
    private static boolean validDateCheck(NWAL1840CMsg bizMsg, boolean isCallSave) {

        String vaildFrom = bizMsg.schdAgmtVldFromDt.getValue();
        String vaildTo = bizMsg.schdAgmtVldThruDt.getValue();

        // Error - From Date > ThruDate
        if (vaildFrom.compareTo(vaildTo) > 0) {
            bizMsg.schdAgmtVldFromDt.setErrorInfo(1, NWAM0712E, new String[] {MSG_PARAM_VALID_TO, MSG_PARAM_VALID_FROM });
            bizMsg.schdAgmtVldThruDt.setErrorInfo(1, NWAM0712E, new String[] {MSG_PARAM_VALID_TO, MSG_PARAM_VALID_FROM });
            return false;
        }

        // 2018/08/25 QC#27213 Del Start
        // Compare Contract Date
        //if (ZYPCommonFunc.hasValue(bizMsg.contrVrsnEffFromDt)) {
        //    String contrStrtDate = bizMsg.contrVrsnEffFromDt.getValue();
        //    if (contrStrtDate.compareTo(vaildFrom) > 0) {
        //        bizMsg.schdAgmtVldFromDt.setErrorInfo(1, NWAM0712E, new String[] {MSG_PARAM_VALID_FROM, MSG_PARAM_CONTR_FROM });
        //        return false;
        //    }
        //}
        //if (ZYPCommonFunc.hasValue(bizMsg.contrVrsnEffThruDt)) {
        //    String contrEndDate = bizMsg.contrVrsnEffThruDt.getValue();
        //    if (vaildTo.compareTo(contrEndDate) > 0) {
        //        bizMsg.schdAgmtVldThruDt.setErrorInfo(1, NWAM0765E, new String[] {MSG_PARAM_VALID_TO, MSG_PARAM_CONTR_TO });
        //        return false;
        //    }
        //}
        // 2018/08/25 QC#27213 Del End

        boolean checkFlg = true;
        String buttonNm = null;

        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg_DT.getValue())) {
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                NWAL1840_BCMsg schdLineMsg = bizMsg.B.no(i);

                if (ZYPConstant.FLG_ON_Y.equals(schdLineMsg.schdAgmtPlnCancFlg_B.getValue())) {
                    continue;
                }
                if (schdLineMsg.rddDt_B.getValue().compareTo(vaildTo) > 0) {

                    if (isCallSave) {
                        buttonNm = MSG_PARAM_SAVE;
                    } else {
                        buttonNm = MSG_PARAM_SUBMIT;
                    }

                    checkFlg = false;
                    schdLineMsg.rddDt_B.setErrorInfo(2, NWAM0825W, new String[] {buttonNm });
                    // Add Start 2016/09/16 S21_NA#13040
                    for (int s = 0 ; s < bizMsg.A.getValidCount() ; s++) {
                        if (schdLineMsg.schdAgmtLineNum_B.getValueInt() == bizMsg.A.no(s).schdAgmtLineNum_A.getValueInt()) {
                            bizMsg.A.no(s).xxSmryLineFlg_A.setValue(ZYPConstant.FLG_OFF_N);
                            break;
                        }
                    }
                    // Add End 2016/09/16 S21_NA#13040
                }

            }
        } else {
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {

                NWAL1840_BCMsg schdLineMsg = bizMsg.B.no(i);

                if (schdLineMsg.rddDt_B.getValue().compareTo(vaildTo) > 0) {
                    schdLineMsg.xxChkBox_B.setValue(ZYPConstant.FLG_ON_Y);
                    bizMsg.xxDplyTab.setValue(TAB_LINES);
                }

            }
            NWAL1840CommonLogic.scheduleCancel(bizMsg);
        }

        if (!checkFlg) {
            bizMsg.setMessageInfo(NWAM0825W, new String[] {buttonNm });
            bizMsg.xxWrnSkipFlg_DT.setValue(ZYPConstant.FLG_ON_Y);
            return false;
        }
        return true;
    }

    /**
     * Relation Check Configuration
     * @param bizMsg
     * @return boolean - Error :false
     */
    private static boolean checkConfigParam(NWAL1840CMsg bizMsg) {
        // no input item
        if (!ZYPCommonFunc.hasValue(bizMsg.mdlNm) && !ZYPCommonFunc.hasValue(bizMsg.serNum) //
                && !ZYPCommonFunc.hasValue(bizMsg.svcConfigMstrPk) && !ZYPCommonFunc.hasValue(bizMsg.dsContrNum)) {
            return true;
        }
        //
        if (!ZYPCommonFunc.hasValue(bizMsg.mdlNm) || !ZYPCommonFunc.hasValue(bizMsg.serNum) //
                || !ZYPCommonFunc.hasValue(bizMsg.svcConfigMstrPk) || !ZYPCommonFunc.hasValue(bizMsg.dsContrNum)) {
            bizMsg.mdlNm.setErrorInfo(1, NWAM0545E, new String[] {MSG_PARAM_MDL_NM + "," + MSG_PARAM_SER_NUM + "," + MSG_PARAM_CONF + "," + MSG_PARAM_CONTR_NUM });
            return false;
        }

        if (!NWAL1840QueryForSaveSubmit.getInstance().checkConfigParam(bizMsg)) {
            bizMsg.mdlNm.setErrorInfo(1, NWAM0545E, new String[] {MSG_PARAM_MDL_NM + "," + MSG_PARAM_SER_NUM + "," + MSG_PARAM_CONF + "," + MSG_PARAM_CONTR_NUM });
            return false;
        }
        return true;
    }

    /**
     * Check Customer Code
     * @param bizMsg
     * @return boolean -Error:false
     */
    private static boolean checkAllCustCd(NWAL1840CMsg bizMsg) {

        if (NWXC150001DsCheck.checkBillToRalation(bizMsg.glblCmpyCd.getValue(), bizMsg.billToCustLocCd.getValue(), bizMsg.billToCustAcctCd.getValue())) {
            bizMsg.billToCustLocCd.setErrorInfo(1, NWAM0818E);
            bizMsg.billToCustAcctCd.setErrorInfo(1, NWAM0818E);
            return false;
        }

        if (NWXC150001DsCheck.checkShipToRalation(bizMsg.glblCmpyCd.getValue(), bizMsg.shipToCustLocCd.getValue(), bizMsg.shipToCustAcctCd.getValue())) {
            bizMsg.shipToCustLocCd.setErrorInfo(1, NWAM0819E);
            bizMsg.shipToCustAcctCd.setErrorInfo(1, NWAM0819E);
            return false;
        }

        if (NWXC150001DsCheck.checkSoldToRalation(bizMsg.glblCmpyCd.getValue(), bizMsg.soldToCustLocCd.getValue(), bizMsg.sellToCustCd.getValue())) {
            bizMsg.soldToCustLocCd.setErrorInfo(1, NWAM0820E);
            bizMsg.sellToCustCd.setErrorInfo(1, NWAM0820E);
            return false;
        }

        if (checkBillShipSoldRelation(bizMsg)) {
	        return false;
        }
        return true;
    }

    /**
     * checkBillShipSoldRelation
     * @param bizMsg
     * @param glblCmpyCd glblCmpyCd
     * @param billToCustCd
     * @param shipToCustCd
     * @param sellToCustCd
     * @return boolean if error then return true.
     */
    private static boolean checkBillShipSoldRelation(NWAL1840CMsg bizMsg) {
        // Add Start 2018/02/26 QC#22967
        boolean result1 = false;
        boolean result2 = false;

        // Check Sold To(Customer Code) <- Ship To(Account Number)
        // relation.
        result1 = callCustInfoGetApiForCheckRelation( //
                bizMsg, bizMsg.soldToCustLocCd.getValue(), bizMsg.shipToCustAcctCd.getValue(), //
                bizMsg.shipToCustLocCd, bizMsg.soldToCustLocCd, NWZM2254E);

        // Check Bill To(Customer Code) <- Sold To(Account Number)
        // relation.
        result2 = callCustInfoGetApiForCheckRelation( //
                bizMsg, bizMsg.billToCustLocCd.getValue(), bizMsg.sellToCustCd.getValue(), //
                bizMsg.soldToCustLocCd, bizMsg.billToCustLocCd, NWZM2255E);

        if (result1 || result2) {
            return true;
        } else {
            return false;
        }
        // Add End 2018/02/26 QC#22967

        // Del Start 2018/02/26 QC#22967
        //NMZC610001PMsg custInfoGetApiMsg = NWXC150001DsCheck.callCustInfoGetApi(//
        //        bizMsg.glblCmpyCd.getValue() //
        //        , bizMsg.billToCustLocCd.getValue() //
        //        // 2017/06/09 S21_NA#18296 Mod Start
        //        // , bizMsg.shipToCustLocCd.getValue() //
        //        , bizMsg.sellToCustCd.getValue()
        //        // 2017/06/09 S21_NA#18296 Mod End
        //        // , bizMsg.sellToCustCd.getValue() //// QC#18235 2017/04/12 Mod
        //        , bizMsg.shipToCustAcctCd.getValue() //
        //        , ONBATCH_TYPE.ONLINE);
        //
        //if (S21ApiUtil.isXxMsgId(custInfoGetApiMsg)) {
        //    List<String> ml = S21ApiUtil.getXxMsgIdList(custInfoGetApiMsg);
        //    for (String msgId : ml) {
        //        bizMsg.setMessageInfo(msgId);
        //    }
        //    return true;
        //}
        //
        //for (int i = 0; i < custInfoGetApiMsg.EligibleCheckList.getValidCount(); i++) {
        //    NMZC610001_EligibleCheckListPMsg eligiblePMsg = custInfoGetApiMsg.EligibleCheckList.no(i);
        //
        //    // QC#11591 2016/07/28 Mod Start
        //    // if (ZYPConstant.FLG_OFF_N.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue()) //
        //    //        || ZYPConstant.FLG_OFF_N.equals(eligiblePMsg.dsAcctRelnShipToFlg_S.getValue())) {
        //    if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue())) {
        //        bizMsg.billToCustLocCd.setErrorInfo(1, NWZM1455E);
        //        return true;
        //    }
        //    // 2017/06/09 S21_NA#18296 Mod Start
        //    // if ( !ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnShipToFlg_S.getValue())) {
        //    //     bizMsg.shipToCustLocCd.setErrorInfo(1, NWZM1455E);
        //    //     return true;
        //    // }
        //    if ( !ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnRecipFlg.getValue())) {
        //        bizMsg.shipToCustLocCd.setErrorInfo(1, NWZM1455E);
        //        return true;
        //    }
        //    // 2017/06/09 S21_NA#18296 Mod End
        //    // QC#11591 2016/07/28 Mod End
        //}
        //
        //return false;
        // Del End 2018/02/26 QC#22967
    }

    // Add Start 2018/02/26 QC#22967
    /**
     * @param bizMsg NWAL1840CMsg
     * @param billToCustCd String
     * @param acctNum String
     * @param err1 EZDCStringItem
     * @param err2 EZDCStringItem
     * @param errMsgId String
     * @return boolean
     */
    private static boolean callCustInfoGetApiForCheckRelation(NWAL1840CMsg bizMsg, String billToCustCd, String acctNum, EZDCStringItem err1, EZDCStringItem err2, String errMsgId) {
        NMZC610001PMsg custInfoGetApiMsg = NWXC150001DsCheck.callCustInfoGetApiBillTo( //
                bizMsg.glblCmpyCd.getValue(), billToCustCd, acctNum, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(custInfoGetApiMsg)) {
            List<String> ml = S21ApiUtil.getXxMsgIdList(custInfoGetApiMsg);
            for (String msgId : ml) {
                bizMsg.setMessageInfo(msgId);
            }
            return true;
        }

        for (int i = 0; i < custInfoGetApiMsg.EligibleCheckList.getValidCount(); i++) {
            NMZC610001_EligibleCheckListPMsg eligiblePMsg = custInfoGetApiMsg.EligibleCheckList.no(i);

            if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue())) {
                err1.setErrorInfo(1, errMsgId);
                err2.setErrorInfo(1, errMsgId);
                return true;
            }
        }

        return false;
    }
    // Add End 2018/02/26 QC#22967

    /**
     * @param bizMsg
     * @return boolean - Error:false
     */
    private static boolean checkMerchandise(NWAL1840CMsg bizMsg) {

        boolean checkFlg = true;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).schdAgmtLineCancFlg_A.getValue())) {
                continue;
            }
            MDSETMsg lineMdseTmsg = NWAL1840CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), bizMsg.A.no(i).mdseCd_A.getValue());

            if (MDSE_TP.SET.equals(lineMdseTmsg.mdseTpCd.getValue())) {
                bizMsg.A.no(i).mdseCd_A.setErrorInfo(1, NWAM0839E);
                checkFlg = false;
            }
        }
        return checkFlg;
    }

    // QC#13918,13919 2016/09/07 Add Start
    /**
     * EasyPac Check
     * @param bizMsg
     * @return boolean - Error:false
     */
    private static boolean otherEasyPackCheck(NWAL1840CMsg bizMsg) {
        boolean checkFlg = true;
        if (NWXC150001DsCheck.isEasyPack(bizMsg.glblCmpyCd.getValue(), bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue())) {
            
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                NWAL1840_ACMsg lineMsg = bizMsg.A.no(i);

                if (ZYPConstant.FLG_ON_Y.equals(lineMsg.schdAgmtLineCancFlg_A.getValue())) {
                    continue;
                }
                MDSETMsg mdseTmsg = NWAL1840CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_A.getValue());

                if (mdseTmsg == null || !ZYPCommonFunc.hasValue(mdseTmsg.easyPackTpCd)) {
                    lineMsg.mdseCd_A.setErrorInfo(1, NWZM1530E);
                    checkFlg = false;
                    continue;
                }
            }
            if (!NWXC150001DsCheck.isSplyPgmContr(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), bizMsg.billToCustAcctCd.getValue(), bizMsg.billToCustLocCd.getValue())) {
                bizMsg.setMessageInfo(NWZM2008E);
                checkFlg = false;
            }
        }
        return checkFlg;
    }
    // QC#13918,13919 2016/09/07 Add End

    // QC#11340 2016/08/03 Del Start
//    /**
//     * @param bizMsg
//     * @return boolean - Error:false
//     */
//    private static boolean checkMdlComponent(NWAL1840CMsg bizMsg) {
//        if (!ZYPCommonFunc.hasValue(bizMsg.dsContrNum)) {
//            return true;
//        }
//
//        boolean checkFlg = true;
//        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
//
//            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).schdAgmtLineCancFlg_A.getValue())) {
//                continue;
//            }
//            if (!NWAL1840QueryForSaveSubmit.getInstance().checkMdlComponent(bizMsg, i)) {
//                bizMsg.A.no(i).mdseCd_A.setErrorInfo(1, NWAM0813E);
//                checkFlg = false;
//            }
//        }
//        return checkFlg;
//    }
    // QC#11340 2016/08/03 Del End

    /**
     * @param bizMsg
     * @return boolean - Error:false
     */
    private static boolean checkSbstItem(NWAL1840CMsg bizMsg) {

        boolean checkFlg = true;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).schdAgmtLineCancFlg_A.getValue())) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).sbstMdseCd_A)) {
                continue;
            }

            if (!NWAL1840QueryForSaveSubmit.getInstance().checkSbstItem(bizMsg, i)) {
                bizMsg.A.no(i).mdseCd_A.setErrorInfo(1, NWAM0817E);
                bizMsg.A.no(i).sbstMdseCd_A.setErrorInfo(1, NWAM0817E);
                checkFlg = false;
            }
        }
        return checkFlg;
    }

    /**
     * @param bizMsg
     * @return boolean - Error:false
     */
    private static boolean checkSchdLineQty(NWAL1840CMsg bizMsg) {
        boolean checkFlg = true;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL1840_ACMsg lineMsg = bizMsg.A.no(i);
            MDSETMsg lineMdseTmsg = NWAL1840CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_A.getValue());

            int schdLineSumQty = 0;
            for (int j = 0; j < bizMsg.B.getValidCount(); j++) {
                NWAL1840_BCMsg schdLineMsg = bizMsg.B.no(j);

                if (ZYPConstant.FLG_ON_Y.equals(schdLineMsg.schdAgmtPlnCancFlg_B.getValue())) {
                    continue;
                }

                if (lineMsg.schdAgmtLineNum_A.getValue().compareTo(schdLineMsg.schdAgmtLineNum_B.getValue()) == 0) {

                    // 2019/01/08 QC#29241 Mod Start
                    DS_ORD_LINE_PROC_DFNTMsg dsOrdlineProcDfn = getDsOrdlineProcDfn(bizMsg);
                    if (dsOrdlineProcDfn == null) {
                        // Not Found, Error
                    	schdLineMsg.xxChkBox_B.setErrorInfo(1, "NWAM0739E", new String[]{"DS Order Line Process Definition Master", "DS_ORD_LINE_PROC_DFN", "DS_ORD_TP_CD", bizMsg.dsOrdTpCd.getValue()});
                        continue;
                    }

                    if (NWXC150001DsCheck.checkOrdQtyVldFlg(bizMsg.glblCmpyCd.getValue(), bizMsg.dsOrdTpCd.getValue(), dsOrdlineProcDfn.dsOrdLineCatgCd.getValue(), bizMsg.slsDt.getValue())) {
                        if (schdLineMsg.ordQty_B.getValueInt() < lineMdseTmsg.cpoMinOrdQty.getValueInt()) {
                            // 2018/06/01 S21_NA#26273 mod start
//                            schdLineMsg.ordQty_B.setErrorInfo(1, NWAM0814E);
                            schdLineMsg.ordQty_B.setErrorInfo(1, NWZM2023E, new String[] {String.valueOf(lineMdseTmsg.cpoMinOrdQty.getValue()) });
                            // 2018/06/01 S21_NA#26273 mod end
                            checkFlg = false;
                        } else if (schdLineMsg.ordQty_B.getValueInt() > lineMdseTmsg.cpoMaxOrdQty.getValueInt()) {
                            // 2018/06/01 S21_NA#26273 mod start
//                            schdLineMsg.ordQty_B.setErrorInfo(1, NWAM0815E);
                            schdLineMsg.ordQty_B.setErrorInfo(1, NWZM2024E, new String[] {String.valueOf(lineMdseTmsg.cpoMaxOrdQty.getValue()) });
                            // 2018/06/01 S21_NA#26273 mod end
                            checkFlg = false;
                        } else if (schdLineMsg.ordQty_B.getValueInt() % lineMdseTmsg.cpoIncrOrdQty.getValueInt() != 0) {
                            // 2018/06/01 S21_NA#26273 mod start
//                            schdLineMsg.ordQty_B.setErrorInfo(1, NWAM0816E);
                            schdLineMsg.ordQty_B.setErrorInfo(1, NWZM2025E, new String[] {String.valueOf(lineMdseTmsg.cpoIncrOrdQty.getValue()) });
                            // 2018/06/01 S21_NA#26273 mod end
                            checkFlg = false;
                        }
                }
                    // 2019/01/08 QC#29241 Mod End

                    schdLineSumQty += schdLineMsg.ordQty_B.getValueInt();
                }
                // ShcdLine sumQty > Line Qty
                if (schdLineSumQty > lineMsg.schdAllwQty_A.getValueInt()) {
                    schdLineMsg.ordQty_B.setErrorInfo(1, NWAM0824E);
                    checkFlg = false;
                }

            }
        }
        return checkFlg;
    }

    /**
     * Check Rerun Price $ Event
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     * @return Need ReRun : true
     */
    public static boolean needRePricing(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        boolean needRerun = false;

        if (needRePricingForHeader(bizMsg, glblMsg)) {
            needRerun = true;
        } else if (needRePricingForCustomer(bizMsg, glblMsg)) {
            needRerun = true;
        } else if (needRePricingForHeaderTab(bizMsg, glblMsg)) {
            needRerun = true;
        } else if (needRePricingForItemLine(bizMsg, glblMsg)) {
            needRerun = true;
        } else if (needRePricingForAddlData(bizMsg, glblMsg)) {
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
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     * @return Need ReRun : true
     */
    private static boolean needRePricingForHeader(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        if (!NWAL1840CommonLogic.isEqualsEZDItem(bizMsg.dsOrdTpCd, glblMsg.dsOrdTpCd)) {
            return true;
        } else if (!NWAL1840CommonLogic.isEqualsEZDItem(bizMsg.mdlNm, glblMsg.mdlNm)) {
            return true;
        } else if (!NWAL1840CommonLogic.isEqualsEZDItem(bizMsg.prcCatgNm, glblMsg.prcCatgNm)) {
            return true;
        }

        return false;
    }

    /**
     * Check Rerun Price $ Event For Customer
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     * @return Need ReRun : true
     */
    private static boolean needRePricingForCustomer(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        if (!NWAL1840CommonLogic.isEqualsEZDItem(bizMsg.billToCustAcctCd, glblMsg.billToCustAcctCd)) {
            return true;
        } else if (!NWAL1840CommonLogic.isEqualsEZDItem(bizMsg.billToCustLocCd, glblMsg.billToCustLocCd)) {
            return true;
        } else if (!NWAL1840CommonLogic.isEqualsEZDItem(bizMsg.shipToCustAcctCd, glblMsg.shipToCustAcctCd)) {
            return true;
        } else if (!NWAL1840CommonLogic.isEqualsEZDItem(bizMsg.shipToCustLocCd, glblMsg.shipToCustLocCd)) {
            return true;
        } else if (!NWAL1840CommonLogic.isEqualsEZDItem(bizMsg.sellToCustCd, glblMsg.sellToCustCd)) {
            return true;
        } else if (!NWAL1840CommonLogic.isEqualsEZDItem(bizMsg.shipToStCd, glblMsg.shipToStCd)) {
            return true;
        } else if (!NWAL1840CommonLogic.isEqualsEZDItem(bizMsg.shipToPostCd, glblMsg.shipToPostCd)) {
            return true;
        } else if (!NWAL1840CommonLogic.isEqualsEZDItem(bizMsg.shipToCntyNm, glblMsg.shipToCntyNm)) {
            return true;
        }

        return false;
    }

    /**
     * Check Rerun Price $ Event For Delivery / Payment
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     * @return Need Rerun : true
     */
    private static boolean needRePricingForHeaderTab(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        if (!NWAL1840CommonLogic.isEqualsEZDItem(bizMsg.frtCondDescTxt, glblMsg.frtCondDescTxt)) {
            return true;
        } else if (!NWAL1840CommonLogic.isEqualsEZDItem(bizMsg.spclHdlgTpCd, glblMsg.spclHdlgTpCd)) {
            return true;
        } else if (!NWAL1840CommonLogic.isEqualsEZDItem(bizMsg.shpgSvcLvlCd, glblMsg.shpgSvcLvlCd)) {
            return true;
        } else if (!NWAL1840CommonLogic.isEqualsEZDItem(bizMsg.slsRepTocCd, glblMsg.slsRepTocCd)) {
            return true;
        } else if (!NWAL1840CommonLogic.isEqualsEZDItem(bizMsg.custIssPoNum, glblMsg.custIssPoNum)) {
            return true;
        }

        return false;
    }

    /**
     * Check Rerun Price $ Event For Item Line
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     * @return Need ReRun : true
     */
    private static boolean needRePricingForItemLine(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        if (bizMsg.A.getValidCount() != glblMsg.A.getValidCount()) {
            return true;
        }

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL1840_ACMsg itemLineMsg = bizMsg.A.no(i);
            NWAL1840_ASMsg itemLineGlblMsg = glblMsg.A.no(i);

            if (!NWAL1840CommonLogic.isEqualsEZDItem(itemLineMsg.mdseCd_A, itemLineGlblMsg.mdseCd_A)) {
                return true;
            } else if (!NWAL1840CommonLogic.isEqualsEZDItem(itemLineMsg.pkgUomCd_A, itemLineGlblMsg.pkgUomCd_A)) {
                return true;
            } else if (!NWAL1840CommonLogic.isEqualsEZDItem(itemLineMsg.schdAllwQty_A, itemLineGlblMsg.schdAllwQty_A)) {
                return true;
            } else if (!NWAL1840CommonLogic.isEqualsEZDItem(itemLineMsg.dealNetUnitPrcAmt_A, itemLineGlblMsg.dealNetUnitPrcAmt_A)) {
                return true;
            } else if (!NWAL1840CommonLogic.isEqualsEZDItem(itemLineMsg.schdAgmtLineCancFlg_A, itemLineGlblMsg.schdAgmtLineCancFlg_A)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check Rerun Price $ Event For Additional Data
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     * @return Need ReRun : true
     */
    private static boolean needRePricingForAddlData(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        if (!NWAL1840CommonLogic.isEqualsEZDItem(bizMsg.prcContrNum, glblMsg.prcContrNum)) {
            return true;
        }

        return false;
    }

    /**
     * Check Duplication PO Number
     * @param bizMsg NWAL1840CMsg
     * @param isCallSave Called Save Button
     * @return Duplication : true
     */
    public static boolean checkCustIssPoNum(NWAL1840CMsg bizMsg, boolean isCallSave) {
        boolean isDuplication = false;

        if (!ZYPCommonFunc.hasValue(bizMsg.custIssPoNum)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_PO, ZYPConstant.FLG_OFF_N);
            bizMsg.custIssPoNum_BK.clear();
            return false;
        } else if (ZYPCommonFunc.hasValue(bizMsg.custIssPoNum_BK) && (!bizMsg.custIssPoNum.getValue().equals(bizMsg.custIssPoNum_BK.getValue()))) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_PO, ZYPConstant.FLG_OFF_N);
        }

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg_PO.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.custIssPoNum_BK, bizMsg.custIssPoNum);
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.custIssPoNum_BK, bizMsg.custIssPoNum);

        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg_PO.getValue())) {

            String buttonNm = null;

            if (isCallSave) {
                if (NWAL1840QueryForSaveSubmit.getInstance().isDuplicationPoNumForSchd(bizMsg)) {
                    isDuplication = true;
                    buttonNm = MSG_PARAM_SAVE;
                }
            } else {
                if (NWAL1840QueryForSaveSubmit.getInstance().isDuplicationPoNumForSchd(bizMsg)) {
                    isDuplication = true;
                    buttonNm = MSG_PARAM_SUBMIT;
                }
            }

            if (isDuplication) {
                bizMsg.custIssPoNum.setErrorInfo(2, NWAM0793W, new String[] {MSG_PARAM_CUST_PO_NUM, buttonNm });
                bizMsg.setMessageInfo(NWAM0793W, new String[] {MSG_PARAM_CUST_PO_NUM, buttonNm });
                ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_PO, ZYPConstant.FLG_ON_Y);
                // 2017/11/20 S21_NA#22388 Del Start
                //bizMsg.xxDplyTab.setValue(TAB_HEADER);
                // 2017/11/20 S21_NA#22388 Del End
            }
        }
        return isDuplication;
    }

    /**
     * Check Mandatory Field For Header
     * @param bizMsg NWAL1840CMsg
     * @return No Error : true
     */
    private static boolean checkMandatoryHeader(NWAL1840CMsg bizMsg) {

        boolean isNormal = true;

        if (!ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgDescTxt)) {
            bizMsg.dsOrdCatgDescTxt.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_CATG });
            isNormal = false;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
            bizMsg.dsOrdTpCd.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_RSN_CD });
            isNormal = false;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.cpoSrcTpDescTxt)) {
            bizMsg.cpoSrcTpDescTxt.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_SRC_NM });
            isNormal = false;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.schdAgmtVldFromDt)) {
            bizMsg.schdAgmtVldFromDt.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_VALID_FROM });
            isNormal = false;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.schdAgmtVldThruDt)) {
            bizMsg.schdAgmtVldThruDt.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_VALID_TO });
            isNormal = false;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.prcCatgNm)) {
            bizMsg.prcCatgNm.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_PRC_LIST });
            isNormal = false;
        } else {
            S21SsmEZDResult ssmResult = NWAL1840QueryForPricing.getInstance().getPrcCatgCd(bizMsg, bizMsg.prcCatgNm.getValue());
            if (ssmResult.isCodeNotFound()) {
                bizMsg.prcCatgNm.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_PRC_LIST });
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd, (String) ssmResult.getResultObject());
        }
        return isNormal;
    }

    /**
     * Check Mandatory Field For Customer
     * @param bizMsg NWAL1840CMsg
     * @return No Error : true
     */
    private static boolean checkMandatoryCustomerTab(NWAL1840CMsg bizMsg) {

        boolean isNormal = true;

        if (!ZYPCommonFunc.hasValue(bizMsg.billToCustAcctCd)) {
            bizMsg.billToCustAcctCd.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_BILL_TO_NUM });
            isNormal = false;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.billToCustLocCd)) {
            bizMsg.billToCustLocCd.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_BILL_TO_LOC });
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

        if (!ZYPCommonFunc.hasValue(bizMsg.shipToCustLocCd)) {
            bizMsg.shipToCustLocCd.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_SHIP_TO_LOC });
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

        return isNormal;
    }

    /**
     * Check Mandatory Field For Header Tab
     * @param bizMsg NWAL1840CMsg
     * @return No Error : true
     */
    private static boolean checkMandatoryHeaderTab(NWAL1840CMsg bizMsg, boolean isCallSave) {
//  private static boolean checkMandatoryHeaderTab(NWAL1840CMsg bizMsg) { // Mod 2016/09/15 S21_NA#13914

        boolean isNormal = true;

        if (!ZYPCommonFunc.hasValue(bizMsg.frtCondDescTxt)) {
            bizMsg.frtCondDescTxt.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_FRT_TERMS });
            isNormal = false;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.shpgSvcLvlCd)) {
            bizMsg.shpgSvcLvlCd.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_SHPG_SVC_LVL });
            isNormal = false;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.slsRepTocNm)) {
            bizMsg.slsRepTocNm.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_SLS_REP_NM });
            isNormal = false;
        }

        // 2016/05/13 S21_NA#7861 Mod Start
        // if (!ZYPCommonFunc.hasValue(bizMsg.slsRepPsnCd)) {
        // bizMsg.slsRepPsnCd.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_SLS_REP_CD });
        // isNormal = false;
        // }
        if (!ZYPCommonFunc.hasValue(bizMsg.psnNum)) {
            bizMsg.psnNum.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_SLS_REP_CD });
            isNormal = false;
        }
        // 2016/05/13 S21_NA#7861 Mod End

        // 2016/08/03 S21_NA#11657 Add Start
        if (!isCallSave && checkPoNum(bizMsg)) {
//      if (checkPoNum(bizMsg)) { // Mod 2016/09/15 S21_NA#13914
            bizMsg.custIssPoNum.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_CUST_PO_NUM});
            isNormal = false;
        }
        // 2016/08/03 S21_NA#11657 Add End
        return isNormal;
    }

    /**
     * Check Mandatory Field For Schedule Line Tab
     * @param bizMsg NWAL1840CMsg
     * @return No Error : true
     */
    private static boolean checkMandatorySchdLineTab(NWAL1840CMsg bizMsg) {

        boolean isNormal = true;

        if (bizMsg.A.getValidCount() == 0) {

            // Create New  Line.
            NWAL1840CommonLogic.setNewLine(bizMsg);
        }

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL1840_ACMsg itemLineMsg = bizMsg.A.no(i);

            if (ZYPConstant.FLG_ON_Y.equals(itemLineMsg.schdAgmtLineCancFlg_A.getValue())) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(itemLineMsg.mdseCd_A)) {
                itemLineMsg.mdseCd_A.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_ITEM });
                isNormal = false;
            }

            if (!ZYPCommonFunc.hasValue(itemLineMsg.pkgUomDescTxt_A)) {
                itemLineMsg.pkgUomDescTxt_A.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_UOM });
                isNormal = false;
            }

            if (!ZYPCommonFunc.hasValue(itemLineMsg.schdAllwQty_A)) {
                itemLineMsg.schdAllwQty_A.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_QTY_ALLW });
                isNormal = false;
            }

            if (!ZYPCommonFunc.hasValue(itemLineMsg.dealNetUnitPrcAmt_A)) {
                itemLineMsg.dealNetUnitPrcAmt_A.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_UNIT_SELL_PRC });
                isNormal = false;
            }
        }

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1840_BCMsg schdPlnMsg = bizMsg.B.no(i);

            if (ZYPConstant.FLG_ON_Y.equals(schdPlnMsg.schdAgmtPlnCancFlg_B.getValue())) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(schdPlnMsg.rddDt_B)) {
                schdPlnMsg.rddDt_B.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_RDD_DT });
                isNormal = false;
            }

            if (!ZYPCommonFunc.hasValue(schdPlnMsg.ordQty_B)) {
                schdPlnMsg.ordQty_B.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_ORD_QTY });
                isNormal = false;
            }
        }
        return isNormal;
    }

    /**
     * Check Modified By Other User
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     * @return Modified By Other User : true
     */
    public static boolean isModifiedByOtherUser(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.xxSrchNum)) {
            return false;
        }
        SCHD_AGMTTMsg schdAgmtTMsg = new SCHD_AGMTTMsg();
        ZYPEZDItemValueSetter.setValue(schdAgmtTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtTMsg.schdAgmtNum, bizMsg.schdAgmtNum);
        schdAgmtTMsg = (SCHD_AGMTTMsg) S21FastTBLAccessor.findByKey(schdAgmtTMsg);

        if (schdAgmtTMsg == null) {
            return false;
        }

        String origUpdtDt = glblMsg.ezUpTime.getValue();
        String origTimeZone = glblMsg.ezUpTimeZone.getValue();
        String curUpdtDt = schdAgmtTMsg.ezUpTime.getValue();
        String curTimeZone = schdAgmtTMsg.ezUpTimeZone.getValue();

        if (!ZYPDateUtil.isSameTimeStamp(origUpdtDt, origTimeZone, curUpdtDt, curTimeZone)) {
            bizMsg.setMessageInfo(NWAM0429E);
            return true;
        }

        return false;
    }

    /**
     * Execute Record Lock
     * @param bizMsg NWAL1840CMsg
     * @return Modified By Other User : false
     */
    public static boolean executeRecordLock(NWAL1840CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.schdAgmtStsCd)) {
            return true;
        }

        SCHD_AGMTTMsg schdAgmtTMsg = new SCHD_AGMTTMsg();
        ZYPEZDItemValueSetter.setValue(schdAgmtTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtTMsg.schdAgmtNum, bizMsg.schdAgmtNum);
        schdAgmtTMsg = (SCHD_AGMTTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(schdAgmtTMsg);

        if (schdAgmtTMsg == null) {
            bizMsg.setMessageInfo(NWAM0429E);
            return false;
        }

        return true;
    }

    /**
     * Call NWZC1710 Scheduling Agreement Update API
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     * @param isCallSave Save Flag
     */
    public static void callSchdAgmtUpdateApi(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg, boolean isCallSave) {

        NWZC171001PMsg schdAgmtUpdApiPMsg = new NWZC171001PMsg();

        // Create Parameter
        createHdrParam(bizMsg, schdAgmtUpdApiPMsg, isCallSave);
        createDtlParam(bizMsg, schdAgmtUpdApiPMsg, isCallSave);
        createPlnParam(bizMsg, schdAgmtUpdApiPMsg, isCallSave);
        createSlsCreditParam(bizMsg, schdAgmtUpdApiPMsg, isCallSave);
        createCtacPsnParam(bizMsg, glblMsg, schdAgmtUpdApiPMsg);

        // Call Schedule Agreement Update API
        new NWZC171001().execute(schdAgmtUpdApiPMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(schdAgmtUpdApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(schdAgmtUpdApiPMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                // 2018/01/12 S21_NA#20164 ADD START
                if(NWZM2251E.equals(msgId)){
                    bizMsg.sellToFirstRefCmntTxt.setErrorInfo(1, msgId);
                }
                // 2018/01/12 S21_NA#20164 ADD END
                bizMsg.setMessageInfo(msgId, msgPrms);
                return;
            }
        }

        for (int i = 0; i < schdAgmtUpdApiPMsg.NWZC171002PMsg.getValidCount(); i++) {
            NWZC171002PMsg pMsg = schdAgmtUpdApiPMsg.NWZC171002PMsg.no(i);
            if (S21ApiUtil.isXxMsgId(pMsg)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                for (S21ApiMessage msg : msgList) {
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    bizMsg.setMessageInfo(msgId, msgPrms);
                    return;
                }
            }
        }

        // Set Numbering Schedule Agreement Number
        ZYPEZDItemValueSetter.setValue(bizMsg.schdAgmtNum, schdAgmtUpdApiPMsg.schdAgmtNum);
        // ZYPEZDItemValueSetter.setValue(bizMsg.xxPgFlg, ZYPConstant.FLG_ON_Y);
        bizMsg.setMessageInfo(ZZM8100I);
    }

    /**
     * Create Header Parameter
     * @param bizMsg NWAL1840CMsg
     * @param schdAgmtApiPMsg NWZC171001PMsg
     * @param isSave Save Flag
     */
    private static void createHdrParam(NWAL1840CMsg bizMsg, NWZC171001PMsg schdAgmtApiPMsg, boolean isSave) {

        // Process Mode
        String procMode = NWZC171001Constant.MODE_SAVE;
        if (!isSave) {
            procMode = NWZC171001Constant.MODE_SUBMIT;
        }

        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.slsDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.xxModeCd, procMode);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.schdAgmtNum, bizMsg.xxSrchNum);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.refCpoOrdNum, bizMsg.refCpoOrdNum);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.schdCratTmplPk, bizMsg.schdCratTmplPk);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.schdAgmtCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.dsOrdRsnCd, bizMsg.dsOrdRsnCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.cpoOrdTpCd, bizMsg.cpoOrdTpCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.schdAgmtCratDt, bizMsg.schdAgmtCratDt);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.prcCatgCd, bizMsg.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.flPrcListCd, bizMsg.flPrcListCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.mdlId, bizMsg.mdlId);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.serNum, bizMsg.serNum);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.svcConfigMstrPk, bizMsg.svcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.dsContrNum, bizMsg.dsContrNum);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.dsContrSqNum, NWAL1840CommonLogic.getDsContrSqNum(bizMsg));
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.cpoSrcTpCd, bizMsg.cpoSrcTpCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.schdAgmtVldFromDt, bizMsg.schdAgmtVldFromDt);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.schdAgmtVldThruDt, bizMsg.schdAgmtVldThruDt);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.schdAgmtDelyHldCd, bizMsg.schdAgmtDelyHldCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.custIssPoNum, bizMsg.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.custIssPoDt, bizMsg.custIssPoDt);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.slsRepTocCd, bizMsg.slsRepTocCd);

        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.carrCd, bizMsg.carrCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.spclHdlgTpCd, bizMsg.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.frtCondCd, bizMsg.frtCondCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.carrSvcLvlCd, bizMsg.carrSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.shpgSvcLvlCd, bizMsg.shpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.frtChrgToCd, bizMsg.frtChrgToCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.frtChrgMethCd, bizMsg.frtChrgMethCd);

        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.sellToCustCd, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.soldToCustLocCd, bizMsg.soldToCustLocCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.billToCustAcctCd, bizMsg.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.billToCustLocCd, bizMsg.billToCustLocCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.shipToCustAcctCd, bizMsg.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.shipToCustLocCd, bizMsg.shipToCustLocCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.sellToFirstRefCmntTxt, bizMsg.sellToFirstRefCmntTxt);// S21_NA ADD QC#20246
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.adminPsnCd, bizMsg.adminPsnCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.dropShipFlg, bizMsg.dropShipFlg);
        // QC#11658 Mod Start
        // ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.shipToLocNm, bizMsg.shipToCustAcctNm);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.shipToLocNm, bizMsg.shipToLocNm);
        // QC#11658 Mod End
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.shipToAddlLocNm, bizMsg.shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.shipToFirstLineAddr, bizMsg.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.shipToScdLineAddr, bizMsg.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.shipToThirdLineAddr, bizMsg.shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.shipToFrthLineAddr, bizMsg.shipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.shipToCtyAddr, bizMsg.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.shipToStCd, bizMsg.shipToStCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.shipToProvNm, bizMsg.shipToProvNm);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.shipToPostCd, bizMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.shipToCntyNm, bizMsg.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.shipToCtryCd, bizMsg.shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.shipTo01RefCmntTxt, bizMsg.shipTo01RefCmntTxt);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.shipTo02RefCmntTxt, bizMsg.shipTo02RefCmntTxt);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.pmtTermCashDiscCd, bizMsg.pmtTermCashDiscCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.carrAcctNum, bizMsg.carrAcctNum);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.prcBaseDt, bizMsg.prcBaseDt);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.prcCalcDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.prcContrNum, bizMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.shpgCmntTxt, bizMsg.shpgCmntTxt);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.itrlOrdCmntTxt, bizMsg.itrlOrdCmntTxt);
        ZYPEZDItemValueSetter.setValue(schdAgmtApiPMsg.invCmntTxt, bizMsg.invCmntTxt);
    }

    /**
     * Create Detail Parameter
     * @param bizMsg NWAL1840CMsg
     * @param schdAgmtApiPMsg NWZC171001PMsg
     * @param isSave Save Flag
     */
    private static void createDtlParam(NWAL1840CMsg bizMsg, NWZC171001PMsg schdAgmtApiPMsg, boolean isSave) {

        int lineMsgValidCnt = 0;

        for (; lineMsgValidCnt < bizMsg.A.getValidCount(); lineMsgValidCnt++) {
            NWAL1840_ACMsg itemLineMsg = bizMsg.A.no(lineMsgValidCnt);
            BigDecimal schdAgmtDtlLineNum = itemLineMsg.schdAgmtLineNum_A.getValue();

            NWZC171002PMsg schdAgmtLinePMsg = schdAgmtApiPMsg.NWZC171002PMsg.no(lineMsgValidCnt);
            ZYPEZDItemValueSetter.setValue(schdAgmtLinePMsg.schdAgmtNum, bizMsg.xxSrchNum);
            ZYPEZDItemValueSetter.setValue(schdAgmtLinePMsg.schdAgmtLineNum, schdAgmtDtlLineNum);
            ZYPEZDItemValueSetter.setValue(schdAgmtLinePMsg.dsContrDtlPk, itemLineMsg.dsContrDtlPk_A);
            ZYPEZDItemValueSetter.setValue(schdAgmtLinePMsg.mdseCd, itemLineMsg.mdseCd_A);
            ZYPEZDItemValueSetter.setValue(schdAgmtLinePMsg.mdseNm, itemLineMsg.mdseNm_A);
            ZYPEZDItemValueSetter.setValue(schdAgmtLinePMsg.sbstMdseCd, itemLineMsg.sbstMdseCd_A);
            ZYPEZDItemValueSetter.setValue(schdAgmtLinePMsg.stkStsCd, STK_STS.GOOD);
            ZYPEZDItemValueSetter.setValue(schdAgmtLinePMsg.pkgUomCd, itemLineMsg.pkgUomCd_A);
            ZYPEZDItemValueSetter.setValue(schdAgmtLinePMsg.schdAllwQty, itemLineMsg.schdAllwQty_A);
            ZYPEZDItemValueSetter.setValue(schdAgmtLinePMsg.shpgIntvlCd, itemLineMsg.shpgIntvlCd_A);
            ZYPEZDItemValueSetter.setValue(schdAgmtLinePMsg.otmShipQty, itemLineMsg.otmShipQty_A);
            ZYPEZDItemValueSetter.setValue(schdAgmtLinePMsg.ccyCd, itemLineMsg.ccyCd_A);
            ZYPEZDItemValueSetter.setValue(schdAgmtLinePMsg.manPrcFlg, itemLineMsg.manPrcFlg_A);
            ZYPEZDItemValueSetter.setValue(schdAgmtLinePMsg.supdLockFlg, itemLineMsg.supdLockFlg_A);
            ZYPEZDItemValueSetter.setValue(schdAgmtLinePMsg.schdAgmtLineCancFlg, itemLineMsg.schdAgmtLineCancFlg_A);
            if (!ZYPCommonFunc.hasValue(schdAgmtLinePMsg.supdLockFlg)) {
                ZYPEZDItemValueSetter.setValue(schdAgmtLinePMsg.supdLockFlg, ZYPConstant.FLG_OFF_N);
            }

            // Set Calc Base Param
            int calcBaseValidCnt = 0;
            for (int i = 0; i < bizMsg.E.getValidCount(); i++) {
                NWAL1840_ECMsg calcBaseMsg = bizMsg.E.no(i);
                BigDecimal lineNum = calcBaseMsg.schdAgmtLineNum_E.getValue();

                if (lineNum.compareTo(schdAgmtDtlLineNum) == 0) {
                    NWZC171001_EPMsg calcBaseApiMsg = schdAgmtApiPMsg.E.no(schdAgmtApiPMsg.E.getValidCount() + calcBaseValidCnt);
                    EZDMsg.copy(calcBaseMsg, "E", calcBaseApiMsg, "");
                    ZYPEZDItemValueSetter.setValue(calcBaseApiMsg.schdAgmtLineNum, lineNum);
                    calcBaseValidCnt++;
                }
            }
            schdAgmtApiPMsg.E.setValidCount(schdAgmtApiPMsg.E.getValidCount() + calcBaseValidCnt);
        }
        schdAgmtApiPMsg.NWZC171002PMsg.setValidCount(lineMsgValidCnt);
    }

    /**
     * Create Detail Parameter
     * @param bizMsg NWAL1840CMsg
     * @param schdAgmtApiPMsg NWZC171001PMsg
     * @param isSave Save Flag
     */
    private static void createPlnParam(NWAL1840CMsg bizMsg, NWZC171001PMsg schdAgmtApiPMsg, boolean isSave) {

        int lineMsgValidCnt = 0;

        for (; lineMsgValidCnt < bizMsg.B.getValidCount(); lineMsgValidCnt++) {
            NWAL1840_BCMsg plnLineMsg = bizMsg.B.no(lineMsgValidCnt);
            BigDecimal schdAgmtDtlLineNum = plnLineMsg.schdAgmtLineNum_B.getValue();

            NWZC171003PMsg schdAgmtLinePMsg = schdAgmtApiPMsg.NWZC171003PMsg.no(lineMsgValidCnt);
            ZYPEZDItemValueSetter.setValue(schdAgmtLinePMsg.schdAgmtNum, bizMsg.xxSrchNum);
            ZYPEZDItemValueSetter.setValue(schdAgmtLinePMsg.schdAgmtLineNum, schdAgmtDtlLineNum);
            ZYPEZDItemValueSetter.setValue(schdAgmtLinePMsg.schdAgmtSchdLineNum, plnLineMsg.schdAgmtSchdLineNum_B);
            ZYPEZDItemValueSetter.setValue(schdAgmtLinePMsg.rddDt, plnLineMsg.rddDt_B);
            ZYPEZDItemValueSetter.setValue(schdAgmtLinePMsg.ordQty, plnLineMsg.ordQty_B);
            ZYPEZDItemValueSetter.setValue(schdAgmtLinePMsg.schdAgmtPlnCancFlg, plnLineMsg.schdAgmtPlnCancFlg_B);

        }
        schdAgmtApiPMsg.NWZC171003PMsg.setValidCount(lineMsgValidCnt);
    }

    /**
     * Create Sales Credit Parameter
     * @param bizMsg NWAL1840CMsg
     * @param schdAgmtApiPMsg NWZC171001PMsg
     * @param isSave Save Flag
     */
    private static void createSlsCreditParam(NWAL1840CMsg bizMsg, NWZC171001PMsg schdAgmtApiPMsg, boolean isSave) {

        int validCnt = 0;
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            NWAL1840_CCMsg slsCreditMsg = bizMsg.C.no(i);
            NWZC171001_CPMsg slsCreditApiMsg = schdAgmtApiPMsg.C.no(validCnt);
            EZDMsg.copy(slsCreditMsg, "C", slsCreditApiMsg, "");

            // Request Type
            String rqstTpCd = NWZC171001Constant.REQUEST_TYPE_NEW;
            if (!ZYPCommonFunc.hasValue(slsCreditMsg.xxRqstTpCd_C) && ZYPCommonFunc.hasValue(slsCreditMsg.schdAgmtSlsCrPk_C)) {
                rqstTpCd = NWZC150001Constant.RQST_TP_SLS_CR_MODIFY;
            } else if (NWZC150001Constant.RQST_TP_SLS_CR_NEW.equals(slsCreditMsg.xxRqstTpCd_C.getValue()) && ZYPCommonFunc.hasValue(slsCreditMsg.schdAgmtSlsCrPk_C)) {
                rqstTpCd = NWZC150001Constant.RQST_TP_SLS_CR_MODIFY;
            } else if (ZYPCommonFunc.hasValue(slsCreditMsg.xxRqstTpCd_C)) {
                rqstTpCd = slsCreditMsg.xxRqstTpCd_C.getValue();
            }
            ZYPEZDItemValueSetter.setValue(slsCreditApiMsg.xxRqstTpCd, rqstTpCd);

            validCnt++;
        }

        schdAgmtApiPMsg.C.setValidCount(validCnt);
    }

    /**
     * Create Contact Person Parameter
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     * @param schdAgmtApiPMsg NWZC171001PMsg
     */
    private static void createCtacPsnParam(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg, NWZC171001PMsg schdAgmtApiPMsg) {

        int validCnt = 0;

        // Set Insert Or Update Param
        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            NWAL1840_DCMsg ctacLineMsg = bizMsg.D.no(i);
            NWZC171001_DPMsg ctacLineApiMsg = schdAgmtApiPMsg.D.no(validCnt);

            EZDMsg.copy(ctacLineMsg, "D", ctacLineApiMsg, "");
            // Request Type
            if (ZYPCommonFunc.hasValue(ctacLineMsg.schdAgmtCtacPsnPk_D)) {
                ZYPEZDItemValueSetter.setValue(ctacLineApiMsg.xxRqstTpCd, NWZC171001Constant.REQUEST_TYPE_MOD);
            } else {
                ZYPEZDItemValueSetter.setValue(ctacLineApiMsg.xxRqstTpCd, NWZC171001Constant.REQUEST_TYPE_NEW);
            }
            ZYPEZDItemValueSetter.setValue(ctacLineApiMsg.ctacPsnOvrdFlg, ZYPConstant.FLG_OFF_N);
            validCnt++;
        }

        // Set Delete Param
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            NWAL1840_DSMsg ctacLineGlblMsg = glblMsg.D.no(i);
            NWZC171001_DPMsg ctacLineApiMsg = schdAgmtApiPMsg.D.no(validCnt);

            EZDMsg.copy(ctacLineGlblMsg, "D", ctacLineApiMsg, "");
            ZYPEZDItemValueSetter.setValue(ctacLineApiMsg.xxRqstTpCd, NWZC171001Constant.REQUEST_TYPE_DEL);
            validCnt++;
        }

        schdAgmtApiPMsg.D.setValidCount(validCnt);
    }

    /**
     * Call Contact UpdateAPI
     * @param bizMsg NWAL1840CMsg
     * @return boolean
     */
    // Mod Start 2016/07/25 S21_NA#11714
    // public static void callContactUpdateApi(NWAL1840CMsg bizMsg) {
    public static boolean callContactUpdateApi(NWAL1840CMsg bizMsg) {
        // Mod End 2016/07/25 S21_NA#11714

        //QC#16452 mod Start
//        S21SsmEZDResult ssmResult = NWAL1840Query.getInstance().getShipToInfo(bizMsg);
        S21SsmEZDResult ssmResult;

        String locNum = "";
        String dsAcctNum = "";
//        if (!ssmResult.isCodeNotFound()) {
//            Map<String, String> shipToCustAddrInfo = (Map<String, String>) ssmResult.getResultObject();
//            locNum = shipToCustAddrInfo.get("LOC_NUM");
//            dsAcctNum = shipToCustAddrInfo.get("SELL_TO_CUST_CD");
//        }
        //QC#16452 mod End
        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {

            NMZC002001PMsg nmzc002001pMsg = new NMZC002001PMsg();

            NWAL1840_DCMsg ctacMsg = bizMsg.D.no(i);

//            if (ZYPCommonFunc.hasValue(ctacMsg.ctacPsnPk_D)) {
//                continue;
//            }

            // QC#16452 add Start
            ssmResult = NWAL1840Query.getInstance().getLocationNumber(bizMsg, ctacMsg);
            if (!ssmResult.isCodeNotFound()) {
                locNum = (String) ssmResult.getResultObject();
            }
            // QC#16452 add End

            ZYPEZDItemValueSetter.setValue(nmzc002001pMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
            ZYPEZDItemValueSetter.setValue(nmzc002001pMsg.glblCmpyCd, bizMsg.glblCmpyCd);

            ZYPEZDItemValueSetter.setValue(nmzc002001pMsg.locNum, locNum);
            ZYPEZDItemValueSetter.setValue(nmzc002001pMsg.dsAcctNum, dsAcctNum);
            ZYPEZDItemValueSetter.setValue(nmzc002001pMsg.slsDt, bizMsg.slsDt);
            ZYPEZDItemValueSetter.setValue(nmzc002001pMsg.effFromDt, bizMsg.slsDt);
            ZYPEZDItemValueSetter.setValue(nmzc002001pMsg.ctacPsnFirstNm, ctacMsg.ctacPsnFirstNm_D);
            ZYPEZDItemValueSetter.setValue(nmzc002001pMsg.ctacPsnLastNm, ctacMsg.ctacPsnLastNm_D);
            ZYPEZDItemValueSetter.setValue(nmzc002001pMsg.ctacTpCd, ctacMsg.ctacPsnTpCd_D);
            ZYPEZDItemValueSetter.setValue(nmzc002001pMsg.ctacPsnCmntTxt, ctacMsg.ctacPsnCmntTxt_D);
            ZYPEZDItemValueSetter.setValue(nmzc002001pMsg.ctacPsnActvFlg, ZYPConstant.FLG_ON_Y);

            NMZC002001_ContactPointInfoListPMsg contactPntPMsg = null;
            int contactVldCnt = 0;

            if (ZYPCommonFunc.hasValue(ctacMsg.ctacPsnTelNum_D)) {
                contactPntPMsg = nmzc002001pMsg.ContactPointInfoList.no(contactVldCnt);
                ZYPEZDItemValueSetter.setValue(contactPntPMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
                ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsCtacPntTpCd, DS_CTAC_PNT_TP.PHONE_WORK);
                ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsCtacPntValTxt, ctacMsg.ctacPsnTelNum_D);
                ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsCtacPsnExtnNum, ctacMsg.ctacPsnExtnNum_D);
                ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
                // 2019/01/16 QC#29642 Add Start
                ZYPEZDItemValueSetter.setValue(contactPntPMsg.updCtrlFlg, ZYPConstant.FLG_ON_Y);
                // 2019/01/16 QC#29642 Add End
                contactVldCnt++;
            }

            if (ZYPCommonFunc.hasValue(ctacMsg.ctacPsnEmlAddr_D)) {
                contactPntPMsg = nmzc002001pMsg.ContactPointInfoList.no(contactVldCnt);
                ZYPEZDItemValueSetter.setValue(contactPntPMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
                ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsCtacPntTpCd, DS_CTAC_PNT_TP.EMAIL_WORK);
                ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsCtacPntValTxt, ctacMsg.ctacPsnEmlAddr_D);
                // 2019/01/16 QC#29642 Del Start
                //ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsCtacPsnExtnNum, ctacPsnPMsg.ctacPsnExtnNum_C);
                // 2019/01/16 QC#29642 Del End
                ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
                contactVldCnt++;
            }

            if (ZYPCommonFunc.hasValue(ctacMsg.ctacPsnFaxNum_D)) {
                contactPntPMsg = nmzc002001pMsg.ContactPointInfoList.no(contactVldCnt);
                ZYPEZDItemValueSetter.setValue(contactPntPMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
                ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsCtacPntTpCd, DS_CTAC_PNT_TP.FAX_WORK);
                ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsCtacPntValTxt, ctacMsg.ctacPsnFaxNum_D);
                // 2019/01/16 QC#29642 Del Start
                //ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsCtacPsnExtnNum, ctacPsnPMsg.ctacPsnExtnNum_C);
                // 2019/01/16 QC#29642 Del End
                ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
                contactVldCnt++;
            }

            nmzc002001pMsg.ContactPointInfoList.setValidCount(contactVldCnt);
            new NMZC002001().execute(nmzc002001pMsg, ONBATCH_TYPE.ONLINE);

            List<String> errList = S21ApiUtil.getXxMsgIdList(nmzc002001pMsg);

            if (!errList.isEmpty()) {
                for (String xxMsgId : errList) {
                    if (xxMsgId.endsWith("E")) {
                        bizMsg.setMessageInfo(xxMsgId);
                        
                        // Mod Start 2016/07/25 S21_NA#11714
                        // return;
                        return false;
                        // Mod End 2016/07/25 S21_NA#11714
                    }
                }
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).ctacPsnPk_D, nmzc002001pMsg.ctacPsnPk);
        }

        return true; // Add 2016/07/25 S21_NA#11714
    }

    private static boolean checkPoNum(NWAL1840CMsg bizMsg) {
        boolean errFlg = false;

        String trxRuleTp = getTrxRuleTp(bizMsg.glblCmpyCd.getValue(), bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
        //String trxRuleTp = NWAL1840CommonLogicForCustomer.getTrxRuleTp(bizMsg);

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
                String dsPoReqFlg = pMsg.TransactionRuleList.no(i).dsPoReqFlg.getValue();
                if (ZYPConstant.FLG_ON_Y.equals(dsPoReqFlg)) {
                    if (!ZYPCommonFunc.hasValue(bizMsg.custIssPoNum)) {
                        errFlg = true;
                        break;
                    }
                }
            }
        }

        return errFlg;
    }

    private static String getTrxRuleTp(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd) {

        ORD_CATG_BIZ_CTXTMsg tMsg = getOrdCatgBizCtx(glblCmpyCd, dsOrdCatgCd, dsOrdTpCd);

        if (tMsg == null) {
            return null;
        }
        return tMsg.firstBizCtxAttrbTxt.getValue();
    }

    private static ORD_CATG_BIZ_CTXTMsg getOrdCatgBizCtx(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd) {

        ORD_CATG_BIZ_CTXTMsg inTMsg = new ORD_CATG_BIZ_CTXTMsg();
        inTMsg.setSQLID("005");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("ordCatgCtxTpCd01A", EQUIPMENT_ORDER_VALUE_SET);
        inTMsg.setConditionValue("ordCatgCtxTpCd01B", SUPPLIES_ORDER_VALUE_SET);
        inTMsg.setConditionValue("dsOrdCatgCd01", dsOrdCatgCd);
        inTMsg.setConditionValue("dsOrdTpCd01", dsOrdTpCd);
        ORD_CATG_BIZ_CTXTMsgArray array = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);

        if (array == null || array.length() == 0) {
            return null;
        }
        return array.no(0);
    }

    // 2019/01/08 QC#29241 Add Start
    /**
     * Get DsOrdlineProcDfn
     * @param schdAgmt SCHD_AGMTTMsg
     * @return DS_ORD_LINE_PROC_DFNTMsg
     */
    private static DS_ORD_LINE_PROC_DFNTMsg getDsOrdlineProcDfn(NWAL1840CMsg bizMsg) {
        // Get DS_ORD_LINE_PROC_DFN
        DS_ORD_LINE_PROC_DFNTMsg dsOrdlineProcDfn = new DS_ORD_LINE_PROC_DFNTMsg();
        dsOrdlineProcDfn.setSQLID("001");
        dsOrdlineProcDfn.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        dsOrdlineProcDfn.setConditionValue("dsOrdTpCd01", bizMsg.dsOrdTpCd.getValue());
        dsOrdlineProcDfn.setConditionValue("effFromDt01", bizMsg.slsDt.getValue());
        dsOrdlineProcDfn.setConditionValue("effThruDt01", bizMsg.slsDt.getValue());
        dsOrdlineProcDfn.setConditionValue("actvFlg01", ZYPConstant.FLG_ON_Y);
        dsOrdlineProcDfn.setConditionValue("primLineCatgFlg01", ZYPConstant.FLG_ON_Y);

        DS_ORD_LINE_PROC_DFNTMsgArray dsOrdlineProcDfnList = (DS_ORD_LINE_PROC_DFNTMsgArray) EZDTBLAccessor.findByCondition(dsOrdlineProcDfn);
        if (dsOrdlineProcDfnList == null || dsOrdlineProcDfnList.getValidCount() == 0) {
            return null;
        }
        if (dsOrdlineProcDfnList.getValidCount() == 2) {
            dsOrdlineProcDfn = getOutBoundProcDfn(dsOrdlineProcDfnList);
        } else {
            dsOrdlineProcDfn = dsOrdlineProcDfnList.no(0);
        }
        return dsOrdlineProcDfn;
    }

    private static DS_ORD_LINE_PROC_DFNTMsg getOutBoundProcDfn(DS_ORD_LINE_PROC_DFNTMsgArray dsOrdlineProcDfnList) {
        DS_ORD_LINE_PROC_DFNTMsg dsOrdlineProcDfn = new DS_ORD_LINE_PROC_DFNTMsg();
        
        for (int i = 0 ; i < dsOrdlineProcDfnList.length(); i++) {
            dsOrdlineProcDfn = dsOrdlineProcDfnList.no(i);
            
            DS_ORD_LINE_CATGTMsg tMsg = new DS_ORD_LINE_CATGTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, dsOrdlineProcDfn.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsOrdLineCatgCd , dsOrdlineProcDfn.dsOrdLineCatgCd);
            tMsg = (DS_ORD_LINE_CATGTMsg) EZDTBLAccessor.findByKey(tMsg);
            
            if (tMsg != null) {
                if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(tMsg.dsOrdLineDrctnCd.getValue())) {
                    break; 
                }
            }
        }
        return dsOrdlineProcDfn;
    }
    // 2019/01/08 QC#29241 Add End

    // 2019/12/07 QC#54670 Add Start
    /**
     * Check SalesRep assign to correct IS Order 
     * @param bizMsg Business Message
     * @return true: normal end, false: abnornal end
     */
    public static boolean checkISOrderBindToISGroup(NWAL1840CMsg bizMsg) {
        boolean isOkFlg = true;
        BigDecimal count = NWAL1840QueryForSaveSubmit.getInstance().cntOrdTpCd(bizMsg);
        if (count.compareTo(BigDecimal.ZERO) == 0) {
            return isOkFlg;
        }

        count = NWAL1840QueryForSaveSubmit.getInstance().cntAssignedISGroup(bizMsg);
        if (count.compareTo(BigDecimal.ZERO) == 0) {
            isOkFlg = false;
            return isOkFlg;
        }
        return isOkFlg;
    }
    // 2019/12/07 QC#54670 Add End
    
    // QC#61281 Add Start
    public static boolean checkPmtTermCashDiscCd(NWAL1840CMsg bizMsg) {
        boolean isOkFlg = true;
        
        if (ZYPCommonFunc.hasValue(bizMsg.pmtTermCashDiscCd) && PMT_TERM_CASH_DISC.CREDIT_CARD.equals(bizMsg.pmtTermCashDiscCd.getValue())) {
            bizMsg.pmtTermCashDiscDescTxt.setErrorInfo(1, NWZM2321E);
            isOkFlg = false;
        }
        
        return isOkFlg;
    }
    // QC#61281 Add End

    // START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
    /**
     * checkNotesRegistered
     * @param bizMsg NWAL1840CMsg
     * @param isCallSave Save or not
     * @return true: normal, false: error
     */
    public static boolean checkNotesRegistered(NWAL1840CMsg bizMsg, boolean isCallSave) {

        if (isCallSave) {
            return true;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.startTs_NT) || !ZYPCommonFunc.hasValue(bizMsg.schdAgmtNum)) {
            return true;
        }

        NWAL1840QueryForSaveSubmit query = NWAL1840QueryForSaveSubmit.getInstance();
        Integer cnt = query.cntRegisteredNotes(bizMsg);
        if (cnt.intValue() == 0) {
            bizMsg.setMessageInfo(NWAM8475E);
            return false;
        }

        return true;
    }

    /**
     * checkMultiRegSchdAgmtForMach
     * @param bizMsg NWAL1840CMsg
     * @param isCallSave Save or not
     * @return true: normal, false: error
     */
    public static boolean checkMultiRegSchdAgmtForMach(NWAL1840CMsg bizMsg, boolean isCallSave) {

        if (isCallSave) {
            return true;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.serNum)) {
            return true;
        }

        String serNum = bizMsg.serNum.getValue();
        String dupValid = ZYPCodeDataUtil.getVarCharConstValue(KEY_NWAL1840_DUP_VALID, bizMsg.glblCmpyCd.getValue());
        if (DUP_VALID_ERROR.equals(dupValid)) {
            // error
            String schdAgmtNum = getMultiRegSchdAgmtForMach(bizMsg);
            if (schdAgmtNum != null) {
                bizMsg.setMessageInfo(NWAM8477E, new String[]{schdAgmtNum, serNum});
                return false;
            }
        } else {
            // warning
            if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg_NT.getValue())) {
                String schdAgmtNum = getMultiRegSchdAgmtForMach(bizMsg);
                if (schdAgmtNum != null) {
                    bizMsg.setMessageInfo(NWAM8476W, new String[]{schdAgmtNum, serNum});
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_NT, ZYPConstant.FLG_ON_Y);
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * getMultiRegSchdAgmtForMach
     * @param bizMsg
     * @return SCHD_AGMT_NUM(if exists)
     */
    @SuppressWarnings("unchecked")
    private static String getMultiRegSchdAgmtForMach(NWAL1840CMsg bizMsg) {

        NWAL1840QueryForSaveSubmit query = NWAL1840QueryForSaveSubmit.getInstance();

        S21SsmEZDResult ssmResult = query.getMultiRegSchdAgmtForMach(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }

        // Always use 1st record
        List<Map<String, Object>> saNumList = (List<Map<String, Object>>) ssmResult.getResultObject();
        Map<String, Object> saNum = saNumList.get(0);
        String schdAgmtNum = (String) saNum.get("SCHD_AGMT_NUM");
        return schdAgmtNum;
    }
    // END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
}
