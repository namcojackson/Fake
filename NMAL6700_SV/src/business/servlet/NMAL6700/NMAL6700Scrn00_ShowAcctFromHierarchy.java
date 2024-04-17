/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6700.common.NMAL6700CommonLogic;
import business.servlet.NMAL6700.constant.NMAL6700Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.treeview.S21TreeView;
import com.fujitsu.uji.compo.TreeNodeRow;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/07   Fujitsu         N.Sugiura       Update          #6633
 *</pre>
 */
public class NMAL6700Scrn00_ShowAcctFromHierarchy extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;

        S21TreeView treeView = scrnMsg.getTreeView();

        List<TreeNodeRow> checkedNodeList = treeView.getCheckedTreeNodeRows(10);

        if (!checkedNodeList.isEmpty()) {
            TreeNodeRow checkedNode = checkedNodeList.get(0);

            String dsAcctNum = (String) checkedNode.getValueAt(11);
            if (!ZYPCommonFunc.hasValue(dsAcctNum)) {
                String[] args = {"Account#" };
                scrnMsg.setMessageInfo(NMAL6700Constant.NMAM8111E, args);
                throw new EZDFieldErrorException();
            }
        }
        scrnMsg.putErrorScreen();
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;

        S21TreeView treeView = scrnMsg.getTreeView();
        List<TreeNodeRow> checkedNodeList = treeView.getCheckedTreeNodeRows(10);

        String dsAcctNum = "";
        if (!checkedNodeList.isEmpty()) {
            TreeNodeRow checkedNode = checkedNodeList.get(0);

            dsAcctNum = (String) checkedNode.getValueAt(11);
            if (!ZYPCommonFunc.hasValue(dsAcctNum)) {
                String[] args = {"Account#" };
                scrnMsg.setMessageInfo(NMAL6700Constant.NMAM8111E, args);
                throw new EZDFieldErrorException();
            }
        }

        scrnMsg.P.clear();
        scrnMsg.P.no(0).xxPopPrm.setValue(dsAcctNum);

        Object[] params = NMAL6700CommonLogic.toArrayPopup(scrnMsg.P, 2);
        setArgForSubScreen(params);

        openMultiModeScreen();
    }
}
