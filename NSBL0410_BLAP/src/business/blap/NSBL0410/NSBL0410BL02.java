/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0410;

import static business.blap.NSBL0410.constant.NSBL0410Constant.MDSE_CODE;
import static business.blap.NSBL0410.constant.NSBL0410Constant.MKT_MDL;
import static business.blap.NSBL0410.constant.NSBL0410Constant.NSAM0184E;
import static business.blap.NSBL0410.constant.NSBL0410Constant.NSBM0002E;
import static business.blap.NSBL0410.constant.NSBL0410Constant.NSBM0005I;
import static business.blap.NSBL0410.constant.NSBL0410Constant.NSBM0016E;
import static business.blap.NSBL0410.constant.NSBL0410Constant.NSBM0042E;
import static business.blap.NSBL0410.constant.NSBL0410Constant.NSBM0057E;
import static business.blap.NSBL0410.constant.NSBL0410Constant.NSBM0187E;
import static business.blap.NSBL0410.constant.NSBL0410Constant.NZZM0001W;
import static business.blap.NSBL0410.constant.NSBL0410Constant.NZZM0012E;
import static business.blap.NSBL0410.constant.NSBL0410Constant.SERVICE_CALL;
import static business.blap.NSBL0410.constant.NSBL0410Constant.SVC_MOD_PK;
import static business.blap.NSBL0410.constant.NSBL0410Constant.ZZZM9007E;
import static business.blap.NSBL0410.constant.NSBL0410Constant.NSBM0188E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSBL0410.common.NSBL0410CommonLogic;
import business.db.MDSETMsg;
import business.db.MKT_MDLTMsg;
import business.db.SVC_MODTMsg;
import business.db.SVC_MOD_STSTMsg;
import business.db.SVC_MOD_STSTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MOD_PRTY;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
/**
 *<pre>
 * Mods Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Hitachi         M.Gotou         Create          N/A
 * 2016/02/26   Hitachi         M.Gotou         Update          QC#4710,4711
 * 2016/03/01   Hitachi         M.Gotou         Update          QC#4709
 * 2016/03/09   Hitachi         K.Yamada        Update          CSA QC#4986
 * 2016/04/15   Hitachi         M.Gotou         Update          QC#3425
 * 2016/07/11   Hitachi         O.Okuma         Update          QC#11682
 * 2016/07/14   Hitachi         O.Okuma         Update          QC#11675
 * 2018/03/08   Hitachi         K.Kojima        Update          QC#22479
 * 2018/05/17   CITS            T.Wada          Update          QC#22445
 * 2018/05/30   Hitachi         U.Kim           Update          QC#22393
 * 2018/06/18   CITS            T.Wada          Update          QC#22445-1
 * 2018/07/05   Fujitsu         T.Ogura         Update          QC#27067
 *</pre>
 */
