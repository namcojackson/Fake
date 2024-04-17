<%@ page import="com.canon.cusa.s21.framework.common.S21StringUtil" %>
<%@ page import="com.canon.cusa.s21.framework.workflow.common.sweb.core.WfSwebActionErrors" %>
<%@ page import="com.canon.cusa.s21.framework.workflow.common.sweb.core.WfSwebActionMessage" %>
<%@ page import="com.canon.cusa.s21.framework.workflow.common.sweb.core.WfSwebActionMessages" %>
<%@ page import="com.canon.cusa.s21.framework.workflow.common.sweb.page.WfSwebPage" %>
<%@ page import="java.text.MessageFormat" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Properties" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<span style="color:#ff0000"><c:out value="${SwebPage.currentErrorMessage}"/></span>--%>
<%
    // get Page
    WfSwebPage swebPage = (WfSwebPage) request.getAttribute(WfSwebPage.DEST_PAGE_ATTR_NAME); // name is SwebPage

    // parse parameter
    String msgKey;
    String msgType = request.getParameter("msgType") + "";
    if ((msgType.length() < 1) || msgType.equals("undefined") || msgType.equals("null") || msgType.equalsIgnoreCase("ERROR")) {
        msgKey = WfSwebActionErrors.GLOBAL_ERROR;
    } else {
        msgKey = WfSwebActionMessages.GLOBAL_MESSAGE;
    }
    WfSwebActionMessages messages = (WfSwebActionMessages) session.getAttribute(msgKey);

    // construct error message
    if (messages != null) {
        Properties appProperties = swebPage.getAppProperties();
        String header = appProperties.getProperty("message.header");
        out.println(header);

        String prefix = appProperties.getProperty("message.prefix");
        String suffix = appProperties.getProperty("message.suffix");
        Iterator it = messages.get();
        while (it.hasNext()) {
            WfSwebActionMessage msg = (WfSwebActionMessage) it.next();
            // retrieve message value
            String key = msg.getKey();
            String propStr = (String) appProperties.get(key);
            if (S21StringUtil.isEmpty(propStr)) {
                propStr = "message " + key + " not found";
            }
            Object[] values = msg.getValues();
            MessageFormat formater = new MessageFormat(propStr);
            String formatedMsg = formater.format(values);
            out.println(prefix + formatedMsg + suffix);
        }
        
        String footer = appProperties.getProperty("message.footer");
        out.println(footer);
    }

    // cleanup sesion
    session.removeAttribute(WfSwebActionErrors.GLOBAL_ERROR);
    session.removeAttribute(WfSwebActionErrors.GLOBAL_MESSAGE);
%>
