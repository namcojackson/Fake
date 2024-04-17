/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0310;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_CONTRTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CRAT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/26   CUSA            SRAA            Create          N/A
 * 2015/10/08   Hitachi         T.Tomita        Update          N/A
 * 2015/12/03   Hitachi         A.Kohinata      Update          QC501
 * 2016/02/16   Hitachi         A.Kohinata      Update          QC#3017
 * 2016/02/24   Hitachi         T.Kanasaka      Update          QC#4086
 * 2016/09/15   Hitachi         N.Arai          Update          QC#11616
 * 2017/09/04   Hitachi         U.Kim           Update          QC#20856
 * 2018/10/30   Hitachi         K.Kim           Update          QC#28890
 * 2022/01/21   CITS            R.Cabral        Update          QC#59502
 * 2022/02/03   CITS            R.Cabral        Update          QC#59502
 *</pre>
 */
public final class NSAL0310Query extends S21SsmEZDQuerySupport {

    /** Instance */
    private static final NSAL0310Query INSTANCE = new NSAL0310Query();

    private NSAL0310Query() {
    }

    /**
     * Get Instance
     * @return String
     */
    public static NSAL0310Query getInstance() {
        return INSTANCE;
    }

    // START 2015/10/08 T.Tomita [N/A, MOD]
    // START 2016/02/24 T.Kanasaka [QC4086, MOD]
    /**
     * Get Service Machine Master
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @param serNum String
     * @param mdlNm String
     * @param xxComnScrColValTxt String
     * @param xxScrItem29Txt String
     * @param mdseDescShortTxt String
     * @param xxConfigFlg String
     * @param rowNum int
     * @param dsContrPk String
     * @param contrInacFlg String
     * @param dsContrCatgCd String
     * @param fromDate String
     * @param thruDate String
     * @param svcMachMstrPk BigDecimal
     * @param svcConfigMstrPk BigDecimal
     * @return S21SsmEZDResult
     */
    // START 2016/09/15 N.Arai [QC#11616, MOD]
    // public S21SsmEZDResult getSvcMachMstr(String glblCmpyCd, String dsAcctNum, String serNum, String mdlNm, String xxComnScrColValTxt, String xxScrItem29Txt, String mdseNm, String xxConfigFlg, int rowNum, BigDecimal dsContrPk,
    //         String contrInacFlg, String dsContrCatgCd, String fromDate, String thruDate) {
    // START 2022/01/21 R.Cabral [QC#59502, MOD]
//    public S21SsmEZDResult getSvcMachMstr(String glblCmpyCd, String dsAcctNum, String serNum, String mdlNm, String xxComnScrColValTxt, String xxScrItem29Txt, String mdseDescShortTxt, String xxConfigFlg, int rowNum, BigDecimal dsContrPk,
//            String contrInacFlg, String dsContrCatgCd, String fromDate, String thruDate) {
    public S21SsmEZDResult getSvcMachMstr(String glblCmpyCd, String dsAcctNum, String serNum, String mdlNm, String xxComnScrColValTxt, String xxScrItem29Txt, String mdseDescShortTxt, String xxConfigFlg, int rowNum, BigDecimal dsContrPk,
    // START 2022/02/03 R.Cabral [QC#59502, MOD]
//          String contrInacFlg, String dsContrCatgCd, String fromDate, String thruDate, BigDecimal svcMachMstrPk) {
          String contrInacFlg, String dsContrCatgCd, String fromDate, String thruDate, BigDecimal svcMachMstrPk, BigDecimal svcConfigMstrPk) {
    // END   2022/02/03 R.Cabral [QC#59502, MOD]
    // END   2022/01/21 R.Cabral [QC#59502, MOD]
        // END 2016/09/15 N.Arai [QC#11616, MOD]
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsAcctNum", dsAcctNum);
        ssmPrm.put("serNum", serNum);
        ssmPrm.put("mdlNm", mdlNm);
        // [QC#3017,MOD]START
        ssmPrm.put("xxComnScrColValTxt", xxComnScrColValTxt);
        // [QC#3017,MOD]END
        ssmPrm.put("xxScrItem29Txt", xxScrItem29Txt);
        // START 2016/09/15 N.Arai [QC#11616, MOD]
        // ssmPrm.put("mdseNm", mdseNm);
        ssmPrm.put("mdseDescShortTxt", mdseDescShortTxt);
        // END 2016/09/15 N.Arai [QC#11616, MOD]
        ssmPrm.put("xxConfigFlg", xxConfigFlg);
        ssmPrm.put("contrInacFlg", contrInacFlg);
        ssmPrm.put("svcMachTpMach", SVC_MACH_TP.MACHINE);
        ssmPrm.put("rowNum", rowNum);
        ssmPrm.put("dsContrPk", dsContrPk);
        // START 2015/12/03 A.Kohinata [QC501, ADD]
        String[] svcMachMstrStsCdList = new String[] {SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION, SVC_MACH_MSTR_STS.INSTALLED };
        ssmPrm.put("svcMachMstrStsCdList", svcMachMstrStsCdList);
        // END 2015/12/03 A.Kohinata [QC501, ADD]
        if (DS_CONTR_CATG.WARRANTY.equals(dsContrCatgCd)) {
            ssmPrm.put("wtyFlg", FLG_ON_Y);
        } else {
            ssmPrm.put("wtyFlg", FLG_OFF_N);
        }
        ssmPrm.put("warranty", DS_CONTR_CATG.WARRANTY);
        ssmPrm.put("fromDate", fromDate);
        ssmPrm.put("thruDate", thruDate);
        // START 2022/01/21 R.Cabral [QC#59502, ADD]
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        // START 2022/02/03 R.Cabral [QC#59502, MOD]
        if (svcConfigMstrPk != null) {
            ssmPrm.put("svcConfigMstrPk", svcConfigMstrPk);
            ssmPrm.put("accCd", SVC_MACH_TP.ACCESSORY);
        }
        // END   2022/02/03 R.Cabral [QC#59502, MOD]
        // END   2022/01/21 R.Cabral [QC#59502, ADD]
        ssmPrm.put("dsContrDtlStsCd_Cancelled", DS_CONTR_DTL_STS.CANCELLED);
        return getSsmEZDClient().queryObjectList("getSvcMachMstr", ssmPrm);
    }
    // END 2016/02/24 T.Kanasaka [QC4086, MOD]
    // END 2015/10/08 T.Tomita [N/A, MOD]

