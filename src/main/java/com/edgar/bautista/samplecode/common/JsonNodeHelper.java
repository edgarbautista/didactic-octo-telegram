package com.edgar.bautista.samplecode.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class JsonNodeHelper {

    public static String jsonToTypeTraversal(final JsonNode node) {
        return JsonNodeHelper.jsonToTypeTraversal(node, "");
    }

    public static String jsonToTypeTraversal(final JsonNode node, final String appendName) {
        String namePath = StringUtils.isEmpty(appendName) ? "" : appendName + "::";
        if(node.isArray()) {
            ArrayNode arrayNode = (ArrayNode) node;
            String arrayNested = jsonNodeIteratorLimitDepth(arrayNode.iterator(), appendName, 1);
            return namePath + node.getNodeType().name() + "\n" + arrayNested;
        } else if (node.isValueNode()) {
            return namePath + node.getNodeType().name();
        }
        return JsonNodeHelper.jsonNodeFieldsIterator(node.fields(), appendName);
    }

    public static String jsonNodeFieldsIterator(final Iterator<Map.Entry<String, JsonNode>> nodeFields, final String appendName) {
        return JsonNodeHelper.iteratorToStream(nodeFields)
                .map((entry) -> JsonNodeHelper.jsonToTypeTraversal(entry.getValue(),
                        StringUtils.isEmpty(appendName) ? entry.getKey() : appendName + ":" + entry.getKey()))
                .collect(Collectors.joining("\n"));
    }

    public static String jsonNodeIteratorLimitDepth(final Iterator<JsonNode> nodes, final String appendName, final Integer limit) {
        return JsonNodeHelper.iteratorToStream(nodes)
                .limit(limit)
                .map((node) -> JsonNodeHelper.jsonToTypeTraversal(node, appendName)).collect(Collectors.joining( "\n"));
    }

    public static <T> Stream<T> iteratorToStream(final Iterator<T> it) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                it,
                Spliterator.ORDERED),
                false
        );
    }
}
