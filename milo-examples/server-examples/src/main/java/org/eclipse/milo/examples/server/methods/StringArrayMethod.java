package org.eclipse.milo.examples.server.methods;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.api.methods.AbstractMethodInvocationHandler;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringArrayMethod extends AbstractMethodInvocationHandler{

    public static final Argument array = new Argument(
            "array",
            Identifiers.String,
            ValueRanks.OneDimension,
            new UInteger[10],
            new LocalizedText("Arr value.")
    );
    public static final Argument concat = new Argument(
            "concat",
            Identifiers.String,
            ValueRanks.Scalar,
            null,
            new LocalizedText("concatenation elements array")
    );

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public StringArrayMethod(UaMethodNode node) {
        super(node);
    }

    @Override
    public Argument[] getInputArguments() {
        return new Argument[]{array};
    }

    @Override
    public Argument[] getOutputArguments() {
        return new Argument[]{concat};
    }

    @Override
    protected Variant[] invoke(AbstractMethodInvocationHandler.InvocationContext invocationContext, Variant[] inputValues) {

        logger.info("Array string = {}", inputValues[0].getValue());
        String[] arr = (String[]) inputValues[0].getValue();

        String concat = "";
        for (String s : arr) {
            concat += s;
        }
        logger.info("Concatenation string = {}", concat);
        return new Variant[]{new Variant(concat)};
    }

}
