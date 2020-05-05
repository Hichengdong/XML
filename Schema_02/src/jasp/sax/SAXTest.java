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
		 * 1. ����һ������������ 2. ���������� 3. ִ��parse���� 4. �Լ�����һ���࣬�̳�DefaultHandler 5. ��д���������������
		 */

		// ��������������
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		// ����������
		SAXParser saxParser = saxParserFactory.newSAXParser();
		// ִ��parse����
		saxParser.parse("src/p1.xml", new SAXHandler2());
	}
}

// ʵ�ֻ�ȡ���е�nameԪ�ص�ֵ
class SAXHandler2 extends DefaultHandler {

	boolean flag = false;
	int index = 1;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// �ж�qName�Ƿ���nameԪ��
		if ("name".equals(qName)) {
			flag = true;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// ��flag��true��ʱ�򣬱�ʾ������nameԪ��
		if (flag == true && index == 2) {
			System.out.println(new String(ch, start, length));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// ��flag����Ϊfalse����ʾnameԪ�ؽ���
		if ("name".equals(qName)) {
			flag = false;
			index++;
		}
	}
}

// ��ӡ�����ĵ�
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
