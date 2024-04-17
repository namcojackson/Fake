/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6710;

import static business.servlet.NMAL6710.constant.NMAL6710Constant.*;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NMAL6710.common.NMAL6710CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.treeview.S21TreeView;
import com.fujitsu.uji.compo.TreeNodeRow;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 05/05/2016   SRAA            Y.Chen          Update          QC#7711
 * 06/22/2016   SRAA            Y.Chen          Update          QC#6189
 * 2016/06/30   SRAA            Y.Chen          Update          QC#11265
 *</pre>
 */
public class NMAL6710Scrn00_OpenWin_LocInfo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;
// QC#6189
//        // QC#7711
//        if(!NMAL6710CommonLogic.checkSearchMode(scrnMsg)){
//            throw new EZDFieldErrorException();
//        }
        // QC#6189
        if(!ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn)){
            if (TAB_NM_QUICK.equals(scrnMsg.xxDplyTab.getValue()) || (TAB_NM_HRCH_QUICK.equals(scrnMsg.xxDplyTab.getValue())) ) {
                scrnMsg.setMessageInfo(NMAM8054E);
                throw new EZDFieldErrorException();
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;
        String dsAcctNum = "";
        String locNum = "";

        // QC#6189
        // if (SEARCH_MODE_CD_HRCH.equals(scrnMsg.xxAcctSrchModeCd_D1.getValue())) {
        if (TAB_NM_HRCH.equals(scrnMsg.xxDplyTab.getValue())){
            S21TreeView treeView = scrnMsg.getTreeView();
            if (treeView != null) {

                List<TreeNodeRow> checkedNodeList = treeView.getCheckedTreeNodeRows(COLUMN_INDEX_RADIO_BUTTON);

                if (!checkedNodeList.isEmpty()) {
                    TreeNodeRow checkedNode = checkedNodeList.get(0);

                    dsAcctNum = (String) checkedNode.getValueAt(COLUMN_INDEX_DS_ACCT_NUM);
                    locNum = (String) checkedNode.getValueAt(COLUMN_INDEX_LOC_NUM);
                    if (!ZYPCommonFunc.hasValue(dsAcctNum)) {
                        String[] args = {"Account#" };
                        scrnMsg.setMessageInfo(NMAM8111E, args);
                        throw new EZDFieldErrorException();
                    }
                    if (!ZYPCommonFunc.hasValue(locNum)) {
                        String[] args = {"Location#" };
                        scrnMsg.setMessageInfo(NMAM8111E, args);
                        throw new EZDFieldErrorException();
                    }
                }
            }
        } else {
            int selectedRow = scrnMsg.xxRadioBtn.getValue().intValue();
            // QC#11265
//            if (ZYPCommonFunc.hasValue(scrnMsg.C.no(selectedRow).dsAcctRelnTpNm_C1)) {
//                dsAcctNum = scrnMsg.C.no(selectedRow).relnDsAcctNum_C1.getValue();
//            } else {
//                dsAcctNum = scrnMsg.C.no(selectedRow).dsAcctNum_C1.getValue();
//            }
            dsAcctNum = scrnMsg.C.no(selectedRow).dsAcctNum_C1.getValue();
            locNum = scrnMsg.C.no(selectedRow).locNum_C1.getValue();
        }

        scrnMsg.P.clear();
        scrnMsg.P.no(0).xxPopPrm.setValue("01");

        scrnMsg.P.no(1).xxPopPrm.setValue(dsAcctNum);
        scrnMsg.P.no(2).xxPopPrm.setValue(locNum);

        Object[] params = NMAL6710CommonLogic.toArrayPopup(scrnMsg.P, 9);

        setArgForSubScreen(params);

        openMultiModeScreen("New Location");

    }
}
