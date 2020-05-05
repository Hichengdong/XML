package jasp.sax;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXTest {
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		/**
		 * 1. 创建一个解析器工厂 2. 创建解析器 3. 执行parse方法 4. 自己创建一个类，继承DefaultHandler 5. 重写类里面的三个方法
		 */

		// 创建解析器工厂
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		// 创建解析器
		SAXParser saxParser = saxParserFactory.newSAXParser();
		// 执行parse方法
		saxParser.parse("src/p1.xml", new SAXHandler2());
	}
}

// 实现获取所有的name元素的值
class SAXHandler2 extends DefaultHandler {

	boolean flag = false;
	int index = 1;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// 判断qName是否是name元素
		if ("name".equals(qName)) {
			flag = true;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// 当flag是true的时候，表示解析到name元素
		if (flag == true && index == 2) {
			System.out.println(new String(ch, start, length));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// 把flag设置为false，表示name元素结束
		if ("name".equals(qName)) {
			flag = false;
			index++;
		}
	}
}

// 打印整个文档
class SAXHandler extends DefaultHandler {

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.print("<" + qName + ">");
		super.startElement(uri, localName, qName, attributes);
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		System.out.print(new String(ch, start, length));
		super.characters(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("</" + qName + ">");
		super.endElement(uri, localName, qName);
	}

}
