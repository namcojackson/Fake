/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFBL2120;

import static business.blap.NFBL2120.constant.NFBL2120Constant.COL_DOC_MGT_CATG_CD;
import static business.blap.NFBL2120.constant.NFBL2120Constant.COL_DOC_MGT_CATG_DESC_TXT;
import static business.blap.NFBL2120.constant.NFBL2120Constant.COL_DOC_MGT_CATG_NUM;
import static business.blap.NFBL2120.constant.NFBL2120Constant.COL_DOC_MGT_FLD_DESC_TXT;
import static business.blap.NFBL2120.constant.NFBL2120Constant.COL_DOC_MGT_FLD_NUM;
import static business.blap.NFBL2120.constant.NFBL2120Constant.GET_SEARCH_CONDITION_FLD_NM_LIST;
import static business.blap.NFBL2120.constant.NFBL2120Constant.MESSAGE_KIND_E;
import static business.blap.NFBL2120.constant.NFBL2120Constant.NFBL2120_SEARCH_FOR_THEREFORE;
import static business.blap.NFBL2120.constant.NFBL2120Constant.NZZM0000E;
import static business.blap.NFBL2120.constant.NFBL2120Constant.NZZM0001W;
import static business.blap.NFBL2120.constant.NFBL2120Constant.SEARCH_CONDITION_STRING_ASTERISK;
import static business.blap.NFBL2120.constant.NFBL2120Constant.THEREFORE_CONN_AVAL_FLG;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.xml.ws.soap.SOAPFaultException;

import net.therefore.schemas.webservices.interop.v0001.messages.IThereforeService;
import net.therefore.schemas.webservices.interop.v0001.types.ArrayOfWSCondition;
import net.therefore.schemas.webservices.interop.v0001.types.ExecuteSingleQueryParams;
import net.therefore.schemas.webservices.interop.v0001.types.ExecuteSingleQueryResponse;
import net.therefore.schemas.webservices.interop.v0001.types.FieldNoOrNameList;
import net.therefore.schemas.webservices.interop.v0001.types.QueryObject;
import net.therefore.schemas.webservices.interop.v0001.types.WSCondition;
import net.therefore.schemas.webservices.interop.v0001.types.WSQueryResultRow;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.common.EZDSystemEnv;
import business.blap.NFBL2120.common.NFBL2120CommonLogic;
import business.db.DOC_MGT_CATGTMsg;
import business.db.DOC_MGT_ORGTMsg;
import business.db.DOC_MGT_PRTYTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.therefore.webservice.S21ThereforeWebServiceProxy;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NFBL2120BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/14   Fujitsu         W.Honda         Create          N/A
 * 2016/10/06   Fujitsu         W.Honda         Update          QC#15051
 *</pre>
 */
