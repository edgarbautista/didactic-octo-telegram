package com.edgar.bautista.samplecode.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Helper {

    public static String jsonToTypeTraversal(final JsonNode node) {
        return Helper.jsonToTypeTraversal(node, "");
    }

    public static String jsonToTypeTraversal(final JsonNode node, final String appendName) {
        if(node.isArray()) {
            ArrayNode arrayNode = (ArrayNode) node;
            String arrayNested = jsonNodeIteratorLimitDepth(arrayNode.iterator(), appendName, 1);
            return appendName + "::" + node.getNodeType().name() + "\n" + arrayNested;
        } else if (node.isValueNode()) {
            return appendName + "::" + node.getNodeType().name();
        }
        return Helper.jsonNodeFieldsIterator(node.fields(), appendName);
    }

    public static String jsonTraversalWithFields(final JsonNode node, final String appendName) {
        return Helper.jsonToTypeTraversal(node, appendName);
    }

    public static String jsonNodeFieldsIterator(final Iterator<Map.Entry<String, JsonNode>> nodeFields, final String appendName) {
        return Helper.iteratorToStream(nodeFields)
                .map((entry) -> Helper.jsonTraversalWithFields(entry.getValue(),
                        StringUtils.isEmpty(appendName) ? entry.getKey() : appendName + ":" + entry.getKey()))
                .collect(Collectors.joining("\n"));
    }

    public static String jsonNodeIteratorLimitDepth(final Iterator<JsonNode> nodes, final String appendName, final Integer limit) {
        return Helper.iteratorToStream(nodes)
                .limit(limit)
                .map((node) -> Helper.jsonToTypeTraversal(node, appendName)).collect(Collectors.joining( "\n"));
    }

    public static <T> Stream<T> iteratorToStream(final Iterator<T> it) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                it,
                Spliterator.ORDERED),
                false
        );
    }
}
