/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC156001;

import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.AMT_SCALE;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.PCT_SCALE;

import java.math.BigDecimal;

/**
 * NWZC156001DtlBean
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/31   Fujitsu         M.Yamada        Update          QC#12112
 */
public class NWZC156001DtlBean {

    /** */
    private String ordKey;

    /** */
    private String dsOrdPosnNum;

    /** */
    private BigDecimal dsCpoLineNum;

    /** */
    private BigDecimal dsCpoLineSubNum;

    /** */
    private String ordPrftTrxCatgCd;

    /** */
    private String trxLineNum;

    /** */
    private String trxLineSubNum;

    /** */
    private String trxRefLineNum;

    /** */
    private String trxRefLineSubNum;

    /** */
    private String rtlWhCd;

    /** */
    private String dsOrdLineCatgCd;

    /** */
    private BigDecimal ordQty;

    /** */
    private String custUomCd;

    /** */
    private String flPrcListCd;

    /** */
    private String flPrcListNm;

    /** */
    private String csmpPrcListCd;

    /** */
    private String csmpPrcListNm;

    /** */
    private BigDecimal funcManFlAdjAmt;

    /** */
    private BigDecimal funcNetUnitPrcAmt;

    /** */
    private BigDecimal funcNetSellPrcAmt;

    /** */
    private BigDecimal funcSvcRevTrnsfAmt;

    /** */
    private BigDecimal funcDlrCrAmt;

    /** */
    private BigDecimal funcRedCompAmt;

    /** */
    private BigDecimal funcUnitMsrpAmt;

    /** */
    private BigDecimal funcUnitStdCostAmt;

    /** */
    private BigDecimal funcFinalStdCostAmt;

    /** */
    private String mdseCd;

    /** */
    private String mdseNm;

    /** */
    private String origMdseCd;

    /** */
    private String mdseDescShortTxt;

    /** */
    private String coaMdseTpCd;

    /** */
    private String coaProdCd;

    /** */
    private String mdseItemTpCd;

    /** */
    private String ordPrftRuleTpCd;

    /** */
    private String sellToCustCd;

    /** */
    private String billToCustCd;

    /** */
    private String shipToCustCd;

    /** */
    private String shipToCustAcctCd;

    /** */
    private String billToCustAcctCd;

    /** */
    private String ordLineStsCd;

    /** */
    private String chngOrdFlg;

    /** */
    private String flPrcCalcInclFlg;

    /** */
    private String repRevCalcInclFlg;

    /** */
    private String discMdseTpFlg;

    /** */
    private String redFlPrcFlg;

    /** */
    private String redRepRevFlg;

    /** */
    private String dlrCrPrftInclFlg;

    /** */
    private String redCompAmtFlg;

    /** */
    private String discAllocMethCd;

    /** */
    private BigDecimal funcUnitListPrcAmt;

    /** */
    private BigDecimal negoSellPrcAmt;

    /** */
    private String rtlSwhCd;

    /** */
    private BigDecimal mdseInvtyCostPct;

//    /** */ // 2016/03/10 S21_NA#2939
//    private BigDecimal csmpCrAmt;

    //floor
    /** */
    private BigDecimal funcUnitFlPrcAmt;

    /** */
    private BigDecimal lineWtAmtRate;

    /** */
    private BigDecimal csmpUnitNewFlAmt;

    /** */
    private BigDecimal genAdjFlAmt;

    /** */
    private BigDecimal calcGenAdjFlAmt;

    /** */
    private BigDecimal calcSpecAdjFlAmt;

    /** */
    private BigDecimal funcSpecFlAdjAmt;

    /** */
    private BigDecimal funcGenlFlAdjAmt;

    /** */
    private BigDecimal funcCsmpUnitCrAmt;

    /** */
    private BigDecimal funcCsmpCrAmt;

    /** */
    private BigDecimal funcCsmpFlPrcAmt;

    /** */
    private BigDecimal funcSvcCostTrnsfAmt;

    /** */
    private BigDecimal funcWtSvcCostTrnsfAmt;

    /** */
    private BigDecimal funcInitFlPrcAmt;

    /** */
    private BigDecimal funcFlAdjAmt;

    /** */
    private BigDecimal funcFinalFlPrcAmt;

    /** */
    private BigDecimal finlRepFlAmt;

    //rep
    /** */
    private BigDecimal funcInitRepRevAmt;

    /** */
    private BigDecimal funcManRepRevAdjAmt;

    /** */
    private BigDecimal funcGenlRepRevAdjAmt;

    /** */
    private BigDecimal funcSpecRepRevAdjAmt;

    /** */
    private BigDecimal calcGenAdjRepRevAmt;

    /** */
    private BigDecimal calcSpecAdjRepRevAmt;

    /** */
    private BigDecimal funcRepRevAdjAmt;

    /** */
    private BigDecimal funcWtSvcRevTrnsfAmt;

    /** */
    private BigDecimal funcFinalRepRevAmt;

    /** */
    private String maintFlPrcCatgCd;

    /** */
    private BigDecimal mdlId;

    /** */
    private String ccyCd;

    /** */
    private BigDecimal cpoDtlFuncSlsAmt;

    /** */
    private BigDecimal ordCustUomQty;

    /** */
    private String csmpContrNum;

    /** */
    private String dlrRefNum;

    /** */
    private String prcCatgCd;

    /** */
    private String prcCatgNm;

    /** */
    private BigDecimal funcWtManFlAdjAmt;

    /** */
    private BigDecimal funcWtManRevAdjAmt;

    /** */
    private BigDecimal funcFinalUnitFlPrcAmt;

    /** */
    private BigDecimal funcFinalUnitRevAmt;

