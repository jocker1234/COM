package com.com.backend.service;

import com.com.backend.dto.response.CaseAbstractsDtoResponse;
import com.com.backend.model.CaseAbstracts;
import com.com.backend.dto.request.CaseAbstractsDtoRequest;

public interface CaseAbstractsService extends AbstractsAbstractService<CaseAbstractsDtoRequest,
                                                                            CaseAbstractsDtoResponse, CaseAbstracts> {
}
