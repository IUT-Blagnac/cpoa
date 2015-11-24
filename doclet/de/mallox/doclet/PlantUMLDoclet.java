package de.mallox.doclet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.ConstructorDoc;
import com.sun.javadoc.ExecutableMemberDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.PackageDoc;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.RootDoc;
import com.sun.javadoc.Tag;
import com.sun.javadoc.Type;
import com.sun.tools.doclets.formats.html.ConfigurationImpl;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symbol.TypeSymbol;
import com.sun.tools.javac.code.Symbol.VarSymbol;
import com.sun.tools.javac.util.List;

/**
 * Generates a PlantUML description for UML-class diagram out of Java-source.
 * Only public fields and methods will be exported.
 * <p>
 * The association are based only on field declaration and Javadoc-Tags in
 * methods.
 * <p>
 * There are following Javadoc-Tags to describe associations (according to
 * UMLGraph):
 * <ul>
 * <li>
 *
 * @note: can be used to add a note to a class</li>
 *        <li>
 * @assoc: Pattern: &lt;cardinality&gt; - &lt;cardinality&gt; &lt;assoziated
 *         class&gt; (no spaces)</li>
 *         <li>
 * @navassoc: Pattern: &lt;cardinality&gt; - &lt;cardinality&gt; &lt;assoziated
 *            class&gt; (no spaces)</li>
 *            <li>
 * @overrideAssoc: can be used to mark a field-declaration not to export its
 *                 association.</li>
 *                 </ul>
 *                 <p>
 *                 For configuration following system.properties can be used:
 *                 <ul>
 *                 <li>createPackages: Default value is <code>false</code></li>
 *                 <li>showPublicMethods: Default value is <code>true</code></li>
 *                 <li>showPublicConstructors: Default value is
 *                 <code>true</code></li>
 *                 <li>showPublicFields: Default value is <code>true</code></li>
 *                 </ul>
 *                 <p>
 *                 Ant-Script Example:
 *                 <p>
 *                 <code>
 * &lt;javadoc doclet=&quot;de.mallox.doclet.PlantUMLDoclet&quot;<br>
 * &nbsp;&nbsp; docletpath=&quot;plantUmlDoclet.jar&quot;<br>
 * &nbsp;&nbsp; access=&quot;private&quot;<br>
 * &nbsp;&nbsp; additionalparam=&quot;-J-DdestinationFile=uml.txt -J-DcreatePackages=false -J-DshowPublicMethods=true -J-DshowPublicConstructors=flase -J-DshowPublicFields=true&quot;&gt;<br>
 * &nbsp;&nbsp;&lt;packageset dir=&quot;src&quot;&gt;<br>
 * &nbsp;&nbsp; &lt;include name=&quot;de/mallox/**&quot;/&gt;<br>
 * &nbsp;&nbsp;&lt;/packageset&gt;<br>
 * &lt;javadoc&gt;<br>
 * </code>
 *                 <p>
 *                 <code>
 * &lt;java jar=plantuml.jar&quot; fork=&quot;true&quot; maxmemory="128m"&gt;<br>
 * &nbsp;&lt;arg value=&quot;uml.txt&quot;/&gt;<br>
 * &lt;/java&gt;<br>
 * </code>
 */
public class PlantUMLDoclet {

	public static final String SYSTEM_PROPERTY_DESTINATION_FILENAME = "destinationFile";
	public static final String SYSTEM_PROPERTY_CREATE_PACKAGES = "createPackages";
	public static final String SYSTEM_PROPERTY_SHOW_PUBLIC_METHODS = "showPublicMethods";
	public static final String SYSTEM_PROPERTY_SHOW_PUBLIC_CONSTRUCTORS = "showPublicConstructors";
	public static final String SYSTEM_PROPERTY_SHOW_PUBLIC_FIELDS = "showPublicFields";

	private Set<ClassDoc> whiteliste = new HashSet<ClassDoc>();
	private Set<String> whitelistePerName = new HashSet<String>();
	private Map<PackageDoc, java.util.List<ClassDoc>> packageMap = new HashMap<PackageDoc, java.util.List<ClassDoc>>();

	private ConfigurationImpl configuration;

	private int noteNumber = 1;
	private boolean createPackages;
	private boolean showPublicMethods;
	private boolean showPublicConstructors;
	private boolean showPublicFields;

	/**
	 * The "start" method as required by Javadoc.
	 */
	public static boolean start(RootDoc root) throws IOException {
		try {
			PlantUMLDoclet doclet = new PlantUMLDoclet();
			doclet.init(root);
			doclet.createPlantUml(root);
		} catch (Exception exc) {
			return false;
		}
		return true;
	}

