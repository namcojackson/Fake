/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0310;

import static business.blap.NSBL0310.constant.NSBL0310Constant.NSBM0002E;
import static business.blap.NSBL0310.constant.NSBL0310Constant.NSBM0009W;
import static business.blap.NSBL0310.constant.NSBL0310Constant.DS_ORG_UNIT;
import static business.blap.NSBL0310.constant.NSBL0310Constant.NSBM0025E;
import static business.blap.NSBL0310.constant.NSBL0310Constant.ORG_CD;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSBL0310.common.NSBL0310CommonLogic;
import business.db.DS_ORG_UNITTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MGR_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Service Request By Manager
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/18   Hitachi         T.Harada        Create          N/A
 * 2019/03/28   Hitachi         K.Kitamura      Update          CSA QC#30906
 *</pre>
 */
public class NSBL0310BL02 extends S21BusinessHandler {

    /**
     * Method name: doProcess <dd>The method explanation: Call each
     * process by screen id. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            // +++++ [START] : Programming Area +++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

          NSBL0310CMsg bizMsg = (NSBL0310CMsg) cMsg;
          NSBL0310SMsg shareMsg = (NSBL0310SMsg) sMsg;

            if ("NSBL0310_INIT".equals(screenAplID)) {
                doProcess_NSBL0310_INIT(bizMsg, shareMsg);

            } else if ("NSBL0310Scrn00_Search".equals(screenAplID)) {
                doProcess_NSBL0310Scrn00_Search(bizMsg, shareMsg);

            } else if ("NSBL0310Scrn00_Search_SvcGrp".equals(screenAplID)) {
                doProcess_NSBL0310Scrn00_Search_SvcGrp(bizMsg, shareMsg);

            } else if ("NSBL0310Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSBL0310Scrn00_PageNext(bizMsg, shareMsg);

            } else if ("NSBL0310Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSBL0310Scrn00_PagePrev(bizMsg, shareMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Programming Area ++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_NSBL0310_INIT
     * @param bizMsg NSBL0310CMsg
     * @param shareMsg NSBL0310SMsg
     */
    private void doProcess_NSBL0310_INIT(NSBL0310CMsg bizMsg, NSBL0310SMsg shareMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(bizMsg.svcMgrTpCd_HD, SVC_MGR_TP.MACHINE_MANAGER);

    }

    /**
     * Method name: doProcess_NSBL0310Scrn00_Search
     * @param bizMsg NSBL0310CMsg
     * @param shareMsg NSBL0310SMsg
     */
    private void doProcess_NSBL0310Scrn00_Search(NSBL0310CMsg bizMsg, NSBL0310SMsg shareMsg) {

        DS_ORG_UNITTMsg result = NSBL0310CommonLogic.isExistSvcGrp(bizMsg);
        if (result == null) {
            bizMsg.orgCd_HT.setErrorInfo(1, NSBM0025E, new String[] {ORG_CD, DS_ORG_UNIT });
            return;
        }
        setValue(bizMsg.orgCd_HL, bizMsg.orgCd_HT);
        setValue(bizMsg.svcMgrTpCd_HD, bizMsg.svcMgrTpCd_H);

        setValue(bizMsg.orgDescTxt_H, result.orgNm);

        BigDecimal layerNum = NSBL0310Query.getInstance().getLayerNum(bizMsg);

        ZYPEZDItemValueSetter.setValue(bizMsg.orgLayerNum, layerNum);

        searchDetail(bizMsg, shareMsg, layerNum);

    }

    private void searchDetail(NSBL0310CMsg bizMsg, NSBL0310SMsg shareMsg, BigDecimal layerNum) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(shareMsg.A);

        EZDMsg.copy(bizMsg, null, shareMsg, null);

        S21SsmEZDResult rsSum = NSBL0310Query.getInstance().getOpenSvcTaskSmry(bizMsg, shareMsg, layerNum);

