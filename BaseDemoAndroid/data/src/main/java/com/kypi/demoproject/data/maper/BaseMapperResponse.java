package com.kypi.demoproject.data.maper;



import com.kypi.demoproject.data.remote.response.BaseResponse;
import com.kypi.demoproject.domain.error.MyError;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Khoa on 8/14/2017.
 */

public abstract class BaseMapperResponse<Response extends BaseResponse, Value> implements Function<Response, Value> {

    @Override
    public Value apply(@NonNull Response response) throws Exception {
        if(response.code != 0){
            String msg = printLog(response.code);
            System.out.println("com.gs2.iread.audio Error : " + msg);
            throw new MyError(response.code, response.msg);
        }
        return map(response);
    }

    public abstract Value map(Response response);

    private final String printLog(int code){
        String msg = "";

        switch (code){
            case MyError.FAIL:
                msg = "FAIL";
                break;
            case MyError.ERROR:
                msg = "ERROR";
                break;
            case MyError.DDOS:
                msg = "DDOS";
                break;
            case MyError.INVALID_PARAMS:
                msg = "INVALID_PARAMS";
                break;
            case MyError.NOT_EXISTS:
                msg = "NOT_EXISTS";
                break;
            case MyError.EXISTED:
                msg = "EXISTED";
                break;
            case MyError.NOT_LOGIN:
                msg = "NOT_LOGIN";
                break;
            case MyError.FULL:
                msg = "FULL";
                break;
            case MyError.INVALID_USERNAME:
                msg = "INVALID_USERNAME";
                break;
            case MyError.INVALID_DISPLAY_NAME:
                msg = "INVALID_DISPLAY_NAME";
                break;
            case MyError.INVALID_BIRTHDAY:
                msg = "INVALID_BIRTHDAY";
                break;
            case MyError.INVALID_GENDER:
                msg = "INVALID_GENDER";
                break;
            case MyError.INVALID_PHONE:
                msg = "INVALID_PHONE";
                break;
            case MyError.INVALID_EMAIL:
                msg = "INVALID_EMAIL";
                break;
        }

        return msg;
    }
}
