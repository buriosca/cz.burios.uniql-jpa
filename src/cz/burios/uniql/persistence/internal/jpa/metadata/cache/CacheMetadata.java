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
 *     05/16/2008-1.0M8 Guy Pelletier 
 *       - 218084: Implement metadata merging functionality between mapping files
 *     04/27/2010-2.1 Guy Pelletier 
 *       - 309856: MappedSuperclasses from XML are not being initialized properly
 *     03/24/2011-2.3 Guy Pelletier 
 *       - 337323: Multi-tenant with shared schema support (part 1)
 ******************************************************************************/  
package cz.burios.uniql.persistence.internal.jpa.metadata.cache;

import cz.burios.uniql.persistence.annotations.CacheCoordinationType;
import cz.burios.uniql.persistence.annotations.CacheType;
import cz.burios.uniql.persistence.annotations.DatabaseChangeNotificationType;
import cz.burios.uniql.persistence.config.CacheIsolationType;
import cz.burios.uniql.persistence.descriptors.ClassDescriptor;
import cz.burios.uniql.persistence.descriptors.invalidation.DailyCacheInvalidationPolicy;
import cz.burios.uniql.persistence.descriptors.invalidation.TimeToLiveCacheInvalidationPolicy;
import cz.burios.uniql.persistence.exceptions.ValidationException;
import cz.burios.uniql.persistence.internal.jpa.metadata.MetadataDescriptor;
import cz.burios.uniql.persistence.internal.jpa.metadata.ORMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.MetadataAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.objects.MetadataAnnotation;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.objects.MetadataClass;

/**
 * Object to hold onto cache metadata.
 * 
 * Key notes:
 * - any metadata mapped from XML to this class must be compared in the
 *   equals method.
 * - when loading from annotations, the constructor accepts the metadata
 *   accessor this metadata was loaded from. Used it to look up any 
 *   'companion' annotation needed for processing.
 * - methods should be preserved in alphabetical order.
 * 
 * @author Guy Pelletier
 * @since TopLink 11g
 */
public class CacheMetadata extends ORMetadata {
    protected Boolean m_alwaysRefresh;
    protected Boolean m_disableHits;
    protected Boolean m_shared;
    protected String m_isolation;
    protected Boolean m_refreshOnlyIfNewer;
    
    protected String m_coordinationType;
    protected String m_databaseChangeNotificationType;
    protected String m_type;
    
    protected Integer m_expiry;
    protected Integer m_size;
    
    protected TimeOfDayMetadata m_expiryTimeOfDay;

    /**
     * INTERNAL:
     * Used for XML loading.
     */
    public CacheMetadata() {
        super("<cache>");
    }
    
    /**
     * INTERNAL:
     * Used for annotation loading.
     */
    public CacheMetadata(MetadataAnnotation cache, MetadataAccessor accessor) {
        super(cache, accessor);
        
        m_alwaysRefresh = cache.getAttributeBooleanDefaultFalse("alwaysRefresh");
        m_disableHits = cache.getAttributeBooleanDefaultFalse("disableHits");
        m_coordinationType = cache.getAttributeString("coordinationType");
        m_databaseChangeNotificationType = cache.getAttributeString("databaseChangeNotificationType");
        m_expiry = cache.getAttributeInteger("expiry");

        MetadataAnnotation expiryTimeOfDay = cache.getAttributeAnnotation("expiryTimeOfDay");
        
        if (expiryTimeOfDay != null) {
            m_expiryTimeOfDay = new TimeOfDayMetadata(expiryTimeOfDay, accessor);
        }
        
        m_shared = cache.getAttributeBooleanDefaultTrue("shared");
        m_isolation = cache.getAttributeString("isolation");
        m_size = cache.getAttributeInteger("size");
        m_type = cache.getAttributeString("type");
        m_refreshOnlyIfNewer = cache.getAttributeBooleanDefaultFalse("refreshOnlyIfNewer");
    }
    