    //
    /** Constructor */
    public NWZC156001DtlBean() {
        setOrdKey("");
        setDsOrdPosnNum("");
//        setDsCpoLineNum(BigDecimal.ZERO);
//        setDsCpoLineSubNum(BigDecimal.ZERO);
        setOrdPrftTrxCatgCd("");
        setTrxLineNum("");
        setTrxLineSubNum("");
        setTrxRefLineNum("");
        setTrxRefLineSubNum("");
        setRtlWhCd("");
        setDsOrdLineCatgCd("");
        setOrdQty(BigDecimal.ZERO);
        setCustUomCd("");
        setFlPrcListCd("");
        setCsmpPrcListCd("");
        setFuncManFlAdjAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncNetUnitPrcAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncNetSellPrcAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncSvcRevTrnsfAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncDlrCrAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncRedCompAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncUnitMsrpAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncUnitStdCostAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncFinalStdCostAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setMdseCd("");
        setMdseNm("");
        setMdseDescShortTxt("");
        setCoaMdseTpCd("");
        setCoaProdCd("");
        setMdseItemTpCd("");
        setOrdPrftRuleTpCd("");
        setSellToCustCd("");
        setBillToCustCd("");
        setShipToCustCd("");
        setShipToCustAcctCd("");
        setBillToCustAcctCd("");
        setOrdLineStsCd("");
        setChngOrdFlg("");
        setFlPrcCalcInclFlg("");
        setRepRevCalcInclFlg("");
        setDiscMdseTpFlg("");
        setRedFlPrcFlg("");
        setRedRepRevFlg("");
        setDlrCrPrftInclFlg("");
        setRedCompAmtFlg("");
        setDiscAllocMethCd("");
        setFuncUnitListPrcAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setNegoSellPrcAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setRtlSwhCd("");
        setMdseInvtyCostPct(BigDecimal.ZERO.setScale(PCT_SCALE));
//        setCsmpCrAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncUnitFlPrcAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setLineWtAmtRate(BigDecimal.ZERO.setScale(AMT_SCALE));
        setCsmpUnitNewFlAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setGenAdjFlAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setCalcGenAdjFlAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setCalcSpecAdjFlAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncSpecFlAdjAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncGenlFlAdjAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncCsmpUnitCrAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncCsmpCrAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncCsmpFlPrcAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncSvcCostTrnsfAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncWtSvcCostTrnsfAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncInitFlPrcAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncFlAdjAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncFinalFlPrcAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFinlRepFlAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncInitRepRevAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncManRepRevAdjAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncGenlRepRevAdjAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncSpecRepRevAdjAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setCalcGenAdjRepRevAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setCalcSpecAdjRepRevAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncRepRevAdjAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncWtSvcRevTrnsfAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncFinalRepRevAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setMaintFlPrcCatgCd("");
        setMdlId(BigDecimal.ZERO);
        setCcyCd("");
        setCpoDtlFuncSlsAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setOrdCustUomQty(BigDecimal.ZERO);
        setCsmpContrNum("");
        setDlrRefNum("");
        setPrcCatgCd("");
        setPrcCatgNm("");
        setFuncWtManFlAdjAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncWtManRevAdjAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncFinalUnitFlPrcAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncFinalUnitRevAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
    }

    /**
     * @param ordKey ordKey
     */
    public void setOrdKey(String ordKey) {
        this.ordKey = ordKey;
    }

    /**
     * @return ordKey
     */
    public String getOrdKey() {
        return ordKey;
    }

    /**
     * @param dsOrdPosnNum dsOrdPosnNum
     */
    public void setDsOrdPosnNum(String dsOrdPosnNum) {
        this.dsOrdPosnNum = dsOrdPosnNum;
    }

    /**
     * @return dsOrdPosnNum
     */
    public String getDsOrdPosnNum() {
        return dsOrdPosnNum;
    }

    /**
     * @param dsCpoLineNum dsCpoLineNum
     */
    public void setDsCpoLineNum(BigDecimal dsCpoLineNum) {
        this.dsCpoLineNum = dsCpoLineNum;
    }

    /**
     * @return dsCpoLineNum
     */
    public BigDecimal getDsCpoLineNum() {
        return dsCpoLineNum;
    }

    /**
     * @return dsCpoLineSubNum
     */
    public BigDecimal getDsCpoLineSubNum() {
        return dsCpoLineSubNum;
    }

    /**
     * @param dsCpoLineSubNum dsCpoLineSubNum
     */
    public void setDsCpoLineSubNum(BigDecimal dsCpoLineSubNum) {
        this.dsCpoLineSubNum = dsCpoLineSubNum;
    }

    /**
     * @return mdseCd mdseCd
     */
    public String getMdseCd() {
        return mdseCd;
    }

    /**
     * @param mdseCd mdseCd
     */
    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    /**
     * @return mdseNm mdseNm
     */
    public String getMdseNm() {
        return mdseNm;
    }

    /**
     * @return mdseDescShortTxt mdseDescShortTxt
     */
    public String getMdseDescShortTxt() {
        return mdseDescShortTxt;
    }

    /**
     * @param mdseNm mdseNm
     */
    public void setMdseNm(String mdseNm) {
        this.mdseNm = mdseNm;
    }

    /**
     * @param mdseDescShortTxt mdseDescShortTxt
     */
    public void setMdseDescShortTxt(String mdseDescShortTxt) {
        this.mdseDescShortTxt = mdseDescShortTxt;
    }

    /**
     * getCoaMdseTpCd
     * @return coaMdseTpCd
     */
    public String getCoaMdseTpCd() {
        return coaMdseTpCd;
    }

    /**
     * setCoaMdseTpCd
     * @param coaMdseTpCd coaMdseTpCd
     */
    public void setCoaMdseTpCd(String coaMdseTpCd) {
        this.coaMdseTpCd = coaMdseTpCd;
    }

