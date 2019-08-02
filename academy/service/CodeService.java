package mate.academy.service;

import mate.academy.model.Code;

public interface CodeService {

    void addCode(Code code);

    String getCode(Long id);
}
