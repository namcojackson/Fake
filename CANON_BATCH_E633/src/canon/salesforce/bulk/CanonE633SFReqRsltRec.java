package canon.salesforce.bulk;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.List;

import oracle.sql.ArrayDescriptor;

public class CanonE633SFReqRsltRec implements java.sql.SQLData, java.io.Serializable {

        private String status;
        private String sfdcid;
        private String message;
        private String instanceid;
        private String jobId;

        public CanonE633SFReqRsltRec() {
        }

        public CanonE633SFReqRsltRec(String status,
                String sfdcid,
                String message,
                String instanceid,
                String jobId) {
            this.status = status;
            this.sfdcid = sfdcid;
            this.message = message;
            this.instanceid = instanceid;
            this.jobId = jobId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSfdcid() {
            return sfdcid;
        }

        public void setSfdcid(String sfdcid) {
            this.sfdcid = sfdcid;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getInstanceid() {
            return instanceid;
        }

        public void setInstanceid(String instanceid) {
            this.instanceid = instanceid;
        }
        
        public String getJobId(){
        	return jobId;
        }
        
        public void setJobId(String jobId){
        	this.jobId = jobId;
        }

        public String getSQLTypeName() throws SQLException {
            return "CANON_E633_SF_REQ_RSLT_REC";
        }

        public void readSQL(SQLInput stream, String typeName) throws SQLException {
            status = stream.readString();
            sfdcid = stream.readString();
            message = stream.readString();
            instanceid = stream.readString();
            jobId = stream.readString();

        }

        public void writeSQL(SQLOutput stream) throws SQLException {
            stream.writeString(status);
            stream.writeString(sfdcid);
            stream.writeString(message);
            stream.writeString(instanceid);
            stream.writeString(jobId);

        }
        
        public static oracle.sql.ARRAY canonE633SFReqRsltTblListToArray(List<CanonE633SFReqRsltRec> list, Connection connection) throws SQLException {
   		 System.out.println("connection : " +connection);
   		 System.out.println("connection : " +connection.getMetaData().getConnection());
            ArrayDescriptor arraydesc = ArrayDescriptor.createDescriptor("CANON_E633_SF_REQ_RSLT_TBL", connection.getMetaData().getConnection());
            System.out.println("arraydesc : " +arraydesc);
            //ArrayDescriptor arraydesc = ArrayDescriptor.createDescriptor("CANON_E633_SF_REQ_RSLT_TBL", connection);
            CanonE633SFReqRsltRec[] elements = (CanonE633SFReqRsltRec[]) list.toArray(new CanonE633SFReqRsltRec[0]);
            oracle.sql.ARRAY array = new oracle.sql.ARRAY(arraydesc, connection.getMetaData().getConnection(), elements);
            return array;
        }

        public String toString() {
            return "CanonE633SFReqRsltRec{" + "status=" + status + ", sfdcid=" + sfdcid + ", message=" + message + ", instanceid=" + instanceid + ", jobId=" + jobId +'}';
        }
    }
