/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0990;

import static business.blap.NSAL0990.constant.NSAL0990Constant.GET_BLLG_SCHD_NUM;
import static business.blap.NSAL0990.constant.NSAL0990Constant.MDL_MTR_TP_BW;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0990.constant.NSAL0990Constant;
import business.db.FRT_CONDTMsg;
import business.db.MDL_NMTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.SVC_COV_FEAT;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Supply Order
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/06   Hitachi         K.Kasai         Create          N/A
 * 2016/03/08   Hitachi         A.Kohinata      Update          QC#5143
 * 2016/03/09   Hitachi         A.Kohinata      Update          QC#5196
 * 2016/03/11   Hitachi         A.Kohinata      Update          QC#5354
 * 2016/03/31   Hitachi         K.Kasai         Update          QC#6344
 * 2016/09/21   Hitachi         N.Arai          Update          QC#11616
 * 2016/10/04   Hitachi         A.Kohinata      Update          QC#12898
 * 2016/10/19   Hitachi         A.Kohinata      Update          QC#15344
 * 2016/10/25   Hitachi         A.Kohinata      Update          QC#15338
 * 2017/01/06   Hitachi         K.Ochiai        Update          QC#16012
 * 2017/05/09   Hitachi         K.Kitachi       Update          QC#18277
 * 2017/07/03   Hitachi         K.Kitachi       Update          QC#19026
 * 2017/11/22   Hitachi         M.Kidokoro      Update          QC#20497
 * 2017/12/26   Fujitsu         K.Ishizuka      Update          QC#20164(Sol#356)
 * 2018/01/26   Hitachi         K.Kojima        Update          QC#22797
 * 2018/04/11   Hitachi         K.Kitachi       Update          QC#11642
 * 2018/05/25   CITS            M.Naito         Update          QC#15410
 * 2018/07/03   Hitachi         K.Kitachi       Update          QC#26924
 * 2018/07/04   CITS            T.Wada          Update          QC#23726
 * 2018/09/10   Hitachi         K.Kitachi       Update          QC#26260
 * 2018/09/20   CITS            T.Wada          Update          QC#28333
 * 2018/11/15   Fujitsu         M.Yamada        Update          QC#29191
 * 2018/11/21   Fujitsu         M.Yamada        Update          QC#29302
 * 2019/01/21   Hitachi         A.Kohinata      Update          QC#27304
 * 2019/06/17   Fujitsu         W.Honda         Update          QC#50842
 * 2023/06/07   CITS            M.Okamura       Update          QC#61570
 * 2023/07/14   CITS            T.Kimura        Update          QC#61570
 *</pre>
 */
