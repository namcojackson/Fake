/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0490;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0490.common.NSAL0490CommonLogic;
import business.blap.NSAL0490.constant.NSAL0490Constant;
import business.blap.NSAL0490.constant.NSAL0490Constant.ACTV_STS;
import business.db.DS_MDLTMsg;
import business.db.DS_MDL_EOLTMsg;
import business.db.MDL_NMTMsg;
import business.db.MDL_NMTMsgArray;
import business.db.ORD_TAKE_MDSETMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MDL_EOL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MDL_PAPER_SIZE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_LB_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MDL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_SEG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/09   Fujitsu         T.Yoshida       Create          N/A
 * 2015/10/07   Hitachi         Y.Tsuchimoto    Update          N/A
 * 2015/11/25   Hitachi         K.Yamada        Update          CSA QC#1150
 * 2015/11/30   Hitachi         K.Yamada        Update          CSA QC#1152
 * 2016/02/10   Hitachi         A.Kohinata      Update          CSA QC#1157
 * 2016/05/19   Hitachi         K.Kasai         Update          CSA QC#447
 * 2016/05/30   Hitachi         K.Kasai         Update          CSA QC#6675
 * 2016/09/16   Hitachi         N.Arai          Update          QC#11616
 * 2016/12/02   Hitachi         K.Kojima        Update          QC#14204
 * 2017/01/16   Hitachi         T.Mizuki        Update          QC#16618
 * 2017/01/23   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/01/25   Hitachi         T.Mizuki        Update          QC#17283
 * 2017/08/03   CITS            S.Endo          Update          Sol#072(QC#18386)
 * 2017/11/02   CITS            S.Katsuma       Update          SOL#170(QC#18624)
 * 2018/01/15   Hitachi         K.Kojima        Update          QC#23352
 * 2018/02/16   Hitachi         M.Kidokoro      Update          QC#24249
 * 2019/05/31   Hitachi         A.Kohinta       Update          QC#50617
 * 2024/03/14   Hitachi         K.Watanabe      Update          QC#63542
 *</pre>
 */
