package business.blap.NPAL1080;

import static business.blap.NPAL1080.constant.NPAL1080Constant.BUSINESS_APPLICATION_ID;
import static business.blap.NPAL1080.constant.NPAL1080Constant.COMNT_LENGTH;
import static business.blap.NPAL1080.constant.NPAL1080Constant.DB_PARAM_FLAG_N;
import static business.blap.NPAL1080.constant.NPAL1080Constant.DB_PARAM_FLAG_Y;
import static business.blap.NPAL1080.constant.NPAL1080Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1080.constant.NPAL1080Constant.DB_PARAM_MDSE_CD;
import static business.blap.NPAL1080.constant.NPAL1080Constant.DB_PARAM_PO_STS_CD_CANCELED;
import static business.blap.NPAL1080.constant.NPAL1080Constant.DB_PARAM_PO_STS_CD_CLOSED;
import static business.blap.NPAL1080.constant.NPAL1080Constant.DB_PARAM_PRCH_REQ_NUM;
import static business.blap.NPAL1080.constant.NPAL1080Constant.DB_PARAM_PRCH_REQ_REC_TP_CD;
import static business.blap.NPAL1080.constant.NPAL1080Constant.DB_PARAM_SALES_DATE;
import static business.blap.NPAL1080.constant.NPAL1080Constant.DB_PARAM_SCE_ORD_TP_CD;
import static business.blap.NPAL1080.constant.NPAL1080Constant.DB_PARAM_VND_CD;
import static business.blap.NPAL1080.constant.NPAL1080Constant.MESSAGE_KIND_ERROR;
import static business.blap.NPAL1080.constant.NPAL1080Constant.MESSAGE_KIND_WARN;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NLAM0173E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NLBM1341E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NMAM0836E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NMXC100001_BIZ_APP_KEY_ID;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM0006E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM0008E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM0046E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM0049E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM0076E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM0080E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM0088E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM1329E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM1360E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM1361E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM1363E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM1365E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM1368E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM1579E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM1594E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM1595E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM1596E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM1597E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM1606E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM1614E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM1615E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM1616E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM1619E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM1622E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM1623E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM1624E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM1625E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM1628E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM1638E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM1649E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM1650W;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPAM1660E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.NPZM0272E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.RUN_MODE_SUBMITTED;
import static business.blap.NPAL1080.constant.NPAL1080Constant.TAB_DETAIL;
import static business.blap.NPAL1080.constant.NPAL1080Constant.TAB_HEADER;
import static business.blap.NPAL1080.constant.NPAL1080Constant.VAR_CONST_CREATE_MATERIAL_PARTS;
import static business.blap.NPAL1080.constant.NPAL1080Constant.ZZM8100I;
import static business.blap.NPAL1080.constant.NPAL1080Constant.ZZM9000E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.ZZM9001E;
import static business.blap.NPAL1080.constant.NPAL1080Constant.ZZZM9003I;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1080.common.NPAL1080CommonLogic;
import business.blap.NPAL1080.constant.NPAL1080Constant;
import business.db.ALL_MDSE_VTMsg;
import business.db.ALL_MDSE_VTMsgArray;
import business.db.MDSETMsg;
import business.db.MDSE_SFTY_INFOTMsg;
import business.db.OTBD_CARR_VTMsg;
import business.db.OTBD_CARR_VTMsgArray;
import business.db.PRCH_REQTMsg;
import business.db.PRCH_REQ_DTLTMsg;
import business.db.RTL_WHTMsg;
import business.db.SHPG_SVC_LVL_CARR_RELNTMsg;
import business.db.SHPG_SVC_LVL_CARR_RELNTMsgArray;
import business.parts.NPZC103001PMsg;
import business.parts.NPZC103001_prchReqInfoPMsg;

import com.canon.cusa.s21.api.NPZ.NPZC103001.NPZC103001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.constant.NPZC103001Constant;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWHData;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_QLFY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NPAL1080 Tech Parts Request Entry
 * Function Name : update business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/16/2015   CITS            Yasushi Nomura  Create          N/A
 * 05/06/2016   CSAI            D.Fukaya        Update          QC#7628
 * 09/21/2016   CITS            K.Ogino         Update          QC#8195
 * 11/22/2016   CITS            K.Ogino         Create          QC#8295
 * 02/14/2017   CITS            M.Naito         Update          QC#17456
 * 08/22/2017   CITS            R.Shimamoto     Update          QC#19966
 * 08/31/2017   CITS            K.Kameoka       Update          Sol#369(QC#19243)
 * 09/01/2017   CITS            R.Shimamoto     Update          QC#20439
 * 10/16/2017   CITS            T.Tokutomi      Update          QC#21657
 * 12/26/2017   CITS            K.Ogino         Update          QC#21870
 * 01/17/2018   CITS            K.Ogino         Update          QC#21909
 * 02/26/2018   CITS            S.Katsuma       Update          QC#24312
 * 03/13/2018   CITS            S.Katsuma       Update          SOL#118(QC#12110)
 * 05/25/2018   CITS            Y.Iwasaki       Update          SOL#135(QC#2366)
 * 06/01/2018   CITS            T.Tokutomi      Update          SOL#135(QC#2366)
 * 07/05/2018   CITS            K.Ogino         Update          QC#24918
 * 08/17/2018   CITS            T.Tokutomi      Update          QC#26581
 * 11/08/2018   CITS            T.Tokutomi      Update          QC#29020
 * 12/05/2018   CITS            M.Naito         Update          QC#25900
 * 01/24/2019   CITS            K.Ogino         Update          QC#29988
 * 02/14/2019   Fujitsu         S.Takami        Update          QC#30358
 * 04/23/2019   CITS            K.Ogino         Update          QC#31164
 * 08/09/2019   CITS            T.Wada          Update          QC#52436
 * 08/12/2019   CITS            T.Wada          Update          QC#52445
 * 12/02/2019   CITS            K.Ogino         Update          QC#54672
 * 02/13/2020   CITS            Y.Iwasaki       Update          QC#55702,55709
 * 03/05/2020   Fujitsu         R.Nakamura      Update          QC#56103
 * 03/10/2020   Fujitsu         R.Nakamura      Update          QC#56126
 * 03/10/2020   Fujitsu         R.Nakamura      Update          QC#56178
 * 06/09/2022   CITS            A.Cullano       Update          QC#60154
 * 08/03/2023   Hitachi         T.Kuroda        Update          QC#61648
 *</pre>
 */
