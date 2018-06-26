package com.kypi.demoproject.data.remote.response;

import com.kypi.demoproject.domain.entities.IReadBookInfo;

import java.util.List;

public class IReadListBookDemoResponse extends BaseResponse {
    public List<IReadBookInfo> books;
}
