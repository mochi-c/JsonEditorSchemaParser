package jsoneditorparse.parsehandler;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Optional;
import jsoneditorparse.annotation.ConfigEditorDateTimeSelector;

/**
 * Description:
 * User: Mochi
 * version: 1.0
 */
public class DateSelectorParseHandler extends AbstractParseHandler {
    @Override
    public void handle() {
        JSONObject options = Optional.fromNullable(getResult().getJSONObject("options")).or(new JSONObject());
        options.put("flatpickr", getFlatPickerConfigs());
        getResult().put("options", options);
    }

    private JSONObject getFlatPickerConfigs() {
        ConfigEditorDateTimeSelector annotation = getField().getAnnotation(ConfigEditorDateTimeSelector.class);
        JSONObject config = new JSONObject();
        if (annotation != null) {
            config.put("dateFormat", annotation.resultFormat());
        } else {
            config.put("dateFormat", "Y-m-d");
        }
        config.put("defaultDate", "today");
        return config;
    }
}