public class NSBL0410BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSBL0410CMsg cMsg = (NSBL0410CMsg) arg0;
        NSBL0410SMsg sMsg = (NSBL0410SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        // Common Column Order Text
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0410_INIT".equals(screenAplID)) {
                doProcess_NSBL0410_INIT(cMsg, sMsg);
                ZYPGUITableColumn.getColData(cMsg, sMsg);
            // add start 2016/04/15 CSA Defect#3425
            } else if ("NSBL0410Scrn00_Filter".equals(screenAplID)) {
                doProcess_NSBL0410Scrn00_Filter(cMsg, sMsg);
            // add end 2016/04/15 CSA Defect#3425
            } else if ("NSBL0410Scrn00_Add".equals(screenAplID)) {
                doProcess_NSBL0410Scrn00_Add(cMsg, sMsg);
            } else if ("NSBL0410Scrn00_Apply".equals(screenAplID)) {
                doProcess_NSBL0410Scrn00_Apply(cMsg, sMsg);
            } else if ("NSBL0410Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSBL0410Scrn00_CMN_Reset(cMsg, sMsg);
            } else if ("NSBL0410Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSBL0410Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NSBL0410Scrn00_Delete".equals(screenAplID)) {
                doProcess_NSBL0410Scrn00_Delete(cMsg, sMsg);
            } else if ("NSBL0410Scrn00_MoveWin_MachineStatus".equals(screenAplID)) {
                doProcess_NSBL0410Scrn00_MoveWin_MachineStatus(cMsg, sMsg);
            } else if ("NSBL0410Scrn00_MoveWin_SerialAssinment".equals(screenAplID)) {
                doProcess_NSBL0410Scrn00_MoveWin_SerialAssinment(cMsg, sMsg);
            } else if ("NSBL0410Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSBL0410Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSBL0410Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSBL0410Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSBL0410Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSBL0410Scrn00_TBLColumnSort(cMsg, sMsg);
            } else if ("NSBL0410_NSBL0420".equals(screenAplID)) {
                doProcess_NSBL0410_NSBL0420(cMsg, sMsg);
            } else if ("NSBL0410_NSBL0430".equals(screenAplID)) {
                doProcess_NSBL0410_NSBL0430(cMsg, sMsg);
            } else if ("NSBL0410_NSBL0440".equals(screenAplID)) {
                doProcess_NSBL0410_NSBL0440(cMsg, sMsg);
                // START 2018/05/30 U.Kim [QC#22393, ADD]
            } else if ("NSBL0410Scrn00_SearchMarketingModel".equals(screenAplID)) {
                doProcess_NSBL0410Scrn00_SearchMarketingModel(cMsg, sMsg);
                // END 2018/05/30 U.Kim [QC#22393, ADD]
            }
        } finally {
            // Set Common Column Order Text of SMsg
            sMsg.xxComnColOrdTxt.clear();
            setValue(sMsg.xxComnColOrdTxt, xxComnColOrdTxt);
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0410_INIT(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {
        // add start 2016/04/15 CSA Defect#3425
        cMsg.mdseCd_F.clear();
        // add end 2016/04/15 CSA Defect#3425
        init(cMsg, sMsg);
    }

    // add start 2016/04/15 CSA Defect#3425
    private void doProcess_NSBL0410Scrn00_Filter(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {
        // mod start 2016/07/11 CSA Defect#11682
        filter(cMsg, sMsg);
        // mod end 2016/07/11 CSA Defect#11682
    }
    // add end 2016/04/15 CSA Defect#3425

    private void doProcess_NSBL0410Scrn00_Add(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {
        addNewMdseCdRow(cMsg, sMsg);
    }

    private void doProcess_NSBL0410Scrn00_Apply(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {

        // START 2018/07/05 T.Ogura [QC#27067,ADD]
        if ((SVC_MOD_PRTY.SAFETY.equals(cMsg.svcModPrtyCd.getValue()) || SVC_MOD_PRTY.NEXT_VISIT.equals(cMsg.svcModPrtyCd.getValue()))
                && ZYPConstant.FLG_OFF_N.equals(cMsg.svcModRptTrkFlg.getValue())) {
            cMsg.svcModRptTrkFlg.setErrorInfo(1, NSBM0188E);
            return;
        }
        // END   2018/07/05 T.Ogura [QC#27067,ADD]

        // set Row Number
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            setValue(cMsg.A.no(i).xxRowNum_A, BigDecimal.valueOf(i + 1));
        }

        NSBL0410CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSBL0410_ACMsgArray acMsgArray = cMsg.A;
        ZYPTableUtil.clear(acMsgArray);

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NSBM0042E);
        } else {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                    setValue(sMsg.A.no(i).svcMnfCd_A, cMsg.svcMnfCd.getValue());
                    setValue(sMsg.A.no(i).svcMnfModNum_A, cMsg.svcMnfModNum.getValue());
                    setValue(sMsg.A.no(i).svcModDocNum_A, cMsg.svcModDocNum.getValue());
                    setValue(sMsg.A.no(i).svcModPrtyDescTxt_A, cMsg.svcModPrtyCd.getValue());
                    setValue(sMsg.A.no(i).svcModRptTrkFlg_A, cMsg.svcModRptTrkFlg.getValue());
                    setValue(sMsg.A.no(i).svcModIssDt_A, cMsg.svcModIssDt.getValue());
                    // mod start 2016/07/14 CSA Defect#11675
                    if (!hasValue(sMsg.A.no(i).svcModStartDt_A) || ZYPDateUtil.isFutureDate(sMsg.A.no(i).svcModStartDt_A.getValue())) {
                        setValue(sMsg.A.no(i).svcModStartDt_A, cMsg.svcModStartDt.getValue());
                    }
                    // mod end 2016/07/14 CSA Defect#11675
                }
            }
        }

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int i = pageFrom;
        for (; i < pageFrom + acMsgArray.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, acMsgArray.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        acMsgArray.setValidCount(i - pageFrom);

        pageFrom = pageFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pageFrom);
        cMsg.xxPageShowToNum.setValue(pageFrom + acMsgArray.getValidCount() - 1);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSBL0410Scrn00_CMN_Reset(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        doProcess_NSBL0410_INIT(cMsg, sMsg);
        setValue(cMsg.xxComnColOrdTxt, xxComnColOrdTxt);
        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NSBM0005I);
        }
    }

    private void doProcess_NSBL0410Scrn00_CMN_Submit(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        doProcess_NSBL0410_INIT(cMsg, sMsg);
        setValue(cMsg.xxComnColOrdTxt, xxComnColOrdTxt);
        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NSBM0005I);
        }
    }

    private void doProcess_NSBL0410Scrn00_Delete(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {
        // set Row Number
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            setValue(cMsg.A.no(i).xxRowNum_A, BigDecimal.valueOf(i + 1));
        }

        NSBL0410CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSBL0410_ACMsgArray acMsgArray = cMsg.A;
        ZYPTableUtil.clear(acMsgArray);

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NSBM0042E);
        } else {
            boolean flg = true;
            for (int i = 0; i < selectedRows.size(); i++) {
               if (findSvcModSts(sMsg.A.no(selectedRows.get(i)).svcModDtlPk_A)) {
                   // QC#22445-1 Mod Start
                   //sMsg.A.no(i).xxChkBox_A.setErrorInfo(1, NSAM0184E, new String[] {SERVICE_CALL });
                   sMsg.A.no(selectedRows.get(i)).xxChkBox_A.setErrorInfo(1, NSAM0184E, new String[] {SERVICE_CALL });
                   // QC#22445-1 Mod End
                   flg = false;
               }
            }
            if (flg) {
                for (int i = 0; i < selectedRows.size(); i++) {
                    if (hasValue(sMsg.A.no(selectedRows.get(i)).svcModDtlPk_A)) {
                        setValue(sMsg.B.no(sMsg.B.getValidCount()).svcModDtlPk_B, sMsg.A.no(selectedRows.get(i)).svcModDtlPk_A.getValue());
                        sMsg.B.setValidCount(sMsg.B.getValidCount() + 1);
                    }
                }
                ZYPTableUtil.deleteRows(sMsg.A, selectedRows);
            }
        }

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int i = pageFrom;
        for (; i < pageFrom + acMsgArray.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, acMsgArray.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        acMsgArray.setValidCount(i - pageFrom);

        pageFrom = pageFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pageFrom);
        cMsg.xxPageShowToNum.setValue(pageFrom + acMsgArray.getValidCount() - 1);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSBL0410Scrn00_MoveWin_MachineStatus(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {

        int rowIndex = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSBL0410CommonLogic.setPagenation(cMsg, sMsg, rowIndex);

        if (checkInput(sMsg)) {
            NSBL0410CommonLogic.updateMod(cMsg, sMsg);
            return;
        }
        if (sMsg.B.getValidCount() != 0) {
            NSBL0410CommonLogic.updateMod(cMsg, sMsg);
            return;
        }
    }

    private void doProcess_NSBL0410Scrn00_MoveWin_SerialAssinment(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {

        int rowIndex = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSBL0410CommonLogic.setPagenation(cMsg, sMsg, rowIndex);

        if (checkInput(sMsg)) {
            NSBL0410CommonLogic.updateMod(cMsg, sMsg);
            return;
        }
        if (sMsg.B.getValidCount() != 0) {
            NSBL0410CommonLogic.updateMod(cMsg, sMsg);
            return;
        }
    }

    private boolean checkInput(NSBL0410SMsg sMsg) {
        if (sMsg.A.getValidCount() == 0) {
            return false;
        }
        // mod start 2016/03/28 CSA Defect#5410
        return true;
        // mod end 2016/03/28 CSA Defect#5410
    }

    private void doProcess_NSBL0410Scrn00_PageNext(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSBL0410CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSBL0410CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSBL0410Scrn00_PagePrev(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSBL0410CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSBL0410CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSBL0410Scrn00_TBLColumnSort(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {

        // add start 2016/07/11 CSA Defect#11687
        int rowIndex = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSBL0410CommonLogic.setPagenation(cMsg, sMsg, rowIndex);
        // add end 2016/07/11 CSA Defect#11687

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        if ("A".equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            // SMsg -> CMsg
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // Show 1st page
            setValue(cMsg.xxPageShowFromNum, BigDecimal.ONE);
            setValue(cMsg.xxPageShowToNum, new BigDecimal(cMsg.A.getValidCount()));
        }
    }

    // add start 2016/03/09 CSA Defect#4986
    private void doProcess_NSBL0410_NSBL0420(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        doProcess_NSBL0410_INIT(cMsg, sMsg);
        setValue(cMsg.xxComnColOrdTxt, xxComnColOrdTxt);
        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NSBM0005I);
        }
    }
    // add end 2016/03/09 CSA Defect#4986

    // add start 2016/03/28 CSA Defect#5410
    private void doProcess_NSBL0410_NSBL0430(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        doProcess_NSBL0410_INIT(cMsg, sMsg);
        setValue(cMsg.xxComnColOrdTxt, xxComnColOrdTxt);
        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NSBM0005I);
        }
    }

    private void doProcess_NSBL0410_NSBL0440(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        doProcess_NSBL0410_INIT(cMsg, sMsg);
        setValue(cMsg.xxComnColOrdTxt, xxComnColOrdTxt);
        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NSBM0005I);
        }
    }
    // add end 2016/03/09 CSA Defect#5410

    private void init(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {
        // mod start 2016/07/11 CSA Defect#11682
        NSBL0410CommonLogic.clearMsg(cMsg, sMsg, true);
        // mod end 2016/07/11 CSA Defect#11682
        NSBL0410CommonLogic.createPullDown(cMsg);
        NSBL0410CommonLogic.setInitParams(cMsg, sMsg);

        NSBL0410CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        if (!hasValue(cMsg.svcModPk)) {
            cMsg.setMessageInfo(NZZM0012E, new String[]{SVC_MOD_PK});
            return;
        }
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());
        findSvcMod(cMsg);
        findDtlInfo(cMsg, sMsg);

        // set Row Number
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            setValue(cMsg.A.no(i).xxRowNum_A, BigDecimal.valueOf(i + 1));
        }
    }

    // add start 2016/07/11 CSA Defect#11682
    private void filter(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {
        NSBL0410CommonLogic.clearMsg(cMsg, sMsg, false);

        NSBL0410CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());

        findDtlInfo(cMsg, sMsg);

        // set Row Number
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            setValue(cMsg.A.no(i).xxRowNum_A, BigDecimal.valueOf(i + 1));
        }
    }
    // add end 2016/07/11 CSA Defect#11682

    private void findSvcMod(NSBL0410CMsg cMsg) {
        SVC_MODTMsg tMsg = new SVC_MODTMsg();
        setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(tMsg.svcModPk, cMsg.svcModPk);

        SVC_MODTMsg rtMsg =  (SVC_MODTMsg) S21CacheTBLAccessor.findByKey(tMsg);

        if (rtMsg != null) {
            setValue(cMsg.svcModPlnId, rtMsg.svcModPlnId);
            setValue(cMsg.svcModNm, rtMsg.svcModNm);
            setValue(cMsg.svcModCmntTxt, rtMsg.svcModCmntTxt);
            setValue(cMsg.xxEndDplyTmTxt, rtMsg.estSvcLborHourMn);
            setValue(cMsg.svcMnfCd_MO, rtMsg.svcMnfCd);
            setValue(cMsg.ezUpTime, rtMsg.ezUpTime);
            setValue(cMsg.ezUpTimeZone, rtMsg.ezUpTimeZone);
        }
    }

    private void findDtlInfo(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", getGlobalCompanyCode());
        queryMap.put("svcModPk", cMsg.svcModPk.getValue());
        queryMap.put("limitCnt", sMsg.A.length() + 1);
        // add start 2016/04/15 CSA Defect#3425
        queryMap.put("mdseCd", cMsg.mdseCd_F.getValue());
        // add end 2016/04/15 CSA Defect#3425
        S21SsmEZDResult ssmResult = NSBL0410Query.getInstance().getDtlInfo(queryMap, sMsg.A);

        boolean warFlg = false;
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                warFlg = true;
                queryResCnt = sMsg.A.length();
            }
            if (!hasValue(sMsg.A.no(0).svcModDtlPk_A)) {
                sMsg.A.setValidCount(0);
                cMsg.A.setValidCount(0);
                cMsg.xxPageShowFromNum.clear();
                cMsg.xxPageShowToNum.clear();
                cMsg.xxPageShowOfNum.clear();
                return;
            }

            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            if (!warFlg) {
                cMsg.setMessageInfo(NSBM0005I);
            }
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);
        } else {
            cMsg.setMessageInfo(NSBM0002E);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }

    private void addNewMdseCdRow(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        // START 2018/05/31 U.Kim [QC#22393, ADD]
        // Input Check
        if (hasValue(cMsg.mdseItemTpCd)) {
            if (!hasValue(cMsg.mktMdlCd) && !hasValue(cMsg.mdseCd)) {
                cMsg.mktMdlCd.setErrorInfo(1, NSBM0187E, new String[] {MKT_MDL, MDSE_CODE });
                cMsg.mdseCd.setErrorInfo(1, NSBM0187E, new String[] {MKT_MDL, MDSE_CODE });
                return;
            }
        }
        if (!hasValue(cMsg.mdseCd)) {
            List<String> mdseCdList = NSBL0410Query.getInstance().getMdseCdForItemTpMktMdl(cMsg, sMsg);
            if (mdseCdList == null) {
                cMsg.setMessageInfo(NSBM0002E);
                return;
            }
            ZYPTableUtil.clear(sMsg.A);
            if (mdseCdList.size() > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }
            int count = 0;
            for (; count < mdseCdList.size() && count < sMsg.A.length(); count++) {
                if (hasValue(cMsg.svcMnfCd)) {
                    setValue(sMsg.A.no(count).svcMnfCd_A, cMsg.svcMnfCd.getValue());
                } else {
                    setValue(sMsg.A.no(count).svcMnfCd_A, cMsg.svcMnfCd_MO.getValue());
                }
                if (hasValue(cMsg.svcMnfModNum)) {
                    setValue(sMsg.A.no(count).svcMnfModNum_A, cMsg.svcMnfModNum.getValue());
                }
                if (hasValue(cMsg.svcModDocNum)) {
                    setValue(sMsg.A.no(count).svcModDocNum_A, cMsg.svcModDocNum.getValue());
                }
                setValue(sMsg.A.no(count).mdseCd_A, mdseCdList.get(count));
                if (hasValue(cMsg.svcModPrtyCd)) {
                    setValue(sMsg.A.no(count).svcModPrtyDescTxt_A, cMsg.svcModPrtyCd.getValue());
                }
                if (hasValue(cMsg.svcModRptTrkFlg)) {
                    setValue(sMsg.A.no(count).svcModRptTrkFlg_A, cMsg.svcModRptTrkFlg.getValue());
                }
                if (hasValue(cMsg.svcModIssDt)) {
                    setValue(sMsg.A.no(count).svcModIssDt_A, cMsg.svcModIssDt.getValue());
                }
                if (hasValue(cMsg.svcModStartDt)) {
                    setValue(sMsg.A.no(count).svcModStartDt_A, cMsg.svcModStartDt.getValue());
                }
            }
            sMsg.A.setValidCount(count);
            if (sMsg.A.getValidCount() > 0) {
                cMsg.xxPageShowFromNum.setValue(1);
            }
        } else {
            // END 2018/05/31 U.Kim [QC#22393, ADD]
            // START 2018/06/01 U.Kim [QC#, DEL]
            // if (!hasValue(cMsg.mdseCd)) {
            //     cMsg.mdseCd.setErrorInfo(1, ZZZM9007E, new String[] {MDSE_CODE });
            //     return;
            // }
            // END2018/06/01 U.Kim [QC#, DEL]
            int count = sMsg.A.getValidCount();
            if (sMsg.A.length() < count + 1) {
                cMsg.setMessageInfo(NSBM0057E);
                return;
            }
            // START 2018/03/07 K.Kojima [QC#22479,MOD]
            // ALL_MDSE_VTMsgArray result = NSBL0410Query.getALL_MDSE_V(cMsg.glblCmpyCd.getValue(),
            // cMsg.mdseCd.getValue());
            MDSETMsg result = NSBL0410Query.getMDSE(cMsg.glblCmpyCd.getValue(), cMsg.mdseCd.getValue());
            // END 2018/03/07 K.Kojima [QC#22479,MOD]

            // START 2018/03/07 K.Kojima [QC#22479,MOD]
            // if (result.getValidCount() == 0) {
            if (result == null) {
                // END 2018/03/07 K.Kojima [QC#22479,MOD]
                cMsg.mdseCd.setErrorInfo(1, NSBM0002E);
                return;
            }

            // START 2018/06/01 U.Kim [QC#22393, ADD]
            if (hasValue(cMsg.mdseItemTpCd)) {
                if (!hasValue(result.mdseItemTpCd)) {
                    cMsg.mdseCd.setErrorInfo(1, NSBM0002E);
                    return;
                }
                if (!result.mdseItemTpCd.getValue().equals(cMsg.mdseItemTpCd.getValue())) {
                    cMsg.mdseCd.setErrorInfo(1, NSBM0002E);
                    return;
                }
            }
            if (hasValue(cMsg.mktMdlCd)) {
                if (!hasValue(result.mktMdlCd)) {
                    cMsg.mdseCd.setErrorInfo(1, NSBM0002E);
                    return;
                }
                if (!result.mktMdlCd.getValue().equals(cMsg.mktMdlCd.getValue())) {
                    cMsg.mdseCd.setErrorInfo(1, NSBM0002E);
                    return;
                }
            }
            // END 2018/06/01 U.Kim [QC#22393, ADD]

            int i = 0;
            for (; i < count; i++) {
                if (ZYPDateUtil.isFutureDate(sMsg.A.no(i).svcModCancDt_A.getValue()) && ZYPDateUtil.isFutureDate(sMsg.A.no(i).svcModObslDt_A.getValue())) {
                    continue;
                }
                if (cMsg.mdseCd.getValue().equals(sMsg.A.no(i).mdseCd_A.getValue())) {
                    cMsg.mdseCd.setErrorInfo(1, NSBM0016E, new String[] {MDSE_CODE });
                    return;
                }
            }
            sMsg.A.setValidCount(count + 1);
            if (hasValue(cMsg.svcMnfCd)) {
                setValue(sMsg.A.no(count).svcMnfCd_A, cMsg.svcMnfCd.getValue());
            } else {
                setValue(sMsg.A.no(count).svcMnfCd_A, cMsg.svcMnfCd_MO.getValue());
            }
            if (hasValue(cMsg.svcMnfModNum)) {
                setValue(sMsg.A.no(count).svcMnfModNum_A, cMsg.svcMnfModNum.getValue());
            }
            if (hasValue(cMsg.svcModDocNum)) {
                setValue(sMsg.A.no(count).svcModDocNum_A, cMsg.svcModDocNum.getValue());
            }
            setValue(sMsg.A.no(count).mdseCd_A, cMsg.mdseCd.getValue());
            if (hasValue(cMsg.svcModPrtyCd)) {
                setValue(sMsg.A.no(count).svcModPrtyDescTxt_A, cMsg.svcModPrtyCd.getValue());
            }
            if (hasValue(cMsg.svcModRptTrkFlg)) {
                setValue(sMsg.A.no(count).svcModRptTrkFlg_A, cMsg.svcModRptTrkFlg.getValue());
            }
            if (hasValue(cMsg.svcModIssDt)) {
                setValue(sMsg.A.no(count).svcModIssDt_A, cMsg.svcModIssDt.getValue());
            }
            if (hasValue(cMsg.svcModStartDt)) {
                setValue(sMsg.A.no(count).svcModStartDt_A, cMsg.svcModStartDt.getValue());
            }

            // START 2018/05/31 U.Kim [QC#22393, ADD]
        }
        // END 2018/05/31 U.Kim [QC#22393, ADD]

        for (int h = 0; h < cMsg.A.getValidCount(); h++) {
            setValue(cMsg.A.no(h).xxRowNum_A, BigDecimal.valueOf(h + 1));
        }
        // START 2018/05/31 U.Kim [QC#22393, MOD]
        // NSBL0410CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        if (hasValue(cMsg.mdseCd)) {
            NSBL0410CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        }
        // END 2018/05/31 U.Kim [QC#22393, MOD]
        // START 2018/05/31 U.Kim [QC#22393, DEL]
        // cMsg.mdseCd.clear();
        // END 2018/05/31 U.Kim [QC#22393, DEL]

        if (sMsg.A.getValidCount() == 1) {
            cMsg.xxPageShowFromNum.setValue(1);
        }

        // START 2018/05/31 U.Kim [QC#22393, DEL]
        // int pagenationFrom;
        // END 2018/05/31 U.Kim [QC#22393, DEL]
        // START 2018/05/31 U.Kim [QC#22393, MOD]
        // if (sMsg.A.getValidCount() < cMsg.A.length() + 1) {
        if (sMsg.A.getValidCount() < cMsg.A.length() + 1 || !hasValue(cMsg.mdseCd)) {
        // END 2018/05/31 U.Kim [QC#22393, MOD]
            // START 2018/05/31 U.Kim [QC#22393, MOD]
            //pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
            // int j = 0;
            // for (; j < sMsg.A.getValidCount(); j++) {
            //     if (j < sMsg.A.getValidCount()) {
            //         EZDMsg.copy(sMsg.A.no(j), null, cMsg.A.no(j), null);
            //     } else {
            //         break;
            //     }
            // }
            // cMsg.A.setValidCount(j);
            // cMsg.xxPageShowFromNum.setValue(pagenationFrom);
            // cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            // cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
            cMsg.xxPageShowFromNum.setValue(1);
            NSBL0410CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            NSBL0410CommonLogic.setPageShowNum(cMsg.xxPageShowOfNum, cMsg.xxPageShowToNum, cMsg.xxPageShowFromNum.getValueInt(), cMsg.A.getValidCount(), sMsg.A.getValidCount());
            // END 2018/05/31 U.Kim [QC#22393, MOD]
        } else {
            // START 2018/05/31 U.Kim [QC#22393, MOD]
            // int pageNum = (sMsg.A.getValidCount() - 1) / cMsg.A.length();
            // pagenationFrom = pageNum * cMsg.A.length();
            // ZYPTableUtil.clear(cMsg.A);
            // int j = pagenationFrom; // 100
            // for (; j < sMsg.A.getValidCount(); j++) {
            //     if (j < sMsg.A.getValidCount()) {
            //         EZDMsg.copy(sMsg.A.no(j), null, cMsg.A.no(j - pagenationFrom), null);
            //     } else {
            //         break;
            //     }
            // }
            // cMsg.A.setValidCount(j - pagenationFrom);
            // cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
            // cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
            // cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
            cMsg.xxPageShowFromNum.setValue(NSBL0410CommonLogic.getLastPageFromNum(cMsg, sMsg));
            NSBL0410CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            NSBL0410CommonLogic.setPageShowNum(cMsg.xxPageShowOfNum, cMsg.xxPageShowToNum, cMsg.xxPageShowFromNum.getValueInt(), cMsg.A.getValidCount(), sMsg.A.getValidCount());
            // END 2018/05/31 U.Kim [QC#22393, MOD]
        }
    }

    // QC#22445 Mod Start
