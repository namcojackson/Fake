/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.SVC_COV_FEAT;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

import business.db.MDSETMsg;
import business.db.SVC_BILL_TPTMsg;

/**
 * <pre>
 * Get Coverage Template Info. 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/14/2015   Hitachi         T.Tomita        Create          N/A
 * 11/17/2015   Hitachi         A.Kohinata      Update          N/A
 * 01/12/2016   Hitachi         K.Kasai         Update          N/A
 * 04/28/2016   Hitachi         T.Tomita        Update          QC#7739
 * </pre>
 */
public class NSXC001001GetCovTmpl {

    // add start 2016/04/28 CSA Defect#7739
    /**
     * SSM Batch Client
     */
    private static final S21SsmBatchClient SSM_CLIENT = S21SsmBatchClient.getClient(NSXC001001GetCovTmpl.class);
    // add end 2016/04/28 CSA Defect#7739

    
    /**
     * get Coverage Template
     * @param covTmplInfo CovTmplInfo
     */
    public void getCovTmpl(CovTmplInfo covTmplInfo) {
        // input check
        if (isErrorInputItem(covTmplInfo)) {
            return;
        }

        // get SVC_COV_TMPL_TP_CD
        String svcCovTmplTpCd = getSvcCovTmplTpCd(covTmplInfo.getGlblCmpyCd(), covTmplInfo.getSvcPgmMdseCd());
        if (!hasValue(svcCovTmplTpCd)) {
            return;
        }

        // mod start 2016/04/28 CSA Defect#7739
        // get SVC_COV_TMPL_DTL
        List<Map<String, Object>> svcCovTmplDtlList = getSvcCovTmplDtlList(covTmplInfo.getGlblCmpyCd(), svcCovTmplTpCd, covTmplInfo.getSlsDt());
        List<OutputCovTmplInfo> outputList = new ArrayList<OutputCovTmplInfo>();
        OutputCovTmplInfo output;
        String billCd = null;
        for (Map<String, Object> svcCovTmplDtl : svcCovTmplDtlList) {
            output = new OutputCovTmplInfo();
            output.setSvcCovFeatCd((String) svcCovTmplDtl.get("SVC_COV_FEAT_CD"));
            output.setSvcCovDtlValTxt((String) svcCovTmplDtl.get("SVC_COV_DTL_VAL_TXT"));
            outputList.add(output);
            if (SVC_COV_FEAT.BILL_CD.equals((String) svcCovTmplDtl.get("SVC_COV_FEAT_CD"))) {
                billCd = (String) svcCovTmplDtl.get("SVC_COV_DTL_VAL_TXT");
            }
        }
        // mod end 2016/04/28 CSA Defect#7739

        // update SVC_BILL_TP attribute
        if (hasValue(billCd)) {
            SVC_BILL_TPTMsg svcBillTpTMsg = getSvcBillTp(covTmplInfo.getGlblCmpyCd(), billCd);
            if (svcBillTpTMsg != null) {
                updateOutputList(outputList, svcBillTpTMsg);
            }
        }

        // set Output List
        covTmplInfo.setOutputCovTmplInfoList(outputList);
    }

    public boolean isSuplIncl(CovTmplInfo covTmplInfo) {
        boolean isSuplIncl = false;

        getCovTmpl(covTmplInfo);
        if (covTmplInfo.getOutputCovTmplInfoList() == null || covTmplInfo.getOutputCovTmplInfoList().isEmpty()) {
            return false;
        }

        for (OutputCovTmplInfo out : covTmplInfo.getOutputCovTmplInfoList()) {
            if (SVC_COV_FEAT.SPLY_INCL.equals(out.getSvcCovFeatCd())
                    && FLG_ON_Y.equals(out.getSvcCovDtlValTxt())) {
                isSuplIncl = true;
                break;
            }
        }
        return isSuplIncl;
    }

    // START 2016/01/12 K.Kasai [N/A, ADD]
    public boolean isLaserPlusContr(CovTmplInfo covTmplInfo) {
        boolean isLaserPlusContr = false;

        getCovTmpl(covTmplInfo);
        if (covTmplInfo.getOutputCovTmplInfoList() == null || covTmplInfo.getOutputCovTmplInfoList().isEmpty()) {
            return false;
        }

        for (OutputCovTmplInfo out : covTmplInfo.getOutputCovTmplInfoList()) {
            if (SVC_COV_FEAT.LASER_PLUS_CONTR.equals(out.getSvcCovFeatCd())
                    && FLG_ON_Y.equals(out.getSvcCovDtlValTxt())) {
                isLaserPlusContr = true;
                break;
            }
        }
        return isLaserPlusContr;
    }
    // END 2016/01/12 K.Kasai [N/A, ADD]

