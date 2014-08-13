package controllers;

import play.libs.F.Function0;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

    /*
     * Handles GET:/*
     */
    public static Promise<Result> noGet(String param) {
        Promise<Result> promise = Promise.promise(new Function0<Result>() {
            public Result apply() throws Exception {
                response().setContentType("application/json");
                return ok("{\"status\":405,\"message\":\"GET is not supported!\"}");
            }
        });
        return promise;
    }
}
