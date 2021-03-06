/**
 * ****************************************************************************
 * Copyright (c) 2015 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 * <p/>
 * Contributors:
 *      Marcel Valovy - initial API and implementation
 * ****************************************************************************
 */
package cz.burios.uniql.persistence.internal.cache;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Memoizer for computing resource expensive tasks asynchronously & concurrently.
 *
 * Inspired by D. Lea. <i>JCiP</i>, 2nd edition. Addison-Wesley, 2006. pp.109
 * @author Marcel Valovy
 */
public class Memoizer<A, V> implements LowLevelProcessor<A, V> {

    /** cache */
    private final ConcurrentMap<CacheKey, Future<V>> cache = new ConcurrentHashMap<>();

    @Override
    public V compute(final ComputableTask<A, V> c, final A taskArg) throws InterruptedException {
        final CacheKey key = new CacheKey(c, taskArg);
        while (true) {
            Future<V> f = cache.get(key);
            if (f == null) {
                Callable<V> evaluation = new Callable<V>() {
                    public V call() throws InterruptedException {
                        return c.compute(taskArg);
                    }
                };
                FutureTask<V> ft = new FutureTask<>(evaluation);
                f = cache.putIfAbsent(key, ft);
                if (f == null) {
                    f = ft;
                    ft.run();
                }
            }
            try {
                return f.get();
            } catch (CancellationException e) {
                /*
                 *  "Caching a Future instead of a value creates the possibility of cache pollution: if a computation
                 *  is cancelled or fails, future attempts to compute the results will also indicate cancellation or
                 *  failure. To avoid this, Memoizer removes the Future from the cache if it detects that the
                 *  computation was cancelled."
                 */
                cache.remove(key, f);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Forgets result of the specified task.
     * This allows to manually control size of internal cache.
     *
     * @param task computable task, forming part of result key
     * @param key argument of computation
     */
    public void forget(ComputableTask<A, V> task, A key) {
        cache.remove(this.new CacheKey(task, key));
    }

    /**
     * Forgets all cached results.
     */
    public void forgetAll() {
        cache.clear();
    }

    private class CacheKey {
        private final ComputableTask<A, V> task;
        private final A key;

        private CacheKey(ComputableTask<A, V> task, A key) {
            this.task = task;
            this.key = key;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CacheKey cacheKey = (CacheKey) o;

            //noinspection SimplifiableIfStatement
            if (key != null ? !key.equals(cacheKey.key) : cacheKey.key != null) return false;
            return !(task != null ? !task.equals(cacheKey.task) : cacheKey.task != null);

        }

        @Override
        public int hashCode() {
            int result = task != null ? task.hashCode() : 0;
            result = 31 * result + (key != null ? key.hashCode() : 0);
            return result;
        }
    }
}
