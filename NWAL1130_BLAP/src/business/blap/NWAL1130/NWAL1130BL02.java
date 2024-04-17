/*
 * <Pre>Copyright(c)2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1130;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1130.common.NWAL1130CommonLogic;
import business.blap.NWAL1130.constant.NWAL1130Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 *  Common PopUp
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/14/2012   Fujitsu         T.Ishii         Create          N/A
 * 02/19/2016   Fujitsu         W.Honda         Update          S21 CSA QC#1130
 * 2016/07/25   Hitachi         T.Tomita        Update          QC#11471
 * 2018/10/23   Fujitsu         M.Ohno          Update          QC#28425
 *</pre>
 */
public class NWAL1130BL02 extends S21BusinessHandler implements NWAL1130Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NWAL1130_INIT".equals(screenAplID)) {
                doProcess_NWAL1130_INIT((NWAL1130CMsg) cMsg, (NWAL1130SMsg) sMsg);
            } else if ("NWAL1130Scrn00_Search".equals(screenAplID)) {
                doProcess_NWAL1130Scrn00_Search((NWAL1130CMsg) cMsg, (NWAL1130SMsg) sMsg);
            } else if ("NWAL1130Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NWAL1130Scrn00_PageNext((NWAL1130CMsg) cMsg, (NWAL1130SMsg) sMsg);
            } else if ("NWAL1130Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NWAL1130Scrn00_PagePrev((NWAL1130CMsg) cMsg, (NWAL1130SMsg) sMsg);
            } else if ("NWAL1130Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NWAL1130Scrn00_TBLColumnSort((NWAL1130CMsg) cMsg, (NWAL1130SMsg) sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * Process of screen event NWAL1130_INIT
     * </pre>
     * @param bizMsg Business Component Interface Message
     * @param sharedMsg Global area information
     */
    private void doProcess_NWAL1130_INIT(NWAL1130CMsg bizMsg, NWAL1130SMsg sharedMsg) {

        boolean errorExists = false;

        if (errorExists) {
            // error exists
            return;
        }

        // search
        search(bizMsg, sharedMsg);
    }

    /**
     * <pre>
     * Process of screen event NWAL1130Scrn00_Search
     * </pre>
     * @param bizMsg Business Component Interface Message
     * @param sharedMsg Global area information
     */
    private void doProcess_NWAL1130Scrn00_Search(NWAL1130CMsg bizMsg, NWAL1130SMsg sharedMsg) {

        boolean errorExists = false;

        if (errorExists) {
            // error exists
            return;
        }

        // search
        search(bizMsg, sharedMsg);
    }

    /**
     * <pre>
     * Process of screen event NWAL1130Scrn00_PageNext
     * </pre>
     * @param bizMsg Business Component Interface Message
     * @param sharedMsg Global area information
     */
    private void doProcess_NWAL1130Scrn00_PageNext(NWAL1130CMsg bizMsg, NWAL1130SMsg sharedMsg) {

        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NWAL1130CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, sharedMsg.A);
    }

    /**
     * <pre>
     * Process of screen event NWAL1130Scrn00_PagePrev
     * </pre>
     * @param bizMsg Business Component Interface Message
     * @param sharedMsg Global area information
     */
    private void doProcess_NWAL1130Scrn00_PagePrev(NWAL1130CMsg bizMsg, NWAL1130SMsg sharedMsg) {

        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NWAL1130CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, sharedMsg.A);
    }

    /**
     * <pre>
     * Process of screen event NWAL1130Scrn00_TBLColumnSort
     * </pre>
     * @param bizMsg Business Component Interface Message
     * @param sharedMsg Global area information
     */
    private void doProcess_NWAL1130Scrn00_TBLColumnSort(NWAL1130CMsg bizMsg, NWAL1130SMsg sharedMsg) {

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sharedMsg.A, sharedMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sharedMsg.A.getValidCount());

            int i = 0;
            for (; i < sharedMsg.A.getValidCount(); i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sharedMsg.A.no(i), null, bizMsg.A.no(i), null);
            }
            bizMsg.A.setValidCount(i);

            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
        }
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param sharedMsg Global area information
     */
    private void search(NWAL1130CMsg bizMsg, NWAL1130SMsg sharedMsg) {

        bizMsg.A.clear();
        sharedMsg.A.clear();
        bizMsg.A.setValidCount(0);
        sharedMsg.A.setValidCount(0);

        Map<String, Object> ssmParam = createSearchCriteria(bizMsg, sharedMsg.A.length());

        S21SsmEZDResult ssmResult = NWAL1130Query.getInstance().search(ssmParam, sharedMsg);

        if (ssmResult.isCodeNotFound()) {

            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();

            bizMsg.setMessageInfo(NZZM0000E);

        } else {
            if (ssmResult.getQueryResultCount() > sharedMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                sharedMsg.A.setValidCount(sharedMsg.A.length());
            } else {
                sharedMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }

            int j = 0;
            for (; j < sharedMsg.A.getValidCount(); j++) {

                NWAL1130_ASMsg asMsg = sharedMsg.A.no(j);

                if (j < bizMsg.A.length()) {

                    EZDMsg.copy(asMsg, null, bizMsg.A.no(j), null);
                    bizMsg.A.setValidCount(j + 1);
                }
            }

            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum.setValue(sharedMsg.A.getValidCount());
        }
    }

    /**
     * create search criteria
     * @param bizMsg Business Component Interface Message
     * @param maxRowNum max row number
     * @return search criteria
     */
    private Map<String, Object> createSearchCriteria(NWAL1130CMsg bizMsg, int maxRowNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());

        // SELECT
        ssmParam.put("column00", getColumnName(bizMsg.xxComnScrQueryColNm_C0));
        ssmParam.put("column01", getColumnName(bizMsg.xxComnScrQueryColNm_C1));
        ssmParam.put("column02", getColumnName(bizMsg.xxComnScrQueryColNm_C2));
        ssmParam.put("column03", getColumnName(bizMsg.xxComnScrQueryColNm_C3));
        ssmParam.put("column04", getColumnName(bizMsg.xxComnScrQueryColNm_C4));
        ssmParam.put("column05", getColumnName(bizMsg.xxComnScrQueryColNm_C5));
        ssmParam.put("column06", getColumnName(bizMsg.xxComnScrQueryColNm_C6));
        ssmParam.put("column07", getColumnName(bizMsg.xxComnScrQueryColNm_C7));
        ssmParam.put("column08", getColumnName(bizMsg.xxComnScrQueryColNm_C8));
        ssmParam.put("column09", getColumnName(bizMsg.xxComnScrQueryColNm_C9));

        // FROM
        ssmParam.put("from", bizMsg.xxComnScrTblTxt.getValue());

        // 2018/10/23 QC#28425 Mod Start
        // WHERE
        if (ZYPCommonFunc.hasValue(bizMsg.xxComnScrQueryFltrTxt_H0)) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxValUpdFlg_H0.getValue())) {
                ssmParam.put("where00", "UPPER(" + bizMsg.xxComnScrQueryFltrTxt_H0.getValue() + ")");
            } else {
                ssmParam.put("where00", bizMsg.xxComnScrQueryFltrTxt_H0.getValue());
            }
        } else {
	        ssmParam.put("where00", bizMsg.xxComnScrQueryFltrTxt_H0.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxComnScrQueryFltrTxt_H1)) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxValUpdFlg_H1.getValue())) {
                ssmParam.put("where01", "UPPER(" + bizMsg.xxComnScrQueryFltrTxt_H1.getValue() + ")");
            } else {
                ssmParam.put("where01", bizMsg.xxComnScrQueryFltrTxt_H1.getValue());
            }
        } else {
	        ssmParam.put("where01", bizMsg.xxComnScrQueryFltrTxt_H1.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxComnScrQueryFltrTxt_H2)) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxValUpdFlg_H2.getValue())) {
                ssmParam.put("where02", "UPPER(" + bizMsg.xxComnScrQueryFltrTxt_H2.getValue() + ")");
            } else {
                ssmParam.put("where02", bizMsg.xxComnScrQueryFltrTxt_H2.getValue());
            }
        } else {
	        ssmParam.put("where02", bizMsg.xxComnScrQueryFltrTxt_H2.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxComnScrQueryFltrTxt_H3)) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxValUpdFlg_H3.getValue())) {
                ssmParam.put("where03", "UPPER(" + bizMsg.xxComnScrQueryFltrTxt_H3.getValue() + ")");
            } else {
                ssmParam.put("where03", bizMsg.xxComnScrQueryFltrTxt_H3.getValue());
            }
        } else {
	        ssmParam.put("where03", bizMsg.xxComnScrQueryFltrTxt_H3.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxComnScrQueryFltrTxt_H4)) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxValUpdFlg_H4.getValue())) {
                ssmParam.put("where04", "UPPER(" + bizMsg.xxComnScrQueryFltrTxt_H4.getValue() + ")");
            } else {
                ssmParam.put("where04", bizMsg.xxComnScrQueryFltrTxt_H4.getValue());
            }
        } else {
	        ssmParam.put("where04", bizMsg.xxComnScrQueryFltrTxt_H4.getValue());
        }
        // S21 CSA Add Start
        if (ZYPCommonFunc.hasValue(bizMsg.xxComnScrQueryFltrTxt_H5)) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxValUpdFlg_H5.getValue())) {
                ssmParam.put("where05", "UPPER(" + bizMsg.xxComnScrQueryFltrTxt_H5.getValue() + ")");
            } else {
                ssmParam.put("where05", bizMsg.xxComnScrQueryFltrTxt_H5.getValue());
            }
        } else {
	        ssmParam.put("where05", bizMsg.xxComnScrQueryFltrTxt_H5.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxComnScrQueryFltrTxt_H6)) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxValUpdFlg_H6.getValue())) {
                ssmParam.put("where06", "UPPER(" + bizMsg.xxComnScrQueryFltrTxt_H6.getValue() + ")");
            } else {
                ssmParam.put("where06", bizMsg.xxComnScrQueryFltrTxt_H6.getValue());
            }
        } else {
	        ssmParam.put("where06", bizMsg.xxComnScrQueryFltrTxt_H6.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxComnScrQueryFltrTxt_H7)) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxValUpdFlg_H7.getValue())) {
                ssmParam.put("where07", "UPPER(" + bizMsg.xxComnScrQueryFltrTxt_H7.getValue() + ")");
            } else {
                ssmParam.put("where07", bizMsg.xxComnScrQueryFltrTxt_H7.getValue());
            }
        } else {
	        ssmParam.put("where07", bizMsg.xxComnScrQueryFltrTxt_H7.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxComnScrQueryFltrTxt_H8)) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxValUpdFlg_H8.getValue())) {
                ssmParam.put("where08", "UPPER(" + bizMsg.xxComnScrQueryFltrTxt_H8.getValue() + ")");
            } else {
                ssmParam.put("where08", bizMsg.xxComnScrQueryFltrTxt_H8.getValue());
            }
        } else {
	        ssmParam.put("where08", bizMsg.xxComnScrQueryFltrTxt_H8.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxComnScrQueryFltrTxt_H9)) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxValUpdFlg_H9.getValue())) {
                ssmParam.put("where09", "UPPER(" + bizMsg.xxComnScrQueryFltrTxt_H9.getValue() + ")");
            } else {
                ssmParam.put("where09", bizMsg.xxComnScrQueryFltrTxt_H9.getValue());
            }
        } else {
	        ssmParam.put("where09", bizMsg.xxComnScrQueryFltrTxt_H9.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxComnScrQueryFltrTxt_HA)) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxValUpdFlg_HA.getValue())) {
                ssmParam.put("where0A", "UPPER(" + bizMsg.xxComnScrQueryFltrTxt_HA.getValue() + ")");
            } else {
                ssmParam.put("where0A", bizMsg.xxComnScrQueryFltrTxt_HA.getValue());
            }
        } else {
	        ssmParam.put("where0A", bizMsg.xxComnScrQueryFltrTxt_HA.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxComnScrQueryFltrTxt_HB)) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxValUpdFlg_HB.getValue())) {
                ssmParam.put("where0B", "UPPER(" + bizMsg.xxComnScrQueryFltrTxt_HB.getValue() + ")");
            } else {
                ssmParam.put("where0B", bizMsg.xxComnScrQueryFltrTxt_HB.getValue());
            }
        } else {
	        ssmParam.put("where0B", bizMsg.xxComnScrQueryFltrTxt_HB.getValue());
        }
        // 2018/10/19 QC#28425 Mod End
        // START 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
