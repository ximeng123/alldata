// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.apache.doris.nereids.trees.plans.commands;

import org.apache.doris.nereids.memo.GroupExpression;
import org.apache.doris.nereids.properties.LogicalProperties;
import org.apache.doris.nereids.trees.expressions.Expression;
import org.apache.doris.nereids.trees.expressions.Slot;
import org.apache.doris.nereids.trees.plans.AbstractPlan;
import org.apache.doris.nereids.trees.plans.Plan;
import org.apache.doris.nereids.trees.plans.PlanType;
import org.apache.doris.nereids.trees.plans.logical.LogicalPlan;
import org.apache.doris.qe.ConnectContext;
import org.apache.doris.qe.StmtExecutor;
import org.apache.doris.statistics.Statistics;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

/**
 * All DDL and DML commands' super class.
 */
public abstract class Command extends AbstractPlan implements LogicalPlan {

    public Command(PlanType type, Plan... children) {
        super(type, children);
    }

    public Command(PlanType type, Optional<LogicalProperties> optLogicalProperties, Plan... children) {
        super(type, optLogicalProperties, children);
    }

    public Command(PlanType type, Optional<GroupExpression> groupExpression,
            Optional<LogicalProperties> optLogicalProperties,
            @Nullable Statistics statistics,
            Plan... children) {
        super(type, groupExpression, optLogicalProperties, statistics, children);
    }

    public void run(ConnectContext ctx, StmtExecutor executor) throws Exception {
        // all command should impl this interface.
    }

    @Override
    public Optional<GroupExpression> getGroupExpression() {
        throw new RuntimeException("Command do not implement getGroupExpression");
    }

    @Override
    public List<Plan> children() {
        throw new RuntimeException("Command do not implement children");
    }

    @Override
    public Plan child(int index) {
        throw new RuntimeException("Command do not implement child");
    }

    @Override
    public int arity() {
        throw new RuntimeException("Command do not implement arity");
    }

    @Override
    public Plan withChildren(List<Plan> children) {
        throw new RuntimeException("Command do not implement withChildren");
    }

    @Override
    public PlanType getType() {
        throw new RuntimeException("Command do not implement getType");
    }

    @Override
    public List<? extends Expression> getExpressions() {
        throw new RuntimeException("Command do not implement getExpressions");
    }

    @Override
    public LogicalProperties getLogicalProperties() {
        throw new RuntimeException("Command do not implement getLogicalProperties");
    }

    @Override
    public boolean canBind() {
        throw new RuntimeException("Command do not implement canResolve");
    }

    @Override
    public List<Slot> getOutput() {
        throw new RuntimeException("Command do not implement getOutput");
    }

    @Override
    public String treeString() {
        throw new RuntimeException("Command do not implement treeString");
    }

    @Override
    public Plan withGroupExpression(Optional<GroupExpression> groupExpression) {
        throw new RuntimeException("Command do not implement withGroupExpression");
    }

    @Override
    public Plan withLogicalProperties(Optional<LogicalProperties> logicalProperties) {
        throw new RuntimeException("Command do not implement withLogicalProperties");
    }
}