    /**
     * @param coaProdCd coaProdCd
     */
    public void setCoaProdCd(String coaProdCd) {
        this.coaProdCd = coaProdCd;
    }

    /**
     * @return coaProdCd
     */
    public String getCoaProdCd() {
        return coaProdCd;
    }

    /**
     * @param mdseItemTpCd mdseItemTpCd
     */
    public void setMdseItemTpCd(String mdseItemTpCd) {
        this.mdseItemTpCd = mdseItemTpCd;
    }

    /**
     * @return mdseItemTpCd
     */
    public String getMdseItemTpCd() {
        return mdseItemTpCd;
    }

    /**
     * getFlPrcCalcInclFlg
     * @return flPrcCalcInclFlg
     */
    public String getFlPrcCalcInclFlg() {
        return flPrcCalcInclFlg;
    }

    /**
     * setFlPrcCalcInclFlg
     * @param flPrcCalcInclFlg flPrcCalcInclFlg
     */
    public void setFlPrcCalcInclFlg(String flPrcCalcInclFlg) {
        this.flPrcCalcInclFlg = flPrcCalcInclFlg;
    }

    /**
     * getRepRevCalcInclFlg
     * @return repRevIncFlg
     */
    public String getRepRevCalcInclFlg() {
        return repRevCalcInclFlg;
    }

    /**
     * setRepRevIncFlg
     * @param repRevCalcInclFlg repRevCalcInclFlg
     */
    public void setRepRevCalcInclFlg(String repRevCalcInclFlg) {
        this.repRevCalcInclFlg = repRevCalcInclFlg;
    }

    /**
     * getDiscMdseTpFlg
     * @return discFlg
     */
    public String getDiscMdseTpFlg() {
        return discMdseTpFlg;
    }

    /**
     * setDiscMdseTpFlg
     * @param discMdseTpFlg discMdseTpFlg
     */
    public void setDiscMdseTpFlg(String discMdseTpFlg) {
        this.discMdseTpFlg = discMdseTpFlg;
    }

    /**
     * @return redFlFlg
     */
    public String getRedFlPrcFlg() {
        return redFlPrcFlg;
    }

    /**
     * setRedFlFlg
     * @param redFlPrcFlg redFlPrcFlg
     */
    public void setRedFlPrcFlg(String redFlPrcFlg) {
        this.redFlPrcFlg = redFlPrcFlg;
    }

    /**
     * getRedRepRev
     * @return redRepRevFlg
     */
    public String getRedRepRevFlg() {
        return redRepRevFlg;
    }

    /**
     * setRedRepRevFlg
     * @param redRepRevFlg redRepRevFlg
     */
    public void setRedRepRevFlg(String redRepRevFlg) {
        this.redRepRevFlg = redRepRevFlg;
    }

    /**
     * @param dlrCrPrftInclFlg dlrCrPrftInclFlg
     */
    public void setDlrCrPrftInclFlg(String dlrCrPrftInclFlg) {
        this.dlrCrPrftInclFlg = dlrCrPrftInclFlg;
    }

    /**
     * @return dlrCrPrftInclFlg
     */
    public String getDlrCrPrftInclFlg() {
        return dlrCrPrftInclFlg;
    }

    /**
     * getAllocMethCd
     * @return allocMethCd
     */
    public String getDiscAllocMethCd() {
        return discAllocMethCd;
    }

    /**
     * @param redCompAmtFlg redCompAmtFlg
     */
    public void setRedCompAmtFlg(String redCompAmtFlg) {
        this.redCompAmtFlg = redCompAmtFlg;
    }

    /**
     * @return redCompAmtFlg
     */
    public String getRedCompAmtFlg() {
        return redCompAmtFlg;
    }

    /**
     * setAllocMethCd
     * @param discAllocMethCd allocMethCd
     */
    public void setDiscAllocMethCd(String discAllocMethCd) {
        this.discAllocMethCd = discAllocMethCd;
    }

    /**
     * getListPrcAmt
     * @return listPrcAmt
     */
    public BigDecimal getFuncUnitListPrcAmt() {
        return funcUnitListPrcAmt;
    }

    /**
     * setListPrcAmt
     * @param listPrcAmt listPrcAmt
     */
    public void setFuncUnitListPrcAmt(BigDecimal listPrcAmt) {
        this.funcUnitListPrcAmt = listPrcAmt;
    }

    /**
     * getNegoSellPrcAmt
     * @return negoSellPrcAmt
     */
    public BigDecimal getNegoSellPrcAmt() {
        return negoSellPrcAmt;
    }

    /**
     * setNegoSellPrcAmt
     * @param negoSellPrcAmt negoSellPrcAmt
     */
    public void setNegoSellPrcAmt(BigDecimal negoSellPrcAmt) {
        this.negoSellPrcAmt = negoSellPrcAmt;
    }

    /**
     * getRtlSwhCd
     * @return rtlSwhCd
     */
    public String getRtlSwhCd() {
        return rtlSwhCd;
    }

    /**
     * setRtlSwhCd
     * @param rtlSwhCd rtlSwhCd
     */
    public void setRtlSwhCd(String rtlSwhCd) {
        this.rtlSwhCd = rtlSwhCd;
    }

    /**
     * getInvtyPct
     * @return invtyPct
     */
    public BigDecimal getMdseInvtyCostPct() {
        return mdseInvtyCostPct;
    }

    /**
     * setInvtyPct
     * @param mdseInvtyCostPct invtyPct
     */
    public void setMdseInvtyCostPct(BigDecimal mdseInvtyCostPct) {
        this.mdseInvtyCostPct = mdseInvtyCostPct;
    }

//    /**
//     * getCsmpCrAmt
//     * @return csmpCrAmt
//     */
//    public BigDecimal getCsmpCrAmt() {
//        return csmpCrAmt;
//    }
//
//    /**
//     * setCsmpCrAmt
//     * @param csmpCrAmt csmpCrAmt
//     */
//    public void setCsmpCrAmt(BigDecimal csmpCrAmt) {
//        this.csmpCrAmt = csmpCrAmt;
//    }

