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
	
	// 获取第一个p1里面的属性id的值
	public static void getValues() throws DocumentException {
		/*
		 * 1.得到document
		 * 2.得到根节点
		 * 3.得到第一个p1元素
		 * 4.得到p1里面的属性值
		 */
		
		// 得到document
		Document document = Dom4jUtils.getDocument("src/p1.xml");
		// 得到根节点
		Element root = document.getRootElement();
		// 得到第一个p1
		Element p1 = root.element("p1");
		System.out.println(p1.attributeValue("id"));
	}
	
	// 删除第一个p1下面的school元素
	public static void delSchool() throws DocumentException, IOException {
		/*
		 * 1。得到document
		 * 2.得到根节点
		 * 3.得到第一个p1
		 * 4.得到第一个p1下面的school
		 * 5.使用p1删除school
		 * 6.回写xml
		 */
		
		// 得到document
		Document document = Dom4jUtils.getDocument("src/p1.xml");
		// 得到根节点
		Element root = document.getRootElement();
		// 得到第一个p1
		Element p1 = root.element("p1");
		// 得到第一个p1下面的school
		Element school = p1.element("school");
		// 删除school
		school.getParent().remove(school);
		// 回写xml
		Dom4jUtils.overridexml(document, "src/p1.xml");
	}
	
	// 修改第一个p1下面的age元素的值<age>30</age>
	public static void modifyAge() throws IOException, DocumentException {
		/*
		 * 1。得到document
		 * 2.得到第一个p1元素
		 * 3.得到第一个p1下面的age
		 * 4.修改值为30
		 * 5.回写xml
		 */
		
		// 得到document
		Document document = Dom4jUtils.getDocument("src/p1.xml");
		// 得到根节点
		Element root = document.getRootElement();
		// 得到第一个p1
		Element p1 = root.element("p1");
		// 得到p1下面的age
		Element age = p1.element("age");
		// 修改element的值
		age.setText("30");
		Dom4jUtils.overridexml(document, "src/p1.xml");
	}
	
	// 在第一个p1下面的age标签之前添加<school>重庆理工大学</school>
	public static void addAgeBefore() throws DocumentException, IOException {
		/*
		 * 1.创建解析器
		 * 2.得到document
		 * 3.得到根节点
		 * 4.获取到第一个p1
		 * 5.获取p1下面的所有元素
		 *   ** elements()方法 返回list集合
		 *   ** 创建元素
		 *   ** 使用list里面的方法，在特点位置添加元素
		 *       *** add(int index,E element)
		 * 6.回写xml
		 */
		
		// 得到document
		Document document = Dom4jUtils.getDocument("src/p1.xml");
		// 得到根节点
		Element root = document.getRootElement();
		// 得到第一个p1元素
		Element p1 = root.element("p1");
		// 获取p1下面的所有元素
		List<Element> list = p1.elements();
		// 创建元素
		Element school = DocumentHelper.createElement("school");
		// 在school下面添加文本
		school.setText("重庆理工大学");
		// 在特定位置添加元素
		list.add(1,school);
		// 回写xml
		Dom4jUtils.overridexml(document, "src/p1.xml");
	}
	
	// 在第一个p1标签末尾添加一个元素<sex>女</sex>
	public static void addSex() throws DocumentException, IOException {
		/*
		 * 1.创建解析器
		 * 2.得到document
		 * 3.得到根节点
		 * 4.获取到第一个p1
		 * 5.在p1下面添加元素
		 * 6.在元素下面添加文本
		 * 7.回写xml
		 */
		
		// 得到document
		Document document = Dom4jUtils.getDocument("src/p1.xml");		// 得到根节点
		Element root = document.getRootElement();
		// 得到第一个p1元素
		Element p1 = root.element("p1");
		// 在p1下面直接添加元素
		Element sex= p1.addElement("sex");
		// 在sex里面天界文本
		sex.setText("女");
		// 回写xml
		Dom4jUtils.overridexml(document, "src/p1.xml");
	}

	// 获取第二个name员的值
	public static void selectSecond() throws DocumentException {
		/*
		 * 1.创建解析器 2.得到document 3.得到根元素 4.得到第二个p1 5.得到p1下面的name 6.得到name的值
		 */

		// 创建解析器
		SAXReader saxReader = new SAXReader();
		// 得到document
		Document document = saxReader.read("src/p1.xml");
		// 得到根节点
		Element root = document.getRootElement();
		// 得到p1
		List<Element> list = root.elements("p1");
		// 得到第二个p1下面name的元素
		Element name = list.get(1).element("name");
		// 得到name里面的值
		System.out.println(name.getText());
	}

	// 获取到第一个name元素里面的值
	public static void selectsin() throws DocumentException {
		/*
		 * 1.创建解析器 2.得到document 3.得到根元素 4.得到第一个p1 5.得到p1下面的name 6.得到name的值
		 */
		// 得到document
		Document document = Dom4jUtils.getDocument("src/p1.xml");		// 得到根节点
		Element root = document.getRootElement();
		// 得到p1
		Element p1 = root.element("p1");
		// 得到p1下面name的元素
		Element name = p1.element("name");
		// 得到name里面的值
		System.out.println(name.getText());
	}

	// 查询xml中所有name元素的值
	public static void selectAllName() throws DocumentException {
		/*
		 * 1.创建解析器 2.得到document 3.得到根元素 4.得到p1 5.得到p1下面的name 6.得到name的值
		 */

		// 得到document
		Document document = Dom4jUtils.getDocument("src/p1.xml");		// 得到根节点
		Element root = document.getRootElement();
		// 得到p1
		List<Element> list = root.elements("p1");
		// 遍历list
		for (Element element : list) {
			// Element是每一个p1的元素
			// 得到p1下面name的元素
			Element name = element.element("name");
			// 得到name里面的值
			System.out.println(name.getText());
		}
	}

}
