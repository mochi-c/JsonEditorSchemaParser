package jsoneditorparse;

import jsoneditorparse.annotation.ConfigEditorUIMeta;
import jsoneditorparse.fieldfilter.IFieldFilter;
import jsoneditorparse.parsehandler.DesParseHandler;
import jsoneditorparse.parsehandler.FormatDispatcherParseHandler;
import jsoneditorparse.parsehandler.GuideParseHandler;
import jsoneditorparse.parsehandler.TitleParseHandler;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Description:
 * User: Mochi
 * version: 1.0
 */
public class JsonEditorParserBuilder {

    public static SchemaParser create(Class<?> clazz) {
        SchemaParser schemaParser = new SchemaParser(clazz);
        baseHandler(schemaParser);
        return schemaParser;
    }

    public static SchemaParser SimpleParser(Field field, boolean clearGenericsClass, Config config) {
        SchemaParser schemaParser = new SchemaParser(field, clearGenericsClass, config);
        baseHandler(schemaParser);
        return schemaParser;
    }

    private static void baseHandler(SchemaParser schemaParser) {
        schemaParser.addHandlerLast(new FormatDispatcherParseHandler());  //format可能覆盖type
        schemaParser.addHandlerLast(new GuideParseHandler());
        schemaParser.addHandlerLast(new DesParseHandler());
        schemaParser.addHandlerLast(new TitleParseHandler());
    }

}