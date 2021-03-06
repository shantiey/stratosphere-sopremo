/***********************************************************************************************************************
 *
 * Copyright (C) 2010 by the Stratosphere project (http://stratosphere.eu)
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
package eu.stratosphere.sopremo.expressions;

import java.util.Comparator;

import eu.stratosphere.pact.common.contract.Order;
import eu.stratosphere.sopremo.type.IArrayNode;
import eu.stratosphere.sopremo.type.IJsonNode;
import eu.stratosphere.sopremo.type.IntNode;

public class OrderingExpression extends EvaluationExpression {
	private final Order order;

	private final PathSegmentExpression path;

	private final IntNode result = new IntNode();

	public OrderingExpression(Order order, PathSegmentExpression path) {
		this.order = order;
		this.path = path;
	}

	/**
	 * Initializes OrderingExpression.
	 */
	public OrderingExpression() {
		this(Order.ASCENDING, EvaluationExpression.VALUE);
	}

	/**
	 * Returns the order.
	 * 
	 * @return the order
	 */
	public Order getOrder() {
		return this.order;
	}

	/**
	 * Returns the path.
	 * 
	 * @return the path
	 */
	public PathSegmentExpression getPath() {
		return this.path;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.expressions.EvaluationExpression#evaluate(eu.stratosphere.sopremo.type.IJsonNode)
	 */
	@Override
	public IntNode evaluate(IJsonNode node) {
		@SuppressWarnings("unchecked")
		final IArrayNode<IJsonNode> pair = (IArrayNode<IJsonNode>) node;
		this.result.setValue(compare(pair.get(0), pair.get(1)));
		return this.result;
	}

	public int compare(IJsonNode node1, IJsonNode node2) {
		final int result = this.path.evaluate(node1).compareTo(this.path.evaluate(node2));
		return this.order == Order.DESCENDING ? -result : result;
	}

	public Comparator<IJsonNode> asComparator() {
		return new Comparator<IJsonNode>() {
			/*
			 * (non-Javadoc)
			 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
			 */
			@Override
			public int compare(IJsonNode node1, IJsonNode node2) {
				final int result =
					OrderingExpression.this.path.evaluate(node1).compareTo(OrderingExpression.this.path.evaluate(node2));
				return OrderingExpression.this.order == Order.DESCENDING ? -result : result;
			}
		};
	}
}
