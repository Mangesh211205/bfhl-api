package com.acropolis.apiround.service;

import com.acropolis.apiround.dto.BfhlRequest;
import com.acropolis.apiround.dto.BfhlResponse;

public interface BfhlService {
    BfhlResponse process(BfhlRequest request);
}
