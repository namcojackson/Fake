/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB039001;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/04/2015   Hitachi         J.Kim           Create          N/A
 * 08/24/2016   Hitachi         K.Kasai         Update          QC#13662,13678
 * 09/30/2016   Hitachi         T.Tomita        Update          QC#14145
 * 01/10/2018   CITS            M.Naito         Update          QC#18889
 * 01/19/2018   Hitachi         K.Ochiai        Update          QC#18889
 * 09/05/2023   Hitachi         K.Watanabe      Update          QC#53408
 *</pre>
 */
public class InstallCallMachineInfoBean {

    private String bypsPrfTechFlg;

    private String soNum;

    private String firstProdCtrlCd;

    private BigDecimal svcMachMstrPk;

    private String custMachCtrlNum;

    private String serNum;

    private String custTelNum;

    private String custTelExtnNum;

    private String svcCustAttnTxt;

    private String custEmlAddr;

    private String custPoNum;

    private String custPoDt;

    private String dsSvcCallTpCd;

    private String svcBillTpCd;

    private String svcTaskRcvDt;

    private String svcTaskRcvTm;

    private String svcTaskRcvTz;

    private String svcTaskSchdDt;

    private String svcTaskSchdTm;

    private String svcTaskSchdByUsrId;

    private String machDownFlg;

    private String prtChrgFlg;

    private String svcLborChrgFlg;

    private String pmtTermCashDiscCd;

    private String svcCallPrtyCd;

    private String techCd;

    private String schdDisptEmlFlg;

    private String techSchdFromDt;

    private String techSchdFromTm;

    private String erlStartTs;

    private String svcIstlCalGrpNum;

    private String actlDelyDt;

    private String actlDelyTm;

    private String rqstIstlDt;

    private String rqstIstlTm;

    private String sosEzUpUserId;

    private String dciiEzUpUserId1;

    private String dciiEzUpUserId2;

    private String billToAcctNum;

    private String cpoGlblCmpyCd;

    private String cpoOrdNum;

    private BigDecimal cpoConfigPk;

    private String cpoDtlLineNum;

    private String cpoDtlLineSubNum;

    private String svcCustAttnTxt1;

    private String rqstIstlDt1;

    private String rqstIstlTm1;

    private String techCd1;

    private String svcCustAttnTxt2;

    private String rqstIstlDt2;

    private String rqstIstlTm2;

    private String techCd2;

// START 2016/07/12 [QC#8423, ADD]
    private String svcCustCllrTxt;

    private String svcCustCllrTelNum;

    private String svcCustCllrTelExtnNum;
// END   2016/07/12 [QC#8423, ADD]

    // START 2016/08/24 [QC#13662,13678, ADD]
    private String shpgStsCd;
    // END 2016/08/24 [QC#13662,13678, ADD]

    // add start 2016/09/30 CSA Defect#14145
    private String svcExchFlg;

    private BigDecimal preSvcConfigMstrPk;

    private String machMdseCd;

    private BigDecimal dsCpoIstlInfoPk1;

    private BigDecimal dsCpoIstlInfoPk2;

    private BigDecimal svcExchMdlId;

    private String svcExchMdlNm;
    // add end 2016/09/30 CSA Defect#14145

    // START 2018/01/10 M.Naito [QC#18889, ADD]
    private String techMeetTruckFlg;

    private String svcByLineBizTpCd;

    private String schdDelyDt;

    private String schdDelyTm;
    // END 2018/01/10 M.Naito [QC#18889, ADD]
    // START 2018/01/19 K.Ochiai [QC#18889, ADD]
    private String ctryCd;

    private String postCd;
    // END 2018/01/19 K.Ochiai [QC#18889, ADD]

    // START 2023/09/05 K.Watanabe [QC#53408, ADD]
    private String addAsryFlg;
    // END 2023/09/05 K.Watanabe [QC#53408, ADD]

    public String getBypsPrfTechFlg() {
        return bypsPrfTechFlg;
    }

