package megalab.cinematica.microservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "files", url = "")
public interface FileServiceFeign {
    @GetMapping("api/v1/file/get/file")
}