    /**
     * @param glblCmpyCd String
     * @param shipToCustCd String
     * @return SHIP_TO_CUSTTMsg
     */
    public SHIP_TO_CUSTTMsg getShipToCust(String glblCmpyCd, String shipToCustCd) {
        SHIP_TO_CUSTTMsg prmTMsg = new SHIP_TO_CUSTTMsg();
        prmTMsg.setSQLID("004");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("shipToCustCd01", shipToCustCd);
        SHIP_TO_CUSTTMsgArray tMsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(prmTMsg);
        if (tMsgArray.length() > 0) {
            return tMsgArray.no(0);
        } else {
            return null;
        }
    }

    // START 2015/10/08 T.Tomita [N/A, ADD]
    /**
     * Get DS Contract For PK
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return DS_CONTRTMsg
     */
    public DS_CONTRTMsg getDsContrForPk(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrPk, dsContrPk);

        return (DS_CONTRTMsg) EZDTBLAccessor.findByKey(inMsg);
    }
    // END 2015/10/08 T.Tomita [N/A, MOD]

    // [QC#3017,ADD]START
    /**
     * Get DS Account Name
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return String
     */
    public String getDsAcctNm(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);

        return (String) getSsmEZDClient().queryObject("getDsAcctNm", ssmPrm).getResultObject();
    }
    // [QC#3017,ADD]END

    // START 2017/09/04 U.Kim [QC#20856, ADD]
    public BigDecimal getMainMachine(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal svcConfigMstrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("svcConfigMstrPk", svcConfigMstrPk);
        ssmPrm.put("svcMachTpCd", SVC_MACH_TP.MACHINE);

        S21SsmEZDResult result = getSsmEZDClient().queryObject("getMainMachine", ssmPrm);
        if (result == null || !result.isCodeNormal()) {
            return BigDecimal.ZERO;
        } else {
            return (BigDecimal) result.getResultObject();
        }
    }
    // END 2017/09/04 U.Kim [QC#20856, ADD]

    // START 2018/10/30 [QC#28890, ADD]
    /**
     * Get DS Account Name
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @return String
     */
    public String getDsAcct(String glblCmpyCd, String dsAcctNum) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsAcctNum", dsAcctNum);
        S21SsmEZDResult dsAcct = getSsmEZDClient().queryObject("getDsAcct", ssmPrm);
        if (dsAcct.isCodeNormal()) {
            return (String) dsAcct.getResultObject();
        } else {
            return null;
        }
    }
    // END 2018/10/30 [QC#28890, ADD]
    // START 2022/01/21 R.Cabral [QC#59502, ADD]
    /**
     * Get DS_CONTR_DTL info of machines
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrDtlMachInfo(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachTpCd", SVC_MACH_TP.MACHINE);
        ssmPrm.put("dsContrPk", dsContrPk);
        // START 2022/02/03 R.Cabral [QC#59502, MOD]
        ssmPrm.put("svcPrcFlg", ZYPConstant.FLG_OFF_N);
        ssmPrm.put("dsContrCratTp", DS_CONTR_CRAT_TP.OTHER);
        // END   2022/02/03 R.Cabral [QC#59502, MOD]
        S21SsmEZDResult result = getSsmEZDClient().queryObjectList("getDsContrDtlMachInfo", ssmPrm);
        if (result == null || !result.isCodeNormal()) {
            return null;
        } else {
            return result;
        }
    }

    /**
     * Get DS_CONTR_DTL info of accessories
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param dsContrDtlPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrDtlAccInfo(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.ACCESSORIES);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        S21SsmEZDResult result = getSsmEZDClient().queryObjectList("getDsContrDtlAccInfo", ssmPrm);
        if (result == null || !result.isCodeNormal()) {
            return null;
        } else {
            return result;
        }
    }
    // START 2022/02/03 R.Cabral [QC#59502, DEL]
//    /**
//     * Get DS_CONTR_DTL info of machines
//     * @param glblCmpyCd String
//     * @param svcConfigMstrPk BigDecimal
//     * @param svcMachMstrPkArray BigDecimal[]
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getMachAccInfo(String glblCmpyCd, BigDecimal svcConfigMstrPk, BigDecimal[] svcMachMstrPkArray) {
//        Map<String, Object> ssmPrm = new HashMap<String, Object>();
//        ssmPrm.put("glblCmpyCd", glblCmpyCd);
//        ssmPrm.put("svcConfigMstrPk", svcConfigMstrPk);
//        if (svcMachMstrPkArray != null && svcMachMstrPkArray.length > 0) {
//            ssmPrm.put("svcMachMstrPkList", svcMachMstrPkArray);
//        }
//        S21SsmEZDResult result = getSsmEZDClient().queryObjectList("getMachAccInfo", ssmPrm);
//        if (result == null || !result.isCodeNormal()) {
//            return null;
//        } else {
//            return result;
//        }
//    }
    // END   2022/02/03 R.Cabral [QC#59502, DEL]
    // END   2022/01/21 R.Cabral [QC#59502, ADD]
}
