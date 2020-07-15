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
package org.jetbrains.kotlinx.lincheck.test.transformer

import org.jetbrains.kotlinx.lincheck.LinChecker
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.strategy.modelchecking.ModelCheckingCTest
import org.jetbrains.kotlinx.lincheck.verifier.VerifierState
import org.junit.Test

/**
 * Checks that System.nanoTime() and System.currentTimeMillis() are made deterministic by the transformer.
 */
@ModelCheckingCTest(iterations = 50, invocationsPerIteration = 1000)
class TimeStubTest : VerifierState() {
    @Volatile
    private var a: Any = Any()

    @Operation
    fun nanoTime() {
        if (System.nanoTime() % 3L == 2L) {
            // just add some code locations
            a = Any()
            a = Any()
        }
    }

    @Operation
    fun currentTimeMillis() {
        if (System.currentTimeMillis() % 3L == 2L) {
            // just add some code locations
            a = Any()
            a = Any()
        }
    }

    @Test
    fun test() {
        LinChecker.check(this::class.java)
    }

    override fun extractState(): Any = 543

}
