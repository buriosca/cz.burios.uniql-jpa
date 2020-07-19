/*******************************************************************************
 * Copyright (c) 1998, 2013 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Matt MacIvor - 2.5.1 - Initial Implementation
 ******************************************************************************/
package cz.burios.uniql.persistence.oxm.mappings;

import java.util.Vector;

import javax.xml.namespace.QName;

import cz.burios.uniql.persistence.descriptors.ClassDescriptor;
import cz.burios.uniql.persistence.exceptions.DescriptorException;
import cz.burios.uniql.persistence.exceptions.XMLMarshalException;
import cz.burios.uniql.persistence.internal.descriptors.InstanceVariableAttributeAccessor;
import cz.burios.uniql.persistence.internal.descriptors.MethodAttributeAccessor;
import cz.burios.uniql.persistence.internal.helper.DatabaseField;
import cz.burios.uniql.persistence.internal.oxm.NamespaceResolver;
import cz.burios.uniql.persistence.internal.oxm.XPathFragment;
import cz.burios.uniql.persistence.internal.oxm.mappings.VariableXPathCollectionMapping;
import cz.burios.uniql.persistence.internal.oxm.mappings.XMLContainerMapping;
import cz.burios.uniql.persistence.internal.queries.ContainerPolicy;
import cz.burios.uniql.persistence.internal.queries.MapContainerPolicy;
import cz.burios.uniql.persistence.internal.sessions.AbstractRecord;
import cz.burios.uniql.persistence.internal.sessions.AbstractSession;
import cz.burios.uniql.persistence.mappings.AttributeAccessor;
import cz.burios.uniql.persistence.mappings.converters.Converter;
import cz.burios.uniql.persistence.oxm.XMLField;
import cz.burios.uniql.persistence.oxm.XMLMarshaller;
import cz.burios.uniql.persistence.oxm.XMLUnmarshaller;
import cz.burios.uniql.persistence.oxm.record.XMLRecord;
import cz.burios.uniql.persistence.sessions.Session;
public class XMLVariableXPathCollectionMapping extends XMLCompositeCollectionMapping implements VariableXPathCollectionMapping<AbstractSession, AttributeAccessor, ContainerPolicy, Converter, ClassDescriptor, DatabaseField, XMLMarshaller, Session, XMLUnmarshaller, XMLRecord>, XMLMapping, XMLContainerMapping {
    
    protected String variableAttributeName;
    protected String variableGetMethodName;
    protected String variableSetMethodName;
    
    private AttributeAccessor variableAttributeAccessor;
 
    private boolean isAttribute;

    public void initialize(AbstractSession session) throws DescriptorException {
       super.initialize(session);

        if(variableAttributeAccessor == null){
        
	        if(variableAttributeName != null){
		        this.variableAttributeAccessor = new InstanceVariableAttributeAccessor();
		        this.variableAttributeAccessor.setAttributeName(variableAttributeName);
	        }else if(variableGetMethodName != null){
	        	this.variableAttributeAccessor = new MethodAttributeAccessor();
	        	this.variableAttributeAccessor.setAttributeName("VARIABLE");
	        	((MethodAttributeAccessor)this.variableAttributeAccessor).setGetMethodName(variableGetMethodName);
	        	if(variableSetMethodName == null){
	        		this.variableAttributeAccessor.setIsWriteOnly(true);
	        	}else{
    	        	((MethodAttributeAccessor)this.variableAttributeAccessor).setSetMethodName(variableSetMethodName);
	        	}	    
	        }	        
        }
        this.variableAttributeAccessor.initializeAttributes(this.getReferenceClass());
    }

    public void useMapClass(String concreteContainerClassName) {
    	MapContainerPolicy policy = new MapContainerPolicy(concreteContainerClassName);                      
        this.setContainerPolicy(policy);
    }
    
    protected void initializeMapContainerPolicy(AbstractSession session, MapContainerPolicy cp){
   	    super.initializeMapContainerPolicy(session, cp);
   	    if(variableAttributeName != null){
   	        cp.setKeyName(variableAttributeName);
   	    }else if(variableGetMethodName != null){
   	    	cp.setKeyMethodName(variableGetMethodName);
   	    }
   	}
    
    @Override
    protected Vector collectFields() {
    	if(field != null){
    		return super.collectFields();
    	}
       // Vector fields = new Vector(1);
        //fields.addElement(this.getField());
        //return fields;
    	return NO_FIELDS;
    }

public Vector getFields() {
	return collectFields();
}
    
//    public Vector getFields() {
  //  	return fields;
    	//return NO_FIELDS;
    // }
    
