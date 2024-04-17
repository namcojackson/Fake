/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0500.common;

import static business.blap.NSAL0500.constant.NSAL0500Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCItem;
import parts.common.EZDCStringItemArray;
import parts.common.EZDMsg;
import business.blap.NSAL0500.NSAL0500CMsg;
import business.blap.NSAL0500.NSAL0500Query;
import business.blap.NSAL0500.NSAL0500SMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.VND_TP_RELNTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Sub Contract Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/07   Hitachi         T.Aoyagi        Create          N/A
 * 2016/01/06   Hitachi         T.Tsuchida      Update          N/A
 * 2016/01/12   Hitachi         T.Tsuchida      Update          QC#2889
 * 2016/01/13   Hitachi         T.Tsuchida      Update          QC#2820
 * 2016/02/18   Hitachi         K.Kasai         Update          QC#3706
 * 2016/12/14   Hitachi         K.Kojima        Update          QC#15653
 * 2017/03/01   Hitachi         T.Mizuki        Update          QC#17575
 * 2017/05/25   Hitachi         Y.Osawa         Update          QC#18560
 * 2017/12/22   Hitachi         Y.Takeno        Update          QC#22831
 *</pre>
 */
public class NSAL0500CommonLogic {

    /**
     * Check whether the cMsg has an error.
     * @param cMsg NSAL0500CMsg
     * @return boolean true: If cMsg has error. false: otherwise.
     */
    public static boolean isError(NSAL0500CMsg cMsg) {
        // Message line
        if (ERROR.equals(cMsg.getMessageKind())) {
            return true;
        }

        // screen item
        List<EZDCItem> inputItem = getCMsgInputItem(cMsg);
        for (EZDCItem item : inputItem) {
            if (item.isError()) {
                return true;
            }
        }
        return false;
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation_A(NSAL0500CMsg cMsg, NSAL0500SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum_A0.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum_A0.setValue(pageFrom + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum_A0.setValue(sMsg.A.getValidCount());
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation_B(NSAL0500CMsg cMsg, NSAL0500SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.B.length(); i++) {
            if (i < sMsg.B.getValidCount()) {
                EZDMsg.copy(sMsg.B.get(i), null, cMsg.B.get(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.B.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum_B0.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum_B0.setValue(pageFrom + cMsg.B.getValidCount());
        cMsg.xxPageShowOfNum_B0.setValue(sMsg.B.getValidCount());
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation_C(NSAL0500CMsg cMsg, NSAL0500SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.C.length(); i++) {
            if (i < sMsg.C.getValidCount()) {
                EZDMsg.copy(sMsg.C.get(i), null, cMsg.C.get(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.C.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum_C0.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum_C0.setValue(pageFrom + cMsg.C.getValidCount());
        cMsg.xxPageShowOfNum_C0.setValue(sMsg.C.getValidCount());
    }

    /**
     * searchVndNm
     * @param cMsg NSAL0500CMsg
     */
    public static void searchVndNm(NSAL0500CMsg cMsg) {

        if (hasValue(cMsg.vndCd)) {
            String prntVndNm = NSAL0500Query.getInstance().getVnd(cMsg);

            if (hasValue(prntVndNm)) {
                setValue(cMsg.prntVndNm, prntVndNm);
            } else {
                // START 2017/12/22 [QC#22831, MOD]
                cMsg.vndCd.setErrorInfo(1, NSAM0072E, new String[]{"Supplier Site"});
                // END   2017/12/22 [QC#22831, MOD]
            }
            // mod start 2017/03/01 CSA QC#17575
            if (!isServiceDealer(cMsg.glblCmpyCd.getValue(), cMsg.vndCd.getValue())) {
                // START 2017/12/22 [QC#22831, MOD]
                cMsg.vndCd.setErrorInfo(1, NSZM0634E, new String[]{"Supplier Site", "Service Dealer"});
                // END   2017/12/22 [QC#22831, MOD]
            }
            // mod end 2017/03/01 CSA QC#17575
        } else {
            cMsg.prntVndNm.clear();
        }
    }

// mod start 2017/03/01 CSA QC#17575
    /**
     * Is Service Dealer.
     * @param glblCmpyCd
     * @param vndCd
     * @return true:Service Dealer, false:Not Service Dealer
     */
    private static boolean isServiceDealer(String glblCmpyCd, String vndCd) {
        VND_TP_RELNTMsg inMsg = new VND_TP_RELNTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.vndCd, vndCd);
        setValue(inMsg.vndTpCd, VND_TP.SERVICE_DEALER);
        inMsg = (VND_TP_RELNTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (inMsg == null) {
            return false;
        }
        return true;
    }

    /**
     * searchDefTech
     * @param cMsg NSAL0500CMsg
     */
    public static void searchDefTech(NSAL0500CMsg cMsg) {

        // mod start 2016/02/18 CSA Defect#3706
//        if (hasValue(cMsg.vndCd)) {
        if (hasValue(cMsg.vndCd) && !hasValue(cMsg.techTocCd)) {
        // mod end 2016/02/18 CSA Defect#3706
            String techTocCd = NSAL0500Query.getInstance().getDefTech(cMsg);

            if (hasValue(techTocCd)) {
                setValue(cMsg.techTocCd, techTocCd);
            }
        }
    }

    /**
     * searchTech
     * @param cMsg NSAL0500CMsg
     */
    public static void searchTech(NSAL0500CMsg cMsg) {

        if (hasValue(cMsg.techTocCd)) {
            // START 2016/12/14 K.Kojima [QC#15653,MOD]
            // TECH_MSTRTMsg techMstrMsg = NSAL0500Query.getInstance().getTech(cMsg);
            // 
            // if (techMstrMsg == null) {
            String techTocCd = NSAL0500Query.getInstance().getTechMstr(cMsg.glblCmpyCd.getValue(), cMsg.techTocCd.getValue(), cMsg.slsDt.getValue());
            if (techTocCd == null) {
                // END 2016/12/14 K.Kojima [QC#15653,MOD]
                cMsg.techTocCd.setErrorInfo(1, NSAM0072E, new String[]{"Technician Code"});
            }
            // mod start 2017/03/01 CSA QC#17575
            String psnCd = NSAL0500Query.getInstance().getThirdPartyTech(cMsg.glblCmpyCd.getValue(), cMsg.vndCd.getValue(), cMsg.techTocCd.getValue(), cMsg.slsDt.getValue());
            if (psnCd == null) {
                cMsg.techTocCd.setErrorInfo(1, NSZM0634E, new String[] {"Technician", "3rd party technician" });
            }
            // mod end 2017/03/01 CSA QC#17575
        }
    }

    private static List<EZDCItem> getCMsgInputItem(NSAL0500CMsg cMsg) {
        List<EZDCItem> list = new ArrayList<EZDCItem>();
        list.add(cMsg.vndCd);
        list.add(cMsg.techTocCd);
        list.add(cMsg.effFromDt);
        list.add(cMsg.effThruDt);
        list.add(cMsg.svcCmntTxt_AD);
        list.add(cMsg.dlrFleetNum);
        return list;
    }

    /**
     * Create Billing Cycle pull down.
     * @param cMsg NSAL0500CMsg
     */
    public static void createBllgCyclePullDown(NSAL0500CMsg cMsg) {
        ZYPCodeDataUtil.createPulldownList(BLLG_CYCLE.class, cMsg.bllgCycleCd_L0, cMsg.bllgCycleNm_L0);
        // START 2017/05/25 Y.Osawa [QC#18560, ADD]
        deletePulldownList(cMsg.bllgCycleCd_L0, cMsg.bllgCycleNm_L0, BLLG_CYCLE.DAILY);
        // END   2017/05/25 Y.Osawa [QC#18560, ADD]
    }


    // START 2017/05/25 Y.Osawa [QC#18560, ADD]
    /**
     * deletePulldownList
     * @param cdArray EZDCStringItemArray Code Array
     * @param txtArray EZDCStringItemArray Text Array
     * @param delCd delete Code
     */
    public static void deletePulldownList(EZDCStringItemArray cdArray, EZDCStringItemArray txtArray, String delCd) {
        int index = -1;
        for (int i = 0; i < cdArray.length(); i++) {
            if (delCd.equals(cdArray.no(i).getValue())) {
                index = i;
                break;
            }
        }

        if (index >= 0) {
            int i = index;
            for (; i < cdArray.length() - 1; i++) {
                ZYPEZDItemValueSetter.setValue(cdArray.no(i), cdArray.no(i + 1));
                ZYPEZDItemValueSetter.setValue(txtArray.no(i), txtArray.no(i + 1));
            }
            cdArray.no(i).clear();
            txtArray.no(i).clear();
        }
    }
    // END   2017/05/25 Y.Osawa [QC#18560, ADD]
    // Add Start 2018/12/10 K.Fujimoto QC#29079
    public static BigDecimal getDsContrDtlPk(S21SsmEZDResult ssmResult, NSAL0500CMsg cMsg) {
        BigDecimal rtnVal = null;
        String dsContrCatgCd = null;
        String dsContrDtlTpCd = null;
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (resultList.size() > 0) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);
            DS_CONTR_DTLTMsg dsContrDtlTMsg = NSAL0500Query.getInstance().getContrDtl(cMsg);
            if (dsContrDtlTMsg != null && !DS_CONTR_DTL_TP.USAGE_ONLY.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue()) && !DS_CONTR_DTL_TP.BASE_AND_USAGE.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                return null;
            }
            rtnVal = cMsg.dsContrDtlPk.getValue();
            dsContrCatgCd = (String) resultMap.get(DS_CONTR_CATG_CD);
            if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
                dsContrDtlTpCd = DS_CONTR_DTL_TP.FLEET;
                rtnVal = NSAL0500Query.getInstance().getLineDsContrDtlPk(cMsg, dsContrDtlTpCd);
            }
        }
        return rtnVal;
    }
    // Add End   2018/12/10 K.Fujimoto QC#29079
}