//    private boolean findSvcModSts(EZDSBigDecimalItem modDtlPk) {
//        SVC_MOD_STSTMsg tMsg = new SVC_MOD_STSTMsg();
//        setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
//        setValue(tMsg.svcModDtlPk, modDtlPk);
//
//        SVC_MOD_STSTMsg rtMsg =  (SVC_MOD_STSTMsg) S21CacheTBLAccessor.findByKey(tMsg);
//        if (rtMsg != null) {
//            return true;
//        }
//        return false;
//    }

    private boolean findSvcModSts(EZDSBigDecimalItem modDtlPk) {
        if (!ZYPCommonFunc.hasValue(modDtlPk)) {
            return false;
        }
        SVC_MOD_STSTMsg param = new SVC_MOD_STSTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        param.setConditionValue("svcModDtlPk01", modDtlPk.getValue());

        SVC_MOD_STSTMsgArray result = (SVC_MOD_STSTMsgArray) EZDTBLAccessor.findByCondition(param);

        if (result.getValidCount() == 0) {
            return false;
        }
        return true;
    }
    // QC#22445 Mod End

    // START 2018/05/30 U.Kim [QC#22939, ADD]
    private void doProcess_NSBL0410Scrn00_SearchMarketingModel(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {
        if (!hasValue(cMsg.mktMdlCd)) {
            cMsg.mktMdlCd.setErrorInfo(1, ZZZM9007E, new String[] {"Marketing Model" });
            return;
        }

        MKT_MDLTMsg mktMdl = NSBL0410Query.getInstance().getMarketingModel(getGlobalCompanyCode(), cMsg.mktMdlCd.getValue());
        if (mktMdl == null) {
            cMsg.mktMdlDescTxt.clear();
            cMsg.setMessageInfo(NSBM0002E);
            return;
        }
        ZYPEZDItemValueSetter.setValue(cMsg.mktMdlDescTxt, mktMdl.mktMdlDescTxt);
    }
    // END 2018/05/30 U.Kim [QC#22939, ADD]
}
