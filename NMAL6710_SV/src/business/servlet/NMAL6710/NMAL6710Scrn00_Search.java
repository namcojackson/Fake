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
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6710.NMAL6710CMsg;
import business.servlet.NMAL6710.common.NMAL6710CommonLogic;
import business.servlet.NMAL6710.constant.NMAL6710Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 *  Account Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/20/2015   SRAA            K.Aratani       Create          N/A
 * 03/03/2016   SRAA            Y.Chen          Update          QC#4522
 * 05/05/2016   SRAA            Y.Chen          Update          QC#7711
 * 06/22/2016   SRAA            Y.Chen          Update          QC#6189
 * 08/18/2016   SRAA            Y.Chen          Update          QC#11265
 * 09/21/2016   Fujitsu         Mz.Takahashi    Update          QC#11068
 * 02/21/2017   Fujitsu         K.Ishizuka      Update          QC#17610
 *</pre>
 */
public class NMAL6710Scrn00_Search extends S21CommonHandler implements NMAL6710Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;
        NMAL6710CommonLogic.clearConfirmInfo(scrnMsg, ALL);
        scrnMsg.putErrorScreen();

        // QC#4522
        NMAL6710CommonLogic.checkItemAttribute(scrnMsg);

        // QC#6189
        NMAL6710CommonLogic.checkMandatorySearchCondition(scrnMsg);
        
        // QC#17610
        NMAL6710CommonLogic.checkWildCardOnly(scrnMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;

        NMAL6710CMsg bizMsg = new NMAL6710CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_CODE_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;
        NMAL6710CMsg bizMsg = (NMAL6710CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_RT);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_RT);
        scrnMsg.addCheckItem(scrnMsg.xxAllLineAddr_RT);
        scrnMsg.putErrorScreen();

        if (!ZYPCommonFunc.hasValue(scrnMsg.dsAcctGrpCd_DP)) {
            scrnMsg.dsAcctGrpDescTxt_DP.clear();
        }
        
        if (SEARCH_MODE_CD_HRCH.equals(scrnMsg.xxAcctSrchModeCd_D1.getValue())) {
            // QC#6189
            // scrnMsg.xxDplyTab.setValue(TAB_NM_HRCH);
            if(TAB_NM_HRCH.equals(scrnMsg.xxDplyTab.getValue())){
                scrnMsg.setTreeView(null);
                NMAL6710CommonLogic.setHirarchyTreeView(scrnMsg, bizMsg);
            }
        } else  if (SEARCH_MODE_CD_QUICK.equals(scrnMsg.xxAcctSrchModeCd_D1.getValue())) {
            scrnMsg.xxDplyTab.setValue(TAB_NM_QUICK);
        }
        
        // QC#11265
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.C.no(0).getBaseContents());
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        
        // QC#11268
        NMAL6710CommonLogic.resetRowsBackgroundQuickMode(scrnMsg);

        NMAL6710CommonLogic.controlDetailScreenFields(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.dsAcctNm_01);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

    }
}
