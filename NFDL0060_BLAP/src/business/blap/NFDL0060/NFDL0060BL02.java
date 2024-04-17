/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0060;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NFDL0060.common.NFDL0060CommonLogic;
import business.blap.NFDL0060.constant.NFDL0060Constant;
import business.file.NFDL0060F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_DISP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/17   Fujitsu         Kamide          Create          N/A
 * 2016/06/30   Hitachi         K.Kojima        Update          QC#11088
 * 2016/07/01   Hitachi         K.Kojima        Update          QC#11089
 * 2016/07/28   Hitachi         K.Kojima        Update          QC#10260
 * 2016/08/22   Hitachi         K.Kojima        Update          QC#13331
 * 2016/09/13   Hitachi         K.Kojima        Update          QC#13257
 * 2017/07/31   Hitachi         T.Tsuchida      Update          QC#19575
 * 2018/06/07   Hitachi         Y.Takeno        Update          QC#25781
 *</pre>
 */
public class NFDL0060BL02 extends S21BusinessHandler implements NFDL0060Constant {

    @Override
    protected void doProcess(EZDCMsg ezdCMsg, EZDSMsg ezdSMsg) {

        super.preDoProcess(ezdCMsg, ezdSMsg);

        try {
            NFDL0060CMsg cMsg = (NFDL0060CMsg) ezdCMsg;
            NFDL0060SMsg sMsg = (NFDL0060SMsg) ezdSMsg;
            String scrnAplId = cMsg.getScreenAplID();
            if ("NFDL0060_INIT".equals(scrnAplId)) {
                doProcess_NFDL0060_INIT(cMsg, sMsg);
                ZYPGUITableColumn.getColData((NFDL0060CMsg) cMsg, (NFDL0060SMsg) sMsg);
            } else if ("NFDL0060Scrn00_CMN_Clear".equals(scrnAplId)) {
                doProcess_NFDL0060Scrn00_CMN_Clear(cMsg, sMsg);
                // START 2016/07/01 K.Kojima [QC#11089,ADD]
                ZYPGUITableColumn.getColData((NFDL0060CMsg) cMsg, (NFDL0060SMsg) sMsg);
                // END 2016/07/01 K.Kojima [QC#11089,ADD]
            } else if ("NFDL0060Scrn00_CMN_Reset".equals(scrnAplId)) {
                doProcess_NFDL0060_INIT(cMsg, sMsg);
                // START 2016/07/01 K.Kojima [QC#11089,ADD]
                ZYPGUITableColumn.getColData((NFDL0060CMsg) cMsg, (NFDL0060SMsg) sMsg);
                // END 2016/07/01 K.Kojima [QC#11089,ADD]
            } else if ("NFDL0060Scrn00_CMN_Download".equals(scrnAplId)) {
                doProcess_NFDL0060Scrn00_CMN_Download(cMsg, sMsg);
                // START 2016/07/28 K.Kojima [QC#10260,DEL]
                // } else if
                // ("NFDL0060Scrn00_OnChangeDispTp".equals(scrnAplId))
                // {
                // doProcess_NFDL0060Scrn00_OnChangeDispTp(cMsg,
                // sMsg);
                // END 2016/07/28 K.Kojima [QC#10260,DEL]
            } else if ("NFDL0060Scrn00_PageNext".equals(scrnAplId)) {
                doProcess_NFDL0060Scrn00_PageNext(cMsg, sMsg);
            } else if ("NFDL0060Scrn00_PagePrev".equals(scrnAplId)) {
                doProcess_NFDL0060Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NFDL0060Scrn00_Search".equals(scrnAplId)) {
                doProcess_NFDL0060_Search(cMsg, sMsg);
            } else if ("NFDL0060Scrn00_TBLColumnSort".equals(scrnAplId)) {
                doProcess_NFDL0060Scrn00_TBLColumnSort(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + scrnAplId);
            }
        } finally {
            super.postDoProcess(ezdCMsg, ezdSMsg);
        }
    }

    private void doProcess_NFDL0060_INIT(NFDL0060CMsg cMsg, NFDL0060SMsg sMsg) {
        doProcess_NFDL0060Scrn00_CMN_Clear(cMsg, sMsg);

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
    }

