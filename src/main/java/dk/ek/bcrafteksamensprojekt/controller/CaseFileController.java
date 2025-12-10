package dk.ek.bcrafteksamensprojekt.controller;

import dk.ek.bcrafteksamensprojekt.model.CaseFile;
import dk.ek.bcrafteksamensprojekt.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cases/{caseId}/files")
public class CaseFileController {

    private final FileStorageService fileStorage;

    @PostMapping
    public ResponseEntity<?> uploadFile(
            @PathVariable Long caseId,
            @RequestParam("file") MultipartFile file) throws IOException {

        CaseFile saved = fileStorage.storeFile(caseId, file);
        return ResponseEntity.ok(saved);
    }
}
