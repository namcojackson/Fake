/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2500.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.blap.NMAL2500.NMAL2500_TCMsgArray;
import business.servlet.NMAL2500.NMAL2500BMsg;
import business.servlet.NMAL2500.constant.NMAL2500Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
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
 * Org Resource Search NMAL2500CommonLogic
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/10   Fujitsu         J.Sakamoto      Create          N/A
 * 2015/12/02   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/03/01   Fujitsu         H.Ikeda         Update          QC#4532
 * 2016/03/04   Fujitsu         M.suzuki        Update          S21_NA#4539
 * 2016/07/17   Fujitsu         C.Yokoi         Update          CSA-QC#11652
 * 2016/09/01   Fujitsu         C.Yokoi         Update          CSA-QC#11652
 * 2017/02/23   Fujitsu         K.Ishizuka      Update          QC#16481
 * 2017/06/20   Fujitsu         N.Sugiura       Update          QC#19356
 * 2018/11/01   Fujitsu         Hd.Sugawara     Update          QC#29014
 *</pre>
 */

public class NMAL2500CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2500BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NMAL2500BMsg scrnMsg) {
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
        handler.setButtonProperties(NMAL2500Constant.BTN_CMN_BTN_SAVE[0], NMAL2500Constant.BTN_CMN_BTN_SAVE[1], NMAL2500Constant.BTN_CMN_BTN_SAVE[2], 0, null);
        handler.setButtonProperties(NMAL2500Constant.BTN_CMN_BTN_SUBMIT[0], NMAL2500Constant.BTN_CMN_BTN_SUBMIT[1], NMAL2500Constant.BTN_CMN_BTN_SUBMIT[2], 0, null);
        handler.setButtonProperties(NMAL2500Constant.BTN_CMN_BTN_APPLY[0], NMAL2500Constant.BTN_CMN_BTN_APPLY[1], NMAL2500Constant.BTN_CMN_BTN_APPLY[2], 0, null);
        handler.setButtonProperties(NMAL2500Constant.BTN_CMN_BTN_APPROVE[0], NMAL2500Constant.BTN_CMN_BTN_APPROVE[1], NMAL2500Constant.BTN_CMN_BTN_APPROVE[2], 0, null);
        handler.setButtonProperties(NMAL2500Constant.BTN_CMN_BTN_REJECT[0], NMAL2500Constant.BTN_CMN_BTN_REJECT[1], NMAL2500Constant.BTN_CMN_BTN_REJECT[2], 0, null);
        handler.setButtonProperties(NMAL2500Constant.BTN_CMN_BTN_DOWNLOAD[0], NMAL2500Constant.BTN_CMN_BTN_DOWNLOAD[1], NMAL2500Constant.BTN_CMN_BTN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(NMAL2500Constant.BTN_CMN_BTN_DELETE[0], NMAL2500Constant.BTN_CMN_BTN_DELETE[1], NMAL2500Constant.BTN_CMN_BTN_DELETE[2], 0, null);
        handler.setButtonProperties(NMAL2500Constant.BTN_CMN_BTN_CLEAR[0], NMAL2500Constant.BTN_CMN_BTN_CLEAR[1], NMAL2500Constant.BTN_CMN_BTN_CLEAR[2], 1, null);
        handler.setButtonProperties(NMAL2500Constant.BTN_CMN_BTN_RESET[0], NMAL2500Constant.BTN_CMN_BTN_RESET[1], NMAL2500Constant.BTN_CMN_BTN_RESET[2], 1, null);
        handler.setButtonProperties(NMAL2500Constant.BTN_CMN_BTN_RETURN[0], NMAL2500Constant.BTN_CMN_BTN_RETURN[1], NMAL2500Constant.BTN_CMN_BTN_RETURN[2], 1, null);
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     */
    public static void initButton(EZDCommonHandler handler) {
        handler.setButtonEnabled(NMAL2500Constant.BTN_SEARCH[0], true);
        handler.setButtonEnabled(NMAL2500Constant.BTN_EDIT_ORGANIZATION[0], false);
        handler.setButtonEnabled(NMAL2500Constant.BTN_ADD_CHILD_ORGANIZATION[0], false);
        handler.setButtonEnabled(NMAL2500Constant.BTN_EDIT_RESOURCE[0], false);
        // 2016/02/26 QC#4532 Add Start
        handler.setButtonEnabled(NMAL2500Constant.BTN_DELETE[0], false);
        // 2016/02/26 QC#4532 Add End
    }

    /**
     * <pre>
     * The state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     */
    public static void enableLinkButton(EZDCommonHandler handler) {
        handler.setButtonEnabled(NMAL2500Constant.BTN_EDIT_ORGANIZATION[0], true);
        handler.setButtonEnabled(NMAL2500Constant.BTN_ADD_CHILD_ORGANIZATION[0], true);
        handler.setButtonEnabled(NMAL2500Constant.BTN_EDIT_RESOURCE[0], true);
    }

    /**
     * <pre>
     * The state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     */
    public static void disableLinkButton(EZDCommonHandler handler) {
        handler.setButtonEnabled(NMAL2500Constant.BTN_EDIT_ORGANIZATION[0], false);
        handler.setButtonEnabled(NMAL2500Constant.BTN_ADD_CHILD_ORGANIZATION[0], false);
        handler.setButtonEnabled(NMAL2500Constant.BTN_EDIT_RESOURCE[0], false);
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2630BMsg
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NMAL2500BMsg scrnMsg) {
        controlScreenDetailFields(handler, scrnMsg);
    }

    /**
     * controlScreenDetailFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2630BMsg
     */
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NMAL2500BMsg scrnMsg) {
        scrnMsg.xxRadioBtn_H1.setValue(NMAL2500Constant.DISPLAY_ORG_LEVEL_CHILDREN_RESOURCE);
        scrnMsg.xxRadioBtn_H1.setInputProtected(false);
    }

    /**
     * <pre>
     * Details are initialized (sorting sign deletion and BG color control).
     * </pre>
     * @param scrnMsg Screen Msg
     * @param baseContents String[][]
     */
    public static void clearGUIAttribute(NMAL2500BMsg scrnMsg, String[][] baseContents) {
        S21SortColumnIMGController.clearIMG(NMAL2500Constant.SCREEN_ID, scrnMsg, baseContents);
        scrnMsg.clearAllGUIAttribute(NMAL2500Constant.SCREEN_ID);
    }

