package jaxp;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class TestJaxp {
	public static void main(String[] args)
			throws ParserConfigurationException, SAXException, IOException, TransformerException {
		listElement();
	}

	// 遍历节点，把所有元素的名称都打印出来
	public static void listElement() throws ParserConfigurationException, SAXException, IOException {
		/**
		 * 操作步骤 1.创建解析器工厂 2.根据解析器工厂创建解析器 3.解析xml返回document ==递归== 4.得到根节点元素 5.得到根节点的子节点
		 * 6.得到根节点子节点的子节点
		 */

		// 创建解析器工厂
		DocumentBuilderFactory builderFactoryd = DocumentBuilderFactory.newInstance();
		// 创建解析器
		DocumentBuilder builder = builderFactoryd.newDocumentBuilder();
		// 解析xml返回document
		Document doc = builder.parse("src/person.xml");

		// 编写一个方法来实现递归的操作
		list(doc);
	}

	// 递归遍历的方法
	public static void list(Node node) {
		// 判断是元素类型时才打印
		if (node.getNodeType() == Node.ELEMENT_NODE)
			System.out.println(node.getNodeName());
		// 得到子节点
		NodeList list = node.getChildNodes();
		if (list != null) {
			// 遍历list
			for (int i = 0; i < list.getLength(); i++) {
				// 得到每一个节点
				Node node1 = list.item(i);
				// 继续得到node1的节点
				list(node1);
			}
		}
	}

	// 删除sex节点
	public static void delSex() throws ParserConfigurationException, SAXException, IOException, TransformerException {
		/**
		 * 操作步骤 1.创建解析器工厂 2.根据解析器工厂创建解析器 3.解析xml返回document 4.得到sex元素 5.获取sex的父节点
		 * 6.使用父节点删除 removeChild() 7.回写xml
		 */

		// 创建解析器工厂
		DocumentBuilderFactory builderFactoryd = DocumentBuilderFactory.newInstance();
		// 创建解析器
		DocumentBuilder builder = builderFactoryd.newDocumentBuilder();
		// 解析xml返回document
		Document doc = builder.parse("src/person.xml");
		// 获取sex元素
		Node sex = doc.getElementsByTagName("sex").item(0);
		// 得到sex的父节点
		Node sexParentNode = sex.getParentNode();
		// 删除sex节点
		sexParentNode.removeChild(sex);
		// 回写xml
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.transform(new DOMSource(doc), new StreamResult("src/person.xml"));
	}

	// 修改第一个p1下面的sex的内容为男
	public static void modifySex()
			throws ParserConfigurationException, SAXException, IOException, TransformerException {
		/**
		 * 操作步骤 1.创建解析器工厂 2.根据解析器工厂创建解析器 3.解析xml返回document 4.得到sex 5.修改sex里面的值
		 * setTextContent() 6.回写xml
		 */

		// 创建解析器工厂
		DocumentBuilderFactory builderFactoryd = DocumentBuilderFactory.newInstance();
		// 创建解析器
		DocumentBuilder builder = builderFactoryd.newDocumentBuilder();
		// 解析xml返回document
		Document doc = builder.parse("src/person.xml");
		// 获取sex元素
		NodeList sexList = doc.getElementsByTagName("sex");
		// 获取sex元素
		Node sex = sexList.item(0);
		// 修改sex值
		sex.setTextContent("男");
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.transform(new DOMSource(doc), new StreamResult("src/person.xml"));
	}

	// 在第一个p1下面（末尾）添加<sex>女</sex>
	public static void addSex() throws ParserConfigurationException, SAXException, IOException, TransformerException {
		/**
		 * 操作步骤 1.创建解析器工厂 2.根据解析器工厂创建解析器 3.解析xml返回document 4.得到第一个p1元素
		 * 5.创建sex标签createElement 6.创建文本createTextNode 7.把文本添加到sex下面 8.把sex添加到第一个p1下
		 * 9.回写xml
		 */

		// 创建解析器工厂
		DocumentBuilderFactory builderFactoryd = DocumentBuilderFactory.newInstance();
		// 创建解析器
		DocumentBuilder builder = builderFactoryd.newDocumentBuilder();
		// 解析xml返回document
		Document doc = builder.parse("src/person.xml");
		// 获取所有p1元素
		NodeList p1List = doc.getElementsByTagName("p1");
		// 获取第一个p1元素
		Node firstP1 = p1List.item(0);
		// 创建标签
		Element sex = doc.createElement("sex");
		// 创建文本
		Text sexText = doc.createTextNode("女");
		// 把文本添加到sex下面
		sex.appendChild(sexText);
		// 把sex添加到p1下面
		firstP1.appendChild(sex);
		// 回写xml
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.transform(new DOMSource(doc), new StreamResult("src/person.xml"));
	}

	// 查询第一个name元素的值
	private static void selectFirst() throws ParserConfigurationException, SAXException, IOException {
		/**
		 * 操作步骤 1.创建解析器工厂 2.根据解析器工厂创建解析器 3.解析xml返回document 4.得到第一个name的元素 5.得到具体的值
		 */

		// 创建解析器工厂
		DocumentBuilderFactory builderFactoryd = DocumentBuilderFactory.newInstance();
		// 创建解析器
		DocumentBuilder builder = builderFactoryd.newDocumentBuilder();
		// 解析xml返回document
		Document doc = builder.parse("src/person.xml");
		// 得到所有name元素
		NodeList nameList = doc.getElementsByTagName("name");
		// 获取第一个元素
		Node item = nameList.item(0);
		// 获取元素的值
		String value = item.getTextContent();
		System.out.println(value);
	}

	// 查询所有name元素的值
	private static void selectAll() throws ParserConfigurationException, SAXException, IOException {
		/**
		 * 操作步骤 1.创建解析器工厂 2.根据解析器工厂创建解析器 3.解析xml返回document 4.得到所有name的元素
		 * 5.遍历集合，得到每一个name元素
		 */

		// 创建解析器工厂
		DocumentBuilderFactory builderFactoryd = DocumentBuilderFactory.newInstance();
		// 创建解析器
		DocumentBuilder builder = builderFactoryd.newDocumentBuilder();
		// 解析xml返回document
		Document doc = builder.parse("src/person.xml");
		NodeList pList = doc.getElementsByTagName("name");
		// 遍历
		for (int i = 0; i < pList.getLength(); i++) {
			Node item = pList.item(i);
			// 得到name元素里面的值
			String value = item.getTextContent();
			System.out.println(value);
		}
	}
}
