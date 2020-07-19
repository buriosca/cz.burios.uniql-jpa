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
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package cz.burios.uniql.persistence.oxm.platform;

import java.util.Map;

import cz.burios.uniql.persistence.internal.databaseaccess.DatasourcePlatform;
import cz.burios.uniql.persistence.internal.helper.ConversionManager;
import cz.burios.uniql.persistence.internal.oxm.XMLConversionManager;
import cz.burios.uniql.persistence.internal.oxm.XMLUnmarshaller;
import cz.burios.uniql.persistence.internal.oxm.record.PlatformUnmarshaller;

public abstract class XMLPlatform<XML_UNMARSHALLER extends XMLUnmarshaller> extends DatasourcePlatform implements cz.burios.uniql.persistence.internal.oxm.record.XMLPlatform<XML_UNMARSHALLER> {
    public ConversionManager getConversionManager() {
        // Lazy init for serialization.
        if (conversionManager == null) {
            //Clone the default to allow customers to easily override the conversion manager
            conversionManager = (XMLConversionManager)XMLConversionManager.getDefaultXMLManager().clone();
        }
        return conversionManager;
    }

    /**
     * INTERNAL:
     */
    public abstract PlatformUnmarshaller newPlatformUnmarshaller(XML_UNMARSHALLER xmlUnmarshaller);

    /**
     * INTERNAL:
     */
    @Override
    public abstract PlatformUnmarshaller newPlatformUnmarshaller(XML_UNMARSHALLER xmlUnmarshaller, Map<String, Boolean> parserFeatures);

}