public class NFBL2120BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NFBL2120CMsg bizMsg = (NFBL2120CMsg) cMsg;
            NFBL2120SMsg glblMsg = (NFBL2120SMsg) sMsg;


            if ("NFBL2120_INIT".equals(screenAplID)) {
                doProcess_NFBL2120_INIT(bizMsg, glblMsg);

            } else if ("NFBL2120Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFBL2120Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NFBL2120Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFBL2120Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NFBL2120Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFBL2120Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NFBL2120Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NFBL2120Scrn00_PageJump(bizMsg, glblMsg);

            } else if ("NFBL2120Scrn00_Search".equals(screenAplID)) {
                doProcess_NFBL2120Scrn00_Search(bizMsg, glblMsg);

            } else if ("NFBL2120Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NFBL2120Scrn00_TBLColumnSort(bizMsg, glblMsg);

            } else if ("NFBL2120Scrn00_OpenWin_ViewTherefore".equals(screenAplID)) {
                doProcess_NFBL2120Scrn00_OpenWin_ViewTherefore(bizMsg, glblMsg);


            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFBL2120_INIT(NFBL2120CMsg bizMsg, NFBL2120SMsg glblMsg) {
        ZYPCodeDataUtil.createPulldownList("DOC_MGT_ORG", bizMsg.docMgtOrgCd_L, bizMsg.docMgtOrgDescTxt_L);
        ZYPCodeDataUtil.createPulldownList("DOC_MGT_PRTY", bizMsg.docMgtPrtyCd_L, bizMsg.docMgtPrtyDescTxt_L);

        // DOC_MGT_CATG
        if (ZYPCommonFunc.hasValue(bizMsg.xxScrDply)) {
            String[] docMgtCatgList = null;
            if (bizMsg.xxScrDply.getValue().contains(",")) {
                docMgtCatgList = bizMsg.xxScrDply.getValue().split(",");
            } else {
                docMgtCatgList = new String[1];
                docMgtCatgList[0] = bizMsg.xxScrDply.getValue();
            }

            int i = 0;
            for (String docMgtCatgCd : docMgtCatgList) {
                DOC_MGT_CATGTMsg docMgtCatg = new DOC_MGT_CATGTMsg();
                ZYPEZDItemValueSetter.setValue(docMgtCatg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(docMgtCatg.docMgtCatgCd, docMgtCatgCd);
                docMgtCatg = (DOC_MGT_CATGTMsg) S21CodeTableAccessor.findByKey(docMgtCatg);

                if (docMgtCatg != null) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.docMgtCatgCd_L.no(i), docMgtCatg.docMgtCatgCd);
                    ZYPEZDItemValueSetter.setValue(bizMsg.docMgtCatgDescTxt_L.no(i), docMgtCatg.docMgtCatgDescTxt);
                }

                i++;
            }
        }

        search(bizMsg, glblMsg);
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFBL2120Scrn00_CMN_Clear(NFBL2120CMsg bizMsg, NFBL2120SMsg glblMsg) {
        String catgCdList = bizMsg.xxScrDply.getValue();

        bizMsg.clear();
        glblMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrDply, catgCdList);

        doProcess_NFBL2120_INIT(bizMsg, glblMsg);
    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFBL2120Scrn00_PageNext(NFBL2120CMsg bizMsg, NFBL2120SMsg glblMsg) {
        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NFBL2120CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFBL2120Scrn00_PagePrev(NFBL2120CMsg bizMsg, NFBL2120SMsg glblMsg) {
        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NFBL2120CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFBL2120Scrn00_PageJump(NFBL2120CMsg bizMsg, NFBL2120SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NFBL2120CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn, BigDecimal.ZERO);

    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFBL2120Scrn00_Search(NFBL2120CMsg bizMsg, NFBL2120SMsg glblMsg) {

        // search
        search(bizMsg, glblMsg);
    }

    /**
     * TBLColumnSort Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFBL2120Scrn00_TBLColumnSort(NFBL2120CMsg bizMsg, NFBL2120SMsg glblMsg) {
        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy  = bizMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());

            // set pagination.
            bizMsg.xxPageShowFromNum.setValue(1);
            NFBL2120CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }

    /**
     * TBLColumnSort Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFBL2120Scrn00_OpenWin_ViewTherefore(NFBL2120CMsg bizMsg, NFBL2120SMsg glblMsg) {
        int index = bizMsg.xxRadioBtn.getValueInt();

        String url = EZDSystemEnv.getString("S21.therfore.attachment.url");

        if (ZYPCommonFunc.hasValue(url)
                && ZYPCommonFunc.hasValue(bizMsg.A.no(index).docMgtDocId_A1)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.docMgtDocId, bizMsg.A.no(index).docMgtDocId_A1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.docMgtCatgCd, bizMsg.A.no(index).docMgtCatgCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.xxSrvUrlTxt, url + bizMsg.A.no(index).docMgtDocId_A1.getValue().toString());
        }
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NFBL2120CMsg bizMsg, NFBL2120SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        // QC#15051 2016/10/06 Add start
        /** Therefore Connection Available Flag */
        String thereforeConnAvalFlg = "N";
        thereforeConnAvalFlg = ZYPCodeDataUtil.getVarCharConstValue(THEREFORE_CONN_AVAL_FLG, getGlobalCompanyCode());
        if(ZYPConstant.FLG_ON_Y.equals(thereforeConnAvalFlg)) {
        // QC#15051 2016/10/06 Add end
            S21SsmEZDResult ssmResult = NFBL2120Query.getInstance().getThereforeCatgoryNo(bizMsg);

            if (ssmResult.isCodeNormal()) {
                List<Map<String, Object>> docMgtCatgList = (List<Map<String, Object>>) ssmResult.getResultObject();
                for (Map<String, Object> docMgtCatgResult : docMgtCatgList) {
                    callThereforeApiForSearch(bizMsg, glblMsg, docMgtCatgResult);
                    if (MESSAGE_KIND_E.equals(bizMsg.getMessageKind())) {
                        break;
                    }
                }
            }
        // QC#15051 2016/10/06 Add start
        } else {
            S21InfoLogOutput.println("NFBL2120 Therefore Search process was skipped. THEREFORE_CONN_AVAL_FLG is 'N'.");
        }
        // QC#15051 2016/10/06 Add end

        if (glblMsg.A.getValidCount() == 0) {

            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();

            bizMsg.setMessageInfo(NZZM0000E);
        }

        bizMsg.xxPageShowFromNum.setValue(1);
        NFBL2120CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
//    private void callThereforeApiForSearch(NFBL2120CMsg bizMsg, NFBL2120SMsg glblMsg, String docMgtCatgCd, int thereforeCategoryNo) {
    private void callThereforeApiForSearch(NFBL2120CMsg bizMsg, NFBL2120SMsg glblMsg, Map<String, Object> docMgtCatgResult) {
        String docMgtCatgCd      = (String) docMgtCatgResult.get(COL_DOC_MGT_CATG_CD);
        int thereforeCategoryNo = NFBL2120CommonLogic.nvlBigDecimal((BigDecimal) docMgtCatgResult.get(COL_DOC_MGT_CATG_NUM));
        String docMgtCatgDescTxt = (String) docMgtCatgResult.get(COL_DOC_MGT_CATG_DESC_TXT);

        // for search condition
        S21SsmEZDResult conditionSsmResult = NFBL2120Query.getInstance().getSearchDocMgtFld(docMgtCatgCd, true);
        List<Map<String, Object>> searchConditionFldList = (List<Map<String, Object>>) conditionSsmResult.getResultObject();
        // for search result
        S21SsmEZDResult resultSsmResult = NFBL2120Query.getInstance().getSearchDocMgtFld(docMgtCatgCd, false);
        List<Map<String, Object>> searchResultFldList = (List<Map<String, Object>>) resultSsmResult.getResultObject();

        // Get Therefore web service port instance
        IThereforeService thereforWebSerive = S21ThereforeWebServiceProxy.getInstance().getThereforeService();

        try {
            // Set parameters
            ExecuteSingleQueryParams params = new ExecuteSingleQueryParams();
            QueryObject queryObject = new QueryObject();
            ArrayOfWSCondition conditions = new ArrayOfWSCondition();
            queryObject.setConditions(conditions);
            queryObject.setCategoryNo(thereforeCategoryNo);

            FieldNoOrNameList fieldNoList = new FieldNoOrNameList();
            for (Map<String, Object> searchResultFld : searchResultFldList) {
                fieldNoList.getFieldNoOrName().add(String.valueOf(NFBL2120CommonLogic.nvlBigDecimal((BigDecimal) searchResultFld.get(COL_DOC_MGT_FLD_NUM))));
            }

            if (fieldNoList.getFieldNoOrName().size() > 0) {
                queryObject.setSelectedFieldsNoOrNames(fieldNoList);
            }
            params.setQuery(queryObject);

            for (Map<String, Object> searchConditionFld : searchConditionFldList) {
                // Line of BusinesNo
                if (GET_SEARCH_CONDITION_FLD_NM_LIST[0].equals((String) searchConditionFld.get(COL_DOC_MGT_FLD_DESC_TXT))
                        && ZYPCommonFunc.hasValue(bizMsg.docMgtOrgCd_H)) {
                    DOC_MGT_ORGTMsg docMgtOrg = new DOC_MGT_ORGTMsg();
                    ZYPEZDItemValueSetter.setValue(docMgtOrg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(docMgtOrg.docMgtOrgCd, bizMsg.docMgtOrgCd_H);
                    docMgtOrg = (DOC_MGT_ORGTMsg) S21CodeTableAccessor.findByKey(docMgtOrg);

                    if (docMgtOrg != null) {
                        WSCondition condition = new WSCondition();
                        condition.setCondition(docMgtOrg.docMgtOrgDescTxt.getValue());
                        condition.setFieldNoOrName(String.valueOf(NFBL2120CommonLogic.nvlBigDecimal((BigDecimal) searchConditionFld.get(COL_DOC_MGT_FLD_NUM))));
                        conditions.getWSCondition().add(condition);
                    }
                }

                // PriorityNo
                if (GET_SEARCH_CONDITION_FLD_NM_LIST[1].equals((String) searchConditionFld.get(COL_DOC_MGT_FLD_DESC_TXT))
                        && ZYPCommonFunc.hasValue(bizMsg.docMgtPrtyCd_H)) {
                    DOC_MGT_PRTYTMsg docMgtPrty = new DOC_MGT_PRTYTMsg();
                    ZYPEZDItemValueSetter.setValue(docMgtPrty.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(docMgtPrty.docMgtPrtyCd, bizMsg.docMgtPrtyCd_H);
                    docMgtPrty = (DOC_MGT_PRTYTMsg) S21CodeTableAccessor.findByKey(docMgtPrty);

                    if (docMgtPrty != null) {
                        WSCondition condition = new WSCondition();
                        condition.setCondition(docMgtPrty.docMgtPrtyDescTxt.getValue());
                        condition.setFieldNoOrName(String.valueOf(NFBL2120CommonLogic.nvlBigDecimal((BigDecimal) searchConditionFld.get(COL_DOC_MGT_FLD_NUM))));
                        conditions.getWSCondition().add(condition);
                    }
                }
                // Submitted By
                if (GET_SEARCH_CONDITION_FLD_NM_LIST[2].equals((String) searchConditionFld.get(COL_DOC_MGT_FLD_DESC_TXT))) {
                    WSCondition condition = new WSCondition();
                    S21UserProfileService prof = S21UserProfileServiceFactory.getInstance().getService();
                    S21UserInfo userInfo = prof.getContextUserInfo();
                    condition.setCondition(SEARCH_CONDITION_STRING_ASTERISK + userInfo.getUserId());
                    condition.setFieldNoOrName(String.valueOf(NFBL2120CommonLogic.nvlBigDecimal((BigDecimal) searchConditionFld.get(COL_DOC_MGT_FLD_NUM))));
                    conditions.getWSCondition().add(condition);
                }
                // Invoice Status
                if (GET_SEARCH_CONDITION_FLD_NM_LIST[3].equals((String) searchConditionFld.get(COL_DOC_MGT_FLD_DESC_TXT))) {
                    WSCondition condition = new WSCondition();
                    String searchTargetForTherefore = ZYPCodeDataUtil.getVarCharConstValue(NFBL2120_SEARCH_FOR_THEREFORE, getGlobalCompanyCode());
                    condition.setCondition(searchTargetForTherefore);
                    condition.setFieldNoOrName(String.valueOf(NFBL2120CommonLogic.nvlBigDecimal((BigDecimal) searchConditionFld.get(COL_DOC_MGT_FLD_NUM))));
                    conditions.getWSCondition().add(condition);
                }
            }

            // Invoke Therefore web service
            ExecuteSingleQueryResponse response = thereforWebSerive.executeSingleQuery(params);
            java.util.List<WSQueryResultRow> rows = response.getQueryResult().getResultRows().getWSQueryResultRow();
            int i = glblMsg.A.getValidCount();
            for (WSQueryResultRow row : rows) {
                if (i >= glblMsg.A.length()) {
                    bizMsg.setMessageInfo(NZZM0001W);
                    break;
                }

                List<String> rowValueList = row.getIndexValues().getIndexValue();

                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).docMgtDocId_A1, BigDecimal.valueOf(row.getDocNo()));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).docMgtCatgDescTxt_A1, docMgtCatgDescTxt);
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).docMgtCatgCd_A1, docMgtCatgCd);
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).docMgtCatgNum_A1, BigDecimal.valueOf(thereforeCategoryNo));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxUsrSysDtTxt_A1, rowValueList.get(1));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).docMgtOrgDescTxt_A1, rowValueList.get(2));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).docMgtPrtyDescTxt_A1, rowValueList.get(3));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxScrDply_A1, rowValueList.get(4));

                i++;
            }

            glblMsg.A.setValidCount(i);
        } catch (SOAPFaultException e) { // Most of error from Therefore side include data error falls in here
            e.printStackTrace();
//        } catch (ClientTransportException e) { // Connection refused error falls in here, it happens before Therefore side
//            e.printStackTrace();
////            throw new S21AbendException(e);
        } catch (Exception e) { // Unknown error falls in here
            e.printStackTrace();
            throw new S21AbendException(e);
        }
    }
}