    protected void initializeReferenceDescriptorAndField(AbstractSession session){
    	 if (getReferenceClass() == null) {
             throw DescriptorException.referenceClassNotSpecified(this);
         }

         setReferenceDescriptor(session.getDescriptor(getReferenceClass()));

         
         ClassDescriptor refDescriptor = this.getReferenceDescriptor();
         if (refDescriptor == null) {
             session.getIntegrityChecker().handleError(DescriptorException.descriptorIsMissing(getReferenceClass().getName(), this));
             return;
         }
         
         if(field != null){
           setField(getDescriptor().buildField(this.field));
           setFields(collectFields());
         }
         
         if (hasConverter()) {
             getConverter().initialize(this, session);
         }   	    
   }
    
    public boolean isAbstractCompositeCollectionMapping(){
    	return false;
    }
    
    public String getVariableAttributeName() {    	
        return variableAttributeName;
    }

    public void setVariableAttributeName(String variableAttributeName) {
        this.variableAttributeName = variableAttributeName;
    }
    
    public String getVariableGetMethodName() {
		return variableGetMethodName;
	}

	public void setVariableGetMethodName(String variableGetMethodName) {
		this.variableGetMethodName = variableGetMethodName;
	}

	public String getVariableSetMethodName() {
		return variableSetMethodName;
	}

	public void setVariableSetMethodName(String variableSetMethodName) {
		this.variableSetMethodName = variableSetMethodName;
	}
		
	
    public AttributeAccessor getVariableAttributeAccessor() {
		return variableAttributeAccessor;
	}

	public void setVariableAttributeAccessor(
			AttributeAccessor variableAttributeAccessor) {
		this.variableAttributeAccessor = variableAttributeAccessor;
	}
	
	
	 public void writeFromObjectIntoRow(Object object, AbstractRecord row, AbstractSession session, WriteType writeType) throws DescriptorException {
		  if (this.isReadOnly()) {
	            return;
	        }

	        Object attributeValue = this.getAttributeValueFromObject(object);
	        ContainerPolicy cp = this.getContainerPolicy();

	        Object iter = cp.iteratorFor(attributeValue);
	        if(null != iter) {
	            while(cp.hasNext(iter)) {
	                Object element = cp.next(iter, session);
	                // convert the value - if necessary
	                element = convertObjectValueToDataValue(element, session, ((XMLRecord) row).getMarshaller());
	                if(element != null) {	                    
	                	XMLField variableField = new XMLField();
	                	XMLRecord xmlRow = (XMLRecord)row;
	                	//variableField.setXPathFragment(getXPathFragmentForValue(element,(XMLRecord)row));
	                	variableField.setXPathFragment(getXPathFragmentForValue(element,xmlRow.getNamespaceResolver(), xmlRow.isNamespaceAware(), xmlRow.getNamespaceSeparator()));
	                	row.put(variableField, buildCompositeRow(variableField, element, session, row, writeType));
	                }
	            }
	        }
	 }
	 
	 protected AbstractRecord buildCompositeRow(XMLField variableField, Object attributeValue, AbstractSession session, AbstractRecord parentRow, WriteType writeType) {
         ClassDescriptor  classDesc = getReferenceDescriptor(attributeValue, session);
	     return buildCompositeRowForDescriptor(classDesc, attributeValue, session, (XMLRecord)parentRow, writeType);
    }

	 public XPathFragment getXPathFragmentForValue(Object obj, NamespaceResolver nr, boolean isNamespaceAware,char sep) {
	    	Object value = getVariableAttributeAccessor().getAttributeValueFromObject(obj);
	    	if(value == null){
	    		throw XMLMarshalException.nullValueNotAllowed(getVariableAttributeName(), getReferenceClassName());
	    	}
	    	
			String returnString;
			String uri = null;
			if(value instanceof QName){
				returnString = ((QName)value).getLocalPart();
				uri = ((QName)value).getNamespaceURI();
			}else{
				returnString = (String)value;
			}
			XPathFragment frag = new XPathFragment();
			frag.setLocalName(returnString);
			if(isNamespaceAware && uri != null && uri.length() >0){								
				String prefix = nr.resolveNamespaceURI(uri);
				if(prefix == null){
	 		   	   prefix = nr.generatePrefix();														
					//marshalRecord.namespaceDeclaration(prefix, uri);
	 		   	   frag.setGeneratedPrefix(true);
				}
				if(prefix != null && prefix.length() >0){
					frag.setPrefix(prefix);
				    //returnString = prefix + sep + returnString;
				}	
			}
				
			//frag.setXPath(returnString);
			//frag.setLocalName(localName);
			
			frag.setNamespaceURI(uri);
			
			return frag;
		}

	    public boolean isAttribute() {
			return isAttribute;
		}

		public void setAttribute(boolean isAttribute) {
			this.isAttribute = isAttribute;
		}

	    public void useMapClassName(String concreteContainerClassName, String methodName) {
	        // the reference class has to be specified before coming here
	        if (this.getReferenceClassName() == null) {
	            throw DescriptorException.referenceClassNotSpecified(this);
	        }
	        MapContainerPolicy policy = new MapContainerPolicy(concreteContainerClassName);	        
	        policy.setKeyName(methodName, getReferenceClass().getName());
	        this.setContainerPolicy(policy);
	    }
}
