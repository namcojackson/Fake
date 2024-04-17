/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0300.NSAL0300CMsg;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;
import business.servlet.NSAL0300.constant.NSAL0300Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            SRAA            Create          N/A
 * 2015/10/22   Hitachi         T.Kanasaka      Update          N/A
 * 2016/01/21   Hitachi         T.Tomita        Create          QC#2182
 * 2016/02/24   Hitachi         T.Kanasaka      Update          QC4079
 * 2016/04/08   Hitachi         M.Gotou         Update          QC#5312,5313
 * 2016/04/26   Hitachi         T.Tomita        Update          QC#4668
 * 2016/05/27   Hitachi         T.Kanasaka      Update          QC#4092
 * 2016/07/01   Hitachi         T.Aoyagi        Update          QC#11261
 * 2016/07/28   Hitachi         T.Kanasaka      Update          QC#4806
 * 2017/01/27   Hitachi         Y.Takeno        Update          QC#17278
 * 2018/01/12   Hitachi         T.Tomita        Update          QC#18552
 * 2018/08/20   Hitachi         T.Tomita        Update          QC#26949
 * 2023/01/30   Hitachi         R.Takau         Update          QC#55645
 *</pre>
 */
public class NSAL0300_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        if ("OpenWin_Serial".equals(scrEventNm)) {
            for (int i = 0; i < scrnMsg.R.length(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.R.no(i).xxSelFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.serNum, scrnMsg.R.no(i).xxComnScrColValTxt);
                }
            }

            NSAL0300CMsg bizMsg = new NSAL0300CMsg();
            bizMsg.setBusinessID(NSAL0300Constant.BIZ_ID);
            bizMsg.setFunctionCode(NSAL0300Constant.EZD_FUNC_CD_INQ);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        // Add Start 2018/08/20 QC#26946
        } else if ("OpenWin_ServiceProgram".equals(scrEventNm) && !"CMN_Close".equals(getLastGuard())) {
            int selectIdx = getButtonSelectNumber();
            String svcPgmMdseCd = scrnMsg.R.no(0).xxComnScrColValTxt.getValue();
            if (ZYPCommonFunc.hasValue(svcPgmMdseCd)) {
                if (selectIdx > -1) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(selectIdx).svcPgmMdseCd_B, svcPgmMdseCd);
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.svcPgmMdseCd, svcPgmMdseCd);
                }
            }
            String mdseNm = scrnMsg.R.no(1).xxComnScrColValTxt.getValue();
            if (ZYPCommonFunc.hasValue(mdseNm)) {
                if (selectIdx > -1) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(selectIdx).mdseDescShortTxt_B, mdseNm);
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.mdseDescShortTxt_SP, mdseNm);
                }
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(selectIdx));

            NSAL0300CMsg bizMsg = new NSAL0300CMsg();
            bizMsg.setBusinessID(NSAL0300Constant.BIZ_ID);
            bizMsg.setFunctionCode(NSAL0300Constant.EZD_FUNC_CD_INQ);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        // Add End 2018/08/20 QC#26946
        //START 2023/01/30/ R.Takau [QC#55645,ADD]
        } else if("OpenWin_BankAccountSearch".equals(scrEventNm) && !"CMN_Close".equals(getLastGuard())){
            ZYPEZDItemValueSetter.setValue(scrnMsg.bankRteNum, scrnMsg.R.no(1).xxComnScrColValTxt);  
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankAcctNum, scrnMsg.R.no(2).xxComnScrColValTxt);   
            if(ZYPCommonFunc.hasValue(scrnMsg.R.no(3).xxComnScrColValTxt)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsCustBankAcctRelnPk, new BigDecimal(scrnMsg.R.no(3).xxComnScrColValTxt.getValue())); 
            }
            NSAL0300CMsg bizMsg = new NSAL0300CMsg();
            bizMsg.setBusinessID(NSAL0300Constant.BIZ_ID);
            bizMsg.setFunctionCode(NSAL0300Constant.EZD_FUNC_CD_INQ);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        //END 2023/01/30/ R.Takau [QC#55645,ADD]
        } else {
            return null;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // add start 2016/04/08 CSA Defect#5312,5313
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        if ("OpenWin_Rep".equals(scrEventNm) && ZYPCommonFunc.hasValue(scrnMsg.svcContrBrCd_WK)) {
            setValue(scrnMsg.svcContrBrCd, scrnMsg.svcContrBrCd_WK);
        }
        // add start 2016/04/08 CSA Defect#5312,5313

        if (!"CMN_Close".equals(getLastGuard())) {

            // START 2016/01/21 T.Tomita [QC#2182, MOD]
            if ("OpenWin_ReportGrp".equals(scrEventNm)) {
                String dsContrRptGrpNum = scrnMsg.R.no(0).xxComnScrColValTxt.getValue();
                if (ZYPCommonFunc.hasValue(dsContrRptGrpNum)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrRptGrpNum, dsContrRptGrpNum);
                }
                // START 2017/01/27 [QC#17278, MOD]
                // scrnMsg.setFocusItem(scrnMsg.dsContrRptGrpNum);
                String dsContrRptGrpDescTxt = scrnMsg.R.no(1).xxComnScrColValTxt.getValue();
                if (ZYPCommonFunc.hasValue(dsContrRptGrpDescTxt)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrRptGrpDescTxt, dsContrRptGrpDescTxt);
                }
                scrnMsg.setFocusItem(scrnMsg.dsContrRptGrpDescTxt);
                // END   2017/01/27 [QC#17278, MOD]
            } else if ("OpenWin_SalesRep".equals(scrEventNm)) {
                // START 2016/04/26 T.Tomita [QC#4668, MOD]
                String tocCd = scrnMsg.R.no(1).xxComnScrColValTxt.getValue();
                if (ZYPCommonFunc.hasValue(tocCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.tocCd, tocCd);
                }
                // START 2016/02/24 T.Kanasaka [QC4079, ADD]
                String tocNm = scrnMsg.R.no(2).xxComnScrColValTxt.getValue();
                if (ZYPCommonFunc.hasValue(tocNm)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.tocNm, tocNm);
                }
                // END 2016/02/24 T.Kanasaka [QC4079, ADD]
                scrnMsg.setFocusItem(scrnMsg.tocNm);
                // END 2016/04/26 T.Tomita [QC#4668, MOD]
            } else if ("OpenWin_Rep".equals(scrEventNm)) {
                String psnCd = scrnMsg.R.no(0).xxComnScrColValTxt.getValue();
                if (ZYPCommonFunc.hasValue(psnCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.contrAdminPsnCd, psnCd);
                }
                String psnNm = scrnMsg.R.no(1).xxComnScrColValTxt.getValue();
                if (ZYPCommonFunc.hasValue(psnNm)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxPsnNm, psnNm);
                }
            // START 2016/05/27 T.Kanasaka [QC#4092, ADD]
            } else if ("OpenWin_Serial".equals(scrEventNm)) {
                NSAL0300CMsg bizMsg  = (NSAL0300CMsg) cMsg;
                EZDMsg.copy(bizMsg, null, scrnMsg, null);
                NSAL0300CommonLogic.setupScreenItems(this, scrnMsg);
                scrnMsg.addCheckItem(scrnMsg.serNum);
                scrnMsg.putErrorScreen();
                if ("E".equals(bizMsg.getMessageKind())) {
                    throw new EZDFieldErrorException();
                }
                scrnMsg.setFocusItem(scrnMsg.serNum);
            // add start 2016/07/01 CSA Defect#11261
            } else if ("OpenWin_ServiceProgram".equals(scrEventNm)) {
                // Mod Start 2018/08/20 QC#26949
                int selectIdx = getButtonSelectNumber();

//                String svcPgmMdseCd = scrnMsg.R.no(0).xxComnScrColValTxt.getValue();
//                if (ZYPCommonFunc.hasValue(svcPgmMdseCd)) {
//                    if (selectIdx > -1) {
//                        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(selectIdx).svcPgmMdseCd_B, svcPgmMdseCd);
//                    } else {
//                        ZYPEZDItemValueSetter.setValue(scrnMsg.svcPgmMdseCd, svcPgmMdseCd);
//                    }
//                }
//                String mdseNm = scrnMsg.R.no(1).xxComnScrColValTxt.getValue();
//                if (ZYPCommonFunc.hasValue(mdseNm)) {
//                    if (selectIdx > -1) {
//                        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(selectIdx).mdseDescShortTxt_B, mdseNm);
//                    } else {
//                        ZYPEZDItemValueSetter.setValue(scrnMsg.mdseDescShortTxt_SP, mdseNm);
//                    }
//                }
//
                NSAL0300CMsg bizMsg  = (NSAL0300CMsg) cMsg;
                EZDMsg.copy(bizMsg, null, scrnMsg, null);
                if (selectIdx > -1) {
                    scrnMsg.addCheckItem(scrnMsg.B.no(selectIdx).svcPgmMdseCd_B);
                } else {
                    scrnMsg.addCheckItem(scrnMsg.mdseDescShortTxt_SP);
                }
                scrnMsg.putErrorScreen();
                if ("E".equals(bizMsg.getMessageKind())) {
                    throw new EZDFieldErrorException();
                }
                // Mod End 2018/08/20 QC#26949

                if (selectIdx > -1) {
                    // Mod Start 2018/01/12 QC#18552
                    scrnMsg.setFocusItem(scrnMsg.B.no(selectIdx).svcPgmMdseCd_B);
                    // Mod End 2018/01/12 QC#18552
                } else {
                    scrnMsg.setFocusItem(scrnMsg.mdseDescShortTxt_SP);
                }
            // add end 2016/07/01 CSA Defect#11261
            // END 2016/05/27 T.Kanasaka [QC#4092, ADD]
            }
            // END 2016/01/21 T.Tomita [QC#2182, MOD]
        }
    }
}
