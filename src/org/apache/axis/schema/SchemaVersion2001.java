/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 2001-2003 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Axis" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */

package org.apache.axis.schema;

import org.apache.axis.Constants;
import org.apache.axis.encoding.TypeMapping;
import org.apache.axis.encoding.ser.CalendarDeserializerFactory;
import org.apache.axis.encoding.ser.CalendarSerializerFactory;

import javax.xml.namespace.QName;

/**
 * 2001 Schema characteristics.
 *
 * @author Glen Daniels (gdaniels@apache.org)
 */
public class SchemaVersion2001 implements SchemaVersion {
    public static QName QNAME_NIL = new QName(Constants.URI_2001_SCHEMA_XSI,
                                              "nil");

    /**
     * Package-access constructor - access this through SchemaVersion
     * constants.
     */
    SchemaVersion2001() {
    }

    /**
     * Get the appropriate QName for the "null"/"nil" attribute for this
     * Schema version.
     * @return {http://www.w3.org/2001/XMLSchema-instance}nil
     */
    public QName getNilQName() {
        return QNAME_NIL;
    }

    /**
     * The XSI URI
     * @return the XSI URI
     */
    public String getXsiURI() {
        return Constants.URI_2001_SCHEMA_XSI;
    }

    /**
     * The XSD URI
     * @return the XSD URI
     */
    public String getXsdURI() {
        return Constants.URI_2001_SCHEMA_XSD;
    }

    /**
     * Register the schema specific type mappings
     */
    public void registerSchemaSpecificTypes(TypeMapping tm) {
        
        // This mapping will convert a Java 'Date' type to a dateTime
        tm.register(java.util.Date.class,
                    Constants.XSD_DATETIME,
                   new CalendarSerializerFactory(java.util.Date.class,
                                             Constants.XSD_DATETIME),
                   new CalendarDeserializerFactory(java.util.Date.class,
                                               Constants.XSD_DATETIME)
                   );
        
        // This is the preferred mapping per JAX-RPC.
        // Last one registered take priority
        tm.register(java.util.Calendar.class,
                    Constants.XSD_DATETIME,
                   new CalendarSerializerFactory(java.util.Calendar.class,
                                             Constants.XSD_DATETIME),
                   new CalendarDeserializerFactory(java.util.Calendar.class,
                                               Constants.XSD_DATETIME)
                   );
        
    }
}
