/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.test.junit4;

import org.apache.camel.CamelContext;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration 
@ActiveProfiles("test")
@RunWith(CamelSpringJUnit4ClassRunner.class)
public class CamelSpringActiveProfileTest {
    @Autowired
    protected CamelContext camelContext;
    @Produce(uri = "direct:start", context = "camelContext")
    protected ProducerTemplate start;
 
    @Test
    public void testLoadActiveProfile() throws InterruptedException {
        MockEndpoint mock = camelContext.getEndpoint("mock:test", MockEndpoint.class);
        mock.expectedBodiesReceived("Hello World");
        start.sendBody("World");
        mock.assertIsSatisfied();
    }

}
