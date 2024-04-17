/**
 * <Pre>Copyright (c) 2016-2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC226001;

import java.math.BigDecimal;

import parts.common.EZDTMsg;
import business.parts.NWZC226001_xxMsgPrmListPMsg;

import com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant.MSG_ID;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * DsImptOrdErrBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/17/2016   Fujitsu         M.Hara          Create          N/A
 * 01/17/2019   Fujitsu         K.Kato          Update          S21_NA#29942
 * </pre>
 */
public class DsImptOrdErrBean extends NWZC226001_xxMsgPrmListPMsg {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // *************************************************************************
    // Extension Field
    // *************************************************************************
    final private Object owner;

    private String[] msgParam;

    public DsImptOrdErrBean(Object owner, MSG_ID msgId) {

        this(owner, msgId.toString(), new String[] {});
    }

    public DsImptOrdErrBean(Object owner, MSG_ID msgId, String msgParam) {

        this(owner, msgId.toString(), new String[] {msgParam });
    }

    public DsImptOrdErrBean(Object owner, MSG_ID msgId, String[] msgParam) {

        this(owner, msgId.toString(), msgParam);
    }

    public DsImptOrdErrBean(Object owner, String msgId, String[] msgParam) {

        this.owner = owner;
        this.msgParam = msgParam;

        String ordSrcRefNum = null;
        String dsOrdPosnNum = null;
        String ordSrcRefLineNum = null;
        BigDecimal dsImptSvcLineNum = null;
        String bllgMtrLbCd = null;
        if (owner instanceof ImptHdrBean) {

            ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, ((ImptHdrBean) owner).getDsImptOrdPk());

            ordSrcRefNum = ((ImptHdrBean) owner).getOrdSrcRefNum();
        } else if (owner instanceof DsImptOrdConfigBean) {

            ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, ((DsImptOrdConfigBean) owner).dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdConfigPk, ((DsImptOrdConfigBean) owner).dsImptOrdConfigPk);

