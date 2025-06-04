package org.koreait.planitkorea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConvertImgService {

    @Value("${file.path}")
    private String uploadPath;

    @Value("${file.url}")
    private String fileUrl;

    public String convertImgFile(MultipartFile file, String type) {
        if (file == null || file.isEmpty()) return null;

        try {
            // 디렉토리 구분
            String folderPath = uploadPath + type + "/";
            File dir = new File(folderPath);
            if (!dir.exists()) dir.mkdirs();

            // 파일명 유니크 처리
            String originalFilename = file.getOriginalFilename();
            String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uuid = UUID.randomUUID().toString();
            String savedFilename = uuid + ext;

            // 저장
            File dest = new File(folderPath + savedFilename);
            file.transferTo(dest);

            // 접근 가능한 URL 리턴 (DB에는 이 값을 저장)
            return fileUrl + type + "/" + savedFilename;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
