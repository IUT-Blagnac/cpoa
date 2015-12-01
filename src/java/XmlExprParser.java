public class XmlExprParser {

    public static Expression fromFile(String file) throws ... {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setValidating(true);
        SAXParser sp = spf.newSAXParser();
        ExprHandler ep = new ExprHandler();
        sp.parse(file, ep);
        return ep.getResult();
    }

}
