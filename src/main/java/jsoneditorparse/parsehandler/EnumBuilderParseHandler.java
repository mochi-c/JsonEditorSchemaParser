package jsoneditorparse.parsehandler;

import com.google.common.collect.Lists;
import jsoneditorparse.JsonSchemaParseException;
import jsoneditorparse.annotation.JsonEditorEnumBuilder;
import jsoneditorparse.formateutil.IEnumItemBuilder;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * User: Mochi
 * version: 1.0
 */
public class EnumBuilderParseHandler extends AbstractParseHandler {

    @Override
    public void handle() throws JsonSchemaParseException {
        List<String> enums = Lists.newArrayList();
        List<String> titles = Lists.newArrayList();
        if (getClazz().isEnum()) {
            enums = buildFromEnum(getClazz());
        } else {
            for (Map.Entry<String, String> x : buildFromAnnotation(getField()).entrySet()) {
                enums.add(x.getKey());
                titles.add(x.getValue());
            }
        }
        getResult().put("enum", enums);
        addHandlerFirst(new EnumsTitleParseHandler(titles));
    }

    List<String> buildFromEnum(Class enumClazz) {
        List<String> lists = Lists.newArrayList();
        if (enumClazz.getEnumConstants() == null) {
            return lists;
        }
        for (Object x : enumClazz.getEnumConstants()) {
            lists.add(x.toString());
        }
        return lists;
    }

    Map<String, String> buildFromAnnotation(Field field) throws JsonSchemaParseException {
        Class<? extends IEnumItemBuilder> builderClass = null;
        try {
            IEnumItemBuilder builder = field.getAnnotation(JsonEditorEnumBuilder.class).itemsBuilder().newInstance();
            return builder.getItems();
        } catch (Exception e) {
            throw new JsonSchemaParseException("build enum item error: " + e.getMessage(), e);
        }
    }
}
