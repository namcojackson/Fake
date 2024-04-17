/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6710;

import static business.servlet.NMAL6710.constant.NMAL6710Constant.*;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL6710.NMAL6710CMsg;

import business.servlet.NMAL6710.common.NMAL6710CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.treeview.S21TreeView;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.fujitsu.uji.compo.TreeNodeRow;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 05/05/2016   SRAA            Y.Chen          Update          QC#7711
 * 06/22/2016   SRAA            Y.Chen          Update          QC#6189
 *</pre>
 */
public class NMAL6710Scrn00_OpenWin_Note extends S21CommonHandler {

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
        String billCustCd = "";
        String shipCustCd = "";

        // QC#6189
        // if (SEARCH_MODE_CD_HRCH.equals(scrnMsg.xxAcctSrchModeCd_D1.getValue())) {
        if (TAB_NM_HRCH.equals(scrnMsg.xxDplyTab.getValue())){
            S21TreeView treeView = scrnMsg.getTreeView();
            if (treeView != null) {

                List<TreeNodeRow> checkedNodeList = treeView.getCheckedTreeNodeRows(COLUMN_INDEX_RADIO_BUTTON);

                if (!checkedNodeList.isEmpty()) {
                    TreeNodeRow checkedNode = checkedNodeList.get(0);

                    dsAcctNum = (String) checkedNode.getValueAt(COLUMN_INDEX_DS_ACCT_NUM);
                    billCustCd = (String) checkedNode.getValueAt(COLUMN_INDEX_BILL_TO);
                    shipCustCd = (String) checkedNode.getValueAt(COLUMN_INDEX_SHIP_TO);
                    if (!ZYPCommonFunc.hasValue(dsAcctNum) && !ZYPCommonFunc.hasValue(billCustCd) && !ZYPCommonFunc.hasValue(shipCustCd)) {
                        String[] args = {"Ship To or Bill To or Account#" };
                        scrnMsg.setMessageInfo(NMAM8111E, args);
                        throw new EZDFieldErrorException();
                    }
                }
            }
        } else {
            int selectedRow = scrnMsg.xxRadioBtn.getValue().intValue();
            if (ZYPCommonFunc.hasValue(scrnMsg.C.no(selectedRow).shipToCustCd_C1)) {
                shipCustCd = scrnMsg.C.no(selectedRow).shipToCustCd_C1.getValue();

            } else if (ZYPCommonFunc.hasValue(scrnMsg.C.no(selectedRow).billToCustCd_C1)) {
                billCustCd = scrnMsg.C.no(selectedRow).billToCustCd_C1.getValue();

            } else if (ZYPCommonFunc.hasValue(scrnMsg.C.no(selectedRow).dsAcctNum_C1)) {
                dsAcctNum = scrnMsg.C.no(selectedRow).dsAcctNum_C1.getValue();

            }
        }

        scrnMsg.P.clear();
        scrnMsg.D.clear();
        scrnMsg.P.no(0).xxPopPrm.setValue(S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
        scrnMsg.P.no(1).xxPopPrm.setValue(BUSINESS_ID);
        Object[] params = NMAL6710CommonLogic.toArrayPopup(scrnMsg.P, 7);
        if (ZYPCommonFunc.hasValue(shipCustCd)) {
        scrnMsg.D.no(0).shipToCustCd_D.setValue(shipCustCd);
        } else if (ZYPCommonFunc.hasValue(billCustCd)) {
        scrnMsg.D.no(0).billToCustCd_D.setValue(billCustCd);
        } else if (ZYPCommonFunc.hasValue(dsAcctNum)) {
        scrnMsg.D.no(0).dsAcctNum_D.setValue(dsAcctNum);
        }
        params[5] = "D";
        params[6] = scrnMsg.D;

        setArgForSubScreen(params);

    }
}
