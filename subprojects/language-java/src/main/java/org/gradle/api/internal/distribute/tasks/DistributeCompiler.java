
package org.gradle.api.internal.distribute.tasks;

import org.gradle.language.base.internal.compile.Compiler;
import org.gradle.api.internal.tasks.compile.JavaCompileSpec;
import org.gradle.api.internal.tasks.compile.incremental.jar.JarClasspathSnapshot;
import org.gradle.api.internal.tasks.compile.incremental.jar.PreviousCompilation;
import org.gradle.api.internal.tasks.compile.incremental.jar.JarClasspathSnapshotProvider;
import org.gradle.api.internal.tasks.compile.incremental.RecompilationSpecProvider;
import org.gradle.api.internal.tasks.compile.incremental.recomp.RecompilationSpec;
import org.gradle.api.tasks.incremental.IncrementalTaskInputs;
import org.gradle.api.tasks.WorkResult;
import org.gradle.internal.time.Time;
import org.gradle.internal.time.Timer;

/**
 * Created by nls on 2019/4/21. Nothing.
 */
public class DistributeCompiler implements Compiler<JavaCompileSpec> {

    private final IncrementalTaskInputs inputs;
    private final Compiler<JavaCompileSpec> delegate;
    private final PreviousCompilation previousCompilation;
    private final RecompilationSpecProvider recompilationSpecProvider;
    private final JarClasspathSnapshotProvider jarClasspathSnapshotProvider;

    public DistributeCompiler(IncrementalTaskInputs inputs, Compiler<JavaCompileSpec> delegate,
            PreviousCompilation previousCompilation, JarClasspathSnapshotProvider jarClasspathSnapshotProvider,
            RecompilationSpecProvider recompilationSpecProvider) {
        this.inputs = inputs;
        this.delegate = delegate;
        this.previousCompilation = previousCompilation;
        this.recompilationSpecProvider = recompilationSpecProvider;
        this.jarClasspathSnapshotProvider = jarClasspathSnapshotProvider;
    }

    @Override
    public WorkResult execute(JavaCompileSpec spec) {
        Timer clock = Time.startTimer();
        JarClasspathSnapshot jarClasspathSnapshot = jarClasspathSnapshotProvider
                .getJarClasspathSnapshot(spec.getCompileClasspath());
        RecompilationSpec recompilationSpec = recompilationSpecProvider.provideRecompilationSpec(inputs,
                previousCompilation, jarClasspathSnapshot);

        return delegate.execute(spec);
    }

}
