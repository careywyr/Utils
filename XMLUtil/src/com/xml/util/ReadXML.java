/**
 * 
 */
package com.xml.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @description 
 * @author 吴晔冉
 *
 */
public class ReadXML {
	
	private static  Map<String, String> policyMap = new HashMap<>();
	
	public static void main(String[] args) throws DocumentException {
		readXml();
	}

	public static void readXml() throws DocumentException{
		// 创建saxReader对象  
	    SAXReader reader = new SAXReader();  
	    // 通过read方法读取一个文件 转换成Document对象  
	    Document document = reader.read(new File("C:\\Users\\lenovo\\Desktop\\policyxml\\新建文件夹\\CLR_DTL_POL_20161022.xml"));  
	    //获取根节点元素对象  
	    Element node = document.getRootElement(); 
	    List<Element> policyList = node.elements();
	    for (Element policy : policyList) {
	    	List<Element> nodes = policy.elements("policyInfo");
			for (Element policyInfo : nodes) {
				List<Element> policyInfoList = policyInfo.elements();
				for (Element element : policyInfoList) {
					if(element.getName().equals("sumAmout")){
						System.out.println("999");
						policyMap.put("sumAmout", element.getTextTrim());
					} 
					if(element.getName().equals("sumPremium")){
						policyMap.put("sumPremium", element.getTextTrim());
					}
					if(element.getName().equals("sumSalesFee")){
						policyMap.put("sumSalesFee", element.getTextTrim());
					}
					System.out.println(element.getName()+":"+element.getTextTrim());
					List<Element> insurantList = element.elements();
					for (Element insurant : insurantList) {
						List<Element> insurants = insurant.elements();
						for (Element insur : insurants) {
							System.out.println(insur.getName()+":"+insur.getTextTrim());
						}
					}
				}
				
				System.out.println("****************************************");
			}
			List<Element> dispaPolicyList = policy.element("dispa-policy-list").elements("dispa-policy");
			for (Element dispaPolicy : dispaPolicyList) {
				List<Element> dispaPolicyInfoList = dispaPolicy.elements();
				for (Element dispa : dispaPolicyInfoList) {
					System.out.println(dispa.getName()+":"+dispa.getTextTrim());
					List<Element> dispainsurantList = dispa.elements("dispa-insurantList");
					for (Element element : dispainsurantList) {
						System.out.println(element.getName());
					}
				}
			}
		}
	    
	} 
}
