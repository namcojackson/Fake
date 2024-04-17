/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2600.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.blap.NMAL2600.NMAL2600_TCMsgArray;
import business.servlet.NMAL2600.NMAL2600BMsg;
import business.servlet.NMAL2600.constant.NMAL2600Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.treeview.S21TreeView;
import com.canon.cusa.s21.framework.online.treeview.S21TreeViewCheckColumn;
import com.canon.cusa.s21.framework.online.treeview.S21TreeViewCreator;
import com.canon.cusa.s21.framework.online.treeview.S21TreeViewCreatorIF;
import com.canon.cusa.s21.framework.online.treeview.S21TreeViewDataColumn;
import com.canon.cusa.s21.framework.online.treeview.S21TreeViewNodeColumn;
import com.fujitsu.uji.compo.TreeNodeRow;

/**
 *<pre>
 * Resource Search NMAL24000CommonLogic
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/10   Fujitsu         J.Sakamoto      Create          N/A
 * 2015/12/01   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/03/03   Fujitsu         C.Yokoi         Update          CSA-QC#4539
 * 2016/03/11   SRAA            Y.Chen          Update          QC#5311
 * 2016/07/27   Fujitsu         C.Yokoi         Update          CSA-QC#11652
 * 2016/08/24   Fujitsu         C.Yokoi         Update          CSA-QC#11652
 * 2017/07/19   Hitachi         J.Kim           Update          QC#19588
 *</pre>
 */