            ordSrcRefNum = ((DsImptOrdConfigBean) owner).imptHdrBean.getOrdSrcRefNum();
            dsOrdPosnNum = ((DsImptOrdConfigBean) owner).dsOrdPosnNum.getValue();
        } else if (owner instanceof DsImptLineBean) {

            ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, ((DsImptLineBean) owner).dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdConfigPk, ((DsImptLineBean) owner).dsImptOrdConfigPk);
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdDtlPk, ((DsImptLineBean) owner).dsImptOrdDtlPk);

            ordSrcRefNum = ((DsImptLineBean) owner).imptHdrBean.getOrdSrcRefNum();
            dsOrdPosnNum = ((DsImptLineBean) owner).dsOrdPosnNum.getValue();
            ordSrcRefLineNum = ((DsImptLineBean) owner).ordSrcRefLineNum.getValue();
        } else if (owner instanceof DsImptRtnLineBean) {

            ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, ((DsImptRtnLineBean) owner).dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdConfigPk, ((DsImptRtnLineBean) owner).dsImptOrdConfigPk);
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdRtrnDtlPk, ((DsImptRtnLineBean) owner).dsImptOrdRtrnDtlPk);

            ordSrcRefNum = ((DsImptRtnLineBean) owner).imptHdrBean.getOrdSrcRefNum();
            dsOrdPosnNum = ((DsImptRtnLineBean) owner).dsOrdPosnNum.getValue();
            ordSrcRefLineNum = ((DsImptRtnLineBean) owner).ordSrcRefLineNum.getValue();
        } else if (owner instanceof DsImptSvcDtlBean) {

            ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, ((DsImptSvcDtlBean) owner).dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(this.dsImptSvcDtlPk, ((DsImptSvcDtlBean) owner).dsImptSvcDtlPk);

            ordSrcRefNum = ((DsImptSvcDtlBean) owner).imptHdrBean.getOrdSrcRefNum();
            dsImptSvcLineNum = ((DsImptSvcDtlBean) owner).dsImptSvcLineNum.getValue();
        }

        ZYPEZDItemValueSetter.setValue(this.ordSrcRefNum, ordSrcRefNum);
        ZYPEZDItemValueSetter.setValue(this.dsOrdPosnNum, dsOrdPosnNum);
        ZYPEZDItemValueSetter.setValue(this.ordSrcRefLineNum, ordSrcRefLineNum);
        ZYPEZDItemValueSetter.setValue(this.dsImptSvcLineNum, dsImptSvcLineNum);
        ZYPEZDItemValueSetter.setValue(this.bllgMtrLbCd, bllgMtrLbCd);

        if (!msgId.isEmpty()) {

            ZYPEZDItemValueSetter.setValue(this.dsImptOrdErrMsgId, msgId);
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdErrTxt, S21MessageFunc.clspGetMessage(msgId, msgParam));
        }
    }

    public static DsImptOrdErrBean createMandatoryError(Object owner, String itemNm) {

        return new DsImptOrdErrBean(owner, MSG_ID.NWAM0298E, itemNm);
    }

    public static DsImptOrdErrBean createTableExistError(Object owner, String tableNm) {

        return new DsImptOrdErrBean(owner, MSG_ID.NWAM0809E, new String[] {tableNm });
    }

    public static DsImptOrdErrBean createTableExistError(Object owner, String tableNm, Object pk) {

        return new DsImptOrdErrBean(owner, MSG_ID.NWAM0525E, new String[] {tableNm, pk.toString() });
    }

    public static DsImptOrdErrBean createTableInsertError(Object owner, String tableNm) {

        return new DsImptOrdErrBean(owner, MSG_ID.NWAM0728E, new String[] {tableNm });
    }

    public static DsImptOrdErrBean createTableUpdateError(Object owner, String tableNm) {

        return new DsImptOrdErrBean(owner, MSG_ID.NWAM0729E, new String[] {tableNm });
    }

    // 2019/01/17 S21_NA#29942 Add Start
    public static DsImptOrdErrBean createTableUomNotExistError(Object owner, String uomCd, String mdseCd) {

        return new DsImptOrdErrBean(owner, MSG_ID.NWAM0968E, new String[] {uomCd, mdseCd });
    }
    // 2019/01/17 S21_NA#29942 Add End

    public static <T extends EZDTMsg> DsImptOrdErrBean createMasterExistError(Class<T> tMsg, Object owner, Object val) {

        DsImptOrdErrBean errBean = null;
        String tableNm;
        try {
            T msg = tMsg.newInstance();
            tableNm = msg.getTableName();

            errBean = new DsImptOrdErrBean(owner, MSG_ID.NWAM0403E, new String[] {tableNm, val.toString() });
        } catch (Exception e) {
        }

        return errBean;
    }

    public String toMailString() {
        String level, errPos, errMsg;

        level = "";
        errPos = "";
        errMsg = "";
        DsImptOrdConfigBean configBean;
        DsImptLineBean lineBean;
        DsImptRtnLineBean rtnBean;
        DsImptSvcDtlBean svcBean;
        if (this.owner instanceof DsImptSvcDtlBean) {

            level = "Service";
            svcBean = (DsImptSvcDtlBean) this.owner;
            errPos = String.format("Service Line# : %s", svcBean.dsImptSvcLineNum.getValue());
        } else if (this.owner instanceof DsImptOrdConfigBean) {

            level = "Config";
            configBean = (DsImptOrdConfigBean) this.owner;
            errPos = String.format("Config# : %.0f", configBean.svcConfigMstrPk.getValue());
        } else if (this.owner instanceof DsImptLineBean) {

            level = "Line";
            lineBean = (DsImptLineBean) this.owner;
            configBean = lineBean.dsImptOrdConfigBean;
            errPos = String.format("Config# : %.0f, line#%s.%s.%s", configBean.svcConfigMstrPk.getValue(), configBean.dsOrdPosnNum.getValue(), lineBean.ordSrcRefLineNum.getValue(), lineBean.ordSrcRefLineSubNum);
        } else if (this.owner instanceof DsImptRtnLineBean) {

            level = "RMA Line";
            rtnBean = (DsImptRtnLineBean) this.owner;
            configBean = rtnBean.dsImptOrdConfigBean;

            errPos = String.format("Config# : %.0f, line#%s.%s.%s", configBean.svcConfigMstrPk.getValue(), configBean.dsOrdPosnNum.getValue(), rtnBean.ordSrcRefLineNum.getValue(), rtnBean.ordSrcRefLineSubNum);
        } else {

            level = "Header";
        }

        errMsg = this.dsImptOrdErrTxt.getValue();

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("\tlevel : %s\n", level));
        if (ZYPCommonFunc.hasValue(errPos)) {

            sb.append(String.format("\t%s\n", errPos));
        }
        if (ZYPCommonFunc.hasValue(errMsg)) {

            sb.append(String.format("\t%s\n", errMsg));
        }

        return sb.toString();
    }

    /**
     * @return msgParam
     */
    public String[] getMsgParam() {

        return msgParam;
    }
}