//        // S21 CSA Add End
//        // OPERATOR
//        ssmParam.put("operator00", getOperator(bizMsg.xxComnScrCondValTxt_H0, bizMsg.xxComnScrCondLbTxt_H0, bizMsg.xxComnScrQueryLikeFlg_H0));
//        ssmParam.put("operator01", getOperator(bizMsg.xxComnScrCondValTxt_H1, bizMsg.xxComnScrCondLbTxt_H1, bizMsg.xxComnScrQueryLikeFlg_H1));
//        ssmParam.put("operator02", getOperator(bizMsg.xxComnScrCondValTxt_H2, bizMsg.xxComnScrCondLbTxt_H2, bizMsg.xxComnScrQueryLikeFlg_H2));
//        ssmParam.put("operator03", getOperator(bizMsg.xxComnScrCondValTxt_H3, bizMsg.xxComnScrCondLbTxt_H3, bizMsg.xxComnScrQueryLikeFlg_H3));
//        ssmParam.put("operator04", getOperator(bizMsg.xxComnScrCondValTxt_H4, bizMsg.xxComnScrCondLbTxt_H4, bizMsg.xxComnScrQueryLikeFlg_H4));
//        // S21 CSA Add Start
//        ssmParam.put("operator05", getOperator(bizMsg.xxComnScrCondValTxt_H5, bizMsg.xxComnScrCondLbTxt_H5, bizMsg.xxComnScrQueryLikeFlg_H5));
//        ssmParam.put("operator06", getOperator(bizMsg.xxComnScrCondValTxt_H6, bizMsg.xxComnScrCondLbTxt_H6, bizMsg.xxComnScrQueryLikeFlg_H6));
//        ssmParam.put("operator07", getOperator(bizMsg.xxComnScrCondValTxt_H7, bizMsg.xxComnScrCondLbTxt_H7, bizMsg.xxComnScrQueryLikeFlg_H7));
//        ssmParam.put("operator08", getOperator(bizMsg.xxComnScrCondValTxt_H8, bizMsg.xxComnScrCondLbTxt_H8, bizMsg.xxComnScrQueryLikeFlg_H8));
//        ssmParam.put("operator09", getOperator(bizMsg.xxComnScrCondValTxt_H9, bizMsg.xxComnScrCondLbTxt_H9, bizMsg.xxComnScrQueryLikeFlg_H9));
//        ssmParam.put("operator0A", getOperator(bizMsg.xxComnScrCondValTxt_HA, bizMsg.xxComnScrCondLbTxt_HA, bizMsg.xxComnScrQueryLikeFlg_HA));
//        ssmParam.put("operator0B", getOperator(bizMsg.xxComnScrCondValTxt_HB, bizMsg.xxComnScrCondLbTxt_HB, bizMsg.xxComnScrQueryLikeFlg_HB));
//        // S21 CSA Add End
//        // CRITERIA
//        ssmParam.put("criteria00", bizMsg.xxComnScrCondValTxt_H0.getValue());
//        ssmParam.put("criteria01", bizMsg.xxComnScrCondValTxt_H1.getValue());
//        ssmParam.put("criteria02", bizMsg.xxComnScrCondValTxt_H2.getValue());
//        ssmParam.put("criteria03", bizMsg.xxComnScrCondValTxt_H3.getValue());
//        ssmParam.put("criteria04", bizMsg.xxComnScrCondValTxt_H4.getValue());
//        // S21 CSA Add Start
//        ssmParam.put("criteria05", bizMsg.xxComnScrCondValTxt_H5.getValue());
//        ssmParam.put("criteria06", bizMsg.xxComnScrCondValTxt_H6.getValue());
//        ssmParam.put("criteria07", bizMsg.xxComnScrCondValTxt_H7.getValue());
//        ssmParam.put("criteria08", bizMsg.xxComnScrCondValTxt_H8.getValue());
//        ssmParam.put("criteria09", bizMsg.xxComnScrCondValTxt_H9.getValue());
//        ssmParam.put("criteria0A", bizMsg.xxComnScrCondValTxt_HA.getValue());
//        ssmParam.put("criteria0B", bizMsg.xxComnScrCondValTxt_HB.getValue());
//        // S21 CSA Add End
        // OPERATOR
        ssmParam.put("operator00", getOperator(bizMsg.xxScrItem500Txt_H0, bizMsg.xxComnScrCondLbTxt_H0, bizMsg.xxComnScrQueryLikeFlg_H0));
        ssmParam.put("operator01", getOperator(bizMsg.xxScrItem500Txt_H1, bizMsg.xxComnScrCondLbTxt_H1, bizMsg.xxComnScrQueryLikeFlg_H1));
        ssmParam.put("operator02", getOperator(bizMsg.xxScrItem500Txt_H2, bizMsg.xxComnScrCondLbTxt_H2, bizMsg.xxComnScrQueryLikeFlg_H2));
        ssmParam.put("operator03", getOperator(bizMsg.xxScrItem500Txt_H3, bizMsg.xxComnScrCondLbTxt_H3, bizMsg.xxComnScrQueryLikeFlg_H3));
        ssmParam.put("operator04", getOperator(bizMsg.xxScrItem500Txt_H4, bizMsg.xxComnScrCondLbTxt_H4, bizMsg.xxComnScrQueryLikeFlg_H4));
        ssmParam.put("operator05", getOperator(bizMsg.xxScrItem500Txt_H5, bizMsg.xxComnScrCondLbTxt_H5, bizMsg.xxComnScrQueryLikeFlg_H5));
        ssmParam.put("operator06", getOperator(bizMsg.xxScrItem500Txt_H6, bizMsg.xxComnScrCondLbTxt_H6, bizMsg.xxComnScrQueryLikeFlg_H6));
        ssmParam.put("operator07", getOperator(bizMsg.xxScrItem500Txt_H7, bizMsg.xxComnScrCondLbTxt_H7, bizMsg.xxComnScrQueryLikeFlg_H7));
        ssmParam.put("operator08", getOperator(bizMsg.xxScrItem500Txt_H8, bizMsg.xxComnScrCondLbTxt_H8, bizMsg.xxComnScrQueryLikeFlg_H8));
        ssmParam.put("operator09", getOperator(bizMsg.xxScrItem500Txt_H9, bizMsg.xxComnScrCondLbTxt_H9, bizMsg.xxComnScrQueryLikeFlg_H9));
        ssmParam.put("operator0A", getOperator(bizMsg.xxScrItem500Txt_HA, bizMsg.xxComnScrCondLbTxt_HA, bizMsg.xxComnScrQueryLikeFlg_HA));
        ssmParam.put("operator0B", getOperator(bizMsg.xxScrItem500Txt_HB, bizMsg.xxComnScrCondLbTxt_HB, bizMsg.xxComnScrQueryLikeFlg_HB));
        // CRITERIA
        ssmParam.put("criteria00", bizMsg.xxScrItem500Txt_H0.getValue());
        ssmParam.put("criteria01", bizMsg.xxScrItem500Txt_H1.getValue());
        ssmParam.put("criteria02", bizMsg.xxScrItem500Txt_H2.getValue());
        ssmParam.put("criteria03", bizMsg.xxScrItem500Txt_H3.getValue());
        ssmParam.put("criteria04", bizMsg.xxScrItem500Txt_H4.getValue());
        ssmParam.put("criteria05", bizMsg.xxScrItem500Txt_H5.getValue());
        ssmParam.put("criteria06", bizMsg.xxScrItem500Txt_H6.getValue());
        ssmParam.put("criteria07", bizMsg.xxScrItem500Txt_H7.getValue());
        ssmParam.put("criteria08", bizMsg.xxScrItem500Txt_H8.getValue());
        ssmParam.put("criteria09", bizMsg.xxScrItem500Txt_H9.getValue());
        ssmParam.put("criteria0A", bizMsg.xxScrItem500Txt_HA.getValue());
        ssmParam.put("criteria0B", bizMsg.xxScrItem500Txt_HB.getValue());
        // End 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]

        // ORDER BY
        String[] orderArray = createSortOrderArray(bizMsg.S);
        ssmParam.put("sortOrder", orderArray);

        ssmParam.put("rowNum", maxRowNum + 1);

        return ssmParam;
    }

    /**
     * get column name
     * @param column
     * @return column name
     */
    private String getColumnName(EZDCStringItem column) {
        if (!ZYPCommonFunc.hasValue(column)) {
            return "Null";
        } else {
            return column.getValue();
        }
    }

    /**
     * get operator
     * @param conditionValue EZDCStringItem
     * @param conditionLabel EZDCStringItem
     * @return operator
     */
    private String getOperator(EZDCStringItem conditionValue, EZDCStringItem conditionLabel, EZDCStringItem likeFlag) {

        if (!ZYPCommonFunc.hasValue(conditionValue)) {
            return null;
        }
        if (!ZYPCommonFunc.hasValue(conditionLabel)) {
            return null;
        }

        if (ZYPConstant.FLG_ON_Y.equals(likeFlag.getValue()) && conditionValue.getValue().indexOf("%") >= 0) {
            return "LIKE";
        } else {
            return "=";
        }
    }

    /**
     * create sort order array
     * @param sortOrderArray
     * @return sort order array
     */
    private String[] createSortOrderArray(NWAL1130_SCMsgArray sortOrderArray) {

        List<String> sortOrderList = new ArrayList<String>();
        for (int i = 0; i < sortOrderArray.length(); i++) {
            NWAL1130_SCMsg sortOrder = sortOrderArray.no(i);
            if (ZYPCommonFunc.hasValue(sortOrder.xxTblSortColNm_S)) {
                sortOrderList.add(String.format("%s %s", sortOrder.xxTblSortColNm_S.getValue(), sortOrder.xxSortOrdByTxt_S.getValue()));
            }
        }

        if (sortOrderList.size() == 0) {
            return null;
        }
        return sortOrderList.toArray(new String[0]);
    }
}
