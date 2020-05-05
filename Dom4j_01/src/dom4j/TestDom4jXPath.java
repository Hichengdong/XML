package dom4j;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;

import utils.Dom4jUtils;

public class TestDom4jXPath {
	public static void main(String[] args) throws Exception {
		test2();
	}

	// 使用xpath实现：获取第一个p1下面的name值
	public static void test2() throws Exception{
		/*
		 * 1.得到document 2.直接使用selectSingleNode方法实现 - xpath: //p1[@id="ads"]/name
		 */

		// 获取document
		Document document = Dom4jUtils.getDocument("src/p1.xml");
		// 使用selectNodes("//p1[@id='ads']/name")方法得到name元素
		Node name = document.selectSingleNode("//p1[@id='ads']/name");
		System.out.println(name.getText());
	}

	// 查询xml种所有name元素的值
	public static void test1() throws Exception {
		/*
		 * 1.得到document 2.直接使用selectNodes("//name")方法得到所有的name元素
		 */

		// 获取document
		Document document = Dom4jUtils.getDocument("src/p1.xml");
		// 使用selectNodes("//name")方法得到所有的name元素
		List<Node> list = document.selectNodes("//name");
		for (Node node : list) {
			System.out.println(node.getText());
		}
	}
}