    /**
     * getUnitFlPrcAmt
     * @return unitFlPrcAmt
     */
    public BigDecimal getFuncUnitFlPrcAmt() {
        return funcUnitFlPrcAmt;
    }

    /**
     * setUnitFlPrcAmt
     * @param unitFlPrcAmt unitFlPrcAmt
     */
    public void setFuncUnitFlPrcAmt(BigDecimal unitFlPrcAmt) {
        this.funcUnitFlPrcAmt = unitFlPrcAmt;
    }

    /**
     * getLineWtAmtRate
     * @return lineWtAmtRate
     */
    public BigDecimal getLineWtAmtRate() {
        return lineWtAmtRate;
    }

    /**
     * setLineWtAmtRate
     * @param wtPct wtPct
     */
    public void setLineWtAmtRate(BigDecimal wtPct) {
        this.lineWtAmtRate = wtPct;
    }

    /**
     * getCsmpUnitNewFlAmt
     * @return csmpUnitNewFlAmt
     */
    public BigDecimal getCsmpUnitNewFlAmt() {
        return csmpUnitNewFlAmt;
    }

    /**
     * setCsmpUnitNewFlAmt
     * @param csmpUnitNewFlAmt csmpUnitNewFlAmt
     */
    public void setCsmpUnitNewFlAmt(BigDecimal csmpUnitNewFlAmt) {
        this.csmpUnitNewFlAmt = csmpUnitNewFlAmt;
    }

    /**
     * getGenAdjFlAmt
     * @return genAdjFlAmt
     */
    public BigDecimal getGenAdjFlAmt() {
        return genAdjFlAmt;
    }

    /**
     * setGenAdjFlAmt
     * @param genAdjFlAmt genAdjFlAmt
     */
    public void setGenAdjFlAmt(BigDecimal genAdjFlAmt) {
        this.genAdjFlAmt = genAdjFlAmt;
    }

    /**
     * getCalcGenAdjFlAmt
     * @return calcGenAdjFlAmt
     */
    public BigDecimal getCalcGenAdjFlAmt() {
        return calcGenAdjFlAmt;
    }

    /**
     * setCalcGenAdjFlAmt
     * @param calcGenAdjFlAmt calcGenAdjFlAmt
     */
    public void setCalcGenAdjFlAmt(BigDecimal calcGenAdjFlAmt) {
        this.calcGenAdjFlAmt = calcGenAdjFlAmt;
    }

    /**
     * getCalcSpecAdjFlAmt
     * @return calcSpecAdjFlAmt
     */
    public BigDecimal getCalcSpecAdjFlAmt() {
        return calcSpecAdjFlAmt;
    }

    /**
     * setCalcSpecAdjFlAmt
     * @param calcSpecAdjFlAmt calcSpecAdjFlAmt
     */
    public void setCalcSpecAdjFlAmt(BigDecimal calcSpecAdjFlAmt) {
        this.calcSpecAdjFlAmt = calcSpecAdjFlAmt;
    }

    /**
     * getFuncGenlFlAdjAmt
     * @return funcGenlFlAdjAmt
     */
    public BigDecimal getFuncGenlFlAdjAmt() {
        return funcGenlFlAdjAmt;
    }

    /**
     * setFuncGenlFlAdjAmt
     * @param funcGenlFlAdjAmt funcGenlFlAdjAmt
     */
    public void setFuncGenlFlAdjAmt(BigDecimal funcGenlFlAdjAmt) {
        this.funcGenlFlAdjAmt = funcGenlFlAdjAmt;
    }

    /**
     * getSvcCtValAmt
     * @return svcCtValAmt
     */
    public BigDecimal getFuncSvcCostTrnsfAmt() {
        return funcSvcCostTrnsfAmt;
    }

    /**
     * setSvcCtValAmt
     * @param svcCtValAmt svcCtValAmt
     */
    public void setFuncSvcCostTrnsfAmt(BigDecimal svcCtValAmt) {
        this.funcSvcCostTrnsfAmt = svcCtValAmt;
    }

    /**
     * getFinlRepFlAmt
     * @return finlRepFlAmt
     */
    public BigDecimal getFinlRepFlAmt() {
        return finlRepFlAmt;
    }

    /**
     * setFinlRepFlAmt
     * @param finlRepFlAmt finlRepFlAmt
     */
    public void setFinlRepFlAmt(BigDecimal finlRepFlAmt) {
        this.finlRepFlAmt = finlRepFlAmt;
    }

    /**
     * getInitRepRevAmt
     * @return initRepRevAmt
     */
    public BigDecimal getFuncInitRepRevAmt() {
        return funcInitRepRevAmt;
    }

    /**
     * setFuncInitRepRevAmt
     * @param initRepRevAmt initRepRevAmt
     */
    public void setFuncInitRepRevAmt(BigDecimal initRepRevAmt) {
        this.funcInitRepRevAmt = initRepRevAmt;
    }

    /**
     * getFuncGenlRepRevAdjAmt
     * @return funcGenAdjRepRevAmt
     */
    public BigDecimal getFuncGenlRepRevAdjAmt() {
        return funcGenlRepRevAdjAmt;
    }

    //    /**
    //     * setFuncGenAdjRepRevAmt
    //     * @param funcGenlRepRevAdjAmt funcGenlRepRevAdjAmt
    //     */
    //    public void setFuncGenlRepRevAdjAmt(BigDecimal funcGenlRepRevAdjAmt) {
    //        this.funcGenlRepRevAdjAmt = funcGenlRepRevAdjAmt;
    //    }

