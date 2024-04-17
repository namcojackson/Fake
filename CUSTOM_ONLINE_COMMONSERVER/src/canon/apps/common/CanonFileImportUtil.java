package canon.apps.common;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FilenameUtils;

public class CanonFileImportUtil {

	public static FileUploadStream getFileUploadStream(HttpServletRequest req)
			throws Exception {
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		FileUploadStreamImpl fustream = new FileUploadStreamImpl();
		if (isMultipart) {
			// https://commons.apache.org/proper/commons-fileupload/streaming.html
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload();

			FileItemIterator iter = upload.getItemIterator(req);
			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				String name = item.getFieldName();
				InputStream stream = item.openStream();
				/*
				 * Import: place all form field before file input.
				 * 
				 * https://www.w3.org/TR/html4/interact/forms.html#h-17.13.4.2 A
				 * "multipart/form-data" message contains a series of parts,
				 * each representing a successful control. The parts are sent to
				 * the processing agent in the same order the corresponding
				 * controls appear in the document stream.
				 */
				if (item.isFormField()) {
					fustream.setParameter(name, Streams.asString(stream));
				} else {
					fustream.setFileName(FilenameUtils.getName(item.getName()));
					fustream.setInputStream(stream);
					/*
					 * after Iterator.hasNext() has been invoked on the
					 * iterator, which created the FileItemStream.
					 */
					break;
				}
			}
		}
		return fustream;
	}

	public static interface FileUploadStream {
		public String getFileName();

		public InputStream getInputStream();

		public String getParameter(String name);
	}

	private static class FileUploadStreamImpl implements FileUploadStream {
		private String fileName = null;
		private InputStream inputStream = null;
		private Map<String, String> parameters = new HashMap<String, String>();

		public FileUploadStreamImpl() {
			super();
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public InputStream getInputStream() {
			return inputStream;
		}

		public void setInputStream(InputStream inputStream) {
			this.inputStream = inputStream;
		}

		public void setParameter(String name, String value) {
			this.parameters.put(name, value);
		}

		public String getParameter(String name) {
			return parameters.get(name);
		}

		@Override
		public String toString() {
			return "FileUploadStreamImpl [fileName=" + fileName
					+ ", parameters=" + parameters + "]";
		}

	}

}
