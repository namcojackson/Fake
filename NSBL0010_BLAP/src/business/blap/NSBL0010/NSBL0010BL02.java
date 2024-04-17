/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0010;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSBL0010.common.NSBL0010CommonLogic;
import business.blap.NSBL0010.constant.NSBL0010Constant;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 * <pre>
 * Service Dispatch Schedule/Dispatch Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/04/29   SRA             E.Inada         Create          N/A
 * 2014/06/02   Hitachi         T.Aoyagi        Update          CSA-166
 * 2016/10/19   Hitachi         N.Arai          Update          QC#13901
 * 2017/01/05   Hitachi         N.Arai          Update          QC#13901-2
 * 2017/01/17   Hitachi         N.Arai          Update          QC#16331
 *</pre>
 */
public class NSBL0010BL02 extends S21BusinessHandler implements NSBL0010Constant {

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

            NSBL0010CMsg bizMsg = (NSBL0010CMsg) cMsg;
            NSBL0010SMsg shareMsg = (NSBL0010SMsg) sMsg;

            if ("NSBL0010_INIT".equals(screenAplID)) {
                doProcess_NSBL0010_INIT(bizMsg, shareMsg);

            } else if ("NSBL0010Scrn00_Search".equals(screenAplID)) {
                doProcess_NSBL0010Scrn00_Search(bizMsg, shareMsg);

            } else if ("NSBL0010Scrn00_SearchShipToName".equals(screenAplID)) {
                doProcess_NSBL0010Scrn00_SearchShipToName(bizMsg, shareMsg);

            } else if ("NSBL0010Scrn00_Schedule".equals(screenAplID)) {
                doProcess_NSBL0010Scrn00_Schedule(bizMsg, shareMsg);

            } else if ("NSBL0010Scrn00_Dispatch".equals(screenAplID)) {
                doProcess_NSBL0010Scrn00_Dispatch(bizMsg, shareMsg);

            } else if ("NSBL0010Scrn00_Cancel".equals(screenAplID)) {
                doProcess_NSBL0010Scrn00_Cancel(bizMsg, shareMsg);

            } else if ("NSBL0010Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSBL0010Scrn00_PageNext(bizMsg, shareMsg);

            } else if ("NSBL0010Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSBL0010Scrn00_PagePrev(bizMsg, shareMsg);

            } else if ("NSBL0010Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSBL0010Scrn00_TBLColumnSort(bizMsg, shareMsg);

            } else if ("NSBL0010Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NSBL0010Scrn00_CMN_Download(bizMsg, shareMsg);

            } else if ("NSBL0010_NSBL0160".equals(screenAplID)) {
                doProcess_NSBL0010_NSBL0160(bizMsg, shareMsg);

            } else if ("NSBL0010Scrn00_Accept".equals(screenAplID)) {
                doProcess_NSBL0010Scrn00_Accept(bizMsg, shareMsg);

            } else if ("NSBL0010Scrn00_UpdateETA".equals(screenAplID)) {
                doProcess_NSBL0010Scrn00_UpdateETA(bizMsg, shareMsg);

// START 2016/10/19 N.Arai [QC#13901, MOD]
            } else if ("NSBL0010_NMAL6760".equals(screenAplID)) {
                doProcess_NSBL0010_NMAL6760(bizMsg, shareMsg);
// END 2016/10/19 N.Arai [QC#13901, MOD]

// START 2017/01/17 N.Arai [QC#16331, MOD]
            } else if ("NSBL0010Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSBL0010Scrn00_CMN_Clear(bizMsg, shareMsg);
// END 2017/01/17 N.Arai [QC#16331, MOD]

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Programming Area ++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

// START 2017/01/17 N.Arai [QC#16331, MOD]
    /**
     * doProcess_NSBL0010Scrn00_CMN_Clear
     * @param bizMsg NSBL0010CMsg
     * @param shareMsg NSBL0010SMsg
     */
    private void doProcess_NSBL0010Scrn00_CMN_Clear(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg) {

        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        String slsDt = bizMsg.slsDt.getValue();
        //clear msg
        NSBL0010CommonLogic.clearMsg(bizMsg, shareMsg);

        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.slsDt, slsDt);

        NSBL0010CommonLogic.createPulldownList(bizMsg);

        bizMsg.xxChkBox_L0.setValue(ZYPConstant.CHKBOX_ON_Y);
        bizMsg.xxChkBox_L1.setValue(ZYPConstant.CHKBOX_ON_Y);
    }
//END 2017/01/17 N.Arai [QC#16331, MOD]

// START 2016/10/19 N.Arai [QC#13901, MOD]
    /**
     * doProcess_NSBL0010_NMAL6760
     * @param bizMsg NSBL0010CMsg
     * @param shareMsg NSBL0010SMsg
     */
    private void doProcess_NSBL0010_NMAL6760(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.shipToCustCd.getValue())) {
            bizMsg.locNm.clear();
            return;
        }
        SHIP_TO_CUSTTMsg shipToCustTMsg = new SHIP_TO_CUSTTMsg();
        SHIP_TO_CUSTTMsgArray shipToCustTmsgArray;

        shipToCustTMsg.setSQLID("004");
        shipToCustTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        shipToCustTMsg.setConditionValue("shipToCustCd01", bizMsg.shipToCustCd.getValue());
        shipToCustTmsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(shipToCustTMsg);

        if (shipToCustTmsgArray.length() == 0) {
            bizMsg.locNm.clear();
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.locNm, shipToCustTmsgArray.no(0).locNm.getValue());
    }
