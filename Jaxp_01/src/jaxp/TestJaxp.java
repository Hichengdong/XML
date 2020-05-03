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

	// �����ڵ㣬������Ԫ�ص����ƶ���ӡ����
	public static void listElement() throws ParserConfigurationException, SAXException, IOException {
		/**
		 * �������� 1.�������������� 2.���ݽ������������������� 3.����xml����document ==�ݹ�== 4.�õ����ڵ�Ԫ�� 5.�õ����ڵ���ӽڵ�
		 * 6.�õ����ڵ��ӽڵ���ӽڵ�
		 */

		// ��������������
		DocumentBuilderFactory builderFactoryd = DocumentBuilderFactory.newInstance();
		// ����������
		DocumentBuilder builder = builderFactoryd.newDocumentBuilder();
		// ����xml����document
		Document doc = builder.parse("src/person.xml");

		// ��дһ��������ʵ�ֵݹ�Ĳ���
		list(doc);
	}

	// �ݹ�����ķ���
	public static void list(Node node) {
		// �ж���Ԫ������ʱ�Ŵ�ӡ
		if (node.getNodeType() == Node.ELEMENT_NODE)
			System.out.println(node.getNodeName());
		// �õ��ӽڵ�
		NodeList list = node.getChildNodes();
		if (list != null) {
			// ����list
			for (int i = 0; i < list.getLength(); i++) {
				// �õ�ÿһ���ڵ�
				Node node1 = list.item(i);
				// �����õ�node1�Ľڵ�
				list(node1);
			}
		}
	}

	// ɾ��sex�ڵ�
	public static void delSex() throws ParserConfigurationException, SAXException, IOException, TransformerException {
		/**
		 * �������� 1.�������������� 2.���ݽ������������������� 3.����xml����document 4.�õ�sexԪ�� 5.��ȡsex�ĸ��ڵ�
		 * 6.ʹ�ø��ڵ�ɾ�� removeChild() 7.��дxml
		 */

		// ��������������
		DocumentBuilderFactory builderFactoryd = DocumentBuilderFactory.newInstance();
		// ����������
		DocumentBuilder builder = builderFactoryd.newDocumentBuilder();
		// ����xml����document
		Document doc = builder.parse("src/person.xml");
		// ��ȡsexԪ��
		Node sex = doc.getElementsByTagName("sex").item(0);
		// �õ�sex�ĸ��ڵ�
		Node sexParentNode = sex.getParentNode();
		// ɾ��sex�ڵ�
		sexParentNode.removeChild(sex);
		// ��дxml
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.transform(new DOMSource(doc), new StreamResult("src/person.xml"));
	}

	// �޸ĵ�һ��p1�����sex������Ϊ��
	public static void modifySex()
			throws ParserConfigurationException, SAXException, IOException, TransformerException {
		/**
		 * �������� 1.�������������� 2.���ݽ������������������� 3.����xml����document 4.�õ�sex 5.�޸�sex�����ֵ
		 * setTextContent() 6.��дxml
		 */

		// ��������������
		DocumentBuilderFactory builderFactoryd = DocumentBuilderFactory.newInstance();
		// ����������
		DocumentBuilder builder = builderFactoryd.newDocumentBuilder();
		// ����xml����document
		Document doc = builder.parse("src/person.xml");
		// ��ȡsexԪ��
		NodeList sexList = doc.getElementsByTagName("sex");
		// ��ȡsexԪ��
		Node sex = sexList.item(0);
		// �޸�sexֵ
		sex.setTextContent("��");
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.transform(new DOMSource(doc), new StreamResult("src/person.xml"));
	}

	// �ڵ�һ��p1���棨ĩβ�����<sex>Ů</sex>
	public static void addSex() throws ParserConfigurationException, SAXException, IOException, TransformerException {
		/**
		 * �������� 1.�������������� 2.���ݽ������������������� 3.����xml����document 4.�õ���һ��p1Ԫ��
		 * 5.����sex��ǩcreateElement 6.�����ı�createTextNode 7.���ı���ӵ�sex���� 8.��sex��ӵ���һ��p1��
		 * 9.��дxml
		 */

		// ��������������
		DocumentBuilderFactory builderFactoryd = DocumentBuilderFactory.newInstance();
		// ����������
		DocumentBuilder builder = builderFactoryd.newDocumentBuilder();
		// ����xml����document
		Document doc = builder.parse("src/person.xml");
		// ��ȡ����p1Ԫ��
		NodeList p1List = doc.getElementsByTagName("p1");
		// ��ȡ��һ��p1Ԫ��
		Node firstP1 = p1List.item(0);
		// ������ǩ
		Element sex = doc.createElement("sex");
		// �����ı�
		Text sexText = doc.createTextNode("Ů");
		// ���ı���ӵ�sex����
		sex.appendChild(sexText);
		// ��sex��ӵ�p1����
		firstP1.appendChild(sex);
		// ��дxml
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.transform(new DOMSource(doc), new StreamResult("src/person.xml"));
	}

	// ��ѯ��һ��nameԪ�ص�ֵ
	private static void selectFirst() throws ParserConfigurationException, SAXException, IOException {
		/**
		 * �������� 1.�������������� 2.���ݽ������������������� 3.����xml����document 4.�õ���һ��name��Ԫ�� 5.�õ������ֵ
		 */

		// ��������������
		DocumentBuilderFactory builderFactoryd = DocumentBuilderFactory.newInstance();
		// ����������
		DocumentBuilder builder = builderFactoryd.newDocumentBuilder();
		// ����xml����document
		Document doc = builder.parse("src/person.xml");
		// �õ�����nameԪ��
		NodeList nameList = doc.getElementsByTagName("name");
		// ��ȡ��һ��Ԫ��
		Node item = nameList.item(0);
		// ��ȡԪ�ص�ֵ
		String value = item.getTextContent();
		System.out.println(value);
	}

	// ��ѯ����nameԪ�ص�ֵ
	private static void selectAll() throws ParserConfigurationException, SAXException, IOException {
		/**
		 * �������� 1.�������������� 2.���ݽ������������������� 3.����xml����document 4.�õ�����name��Ԫ��
		 * 5.�������ϣ��õ�ÿһ��nameԪ��
		 */

		// ��������������
		DocumentBuilderFactory builderFactoryd = DocumentBuilderFactory.newInstance();
		// ����������
		DocumentBuilder builder = builderFactoryd.newDocumentBuilder();
		// ����xml����document
		Document doc = builder.parse("src/person.xml");
		NodeList pList = doc.getElementsByTagName("name");
		// ����
		for (int i = 0; i < pList.getLength(); i++) {
			Node item = pList.item(i);
			// �õ�nameԪ�������ֵ
			String value = item.getTextContent();
			System.out.println(value);
		}
	}
}
