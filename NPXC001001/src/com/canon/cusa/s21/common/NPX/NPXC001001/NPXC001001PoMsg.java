/**
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NPX.NPXC001001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.PO_MSGTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * PO Message
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/30/2012   SRA             N.Otsuji        Create          N/A
 *</pre>
 */
public class NPXC001001PoMsg {

    /**
     * Key to retrieve maximum number of bytes allowed in a PO message
     * from NUM_CONST
     */
    private static final String NUM_CONST_PO_MSG_MAX_BYTES = "NPXC0010_PO_MSG_MAX_BYTES";

    /**
     * Key to retrieve PO message template
     */
    private static final String VAR_CHAR_CONST_PO_MSG_TMPL = "NPXC0010_PO_MSG_TMPL";

    /**
     * The Constant [@] was not found on Constant table.
     */
    private static final String NPAM1010E = "NPAM1010E";

    /**
     * Maximum number of bytes allowed in a PO message segment
     */
    private static final int PO_MSG_SEGMENT_BYTES = 65;

    /**
     * Terminator
     */
    private static final String PO_MSG_SEGMENT_TERMINATOR = "$";

    /**
     * SSM Batch Client
     */
    private static final S21SsmBatchClient SSM_CLIENT = S21SsmBatchClient.getClient(NPXC001001PoMsg.class);

    /**
     * Get maximum nummber of bytes allowed in a PO message
     * @return BigDecimal
     */
    public static BigDecimal getPoMsgMaxBytes() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        String glblCmpyCd = profileService.getGlobalCompanyCode();
        BigDecimal maxBytes = ZYPCodeDataUtil.getNumConstValue(NUM_CONST_PO_MSG_MAX_BYTES, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(maxBytes)) {
            return maxBytes;
        } else {
            String[] list = {NUM_CONST_PO_MSG_MAX_BYTES };
            throw new S21AbendException(NPAM1010E, list);
        }
    }

    /**
     * Get Order Acknowledge Message Template
     * @return template
     */
    public static String getPoMsgTmpl() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        String glblCmpyCd = profileService.getGlobalCompanyCode();
        String tmpl = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_PO_MSG_TMPL, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(tmpl)) {
            tmpl = tmpl.replaceAll("\\\\r", "\r").replaceAll("\\\\n", "\n");
            return tmpl;
        } else {
            String[] list = {VAR_CHAR_CONST_PO_MSG_TMPL };
            throw new S21AbendException(NPAM1010E, list);
        }
    }

    /**
     * Concatenate PO message segments
     * @param poMsgList List<PO_MSGTMsg>
     * @return String
     */
    public static String concatenatePoMsg(List<PO_MSGTMsg> poMsgList) {
        if (poMsgList == null || poMsgList.isEmpty()) {
            return null;
        }
        BigDecimal maxBytes = getPoMsgMaxBytes();
        int length = maxBytes.intValueExact();
        StringBuilder buf = new StringBuilder(length);
        String segment;
        for (PO_MSGTMsg tMsg : poMsgList) {
            segment = tMsg.poMsgTxt.getValue();
            /**
             * See NPXC001001PoMsg#splitPoMsg
             */
            buf.append(segment.substring(0, segment.length() - 1));
        }
        return buf.toString();
    }

    /**
     * Split PO message into PO message segments
     * @param poMsg PO message
     * @return List<String>
     */
    public static List<String> splitPoMsg(String poMsg) {
        BigDecimal maxBytes = getPoMsgMaxBytes();
        int bytes = maxBytes.intValueExact();
        List<String> poMsgList = new ArrayList<String>(bytes / PO_MSG_SEGMENT_BYTES);
        final int length = poMsg.length();
        /**
         * EZDTStringItem#setValue trims trailing spaces of the
         * specified string. To preserve trailing spaces, PO message
         * is segmented to fit into (PO_MSG_SEGMENT_BYTES - 1) bytes
         * and PO_MSG_SEGMENT_TERMINATOR is appended to each segment.
         */
        final int segBytes = PO_MSG_SEGMENT_BYTES - 1;
        for (int i = 0; i < length; i += segBytes) {
            int end = i + segBytes;
            if (end > length) {
                end = length;
            }
            poMsgList.add(poMsg.substring(i, end) + PO_MSG_SEGMENT_TERMINATOR);
        }
        return poMsgList;
    }

    /**
     * Get ezuptime from PO message segments
     * @param poMsgList List<PO_MSGTMsg>
     * @return ezuptime
     */
    public static String getEzUpTime(List<PO_MSGTMsg> poMsgList) {
        if (poMsgList == null || poMsgList.isEmpty()) {
            return null;
        } else {
            return poMsgList.get(0).ezUpTime.getValue();
        }
    }

    /**
     * Get ezuptimezone from PO message segments
     * @param poMsgList List<PO_MSGTMsg>
     * @return ezuptimezone
     */
    public static String getEzUpTimeZone(List<PO_MSGTMsg> poMsgList) {
        if (poMsgList == null || poMsgList.isEmpty()) {
            return null;
        } else {
            return poMsgList.get(0).ezUpTimeZone.getValue();
        }
    }

    /**
     * Get PO Message corresponding to the specified PO#, PO Line#
     * @param glblCmpyCd Global Company Code
     * @param poMsgTpCd PO Message Type Code
     * @param poOrdNum PO Order Number
     * @param poOrdDtlLineNum PO Order Detail Line Number
     * @return PO message
     */
    public static List<PO_MSGTMsg> getPoMsg(String glblCmpyCd, String poMsgTpCd, String poOrdNum, String poOrdDtlLineNum) {
        return getPoMsg(glblCmpyCd, poMsgTpCd, poOrdNum, poOrdDtlLineNum, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N);
    }

    /**
     * Get PO Message corresponding to the specified PO#, PO Line#
     * @param glblCmpyCd Global Company Code
     * @param poMsgTpCd PO Message Type Code
     * @param poOrdNum PO Order Number
     * @param poOrdDtlLineNum PO Order Detail Line Number
     * @return PO message
     */
    public static List<PO_MSGTMsg> getPoMsgForUpdateNoWait(String glblCmpyCd, String poMsgTpCd, String poOrdNum, String poOrdDtlLineNum) {
        return getPoMsg(glblCmpyCd, poMsgTpCd, poOrdNum, poOrdDtlLineNum, ZYPConstant.FLG_ON_Y, ZYPConstant.FLG_ON_Y);
    }

    /**
     * Get PO Message corresponding to the specified PO#, PO Line#
     * @param poMsgTpCd PO Message Type Code
     * @param poOrdNum PO Order Number
     * @param poOrdDtlLineNum PO Order Detail Line Number
     * @param forUpdate Y/N
     * @return List<PO_MSGTMsg>
     */
    private static List<PO_MSGTMsg> getPoMsg(String glblCmpyCd, String poMsgTpCd, String poOrdNum, String poOrdDtlLineNum, String forUpdate, String noWait) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("poOrdNum", poOrdNum);
        params.put("poOrdDtlLineNum", poOrdDtlLineNum);
        params.put("poMsgTpCd", poMsgTpCd);
        params.put("forUpdate", forUpdate);
        params.put("noWait", noWait);

        List<PO_MSGTMsg> poMsgList = (List<PO_MSGTMsg>) SSM_CLIENT.queryObjectList("getPoMsg", params);

        return poMsgList;
    }
}