	private void init(RootDoc root) {
		configuration = ConfigurationImpl();
		configuration.root = root;
		createPackages = Boolean.valueOf(System.getProperty(SYSTEM_PROPERTY_CREATE_PACKAGES, "false")).booleanValue();
		showPublicMethods = Boolean.valueOf(System.getProperty(SYSTEM_PROPERTY_SHOW_PUBLIC_METHODS, "true"))
				.booleanValue();
		showPublicConstructors = Boolean.valueOf(System.getProperty(SYSTEM_PROPERTY_SHOW_PUBLIC_CONSTRUCTORS, "true"))
				.booleanValue();
		showPublicFields = Boolean.valueOf(System.getProperty(SYSTEM_PROPERTY_SHOW_PUBLIC_FIELDS, "true"))
				.booleanValue();
	}

	private void addClassDocToPackageMap(ClassDoc classDoc) {
		PackageDoc packageDoc = classDoc.containingPackage();
		if (packageMap.containsKey(packageDoc) == false) {
			packageMap.put(packageDoc, new ArrayList<ClassDoc>());
		}
		packageMap.get(packageDoc).add(classDoc);
	}

	private void createPlantUml(RootDoc root) {
		System.out.println("PlantUMLDoclet.createPlantUml() -  start");

		PrintWriter pw = null;
		try {
			pw = openOutputFile();

			writeBeginUml(pw);

			// identify relevant classes:
			identifyRelevantClasses(root.specifiedClasses());
			for (PackageDoc packageDoc : root.specifiedPackages()) {
				identifyRelevantClasses(packageDoc.allClasses());
			}

			// write definitions:
			System.out.println("write interfaces/ abstract classes...");
			pw.println();
			pw.println("' definitions");
			pw.println("' -----------");
			pw.println();
			for (PackageDoc packageDoc : packageMap.keySet()) {
				writePackage(pw, packageDoc);
				for (ClassDoc classDoc : packageMap.get(packageDoc)) {
					writeClassDef(pw, classDoc);
				}
				endPackage(pw);
				pw.println();
			}

			// Write class links
			for (PackageDoc packageDoc : packageMap.keySet()) {
				for (ClassDoc classDoc : packageMap.get(packageDoc)) {
					handleClassLink(pw, "navassoc", classDoc, "-->");
					handleClassLink(pw, "depend", classDoc, "..>");
					handleClassLink(pw, "assoc", classDoc, "--");
					handleExtends(pw, "extends", classDoc, "<|--");
					handleExtends(pw, "implements", classDoc, "<|..");
				}

			}

			// write content:
			System.out.println("write content...");
			pw.println("' content");
			pw.println("' -------");
			pw.println();
			for (ClassDoc classDoc : whiteliste) {
				writeContent(pw, classDoc);
				pw.println();
			}

			writeEndUml(pw);
		} catch (Throwable e) {
			root.printError("Failed to create outputfile!");
			root.printError("Reson: " + e.toString());
			throw new RuntimeException(e);
		} finally {
			if (pw != null) {
				pw.close();
			}
		}

		System.out.println("PlantUMLDoclet.createPlantUml() -  end");
	}

	private void identifyRelevantClasses(ClassDoc[] classes) {
		for (ClassDoc classDoc : classes) {
			if (whiteliste.contains(classDoc) == false) {
				boolean isInnerElement = isInnerElement(classDoc);
				if (isInnerElement == false || classDoc.isPublic()) {
					whiteliste.add(classDoc);
					whitelistePerName.add(classDoc.qualifiedName());
					addClassDocToPackageMap(classDoc);
				}
			}
		}
	}

	private void writePackage(PrintWriter pw, PackageDoc packageDoc) {
		if (createPackages == false) {
			pw.print("' ");
		}
		pw.println("package \"" + packageDoc.name() + "\"");
	}

	private void endPackage(PrintWriter pw) {
		if (createPackages == false) {
			pw.print("' ");
		}
		pw.println("end package");
	}

	private void writeClassDef(PrintWriter pw, ClassDoc classDoc) {
		String className = getShortClassName(classDoc);

		boolean tIsInterface = classDoc.isInterface();
		if (classDoc.isAbstract() && !tIsInterface) {
			pw.println("abstract " + className);
		} else if (tIsInterface) {
			pw.println("interface " + className);
		} else if (isEnum(classDoc)) {
			pw.println("enum " + className);
		} else if (classDoc.isClass()) {
			pw.println("class " + className);
		}

		handleNote(pw, classDoc);
	}

	/**
	 * If ClassDoc name doesn't match it's simpleTypeName it's purposed to be
	 * inner Element.
	 *
	 * @param classDoc
	 * @return
	 */
	private boolean isInnerElement(ClassDoc classDoc) {
		return classDoc.name().equals(classDoc.simpleTypeName()) == false;
	}

	private String getShortClassName(ClassDoc classDoc) {
		return classDoc.simpleTypeName();
	}

