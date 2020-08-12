package org.jetbrains.kotlinx.lincheck.strategy.managed.randomswitch;

/*
 * #%L
 * Lincheck
 * %%
 * Copyright (C) 2015 - 2018 Devexperts, LLC
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

import org.jetbrains.kotlinx.lincheck.execution.*;
import org.jetbrains.kotlinx.lincheck.strategy.*;
import org.jetbrains.kotlinx.lincheck.strategy.managed.ManagedCTestConfiguration;
import org.jetbrains.kotlinx.lincheck.strategy.managed.ManagedStrategyGuarantee;
import org.jetbrains.kotlinx.lincheck.verifier.Verifier;

import java.lang.reflect.*;
import java.util.*;

/**
 * Configuration for {@link RandomSwitchStrategy random-switch} strategy.
 */
public class RandomSwitchCTestConfiguration extends ManagedCTestConfiguration {
    public RandomSwitchCTestConfiguration(Class<?> testClass, int iterations, int threads, int actorsPerThread, int actorsBefore,
        int actorsAfter, Class<? extends ExecutionGenerator> generatorClass, Class<? extends Verifier> verifierClass,
        boolean checkObstructionFreedom, int hangingDetectionThreshold,  int invocationsPerIteration, List<ManagedStrategyGuarantee> guarantees,
        boolean requireStateEquivalenceCheck, boolean minimizeFailedScenario,  Class<?> sequentialSpecification, long timeoutMs,
        boolean eliminateLocalObjects, RecoverableMode mode)
    {
        super(testClass, iterations, threads, actorsPerThread, actorsBefore, actorsAfter, generatorClass, verifierClass,
                checkObstructionFreedom, hangingDetectionThreshold, invocationsPerIteration, guarantees,
                requireStateEquivalenceCheck, minimizeFailedScenario, sequentialSpecification, timeoutMs, eliminateLocalObjects, mode);
    }

    @Override
    protected Strategy createStrategy(Class<?> testClass, ExecutionScenario scenario, List<Method> validationFunctions,
                                      Method stateRepresentation, Verifier verifier) {
        return new RandomSwitchStrategy(this, testClass, scenario, validationFunctions, stateRepresentation, verifier);
    }
}