public class NMAL2600CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2600BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NMAL2600BMsg scrnMsg) {
        initCommonButton(handler);
        initButton(handler);
        controlScreenFields(handler, scrnMsg);
        clearTreeView(scrnMsg);

    }

    /**
     * <pre>
     * Initial common button
     * </pre>
     * @param handler EZCommandHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(NMAL2600Constant.BTN_CMN_BTN_SAVE[0], NMAL2600Constant.BTN_CMN_BTN_SAVE[1], NMAL2600Constant.BTN_CMN_BTN_SAVE[2], 0, null);
        handler.setButtonProperties(NMAL2600Constant.BTN_CMN_BTN_SUBMIT[0], NMAL2600Constant.BTN_CMN_BTN_SUBMIT[1], NMAL2600Constant.BTN_CMN_BTN_SUBMIT[2], 0, null);
        handler.setButtonProperties(NMAL2600Constant.BTN_CMN_BTN_APPLY[0], NMAL2600Constant.BTN_CMN_BTN_APPLY[1], NMAL2600Constant.BTN_CMN_BTN_APPLY[2], 0, null);
        handler.setButtonProperties(NMAL2600Constant.BTN_CMN_BTN_APPROVE[0], NMAL2600Constant.BTN_CMN_BTN_APPROVE[1], NMAL2600Constant.BTN_CMN_BTN_APPROVE[2], 0, null);
        handler.setButtonProperties(NMAL2600Constant.BTN_CMN_BTN_REJECT[0], NMAL2600Constant.BTN_CMN_BTN_REJECT[1], NMAL2600Constant.BTN_CMN_BTN_REJECT[2], 0, null);
        handler.setButtonProperties(NMAL2600Constant.BTN_CMN_BTN_DOWNLOAD[0], NMAL2600Constant.BTN_CMN_BTN_DOWNLOAD[1], NMAL2600Constant.BTN_CMN_BTN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(NMAL2600Constant.BTN_CMN_BTN_DELETE[0], NMAL2600Constant.BTN_CMN_BTN_DELETE[1], NMAL2600Constant.BTN_CMN_BTN_DELETE[2], 0, null);
        handler.setButtonProperties(NMAL2600Constant.BTN_CMN_BTN_CLEAR[0], NMAL2600Constant.BTN_CMN_BTN_CLEAR[1], NMAL2600Constant.BTN_CMN_BTN_CLEAR[2], 1, null);
        handler.setButtonProperties(NMAL2600Constant.BTN_CMN_BTN_RESET[0], NMAL2600Constant.BTN_CMN_BTN_RESET[1], NMAL2600Constant.BTN_CMN_BTN_RESET[2], 1, null);
        handler.setButtonProperties(NMAL2600Constant.BTN_CMN_BTN_RETURN[0], NMAL2600Constant.BTN_CMN_BTN_RETURN[1], NMAL2600Constant.BTN_CMN_BTN_RETURN[2], 1, null);
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     */
    public static void initButton(EZDCommonHandler handler) {
        handler.setButtonEnabled(NMAL2600Constant.BTN_SEARCH[0], true);
        handler.setButtonEnabled(NMAL2600Constant.BTN_RESOURCE_ASSIGNMENT[0], false);
        handler.setButtonEnabled(NMAL2600Constant.BTN_SHOW_ACCOUNT[0], false);
        handler.setButtonEnabled(NMAL2600Constant.BTN_SHOW_RULES[0], false);
        handler.setButtonEnabled(NMAL2600Constant.BTN_SHOW_TERRITORY[0], false);
    }

    /**
     * <pre>
     * The state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     */
    public static void enableLinkButton(EZDCommonHandler handler) {
        handler.setButtonEnabled(NMAL2600Constant.BTN_RESOURCE_ASSIGNMENT[0], true);
        handler.setButtonEnabled(NMAL2600Constant.BTN_SHOW_ACCOUNT[0], true);
        handler.setButtonEnabled(NMAL2600Constant.BTN_SHOW_RULES[0], true);
        handler.setButtonEnabled(NMAL2600Constant.BTN_SHOW_TERRITORY[0], true);
    }

    /**
     * <pre>
     * The state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     */
    public static void disableLinkButton(EZDCommonHandler handler) {
        handler.setButtonEnabled(NMAL2600Constant.BTN_RESOURCE_ASSIGNMENT[0], false);
        handler.setButtonEnabled(NMAL2600Constant.BTN_SHOW_ACCOUNT[0], false);
        handler.setButtonEnabled(NMAL2600Constant.BTN_SHOW_RULES[0], false);
        handler.setButtonEnabled(NMAL2600Constant.BTN_SHOW_TERRITORY[0], false);
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2600BMsg
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NMAL2600BMsg scrnMsg) {
        controlScreenDetailFields(handler, scrnMsg);
    }

    /**
     * controlScreenDetailFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2600BMsg
     */
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NMAL2600BMsg scrnMsg) {
        scrnMsg.xxChkBox_H1.clear();
        scrnMsg.xxRadioBtn_H1.setValue(NMAL2600Constant.RADIO_BUTTON_TERRITORY_AND_CHILDREN_ONLY);
        scrnMsg.bizAreaOrgCd_P1.setValue(BIZ_AREA_ORG.SALES_2);
        scrnMsg.setFocusItem(scrnMsg.bizAreaOrgCd_P1);
    }

    /**
     * <pre>
     * Details are initialized (sorting sign deletion and BG color control).
     * </pre>
     * @param scrnMsg Screen Msg
     * @param baseContents String[][]
     */
    public static void clearGUIAttribute(NMAL2600BMsg scrnMsg, String[][] baseContents) {
        S21SortColumnIMGController.clearIMG(NMAL2600Constant.SCREEN_ID, scrnMsg, baseContents);
        scrnMsg.clearAllGUIAttribute(NMAL2600Constant.SCREEN_ID);
    }

    /**
     * set alternate rows background
     * @param scrnMsg screen message
     */
    public static void setTableAttribute(NMAL2600BMsg scrnMsg) {
        // TO-DO
        //S21TableColorController tblColor = new S21TableColorController(NMAL2600Constant.SCREEN_ID, scrnMsg);
    }

    /**
     * <pre>
     * Common protect control
     * </pre>
     * @param handler EZCommandHandler
     * @param scrnMsg NMAL2600BMsg
     */
    public static void commonControl(EZDCommonHandler handler, NMAL2600BMsg scrnMsg) {
        initCommonButton(handler);
        // checkAuth(handler, scrnMsg);
    }

    /**
     * Method name: clearTreeView <dd>The method explanation:
     * Processing for convert Tree Info
     * @param bMsg Screen Component Interface Message
     */
    public static void clearTreeView(NMAL2600BMsg bMsg) {
        bMsg.setTreeView(null);
    }

    /**
     * Method name: convertTreeInfo <dd>The method explanation:
     * Processing for convert Tree Info
     * @param bMsg Screen Component Interface Message
     * @param cMsgArray Component Interface Message
     * @return boolean
     */
    public static void convertTreeInfo(NMAL2600BMsg bMsg, NMAL2600_TCMsgArray cMsgArray) {

        // Create Node data.
        S21TreeViewNodeColumn nodeCol = new S21TreeViewNodeColumn();
        nodeCol.setRootNodeKey("1");
        nodeCol.addInnerNodeKey("orgNm_1");
        nodeCol.addInnerNodeKey("orgNm_2");
        nodeCol.addInnerNodeKey("orgNm_3");
        nodeCol.addInnerNodeKey("orgNm_4");
        nodeCol.addInnerNodeKey("orgNm_5");
        nodeCol.addInnerNodeKey("orgNm_6");
        nodeCol.addInnerNodeKey("orgNm_7");
        nodeCol.addInnerNodeKey("orgNm_8");
        nodeCol.addInnerNodeKey("orgNm_9");
        nodeCol.addInnerNodeKey("orgNm_10");

        if (NMAL2600Constant.RADIO_BUTTON_TERRITORY_CHILDREN_RULES == bMsg.xxRadioBtn_H1.getValueInt()) {
          nodeCol.setLeafNodeKey("xxFullNm_T");
        }

        S21TreeViewCreatorIF tViewCreatorIF = new S21TreeViewCreatorIF(cMsgArray, nodeCol);

        S21TreeViewDataColumn dataCol = new S21TreeViewDataColumn();
        dataCol.setSchemaKey(Arrays.asList(new String[]{"xxPsnNm_T"}), " , ");
        tViewCreatorIF.addColumnType(dataCol);

        dataCol = new S21TreeViewDataColumn();
        dataCol.setSchemaKey("orgFuncRoleTpNm_T");
        tViewCreatorIF.addColumnType(dataCol);

        S21TreeViewCheckColumn checkCol = new S21TreeViewCheckColumn();
        tViewCreatorIF.addColumnType(checkCol);

        S21TreeView treeView = S21TreeViewCreator.create(tViewCreatorIF, NMAL2600Constant.TREEVIEWMOVEFTO);

        if (null != treeView) {
            treeView.setExpanded((TreeNodeRow) treeView.getRoot());

            if (NMAL2600Constant.RADIO_BUTTON_TOP_TERRITORY_ONLY == bMsg.xxRadioBtn_H1.getValueInt()) {
               // 2016/08/24 CSA-QC#11652 Delete Start
               // treeView.setExpandedNodeIcon();
               // 2016/08/24 CSA-QC#11652 Delete End
            } else if (NMAL2600Constant.RADIO_BUTTON_ALL_TERRITORY_EXPANDED == bMsg.xxRadioBtn_H1.getValueInt()) {
                treeView.setExpandedNodeIcon();
            } else if (NMAL2600Constant.RADIO_BUTTON_TERRITORY_AND_CHILDREN_ONLY == bMsg.xxRadioBtn_H1.getValueInt()) {
                treeView.setCollapsedNodeIcon();
            } else if (NMAL2600Constant.RADIO_BUTTON_TERRITORY_CHILDREN_RULES == bMsg.xxRadioBtn_H1.getValueInt()) {
                treeView.setCollapsedNodeIcon();
            } else {
                treeView.setCollapsedNodeIcon();
            }

            setFocusTreeInfo(treeView, bMsg);
            treeView.setFocusTreeView(true);

            bMsg.setTreeView(treeView);
        } else {
            bMsg.setTreeView(null);
        }
    }

    /**
     * Method name: convertTreeInfo <dd>The method explanation:
     * Processing for convert Tree Info
     * @param bMsg Screen Component Interface Message
     * @return boolean
     */
    public static boolean isExpanded(NMAL2600BMsg bMsg) {

        if (NMAL2600Constant.RADIO_BUTTON_TERRITORY_AND_CHILDREN_ONLY == bMsg.xxRadioBtn_H1.getValueInt()) {
            return true;
        } else if (NMAL2600Constant.RADIO_BUTTON_TERRITORY_CHILDREN_RULES == bMsg.xxRadioBtn_H1.getValueInt()) {
            return true;
        }

        return false;
    }

    /**
     * Method name: convertTreeInfo <dd>The method explanation:
     * Processing for convert Tree Info
     * @param treeView Screen Component Interface Message
     * @param bMsg Screen Component Interface Message
     * @return boolean
     */
    public static void setFocusTreeInfo(S21TreeView treeView, NMAL2600BMsg bMsg) {

        List<TreeNodeRow> currentNodeList = new ArrayList<TreeNodeRow>();
        TreeNodeRow rootNode = (TreeNodeRow) treeView.getRoot();

        String nodeValue = "";
        String target = bMsg.orgNm_H1.getValue();

        TreeNodeRow childNodeFirst;
        TreeNodeRow childNodeSecond;
        TreeNodeRow childNodeThird;
        TreeNodeRow childNodeFourth;
        TreeNodeRow childNodeFifth;
        TreeNodeRow childNodeSixth;
        TreeNodeRow childNodeSeventh;
        TreeNodeRow childNodeEighth;
        TreeNodeRow childNodeNinth;
        TreeNodeRow childNodeTenth;

        List<TreeNodeRow> expandNodeList = new ArrayList<TreeNodeRow>();

        int childCountFirst;
        int childCountSecond;
        int childCountThird;
        int childCountFourth;
        int childCountFifth;
        int childCountSixth;
        int childCountSeventh;
        int childCountEighth;
        int childCountNinth;
        int childCountTenth;

        boolean isFound = false;
        boolean isFirst = false;

        expandNodeList.clear();

        childCountFirst = rootNode.getChildCount();
        for (int x = 0; x < bMsg.Q.getValidCount(); x++) {
            target = bMsg.Q.no(x).orgNm_Q.getValue();
            isFound = false;
            isFirst = bMsg.Q.no(x).xxFrtChgMethDnldFlg_Q.getValue() == ZYPConstant.FLG_ON_Y;

            for (int a = 0; a < childCountFirst; a++) {
                currentNodeList.clear();
                currentNodeList.add(0, rootNode.getChild(a));
                childNodeFirst = currentNodeList.get(0);
                nodeValue = S21TreeView.getNodeColumnValue(childNodeFirst);

                if (target.equals(nodeValue)) {
                    if (childNodeFirst != null && isExpanded(bMsg)) {
                        treeView.setExpanded(rootNode);
                        // START 2017/07/19 J.Kim [QC#19588,MOD]
                        // treeView.setCollapsed(childNodeFirst);
                        treeView.setExpanded(childNodeFirst);
                        // END 2017/07/19 J.Kim [QC#19588,MOD]
                    }
                    setFocusOn(treeView, currentNodeList, isFirst);
                    setFocus(treeView, currentNodeList);
                    isFound = true;
                    break;
                } else {
                    expandNodeList.add(childNodeFirst);
                    childCountSecond = childNodeFirst.getChildCount();

                    for (int b = 0; b < childCountSecond; b++) {
                        currentNodeList.clear();
                        currentNodeList.add(0, childNodeFirst.getChild(b));
                        childNodeSecond = childNodeFirst.getChild(b);
                        nodeValue = S21TreeView.getNodeColumnValue(childNodeSecond);

                        if (target.equals(nodeValue)) {
                            if (childNodeFirst != null && isExpanded(bMsg)) {
                                treeView.setExpanded(rootNode);
                                treeView.setExpanded(childNodeFirst);
                                if (childNodeSecond != null) {
                                    // START 2017/07/19 J.Kim [QC#19588,MOD]
                                    // treeView.setCollapsed(childNodeSecond);
                                    treeView.setExpanded(childNodeSecond);
                                    // END 2017/07/19 J.Kim [QC#19588,MOD]
                                }
                            }
                            setFocusOn(treeView, currentNodeList, isFirst);
                            setFocus(treeView, currentNodeList);
                            isFound = true;
                            break;
                        } else {
                            expandNodeList.add(childNodeSecond);
                            childCountThird = childNodeSecond.getChildCount();

                            for (int c = 0; c < childCountThird; c++) {
                                currentNodeList.clear();
                                currentNodeList.add(0, childNodeSecond.getChild(c));
                                childNodeThird = childNodeSecond.getChild(c);
                                nodeValue = S21TreeView.getNodeColumnValue(childNodeThird);

                                if (target.equals(nodeValue)) {
                                    if (childNodeFirst != null && isExpanded(bMsg)) {
                                        treeView.setExpanded(rootNode);
                                        treeView.setExpanded(childNodeFirst);
                                        if (childNodeSecond != null) {
                                            treeView.setExpanded(childNodeSecond);
                                            if (childNodeThird != null) {
                                                // START 2017/07/19 J.Kim [QC#19588,MOD]
                                                // treeView.setCollapsed(childNodeThird);
                                                treeView.setExpanded(childNodeThird);
                                                // END 2017/07/19 J.Kim [QC#19588,MOD]
                                            }
                                        }
                                    }
                                    setFocusOn(treeView, currentNodeList, isFirst);
                                    setFocus(treeView, currentNodeList);
                                    isFound = true;
                                    break;
                                } else {
                                    expandNodeList.add(childNodeThird);
                                    childCountFourth = childNodeThird.getChildCount();

                                    for (int d = 0; d < childCountFourth; d++) {
                                        currentNodeList.clear();
                                        currentNodeList.add(0, childNodeThird.getChild(d));
                                        childNodeFourth = childNodeThird.getChild(d);
                                        nodeValue = S21TreeView.getNodeColumnValue(childNodeFourth);

                                        if (target.equals(nodeValue)) {
                                            if (childNodeFirst != null && isExpanded(bMsg)) {
                                                treeView.setExpanded(rootNode);
                                                treeView.setExpanded(childNodeFirst);
                                                if (childNodeSecond != null) {
                                                    treeView.setExpanded(childNodeSecond);
                                                    if (childNodeThird != null) {
                                                        treeView.setExpanded(childNodeThird);
                                                        if (childNodeFourth != null) {
                                                            // START 2017/07/19 J.Kim [QC#19588,MOD]
                                                            // treeView.setCollapsed(childNodeFourth);
                                                            treeView.setExpanded(childNodeFourth);
                                                            // END 2017/07/19 J.Kim [QC#19588,MOD]
                                                        }
                                                    }
                                                }
                                            }
                                            setFocusOn(treeView, currentNodeList, isFirst);
                                            setFocus(treeView, currentNodeList);
                                            isFound = true;
                                            break;
                                        } else {
                                            expandNodeList.add(childNodeFourth);
                                            childCountFifth = childNodeFourth.getChildCount();

                                            for (int e = 0; e < childCountFifth; e++) {
                                                currentNodeList.clear();
                                                currentNodeList.add(0, childNodeFourth.getChild(e));
                                                childNodeFifth = childNodeFourth.getChild(e);
                                                nodeValue = S21TreeView.getNodeColumnValue(childNodeFifth);

                                                if (target.equals(nodeValue)) {
                                                    if (childNodeFirst != null && isExpanded(bMsg)) {
                                                        treeView.setExpanded(rootNode);
                                                        treeView.setExpanded(childNodeFirst);
                                                        if (childNodeSecond != null) {
                                                            treeView.setExpanded(childNodeSecond);
                                                            if (childNodeThird != null) {
                                                                treeView.setExpanded(childNodeThird);
                                                                if (childNodeFourth != null) {
                                                                    treeView.setExpanded(childNodeFourth);
                                                                    if (childNodeFifth != null) {
                                                                        // START 2017/07/19 J.Kim [QC#19588,MOD]
                                                                        // treeView.setCollapsed(childNodeFifth);
                                                                        treeView.setExpanded(childNodeFifth);
                                                                        // END 2017/07/19 J.Kim [QC#19588,MOD]
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                    setFocusOn(treeView, currentNodeList, isFirst);
                                                    setFocus(treeView, currentNodeList);
                                                    isFound = true;
                                                    break;
                                                } else {
                                                    expandNodeList.add(childNodeFifth);
                                                    childCountSixth = childNodeFifth.getChildCount();

                                                    for (int f = 0; f < childCountSixth; f++) {
                                                        currentNodeList.clear();
                                                        currentNodeList.add(0, childNodeFifth.getChild(f));
                                                        childNodeSixth = childNodeFifth.getChild(f);
                                                        nodeValue = S21TreeView.getNodeColumnValue(childNodeSixth);

                                                        if (target.equals(nodeValue)) {
                                                            if (childNodeFirst != null && isExpanded(bMsg)) {
                                                                treeView.setExpanded(rootNode);
                                                                treeView.setExpanded(childNodeFirst);
                                                                if (childNodeSecond != null) {
                                                                    treeView.setExpanded(childNodeSecond);
                                                                    if (childNodeThird != null) {
                                                                        treeView.setExpanded(childNodeThird);
                                                                        if (childNodeFourth != null) {
                                                                            treeView.setExpanded(childNodeFourth);
                                                                            if (childNodeFifth != null) {
                                                                                treeView.setExpanded(childNodeFifth);
                                                                                if (childNodeSixth != null) {
                                                                                    // START 2017/07/19 J.Kim [QC#19588,MOD]
                                                                                    // treeView.setCollapsed(childNodeSixth);
                                                                                    treeView.setExpanded(childNodeSixth);
                                                                                    // END 2017/07/19 J.Kim [QC#19588,MOD]
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            setFocusOn(treeView, currentNodeList, isFirst);
                                                            setFocus(treeView, currentNodeList);
                                                            isFound = true;
                                                            break;
                                                        } else {
                                                            expandNodeList.add(childNodeSixth);
                                                            childCountSeventh = childNodeSixth.getChildCount();

                                                            for (int g = 0; g < childCountSeventh; g++) {
                                                                currentNodeList.clear();
                                                                currentNodeList.add(0, childNodeSixth.getChild(g));
                                                                childNodeSeventh = childNodeSixth.getChild(g);
                                                                nodeValue = S21TreeView.getNodeColumnValue(childNodeSeventh);

                                                                if (target.equals(nodeValue)) {
                                                                    if (childNodeFirst != null && isExpanded(bMsg)) {
                                                                        treeView.setExpanded(rootNode);
                                                                        treeView.setExpanded(childNodeFirst);
                                                                        if (childNodeSecond != null) {
                                                                            treeView.setExpanded(childNodeSecond);
                                                                            if (childNodeThird != null) {
                                                                                treeView.setExpanded(childNodeThird);
                                                                                if (childNodeFourth != null) {
                                                                                    treeView.setExpanded(childNodeFourth);
                                                                                    if (childNodeFifth != null) {
                                                                                        treeView.setExpanded(childNodeFifth);
                                                                                        if (childNodeSixth != null) {
                                                                                            treeView.setExpanded(childNodeSixth);
                                                                                            if (childNodeSeventh != null) {
                                                                                                // START 2017/07/19 J.Kim [QC#19588,MOD]
                                                                                                // treeView.setCollapsed(childNodeSeventh);
                                                                                                treeView.setExpanded(childNodeSeventh);
                                                                                                // END 2017/07/19 J.Kim [QC#19588,MOD]
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                    setFocusOn(treeView, currentNodeList, isFirst);
                                                                    setFocus(treeView, currentNodeList);
                                                                    isFound = true;
                                                                    break;
                                                                } else {
                                                                    expandNodeList.add(childNodeSeventh);
                                                                    childCountEighth = childNodeSeventh.getChildCount();

                                                                    for (int h = 0; h < childCountEighth; h++) {
                                                                        currentNodeList.clear();
                                                                        currentNodeList.add(0, childNodeSeventh.getChild(h));
                                                                        childNodeEighth = childNodeSeventh.getChild(h);
                                                                        nodeValue = S21TreeView.getNodeColumnValue(childNodeEighth);

                                                                        if (target.equals(nodeValue)) {
                                                                            if (childNodeFirst != null && isExpanded(bMsg)) {
                                                                                treeView.setExpanded(rootNode);
                                                                                treeView.setExpanded(childNodeFirst);
                                                                                if (childNodeSecond != null) {
                                                                                    treeView.setExpanded(childNodeSecond);
                                                                                    if (childNodeThird != null) {
                                                                                        treeView.setExpanded(childNodeThird);
                                                                                        if (childNodeFourth != null) {
                                                                                            treeView.setExpanded(childNodeFourth);
                                                                                            if (childNodeFifth != null) {
                                                                                                treeView.setExpanded(childNodeFifth);
                                                                                                if (childNodeSixth != null) {
                                                                                                    treeView.setExpanded(childNodeSixth);
                                                                                                    if (childNodeSeventh != null) {
                                                                                                        treeView.setExpanded(childNodeSeventh);
                                                                                                        if (childNodeEighth != null) {
                                                                                                            // START 2017/07/19 J.Kim [QC#19588,MOD]
                                                                                                            // treeView.setCollapsed(childNodeEighth);
                                                                                                            treeView.setExpanded(childNodeEighth);
                                                                                                            // END 2017/07/19 J.Kim [QC#19588,MOD]
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                            setFocusOn(treeView, currentNodeList, isFirst);
                                                                            setFocus(treeView, currentNodeList);
                                                                            isFound = true;
                                                                            break;
                                                                        } else {
                                                                            expandNodeList.add(childNodeEighth);
                                                                            childCountNinth = childNodeEighth.getChildCount();

                                                                            for (int i = 0; i < childCountNinth; i++) {
                                                                                currentNodeList.clear();
                                                                                currentNodeList.add(0, childNodeEighth.getChild(i));
                                                                                childNodeNinth = childNodeEighth.getChild(i);
                                                                                nodeValue = S21TreeView.getNodeColumnValue(childNodeNinth);

                                                                                if (target.equals(nodeValue)) {
                                                                                    if (childNodeFirst != null && isExpanded(bMsg)) {
                                                                                        treeView.setExpanded(rootNode);
                                                                                        treeView.setExpanded(childNodeFirst);
                                                                                        if (childNodeSecond != null) {
                                                                                            treeView.setExpanded(childNodeSecond);
                                                                                            if (childNodeThird != null) {
                                                                                                treeView.setExpanded(childNodeThird);
                                                                                                if (childNodeFourth != null) {
                                                                                                    treeView.setExpanded(childNodeFourth);
                                                                                                    if (childNodeFifth != null) {
                                                                                                        treeView.setExpanded(childNodeFifth);
                                                                                                        if (childNodeSixth != null) {
                                                                                                            treeView.setExpanded(childNodeSixth);
                                                                                                            if (childNodeSeventh != null) {
                                                                                                                treeView.setExpanded(childNodeSeventh);
                                                                                                                if (childNodeEighth != null) {
                                                                                                                    treeView.setExpanded(childNodeEighth);
                                                                                                                    if (childNodeNinth != null) {
                                                                                                                        // START 2017/07/19 J.Kim [QC#19588,MOD]
                                                                                                                        // treeView.setCollapsed(childNodeNinth);
                                                                                                                        treeView.setExpanded(childNodeNinth);
                                                                                                                        // END 2017/07/19 J.Kim [QC#19588,MOD]
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                    setFocusOn(treeView, currentNodeList, isFirst);
                                                                                    setFocus(treeView, currentNodeList);
                                                                                    isFound = true;
                                                                                    break;
                                                                                } else {
                                                                                    expandNodeList.add(childNodeNinth);
                                                                                    childCountTenth = childNodeNinth.getChildCount();

                                                                                    for (int j = 0; j < childCountTenth; j++) {
                                                                                        currentNodeList.clear();
                                                                                        currentNodeList.add(0, childNodeNinth.getChild(j));
                                                                                        childNodeTenth = childNodeNinth.getChild(j);
                                                                                        nodeValue = S21TreeView.getNodeColumnValue(childNodeTenth);

                                                                                        if (target.equals(nodeValue)) {
                                                                                            if (childNodeFirst != null && isExpanded(bMsg)) {
                                                                                                treeView.setExpanded(rootNode);
                                                                                                treeView.setExpanded(childNodeFirst);
                                                                                                if (childNodeSecond != null) {
                                                                                                    treeView.setExpanded(childNodeSecond);
                                                                                                    if (childNodeThird != null) {
                                                                                                        treeView.setExpanded(childNodeThird);
                                                                                                        if (childNodeFourth != null) {
                                                                                                            treeView.setExpanded(childNodeFourth);
                                                                                                            if (childNodeFifth != null) {
                                                                                                                treeView.setExpanded(childNodeFifth);
                                                                                                                if (childNodeSixth != null) {
                                                                                                                    treeView.setExpanded(childNodeSixth);
                                                                                                                    if (childNodeSeventh != null) {
                                                                                                                        treeView.setExpanded(childNodeSeventh);
                                                                                                                        if (childNodeEighth != null) {
                                                                                                                            treeView.setExpanded(childNodeEighth);
                                                                                                                            if (childNodeNinth != null) {
                                                                                                                                treeView.setExpanded(childNodeNinth);
                                                                                                                                if (childNodeTenth != null) {
                                                                                                                                    // START 2017/07/19 J.Kim [QC#19588,MOD]
                                                                                                                                    // treeView.setCollapsed(childNodeTenth);
                                                                                                                                    treeView.setExpanded(childNodeTenth);
                                                                                                                                    // END 2017/07/19 J.Kim [QC#19588,MOD]
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                            setFocusOn(treeView, currentNodeList, isFirst);
                                                                                            setFocus(treeView, currentNodeList);
                                                                                            isFound = true;
                                                                                            break;
                                                                                        } else {
                                                                                            continue;
                                                                                        }
                                                                                    }
                                                                                    if (isFound) {
                                                                                        break;
                                                                                    }
                                                                                }
                                                                            }
                                                                            if (isFound) {
                                                                                break;
                                                                            }
                                                                        }
                                                                    }
                                                                    if (isFound) {
                                                                        break;
                                                                    }
                                                                }
                                                            }
                                                            if (isFound) {
                                                                break;
                                                            }
                                                        }
                                                    }
                                                    if (isFound) {
                                                        break;
                                                    }
                                                }
                                            }
                                            if (isFound) {
                                                break;
                                            }
                                        }
                                    }
                                    if (isFound) {
                                        break;
                                    }
                                }
                            }
                            if (isFound) {
                                break;
                            }
                        }
                    }
                    if (isFound) {
                        break;
                    }
                }
            }
        }
    }

    /**
     * The method explanation: Change background color and focus on
     * @param treeView S21TreeView
     * @param currentNodeList List<TreeNodeRow>
     * @param rootNode TreeNodeRow
     * @param expandNodeList List<TreeNodeRow>
     */
    public static void setFocus(S21TreeView treeView, List<TreeNodeRow> currentNodeList) {

//        // set focus on radio button
//        treeView.setCheckboxON(currentNodeList.get(0), NMAL2600Constant.COLUMN_INDEX_RADIO_BUTTON);
//        treeView.setFocusTreeView(true);
//
//        // change background color of focus.
//        treeView.setBackgroundColor(null);
        S21TreeView.setBackgroundColor(currentNodeList.get(0), NMAL2600Constant.BACKGROUND_COLOR_YELLOW);

    }
    
    /**
     * The method explanation: Change background color and focus on
     * @param treeView S21TreeView
     * @param currentNodeList List<TreeNodeRow>
     * @param isFirst boolean
     */
    public static void setFocusOn(S21TreeView treeView, List<TreeNodeRow> currentNodeList, boolean isFirst) {

        if (isFirst) {
            treeView.setCheckboxON(currentNodeList.get(0), NMAL2600Constant.COLUMN_INDEX_RADIO_BUTTON);
        }

        return;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL2600BMsg
     */
    public static void initParam(NMAL2600BMsg scrnMsg) {

        scrnMsg.xxPopPrm_0.clear();
        scrnMsg.xxPopPrm_1.clear();
        scrnMsg.xxPopPrm_2.clear();
        scrnMsg.xxPopPrm_3.clear();
        scrnMsg.xxPopPrm_4.clear();
        scrnMsg.xxPopPrm_5.clear();
        scrnMsg.xxPopPrm_6.clear();
        scrnMsg.xxPopPrm_7.clear();
        scrnMsg.xxPopPrm_8.clear();
        scrnMsg.xxPopPrm_9.clear();
        scrnMsg.xxPopPrm_10.clear();
    }

    /**
     * The method explanation: set parameter to call.
     * @param scrnMsg NMAL2600BMsg
     * @return Object[]
     */
    public static Object[] setParamsForOtherScreen(NMAL2600BMsg scrnMsg) {

        Object[] params = new Object[1];

        initParam(scrnMsg);

        if (ZYPCommonFunc.hasValue(scrnMsg.orgCd_P)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0, scrnMsg.orgCd_P);
        }

        // Territory ID
        params[0] = scrnMsg.xxPopPrm_0;

        return params;
    }

    /**
     * The method explanation: set parameter to call.
     * @param scrnMsg NMAL2600BMsg
     * @return Object[]
     */
    public static Object[] setParamsForAccountRulePopup(NMAL2600BMsg scrnMsg) {

        Object[] params = new Object[2];

        initParam(scrnMsg);

        if (ZYPCommonFunc.hasValue(scrnMsg.orgCd_P)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_1, scrnMsg.orgCd_P);
        }

        params[0] = null;
        params[1] = scrnMsg.xxPopPrm_1;

        return params;
    }
    /**
     * check selected value in tree view.
     * @param scrnMsg NMAL2600BMsg
     * @return boolean
     */
    public static boolean chkTerritoryCodeforParam(NMAL2600BMsg scrnMsg) {

        S21TreeView treeView = scrnMsg.getTreeView();
        if (treeView != null) {
            List<TreeNodeRow> checkedNodeList = treeView.getCheckedTreeNodeRows(NMAL2600Constant.COLUMN_INDEX_RADIO_BUTTON);

            if (checkedNodeList.isEmpty()) {
                scrnMsg.setMessageInfo(NMAL2600Constant.NMAM0014E, new String[] {NMAL2600Constant.NAME_FOR_MESSAGE_ORG_NM });
                return false;
            } else {
                TreeNodeRow checkedNode = checkedNodeList.get(0);
                String nodeValue = S21TreeView.getNodeColumnValue(checkedNode);

                if (nodeValue == null) {
                    scrnMsg.setMessageInfo(NMAL2600Constant.NMAM0014E, new String[] {NMAL2600Constant.NAME_FOR_MESSAGE_ORG_NM });
                    return false;

                } else if (".".equals(nodeValue)) {
                    scrnMsg.setMessageInfo(NMAL2600Constant.NMAM0014E, new String[] {NMAL2600Constant.NAME_FOR_MESSAGE_ORG_NM });
                    return false;
                // QC#5311
                } else if (checkedNode.getChildCount() == treeView.getChildCount(treeView.getRoot())) {
                    // CSA-QC#11652 2016/07/27 Mod Start
                    // scrnMsg.setMessageInfo(NMAL2600Constant.NMAM8386E);
                    // return false;

                    TreeNodeRow rootNode = (TreeNodeRow)treeView.getRoot();
                    String rootNodeValue = S21TreeView.getNodeColumnValue(rootNode);

                    if (nodeValue.equals(rootNodeValue)) {
                        scrnMsg.setMessageInfo(NMAL2600Constant.NMAM8386E);
                        return false;
                    }
                    if (ZYPCommonFunc.hasValue(nodeValue)) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.orgNm_P, nodeValue);
                    }
                    // CSA-QC#11652 2016/07/27 Mod End
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.orgNm_P, nodeValue);
                    return true;
                }
            }
        } else {
            scrnMsg.setMessageInfo(NMAL2600Constant.NMAM0014E, new String[] {NMAL2600Constant.NAME_FOR_MESSAGE_ORG_NM });
            return false;
        }

        return true;
    }

    /**
     * add check input items
     * @param scrnMsg NMAL2600BMsg
     */
    public static void addCheckItems(NMAL2600BMsg scrnMsg) {
        // 2016/03/03 CSA-QC#4539 Add Start
        scrnMsg.addCheckItem(scrnMsg.orgNm_H1);
        scrnMsg.addCheckItem(scrnMsg.xxPsnNm_H1);
        scrnMsg.addCheckItem(scrnMsg.psnCd_H1);
        scrnMsg.addCheckItem(scrnMsg.psnNum_H1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).trtyRuleFromValTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).trtyRuleThruValTxt_A1);
        }

        scrnMsg.putErrorScreen();
        // 2016/03/03 CSA-QC#4539 Add End
    }

}