	/**
	 * workaround to detect enum.
	 */
	private boolean isEnum(ClassDoc classDoc) {
		ClassDoc tFindClass = classDoc.findClass(Enum.class.getName());
		if (classDoc.subclassOf(tFindClass)) {
			return true;
		}
		return false;
	}

	private PrintWriter openOutputFile() throws IOException, UnsupportedEncodingException {

		// Filename:
		String tFileName = System.getProperty(SYSTEM_PROPERTY_DESTINATION_FILENAME, "output.txt");

		// configuration
		String tDestFileName = tFileName;
		String tDocencoding = configuration.docencoding;

		System.out.println("open outputfile: " + tDestFileName);

		// create Writer:
		Writer writer;
		FileOutputStream fos = new FileOutputStream(tDestFileName);
		if (tDocencoding == null) {
			writer = new OutputStreamWriter(fos);
		} else {
			writer = new OutputStreamWriter(fos, tDocencoding);
		}
		return new PrintWriter(writer);
	}

	private void writeContent(PrintWriter pw, ClassDoc classDoc) {

		System.out.println("write Element " + getShortClassName(classDoc) + "...");
		pw.println("' " + getShortClassName(classDoc) + ":");

		// Generalization:
		if (classDoc.superclass() != null) {
			ClassDoc superClass = classDoc.superclass().asClassDoc();
			if (whiteliste.contains(superClass)) {
				String superClassName = getShortClassName(superClass);
				pw.println(superClassName + " <|-- " + getShortClassName(classDoc));
			}
		}

		// Implementation:
		Type[] interfaceTypes = classDoc.interfaces();
		int interfaceNum = interfaceTypes.length;
		if (interfaceNum != 0) {
			for (int i = 0; i < interfaceNum; i++) {
				ClassDoc tInterfaceClassDoc = interfaceTypes[i].asClassDoc();

				if (whiteliste.contains(tInterfaceClassDoc)) {
					String interfaceName = tInterfaceClassDoc.typeName();
					pw.println(interfaceName + " <|.. " + getShortClassName(classDoc));
				} else {
					System.out.println("skip interface: " + getShortClassName(tInterfaceClassDoc));
				}
			}
		}

		// Fields:
		for (FieldDoc field : classDoc.fields()) {
			writeFields(pw, classDoc, field);
		}

		// Constructors:
		for (ConstructorDoc constructor : classDoc.constructors()) {
			writeConstructors(pw, classDoc, constructor);
		}

		// Methods:
		for (MethodDoc method : classDoc.methods()) {
			writeMethods(pw, classDoc, method);
		}
	}

