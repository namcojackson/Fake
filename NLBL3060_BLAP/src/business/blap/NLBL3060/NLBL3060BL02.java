/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/07/13   Fujitsu         N.Yamamoto      Create          4486
 *</pre>
 */
package business.blap.NLBL3060;

import static business.blap.NLBL3060.common.NLBL3060CommonLogic.getQuery;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DEF_CUSA_GLBL_CMPY_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DEF_PO_CUST_DROP_SHIP_QLFY_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.VAR_CHAR_CUSA_GLBL_CMPY_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.VAR_CHAR_PO_CUST_DROP_SHIP_QLFY_CD;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCSVInFile;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLBL3060.common.NLBL3060CommonLogic;
import business.blap.NLBL3060.constant.NLBL3060Constant;
import business.blap.NLBL3060.constant.NLBL3060Constant.MESSAGE_ID;
import business.blap.NPAL1500.NPAL1500CMsg;
import business.blap.NPAL1500.NPAL1500Query;
import business.db.PO_ORD_SRCTMsg;
import business.db.SCHD_COORD_WH_PMSNTMsg;
import business.file.NLBL3060F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ORD_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   CSAI            K.Lee           Create          
 * 2016/11/15   CITS            K.Kameoka       Update          QC#15574
 * 2023/04/13   Hitachi         T.Kuroda        Update          QC#61166
 * 2023/10/18   Hitachi         Y.Ogura         Update          QC#61793
 *</pre>
 */
