/*
 * Copyright 2014 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.p.s.pinpoint.dao;


import com.p.s.pinpoint.model.Application;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author netspider
 * 
 */
public interface ApplicationIndexDao {
    List<Application> selectAllApplicationNames();

    Application selectApplication(String appName);

    List<Application> selectApplication(List<String> appNames);

    List<String> selectAgentIds(String applicationName);

    void deleteApplicationName(String applicationName);

    void deleteAgentIds(Map<String, List<String>> applicationAgentIdMap);

    void deleteAgentId(String applicationName, String agentId);
}
