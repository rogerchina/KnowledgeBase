package com.debuglife.codelabs.crazyit.chapter14;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;

@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes({"Persistent","Id","Property"})
public class HibernateAnnotationProcessor extends AbstractProcessor{

	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		PrintStream ps = null;
		try {
			for(Element e : roundEnv.getElementsAnnotatedWith(Persistent.class)){
				Name clazzName = e.getSimpleName();
				Persistent per = e.getAnnotation(Persistent.class);
				ps = new PrintStream(new FileOutputStream(clazzName.toString() + "hbm.xml"));
				ps.println("<hibernate-mapping>");
				ps.println("<class name=\"" + e);
				ps.println("\" table=\"" +  per.table() + "\">");
				for(Element el : e.getEnclosedElements()){
					if(el.getKind() == ElementKind.FIELD){
						Id id = el.getAnnotation(Id.class);
						if(id != null){
							ps.println("<id name=\"" 
									+ el.getSimpleName()
									+ "\" column=\"" + id.column()
									+ "\" type=\"" + id.type()
									+ "\" />");
							
							ps.println("<generator class=\"" 
									+ id.generator()
									+ "\">");
							ps.println("</id>");
						}
						
						Property property = el.getAnnotation(Property.class);
						if(property != null){
							ps.println("<property name=\"" 
									+ el.getSimpleName()
									+ "\" column=\"" + property.column()
									+ "\" type=\"" + property.type()
									+ "\" />");
						}
					}
				}
				ps.println("</class>");
				ps.println("</hibernate-mapping>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			ps.close();
		}
		
		return false;
	}

}
