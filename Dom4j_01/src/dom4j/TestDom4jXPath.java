package dom4j;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;

import utils.Dom4jUtils;

public class TestDom4jXPath {
	public static void main(String[] args) throws Exception {
		test2();
	}

	// ʹ��xpathʵ�֣���ȡ��һ��p1�����nameֵ
	public static void test2() throws Exception{
		/*
		 * 1.�õ�document 2.ֱ��ʹ��selectSingleNode����ʵ�� - xpath: //p1[@id="ads"]/name
		 */

		// ��ȡdocument
		Document document = Dom4jUtils.getDocument("src/p1.xml");
		// ʹ��selectNodes("//p1[@id='ads']/name")�����õ�nameԪ��
		Node name = document.selectSingleNode("//p1[@id='ads']/name");
		System.out.println(name.getText());
	}

	// ��ѯxml������nameԪ�ص�ֵ
	public static void test1() throws Exception {
		/*
		 * 1.�õ�document 2.ֱ��ʹ��selectNodes("//name")�����õ����е�nameԪ��
		 */

		// ��ȡdocument
		Document document = Dom4jUtils.getDocument("src/p1.xml");
		// ʹ��selectNodes("//name")�����õ����е�nameԪ��
		List<Node> list = document.selectNodes("//name");
		for (Node node : list) {
			System.out.println(node.getText());
		}
	}
}
