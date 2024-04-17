/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2520.common;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsgArray;
import parts.common.EZDCommonConst;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NMAL2520.NMAL2520_TCMsgArray;
import business.servlet.NMAL2520.NMAL2520BMsg;
import business.servlet.NMAL2520.constant.NMAL2520Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.online.treeview.S21TreeView;
import com.canon.cusa.s21.framework.online.treeview.S21TreeViewCreator;
import com.canon.cusa.s21.framework.online.treeview.S21TreeViewCreatorIF;
import com.canon.cusa.s21.framework.online.treeview.S21TreeViewNodeColumn;
import com.canon.cusa.s21.framework.online.treeview.S21TreeViewRadioColumn;
import com.fujitsu.uji.compo.TreeNodeRow;

/**
 *<pre>
 * Resource Search NMAL24000CommonLogic
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/10   Fujitsu         J.Sakamoto      Create          N/A
 * 2015/11/10   Fujitsu         K.Koitabashi    Update          N/A
 * 2016/02/05   Fujitsu         C.Yokoi         Update          CSA-QC#2869
 * 2016/03/03   Fujitsu         W.Honda         Update          CSA-QC#4545
 * 2016/03/04   Fujitsu         C.Yokoi         Update          CSA-QC#4654
 * 2016/03/30   SRAA            Y.Chen          Update          CSA-QC#4429
 * 2016/08/31   SRAA            Y.Chen          Update          QC#7966
 * 2016/10/07   Hitachi         Y.Takeno        Update          QC#13431
 * 2016/10/11   Hitachi         Y.Takeno        Update          QC#13431-1
 * 2017/06/14   Hitachi         J.Kim           Update          QC#18924
 * 2017/12/19   Fujitsu         N.Sugiura       Update          QC#21893-2
 * 2018/09/20   Fujitsu         S.Kosaka        Update          QC#27724
 *</pre>
 */

public class NMAL2520CommonLogic {

    /**
     * <pre>
     * Details are initialized (sorting sign deletion and BG color control).
     * </pre>
     * @param scrnMsg Screen Msg
     * @param baseContents String[][]
     */
    public static void clearGUIAttribute(NMAL2520BMsg scrnMsg, String[][] baseContents) {
        S21SortColumnIMGController.clearIMG(NMAL2520Constant.SCREEN_ID, scrnMsg, baseContents);
        scrnMsg.clearAllGUIAttribute(NMAL2520Constant.SCREEN_ID);
    }

    /**
     * set alternate rows background
     * @param scrnMsg screen message
     */
    public static void setTableAttribute(NMAL2520BMsg scrnMsg) {
        // TO-DO
        // S21TableColorController tblColor = new
        // S21TableColorController(NMAL2520Constant.SCREEN_ID,
        // scrnMsg);
    }

