/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0390;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static business.blap.NSAL0390.constant.NSAL0390Constant.*;
import static business.blap.NSAL0390.common.NSAL0390CommonLogic.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.db.DS_CR_CARDTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/19   Fujitsu         T.Yoshida       Create          N/A
 * 2016/02/29   Hitachi         K.Kasai         Update          QC#2684
 * 2016/03/10   Hitachi         M.Gotou         Update          QC#4423
 * 2016/05/18   Hitachi         A.Kohinata      Update          QC#4212
 * 2016/06/09   Hitachi         T.Kanasaka      Update          QC#9708
 * 2016/08/29   Hitachi         A.Kohinata      Update          QC#11243
 * 2016/12/05   Hitachi         K.Ochiai        Update          QC#14204
 * 2017/01/17   Hitachi         K.Ochiai        Update          QC#16331
 * 2018/03/16   CITS            M.Naito         Update          QC#20496
 *</pre>
 */
public class NSAL0390BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0390_INIT".equals(screenAplID)) {
                doProcess_NSAL0390_INIT((NSAL0390CMsg) cMsg, (NSAL0390SMsg) sMsg);
            } else if ("NSAL0390Scrn00_Expansion".equals(screenAplID)) {
                doProcess_NSAL0390Scrn00_Expansion((NSAL0390CMsg) cMsg, (NSAL0390SMsg) sMsg);
            } else if ("NSAL0390Scrn00_Contraction".equals(screenAplID)) {
                doProcess_NSAL0390Scrn00_Contraction((NSAL0390CMsg) cMsg, (NSAL0390SMsg) sMsg);
            } else if ("NSAL0390Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0390Scrn00_PagePrev((NSAL0390CMsg) cMsg, (NSAL0390SMsg) sMsg);
            } else if ("NSAL0390Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0390Scrn00_PageNext((NSAL0390CMsg) cMsg, (NSAL0390SMsg) sMsg);
            } else if ("NSAL0390Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NSAL0390Scrn00_PageJump((NSAL0390CMsg) cMsg, (NSAL0390SMsg) sMsg);
            // mod start 2017/01/17 CSA Defect#16331
            } else if ("NSAL0390Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0390Scrn00_CMN_Reset((NSAL0390CMsg) cMsg, (NSAL0390SMsg) sMsg);
            // mod end 2017/01/17 CSA Defect#16331
                // mod start 2016/03/14 CSA Defect#4423
            } else if ("NSAL0390_NWAL2010".equals(screenAplID)) {
                doProcess_NSAL0390Scrn00_NWAL2010((NSAL0390CMsg) cMsg, (NSAL0390SMsg) sMsg);
                // mod end 2016/03/14 CSA Defect#4423
            } else if ("NSAL0390Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL0390Scrn00_Search((NSAL0390CMsg) cMsg, (NSAL0390SMsg) sMsg);
            } else if ("NSAL0390Scrn00_SelectAllContract".equals(screenAplID)) {
                doProcess_NSAL0390Scrn00_SelectAllContract((NSAL0390CMsg) cMsg, (NSAL0390SMsg) sMsg);
            } else if ("NSAL0390Scrn00_SelectAllSerial".equals(screenAplID)) {
                doProcess_NSAL0390Scrn00_SelectAllSerial((NSAL0390CMsg) cMsg, (NSAL0390SMsg) sMsg);
            } else if ("NSAL0390Scrn00_ApplyToAll".equals(screenAplID)) {
                doProcess_NSAL0390Scrn00_ApplyToAll((NSAL0390CMsg) cMsg, (NSAL0390SMsg) sMsg);
            // add start 2016/08/29 CSA Defect#11243
            } else if ("NSAL0390Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0390Scrn00_CMN_Submit((NSAL0390CMsg) cMsg, (NSAL0390SMsg) sMsg);
            // add end 2016/08/29 CSA Defect#11243
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Init)
     * @param cMsg NSAL0390CMsg
     * @param sMsg NSAL0390SMsg
     */
    private void doProcess_NSAL0390_INIT(NSAL0390CMsg cMsg, NSAL0390SMsg sMsg) {

        clearScreen(cMsg, sMsg);
        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        if (!checkInparam(cMsg)) {
            return;
        }

        // Service Memo Reason Pull down
        setServiceMemoReasonInfo(cMsg);

        // Set Datil List
        String smryLineFlg = ZYPConstant.FLG_ON_Y;
        setDetailListInfo(cMsg, sMsg, smryLineFlg);
    }

    /**
     * do process (Expansion)
     * @param cMsg NSAL0390CMsg
     * @param sMsg NSAL0390SMsg
     */
    private void doProcess_NSAL0390Scrn00_Expansion(NSAL0390CMsg cMsg, NSAL0390SMsg sMsg) {

        // set Contraction Button
        NSAL0390_ACMsg acMsg = cMsg.A.no(cMsg.xxNum_EV.getValueInt());
        setValue(acMsg.xxSmryLineFlg_A0, ZYPConstant.FLG_OFF_N);

        copyCurrentPageToBSMsg(cMsg, sMsg);
        copyFromBSMsgToASMsgForDisplay(sMsg);

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        copyFromASMsgToACMsg(cMsg, sMsg, pageFrom);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    /**
     * do process (Contraction)
     * @param cMsg NSAL0390CMsg
     * @param sMsg NSAL0390SMsg
     */
    private void doProcess_NSAL0390Scrn00_Contraction(NSAL0390CMsg cMsg, NSAL0390SMsg sMsg) {

        // set Expansion Button
        NSAL0390_ACMsg acMsg = cMsg.A.no(cMsg.xxNum_EV.getValueInt());
        setValue(acMsg.xxSmryLineFlg_A0, ZYPConstant.FLG_ON_Y);

        copyCurrentPageToBSMsg(cMsg, sMsg);
        copyFromBSMsgToASMsgForDisplay(sMsg);

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        copyFromASMsgToACMsg(cMsg, sMsg, pageFrom);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    /**
     * do process (Page Prev)
     * @param cMsg NSAL0390CMsg
     * @param sMsg NSAL0390SMsg
     */
    private void doProcess_NSAL0390Scrn00_PagePrev(NSAL0390CMsg cMsg, NSAL0390SMsg sMsg) {

        copyCurrentPageToBSMsg(cMsg, sMsg);
        copyFromBSMsgToASMsgForPaging(sMsg);

        cMsg.xxChkBox_H0.clear();
        cMsg.xxChkBox_H1.clear();

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        copyFromASMsgToACMsg(cMsg, sMsg, pageFrom);
    }

    /**
     * do process (Page Next)
     * @param cMsg NSAL0390CMsg
     * @param sMsg NSAL0390SMsg
     */
    private void doProcess_NSAL0390Scrn00_PageNext(NSAL0390CMsg cMsg, NSAL0390SMsg sMsg) {

        copyCurrentPageToBSMsg(cMsg, sMsg);
        copyFromBSMsgToASMsgForPaging(sMsg);

        cMsg.xxChkBox_H0.clear();
        cMsg.xxChkBox_H1.clear();

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        copyFromASMsgToACMsg(cMsg, sMsg, pageFrom);
    }

    /**
     * do process (Page Jump)
     * @param cMsg NSAL0390CMsg
     * @param sMsg NSAL0390SMsg
     */
    private void doProcess_NSAL0390Scrn00_PageJump(NSAL0390CMsg cMsg, NSAL0390SMsg sMsg) {

        copyCurrentPageToBSMsg(cMsg, sMsg);
        copyFromBSMsgToASMsgForPaging(sMsg);

        cMsg.xxChkBox_H0.clear();
        cMsg.xxChkBox_H1.clear();

        int pageFrom = (cMsg.xxPageShowCurNum.getValue().subtract(BigDecimal.ONE)).multiply(new BigDecimal(cMsg.A.length())).intValue();
        copyFromASMsgToACMsg(cMsg, sMsg, pageFrom);
    }

    // mod start 2017/01/1 CSA Defect#16331
    /**
     * do process (Clear)
     * @param cMsg NSAL0390CMsg
     * @param sMsg NSAL0390SMsg
     */
    private void doProcess_NSAL0390Scrn00_CMN_Reset(NSAL0390CMsg cMsg, NSAL0390SMsg sMsg) {

        clearScreen(cMsg, sMsg);

        String smryLineFlg = ZYPConstant.FLG_ON_Y;
        setDetailListInfo(cMsg, sMsg, smryLineFlg);
    }
    // mod end 2017/01/17 CSA Defect#16331

    // mod start 2016/03/14 CSA Defect#4423
    /**
     * do process (NWAL201)
     * @param cMsg NSAL0390CMsg
     * @param sMsg NSAL0390SMsg
     */
    private void doProcess_NSAL0390Scrn00_NWAL2010(NSAL0390CMsg cMsg, NSAL0390SMsg sMsg) {

        if (!hasValue(cMsg.dsCrCardPk)) {
            return;
        }

        // get Reference Number and Expire Date
        DS_CR_CARDTMsg tMsg = getCrCard(cMsg.glblCmpyCd.getValue(), cMsg);
        if (tMsg == null) {
            return;
        }

        String scrEventNm = cMsg.xxScrEventNm.getValue();
        if ("Open_CC".equals(scrEventNm)) {
            copyCurrentPageToBSMsg(cMsg, sMsg);

            NSAL0390_ACMsg acMsg = cMsg.A.no(cMsg.xxNum_EV.getValueInt());
            int index = acMsg.xxNum_A0.getValueInt();
            NSAL0390_BSMsg bsMsg = sMsg.B.no(index);

            setValue(bsMsg.crCardCustRefNum_B1, tMsg.crCardCustRefNum);
            setValue(bsMsg.crCardExprYrMth_B1, tMsg.crCardExprYrMth);

            String dsContrMachLvlNum = bsMsg.dsContrMachLvlNum_B0.getValue();
            BigDecimal dsContrPk = bsMsg.dsContrPk_B0.getValue();
            BigDecimal dsContrDtlPk = bsMsg.dsContrDtlPk_B0.getValue();

            index++;
            for (; index < sMsg.B.getValidCount(); index++) {
                bsMsg = sMsg.B.no(index);
                if (MACH_LVL_NUM_1.equals(dsContrMachLvlNum)) {
                    if (dsContrPk.compareTo(bsMsg.dsContrPk_B0.getValue()) != 0) {
                        break;
                    }
                }
                if (MACH_LVL_NUM_2.equals(dsContrMachLvlNum)) {
                    if (!hasValue(bsMsg.dsContrDtlPk_B0) || dsContrDtlPk.compareTo(bsMsg.dsContrDtlPk_B0.getValue()) != 0) {
                        break;
                    }
                }
                if (MACH_LVL_NUM_3.equals(dsContrMachLvlNum)) {
                    break;
                }
                if (ZYPConstant.CHKBOX_ON_Y.equals(bsMsg.xxChkBox_B0.getValue()) || ZYPConstant.CHKBOX_ON_Y.equals(bsMsg.xxChkBox_B1.getValue())) {
                    setValue(bsMsg.crCardCustRefNum_B1, tMsg.crCardCustRefNum);
                    setValue(bsMsg.crCardExprYrMth_B1, tMsg.crCardExprYrMth);
                }
            }

            copyFromBSMsgToASMsgForPaging(sMsg);

            int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
            copyFromASMsgToACMsg(cMsg, sMsg, pageFrom);
            cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
        }

        if ("OpenWin_CreditCard".equals(scrEventNm)) {
            setValue(cMsg.crCardCustRefNum_H, tMsg.crCardCustRefNum);
            setValue(cMsg.crCardExprYrMth_H, tMsg.crCardExprYrMth);
        }
    }

    /**
     * do process (Search)
     * @param cMsg NSAL0390CMsg
     * @param sMsg NSAL0390SMsg
     */
    private void doProcess_NSAL0390Scrn00_Search(NSAL0390CMsg cMsg, NSAL0390SMsg sMsg) {

        String smryLineFlg = ZYPConstant.FLG_OFF_N;
        setDetailListInfo(cMsg, sMsg, smryLineFlg);
    }

    /**
     * do process (SelectAllContract)
     * @param cMsg NSAL0390CMsg
     * @param sMsg NSAL0390SMsg
     */
    private void doProcess_NSAL0390Scrn00_SelectAllContract(NSAL0390CMsg cMsg, NSAL0390SMsg sMsg) {

        for (int i = 0; i < cMsg.A.length(); i++) {
            NSAL0390_ACMsg acMsg = cMsg.A.no(i);
            if (ZYPConstant.FLG_OFF_N.equals(acMsg.xxDplyCtrlFlg_A1.getValue()) || MACH_LVL_NUM_3.equals(acMsg.dsContrMachLvlNum_A0.getValue())) {
                continue;
            }
            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_H0.getValue())) {
                setValue(acMsg.xxChkBox_A0, ZYPConstant.CHKBOX_ON_Y);
            } else {
                acMsg.xxChkBox_A0.clear();
            }
        }

        copyCurrentPageToBSMsg(cMsg, sMsg);
        copyFromBSMsgToASMsgForPaging(sMsg);

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        copyFromASMsgToACMsg(cMsg, sMsg, pageFrom);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    /**
     * do process (SelectAllSerial)
     * @param cMsg NSAL0390CMsg
     * @param sMsg NSAL0390SMsg
     */
    private void doProcess_NSAL0390Scrn00_SelectAllSerial(NSAL0390CMsg cMsg, NSAL0390SMsg sMsg) {

        for (int i = 0; i < cMsg.A.length(); i++) {
            NSAL0390_ACMsg acMsg = cMsg.A.no(i);
            if (ZYPConstant.FLG_OFF_N.equals(acMsg.xxDplyCtrlFlg_A1.getValue()) || !MACH_LVL_NUM_3.equals(acMsg.dsContrMachLvlNum_A0.getValue())) {
                continue;
            }
            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_H1.getValue())) {
                setValue(acMsg.xxChkBox_A1, ZYPConstant.CHKBOX_ON_Y);
            } else {
                acMsg.xxChkBox_A1.clear();
            }
        }

        copyCurrentPageToBSMsg(cMsg, sMsg);
        copyFromBSMsgToASMsgForPaging(sMsg);

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        copyFromASMsgToACMsg(cMsg, sMsg, pageFrom);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    /**
     * do process (Apply To All)
     * @param cMsg NSAL0390CMsg
     * @param sMsg NSAL0390SMsg
     */
    private void doProcess_NSAL0390Scrn00_ApplyToAll(NSAL0390CMsg cMsg, NSAL0390SMsg sMsg) {

        copyCurrentPageToBSMsg(cMsg, sMsg);

        if (!checkSelectLine(cMsg, sMsg)) {
            return;
        }

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NSAL0390_BSMsg bsMsg = sMsg.B.no(i);
            if ((ZYPConstant.CHKBOX_ON_Y.equals(bsMsg.xxChkBox_B0.getValue()) || ZYPConstant.CHKBOX_ON_Y.equals(bsMsg.xxChkBox_B1.getValue())) && !ZYPConstant.FLG_ON_Y.equals(bsMsg.leaseCmpyFlg_B0.getValue())) {
                setValue(sMsg.B.no(i).crCardCustRefNum_B1, cMsg.crCardCustRefNum_H);
                setValue(sMsg.B.no(i).crCardExprYrMth_B1, cMsg.crCardExprYrMth_H);
            }
        }
        copyFromBSMsgToASMsgForPaging(sMsg);

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        copyFromASMsgToACMsg(cMsg, sMsg, pageFrom);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    // add start 2016/08/29 CSA Defect#11243
    /**
     * do process (Submit)
     * @param cMsg NSAL0390CMsg
     * @param sMsg NSAL0390SMsg
     */
    private void doProcess_NSAL0390Scrn00_CMN_Submit(NSAL0390CMsg cMsg, NSAL0390SMsg sMsg) {
    }
    // add end 2016/08/29 CSA Defect#11243

    /**
     * check Input Parameter
     * @param cMsg NSAL0390CMsg
     * @return No Error : true
     */
    private boolean checkInparam(NSAL0390CMsg cMsg) {

        if (cMsg.P.getValidCount() == 0) {
            cMsg.setMessageInfo(NSAM0219E, new String[] {NO_INPUT });
            return false;
        }

        return true;
    }

    private void setDetailListInfo(NSAL0390CMsg cMsg, NSAL0390SMsg sMsg, String smryLineFlg) {

        // Get targetContrList
        List<BigDecimal> dsContrPkList = new ArrayList<BigDecimal>();
        Map<BigDecimal, List<BigDecimal>> dsContrDtlPkMap = new HashMap<BigDecimal, List<BigDecimal>>();
        getTargetContrList(cMsg, dsContrPkList, dsContrDtlPkMap);

        // get Detail Data and Set SMsg
        getDetailDataList(cMsg, sMsg, dsContrPkList, dsContrDtlPkMap, smryLineFlg);

        copyFromBSMsgToASMsgForDisplay(sMsg);

        copyFromASMsgToACMsg(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void getTargetContrList(NSAL0390CMsg cMsg, List<BigDecimal> dsContrPkList, Map<BigDecimal, List<BigDecimal>> dsContrDtlPkMap) {

        for (int i = 0; i < cMsg.P.getValidCount(); i++) {
            BigDecimal dsContrPk = cMsg.P.no(i).dsContrPk_P1.getValue();
            BigDecimal dsContrDtlPk = cMsg.P.no(i).dsContrDtlPk_P1.getValue();

            // add DS_CONTR_PK
            if (!dsContrPkList.contains(dsContrPk)) {
                dsContrPkList.add(dsContrPk);
            }

            // add DS_CONTR_DTL_PK
            if (dsContrDtlPkMap.containsKey(dsContrPk)) {
                List<BigDecimal> dsContrDtlPkList = dsContrDtlPkMap.get(dsContrPk);
                if (!dsContrDtlPkList.contains(dsContrDtlPk)) {
                    dsContrDtlPkList.add(dsContrDtlPk);
                }
            } else {
                List<BigDecimal> dsContrDtlPkList = new ArrayList<BigDecimal>();
                if (hasValue(dsContrDtlPk)) {
                    dsContrDtlPkList.add(dsContrDtlPk);
                }
                dsContrDtlPkMap.put(dsContrPk, dsContrDtlPkList);
            }
        }
    }

    /**
     * get Detail Data List
     * @param cMsg NSAL0390CMsg
     * @param sMsg NSAL0390SMsg
     * @param dsContrDtlPkMap Map<BigDecimal, List<BigDecimal>>
     */
    private void getDetailDataList(NSAL0390CMsg cMsg, NSAL0390SMsg sMsg, List<BigDecimal> dsContrPkList, Map<BigDecimal, List<BigDecimal>> dsContrDtlPkMap, String smryLineFlg) {

        NSAL0390_BSMsgArray bsMsgArray = sMsg.B;
        ZYPTableUtil.clear(bsMsgArray);

        int indexCnt = 0;
        int recCnt = 0;

        List<Map<String, Object>> fstDataList = getFstDataList(cMsg, dsContrPkList);
        for (Map<String, Object> fstMap : fstDataList) {
            BigDecimal dsContrPk = (BigDecimal) fstMap.get("DS_CONTR_PK");

            // check recored count
            recCnt++;
            List<BigDecimal> dsContrDtlPkList = dsContrDtlPkMap.get(dsContrPk);
            List<Map<String, Object>> scdDataList = getScdDataList(cMsg, dsContrPk, dsContrDtlPkList);
            recCnt = recCnt + scdDataList.size();
            for (Map<String, Object> scdMap : scdDataList) {
                BigDecimal dsContrDtlPk = (BigDecimal) scdMap.get("DS_CONTR_DTL_PK");
                List<Map<String, Object>> trdDataList = getTrdDataList(cMsg, dsContrPk, dsContrDtlPk);
                recCnt = recCnt + trdDataList.size();
            }
            if (bsMsgArray.length() < recCnt) {
                break;
            }

            // set First Line
            NSAL0390_BSMsg bsMsg = bsMsgArray.no(indexCnt);

            setValue(bsMsg.dsContrPk_B0, dsContrPk);
            setValue(bsMsg.dsContrNum_B0, (String) fstMap.get("DS_CONTR_NUM"));
            setValue(bsMsg.altPayerCustCd_B0, (String) fstMap.get("ALT_PAYER_CUST_CD"));
            // START 2016/06/09 T.Kanasaka [QC#9708, ADD]
            setValue(bsMsg.dsAcctNum_B0, (String) fstMap.get("DS_ACCT_NUM"));
            // END 2016/06/09 T.Kanasaka [QC#9708, ADD]
            // add start 2016/08/29 CSA Defect#11243
            setValue(bsMsg.sellToCustCd_B0, (String) fstMap.get("SELL_TO_CUST_CD"));
            // add end 2016/08/29 CSA Defect#11243
            setValue(bsMsg.dsContrCrCardPoPk_B0, (BigDecimal) fstMap.get("DS_CONTR_CR_CARD_PO_PK"));
            setValue(bsMsg.crCardCustRefNum_B0, (String) fstMap.get("CR_CARD_CUST_REF_NUM"));
            setValue(bsMsg.crCardExprYrMth_B0, (String) fstMap.get("CR_CARD_EXPR_YR_MTH"));
            setValue(bsMsg.leaseCmpyFlg_B0, (String) fstMap.get("LEASE_CMPY_FLG"));
            // START 2018/03/16 M.Naito [QC#20496, ADD]
            setValue(bsMsg.xxCntDplyFlg_B0,  (String) fstMap.get("XX_CNT_DPLY_FLG"));
            // END 2018/03/16 M.Naito [QC#20496, ADD]
            setValue(bsMsg.ezUpTimeZone_B0, (String) fstMap.get("EZUPTIMEZONE"));
            setValue(bsMsg.ezUpTime_B0, (String) fstMap.get("EZUPTIME"));
            setValue(bsMsg.xxNum_B0, BigDecimal.valueOf(indexCnt++));
            setValue(bsMsg.xxDplyCtrlFlg_B0, ZYPConstant.FLG_ON_Y);
            setValue(bsMsg.xxDplyCtrlFlg_B1, checkContrStsActive((String) fstMap.get("DS_CONTR_CTRL_STS_CD")));
            setValue(bsMsg.xxSmryLineFlg_B0, smryLineFlg);
            setValue(bsMsg.dsContrMachLvlNum_B0, MACH_LVL_NUM_1);
            // START 2016/12/05 K.Ochiai [QC#14204, ADD]
            setValue(bsMsg.xxRecHistCratTs_B0, (String) fstMap.get("XX_REC_HIST_CRAT_TS"));
            setValue(bsMsg.xxRecHistCratByNm_B0, (String) fstMap.get("XX_REC_HIST_CRAT_BY_NM"));
            setValue(bsMsg.xxRecHistUpdTs_B0, (String) fstMap.get("XX_REC_HIST_UPD_TS"));
            setValue(bsMsg.xxRecHistUpdByNm_B0, (String) fstMap.get("XX_REC_HIST_UPD_BY_NM"));
            setValue(bsMsg.xxRecHistTblNm_B0, (String) fstMap.get("XX_REC_HIST_TBL_NM"));
            // END 2016/12/05 K.Ochiai [QC#14204, ADD]

            String crCardCustRefNum = bsMsg.crCardCustRefNum_B0.getValue();
            String crCardExprYrMth = bsMsg.crCardExprYrMth_B0.getValue();
            String altPayerCustCd = bsMsg.altPayerCustCd_B0.getValue();
            // START 2016/06/09 T.Kanasaka [QC#9708, ADD]
            String dsAcctNum = bsMsg.dsAcctNum_B0.getValue();
            // END 2016/06/09 T.Kanasaka [QC#9708, ADD]

            // set Second Line
            for (Map<String, Object> scdMap : scdDataList) {
                bsMsg = sMsg.B.no(indexCnt);

                setValue(bsMsg.dsContrPk_B0, dsContrPk);
                setValue(bsMsg.dsContrDtlPk_B0, (BigDecimal) scdMap.get("DS_CONTR_DTL_PK"));
                setValue(bsMsg.altPayerCustCd_B0, altPayerCustCd);
                // START 2016/06/09 T.Kanasaka [QC#9708, ADD]
                setValue(bsMsg.dsAcctNum_B0, dsAcctNum);
                // END 2016/06/09 T.Kanasaka [QC#9708, ADD]
                // add start 2016/08/29 CSA Defect#11243
                setValue(bsMsg.sellToCustCd_B0, (String) scdMap.get("SELL_TO_CUST_CD"));
                // add end 2016/08/29 CSA Defect#11243
                setValue(bsMsg.serNum_B0, (String) scdMap.get("SER_NUM"));
                setValue(bsMsg.dsContrCrCardPoPk_B0, (BigDecimal) scdMap.get("DS_CONTR_CR_CARD_PO_PK"));
                // mod start 2016/08/29 CSA Defect#11243
                setValue(bsMsg.crCardCustRefNum_B0, selectItemValue((BigDecimal) scdMap.get("DS_CONTR_CR_CARD_PO_PK"), (String) scdMap.get("CR_CARD_CUST_REF_NUM"), crCardCustRefNum));
                setValue(bsMsg.crCardExprYrMth_B0, selectItemValue((BigDecimal) scdMap.get("DS_CONTR_CR_CARD_PO_PK"), (String) scdMap.get("CR_CARD_EXPR_YR_MTH"), crCardExprYrMth));
                // mod end 2016/08/29 CSA Defect#11243
                setValue(bsMsg.leaseCmpyFlg_B0, (String) scdMap.get("LEASE_CMPY_FLG"));
                // START 2018/03/16 M.Naito [QC#20496, ADD]
                setValue(bsMsg.xxCntDplyFlg_B0,  (String) scdMap.get("XX_CNT_DPLY_FLG"));
                // END 2018/03/16 M.Naito [QC#20496, ADD]
                setValue(bsMsg.ezUpTimeZone_B0, (String) scdMap.get("EZUPTIMEZONE"));
                setValue(bsMsg.ezUpTime_B0, (String) scdMap.get("EZUPTIME"));
                setValue(bsMsg.xxNum_B0, BigDecimal.valueOf(indexCnt++));
                setValue(bsMsg.xxDplyCtrlFlg_B0, ZYPConstant.FLG_ON_Y);
                setValue(bsMsg.xxDplyCtrlFlg_B1, checkContrStsActive((String) fstMap.get("DS_CONTR_CTRL_STS_CD")));
                setValue(bsMsg.xxSmryLineFlg_B0, smryLineFlg);
                setValue(bsMsg.dsContrMachLvlNum_B0, MACH_LVL_NUM_2);

                crCardCustRefNum = bsMsg.crCardCustRefNum_B0.getValue();
                crCardExprYrMth = bsMsg.crCardExprYrMth_B0.getValue();
                BigDecimal dsContrDtlPk = bsMsg.dsContrDtlPk_B0.getValue();
                // START 2016/12/05 K.Ochiai [QC#14204, ADD]
                setValue(bsMsg.xxRecHistCratTs_B0, (String) scdMap.get("XX_REC_HIST_CRAT_TS"));
                setValue(bsMsg.xxRecHistCratByNm_B0, (String) scdMap.get("XX_REC_HIST_CRAT_BY_NM"));
                setValue(bsMsg.xxRecHistUpdTs_B0, (String) scdMap.get("XX_REC_HIST_UPD_TS"));
                setValue(bsMsg.xxRecHistUpdByNm_B0, (String) scdMap.get("XX_REC_HIST_UPD_BY_NM"));
                setValue(bsMsg.xxRecHistTblNm_B0, (String) scdMap.get("XX_REC_HIST_TBL_NM"));
                // END 2016/12/05 K.Ochiai [QC#14204, ADD]

                // set Third Line
                for (Map<String, Object> trdMap : getTrdDataList(cMsg, dsContrPk, dsContrDtlPk)) {
                    bsMsg = sMsg.B.no(indexCnt);

                    setValue(bsMsg.dsContrPk_B0, dsContrPk);
                    setValue(bsMsg.dsContrDtlPk_B0, dsContrDtlPk);
                    setValue(bsMsg.altPayerCustCd_B0, altPayerCustCd);
                    // START 2016/06/09 T.Kanasaka [QC#9708, ADD]
                    setValue(bsMsg.dsAcctNum_B0, dsAcctNum);
                    // END 2016/06/09 T.Kanasaka [QC#9708, ADD]
                    // add start 2016/08/29 CSA Defect#11243
                    setValue(bsMsg.sellToCustCd_B0, (String) trdMap.get("SELL_TO_CUST_CD"));
                    // add end 2016/08/29 CSA Defect#11243
                    setValue(bsMsg.dsContrBllgMtrPk_B0, (BigDecimal) trdMap.get("DS_CONTR_BLLG_MTR_PK"));
                    // mod start 2016/02/29 CSA Defect#2684
                    setValue(bsMsg.mtrLbDescTxt_B0, (String) trdMap.get("TRD_NM"));
                    // mod end 2016/02/29 CSA Defect#2684
                    setValue(bsMsg.dsContrCrCardPoPk_B0, (BigDecimal) trdMap.get("DS_CONTR_CR_CARD_PO_PK"));
                    // mod start 2016/08/29 CSA Defect#11243
                    setValue(bsMsg.crCardCustRefNum_B0, selectItemValue((BigDecimal) trdMap.get("DS_CONTR_CR_CARD_PO_PK"), (String) trdMap.get("CR_CARD_CUST_REF_NUM"), crCardCustRefNum));
                    setValue(bsMsg.crCardExprYrMth_B0, selectItemValue((BigDecimal) trdMap.get("DS_CONTR_CR_CARD_PO_PK"), (String) trdMap.get("CR_CARD_EXPR_YR_MTH"), crCardExprYrMth));
                    // mod end 2016/08/29 CSA Defect#11243
                    setValue(bsMsg.leaseCmpyFlg_B0, (String) trdMap.get("LEASE_CMPY_FLG"));
                    // START 2018/03/16 M.Naito [QC#20496, ADD]
                    setValue(bsMsg.xxCntDplyFlg_B0,  (String) trdMap.get("XX_CNT_DPLY_FLG"));
                    // END 2018/03/16 M.Naito [QC#20496, ADD]
                    setValue(bsMsg.ezUpTimeZone_B0, (String) trdMap.get("EZUPTIMEZONE"));
                    setValue(bsMsg.ezUpTime_B0, (String) trdMap.get("EZUPTIME"));
                    setValue(bsMsg.xxNum_B0, BigDecimal.valueOf(indexCnt++));
                    setValue(bsMsg.xxDtlNm_B0, (String) trdMap.get("TRD_TP"));
                    setValue(bsMsg.xxDplyCtrlFlg_B0, ZYPConstant.FLG_OFF_N);
                    setValue(bsMsg.xxDplyCtrlFlg_B1, checkContrStsActive((String) fstMap.get("DS_CONTR_CTRL_STS_CD")));
                    setValue(bsMsg.xxSmryLineFlg_B0, ZYPConstant.FLG_OFF_N);
                    setValue(bsMsg.dsContrMachLvlNum_B0, MACH_LVL_NUM_3);
                    // START 2016/12/05 K.Ochiai [QC#14204, ADD]
                    setValue(bsMsg.xxRecHistCratTs_B0, (String) trdMap.get("XX_REC_HIST_CRAT_TS"));
                    setValue(bsMsg.xxRecHistCratByNm_B0, (String) trdMap.get("XX_REC_HIST_CRAT_BY_NM"));
                    setValue(bsMsg.xxRecHistUpdTs_B0, (String) trdMap.get("XX_REC_HIST_UPD_TS"));
                    setValue(bsMsg.xxRecHistUpdByNm_B0, (String) trdMap.get("XX_REC_HIST_UPD_BY_NM"));
                    setValue(bsMsg.xxRecHistTblNm_B0, (String) trdMap.get("XX_REC_HIST_TBL_NM"));
                    // END 2016/12/05 K.Ochiai [QC#14204, ADD]
                }
            }
        }

        bsMsgArray.setValidCount(indexCnt);
    }

    /**
     * get First Data List
     * @param cMsg NSAL0390CMsg
     * @param dsContrPkList List<BigDecimal>
     * @return Exist Data : true
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getFstDataList(NSAL0390CMsg cMsg, List<BigDecimal> dsContrPkList) {

        // get First Data
        S21SsmEZDResult ssmFstResult = NSAL0390Query.getInstance().getFstData(cMsg, dsContrPkList);

        if (ssmFstResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NSAM0219E, new String[] {INVALID_PARAM });
            return new ArrayList<Map<String, Object>>();
        }

        return (List<Map<String, Object>>) ssmFstResult.getResultObject();
    }

    /**
     * get Second Data List
     * @param cMsg NSAL0390CMsg
     * @param dsContrPk BigDecimal
     * @param dsContrDtlPkList List<BigDecimal>
     * @return Second Data List
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getScdDataList(NSAL0390CMsg cMsg, BigDecimal dsContrPk, List<BigDecimal> dsContrDtlPkList) {

        // get Second Data
        S21SsmEZDResult ssmScdResult = NSAL0390Query.getInstance().getScdData(cMsg, dsContrPk, dsContrDtlPkList);

        if (ssmScdResult.isCodeNotFound()) {
            return new ArrayList<Map<String, Object>>();
        }

        return (List<Map<String, Object>>) ssmScdResult.getResultObject();
    }

    /**
     * get Third Data List
     * @param cMsg NSAL0390CMsg
     * @param dsContrPk BigDecimal
     * @param dsContrDtlPk DS Contract Detail PK
     * @return Third Data List
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getTrdDataList(NSAL0390CMsg cMsg, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {

        // get Third Data
        S21SsmEZDResult ssmScdResult = NSAL0390Query.getInstance().getTrdData(cMsg, dsContrPk, dsContrDtlPk);

        if (ssmScdResult.isCodeNotFound()) {
            return new ArrayList<Map<String, Object>>();
        }

        return (List<Map<String, Object>>) ssmScdResult.getResultObject();
    }

    private static DS_CR_CARDTMsg getCrCard(String glblCmpyCd, NSAL0390CMsg cMsg) {
        DS_CR_CARDTMsg prmTMsg = new DS_CR_CARDTMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.dsCrCardPk, cMsg.dsCrCardPk);
        return (DS_CR_CARDTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

}
