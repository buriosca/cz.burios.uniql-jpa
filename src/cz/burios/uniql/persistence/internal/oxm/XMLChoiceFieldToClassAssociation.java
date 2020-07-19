package cz.burios.uniql.persistence.internal.oxm;

import cz.burios.uniql.persistence.core.mappings.converters.CoreConverter;
import cz.burios.uniql.persistence.internal.oxm.mappings.Field;

/** INTERNAL:
 * <p><b>Purpose</b>: This class holds onto a class name and an XMLField in order to read and write
 * choice mappings from deployment.xml 
 * @author mmacivor
 */
public class XMLChoiceFieldToClassAssociation <
   CONVERTER extends CoreConverter,
   XML_FIELD extends Field
>
{
    protected String className;
    protected XML_FIELD xmlField;
    protected CONVERTER converter;
    
    public XMLChoiceFieldToClassAssociation() {
    }
    
    public XMLChoiceFieldToClassAssociation(XML_FIELD xmlField, String className) {
        this.xmlField = xmlField;
        this.className = className;
    }
    
    public String getClassName() {
        return className;
    }
    
    public void setClassName(String name) {
        this.className = name;
    }
    
    public XML_FIELD getXmlField() {
        return xmlField;
    }
    
    public void setXmlField(XML_FIELD field) {
        this.xmlField = field;
    }
    
    public CONVERTER getConverter() {
        return this.converter;
    }
    
    public void setConverter(CONVERTER valueConverter) {
        this.converter = valueConverter;
    }
}