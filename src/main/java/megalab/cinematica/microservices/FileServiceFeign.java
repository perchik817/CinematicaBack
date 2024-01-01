package megalab.cinematica.microservices;

import megalab.cinematica.microservices.jsons.FileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "files", url = "http://localhost:8011")
public interface FileServiceFeign {
    @PostMapping(value = "/api/v1/file/upload", consumes = "multipart/form-data")
    FileResponse upload (@RequestPart MultipartFile file);
}
