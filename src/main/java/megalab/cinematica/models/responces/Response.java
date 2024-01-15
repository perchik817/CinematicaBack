package megalab.cinematica.models.responces;

import lombok.*;
import lombok.experimental.FieldDefaults;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.utils.ResourceBundle;

@Builder
@AllArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Response <D>{
    String mess;
    D data;
    public static <D> Response<D> getSuccessResponse(D responseData, Language lang) {
        return Response
                .<D>builder()
                .mess(ResourceBundle.periodMess("successResponse", lang))
                .data(responseData)
                .build();
    }

    public static Response getSuccessDelete(String key, Language lang) {
        return Response
                .builder()
                .mess(ResourceBundle.periodMess("successDelete", lang))
                .build();
    }

    public static Response getUniqueFieldResponse(String key, Language lang) {
        return Response
                .builder()
                .mess(ResourceBundle.periodMess(key, lang))
                .build();
    }

    public static Response getErrorResponse(String key, Language language) {
        return Response
                .builder()
                .mess(ResourceBundle.periodMess(key, language))
                .build();
    }
}