    /**
     * <pre>
     * Common protect control
     * </pre>
     * @param handler EZCommandHandler
     * @param scrnMsg NMAL2520BMsg
     */
    public static void commonControl(EZDCommonHandler handler, NMAL2520BMsg scrnMsg) {
        initCommonButton(handler);
        // checkAuth(handler, scrnMsg);
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2520BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NMAL2520BMsg scrnMsg) {
        initCommonButton(handler);
        initButton(handler);
        // QC#13431
        controlAttachmentsButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);
        // QC#4429
        controlContract(scrnMsg);
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2520BMsg
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NMAL2520BMsg scrnMsg) {

        controlScreenDetailFields(handler, scrnMsg);
    }

    /**
     * controlScreenDetailFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2520BMsg
     */
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NMAL2520BMsg scrnMsg) {
        setIniOrgStatDate(scrnMsg);
        controlBusinessAreaFields(scrnMsg);
        scrnMsg.lineBizTpCd_P1.setInputProtected(true);
        scrnMsg.xxChkBox_H1.setInputProtected(true);

        scrnMsg.setFocusItem(scrnMsg.bizAreaOrgCd_P1);
    }

    /**
     * setIniOrgStatDate
     * @param scrnMsg NMAL2520BMsg
     */
    private static void setIniOrgStatDate(NMAL2520BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.effFromDt_H1)) {
             // Get Sales Date
            final String slsDt = ZYPDateUtil.getSalesDate();
             // Set Start Date
            scrnMsg.effFromDt_H1.setValue(slsDt);
        }
    }

    /**
     * controlBusinessAreaFields
     * @param scrnMsg NMAL2520BMsg
     */
    public static void controlBusinessAreaFields(NMAL2520BMsg scrnMsg) {
        // 2016/03/04 CSA-QC#4654 Add Start
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.actvFlg_BA.getValue())) {
            scrnMsg.bizAreaOrgCd_P1.setInputProtected(true);
        } else {
            scrnMsg.bizAreaOrgCd_P1.setInputProtected(false);
        }
        // 2016/03/04 CSA-QC#4654 Add End
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     */
    public static void initButton(EZDCommonHandler handler) {

        handler.setButtonEnabled(NMAL2520Constant.BTN_SEARCH[0], true);
    }

    /**
     * <pre>
     * Initial common button
     * </pre>
     * @param handler EZCommandHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(NMAL2520Constant.BTN_CMN_BTN_SAVE[0], NMAL2520Constant.BTN_CMN_BTN_SAVE[1], NMAL2520Constant.BTN_CMN_BTN_SAVE[2], 0, null);
        handler.setButtonProperties(NMAL2520Constant.BTN_CMN_BTN_SUBMIT[0], NMAL2520Constant.BTN_CMN_BTN_SUBMIT[1], NMAL2520Constant.BTN_CMN_BTN_SUBMIT[2], 1, null);
        handler.setButtonProperties(NMAL2520Constant.BTN_CMN_BTN_APPLY[0], NMAL2520Constant.BTN_CMN_BTN_APPLY[1], NMAL2520Constant.BTN_CMN_BTN_APPLY[2], 0, null);
        handler.setButtonProperties(NMAL2520Constant.BTN_CMN_BTN_APPROVE[0], NMAL2520Constant.BTN_CMN_BTN_APPROVE[1], NMAL2520Constant.BTN_CMN_BTN_APPROVE[2], 0, null);
        handler.setButtonProperties(NMAL2520Constant.BTN_CMN_BTN_REJECT[0], NMAL2520Constant.BTN_CMN_BTN_REJECT[1], NMAL2520Constant.BTN_CMN_BTN_REJECT[2], 0, null);
        handler.setButtonProperties(NMAL2520Constant.BTN_CMN_BTN_DOWNLOAD[0], NMAL2520Constant.BTN_CMN_BTN_DOWNLOAD[1], NMAL2520Constant.BTN_CMN_BTN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(NMAL2520Constant.BTN_CMN_BTN_DELETE[0], NMAL2520Constant.BTN_CMN_BTN_DELETE[1], NMAL2520Constant.BTN_CMN_BTN_DELETE[2], 0, null);
        handler.setButtonProperties(NMAL2520Constant.BTN_CMN_BTN_CLEAR[0], NMAL2520Constant.BTN_CMN_BTN_CLEAR[1], NMAL2520Constant.BTN_CMN_BTN_CLEAR[2], 1, null);
        handler.setButtonProperties(NMAL2520Constant.BTN_CMN_BTN_RESET[0], NMAL2520Constant.BTN_CMN_BTN_RESET[1], NMAL2520Constant.BTN_CMN_BTN_RESET[2], 1, null);
        handler.setButtonProperties(NMAL2520Constant.BTN_CMN_BTN_RETURN[0], NMAL2520Constant.BTN_CMN_BTN_RETURN[1], NMAL2520Constant.BTN_CMN_BTN_RETURN[2], 1, null);
    }

    // QC#13431
    /**
     * controlAttachmentsButton
     * 
     * @param handler EZCommandHandler
     * @param scrnMsg NMAL2520BMsg
     */
    public static final void controlAttachmentsButton(EZDCommonHandler handler, NMAL2520BMsg scrnMsg) {
        if (ZYPCommonFunc.hasValue(scrnMsg.orgCd_H1)) {
            handler.setButtonEnabled(NMAL2520Constant.BTN_ATTACHMENTS, true);
        } else {
            handler.setButtonEnabled(NMAL2520Constant.BTN_ATTACHMENTS, false);
        }
    }

    /**
     * <pre>
     * clear mandantory check
     * </pre>
     * @param scrnMsg NMAL2520BMsg
     */
    public static void clearMandantoryCheck(NMAL2520BMsg scrnMsg) {
        // ### Header ###
        // Business Area
        if (scrnMsg.bizAreaOrgCd_P1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("bizAreaOrgCd_P1");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.bizAreaOrgCd_P1.clearErrorInfo();
            }
        }

        // Organization Name
        if (scrnMsg.orgNm_H1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("orgNm_H1");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.orgNm_H1.clearErrorInfo();
            }
        }

        // Tier (LEVEL)
        if (scrnMsg.orgTierCd_P1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("orgTierCd_P1");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.orgTierCd_P1.clearErrorInfo();
            }
        }

        // ### Build Hierarchy ###
        if (NMAL2520Constant.TAB_BUILD_HIERARCHY.equals(scrnMsg.xxDplyTab.getValue())) {
            // Parent Organization / Team
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (scrnMsg.A.no(i).effFromDt_A1.isError()) {
                    EZDMessageInfo ezdMsgInfo = scrnMsg.A.no(i).getErrorInfo("effFromDt_A1");

                    if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                        scrnMsg.A.no(i).effFromDt_A1.clearErrorInfo();
                    }
                }
            }
        }

        // ### Research Assign ###
        if (NMAL2520Constant.TAB_RESRC_ASIGN.equals(scrnMsg.xxDplyTab.getValue())) {
            // Resource Assignment(s)
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                if (scrnMsg.C.no(i).effFromDt_C1.isError()) {
                    EZDMessageInfo ezdMsgInfo = scrnMsg.C.no(i).getErrorInfo("effFromDt_C1");

                    if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                        scrnMsg.C.no(i).effFromDt_C1.clearErrorInfo();
                    }
                }
            }
        }

        // ### Build Hierarchy ###
        if (NMAL2520Constant.TAB_BUILD_HIERARCHY.equals(scrnMsg.xxDplyTab.getValue())) {
            // Parent Organization / Team
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (scrnMsg.A.no(i).orgNm_A1.isError()) {
                    EZDMessageInfo ezdMsgInfo = scrnMsg.A.no(i).getErrorInfo("orgNm_A1");

                    if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                        scrnMsg.A.no(i).orgNm_A1.clearErrorInfo();
                    }
                }
            }
        }
    }

    /**
     * <pre>
     * addCheck input items
     * </pre>
     * @param scrnMsg NMAL2520BMsg
     */
    public static void addCheckItems(NMAL2520BMsg scrnMsg) {
        // ### Header ###
        scrnMsg.addCheckItem(scrnMsg.bizAreaOrgCd_P1);
        scrnMsg.addCheckItem(scrnMsg.orgNm_H1);
        scrnMsg.addCheckItem(scrnMsg.orgTierCd_P1);
        scrnMsg.addCheckItem(scrnMsg.orgDescTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.orgShortNm_H1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);

        // 2016/03/03 CSA-QC#4545 Add Start
        scrnMsg.addCheckItem(scrnMsg.orgCd_H1);
        scrnMsg.addCheckItem(scrnMsg.lineBizTpCd_P1);
        scrnMsg.addCheckItem(scrnMsg.orgTierDescTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.csrRgTpCd_P1);
        // 2016/03/03 CSA-QC#4545 Add End

        // ### Build Hierarchy ###
        if (NMAL2520Constant.TAB_BUILD_HIERARCHY.equals(scrnMsg.xxDplyTab.getValue())) {
            // Parent Organization / Team
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                // 2016/03/03 CSA-QC#4545 Add Start
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).orgCd_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).orgNm_A1);
                // 2016/03/03 CSA-QC#4545 Add End
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A1);
            }

            // Child Organization Team
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                // 2016/03/03 CSA-QC#4545 Add Start
                scrnMsg.addCheckItem(scrnMsg.B.no(i).orgCd_B1);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).orgNm_B1);
                // 2016/03/03 CSA-QC#4545 Add End
                scrnMsg.addCheckItem(scrnMsg.B.no(i).effFromDt_B1);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).effThruDt_B1);
            }
        }

        // ### Research Assign ###
        if (NMAL2520Constant.TAB_RESRC_ASIGN.equals(scrnMsg.xxDplyTab.getValue())) {
            // Resource Assignment(s)
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                // 2016/03/03 CSA-QC#4545 Add Start
                scrnMsg.addCheckItem(scrnMsg.C.no(i).xxChkBox_C1);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).psnNum_C1);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).orgFuncRoleTpCd_P1);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).psnLastNm_C1);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).psnFirstNm_C1);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).asgCustFromNm_C1);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).asgCustToNm_C1);
                // 2016/03/03 CSA-QC#4545 Add End
                scrnMsg.addCheckItem(scrnMsg.C.no(i).effFromDt_C1);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).effThruDt_C1);
            }
        }
    }

    /**
     * addTabBuildCheckItem
     * @param scrnMsg NMAL2520BMsg
     */
    public static void addTabBuildCheckItem(NMAL2520BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
        }
    }

    /**
     * addTabResrcCheckItem
     * @param scrnMsg NMAL2520BMsg
     */
    public static void addTabResrcCheckItem(NMAL2520BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.C.no(i).orgFuncRoleTpCd_P1);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).xxChkBox_C1);
        }
    }

    /**
     * Method name: convertTreeInfo <dd>The method explanation:
     * Processing for convert Tree Info
     * @param bMsg NMAL2520BMsg
     * @param cMsgArray cMsgArray
     */
    public static void convertTreeInfo(NMAL2520BMsg bMsg, NMAL2520_TCMsgArray cMsgArray) {
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

        S21TreeViewRadioColumn radioCol = new S21TreeViewRadioColumn();

        S21TreeViewCreatorIF tViewCreatorIF = new S21TreeViewCreatorIF(cMsgArray, nodeCol);
        tViewCreatorIF.addColumnType(radioCol);

        S21TreeView treeView = S21TreeViewCreator.create(tViewCreatorIF, NMAL2520Constant.TREEVIEWMOVEFTO);

        if (null != treeView) {
            treeView.setExpandedNodeIcon();

            setFocusTreeInfo(treeView, bMsg);

            bMsg.setTreeView(treeView);
        } else {
            bMsg.setTreeView(null);
        }
    }

    /**
     * Method name: convertTreeInfo <dd>The method explanation:
     * Processing for convert Tree Info
     * @param treeView S21TreeView
     * @param bMsg NMAL2520BMsg
     */
    public static void setFocusTreeInfo(S21TreeView treeView, NMAL2520BMsg bMsg) {

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

        childCountFirst = rootNode.getChildCount();

        for (int a = 0; a < childCountFirst; a++) {
            currentNodeList.clear();
            currentNodeList.add(0, rootNode.getChild(a));
            childNodeFirst = currentNodeList.get(0);
            nodeValue = S21TreeView.getNodeColumnValue(childNodeFirst);

            if (target.equals(nodeValue)) {
                setFocus(treeView, currentNodeList);
                break;
            } else {
                childCountSecond = childNodeFirst.getChildCount();

                for (int b = 0; b < childCountSecond; b++) {
                    currentNodeList.clear();
                    currentNodeList.add(0, childNodeFirst.getChild(b));
                    childNodeSecond = childNodeFirst.getChild(b);
                    nodeValue = S21TreeView.getNodeColumnValue(childNodeSecond);

                    if (target.equals(nodeValue)) {
                        setFocus(treeView, currentNodeList);
                        return;
                    } else {
                        childCountThird = childNodeSecond.getChildCount();

                        for (int c = 0; c < childCountThird; c++) {
                            currentNodeList.clear();
                            currentNodeList.add(0, childNodeSecond.getChild(c));
                            childNodeThird = childNodeSecond.getChild(c);
                            nodeValue = S21TreeView.getNodeColumnValue(childNodeThird);

                            if (target.equals(nodeValue)) {
                                setFocus(treeView, currentNodeList);
                                return;

                            } else {

                                childCountFourth = childNodeThird.getChildCount();

                                for (int d = 0; d < childCountFourth; d++) {
                                    currentNodeList.clear();
                                    currentNodeList.add(0, childNodeThird.getChild(d));
                                    childNodeFourth = childNodeThird.getChild(d);
                                    nodeValue = S21TreeView.getNodeColumnValue(childNodeFourth);

                                    if (target.equals(nodeValue)) {
                                        setFocus(treeView, currentNodeList);
                                        return;

                                    } else {

                                        childCountFifth = childNodeFourth.getChildCount();

                                        for (int e = 0; e < childCountFifth; e++) {
                                            currentNodeList.clear();
                                            currentNodeList.add(0, childNodeFourth.getChild(e));
                                            childNodeFifth = childNodeFourth.getChild(e);
                                            nodeValue = S21TreeView.getNodeColumnValue(childNodeFifth);

                                            if (target.equals(nodeValue)) {
                                                setFocus(treeView, currentNodeList);
                                                return;

                                            } else {
                                                childCountSixth = childNodeFifth.getChildCount();

                                                for (int f = 0; f < childCountSixth; f++) {
                                                    currentNodeList.clear();
                                                    currentNodeList.add(0, childNodeFifth.getChild(f));
                                                    childNodeSixth = childNodeFifth.getChild(f);
                                                    nodeValue = S21TreeView.getNodeColumnValue(childNodeSixth);

                                                    if (target.equals(nodeValue)) {
                                                        setFocus(treeView, currentNodeList);
                                                        return;

                                                    } else {
                                                        childCountSeventh = childNodeSixth.getChildCount();

                                                        for (int g = 0; g < childCountSeventh; g++) {
                                                            currentNodeList.clear();
                                                            currentNodeList.add(0, childNodeSixth.getChild(g));
                                                            childNodeSeventh = childNodeSixth.getChild(g);
                                                            nodeValue = S21TreeView.getNodeColumnValue(childNodeSeventh);

                                                            if (target.equals(nodeValue)) {
                                                                setFocus(treeView, currentNodeList);
                                                                return;

                                                            } else {
                                                                childCountEighth = childNodeSeventh.getChildCount();

                                                                for (int h = 0; h < childCountEighth; h++) {
                                                                    currentNodeList.clear();
                                                                    currentNodeList.add(0, childNodeSeventh.getChild(h));
                                                                    childNodeEighth = childNodeSeventh.getChild(h);
                                                                    nodeValue = S21TreeView.getNodeColumnValue(childNodeEighth);

                                                                    if (target.equals(nodeValue)) {
                                                                        setFocus(treeView, currentNodeList);
                                                                        return;

                                                                    } else {
                                                                        childCountNinth = childNodeEighth.getChildCount();

                                                                        for (int i = 0; i < childCountNinth; i++) {
                                                                            currentNodeList.clear();
                                                                            currentNodeList.add(0, childNodeEighth.getChild(i));
                                                                            childNodeNinth = childNodeEighth.getChild(i);
                                                                            nodeValue = S21TreeView.getNodeColumnValue(childNodeNinth);

                                                                            if (target.equals(nodeValue)) {
                                                                                setFocus(treeView, currentNodeList);
                                                                                return;

                                                                            } else {
                                                                                childCountTenth = childNodeNinth.getChildCount();

                                                                                for (int j = 0; j < childCountTenth; j++) {
                                                                                    currentNodeList.clear();
                                                                                    currentNodeList.add(0, childNodeNinth.getChild(j));
                                                                                    childNodeTenth = childNodeNinth.getChild(j);
                                                                                    nodeValue = S21TreeView.getNodeColumnValue(childNodeTenth);

                                                                                    if (target.equals(nodeValue)) {
                                                                                        setFocus(treeView, currentNodeList);
                                                                                        return;

                                                                                    } else {
                                                                                        continue;
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
    }

    /**
     * The method explanation: Change background color and focus on
     * @param treeView S21TreeView
     * @param currentNodeList List<TreeNodeRow>
     */
    public static void setFocus(S21TreeView treeView, List<TreeNodeRow> currentNodeList) {

        // set focus on radio button
        treeView.setCheckboxON(currentNodeList.get(0), NMAL2520Constant.COLUMN_INDEX_RADIO_BUTTON);
        treeView.setFocusTreeView(true);

        // change background color of focus.
        treeView.setBackgroundColor(null);
        S21TreeView.setBackgroundColor(currentNodeList.get(0), NMAL2520Constant.BACKGROUND_COLOR_YELLOW);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL2520BMsg
     * @return Object[]
     */
    public static Object[] setParamForHeaderOrganizationSearchPopup(NMAL2520BMsg scrnMsg) {

        Object[] params = new Object[7];
        initParam(scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxEventFlgTxt, NMAL2520Constant.OPEN_WIN_ORGANIZATION_LOOKUP);

        if (ZYPCommonFunc.hasValue(scrnMsg.bizAreaOrgCd_P1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0, scrnMsg.bizAreaOrgCd_P1);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.orgNm_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_1, scrnMsg.orgNm_H1);
        }

        params[0] = scrnMsg.xxPopPrm_0;
        params[1] = scrnMsg.xxPopPrm_1;
        params[2] = scrnMsg.xxPopPrm_2;
        params[3] = scrnMsg.xxPopPrm_3;
        params[4] = scrnMsg.xxPopPrm_4;
        params[5] = scrnMsg.xxPopPrm_5;
        params[6] = scrnMsg.xxPopPrm_6;

        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL2520BMsg
     * @param i int
     * @return Object[]
     */
    public static Object[] setParamForDetailOrganizationSearchPopup(NMAL2520BMsg scrnMsg, int i) {

        Object[] params = new Object[7];
        initParam(scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxEventFlgTxt, NMAL2520Constant.OPEN_WIN_ORGANIZATION_LOOKUP_DETAIL);

        if (ZYPCommonFunc.hasValue(scrnMsg.bizAreaOrgCd_P1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0, scrnMsg.bizAreaOrgCd_P1);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).orgNm_A1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_1, scrnMsg.A.no(i).orgNm_A1);
        }

        params[0] = scrnMsg.xxPopPrm_0;
        params[1] = scrnMsg.xxPopPrm_1;
        params[2] = scrnMsg.xxPopPrm_2;
        params[3] = scrnMsg.xxPopPrm_3;
        params[4] = scrnMsg.xxPopPrm_4;
        params[5] = scrnMsg.xxPopPrm_5;
        params[6] = scrnMsg.xxPopPrm_6;

        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL2500BMsg
     * @param i int
     * @return Object[]
     */
    public static Object[] setParamForResourceSearchPopup(NMAL2520BMsg scrnMsg, int i) {

        Object[] params = new Object[2];
        initParam(scrnMsg);

        // Resource#
        if (ZYPCommonFunc.hasValue(scrnMsg.C.no(i).psnNum_C1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0, scrnMsg.C.no(i).psnNum_C1);
        }

        // Employee ID
        if (ZYPCommonFunc.hasValue(scrnMsg.C.no(i).psnCd_C1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_1, scrnMsg.C.no(i).psnCd_C1);
        }

        params[0] = scrnMsg.xxPopPrm_0;
        params[1] = scrnMsg.xxPopPrm_1;

        return params;

    }

 // QC#13431
    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL2520BMsg
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] setParamForAttachmentPopup(NMAL2520BMsg scrnMsg, String glblCmpyCd) {
        Object[] params = new Object[9];

        params[0] = scrnMsg.xxPopPrm_0.getValue();
        params[1] = scrnMsg.xxPopPrm_1.getValue();
        params[2] = scrnMsg.xxPopPrm_2.getValue();
        params[3] = scrnMsg.xxPopPrm_3.getValue();
        params[4] = scrnMsg.xxPopPrm_4.getValue();
        params[5] = scrnMsg.xxPopPrm_5.getValue();
        params[6] = scrnMsg.xxPopPrm_6.getValue();
        params[7] = scrnMsg.xxPopPrm_7.getValue();
        params[8] = scrnMsg.xxPopPrm_8.getValue();

        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL2520BMsg
     */
    public static void initParam(NMAL2520BMsg scrnMsg) {

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
    }

    /**
     * The method explanation: change active/inactive by selected biz
     * area.
     * @param scrnMsg NMAL2520BMsg
     */
    public static void controlContract(NMAL2520BMsg scrnMsg) {

        if (BIZ_AREA_ORG.CONTRACT.equals(scrnMsg.bizAreaOrgCd_P1.getValue())) {
            scrnMsg.lineBizTpCd_P1.setInputProtected(false);
            scrnMsg.xxChkBox_H1.setInputProtected(false);

            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                scrnMsg.C.no(i).asgCustFromNm_C1.setInputProtected(false);
                scrnMsg.C.no(i).asgCustToNm_C1.setInputProtected(false);
            }
        } else {
            scrnMsg.lineBizTpCd_P1.setInputProtected(true);
            scrnMsg.xxChkBox_H1.setInputProtected(true);

            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                scrnMsg.C.no(i).asgCustFromNm_C1.setInputProtected(true);
                scrnMsg.C.no(i).asgCustToNm_C1.setInputProtected(true);
            }
        }
    }

    /**
     * The method explanation: control organization link enable or
     * disable
     * @param scrnMsg NMAL2520BMsg
     */
    public static void controlOrgLink(NMAL2520BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).orgCd_A1)) {
                scrnMsg.A.no(i).orgCd_L1.setInputProtected(false);
            } else {
                scrnMsg.A.no(i).orgCd_L1.setInputProtected(true);
            }
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).orgCd_B1)) {
                scrnMsg.B.no(i).orgCd_L2.setInputProtected(false);
            } else {
                scrnMsg.B.no(i).orgCd_L2.setInputProtected(true);
            }
        }
    }

    /**
     * The method explanation: set default from date
     * @param scrnMsg NMAL2520BMsg
     */
    public static void setHierarchyOrgStatDate(NMAL2520BMsg scrnMsg) {

        // Get Sales Date
        final String slsDt = ZYPDateUtil.getSalesDate();

        // Set Start Date
        scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).effFromDt_A1.setValue(slsDt);
    }


    /**
     * The method explanation: set default from date
     * @param scrnMsg NMAL2520BMsg
     */
    public static void setResourceOrgStatDate(NMAL2520BMsg scrnMsg) {

        // Get Sales Date
        final String slsDt = ZYPDateUtil.getSalesDate();

        // Set Start Date
        scrnMsg.C.no(scrnMsg.C.getValidCount() - 1).effFromDt_C1.setValue(slsDt);
    }

    /**
     * The method explanation: control resource link enable or disable
     * @param scrnMsg NMAL2520BMsg
     */
    public static void controlRscLink(NMAL2520BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.C.no(i).psnNum_C1)) {
                scrnMsg.C.no(i).psnNum_L1.setInputProtected(false);
            } else {
                scrnMsg.C.no(i).psnNum_L1.setInputProtected(true);
            }
        }
    }

    // START 2017/06/14 J.Kim [QC#18924,ADD]
    /**
     * The method explanation: control Insert/Delete enable or disable
     * @param scrnMsg NMAL2520BMsg
     */
    public static void controlInsertDelete(EZDCommonHandler handler, NMAL2520BMsg scrnMsg) {

        if (NMAL2520Constant.INSERT_LIMIT_NUM == scrnMsg.xxTotCnt.getValueInt()) {
            handler.setButtonEnabled(NMAL2520Constant.BTN_INSERT_RESRC, false);
        } else {
            handler.setButtonEnabled(NMAL2520Constant.BTN_INSERT_RESRC, true);
        }

// 2017/12/19 QC#21893-2 Del Start
//        if (scrnMsg.C.getValidCount() > 0) {
//            handler.setButtonEnabled(NMAL2520Constant.BTN_DELETE_RESRC, true);
//        } else {
//            handler.setButtonEnabled(NMAL2520Constant.BTN_DELETE_RESRC, false);
//        }
    }
// 2017/12/19 QC#21893-2 Del End
    // END 2017/06/14 J.Kim [QC#18924,ADD]

// QC#7966
//    /**
//     * The method explanation: control resource link enable or disable
//     * @param scrnMsg NMAL2520BMsg
//     */
//    public static void clearHeader(NMAL2520BMsg scrnMsg) {
//
//        scrnMsg.orgCd_H1.clear();
//        scrnMsg.lineBizTpCd_P1.clear();
//        scrnMsg.orgNm_H1.clear();
//        scrnMsg.orgShortNm_H1.clear();
//        scrnMsg.orgDescTxt_H1.clear();
//        scrnMsg.orgTierCd_P1.clear();
//        scrnMsg.effFromDt_H1.clear();
//        scrnMsg.orgTierDescTxt_H1.clear();
//        scrnMsg.effThruDt_H1.clear();
//        scrnMsg.csrRgTpCd_P1.clear();
//        scrnMsg.xxChkBox_H1.clear();
//        scrnMsg.B.clear();
//    }

    // 2018/09/20 QC#27724,ADD Add Start
    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL2510BMsg
     * @param scrnMsgAry  EZDBMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL2520BMsg scrnMsg, EZDBMsgArray scrnMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL2520BMsg
     * @param scrnMsgAry  EZDBMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL2520BMsg scrnMsg, EZDBMsgArray scrnMsgAry, String tblId, int grpRows) {
        S21TableColorController tblColor = new S21TableColorController(NMAL2520Constant.SCREEN_ID, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.(All Table)
     * 
     * @param scrnMsg     NMAL2520BMsg
     */
    public static void setAllBGWithClear(NMAL2520BMsg scrnMsg) {
        if (NMAL2520Constant.TAB_BUILD_HIERARCHY.equals(scrnMsg.xxDplyTab.getValue())) {
            setRowsBGWithClear(scrnMsg, scrnMsg.A, NMAL2520Constant.SCREEN_TABLE_NAME_HIERARCHY_UP);
            setRowsBGWithClear(scrnMsg, scrnMsg.B, NMAL2520Constant.SCREEN_TABLE_NAME_HIERARCHY_UN);
        } else if (NMAL2520Constant.TAB_RESRC_ASIGN.equals(scrnMsg.xxDplyTab.getValue())) {
            setRowsBGWithClear(scrnMsg, scrnMsg.C, NMAL2520Constant.SCREEN_TABLE_NAME_RESRC);
        }
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.(All Table and view first tab)
     * 
     * @param scrnMsg     NMAL2520BMsg
     * @param clearTab    String
     */
    public static void setAllBGWithReset(NMAL2520BMsg scrnMsg, String clearTab) {
        if (NMAL2520Constant.TAB_BUILD_HIERARCHY.equals(clearTab)) {
            clearRowsBG(scrnMsg, scrnMsg.A, NMAL2520Constant.SCREEN_TABLE_NAME_HIERARCHY_UP);
            clearRowsBG(scrnMsg, scrnMsg.B, NMAL2520Constant.SCREEN_TABLE_NAME_HIERARCHY_UN);
        } else if (NMAL2520Constant.TAB_RESRC_ASIGN.equals(clearTab)) {
            clearRowsBG(scrnMsg, scrnMsg.C, NMAL2520Constant.SCREEN_TABLE_NAME_RESRC);
        }
        // Color on
        S21TableColorController tblColor = new S21TableColorController(NMAL2520Constant.SCREEN_ID, scrnMsg);
        if (NMAL2520Constant.TAB_BUILD_HIERARCHY.equals(scrnMsg.xxDplyTab.getValue())) {
            tblColor.setAlternateRowsBG(NMAL2520Constant.SCREEN_TABLE_NAME_HIERARCHY_UP, scrnMsg.A, 1);
            tblColor.setAlternateRowsBG(NMAL2520Constant.SCREEN_TABLE_NAME_HIERARCHY_UN, scrnMsg.B, 1);
        } else if (NMAL2520Constant.TAB_RESRC_ASIGN.equals(scrnMsg.xxDplyTab.getValue())) {
            tblColor.setAlternateRowsBG(NMAL2520Constant.SCREEN_TABLE_NAME_RESRC, scrnMsg.C, 1);
        }
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. (All Table)
     * 
     * @param scrnMsg     NMAL2520BMsg
     */
    public static void clearAllRowsBG(NMAL2520BMsg scrnMsg) {
        if (NMAL2520Constant.TAB_BUILD_HIERARCHY.equals(scrnMsg.xxDplyTab.getValue())) {
            clearRowsBG(scrnMsg, scrnMsg.A, NMAL2520Constant.SCREEN_TABLE_NAME_HIERARCHY_UP);
            clearRowsBG(scrnMsg, scrnMsg.B, NMAL2520Constant.SCREEN_TABLE_NAME_HIERARCHY_UN);
        } else if (NMAL2520Constant.TAB_RESRC_ASIGN.equals(scrnMsg.xxDplyTab.getValue())) {
            clearRowsBG(scrnMsg, scrnMsg.C, NMAL2520Constant.SCREEN_TABLE_NAME_RESRC);
        }
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NMAL2520BMsg
     * @param scrnMsgAry  EZDBMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    private static void clearRowsBG(NMAL2520BMsg scrnMsg, EZDBMsgArray scrnMsgAry, String tblId) {
        S21TableColorController tblColor = new S21TableColorController(NMAL2520Constant.SCREEN_ID, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnMsgAry);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. (All Table)
     * 
     * @param handler     EZDCommonHandler
     * @param scrnMsg     NMAL2520BMsg
     */
    public static void setAddDelButton(EZDCommonHandler handler, NMAL2520BMsg scrnMsg) {
        if (NMAL2520Constant.TAB_BUILD_HIERARCHY.equals(scrnMsg.xxDplyTab.getValue())) {
            if (scrnMsg.A.getValidCount() + 1 <= scrnMsg.A.length()) {
                handler.setButtonEnabled(NMAL2520Constant.BTN_INSERT_HIERARCHY, true);
            } else {
                handler.setButtonEnabled(NMAL2520Constant.BTN_INSERT_HIERARCHY, false);
            }
            if (scrnMsg.A.getValidCount() > 0) {
                handler.setButtonEnabled(NMAL2520Constant.BTN_DELETE_HIERARCHY, true);
            } else {
                handler.setButtonEnabled(NMAL2520Constant.BTN_DELETE_HIERARCHY, false);
            }
        } else if (NMAL2520Constant.TAB_RESRC_ASIGN.equals(scrnMsg.xxDplyTab.getValue())) {
            if (scrnMsg.C.getValidCount() + 1 <= scrnMsg.C.length()) {
                handler.setButtonEnabled(NMAL2520Constant.BTN_INSERT_RESRC, true);
            } else {
                handler.setButtonEnabled(NMAL2520Constant.BTN_INSERT_RESRC, false);
            }
            if (scrnMsg.C.getValidCount() > 0) {
                handler.setButtonEnabled(NMAL2520Constant.BTN_DELETE_RESRC, true);
            } else {
                handler.setButtonEnabled(NMAL2520Constant.BTN_DELETE_RESRC, false);
            }
        }
    }
    // 2018/09/20 QC#27724,ADD Add End
}
