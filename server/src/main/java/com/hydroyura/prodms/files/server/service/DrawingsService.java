package com.hydroyura.prodms.files.server.service;

import com.hydroyura.prodms.files.server.api.drawings.params.GetLatestParams;
import com.hydroyura.prodms.files.server.api.res.GetLatestRes;
import com.hydroyura.prodms.files.server.data.DrawingsRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DrawingsService {

    private final DrawingsRepository drawingsRepository;

    public Optional<GetLatestRes> getLatest(String number, GetLatestParams params) {
        return null;
    }

}
