/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0230.common;

import static business.servlet.NFAL0230.constant.NFAL0230Constant.ACCOUNTS;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.AFFILIATES;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BRANCHES;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_APL_BTN_NM;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_APL_EVENT_NM;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_APL_LABEL;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_ARV_BTN_NM;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_ARV_EVENT_NM;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_ARV_LABEL;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_CLR_BTN_NM;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_CLR_EVENT_NM;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_CLR_LABEL;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_DEL_BTN_NM;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_DEL_EVENT_NM;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_DEL_LABEL;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_DWL_BTN_NM;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_DWL_EVENT_NM;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_DWL_LABEL;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_RJT_BTN_NM;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_RJT_EVENT_NM;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_RJT_LABEL;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_RST_BTN_NM;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_RST_EVENT_NM;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_RST_LABEL;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_RTN_BTN_NM;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_RTN_EVENT_NM;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_RTN_LABEL;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_SAV_BTN_NM;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_SAV_EVENT_NM;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_SAV_LABEL;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_SUB_BTN_NM;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_SUB_EVENT_NM;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BTN_CMN_SUB_LABEL;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.BUSINESS_UNITS;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.CHANNELLS;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.COST_CENTERS;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.MERCHANDISE_TYPES;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.NFAM0174I;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.PRODUCTS;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.SEGMENT2;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.SEGMENT3;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.SEGMENT4;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.SEGMENT5;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.SEGMENT6;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.SEGMENT7;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.SEGMENT8;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.SEGMENT9;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.TREEVIEWMOVEFTO;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NFAL0230.NFAL0230_ACMsgArray;
import business.servlet.NFAL0230.NFAL0230BMsg;

import com.canon.cusa.s21.framework.online.treeview.S21TreeView;
import com.canon.cusa.s21.framework.online.treeview.S21TreeViewCreator;
import com.canon.cusa.s21.framework.online.treeview.S21TreeViewCreatorIF;
import com.canon.cusa.s21.framework.online.treeview.S21TreeViewNodeColumn;

/**
 *<pre>
 * COA Hierarchy View NFAL0230CommonLogic
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/23   Fujitsu         T.Murai         Create          N/A
 *</pre>
 */

public class NFAL0230CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NFAL0230BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NFAL0230BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
    }

    /**
     * <pre>
     * Initial common button
     * </pre>
     * @param handler EZCommandHandler
     * @param scrnMsg NFAL0230BMsg
     */
    public static void initCommonButton(EZDCommonHandler handler, NFAL0230BMsg scrnMsg) {
        handler.setButtonProperties(BTN_CMN_SAV_BTN_NM, BTN_CMN_SAV_EVENT_NM, BTN_CMN_SAV_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_SUB_BTN_NM, BTN_CMN_SUB_EVENT_NM, BTN_CMN_SUB_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_APL_BTN_NM, BTN_CMN_APL_EVENT_NM, BTN_CMN_APL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_ARV_BTN_NM, BTN_CMN_ARV_EVENT_NM, BTN_CMN_ARV_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_RJT_BTN_NM, BTN_CMN_RJT_EVENT_NM, BTN_CMN_RJT_LABEL, 0, null);
        if (NFAM0174I.equals(scrnMsg.getMessageCode()) || EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            handler.setButtonProperties(BTN_CMN_DWL_BTN_NM, BTN_CMN_DWL_EVENT_NM, BTN_CMN_DWL_LABEL, 0, null);
        } else {
            handler.setButtonProperties(BTN_CMN_DWL_BTN_NM, BTN_CMN_DWL_EVENT_NM, BTN_CMN_DWL_LABEL, 1, null);
        }
        handler.setButtonProperties(BTN_CMN_DEL_BTN_NM, BTN_CMN_DEL_EVENT_NM, BTN_CMN_DEL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_CLR_BTN_NM, BTN_CMN_CLR_EVENT_NM, BTN_CMN_CLR_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_RST_BTN_NM, BTN_CMN_RST_EVENT_NM, BTN_CMN_RST_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_RTN_BTN_NM, BTN_CMN_RTN_EVENT_NM, BTN_CMN_RTN_LABEL, 1, null);
    }

    /**
     * get Segment ValueName
     * @param bizMsg NFAL0230BMsg
     * @return String segment Value Name
     */
    public static String getSegVlaueName(NFAL0230BMsg bizMsg) {

        String segmentNm = bizMsg.coaSegNm.getValue();
        if (SEGMENT2.equals(segmentNm)) {
            return BRANCHES;
        } else if (SEGMENT3.equals(segmentNm)) {
            return COST_CENTERS;
        } else if (SEGMENT4.equals(segmentNm)) {
            return ACCOUNTS;
        } else if (SEGMENT5.equals(segmentNm)) {
            return PRODUCTS;
        } else if (SEGMENT6.equals(segmentNm)) {
            return CHANNELLS;
        } else if (SEGMENT7.equals(segmentNm)) {
            return AFFILIATES;
        } else if (SEGMENT8.equals(segmentNm)) {
            return MERCHANDISE_TYPES;
        } else if (SEGMENT9.equals(segmentNm)) {
            return BUSINESS_UNITS;
        }
        return "";
    }

    /**
     * create TreeView
     * @param scrnMsg NFAL0230BMsg
     * @param treeMsgArray NFAL0230_ACMsgArray
     */
    public static void convertTreeInfo(NFAL0230BMsg scrnMsg, NFAL0230_ACMsgArray treeMsgArray) {

        // Create Node data.
        S21TreeViewNodeColumn nodeCol = new S21TreeViewNodeColumn();
        nodeCol.setRootNodeKey("xxCoaExtnSrchTxt_0");
        nodeCol.addInnerNodeKey("xxCoaExtnSrchTxt_1");
        nodeCol.addInnerNodeKey("xxCoaExtnSrchTxt_2");
        nodeCol.addInnerNodeKey("xxCoaExtnSrchTxt_3");
        nodeCol.addInnerNodeKey("xxCoaExtnSrchTxt_4");
        nodeCol.addInnerNodeKey("xxCoaExtnSrchTxt_5");
        nodeCol.addInnerNodeKey("xxCoaExtnSrchTxt_6");
        nodeCol.addInnerNodeKey("xxCoaExtnSrchTxt_7");
        nodeCol.addInnerNodeKey("xxCoaExtnSrchTxt_8");
        nodeCol.addInnerNodeKey("xxCoaExtnSrchTxt_9");
        nodeCol.addInnerNodeKey("xxCoaExtnSrchTxt_10");
        nodeCol.setLeafNodeKey("xxCoaExtnSrchTxt_11");
        S21TreeViewCreatorIF tViewCreatorIF = new S21TreeViewCreatorIF(treeMsgArray, nodeCol);
        S21TreeView treeView = S21TreeViewCreator.create(tViewCreatorIF, TREEVIEWMOVEFTO);

        if (null != treeView) {
            scrnMsg.setTreeView(treeView);
        } else {
            scrnMsg.setTreeView(null);
        }

    }
}