public final class NSAL0990Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0990Query INSTANCE = new NSAL0990Query();

    /**
     * Constructor.
     */
    private NSAL0990Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0990Query
     */
    public static NSAL0990Query getInstance() {
        return INSTANCE;
    }

    /**
     * get SvcMachMstr
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcMachMstr(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        return getSsmEZDClient().queryObjectList("getSvcMachMstr", ssmPrm);
    }

    /**
     * get DsContr
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContr(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        return getSsmEZDClient().queryObjectList("getDsContr", ssmPrm);
    }

    /**
     * get OrderHistory
     * @param glblCmpyCd String
     * @param slsDt String
     * @param svcConfigMstrPk BigDecimal
     * @param period String
     * @param dsOrdCatgCd String
     * @param dsContrNum String
     * @return S21SsmEZDResult
     */
    // mod start 2016/10/04 CSA Defect#12898
    public S21SsmEZDResult getOrderHistory(String glblCmpyCd, String slsDt, BigDecimal svcConfigMstrPk, String period, String dsOrdCatgCd, String dsContrNum) {
    // mod end 2016/10/04 CSA Defect#12898
        if (svcConfigMstrPk == null) {
            return null;
        }
        String targetDt = null;
        if (period.length() > 0) {
            int yyyy = Integer.parseInt(slsDt.substring(0, NSAL0990Constant.NUM_FOUR));
            int mm = Integer.parseInt(slsDt.substring(NSAL0990Constant.NUM_FOUR, NSAL0990Constant.NUM_SIX)) - 1;
            int dd = Integer.parseInt(slsDt.substring(NSAL0990Constant.NUM_SIX, NSAL0990Constant.NUM_EIGHT));
            Calendar cal = Calendar.getInstance();
            cal.set(yyyy, mm, dd);
            cal.add(Calendar.DAY_OF_MONTH, -Integer.valueOf(period));
            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
            targetDt = fmt.format(cal.getTime());
        }
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("targetDt", targetDt);
        ssmPrm.put("svcConfigMstrPk", svcConfigMstrPk);
        ssmPrm.put("ordLineStsCd", ORD_LINE_STS.CANCELLED);
        ssmPrm.put("dsOrdCatgCd", dsOrdCatgCd);
        // add start 2016/10/04 CSA Defect#12898
        ssmPrm.put("dsContrNum", dsContrNum);
        // add end 2016/10/04 CSA Defect#12898
        return getSsmEZDClient().queryObjectList("getOrderHistory", ssmPrm);
    }

    // START 2017/05/09 K.Kitachi [QC#18277, MOD]
    /**
     * get LineDetailForSupplyOrder
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @param mdlId BigDecimal
     * @param slsDt String
     * @param length int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLineDetailForSupplyOrder(String glblCmpyCd, BigDecimal svcMachMstrPk, BigDecimal mdlId, String slsDt, int length) {
        if (mdlId == null) {
            return null;
        }
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        // del start 2016/10/04 CSA Defect#12898
        //ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        //ssmPrm.put("frzFlg", ZYPConstant.FLG_OFF_N);
        //ssmPrm.put("colorTpBl", IMG_SPLY_COLOR_TP.BLACK);
        // del end 2016/10/04 CSA Defect#12898
        ssmPrm.put("mdlId", mdlId);
        ssmPrm.put("pkgUomCd", PKG_UOM.EACH);
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("rowNum", length + 1);
        return getSsmEZDClient().queryObjectList("getLineDetailForSupplyOrder", ssmPrm);
    }
    // END 2017/05/09 K.Kitachi [QC#18277, MOD]

    /**
     * get AddlLineDetail
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param svcMachMstrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAddlLineDetail(String glblCmpyCd, String mdseCd, BigDecimal svcMachMstrPk) {
        if (mdseCd == null) {
            return null;
        }
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        // del start 2016/10/04 CSA Defect#12898
        //ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        //ssmPrm.put("frzFlg", ZYPConstant.FLG_OFF_N);
        //ssmPrm.put("colorTpBl", IMG_SPLY_COLOR_TP.BLACK);
        // del end 2016/10/04 CSA Defect#12898
        ssmPrm.put("mdseCd", mdseCd);
        ssmPrm.put("pkgUomCd", PKG_UOM.EACH);
        return getSsmEZDClient().queryObjectList("getAddlLineDetail", ssmPrm);
    }

    /**
     * get SvcTermAttrbMapValCd
     * @param glblCmpyCd String
     * @param slsDt String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrPk BigDecimal
     * @param attrbNm String
     * @return String
     */
    public String getSvcTermAttrbMapValCd(String glblCmpyCd, String slsDt, BigDecimal dsContrDtlPk, BigDecimal dsContrPk, String attrbNm) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("svcTermCondAttrbNm", attrbNm);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        // add start 2016/10/04 CSA Defect#12898
        ssmPrm.put("dsContrPk", dsContrPk);
        // add end 2016/10/04 CSA Defect#12898
        return (String) getSsmEZDClient().queryObject("getSvcTermAttrbMapValCd", ssmPrm).getResultObject();
    }

    /**
     * get SvcTermCondDataDispTxt
     * @param glblCmpyCd String
     * @param slsDt String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrPk BigDecimal
     * @param attrbNm String
     * @param dsContrCatgCd String
     * @return String
     */
    // mod start 2016/10/04 CSA Defect#12898
    public String getSvcTermCondDataDispTxt(String glblCmpyCd, String slsDt, BigDecimal dsContrDtlPk, BigDecimal dsContrPk, String attrbNm, String dsContrCatgCd) {
    // mod end 2016/10/04 CSA Defect#12898
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("svcTermCondAttrbNm", attrbNm);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("dsContrPk", dsContrPk);
        // add start 2016/10/04 CSA Defect#12898
        String fltFlg = ZYPConstant.FLG_OFF_N;
        if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            fltFlg = ZYPConstant.FLG_ON_Y;
        }
        ssmPrm.put("fltFlg", fltFlg);
        // add end 2016/10/04 CSA Defect#12898
        return (String) getSsmEZDClient().queryObject("getSvcTermCondDataDispTxt", ssmPrm).getResultObject();
    }

    /**
     * check CarrShpgSvcLvlAndShpgSvcLvl
     * @param bizMsg NSAL0990CMsg
     * @return BigDecimal
     */
    public BigDecimal checkCarrShpgSvcLvlAndShpgSvcLvl(NSAL0990CMsg bizMsg) {
        Map<String, String> ssmPrm = new HashMap<String, String>();
        ssmPrm.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmPrm.put("shpgSvcLvlCd", bizMsg.shpgSvcLvlCd.getValue());
        ssmPrm.put("carrSvcLvlCd", bizMsg.carrSvcLvlCd.getValue());
        return (BigDecimal) getSsmEZDClient().queryObject("checkCarrShpgSvcLvlAndShpgSvcLvl", ssmPrm).getResultObject();
    }

    /**
     * check POAndBillTo
     * @param bizMsg NSAL0990CMsg
     * @return BigDecimal
     */
    public BigDecimal checkPOAndBillTo(NSAL0990CMsg bizMsg) {
        Map<String, String> ssmPrm = new HashMap<String, String>();
        ssmPrm.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmPrm.put("billToCust", bizMsg.billToLocNum.getValue());
        ssmPrm.put("orgCatgCtxTpCdIsSupplyOrder", "SUPPLIES_ORDER");
        // add start 2016/10/25 CSA Defect#15338
        ssmPrm.put("dsOrdCatgCd", bizMsg.dsOrdCatgCd.getValue());
        // add end 2016/10/25 CSA Defect#15338
        // START 2019/06/17 W.HOnda [QC#50842, ADD]
        ssmPrm.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        // END 2017/06/17 W.Honda [QC#50842, ADD]
        return (BigDecimal) getSsmEZDClient().queryObject("checkPOAndBillTo", ssmPrm).getResultObject();
    }

    /**
     * check POAndAccount
     * @param bizMsg NSAL0990CMsg
     * @return BigDecimal
     */
    public BigDecimal checkPOAndAccount(NSAL0990CMsg bizMsg) {
        Map<String, String> ssmPrm = new HashMap<String, String>();
        ssmPrm.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmPrm.put("billToAcctNum", bizMsg.billToAcctNum.getValue());
        ssmPrm.put("orgCatgCtxTpCdIsSupplyOrder", "SUPPLIES_ORDER");
        // add start 2016/10/25 CSA Defect#15338
        ssmPrm.put("dsOrdCatgCd", bizMsg.dsOrdCatgCd.getValue());
        // add end 2016/10/25 CSA Defect#15338
        // START 2019/06/17 W.HOnda [QC#50842, ADD]
        ssmPrm.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        // END 2017/06/17 W.Honda [QC#50842, ADD]
        return (BigDecimal) getSsmEZDClient().queryObject("checkPOAndAccount", ssmPrm).getResultObject();
    }

    /**
     * check FrtCondRelation
     * @param bizMsg NSAL0990CMsg
     * @return BigDecimal
     */
    public BigDecimal checkFrtCondRelation(NSAL0990CMsg bizMsg) {
        Map<String, String> ssmPrm = new HashMap<String, String>();
        ssmPrm.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        // del start 2016/10/19 CSA Defect#15344
        //ssmPrm.put("lineBizTpCd", bizMsg.svcByLineBizTpCd.getValue());
        // del end 2016/10/19 CSA Defect#15344
        ssmPrm.put("frtCondCd", bizMsg.frtCondCd.getValue());
        ssmPrm.put("shpgSvcLvlCd", bizMsg.shpgSvcLvlCd.getValue());
        ssmPrm.put("carrSvcLvlCd", bizMsg.carrSvcLvlCd.getValue());
        // add start 2016/10/19 CSA Defect#15344
        ssmPrm.put("slsDt", bizMsg.slsDt.getValue());
        ssmPrm.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        // add end 2016/10/19 CSA Defect#15344
        return (BigDecimal) getSsmEZDClient().queryObject("checkFrtCondRelation", ssmPrm).getResultObject();
    }

    /**
     * get BillToInfo
     * @param glblCmpyCd String
     * @param billToLocNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBillToInfo(String glblCmpyCd, String billToLocNum) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("billToCustCd", billToLocNum);
        return getSsmEZDClient().queryObjectList("getBillToInfo", ssmPrm);
    }

    /**
     * get ShipToInfo
     * @param bizMsg NSAL0990CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipToInfo(NSAL0990CMsg bizMsg) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmPrm.put("shipToCustCd", bizMsg.curLocNum.getValue());
        return getSsmEZDClient().queryObjectList("getShipToInfo", ssmPrm);
    }

    /**
     * get SvcSplyMisc
     * @param glblCmpyCd String
     * @param slsDt String
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public String getSvcSplyMisc(String glblCmpyCd, String slsDt, String mdseCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("mdseCd", mdseCd);
        ssmPrm.put("actvFlg", ZYPConstant.FLG_ON_Y);
        return (String) getSsmEZDClient().queryObject("getSvcSplyMisc", ssmPrm).getResultObject();
    }

    /**
     * get TOC
     * @param glblCmpyCd String
     * @param prcBaseDt String
     * @param slsRepOrSlsTeamTocCd String
     * @return Map<String, Object>
     */
    public Map<String, Object> getToc(String glblCmpyCd, String prcBaseDt, String slsRepOrSlsTeamTocCd) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd", glblCmpyCd);
        mapParam.put("prcBaseDt", prcBaseDt);
        mapParam.put("slsRepOrSlsTeamTocCd", slsRepOrSlsTeamTocCd);
        List<Map<String, Object>> ssmResList = (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("selectToc", mapParam).getResultObject();
        if (ssmResList == null || ssmResList.isEmpty()) {
            return null;
        }
        return ssmResList.get(0);
    }

    /**
     * find Freight Condition
     * @param bizMsg NSAL0990CMsg
     * @return FRT_CONDTMsg
     */
    public FRT_CONDTMsg findFrtCond(NSAL0990CMsg bizMsg) {
        FRT_CONDTMsg tMsg = new FRT_CONDTMsg();
        S21SsmEZDResult ssmRslt = checkFrtCondTxt(bizMsg);
        if (ssmRslt.isCodeNormal()) {
            List<Map<String, String>> rsltMapList = (List<Map<String, String>>) ssmRslt.getResultObject();
            setValue(bizMsg.frtCondCd, rsltMapList.get(0).get("FRT_COND_CD"));
        }
        if (hasValue(bizMsg.frtCondCd)) {
            tMsg = findFrtCondByKey(bizMsg);
        }
        return tMsg;
    }

    /**
     * find DS Payment Method
     * @param bizMsg NSAL0990CMsg
     * @return String
     */
    public String findDsPmtMeth(NSAL0990CMsg bizMsg) {
        S21SsmEZDResult ssmRslt = checkPmtMethDescTxt(bizMsg);
        if (ssmRslt.isCodeNormal()) {
            List<Map<String, String>> rsltMapList = (List<Map<String, String>>) ssmRslt.getResultObject();
            return (String) rsltMapList.get(0).get("DS_PMT_METH_CD");
        }
        return null;
    }

    /**
     * find Freight Condition By Key
     * @param bizMsg NSAL0990CMsg
     * @return FRT_CONDTMsg
     */
    private FRT_CONDTMsg findFrtCondByKey(NSAL0990CMsg bizMsg) {
        FRT_CONDTMsg tMsg = new FRT_CONDTMsg();
        setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        setValue(tMsg.frtCondCd, bizMsg.frtCondCd);
        return (FRT_CONDTMsg) S21CacheTBLAccessor.findByKey(tMsg);
    }

    /**
     * check Freight Condition Description Text
     * @param bizMsg NSAL0990CMsg
     * @return SsmEZDClient
     */
    private S21SsmEZDResult checkFrtCondTxt(NSAL0990CMsg bizMsg) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("descTxt", bizMsg.frtCondDescTxt.getValue());
        queryParam.put("tblNm", "FRT_COND");
        return getSsmEZDClient().queryObjectList("checkCodeTableDescText", queryParam);
    }

    /**
     * check DS Payment Method Description Text
     * @param bizMsg NSAL0990CMsg
     * @return SsmEZDClient
     */
    private S21SsmEZDResult checkPmtMethDescTxt(NSAL0990CMsg bizMsg) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("descTxt", bizMsg.dsPmtMethCd.getValue());
        queryParam.put("tblNm", "DS_PMT_METH");
        return getSsmEZDClient().queryObjectList("checkCodeTableDescText", queryParam);
    }

    /**
     * get CarrSvcLvl
     * @param bizMsg NSAL0990CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkCarrSvcLvl(NSAL0990CMsg bizMsg) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", bizMsg.glblCmpyCd);
        ssmPrm.put("frtCondCd", bizMsg.frtCondCd);
        ssmPrm.put("shpgSvcLvlCd", bizMsg.shpgSvcLvlCd);
        ssmPrm.put("dsOrdTpCd", bizMsg.dsOrdTpCd);
        ssmPrm.put("carrSvcLvlDescTxt", bizMsg.carrSvcLvlDescTxt);
        return getSsmEZDClient().queryObjectList("checkCarrSvcLvl", ssmPrm);
    }

    /**
     * get Freight Term Information List
     * @param bizMsg NSAL0990CMsg
     * @return Freight Term Information List
     */
    public S21SsmEZDResult getFreightTermInfoList(NSAL0990CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("frtCondDescTxt", bizMsg.frtCondDescTxt.getValue());
//        params.put("lineBizTpCd", bizMsg.svcByLineBizTpCd.getValue()); // QC#29191
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue()); // QC#29191
        return getSsmEZDClient().queryObjectList("getFreightTermInfoList", params);
    }

    /**
     * get Service Level Pulldown Data
     * @param glblCmpyCd Global Company Code
     * @param frtCondCd Freight Condition COde (Option)
     * @param dsOrdTpCd DS Order Type Code
     * @return S21SsmEZDResult
     */
    //    public S21SsmEZDResult getShpgSvcLvlDataList(String glblCmpyCd, String lineBizTpCd, String frtCondCd, String dsOrdTpCd) { // QC#29191
    public S21SsmEZDResult getShpgSvcLvlDataList(String glblCmpyCd, String frtCondCd, String dsOrdTpCd) { // QC#29191

        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        //        params.put("lineBizTpCd", lineBizTpCd); // QC#29191
        if (ZYPCommonFunc.hasValue(frtCondCd)) {
            params.put("frtCondCd", frtCondCd);
        } else {
            params.remove("frtCondCd");
        }
        params.put("dsOrdTpCd", dsOrdTpCd); // QC#29191
        return getSsmEZDClient().queryObjectList("getShpgSvcLvlDataList", params);
    }

    /**
     * get Carrier Service Level Information List
     * @param bizMsg NSAL0990CMsg
     * @return Carrier Service Level Information List
     */
    public S21SsmEZDResult getCarrSvcLvlInfoList(NSAL0990CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        params.put("frtCondCd", bizMsg.frtCondCd.getValue());
        params.put("carrSvcLvlDescTxt", bizMsg.carrSvcLvlDescTxt.getValue());
        params.put("shpgSvcLvlCd", bizMsg.shpgSvcLvlCd.getValue());
        return getSsmEZDClient().queryObjectList("getCarrSvcLvlInfoList", params);
    }

    // add start 2016/10/04 CSA Defect#12898
    /**
     * getMdlNm
     * @param glblCmpyCd String
     * @param mdlId BigDecimal
     * @return MDL_NMTMsg
     */
    public MDL_NMTMsg getMdlNm(String glblCmpyCd, BigDecimal mdlId) {
        MDL_NMTMsg tMsg = new MDL_NMTMsg();
        setValue(tMsg.t_GlblCmpyCd, glblCmpyCd);
        setValue(tMsg.t_MdlId, mdlId);
        return (MDL_NMTMsg) EZDTBLAccessor.findByKey(tMsg);
    }

    /**
     * get Fleet Line Info
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getFltLineInfo(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("dsContrDtlTpFleet", DS_CONTR_DTL_TP.FLEET);
        return getSsmEZDClient().queryObjectList("getFltLineInfo", ssmPrm);
    }

    // START 2017/05/09 K.Kitachi [QC#18277, MOD]
    /**
     * get Fleet LineDetail For SupplyOrder
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param slsDt String
     * @param length int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getFltLineDetailForSupplyOrder(String glblCmpyCd, BigDecimal dsContrPk, String slsDt, int length) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("svcMachTpMachine", SVC_MACH_TP.MACHINE);
        ssmPrm.put("pkgUomCd", PKG_UOM.EACH);
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("rowNum", length + 1);
        return getSsmEZDClient().queryObjectList("getFltLineDetailForSupplyOrder", ssmPrm);
    }
    // END 2017/05/09 K.Kitachi [QC#18277, MOD]

    /**
     * get DsContractDetail For Fleet Machine
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param slsDt String
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getDsContrDtlForFltMach(String glblCmpyCd, BigDecimal dsContrPk, String slsDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("svcMachTpMachine", SVC_MACH_TP.MACHINE);
        ssmPrm.put("slsDt", slsDt);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getDsContrDtlForFltMach", ssmPrm).getResultObject();
    }

    /**
     * get Supply Mdse Info
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @param colorTp String
     * @return S21SsmEZDResult
     */
    public Map<String, Object> getSupplyMdseInfo(String glblCmpyCd, BigDecimal svcMachMstrPk, String colorTp) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        ssmPrm.put("imgSplyColorTpCd", colorTp);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getSupplyMdseInfo", ssmPrm).getResultObject();
    }

    /**
     * get Start Meter Info
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param mtrTp String
     * @return S21SsmEZDResult
     */
    public Map<String, Object> getStartMtrInfo(String glblCmpyCd, BigDecimal dsContrDtlPk, String mtrTp) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        if (mtrTp.equals(MDL_MTR_TP_BW)) {
            ssmPrm.put("bwMtrflgY", ZYPConstant.FLG_ON_Y);
        } else {
            ssmPrm.put("colorMtrflgY", ZYPConstant.FLG_ON_Y);
        }
        return (Map<String, Object>) getSsmEZDClient().queryObject("getStartMtrInfo", ssmPrm).getResultObject();
    }

    /**
     * get Billing Meter Info
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param mtrTp String
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getBllgMtrList(String glblCmpyCd, BigDecimal dsContrDtlPk, String mtrTp) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        if (mtrTp.equals(MDL_MTR_TP_BW)) {
            ssmPrm.put("bwMtrflgY", ZYPConstant.FLG_ON_Y);
        } else {
            ssmPrm.put("colorMtrflgY", ZYPConstant.FLG_ON_Y);
        }
        ssmPrm.put("rowNum", GET_BLLG_SCHD_NUM);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getBllgMtrList", ssmPrm).getResultObject();
    }
    // add end 2016/10/04 CSA Defect#12898

    // add start 2017/01/06 CSA Defect#16012
    public S21SsmEZDResult getRevenueCost(NSAL0990CMsg cMsg, String targetYr, String targetTwoYrAgo) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmPrm.put("targetTwoYrAgo", targetTwoYrAgo);
        ssmPrm.put("targetYr", targetYr);
        ssmPrm.put("dsContrPk", cMsg.dsContrPk.getValue());
        if (!DS_CONTR_CATG.FLEET.equals(cMsg.dsContrCatgCd.getValue())) {
            ssmPrm.put("svcMachMstrPk", cMsg.svcMachMstrPk.getValue());
        }
        return getSsmEZDClient().queryObjectList("getRevenueCost", ssmPrm);
    }
    // add end 2017/01/06 CSA Defect#16012

    // START 2017/07/03 K.Kitachi [QC#19026, ADD]
    /**
     * isExistsSplyReln
     * @param cMsg cMsg
     * @param mdseCd String
     * @return boolean
     */
    // START 2018/01/26 K.Kojima [QC#22797,MOD]
    // public boolean isTonerExistsSplyReln(String glblCmpyCd, String mdseCd) {
    // START 2018/07/03 K.Kitachi [QC#26924, MOD]