    /**
     * <pre>
     * Common protect control
     * </pre>
     * @param handler EZCommandHandler
     * @param scrnMsg NPAL1030BMsg
     */
    public static void commonControl(EZDCommonHandler handler, NMAL2500BMsg scrnMsg) {
        initCommonButton(handler);
    }

    /**
     * Method name: clearTreeView <dd>The method explanation:
     * Processing for convert Tree Info
     * @param bMsg Screen Component Interface Message
     */
    public static void clearTreeView(NMAL2500BMsg bMsg) {
        bMsg.setTreeView(null);
    }

    /**
     * Method name: convertTreeInfo <dd>The method explanation:
     * Processing for convert Tree Info
     * @param bMsg Screen Component Interface Message
     * @param cMsgArray Screen Component Interface Message
     */
    public static void convertTreeInfo(NMAL2500BMsg bMsg, NMAL2500_TCMsgArray cMsgArray) {

//        boolean isFocusOn = false;

        // Create Node data.
        S21TreeViewNodeColumn nodeCol = new S21TreeViewNodeColumn();
        nodeCol.setRootNodeKey("bizAreaOrgNm_T");
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
        nodeCol.setLeafNodeKey("xxFullNm_T");
        S21TreeViewCreatorIF tViewCreatorIF = new S21TreeViewCreatorIF(cMsgArray, nodeCol);

        // Create Column data.
        S21TreeViewDataColumn dataCol = new S21TreeViewDataColumn();
        dataCol.setSchemaKey(Arrays.asList(new String[] {"xxPsnNm_T" }), " , ");
        tViewCreatorIF.addColumnType(dataCol);

        dataCol = new S21TreeViewDataColumn();
        dataCol.setSchemaKey("orgFuncRoleTpNm_T");
        tViewCreatorIF.addColumnType(dataCol);

        dataCol = new S21TreeViewDataColumn();
        dataCol.setSchemaKey("mgrFlg_T");
        tViewCreatorIF.addColumnType(dataCol);

        S21TreeViewCheckColumn checkCol = new S21TreeViewCheckColumn();
        tViewCreatorIF.addColumnType(checkCol);

        // Add Start 2018/11/01 QC#29014
        dataCol = new S21TreeViewDataColumn();
        dataCol.setSchemaKey("psnNum_T");
        tViewCreatorIF.addColumnType(dataCol);
        // Add End 2018/11/01 QC#29014

        S21TreeView treeView = S21TreeViewCreator.create(tViewCreatorIF, NMAL2500Constant.TREEVIEWMOVEFTO);

        if (null != treeView) {

            if (NMAL2500Constant.DISPLAY_TOP_TIERS_ONLY == bMsg.xxRadioBtn_H1.getValueInt()) {
             // 2016/09/02 CSA-QC#11652 Delete Start
             // treeView.setExpandedNodeIcon();
             // 2016/09/02 CSA-QC#11652 Delete End
            } else if (NMAL2500Constant.DISPLAY_ORG_LEVEL_CHILDREN_ONLY == bMsg.xxRadioBtn_H1.getValueInt()) {
//                treeView.setExpandedNodeIcon(); // Dell QC#19356
            } else if (NMAL2500Constant.DISPLAY_ORG_LEVEL_CHILDREN_RESOURCE == bMsg.xxRadioBtn_H1.getValueInt()) {
             // 2016/09/02 CSA-QC#11652 Delete Start
             // treeView.setCollapsedNodeIcon();
             // 2016/09/02 CSA-QC#11652 Delete End
            } else if (NMAL2500Constant.DISPLAY_ALL_LEVELS_EXPANDED_WITH_RESOURCE_ASSIGNMENT == bMsg.xxRadioBtn_H1.getValueInt()) {
              treeView.setExpandedNodeIcon(); // Add QC#19356
            } else {
                treeView.setCollapsedNodeIcon();
            }

            boolean isFound = true;
            // 2016/02/15 CSA-QC#1931 Mod Start
            if ((NMAL2500Constant.DISPLAY_ORG_LEVEL_CHILDREN_RESOURCE == bMsg.xxRadioBtn_H1.getValueInt() 
                    || NMAL2500Constant.DISPLAY_ALL_LEVELS_EXPANDED_WITH_RESOURCE_ASSIGNMENT == bMsg.xxRadioBtn_H1.getValueInt())
                    && ZYPConstant.FLG_ON_Y.equals(bMsg.xxRsltFlg_H3.getValue())) {
                isFound = setFocusTreeInfoForResource(treeView, bMsg);
            } else {
                isFound = setFocusTreeInfoForOrganization(treeView, bMsg);
            }
            // 2016/02/15 CSA-QC#1931 Mod End

            if (isFound) {
                treeView.setFocusTreeView(true);
                bMsg.setTreeView(treeView);
            } else {
                bMsg.setTreeView(null);
                ZYPTableUtil.clear(bMsg.T);
                ZYPTableUtil.clear(bMsg.R);
                bMsg.setMessageInfo(NMAL2500Constant.NMAM0038I);
            }
        } else {
            bMsg.setTreeView(null);
        }

    }

