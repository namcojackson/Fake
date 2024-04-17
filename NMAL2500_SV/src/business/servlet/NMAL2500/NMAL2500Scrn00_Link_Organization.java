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
 * 2016/07/27   Fujitsu         K.Yokoi         Update          QC-CSA#11652
 *</pre>
 */
public class NMAL2500Scrn00_Link_Organization extends S21CommonHandler {

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
                } else if (".".equals(nodeValue)) {
                    scrnMsg.setMessageInfo(NMAL2500Constant.NMAM0014E, new String[] {NMAL2500Constant.NAME_FOR_MESSAGE_ORG_NM });
                    throw new EZDFieldErrorException();
                }

                // Add Start CSA#4556 2016/03/01
                if (checkedNode.getChildCount() == treeView.getChildCount(treeView.getRoot())) {
                    // Mod Start CSA#11652 2016/07/27
                    // scrnMsg.setMessageInfo(NMAL2500Constant.NMAM8386E);
                    // throw new EZDFieldErrorException();

                    TreeNodeRow rootNode = (TreeNodeRow)treeView.getRoot();
                    String rootNodeValue = S21TreeView.getNodeColumnValue(rootNode);

                    if (nodeValue.equals(rootNodeValue)) {
                        scrnMsg.setMessageInfo(NMAL2500Constant.NMAM8386E);
                        throw new EZDFieldErrorException();
                    }
                    // Mod End CSA#11652 2016/07/27
                }
                // Add Start CSA#4556 2016/03/01
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

                // 2016/02/02 CSA-QC#2249 Add Start
                if (checkedNode.isLeaf()) {
                    checkedNode = checkedNode.getParent();
                }
                // 2016/02/02 CSA-QC#2249 AddEnd

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
