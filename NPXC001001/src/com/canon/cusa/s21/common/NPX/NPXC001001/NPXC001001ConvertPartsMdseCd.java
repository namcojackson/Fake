/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NPX.NPXC001001;

import parts.dbcommon.EZDTBLAccessor;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

import business.db.MDSETMsg;

/**
 * <pre>
 * Convert Parts MDSE Code
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/09/19   Hitachi         T.Tomita        Create          MEX-LC020
 * 2013/09/30   Hitachi         T.Tomita        Update          MEX-LC020
 *</pre>
 */
public class NPXC001001ConvertPartsMdseCd {

    /** Mode : Convert to MDSE Code from Parts Code */
    public static final String MODE_PARTS = "11";

    /** Mode : Convert to MDSE Code from Parts Code and Size */
    public static final String MODE_PARTS_AND_SIZE = "12";

    /** Mode : Convert to Parts Code and Size from MDSE Code */
    public static final String MODE_MDSE = "21";

    /** Error Message ID : Mode is invalid */
    public static final String MSG_ID_INVALID_MODE = "NPZM0101E";

    /** Error Message ID : MDSE Code does not exist in MDSE Master. */
    public static final String MSG_ID_NOT_EXIST_MDSE = "NPZM0147E";

    /** Default Size */
    private static final String DEFAULT_SIZE = "000";

    /** Hyphen */
    private static final String HYPHEN = "-";

    /**
     * Convert PartsMdseCd
     * @param bean NPXC001001ConvertPartsMdseCdBean
     */
    public static void convertPartsMdseCd(NPXC001001ConvertPartsMdseCdBean bean) {
        if (MODE_PARTS.equals(bean.getMode())) {
            checkMdseCdFromParts(bean);
        } else if (MODE_PARTS_AND_SIZE.equals(bean.getMode())) {
            checkMdseCdFromPartsAndSize(bean);
        } else if (MODE_MDSE.equals(bean.getMode())) {
            splitPartsAndSize(bean);
        } else {
            bean.setErrCd(MSG_ID_INVALID_MODE);
        }
    }

    private static void checkMdseCdFromParts(NPXC001001ConvertPartsMdseCdBean bean) {
        String targetMdseCd = bean.getXtrnlSysPrtCd();
        if (targetMdseCd.length() > 16) {
            bean.setErrCd(MSG_ID_NOT_EXIST_MDSE);
            return;
        }

        if (getMdseCd(bean, targetMdseCd)) {
            return;
        }

        if (targetMdseCd.length() == 12) {
            targetMdseCd = targetMdseCd + DEFAULT_SIZE;
            if (getMdseCd(bean, targetMdseCd)) {
                return;
            }
        } else if (targetMdseCd.length() == 16 && HYPHEN.equals(targetMdseCd.substring(12, 13))) {
            targetMdseCd = targetMdseCd.substring(0, 12) + targetMdseCd.substring(13);
            if (getMdseCd(bean, targetMdseCd)) {
                return;
            }
        }
        bean.setErrCd(MSG_ID_NOT_EXIST_MDSE);
    }

    private static void checkMdseCdFromPartsAndSize(NPXC001001ConvertPartsMdseCdBean bean) {
        String targetMdseCd = bean.getXtrnlSysPrtCd();
        if (DEFAULT_SIZE.equals(bean.getXtrnlSysSize())) {
            checkMdseCdFromParts(bean);
            return;
        }

        targetMdseCd = targetMdseCd + bean.getXtrnlSysSize();
        if (targetMdseCd.length() > 16) {
            bean.setErrCd(MSG_ID_NOT_EXIST_MDSE);
            return;
        }

        if (getMdseCd(bean, targetMdseCd)) {
            return;
        }
        bean.setErrCd(MSG_ID_NOT_EXIST_MDSE);
    }

    private static boolean getMdseCd(NPXC001001ConvertPartsMdseCdBean bean, String targetMdseCd) {
        String rtnMdseCd = selectMdseFindByKey(bean.getGlblCmpyCd(), targetMdseCd);
        if (ZYPCommonFunc.hasValue(rtnMdseCd)) {
            bean.setMdseCd(rtnMdseCd);
            return true;
        }
        return false;
    }

    private static String selectMdseFindByKey(String glblCmpyCd, String mdseCd) {
        MDSETMsg inMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, mdseCd);

        MDSETMsg result = (MDSETMsg) EZDTBLAccessor.findByKey(inMsg);
        String rtnMdseCd = null;
        if (result != null) {
            rtnMdseCd = result.mdseCd.getValue();
        }
        return rtnMdseCd;
    }

    private static void splitPartsAndSize(NPXC001001ConvertPartsMdseCdBean bean) {
        if (bean.getMdseCd().length() <= 12) {
            bean.setXtrnlSysPrtCd(bean.getMdseCd());
            bean.setXtrnlSysSize(DEFAULT_SIZE);
            return;
        }
        bean.setXtrnlSysPrtCd(bean.getMdseCd().substring(0, 12));
        bean.setXtrnlSysSize(formatSize(bean.getMdseCd().substring(12)));
    }

    private static String formatSize(String size) {
        String rtnSize = size;
        if (HYPHEN.equals(rtnSize.substring(0, 1))) {
            rtnSize = rtnSize.substring(1);
        }

        if (rtnSize.length() > 3) {
            rtnSize = rtnSize.substring(0, 3);
        }

        if (rtnSize.length() == 0) {
            rtnSize = DEFAULT_SIZE;
        }
        return rtnSize;
    }
}