public class NSAL0490BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            NSAL0490CMsg bizMsg = (NSAL0490CMsg) cMsg;
            NSAL0490SMsg glblMsg = (NSAL0490SMsg) sMsg;
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0490_INIT".equals(screenAplID)) {
                doProcess_NSAL0490_INIT(bizMsg, glblMsg);
            } else if ("NSAL0490Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL0490Scrn00_Search(bizMsg, glblMsg);
            } else if ("NSAL0490Scrn00_TAB_ItemConfig".equals(screenAplID)) {
                doProcess_NSAL0490Scrn00_TAB_ItemConfig(bizMsg, glblMsg);
            } else if ("NSAL0490Scrn00_TAB_SvcRules".equals(screenAplID)) {
                doProcess_NSAL0490Scrn00_TAB_SvcRules(bizMsg, glblMsg);
            } else if ("NSAL0490Scrn00_TAB_SupplyMap".equals(screenAplID)) {
                doProcess_NSAL0490Scrn00_TAB_SupplyMap(bizMsg, glblMsg);
            // add start 2016/05/19 CSA Defect#447
            } else if ("NSAL0490Scrn00_TAB_EndOfLife".equals(screenAplID)) {
                doProcess_NSAL0490Scrn00_TAB_EndOfLife(bizMsg, glblMsg);
            // add end 2016/05/19 CSA Defect#447
            } else if ("NSAL0490Scrn00_InsertRow_Parent".equals(screenAplID)) {
                doProcess_NSAL0490Scrn00_InsertRow_Parent(bizMsg, glblMsg);
            } else if ("NSAL0490Scrn00_InsertRow_Child".equals(screenAplID)) {
                doProcess_NSAL0490Scrn00_InsertRow_Child(bizMsg, glblMsg);
            } else if ("NSAL0490Scrn00_InsertRow_SupplyMap".equals(screenAplID)) {
                doProcess_NSAL0490Scrn00_InsertRow_SupplyMap(bizMsg, glblMsg);
            } else if ("NSAL0490Scrn00_DeleteRow_ItemConfig".equals(screenAplID)) {
                doProcess_NSAL0490Scrn00_DeleteRow_ItemConfig(bizMsg, glblMsg);
            } else if ("NSAL0490Scrn00_DeleteRow_SupplyMap".equals(screenAplID)) {
                doProcess_NSAL0490Scrn00_DeleteRow_SupplyMap(bizMsg, glblMsg);
            //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
            } else if ("NSAL0490Scrn00_InsertRow_Criticality".equals(screenAplID)) {
                doProcess_NSAL0490Scrn00_InsertRow_Criticality(bizMsg, glblMsg);
            } else if ("NSAL0490Scrn00_DeleteRow_Criticality".equals(screenAplID)) {
                doProcess_NSAL0490Scrn00_DeleteRow_Criticality(bizMsg, glblMsg);
            //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END
            } else if ("NSAL0490Scrn00_Setting_MdseInfo".equals(screenAplID)) {
                doProcess_NSAL0490Scrn00_Setting_MdseInfo(bizMsg, glblMsg);
            // START 2024/03/14 K.Watanabe [QC#63542, ADD]
            } else if ("NSAL0490Scrn00_CopyModel".equals(screenAplID)) {
                doProcess_NSAL0490Scrn00_CopyModel(bizMsg, glblMsg);
            // END 2024/03/14 K.Watanabe [QC#63542, ADD]
            } else if ("NSAL0490_NMAL6050".equals(screenAplID)) {
                doProcess_NSAL0490_NMAL6050(bizMsg, glblMsg);
            } else if ("NSAL0490Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0490Scrn00_PagePrev(bizMsg, glblMsg);
            } else if ("NSAL0490Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0490Scrn00_PageNext(bizMsg, glblMsg);
            } else if ("NSAL0490Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL0490Scrn00_CMN_Clear(bizMsg, glblMsg);
            // START 2017/01/23 K.Ochiai [QC#16331,ADD]
            } else if ("NSAL0490Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0490Scrn00_CMN_Reset(bizMsg, glblMsg);
            // END 2017/01/23 K.Ochiai [QC#16331,ADD]
            } else if ("NSAL0490Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0490Scrn00_CMN_Submit(bizMsg, glblMsg);
            // mod start 2016/03/01 CSA Defect#3586
            // 2015/10/07 CSA Y.Tsuchimoto Add Start
            } else if ("NSAL0490_NMAL6800".equals(screenAplID)) {
                doProcess_NSAL0490_NMAL6800(bizMsg, glblMsg);
            // 2015/10/07 CSA Y.Tsuchimoto Add End
            // mod end 2016/03/01 CSA Defect#3586
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Init)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void doProcess_NSAL0490_INIT(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        cMsg.slsDt.setValue(ZYPDateUtil.getSalesDate());
        cMsg.xxMaxSrchCnt.setValue(sMsg.A.length());

        createPullDown(cMsg);

        if (ZYPCommonFunc.hasValue(cMsg.mdlNm_PR)) {
            ZYPEZDItemValueSetter.setValue(cMsg.mdlNm, cMsg.mdlNm_PR);
            doProcess_NSAL0490Scrn00_Search(cMsg, sMsg);
        } else {
            createItemConfigEmptyLine(cMsg, sMsg);
            ZYPEZDItemValueSetter.setValue(cMsg.xxRsltFlg, ZYPConstant.FLG_OFF_N);
        }
    }

    /**
     * do process (Search)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void doProcess_NSAL0490Scrn00_Search(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        if (!getHdrData(cMsg, sMsg)) {
            return;
        }

        doProcess_NSAL0490Scrn00_TAB_ItemConfig(cMsg, sMsg);

        //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
        if (!NSAL0490CommonLogic.getCriticalityData(cMsg, sMsg)) {
            return;
        }
        //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END

    }

    /**
     * do process (Item Configurations Tab)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void doProcess_NSAL0490Scrn00_TAB_ItemConfig(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.mdlId)) {
            return;
        }

        getItemConfigData(cMsg, sMsg);
    }

    /**
     * do process (Service Rules Tab)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void doProcess_NSAL0490Scrn00_TAB_SvcRules(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        ZYPCodeDataUtil.createPulldownList(DS_MDL_PAPER_SIZE.class, cMsg.dsMdlPaperSizeCd_PL, cMsg.dsMdlPaperSizeDescTxt_PL);
        getSvcRulesData(cMsg);
    }

    /**
     * do process (Supply Mapping Tab)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void doProcess_NSAL0490Scrn00_TAB_SupplyMap(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        getSupplyMapData(cMsg, sMsg);
    }

    // add start 2016/05/19 CSA Defect#447
    /**
     * do process (End Of Life Tab)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void doProcess_NSAL0490Scrn00_TAB_EndOfLife(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        getEndOfLifeData(cMsg, sMsg);
    }
    // add end 2016/05/19 CSA Defect#447

    /**
     * do process (Insert Row Parent)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void doProcess_NSAL0490Scrn00_InsertRow_Parent(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        NSAL0490CommonLogic.saveCurrentPageToSMsgItemConfig(cMsg, sMsg);

        // START 2018/02/16 M.Kidokoro [QC#24249,ADD]
        if (!checkDtlLength(cMsg, sMsg, NSAL0490Constant.PARENT_ITEM_LINE)) {
            return;
        }
        // END 2018/02/16 M.Kidokoro [QC#24249,ADD]

        // del start 2019/05/31 QC#50617
        //if (NSAL0490CommonLogic.checkItemCombination(cMsg, sMsg)) {
        //    return;
        //}
        // del end 2019/05/31 QC#50617

        int nextIdx = sMsg.A.getValidCount();
        int maxLineNum = Integer.parseInt(sMsg.A.no(nextIdx - 1).xxLineNum_A.getValue());

        // set SMsg
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(nextIdx).xxFlgNm_A, NSAL0490Constant.DTL_TP_PRNT);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(nextIdx).xxLineNum_A, String.valueOf(++maxLineNum));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(nextIdx).effFromDt_A, cMsg.slsDt);
        sMsg.A.setValidCount(nextIdx + 1);

        int startIdx = getPageStartRowIndexItemConfig(cMsg, nextIdx);
        copySMsgArrayToCMsgItemConfig(cMsg, sMsg, startIdx);
    }

    /**
     * do process (Insert Row Child)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void doProcess_NSAL0490Scrn00_InsertRow_Child(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        int selectIndex = cMsg.xxRadioBtn_A.getValueInt();
        int selectLineNum = Integer.parseInt(cMsg.A.no(selectIndex).xxLineNum_A.getValue());

        // START 2018/02/16 M.Kidokoro [QC#24249,MOD]
//        if (!checkChildDtlLength(cMsg, sMsg, selectLineNum)) {
        if (!checkDtlLength(cMsg, sMsg, NSAL0490Constant.CHILD_ITEM_LINE)) {
        // END 2018/02/16 M.Kidokoro [QC#24249,MOD]
            return;
        }

        // get select Attach Parent MDSE Code
        NSAL0490CommonLogic.saveCurrentPageToSMsgItemConfig(cMsg, sMsg);

        // set SMsg
        setSMsgForInsertChildRow(cMsg, sMsg, selectLineNum);

        int startIdx = getPageStartRowIndexItemConfig(cMsg, sMsg.A.getValidCount() - 1);
        copySMsgArrayToCMsgItemConfig(cMsg, sMsg, startIdx);
    }

    /**
     * do process (Insert Row Supply Mapping)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void doProcess_NSAL0490Scrn00_InsertRow_SupplyMap(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        NSAL0490CommonLogic.saveCurrentPageToSMsgSupplyMap(cMsg, sMsg);

        int nextIdx = sMsg.B.getValidCount();

        // set SMsg
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(nextIdx).effFromDt_B, cMsg.slsDt);
        sMsg.B.setValidCount(nextIdx + 1);

        int startIdx = getPageStartRowIndexSupplyMap(cMsg, nextIdx);
        copySMsgArrayToCMsgSupplyMap(cMsg, sMsg, startIdx);
    }

    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
    /**
     * do process (Insert Row Supply Mapping)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void doProcess_NSAL0490Scrn00_InsertRow_Criticality(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        NSAL0490CommonLogic.saveCMsgToSMsgCriticality(cMsg, sMsg);

        int nextIdx = sMsg.D.getValidCount();

        if (nextIdx == sMsg.D.length()) {
            cMsg.setMessageInfo(NSAL0490Constant.NSAM0320E, new String[] {"Item" , String.valueOf(sMsg.D.length())});
            return;
        }
        
        // set SMsg
        sMsg.D.setValidCount(nextIdx + 1);
        copySMsgArrayToCMsgCriticality(sMsg, cMsg);
    }
    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END

    /**
     * do process (Delete Row Item Configurations)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void doProcess_NSAL0490Scrn00_DeleteRow_ItemConfig(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        int selectIndex = cMsg.xxRadioBtn_A.getValueInt();
        String dtlType = cMsg.A.no(selectIndex).xxFlgNm_A.getValue();

        // check for Delete Row
        if (!checkDeleteRowItemConfig(cMsg, sMsg, selectIndex, dtlType)) {
            return;
        }

        NSAL0490CommonLogic.saveCurrentPageToSMsgItemConfig(cMsg, sMsg);

        int maxLength = cMsg.A.length();
        int currentPageNum = getCurrentPageNum(cMsg, cMsg.xxPageShowFromNum_A.getValueInt(), maxLength);
        selectIndex += maxLength * (currentPageNum - 1);

        // get Delete Total Number
        int deleteTotalNum = getDeleteTotalNum(sMsg, dtlType, selectIndex);

        // clear
        for (int cnt = selectIndex + deleteTotalNum; cnt < sMsg.A.getValidCount(); cnt++) {
            EZDMsg.copy(sMsg.A.no(cnt), null, sMsg.A.no(cnt - deleteTotalNum), null);
        }
        sMsg.A.setValidCount(sMsg.A.getValidCount() - deleteTotalNum);

        // rebuild the sMsg.
        NSAL0490SMsg bakSMsg = (NSAL0490SMsg) sMsg.clone();
        ZYPTableUtil.clear(sMsg.A);
        int validCnt = bakSMsg.A.getValidCount();
        for (int cnt = 0; cnt < validCnt; cnt++) {
            EZDMsg.copy(bakSMsg.A.no(cnt), null, sMsg.A.no(cnt), null);
        }
        sMsg.A.setValidCount(validCnt);

        int startIdx = getPageStartRowIndexItemConfig(cMsg, sMsg.A.getValidCount() - 1);
        copySMsgArrayToCMsgItemConfig(cMsg, sMsg, startIdx);
    }

    /**
     * do process (Delete Row Supply Mapping)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void doProcess_NSAL0490Scrn00_DeleteRow_SupplyMap(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        int selectIndex = cMsg.xxRadioBtn_B.getValueInt();

        // check registered
        if (ZYPCommonFunc.hasValue(cMsg.B.no(selectIndex).dsMdlSplyRelnPk_B)) {
            cMsg.setMessageInfo(NSAL0490Constant.NSAM0321E);
            return;
        }

        NSAL0490CommonLogic.saveCurrentPageToSMsgSupplyMap(cMsg, sMsg);

        int maxLength = cMsg.B.length();
        int currentPageNum = getCurrentPageNum(cMsg, cMsg.xxPageShowFromNum_B.getValueInt(), maxLength);
        selectIndex += maxLength * (currentPageNum - 1);

        // clear
        for (int cnt = selectIndex + 1; cnt < sMsg.B.getValidCount(); cnt++) {
            EZDMsg.copy(sMsg.B.no(cnt), null, sMsg.B.no(cnt - 1), null);
        }
        sMsg.B.setValidCount(sMsg.B.getValidCount() - 1);

        // rebuild the sMsg.
        NSAL0490SMsg bakSMsg = (NSAL0490SMsg) sMsg.clone();
        ZYPTableUtil.clear(sMsg.B);
        int validCnt = bakSMsg.B.getValidCount();
        for (int cnt = 0; cnt < validCnt; cnt++) {
            EZDMsg.copy(bakSMsg.B.no(cnt), null, sMsg.B.no(cnt), null);
        }
        sMsg.B.setValidCount(validCnt);

        int startIdx = getPageStartRowIndexSupplyMap(cMsg, sMsg.B.getValidCount() - 1);
        copySMsgArrayToCMsgSupplyMap(cMsg, sMsg, startIdx);
    }

    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
    /**
     * do process (Delete Row Criticality)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void doProcess_NSAL0490Scrn00_DeleteRow_Criticality(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {


        NSAL0490CommonLogic.saveCMsgToSMsgCriticality(cMsg, sMsg);

        // clear
        int noChangeRowCount = 0;
        NSAL0490SMsg newSMsg = new NSAL0490SMsg();
        for (int cnt = 0; cnt < sMsg.D.getValidCount(); cnt++) {
            if (!ZYPConstant.CHKBOX_ON_Y.equals(sMsg.D.no(cnt).xxChkBox_D.getValue())) {
                EZDMsg.copy(sMsg.D.no(cnt), null, newSMsg.D.no(newSMsg.D.getValidCount()), null);
                newSMsg.D.setValidCount(newSMsg.D.getValidCount() + 1);
                noChangeRowCount = noChangeRowCount + 1;
            }
        }
        //no check for delete
        if (noChangeRowCount == sMsg.D.getValidCount()) {
            cMsg.setMessageInfo(NSAL0490Constant.NSAM0199E, new String[] {NSAL0490Constant.MDSE_CD });
            return;
        }

        // rebuild the sMsg.
        ZYPTableUtil.clear(sMsg.D);
        int validCnt = newSMsg.D.getValidCount();
        for (int cnt = 0; cnt < validCnt; cnt++) {
            EZDMsg.copy(newSMsg.D.no(cnt), null, sMsg.D.no(cnt), null);
        }
        sMsg.D.setValidCount(validCnt);

        copySMsgArrayToCMsgCriticality(sMsg, cMsg);
    }
    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END

    /**
     * do process (Setting MDSE Info)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void doProcess_NSAL0490Scrn00_Setting_MdseInfo(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        String dplyTab = cMsg.xxDplyTab.getValue();
        if (NSAL0490Constant.TAB_ITEM_CONFIG.equals(dplyTab)) {
            setMdseInfoForItemConfig(cMsg);
        } else if (NSAL0490Constant.TAB_SUPPLY_MAP.equals(dplyTab)) {
            setMdseInfoForSupplyMap(cMsg);
        //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
        } else if (NSAL0490Constant.TAB_CRITICALITY.equals(dplyTab)) {
            setMdseInfoForCriticality(cMsg);
        //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END
        }
    }

    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
    private void setMdseInfoForCriticality(NSAL0490CMsg cMsg) {
        int selectIndex = cMsg.xxCellIdx_D.getValueInt();
        NSAL0490_DCMsg dcMsg = cMsg.D.no(selectIndex);

        EZDCStringItem targetMdseCd = dcMsg.mdseCd_D;

        if (!NSAL0490CommonLogic.isExistAllMdseV(cMsg, targetMdseCd.getValue())) {
            targetMdseCd.setErrorInfo(1, NSAL0490Constant.NSAM0063E, new String[] {NSAL0490Constant.MDSE_CD, NSAL0490Constant.MDSE });
            return;
        }

        // get Mdse Info Data
        setMdseInfoForCriticality(cMsg, dcMsg, targetMdseCd.getValue());
    }
    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END

    /**
     * do process (NMAL6050)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void doProcess_NSAL0490_NMAL6050(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        String dplyTab = cMsg.xxDplyTab.getValue();
        if (NSAL0490Constant.TAB_ITEM_CONFIG.equals(dplyTab)) {
            NSAL0490_ACMsg acMsg = cMsg.A.no(cMsg.xxCellIdx_A.getValueInt());
            setMdseInfoForItemConfig(cMsg, acMsg, cMsg.xxCondCd.getValue());
        } else if (NSAL0490Constant.TAB_SUPPLY_MAP.equals(dplyTab)) {
            NSAL0490_BCMsg bcMsg = cMsg.B.no(cMsg.xxCellIdx_B.getValueInt());
            setMdseInfoSupplyMap(cMsg, bcMsg, cMsg.xxCondCd.getValue());
        }
    }

    // mod start 2016/03/01 CSA Defect#3586
    // 2015/10/07 CSA Y.Tsuchimoto Add Start
    /**
     * do process (NMAL6800)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void doProcess_NSAL0490_NMAL6800(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        String dplyTab = cMsg.xxDplyTab.getValue();
        if (NSAL0490Constant.TAB_ITEM_CONFIG.equals(dplyTab)) {
            NSAL0490_ACMsg acMsg = cMsg.A.no(cMsg.xxCellIdx_A.getValueInt());
            String mdseCd = null;
            if (NSAL0490Constant.DTL_TP_PRNT.equals(cMsg.A.no(cMsg.xxCellIdx_A.getValueInt()).xxFlgNm_A.getValue())) {
                mdseCd = cMsg.A.no(cMsg.xxCellIdx_A.getValueInt()).prntMdseCd_A.getValue();
            } else {
                mdseCd = cMsg.A.no(cMsg.xxCellIdx_A.getValueInt()).childMdseCd_A.getValue();
            }
            setMdseInfoForItemConfig(cMsg, acMsg, mdseCd);
        } else if (NSAL0490Constant.TAB_SUPPLY_MAP.equals(dplyTab)) {
            NSAL0490_BCMsg bcMsg = cMsg.B.no(cMsg.xxCellIdx_B.getValueInt());
            setMdseInfoSupplyMap(cMsg, bcMsg, cMsg.B.no(cMsg.xxCellIdx_B.getValueInt()).mdseCd_B.getValue());
        //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
        } else if (NSAL0490Constant.TAB_CRITICALITY.equals(dplyTab)) {
            NSAL0490_DCMsg dcMsg = cMsg.D.no(cMsg.xxCellIdx_D.getValueInt());
            setMdseInfoForCriticality(cMsg, dcMsg, cMsg.D.no(cMsg.xxCellIdx_D.getValueInt()).mdseCd_D.getValue());
        //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END
        }
    }

    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
    private void setMdseInfoForCriticality(NSAL0490CMsg cMsg, NSAL0490_DCMsg dcMsg, String targetMdseCd) {

        S21SsmEZDResult ssmResult = NSAL0490Query.getInstance().getItemInfoForCriticality(cMsg.glblCmpyCd.getValue(), targetMdseCd);
        if (ssmResult.isCodeNormal()) {
            Map<String, String> resultObj = (Map<String, String>) ssmResult.getResultObject();
            ZYPEZDItemValueSetter.setValue(dcMsg.mdseDescShortTxt_D, resultObj.get("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(dcMsg.backOrdImpctTpDescTxt_D1, resultObj.get("BACK_ORD_IMPCT_TP_DESC_TXT"));
            if (!ZYPCommonFunc.hasValue(dcMsg.backOrdImpctTpCd_D2)) {
                ZYPEZDItemValueSetter.setValue(dcMsg.backOrdImpctTpCd_D2, resultObj.get("BACK_ORD_IMPCT_TP_CD"));
            }
        }
    }
    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END

    // START 2024/03/14 K.Watanabe [QC#63542, ADD]
    /**
     * do process (Copy Model)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void doProcess_NSAL0490Scrn00_CopyModel(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        MDL_NMTMsg mdlNmTMsg = new MDL_NMTMsg();
        mdlNmTMsg.setSQLID("001");
        mdlNmTMsg.setConditionValue("t_GlblCmpyCd01", cMsg.glblCmpyCd.getValue());
        mdlNmTMsg.setConditionValue("t_MdlNm01", cMsg.mdlNm_CF.getValue());
        MDL_NMTMsgArray mdlNmList = (MDL_NMTMsgArray) EZDTBLAccessor.findByCondition(mdlNmTMsg);
        if (mdlNmList.getValidCount() <= 0) {
            cMsg.setMessageInfo(NSAL0490Constant.NSAM0011E, new String[] {cMsg.mdlNm_CF.getValue()});
            return;
        }

        S21SsmEZDResult ssmResult = NSAL0490Query.getInstance().getItemInfoForNotEnteredEndDate(cMsg.glblCmpyCd.getValue(), mdlNmList.no(0).t_MdlId.getValue(), sMsg.B.length());
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultObjList = (List<Map<String, Object>>) ssmResult.getResultObject();
            getCopySupplyMapData(cMsg, sMsg, resultObjList);
        } else if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NSAL0490Constant.NSAM0011E, new String[] {NSAL0490Constant.NSAM0011E_SUPPLY_MAP});
        }
    }
    // END 2024/03/14 K.Watanabe [QC#63542, ADD]

    // 2015/10/07 CSA Y.Tsuchimoto Add End
    // mod end 2016/03/01 CSA Defect#3586

    /**
     * do process (Page Prev)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void doProcess_NSAL0490Scrn00_PagePrev(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        String dplyTab = cMsg.xxDplyTab.getValue();
        if (NSAL0490Constant.TAB_ITEM_CONFIG.equals(dplyTab)) {
            pagePrevForItemConfig(cMsg, sMsg);
        } else if (NSAL0490Constant.TAB_SUPPLY_MAP.equals(dplyTab)) {
            pagePrevForSupplyMap(cMsg, sMsg);
        }
    }

    /**
     * do process (Page Next)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void doProcess_NSAL0490Scrn00_PageNext(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        String dplyTab = cMsg.xxDplyTab.getValue();
        if (NSAL0490Constant.TAB_ITEM_CONFIG.equals(dplyTab)) {
            pageNextForItemConfig(cMsg, sMsg);
        } else if (NSAL0490Constant.TAB_SUPPLY_MAP.equals(dplyTab)) {
            pageNextForSupplyMap(cMsg, sMsg);
        }
    }

    // START 2017/01/23 K.Ochiai [QC#16331,MOD]
    /**
     * do process (Clear)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void doProcess_NSAL0490Scrn00_CMN_Clear(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        cMsg.clear();
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
        ZYPTableUtil.clear(cMsg.D);
        ZYPTableUtil.clear(sMsg.D);
        //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END
        EZDMsg.copy(cMsg, null, sMsg, null);

        doProcess_NSAL0490_INIT(cMsg, sMsg);
    }

    /**
     * do process (Reset)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void doProcess_NSAL0490Scrn00_CMN_Reset(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        clearField(cMsg, sMsg);
        doProcess_NSAL0490_INIT(cMsg, sMsg);
    }
    // END 2017/01/23 K.Ochiai [QC#16331,MOD]

    /**
     * do process (Submit)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void doProcess_NSAL0490Scrn00_CMN_Submit(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        String dplyTab = cMsg.xxDplyTab.getValue();
        if (NSAL0490Constant.TAB_ITEM_CONFIG.equals(dplyTab)) {
            doProcess_NSAL0490Scrn00_Search(cMsg, sMsg);
        } else if (NSAL0490Constant.TAB_SVC_RULES.equals(dplyTab)) {
            getSvcRulesData(cMsg);
        } else if (NSAL0490Constant.TAB_SUPPLY_MAP.equals(dplyTab)) {
            getSupplyMapData(cMsg, sMsg);
        // add start 2016/05/19 CSA Defect#447
        } else if (NSAL0490Constant.TAB_END_OF_LIFE.equals(dplyTab)) {
            getDsMdlUpTime(cMsg);
            getEndOfLifeData(cMsg, sMsg);
        // add end 2016/05/19 CSA Defect#447
        }

        cMsg.setMessageInfo(NSAL0490Constant.ZZZM9003I, new String[] {NSAL0490Constant.SUBMIT });
    }

    /**
     * create PullDown
     * @param cMsg NSAL0490CMsg
     */
    private void createPullDown(NSAL0490CMsg cMsg) {

        ZYPCodeDataUtil.createPulldownList(SVC_SEG.class, cMsg.svcSegCd_PL, cMsg.svcSegDescTxt_PL);
        createActvStsPulldown(cMsg);
        createMtrGrpPulldownList(cMsg);
        // START 2016/02/10 A.Kohinata [QC#1157, DEL]
//        createSvcSklPulldownList(cMsg);
        // END 2016/02/10 A.Kohinata [QC#1157, DEL]
        createSvcIstlRulePulldownList(cMsg, ZYPConstant.FLG_ON_Y);
        createSvcIstlRulePulldownList(cMsg, ZYPConstant.FLG_OFF_N);
        // 2015/10/07 CSA Y.Tsuchimoto Add Start
        createSvcIstlCallGrp(cMsg, ZYPConstant.FLG_ON_Y);
        createSvcIstlCallGrp(cMsg, ZYPConstant.FLG_OFF_N);
        // 2015/10/07 CSA Y.Tsuchimoto Add End
        // add start 2016/05/19 CSA Defect#447
        ZYPCodeDataUtil.createPulldownList(DS_MDL_EOL_STS.class, cMsg.dsMdlEolStsCd_PL, cMsg.dsMdlEolStsDescTxt_PL);
        // add end 2016/05/19 CSA Defect#447
        // START 2017/11/02 S.Katsuma [SOL#170(QC#18624), ADD]
        ZYPCodeDataUtil.createPulldownList(SVC_LB_GRP.class, cMsg.svcLbGrpCd_PL, cMsg.svcLbGrpDescTxt_PL);
        // END 2017/11/02 S.Katsuma [SOL#170(QC#18624), ADD]
        // START 2018/01/15 K.Kojima [QC#23352,ADD]
        ZYPCodeDataUtil.createPulldownList(SVC_MDL_TP.class, cMsg.svcMdlTpCd_PL, cMsg.svcMdlTpDescTxt_PL);
        // END 2018/01/15 K.Kojima [QC#23352,ADD]
    }

    // 2015/10/07 CSA Y.Tsuchimoto Add Start
    private void createSvcIstlCallGrp(NSAL0490CMsg cMsg, String svcIstlCallGrpFlg) {

        S21SsmEZDResult ssmResult = NSAL0490Query.getInstance().getSvcIstlCallGrpList(cMsg, svcIstlCallGrpFlg);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> map = (Map<String, String>) resultList.get(i);
                if (ZYPConstant.FLG_ON_Y.equals(svcIstlCallGrpFlg)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.svcIstlCallGrpNum_PI.no(i), (String) map.get("SVC_ISTL_CALL_GRP_NUM"));
                    ZYPEZDItemValueSetter.setValue(cMsg.svcIstlCallGrpNm_PI.no(i), (String) map.get("SVC_ISTL_CALL_GRP_NM"));
                } else {
                    ZYPEZDItemValueSetter.setValue(cMsg.svcIstlCallGrpNum_PD.no(i), (String) map.get("SVC_ISTL_CALL_GRP_NUM"));
                    ZYPEZDItemValueSetter.setValue(cMsg.svcIstlCallGrpNm_PD.no(i), (String) map.get("SVC_ISTL_CALL_GRP_NM"));
                }
            }
        }
    }
    // 2015/10/07 CSA Y.Tsuchimoto Add End

    /**
     * create Active Status PullDown
     * @param cMsg NSAL0490CMsg
     */
    private void createActvStsPulldown(NSAL0490CMsg cMsg) {

        ACTV_STS[] actvStsList = new ACTV_STS[] {ACTV_STS.ACTV, ACTV_STS.IN_ACTV };

        for (int i = 0; i < actvStsList.length; i++) {
            cMsg.keyInfoCd_PL.no(i).setValue(actvStsList[i].getValue());
            cMsg.xxScrItem20Txt_PL.no(i).setValue(actvStsList[i].getDisplay());
        }
        cMsg.mdlActvFlg.setValue(ZYPConstant.FLG_ON_Y);
    }

    /**
     * create Meter Group PullDown
     * @param cMsg NSAL0490CMsg
     */
    @SuppressWarnings("unchecked")
    private void createMtrGrpPulldownList(NSAL0490CMsg cMsg) {

        S21SsmEZDResult ssmResult = NSAL0490Query.getInstance().getMtrGrpList(cMsg);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, Object> map = (Map<String, Object>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.mtrGrpPk_PL.no(i), (BigDecimal) map.get("MTR_GRP_PK"));
                ZYPEZDItemValueSetter.setValue(cMsg.mtrGrpNm_PL.no(i), (String) map.get("MTR_GRP_NM"));
            }
        }
    }

    // START 2016/02/10 A.Kohinata [QC#1157, DEL]
