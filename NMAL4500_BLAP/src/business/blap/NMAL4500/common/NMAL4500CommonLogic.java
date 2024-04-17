/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL4500.common;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCStringItem;
import parts.common.EZDPStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL4500.NMAL4500CMsg;
import business.blap.NMAL4500.NMAL4500Query;
import business.blap.NMAL4500.NMAL4500SMsg;
import business.blap.NMAL4500.constant.NMAL4500Constant;
import business.db.CHRG_RATE_VND_GRPTMsg;
import business.db.CHRG_RATE_VND_GRPTMsgArray;
import business.db.CMPYTMsg;
import business.db.CNTYTMsg;
import business.db.CNTYTMsgArray;
import business.db.CNTY_POST_RELNTMsg;
import business.db.COA_AFFLTMsg;
import business.db.COA_AFFLTMsgArray;
import business.db.CTRYTMsg;
import business.db.CTRYTMsgArray;
import business.db.FRT_CHRG_METHTMsg;
import business.db.FRT_CHRG_METHTMsgArray;
import business.db.FRT_CHRG_TOTMsg;
import business.db.FRT_CHRG_TOTMsgArray;
import business.db.INV_RCV_METH_TPTMsg;
import business.db.INV_RCV_METH_TPTMsgArray;
import business.db.POSTTMsg;
import business.db.POSTTMsgArray;
import business.db.PTYTMsg;
import business.db.PTYTMsgArray;
import business.db.PTY_LOC_WRKTMsg;
import business.db.SHPG_SVC_LVLTMsg;
import business.db.SHPG_SVC_LVLTMsgArray;
import business.db.TRSMT_METH_TPTMsg;
import business.db.TRSMT_METH_TPTMsgArray;
import business.db.VNDTMsg;
import business.db.VNDTMsgArray;
import business.db.VND_TP_RELNTMsg;
import business.db.VND_TP_RELNTMsgArray;
import business.db.WHTMsg;
import business.db.WHTMsgArray;
import business.parts.NMZC003001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC003001.NMZC003001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/08   Fujitsu         F.Saito         Update          WDS#R-MS001
 * 2013/09/19   Fujitsu         N.Sugiura       Update          MEX-LC004
 * 2013/10/21   Fujitsu         D.Yanagisawa    Update          MEX-LC001
 * 2016/08/01   CITS            S.Endo          Update          QC#10840
 *</pre>
 */
public class NMAL4500CommonLogic implements NMAL4500Constant {

    /*****************************************************************
     * ******************** public method
     * ***********************************************************
     ****************************************************************/

    // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // /////////////// ErrMessage method
    // ///////////////////////////////////////////////////////////
    // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @param bizMsg bizMsg
     * @return boolean
     */
    public static boolean isErrMessage(NMAL4500CMsg bizMsg) {

        String msgCode = bizMsg.getMessageCode();

        if (msgCode != null && msgCode.length() > 0) {
            if (msgCode.charAt(msgCode.length() - 1) == 'E') {
                return true;
            }
        }
        return false;
    }

    /**
     * @param glblCmpyCd glblCmpyCd
     * @param ptyCd ptyCd
     * @return boolean
     */
    public static boolean isDuplicateInventoryLocation(String glblCmpyCd, String ptyCd) {
        if (isDuplicateInventoryLocation_Party(glblCmpyCd, ptyCd)) {
            return true;
        }

        if (isDuplicateInventoryLocation_Consignee(glblCmpyCd, ptyCd)) {
            return true;
        }

        return false;
    }

    private static boolean isDuplicateInventoryLocation_Party(String glblCmpyCd, String ptyCd) {
        PTYTMsgArray pty = NMAL4500CommonLogic.db_pty_search(glblCmpyCd, ptyCd);
        if (pty.length() != 0) {
            return true;
        }

        return false;
    }

    private static boolean isDuplicateInventoryLocation_Consignee(String glblCmpyCd, String ptyCd) {
        S21SsmEZDResult ssmResult = NMAL4500Query.getInstance().getImportInvoiceConsigneeByPartyCode(glblCmpyCd, ptyCd);
        if (ssmResult.isCodeNormal()) {
            return true;
        }

        return false;
    }

    // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // /////////////// DB method
    // ///////////////////////////////////////////////////////////
    // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @param glblCmpyCd glblCmpyCd
     * @return CTRYTMsgArray
     */
    public static CTRYTMsgArray db_ctry_init(String glblCmpyCd) {
        return getCTRY_CD(glblCmpyCd);
    }

    /**
     * @param glblCmpyCd glblCmpyCd
     * @return CNTYTMsgArray
     */
    public static CNTYTMsgArray db_cnty_init(String glblCmpyCd) {
        return getCNTY_CD(glblCmpyCd);
    }

    // 10/22/2015 mod start
    // public static VND_TPTMsgArray db_vnd_tp_init(String glblCmpyCd)
    // {
    // return getVND_TP_CD(glblCmpyCd);
    // }
    /**
     * @param glblCmpyCd glblCmpyCd
     * @return List<Map>
     */
    public static List<Map> db_vnd_tp_init(String glblCmpyCd) {
        return getVND_TP_CD(glblCmpyCd);
    }

    // 10/22/2015 mod end

    // 10/21/2015 add start
    /**
     * @param glblCmpyCd glblCmpyCd
     * @return List<Map>
     */
    public static List<Map> db_carr_tp_init(String glblCmpyCd) {
        return getCARR_TP_CD(glblCmpyCd);
    }

    // 10/21/2015 add end

    /**
     * @param glblCmpyCd glblCmpyCd
     * @return SHPG_SVC_LVLTMsgArray
     */
    public static SHPG_SVC_LVLTMsgArray db_shpg_svc_lvl_init(String glblCmpyCd) {
        return getSHPG_SVC_LVL_CD(glblCmpyCd);
    }

    /**
     * @param glblCmpyCd glblCmpyCd
     * @return FRT_CHRG_TOTMsgArray
     */
    public static FRT_CHRG_TOTMsgArray db_frt_chrg_to_init(String glblCmpyCd) {
        return getFRT_CHRG_TO_CD(glblCmpyCd);
    }

    /**
     * @param glblCmpyCd glblCmpyCd
     * @return FRT_CHRG_METHTMsgArray
     */
    public static FRT_CHRG_METHTMsgArray db_frt_chrg_meth_init(String glblCmpyCd) {
        return getFRT_CHRG_METH_CD(glblCmpyCd);
    }

    /**
     * @param glblCmpyCd glblCmpyCd
     * @return TRSMT_METH_TPTMsgArray
     */
    public static TRSMT_METH_TPTMsgArray db_trsmt_meth_tp_init(String glblCmpyCd) {
        return getTRSMT_METH_TP_CD(glblCmpyCd);
    }

