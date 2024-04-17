/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6710;

import static business.servlet.NMAL6710.constant.NMAL6710Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NMAL6710.NMAL6710CMsg;
import business.servlet.NMAL6710.common.NMAL6710CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 10/01/2015   Fujitsu         N.Sugiura       Update          CSA
 * 06/22/2016   SRAA            Y.Chen          Update          QC#6189
 * 06/30/2016   SRAA            Y.Chen          Update          QC#11265
 * 08/18/2016   SRAA            Y.Chen          Update          QC#11268
 *</pre>
 */
public class NMAL6710Scrn00_GoToAcct extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // QC#6189
        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;
        if(TAB_NM_HRCH_QUICK.equals(scrnMsg.xxDplyTab.getValue())){
            int selectIdx = getButtonSelectNumber();
            NMAL6710_CBMsg aBMsg = scrnMsg.C.no(selectIdx);
            scrnMsg.dsAcctNum_01.setValue(aBMsg.dsAcctNum_C1.getValue());
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // QC#6189
        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;
        if(TAB_NM_HRCH_QUICK.equals(scrnMsg.xxDplyTab.getValue())){
            NMAL6710CMsg bizMsg = new NMAL6710CMsg();
            bizMsg.setBusinessID(BUSINESS_ID);
            bizMsg.setFunctionCode(FUNCTION_CODE_SEARCH);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;    
        }
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;
        // QC#6189
        if(TAB_NM_HRCH_QUICK.equals(scrnMsg.xxDplyTab.getValue())){
            NMAL6710CMsg bizMsg = (NMAL6710CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            if(TAB_NM_HRCH.equals(scrnMsg.xxDplyTab.getValue())){
                scrnMsg.setTreeView(null);
                NMAL6710CommonLogic.setHirarchyTreeView(scrnMsg, bizMsg);
            }
            // QC#11265
            S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.C.no(0).getBaseContents());
            scrnMsg.clearAllGUIAttribute(SCREEN_ID);

            // QC#11268
            NMAL6710CommonLogic.resetRowsBackgroundQuickMode(scrnMsg);
            
            NMAL6710CommonLogic.controlDetailScreenFields(this, scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.dsAcctNm_01);
            
            this.setResult("hierarchy");
            
            return;
        } else {
            this.setResult("window");
        }
        
        int selectIdx = getButtonSelectNumber();
        NMAL6710_CBMsg aBMsg = scrnMsg.C.no(selectIdx);

        scrnMsg.P.clear();
        scrnMsg.P.no(0).xxPopPrm.setValue(aBMsg.dsAcctNum_C1.getValue());

        Object[] params = NMAL6710CommonLogic.toArrayPopup(scrnMsg.P, 2);

        setArgForSubScreen(params);

    }
}
