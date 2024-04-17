package com.canon.cusa.s21.common.NWX.NWXC412001;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.usa.s21.integration.service.som.api.InsertNote;
import com.canon.usa.s21.integration.service.som.api.InsertNoteResponse;
import com.canon.usa.s21.integration.service.som.api.UpdateQuoteOrderNumber;
import com.canon.usa.s21.integration.service.som.api.UpdateQuoteOrderNumberResponse;
import com.canon.usa.s21.integration.service.som.api.UpdateQuoteStatus;
import com.canon.usa.s21.integration.service.som.api.UpdateQuoteStatusResponse;
import com.canon.usa.s21.integration.service.som.api.wrapper.SomApiProxy;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * EOPS web service
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/18/2018   Fujitsu         T.Aoi           Create          N/A
 * </pre>
 */
public final class NWXC412001EopsWebService {

    // ----------------------------------
    // EOPS quote status
    // ----------------------------------
    //private static final int REVIEW = 6;
    private static final int REVIEW = 4;

    //private static final int REJECT = 4;
    private static final int REJECT = 5;

    //private static final int APPROVE = 5;
    private static final int APPROVE = 6;

    // ----------------------------------
    // message ID
    // ----------------------------------
    private enum MESSAGE_ID {

        /**
         * EOPS web service has ended abnormally.
         */
        NWAM4120E,

        /**
         * Invalid quote id. @
         */
        NWAM4121E,

        /**
         * Invalid CPO number @
         */
        NWAM4122E,

        /**
         * User Id is not set.
         */
        NWAM4123E,
    }

    /**
     * review
     * @param ordSrcRefNum String - mandatory
     * @param userId String - mandatory
     * @param note String - optional
     * @return message id
     */
    public static String review(String ordSrcRefNum, String userId, String note) {

        return updateEopsStatus(ordSrcRefNum, REVIEW, userId, note);
    }

    /**
     * reject
     * @param ordSrcRefNum String - mandatory
     * @param userId String - mandatory
     * @param note String - optional
     * @return message id
     */
    public static String reject(String ordSrcRefNum, String userId, String note) {

        return updateEopsStatus(ordSrcRefNum, REJECT, userId, note);
    }

    /**
     * approve
     * @param ordSrcRefNum String - mandatory
     * @param userId String - mandatory
     * @param note String - optional
     * @return message id
     */
    public static String approve(String ordSrcRefNum, String userId, String note) {

        return updateEopsStatus(ordSrcRefNum, APPROVE, userId, note);
    }

    /**
     * order created
     * @param ordSrcRefNum String - mandatory
     * @param cpoOrdNum String - mandatory
     * @param userId String - mandatory
     * @param note String - optional
     * @return message id
     */
    public static String orderCreated(String ordSrcRefNum, String cpoOrdNum, String userId, String note) {

        try {

            Integer quoteId = getValidQuoteId(ordSrcRefNum);

            if (quoteId == null) {

                return MESSAGE_ID.NWAM4121E.name();
            }

            Integer orderNumber = getValidOrderNumber(cpoOrdNum);

            if (orderNumber == null) {

                return MESSAGE_ID.NWAM4122E.name();
            }

            // for debug
            S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();
            if (userProfSvc != null) {
                if (S21StringUtil.isEquals(ZYPCodeDataUtil.getVarCharConstValue("SKIP_EOPS_WEB_SERVICE", userProfSvc.getGlobalCompanyCode()), ZYPConstant.FLG_ON_Y)) {

                    return "";
                }
            }

            SomApiProxy proxy = new SomApiProxy();

            UpdateQuoteOrderNumberResponse response2 = updateQuoteOrderNumber(proxy, quoteId, orderNumber);
            System.out.println("Update Quote Order Number Response: " + response2.getUpdateQuoteOrderNumberResult());

            String statusResult = response2.getUpdateQuoteOrderNumberResult();
            if (statusResult != null && statusResult.startsWith("E:")) {
                throw new Throwable(statusResult);
            }

        } catch (Throwable e) {

            e.printStackTrace();
            return MESSAGE_ID.NWAM4120E.name();
        }

        return "";
    }

    private static String updateEopsStatus(String ordSrcRefNum, int statusId, String userId, String note) {

        try {

            Integer quoteId = getValidQuoteId(ordSrcRefNum);

            if (quoteId == null) {

                return MESSAGE_ID.NWAM4121E.name();
            }

            // for debug
            S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();
            if (userProfSvc != null) {
                if (S21StringUtil.isEquals(ZYPCodeDataUtil.getVarCharConstValue("SKIP_EOPS_WEB_SERVICE", userProfSvc.getGlobalCompanyCode()), ZYPConstant.FLG_ON_Y)) {

                    return "";
                }
            }

            SomApiProxy proxy = new SomApiProxy();

            UpdateQuoteStatusResponse response = updateQuoteStatus(proxy, quoteId, statusId, userId, note);
            System.out.println("Update Quote Status Response: " + response.getUpdateQuoteStatusResult());

            String statusResult = response.getUpdateQuoteStatusResult();
            if (statusResult != null && statusResult.startsWith("E:")) {
                throw new Throwable(statusResult);
            }
            
        } catch (Throwable e) {

            e.printStackTrace();
            return MESSAGE_ID.NWAM4120E.name();
        }

        return "";
    }

    private static UpdateQuoteStatusResponse updateQuoteStatus(SomApiProxy proxy, int quoteId, int statusId, String userId, String note) throws Throwable {

        UpdateQuoteStatus request = new UpdateQuoteStatus();
        request.setQuoteid(quoteId);
        request.setStatusId(statusId);
        request.setUserId(userId);

        if (S21StringUtil.isEmpty(note)) {

            request.setNote(" ");
        } else {

            request.setNote(note);
        }

        UpdateQuoteStatusResponse response = null;

        response = proxy.updateQuoteStatus(request);

        return response;
    }

    private static UpdateQuoteOrderNumberResponse updateQuoteOrderNumber(SomApiProxy proxy, int quateId, int orderNumber) throws Throwable {

        UpdateQuoteOrderNumber request = new UpdateQuoteOrderNumber();
        request.setQuoteid(quateId);
        request.setOracleOrderNumber(orderNumber);

        UpdateQuoteOrderNumberResponse response = null;
        response = proxy.updateQuoteOrderNumber(request);

        return response;
    }

    @SuppressWarnings("unused")
    private static InsertNoteResponse insertNote(SomApiProxy proxy, int quoteId, String userId, String note) throws Throwable {

        InsertNote request = new InsertNote();
        request.setQuoteid(quoteId);
        request.setUserId(userId);
        request.setNote(note);

        InsertNoteResponse response = null;

        response = proxy.insertNote(request);

        return response;
    }

    private static Integer getValidQuoteId(String ordSrcRefNum) {

        if (S21StringUtil.isEmpty(ordSrcRefNum)) {

            return null;
        }

        String quoteId = ordSrcRefNum.replaceAll("[A-Z]|[a-z]", "");
        if (S21StringUtil.isEmpty(quoteId)) {

            return null;
        }

        String regex = "[0-9]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(quoteId);

        if (matcher.find()) {

            return Integer.valueOf(quoteId);
        } else {

            return null;
        }
    }

    private static Integer getValidOrderNumber(String cpoOrdNum) {

        if (S21StringUtil.isEmpty(cpoOrdNum)) {

            return null;
        }

        String regex = "[0-9]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cpoOrdNum);

        if (matcher.find()) {

            return Integer.valueOf(cpoOrdNum);
        } else {

            return null;
        }
    }
}