	private void writeFields(PrintWriter pw, ClassDoc classDoc, final FieldDoc fieldDoc) {
		Type type = fieldDoc.type();
		if (showPublicFields && fieldDoc.isPublic() == true) {
			writeFieldDescription(pw, classDoc, fieldDoc.name(), type.typeName());
		}

		if (isEnum(classDoc) == false) {
			Tag[] tags = fieldDoc.tags("overrideAssoc");
			if (tags.length == 0) {
				checkAssociations(pw, classDoc, fieldDoc, type, type.asClassDoc());
			} else {
				System.out
						.println("overrideAssoc from " + getShortClassName(classDoc) + " to " + fieldDoc.name() + "!");
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void checkAssociations(PrintWriter pw, ClassDoc classDoc, final FieldDoc fieldDoc, Type type,
			ClassDoc pFiledClass) {

		if (whiteliste.contains(pFiledClass)) {
			if ("".equals(type.dimension())) {
				writeNavAssoziationDescription(pw, classDoc, getShortClassName(pFiledClass), "1");
			} else {
				writeNavAssoziationDescription(pw, classDoc, getShortClassName(pFiledClass), "*");
			}
		} else {

			// check collection/ generic:
			try {
				// Workaround for erasure in TypeMaker:
				Field field = fieldDoc.getClass().getDeclaredField("sym");
				field.setAccessible(true);
				Symbol.VarSymbol tSymbol = (VarSymbol) field.get(fieldDoc);
				com.sun.tools.javac.code.Type tSymbolType = tSymbol.type;

				if (tSymbolType != null) {
					TypeSymbol tTypeSymbol = tSymbolType.asElement();
					String tQualifiedClassName = tTypeSymbol.toString();

					if (whitelistePerName.contains(tQualifiedClassName)) {
						Class tClass = Class.forName(tQualifiedClassName);
						if (Collection.class.isAssignableFrom(tClass)) {
							List<com.sun.tools.javac.code.Type> tTypeArguments = tSymbolType.getTypeArguments();
							if (tTypeArguments.length() == 1) {
								writeNavAssoziationDescription(pw, classDoc,
										tTypeArguments.get(0).tsym.name.toString(), "*");
							}
						} else if (Map.class.isAssignableFrom(tClass)) {
							List<com.sun.tools.javac.code.Type> tTypeArguments = tSymbolType.getTypeArguments();
							if (tTypeArguments.length() == 2) {
								writeNavAssoziationDescription(pw, classDoc,
										tTypeArguments.get(1).tsym.name.toString(), "*");
							}
						}
					} else {
						System.out.println("skip association for " + getShortClassName(classDoc) + " --> "
								+ tQualifiedClassName);
					}
				}
			} catch (Exception e) {
				System.out.println("skip association for " + getShortClassName(classDoc) + "." + pFiledClass);
			}
		}
	}

	private void writeNavAssoziationDescription(PrintWriter pw, ClassDoc parent, String pFiledClassName,
			String pCardinality) {
		pw.println(getShortClassName(parent) + " --> \"" + pCardinality + "\" " + pFiledClassName);
	}

	// private void writeAssoziationDescription(PrintWriter pw, ClassDoc parent,
	// String pFiledClassName,
	// String pCardinality) {
	// pw.println(getShortClassName(parent) + " \"1\" -- \"" + pCardinality +
	// "\" " + pFiledClassName);
	// }
	//
	private void writeFieldDescription(PrintWriter pw, ClassDoc classDoc, String pVarName, String pVarType) {
		pw.println(getShortClassName(classDoc) + " : " + pVarType + " " + pVarName);
	}

	private void writeConstructors(PrintWriter pw, ClassDoc classDoc, ConstructorDoc constructor) {

		if (showPublicConstructors && constructor.isPublic() == true) {

			// Begin:
			pw.print(getShortClassName(classDoc) + " : " + getShortClassName(classDoc) + "(");

			// Parameters (only types):
			writeParameters(pw, constructor);

			// End:
			pw.println(")");
		}
	}

	private void writeMethods(PrintWriter pw, ClassDoc classDoc, MethodDoc method) {
		if (showPublicMethods && method.isPublic() == true) {
			// Return type:
			Type tReturnType = method.returnType();
			String tReturnTypeName = "";
			if (tReturnType != null) {
				tReturnTypeName = tReturnType.typeName();
			}

			// Begin:
			pw.print(getShortClassName(classDoc) + " : " + tReturnTypeName + " " + method.name() + "(");

			// Parameters (only types):
			writeParameters(pw, method);

			// End:
			pw.println(")");
		}
		// handleAssocTag(pw, method);
		// handleNavAssocTag(pw, method);
	}

	private void writeParameters(PrintWriter pw, ExecutableMemberDoc pExecutableMemberDoc) {
		Parameter[] tParameters = pExecutableMemberDoc.parameters();
		for (int i = 0; i < tParameters.length; i++) {
			if (i > 0) {
				pw.print(", ");
			}

			// Type:
			pw.print(tParameters[i].type().typeName());
		}
	}

	private void writeBeginUml(PrintWriter pw) {
		pw.println("@startuml");
	}

	private static void writeEndUml(PrintWriter pw) {
		pw.println("@enduml");
	}

	/**
	 * Tag
	 *
	 * @note
	 */
	private void handleNote(PrintWriter pw, ClassDoc classDoc) {
		for (Tag tTag : classDoc.tags("note")) {
			String tText = tTag.text();
			String tNoteId = getNextNoteId();
			pw.println("note \"" + tText + "\" as " + tNoteId);
			pw.println(getShortClassName(classDoc) + " .. " + tNoteId);
		}
	}

	private void handleClassLink(PrintWriter pw, String tag, ClassDoc classDoc, String type) {
		for (Tag tTag : classDoc.tags(tag)) {
			String tText = tTag.text();
			// Pattern: <cardinality> - <cardinality> <assoziated class>
			String[] split = tText.split("\\s");
			if (split.length == 4) {
				printLink(pw, classDoc, split, type);
			}
		}
	}

	private void handleExtends(PrintWriter pw, String tag, ClassDoc classDoc, String type) {
		for (Tag tTag : classDoc.tags(tag)) {
			final String tText = tTag.text();
			pw.println(tText + " " + type + " " + getShortClassName(classDoc));
		}
	}

	private void printLink(PrintWriter pw, ClassDoc classDoc, String[] split, String type) {
		final String card1 = split[0].equals("-") ? "" : "\"" + split[0] + "\"";
		final String card2 = split[2].equals("-") ? "" : "\"" + split[2] + "\"";
		if (split[1].equals("-")) {
			pw.println(getShortClassName(classDoc) + " " + card1 + " " + type + " " + card2 + " " + split[3]);
		} else {
			pw.println(getShortClassName(classDoc) + " " + card1 + " " + type + " " + card2 + " " + split[3] + ":"
					+ split[1]);
		}
	}

	private String getNextNoteId() {
		return "Note" + noteNumber++;
	}
}