//    /**
//     * create Service Skills PullDown
//     * @param cMsg NSAL0490CMsg
//     */
//    @SuppressWarnings("unchecked")
//    private void createSvcSklPulldownList(NSAL0490CMsg cMsg) {
//
//        S21SsmEZDResult ssmResult = NSAL0490Query.getInstance().getSvcSklList(cMsg);
//
//        if (ssmResult.isCodeNormal()) {
//            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
//            // 2015/10/07 CSA Y.Tsuchimoto Mod Start
//            //for (int i = 0; i < resultList.size(); i++) {
//            //    Map<String, String> map = (Map<String, String>) resultList.get(i);
//            //    ZYPEZDItemValueSetter.setValue(cMsg.svcSkillNum_PL.no(i), (String) map.get("SVC_SKILL_NUM"));
//            //    ZYPEZDItemValueSetter.setValue(cMsg.svcSkillNm_PL.no(i), (String) map.get("SVC_SKILL_NM"));
//            //}
//            int cnt = resultList.size();
//            if (resultList.size() > cMsg.svcSkillNum_PL.length()) {
//                cnt =  cMsg.svcSkillNum_PL.length();
//            }
//            for (int i = 0; i < cnt; i++) {
//                Map<String, String> map = (Map<String, String>) resultList.get(i);
//                ZYPEZDItemValueSetter.setValue(cMsg.svcSkillNum_PL.no(i), (String) map.get("SVC_SKILL_NUM"));
//                ZYPEZDItemValueSetter.setValue(cMsg.svcSkillNm_PL.no(i), (String) map.get("SVC_SKILL_NM"));
//            }
//         // 2015/10/07 CSA Y.Tsuchimoto Mod End
//        }
//    }
    // END 2016/02/10 A.Kohinata [QC#1157, DEL]

    /**
     * create Service Installation Rules PullDown
     * @param cMsg NSAL0490CMsg
     * @param actvFlg Active Flag
     */
    @SuppressWarnings("unchecked")
    private void createSvcIstlRulePulldownList(NSAL0490CMsg cMsg, String actvFlg) {

        S21SsmEZDResult ssmResult = NSAL0490Query.getInstance().getSvcIstlRuleList(cMsg, actvFlg);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> map = (Map<String, String>) resultList.get(i);

                if (ZYPConstant.FLG_ON_Y.equals(actvFlg)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.svcIstlRuleNum_PY.no(i), (String) map.get("SVC_ISTL_RULE_NUM"));
                    ZYPEZDItemValueSetter.setValue(cMsg.svcIstlRuleNm_PY.no(i), (String) map.get("SVC_ISTL_RULE_NM"));
                } else {
                    ZYPEZDItemValueSetter.setValue(cMsg.svcIstlRuleNum_PN.no(i), (String) map.get("SVC_ISTL_RULE_NUM"));
                    ZYPEZDItemValueSetter.setValue(cMsg.svcIstlRuleNm_PN.no(i), (String) map.get("SVC_ISTL_RULE_NM"));
                }
            }
        }
    }

    /**
     * create Item Configurations Empty Line
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void createItemConfigEmptyLine(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        NSAL0490_ACMsg acMsg = cMsg.A.no(0);
        ZYPEZDItemValueSetter.setValue(acMsg.xxFlgNm_A, NSAL0490Constant.DTL_TP_PRNT);
        ZYPEZDItemValueSetter.setValue(acMsg.xxLineNum_A, ZYPConstant.FLG_ON_1);
        ZYPEZDItemValueSetter.setValue(acMsg.effFromDt_A, cMsg.slsDt);
        cMsg.A.setValidCount(1);

        EZDMsg.copy(cMsg.A, null, sMsg.A, null);

        cMsg.xxPageShowFromNum_A.setValue(1);
        cMsg.xxPageShowToNum_A.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum_A.setValue(sMsg.A.getValidCount());
    }

    /**
     * create Supply Mapping Empty Line
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void createSupplyMapEmptyLine(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(sMsg.B);

        NSAL0490_BCMsg bcMsg = cMsg.B.no(0);
        ZYPEZDItemValueSetter.setValue(bcMsg.effFromDt_B, cMsg.slsDt);
        cMsg.B.setValidCount(1);

        EZDMsg.copy(cMsg.B, null, sMsg.B, null);

        cMsg.xxPageShowFromNum_B.setValue(1);
        cMsg.xxPageShowToNum_B.setValue(cMsg.B.getValidCount());
        cMsg.xxPageShowOfNum_B.setValue(sMsg.B.getValidCount());
    }

    /**
     * clear Field
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void clearField(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        String inparamMdlNm = cMsg.mdlNm_PR.getValue();

        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
        ZYPTableUtil.clear(cMsg.D);
        //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END
        ZYPEZDItemValueSetter.setValue(cMsg.mdlNm_PR, inparamMdlNm);

        EZDMsg.copy(cMsg, null, sMsg, null);
    }

    /**
     * get Header Data
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     * @return Exist Data : true
     */
    private boolean getHdrData(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        // get Header Data
        S21SsmEZDResult ssmResult = NSAL0490Query.getInstance().getHdrData(cMsg);

        if (ssmResult.isCodeNotFound()) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxRsltFlg, ZYPConstant.FLG_OFF_N);
            cMsg.setMessageInfo(NSAL0490Constant.NZZM0000E);
            return false;
        }

        String formattedDt = ZYPDateUtil.formatEzd8ToDisp(cMsg.ezInTime_CD.getValue().substring(NSAL0490Constant.DT_START_INDEX, NSAL0490Constant.DT_END_INDEX));
        ZYPEZDItemValueSetter.setValue(cMsg.ezInTime_CD, formattedDt);
        ZYPEZDItemValueSetter.setValue(cMsg.xxRsltFlg, ZYPConstant.FLG_ON_Y);
        // START 2017/01/23 K.Ochiai [QC#16331,ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.mdlNm_PR, cMsg.mdlNm);
        // END 2017/01/23 K.Ochiai [QC#16331,ADD]

        EZDMsg.copy(cMsg, null, sMsg, null);

        return true;
    }

    /**
     * get Item Configurations Data
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void getItemConfigData(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        // clear Detail
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
// mod start 2017/01/16 CSA QC#16618
        // get Parent Data
        List<Map<String, Object>> configDataList = getItemConfigDataList(cMsg);

        if (configDataList.size() == 0) {
            createItemConfigEmptyLine(cMsg, sMsg);
            return;
        }

        int validCnt = 0;
        int lineNum = 1;
        for (Map<String, Object> cnfgData : configDataList) {
            // set Parent Data
            BigDecimal prntConfigPk = (BigDecimal) cnfgData.get("PRNT_CONFIG_PK");
            String prntMdseCd = (String) cnfgData.get("PRNT_MDSE_CD");
            NSAL0490_ASMsg asMsg = sMsg.A.no(validCnt++);
            // mod start 2017/01/25 CSA QC#17283
            if (!ZYPCommonFunc.hasValue((String)cnfgData.get("CHILD_MDSE_CD"))) {
                lineNum++;
                ZYPEZDItemValueSetter.setValue(asMsg.xxFlgNm_A, NSAL0490Constant.DTL_TP_PRNT);
            } else {
                ZYPEZDItemValueSetter.setValue(asMsg.xxFlgNm_A, NSAL0490Constant.DTL_TP_CHILD);
            }
            // mod end 2017/01/25 CSA QC#17283
            ZYPEZDItemValueSetter.setValue(asMsg.xxLineNum_A, String.valueOf(lineNum));
            ZYPEZDItemValueSetter.setValue(asMsg.dsMdlConfigPk_A, (BigDecimal) cnfgData.get("DS_MDL_CONFIG_PK"));
            ZYPEZDItemValueSetter.setValue(asMsg.prntConfigPk_A, prntConfigPk);
            ZYPEZDItemValueSetter.setValue(asMsg.childMdseCd_A, (String)cnfgData.get("CHILD_MDSE_CD"));
            if (!ZYPCommonFunc.hasValue(asMsg.childMdseCd_A)) {
                ZYPEZDItemValueSetter.setValue(asMsg.prntMdseCd_A, prntMdseCd);
            }
            // START 2016/09/16 N.Arai [QC#11616, MOD]
            // ZYPEZDItemValueSetter.setValue(asMsg.mdseNm_A, (String) prntData.get("MDSE_NM"));
            ZYPEZDItemValueSetter.setValue(asMsg.mdseDescShortTxt_A, (String) cnfgData.get("MDSE_DESC_SHORT_TXT"));
            // END 2016/09/16 N.Arai [QC#11616, MOD]
            ZYPEZDItemValueSetter.setValue(asMsg.mdseCatgNm_A, (String) cnfgData.get("MDSE_CATG_NM"));
            ZYPEZDItemValueSetter.setValue(asMsg.mdseTpNm_A, (String) cnfgData.get("MDSE_TP_NM"));
            ZYPEZDItemValueSetter.setValue(asMsg.coaProdNm_A, (String) cnfgData.get("COA_PROD_NM"));
            ZYPEZDItemValueSetter.setValue(asMsg.effFromDt_A, (String) cnfgData.get("EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(asMsg.effThruDt_A, (String) cnfgData.get("EFF_THRU_DT"));
            ZYPEZDItemValueSetter.setValue(asMsg.ezUpTimeZone_A, (String) cnfgData.get("EZUPTIMEZONE"));
            ZYPEZDItemValueSetter.setValue(asMsg.ezUpTime_A, (String) cnfgData.get("EZUPTIME"));
            // 2015/10/07 CSA Y.Tsuchimoto Add Start
            ZYPEZDItemValueSetter.setValue(asMsg.mdseCd_A, (String) cnfgData.get("MDSE_CD"));
            // 2015/10/07 CSA Y.Tsuchimoto Add End
            // START 2016/12/02 K.Kojima [QC#14204,ADD]
            ZYPEZDItemValueSetter.setValue(asMsg.xxRecHistCratTs_A, (String) cnfgData.get("XX_REC_HIST_CRAT_TS_A"));
            ZYPEZDItemValueSetter.setValue(asMsg.xxRecHistCratByNm_A, (String) cnfgData.get("XX_REC_HIST_CRAT_BY_NM_A"));
            ZYPEZDItemValueSetter.setValue(asMsg.xxRecHistUpdTs_A, (String) cnfgData.get("XX_REC_HIST_UPD_TS_A"));
            ZYPEZDItemValueSetter.setValue(asMsg.xxRecHistUpdByNm_A, (String) cnfgData.get("XX_REC_HIST_UPD_BY_NM_A"));
            ZYPEZDItemValueSetter.setValue(asMsg.xxRecHistTblNm_A, (String) cnfgData.get("XX_REC_HIST_TBL_NM_A"));
            // END 2016/12/02 K.Kojima [QC#14204,ADD]
            if (validCnt == sMsg.A.length()) {
                break;
            }
//
//            // get and set Child Data
//            for (Map<String, Object> childData : getChildDataList(cMsg, prntConfigPk, prntMdseCd)) {
//                asMsg = sMsg.A.no(validCnt++);
//                ZYPEZDItemValueSetter.setValue(asMsg.xxFlgNm_A, NSAL0490Constant.DTL_TP_CHILD);
//                ZYPEZDItemValueSetter.setValue(asMsg.xxLineNum_A, String.valueOf(lineNum));
//                ZYPEZDItemValueSetter.setValue(asMsg.dsMdlConfigPk_A, (BigDecimal) childData.get("DS_MDL_CONFIG_PK"));
//                ZYPEZDItemValueSetter.setValue(asMsg.prntConfigPk_A, (BigDecimal) childData.get("PRNT_CONFIG_PK"));
//                ZYPEZDItemValueSetter.setValue(asMsg.childMdseCd_A, (String) childData.get("CHILD_MDSE_CD"));
//                // START 2016/09/16 N.Arai [QC#11616, MOD]
//                // ZYPEZDItemValueSetter.setValue(asMsg.mdseNm_A, (String) childData.get("MDSE_NM"));
//                ZYPEZDItemValueSetter.setValue(asMsg.mdseDescShortTxt_A, (String) childData.get("MDSE_DESC_SHORT_TXT"));
//                // END 2016/09/16 N.Arai [QC#11616, MOD]
//                ZYPEZDItemValueSetter.setValue(asMsg.mdseCatgNm_A, (String) childData.get("MDSE_CATG_NM"));
//                ZYPEZDItemValueSetter.setValue(asMsg.mdseTpNm_A, (String) childData.get("MDSE_TP_NM"));
//                ZYPEZDItemValueSetter.setValue(asMsg.coaProdNm_A, (String) childData.get("COA_PROD_NM"));
//                ZYPEZDItemValueSetter.setValue(asMsg.effFromDt_A, (String) childData.get("EFF_FROM_DT"));
//                ZYPEZDItemValueSetter.setValue(asMsg.effThruDt_A, (String) childData.get("EFF_THRU_DT"));
//                ZYPEZDItemValueSetter.setValue(asMsg.ezUpTimeZone_A, (String) prntData.get("EZUPTIMEZONE"));
//                ZYPEZDItemValueSetter.setValue(asMsg.ezUpTime_A, (String) prntData.get("EZUPTIME"));
//                ZYPEZDItemValueSetter.setValue(asMsg.ezUpTime_A, (String) prntData.get("EZUPTIME"));
//                // 2015/10/07 CSA Y.Tsuchimoto Add Start
//                ZYPEZDItemValueSetter.setValue(asMsg.mdseCd_A, (String) childData.get("MDSE_CD"));
//                // 2015/10/07 CSA Y.Tsuchimoto Add End
//                // START 2016/12/02 K.Kojima [QC#14204,ADD]
//                ZYPEZDItemValueSetter.setValue(asMsg.xxRecHistCratTs_A, (String) childData.get("XX_REC_HIST_CRAT_TS_A"));
//                ZYPEZDItemValueSetter.setValue(asMsg.xxRecHistCratByNm_A, (String) childData.get("XX_REC_HIST_CRAT_BY_NM_A"));
//                ZYPEZDItemValueSetter.setValue(asMsg.xxRecHistUpdTs_A, (String) childData.get("XX_REC_HIST_UPD_TS_A"));
//                ZYPEZDItemValueSetter.setValue(asMsg.xxRecHistUpdByNm_A, (String) childData.get("XX_REC_HIST_UPD_BY_NM_A"));
//                ZYPEZDItemValueSetter.setValue(asMsg.xxRecHistTblNm_A, (String) childData.get("XX_REC_HIST_TBL_NM_A"));
//                // END 2016/12/02 K.Kojima [QC#14204,ADD]
//
//                if (validCnt == sMsg.A.length()) {
//                    break;
//                }
//            }
// mod end 2017/01/16 CSA QC#16618
            // mod start 2017/01/25 CSA QC#17283
//          lineNum++;
            // mod end 2017/01/25 CSA QC#17283
        }
        sMsg.A.setValidCount(validCnt);

        // 1page copy (sMsg -> cMsg)
        int i = 0;
        for (; i < sMsg.A.getValidCount(); i++) {
            if (i == cMsg.A.length()) {
                break;
            }
            EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);

        }
        cMsg.A.setValidCount(i);

        cMsg.xxPageShowFromNum_A.setValue(1);
        cMsg.xxPageShowToNum_A.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum_A.setValue(sMsg.A.getValidCount());
    }
// mod start 2017/01/16 CSA QC#16618
//    /**
//     * get Parent Data List
//     * @param cMsg NSAL0490CMsg
//     * @return Parent Data List
//     */
//    @SuppressWarnings("unchecked")
//    private List<Map<String, Object>> getPrntDataList(NSAL0490CMsg cMsg) {
//
//        S21SsmEZDResult ssmResult = NSAL0490Query.getInstance().getPrntDataList(cMsg);
//
//        if (ssmResult.isCodeNotFound()) {
//            return new ArrayList<Map<String, Object>>();
//        }
//
//        return (List<Map<String, Object>>) ssmResult.getResultObject();
//    }
//
//    /**
//     * get Child Data List
//     * @param cMsg NSAL0490CMsg
//     * @param prntConfigPk Parent Config PK
//     * @param prntMdseCd Parent Mdse Code
//     * @return Child Data List
//     */
//    @SuppressWarnings("unchecked")
//    private List<Map<String, Object>> getChildDataList(NSAL0490CMsg cMsg, BigDecimal prntConfigPk, String prntMdseCd) {
//
//        S21SsmEZDResult ssmResult = NSAL0490Query.getInstance().getChildDataList(cMsg, prntConfigPk, prntMdseCd);
//
//        if (ssmResult.isCodeNotFound()) {
//            return new ArrayList<Map<String, Object>>();
//        }
//
//        return (List<Map<String, Object>>) ssmResult.getResultObject();
//    }

    /**
     * get Item Config Data List
     * @param cMsg NSAL0490CMsg
     * @return Data List
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getItemConfigDataList(NSAL0490CMsg cMsg) {

        S21SsmEZDResult ssmResult = NSAL0490Query.getInstance().getItemConfigDataList(cMsg);

        if (ssmResult.isCodeNotFound()) {
            return new ArrayList<Map<String, Object>>();
        }

        return (List<Map<String, Object>>) ssmResult.getResultObject();
    }
// mod end 2017/01/16 CSA QC#16618
    /**
     * get Page Start Row Index for Item Configurations
     * @param cMsg NSAL0490CMsg
     * @param index target Index
     * @return Page Start Row Index
     */
    private int getPageStartRowIndexItemConfig(NSAL0490CMsg cMsg, int index) {

        return (index / cMsg.A.length()) * cMsg.A.length();
    }

    /**
     * get Page Start Row Index for Supply Mapping
     * @param cMsg NSAL0490CMsg
     * @param index target Index
     * @return Page Start Row Index
     */
    private int getPageStartRowIndexSupplyMap(NSAL0490CMsg cMsg, int index) {

        return (index / cMsg.B.length()) * cMsg.B.length();
    }

    /**
     * copy from NSAL0490_ASMsgArray to NSAL0490_ACMsgArray for Item Configurations
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     * @param pageFrom page from
     */
    private void copySMsgArrayToCMsgItemConfig(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg, int pageFrom) {

        ZYPTableUtil.clear(cMsg.A);

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pageFrom);

        if (cMsg.A.getValidCount() > 0) {
            cMsg.xxPageShowFromNum_A.setValue(pageFrom + 1);
            cMsg.xxPageShowToNum_A.setValue(pageFrom + cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum_A.setValue(sMsg.A.getValidCount());
        } else {
            cMsg.xxPageShowFromNum_A.setValue(BigDecimal.ZERO);
            cMsg.xxPageShowToNum_A.setValue(BigDecimal.ZERO);
            cMsg.xxPageShowOfNum_A.setValue(BigDecimal.ZERO);
        }
    }

    /**
     * copy from NSAL0490_ASMsgArray to NSAL0490_ACMsgArray for Supply Mapping
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     * @param pageFrom page from
     */
    private void copySMsgArrayToCMsgSupplyMap(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg, int pageFrom) {

        ZYPTableUtil.clear(cMsg.B);

        int i = pageFrom;
        for (; i < pageFrom + cMsg.B.length(); i++) {
            if (i < sMsg.B.getValidCount()) {
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.B.setValidCount(i - pageFrom);

        if (cMsg.B.getValidCount() > 0) {
            cMsg.xxPageShowFromNum_B.setValue(pageFrom + 1);
            cMsg.xxPageShowToNum_B.setValue(pageFrom + cMsg.B.getValidCount());
            cMsg.xxPageShowOfNum_B.setValue(sMsg.B.getValidCount());
        } else {
            cMsg.xxPageShowFromNum_B.setValue(BigDecimal.ZERO);
            cMsg.xxPageShowToNum_B.setValue(BigDecimal.ZERO);
            cMsg.xxPageShowOfNum_B.setValue(BigDecimal.ZERO);
        }
    }

    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
    /**
     * copy from NSAL0490_ASMsgArray to NSAL0490_ACMsgArray for Criticality
     * @param sMsg NSAL0490SMsg
     * @param cMsg NSAL0490CMsg
     */
    private void copySMsgArrayToCMsgCriticality(NSAL0490SMsg sMsg, NSAL0490CMsg cMsg) {

        ZYPTableUtil.clear(cMsg.D);

        int i = 0;
        for (; i < cMsg.D.length(); i++) {
            if (i < sMsg.D.getValidCount()) {
                EZDMsg.copy(sMsg.D.no(i), null, cMsg.D.no(i), null);
            } else {
                break;
            }
        }
        cMsg.D.setValidCount(i);
    }
    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END

    /**
     * set SMsg for Insert Child Row
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSNSAL0490SMsgAL0490CMsg
     * @param selectLineNum Select Line Number
     */
    private void setSMsgForInsertChildRow(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg, int selectLineNum) {

        // Max Line Number
        int maxLineNum = Integer.parseInt(sMsg.A.no(sMsg.A.getValidCount() - 1).xxLineNum_A.getValue());

        String convSelectLineNum = String.valueOf(selectLineNum);
        String prntStartDate = getPrntDate(sMsg, convSelectLineNum, true);
        String prntEndDate = getPrntDate(sMsg, convSelectLineNum, false);

        if (selectLineNum == maxLineNum) {
            int nextIdx = sMsg.A.getValidCount();
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(nextIdx).xxFlgNm_A, NSAL0490Constant.DTL_TP_CHILD);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(nextIdx).xxLineNum_A, convSelectLineNum);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(nextIdx).effFromDt_A, prntStartDate);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(nextIdx).effThruDt_A, prntEndDate);
            sMsg.A.setValidCount(nextIdx + 1);

        } else {
            NSAL0490SMsg bakSMsg = (NSAL0490SMsg) sMsg.clone();
            ZYPTableUtil.clear(sMsg.A);

            boolean isChildSet = false;
            int validCnt = 0;
            for (int cnt = 0; cnt < bakSMsg.A.getValidCount(); cnt++) {
                NSAL0490_ASMsg bakAsMsg = bakSMsg.A.no(cnt);
                NSAL0490_ASMsg asMsg = sMsg.A.no(validCnt);

                int lineNum = Integer.parseInt(bakAsMsg.xxLineNum_A.getValue());
                if (lineNum > selectLineNum && !isChildSet) {
                    ZYPEZDItemValueSetter.setValue(asMsg.xxFlgNm_A, NSAL0490Constant.DTL_TP_CHILD);
                    ZYPEZDItemValueSetter.setValue(asMsg.xxLineNum_A, convSelectLineNum);
                    ZYPEZDItemValueSetter.setValue(asMsg.effFromDt_A, prntStartDate);
                    ZYPEZDItemValueSetter.setValue(asMsg.effThruDt_A, prntEndDate);
                    isChildSet = true;
                    validCnt++;
                    asMsg = sMsg.A.no(validCnt);
                }

                ZYPEZDItemValueSetter.setValue(asMsg.xxFlgNm_A, bakAsMsg.xxFlgNm_A);
                ZYPEZDItemValueSetter.setValue(asMsg.xxLineNum_A, bakAsMsg.xxLineNum_A);
                ZYPEZDItemValueSetter.setValue(asMsg.dsMdlConfigPk_A, bakAsMsg.dsMdlConfigPk_A);
                ZYPEZDItemValueSetter.setValue(asMsg.prntConfigPk_A, bakAsMsg.prntConfigPk_A);
                ZYPEZDItemValueSetter.setValue(asMsg.prntMdseCd_A, bakAsMsg.prntMdseCd_A);
                ZYPEZDItemValueSetter.setValue(asMsg.childMdseCd_A, bakAsMsg.childMdseCd_A);
                // START 2016/09/16 N.Arai [QC#11616, MOD]
                // ZYPEZDItemValueSetter.setValue(asMsg.mdseNm_A, bakAsMsg.mdseNm_A);
                ZYPEZDItemValueSetter.setValue(asMsg.mdseDescShortTxt_A, bakAsMsg.mdseDescShortTxt_A);
                // END 2016/09/16 N.Arai [QC#11616, MOD]
                ZYPEZDItemValueSetter.setValue(asMsg.mdseCatgNm_A, bakAsMsg.mdseCatgNm_A);
                ZYPEZDItemValueSetter.setValue(asMsg.mdseTpNm_A, bakAsMsg.mdseTpNm_A);
                ZYPEZDItemValueSetter.setValue(asMsg.coaProdNm_A, bakAsMsg.coaProdNm_A);
                ZYPEZDItemValueSetter.setValue(asMsg.effFromDt_A, bakAsMsg.effFromDt_A);
                ZYPEZDItemValueSetter.setValue(asMsg.effThruDt_A, bakAsMsg.effThruDt_A);
                ZYPEZDItemValueSetter.setValue(asMsg.ezUpTimeZone_A, bakAsMsg.ezUpTimeZone_A);
                ZYPEZDItemValueSetter.setValue(asMsg.ezUpTime_A, bakAsMsg.ezUpTime_A);
                validCnt++;
            }
            sMsg.A.setValidCount(validCnt);
        }
    }

    // START 2018/02/16 M.Kidokoro [QC#24249,DEL]
