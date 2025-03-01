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

suite("group_concat") {
    sql "SET enable_nereids_planner=true"
    sql "SET enable_fallback_to_original_planner=false"


    test {
        sql """select /*+SET_VAR(disable_nereids_rules='TWO_PHASE_AGGREGATE_WITHOUT_DISTINCT')*/
                 group_concat(cast(number as string), ',' order by number)
               from numbers('number'='10')"""
        result([["0,1,2,3,4,5,6,7,8,9"]])
    }

    test {
        sql """select /*+SET_VAR(disable_nereids_rules='ONE_PHASE_AGGREGATE_WITHOUT_DISTINCT')*/
                 group_concat(cast(number as string), ',' order by number)
               from numbers('number'='10')"""
        result([["0,1,2,3,4,5,6,7,8,9"]])
    }

    test {
        sql "select group_concat(cast(number as string)) from numbers('number'='10')"
        result([["0,1,2,3,4,5,6,7,8,9"]])
    }

    test {
        sql "select group_concat(cast(number as string), ' : ') from numbers('number'='10')"
        result([["0 : 1 : 2 : 3 : 4 : 5 : 6 : 7 : 8 : 9"]])
    }

    test {
        sql "select group_concat(cast(number as string), NULL) from numbers('number'='10')"
        result([[null]])
    }
    
    
}
