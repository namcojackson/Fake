/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6710;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL6720.NMAL6720CMsg;
//import business.servlet.NMAL6720.constant.NMAL6720Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 *  Account Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/20/2015   SRAA            K.Aratani       Create          N/A
 *</pre>
 */
public class NMAL6710Scrn00_ShowLocation extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

//        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;
//        scrnMsg.A.setValidCount(scrnMsg.xxSrchCnt.getValueInt());
//        scrnMsg.B.setValidCount(scrnMsg.xxSrchCnt.getValueInt());

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;

        //NMAL6710CMsg bizMsg = new NMAL6710CMsg();
        //bizMsg.setBusinessID("NMAL6710");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

//        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;
//        //NMAL6710CMsg bizMsg  = (NMAL6710CMsg) cMsg;
//
//        //EZDMsg.copy(bizMsg, null, scrnMsg, null);
//        S21TreeView treeView = scrnMsg.getTreeView();
//        if (treeView != null) {
//            java.util.List<TreeNodeRow> allNodeList = NMAL6710CommonLogic.getAllTreeNodeRows(treeView, 7);
//            for (int i = 0; i < allNodeList.size(); i++) {
//                TreeNodeRow node = allNodeList.get(i);
//                boolean isCheck = ((Boolean) node.getValueAt(7)).booleanValue();
//                if (isCheck) {
//                    Object[] params = new Object[3];
//                    params[0] = "01";
//                    params[1] = scrnMsg.B.no(i).dsAcctNum_S;
//                    params[2] = scrnMsg.B.no(i).locNum_S;
//                    setArgForSubScreen(params);
//                    break;
//                }
//            }
//        }

    }
}
