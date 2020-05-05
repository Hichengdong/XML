package dom4j;

import java.io.IOException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import utils.Dom4jUtils;

public class TestDom4j {
	public static void main(String[] args) throws DocumentException, IOException {
		getValues();
	}
	
	// ��ȡ��һ��p1���������id��ֵ
	public static void getValues() throws DocumentException {
		/*
		 * 1.�õ�document
		 * 2.�õ����ڵ�
		 * 3.�õ���һ��p1Ԫ��
		 * 4.�õ�p1���������ֵ
		 */
		
		// �õ�document
		Document document = Dom4jUtils.getDocument("src/p1.xml");
		// �õ����ڵ�
		Element root = document.getRootElement();
		// �õ���һ��p1
		Element p1 = root.element("p1");
		System.out.println(p1.attributeValue("id"));
	}
	
	// ɾ����һ��p1�����schoolԪ��
	public static void delSchool() throws DocumentException, IOException {
		/*
		 * 1���õ�document
		 * 2.�õ����ڵ�
		 * 3.�õ���һ��p1
		 * 4.�õ���һ��p1�����school
		 * 5.ʹ��p1ɾ��school
		 * 6.��дxml
		 */
		
		// �õ�document
		Document document = Dom4jUtils.getDocument("src/p1.xml");
		// �õ����ڵ�
		Element root = document.getRootElement();
		// �õ���һ��p1
		Element p1 = root.element("p1");
		// �õ���һ��p1�����school
		Element school = p1.element("school");
		// ɾ��school
		school.getParent().remove(school);
		// ��дxml
		Dom4jUtils.overridexml(document, "src/p1.xml");
	}
	
	// �޸ĵ�һ��p1�����ageԪ�ص�ֵ<age>30</age>
	public static void modifyAge() throws IOException, DocumentException {
		/*
		 * 1���õ�document
		 * 2.�õ���һ��p1Ԫ��
		 * 3.�õ���һ��p1�����age
		 * 4.�޸�ֵΪ30
		 * 5.��дxml
		 */
		
		// �õ�document
		Document document = Dom4jUtils.getDocument("src/p1.xml");
		// �õ����ڵ�
		Element root = document.getRootElement();
		// �õ���һ��p1
		Element p1 = root.element("p1");
		// �õ�p1�����age
		Element age = p1.element("age");
		// �޸�element��ֵ
		age.setText("30");
		Dom4jUtils.overridexml(document, "src/p1.xml");
	}
	
	// �ڵ�һ��p1�����age��ǩ֮ǰ���<school>��������ѧ</school>
	public static void addAgeBefore() throws DocumentException, IOException {
		/*
		 * 1.����������
		 * 2.�õ�document
		 * 3.�õ����ڵ�
		 * 4.��ȡ����һ��p1
		 * 5.��ȡp1���������Ԫ��
		 *   ** elements()���� ����list����
		 *   ** ����Ԫ��
		 *   ** ʹ��list����ķ��������ص�λ�����Ԫ��
		 *       *** add(int index,E element)
		 * 6.��дxml
		 */
		
		// �õ�document
		Document document = Dom4jUtils.getDocument("src/p1.xml");
		// �õ����ڵ�
		Element root = document.getRootElement();
		// �õ���һ��p1Ԫ��
		Element p1 = root.element("p1");
		// ��ȡp1���������Ԫ��
		List<Element> list = p1.elements();
		// ����Ԫ��
		Element school = DocumentHelper.createElement("school");
		// ��school��������ı�
		school.setText("��������ѧ");
		// ���ض�λ�����Ԫ��
		list.add(1,school);
		// ��дxml
		Dom4jUtils.overridexml(document, "src/p1.xml");
	}
	
	// �ڵ�һ��p1��ǩĩβ���һ��Ԫ��<sex>Ů</sex>
	public static void addSex() throws DocumentException, IOException {
		/*
		 * 1.����������
		 * 2.�õ�document
		 * 3.�õ����ڵ�
		 * 4.��ȡ����һ��p1
		 * 5.��p1�������Ԫ��
		 * 6.��Ԫ����������ı�
		 * 7.��дxml
		 */
		
		// �õ�document
		Document document = Dom4jUtils.getDocument("src/p1.xml");		// �õ����ڵ�
		Element root = document.getRootElement();
		// �õ���һ��p1Ԫ��
		Element p1 = root.element("p1");
		// ��p1����ֱ�����Ԫ��
		Element sex= p1.addElement("sex");
		// ��sex��������ı�
		sex.setText("Ů");
		// ��дxml
		Dom4jUtils.overridexml(document, "src/p1.xml");
	}

	// ��ȡ�ڶ���nameԱ��ֵ
	public static void selectSecond() throws DocumentException {
		/*
		 * 1.���������� 2.�õ�document 3.�õ���Ԫ�� 4.�õ��ڶ���p1 5.�õ�p1�����name 6.�õ�name��ֵ
		 */

		// ����������
		SAXReader saxReader = new SAXReader();
		// �õ�document
		Document document = saxReader.read("src/p1.xml");
		// �õ����ڵ�
		Element root = document.getRootElement();
		// �õ�p1
		List<Element> list = root.elements("p1");
		// �õ��ڶ���p1����name��Ԫ��
		Element name = list.get(1).element("name");
		// �õ�name�����ֵ
		System.out.println(name.getText());
	}

	// ��ȡ����һ��nameԪ�������ֵ
	public static void selectsin() throws DocumentException {
		/*
		 * 1.���������� 2.�õ�document 3.�õ���Ԫ�� 4.�õ���һ��p1 5.�õ�p1�����name 6.�õ�name��ֵ
		 */
		// �õ�document
		Document document = Dom4jUtils.getDocument("src/p1.xml");		// �õ����ڵ�
		Element root = document.getRootElement();
		// �õ�p1
		Element p1 = root.element("p1");
		// �õ�p1����name��Ԫ��
		Element name = p1.element("name");
		// �õ�name�����ֵ
		System.out.println(name.getText());
	}

	// ��ѯxml������nameԪ�ص�ֵ
	public static void selectAllName() throws DocumentException {
		/*
		 * 1.���������� 2.�õ�document 3.�õ���Ԫ�� 4.�õ�p1 5.�õ�p1�����name 6.�õ�name��ֵ
		 */

		// �õ�document
		Document document = Dom4jUtils.getDocument("src/p1.xml");		// �õ����ڵ�
		Element root = document.getRootElement();
		// �õ�p1
		List<Element> list = root.elements("p1");
		// ����list
		for (Element element : list) {
			// Element��ÿһ��p1��Ԫ��
			// �õ�p1����name��Ԫ��
			Element name = element.element("name");
			// �õ�name�����ֵ
			System.out.println(name.getText());
		}
	}

}