//    public boolean isTonerExistsSplyReln(String glblCmpyCd, String mdseCd, String svcTermCondDataDispTxt_02) {
//    public boolean isExistsSplyReln(String glblCmpyCd, String mdseCd) {
    // START 2018/9/19 T.Wada [QC#28333 MOD]
//    public boolean isExistsSplyReln(String glblCmpyCd, String mdseCd, BigDecimal mdlId) { // QC#29302
    // END 2018/9/19 T.Wada [QC#28333 MOD]
    // END 2018/07/03 K.Kitachi [QC#26924, MOD]
    // END 2018/01/26 K.Kojima [QC#22797,MOD]
    public boolean isExistsSplyReln(NSAL0990CMsg cMsg, String mdseCd, BigDecimal mdlId) { // QC#29302
        // QC#29302
        if (S21StringUtil.isEquals(ZYPConstant.FLG_OFF_N, cMsg.splyInclFlg.getValue())) {
            return false;
        }
//        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, cMsg.laserPlusFlg.getValue())) { // no need
//            return false;
//        }

        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", cMsg.glblCmpyCd); // QC#29302
        ssmPrm.put("mdseCd", mdseCd);

        // START 2018/9/19 T.Wada [QC#28333 ADD]
        if (ZYPCommonFunc.hasValue(mdlId)) {
            ssmPrm.put("mdlId", mdlId);
        }
        // END 2018/9/19 T.Wada [QC#28333 ADD]

        // START 2018/01/26 K.Kojima [QC#22797,MOD]
        // ssmPrm.put("imgSplyTpCd", IMG_SPLY_TP.TONER);
        // START 2018/07/03 K.Kitachi [QC#26924, DEL]
//        List<String> imgSplyTpCd = new ArrayList<String>(2);
//        imgSplyTpCd.add(IMG_SPLY_TP.TONER);
//        if (hasValue(svcTermCondDataDispTxt_02) && "Yes".equals(svcTermCondDataDispTxt_02)) {
//            imgSplyTpCd.add(IMG_SPLY_TP.STAPLE);
//        }
//        ssmPrm.put("imgSplyTpCd", imgSplyTpCd.toArray(new String[imgSplyTpCd.size()]));
        // END 2018/07/03 K.Kitachi [QC#26924, DEL]
        // END 2018/01/26 K.Kojima [QC#22797,MOD]
        // START 2018/07/03 K.Kitachi [QC#26924, MOD]
//        BigDecimal count = (BigDecimal) getSsmEZDClient().queryObject("isTonerExistsSplyReln", ssmPrm).getResultObject();
        BigDecimal count = (BigDecimal) getSsmEZDClient().queryObject("isExistsSplyReln", ssmPrm).getResultObject();
        // END 2018/07/03 K.Kitachi [QC#26924, MOD]
        if (count.compareTo(BigDecimal.ZERO) > 0) {
            return true;
        }
        // START 2018/07/03 K.Kitachi [QC#26924, MOD]
//        count = (BigDecimal) getSsmEZDClient().queryObject("isTonerExistsSplyRelnForOrdTakeMdse", ssmPrm).getResultObject();
        count = (BigDecimal) getSsmEZDClient().queryObject("isExistsSplyRelnForOrdTakeMdse", ssmPrm).getResultObject();
        // END 2018/07/03 K.Kitachi [QC#26924, MOD]
        if (count.compareTo(BigDecimal.ZERO) > 0) {
            return true;
        }
        return false;
    }
    // END 2017/07/03 K.Kitachi [QC#19026, ADD]

    // START 2017/11/22 M.Kidokoro [QC#20497, ADD]
    /**
     * get Default Freight Term Information
     * @param bizMsg NSAL0990CMsg
     * @return Freight Term Information
     */
    public S21SsmEZDResult getDefaultFreightTermInfo(NSAL0990CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
//        params.put("lineBizTpCd", bizMsg.svcByLineBizTpCd.getValue()); // QC#29191
        return getSsmEZDClient().queryObject("getDefaultFreightTermInfo", params);
    }
    // END 2017/11/22 M.Kidokoro [QC#20497, ADD]

    // START 2017/12/26 K.Ishizuka [QC#20164, ADD]
    /**
     * get Shipping Label
     * @param bizMsg NSAL0990CMsg
     * @return Shipping Label 
     */
    public S21SsmEZDResult getShpgLbl(NSAL0990CMsg bizMsg){

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("svcMachMstrPk", bizMsg.svcMachMstrPk.getValue());
        params.put("svcCtacTpCd", SVC_CTAC_TP.DELIVERY_CONTACT);

        return getSsmEZDClient().queryObjectList("getShpgLbl", params);
    }
    // END 2017/12/26 K.Ishizuka [QC#20164, ADD]

    // START 2018/04/11 K.Kitachi [QC#11642, ADD]
    /**
     * getShipToCust
     * @param glblCmpyCd String
     * @param shipToCustCd String
     * @return SHIP_TO_CUSTTMsg
     */
    public SHIP_TO_CUSTTMsg getShipToCust(String glblCmpyCd, String shipToCustCd) {
        SHIP_TO_CUSTTMsg inMsg = new SHIP_TO_CUSTTMsg();
        inMsg.setSQLID("004");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("shipToCustCd01", shipToCustCd);
        SHIP_TO_CUSTTMsgArray outMsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outMsgArray == null || outMsgArray.getValidCount() == 0) {
            return null;
        }
        return outMsgArray.no(0);
    }
    // END 2018/04/11 K.Kitachi [QC#11642, ADD]

    // START 2018/05/25 M.Naito [QC#15410, ADD]
    /**
     * get Contract Detail List by Machine Master
     * @param bizMsg NSAL0990CMsg
     * @return Contract Detail List
     */
    public S21SsmEZDResult getContrDtlKeysByMachMstrPk(NSAL0990CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("svcMachMstrPk", bizMsg.svcMachMstrPk.getValue());
        // START 2023/07/14 T.Kimura  [QC#61570, DEL]
        //params.put("dsContrDtlStsCanc", DS_CONTR_DTL_STS.CANCELLED);
        // START 2023/06/07 M.Okamura [QC#61570, ADD]
        // params.put("dsContrDtlStsTrmd", DS_CONTR_DTL_STS.TERMINATED);
        // params.put("dsContrDtlStsExpd", DS_CONTR_DTL_STS.EXPIRED);
        // END 2023/06/07 M.Okamura [QC#61570, ADD]
        // END 2023/07/14 T.Kimura  [QC#61570, DEL]
        // START 2023/07/14 T.Kimura  [QC#61570, ADD]
        params.put("ettlAvalFlg", ZYPConstant.FLG_ON_Y);
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("maxEffThruDt", NSAL0990Constant.MAX_EFF_THRU_DT);
        params.put("covFeatCd", SVC_COV_FEAT.SPLY_INCL);
        params.put("covDtlValTxt", ZYPConstant.FLG_ON_Y);
        // END 2023/07/14 T.Kimura  [QC#61570, ADD]

        return getSsmEZDClient().queryObjectList("getContrDtlKeysByMachMstrPk", params);
    }
    // END 2018/05/25 M.Naito [QC#15410, ADD]
    // QC#23726 Add Start
    public S21SsmEZDResult getCarrSvcLvl(NSAL0990CMsg bizMsg, String carrCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("shpgSvcLvlCd", bizMsg.shpgSvcLvlCd.getValue());
        params.put("carrCd", carrCd);

        return getSsmEZDClient().queryObject("getCarrSvcLvl", params);
    }
    public String getCarrCd(NSAL0990CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("carrSvcLvlCd", bizMsg.carrSvcLvlCd.getValue());
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getCarrCd", params);
        if (ssmResult.isCodeNormal()) {
            return (String) ssmResult.getResultObject();
        } else {
            return null;
        }
    }
    // QC#23726 Add End

    // START 2018/09/10 K.Kitachi [QC#26260, ADD]
    public String convLineBizTpToSvcLineBiz(String glblCmpyCd, String lineBizTpCd) {
        if (!hasValue(lineBizTpCd)) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("lineBizTpCd", lineBizTpCd);
        return (String) getSsmEZDClient().queryObject("convLineBizTpToSvcLineBiz", param).getResultObject();
    }
    // END 2018/09/10 K.Kitachi [QC#26260, ADD]

    // add start 2019/01/21 QC#27304
    /**
     * getPsnNm
     * @param glblCmpyCd String
     * @param userId String
     * @return PsnNm
     */
    public String getPsnNm(String glblCmpyCd, String userId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("userId", userId);
        return (String) getSsmEZDClient().queryObject("getPsnNm", param).getResultObject();
    }

    /**
     * getSvcSplyOrdDtlPk
     * @param glblCmpyCd String
     * @param svcSplyOrdPk BigDecimal
     * @return SvcSplyOrdDtlPk List
     */
    public List<BigDecimal> getSvcSplyOrdDtlPk(String glblCmpyCd, BigDecimal svcSplyOrdPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcSplyOrdPk", svcSplyOrdPk);
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObjectList("getSvcSplyOrdDtlPk", param);
        if (ssmResult.isCodeNormal()) {
            return (List<BigDecimal>) ssmResult.getResultObject();
        } else {
            return new ArrayList<BigDecimal>();
        }
    }

    /**
     * getSvcSplyOrd
     * @param glblCmpyCd String
     * @param svcSplyOrdPk BigDecimal
     * @return SvcSplyOrd List
     */
    public S21SsmEZDResult getSvcSplyOrd(String glblCmpyCd, BigDecimal svcSplyOrdPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("svcSplyOrdPk", svcSplyOrdPk);
        return getSsmEZDClient().queryObject("getSvcSplyOrd", params);
    }

    /**
     * getSvcSplyOrd
     * @param glblCmpyCd String
     * @param svcSplyOrdPk BigDecimal
     * @param length int
     * @return SvcSplyOrdDtl List
     */
    public S21SsmEZDResult getSvcSplyOrdDtl(String glblCmpyCd, BigDecimal svcSplyOrdPk, int length) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("svcSplyOrdPk", svcSplyOrdPk);
        params.put("pkgUomCd", PKG_UOM.EACH);
        params.put("rowNum", length + 1);
        return getSsmEZDClient().queryObjectList("getSvcSplyOrdDtl", params);
    }
    // add end 2019/01/21 QC#27304
}
