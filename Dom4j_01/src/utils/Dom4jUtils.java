package utils;

import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jUtils {
	// ����document
	public static Document getDocument(String path) throws DocumentException {
		// ����������
		SAXReader reader = new SAXReader();
		// �õ�document
		Document document = reader.read(path);
		return document;
	}

	// ��дxml
	public static void overridexml(Document document, String path) throws IOException {
		OutputFormat format = OutputFormat.createPrettyPrint(); // ������������Ч��
		XMLWriter xmlrWriter = new XMLWriter(new FileOutputStream(path), format);
		xmlrWriter.write(document);
		xmlrWriter.close();
	}
}