    /**
     * getCalcGenAdjRepRevAmt
     * @return calcGenAdjRepRevAmt
     */
    public BigDecimal getCalcGenAdjRepRevAmt() {
        return calcGenAdjRepRevAmt;
    }

    /**
     * setCalcGenAdjRepRevAmt
     * @param calcGenAdjRepRevAmt calcGenAdjRepRevAmt
     */
    public void setCalcGenAdjRepRevAmt(BigDecimal calcGenAdjRepRevAmt) {
        this.calcGenAdjRepRevAmt = calcGenAdjRepRevAmt;
    }

    /**
     * getCalcSpecAdjRepRevAmt
     * @return calcSpecAdjRepRevAmt
     */
    public BigDecimal getCalcSpecAdjRepRevAmt() {
        return calcSpecAdjRepRevAmt;
    }

    /**
     * setCalcSpecAdjRepRevAmt
     * @param calcSpecAdjRepRevAmt calcSpecAdjRepRevAmt
     */
    public void setCalcSpecAdjRepRevAmt(BigDecimal calcSpecAdjRepRevAmt) {
        this.calcSpecAdjRepRevAmt = calcSpecAdjRepRevAmt;
    }

    /**
     * getFuncRepRevAdjAmt
     * @return funcRepRevAdjAmt
     */
    public BigDecimal getFuncRepRevAdjAmt() {
        return funcRepRevAdjAmt;
    }

    /**
     * setFuncRepRevAdjAmt
     * @param repRevAdjAmt repRevAdjAmt
     */
    public void setFuncRepRevAdjAmt(BigDecimal repRevAdjAmt) {
        this.funcRepRevAdjAmt = repRevAdjAmt;
    }

    //    /**
    //     * getSrtAllocAmt
    //     * @return srtAllocAmt
    //     */
    //    public BigDecimal getSrtAllocAmt() {
    //        return srtAllocAmt;
    //    }
    //
    //    /**
    //     * setSrtAllocAmt
    //     * @param srtAllocAmt srtAllocAmt
    //     */
    //    public void setSrtAllocAmt(BigDecimal srtAllocAmt) {
    //        this.srtAllocAmt = srtAllocAmt;
    //    }
    //
    //    /**
    //     * getFinlRepRevAmt
    //     * @return finlRepRevAmt
    //     */
    //    public BigDecimal getFinlRepRevAmt() {
    //        return finlRepRevAmt;
    //    }
    //
    //    /**
    //     * setFinlRepRevAmt
    //     * @param finlRepRevAmt finlRepRevAmt
    //     */
    //    public void setFinlRepRevAmt(BigDecimal finlRepRevAmt) {
    //        this.finlRepRevAmt = finlRepRevAmt;
    //    }

    /**
     * @return ordPrftTrxCatgCd
     */
    public String getOrdPrftTrxCatgCd() {
        return ordPrftTrxCatgCd;
    }

    /**
     * @param ordPrftTrxCatgCd ordPrftTrxCatgCd
     */
    public void setOrdPrftTrxCatgCd(String ordPrftTrxCatgCd) {
        this.ordPrftTrxCatgCd = ordPrftTrxCatgCd;
    }

    /**
     * @return trxLineNum
     */
    public String getTrxLineNum() {
        return trxLineNum;
    }

    /**
     * @param trxLineNum trxLineNum
     */
    public void setTrxLineNum(String trxLineNum) {
        this.trxLineNum = trxLineNum;
    }

    /**
     * @return trxLineSubNum trxLineSubNum
     */
    public String getTrxLineSubNum() {
        return trxLineSubNum;
    }

    /**
     * @param trxLineSubNum trxLineSubNum
     */
    public void setTrxLineSubNum(String trxLineSubNum) {
        this.trxLineSubNum = trxLineSubNum;
    }

    /**
     * @return rtlWhCd rtlWhCd
     */
    public String getRtlWhCd() {
        return rtlWhCd;
    }

    /**
     * @param rtlWhCd rtlWhCd
     */
    public void setRtlWhCd(String rtlWhCd) {
        this.rtlWhCd = rtlWhCd;
    }

    /**
     * @return dsOrdLineCatgCd
     */
    public String getDsOrdLineCatgCd() {
        return dsOrdLineCatgCd;
    }

    /**
     * @param dsOrdLineCatgCd dsOrdLineCatgCd
     */
    public void setDsOrdLineCatgCd(String dsOrdLineCatgCd) {
        this.dsOrdLineCatgCd = dsOrdLineCatgCd;
    }

    /**
     * @return ordQty
     */
    public BigDecimal getOrdQty() {
        return ordQty;
    }

    /**
     * @param ordQty ordQty
     */
    public void setOrdQty(BigDecimal ordQty) {
        this.ordQty = ordQty;
    }

    /**
     * @return custUomCd
     */
    public String getCustUomCd() {
        return custUomCd;
    }

    /**
     * @param custUomCd custUomCd
     */
    public void setCustUomCd(String custUomCd) {
        this.custUomCd = custUomCd;
    }

    /**
     * @return flPrcListCd
     */
    public String getFlPrcListCd() {
        return flPrcListCd;
    }

    /**
     * @param flPrcListCd flPrcListCd
     */
    public void setFlPrcListCd(String flPrcListCd) {
        this.flPrcListCd = flPrcListCd;
    }

    /**
     * @return csmpPrcListCd
     */
    public String getCsmpPrcListCd() {
        return csmpPrcListCd;
    }

    /**
     * @param csmpPrcListCd csmpPrcListCd
     */
    public void setCsmpPrcListCd(String csmpPrcListCd) {
        this.csmpPrcListCd = csmpPrcListCd;
    }

