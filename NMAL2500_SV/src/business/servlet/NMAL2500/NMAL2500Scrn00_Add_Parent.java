/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2500;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2500.constant.NMAL2500Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.treeview.S21TreeView;
import com.fujitsu.uji.compo.TreeNodeRow;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Fujitsu         K.Koitabashi    Create          N/A
 * 2015/02/15   Fujitsu         C.Yokoi         Update          CSA-QC#1931
 * 2015/02/22   Fujitsu         C.Yokoi         Update          CSA-QC#2249
 *</pre>
 */
public class NMAL2500Scrn00_Add_Parent extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2500BMsg scrnMsg = (NMAL2500BMsg) bMsg;

        S21TreeView treeView = scrnMsg.getTreeView();
        if (treeView != null) {
            List<TreeNodeRow> checkedNodeList = treeView.getCheckedTreeNodeRows(NMAL2500Constant.COLUMN_INDEX_RADIO_BUTTON);
            if (checkedNodeList.isEmpty()) {
                scrnMsg.setMessageInfo(NMAL2500Constant.NMAM0014E, new String[] {NMAL2500Constant.NAME_FOR_MESSAGE_ORG_NM });
                throw new EZDFieldErrorException();
            } else {
                TreeNodeRow checkedNode = checkedNodeList.get(0);
                String nodeValue = S21TreeView.getNodeColumnValue(checkedNode);
                if (nodeValue == null) {
                    scrnMsg.setMessageInfo(NMAL2500Constant.NMAM0014E, new String[] {NMAL2500Constant.NAME_FOR_MESSAGE_ORG_NM });
                    throw new EZDFieldErrorException();
                    // 2016/02/15 CSA-QC#1931 Delete Start
                    // } else if (".".equals(nodeValue)) {
                    //     scrnMsg.setMessageInfo(NMAL2500Constant.NMAM0014E, new String[] {NMAL2500Constant.NAME_FOR_MESSAGE_ORG_NM });
                    //     throw new EZDFieldErrorException();
                    // 2016/02/15 CSA-QC#1931 Delete End
                }
            }
        } else {
            scrnMsg.setMessageInfo(NMAL2500Constant.NMAM0014E, new String[] {NMAL2500Constant.NAME_FOR_MESSAGE_ORG_NM });
            throw new EZDFieldErrorException();
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2500BMsg scrnMsg = (NMAL2500BMsg) bMsg;

        S21TreeView treeView = scrnMsg.getTreeView();

        if (treeView != null) {
            List<TreeNodeRow> checkedNodeList = treeView.getCheckedTreeNodeRows(NMAL2500Constant.COLUMN_INDEX_RADIO_BUTTON);

            if (!checkedNodeList.isEmpty()) {
                TreeNodeRow checkedNode = checkedNodeList.get(0);
                // 2016/02/22 CSA-QC#2249 Add Start
                if (checkedNode.isLeaf()) {
                    checkedNode = checkedNode.getParent();
                }
                // 2016/02/22 CSA-QC#2249 AddEnd
                String nodeValue = S21TreeView.getNodeColumnValue(checkedNode);

                Object[] params = new Object[3];

                // EZDBStringItem param0 = (EZDBStringItem) params[0];
                if (nodeValue != null) {
                    // Organization Name
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0, nodeValue);
                    params[0] = scrnMsg.xxPopPrm_0;
                }

                // EZDBStringItem param1 = (EZDBStringItem) params[1];
                if (ZYPCommonFunc.hasValue(scrnMsg.bizAreaOrgCd_P1)) {
                    // Business Area
                    params[1] = scrnMsg.bizAreaOrgCd_P1;
                }

                setArgForSubScreen(params);
            }
        }

    }
}