public class NPAL1080BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            cMsg.setCommitSMsg(true);

            if ("NPAL1080Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NPAL1080Scrn00_CMN_Save((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);
            } else if ("NPAL1080Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NPAL1080Scrn00_CMN_Submit((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);
            } else if ("NPAL1080Scrn00_PRFreeze".equals(screenAplID)) {
                doProcess_NPAL1080Scrn00_Freeze((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);
            } else if ("NPAL1080Scrn00_HeaderCancel".equals(screenAplID)) {
                doProcess_NPAL1080Scrn00_HeaderCancel((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);
            } else if ("NPAL1080Scrn00_LineCancel".equals(screenAplID)) {
                doProcess_NPAL1080Scrn00_LineCancel((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);
            } else if ("NPAL1080Scrn00_HeaderClose".equals(screenAplID)) {
                doProcess_NPAL1080Scrn00_HeaderClose((NPAL1080CMsg) cMsg, (NPAL1080SMsg) sMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * Save Event
     * </pre>
     * @param cMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NPAL1080Scrn00_CMN_Save(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {
        //Reset warning flag
        sMsg.xxBtnFlg_H1.clear();

        // QC#29020 Add. Setup Address.
        NPAL1080CommonLogic.setUpAddress4cMsg(getGlobalCompanyCode(), cMsg);

        // Input Check
        // Get Create Material Parts#
        String[] materialParts = ZYPCodeDataUtil.getVarCharConstValue(VAR_CONST_CREATE_MATERIAL_PARTS, getGlobalCompanyCode()).split(",");

        // Find ReqRecType
        String prchReqRecTpCd = NPAL1080CommonLogic.findRequisitionRecordTypeCode(cMsg.prchReqTpCd_SE.getValue(), getGlobalCompanyCode());
        if (prchReqRecTpCd != null) {
            cMsg.prchReqRecTpCd_H2.setValue(prchReqRecTpCd);
        }
        if (!NPAL1080CommonLogic.setTechTocCodeAndName(cMsg, getGlobalCompanyCode())) {
            return;
        }

        NPAL1080CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        if (!checkForSaveSubmit(cMsg, sMsg, getGlobalCompanyCode(), materialParts)) {
            return;
        }

        // Save PR
        PRCH_REQTMsg tMsg = findPR(cMsg);

        NPZC103001PMsg pMsg = null;
        if (tMsg != null) {
            // update
            sMsg.prchReqNum_H1.setValue(cMsg.prchReqNum_HD.getValue());
            // optimistic lock
            if (!tryLock(cMsg, sMsg, tMsg)) {
                // cannot lock
                cMsg.prchReqNum_H1.setValue(cMsg.prchReqNum_HD.getValue());
                return;
            }
            pMsg = createNPZC103001PMsgForSaveUpdate(sMsg, tMsg);

        } else {
            // new
            pMsg = createNPZC103001PMsgForSaveCreate(sMsg);
        }

        // call api
        if (executeNPZC1030(cMsg, pMsg)) {
            cMsg.setMessageInfo(ZZZM9003I, new String[] {"Save" });
        }
    }

    /**
     * <pre>
     * Submit Event
     * </pre>
     * @param cMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NPAL1080Scrn00_CMN_Submit(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {

        // QC#29020 Add. Setup Address.
        NPAL1080CommonLogic.setUpAddress4cMsg(getGlobalCompanyCode(), cMsg);

        // Input Check
        // Get Create Material Parts#
        String[] materialParts = ZYPCodeDataUtil.getVarCharConstValue(VAR_CONST_CREATE_MATERIAL_PARTS, getGlobalCompanyCode()).split(",");

        // Find ReqRecType
        String prchReqRecTpCd = NPAL1080CommonLogic.findRequisitionRecordTypeCode(cMsg.prchReqTpCd_SE.getValue(), getGlobalCompanyCode());
        if (prchReqRecTpCd != null) {
            cMsg.prchReqRecTpCd_H2.setValue(prchReqRecTpCd);
        }
        if (!NPAL1080CommonLogic.setTechTocCodeAndName(cMsg, getGlobalCompanyCode())) {
            return;
        }

        NPAL1080CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        if (!checkForSaveSubmit(cMsg, sMsg, getGlobalCompanyCode(), materialParts)) {
            return;
        }

        // Save PR
        PRCH_REQTMsg tMsg = findPR(cMsg);
        // Add Start 2020/03/18 QC#56103
        boolean newTechReqFlg = false;
        // Add End 2020/03/18 QC#56103

        NPZC103001PMsg pMsg = null;
        if (tMsg == null) {
            // Add Start 2020/03/18 QC#56103
            newTechReqFlg = true;
            // Add End 2020/03/18 QC#56103

            // new
            pMsg = createNPZC103001PMsgForSaveCreate(sMsg);

            // call api
            if (!executeNPZC1030(cMsg, pMsg)) {
                return;
            }
            ZYPEZDItemValueSetter.setValue(sMsg.prchReqNum_H1, pMsg.prchReqNum);

            PRCH_REQTMsg inMsg = new PRCH_REQTMsg();
            setValue(inMsg.prchReqNum, pMsg.prchReqNum);
            setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
            tMsg = (PRCH_REQTMsg) EZDTBLAccessor.findByKey(inMsg);
            if (tMsg == null) {
                // error
                return;
            } else {
                // QC#2366 Update.
                ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum_H1, tMsg.prchReqNum);
                NPAL1080CommonLogic.searchDetail(cMsg, sMsg, getGlobalCompanyCode(), false);
            }

        } else {
            // Submit PR
            sMsg.prchReqNum_H1.setValue(cMsg.prchReqNum_HD.getValue());
            tMsg = lockPR(cMsg, sMsg);
            if (tMsg == null) {
                // lock error
                return;
            }

        }

        // call api
        // 2019/02/14 QC#30358 Add Start
        ZYPEZDItemValueSetter.setValue(sMsg.xxEdtModeFlg, cMsg.xxEdtModeFlg);
        // 2019/02/14 QC#30358 Add End

        pMsg = createNPZC103001PMsgForSubmitUpdate(sMsg, tMsg);
        if (executeNPZC1030(cMsg, pMsg)) {
            cMsg.setMessageInfo(ZZZM9003I, new String[] {"Submit" });
        }

        // Add Start 2020/03/05 QC#56103
        if (cMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR) || cMsg.getMessageCode().endsWith(MESSAGE_KIND_WARN)) {
            NPAL1080CommonLogic.clearValueForError(getGlobalCompanyCode(), cMsg, sMsg, newTechReqFlg);
        }
        // Add End 2020/03/05 QC#56103
    }

    /**
     * Freeze/Un Freeze Event
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NPAL1080Scrn00_Freeze(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {

        if (!checkChkboxAllOff(cMsg)) {
            cMsg.setMessageInfo(NPAM0049E);
            return;
        }
        if(checkCancelledLineSelected(cMsg)) {
            cMsg.setMessageInfo(NPAM1649E);
            return;
        }

        // Input Check
        // Get Create Material Parts#
        String[] materialParts = ZYPCodeDataUtil.getVarCharConstValue(VAR_CONST_CREATE_MATERIAL_PARTS, getGlobalCompanyCode()).split(",");

        // Find ReqRecType
        String prchReqRecTpCd = NPAL1080CommonLogic.findRequisitionRecordTypeCode(cMsg.prchReqTpCd_SE.getValue(), getGlobalCompanyCode());
        if (prchReqRecTpCd != null) {
            cMsg.prchReqRecTpCd_H2.setValue(prchReqRecTpCd);
        }
        if (!NPAL1080CommonLogic.setTechTocCodeAndName(cMsg, getGlobalCompanyCode())) {
            return;
        }
        NPAL1080CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        NPAL1080CommonLogic.copyPrchReqCmntTxtToLines(sMsg);
        // START 2018/02/26 S.Katsuma [QC#24312,MOD]
        // if (!checkForSaveSubmit(cMsg, sMsg, getGlobalCompanyCode(),
        // materialParts)) {
        // return;
        // }
        // check detail
        // QC#52436 Mod Start
        cMsg.xxPageShowFromNum_D2.setValue(1);
        while (true) {
            NPAL1080CommonLogic.setPagePos(cMsg, sMsg);
            ZYPTableUtil.clear(cMsg.A);
            int cMsgCount = 0;
            for (int i = cMsg.xxPageShowFromNum_D2.getValueInt() - 1; i < cMsg.xxPageShowToNum_D2.getValueInt(); i++) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(cMsgCount), null);
                cMsgCount++;
            }
            cMsg.A.setValidCount(cMsgCount);
            for (int i = 0; i <cMsg.A.getValidCount(); i++) {
                if (NPAL1080CommonLogic.isChecked(cMsg.A.no(i))) {
                    if (!isNewLine(cMsg.A.no(i))) {
                        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).prchReqFrzFlg_D1.getValue())) {
                            if (cMsg.A.no(i).prchReqBalQty_D1.getValue().compareTo(BigDecimal.ZERO) == 0) {
                                cMsg.A.no(i).xxChkBox_D1.setErrorInfo(1, NPAM1615E);
                                return;
                            }
                        }
                    }
                }
            }
            if (cMsg.xxPageShowToNum_D2.getValue().compareTo(cMsg.xxPageShowOfNum_D2.getValue()) == 0) {
                break;
            }
            cMsg.xxPageShowFromNum_D2.setValue(cMsg.xxPageShowFromNum_D2.getValueInt() + cMsg.A.length());
        }
        // QC#52436 Mod End       
        // END 2018/02/26 S.Katsuma [QC#24312,MOD]

        PRCH_REQTMsg tMsg = lockPR(cMsg, sMsg);
        if (tMsg == null) {
            return;
        }

        boolean inNewLine = false;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (isNewLine(sMsg.A.no(i))) {
                inNewLine = true;
                break;
            }
        }
        NPZC103001PMsg pMsg = null;
        if (inNewLine) {
            // START 2018/02/26 S.Katsuma [QC#24312,MOD]
            // pMsg = createNPZC103001PMsgForSubmitUpdate(sMsg, tMsg);
            pMsg = createNPZC103001PMsgForSaveUpdate(sMsg, tMsg);
            // END 2018/02/26 S.Katsuma [QC#24312,MOD]
            if (executeNPZC1030(cMsg, pMsg)) {
                for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                    if (isNewLine(sMsg.A.no(i))) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqLineNum_D1, pMsg.prchReqInfo.no(i).prchReqLineNum);
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqLineSubNum_D1, pMsg.prchReqInfo.no(i).prchReqLineSubNum);
                        sMsg.A.no(i).prchReqFrzFlg_D1.setValue(ZYPConstant.FLG_OFF_N);
                    }
                }
            } else {
                return;
            }
        }

        // QC#52445 Add
        ZYPEZDItemValueSetter.setValue(sMsg.prchReqNum_HD, cMsg.prchReqNum_HD);

        pMsg = createNPZC103001PMsgForFreeze(sMsg);
        if (executeNPZC1030(cMsg, pMsg)) {
            cMsg.setMessageInfo(ZZZM9003I, new String[] {"Freeze / UnFreeze" });
        }
    }

    /**
     * <pre>
     * HeaderCancel Event
     * </pre>
     * @param cMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NPAL1080Scrn00_HeaderCancel(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {
        NPAL1080CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        if (lockPR(cMsg, sMsg) == null) {
            return;
        }

        NPZC103001PMsg pMsg = new NPZC103001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC103001Constant.MODE_CANCEL);
        ZYPEZDItemValueSetter.setValue(pMsg.eventId, NPZC103001Constant.EVENT_SUBMIT);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        
        // QC#52445 Mod
//        ZYPEZDItemValueSetter.setValue(pMsg.prchReqNum, sMsg.prchReqNum_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqNum, cMsg.prchReqNum_HD);

        ZYPEZDItemValueSetter.setValue(pMsg.procDt, ZYPDateUtil.getSalesDate());

        if (executeNPZC1030(cMsg, pMsg)) {
            cMsg.setMessageInfo(ZZZM9003I, new String[] {"HeaderCancel" });
        }
    }

    /**
     * <pre>
     * LineCancel Event
     * </pre>
     * @param cMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NPAL1080Scrn00_LineCancel(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {

        if (!checkChkboxAllOff(cMsg)) {
            cMsg.setMessageInfo(NPAM0049E);
            return;
        }
        if(checkCancelledLineSelected(cMsg)) {
            cMsg.setMessageInfo(NPAM1649E);
            return;
        }

        NPAL1080CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        NPAL1080CommonLogic.copyPrchReqCmntTxtToLines(sMsg);

        boolean inNewLine = false;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if ((NPAL1080CommonLogic.isChecked(sMsg.A.no(i))) && (isNewLine(sMsg.A.no(i)))) {
                inNewLine = true;
                break;
            }
        }
        if (inNewLine) {
            sMsg.xxEdtModeFlg.setValue("1");
            int cancelCount = 0;
            List<NPAL1080_ASMsg> valueList = new ArrayList<NPAL1080_ASMsg>();
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                if (isNewLine(sMsg.A.no(i))) {
                    // Add Line
                    if (NPAL1080CommonLogic.isChecked(sMsg.A.no(i))) {
                        cancelCount++;
                    } else {
                        NPAL1080_ASMsg asMsg = new NPAL1080_ASMsg();
                        EZDMsg.copy(sMsg.A.no(i), null, asMsg, null);
                        valueList.add(asMsg);
                    }
                }
            }
            if (0 < valueList.size()) {
                int num = 0;
                for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                    if (!isNewLine(sMsg.A.no(i))) {
                        int temp = Integer.valueOf(sMsg.A.no(i).prchReqLineNum_D1.getValue());
                        if (num < temp) {
                            num = temp;
                        }
                    }
                }
                for (int i = 0, j = 0; (i < sMsg.A.getValidCount()) && (j < valueList.size()); i++) {
                    if (isNewLine(sMsg.A.no(i))) {
                        num++;
                        valueList.get(j).prchReqLineNum_D1.setValue(String.format("%03d", num));
                        EZDMsg.copy(valueList.get(j), null, sMsg.A.no(i), null);
                        j++;
                    }
                }
            }
            sMsg.A.setValidCount(sMsg.A.getValidCount() - cancelCount);
            NPAL1080CommonLogic.setPagePos(cMsg, sMsg);
            NPAL1080CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
        } else {
            sMsg.xxEdtModeFlg.clear();

            // QC#26581 Add check
            if (isPossibleLineCancel(sMsg)) {
                // QC#52445 Add
                ZYPEZDItemValueSetter.setValue(sMsg.prchReqNum_HD, cMsg.prchReqNum_HD);

                NPZC103001PMsg pMsg = createNPZC103001PMsgForLineCancel(sMsg);
                if (executeNPZC1030(cMsg, pMsg)) {
                    cMsg.setMessageInfo(ZZZM9003I, new String[] {"LineCancel" });
                }
            } else {
                // Error.
                NPAL1080CommonLogic.setPagePos(cMsg, sMsg);
                NPAL1080CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            }
        }

    }

    private boolean checkChkboxAllOff(NPAL1080CMsg cMsg) {
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (NPAL1080CommonLogic.isChecked(cMsg.A.no(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCancelledLineSelected(NPAL1080CMsg cMsg) {
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (NPAL1080CommonLogic.isChecked(cMsg.A.no(i))) {
                if (PRCH_REQ_LINE_STS.CANCELLED.equals(cMsg.A.no(i).prchReqLineStsCd_D1.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Creates new pMsg for NPZC103001 mode=create event=save
     * @param sMsg
     * @return
     */
    private NPZC103001PMsg createNPZC103001PMsgForSaveCreate(NPAL1080SMsg sMsg) {
        NPZC103001PMsg pMsg = createNPZC103001PMsgForSubmitCreate(sMsg);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqApvlStsCd, PRCH_REQ_APVL_STS.ENTERED); // #13
        return pMsg;
    }

    /**
     * Creates new pMsg for NPZC103001 mode=create event=submit
     * @param sMsg
     * @return
     */
    private NPZC103001PMsg createNPZC103001PMsgForSubmitCreate(NPAL1080SMsg sMsg) {
        NPZC103001PMsg pMsg = new NPZC103001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC103001Constant.MODE_CREATE);
        ZYPEZDItemValueSetter.setValue(pMsg.eventId, NPZC103001Constant.EVENT_SUBMIT);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(pMsg.procDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqRecTpCd, sMsg.prchReqRecTpCd_H2);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqTpCd, sMsg.prchReqTpCd_SE);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqCratByPsnCd, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqRqstByPsnCd, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        // QC#17456
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqSrcTpCd, sMsg.prchReqSrcTpCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqApvlStsCd, PRCH_REQ_APVL_STS.SUBMIT_FOR_APPROVAL);
        // Service Request#
        ZYPEZDItemValueSetter.setValue(pMsg.fsrNum, sMsg.fsrNum_H1);
        // Service Request Task#
        ZYPEZDItemValueSetter.setValue(pMsg.svcTaskNum, sMsg.svcTaskNum_H1);
        // Service Request Serial#
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachSerNum, sMsg.svcMachSerNum_H1);
        // Technician Code
        ZYPEZDItemValueSetter.setValue(pMsg.rqstTechTocCd, sMsg.rqstTechTocCd_H1);
        // Ship To Customer (Code)
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, sMsg.shipToCustCd_H1);
        // QC#21657
        if (ZYPCommonFunc.hasValue(sMsg.shipToCustCd_H1)) {
            // Ship to customer
            ZYPEZDItemValueSetter.setValue(pMsg.poQlfyCd, PO_QLFY.TECH_REQUEST);
        }
        // Attention To
        // QC#21870
        if (ZYPCommonFunc.hasValue(sMsg.ctacPsnNm_H1)) {
            ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnNm, sMsg.ctacPsnNm_H1);
        } else {
            RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
            ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, sMsg.destRtlWhCd_H1);

            rtlWhTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(rtlWhTMsg);

            if (rtlWhTMsg != null && RTL_WH_CATG.CUSTOMER.equals(rtlWhTMsg.rtlWhCatgCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnNm, sMsg.techNm_H1);
            }
        }
        // Shipping Instructions
        ZYPEZDItemValueSetter.setValue(pMsg.delyAddlCmntTxt, sMsg.delyAddlCmntTxt_H2);

        // Date & Time Needed
        // QC:7389
        // String sTimeNeeded = "";
        // if (ZYPCommonFunc.hasValue(sMsg.xxDtNm_H2)) {
        // int[] temp =
        // NPAL1080CommonLogic.getTimeIntValue(sMsg.xxDtNm_H2.getValue());
        // if ((temp != null) && (temp[0] < 12)) {
        // if
        // (NPAL1080Constant.TIME_PM.equals(sMsg.dsCondConstCd_SE.getValue()))
        // {
        // temp[0] += HALF_DAY_HOURS;
        // }
        // sTimeNeeded = String.format("%02d%02d", temp[0], temp[1]);
        // }
        // }

        int i;
        for (i = 0; i < sMsg.A.getValidCount(); i++) {
            NPZC103001_prchReqInfoPMsg apMsg = pMsg.prchReqInfo.no(i);
            NPAL1080_ASMsg asMsg = sMsg.A.no(i);
            // Date & Time Needed
            ZYPEZDItemValueSetter.setValue(apMsg.rqstRcvDt, sMsg.rqstRcvDt_H1);
            // QC:7389
            // ZYPEZDItemValueSetter.setValue(apMsg.rqstRcvTm,
            // sTimeNeeded);

            if (ZYPCommonFunc.hasValue(sMsg.prchReqTpCd_SE) && ZYPCommonFunc.hasValue(sMsg.shpgSvcLvlCd_SE) //
                    && PRCH_REQ_TP.PREMIUM_RUSH.equals(sMsg.prchReqTpCd_SE.getValue()) //
                    && SHPG_SVC_LVL.SCHD_DELIVERY.equals(sMsg.shpgSvcLvlCd_SE.getValue())) {

                String schdDelyTm = NPAL1080CommonLogic.getAllDayTimes(sMsg.xxDtTm_H1.getValue(), sMsg.rqstRcvDtTxt_SL.getValue());
                ZYPEZDItemValueSetter.setValue(apMsg.rqstRcvTm, schdDelyTm);

            } else {
                for (int j = 0; j < sMsg.dsCondConstCd_CD.length(); j++) {
                    if (sMsg.dsCondConstCd_SE.getValue().equals(sMsg.dsCondConstCd_CD.no(j).getValue())) {

                        ZYPEZDItemValueSetter.setValue(apMsg.rqstRcvTm, NPAL1080CommonLogic.convertRequestTime(sMsg.rqstRcvDtTxt_DI.no(j).getValue()));
                        break;
                    }
                }
            }
            // Line Type
            if (PRCH_REQ_REC_TP.TECH_RETURN.equals(sMsg.prchReqRecTpCd_H2.getValue())) {
                ZYPEZDItemValueSetter.setValue(apMsg.prchReqLineTpCd, asMsg.prchReqLineTpCd_SE);
            }
            // Shipping Service Level (Req Service Level)
            ZYPEZDItemValueSetter.setValue(apMsg.shpgSvcLvlCd, sMsg.shpgSvcLvlCd_SE);

            // Carrier Code (Requested Carrier)
            ZYPEZDItemValueSetter.setValue(apMsg.carrCd, sMsg.carrCd_AC);
            // Source Type
            ZYPEZDItemValueSetter.setValue(apMsg.procrTpCd, asMsg.procrTpCd_SE);
            if (PRCH_REQ_REC_TP.TECH_REQUEST.equals(sMsg.prchReqRecTpCd_H2.getValue())) {
                // Destination
                apMsg.destInvtyLocCd.setValue(NPAL1080CommonLogic.joinRtlWhCdAndSwhCd(sMsg.destRtlWhCd_H1, sMsg.destRtlSwhCd_H1));
                // Souce
                apMsg.srcInvtyLocCd.setValue(NPAL1080CommonLogic.joinRtlWhCdAndSwhCd(asMsg.prntVndCd_D1, asMsg.vndCd_D1));
                if (PROCR_TP.SUPPLIER.equals(asMsg.procrTpCd_SE.getValue())) {
                    // WH / Supplier
                    ZYPEZDItemValueSetter.setValue(apMsg.prntVndCd, asMsg.prntVndCd_D1);
                    // SWH / Site
                    ZYPEZDItemValueSetter.setValue(apMsg.vndCd, asMsg.vndCd_D1);
                }
            } else if (PRCH_REQ_REC_TP.TECH_RETURN.equals(sMsg.prchReqRecTpCd_H2.getValue())) {
                // Destination
                apMsg.destInvtyLocCd.setValue(NPAL1080CommonLogic.joinRtlWhCdAndSwhCd(asMsg.prntVndCd_D1, asMsg.vndCd_D1));
                // Souce
                apMsg.srcInvtyLocCd.setValue(NPAL1080CommonLogic.joinRtlWhCdAndSwhCd(sMsg.destRtlWhCd_H1, sMsg.destRtlSwhCd_H1));
                // Pro Num
                ZYPEZDItemValueSetter.setValue(apMsg.proNum, asMsg.proNum_D1);
            }
            // Item Number
            ZYPEZDItemValueSetter.setValue(apMsg.mdseCd, asMsg.mdseCd_D1);
            // Req Qty
            ZYPEZDItemValueSetter.setValue(apMsg.prchReqQty, asMsg.prchReqQty_D1);
            // Line Comment
            ZYPEZDItemValueSetter.setValue(apMsg.prchReqLineCmntTxt, asMsg.prchReqLineCmntTxt_D1);
            if (i == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.prchGrpCd, getPrchGrpCd(asMsg.mdseCd_D1.getValue()));
            }
        }
        pMsg.prchReqInfo.setValidCount(i);
        return pMsg;
    }

    /**
     * Creates new pMsg for NPZC103001 mode=update event=save
     * @param cMsg
     * @param sMsg
     * @return
     */
    private NPZC103001PMsg createNPZC103001PMsgForSaveUpdate(NPAL1080SMsg sMsg, PRCH_REQTMsg tMsg) {
        NPZC103001PMsg pMsg = createNPZC103001PMsgForSubmitUpdate(sMsg, tMsg);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqApvlStsCd, PRCH_REQ_APVL_STS.ENTERED); // #13
        return pMsg;
    }

    /**
     * Creates new pMsg for NPZC103001 mode=update event=submit
     * @param cMsg
     * @param sMsg
     * @return
     */
    private NPZC103001PMsg createNPZC103001PMsgForSubmitUpdate(NPAL1080SMsg sMsg, PRCH_REQTMsg tMsg) {
        NPZC103001PMsg pMsg = new NPZC103001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC103001Constant.MODE_UPDATE);
        ZYPEZDItemValueSetter.setValue(pMsg.eventId, NPZC103001Constant.EVENT_SUBMIT);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(pMsg.procDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqNum, sMsg.prchReqNum_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqRecTpCd, sMsg.prchReqRecTpCd_H2);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqTpCd, sMsg.prchReqTpCd_SE);
        // QC#20439
        if (ZYPCommonFunc.hasValue(tMsg.prchReqRqstByPsnCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqRqstByPsnCd, tMsg.prchReqRqstByPsnCd.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqRqstByPsnCd, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }
        if (PRCH_REQ_APVL_STS.ENTERED.equals(sMsg.prchReqApvlStsCd_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqCratByPsnCd, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        // QC#17456
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqSrcTpCd, sMsg.prchReqSrcTpCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqApvlStsCd, PRCH_REQ_APVL_STS.SUBMIT_FOR_APPROVAL);
        // Service Request#
        ZYPEZDItemValueSetter.setValue(pMsg.fsrNum, sMsg.fsrNum_H1);
        // Service Request Task#
        ZYPEZDItemValueSetter.setValue(pMsg.svcTaskNum, sMsg.svcTaskNum_H1);
        // Service Request Serial#
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachSerNum, sMsg.svcMachSerNum_H1);
        // Technician Code
        ZYPEZDItemValueSetter.setValue(pMsg.rqstTechTocCd, sMsg.rqstTechTocCd_H1);
        // Ship To Customer (Code)
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, sMsg.shipToCustCd_H1);
        // QC#21657
        if (ZYPCommonFunc.hasValue(sMsg.shipToCustCd_H1)) {
            // Ship to customer
            ZYPEZDItemValueSetter.setValue(pMsg.poQlfyCd, PO_QLFY.TECH_REQUEST);
        }
        // Attention To
        // QC#21870
        if (ZYPCommonFunc.hasValue(sMsg.ctacPsnNm_H1)) {
            ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnNm, sMsg.ctacPsnNm_H1);
        } else {
            RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
            ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, sMsg.destRtlWhCd_H1);

            rtlWhTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(rtlWhTMsg);

            if (rtlWhTMsg != null && RTL_WH_CATG.CUSTOMER.equals(rtlWhTMsg.rtlWhCatgCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnNm, sMsg.techNm_H1);
            }
        }
        // Shipping Instructions
        ZYPEZDItemValueSetter.setValue(pMsg.delyAddlCmntTxt, sMsg.delyAddlCmntTxt_H2);
        // QC:7389
        // Date & Time Needed
        // String sTimeNeeded = "";
        // if (ZYPCommonFunc.hasValue(sMsg.xxDtNm_H2)) {
        // int[] temp =
        // NPAL1080CommonLogic.getTimeIntValue(sMsg.xxDtNm_H2.getValue());
        // if ((temp != null) && (temp[0] < 12)) {
        // if
        // (NPAL1080Constant.TIME_PM.equals(sMsg.dsCondConstCd_SE.getValue()))
        // {
        // temp[0] += HALF_DAY_HOURS;
        // }
        // sTimeNeeded = String.format("%02d%02d", temp[0], temp[1]);
        // }
        // }
        int i;
        int pMsgCount = 0;
        // 2019/02/14 QC#30358 Add Start
        boolean isSubmittedRequest = S21StringUtil.isEquals(RUN_MODE_SUBMITTED, sMsg.xxEdtModeFlg.getValue());
        // 2019/02/14 QC#30358 Add End
        for (i = 0; i < sMsg.A.getValidCount(); i++) {

            // QC#26662 Update.
            if (isInsourcedLine(sMsg.prchReqNum_H1.getValue()
                    ,sMsg.A.no(i).prchReqLineNum_D1.getValue()//
                    ,sMsg.A.no(i).prchReqLineSubNum_D1.getValue())//
                    || PRCH_REQ_LINE_STS.CANCELLED.equals(sMsg.A.no(i).prchReqLineStsCd_D1.getValue())//
                    || PRCH_REQ_LINE_STS.CLOSED.equals(sMsg.A.no(i).prchReqLineStsCd_D1.getValue())) {
                // Not need check line.
                continue;
            }
            // 2019/02/14 QC#30358 Add Start
            boolean isProtectedLine = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, sMsg.A.no(i).submtFlg_D1.getValue());
            if (isSubmittedRequest && isProtectedLine) {
                continue;
            }
            // 2019/02/14 QC#30358 Add End

            NPZC103001_prchReqInfoPMsg apMsg = pMsg.prchReqInfo.no(pMsgCount);
            NPAL1080_ASMsg asMsg = sMsg.A.no(i);

            ZYPEZDItemValueSetter.setValue(apMsg.prchReqLineNum, asMsg.prchReqLineNum_D1);
            ZYPEZDItemValueSetter.setValue(apMsg.prchReqLineSubNum, asMsg.prchReqLineSubNum_D1);
            // Date & Time Needed
            ZYPEZDItemValueSetter.setValue(apMsg.rqstRcvDt, sMsg.rqstRcvDt_H1);
            // QC:7389
            // ZYPEZDItemValueSetter.setValue(apMsg.rqstRcvTm,
            // sTimeNeeded);
            if (ZYPCommonFunc.hasValue(sMsg.prchReqTpCd_SE) && ZYPCommonFunc.hasValue(sMsg.shpgSvcLvlCd_SE) //
                    && PRCH_REQ_TP.PREMIUM_RUSH.equals(sMsg.prchReqTpCd_SE.getValue()) //
                    && SHPG_SVC_LVL.SCHD_DELIVERY.equals(sMsg.shpgSvcLvlCd_SE.getValue())) {

                String schdDelyTm = NPAL1080CommonLogic.getAllDayTimes(sMsg.xxDtTm_H1.getValue(), sMsg.rqstRcvDtTxt_SL.getValue());
                ZYPEZDItemValueSetter.setValue(apMsg.rqstRcvTm, schdDelyTm);

            } else {
                for (int j = 0; j < sMsg.dsCondConstCd_CD.length(); j++) {
                    if (sMsg.dsCondConstCd_SE.getValue().equals(sMsg.dsCondConstCd_CD.no(j).getValue())) {

                        ZYPEZDItemValueSetter.setValue(apMsg.rqstRcvTm, NPAL1080CommonLogic.convertRequestTime(sMsg.rqstRcvDtTxt_DI.no(j).getValue()));
                        break;
                    }
                }
            }
            // Line Type
            if (PRCH_REQ_REC_TP.TECH_RETURN.equals(sMsg.prchReqRecTpCd_H2.getValue())) {
                ZYPEZDItemValueSetter.setValue(apMsg.prchReqLineTpCd, asMsg.prchReqLineTpCd_SE);
            }
            // Shipping Service Level (Req Service Level)
            ZYPEZDItemValueSetter.setValue(apMsg.shpgSvcLvlCd, sMsg.shpgSvcLvlCd_SE);

            // Carrier Code (Requested Carrier)
            ZYPEZDItemValueSetter.setValue(apMsg.carrCd, sMsg.carrCd_AC);
            // Source Type
            ZYPEZDItemValueSetter.setValue(apMsg.procrTpCd, asMsg.procrTpCd_SE);
            if (PRCH_REQ_REC_TP.TECH_REQUEST.equals(sMsg.prchReqRecTpCd_H2.getValue())) {
                // Destination
                apMsg.destInvtyLocCd.setValue(NPAL1080CommonLogic.joinRtlWhCdAndSwhCd(sMsg.destRtlWhCd_H1, sMsg.destRtlSwhCd_H1));
                // Souce
                apMsg.srcInvtyLocCd.setValue(NPAL1080CommonLogic.joinRtlWhCdAndSwhCd(asMsg.prntVndCd_D1, asMsg.vndCd_D1));
                if (PROCR_TP.SUPPLIER.equals(asMsg.procrTpCd_SE.getValue())) {
                    // WH / Supplier
                    ZYPEZDItemValueSetter.setValue(apMsg.prntVndCd, asMsg.prntVndCd_D1);
                    // SWH / Site
                    ZYPEZDItemValueSetter.setValue(apMsg.vndCd, asMsg.vndCd_D1);
                }
            } else if (PRCH_REQ_REC_TP.TECH_RETURN.equals(sMsg.prchReqRecTpCd_H2.getValue())) {
                // Destination
                apMsg.destInvtyLocCd.setValue(NPAL1080CommonLogic.joinRtlWhCdAndSwhCd(asMsg.prntVndCd_D1, asMsg.vndCd_D1));
                // Souce
                apMsg.srcInvtyLocCd.setValue(NPAL1080CommonLogic.joinRtlWhCdAndSwhCd(sMsg.destRtlWhCd_H1, sMsg.destRtlSwhCd_H1));
                // Pro Num
                ZYPEZDItemValueSetter.setValue(apMsg.proNum, asMsg.proNum_D1);
            }
            // Item Number
            ZYPEZDItemValueSetter.setValue(apMsg.mdseCd, asMsg.mdseCd_D1);
            // Req Qty
            ZYPEZDItemValueSetter.setValue(apMsg.prchReqQty, asMsg.prchReqQty_D1);
            // Line Comment
            ZYPEZDItemValueSetter.setValue(apMsg.prchReqLineCmntTxt, asMsg.prchReqLineCmntTxt_D1);
            if (i == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.prchGrpCd, getPrchGrpCd(asMsg.mdseCd_D1.getValue()));
            }

            // QC#26662 Update.
            if (ZYPConstant.FLG_ON_Y.equals(asMsg.prchReqFrzFlg_D1.getValue())) {
                ZYPEZDItemValueSetter.setValue(apMsg.prchReqFrzFlg, asMsg.prchReqFrzFlg_D1);
            }

            //QC#55615
            if (ZYPCommonFunc.hasValue(asMsg.poSchdRelDt_D1)) {
                ZYPEZDItemValueSetter.setValue(apMsg.poSchdRelDt, asMsg.poSchdRelDt_D1);
            }

            pMsgCount++;

        }
        pMsg.prchReqInfo.setValidCount(pMsgCount);
        return pMsg;
    }

    /**
     * Creates new pMsg for NPZC103001 event=Freeze / UnFreeze
     * @param cMsg
     * @param sMsg
     * @return
     */
    private NPZC103001PMsg createNPZC103001PMsgForFreeze(NPAL1080SMsg sMsg) {
        NPZC103001PMsg pMsg = new NPZC103001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC103001Constant.MODE_UPDATE);
        ZYPEZDItemValueSetter.setValue(pMsg.eventId, NPZC103001Constant.EVENT_HOLD);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());

        //QC#52445 Mod