    /**
     * @return funcManFlAdjAmt
     */
    public BigDecimal getFuncManFlAdjAmt() {
        return funcManFlAdjAmt;
    }

    /**
     * @param funcManFlAdjAmt funcManFlAdjAmt
     */
    public void setFuncManFlAdjAmt(BigDecimal funcManFlAdjAmt) {
        this.funcManFlAdjAmt = funcManFlAdjAmt;
    }

    /**
     * @return funcNetUnitPrcAmt
     */
    public BigDecimal getFuncNetUnitPrcAmt() {
        return funcNetUnitPrcAmt;
    }

    /**
     * @param funcNetUnitPrcAmt funcNetUnitPrcAmt
     */
    public void setFuncNetUnitPrcAmt(BigDecimal funcNetUnitPrcAmt) {
        this.funcNetUnitPrcAmt = funcNetUnitPrcAmt;
    }

    /**
     * @return funcNetSellPrcAmt
     */
    public BigDecimal getFuncNetSellPrcAmt() {
        return funcNetSellPrcAmt;
    }

    /**
     * @param funcNetSellPrcAmt funcNetSellPrcAmt
     */
    public void setFuncNetSellPrcAmt(BigDecimal funcNetSellPrcAmt) {
        this.funcNetSellPrcAmt = funcNetSellPrcAmt;
    }

    /**
     * @return funcSvcRevTrnsfAmt
     */
    public BigDecimal getFuncSvcRevTrnsfAmt() {
        return funcSvcRevTrnsfAmt;
    }

    /**
     * @param funcSvcRevTrnsfAmt funcSvcRevTrnsfAmt
     */
    public void setFuncSvcRevTrnsfAmt(BigDecimal funcSvcRevTrnsfAmt) {
        this.funcSvcRevTrnsfAmt = funcSvcRevTrnsfAmt;
    }

    /**
     * @return funcManRepRevAdjAmt
     */
    public BigDecimal getFuncManRepRevAdjAmt() {
        return funcManRepRevAdjAmt;
    }

    /**
     * @param funcManRepRevAdjAmt funcManRepRevAdjAmt
     */
    public void setFuncManRepRevAdjAmt(BigDecimal funcManRepRevAdjAmt) {
        this.funcManRepRevAdjAmt = funcManRepRevAdjAmt;
    }

    /**
     * @param funcInitFlPrcAmt funcInitFlPrcAmt
     */
    public void setFuncInitFlPrcAmt(BigDecimal funcInitFlPrcAmt) {
        this.funcInitFlPrcAmt = funcInitFlPrcAmt;
    }

    /**
     * @return funcInitFlPrcAmt
     */
    public BigDecimal getFuncInitFlPrcAmt() {
        return funcInitFlPrcAmt;
    }

    /**
     * @param funcCsmpFlPrcAmt funcCsmpFlPrcAmt
     */
    public void setFuncCsmpFlPrcAmt(BigDecimal funcCsmpFlPrcAmt) {
        this.funcCsmpFlPrcAmt = funcCsmpFlPrcAmt;
    }

    /**
     * @return funcCsmpFlPrcAmt
     */
    public BigDecimal getFuncCsmpFlPrcAmt() {
        return funcCsmpFlPrcAmt;
    }

    /**
     * @param funcWtSvcCostTrnsfAmt funcWtSvcCostTrnsfAmt
     */
    public void setFuncWtSvcCostTrnsfAmt(BigDecimal funcWtSvcCostTrnsfAmt) {
        this.funcWtSvcCostTrnsfAmt = funcWtSvcCostTrnsfAmt;
    }

    /**
     * @return funcWtSvcCostTrnsfAmt
     */
    public BigDecimal getFuncWtSvcCostTrnsfAmt() {
        return funcWtSvcCostTrnsfAmt;
    }

    /**
     * @param funcWtSvcRevTrnsfAmt funcWtSvcRevTrnsfAmt
     */
    public void setFuncWtSvcRevTrnsfAmt(BigDecimal funcWtSvcRevTrnsfAmt) {
        this.funcWtSvcRevTrnsfAmt = funcWtSvcRevTrnsfAmt;
    }

    /**
     * @return funcWtSvcRevTrnsfAmt
     */
    public BigDecimal getFuncWtSvcRevTrnsfAmt() {
        return funcWtSvcRevTrnsfAmt;
    }

    /**
     * @return funcSpecRepRevAdjAmt
     */
    public BigDecimal getFuncSpecRepRevAdjAmt() {
        return funcSpecRepRevAdjAmt;
    }

    /**
     * @param funcDlrCrAmt funcDlrCrAmt
     */
    public void setFuncDlrCrAmt(BigDecimal funcDlrCrAmt) {
        this.funcDlrCrAmt = funcDlrCrAmt;
    }

    /**
     * @return funcDlrCrAmt
     */
    public BigDecimal getFuncDlrCrAmt() {
        return funcDlrCrAmt;
    }

    /**
     * @param funcRedCompAmt funcRedCompAmt
     */
    public void setFuncRedCompAmt(BigDecimal funcRedCompAmt) {
        this.funcRedCompAmt = funcRedCompAmt;
    }

    /**
     * @return funcRedCompAmt
     */
    public BigDecimal getFuncRedCompAmt() {
        return funcRedCompAmt;
    }

    /**
     * @param funcSpecFlAdjAmt funcSpecFlAdjAmt
     */
    public void setFuncSpecFlAdjAmt(BigDecimal funcSpecFlAdjAmt) {
        this.funcSpecFlAdjAmt = funcSpecFlAdjAmt;
    }

    /**
     * @return funcSpecFlAdjAmt
     */
    public BigDecimal getFuncSpecFlAdjAmt() {
        return funcSpecFlAdjAmt;
    }

