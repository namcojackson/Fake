package com.canon.cusa.s21.batch.NWA.NWAB221001;

import parts.common.EZDTMsg;
import business.db.DS_IMPT_ORD_ERRTMsg;

import com.canon.cusa.s21.batch.NWA.NWAB221001.constant.NWAB221001Constant;
import com.canon.cusa.s21.batch.NWA.NWAB221001.constant.NWAB221001Constant.MSG_ID;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * DsImptOrdErrBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/17/2016   Fujitsu         M.Hara          Create          N/A
 * </pre>
 */
public class DsImptOrdErrBean extends DS_IMPT_ORD_ERRTMsg {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // *************************************************************************
    // Extention Field
    // *************************************************************************
    final private Object owner;

    // private boolean canContinue;

    public DsImptOrdErrBean(Object owner) {
        this(owner, "", new String[] {});
    }

    public DsImptOrdErrBean(Object owner, S21ApiMessage apiMsg) {
        this(owner, apiMsg.getXxMsgid(), (String[]) apiMsg.getXxMsgPrmList().toArray());
    }

    public DsImptOrdErrBean(Object owner, String msgId) {
        this(owner, msgId, new String[] {});
    }

    public DsImptOrdErrBean(Object owner, NWAB221001Constant.MSG_ID msgId) {
        this(owner, msgId.toString(), new String[] {});
    }

    public DsImptOrdErrBean(Object owner, String msgId, String msgParam) {
        this(owner, msgId, new String[] {msgParam });
    }

    public DsImptOrdErrBean(Object owner, NWAB221001Constant.MSG_ID msgId, String msgParam) {
        this(owner, msgId.toString(), new String[] {msgParam });
    }

    public DsImptOrdErrBean(Object owner, NWAB221001Constant.MSG_ID msgId, String[] msgParam) {
        this(owner, msgId.toString(), msgParam);
    }

    public DsImptOrdErrBean(Object owner, String msgId, String[] msgParam) {
        this.owner = owner;
        // this.canContinue = false;

        if (owner instanceof ImptHdrBean) {

            ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, ((ImptHdrBean) owner).getDsImptOrdPk());
        } else if (owner instanceof DsImptOrdConfigBean) {

            ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, ((DsImptOrdConfigBean) owner).dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdConfigPk, ((DsImptOrdConfigBean) owner).dsImptOrdConfigPk);
        } else if (owner instanceof DsImptLineBean) {

            ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, ((DsImptLineBean) owner).dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdConfigPk, ((DsImptLineBean) owner).dsImptOrdConfigPk);
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdDtlPk, ((DsImptLineBean) owner).dsImptOrdDtlPk);
        } else if (owner instanceof DsImptRtnLineBean) {

            ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, ((DsImptRtnLineBean) owner).dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdConfigPk, ((DsImptRtnLineBean) owner).dsImptOrdConfigPk);
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdRtrnDtlPk, ((DsImptRtnLineBean) owner).dsImptOrdRtrnDtlPk);
        }

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

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append("\n");
        sb.append(this.dsImptOrdErrMsgId.getValue());
        sb.append(" : ");
        sb.append(this.dsImptOrdErrTxt.getValue());

        return sb.toString();
    }

    public String toMailString() {
        String level, errPos, errMsg;

        level = "";
        errPos = "";
        errMsg = "";
        DsImptOrdConfigBean configBean;
        DsImptLineBean lineBean;
        DsImptRtnLineBean rtnBean;

        if (this.owner instanceof DsImptOrdConfigBean) {

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

    public Object getOwner() {
        return owner;
    }

    // public boolean isCanContinue() {
    // return canContinue;
    // }
    //
    // public void setCanContinue(boolean canContinue) {
    // this.canContinue = canContinue;
    // }

}
