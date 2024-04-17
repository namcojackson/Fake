/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC150001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MDL_TP;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 * <pre>
 * NWXC150001SvcMdlFuncParamBean: Service Model Function Parameter Bean

 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/20   Fujitsu         S.Takami        Update          S21_NA#25604
 * </pre>
 */
public class NWXC150001SvcMdlFuncParamBean {

    /** */
    private String glblCmpyCd = null;

    /** */
    private String slsDt = null;

    /** */
    private ONBATCH_TYPE onBatchType;

    /** */
    private BigDecimal svcConfigMstrPk = null;

    /** */
    private List<String> outBndChildMdseCdList = new ArrayList<String>(0);

    /** */
    private List<NWXC150001SvcMdlFuncInbndChildBean> inBndChildBeanList = new ArrayList<NWXC150001SvcMdlFuncInbndChildBean>(0);

    /** */
    private String prntMdseCd = null;

    /** */
    private String baseCmptMdseCd = null;

    /** */
    private BigDecimal mdlId = null;

    /** */
    private String mdlNm = null;

    /** */
    private String mdlDescTxt = null;

    /** */
    private String svcMdlTpCd = null;

    /** */
    private List<String> errMsgIdList = new ArrayList<String>(0);

    /**
     * @return global campany code
     */
    public  String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /**
     * @param glblCmpyCd global campany code
     */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    /**
     * @return sales date
     */
    public String getSlsDt() {
        return slsDt;
    }

    /**
     * @param slsDt sale date
     */
    public void setSlsDt(String slsDt) {
        this.slsDt = slsDt;
    }

    /**
     * @return on-batch type
     */
    public ONBATCH_TYPE getOnBatchType() {
        return onBatchType;
    }

    /**
     * @param onBatchType on-batch type
     */
    public void setOnBatchType(ONBATCH_TYPE onBatchType) {
        this.onBatchType = onBatchType;
    }

    /**
     * @return service machine master pk
     */
    public BigDecimal getSvcConfigMstrPk() {
        return svcConfigMstrPk;
    }

    /**
     * @param svcConfigMstrPk service machine master pk
     */
    public void setSvcConfigMstrPk(BigDecimal svcConfigMstrPk) {
        this.svcConfigMstrPk = svcConfigMstrPk;
    }

    /**
     * @return outbound child mdse code list
     */
    public List<String> getOutBndChildMdseCdList() {
        return outBndChildMdseCdList;
    }

    /**
     * @param outBndChildMdseCdList outbound child mdse code list
     */
    public void setOutBndChildMdseCdList(List<String> outBndChildMdseCdList) {
        this.outBndChildMdseCdList = outBndChildMdseCdList;
    }

    /**
     * @param outBndChildMdseCd outbound child mdse code
     */
    public void addOutBndChildMdseCdList(String outBndChildMdseCd) {
        if (this.outBndChildMdseCdList == null) {
            this.outBndChildMdseCdList = new ArrayList<String>();
        }
        this.outBndChildMdseCdList.add(outBndChildMdseCd);
    }
    /**
     * @return in-bound child mdse code list
     */
    public List<NWXC150001SvcMdlFuncInbndChildBean> getInBndChildBeanList() {
        return inBndChildBeanList;
    }

    /**
     * @param inBndChildBeanList in-bound child mdse code list
     */
    public void setInBndChildBeanList(List<NWXC150001SvcMdlFuncInbndChildBean> inBndChildBeanList) {
        this.inBndChildBeanList = inBndChildBeanList;
    }

    /**
     * @param mdseCd merchandise code
     * @param serNum serial number
     * @param svcMachMstrPk service machine master pk
     */
    public void addInBndChi(String mdseCd, String serNum, BigDecimal svcMachMstrPk) {
        if (this.inBndChildBeanList == null) {
            this.inBndChildBeanList = new ArrayList<NWXC150001SvcMdlFuncInbndChildBean>();
        }
        NWXC150001SvcMdlFuncInbndChildBean inBndChildBean = new NWXC150001SvcMdlFuncInbndChildBean();
        inBndChildBean.setMdseCd(mdseCd);
        inBndChildBean.setSerNZum(serNum);
        inBndChildBean.setSvcMachMstrPk(svcMachMstrPk);

        this.inBndChildBeanList.add(inBndChildBean);
    }
    /**
     * @return parent mdse code
     */
    public String getPrntMdseCd() {
        return prntMdseCd;
    }

    /**
     * @param prntMdseCd parent mdse code
     */
    public void setPrntMdseCd(String prntMdseCd) {
        this.prntMdseCd = prntMdseCd;
    }

    /**
     * @return base component mdse code
     */
    public String getBaseCmptMdseCd() {
        return baseCmptMdseCd;
    }

    /**
     * @param baseCmptMdseCd base component mdse code
     */
    public void setBaseCmptMdseCd(String baseCmptMdseCd) {
        this.baseCmptMdseCd = baseCmptMdseCd;
    }

    /**
     * @return model id
     */
    public BigDecimal getMdlId() {
        return mdlId;
    }

    /**
     * @param mdlId model id
     */
    public void setMdlId(BigDecimal mdlId) {
        this.mdlId = mdlId;
    }

    /**
     * @return model name
     */
    public String getMdlNm() {
        return mdlNm;
    }

    /**
     * @param mdlNm model name
     */
    public void setMdlNm(String mdlNm) {
        this.mdlNm = mdlNm;
    }

    /**
     * @return model description text
     */
    public String getMdlDescTxt() {
        return mdlDescTxt;
    }

    /**
     * @param mdlDescTxt model description text
     */
    public void setMdlDescTxt(String mdlDescTxt) {
        this.mdlDescTxt = mdlDescTxt;
    }

    /**
     * @return service model type
     */
    public String getSvcMdlTpCd() {
        return svcMdlTpCd;
    }

    /**
     * @param svcMdlTpCd service model type
     */
    public void setSvcMdlTpCd(String svcMdlTpCd) {
        this.svcMdlTpCd = svcMdlTpCd;
    }

    /**
     * @return true: software mode false: not software model
     */
    public boolean isSvcMdlTpSoftWare() {
        return S21StringUtil.isEquals(SVC_MDL_TP.SOFTWARE, this.svcMdlTpCd);
    }

    /**
     * @return error message id list
     */
    public List<String> getErrMsgIdList() {
        return errMsgIdList;
    }

    /**
     * @param errMsgIdList error message id list
     */
    public void setErrMsgIdList(List<String> errMsgIdList) {
        this.errMsgIdList = errMsgIdList;
    }

    /**
     * @param errMsgId error message id
     */
    public void addErrMsgIdList(String errMsgId) {
        if (this.errMsgIdList == null) {
            this.errMsgIdList = new ArrayList<String>(0);
        }
        this.errMsgIdList.add(errMsgId);
    }
}