    /**
     * @param glblCmpyCd glblCmpyCd
     * @return INV_RCV_METH_TPTMsgArray
     */
    public static INV_RCV_METH_TPTMsgArray db_inv_rcv_meth_tp_init(String glblCmpyCd) {
        return getINV_RCV_METH_TP_CD(glblCmpyCd);
    }

    /**
     * @param glblCmpyCd glblCmpyCd
     * @return CHRG_RATE_VND_GRPTMsgArray
     */
    public static CHRG_RATE_VND_GRPTMsgArray db_chrg_rate_vnd_grp_init(String glblCmpyCd) {
        return getCHRG_RATE_VND_GRP_CD(glblCmpyCd);
    }

    /**
     * @param bizMsg bizMsg
     * @param glblCmpyCd glblCmpyCd
     * @return VNDTMsgArray
     */
    public static VNDTMsgArray db_vnd_search(NMAL4500CMsg bizMsg, String glblCmpyCd) {
        return getVND_search(glblCmpyCd, bizMsg.vndCd_01.getValue());
    }

    // ADD START 2013/10/21 MEX-LC001
    /**
     * @param glblCmpyCd glblCmpyCd
     * @param invVndCd invVndCd
     * @return VNDTMsgArray
     */
    public static VNDTMsgArray db_inv_vnd_search(String glblCmpyCd, String invVndCd) {
        return getVND_search(glblCmpyCd, invVndCd);
    }

    // ADD E N D 2013/10/21 MEX-LC001

    // <defect#5206 T.Ishii 20100405>
    /**
     * @param glblCmpyCd glblCmpyCd
     * @return WHTMsgArray
     */
    public static WHTMsgArray db_wh_init(String glblCmpyCd) {
        return getWH_CD(glblCmpyCd);
    }

    /**
     * @param glblCmpyCd glblCmpyCd
     * @param ptyCd ptyCd
     * @return PTYTMsgArray
     */
    public static PTYTMsgArray db_pty_search(String glblCmpyCd, String ptyCd) {
        // DefectID: 588
        PTYTMsg m = new PTYTMsg();
        m.setSQLID("005");
        m.setConditionValue("glblCmpyCd01", glblCmpyCd);
        m.setConditionValue("ptyCd01", ptyCd);
        return (PTYTMsgArray) EZDTBLAccessor.findByCondition(m);
    }

    /**
     * @param glblCmpyCd glblCmpyCd
     * @param vndtmsg vndtmsg
     * @return PTY_LOC_WRKTMsg
     */
    public static PTY_LOC_WRKTMsg db_ptylocwrk_search(String glblCmpyCd, VNDTMsg vndtmsg) {
        return getPTY_LOC_WRK_search(glblCmpyCd, vndtmsg);
    }

    /**
     * @param glblCmpyCd glblCmpyCd
     * @param vndtmsg vndtmsg
     * @return SHPG_SVC_LVLTMsg
     */
    public static SHPG_SVC_LVLTMsg db_shpgsvclvl_search(String glblCmpyCd, VNDTMsg vndtmsg) {
        return getSHPG_SVC_LVL_search(glblCmpyCd, vndtmsg.shpgSvcLvlCd.getValue());
    }

    /**
     * @param glblCmpyCd glblCmpyCd
     * @param vndtmsg vndtmsg
     * @return FRT_CHRG_TOTMsg
     */
    public static FRT_CHRG_TOTMsg db_frtchrgto_search(String glblCmpyCd, VNDTMsg vndtmsg) {
        return getFRT_CHRG_TO_search(glblCmpyCd, vndtmsg.frtChrgToCd.getValue());
    }

    /**
     * @param glblCmpyCd glblCmpyCd
     * @param vndtmsg vndtmsg
     * @return FRT_CHRG_METHTMsg
     */
    public static FRT_CHRG_METHTMsg db_frtchrgmeth_search(String glblCmpyCd, VNDTMsg vndtmsg) {
        return getFRT_CHRG_METH_search(glblCmpyCd, vndtmsg.frtChrgMethCd.getValue());
    }

    /**
     * @param glblCmpyCd glblCmpyCd
     * @param vndtmsg vndtmsg
     * @return TRSMT_METH_TPTMsg
     */
    public static TRSMT_METH_TPTMsg db_rtsmtmethtp_search(String glblCmpyCd, VNDTMsg vndtmsg) {
        return getTRSMT_METH_TP_search(glblCmpyCd, vndtmsg.trsmtMethTpCd.getValue());
    }

    /**
     * @param bizMsg bizMsg
     * @param glblCmpyCd glblCmpyCd
     * @return VND_TP_RELNTMsgArray
     */
    public static VND_TP_RELNTMsgArray db_vndtpreln_search(NMAL4500CMsg bizMsg, String glblCmpyCd) {
        return getVNDTPRELN_search(glblCmpyCd, bizMsg.vndCd_01.getValue());
    }

    /**
     * @param bizMsg bizMsg
     * @param glblCmpyCd glblCmpyCd
     * @param vndTpCd vndTpCd
     * @return VND_TP_RELNTMsg
     */
    public static VND_TP_RELNTMsg db_vndtpreln_search(NMAL4500CMsg bizMsg, String glblCmpyCd, String vndTpCd) {
        return getVNDTPRELN_search(glblCmpyCd, bizMsg.vndCd_01.getValue(), vndTpCd);
    }

    /**
     * @param bizMsg bizMsg
     * @param glblCmpyCd glblCmpyCd
     * @param vndTpCd vndTpCd
     * @return VND_TP_RELNTMsg
     */
    public static VND_TP_RELNTMsg db_vndtpreln_searchUpdate(NMAL4500CMsg bizMsg, String glblCmpyCd, String vndTpCd) {
        return getVNDTPRELN_searchUpdate(glblCmpyCd, bizMsg.vndCd_01.getValue(), vndTpCd);
    }

    /**
     * @param bizMsg bizMsg
     * @param glblCmpyCd glblCmpyCd
     * @param cmpyseq cmpyseq
     * @return CMPYTMsg
     */
    public static CMPYTMsg db_cmpy_search(NMAL4500CMsg bizMsg, String glblCmpyCd, BigDecimal cmpyseq) {
        return getCMPY_search(glblCmpyCd, cmpyseq);
    }

    /**
     * @param globalCompanyCode globalCompanyCode
     * @param vndtmsg vndtmsg
     * @return CHRG_RATE_VND_GRPTMsg
     */
    public static CHRG_RATE_VND_GRPTMsg db_chrgratevndgrp_search(String globalCompanyCode, VNDTMsg vndtmsg) {
        return getCHRGRATEVNDGRP_search(globalCompanyCode, vndtmsg.chrgRateVndGrpCd.getValue());
    }

    // BL06
    /**
     * @param bizMsg bizMsg
     * @param glblCmpyCd glblCmpyCd
     * @return boolean
     */
    public static boolean Country_check(NMAL4500CMsg bizMsg, String glblCmpyCd) {
        return ctry_check(bizMsg, glblCmpyCd);
    }

