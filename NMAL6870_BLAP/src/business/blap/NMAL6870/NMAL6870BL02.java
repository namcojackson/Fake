/*
 * <Pre>Copyright(c)2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL6870;

import static business.blap.NMAL6870.constant.NMAL6870Constant.NZZM0000E;
import static business.blap.NMAL6870.constant.NMAL6870Constant.NZZM0001W;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Multi Selection Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/27   Fujitsu         S.Yoshida         Create          N/A
 *</pre>
 */
public class NMAL6870BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL6870_INIT".equals(screenAplID)) {
                doProcess_NMAL6870_INIT((NMAL6870CMsg) cMsg);
            } else if ("NMAL6870Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL6870Scrn00_Search((NMAL6870CMsg) cMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * Process of screen event NMAL6870_INIT
     * </pre>
     * @param bizMsg Business Component Interface Message
     */
    private void doProcess_NMAL6870_INIT(NMAL6870CMsg bizMsg) {

        boolean errorExists = false;

        if (errorExists) {
            // error exists
            return;
        }

        // search
        search(bizMsg, true);
    }

    /**
     * <pre>
     * Process of screen event NMAL6870Scrn00_Search
     * </pre>
     * @param bizMsg Business Component Interface Message
     */
    private void doProcess_NMAL6870Scrn00_Search(NMAL6870CMsg bizMsg) {

        boolean errorExists = false;

        if (errorExists) {
            // error exists
            return;
        }

        // search
        search(bizMsg, false);
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param initFlg Init Event Flag (true = Init Event)
     */
    private void search(NMAL6870CMsg bizMsg, boolean initFlg) {

        ZYPTableUtil.clear(bizMsg.A);

        Map<String, Object> ssmParam = createSearchCriteria(bizMsg, bizMsg.A.length(), initFlg);

        S21SsmEZDResult ssmResult = NMAL6870Query.getInstance().search(ssmParam, bizMsg);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NZZM0000E);

        } else {
            if (ssmResult.getQueryResultCount() > bizMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                bizMsg.A.setValidCount(bizMsg.A.length());
            } else {
                bizMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }
        }
    }

    /**
     * create search criteria
     * @param bizMsg Business Component Interface Message
     * @param maxRowNum max row number
     * @return search criteria
     */
    private Map<String, Object> createSearchCriteria(NMAL6870CMsg bizMsg, int maxRowNum, boolean initFlg) {

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

        // WHERE
        if (ZYPCommonFunc.hasValue(bizMsg.xxComnScrQueryFltrTxt_H0)) {
            ssmParam.put("where00", "UPPER(" + bizMsg.xxComnScrQueryFltrTxt_H0.getValue() + ")");
        } else {
            ssmParam.put("where00", bizMsg.xxComnScrQueryFltrTxt_H0.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxComnScrQueryFltrTxt_H1)) {
            ssmParam.put("where01", "UPPER(" + bizMsg.xxComnScrQueryFltrTxt_H1.getValue() + ")");
        } else {
            ssmParam.put("where01", bizMsg.xxComnScrQueryFltrTxt_H1.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxComnScrQueryFltrTxt_H2)) {
            ssmParam.put("where02", "UPPER(" + bizMsg.xxComnScrQueryFltrTxt_H2.getValue() + ")");
        } else {
            ssmParam.put("where02", bizMsg.xxComnScrQueryFltrTxt_H2.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxComnScrQueryFltrTxt_H3)) {
            ssmParam.put("where03", "UPPER(" + bizMsg.xxComnScrQueryFltrTxt_H3.getValue() + ")");
        } else {
            ssmParam.put("where03", bizMsg.xxComnScrQueryFltrTxt_H3.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxComnScrQueryFltrTxt_H4)) {
            ssmParam.put("where04", "UPPER(" + bizMsg.xxComnScrQueryFltrTxt_H4.getValue() + ")");
        } else {
            ssmParam.put("where04", bizMsg.xxComnScrQueryFltrTxt_H4.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxComnScrQueryFltrTxt_H5)) {
            ssmParam.put("where05", "UPPER(" + bizMsg.xxComnScrQueryFltrTxt_H5.getValue() + ")");
        } else {
            ssmParam.put("where05", bizMsg.xxComnScrQueryFltrTxt_H5.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxComnScrQueryFltrTxt_H6)) {
            ssmParam.put("where06", "UPPER(" + bizMsg.xxComnScrQueryFltrTxt_H6.getValue() + ")");
        } else {
            ssmParam.put("where06", bizMsg.xxComnScrQueryFltrTxt_H6.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxComnScrQueryFltrTxt_H7)) {
            ssmParam.put("where07", "UPPER(" + bizMsg.xxComnScrQueryFltrTxt_H7.getValue() + ")");
        } else {
            ssmParam.put("where07", bizMsg.xxComnScrQueryFltrTxt_H7.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxComnScrQueryFltrTxt_H8)) {
            ssmParam.put("where08", "UPPER(" + bizMsg.xxComnScrQueryFltrTxt_H8.getValue() + ")");
        } else {
            ssmParam.put("where08", bizMsg.xxComnScrQueryFltrTxt_H8.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxComnScrQueryFltrTxt_H9)) {
            ssmParam.put("where09", "UPPER(" + bizMsg.xxComnScrQueryFltrTxt_H9.getValue() + ")");
        } else {
            ssmParam.put("where09", bizMsg.xxComnScrQueryFltrTxt_H9.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxComnScrQueryFltrTxt_HA)) {
            ssmParam.put("where0A", "UPPER(" + bizMsg.xxComnScrQueryFltrTxt_HA.getValue() + ")");
        } else {
            ssmParam.put("where0A", bizMsg.xxComnScrQueryFltrTxt_HA.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxComnScrQueryFltrTxt_HB)) {
            ssmParam.put("where0B", "UPPER(" + bizMsg.xxComnScrQueryFltrTxt_HB.getValue() + ")");
        } else {
            ssmParam.put("where0B", bizMsg.xxComnScrQueryFltrTxt_HB.getValue());
        }

        // OPERATOR
        ssmParam.put("operator00", getOperator(bizMsg.xxComnScrCondValTxt_H0, bizMsg.xxComnScrCondLbTxt_H0, bizMsg.xxComnScrQueryLikeFlg_H0));
        ssmParam.put("operator01", getOperator(bizMsg.xxComnScrCondValTxt_H1, bizMsg.xxComnScrCondLbTxt_H1, bizMsg.xxComnScrQueryLikeFlg_H1));
        ssmParam.put("operator02", getOperator(bizMsg.xxComnScrCondValTxt_H2, bizMsg.xxComnScrCondLbTxt_H2, bizMsg.xxComnScrQueryLikeFlg_H2));
        ssmParam.put("operator03", getOperator(bizMsg.xxComnScrCondValTxt_H3, bizMsg.xxComnScrCondLbTxt_H3, bizMsg.xxComnScrQueryLikeFlg_H3));
        ssmParam.put("operator04", getOperator(bizMsg.xxComnScrCondValTxt_H4, bizMsg.xxComnScrCondLbTxt_H4, bizMsg.xxComnScrQueryLikeFlg_H4));
        ssmParam.put("operator05", getOperator(bizMsg.xxComnScrCondValTxt_H5, bizMsg.xxComnScrCondLbTxt_H5, bizMsg.xxComnScrQueryLikeFlg_H5));
        ssmParam.put("operator06", getOperator(bizMsg.xxComnScrCondValTxt_H6, bizMsg.xxComnScrCondLbTxt_H6, bizMsg.xxComnScrQueryLikeFlg_H6));
        ssmParam.put("operator07", getOperator(bizMsg.xxComnScrCondValTxt_H7, bizMsg.xxComnScrCondLbTxt_H7, bizMsg.xxComnScrQueryLikeFlg_H7));
        ssmParam.put("operator08", getOperator(bizMsg.xxComnScrCondValTxt_H8, bizMsg.xxComnScrCondLbTxt_H8, bizMsg.xxComnScrQueryLikeFlg_H8));
        ssmParam.put("operator09", getOperator(bizMsg.xxComnScrCondValTxt_H9, bizMsg.xxComnScrCondLbTxt_H9, bizMsg.xxComnScrQueryLikeFlg_H9));
        ssmParam.put("operator0A", getOperator(bizMsg.xxComnScrCondValTxt_HA, bizMsg.xxComnScrCondLbTxt_HA, bizMsg.xxComnScrQueryLikeFlg_HA));
        ssmParam.put("operator0B", getOperator(bizMsg.xxComnScrCondValTxt_HB, bizMsg.xxComnScrCondLbTxt_HB, bizMsg.xxComnScrQueryLikeFlg_HB));

        // CRITERIA
        ssmParam.put("criteria00", bizMsg.xxComnScrCondValTxt_H0.getValue());
        ssmParam.put("criteria01", bizMsg.xxComnScrCondValTxt_H1.getValue());
        ssmParam.put("criteria02", bizMsg.xxComnScrCondValTxt_H2.getValue());
        ssmParam.put("criteria03", bizMsg.xxComnScrCondValTxt_H3.getValue());
        ssmParam.put("criteria04", bizMsg.xxComnScrCondValTxt_H4.getValue());
        ssmParam.put("criteria05", bizMsg.xxComnScrCondValTxt_H5.getValue());
        ssmParam.put("criteria06", bizMsg.xxComnScrCondValTxt_H6.getValue());
        ssmParam.put("criteria07", bizMsg.xxComnScrCondValTxt_H7.getValue());
        ssmParam.put("criteria08", bizMsg.xxComnScrCondValTxt_H8.getValue());
        ssmParam.put("criteria09", bizMsg.xxComnScrCondValTxt_H9.getValue());
        ssmParam.put("criteria0A", bizMsg.xxComnScrCondValTxt_HA.getValue());
        ssmParam.put("criteria0B", bizMsg.xxComnScrCondValTxt_HB.getValue());

        if (initFlg
                && bizMsg.R.getValidCount() > 0) {

            List<List<Map<String, String>>> queryAll = new ArrayList<List<Map<String, String>>>();

            for (int r = 0; r < bizMsg.R.getValidCount(); r++) {
                NMAL6870_RCMsg rCMsg = bizMsg.R.no(r);
                List<Map<String, String>> queryOfRecord = new ArrayList<Map<String, String>>();

                for (int c = 0; c < bizMsg.C.getValidCount(); c++) {
                    NMAL6870_CCMsg cCMsg = bizMsg.C.no(c);
                    String col = cCMsg.xxComnScrQueryColNm_C.getValue();
                    String val = getEZDStringItemValue(rCMsg, "xxComnScrColValTxt_", c);

                    if (!ZYPCommonFunc.hasValue(col)
                            || !ZYPCommonFunc.hasValue(val)) {
                        continue;
                    }
                    Map<String, String> queryMap = new HashMap<String, String>();
                    queryMap.put("col", col);
                    queryMap.put("val", val);
                    queryOfRecord.add(queryMap);
                }

                if (!queryOfRecord.isEmpty()) {
                    queryAll.add(queryOfRecord);
                }
            }

            if (!queryAll.isEmpty()) {
                ssmParam.put("queryAll", queryAll);
            }
        }

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
    private String[] createSortOrderArray(NMAL6870_SCMsgArray sortOrderArray) {

        List<String> sortOrderList = new ArrayList<String>();
        for (int i = 0; i < sortOrderArray.length(); i++) {
            NMAL6870_SCMsg sortOrder = sortOrderArray.no(i);
            if (ZYPCommonFunc.hasValue(sortOrder.xxTblSortColNm_S)) {
                sortOrderList.add(String.format("%s %s", sortOrder.xxTblSortColNm_S.getValue(), sortOrder.xxSortOrdByTxt_S.getValue()));
            }
        }

        if (sortOrderList.size() == 0) {
            return null;
        }
        return sortOrderList.toArray(new String[0]);
    }

    /**
     * Get EZDStringItem value
     * @param msg EZDMsg
     * @param itemName String
     * @param suffixNumber int
     * @return String value
     */
    public static String getEZDStringItemValue(EZDMsg msg, String itemName, int suffixNumber) {
        return msg.getValueString(String.format(itemName + "%d", suffixNumber), -1);
    }

}
