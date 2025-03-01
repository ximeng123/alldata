/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.celeborn.server.common

import org.apache.celeborn.common.CelebornConf
import org.apache.celeborn.common.internal.Logging
import org.apache.celeborn.common.metrics.MetricsSystem

abstract class Service extends Logging {
  def serviceName: String

  def conf: CelebornConf

  def metricsSystem: MetricsSystem

  def initialize(): Unit = {
    if (conf.metricsSystemEnable) {
      logInfo(s"Metrics system enabled.")
      metricsSystem.start()
    }
  }

  def close(): Unit = {}

  def shutdown(graceful: Boolean): Unit = {}
}

object Service {
  val MASTER = "master"
  val WORKER = "worker"
}
