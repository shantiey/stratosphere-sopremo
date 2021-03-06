package eu.stratosphere.sopremo.expressions;

import static eu.stratosphere.sopremo.type.JsonUtil.createArrayNode;
import static eu.stratosphere.sopremo.type.JsonUtil.createObjectNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

import eu.stratosphere.sopremo.type.IJsonNode;
import eu.stratosphere.sopremo.type.IntNode;
import eu.stratosphere.sopremo.type.ObjectNode;
import eu.stratosphere.sopremo.type.TextNode;

public class ObjectCreationTest extends EvaluableExpressionTest<ObjectCreation> {

	@Override
	protected ObjectCreation createDefaultInstance(final int index) {
		return new ObjectCreation(new ObjectCreation.FieldAssignment(String.valueOf(index), new ConstantExpression(
			IntNode.valueOf(index))));
	}

	@Test
	public void shouldCreateObjectAsIntended() {
		final IJsonNode result = new ObjectCreation(new ObjectCreation.FieldAssignment("name", new ConstantExpression(
			TextNode.valueOf("testperson"))), new ObjectCreation.FieldAssignment("age", new ConstantExpression(
			IntNode.valueOf(30)))).evaluate(IntNode.valueOf(0));

		Assert.assertEquals(createObjectNode("name", "testperson", "age", 30), result);
	}

	@Test
	public void shouldReturnSpecifiedMapping() {
		final ObjectCreation.Mapping<?> mapping = new ObjectCreation(new ObjectCreation.FieldAssignment("name",
			new ConstantExpression(
				TextNode.valueOf("testperson"))), new ObjectCreation.FieldAssignment("age", new ConstantExpression(
			IntNode.valueOf(30)))).getMapping(1);

		Assert.assertEquals(new ObjectCreation.FieldAssignment("age", new ConstantExpression(IntNode.valueOf(30))),
			mapping);
	}

	@Test
	public void shouldAddMappings() {

		final ObjectCreation object = new ObjectCreation(new ObjectCreation.FieldAssignment("name",
			new ConstantExpression(
				TextNode.valueOf("testperson"))), new ObjectCreation.FieldAssignment("age", new ConstantExpression(
			IntNode.valueOf(30))));

		object.addMapping("birthday", new ConstantExpression(TextNode.valueOf("01.01.2000")));

		final IJsonNode result = object.evaluate(IntNode.valueOf(0));

		Assert.assertEquals(createObjectNode("name", "testperson", "age", 30, "birthday", "01.01.2000"), result);
	}

	@Test
	public void shouldEvaluateMappings() {
		final ObjectCreation.FieldAssignment mapping = new ObjectCreation.FieldAssignment("testname",
			new InputSelection(0));
		final ObjectNode result = createObjectNode("fieldname", "test");

		mapping.evaluate(createArrayNode("1", "2"), result);
		mapping.evaluate(createArrayNode("3", "4"), result);

		Assert.assertEquals(createObjectNode("fieldname", "test", "testname", "3"), result);
	}

	@Test
	public void shouldReuseTargetIfCorrectType() {
		final ObjectCreation object = new ObjectCreation(new ObjectCreation.FieldAssignment("name",
			new ConstantExpression(
				TextNode.valueOf("testperson"))), new ObjectCreation.FieldAssignment("age", new ConstantExpression(
			IntNode.valueOf(30))));

		final IJsonNode result1 = object.evaluate(IntNode.valueOf(0));
		final IJsonNode result2 = object.evaluate(IntNode.valueOf(1));

		Assert.assertSame(result1, result2);
	}

	@Override
	protected void initVerifier(final EqualsVerifier<ObjectCreation> equalVerifier) {
		super.initVerifier(equalVerifier);
		equalVerifier.withPrefabValues(List.class,
			new ArrayList<Object>(),
			new ArrayList<Object>(Arrays.asList(new ObjectCreation.CopyFields(new ObjectAccess("field")))));
	}
}
