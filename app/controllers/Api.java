package controllers;

import global.Registry;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import play.Logger;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http.RequestBody;
import play.mvc.Result;

import com.github.ddth.commons.utils.DPathUtils;
import com.github.ddth.commons.utils.SerializationUtils;
import com.github.ddth.tsc.ICounter;

public class Api extends Controller {

    private static Result doResponse(int status, Object message) {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("status", status);
        if (message != null) {
            response.put("message", message);
        }
        return ok(SerializationUtils.toJsonString(response));
    }

    private final static int MAX_LENGTH = 100;

    @SuppressWarnings("unchecked")
    private static Result validateRequest(RequestBody requestBody, Map<String, Object> paramStorage) {
        if (requestBody.isMaxSizeExceeded()) {
            return doResponse(400, "Request is too large!");
        }

        Map<String, Object> params = null;
        try {
            String jsonString = new String(requestBody.asRaw().asBytes(), "UTF-8");
            params = SerializationUtils.fromJsonString(jsonString, Map.class);
        } catch (Exception e) {
            params = null;
        }
        if (params == null) {
            return doResponse(400, "Post data must be a Map in Json format!");
        }

        paramStorage.putAll(params);

        return null;
    }

    @BodyParser.Of(value = BodyParser.Raw.class, maxLength = MAX_LENGTH)
    public static Result add() {
        response().setContentType("application/json");

        try {
            Map<String, Object> params = new HashMap<String, Object>();
            RequestBody requestBody = request().body();
            Result result = validateRequest(requestBody, params);
            if (result != null) {
                return result;
            }

            String counterName = DPathUtils.getValue(params, "c", String.class);
            if (StringUtils.isBlank(counterName)) {
                return doResponse(400, "Invalid of missing counter name!");
            }
            Long value = DPathUtils.getValue(params, "v", Long.class);
            if (value == null) {
                value = 1L;
            }
            Long timestamp = DPathUtils.getValue(params, "t", Long.class);
            if (timestamp == null || timestamp.longValue() < 1) {
                timestamp = System.currentTimeMillis();
            }

            ICounter counter = Registry.getCounter(counterName);
            counter.add(timestamp.longValue(), value.longValue());

            return ok("{\"status\":200,\"message\":\"Ok\"}");
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
            return doResponse(500, e.getMessage());
        }
    }

    @BodyParser.Of(value = BodyParser.Raw.class, maxLength = MAX_LENGTH)
    public static Result set() {
        response().setContentType("application/json");

        try {
            Map<String, Object> params = new HashMap<String, Object>();
            RequestBody requestBody = request().body();
            Result result = validateRequest(requestBody, params);
            if (result != null) {
                return result;
            }

            String counterName = DPathUtils.getValue(params, "c", String.class);
            if (StringUtils.isBlank(counterName)) {
                return doResponse(400, "Invalid of missing counter name!");
            }
            Long value = DPathUtils.getValue(params, "v", Long.class);
            if (value == null) {
                return doResponse(400, "Invalid of missing counter value!");
            }
            Long timestamp = DPathUtils.getValue(params, "t", Long.class);
            if (timestamp == null || timestamp.longValue() < 1) {
                timestamp = System.currentTimeMillis();
            }

            ICounter counter = Registry.getCounter(counterName);
            counter.set(timestamp.longValue(), value.longValue());

            return ok("{\"status\":200,\"message\":\"Ok\"}");
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
            return doResponse(500, e.getMessage());
        }
    }
}
