package jsoneditorparse.parsehandler;

import com.alibaba.fastjson.JSONObject;
import jsoneditorparse.Context;
import jsoneditorparse.JsonSchemaParseException;
import jsoneditorparse.annotation.JsonEditorUIMeta;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;

/**
 * Description:
 * User: Mochi
 * version: 1.0
 */
@Getter
public abstract class AbstractParseHandler {

    /**
     * 执行解析的上下文
     */
    @Setter
    private Context context;

    void addHandlerFirst(AbstractParseHandler handler) {
        context.getHandlerQueue().addFirst(handler);
    }

    void addHandlerLast(AbstractParseHandler handler) {
        context.getHandlerQueue().addLast(handler);
    }

    JSONObject getResult() {
        return context.getResult();
    }


    /**
     * 执行解析
     */
    abstract public void handle() throws JsonSchemaParseException;

    protected Class getClazz() {
        return context.getClazz();
    }

    protected boolean isClearGenericsClazz() {
        return context.isClearGenericsClazz();
    }

    protected JsonEditorUIMeta getConfigEditorUIMeta() {
        return context.getJsonEditorUIMeta();
    }

    protected Field getField() {
        return context.getField();
    }
}