    /**
     * @param funcFlAdjAmt funcFlAdjAmt
     */
    public void setFuncFlAdjAmt(BigDecimal funcFlAdjAmt) {
        this.funcFlAdjAmt = funcFlAdjAmt;
    }

    /**
     * @return funcFlAdjAmt
     */
    public BigDecimal getFuncFlAdjAmt() {
        return funcFlAdjAmt;
    }

    /**
     * @param funcCsmpUnitCrAmt funcCsmpUnitCrAmt
     */
    public void setFuncCsmpUnitCrAmt(BigDecimal funcCsmpUnitCrAmt) {
        this.funcCsmpUnitCrAmt = funcCsmpUnitCrAmt;
    }

    /**
     * @return funcCsmpUnitCrAmt
     */
    public BigDecimal getFuncCsmpUnitCrAmt() {
        return funcCsmpUnitCrAmt;
    }

    /**
     * @param funcCsmpCrAmt funcCsmpCrAmt
     */
    public void setFuncCsmpCrAmt(BigDecimal funcCsmpCrAmt) {
        this.funcCsmpCrAmt = funcCsmpCrAmt;
    }

    /**
     * @return funcCsmpCrAmt
     */
    public BigDecimal getFuncCsmpCrAmt() {
        return funcCsmpCrAmt;
    }

    /**
     * @param funcFinalFlPrcAmt funcFinalFlPrcAmt
     */
    public void setFuncFinalFlPrcAmt(BigDecimal funcFinalFlPrcAmt) {
        this.funcFinalFlPrcAmt = funcFinalFlPrcAmt;
    }

    /**
     * @return funcFinalFlPrcAmt
     */
    public BigDecimal getFuncFinalFlPrcAmt() {
        return funcFinalFlPrcAmt;
    }

    /**
     * @param funcFinalRepRevAmt funcFinalRepRevAmt
     */
    public void setFuncFinalRepRevAmt(BigDecimal funcFinalRepRevAmt) {
        this.funcFinalRepRevAmt = funcFinalRepRevAmt;
    }

    /**
     * @return funcFinalRepRevAmt
     */
    public BigDecimal getFuncFinalRepRevAmt() {
        return funcFinalRepRevAmt;
    }

    /**
     * @param funcUnitMsrpAmt funcUnitMsrpAmt
     */
    public void setFuncUnitMsrpAmt(BigDecimal funcUnitMsrpAmt) {
        this.funcUnitMsrpAmt = funcUnitMsrpAmt;
    }

    /**
     * @return funcUnitMsrpAmt
     */
    public BigDecimal getFuncUnitMsrpAmt() {
        return funcUnitMsrpAmt;
    }

    /**
     * @param funcUnitStdCostAmt funcUnitStdCostAmt
     */
    public void setFuncUnitStdCostAmt(BigDecimal funcUnitStdCostAmt) {
        this.funcUnitStdCostAmt = funcUnitStdCostAmt;
    }

    /**
     * @return funcUnitStdCostAmt
     */
    public BigDecimal getFuncUnitStdCostAmt() {
        return funcUnitStdCostAmt;
    }

    /**
     * @param funcFinalStdCostAmt funcFinalStdCostAmt
     */
    public void setFuncFinalStdCostAmt(BigDecimal funcFinalStdCostAmt) {
        this.funcFinalStdCostAmt = funcFinalStdCostAmt;
    }

    /**
     * @return funcFinalStdCostAmt
     */
    public BigDecimal getFuncFinalStdCostAmt() {
        return funcFinalStdCostAmt;
    }

    /**
     * @param chngOrdFlg chngOrdFlg
     */
    public void setChngOrdFlg(String chngOrdFlg) {
        this.chngOrdFlg = chngOrdFlg;
    }

    /**
     * @return chngOrdFlg
     */
    public String getChngOrdFlg() {
        return chngOrdFlg;
    }

    /**
     * @param ordPrftRuleTpCd ordPrftRuleTpCd
     */
    public void setOrdPrftRuleTpCd(String ordPrftRuleTpCd) {
        this.ordPrftRuleTpCd = ordPrftRuleTpCd;
    }

    /**
     * @return ordPrftRuleTpCd
     */
    public String getOrdPrftRuleTpCd() {
        return ordPrftRuleTpCd;
    }

    /**
     * @return trxRefLineNum
     */
    public String getTrxRefLineNum() {
        return trxRefLineNum;
    }

    /**
     * @param trxRefLineNum trxRefLineNum
     */
    public void setTrxRefLineNum(String trxRefLineNum) {
        this.trxRefLineNum = trxRefLineNum;
    }

    /**
     * @return trxRefLineSubNum
     */
    public String getTrxRefLineSubNum() {
        return trxRefLineSubNum;
    }

    /**
     * @param trxRefLineSubNum trxRefLineSubNum
     */
    public void setTrxRefLineSubNum(String trxRefLineSubNum) {
        this.trxRefLineSubNum = trxRefLineSubNum;
    }

    /**
     * @param funcGenlRepRevAdjAmt funcGenlRepRevAdjAmt
     */
    public void setFuncGenlRepRevAdjAmt(BigDecimal funcGenlRepRevAdjAmt) {
        this.funcGenlRepRevAdjAmt = funcGenlRepRevAdjAmt;
    }

    /**
     * @param funcSpecRepRevAdjAmt funcSpecRepRevAdjAmt
     */
    public void setFuncSpecRepRevAdjAmt(BigDecimal funcSpecRepRevAdjAmt) {
        this.funcSpecRepRevAdjAmt = funcSpecRepRevAdjAmt;
    }

    /**
     * @return sellToCustCd
     */
    public String getSellToCustCd() {
        return sellToCustCd;
    }

    /**
     * @param sellToCustCd sellToCustCd
     */
    public void setSellToCustCd(String sellToCustCd) {
        this.sellToCustCd = sellToCustCd;
    }