//        ZYPEZDItemValueSetter.setValue(pMsg.prchReqNum, sMsg.prchReqNum_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqNum, sMsg.prchReqNum_HD);

        ZYPEZDItemValueSetter.setValue(pMsg.procDt, ZYPDateUtil.getSalesDate());

        boolean isChecked = false;
        String setValue = ZYPConstant.FLG_OFF_N;
        int pMsgCount = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (isParentDetail(sMsg.A.no(i))) {
                if (NPAL1080CommonLogic.isChecked(sMsg.A.no(i))) {
                    isChecked = true;
                    if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).prchReqFrzFlg_D1.getValue())) {
                        setValue = ZYPConstant.FLG_OFF_N;
                    } else {
                        setValue = ZYPConstant.FLG_ON_Y;
                    }
                } else {
                    isChecked = false;
                }
            }
            if (isChecked) {
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(pMsgCount).prchReqLineNum, sMsg.A.no(i).prchReqLineNum_D1);
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(pMsgCount).prchReqLineSubNum, sMsg.A.no(i).prchReqLineSubNum_D1);
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(pMsgCount).prchReqFrzFlg, setValue);
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(pMsgCount).prchReqLineCmntTxt, sMsg.A.no(i).prchReqLineCmntTxt_D1);
                pMsgCount++;
            }
        }
        pMsg.prchReqInfo.setValidCount(pMsgCount);
        return pMsg;
    }

    /**
     * Creates new pMsg for NPZC103001 event=Freeze / UnFreeze
     * @param cMsg
     * @param sMsg
     * @return
     */
    private NPZC103001PMsg createNPZC103001PMsgForLineCancel(NPAL1080SMsg sMsg) {
        NPZC103001PMsg pMsg = new NPZC103001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC103001Constant.MODE_CANCEL);
        ZYPEZDItemValueSetter.setValue(pMsg.eventId, NPZC103001Constant.EVENT_SUBMIT);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());

        // QC#52445 Mod
