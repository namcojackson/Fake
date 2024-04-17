/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0330;

import static business.blap.NSBL0330.constant.NSBL0330Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NSBL0330.common.NSBL0330CommonLogic;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_RQST_CRIT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Service Request By Summary Criteria
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/02   Hitachi         T.Tsuchida      Create          N/A
 * 2016/10/18   Hitachi         A.Kohinata      Update          CSA QC#11030
 *</pre>
 */
public class NSBL0330BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NSBL0330CMsg bizMsg = (NSBL0330CMsg) cMsg;
            NSBL0330SMsg shareMsg = (NSBL0330SMsg) sMsg;

            if ("NSBL0330_INIT".equals(screenAplID)) {
                doProcess_NSBL0330_INIT(bizMsg, shareMsg);
            } else if ("NSBL0330Scrn00_NextMgr".equals(screenAplID)) {
                doProcess_NSBL0330Scrn00_NextMgr(bizMsg, shareMsg);
            } else if ("NSBL0330Scrn00_PrevMgr".equals(screenAplID)) {
                doProcess_NSBL0330Scrn00_PrevMgr(bizMsg, shareMsg);
            } else if ("NSBL0330Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSBL0330Scrn00_PageNext(bizMsg, shareMsg);
            } else if ("NSBL0330Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSBL0330Scrn00_PagePrev(bizMsg, shareMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0330_INIT(NSBL0330CMsg cMsg, NSBL0330SMsg sMsg) {

        setInitValue(cMsg);

        if (!NSBL0330CommonLogic.checkMandatory(cMsg)) {
            return;
        }

        if (!getMgrList(cMsg, sMsg)) {
            return;
        }

        NSBL0330CommonLogic.setInitPagenationInfoW(cMsg, sMsg.A.getValidCount());

        setValue(cMsg.xxScrEventNm, EVENT_NM_INIT);
        search(cMsg, sMsg);
    }

    private void doProcess_NSBL0330Scrn00_NextMgr(NSBL0330CMsg cMsg, NSBL0330SMsg sMsg) {
        NSBL0330CommonLogic.setNextMgrPagenationInfoH(cMsg);
        setValue(cMsg.xxScrEventNm, EVENT_NM_NEXT_MGR);
        search(cMsg, sMsg);
    }

    private void doProcess_NSBL0330Scrn00_PrevMgr(NSBL0330CMsg cMsg, NSBL0330SMsg sMsg) {
        NSBL0330CommonLogic.setPrevMgrPagenationInfoH(cMsg);
        setValue(cMsg.xxScrEventNm, EVENT_NM_PREV_MGR);
        search(cMsg, sMsg);
    }

    private void doProcess_NSBL0330Scrn00_PageNext(NSBL0330CMsg cMsg, NSBL0330SMsg sMsg) {
        setValue(cMsg.xxScrEventNm, EVENT_NM_PAGE_NEXT);
        search(cMsg, sMsg);
    }

    private void doProcess_NSBL0330Scrn00_PagePrev(NSBL0330CMsg cMsg, NSBL0330SMsg sMsg) {
        setValue(cMsg.xxScrEventNm, EVENT_NM_PAGE_PREV);
        search(cMsg, sMsg);
    }

    private void setInitValue(NSBL0330CMsg cMsg) {
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.xxPageShowFromNum_H, BigDecimal.ZERO);
        setValue(cMsg.xxPageShowToNum_H, BigDecimal.ZERO);
        setValue(cMsg.xxPageShowOfNum_H, BigDecimal.ZERO);
        setValue(cMsg.xxPageShowFromNum_W, BigDecimal.ZERO);
        setValue(cMsg.xxPageShowToNum_W, BigDecimal.ZERO);
        setValue(cMsg.xxPageShowOfNum_W, BigDecimal.ZERO);
        BigDecimal srchSrMgrNum = ZYPCodeDataUtil.getNumConstValue(SRCH_SR_MGR_NUM, cMsg.glblCmpyCd.getValue());
        if (!hasValue(srchSrMgrNum)) {
            srchSrMgrNum = new BigDecimal(cMsg.A.length());
        }
        setValue(cMsg.xxPageShowTotNum_W, srchSrMgrNum);
    }

    private boolean getMgrList(NSBL0330CMsg cMsg, NSBL0330SMsg sMsg) {

        ZYPTableUtil.clear(sMsg.A);

        S21SsmEZDResult ssmResult = NSBL0330Query.getInstance().getMgrList(cMsg, sMsg);
        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NZZM0000E);
            return false;
        }
        if (ssmResult.getQueryResultCount() > sMsg.A.length()) {
            cMsg.setMessageInfo(NZZM0001W);
        }

        return true;
    }

    private void search(NSBL0330CMsg cMsg, NSBL0330SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);

        List<String> mgrList = createMgrListForShowPage(cMsg, sMsg);
        if (mgrList == null || mgrList.size() == 0) {
            cMsg.setMessageInfo(NZZM0000E);
            return;
        }

        List<Map<String, Object>> drillDownMapList = getDrillDownMapList(cMsg, mgrList);
        if (drillDownMapList == null || drillDownMapList.size() == 0) {
            cMsg.setMessageInfo(NZZM0000E);
            return;
        }

        if (cMsg.xxScrEventNm.getValue().equals(EVENT_NM_INIT) || cMsg.xxScrEventNm.getValue().equals(EVENT_NM_PREV_MGR) || cMsg.xxScrEventNm.getValue().equals(EVENT_NM_NEXT_MGR)) {
            NSBL0330CommonLogic.setInitPagenationInfoH(cMsg, drillDownMapList.size());
        } else if (cMsg.xxScrEventNm.getValue().equals(EVENT_NM_PAGE_PREV)) {
            NSBL0330CommonLogic.setPagePrevPagenationInfoH(cMsg, drillDownMapList.size());
        } else if (cMsg.xxScrEventNm.getValue().equals(EVENT_NM_PAGE_NEXT)) {
            NSBL0330CommonLogic.setPageNextPagenationInfoH(cMsg, drillDownMapList.size());
        }

        List<Map<String, Object>> searchList = getSearchList(cMsg, mgrList, drillDownMapList);
        if (searchList == null || searchList.size() == 0) {
            return;
        }

        setSearchResult(cMsg, searchList, mgrList.size());
    }

    private List<String> createMgrListForShowPage(NSBL0330CMsg cMsg, NSBL0330SMsg sMsg) {
        int pageShowFromNum = cMsg.xxPageShowFromNum_W.getValueInt();
        int pageShowToNum = cMsg.xxPageShowToNum_W.getValueInt();
        int listSize = pageShowToNum - pageShowFromNum + 1;
        List<String> mgrList = new ArrayList<String>(listSize);
        for (int i = pageShowFromNum - 1; i < pageShowToNum; i++) {
            mgrList.add(sMsg.A.no(i).svcMgrPsnCd.getValue());
        }
        return mgrList;
    }

    private List<Map<String, Object>> getDrillDownMapList(NSBL0330CMsg cMsg, List<String> mgrList) {
        S21SsmEZDResult ssmResult = NSBL0330Query.getInstance().getDrillDownList(cMsg, mgrList);
        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NZZM0000E);
            return null;
        }
        return (List<Map<String, Object>>) ssmResult.getResultObject();
    }

    private List<Map<String, Object>> getSearchList(NSBL0330CMsg cMsg, List<String> mgrList, List<Map<String, Object>> drillDownMapList) {

        String svcRqstCritTpCd = cMsg.svcRqstCritTpCd_I.getValue();
        int listSize = cMsg.xxPageShowToNum_H.getValueInt() - cMsg.xxPageShowFromNum_H.getValueInt() + 1;
        S21SsmEZDResult ssmResult = null;

        if (svcRqstCritTpCd.equals(SVC_RQST_CRIT_TP.TASK_TYPES)) {
            List<String> dsSvcCallTpCdList = new ArrayList<String>(listSize);
            setDrillDownMapListForShowPage(cMsg, drillDownMapList, dsSvcCallTpCdList, null, null, null);
            ssmResult = NSBL0330Query.getInstance().getSearchList(cMsg, mgrList, dsSvcCallTpCdList, null, null, null);
        } else if (svcRqstCritTpCd.equals(SVC_RQST_CRIT_TP.SR_OR_TASK_STATUS)) {
            List<BigDecimal> fsrSvcTaskStsRelnPkList = new ArrayList<BigDecimal>(listSize);
            setDrillDownMapListForShowPage(cMsg, drillDownMapList, null, fsrSvcTaskStsRelnPkList, null, null);
            ssmResult = NSBL0330Query.getInstance().getSearchList(cMsg, mgrList, null, fsrSvcTaskStsRelnPkList, null, null);
        } else if (svcRqstCritTpCd.equals(SVC_RQST_CRIT_TP.ASSIGNEE)) {
            List<String> techCdList = new ArrayList<String>(listSize);
            setDrillDownMapListForShowPage(cMsg, drillDownMapList, null, null, techCdList, null);
            ssmResult = NSBL0330Query.getInstance().getSearchList(cMsg, mgrList, null, null, techCdList, null);
        } else if (svcRqstCritTpCd.equals(SVC_RQST_CRIT_TP.CANNEL)) {
            List<String> svcCallSrcTpCdList = new ArrayList<String>(listSize);
            setDrillDownMapListForShowPage(cMsg, drillDownMapList, null, null, null, svcCallSrcTpCdList);
            ssmResult = NSBL0330Query.getInstance().getSearchList(cMsg, mgrList, null, null, null, svcCallSrcTpCdList);
        }

        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NZZM0000E);
            return null;
        }

        return (List<Map<String, Object>>) ssmResult.getResultObject();
    }

    private void setDrillDownMapListForShowPage(NSBL0330CMsg cMsg, List<Map<String, Object>> drillDownMapList, List<String> dsSvcCallTpCdList, List<BigDecimal> fsrSvcTaskStsRelnPkList, List<String> techCdList,
            List<String> svcCallSrcTpCdList) {
        String svcRqstCritTpCd = cMsg.svcRqstCritTpCd_I.getValue();
        int pageShowFromNum = cMsg.xxPageShowFromNum_H.getValueInt();
        int pageShowToNum = cMsg.xxPageShowToNum_H.getValueInt();
        for (int i = pageShowFromNum - 1; i < pageShowToNum; i++) {
            Map<String, Object> drillDownMap = drillDownMapList.get(i);
            if (svcRqstCritTpCd.equals(SVC_RQST_CRIT_TP.TASK_TYPES)) {
                dsSvcCallTpCdList.add((String) drillDownMap.get("DS_SVC_CALL_TP_CD"));
            } else if (svcRqstCritTpCd.equals(SVC_RQST_CRIT_TP.SR_OR_TASK_STATUS)) {
                fsrSvcTaskStsRelnPkList.add((BigDecimal) drillDownMap.get("FSR_SVC_TASK_STS_RELN_PK"));
            } else if (svcRqstCritTpCd.equals(SVC_RQST_CRIT_TP.ASSIGNEE)) {
                techCdList.add((String) drillDownMap.get("TECH_CD"));
            } else if (svcRqstCritTpCd.equals(SVC_RQST_CRIT_TP.CANNEL)) {
                svcCallSrcTpCdList.add((String) drillDownMap.get("SVC_CALL_SRC_TP_CD"));
            }
        }
    }

    private void setSearchResult(NSBL0330CMsg cMsg, List<Map<String, Object>> searchList, int mgrListSize) {
        String svcRqstCritTpCd = cMsg.svcRqstCritTpCd_I.getValue();
        String suffix;
        int mgrIdx = 0;
        int sfxIdx = 0;
        for (Map<String, Object> search : searchList) {
            setValue(cMsg.A.no(mgrIdx).xxScrItem81Txt, (String) search.get("XX_SCR_ITEM_81_TXT"));
            setValue(cMsg.A.no(mgrIdx).svcMgrPsnCd, (String) search.get("PSN_CD"));
            // del start 2016/10/18 CSA Defect#11030
            //setValue(cMsg.A.no(mgrIdx).orgLayerNum, (BigDecimal) search.get("MGR_ORG_LAYER_NUM"));
            //setValue(cMsg.A.no(mgrIdx).orgCd, (String) search.get("ORG_CD"));
            // del end 2016/10/18 CSA Defect#11030
            suffix = NSBL0330CommonLogic.createSuffix(sfxIdx);
            cMsg.A.no(mgrIdx).setValue(XX_SCR_ITEM_130_TXT.concat(suffix), -1, (String) search.get("XX_SCR_ITEM_130_TXT"));
            cMsg.A.no(mgrIdx).setValue(XX_TOT_CNT.concat(suffix), -1, (BigDecimal) search.get("XX_TOT_CNT"));
            if (svcRqstCritTpCd.equals(SVC_RQST_CRIT_TP.TASK_TYPES)) {
                cMsg.A.no(mgrIdx).setValue(DS_SVC_CALL_TP_CD.concat(suffix), -1, (String) search.get("DS_SVC_CALL_TP_CD"));
            } else if (svcRqstCritTpCd.equals(SVC_RQST_CRIT_TP.SR_OR_TASK_STATUS)) {
                cMsg.A.no(mgrIdx).setValue(FSR_SVC_TASK_STS_RELN_PK.concat(suffix), -1, (BigDecimal) search.get("FSR_SVC_TASK_STS_RELN_PK"));
            } else if (svcRqstCritTpCd.equals(SVC_RQST_CRIT_TP.ASSIGNEE)) {
                cMsg.A.no(mgrIdx).setValue(TECH_CD.concat(suffix), -1, (String) search.get("TECH_CD"));
            } else if (svcRqstCritTpCd.equals(SVC_RQST_CRIT_TP.CANNEL)) {
                cMsg.A.no(mgrIdx).setValue(SVC_CALL_SRC_TP_CD.concat(suffix), -1, (String) search.get("SVC_CALL_SRC_TP_CD"));
            }
            mgrIdx++;
            if (mgrListSize == mgrIdx) {
                mgrIdx = 0;
                sfxIdx++;
                if (sfxIdx == DRILL_DOWN_LIST_SIZE) {
                    break;
                }
            }
        }
        cMsg.A.setValidCount(mgrListSize);
    }
}
