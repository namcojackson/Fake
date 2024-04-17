/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFAL0230;

import static business.blap.NFAL0230.constant.NFAL0230Constant.DEFALT_MAX_LEVEL;
import static business.blap.NFAL0230.constant.NFAL0230Constant.SEGMENT2;
import static business.blap.NFAL0230.constant.NFAL0230Constant.SEGMENT3;
import static business.blap.NFAL0230.constant.NFAL0230Constant.SEGMENT4;
import static business.blap.NFAL0230.constant.NFAL0230Constant.SEGMENT5;
import static business.blap.NFAL0230.constant.NFAL0230Constant.SEGMENT6;
import static business.blap.NFAL0230.constant.NFAL0230Constant.SEGMENT7;
import static business.blap.NFAL0230.constant.NFAL0230Constant.SEGMENT8;
import static business.blap.NFAL0230.constant.NFAL0230Constant.SEGMENT9;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/23   Fujitsu         T.Murai         Create          N/A
 * </pre>
 */
public final class NFAL0230Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NFAL0230Query MY_INSTANCE = new NFAL0230Query();

    /**
     * Constructor.
     */
    private NFAL0230Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFAL0230Query
     */
    public static NFAL0230Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Search Result
     * @param bizMsg NFAL0230CMsg
     * @param segmentNm String
     * @return Search Result List
     */
    public S21SsmEZDResult getTopLevelValueSeg(NFAL0230CMsg bizMsg, String segmentNm) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("segNm", segmentNm);
        params.put("strKome", "**");

        if (SEGMENT2.equals(segmentNm)) {
            params.put("seg2Flg", ZYPConstant.FLG_ON_Y);

        } else if (SEGMENT3.equals(segmentNm)) {
            params.put("seg3Flg", ZYPConstant.FLG_ON_Y);

        } else if (SEGMENT4.equals(segmentNm)) {
            params.put("seg4Flg", ZYPConstant.FLG_ON_Y);

        } else if (SEGMENT5.equals(segmentNm)) {
            params.put("seg5Flg", ZYPConstant.FLG_ON_Y);

        } else if (SEGMENT6.equals(segmentNm)) {
            params.put("seg6Flg", ZYPConstant.FLG_ON_Y);

        } else if (SEGMENT7.equals(segmentNm)) {
            params.put("seg7Flg", ZYPConstant.FLG_ON_Y);

        } else if (SEGMENT8.equals(segmentNm)) {
            params.put("seg8Flg", ZYPConstant.FLG_ON_Y);

        } else if (SEGMENT9.equals(segmentNm)) {
            params.put("seg9Flg", ZYPConstant.FLG_ON_Y);
        } else {
            return null;
        }
        return getSsmEZDClient().queryObjectList("getTopLevelValue", params);

    }

    /**
     * Get Search Result For Segment2
     * @param bizMsg NFAL0230CMsg
     * @param glblMsg NFAL0230SMsg
     * @param topLevelValueMap Map<String, Object>
     * @return Search Result List
     */
    public S21SsmEZDResult getTreeInfoSeg(NFAL0230CMsg bizMsg, NFAL0230SMsg glblMsg, Map<String, Object> topLevelValueMap) {

        String segmentNm = bizMsg.coaSegNm.getValue();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("topCoaValCd", topLevelValueMap.get("COA_VAL"));
        params.put("topCoaValTxt", topLevelValueMap.get("COA_VAL_TXT"));
        params.put("segNm", segmentNm);
        params.put("maxLevel", DEFALT_MAX_LEVEL);
        params.put("rowNum", glblMsg.A.length() + 1);

        if (SEGMENT2.equals(segmentNm)) {
            params.put("seg2Flg", ZYPConstant.FLG_ON_Y);

        } else if (SEGMENT3.equals(segmentNm)) {
            params.put("seg3Flg", ZYPConstant.FLG_ON_Y);

        } else if (SEGMENT4.equals(segmentNm)) {
            params.put("seg4Flg", ZYPConstant.FLG_ON_Y);

        } else if (SEGMENT5.equals(segmentNm)) {
            params.put("seg5Flg", ZYPConstant.FLG_ON_Y);

        } else if (SEGMENT6.equals(segmentNm)) {
            params.put("seg6Flg", ZYPConstant.FLG_ON_Y);

        } else if (SEGMENT7.equals(segmentNm)) {
            params.put("seg7Flg", ZYPConstant.FLG_ON_Y);

        } else if (SEGMENT8.equals(segmentNm)) {
            params.put("seg8Flg", ZYPConstant.FLG_ON_Y);

        } else if (SEGMENT9.equals(segmentNm)) {
            params.put("seg9Flg", ZYPConstant.FLG_ON_Y);
        }

        return getSsmEZDClient().queryEZDMsgArray("getTreeInfo", params, glblMsg.A);
    }

    /**
     * Get Max Tree Level
     * @param bizMsg NFAL0230CMsg
     * @return S21SsmEZDResult Level
     */
    public S21SsmEZDResult getMaxTreeLevel(NFAL0230CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("segNm", bizMsg.coaSegNm.getValue());

        return getSsmEZDClient().queryObject("getMaxTreeLevel", params);
    }

}