//        ZYPEZDItemValueSetter.setValue(pMsg.prchReqNum, sMsg.prchReqNum_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqNum, sMsg.prchReqNum_HD);

        ZYPEZDItemValueSetter.setValue(pMsg.procDt, ZYPDateUtil.getSalesDate());

        boolean isChecked = false;
        int pMsgCount = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (isParentDetail(sMsg.A.no(i))) {
                if (NPAL1080CommonLogic.isChecked(sMsg.A.no(i))) {
                    isChecked = true;
                } else {
                    isChecked = false;
                }
            }
            if (isChecked) {
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(pMsgCount).prchReqLineNum, sMsg.A.no(i).prchReqLineNum_D1);
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(pMsgCount).prchReqLineSubNum, sMsg.A.no(i).prchReqLineSubNum_D1);
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(pMsgCount).prchReqLineCmntTxt, sMsg.A.no(i).prchReqLineCmntTxt_D1);
                // QC#54672
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(pMsgCount).shipQty, sMsg.A.no(i).shipQty_D1);
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(pMsgCount).rwsPutAwayQty, sMsg.A.no(i).rwsPutAwayQty_D1);
                pMsgCount++;
            }
        }
        pMsg.prchReqInfo.setValidCount(pMsgCount);
        return pMsg;
    }

    /**
     * <pre>
  * Execute PR Update Api
  * </pre>
     * @param pMsg NPZC103001PMsg
     */
    private boolean executeNPZC1030(NPAL1080CMsg cMsg, NPZC103001PMsg pMsg) {
        NPZC103001 dPZC103001 = new NPZC103001();
        dPZC103001.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (pMsg.xxMsgIdList.getValidCount() != 0) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                String messageId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                cMsg.setMessageInfo(messageId);
            }
            return false;
        }
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum_H1, pMsg.prchReqNum);
        return true;
    }

    /**
     * @param msg
     * @return true if given ACMsg is not saved on DB.
     */
    private boolean isNewLine(NPAL1080_ASMsg msg) {
        // Mod Start 2020/03/05 QC#56103
//        return !ZYPCommonFunc.hasValue(msg.prchReqLineStsCd_D1);
        String prchReqNum = msg.prchReqNum_D1.getValue();
        String prchReqLineNum = msg.prchReqLineNum_D1.getValue();
        BigDecimal prchReqLineSubNum = msg.prchReqLineSubNum_D1.getValue();
        PRCH_REQ_DTLTMsg dtl = NPAL1080Query.getInstance().getPrchReqDtl(getGlobalCompanyCode(), prchReqNum, prchReqLineNum, prchReqLineSubNum);

        if (null != dtl && ZYPCommonFunc.hasValue(dtl.prchReqNum)) {
            return false;
        } else {
            return true;
        }
        // Mod End 2020/03/05 QC#56103
    }

    // QC#52436 Add
    /**
     * 
     * @param msg
     * @return
     */
    private boolean isNewLine(NPAL1080_ACMsg msg) {
        return !ZYPCommonFunc.hasValue(msg.prchReqLineStsCd_D1);
    }

    /**
     * If lock cannot acquired successfully, set error message and
     * then returns false.
     * @param bizMsg
     * @param sMsg
     * @param tMsg
     * @return
     */
    private boolean tryLock(NPAL1080CMsg bizMsg, NPAL1080SMsg sMsg, PRCH_REQTMsg tMsg) {
        if (isSameTimeStamp(sMsg, tMsg)) {
            return true;
        } else {
            bizMsg.setMessageInfo(NPAM0006E);
            return false;
        }
    }

    /**
     * @param sMsg
     * @param tMsg
     * @return returns true if timestamps are the same.
     */
    private boolean isSameTimeStamp(NPAL1080SMsg sMsg, PRCH_REQTMsg tMsg) {
        if (tMsg == null) {
            return false;
        }

        String preUpTime = sMsg.xxRqstTs_H1.getValue();
        String preTimeZone = sMsg.xxRqstTz_H1.getValue();
        String currUpTime = tMsg.ezUpTime.getValue();
        String currTimeZone = tMsg.ezUpTimeZone.getValue();

        return ZYPDateUtil.isSameTimeStamp(preUpTime, preTimeZone, currUpTime, currTimeZone);
    }

    /**
     * Note: record is supposed to be present. Even when record is not
     * found, it sets lock error message.
     * @param bizMsg
     * @param sMsg
     * @return tMsg if lock is successfully acquired.
     */
    private PRCH_REQTMsg lockPR(NPAL1080CMsg bizMsg, NPAL1080SMsg sMsg) {
        PRCH_REQTMsg tMsg = findPR(bizMsg);
        if (tMsg == null) {
            sMsg.prchReqNum_H1.setValue(sMsg.prchReqNum_HD.getValue());
            bizMsg.setMessageInfo(NPAM0008E);
            return null;
        }

        if (tryLock(bizMsg, sMsg, tMsg)) {
            return tMsg;
        } else {
            sMsg.prchReqNum_H1.setValue(sMsg.prchReqNum_HD.getValue());
            return null;
        }
    }

    /**
     * Find PR by primary key, for update.
     * @param bizMsg
     * @return
     */
    private PRCH_REQTMsg findPR(NPAL1080CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.prchReqNum_HD)) {
            return null;
        }

        PRCH_REQTMsg inMsg = new PRCH_REQTMsg();
        setValue(inMsg.prchReqNum, bizMsg.prchReqNum_HD);
        setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());

        PRCH_REQTMsg outMsg = (PRCH_REQTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);
        return outMsg;
    }

    private static boolean isParentDetail(NPAL1080_ASMsg aMsg) {
        return BigDecimal.ZERO.equals(aMsg.prchReqLineSubNum_D1.getValue());
    }

    private String getPrchGrpCd(String mdseCd) {
        MDSETMsg tMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseCd);

        MDSETMsg outMsg = (MDSETMsg) EZDTBLAccessor.findByKey(tMsg);
        if (outMsg != null) {
            return outMsg.prchGrpCd.getValue();
        }
        return null;
    }

    private boolean inputPageCheck(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg, String glblCmpyCd, String[] materialParts, boolean showWarning) {
        boolean ret = true;

        String salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);

        // 2019/02/14 QC#30358 Add Start
        boolean isSubmittedRequest = S21StringUtil.isEquals(RUN_MODE_SUBMITTED, cMsg.xxEdtModeFlg.getValue());
        // 2019/02/14 QC#30358 Add End

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {

            // QC#26662 Update.
            if (isInsourcedLine(cMsg.prchReqNum_H1.getValue(), cMsg.A.no(i).prchReqLineNum_D1.getValue(), cMsg.A.no(i).prchReqLineSubNum_D1.getValue()) //
                    || PRCH_REQ_LINE_STS.CANCELLED.equals(cMsg.A.no(i).prchReqLineStsCd_D1.getValue())//
                    || PRCH_REQ_LINE_STS.CLOSED.equals(cMsg.A.no(i).prchReqLineStsCd_D1.getValue())) {
                // Not need check line.
                continue;
            }

            // 2019/02/14 QC#30358 Add Start
            boolean isProtectedLine = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, cMsg.A.no(i).submtFlg_D1.getValue());
            if (isSubmittedRequest && isProtectedLine) {
                continue;
            }
            // 2019/02/14 QC#30358 Add End
            if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).mdseCd_D1)) {
                cMsg.A.no(i).mdseCd_D1.setErrorInfo(1, ZZM9000E, new String[] {"Item Number" });
                ret = false;
            }
            if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).prchReqQty_D1)) {
                cMsg.A.no(i).prchReqQty_D1.setErrorInfo(1, ZZM9000E, new String[] {"Req Qty" });
                ret = false;
            }
            if (PRCH_REQ_REC_TP.TECH_RETURN.equals(getPrchReqRecTpCdFromReqTpCd(sMsg, cMsg.prchReqTpCd_SE.getValue()))) {
                if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).prchReqLineTpCd_SE)) {
                    cMsg.A.no(i).prchReqLineTpCd_SE.setErrorInfo(1, NMAM0836E, new String[] {"Line Type" });
                    ret = false;
                }
                if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).prntVndNm_D1)) {
                    cMsg.A.no(i).prntVndNm_D1.setErrorInfo(1, NMAM0836E, new String[] {"WH/Supplier Name" });
                    ret = false;
                }
                if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).locNm_D1)) {
                    cMsg.A.no(i).locNm_D1.setErrorInfo(1, NMAM0836E, new String[] {"SWH/Site Name" });
                    ret = false;
                }
                // START 2017/10/25 S.Katsuma QC#21896 ADD
                if (sMsg.destRtlSwhCd_H1.getValue().equals(NPAL1080Constant.TECH_SWH_CD_G)) {
                    if (!PRCH_REQ_LINE_TP.LOCAL_RETURN.equals(cMsg.A.no(i).prchReqLineTpCd_SE.getValue()) && !PRCH_REQ_LINE_TP.DEFECTIVE_RETURN.equals(cMsg.A.no(i).prchReqLineTpCd_SE.getValue())) {
                        sMsg.destRtlSwhCd_H1.setErrorInfo(1, NPAM1606E, new String[] {sMsg.destRtlSwhCd_H1.getValue() });
                        sMsg.A.no(i).prchReqLineTpCd_SE.setErrorInfo(1, NPAM1606E, new String[] {sMsg.A.no(i).prchReqLineTpCd_SE.getValue() });
                        cMsg.setMessageInfo(NPAM1606E, new String[] {sMsg.A.no(i).prchReqLineTpCd_SE.getValue() });
                        ret = false;
                    }
                } else if (sMsg.destRtlSwhCd_H1.getValue().equals(NPAL1080Constant.TECH_SWH_CD_R)) {
                    if (!PRCH_REQ_LINE_TP.USED_LOCAL_RETURN.equals(cMsg.A.no(i).prchReqLineTpCd_SE.getValue())) {
                        sMsg.destRtlSwhCd_H1.setErrorInfo(1, NPAM1606E, new String[] {sMsg.destRtlSwhCd_H1.getValue() });
                        sMsg.A.no(i).prchReqLineTpCd_SE.setErrorInfo(1, NPAM1606E, new String[] {sMsg.A.no(i).prchReqLineTpCd_SE.getValue() });
                        cMsg.setMessageInfo(NPAM1606E, new String[] {sMsg.A.no(i).prchReqLineTpCd_SE.getValue() });
                        ret = false;
                    }
                }
                if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).procrTpCd_SE)) {
                    cMsg.A.no(i).procrTpCd_SE.setErrorInfo(1, NMAM0836E, new String[] {"Source Type" });
                    ret = false;
                }
                if (!NPAL1080CommonLogic.getItemName(cMsg, sMsg, getGlobalCompanyCode(), i)) {
                    ret = false;
                }
                // END 2017/10/25 S.Katsuma QC#21896 ADD
                // START 2017/08/22 QC#19966 Add.
                if (!NPAL1080CommonLogic.checkTechRtnSWH(cMsg, getGlobalCompanyCode(), i)) {
                    ret = false;
                }
                // START 2018/03/13 S.Katsuma [SOL#118(QC#12110),ADD]
                if (!NPAL1080CommonLogic.checkPrtItemTpCdTool(cMsg, getGlobalCompanyCode(), i)) {
                    cMsg.A.no(i).mdseCd_D1.setErrorInfo(1, NPAM1616E, null);
                    ret = false;
                }
                // END 2018/03/13 S.Katsuma [SOL#118(QC#12110),ADD]

                // START 2022/06/09 A.Cullano [QC#60154, ADD]
                if (!NPAL1080CommonLogic.chkRtlSwhCdAndDestSwhCd(getGlobalCompanyCode(), sMsg.destRtlSwhCd_H1.getValue(), cMsg.A.no(i).locNm_D1.getValue())) {
                    // Add an error message in the SWH / Site Column
                    cMsg.A.no(i).locNm_D1.setErrorInfo(1, NPAM1606E);
                    ret = false;
                }
                // END 2022/06/09 A.Cullano [QC#60154, ADD]
            } else {
                if (PROCR_TP.WAREHOUSE.equals(cMsg.A.no(i).procrTpCd_SE.getValue())) {
                    if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).prntVndNm_D1)) {
                        cMsg.A.no(i).prntVndNm_D1.setErrorInfo(1, NMAM0836E, new String[] {"WH Name" });
                        ret = false;
                    }
                    if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).locNm_D1)) {
                        cMsg.A.no(i).locNm_D1.setErrorInfo(1, NMAM0836E, new String[] {"SWH Name" });
                        ret = false;
                    }
                }
                if (PROCR_TP.SUPPLIER.equals(cMsg.A.no(i).procrTpCd_SE.getValue())) {
                    if (ZYPCommonFunc.hasValue(cMsg.A.no(i).prntVndNm_D1) && (!ZYPCommonFunc.hasValue(cMsg.A.no(i).locNm_D1))) {
                        cMsg.A.no(i).locNm_D1.setErrorInfo(1, NLAM0173E, new String[] {"Site Name" });
                        ret = false;
                    }
                    if ((!ZYPCommonFunc.hasValue(cMsg.A.no(i).prntVndNm_D1)) && ZYPCommonFunc.hasValue(cMsg.A.no(i).locNm_D1)) {
                        cMsg.A.no(i).prntVndNm_D1.setErrorInfo(1, NLAM0173E, new String[] {"Supplier Name" });
                        ret = false;
                    }
                    if ((!ZYPCommonFunc.hasValue(cMsg.A.no(i).prntVndNm_D1)) && (!ZYPCommonFunc.hasValue(cMsg.A.no(i).locNm_D1))) {
                        cMsg.A.no(i).prntVndCd_D1.clear();
                    }
                }
            }
        }
        if (!ret) {
            return ret;
        }

        Boolean[] hazMatItems = new Boolean[cMsg.A.getValidCount()];
        // START 2018/12/05 M.Naito [QC#25900,ADD]
        Boolean[] toolItems = new Boolean[cMsg.A.getValidCount()];
        // END 2018/12/05 M.Naito [QC#25900,ADD]
        //Warning control. Show warning only when first submit.

        String prchReqRecTpCd = getPrchReqRecTpCdFromReqTpCd(sMsg, cMsg.prchReqTpCd_SE.getValue());
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            NPAL1080_ACMsg line = cMsg.A.no(i);
            // START 2018/12/05 M.Naito [QC#25900,ADD]
            // QC#31164
            if (isInsourcedLine(cMsg.prchReqNum_H1.getValue()
                    ,line.prchReqLineNum_D1.getValue()//
                    ,line.prchReqLineSubNum_D1.getValue())//
                    || PRCH_REQ_LINE_STS.CANCELLED.equals(line.prchReqLineStsCd_D1.getValue())//
                    || PRCH_REQ_LINE_STS.CLOSED.equals(line.prchReqLineStsCd_D1.getValue())) {
                // Not need check line.
                continue;
            }
            // END 2018/12/05 M.Naito [QC#25900,ADD]
            // 2019/02/14 QC#30358 Add Start
            boolean isProtectedLine = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, cMsg.A.no(i).submtFlg_D1.getValue());
            if (isSubmittedRequest && isProtectedLine) {
                continue;
            }
            // 2019/02/14 QC#30358 Add End
            // Check Req Qty
            if (BigDecimal.ZERO.equals(line.prchReqQty_D1.getValue())) {
                ret = false;
                line.prchReqQty_D1.setErrorInfo(1, NPAM0046E, new String[] {"Req Qty" });
            }
            // Check Item number
            if (!checkItemNumber(line.mdseCd_D1.getValue(), glblCmpyCd)) {
                ret = false;
                line.mdseCd_D1.setErrorInfo(1, NPAM1361E, new String[] {"Item#" });
            }
            // Check Hazmat/Non-Hazmat items
            hazMatItems[i] = checkHazMat(line.mdseCd_D1.getValue(), glblCmpyCd);
            // 
            // START 2018/12/05 M.Naito [QC#25900,ADD]
            // Check Tool/Non-Tool items
            toolItems[i] = NPAL1080CommonLogic.checkPrtItemTpCdTool(cMsg, glblCmpyCd, i);
            // END 2018/12/05 M.Naito [QC#25900,ADD]

            Map<String, Object> item = NPAL1080CommonLogic.getItem(glblCmpyCd, line.mdseCd_D1.getValue());
            if (item == null) {
                sMsg.A.no(i).mdseCd_D1.setErrorInfo(1, NPAM0076E, new String[] {sMsg.A.no(i).mdseCd_D1.getValue() });
                cMsg.setMessageInfo(NPAM0076E, new String[] {"Item#" });
                ret = false;
            } else if (showWarning) {
                // Check item PRCH_AVAL_FLG only when first submit.
                Boolean prchAvalFlg = NPAL1080CommonLogic.checkPrchAvalFlg(item);
                if (prchAvalFlg==Boolean.FALSE) {
                    sMsg.A.no(i).mdseCd_D1.setErrorInfo(2, NPAM1650W);
                    cMsg.A.no(i).mdseCd_D1.setErrorInfo(2, NPAM1650W);
                    cMsg.setMessageInfo(NPAM1650W);
                    ZYPEZDItemValueSetter.setValue(sMsg.xxBtnFlg_H1, ZYPConstant.FLG_ON_1);
                    ret = false;
                }
            }

            if (PROCR_TP.SUPPLIER.equals(line.procrTpCd_SE.getValue())) {
                if (ZYPCommonFunc.hasValue(line.prntVndNm_D1)) {
                    // chk Supplier
                    String vndCd = findVndCd(line.prntVndNm_D1.getValue(), glblCmpyCd);
                    if (vndCd != null) {
                        ZYPEZDItemValueSetter.setValue(line.prntVndCd_D1, vndCd);
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(getSMsgIndexFromCMsg(cMsg, i)).prntVndCd_D1, vndCd);
                    } else {
                        ret = false;
                        line.prntVndNm_D1.setErrorInfo(1, NPAM1361E, new String[] {"Supplier" });
                        line.prntVndCd_D1.clear();
                    }
                    // chk site
                    String siteCd = findSiteCd(vndCd, line.locNm_D1.getValue(), glblCmpyCd);
                    if (siteCd != null) {
                        ZYPEZDItemValueSetter.setValue(line.vndCd_D1, siteCd);
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(getSMsgIndexFromCMsg(cMsg, i)).vndCd_D1, siteCd);
                    } else {
                        ret = false;
                        line.locNm_D1.setErrorInfo(1, NPAM1361E, new String[] {"Site" });
                    }
                    if ((vndCd != null) && (siteCd != null)) {
                        if (!checkPoVnd(vndCd, siteCd, glblCmpyCd)) {
                            ret = false;
                            line.prntVndNm_D1.setErrorInfo(1, NPAM1363E, new String[] {"Supplier", "Site" });
                            line.locNm_D1.setErrorInfo(1, NPAM1363E, new String[] {"Supplier", "Site" });
                            line.prntVndCd_D1.clear();
                        }
                    }
                }
            } else if (PROCR_TP.WAREHOUSE.equals(line.procrTpCd_SE.getValue())) {
                // chk WH
                String whCd = findWhCd(line.prntVndNm_D1.getValue(), glblCmpyCd);
                if (whCd != null) {
                    ZYPEZDItemValueSetter.setValue(line.prntVndCd_D1, whCd);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(getSMsgIndexFromCMsg(cMsg, i)).prntVndCd_D1, whCd);
                } else {
                    ret = false;
                    line.prntVndNm_D1.setErrorInfo(1, NPAM1361E, new String[] {"Warehouse" });
                    line.prntVndCd_D1.clear();
                }
                // chk SWH
                String swhCd = findSwhCd(whCd, line.locNm_D1.getValue(), glblCmpyCd);
                if (swhCd != null) {
                    ZYPEZDItemValueSetter.setValue(line.vndCd_D1, swhCd);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(getSMsgIndexFromCMsg(cMsg, i)).vndCd_D1, swhCd);
                } else {
                    ret = false;
                    line.locNm_D1.setErrorInfo(1, NPAM1361E, new String[] {"Sub Warehouse" });
                }
                if ((whCd != null) && (swhCd != null)) {
                    NMXC100001EnableWHData result = NMXC100001EnableWH.checkEnableWH(glblCmpyCd, whCd + swhCd, BUSINESS_APPLICATION_ID, null, ZYPConstant.FLG_OFF_N, prchReqRecTpCd);
                    if (result.getXxMsgId() != null) {
                        ret = false;
                        line.prntVndNm_D1.setErrorInfo(1, NPAM1368E, new String[] {"Warehouse with Tech Request" });
                        line.locNm_D1.setErrorInfo(1, NPAM1368E, new String[] {"Warehouse with Tech Request" });
                        line.prntVndCd_D1.clear();
                    }
                }
            }

            // QC#8195 ItemNumber Check ASL SulpilerItem
            if (materialParts != null) {

                // QC#24918
                if (Arrays.asList(materialParts).contains(line.mdseCd_D1.getValue())) {

                    if (!PROCR_TP.SUPPLIER.equals(line.procrTpCd_SE.getValue())) {
                        ret = false;
                        line.procrTpCd_SE.setErrorInfo(1, NPAM1329E, new String[] {"Source Type", "Supplier" });
                    }

                    if (!ZYPCommonFunc.hasValue(line.prchReqLineCmntTxt_D1)) {
                        ret = false;
                        line.prchReqLineCmntTxt_D1.setErrorInfo(1, NPAM1579E, new String[] {"Line Comment" });
                    }

                    String splyItemNum = getSplyItemNumFromAsl(glblCmpyCd, salesDate, line.mdseCd_D1.getValue(), line.vndCd_D1.getValue());

                    if (!ZYPCommonFunc.hasValue(splyItemNum)) {
                        ret = false;
                        line.mdseCd_D1.setErrorInfo(1, NPZM0272E, null);
                        line.prntVndNm_D1.setErrorInfo(1, NPZM0272E, null);
                        line.locNm_D1.setErrorInfo(1, NPZM0272E, null);
                    }
                }
            }
        }

        List<Boolean> hazMatItemList = Arrays.asList(hazMatItems);
        if (hazMatItemList.contains(Boolean.TRUE) && hazMatItemList.contains(Boolean.FALSE)) {
            // Error: Hazmat item(s) are specified with Non-Hazmat
            // item(s). Hazmat/Non-Hazmat must be ordered separately
            // each other.
            for (int i = 0; i < cMsg.A.getValidCount(); ++i) {
                // START 2018/12/05 M.Naito [QC#25900,MOD]
//                if (hazMatItemList.get(i)) {
                if (hazMatItemList.get(i) != null && hazMatItemList.get(i)) {
                // END 2018/12/05 M.Naito [QC#25900,MOD]
                    cMsg.A.no(i).mdseCd_D1.setErrorInfo(1, NPAM1619E);
                }
            }
            ret = false;
        }

        // START 2018/12/05 M.Naito [QC#25900,ADD]
        List<Boolean> toolItemList = Arrays.asList(toolItems);
        if (toolItemList.contains(Boolean.TRUE) && toolItemList.contains(Boolean.FALSE)) {
            for (int i = 0; i < cMsg.A.getValidCount(); ++i) {
                if (toolItemList.get(i) != null && !toolItemList.get(i)) {
                    cMsg.A.no(i).mdseCd_D1.setErrorInfo(1, NPAM1638E);
                }
            }
            ret = false;
        }
        // END 2018/12/05 M.Naito [QC#25900,ADD]

        // START 2023/08/03 T.Kuroda [QC#61648, ADD]
        if (sMsg.prchReqTpCd_SE.getValue().equals(PRCH_REQ_TP.PREMIUM_RUSH)
                && (sMsg.shpgSvcLvlCd_SE.getValue().equals(SHPG_SVC_LVL.CUSTOMER_PICK_UP)
                        || sMsg.shpgSvcLvlCd_SE.getValue().equals(SHPG_SVC_LVL.SCHD_DELIVERY))) {
            for (int i = 0; i < cMsg.A.getValidCount(); ++i) {
                BigDecimal reqQty = cMsg.A.no(i).prchReqQty_D1.getValue();
                String invtyLocCd = NPAL1080Query.getInstance().getInvtyLocCd(
                        glblCmpyCd, cMsg.destRtlWhCd_H1.getValue(), ZYPDateUtil.getSalesDate());

                BigDecimal openTrOrdQty = null;
                BigDecimal avaQty = null;

                if (ZYPCommonFunc.hasValue(invtyLocCd)) {
                    openTrOrdQty = NPAL1080Query.getInstance().getOpenTrOrdQty(
                            glblCmpyCd, cMsg.A.no(i).mdseCd_D1.getValue(), invtyLocCd);
                    avaQty = NPAL1080Query.getInstance().getAvaQty(
                            glblCmpyCd, cMsg.A.no(i).mdseCd_D1.getValue(), invtyLocCd);
                }

                if (!ZYPCommonFunc.hasValue(openTrOrdQty) || !ZYPCommonFunc.hasValue(avaQty)
                        || reqQty.add(openTrOrdQty).compareTo(avaQty) == 1) {
                    cMsg.A.no(i).xxChkBox_D1.setErrorInfo(1, NPAM1660E);
                    ret = false;
                    break;
                }
            }
        }
        // END 2023/08/03 T.Kuroda [QC#61648, ADD]

        return ret;
    }

    private String getPrchReqRecTpCdFromReqTpCd(NPAL1080SMsg sMsg, String prchReqTpCd) {
        for (int i = 0; i < sMsg.X.getValidCount(); i++) {
            if (prchReqTpCd.equals(sMsg.X.no(i).prchReqTpCd_X1.getValue())) {
                return sMsg.X.no(i).prchReqRecTpCd_X1.getValue();
            }
        }
        return null;
    }

    /**
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     * @param glblCmpyCd String
     * @param materialParts String[]
     * @return false for error
     */
    private boolean checkForSaveSubmit(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg, String glblCmpyCd, String[] materialParts) {
        boolean bRet = true;
        // Header check
        if (ZYPCommonFunc.hasValue(cMsg.delyAddlCmntTxt_H2)) {
            if (COMNT_LENGTH < cMsg.delyAddlCmntTxt_H2.getValue().length()) {
                cMsg.delyAddlCmntTxt_H2.setErrorInfo(1, ZZM9001E, new String[] {"Shipping Instructions" });
                cMsg.xxDplyTab.setValue(TAB_HEADER);
                return false;
            }
        }
        // Location Security Check
        if (!checkInvtyLocCdIsEnable(cMsg, glblCmpyCd)) {
            return false;
        }
        // Technician Location Check
        if (!checkTechnicianocation(cMsg, glblCmpyCd)) {
            bRet = false;
        }

        // Carrier
        if (ZYPCommonFunc.hasValue(cMsg.carrNm_H1)) {
            if (!checkCarrier(cMsg, sMsg, glblCmpyCd)) {
                bRet = false;
            }
        } else {
            cMsg.carrCd_AC.clear();
        }

        if (cMsg.A.getValidCount() == 0) {
            cMsg.setMessageInfo(NPAM1360E);
            return false;
        }

        if (ZYPCommonFunc.hasValue(cMsg.carrNm_H1) && ZYPCommonFunc.hasValue(cMsg.shpgSvcLvlCd_SE)) {
            if (!checkShippingServiceLevel(cMsg, sMsg, glblCmpyCd)) {
                bRet = false;
            }
        }

        // START 2017/08/31 K.Kameoka [Sol#369(QC#19243),ADD]
        // PR Type and LOB combination check
        if (ZYPCommonFunc.hasValue(cMsg.prchReqTpCd_SE) && ZYPCommonFunc.hasValue(cMsg.destRtlWhCd_H1)) {
            String insrcCtrl = getInsrcCtrlInfo(glblCmpyCd, cMsg.destRtlWhCd_H1.getValue(), cMsg.prchReqTpCd_SE.getValue(), cMsg.shpgSvcLvlCd_SE.getValue());
            if (insrcCtrl != null && insrcCtrl.equals((ZYPConstant.FLG_OFF_N))) {
                cMsg.prchReqTpCd_SE.setErrorInfo(1, NPAM0088E, new String[] {"Tech Request Type", "Tech WH / SWH" });
                return false;
            }
        }
        // END 2017/08/31 K.Kameoka [Sol#369(QC#19243),ADD]

        // QC#21909
        // Shipping Service Level Validation for ESS Tech
        if (ZYPCommonFunc.hasValue(cMsg.shpgSvcLvlCd_SE) //
                && ZYPCommonFunc.hasValue(cMsg.rqstTechTocCd_H1) //
                && SHPG_SVC_LVL.ASAP.equals(cMsg.shpgSvcLvlCd_SE.getValue())) {

            // ESS Tech Check
            String lineBizTpCd = getTechnLineBizType(cMsg.rqstTechTocCd_H1.getValue(), glblCmpyCd);

            if (ZYPCommonFunc.hasValue(lineBizTpCd) && "ESS".equals(lineBizTpCd)) {
                cMsg.shpgSvcLvlCd_SE.setErrorInfo(1, NPAM1614E, null);
                cMsg.rqstTechTocCd_AC.setErrorInfo(1, NPAM1614E, null);
                return false;
            }
        }

        // Date taime check.
        if (ZYPCommonFunc.hasValue(sMsg.prchReqTpCd_SE) && ZYPCommonFunc.hasValue(sMsg.shpgSvcLvlCd_SE) //
                && PRCH_REQ_TP.PREMIUM_RUSH.equals(sMsg.prchReqTpCd_SE.getValue()) //
                && SHPG_SVC_LVL.SCHD_DELIVERY.equals(sMsg.shpgSvcLvlCd_SE.getValue())) {

            String postCd = "";
            String ctryCd = "";
            Map<String, Object> locInfo =  NPAL1080CommonLogic.getTechLocation(glblCmpyCd, sMsg.destRtlWhCd_H1.getValue());
            if (locInfo != null) {
                postCd = (String) locInfo.get("POST_CD");
                ctryCd = (String) locInfo.get("CTRY_CD");
            }

            String schdDelyTm = NPAL1080CommonLogic.getAllDayTimes(sMsg.xxDtTm_H1.getValue(), sMsg.rqstRcvDtTxt_SL.getValue());
            String dateTm = sMsg.rqstRcvDt_H1.getValue() + schdDelyTm;

            SvcTimeZoneInfo timeInfo = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, dateTm, ctryCd, postCd);

            String dateTime = timeInfo.getDateTime().substring(0, NPAL1080Constant.HALF_DAY_HOURS);

            String currentTime = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmm");
            SvcTimeZoneInfo lclTimeInfo = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, currentTime, ctryCd, postCd);
            String lclDateTime = lclTimeInfo.getDateTime().substring(0, NPAL1080Constant.HALF_DAY_HOURS);

            if (lclDateTime.compareToIgnoreCase(dateTime) > 0) {
                cMsg.rqstRcvDt_H1.setErrorInfo(1, "NPAM1230E", new String[]{"Date & Time Needed"});
                cMsg.xxDtTm_H1.setErrorInfo(1, "NPAM1230E", new String[]{"Date & Time Needed"});
                cMsg.rqstRcvDtTxt_SL.setErrorInfo(1, "NPAM1230E", new String[]{"Date & Time Needed"});
                return false;
            }
        }

        String screenAplID = cMsg.getScreenAplID();
        boolean isSubmitting = "NPAL1080Scrn00_CMN_Submit".equals(screenAplID);
        boolean showWarning = isSubmitting;
        if (ZYPCommonFunc.hasValue(sMsg.xxBtnFlg_H1.getValue()) && ZYPConstant.FLG_ON_1.equals(sMsg.xxBtnFlg_H1.getValue())) {
            showWarning = false;
        }

        // check detail
        cMsg.xxPageShowFromNum_D2.setValue(1);
        while (true) {
            NPAL1080CommonLogic.setPagePos(cMsg, sMsg);
            ZYPTableUtil.clear(cMsg.A);
            int cMsgCount = 0;
            for (int i = cMsg.xxPageShowFromNum_D2.getValueInt() - 1; i < cMsg.xxPageShowToNum_D2.getValueInt(); i++) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(cMsgCount), null);
                cMsgCount++;
            }
            cMsg.A.setValidCount(cMsgCount);
            if (!inputPageCheck(cMsg, sMsg, glblCmpyCd, materialParts, showWarning)) {
                bRet = false;
                break;
            }
            if (cMsg.xxPageShowToNum_D2.getValue().compareTo(cMsg.xxPageShowOfNum_D2.getValue()) == 0) {
                break;
            }
            cMsg.xxPageShowFromNum_D2.setValue(cMsg.xxPageShowFromNum_D2.getValueInt() + cMsg.A.length());
        }

        if (!bRet) {
            cMsg.xxDplyTab.setValue(TAB_DETAIL);
            if("E".equals(cMsg.getMessageKind())) {
                sMsg.xxBtnFlg_H1.clear();
            }
        } else {
            sMsg.xxBtnFlg_H1.clear();
        }

        return bRet;
    }

    private int getSMsgIndexFromCMsg(NPAL1080CMsg cMsg, int index) {
        return cMsg.xxPageShowFromNum_D2.getValueInt() + index - 1;
    }

    /**
     * @param glblCmpyCd
     * @param locTpCds Allowed location types
     * @return false for error
     */
    private boolean checkInvtyLocCdIsEnable(NPAL1080CMsg cMsg, String glblCmpyCd) {
        NMXC100001EnableWHData result = NMXC100001EnableWH.checkEnableWH(glblCmpyCd, joinRtlWhCdAndSwhCd(cMsg.destRtlWhCd_H1, cMsg.destRtlSwhCd_H1), BUSINESS_APPLICATION_ID, null, ZYPConstant.FLG_OFF_N, NMXC100001_BIZ_APP_KEY_ID);
        if (result.getXxMsgId() != null) {
            cMsg.setMessageInfo(result.getXxMsgId());
            return false;
        }
        return true;
    }

    /**
     * @param cMsg
     * @param glblCmpyCd
     * @return false for error
     */
    private boolean checkTechnicianocation(NPAL1080CMsg cMsg, String glblCmpyCd) {
        // Check Technician Location
        if (!NPAL1080CommonLogic.checkWhCode(cMsg, getGlobalCompanyCode())) {
            cMsg.destRtlWhCd_H1.setErrorInfo(1, NPAM0080E, new String[] {cMsg.destRtlWhCd_H1.getValue() });
            return false;
        }

        // Check Technician
        String temp = checkTechnician(cMsg.rqstTechTocCd_H1.getValue(), glblCmpyCd);
        if (temp == null) {
            cMsg.rqstTechTocCd_H1.setErrorInfo(1, NPAM0088E, new String[] {"Technician", "Technician Location" });
            cMsg.techNm_H1.setErrorInfo(1, NPAM0088E, new String[] {"Technician", "Technician Location" });
            cMsg.destRtlWhCd_H1.setErrorInfo(1, NPAM0088E, new String[] {"Technician", "Technician Location" });
            cMsg.destRtlSwhCd_H1.setErrorInfo(1, NPAM0088E, new String[] {"Technician", "Technician Location" });
            return false;
        }

        return true;
    }

    /**
     * @param cMsg
     * @param sMsg
     * @param glblCmpyCd
     * @return false for error
     */
    private boolean checkCarrier(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg, String glblCmpyCd) {
        // Check Carrier
        OTBD_CARR_VTMsg inMsgTechLoc = new OTBD_CARR_VTMsg();
        inMsgTechLoc.setSQLID("005");
        inMsgTechLoc.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsgTechLoc.setConditionValue("carrNm01", cMsg.carrNm_H1.getValue());
        OTBD_CARR_VTMsgArray outMsgsTechLoc = (OTBD_CARR_VTMsgArray) EZDTBLAccessor.findByCondition(inMsgTechLoc);
        if (outMsgsTechLoc.length() == 0) {
            cMsg.carrNm_H1.setErrorInfo(1, NPAM1361E, new String[] {"Carrier" });
            return false;
        } else if (outMsgsTechLoc.length() > 1) {
            if (ZYPCommonFunc.hasValue(sMsg.carrCd_AC)) {
                for (int i = 0; i < outMsgsTechLoc.length(); i++) {
                    if (sMsg.carrCd_AC.getValue().equals(((OTBD_CARR_VTMsg) outMsgsTechLoc.get(i)).carrCd.getValue())) {
                        return true;
                    }
                }
            }
            cMsg.carrNm_H1.setErrorInfo(1, NLBM1341E, new String[] {"Carriers" });
            sMsg.carrCd_AC.clear();
            return false;
        } else {
            sMsg.carrCd_AC.setValue(((OTBD_CARR_VTMsg) outMsgsTechLoc.get(0)).carrCd.getValue());
        }

        return true;
    }

    /**
     * @param cMsg
     * @param glblCmpyCd
     * @return false for error
     */
    private boolean checkShippingServiceLevel(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg, String glblCmpyCd) {
        // Check Carrier
        SHPG_SVC_LVL_CARR_RELNTMsg inMsgTechLoc = new SHPG_SVC_LVL_CARR_RELNTMsg();
        inMsgTechLoc.setSQLID("001");
        inMsgTechLoc.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsgTechLoc.setConditionValue("shpgSvcLvlCd01", sMsg.shpgSvcLvlCd_SE.getValue());
        inMsgTechLoc.setConditionValue("carrCd01", sMsg.carrCd_AC.getValue());
        SHPG_SVC_LVL_CARR_RELNTMsgArray outMsgsTechLoc = (SHPG_SVC_LVL_CARR_RELNTMsgArray) EZDTBLAccessor.findByCondition(inMsgTechLoc);
        if (outMsgsTechLoc.length() == 0) {
            cMsg.shpgSvcLvlCd_SE.setErrorInfo(1, NPAM1365E, new String[] {});
            cMsg.carrCd_AC.setErrorInfo(1, NPAM1365E, new String[] {});
            return false;
        }

        return true;
    }

    /**
     * checkItemNumber
     * @param mdseCd
     * @param glblCmpyCd
     * @return
     */
    private boolean checkItemNumber(String mdseCd, String glblCmpyCd) {
        ALL_MDSE_VTMsg inMsg = new ALL_MDSE_VTMsg();
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mdseCd01", mdseCd);

        ALL_MDSE_VTMsgArray outMsgsTechLoc = (ALL_MDSE_VTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (0 < outMsgsTechLoc.length()) {
            return true;
        }
        return false;
    }

    /**
     * checkHazMat
     * @param mdseCd
     * @param glblCmpyCd
     * @return
     */
    private boolean checkHazMat(String mdseCd, String glblCmpyCd) {
        MDSE_SFTY_INFOTMsg tMsg = new MDSE_SFTY_INFOTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseCd);

        tMsg = (MDSE_SFTY_INFOTMsg) EZDTBLAccessor.findByKey(tMsg);
        if (tMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            return false;
        }

        return ZYPConstant.FLG_ON_Y.equals(tMsg.hazMatFlg.getValue());
    }

    /**
     * @param psnCd String
     * @param glblCmpyCd String
     * @return PSN Code
     */
    private String checkTechnician(String psnCd, String glblCmpyCd) {
        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put("psnCd", psnCd);
        ssmParam.put("effFromDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("effThruDt", ZYPDateUtil.getSalesDate());
        // Execute
        S21SsmEZDResult result = NPAL1080Query.getInstance().checkTechnician(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {
                return (String) resultList.get(i).get("PSN_CD");
            }
        }
        return null;
    }

    /**
     * <pre>
     * Join WH Code and SWH Code
     * </pre>
     * @param rtlWhCdItem EZDCStringItem
     * @param rtlSwhCdItem EZDCStringItem
     * @return WH Code + SWH Code
     */
    private String joinRtlWhCdAndSwhCd(EZDCStringItem rtlWhCdItem, EZDCStringItem rtlSwhCdItem) {
        String rtlWhCd = "";
        String rtlSwhCd = "";

        if (ZYPCommonFunc.hasValue(rtlWhCdItem)) {
            rtlWhCd = rtlWhCdItem.getValue();
        }
        if (ZYPCommonFunc.hasValue(rtlSwhCdItem)) {
            rtlSwhCd = rtlSwhCdItem.getValue();
        }

        return rtlWhCd + rtlSwhCd;
    }

    /**
     * @param prntVndNm String
     * @param glblCmpyCd String
     * @return WH Code
     */
    private String findVndCd(String prntVndNm, String glblCmpyCd) {
        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put("prntVndNm", prntVndNm);
        // Execute
        S21SsmEZDResult result = NPAL1080Query.getInstance().findVndCd(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {
                return (String) resultList.get(i).get("PRNT_VND_CD");
            }
        }
        return null;
    }

    /**
     * @param prntVndCd String
     * @param locNm String
     * @param glblCmpyCd String
     * @return WH Code
     */
    private String findSiteCd(String prntVndCd, String locNm, String glblCmpyCd) {
        if (prntVndCd == null) {
            return null;
        }
        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put("prntVndCd", prntVndCd);
        ssmParam.put("locNm", locNm);
        // Execute
        S21SsmEZDResult result = NPAL1080Query.getInstance().findSiteCd(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {
                return (String) resultList.get(i).get("VND_CD");
            }
        }
        return null;
    }

    /**
     * @param rtlWhNm String
     * @param glblCmpyCd String
     * @return WH Code
     */
    private String findWhCd(String rtlWhNm, String glblCmpyCd) {
        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put("rtlWhNm", rtlWhNm);
        // Execute
        S21SsmEZDResult result = NPAL1080Query.getInstance().findWhCd(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {
                return (String) resultList.get(i).get("RTL_WH_CD");
            }
        }
        return null;
    }

    /**
     * @param rtlWhCd String
     * @param rtlSwhCd String
     * @param glblCmpyCd String
     * @return WH Code
     */
    private String findSwhCd(String rtlWhCd, String rtlSwhCd, String glblCmpyCd) {
        if (rtlWhCd == null) {
            return null;
        }
        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put("rtlWhCd", rtlWhCd);
        ssmParam.put("rtlSwhCd", rtlSwhCd);
        // Execute
        S21SsmEZDResult result = NPAL1080Query.getInstance().findSwhCd(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {
                return (String) resultList.get(i).get("RTL_SWH_CD");
            }
        }
        return null;
    }

    /**
     * @param prntVndCd String
     * @param vndCd String
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean checkPoVnd(String prntVndCd, String vndCd, String glblCmpyCd) {
        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put("prntVndCd", prntVndCd);
        ssmParam.put("vndCd", vndCd);
        // Execute
        S21SsmEZDResult result = NPAL1080Query.getInstance().findPoVnd(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();
            if (0 < resultList.size()) {
                return true;
            }
        }
        return false;
    }

    /**
     * <pre>
     * HeaderClose Event
     * </pre>
     * @param cMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NPAL1080Scrn00_HeaderClose(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {
        NPAL1080CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        if (isCloseStatus(cMsg, sMsg)) {
            // Header Close NG
            return;
        }

        if (lockPR(cMsg, sMsg) == null) {
            return;
        }
        NPZC103001PMsg pMsg = new NPZC103001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC103001Constant.MODE_CLOSE);
        ZYPEZDItemValueSetter.setValue(pMsg.eventId, NPZC103001Constant.EVENT_SUBMIT);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        // QC#52445 Mod
//        ZYPEZDItemValueSetter.setValue(pMsg.prchReqNum, sMsg.prchReqNum_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.prchReqNum_HD, cMsg.prchReqNum_HD);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqNum, sMsg.prchReqNum_HD);

        ZYPEZDItemValueSetter.setValue(pMsg.procDt, ZYPDateUtil.getSalesDate());

        if (executeNPZC1030(cMsg, pMsg)) {
            cMsg.setMessageInfo(ZZM8100I);
        }
    }

    /**
     * <pre>
     * Close Status Check
     * </pre>
     * @param cMsg Business Msg
     * @param sMsg Global Msg
     * @return boolean close process ok:true
     */
    private boolean isCloseStatus(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        // Open SO Check
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRCH_REQ_NUM, sMsg.prchReqNum_H1);
        ssmParam.put(DB_PARAM_SCE_ORD_TP_CD, SCE_ORD_TP.TECH_REQUEST);
        ssmParam.put(DB_PARAM_FLAG_Y, ZYPConstant.FLG_ON_Y);
        ssmParam.put(DB_PARAM_FLAG_N, ZYPConstant.FLG_OFF_N);

        // Execute
        S21SsmEZDResult result = NPAL1080Query.getInstance().execQuery(ssmParam, "checkOpenSO");

        if (result.isCodeNormal()) {
            cMsg.setMessageInfo(NPAM1594E);
            return true;
        }

        // Open RWS Check
        ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRCH_REQ_NUM, sMsg.prchReqNum_H1);
        ssmParam.put(DB_PARAM_SCE_ORD_TP_CD, SCE_ORD_TP.TECH_REQUEST);
        ssmParam.put(DB_PARAM_FLAG_Y, ZYPConstant.FLG_ON_Y);

        // Execute
        result = NPAL1080Query.getInstance().execQuery(ssmParam, "checkOpenRWS");

        if (result.isCodeNormal()) {
            cMsg.setMessageInfo(NPAM1595E);
            return true;
        }

        // Open PR Check
        ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRCH_REQ_NUM, sMsg.prchReqNum_H1);
        ssmParam.put(DB_PARAM_PRCH_REQ_REC_TP_CD, PRCH_REQ_REC_TP.PO_REQUISITION);
        ssmParam.put(DB_PARAM_FLAG_Y, ZYPConstant.FLG_ON_Y);

        // Execute
        result = NPAL1080Query.getInstance().execQuery(ssmParam, "checkOpenPR");

        if (result.isCodeNormal()) {
            cMsg.setMessageInfo(NPAM1596E);
            return true;
        }

        // Open SO Check
        ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRCH_REQ_NUM, sMsg.prchReqNum_H1);
        // Mod Start 2020/03/10 QC#56178
