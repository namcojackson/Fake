/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NPX.NPXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.ArrayList;
import java.util.List;


import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;

import business.db.MDSETMsg;
import business.db.MDSETMsgArray;
import business.db.ORD_TAKE_MDSETMsg;


/**
 * <pre>
 * CanBe8DigitMdse
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/03/2014   Hitachi         T.Kawazu        Create          CSA-339
 *</pre>
 */
public class NPXC001001CanBe8DigitMdse {

    /**
     * Mode MODE_CHECK
     */
    public static final String MODE_CHECK = "1";

    /**
     * Mode MODE_GET
     */
    public static final String MODE_GET = "2";

    /**
     * Length 8
     */
    public static final int LENGTH_8 = 8;

    /**
     * Global Company Code is required.
     */
    public static final String NPZM0001E = "NPZM0001E";

    /**
     * Merchandise Code is required.
     */
    public static final String NPZM0020E = "NPZM0020E";

    /**
     * "Mode" must be entered.
     */
    public static final String NPZM0093E = "NPZM0093E";

    /**
     * Mode is invalid.
     */
    public static final String NPZM0101E = "NPZM0101E";

    /**
     * 
     * @param bean NPXC001001CanBe8DigitMdseBean
     * 
     */
    public static void canBe8DigitMdse(NPXC001001CanBe8DigitMdseBean bean) {

        checkParam(bean);

        if (bean.getErrList() != null) {
            return;
        }
        if (isCheckMode(bean)) {
            check8DigitMdse(bean);
        }

        if (isGetMode(bean)) {
            getMdseList(bean);
        }
}

    private static void getMdseList(NPXC001001CanBe8DigitMdseBean bean) {

        if (LENGTH_8 > bean.getMdseCd().length()) {
            return;
        }
        MDSETMsgArray mdseTMsgArray = getMdseTMsgArray(bean);

        for (int i = 0; i < mdseTMsgArray.length(); i++) {
            addMdseList(bean, mdseTMsgArray.no(i).mdseCd.getValue());
        }
    }

    private static MDSETMsgArray getMdseTMsgArray(NPXC001001CanBe8DigitMdseBean bean) {
        MDSETMsg inMsg = new MDSETMsg();
        inMsg.setSQLID("079");
        inMsg.setConditionValue("glblCmpyCd01", bean.getGlblCmpyCd());
        inMsg.setConditionValue("mdseCd01", to8Digit(bean.getMdseCd()) + "%");
        inMsg.setConditionValue("rgtnStsCd01A", RGTN_STS.READY_FOR_ORDER_TAKING);
        inMsg.setConditionValue("rgtnStsCd01B", RGTN_STS.READY_FOR_CUSTOMS_CLEARANCE_AND_RECEIVING);

        return (MDSETMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    private static String to8Digit(String mdseCd) {
        if (LENGTH_8 < mdseCd.length()) {
            mdseCd = mdseCd.substring(0, LENGTH_8);
        }
        return mdseCd;
    }

    private static void check8DigitMdse(NPXC001001CanBe8DigitMdseBean bean) {
        ORD_TAKE_MDSETMsg ordTakeMdse = getOrdTakeMdse(bean);
        if (ordTakeMdse == null) {
            bean.setCanBe8DigitMdseFlg(false);
            return;
        }
        bean.setCanBe8DigitMdseFlg(true);
        addMdseList(bean, ordTakeMdse.mdseCd.getValue());
    }

    private static void checkParam(NPXC001001CanBe8DigitMdseBean bean) {
        if (bean.getGlblCmpyCd() == null) {
            addErrList(bean, NPZM0001E);
        }
        if (bean.getMdseCd() == null) {
            addErrList(bean, NPZM0020E);
        }
        if (bean.getMode() == null) {
            addErrList(bean, NPZM0093E);
        }
        if (!isCheckMode(bean) && !isGetMode(bean)) {
            addErrList(bean, NPZM0101E);
        }
    }

    private static boolean isCheckMode(NPXC001001CanBe8DigitMdseBean bean) {
        return MODE_CHECK.equals(bean.getMode());
    }

    private static boolean isGetMode(NPXC001001CanBe8DigitMdseBean bean) {
        return MODE_GET.equals(bean.getMode());
    }

    private static void addErrList(NPXC001001CanBe8DigitMdseBean bean, String errCd) {
        List<String> errList = bean.getErrList();
        if (errList == null) {
            errList = new ArrayList<String>();
        }
        errList.add(errCd);
        bean.setErrList(errList);
    }

    private static void addMdseList(NPXC001001CanBe8DigitMdseBean bean, String value) {
        List<String> mdseCdList = bean.getMdseCdList();
        if (mdseCdList == null) {
            mdseCdList = new ArrayList<String>();
        }
        mdseCdList.add(value);
        bean.setMdseCdList(mdseCdList);
    }

    private static ORD_TAKE_MDSETMsg getOrdTakeMdse(NPXC001001CanBe8DigitMdseBean bean) {
        ORD_TAKE_MDSETMsg inMsg = new ORD_TAKE_MDSETMsg();
        setValue(inMsg.glblCmpyCd, bean.getGlblCmpyCd());
        setValue(inMsg.ordTakeMdseCd, to8Digit(bean.getMdseCd()));

        return (ORD_TAKE_MDSETMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }


}
