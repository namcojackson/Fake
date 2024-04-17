<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>

<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../../../css/common.css" type="text/css">
    <link rel="stylesheet" href="../../../css/S21WfCommon.css" type="text/css">

    <script type="text/javascript" src="../../../js/common/common.js" charset="UTF-8"></script>
    <script type="text/javascript" src="../../../js/wfcomp/S21WfCommon.js"></script>
    <title>Workflow Admin Application</title>
    <script type="text/javascript">
        <!--
        function getUrlPrefix() {
            return "../../../";
        }
        //-->
    </script>
</head>


<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginheight="0" marginwidth="0" style="background:#eeeeff;">

<!--  * Display Header Area  -->
<div style="width:1010px;height:25; overflow:hidden;">
    <table border="0" cellspacing="0" cellpadding="2" class="cHeadCloth" width="1010px">
        <tr>
            <td class="cSystemName" style="width:80px;">
                &nbsp;&nbsp;WORKFLOW
            </td>
            <td class="cDispname" style="width:180px;">
                <a href='<c:url value="/wfadmin/menu/MenuPage/showPage.sweb"/>'><font color="white">WfAdmin Menu</font></a>
            </td>
            <td class="cDispid" style="width:80px;">
                <div id="pageNameArea">${SwebPage.pageId}</div>
            </td>
            <td class="cDispname" style="width:670px;">
                <div id="pageNameArea">${SwebPage.pageName}</div>
            </td>
        </tr>
    </table>
</div>

<!-- error msg starts -->
<jsp:include page="../../../jsp/wfadmin/_common/SwebDisplayGlobalMessage.jsp"/>
<!-- error msg ends -->