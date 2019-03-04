package com.sky.gank.data.meizi;

import com.sky.gank.base.BaseResponse;

import java.util.List;

public class MeiziData extends BaseResponse {

    private boolean error;
    private List<MeizhiBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<MeizhiBean> getResults() {
        return results;
    }

    public void setResults(List<MeizhiBean> results) {
        this.results = results;
    }

}
