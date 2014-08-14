package controllers;

import global.Registry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import play.Logger;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http.RequestBody;
import play.mvc.Result;

import com.github.ddth.commons.utils.DPathUtils;
import com.github.ddth.commons.utils.SerializationUtils;
import com.github.ddth.plommon.utils.PlayAppUtils;

public class Api extends Controller {

    private static Result doResponse(int status, Object message) {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("status", status);
        if (message != null) {
            response.put("message", message);
        }
        return ok(SerializationUtils.toJsonString(response));
    }

    private final static int MAX_LENGTH = 1024;

    @SuppressWarnings("unchecked")
    private static Result validateRequest(RequestBody requestBody, Map<String, Object> paramStorage) {
        if (requestBody.isMaxSizeExceeded()) {
            return doResponse(400, "Request is too large! Maximum allowed size: " + MAX_FORWARDS
                    + " bytes");
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

    /**
     * No-op
     * 
     * @return
     */
    public static Result ping() {
        response().setContentType("application/json");
        return ok("{\"status\":200,\"message\":\"Ok\"}");
    }

    /**
     * Returns API server's version information.
     * 
     * @return
     */
    public static Result version() {
        response().setContentType("application/json");
        return doResponse(200, PlayAppUtils.appConfigString("app.version"));
    }

    /**
     * Adds value to a counter.
     * 
     * <p>
     * Parameters:
     * <ul>
     * <li>{@code c}: [String] counter name.</li>
     * <li>{@code v}: (optional)[Long] value to add, default value is {@code 1}.
     * </li>
     * <li>{@code t}: (optional)[Long] UNIX-timestamp (in millisec) to add,
     * default value is current timestamp at server.</li>
     * </ul>
     * </p>
     * 
     * @return
     */
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
                return doResponse(400, "Invalid or missing counter name!");
            }
            Long value = DPathUtils.getValue(params, "v", Long.class);
            if (value == null) {
                value = 1L;
            }
            Long timestamp = DPathUtils.getValue(params, "t", Long.class);
            if (timestamp == null || timestamp.longValue() < 1) {
                timestamp = System.currentTimeMillis();
            }

            if (Registry.api.add(counterName, value.longValue(), timestamp.longValue())) {
                return ok("{\"status\":200,\"message\":\"Ok\"}");
            } else {
                return ok("{\"status\":304,\"message\":\"Counter not exist or not configured!\"}");
            }
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
            return doResponse(500, e.getMessage());
        }
    }

    /**
     * Sets value to a counter.
     * 
     * <p>
     * Parameters:
     * <ul>
     * <li>{@code c}: [String] counter name.</li>
     * <li>{@code v}: [Long] value to set.</li>
     * <li>{@code t}: (optional)[Long] UNIX-timestamp (in millisec) to add,
     * default value is current timestamp at server.</li>
     * </ul>
     * </p>
     * 
     * @return
     */
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
                return doResponse(400, "Invalid or missing counter name!");
            }
            Long value = DPathUtils.getValue(params, "v", Long.class);
            if (value == null) {
                return doResponse(400, "Invalid or missing counter value!");
            }
            Long timestamp = DPathUtils.getValue(params, "t", Long.class);
            if (timestamp == null || timestamp.longValue() < 1) {
                timestamp = System.currentTimeMillis();
            }

            if (Registry.api.set(counterName, value.longValue(), timestamp.longValue())) {
                return ok("{\"status\":200,\"message\":\"Ok\"}");
            } else {
                return ok("{\"status\":304,\"message\":\"Counter not exist or not configured!\"}");
            }
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
            return doResponse(500, e.getMessage());
        }
    }

    /**
     * Tags a counter.
     * 
     * <p>
     * Parameters:
     * <ul>
     * <li>{@code c}: [String] counter name.</li>
     * <li>{@code tags}: [Array of String] list of tag names.</li>
     * </ul>
     * </p>
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    @BodyParser.Of(value = BodyParser.Raw.class, maxLength = MAX_LENGTH)
    public static Result tag() {
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
                return doResponse(400, "Invalid or missing counter name!");
            }
            List<String> tags = DPathUtils.getValue(params, "tags", List.class);
            if (tags == null || tags.size() == 0) {
                return doResponse(400, "Invalid or missing list of tags!");
            }

            if (Registry.api.tag(counterName, tags.toArray(ArrayUtils.EMPTY_STRING_ARRAY))) {
                return ok("{\"status\":200,\"message\":\"Ok\"}");
            } else {
                return ok("{\"status\":304,\"message\":\"Counter not exist or not configured!\"}");
            }
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
            return doResponse(500, e.getMessage());
        }
    }

    /**
     * Untags a counter.
     * 
     * <p>
     * Parameters:
     * <ul>
     * <li>{@code c}: [String] counter name.</li>
     * <li>{@code tags}: [Array of String] list of tag names.</li>
     * </ul>
     * </p>
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    @BodyParser.Of(value = BodyParser.Raw.class, maxLength = MAX_LENGTH)
    public static Result untag() {
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
                return doResponse(400, "Invalid or missing counter name!");
            }
            List<String> tags = DPathUtils.getValue(params, "tags", List.class);
            if (tags == null || tags.size() == 0) {
                return doResponse(400, "Invalid or missing list of tags!");
            }

            if (Registry.api.untag(counterName, tags.toArray(ArrayUtils.EMPTY_STRING_ARRAY))) {
                return ok("{\"status\":200,\"message\":\"Ok\"}");
            } else {
                return ok("{\"status\":304,\"message\":\"Counter not exist or not configured!\"}");
            }
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
            return doResponse(500, e.getMessage());
        }
    }
}