public class NLBL3060BL02 extends S21BusinessHandler implements NLBL3060Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NLBL3060_INIT".equals(screenAplID)) {
                doProcess_NLBL3060_INIT(cMsg, sMsg);

            } else if ("NLBL3060Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NLBL3060Scrn00_CMN_Reset((NLBL3060CMsg) cMsg, (NLBL3060SMsg) sMsg);

            } else if ("NLBL3060Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLBL3060Scrn00_CMN_Submit(cMsg, sMsg);

            } else if ("NLBL3060Scrn00_Delete_Row".equals(screenAplID)) {
                doProcess_NLBL3060Scrn00_Delete_Row((NLBL3060CMsg) cMsg, (NLBL3060SMsg) sMsg);

            } else if ("NLBL3060Scrn00_Insert_Row".equals(screenAplID)) {
                doProcess_NLBL3060Scrn00_Insert_Row((NLBL3060CMsg) cMsg, (NLBL3060SMsg) sMsg);

            } else if ("NLBL3060Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NLBL3060Scrn00_PageNext((NLBL3060CMsg) cMsg, (NLBL3060SMsg) sMsg);

            } else if ("NLBL3060Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NLBL3060Scrn00_PagePrev((NLBL3060CMsg) cMsg, (NLBL3060SMsg) sMsg);

            } else if ("NLBL3060Scrn00_Search".equals(screenAplID)) {
                doProcess_NLBL3060Scrn00_Search((NLBL3060CMsg) cMsg, (NLBL3060SMsg) sMsg);

            } else if ("NLBL3060Scrn00_Click_DtlPersonName".equals(screenAplID)) {
                doProcess_NLBL3060Scrn00_Click_DtlPersonName((NLBL3060CMsg) cMsg, (NLBL3060SMsg) sMsg);

            } else if ("NLBL3060Scrn00_Click_DtlRtlWhName".equals(screenAplID)) {
                doProcess_NLBL3060Scrn00_Click_DtlRtlWhName((NLBL3060CMsg) cMsg, (NLBL3060SMsg) sMsg);

            } else if ("NLBL3060Scrn00_Click_PersonName".equals(screenAplID)) {
                doProcess_NLBL3060Scrn00_Click_PersonName((NLBL3060CMsg) cMsg, (NLBL3060SMsg) sMsg);

            } else if ("NLBL3060Scrn00_Click_RtlWhName".equals(screenAplID)) {
                doProcess_NLBL3060Scrn00_Click_RtlWhName((NLBL3060CMsg) cMsg, (NLBL3060SMsg) sMsg);

            } else if ("NLBL3060_NWAL1130".equals(screenAplID)) {
                doProcess_NLBL3060_NWAL1130((NLBL3060CMsg) cMsg, (NLBL3060SMsg) sMsg);

            // START 2023/04/13 T.Kuroda [QC#61166, ADD]
            } else if ("NLBL3060Scrn00_OnClick_TemplateDownload".equals(screenAplID)) {
                doProcess_NLBL3060_OnClick_TemplateDownload((NLBL3060CMsg) cMsg, (NLBL3060SMsg) sMsg);

            } else if ("NLBL3060Scrn00_OnClick_Upload".equals(screenAplID)) {
                doProcess_NLBL3060_OnClick_Upload((NLBL3060CMsg) cMsg, (NLBL3060SMsg) sMsg);

            } else if ("NLBL3060Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NLBL3060_CMN_Download((NLBL3060CMsg) cMsg, (NLBL3060SMsg) sMsg);
            // END   2023/04/13 T.Kuroda [QC#61166, ADD]

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);

            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NLBL3060_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLBL3060_INIT START ----- ", this);

        NLBL3060CMsg bizMsg = (NLBL3060CMsg) cMsg;
        NLBL3060SMsg glblMsg = (NLBL3060SMsg) sMsg;
        bizMsg.clear();
        glblMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        bizMsg.xxChkBox.setValue(ZYPConstant.CHKBOX_ON_Y);
        // START 2023/10/18 Y.Ogura [QC#61793, ADD]
        setInitialData(bizMsg, false);
        // END 2023/10/18 Y.Ogura [QC#61793, ADD]

        EZDDebugOutput.println(1, "----- doProcess_NLBL3060_INIT END ----- ", this);
    }

    private void doProcess_NLBL3060Scrn00_CMN_Reset(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLBL3060Scrn00_CMN_Reset START ----- ", this);
        doProcess_NLBL3060_INIT(cMsg, sMsg);
        EZDDebugOutput.println(1, "----- doProcess_NLBL3060Scrn00_CMN_Reset END ----- ", this);
    }

    private void doProcess_NLBL3060Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLBL3060Scrn00_CMN_Submit START ----- ", this);

        NLBL3060CMsg bizMsg = (NLBL3060CMsg) cMsg;
        NLBL3060SMsg globalMsg = (NLBL3060SMsg) sMsg;

        // START 2023/10/18 Y.Ogura [QC#61793, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCatgCd_HD, bizMsg.rtlWhCatgCd_HD);
        ZYPEZDItemValueSetter.setValue(bizMsg.physWhCd_HD, bizMsg.physWhCd_HD);
        // END 2023/10/18 Y.Ogura [QC#61793, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd, bizMsg.rtlWhCd_B);
        ZYPEZDItemValueSetter.setValue(bizMsg.schdCoordPsnCd, bizMsg.schdCoordPsnCd_B);

        doProcess_NLBL3060Scrn00_Search(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "----- doProcess_NLBL3060Scrn00_CMN_Submit END ----- ", this);
    }

    private void doProcess_NLBL3060Scrn00_Delete_Row(NLBL3060CMsg bizMsg, NLBL3060SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_Delete_Row START ----- ", this);
        int procCnt = 0;
        NLBL3060CommonLogic.copyPage(bizMsg, bizMsg.A, globalMsg.A);
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(globalMsg.A.no(i).xxChkBox_A1.getValue())) {
                procCnt++;
            }
        }
        if (procCnt == 0) {
            bizMsg.setMessageInfo(MESSAGE_ID.NFAM0075E.toString());
            return;
        }

        NLBL3060SMsg tmpMsg = new NLBL3060SMsg();
        EZDMsg.copy(globalMsg, null, tmpMsg, null);
        ZYPTableUtil.clear(globalMsg.A);

        int j = 0;
        for (int i = 0; i < tmpMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.CHKBOX_ON_Y.equals(tmpMsg.A.no(i).xxChkBox_A1.getValue())) {
                EZDMsg.copy(tmpMsg.A.no(i), null, globalMsg.A.no(j), null);
                j++;
            }
        }
        globalMsg.A.setValidCount(j);
        BigDecimal lastPageFromNum = NLBL3060CommonLogic.getLastPageFromNum(bizMsg, globalMsg);
        if (bizMsg.xxPageShowFromNum.getValue().compareTo(lastPageFromNum) < 0) {
            NLBL3060CommonLogic.dispPage(bizMsg, bizMsg.A, globalMsg.A);
        } else {
            NLBL3060CommonLogic.lastPage(bizMsg, globalMsg);
        }
        bizMsg.setMessageInfo("NZZM0002I");
        EZDDebugOutput.println(1, "----- doProcess_Delete_Row END ----- ", this);
    }

    private void doProcess_NLBL3060Scrn00_Insert_Row(NLBL3060CMsg bizMsg, NLBL3060SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_InsertRow START ----- ", this);

        NLBL3060CommonLogic.copyPage(bizMsg, bizMsg.A, globalMsg.A);

        int no = globalMsg.A.getValidCount();

        if (no == globalMsg.A.length()) {
            bizMsg.setMessageInfo("NFBM0150E", new String[] {String.valueOf(globalMsg.A.length()) });
            return;
        }

        // START 2023/10/18 Y.Ogura [QC#61793, ADD]
        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).rtlWhCatgCd_A1, bizMsg.rtlWhCatgCd_HD);
        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).physWhCd_A1, bizMsg.physWhCd_HD);
        // END 2023/10/18 Y.Ogura [QC#61793, ADD]
        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).rtlWhCd_A1, bizMsg.rtlWhCd_B);
        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).schdCoordPsnCd_A1, bizMsg.schdCoordPsnCd_B);
        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).effFromDt_A1, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).effThruDt_A1, LAST_DATE);
        globalMsg.A.setValidCount(no + 1);
        NLBL3060CommonLogic.lastPage(bizMsg, globalMsg);
        bizMsg.setMessageInfo("NZZM0002I");

        EZDDebugOutput.println(1, "----- doProcess_InsertRow END ----- ", this);
    }

    private void doProcess_NLBL3060Scrn00_PageNext(NLBL3060CMsg bizMsg, NLBL3060SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLBL3060Scrn00_PageNext START ----- ", this);

        NLBL3060CommonLogic.copyPage(bizMsg, bizMsg.A, globalMsg.A);
        NLBL3060CommonLogic.nextPage(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "----- doProcess_NLBL3060Scrn00_PageNext END ----- ", this);
    }

    private void doProcess_NLBL3060Scrn00_PagePrev(NLBL3060CMsg bizMsg, NLBL3060SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLBL3060Scrn00_PagePrev START ----- ", this);

        NLBL3060CommonLogic.copyPage(bizMsg, bizMsg.A, globalMsg.A);
        NLBL3060CommonLogic.prevPage(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "----- doProcess_NLBL3060Scrn00_PagePrev END ----- ", this);
    }

    private void doProcess_NLBL3060Scrn00_Search(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(1, "----- doProcess_NLBL3060Scrn00_Search START ----- ", this);

        NLBL3060CMsg bizMsg = (NLBL3060CMsg) cMsg;
        NLBL3060SMsg globalMsg = (NLBL3060SMsg) sMsg;
        boolean errFlg = false;
        if (!ZYPCommonFunc.hasValue(bizMsg.rtlWhCd)) {
            bizMsg.rtlWhNm.clear();
        } else {
            if (!doProcess_NLBL3060Scrn00_Click_RtlWhName(bizMsg, globalMsg)) {
                errFlg = true;
            }
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.schdCoordPsnCd)) {
            bizMsg.fullPsnNm.clear();
        } else {
            if (!doProcess_NLBL3060Scrn00_Click_PersonName(bizMsg, globalMsg)) {
                errFlg = true;
            }
        }
        if (errFlg) {
            return;
        }
        getMasterTableInfo(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "----- doProcess_NLBL3060Scrn00_Search END ----- ", this);
    }

    private void getMasterTableInfo(NLBL3060CMsg bizMsg, NLBL3060SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- getMasterTableInfo START ----- ", this);

        NLBL3060_ACMsgArray bizMsgArray = bizMsg.A;
        NLBL3060_ASMsgArray globalMsgArray = globalMsg.A;

        ZYPTableUtil.clear(bizMsgArray);
        ZYPTableUtil.clear(globalMsgArray);
        ZYPTableUtil.clear(globalMsg.B);
        bizMsg.xxPageShowFromNum.clear();
        bizMsg.xxPageShowOfNum.clear();
        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxSortItemNm.clear();
        bizMsg.xxSortOrdByTxt.clear();
        bizMsg.xxSortTblNm.clear();

        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", getGlobalCompanyCode());
        prmMap.put("rtlWhCd", bizMsg.rtlWhCd.getValue());
        prmMap.put("schdCoordPsnCd", bizMsg.schdCoordPsnCd.getValue());
        prmMap.put("xxChkBox", bizMsg.xxChkBox.getValue());
        prmMap.put("slsDt", ZYPDateUtil.getSalesDate());
        // START 2023/10/18 Y.Ogura [QC#61793, ADD]
        prmMap.put("rtlWhCatgCd", bizMsg.rtlWhCatgCd_HD.getValue());
        prmMap.put("physWhCd", bizMsg.physWhCd_HD.getValue());
        // END 2023/10/18 Y.Ogura [QC#61793, ADD]

        S21SsmEZDResult ssmResult = getQuery().getMasterTable(bizMsg, globalMsg, prmMap);

        if (ssmResult.isCodeNormal()) {
            ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd_B, bizMsg.rtlWhCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.schdCoordPsnCd_B, bizMsg.schdCoordPsnCd);
            int queryResCnt = ssmResult.getQueryResultCount();
            int maxCnt = globalMsgArray.length();
            // QC#15574 Start
            for (int i = 0; i < maxCnt; i++) {
                if ("*".equals(globalMsg.A.no(i).rtlWhCd_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).rtlWhNm_A1, "All Warehouse");
                }
            }
            // QC#15574 End

            if (queryResCnt > maxCnt) {
                queryResCnt = maxCnt;
                bizMsg.setMessageInfo(MESSAGE_ID.NMAM0098I.toString(), new String[] {Integer.toString(maxCnt) });
            }

            EZDMsg.copy(globalMsgArray, null, bizMsgArray, null);
            EZDMsg.copy(globalMsgArray, "A1", globalMsg.B, "B1");

            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowOfNum.setValue(queryResCnt);
            bizMsg.xxPageShowToNum.setValue(bizMsgArray.getValidCount());

        } else {
            bizMsg.setMessageInfo(MESSAGE_ID.NMAM0007I.toString());
            return;
        }

        EZDDebugOutput.println(1, "----- getMasterTableInfo END ----- ", this);
    }

    private boolean doProcess_NLBL3060Scrn00_Click_RtlWhName(NLBL3060CMsg bizMsg, NLBL3060SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLBL3060Scrn00_Click_RtlWhName START ----- ", this);
        String rtlWhNm = NLBL3060CommonLogic.getRtlWhNm(getGlobalCompanyCode(), bizMsg.rtlWhCd.getValue());
        if (rtlWhNm == null) {
            bizMsg.rtlWhCd.setErrorInfo(1, MESSAGE_ID.NMAM0009E.toString(), new String[] {"Retail Warehouse Code" });
            return false;
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm, rtlWhNm);
        }
        return true;
    }

    private boolean doProcess_NLBL3060Scrn00_Click_PersonName(NLBL3060CMsg bizMsg, NLBL3060SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLBL3060Scrn00_Click_PersonName START ----- ", this);
        String psnNm = NLBL3060CommonLogic.getPersonNm(getGlobalCompanyCode(), bizMsg.schdCoordPsnCd.getValue());
        if (psnNm == null) {
            bizMsg.schdCoordPsnCd.setErrorInfo(1, MESSAGE_ID.NMAM0009E.toString(), new String[] {"Person Code" });
            return false;
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.fullPsnNm, psnNm);
        }
        return true;
    }

    private void doProcess_NLBL3060Scrn00_Click_DtlRtlWhName(NLBL3060CMsg bizMsg, NLBL3060SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLBL3060Scrn00_Click_DtlRtlWhName START ----- ", this);
        String rtlWhNm = NLBL3060CommonLogic.getRtlWhNm(getGlobalCompanyCode(), bizMsg.A.no(bizMsg.xxNum.getValueInt()).rtlWhCd_A1.getValue());
        if (rtlWhNm == null) {
            bizMsg.A.no(bizMsg.xxNum.getValueInt()).rtlWhCd_A1.setErrorInfo(1, MESSAGE_ID.NMAM0009E.toString(), new String[] {"Retail Warehouse Code" });
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.xxNum.getValueInt()).rtlWhNm_A1, rtlWhNm);
        }
        EZDDebugOutput.println(1, "----- doProcess_NLBL3060Scrn00_Click_DtlRtlWhName END ----- ", this);
    }

    private void doProcess_NLBL3060Scrn00_Click_DtlPersonName(NLBL3060CMsg bizMsg, NLBL3060SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLBL3060Scrn00_Click_DtlPersonName START ----- ", this);
        String psnNm = NLBL3060CommonLogic.getPersonNm(getGlobalCompanyCode(), bizMsg.A.no(bizMsg.xxNum.getValueInt()).schdCoordPsnCd_A1.getValue());
        if (psnNm == null) {
            bizMsg.A.no(bizMsg.xxNum.getValueInt()).schdCoordPsnCd_A1.setErrorInfo(1, MESSAGE_ID.NMAM0009E.toString(), new String[] {"Person Code" });
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.xxNum.getValueInt()).fullPsnNm_A1, psnNm);
        }
    }
    
    // START 2023/10/18 Y.Ogura [QC#61793, ADD]
    private boolean doProcess_NLBL3060Scrn00_Click_GroupName(NLBL3060CMsg bizMsg, NLBL3060SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLBL3060Scrn00_Click_GroupName START ----- ", this);
        String physWhCd = NLBL3060CommonLogic.getRtlWhNm(getGlobalCompanyCode(), bizMsg.physWhCd_HD.getValue());
        if (physWhCd == null) {
            bizMsg.rtlWhCd.setErrorInfo(1, MESSAGE_ID.NMAM0009E.toString(), new String[] {"Group Name" });
            return false;
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.physWhCd_HD, physWhCd);
        }
        return true;
    }
    // END 2023/10/18 Y.Ogura [QC#61793, ADD]

    private void doProcess_NLBL3060_NWAL1130(NLBL3060CMsg bizMsg, NLBL3060SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLBL3060Scrn00_NLBL3060_NWAL1130 START ----- ", this);
        String scrEventNm = bizMsg.xxScrEventNm.getValue();
        if ("Link_Person".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(bizMsg.schdCoordPsnCd)) {
                doProcess_NLBL3060Scrn00_Click_PersonName(bizMsg, globalMsg);
            }
        } else if ("Link_RtlWh".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(bizMsg.rtlWhCd)) {
                doProcess_NLBL3060Scrn00_Click_RtlWhName(bizMsg, globalMsg);
            }
        } else if ("Btn_DtlPerson".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(bizMsg.xxNum.getValueInt()).schdCoordPsnCd_A1)) {
                doProcess_NLBL3060Scrn00_Click_DtlPersonName(bizMsg, globalMsg);
            }
        } else if ("Btn_DtlRtlWh".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(bizMsg.xxNum.getValueInt()).rtlWhCd_A1)) {
                doProcess_NLBL3060Scrn00_Click_DtlRtlWhName(bizMsg, globalMsg);
            }
        }
        // START 2023/10/18 Y.Ogura [QC#61793, ADD]
        else if ("Link_GroupName".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(bizMsg.physWhCd_HD)) {
                doProcess_NLBL3060Scrn00_Click_GroupName(bizMsg, globalMsg);
            }
        }
        // END 2023/10/18 Y.Ogura [QC#61793, ADD]
    }

    // START 2023/04/13 T.Kuroda [QC#61166, ADD]
    private void doProcess_NLBL3060_OnClick_TemplateDownload(NLBL3060CMsg bizMsg, NLBL3060SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLBL3060_OnClick_TemplateDownload START ----- ", this);

        NLBL3060F00FMsg fMsg = new NLBL3060F00FMsg();

        bizMsg.xxFileData_UP.setTempFilePath(null
                , ZYPCSVOutFile.createCSVOutFileNm(CSV_TEMP_FILE_NAME)
                , CSV_FILE_EXTENSION);
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData_UP.getTempFilePath(), fMsg);

        csvOutFile.writeHeader(CSV_HDR_DOWNLOAD);
        csvOutFile.close();

        EZDDebugOutput.println(1, "----- doProcess_NLBL3060_OnClick_TemplateDownload END ----- ", this);
    }

    private void doProcess_NLBL3060_OnClick_Upload(NLBL3060CMsg bizMsg, NLBL3060SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLBL3060_OnClick_Upload START ----- ", this);

        // clear details
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(globalMsg.A);
        ZYPTableUtil.clear(globalMsg.B);

        String glCmpCd = getGlobalCompanyCode();

        String path = bizMsg.xxFileData_UP.getTempFilePath();
        if (path.length() == 0) {
            bizMsg.setMessageInfo(MESSAGE_ID.ZYEM0004E.toString());
            return;
        }

        NLBL3060F00FMsg fMsg = new NLBL3060F00FMsg();
        int option =
            EZDCSVInFile.NO_ABORT
            | EZDCSVInFile.QUOTE_FLG
            | EZDCSVInFile.STR_LEN_CHK
            | EZDCSVInFile.STR_TYPE_CHK;
        EZDCSVInFile mappedFile = new EZDCSVInFile(path, fMsg, option);

        try {
            // -----------------------------------
            // Validate File for Upload
            // -----------------------------------
            if (mappedFile.read() == 1) {
                // Upload File is empty
                bizMsg.setMessageInfo(MESSAGE_ID.ZYEM0004E.toString());
                return;
            }

            int sts = -1;
            int upFileLine = 0;

            while ((sts = mappedFile.read()) != 1) {
                upFileLine++;

                // Check format
                if (sts == CSV_READ_STATUS_CODE_1000) {
                    bizMsg.setMessageInfo(MESSAGE_ID.NLBM1379E.toString()
                            , new String[] {UPLOAD_DATA_FORMAT});
                    return;
                }

                int errCol1 = sts - CSV_READ_STATUS_CODE_1000;
                int errCol2 = sts - CSV_READ_STATUS_CODE_2000;
                String errCol = "";


                // START 2023/10/18 Y.Ogura [QC#61793, ADD]
                // WH Type
                if (errCol1 == 1 || errCol2 == 1) {
                    errCol = "WH Type (Line# " + String.valueOf(upFileLine) + ")";

                // Group Name
                } else if (errCol1 == 2 || errCol2 == 2) {
                    errCol = "Group Name (Line# " + String.valueOf(upFileLine) + ")";
                // END 2023/10/18 Y.Ogura [QC#61793, ADD]
                // Retail Warehouse Code
                // START 2023/10/18 Y.Ogura [QC#61793, MOD]
//                } else if (errCol1 == 1 || errCol2 == 1) {
                } else if (errCol1 == 3 || errCol2 == 3) {
                    errCol = "Retail Warehouse Code (Line# " + String.valueOf(upFileLine) + ")";
                // END 2023/10/18 Y.Ogura [QC#61793, MOD]
                // Retail Warehouse Name
                // START 2023/10/18 Y.Ogura [QC#61793, MOD]
//                } else if (errCol1 == 2 || errCol2 == 2) {
                } else if (errCol1 == 4 || errCol2 == 4) {
                // END 2023/10/18 Y.Ogura [QC#61793, MOD]
                    errCol = "Retail Warehouse Name (Line# " + String.valueOf(upFileLine) + ")";

                // Permitted User Code
                // START 2023/10/18 Y.Ogura [QC#61793, MOD]
//                } else if (errCol1 == 3 || errCol2 == 3) {
                } else if (errCol1 == 5 || errCol2 == 5) {
                // END 2023/10/18 Y.Ogura [QC#61793, MOD]
                    errCol = "Permitted User Code (Line# " + String.valueOf(upFileLine) + ")";

                // Permitted User Name
                // START 2023/10/18 Y.Ogura [QC#61793, MOD]
//                } else if (errCol1 == 4 || errCol2 == 4) {
                } else if (errCol1 == 6 || errCol2 == 6) {
                // END 2023/10/18 Y.Ogura [QC#61793, MOD]
                    errCol = "Permitted User Name (Line# " + String.valueOf(upFileLine) + ")";

                // Eff From Date
                // START 2023/10/18 Y.Ogura [QC#61793, MOD]
//                } else if (errCol1 == 5 || errCol2 == 5) {
                } else if (errCol1 == 7 || errCol2 == 7) {
                // END 2023/10/18 Y.Ogura [QC#61793, MOD]
                    errCol = "Eff From Date (Line# " + String.valueOf(upFileLine) + ")";

                // Eff Thru Date
                // START 2023/10/18 Y.Ogura [QC#61793, MOD]
//                } else if (errCol1 == 6 || errCol2 == 6) {
                } else if (errCol1 == 8 || errCol2 == 8) {
                // END 2023/10/18 Y.Ogura [QC#61793, MOD]
                    errCol = "Eff Thru Date (Line# " + String.valueOf(upFileLine) + ")";
                }

                if (!errCol.isEmpty()) {
                    bizMsg.setMessageInfo(MESSAGE_ID.NMAM8191E.toString(), new String[] {errCol});
                    return;
                }

                // Exceeds the max numbers
                if (globalMsg.A.getValidCount() + upFileLine > globalMsg.A.length()) {
                    bizMsg.setMessageInfo(MESSAGE_ID.NLBM1380E.toString());
                    return;
                }
            }
            mappedFile.close();

            if (upFileLine == 0) {
                // Only has a header line
                bizMsg.setMessageInfo(MESSAGE_ID.ZYEM0004E.toString());
                return;
            }

            // -----------------------------------
            // Import File for Upload
            // -----------------------------------
            int index = globalMsg.A.getValidCount();

            mappedFile = new EZDCSVInFile(path, fMsg, option);
            // Dummy read header
            mappedFile.read();
            // START 2023/10/18 Y.Ogura [QC#61793, ADD]
            Map<String, String> rtlWhCatgCdMap = getRtlWhCatgCdMap(glCmpCd, UPLOAD_NAME);
            // END 2023/10/18 Y.Ogura [QC#61793, ADD]
            while (mappedFile.read() != 1) {
                // START 2023/10/18 Y.Ogura [QC#61793, ADD]
                BigDecimal schdCoordWhPmsnPk = null;
                String rtlWhCatgCd = rtlWhCatgCdMap.get(fMsg.rtlWhCatgNm_DL.getValue());
                // END 2023/10/18 Y.Ogura [QC#61793, ADD]
                
                // Get Retail Warehouse Name
                String rtlWhNm = "";
                if (ZYPCommonFunc.hasValue(fMsg.rtlWhCd_DL)) {
                    String whNm = NLBL3060CommonLogic.getRtlWhNm(
                            glCmpCd, fMsg.rtlWhCd_DL.getValue());
                    rtlWhNm = whNm == null ? "" : whNm; 
                }

                // Get Permitted User Name
                String permUsrNm = "";
                if (ZYPCommonFunc.hasValue(fMsg.schdCoordPsnCd_DL)) {
                    String psnNm = NLBL3060CommonLogic.getPersonNm(
                            glCmpCd, fMsg.schdCoordPsnCd_DL.getValue());
                    permUsrNm = psnNm == null ? "" : psnNm; 
                }

                // Get SCHD_COORD_WH_PMSN
                String effThruDt = "";
                String ezUpTime = "";
                String ezUpTimeZone = "";
                boolean isGetSchdCoordWhPmsn = false;

                // START 2023/10/18 Y.Ogura [QC#61793, MOD]
//                if (ZYPCommonFunc.hasValue(fMsg.rtlWhCd_DL)
//                        && ZYPCommonFunc.hasValue(fMsg.schdCoordPsnCd_DL)
//                        && ZYPCommonFunc.hasValue(fMsg.effFromDt_DL)
                if (ZYPCommonFunc.hasValue(fMsg.schdCoordPsnCd_DL)
                        && ZYPCommonFunc.hasValue(fMsg.effFromDt_DL)
                        &&(ZYPCommonFunc.hasValue(fMsg.rtlWhCd_DL)
                            || ZYPCommonFunc.hasValue(fMsg.rtlWhCatgNm_DL) 
                            || ZYPCommonFunc.hasValue(fMsg.physWhCd_DL))
                // END 2023/10/18 Y.Ogura [QC#61793, MOD]
                       ){
                    SCHD_COORD_WH_PMSNTMsg inMsg = new SCHD_COORD_WH_PMSNTMsg();
                    ZYPEZDItemValueSetter.setValue(inMsg.schdCoordPsnCd, fMsg.schdCoordPsnCd_DL.getValue());
                    // START 2023/10/18 Y.Ogura [QC#61793, MOD]
//                    ZYPEZDItemValueSetter.setValue(inMsg.rtlWhCd, fMsg.rtlWhCd_DL.getValue());
                    if (fMsg.rtlWhCd_DL.getValue() != ""){
                        ZYPEZDItemValueSetter.setValue(inMsg.rtlWhCd, fMsg.rtlWhCd_DL.getValue());
                    }
                    // END 2023/10/18 Y.Ogura [QC#61793, MOD]
                    ZYPEZDItemValueSetter.setValue(inMsg.effFromDt, fMsg.effFromDt_DL.getValue());
                    ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glCmpCd);
                    // START 2023/10/18 Y.Ogura [QC#61793, ADD]
                    if (fMsg.rtlWhCatgNm_DL.getValue() != ""){
                        ZYPEZDItemValueSetter.setValue(inMsg.rtlWhCatgCd, rtlWhCatgCd);
                    }
                    if (fMsg.physWhCd_DL.getValue() != ""){
                        ZYPEZDItemValueSetter.setValue(inMsg.physWhCd, fMsg.physWhCd_DL.getValue());
                    }
                    
                    String scwpPk = NLBL3060CommonLogic.getSchdCoordWhPmsnPk(glCmpCd, rtlWhCatgCd,
                            fMsg.physWhCd_DL.getValue(),fMsg.rtlWhCd_DL.getValue(),fMsg.effFromDt_DL.getValue(),fMsg.schdCoordPsnCd_DL.getValue());
                    
                    if(scwpPk != null){
                        schdCoordWhPmsnPk = new BigDecimal(scwpPk);
                        ZYPEZDItemValueSetter.setValue(inMsg.schdCoordWhPmsnPk, schdCoordWhPmsnPk);
                    }
                    // END 2023/10/18 Y.Ogura [QC#61793, ADD]

                    SCHD_COORD_WH_PMSNTMsg outMsg = new SCHD_COORD_WH_PMSNTMsg();
                    outMsg = (SCHD_COORD_WH_PMSNTMsg) EZDTBLAccessor.findByKey(inMsg);

                    if (outMsg != null) {
                        effThruDt = outMsg.effThruDt.getValue();
                        ezUpTime = outMsg.ezUpTime.getValue();
                        ezUpTimeZone = outMsg.ezUpTimeZone.getValue();
                        isGetSchdCoordWhPmsn = true;
                    }
                }

                // Set sMsg.A Detail
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(index).glblCmpyCd_A1, glCmpCd);
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(index).rtlWhCd_A1, fMsg.rtlWhCd_DL);
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(index).rtlWhNm_A1, rtlWhNm);
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(index).schdCoordPsnCd_A1, fMsg.schdCoordPsnCd_DL);
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(index).fullPsnNm_A1, permUsrNm);
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(index).effFromDt_A1, fMsg.effFromDt_DL);
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(index).effThruDt_A1, fMsg.effThruDt_DL);
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(index).ezUpTime_A1, ezUpTime);
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(index).ezUpTimeZone_A1, ezUpTimeZone);
                // START 2023/10/18 Y.Ogura [QC#61793, ADD]
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(index).rtlWhCatgCd_A1, rtlWhCatgCd);
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(index).physWhCd_A1, fMsg.physWhCd_DL);
                if (schdCoordWhPmsnPk != null){
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(index).schdCoordWhPmsnPk_A1, schdCoordWhPmsnPk);
                }else{
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(index).schdCoordWhPmsnPk_A1, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SCHD_COORD_WH_PMSN_SQ));
                }
                // END 2023/10/18 Y.Ogura [QC#61793, ADD]

                globalMsg.A.setValidCount(index + 1);

                // Set sMsg.B Detail
                if (isGetSchdCoordWhPmsn) {
                    ZYPEZDItemValueSetter.setValue(globalMsg.B.no(index).glblCmpyCd_B1, glCmpCd);
                    ZYPEZDItemValueSetter.setValue(globalMsg.B.no(index).rtlWhCd_B1, fMsg.rtlWhCd_DL);
                    ZYPEZDItemValueSetter.setValue(globalMsg.B.no(index).rtlWhNm_B1, rtlWhNm);
                    ZYPEZDItemValueSetter.setValue(globalMsg.B.no(index).schdCoordPsnCd_B1, fMsg.schdCoordPsnCd_DL);
                    ZYPEZDItemValueSetter.setValue(globalMsg.B.no(index).fullPsnNm_B1, permUsrNm);
                    ZYPEZDItemValueSetter.setValue(globalMsg.B.no(index).effFromDt_B1, fMsg.effFromDt_DL);
                    ZYPEZDItemValueSetter.setValue(globalMsg.B.no(index).effThruDt_B1, effThruDt);
                    ZYPEZDItemValueSetter.setValue(globalMsg.B.no(index).ezUpTime_B1, ezUpTime);
                    ZYPEZDItemValueSetter.setValue(globalMsg.B.no(index).ezUpTimeZone_B1, ezUpTimeZone);
                    // START 2023/10/18 Y.Ogura [QC#61793, ADD]
                    ZYPEZDItemValueSetter.setValue(globalMsg.B.no(index).rtlWhCatgCd_B1, rtlWhCatgCd);
                    ZYPEZDItemValueSetter.setValue(globalMsg.B.no(index).physWhCd_B1, fMsg.physWhCd_DL);
                    ZYPEZDItemValueSetter.setValue(globalMsg.B.no(index).schdCoordWhPmsnPk_B1, schdCoordWhPmsnPk);
                    // END 2023/10/18 Y.Ogura [QC#61793, ADD]
                    
                    globalMsg.B.setValidCount(index + 1);
                }

                index++;
            }
        } finally {
            mappedFile.close();
            bizMsg.xxFileData_UP.deleteTempFile();

            ZYPTableUtil.clear(bizMsg.A);

            // Copy sMsg.A to cMsg.A
            int i = 0;
            for (; i < globalMsg.A.getValidCount(); i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);
            }
            bizMsg.A.setValidCount(i);

            // Setting Next Page
            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum.setValue(globalMsg.A.getValidCount());

            // Commit sMsg
            bizMsg.setCommitSMsg(true);
        }

        EZDDebugOutput.println(1, "----- doProcess_NLBL3060_OnClick_Upload END ----- ", this);
    }

    private void doProcess_NLBL3060_CMN_Download(NLBL3060CMsg bizMsg, NLBL3060SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLBL3060_CMN_Download START ----- ", this);

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            S21SsmLowLevelCodingClient ssmLLClient =
                S21SsmLowLevelCodingClient.getClient(NLBL3060Query.getInstance().getClass());

            // create csv file, parameters
            bizMsg.xxFileData_DL.setTempFilePath(
                    null
                    , ZYPCSVOutFile.createCSVOutFileNm(CSV_DL_FILE_NAME)
                    , CSV_FILE_EXTENSION);

            // get WH perms
            Map<String, Object> prmMap = new HashMap<String, Object>();
            // START 2023/10/18 Y.Ogura [QC#61793, ADD]
            prmMap.put("rtlWhCatgCd", bizMsg.rtlWhCatgCd_HD.getValue());
            prmMap.put("physWhCd", bizMsg.physWhCd_HD.getValue());
            // END 2023/10/18 Y.Ogura [QC#61793, ADD]
            prmMap.put("glblCmpyCd", getGlobalCompanyCode());
            prmMap.put("rtlWhCd", bizMsg.rtlWhCd.getValue());
            prmMap.put("schdCoordPsnCd", bizMsg.schdCoordPsnCd.getValue());
            prmMap.put("xxChkBox", bizMsg.xxChkBox.getValue());
            prmMap.put("slsDt", ZYPDateUtil.getSalesDate());

            ps = ssmLLClient.createPreparedStatement("getMasterTable", prmMap, execParam);
            rs = ps.executeQuery();

            // write CSV
            NLBL3060F00FMsg fMsg = new NLBL3060F00FMsg();
            ZYPCSVOutFile csvOutFile =
                new ZYPCSVOutFile(bizMsg.xxFileData_DL.getTempFilePath(), fMsg);
            fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));

            // write header
            csvOutFile.writeHeader(CSV_HDR_DOWNLOAD, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));

            // START 2023/10/18 Y.Ogura [QC#61793, ADD]
            Map<String, String> rtlWhCatgCdMap = getRtlWhCatgCdMap(getGlobalCompanyCode(), DOWNLOAD_NAME);
            // END 2023/10/18 Y.Ogura [QC#61793, ADD]

            // write contents
            String rtlWhNm = "";
            while (rs.next()) {
                // too many search results
                if (rs.getRow() >= LIMIT_DL_ROWNUM) {
                    bizMsg.setMessageInfo(MESSAGE_ID.NZZM0001W.toString());
                    break;
                }

                rtlWhNm =
                    "*".equals(rs.getString("RTL_WH_CD")) ? "All Warehouse" : rs.getString("RTL_WH_NM");

                // set result
                // START 2023/10/18 Y.Ogura [QC#61793, ADD]
                ZYPEZDItemValueSetter.setValue(fMsg.rtlWhCatgNm_DL, rtlWhCatgCdMap.get(rs.getString("RTL_WH_CATG_CD")));
                ZYPEZDItemValueSetter.setValue(fMsg.physWhCd_DL, rs.getString("PHYS_WH_CD"));
                // END 2023/10/18 Y.Ogura [QC#61793, ADD]
                ZYPEZDItemValueSetter.setValue(fMsg.rtlWhCd_DL, rs.getString("RTL_WH_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm_DL, rtlWhNm);
                ZYPEZDItemValueSetter.setValue(fMsg.schdCoordPsnCd_DL, rs.getString("SCHD_COORD_PSN_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.fullPsnNm_DL, rs.getString("FULL_PSN_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.effFromDt_DL, rs.getString("EFF_FROM_DT"));
                ZYPEZDItemValueSetter.setValue(fMsg.effThruDt_DL, rs.getString("EFF_THRU_DT"));

                csvOutFile.write();
            }

            csvOutFile.close();

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

        EZDDebugOutput.println(1, "----- doProcess_NLBL3060_CMN_Download END ----- ", this);
    }
    // END   2023/04/13 T.Kuroda [QC#61166, ADD]
    
    // START 2023/10/18 Y.Ogura [QC#61793, ADD]
    /**
     * getInitialData
     * @param cMsg NLBL3060CMsg
     * @param searchFlg boolean
     */
    private static void setInitialData(NLBL3060CMsg cMsg, boolean searchFlg) {

        // Create PullDown List
        createPullDownList(cMsg, searchFlg);

    }
    
    /**
     * createPullDownList
     * @param cMsg NLBL3060CMsg
     * @param searchFlg boolean
     */
    private static void createPullDownList(NLBL3060CMsg cMsg, boolean searchFlg) {

        setWhTypeList(cMsg);

    }
    
    /**
     * getBusinessUnitList
     * @param cMsg NLBL3060CMsg
     */
    private static void setWhTypeList(NLBL3060CMsg cMsg) {
        
        cMsg.rtlWhCatgCd_PD.clear();
        cMsg.rtlWhCatgNm_PD.clear();

        S21SsmEZDResult ssmResult = NLBL3060Query.getInstance().getWhTypeList(cMsg);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
           
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_PD.no(i), (String) resultMap.get("RTL_WH_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgNm_PD.no(i), (String) resultMap.get("RTL_WH_CATG_NM"));
            }
        }
    }

    /**
     * getRtlWhCatgCdMap
     * @param glblCmpyCd glblCmpyCd
     * @param eventName 
     */
    private static Map<String, String> getRtlWhCatgCdMap(String glblCmpyCd, String eventName) {
        
        Map<String, String> resultMap = new HashMap<String, String>();
        S21SsmEZDResult ssmResult = NLBL3060Query.getInstance().getRtlWhCatgCdList(glblCmpyCd);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
           
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> result = (Map<String, String>) resultList.get(i);
                if (eventName.equals(UPLOAD_NAME)) {
                    resultMap.put(result.get("RTL_WH_CATG_NM"), result.get("RTL_WH_CATG_CD"));
                } else if (eventName.equals(DOWNLOAD_NAME)) {
                    resultMap.put(result.get("RTL_WH_CATG_CD"), result.get("RTL_WH_CATG_NM"));
                }
            }
        }
        return resultMap;
    }
    // END 2023/10/18 Y.Ogura [QC#61793, ADD]

}
