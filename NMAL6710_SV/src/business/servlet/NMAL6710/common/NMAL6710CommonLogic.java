/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.servlet.NMAL6710.common;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBItem;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NMAL6710.NMAL6710CMsg;
import business.servlet.NMAL6710.NMAL6710BMsg;
import business.servlet.NMAL6710.NMAL6710_CBMsgArray;
import business.servlet.NMAL6710.NMAL6710_PBMsgArray;
import business.servlet.NMAL6710.constant.NMAL6710Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.online.treeview.S21TreeView;
import com.canon.cusa.s21.framework.online.treeview.S21TreeViewCheckColumn;
import com.canon.cusa.s21.framework.online.treeview.S21TreeViewCreator;
import com.canon.cusa.s21.framework.online.treeview.S21TreeViewCreatorIF;
import com.canon.cusa.s21.framework.online.treeview.S21TreeViewDataColumn;
import com.canon.cusa.s21.framework.online.treeview.S21TreeViewNodeColumn;
import com.canon.cusa.s21.framework.online.treeview.S21TreeViewRadioColumn;
import com.fujitsu.uji.compo.TreeNodeRow;

/**
 *<pre>
 *  Account Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/20/2015   SRAA            K.Aratani       Create          N/A
 * 10/01/2015   Fujitsu         N.Sugiura       Update          CSA
 * 03/02/2016   SRAA            Y.Chen          Update          QC#3290
 * 03/17/2016   SRAA            Y.Chen          Update          QC#4506
 * 05/05/2016   SRAA            Y.Chen          Update          QC#7711
 * 06/22/2016   SRAA            Y.Chen          Update          QC#6189
 * 06/22/2016   SRAA            Y.Chen          Update          QC#11268
 * 09/21/2016   Fujitsu         Mz.Takahashi    Update          QC#11068
 * 11/11/2016   Fujitsu         N.Sugiura       Update          QC#15431
 * 02/21/2017   Fujitsu         K.Ishizuka      Update          QC#17610
 *</pre>
 */
public class NMAL6710CommonLogic implements NMAL6710Constant {

    /**
     * Method name: setButtonProperties_INIT <dd>The method
     * explanation: Processing for INIT setButtonProperties
     * @param handler S21CommonHandler
     * @param scrnMsg Screen Component Interface Message
     */
    public static void setButtonProperties_INIT(S21CommonHandler handler, NMAL6710BMsg scrnMsg) {
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

    }

