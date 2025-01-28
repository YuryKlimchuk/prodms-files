package com.hydroyura.prodms.files.server.service;

import com.hydroyura.prodms.files.server.api.drawings.params.GetLatestParams;
import com.hydroyura.prodms.files.server.api.enums.DrawingType;
import com.hydroyura.prodms.files.server.api.res.GetLatestRes;
import com.hydroyura.prodms.files.server.data.DrawingsRepository;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DrawingsService {

    private final DrawingsRepository drawingsRepository;

    public Optional<GetLatestRes> getLatest(String number, GetLatestParams params) {
        Map<DrawingType, String> result = drawingsRepository.listObjects(number).entrySet()
            .stream()
            .map(entry -> Map.entry(entry.getKey(), drawingsRepository.getDrawingUrl(entry.getValue())))
            .filter(entry -> entry.getValue().isPresent())
            .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().get()));

        return result.isEmpty()
            ? Optional.empty()
            : Optional.of(new GetLatestRes(result));
    }

}