    /**
     * INTERNAL:
     */
    @Override
    public boolean equals(Object objectToCompare) {
        if (objectToCompare instanceof CacheMetadata) {
            CacheMetadata cache = (CacheMetadata) objectToCompare;
            
            if (! valuesMatch(m_alwaysRefresh, cache.getAlwaysRefresh())) {
                return false;
            }
            
            if (! valuesMatch(m_disableHits, cache.getDisableHits())) {
                return false;
            }

            if (! valuesMatch(m_shared, cache.getShared())) {
                return false;
            }
            
            if (! valuesMatch(m_isolation, cache.getIsolation())) {
                return false;
            }
            
            if (! valuesMatch(m_refreshOnlyIfNewer, cache.getRefreshOnlyIfNewer())) {
                return false;
            }
            
            if (! valuesMatch(m_coordinationType, cache.getCoordinationType())) {
                return false;
            }
            
            if (! valuesMatch(m_databaseChangeNotificationType, cache.getDatabaseChangeNotificationType())) {
                return false;
            }
            
            if (! valuesMatch(m_type, cache.getType())) {
                return false;
            }
            
            if (! valuesMatch(m_expiry, cache.getExpiry())) {
                return false;
            }
            
            if (! valuesMatch(m_size, cache.getSize())) {
                return false;
            }
            
            return valuesMatch(m_expiryTimeOfDay, cache.getExpiryTimeOfDay());
        }
        
        return false;
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public Boolean getAlwaysRefresh() {
        return m_alwaysRefresh; 
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public String getCoordinationType() {
        return m_coordinationType; 
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public Boolean getDisableHits() {
        return m_disableHits; 
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public Integer getExpiry() {
        return m_expiry; 
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public TimeOfDayMetadata getExpiryTimeOfDay() { 
        return m_expiryTimeOfDay;
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public Boolean getRefreshOnlyIfNewer() {
       return m_refreshOnlyIfNewer; 
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public Boolean getShared() {
       return m_shared; 
    }
    
    public String getIsolation(){
        return m_isolation;
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public Integer getSize() {
        return m_size;
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public String getType() {
       return m_type;
    }
    
    /**
     * INTERNAL:
     */
    public void process(MetadataDescriptor descriptor, MetadataClass javaClass) {
        // Set the cache flag on the metadata Descriptor.
        descriptor.setHasCache();
        
        // Process the cache metadata.
        ClassDescriptor classDescriptor = descriptor.getClassDescriptor();
        
        // Process type
        if (m_type == null) {
            // Leave as default.
        } else if (m_type.equals(CacheType.SOFT_WEAK.name())) {
            classDescriptor.useSoftCacheWeakIdentityMap();
        } else if (m_type.equals(CacheType.FULL.name())) {
            classDescriptor.getCachePolicy().useFullIdentityMap();
        } else if (m_type.equals(CacheType.WEAK.name())) {
            classDescriptor.getCachePolicy().useWeakIdentityMap();
        }  else if (m_type.equals(CacheType.SOFT.name())) {
            classDescriptor.getCachePolicy().useSoftIdentityMap();
        } else if (m_type.equals(CacheType.HARD_WEAK.name())) {
            classDescriptor.getCachePolicy().useHardCacheWeakIdentityMap();
        } else if (m_type.equals(CacheType.CACHE.name())) {
            classDescriptor.useCacheIdentityMap();
        } else if (m_type.equals(CacheType.NONE.name())) {
            classDescriptor.getCachePolicy().useNoIdentityMap();
        }
        
        // Process size.
        if (m_size != null) {
            classDescriptor.getCachePolicy().setIdentityMapSize(m_size);
        }
        
        // Process shared.
        if ( (m_shared !=null && !m_shared.booleanValue()) || (m_shared == null && descriptor.getProject().isSharedCacheModeEnableSelective())){
            classDescriptor.getCachePolicy().setCacheIsolation(CacheIsolationType.ISOLATED);
        }
        
        if (m_isolation != null){
            classDescriptor.getCachePolicy().setCacheIsolation(CacheIsolationType.valueOf(m_isolation));
        }
        
        // Process expiry or expiry time of day.
        if (m_expiryTimeOfDay == null) {
            // Expiry time of day is not specified, look for an expiry.
            if (m_expiry != null && m_expiry != -1) {
                classDescriptor.setCacheInvalidationPolicy(new TimeToLiveCacheInvalidationPolicy(m_expiry));
            }
        } else {
            // Expiry time of day is specified, if expiry is also specified, 
            // throw an exception.
            if (m_expiry == null || m_expiry == -1) {
                classDescriptor.setCacheInvalidationPolicy(new DailyCacheInvalidationPolicy(m_expiryTimeOfDay.processHour(), m_expiryTimeOfDay.processMinute(), m_expiryTimeOfDay.processSecond(), m_expiryTimeOfDay.processMillisecond()));
            } else {
                throw ValidationException.cacheExpiryAndExpiryTimeOfDayBothSpecified(javaClass);
            }
        }
        
        // Process always refresh.
        if (m_alwaysRefresh != null) {
            classDescriptor.getCachePolicy().setShouldAlwaysRefreshCache(m_alwaysRefresh);
        }
        
        // Process refresh only if newer.
        if (m_refreshOnlyIfNewer != null) {
            classDescriptor.getCachePolicy().setShouldOnlyRefreshCacheIfNewerVersion(m_refreshOnlyIfNewer);
        }
        
        // Process disable hits.
        if (m_disableHits != null) {
            classDescriptor.getCachePolicy().setShouldDisableCacheHits(m_disableHits);
        }
        
        // Process coordination type.
        if (m_coordinationType == null) {
            // Leave as default.
        } else if (m_coordinationType.equals(CacheCoordinationType.SEND_OBJECT_CHANGES.name())) {
            classDescriptor.getCachePolicy().setCacheSynchronizationType(ClassDescriptor.SEND_OBJECT_CHANGES);
        } else if (m_coordinationType.equals(CacheCoordinationType.INVALIDATE_CHANGED_OBJECTS.name())) {
            classDescriptor.getCachePolicy().setCacheSynchronizationType(ClassDescriptor.INVALIDATE_CHANGED_OBJECTS);
        } else if (m_coordinationType.equals(CacheCoordinationType.SEND_NEW_OBJECTS_WITH_CHANGES.name())) {
            classDescriptor.getCachePolicy().setCacheSynchronizationType(ClassDescriptor.SEND_NEW_OBJECTS_WITH_CHANGES);
        } else if (m_coordinationType.equals(CacheCoordinationType.NONE.name())) {
            classDescriptor.getCachePolicy().setCacheSynchronizationType(ClassDescriptor.DO_NOT_SEND_CHANGES);
        }
        
        // Process database change notification type.
        if (m_databaseChangeNotificationType == null) {
            // Leave as default.
        } else if (m_databaseChangeNotificationType.equals(DatabaseChangeNotificationType.NONE.name())) {
            classDescriptor.getCachePolicy().setDatabaseChangeNotificationType(DatabaseChangeNotificationType.NONE);
        } else if (m_databaseChangeNotificationType.equals(DatabaseChangeNotificationType.INVALIDATE.name())) {
            classDescriptor.getCachePolicy().setDatabaseChangeNotificationType(DatabaseChangeNotificationType.INVALIDATE);
        }
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping 
     */
    public void setAlwaysRefresh(Boolean alwaysRefresh) {
        m_alwaysRefresh = alwaysRefresh;
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public void setCoordinationType(String coordinationType) {
        m_coordinationType = coordinationType; 
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public String getDatabaseChangeNotificationType() {
        return m_databaseChangeNotificationType;
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public void setDatabaseChangeNotificationType(String databaseChangeNotificationType) {
        m_databaseChangeNotificationType = databaseChangeNotificationType; 
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public void setDisableHits(Boolean disableHits) {
        m_disableHits = disableHits; 
    }

    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public void setExpiry(Integer expiry) {
       m_expiry = expiry; 
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public void setExpiryTimeOfDay(TimeOfDayMetadata expiryTimeOfDay) { 
        m_expiryTimeOfDay = expiryTimeOfDay;
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public void setRefreshOnlyIfNewer(Boolean refreshOnlyIfNewer) {
        m_refreshOnlyIfNewer = refreshOnlyIfNewer;
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public void setShared(Boolean shared) {
       m_shared = shared; 
    }
    
    public void setIsolation(String isolation){
        m_isolation = isolation;
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public void setSize(Integer size) {
        m_size = size;
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public void setType(String type) {
       m_type = type;
    }
}