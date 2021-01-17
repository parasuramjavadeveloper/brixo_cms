package cms.brixo.se.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
/**
 * To capture Exception Resource.
 *
 * @author Parasuram
 * @since 16-01-2021
 */
public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private String errorMessage;
    private List<String> details;


}
