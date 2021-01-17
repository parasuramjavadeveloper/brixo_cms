package cms.brixo.se.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(value = Include.NON_NULL)
/**
 * Class used for representing  Applications API Response
 * @author Parasuram
 * @since 15-01-2021
 */
public class Applications {

    private int status_code;
    private String Message;
    ArrayList<Application> Response = new ArrayList<Application>();


}
