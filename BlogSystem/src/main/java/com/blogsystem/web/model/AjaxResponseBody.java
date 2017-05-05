package com.blogsystem.web.model;

/**
 * Created by Spaskich on 5.5.2017 г..
 */
import java.util.List;

import com.blogsystem.entity.Comment;
import com.fasterxml.jackson.annotation.JsonView;
import com.blogsystem.web.jsonview.Views;

public class AjaxResponseBody {

    @JsonView(Views.Public.class)
    String msg;

    @JsonView(Views.Public.class)
    String code;

    @JsonView(Views.Public.class)
    Comment result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Comment getResult() {
        return result;
    }

    public void setResult(Comment result) {
        this.result = result;
    }
}

