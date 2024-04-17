/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6760;

import static business.servlet.NMAL6760.constant.NMAL6760Constant.BIZ_ID;
import static business.servlet.NMAL6760.constant.NMAL6760Constant.FUNC_CD_20;
import static business.servlet.NMAL6760.constant.NMAL6760Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6760.NMAL6760CMsg;
import business.servlet.NMAL6760.common.NMAL6760CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL6760Scrn00_Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/21/2017   Fujitsu         K.Ishizuka      Update          QC#17610
 * 2017/08/07   Hitachi         T.Tsuchida      Update          QC#19574
 *</pre>
 */
public class NMAL6760Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6760BMsg scrnMsg = (NMAL6760BMsg) bMsg;
        checkItemAttribute(scrnMsg);

        NMAL6760CommonLogic.checkMandatorySearchCondition(scrnMsg);

        NMAL6760CommonLogic.checkWildCardOnly(scrnMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6760BMsg scrnMsg = (NMAL6760BMsg) bMsg;

        NMAL6760CMsg bizMsg = new NMAL6760CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6760BMsg scrnMsg = (NMAL6760BMsg) bMsg;
        NMAL6760CMsg bizMsg = (NMAL6760CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (!ZYPCommonFunc.hasValue(scrnMsg.dsAcctGrpCd_DP)) {
            scrnMsg.dsAcctGrpDescTxt_DP.clear();
        }
        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NMAL6760CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.C, "C");
    }

    private void checkItemAttribute(NMAL6760BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.xxAcctSrchModeCd_D1);
        scrnMsg.addCheckItem(scrnMsg.xxAcctSrchStsCd_D2);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_01);
        scrnMsg.addCheckItem(scrnMsg.xxAcctSrchDplyHrchCd_D3);

        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_01);
        scrnMsg.addCheckItem(scrnMsg.xxAllLineAddr_01);
        scrnMsg.addCheckItem(scrnMsg.ctyAddr_01);
        scrnMsg.addCheckItem(scrnMsg.stCd_DP);
        scrnMsg.addCheckItem(scrnMsg.postCd_01);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_01);
        scrnMsg.addCheckItem(scrnMsg.locNum_01);
        scrnMsg.addCheckItem(scrnMsg.dbaNm_01);
        scrnMsg.addCheckItem(scrnMsg.dsAcctLegalNm_01);
        scrnMsg.addCheckItem(scrnMsg.dsAcctGrpCd_DP);
        scrnMsg.addCheckItem(scrnMsg.dsAcctClsCd_DP);
        scrnMsg.addCheckItem(scrnMsg.dsLocNm_01);

        scrnMsg.addCheckItem(scrnMsg.dsAcctDunsNum_01);
        scrnMsg.addCheckItem(scrnMsg.dsUltDunsNum_01);
        scrnMsg.addCheckItem(scrnMsg.dsCustSicDescTxt_01);

        scrnMsg.addCheckItem(scrnMsg.ctacPsnLastNm_01);
        scrnMsg.addCheckItem(scrnMsg.ctacPsnFirstNm_01);
        scrnMsg.addCheckItem(scrnMsg.ctacPsnTelNum_01);
        // START 2017/08/07 T.Tsuchida [QC#19574,ADD]
        scrnMsg.addCheckItem(scrnMsg.ctacPsnEmlAddr_01);
        // END 2017/08/07 T.Tsuchida [QC#19574,ADD]
        scrnMsg.addCheckItem(scrnMsg.dsXrefAcctTpCd_DP);
        scrnMsg.addCheckItem(scrnMsg.dsXrefAcctCd_01);
        scrnMsg.addCheckItem(scrnMsg.billToCustCd_01);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd_01);

        scrnMsg.addCheckItem(scrnMsg.dsXtrnlRefTxt_01);
        scrnMsg.addCheckItem(scrnMsg.dsAcctTpCd_DP);
        scrnMsg.addCheckItem(scrnMsg.dsAcctItrlFlg_C1);

        scrnMsg.addCheckItem(scrnMsg.xxAcctSrchDplyRelnCd_D4);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_RT);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_RT);
        scrnMsg.addCheckItem(scrnMsg.xxAllLineAddr_RT);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_02);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_03);

        scrnMsg.putErrorScreen();
    }

}
