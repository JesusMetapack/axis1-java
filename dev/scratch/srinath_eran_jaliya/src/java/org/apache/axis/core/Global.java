/*
 * Copyright 2001-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.axis.core;

import org.apache.axis.core.registry.EngineElement;
import org.apache.axis.core.registry.FlowInclude;
import org.apache.axis.core.registry.Module;
import org.apache.axis.core.registry.ModuleInclude;
import org.apache.axis.core.registry.TypeMappingInclude;
/**
 * Executes the Handlers that should be executed  for every web Service and
 * Contians the all global elements.   
 */
public interface Global extends FlowInclude,EngineElement,
    TypeMappingInclude,ModuleInclude{
    public int getModuleCount();
    public Module getModule(int index);
}