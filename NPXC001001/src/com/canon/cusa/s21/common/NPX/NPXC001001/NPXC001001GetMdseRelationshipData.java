/**
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NPX.NPXC001001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Get Merchandise Relationship Data
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/30/2012   CITS            K.Ogino         Create          QC#55711
 * 02/19/2022   CITS            K.Ogino         Update          QC#59718
 *</pre>
 */
public class NPXC001001GetMdseRelationshipData {

    /**
     * SSM Batch Client
     */
    private static final S21SsmBatchClient SSM_CLIENT = S21SsmBatchClient.getClient(NPXC001001GetMdseRelationshipData.class);

    /**
     * Get MDSE Down Level Item List
     *
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param mdseItemRelnTpCds String[]
     * @return List<String> Merchandise Code List
     */
    public static List<String> getMdseRelationshipData(String glblCmpyCd, String mdseCd, String[] mdseItemRelnTpCds) {

        List<String> downLevelItemList = new ArrayList<String>();

        // Parameter check
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            return downLevelItemList;
        }

        // Parameter check
        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            return downLevelItemList;
        }

        // Set MDSE_ITEM_RELN_TP_CD
        String[] itrlMdseItemrelnTpCds = mdseItemRelnTpCds;
        if (itrlMdseItemrelnTpCds == null || itrlMdseItemrelnTpCds.length == 0) {
            String varChar = ZYPCodeDataUtil.getVarCharConstValue("NPXC0010_DOWNITEM_RELN_TP_CD", glblCmpyCd);
            if (ZYPCommonFunc.hasValue(varChar)) {
                itrlMdseItemrelnTpCds = varChar.split(",");
            }
        }

        List<String> chkCompMdseCdList = new ArrayList<String>();
        boolean dupFlg = false;
        boolean otherItempFlg = false;
        chkCompMdseCdList.add(mdseCd);
        // get RELN_MDSE_CD
        List<String> initDownLevelItemList = getRelnMdseCd(glblCmpyCd, mdseCd, itrlMdseItemrelnTpCds);

        if (initDownLevelItemList == null || initDownLevelItemList.isEmpty()) {
            downLevelItemList = new ArrayList<String>();
            downLevelItemList.add(mdseCd);
            return downLevelItemList;
        } else {
            for (String relnMdseCd : initDownLevelItemList) {
                if (!chkCompMdseCdList.contains(relnMdseCd)) {
                    downLevelItemList.add(relnMdseCd);
                }
            }
        }

        List<String> downItemList = downLevelItemList;
        List<String> relnDownItemList = new ArrayList<String>();
        while (true) {
            dupFlg = false;
            otherItempFlg = false;
            relnDownItemList.clear();
            if (downItemList == null || downItemList.isEmpty()) {
                break;
            }
            chkCompMdseCdList.addAll(downItemList);
            downItemList = getRelnMdseCd(glblCmpyCd, downItemList, itrlMdseItemrelnTpCds);
            if (downItemList == null || downItemList.isEmpty()) {
                break;
            }
            for (String relnMdseCd : downItemList) {
                if (chkCompMdseCdList.contains(relnMdseCd)) {
                    dupFlg = true;
                } else {
                    relnDownItemList.add(relnMdseCd);
                    downLevelItemList.add(relnMdseCd);
                    otherItempFlg = true;
                }
            }
            downItemList.clear();
            downItemList.addAll(relnDownItemList);
            if (!otherItempFlg && dupFlg) {
                break;
            }
        }

        downLevelItemList.add(mdseCd);

        return downLevelItemList;
    }

    /**
     * Get MDSE REFURBISHED Item List. Add QC#59718
     *
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param mdseItemRelnTpCds String[]
     * @return List<String> Merchandise Code List
     */
    public static List<String> getMdseRelationshipDataForRefurbished(String glblCmpyCd, String mdseCd, String[] mdseItemRelnTpCds) {

        List<String> downLevelItemList = new ArrayList<String>();

        // Parameter check
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            return downLevelItemList;
        }

        // Parameter check
        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            return downLevelItemList;
        }

        // Set MDSE_ITEM_RELN_TP_CD
        String[] itrlMdseItemrelnTpCds = mdseItemRelnTpCds;
        if (itrlMdseItemrelnTpCds == null || itrlMdseItemrelnTpCds.length == 0) {
            String varChar = ZYPCodeDataUtil.getVarCharConstValue("NPXC0010_REFURB_RELN_TP_CD", glblCmpyCd);
            if (ZYPCommonFunc.hasValue(varChar)) {
                itrlMdseItemrelnTpCds = varChar.split(",");
            }
        }

        List<String> chkCompMdseCdList = new ArrayList<String>();
        boolean dupFlg = false;
        boolean otherItempFlg = false;
        chkCompMdseCdList.add(mdseCd);
        // get RELN_MDSE_CD
        List<String> initDownLevelItemList = getMdseCd(glblCmpyCd, mdseCd, itrlMdseItemrelnTpCds);

        if (initDownLevelItemList == null || initDownLevelItemList.isEmpty()) {
            downLevelItemList = new ArrayList<String>();
            downLevelItemList.add(mdseCd);
            return downLevelItemList;
        } else {
            for (String relnMdseCd : initDownLevelItemList) {
                if (!chkCompMdseCdList.contains(relnMdseCd)) {
                    downLevelItemList.add(relnMdseCd);
                }
            }
        }

        List<String> downItemList = downLevelItemList;
        List<String> relnDownItemList = new ArrayList<String>();
        while (true) {
            dupFlg = false;
            otherItempFlg = false;
            relnDownItemList.clear();
            if (downItemList == null || downItemList.isEmpty()) {
                break;
            }
            chkCompMdseCdList.addAll(downItemList);
            downItemList = getMdseCd(glblCmpyCd, downItemList, itrlMdseItemrelnTpCds);
            if (downItemList == null || downItemList.isEmpty()) {
                break;
            }
            for (String relnMdseCd : downItemList) {
                if (chkCompMdseCdList.contains(relnMdseCd)) {
                    dupFlg = true;
                } else {
                    relnDownItemList.add(relnMdseCd);
                    downLevelItemList.add(relnMdseCd);
                    otherItempFlg = true;
                }
            }
            downItemList.clear();
            downItemList.addAll(relnDownItemList);
            if (!otherItempFlg && dupFlg) {
                break;
            }
        }

        downLevelItemList.add(mdseCd);

        return downLevelItemList;
    }

    private static List<String> getRelnMdseCd(String glblCmpyCd, String mdseCd, String[] mdseItemRelnTpCds) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCds", new String[] {mdseCd });
        if (mdseItemRelnTpCds != null) {
            params.put("mdseItemRelnTpCds", mdseItemRelnTpCds);
        }

        List<String> relnMdseCdList = (List<String>) SSM_CLIENT.queryObjectList("getRelnMdseCd", params);

        return relnMdseCdList;
    }

    private static List<String> getRelnMdseCd(String glblCmpyCd, List<String> mdseCdList, String[] mdseItemRelnTpCds) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCds", mdseCdList);
        if (mdseItemRelnTpCds != null) {
            params.put("mdseItemRelnTpCds", mdseItemRelnTpCds);
        }

        List<String> relnMdseCdList = (List<String>) SSM_CLIENT.queryObjectList("getRelnMdseCd", params);

        return relnMdseCdList;
    }

    private static List<String> getMdseCd(String glblCmpyCd, String mdseCd, String[] mdseItemRelnTpCds) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCds", new String[] {mdseCd });
        if (mdseItemRelnTpCds != null) {
            params.put("mdseItemRelnTpCds", mdseItemRelnTpCds);
        }

        List<String> relnMdseCdList = (List<String>) SSM_CLIENT.queryObjectList("getMdseCd", params);

        return relnMdseCdList;
    }

    private static List<String> getMdseCd(String glblCmpyCd, List<String> mdseCdList, String[] mdseItemRelnTpCds) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCds", mdseCdList);
        if (mdseItemRelnTpCds != null) {
            params.put("mdseItemRelnTpCds", mdseItemRelnTpCds);
        }

        List<String> relnMdseCdList = (List<String>) SSM_CLIENT.queryObjectList("getMdseCd", params);

        return relnMdseCdList;
    }
}
