/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC113001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.List;

import business.parts.NSZC113001PMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;

/**
 * <pre>
 * Date Time Conversion API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/02/09   Hitachi         A.Kohinata      Create          QC#16993
 * 2017/05/19   Hitachi         K.Kitachi       Update          QC#18217
 *</pre>
 */
public class NSZC113001 extends S21ApiCommonBase {

    /**
     * Constructor
     */
    public NSZC113001() {
        super();
    }

    /**
     * execute
     * @param pMsgList List<NSZC113001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NSZC113001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {
        for (NSZC113001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType);
        }
    }

    /**
     * execute Date Time Conversion API.
     * @param pMsg NSZC113001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC113001PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        ZYPTableUtil.clear(pMsg.xxMsgIdList);

        try {
            int mode = Integer.parseInt(pMsg.xxModeCd.getValue());

            SvcTimeZoneInfo svcTimeZoneInfo = NSXC001001SvcTimeZone.convertTime(mode, pMsg.xxRqstTs_I.getValue(), pMsg.ctryCd.getValue(), pMsg.postCd.getValue());
            if (svcTimeZoneInfo != null) {
                setValue(pMsg.xxRqstTs_O, svcTimeZoneInfo.getDateTime());
                // START 2017/05/19 K.Kitachi [QC#18217, ADD]
                setValue(pMsg.tmZoneCd_O, svcTimeZoneInfo.getTimeZone());
                // END 2017/05/19 K.Kitachi [QC#18217, ADD]
            } else {
                pMsg.xxRqstTs_O.clear();
            }
        } catch (NumberFormatException e) {
            pMsg.xxRqstTs_O.clear();
        }

        msgMap.flush();
    }
}
