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
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.treeview.S21TreeView;
import com.fujitsu.uji.compo.TreeNodeRow;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/02/02   Fujitsu         C.Yokoi         Update          CSA_QC#2249
 * 2016/02/03   Fujitsu         C.Yokoi         Update          CSA_QC#4028
 * 2018/11/01   Fujitsu         Hd.Sugawara     Update          CSA_QC#29014
 *</pre>
 */
public class NMAL2500Scrn00_Link_Resource extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2016/02/02 CSA_QC#2249 Start
        NMAL2500BMsg scrnMsg = (NMAL2500BMsg) bMsg;

        if (NMAL2500Constant.SEARCH_MODE_SHOW_HIERARCHY.equals(scrnMsg.xxModeCd_P1.getValue())) {
            S21TreeView treeView = scrnMsg.getTreeView();

            if (treeView != null) {
                List<TreeNodeRow> checkedNodeList = treeView.getCheckedTreeNodeRows(NMAL2500Constant.COLUMN_INDEX_RADIO_BUTTON);

                if (!checkedNodeList.isEmpty()) {
                    TreeNodeRow checkedNode = checkedNodeList.get(0);

                    if (!checkedNode.isLeaf()) {
                        // Mod Start 2018/11/01 QC#29014
                        //scrnMsg.setMessageInfo(NMAL2500Constant.NMAM8339E);
                        scrnMsg.setMessageInfo(NMAL2500Constant.NMAM8695E);
                        // Mod End 2018/11/01 QC#29014
                        throw new EZDFieldErrorException();
                    }
                }
            }
        }
        // 2016/02/02 CSA_QC#2249 End
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Del Start 2018/11/01 QC#29014
//        NMAL2500BMsg scrnMsg = (NMAL2500BMsg) bMsg;
//
//        if (NMAL2500Constant.SEARCH_MODE_SHOW_HIERARCHY.equals(scrnMsg.xxModeCd_P1.getValue())) {
//            S21TreeView treeView = scrnMsg.getTreeView();
//
//            if (treeView != null) {
//                List<TreeNodeRow> checkedNodeList = treeView.getCheckedTreeNodeRows(NMAL2500Constant.COLUMN_INDEX_RADIO_BUTTON);
//
//                if (!checkedNodeList.isEmpty()) {
//                    TreeNodeRow checkedNode = checkedNodeList.get(0);
//
//                    if (checkedNode.isLeaf()) {
//                        String nodeValue = S21TreeView.getNodeColumnValue(checkedNode);
//                        scrnMsg.xxPopPrm_8.setValue(nodeValue); // S21_NA#4028 Mod
//
//                        NMAL2500CMsg bizMsg = new NMAL2500CMsg();
//                        bizMsg.setBusinessID(NMAL2500Constant.BIZ_ID);
//                        bizMsg.setFunctionCode(NMAL2500Constant.FUNCTION_CD);
//                        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//
//                        return bizMsg;
//                    }
//                }
//            }
//        }
        // Del End 2018/11/01 QC#29014

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2500BMsg scrnMsg = (NMAL2500BMsg) bMsg;

        if (NMAL2500Constant.SEARCH_MODE_QUICK_RESOURCE_LOOK_UP.equals(scrnMsg.xxModeCd_P1.getValue())) {
            int index = getButtonSelectNumber();

            if (index >= 0) {

                Object[] params = new Object[1];
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).psnNum_A1)) {
                    // Resource#
                    params[0] = scrnMsg.A.no(index).psnNum_A1;
                }

                setArgForSubScreen(params);
            }

        } else if (NMAL2500Constant.SEARCH_MODE_SHOW_HIERARCHY.equals(scrnMsg.xxModeCd_P1.getValue())) {

            S21TreeView treeView = scrnMsg.getTreeView();

            if (treeView != null) {
                List<TreeNodeRow> checkedNodeList = treeView.getCheckedTreeNodeRows(NMAL2500Constant.COLUMN_INDEX_RADIO_BUTTON);

                if (!checkedNodeList.isEmpty()) {
                    TreeNodeRow checkedNode = checkedNodeList.get(0);

                    if (checkedNode.isLeaf()) {
                        // Add Start 2018/11/01 QC#29014
                        String psnNum = (String) checkedNode.getValueAt(NMAL2500Constant.COLUMN_INDEX_PSN_NUM);
                        scrnMsg.xxPopPrm_8.setValue(psnNum);
                        // Add End 2018/11/01 QC#29014

                        // Del Start 2018/11/01 QC#29014
                        //NMAL2500CMsg bizMsg = (NMAL2500CMsg) cMsg;
                        //EZDMsg.copy(bizMsg, null, scrnMsg, null);
                        // Del End 2018/11/01 QC#29014

                        Object[] params = new Object[1];
                        // Mod Start 2018/11/01 QC#29014
                        //if (ZYPCommonFunc.hasValue(scrnMsg.psnNum_H3)) {
                        //    // Resource#
                        //    params[0] = scrnMsg.psnNum_H3;
                        //}
                        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_8)) {
                            // Resource#
                            params[0] = scrnMsg.xxPopPrm_8;
                        }
                        // Mod End 2018/11/01 QC#29014

                        setArgForSubScreen(params);
                    }
                }
            }
        }

    }
}