    // SHIP_TO_CUST
    // public static SHIP_TO_CUSTTMsgArray
    // db_shiptocust_search_nowait(String glblCmpyCd, BigDecimal
    // cmpyPk, String shipToCustCd) {
    // return getSHIPTOCUST_search_nowait(glblCmpyCd, cmpyPk,
    // shipToCustCd);
    // }

    /**
     * @param glblCmpyCd glblCmpyCd
     * @param affiliationNum affiliationNum
     * @return COA_AFFLTMsgArray
     */
    public static COA_AFFLTMsgArray db_affiliation_search(String glblCmpyCd, int affiliationNum) {
        return getAFFILIATION_search(glblCmpyCd, affiliationNum);
    }

    /*****************************************************************
     * ******************** private method
     * ***********************************************************
     ****************************************************************/

    // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // /////////////// DB method
    // ///////////////////////////////////////////////////////////
    // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static CTRYTMsgArray getCTRY_CD(String glblCmpyCd) {
        CTRYTMsg tmsg = new CTRYTMsg();
        /** CHG-START 2009/12/08 takamura order by SORT_NUM **/
        // def 1142
        // tmsg.setSQLID("001");
        // tmsg.setSQLID("005");
        tmsg.setSQLID("006");

        /** CHG-END **/
        tmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tmsg.setConditionValue("ctryVisFlg01", ZYPConstant.FLG_ON_Y);

