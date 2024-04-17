/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFDL0020.NFDL0020CMsg;
//import business.servlet.NFDL0020.constant.NFDL0020Constant;

import business.blap.NFDL0020.NFDL0020CMsg;
import business.servlet.NFDL0020.common.NFDL0020CommonLogic;
import static business.servlet.NFDL0020.constant.NFDL0020Constant.SCRN_ID_00;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Collection Detail Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/07/07   Hitachi         K.Kojima        Update          QC#10337
 * 2016/07/19   Hitachi         K.Kojima        Update          QC#11478
 * 2017/01/18   Fujitsu         T.Murai         Update          QC#16809
 * 2018/04/03   Hitachi         J.Kim           Update          QC#24729
 * 2018/05/16   Fujitsu         Y.Matsui        Update          QC#24329
 * 2018/06/05   Hitachi         Y.Takeno        Update          QC#26107
 * 2018/06/21   Hitachi         Y.Takeno        Update          QC#25080
 * 2018/07/25   Hitachi         Y.Takeno        Update          QC#25767
 * 2019/02/12   Fujitsu         S.Ohki          Update          QC#30143
 *</pre>
 */
public class NFDL0020Scrn00_Click_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.billToCustCd_H);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;

        NFDL0020CMsg bizMsg = new NFDL0020CMsg();
        bizMsg.setBusinessID("NFDL0020");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        NFDL0020CMsg bizMsg  = (NFDL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.billToCustCd_H);
        scrnMsg.putErrorScreen();
        if (scrnMsg.xxDplyTab_H.getValue().equals("Note")) {
            // START 2018/06/21 [QC#25080, MOD]
            // scrnMsg.arCltNoteTpCd_FH.setInputProtected(true);
            scrnMsg.cltNoteTpCd_FH.setInputProtected(true);
            scrnMsg.cratTsDplyTxt_FH.setInputProtected(true);
            // END   2018/06/21 [QC#25080, MOD]
            scrnMsg.colNoteSubjTxt_FH.setInputProtected(true);
            // START 2018/04/03 J.Kim [QC#25096,MOD]
            // scrnMsg.dtlNoteTxt_FH.setInputProtected(true);
            scrnMsg.xxMlBodyTxt_FH.setInputProtected(true);
            // END 2018/04/03 J.Kim [QC#25096,MOD]
        } else  if (scrnMsg.xxDplyTab_H.getValue().equals("Task")) {
            scrnMsg.cltTaskPk_GH.setInputProtected(true);
            scrnMsg.cltTaskStsCd_GH.setInputProtected(false);
            scrnMsg.cltTaskTpCd_GH.setInputProtected(true);
            scrnMsg.cltTaskPrtyCd_GH.setInputProtected(true);
            scrnMsg.cltTaskOwnrId_GH.setInputProtected(true);
            // START 2016/07/07 K.Kojima [QC#10337,MOD]
            // scrnMsg.xxPsnNm_G1.setInputProtected(true);
            scrnMsg.cltPsnNm_G1.setInputProtected(true);
            // END 2016/07/07 K.Kojima [QC#10337,MOD]
            scrnMsg.cratUsrId_GH.setInputProtected(true);
            scrnMsg.xxPsnNm_G2.setInputProtected(true);
            // START 2018/07/25 [QC#25767, ADD]
            scrnMsg.updUsrId_GH.setInputProtected(true);
            scrnMsg.xxPsnNm_G5.setInputProtected(true);
            scrnMsg.cltTaskUpdDt_GH.setInputProtected(true);
            // END   2018/07/25 [QC#25767, ADD]
            scrnMsg.cltTaskDescTxt_GH.setInputProtected(true);
            scrnMsg.cltTaskSubjTxt_GH.setInputProtected(true);
            scrnMsg.cltTaskRwsdDt_GH.setInputProtected(true);
            scrnMsg.cltTaskRwedDt_GH.setInputProtected(true);
            scrnMsg.cltTaskCtacNm_GH.setInputProtected(true);
            scrnMsg.cltTaskCtacPhoNum_GH.setInputProtected(true);
        } else  if (scrnMsg.xxDplyTab_H.getValue().equals("Strategy")) {
            NFDL0020CommonLogic.setTabStrategyEnabled(this, scrnMsg);
        }
        // START 2016/07/19 K.Kojima [QC#11478,ADD]
        NFDL0020CommonLogic.setInputProtected_B(scrnMsg);
        S21SortColumnIMGController.clearIMG("NFDL0020Scrn00", scrnMsg, scrnMsg.B.no(0).getBaseContents());
        // END 2016/07/19 K.Kojima [QC#11478,ADD]

        // START 2017/01/17 T.Murai [QC#16809,ADD]
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxScrItem130Txt_A.setInputProtected(true);
            // START 2018/06/05 [QC#26107, MOD]
            // if (scrnMsg.A.no(i).arTrxTpCd_A.getValue().equals(AR_TRX_TP.INVOICE) || scrnMsg.A.no(i).arTrxTpCd_A.getValue().equals(AR_TRX_TP.CREDIT_MEMO) || scrnMsg.A.no(i).arTrxTpCd_A.getValue().equals(AR_TRX_TP.RECEIPT)) {
            if (scrnMsg.A.no(i).arTrxTpCd_A.getValue().equals(AR_TRX_TP.INVOICE) || scrnMsg.A.no(i).arTrxTpCd_A.getValue().equals(AR_TRX_TP.CREDIT_MEMO) || scrnMsg.A.no(i).arTrxTpCd_A.getValue().equals(AR_TRX_TP.RECEIPT)
                    || (scrnMsg.A.no(i).arTrxTpCd_A.getValue().equals(AR_TRX_TP.DEDUCTION) && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).eipRptRqstPk_A))) {
            // END   2018/06/05 [QC#26107, MOD]
                scrnMsg.A.no(i).xxLinkProt_A.setInputProtected(false);
                scrnMsg.A.no(i).xxLinkProt_A.setValue(ZYPConstant.FLG_ON_Y);
            } else {
                scrnMsg.A.no(i).xxLinkProt_A.setInputProtected(true);
                scrnMsg.A.no(i).xxLinkProt_A.clear();
            }
        }
        // END 2017/01/17 T.Murai [QC#16809,ADD]

        // START 2018/05/16 [QC#24329,ADD]
        if (scrnMsg.xxDplyTab_H.getValue().equals("Statement")) {
            NFDL0020CommonLogic.initializeStatementTab(scrnMsg);
            S21SortColumnIMGController.clearIMG("NFDL0020Scrn00", scrnMsg, scrnMsg.J.no(0).getBaseContents());
        }
        // END 2018/05/16 [QC#24329,ADD]
        // START 2019/02/12 S.Ohki [QC#30143,ADD]
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.F.no(0).getBaseContents());
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.G.no(0).getBaseContents());
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.B.no(0).getBaseContents());
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.C.no(0).getBaseContents());
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.H.no(0).getBaseContents());
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.J.no(0).getBaseContents());
        // END 2019/02/12 S.Ohki [QC#30143,ADD]
    }
}
