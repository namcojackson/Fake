/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6710;

import static business.servlet.NMAL6710.constant.NMAL6710Constant.COLUMN_INDEX_DS_ACCT_NUM;
import static business.servlet.NMAL6710.constant.NMAL6710Constant.COLUMN_INDEX_RADIO_BUTTON;
import static business.servlet.NMAL6710.constant.NMAL6710Constant.NMAM8054E;
import static business.servlet.NMAL6710.constant.NMAL6710Constant.NMAM8111E;
import static business.servlet.NMAL6710.constant.NMAL6710Constant.TAB_NM_HRCH;
import static business.servlet.NMAL6710.constant.NMAL6710Constant.TAB_NM_HRCH_QUICK;
import static business.servlet.NMAL6710.constant.NMAL6710Constant.TAB_NM_QUICK;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
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
 *</pre>
 */
public class NMAL6710Scrn00_OpenWin_CtacSrch extends S21CommonHandler {

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

        scrnMsg.P.clear();
        Object[] params = new Object[20];
        params[0] = scrnMsg.P.no(0).xxPopPrm;
        params[1] = scrnMsg.P.no(1).xxPopPrm;
        params[2] = scrnMsg.P.no(2).xxPopPrm;
        params[4] = scrnMsg.P.no(4).xxPopPrm;
        params[5] = scrnMsg.P.no(5).xxPopPrm;
        params[6] = scrnMsg.P.no(6).xxPopPrm;
        params[7] = scrnMsg.P.no(7).xxPopPrm;
        params[8] = scrnMsg.P.no(8).xxPopPrm;
        params[9] = scrnMsg.P.no(9).xxPopPrm;
        params[10] = scrnMsg.P.no(10).xxPopPrm;
        params[11] = scrnMsg.P.no(11).xxPopPrm;
        params[12] = scrnMsg.P.no(12).xxPopPrm;
        params[13] = scrnMsg.P.no(13).xxPopPrm;
        params[14] = scrnMsg.P.no(14).xxPopPrm;
        params[18] = new ArrayList<Object>();

        // QC#6189
        // if (SEARCH_MODE_CD_HRCH.equals(scrnMsg.xxAcctSrchModeCd_D1.getValue())) {
        if (TAB_NM_HRCH.equals(scrnMsg.xxDplyTab.getValue())){
            S21TreeView treeView = scrnMsg.getTreeView();
            if (treeView != null) {

                List<TreeNodeRow> checkedNodeList = treeView.getCheckedTreeNodeRows(COLUMN_INDEX_RADIO_BUTTON);

                if (!checkedNodeList.isEmpty()) {
                    TreeNodeRow checkedNode = checkedNodeList.get(0);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, (String) checkedNode.getValueAt(COLUMN_INDEX_DS_ACCT_NUM));
                    if (!ZYPCommonFunc.hasValue(scrnMsg.P.no(2).xxPopPrm) && !ZYPCommonFunc.hasValue(scrnMsg.P.no(3).xxPopPrm) && !ZYPCommonFunc.hasValue(scrnMsg.P.no(4).xxPopPrm)) {
                        String[] args = {"Account#" };
                        scrnMsg.setMessageInfo(NMAM8111E, args);
                        throw new EZDFieldErrorException();
                    }
                    params[3] = scrnMsg.P.no(3).xxPopPrm;
                    params[15] = scrnMsg.C.no(0).dsCtacPntPk_C1;
                    params[16] = scrnMsg.C.no(0).dsCtacPntPk_C2;
                    params[17] = scrnMsg.C.no(0).dsCtacPntPk_C3;
                    params[19] = scrnMsg.C.no(0).ctacPsnPk_C1;
                }
            }
        } else {
            int selectedRow = scrnMsg.xxRadioBtn.getValue().intValue();
            params[3] = scrnMsg.C.no(selectedRow).dsAcctNum_C1;
            params[15] = scrnMsg.C.no(selectedRow).dsCtacPntPk_C1;
            params[16] = scrnMsg.C.no(selectedRow).dsCtacPntPk_C2;
            params[17] = scrnMsg.C.no(selectedRow).dsCtacPntPk_C3;
            params[19] = scrnMsg.C.no(selectedRow).ctacPsnPk_C1;
        }

        setArgForSubScreen(params);

    }
}