    /**
     * Method name: setFocusTreeInfoForResource <dd>The method explanation:
     * Processing for Set Tree Info For Org
     * @param treeView Screen Component Interface Message
     * @param bMsg Screen Component Interface Message
     * @return boolean
     */
    public static boolean setFocusTreeInfoForResource(S21TreeView treeView, NMAL2500BMsg bMsg) {

        List<TreeNodeRow> currentNodeList = new ArrayList<TreeNodeRow>();
        TreeNodeRow rootNode = (TreeNodeRow) treeView.getRoot();

        String nodeValue = "";
        String target = bMsg.orgNm_H2.getValue();

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
        boolean hasTarget = false;
        String focusOnFlag = "";

        childCountFirst = rootNode.getChildCount();
        for (int x = 0; x < bMsg.O.getValidCount(); x++) {

            target = bMsg.O.no(x).orgNm_O1.getValue();
            focusOnFlag = bMsg.O.no(x).xxFrtChgMethDnldFlg_O1.getValue();
            isFound = false;

            for (int a = 0; a < childCountFirst; a++) {

                currentNodeList.clear();
                currentNodeList.add(0, rootNode.getChild(a));
                childNodeFirst = currentNodeList.get(0);
                nodeValue = S21TreeView.getNodeColumnValue(childNodeFirst);
                if (!childNodeFirst.isLeaf() && target.equals(nodeValue)) {
                    if (setFocusTreeInfo(bMsg, treeView, childNodeFirst, currentNodeList, focusOnFlag)) {
                        // 2016/09/02 CSA-QC#11652 Add Start
                        treeView.setExpanded(rootNode);
                        treeView.setExpanded(childNodeFirst);
                        // 2016/09/02 CSA-QC#11652 Add End
                        isFound = true;
                        hasTarget = true;
                        break;
                    }
                } else {
                    childCountSecond = childNodeFirst.getChildCount();
                    for (int b = 0; b < childCountSecond; b++) {

                        currentNodeList.clear();
                        currentNodeList.add(0, childNodeFirst.getChild(b));
                        childNodeSecond = childNodeFirst.getChild(b);
                        nodeValue = S21TreeView.getNodeColumnValue(childNodeSecond);
                        if (!childNodeSecond.isLeaf() && target.equals(nodeValue)) {
                            if (setFocusTreeInfo(bMsg, treeView, childNodeSecond, currentNodeList, focusOnFlag)) {
                                // 2016/09/02 CSA-QC#11652 Add Start
                                treeView.setExpanded(rootNode);
                                treeView.setExpanded(childNodeFirst);
                                treeView.setExpanded(childNodeSecond);
                                // 2016/09/02 CSA-QC#11652 Add End
                                isFound = true;
                                hasTarget = true;
                                break;
                            }
                        } else {
                            childCountThird = childNodeSecond.getChildCount();
                            for (int c = 0; c < childCountThird; c++) {

                                currentNodeList.clear();
                                currentNodeList.add(0, childNodeSecond.getChild(c));
                                childNodeThird = childNodeSecond.getChild(c);
                                nodeValue = S21TreeView.getNodeColumnValue(childNodeThird);
                                if (!childNodeThird.isLeaf() && target.equals(nodeValue)) {
                                    if (setFocusTreeInfo(bMsg, treeView, childNodeThird, currentNodeList, focusOnFlag)) {
                                        // 2016/09/02 CSA-QC#11652 Add Start
                                        treeView.setExpanded(rootNode);
                                        treeView.setExpanded(childNodeFirst);
                                        treeView.setExpanded(childNodeSecond);
                                        treeView.setExpanded(childNodeThird);
                                        // 2016/09/02 CSA-QC#11652 Add End
                                        isFound = true;
                                        hasTarget = true;
                                        break;
                                    }
                                } else {
                                    childCountFourth = childNodeThird.getChildCount();
                                    for (int d = 0; d < childCountFourth; d++) {

                                        currentNodeList.clear();
                                        currentNodeList.add(0, childNodeThird.getChild(d));
                                        childNodeFourth = childNodeThird.getChild(d);
                                        nodeValue = S21TreeView.getNodeColumnValue(childNodeFourth);
                                        if (!childNodeFourth.isLeaf() && target.equals(nodeValue)) {
                                            if (setFocusTreeInfo(bMsg, treeView, childNodeFourth, currentNodeList, focusOnFlag)) {
                                                // 2016/09/02 CSA-QC#11652 Add Start
                                                treeView.setExpanded(rootNode);
                                                treeView.setExpanded(childNodeFirst);
                                                treeView.setExpanded(childNodeSecond);
                                                treeView.setExpanded(childNodeThird);
                                                treeView.setExpanded(childNodeFourth);
                                                // 2016/09/02 CSA-QC#11652 Add End
                                                isFound = true;
                                                hasTarget = true;
                                                break;
                                            }
                                        } else {
                                            childCountFifth = childNodeFourth.getChildCount();
                                            for (int e = 0; e < childCountFifth; e++) {

                                                currentNodeList.clear();
                                                currentNodeList.add(0, childNodeFourth.getChild(e));
                                                childNodeFifth = childNodeFourth.getChild(e);
                                                nodeValue = S21TreeView.getNodeColumnValue(childNodeFifth);
                                                if (!childNodeFifth.isLeaf() && target.equals(nodeValue)) {
                                                    if (setFocusTreeInfo(bMsg, treeView, childNodeFifth, currentNodeList, focusOnFlag)) {
                                                        // 2016/09/02 CSA-QC#11652 Add Start
                                                        treeView.setExpanded(rootNode);
                                                        treeView.setExpanded(childNodeFirst);
                                                        treeView.setExpanded(childNodeSecond);
                                                        treeView.setExpanded(childNodeThird);
                                                        treeView.setExpanded(childNodeFourth);
                                                        treeView.setExpanded(childNodeFifth);
                                                        // 2016/09/02 CSA-QC#11652 Add End
                                                        isFound = true;
                                                        hasTarget = true;
                                                        break;
                                                    }
                                                } else {
                                                    childCountSixth = childNodeFifth.getChildCount();
                                                    for (int f = 0; f < childCountSixth; f++) {

                                                        currentNodeList.clear();
                                                        currentNodeList.add(0, childNodeFifth.getChild(f));
                                                        childNodeSixth = childNodeFifth.getChild(f);
                                                        nodeValue = S21TreeView.getNodeColumnValue(childNodeSixth);
                                                        if (!childNodeSixth.isLeaf() && target.equals(nodeValue)) {
                                                            if (setFocusTreeInfo(bMsg, treeView, childNodeSixth, currentNodeList, focusOnFlag)) {
                                                                // 2016/09/02 CSA-QC#11652 Add Start
                                                                treeView.setExpanded(rootNode);
                                                                treeView.setExpanded(childNodeFirst);
                                                                treeView.setExpanded(childNodeSecond);
                                                                treeView.setExpanded(childNodeThird);
                                                                treeView.setExpanded(childNodeFourth);
                                                                treeView.setExpanded(childNodeFifth);
                                                                treeView.setExpanded(childNodeSixth);
                                                                // 2016/09/02 CSA-QC#11652 Add End
                                                                isFound = true;
                                                                hasTarget = true;
                                                                break;
                                                            }
                                                        } else {
                                                            childCountSeventh = childNodeSixth.getChildCount();
                                                            for (int g = 0; g < childCountSeventh; g++) {

                                                                currentNodeList.clear();
                                                                currentNodeList.add(0, childNodeSixth.getChild(g));
                                                                childNodeSeventh = childNodeSixth.getChild(g);
                                                                nodeValue = S21TreeView.getNodeColumnValue(childNodeSeventh);
                                                                if (!childNodeSeventh.isLeaf() && target.equals(nodeValue)) {
                                                                    if (setFocusTreeInfo(bMsg, treeView, childNodeSeventh, currentNodeList, focusOnFlag)) {
                                                                        // 2016/09/02 CSA-QC#11652 Add Start
                                                                        treeView.setExpanded(rootNode);
                                                                        treeView.setExpanded(childNodeFirst);
                                                                        treeView.setExpanded(childNodeSecond);
                                                                        treeView.setExpanded(childNodeThird);
                                                                        treeView.setExpanded(childNodeFourth);
                                                                        treeView.setExpanded(childNodeFifth);
                                                                        treeView.setExpanded(childNodeSixth);
                                                                        treeView.setExpanded(childNodeSeventh);
                                                                        // 2016/09/02 CSA-QC#11652 Add End
                                                                        isFound = true;
                                                                        hasTarget = true;
                                                                        break;
                                                                    }
                                                                } else {
                                                                    childCountEighth = childNodeSeventh.getChildCount();
                                                                    for (int h = 0; h < childCountEighth; h++) {

                                                                        currentNodeList.clear();
                                                                        currentNodeList.add(0, childNodeSeventh.getChild(h));
                                                                        childNodeEighth = childNodeSeventh.getChild(h);
                                                                        nodeValue = S21TreeView.getNodeColumnValue(childNodeEighth);
                                                                        if (!childNodeEighth.isLeaf() && target.equals(nodeValue)) {
                                                                            if (setFocusTreeInfo(bMsg, treeView, childNodeEighth, currentNodeList, focusOnFlag)) {
                                                                                // 2016/09/02 CSA-QC#11652 Add Start
                                                                                treeView.setExpanded(rootNode);
                                                                                treeView.setExpanded(childNodeFirst);
                                                                                treeView.setExpanded(childNodeSecond);
                                                                                treeView.setExpanded(childNodeThird);
                                                                                treeView.setExpanded(childNodeFourth);
                                                                                treeView.setExpanded(childNodeFifth);
                                                                                treeView.setExpanded(childNodeSixth);
                                                                                treeView.setExpanded(childNodeSeventh);
                                                                                treeView.setExpanded(childNodeEighth);
                                                                                // 2016/09/02 CSA-QC#11652 Add End
                                                                                isFound = true;
                                                                                hasTarget = true;
                                                                                break;
                                                                            }
                                                                        } else {
                                                                            childCountNinth = childNodeEighth.getChildCount();
                                                                            for (int i = 0; i < childCountNinth; i++) {

                                                                                currentNodeList.clear();
                                                                                currentNodeList.add(0, childNodeEighth.getChild(i));
                                                                                childNodeNinth = childNodeEighth.getChild(i);
                                                                                nodeValue = S21TreeView.getNodeColumnValue(childNodeNinth);
                                                                                if (!childNodeNinth.isLeaf() && target.equals(nodeValue)) {
                                                                                    if (setFocusTreeInfo(bMsg, treeView, childNodeNinth, currentNodeList, focusOnFlag)) {
                                                                                        // 2016/09/02 CSA-QC#11652 Add Start
                                                                                        treeView.setExpanded(rootNode);
                                                                                        treeView.setExpanded(childNodeFirst);
                                                                                        treeView.setExpanded(childNodeSecond);
                                                                                        treeView.setExpanded(childNodeThird);
                                                                                        treeView.setExpanded(childNodeFourth);
                                                                                        treeView.setExpanded(childNodeFifth);
                                                                                        treeView.setExpanded(childNodeSixth);
                                                                                        treeView.setExpanded(childNodeSeventh);
                                                                                        treeView.setExpanded(childNodeEighth);
                                                                                        treeView.setExpanded(childNodeNinth);
                                                                                        // 2016/09/02 CSA-QC#11652 Add End
                                                                                        isFound = true;
                                                                                        hasTarget = true;
                                                                                        break;
                                                                                    }
                                                                                } else {
                                                                                    childCountTenth = childNodeNinth.getChildCount();
                                                                                    for (int j = 0; j < childCountTenth; j++) {

                                                                                        currentNodeList.clear();
                                                                                        currentNodeList.add(0, childNodeNinth.getChild(j));
                                                                                        childNodeTenth = childNodeNinth.getChild(j);
                                                                                        nodeValue = S21TreeView.getNodeColumnValue(childNodeTenth);
                                                                                        if (!childNodeTenth.isLeaf() && target.equals(nodeValue)) {
                                                                                            if (setFocusTreeInfo(bMsg, treeView, childNodeTenth, currentNodeList, focusOnFlag)) {
                                                                                                // 2016/09/02 CSA-QC#11652 Add Start
                                                                                                treeView.setExpanded(rootNode);
                                                                                                treeView.setExpanded(childNodeFirst);
                                                                                                treeView.setExpanded(childNodeSecond);
                                                                                                treeView.setExpanded(childNodeThird);
                                                                                                treeView.setExpanded(childNodeFourth);
                                                                                                treeView.setExpanded(childNodeFifth);
                                                                                                treeView.setExpanded(childNodeSixth);
                                                                                                treeView.setExpanded(childNodeSeventh);
                                                                                                treeView.setExpanded(childNodeEighth);
                                                                                                treeView.setExpanded(childNodeNinth);
                                                                                                treeView.setExpanded(childNodeTenth);
                                                                                                // 2016/09/02 CSA-QC#11652 Add End
                                                                                                isFound = true;
                                                                                                hasTarget = true;
                                                                                                break;
                                                                                            } else {
                                                                                                continue;
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
            // 2016/07/27 CSA-QC#11652 Delete Start
            // if (isFound) {
            //    break;
            // }
            // 2016/07/27 CSA-QC#11652 Delete End
        }
        return hasTarget;
    }
    /**
     * setFocusTreeInfo
     * @param bMsg NMAL2500BMsg
     * @param treeView S21TreeView
     * @param currentTreeNode TreeNodeRow
     * @param currentNodeList List<TreeNodeRow>
     * @param focusOnFlg String
     * 
     * @return boolean
     */
    public static boolean setFocusTreeInfo(NMAL2500BMsg bMsg, S21TreeView treeView, TreeNodeRow currentTreeNode, List<TreeNodeRow> currentNodeList, String focusOnFlg) {

        if (ZYPConstant.FLG_ON_Y.equals(focusOnFlg)) {
            setFocusOn(treeView, currentNodeList, focusOnFlg, null);
        }

        String target = "";
        boolean isFound = false;

        int childCount = currentTreeNode.getChildCount();
        if (childCount > 0) {
            for (int x = 0; x < bMsg.R.getValidCount(); x++) {
                target = bMsg.R.no(x).xxPsnNm_R1.getValue();
                if (ZYPConstant.FLG_ON_Y.equals(bMsg.xxRsltFlg_H3.getValue())) {
                    focusOnFlg = bMsg.R.no(x).xxFrtChgMethDnldFlg_R1.getValue();
                }

                if (chkFocusTreeInfoForLeaf(treeView, currentTreeNode, target, focusOnFlg)) {
                    bMsg.R.no(x).xxFrtChgMethDnldFlg_R1.setValue(ZYPConstant.FLG_OFF_N);
                    isFound = true;
                }
            }
        }
        return isFound;
    }
    /**
     * chkFocusTreeInfoForLeaf
     * @param treeView S21TreeView
     * @param currentTreeNode TreeNodeRow
     * @param target String
     * @param focusOnFlg String
     * @return boolean
     */
    public static boolean chkFocusTreeInfoForLeaf(S21TreeView treeView, TreeNodeRow currentTreeNode, String target, String focusOnFlg) {

        List<TreeNodeRow> currentNodeList = new ArrayList<TreeNodeRow>();
        TreeNodeRow childNode;
        String nodeValue = "";
        boolean isFound = false;
        int childCount = currentTreeNode.getChildCount();
        if (childCount > 0) {
            for (int a = 0; a < childCount; a++) {
                currentNodeList.clear();
                currentNodeList.add(0, currentTreeNode.getChild(a));
                childNode = currentTreeNode.getChild(a);
                nodeValue = S21TreeView.getNodeColumnValue(childNode);
                if (childNode.isLeaf() && target.equals(nodeValue)) {
                    setFocusOn(treeView, currentNodeList, focusOnFlg, target);
                    setFocus(treeView, currentNodeList);
                    isFound =  true;
                    break;
                } else if (!childNode.isLeaf()) {
                    if (chkFocusTreeInfoForLeaf(treeView, childNode, target, focusOnFlg)) {
                        isFound = true;
                        break;
                    }
                }
            }
        }
        return isFound;
    }
    /**
     * Method name: convertTreeInfo <dd>The method explanation:
     * Processing for convert Tree Info
     * @param treeView Screen Component Interface Message
     * @param bMsg Screen Component Interface Message
     * @return boolean
     */
    public static boolean setFocusTreeInfoForOrganization(S21TreeView treeView, NMAL2500BMsg bMsg) {

        List<TreeNodeRow> currentNodeList = new ArrayList<TreeNodeRow>();
        TreeNodeRow rootNode = (TreeNodeRow) treeView.getRoot();

        String nodeValue = "";
        String target = bMsg.orgNm_H2.getValue();

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
        boolean hasTarget = false;
        String firstOrgFlag = null;

        childCountFirst = rootNode.getChildCount();
        for (int x = 0; x < bMsg.O.getValidCount(); x++) {

            target = bMsg.O.no(x).orgNm_O1.getValue();
            firstOrgFlag = bMsg.O.no(x).xxFrtChgMethDnldFlg_O1.getValue();
            isFound = false;

            for (int a = 0; a < childCountFirst; a++) {

                currentNodeList.clear();
                currentNodeList.add(0, rootNode.getChild(a));
                childNodeFirst = currentNodeList.get(0);
                nodeValue = S21TreeView.getNodeColumnValue(childNodeFirst);

                if (target.equals(nodeValue)) {
                    setFocusOn(treeView, currentNodeList, firstOrgFlag, target);
                    setFocus(treeView, currentNodeList);
                    treeView.setExpanded(rootNode);
                    // 2017/06/20 CSA-QC#19356 Add Star
                    if (NMAL2500Constant.DISPLAY_TOP_TIERS_ONLY == bMsg.xxRadioBtn_H1.getValueInt()) {
                        treeView.setCollapsed(childNodeFirst);
                    } else {
                        treeView.setExpanded(childNodeFirst);
                    }
                    // 2017/06/20 CSA-QC#19356 Add end
                    isFound = true;
                     hasTarget = true;
                    break;
                } else {
                    childCountSecond = childNodeFirst.getChildCount();

                    for (int b = 0; b < childCountSecond; b++) {
                        currentNodeList.clear();
                        currentNodeList.add(0, childNodeFirst.getChild(b));
                        childNodeSecond = childNodeFirst.getChild(b);
                        nodeValue = S21TreeView.getNodeColumnValue(childNodeSecond);

                        if (target.equals(nodeValue)) {
                            setFocusOn(treeView, currentNodeList, firstOrgFlag, target);
                            setFocus(treeView, currentNodeList);
                            // 2016/09/02 CSA-QC#11652 Add Start
                            treeView.setExpanded(rootNode);
                            treeView.setExpanded(childNodeFirst);
                            treeView.setExpanded(childNodeSecond); // Mod QC#19356
                            // 2016/09/02 CSA-QC#11652 Add End
                            isFound = true;
                            hasTarget = true;
                            break;
                        } else {
                            childCountThird = childNodeSecond.getChildCount();

                            for (int c = 0; c < childCountThird; c++) {
                                currentNodeList.clear();
                                currentNodeList.add(0, childNodeSecond.getChild(c));
                                childNodeThird = childNodeSecond.getChild(c);
                                nodeValue = S21TreeView.getNodeColumnValue(childNodeThird);

                                if (target.equals(nodeValue)) {
                                    setFocusOn(treeView, currentNodeList, firstOrgFlag, target);
                                    setFocus(treeView, currentNodeList);
                                    // 2016/09/02 CSA-QC#11652 Add Start
                                    treeView.setExpanded(rootNode);
                                    treeView.setExpanded(childNodeFirst);
                                    treeView.setExpanded(childNodeSecond);
                                    treeView.setExpanded(childNodeThird); // Mod QC#19356
                                    // 2016/09/02 CSA-QC#11652 Add End
                                    isFound = true;
                                    hasTarget = true;
                                    break;
                                } else {
                                    childCountFourth = childNodeThird.getChildCount();

                                    for (int d = 0; d < childCountFourth; d++) {
                                        currentNodeList.clear();
                                        currentNodeList.add(0, childNodeThird.getChild(d));
                                        childNodeFourth = childNodeThird.getChild(d);
                                        nodeValue = S21TreeView.getNodeColumnValue(childNodeFourth);

                                        if (target.equals(nodeValue)) {
                                            setFocusOn(treeView, currentNodeList, firstOrgFlag, target);
                                            setFocus(treeView, currentNodeList);
                                            // 2016/09/02 CSA-QC#11652 Add Start
                                            treeView.setExpanded(rootNode);
                                            treeView.setExpanded(childNodeFirst);
                                            treeView.setExpanded(childNodeSecond);
                                            treeView.setExpanded(childNodeThird);
                                            treeView.setExpanded(childNodeFourth); // Mod QC#19356
                                            // 2016/09/02 CSA-QC#11652 Add End
                                            isFound = true;
                                            hasTarget = true;
                                            break;
                                        } else {
                                            childCountFifth = childNodeFourth.getChildCount();

                                            for (int e = 0; e < childCountFifth; e++) {
                                                currentNodeList.clear();
                                                currentNodeList.add(0, childNodeFourth.getChild(e));
                                                childNodeFifth = childNodeFourth.getChild(e);
                                                nodeValue = S21TreeView.getNodeColumnValue(childNodeFifth);

                                                if (target.equals(nodeValue)) {
                                                    setFocusOn(treeView, currentNodeList, firstOrgFlag, target);
                                                    setFocus(treeView, currentNodeList);
                                                    // 2016/09/02 CSA-QC#11652 Add Start
                                                    treeView.setExpanded(rootNode);
                                                    treeView.setExpanded(childNodeFirst);
                                                    treeView.setExpanded(childNodeSecond);
                                                    treeView.setExpanded(childNodeThird);
                                                    treeView.setExpanded(childNodeFourth);
                                                    treeView.setExpanded(childNodeFifth); // Mod QC#19356
                                                    // 2016/09/02 CSA-QC#11652 Add End
                                                    isFound = true;
                                                    hasTarget = true;
                                                    break;
                                                } else {
                                                    childCountSixth = childNodeFifth.getChildCount();

                                                    for (int f = 0; f < childCountSixth; f++) {
                                                        currentNodeList.clear();
                                                        currentNodeList.add(0, childNodeFifth.getChild(f));
                                                        childNodeSixth = childNodeFifth.getChild(f);
                                                        nodeValue = S21TreeView.getNodeColumnValue(childNodeSixth);

                                                        if (target.equals(nodeValue)) {
                                                            setFocusOn(treeView, currentNodeList, firstOrgFlag, target);
                                                            setFocus(treeView, currentNodeList);
                                                            // 2016/09/02 CSA-QC#11652 Add Start
                                                            treeView.setExpanded(rootNode);
                                                            treeView.setExpanded(childNodeFirst);
                                                            treeView.setExpanded(childNodeSecond);
                                                            treeView.setExpanded(childNodeThird);
                                                            treeView.setExpanded(childNodeFourth);
                                                            treeView.setExpanded(childNodeFifth);
                                                            treeView.setExpanded(childNodeSixth); // Mod QC#19356
                                                            // 2016/09/02 CSA-QC#11652 Add End
                                                            isFound = true;
                                                            hasTarget = true;
                                                            break;
                                                        } else {
                                                            childCountSeventh = childNodeSixth.getChildCount();

                                                            for (int g = 0; g < childCountSeventh; g++) {
                                                                currentNodeList.clear();
                                                                currentNodeList.add(0, childNodeSixth.getChild(g));
                                                                childNodeSeventh = childNodeSixth.getChild(g);
                                                                nodeValue = S21TreeView.getNodeColumnValue(childNodeSeventh);

                                                                if (target.equals(nodeValue)) {
                                                                    setFocusOn(treeView, currentNodeList, firstOrgFlag, target);
                                                                    setFocus(treeView, currentNodeList);
                                                                    // 2016/09/02 CSA-QC#11652 Add Start
                                                                    treeView.setExpanded(rootNode);
                                                                    treeView.setExpanded(childNodeFirst);
                                                                    treeView.setExpanded(childNodeSecond);
                                                                    treeView.setExpanded(childNodeThird);
                                                                    treeView.setExpanded(childNodeFourth);
                                                                    treeView.setExpanded(childNodeFifth);
                                                                    treeView.setExpanded(childNodeSixth);
                                                                    treeView.setExpanded(childNodeSeventh); // Mod QC#19356
                                                                    // 2016/09/02 CSA-QC#11652 Add End
                                                                    isFound = true;
                                                                    hasTarget = true;
                                                                    break;
                                                                } else {
                                                                    childCountEighth = childNodeSeventh.getChildCount();

                                                                    for (int h = 0; h < childCountEighth; h++) {
                                                                        currentNodeList.clear();
                                                                        currentNodeList.add(0, childNodeSeventh.getChild(h));
                                                                        childNodeEighth = childNodeSeventh.getChild(h);
                                                                        nodeValue = S21TreeView.getNodeColumnValue(childNodeEighth);

                                                                        if (target.equals(nodeValue)) {
                                                                            setFocusOn(treeView, currentNodeList, firstOrgFlag, target);
                                                                            setFocus(treeView, currentNodeList);
                                                                            // 2016/09/02 CSA-QC#11652 Add Start
                                                                            treeView.setExpanded(rootNode);
                                                                            treeView.setExpanded(childNodeFirst);
                                                                            treeView.setExpanded(childNodeSecond);
                                                                            treeView.setExpanded(childNodeThird);
                                                                            treeView.setExpanded(childNodeFourth);
                                                                            treeView.setExpanded(childNodeFifth);
                                                                            treeView.setExpanded(childNodeSixth);
                                                                            treeView.setExpanded(childNodeSeventh);
                                                                            treeView.setExpanded(childNodeEighth); // Mod QC#19356
                                                                            // 2016/09/02 CSA-QC#11652 Add End
                                                                            isFound = true;
                                                                            hasTarget = true;
                                                                            break;
                                                                        } else {
                                                                            childCountNinth = childNodeEighth.getChildCount();

                                                                            for (int i = 0; i < childCountNinth; i++) {
                                                                                currentNodeList.clear();
                                                                                currentNodeList.add(0, childNodeEighth.getChild(i));
                                                                                childNodeNinth = childNodeEighth.getChild(i);
                                                                                nodeValue = S21TreeView.getNodeColumnValue(childNodeNinth);

                                                                                if (target.equals(nodeValue)) {
                                                                                    setFocusOn(treeView, currentNodeList, firstOrgFlag, target);
                                                                                    setFocus(treeView, currentNodeList);
                                                                                    // 2016/09/02 CSA-QC#11652 Add Start
                                                                                    treeView.setExpanded(rootNode);
                                                                                    treeView.setExpanded(childNodeFirst);
                                                                                    treeView.setExpanded(childNodeSecond);
                                                                                    treeView.setExpanded(childNodeThird);
                                                                                    treeView.setExpanded(childNodeFourth);
                                                                                    treeView.setExpanded(childNodeFifth);
                                                                                    treeView.setExpanded(childNodeSixth);
                                                                                    treeView.setExpanded(childNodeSeventh);
                                                                                    treeView.setExpanded(childNodeEighth);
                                                                                    treeView.setExpanded(childNodeNinth); // Mod QC#19356
                                                                                    // 2016/09/02 CSA-QC#11652 Add End
                                                                                    isFound = true;
                                                                                    hasTarget = true;
                                                                                    break;
                                                                                } else {
                                                                                    childCountTenth = childNodeNinth.getChildCount();

                                                                                    for (int j = 0; j < childCountTenth; j++) {
                                                                                        currentNodeList.clear();
                                                                                        currentNodeList.add(0, childNodeNinth.getChild(j));
                                                                                        childNodeTenth = childNodeNinth.getChild(j);
                                                                                        nodeValue = S21TreeView.getNodeColumnValue(childNodeTenth);

                                                                                        if (target.equals(nodeValue)) {
                                                                                            setFocusOn(treeView, currentNodeList, firstOrgFlag, target);
                                                                                            setFocus(treeView, currentNodeList);
                                                                                            // 2016/09/02 CSA-QC#11652 Add Start
                                                                                            treeView.setExpanded(rootNode);
                                                                                            treeView.setExpanded(childNodeFirst);
                                                                                            treeView.setExpanded(childNodeSecond);
                                                                                            treeView.setExpanded(childNodeThird);
                                                                                            treeView.setExpanded(childNodeFourth);
                                                                                            treeView.setExpanded(childNodeFifth);
                                                                                            treeView.setExpanded(childNodeSixth);
                                                                                            treeView.setExpanded(childNodeSeventh);
                                                                                            treeView.setExpanded(childNodeEighth);
                                                                                            treeView.setExpanded(childNodeNinth);
                                                                                            treeView.setExpanded(childNodeTenth); // Mod QC#19356
                                                                                            // 2016/09/02 CSA-QC#11652 Add End
                                                                                            isFound = true;
                                                                                            hasTarget = true;
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
        return hasTarget;
    }

    /**
     * The method explanation: Change background color and focus on
     * @param treeView S21TreeView
     * @param currentNodeList List<TreeNodeRow>
     */
    public static void setFocus(S21TreeView treeView, List<TreeNodeRow> currentNodeList) {

        // set focus on radio button
        //treeView.setCheckboxON(currentNodeList.get(0), NMAL2500Constant.COLUMN_INDEX_RADIO_BUTTON);
        //treeView.setFocusTreeView(true);

        // change background color of focus.
        //treeView.setBackgroundColor(null);
        S21TreeView.setBackgroundColor(currentNodeList.get(0), NMAL2500Constant.BACKGROUND_COLOR_YELLOW);
    }

    /**
     * The method explanation: Set focus on
     * @param treeView S21TreeView
     * @param currentNodeList List<TreeNodeRow>
     * @param focusOnFlg String
     */
    public static void setFocusOn(S21TreeView treeView, List<TreeNodeRow> currentNodeList, String focusOnFlg, String target) {
       if (ZYPConstant.FLG_ON_Y.equals(focusOnFlg)) {
           treeView.setCheckboxON(currentNodeList.get(0), NMAL2500Constant.COLUMN_INDEX_RADIO_BUTTON);
       }
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL2500BMsg
     * @q   w       return Object[]
     */
    public static Object[] setParamForOrganizationSearchPopup(NMAL2500BMsg scrnMsg) {

        Object[] params = new Object[7];

        initParam(scrnMsg);

        if (ZYPCommonFunc.hasValue(scrnMsg.bizAreaOrgCd_P1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0, scrnMsg.bizAreaOrgCd_P1);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.orgNm_H2)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_1, scrnMsg.orgNm_H2);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxPsnNm_H3)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_3, scrnMsg.xxPsnNm_H3);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.psnCd_H3)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_4, scrnMsg.psnCd_H3);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.orgTierCd_P2)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_5, scrnMsg.orgTierCd_P2);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.csrRgTpCd_P2)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_6, scrnMsg.csrRgTpCd_P2);
        }

        // Business Area
        params[0] = scrnMsg.xxPopPrm_0;
        // Organization Name
        params[1] = scrnMsg.xxPopPrm_1;
        // Parent Organization Name
        params[2] = scrnMsg.xxPopPrm_2;
        // Resource Name
        params[3] = scrnMsg.xxPopPrm_3;
        // Employee ID
        params[4] = scrnMsg.xxPopPrm_4;
        // Tier
        params[5] = scrnMsg.xxPopPrm_5;
        // CSR Region
        params[6] = scrnMsg.xxPopPrm_6;

        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL2500BMsg
     * @return Object[]
     */
    public static Object[] setParamForTerritorySearchPopup(NMAL2500BMsg scrnMsg) {

        Object[] params = new Object[8];

        initParam(scrnMsg);

        if (ZYPCommonFunc.hasValue(scrnMsg.bizAreaOrgCd_P1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0, scrnMsg.bizAreaOrgCd_P1);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.orgNm_H3)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_1, scrnMsg.orgNm_H3);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxPsnNm_H3)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_3, scrnMsg.xxPsnNm_H3);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.psnCd_H3)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_4, scrnMsg.psnCd_H3);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.orgNm_H2)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_7, scrnMsg.orgNm_H2);
        }

        // Business Area
        params[0] = scrnMsg.xxPopPrm_0;
        // Territory Name
        params[1] = scrnMsg.xxPopPrm_1;
        // Parent Territory Name
        params[2] = scrnMsg.xxPopPrm_2;
        // Resource Name
        params[3] = scrnMsg.xxPopPrm_3;
        // Employee ID
        params[4] = scrnMsg.xxPopPrm_4;
        // Rank
        params[5] = scrnMsg.xxPopPrm_5;
        // Line of Business
        params[6] = scrnMsg.xxPopPrm_6;
        // Organization Name
        params[7] = scrnMsg.xxPopPrm_7;

        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL2500BMsg
     */
    public static void initParam(NMAL2500BMsg scrnMsg) {

        scrnMsg.xxPopPrm_0.clear();
        scrnMsg.xxPopPrm_1.clear();
        scrnMsg.xxPopPrm_2.clear();
        scrnMsg.xxPopPrm_3.clear();
        scrnMsg.xxPopPrm_4.clear();
        scrnMsg.xxPopPrm_5.clear();
        scrnMsg.xxPopPrm_6.clear();
        scrnMsg.xxPopPrm_7.clear();

    }

    // 2016/03/04 S21_NA#4539 Add Start ------------
    /**
     * addcheckItemH2
     * @param scrnMsg NMAL2500BMsg
     */
    public static void addcheckItemH2 (NMAL2500BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.orgNm_H2);
    }

    /**
     * addcheckItemH3
     * @param scrnMsg NMAL2500BMsg
     */
    public static void addcheckItemH3 (NMAL2500BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.psnCd_H3);
        scrnMsg.addCheckItem(scrnMsg.xxPsnNm_H3);
        scrnMsg.addCheckItem(scrnMsg.jobTtlCd_H3);
        scrnMsg.addCheckItem(scrnMsg.hrTtlNm_H3);
        scrnMsg.addCheckItem(scrnMsg.psnNum_H3);
        scrnMsg.addCheckItem(scrnMsg.orgNm_H3);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_FR);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_TO);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_FR);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_TO);
    }
    /**
     * addcheckItemH3
     * @param scrnMsg NMAL2500BMsg
     */
    public static void addcheckItemH4 (NMAL2500BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H4);
    }
    // 2016/03/04 S21_NA#4539 Add End ------------
    
    /**
     * checkInput
     * @param scrnMsg NMAL2500BMsg
     */
    public static void checkInput(NMAL2500BMsg scrnMsg){

        if (NMAL2500Constant.SEARCH_MODE_SHOW_HIERARCHY.equals(scrnMsg.xxModeCd_P1.getValue())) {

            if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_H2.getValue())) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.orgNm_H2.getValue())) {
                    scrnMsg.orgNm_H2.setErrorInfo(1, NMAL2500Constant.ZZZM9007E, new String[] {NMAL2500Constant.NAME_FOR_MESSAGE_ORG_NM });
                    scrnMsg.addCheckItem(scrnMsg.orgNm_H2);
                }
            } else if (NMAL2500Constant.DISPLAY_ORG_LEVEL_CHILDREN_ONLY == scrnMsg.xxRadioBtn_H1.getValueInt()) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.orgNm_H2.getValue())) {
                    scrnMsg.orgNm_H2.setErrorInfo(1, NMAL2500Constant.ZZZM9007E, new String[] {NMAL2500Constant.NAME_FOR_MESSAGE_ORG_NM });
                    scrnMsg.addCheckItem(scrnMsg.orgNm_H2);
                }
            } else if (NMAL2500Constant.DISPLAY_ORG_LEVEL_CHILDREN_RESOURCE == scrnMsg.xxRadioBtn_H1.getValueInt()) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.orgNm_H2.getValue())) {
                    scrnMsg.orgNm_H2.setErrorInfo(1, NMAL2500Constant.ZZZM9007E, new String[] {NMAL2500Constant.NAME_FOR_MESSAGE_ORG_NM });
                    scrnMsg.addCheckItem(scrnMsg.orgNm_H2);
                }
            }

        } else if (NMAL2500Constant.SEARCH_MODE_QUICK_RESOURCE_LOOK_UP.equals(scrnMsg.xxModeCd_P1.getValue())) {
            scrnMsg.putErrorScreen();
        } else {
            scrnMsg.xxModeCd_P1.setErrorInfo(1, NMAL2500Constant.ZZZM9007E, new String[] {NMAL2500Constant.NAME_FOR_MESSAGE_SEARCH_MODE });
            scrnMsg.addCheckItem(scrnMsg.xxModeCd_P1);
        }

        scrnMsg.addCheckItem(scrnMsg.orgNm_H2);
        scrnMsg.addCheckItem(scrnMsg.psnCd_H3);
        scrnMsg.addCheckItem(scrnMsg.xxPsnNm_H3);
        scrnMsg.addCheckItem(scrnMsg.jobTtlCd_H3);
        scrnMsg.addCheckItem(scrnMsg.hrTtlNm_H3);
        scrnMsg.addCheckItem(scrnMsg.psnNum_H3);
        scrnMsg.addCheckItem(scrnMsg.orgNm_H3);
        // MOD START S21_NA QC#16481
        scrnMsg.addCheckItem(scrnMsg.effFromDt_FR);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_TO);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_FR);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_TO);
        // MOD END S21_NA QC#16481
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H4);
        scrnMsg.putErrorScreen();

    }
    
    // ADD START S21_NA QC#16481
    /**
     * Check Consistency of Date Column
     * @param scrnMsg NMAL0170BMsg
     */
    public static void checkDate(NMAL2500BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.effFromDt_TO) && ZYPDateUtil.compare(scrnMsg.effFromDt_FR.getValue(), scrnMsg.effFromDt_TO.getValue()) > 0) {
            scrnMsg.effFromDt_FR.setErrorInfo(1, NMAL2500Constant.NMAM0185E);
            scrnMsg.effFromDt_TO.setErrorInfo(1, NMAL2500Constant.NMAM0185E);
        }
        
        if (ZYPCommonFunc.hasValue(scrnMsg.effThruDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.effThruDt_TO) && ZYPDateUtil.compare(scrnMsg.effThruDt_FR.getValue(), scrnMsg.effThruDt_TO.getValue()) > 0) {
            scrnMsg.effThruDt_FR.setErrorInfo(1, NMAL2500Constant.NMAM0185E);
            scrnMsg.effThruDt_TO.setErrorInfo(1, NMAL2500Constant.NMAM0185E);
        }
        
    }
    // ADD END S21_NA QC#16481
}
