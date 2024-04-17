/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLE.NLEB007001;

import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.AP_INVOICE;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.ASSET_ADJUSTOMENT_OR_DISPOSAL;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.CONFIGURATION_CHANGE;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.CSV_EXT;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.CSV_HEADER_1;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.CSV_HEADER_2;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.CSV_HEADER_3;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.CSV_HEADER_4;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.DT_FORMAT_TS;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.DT_LEN_14;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.DT_LEN_8;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.EMPTY_STRING;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.EMSD_SHIPMENT;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.FILE;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.MAIL_CHARSET;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.MAIL_GROUP_ID_TO;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.MAIL_GROUP_KEY_FROM;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.MAIL_ITEM_BATCH_ID;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.MAIL_ITEM_BATCH_NM;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.MAIL_ITEM_CSV_FILE_NAME;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.MAIL_ITEM_DATE;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.MAIL_ITEM_ERR_MSG;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.MAIL_TEMPLATE_ID;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.MAIL_TITLE;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.NLEM0001E;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.PROC_MODE_01;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.PROC_MODE_02;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.PROC_MODE_11;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.PROC_MODE_21;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.PROC_MODE_31;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.PROC_MODE_41;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.PROC_MODE_51;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.PROC_MODE_61;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.PROC_PGM_ID;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.RENTAL_SHIPMENT;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.RENTAL_TO_SALES;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.RETURN;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.SERVICE_EXCHANGE_SHIPMENT;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.STR_CNM;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.STR_CRLF;

import java.math.BigDecimal;
import java.util.List;

