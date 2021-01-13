/**
 * Created By Achmad Yusuf
 * Github : abdullah1006
 **/
package com.example.crudmysql.Model;

import java.util.List;

public class ResponseModel {
    int status;
    boolean error;
    String message;
    List<DataModel> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataModel> getData() {
        return data;
    }

    public void setData(List<DataModel> data) {
        this.data = data;
    }
}
