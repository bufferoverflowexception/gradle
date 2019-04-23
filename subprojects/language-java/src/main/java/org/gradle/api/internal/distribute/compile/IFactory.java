package org.gradle.api.internal.distribute.compile;

import java.util.Set;

/**
 * Created by nls on 2019/4/21. Nothing.
 */
public interface IFactory {

    DispatchTask create(String file, Set<String> classes);
}