//        ssmParam.put(DB_PARAM_PO_HDR_STS_CD, PO_HDR_STS.OPEN);
        ssmParam.put(DB_PARAM_PO_STS_CD_CLOSED, PO_STS.CLOSED);
        ssmParam.put(DB_PARAM_PO_STS_CD_CANCELED, PO_STS.CANCELLED);
        // Mod End 2020/03/10 QC#56178

        // Execute
        result = NPAL1080Query.getInstance().execQuery(ssmParam, "checkOpenPO");

        if (result.isCodeNormal()) {
            cMsg.setMessageInfo(NPAM1597E);
            return true;
        }

        return false;
    }

    /**
     * getSplyItemNumFromAsl
     * @param glblCmpyCd String
     * @param salesDate String
     * @param mdseCd String
     * @param vndCd String
     * @return String
     */
    private String getSplyItemNumFromAsl(String glblCmpyCd, String salesDate, String mdseCd, String vndCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_VND_CD, vndCd);
        ssmParam.put(DB_PARAM_MDSE_CD, mdseCd);
        ssmParam.put(DB_PARAM_SALES_DATE, salesDate);
        S21SsmEZDResult rs = NPAL1080Query.getInstance().getSplyItemNumFromAsl(ssmParam);

        if (rs.isCodeNormal()) {
            return (String) rs.getResultObject();
        }

        return null;

    }

    // START 2017/08/31 K.Kameoka [Sol#369(QC#19243),ADD]
    /**
     * getInsrcCtrlInfo
     * @param techTocCd String
     * @param prchReqTpCd String
     * @param shpgSvcLvlCd String
     * @return String
     */
    private String getInsrcCtrlInfo(String glblCmpyCd, String rtlWhCd, String prchReqTpCd, String shpgSvcLvlCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put("rtlWhCd", rtlWhCd);
        ssmParam.put("prchReqTpCd", prchReqTpCd);

        if (ZYPCommonFunc.hasValue(shpgSvcLvlCd)) {
            ssmParam.put("shpgSvcLvlCds", new String[] {shpgSvcLvlCd, NPAL1080Constant.ASTERISK });
        } else {
            ssmParam.put("shpgSvcLvlCds", new String[] {NPAL1080Constant.ASTERISK });
        }

        S21SsmEZDResult rs = NPAL1080Query.getInstance().getInsrcCtrlInfo(ssmParam);

        return (String) rs.getResultObject();
    }

    // END 2017/08/31 K.Kameoka [Sol#369(QC#19243),ADD]

    /**
     * QC#21909
     * @param psnCd String
     * @param glblCmpyCd String
     * @return PSN Code
     */
    private String getTechnLineBizType(String psnCd, String glblCmpyCd) {
        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put("psnCd", psnCd);
        ssmParam.put("effFromDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("effThruDt", ZYPDateUtil.getSalesDate());
        // Execute
        S21SsmEZDResult result = NPAL1080Query.getInstance().checkTechnician(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {
                return (String) resultList.get(i).get("LINE_BIZ_TP_CD");
            }
        }
        return null;
    }

    /**
     * isPossibleLineCancel
     * @param sMsg
     * @return true:Cancel OK.
     */
    private boolean isPossibleLineCancel(NPAL1080SMsg sMsg) {

        boolean error = false;

        if (PRCH_REQ_APVL_STS.APPROVED.equals(sMsg.prchReqApvlStsCd_H1.getValue()) //
                || PRCH_REQ_APVL_STS.PRE_APPROVED.equals(sMsg.prchReqApvlStsCd_H1.getValue())) {

            boolean currentlyChecking = false;
            int currentParentID = 0;
            // QC#54672
            boolean isParentPartial = false;

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                NPAL1080_ASMsg lineMsg = sMsg.A.no(i);

                if (isParentDetail(lineMsg)) {
                    if (NPAL1080CommonLogic.isChecked(lineMsg)) {
                        currentlyChecking = true;
                        currentParentID = i;
                        // QC#54672
                        if (PRCH_REQ_LINE_STS.PARTIALLY_RECEIVED.equals(lineMsg.prchReqLineStsCd_D1.getValue())) {
                            isParentPartial = true;
                        }
                    }
                }

                // Check
                if (currentlyChecking) {
                    // QC#54672
                    if (!isParentPartial && PRCH_REQ_LINE_STS.RECEIVED.equals(lineMsg.prchReqLineStsCd_D1.getValue()) //
                            || PRCH_REQ_LINE_STS.CLOSED.equals(lineMsg.prchReqLineStsCd_D1.getValue())) {
                        // Error
                        error = true;
                        // [This line] is closed. Cannot [Cancel]
                        // process.
                        sMsg.A.no(currentParentID).xxChkBox_D1.setErrorInfo(1, NPAM1628E, new String[] {"This line", "Cancel" });
                    }
                    // QC#29988 Order Line Status Chacek Start
                    // Mod Start 2020/03/10 QC#56126
//                    if (ZYPCommonFunc.hasValue(lineMsg.poOrdNum_AC)) {
                    if (NPAL1080CommonLogic.isPoDtlCancel(getGlobalCompanyCode(), sMsg.prchReqNum_H1.getValue(), lineMsg.prchReqLineNum_D1.getValue())) {
                        // Error
                        error = true;
                        // You can't cancel this request since
                        // open PO exists.
                        sMsg.A.no(currentParentID).xxChkBox_D1.setErrorInfo(1, NPAM1625E);
                    }
//                    }
//                    if (ZYPCommonFunc.hasValue(lineMsg.prchReqNum_AC)) {
                    if (NPAL1080CommonLogic.isPrDtlCancel(getGlobalCompanyCode(), sMsg.prchReqNum_H1.getValue(), lineMsg.prchReqLineNum_D1.getValue())) {
                        // Error
                        error = true;
                        // You can't cancel this request since
                        // open PO Requisition exists.
                        sMsg.A.no(currentParentID).xxChkBox_D1.setErrorInfo(1, NPAM1624E);
                    }
//                    }
//                    if (ZYPCommonFunc.hasValue(lineMsg.rwsNum_AC)) {
                    if (NPAL1080CommonLogic.isRwsDtlCancel(getGlobalCompanyCode(), sMsg.prchReqNum_H1.getValue(), lineMsg.prchReqLineNum_D1.getValue())) {
                        // Error
                        error = true;
                        // You can't cancel this request since
                        // open Receiving Order exists.
                        sMsg.A.no(currentParentID).xxChkBox_D1.setErrorInfo(1, NPAM1623E);
                    }
//                    }
//                    if (ZYPCommonFunc.hasValue(lineMsg.vndSoNum_AC)) {
                    if (NPAL1080CommonLogic.isSoDtlCancel(getGlobalCompanyCode(), sMsg.prchReqNum_H1.getValue(), lineMsg.prchReqLineNum_D1.getValue())) {
                        // Error
                        error = true;
                        // You can't cancel this request since
                        // open Shipping Order exists.
                        sMsg.A.no(currentParentID).xxChkBox_D1.setErrorInfo(1, NPAM1622E);
                    }
//                    }
                    // Mod End 2020/03/10 QC#56126
                    // QC#29988 Order Line Status Chacek End
                }

                // Next Line Check
                int nextValue = i + 1;
                if (nextValue < sMsg.A.getValidCount()) {
                    NPAL1080_ASMsg nextLineMsg = sMsg.A.no(i + 1);
                    if (isParentDetail(nextLineMsg)) {
                        // init
                        currentlyChecking = false;
                        // QC#54672
                        isParentPartial = false;
                    }
                }
            }
        }

        return !error;
    }

    private boolean isInsourcedLine(String prchReqNum, String prchReqLineNum, BigDecimal prchReqLineSubNum) {

        PRCH_REQ_DTLTMsg dtl = NPAL1080Query.getInstance().getPrchReqDtl(getGlobalCompanyCode(), prchReqNum, prchReqLineNum, prchReqLineSubNum);

        if (dtl != null //
                && ((ZYPCommonFunc.hasValue(prchReqLineSubNum) && BigDecimal.ZERO.compareTo(prchReqLineSubNum) < 0) //
                        || !PRCH_REQ_REL_STS.IN_COMPLETED.equals(dtl.prchReqRelStsCd.getValue()))) {
            return true;
        } else {
            return false;
        }
    }
}