import business.db.COA_PROJTMsg;
import business.db.DS_ASSET_MSTRTMsg;
import business.db.MDSETMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SVC_MACH_MSTRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_TP;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailAttachment;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 *<pre>
 *
 * Asset Creation Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/10   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class DSAssetStagingSendMail {

    /**
     * Global Company Code
     */
    private String glblCmpyCd;

    /**
     * Process Mode
     */
    private String mode;

    /**
     * CSV HEADER
     */
    private StringBuilder csvValueHeaderStr = new StringBuilder();

    /** CSV String */
    private StringBuilder csvValueStr = new StringBuilder();

    /**
     * Constructor
     * @param glblCmpyCd Global Company Code
     * @param mode Process Mode
     */
    public DSAssetStagingSendMail(String glblCmpyCd, String mode) {
        this.glblCmpyCd = glblCmpyCd;
        this.mode = mode;
    }

    /**
     * Mail send
     * @param beanList List<DSAssetStagingInfoBean>
     * @param msgList List<String>
     */
    public void sendMail(List<DSAssetStagingInfoBean> beanList, List<String> msgList) {

        // 1. Get From Address
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1(MAIL_GROUP_KEY_FROM);

        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            throw new S21AbendException(NLEM0001E, new String[] {"FROM mail-address.", (MAIL_GROUP_ID_FROM + "/" + MAIL_GROUP_KEY_FROM) });
        }

        // 2. Get To Address
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            throw new S21AbendException(NLEM0001E, new String[] {"TO mail-address.", MAIL_GROUP_ID_TO });
        }

        // 3. Get Mail Template.
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);
        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(NLEM0001E, new String[] {"Mailtemplate", MAIL_TEMPLATE_ID });
        }

        // 4. Create message for Body
        S21MailAddress fromAddress;
        fromAddress = addrFromList.get(0);

        String currentSystemTime = ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS);

        // CSV file attachment for Email.
        S21MailAttachment attachment = new S21MailAttachment(this.glblCmpyCd);
        createCSVData(beanList);
        String fileName = setFileName(beanList, currentSystemTime);
        String csvInfo = this.csvValueHeaderStr.append(this.csvValueStr).toString();
        byte[] attachData = csvInfo.getBytes();
        int attachId = attachment.setAttachment(attachData);
        attachment.setFileName(fileName);

        // 5. Create Subject and Body
        S21Mail mail = new S21Mail(glblCmpyCd);
        mail.setFromAddress(fromAddress);
        mail.setToAddressList(addrToList);
        mail.setAttachmentId(attachId);
        template.setTemplateParameter(MAIL_ITEM_BATCH_ID, PROC_PGM_ID);
        template.setTemplateParameter(MAIL_ITEM_BATCH_NM, MAIL_TITLE);
        template.setTemplateParameter(MAIL_ITEM_DATE, ZYPDateUtil.DateFormatter(currentSystemTime.substring(0, DT_LEN_8), "yyyyMMdd", "MM/dd/yyyy"));
        template.setTemplateParameter(MAIL_ITEM_CSV_FILE_NAME, fileName);
        template.setTemplateParameter(MAIL_ITEM_ERR_MSG, editMessage(msgList));

        // 6. Call Mail API
        mail.setMailTemplate(template);
        mail.setSubject(template.getSubject(), MAIL_CHARSET);
        mail.postMail();
    }

    private String setFileName(List<DSAssetStagingInfoBean> beanList, String currentSystemTs) {

        String csvfileName = "";
        if (PROC_MODE_01.equals(this.mode)) {
            csvfileName = RENTAL_SHIPMENT;
            creatHeader(CSV_HEADER_1);
        } else if (PROC_MODE_02.equals(this.mode)) {
            csvfileName = EMSD_SHIPMENT;
            creatHeader(CSV_HEADER_1);
        } else if (PROC_MODE_11.equals(this.mode)) {
            csvfileName = RENTAL_TO_SALES;
            creatHeader(CSV_HEADER_1);
        } else if (PROC_MODE_21.equals(this.mode)) {
            csvfileName = RETURN;
            if (ASSET_TP.EMSD_ASSET.equals(beanList.get(0).getAssetTpCd())) {
                creatHeader(CSV_HEADER_2);
            } else {
                creatHeader(CSV_HEADER_1);
            }
        } else if (PROC_MODE_31.equals(this.mode)) {
            csvfileName = CONFIGURATION_CHANGE;
            creatHeader(CSV_HEADER_1);
        } else if (PROC_MODE_41.equals(this.mode)) {
            csvfileName = SERVICE_EXCHANGE_SHIPMENT;
            creatHeader(CSV_HEADER_2);
        } else if (PROC_MODE_51.equals(this.mode)) {
            csvfileName = AP_INVOICE;
            creatHeader(CSV_HEADER_3);
        } else if (PROC_MODE_61.equals(this.mode)) {
            csvfileName = ASSET_ADJUSTOMENT_OR_DISPOSAL;
            creatHeader(CSV_HEADER_4);
        }

        StringBuffer sb = new StringBuffer();
        sb.append(csvfileName.replace(" ", ""));
        sb.append(FILE);
        sb.append(currentSystemTs.substring(0, DT_LEN_14));
        sb.append(CSV_EXT);
        return sb.toString();
    }

    private void creatHeader(String[] csvHeader) {

        for (String column : csvHeader) {
            this.csvValueHeaderStr.append(column);
            this.csvValueHeaderStr.append(STR_CNM);
        }
        this.csvValueHeaderStr.append(STR_CRLF);
    }

    private String createCSVData(List<DSAssetStagingInfoBean> beanList) {

        for (DSAssetStagingInfoBean bean : beanList) {
            getCsvData(bean);
            appendDbValueToCsv(bean.getDsAssetMstrPk());
            csvValueStr.append(STR_CNM);
            appendDbValueToCsv(bean.getSmmMdseCd());
            csvValueStr.append(STR_CNM);
            appendDbValueToCsv(bean.getMdseDescShortTxt());
            csvValueStr.append(STR_CNM);
            appendDbValueToCsv(bean.getSerNum());
            csvValueStr.append(STR_CNM);
            appendDbValueToCsv(bean.getCoaMdseTpNm());
            csvValueStr.append(STR_CNM);
            appendDbValueToCsv(bean.getServcConfigMstrPk());
            if (PROC_MODE_51.equals(this.mode)) {
                csvValueStr.append(STR_CNM);
                appendDbValueToCsv(bean.getVndNm());
                csvValueStr.append(STR_CNM);
                appendDbValueToCsv(bean.getTotAssetQty());
                csvValueStr.append(STR_CNM);
                appendDbValueToCsv(bean.getPoOrdNum());
            } else if (PROC_MODE_61.equals(this.mode)) {
                csvValueStr.append(STR_CNM);
                appendDbValueToCsv(bean.getLocNm());
                csvValueStr.append(STR_CNM);
                appendDbValueToCsv(1);
            } else {
                csvValueStr.append(STR_CNM);
                appendDbValueToCsv(bean.getLocNm());
                csvValueStr.append(STR_CNM);
                appendDbValueToCsv(1);
                csvValueStr.append(STR_CNM);
                appendDbValueToCsv(bean.getCpoOrdNum());
                csvValueStr.append(STR_CNM);
                appendDbValueToCsv(bean.getCpoDtlLineNum());
                csvValueStr.append(STR_CNM);
                appendDbValueToCsv(bean.getCpoDtlLineSubNum());
                csvValueStr.append(STR_CNM);
                appendDbValueToCsv(bean.getDsOrdPosnNum());
                if (PROC_MODE_41.equals(this.mode) || (PROC_MODE_21.equals(this.mode) && ASSET_TP.EMSD_ASSET.equals(bean.getAssetTpCd()))) {
                    csvValueStr.append(STR_CNM);
                    appendDbValueToCsv(bean.getRtlWhNm());
                }
            }
            csvValueStr.append(STR_CRLF);
        }
        return csvValueStr.toString();
    }

    private String editMessage(List<String> msgList) {

        StringBuilder msgTxt = new StringBuilder();
        for (String msg : msgList) {
            msgTxt.append(msg);
            msgTxt.append(STR_CRLF);
        }
        return msgTxt.toString();
    }

    /**
     * Append value to CSV string.
     */
    private void appendDbValueToCsv(Object objValue) {

        String csvValue;

        if (objValue == null) {
            csvValue = EMPTY_STRING;
        } else {
            csvValue = String.valueOf(objValue);
        }
        csvValueStr.append(quote(csvValue));
    }

    /**
     * Escape string including "," character.
     */
    private static String quote(String str) {
        str = nullToEmpty(str);
        if (str.contains(",")) {
            str = "\"" + str + "\"";
        }
        return str;
    }

    /**
     * <pre>
      * Change null value to empty string("").
      * </pre>
     * @return String
     */
    private static String nullToEmpty(String str) {
        if (str == null) {
            return EMPTY_STRING;
        }
        return str;
    }

    private void getCsvData(DSAssetStagingInfoBean bean) {

        // DS_ASSET_MSTR
        DS_ASSET_MSTRTMsg dsAssetMstr = NLEB007001Query.getDsAssetMstrPk(this.glblCmpyCd, setDsAssetMstrPk(bean));
        if (dsAssetMstr != null) {
            bean.setDsAssetMstrPk(dsAssetMstr.dsAssetMstrPk.getValue());
            bean.setPoOrdNum(dsAssetMstr.poOrdNum.getValue());
            bean.setVndNm(dsAssetMstr.vndNm.getValue());
            bean.setTotAssetQty(dsAssetMstr.totAssetQty.getValue());
        }

        // SVC_MACH_MSTR
        SVC_MACH_MSTRTMsg svcMachMstrInfo = NLEB007001Query.getSvcMachMstr(this.glblCmpyCd, dsAssetMstr.svcMachMstrPk.getValue());
        if (svcMachMstrInfo != null) {
            bean.setSmmMdseCd(svcMachMstrInfo.mdseCd.getValue());
            bean.setSerNum(svcMachMstrInfo.serNum.getValue());
        }

        // MDSE
        MDSETMsg mdseTMsg = NLEB007001Query.getMdseTMsg(this.glblCmpyCd, bean.getSmmMdseCd());
        if (mdseTMsg != null) {
            bean.setMdseDescShortTxt(mdseTMsg.mdseDescShortTxt.getValue());
            bean.setMdseTpCd(mdseTMsg.coaMdseTpCd.getValue());
        }

        // COA_PROJTMsg
        COA_PROJTMsg coaProj = NLEB007001Query.getCoaProj(this.glblCmpyCd, bean.getMdseTpCd());
        if (coaProj != null) {
            bean.setCoaMdseTpNm(coaProj.coaProjDescTxt.getValue());
        }

        // SHIP_TO_CUST
        SHIP_TO_CUSTTMsg shipToCustInfo = NLEB007001Query.getShipToCust(this.glblCmpyCd, bean.getShipToCustCd());
        if (shipToCustInfo != null) {
            bean.setLocNm(shipToCustInfo.locNm.getValue());
        }
    }

    private BigDecimal setDsAssetMstrPk(DSAssetStagingInfoBean bean) {

        if (bean.getSeApiRsDsAssetMstrPk() != null) {
            return bean.getSeApiRsDsAssetMstrPk();
        } else if (bean.getSeDsAssetMstrPk() != null) {
            return bean.getSeDsAssetMstrPk();
        } else if (bean.getActiveDsAssetMstrPk() != null) {
            return bean.getActiveDsAssetMstrPk();
        } else {
            return bean.getDsAssetMstrPk();
        }
    }
}
