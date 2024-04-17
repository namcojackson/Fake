/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.servlet.NMAL6710;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6710.common.NMAL6710CommonLogic;
import business.servlet.NMAL6710.constant.NMAL6710Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 *  Account Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/20/2015   SRAA            K.Aratani       Create          N/A
 * 02/17/2016   Fujitsu         T.Murai         Update          CSA#2943
 * 03/03/2016   SRAA            Y.Chen          Update          QC#4527
 * 07/06/2016   SRAA            Y.Chen          Update          QC#11456
 * 08/18/2016   SRAA            Y.Chen          Update          QC#11268
 *</pre>
 */
public class NMAL6710Scrn00_CMN_Clear extends S21CommonHandler implements NMAL6710Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;

        scrnMsg.dsAcctNm_01.clear();
        scrnMsg.xxAllLineAddr_01.clear();
        // 02/17/2016 CSA#2943 Add Start
        scrnMsg.dsAcctTpCd_DP.clear();
        // 02/17/2016 CSA#2943 Add End
        scrnMsg.ctyAddr_01.clear();
        scrnMsg.postCd_01.clear();
        scrnMsg.dsAcctNum_01.clear();
        scrnMsg.dbaNm_01.clear();
        scrnMsg.dsAcctLegalNm_01.clear();
        scrnMsg.locNum_01.clear();
        scrnMsg.dsAcctDunsNum_01.clear();
        scrnMsg.dsUltDunsNum_01.clear();
        scrnMsg.dsCustSicDescTxt_01.clear();
        scrnMsg.ctacPsnLastNm_01.clear();
        scrnMsg.ctacPsnFirstNm_01.clear();
        scrnMsg.ctacPsnTelNum_01.clear();
        scrnMsg.dsXrefAcctCd_01.clear();
        scrnMsg.dsXtrnlRefTxt_01.clear();
        scrnMsg.dsAcctNum_DR.clear();
        scrnMsg.dsAcctNm_RT.clear();
        scrnMsg.dsAcctNum_RT.clear();
        scrnMsg.xxAllLineAddr_RT.clear();
        scrnMsg.xxChkBox_01.clear();
        // QC#6189
        // scrnMsg.xxAcctSrchModeCd_D1.setValue("01");
        scrnMsg.xxAcctSrchModeCd_D1.setValue(SEARCH_MODE_CD_QUICK);
        scrnMsg.xxAcctSrchStsCd_D2.setValue("01");
        scrnMsg.stCd_DP.clear();
        scrnMsg.dsAcctGrpCd_DP.clear();
        // 02/17/2016 CSA#2943 Add Start
        scrnMsg.dsAcctGrpDescTxt_DP.clear();
        // 02/17/2016 CSA#2943 Add End
        scrnMsg.dsAcctClsCd_DP.clear();
        scrnMsg.dsLocNm_01.clear();
        scrnMsg.dsXrefAcctTpCd_DP.clear();
        scrnMsg.dsAcctItrlFlg_C1.clear();
        scrnMsg.xxAcctSrchDplyHrchCd_D3.setValue("01");
        // QC#6189
        // QC#11456
        // scrnMsg.xxAcctSrchDplyRelnCd_D4.setValue(DISP_RELN_ACCT_CD_ACCT);
        scrnMsg.xxAcctSrchDplyRelnCd_D4.clear();

        // QC#4527
        scrnMsg.billToCustCd_01.clear();
        scrnMsg.shipToCustCd_01.clear();
        scrnMsg.xxChkBox_02.clear();
        scrnMsg.xxChkBox_03.clear();

        scrnMsg.C.clear();
        scrnMsg.C.setValidCount(0);
        scrnMsg.setTreeView(null);

        scrnMsg.setFocusItem(scrnMsg.dsAcctNm_01);
        scrnMsg.xxChkBox_01.setValue(ZYPConstant.CHKBOX_ON_Y);
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.xxRadioBtn.clear();
        
        // QC#11268
        NMAL6710CommonLogic.resetRowsBackgroundQuickMode(scrnMsg);
        
        scrnMsg.srchOptPk_S.clear();
        scrnMsg.srchOptNm_S.clear();
    }

}
