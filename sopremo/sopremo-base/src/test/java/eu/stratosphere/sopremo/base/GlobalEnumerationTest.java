/***********************************************************************************************************************
 *
 * Copyright (C) 2010-2013 by the Stratosphere project (http://stratosphere.eu)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 **********************************************************************************************************************/
package eu.stratosphere.sopremo.base;

import org.junit.Ignore;

//import eu.stratosphere.sopremo.testing.SopremoTestPlan;

/**
 * @author Arvid Heise
 */
@Ignore
public class GlobalEnumerationTest {

	// TODO mleich: re-enable tests!
	// @Test
	// public void shouldEnumerate() {
	// final GlobalEnumeration globalEnumeration = new GlobalEnumeration();
	// SopremoTestPlan plan = new SopremoTestPlan(globalEnumeration);
	// for (int index = 0; index < 5; index++)
	// plan.getInput(0).addObject("text", "value");
	// plan.run();
	//
	// @SuppressWarnings({ "unchecked", "rawtypes" })
	// final List<IObjectNode> results = (List) plan.getActualOutput(0).getAllNodes();
	// Assert.assertEquals(5, results.size());
	// for (IObjectNode result : results)
	// Assert.assertEquals(TextNode.valueOf("value"), result.get("text"));
	//
	// for (int index1 = 0; index1 < results.size(); index1++)
	// for (int index2 = index1 + 1; index2 < results.size(); index2++)
	// Assert.assertFalse("different ids expected", results.get(index1)
	// .get(globalEnumeration.getIdFieldName()).equals(
	// results.get(index2).get(globalEnumeration.getIdFieldName())));
	// }
}
