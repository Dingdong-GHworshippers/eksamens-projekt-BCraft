package dk.ek.bcrafteksamensprojekt.service;

import dk.ek.bcrafteksamensprojekt.model.Case;
import dk.ek.bcrafteksamensprojekt.model.CaseFile;
import dk.ek.bcrafteksamensprojekt.repository.CaseFileRepository;
import dk.ek.bcrafteksamensprojekt.repository.CaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private final CaseRepository caseRepo;
    private final CaseFileRepository caseFileRepo;

    public CaseFile storeFile(Long caseId, MultipartFile file) throws IOException {

        Case c = caseRepo.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Case not found"));

        String storedName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        Path target = Paths.get(uploadDir).resolve(storedName);
        Files.copy(file.getInputStream(), target);

        CaseFile cf = new CaseFile();
        cf.setFilename(storedName);
        cf.setOriginalFilename(file.getOriginalFilename());
        cf.setFileType(file.getContentType());
        cf.setFileSize(file.getSize());
        cf.setC(c);

        return caseFileRepo.save(cf);
    }
}