    /**
     * @return billToCustCd
     */
    public String getBillToCustCd() {
        return billToCustCd;
    }

    /**
     * @param billToCustCd billToCustCd
     */
    public void setBillToCustCd(String billToCustCd) {
        this.billToCustCd = billToCustCd;
    }

    /**
     * @return shipToCustCd
     */
    public String getShipToCustCd() {
        return shipToCustCd;
    }

    /**
     * @param shipToCustCd shipToCustCd
     */
    public void setShipToCustCd(String shipToCustCd) {
        this.shipToCustCd = shipToCustCd;
    }

    /**
     * @return shipToCustAcctCd
     */
    public String getShipToCustAcctCd() {
        return shipToCustAcctCd;
    }

    /**
     * @param shipToCustAcctCd shipToCustAcctCd
     */
    public void setShipToCustAcctCd(String shipToCustAcctCd) {
        this.shipToCustAcctCd = shipToCustAcctCd;
    }

    /**
     * @return billToCustAcctCd
     */
    public String getBillToCustAcctCd() {
        return billToCustAcctCd;
    }

    /**
     * @param billToCustAcctCd billToCustAcctCd
     */
    public void setBillToCustAcctCd(String billToCustAcctCd) {
        this.billToCustAcctCd = billToCustAcctCd;
    }

    /**
     * @return ordLineStsCd
     */
    public String getOrdLineStsCd() {
        return ordLineStsCd;
    }

    /**
     * @param ordLineStsCd ordLineStsCd
     */
    public void setOrdLineStsCd(String ordLineStsCd) {
        this.ordLineStsCd = ordLineStsCd;
    }

    /**
     * @param maintFlPrcCatgCd maintFlPrcCatgCd
     */
    public void setMaintFlPrcCatgCd(String maintFlPrcCatgCd) {
        this.maintFlPrcCatgCd = maintFlPrcCatgCd;
    }

    /**
     * @return maintFlPrcCatgCd
     */
    public String getMaintFlPrcCatgCd() {
        return maintFlPrcCatgCd;
    }

    /**
     * @param mdlId
     */
    public void setMdlId(BigDecimal mdlId) {
        this.mdlId = mdlId;
    }

    /**
     * @return mdlId
     */
    public BigDecimal getMdlId() {
        return mdlId;
    }

    /**
     * @param ccyCd
     */
    public void setCcyCd(String ccyCd) {
        this.ccyCd = ccyCd;
    }

    /**
     * @return ccyCd
     */
    public String getCcyCd() {
        return ccyCd;
    }

    public void setFlPrcListNm(String flPrcListNm) {
        this.flPrcListNm = flPrcListNm;
    }

    public String getFlPrcListNm() {
        return flPrcListNm;
    }

    public void setCsmpPrcListNm(String csmpPrcListNm) {
        this.csmpPrcListNm = csmpPrcListNm;
    }

    public String getCsmpPrcListNm() {
        return csmpPrcListNm;
    }

    public void setCpoDtlFuncSlsAmt(BigDecimal cpoDtlFuncSlsAmt) {
        this.cpoDtlFuncSlsAmt = cpoDtlFuncSlsAmt;
    }

    public BigDecimal getCpoDtlFuncSlsAmt() {
        return cpoDtlFuncSlsAmt;
    }

    public void setOrdCustUomQty(BigDecimal ordCustUomQty) {
        this.ordCustUomQty = ordCustUomQty;
    }

    public BigDecimal getOrdCustUomQty() {
        return ordCustUomQty;
    }

    public void setCsmpContrNum(String csmpContrNum) {
        this.csmpContrNum = csmpContrNum;
    }

    public String getCsmpContrNum() {
        return csmpContrNum;
    }

    public void setDlrRefNum(String dlrRefNum) {
        this.dlrRefNum = dlrRefNum;
    }

    public String getDlrRefNum() {
        return dlrRefNum;
    }

    public void setPrcCatgCd(String prcCatgCd) {
        this.prcCatgCd = prcCatgCd;
    }

    public String getPrcCatgCd() {
        return prcCatgCd;
    }

    public void setPrcCatgNm(String prcCatgNm) {
        this.prcCatgNm = prcCatgNm;
    }

    public String getPrcCatgNm() {
        return prcCatgNm;
    }

    public void setFuncWtManFlAdjAmt(BigDecimal funcWtManFlAdjAmt) {
        this.funcWtManFlAdjAmt = funcWtManFlAdjAmt;
    }

    public BigDecimal getFuncWtManFlAdjAmt() {
        return funcWtManFlAdjAmt;
    }

    public void setFuncWtManRevAdjAmt(BigDecimal funcWtManRevAdjAmt) {
        this.funcWtManRevAdjAmt = funcWtManRevAdjAmt;
    }

    public BigDecimal getFuncWtManRevAdjAmt() {
        return funcWtManRevAdjAmt;
    }

    public void setFuncFinalUnitFlPrcAmt(BigDecimal funcFinalUnitFlPrcAmt) {
        this.funcFinalUnitFlPrcAmt = funcFinalUnitFlPrcAmt;
    }

    public BigDecimal getFuncFinalUnitFlPrcAmt() {
        return funcFinalUnitFlPrcAmt;
    }

    public void setFuncFinalUnitRevAmt(BigDecimal funcFinalUnitRevAmt) {
        this.funcFinalUnitRevAmt = funcFinalUnitRevAmt;
    }

    public BigDecimal getFuncFinalUnitRevAmt() {
        return funcFinalUnitRevAmt;
    }

    /**
     * @param origMdseCd set to origMdseCd
     */
    public void setOrigMdseCd(String origMdseCd) {
        this.origMdseCd = origMdseCd;
    }

    /**
     * @return origMdseCd
     */
    public String getOrigMdseCd() {
        return origMdseCd;
    }
}