    // START 2015/11/17 A.Kohinata [N/A, DEL]
    public boolean isMps(CovTmplInfo covTmplInfo) {
        boolean isMps = false;

        getCovTmpl(covTmplInfo);
        if (covTmplInfo.getOutputCovTmplInfoList() == null || covTmplInfo.getOutputCovTmplInfoList().isEmpty()) {
            return false;
        }

        for (OutputCovTmplInfo out : covTmplInfo.getOutputCovTmplInfoList()) {
            if (SVC_COV_FEAT.MPS.equals(out.getSvcCovFeatCd())
                    && FLG_ON_Y.equals(out.getSvcCovDtlValTxt())) {
                isMps = true;
                break;
            }
        }
        return isMps;
    }

    public boolean isOptima(CovTmplInfo covTmplInfo) {
        boolean isOptima = false;

        getCovTmpl(covTmplInfo);
        if (covTmplInfo.getOutputCovTmplInfoList() == null || covTmplInfo.getOutputCovTmplInfoList().isEmpty()) {
            return false;
        }

        for (OutputCovTmplInfo out : covTmplInfo.getOutputCovTmplInfoList()) {
            if (SVC_COV_FEAT.OPTIMA.equals(out.getSvcCovFeatCd())
                    && FLG_ON_Y.equals(out.getSvcCovDtlValTxt())) {
                isOptima = true;
                break;
            }
        }
        return isOptima;
    }
    // END 2015/11/17 A.Kohinata [N/A, DEL]

    private boolean isErrorInputItem(CovTmplInfo covTmplInfo) {
        if (!hasValue(covTmplInfo.getGlblCmpyCd())) {
            return true;
        }

        if (!hasValue(covTmplInfo.getSvcPgmMdseCd())) {
            return true;
        }

        if (!hasValue(covTmplInfo.getSlsDt())) {
            return true;
        }

        return false;
    }

    private String getSvcCovTmplTpCd(String glblCmpyCd, String mdseCd) {
        MDSETMsg inMsg = new MDSETMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.mdseCd, mdseCd);
        MDSETMsg rtnMsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(inMsg);
        String svcCovTmplTpCd = null;
        if (rtnMsg != null) {
            svcCovTmplTpCd = rtnMsg.svcCovTmplTpCd.getValue();
        }
        return svcCovTmplTpCd;
    }

    // mod start 2016/04/28 CSA Defect#7739
    private List<Map<String, Object>> getSvcCovTmplDtlList(String glblCmpyCd, String svcCovTmplTpCd, String slsDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCovTmplTpCd", svcCovTmplTpCd);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("maxDt", "99991231");

        return (List<Map<String, Object>>) SSM_CLIENT.queryObjectList("getSvcCovTmplDtl", ssmParam);
    }
    // mod end 2016/04/28 CSA Defect#7739

    private SVC_BILL_TPTMsg getSvcBillTp(String glblCmpyCd, String svcBillTpCd) {
        SVC_BILL_TPTMsg inMsg = new SVC_BILL_TPTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.svcBillTpCd, svcBillTpCd);
        return (SVC_BILL_TPTMsg) S21CodeTableAccessor.findByKey(inMsg);
    }

    private void updateOutputList(List<OutputCovTmplInfo> outputList, SVC_BILL_TPTMsg svcBillTpTMsg) {
        for (OutputCovTmplInfo output : outputList) {
            if (SVC_COV_FEAT.LBOR_INCL.equals(output.getSvcCovFeatCd())) {
                output.setSvcCovDtlValTxt(changFlg(svcBillTpTMsg.lborChrgFlg.getValue()));
            } else if (SVC_COV_FEAT.PARTS_INCL.equals(output.getSvcCovFeatCd())) {
                output.setSvcCovDtlValTxt(changFlg(svcBillTpTMsg.prtChrgFlg.getValue()));
            } else if (SVC_COV_FEAT.DRUM_INCL.equals(output.getSvcCovFeatCd())) {
                output.setSvcCovDtlValTxt(changFlg(svcBillTpTMsg.drumChrgFlg.getValue()));
            } else if (SVC_COV_FEAT.EXP_INCL.equals(output.getSvcCovFeatCd())) {
                output.setSvcCovDtlValTxt(changFlg(svcBillTpTMsg.expChrgFlg.getValue()));
            }
        }
    }

    private String changFlg(String flg) {
        if (!hasValue(flg)) {
            return null;
        }
        if (FLG_OFF_N.equals(flg)) {
            return FLG_ON_Y;
        } else if (FLG_ON_Y.equals(flg))  {
            return FLG_OFF_N;
        }
        return null;
    }
}