    public void setBypsPrfTechFlg(String bypsPrfTechFlg) {
        this.bypsPrfTechFlg = bypsPrfTechFlg;
    }

    public String getSoNum() {
        return soNum;
    }

    public void setSoNum(String soNum) {
        this.soNum = soNum;
    }

    public String getFirstProdCtrlCd() {
        return firstProdCtrlCd;
    }

    public void setFirstProdCtrlCd(String firstProdCtrlCd) {
        this.firstProdCtrlCd = firstProdCtrlCd;
    }

    public BigDecimal getSvcMachMstrPk() {
        return svcMachMstrPk;
    }

    public void setSvcMachMstrPk(BigDecimal svcMachMstrPk) {
        this.svcMachMstrPk = svcMachMstrPk;
    }

    public String getCustMachCtrlNum() {
        return custMachCtrlNum;
    }

    public void setCustMachCtrlNum(String custMachCtrlNum) {
        this.custMachCtrlNum = custMachCtrlNum;
    }

    public String getSerNum() {
        return serNum;
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    public String getCustTelNum() {
        return custTelNum;
    }

    public void setCustTelNum(String custTelNum) {
        this.custTelNum = custTelNum;
    }

    public String getCustTelExtnNum() {
        return custTelExtnNum;
    }

    public void setCustTelExtnNum(String custTelExtnNum) {
        this.custTelExtnNum = custTelExtnNum;
    }

    public String getSvcCustAttnTxt() {
        return svcCustAttnTxt;
    }

    public void setSvcCustAttnTxt(String svcCustAttnTxt) {
        this.svcCustAttnTxt = svcCustAttnTxt;
    }

    public String getCustEmlAddr() {
        return custEmlAddr;
    }

    public void setCustEmlAddr(String custEmlAddr) {
        this.custEmlAddr = custEmlAddr;
    }

    public String getCustPoNum() {
        return custPoNum;
    }

    public void setCustPoNum(String custPoNum) {
        this.custPoNum = custPoNum;
    }

    public String getCustPoDt() {
        return custPoDt;
    }

    public void setCustPoDt(String custPoDt) {
        this.custPoDt = custPoDt;
    }

    public String getDsSvcCallTpCd() {
        return dsSvcCallTpCd;
    }

    public void setDsSvcCallTpCd(String dsSvcCallTpCd) {
        this.dsSvcCallTpCd = dsSvcCallTpCd;
    }

    public String getSvcBillTpCd() {
        return svcBillTpCd;
    }

    public void setSvcBillTpCd(String svcBillTpCd) {
        this.svcBillTpCd = svcBillTpCd;
    }

    public String getSvcTaskRcvDt() {
        return svcTaskRcvDt;
    }

    public void setSvcTaskRcvDt(String svcTaskRcvDt) {
        this.svcTaskRcvDt = svcTaskRcvDt;
    }

    public String getSvcTaskRcvTm() {
        return svcTaskRcvTm;
    }

    public void setSvcTaskRcvTm(String svcTaskRcvTm) {
        this.svcTaskRcvTm = svcTaskRcvTm;
    }

    public String getSvcTaskRcvTz() {
        return svcTaskRcvTz;
    }

    public void setSvcTaskRcvTz(String svcTaskRcvTz) {
        this.svcTaskRcvTz = svcTaskRcvTz;
    }

    public String getSvcTaskSchdDt() {
        return svcTaskSchdDt;
    }

    public void setSvcTaskSchdDt(String svcTaskSchdDt) {
        this.svcTaskSchdDt = svcTaskSchdDt;
    }

    public String getSvcTaskSchdTm() {
        return svcTaskSchdTm;
    }

    public void setSvcTaskSchdTm(String svcTaskSchdTm) {
        this.svcTaskSchdTm = svcTaskSchdTm;
    }

    public String getSvcTaskSchdByUsrId() {
        return svcTaskSchdByUsrId;
    }

    public void setSvcTaskSchdByUsrId(String svcTaskSchdByUsrId) {
        this.svcTaskSchdByUsrId = svcTaskSchdByUsrId;
    }

    public String getMachDownFlg() {
        return machDownFlg;
    }

    public void setMachDownFlg(String machDownFlg) {
        this.machDownFlg = machDownFlg;
    }

    public String getPrtChrgFlg() {
        return prtChrgFlg;
    }

    public void setPrtChrgFlg(String prtChrgFlg) {
        this.prtChrgFlg = prtChrgFlg;
    }

    public String getSvcLborChrgFlg() {
        return svcLborChrgFlg;
    }

    public void setSvcLborChrgFlg(String svcLborChrgFlg) {
        this.svcLborChrgFlg = svcLborChrgFlg;
    }

    public String getPmtTermCashDiscCd() {
        return pmtTermCashDiscCd;
    }

    public void setPmtTermCashDiscCd(String pmtTermCashDiscCd) {
        this.pmtTermCashDiscCd = pmtTermCashDiscCd;
    }

    public String getSvcCallPrtyCd() {
        return svcCallPrtyCd;
    }

    public void setSvcCallPrtyCd(String svcCallPrtyCd) {
        this.svcCallPrtyCd = svcCallPrtyCd;
    }

    public String getTechCd() {
        return techCd;
    }

    public void setTechCd(String techCd) {
        this.techCd = techCd;
    }

    public String getSchdDisptEmlFlg() {
        return schdDisptEmlFlg;
    }

    public void setSchdDisptEmlFlg(String schdDisptEmlFlg) {
        this.schdDisptEmlFlg = schdDisptEmlFlg;
    }

    public String getTechSchdFromDt() {
        return techSchdFromDt;
    }

    public void setTechSchdFromDt(String techSchdFromDt) {
        this.techSchdFromDt = techSchdFromDt;
    }

    public String getTechSchdFromTm() {
        return techSchdFromTm;
    }

    public void setTechSchdFromTm(String techSchdFromTm) {
        this.techSchdFromTm = techSchdFromTm;
    }

    public String getErlStartTs() {
        return erlStartTs;
    }

    public void setErlStartTs(String erlStartTs) {
        this.erlStartTs = erlStartTs;
    }

    public String getActlDelyDt() {
        return actlDelyDt;
    }

    public void setActlDelyDt(String actlDelyDt) {
        this.actlDelyDt = actlDelyDt;
    }

    public String getActlDelyTm() {
        return actlDelyTm;
    }

    public void setActlDelyTm(String actlDelyTm) {
        this.actlDelyTm = actlDelyTm;
    }

    public String getRqstIstlDt() {
        return rqstIstlDt;
    }

    public void setRqstIstlDt(String rqstIstlDt) {
        this.rqstIstlDt = rqstIstlDt;
    }

    public String getRqstIstlTm() {
        return rqstIstlTm;
    }

    public void setRqstIstlTm(String rqstIstlTm) {
        this.rqstIstlTm = rqstIstlTm;
    }

    public String getSosEzUpUserId() {
        return sosEzUpUserId;
    }

    public void setSosEzUpUserId(String sosEzUpUserId) {
        this.sosEzUpUserId = sosEzUpUserId;
    }

    public String getDciiEzUpUserId1() {
        return dciiEzUpUserId1;
    }

    public void setDciiEzUpUserId1(String dciiEzUpUserId1) {
        this.dciiEzUpUserId1 = dciiEzUpUserId1;
    }

    public String getDciiEzUpUserId2() {
        return dciiEzUpUserId2;
    }

    public void setDciiEzUpUserId2(String dciiEzUpUserId2) {
        this.dciiEzUpUserId2 = dciiEzUpUserId2;
    }

    public String getBillToAcctNum() {
        return billToAcctNum;
    }

    public void setBillToAcctNum(String billToAcctNum) {
        this.billToAcctNum = billToAcctNum;
    }

    public String getCpoGlblCmpyCd() {
        return cpoGlblCmpyCd;
    }

    public void setCpoGlblCmpyCd(String cpoGlblCmpyCd) {
        this.cpoGlblCmpyCd = cpoGlblCmpyCd;
    }

    public String getCpoOrdNum() {
        return cpoOrdNum;
    }

    public void setCpoOrdNum(String cpoOrdNum) {
        this.cpoOrdNum = cpoOrdNum;
    }

    public BigDecimal getCpoConfigPk() {
        return cpoConfigPk;
    }

    public void setCpoConfigPk(BigDecimal cpoConfigPk) {
        this.cpoConfigPk = cpoConfigPk;
    }

    public String getCpoDtlLineNum() {
        return cpoDtlLineNum;
    }

    public void setCpoDtlLineNum(String cpoDtlLineNum) {
        this.cpoDtlLineNum = cpoDtlLineNum;
    }

    public String getCpoDtlLineSubNum() {
        return cpoDtlLineSubNum;
    }

    public void setCpoDtlLineSubNum(String cpoDtlLineSubNum) {
        this.cpoDtlLineSubNum = cpoDtlLineSubNum;
    }

    public String getSvcCustAttnTxt1() {
        return svcCustAttnTxt1;
    }

    public void setSvcCustAttnTxt1(String svcCustAttnTxt1) {
        this.svcCustAttnTxt1 = svcCustAttnTxt1;
    }

    public String getRqstIstlDt1() {
        return rqstIstlDt1;
    }

    public void setRqstIstlDt1(String rqstIstlDt1) {
        this.rqstIstlDt1 = rqstIstlDt1;
    }

    public String getRqstIstlTm1() {
        return rqstIstlTm1;
    }

    public void setRqstIstlTm1(String rqstIstlTm1) {
        this.rqstIstlTm1 = rqstIstlTm1;
    }

    public String getTechCd1() {
        return techCd1;
    }

    public void setTechCd1(String techCd1) {
        this.techCd1 = techCd1;
    }

    public String getSvcCustAttnTxt2() {
        return svcCustAttnTxt2;
    }

    public void setSvcCustAttnTxt2(String svcCustAttnTxt2) {
        this.svcCustAttnTxt2 = svcCustAttnTxt2;
    }

    public String getRqstIstlDt2() {
        return rqstIstlDt2;
    }

    public void setRqstIstlDt2(String rqstIstlDt2) {
        this.rqstIstlDt2 = rqstIstlDt2;
    }

    public String getRqstIstlTm2() {
        return rqstIstlTm2;
    }

    public void setRqstIstlTm2(String rqstIstlTm2) {
        this.rqstIstlTm2 = rqstIstlTm2;
    }

    public String getTechCd2() {
        return techCd2;
    }

    public void setTechCd2(String techCd2) {
        this.techCd2 = techCd2;
    }

    public String getSvcIstlCalGrpNum() {
        return svcIstlCalGrpNum;
    }

    public void setSvcIstlCalGrpNum(String svcIstlCalGrpNum) {
        this.svcIstlCalGrpNum = svcIstlCalGrpNum;
    }

// START 2016/07/12 [QC#8423, ADD]
    public String getSvcCustCllrTxt() {
        return svcCustCllrTxt;
    }

    public void setSvcCustCllrTxt(String svcCustCllrTxt) {
        this.svcCustCllrTxt = svcCustCllrTxt;
    }

    public String getSvcCustCllrTelNum() {
        return svcCustCllrTelNum;
    }

    public void setSvcCustCllrTelNum(String svcCustCllrTelNum) {
        this.svcCustCllrTelNum = svcCustCllrTelNum;
    }

    public String getSvcCustCllrTelExtnNum() {
        return svcCustCllrTelExtnNum;
    }

    public void setSvcCustCllrTelExtnNum(String svcCustCllrTelExtnNum) {
        this.svcCustCllrTelExtnNum = svcCustCllrTelExtnNum;
    }
// END   2016/07/12 [QC#8423, ADD]

    // START 2016/08/24 [QC#13662,13678, ADD]
    public String getShpgStsCd() {
        return shpgStsCd;
    }

    public void setShpgStsCd(String shpgStsCd) {
        this.shpgStsCd = shpgStsCd;
    }
    // END 2016/08/24 [QC#13662,13678, ADD]

    // add start 2016/09/30 CSA Defect#14145
    public String getSvcExchFlg() {
        return this.svcExchFlg;
    }

    public void setSvcExchFlg(String svcExchFlg) {
        this.svcExchFlg = svcExchFlg;
    }

    public BigDecimal getPreSvcConfigMstrPk() {
        return this.preSvcConfigMstrPk;
    }

    public void setPreSvcConfigMstrPk(BigDecimal preSvcConfigMstrPk) {
        this.preSvcConfigMstrPk = preSvcConfigMstrPk;
    }

    public String getMachMdseCd() {
        return machMdseCd;
    }

    public void setMachMdseCd(String machMdseCd) {
        this.machMdseCd = machMdseCd;
    }

    public BigDecimal getDsCpoIstlInfoPk1() {
        return dsCpoIstlInfoPk1;
    }

    public void setDsCpoIstlInfoPk1(BigDecimal dsCpoIstlInfoPk1) {
        this.dsCpoIstlInfoPk1 = dsCpoIstlInfoPk1;
    }

    public BigDecimal getDsCpoIstlInfoPk2() {
        return dsCpoIstlInfoPk2;
    }

    public void setDsCpoIstlInfoPk2(BigDecimal dsCpoIstlInfoPk2) {
        this.dsCpoIstlInfoPk2 = dsCpoIstlInfoPk2;
    }

    public BigDecimal getSvcExchMdlId() {
        return this.svcExchMdlId;
    }

    public void setSvcExchMdlId(BigDecimal svcExchMdlId) {
        this.svcExchMdlId = svcExchMdlId;
    }

    public String getSvcExchMdlNm() {
        return this.svcExchMdlNm;
    }

    public void setSvcExchMdlNm(String svcExchMdlNm) {
        this.svcExchMdlNm = svcExchMdlNm;
    }
    // add end 2016/09/30 CSA Defect#14145

    // START 2018/01/10 M.Naito [QC#18889, ADD]
    public String getTechMeetTruckFlg() {
        return this.techMeetTruckFlg;
    }

    public void setTechMeetTruckFlg(String techMeetTruckFlg) {
        this.techMeetTruckFlg = techMeetTruckFlg;
    }

    public String getSvcByLineBizTpCd() {
        return this.svcByLineBizTpCd;
    }

    public void setSvcByLineBizTpCd(String svcByLineBizTpCd) {
        this.svcByLineBizTpCd = svcByLineBizTpCd;
    }

    public String getSchdDelyDt() {
        return this.schdDelyDt;
    }

    public void setSchdDelyDt(String schdDelyDt) {
        this.schdDelyDt = schdDelyDt;
    }

    public String getSchdDelyTm() {
        return this.schdDelyTm;
    }

    public void setSchdDelyTm(String schdDelyTm) {
        this.schdDelyTm = schdDelyTm;
    }

    // START 2018/01/19 K.Ochiai [QC#18889, ADD]
    public String getCtryCd() {
        return this.ctryCd;
    }

    public void setCtryCd(String ctryCd) {
        this.ctryCd = ctryCd;
    }

    public String getPostCd() {
        return this.postCd;
    }

    public void setPostCd(String postCd) {
        this.postCd = postCd;
    }
    // END 2018/01/19 K.Ochiai [QC#18889, ADD]

    // START 2023/09/05 K.Watanabe [QC#53408, ADD]
    public String getAddAsryFlg() {
        return this.addAsryFlg;
    }

    public void setAddAsryFlg(String addAsryFlg) {
        this.addAsryFlg = addAsryFlg;
    }
    // END 2023/09/05 K.Watanabe [QC#53408, ADD]
}