//    /**
//     * check Child Detail Length
//     * @param cMsg NSAL0490CMsg
//     * @param sMsg NSAL0490SMsg
//     * @param selectLineNum Select Line Number
//     * @return true : No Error
//     */
//    private boolean checkChildDtlLength(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg, int selectLineNum) {
//
//        int dtlLength = 0;
//        for (int cnt = 0; cnt < sMsg.A.getValidCount(); cnt++) {
//            NSAL0490_ASMsg asMsg = sMsg.A.no(cnt);
//            if (asMsg.xxLineNum_A.getValue().equals(String.valueOf(selectLineNum)) && !ZYPCommonFunc.hasValue(asMsg.prntMdseCd_A)) {
//                dtlLength++;
//                continue;
//            } else if (Integer.parseInt(asMsg.xxLineNum_A.getValue()) > selectLineNum) {
//                break;
//            }
//        }
//
//        int maxlength = cMsg.A.length();
//        if (maxlength - 1 == dtlLength) {
//            cMsg.setMessageInfo(NSAL0490Constant.NSAM0320E, new String[] {NSAL0490Constant.CHILD_ITEM_LINE, String.valueOf(maxlength) });
//            return false;
//        }
//
//        return true;
//    }
    // END 2018/02/16 M.Kidokoro [QC#24249,DEL]

    // START 2018/02/16 M.Kidokoro [QC#24249,ADD]
    /**
     * check Detail Length
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     * @return true : No Error
     */
    private boolean checkDtlLength(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg, String itemLineNm) {

        int maxlength = sMsg.A.length();
        if (maxlength == sMsg.A.getValidCount()) {
            cMsg.setMessageInfo(NSAL0490Constant.NSAM0320E, new String[] {itemLineNm, String.valueOf(maxlength) });
            return false;
        }

        return true;
    }
    // END 2018/02/16 M.Kidokoro [QC#24249,ADD]

    /**
     * check Delete Row Item Configurations
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     * @param selectIndex Select Index
     * @param dtlType Detail Type
     * @return true : No Error
     */
    private boolean checkDeleteRowItemConfig(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg, int selectIndex, String dtlType) {

        // check registered
        if (ZYPCommonFunc.hasValue(cMsg.A.no(selectIndex).dsMdlConfigPk_A)) {
            cMsg.setMessageInfo(NSAL0490Constant.NSAM0321E);
            return false;
        }

        // check only one parent
        if (NSAL0490Constant.DTL_TP_PRNT.equals(dtlType)) {
            boolean isExistParent = false;
            for (int cnt = 1; cnt < sMsg.A.getValidCount(); cnt++) {
                NSAL0490_ASMsg asMsg = sMsg.A.no(cnt);
                if (NSAL0490Constant.DTL_TP_PRNT.equals(asMsg.xxFlgNm_A.getValue())) {
                    isExistParent = true;
                    break;
                }
            }

            if (!isExistParent) {
                cMsg.setMessageInfo(NSAL0490Constant.NSAM0322E);
                return false;
            }
        }

        return true;
    }

    /**
     * get Delete Total Number
     * @param sMsg NSAL0490SMsg
     * @param dtlType Detail Type
     * @param selectIndex Select Index
     * @return Delete Total Number
     */
    private int getDeleteTotalNum(NSAL0490SMsg sMsg, String dtlType, int selectIndex) {

        int deleteTotalNum = 0;
        if (NSAL0490Constant.DTL_TP_PRNT.equals(dtlType)) {
            String lineNum = sMsg.A.no(selectIndex).xxLineNum_A.getValue();
            for (int cnt = 0; cnt < sMsg.A.getValidCount(); cnt++) {
                if (lineNum.equals(sMsg.A.no(cnt).xxLineNum_A.getValue())) {
                    deleteTotalNum++;
                }
            }
        } else {
            return 1;
        }

        return deleteTotalNum;
    }

    /**
     * get Service Rules Data
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void getSvcRulesData(NSAL0490CMsg cMsg) {

        clearSvcRulesData(cMsg);
        NSAL0490Query.getInstance().getSvcRulesData(cMsg);
    }

    /**
     * clear Service Rules Data
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void clearSvcRulesData(NSAL0490CMsg cMsg) {

        cMsg.rcllIntvlDaysAot.clear();
        cMsg.rcllCopyVolCnt.clear();
        cMsg.rcllGlblIntvlDaysAot.clear();
        cMsg.rcllGlblCopyVolCnt.clear();
        // mod start 2016/05/30 CSA Defect#6675
        cMsg.xxRtoTaskTmNum.clear();
        // mod end 2016/05/30 CSA Defect#6675
        cMsg.xsVisitCnt.clear();
        cMsg.phoneFixFlg.clear();
        cMsg.phoneFixIntvlDaysAot.clear();
        cMsg.afterHourAllwFlg.clear();
        // 2015/10/07 CSA Y.Tsuchimoto Add Start
        cMsg.machInFldInacMthAot.clear();
        cMsg.mdlDurnTmNum.clear();
        // 2015/10/07 CSA Y.Tsuchimoto Add End
        cMsg.copyVolDaysAot.clear();
        cMsg.maxCopyPerDayTotCnt.clear();
        cMsg.maxCopyPerDayBlackCnt.clear();
        cMsg.maxCopyTestCnt.clear();
        cMsg.mdlSpeedBlackRate.clear();
        cMsg.mdlSpeedColorRate.clear();
        cMsg.dsMdlPaperSizeCd.clear();
    }

    /**
     * get Supply Mapping Data
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void getSupplyMapData(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        // clear Detail
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(sMsg.B);

        S21SsmEZDResult ssmResult = NSAL0490Query.getInstance().getSupplyMapData(cMsg, sMsg);

        if (ssmResult.isCodeNotFound()) {
            createSupplyMapEmptyLine(cMsg, sMsg);
            return;
        }

        // 1page copy (sMsg -> cMsg)
        int i = 0;
        for (; i < sMsg.B.getValidCount(); i++) {
            if (i == cMsg.B.length()) {
                break;
            }
            EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);

        }
        cMsg.B.setValidCount(i);

        cMsg.xxPageShowFromNum_B.setValue(1);
        cMsg.xxPageShowToNum_B.setValue(cMsg.B.getValidCount());
        cMsg.xxPageShowOfNum_B.setValue(sMsg.B.getValidCount());
    }

    // START 2024/03/14 K.Watanabe [QC#63542, ADD]
    /**
     * get Copy Supply Mapping Data
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     * @param supplyMapDataList List<Map<String, Object>>
     */
    private void getCopySupplyMapData(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg, List<Map<String, Object>> supplyMapDataList) {

        if (supplyMapDataList == null || supplyMapDataList.isEmpty()) {
            cMsg.setMessageInfo(NSAL0490Constant.NSAM0011E, new String[] {NSAL0490Constant.NSAM0011E_SUPPLY_MAP});
            return;
        }

        // reset Detail
        getSupplyMapData(cMsg, sMsg);

        // set supplyMapData (supplyMapData -> sMsg)
        int validCount = sMsg.B.getValidCount();
        if (validCount == 1 && sMsg.B.no(0).dsMdlSplyRelnPk_B.getValue() == null) {
            validCount = 0;
        }
        int maxlength = sMsg.B.length();
        if (maxlength < validCount + supplyMapDataList.size()) {
            cMsg.setMessageInfo(NSAL0490Constant.NSAM0320E, new String[] {"Item", String.valueOf(maxlength) });
            return;
        }
        int pageFrom = getPageStartRowIndexSupplyMap(cMsg, validCount);
        for (Map<String, Object> supplyMapData : supplyMapDataList) {
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(validCount).effFromDt_B, cMsg.slsDt);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(validCount).mdseCd_B, (String) supplyMapData.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(validCount).imgSplyOemCd_B, (String) supplyMapData.get("IMG_SPLY_OEM_CD"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(validCount).mdseDescShortTxt_B, (String) supplyMapData.get("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(validCount).mdseItemClsTpNm_B, (String) supplyMapData.get("MDSE_ITEM_CLS_TP_NM"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(validCount).imgSplyTpNm_B, (String) supplyMapData.get("IMG_SPLY_TP_NM"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(validCount).imgSplyColorTpNm_B, (String) supplyMapData.get("IMG_SPLY_COLOR_TP_NM"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(validCount).imgSplyYieldCnt_B, (BigDecimal) supplyMapData.get("IMG_SPLY_YIELD_CNT"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(validCount).imgSplyYieldTpNm_B, (String) supplyMapData.get("IMG_SPLY_YIELD_TP_NM"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(validCount).splyTolPct_B, (BigDecimal) supplyMapData.get("SPLY_TOL_PCT"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(validCount).custStkQty_B, (BigDecimal) supplyMapData.get("CUST_STK_QTY"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(validCount).effFromDt_B, (String) cMsg.slsDt.getValue());
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(validCount).effThruDt_B, (String) null);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(validCount).splyInitQty_B, (BigDecimal) supplyMapData.get("SPLY_INIT_QTY"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(validCount).splyContrQty_B, (BigDecimal) supplyMapData.get("SPLY_CONTR_QTY"));
            validCount++;
        }

        // 1page copy (sMsg -> cMsg)
        int i = pageFrom;
        for (; i < pageFrom + cMsg.B.length(); i++) {
            if (i < validCount) {
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        sMsg.B.setValidCount(validCount);
        if (cMsg.B.length() < validCount - pageFrom) {
            cMsg.B.setValidCount(cMsg.B.length());
        } else {
            cMsg.B.setValidCount(validCount - pageFrom);
        }

        cMsg.xxPageShowFromNum_B.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum_B.setValue(pageFrom + cMsg.B.getValidCount());
        cMsg.xxPageShowOfNum_B.setValue(sMsg.B.getValidCount());
    }
    // END 2024/03/14 K.Watanabe [QC#63542, ADD]

    // add start 2016/05/19 CSA Defect#447
    /**
     * get Supply Mapping Data
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void getEndOfLifeData(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        DS_MDL_EOLTMsg noContrInfo = getDsMdlEol(cMsg, DS_MDL_EOL_STS.EOL_NO_CONTRACT);
        DS_MDL_EOLTMsg noSvcInfo = getDsMdlEol(cMsg, DS_MDL_EOL_STS.EOL_NO_SERVICE);
        DS_MDL_EOLTMsg inactiveInfo = getDsMdlEol(cMsg, DS_MDL_EOL_STS.EOL_INACTIVE);
        clearEndOfLifeData(cMsg);
        int idx = 0;
        if (noContrInfo != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.dsMdlEolStsCd_D1, noContrInfo.dsMdlEolStsCd);
            ZYPEZDItemValueSetter.setValue(cMsg.dsMdlEolDt_D1, noContrInfo.dsMdlEolDt);
            ZYPEZDItemValueSetter.setValue(cMsg.C.no(idx).mdlId_C, noContrInfo.mdlId);
            ZYPEZDItemValueSetter.setValue(cMsg.C.no(idx).dsMdlEolStsCd_C, noContrInfo.dsMdlEolStsCd);
            ZYPEZDItemValueSetter.setValue(cMsg.C.no(idx).ezUpTime_C, noContrInfo.ezUpTime);
            ZYPEZDItemValueSetter.setValue(cMsg.C.no(idx).ezUpTimeZone_C, noContrInfo.ezUpTimeZone);
            cMsg.C.setValidCount(cMsg.C.getValidCount() + 1);
            idx++;
        }
        if (noSvcInfo != null) {
            if (idx== 0) {
                ZYPEZDItemValueSetter.setValue(cMsg.dsMdlEolStsCd_D1, noSvcInfo.dsMdlEolStsCd);
                ZYPEZDItemValueSetter.setValue(cMsg.dsMdlEolDt_D1, noSvcInfo.dsMdlEolDt);
            } else if (idx == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.dsMdlEolStsCd_D2, noSvcInfo.dsMdlEolStsCd);
                ZYPEZDItemValueSetter.setValue(cMsg.dsMdlEolDt_D2, noSvcInfo.dsMdlEolDt);
            }
            ZYPEZDItemValueSetter.setValue(cMsg.C.no(idx).mdlId_C, noSvcInfo.mdlId);
            ZYPEZDItemValueSetter.setValue(cMsg.C.no(idx).dsMdlEolStsCd_C, noSvcInfo.dsMdlEolStsCd);
            ZYPEZDItemValueSetter.setValue(cMsg.C.no(idx).ezUpTime_C, noSvcInfo.ezUpTime);
            ZYPEZDItemValueSetter.setValue(cMsg.C.no(idx).ezUpTimeZone_C, noSvcInfo.ezUpTimeZone);
            cMsg.C.setValidCount(cMsg.C.getValidCount() + 1);
            idx++;
        }
        if (inactiveInfo != null) {
            if (idx== 0) {
                ZYPEZDItemValueSetter.setValue(cMsg.dsMdlEolStsCd_D1, inactiveInfo.dsMdlEolStsCd);
                ZYPEZDItemValueSetter.setValue(cMsg.dsMdlEolDt_D1, inactiveInfo.dsMdlEolDt);
            } else if (idx == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.dsMdlEolStsCd_D2, inactiveInfo.dsMdlEolStsCd);
                ZYPEZDItemValueSetter.setValue(cMsg.dsMdlEolDt_D2, inactiveInfo.dsMdlEolDt);
            } else if (idx == 2) {
                ZYPEZDItemValueSetter.setValue(cMsg.dsMdlEolStsCd_D3, inactiveInfo.dsMdlEolStsCd);
                ZYPEZDItemValueSetter.setValue(cMsg.dsMdlEolDt_D3, inactiveInfo.dsMdlEolDt);
            }
            ZYPEZDItemValueSetter.setValue(cMsg.C.no(idx).mdlId_C, inactiveInfo.mdlId);
            ZYPEZDItemValueSetter.setValue(cMsg.C.no(idx).dsMdlEolStsCd_C, inactiveInfo.dsMdlEolStsCd);
            ZYPEZDItemValueSetter.setValue(cMsg.C.no(idx).ezUpTime_C, inactiveInfo.ezUpTime);
            ZYPEZDItemValueSetter.setValue(cMsg.C.no(idx).ezUpTimeZone_C, inactiveInfo.ezUpTimeZone);
            cMsg.C.setValidCount(cMsg.C.getValidCount() + 1);
        }
    }

    private void clearEndOfLifeData(NSAL0490CMsg cMsg) {

        ZYPTableUtil.clear(cMsg.C);
        cMsg.dsMdlEolStsCd_D1.clear();
        cMsg.dsMdlEolStsCd_D2.clear();
        cMsg.dsMdlEolStsCd_D3.clear();
        cMsg.dsMdlEolDt_D1.clear();
        cMsg.dsMdlEolDt_D2.clear();
        cMsg.dsMdlEolDt_D3.clear();
    }

    private void getDsMdlUpTime(NSAL0490CMsg cMsg) {
        DS_MDLTMsg inTMsg = new DS_MDLTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.mdlId, cMsg.mdlId);
        DS_MDLTMsg outMsg = (DS_MDLTMsg) EZDTBLAccessor.findByKey(inTMsg);
        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime_DM, outMsg.ezUpTime);
        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone_DM, outMsg.ezUpTimeZone);
    }

    private DS_MDL_EOLTMsg getDsMdlEol(NSAL0490CMsg cMsg, String dsMdlStsCd) {
        DS_MDL_EOLTMsg inTMsg = new DS_MDL_EOLTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.mdlId, cMsg.mdlId);
        ZYPEZDItemValueSetter.setValue(inTMsg.dsMdlEolStsCd, dsMdlStsCd);
        DS_MDL_EOLTMsg outTMsg = (DS_MDL_EOLTMsg) EZDTBLAccessor.findByKey(inTMsg);
        return outTMsg;
    }
    // add end 2016/05/19 CSA Defect#447

    /**
     * page prev (Item Configurations)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void pagePrevForItemConfig(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        NSAL0490CommonLogic.saveCurrentPageToSMsgItemConfig(cMsg, sMsg);

        // del start 2019/05/31 QC#50617
        //if (NSAL0490CommonLogic.checkItemCombination(cMsg, sMsg)) {
        //    return;
        //}
        // del end 2019/05/31 QC#50617

        ZYPTableUtil.clear(cMsg.A);

        cMsg.xxPageShowFromNum_A.setValue(cMsg.xxPageShowFromNum_A.getValueInt() - cMsg.A.length() - 1);
        int pageFrom = cMsg.xxPageShowFromNum_A.getValueInt();
        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pageFrom);

        cMsg.xxPageShowFromNum_A.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum_A.setValue(pageFrom + cMsg.A.getValidCount());
    }

    /**
     * page next (Item Configurations)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void pageNextForItemConfig(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        NSAL0490CommonLogic.saveCurrentPageToSMsgItemConfig(cMsg, sMsg);

        // del start 2019/05/31 QC#50617
        //if (NSAL0490CommonLogic.checkItemCombination(cMsg, sMsg)) {
        //    return;
        //}
        // del end 2019/05/31 QC#50617

        ZYPTableUtil.clear(cMsg.A);

        cMsg.xxPageShowFromNum_A.setValue(cMsg.xxPageShowToNum_A.getValueInt());
        int pageFrom = cMsg.xxPageShowFromNum_A.getValueInt();
        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pageFrom);

        cMsg.xxPageShowFromNum_A.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum_A.setValue(pageFrom + cMsg.A.getValidCount());
    }

    /**
     * page prev (Supply Mapping)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void pagePrevForSupplyMap(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        NSAL0490CommonLogic.saveCurrentPageToSMsgSupplyMap(cMsg, sMsg);

        ZYPTableUtil.clear(cMsg.B);

        cMsg.xxPageShowFromNum_B.setValue(cMsg.xxPageShowFromNum_B.getValueInt() - cMsg.B.length() - 1);
        int pageFrom = cMsg.xxPageShowFromNum_B.getValueInt();
        int i = pageFrom;
        for (; i < pageFrom + cMsg.B.length(); i++) {
            if (i < sMsg.B.getValidCount()) {
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.B.setValidCount(i - pageFrom);

        cMsg.xxPageShowFromNum_B.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum_B.setValue(pageFrom + cMsg.B.getValidCount());
    }

    /**
     * page next (Supply Mapping)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void pageNextForSupplyMap(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        NSAL0490CommonLogic.saveCurrentPageToSMsgSupplyMap(cMsg, sMsg);

        ZYPTableUtil.clear(cMsg.B);

        cMsg.xxPageShowFromNum_B.setValue(cMsg.xxPageShowToNum_B.getValueInt());
        int pageFrom = cMsg.xxPageShowFromNum_B.getValueInt();
        int i = pageFrom;
        for (; i < pageFrom + cMsg.B.length(); i++) {
            if (i < sMsg.B.getValidCount()) {
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.B.setValidCount(i - pageFrom);

        cMsg.xxPageShowFromNum_B.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum_B.setValue(pageFrom + cMsg.B.getValidCount());
    }

    /**
     * set Mdse Info (Item Configurations)
     * @param cMsg NSAL0490CMsg
     */
    private void setMdseInfoForItemConfig(NSAL0490CMsg cMsg) {

        int selectIndex = cMsg.xxCellIdx_A.getValueInt();
        NSAL0490_ACMsg acMsg = cMsg.A.no(selectIndex);

        EZDCStringItem targetMdseCd = null;
        if (NSAL0490Constant.DTL_TP_PRNT.equals(acMsg.xxFlgNm_A.getValue())) {
            targetMdseCd = acMsg.prntMdseCd_A;
        } else {
            targetMdseCd = acMsg.childMdseCd_A;
        }

        // check Exist Master
        // mod start 2015/11/25 CSA Defect#1152
        //if (!NSAL0490CommonLogic.isExistMdse(cMsg, targetMdseCd.getValue())) {
        //    targetMdseCd.setErrorInfo(1, NSAL0490Constant.NSAM0063E, new String[] {NSAL0490Constant.MDSE_CD, NSAL0490Constant.MDSE });
        //    return;
        //}
        if (!NSAL0490CommonLogic.isExistAllMdseV(cMsg, targetMdseCd.getValue())) {
            targetMdseCd.setErrorInfo(1, NSAL0490Constant.NSAM0063E, new String[] {NSAL0490Constant.MDSE_CD, NSAL0490Constant.MDSE });
            return;
        }
        // mod end 2015/11/25 CSA Defect#1152

        // get Mdse Info Data
        setMdseInfoForItemConfig(cMsg, acMsg, targetMdseCd.getValue());
    }

    /**
     * set Mdse Info (Supply Mapping)
     * @param cMsg NSAL0490CMsg
     */
    private void setMdseInfoForSupplyMap(NSAL0490CMsg cMsg) {

        int selectIndex = cMsg.xxCellIdx_B.getValueInt();
        NSAL0490_BCMsg bcMsg = cMsg.B.no(selectIndex);

        EZDCStringItem targetMdseCd = bcMsg.mdseCd_B;

        // check Exist Master
        // mod start 2015/11/25 CSA Defect#1152
        //if (!NSAL0490CommonLogic.isExistMdse(cMsg, targetMdseCd.getValue())) {
        //    targetMdseCd.setErrorInfo(1, NSAL0490Constant.NSAM0063E, new String[] {NSAL0490Constant.MDSE_CD, NSAL0490Constant.MDSE });
        //    return;
        //}
        if (!NSAL0490CommonLogic.isExistAllMdseV(cMsg, targetMdseCd.getValue())) {
            targetMdseCd.setErrorInfo(1, NSAL0490Constant.NSAM0063E, new String[] {NSAL0490Constant.MDSE_CD, NSAL0490Constant.MDSE });
            return;
        }
        // mod end 2015/11/25 CSA Defect#1152

        // get Mdse Info Data
        setMdseInfoSupplyMap(cMsg, bcMsg, targetMdseCd.getValue());
    }

    /**
     * set Mdse Info (Item Configurations)
     * @param cMsg NSAL0490CMsg
     * @param acMsg NSAL0490_ACMsg
     * @param targetMdseCd target MDSE Code
     */
    @SuppressWarnings("unchecked")
    private void setMdseInfoForItemConfig(NSAL0490CMsg cMsg, NSAL0490_ACMsg acMsg, String targetMdseCd) {

        // mod start 2015/11/30 CSA Defect#1152
        String searchMdseCd = targetMdseCd;
        ORD_TAKE_MDSETMsg ordTakeInfo = NSAL0490CommonLogic.getOrdTakeMdse(cMsg, targetMdseCd);
        if (ordTakeInfo != null) {
            searchMdseCd = ordTakeInfo.mdseCd.getValue();
        }

        S21SsmEZDResult ssmResult = NSAL0490Query.getInstance().getMdseInfoForItemConfig(cMsg, searchMdseCd);
        Map<String, Object> mdseInfoMap = (Map<String, Object>) ssmResult.getResultObject();

        // START 2016/09/16 N.Arai [QC#11616, MOD]
        // ZYPEZDItemValueSetter.setValue(acMsg.mdseNm_A, (String) mdseInfoMap.get("MDSE_NM"));
        ZYPEZDItemValueSetter.setValue(acMsg.mdseDescShortTxt_A, (String) mdseInfoMap.get("MDSE_DESC_SHORT_TXT"));
        // END 2016/09/16 N.Arai [QC#11616, MOD]
        ZYPEZDItemValueSetter.setValue(acMsg.mdseCatgNm_A, (String) mdseInfoMap.get("MDSE_CATG_NM"));
        ZYPEZDItemValueSetter.setValue(acMsg.mdseTpNm_A, (String) mdseInfoMap.get("MDSE_TP_NM"));
        ZYPEZDItemValueSetter.setValue(acMsg.coaProdNm_A, (String) mdseInfoMap.get("COA_PROD_NM"));
        ZYPEZDItemValueSetter.setValue(acMsg.mdseCd_A, targetMdseCd);
        // mod end 2015/11/30 CSA Defect#1152
    }

    /**
     * set Mdse Info (Supply Mapping)
     * @param cMsg NSAL0490CMsg
     * @param bcMsg NSAL0490_BCMsg
     * @param targetMdseCd target MDSE Code
     */
    @SuppressWarnings("unchecked")
    private void setMdseInfoSupplyMap(NSAL0490CMsg cMsg, NSAL0490_BCMsg bcMsg, String targetMdseCd) {

        // mod start 2015/11/30 CSA Defect#1152
        String searchMdseCd = targetMdseCd;
        ORD_TAKE_MDSETMsg ordTakeInfo = NSAL0490CommonLogic.getOrdTakeMdse(cMsg, targetMdseCd);
        if (ordTakeInfo != null) {
            searchMdseCd = ordTakeInfo.mdseCd.getValue();
        }

        S21SsmEZDResult ssmResult = NSAL0490Query.getInstance().getMdseInfoForSupplyMap(cMsg, searchMdseCd);
        Map<String, Object> mdseInfoMap = (Map<String, Object>) ssmResult.getResultObject();
        // mod end 2015/11/30 CSA Defect#1152

        ZYPEZDItemValueSetter.setValue(bcMsg.imgSplyOemCd_B, (String) mdseInfoMap.get("IMG_SPLY_OEM_CD"));
        // START 2016/09/16 N.Arai [QC#11616, MOD]
        // ZYPEZDItemValueSetter.setValue(bcMsg.mdseNm_B, (String) mdseInfoMap.get("MDSE_NM"));
        ZYPEZDItemValueSetter.setValue(bcMsg.mdseDescShortTxt_B, (String) mdseInfoMap.get("MDSE_DESC_SHORT_TXT"));
        // END 2016/09/16 N.Arai [QC#11616, MOD]
        ZYPEZDItemValueSetter.setValue(bcMsg.mdseItemClsTpNm_B, (String) mdseInfoMap.get("MDSE_ITEM_CLS_TP_NM"));
        ZYPEZDItemValueSetter.setValue(bcMsg.imgSplyTpNm_B, (String) mdseInfoMap.get("IMG_SPLY_TP_NM"));
        ZYPEZDItemValueSetter.setValue(bcMsg.imgSplyColorTpNm_B, (String) mdseInfoMap.get("IMG_SPLY_COLOR_TP_NM"));
        ZYPEZDItemValueSetter.setValue(bcMsg.imgSplyYieldCnt_B, (BigDecimal) mdseInfoMap.get("IMG_SPLY_YIELD_CNT"));
        ZYPEZDItemValueSetter.setValue(bcMsg.imgSplyYieldTpNm_B, (String) mdseInfoMap.get("IMG_SPLY_YIELD_TP_NM"));
    }

    /**
     * get Current Page Number
     * @param cMsg NSAL0490CMsg
     * @return Current Page Number
     */
    private int getCurrentPageNum(NSAL0490CMsg cMsg, int fromNum, int maxLength) {

        double pageFrom = fromNum;
        double len = maxLength;

        return new BigDecimal(pageFrom / len).setScale(0, BigDecimal.ROUND_CEILING).intValue();
    }

    /**
     * get Parent Date
     * @param sMsg NSAL0490SMsg
     * @param selectLineNum Select Line Number
     * @param startDateFlg Start Date Flag
     * @return Parent Date
     */
    private String getPrntDate(NSAL0490SMsg sMsg, String selectLineNum, boolean startDateFlg) {

        for (int cnt = 0; cnt < sMsg.A.getValidCount(); cnt++) {
            NSAL0490_ASMsg asMsg = sMsg.A.no(cnt);
            if (selectLineNum.equals(asMsg.xxLineNum_A.getValue()) && NSAL0490Constant.DTL_TP_PRNT.equals(asMsg.xxFlgNm_A.getValue())) {
                if (startDateFlg) {
                    return asMsg.effFromDt_A.getValue();
                }
                return asMsg.effThruDt_A.getValue();
            }
        }

        return null;
    }
}