    private void doProcess_NFDL0060Scrn00_CMN_Clear(NFDL0060CMsg cMsg, NFDL0060SMsg sMsg) {
        cMsg.clear();
        sMsg.clear();
        
        // START 2016/07/28 K.Kojima [QC#10260,MOD]
        // ZYPCodeDataUtil.createPulldownList(CLT_DISP_TP.class,
        // cMsg.cltDispTpCd_H1, cMsg.cltDispTpDescTxt_H2);
        ZYPCodeDataUtil.createPulldownList(CLT_DISP_TP.class, cMsg.cltDispTpCd_H1, cMsg.cltDispTpDescTxt_H2, ":");
        // END 2016/07/28 K.Kojima [QC#10260,MOD]
        ZYPCodeDataUtil.createPulldownList(CLT_ITEM_TP.class, cMsg.cltItemTpCd_H1, cMsg.cltItemTpDescTxt_H2);

        ZYPEZDItemValueSetter.setValue(cMsg.cltDispTpCd_H3, CLT_DISP_TP.SELF);

        // START 2016/07/08 K.Kojima [QC#11417,DEL]
        // if (NFDL0060CommonLogic.isManager(getGlobalCompanyCode(),
        // getContextUserInfo().getUserId())) {
        // ZYPEZDItemValueSetter.setValue(cMsg.xxEdtModeFlg,
        // ZYPConstant.FLG_ON_Y);
        // } else {
        // ZYPEZDItemValueSetter.setValue(cMsg.xxEdtModeFlg,
        // ZYPConstant.FLG_OFF_N);
        // }
        // END 2016/07/08 K.Kojima [QC#11417,DEL]

        // START 2016/08/22 K.Kojima [QC#13331,ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem50Txt_TS, ZYPCodeDataUtil.getName(CLT_ITEM_TP.class, getGlobalCompanyCode(), CLT_ITEM_TP.TASK));
        ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem50Txt_WI, ZYPCodeDataUtil.getName(CLT_ITEM_TP.class, getGlobalCompanyCode(), CLT_ITEM_TP.WORK_ITEM));
        // END 2016/08/22 K.Kojima [QC#13331,ADD]
    }

    private void doProcess_NFDL0060Scrn00_CMN_Download(NFDL0060CMsg cMsg, NFDL0060SMsg sMsg) {
        // START 2016/06/30 K.Kojima [QC#11088,ADD]
        cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm("Open Status Item List Inquiry"), ".csv");

        NFDL0060F00FMsg fMsg = new NFDL0060F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));

        // START 2017/07/31 T.Tsuchida [QC#19575,MOD]
        csvOutFile.writeHeader(new String[] {"Item Type", "Work Item#", "Date", "Strategy Name", "Item Name", "Owner", "Portfolio Name", "Type", "Status", "Priority", "Account#", "Account Name", "Created By", "$Amount Overdue" }, fMsg, ZYPGUITableColumn
                .getColOrder(cMsg));
        // END 2017/07/31 T.Tsuchida [QC#19575,MOD]

        if (cMsg.A.getValidCount() > 0) {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(fMsg.cltItemTpDescTxt_A1, sMsg.A.no(i).cltItemTpDescTxt_A1);
                ZYPEZDItemValueSetter.setValue(fMsg.cltTaskPk_A1, sMsg.A.no(i).cltTaskPk_A1);
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_A1, ZYPDateUtil.formatEzd8ToDisp(sMsg.A.no(i).cltTaskRwsdDt_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(fMsg.cltStrgyNm_A1, sMsg.A.no(i).cltStrgyNm_A1);
                ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem130Txt_A1, sMsg.A.no(i).xxScrItem130Txt_A1);
                ZYPEZDItemValueSetter.setValue(fMsg.cltPsnNm_A1, sMsg.A.no(i).cltPsnNm_A1);
                // START 2017/07/31 T.Tsuchida [QC#19575,ADD]
                ZYPEZDItemValueSetter.setValue(fMsg.cltPtfoNm_A1, sMsg.A.no(i).cltPtfoNm_A1);
                // END 2017/07/31 T.Tsuchida [QC#19575,ADD]
                ZYPEZDItemValueSetter.setValue(fMsg.cltTaskTpDescTxt_A1, sMsg.A.no(i).cltTaskTpDescTxt_A1);
                ZYPEZDItemValueSetter.setValue(fMsg.cltTaskStsDescTxt_A1, sMsg.A.no(i).cltTaskStsDescTxt_A1);
                ZYPEZDItemValueSetter.setValue(fMsg.cltTaskPrtyDescTxt_A1, sMsg.A.no(i).cltTaskPrtyDescTxt_A1);
                // START 2017/07/31 T.Tsuchida [QC#19575,ADD]
                ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNum_A1, sMsg.A.no(i).dsAcctNum_A1);
                // END 2017/07/31 T.Tsuchida [QC#19575,ADD]
                ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_A1, sMsg.A.no(i).dsAcctNm_A1);
                ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem130Txt_A3, sMsg.A.no(i).xxScrItem130Txt_A3);
                ZYPEZDItemValueSetter.setValue(fMsg.xxTotAmt_A1, sMsg.A.no(i).xxTotAmt_A1.getValue().setScale(2));
                csvOutFile.write();
            }
        }
        csvOutFile.close();
        // END 2016/06/30 K.Kojima [QC#11088,ADD]
    }

    // START 2016/07/28 K.Kojima [QC#10260,DEL]
    // private void
    // doProcess_NFDL0060Scrn00_OnChangeDispTp(NFDL0060CMsg cMsg,
    // NFDL0060SMsg sMsg) {
    // }
    // END 2016/07/28 K.Kojima [QC#10260,DEL]

    private void doProcess_NFDL0060_Search(NFDL0060CMsg cMsg, NFDL0060SMsg sMsg) {

        // START 2016/07/08 K.Kojima [QC#11417,ADD]
        if (!ZYPCommonFunc.hasValue(cMsg.cltDispTpCd_H3)) {
            // START 2016/09/13 K.Kojima [QC#13257,MOD]
            // if(!ZYPCommonFunc.hasValue(cMsg.dsAcctNum_H1) &&
            // !ZYPCommonFunc.hasValue(cMsg.dsAcctNm_H1)) {
            // cMsg.dsAcctNum_H1.setErrorInfo(1, NFCM0850E);
            // START 2018/06/07 [QC#25781, DEL]
            // if (!ZYPCommonFunc.hasValue(cMsg.xxQueryFltrTxt_H2) && !ZYPCommonFunc.hasValue(cMsg.dsAcctNm_H1)) {
            //     cMsg.xxQueryFltrTxt_H2.setErrorInfo(1, NFCM0850E);
            //     // END 2016/09/13 K.Kojima [QC#13257,MOD]
            //     cMsg.dsAcctNm_H1.setErrorInfo(1, NFCM0850E);
            //     return;
            // }
            // END   2018/06/07 [QC#25781, DEL]
        }
        // END 2016/07/08 K.Kojima [QC#11417,ADD]

        // START 2018/06/07 [QC#25781, ADD]
        // END   2018/06/07 [QC#25781, ADD]
        
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        int count = 0;

        String salesDate = ZYPDateUtil.getSalesDate(getGlobalCompanyCode());
        String userId = getContextUserInfo().getUserId();
        S21SsmEZDResult ssmRslt = NFDL0060Query.getInstance().getTaskList(cMsg, sMsg, salesDate, userId);
        if (ssmRslt.isCodeNormal()) {
            int maxRowNum = getMaxRowNum(sMsg);
            count = ssmRslt.getQueryResultCount();
            if (count > maxRowNum) {
                cMsg.setMessageInfo(NZZM0001W);
                count = maxRowNum;
            }
        } else if (ssmRslt.isCodeNotFound()) {
            cMsg.setMessageInfo(ZZZM9005W);
            return;
        }

        sMsg.A.setValidCount(count);

        int startIndex = 0;

        NFDL0060CommonLogic.paginate(cMsg, sMsg, startIndex);
    }

    private void doProcess_NFDL0060Scrn00_PageNext(NFDL0060CMsg cMsg, NFDL0060SMsg sMsg) {

        int startIndex = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;

        NFDL0060CommonLogic.paginate(cMsg, sMsg, startIndex);
    }

    private void doProcess_NFDL0060Scrn00_PagePrev(NFDL0060CMsg cMsg, NFDL0060SMsg sMsg) {

        int startIndex = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;

        NFDL0060CommonLogic.paginate(cMsg, sMsg, startIndex);
    }

    private void doProcess_NFDL0060Scrn00_TBLColumnSort(NFDL0060CMsg cMsg, NFDL0060SMsg sMsg) {

        NFDL0060CommonLogic.sort(sMsg.A, sMsg.A.no(0).getBaseContents(), cMsg.xxSortItemNm.getValue(), cMsg.xxSortOrdByTxt.getValue());

        int startIndex = 0;

        NFDL0060CommonLogic.paginate(cMsg, sMsg, startIndex);
    }

    private int getMaxRowNum(NFDL0060SMsg sMsg) {
        return sMsg.A.length();
    }

}
