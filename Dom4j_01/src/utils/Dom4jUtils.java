package utils;

import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jUtils {
	// 返回document
	public static Document getDocument(String path) throws DocumentException {
		// 创建解析器
		SAXReader reader = new SAXReader();
		// 得到document
		Document document = reader.read(path);
		return document;
	}

	// 回写xml
	public static void overridexml(Document document, String path) throws IOException {
		OutputFormat format = OutputFormat.createPrettyPrint(); // 可以有缩进的效果
		XMLWriter xmlrWriter = new XMLWriter(new FileOutputStream(path), format);
		xmlrWriter.write(document);
		xmlrWriter.close();
	}
}
