/*******************************************************************************
 * Copyright (c) 2011, 2013 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Oracle - initial API and implementation
 *
 ******************************************************************************/
package cz.burios.uniql.persistence.jpa.jpql.tools.spi;

/**
 * This enumeration lists the mapping types defined in the Java Persistence functional specification
 * and those that are provided by EclipseLink.
 * <p>
 * Provisional API: This interface is part of an interim API that is still under development and
 * expected to change significantly before reaching stability. It is available at this early stage
 * to solicit feedback from pioneering adopters on the understanding that any code that uses this
 * API will almost certainly be broken (repeatedly) as the API evolves.
 *
 * @version 2.4
 * @since 2.4
 * @author Pascal Filion
 */
public interface IEclipseLinkMappingType extends IMappingType {

	/**
	 * The constant for a basic collection mapping, which is deprecated.
	 */
	int BASIC_COLLECTION = 101;

	/**
	 * The constant for a basic map mapping, which is deprecated.
	 */
	int BASIC_MAP = 102;

	/**
	 * The constant for a transformation mapping.
	 */
	int TRANSFORMATION = 103;

	/**
	 * The constant for a variable one to one mapping.
	 */
	int VARIABLE_ONE_TO_ONE = 104;
}