        if (rsSum.isCodeNotFound()) {
            bizMsg.setMessageInfo(NSBM0002E);
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.prevOpenTaskCnt_T, shareMsg.A.no(0).prevOpenTaskCnt_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.inprocTaskCnt_T, shareMsg.A.no(0).inprocTaskCnt_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.cloTaskCnt_T, shareMsg.A.no(0).cloTaskCnt_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.cancTaskCnt_T, shareMsg.A.no(0).cancTaskCnt_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.rejTaskCnt_T, shareMsg.A.no(0).rejTaskCnt_A);
        ZYPEZDItemValueSetter.setValue(shareMsg.xxRcvTm_T, shareMsg.A.no(0).xxRcvTm_A);
        // START 2019/03/28 K.Kitamura [QC#30233, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.toBeOptmTaskCnt_T, shareMsg.A.no(0).toBeOptmTaskCnt_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.schdTaskCnt_T, shareMsg.A.no(0).schdTaskCnt_A);
        // END 2019/03/28 K.Kitamura [QC#30233, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.asgTaskCnt_T, shareMsg.A.no(0).asgTaskCnt_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.openTaskCnt_T, shareMsg.A.no(0).openTaskCnt_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.hldTaskCnt_T, shareMsg.A.no(0).hldTaskCnt_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.esclTaskCnt_T, shareMsg.A.no(0).esclTaskCnt_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.vipTaskCnt_T, shareMsg.A.no(0).vipTaskCnt_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.attnTaskCnt_T, shareMsg.A.no(0).attnTaskCnt_A);

        ZYPTableUtil.clear(shareMsg.A);
        S21SsmEZDResult rs = NSBL0310Query.getInstance().getOpenSvcTask(bizMsg, shareMsg, layerNum);

        if (rs.isCodeNotFound()) {
            EZDMsg.copy(shareMsg.A, null, bizMsg.A, null);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRcvTm_T, shareMsg.xxRcvTm_T);
            bizMsg.setMessageInfo(NSBM0002E);
            return;
        }

        if (rs.getQueryResultCount() > shareMsg.A.length()) {
            bizMsg.setMessageInfo(NSBM0009W, new String[] {String.valueOf(shareMsg.A.length()) });
        }

        NSBL0310CommonLogic.sumResult(shareMsg);
        NSBL0310CommonLogic.managerFormat(shareMsg);
        NSBL0310CommonLogic.hhmmFormat(shareMsg);

        EZDMsg.copy(shareMsg.A, null, bizMsg.A, null);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRcvTm_T, shareMsg.xxRcvTm_T);

        bizMsg.xxPageShowFromNum.setValue(1);
        bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(shareMsg.A.getValidCount());

    }

    /**
     * Method name: doProcess_NSBL0310Scrn00_Search_SvcGrp
     * @param bizMsg NSBL0310CMsg
     * @param shareMsg NSBL0310SMsg
     */
    private void doProcess_NSBL0310Scrn00_Search_SvcGrp(NSBL0310CMsg bizMsg, NSBL0310SMsg shareMsg) {

        DS_ORG_UNITTMsg result = NSBL0310CommonLogic.isExistSvcGrp(bizMsg);
        if (result == null) {
            bizMsg.orgCd_HT.setErrorInfo(1, NSBM0025E, new String[] {ORG_CD, DS_ORG_UNIT });
            return;
        }
        setValue(bizMsg.orgDescTxt_H, result.orgNm);
    }

    private void doProcess_NSBL0310Scrn00_PageNext(NSBL0310CMsg bizMsg, NSBL0310SMsg shareMsg) {

        NSBL0310CommonLogic.copyBizToShare(bizMsg, shareMsg);

        ZYPTableUtil.clear(bizMsg.A);

        int pagenationFrom = bizMsg.xxPageShowToNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < shareMsg.A.getValidCount()) {
                EZDMsg.copy(shareMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());
    }

    private void doProcess_NSBL0310Scrn00_PagePrev(NSBL0310CMsg bizMsg, NSBL0310SMsg shareMsg) {

        NSBL0310CommonLogic.copyBizToShare(bizMsg, shareMsg);

        ZYPTableUtil.clear(bizMsg.A);

        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length() - 1;
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < shareMsg.A.getValidCount()) {
                EZDMsg.copy(shareMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        pagenationFrom = pagenationFrom + 1;
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount() - 1);
    }

}