        return (CTRYTMsgArray) EZDTBLAccessor.findByCondition(tmsg);
    }

    private static CNTYTMsgArray getCNTY_CD(String glblCmpyCd) {
        CNTYTMsg tmsg = new CNTYTMsg();
        tmsg.setSQLID("001");
        tmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);

        return (CNTYTMsgArray) EZDTBLAccessor.findByCondition(tmsg);
    }

    // 10/22/2015 mod start
    // private static VND_TPTMsgArray getVND_TP_CD(String glblCmpyCd)
    // {
    // VND_TPTMsg tmsg = new VND_TPTMsg();
    // tmsg.setSQLID("001");
    // tmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
    //
    // return (VND_TPTMsgArray) EZDTBLAccessor.findByCondition(tmsg);
    // }
    @SuppressWarnings("unchecked")
    private static List<Map> getVND_TP_CD(String glblCmpyCd) {
        S21SsmEZDResult ssmResult = NMAL4500Query.getInstance().getVndTp(glblCmpyCd);
        if (ssmResult.isCodeNormal()) {
            return (List<Map>) ssmResult.getResultObject();
        }
        return null;
    }

    // 10/22/2015 mod end

    // 10/21/2015 add start
    @SuppressWarnings("unchecked")
    private static List<Map> getCARR_TP_CD(String glblCmpyCd) {
        S21SsmEZDResult ssmResult = NMAL4500Query.getInstance().getCarrTp(glblCmpyCd);
        if (ssmResult.isCodeNormal()) {
            return (List<Map>) ssmResult.getResultObject();
        }
        return null;
    }

    // 10/21/2015 add end

    private static SHPG_SVC_LVLTMsgArray getSHPG_SVC_LVL_CD(String glblCmpyCd) {
        SHPG_SVC_LVLTMsg tmsg = new SHPG_SVC_LVLTMsg();
        tmsg.setSQLID("002");
        tmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);

        return (SHPG_SVC_LVLTMsgArray) EZDTBLAccessor.findByCondition(tmsg);
    }

    private static FRT_CHRG_TOTMsgArray getFRT_CHRG_TO_CD(String glblCmpyCd) {
        FRT_CHRG_TOTMsg tmsg = new FRT_CHRG_TOTMsg();
        tmsg.setSQLID("001");
        tmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);

        return (FRT_CHRG_TOTMsgArray) EZDTBLAccessor.findByCondition(tmsg);
    }

    private static FRT_CHRG_METHTMsgArray getFRT_CHRG_METH_CD(String glblCmpyCd) {
        FRT_CHRG_METHTMsg tmsg = new FRT_CHRG_METHTMsg();
        tmsg.setSQLID("001");
        tmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);

        return (FRT_CHRG_METHTMsgArray) EZDTBLAccessor.findByCondition(tmsg);
    }

    private static TRSMT_METH_TPTMsgArray getTRSMT_METH_TP_CD(String glblCmpyCd) {
        TRSMT_METH_TPTMsg tmsg = new TRSMT_METH_TPTMsg();
        tmsg.setSQLID("001");
        tmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);

        return (TRSMT_METH_TPTMsgArray) EZDTBLAccessor.findByCondition(tmsg);
    }

    private static INV_RCV_METH_TPTMsgArray getINV_RCV_METH_TP_CD(String glblCmpyCd) {
        INV_RCV_METH_TPTMsg tmsg = new INV_RCV_METH_TPTMsg();
        tmsg.setSQLID("001");
        tmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);

        return (INV_RCV_METH_TPTMsgArray) EZDTBLAccessor.findByCondition(tmsg);
    }

    private static CHRG_RATE_VND_GRPTMsgArray getCHRG_RATE_VND_GRP_CD(String glblCmpyCd) {
        CHRG_RATE_VND_GRPTMsg tmsg = new CHRG_RATE_VND_GRPTMsg();
        tmsg.setSQLID("001");
        tmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);

        return (CHRG_RATE_VND_GRPTMsgArray) EZDTBLAccessor.findByCondition(tmsg);
    }

    // <defect#5206 T.Ishii 20100405>
    private static WHTMsgArray getWH_CD(String glblCmpyCd) {
        WHTMsg tmsg = new WHTMsg();
        tmsg.setSQLID("036");
        tmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);

        return (WHTMsgArray) EZDTBLAccessor.findByCondition(tmsg);
    }

    private static VNDTMsgArray getVND_search(String glblCmpyCd, String vndCd) {
        VNDTMsg tmsg = new VNDTMsg();
        tmsg.setSQLID("013");
        tmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tmsg.setConditionValue("vndCd01", vndCd);

        return (VNDTMsgArray) EZDTBLAccessor.findByCondition(tmsg);
    }

    private static PTY_LOC_WRKTMsg getPTY_LOC_WRK_search(String glblCmpyCd, VNDTMsg vndtmsg) {
        PTY_LOC_WRKTMsg tmsg = new PTY_LOC_WRKTMsg();
        tmsg.glblCmpyCd.setValue(glblCmpyCd);
        tmsg.ptyLocPk.setValue(vndtmsg.ptyLocPk.getValue());
        return (PTY_LOC_WRKTMsg) EZDTBLAccessor.findByKey(tmsg);
    }

    private static SHPG_SVC_LVLTMsg getSHPG_SVC_LVL_search(String glblCmpyCd, String shpgSvcLvlCd) {
        SHPG_SVC_LVLTMsg tmsg = new SHPG_SVC_LVLTMsg();
        tmsg.glblCmpyCd.setValue(glblCmpyCd);
        tmsg.shpgSvcLvlCd.setValue(shpgSvcLvlCd);
        return (SHPG_SVC_LVLTMsg) EZDTBLAccessor.findByKey(tmsg);
    }

    private static FRT_CHRG_TOTMsg getFRT_CHRG_TO_search(String glblCmpyCd, String frtChrgToCd) {
        FRT_CHRG_TOTMsg tmsg = new FRT_CHRG_TOTMsg();
        tmsg.glblCmpyCd.setValue(glblCmpyCd);
        tmsg.frtChrgToCd.setValue(frtChrgToCd);
        return (FRT_CHRG_TOTMsg) EZDTBLAccessor.findByKey(tmsg);
    }

    private static FRT_CHRG_METHTMsg getFRT_CHRG_METH_search(String glblCmpyCd, String frtChrgMethCd) {
        FRT_CHRG_METHTMsg tmsg = new FRT_CHRG_METHTMsg();
        tmsg.glblCmpyCd.setValue(glblCmpyCd);
        tmsg.frtChrgMethCd.setValue(frtChrgMethCd);
        return (FRT_CHRG_METHTMsg) EZDTBLAccessor.findByKey(tmsg);
    }

    private static TRSMT_METH_TPTMsg getTRSMT_METH_TP_search(String glblCmpyCd, String trsmtMethTpCd) {
        TRSMT_METH_TPTMsg tmsg = new TRSMT_METH_TPTMsg();
        tmsg.glblCmpyCd.setValue(glblCmpyCd);
        tmsg.trsmtMethTpCd.setValue(trsmtMethTpCd);
        return (TRSMT_METH_TPTMsg) EZDTBLAccessor.findByKey(tmsg);
    }

    private static VND_TP_RELNTMsgArray getVNDTPRELN_search(String glblCmpyCd, String vndCd) {
        VND_TP_RELNTMsg tmsg = new VND_TP_RELNTMsg();
        tmsg.setSQLID("002");
        tmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        /* CHG : No.394 of Quality Center <10/02/09 Noda> */
        // tmsg.setConditionValue("VND_CD", glblCmpyCd);
        tmsg.setConditionValue("vndCd01", vndCd);
        /* END : No.394 */

        return (VND_TP_RELNTMsgArray) EZDTBLAccessor.findByCondition(tmsg);
    }

    private static VND_TP_RELNTMsg getVNDTPRELN_search(String glblCmpyCd, String vndCd, String vndTpCd) {
        VND_TP_RELNTMsg tmsg = new VND_TP_RELNTMsg();
        tmsg.glblCmpyCd.setValue(glblCmpyCd);
        tmsg.vndCd.setValue(vndCd);
        tmsg.vndTpCd.setValue(vndTpCd);

        return (VND_TP_RELNTMsg) EZDTBLAccessor.findByKey(tmsg);
    }

    private static VND_TP_RELNTMsg getVNDTPRELN_searchUpdate(String glblCmpyCd, String vndCd, String vndTpCd) {
        VND_TP_RELNTMsg tmsg = new VND_TP_RELNTMsg();
        tmsg.glblCmpyCd.setValue(glblCmpyCd);
        tmsg.vndCd.setValue(vndCd);
        tmsg.vndTpCd.setValue(vndTpCd);

        return (VND_TP_RELNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tmsg);
    }

    // CMPY
    private static CMPYTMsg getCMPY_search(String glblCmpyCd, BigDecimal cmpyseq) {
        CMPYTMsg tmsg = new CMPYTMsg();
        tmsg.glblCmpyCd.setValue(glblCmpyCd);
        tmsg.cmpyPk.setValue(cmpyseq);
        return (CMPYTMsg) EZDTBLAccessor.findByKey(tmsg);
    }

    // private static SHIP_TO_CUSTTMsgArray
    // getSHIPTOCUST_search_nowait(String glblCmpyCd, BigDecimal
    // cmpyPk, String shipToCustCd) {
    // SHIP_TO_CUSTTMsg m = new SHIP_TO_CUSTTMsg();
    // m.setSQLID("019");
    // m.setConditionValue("glblCmpyCd01", glblCmpyCd);
    // m.setConditionValue("shipToCustCd01", shipToCustCd);
    // m.setConditionValue("cmpyPk01", cmpyPk);
    // return (SHIP_TO_CUSTTMsgArray)
    // EZDTBLAccessor.findByConditionForUpdateNoWait(m);
    // }

    private static COA_AFFLTMsgArray getAFFILIATION_search(String glblCmpyCd, int affiliationNum) {

        COA_AFFLTMsg tmsg = new COA_AFFLTMsg();
        tmsg.setSQLID("002");
        tmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tmsg.setConditionValue("coaAfflSortNum01", affiliationNum);

        return (COA_AFFLTMsgArray) EZDTBLAccessor.findByCondition(tmsg);
    }

    private static CHRG_RATE_VND_GRPTMsg getCHRGRATEVNDGRP_search(String globalCompanyCode, String value) {
        CHRG_RATE_VND_GRPTMsg tmsg = new CHRG_RATE_VND_GRPTMsg();
        tmsg.glblCmpyCd.setValue(globalCompanyCode);
        tmsg.chrgRateVndGrpCd.setValue(value);
        return (CHRG_RATE_VND_GRPTMsg) EZDTBLAccessor.findByKey(tmsg);
    }

    // BL06 method
    private static boolean ctry_check(NMAL4500CMsg cMsg, String glblCmpyCd) {

        if (!ZYPCommonFunc.hasValue(cMsg.postCd_01)) {
            cMsg.postCd_01.setErrorInfo(1, "NMAM0039E", new String[] {"PostalCode" });
            cMsg.setMessageInfo("NMAM0039E", new String[] {"PostalCode" });
            return false;
        }

        // QC :5515
        String postCd = cMsg.postCd_01.getValue();

        POSTTMsg postC = new POSTTMsg();
        postC.setSQLID("006");
        postC.setConditionValue("glblCmpyCd01", glblCmpyCd);
        postC.setConditionValue("postCd01", postCd);
        POSTTMsgArray xxpost = (POSTTMsgArray) EZDTBLAccessor.findByCondition(postC);
        if (xxpost.length() == 0 && ZYPCommonFunc.hasValue(postCd) && postCd.length() > 5) {
            postC.setConditionValue("postCd01", postCd.substring(0, 5));
            xxpost = (POSTTMsgArray) EZDTBLAccessor.findByCondition(postC);
        }
        // Post check
        if (xxpost.length() == 0) {
            cMsg.postCd_01.setErrorInfo(1, "NMAM0039E", new String[] {"PostalCode" });
            cMsg.setMessageInfo("NMAM0039E", new String[] {"PostalCode" });
            return false;
        }
        postCd = xxpost.no(0).postCd.getValue();
        postC = new POSTTMsg();
        postC.setSQLID("007");
        postC.setConditionValue("glblCmpyCd01", glblCmpyCd);
        postC.setConditionValue("postCd01", postCd);
        postC.setConditionValue("ctyAddr01", cMsg.ctyAddr_01.getValue());
        xxpost = (POSTTMsgArray) EZDTBLAccessor.findByCondition(postC);

        // City check
        if (xxpost.length() == 0) {
            cMsg.ctyAddr_01.setErrorInfo(1, "NMAM0039E", new String[] {"City" });
            cMsg.setMessageInfo("NMAM0039E", new String[] {"City" });
            return false;

        }

        // State check
        if (!xxpost.no(ONE_ROW).stCd.getValue().equals(cMsg.stCd_01.getValue())) {
            cMsg.stCd_01.setErrorInfo(1, "NMAM0071E", new String[] {"State" });
            cMsg.setMessageInfo("NMAM0071E", new String[] {"State" });
            return false;

        }

        // County check
        if (ZYPCommonFunc.hasValue(cMsg.cntyPk_03)) {
            CNTY_POST_RELNTMsg cntypostrelnC = new CNTY_POST_RELNTMsg();
            cntypostrelnC.glblCmpyCd.setValue(glblCmpyCd);
            cntypostrelnC.cntyPk.setValue(cMsg.cntyPk_03.getValue());
            cntypostrelnC.postPk.setValue(xxpost.no(ONE_ROW).postPk.getValue());
            CNTY_POST_RELNTMsg xxcntypostreln = (CNTY_POST_RELNTMsg) EZDTBLAccessor.findByKey(cntypostrelnC);
            if (xxcntypostreln == null) {
                cMsg.cntyPk_03.setErrorInfo(1, "NMAM0039E", new String[] {"County" });
                cMsg.setMessageInfo("NMAM0039E", new String[] {"County" });
                return false;
            }

            CNTYTMsg cntyC = new CNTYTMsg();
            cntyC.glblCmpyCd.setValue(glblCmpyCd);
            cntyC.cntyPk.setValue(xxcntypostreln.cntyPk.getValue());
            CNTYTMsg xxcnty = (CNTYTMsg) EZDTBLAccessor.findByKey(cntyC);

            if (xxcnty == null) {
                cMsg.cntyPk_03.setErrorInfo(1, "NMAM0039E", new String[] {"County" });
                cMsg.setMessageInfo("NMAM0039E", new String[] {"County" });
                return false;
            }

            if (!cMsg.stCd_01.getValue().equals(xxcnty.stCd.getValue())) {
                cMsg.stCd_01.setErrorInfo(1, "NMAM0039E", new String[] {"State" });
                cMsg.setMessageInfo("NMAM0039E", new String[] {"State" });
                return false;
            }
        }

        return true;

    }

    /**
     * @param cMsg cMsg
     * @param globalCompanyCode globalCompanyCode
     * @param cmpySeq CMPYSeq
     * @return CMPYTMsg
     */
    public static CMPYTMsg setCreateCMPY(NMAL4500CMsg cMsg, String globalCompanyCode, BigDecimal cmpySeq) {
        // Create
        CMPYTMsg tcmpy = new CMPYTMsg();

        tcmpy.glblCmpyCd.setValue(globalCompanyCode);
        tcmpy.cmpyPk.setValue(cmpySeq);
        tcmpy.cmpyNm.setValue(cMsg.locNm_01.getValue());
        tcmpy.dbaNm.setValue(cMsg.dbaNm_01.getValue());

        return tcmpy;
    }

    /**
     * @param cMsg cMsg
     * @param tcmpy tcmpy
     */
    public static void setUpdateCMPY(NMAL4500CMsg cMsg, CMPYTMsg tcmpy) {
        tcmpy.cmpyNm.setValue(cMsg.locNm_01.getValue());
        tcmpy.dbaNm.setValue(cMsg.dbaNm_01.getValue());
    }

    /**
     * @param cMsg cMsg
     * @param globalCompanyCode globalCompanyCode
     * @param ptyLocWrkSeq PTY_LOC_WRKSeq
     * @return PTY_LOC_WRKTMsg
     */
    public static PTY_LOC_WRKTMsg setCreatePTYLOCWRK(NMAL4500CMsg cMsg, String globalCompanyCode, BigDecimal ptyLocWrkSeq) {
        // Create
        PTY_LOC_WRKTMsg tptylocwrk = new PTY_LOC_WRKTMsg();

        tptylocwrk.glblCmpyCd.setValue(globalCompanyCode);
        tptylocwrk.ptyLocPk.setValue(ptyLocWrkSeq);
        tptylocwrk.firstLineAddr.setValue(cMsg.firstLineAddr_01.getValue());
        tptylocwrk.scdLineAddr.setValue(cMsg.scdLineAddr_01.getValue());
        tptylocwrk.thirdLineAddr.setValue(cMsg.thirdLineAddr_01.getValue());
        tptylocwrk.frthLineAddr.setValue(cMsg.frthLineAddr_01.getValue());
        tptylocwrk.ctyAddr.setValue(cMsg.ctyAddr_01.getValue());
        tptylocwrk.provNm.setValue(cMsg.provNm_01.getValue());
        tptylocwrk.stCd.setValue(cMsg.stCd_01.getValue());
        tptylocwrk.postCd.setValue(cMsg.postCd_01.getValue());
        tptylocwrk.ctryCd.setValue(cMsg.ctryCd_03.getValue());
        tptylocwrk.locNm.setValue(cMsg.locNm_01.getValue());
        tptylocwrk.firstRefCmntTxt.setValue(cMsg.firstRefCmntTxt_01.getValue());
        tptylocwrk.scdRefCmntTxt.setValue(cMsg.scdRefCmntTxt_01.getValue());
        tptylocwrk.dbaNm.setValue(cMsg.dbaNm_01.getValue());
        tptylocwrk.telNum.setValue(cMsg.telNum_01.getValue());
        tptylocwrk.faxNum.setValue(cMsg.faxNum_01.getValue());
        tptylocwrk.cntyPk.setValue(cMsg.cntyPk_03.getValue());

        return tptylocwrk;
    }

    /**
     * @param cMsg cMsg
     * @param tptylocwrk tptylocwrk
     */
    public static void setUpdatePTYLOCWRK(NMAL4500CMsg cMsg, PTY_LOC_WRKTMsg tptylocwrk) {

        tptylocwrk.firstLineAddr.setValue(cMsg.firstLineAddr_01.getValue());
        tptylocwrk.scdLineAddr.setValue(cMsg.scdLineAddr_01.getValue());
        tptylocwrk.thirdLineAddr.setValue(cMsg.thirdLineAddr_01.getValue());
        tptylocwrk.frthLineAddr.setValue(cMsg.frthLineAddr_01.getValue());
        tptylocwrk.ctyAddr.setValue(cMsg.ctyAddr_01.getValue());
        tptylocwrk.provNm.setValue(cMsg.provNm_01.getValue());
        tptylocwrk.stCd.setValue(cMsg.stCd_01.getValue());
        tptylocwrk.postCd.setValue(cMsg.postCd_01.getValue());
        tptylocwrk.ctryCd.setValue(cMsg.ctryCd_03.getValue());
        tptylocwrk.locNm.setValue(cMsg.locNm_01.getValue());
        tptylocwrk.firstRefCmntTxt.setValue(cMsg.firstRefCmntTxt_01.getValue());
        tptylocwrk.scdRefCmntTxt.setValue(cMsg.scdRefCmntTxt_01.getValue());
        tptylocwrk.dbaNm.setValue(cMsg.dbaNm_01.getValue());
        tptylocwrk.telNum.setValue(cMsg.telNum_01.getValue());
        tptylocwrk.faxNum.setValue(cMsg.faxNum_01.getValue());
        tptylocwrk.cntyPk.setValue(cMsg.cntyPk_03.getValue());
    }

    /**
     * @param cMsg cMsg
     * @param globalCompanyCode globalCompanyCode
     * @param vndSeq VNDSeq
     * @return VNDTMsg
     */
    public static VNDTMsg setCreateVND(NMAL4500CMsg cMsg, String globalCompanyCode, BigDecimal vndSeq) {
        // Create
        VNDTMsg tvnd = new VNDTMsg();

        tvnd.glblCmpyCd.setValue(globalCompanyCode);
        tvnd.vndPk.setValue(vndSeq);
        tvnd.vndCd.setValue(cMsg.vndCd_01.getValue());
        tvnd.firstLineAddr.setValue(cMsg.firstLineAddr_01.getValue());
        tvnd.scdLineAddr.setValue(cMsg.scdLineAddr_01.getValue());
        tvnd.thirdLineAddr.setValue(cMsg.thirdLineAddr_01.getValue());
        tvnd.frthLineAddr.setValue(cMsg.frthLineAddr_01.getValue());
        tvnd.ctyAddr.setValue(cMsg.ctyAddr_01.getValue());
        tvnd.cntyPk.setValue(cMsg.cntyPk_03.getValue());
        tvnd.provNm.setValue(cMsg.provNm_01.getValue());
        tvnd.stCd.setValue(cMsg.stCd_01.getValue());
        tvnd.postCd.setValue(cMsg.postCd_01.getValue());
        tvnd.ctryCd.setValue(cMsg.ctryCd_03.getValue());
        tvnd.locNm.setValue(cMsg.locNm_01.getValue());
        tvnd.firstRefCmntTxt.setValue(cMsg.firstRefCmntTxt_01.getValue());
        tvnd.scdRefCmntTxt.setValue(cMsg.scdRefCmntTxt_01.getValue());
        tvnd.dbaNm.setValue(cMsg.dbaNm_01.getValue());
        tvnd.telNum.setValue(cMsg.telNum_01.getValue());
        tvnd.faxNum.setValue(cMsg.faxNum_01.getValue());
        tvnd.coaAfflCd.setValue(cMsg.coaAfflCd_01.getValue());
        tvnd.taxPayerId.setValue(cMsg.taxPayerId_01.getValue());

        tvnd.intlVndFlg.setValue(cMsg.intlVndFlg_01.getValue());
        tvnd.asnReqFlg.setValue(cMsg.asnReqFlg_01.getValue());
        tvnd.payeeFlg.setValue(cMsg.payeeFlg_01.getValue());
        tvnd.thirdPtyVndFlg.setValue(cMsg.thirdPtyVndFlg_01.getValue());

        tvnd.shpgSvcLvlCd.setValue(cMsg.shpgSvcLvlCd_03.getValue());
        tvnd.frtChrgToCd.setValue(cMsg.frtChrgToCd_03.getValue());
        tvnd.frtChrgMethCd.setValue(cMsg.frtChrgMethCd_03.getValue());
        tvnd.trsmtMethTpCd.setValue(cMsg.trsmtMethTpCd_03.getValue());
        tvnd.chrgRateVndGrpCd.setValue(cMsg.chrgRateVndGrpCd_03.getValue());
        ZYPEZDItemValueSetter.setValue(tvnd.sendPoEmlAddr, cMsg.sendPoEmlAddr_01);
        ZYPEZDItemValueSetter.setValue(tvnd.dealCcyCd, cMsg.dealCcyCd_01);
        ZYPEZDItemValueSetter.setValue(tvnd.invVndCd, cMsg.invVndCd_01);
        ZYPEZDItemValueSetter.setValue(tvnd.splyPmtFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tvnd.splyPoFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tvnd.sendArcsFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tvnd.carrTpCd, cMsg.carrTpCd_03);

        return tvnd;
    }

    /**
     * @param cMsg cMsg
     * @param tvnd cMsg
     */
    public static void setUpdateVND(NMAL4500CMsg cMsg, VNDTMsg tvnd) {
        tvnd.vndCd.setValue(cMsg.vndCd_01.getValue());
        tvnd.firstLineAddr.setValue(cMsg.firstLineAddr_01.getValue());
        tvnd.scdLineAddr.setValue(cMsg.scdLineAddr_01.getValue());
        tvnd.thirdLineAddr.setValue(cMsg.thirdLineAddr_01.getValue());
        tvnd.frthLineAddr.setValue(cMsg.frthLineAddr_01.getValue());
        tvnd.ctyAddr.setValue(cMsg.ctyAddr_01.getValue());
        tvnd.cntyPk.setValue(cMsg.cntyPk_03.getValue());
        tvnd.provNm.setValue(cMsg.provNm_01.getValue());
        tvnd.stCd.setValue(cMsg.stCd_01.getValue());
        tvnd.postCd.setValue(cMsg.postCd_01.getValue());
        tvnd.ctryCd.setValue(cMsg.ctryCd_03.getValue());
        tvnd.locNm.setValue(cMsg.locNm_01.getValue());
        tvnd.firstRefCmntTxt.setValue(cMsg.firstRefCmntTxt_01.getValue());
        tvnd.scdRefCmntTxt.setValue(cMsg.scdRefCmntTxt_01.getValue());
        tvnd.dbaNm.setValue(cMsg.dbaNm_01.getValue());
        tvnd.telNum.setValue(cMsg.telNum_01.getValue());
        tvnd.faxNum.setValue(cMsg.faxNum_01.getValue());
        tvnd.coaAfflCd.setValue(cMsg.coaAfflCd_01.getValue());
        tvnd.taxPayerId.setValue(cMsg.taxPayerId_01.getValue());

        tvnd.intlVndFlg.setValue(cMsg.intlVndFlg_01.getValue());
        tvnd.asnReqFlg.setValue(cMsg.asnReqFlg_01.getValue());
        tvnd.payeeFlg.setValue(cMsg.payeeFlg_01.getValue());
        tvnd.thirdPtyVndFlg.setValue(cMsg.thirdPtyVndFlg_01.getValue());

        tvnd.shpgSvcLvlCd.setValue(cMsg.shpgSvcLvlCd_03.getValue());
        tvnd.frtChrgToCd.setValue(cMsg.frtChrgToCd_03.getValue());
        tvnd.frtChrgMethCd.setValue(cMsg.frtChrgMethCd_03.getValue());
        tvnd.trsmtMethTpCd.setValue(cMsg.trsmtMethTpCd_03.getValue());
        tvnd.chrgRateVndGrpCd.setValue(cMsg.chrgRateVndGrpCd_03.getValue());
    }

    // public static SHIP_TO_CUSTTMsg
    // setCreateSHIPTOCUST(PTY_LOC_WRKTMsg source, String
    // globalCompanyCode, BigDecimal seq, String ptyCd) {
    // // Create
    // SHIP_TO_CUSTTMsg w = new SHIP_TO_CUSTTMsg();
    // w.glblCmpyCd.setValue(globalCompanyCode);
    // w.shipToCustPk.setValue(seq);
    // w.firstLineAddr.setValue(source.firstLineAddr.getValue());
    // w.scdLineAddr.setValue(source.scdLineAddr.getValue());
    // w.thirdLineAddr.setValue(source.thirdLineAddr.getValue());
    // w.frthLineAddr.setValue(source.frthLineAddr.getValue());
    // w.ctyAddr.setValue(source.ctyAddr.getValue());
    // w.cntyPk.setValue(source.cntyPk.getValue());
    // w.provNm.setValue(source.provNm.getValue());
    // w.stCd.setValue(source.stCd.getValue());
    // w.postCd.setValue(source.postCd.getValue());
    // w.ctryCd.setValue(source.ctryCd.getValue());
    // w.locNum.setValue(source.locNum.getValue());
    // w.locNm.setValue(source.locNm.getValue());
    // w.addlLocNm.setValue(source.addlLocNm.getValue());
    // w.glnNum.setValue(source.glnNum.getValue());
    // w.firstRefCmntTxt.setValue(source.firstRefCmntTxt.getValue());
    // w.scdRefCmntTxt.setValue(source.scdRefCmntTxt.getValue());
    // w.dbaNm.setValue(source.dbaNm.getValue());
    // w.dunsNum.setValue(source.dunsNum.getValue());
    // w.rgtnStsCd.setValue(source.rgtnStsCd.getValue());
    // w.ptyLocPk.setValue(source.ptyLocPk.getValue());
    // w.effFromDt.setValue(source.effFromDt.getValue());
    // w.effThruDt.setValue(source.effThruDt.getValue());
    // w.telNum.setValue(source.telNum.getValue());
    // w.faxNum.setValue(source.faxNum.getValue());
    // w.cmpyPk.setValue(source.cmpyPk.getValue());
    // w.shipToCustCd.setValue(ptyCd);
    // w.locRoleTpCd.setValue(VND);
    // w.locGrpCd.setValue(NUM_2);
    // w.cnsgnFlg.setValue(N);
    // w.oemFlg.setValue(N);
    // w.embgoFlg.setValue(N);
    // return w;
    // }
    //
    // public static void setUpdateSHIPTOCUST(PTY_LOC_WRKTMsg source,
    // SHIP_TO_CUSTTMsg dist) {
    // dist.firstLineAddr.setValue(source.firstLineAddr.getValue());
    // dist.scdLineAddr.setValue(source.scdLineAddr.getValue());
    // dist.thirdLineAddr.setValue(source.thirdLineAddr.getValue());
    // dist.frthLineAddr.setValue(source.frthLineAddr.getValue());
    // dist.ctyAddr.setValue(source.ctyAddr.getValue());
    // dist.cntyPk.setValue(source.cntyPk.getValue());
    // dist.provNm.setValue(source.provNm.getValue());
    // dist.stCd.setValue(source.stCd.getValue());
    // dist.postCd.setValue(source.postCd.getValue());
    // dist.ctryCd.setValue(source.ctryCd.getValue());
    // dist.locNum.setValue(source.locNum.getValue());
    // dist.locNm.setValue(source.locNm.getValue());
    // dist.addlLocNm.setValue(source.addlLocNm.getValue());
    // dist.glnNum.setValue(source.glnNum.getValue());
    // dist.firstRefCmntTxt.setValue(source.firstRefCmntTxt.getValue());
    // dist.scdRefCmntTxt.setValue(source.scdRefCmntTxt.getValue());
    // dist.dbaNm.setValue(source.dbaNm.getValue());
    // dist.dunsNum.setValue(source.dunsNum.getValue());
    // dist.rgtnStsCd.setValue(source.rgtnStsCd.getValue());
    // dist.ptyLocPk.setValue(source.ptyLocPk.getValue());
    // dist.effFromDt.setValue(source.effFromDt.getValue());
    // dist.effThruDt.setValue(source.effThruDt.getValue());
    // dist.telNum.setValue(source.telNum.getValue());
    // dist.faxNum.setValue(source.faxNum.getValue());
    // dist.cmpyPk.setValue(source.cmpyPk.getValue());
    // dist.locRoleTpCd.setValue(VND);
    // dist.locGrpCd.setValue(NUM_2);
    // }

    // ADD START 2013/05/08 WDS#R-MS001
    /**
     * Get VND Record
     * @param glblCmpyCd Global Company Code
     * @param vndPk Vendor PK
     * @return VND Record(TMsg)
     */
    public static VNDTMsg getDsVnd(String glblCmpyCd, BigDecimal vndPk) {
        VNDTMsg tmsg = new VNDTMsg();
        tmsg.glblCmpyCd.setValue(glblCmpyCd);
        tmsg.vndPk.setValue(vndPk);
        return (VNDTMsg) EZDTBLAccessor.findByKey(tmsg);
    }

    // ADD END 2013/05/08 WDS#R-MS001

    /**
     * check Address values using API.
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg cMsg
     * @param sMsg sMsg
     * @return boolean checkResult(NoError:true/HasError:false)
     */
    public static boolean checkAddress(String glblCmpyCd, NMAL4500CMsg cMsg, NMAL4500SMsg sMsg) {
        boolean rtrnCd = true;

        String addrChkFlg = ZYPCodeDataUtil.getVarCharConstValue(NMAL4500_ADDR_CHK_FLG, glblCmpyCd);

        if (ZYPCommonFunc.hasValue(addrChkFlg) //
                && ZYPConstant.FLG_OFF_N.equals(addrChkFlg)) {
            // Unnecessary address check.
            return rtrnCd;
        }

        // Check Address
        if (!callAddrValidApi(cMsg, glblCmpyCd)) {
            rtrnCd = false;
        } else {
            // Set sMsg.
            ZYPEZDItemValueSetter.setValue(sMsg.firstLineAddr_01, cMsg.firstLineAddr_01);
            ZYPEZDItemValueSetter.setValue(sMsg.scdLineAddr_01, cMsg.scdLineAddr_01);
            ZYPEZDItemValueSetter.setValue(sMsg.ctyAddr_01, cMsg.ctyAddr_01);
            ZYPEZDItemValueSetter.setValue(sMsg.stCd_01, cMsg.stCd_01);
            ZYPEZDItemValueSetter.setValue(sMsg.postCd_01, cMsg.postCd_01);
            ZYPEZDItemValueSetter.setValue(sMsg.cntyPk_03, cMsg.cntyPk_03);
        }
        return rtrnCd;
    }

    /**
     * <pre>
     * callAddrValidApi
     * </pre>
     * @param cMsg NMAL4500CMsg
     * @param glblCmpyCd String
     * @return boolean checkResult(NoError:true/HasError:false)
     */
    public static boolean callAddrValidApi(NMAL4500CMsg cMsg, String glblCmpyCd) {
        boolean rtrnCd = true;

        NMZC003001PMsg addrValidApiPMsg = new NMZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.firstLineAddr, cMsg.firstLineAddr_01);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.scdLineAddr, cMsg.scdLineAddr_01);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.ctyAddr, cMsg.ctyAddr_01);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.stCd, cMsg.stCd_01);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.postCd, cMsg.postCd_01);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.ctryCd, cMsg.ctryCd_03);
        if (cMsg.cntyPk_03.getValue() != null) {
            for (int i = 0; i < cMsg.cntyPk_01.length(); i++) {
                if (((EZDCBigDecimalItem) cMsg.cntyPk_01.get(i)).getValue() != null) {
                    if (cMsg.cntyPk_03.getValue().compareTo(((EZDCBigDecimalItem) cMsg.cntyPk_01.get(i)).getValue()) == 0) {
                        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.cntyNm, ((EZDCStringItem) cMsg.cntyNm_02.get(i)).getValue());
                    }
                } else {
                    break;
                }
            }
        }

        NMZC003001 addrValidApi = new NMZC003001();
        addrValidApi.execute(addrValidApiPMsg, ONBATCH_TYPE.ONLINE);

        List<String> msgIdList = S21ApiUtil.getXxMsgIdList(addrValidApiPMsg);

        // check Error
        // Address Line
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_01.getValue())) {
            cMsg.firstLineAddr_01.setErrorInfo(1, NMAM8454E, new String[] {"Ship to Location Address Line 1" });
            rtrnCd = false;
        }
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_02.getValue())) {
            cMsg.scdLineAddr_01.setErrorInfo(1, NMAM8454E, new String[] {"Ship to Location Address Line 2" });
            rtrnCd = false;
        }
        // City
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_03.getValue())) {
            cMsg.ctyAddr_01.setErrorInfo(1, NMAM8454E, new String[] {"Ship to Location City" });
            rtrnCd = false;
        }
        // State
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_04.getValue())) {
            cMsg.stCd_01.setErrorInfo(1, NMAM8454E, new String[] {"Ship to Location State" });
            rtrnCd = false;
        }
        // Postal code
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_05.getValue())) {
            cMsg.postCd_01.setErrorInfo(1, NMAM8454E, new String[] {"Ship to Location Postal Code" });
            rtrnCd = false;
        }
        // Ctry
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_06.getValue())) {
            cMsg.ctryCd_03.setErrorInfo(1, NMAM8454E, new String[] {"Ship to Location Country" });
            rtrnCd = false;
        }
        // Cnty
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_07.getValue())) {
            cMsg.cntyPk_03.setErrorInfo(1, NMAM8454E, new String[] {"Ship to Location County" });
            rtrnCd = false;
        }

        if (msgIdList != null && msgIdList.size() > 0) {
            for (int i = 0; i < msgIdList.size(); i++) {
                setAddrValidResult((String) msgIdList.get(0), cMsg.firstLineAddr_01, addrValidApiPMsg.xxVldStsCd_01);
                setAddrValidResult((String) msgIdList.get(0), cMsg.scdLineAddr_01, addrValidApiPMsg.xxVldStsCd_02);
                setAddrValidResult((String) msgIdList.get(0), cMsg.ctyAddr_01, addrValidApiPMsg.xxVldStsCd_03);
                setAddrValidResult((String) msgIdList.get(0), cMsg.stCd_01, addrValidApiPMsg.xxVldStsCd_04);
                setAddrValidResult((String) msgIdList.get(0), cMsg.postCd_01, addrValidApiPMsg.xxVldStsCd_05);
                setAddrValidResult((String) msgIdList.get(0), cMsg.ctryCd_03, addrValidApiPMsg.xxVldStsCd_06);
                setAddrValidResult((String) msgIdList.get(0), cMsg.cntyPk_03, addrValidApiPMsg.xxVldStsCd_07);
                rtrnCd = false;
            }
        }

        // Not Error => replace address data.
        if (rtrnCd) {
            ZYPEZDItemValueSetter.setValue(cMsg.firstLineAddr_01, addrValidApiPMsg.firstLineAddr);
            ZYPEZDItemValueSetter.setValue(cMsg.scdLineAddr_01, addrValidApiPMsg.scdLineAddr);
            ZYPEZDItemValueSetter.setValue(cMsg.ctyAddr_01, addrValidApiPMsg.ctyAddr);
            ZYPEZDItemValueSetter.setValue(cMsg.stCd_01, addrValidApiPMsg.stCd);
            ZYPEZDItemValueSetter.setValue(cMsg.postCd_01, addrValidApiPMsg.postCd);
            ZYPEZDItemValueSetter.setValue(cMsg.ctryCd_03, addrValidApiPMsg.ctryCd);
            ZYPEZDItemValueSetter.setValue(cMsg.cntyPk_03, addrValidApiPMsg.cntyPk);
        }
        return rtrnCd;
    }

    /**
     * <pre>
     * setAddrValidResult
     * </pre>
     * @param msgId String
     * @param checkItem EZDCStringItem
     * @param rtnStsCd EZDPStringItem
     */
    public static void setAddrValidResult(String msgId, EZDCStringItem checkItem, EZDPStringItem rtnStsCd) {
        if (NMZC0030_ERROR.equals(rtnStsCd.getValue())) {
            // Replace Error Message.
            checkItem.clearErrorInfo();
            checkItem.setErrorInfo(1, msgId);
        }
    }

    /**
     * <pre>
     * setAddrValidResult
     * </pre>
     * @param msgId String
     * @param checkItem EZDCBigDecimalItem
     * @param rtnStsCd EZDPStringItem
     */
    public static void setAddrValidResult(String msgId, EZDCBigDecimalItem checkItem, EZDPStringItem rtnStsCd) {
        if (NMZC0030_ERROR.equals(rtnStsCd.getValue())) {
            // Replace Error Message.
            checkItem.clearErrorInfo();
            checkItem.setErrorInfo(1, msgId);
        }
    }
}
