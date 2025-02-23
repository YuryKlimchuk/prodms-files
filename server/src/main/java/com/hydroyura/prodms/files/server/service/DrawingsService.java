package com.hydroyura.prodms.files.server.service;

import com.hydroyura.prodms.files.server.api.drawings.params.GetLatestParams;
import com.hydroyura.prodms.files.server.api.enums.DrawingType;
import com.hydroyura.prodms.files.server.api.req.AddFileReq;
import com.hydroyura.prodms.files.server.api.res.GetUrlsLatestRes;
import com.hydroyura.prodms.files.server.data.DrawingsRepository;
import com.hydroyura.prodms.files.server.service.filename.FileNameBuilderStrategy;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DrawingsService {

    private final DrawingsRepository drawingsRepository;
    private final FileNameBuilderStrategy fileNameBuilderStrategy;

    public Optional<GetUrlsLatestRes> getFiles(String number, GetLatestParams params) {
        Map<DrawingType, String> result = drawingsRepository.listObjects(number).entrySet()
            .stream()
            .map(entry -> Map.entry(entry.getKey(), drawingsRepository.getDrawingUrl(entry.getValue())))
            .filter(entry -> entry.getValue().isPresent())
            .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().get()));

        return result.isEmpty()
            ? Optional.empty()
            : Optional.of(new GetUrlsLatestRes(result));
    }


    public void addFile(String number, AddFileReq req) {
        String fileName = fileNameBuilderStrategy.build(number, req);
        Map<String, String> tags = Map.of("type", req.getType().getCode());

        drawingsRepository.addFile(fileName, tags, req.getBytes());
    }


}
