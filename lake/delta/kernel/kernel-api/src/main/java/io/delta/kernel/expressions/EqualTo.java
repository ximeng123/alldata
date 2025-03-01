/*
 * Copyright (2023) The Delta Lake Project Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.delta.kernel.expressions;

/**
 * Evaluates {@code expr1} = {@code expr2} for {@code new EqualTo(expr1, expr2)}.
 */
public final class EqualTo extends BinaryComparison implements Predicate {

    public EqualTo(Expression left, Expression right) {
        super(left, right, "=");
    }

    @Override
    protected Object nullSafeEval(Object leftResult, Object rightResult) {
        return compare(leftResult, rightResult) == 0;
    }
}