    /**
     * Method name: checkItem <dd>The method explanation: Add check
     * item. <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     */
    public static void checkItem(NMAL6710BMsg scrnMsg) {

        // add check items.
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_01);
        scrnMsg.putErrorScreen();

    }

    /**
     * Method name: setCommonProperties <dd>The method explanation:
     * set AddDel Properties
     * @param handler S21CommonHandler
     * @param scrnMsg Screen Component Interface Message
     */
    public static void setCommonProperties(S21CommonHandler handler, NMAL6710BMsg scrnMsg) {

        // init value
        handler.setButtonEnabled(BTN_CMN_SAVE[0], false);
        handler.setButtonEnabled(BTN_CMN_SUBMIT[0], false);
        handler.setButtonEnabled(BTN_CMN_APPROVE[0], false);
        handler.setButtonEnabled(BTN_CMN_REJECT[0], false);
        handler.setButtonEnabled(BTN_CMN_DELETE[0], false);
        handler.setButtonEnabled(BTN_CMN_CLEAR[0], true);
        handler.setButtonEnabled(BTN_CMN_RETURN[0], true);
        handler.setButtonEnabled(BTN_CMN_RESET[0], false);
        handler.setButtonEnabled(BTN_CMN_DOWNLOAD[0], false);
    }

    /**
     * Method name: setProperties <dd>The method explanation: set
     * AddDel Properties
     * @param handler S21CommonHandler
     * @param scrnMsg Screen Component Interface Message
     * @param exeMode exe Mode
     */
    public static void setProperties(S21CommonHandler handler, NMAL6710BMsg scrnMsg, String exeMode) {
        setCommonProperties(handler, scrnMsg);
    }

    /**
     * Method name: convertTreeInfo <dd>The method explanation:
     * Processing for convert Tree Info
     * @param bMsg Screen Component Interface Message
     * @param cMsg Business Component Interface Message
     * @param tableMode table Mode
     */
    public static void convertTreeInfo(NMAL6710BMsg bMsg, NMAL6710CMsg cMsg, String tableMode) {

    }

    // QC#4522
    public static void checkItemAttribute(NMAL6710BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.xxAcctSrchModeCd_D1);
        scrnMsg.addCheckItem(scrnMsg.xxAcctSrchStsCd_D2);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_01);
        scrnMsg.addCheckItem(scrnMsg.xxAcctSrchDplyHrchCd_D3);

        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_01);
        scrnMsg.addCheckItem(scrnMsg.xxAllLineAddr_01);
        scrnMsg.addCheckItem(scrnMsg.ctyAddr_01);
        scrnMsg.addCheckItem(scrnMsg.stCd_DP);
        scrnMsg.addCheckItem(scrnMsg.postCd_01);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_01);
        scrnMsg.addCheckItem(scrnMsg.locNum_01);
        scrnMsg.addCheckItem(scrnMsg.dbaNm_01);
        scrnMsg.addCheckItem(scrnMsg.dsAcctLegalNm_01);
        scrnMsg.addCheckItem(scrnMsg.dsAcctGrpCd_DP);
        scrnMsg.addCheckItem(scrnMsg.dsAcctClsCd_DP);
        scrnMsg.addCheckItem(scrnMsg.dsLocNm_01);

        scrnMsg.addCheckItem(scrnMsg.dsAcctDunsNum_01);
        scrnMsg.addCheckItem(scrnMsg.dsUltDunsNum_01);
        scrnMsg.addCheckItem(scrnMsg.dsCustSicDescTxt_01);

        scrnMsg.addCheckItem(scrnMsg.ctacPsnLastNm_01);
        scrnMsg.addCheckItem(scrnMsg.ctacPsnFirstNm_01);
        scrnMsg.addCheckItem(scrnMsg.ctacPsnTelNum_01);
        scrnMsg.addCheckItem(scrnMsg.dsXrefAcctTpCd_DP);
        scrnMsg.addCheckItem(scrnMsg.dsXrefAcctCd_01);
        scrnMsg.addCheckItem(scrnMsg.billToCustCd_01);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd_01);

        scrnMsg.addCheckItem(scrnMsg.dsXtrnlRefTxt_01);
        scrnMsg.addCheckItem(scrnMsg.dsAcctTpCd_DP);
        scrnMsg.addCheckItem(scrnMsg.dsAcctItrlFlg_C1);

        scrnMsg.addCheckItem(scrnMsg.xxAcctSrchDplyRelnCd_D4);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_RT);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_RT);
        scrnMsg.addCheckItem(scrnMsg.xxAllLineAddr_RT);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_02);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_03);

        scrnMsg.putErrorScreen();
    }

    // QC#6189
    public static void checkMandatorySearchCondition(NMAL6710BMsg scrnMsg) {
        List<EZDBItem> list = new ArrayList<EZDBItem>();
        list.add(scrnMsg.dsAcctNm_01);
        list.add(scrnMsg.xxAllLineAddr_01);
        list.add(scrnMsg.ctyAddr_01);
        list.add(scrnMsg.stCd_DP);
        list.add(scrnMsg.postCd_01);
        list.add(scrnMsg.dsAcctNum_01);
        list.add(scrnMsg.locNum_01);
        list.add(scrnMsg.dbaNm_01);
        list.add(scrnMsg.dsAcctLegalNm_01);
        list.add(scrnMsg.dsAcctGrpCd_DP);
        list.add(scrnMsg.dsAcctClsCd_DP);
        list.add(scrnMsg.dsLocNm_01);

        list.add(scrnMsg.dsAcctDunsNum_01);
        list.add(scrnMsg.dsUltDunsNum_01);
        list.add(scrnMsg.dsCustSicDescTxt_01);
        list.add(scrnMsg.ctacPsnLastNm_01);
        list.add(scrnMsg.ctacPsnFirstNm_01);
        list.add(scrnMsg.ctacPsnTelNum_01);
        list.add(scrnMsg.dsXrefAcctTpCd_DP);
        list.add(scrnMsg.dsXrefAcctCd_01);
        list.add(scrnMsg.billToCustCd_01);
        list.add(scrnMsg.shipToCustCd_01);

        boolean hasSearchCondition = false;
        for (EZDBItem item : list) {
            if (ZYPCommonFunc.hasValue(item)) {
                hasSearchCondition = true;
                break;
            }
        }
        if (!hasSearchCondition) {
            for (EZDBItem item : list) {
                item.setErrorInfo(1, NMAM0288E);
                scrnMsg.addCheckItem(item);
            }
            scrnMsg.putErrorScreen();
        }
    }

    // ADD START S21_NA QC#17610
    /**
     * check fields input only wild card
     * @param scrnMsg NMAL6710BMsg
     */
    public static void checkWildCardOnly(NMAL6710BMsg scrnMsg) {
        List<EZDBStringItem> list = new ArrayList<EZDBStringItem>();
        list.add(scrnMsg.dsAcctNm_01);
        list.add(scrnMsg.xxAllLineAddr_01);
        list.add(scrnMsg.ctyAddr_01);
        list.add(scrnMsg.postCd_01);
        list.add(scrnMsg.dsAcctNum_01);
        list.add(scrnMsg.locNum_01);
        list.add(scrnMsg.dbaNm_01);
        list.add(scrnMsg.dsAcctLegalNm_01);
        list.add(scrnMsg.dsAcctGrpCd_DP);
        list.add(scrnMsg.dsLocNm_01);
        list.add(scrnMsg.dsAcctDunsNum_01);
        list.add(scrnMsg.dsUltDunsNum_01);
        list.add(scrnMsg.dsCustSicDescTxt_01);
        list.add(scrnMsg.ctacPsnLastNm_01);
        list.add(scrnMsg.ctacPsnFirstNm_01);
        list.add(scrnMsg.ctacPsnTelNum_01);
        list.add(scrnMsg.dsXrefAcctCd_01);
        list.add(scrnMsg.billToCustCd_01);
        list.add(scrnMsg.shipToCustCd_01);

        boolean hasWildCardOnly = false;
        for (EZDBStringItem item : list) {
            if (WILDCARD.equals(item.getValue())) {
                item.setErrorInfo(1, NMAM8667E);
                scrnMsg.addCheckItem(item);
                hasWildCardOnly = true;
            }
        }

        if (hasWildCardOnly) {
            scrnMsg.putErrorScreen();
        }
    }
    // ADD END S21_NA QC#17610

    /**
     * Method name: settViewCreatorIF <dd>The method explanation:
     * clear Modify Input Info
     * @param tViewCreatorIF tree ViewCreatorIF
     * @param dataCol S21TreeViewDataColumn[]
     * @param checkCol S21TreeViewCheckColumn
     */
    private static void settViewCreatorIF(S21TreeViewCreatorIF tViewCreatorIF, S21TreeViewDataColumn[] dataCol, S21TreeViewCheckColumn checkCol) {
        tViewCreatorIF.addColumnType(dataCol[0]);
        tViewCreatorIF.addColumnType(dataCol[1]);
        tViewCreatorIF.addColumnType(dataCol[2]);
        tViewCreatorIF.addColumnType(dataCol[3]);
        tViewCreatorIF.addColumnType(checkCol);
    }

    /**
     * Method name: setMovetViewCreatorIF <dd>The method explanation:
     * clear Modify Input Info
     * @param tViewCreatorIF tree ViewCreatorIF
     * @param dataCol S21TreeViewDataColumn[]
     * @param checkCol S21TreeViewCheckColumn
     */
    private static void setMovetViewCreatorIF(S21TreeViewCreatorIF tViewCreatorIF, S21TreeViewDataColumn[] dataCol, S21TreeViewCheckColumn checkCol) {
        tViewCreatorIF.addColumnType(dataCol[0]);
        tViewCreatorIF.addColumnType(dataCol[1]);
        tViewCreatorIF.addColumnType(dataCol[2]);
        tViewCreatorIF.addColumnType(checkCol);
    }

    /**
     * Method name: clearInit <dd>The method explanation: clear Init
     * for display
     * @param scrnMsg Screen Component Interface Message
     * @param exeMode exe Mode
     */
    public static void clearInit(NMAL6710BMsg scrnMsg, String exeMode) {

    }

    /**
     * Method name: clearConfirmInfo <dd>The method explanation: clear
     * Confirm Info
     * @param scrnMsg Screen Component Interface Message
     * @param exeMode exe Mode
     */
    public static void clearConfirmInfo(NMAL6710BMsg scrnMsg, String exeMode) {

    }

    /**
     * Method name: clearModifyInputInfo <dd>The method explanation:
     * clear Modify Input Info
     * @param scrnMsg Screen Component Interface Message
     */
    public static void clearModifyInputInfo(NMAL6710BMsg scrnMsg) {

    }

    /**
     * Method name: resetClear <dd>The method explanation: reset Info
     * @param scrnMsg Screen Component Interface Message
     */
    public static void resetClear(NMAL6710BMsg scrnMsg) {

    }

    /**
     * Method name: getAllTreeNodeRows <dd>The method explanation: Get
     * all tree node rows
     * @param treeView S21TreeView
     * @param columnIndex column Index
     * @return returnList List(TreeNodeRow)
     */
    public static List<TreeNodeRow> getAllTreeNodeRows(S21TreeView treeView, int columnIndex) {
        List<TreeNodeRow> returnList = new ArrayList<TreeNodeRow>();
        getAllTreeNodeRows(returnList, (TreeNodeRow) treeView.getRoot(), columnIndex);
        return returnList;
    }

    /**
     * Method name: getAllTreeNodeRows <dd>The method explanation: Get
     * all tree node rows
     * @param returnList List
     * @param treeNodeRow TreeNodeRow
     * @param columnIndex column Index
     */
    private static void getAllTreeNodeRows(List<TreeNodeRow> returnList, TreeNodeRow treeNodeRow, int columnIndex) {
        returnList.add(treeNodeRow);
        for (int i = 0; i < treeNodeRow.getChildCount(); i++) {
            getAllTreeNodeRows(returnList, treeNodeRow.getChild(i), columnIndex);
        }
    }

    /**
     * Method name: setAddTabProtect <dd>The method explanation: set
     * AddDel Properties
     * @param handler S21CommonHandler
     * @param scrnMsg Screen Component Interface Message
     */
    public static void setAddTabProtect(S21CommonHandler handler, NMAL6710BMsg scrnMsg) {

    }

    /**
     * writePerformanceProfilingLogStart
     * @param clazz Class
     * @param user String
     * @param methodNm String
     */
    public static void writePerformanceProfilingLogStart(Class clazz, String user, String methodNm) {
        final StringBuilder sb = new StringBuilder();
        sb.append("\t#Performance Profiling# [").append(clazz.getSimpleName()).append("#").append(user).append("]\t").append(methodNm).append("\t[START]\t").append(System.currentTimeMillis());
        System.out.println(sb.toString());
    }

    /**
     * writePerformanceProfilingLogEnd
     * @param clazz Class
     * @param user String
     * @param methodNm String
     */
    public static void writePerformanceProfilingLogEnd(Class clazz, String user, String methodNm) {
        final StringBuilder sb = new StringBuilder();
        sb.append("\t#Performance Profiling# [").append(clazz.getSimpleName()).append("#").append(user).append("]\t").append(methodNm).append("\t[E N D]\t").append(System.currentTimeMillis());
        System.out.println(sb.toString());
    }

    /**
     * popup array.
     * @param p NMAL6710_PBMsgArray
     * @param size int
     * @return Object[]
     */
    public static Object[] toArrayPopup(NMAL6710_PBMsgArray p, int size) {
        Object[] param = new Object[size];
        for (int i = 0; i < size; i++) {
            param[i] = p.no(i).xxPopPrm;
        }
        return param;
    }

    /**
     * Control Detail Screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6710BMsg
     */
    public static void controlDetailScreenFields(EZDCommonHandler handler, NMAL6710BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).dsAcctRelnTpNm_C1.setInputProtected(true);
            scrnMsg.C.no(i).dsAcctNm_C1.setInputProtected(true);
            scrnMsg.C.no(i).xxAllLineAddr_C1.setInputProtected(true);
            scrnMsg.C.no(i).xxYesNoCd_C1.setInputProtected(true);
            scrnMsg.C.no(i).dsAcctTpNm_C1.setInputProtected(true);
            scrnMsg.C.no(i).xxCtlNm_C1.setInputProtected(true);
        }
    }

    /**
     * setHirarchyTreeView
     * @param scrnMsg NMAL6710BMsg
     * @param bizMsg NMAL6710CMsg
     */
    public static void setHirarchyTreeView(NMAL6710BMsg scrnMsg, NMAL6710CMsg bizMsg) {
        // ##### create TreeView column types
        // 1. "Node"
        S21TreeViewNodeColumn nodeCol = new S21TreeViewNodeColumn();
        nodeCol.setRootNodeKey("dsAcctNum_T1"); // Root
        nodeCol.addInnerNodeKey("dsAcctNum_T2");
        nodeCol.addInnerNodeKey("dsAcctNum_T3");
        nodeCol.addInnerNodeKey("dsAcctNum_T4");
        nodeCol.addInnerNodeKey("dsAcctNum_T5");
        nodeCol.addInnerNodeKey("dsAcctNum_T6");
        nodeCol.addInnerNodeKey("dsAcctNum_T7");
        nodeCol.addInnerNodeKey("dsAcctNum_T8");
        nodeCol.addInnerNodeKey("dsAcctNum_T9");
        nodeCol.addInnerNodeKey("dsAcctNum_TA");
        nodeCol.addInnerNodeKey("dsAcctNum_TB");
        // QC#4506
        // nodeCol.setLeafNodeKey("locNum_TC"); // Leaf
        nodeCol.setLeafNodeKey("dsAcctNum_TL"); // Leaf

        // 2. "Radio"
        S21TreeViewRadioColumn radioCol = new S21TreeViewRadioColumn();

        // QC#4506
        // S21TreeViewDataColumn dataCol_01 = new
        // S21TreeViewDataColumn();
        // dataCol_01.setSchemaKey("dsAcctNm_T");
        // S21TreeViewDataColumn dataCol_02 = new
        // S21TreeViewDataColumn();
        // dataCol_02.setSchemaKey("xxAllLineAddr_T");
        // S21TreeViewDataColumn dataCol_03 = new
        // S21TreeViewDataColumn();
        // dataCol_03.setSchemaKey("xxYesNoCd_T");
        // S21TreeViewDataColumn dataCol_04 = new
        // S21TreeViewDataColumn();
        // dataCol_04.setSchemaKey("dsAcctTpNm_T");
        // S21TreeViewDataColumn dataCol_05 = new
        // S21TreeViewDataColumn();
        // dataCol_05.setSchemaKey("dsAcctRelnTpNm_T");
        // S21TreeViewDataColumn dataCol_06 = new
        // S21TreeViewDataColumn();
        // dataCol_06.setSchemaKey("xxCtlNm_T");
        // S21TreeViewDataColumn dataCol_07 = new
        // S21TreeViewDataColumn();
        // dataCol_07.setSchemaKey("billToCustCd_T");
        // S21TreeViewDataColumn dataCol_08 = new
        // S21TreeViewDataColumn();
        // dataCol_08.setSchemaKey("shipToCustCd_T");
        // S21TreeViewDataColumn dataCol_09 = new
        // S21TreeViewDataColumn();
        // dataCol_09.setSchemaKey("dsAcctNum_T");
        // S21TreeViewDataColumn dataCol_10 = new
        // S21TreeViewDataColumn();
        // dataCol_10.setSchemaKey("locNum_T");
        // // QC#3290
        // S21TreeViewDataColumn dataCol_11 = new
        // S21TreeViewDataColumn();
        // dataCol_11.setSchemaKey("xxRgtnStsTxt_A1");
        // S21TreeViewDataColumn dataCol_12 = new
        // S21TreeViewDataColumn();
        // dataCol_12.setSchemaKey("xxRgtnStsTxt_A2");
        // S21TreeViewDataColumn dataCol_13 = new
        // S21TreeViewDataColumn();
        // dataCol_13.setSchemaKey("xxRgtnStsTxt_A3");
        // S21TreeViewDataColumn dataCol_14 = new
        // S21TreeViewDataColumn();
        // dataCol_14.setSchemaKey("xxRgtnStsTxt_A4");
        List<String> listSchemaKey = new ArrayList<String>();
        List<S21TreeViewDataColumn> listDataColumn = new ArrayList<S21TreeViewDataColumn>();
        listSchemaKey.add("dsAcctNm_T"); // tree column index: 2
        listSchemaKey.add("xxAllLineAddr_T"); // tree column index: 3
        listSchemaKey.add("locNum_T"); // tree column index: 4
        listSchemaKey.add("xxYesNoCd_T"); // tree column index: 5
        listSchemaKey.add("dsAcctTpNm_T"); // tree column index: 6
        listSchemaKey.add("dsAcctRelnTpNm_T"); // tree column index: 7
        listSchemaKey.add("xxCtlNm_T"); // tree column index: 8
        listSchemaKey.add("billToCustCd_T"); // tree column index: 9
        listSchemaKey.add("shipToCustCd_T"); // tree column index: 10
        listSchemaKey.add("dsAcctNum_T"); // tree column index: 11
        listSchemaKey.add("xxRgtnStsTxt_A1"); // tree column index: 12
        listSchemaKey.add("xxRgtnStsTxt_A2"); // tree column index: 13
        listSchemaKey.add("xxRgtnStsTxt_A3"); // tree column index: 14
        listSchemaKey.add("xxRgtnStsTxt_A4"); // tree column index: 15
        listSchemaKey.add("xxRgtnStsTxt_A5"); // tree column index: 16
        // // QC#15431 Add
        for (String schemaKey : listSchemaKey) {
            S21TreeViewDataColumn dataCol = new S21TreeViewDataColumn();
            dataCol.setSchemaKey(schemaKey);
            listDataColumn.add(dataCol);
        }

        // ##### create S21TreeViewCreatorIF
        S21TreeViewCreatorIF tViewCreatorIF = new S21TreeViewCreatorIF((EZDCMsgArray) bizMsg.getMsgData("A"), nodeCol);
        tViewCreatorIF.addColumnType(radioCol);
        // QC#4506
        // tViewCreatorIF.addColumnType(dataCol_01);
        // tViewCreatorIF.addColumnType(dataCol_02);
        // tViewCreatorIF.addColumnType(dataCol_03);
        // tViewCreatorIF.addColumnType(dataCol_04);
        // tViewCreatorIF.addColumnType(dataCol_05);
        // tViewCreatorIF.addColumnType(dataCol_06);
        // tViewCreatorIF.addColumnType(dataCol_07);
        // tViewCreatorIF.addColumnType(dataCol_08);
        // tViewCreatorIF.addColumnType(dataCol_09);
        // tViewCreatorIF.addColumnType(dataCol_10);
        // // QC#3290
        // tViewCreatorIF.addColumnType(dataCol_11);
        // tViewCreatorIF.addColumnType(dataCol_12);
        // tViewCreatorIF.addColumnType(dataCol_13);
        // tViewCreatorIF.addColumnType(dataCol_14);
        for (S21TreeViewDataColumn dataCol : listDataColumn) {
            tViewCreatorIF.addColumnType(dataCol);
        }

        // ##### create S21TreeView
        S21TreeView treeView = S21TreeViewCreator.create(tViewCreatorIF, "treeView");
        if (null != treeView) {
            treeView.setExpandedNodeIcon();
            scrnMsg.setTreeView(treeView);
        } else {
            scrnMsg.setTreeView(null);
        }
    }

    // QC#6189
    // // QC#7711
    // public static boolean checkSearchMode(NMAL6710BMsg scrnMsg) {
    // boolean isError = false;
    // if
    // (SEARCH_MODE_CD_HRCH.equals(scrnMsg.xxAcctSrchModeCd_D1.getValue()))
    // {
    // if (!TAB_NM_HRCH.equals(scrnMsg.xxDplyTab.getValue())) {
    // isError = true;
    // }
    // } else if
    // (SEARCH_MODE_CD_QUICK.equals(scrnMsg.xxAcctSrchModeCd_D1.getValue()))
    // {
    // if (!TAB_NM_QUICK.equals(scrnMsg.xxDplyTab.getValue())) {
    // isError = true;
    // }
    // }
    // if (isError) {
    // scrnMsg.setMessageInfo(NMAM8494E);
    // }
    // return !isError;
    // }

    // QC#11268
    public static void resetRowsBackgroundQuickMode(NMAL6710BMsg scrnMsg) {
        String tblId = "C";
        NMAL6710_CBMsgArray scrnCMsgAry = scrnMsg.C;
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        if (TAB_NM_QUICK.equals(scrnMsg.xxDplyTab.getValue()) || (TAB_NM_HRCH_QUICK.equals(scrnMsg.xxDplyTab.getValue()))) {
            tblColor.clearRowsBG(tblId, scrnCMsgAry);
            tblColor.setAlternateRowsBG(tblId, scrnCMsgAry);
        } else {
            tblColor.clearRowsBG(tblId, scrnCMsgAry);
        }
    }
}
