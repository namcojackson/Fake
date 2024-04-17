<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tbody>
        <tr>
            <td>
                <div id="spacer1" style="height:10"/>
            </td>
        </tr>
        <tr>
            <td style="width:10"></td>
            <td>
                <fieldset>
                    <!--<legend>Workflow Administration</legend>-->
                    <table align="left">
                        <tr>
                            <td class="stab" style="width:200">
                                <a href='<c:url value="/wfadmin/definition/ProcessDefinitionManagePage/showPage.sweb"/>'>Process Definition Management</a>
                            </td>
                            <td class="stab">
                                Activate, De-Activate a Process Definition
                            </td>
                        </tr>
                        <tr>
                            <td class="stab" style="width:200">
                                <a href='<c:url value="/wfadmin/definition/ProcessCodeManagePage/showPage.sweb"/>'>Process Code Management</a>
                            </td>
                            <td class="stab">
                                Stop, Resume a Process Code
                            </td>
                        </tr>                         
                        <tr>
                            <td class="stab" style="width:200">
                                <a href='<c:url value="/wfadmin/instance/ProcessInstanceSearchPage/showPage.sweb?needSearch=false"/>'>Process Instance Management </a>
                            </td>
                            <td class="stab">
                                Search, Edit, Suspend, Resume, Abort Process Instances
                            </td>
                        </tr>

                        <tr>
                            <td class="stab" style="width:200">
                                <a href='<c:url value="/wfadmin/instance/TaskInstanceSearchPage/showPage.sweb"/>'>Task Instance Management</a>
                            </td>
                            <td class="stab">
                                Search, Edit Task Instances
                            </td>
                        </tr>
                        
                        <tr>
                            <td class="stab" style="width:200">
                                <a href='<c:url value="/wfadmin/instance/JonInstanceManagePage/showPage.sweb"/>'>Job Instance Management </a>
                            </td>
                            <td class="stab">
                                Search Jobs/Archived Jobs, Rerun, Change Due date.
                            </td>
                        </tr>
                        <tr>
                            <td class="stab" style="width:200">
                                <a href='<c:url value="/wfadmin/jobx/JobDispatcherManagePage/showPage.sweb"/>'> Job Watch Management</a>
                            </td>
                            <td class="stab">
                                Monitor Job Watcher.  
                            </td>
                        </tr>
                    </table>
                </fieldset>
            </td>
            <td style="width:10"></td>
        </tr>
    </tbody>
</table>

