/*-
 * #%L
 * Lincheck
 * %%
 * Copyright (C) 2019 - 2020 JetBrains s.r.o.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */
package org.jetbrains.kotlinx.lincheck.strategy.modelchecking;

import org.jetbrains.kotlinx.lincheck.Options;

import java.util.ArrayList;
import java.util.List;

import static org.jetbrains.kotlinx.lincheck.UtilsKt.chooseSequentialSpecification;
import static org.jetbrains.kotlinx.lincheck.strategy.modelchecking.ModelCheckingCTestConfiguration.*;

/**
 * Options for {@link ModelCheckingStrategy uniform search} strategy.
 */
public class ModelCheckingOptions extends Options<ModelCheckingOptions, ModelCheckingCTestConfiguration> {
    protected boolean checkObstructionFreedom = DEFAULT_CHECK_OBSTRUCTION_FREEDOM;
    protected int hangingDetectionThreshold = DEFAULT_HANGING_DETECTION_THRESHOLD;
    protected int maxInvocationsPerIteration = DEFAULT_INVOCATIONS;
    protected List<String> ignoredEntryPoints = new ArrayList<>(DEFAULT_IGNORED_ENTRY_POINTS);

    /**
     * Check obstruction freedom of the concurrent algorithm
     */
    public ModelCheckingOptions checkObstructionFreedom(boolean checkObstructionFreedom) {
        this.checkObstructionFreedom = checkObstructionFreedom;
        return this;
    }

    /**
     * Use the specified maximum number of repetitions to detect loops for checking concurrent guarantee
     */
    public ModelCheckingOptions hangingDetectionThreshold(int maxRepetitions) {
        this.hangingDetectionThreshold = maxRepetitions;
        return this;
    }

    /**
     * Number of maxInvocationsPerIteration that managed strategy may use to search for incorrect execution
     */
    public ModelCheckingOptions invocationsPerIteration(int invocationsPerIteration) {
        this.maxInvocationsPerIteration = invocationsPerIteration;
        return this;
    }

    /**
     * Add an entry point which should not be transformed in the format "java.util.concurrent." or "java.util.WeakHashMap"
     */
    public ModelCheckingOptions addIgnoredEntryPoint(String ignoredEntryPoint) {
        this.ignoredEntryPoints.add(ignoredEntryPoint.replace(".", "/"));
        return this;
    }

    @Override
    public ModelCheckingCTestConfiguration createTestConfigurations(Class<?> testClass) {
        return new ModelCheckingCTestConfiguration(testClass, iterations, threads, actorsPerThread, actorsBefore, actorsAfter,
                executionGenerator, verifier, checkObstructionFreedom, hangingDetectionThreshold, maxInvocationsPerIteration,
                ignoredEntryPoints, requireStateEquivalenceImplementationCheck, minimizeFailedScenario,
                chooseSequentialSpecification(sequentialSpecification, testClass));
    }
}