// END 2016/10/19 N.Arai [QC#13901, MOD]

    /**
     * Method name: doProcess_NSBL0010_INIT
     * @param bizMsg NSBL0010CMsg
     * @param shareMsg NSBL0010SMsg
     */
    private void doProcess_NSBL0010_INIT(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg) {

     // START 2017/01/05 N.Arai [QC#13901-2, MOD]
        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(bizMsg.slsDt, ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue()));
     // END 2017/01/05 N.Arai [QC#13901-2, MOD]

        NSBL0010CommonLogic.createPulldownList(bizMsg);

        bizMsg.xxChkBox_L0.setValue(ZYPConstant.CHKBOX_ON_Y);
        bizMsg.xxChkBox_L1.setValue(ZYPConstant.CHKBOX_ON_Y);

        if (ZYPCommonFunc.hasValue(bizMsg.svcTaskNum)) {
            searchSvcTask(bizMsg, shareMsg);
        }
    }

    /**
     * Method name: doProcess_NSBL0010Scrn00_Search
     * @param bizMsg NSBL0010CMsg
     * @param shareMsg NSBL0010SMsg
     */
    private void doProcess_NSBL0010Scrn00_Search(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(shareMsg.A);
        bizMsg.xxErrFlg.clear();
        // START 2014/06/02 T.Aoyagi [CSA-166,ADD]
        bizMsg.xxErrFlg_PT.clear();
        // END 2014/06/02 T.Aoyagi [CSA-166,ADD]

// START 2016/10/19 N.Arai [QC#13901, MOD]
//        NSBL0010CommonLogic.setOrgNm(bizMsg);
// END 2016/10/19 N.Arai [QC#13901, MOD]

        searchSvcTask(bizMsg, shareMsg);
    }

    /**
     * Method name: doProcess_NSBL0010Scrn00_SearchShipToName
     * @param bizMsg NSBL0010CMsg
     * @param shareMsg NSBL0010SMsg
     */
    private void doProcess_NSBL0010Scrn00_SearchShipToName(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg) {

        searchShipToName(bizMsg);
    }

    /**
     * Method name: doProcess_NSBL0010_NSBL0160
     * @param bizMsg NSBL0010CMsg
     * @param shareMsg NSBL0010SMsg
     */
    private void doProcess_NSBL0010_NSBL0160(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg) {

        NSBL0010CommonLogic.setMsgFlg(bizMsg);
    }

    private void doProcess_NSBL0010Scrn00_PageNext(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg) {

        NSBL0010CommonLogic.copyBizToShare(bizMsg, shareMsg);

        ZYPTableUtil.clear(bizMsg.A);
        bizMsg.xxErrFlg.clear();
        // START 2014/06/02 T.Aoyagi [CSA-166,ADD]
        bizMsg.xxErrFlg_PT.clear();
        // END 2014/06/02 T.Aoyagi [CSA-166,ADD]

        // copy data from SMsg onto CMsg
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

        // set value to pagenation items
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());
    }

    private void doProcess_NSBL0010Scrn00_PagePrev(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg) {

        NSBL0010CommonLogic.copyBizToShare(bizMsg, shareMsg);

        ZYPTableUtil.clear(bizMsg.A);
        bizMsg.xxErrFlg.clear();
        // START 2014/06/02 T.Aoyagi [CSA-166,ADD]
        bizMsg.xxErrFlg_PT.clear();
        // END 2014/06/02 T.Aoyagi [CSA-166,ADD]

        // copy data from SMsg onto CMsg
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

        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount() - 1);
    }

    /**
     * searchSvcTask
     * @param bizMsg NSBL0010CMsg
     * @param shareMsg NSBL0010SMsg
     */
    private void searchSvcTask(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg) {

        EZDMsg.copy(bizMsg, null, shareMsg, null);

        S21SsmEZDResult rsSvcTask = NSBL0010Query.getInstance().getSvcTask(bizMsg, shareMsg);

        if (rsSvcTask == null || rsSvcTask.isCodeNotFound()) {
            bizMsg.setMessageInfo(NSBM0002E);
            return;
        }

        if (shareMsg.A.getValidCount() > MAX_SEARCH) {
            bizMsg.setMessageInfo(NSBM0009W, new String[] {String.valueOf(MAX_SEARCH) });

            shareMsg.A.setValidCount(shareMsg.A.getValidCount() - 1);
        }

        NSBL0010CommonLogic.setSvcTaskValue(shareMsg);

        EZDMsg.copy(shareMsg, null, bizMsg, null);

        bizMsg.xxPageShowFromNum.setValue(1);
        bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(shareMsg.A.getValidCount());
    }

    /**
     * reloadSvcTask
     * @param bizMsg NSBL0010CMsg
     * @param shareMsg NSBL0010SMsg
     */
    private void reloadSvcTask(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg) {

        if (MSG_KIND_ERR.equals(bizMsg.getMessageKind()) || MSG_KIND_WARN.equals(bizMsg.getMessageKind())) {
            return;
        }

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(shareMsg.A);
        bizMsg.xxErrFlg.clear();
        // START 2014/06/02 T.Aoyagi [CSA-166,ADD]
        bizMsg.xxErrFlg_PT.clear();
        // END 2014/06/02 T.Aoyagi [CSA-166,ADD]

        EZDMsg.copy(bizMsg, null, shareMsg, null);

        NSBL0010Query.getInstance().getSvcTask(bizMsg, shareMsg);

        if (shareMsg.A.getValidCount() > MAX_SEARCH) {
            shareMsg.A.setValidCount(shareMsg.A.getValidCount() - 1);
        }

        NSBL0010CommonLogic.setSvcTaskValue(shareMsg);

        EZDMsg.copy(shareMsg, null, bizMsg, null);

        bizMsg.xxPageShowFromNum.setValue(1);
        bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(shareMsg.A.getValidCount());
    }

    private boolean searchShipToName(NSBL0010CMsg bizMsg) {
        SHIP_TO_CUSTTMsg shipToCustTMsg = new SHIP_TO_CUSTTMsg();
        SHIP_TO_CUSTTMsgArray shipToCustTmsgArray;

        shipToCustTMsg.setSQLID("004");
        shipToCustTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        shipToCustTMsg.setConditionValue("shipToCustCd01", bizMsg.shipToCustCd.getValue());
        shipToCustTmsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(shipToCustTMsg);

        if (shipToCustTmsgArray.length() == 0) {
            bizMsg.locNm.clear();
            bizMsg.shipToCustCd.setErrorInfo(1, NSBM0002E);
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.locNm, shipToCustTmsgArray.no(0).locNm.getValue());

        return true;
    }

    private void doProcess_NSBL0010Scrn00_TBLColumnSort(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg) {

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            NSBL0010CommonLogic.copyBizToShare(bizMsg, shareMsg);

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(shareMsg.A, shareMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, shareMsg.A.getValidCount());

            // SMsg -> CMsg
            int i = 0;
            for (; i < shareMsg.A.getValidCount(); i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(shareMsg.A.no(i), null, bizMsg.A.no(i), null);
            }
            bizMsg.A.setValidCount(i);

            // Move to page 1
            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
        }
    }

    private void doProcess_NSBL0010Scrn00_CMN_Download(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg) {

        NSBL0010Query.getInstance().createNSBL0010F00(bizMsg);
    }

    private void doProcess_NSBL0010Scrn00_Schedule(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg) {
        reloadSvcTask(bizMsg, shareMsg);
    }

    private void doProcess_NSBL0010Scrn00_Dispatch(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg) {
        reloadSvcTask(bizMsg, shareMsg);
    }

    private void doProcess_NSBL0010Scrn00_Cancel(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg) {
        reloadSvcTask(bizMsg, shareMsg);
    }

    private void doProcess_NSBL0010Scrn00_Accept(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg) {
        reloadSvcTask(bizMsg, shareMsg);
    }

    private void doProcess_NSBL0010Scrn00_UpdateETA(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg) {
        reloadSvcTask(bizMsg, shareMsg);
    }
}
